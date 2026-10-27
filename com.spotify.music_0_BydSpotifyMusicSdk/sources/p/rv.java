package p;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.work.Worker;
import com.spotify.core.coreimpl.NativeApplicationScopeImpl;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.collections.EmptySet;
import kotlin.collections.builders.SetBuilder;

/* JADX INFO: loaded from: classes.dex */
public final class rv implements Runnable {
    public final /* synthetic */ int c;
    public final Object e;

    public /* synthetic */ rv(int i, Object obj) {
        this.c = i;
        this.e = obj;
    }

    public final SetBuilder a() {
        c61 c61Var = (c61) this.e;
        SetBuilder setBuilder = new SetBuilder();
        Cursor cursorM = c61Var.a.m(new ld2("SELECT * FROM room_table_modification_log WHERE invalidated = 1;"), null);
        while (cursorM.moveToNext()) {
            try {
                setBuilder.add(Integer.valueOf(cursorM.getInt(0)));
            } finally {
            }
        }
        ux2.e(cursorM, null);
        setBuilder.b();
        if (!setBuilder.isEmpty()) {
            if (((c61) this.e).h == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            th2 th2Var = ((c61) this.e).h;
            if (th2Var == null) {
                throw new IllegalArgumentException("Required value was null.".toString());
            }
            th2Var.F();
        }
        return setBuilder;
    }

    @Override // java.lang.Runnable
    public final void run() {
        g4 g4Var;
        Object obj;
        Set setA;
        o03 o03Var;
        switch (this.c) {
            case 0:
                try {
                    super/*android.app.Activity*/.onBackPressed();
                    return;
                } catch (IllegalStateException e) {
                    if (!TextUtils.equals(e.getMessage(), "Can not perform this action after onSaveInstanceState")) {
                        throw e;
                    }
                    return;
                }
            case 1:
                ab0 ab0Var = (ab0) this.e;
                ab0Var.a(true);
                ab0Var.invalidateSelf();
                return;
            case 2:
                ib0 ib0Var = (ib0) this.e;
                ib0Var.t = null;
                ib0Var.drawableStateChanged();
                return;
            case 3:
                ActionMenuView actionMenuView = ((Toolbar) this.e).c;
                if (actionMenuView == null || (g4Var = actionMenuView.B) == null) {
                    return;
                }
                g4Var.l();
                return;
            case 4:
                jb1 jb1Var = (jb1) this.e;
                if (jb1Var.w) {
                    if (jb1Var.u) {
                        jb1Var.u = false;
                        ug ugVar = jb1Var.c;
                        ugVar.getClass();
                        long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
                        ugVar.e = jCurrentAnimationTimeMillis;
                        ugVar.g = -1L;
                        ugVar.f = jCurrentAnimationTimeMillis;
                        ugVar.h = 0.5f;
                    }
                    ug ugVar2 = jb1Var.c;
                    if ((ugVar2.g > 0 && AnimationUtils.currentAnimationTimeMillis() > ugVar2.g + ((long) ugVar2.i)) || !jb1Var.f()) {
                        jb1Var.w = false;
                        return;
                    }
                    if (jb1Var.v) {
                        jb1Var.v = false;
                        long jUptimeMillis = SystemClock.uptimeMillis();
                        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                        jb1Var.k.onTouchEvent(motionEventObtain);
                        motionEventObtain.recycle();
                    }
                    if (ugVar2.f == 0) {
                        throw new RuntimeException("Cannot compute scroll delta before calling start()");
                    }
                    long jCurrentAnimationTimeMillis2 = AnimationUtils.currentAnimationTimeMillis();
                    float fA = ugVar2.a(jCurrentAnimationTimeMillis2);
                    long j = jCurrentAnimationTimeMillis2 - ugVar2.f;
                    ugVar2.f = jCurrentAnimationTimeMillis2;
                    kb1.b(jb1Var.y, (int) (j * ((fA * 4.0f) + ((-4.0f) * fA * fA)) * ugVar2.d));
                    View view = jb1Var.k;
                    WeakHashMap weakHashMap = zt2.a;
                    it2.m(view, this);
                    return;
                }
                return;
            case 5:
                ((du2) this.e).n(0);
                return;
            case 6:
                s90 s90Var = (s90) this.e;
                s90Var.e0.onDismiss(s90Var.m0);
                return;
            case 7:
                ((ox0) this.e).x(true);
                return;
            case 8:
                synchronized (((androidx.lifecycle.b) this.e).a) {
                    obj = ((androidx.lifecycle.b) this.e).f;
                    ((androidx.lifecycle.b) this.e).f = androidx.lifecycle.b.k;
                    break;
                }
                ((androidx.lifecycle.b) this.e).e(obj);
                return;
            case 9:
                ReentrantReadWriteLock.ReadLock lock = ((c61) this.e).a.h.readLock();
                v03.i(lock, "readWriteLock.readLock()");
                lock.lock();
                try {
                    try {
                        try {
                        } catch (IllegalStateException e2) {
                            Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e2);
                            setA = EmptySet.c;
                        }
                    } catch (SQLiteException e3) {
                        Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e3);
                        setA = EmptySet.c;
                    }
                    if (((c61) this.e).b() && ((c61) this.e).f.compareAndSet(true, false) && !((c61) this.e).a.h().u0().S()) {
                        nh2 nh2VarU0 = ((c61) this.e).a.h().u0();
                        nh2VarU0.k0();
                        try {
                            setA = a();
                            nh2VarU0.j0();
                            if (!setA.isEmpty()) {
                                c61 c61Var = (c61) this.e;
                                synchronized (c61Var.j) {
                                    Iterator it = c61Var.j.iterator();
                                    while (true) {
                                        o62 o62Var = (o62) it;
                                        if (o62Var.hasNext()) {
                                            ((b61) ((Map.Entry) o62Var.next()).getValue()).a(setA);
                                        }
                                    }
                                }
                                return;
                            }
                            return;
                        } finally {
                            nh2VarU0.m();
                        }
                    }
                    return;
                } finally {
                    lock.unlock();
                    ((c61) this.e).getClass();
                }
            case 10:
                Object obj2 = this.e;
                try {
                    ((Worker) obj2).m.j(((Worker) obj2).f());
                    return;
                } catch (Throwable th) {
                    ((Worker) obj2).m.k(th);
                    return;
                }
            case 11:
                try {
                    ((Runnable) this.e).run();
                    return;
                } catch (Exception e4) {
                    ln1.j("Executor", "Background execution failure.", e4);
                    return;
                }
            case 12:
                synchronized (((i23) this.e).k) {
                    try {
                        Object obj3 = ((i23) this.e).l;
                        if (((pp1) obj3) != null) {
                            ((pp1) obj3).g();
                        }
                    } finally {
                    }
                    break;
                }
                return;
            case 13:
                View view2 = (View) this.e;
                ((InputMethodManager) view2.getContext().getSystemService("input_method")).showSoftInput(view2, 1);
                return;
            case 14:
                for (File file : ((com.google.android.play.core.assetpacks.c) this.e).e()) {
                    if (file.listFiles() != null) {
                        com.google.android.play.core.assetpacks.c.f(file);
                        long jB = com.google.android.play.core.assetpacks.c.b(file, false);
                        if (r0.b.a() != jB) {
                            try {
                                new File(new File(file, String.valueOf(jB)), "stale.tmp").createNewFile();
                            } catch (IOException unused) {
                                com.google.android.play.core.assetpacks.c.c.c("Could not write staleness marker.", new Object[0]);
                            }
                        }
                        for (File file2 : file.listFiles()) {
                            com.google.android.play.core.assetpacks.c.f(file2);
                        }
                        break;
                    }
                }
                return;
            case 15:
                m23 m23Var = (m23) this.e;
                d33 d33Var = (d33) ((h13) m23Var.b).a();
                com.google.android.play.core.assetpacks.c cVar = m23Var.a;
                cVar.getClass();
                HashMap map = new HashMap();
                tz2 tz2Var = com.google.android.play.core.assetpacks.c.c;
                HashMap map2 = new HashMap();
                try {
                    for (File file3 : cVar.e()) {
                        String strL = cVar.l(file3.getName());
                        if (strL == null) {
                            o03Var = null;
                        } else {
                            File file4 = new File(strL, "assets");
                            if (file4.isDirectory()) {
                                o03Var = new o03(strL, file4.getCanonicalPath());
                            } else {
                                tz2Var.c("Failed to find assets directory: %s", file4);
                                o03Var = null;
                            }
                        }
                        if (o03Var != null) {
                            map2.put(file3.getName(), o03Var);
                        }
                        break;
                    }
                } catch (IOException e5) {
                    tz2Var.c("Could not process directory while scanning installed packs: %s", e5);
                }
                for (String str : map2.keySet()) {
                    map.put(str, Long.valueOf(com.google.android.play.core.assetpacks.c.b(new File(new File(cVar.d(), str), String.valueOf((int) com.google.android.play.core.assetpacks.c.b(new File(cVar.d(), str), true))), true)));
                }
                wc0 wc0VarB = d33Var.b(map);
                h13 h13Var = (h13) m23Var.d;
                ((s23) wc0VarB.c).a(new i03((Executor) h13Var.a(), new hk0(18, cVar)));
                wc0VarB.i();
                ((s23) wc0VarB.c).a(new i03((Executor) h13Var.a(), u11.v));
                wc0VarB.i();
                return;
            case 16:
                ((ThreadLocal) ((gh2) this.e).m).set(Boolean.TRUE);
                return;
            case 17:
                ((com.spotify.audiofocus.coreinternal.a) this.e).a();
                return;
            case 18:
                q10 q10Var = (q10) this.e;
                NativeApplicationScopeImpl nativeApplicationScopeImpl = q10Var.f;
                if (nativeApplicationScopeImpl == null) {
                    v03.Q("nativeCoreApplicationScopeImpl");
                    throw null;
                }
                nativeApplicationScopeImpl.prepareForShutdown();
                NativeApplicationScopeImpl nativeApplicationScopeImpl2 = q10Var.f;
                if (nativeApplicationScopeImpl2 != null) {
                    nativeApplicationScopeImpl2.destroy();
                    return;
                } else {
                    v03.Q("nativeCoreApplicationScopeImpl");
                    throw null;
                }
            case 19:
                b7 b7Var = (b7) this.e;
                pl2 pl2Var = b7Var.c;
                if (pl2Var == null) {
                    return;
                }
                Iterator it2 = b7Var.d.iterator();
                while (it2.hasNext()) {
                    pl2Var.a((vk2) it2.next());
                }
                b7Var.d.clear();
                return;
            default:
                d10.a((d10) this.e);
                return;
        }
    }
}
