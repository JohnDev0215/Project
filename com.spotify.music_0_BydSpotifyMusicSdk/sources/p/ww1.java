package p;

import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat$Token;
import androidx.cardview.widget.CardView;
import androidx.work.impl.WorkDatabase;
import com.google.android.datatransport.cct.CctBackendFactory;
import com.google.android.datatransport.runtime.backends.TransportBackendDiscovery;
import com.google.protobuf.InvalidProtocolBufferException;
import com.spotify.music.R;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: classes.dex */
public final class ww1 implements q13, i13, d00, xo1, zj, h00 {
    public final /* synthetic */ int c;
    public Object e;
    public Object k;

    public /* synthetic */ ww1(int i, Object obj) {
        this.c = i;
        this.k = obj;
    }

    @Override // p.q13
    public final Object a() {
        switch (this.c) {
            case 7:
                com.google.android.play.core.assetpacks.j jVar = (com.google.android.play.core.assetpacks.j) this.e;
                Bundle bundle = (Bundle) this.k;
                jVar.getClass();
                int i = bundle.getInt("session_id");
                if (i == 0) {
                    return Boolean.TRUE;
                }
                HashMap map = jVar.d;
                Integer numValueOf = Integer.valueOf(i);
                if (!map.containsKey(numValueOf)) {
                    return Boolean.TRUE;
                }
                if (((o13) map.get(numValueOf)).c.d == 6) {
                    return Boolean.FALSE;
                }
                return Boolean.valueOf(!he1.g0(r0.c.d, bundle.getInt(l13.u("status", com.google.android.play.core.assetpacks.j.d(bundle)))));
            default:
                Object objA = ((i13) this.e).a();
                Context contextB = ((w23) ((i13) this.k)).b();
                m23 m23Var = (m23) objA;
                v03.V(contextB.getPackageManager(), new ComponentName(contextB.getPackageName(), "com.google.android.play.core.assetpacks.AssetPackExtractionService"));
                v03.V(contextB.getPackageManager(), new ComponentName(contextB.getPackageName(), "com.google.android.play.core.assetpacks.ExtractionForegroundService"));
                ci1.M(m23Var);
                return m23Var;
        }
    }

    public final void b(boolean z) {
        ww0 ww0Var = ((ox0) this.k).v;
        if (ww0Var != null) {
            ww0Var.h().l.b(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.e).iterator();
        if (it.hasNext()) {
            u4.z(it.next());
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    @Override // p.h00
    public final Object convert(Object obj) {
        com.google.protobuf.i partialFrom;
        ResponseBody responseBody = (ResponseBody) obj;
        try {
            try {
                if (((rl0) this.k) == null) {
                    ds1 ds1Var = (ds1) this.e;
                    wo woVarC0 = responseBody.i().C0();
                    i01 i01Var = (i01) ds1Var;
                    rl0 rl0Var = i01.b;
                    i01Var.getClass();
                    au auVarG = au.g(woVarC0);
                    partialFrom = com.google.protobuf.i.parsePartialFrom(i01Var.a, auVarG, rl0Var);
                    try {
                        auVarG.a(0);
                        i01.a(partialFrom);
                    } catch (InvalidProtocolBufferException e) {
                        e.i(partialFrom);
                        throw e;
                    }
                } else {
                    ds1 ds1Var2 = (ds1) this.e;
                    wo woVarC02 = responseBody.i().C0();
                    rl0 rl0Var2 = (rl0) this.k;
                    i01 i01Var2 = (i01) ds1Var2;
                    i01Var2.getClass();
                    au auVarG2 = au.g(woVarC02);
                    partialFrom = com.google.protobuf.i.parsePartialFrom(i01Var2.a, auVarG2, rl0Var2);
                    try {
                        auVarG2.a(0);
                        i01.a(partialFrom);
                    } catch (InvalidProtocolBufferException e2) {
                        e2.i(partialFrom);
                        throw e2;
                    }
                }
                return partialFrom;
            } catch (InvalidProtocolBufferException e3) {
                throw new RuntimeException(e3);
            }
        } finally {
            responseBody.close();
        }
    }

    public final void d(boolean z) {
        ox0 ox0Var = (ox0) this.k;
        Context context = ox0Var.t.r;
        ww0 ww0Var = ox0Var.v;
        if (ww0Var != null) {
            ww0Var.h().l.d(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.e).iterator();
        if (it.hasNext()) {
            u4.z(it.next());
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public final void e(boolean z) {
        ww0 ww0Var = ((ox0) this.k).v;
        if (ww0Var != null) {
            ww0Var.h().l.e(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.e).iterator();
        if (it.hasNext()) {
            u4.z(it.next());
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public final void f(boolean z) {
        ww0 ww0Var = ((ox0) this.k).v;
        if (ww0Var != null) {
            ww0Var.h().l.f(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.e).iterator();
        if (it.hasNext()) {
            u4.z(it.next());
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public final void g(boolean z) {
        ww0 ww0Var = ((ox0) this.k).v;
        if (ww0Var != null) {
            ww0Var.h().l.g(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.e).iterator();
        if (it.hasNext()) {
            u4.z(it.next());
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public final void h(boolean z) {
        ww0 ww0Var = ((ox0) this.k).v;
        if (ww0Var != null) {
            ww0Var.h().l.h(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.e).iterator();
        if (it.hasNext()) {
            u4.z(it.next());
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public final void i(boolean z) {
        ox0 ox0Var = (ox0) this.k;
        Context context = ox0Var.t.r;
        ww0 ww0Var = ox0Var.v;
        if (ww0Var != null) {
            ww0Var.h().l.i(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.e).iterator();
        if (it.hasNext()) {
            u4.z(it.next());
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    @Override // p.d00
    public final Object j(aj2 aj2Var) {
        return ((Callable) this.e).call();
    }

    @Override // p.xo1
    public final Object k() {
        try {
            return ((Constructor) this.e).newInstance(null);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InstantiationException e2) {
            throw new RuntimeException("Failed to invoke " + ((Constructor) this.e) + " with no args", e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException("Failed to invoke " + ((Constructor) this.e) + " with no args", e3.getTargetException());
        }
    }

    public final void l(boolean z) {
        ww0 ww0Var = ((ox0) this.k).v;
        if (ww0Var != null) {
            ww0Var.h().l.l(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.e).iterator();
        if (it.hasNext()) {
            u4.z(it.next());
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    @Override // p.zj
    public final void m(yj yjVar) {
        ij ijVar = (ij) this.k;
        ijVar.d = yjVar;
        ijVar.getClass();
        MediaSessionCompat$Token mediaSessionCompat$Token = ((ek) yjVar).b.i.b.a.c;
        bh1 bh1Var = (bh1) ijVar.b;
        if (mediaSessionCompat$Token == null) {
            bh1Var.getClass();
            throw new IllegalArgumentException("Session token may not be null");
        }
        if (bh1Var.o != null) {
            throw new IllegalStateException("The session token has already been set");
        }
        bh1Var.o = mediaSessionCompat$Token;
        sg1 sg1Var = bh1Var.c;
        sg1Var.d.n.a(new ug1(sg1Var, mediaSessionCompat$Token, 1));
        ((rg2) this.e).onComplete();
    }

    public final void n(boolean z) {
        ww0 ww0Var = ((ox0) this.k).v;
        if (ww0Var != null) {
            ww0Var.h().l.n(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.e).iterator();
        if (it.hasNext()) {
            u4.z(it.next());
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public final void o(boolean z) {
        ww0 ww0Var = ((ox0) this.k).v;
        if (ww0Var != null) {
            ww0Var.h().l.o(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.e).iterator();
        if (it.hasNext()) {
            u4.z(it.next());
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public final void p(boolean z) {
        ww0 ww0Var = ((ox0) this.k).v;
        if (ww0Var != null) {
            ww0Var.h().l.p(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.e).iterator();
        if (it.hasNext()) {
            u4.z(it.next());
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public final void q(boolean z) {
        ww0 ww0Var = ((ox0) this.k).v;
        if (ww0Var != null) {
            ww0Var.h().l.q(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.e).iterator();
        if (it.hasNext()) {
            u4.z(it.next());
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public final void r(boolean z) {
        ww0 ww0Var = ((ox0) this.k).v;
        if (ww0Var != null) {
            ww0Var.h().l.r(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.e).iterator();
        if (it.hasNext()) {
            u4.z(it.next());
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public final void s(boolean z) {
        ww0 ww0Var = ((ox0) this.k).v;
        if (ww0Var != null) {
            ww0Var.h().l.s(true);
        }
        Iterator it = ((CopyOnWriteArrayList) this.e).iterator();
        if (it.hasNext()) {
            u4.z(it.next());
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public final SharedPreferences t() {
        if (((SharedPreferences) this.e) == null) {
            this.e = ((Context) this.k).getSharedPreferences("com.spotify.eventsender.shared.prefs", 0);
        }
        return (SharedPreferences) this.e;
    }

    public final CctBackendFactory u(String str) {
        Map mapEmptyMap;
        PackageManager packageManager;
        ServiceInfo serviceInfo;
        if (((Map) this.k) == null) {
            Context context = (Context) this.e;
            try {
                packageManager = context.getPackageManager();
            } catch (PackageManager.NameNotFoundException unused) {
            }
            Bundle bundle = (packageManager == null || (serviceInfo = packageManager.getServiceInfo(new ComponentName(context, (Class<?>) TransportBackendDiscovery.class), 128)) == null) ? null : serviceInfo.metaData;
            if (bundle == null) {
                mapEmptyMap = Collections.emptyMap();
            } else {
                HashMap map = new HashMap();
                for (String str2 : bundle.keySet()) {
                    Object obj = bundle.get(str2);
                    if ((obj instanceof String) && str2.startsWith("backend:")) {
                        for (String str3 : ((String) obj).split(",", -1)) {
                            String strTrim = str3.trim();
                            if (!strTrim.isEmpty()) {
                                map.put(strTrim, str2.substring(8));
                            }
                        }
                    }
                }
                mapEmptyMap = map;
            }
            this.k = mapEmptyMap;
        }
        String str4 = (String) ((Map) this.k).get(str);
        if (str4 == null) {
            return null;
        }
        try {
            return (CctBackendFactory) Class.forName(str4).asSubclass(CctBackendFactory.class).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (ClassNotFoundException unused2) {
            String.format("Class %s is not found.", str4);
            return null;
        } catch (IllegalAccessException unused3) {
            String.format("Could not instantiate %s.", str4);
            return null;
        } catch (InstantiationException unused4) {
            String.format("Could not instantiate %s.", str4);
            return null;
        } catch (NoSuchMethodException unused5) {
            String.format("Could not instantiate %s", str4);
            return null;
        } catch (InvocationTargetException unused6) {
            String.format("Could not instantiate %s", str4);
            return null;
        }
    }

    public final Long v(String str) {
        m52 m52VarI = m52.i(1, "SELECT long_value FROM Preference where `key`=?");
        m52VarI.x(1, str);
        ((i52) this.e).b();
        Cursor cursorO = p02.o((i52) this.e, m52VarI);
        try {
            Long lValueOf = null;
            if (cursorO.moveToFirst() && !cursorO.isNull(0)) {
                lValueOf = Long.valueOf(cursorO.getLong(0));
            }
            return lValueOf;
        } finally {
            cursorO.close();
            m52VarI.q();
        }
    }

    public final String w(String str) {
        int identifier = ((Resources) this.e).getIdentifier(str, "string", (String) this.k);
        if (identifier == 0) {
            return null;
        }
        return ((Resources) this.e).getString(identifier);
    }

    public final pp2 x(String str) {
        switch (this.c) {
            case 15:
                op2 op2Var = new op2();
                op2Var.c((ip2) this.e);
                op2Var.b = ((zi) this.k).a;
                ep2 ep2Var = ep2.e;
                dp2 dp2VarR = u11.r();
                dp2VarR.a = "ui_navigate";
                dp2VarR.c = "hit";
                dp2VarR.b = 1;
                dp2VarR.b(str, "destination");
                op2Var.d = dp2VarR.a();
                return (pp2) op2Var.a();
            default:
                op2 op2Var2 = new op2();
                op2Var2.c((ip2) this.e);
                op2Var2.b = ((gj) this.k).a;
                ep2 ep2Var2 = ep2.e;
                dp2 dp2VarR2 = u11.r();
                dp2VarR2.a = "ui_navigate";
                dp2VarR2.c = "hit";
                dp2VarR2.b = 1;
                dp2VarR2.b(str, "destination");
                op2Var2.d = dp2VarR2.a();
                return (pp2) op2Var2.a();
        }
    }

    public final void y(vw1 vw1Var) {
        ((i52) this.e).b();
        ((i52) this.e).c();
        try {
            ((ee0) this.k).e(vw1Var);
            ((i52) this.e).o();
        } finally {
            ((i52) this.e).k();
        }
    }

    public final void z(int i, int i2, int i3, int i4) {
        ((CardView) this.k).l.set(i, i2, i3, i4);
        Object obj = this.k;
        super/*android.view.View*/.setPadding(i + ((CardView) obj).k.left, i2 + ((CardView) obj).k.top, i3 + ((CardView) obj).k.right, i4 + ((CardView) obj).k.bottom);
    }

    public /* synthetic */ ww1(Object obj, int i, Object obj2) {
        this.c = i;
        this.e = obj;
        this.k = obj2;
    }

    public /* synthetic */ ww1(Object obj, Object obj2, int i) {
        this.c = i;
        this.k = obj;
        this.e = obj2;
    }

    public ww1() {
        this.c = 10;
        this.e = new AtomicInteger();
        this.k = new AtomicInteger();
    }

    public ww1(WorkDatabase workDatabase) {
        this.c = 0;
        this.e = workDatabase;
        this.k = new f90(this, workDatabase, 1);
    }

    public ww1(ox0 ox0Var) {
        this.c = 2;
        this.e = new CopyOnWriteArrayList();
        this.k = ox0Var;
    }

    public ww1(Context context, int i) {
        this.c = i;
        if (i != 6) {
            this.k = null;
            this.e = context;
        } else {
            he1.g(context);
            Resources resources = context.getResources();
            this.e = resources;
            this.k = resources.getResourcePackageName(R.string.common_google_play_services_unknown_issue);
        }
    }

    public ww1(zi ziVar) {
        this.c = 15;
        this.k = ziVar;
        hp2 hp2VarB = ziVar.b.b();
        hp2VarB.i.add(new jp2("need_help", null, null, null, null));
        hp2VarB.j = false;
        this.e = hp2VarB.a();
    }

    public ww1(gj gjVar) {
        this.c = 16;
        this.k = gjVar;
        hp2 hp2VarB = gjVar.b.b();
        hp2VarB.i.add(new jp2("login_password", null, null, null, null));
        hp2VarB.j = false;
        this.e = hp2VarB.a();
    }
}
