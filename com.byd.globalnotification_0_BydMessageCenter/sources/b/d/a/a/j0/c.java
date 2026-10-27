package b.d.a.a.j0;

import a.f.b.d;
import a.h.k.q;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import b.d.a.a.e0.g;
import b.d.a.a.e0.j;
import b.d.a.a.h;
import b.d.a.a.k;

/* JADX INFO: loaded from: classes.dex */
public class c extends ConstraintLayout {
    public final Runnable u;
    public int v;
    public g w;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.m();
        }
    }

    public c(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public c(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LayoutInflater.from(context).inflate(h.material_radial_view_group, this);
        g gVar = new g();
        this.w = gVar;
        b.d.a.a.e0.h hVar = new b.d.a.a.e0.h(0.5f);
        j jVar = gVar.f1828a.f1835a;
        if (jVar == null) {
            throw null;
        }
        j.b bVar = new j.b(jVar);
        bVar.f1853e = hVar;
        bVar.f1854f = hVar;
        bVar.g = hVar;
        bVar.h = hVar;
        gVar.f1828a.f1835a = bVar.a();
        gVar.invalidateSelf();
        this.w.q(ColorStateList.valueOf(-1));
        q.X(this, this.w);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, k.RadialViewGroup, i, 0);
        this.v = typedArrayObtainStyledAttributes.getDimensionPixelSize(k.RadialViewGroup_materialCircleRadius, 0);
        this.u = new a();
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        if (view.getId() == -1) {
            view.setId(q.e());
        }
        Handler handler = getHandler();
        if (handler != null) {
            handler.removeCallbacks(this.u);
            handler.post(this.u);
        }
    }

    public void m() {
        int childCount = getChildCount();
        int i = 1;
        for (int i2 = 0; i2 < childCount; i2++) {
            if ("skip".equals(getChildAt(i2).getTag())) {
                i++;
            }
        }
        a.f.b.d dVar = new a.f.b.d();
        dVar.c(this);
        float f2 = 0.0f;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getId() != b.d.a.a.f.circle_center && !"skip".equals(childAt.getTag())) {
                int id = childAt.getId();
                int i4 = b.d.a.a.f.circle_center;
                int i5 = this.v;
                if (!dVar.f656c.containsKey(Integer.valueOf(id))) {
                    dVar.f656c.put(Integer.valueOf(id), new d.a());
                }
                d.b bVar = dVar.f656c.get(Integer.valueOf(id)).f660d;
                bVar.A = i4;
                bVar.B = i5;
                bVar.C = f2;
                f2 = (360.0f / (childCount - i)) + f2;
            }
        }
        dVar.b(this, true);
        setConstraintSet(null);
        requestLayout();
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        m();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        Handler handler = getHandler();
        if (handler != null) {
            handler.removeCallbacks(this.u);
            handler.post(this.u);
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.w.q(ColorStateList.valueOf(i));
    }
}
