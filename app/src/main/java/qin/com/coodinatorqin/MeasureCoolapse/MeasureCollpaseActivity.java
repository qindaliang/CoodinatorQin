package qin.com.coodinatorqin.MeasureCoolapse;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import qin.com.coodinatorqin.R;

public class MeasureCollpaseActivity extends AppCompatActivity {

    @BindView(R.id.search_title)
    TextView searchTitle;
    @BindView(R.id.ll_contain)
    LinearLayout llContain;
    @BindView(R.id.headview)
    TextView headview;
    @BindView(R.id.view)
    TextView view;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private int mHeadviewHeight;
    private int mAppBarHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure_collpase);
        ButterKnife.bind(this);
        headview.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                headview.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mHeadviewHeight = headview.getHeight();
                mAppBarHeight = appBar.getHeight();
                Log.i("TAG", "onGlobalLayout: "+mHeadviewHeight);
                Log.i("TAG", "onGlobalLayout: "+mAppBarHeight);
            }
        });
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                ViewGroup.LayoutParams params = llContain.getLayoutParams();
                int scal = Math.abs(i/1200);
                params.height = scal*100;
                llContain.setLayoutParams(params);
                Log.i("TAG", "onOffsetChanged: "+i);
            }
        });
    }
}
