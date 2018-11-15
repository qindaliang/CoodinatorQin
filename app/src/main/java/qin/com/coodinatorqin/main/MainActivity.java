package qin.com.coodinatorqin.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import qin.com.coodinatorqin.R;
import qin.com.coodinatorqin.databinding.ActivityMainBinding;
import qin.com.coodinatorqin.main.adapter.MainAdapter;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mDataBinding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        mDataBinding.recyclerview.setAdapter(new MainAdapter(this));
    }
}
