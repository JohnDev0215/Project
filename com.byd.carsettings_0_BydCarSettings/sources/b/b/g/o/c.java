package b.b.g.o;

import android.car.Car;
import android.content.Context;
import android.hardware.bydauto.audio.AbsBYDAutoAudioListener;
import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.hardware.bydauto.audio.tsimpl.BYDCarServiceBase;
import android.hardware.bydauto.audio.tsimpl.IServiceConnectionListener;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.media.AudioFocusInfo;
import b.b.a.f.h;
import b.b.g.o.a;
import com.byd.carsettings.sound.model.DiracLevel;
import com.byd.carsettings.sound.model.DiracMode;
import com.byd.carsettings.sound.model.Manufactor;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: SoundBoxManager.java */
/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f1321a = {1, 2, 3, 4, 5};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f1322b = {17, 18, 19, 20};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f1323c = {17, 18, 19, 20, 21};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f1324d = {33, 34, 35};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public b.b.g.o.h.a f1325e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile int f1326f;
    public volatile int g;
    public volatile int h;
    public volatile int i;
    public volatile int j;
    public BYDAutoAudioDevice k;
    public final ConcurrentHashMap<String, AbsBYDAutoAudioListener> l;
    public BYDCarServiceBase m;
    public e n;
    public volatile boolean o;
    public final IServiceConnectionListener p;
    public f q;

    /* JADX INFO: compiled from: SoundBoxManager.java */
    public class a implements IServiceConnectionListener {

        /* JADX INFO: renamed from: b.b.g.o.c$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: SoundBoxManager.java */
        public class RunnableC0060a implements Runnable {
            public RunnableC0060a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (c.this.q != null) {
                    c.this.q.a();
                }
            }
        }

        public a() {
        }

        @Override // android.hardware.bydauto.audio.tsimpl.IServiceConnectionListener
        public void onServiceConnected(boolean z) {
            b.b.m.c.a.a("SoundBoxManager", "Listener, onServiceConnected:" + z);
            h.a().d(new RunnableC0060a());
        }

        @Override // android.hardware.bydauto.audio.tsimpl.IServiceConnectionListener
        public void onServiceDisconnected(boolean z) {
            b.b.m.c.a.a("SoundBoxManager", "onServiceDisconnected:" + z);
        }
    }

    /* JADX INFO: compiled from: SoundBoxManager.java */
    public class b extends BYDCarServiceBase {
        public b(Context context) {
            super(context);
        }

        @Override // android.hardware.bydauto.audio.tsimpl.BYDCarServiceBase
        public void onServiceConnected(Car car) {
            b.b.m.c.a.a("SoundBoxManager", "init", ", onServiceConnected car:" + car);
        }

        @Override // android.hardware.bydauto.audio.tsimpl.BYDCarServiceBase
        public void onServiceDied() {
            b.b.m.c.a.a("SoundBoxManager", "init", ", onServiceDied:");
        }
    }

    /* JADX INFO: renamed from: b.b.g.o.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SoundBoxManager.java */
    public static /* synthetic */ class C0061c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1330a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final /* synthetic */ int[] f1331b;

        static {
            int[] iArr = new int[DiracMode.values().length];
            f1331b = iArr;
            try {
                iArr[DiracMode.LIVE_HIFI.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1331b[DiracMode.LIVE_STAGE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1331b[DiracMode.PERFORMANCE_DYNAMIC.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1331b[DiracMode.PERFORMANCE_NICAM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1331b[DiracMode.SIMPLE_OPEN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1331b[DiracMode.LIVE_CLOSE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1331b[DiracMode.PERFORMANCE_CLOSE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1331b[DiracMode.SIMPLE_CLOSE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            int[] iArr2 = new int[DiracLevel.values().length];
            f1330a = iArr2;
            try {
                iArr2[DiracLevel.DIRAC_SIMPLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f1330a[DiracLevel.DIRAC_PERFORMANCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f1330a[DiracLevel.DIRAC_LIVE.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    /* JADX INFO: compiled from: SoundBoxManager.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f1332a = new c(null);

        public static c a() {
            return f1332a;
        }
    }

    /* JADX INFO: compiled from: SoundBoxManager.java */
    public static class e extends AbsBYDAutoAudioListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final WeakReference<c> f1333b;

        public e(c cVar) {
            this.f1333b = new WeakReference<>(cVar);
        }

        public final boolean a() {
            WeakReference<c> weakReference = this.f1333b;
            return weakReference == null || weakReference.get() == null || this.f1333b.get().l == null || this.f1333b.get().l.isEmpty();
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onAudioFocusChanged(int i, List<AudioFocusInfo> list) {
            b.b.m.c.a.a("SoundBoxManager", "--onAudioFocusChanged--zoneId：" + i + ", focusInfoList: " + list);
            if (a()) {
                return;
            }
            Iterator it = this.f1333b.get().l.values().iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoAudioListener) it.next()).onAudioFocusChanged(i, list);
            }
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onEepromAmpTypeChanged(int i) {
            b.b.m.c.a.a("SoundBoxManager", "onEepromAmpTypeChanged", "value：" + i);
            b.b.g.o.d.i(i);
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onEqualizerChanged(int i, int i2) {
            if (a()) {
                return;
            }
            c cVar = this.f1333b.get();
            b.b.m.c.a.a("SoundBoxManager", "--onEqualizerChanged-1-freq： " + b.b.m.g.f.h(i) + ", value: " + i2);
            Iterator it = cVar.l.values().iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoAudioListener) it.next()).onEqualizerChanged(i, i2);
            }
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onGearSpeedChanged(int i, int i2) {
            b.b.m.c.a.a("SoundBoxManager", "--onGearSpeedChanged--manufacturer：" + i + ", gear: " + i2);
            if (a()) {
                return;
            }
            Iterator it = this.f1333b.get().l.values().iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoAudioListener) it.next()).onGearSpeedChanged(i, i2);
            }
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onGeneralEqualizerChanged(int i, int i2, int i3) {
            b.b.m.c.a.a("SoundBoxManager", "onGeneralEqualizerChanged", "manufactor：" + i, "frequencyNum：" + i2, "value：" + i3);
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onGeneralSoundFieldChanged(int i, int i2, int i3) {
            b.b.m.c.a.a("SoundBoxManager", "onGeneralSoundFieldChanged", "manufactor：" + i);
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onMuteStateChanged(int i) {
            if (a()) {
                return;
            }
            b.b.m.c.a.a("SoundBoxManager", "--onMuteStateChanged--state：" + i);
            Iterator it = this.f1333b.get().l.values().iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoAudioListener) it.next()).onMuteStateChanged(i);
            }
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onNaviMuteStateChanged(int i) {
            if (a()) {
                return;
            }
            b.b.m.c.a.a("SoundBoxManager", "--onNaviMuteStateChanged--state：" + i);
            Iterator it = this.f1333b.get().l.values().iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoAudioListener) it.next()).onNaviMuteStateChanged(i);
            }
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onNaviVolumeChanged(int i) {
            if (a()) {
                return;
            }
            b.b.m.c.a.a("SoundBoxManager", "--onNaviVolumeChanged--value：" + i);
            Iterator it = this.f1333b.get().l.values().iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoAudioListener) it.next()).onNaviVolumeChanged(i);
            }
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onNavigationMediaStateChanged(int i) {
            b.b.m.c.a.a("SoundBoxManager", "--onNavigationMediaStateChanged--value：" + i);
            if (a()) {
                return;
            }
            Iterator it = this.f1333b.get().l.values().iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoAudioListener) it.next()).onNavigationMediaStateChanged(i);
            }
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onPhoneVolume(int i) {
            b.b.m.c.a.a("SoundBoxManager", "--onPhoneVolume--value：" + i);
            if (a()) {
                return;
            }
            Iterator it = this.f1333b.get().l.values().iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoAudioListener) it.next()).onPhoneVolume(i);
            }
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onSoundEffectChanged(int i) {
            if (a()) {
                return;
            }
            b.b.m.c.a.a("SoundBoxManager", "--onSoundEffectChanged--effect：" + i);
            Iterator it = this.f1333b.get().l.values().iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoAudioListener) it.next()).onSoundEffectChanged(i);
            }
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onSoundFieldChanged(int i, int i2) {
            if (a()) {
                return;
            }
            b.b.m.c.a.a("SoundBoxManager", "--onSoundFieldChanged--x：" + i + ", y: " + i2);
            Iterator it = this.f1333b.get().l.values().iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoAudioListener) it.next()).onSoundFieldChanged(i, i2);
            }
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onSoundFieldPositionChanged(int i) {
            b.b.m.c.a.a("SoundBoxManager", "--onSoundFieldPositionChanged--type：" + i);
            if (a()) {
                return;
            }
            Iterator it = this.f1333b.get().l.values().iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoAudioListener) it.next()).onSoundFieldPositionChanged(i);
            }
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onVoiceAssistVolume(int i) {
            b.b.m.c.a.a("SoundBoxManager", "--onVoiceAssistVolume--value：" + i);
            if (a()) {
                return;
            }
            Iterator it = this.f1333b.get().l.values().iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoAudioListener) it.next()).onVoiceAssistVolume(i);
            }
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onVolumeChanged(int i, int i2) {
            if (a()) {
                return;
            }
            b.b.m.c.a.a("SoundBoxManager", "--onVolumeChanged--value：" + i + ", flag: " + i2);
            Iterator it = this.f1333b.get().l.values().iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoAudioListener) it.next()).onVolumeChanged(i, i2);
            }
        }
    }

    /* JADX INFO: compiled from: SoundBoxManager.java */
    public interface f {
        void a();
    }

    public /* synthetic */ c(a aVar) {
        this();
    }

    public int A() {
        b.b.m.c.a.a("SoundBoxManager", "--resetDiracSoundEffect--");
        return this.k.reset(0, 1);
    }

    public Map<Integer, Integer> B() {
        this.k.reset(0, 1);
        return k();
    }

    public int C() {
        return this.k.reset(2, 1);
    }

    public void D() {
        b.b.m.c.a.a("SoundBoxManager", "---resetEqualizer---");
        this.k.reset(0, 1);
    }

    public Map<Integer, Integer> E() {
        this.k.reset(0, 1);
        return p();
    }

    public int F() {
        return this.k.reset(2, 1);
    }

    public void G() {
        b.b.m.c.a.a("SoundBoxManager", "---resetSoundEffect---");
        this.k.setSoundEffect(19);
    }

    public void H() {
        b.b.m.c.a.a("SoundBoxManager", "---resetSoundFieldPosition---");
        this.k.setSoundFieldPosition(1);
    }

    public void I(boolean z) {
        this.k.setAllMute(z ? 1 : 0);
    }

    public int J(int i, int i2) {
        b.b.m.c.a.a("SoundBoxManager", "--setDiracEqualizer--freq: " + b.b.m.g.f.h(i) + ", value: " + i2);
        if (i < 1 || i > 5) {
            return -2147482645;
        }
        if (i2 > 10) {
            i2 = 10;
        }
        if (i2 < -10) {
            i2 = -10;
        }
        b.b.m.c.a.a("SoundBoxManager", "--setDiracEqualizer--value: " + i2);
        return this.k.setEqualizer(i, i2);
    }

    public int K(DiracMode diracMode) {
        int diracLiveMode;
        switch (C0061c.f1331b[diracMode.ordinal()]) {
            case 1:
                diracLiveMode = this.k.setDiracLiveMode(1);
                break;
            case 2:
                diracLiveMode = this.k.setDiracLiveMode(2);
                break;
            case 3:
                diracLiveMode = this.k.setDiracPerformanceMode(1);
                break;
            case 4:
                diracLiveMode = this.k.setDiracPerformanceMode(2);
                break;
            case 5:
                diracLiveMode = this.k.setDiracLiveState(1);
                break;
            case 6:
            case 7:
            case 8:
                diracLiveMode = this.k.setDiracLiveState(0);
                break;
            default:
                diracLiveMode = -1;
                break;
        }
        b.b.m.c.a.a("SoundBoxManager", "--setDiracMode--result = " + diracLiveMode);
        return diracLiveMode;
    }

    public int L(int i, int i2) {
        b.b.m.c.a.a("SoundBoxManager", "--setDiracPosition-0-x = " + i + ", y = " + i2);
        if (Math.abs(i) > 10 || Math.abs(i2) > 10) {
            return -2147482645;
        }
        b.b.m.c.a.a("SoundBoxManager", "--setDiracPosition-1-x = " + i + ", y = " + i2);
        return this.k.setSoundField(i, i2);
    }

    public int M(int i, int i2) {
        b.b.m.c.a.a("SoundBoxManager", "--setDynaCustomSoundField-0-x: " + i + ", y: " + i2);
        if (Math.abs(i) > 9) {
            i = 9;
        }
        if (Math.abs(i2) > 9) {
            i2 = 9;
        }
        b.b.m.c.a.a("SoundBoxManager", "--setDynaCustomSoundField-1-x: " + i + ", y: " + i2);
        return this.k.setSoundField(i, i2);
    }

    public int N(int i) {
        b.b.m.c.a.a("SoundBoxManager", "--setDynaEffect--effect = " + i);
        return this.k.setSoundEffect(i);
    }

    public int O(int i, int i2) {
        b.b.m.c.a.a("SoundBoxManager", "--setDynaEqualizer--freq: " + b.b.m.g.f.h(i) + ", value: " + i2);
        if (i < 17) {
            return -2147482645;
        }
        if (b.b.g.o.h.a.c(this.f1325e) && i > 20) {
            return -2147482645;
        }
        if (b.b.g.o.h.a.b(this.f1325e) && i > 21) {
            return -2147482645;
        }
        if (i2 > 9) {
            i2 = 9;
        }
        if (i2 < -9) {
            i2 = -9;
        }
        b.b.m.c.a.a("SoundBoxManager", "--setDynaEqualizer--value: " + i2);
        return this.k.setEqualizer(i, i2);
    }

    public int P(int i) {
        b.b.m.c.a.a("SoundBoxManager", "--setDynaSoundFieldPosition--type = " + i);
        if (i < 1 || i > 6) {
            i = 1;
        }
        return this.k.setSoundFieldPosition(i);
    }

    public int Q(int i) {
        Manufactor manufactor = this.f1325e.f1342a;
        if (manufactor == Manufactor.DYNAUDIO && (i < 0 || i > 7)) {
            return -2147482645;
        }
        if ((manufactor == Manufactor.HARMAN || manufactor == Manufactor.BS) && (i < 0 || i > 3)) {
            return -2147482645;
        }
        b.b.m.c.a.a("SoundBoxManager", "--setGearBySpeed--gear: " + i);
        return this.k.setGearBySpeed(i);
    }

    public int R(int i, int i2, int i3) {
        b.b.m.c.a.a("SoundBoxManager", "setGeneralEqualizer", "manufactor: " + i, "frequencyNum: " + i2 + ", value: " + i3);
        return this.k.setGeneralEqualizer(i, i2, i3);
    }

    public int S(int i, int i2, int i3) {
        b.b.m.c.a.a("SoundBoxManager", "setGeneralSoundField", "manufactor: " + i, "x: " + i2 + ", y: " + i3);
        return this.k.setGeneralSoundField(i, i2, i3);
    }

    public int T(int i, int i2) {
        b.b.m.c.a.a("SoundBoxManager", "--setHarmanEqualizer--freq: " + b.b.m.g.f.h(i) + ", value: " + i2);
        if (i < 33 || i > 35) {
            return -2147482645;
        }
        if (i2 > 10) {
            i2 = 10;
        }
        if (i2 < -10) {
            i2 = -10;
        }
        b.b.m.c.a.a("SoundBoxManager", "--setHarmanEqualizer--value: " + i2);
        return this.k.setEqualizer(i, i2);
    }

    public int U(int i) {
        this.i = i;
        b.b.m.c.a.a("SoundBoxManager", "--setHarmanFocusingIndex--type: 18");
        return this.k.setSoundFieldPosition(18);
    }

    public int V(int i, int i2) {
        b.b.m.c.a.a("SoundBoxManager", "--setHarmanPosition-0-x = " + i + ", y = " + i2);
        if (Math.abs(i) > 10 || Math.abs(i2) > 10) {
            return -2147482645;
        }
        b.b.m.c.a.a("SoundBoxManager", "--setHarmanPosition-1-x = " + i + ", y = " + i2);
        return this.k.setBalanceFadePosition(i, i2);
    }

    public int W(boolean z) {
        b.b.m.c.a.a("SoundBoxManager", "--setLoudnessEnable--enable = " + z);
        return this.k.setLoudnessState(z ? 1 : 0);
    }

    public void X(f fVar) {
        this.q = fVar;
    }

    public void Y() {
        BYDAutoAudioDevice bYDAutoAudioDevice = this.k;
        if (bYDAutoAudioDevice != null) {
            bYDAutoAudioDevice.unregisterListener((AbsBYDAutoAudioListener) this.n);
        }
        BYDCarServiceBase bYDCarServiceBase = this.m;
        if (bYDCarServiceBase != null) {
            bYDCarServiceBase.unregisterConnectionListener(this.p);
        }
    }

    public void Z(String str) {
        this.l.remove(str);
    }

    public void c(int i, int i2) {
        b.b.m.c.a.a("SoundBoxManager", "generalReset", "manufactor: " + i, "item: " + i2);
        this.k.generalReset(i, i2, 1);
    }

    public b.b.g.o.h.a d() {
        return this.f1325e;
    }

    public Map<Integer, Integer> e() {
        HashMap map = new HashMap();
        for (int i : f1321a) {
            map.put(Integer.valueOf(i), Integer.valueOf(this.k.getEqualizer(i)));
        }
        b.b.m.c.a.a("SoundBoxManager", "--getDiracFreqBands--map: " + map);
        return map;
    }

    public DiracMode f() {
        b.b.g.o.h.a aVar = this.f1325e;
        DiracMode diracMode = null;
        if (aVar != null && aVar.f1342a == Manufactor.DIRAC) {
            DiracLevel diracLevel = DiracLevel.getDiracLevel(aVar.f1343b);
            b.b.m.c.a.a("SoundBoxManager", "--getDiracMode--level: " + diracLevel);
            if (diracLevel == null) {
                return null;
            }
            int i = C0061c.f1330a[diracLevel.ordinal()];
            if (i == 1) {
                int diracLiveState = this.k.getDiracLiveState();
                b.b.m.c.a.a("SoundBoxManager", "--getDiracMode-DIRAC_SIMPLE-value = " + diracLiveState);
                diracMode = diracLiveState == 1 ? DiracMode.SIMPLE_OPEN : DiracMode.SIMPLE_CLOSE;
            } else if (i == 2) {
                int diracPerformanceMode = this.k.getDiracPerformanceMode();
                b.b.m.c.a.a("SoundBoxManager", "--getDiracMode-PERFORMANCE-value = " + diracPerformanceMode);
                diracMode = diracPerformanceMode == 1 ? DiracMode.PERFORMANCE_DYNAMIC : diracPerformanceMode == 2 ? DiracMode.PERFORMANCE_NICAM : DiracMode.PERFORMANCE_CLOSE;
            } else if (i == 3) {
                int diracLiveMode = this.k.getDiracLiveMode();
                b.b.m.c.a.a("SoundBoxManager", "--getDiracMode-DIRAC_LIVE-value = " + diracLiveMode);
                diracMode = diracLiveMode == 1 ? DiracMode.LIVE_HIFI : diracLiveMode == 2 ? DiracMode.LIVE_STAGE : DiracMode.LIVE_CLOSE;
            }
            b.b.m.c.a.a("SoundBoxManager", "--getDiracMode--mode = " + diracMode);
        }
        return diracMode;
    }

    public int[] g() {
        int[] soundField = this.k.getSoundField();
        if (soundField == null || soundField.length <= 0) {
            soundField = new int[]{0, 0};
        }
        b.b.m.c.a.a("SoundBoxManager", "--getDiracPosition--position: " + Arrays.toString(soundField));
        soundField[0] = soundField[0] + (-10);
        soundField[1] = soundField[1] + (-10);
        return soundField;
    }

    public int h() {
        return this.k.getSoundEffect();
    }

    public int[] i() {
        int[] soundField = this.k.getSoundField();
        b.b.m.c.a.a("SoundBoxManager", "--getDynaCustomSoundField-0-position: " + Arrays.toString(soundField));
        if (soundField == null || soundField.length <= 0) {
            soundField = new int[]{0, 0};
        }
        b.b.m.c.a.a("SoundBoxManager", "--getDynaCustomSoundField-1-position: " + Arrays.toString(soundField));
        return soundField;
    }

    public int j() {
        int soundEffect = this.k.getSoundEffect();
        b.b.m.c.a.a("SoundBoxManager", "getDynaEffect:" + soundEffect);
        return soundEffect;
    }

    public Map<Integer, Integer> k() {
        HashMap map = new HashMap();
        for (int i : b.b.g.o.h.a.c(this.f1325e) ? f1322b : f1323c) {
            Integer numValueOf = Integer.valueOf(i);
            int equalizer = this.k.getEqualizer(i);
            map.put(numValueOf, Integer.valueOf(equalizer));
            b.b.m.c.a.a("SoundBoxManager", "getDynaFreqBands:" + i + ",value:" + equalizer);
        }
        return map;
    }

    public int l() {
        try {
            int soundFieldPositionType = this.k.getSoundFieldPositionType();
            b.b.m.c.a.a("SoundBoxManager", "getDynaSoundFieldPositionType:" + soundFieldPositionType);
            return soundFieldPositionType;
        } catch (RuntimeException unused) {
            return 0;
        }
    }

    public int m() {
        int gearBySpeed = this.k.getGearBySpeed();
        b.b.m.c.a.a("SoundBoxManager", "--getGearBySpeed--gear: " + gearBySpeed);
        return gearBySpeed;
    }

    public int[] n(int i) {
        b.b.m.c.a.a("SoundBoxManager", "getGeneralSoundField", "manufactor: " + i);
        return this.k.getGeneralSoundField(i);
    }

    public int o() {
        return this.k.getSoundFieldPositionType();
    }

    public Map<Integer, Integer> p() {
        HashMap map = new HashMap();
        for (int i : f1324d) {
            map.put(Integer.valueOf(i), Integer.valueOf(this.k.getEqualizer(i)));
        }
        return map;
    }

    public int[] q() {
        int[] balanceFadePosition = this.k.getBalanceFadePosition();
        if (balanceFadePosition == null || balanceFadePosition.length <= 0) {
            balanceFadePosition = new int[]{0, 0};
        }
        b.b.m.c.a.a("SoundBoxManager", "--getHarmanPosition--position: " + Arrays.toString(balanceFadePosition));
        balanceFadePosition[0] = balanceFadePosition[0] + (-10);
        balanceFadePosition[1] = balanceFadePosition[1] + (-10);
        return balanceFadePosition;
    }

    public void r(Context context) {
        b.b.m.c.a.d("SoundBoxManager", "init", "---init---isInited: " + this.o);
        if (context == null || this.o) {
            return;
        }
        Context applicationContext = context.getApplicationContext();
        this.o = true;
        BYDAutoStudy bYDAutoStudy = BYDAutoStudy.getInstance(applicationContext);
        if (this.m == null) {
            this.m = new b(applicationContext);
        }
        if (this.k == null) {
            this.k = BYDAutoAudioDevice.getInstance(applicationContext);
        }
        if (this.g == -1) {
            this.g = 7;
        }
        if (this.h == -1) {
            this.h = 0;
        }
        if (this.i == -1) {
            this.i = 0;
        }
        if (this.j == -1) {
            this.j = 1;
        }
        a.b.a().A(applicationContext);
        this.f1326f = bYDAutoStudy.getAmplifierConfiguration();
        int i = b.b.a.i.a.f532b;
        b.b.m.c.a.d("SoundBoxManager", "init", ", init, modelCode: " + i + ", ampConfigValue: " + this.f1326f);
        this.f1325e = b.b.g.o.d.b(i, this.f1326f);
        StringBuilder sb = new StringBuilder();
        sb.append(", init, mConfig: ");
        sb.append(this.f1325e);
        b.b.m.c.a.d("SoundBoxManager", "init", sb.toString());
    }

    public boolean s() {
        b.b.m.c.a.a("SoundBoxManager", "--isAllMute--mAudio.getAllMute = " + this.k.getAllMute());
        return this.k.getAllMute() == 1;
    }

    public boolean t() {
        return 1 == this.k.getLoudnessState();
    }

    public void u(String str, AbsBYDAutoAudioListener absBYDAutoAudioListener) {
        this.l.put(str, absBYDAutoAudioListener);
    }

    public void v() {
        BYDAutoAudioDevice bYDAutoAudioDevice = this.k;
        if (bYDAutoAudioDevice != null) {
            e eVar = new e(this);
            this.n = eVar;
            bYDAutoAudioDevice.registerListener((AbsBYDAutoAudioListener) eVar);
        }
        BYDCarServiceBase bYDCarServiceBase = this.m;
        if (bYDCarServiceBase != null) {
            bYDCarServiceBase.registerConnectionListener(this.p);
        }
        if (this.f1326f == 0) {
            this.k.requestEepromAmpType();
        }
    }

    public void w() {
        b.b.m.c.a.a("SoundBoxManager", "---resetBalance---");
        this.k.reset(1, 1);
    }

    public void x() {
        b.b.m.c.a.a("SoundBoxManager", "---resetBalanceFade---");
        this.k.reset(2, 1);
    }

    public Map<Integer, Integer> y() {
        this.k.reset(0, 1);
        return e();
    }

    public int z() {
        return this.k.reset(2, 1);
    }

    public c() {
        this.f1326f = -1;
        this.g = -1;
        this.h = -1;
        this.i = -1;
        this.j = -1;
        this.o = false;
        this.p = new a();
        this.l = new ConcurrentHashMap<>();
    }
}
