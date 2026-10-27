package spi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import spi.f4;

/* JADX INFO: loaded from: classes.dex */
public final class pp {
    public static volatile pp d;
    public final c a;

    @GuardedBy("this")
    public final Set<f4.a> b = new HashSet();

    @GuardedBy("this")
    public boolean c;

    public class a implements rd<ConnectivityManager> {
        public final /* synthetic */ Context a;

        public a(pp ppVar, Context context) {
            this.a = context;
        }

        @Override // spi.rd
        public final ConnectivityManager get() {
            return (ConnectivityManager) this.a.getSystemService("connectivity");
        }
    }

    public class b implements f4.a {
        public b() {
        }

        @Override // spi.f4.a
        public final void a(boolean z) {
            ArrayList arrayList;
            synchronized (pp.this) {
                arrayList = new ArrayList(pp.this.b);
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((f4.a) it.next()).a(z);
            }
        }
    }

    @RequiresApi(24)
    public static final class c {
        public boolean a;
        public final f4.a b;
        public final rd<ConnectivityManager> c;
        public final a d = new a();

        public class a extends ConnectivityManager.NetworkCallback {
            public a() {
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public final void onAvailable(@NonNull Network network) {
                tu.k(new qp(this, true));
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public final void onLost(@NonNull Network network) {
                tu.k(new qp(this, false));
            }
        }

        public c(rd<ConnectivityManager> rdVar, f4.a aVar) {
            this.c = rdVar;
            this.b = aVar;
        }
    }

    private pp(@NonNull Context context) {
        this.a = new c(new qd(new a(this, context)), new b());
    }

    public static pp a(@NonNull Context context) {
        if (d == null) {
            synchronized (pp.class) {
                if (d == null) {
                    d = new pp(context.getApplicationContext());
                }
            }
        }
        return d;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.HashSet, java.util.Set<spi.f4$a>] */
    @GuardedBy("this")
    public final void b() {
        if (this.c || this.b.isEmpty()) {
            return;
        }
        c cVar = this.a;
        boolean z = true;
        cVar.a = cVar.c.get().getActiveNetwork() != null;
        try {
            cVar.c.get().registerDefaultNetworkCallback(cVar.d);
        } catch (RuntimeException e) {
            if (Log.isLoggable("ConnectivityMonitor", 5)) {
                Log.w("ConnectivityMonitor", "Failed to register callback", e);
            }
            z = false;
        }
        this.c = z;
    }
}
