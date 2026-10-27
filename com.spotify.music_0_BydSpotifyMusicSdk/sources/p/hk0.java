package p;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.behavior.SwipeDismissBehavior;
import com.google.firebase.components.ComponentDiscoveryService;
import com.spotify.automotive.baseui.login.AAMLoginPasswordActivity;
import com.spotify.automotive.ubi.LoginUbiLogger$Page;
import com.spotify.connectivity.httpwebgate.WebgateHelper;
import io.reactivex.rxjava3.core.Observable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class hk0 implements dz, wi1, qi1, s4, p3, dq1, q6, ah2, zj, nc1, lp {
    public final /* synthetic */ int c;
    public Object e;

    public /* synthetic */ hk0(int i, Object obj) {
        this.c = i;
        this.e = obj;
    }

    public static void A(Object... objArr) {
        for (hk0 hk0Var : qk2.a) {
            hk0Var.getClass();
            A(objArr);
        }
    }

    public static void B(Object... objArr) {
        for (hk0 hk0Var : qk2.a) {
            hk0Var.getClass();
            B(objArr);
        }
    }

    public static void C(Object... objArr) {
        for (hk0 hk0Var : qk2.a) {
            hk0Var.getClass();
            C(objArr);
        }
    }

    public static void t(Object... objArr) {
        for (hk0 hk0Var : qk2.a) {
            hk0Var.getClass();
            t(objArr);
        }
    }

    public static void u(Object... objArr) {
        for (hk0 hk0Var : qk2.a) {
            hk0Var.getClass();
            u(objArr);
        }
    }

    public static void v(Object... objArr) {
        for (hk0 hk0Var : qk2.a) {
            hk0Var.getClass();
            v(objArr);
        }
    }

    public static Object w(Object[] objArr, int i, wo2 wo2Var) {
        int i2 = (i & 1) == 0 ? 400 : 700;
        boolean z = (i & 2) != 0;
        Object obj = null;
        int i3 = Integer.MAX_VALUE;
        for (Object obj2 : objArr) {
            int iAbs = (Math.abs(wo2Var.k(obj2) - i2) * 2) + (wo2Var.l(obj2) == z ? 0 : 1);
            if (obj == null || i3 > iAbs) {
                obj = obj2;
                i3 = iAbs;
            }
        }
        return obj;
    }

    public static void y(Object... objArr) {
        for (hk0 hk0Var : qk2.a) {
            hk0Var.getClass();
            y(objArr);
        }
    }

    @Override // p.p3
    public final boolean a(View view) {
        if (!((SwipeDismissBehavior) this.e).r(view)) {
            return false;
        }
        WeakHashMap weakHashMap = zt2.a;
        boolean z = jt2.d(view) == 1;
        int i = ((SwipeDismissBehavior) this.e).c;
        view.offsetLeftAndRight((!(i == 0 && z) && (i != 1 || z)) ? view.getWidth() : -view.getWidth());
        view.setAlpha(0.0f);
        ((SwipeDismissBehavior) this.e).getClass();
        return true;
    }

    @Override // p.s4
    public final void b(Object obj) {
        r4 r4Var = (r4) obj;
        lx0 lx0Var = (lx0) ((ox0) this.e).C.pollLast();
        if (lx0Var == null) {
            toString();
            return;
        }
        ww0 ww0VarL = ((ox0) this.e).c.l(lx0Var.c);
        if (ww0VarL == null) {
            return;
        }
        int i = r4Var.c;
        if (Log.isLoggable("FragmentManager", 2)) {
            ww0VarL.toString();
            Objects.toString(r4Var.e);
        }
    }

    @Override // p.zj
    public final void c() {
        ((AAMLoginPasswordActivity) this.e).H.b.d();
    }

    @Override // p.nc1
    public final String d(String str, Object... objArr) {
        String strG;
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            Object obj = objArr[i];
            String strValueOf = String.valueOf(obj);
            if (obj instanceof URI) {
                URI uri = (URI) obj;
                String scheme = uri.getScheme();
                if ("http".equals(scheme) || WebgateHelper.DEFAULT_WEBGATE_PROTOCOL.equals(scheme)) {
                    StringBuilder sbF = hf2.f(scheme, "://");
                    sbF.append(uri.getHost());
                    sbF.append("/AnonymizedMarvin");
                    strG = sbF.toString();
                } else if (scheme != null) {
                    strG = scheme.concat(":AnonymizedMarvin");
                } else {
                    strG = "<no colon, " + uri.toString().length() + " characters>";
                }
            } else {
                strG = g(strValueOf);
            }
            if (!strG.equals(strValueOf)) {
                objArr[i] = strG;
            }
        }
        return String.format(Locale.US, g(str), objArr);
    }

    @Override // p.qi1
    public final void e(si1 si1Var) {
        switch (this.c) {
            case 5:
                qi1 qi1Var = ((ActionMenuView) this.e).D;
                if (qi1Var != null) {
                    qi1Var.e(si1Var);
                }
                break;
            default:
                g4 g4Var = ((Toolbar) this.e).c.B;
                if (g4Var == null || !g4Var.e()) {
                    Iterator it = ((CopyOnWriteArrayList) ((Toolbar) this.e).O.k).iterator();
                    while (it.hasNext()) {
                        ((hx0) it.next()).a.s();
                    }
                }
                ((Toolbar) this.e).getClass();
                break;
        }
    }

    @Override // p.lp
    public final Type f() {
        return (Type) this.e;
    }

    @Override // p.nc1
    public final String g(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(str.length());
        for (Pattern pattern : (Pattern[]) this.e) {
            sb.setLength(0);
            Matcher matcher = pattern.matcher(str);
            int iEnd = 0;
            while (matcher.find()) {
                for (int i = 1; i <= matcher.groupCount(); i++) {
                    int iStart = matcher.start(i);
                    if (iStart != -1) {
                        sb.append((CharSequence) str, iEnd, iStart);
                        sb.append("AnonymizedMarvin");
                        iEnd = matcher.end(i);
                    }
                }
            }
            sb.append((CharSequence) str, iEnd, str.length());
            str = sb.toString();
        }
        return str;
    }

    @Override // p.qi1
    public final boolean h(si1 si1Var, MenuItem menuItem) {
        switch (this.c) {
            case 5:
                j4 j4Var = ((ActionMenuView) this.e).I;
                if (j4Var != null) {
                    p01 p01Var = (p01) j4Var;
                    if (!((Toolbar) p01Var.e).O.w()) {
                        ((Toolbar) p01Var.e).getClass();
                        break;
                    }
                }
                break;
            default:
                ((Toolbar) this.e).getClass();
                break;
        }
        return false;
    }

    @Override // p.q6
    public final void i(Bundle bundle) {
        u4.z(this.e);
        throw null;
    }

    @Override // p.ah2
    public final b33 j(Object obj) {
        return ci1.t(Boolean.TRUE);
    }

    public final yh k() {
        String str = ((String) this.e) == null ? " identifier" : "";
        if (str.isEmpty()) {
            return new yh((String) this.e);
        }
        throw new IllegalStateException("Missing required properties:".concat(str));
    }

    public Typeface l(Context context, zv0 zv0Var, Resources resources, int i) {
        long jLongValue;
        aw0 aw0Var = (aw0) w(zv0Var.a, i, new p01(5, this));
        if (aw0Var == null) {
            return null;
        }
        int i2 = aw0Var.f;
        String str = aw0Var.a;
        Typeface typefaceR = ro2.a.r(context, resources, i2, str, i);
        if (typefaceR != null) {
            ro2.b.c(ro2.b(resources, i2, str, 0, i), typefaceR);
        }
        if (typefaceR == null) {
            jLongValue = 0;
        } else {
            try {
                Field declaredField = Typeface.class.getDeclaredField("native_instance");
                declaredField.setAccessible(true);
                jLongValue = ((Number) declaredField.get(typefaceR)).longValue();
            } catch (IllegalAccessException e) {
                Log.e("TypefaceCompatBaseImpl", "Could not retrieve font from family.", e);
                jLongValue = 0;
            } catch (NoSuchFieldException e2) {
                Log.e("TypefaceCompatBaseImpl", "Could not retrieve font from family.", e2);
                jLongValue = 0;
            }
        }
        if (jLongValue != 0) {
            ((ConcurrentHashMap) this.e).put(Long.valueOf(jLongValue), zv0Var);
        }
        return typefaceR;
    }

    @Override // p.zj
    public final void m(yj yjVar) {
        AAMLoginPasswordActivity aAMLoginPasswordActivity = (AAMLoginPasswordActivity) this.e;
        l0 l0Var = new l0(aAMLoginPasswordActivity);
        y3 y3Var = new y3(aAMLoginPasswordActivity, 1);
        AAMLoginPasswordActivity aAMLoginPasswordActivity2 = (AAMLoginPasswordActivity) this.e;
        aAMLoginPasswordActivity2.H = new kd1(aAMLoginPasswordActivity2, ((ek) yjVar).b.f, l0Var, y3Var, aAMLoginPasswordActivity2.P);
        kd1 kd1Var = ((AAMLoginPasswordActivity) this.e).H;
        kd1Var.f.x(LoginUbiLogger$Page.PASSWORD);
        kd1Var.e.getClass();
        kd1Var.b.c(Observable.empty().subscribe(new jd1(kd1Var, 2), new ed1(10)));
    }

    @Override // p.wi1
    public final void n(si1 si1Var, MenuItem menuItem) {
        ((lq) this.e).o.removeCallbacksAndMessages(si1Var);
    }

    @Override // p.wi1
    public final void o(si1 si1Var, xi1 xi1Var) {
        ((lq) this.e).o.removeCallbacksAndMessages(null);
        int size = ((lq) this.e).q.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                i = -1;
                break;
            } else if (si1Var == ((kq) ((lq) this.e).q.get(i)).b) {
                break;
            } else {
                i++;
            }
        }
        if (i == -1) {
            return;
        }
        int i2 = i + 1;
        ((lq) this.e).o.postAtTime(new jq(this, i2 < ((lq) this.e).q.size() ? (kq) ((lq) this.e).q.get(i2) : null, xi1Var, si1Var, 0), si1Var, SystemClock.uptimeMillis() + 200);
    }

    @Override // p.dq1
    public final void onSuccess(Object obj) {
        com.google.android.play.core.assetpacks.c cVar = (com.google.android.play.core.assetpacks.c) this.e;
        List list = (List) obj;
        int iA = cVar.b.a();
        for (File file : cVar.e()) {
            if (!list.contains(file.getName()) && com.google.android.play.core.assetpacks.c.b(file, true) != iA) {
                com.google.android.play.core.assetpacks.c.g(file);
            }
        }
    }

    public Typeface p(Context context, cw0[] cw0VarArr, int i) throws Throwable {
        InputStream inputStreamOpenInputStream;
        InputStream inputStream = null;
        if (cw0VarArr.length < 1) {
            return null;
        }
        try {
            inputStreamOpenInputStream = context.getContentResolver().openInputStream(x(i, cw0VarArr).a);
        } catch (IOException unused) {
            inputStreamOpenInputStream = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            Typeface typefaceQ = q(context, inputStreamOpenInputStream);
            f60.j(inputStreamOpenInputStream);
            return typefaceQ;
        } catch (IOException unused2) {
            f60.j(inputStreamOpenInputStream);
            return null;
        } catch (Throwable th2) {
            th = th2;
            inputStream = inputStreamOpenInputStream;
            f60.j(inputStream);
            throw th;
        }
    }

    public Typeface q(Context context, InputStream inputStream) {
        File fileU = f60.u(context);
        if (fileU == null) {
            return null;
        }
        try {
            if (f60.l(fileU, inputStream)) {
                return Typeface.createFromFile(fileU.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            fileU.delete();
        }
    }

    public Typeface r(Context context, Resources resources, int i, String str, int i2) {
        File fileU = f60.u(context);
        if (fileU == null) {
            return null;
        }
        try {
            if (f60.k(fileU, resources, i)) {
                return Typeface.createFromFile(fileU.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            fileU.delete();
        }
    }

    @Override // p.lp
    public final Object s(ip1 ip1Var) {
        dv dvVar = new dv(ip1Var);
        ip1Var.E(new cv(this, 0, dvVar));
        return dvVar;
    }

    public cw0 x(int i, cw0[] cw0VarArr) {
        return (cw0) w(cw0VarArr, i, new oh1(5, this));
    }

    public final void z() {
        ((yw0) this.e).t.K();
    }

    public hk0(zu zuVar) {
        this.c = 7;
        this.e = zuVar;
    }

    public hk0(xw xwVar) {
        this.c = 27;
        this.e = xwVar;
    }

    public hk0(int i) {
        this.c = i;
        if (i == 8) {
            this.e = new ConcurrentHashMap();
            return;
        }
        if (i == 9) {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 30) {
                this.e = new vw2();
                return;
            } else if (i2 >= 29) {
                this.e = new uw2();
                return;
            } else {
                this.e = new sw2();
                return;
            }
        }
        if (i == 13) {
            this.e = new ArrayList();
            return;
        }
        if (i == 15) {
            this.e = null;
            return;
        }
        if (i != 22) {
            if (i == 26) {
                this.e = new Pattern[]{Pattern.compile("spotify:user:([a-zA-Z0-9%_~+.\\-]+)"), Pattern.compile("spotify%3Auser%3A([^%\\s]+)"), Pattern.compile("spotify(?::user:([a-zA-Z0-9%_~+.\\-]+))?:playlist:([a-zA-Z0-9]+)"), Pattern.compile("spotify(?:%3Auser%3A([^%]+))?%3Aplaylist%3A([a-zA-Z0-9]+)"), Pattern.compile("spotify:user:([a-zA-Z0-9%_~+.\\-]+):cluster:([a-zA-Z0-9]+)"), Pattern.compile("spotify%3Auser%3A([a-zA-Z0-9%_~+.\\-]+)%3Acluster%3A([a-zA-Z0-9]+)"), Pattern.compile("searchview/.*username=([^&]+)")};
            } else if (i != 29) {
                this.e = new ArrayDeque();
            } else {
                this.e = new ThreadLocal();
            }
        }
    }

    public hk0(Field field) {
        this.c = 23;
        field.getClass();
        this.e = field;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ hk0() {
        this(19, ComponentDiscoveryService.class);
        this.c = 19;
    }

    public hk0(fx2 fx2Var) {
        this.c = 9;
        int i = Build.VERSION.SDK_INT;
        if (i >= 30) {
            this.e = new vw2(fx2Var);
        } else if (i >= 29) {
            this.e = new uw2(fx2Var);
        } else {
            this.e = new sw2(fx2Var);
        }
    }
}
