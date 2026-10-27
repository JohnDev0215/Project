package e7;

import android.view.MotionEvent;
import android.view.View;
import e7.g;

/* JADX INFO: compiled from: VerticalOverScrollBounceEffectDecorator.java */
/* JADX INFO: loaded from: classes2.dex */
public class i extends g {

    /* JADX INFO: compiled from: VerticalOverScrollBounceEffectDecorator.java */
    public static class a extends g.a {
        public a() {
            this.f4781a = View.TRANSLATION_Y;
        }

        @Override // e7.g.a
        public void a(View view) {
            this.f4782b = view.getTranslationY();
            this.f4783c = view.getHeight();
        }
    }

    /* JADX INFO: compiled from: VerticalOverScrollBounceEffectDecorator.java */
    public static class b extends g.e {
        @Override // e7.g.e
        public boolean a(View view, MotionEvent motionEvent) {
            if (motionEvent.getHistorySize() == 0) {
                return false;
            }
            float y7 = motionEvent.getY(0) - motionEvent.getHistoricalY(0, 0);
            if (Math.abs(motionEvent.getX(0) - motionEvent.getHistoricalX(0, 0)) > Math.abs(y7)) {
                return false;
            }
            this.f4791a = view.getTranslationY();
            this.f4792b = y7;
            this.f4793c = y7 > 0.0f;
            return true;
        }
    }

    public i(f7.a aVar) {
        this(aVar, 3.0f, 1.0f, -2.0f);
    }

    @Override // e7.g
    public g.a b() {
        return new a();
    }

    @Override // e7.g
    public g.e c() {
        return new b();
    }

    @Override // e7.g
    public void f(View view, float f8) {
        view.setTranslationY(f8);
    }

    @Override // e7.g
    public void g(View view, float f8, MotionEvent motionEvent) {
        view.setTranslationY(f8);
        motionEvent.offsetLocation(f8 - motionEvent.getY(0), 0.0f);
    }

    public i(f7.a aVar, float f8, float f9, float f10) {
        super(aVar, f10, f8, f9);
    }
}
