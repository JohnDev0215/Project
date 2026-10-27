package com.byd.cleardata.ui.g;

import android.view.MotionEvent;
import android.view.View;
import com.byd.cleardata.ui.g.h;

/* JADX INFO: loaded from: classes.dex */
public class k extends h {

    protected static class a extends h.a {
        public a() {
            this.f2889a = View.TRANSLATION_Y;
        }

        @Override // com.byd.cleardata.ui.g.h.a
        protected void a(View view) {
            this.f2890b = view.getTranslationY();
            this.f2891c = view.getHeight();
        }
    }

    protected static class b extends h.e {
        protected b() {
        }

        @Override // com.byd.cleardata.ui.g.h.e
        public boolean a(View view, MotionEvent motionEvent) {
            if (motionEvent.getHistorySize() == 0) {
                return false;
            }
            float y = motionEvent.getY(0) - motionEvent.getHistoricalY(0, 0);
            if (Math.abs(motionEvent.getX(0) - motionEvent.getHistoricalX(0, 0)) > Math.abs(y)) {
                return false;
            }
            this.f2898a = view.getTranslationY();
            this.f2899b = y;
            this.f2900c = y > 0.0f;
            return true;
        }
    }

    public k(c cVar) {
        this(cVar, 3.0f, 1.0f, -2.0f);
    }

    public k(c cVar, float f, float f2, float f3) {
        super(cVar, f3, f, f2);
    }

    @Override // com.byd.cleardata.ui.g.h
    protected h.a b() {
        return new a();
    }

    @Override // com.byd.cleardata.ui.g.h
    protected h.e c() {
        return new b();
    }

    @Override // com.byd.cleardata.ui.g.h
    protected void f(View view, float f) {
        view.setTranslationY(f);
    }

    @Override // com.byd.cleardata.ui.g.h
    protected void g(View view, float f, MotionEvent motionEvent) {
        view.setTranslationY(f);
        motionEvent.offsetLocation(f - motionEvent.getY(0), 0.0f);
    }
}
