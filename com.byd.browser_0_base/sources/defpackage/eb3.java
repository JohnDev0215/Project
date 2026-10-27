package defpackage;

import J.N;
import android.os.Build;
import android.os.Handler;
import java.util.PriorityQueue;
import org.chromium.ui.widget.Toast;

/* JADX INFO: compiled from: ToastManager.java */
/* JADX INFO: loaded from: classes3.dex */
public final class eb3 {
    public static Boolean d;
    public static eb3 e;
    public final PriorityQueue<Toast> a = new PriorityQueue<>(new j4(3));
    public final a b;
    public Toast c;

    /* JADX INFO: compiled from: ToastManager.java */
    public interface a {
        void a(Toast toast);

        void onCancel();
    }

    /* JADX INFO: compiled from: ToastManager.java */
    public class b implements a {
        public final Handler a = new Handler();
        public final Runnable b;

        public b(an2 an2Var) {
            this.b = an2Var;
        }

        @Override // eb3.a
        public final void a(Toast toast) {
            this.a.postDelayed(this.b, eb3.this.c.a.getDuration() == 0 ? 2000 : 3500);
        }

        @Override // eb3.a
        public final void onCancel() {
            Handler handler = this.a;
            Runnable runnable = this.b;
            handler.removeCallbacks(runnable);
            runnable.run();
        }
    }

    public eb3() {
        if (Build.VERSION.SDK_INT < 30) {
            this.b = new b(new an2(13, this));
        } else {
            this.b = new c(new db3(0, this));
        }
    }

    public static boolean a() {
        if (Boolean.FALSE.equals(d) || !org.chromium.base.library_loader.a.l.d()) {
            return false;
        }
        if (d == null) {
            d = Boolean.valueOf(N.M7z614O_());
        }
        return d.booleanValue();
    }

    public final void b() {
        Toast toastPoll = this.a.poll();
        this.c = toastPoll;
        if (toastPoll != null) {
            toastPoll.a.show();
            this.b.a(this.c);
        }
    }

    /* JADX INFO: compiled from: ToastManager.java */
    public class c implements a {
        public final fb3 a;

        public c(db3 db3Var) {
            this.a = new fb3(db3Var);
        }

        @Override // eb3.a
        public final void a(Toast toast) {
            toast.a.addCallback(this.a);
        }

        @Override // eb3.a
        public final void onCancel() {
        }
    }
}
