package a6;

import android.os.Handler;
import android.os.Looper;
import g5.r;
import k5.g;
import kotlin.jvm.internal.m;
import kotlinx.coroutines.i;
import kotlinx.coroutines.v0;
import r5.l;
import v5.h;

/* JADX INFO: compiled from: HandlerDispatcher.kt */
/* JADX INFO: loaded from: classes.dex */
public final class a extends a6.b {
    private volatile a _immediate;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final a f129g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final Handler f130h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final String f131i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final boolean f132j;

    /* JADX INFO: renamed from: a6.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: HandlerDispatcher.kt */
    public static final class C0003a implements v0 {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ Runnable f134g;

        C0003a(Runnable runnable) {
            this.f134g = runnable;
        }

        @Override // kotlinx.coroutines.v0
        public void dispose() {
            a.this.f130h.removeCallbacks(this.f134g);
        }
    }

    /* JADX INFO: compiled from: Runnable.kt */
    public static final class b implements Runnable {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ i f136g;

        public b(i iVar) {
            this.f136g = iVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            this.f136g.h(a.this, r.f6563a);
        }
    }

    /* JADX INFO: compiled from: HandlerDispatcher.kt */
    static final class c extends m implements l<Throwable, r> {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ Runnable f138g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(Runnable runnable) {
            super(1);
            this.f138g = runnable;
        }

        @Override // r5.l
        public /* bridge */ /* synthetic */ r invoke(Throwable th) {
            invoke2(th);
            return r.f6563a;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Throwable th) {
            a.this.f130h.removeCallbacks(this.f138g);
        }
    }

    private a(Handler handler, String str, boolean z6) {
        super(null);
        this.f130h = handler;
        this.f131i = str;
        this.f132j = z6;
        this._immediate = z6 ? this : null;
        a aVar = this._immediate;
        if (aVar == null) {
            aVar = new a(handler, str, true);
            this._immediate = aVar;
            r rVar = r.f6563a;
        }
        this.f129g = aVar;
    }

    @Override // kotlinx.coroutines.q0
    public void A(long j7, i<? super r> iVar) {
        b bVar = new b(iVar);
        this.f130h.postDelayed(bVar, h.e(j7, 4611686018427387903L));
        iVar.d(new c(bVar));
    }

    @Override // a6.b, kotlinx.coroutines.q0
    public v0 N(long j7, Runnable runnable, g gVar) {
        this.f130h.postDelayed(runnable, h.e(j7, 4611686018427387903L));
        return new C0003a(runnable);
    }

    @Override // kotlinx.coroutines.e0
    public void a0(g gVar, Runnable runnable) {
        this.f130h.post(runnable);
    }

    @Override // kotlinx.coroutines.e0
    public boolean c0(g gVar) {
        return !this.f132j || (kotlin.jvm.internal.l.a(Looper.myLooper(), this.f130h.getLooper()) ^ true);
    }

    public boolean equals(Object obj) {
        return (obj instanceof a) && ((a) obj).f130h == this.f130h;
    }

    @Override // kotlinx.coroutines.v1
    /* JADX INFO: renamed from: g0, reason: merged with bridge method [inline-methods] */
    public a d0() {
        return this.f129g;
    }

    public int hashCode() {
        return System.identityHashCode(this.f130h);
    }

    @Override // kotlinx.coroutines.v1, kotlinx.coroutines.e0
    public String toString() {
        String strE0 = e0();
        if (strE0 != null) {
            return strE0;
        }
        String string = this.f131i;
        if (string == null) {
            string = this.f130h.toString();
        }
        if (!this.f132j) {
            return string;
        }
        return string + ".immediate";
    }

    public /* synthetic */ a(Handler handler, String str, int i7, kotlin.jvm.internal.g gVar) {
        this(handler, (i7 & 2) != 0 ? null : str);
    }

    public a(Handler handler, String str) {
        this(handler, str, false);
    }
}
