package qin.com.coodinatorqin.tablayout;

import android.databinding.Bindable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import qin.com.coodinatorqin.R;
import qin.com.coodinatorqin.tablayout.fragment.TestFramgment;

public class TabLayoutActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.appbarlayout)
    AppBarLayout appbarlayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private List<Fragment> mFragments = new ArrayList<>();
    private final String[] titles = {"ONE", "TWO", "THREE"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);
        ButterKnife.bind(this);
        initData();
        initLayout();
        initView();
    }

    private void initView() {
        toolbar.setTitle("tablayout");
        setSupportActionBar(toolbar);
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        tablayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewpager));
    }

    private void initLayout() {
      viewpager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), mFragments));
    }

    private void initData() {
        for(int i = 0 ; i < titles.length ; i++){
            TabLayout.Tab tab = tablayout.newTab();
            tab.setText(titles[i]);
            tablayout.addTab(tab);
        }
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
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.blog:
                Toast.makeText(this,"blog",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.weibo:
                Toast.makeText(this,"weibo",Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                Toast.makeText(this,"setting",Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;
        private final String[] titles = {"ONE", "TWO", "THREE"};

        public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
