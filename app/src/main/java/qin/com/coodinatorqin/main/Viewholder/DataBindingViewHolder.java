package qin.com.coodinatorqin.main.Viewholder;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

/**
 * Create by qindl
 * on 2018/11/9
 */
public class DataBindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private T mBinding;

    public DataBindingViewHolder(@NonNull T binding) {
        super(binding.getRoot());
        mBinding = binding;
    }
    public T getBinding(){
        return mBinding;
    }
}
