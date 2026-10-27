package a.f.b;

import a.f.b.d;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;

/* JADX INFO: loaded from: classes.dex */
public class e extends ViewGroup {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d f692a;

    public static class a extends ConstraintLayout.a {
        public float A0;
        public float B0;
        public float C0;
        public float D0;
        public float E0;
        public float s0;
        public boolean t0;
        public float u0;
        public float v0;
        public float w0;
        public float x0;
        public float y0;
        public float z0;

        public a(int i, int i2) {
            super(i, i2);
            this.s0 = 1.0f;
            this.t0 = false;
            this.u0 = 0.0f;
            this.v0 = 0.0f;
            this.w0 = 0.0f;
            this.x0 = 0.0f;
            this.y0 = 1.0f;
            this.z0 = 1.0f;
            this.A0 = 0.0f;
            this.B0 = 0.0f;
            this.C0 = 0.0f;
            this.D0 = 0.0f;
            this.E0 = 0.0f;
        }

        public a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.s0 = 1.0f;
            this.t0 = false;
            this.u0 = 0.0f;
            this.v0 = 0.0f;
            this.w0 = 0.0f;
            this.x0 = 0.0f;
            this.y0 = 1.0f;
            this.z0 = 1.0f;
            this.A0 = 0.0f;
            this.B0 = 0.0f;
            this.C0 = 0.0f;
            this.D0 = 0.0f;
            this.E0 = 0.0f;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, i.ConstraintSet);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == i.ConstraintSet_android_alpha) {
                    this.s0 = typedArrayObtainStyledAttributes.getFloat(index, this.s0);
                } else if (index == i.ConstraintSet_android_elevation) {
                    this.u0 = typedArrayObtainStyledAttributes.getFloat(index, this.u0);
                    this.t0 = true;
                } else if (index == i.ConstraintSet_android_rotationX) {
                    this.w0 = typedArrayObtainStyledAttributes.getFloat(index, this.w0);
                } else if (index == i.ConstraintSet_android_rotationY) {
                    this.x0 = typedArrayObtainStyledAttributes.getFloat(index, this.x0);
                } else if (index == i.ConstraintSet_android_rotation) {
                    this.v0 = typedArrayObtainStyledAttributes.getFloat(index, this.v0);
                } else if (index == i.ConstraintSet_android_scaleX) {
                    this.y0 = typedArrayObtainStyledAttributes.getFloat(index, this.y0);
                } else if (index == i.ConstraintSet_android_scaleY) {
                    this.z0 = typedArrayObtainStyledAttributes.getFloat(index, this.z0);
                } else if (index == i.ConstraintSet_android_transformPivotX) {
                    this.A0 = typedArrayObtainStyledAttributes.getFloat(index, this.A0);
                } else if (index == i.ConstraintSet_android_transformPivotY) {
                    this.B0 = typedArrayObtainStyledAttributes.getFloat(index, this.B0);
                } else if (index == i.ConstraintSet_android_translationX) {
                    this.C0 = typedArrayObtainStyledAttributes.getFloat(index, this.C0);
                } else if (index == i.ConstraintSet_android_translationY) {
                    this.D0 = typedArrayObtainStyledAttributes.getFloat(index, this.D0);
                } else if (index == i.ConstraintSet_android_translationZ) {
                    this.E0 = typedArrayObtainStyledAttributes.getFloat(index, this.E0);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new a(-2, -2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new a(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new ConstraintLayout.a(layoutParams);
    }

    public d getConstraintSet() {
        if (this.f692a == null) {
            this.f692a = new d();
        }
        d dVar = this.f692a;
        if (dVar == null) {
            throw null;
        }
        int childCount = getChildCount();
        dVar.f656c.clear();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            a aVar = (a) childAt.getLayoutParams();
            int id = childAt.getId();
            if (dVar.f655b && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!dVar.f656c.containsKey(Integer.valueOf(id))) {
                dVar.f656c.put(Integer.valueOf(id), new d.a());
            }
            d.a aVar2 = dVar.f656c.get(Integer.valueOf(id));
            if (aVar2 != null) {
                if (childAt instanceof b) {
                    b bVar = (b) childAt;
                    aVar2.c(id, aVar);
                    if (bVar instanceof Barrier) {
                        d.b bVar2 = aVar2.f660d;
                        bVar2.i0 = 1;
                        Barrier barrier = (Barrier) bVar;
                        bVar2.g0 = barrier.getType();
                        aVar2.f660d.j0 = barrier.getReferencedIds();
                        aVar2.f660d.h0 = barrier.getMargin();
                    }
                }
                aVar2.c(id, aVar);
            }
        }
        return this.f692a;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }
}
