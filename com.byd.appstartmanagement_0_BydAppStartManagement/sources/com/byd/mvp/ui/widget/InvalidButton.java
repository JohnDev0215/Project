package com.byd.mvp.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class InvalidButton extends Button {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f727b;

    public InvalidButton(@NonNull Context context) {
        super(context);
        this.f727b = false;
    }

    public void setInvalid(boolean z) {
        this.f727b = z;
        setAlpha(z ? 0.3f : 1.0f);
        setEnabled(!z);
    }

    public InvalidButton(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f727b = false;
    }

    public InvalidButton(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f727b = false;
    }
}
