package e7;

import android.view.MotionEvent;
import android.view.View;
import e7.g;

/* JADX INFO: compiled from: HorizontalOverScrollBounceEffectDecorator.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends g {

    /* JADX INFO: renamed from: e7.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: HorizontalOverScrollBounceEffectDecorator.java */
    public static class C0033a extends g.a {
        public C0033a() {
            this.f4781a = View.TRANSLATION_X;
        }

        @Override // e7.g.a
        public void a(View view) {
            this.f4782b = view.getTranslationX();
            this.f4783c = view.getWidth();
        }
    }

    /* JADX INFO: compiled from: HorizontalOverScrollBounceEffectDecorator.java */
    public static class b extends g.e {
        @Override // e7.g.e
        public boolean a(View view, MotionEvent motionEvent) {
            if (motionEvent.getHistorySize() == 0) {
                return false;
            }
            float y7 = motionEvent.getY(0) - motionEvent.getHistoricalY(0, 0);
            float x7 = motionEvent.getX(0) - motionEvent.getHistoricalX(0, 0);
            if (Math.abs(x7) < Math.abs(y7)) {
                return false;
            }
            this.f4791a = view.getTranslationX();
            this.f4792b = x7;
            this.f4793c = x7 > 0.0f;
            return true;
        }
    }

    public a(f7.a aVar) {
        this(aVar, 3.0f, 1.0f, -2.0f);
    }

    @Override // e7.g
    public g.a b() {
        return new C0033a();
    }

    @Override // e7.g
    public g.e c() {
        return new b();
    }

    @Override // e7.g
    public void f(View view, float f8) {
        view.setTranslationX(f8);
    }

    @Override // e7.g
    public void g(View view, float f8, MotionEvent motionEvent) {
        view.setTranslationX(f8);
        motionEvent.offsetLocation(f8 - motionEvent.getX(0), 0.0f);
    }

    public a(f7.a aVar, float f8, float f9, float f10) {
        super(aVar, f10, f8, f9);
    }
}
