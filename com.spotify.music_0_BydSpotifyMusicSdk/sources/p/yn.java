package p;

import android.support.v4.media.MediaBrowserCompat$MediaItem;
import com.fasterxml.jackson.annotation.JsonFormat$Value;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.cfg.MapperConfigBase;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.base.Optional;
import com.google.firebase.components.ComponentRegistrar;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class yn implements io.reactivex.rxjava3.functions.o, io.reactivex.rxjava3.functions.g, xo1, sl, i13, im1, kc2, cw {
    public static final yn e = new yn(0);
    public static final yn k = new yn(1);
    public static final yn l = new yn(0);
    public static final yn m = new yn(1);
    public final /* synthetic */ int c;

    public /* synthetic */ yn(int i) {
        this.c = i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:426:0x085c, code lost:
    
        r10 = r10 + 1;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003f A[PHI: r4
  0x003f: PHI (r4v2 com.google.zxing.qrcode.decoder.ErrorCorrectionLevel) = 
  (r4v1 com.google.zxing.qrcode.decoder.ErrorCorrectionLevel)
  (r4v52 com.google.zxing.qrcode.decoder.ErrorCorrectionLevel)
 binds: [B:7:0x0014, B:12:0x0030] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x028d  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:530:0x00b4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x010a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static p.yl m(java.lang.String r36, int r37, int r38, java.util.Map r39) {
        /*
            Method dump skipped, instruction units count: 2717
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.yn.m(java.lang.String, int, int, java.util.Map):p.yl");
    }

    public static String n(String str) {
        return (str.startsWith("lib") && str.endsWith(".so")) ? str : System.mapLibraryName(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void o(p.io r7, java.lang.String r8) {
        /*
            java.lang.String r0 = "sink"
            p.v03.j(r7, r0)
            java.lang.String r0 = "value"
            p.v03.j(r8, r0)
            java.lang.String[] r0 = p.jo.f140p
            r1 = 34
            r7.R(r1)
            int r2 = r8.length()
            r3 = 0
            r4 = r3
        L17:
            if (r3 >= r2) goto L40
            char r5 = r8.charAt(r3)
            r6 = 128(0x80, float:1.8E-43)
            if (r5 >= r6) goto L26
            r5 = r0[r5]
            if (r5 != 0) goto L33
            goto L3d
        L26:
            r6 = 8232(0x2028, float:1.1535E-41)
            if (r5 != r6) goto L2d
            java.lang.String r5 = "\\u2028"
            goto L33
        L2d:
            r6 = 8233(0x2029, float:1.1537E-41)
            if (r5 != r6) goto L3d
            java.lang.String r5 = "\\u2029"
        L33:
            if (r4 >= r3) goto L38
            r7.r(r8, r4, r3)
        L38:
            r7.s0(r5)
            int r4 = r3 + 1
        L3d:
            int r3 = r3 + 1
            goto L17
        L40:
            if (r4 >= r2) goto L45
            r7.r(r8, r4, r2)
        L45:
            r7.R(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p.yn.o(p.io, java.lang.String):void");
    }

    public static kh1 s(String str) {
        v03.j(str, "uri");
        return new kh1(str);
    }

    @Override // p.i13
    public /* synthetic */ Object a() {
        switch (this.c) {
            case 14:
                return new g13();
            default:
                ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor(q23.c);
                ci1.M(executorServiceNewSingleThreadExecutor);
                return executorServiceNewSingleThreadExecutor;
        }
    }

    @Override // io.reactivex.rxjava3.functions.g
    public /* bridge */ /* synthetic */ void accept(Object obj) {
        switch (this.c) {
            case 0:
                l((Throwable) obj);
                break;
            default:
                l((Throwable) obj);
                break;
        }
    }

    @Override // io.reactivex.rxjava3.functions.o
    public Object apply(Object obj) {
        switch (this.c) {
            case 0:
                return (MediaBrowserCompat$MediaItem) ((Optional) obj).c();
            default:
                return new tx1();
        }
    }

    @Override // p.im1
    public File b() {
        return null;
    }

    @Override // p.sl
    public PropertyName c() {
        return PropertyName.e;
    }

    @Override // p.sl
    public JsonFormat$Value d(MapperConfigBase mapperConfigBase, Class cls) {
        return JsonFormat$Value.e;
    }

    @Override // p.im1
    public File e() {
        return null;
    }

    @Override // p.cw
    public List f(ComponentRegistrar componentRegistrar) {
        ArrayList arrayList = new ArrayList();
        for (mv mvVar : componentRegistrar.getComponents()) {
            String str = mvVar.a;
            if (str != null) {
                mvVar = new mv(str, mvVar.b, mvVar.c, mvVar.d, mvVar.e, new f91(1, mvVar, str), mvVar.g);
            }
            arrayList.add(mvVar);
        }
        return arrayList;
    }

    @Override // p.kc2
    public cc2 g(yn ynVar, JSONObject jSONObject) throws JSONException {
        long jCurrentTimeMillis;
        jSONObject.optInt("settings_version", 0);
        int iOptInt = jSONObject.optInt("cache_duration", 3600);
        double dOptDouble = jSONObject.optDouble("on_demand_upload_rate_per_minute", 10.0d);
        double dOptDouble2 = jSONObject.optDouble("on_demand_backoff_base", 1.2d);
        int iOptInt2 = jSONObject.optInt("on_demand_backoff_step_duration_seconds", 60);
        sm1 sm1Var = jSONObject.has("session") ? new sm1(jSONObject.getJSONObject("session").optInt("max_custom_exception_events", 8), 4, 2) : new sm1(new JSONObject().optInt("max_custom_exception_events", 8), 4, 2);
        JSONObject jSONObject2 = jSONObject.getJSONObject("features");
        ac2 ac2Var = new ac2(jSONObject2.optBoolean("collect_reports", true), jSONObject2.optBoolean("collect_anrs", false), jSONObject2.optBoolean("collect_build_ids", false));
        long j = iOptInt;
        if (jSONObject.has("expires_at")) {
            jCurrentTimeMillis = jSONObject.optLong("expires_at");
        } else {
            ynVar.getClass();
            jCurrentTimeMillis = (j * 1000) + System.currentTimeMillis();
        }
        return new cc2(jCurrentTimeMillis, sm1Var, ac2Var, dOptDouble, dOptDouble2, iOptInt2);
    }

    @Override // p.sl
    public PropertyMetadata getMetadata() {
        return PropertyMetadata.l;
    }

    @Override // p.sl
    public JavaType getType() {
        return TypeFactory.a();
    }

    @Override // p.im1
    public File h() {
        return null;
    }

    @Override // p.im1
    public m30 i() {
        return null;
    }

    @Override // p.sl
    public AnnotatedMember j() {
        return null;
    }

    @Override // p.xo1
    public Object k() {
        switch (this.c) {
            case 0:
                return new TreeMap();
            case 1:
                return new TreeSet();
            default:
                return new ArrayList();
        }
    }

    public void l(Throwable th) {
        switch (this.c) {
            case 0:
                hk0 hk0VarA = qk2.a();
                th.getMessage();
                hk0VarA.getClass();
                hk0.B(th);
                break;
            default:
                qk2.a().getClass();
                hk0.u(th);
                break;
        }
    }

    @Override // p.im1
    public File p() {
        return null;
    }

    @Override // p.im1
    public File q() {
        return null;
    }

    @Override // p.im1
    public File r() {
        return null;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ yn(int i, int i2) {
        this(0);
        this.c = i;
        int i3 = 23;
        if (i == 23) {
            this(i3);
            return;
        }
        int i4 = 25;
        if (i == 25) {
            this(i4);
            return;
        }
        switch (i) {
            case 1:
                this(1);
                break;
            case 2:
                this(2);
                break;
            case 3:
                this(3);
                break;
            case 4:
                this(4);
                break;
            case 5:
                this(5);
                break;
            case 6:
                this(6);
                break;
            case 7:
                this(7);
                break;
            case 8:
                this(8);
                break;
            case 9:
                this(9);
                break;
            case 10:
                this(10);
                break;
            case 11:
                this(11);
                break;
            default:
                switch (i) {
                    case 27:
                        this(27);
                        break;
                    case 28:
                        this(28);
                        break;
                    case 29:
                        this(29);
                        break;
                    default:
                        break;
                }
                break;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ yn(Object obj) {
        this(17);
        this.c = 17;
    }
}
