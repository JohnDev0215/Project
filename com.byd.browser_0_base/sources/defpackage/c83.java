package defpackage;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: TextDrawableHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class c83 {
    public float c;
    public WeakReference<b> e;
    public s73 f;
    public final TextPaint a = new TextPaint(1);
    public final a b = new a();
    public boolean d = true;

    /* JADX INFO: compiled from: TextDrawableHelper.java */
    public class a extends vd1 {
        public a() {
        }

        @Override // defpackage.vd1
        public final void s(int i) {
            c83 c83Var = c83.this;
            c83Var.d = true;
            b bVar = c83Var.e.get();
            if (bVar != null) {
                bVar.a();
            }
        }

        @Override // defpackage.vd1
        public final void w(Typeface typeface, boolean z) {
            if (z) {
                return;
            }
            c83 c83Var = c83.this;
            c83Var.d = true;
            b bVar = c83Var.e.get();
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

    public c83(b bVar) {
        this.e = new WeakReference<>(null);
        this.e = new WeakReference<>(bVar);
    }

    public final float a(String str) {
        if (!this.d) {
            return this.c;
        }
        float fMeasureText = str == null ? 0.0f : this.a.measureText((CharSequence) str, 0, str.length());
        this.c = fMeasureText;
        this.d = false;
        return fMeasureText;
    }

    public final void b(s73 s73Var, Context context) {
        if (this.f != s73Var) {
            this.f = s73Var;
            if (s73Var != null) {
                TextPaint textPaint = this.a;
                a aVar = this.b;
                s73Var.f(context, textPaint, aVar);
                b bVar = this.e.get();
                if (bVar != null) {
                    textPaint.drawableState = bVar.getState();
                }
                s73Var.e(context, textPaint, aVar);
                this.d = true;
            }
            b bVar2 = this.e.get();
            if (bVar2 != null) {
                bVar2.a();
                bVar2.onStateChange(bVar2.getState());
            }
        }
    }
}
