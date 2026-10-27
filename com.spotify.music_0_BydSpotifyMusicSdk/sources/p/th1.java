package p;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class th1 {
    public boolean c;
    public rh1 e;
    public final Object a = new Object();
    public final sh1 b = new sh1(this);
    public WeakReference d = new WeakReference(null);

    public final void a(uh1 uh1Var, rh1 rh1Var) {
        if (this.c) {
            this.c = false;
            rh1Var.removeMessages(1);
            PlaybackStateCompat playbackStateCompatA = uh1Var.a();
            long j = playbackStateCompatA == null ? 0L : playbackStateCompatA.m;
            boolean z = playbackStateCompatA != null && playbackStateCompatA.c == 3;
            boolean z2 = (516 & j) != 0;
            boolean z3 = (j & 514) != 0;
            if (z && z3) {
                f();
            } else {
                if (z || !z2) {
                    return;
                }
                g();
            }
        }
    }

    public void b(MediaDescriptionCompat mediaDescriptionCompat) {
    }

    public void c(MediaDescriptionCompat mediaDescriptionCompat) {
    }

    public void d(String str) {
    }

    public final boolean e(Intent intent) {
        uh1 uh1Var;
        rh1 rh1Var;
        KeyEvent keyEvent;
        if (Build.VERSION.SDK_INT >= 27) {
            return false;
        }
        synchronized (this.a) {
            uh1Var = (uh1) this.d.get();
            rh1Var = this.e;
        }
        if (uh1Var == null || rh1Var == null || (keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT")) == null || keyEvent.getAction() != 0) {
            return false;
        }
        gi1 gi1VarD = uh1Var.d();
        int keyCode = keyEvent.getKeyCode();
        if (keyCode != 79 && keyCode != 85) {
            a(uh1Var, rh1Var);
            return false;
        }
        if (keyEvent.getRepeatCount() != 0) {
            a(uh1Var, rh1Var);
        } else if (this.c) {
            rh1Var.removeMessages(1);
            this.c = false;
            PlaybackStateCompat playbackStateCompatA = uh1Var.a();
            if (((playbackStateCompatA == null ? 0L : playbackStateCompatA.m) & 32) != 0) {
                p();
            }
        } else {
            this.c = true;
            rh1Var.sendMessageDelayed(rh1Var.obtainMessage(1, gi1VarD), ViewConfiguration.getDoubleTapTimeout());
        }
        return true;
    }

    public void f() {
    }

    public void g() {
    }

    public void h(String str) {
    }

    public void i(String str, Bundle bundle) {
    }

    public void j(Uri uri, Bundle bundle) {
    }

    public void k(Uri uri, Bundle bundle) {
    }

    public void l(MediaDescriptionCompat mediaDescriptionCompat) {
    }

    public void m(long j) {
    }

    public void n(int i) {
    }

    public void o(int i) {
    }

    public void p() {
    }

    public void q() {
    }

    public void r(long j) {
    }

    public void s() {
    }

    public final void t(uh1 uh1Var, Handler handler) {
        synchronized (this.a) {
            try {
                this.d = new WeakReference(uh1Var);
                rh1 rh1Var = this.e;
                rh1 rh1Var2 = null;
                if (rh1Var != null) {
                    rh1Var.removeCallbacksAndMessages(null);
                }
                if (uh1Var != null && handler != null) {
                    rh1Var2 = new rh1(this, handler.getLooper(), 0);
                }
                this.e = rh1Var2;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
