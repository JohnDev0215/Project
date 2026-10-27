package androidx.mediarouter.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.mediarouter.media.u;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public abstract class MediaRouteProvider {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f934a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c f935b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final b f936c;
    private a d;
    private q e;
    private boolean f;
    private r g;
    private boolean h;

    /* JADX INFO: compiled from: caradapter-bug */
    public static abstract class DynamicGroupRouteController extends d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Object f937a = new Object();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        @GuardedBy("mLock")
        Executor f938b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        @GuardedBy("mLock")
        c f939c;

        @GuardedBy("mLock")
        p d;

        @GuardedBy("mLock")
        Collection<DynamicRouteDescriptor> e;

        /* JADX INFO: compiled from: caradapter-bug */
        public static final class DynamicRouteDescriptor {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final p f940a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final int f941b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final boolean f942c;
            final boolean d;
            final boolean e;
            Bundle f;

            /* JADX INFO: compiled from: caradapter-bug */
            @Retention(RetentionPolicy.SOURCE)
            @RestrictTo({RestrictTo.Scope.LIBRARY})
            public @interface SelectionState {
            }

            DynamicRouteDescriptor(p pVar, int i, boolean z, boolean z2, boolean z3) {
                this.f940a = pVar;
                this.f941b = i;
                this.f942c = z;
                this.d = z2;
                this.e = z3;
            }

            static DynamicRouteDescriptor a(Bundle bundle) {
                if (bundle == null) {
                    return null;
                }
                return new DynamicRouteDescriptor(p.e(bundle.getBundle("mrDescriptor")), bundle.getInt("selectionState", 1), bundle.getBoolean("isUnselectable", false), bundle.getBoolean("isGroupable", false), bundle.getBoolean("isTransferable", false));
            }

            @NonNull
            public p b() {
                return this.f940a;
            }

            public int c() {
                return this.f941b;
            }

            public boolean d() {
                return this.d;
            }

            public boolean e() {
                return this.e;
            }

            public boolean f() {
                return this.f942c;
            }

            Bundle g() {
                if (this.f == null) {
                    Bundle bundle = new Bundle();
                    this.f = bundle;
                    bundle.putBundle("mrDescriptor", this.f940a.a());
                    this.f.putInt("selectionState", this.f941b);
                    this.f.putBoolean("isUnselectable", this.f942c);
                    this.f.putBoolean("isGroupable", this.d);
                    this.f.putBoolean("isTransferable", this.e);
                }
                return this.f;
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        class a implements Runnable {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ c f943c;
            final /* synthetic */ p d;
            final /* synthetic */ Collection e;

            a(c cVar, p pVar, Collection collection) {
                this.f943c = cVar;
                this.d = pVar;
                this.e = collection;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f943c.a(DynamicGroupRouteController.this, this.d, this.e);
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        class b implements Runnable {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ c f944c;
            final /* synthetic */ p d;
            final /* synthetic */ Collection e;

            b(c cVar, p pVar, Collection collection) {
                this.f944c = cVar;
                this.d = pVar;
                this.e = collection;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f944c.a(DynamicGroupRouteController.this, this.d, this.e);
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        interface c {
            void a(DynamicGroupRouteController dynamicGroupRouteController, p pVar, Collection<DynamicRouteDescriptor> collection);
        }

        @Nullable
        public String k() {
            return null;
        }

        @Nullable
        public String l() {
            return null;
        }

        public final void m(@NonNull p pVar, @NonNull Collection<DynamicRouteDescriptor> collection) {
            if (pVar == null) {
                throw new NullPointerException("groupRoute must not be null");
            }
            if (collection == null) {
                throw new NullPointerException("dynamicRoutes must not be null");
            }
            synchronized (this.f937a) {
                if (this.f938b != null) {
                    this.f938b.execute(new b(this.f939c, pVar, collection));
                } else {
                    this.d = pVar;
                    this.e = new ArrayList(collection);
                }
            }
        }

        public abstract void n(@NonNull String str);

        public abstract void o(String str);

        public abstract void p(@Nullable List<String> list);

        void q(@NonNull Executor executor, @NonNull c cVar) {
            synchronized (this.f937a) {
                if (executor == null) {
                    throw new NullPointerException("Executor shouldn't be null");
                }
                if (cVar == null) {
                    throw new NullPointerException("Listener shouldn't be null");
                }
                this.f938b = executor;
                this.f939c = cVar;
                if (this.e != null && !this.e.isEmpty()) {
                    p pVar = this.d;
                    Collection<DynamicRouteDescriptor> collection = this.e;
                    this.d = null;
                    this.e = null;
                    this.f938b.execute(new a(cVar, pVar, collection));
                }
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static abstract class a {
        public abstract void a(@NonNull MediaRouteProvider mediaRouteProvider, @Nullable r rVar);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private final class b extends Handler {
        b() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                MediaRouteProvider.this.l();
            } else {
                if (i != 2) {
                    return;
                }
                MediaRouteProvider.this.m();
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ComponentName f946a;

        c(ComponentName componentName) {
            if (componentName == null) {
                throw new IllegalArgumentException("componentName must not be null");
            }
            this.f946a = componentName;
        }

        public ComponentName a() {
            return this.f946a;
        }

        public String b() {
            return this.f946a.getPackageName();
        }

        public String toString() {
            return "ProviderMetadata{ componentName=" + this.f946a.flattenToShortString() + " }";
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static abstract class d {
        public boolean d(Intent intent, @Nullable u.d dVar) {
            return false;
        }

        public void e() {
        }

        public void f() {
        }

        public void g(int i) {
        }

        @Deprecated
        public void h() {
        }

        public void i(int i) {
            h();
        }

        public void j(int i) {
        }
    }

    public MediaRouteProvider(@NonNull Context context) {
        this(context, null);
    }

    void l() {
        this.h = false;
        a aVar = this.d;
        if (aVar != null) {
            aVar.a(this, this.g);
        }
    }

    void m() {
        this.f = false;
        v(this.e);
    }

    public final Context n() {
        return this.f934a;
    }

    @Nullable
    public final r o() {
        return this.g;
    }

    @Nullable
    public final q p() {
        return this.e;
    }

    public final Handler q() {
        return this.f936c;
    }

    public final c r() {
        return this.f935b;
    }

    @Nullable
    public DynamicGroupRouteController s(@NonNull String str) {
        if (str != null) {
            return null;
        }
        throw new IllegalArgumentException("initialMemberRouteId cannot be null.");
    }

    @Nullable
    public d t(@NonNull String str) {
        if (str != null) {
            return null;
        }
        throw new IllegalArgumentException("routeId cannot be null");
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public d u(@NonNull String str, @NonNull String str2) {
        if (str == null) {
            throw new IllegalArgumentException("routeId cannot be null");
        }
        if (str2 != null) {
            return t(str);
        }
        throw new IllegalArgumentException("routeGroupId cannot be null");
    }

    public void v(@Nullable q qVar) {
    }

    public final void w(@Nullable a aVar) {
        u.d();
        this.d = aVar;
    }

    public final void x(@Nullable r rVar) {
        u.d();
        if (this.g != rVar) {
            this.g = rVar;
            if (this.h) {
                return;
            }
            this.h = true;
            this.f936c.sendEmptyMessage(1);
        }
    }

    public final void y(q qVar) {
        u.d();
        if (androidx.core.d.a.a(this.e, qVar)) {
            return;
        }
        this.e = qVar;
        if (this.f) {
            return;
        }
        this.f = true;
        this.f936c.sendEmptyMessage(2);
    }

    MediaRouteProvider(Context context, c cVar) {
        this.f936c = new b();
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        this.f934a = context;
        if (cVar == null) {
            this.f935b = new c(new ComponentName(context, getClass()));
        } else {
            this.f935b = cVar;
        }
    }
}
