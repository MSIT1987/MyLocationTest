package ir.msit87.mylocationtest.frameWork.ui.custom;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.io.IOException;
import java.net.URL;

import ir.msit87.mylocationtest.R;
import ir.msit87.mylocationtest.frameWork.helpers.UtilityHelper;

/**
 * AMSmartImageView Class Created by Mohammad Soltanmohammadi on 7/15/2017.
 */

@SuppressLint("AppCompatCustomView")
public class AMSmartImageView extends ImageView {

    boolean mRounded = false;
    private OnImageChangeListener onImageChangeListener;

    public AMSmartImageView(Context context) {
        super(context);
        setUp(context, null);
    }

    public AMSmartImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setUp(context, attrs);
    }

    public AMSmartImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUp(context, attrs);
    }

    @TargetApi(21)
    public AMSmartImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setUp(context, attrs);
    }

    private void setUp(Context context, AttributeSet attrs) {
        TypedArray arrayOfValue = context.obtainStyledAttributes(attrs, R.styleable.AMSmartImageView, 0, 0);
        try {
            this.mRounded = arrayOfValue.getBoolean(R.styleable.AMSmartImageView_rounded, false);
        } finally {
            arrayOfValue.recycle();
        }
    }

    public interface OnImageChangeListener {
        void imageChangedInView(Object obj);
    }

    public void setImageChangeListener(OnImageChangeListener onImageChangeListener) {
        this.onImageChangeListener = onImageChangeListener;
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        if (bm != null && mRounded) {
            bm = getRoundedCornerBitmap(bm);
        }
        super.setImageBitmap(bm);

        if (this.onImageChangeListener != null) {
            this.onImageChangeListener.imageChangedInView(bm);
        }
    }

    @Override
    public void setImageDrawable(@Nullable Drawable drawable) {
        super.setImageDrawable(drawable);
        if (this.onImageChangeListener != null) {
            this.onImageChangeListener.imageChangedInView(drawable);
        }
        if (this.mRounded) {
            buildDrawingCache();
        }
    }

    @Override
    public void setBackgroundResource(@DrawableRes int resid) {
        super.setBackgroundResource(resid);
        if (this.onImageChangeListener != null) {
            this.onImageChangeListener.imageChangedInView(Integer.valueOf(resid));
        }
    }

    @Override
    @Deprecated
    public void setBackgroundDrawable(Drawable background) {
        super.setBackgroundDrawable(background);
        if (this.onImageChangeListener != null) {
            this.onImageChangeListener.imageChangedInView(background);
        }
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {

        /**Rect*/
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);

        /**Paint*/
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(12434878);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));

        /**Canvas*/
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(rectF, 12.0f, 12.0f, paint);

        return output;
    }

    @Override
    public void setImageURI(@Nullable Uri uri) {
        super.setImageURI(uri);
        if (this.onImageChangeListener != null) {
            this.onImageChangeListener.imageChangedInView(uri);
        }
    }

    public void setImageUrl(String url) {
        if (url != null && url.length() != 0) {
            String imageName = UtilityHelper.lastPath(url);
            try {
                setImageDrawable(Drawable.createFromStream(getContext().getAssets().open("static-images/" + imageName), null));
            } catch (IOException e) {
                int id = getContext().
                        getResources().
                        getIdentifier(imageName, "mipmap", getContext().getPackageName());
                if (id > 0) {
                    setImageResource(id);
                    if (this.onImageChangeListener != null) {
                        this.onImageChangeListener.imageChangedInView(null);
                    }
                } else if (mRounded) {

                    Glide.
                            with(getContext()).
                            load(url).
                            asBitmap().
                            centerCrop().
                            into(new BitmapImageViewTarget(this) {
                                @Override
                                protected void setResource(Bitmap resource) {
                                    //super.setResource(resource);
                                    RoundedBitmapDrawable circularBitmapDrawable =
                                            RoundedBitmapDrawableFactory.
                                                    create(AMSmartImageView.this.getContext().getResources(), resource);
                                    circularBitmapDrawable.setCircular(true);
                                    if (AMSmartImageView.this.onImageChangeListener != null) {
                                        AMSmartImageView.this.onImageChangeListener.imageChangedInView(null);
                                    }
                                }
                            });
                } else {
                    try {

                        Glide.
                                with(getContext()).
                                load(url).
                                diskCacheStrategy(DiskCacheStrategy.ALL).
                                skipMemoryCache(false).
                                into(this);

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }
}
