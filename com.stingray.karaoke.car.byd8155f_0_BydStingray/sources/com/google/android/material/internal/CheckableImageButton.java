package com.google.android.material.internal;

import F1.H;
import Q4.d;
import U4.a;
import android.R;
import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import r.C2262u;

/* JADX INFO: loaded from: classes.dex */
public class CheckableImageButton extends C2262u implements Checkable {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final int[] f12846r = {R.attr.state_checked};
    public boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f12847p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f12848q;

    public CheckableImageButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, com.stingray.drivemodedetector.R.attr.imageButtonStyle);
        this.f12847p = true;
        this.f12848q = true;
        H.g(this, new d(this, 1));
    }

    @Override // android.widget.Checkable
    public final boolean isChecked() {
        return this.o;
    }

    @Override // android.widget.ImageView, android.view.View
    public final int[] onCreateDrawableState(int i7) {
        return this.o ? View.mergeDrawableStates(super.onCreateDrawableState(i7 + 1), f12846r) : super.onCreateDrawableState(i7);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof a)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        a aVar = (a) parcelable;
        super.onRestoreInstanceState(aVar.l);
        setChecked(aVar.f9016n);
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        a aVar = new a(super.onSaveInstanceState());
        aVar.f9016n = this.o;
        return aVar;
    }

    public void setCheckable(boolean z6) {
        if (this.f12847p != z6) {
            this.f12847p = z6;
            sendAccessibilityEvent(0);
        }
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z6) {
        if (!this.f12847p || this.o == z6) {
            return;
        }
        this.o = z6;
        refreshDrawableState();
        sendAccessibilityEvent(2048);
    }

    public void setPressable(boolean z6) {
        this.f12848q = z6;
    }

    @Override // android.view.View
    public void setPressed(boolean z6) {
        if (this.f12848q) {
            super.setPressed(z6);
        }
    }

    @Override // android.widget.Checkable
    public final void toggle() {
        setChecked(!this.o);
    }
}
