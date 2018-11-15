package qin.com.coodinatorqin.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Create by qindl
 * on 2018/11/14
 */
public class FloatButtonScaleBehavior extends BaseFloatButtonBehavior {

    public FloatButtonScaleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        Log.i("TAG", "dyConsumed: " + dyConsumed);//下滑为负，上滑为正
        Log.i("TAG", "dyUnconsumed: " + dyUnconsumed);
        if (dyConsumed > 0 || dyUnconsumed > 0 && child.getVisibility() == View.VISIBLE) {
            hide(child);
        } else {
            show(child);
        }
    }

    @Override
    public boolean onNestedFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View target, float velocityX, float velocityY, boolean consumed) {
        Log.i("TAG", "onNestedFling: " + velocityY);//velocityY表示速度,上为正
        if (velocityY < 0 && child.getVisibility() == View.VISIBLE) {
            hide(child);
        } else {
            show(child);
        }
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }
}
