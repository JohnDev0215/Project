package com.google.android.material.internal;

import android.R;
import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import p.cf1;
import p.ds;
import p.z8;
import p.zt2;

/* JADX INFO: loaded from: classes.dex */
public class CheckableImageButton extends z8 implements Checkable {
    public static final int[] o = {R.attr.state_checked};
    public boolean l;
    public boolean m;
    public boolean n;

    public CheckableImageButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, com.spotify.music.R.attr.imageButtonStyle);
        this.m = true;
        this.n = true;
        zt2.k(this, new cf1(this, 1));
    }

    @Override // android.widget.Checkable
    public final boolean isChecked() {
        return this.l;
    }

    @Override // android.widget.ImageView, android.view.View
    public final int[] onCreateDrawableState(int i) {
        return this.l ? View.mergeDrawableStates(super.onCreateDrawableState(i + 1), o) : super.onCreateDrawableState(i);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof ds)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        ds dsVar = (ds) parcelable;
        super.onRestoreInstanceState(dsVar.c);
        setChecked(dsVar.k);
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        ds dsVar = new ds(super.onSaveInstanceState());
        dsVar.k = this.l;
        return dsVar;
    }

    public void setCheckable(boolean z) {
        if (this.m != z) {
            this.m = z;
            sendAccessibilityEvent(0);
        }
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        if (!this.m || this.l == z) {
            return;
        }
        this.l = z;
        refreshDrawableState();
        sendAccessibilityEvent(2048);
    }

    public void setPressable(boolean z) {
        this.n = z;
    }

    @Override // android.view.View
    public void setPressed(boolean z) {
        if (this.n) {
            super.setPressed(z);
        }
    }

    @Override // android.widget.Checkable
    public final void toggle() {
        setChecked(!this.l);
    }
}
