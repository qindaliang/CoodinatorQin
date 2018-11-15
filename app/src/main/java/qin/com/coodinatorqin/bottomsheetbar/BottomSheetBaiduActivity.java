package qin.com.coodinatorqin.bottomsheetbar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import qin.com.coodinatorqin.R;

public class BottomSheetBaiduActivity extends AppCompatActivity {

    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.sheet_bar)
    RelativeLayout sheetBar;
    @BindView(R.id.sheet_tv)
    TextView sheetTv;
    @BindView(R.id.bottom_sheet_iv)
    ImageView bottomSheetIv;
    @BindView(R.id.sheet)
    RelativeLayout sheet;
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinator;
    @BindView(R.id.nest)
    NestedScrollView nest;
    private BottomSheetBehavior<RelativeLayout> mBottomSheetBehavior;
    private boolean ifFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_baidu);
        ButterKnife.bind(this);

        mBottomSheetBehavior = BottomSheetBehavior.from(sheet);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                if (i == BottomSheetBehavior.STATE_EXPANDED) {
                    nest.setNestedScrollingEnabled(false);
                }
                if (i != BottomSheetBehavior.STATE_EXPANDED) {
                    nest.setNestedScrollingEnabled(true);
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {
                sheetBar.setVisibility(View.VISIBLE);
                sheetBar.setAlpha(v);
            }
        });
        sheetBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!ifFirst) {
            super.onWindowFocusChanged(hasFocus);
            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) sheet.getLayoutParams();
            params.height = coordinator.getHeight() - sheetBar.getHeight();
            sheet.setLayoutParams(params);
            ifFirst = true;
        }
    }
}
