package com.google.android.material.internal;

import android.R;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Checkable;
import android.widget.ImageButton;
import androidx.appcompat.R$attr;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.customview.view.AbsSavedState;
import o0.t;
import p0.c;

/* JADX INFO: loaded from: classes2.dex */
public class CheckableImageButton extends AppCompatImageButton implements Checkable {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int[] f3926f = {R.attr.state_checked};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f3927g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f3928h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f3929i;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f3930f;

        public static class a implements Parcelable.ClassLoaderCreator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i8) {
                return new SavedState[i8];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public final void g(Parcel parcel) {
            this.f3930f = parcel.readInt() == 1;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i8) {
            super.writeToParcel(parcel, i8);
            parcel.writeInt(this.f3930f ? 1 : 0);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            g(parcel);
        }
    }

    public class a extends o0.a {
        public a() {
        }

        @Override // o0.a
        public void f(View view, AccessibilityEvent accessibilityEvent) {
            super.f(view, accessibilityEvent);
            accessibilityEvent.setChecked(CheckableImageButton.this.isChecked());
        }

        @Override // o0.a
        public void g(View view, c cVar) {
            super.g(view, cVar);
            cVar.a0(CheckableImageButton.this.a());
            cVar.b0(CheckableImageButton.this.isChecked());
        }
    }

    public CheckableImageButton(Context context) {
        this(context, null);
    }

    public boolean a() {
        return this.f3928h;
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.f3927g;
    }

    @Override // android.widget.ImageView, android.view.View
    public int[] onCreateDrawableState(int i8) {
        if (!this.f3927g) {
            return super.onCreateDrawableState(i8);
        }
        int[] iArr = f3926f;
        return ImageButton.mergeDrawableStates(super.onCreateDrawableState(i8 + iArr.length), iArr);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.f());
        setChecked(savedState.f3930f);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f3930f = this.f3927g;
        return savedState;
    }

    public void setCheckable(boolean z7) {
        if (this.f3928h != z7) {
            this.f3928h = z7;
            sendAccessibilityEvent(0);
        }
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z7) {
        if (!this.f3928h || this.f3927g == z7) {
            return;
        }
        this.f3927g = z7;
        refreshDrawableState();
        sendAccessibilityEvent(2048);
    }

    public void setPressable(boolean z7) {
        this.f3929i = z7;
    }

    @Override // android.view.View
    public void setPressed(boolean z7) {
        if (this.f3929i) {
            super.setPressed(z7);
        }
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.f3927g);
    }

    public CheckableImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.imageButtonStyle);
    }

    public CheckableImageButton(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f3928h = true;
        this.f3929i = true;
        t.l0(this, new a());
    }
}
