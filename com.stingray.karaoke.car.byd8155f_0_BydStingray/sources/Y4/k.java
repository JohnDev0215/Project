package Y4;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;

/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public com.bumptech.glide.c f10748a = new i();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public com.bumptech.glide.c f10749b = new i();
    public com.bumptech.glide.c c = new i();
    public com.bumptech.glide.c d = new i();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c f10750e = new a(0.0f);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c f10751f = new a(0.0f);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c f10752g = new a(0.0f);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public c f10753h = new a(0.0f);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public e f10754i = new e(0);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public e f10755j = new e(0);

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public e f10756k = new e(0);
    public e l = new e(0);

    public static j a(Context context, AttributeSet attributeSet, int i7, int i10) {
        a aVar = new a(0);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, K4.a.l, i7, i10);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(1, 0);
        typedArrayObtainStyledAttributes.recycle();
        if (resourceId2 != 0) {
            context = new ContextThemeWrapper(context, resourceId);
            resourceId = resourceId2;
        }
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, K4.a.f3598r);
        try {
            int i11 = typedArrayObtainStyledAttributes2.getInt(0, 0);
            int i12 = typedArrayObtainStyledAttributes2.getInt(3, i11);
            int i13 = typedArrayObtainStyledAttributes2.getInt(4, i11);
            int i14 = typedArrayObtainStyledAttributes2.getInt(2, i11);
            int i15 = typedArrayObtainStyledAttributes2.getInt(1, i11);
            c cVarB = b(typedArrayObtainStyledAttributes2, 5, aVar);
            c cVarB2 = b(typedArrayObtainStyledAttributes2, 8, cVarB);
            c cVarB3 = b(typedArrayObtainStyledAttributes2, 9, cVarB);
            c cVarB4 = b(typedArrayObtainStyledAttributes2, 7, cVarB);
            c cVarB5 = b(typedArrayObtainStyledAttributes2, 6, cVarB);
            j jVar = new j();
            com.bumptech.glide.c cVarX = com.bumptech.glide.d.x(i12);
            jVar.f10739a = cVarX;
            j.b(cVarX);
            jVar.f10741e = cVarB2;
            com.bumptech.glide.c cVarX2 = com.bumptech.glide.d.x(i13);
            jVar.f10740b = cVarX2;
            j.b(cVarX2);
            jVar.f10742f = cVarB3;
            com.bumptech.glide.c cVarX3 = com.bumptech.glide.d.x(i14);
            jVar.c = cVarX3;
            j.b(cVarX3);
            jVar.f10743g = cVarB4;
            com.bumptech.glide.c cVarX4 = com.bumptech.glide.d.x(i15);
            jVar.d = cVarX4;
            j.b(cVarX4);
            jVar.f10744h = cVarB5;
            return jVar;
        } finally {
            typedArrayObtainStyledAttributes2.recycle();
        }
    }

    public static c b(TypedArray typedArray, int i7, c cVar) {
        TypedValue typedValuePeekValue = typedArray.peekValue(i7);
        if (typedValuePeekValue == null) {
            return cVar;
        }
        int i10 = typedValuePeekValue.type;
        return i10 == 5 ? new a(TypedValue.complexToDimensionPixelSize(typedValuePeekValue.data, typedArray.getResources().getDisplayMetrics())) : i10 == 6 ? new h(typedValuePeekValue.getFraction(1.0f, 1.0f)) : cVar;
    }

    public final boolean c(RectF rectF) {
        boolean z6 = this.l.getClass().equals(e.class) && this.f10755j.getClass().equals(e.class) && this.f10754i.getClass().equals(e.class) && this.f10756k.getClass().equals(e.class);
        float fA = this.f10750e.a(rectF);
        return z6 && ((this.f10751f.a(rectF) > fA ? 1 : (this.f10751f.a(rectF) == fA ? 0 : -1)) == 0 && (this.f10753h.a(rectF) > fA ? 1 : (this.f10753h.a(rectF) == fA ? 0 : -1)) == 0 && (this.f10752g.a(rectF) > fA ? 1 : (this.f10752g.a(rectF) == fA ? 0 : -1)) == 0) && ((this.f10749b instanceof i) && (this.f10748a instanceof i) && (this.c instanceof i) && (this.d instanceof i));
    }

    public final j d() {
        j jVar = new j();
        jVar.f10739a = this.f10748a;
        jVar.f10740b = this.f10749b;
        jVar.c = this.c;
        jVar.d = this.d;
        jVar.f10741e = this.f10750e;
        jVar.f10742f = this.f10751f;
        jVar.f10743g = this.f10752g;
        jVar.f10744h = this.f10753h;
        jVar.f10745i = this.f10754i;
        jVar.f10746j = this.f10755j;
        jVar.f10747k = this.f10756k;
        jVar.l = this.l;
        return jVar;
    }
}
