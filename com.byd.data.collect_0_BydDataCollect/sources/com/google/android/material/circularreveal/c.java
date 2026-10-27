package com.google.android.material.circularreveal;

import android.animation.TypeEvaluator;
import android.graphics.drawable.Drawable;
import android.util.Property;
import com.google.android.material.circularreveal.b;

/* JADX INFO: compiled from: CircularRevealWidget.java */
/* JADX INFO: loaded from: classes.dex */
public interface c extends b.a {

    /* JADX INFO: compiled from: CircularRevealWidget.java */
    public static class b implements TypeEvaluator<e> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final TypeEvaluator<e> f3898a = new b();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final e f3899b = new e();

        @Override // android.animation.TypeEvaluator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public e evaluate(float f2, e eVar, e eVar2) {
            this.f3899b.b(d.b.a.a.t.a.d(eVar.f3902a, eVar2.f3902a, f2), d.b.a.a.t.a.d(eVar.f3903b, eVar2.f3903b, f2), d.b.a.a.t.a.d(eVar.f3904c, eVar2.f3904c, f2));
            return this.f3899b;
        }
    }

    /* JADX INFO: renamed from: com.google.android.material.circularreveal.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CircularRevealWidget.java */
    public static class C0074c extends Property<c, e> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Property<c, e> f3900a = new C0074c("circularReveal");

        private C0074c(String str) {
            super(e.class, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public e get(c cVar) {
            return cVar.getRevealInfo();
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(c cVar, e eVar) {
            cVar.setRevealInfo(eVar);
        }
    }

    /* JADX INFO: compiled from: CircularRevealWidget.java */
    public static class d extends Property<c, Integer> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Property<c, Integer> f3901a = new d("circularRevealScrimColor");

        private d(String str) {
            super(Integer.class, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Integer get(c cVar) {
            return Integer.valueOf(cVar.getCircularRevealScrimColor());
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(c cVar, Integer num) {
            cVar.setCircularRevealScrimColor(num.intValue());
        }
    }

    /* JADX INFO: compiled from: CircularRevealWidget.java */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f3902a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f3903b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f3904c;

        public boolean a() {
            return this.f3904c == Float.MAX_VALUE;
        }

        public void b(float f2, float f3, float f4) {
            this.f3902a = f2;
            this.f3903b = f3;
            this.f3904c = f4;
        }

        public void c(e eVar) {
            b(eVar.f3902a, eVar.f3903b, eVar.f3904c);
        }

        private e() {
        }

        public e(float f2, float f3, float f4) {
            this.f3902a = f2;
            this.f3903b = f3;
            this.f3904c = f4;
        }

        public e(e eVar) {
            this(eVar.f3902a, eVar.f3903b, eVar.f3904c);
        }
    }

    void b();

    void c();

    int getCircularRevealScrimColor();

    e getRevealInfo();

    void setCircularRevealOverlayDrawable(Drawable drawable);

    void setCircularRevealScrimColor(int i2);

    void setRevealInfo(e eVar);
}
