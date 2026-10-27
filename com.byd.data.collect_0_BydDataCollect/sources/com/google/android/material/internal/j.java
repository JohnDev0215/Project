package com.google.android.material.internal;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: TextDrawableHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f4162c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private d.b.a.a.u.d f4165f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final TextPaint f4160a = new TextPaint(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final d.b.a.a.u.f f4161b = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f4163d = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private WeakReference<b> f4164e = new WeakReference<>(null);

    /* JADX INFO: compiled from: TextDrawableHelper.java */
    class a extends d.b.a.a.u.f {
        a() {
        }

        @Override // d.b.a.a.u.f
        public void a(int i2) {
            j.this.f4163d = true;
            b bVar = (b) j.this.f4164e.get();
            if (bVar != null) {
                bVar.a();
            }
        }

        @Override // d.b.a.a.u.f
        public void b(Typeface typeface, boolean z) {
            if (z) {
                return;
            }
            j.this.f4163d = true;
            b bVar = (b) j.this.f4164e.get();
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

    public j(b bVar) {
        g(bVar);
    }

    private float c(CharSequence charSequence) {
        if (charSequence == null) {
            return 0.0f;
        }
        return this.f4160a.measureText(charSequence, 0, charSequence.length());
    }

    public d.b.a.a.u.d d() {
        return this.f4165f;
    }

    public TextPaint e() {
        return this.f4160a;
    }

    public float f(String str) {
        if (!this.f4163d) {
            return this.f4162c;
        }
        float fC = c(str);
        this.f4162c = fC;
        this.f4163d = false;
        return fC;
    }

    public void g(b bVar) {
        this.f4164e = new WeakReference<>(bVar);
    }

    public void h(d.b.a.a.u.d dVar, Context context) {
        if (this.f4165f != dVar) {
            this.f4165f = dVar;
            if (dVar != null) {
                dVar.j(context, this.f4160a, this.f4161b);
                b bVar = this.f4164e.get();
                if (bVar != null) {
                    this.f4160a.drawableState = bVar.getState();
                }
                dVar.i(context, this.f4160a, this.f4161b);
                this.f4163d = true;
            }
            b bVar2 = this.f4164e.get();
            if (bVar2 != null) {
                bVar2.a();
                bVar2.onStateChange(bVar2.getState());
            }
        }
    }

    public void i(boolean z) {
        this.f4163d = z;
    }

    public void j(Context context) {
        this.f4165f.i(context, this.f4160a, this.f4161b);
    }
}
