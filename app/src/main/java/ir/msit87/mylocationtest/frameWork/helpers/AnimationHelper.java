package ir.msit87.mylocationtest.frameWork.helpers;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.daimajia.easing.Glider;
import com.daimajia.easing.Skill;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.view.ViewPropertyAnimator;

import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

//import io.fabric.sdk.android.services.settings.SettingsJsonConstants;

public class AnimationHelper {

    public final static String ICON_HEIGHT_KEY = "10";
    public final static String ICON_WIDTH_KEY = "15";


    public interface CompleteListener {
        void onFinish();
    }

    public static void animateView(View view, Skill skill, String property, int duration, float to, CompleteListener completeListener) {
        animateView(view, skill, property, duration, getCurrentValueOfProperty(view, property), to, completeListener);
    }

    public static void animateView(View view, Skill skill, String property, int duration, float to) {
        animateView(view, skill, property, duration, getCurrentValueOfProperty(view, property), to, null);
    }

    public static void animateView(View view, Skill skill, String property, int duration, float from, float to) {
        animateView(view, skill, property, duration, from, to, null);
    }

    public static void animateView(final View view, Skill skill, final String property, int duration, float from, float to, final CompleteListener completeListener) {
        if (view != null) {
            ViewPropertyAnimator.animate(view).cancel();

            android.animation.ValueAnimator a = Glider.glide(skill, (float) duration, android.animation.ValueAnimator.ofFloat(from, to));
            a.setDuration((long) duration);
            a.setInterpolator(new AccelerateDecelerateInterpolator());
            a.addUpdateListener(new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator animation) {
                    AnimationHelper.setValueForProperty(view, property, ((Float) animation.getAnimatedValue()).floatValue());
                }
            });

            a.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animation) {
                    if (completeListener != null) {
                        completeListener.onFinish();
                    }
                }
            });
            a.start();
        }
    }

    public static void setValueForProperty(View view, String property, float value) {
        final ViewGroup.LayoutParams layoutParams;
        switch (property) {
            case "alpha":
                view.setAlpha(value);
                break;
            case "scale":
                view.setScaleX(value);
                view.setScaleY(value);
                break;
            case ICON_HEIGHT_KEY:
                layoutParams = view.getLayoutParams();
                layoutParams.height = (int) value;
                view.requestLayout();
                break;
            case ICON_WIDTH_KEY:
                layoutParams = view.getLayoutParams();
                layoutParams.width = (int) value;
                view.requestLayout();
                break;
            case "marginBottom":
                layoutParams = view.getLayoutParams();
                if (LayoutParams.class.isInstance(layoutParams)) {
                    ((LayoutParams) layoutParams).bottomMargin = (int) value;
                } else if (LinearLayout.LayoutParams.class.isInstance(layoutParams)) {
                    ((LinearLayout.LayoutParams) layoutParams).bottomMargin = (int) value;
                }
                view.requestLayout();
                break;
            case "marginTop":
                layoutParams = view.getLayoutParams();
                if (LayoutParams.class.isInstance(layoutParams)) {
                    ((LayoutParams) layoutParams).topMargin = (int) value;
                } else if (LinearLayout.LayoutParams.class.isInstance(layoutParams)) {
                    ((LinearLayout.LayoutParams) layoutParams).topMargin = (int) value;
                }
                view.requestLayout();
                break;
            case "marginLeft":
                layoutParams = view.getLayoutParams();
                if (LayoutParams.class.isInstance(layoutParams)) {
                    ((LayoutParams) layoutParams).leftMargin = (int) value;
                } else if (LinearLayout.LayoutParams.class.isInstance(layoutParams)) {
                    ((LinearLayout.LayoutParams) layoutParams).leftMargin = (int) value;
                }
                view.requestLayout();
                break;
            case "marginRight":
                layoutParams = view.getLayoutParams();
                if (LayoutParams.class.isInstance(layoutParams)) {
                    ((LayoutParams) layoutParams).rightMargin = (int) value;
                } else if (LinearLayout.LayoutParams.class.isInstance(layoutParams)) {
                    ((LinearLayout.LayoutParams) layoutParams).rightMargin = (int) value;
                }
                view.requestLayout();
                break;
        }
    }

    public static float getCurrentValueOfProperty(View view, String property) {

        ViewGroup.LayoutParams layoutParams;

        if (property.equals("alpha")) {
            return view.getAlpha();
        }
        if (property.equals("scale")) {
            return view.getScaleX();
        }
//        if (property.equals(SettingsJsonConstants.ICON_HEIGHT_KEY)) {
        if (property.equals(ICON_HEIGHT_KEY)) {
            return (float) view.getLayoutParams().height;
        }
//        if (property.equals(SettingsJsonConstants.ICON_WIDTH_KEY)) {
        if (property.equals(ICON_WIDTH_KEY)) {
            return (float) view.getLayoutParams().width;
        }

        if (property.equals("marginBottom")) {
            layoutParams = view.getLayoutParams();
            if (LayoutParams.class.isInstance(layoutParams)) {
                return (float) ((LayoutParams) layoutParams).bottomMargin;
            }
            if (LinearLayout.LayoutParams.class.isInstance(layoutParams)) {
                return (float) ((LinearLayout.LayoutParams) layoutParams).bottomMargin;
            }
        } else if (property.equals("marginTop")) {
            layoutParams = view.getLayoutParams();
            if (LayoutParams.class.isInstance(layoutParams)) {
                return (float) ((LayoutParams) layoutParams).topMargin;
            }
            if (LinearLayout.LayoutParams.class.isInstance(layoutParams)) {
                return (float) ((LinearLayout.LayoutParams) layoutParams).topMargin;
            }
        } else if (property.equals("marginLeft")) {
            layoutParams = view.getLayoutParams();
            if (LayoutParams.class.isInstance(layoutParams)) {
                return (float) ((LayoutParams) layoutParams).leftMargin;
            }
            if (LinearLayout.LayoutParams.class.isInstance(layoutParams)) {
                return (float) ((LinearLayout.LayoutParams) layoutParams).leftMargin;
            }
        }
        return 0.0f;
    }
}
