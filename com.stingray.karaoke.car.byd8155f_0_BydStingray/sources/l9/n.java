package l9;

import android.content.Context;
import android.hardware.bydauto.TsNormalNum;
import android.media.MediaDrmException;
import android.media.MediaExtractor;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import androidx.lifecycle.EnumC0981t;
import androidx.recyclerview.widget.RecyclerView;
import c9.D;
import e2.M;
import j4.C1540a;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import k2.u;
import l5.C1778f;
import m2.InterfaceC1794a;
import n0.AbstractC1858H;
import n0.C1856F;
import n0.InterfaceC1866P;
import n3.C1945j;
import n3.r;
import n3.y;
import n4.C;
import n4.C1954B;
import n7.AbstractC2000o;
import q2.C2172c;
import q2.q;
import q2.s;
import r9.C2315e;
import r9.C2319i;
import u2.a0;

/* JADX INFO: loaded from: classes.dex */
public final class n implements InterfaceC1866P, C, s, t4.b, a0, x2.i {
    public final /* synthetic */ int l;

    public /* synthetic */ n(int i7) {
        this.l = i7;
    }

    public static final void C(C2315e c2315e, long j3, boolean z6) {
        C2315e c2315e2;
        ReentrantLock reentrantLock = C2315e.f21919h;
        if (C2315e.l == null) {
            C2315e.l = new C2315e();
            C1540a c1540a = new C1540a("Okio Watchdog");
            c1540a.setDaemon(true);
            c1540a.start();
        }
        long jNanoTime = System.nanoTime();
        if (j3 != 0 && z6) {
            c2315e.f21925g = Math.min(j3, c2315e.c() - jNanoTime) + jNanoTime;
        } else if (j3 != 0) {
            c2315e.f21925g = j3 + jNanoTime;
        } else {
            if (!z6) {
                throw new AssertionError();
            }
            c2315e.f21925g = c2315e.c();
        }
        long j10 = c2315e.f21925g - jNanoTime;
        C2315e c2315e3 = C2315e.l;
        A7.m.c(c2315e3);
        while (true) {
            c2315e2 = c2315e3.f21924f;
            if (c2315e2 == null || j10 < c2315e2.f21925g - jNanoTime) {
                break;
            }
            A7.m.c(c2315e2);
            c2315e3 = c2315e2;
        }
        c2315e.f21924f = c2315e2;
        c2315e3.f21924f = c2315e;
        if (c2315e3 == C2315e.l) {
            C2315e.f21920i.signal();
        }
    }

    public static ArrayList G(List list) {
        A7.m.f(list, "protocols");
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (((D) obj) != D.HTTP_1_0) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((D) it.next()).l);
        }
        return arrayList2;
    }

    public static C2315e H() throws InterruptedException {
        C2315e c2315e = C2315e.l;
        A7.m.c(c2315e);
        C2315e c2315e2 = c2315e.f21924f;
        if (c2315e2 == null) {
            long jNanoTime = System.nanoTime();
            C2315e.f21920i.await(C2315e.f21921j, TimeUnit.MILLISECONDS);
            C2315e c2315e3 = C2315e.l;
            A7.m.c(c2315e3);
            if (c2315e3.f21924f != null || System.nanoTime() - jNanoTime < C2315e.f21922k) {
                return null;
            }
            return C2315e.l;
        }
        long jNanoTime2 = c2315e2.f21925g - System.nanoTime();
        if (jNanoTime2 > 0) {
            C2315e.f21920i.await(jNanoTime2, TimeUnit.NANOSECONDS);
            return null;
        }
        C2315e c2315e4 = C2315e.l;
        A7.m.c(c2315e4);
        c2315e4.f21924f = c2315e2.f21924f;
        c2315e2.f21924f = null;
        c2315e2.f21923e = 2;
        return c2315e2;
    }

    public static byte[] I(List list) {
        A7.m.f(list, "protocols");
        C2319i c2319i = new C2319i();
        for (String str : G(list)) {
            c2319i.X(str.length());
            c2319i.c0(str);
        }
        return c2319i.C(c2319i.f21932m);
    }

    public static C1945j K(Context context, y yVar, Bundle bundle, EnumC0981t enumC0981t, r rVar) {
        String string = UUID.randomUUID().toString();
        A7.m.e(string, "randomUUID().toString()");
        A7.m.f(yVar, "destination");
        A7.m.f(enumC0981t, "hostLifecycleState");
        return new C1945j(context, yVar, bundle, enumC0981t, rVar, string, null);
    }

    public static boolean L() {
        return "Dalvik".equals(System.getProperty("java.vm.name"));
    }

    @Override // n4.C
    public void A(MediaExtractor mediaExtractor, Object obj) throws IOException {
        mediaExtractor.setDataSource(new C1954B((ByteBuffer) obj));
    }

    @Override // t4.b
    public void J() {
    }

    @Override // q2.s
    public void a() {
    }

    @Override // q2.s
    public void b(byte[] bArr, byte[] bArr2) {
        throw new IllegalStateException();
    }

    @Override // q2.s
    public Map c(byte[] bArr) {
        throw new IllegalStateException();
    }

    @Override // x2.i
    public long d(C1.j jVar) {
        Throwable cause = (IOException) jVar.f944m;
        if (!(cause instanceof M) && !(cause instanceof FileNotFoundException) && !(cause instanceof u) && !(cause instanceof x2.m)) {
            int i7 = k2.j.f17663m;
            while (cause != null) {
                if (!(cause instanceof k2.j) || ((k2.j) cause).l != 2008) {
                    cause = cause.getCause();
                }
            }
            return Math.min((jVar.l - 1) * 1000, TsNormalNum.THOUSAND_FIVE);
        }
        return -9223372036854775807L;
    }

    @Override // q2.s
    public void e(byte[] bArr) {
    }

    @Override // q2.s
    public byte[] f(byte[] bArr, byte[] bArr2) {
        throw new IllegalStateException();
    }

    @Override // q2.s
    public q2.r h() {
        throw new IllegalStateException();
    }

    @Override // q2.s
    public void j(byte[] bArr) {
        throw new IllegalStateException();
    }

    @Override // u2.a0
    public boolean k() {
        return true;
    }

    @Override // q2.s
    public q l(byte[] bArr, List list, int i7, HashMap map) {
        throw new IllegalStateException();
    }

    @Override // q2.s
    public void m(C2172c c2172c) {
    }

    @Override // q2.s
    public int o() {
        return 1;
    }

    @Override // u2.a0
    public int p(C1778f c1778f, m2.g gVar, int i7) {
        gVar.f15673m = 4;
        return -4;
    }

    @Override // n4.C
    public void q(MediaMetadataRetriever mediaMetadataRetriever, Object obj) {
        mediaMetadataRetriever.setDataSource(new C1954B((ByteBuffer) obj));
    }

    @Override // u2.a0
    public void r() {
    }

    @Override // u2.a0
    public int s(long j3) {
        return 0;
    }

    public String toString() {
        switch (this.l) {
            case 1:
                return "RectangleShape";
            default:
                return super.toString();
        }
    }

    @Override // n0.InterfaceC1866P
    public AbstractC1858H v(long j3, a1.k kVar, a1.b bVar) {
        return new C1856F(h6.f.a(0L, j3));
    }

    @Override // q2.s
    public InterfaceC1794a w(byte[] bArr) {
        throw new IllegalStateException();
    }

    @Override // q2.s
    public boolean x(String str, byte[] bArr) {
        throw new IllegalStateException();
    }

    @Override // x2.i
    public int y(int i7) {
        return i7 == 7 ? 6 : 3;
    }

    @Override // q2.s
    public byte[] z() throws MediaDrmException {
        throw new MediaDrmException("Attempting to open a session using a dummy ExoMediaDrm.");
    }

    public n(RecyclerView recyclerView) {
        this.l = 10;
    }
}
