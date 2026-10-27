package com.byd.carsettings.databinding;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.dipilot.viewmodel.BaseAdasViewModel;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentDriverAssistBinding extends ViewDataBinding {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @NonNull
    public final RecyclerView f2815a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Bindable
    public BaseAdasViewModel f2816b;

    public FragmentDriverAssistBinding(Object obj, View view, int i, RecyclerView recyclerView) {
        super(obj, view, i);
        this.f2815a = recyclerView;
    }

    @Nullable
    public BaseAdasViewModel b() {
        return this.f2816b;
    }

    public abstract void c(@Nullable BaseAdasViewModel baseAdasViewModel);
}
