package q3;

import android.content.Context;
import android.hardware.bydauto.ac.BYDAutoAcDevice;
import android.hardware.bydauto.audio.AbsBYDAutoAudioListener;
import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.charging.BYDAutoChargingDevice;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.setting.AbsBYDAutoSettingListener;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.hardware.bydauto.showcarmode.BYDAutoShowCarDevice;
import android.hardware.bydauto.showcarmode.IShowCarModelChangeListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemProperties;
import android.util.Log;
import com.byd.displaymode.bean.DisplaySwitchBean;
import com.byd.displaymode.bean.SeatState;
import com.byd.displaymode.bean.SoundEffectBean;
import com.byd.displaymode.present.IStateChangeCallback;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.util.ArrayUtils;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;
import p3.f;

/* JADX INFO: compiled from: DisplayPresenter.java */
/* JADX INFO: loaded from: classes.dex */
public class k implements IShowCarModelChangeListener, IStateChangeCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte f7399a = (byte) SystemProperties.getInt("persist.vendor.byd.vehicle.type", 0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile int f7400b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f7401c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public s3.b f7402d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Handler f7403e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public BYDAutoShowCarDevice f7404f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final DisplaySwitchBean f7405g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ReentrantReadWriteLock f7406h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ReentrantReadWriteLock.ReadLock f7407i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final ReentrantReadWriteLock.WriteLock f7408j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public volatile boolean f7409k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public r3.b f7410l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public r3.c f7411m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public r3.d f7412n;

    public k(Context context, DisplaySwitchBean displaySwitchBean, s3.b bVar) {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.f7406h = reentrantReadWriteLock;
        this.f7407i = reentrantReadWriteLock.readLock();
        this.f7408j = reentrantReadWriteLock.writeLock();
        this.f7409k = false;
        this.f7401c = context;
        this.f7405g = displaySwitchBean;
        this.f7402d = bVar;
        this.f7404f = BYDAutoShowCarDevice.getInstance(context);
        BYDAutoAudioDevice.getInstance(context);
        BYDAutoSettingDevice.getInstance(context);
        BYDAutoAcDevice.getInstance(context);
        BYDAutoGearboxDevice.getInstance(context);
        BYDAutoBodyworkDevice.getInstance(context);
        this.f7400b = this.f7404f.getShowCarModelType();
        Log.i("DisplayPresenter", "DisplayPresenter init displayModel: " + this.f7400b);
        this.f7404f.registerListener(this);
        HandlerThread handlerThread = new HandlerThread("delay_set_display");
        handlerThread.start();
        this.f7403e = new Handler(handlerThread.getLooper(), new Handler.Callback() { // from class: q3.b
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return this.f7377d.h(message);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void m(CountDownLatch countDownLatch) {
        d(this.f7400b);
        countDownLatch.countDown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void o(boolean z7, CountDownLatch countDownLatch) {
        E(z7);
        countDownLatch.countDown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void q(boolean z7, CountDownLatch countDownLatch) {
        L(this.f7401c, z7, false);
        countDownLatch.countDown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: r, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void s(boolean z7, CountDownLatch countDownLatch) {
        O(z7, false);
        countDownLatch.countDown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: t, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void u() {
        L(this.f7401c, false, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: v, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void w() {
        O(false, true);
    }

    public static /* synthetic */ void x(boolean z7, BYDAutoSettingDevice bYDAutoSettingDevice, SeatState seatState) {
        if (!z7 && seatState.getHeatLevel() != 1 && seatState.getHeatLevel() != 0) {
            bYDAutoSettingDevice.setSeatHeatingState(seatState.getSeatId(), 1);
        } else {
            if (z7 || seatState.getVentilation() == 1 || seatState.getVentilation() == 0) {
                return;
            }
            bYDAutoSettingDevice.setSeatVentilatingState(seatState.getSeatId(), 1);
        }
    }

    public static /* synthetic */ void y(boolean z7, BYDAutoSettingDevice bYDAutoSettingDevice, boolean z8, int i8, SeatState seatState) {
        BydLogHelper.logI("DisplayPresenter", "setSeatForDisplay", " heatLevel =" + seatState.getHeatLevel() + " ventilate=" + seatState.getVentilation());
        if (z7 && !seatState.isInvalid()) {
            if (seatState.getHeatLevel() > 0) {
                bYDAutoSettingDevice.setSeatHeatingState(seatState.getSeatId(), seatState.getHeatLevel());
            }
            if (seatState.getVentilation() > 0) {
                bYDAutoSettingDevice.setSeatVentilatingState(seatState.getSeatId(), seatState.getVentilation());
                return;
            }
            return;
        }
        if (!z7 && z8 && seatState.getHeatLevel() != 1 && seatState.getHeatLevel() != 0) {
            bYDAutoSettingDevice.setSeatHeatingState(i8, 1);
        } else {
            if (z7 || z8 || seatState.getVentilation() == 1 || seatState.getVentilation() == 0) {
                return;
            }
            bYDAutoSettingDevice.setSeatVentilatingState(i8, 1);
        }
    }

    public void B(Context context) {
        if (this.f7410l == null) {
            this.f7410l = new r3.b(this, this.f7405g.getSoundEffectBean());
        }
        BYDAutoAudioDevice.getInstance(context).registerListener((AbsBYDAutoAudioListener) this.f7410l);
        if (this.f7411m == null) {
            this.f7411m = new r3.c(this);
        }
        BYDAutoSettingDevice.getInstance(context).registerListener((AbsBYDAutoSettingListener) this.f7411m);
        if (this.f7412n == null) {
            this.f7412n = new r3.d(this.f7402d);
        }
        BYDAutoGearboxDevice.getInstance(context).registerListener((AbsBYDAutoGearboxListener) this.f7412n);
        this.f7409k = true;
        BydLogHelper.logI("DisplayPresenter", "registerListener", " is execute end");
    }

    public final void C(BYDAutoAcDevice bYDAutoAcDevice, int i8, int i9, int i10) {
        if (N(i8)) {
            bYDAutoAcDevice.setAcMaxCoolingState(i8);
        }
        if (N(i9)) {
            bYDAutoAcDevice.setAcCompressorMode(0, i9);
        }
        if (N(i10)) {
            bYDAutoAcDevice.setAcControlMode(0, i10);
        }
        try {
            this.f7408j.lock();
            this.f7405g.resetAcAssociate();
        } finally {
            this.f7408j.unlock();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0049, code lost:
    
        if (r17 == false) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x004c, code lost:
    
        r10 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004d, code lost:
    
        r7.setLoudnessState(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0098, code lost:
    
        if (r17 == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x009b, code lost:
    
        r8 = 19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x009c, code lost:
    
        r7.setSoundEffect(r8);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void D(boolean r17, int r18, int r19, int r20, int r21, int r22, int r23) {
        /*
            Method dump skipped, instruction units count: 216
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: q3.k.D(boolean, int, int, int, int, int, int):void");
    }

    public final void E(boolean z7) {
        try {
            this.f7407i.lock();
            SeatState[] seatArray = this.f7405g.getSeatArray();
            this.f7407i.unlock();
            if (z7 && (seatArray == null || seatArray[0].isInvalid())) {
                return;
            }
            if (seatArray == null) {
                seatArray = new SeatState[4];
                try {
                    this.f7408j.lock();
                    this.f7405g.setSeatArray(seatArray);
                } finally {
                    this.f7408j.unlock();
                }
            }
            if (z7) {
                H(seatArray, true);
                return;
            }
            BYDAutoSettingDevice bYDAutoSettingDevice = BYDAutoSettingDevice.getInstance(this.f7401c);
            int i8 = 0;
            while (i8 < seatArray.length) {
                int i9 = i8 + 1;
                int seatHeatingState = bYDAutoSettingDevice.getSeatHeatingState(i9);
                int seatVentilatingState = seatHeatingState <= 1 ? bYDAutoSettingDevice.getSeatVentilatingState(i9) : 1;
                if (i8 == 1) {
                    seatHeatingState = bYDAutoSettingDevice.getSeatHeatingNotL1PState();
                    seatVentilatingState = seatHeatingState <= 1 ? bYDAutoSettingDevice.getSeatVentilatingNotL1PState() : 1;
                }
                if (seatArray[i8] == null) {
                    seatArray[i8] = new SeatState(i9, seatHeatingState, seatVentilatingState);
                } else {
                    seatArray[i8].setSeatProperty(seatHeatingState, seatVentilatingState);
                }
                i8 = i9;
            }
            H(seatArray, false);
        } catch (Throwable th) {
            this.f7407i.unlock();
            throw th;
        }
    }

    public final void F(SeatState[] seatStateArr, final boolean z7, boolean z8, int i8) {
        final BYDAutoSettingDevice bYDAutoSettingDevice = BYDAutoSettingDevice.getInstance(this.f7401c);
        Arrays.stream(seatStateArr).forEach(new Consumer() { // from class: q3.e
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                k.x(z7, bYDAutoSettingDevice, (SeatState) obj);
            }
        });
    }

    public final void G(SeatState[] seatStateArr, final boolean z7, final boolean z8, final int i8) {
        final BYDAutoSettingDevice bYDAutoSettingDevice = BYDAutoSettingDevice.getInstance(this.f7401c);
        Arrays.stream(seatStateArr).forEach(new Consumer() { // from class: q3.j
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                k.y(z7, bYDAutoSettingDevice, z8, i8, (SeatState) obj);
            }
        });
    }

    public final void H(SeatState[] seatStateArr, boolean z7) {
        if (ArrayUtils.isEmpty(seatStateArr)) {
            return;
        }
        G(seatStateArr, z7, true, 1);
        if (z7) {
            return;
        }
        Message messageObtainMessage = this.f7403e.obtainMessage(16);
        messageObtainMessage.obj = seatStateArr;
        this.f7403e.sendMessageDelayed(messageObtainMessage, 1200000L);
    }

    public final void I(boolean z7, boolean z8, int i8, int i9, int i10, int i11, int i12, int i13, int i14) {
        BYDAutoSettingDevice bYDAutoSettingDevice = BYDAutoSettingDevice.getInstance(this.f7401c);
        if (!z7 ? i(i8) : N(i8)) {
            if (!z7) {
                i8 = 1;
            }
            bYDAutoSettingDevice.setDriverSeatAutoReturn(i8);
        }
        if (!z7 ? i(i9) : N(i9)) {
            if (!z7) {
                i9 = 1;
            }
            bYDAutoSettingDevice.setSteerPositionAutoReturn(i9);
        }
        if (!z7 ? i(i10) : N(i10)) {
            if (!z7) {
                i10 = 1;
            }
            bYDAutoSettingDevice.setSmartWelcomeLightState(i10);
        }
        if (!z7 ? i(i11) : N(i11)) {
            if (!z7) {
                i11 = 1;
            }
            bYDAutoSettingDevice.setRearViewMirrorFlip(i11);
        }
        if (!z7 ? i(i12) : N(i12)) {
            if (!z7) {
                i12 = 1;
            }
            bYDAutoSettingDevice.setAutoExternalRearMirrorFollowUpSwitch(i12);
        }
        if (!z8 && (!z7 ? i(i13) : N(i13))) {
            BYDAutoChargingDevice.getInstance(this.f7401c).setWirelessChargingSwitchState(1);
        }
        if (!z8 && (!z7 ? 2 == i14 : N(i14))) {
            bYDAutoSettingDevice.setPadRotation(1);
        }
        if (z7) {
            try {
                this.f7408j.lock();
                this.f7405g.resetVehicle();
            } finally {
                this.f7408j.unlock();
            }
        }
    }

    public final void J(int i8, boolean z7) {
        BydLogHelper.logI("DisplayPresenter", "eventType" + i8 + "displayModel =" + this.f7400b + ", is4ED : " + z7);
        if (this.f7400b != 1) {
            return;
        }
        if (1 != i8 && 2 != i8 && 16 != i8) {
            if (!(z7 && i8 == 10)) {
                return;
            }
        }
        this.f7402d.a();
    }

    /* JADX INFO: renamed from: K, reason: merged with bridge method [inline-methods] */
    public final void A(int i8, int i9) {
        boolean z7 = true;
        if (i8 != 2 && i8 != 1) {
            z7 = false;
        }
        if (z7) {
            this.f7402d.f(i8);
        } else if (i8 == 3) {
            this.f7402d.g(i9);
        }
    }

    public final void L(Context context, boolean z7, boolean z8) {
        int i8;
        int i9;
        int loudnessState;
        int soundFieldPositionType;
        int soundEffect;
        BYDAutoAudioDevice bYDAutoAudioDevice = BYDAutoAudioDevice.getInstance(context);
        SoundEffectBean soundEffectBean = this.f7405g.getSoundEffectBean();
        int factoryType = soundEffectBean.getFactoryType();
        int configLevel = factoryType == 1 ? soundEffectBean.getConfigLevel() : 0;
        if (!N(factoryType)) {
            factoryType = bYDAutoAudioDevice.getAudioDeviceManufacturer();
        }
        int i10 = factoryType;
        if (i10 == 1) {
            if (!N(configLevel)) {
                configLevel = bYDAutoAudioDevice.getAudioDeviceConfig();
            }
            int diracLiveState = configLevel == 1 ? bYDAutoAudioDevice.getDiracLiveState() : configLevel == 2 ? bYDAutoAudioDevice.getDiracLiveMode() : configLevel == 3 ? bYDAutoAudioDevice.getDiracPerformanceMode() : 0;
            loudnessState = bYDAutoAudioDevice.getLoudnessState();
            i9 = diracLiveState;
            soundFieldPositionType = 0;
            soundEffect = 0;
            i8 = configLevel;
        } else if (i10 == 2) {
            int soundFieldPositionType2 = bYDAutoAudioDevice.getSoundFieldPositionType();
            soundEffect = bYDAutoAudioDevice.getSoundEffect();
            soundFieldPositionType = soundFieldPositionType2;
            i8 = 0;
            i9 = 0;
            loudnessState = 0;
        } else if (i10 == 3) {
            soundFieldPositionType = bYDAutoAudioDevice.getSoundFieldPositionType();
            i8 = 0;
            i9 = 0;
            loudnessState = 0;
            soundEffect = 0;
        } else {
            i8 = 0;
            i9 = 0;
            loudnessState = 0;
            soundFieldPositionType = 0;
            soundEffect = 0;
        }
        if (!z7 && !z8) {
            try {
                this.f7408j.lock();
                soundEffectBean.setData(i10, i8, i9, loudnessState, soundFieldPositionType, soundEffect);
            } finally {
                this.f7408j.unlock();
            }
        }
        D(z7, i10, i8, i9, loudnessState, soundFieldPositionType, soundEffect);
    }

    public final void M() {
        if (this.f7409k) {
            if (this.f7410l != null) {
                BYDAutoAudioDevice.getInstance(this.f7401c).unregisterListener((AbsBYDAutoAudioListener) this.f7410l);
            }
            if (this.f7411m != null) {
                BYDAutoSettingDevice.getInstance(this.f7401c).unregisterListener((AbsBYDAutoSettingListener) this.f7411m);
            }
            if (this.f7412n != null) {
                BYDAutoGearboxDevice.getInstance(this.f7401c).unregisterListener((AbsBYDAutoGearboxListener) this.f7412n);
            }
            this.f7409k = false;
            BydLogHelper.logI("DisplayPresenter", "unRegisterListener", " is execute end");
        }
    }

    public final boolean N(int i8) {
        return i8 > 0;
    }

    public final void O(boolean z7, boolean z8) {
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int screenAngle;
        BYDAutoSettingDevice bYDAutoSettingDevice = BYDAutoSettingDevice.getInstance(this.f7401c);
        if (z7) {
            try {
                this.f7407i.lock();
                int seatWelcome = this.f7405g.getSeatWelcome();
                int steerWelcome = this.f7405g.getSteerWelcome();
                int smartWelcomeLight = this.f7405g.getSmartWelcomeLight();
                int rearMirrorFlip = this.f7405g.getRearMirrorFlip();
                int rearMirrorFollowState = this.f7405g.getRearMirrorFollowState();
                int wirelessCharge = this.f7405g.getWirelessCharge();
                int smartRotate = this.f7405g.getSmartRotate();
                this.f7407i.unlock();
                i8 = rearMirrorFlip;
                i9 = rearMirrorFollowState;
                i10 = wirelessCharge;
                i11 = smartRotate;
                i12 = seatWelcome;
                i13 = steerWelcome;
                i14 = smartWelcomeLight;
            } catch (Throwable th) {
                this.f7407i.unlock();
                throw th;
            }
        } else {
            int wirelessChargingSwitchState = 0;
            boolean z9 = z8 && this.f7411m != null;
            int iB = z9 ? this.f7411m.b(IStateChangeCallback.Pos.SEAT_WELCOME) : bYDAutoSettingDevice.getDriverSeatAutoReturn();
            int iB2 = z9 ? this.f7411m.b(IStateChangeCallback.Pos.STEEL_WELCOME) : bYDAutoSettingDevice.getSteerPositionAutoReturn();
            int iB3 = z9 ? this.f7411m.b(IStateChangeCallback.Pos.SMART_LIGHT_WELCOME) : bYDAutoSettingDevice.getSmartWelcomeLightState();
            int iB4 = z9 ? this.f7411m.b(IStateChangeCallback.Pos.REAR_MIRROR_FLIP) : bYDAutoSettingDevice.getRearViewMirrorFlip();
            int iB5 = z9 ? this.f7411m.b(IStateChangeCallback.Pos.REAR_MIRROR_FOLLOW) : bYDAutoSettingDevice.getAutoExternalRearMirrorFollowUpSwitch();
            if (z8) {
                screenAngle = 0;
            } else {
                wirelessChargingSwitchState = BYDAutoChargingDevice.getInstance(this.f7401c).getWirelessChargingSwitchState();
                screenAngle = bYDAutoSettingDevice.getScreenAngle();
                try {
                    this.f7408j.lock();
                    this.f7405g.updateVehicle(iB, iB2, iB3, iB4, iB5, wirelessChargingSwitchState, screenAngle);
                } finally {
                    this.f7408j.unlock();
                }
            }
            i11 = screenAngle;
            i10 = wirelessChargingSwitchState;
            i9 = iB5;
            i8 = iB4;
            i14 = iB3;
            i13 = iB2;
            i12 = iB;
        }
        I(z7, z8, i12, i13, i14, i8, i9, i10, i11);
    }

    @Override // com.byd.displaymode.present.IStateChangeCallback
    public boolean a() {
        return this.f7400b == 1 || this.f7400b == 2;
    }

    @Override // com.byd.displaymode.present.IStateChangeCallback
    public void b(int i8) {
        if (this.f7403e.hasMessages(i8)) {
            this.f7403e.removeMessages(i8);
        }
    }

    @Override // com.byd.displaymode.present.IStateChangeCallback
    public void c(int i8) {
        try {
            this.f7407i.lock();
            SeatState[] seatArray = this.f7405g.getSeatArray();
            this.f7407i.unlock();
            BYDAutoSettingDevice bYDAutoSettingDevice = BYDAutoSettingDevice.getInstance(this.f7401c);
            int i9 = 0;
            while (i9 < seatArray.length) {
                int i10 = i9 + 1;
                int seatHeatingState = bYDAutoSettingDevice.getSeatHeatingState(i10);
                int seatVentilatingState = seatHeatingState <= 1 ? bYDAutoSettingDevice.getSeatVentilatingState(i10) : 1;
                if (i9 == 1) {
                    seatHeatingState = bYDAutoSettingDevice.getSeatHeatingNotL1PState();
                    seatVentilatingState = seatHeatingState <= 1 ? bYDAutoSettingDevice.getSeatVentilatingNotL1PState() : 1;
                }
                if (seatArray[i9] == null) {
                    seatArray[i9] = new SeatState(i10, seatHeatingState, seatVentilatingState);
                } else {
                    seatArray[i9].setSeatProperty(seatHeatingState, seatVentilatingState);
                }
                i9 = i10;
            }
            Message messageObtainMessage = this.f7403e.obtainMessage(i8);
            messageObtainMessage.obj = seatArray;
            this.f7403e.sendMessageDelayed(messageObtainMessage, 1200000L);
        } catch (Throwable th) {
            this.f7407i.unlock();
            throw th;
        }
    }

    public final void d(int i8) {
        BYDAutoAcDevice bYDAutoAcDevice = BYDAutoAcDevice.getInstance(this.f7401c);
        try {
            this.f7407i.lock();
            int acMaxState = this.f7405g.getAcMaxState();
            int acState = this.f7405g.getAcState();
            int acAutoMode = this.f7405g.getAcAutoMode();
            this.f7407i.unlock();
            boolean z7 = i8 == 2;
            BydLogHelper.logI("DisplayPresenter", "acTransaction", String.format("from temp acState= %1s, maxState= %2s, autoMode=%3s", Integer.valueOf(acState), Integer.valueOf(acMaxState), Integer.valueOf(acAutoMode)));
            if (!z7 && !N(acMaxState)) {
                acMaxState = bYDAutoAcDevice.getAcMaxCoolingState();
            }
            if (!z7 && !N(acState)) {
                acState = bYDAutoAcDevice.getAcStartState();
            }
            if (!z7 && !N(acAutoMode)) {
                acAutoMode = bYDAutoAcDevice.getAcControlMode();
            }
            if (i8 != 1) {
                if (z7 || i8 == 3) {
                    C(bYDAutoAcDevice, acMaxState, acState, acAutoMode);
                    if (i8 != 3 || j()) {
                        return;
                    }
                    BYDAutoSettingDevice.getInstance(this.f7401c).setExhibitionStartEnginePermit(1);
                    return;
                }
                return;
            }
            try {
                this.f7408j.lock();
                this.f7405g.setAcAssociateState(acState, acMaxState, acAutoMode);
                if (acAutoMode == 2) {
                    bYDAutoAcDevice.setAcControlMode(0, 1);
                }
                if (acMaxState == 1) {
                    bYDAutoAcDevice.setAcMaxCoolingState(2);
                }
                if (acState == 1) {
                    bYDAutoAcDevice.setAcCompressorMode(0, 2);
                }
                if (j()) {
                    BYDAutoSettingDevice.getInstance(this.f7401c).setExhibitionStartEnginePermit(2);
                }
            } finally {
                this.f7408j.unlock();
            }
        } catch (Throwable th) {
            this.f7407i.unlock();
            throw th;
        }
    }

    public void e(boolean z7) {
        final boolean z8 = this.f7400b == 3;
        final CountDownLatch countDownLatch = new CountDownLatch(z7 ? 1 : 4);
        p3.f fVar = p3.f.getInstance();
        fVar.a(2, "DisplayPresenter", "display_ac_associate", new f.a() { // from class: q3.d
            @Override // java.lang.Runnable
            public final void run() {
                this.f7381d.m(countDownLatch);
            }
        });
        if (!z7 || z8) {
            fVar.a(2, "DisplayPresenter", "display_seat_heat_associate", new f.a() { // from class: q3.g
                @Override // java.lang.Runnable
                public final void run() {
                    this.f7388d.o(z8, countDownLatch);
                }
            });
            fVar.a(2, "DisplayPresenter", "display_vehicle_sound_associate", new f.a() { // from class: q3.f
                @Override // java.lang.Runnable
                public final void run() {
                    this.f7385d.q(z8, countDownLatch);
                }
            });
            fVar.a(2, "DisplayPresenter", "display_vehicle_body_associate", new f.a() { // from class: q3.i
                @Override // java.lang.Runnable
                public final void run() {
                    this.f7392d.s(z8, countDownLatch);
                }
            });
        }
        try {
            BydLogHelper.logI("DisplayPresenter", "dealDisplayMode ", " await result =" + countDownLatch.await(3000L, TimeUnit.MILLISECONDS));
        } catch (InterruptedException e8) {
            e8.printStackTrace();
        }
        BydLogHelper.logI("DisplayPresenter", "dealDisplayMode ", " hasRegister =" + this.f7409k + ", isDisplayToggle = " + z7);
        if (!this.f7409k && this.f7400b < 3 && !z7) {
            B(this.f7401c);
        } else if (this.f7409k && this.f7400b == 3) {
            M();
        }
    }

    public int f() {
        return this.f7400b;
    }

    public final int g() {
        BYDAutoGearboxDevice bYDAutoGearboxDevice = BYDAutoGearboxDevice.getInstance(this.f7401c);
        if (bYDAutoGearboxDevice == null) {
            return 0;
        }
        return j() ? bYDAutoGearboxDevice.getGearboxAutoModeLevel() : bYDAutoGearboxDevice.getGear();
    }

    public final boolean h(Message message) {
        int i8 = message.what;
        if (i8 != 16) {
            if (i8 != 32) {
                if (i8 != 48) {
                    if (i8 != 64) {
                        if (i8 != 80) {
                            if (i8 != 96) {
                                if (i8 == 128 && a()) {
                                    p3.f.getInstance().a(2, "DisplayPresenter", "delay executeVehicle resetStatus", new f.a() { // from class: q3.a
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            this.f7376d.w();
                                        }
                                    });
                                }
                            } else if ((message.obj instanceof SeatState[]) && a()) {
                                G((SeatState[]) message.obj, false, false, 1);
                            }
                        } else if ((message.obj instanceof SeatState[]) && a()) {
                            G((SeatState[]) message.obj, false, false, 2);
                        }
                    } else if (a()) {
                        p3.f.getInstance().a(2, "DisplayPresenter", "delay executeSound resetStatus", new f.a() { // from class: q3.h
                            @Override // java.lang.Runnable
                            public final void run() {
                                this.f7391d.u();
                            }
                        });
                    }
                } else if ((message.obj instanceof SeatState[]) && a()) {
                    G((SeatState[]) message.obj, false, true, 2);
                }
            } else if ((message.obj instanceof SeatState[]) && a()) {
                G((SeatState[]) message.obj, false, true, 1);
            }
        } else if ((message.obj instanceof SeatState[]) && a()) {
            F((SeatState[]) message.obj, false, true, 1);
        }
        return true;
    }

    public final boolean i(int i8) {
        return i8 == 2;
    }

    @Override // android.hardware.bydauto.showcarmode.IShowCarModelChangeListener, b.d
    public void isConnected(boolean z7) {
        BydLogHelper.logI("DisplayPresenter", "isConnected", ((int) this.f7399a) + ", displayModel =" + this.f7400b);
    }

    public boolean j() {
        return this.f7399a == 2;
    }

    @Override // android.hardware.bydauto.showcarmode.IShowCarModelChangeListener
    public void onAirCtrlChangeEvent(int i8) {
        J(i8, true);
    }

    @Override // android.hardware.bydauto.showcarmode.IShowCarModelChangeListener
    public void onAirCtrlPressEvent(int i8) {
        J(i8, false);
    }

    @Override // android.hardware.bydauto.showcarmode.IShowCarModelChangeListener
    @Deprecated
    public void showCarGearChange(int i8) {
    }

    @Override // android.hardware.bydauto.showcarmode.IShowCarModelChangeListener
    public void showCarModelChange(final int i8) {
        BydLogHelper.logI("DisplayPresenter", "displayModel =" + i8 + ", currentDisplay =" + this.f7400b);
        if (i8 == 0 || this.f7402d == null || i8 == this.f7400b) {
            return;
        }
        if (this.f7400b == 0 && i8 == 3) {
            return;
        }
        int iG = g();
        if ((iG == 1 || 3 == iG) ? false : true) {
            if (i8 == 3) {
                this.f7400b = 3;
            }
            BydLogHelper.logI("DisplayPresenter", "displayModel =" + i8 + ", gear =" + iG);
            return;
        }
        boolean z7 = (Math.min(this.f7400b, i8) != 0) & ((this.f7400b & i8) == 0);
        p3.f fVar = p3.f.getInstance();
        if (Looper.getMainLooper().isCurrentThread()) {
            z(i8, this.f7400b);
        } else {
            final int i9 = this.f7400b;
            fVar.a(8, "DisplayPresenter", "enterDisplay", new f.a() { // from class: q3.c
                @Override // java.lang.Runnable
                public final void run() {
                    this.f7378d.A(i8, i9);
                }
            });
        }
        this.f7400b = i8;
        e(z7);
    }
}
