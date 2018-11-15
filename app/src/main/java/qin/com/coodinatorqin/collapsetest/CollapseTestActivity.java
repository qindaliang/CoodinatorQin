package qin.com.coodinatorqin.collapsetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import qin.com.coodinatorqin.R;

public class CollapseTestActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapse_test);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
    }
}
