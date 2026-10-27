package com.byd.carsettings.databinding;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import com.byd.base.view.BydSwitch;
import com.byd.dipilot.viewmodel.BaseAdasViewModel;
import com.bydux.api.common.views.window.DampingScrollView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentParkAssistBinding extends ViewDataBinding {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @NonNull
    public final ConstraintLayout f2825a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @NonNull
    public final ConstraintLayout f2826b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @NonNull
    public final ConstraintLayout f2827c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @NonNull
    public final LottieAnimationView f2828d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @NonNull
    public final DampingScrollView f2829e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    @NonNull
    public final ConstraintLayout f2830f;

    @NonNull
    public final RecyclerView g;

    @NonNull
    public final BydSwitch h;

    @NonNull
    public final TextView i;

    @NonNull
    public final TextView j;

    @NonNull
    public final TextView k;

    @Bindable
    public BaseAdasViewModel l;

    public FragmentParkAssistBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, LottieAnimationView lottieAnimationView, DampingScrollView dampingScrollView, ConstraintLayout constraintLayout4, RecyclerView recyclerView, BydSwitch bydSwitch, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.f2825a = constraintLayout;
        this.f2826b = constraintLayout2;
        this.f2827c = constraintLayout3;
        this.f2828d = lottieAnimationView;
        this.f2829e = dampingScrollView;
        this.f2830f = constraintLayout4;
        this.g = recyclerView;
        this.h = bydSwitch;
        this.i = textView;
        this.j = textView2;
        this.k = textView3;
    }
}
