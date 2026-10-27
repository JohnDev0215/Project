package com.byd.carsettings.databinding;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager2.widget.ViewPager2;
import com.byd.dipilot.viewmodel.AdasViewModel;
import com.byd.dipilot.widget.InterceptTouchRelativeLayout;
import com.bydux.api.common.views.window.DampingScrollView;
import com.google.android.material.tabs.TabLayout;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentDipilotBinding extends ViewDataBinding {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @NonNull
    public final TabLayout f2808a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @NonNull
    public final ViewPager2 f2809b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @NonNull
    public final DampingScrollView f2810c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @NonNull
    public final InterceptTouchRelativeLayout f2811d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @Bindable
    public AdasViewModel f2812e;

    public FragmentDipilotBinding(Object obj, View view, int i, TabLayout tabLayout, ViewPager2 viewPager2, DampingScrollView dampingScrollView, InterceptTouchRelativeLayout interceptTouchRelativeLayout) {
        super(obj, view, i);
        this.f2808a = tabLayout;
        this.f2809b = viewPager2;
        this.f2810c = dampingScrollView;
        this.f2811d = interceptTouchRelativeLayout;
    }

    @Nullable
    public AdasViewModel b() {
        return this.f2812e;
    }

    public abstract void c(@Nullable AdasViewModel adasViewModel);
}
