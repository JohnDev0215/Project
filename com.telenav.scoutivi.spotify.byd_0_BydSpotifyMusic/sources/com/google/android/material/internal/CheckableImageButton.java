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
import androidx.customview.view.AbsSavedState;
import i0.u;

/* JADX INFO: loaded from: classes.dex */
public class CheckableImageButton extends androidx.appcompat.widget.k implements Checkable {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static final int[] f4579k = {R.attr.state_checked};

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f4580h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f4581i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private boolean f4582j;

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        boolean f4583h;

        static class a implements Parcelable.ClassLoaderCreator<SavedState> {
            a() {
            }

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
            public SavedState[] newArray(int i7) {
                return new SavedState[i7];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private void c(Parcel parcel) {
            this.f4583h = parcel.readInt() == 1;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i7) {
            super.writeToParcel(parcel, i7);
            parcel.writeInt(this.f4583h ? 1 : 0);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            c(parcel);
        }
    }

    class a extends i0.a {
        a() {
        }

        @Override // i0.a
        public void f(View view, AccessibilityEvent accessibilityEvent) {
            super.f(view, accessibilityEvent);
            accessibilityEvent.setChecked(CheckableImageButton.this.isChecked());
        }

        @Override // i0.a
        public void g(View view, j0.c cVar) {
            super.g(view, cVar);
            cVar.N(CheckableImageButton.this.a());
            cVar.O(CheckableImageButton.this.isChecked());
        }
    }

    public CheckableImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, e.a.imageButtonStyle);
    }

    public boolean a() {
        return this.f4581i;
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.f4580h;
    }

    @Override // android.widget.ImageView, android.view.View
    public int[] onCreateDrawableState(int i7) {
        if (!this.f4580h) {
            return super.onCreateDrawableState(i7);
        }
        int[] iArr = f4579k;
        return ImageButton.mergeDrawableStates(super.onCreateDrawableState(i7 + iArr.length), iArr);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.b());
        setChecked(savedState.f4583h);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f4583h = this.f4580h;
        return savedState;
    }

    public void setCheckable(boolean z6) {
        if (this.f4581i != z6) {
            this.f4581i = z6;
            sendAccessibilityEvent(0);
        }
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z6) {
        if (!this.f4581i || this.f4580h == z6) {
            return;
        }
        this.f4580h = z6;
        refreshDrawableState();
        sendAccessibilityEvent(2048);
    }

    public void setPressable(boolean z6) {
        this.f4582j = z6;
    }

    @Override // android.view.View
    public void setPressed(boolean z6) {
        if (this.f4582j) {
            super.setPressed(z6);
        }
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.f4580h);
    }

    public CheckableImageButton(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f4581i = true;
        this.f4582j = true;
        u.k0(this, new a());
    }
}
