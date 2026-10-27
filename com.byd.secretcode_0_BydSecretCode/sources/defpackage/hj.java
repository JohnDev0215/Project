package defpackage;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: TextDrawableHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class hj {
    public float c;
    public tj f;
    public final TextPaint a = new TextPaint(1);
    public final vj b = new a();
    public boolean d = true;
    public WeakReference<b> e = new WeakReference<>(null);

    /* JADX INFO: compiled from: TextDrawableHelper.java */
    public class a extends vj {
        public a() {
        }

        @Override // defpackage.vj
        public void a(int i) {
            hj.this.d = true;
            b bVar = (b) hj.this.e.get();
            if (bVar != null) {
                bVar.a();
            }
        }

        @Override // defpackage.vj
        public void b(Typeface typeface, boolean z) {
            if (z) {
                return;
            }
            hj.this.d = true;
            b bVar = (b) hj.this.e.get();
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

    public hj(b bVar) {
        g(bVar);
    }

    public final float c(CharSequence charSequence) {
        if (charSequence == null) {
            return 0.0f;
        }
        return this.a.measureText(charSequence, 0, charSequence.length());
    }

    public tj d() {
        return this.f;
    }

    public TextPaint e() {
        return this.a;
    }

    public float f(String str) {
        if (!this.d) {
            return this.c;
        }
        float fC = c(str);
        this.c = fC;
        this.d = false;
        return fC;
    }

    public void g(b bVar) {
        this.e = new WeakReference<>(bVar);
    }

    public void h(tj tjVar, Context context) {
        if (this.f != tjVar) {
            this.f = tjVar;
            if (tjVar != null) {
                tjVar.j(context, this.a, this.b);
                b bVar = this.e.get();
                if (bVar != null) {
                    this.a.drawableState = bVar.getState();
                }
                tjVar.i(context, this.a, this.b);
                this.d = true;
            }
            b bVar2 = this.e.get();
            if (bVar2 != null) {
                bVar2.a();
                bVar2.onStateChange(bVar2.getState());
            }
        }
    }

    public void i(boolean z) {
        this.d = z;
    }

    public void j(Context context) {
        this.f.i(context, this.a, this.b);
    }
}
