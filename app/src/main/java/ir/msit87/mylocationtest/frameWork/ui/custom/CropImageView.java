package ir.msit87.mylocationtest.frameWork.ui.custom;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import ir.msit87.mylocationtest.frameWork.helpers.UtilityHelper;


/**
 * CropImageView Class Created by Mohammad Soltanmohammadi on 7/17/2017.
 */

public class CropImageView extends AMSmartImageView {

    float mHeightPercent = 1.0f;
    float mWidthPercent = 0.0f;

    public CropImageView(Context context) {
        super(context);
        setUp();
    }

    public CropImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setUp();
    }

    public CropImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUp();
    }

    public CropImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setUp();
    }

    public void setOffset(float heightPercent, float widthPercent) {
        this.mHeightPercent = heightPercent;
        this.mWidthPercent = widthPercent;
    }

    private void setUp() {
        setScaleType(ScaleType.MATRIX);
        setImageChangeListener(new OnImageChangeListener() {
            @Override
            public void imageChangedInView(Object obj) {
                CropImageView.this.requestLayout();
                CropImageView.this.forceLayout();
                UtilityHelper.delay(0.01d, new UtilityHelper.DelayCallback() {
                    @Override
                    public void afterDelay() {
                        CropImageView.this.requestLayout();
                        CropImageView.this.forceLayout();
                    }
                });
            }
        });
    }

    @Override
    protected boolean setFrame(int l, int t, int r, int b) {

        float scale;
        Matrix matrix = getMatrix();

        int viewWidth = (getWidth() - getPaddingLeft()) - getPaddingRight();
        int viewHeight = (getHeight() - getPaddingTop()) - getPaddingBottom();

        int drawableWidth = 0;
        int drawableHeight = 0;

        if (getDrawable() != null) {
            drawableWidth = getDrawable().getIntrinsicWidth();
            drawableHeight = getDrawable().getIntrinsicHeight();
        }

        if (drawableWidth * viewHeight > viewWidth * drawableHeight) {
            scale = (float) viewHeight / (float) drawableHeight;
        } else {
            scale = (float) viewWidth / (float) drawableWidth;
        }

        float viewToDrawableWidth = ((float) viewWidth) / scale;
        float viewToDrawableHeight = ((float) viewHeight) / scale;

        float xOffset = this.mWidthPercent * (((float) drawableWidth) - viewToDrawableWidth);
        float yOffset = this.mHeightPercent * (((float) drawableHeight) - viewToDrawableHeight);

        matrix.setRectToRect(new RectF(xOffset, yOffset, xOffset + viewToDrawableWidth, yOffset + viewToDrawableHeight)
                , new RectF(0, 0, viewWidth, viewHeight), Matrix.ScaleToFit.FILL);

        if (String.format("%f", new Object[]{Float.valueOf(viewToDrawableWidth)}).equals("NaN")) {
            UtilityHelper.delay(0.001d, new UtilityHelper.DelayCallback() {
                @Override
                public void afterDelay() {
                    CropImageView.this.requestLayout();
                    CropImageView.this.forceLayout();
                }
            });
        } else {
            setImageMatrix(matrix);
        }

        return super.setFrame(l, t, r, b);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        requestLayout();
        forceLayout();
        UtilityHelper.delay(0.01d, new UtilityHelper.DelayCallback() {
            @Override
            public void afterDelay() {
                CropImageView.this.requestLayout();
                CropImageView.this.forceLayout();
            }
        });
    }
}
