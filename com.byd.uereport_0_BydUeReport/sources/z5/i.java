package z5;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: TextDrawableHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public class i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f9593c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public b6.d f9596f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextPaint f9591a = new TextPaint(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b6.f f9592b = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f9594d = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public WeakReference<b> f9595e = new WeakReference<>(null);

    /* JADX INFO: compiled from: TextDrawableHelper.java */
    public class a extends b6.f {
        public a() {
        }

        @Override // b6.f
        public void a(int i8) {
            i.this.f9594d = true;
            b bVar = (b) i.this.f9595e.get();
            if (bVar != null) {
                bVar.a();
            }
        }

        @Override // b6.f
        public void b(Typeface typeface, boolean z7) {
            if (z7) {
                return;
            }
            i.this.f9594d = true;
            b bVar = (b) i.this.f9595e.get();
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
        return this.f9591a.measureText(charSequence, 0, charSequence.length());
    }

    public b6.d d() {
        return this.f9596f;
    }

    public TextPaint e() {
        return this.f9591a;
    }

    public float f(String str) {
        if (!this.f9594d) {
            return this.f9593c;
        }
        float fC = c(str);
        this.f9593c = fC;
        this.f9594d = false;
        return fC;
    }

    public void g(b bVar) {
        this.f9595e = new WeakReference<>(bVar);
    }

    public void h(b6.d dVar, Context context) {
        if (this.f9596f != dVar) {
            this.f9596f = dVar;
            if (dVar != null) {
                dVar.j(context, this.f9591a, this.f9592b);
                b bVar = this.f9595e.get();
                if (bVar != null) {
                    this.f9591a.drawableState = bVar.getState();
                }
                dVar.i(context, this.f9591a, this.f9592b);
                this.f9594d = true;
            }
            b bVar2 = this.f9595e.get();
            if (bVar2 != null) {
                bVar2.a();
                bVar2.onStateChange(bVar2.getState());
            }
        }
    }

    public void i(boolean z7) {
        this.f9594d = z7;
    }

    public void j(Context context) {
        this.f9596f.i(context, this.f9591a, this.f9592b);
    }
}
