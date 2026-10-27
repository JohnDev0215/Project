package com.google.android.material.internal;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: TextDrawableHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f3742c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private d.b.a.a.t.d f3745f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final TextPaint f3740a = new TextPaint(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final d.b.a.a.t.f f3741b = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f3743d = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private WeakReference<b> f3744e = new WeakReference<>(null);

    /* JADX INFO: compiled from: TextDrawableHelper.java */
    class a extends d.b.a.a.t.f {
        a() {
        }

        @Override // d.b.a.a.t.f
        public void a(int i) {
            i.this.f3743d = true;
            b bVar = (b) i.this.f3744e.get();
            if (bVar != null) {
                bVar.a();
            }
        }

        @Override // d.b.a.a.t.f
        public void b(Typeface typeface, boolean z) {
            if (z) {
                return;
            }
            i.this.f3743d = true;
            b bVar = (b) i.this.f3744e.get();
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
        return this.f3740a.measureText(charSequence, 0, charSequence.length());
    }

    public d.b.a.a.t.d d() {
        return this.f3745f;
    }

    public TextPaint e() {
        return this.f3740a;
    }

    public float f(String str) {
        if (!this.f3743d) {
            return this.f3742c;
        }
        float fC = c(str);
        this.f3742c = fC;
        this.f3743d = false;
        return fC;
    }

    public void g(b bVar) {
        this.f3744e = new WeakReference<>(bVar);
    }

    public void h(d.b.a.a.t.d dVar, Context context) {
        if (this.f3745f != dVar) {
            this.f3745f = dVar;
            if (dVar != null) {
                dVar.j(context, this.f3740a, this.f3741b);
                b bVar = this.f3744e.get();
                if (bVar != null) {
                    this.f3740a.drawableState = bVar.getState();
                }
                dVar.i(context, this.f3740a, this.f3741b);
                this.f3743d = true;
            }
            b bVar2 = this.f3744e.get();
            if (bVar2 != null) {
                bVar2.a();
                bVar2.onStateChange(bVar2.getState());
            }
        }
    }

    public void i(boolean z) {
        this.f3743d = z;
    }

    public void j(Context context) {
        this.f3745f.i(context, this.f3740a, this.f3741b);
    }
}
