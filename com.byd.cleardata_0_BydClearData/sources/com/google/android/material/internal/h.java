package com.google.android.material.internal;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f3150c;
    private b.a.a.a.y.d f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final TextPaint f3148a = new TextPaint(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b.a.a.a.y.f f3149b = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f3151d = true;
    private WeakReference<b> e = new WeakReference<>(null);

    class a extends b.a.a.a.y.f {
        a() {
        }

        @Override // b.a.a.a.y.f
        public void a(int i) {
            h.this.f3151d = true;
            b bVar = (b) h.this.e.get();
            if (bVar != null) {
                bVar.a();
            }
        }

        @Override // b.a.a.a.y.f
        public void b(Typeface typeface, boolean z) {
            if (z) {
                return;
            }
            h.this.f3151d = true;
            b bVar = (b) h.this.e.get();
            if (bVar != null) {
                bVar.a();
            }
        }
    }

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
        return this.f3148a.measureText(charSequence, 0, charSequence.length());
    }

    public b.a.a.a.y.d d() {
        return this.f;
    }

    public TextPaint e() {
        return this.f3148a;
    }

    public float f(String str) {
        if (!this.f3151d) {
            return this.f3150c;
        }
        float fC = c(str);
        this.f3150c = fC;
        this.f3151d = false;
        return fC;
    }

    public void g(b bVar) {
        this.e = new WeakReference<>(bVar);
    }

    public void h(b.a.a.a.y.d dVar, Context context) {
        if (this.f != dVar) {
            this.f = dVar;
            if (dVar != null) {
                dVar.j(context, this.f3148a, this.f3149b);
                b bVar = this.e.get();
                if (bVar != null) {
                    this.f3148a.drawableState = bVar.getState();
                }
                dVar.i(context, this.f3148a, this.f3149b);
                this.f3151d = true;
            }
            b bVar2 = this.e.get();
            if (bVar2 != null) {
                bVar2.a();
                bVar2.onStateChange(bVar2.getState());
            }
        }
    }

    public void i(boolean z) {
        this.f3151d = z;
    }

    public void j(Context context) {
        this.f.i(context, this.f3148a, this.f3149b);
    }
}
