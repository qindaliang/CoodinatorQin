package qin.com.coodinatorqin.tablayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import qin.com.coodinatorqin.R;
import qin.com.coodinatorqin.tablayout.fragment.TestFramgment;

public class SecondTablayoutActivity extends AppCompatActivity {

    @BindView(R.id.head_iv)
    ImageView headIv;
    @BindView(R.id.one)
    LinearLayout one;
    @BindView(R.id.two)
    LinearLayout two;
    @BindView(R.id.setting)
    TextView setting;
    @BindView(R.id.head_layout)
    LinearLayout headLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.root_layout)
    CoordinatorLayout rootLayout;
    private List<Fragment> mFragments = new ArrayList<>();
    private final String[] titles = {"ONE", "TWO", "THREE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondtablayout);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        initData();
        initLayout();
        initView();
        initListener();
        findViewById(R.id.setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondTablayoutActivity.this,ThirdTabLayoutActivity.class));
            }
        });
    }

    private void initListener() {
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if (i <= -headLayout.getHeight() / 2) {
                    collapsingToolbarLayout.setTitle("微笑天使");
                } else {
                    collapsingToolbarLayout.setTitle(" ");
                }
            }
        });
    }

    private void initView() {
        toolbar.setTitle("tablayout");
        setSupportActionBar(toolbar);
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewpager));
        Glide.with(this)
                .load(R.drawable.uuu)
                .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(100,0)))
                .into(headIv);

        Glide.with(this)
                .load(R.drawable.timg)
                .apply(RequestOptions.bitmapTransform(new BlurTransformation()))
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        headLayout.setBackground(resource);
                    }
                });
    }

    private void initLayout() {
        viewpager.setAdapter(new TabLayoutActivity.ViewPagerAdapter(getSupportFragmentManager(), mFragments));
    }

    private void initData() {
        for (int i = 0; i < titles.length; i++) {
            TabLayout.Tab tab = tabs.newTab();
            tab.setText(titles[i]);
            tabs.addTab(tab);
        }
        mFragments.add(getFragment("菜单"));
        mFragments.add(getFragment("ONE"));
        mFragments.add(getFragment("TWO"));
        mFragments.add(getFragment("THREE"));

    }

    private Fragment getFragment(String name) {
        TestFramgment framgment = new TestFramgment();
        Bundle bundle = new Bundle();
        bundle.putString("test", name);
        framgment.setArguments(bundle);
        return framgment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String msg = "";
        switch (item.getItemId()) {
            case R.id.blog:
                msg = "blog";
                break;
            case R.id.weibo:
                msg = "weibo";
                break;
            case R.id.setting:
                msg = "setting";
                break;
        }
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
