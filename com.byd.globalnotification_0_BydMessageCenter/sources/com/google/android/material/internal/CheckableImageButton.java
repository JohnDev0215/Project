package com.google.android.material.internal;

import a.b.p.l;
import a.h.k.q;
import android.R;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.ImageButton;

/* JADX INFO: loaded from: classes.dex */
public class CheckableImageButton extends l implements Checkable {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int[] f2454f = {R.attr.state_checked};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f2455c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f2456d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f2457e;

    public static class a extends a.j.a.a {
        public static final Parcelable.Creator<a> CREATOR = new C0057a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f2458c;

        /* JADX INFO: renamed from: com.google.android.material.internal.CheckableImageButton$a$a, reason: collision with other inner class name */
        public static class C0057a implements Parcelable.ClassLoaderCreator<a> {
            @Override // android.os.Parcelable.Creator
            public Object createFromParcel(Parcel parcel) {
                return new a(parcel, null);
            }

            @Override // android.os.Parcelable.Creator
            public Object[] newArray(int i) {
                return new a[i];
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            public a createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new a(parcel, classLoader);
            }
        }

        public a(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f2458c = parcel.readInt() == 1;
        }

        public a(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // a.j.a.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.f892a, i);
            parcel.writeInt(this.f2458c ? 1 : 0);
        }
    }

    public CheckableImageButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, a.b.a.imageButtonStyle);
        this.f2456d = true;
        this.f2457e = true;
        q.U(this, new b.d.a.a.z.a(this));
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.f2455c;
    }

    @Override // android.widget.ImageView, android.view.View
    public int[] onCreateDrawableState(int i) {
        return this.f2455c ? ImageButton.mergeDrawableStates(super.onCreateDrawableState(i + f2454f.length), f2454f) : super.onCreateDrawableState(i);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof a)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        a aVar = (a) parcelable;
        super.onRestoreInstanceState(aVar.f892a);
        setChecked(aVar.f2458c);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        a aVar = new a(super.onSaveInstanceState());
        aVar.f2458c = this.f2455c;
        return aVar;
    }

    public void setCheckable(boolean z) {
        if (this.f2456d != z) {
            this.f2456d = z;
            sendAccessibilityEvent(0);
        }
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        if (!this.f2456d || this.f2455c == z) {
            return;
        }
        this.f2455c = z;
        refreshDrawableState();
        sendAccessibilityEvent(2048);
    }

    public void setPressable(boolean z) {
        this.f2457e = z;
    }

    @Override // android.view.View
    public void setPressed(boolean z) {
        if (this.f2457e) {
            super.setPressed(z);
        }
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.f2455c);
    }
}
