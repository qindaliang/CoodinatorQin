package qin.com.coodinatorqin.behavior;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Scroller;

import java.lang.ref.WeakReference;

import qin.com.coodinatorqin.R;

/**
 * Create by qindl
 * on 2018/11/14
 */
public class DownAndUpSearchFancyBehavior extends CoordinatorLayout.Behavior<NestedScrollView> {
    private static final String TAG = "TAG";
    private WeakReference<View> mDependencyView;
    private final Handler mHandler;
    private final Scroller mScroller;
    private boolean isExpand;
    private boolean isScrolling;
    private boolean isUp;
    private boolean isFirst = true;

    public DownAndUpSearchFancyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
        mHandler = new Handler();
    }

    @Override
    public boolean onLayoutChild(@NonNull CoordinatorLayout parent, @NonNull NestedScrollView child, int layoutDirection) {
        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        if (lp.height == CoordinatorLayout.LayoutParams.MATCH_PARENT) {
            child.layout(0, 0, parent.getWidth(), (int) (parent.getHeight() - 150));
            return true;
        }
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull NestedScrollView child, @NonNull View dependency) {
        if (dependency.getId() == R.id.image) {
            mDependencyView = new WeakReference<>(dependency);
            return true;
        }
        return false;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull NestedScrollView child, @NonNull View dependency) {
        final float progress = 1.0f - Math.abs(dependency.getTranslationY() / (dependency.getHeight() - 150));
        float scale = 1 + (1 - progress);
        child.setTranslationY(dependency.getHeight() + dependency.getTranslationY());
        if (isUp) {
            dependency.setScaleX(scale);
            dependency.setScaleY(scale);
            dependency.setAlpha(progress);
        } else {
            dependency.setScaleY(scale);
            dependency.setAlpha(progress);

        }
        return true;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull NestedScrollView child, @NonNull View directTargetChild, @NonNull View target, int axes) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull CoordinatorLayout coordinatorLayout, @NonNull NestedScrollView child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, axes, type);
        mScroller.abortAnimation();
        isScrolling = false;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull NestedScrollView child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        float translationY = getDependencyView().getTranslationY();
        float newY = translationY - dy;
        if (dy < 0) {
            if (isUp) {
                return;
            }
            getDependencyView().setTranslationY(Math.abs(newY));
        } else {
            isUp = true;
            int maxY = -(getDependencyView().getHeight() - 150);
            if (newY >= maxY) {
                getDependencyView().setTranslationY(newY);
                consumed[1] = dy;
            }
        }
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull NestedScrollView child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
        View dependentView = getDependencyView();
        float newTranslateY = dependentView.getTranslationY() - dyUnconsumed;
        float maxHeaderTranslate = 0;
        if (dyUnconsumed > 0) {
            return;
        } else {
            if (newTranslateY < maxHeaderTranslate) {
                dependentView.setTranslationY(newTranslateY);
            }
        }
    }

    @Override
    public boolean onNestedPreFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull NestedScrollView child, @NonNull View target, float velocityX, float velocityY) {
        return onUserStopDragging(velocityY);
    }

    @Override
    public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull NestedScrollView child, @NonNull View target, int type) {
        super.onStopNestedScroll(coordinatorLayout, child, target, type);
        if (!isScrolling) {
            onUserStopDragging(1500);
        }
    }


    private boolean onUserStopDragging(float velocity) {
        View dependentView = getDependencyView();
        float translateY = dependentView.getTranslationY();
        float minHeaderTranslate = -(dependentView.getHeight() - 150);
        if (translateY == 0 || translateY == minHeaderTranslate) {
            return false;
        }
        boolean targetState;
        if (Math.abs(velocity) <= 1500) {
            if (Math.abs(translateY) < Math.abs(translateY - minHeaderTranslate)) {
                targetState = false;
                isUp = false;
            } else {
                targetState = true;
            }
            velocity = 1500;
        } else {
            if (velocity > 0) {
                targetState = true;
            } else {
                targetState = false;
                isUp = false;
            }
        }
        float targetTranslateY = targetState ? minHeaderTranslate : 0;
        mScroller.startScroll(0, (int) translateY, 0, (int) (targetTranslateY - translateY), (int) (500000 / Math.abs(velocity)));
        mHandler.post(ScrollerRunnable);
        isScrolling = true;
        return true;
    }

    public View getDependencyView() {
        return mDependencyView.get();
    }

    private Runnable ScrollerRunnable = new Runnable() {
        @Override
        public void run() {
            if (mScroller.computeScrollOffset()) {
                getDependencyView().setTranslationY(mScroller.getCurrY());
                mHandler.post(this);
            } else {
                isExpand = getDependencyView().getTranslationY() != 0;
                isScrolling = false;
            }
        }
    };
}
