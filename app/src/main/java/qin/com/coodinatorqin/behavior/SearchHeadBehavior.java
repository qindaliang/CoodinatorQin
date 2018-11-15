package qin.com.coodinatorqin.behavior;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

import qin.com.coodinatorqin.R;

/**
 * Create by qindl
 * on 2018/11/15
 */
public class SearchHeadBehavior extends CoordinatorLayout.Behavior<View> {
    private String TAG = "TAG";
    private WeakReference<View> mDependencyView;

    public SearchHeadBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        mDependencyView = new WeakReference<>(dependency);
        return dependency.getId() == R.id.image;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        //getTranslationY向上为负
        float percent = Math.abs(getDependencyView().getTranslationY()) / (getDependencyView().getHeight() - 150);
        //初始位置
        View dependencyView = getDependencyView();
        int maxHeight = dependencyView.getHeight() - 150;

        //颜色变化,view绘制首先绘制背景
        ArgbEvaluator argbEvaluator = new ArgbEvaluator();

        if (getDependencyView().getTranslationY() > 0) {

            child.setTranslationY(maxHeight + maxHeight * (percent));
            child.setAlpha(1-percent);

        } else {
            child.setTranslationY(maxHeight - maxHeight * (percent));
            child.setBackgroundColor((Integer) argbEvaluator.evaluate(percent
                    , getDependencyView().getResources().getColor(R.color.start)
                    , getDependencyView().getResources().getColor(R.color.end)));

            //MarginLayoutParams设置
            CoordinatorLayout.MarginLayoutParams params = (CoordinatorLayout.MarginLayoutParams) child.getLayoutParams();
            params.leftMargin = (int) (60 * (1 - percent));
            params.rightMargin = (int) (60 * (1 - percent));
            params.setMargins((int) (120 * (1 - percent)), 15, (int) (120 * (1 - percent)), 0);
            child.setLayoutParams(params);

            Log.i(TAG, "percent: " + percent);
        }
        return true;
    }

    public View getDependencyView() {
        return mDependencyView.get();
    }
}
