package com.byd.carsettings.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import b.b.m.e.g;
import com.byd.base.view.segmentedbutton.SegmentedButtonGroup;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.PermissionPagerAdapter;
import com.byd.mvp.mvp.DialogActivity;
import com.byd.mvp.ui.widget.BydViewPager;

/* JADX INFO: loaded from: classes2.dex */
public class PermissionActivity extends DialogActivity implements ViewPager.OnPageChangeListener {
    public FrameLayout u;
    public SegmentedButtonGroup v;
    public BydViewPager w;
    public PermissionPagerAdapter x;
    public volatile int y = 0;

    public class a implements SegmentedButtonGroup.f {
        public a() {
        }

        @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.f
        public void onPositionChanged(View view, View view2, int i) {
            if (i < 0 || i >= PermissionActivity.this.x.getCount()) {
                return;
            }
            PermissionActivity.this.w.setCurrentItem(i);
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PermissionActivity.this.finish();
        }
    }

    @Override // com.byd.mvp.mvp.XActivity
    public void B(boolean z) {
        super.B(z);
        this.u.addView(getLayoutInflater().inflate(R.layout.title_permission_dialog, (ViewGroup) null));
        S();
    }

    @Override // com.byd.mvp.mvp.XActivity
    public void C(boolean z) {
        super.C(z);
        this.u.addView(getLayoutInflater().inflate(R.layout.title_permission_dialog_r, (ViewGroup) null));
        S();
    }

    @Override // com.byd.mvp.mvp.XActivity
    public void D(boolean z) {
        super.D(z);
        this.u.addView(getLayoutInflater().inflate(R.layout.title_permission_dialog, (ViewGroup) null));
        I(this.u, false);
        S();
    }

    @Override // com.byd.mvp.mvp.DialogActivity
    public int J(Context context) {
        return (int) getResources().getDimension(R.dimen.dp_560);
    }

    @Override // com.byd.mvp.mvp.DialogActivity
    public int K() {
        return R.layout.activity_permission;
    }

    @Override // com.byd.mvp.mvp.DialogActivity
    public int M(Context context) {
        int i = getResources().getConfiguration().orientation;
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        if (i == 2) {
            attributes.width = (int) getResources().getDimension(R.dimen.dp_800);
        } else {
            attributes.width = (int) getResources().getDimension(R.dimen.dp_612);
        }
        return attributes.width;
    }

    @Override // com.byd.mvp.mvp.DialogActivity
    public void N(Context context) {
        this.u = (FrameLayout) findViewById(R.id.fl_title__permission_dialog);
        this.w = (BydViewPager) findViewById(R.id.vp_permissions_apps_permission);
    }

    public final void S() {
        SegmentedButtonGroup segmentedButtonGroup = (SegmentedButtonGroup) findViewById(R.id.srg_permission_type_permission);
        this.v = segmentedButtonGroup;
        segmentedButtonGroup.setOnPositionChangedListener(new a());
        findViewById(R.id.iv_back_permission).setOnClickListener(new b());
        this.v.setPosition(this.y);
        this.v.setClickTime(50);
    }

    @Override // com.byd.mvp.mvp.XActivity
    public void k(Context context) {
        b.b.m.c.a.a(this.f3705c, "---initData---curIndex = " + this.y);
        this.w.setCurrentItem(this.y);
    }

    @Override // b.b.m.e.h
    public g newP() {
        return null;
    }

    @Override // com.byd.mvp.mvp.DialogActivity, com.byd.mvp.mvp.XActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        b.b.m.c.a.a(this.f3705c, "---onDestroy---curIndex = " + this.y);
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f2, int i2) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        b.b.m.c.a.a(this.f3705c, "----onPageSelected----position = " + i);
        this.v.setPosition(i);
        this.x.d(i);
        this.y = i;
    }

    @Override // com.byd.mvp.mvp.XActivity, android.app.Activity
    public void onRestoreInstanceState(@NonNull Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.y = bundle.getInt("cur_index_permission");
        b.b.m.c.a.a(this.f3705c, "---onRestoreInstanceState---curIndex = " + this.y);
        this.v.setPosition(this.y);
        this.w.setCurrentItem(this.y);
    }

    @Override // com.byd.mvp.mvp.XActivity, com.bydux.api.common.views.window.BydActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        b.b.m.c.a.a(this.f3705c, "---onResume---curIndex = " + this.y);
        PermissionPagerAdapter permissionPagerAdapter = new PermissionPagerAdapter();
        this.x = permissionPagerAdapter;
        permissionPagerAdapter.f(this);
        this.w.setAdapter(this.x);
        this.w.setScrollEnable(false);
        this.w.addOnPageChangeListener(this);
    }

    @Override // com.byd.mvp.mvp.XActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("cur_index_permission", this.y);
        b.b.m.c.a.a(this.f3705c, "---onSaveInstanceState---curIndex = " + this.y);
    }
}
