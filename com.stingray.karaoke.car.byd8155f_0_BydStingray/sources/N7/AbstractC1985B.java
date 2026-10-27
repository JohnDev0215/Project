package n7;

import H7.H;
import N0.L;
import Q.c1;
import Q5.C0593m;
import T.C0732b0;
import T.C0735d;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import T.S;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.metrics.LogSessionId;
import android.media.metrics.MediaMetricsManager;
import android.os.Bundle;
import androidx.compose.ui.graphics.painter.BitmapPainter;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import h2.AbstractC1346c;
import i0.AbstractC1397a;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import io.sentry.K;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import n0.C1877f;
import n2.C1923x;
import p6.C2110a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: n7.B, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1985B {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f20153a = 0;

    public static BitmapPainter a(C1877f c1877f, int i7) {
        Bitmap bitmap = c1877f.f19431a;
        BitmapPainter bitmapPainter = new BitmapPainter(c1877f, 0L, H.c(bitmap.getWidth(), bitmap.getHeight()));
        bitmapPainter.f11590t = i7;
        return bitmapPainter;
    }

    public static final void b(g0.o oVar, int i7, long j3, long j10, L l, Y0.i iVar, C0761q c0761q, int i10) {
        int i11;
        A7.m.f(l, "textStyle");
        c0761q.S(492303993);
        if ((i10 & 6) == 0) {
            i11 = (c0761q.f(oVar) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= c0761q.d(i7) ? 32 : 16;
        }
        if ((i10 & 384) == 0) {
            i11 |= c0761q.e(j3) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= c0761q.e(j10) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i11 |= c0761q.f(l) ? 16384 : 8192;
        }
        if ((196608 & i10) == 0) {
            i11 |= c0761q.f(iVar) ? 131072 : 65536;
        }
        if ((74899 & i11) == 74898 && c0761q.x()) {
            c0761q.K();
        } else {
            c0761q.Q(-2028874775);
            Object objG = c0761q.G();
            S s6 = C0753m.f8270a;
            if (objG == s6) {
                objG = C0735d.M(i7);
                c0761q.a0(objG);
            }
            C0732b0 c0732b0 = (C0732b0) objG;
            c0761q.p(false);
            Integer numValueOf = Integer.valueOf(i7);
            c0761q.Q(-2028871334);
            boolean z6 = (i11 & 112) == 32;
            Object objG2 = c0761q.G();
            if (z6 || objG2 == s6) {
                objG2 = new C2110a(i7, c0732b0, null);
                c0761q.a0(objG2);
            }
            c0761q.p(false);
            C0735d.f(c0761q, numValueOf, (InterfaceC2785n) objG2);
            int i12 = c0732b0.i();
            Locale locale = ((Context) c0761q.k(AndroidCompositionLocals_androidKt.f11688b)).getResources().getConfiguration().getLocales().get(0);
            A7.m.c(locale);
            String str = NumberFormat.getNumberInstance(locale).format(Integer.valueOf(i12));
            A7.m.e(str, "format(...)");
            c1.b(str, oVar, j3, j10, null, null, null, 0L, null, iVar, 0L, 0, false, 0, 0, null, l, c0761q, ((i11 << 3) & 112) | (i11 & 896) | (i11 & 7168) | ((i11 << 12) & 1879048192), (i11 << 6) & 3670016, 65008);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0593m(oVar, i7, j3, j10, l, iVar, i10);
        }
    }

    public static final void c(Closeable closeable, Throwable th) throws IllegalAccessException, IOException, InvocationTargetException {
        if (closeable != null) {
            if (th == null) {
                closeable.close();
                return;
            }
            try {
                closeable.close();
            } catch (Throwable th2) {
                h6.q.l(th, th2);
            }
        }
    }

    public static y8.n d(String str, List list) {
        y8.m mVar;
        A7.m.f(str, "debugName");
        O8.g gVar = new O8.g();
        Iterator it = list.iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            mVar = y8.m.f24213b;
            if (!zHasNext) {
                break;
            }
            y8.n nVar = (y8.n) it.next();
            if (nVar != mVar) {
                if (nVar instanceof y8.a) {
                    y8.n[] nVarArr = ((y8.a) nVar).c;
                    A7.m.f(nVarArr, "elements");
                    gVar.addAll(AbstractC1996k.x(nVarArr));
                } else {
                    gVar.add(nVar);
                }
            }
        }
        int i7 = gVar.l;
        return i7 != 0 ? i7 != 1 ? new y8.a(str, (y8.n[]) gVar.toArray(new y8.n[0])) : (y8.n) gVar.get(0) : mVar;
    }

    public static h8.p e(h6.e eVar) {
        if (eVar instanceof n8.e) {
            n8.e eVar2 = (n8.e) eVar;
            String str = eVar2.c;
            A7.m.f(str, "name");
            String str2 = eVar2.d;
            A7.m.f(str2, "desc");
            return new h8.p(str.concat(str2));
        }
        if (!(eVar instanceof n8.d)) {
            throw new A9.k();
        }
        n8.d dVar = (n8.d) eVar;
        String str3 = dVar.c;
        A7.m.f(str3, "name");
        String str4 = dVar.d;
        A7.m.f(str4, "desc");
        return new h8.p(str3 + '#' + str4);
    }

    public static Date f() {
        return Calendar.getInstance(io.sentry.vendor.gson.internal.bind.util.a.f16958a).getTime();
    }

    public static Date g(long j3) {
        Calendar calendar = Calendar.getInstance(io.sentry.vendor.gson.internal.bind.util.a.f16958a);
        calendar.setTimeInMillis(j3);
        return calendar.getTime();
    }

    public static Date h(String str) {
        try {
            return io.sentry.vendor.gson.internal.bind.util.a.c(str, new ParsePosition(0));
        } catch (ParseException unused) {
            throw new IllegalArgumentException(M5.b.g("timestamp is not ISO format ", str));
        }
    }

    public static Date i(String str) {
        try {
            return g(new BigDecimal(str).setScale(3, RoundingMode.DOWN).movePointRight(3).longValue());
        } catch (NumberFormatException unused) {
            throw new IllegalArgumentException(M5.b.g("timestamp is not millis format ", str));
        }
    }

    public static Intent j(Context context, ComponentName componentName) throws PackageManager.NameNotFoundException {
        String strK = k(context, componentName);
        if (strK == null) {
            return null;
        }
        ComponentName componentName2 = new ComponentName(componentName.getPackageName(), strK);
        return k(context, componentName2) == null ? Intent.makeMainActivity(componentName2) : new Intent().setComponent(componentName2);
    }

    public static String k(Context context, ComponentName componentName) throws PackageManager.NameNotFoundException {
        String string;
        ActivityInfo activityInfo = context.getPackageManager().getActivityInfo(componentName, 269222528);
        String str = activityInfo.parentActivityName;
        if (str != null) {
            return str;
        }
        Bundle bundle = activityInfo.metaData;
        if (bundle == null || (string = bundle.getString("android.support.PARENT_ACTIVITY")) == null) {
            return null;
        }
        if (string.charAt(0) != '.') {
            return string;
        }
        return context.getPackageName() + string;
    }

    public static String l(long j3) {
        long j10 = j3 / ((long) 1000);
        long j11 = 60;
        return (j10 / j11) + ":" + Q8.h.Q0(2, String.valueOf(j10 % j11));
    }

    public static String m(Date date) {
        TimeZone timeZone = io.sentry.vendor.gson.internal.bind.util.a.f16958a;
        Locale locale = Locale.US;
        TimeZone timeZone2 = io.sentry.vendor.gson.internal.bind.util.a.f16958a;
        GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone2, locale);
        gregorianCalendar.setTime(date);
        StringBuilder sb = new StringBuilder(23 + (timeZone2.getRawOffset() == 0 ? 1 : 6));
        io.sentry.vendor.gson.internal.bind.util.a.b(gregorianCalendar.get(1), 4, sb);
        sb.append('-');
        io.sentry.vendor.gson.internal.bind.util.a.b(gregorianCalendar.get(2) + 1, 2, sb);
        sb.append('-');
        io.sentry.vendor.gson.internal.bind.util.a.b(gregorianCalendar.get(5), 2, sb);
        sb.append('T');
        io.sentry.vendor.gson.internal.bind.util.a.b(gregorianCalendar.get(11), 2, sb);
        sb.append(':');
        io.sentry.vendor.gson.internal.bind.util.a.b(gregorianCalendar.get(12), 2, sb);
        sb.append(':');
        io.sentry.vendor.gson.internal.bind.util.a.b(gregorianCalendar.get(13), 2, sb);
        sb.append('.');
        io.sentry.vendor.gson.internal.bind.util.a.b(gregorianCalendar.get(14), 3, sb);
        int offset = timeZone2.getOffset(gregorianCalendar.getTimeInMillis());
        if (offset != 0) {
            int i7 = offset / 60000;
            int iAbs = Math.abs(i7 / 60);
            int iAbs2 = Math.abs(i7 % 60);
            sb.append(offset >= 0 ? '+' : '-');
            io.sentry.vendor.gson.internal.bind.util.a.b(iAbs, 2, sb);
            sb.append(':');
            io.sentry.vendor.gson.internal.bind.util.a.b(iAbs2, 2, sb);
        } else {
            sb.append('Z');
        }
        return sb.toString();
    }

    public static final void n(ScheduledExecutorService scheduledExecutorService, F1 f12) {
        A7.m.f(f12, "options");
        synchronized (scheduledExecutorService) {
            if (!scheduledExecutorService.isShutdown()) {
                scheduledExecutorService.shutdown();
            }
            try {
                if (!scheduledExecutorService.awaitTermination(f12.getShutdownTimeoutMillis(), TimeUnit.MILLISECONDS)) {
                    scheduledExecutorService.shutdownNow();
                }
            } catch (InterruptedException unused) {
                scheduledExecutorService.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void o(Class cls, Object obj, K k10) {
        k10.k(EnumC1495p1.DEBUG, "%s is not %s", obj != null ? obj.getClass().getCanonicalName() : "Hint", cls.getCanonicalName());
    }

    public static LinkedHashSet p(Set set, Object obj) {
        A7.m.f(set, "<this>");
        LinkedHashSet linkedHashSet = new LinkedHashSet(z.q(set.size()));
        boolean z6 = false;
        for (Object obj2 : set) {
            boolean z10 = true;
            if (!z6 && A7.m.a(obj2, obj)) {
                z6 = true;
                z10 = false;
            }
            if (z10) {
                linkedHashSet.add(obj2);
            }
        }
        return linkedHashSet;
    }

    public static LinkedHashSet q(Set set, Iterable iterable) {
        A7.m.f(set, "<this>");
        A7.m.f(iterable, "elements");
        Integer numValueOf = iterable instanceof Collection ? Integer.valueOf(((Collection) iterable).size()) : null;
        LinkedHashSet linkedHashSet = new LinkedHashSet(z.q(numValueOf != null ? set.size() + numValueOf.intValue() : set.size() * 2));
        linkedHashSet.addAll(set);
        AbstractC2004s.L(linkedHashSet, iterable);
        return linkedHashSet;
    }

    public static LinkedHashSet r(Set set, Object obj) {
        A7.m.f(set, "<this>");
        LinkedHashSet linkedHashSet = new LinkedHashSet(z.q(set.size() + 1));
        linkedHashSet.addAll(set);
        linkedHashSet.add(obj);
        return linkedHashSet;
    }

    public static o2.l s(Context context, C1923x c1923x, boolean z6, String str) {
        MediaMetricsManager mediaMetricsManagerC = AbstractC1397a.c(context.getSystemService("media_metrics"));
        o2.j jVar = mediaMetricsManagerC == null ? null : new o2.j(context, mediaMetricsManagerC.createPlaybackSession());
        if (jVar == null) {
            AbstractC1346c.z("ExoPlayerImpl", "MediaMetricsService unavailable.");
            return new o2.l(LogSessionId.LOG_SESSION_ID_NONE, str);
        }
        if (z6) {
            c1923x.getClass();
            o2.e eVar = c1923x.f19776s;
            eVar.getClass();
            eVar.f20408q.a(jVar);
        }
        return new o2.l(jVar.c.getSessionId(), str);
    }

    public static Set t(Object obj) {
        Set setSingleton = Collections.singleton(obj);
        A7.m.e(setSingleton, "singleton(...)");
        return setSingleton;
    }

    public static final void u(ScheduledExecutorService scheduledExecutorService, F1 f12, String str, Runnable runnable) {
        A7.m.f(scheduledExecutorService, "<this>");
        A7.m.f(f12, "options");
        A7.m.f(str, "taskName");
        try {
            scheduledExecutorService.submit(new T1.k(runnable, f12, str, 6));
        } catch (Throwable th) {
            f12.getLogger().s(EnumC1495p1.ERROR, android.hardware.bydauto.ac.tsimpl.a.i("Failed to submit task ", str, " to executor"), th);
        }
    }

    public static final String v(float f10) {
        if (Float.isNaN(f10)) {
            return "NaN";
        }
        if (Float.isInfinite(f10)) {
            return f10 < 0.0f ? "-Infinity" : "Infinity";
        }
        int iMax = Math.max(1, 0);
        float fPow = (float) Math.pow(10.0f, iMax);
        float f11 = f10 * fPow;
        int i7 = (int) f11;
        if (f11 - i7 >= 0.5f) {
            i7++;
        }
        float f12 = i7 / fPow;
        return iMax > 0 ? String.valueOf(f12) : String.valueOf((int) f12);
    }
}
