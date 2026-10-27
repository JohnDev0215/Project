package com.byd.mvp.ui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VolumeTintImageView extends TintImageView {
    public int[] e;
    public Drawable f;
    public int g;
    public int h;

    public VolumeTintImageView(@NonNull Context context) {
        super(context);
        this.g = 0;
        this.h = -2;
        b();
    }

    public final void b() {
        this.f = getBackground();
        setBackgroundColor(0);
    }

    public int getCurState() {
        return this.h;
    }

    public int getCurVolume() {
        return this.g;
    }

    public void setVolumeIds(int[] iArr) {
        this.e = iArr;
    }

    public VolumeTintImageView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = 0;
        this.h = -2;
        b();
    }

    public VolumeTintImageView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = 0;
        this.h = -2;
        b();
    }
}
