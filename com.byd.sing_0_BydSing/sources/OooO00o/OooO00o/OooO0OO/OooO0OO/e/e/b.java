package OooO00o.OooO00o.OooO0Oo.OooO0Oo.e.e;

import OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.f;
import OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.g;
import OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.j;
import OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.k;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public class b implements g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public j f146a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public f f147b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b f148c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ArrayList<k> f149d = new ArrayList<>();

    public static final class a implements OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ OooO00o.OooO00o.OooO0Oo.OooO0Oo.e.c.a f151b;

        /* JADX INFO: renamed from: OooO00o.OooO00o.OooO0Oo.OooO0Oo.e.e.b$a$a, reason: collision with other inner class name */
        public static final class C0012a implements OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ b f152a;

            public C0012a(b bVar) {
                this.f152a = bVar;
            }

            @Override // OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b
            public void onError(String str) {
                if (str != null) {
                    OooO00o.OooO00o.OooO0O0.b.f41a.d("NetProxy", str);
                }
                OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVarF = this.f152a.f();
                if (bVarF == null) {
                    return;
                }
                bVarF.onError(str);
            }

            @Override // OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b
            public void onSuccess(String str) {
                OooO00o.OooO00o.OooO0O0.b.f41a.e("NetProxy", h.i(str, " activate success"));
                OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVarF = this.f152a.f();
                if (bVarF != null) {
                    bVarF.onSuccess(str);
                }
                Iterator<T> it = this.f152a.h().iterator();
                while (it.hasNext()) {
                    ((k) it.next()).a();
                }
            }
        }

        public a(OooO00o.OooO00o.OooO0Oo.OooO0Oo.e.c.a aVar) {
            this.f151b = aVar;
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b
        public void onError(String str) {
            OooO00o.OooO00o.OooO0O0.b.f41a.d("NetProxy", h.i("get token error:", str));
            b.this.g();
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b
        public void onSuccess(String str) {
            b.this.g();
            f fVarB = b.this.b();
            if (fVarB == null) {
                return;
            }
            fVarB.b(this.f151b.a(), new C0012a(b.this));
        }
    }

    @Override // OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.g
    public void a(OooO00o.OooO00o.OooO0Oo.OooO0Oo.e.c.a aVar) {
        h.d(aVar, "device");
        j jVar = this.f146a;
        if (jVar == null) {
            return;
        }
        jVar.c(new a(aVar));
    }

    public final f b() {
        return this.f147b;
    }

    public final void c(OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVar) {
        this.f148c = bVar;
    }

    public final void d(f fVar) {
        this.f147b = fVar;
    }

    public final void e(j jVar) {
        this.f146a = jVar;
    }

    public final OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b f() {
        return this.f148c;
    }

    public final OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b g() {
        return null;
    }

    public final ArrayList<k> h() {
        return this.f149d;
    }
}
