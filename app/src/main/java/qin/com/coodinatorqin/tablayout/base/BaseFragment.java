package qin.com.coodinatorqin.tablayout.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Create by qindl
 * on 2018/11/15
 */
public abstract class BaseFragment extends Fragment {

    private Intent mIntent;
    private Unbinder mBind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        mBind = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mIntent = getActivity().getIntent();
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }

    protected abstract void initData();

    protected abstract int getLayoutId();

    public Intent getIntent(){
        return mIntent;
    }

}
