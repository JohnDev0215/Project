package com.byd.mvp.ui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

/* JADX INFO: loaded from: classes2.dex */
public class VolumeTintImageView extends TintImageView {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int[] f2942g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Drawable f2943h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2944i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f2945j;

    public VolumeTintImageView(Context context) {
        super(context);
        this.f2944i = 0;
        this.f2945j = -2;
    }

    @Override // com.byd.mvp.ui.widget.TintImageView
    public void d(Context context) {
        super.d(context);
        this.f2943h = getBackground();
        setBackgroundColor(0);
    }

    public int getCurState() {
        return this.f2945j;
    }

    public int getCurVolume() {
        return this.f2944i;
    }

    public void setVolumeIds(int[] iArr) {
        this.f2942g = iArr;
    }

    public VolumeTintImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2944i = 0;
        this.f2945j = -2;
    }

    public VolumeTintImageView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f2944i = 0;
        this.f2945j = -2;
    }
}
