package X1;

import N1.C0420a;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.view.ContextMenu;
import android.view.View;
import androidx.lifecycle.A;
import androidx.lifecycle.C;
import androidx.lifecycle.EnumC0981t;
import androidx.lifecycle.InterfaceC0977o;
import androidx.lifecycle.J;
import androidx.lifecycle.a0;
import androidx.lifecycle.h0;
import androidx.lifecycle.k0;
import androidx.lifecycle.l0;
import b2.AbstractC0992b;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import r.C2257p;

/* JADX INFO: loaded from: classes.dex */
public abstract class f implements ComponentCallbacks, View.OnCreateContextMenuListener, A, l0, InterfaceC0977o, t3.e {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final Object f9978u = null;
    public final int l = -1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f9979m = UUID.randomUUID().toString();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final W2.j f9980n = new W2.j();
    public final boolean o = true;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final EnumC0981t f9981p = EnumC0981t.f12007p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public C f9982q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public t4.e f9983r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final ArrayList f9984s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final C0420a f9985t;

    public f() {
        new J();
        new AtomicInteger();
        this.f9984s = new ArrayList();
        this.f9985t = new C0420a(this);
        this.f9982q = new C(this);
        this.f9983r = new t4.e(this);
        ArrayList arrayList = this.f9984s;
        C0420a c0420a = this.f9985t;
        if (arrayList.contains(c0420a)) {
            return;
        }
        if (this.l < 0) {
            arrayList.add(c0420a);
            return;
        }
        f fVar = (f) c0420a.l;
        fVar.f9983r.k();
        a0.e(fVar);
    }

    @Override // t3.e
    public final C2257p b() {
        return (C2257p) this.f9983r.o;
    }

    public final W2.j c() {
        throw new IllegalStateException("Fragment " + this + " not associated with a fragment manager.");
    }

    @Override // androidx.lifecycle.InterfaceC0977o
    public final h0 d() {
        throw new IllegalStateException("Can't access ViewModels from detached fragment");
    }

    @Override // androidx.lifecycle.InterfaceC0977o
    public final AbstractC0992b e() {
        throw new IllegalStateException("Fragment " + this + " not attached to a context.");
    }

    @Override // androidx.lifecycle.l0
    public final k0 f() {
        throw new IllegalStateException("Can't access ViewModels from detached fragment");
    }

    @Override // androidx.lifecycle.A
    public final A9.b g() {
        return this.f9982q;
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
    }

    @Override // android.view.View.OnCreateContextMenuListener
    public final void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        throw new IllegalStateException("Fragment " + this + " not attached to an activity.");
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} (");
        sb.append(this.f9979m);
        sb.append(")");
        return sb.toString();
    }
}
