package com.google.android.material.internal;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: TextDrawableHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f4659c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private b3.d f4662f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final TextPaint f4657a = new TextPaint(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b3.f f4658b = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f4660d = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private WeakReference<b> f4661e = new WeakReference<>(null);

    /* JADX INFO: compiled from: TextDrawableHelper.java */
    class a extends b3.f {
        a() {
        }

        @Override // b3.f
        public void a(int i7) {
            h.this.f4660d = true;
            b bVar = (b) h.this.f4661e.get();
            if (bVar != null) {
                bVar.a();
            }
        }

        @Override // b3.f
        public void b(Typeface typeface, boolean z6) {
            if (z6) {
                return;
            }
            h.this.f4660d = true;
            b bVar = (b) h.this.f4661e.get();
            if (bVar != null) {
                bVar.a();
            }
        }
    }

    /* JADX INFO: compiled from: TextDrawableHelper.java */
    public interface b {
        void a();

        int[] getState();

        boolean onStateChange(int[] iArr);
    }

    public h(b bVar) {
        g(bVar);
    }

    private float c(CharSequence charSequence) {
        if (charSequence == null) {
            return 0.0f;
        }
        return this.f4657a.measureText(charSequence, 0, charSequence.length());
    }

    public b3.d d() {
        return this.f4662f;
    }

    public TextPaint e() {
        return this.f4657a;
    }

    public float f(String str) {
        if (!this.f4660d) {
            return this.f4659c;
        }
        float fC = c(str);
        this.f4659c = fC;
        this.f4660d = false;
        return fC;
    }

    public void g(b bVar) {
        this.f4661e = new WeakReference<>(bVar);
    }

    public void h(b3.d dVar, Context context) {
        if (this.f4662f != dVar) {
            this.f4662f = dVar;
            if (dVar != null) {
                dVar.k(context, this.f4657a, this.f4658b);
                b bVar = this.f4661e.get();
                if (bVar != null) {
                    this.f4657a.drawableState = bVar.getState();
                }
                dVar.j(context, this.f4657a, this.f4658b);
                this.f4660d = true;
            }
            b bVar2 = this.f4661e.get();
            if (bVar2 != null) {
                bVar2.a();
                bVar2.onStateChange(bVar2.getState());
            }
        }
    }

    public void i(boolean z6) {
        this.f4660d = z6;
    }

    public void j(Context context) {
        this.f4662f.j(context, this.f4657a, this.f4658b);
    }
}
