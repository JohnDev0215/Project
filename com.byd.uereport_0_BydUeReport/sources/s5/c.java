package s5;

import android.animation.TypeEvaluator;
import android.graphics.drawable.Drawable;
import android.util.Property;
import s5.b;

/* JADX INFO: compiled from: CircularRevealWidget.java */
/* JADX INFO: loaded from: classes2.dex */
public interface c extends b.a {

    /* JADX INFO: compiled from: CircularRevealWidget.java */
    public static class b implements TypeEvaluator<e> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final TypeEvaluator<e> f7913a = new b();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final e f7914b = new e();

        @Override // android.animation.TypeEvaluator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public e evaluate(float f8, e eVar, e eVar2) {
            this.f7914b.b(a6.a.d(eVar.f7917a, eVar2.f7917a, f8), a6.a.d(eVar.f7918b, eVar2.f7918b, f8), a6.a.d(eVar.f7919c, eVar2.f7919c, f8));
            return this.f7914b;
        }
    }

    /* JADX INFO: renamed from: s5.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CircularRevealWidget.java */
    public static class C0119c extends Property<c, e> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Property<c, e> f7915a = new C0119c("circularReveal");

        public C0119c(String str) {
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
        public static final Property<c, Integer> f7916a = new d("circularRevealScrimColor");

        public d(String str) {
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
        public float f7917a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f7918b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f7919c;

        public boolean a() {
            return this.f7919c == Float.MAX_VALUE;
        }

        public void b(float f8, float f9, float f10) {
            this.f7917a = f8;
            this.f7918b = f9;
            this.f7919c = f10;
        }

        public void c(e eVar) {
            b(eVar.f7917a, eVar.f7918b, eVar.f7919c);
        }

        public e() {
        }

        public e(float f8, float f9, float f10) {
            this.f7917a = f8;
            this.f7918b = f9;
            this.f7919c = f10;
        }

        public e(e eVar) {
            this(eVar.f7917a, eVar.f7918b, eVar.f7919c);
        }
    }

    void a();

    void c();

    int getCircularRevealScrimColor();

    e getRevealInfo();

    void setCircularRevealOverlayDrawable(Drawable drawable);

    void setCircularRevealScrimColor(int i8);

    void setRevealInfo(e eVar);
}
