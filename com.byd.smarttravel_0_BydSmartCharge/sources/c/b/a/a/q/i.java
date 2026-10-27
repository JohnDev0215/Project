package c.b.a.a.q;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: TextDrawableHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f1800c;
    public c.b.a.a.s.d f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextPaint f1798a = new TextPaint(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c.b.a.a.s.f f1799b = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1801d = true;
    public WeakReference<b> e = new WeakReference<>(null);

    /* JADX INFO: compiled from: TextDrawableHelper.java */
    public class a extends c.b.a.a.s.f {
        public a() {
        }

        @Override // c.b.a.a.s.f
        public void a(int i) {
            i.this.f1801d = true;
            b bVar = (b) i.this.e.get();
            if (bVar != null) {
                bVar.a();
            }
        }

        @Override // c.b.a.a.s.f
        public void b(Typeface typeface, boolean z) {
            if (z) {
                return;
            }
            i.this.f1801d = true;
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

    public final float c(CharSequence charSequence) {
        if (charSequence == null) {
            return 0.0f;
        }
        return this.f1798a.measureText(charSequence, 0, charSequence.length());
    }

    public c.b.a.a.s.d d() {
        return this.f;
    }

    public TextPaint e() {
        return this.f1798a;
    }

    public float f(String str) {
        if (!this.f1801d) {
            return this.f1800c;
        }
        float fC = c(str);
        this.f1800c = fC;
        this.f1801d = false;
        return fC;
    }

    public void g(b bVar) {
        this.e = new WeakReference<>(bVar);
    }

    public void h(c.b.a.a.s.d dVar, Context context) {
        if (this.f != dVar) {
            this.f = dVar;
            if (dVar != null) {
                dVar.j(context, this.f1798a, this.f1799b);
                b bVar = this.e.get();
                if (bVar != null) {
                    this.f1798a.drawableState = bVar.getState();
                }
                dVar.i(context, this.f1798a, this.f1799b);
                this.f1801d = true;
            }
            b bVar2 = this.e.get();
            if (bVar2 != null) {
                bVar2.a();
                bVar2.onStateChange(bVar2.getState());
            }
        }
    }

    public void i(boolean z) {
        this.f1801d = z;
    }

    public void j(Context context) {
        this.f.i(context, this.f1798a, this.f1799b);
    }
}
