package ir.msit87.mylocationtest.frameWork.ui.custom;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.daimajia.easing.Skill;

import java.util.Arrays;

import fr.castorflex.android.circularprogressbar.CircularProgressDrawable;
import fr.castorflex.android.circularprogressbar.CircularProgressDrawable.Style;

import ir.msit87.mylocationtest.R;
import ir.msit87.mylocationtest.frameWork.helpers.AnimationHelper;
import ir.msit87.mylocationtest.frameWork.helpers.UtilityHelper;

/**
 * PrimaryButton Class Created by Mohammad Soltanmohammadi on 7/17/2017.
 */
public class PrimaryButton extends RelativeLayout {

    private static final String ICON_WIDTH_KEY = "width";
    private static final String ICON_HEIGHT_KEY = "height";

    private Button button;
    public AMSmartImageView imageView;
    String mFontStyle;
    private String mImageAlign;
    private int mImageResourceId;
    private int mPrimaryBackgroundColor;
    private float mStork;
    private int mStorkeColor;
    private String mText;
    public int mTextColor;
    private float mTextSize;
    private int originalWidth;
    ProgressBar progressBar;
    private AMTextView textView;

    public PrimaryButton(Context context) {
        super(context);
        setup(context, null);
    }

    public PrimaryButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup(context, attrs);
    }

    public PrimaryButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(context, attrs);
    }

    @TargetApi(21)
    public PrimaryButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setup(context, attrs);
    }

    public void clearPrimaryBackgroundColor() {
        if (Build.VERSION.SDK_INT >= 16) {
            this.button.setBackground(null);
        } else {
            this.button.setBackgroundDrawable(null);
        }
    }

    public void setPrimaryBackgroundColor(int color) {
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{color, color});
        if (this.mStork > 0.0f) {
            gradientDrawable.setStroke((int) this.mStork, this.mStorkeColor);
        }
        gradientDrawable.setCornerRadius(200.0f);
        if (Build.VERSION.SDK_INT >= 21) {
            this.button.setBackground(gradientDrawable);
            this.button.setStateListAnimator(null);
        } else if (Build.VERSION.SDK_INT >= 16) {
            this.button.setBackground(gradientDrawable);
        } else {
            this.button.setBackgroundDrawable(gradientDrawable);
        }
    }

    public void setOnClickListener(OnClickListener l) {
        this.button.setOnClickListener(l);
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.button.setEnabled(enabled);
    }

    public void setClickable(boolean clickable) {
        super.setClickable(clickable);
        this.button.setClickable(clickable);
    }

    private void setup(Context context, AttributeSet attrs) {
        this.button = new Button(context);
        addView(this.button);
        this.button.setLayoutParams(new LayoutParams(-1, -1));
        this.textView = new AMTextView(context);
        addView(this.textView);
//        this.textView.setFontStyle(this.mFontStyle);
        this.textView.setLayoutParams(new LayoutParams(-1, -1));
        LayoutParams layoutParams = (LayoutParams) this.textView.getLayoutParams();
        layoutParams.bottomMargin = UtilityHelper.dpToPx(3);
        this.textView.setLayoutParams(layoutParams);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PrimaryButton, 0, 0);
        try {
            this.mPrimaryBackgroundColor = a.getColor(R.styleable.PrimaryButton_backgroundColor, getResources().getColor(R.color.cardview_dark_background));
            this.mImageResourceId = a.getResourceId(R.styleable.PrimaryButton_image, -1);
            this.mTextColor = a.getColor(R.styleable.PrimaryButton_textColor, -1);
            this.mTextSize = a.getDimension(R.styleable.PrimaryButton_textSize, (float) UtilityHelper.dpToPx(14));
            this.mText = a.getString(R.styleable.PrimaryButton_text);
            this.mStork = a.getDimension(R.styleable.PrimaryButton_stork, 0.0f);
            this.mImageAlign = a.getString(R.styleable.PrimaryButton_imageAlign);
            this.mFontStyle = a.getString(R.styleable.PrimaryButton_textFontStyle);
            this.mStorkeColor = a.getColor(R.styleable.PrimaryButton_storkColor, this.mTextColor);
            if (this.mImageAlign == null) {
                this.mImageAlign = "center";
            }
            if (this.mFontStyle == null) {
                this.mFontStyle = "bold";
            }
            a.recycle();
            setPrimaryBackgroundColor(this.mPrimaryBackgroundColor);
            this.textView.setFontStyle(this.mFontStyle);
            this.textView.setTextColor(this.mTextColor);
            this.textView.setTextSize((float) UtilityHelper.pxToDp((int) this.mTextSize));
            this.textView.setText(this.mText);
            this.textView.setGravity(17);
            if (this.mImageResourceId > 0) {
                this.imageView = new AMSmartImageView(getContext());
                this.imageView.setImageResource(this.mImageResourceId);
                LayoutParams layoutParams1 = new LayoutParams(-2, -2);
                if (this.mImageAlign.equals("center")) {
                    layoutParams1.addRule(13, -1);
                } else if (this.mImageAlign.equals("leftCenter")) {
                    layoutParams1.addRule(15, -1);
                    layoutParams1.addRule(9, -1);
                    layoutParams1.leftMargin = UtilityHelper.dpToPx(15);
                } else if (this.mImageAlign.equals("rightCenter")) {
                    layoutParams1.addRule(15, -1);
                    layoutParams1.addRule(11, -1);
                    layoutParams1.rightMargin = UtilityHelper.dpToPx(15);
                }
                this.imageView.setLayoutParams(layoutParams1);
                addView(this.imageView);
            }
        } catch (Throwable th) {
            a.recycle();
        }
    }

    private static Drawable getRippleMask(int color) {
        float[] outerRadii = new float[100];
        Arrays.fill(outerRadii, 100.0f);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(outerRadii, null, null));
        shapeDrawable.getPaint().setColor(color);
        return shapeDrawable;
    }

    public void showLoading() {
        this.progressBar = new ProgressBar(getContext());
        LayoutParams progressBarLayoutParams = new LayoutParams(-1, -1);
        progressBarLayoutParams.leftMargin = UtilityHelper.dpToPx(5);
        progressBarLayoutParams.rightMargin = progressBarLayoutParams.leftMargin;
        progressBarLayoutParams.topMargin = progressBarLayoutParams.leftMargin;
        progressBarLayoutParams.bottomMargin = progressBarLayoutParams.leftMargin;
        this.progressBar.setLayoutParams(progressBarLayoutParams);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        this.originalWidth = layoutParams.width;
        AnimationHelper.animateView(this, Skill.SineEaseOut, ICON_WIDTH_KEY, 250, (float) layoutParams.height);
        this.button.setEnabled(false);
        this.progressBar.setIndeterminateDrawable(new CircularProgressDrawable.Builder(getContext()).color(this.mTextColor).sweepSpeed(0.95f).strokeWidth((float) UtilityHelper.dpToPx(2)).style(Style.ROUNDED).build());
        addView(this.progressBar);
        AnimationHelper.animateView(this.progressBar, Skill.SineEaseOut, "alpha", 250, 0.0f, 1.0f);
        AnimationHelper.animateView(this.textView, Skill.SineEaseOut, "alpha", 250, 1.0f, 0.0f);
        if (this.imageView != null) {
            AnimationHelper.animateView(this.imageView, Skill.SineEaseOut, "alpha", 250, 1.0f, 0.0f);
        }
    }

    public void hideLoading() {
        AnimationHelper.animateView(this, Skill.SineEaseOut, ICON_WIDTH_KEY, 250, (float) this.originalWidth);
        AnimationHelper.animateView(this.progressBar, Skill.SineEaseIn, ICON_WIDTH_KEY, 250, 1.0f, 0.0f, new AnimationHelper.CompleteListener() {
            public void onFinish() {
                PrimaryButton.this.removeView(PrimaryButton.this.progressBar);
            }
        });
        AnimationHelper.animateView(this.textView, Skill.SineEaseOut, "alpha", 250, 0.0f, 1.0f);
        if (this.imageView != null) {
            AnimationHelper.animateView(this.imageView, Skill.SineEaseOut, "alpha", 250, 0.0f, 1.0f);
        }
        UtilityHelper.delay(0.3d, new UtilityHelper.DelayCallback() {
            public void afterDelay() {
                PrimaryButton.this.button.setEnabled(true);
            }
        });
    }

    public void setText(String text) {
        this.textView.setText(text);
    }
}


//public class PrimaryButton extends RelativeLayout {
//
//    private static final String ICON_WIDTH_KEY = "10";
//    private static final String ICON_HEIGHT_KEY = "10";
//
//    private Button button;
//    public AMSmartImageView imageView;
//    public AMTextView textView;
//
//    private String mImageAlign;
//    private int mImageResourceId;
//    private int mPrimaryBackgroundColor;
//    private float mStork;
//    private int mStorkColor;
//    private String mText;
//    public int mTextColor;
//    private float mTextSize;
//    private String mFontStyle;
//
//    private int originalWidth;
//    ProgressBar progressBar;
//
//    public PrimaryButton(Context context) {
//        super(context);
//        setUp(context, null);
//    }
//
//    public PrimaryButton(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        setUp(context, attrs);
//    }
//
//    public PrimaryButton(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        setUp(context, attrs);
//    }
//
//    @TargetApi(21)
//    public PrimaryButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//        setUp(context, attrs);
//    }
//
//    private void setUp(Context context, AttributeSet attrs) {
//
//        this.button = new Button(context);
//        addView(this.button);
//
//        this.button.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
//
//        this.textView = new AMTextView(context);
//        addView(this.textView);
//        this.textView.setFontStyle(this.mFontStyle);
//        this.textView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
//        LayoutParams layoutParams = (LayoutParams) this.textView.getLayoutParams();
//        layoutParams.bottomMargin = UtilityHelper.dpToPx(3);
//        this.textView.setLayoutParams(layoutParams);
//
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PrimaryButton, 0, 0);
//        try {
//            this.mPrimaryBackgroundColor = typedArray.getColor(R.styleable.PrimaryButton_backgroundColor, getResources().getColor(R.color.darkGray));
//            this.mImageResourceId = typedArray.getResourceId(R.styleable.PrimaryButton_image, -1);
//            this.mTextColor = typedArray.getColor(R.styleable.PrimaryButton_textColor, -1);
//            this.mTextSize = typedArray.getDimension(R.styleable.PrimaryButton_textSize, (float) UtilityHelper.dpToPx(14));
//            this.mText = typedArray.getString(R.styleable.PrimaryButton_text);
//            this.mStork = typedArray.getDimension(R.styleable.PrimaryButton_stork, 0.0f);
//            this.mStorkColor = typedArray.getColor(R.styleable.PrimaryButton_storkColor, this.mTextColor);
//
//            this.mImageAlign = typedArray.getString(R.styleable.PrimaryButton_imageAlign);
//            if (this.mImageAlign != null)
//                this.mImageAlign = "center";
//
//            this.mFontStyle = typedArray.getString(R.styleable.PrimaryButton_textFontStyle);
//            if (this.mFontStyle != null)
//                this.mFontStyle = "Bold";
//
//            typedArray.recycle();
//
//            setPrimaryBackgroundColor(mPrimaryBackgroundColor);
//            this.textView.setTextColor(this.mTextColor);
//            this.textView.setTextSize((float) UtilityHelper.dpToPx((int) this.mTextSize));
//            this.textView.setText(this.mText);
//            this.textView.setGravity(17);
//
//            if (this.mImageResourceId > 0) {
//                this.imageView = new AMSmartImageView(getContext());
//                this.imageView.setImageResource(this.mImageResourceId);
//
//                LayoutParams layoutParams1 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//                switch (this.mImageAlign) {
//                    case "center":
//                        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE); //(13,-1)
//                        break;
//
//                    case "leftCenter":
//                        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE); //(15,-1)
//                        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE); //(9,-1)
//                        layoutParams.rightMargin = UtilityHelper.dpToPx(15);
//                        break;
//
//                    case "rightCenter":
//                        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE); //(15,-1)
//                        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE); //(11,-1)
//                        break;
//                }
//                this.imageView.setLayoutParams(layoutParams1);
//                addView(this.imageView);
//            }
//
//        } catch (Resources.NotFoundException e) {
//            typedArray.recycle();
//        }
//
//    }
//
//    private void setPrimaryBackgroundColor(int color) {
//        GradientDrawable gradientDrawable =
//                new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{color, color});
//
//        if (this.mStork > 0.0f) {
//            gradientDrawable.setStroke((int) this.mStork, this.mStorkColor);
//        }
//
//        gradientDrawable.setCornerRadius(200.0f);
//
//        if (Build.VERSION.SDK_INT > 21) {
//            this.button.setBackground(gradientDrawable);
//            this.button.setStateListAnimator(null);
//        } else if (Build.VERSION.SDK_INT >= 16) {
//            this.button.setBackground(gradientDrawable);
//        } else {
//            this.button.setBackgroundDrawable(gradientDrawable);
//        }
//
//    }
//
//    private static Drawable getRippleMask(int color) {
//        float[] outerRadii = new float[100];
//        Arrays.fill(outerRadii, 100.0f);
//        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(outerRadii, null, null));
//        shapeDrawable.getPaint().setColor(color);
//        return shapeDrawable;
//    }
//
//    public void showLoading() {
//
//        this.progressBar = new ProgressBar(getContext());
//        LayoutParams progressBarLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
//        progressBarLayoutParams.setMargins(UtilityHelper.dpToPx(5), UtilityHelper.dpToPx(5), UtilityHelper.dpToPx(5), UtilityHelper.dpToPx(5));
//        this.progressBar.setLayoutParams(progressBarLayoutParams);
//
//        LayoutParams layoutParams = (LayoutParams) getLayoutParams();
//        this.originalWidth = layoutParams.width;
//        AnimationHelper.animateView(this, Skill.SineEaseOut, ICON_WIDTH_KEY, 250, (float) layoutParams.height);
//
//        this.button.setEnabled(false);
//
//        this.progressBar.setIndeterminateDrawable(new CircularProgressDrawable.Builder(getContext()).color(this.mTextColor).sweepSpeed(0.95f).
//                strokeWidth((float) UtilityHelper.dpToPx(2)).style(Style.ROUNDED).build());
//        addView(this.progressBar);
//
//        AnimationHelper.animateView(this.progressBar, Skill.SineEaseOut, "alpha", 250, 0.0f, 1.0f);
//
//        AnimationHelper.animateView(this.textView, Skill.SineEaseOut, "alpha", 250, 1.0f, 0.0f);
//
//        assert this.imageView != null;
//        AnimationHelper.animateView(this.imageView, Skill.SineEaseOut, "alpha", 250, 1.0f, 0.0f);
//    }
//
//    public void hideLoading() {
//
//        AnimationHelper.animateView(this, Skill.SineEaseOut, ICON_WIDTH_KEY, 250, (float) this.originalWidth);
//
//        AnimationHelper.animateView(this.progressBar, Skill.SineEaseIn, ICON_WIDTH_KEY, 250, 1.0f, 0.0f, new AnimationHelper.CompleteListener() {
//            @Override
//            public void onFinish() {
//                PrimaryButton.this.removeView(PrimaryButton.this.progressBar);
//            }
//        });
//
//        AnimationHelper.animateView(this.textView, Skill.SineEaseOut, "alpha", 250, 0.0f, 1.0f);
//
//        assert this.imageView != null;
//        AnimationHelper.animateView(this.imageView, Skill.SineEaseOut, ICON_WIDTH_KEY, 250, (float) this.originalWidth);
//
//        UtilityHelper.delay(0.3d, new UtilityHelper.DelayCallback() {
//            public void afterDelay() {
//                PrimaryButton.this.button.setEnabled(true);
//            }
//        });
//    }
//
//    public void setText(String text) {
//        this.textView.setText(text);
//    }
//}
