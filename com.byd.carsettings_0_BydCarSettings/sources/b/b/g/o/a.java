package b.b.g.o;

import android.car.Car;
import android.car.media.CarAudioManager;
import android.car.media.CarEnhancedAudioManager;
import android.content.Context;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: BydCarAudio.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f1306a = 18;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static int f1307b = 4;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static int f1308c = 10;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static int f1309d = 10;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static int f1310e = 15;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public CarAudioManager f1311f;
    public CarEnhancedAudioManager g;
    public d h;
    public c i;
    public final ConcurrentHashMap<String, b.b.g.o.g.c> j;
    public final ConcurrentHashMap<String, b.b.g.o.g.b> k;

    /* JADX INFO: renamed from: b.b.g.o.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BydCarAudio.java */
    public class C0058a implements Car.CarServiceLifecycleListener {
        public C0058a() {
        }

        @Override // android.car.Car.CarServiceLifecycleListener
        public void onLifecycleChanged(Car car, boolean z) {
            b.b.m.c.a.a("BydCarAudio", "--onLifecycleChanged--b: " + z);
            C0058a c0058a = null;
            if (!z) {
                if (a.this.f1311f != null) {
                    a.this.f1311f.unregisterCarVolumeCallback(a.this.h);
                    a.this.f1311f = null;
                }
                if (a.this.g != null) {
                    a.this.g.unregisterCarGroupMutingCallback(a.this.i);
                    a.this.g = null;
                    return;
                }
                return;
            }
            a.this.f1311f = (CarAudioManager) car.getCarManager("audio");
            a.this.g = (CarEnhancedAudioManager) car.getCarManager("enhanced_audio");
            b.b.m.c.a.a("BydCarAudio", "--initData--cam: " + a.this.f1311f + ", ceam: " + a.this.g + ", b: " + z);
            if (a.this.h == null) {
                a aVar = a.this;
                aVar.h = new d(aVar, aVar, c0058a);
            }
            if (a.this.f1311f != null) {
                a.this.f1311f.registerCarVolumeCallback(a.this.h);
            }
            if (a.this.i == null) {
                a aVar2 = a.this;
                aVar2.i = new c(aVar2, aVar2, c0058a);
            }
            if (a.this.g != null) {
                a.this.g.registerCarGroupMutingCallback(a.this.i);
            }
        }
    }

    /* JADX INFO: compiled from: BydCarAudio.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f1313a = new a(null);

        public static a a() {
            return f1313a;
        }
    }

    /* JADX INFO: compiled from: BydCarAudio.java */
    public class c extends CarEnhancedAudioManager.CarGroupMutingCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<a> f1314a;

        public /* synthetic */ c(a aVar, a aVar2, C0058a c0058a) {
            this(aVar2);
        }

        public final boolean a() {
            WeakReference<a> weakReference = this.f1314a;
            return weakReference == null || weakReference.get() == null || this.f1314a.get().g == null;
        }

        public void onGroupMuteChanged(int i, int i2, int i3) {
            boolean zIsVolumeGroupMuted = a.this.g.isVolumeGroupMuted(i, i2);
            b.b.m.c.a.a("BydCarAudio", "SoundMutingCallback--onGroupMuteChanged--zoneId: " + i + ", groupId: " + i2 + ", muteState: " + zIsVolumeGroupMuted);
            if (a()) {
                return;
            }
            if (i2 == a.this.f1311f.getVolumeGroupIdForUsage(2)) {
                this.f1314a.get().K(zIsVolumeGroupMuted);
                return;
            }
            if (i2 == a.this.f1311f.getVolumeGroupIdForUsage(12)) {
                this.f1314a.get().I(zIsVolumeGroupMuted);
                return;
            }
            if (i2 == a.this.f1311f.getVolumeGroupIdForUsage(6)) {
                this.f1314a.get().M(zIsVolumeGroupMuted);
                return;
            }
            if (i2 == a.this.f1311f.getVolumeGroupIdForUsage(16)) {
                this.f1314a.get().Q(zIsVolumeGroupMuted);
            } else if (i2 == a.this.f1311f.getVolumeGroupIdForUsage(1)) {
                this.f1314a.get().G(zIsVolumeGroupMuted);
            } else if (i2 == a.this.f1311f.getVolumeGroupIdForUsage(a.f1306a)) {
                this.f1314a.get().O(zIsVolumeGroupMuted);
            }
        }

        public c(a aVar) {
            this.f1314a = new WeakReference<>(aVar);
        }
    }

    /* JADX INFO: compiled from: BydCarAudio.java */
    public class d extends CarAudioManager.CarVolumeCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<a> f1316a;

        public /* synthetic */ d(a aVar, a aVar2, C0058a c0058a) {
            this(aVar2);
        }

        public final boolean a() {
            WeakReference<a> weakReference = this.f1316a;
            return weakReference == null || weakReference.get() == null || this.f1316a.get().f1311f == null;
        }

        @Override // android.car.media.CarAudioManager.CarVolumeCallback
        public void onGroupVolumeChanged(int i, int i2, int i3) {
            if (a()) {
                return;
            }
            int groupVolume = a.this.f1311f.getGroupVolume(i2);
            b.b.m.c.a.a("BydCarAudio", "SoundVolumeCallback--onGroupVolumeChanged--zoneId: " + i + ", groupId: " + i2 + ", volume: " + groupVolume);
            if (i2 == a.this.f1311f.getVolumeGroupIdForUsage(2)) {
                this.f1316a.get().L(groupVolume);
                return;
            }
            if (i2 == a.this.f1311f.getVolumeGroupIdForUsage(12)) {
                this.f1316a.get().J(groupVolume);
                return;
            }
            if (i2 == a.this.f1311f.getVolumeGroupIdForUsage(6)) {
                this.f1316a.get().N(groupVolume);
                return;
            }
            if (i2 == a.this.f1311f.getVolumeGroupIdForUsage(16)) {
                this.f1316a.get().R(groupVolume);
            } else if (i2 == a.this.f1311f.getVolumeGroupIdForUsage(1)) {
                this.f1316a.get().H(groupVolume);
            } else if (i2 == a.this.f1311f.getVolumeGroupIdForUsage(a.f1306a)) {
                this.f1316a.get().P(groupVolume);
            }
        }

        @Override // android.car.media.CarAudioManager.CarVolumeCallback
        public void onMasterMuteChanged(int i, int i2) {
            b.b.m.c.a.a("BydCarAudio", "SoundVolumeCallback--onMasterMuteChanged--zoneId: " + i + ", flags: " + i2);
        }

        public d(a aVar) {
            this.f1316a = new WeakReference<>(aVar);
        }
    }

    public /* synthetic */ a(C0058a c0058a) {
        this();
    }

    public void A(Context context) {
        if (this.f1311f != null) {
            return;
        }
        Car.createCar(context.getApplicationContext(), null, 1000L, new C0058a());
    }

    public boolean B() {
        CarAudioManager carAudioManager = this.f1311f;
        if (carAudioManager == null || this.g == null) {
            return false;
        }
        return this.g.isVolumeGroupMuted(0, carAudioManager.getVolumeGroupIdForUsage(1));
    }

    public boolean C() {
        CarAudioManager carAudioManager = this.f1311f;
        if (carAudioManager == null || this.g == null) {
            return false;
        }
        boolean zIsVolumeGroupMuted = this.g.isVolumeGroupMuted(0, carAudioManager.getVolumeGroupIdForUsage(12));
        b.b.m.c.a.a("BydCarAudio", "--naviMuted: " + zIsVolumeGroupMuted);
        return zIsVolumeGroupMuted;
    }

    public boolean D() {
        CarAudioManager carAudioManager = this.f1311f;
        if (carAudioManager == null || this.g == null) {
            return false;
        }
        boolean zIsVolumeGroupMuted = this.g.isVolumeGroupMuted(0, carAudioManager.getVolumeGroupIdForUsage(6));
        b.b.m.c.a.a("BydCarAudio", "--isRingTongMute: " + zIsVolumeGroupMuted);
        return zIsVolumeGroupMuted;
    }

    public boolean E() {
        CarAudioManager carAudioManager = this.f1311f;
        if (carAudioManager == null || this.g == null) {
            return false;
        }
        boolean zIsVolumeGroupMuted = this.g.isVolumeGroupMuted(0, carAudioManager.getVolumeGroupIdForUsage(16));
        b.b.m.c.a.a("BydCarAudio", "--isTtsMuted: " + zIsVolumeGroupMuted);
        return zIsVolumeGroupMuted;
    }

    public boolean F() {
        CarAudioManager carAudioManager = this.f1311f;
        if (carAudioManager == null || this.g == null) {
            return false;
        }
        boolean zIsVolumeGroupMuted = this.g.isVolumeGroupMuted(0, carAudioManager.getVolumeGroupIdForUsage(18));
        b.b.m.c.a.a("BydCarAudio", "--isUeMuted: " + zIsVolumeGroupMuted);
        return zIsVolumeGroupMuted;
    }

    public final void G(boolean z) {
        Iterator<b.b.g.o.g.b> it = this.k.values().iterator();
        while (it.hasNext()) {
            it.next().c(z);
        }
    }

    public final void H(int i) {
        Iterator<b.b.g.o.g.c> it = this.j.values().iterator();
        while (it.hasNext()) {
            it.next().c(i);
        }
    }

    public final void I(boolean z) {
        Iterator<b.b.g.o.g.b> it = this.k.values().iterator();
        while (it.hasNext()) {
            it.next().f(z);
        }
    }

    public final void J(int i) {
        Iterator<b.b.g.o.g.c> it = this.j.values().iterator();
        while (it.hasNext()) {
            it.next().b(i);
        }
    }

    public final void K(boolean z) {
        Iterator<b.b.g.o.g.b> it = this.k.values().iterator();
        while (it.hasNext()) {
            it.next().a(z);
        }
    }

    public final void L(int i) {
        Iterator<b.b.g.o.g.c> it = this.j.values().iterator();
        while (it.hasNext()) {
            it.next().f(i);
        }
    }

    public final void M(boolean z) {
        Iterator<b.b.g.o.g.b> it = this.k.values().iterator();
        while (it.hasNext()) {
            it.next().d(z);
        }
    }

    public final void N(int i) {
        Iterator<b.b.g.o.g.c> it = this.j.values().iterator();
        while (it.hasNext()) {
            it.next().a(i);
        }
    }

    public final void O(boolean z) {
        Iterator<b.b.g.o.g.b> it = this.k.values().iterator();
        while (it.hasNext()) {
            it.next().e(z);
        }
    }

    public final void P(int i) {
        Iterator<b.b.g.o.g.c> it = this.j.values().iterator();
        while (it.hasNext()) {
            it.next().e(i);
        }
    }

    public final void Q(boolean z) {
        Iterator<b.b.g.o.g.b> it = this.k.values().iterator();
        while (it.hasNext()) {
            it.next().b(z);
        }
    }

    public final void R(int i) {
        Iterator<b.b.g.o.g.c> it = this.j.values().iterator();
        while (it.hasNext()) {
            it.next().d(i);
        }
    }

    public void S(String str, b.b.g.o.g.b bVar) {
        this.k.put(str, bVar);
    }

    public void T(String str, b.b.g.o.g.c cVar) {
        this.j.put(str, cVar);
    }

    public void U() {
        W(f1308c);
        Z(f1308c);
        b0(f1308c);
        d0(f1308c);
        Y(f1307b);
        b.b.m.c.a.a("BydCarAudio", "resetAllMediaVolume");
    }

    public void V(boolean z) {
        CarAudioManager carAudioManager = this.f1311f;
        if (carAudioManager == null || this.g == null) {
            return;
        }
        this.g.setVolumeGroupMute(0, carAudioManager.getVolumeGroupIdForUsage(1), z, 0);
    }

    public void W(int i) {
        CarAudioManager carAudioManager = this.f1311f;
        if (carAudioManager == null) {
            return;
        }
        this.f1311f.setGroupVolume(carAudioManager.getVolumeGroupIdForUsage(1), i, 0);
    }

    public void X(boolean z) {
        if (this.f1311f == null || this.g == null) {
            return;
        }
        b.b.m.c.a.a("BydCarAudio", "--setNaviMute: " + z);
        this.g.setVolumeGroupMute(0, this.f1311f.getVolumeGroupIdForUsage(12), z, 0);
    }

    public void Y(int i) {
        CarAudioManager carAudioManager = this.f1311f;
        if (carAudioManager == null) {
            return;
        }
        this.f1311f.setGroupVolume(carAudioManager.getVolumeGroupIdForUsage(12), i, 0);
    }

    public void Z(int i) {
        CarAudioManager carAudioManager = this.f1311f;
        if (carAudioManager == null) {
            return;
        }
        this.f1311f.setGroupVolume(carAudioManager.getVolumeGroupIdForUsage(2), i, 0);
    }

    public void a0(boolean z) {
        if (this.f1311f == null || this.g == null) {
            return;
        }
        b.b.m.c.a.a("BydCarAudio", "--setRingTongMute: " + z);
        this.g.setVolumeGroupMute(0, this.f1311f.getVolumeGroupIdForUsage(6), z, 0);
    }

    public void b0(int i) {
        CarAudioManager carAudioManager = this.f1311f;
        if (carAudioManager == null) {
            return;
        }
        this.f1311f.setGroupVolume(carAudioManager.getVolumeGroupIdForUsage(6), i, 0);
    }

    public void c0(boolean z) {
        if (this.f1311f == null || this.g == null) {
            return;
        }
        b.b.m.c.a.a("BydCarAudio", "--setTtsMute: " + z);
        this.g.setVolumeGroupMute(0, this.f1311f.getVolumeGroupIdForUsage(16), z, 0);
    }

    public void d0(int i) {
        CarAudioManager carAudioManager = this.f1311f;
        if (carAudioManager == null) {
            return;
        }
        this.f1311f.setGroupVolume(carAudioManager.getVolumeGroupIdForUsage(16), i, 0);
    }

    public void e0(boolean z) {
        CarAudioManager carAudioManager = this.f1311f;
        if (carAudioManager == null || this.g == null) {
            return;
        }
        this.g.setVolumeGroupMute(0, carAudioManager.getVolumeGroupIdForUsage(18), z, 0);
    }

    public void f0(int i) {
        CarAudioManager carAudioManager = this.f1311f;
        if (carAudioManager == null) {
            return;
        }
        this.f1311f.setGroupVolume(carAudioManager.getVolumeGroupIdForUsage(f1306a), i, 0);
    }

    public void g0(String str) {
        this.k.remove(str);
    }

    public void h0(String str) {
        this.j.remove(str);
    }

    public int u() {
        CarAudioManager carAudioManager = this.f1311f;
        if (carAudioManager == null) {
            return 0;
        }
        return this.f1311f.getGroupVolume(carAudioManager.getVolumeGroupIdForUsage(1));
    }

    public int v() {
        CarAudioManager carAudioManager = this.f1311f;
        if (carAudioManager == null) {
            return 0;
        }
        return this.f1311f.getGroupVolume(carAudioManager.getVolumeGroupIdForUsage(12));
    }

    public int w() {
        CarAudioManager carAudioManager = this.f1311f;
        if (carAudioManager == null) {
            return 0;
        }
        return this.f1311f.getGroupVolume(carAudioManager.getVolumeGroupIdForUsage(2));
    }

    public int x() {
        CarAudioManager carAudioManager = this.f1311f;
        if (carAudioManager == null) {
            return 0;
        }
        return this.f1311f.getGroupVolume(carAudioManager.getVolumeGroupIdForUsage(6));
    }

    public int y() {
        CarAudioManager carAudioManager = this.f1311f;
        if (carAudioManager == null) {
            return 0;
        }
        return this.f1311f.getGroupVolume(carAudioManager.getVolumeGroupIdForUsage(16));
    }

    public int z() {
        CarAudioManager carAudioManager = this.f1311f;
        if (carAudioManager == null) {
            return 0;
        }
        return this.f1311f.getGroupVolume(carAudioManager.getVolumeGroupIdForUsage(f1306a));
    }

    public a() {
        this.j = new ConcurrentHashMap<>();
        this.k = new ConcurrentHashMap<>();
    }
}
