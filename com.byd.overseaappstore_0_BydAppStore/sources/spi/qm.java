package spi;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import androidx.annotation.CheckResult;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.b;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import spi.f4;

/* JADX INFO: loaded from: classes.dex */
public class qm implements ComponentCallbacks2, ug {
    public static final tm k = new tm().d(Bitmap.class).j();
    public final com.bumptech.glide.a a;
    public final Context b;
    public final sg c;

    @GuardedBy("this")
    public final um d;

    @GuardedBy("this")
    public final sm e;

    @GuardedBy("this")
    public final ss f;
    public final a g;
    public final f4 h;
    public final CopyOnWriteArrayList<pm<Object>> i;

    @GuardedBy("this")
    public tm j;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            qm qmVar = qm.this;
            qmVar.c.a(qmVar);
        }
    }

    public class b implements f4.a {

        @GuardedBy("RequestManager.this")
        public final um a;

        public b(@NonNull um umVar) {
            this.a = umVar;
        }

        @Override // spi.f4.a
        public final void a(boolean z) {
            if (z) {
                synchronized (qm.this) {
                    this.a.b();
                }
            }
        }
    }

    static {
        new tm().d(GifDrawable.class).j();
    }

    public qm(@NonNull com.bumptech.glide.a aVar, @NonNull sg sgVar, @NonNull sm smVar, @NonNull Context context) {
        this(aVar, sgVar, smVar, new um(), aVar.g, context);
    }

    /* JADX WARN: Type inference failed for: r0v10, types: [java.util.HashSet, java.util.Set<spi.jm>] */
    @Override // spi.ug
    public final synchronized void b() {
        this.f.b();
        Iterator it = ((ArrayList) tu.e(this.f.a)).iterator();
        while (it.hasNext()) {
            m((rs) it.next());
        }
        this.f.a.clear();
        um umVar = this.d;
        Iterator it2 = ((ArrayList) tu.e(umVar.a)).iterator();
        while (it2.hasNext()) {
            umVar.a((jm) it2.next());
        }
        umVar.b.clear();
        this.c.b(this);
        this.c.b(this.h);
        tu.f().removeCallbacks(this.g);
        this.a.d(this);
    }

    @NonNull
    @CheckResult
    public <ResourceType> nm<ResourceType> j(@NonNull Class<ResourceType> cls) {
        return new nm<>(this.a, this, cls, this.b);
    }

    @NonNull
    @CheckResult
    public nm<Bitmap> k() {
        return j(Bitmap.class).a(k);
    }

    @NonNull
    @CheckResult
    public nm<Drawable> l() {
        return j(Drawable.class);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.ArrayList, java.util.List<spi.qm>] */
    public final void m(@Nullable rs<?> rsVar) {
        boolean z;
        if (rsVar == null) {
            return;
        }
        boolean zS = s(rsVar);
        jm jmVarH = rsVar.h();
        if (zS) {
            return;
        }
        com.bumptech.glide.a aVar = this.a;
        synchronized (aVar.h) {
            Iterator it = aVar.h.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                } else if (((qm) it.next()).s(rsVar)) {
                    z = true;
                    break;
                }
            }
        }
        if (z || jmVarH == null) {
            return;
        }
        rsVar.e(null);
        jmVarH.clear();
    }

    @NonNull
    @CheckResult
    public nm<Drawable> n(@Nullable Drawable drawable) {
        return l().H(drawable);
    }

    @NonNull
    @CheckResult
    public nm<Drawable> o(@Nullable String str) {
        return l().J(str);
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }

    @Override // spi.ug
    public final synchronized void onStart() {
        q();
        this.f.onStart();
    }

    @Override // spi.ug
    public final synchronized void onStop() {
        p();
        this.f.onStop();
    }

    @Override // android.content.ComponentCallbacks2
    public final void onTrimMemory(int i) {
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [java.util.HashSet, java.util.Set<spi.jm>] */
    public final synchronized void p() {
        um umVar = this.d;
        umVar.c = true;
        for (jm jmVar : (ArrayList) tu.e(umVar.a)) {
            if (jmVar.isRunning()) {
                jmVar.pause();
                umVar.b.add(jmVar);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.HashSet, java.util.Set<spi.jm>] */
    public final synchronized void q() {
        um umVar = this.d;
        umVar.c = false;
        for (jm jmVar : (ArrayList) tu.e(umVar.a)) {
            if (!jmVar.j() && !jmVar.isRunning()) {
                jmVar.i();
            }
        }
        umVar.b.clear();
    }

    public synchronized void r(@NonNull tm tmVar) {
        this.j = tmVar.clone().b();
    }

    public final synchronized boolean s(@NonNull rs<?> rsVar) {
        jm jmVarH = rsVar.h();
        if (jmVarH == null) {
            return true;
        }
        if (!this.d.a(jmVarH)) {
            return false;
        }
        this.f.a.remove(rsVar);
        rsVar.e(null);
        return true;
    }

    public final synchronized String toString() {
        return super.toString() + "{tracker=" + this.d + ", treeNode=" + this.e + "}";
    }

    /* JADX WARN: Type inference failed for: r4v4, types: [java.util.ArrayList, java.util.List<spi.qm>] */
    /* JADX WARN: Type inference failed for: r5v3, types: [java.util.ArrayList, java.util.List<spi.qm>] */
    public qm(com.bumptech.glide.a aVar, sg sgVar, sm smVar, um umVar, g4 g4Var, Context context) {
        tm tmVar;
        this.f = new ss();
        a aVar2 = new a();
        this.g = aVar2;
        this.a = aVar;
        this.c = sgVar;
        this.e = smVar;
        this.d = umVar;
        this.b = context;
        Context applicationContext = context.getApplicationContext();
        b bVar = new b(umVar);
        Objects.requireNonNull((z5) g4Var);
        boolean z = ContextCompat.checkSelfPermission(applicationContext, "android.permission.ACCESS_NETWORK_STATE") == 0;
        if (Log.isLoggable("ConnectivityMonitor", 3)) {
            Log.d("ConnectivityMonitor", z ? "ACCESS_NETWORK_STATE permission granted, registering connectivity monitor" : "ACCESS_NETWORK_STATE permission missing, cannot register connectivity monitor");
        }
        f4 y5Var = z ? new y5(applicationContext, bVar) : new uj();
        this.h = y5Var;
        if (tu.h()) {
            tu.k(aVar2);
        } else {
            sgVar.a(this);
        }
        sgVar.a(y5Var);
        this.i = new CopyOnWriteArrayList<>(aVar.c.e);
        com.bumptech.glide.c cVar = aVar.c;
        synchronized (cVar) {
            if (cVar.j == null) {
                Objects.requireNonNull((b.a) cVar.d);
                tm tmVar2 = new tm();
                tmVar2.t = true;
                cVar.j = tmVar2;
            }
            tmVar = cVar.j;
        }
        r(tmVar);
        synchronized (aVar.h) {
            if (aVar.h.contains(this)) {
                throw new IllegalStateException("Cannot register already registered manager");
            }
            aVar.h.add(this);
        }
    }
}
