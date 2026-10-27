package b.a.a.h;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f243a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b f244b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ImageView f245c;
    public int[] d;
    public int e;
    public int f;
    public boolean i;
    public int k;
    public final Context o;
    public boolean g = true;
    public boolean h = false;
    public int j = 0;
    public final f n = f.d();
    public final c m = new c(this, null);
    public Runnable l = new a();

    /* JADX INFO: compiled from: caradapter-bug */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (e.this.g || e.this.d == null) {
                e.this.h = false;
                e eVar = e.this;
                eVar.j = eVar.k;
                return;
            }
            if (!e.this.f245c.isShown()) {
                e.this.m.postDelayed(this, 1500L);
                return;
            }
            e.this.h = true;
            Drawable drawableC = e.this.n.c(e.this.d[e.this.k] + "");
            if (drawableC == null) {
                e.this.n.a(e.this.d[e.this.k] + "", e.this.o.getDrawable(e.this.d[e.this.k]));
                e.this.f245c.setImageResource(e.this.d[e.this.k]);
            } else {
                e.this.f245c.setImageDrawable(drawableC);
            }
            if (e.this.k != e.this.f) {
                e eVar2 = e.this;
                eVar2.n(eVar2.k + 1);
            } else if (e.this.f243a) {
                e.this.n(0);
            } else if (e.this.f244b != null) {
                e.this.f244b.a(e.this.f245c.getId());
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface b {
        void a(int i);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class c extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<e> f247a;

        public /* synthetic */ c(e eVar, a aVar) {
            this(eVar);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
        }

        public c(e eVar) {
            this.f247a = new WeakReference<>(eVar);
        }
    }

    public e(Context context, ImageView imageView, int[] iArr, int i, boolean z) {
        this.i = true;
        this.o = context;
        this.f245c = imageView;
        this.d = iArr;
        this.e = i;
        this.f = iArr.length - 1;
        this.f243a = z;
        this.i = true;
    }

    public boolean m() {
        return this.h;
    }

    public void n(int i) {
        if (this.f245c == null) {
            BydLogHelper.logE("play mImageView == NULL: ");
            return;
        }
        this.g = false;
        this.i = false;
        this.h = true;
        this.k = i;
        this.m.postDelayed(this.l, this.e);
    }

    public void o(boolean z) {
        this.i = true;
        this.g = true;
        this.h = false;
        this.j = 0;
        this.m.removeCallbacksAndMessages(null);
        ImageView imageView = this.f245c;
        if (imageView != null && !z) {
            imageView.setImageResource(this.d[0]);
        }
        this.n.b();
    }

    public void p(int i) {
        this.e = i;
    }
}
