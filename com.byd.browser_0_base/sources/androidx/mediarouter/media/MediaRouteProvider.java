package androidx.mediarouter.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.mediarouter.media.h;
import defpackage.rd1;
import defpackage.td1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public abstract class MediaRouteProvider {
    public final Context e;
    public final d f;
    public final c g;
    public a h;
    public rd1 i;
    public boolean j;
    public td1 k;
    public boolean l;

    public static abstract class a {
        public abstract void a(MediaRouteProvider mediaRouteProvider, td1 td1Var);
    }

    public static abstract class b extends e {
        public final Object a = new Object();
        public Executor b;
        public c c;
        public androidx.mediarouter.media.d d;
        public ArrayList e;

        public class a implements Runnable {
            public final /* synthetic */ c e;
            public final /* synthetic */ androidx.mediarouter.media.d f;
            public final /* synthetic */ Collection g;

            public a(c cVar, androidx.mediarouter.media.d dVar, Collection collection) {
                this.e = cVar;
                this.f = dVar;
                this.g = collection;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.e.c(b.this, this.f, this.g);
            }
        }

        /* JADX INFO: renamed from: androidx.mediarouter.media.MediaRouteProvider$b$b, reason: collision with other inner class name */
        public static final class C0013b {
            public final androidx.mediarouter.media.d a;
            public final int b;
            public final boolean c;
            public final boolean d;
            public final boolean e;
            public Bundle f;

            public C0013b(androidx.mediarouter.media.d dVar, int i, boolean z, boolean z2, boolean z3) {
                this.a = dVar;
                this.b = i;
                this.c = z;
                this.d = z2;
                this.e = z3;
            }
        }

        public interface c {
            void c(b bVar, androidx.mediarouter.media.d dVar, Collection<C0013b> collection);
        }

        public String k() {
            return null;
        }

        public String l() {
            return null;
        }

        public final void m(androidx.mediarouter.media.d dVar, ArrayList arrayList) {
            if (dVar == null) {
                throw new NullPointerException("groupRoute must not be null");
            }
            synchronized (this.a) {
                Executor executor = this.b;
                if (executor != null) {
                    executor.execute(new androidx.mediarouter.media.e(this, this.c, dVar, arrayList));
                } else {
                    this.d = dVar;
                    this.e = new ArrayList(arrayList);
                }
            }
        }

        public abstract void n(String str);

        public abstract void o(String str);

        public abstract void p(List<String> list);

        public final void q(Executor executor, c cVar) {
            synchronized (this.a) {
                if (executor == null) {
                    throw new NullPointerException("Executor shouldn't be null");
                }
                if (cVar == null) {
                    throw new NullPointerException("Listener shouldn't be null");
                }
                this.b = executor;
                this.c = cVar;
                ArrayList arrayList = this.e;
                if (arrayList != null && !arrayList.isEmpty()) {
                    androidx.mediarouter.media.d dVar = this.d;
                    ArrayList arrayList2 = this.e;
                    this.d = null;
                    this.e = null;
                    this.b.execute(new a(cVar, dVar, arrayList2));
                }
            }
        }
    }

    public final class c extends Handler {
        public c() {
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            int i = message.what;
            MediaRouteProvider mediaRouteProvider = MediaRouteProvider.this;
            if (i != 1) {
                if (i != 2) {
                    return;
                }
                mediaRouteProvider.j = false;
                mediaRouteProvider.o(mediaRouteProvider.i);
                return;
            }
            mediaRouteProvider.l = false;
            a aVar = mediaRouteProvider.h;
            if (aVar != null) {
                aVar.a(mediaRouteProvider, mediaRouteProvider.k);
            }
        }
    }

    public static final class d {
        public final ComponentName a;

        public d(ComponentName componentName) {
            this.a = componentName;
        }

        public final String toString() {
            return "ProviderMetadata{ componentName=" + this.a.flattenToShortString() + " }";
        }
    }

    public MediaRouteProvider(Context context) {
        this(context, null);
    }

    public b l(String str) {
        if (str != null) {
            return null;
        }
        throw new IllegalArgumentException("initialMemberRouteId cannot be null.");
    }

    public e m(String str) {
        if (str != null) {
            return null;
        }
        throw new IllegalArgumentException("routeId cannot be null");
    }

    public e n(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("routeId cannot be null");
        }
        if (str2 != null) {
            return m(str);
        }
        throw new IllegalArgumentException("routeGroupId cannot be null");
    }

    public final void p(td1 td1Var) {
        h.b();
        if (this.k != td1Var) {
            this.k = td1Var;
            if (this.l) {
                return;
            }
            this.l = true;
            this.g.sendEmptyMessage(1);
        }
    }

    public final void q(rd1 rd1Var) {
        h.b();
        if (Objects.equals(this.i, rd1Var)) {
            return;
        }
        this.i = rd1Var;
        if (this.j) {
            return;
        }
        this.j = true;
        this.g.sendEmptyMessage(2);
    }

    public MediaRouteProvider(Context context, d dVar) {
        this.g = new c();
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        this.e = context;
        if (dVar == null) {
            this.f = new d(new ComponentName(context, getClass()));
        } else {
            this.f = dVar;
        }
    }

    public static abstract class e {
        public boolean d(Intent intent, h.c cVar) {
            return false;
        }

        public void i(int i) {
            h();
        }

        public void e() {
        }

        public void f() {
        }

        @Deprecated
        public void h() {
        }

        public void g(int i) {
        }

        public void j(int i) {
        }
    }

    public void o(rd1 rd1Var) {
    }
}
