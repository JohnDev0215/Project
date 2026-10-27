package p;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.v4.media.session.MediaSessionCompat$Token;
import android.text.TextUtils;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import androidx.lifecycle.Lifecycle$Event;
import coil.request.CachePolicy;
import coil.request.NullRequestDataException;
import coil.size.Scale;
import coil.target.GenericViewTarget;
import com.google.common.base.Optional;
import com.google.firebase.installations.remote.TokenResult$ResponseCode;
import com.spotify.eventsender.eventsender.SequenceNumberProvider$SequenceNumberType;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class kk0 implements nm1, ah2, xo1, lp {
    public final /* synthetic */ int c;
    public Object e;
    public Object k;
    public Object l;

    public /* synthetic */ kk0(Object obj, Object obj2, Object obj3, int i) {
        this.c = i;
        this.l = obj;
        this.e = obj2;
        this.k = obj3;
    }

    public static void a(kk0 kk0Var, AbstractCollection abstractCollection) {
        kk0Var.getClass();
        if (abstractCollection.size() <= 999) {
            kk0Var.o(abstractCollection);
            return;
        }
        ArrayList arrayList = new ArrayList(999);
        Iterator it = abstractCollection.iterator();
        while (it.hasNext()) {
            arrayList.add((Long) it.next());
            if (arrayList.size() == 999) {
                kk0Var.o(arrayList);
                arrayList.clear();
            }
        }
        if (arrayList.size() > 0) {
            kk0Var.o(arrayList);
        }
    }

    public static void b(kk0 kk0Var, String str, byte[] bArr, mb2 mb2Var, byte[] bArr2, ei0 ei0Var, zb0 zb0Var, wc1 wc1Var, boolean z, String str2, String str3, long j, String str4) {
        String strA = si0.a(str2);
        fi0 fi0Var = new fi0();
        fi0Var.c = str;
        fi0Var.g = strA;
        fi0Var.b = Boolean.valueOf(z);
        fi0Var.d = bArr2;
        fi0Var.h = str3;
        fi0Var.f = ux2.f(bArr, j, ei0Var, zb0Var, str4).toByteArray();
        long jA = mb2Var.a(str, bArr2, SequenceNumberProvider$SequenceNumberType.c);
        fi0Var.e = jA;
        ((i52) kk0Var.k).b();
        ((i52) kk0Var.k).c();
        try {
            ee0 ee0Var = (ee0) kk0Var.l;
            th2 th2VarA = ee0Var.a();
            try {
                ee0Var.d(th2VarA, fi0Var);
                long jV0 = th2VarA.v0();
                ee0Var.c(th2VarA);
                ((i52) kk0Var.k).o();
                ((i52) kk0Var.k).k();
                if (((Optional) kk0Var.e).d() && ((Long) ((Optional) kk0Var.e).c()).longValue() >= jV0) {
                    throw new IllegalStateException("DB is overriding saved events for event ".concat(str));
                }
                kk0Var.e = Optional.e(Long.valueOf(jV0));
                long jZ = mb2Var.a.z(str, bArr2);
                if (jZ == 0) {
                    jZ = mb2Var.b;
                }
                if (jZ == jA) {
                    throw new IllegalStateException("DB is overriding saved sequence numbers for event ".concat(str));
                }
                ((u11) wc1Var).v(String.format(Locale.US, "Event persisted: %d, Name: %s, Sequence No: %d", Long.valueOf(jV0), str, Long.valueOf(jA)));
            } catch (Throwable th) {
                ee0Var.c(th2VarA);
                throw th;
            }
        } catch (Throwable th2) {
            ((i52) kk0Var.k).k();
            throw th2;
        }
    }

    public static void d(v4 v4Var, jo1 jo1Var) {
        e(v4Var, "X-CRASHLYTICS-GOOGLE-APP-ID", (String) jo1Var.b);
        e(v4Var, "X-CRASHLYTICS-API-CLIENT-TYPE", "android");
        e(v4Var, "X-CRASHLYTICS-API-CLIENT-VERSION", "18.3.6");
        e(v4Var, "Accept", "application/json");
        e(v4Var, "X-CRASHLYTICS-DEVICE-MODEL", (String) jo1Var.c);
        e(v4Var, "X-CRASHLYTICS-OS-BUILD-VERSION", (String) jo1Var.d);
        e(v4Var, "X-CRASHLYTICS-OS-DISPLAY-VERSION", jo1Var.e);
        e(v4Var, "X-CRASHLYTICS-INSTALLATION-ID", ((e31) jo1Var.f).d());
    }

    public static void e(v4 v4Var, String str, String str2) {
        if (str2 != null) {
            ((Map) v4Var.l).put(str, str2);
        }
    }

    public static kf0 p(q31 q31Var, Throwable th) {
        Drawable drawableB;
        if (th instanceof NullRequestDataException) {
            drawableB = g.b(q31Var, q31Var.K, q31Var.J, q31Var.M.l);
            if (drawableB == null) {
                drawableB = g.b(q31Var, q31Var.I, q31Var.H, q31Var.M.k);
            }
        } else {
            drawableB = g.b(q31Var, q31Var.I, q31Var.H, q31Var.M.k);
        }
        return new kf0(drawableB, q31Var, th);
    }

    public static HashMap r(jo1 jo1Var) {
        HashMap map = new HashMap();
        map.put("build_version", (String) jo1Var.i);
        map.put("display_version", (String) jo1Var.h);
        map.put("source", Integer.toString(jo1Var.a));
        String str = jo1Var.g;
        if (!TextUtils.isEmpty(str)) {
            map.put("instance", str);
        }
        return map;
    }

    public static boolean v(q31 q31Var, Bitmap.Config config) {
        if (!(config == Bitmap.Config.HARDWARE)) {
            return true;
        }
        if (!q31Var.q) {
            return false;
        }
        yi2 yi2Var = q31Var.c;
        if (yi2Var instanceof GenericViewTarget) {
            ImageView imageViewI = ((GenericViewTarget) yi2Var).i();
            if (imageViewI.isAttachedToWindow() && !imageViewI.isHardwareAccelerated()) {
                return false;
            }
        }
        return true;
    }

    public static void z() {
        if (Build.VERSION.SDK_INT >= 29) {
            throw new UnsupportedClassVersionError("This function can only be used for API Level < 29.");
        }
    }

    public final void A() {
        String str = (String) this.l;
        if (str != null) {
            ((p01) this.e).z(str);
        }
        synchronized (((Set) this.k)) {
            Iterator it = ((Set) this.k).iterator();
            while (it.hasNext()) {
                ((p01) this.e).z((String) it.next());
            }
        }
        l();
    }

    public final void c(String str) {
        v03.j(str, "parentId");
        synchronized (((Set) this.k)) {
            ((Set) this.k).add(str);
        }
    }

    @Override // p.lp
    public final Type f() {
        return (Type) this.e;
    }

    public final eh g() {
        String strH = ((String) this.e) == null ? " arch" : "";
        if (((String) this.k) == null) {
            strH = strH.concat(" libraryName");
        }
        if (((String) this.l) == null) {
            strH = u4.h(strH, " buildId");
        }
        if (strH.isEmpty()) {
            return new eh((String) this.e, (String) this.k, (String) this.l);
        }
        throw new IllegalStateException("Missing required properties:".concat(strH));
    }

    public final th h() {
        String strH = ((String) this.e) == null ? " name" : "";
        if (((Integer) this.k) == null) {
            strH = strH.concat(" importance");
        }
        if (((z31) this.l) == null) {
            strH = u4.h(strH, " frames");
        }
        if (strH.isEmpty()) {
            return new th((String) this.e, ((Integer) this.k).intValue(), (z31) this.l);
        }
        throw new IllegalStateException("Missing required properties:".concat(strH));
    }

    public final vi i() {
        String str = ((Long) this.k) == null ? " tokenExpirationTimestamp" : "";
        if (str.isEmpty()) {
            return new vi((String) this.e, ((Long) this.k).longValue(), (TokenResult$ResponseCode) this.l);
        }
        throw new IllegalStateException("Missing required properties:".concat(str));
    }

    @Override // p.ah2
    public final b33 j(Object obj) {
        if (((cc2) obj) == null) {
            return ci1.t(null);
        }
        aj2[] aj2VarArr = new aj2[2];
        aj2VarArr[0] = com.google.firebase.crashlytics.internal.common.a.b(((c30) this.l).n);
        c30 c30Var = (c30) this.l;
        aj2VarArr[1] = c30Var.n.l.e((Executor) this.e, c30Var.m ? (String) this.k : null);
        return ci1.J(Arrays.asList(aj2VarArr));
    }

    @Override // p.xo1
    public final Object k() {
        u4.z(this.e);
        throw null;
    }

    public final void l() {
        synchronized (((Set) this.k)) {
            ((Set) this.k).clear();
        }
    }

    public final void m(LinkedList linkedList, LinkedList linkedList2, bk0 bk0Var) {
        ((i52) this.k).c();
        try {
            n(linkedList);
            bk0Var.m(linkedList2);
            ((i52) this.k).o();
        } finally {
            ((i52) this.k).k();
        }
    }

    public final void n(AbstractCollection abstractCollection) {
        ((i52) this.k).c();
        try {
            a(this, abstractCollection);
            ((i52) this.k).o();
        } finally {
            ((i52) this.k).k();
        }
    }

    public final void o(AbstractCollection abstractCollection) {
        ((i52) this.k).b();
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM Events WHERE id IN (");
        ux2.b(abstractCollection.size(), sb);
        sb.append(")");
        th2 th2VarD = ((i52) this.k).d(sb.toString());
        Iterator it = abstractCollection.iterator();
        int i = 1;
        while (it.hasNext()) {
            Long l = (Long) it.next();
            if (l == null) {
                th2VarD.K(i);
            } else {
                th2VarD.h0(i, l.longValue());
            }
            i++;
        }
        ((i52) this.k).c();
        try {
            th2VarD.F();
            ((i52) this.k).o();
        } finally {
            ((i52) this.k).k();
        }
    }

    public final ArrayList q(String str) {
        Boolean boolValueOf;
        m52 m52VarI = m52.i(1, "SELECT * FROM Events WHERE deviceId IS NULL OR deviceId <> ? ORDER BY id ASC");
        if (str == null) {
            m52VarI.K(1);
        } else {
            m52VarI.x(1, str);
        }
        ((i52) this.k).b();
        Cursor cursorO = p02.o((i52) this.k, m52VarI);
        try {
            int iR = f60.r(cursorO, "id");
            int iR2 = f60.r(cursorO, "authenticated");
            int iR3 = f60.r(cursorO, "eventName");
            int iR4 = f60.r(cursorO, "sequenceId");
            int iR5 = f60.r(cursorO, "sequenceNumber");
            int iR6 = f60.r(cursorO, "fragments");
            int iR7 = f60.r(cursorO, "owner");
            int iR8 = f60.r(cursorO, "deviceId");
            ArrayList arrayList = new ArrayList(cursorO.getCount());
            while (cursorO.moveToNext()) {
                fi0 fi0Var = new fi0();
                fi0Var.a = cursorO.getLong(iR);
                Integer numValueOf = cursorO.isNull(iR2) ? null : Integer.valueOf(cursorO.getInt(iR2));
                if (numValueOf == null) {
                    boolValueOf = null;
                } else {
                    boolValueOf = Boolean.valueOf(numValueOf.intValue() != 0);
                }
                fi0Var.b = boolValueOf;
                if (cursorO.isNull(iR3)) {
                    fi0Var.c = null;
                } else {
                    fi0Var.c = cursorO.getString(iR3);
                }
                if (cursorO.isNull(iR4)) {
                    fi0Var.d = null;
                } else {
                    fi0Var.d = cursorO.getBlob(iR4);
                }
                int i = iR;
                fi0Var.e = cursorO.getLong(iR5);
                if (cursorO.isNull(iR6)) {
                    fi0Var.f = null;
                } else {
                    fi0Var.f = cursorO.getBlob(iR6);
                }
                if (cursorO.isNull(iR7)) {
                    fi0Var.g = null;
                } else {
                    fi0Var.g = cursorO.getString(iR7);
                }
                if (cursorO.isNull(iR8)) {
                    fi0Var.h = null;
                } else {
                    fi0Var.h = cursorO.getString(iR8);
                }
                arrayList.add(fi0Var);
                iR = i;
            }
            cursorO.close();
            m52VarI.q();
            return arrayList;
        } catch (Throwable th) {
            cursorO.close();
            m52VarI.q();
            throw th;
        }
    }

    @Override // p.lp
    public final Object s(ip1 ip1Var) {
        Executor executor = (Executor) this.k;
        return executor == null ? ip1Var : new k60(executor, ip1Var);
    }

    public final JSONObject t(j6 j6Var) {
        int i = j6Var.c;
        ((zb0) this.l).l(2);
        if (i == 200 || i == 201 || i == 202 || i == 203) {
            try {
                return new JSONObject((String) j6Var.e);
            } catch (Exception unused) {
                ((zb0) this.l).l(5);
                ((zb0) this.l).l(5);
                return null;
            }
        }
        zb0 zb0Var = (zb0) this.l;
        StringBuilder sbO = u4.o("Settings request failed; (status: ", i, ") from ");
        sbO.append((String) this.e);
        String string = sbO.toString();
        if (!zb0Var.l(6)) {
            return null;
        }
        Log.e("FirebaseCrashlytics", string, null);
        return null;
    }

    public final String toString() {
        switch (this.c) {
            case 4:
                String str = "[ ";
                if (((ce2) this.e) != null) {
                    for (int i = 0; i < 9; i++) {
                        str = str + ((ce2) this.e).f111p[i] + " ";
                    }
                }
                StringBuilder sbF = hf2.f(str, "] ");
                sbF.append((ce2) this.e);
                return sbF.toString();
            case 12:
                StringBuilder sb = new StringBuilder("[ClassStack (self-refs: ");
                ArrayList arrayList = (ArrayList) this.l;
                sb.append(arrayList == null ? "0" : String.valueOf(arrayList.size()));
                sb.append(')');
                for (kk0 kk0Var = this; kk0Var != null; kk0Var = (kk0) kk0Var.e) {
                    sb.append(' ');
                    sb.append(((Class) kk0Var.k).getName());
                }
                sb.append(']');
                return sb.toString();
            default:
                return super.toString();
        }
    }

    public final mp2 u() {
        switch (this.c) {
            case 22:
                lp2 lp2Var = new lp2();
                lp2Var.c((ip2) this.k);
                lp2Var.b = ((zi) this.l).a;
                return (mp2) lp2Var.a();
            case 23:
                lp2 lp2Var2 = new lp2();
                lp2Var2.c((ip2) this.k);
                lp2Var2.b = ((fj) this.l).a;
                return (mp2) lp2Var2.a();
            default:
                lp2 lp2Var3 = new lp2();
                lp2Var3.c((ip2) this.k);
                lp2Var3.b = ((tj) this.l).a;
                return (mp2) lp2Var3.a();
        }
    }

    public final boolean w() {
        Iterator it = ((CopyOnWriteArrayList) this.k).iterator();
        while (it.hasNext()) {
            if (((hx0) it.next()).a.o()) {
                return true;
            }
        }
        return false;
    }

    public final br1 x(q31 q31Var, qd2 qd2Var) {
        Bitmap.Config config;
        Bitmap.Config config2 = ((q31Var.l.isEmpty() || kotlin.collections.b.p0(i.a, q31Var.g)) && ((config = q31Var.g) != Bitmap.Config.HARDWARE || (v(q31Var, config) && ((l11) this.l).i(qd2Var)))) ? q31Var.g : Bitmap.Config.ARGB_8888;
        CachePolicy cachePolicy = ((ai2) this.k).l ? q31Var.v : CachePolicy.e;
        ci1 ci1Var = qd2Var.a;
        u90 u90Var = u90.f195p;
        return new br1(q31Var.a, config2, q31Var.h, qd2Var, (v03.b(ci1Var, u90Var) || v03.b(qd2Var.b, u90Var)) ? Scale.e : q31Var.C, g.a(q31Var), q31Var.r && q31Var.l.isEmpty() && config2 != Bitmap.Config.ALPHA_8, q31Var.s, q31Var.f, q31Var.n, q31Var.o, q31Var.D, q31Var.t, q31Var.u, cachePolicy);
    }

    public final void y(Lifecycle$Event lifecycle$Event) {
        tb2 tb2Var = (tb2) this.l;
        if (tb2Var != null) {
            tb2Var.run();
        }
        tb2 tb2Var2 = new tb2((androidx.lifecycle.a) this.e, lifecycle$Event);
        this.l = tb2Var2;
        ((Handler) this.k).postAtFrontOfQueue(tb2Var2);
    }

    public /* synthetic */ kk0(Object obj, Object obj2, Object obj3, int i, int i2) {
        this.c = i;
        this.e = obj;
        this.k = obj2;
        this.l = obj3;
    }

    public kk0(p01 p01Var) {
        this.c = 20;
        this.e = p01Var;
        this.k = new HashSet(3);
    }

    public kk0(bk0 bk0Var, wg1 wg1Var) {
        this.c = 21;
        this.l = null;
        this.e = bk0Var;
        this.k = wg1Var;
    }

    public kk0(l31 l31Var, ai2 ai2Var) {
        Object u11Var;
        this.c = 8;
        this.e = l31Var;
        this.k = ai2Var;
        int i = Build.VERSION.SDK_INT;
        if (e.a) {
            u11Var = new x31(false);
        } else if (i != 26 && i != 27) {
            u11Var = new x31(true);
        } else {
            u11Var = new u11();
        }
        this.l = u11Var;
    }

    public kk0(i52 i52Var) {
        this.c = 0;
        this.e = Optional.a();
        this.k = i52Var;
        this.l = new f90(this, i52Var, 8);
    }

    public kk0(s91 s91Var) {
        this.c = 6;
        v03.j(s91Var, "provider");
        this.e = new androidx.lifecycle.a(s91Var);
        this.k = new Handler();
    }

    public kk0(fx1 fx1Var, fx1 fx1Var2) {
        this.c = 4;
        this.l = fx1Var;
        this.k = fx1Var2;
    }

    public kk0(tj tjVar, String str) {
        this.c = 24;
        this.l = tjVar;
        this.e = str;
        hp2 hp2VarB = tjVar.b.b();
        hp2VarB.i.add(new jp2("error_type", (String) this.e, null, null, null));
        hp2VarB.j = true;
        this.k = hp2VarB.a();
    }

    public kk0(Runnable runnable) {
        this.c = 5;
        this.k = new CopyOnWriteArrayList();
        this.l = new HashMap();
        this.e = runnable;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public kk0(String str, yn ynVar) {
        this(str, ynVar, 0);
        this.c = 17;
    }

    public kk0(zi ziVar, String str) {
        this.c = 22;
        this.l = ziVar;
        this.e = str;
        hp2 hp2VarB = ziVar.b.b();
        hp2VarB.i.add(new jp2("error_type", (String) this.e, null, null, null));
        hp2VarB.j = true;
        this.k = hp2VarB.a();
    }

    public kk0(String str, yn ynVar, int i) {
        zb0 zb0Var = zb0.o;
        this.c = 17;
        if (str == null) {
            throw new IllegalArgumentException("url must not be null.");
        }
        this.l = zb0Var;
        this.k = ynVar;
        this.e = str;
    }

    public kk0(fj fjVar, String str) {
        this.c = 23;
        this.l = fjVar;
        this.e = str;
        hp2 hp2VarB = fjVar.b.b();
        hp2VarB.i.add(new jp2("error_message", (String) this.e, null, null, null));
        hp2VarB.j = true;
        this.k = hp2VarB.a();
    }

    public kk0(Context context, MediaSessionCompat$Token mediaSessionCompat$Token) {
        this.c = 1;
        if (mediaSessionCompat$Token != null) {
            this.l = Collections.synchronizedSet(new HashSet());
            this.k = mediaSessionCompat$Token;
            if (Build.VERSION.SDK_INT >= 29) {
                this.e = new gh1(context, mediaSessionCompat$Token);
                return;
            } else {
                this.e = new android.support.v4.media.session.c(context, mediaSessionCompat$Token);
                return;
            }
        }
        throw new IllegalArgumentException("sessionToken must not be null");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public kk0(Application application, bi1 bi1Var) {
        this(application, bi1Var.a.c);
        this.c = 1;
    }

    public kk0(int i) {
        this.c = i;
        if (i == 7) {
            this.e = Collections.emptyList();
            this.k = Collections.emptyList();
            return;
        }
        if (i != 18 && i != 15 && i != 16) {
            this.e = null;
            this.k = null;
            this.l = null;
            z();
            try {
                Method declaredMethod = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", new Class[0]);
                this.e = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException unused) {
            }
            try {
                Method declaredMethod2 = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", new Class[0]);
                this.k = declaredMethod2;
                declaredMethod2.setAccessible(true);
            } catch (NoSuchMethodException unused2) {
            }
            try {
                Method method = AutoCompleteTextView.class.getMethod("ensureImeVisible", Boolean.TYPE);
                this.l = method;
                method.setAccessible(true);
            } catch (NoSuchMethodException unused3) {
            }
        }
    }
}
