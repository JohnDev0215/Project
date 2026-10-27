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
        public static final TypeEvaluator<e> f3479a = new b();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final e f3480b = new e();

        @Override // android.animation.TypeEvaluator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public e evaluate(float f, e eVar, e eVar2) {
            this.f3480b.b(d.b.a.a.s.a.d(eVar.f3483a, eVar2.f3483a, f), d.b.a.a.s.a.d(eVar.f3484b, eVar2.f3484b, f), d.b.a.a.s.a.d(eVar.f3485c, eVar2.f3485c, f));
            return this.f3480b;
        }
    }

    /* JADX INFO: renamed from: com.google.android.material.circularreveal.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CircularRevealWidget.java */
    public static class C0112c extends Property<c, e> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Property<c, e> f3481a = new C0112c("circularReveal");

        private C0112c(String str) {
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
        public static final Property<c, Integer> f3482a = new d("circularRevealScrimColor");

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
        public float f3483a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f3484b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f3485c;

        public boolean a() {
            return this.f3485c == Float.MAX_VALUE;
        }

        public void b(float f, float f2, float f3) {
            this.f3483a = f;
            this.f3484b = f2;
            this.f3485c = f3;
        }

        public void c(e eVar) {
            b(eVar.f3483a, eVar.f3484b, eVar.f3485c);
        }

        private e() {
        }

        public e(float f, float f2, float f3) {
            this.f3483a = f;
            this.f3484b = f2;
            this.f3485c = f3;
        }

        public e(e eVar) {
            this(eVar.f3483a, eVar.f3484b, eVar.f3485c);
        }
    }

    void a();

    void c();

    int getCircularRevealScrimColor();

    e getRevealInfo();

    void setCircularRevealOverlayDrawable(Drawable drawable);

    void setCircularRevealScrimColor(int i);

    void setRevealInfo(e eVar);
}
