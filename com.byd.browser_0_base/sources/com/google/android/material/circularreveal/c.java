package com.google.android.material.circularreveal;

import android.animation.TypeEvaluator;
import android.graphics.drawable.Drawable;
import android.util.Property;
import com.google.android.material.circularreveal.b;

/* JADX INFO: compiled from: CircularRevealWidget.java */
/* JADX INFO: loaded from: classes.dex */
public interface c extends b.a {

    /* JADX INFO: compiled from: CircularRevealWidget.java */
    public static class a implements TypeEvaluator<d> {
        public static final a b = new a();
        public final d a = new d();

        @Override // android.animation.TypeEvaluator
        public final d evaluate(float f, d dVar, d dVar2) {
            d dVar3 = dVar;
            d dVar4 = dVar2;
            float f2 = dVar3.a;
            float f3 = 1.0f - f;
            float f4 = (dVar4.a * f) + (f2 * f3);
            float f5 = dVar3.b;
            float f6 = (dVar4.b * f) + (f5 * f3);
            float f7 = dVar3.c;
            float f8 = f * dVar4.c;
            d dVar5 = this.a;
            dVar5.a = f4;
            dVar5.b = f6;
            dVar5.c = f8 + (f3 * f7);
            return dVar5;
        }
    }

    /* JADX INFO: compiled from: CircularRevealWidget.java */
    public static class b extends Property<c, d> {
        public static final b a = new b();

        public b() {
            super(d.class, "circularReveal");
        }

        @Override // android.util.Property
        public final d get(c cVar) {
            return cVar.getRevealInfo();
        }

        @Override // android.util.Property
        public final void set(c cVar, d dVar) {
            cVar.setRevealInfo(dVar);
        }
    }

    /* JADX INFO: renamed from: com.google.android.material.circularreveal.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CircularRevealWidget.java */
    public static class C0034c extends Property<c, Integer> {
        public static final C0034c a = new C0034c();

        public C0034c() {
            super(Integer.class, "circularRevealScrimColor");
        }

        @Override // android.util.Property
        public final Integer get(c cVar) {
            return Integer.valueOf(cVar.getCircularRevealScrimColor());
        }

        @Override // android.util.Property
        public final void set(c cVar, Integer num) {
            cVar.setCircularRevealScrimColor(num.intValue());
        }
    }

    void a();

    void b();

    int getCircularRevealScrimColor();

    d getRevealInfo();

    void setCircularRevealOverlayDrawable(Drawable drawable);

    void setCircularRevealScrimColor(int i);

    void setRevealInfo(d dVar);

    /* JADX INFO: compiled from: CircularRevealWidget.java */
    public static class d {
        public float a;
        public float b;
        public float c;

        public d() {
        }

        public d(float f, float f2, float f3) {
            this.a = f;
            this.b = f2;
            this.c = f3;
        }

        public d(d dVar) {
            this(dVar.a, dVar.b, dVar.c);
        }
    }
}
