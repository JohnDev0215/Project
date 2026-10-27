package A2;

import K.L0;
import T.C0741g;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.Spatializer;
import android.net.Uri;
import android.opengl.Matrix;
import android.text.TextUtils;
import e2.AbstractC1199g;
import e2.C1195c;
import e2.C1208p;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import io.sentry.AbstractC1451b;
import io.sentry.C1480k1;
import io.sentry.F1;
import io.sentry.K;
import io.sentry.P1;
import io.sentry.T1;
import io.sentry.a2;
import io.sentry.protocol.C;
import io.sentry.protocol.C1498c;
import io.sentry.protocol.E;
import io.sentry.protocol.t;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import q2.q;
import q2.r;
import q2.x;
import r9.C2316f;
import r9.H;
import r9.z;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f310a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f311b;
    public final Object c;
    public Object d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f312e;

    public c(int i7) {
        this.f310a = i7;
        switch (i7) {
            case 2:
                this.c = new Object();
                this.d = new ArrayList();
                this.f312e = new ArrayList();
                this.f311b = true;
                break;
            default:
                this.c = new float[16];
                this.d = new float[16];
                this.f312e = new g(6, (byte) 0);
                break;
        }
    }

    public static void e(float[] fArr, float[] fArr2) {
        Matrix.setIdentityM(fArr, 0);
        float f10 = fArr2[10];
        float f11 = fArr2[8];
        float fSqrt = (float) Math.sqrt((f11 * f11) + (f10 * f10));
        float f12 = fArr2[10] / fSqrt;
        fArr[0] = f12;
        float f13 = fArr2[8];
        fArr[2] = f13 / fSqrt;
        fArr[8] = (-f13) / fSqrt;
        fArr[10] = f12;
    }

    public static c j(C1480k1 c1480k1, F1 f12) {
        c cVar = new c(f12.getLogger());
        C1498c c1498c = c1480k1.f15978m;
        T1 t1B = c1498c.b();
        cVar.o("sentry-trace_id", t1B != null ? t1B.l.toString() : null);
        cVar.o("sentry-public_key", f12.retrieveParsedDsn().f16818b);
        cVar.o("sentry-release", c1480k1.f15981q);
        cVar.o("sentry-environment", c1480k1.f15982r);
        E e10 = c1480k1.f15984t;
        cVar.o("sentry-user_segment", e10 != null ? m(e10) : null);
        cVar.o("sentry-transaction", c1480k1.f16602G);
        cVar.o("sentry-sample_rate", null);
        cVar.o("sentry-sampled", null);
        V v10 = c1498c.get("replay_id");
        if (v10 != 0 && !v10.toString().equals(t.f16774m.toString())) {
            cVar.o("sentry-replay_id", v10.toString());
            c1498c.remove("replay_id");
        }
        cVar.f311b = false;
        return cVar;
    }

    public static String m(E e10) {
        String str = e10.o;
        if (str != null) {
            return str;
        }
        ConcurrentHashMap concurrentHashMap = e10.f16661s;
        if (concurrentHashMap != null) {
            return (String) concurrentHashMap.get("segment");
        }
        return null;
    }

    public void a() {
        switch (this.f310a) {
            case 3:
                c4.c.b((c4.c) this.f312e, this, false);
                return;
            default:
                e9.g gVar = (e9.g) this.f312e;
                synchronized (gVar) {
                    try {
                        if (this.f311b) {
                            throw new IllegalStateException("Check failed.");
                        }
                        if (A7.m.a(((e9.d) this.c).f14499g, this)) {
                            gVar.g(this, false);
                        }
                        this.f311b = true;
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return;
        }
    }

    public boolean b(C1195c c1195c, C1208p c1208p) {
        boolean zEquals = Objects.equals(c1208p.f14316n, "audio/eac3-joc");
        int i7 = c1208p.f14295C;
        if (!zEquals) {
            String str = c1208p.f14316n;
            if (Objects.equals(str, "audio/iamf")) {
                if (i7 == -1) {
                    i7 = 6;
                }
            } else if (Objects.equals(str, "audio/ac4") && (i7 == 18 || i7 == 21)) {
                i7 = 24;
            }
        } else if (i7 == 16) {
            i7 = 12;
        }
        int iP = AbstractC1343A.p(i7);
        if (iP == 0) {
            return false;
        }
        AudioFormat.Builder channelMask = new AudioFormat.Builder().setEncoding(2).setChannelMask(iP);
        int i10 = c1208p.f14296D;
        if (i10 != -1) {
            channelMask.setSampleRate(i10);
        }
        return ((Spatializer) this.c).canBeSpatialized((AudioAttributes) c1195c.b().f13998a, channelMask.build());
    }

    public void c() {
        e9.g gVar = (e9.g) this.f312e;
        synchronized (gVar) {
            try {
                if (this.f311b) {
                    throw new IllegalStateException("Check failed.");
                }
                if (A7.m.a(((e9.d) this.c).f14499g, this)) {
                    gVar.g(this, true);
                }
                this.f311b = true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void d(boolean z6) {
        C3.e eVar = (C3.e) this.f312e;
        synchronized (eVar) {
            try {
                if (this.f311b) {
                    throw new IllegalStateException("editor is closed");
                }
                if (A7.m.a(((C3.b) this.c).f975g, this)) {
                    C3.e.b(eVar, this, z6);
                }
                this.f311b = true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void f() {
        e9.d dVar = (e9.d) this.c;
        if (A7.m.a(dVar.f14499g, this)) {
            e9.g gVar = (e9.g) this.f312e;
            if (gVar.f14522v) {
                gVar.g(this, false);
            } else {
                dVar.f14498f = true;
            }
        }
    }

    public byte[] g(UUID uuid, q qVar) throws x {
        String str = qVar.f21215b;
        if (this.f311b || TextUtils.isEmpty(str)) {
            str = (String) this.d;
        }
        if (TextUtils.isEmpty(str)) {
            Map mapEmptyMap = Collections.emptyMap();
            Uri uri = Uri.EMPTY;
            AbstractC1346c.l(uri, "The uri must be set.");
            new k2.m(uri, 0L, 1, null, mapEmptyMap, 0L, -1L, null, 0);
            throw new x(new IllegalStateException("No license URL"));
        }
        HashMap map = new HashMap();
        UUID uuid2 = AbstractC1199g.f14101e;
        map.put("Content-Type", uuid2.equals(uuid) ? "text/xml" : AbstractC1199g.c.equals(uuid) ? "application/json" : "application/octet-stream");
        if (uuid2.equals(uuid)) {
            map.put("SOAPAction", "http://schemas.microsoft.com/DRM/2007/03/protocols/AcquireLicense");
        }
        synchronized (((HashMap) this.f312e)) {
            map.putAll((HashMap) this.f312e);
        }
        return h6.n.k(((L0) this.c).q(), str, qVar.f21214a, map);
    }

    public byte[] h(r rVar) {
        return h6.n.k(((L0) this.c).q(), rVar.f21217b + "&signedRequest=" + AbstractC1343A.m(rVar.f21216a), null, Collections.emptyMap());
    }

    public z i(int i7) {
        z zVar;
        C3.e eVar = (C3.e) this.f312e;
        synchronized (eVar) {
            if (this.f311b) {
                throw new IllegalStateException("editor is closed");
            }
            ((boolean[]) this.d)[i7] = true;
            Object obj = ((C3.b) this.c).d.get(i7);
            C3.c cVar = eVar.f979A;
            z zVar2 = (z) obj;
            if (!cVar.e(zVar2)) {
                N3.d.a(cVar.j(zVar2));
            }
            zVar = (z) obj;
        }
        return zVar;
    }

    public String k(String str) {
        return (String) ((HashMap) this.c).get(str);
    }

    public File l() {
        File file;
        synchronized (((c4.c) this.f312e)) {
            try {
                c4.b bVar = (c4.b) this.c;
                if (bVar.f12381f != this) {
                    throw new IllegalStateException();
                }
                if (!bVar.f12380e) {
                    ((boolean[]) this.d)[0] = true;
                }
                file = bVar.d[0];
                ((c4.c) this.f312e).l.mkdirs();
            } catch (Throwable th) {
                throw th;
            }
        }
        return file;
    }

    public H n(int i7) {
        e9.g gVar = (e9.g) this.f312e;
        synchronized (gVar) {
            try {
                if (this.f311b) {
                    throw new IllegalStateException("Check failed.");
                }
                if (!A7.m.a(((e9.d) this.c).f14499g, this)) {
                    return new C2316f();
                }
                if (!((e9.d) this.c).f14497e) {
                    boolean[] zArr = (boolean[]) this.d;
                    A7.m.c(zArr);
                    zArr[i7] = true;
                }
                try {
                    return new C3.f(k9.a.f18322a.e((File) ((e9.d) this.c).d.get(i7)), new C0741g(gVar, 11, this));
                } catch (FileNotFoundException unused) {
                    return new C2316f();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void o(String str, String str2) {
        if (this.f311b) {
            ((HashMap) this.c).put(str, str2);
        }
    }

    public void p(P1 p12, E e10, t tVar, F1 f12, B8.t tVar2) {
        o("sentry-trace_id", p12.f15911b.c.l.toString());
        o("sentry-public_key", f12.retrieveParsedDsn().f16818b);
        o("sentry-release", f12.getRelease());
        o("sentry-environment", f12.getEnvironment());
        o("sentry-user_segment", e10 != null ? m(e10) : null);
        C c = p12.f15920n;
        o("sentry-transaction", (c == null || C.URL.equals(c)) ? null : p12.f15912e);
        if (tVar != null && !t.f16774m.equals(tVar)) {
            o("sentry-replay_id", tVar.toString());
        }
        Double d = tVar2 == null ? null : (Double) tVar2.o;
        o("sentry-sample_rate", !h6.i.w(d, false) ? null : new DecimalFormat("#.################", DecimalFormatSymbols.getInstance(Locale.ROOT)).format(d));
        Boolean bool = tVar2 == null ? null : (Boolean) tVar2.f808n;
        o("sentry-sampled", bool != null ? bool.toString() : null);
    }

    public a2 q() {
        String strK = k("sentry-trace_id");
        String strK2 = k("sentry-replay_id");
        String strK3 = k("sentry-public_key");
        if (strK == null || strK3 == null) {
            return null;
        }
        a2 a2Var = new a2(new t(strK), strK3, k("sentry-release"), k("sentry-environment"), k("sentry-user_id"), k("sentry-user_segment"), k("sentry-transaction"), k("sentry-sample_rate"), k("sentry-sampled"), strK2 == null ? null : new t(strK2));
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        for (Map.Entry entry : ((HashMap) this.c).entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            if (!AbstractC1451b.f16474a.contains(str) && str2 != null) {
                concurrentHashMap.put(str.replaceFirst("sentry-", ""), str2);
            }
        }
        a2Var.f16012v = concurrentHashMap;
        return a2Var;
    }

    public c(String str, boolean z6, L0 l02) {
        this.f310a = 6;
        AbstractC1346c.c((z6 && TextUtils.isEmpty(str)) ? false : true);
        this.c = l02;
        this.d = str;
        this.f311b = z6;
        this.f312e = new HashMap();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public c(K k10) {
        this(new HashMap(), null, true, k10);
        this.f310a = 5;
    }

    public c(HashMap map, String str, boolean z6, K k10) {
        this.f310a = 5;
        this.c = map;
        this.f312e = k10;
        this.f311b = z6;
        this.d = str;
    }

    public c(A4.i iVar, t4.d dVar) {
        this.f310a = 7;
        this.f312e = new J3.f(this, 1);
        this.d = iVar;
        this.c = dVar;
    }

    public c(C3.e eVar, C3.b bVar) {
        this.f310a = 1;
        this.f312e = eVar;
        this.c = bVar;
        eVar.getClass();
        this.d = new boolean[2];
    }

    public c(c4.c cVar, c4.b bVar) {
        this.f310a = 3;
        this.f312e = cVar;
        this.c = bVar;
        this.d = bVar.f12380e ? null : new boolean[cVar.f12387r];
    }

    public c(e9.g gVar, e9.d dVar) {
        boolean[] zArr;
        this.f310a = 4;
        this.f312e = gVar;
        this.c = dVar;
        if (dVar.f14497e) {
            zArr = null;
        } else {
            gVar.getClass();
            zArr = new boolean[2];
        }
        this.d = zArr;
    }

    public c(Spatializer spatializer) {
        this.f310a = 8;
        this.c = spatializer;
        this.f311b = spatializer.getImmersiveAudioLevel() != 0;
    }
}
