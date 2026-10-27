package com.google.android.material.internal;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: TextDrawableHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f3936c;
    private c.b.a.a.t.d f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final TextPaint f3934a = new TextPaint(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.b.a.a.t.f f3935b = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f3937d = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private WeakReference<b> f3938e = new WeakReference<>(null);

    /* JADX INFO: compiled from: TextDrawableHelper.java */
    class a extends c.b.a.a.t.f {
        a() {
        }

        @Override // c.b.a.a.t.f
        public void a(int i) {
            i.this.f3937d = true;
            b bVar = (b) i.this.f3938e.get();
            if (bVar != null) {
                bVar.a();
            }
        }

        @Override // c.b.a.a.t.f
        public void b(Typeface typeface, boolean z) {
            if (z) {
                return;
            }
            i.this.f3937d = true;
            b bVar = (b) i.this.f3938e.get();
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
        return this.f3934a.measureText(charSequence, 0, charSequence.length());
    }

    public c.b.a.a.t.d d() {
        return this.f;
    }

    public TextPaint e() {
        return this.f3934a;
    }

    public float f(String str) {
        if (!this.f3937d) {
            return this.f3936c;
        }
        float fC = c(str);
        this.f3936c = fC;
        this.f3937d = false;
        return fC;
    }

    public void g(b bVar) {
        this.f3938e = new WeakReference<>(bVar);
    }

    public void h(c.b.a.a.t.d dVar, Context context) {
        if (this.f != dVar) {
            this.f = dVar;
            if (dVar != null) {
                dVar.j(context, this.f3934a, this.f3935b);
                b bVar = this.f3938e.get();
                if (bVar != null) {
                    this.f3934a.drawableState = bVar.getState();
                }
                dVar.i(context, this.f3934a, this.f3935b);
                this.f3937d = true;
            }
            b bVar2 = this.f3938e.get();
            if (bVar2 != null) {
                bVar2.a();
                bVar2.onStateChange(bVar2.getState());
            }
        }
    }

    public void i(boolean z) {
        this.f3937d = z;
    }

    public void j(Context context) {
        this.f.i(context, this.f3934a, this.f3935b);
    }
}
