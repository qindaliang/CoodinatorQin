package qin.com.coodinatorqin.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Create by qindl
 * on 2018/11/14
 */
public class BottomViewBehavior extends CoordinatorLayout.Behavior<View> {

    private int mBottomMargin;

    public BottomViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        float scale=context.getResources().getDisplayMetrics().density;
        mBottomMargin = (int) (40 * scale + 0.5f);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
        float bottomMargin = params.bottomMargin;
        Log.i("TAG", "bottomMargin: "+bottomMargin);
        float childHeight = child.getHeight();
        float dependencyHeight = dependency.getHeight();
        float scale = (childHeight+bottomMargin)/dependencyHeight;
        float y = scale * (Math.abs(dependency.getY()));
        child.setTranslationY(y);
        Log.i("TAG", "childHeight: "+childHeight);
        Log.i("TAG", "scale: "+scale);
        Log.i("TAG", "y: "+y);
        return true;
    }

}
