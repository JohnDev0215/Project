package com.loostone.libtuning.channel.byd;

import OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO00o;
import OooO00o.OooO00o.OooO0O0.OooO0o0.OooO0OO;
import OooO00o.OooO00o.OooO0Oo.OooO0Oo.OooO0O0;
import OooO00o.OooO00o.OooO0Oo.OooO0Oo.c.c;
import OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO;
import OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0o;
import OooO00o.OooO00o.OooO0o0.OooO0o.c;
import a.a.d.c.a;
import a.a.d.c.c;
import android.content.Context;
import android.hardware.usb.UsbDevice;
import com.android.volley.BuildConfig;
import com.blankj.utilcode.util.ThreadUtils;
import com.loostone.libserver.version2.bean.DongleParams;
import com.loostone.libserver.version2.bean.PlatformParams;
import com.loostone.libtuning.channel.BaseChannelMgr;
import com.loostone.libtuning.channel.byd.old.IDongleListener;
import com.loostone.libtuning.channel.byd.old.PuremicTuning;
import com.loostone.libtuning.channel.byd.upgrade.inf.IUpgradeAndReset;
import com.loostone.libtuning.channel.byd.upgrade.util.DongleUpdateUtil;
import com.loostone.libtuning.channel.byd.upgrade.util.MicUpdateUtil;
import com.loostone.libtuning.inf.extern.IBaseKeyService;
import com.loostone.libtuning.inf.extern.IKeyListener;
import com.loostone.libtuning.inf.extern.IMicControl;
import com.loostone.libtuning.inf.extern.IMicInfoListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Result;
import kotlin.jvm.internal.Ref$IntRef;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.o;

/* JADX INFO: loaded from: classes.dex */
public final class BiYaDiAMgr extends BaseChannelMgr implements a.a.d.c.a, IKeyListener, IMicInfoListener, a.a.d.c.c, OooO00o.OooO00o.OooO0Oo.a.b<Boolean>, c.b, OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.i {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "BiYaDiAMgr";
    private IUpgradeAndReset.DeviceInfoCallback deviceInfoCb;
    private final kotlin.a dongleParams$delegate;
    private Integer effectMode;
    private Integer effectVolume;
    private int[] factoryMicState;
    private boolean hardEffectEnable;
    private IDongleListener iDongleListener;
    private boolean initCheckPower;
    private boolean isAdvancePair;
    private boolean isDongleHard;
    private boolean isMicConnect;
    private Timer karaokeEnableTimer;
    private int[] lastState;
    private Integer micVolume;
    private final kotlin.a networkStatusMgr$delegate;
    private final kotlin.a platformParams$delegate;
    private Timer refreshTimer;
    private Integer sysVolume;
    private IUpgradeAndReset.UpgradePreconditionCallback upgradePreconditionCb;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(kotlin.jvm.internal.f fVar) {
            this();
        }
    }

    public BiYaDiAMgr() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public /* synthetic */ BiYaDiAMgr(Context context, int i, kotlin.jvm.internal.f fVar) {
        this((i & 1) != 0 ? OooO00o.OooO00o.OooO0O0.a.f39a.a() : context);
    }

    private final Timer checkKaraokeEnable() {
        final Ref$IntRef ref$IntRef = new Ref$IntRef();
        ref$IntRef.element = 1;
        Timer timer = this.karaokeEnableTimer;
        if (timer != null) {
            timer.cancel();
        }
        Timer timerA = kotlin.n.a.a("Karaoke_enable", false);
        timerA.scheduleAtFixedRate(new TimerTask() { // from class: com.loostone.libtuning.channel.byd.BiYaDiAMgr$checkKaraokeEnable$$inlined$fixedRateTimer$1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                String parameters = this.this$0.getAudioManager().getParameters("Karaoke_enable");
                OooO00o.OooO00o.OooO0O0.b.f41a.b("BiYaDiAMgr", kotlin.jvm.internal.h.i("check karaoke enable status = ", parameters));
                kotlin.jvm.internal.h.c(parameters, "karaokeEnableStr");
                if (kotlin.jvm.internal.h.a("true", o.j(parameters, "Karaoke_enable=", BuildConfig.FLAVOR, false, 4, null))) {
                    cancel();
                } else {
                    this.this$0.getAudioManager().setParameters("Karaoke_enable=true");
                }
                Ref$IntRef ref$IntRef2 = ref$IntRef;
                int i = ref$IntRef2.element;
                ref$IntRef2.element = i + 1;
                if (i >= 10) {
                    cancel();
                }
            }
        }, 1000L, 1000L);
        return timerA;
    }

    private final void clearDataByFactoryMicOff(boolean z) {
        IMicControl micControl = getKeyService().getMicControl();
        if (micControl == null) {
            return;
        }
        byte[] factoryMicStatus = micControl.getFactoryMicStatus();
        OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
        String string = Arrays.toString(factoryMicStatus);
        kotlin.jvm.internal.h.c(string, "java.util.Arrays.toString(this)");
        bVar.b(TAG, kotlin.jvm.internal.h.i("getFactoryMicStatus = ", string));
        if (z) {
            int[] iArr = this.factoryMicState;
            iArr[1] = factoryMicStatus[0];
            iArr[2] = factoryMicStatus[1];
            return;
        }
        int[] iArr2 = this.factoryMicState;
        if ((iArr2[1] == 1 && factoryMicStatus[0] == 0) || (iArr2[2] == 1 && factoryMicStatus[1] == 0)) {
            bVar.b(TAG, "onFactoryMicPowerOff");
            OooO00o.OooO00o.OooO0O0.OooO0o0.d.a(new Runnable() { // from class: com.loostone.libtuning.channel.byd.k
                @Override // java.lang.Runnable
                public final void run() {
                    BiYaDiAMgr.m3clearDataByFactoryMicOff$lambda27$lambda26$lambda25(this.f3832b);
                }
            });
        }
        int[] iArr3 = this.factoryMicState;
        iArr3[1] = factoryMicStatus[0];
        iArr3[2] = factoryMicStatus[1];
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: clearDataByFactoryMicOff$lambda-27$lambda-26$lambda-25, reason: not valid java name */
    public static final void m3clearDataByFactoryMicOff$lambda27$lambda26$lambda25(BiYaDiAMgr biYaDiAMgr) {
        kotlin.jvm.internal.h.d(biYaDiAMgr, "this$0");
        IDongleListener iDongleListener = biYaDiAMgr.getIDongleListener();
        if (iDongleListener == null) {
            return;
        }
        iDongleListener.onFactoryMicPowerOff();
    }

    private final DongleParams getDongleParams() {
        return (DongleParams) this.dongleParams$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getModelName() {
        String strA = OooO0o.f425a.A().a();
        if (strA == null) {
            strA = "byd_default";
        }
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, kotlin.jvm.internal.h.i("modelName = ", strA));
        return strA;
    }

    private final OooO00o.OooO00o.OooO0Oo.OooO0Oo.c.c getNetworkStatusMgr() {
        return (OooO00o.OooO00o.OooO0Oo.OooO0Oo.c.c) this.networkStatusMgr$delegate.getValue();
    }

    private final PlatformParams getPlatformParams() {
        return (PlatformParams) this.platformParams$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: init$lambda-0, reason: not valid java name */
    public static final void m4init$lambda0(BiYaDiAMgr biYaDiAMgr) {
        kotlin.jvm.internal.h.d(biYaDiAMgr, "this$0");
        biYaDiAMgr.getNetworkStatusMgr().d(biYaDiAMgr);
        biYaDiAMgr.getNetworkStatusMgr().e();
        biYaDiAMgr.getNetworkMgr().c().d(biYaDiAMgr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: init$lambda-1, reason: not valid java name */
    public static final void m5init$lambda1(BiYaDiAMgr biYaDiAMgr, String str) {
        kotlin.jvm.internal.h.d(biYaDiAMgr, "this$0");
        OooO00o.OooO00o.OooO0O0.b.f41a.d(TAG, kotlin.jvm.internal.h.i("crash happened, do release, reason :: ", str));
        biYaDiAMgr.release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onDeviceAddedWithHid$lambda-13, reason: not valid java name */
    public static final void m6onDeviceAddedWithHid$lambda13(BiYaDiAMgr biYaDiAMgr) {
        kotlin.jvm.internal.h.d(biYaDiAMgr, "this$0");
        IDongleListener iDongleListener = biYaDiAMgr.getIDongleListener();
        if (iDongleListener == null) {
            return;
        }
        iDongleListener.onMicPowerOnInit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onInfoEvent$lambda-31, reason: not valid java name */
    public static final void m7onInfoEvent$lambda31(BiYaDiAMgr biYaDiAMgr, Object obj) {
        kotlin.jvm.internal.h.d(biYaDiAMgr, "this$0");
        kotlin.jvm.internal.h.d(obj, "$param");
        IDongleListener iDongleListener = biYaDiAMgr.getIDongleListener();
        if (iDongleListener == null) {
            return;
        }
        iDongleListener.onInteractEffect(obj.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onInfoEvent$lambda-32, reason: not valid java name */
    public static final void m8onInfoEvent$lambda32(BiYaDiAMgr biYaDiAMgr, Object obj) {
        kotlin.jvm.internal.h.d(biYaDiAMgr, "this$0");
        kotlin.jvm.internal.h.d(obj, "$param");
        IDongleListener iDongleListener = biYaDiAMgr.getIDongleListener();
        if (iDongleListener == null) {
            return;
        }
        iDongleListener.onPremiumSpeakerState(((Boolean) obj).booleanValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onPairStateChanged$lambda-17, reason: not valid java name */
    public static final void m9onPairStateChanged$lambda17(BiYaDiAMgr biYaDiAMgr, int i) {
        kotlin.jvm.internal.h.d(biYaDiAMgr, "this$0");
        IDongleListener iDongleListener = biYaDiAMgr.getIDongleListener();
        if (iDongleListener == null) {
            return;
        }
        iDongleListener.onPairStateChanged(i, 6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onPairStateChanged$lambda-18, reason: not valid java name */
    public static final void m10onPairStateChanged$lambda18(BiYaDiAMgr biYaDiAMgr, int i) {
        kotlin.jvm.internal.h.d(biYaDiAMgr, "this$0");
        IDongleListener iDongleListener = biYaDiAMgr.getIDongleListener();
        if (iDongleListener == null) {
            return;
        }
        iDongleListener.onPairStateChanged(i, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onPairStateChanged$lambda-19, reason: not valid java name */
    public static final void m11onPairStateChanged$lambda19(BiYaDiAMgr biYaDiAMgr, int i) {
        kotlin.jvm.internal.h.d(biYaDiAMgr, "this$0");
        IDongleListener iDongleListener = biYaDiAMgr.getIDongleListener();
        if (iDongleListener == null) {
            return;
        }
        iDongleListener.onPairStateChanged(i, 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onPower$lambda-16, reason: not valid java name */
    public static final void m12onPower$lambda16(BiYaDiAMgr biYaDiAMgr, int i, boolean z) {
        kotlin.jvm.internal.h.d(biYaDiAMgr, "this$0");
        IDongleListener iDongleListener = biYaDiAMgr.getIDongleListener();
        if (iDongleListener == null) {
            return;
        }
        iDongleListener.onMicPowerChanged(i, z);
    }

    private final Timer refreshInfo() {
        final Ref$IntRef ref$IntRef = new Ref$IntRef();
        ref$IntRef.element = 1;
        Timer timer = this.refreshTimer;
        if (timer != null) {
            timer.cancel();
        }
        Timer timerA = kotlin.n.a.a("refreshInfo", false);
        timerA.scheduleAtFixedRate(new TimerTask() { // from class: com.loostone.libtuning.channel.byd.BiYaDiAMgr$refreshInfo$$inlined$fixedRateTimer$1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                IUpgradeAndReset.UpgradePreconditionCallback upgradePreconditionCallback = this.this$0.upgradePreconditionCb;
                if (upgradePreconditionCallback != null) {
                    OooO00o.OooO00o.OooO0O0.b.f41a.b("BiYaDiAMgr", "microphone is connected, checkUpgradePrecondition");
                    PuremicTuning.getInstance().checkUpgradePrecondition(upgradePreconditionCallback);
                }
                IUpgradeAndReset.DeviceInfoCallback deviceInfoCallback = this.this$0.deviceInfoCb;
                if (deviceInfoCallback != null) {
                    OooO00o.OooO00o.OooO0O0.b.f41a.b("BiYaDiAMgr", "microphone is connected, checkDeviceInfo");
                    PuremicTuning.getInstance().checkDeviceInfo(deviceInfoCallback);
                }
                Ref$IntRef ref$IntRef2 = ref$IntRef;
                int i = ref$IntRef2.element;
                ref$IntRef2.element = i + 1;
                if (i >= 5) {
                    cancel();
                }
            }
        }, 1000L, 1000L);
        return timerA;
    }

    public static /* synthetic */ void setKaraokeEffect$default(BiYaDiAMgr biYaDiAMgr, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        biYaDiAMgr.setKaraokeEffect(i, z);
    }

    public static /* synthetic */ void setMicPercentage$default(BiYaDiAMgr biYaDiAMgr, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        biYaDiAMgr.setMicPercentage(i, z);
    }

    public static /* synthetic */ void setReverbPercentage$default(BiYaDiAMgr biYaDiAMgr, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        biYaDiAMgr.setReverbPercentage(i, z);
    }

    public static /* synthetic */ void setSystemPercentage$default(BiYaDiAMgr biYaDiAMgr, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        biYaDiAMgr.setSystemPercentage(i, z);
    }

    private final void startKaraokeHard() {
        OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
        bVar.e(TAG, kotlin.jvm.internal.h.i("startKaraokeHard ------> getPowerStatus = ", Integer.valueOf(getPowerStatus())));
        if (getPowerStatus() == 3) {
            bVar.e(TAG, "------> hardOnOff true");
            this.hardEffectEnable = true;
            getAudioManager().setParameters("Karaoke_enable=true");
            this.karaokeEnableTimer = checkKaraokeEnable();
            OooO00o.OooO00o.OooO0o0.OooO0o.OooO0o.f481a.a().n();
        }
    }

    private final void stopKaraokeHard() {
        Object objM23constructorimpl;
        OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
        bVar.b(TAG, kotlin.jvm.internal.h.i("stopKaraoke[Hard effect], hardEffectEnable = ", Boolean.valueOf(this.hardEffectEnable)));
        if ((this.hardEffectEnable ? this : null) == null) {
            return;
        }
        try {
            this.hardEffectEnable = false;
            Timer timer = this.karaokeEnableTimer;
            if (timer != null) {
                timer.cancel();
            }
            getAudioManager().setParameters("Karaoke_enable=false");
            bVar.b(TAG, "stopKaraoke, Karaoke_enable false");
            OooO00o.OooO00o.OooO0o0.OooO0o.OooO0o.f481a.a().p();
            objM23constructorimpl = Result.m23constructorimpl(kotlin.l.f4084a);
        } catch (Throwable th) {
            objM23constructorimpl = Result.m23constructorimpl(kotlin.g.a(th));
        }
        Throwable thM26exceptionOrNullimpl = Result.m26exceptionOrNullimpl(objM23constructorimpl);
        if (thM26exceptionOrNullimpl != null) {
            OooO00o.OooO00o.OooO0O0.b.f41a.d(TAG, kotlin.jvm.internal.h.i("audioManager Karaoke_enable false, error :: ", thM26exceptionOrNullimpl));
            thM26exceptionOrNullimpl.printStackTrace();
        }
        Result.m22boximpl(objM23constructorimpl);
    }

    private final void uploadMicConnection(int i, int i2) {
        if (!this.isMicConnect) {
            if (3 == i2) {
                this.isMicConnect = true;
                OooO00o.OooO00o.OooO0O0.b.f41a.b(TAG, "onMicConnectionChanged true");
                OooO00o.OooO00o.OooO0O0.OooO0o0.d.a(new Runnable() { // from class: com.loostone.libtuning.channel.byd.f
                    @Override // java.lang.Runnable
                    public final void run() {
                        BiYaDiAMgr.m14uploadMicConnection$lambda23(this.f3822b);
                    }
                });
            }
            clearDataByFactoryMicOff(true);
            return;
        }
        int[] iArr = this.lastState;
        if ((3 != iArr[1] && i == 2 && 3 != i2) || (3 != iArr[2] && i == 1 && 3 != i2)) {
            this.isMicConnect = false;
            OooO00o.OooO00o.OooO0O0.b.f41a.b(TAG, "onMicConnectionChanged false");
            OooO00o.OooO00o.OooO0O0.OooO0o0.d.a(new Runnable() { // from class: com.loostone.libtuning.channel.byd.e
                @Override // java.lang.Runnable
                public final void run() {
                    BiYaDiAMgr.m13uploadMicConnection$lambda22(this.f3821b);
                }
            });
        }
        clearDataByFactoryMicOff(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: uploadMicConnection$lambda-22, reason: not valid java name */
    public static final void m13uploadMicConnection$lambda22(BiYaDiAMgr biYaDiAMgr) {
        kotlin.jvm.internal.h.d(biYaDiAMgr, "this$0");
        IDongleListener iDongleListener = biYaDiAMgr.getIDongleListener();
        if (iDongleListener == null) {
            return;
        }
        iDongleListener.onMicConnectionChanged(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: uploadMicConnection$lambda-23, reason: not valid java name */
    public static final void m14uploadMicConnection$lambda23(BiYaDiAMgr biYaDiAMgr) {
        kotlin.jvm.internal.h.d(biYaDiAMgr, "this$0");
        IDongleListener iDongleListener = biYaDiAMgr.getIDongleListener();
        if (iDongleListener == null) {
            return;
        }
        iDongleListener.onMicConnectionChanged(true);
    }

    private final void uploadPairResult(final int i, int i2) {
        final int i3 = 3;
        if (3 == i2) {
            int[] iArr = this.lastState;
            if (iArr[i] == 12) {
                i3 = 1;
            } else if (iArr[i] == 11) {
                i3 = 2;
            }
            OooO00o.OooO00o.OooO0O0.OooO0o0.d.a(new Runnable() { // from class: com.loostone.libtuning.channel.byd.a
                @Override // java.lang.Runnable
                public final void run() {
                    BiYaDiAMgr.m15uploadPairResult$lambda24(this.f3813b, i, i3);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: uploadPairResult$lambda-24, reason: not valid java name */
    public static final void m15uploadPairResult$lambda24(BiYaDiAMgr biYaDiAMgr, int i, int i2) {
        kotlin.jvm.internal.h.d(biYaDiAMgr, "this$0");
        IDongleListener iDongleListener = biYaDiAMgr.getIDongleListener();
        if (iDongleListener == null) {
            return;
        }
        iDongleListener.onPairResultChanged(i, i2, 1);
    }

    public final IDongleListener getDongleListener() {
        return this.iDongleListener;
    }

    public final IDongleListener getIDongleListener() {
        return this.iDongleListener;
    }

    @Override // com.loostone.libtuning.channel.BaseChannelMgr
    public void init() {
        super.init();
        this.initCheckPower = true;
        DongleUpdateUtil.getInstance().setInitFlag();
        getLocalDongleManager().getMEventManager().g(this);
        getKeyService().registerKeyListener(this);
        getKeyService().registerMicInfoListener(this);
        getInfoReceiver().d(this);
        ThreadUtils.l().execute(new Runnable() { // from class: com.loostone.libtuning.channel.byd.m
            @Override // java.lang.Runnable
            public final void run() {
                BiYaDiAMgr.m4init$lambda0(this.f3834b);
            }
        });
        OooO00o.OooO00o.OooO0o0.OooO0o.c.c(new c.a() { // from class: com.loostone.libtuning.channel.byd.l
            @Override // OooO00o.OooO00o.OooO0o0.OooO0o.c.a
            public final void a(String str) {
                BiYaDiAMgr.m5init$lambda1(this.f3833a, str);
            }
        });
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, " ------> init()");
    }

    @Override // a.a.d.c.c
    public void onBydMicKeyEvent(int i) {
        Integer numA;
        Integer numA2;
        OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
        bVar.e(TAG, " ------> onBydMicKeyEvent, key = " + i + ", VerifyStatus = " + getVerifyStatus());
        if (getVerifyStatus() == 2) {
            if (131 != i) {
                if (132 == i) {
                    OooO.f199a.f1().e(0);
                    setMicStatus(4);
                    if (this.isDongleHard && (numA = OooO0o.f425a.D().a()) != null && numA.intValue() == 1) {
                        stopKaraokeHard();
                        return;
                    } else {
                        stopKaraoke();
                        return;
                    }
                }
                return;
            }
            OooO.f199a.i1().e(0);
            IMicControl micControl = getKeyService().getMicControl();
            if (micControl != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(kotlin.jvm.internal.h.i("DgF:", micControl.getFirmwareVersion((byte) 1)));
                sb.append(kotlin.jvm.internal.h.i(", M1F:", micControl.getFirmwareVersion((byte) 2)));
                sb.append(kotlin.jvm.internal.h.i(", M2F:", micControl.getFirmwareVersion((byte) 3)));
                sb.append(kotlin.jvm.internal.h.i(", DgH:", micControl.getHardwareVersion((byte) 5)));
                sb.append(kotlin.jvm.internal.h.i(", M1H:", micControl.getHardwareVersion((byte) 1)));
                sb.append(kotlin.jvm.internal.h.i(", M2H:", micControl.getHardwareVersion((byte) 2)));
                bVar.b(TAG, "dongle and mic version info = {" + ((Object) sb) + '}');
            }
            setMicStatus(3);
            if (this.isDongleHard && (numA2 = OooO0o.f425a.D().a()) != null && numA2.intValue() == 1) {
                startKaraokeHard();
            } else {
                startKaraoke();
            }
        }
    }

    @Override // a.a.d.c.a
    public void onDeviceAddedWithHid(OooO00o oooO00o) {
        kotlin.jvm.internal.h.d(oooO00o, "cachedDevice");
        OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
        bVar.b(TAG, " ------> 设备验证通过, 打开so或者ko");
        bVar.e(TAG, " ------> SDK VERSION 4.0.32_2025-03-25 18:00:02_5fec3632");
        DongleUpdateUtil.getInstance().setUsbDevice(oooO00o, true);
        OooO0o oooO0o = OooO0o.f425a;
        Integer numA = oooO0o.M().a();
        if ((numA == null ? 1 : numA.intValue()) == 1) {
            int iA = OooO00o.OooO00o.OooO0O0.OooO0o0.b.a(oooO00o.t());
            OooO.f199a.D0().e(iA + "00");
            bVar.e(TAG, "------> 设置capture card: " + iA + " device: 00");
        }
        getVolumeAdjustment().setAef3FsEnable(true);
        this.isDongleHard = oooO00o.B();
        this.isAdvancePair = oooO00o.z();
        int i = 0;
        if (this.isDongleHard) {
            bVar.e(TAG, kotlin.jvm.internal.h.i("------> hardOnOff isSysSupportHard = ", oooO0o.D().d()));
            Integer numA2 = oooO0o.D().a();
            if (numA2 == null || numA2.intValue() != 1) {
                getVolumeAdjustment().setAef1ReverbEnable(false);
                getVolumeAdjustment().setAef2EQEnable(false);
                getVolumeAdjustment().setAef2EchoEnable(false);
                getVolumeAdjustment().setAef2ReverbEnable(false);
                getVolumeAdjustment().setAef2CompressorEnable(false);
                getVolumeAdjustment().setAef3FsEnable(false);
                getVolumeAdjustment().setAef2VoiceChangeEnable(false);
                getVolumeAdjustment().setMicVolume(100);
                startKaraoke();
            }
        } else {
            bVar.e(TAG, "------> hardOnOff false");
            getVolumeAdjustment().setAef1ReverbEnable(false);
            getVolumeAdjustment().setAef2EQEnable(true);
            getVolumeAdjustment().setAef2EchoEnable(true);
            getVolumeAdjustment().setAef2ReverbEnable(true);
            getVolumeAdjustment().setAef2CompressorEnable(true);
            getVolumeAdjustment().setAef3FsEnable(true);
            getVolumeAdjustment().setAef2VoiceChangeEnable(false);
            Integer num = this.micVolume;
            if (num != null) {
                getVolumeAdjustment().setMicVolume(num.intValue());
            }
            startKaraoke();
        }
        OooO00o.OooO00o.OooO0O0.OooO0o0.OooO00o oooO00o2 = OooO00o.OooO00o.OooO0O0.OooO0o0.OooO00o.f18a;
        UsbDevice usbDeviceW = oooO00o.w();
        if (StringsKt__StringsKt.n(oooO00o2.l(usbDeviceW == null ? null : usbDeviceW.getProductName()), "Q2", true)) {
            OooO oooO = OooO.f199a;
            oooO.l0().e(1);
            oooO.f1().e(0);
        } else {
            OooO oooO2 = OooO.f199a;
            oooO2.i1().e(0);
            oooO2.l0().e(0);
        }
        IBaseKeyService keyService = getKeyService();
        if (oooO00o.A() || this.isDongleHard) {
            keyService.setDevice(oooO00o.w());
            if (this.isDongleHard) {
                Integer num2 = this.micVolume;
                if (num2 != null) {
                    setMicPercentage(num2.intValue(), true);
                }
                Integer num3 = this.sysVolume;
                if (num3 != null) {
                    setSystemPercentage(num3.intValue(), true);
                }
                Integer num4 = this.effectMode;
                if (num4 != null) {
                    setKaraokeEffect(num4.intValue(), true);
                }
                Integer num5 = this.effectVolume;
                if (num5 != null) {
                    setReverbPercentage(num5.intValue(), true);
                }
                Integer numA3 = oooO0o.D().a();
                if (numA3 != null && numA3.intValue() == 1) {
                    startKaraokeHard();
                }
            }
            keyService.start();
            dynamicAdjustCompPregain();
        } else {
            adjustMicVolume(0);
        }
        if (this.initCheckPower) {
            this.initCheckPower = false;
            byte[] bArrO = new OooO00o.OooO00o.OooO0o0.OooO0O0.OooO0OO.OooO00o(oooO00o.w()).o();
            String string = Arrays.toString(bArrO);
            kotlin.jvm.internal.h.c(string, "java.util.Arrays.toString(this)");
            bVar.e(TAG, kotlin.jvm.internal.h.i("micPowerStatus, micKey = ", string));
            int length = bArrO.length;
            byte b2 = 0;
            while (i < length) {
                byte b3 = bArrO[i];
                i++;
                b2 = (byte) (b2 | b3);
            }
            if (((byte) (b2 & 64)) == 64) {
                OooO00o.OooO00o.OooO0O0.OooO0o0.d.a(new Runnable() { // from class: com.loostone.libtuning.channel.byd.d
                    @Override // java.lang.Runnable
                    public final void run() {
                        BiYaDiAMgr.m6onDeviceAddedWithHid$lambda13(this.f3820b);
                    }
                });
            }
            OooO00o.OooO00o.OooO0OO.OooO0O0.OooO0o.f54a.a().endHid();
        }
        OooO00o.OooO00o.OooO0Oo.OooO0Oo.b.a.f128a.a(true);
        getNetworkMgr().c().m();
        getNetworkMgr().c().i(getPlatformParams());
        OooO0O0 oooO0O0C = getNetworkMgr().c();
        DongleParams dongleParams = getDongleParams();
        dongleParams.setDongle(oooO00o);
        oooO0O0C.e(dongleParams);
    }

    @Override // a.a.d.c.a
    public void onDeviceDeniedByHid(OooO00o oooO00o) {
        kotlin.jvm.internal.h.d(oooO00o, "cachedDevice");
        if (oooO00o.v() == OooO00o.OooO0O0.S) {
            Integer numA = OooO0o.f425a.M().a();
            if ((numA == null ? 1 : numA.intValue()) == 1) {
                int iA = OooO00o.OooO00o.OooO0O0.OooO0o0.b.a(oooO00o.t());
                OooO.f199a.D0().e(iA + "00");
                OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "------> 设置capture card: " + iA + " device: 00");
            }
            getFullSceneMode().d();
        }
    }

    @Override // a.a.d.c.a
    public void onDeviceRemoved(OooO00o oooO00o) {
        kotlin.jvm.internal.h.d(oooO00o, "cachedDevice");
        OooO00o.OooO00o.OooO0O0.b.f41a.f(TAG, kotlin.jvm.internal.h.i(" ------> 设备移除 ", oooO00o.t()));
        DongleUpdateUtil.getInstance().setUsbDevice(oooO00o, false);
        getKeyService().setDevice(null);
        OooO00o.OooO00o.OooO0Oo.OooO0Oo.b.a.f128a.a(false);
        getNetworkMgr().c().o();
        getNetworkMgr().c().h();
        this.isDongleHard = false;
        this.isAdvancePair = false;
        setMicStatus(4);
        try {
            stopKaraoke();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Timer timer = this.refreshTimer;
        if (timer != null) {
            timer.cancel();
        }
        Timer timer2 = this.karaokeEnableTimer;
        if (timer2 != null) {
            timer2.cancel();
        }
        stopKaraokeHard();
        try {
            getKeyService().stop();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            OooO00o.OooO00o.OooO0OO.OooO0O0.OooO0o.f54a.a().i();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    @Override // a.a.d.c.a
    public void onDonglePermissionDenied() {
    }

    @Override // a.a.d.c.a
    public void onDonglePermissionGranted(UsbDevice usbDevice) {
        kotlin.jvm.internal.h.d(usbDevice, "usbDevice");
        OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
        bVar.e(TAG, kotlin.jvm.internal.h.i(" ------> 权限申请成功 ", usbDevice.getProductName()));
        bVar.e(TAG, kotlin.jvm.internal.h.i(" ------> 设备数量 ", Integer.valueOf(((ArrayList) getLocalDongleManager().getMCachedDongleDeviceManager().i()).size())));
    }

    @Override // OooO00o.OooO00o.OooO0Oo.a.b
    public void onError() {
        if (getVerifyStatus() != 2) {
            stopKaraoke();
            getKeyService().stop();
            stopKaraokeHard();
        }
    }

    @Override // com.loostone.libtuning.inf.extern.IMicInfoListener
    public void onInfoEvent(int i, int i2, final Object obj) {
        kotlin.jvm.internal.h.d(obj, "param");
        OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
        bVar.e(TAG, "onInfoEvent, micId = " + i + ", eventType = " + i2 + ", param = " + obj);
        if (i2 == 1) {
            getVolumeAdjustment().setMicVolume(((Integer) obj).intValue());
            return;
        }
        if (i2 == 3) {
            bVar.e(TAG, kotlin.jvm.internal.h.i("互动声效 : ", obj));
            OooO00o.OooO00o.OooO0O0.OooO0o0.d.a(new Runnable() { // from class: com.loostone.libtuning.channel.byd.j
                @Override // java.lang.Runnable
                public final void run() {
                    BiYaDiAMgr.m7onInfoEvent$lambda31(this.f3830b, obj);
                }
            });
        } else {
            if (i2 != 6) {
                return;
            }
            OooO00o.OooO00o.OooO0O0.OooO0o0.d.a(new Runnable() { // from class: com.loostone.libtuning.channel.byd.i
                @Override // java.lang.Runnable
                public final void run() {
                    BiYaDiAMgr.m8onInfoEvent$lambda32(this.f3828b, obj);
                }
            });
        }
    }

    @Override // com.loostone.libtuning.inf.extern.IMicInfoListener
    public void onMicBattery(int i, int i2) {
    }

    @Override // com.loostone.libtuning.inf.extern.IMicInfoListener
    public void onMicBatteryPercentage(int i, double d2) {
    }

    @Override // a.a.d.c.a
    public void onMicConnectState(UsbDevice usbDevice, boolean z) {
        a.C0027a.a(this, usbDevice, z);
    }

    @Override // OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.i
    public void onMicTimeEnd() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "onMicTimeEnd");
        OooO00o oooO00oF = getLocalDongleManager().getMCachedDongleDeviceManager().f();
        if (oooO00oF == null) {
            return;
        }
        OooO0O0 oooO0O0C = getNetworkMgr().c();
        DongleParams dongleParams = getDongleParams();
        dongleParams.setDongle(oooO00oF);
        oooO0O0C.e(dongleParams);
    }

    @Override // OooO00o.OooO00o.OooO0Oo.OooO0Oo.c.c.b
    public void onNetworkChange(boolean z) {
        OooO00o oooO00oF;
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, kotlin.jvm.internal.h.i("isNetworkAvailable = ", Boolean.valueOf(z)));
        if (!z || (oooO00oF = getLocalDongleManager().getMCachedDongleDeviceManager().f()) == null) {
            return;
        }
        getNetworkMgr().c().i(getPlatformParams());
        OooO0O0 oooO0O0C = getNetworkMgr().c();
        DongleParams dongleParams = getDongleParams();
        dongleParams.setDongle(oooO00oF);
        oooO0O0C.e(dongleParams);
    }

    @Override // com.loostone.libtuning.inf.extern.IKeyListener
    public void onPairStateChanged(final int i, int i2) {
        IUpgradeAndReset.DeviceInfoCallback deviceInfoCallback;
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "onPairStateChanged: mic = " + i + ", state = " + i2);
        if (this.isAdvancePair) {
            if (i2 == 11) {
                OooO00o.OooO00o.OooO0O0.OooO0o0.d.a(new Runnable() { // from class: com.loostone.libtuning.channel.byd.g
                    @Override // java.lang.Runnable
                    public final void run() {
                        BiYaDiAMgr.m9onPairStateChanged$lambda17(this.f3823b, i);
                    }
                });
            } else if (i2 == 3) {
                int[] iArr = this.lastState;
                if (iArr[i] == 11 || iArr[i] == 12 || iArr[i] == 4) {
                    OooO0OO.f24a.f("pair_success");
                }
            }
        } else if (i2 == 11) {
            OooO00o.OooO00o.OooO0O0.OooO0o0.d.a(new Runnable() { // from class: com.loostone.libtuning.channel.byd.c
                @Override // java.lang.Runnable
                public final void run() {
                    BiYaDiAMgr.m10onPairStateChanged$lambda18(this.f3818b, i);
                }
            });
        } else if (i2 == 3) {
            int[] iArr2 = this.lastState;
            if (iArr2[i] == 11 || iArr2[i] == 12 || iArr2[i] == 4) {
                OooO00o.OooO00o.OooO0O0.OooO0o0.d.a(new Runnable() { // from class: com.loostone.libtuning.channel.byd.b
                    @Override // java.lang.Runnable
                    public final void run() {
                        BiYaDiAMgr.m11onPairStateChanged$lambda19(this.f3816b, i);
                    }
                });
            }
        }
        uploadMicConnection(i, i2);
        uploadPairResult(i, i2);
        this.lastState[i] = i2;
        if (3 == i2) {
            if (this.upgradePreconditionCb == null && this.deviceInfoCb == null) {
                return;
            }
            this.refreshTimer = refreshInfo();
            return;
        }
        if (2 != i2 || (deviceInfoCallback = this.deviceInfoCb) == null) {
            return;
        }
        PuremicTuning.getInstance().checkDeviceInfo(deviceInfoCallback);
    }

    @Override // OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.i
    public void onPlatformTimeEnd() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "onPlatformTimeEnd");
        getNetworkMgr().c().i(getPlatformParams());
    }

    @Override // com.loostone.libtuning.inf.extern.IKeyListener
    public void onPower(final int i, final boolean z) {
        if (i != 0) {
            return;
        }
        if (z) {
            OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, " ------> 电源按下");
            getFullSceneMode().a(1);
        } else {
            OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, " ------> 电源弹起");
            getFullSceneMode().a(0);
        }
        OooO00o.OooO00o.OooO0O0.OooO0o0.d.a(new Runnable() { // from class: com.loostone.libtuning.channel.byd.h
            @Override // java.lang.Runnable
            public final void run() {
                BiYaDiAMgr.m12onPower$lambda16(this.f3825b, i, z);
            }
        });
    }

    @Override // a.a.d.c.c
    public void onScreenChange(boolean z) {
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, " ------> 亮屏息屏");
    }

    @Override // a.a.d.c.c
    public void onSkyworthKey(int i) {
        c.a.b(this);
    }

    @Override // OooO00o.OooO00o.OooO0Oo.a.b
    public /* bridge */ /* synthetic */ void onSuccess(Boolean bool) {
        onSuccess(bool.booleanValue());
    }

    public void onSuccess(boolean z) {
    }

    @Override // com.loostone.libtuning.inf.extern.IKeyListener
    public void onTVPhoneSwitch(int i, boolean z) {
        if (i != 0) {
            return;
        }
        if (z) {
            OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, " ------> TV按下");
        } else {
            OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, " ------> TV弹起");
        }
    }

    @Override // com.loostone.libtuning.inf.extern.IKeyListener
    public void onVoice(int i, boolean z) {
        if (i != 0) {
            return;
        }
        if (z) {
            OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, " ------> 语音按下");
        } else {
            OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, " ------> 语音弹起");
        }
    }

    @Override // a.a.d.c.c
    public void onVolumeChange(int i) {
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, kotlin.jvm.internal.h.i(" ------> 设置音量 ", Integer.valueOf(i)));
    }

    @Override // com.loostone.libtuning.inf.extern.IKeyListener
    public void onVolumeDown(int i, boolean z) {
        if (i != 0) {
            return;
        }
        if (z) {
            OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, " ------> 音量减按下");
        } else {
            OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, " ------> 音量减弹起");
        }
    }

    @Override // com.loostone.libtuning.inf.extern.IKeyListener
    public void onVolumeUp(int i, boolean z) {
        if (i != 0) {
            return;
        }
        if (z) {
            OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, " ------> 音量加按下");
        } else {
            OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, " ------> 音量加弹起");
        }
    }

    @Override // com.loostone.libtuning.channel.BaseChannelMgr
    public void release() {
        super.release();
        this.iDongleListener = null;
        this.isDongleHard = false;
        this.isAdvancePair = false;
        getNetworkStatusMgr().c();
        Timer timer = this.refreshTimer;
        if (timer != null) {
            timer.cancel();
        }
        Timer timer2 = this.karaokeEnableTimer;
        if (timer2 != null) {
            timer2.cancel();
        }
        stopKaraokeHard();
        try {
            getLocalDongleManager().getMEventManager().q(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            getKeyService().unRegisterKeyListener(this);
            getKeyService().unRegisterMicInfoListener(this);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            getInfoReceiver().h(this);
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        try {
            getKeyService().stop();
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        try {
            OooO00o.OooO00o.OooO0OO.OooO0O0.OooO0o.f54a.a().i();
        } catch (Exception e5) {
            e5.printStackTrace();
        }
        try {
            OooO0OO.f24a.e();
        } catch (Exception e6) {
            e6.printStackTrace();
        }
        try {
            DongleUpdateUtil.getInstance().release();
            MicUpdateUtil.getInstance().release();
        } catch (Exception e7) {
            e7.printStackTrace();
        }
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, " ------> release()");
    }

    public final void setDeviceInfoCallback(IUpgradeAndReset.DeviceInfoCallback deviceInfoCallback) {
        this.deviceInfoCb = deviceInfoCallback;
    }

    public final void setDongleListener(IDongleListener iDongleListener) {
        this.iDongleListener = iDongleListener;
    }

    public final void setIDongleListener(IDongleListener iDongleListener) {
        this.iDongleListener = iDongleListener;
    }

    public final void setKaraokeEffect(int i, boolean z) {
        IMicControl micControl;
        this.effectMode = Integer.valueOf(i);
        if (this.isDongleHard) {
            int i2 = 16;
            if (i == 0) {
                i2 = 32;
            } else if (i != 1) {
                if (i == 2) {
                    i2 = 48;
                } else if (i == 3) {
                    i2 = 1;
                }
            }
            IMicControl micControl2 = getKeyService().getMicControl();
            Integer numValueOf = micControl2 == null ? null : Integer.valueOf(micControl2.getDongleSoundEffect());
            if ((z || numValueOf == null || i2 != numValueOf.intValue()) && (micControl = getKeyService().getMicControl()) != null) {
                micControl.setDongleSoundEffect(i2);
            }
        }
    }

    public final void setMicPercentage(int i, boolean z) {
        IMicControl micControl;
        this.micVolume = Integer.valueOf(i);
        if (this.isDongleHard) {
            if (i < 0) {
                i = 0;
            } else if (i > 100) {
                i = 100;
            }
            IMicControl micControl2 = getKeyService().getMicControl();
            Integer numValueOf = micControl2 == null ? null : Integer.valueOf(micControl2.getDongleVolume());
            OooO00o.OooO00o.OooO0O0.b.f41a.b(TAG, "setMicPercentage, dongleVolume=" + numValueOf + ", dglVol=" + i);
            if ((z || i == 0 || i == 100 || numValueOf == null || i != numValueOf.intValue()) && (micControl = getKeyService().getMicControl()) != null) {
                micControl.setDongleVolume(i);
            }
            Integer numA = OooO0o.f425a.D().a();
            if ((numA == null || numA.intValue() != 1 ? this : null) == null) {
                return;
            }
            getVolumeAdjustment().setMicVolume(100);
        }
    }

    public final void setReverbPercentage(int i, boolean z) {
        IMicControl micControl;
        this.effectVolume = Integer.valueOf(i);
        if (this.isDongleHard) {
            if (i < 0) {
                i = 0;
            } else if (i > 100) {
                i = 100;
            }
            IMicControl micControl2 = getKeyService().getMicControl();
            Integer numValueOf = micControl2 == null ? null : Integer.valueOf(micControl2.getEffectVolume());
            if ((z || i == 0 || i == 100 || numValueOf == null || i != numValueOf.intValue()) && (micControl = getKeyService().getMicControl()) != null) {
                micControl.setEffectVolume(i);
            }
        }
    }

    public final void setSystemPercentage(int i, boolean z) {
        IMicControl micControl;
        this.sysVolume = Integer.valueOf(i);
        if (this.isDongleHard) {
            if (i < 0) {
                i = 0;
            } else if (i > 100) {
                i = 100;
            }
            IMicControl micControl2 = getKeyService().getMicControl();
            Integer numValueOf = micControl2 == null ? null : Integer.valueOf(micControl2.getSystemVolume());
            if ((z || i == 0 || i == 100 || numValueOf == null || i != numValueOf.intValue()) && (micControl = getKeyService().getMicControl()) != null) {
                micControl.setSystemVolume(i);
            }
        }
    }

    public final void setUpgradePreconditionCallback(IUpgradeAndReset.UpgradePreconditionCallback upgradePreconditionCallback) {
        this.upgradePreconditionCb = upgradePreconditionCallback;
    }

    @Override // com.loostone.libtuning.channel.BaseChannelMgr
    public void startKaraoke() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, kotlin.jvm.internal.h.i("startKaraoke ------> getPowerStatus = ", Integer.valueOf(getPowerStatus())));
        if (getPowerStatus() == 3) {
            super.startKaraoke();
        }
    }

    public final void switchVocalRemoveState(boolean z) {
        Integer numA;
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, kotlin.jvm.internal.h.i("switchVocalRemoveState ", Boolean.valueOf(z)));
        if (this.isDongleHard && (numA = OooO0o.f425a.D().a()) != null && numA.intValue() == 1) {
            if (z) {
                OooO00o.OooO00o.OooO0o0.OooO0o.OooO0o.f481a.a().n();
            } else {
                OooO00o.OooO00o.OooO0o0.OooO0o.OooO0o.f481a.a().p();
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BiYaDiAMgr(Context context) {
        super(context);
        kotlin.jvm.internal.h.d(context, "context");
        this.networkStatusMgr$delegate = kotlin.d.b(new kotlin.jvm.b.a<OooO00o.OooO00o.OooO0Oo.OooO0Oo.c.c>() { // from class: com.loostone.libtuning.channel.byd.BiYaDiAMgr$networkStatusMgr$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.b.a
            public final OooO00o.OooO00o.OooO0Oo.OooO0Oo.c.c invoke() {
                return new OooO00o.OooO00o.OooO0Oo.OooO0Oo.c.c();
            }
        });
        this.platformParams$delegate = kotlin.d.b(new kotlin.jvm.b.a<PlatformParams>() { // from class: com.loostone.libtuning.channel.byd.BiYaDiAMgr$platformParams$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.b.a
            public final PlatformParams invoke() {
                PlatformParams platformParams = new PlatformParams(this.this$0.getModelName(), com.loostone.libtuning.BuildConfig.VERSION_NAME);
                platformParams.setConfigListener(this.this$0.getMJsonCallback());
                return platformParams;
            }
        });
        this.dongleParams$delegate = kotlin.d.b(new kotlin.jvm.b.a<DongleParams>() { // from class: com.loostone.libtuning.channel.byd.BiYaDiAMgr$dongleParams$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.b.a
            public final DongleParams invoke() {
                DongleParams dongleParams = new DongleParams(null);
                BiYaDiAMgr biYaDiAMgr = this.this$0;
                dongleParams.setActivateListener(biYaDiAMgr.getMActivateCallback());
                dongleParams.setConfigListener(biYaDiAMgr.getMJsonCallback());
                dongleParams.setDownloadListener(biYaDiAMgr.getMFirmwareCallback());
                return dongleParams;
            }
        });
        this.initCheckPower = true;
        this.lastState = new int[3];
        this.factoryMicState = new int[3];
    }
}
