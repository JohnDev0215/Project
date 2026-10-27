package com.byd.carsettings.databinding;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.ViewDataBinding;
import com.byd.dipilot.viewmodel.NoConfigDialogModel;
import com.byd.dipilot.widget.CountDownButton;
import com.byd.dipilot.widget.MaxHeightLinearLayout;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogAdasCommonBinding extends ViewDataBinding {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @NonNull
    public final Button f2796a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @NonNull
    public final CountDownButton f2797b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @NonNull
    public final LinearLayout f2798c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @NonNull
    public final LinearLayout f2799d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @NonNull
    public final MaxHeightLinearLayout f2800e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    @NonNull
    public final TextView f2801f;

    @NonNull
    public final TextView g;

    @NonNull
    public final TextView h;

    @NonNull
    public final View i;

    @Bindable
    public NoConfigDialogModel j;

    public DialogAdasCommonBinding(Object obj, View view, int i, Button button, CountDownButton countDownButton, LinearLayout linearLayout, LinearLayout linearLayout2, MaxHeightLinearLayout maxHeightLinearLayout, TextView textView, TextView textView2, TextView textView3, View view2) {
        super(obj, view, i);
        this.f2796a = button;
        this.f2797b = countDownButton;
        this.f2798c = linearLayout;
        this.f2799d = linearLayout2;
        this.f2800e = maxHeightLinearLayout;
        this.f2801f = textView;
        this.g = textView2;
        this.h = textView3;
        this.i = view2;
    }

    @Nullable
    public NoConfigDialogModel b() {
        return this.j;
    }

    public abstract void c(@Nullable NoConfigDialogModel noConfigDialogModel);
}
