package k2;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class n implements h {
    public final Context l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayList f17682m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final h f17683n;
    public t o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public C1601b f17684p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public C1604e f17685q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public h f17686r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public G f17687s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public C1605f f17688t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public B f17689u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public h f17690v;

    public n(Context context, h hVar) {
        this.l = context.getApplicationContext();
        hVar.getClass();
        this.f17683n = hVar;
        this.f17682m = new ArrayList();
    }

    public static void p(h hVar, E e10) {
        if (hVar != null) {
            hVar.b(e10);
        }
    }

    @Override // k2.h
    public final void b(E e10) {
        e10.getClass();
        this.f17683n.b(e10);
        this.f17682m.add(e10);
        p(this.o, e10);
        p(this.f17684p, e10);
        p(this.f17685q, e10);
        p(this.f17686r, e10);
        p(this.f17687s, e10);
        p(this.f17688t, e10);
        p(this.f17689u, e10);
    }

    @Override // k2.h
    public final void close() {
        h hVar = this.f17690v;
        if (hVar != null) {
            try {
                hVar.close();
            } finally {
                this.f17690v = null;
            }
        }
    }

    public final void e(h hVar) {
        int i7 = 0;
        while (true) {
            ArrayList arrayList = this.f17682m;
            if (i7 >= arrayList.size()) {
                return;
            }
            hVar.b((E) arrayList.get(i7));
            i7++;
        }
    }

    @Override // k2.h
    public final Map f() {
        h hVar = this.f17690v;
        return hVar == null ? Collections.emptyMap() : hVar.f();
    }

    @Override // k2.h
    public final long g(m mVar) {
        AbstractC1346c.j(this.f17690v == null);
        String scheme = mVar.f17675a.getScheme();
        int i7 = AbstractC1343A.f15195a;
        Uri uri = mVar.f17675a;
        String scheme2 = uri.getScheme();
        boolean zIsEmpty = TextUtils.isEmpty(scheme2);
        Context context = this.l;
        if (zIsEmpty || "file".equals(scheme2)) {
            String path = uri.getPath();
            if (path == null || !path.startsWith("/android_asset/")) {
                if (this.o == null) {
                    t tVar = new t(false);
                    this.o = tVar;
                    e(tVar);
                }
                this.f17690v = this.o;
            } else {
                if (this.f17684p == null) {
                    C1601b c1601b = new C1601b(context);
                    this.f17684p = c1601b;
                    e(c1601b);
                }
                this.f17690v = this.f17684p;
            }
        } else if ("asset".equals(scheme)) {
            if (this.f17684p == null) {
                C1601b c1601b2 = new C1601b(context);
                this.f17684p = c1601b2;
                e(c1601b2);
            }
            this.f17690v = this.f17684p;
        } else if ("content".equals(scheme)) {
            if (this.f17685q == null) {
                C1604e c1604e = new C1604e(context);
                this.f17685q = c1604e;
                e(c1604e);
            }
            this.f17690v = this.f17685q;
        } else {
            boolean zEquals = "rtmp".equals(scheme);
            h hVar = this.f17683n;
            if (zEquals) {
                if (this.f17686r == null) {
                    try {
                        h hVar2 = (h) Class.forName("androidx.media3.datasource.rtmp.RtmpDataSource").getConstructor(null).newInstance(null);
                        this.f17686r = hVar2;
                        e(hVar2);
                    } catch (ClassNotFoundException unused) {
                        AbstractC1346c.z("DefaultDataSource", "Attempting to play RTMP stream without depending on the RTMP extension");
                    } catch (Exception e10) {
                        throw new RuntimeException("Error instantiating RTMP extension", e10);
                    }
                    if (this.f17686r == null) {
                        this.f17686r = hVar;
                    }
                }
                this.f17690v = this.f17686r;
            } else if ("udp".equals(scheme)) {
                if (this.f17687s == null) {
                    G g7 = new G();
                    this.f17687s = g7;
                    e(g7);
                }
                this.f17690v = this.f17687s;
            } else if ("data".equals(scheme)) {
                if (this.f17688t == null) {
                    C1605f c1605f = new C1605f(false);
                    this.f17688t = c1605f;
                    e(c1605f);
                }
                this.f17690v = this.f17688t;
            } else if ("rawresource".equals(scheme) || "android.resource".equals(scheme)) {
                if (this.f17689u == null) {
                    B b10 = new B(context);
                    this.f17689u = b10;
                    e(b10);
                }
                this.f17690v = this.f17689u;
            } else {
                this.f17690v = hVar;
            }
        }
        return this.f17690v.g(mVar);
    }

    @Override // k2.h
    public final Uri j() {
        h hVar = this.f17690v;
        if (hVar == null) {
            return null;
        }
        return hVar.j();
    }

    @Override // e2.InterfaceC1201i
    public final int n(byte[] bArr, int i7, int i10) {
        h hVar = this.f17690v;
        hVar.getClass();
        return hVar.n(bArr, i7, i10);
    }
}
