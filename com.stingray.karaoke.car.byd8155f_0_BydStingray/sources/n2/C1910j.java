package n2;

import android.hardware.bydauto.TsNormalNum;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: renamed from: n2.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1910j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final x2.e f19674a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f19675b;
    public final long c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f19676e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f19677f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long f19678g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final HashMap f19679h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f19680i;

    public C1910j() {
        x2.e eVar = new x2.e();
        a("bufferForPlaybackMs", "0", 2500, 0);
        a("bufferForPlaybackAfterRebufferMs", "0", TsNormalNum.THOUSAND_FIVE, 0);
        a("minBufferMs", "bufferForPlaybackMs", 50000, 2500);
        a("minBufferMs", "bufferForPlaybackAfterRebufferMs", 50000, TsNormalNum.THOUSAND_FIVE);
        a("maxBufferMs", "minBufferMs", 50000, 50000);
        a("backBufferDurationMs", "0", 0, 0);
        this.f19674a = eVar;
        long j3 = 50000;
        this.f19675b = AbstractC1343A.H(j3);
        this.c = AbstractC1343A.H(j3);
        this.d = AbstractC1343A.H(2500);
        this.f19676e = AbstractC1343A.H(TsNormalNum.THOUSAND_FIVE);
        this.f19677f = -1;
        this.f19678g = AbstractC1343A.H(0);
        this.f19679h = new HashMap();
        this.f19680i = -1L;
    }

    public static void a(String str, String str2, int i7, int i10) {
        AbstractC1346c.b(str + " cannot be less than " + str2, i7 >= i10);
    }

    public final int b() {
        Iterator it = this.f19679h.values().iterator();
        int i7 = 0;
        while (it.hasNext()) {
            i7 += ((C1909i) it.next()).f19673b;
        }
        return i7;
    }

    public final boolean c(E e10) {
        int i7;
        C1909i c1909i = (C1909i) this.f19679h.get(e10.f19532a);
        c1909i.getClass();
        x2.e eVar = this.f19674a;
        synchronized (eVar) {
            i7 = eVar.d * eVar.f23726b;
        }
        boolean z6 = i7 >= b();
        float f10 = e10.c;
        long j3 = this.c;
        long jMin = this.f19675b;
        if (f10 > 1.0f) {
            jMin = Math.min(AbstractC1343A.t(f10, jMin), j3);
        }
        long jMax = Math.max(jMin, 500000L);
        long j10 = e10.f19533b;
        if (j10 < jMax) {
            c1909i.f19672a = !z6;
            if (z6 && j10 < 500000) {
                AbstractC1346c.z("DefaultLoadControl", "Target buffer size reached with less than 500ms of buffered media data.");
            }
        } else if (j10 >= j3 || z6) {
            c1909i.f19672a = false;
        }
        return c1909i.f19672a;
    }

    public final void d() {
        if (!this.f19679h.isEmpty()) {
            this.f19674a.a(b());
            return;
        }
        x2.e eVar = this.f19674a;
        synchronized (eVar) {
            if (eVar.f23725a) {
                eVar.a(0);
            }
        }
    }
}
