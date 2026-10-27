package com.google.android.material.internal;

import a.g.k.t;
import android.R;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Checkable;
import android.widget.ImageButton;
import androidx.appcompat.widget.l;

/* JADX INFO: loaded from: classes.dex */
public class CheckableImageButton extends l implements Checkable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final int[] f3123d = {R.attr.state_checked};
    private boolean e;
    private boolean f;
    private boolean g;

    class a extends a.g.k.a {
        a() {
        }

        @Override // a.g.k.a
        public void f(View view, AccessibilityEvent accessibilityEvent) {
            super.f(view, accessibilityEvent);
            accessibilityEvent.setChecked(CheckableImageButton.this.isChecked());
        }

        @Override // a.g.k.a
        public void g(View view, a.g.k.c0.c cVar) {
            super.g(view, cVar);
            cVar.V(CheckableImageButton.this.a());
            cVar.W(CheckableImageButton.this.isChecked());
        }
    }

    static class b extends a.i.a.a {
        public static final Parcelable.Creator<b> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f3125d;

        static class a implements Parcelable.ClassLoaderCreator<b> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public b createFromParcel(Parcel parcel) {
                return new b(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public b createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new b(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public b[] newArray(int i) {
                return new b[i];
            }
        }

        public b(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            v(parcel);
        }

        public b(Parcelable parcelable) {
            super(parcelable);
        }

        private void v(Parcel parcel) {
            this.f3125d = parcel.readInt() == 1;
        }

        @Override // a.i.a.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f3125d ? 1 : 0);
        }
    }

    public CheckableImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.a.a.B);
    }

    public CheckableImageButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f = true;
        this.g = true;
        t.h0(this, new a());
    }

    public boolean a() {
        return this.f;
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.e;
    }

    @Override // android.widget.ImageView, android.view.View
    public int[] onCreateDrawableState(int i) {
        if (!this.e) {
            return super.onCreateDrawableState(i);
        }
        int[] iArr = f3123d;
        return ImageButton.mergeDrawableStates(super.onCreateDrawableState(i + iArr.length), iArr);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof b)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        b bVar = (b) parcelable;
        super.onRestoreInstanceState(bVar.u());
        setChecked(bVar.f3125d);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        b bVar = new b(super.onSaveInstanceState());
        bVar.f3125d = this.e;
        return bVar;
    }

    public void setCheckable(boolean z) {
        if (this.f != z) {
            this.f = z;
            sendAccessibilityEvent(0);
        }
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        if (!this.f || this.e == z) {
            return;
        }
        this.e = z;
        refreshDrawableState();
        sendAccessibilityEvent(2048);
    }

    public void setPressable(boolean z) {
        this.g = z;
    }

    @Override // android.view.View
    public void setPressed(boolean z) {
        if (this.g) {
            super.setPressed(z);
        }
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.e);
    }
}
