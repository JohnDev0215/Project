package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import android.util.FloatProperty;
import org.chromium.chrome.R$dimen;

/* JADX INFO: compiled from: CompositorButton.java */
/* JADX INFO: loaded from: classes.dex */
public class lw implements mo3 {
    public static final a l = new a();
    public static final b m = new b();
    public final RectF a;
    public final RectF b;
    public final c c;
    public float d;
    public float e;
    public boolean f;
    public boolean g;
    public boolean h;
    public final boolean i;
    public String j;
    public String k;

    /* JADX INFO: compiled from: CompositorButton.java */
    public class a extends FloatProperty<lw> {
        public a() {
            super("opacity");
        }

        @Override // android.util.Property
        public final Float get(Object obj) {
            return Float.valueOf(((lw) obj).d);
        }

        @Override // android.util.FloatProperty
        public final void setValue(lw lwVar, float f) {
            lwVar.d = f;
        }
    }

    /* JADX INFO: compiled from: CompositorButton.java */
    public class b extends FloatProperty<lw> {
        public b() {
            super("drawX");
        }

        @Override // android.util.Property
        public final Float get(Object obj) {
            return Float.valueOf(((lw) obj).a.left);
        }

        @Override // android.util.FloatProperty
        public final void setValue(lw lwVar, float f) {
            lwVar.e(f);
        }
    }

    /* JADX INFO: compiled from: CompositorButton.java */
    public interface c {
        void a(long j);
    }

    public lw(Context context, float f, float f2, c cVar) {
        RectF rectF = new RectF();
        this.a = rectF;
        this.b = new RectF();
        rectF.set(0.0f, 0.0f, f, f2);
        this.d = 1.0f;
        this.f = false;
        this.g = true;
        this.h = false;
        this.i = true;
        Resources resources = context.getResources();
        this.e = resources.getDimension(R$dimen.compositor_button_slop) * (1.0f / resources.getDisplayMetrics().density);
        this.c = cVar;
    }

    @Override // defpackage.mo3
    public final void a(long j) {
        this.c.a(j);
    }

    @Override // defpackage.mo3
    public final void b(RectF rectF) {
        rectF.set(this.a);
        float f = this.e;
        rectF.inset((int) (-f), (int) (-f));
    }

    @Override // defpackage.mo3
    public final boolean c(float f, float f2) {
        if (this.d < 1.0f || !this.g || !this.i) {
            return false;
        }
        RectF rectF = this.b;
        rectF.set(this.a);
        float f3 = this.e;
        rectF.inset(-f3, -f3);
        return rectF.contains(f, f2);
    }

    @Override // defpackage.mo3
    public final String d() {
        return this.h ? this.k : this.j;
    }

    public final void e(float f) {
        RectF rectF = this.a;
        rectF.right = rectF.width() + f;
        rectF.left = f;
    }

    public final void f(float f) {
        RectF rectF = this.a;
        rectF.bottom = rectF.height() + f;
        rectF.top = f;
    }
}
