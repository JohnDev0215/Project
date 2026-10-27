package androidx.lifecycle;

import android.os.Looper;
import android.util.Log;
import android.view.View;
import java.util.Map;
import java.util.Objects;
import p.eb;
import p.m62;
import p.n62;
import p.p01;
import p.q62;
import p.rb1;
import p.rv;
import p.s90;
import p.s91;
import p.sb1;
import p.u4;

/* JADX INFO: loaded from: classes.dex */
public class b {
    public static final Object k = new Object();
    public final Object a = new Object();
    public final q62 b = new q62();
    public int c = 0;
    public boolean d;
    public volatile Object e;
    public volatile Object f;
    public int g;
    public boolean h;
    public boolean i;
    public final rv j;

    public b() {
        Object obj = k;
        this.f = obj;
        this.j = new rv(8, this);
        this.e = obj;
        this.g = -1;
    }

    public static void a(String str) {
        eb.w().f.getClass();
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException(u4.j("Cannot invoke ", str, " on a background thread"));
        }
    }

    public final void b(sb1 sb1Var) {
        if (sb1Var.e) {
            if (!sb1Var.g()) {
                sb1Var.c(false);
                return;
            }
            int i = sb1Var.k;
            int i2 = this.g;
            if (i >= i2) {
                return;
            }
            sb1Var.k = i2;
            p01 p01Var = sb1Var.c;
            Object obj = this.e;
            p01Var.getClass();
            if (((s91) obj) != null) {
                s90 s90Var = (s90) p01Var.e;
                if (s90Var.i0) {
                    View viewC = s90Var.C();
                    if (viewC.getParent() != null) {
                        throw new IllegalStateException("DialogFragment can not be attached to a container view");
                    }
                    if (((s90) p01Var.e).m0 != null) {
                        if (Log.isLoggable("FragmentManager", 3)) {
                            p01Var.toString();
                            Objects.toString(((s90) p01Var.e).m0);
                        }
                        ((s90) p01Var.e).m0.setContentView(viewC);
                    }
                }
            }
        }
    }

    public final void c(sb1 sb1Var) {
        if (this.h) {
            this.i = true;
            return;
        }
        this.h = true;
        do {
            this.i = false;
            if (sb1Var != null) {
                b(sb1Var);
                sb1Var = null;
            } else {
                q62 q62Var = this.b;
                q62Var.getClass();
                n62 n62Var = new n62(q62Var);
                q62Var.k.put(n62Var, Boolean.FALSE);
                while (n62Var.hasNext()) {
                    b((sb1) ((Map.Entry) n62Var.next()).getValue());
                    if (this.i) {
                        break;
                    }
                }
            }
        } while (this.i);
        this.h = false;
    }

    public final void d(p01 p01Var) {
        Object obj;
        a("observeForever");
        rb1 rb1Var = new rb1(this, p01Var);
        q62 q62Var = this.b;
        m62 m62VarA = q62Var.a(p01Var);
        if (m62VarA != null) {
            obj = m62VarA.e;
        } else {
            m62 m62Var = new m62(p01Var, rb1Var);
            q62Var.l++;
            m62 m62Var2 = q62Var.e;
            if (m62Var2 == null) {
                q62Var.c = m62Var;
                q62Var.e = m62Var;
            } else {
                m62Var2.k = m62Var;
                m62Var.l = m62Var2;
                q62Var.e = m62Var;
            }
            obj = null;
        }
        sb1 sb1Var = (sb1) obj;
        if (sb1Var instanceof LiveData$LifecycleBoundObserver) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (sb1Var != null) {
            return;
        }
        rb1Var.c(true);
    }

    public final void e(Object obj) {
        a("setValue");
        this.g++;
        this.e = obj;
        c(null);
    }
}
