package com.android.car.ui.widget;

import android.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import java.util.List;
import spi.u1;

/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"AppCompatCustomView"})
@TargetApi(28)
public abstract class CarUiTextView extends AppCompatTextView {
    public static final /* synthetic */ int a = 0;

    public CarUiTextView(Context context) {
        this(context, null);
    }

    public abstract void setText(@NonNull List<u1> list);

    public abstract void setText(@NonNull u1 u1Var);

    public CarUiTextView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    public CarUiTextView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
