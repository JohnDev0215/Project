package androidx.activity;

import android.os.Build;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.lifecycle.d;
import androidx.lifecycle.f;
import androidx.lifecycle.g;
import defpackage.b71;
import defpackage.i21;
import defpackage.j8;
import defpackage.vi3;
import defpackage.xr1;
import defpackage.yl;
import defpackage.yq0;
import defpackage.yr1;
import defpackage.z41;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: compiled from: OnBackPressedDispatcher.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nOnBackPressedDispatcher.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OnBackPressedDispatcher.kt\nandroidx/activity/OnBackPressedDispatcher\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,317:1\n1747#2,3:318\n533#2,6:321\n*S KotlinDebug\n*F\n+ 1 OnBackPressedDispatcher.kt\nandroidx/activity/OnBackPressedDispatcher\n*L\n194#1:318,3\n209#1:321,6\n*E\n"})
public final class OnBackPressedDispatcher {
    public final Runnable a;
    public final j8<xr1> b = new j8<>();
    public final a c;
    public final yr1 d;
    public OnBackInvokedDispatcher e;
    public boolean f;

    /* JADX INFO: compiled from: OnBackPressedDispatcher.kt */
    public final class LifecycleOnBackPressedCancellable implements f, yl {
        public final androidx.lifecycle.d e;
        public final xr1 f;
        public d g;
        public final /* synthetic */ OnBackPressedDispatcher h;

        public LifecycleOnBackPressedCancellable(OnBackPressedDispatcher onBackPressedDispatcher, androidx.lifecycle.d dVar, xr1 xr1Var) {
            i21.e(xr1Var, "onBackPressedCallback");
            this.h = onBackPressedDispatcher;
            this.e = dVar;
            this.f = xr1Var;
            dVar.a(this);
        }

        @Override // androidx.lifecycle.f
        public final void a(b71 b71Var, d.a aVar) {
            if (aVar == d.a.ON_START) {
                this.g = this.h.b(this.f);
                return;
            }
            if (aVar != d.a.ON_STOP) {
                if (aVar == d.a.ON_DESTROY) {
                    cancel();
                }
            } else {
                d dVar = this.g;
                if (dVar != null) {
                    dVar.cancel();
                }
            }
        }

        @Override // defpackage.yl
        public final void cancel() {
            this.e.b(this);
            xr1 xr1Var = this.f;
            xr1Var.getClass();
            xr1Var.b.remove(this);
            d dVar = this.g;
            if (dVar != null) {
                dVar.cancel();
            }
            this.g = null;
        }
    }

    /* JADX INFO: compiled from: OnBackPressedDispatcher.kt */
    public static final class a extends z41 implements yq0<vi3> {
        public a() {
            super(0);
        }

        @Override // defpackage.yq0
        public final vi3 a() {
            OnBackPressedDispatcher.this.d();
            return vi3.a;
        }
    }

    /* JADX INFO: compiled from: OnBackPressedDispatcher.kt */
    public static final class b extends z41 implements yq0<vi3> {
        public b() {
            super(0);
        }

        @Override // defpackage.yq0
        public final vi3 a() {
            OnBackPressedDispatcher.this.c();
            return vi3.a;
        }
    }

    /* JADX INFO: compiled from: OnBackPressedDispatcher.kt */
    public static final class c {
        public static void a(Object obj, yr1 yr1Var) {
            ((OnBackInvokedDispatcher) obj).registerOnBackInvokedCallback(0, yr1Var);
        }

        public static void b(Object obj, yr1 yr1Var) {
            ((OnBackInvokedDispatcher) obj).unregisterOnBackInvokedCallback(yr1Var);
        }
    }

    /* JADX INFO: compiled from: OnBackPressedDispatcher.kt */
    public final class d implements yl {
        public final xr1 e;
        public final /* synthetic */ OnBackPressedDispatcher f;

        public d(OnBackPressedDispatcher onBackPressedDispatcher, xr1 xr1Var) {
            i21.e(xr1Var, "onBackPressedCallback");
            this.f = onBackPressedDispatcher;
            this.e = xr1Var;
        }

        @Override // defpackage.yl
        public final void cancel() {
            OnBackPressedDispatcher onBackPressedDispatcher = this.f;
            j8<xr1> j8Var = onBackPressedDispatcher.b;
            xr1 xr1Var = this.e;
            j8Var.remove(xr1Var);
            xr1Var.getClass();
            xr1Var.b.remove(this);
            if (Build.VERSION.SDK_INT >= 33) {
                xr1Var.c = null;
                onBackPressedDispatcher.d();
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [yr1] */
    public OnBackPressedDispatcher(Runnable runnable) {
        this.a = runnable;
        if (Build.VERSION.SDK_INT >= 33) {
            this.c = new a();
            final b bVar = new b();
            this.d = new OnBackInvokedCallback() { // from class: yr1
                @Override // android.window.OnBackInvokedCallback
                public final void onBackInvoked() {
                    yq0 yq0Var = bVar;
                    i21.e(yq0Var, "$onBackInvoked");
                    yq0Var.a();
                }
            };
        }
    }

    public final void a(b71 b71Var, xr1 xr1Var) {
        i21.e(b71Var, "owner");
        i21.e(xr1Var, "onBackPressedCallback");
        g gVarJ1 = b71Var.j1();
        if (gVarJ1.c == d.b.DESTROYED) {
            return;
        }
        xr1Var.b.add(new LifecycleOnBackPressedCancellable(this, gVarJ1, xr1Var));
        if (Build.VERSION.SDK_INT >= 33) {
            d();
            xr1Var.c = this.c;
        }
    }

    public final d b(xr1 xr1Var) {
        i21.e(xr1Var, "onBackPressedCallback");
        this.b.addLast(xr1Var);
        d dVar = new d(this, xr1Var);
        xr1Var.b.add(dVar);
        if (Build.VERSION.SDK_INT >= 33) {
            d();
            xr1Var.c = this.c;
        }
        return dVar;
    }

    public final void c() {
        xr1 xr1VarPrevious;
        j8<xr1> j8Var = this.b;
        ListIterator<xr1> listIterator = j8Var.listIterator(j8Var.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                xr1VarPrevious = null;
                break;
            } else {
                xr1VarPrevious = listIterator.previous();
                if (xr1VarPrevious.a) {
                    break;
                }
            }
        }
        xr1 xr1Var = xr1VarPrevious;
        if (xr1Var != null) {
            xr1Var.a();
            return;
        }
        Runnable runnable = this.a;
        if (runnable != null) {
            runnable.run();
        }
    }

    public final void d() {
        boolean z;
        yr1 yr1Var;
        j8<xr1> j8Var = this.b;
        if ((j8Var instanceof Collection) && j8Var.isEmpty()) {
            z = false;
        } else {
            Iterator<xr1> it = j8Var.iterator();
            while (it.hasNext()) {
                if (it.next().a) {
                    z = true;
                    break;
                }
            }
            z = false;
        }
        OnBackInvokedDispatcher onBackInvokedDispatcher = this.e;
        if (onBackInvokedDispatcher == null || (yr1Var = this.d) == null) {
            return;
        }
        if (z && !this.f) {
            c.a(onBackInvokedDispatcher, yr1Var);
            this.f = true;
        } else {
            if (z || !this.f) {
                return;
            }
            c.b(onBackInvokedDispatcher, yr1Var);
            this.f = false;
        }
    }
}
