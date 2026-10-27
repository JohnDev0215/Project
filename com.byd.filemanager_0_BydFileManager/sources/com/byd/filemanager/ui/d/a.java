package com.byd.filemanager.ui.d;

import android.view.MotionEvent;
import android.view.View;
import com.byd.filemanager.ui.d.h;

/* JADX INFO: compiled from: HorizontalOverScrollBounceEffectDecorator.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends h {

    /* JADX INFO: renamed from: com.byd.filemanager.ui.d.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: HorizontalOverScrollBounceEffectDecorator.java */
    protected static class C0096a extends h.a {
        public C0096a() {
            this.f3792a = View.TRANSLATION_X;
        }

        @Override // com.byd.filemanager.ui.d.h.a
        protected void a(View view) {
            this.f3793b = view.getTranslationX();
            this.f3794c = view.getWidth();
        }
    }

    /* JADX INFO: compiled from: HorizontalOverScrollBounceEffectDecorator.java */
    protected static class b extends h.e {
        protected b() {
        }

        @Override // com.byd.filemanager.ui.d.h.e
        public boolean a(View view, MotionEvent motionEvent) {
            if (motionEvent.getHistorySize() == 0) {
                return false;
            }
            float y = motionEvent.getY(0) - motionEvent.getHistoricalY(0, 0);
            float x = motionEvent.getX(0) - motionEvent.getHistoricalX(0, 0);
            if (Math.abs(x) < Math.abs(y)) {
                return false;
            }
            this.f3802a = view.getTranslationX();
            this.f3803b = x;
            this.f3804c = x > 0.0f;
            return true;
        }
    }

    public a(c cVar) {
        this(cVar, 3.0f, 1.0f, -2.0f);
    }

    @Override // com.byd.filemanager.ui.d.h
    protected h.a b() {
        return new C0096a();
    }

    @Override // com.byd.filemanager.ui.d.h
    protected h.e c() {
        return new b();
    }

    @Override // com.byd.filemanager.ui.d.h
    protected void f(View view, float f) {
        view.setTranslationX(f);
    }

    @Override // com.byd.filemanager.ui.d.h
    protected void g(View view, float f, MotionEvent motionEvent) {
        view.setTranslationX(f);
        motionEvent.offsetLocation(f - motionEvent.getX(0), 0.0f);
    }

    public a(c cVar, float f, float f2, float f3) {
        super(cVar, f3, f, f2);
    }
}
