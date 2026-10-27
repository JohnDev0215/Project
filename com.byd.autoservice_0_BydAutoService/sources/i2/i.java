package i2;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: TextDrawableHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f3362c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public k2.d f3365f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextPaint f3360a = new TextPaint(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final k2.f f3361b = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f3363d = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public WeakReference<b> f3364e = new WeakReference<>(null);

    /* JADX INFO: compiled from: TextDrawableHelper.java */
    public class a extends k2.f {
        public a() {
        }

        @Override // k2.f
        public void a(int i3) {
            i.this.f3363d = true;
            b bVar = (b) i.this.f3364e.get();
            if (bVar != null) {
                bVar.a();
            }
        }

        @Override // k2.f
        public void b(Typeface typeface, boolean z3) {
            if (z3) {
                return;
            }
            i.this.f3363d = true;
            b bVar = (b) i.this.f3364e.get();
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
        return this.f3360a.measureText(charSequence, 0, charSequence.length());
    }

    public k2.d d() {
        return this.f3365f;
    }

    public TextPaint e() {
        return this.f3360a;
    }

    public float f(String str) {
        if (!this.f3363d) {
            return this.f3362c;
        }
        float fC = c(str);
        this.f3362c = fC;
        this.f3363d = false;
        return fC;
    }

    public void g(b bVar) {
        this.f3364e = new WeakReference<>(bVar);
    }

    public void h(k2.d dVar, Context context) {
        if (this.f3365f != dVar) {
            this.f3365f = dVar;
            if (dVar != null) {
                dVar.j(context, this.f3360a, this.f3361b);
                b bVar = this.f3364e.get();
                if (bVar != null) {
                    this.f3360a.drawableState = bVar.getState();
                }
                dVar.i(context, this.f3360a, this.f3361b);
                this.f3363d = true;
            }
            b bVar2 = this.f3364e.get();
            if (bVar2 != null) {
                bVar2.a();
                bVar2.onStateChange(bVar2.getState());
            }
        }
    }

    public void i(boolean z3) {
        this.f3363d = z3;
    }

    public void j(Context context) {
        this.f3365f.i(context, this.f3360a, this.f3361b);
    }
}
