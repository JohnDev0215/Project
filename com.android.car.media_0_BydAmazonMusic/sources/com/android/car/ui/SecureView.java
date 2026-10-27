package com.android.car.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
@TargetApi(28)
public class SecureView extends View {
    public boolean a;

    public SecureView(Context context) {
        super(context);
        this.a = true;
    }

    @Override // android.view.View
    public final boolean onFilterTouchEventForSecurity(MotionEvent motionEvent) {
        return !this.a ? super.onFilterTouchEventForSecurity(motionEvent) : (motionEvent.getFlags() & 3) == 0 && super.onFilterTouchEventForSecurity(motionEvent);
    }

    public void setSecure(boolean z) {
        this.a = z;
    }

    public SecureView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = true;
    }

    public SecureView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = true;
    }

    public SecureView(Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.a = true;
    }
}
