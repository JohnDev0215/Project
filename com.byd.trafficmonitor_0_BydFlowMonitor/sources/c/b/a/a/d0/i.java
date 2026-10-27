package c.b.a.a.d0;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: TextDrawableHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f3785c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c.b.a.a.f0.d f3788f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextPaint f3783a = new TextPaint(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c.b.a.a.f0.f f3784b = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f3786d = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public WeakReference<b> f3787e = new WeakReference<>(null);

    /* JADX INFO: compiled from: TextDrawableHelper.java */
    public class a extends c.b.a.a.f0.f {
        public a() {
        }

        @Override // c.b.a.a.f0.f
        public void a(int i2) {
            i.this.f3786d = true;
            b bVar = (b) i.this.f3787e.get();
            if (bVar != null) {
                bVar.a();
            }
        }

        @Override // c.b.a.a.f0.f
        public void b(Typeface typeface, boolean z) {
            if (z) {
                return;
            }
            i.this.f3786d = true;
            b bVar = (b) i.this.f3787e.get();
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
        return this.f3783a.measureText(charSequence, 0, charSequence.length());
    }

    public c.b.a.a.f0.d d() {
        return this.f3788f;
    }

    public TextPaint e() {
        return this.f3783a;
    }

    public float f(String str) {
        if (!this.f3786d) {
            return this.f3785c;
        }
        float fC = c(str);
        this.f3785c = fC;
        this.f3786d = false;
        return fC;
    }

    public void g(b bVar) {
        this.f3787e = new WeakReference<>(bVar);
    }

    public void h(c.b.a.a.f0.d dVar, Context context) {
        if (this.f3788f != dVar) {
            this.f3788f = dVar;
            if (dVar != null) {
                dVar.j(context, this.f3783a, this.f3784b);
                b bVar = this.f3787e.get();
                if (bVar != null) {
                    this.f3783a.drawableState = bVar.getState();
                }
                dVar.i(context, this.f3783a, this.f3784b);
                this.f3786d = true;
            }
            b bVar2 = this.f3787e.get();
            if (bVar2 != null) {
                bVar2.a();
                bVar2.onStateChange(bVar2.getState());
            }
        }
    }

    public void i(boolean z) {
        this.f3786d = z;
    }

    public void j(Context context) {
        this.f3788f.i(context, this.f3783a, this.f3784b);
    }
}
