package qin.com.coodinatorqin.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import qin.com.coodinatorqin.BR;
import qin.com.coodinatorqin.MeasureCoolapse.MeasureCollpaseActivity;
import qin.com.coodinatorqin.R;
import qin.com.coodinatorqin.appbar.AppBarLayoutActivity;
import qin.com.coodinatorqin.bottomsheetbar.BottomSheetBaiduActivity;
import qin.com.coodinatorqin.collapse.collapseLayoutActivity;
import qin.com.coodinatorqin.collapsesnap.CollapseSnapActivity;
import qin.com.coodinatorqin.collapsetest.CollapseTestActivity;
import qin.com.coodinatorqin.floatbutton.FloatButtonActivity;
import qin.com.coodinatorqin.main.Viewholder.DataBindingViewHolder;
import qin.com.coodinatorqin.searchFancy.SearchFancyActivity;
import qin.com.coodinatorqin.tablayout.TabLayoutActivity;
import qin.com.coodinatorqin.zhihu.ZhiHuActivity;


/**
 * Create by qindl
 * on 2018/11/9
 */
public class MainAdapter extends RecyclerView.Adapter<DataBindingViewHolder> {

    private Context mContext;
    private Class[] activities = new Class[]{AppBarLayoutActivity.class,collapseLayoutActivity.class
    ,MeasureCollpaseActivity.class,CollapseSnapActivity.class,FloatButtonActivity.class
    ,ZhiHuActivity.class,BottomSheetBaiduActivity.class,SearchFancyActivity.class
    , TabLayoutActivity.class,CollapseTestActivity.class};
    private final LayoutInflater mLayoutInflater;
    private ViewDataBinding mViewDataBinding;

    public MainAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public DataBindingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mViewDataBinding = DataBindingUtil.inflate(mLayoutInflater, R.layout.item_main, viewGroup, false);
        return new DataBindingViewHolder(mViewDataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final DataBindingViewHolder holder, final int i) {
        holder.getBinding().setVariable(BR.name, activities[i].getSimpleName());
        holder.getBinding().executePendingBindings();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext,activities[holder.getAdapterPosition()]));
            }
        });
    }

    @Override
    public int getItemCount() {
        return activities.length;
    }
}
