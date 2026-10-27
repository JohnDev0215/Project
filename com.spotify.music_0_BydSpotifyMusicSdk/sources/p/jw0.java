package p;

import android.os.SystemClock;
import com.google.common.base.Optional;
import com.spotify.eventsender.droppedevents.EventState;
import com.spotify.messages.RejectedClientEventNonAuth;
import io.reactivex.rxjava3.core.Flowable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class jw0 implements vi0 {
    public final ti0 a;
    public final ri0 b;
    public final ub0 c;
    public final e02 d;
    public final wc1 e;
    public final wg1 f;
    public final zb0 g;
    public final xt h;
    public final Runnable i;
    public final xy1 j;
    public final pj0 k;
    public long l;
    public final io.reactivex.rxjava3.processors.b m;

    /* JADX WARN: Multi-variable type inference failed */
    public jw0(ti0 ti0Var, ri0 ri0Var, wb0 wb0Var, e02 e02Var, h02 h02Var, wc1 wc1Var, wg1 wg1Var, zb0 zb0Var, io.reactivex.rxjava3.internal.schedulers.i iVar, xt xtVar, hj0 hj0Var, ij0 ij0Var, oj0 oj0Var) {
        Flowable dVar;
        Flowable flowable;
        v03.j(ri0Var, "eventOwnerProvider");
        v03.j(wc1Var, "logger");
        this.a = ti0Var;
        this.b = ri0Var;
        this.c = wb0Var;
        this.d = e02Var;
        this.e = wc1Var;
        this.f = wg1Var;
        this.g = zb0Var;
        this.h = xtVar;
        this.i = hj0Var;
        this.j = ij0Var;
        this.k = oj0Var;
        io.reactivex.rxjava3.processors.b bVar = new io.reactivex.rxjava3.processors.b();
        this.m = bVar;
        int i = Flowable.c;
        io.reactivex.rxjava3.internal.functions.k.c(i, "capacity");
        io.reactivex.rxjava3.internal.operators.flowable.a0 a0Var = new io.reactivex.rxjava3.internal.operators.flowable.a0(bVar, i);
        io.reactivex.rxjava3.internal.functions.k.c(i, "bufferSize");
        io.reactivex.rxjava3.internal.operators.flowable.z zVar = new io.reactivex.rxjava3.internal.operators.flowable.z(a0Var, iVar, i);
        int i2 = 1;
        du duVar = new du(i2, this);
        io.reactivex.rxjava3.internal.functions.k.c(2, "prefetch");
        if (zVar instanceof io.reactivex.rxjava3.operators.e) {
            Object obj = ((io.reactivex.rxjava3.operators.e) zVar).get();
            if (obj == null) {
                flowable = io.reactivex.rxjava3.internal.operators.flowable.l.e;
                flowable.l(Long.MAX_VALUE, new hw0(this)).subscribe(new iw0(this, 0), new iw0(this, i2));
            }
            dVar = new io.reactivex.rxjava3.internal.operators.flowable.p0(duVar, obj);
        } else {
            dVar = new io.reactivex.rxjava3.internal.operators.flowable.d(zVar, duVar);
        }
        flowable = dVar;
        flowable.l(Long.MAX_VALUE, new hw0(this)).subscribe(new iw0(this, 0), new iw0(this, i2));
    }

    public static final void a(jw0 jw0Var, ts1 ts1Var) {
        String str;
        jw0Var.getClass();
        e02 e02Var = jw0Var.d;
        e02Var.getClass();
        String str2 = ts1Var.a;
        v03.j(str2, "eventName");
        TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        Iterator it = e02Var.b.iterator();
        if (it.hasNext()) {
            u4.z(it.next());
            throw null;
        }
        Iterator it2 = e02Var.b.iterator();
        if (it2.hasNext()) {
            u4.z(it2.next());
            throw null;
        }
        boolean z = ((zl1) ((zi0) jw0Var.j.get())).k;
        Optional optionalA = ((te) jw0Var.b).a();
        boolean z2 = ts1Var.d;
        if (z2 && (str = ts1Var.c) != null && str.length() != 0) {
            jw0Var.c(ts1Var, str);
            return;
        }
        if (!z2 || (optionalA.d() && !((String) optionalA.c()).isEmpty())) {
            if (z2) {
                jw0Var.c(ts1Var, (String) optionalA.c());
                return;
            } else {
                jw0Var.c(ts1Var, null);
                return;
            }
        }
        ((wb0) jw0Var.c).g(str2, EventState.REJECTED_CLIENT);
        v03.i(String.format("An authenticated event %s was dropped by EventSender because the SDK failed to obtain user name. Either you are trying to log an authenticated event before login happens, or you are trying to log before EventSender is been properly initialized.\n\nFor more information see: https://backstage.spotify.net/docs/gabito-docs/event-delivery-sdks/android/limitations/#music-app-only-sending-authenticated-events or reach out to #gabito-users slack channel.\n", Arrays.copyOf(new Object[]{str2}, 1)), "format(format, *args)");
        ((u11) jw0Var.e).getClass();
        v22 v22VarG = RejectedClientEventNonAuth.g();
        v22VarG.d(str2);
        v22VarG.e("UNKNOWN_OWNER");
        byte[] byteArray = v22VarG.build().toByteArray();
        v03.i(byteArray, "buildUnknownOwnerEvent(\n…          ).toByteArray()");
        String str3 = ts1Var.e;
        ((u11) jw0Var.h).getClass();
        jw0Var.c(new ts1(System.currentTimeMillis(), "RejectedClientEventNonAuth", null, str3, false, byteArray), null);
    }

    public final void b(String str, String str2, byte[] bArr) {
        v03.j(bArr, "payload");
        boolean z = !ng2.a0(str, "NonAuth", false);
        String str3 = (str2 == null || !z) ? null : str2;
        ((wb0) this.c).g(str, EventState.ENTERED);
        this.g.getClass();
        ((u11) this.h).getClass();
        this.m.onNext(new ts1(System.currentTimeMillis(), str, str3, null, z, bArr));
    }

    public final void c(ts1 ts1Var, String str) {
        byte[] bArr = ts1Var.b;
        int length = bArr.length;
        String str2 = ts1Var.a;
        if (length > 5120) {
            ((wb0) this.c).g(str2, EventState.REJECTED_CLIENT);
            ti0 ti0Var = this.a;
            v22 v22VarG = RejectedClientEventNonAuth.g();
            v22VarG.d(str2);
            v22VarG.e("PAYLOAD_SIZE_LIMIT_EXCEEDED");
            ti0Var.b(ts1Var.f, "RejectedClientEventNonAuth", null, ts1Var.e, false, v22VarG.build().toByteArray());
            v03.i(String.format(Locale.US, "Size of event %s exceeds maximum allowed payload size of %d bytes", Arrays.copyOf(new Object[]{str2, 5120}, 2)), "format(locale, format, *args)");
            ((u11) this.e).getClass();
            return;
        }
        this.a.b(ts1Var.f, str2, str, ts1Var.e, ts1Var.d, bArr);
        wg1 wg1Var = this.f;
        wg1Var.getClass();
        v03.j(str2, "eventName");
        if (((Set) wg1Var.a).contains(str2)) {
            ((u11) this.h).getClass();
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            if (jElapsedRealtime - this.l < 30000) {
                return;
            }
            this.l = jElapsedRealtime;
            this.i.run();
        }
    }
}
