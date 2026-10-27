package com.byd.carsettings.databinding;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.ViewDataBinding;
import com.airbnb.lottie.LottieAnimationView;
import com.byd.base.view.BydSwitch;
import com.byd.dipilot.model.DriverAssistModel;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemDipilotAssistBinding extends ViewDataBinding {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @NonNull
    public final ConstraintLayout f2831a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @NonNull
    public final LottieAnimationView f2832b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @NonNull
    public final ImageView f2833c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @NonNull
    public final BydSwitch f2834d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @NonNull
    public final TextView f2835e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    @Bindable
    public DriverAssistModel f2836f;

    public ItemDipilotAssistBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, LottieAnimationView lottieAnimationView, ImageView imageView, BydSwitch bydSwitch, TextView textView) {
        super(obj, view, i);
        this.f2831a = constraintLayout;
        this.f2832b = lottieAnimationView;
        this.f2833c = imageView;
        this.f2834d = bydSwitch;
        this.f2835e = textView;
    }

    public abstract void b(@Nullable DriverAssistModel driverAssistModel);
}
