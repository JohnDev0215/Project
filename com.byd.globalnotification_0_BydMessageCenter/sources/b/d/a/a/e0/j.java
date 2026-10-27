package b.d.a.a.e0;

import a.b.k.l;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;

/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d f1843a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public d f1844b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public d f1845c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public d f1846d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c f1847e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c f1848f;
    public c g;
    public c h;
    public f i;
    public f j;
    public f k;
    public f l;

    public j() {
        this.f1843a = new i();
        this.f1844b = new i();
        this.f1845c = new i();
        this.f1846d = new i();
        this.f1847e = new b.d.a.a.e0.a(0.0f);
        this.f1848f = new b.d.a.a.e0.a(0.0f);
        this.g = new b.d.a.a.e0.a(0.0f);
        this.h = new b.d.a.a.e0.a(0.0f);
        this.i = new f();
        this.j = new f();
        this.k = new f();
        this.l = new f();
    }

    public static b a(Context context, int i, int i2, c cVar) {
        if (i2 != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i);
            i = i2;
            context = contextThemeWrapper;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, b.d.a.a.k.ShapeAppearance);
        try {
            int i3 = typedArrayObtainStyledAttributes.getInt(b.d.a.a.k.ShapeAppearance_cornerFamily, 0);
            int i4 = typedArrayObtainStyledAttributes.getInt(b.d.a.a.k.ShapeAppearance_cornerFamilyTopLeft, i3);
            int i5 = typedArrayObtainStyledAttributes.getInt(b.d.a.a.k.ShapeAppearance_cornerFamilyTopRight, i3);
            int i6 = typedArrayObtainStyledAttributes.getInt(b.d.a.a.k.ShapeAppearance_cornerFamilyBottomRight, i3);
            int i7 = typedArrayObtainStyledAttributes.getInt(b.d.a.a.k.ShapeAppearance_cornerFamilyBottomLeft, i3);
            c cVarC = c(typedArrayObtainStyledAttributes, b.d.a.a.k.ShapeAppearance_cornerSize, cVar);
            c cVarC2 = c(typedArrayObtainStyledAttributes, b.d.a.a.k.ShapeAppearance_cornerSizeTopLeft, cVarC);
            c cVarC3 = c(typedArrayObtainStyledAttributes, b.d.a.a.k.ShapeAppearance_cornerSizeTopRight, cVarC);
            c cVarC4 = c(typedArrayObtainStyledAttributes, b.d.a.a.k.ShapeAppearance_cornerSizeBottomRight, cVarC);
            c cVarC5 = c(typedArrayObtainStyledAttributes, b.d.a.a.k.ShapeAppearance_cornerSizeBottomLeft, cVarC);
            b bVar = new b();
            d dVarP = l.i.p(i4);
            bVar.f1849a = dVarP;
            float fB = b.b(dVarP);
            if (fB != -1.0f) {
                bVar.f(fB);
            }
            bVar.f1853e = cVarC2;
            d dVarP2 = l.i.p(i5);
            bVar.f1850b = dVarP2;
            float fB2 = b.b(dVarP2);
            if (fB2 != -1.0f) {
                bVar.g(fB2);
            }
            bVar.f1854f = cVarC3;
            d dVarP3 = l.i.p(i6);
            bVar.f1851c = dVarP3;
            float fB3 = b.b(dVarP3);
            if (fB3 != -1.0f) {
                bVar.e(fB3);
            }
            bVar.g = cVarC4;
            d dVarP4 = l.i.p(i7);
            bVar.f1852d = dVarP4;
            float fB4 = b.b(dVarP4);
            if (fB4 != -1.0f) {
                bVar.d(fB4);
            }
            bVar.h = cVarC5;
            return bVar;
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static b b(Context context, AttributeSet attributeSet, int i, int i2) {
        b.d.a.a.e0.a aVar = new b.d.a.a.e0.a(0);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.d.a.a.k.MaterialShape, i, i2);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(b.d.a.a.k.MaterialShape_shapeAppearance, 0);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(b.d.a.a.k.MaterialShape_shapeAppearanceOverlay, 0);
        typedArrayObtainStyledAttributes.recycle();
        return a(context, resourceId, resourceId2, aVar);
    }

    public static c c(TypedArray typedArray, int i, c cVar) {
        TypedValue typedValuePeekValue = typedArray.peekValue(i);
        if (typedValuePeekValue == null) {
            return cVar;
        }
        int i2 = typedValuePeekValue.type;
        return i2 == 5 ? new b.d.a.a.e0.a(TypedValue.complexToDimensionPixelSize(typedValuePeekValue.data, typedArray.getResources().getDisplayMetrics())) : i2 == 6 ? new h(typedValuePeekValue.getFraction(1.0f, 1.0f)) : cVar;
    }

    public boolean d(RectF rectF) {
        boolean z = this.l.getClass().equals(f.class) && this.j.getClass().equals(f.class) && this.i.getClass().equals(f.class) && this.k.getClass().equals(f.class);
        float fA = this.f1847e.a(rectF);
        return z && ((this.f1848f.a(rectF) > fA ? 1 : (this.f1848f.a(rectF) == fA ? 0 : -1)) == 0 && (this.h.a(rectF) > fA ? 1 : (this.h.a(rectF) == fA ? 0 : -1)) == 0 && (this.g.a(rectF) > fA ? 1 : (this.g.a(rectF) == fA ? 0 : -1)) == 0) && ((this.f1844b instanceof i) && (this.f1843a instanceof i) && (this.f1845c instanceof i) && (this.f1846d instanceof i));
    }

    public j e(float f2) {
        b bVar = new b(this);
        bVar.c(f2);
        return bVar.a();
    }

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public d f1849a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public d f1850b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public d f1851c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public d f1852d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public c f1853e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public c f1854f;
        public c g;
        public c h;
        public f i;
        public f j;
        public f k;
        public f l;

        public b() {
            this.f1849a = new i();
            this.f1850b = new i();
            this.f1851c = new i();
            this.f1852d = new i();
            this.f1853e = new b.d.a.a.e0.a(0.0f);
            this.f1854f = new b.d.a.a.e0.a(0.0f);
            this.g = new b.d.a.a.e0.a(0.0f);
            this.h = new b.d.a.a.e0.a(0.0f);
            this.i = new f();
            this.j = new f();
            this.k = new f();
            this.l = new f();
        }

        public static float b(d dVar) {
            if (dVar instanceof i) {
                return ((i) dVar).f1842a;
            }
            if (dVar instanceof e) {
                return ((e) dVar).f1827a;
            }
            return -1.0f;
        }

        public j a() {
            return new j(this, null);
        }

        public b c(float f2) {
            this.f1853e = new b.d.a.a.e0.a(f2);
            this.f1854f = new b.d.a.a.e0.a(f2);
            this.g = new b.d.a.a.e0.a(f2);
            this.h = new b.d.a.a.e0.a(f2);
            return this;
        }

        public b d(float f2) {
            this.h = new b.d.a.a.e0.a(f2);
            return this;
        }

        public b e(float f2) {
            this.g = new b.d.a.a.e0.a(f2);
            return this;
        }

        public b f(float f2) {
            this.f1853e = new b.d.a.a.e0.a(f2);
            return this;
        }

        public b g(float f2) {
            this.f1854f = new b.d.a.a.e0.a(f2);
            return this;
        }

        public b(j jVar) {
            this.f1849a = new i();
            this.f1850b = new i();
            this.f1851c = new i();
            this.f1852d = new i();
            this.f1853e = new b.d.a.a.e0.a(0.0f);
            this.f1854f = new b.d.a.a.e0.a(0.0f);
            this.g = new b.d.a.a.e0.a(0.0f);
            this.h = new b.d.a.a.e0.a(0.0f);
            this.i = new f();
            this.j = new f();
            this.k = new f();
            this.l = new f();
            this.f1849a = jVar.f1843a;
            this.f1850b = jVar.f1844b;
            this.f1851c = jVar.f1845c;
            this.f1852d = jVar.f1846d;
            this.f1853e = jVar.f1847e;
            this.f1854f = jVar.f1848f;
            this.g = jVar.g;
            this.h = jVar.h;
            this.i = jVar.i;
            this.j = jVar.j;
            this.k = jVar.k;
            this.l = jVar.l;
        }
    }

    public j(b bVar, a aVar) {
        this.f1843a = bVar.f1849a;
        this.f1844b = bVar.f1850b;
        this.f1845c = bVar.f1851c;
        this.f1846d = bVar.f1852d;
        this.f1847e = bVar.f1853e;
        this.f1848f = bVar.f1854f;
        this.g = bVar.g;
        this.h = bVar.h;
        this.i = bVar.i;
        this.j = bVar.j;
        this.k = bVar.k;
        this.l = bVar.l;
    }
}
