package b2;

import android.animation.TypeEvaluator;
import android.graphics.drawable.Drawable;
import android.util.Property;
import b2.b;

/* JADX INFO: compiled from: CircularRevealWidget.java */
/* JADX INFO: loaded from: classes.dex */
public interface c extends b.a {

    /* JADX INFO: compiled from: CircularRevealWidget.java */
    public static class b implements TypeEvaluator<e> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final TypeEvaluator<e> f1771a = new b();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final e f1772b = new e();

        @Override // android.animation.TypeEvaluator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public e evaluate(float f3, e eVar, e eVar2) {
            this.f1772b.b(j2.a.d(eVar.f1775a, eVar2.f1775a, f3), j2.a.d(eVar.f1776b, eVar2.f1776b, f3), j2.a.d(eVar.f1777c, eVar2.f1777c, f3));
            return this.f1772b;
        }
    }

    /* JADX INFO: renamed from: b2.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CircularRevealWidget.java */
    public static class C0005c extends Property<c, e> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Property<c, e> f1773a = new C0005c("circularReveal");

        public C0005c(String str) {
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
        public static final Property<c, Integer> f1774a = new d("circularRevealScrimColor");

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
        public float f1775a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f1776b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f1777c;

        public boolean a() {
            return this.f1777c == Float.MAX_VALUE;
        }

        public void b(float f3, float f4, float f5) {
            this.f1775a = f3;
            this.f1776b = f4;
            this.f1777c = f5;
        }

        public void c(e eVar) {
            b(eVar.f1775a, eVar.f1776b, eVar.f1777c);
        }

        public e() {
        }

        public e(float f3, float f4, float f5) {
            this.f1775a = f3;
            this.f1776b = f4;
            this.f1777c = f5;
        }

        public e(e eVar) {
            this(eVar.f1775a, eVar.f1776b, eVar.f1777c);
        }
    }

    void b();

    void c();

    int getCircularRevealScrimColor();

    e getRevealInfo();

    void setCircularRevealOverlayDrawable(Drawable drawable);

    void setCircularRevealScrimColor(int i3);

    void setRevealInfo(e eVar);
}
