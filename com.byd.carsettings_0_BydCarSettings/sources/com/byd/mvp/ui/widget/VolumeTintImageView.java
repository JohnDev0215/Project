package com.byd.mvp.ui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VolumeTintImageView extends TintImageView {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int[] f3821d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Drawable f3822e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3823f;
    public int g;

    public VolumeTintImageView(@NonNull Context context) {
        super(context);
        this.f3823f = 0;
        this.g = -2;
        f();
    }

    public final void f() {
        this.f3822e = getBackground();
        setBackgroundColor(0);
    }

    public int getCurState() {
        return this.g;
    }

    public int getCurVolume() {
        return this.f3823f;
    }

    public void setVolumeIds(int[] iArr) {
        this.f3821d = iArr;
    }

    public VolumeTintImageView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3823f = 0;
        this.g = -2;
        f();
    }

    public VolumeTintImageView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3823f = 0;
        this.g = -2;
        f();
    }
}
