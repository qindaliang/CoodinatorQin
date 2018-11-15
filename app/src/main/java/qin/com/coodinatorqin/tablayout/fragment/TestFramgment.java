package qin.com.coodinatorqin.tablayout.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import qin.com.coodinatorqin.tablayout.MyViewAdapter;
import qin.com.coodinatorqin.R;
import qin.com.coodinatorqin.tablayout.base.BaseFragment;

/**
 * Create by qindl
 * on 2018/11/15
 */
public class TestFramgment extends BaseFragment {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private List<String> datas = new ArrayList<>();

    @Override
    protected void initData() {
        String test = getArguments().getString("test");
        for (int i = 0; i < 100; i++) {
            datas.add(test + i);
        }
        initView();
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(new MyViewAdapter(getContext(), datas));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test;
    }
}
