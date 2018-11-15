package qin.com.coodinatorqin.behavior;

import android.animation.Animator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;

/**
 * Create by qindl
 * on 2018/11/14
 */
public class BaseFloatButtonBehavior extends CoordinatorLayout.Behavior<FloatingActionButton> {

    private boolean isAnimate;
    private OnFloatStateChangedListener mOnFloatStateChangedListener;
    private int mHeightPixels;

    private interface OnFloatStateChangedListener {
        void onChanged(boolean isShow);
    }

    public BaseFloatButtonBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHeightPixels = context.getResources().getDisplayMetrics().heightPixels;
    }

    public void setOnFloatStateChangedListener(OnFloatStateChangedListener onFloatStateChangedListener) {
        this.mOnFloatStateChangedListener = onFloatStateChangedListener;
    }

    public static BaseFloatButtonBehavior from(View view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (!(params instanceof CoordinatorLayout.LayoutParams)) {
            throw new IllegalArgumentException("这个View必须是CoordinatorLayout的子View");
        }
        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) params).getBehavior();
        if (!(behavior instanceof FloatButtonScaleBehavior)) {
            throw new IllegalArgumentException("必须指定为BaseFloatButtonBehavior子类");
        }
        return (FloatButtonScaleBehavior) behavior;
    }

    public void show(FloatingActionButton button) {
        if (!isAnimate)
            AnimationUtils.scale(button, 1f, 1f, 500, listener);
    }

    public void hide(FloatingActionButton button) {
        if (!isAnimate) {
           AnimationUtils.scale(button, 0f, 0f, 500, listener);
        }
    }

    public void translationDownY(FloatingActionButton button) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) button.getLayoutParams();
        int bottomMargin = params.bottomMargin;
        if (!isAnimate) {
            AnimationUtils.translationY(button,button.getHeight()+bottomMargin,500,listener);
        }
    }

    public void translationUpY(FloatingActionButton button) {
        if (!isAnimate) {
            AnimationUtils.translationY(button,0,500,listener);
        }
    }

    private ViewPropertyAnimatorListener listener = new ViewPropertyAnimatorListener() {
        @Override
        public void onAnimationStart(View view) {
            isAnimate = true;
        }

        @Override
        public void onAnimationEnd(View view) {
            isAnimate = false;
        }

        @Override
        public void onAnimationCancel(View view) {
            isAnimate = false;
        }
    };
    public static class AnimationUtils {

        public static void scale(View view, float x, float y) {
            scale(view, x, y, 500, new LinearOutSlowInInterpolator(), null);
        }

        public static void scale(View view, float x, float y, int duration) {
            scale(view, x, y, duration, new LinearOutSlowInInterpolator(), null);
        }

        public static void scale(View view, float x, float y, int duration, Interpolator interpolator) {
            scale(view, x, y, duration, interpolator, null);
        }

        public static void scale(View view, float x, float y, int duration, ViewPropertyAnimatorListener listener) {
            scale(view, x, y, duration, new LinearOutSlowInInterpolator(), listener);
        }

        public static void translationX(View view, float x, int duration,ViewPropertyAnimatorListener listener) {
            translationX(view, x, duration, new LinearOutSlowInInterpolator(), listener);
        }

        public static void translationX(View view, float x, int duration,Interpolator interpolator) {
            translationX(view, x, duration, interpolator, null);
        }

        public static void translationY(View view, float y, int duration,ViewPropertyAnimatorListener listener) {
            translationY(view, y, duration, new LinearOutSlowInInterpolator(), listener);
        }

        public static void translationY(View view, float y, int duration,Interpolator interpolator) {
            translationY(view, y, duration, interpolator, null);
        }

        public static void alpha(View view, float v, int duration, ViewPropertyAnimatorListener listener) {
            alpha(view, v, duration, new LinearOutSlowInInterpolator(), listener);
        }

        public static void alpha(View view, float v, int duration, Interpolator interpolator) {
            alpha(view, v, duration, interpolator, null);
        }

        public static void scale(View view, float x, float y, int duration, Interpolator interpolator, ViewPropertyAnimatorListener listener) {
            ViewCompat.animate(view).scaleX(x).scaleY(y).setDuration(duration).setInterpolator(interpolator).setListener(listener);
        }

        public static void translationX(View view, float x, int duration, Interpolator interpolator, ViewPropertyAnimatorListener listener) {
            ViewCompat.animate(view).translationX(x).setDuration(duration).setInterpolator(interpolator).setListener(listener);
        }

        public static void translationY(View view, float y, int duration, Interpolator interpolator, ViewPropertyAnimatorListener listener) {
            ViewCompat.animate(view).translationY(y).setDuration(duration).setInterpolator(interpolator).setListener(listener);
        }

        public static void alpha(View view, float v, int duration, Interpolator interpolator, ViewPropertyAnimatorListener listener) {
            ViewCompat.animate(view).alpha(v).setDuration(duration).setInterpolator(interpolator).setListener(listener);
        }

    }
}
