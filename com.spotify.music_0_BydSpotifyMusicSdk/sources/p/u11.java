package p;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.SystemClock;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Lifecycle$Event;
import com.spotify.base.java.logging.Logger;
import com.spotify.player.esperanto.proto.EsResponseWithReasons$ResponseWithReasons;
import com.spotify.rcs.resolver.grpc.v0.Configuration;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleTransformer;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import okhttp3.RequestBody;
import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public class u11 implements nj1, fy1, lg, h00, io.reactivex.rxjava3.functions.g, io.reactivex.rxjava3.functions.o, xt, io.reactivex.rxjava3.functions.b, io.reactivex.rxjava3.functions.c, wp1, wc1, uv2, l11, SingleTransformer, io.reactivex.rxjava3.functions.d {
    public static u11 c;
    public static Field e;
    public static boolean k;
    public static z02 t;
    public static final u11 l = new u11();
    public static final u11 m = new u11();
    public static final u11 n = new u11();
    public static final u11 o = new u11();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final u11 f193p = new u11();
    public static final u11 q = new u11();
    public static final u11 r = new u11();
    public static final u11 s = new u11();
    public static final u11 u = new u11();
    public static final /* synthetic */ u11 v = new u11();

    public static os1 A(File file) {
        String str = os1.e;
        v03.j(file, "<this>");
        String string = file.toString();
        v03.i(string, "toString(...)");
        return z(string, false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x004b, code lost:
    
        if (java.lang.Character.isHighSurrogate(r5) != false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0088, code lost:
    
        if (java.lang.Character.isLowSurrogate(r5) != false) goto L60;
     */
    /* JADX WARN: Removed duplicated region for block: B:106:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0072 A[EDGE_INSN: B:91:0x0072->B:48:0x0072 BREAK  A[LOOP:2: B:49:0x0074->B:60:0x008b, LOOP_LABEL: LOOP:2: B:49:0x0074->B:60:0x008b], EDGE_INSN: B:94:0x0072->B:48:0x0072 BREAK  A[LOOP:2: B:49:0x0074->B:60:0x008b]] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00a8 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean E(android.view.inputmethod.InputConnection r7, android.text.Editable r8, int r9, int r10, boolean r11) {
        /*
            Method dump skipped, instruction units count: 246
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.u11.E(android.view.inputmethod.InputConnection, android.text.Editable, int, int, boolean):boolean");
    }

    public static void F(Activity activity) {
        v03.j(activity, "activity");
        if (Build.VERSION.SDK_INT >= 29) {
            i32.Companion.getClass();
            activity.registerActivityLifecycleCallbacks(new i32());
        }
        FragmentManager fragmentManager = activity.getFragmentManager();
        if (fragmentManager.findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
            fragmentManager.beginTransaction().add(new j32(), "androidx.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            fragmentManager.executePendingTransactions();
        }
    }

    public static final boolean j(StringBuilder sb, String str, Object obj, boolean z) {
        if (obj != null) {
            if (z) {
                sb.append(",");
            }
            sb.append(str + '=' + obj);
        } else {
            obj = null;
        }
        return obj != null;
    }

    public static final void l(uc ucVar, long j, boolean z) {
        uc ucVar2;
        ReentrantLock reentrantLock = uc.h;
        if (uc.l == null) {
            uc.l = new uc();
            rc rcVar = new rc("Okio Watchdog");
            rcVar.setDaemon(true);
            rcVar.start();
        }
        long jNanoTime = System.nanoTime();
        if (j != 0 && z) {
            ucVar.g = Math.min(j, ucVar.c() - jNanoTime) + jNanoTime;
        } else if (j != 0) {
            ucVar.g = j + jNanoTime;
        } else {
            if (!z) {
                throw new AssertionError();
            }
            ucVar.g = ucVar.c();
        }
        long j2 = ucVar.g - jNanoTime;
        uc ucVar3 = uc.l;
        v03.h(ucVar3);
        while (true) {
            ucVar2 = ucVar3.f;
            if (ucVar2 == null || j2 < ucVar2.g - jNanoTime) {
                break;
            }
            v03.h(ucVar2);
            ucVar3 = ucVar2;
        }
        ucVar.f = ucVar2;
        ucVar3.f = ucVar;
        if (ucVar3 == uc.l) {
            uc.i.signal();
        }
    }

    public static final boolean m(os1 os1Var) {
        os1 os1Var2 = okio.internal.a.e;
        os1Var.getClass();
        ByteString byteString = f.a;
        ByteString byteStringQ = os1Var.c;
        int iM = ByteString.m(byteStringQ, byteString);
        if (iM == -1) {
            iM = ByteString.m(byteStringQ, f.b);
        }
        if (iM != -1) {
            byteStringQ = ByteString.q(byteStringQ, iM + 1, 0, 2);
        } else if (os1Var.g() != null && byteStringQ.e() == 2) {
            byteStringQ = ByteString.k;
        }
        return !ng2.a0(byteStringQ.s(), ".class", true);
    }

    public static uc p() throws InterruptedException {
        uc ucVar = uc.l;
        v03.h(ucVar);
        uc ucVar2 = ucVar.f;
        if (ucVar2 == null) {
            long jNanoTime = System.nanoTime();
            uc.i.await(uc.j, TimeUnit.MILLISECONDS);
            uc ucVar3 = uc.l;
            v03.h(ucVar3);
            if (ucVar3.f != null || System.nanoTime() - jNanoTime < uc.k) {
                return null;
            }
            return uc.l;
        }
        long jNanoTime2 = ucVar2.g - System.nanoTime();
        if (jNanoTime2 > 0) {
            uc.i.await(jNanoTime2, TimeUnit.NANOSECONDS);
            return null;
        }
        uc ucVar4 = uc.l;
        v03.h(ucVar4);
        ucVar4.f = ucVar2.f;
        ucVar2.f = null;
        ucVar2.e = 2;
        return ucVar2;
    }

    public static void q(hz1 hz1Var, Object[] objArr) {
        if (objArr == null) {
            return;
        }
        int length = objArr.length;
        int i = 0;
        while (i < length) {
            Object obj = objArr[i];
            i++;
            if (obj == null) {
                hz1Var.K(i);
            } else if (obj instanceof byte[]) {
                hz1Var.B0((byte[]) obj, i);
            } else if (obj instanceof Float) {
                hz1Var.b(i, ((Number) obj).floatValue());
            } else if (obj instanceof Double) {
                hz1Var.b(i, ((Number) obj).doubleValue());
            } else if (obj instanceof Long) {
                hz1Var.h0(i, ((Number) obj).longValue());
            } else if (obj instanceof Integer) {
                hz1Var.h0(i, ((Number) obj).intValue());
            } else if (obj instanceof Short) {
                hz1Var.h0(i, ((Number) obj).shortValue());
            } else if (obj instanceof Byte) {
                hz1Var.h0(i, ((Number) obj).byteValue());
            } else if (obj instanceof String) {
                hz1Var.x(i, (String) obj);
            } else {
                if (!(obj instanceof Boolean)) {
                    throw new IllegalArgumentException("Cannot bind " + obj + " at index " + i + " Supported types: Null, ByteArray, Float, Double, Long, Int, Short, Byte, String");
                }
                hz1Var.h0(i, ((Boolean) obj).booleanValue() ? 1L : 0L);
            }
        }
    }

    public static dp2 r() {
        dp2 dp2Var = new dp2();
        dp2Var.d = new HashMap();
        return dp2Var;
    }

    public static void t(int i, int i2, int i3) {
        if (i >= 0 && i2 <= i3) {
            if (i > i2) {
                throw new IllegalArgumentException(hf2.c("fromIndex: ", i, " > toIndex: ", i2));
            }
            return;
        }
        throw new IndexOutOfBoundsException("fromIndex: " + i + ", toIndex: " + i2 + ", size: " + i3);
    }

    public static boolean w(String str, String str2) {
        v03.j(str, "current");
        if (v03.b(str, str2)) {
            return true;
        }
        if (str.length() != 0) {
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (true) {
                if (i < str.length()) {
                    char cCharAt = str.charAt(i);
                    int i4 = i3 + 1;
                    if (i3 == 0 && cCharAt != '(') {
                        break;
                    }
                    if (cCharAt != '(') {
                        if (cCharAt == ')' && i2 - 1 == 0 && i3 != str.length() - 1) {
                            break;
                        }
                    } else {
                        i2++;
                    }
                    i++;
                    i3 = i4;
                } else if (i2 == 0) {
                    String strSubstring = str.substring(1, str.length() - 1);
                    v03.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                    return v03.b(kotlin.text.b.G0(strSubstring).toString(), str2);
                }
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void x(Activity activity, Lifecycle$Event lifecycle$Event) {
        v03.j(activity, "activity");
        v03.j(lifecycle$Event, "event");
        if (activity instanceof s91) {
            l91 l91VarL = ((s91) activity).l();
            if (l91VarL instanceof androidx.lifecycle.a) {
                ((androidx.lifecycle.a) l91VarL).f(lifecycle$Event);
            }
        }
    }

    public static os1 z(String str, boolean z) {
        v03.j(str, "<this>");
        ByteString byteString = f.a;
        fo foVar = new fo();
        foVar.d1(str);
        return f.d(foVar, z);
    }

    public InputFilter[] B(InputFilter[] inputFilterArr) {
        return inputFilterArr;
    }

    public ProviderInfo C(ResolveInfo resolveInfo) {
        throw new IllegalStateException("Unable to get provider info prior to API 19");
    }

    public Signature[] D(PackageManager packageManager, String str) {
        return packageManager.getPackageInfo(str, 64).signatures;
    }

    public List G(PackageManager packageManager, Intent intent) {
        return Collections.emptyList();
    }

    public void H(boolean z) {
    }

    public void I(boolean z) {
    }

    public void J(ww1 ww1Var, float f) {
        o52 o52Var = (o52) ((Drawable) ww1Var.e);
        boolean useCompatPadding = ((CardView) ww1Var.k).getUseCompatPadding();
        boolean preventCornerOverlap = ((CardView) ww1Var.k).getPreventCornerOverlap();
        if (f != o52Var.e || o52Var.f != useCompatPadding || o52Var.g != preventCornerOverlap) {
            o52Var.e = f;
            o52Var.f = useCompatPadding;
            o52Var.g = preventCornerOverlap;
            o52Var.b(null);
            o52Var.invalidateSelf();
        }
        if (!((CardView) ww1Var.k).getUseCompatPadding()) {
            ww1Var.z(0, 0, 0, 0);
            return;
        }
        o52 o52Var2 = (o52) ((Drawable) ww1Var.e);
        float f2 = o52Var2.e;
        float f3 = o52Var2.a;
        int iCeil = (int) Math.ceil(p52.a(f2, f3, ((CardView) ww1Var.k).getPreventCornerOverlap()));
        int iCeil2 = (int) Math.ceil(p52.b(f2, f3, ((CardView) ww1Var.k).getPreventCornerOverlap()));
        ww1Var.z(iCeil, iCeil2, iCeil, iCeil2);
    }

    public void K(View view, int i) {
        if (!k) {
            try {
                Field declaredField = View.class.getDeclaredField("mViewFlags");
                e = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
            }
            k = true;
        }
        Field field = e;
        if (field != null) {
            try {
                e.setInt(view, i | (field.getInt(view) & (-13)));
            } catch (IllegalAccessException unused2) {
            }
        }
    }

    @Override // p.uv2
    public void a(int i) {
    }

    @Override // io.reactivex.rxjava3.functions.g
    public void accept(Object obj) {
        Logger.c((Throwable) obj, "Failed to get partner id.", new Object[0]);
    }

    @Override // io.reactivex.rxjava3.functions.o
    public Object apply(Object obj) {
        EsResponseWithReasons$ResponseWithReasons esResponseWithReasons$ResponseWithReasons = (EsResponseWithReasons$ResponseWithReasons) obj;
        v03.j(esResponseWithReasons$ResponseWithReasons, "p0");
        return yu.a(esResponseWithReasons$ResponseWithReasons);
    }

    @Override // p.nj1
    public void b(si1 si1Var, boolean z) {
    }

    @Override // p.uv2
    public ni1 c(mi1 mi1Var) {
        return null;
    }

    @Override // p.h00
    public Object convert(Object obj) {
        return (RequestBody) obj;
    }

    @Override // p.wp1
    public void d(Exception exc) {
        tz2 tz2Var = m23.e;
        m23.e.f(String.format("Could not sync active asset packs. %s", exc), new Object[0]);
    }

    @Override // io.reactivex.rxjava3.core.SingleTransformer
    public Single e(Single single) {
        v03.j(single, "upstream");
        Single singleRetryWhen = single.retryWhen(new ux1(12, this));
        v03.i(singleRetryWhen, "override fun apply(upstr…              }\n        }");
        return singleRetryWhen;
    }

    @Override // p.l11
    public boolean f() {
        boolean z;
        synchronized (au0.a) {
            try {
                int i = au0.c;
                au0.c = i + 1;
                if (i >= 30 || SystemClock.uptimeMillis() > au0.d + ((long) 30000)) {
                    au0.c = 0;
                    au0.d = SystemClock.uptimeMillis();
                    String[] list = au0.b.list();
                    if (list == null) {
                        list = new String[0];
                    }
                    au0.e = list.length < 800;
                }
                z = au0.e;
            } catch (Throwable th) {
                throw th;
            }
        }
        return z;
    }

    @Override // p.uv2
    public void g(mi1 mi1Var, Bitmap bitmap, Map map, int i) {
    }

    @Override // p.lg
    public void h(androidx.sqlite.db.framework.b bVar) {
        bVar.w("UPDATE WorkSpec SET `last_enqueue_time` = -1 WHERE `last_enqueue_time` = 0");
    }

    @Override // p.l11
    public boolean i(qd2 qd2Var) {
        ci1 ci1Var = qd2Var.a;
        if (!(ci1Var instanceof t90) || ((t90) ci1Var).f187p > 100) {
            ci1 ci1Var2 = qd2Var.b;
            if (!(ci1Var2 instanceof t90) || ((t90) ci1Var2).f187p > 100) {
                return true;
            }
        }
        return false;
    }

    @Override // io.reactivex.rxjava3.functions.c
    public Object k(Object obj, Object obj2) {
        Map map = (Map) obj;
        Map map2 = (Map) obj2;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        v03.i(map, "previous");
        linkedHashMap.putAll(map);
        v03.i(map2, "update");
        linkedHashMap.putAll(map2);
        return linkedHashMap;
    }

    @Override // p.fy1
    public void n() {
    }

    @Override // p.fy1
    public void o(int i, Object obj) {
        String str;
        switch (i) {
            case 1:
                str = "RESULT_INSTALL_SUCCESS";
                break;
            case 2:
                str = "RESULT_ALREADY_INSTALLED";
                break;
            case 3:
                str = "RESULT_UNSUPPORTED_ART_VERSION";
                break;
            case 4:
                str = "RESULT_NOT_WRITABLE";
                break;
            case 5:
                str = "RESULT_DESIRED_FORMAT_UNSUPPORTED";
                break;
            case 6:
                str = "RESULT_BASELINE_PROFILE_NOT_FOUND";
                break;
            case 7:
                str = "RESULT_IO_EXCEPTION";
                break;
            case 8:
                str = "RESULT_PARSE_EXCEPTION";
                break;
            case 9:
            default:
                str = "";
                break;
            case 10:
                str = "RESULT_INSTALL_SKIP_FILE_SUCCESS";
                break;
            case 11:
                str = "RESULT_DELETE_SKIP_FILE_SUCCESS";
                break;
        }
        if (i == 6 || i == 7 || i == 8) {
            Log.e("ProfileInstaller", str, (Throwable) obj);
        }
    }

    @Override // p.nj1
    public boolean s(si1 si1Var) {
        return false;
    }

    public void u(zd0 zd0Var) {
        sf sfVar = sf.a;
        g71 g71Var = (g71) zd0Var;
        g71Var.a(g40.class, sfVar);
        g71Var.a(bh.class, sfVar);
        yf yfVar = yf.a;
        g71Var.a(f40.class, yfVar);
        g71Var.a(jh.class, yfVar);
        vf vfVar = vf.a;
        g71Var.a(r30.class, vfVar);
        g71Var.a(kh.class, vfVar);
        wf wfVar = wf.a;
        g71Var.a(q30.class, wfVar);
        g71Var.a(lh.class, wfVar);
        kg kgVar = kg.a;
        g71Var.a(e40.class, kgVar);
        g71Var.a(yh.class, kgVar);
        jg jgVar = jg.a;
        g71Var.a(d40.class, jgVar);
        g71Var.a(xh.class, jgVar);
        xf xfVar = xf.a;
        g71Var.a(s30.class, xfVar);
        g71Var.a(mh.class, xfVar);
        hg hgVar = hg.a;
        g71Var.a(c40.class, hgVar);
        g71Var.a(nh.class, hgVar);
        zf zfVar = zf.a;
        g71Var.a(z30.class, zfVar);
        g71Var.a(oh.class, zfVar);
        bg bgVar = bg.a;
        g71Var.a(y30.class, bgVar);
        g71Var.a(ph.class, bgVar);
        eg egVar = eg.a;
        g71Var.a(x30.class, egVar);
        g71Var.a(th.class, egVar);
        fg fgVar = fg.a;
        g71Var.a(w30.class, fgVar);
        g71Var.a(uh.class, fgVar);
        cg cgVar = cg.a;
        g71Var.a(u30.class, cgVar);
        g71Var.a(rh.class, cgVar);
        qf qfVar = qf.a;
        g71Var.a(m30.class, qfVar);
        g71Var.a(dh.class, qfVar);
        pf pfVar = pf.a;
        g71Var.a(l30.class, pfVar);
        g71Var.a(eh.class, pfVar);
        dg dgVar = dg.a;
        g71Var.a(v30.class, dgVar);
        g71Var.a(sh.class, dgVar);
        ag agVar = ag.a;
        g71Var.a(t30.class, agVar);
        g71Var.a(qh.class, agVar);
        rf rfVar = rf.a;
        g71Var.a(n30.class, rfVar);
        g71Var.a(fh.class, rfVar);
        gg ggVar = gg.a;
        g71Var.a(a40.class, ggVar);
        g71Var.a(vh.class, ggVar);
        ig igVar = ig.a;
        g71Var.a(b40.class, igVar);
        g71Var.a(wh.class, igVar);
        tf tfVar = tf.a;
        g71Var.a(p30.class, tfVar);
        g71Var.a(gh.class, tfVar);
        uf ufVar = uf.a;
        g71Var.a(o30.class, ufVar);
        g71Var.a(hh.class, ufVar);
    }

    public void v(String str) {
        v03.j(str, "message");
    }

    public void y(String str, Throwable th) {
        v03.j(th, "e");
        v03.j(str, "message");
        Log.e("EventSender", str, th);
    }

    @Override // io.reactivex.rxjava3.functions.b
    public void accept(Object obj, Object obj2) {
        String strF = ((Configuration) obj).f();
        if (strF == null || ng2.c0(strF)) {
            throw new IllegalStateException("invalid data from RCR".toString());
        }
    }
}
