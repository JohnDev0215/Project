package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.lang.ref.WeakReference;
import n.AbstractC1850a;
import r.D0;

/* JADX INFO: loaded from: classes.dex */
public final class ViewStubCompat extends View {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f11390m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public WeakReference f11391n;
    public LayoutInflater o;

    public ViewStubCompat(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.l = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC1850a.f19368u, 0, 0);
        this.f11390m = typedArrayObtainStyledAttributes.getResourceId(2, -1);
        this.l = typedArrayObtainStyledAttributes.getResourceId(1, 0);
        setId(typedArrayObtainStyledAttributes.getResourceId(0, -1));
        typedArrayObtainStyledAttributes.recycle();
        setVisibility(8);
        setWillNotDraw(true);
    }

    @Override // android.view.View
    public final void dispatchDraw(Canvas canvas) {
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
    }

    public int getInflatedId() {
        return this.f11390m;
    }

    public LayoutInflater getLayoutInflater() {
        return this.o;
    }

    public int getLayoutResource() {
        return this.l;
    }

    @Override // android.view.View
    public final void onMeasure(int i7, int i10) {
        setMeasuredDimension(0, 0);
    }

    public void setInflatedId(int i7) {
        this.f11390m = i7;
    }

    public void setLayoutInflater(LayoutInflater layoutInflater) {
        this.o = layoutInflater;
    }

    public void setLayoutResource(int i7) {
        this.l = i7;
    }

    public void setOnInflateListener(D0 d02) {
    }

    @Override // android.view.View
    public void setVisibility(int i7) {
        WeakReference weakReference = this.f11391n;
        if (weakReference != null) {
            View view = (View) weakReference.get();
            if (view == null) {
                throw new IllegalStateException("setVisibility called on un-referenced view");
            }
            view.setVisibility(i7);
            return;
        }
        super.setVisibility(i7);
        if (i7 == 0 || i7 == 4) {
            ViewParent parent = getParent();
            if (!(parent instanceof ViewGroup)) {
                throw new IllegalStateException("ViewStub must have a non-null ViewGroup viewParent");
            }
            if (this.l == 0) {
                throw new IllegalArgumentException("ViewStub must have a valid layoutResource");
            }
            ViewGroup viewGroup = (ViewGroup) parent;
            LayoutInflater layoutInflaterFrom = this.o;
            if (layoutInflaterFrom == null) {
                layoutInflaterFrom = LayoutInflater.from(getContext());
            }
            View viewInflate = layoutInflaterFrom.inflate(this.l, viewGroup, false);
            int i10 = this.f11390m;
            if (i10 != -1) {
                viewInflate.setId(i10);
            }
            int iIndexOfChild = viewGroup.indexOfChild(this);
            viewGroup.removeViewInLayout(this);
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            if (layoutParams != null) {
                viewGroup.addView(viewInflate, iIndexOfChild, layoutParams);
            } else {
                viewGroup.addView(viewInflate, iIndexOfChild);
            }
            this.f11391n = new WeakReference(viewInflate);
        }
    }
}
