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
        public static final TypeEvaluator<e> f3503a = new b();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final e f3504b = new e();

        @Override // android.animation.TypeEvaluator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public e evaluate(float f2, e eVar, e eVar2) {
            this.f3504b.b(d.b.a.a.s.a.d(eVar.f3507a, eVar2.f3507a, f2), d.b.a.a.s.a.d(eVar.f3508b, eVar2.f3508b, f2), d.b.a.a.s.a.d(eVar.f3509c, eVar2.f3509c, f2));
            return this.f3504b;
        }
    }

    /* JADX INFO: renamed from: com.google.android.material.circularreveal.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CircularRevealWidget.java */
    public static class C0066c extends Property<c, e> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Property<c, e> f3505a = new C0066c("circularReveal");

        private C0066c(String str) {
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
        public static final Property<c, Integer> f3506a = new d("circularRevealScrimColor");

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
        public float f3507a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f3508b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f3509c;

        public boolean a() {
            return this.f3509c == Float.MAX_VALUE;
        }

        public void b(float f2, float f3, float f4) {
            this.f3507a = f2;
            this.f3508b = f3;
            this.f3509c = f4;
        }

        public void c(e eVar) {
            b(eVar.f3507a, eVar.f3508b, eVar.f3509c);
        }

        private e() {
        }

        public e(float f2, float f3, float f4) {
            this.f3507a = f2;
            this.f3508b = f3;
            this.f3509c = f4;
        }

        public e(e eVar) {
            this(eVar.f3507a, eVar.f3508b, eVar.f3509c);
        }
    }

    void b();

    void c();

    int getCircularRevealScrimColor();

    e getRevealInfo();

    void setCircularRevealOverlayDrawable(Drawable drawable);

    void setCircularRevealScrimColor(int i);

    void setRevealInfo(e eVar);
}
