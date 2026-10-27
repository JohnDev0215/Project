package com.google.android.material.internal;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: TextDrawableHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f2446c;
    private c.a.a.a.t.d f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final TextPaint f2444a = new TextPaint(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.a.a.a.t.f f2445b = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f2447d = true;
    private WeakReference<b> e = new WeakReference<>(null);

    /* JADX INFO: compiled from: TextDrawableHelper.java */
    class a extends c.a.a.a.t.f {
        a() {
        }

        @Override // c.a.a.a.t.f
        public void a(int i) {
            i.this.f2447d = true;
            b bVar = (b) i.this.e.get();
            if (bVar != null) {
                bVar.a();
            }
        }

        @Override // c.a.a.a.t.f
        public void b(Typeface typeface, boolean z) {
            if (z) {
                return;
            }
            i.this.f2447d = true;
            b bVar = (b) i.this.e.get();
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

    public i(b bVar) {
        g(bVar);
    }

    private float c(CharSequence charSequence) {
        if (charSequence == null) {
            return 0.0f;
        }
        return this.f2444a.measureText(charSequence, 0, charSequence.length());
    }

    public c.a.a.a.t.d d() {
        return this.f;
    }

    public TextPaint e() {
        return this.f2444a;
    }

    public float f(String str) {
        if (!this.f2447d) {
            return this.f2446c;
        }
        float fC = c(str);
        this.f2446c = fC;
        this.f2447d = false;
        return fC;
    }

    public void g(b bVar) {
        this.e = new WeakReference<>(bVar);
    }

    public void h(c.a.a.a.t.d dVar, Context context) {
        if (this.f != dVar) {
            this.f = dVar;
            if (dVar != null) {
                dVar.j(context, this.f2444a, this.f2445b);
                b bVar = this.e.get();
                if (bVar != null) {
                    this.f2444a.drawableState = bVar.getState();
                }
                dVar.i(context, this.f2444a, this.f2445b);
                this.f2447d = true;
            }
            b bVar2 = this.e.get();
            if (bVar2 != null) {
                bVar2.a();
                bVar2.onStateChange(bVar2.getState());
            }
        }
    }

    public void i(boolean z) {
        this.f2447d = z;
    }

    public void j(Context context) {
        this.f.i(context, this.f2444a, this.f2445b);
    }
}
