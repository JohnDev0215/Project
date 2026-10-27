package com.loostone.libtuning.channel.byd.old;

import OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO00o;
import OooO00o.OooO00o.OooO0O0.OooO0o0.OooO0OO;
import OooO00o.OooO00o.OooO0o0.OooO0o.OooOO0;
import OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0OO.OooO0O0;
import android.app.Application;
import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.media.AudioManager;
import android.text.TextUtils;
import android.util.ArrayMap;
import com.android.volley.BuildConfig;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.w;
import com.loostone.libtuning.channel.BaseChannelMgr;
import com.loostone.libtuning.channel.byd.BiYaDiAMgr;
import com.loostone.libtuning.channel.byd.data.BYDCache;
import com.loostone.libtuning.channel.byd.upgrade.inf.IUpgradeAndReset;
import com.loostone.libtuning.channel.byd.upgrade.util.DongleUpdateUtil;
import com.loostone.libtuning.channel.byd.upgrade.util.MicUpdateUtil;
import com.loostone.libtuning.define.BaseDefine;
import com.loostone.libtuning.inf.extern.IAudioFFTProcess;
import com.loostone.libtuning.inf.extern.IAudioTempoProcess;
import com.loostone.libtuning.inf.extern.IFFTReadListener;
import com.loostone.libtuning.inf.extern.IMicControl;
import com.loostone.libtuning.inf.extern.ITempoCallBack;
import com.loostone.libtuning.inf.extern.IVolumeAdjustment;
import com.loostone.libtuning.process.dongle.LocalDongleManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class PuremicTuning implements InsideDongle, IUpgradeAndReset {
    private static final String TAG = "PuremicTuningInf";
    public static boolean attachDongleEvent = false;
    private static volatile PuremicTuning instance = null;
    private static boolean isInit = false;
    private static boolean micP1ConnectStatus = false;
    private IAudioTempoProcess audioTempoProcess;
    private IUpgradeAndReset.CheckNewVersionCallback checkNewVersionCb;
    private IUpgradeAndReset.DeviceInfoCallback deviceInfoCb;
    private IDongleListener localDongleListener;
    private AudioManager mAudioManager;
    private IAudioFFTProcess musicFFTControl;
    private IUpgradeAndReset.UpgradeCallback upgradeCb;
    private IUpgradeAndReset.UpgradePreconditionCallback upgradePreconditionCb;
    private IVolumeAdjustment volumeAdjustment = null;
    private Long currentTimeMillis = Long.valueOf(System.currentTimeMillis());
    private final ArrayMap<String, String> mSystemPropertiesMap = new ArrayMap<>();
    private w.d<String> micPercentageTask = null;
    private w.d<String> rvbPercentageTask = null;
    private w.d<String> systemPercentageTask = null;
    private w.d<String> karaokeEffectTask = null;
    private final com.loostone.libtuning.inf.extern.IDongleListener dongleListener = new AnonymousClass7();
    private int mProcess = 0;
    private boolean mRealUpgrading = false;
    private w.d<String> notifyDongleInfoTask = null;
    private final com.loostone.libtuning.PuremicTuning realInstance = com.loostone.libtuning.PuremicTuning.Companion.getInstance();

    /* JADX INFO: renamed from: com.loostone.libtuning.channel.byd.old.PuremicTuning$10, reason: invalid class name */
    public class AnonymousClass10 extends w.d<String> {
        public final /* synthetic */ StringBuilder val$dglInfo;
        public final /* synthetic */ StringBuilder val$micInfo;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass10(w.b bVar, StringBuilder sb, StringBuilder sb2) {
            super(bVar);
            this.val$dglInfo = sb;
            this.val$micInfo = sb2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onSuccess$0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void a(StringBuilder sb, StringBuilder sb2) {
            PuremicTuning.this.deviceInfoCb.onDongleInfoChange(sb.toString());
            PuremicTuning.this.deviceInfoCb.onMicInfoChange(sb2.toString());
        }

        @Override // com.blankj.utilcode.util.ThreadUtils.e
        public String doInBackground() {
            OooO00o oooO00oF = null;
            try {
                oooO00oF = PuremicTuning.this.realInstance.getDongleManager().getMCachedDongleDeviceManager().f();
                if (oooO00oF != null) {
                    StringBuilder sb = this.val$dglInfo;
                    sb.append("设备名：");
                    sb.append(oooO00oF.t());
                    StringBuilder sb2 = this.val$dglInfo;
                    sb2.append("\n");
                    sb2.append("厂商名：");
                    sb2.append(oooO00oF.y());
                    StringBuilder sb3 = this.val$dglInfo;
                    sb3.append("\n");
                    sb3.append("序列号：");
                    sb3.append(oooO00oF.a());
                    String strK = oooO00oF.k();
                    if (!TextUtils.isEmpty(strK)) {
                        StringBuilder sb4 = this.val$dglInfo;
                        sb4.append("\n");
                        sb4.append("固件版本：");
                        sb4.append(DongleUpdateUtil.getInstance().convertVersion(strK));
                    }
                }
            } catch (Exception e) {
                OooO00o.OooO00o.OooO0O0.b.f41a.d(PuremicTuning.TAG, "notifyDongleInfo error :: " + e);
                e.printStackTrace();
            }
            if (oooO00oF == null) {
                return "notifyDongleInfo success";
            }
            this.val$micInfo.append(PuremicTuning.this.splicingMicInfo());
            return "notifyDongleInfo success";
        }

        @Override // com.blankj.utilcode.util.w.d, com.blankj.utilcode.util.ThreadUtils.e
        public void onSuccess(String str) {
            final StringBuilder sb = this.val$dglInfo;
            final StringBuilder sb2 = this.val$micInfo;
            ThreadUtils.q(new Runnable() { // from class: com.loostone.libtuning.channel.byd.old.a
                @Override // java.lang.Runnable
                public final void run() {
                    this.f3835b.a(sb, sb2);
                }
            });
        }
    }

    /* JADX INFO: renamed from: com.loostone.libtuning.channel.byd.old.PuremicTuning$7, reason: invalid class name */
    public class AnonymousClass7 implements com.loostone.libtuning.inf.extern.IDongleListener {
        public AnonymousClass7() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onDongleDetach$0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void a() {
            PuremicTuning.this.upgradeCb.onUpgradeProgress(100);
            PuremicTuning.this.upgradeCb.onUpgradeSuccess();
        }

        @Override // com.loostone.libtuning.inf.extern.IDongleListener
        public void onDongleAttach() {
            OooO00o.OooO00o.OooO0O0.b.f41a.e(PuremicTuning.TAG, "onDongleAttach: " + PuremicTuning.attachDongleEvent);
            PuremicTuning.this.notifyDongleInfo();
            if (!PuremicTuning.attachDongleEvent || PuremicTuning.this.localDongleListener == null) {
                return;
            }
            PuremicTuning.this.localDongleListener.attach();
        }

        @Override // com.loostone.libtuning.inf.extern.IDongleListener
        public void onDongleDetach() {
            if (PuremicTuning.this.mProcess >= 100) {
                ThreadUtils.q(new Runnable() { // from class: com.loostone.libtuning.channel.byd.old.d
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f3842b.a();
                    }
                });
                PuremicTuning.this.mProcess = 0;
            }
            PuremicTuning.this.notifyDongleInfo();
            PuremicTuning.this.getMusicAudioFFTControl().stop();
            PuremicTuning.this.getMusicAudioTempoControl().deinit();
            if (PuremicTuning.this.localDongleListener != null) {
                PuremicTuning.this.localDongleListener.detach();
            }
        }

        @Override // com.loostone.libtuning.inf.extern.IDongleListener
        public void onMicConnectState(UsbDevice usbDevice, boolean z) {
            OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
            bVar.b(PuremicTuning.TAG, "status = " + z);
            boolean unused = PuremicTuning.micP1ConnectStatus = z;
            if (!z) {
                try {
                    if (DongleUpdateUtil.getInstance().isRealUpdating() && !MicUpdateUtil.getInstance().getUpgradeList().isEmpty()) {
                        bVar.b(PuremicTuning.TAG, "mic remove, upgrade break!!!");
                        DongleUpdateUtil.getInstance().stopUpdate();
                    }
                } catch (Exception e) {
                    OooO00o.OooO00o.OooO0O0.b.f41a.d(PuremicTuning.TAG, "onMicConnectState = " + z + ", error!");
                    e.printStackTrace();
                    return;
                }
            }
            MicUpdateUtil.getInstance().setUsbDevice(usbDevice, z);
            PuremicTuning.this.notifyUpgradePrecondition();
        }
    }

    private PuremicTuning() {
    }

    private String convertMicSupplier(String str) {
        int i;
        try {
            i = Integer.parseInt(str);
        } catch (Exception e) {
            OooO00o.OooO00o.OooO0O0.b.f41a.d(TAG, "convertMicSupplier error :: " + e);
            i = -1;
        }
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? BuildConfig.FLAVOR : "GT" : "TH" : "LS" : "FD";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public OooO00o getActiveDongle() {
        try {
            return this.realInstance.getDongleManager().getMCachedDongleDeviceManager().f();
        } catch (Exception e) {
            OooO00o.OooO00o.OooO0O0.b.f41a.d(TAG, "isSupportHard, error.");
            e.printStackTrace();
            return null;
        }
    }

    private AudioManager getAudioManager() {
        if (this.mAudioManager == null) {
            Application applicationA = OooO00o.OooO00o.OooO0O0.a.f39a.a();
            if (applicationA == null) {
                OooO00o.OooO00o.OooO0O0.b.f41a.d(TAG, "context is null, init first");
                return null;
            }
            if (this.mAudioManager == null) {
                this.mAudioManager = (AudioManager) applicationA.getSystemService("audio");
            }
        }
        return this.mAudioManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BiYaDiAMgr getBiYaDiAMgr() {
        BaseChannelMgr channelMgr = this.realInstance.getChannelMgr();
        if (channelMgr instanceof BiYaDiAMgr) {
            return (BiYaDiAMgr) channelMgr;
        }
        return null;
    }

    private int getDelayTime() {
        Long lValueOf = Long.valueOf(System.currentTimeMillis());
        int i = lValueOf.longValue() - this.currentTimeMillis.longValue() > 220 ? 0 : 160;
        this.currentTimeMillis = lValueOf;
        return i;
    }

    private String getEffectTipTone(int i) {
        return i != 0 ? i != 1 ? i != 2 ? "byd_ys" : "byd_yyt" : "byd_ktv" : "byd_lyp";
    }

    public static synchronized PuremicTuning getInstance() {
        if (instance == null) {
            instance = new PuremicTuning();
        }
        return instance;
    }

    public static boolean isLoostoneMicPhone(Context context) {
        OooO00o.OooO00o.OooO0O0.OooO0o0.OooO00o oooO00o = OooO00o.OooO00o.OooO0O0.OooO0o0.OooO00o.f18a;
        boolean zI = oooO00o.i(context);
        OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
        bVar.e(TAG, "isLoostoneMicPhone() " + zI);
        if (zI) {
            return zI;
        }
        boolean zI2 = oooO00o.i(context);
        bVar.e(TAG, "two -- > isLoostoneMicPhone() " + zI2);
        return zI2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$checkDeviceInfo$6, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void a(IUpgradeAndReset.DeviceInfoCallback deviceInfoCallback) {
        OooO00o.OooO00o.OooO0O0.b.f41a.b(TAG, "checkDeviceInfo, callback = " + deviceInfoCallback);
        this.deviceInfoCb = deviceInfoCallback;
        notifyDongleInfo();
        if (getBiYaDiAMgr() != null) {
            getBiYaDiAMgr().setDeviceInfoCallback(deviceInfoCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$checkNewVersion$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void b(IUpgradeAndReset.CheckNewVersionCallback checkNewVersionCallback) {
        OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
        bVar.b(TAG, "checkNewVersion, callback = " + checkNewVersionCallback);
        this.checkNewVersionCb = checkNewVersionCallback;
        if (this.mRealUpgrading) {
            bVar.b(TAG, "checkNewVersion, mRealUpgrading!");
            return;
        }
        final HashMap map = new HashMap();
        DongleUpdateUtil.getInstance().checkUpgrade(new IUpgradeAndReset.CheckNewVersionCallback() { // from class: com.loostone.libtuning.channel.byd.old.PuremicTuning.8
            @Override // com.loostone.libtuning.channel.byd.upgrade.inf.IUpgradeAndReset.CheckNewVersionCallback
            public void onNewVersionAvailable(String str) {
                OooO00o.OooO00o.OooO0O0.b.f41a.b(PuremicTuning.TAG, "DongleUpdateUtil onNewVersionAvailable");
                map.put("DGL", str);
                PuremicTuning.this.notifyNewVersion(map);
            }

            @Override // com.loostone.libtuning.channel.byd.upgrade.inf.IUpgradeAndReset.CheckNewVersionCallback
            public void onUpToDate() {
                OooO00o.OooO00o.OooO0O0.b.f41a.b(PuremicTuning.TAG, "DongleUpdateUtil onUpToDate");
                map.put("DGL", BuildConfig.FLAVOR);
                PuremicTuning.this.notifyNewVersion(map);
            }
        });
        MicUpdateUtil.getInstance().checkUpgrade(new IUpgradeAndReset.CheckNewVersionCallback() { // from class: com.loostone.libtuning.channel.byd.old.PuremicTuning.9
            @Override // com.loostone.libtuning.channel.byd.upgrade.inf.IUpgradeAndReset.CheckNewVersionCallback
            public void onNewVersionAvailable(String str) {
                OooO00o.OooO00o.OooO0O0.b.f41a.b(PuremicTuning.TAG, "MicUpdateUtil onNewVersionAvailable");
                map.put("MIC", str);
                PuremicTuning.this.notifyNewVersion(map);
            }

            @Override // com.loostone.libtuning.channel.byd.upgrade.inf.IUpgradeAndReset.CheckNewVersionCallback
            public void onUpToDate() {
                OooO00o.OooO00o.OooO0O0.b.f41a.b(PuremicTuning.TAG, "MicUpdateUtil onUpToDate");
                map.put("MIC", BuildConfig.FLAVOR);
                PuremicTuning.this.notifyNewVersion(map);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$checkUpgradePrecondition$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void c(IUpgradeAndReset.UpgradePreconditionCallback upgradePreconditionCallback) {
        OooO00o.OooO00o.OooO0O0.b.f41a.b(TAG, "checkUpgradePrecondition, callback = " + upgradePreconditionCallback);
        this.upgradePreconditionCb = upgradePreconditionCallback;
        notifyUpgradePrecondition();
        if (getBiYaDiAMgr() != null) {
            getBiYaDiAMgr().setUpgradePreconditionCallback(upgradePreconditionCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$notifyNewVersion$8, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void d(Map map) {
        StringBuilder sb = new StringBuilder();
        String str = (String) map.get("DGL");
        String str2 = (String) map.get("MIC");
        sb.append(TextUtils.isEmpty(str) ? BuildConfig.FLAVOR : str);
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            sb.append("\n");
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = BuildConfig.FLAVOR;
        }
        sb.append(str2);
        if (TextUtils.isEmpty(sb)) {
            this.checkNewVersionCb.onUpToDate();
        } else {
            this.checkNewVersionCb.onNewVersionAvailable(sb.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$notifyUpgrade$7, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void e(int i, int i2, String str) {
        if (i == 4) {
            int i3 = this.mProcess + 100;
            this.mProcess = i3;
            if (i3 == i2) {
                if (this.mRealUpgrading) {
                    this.mRealUpgrading = false;
                    if (DongleUpdateUtil.getInstance().getUpgradeList().isEmpty()) {
                        this.upgradeCb.onUpgradeProgress(100);
                        this.upgradeCb.onUpgradeSuccess();
                        this.mProcess = 0;
                    } else {
                        DongleUpdateUtil.getInstance().rebootAndRelease();
                    }
                }
                DongleUpdateUtil.getInstance().clearUpgradeList();
                MicUpdateUtil.getInstance().clearUpgradeList();
                return;
            }
            return;
        }
        if (i == 5) {
            this.upgradeCb.onUpgradeFailed(str, BuildConfig.FLAVOR);
            if (this.mRealUpgrading) {
                this.mRealUpgrading = false;
                DongleUpdateUtil.getInstance().rebootAndRelease(false);
                DongleUpdateUtil.getInstance().clearUpgradeList();
                MicUpdateUtil.getInstance().clearUpgradeList();
                return;
            }
            return;
        }
        if (i == 6) {
            int iRound = (int) Math.round((((double) (Integer.parseInt(str) + this.mProcess)) * 100.0d) / ((double) i2));
            if (iRound == 100) {
                iRound = 99;
            }
            this.upgradeCb.onUpgradeProgress(iRound);
            return;
        }
        OooO00o.OooO00o.OooO0O0.b.f41a.d(TAG, "notifyUpgrade upgrade type :: " + i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$notifyUpgradePrecondition$9, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void f(boolean z, boolean z2) {
        this.upgradePreconditionCb.onMicConnectToVehicleStateChange(z);
        this.upgradePreconditionCb.onMicMeetConditionChange(z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$setScreenLiftState$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void g(boolean z) {
        this.realInstance.getDongleManager().setScreenLiftState(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$upgrade$5, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void j(IUpgradeAndReset.UpgradeCallback upgradeCallback) {
        OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
        bVar.b(TAG, "upgrade, callback = " + upgradeCallback);
        this.mProcess = 0;
        this.upgradeCb = upgradeCallback;
        if (this.mRealUpgrading) {
            bVar.b(TAG, "upgrade, mRealUpgrading!");
            return;
        }
        ArrayList<Map<String, Object>> upgradeList = DongleUpdateUtil.getInstance().getUpgradeList();
        ArrayList<Map<String, Object>> upgradeList2 = MicUpdateUtil.getInstance().getUpgradeList();
        final int i = upgradeList.size() > 0 ? 100 : 0;
        if (upgradeList2.size() > 0) {
            i += 200;
        }
        if (i <= 0) {
            lambda$upgrade$4(5, "需要先执行版本检测", 0);
            return;
        }
        this.mRealUpgrading = true;
        if (upgradeList.size() > 0) {
            Map<String, Object> map = upgradeList.get(0);
            DongleUpdateUtil.getInstance().startUpdate(((Long) map.get("version")).longValue(), (String) map.get("filepath"), new IDongleUpgrade() { // from class: com.loostone.libtuning.channel.byd.old.e
                @Override // com.loostone.libtuning.channel.byd.old.IDongleUpgrade
                public final void onDongleUpdateStatus(int i2, String str) {
                    this.f3843a.h(i, i2, str);
                }
            });
        }
        if (upgradeList2.size() > 0) {
            Map<String, Object> map2 = upgradeList2.get(0);
            MicUpdateUtil.getInstance().startUpdate(((Long) map2.get("version")).longValue(), (String) map2.get("filepath"), new IDongleUpgrade() { // from class: com.loostone.libtuning.channel.byd.old.l
                @Override // com.loostone.libtuning.channel.byd.old.IDongleUpgrade
                public final void onDongleUpdateStatus(int i2, String str) {
                    this.f3859a.i(i, i2, str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDongleInfo() {
        OooO00o.OooO00o.OooO0O0.b.f41a.b(TAG, "notifyDongleInfo");
        if (this.deviceInfoCb != null) {
            w.d<String> dVar = this.notifyDongleInfoTask;
            if (dVar != null) {
                dVar.cancel();
            }
            AnonymousClass10 anonymousClass10 = new AnonymousClass10(null, new StringBuilder(), new StringBuilder());
            this.notifyDongleInfoTask = anonymousClass10;
            ThreadUtils.h(anonymousClass10);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyNewVersion(final Map<String, String> map) {
        OooO00o.OooO00o.OooO0O0.b.f41a.b(TAG, "notifyNewVersion, versionInfo size = " + map.size());
        if (this.checkNewVersionCb != null && map.containsKey("DGL") && map.containsKey("MIC")) {
            ThreadUtils.q(new Runnable() { // from class: com.loostone.libtuning.channel.byd.old.g
                @Override // java.lang.Runnable
                public final void run() {
                    this.f3847b.d(map);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: notifyUpgrade, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public void i(final int i, final String str, final int i2) {
        if (this.upgradeCb != null) {
            ThreadUtils.q(new Runnable() { // from class: com.loostone.libtuning.channel.byd.old.h
                @Override // java.lang.Runnable
                public final void run() {
                    this.f3849b.e(i, i2, str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyUpgradePrecondition() {
        OooO00o.OooO00o.OooO0O0.b.f41a.b(TAG, "notifyUpgradePrecondition");
        if (this.upgradePreconditionCb != null) {
            final boolean zHasRealNewVersion = micP1ConnectStatus ? MicUpdateUtil.getInstance().hasRealNewVersion() : false;
            final boolean z = getMicBatteryVoltage(1).doubleValue() >= 3.7d || getMicBatteryVoltage(2).doubleValue() >= 3.7d;
            ThreadUtils.q(new Runnable() { // from class: com.loostone.libtuning.channel.byd.old.k
                @Override // java.lang.Runnable
                public final void run() {
                    this.f3856b.f(zHasRealNewVersion, z);
                }
            });
            checkNewVersion(this.checkNewVersionCb);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFixGainEffect() {
        BYDCache bYDCache = BYDCache.INSTANCE;
        int iIntValue = bYDCache.getReverbVolume().a() == null ? 1 : bYDCache.getReverbVolume().a().intValue();
        int iIntValue2 = bYDCache.getKaraokeEffect().a() != null ? bYDCache.getKaraokeEffect().a().intValue() : 1;
        IVolumeAdjustment iVolumeAdjustment = this.volumeAdjustment;
        if (iVolumeAdjustment != null) {
            iVolumeAdjustment.setAef2ReverbVolume(iIntValue);
            this.volumeAdjustment.setEnvironmentalSound(iIntValue2, bYDCache.getReverbVolume().a() == null ? 1.0d : ((double) bYDCache.getReverbVolume().a().intValue()) / 5.0d);
        }
    }

    public static void setNeedListenSystemVolume(boolean z) {
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "setNeedListenSystemVolume: " + z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String splicingMicInfo() {
        OooO00o.OooO00o.OooO0O0.b.f41a.b(TAG, "splicingMicInfo");
        Map<Integer, byte[]> mapQueryMicInfoAll = DongleUpdateUtil.getInstance().queryMicInfoAll(true);
        if (mapQueryMicInfoAll == null || mapQueryMicInfoAll.isEmpty() || mapQueryMicInfoAll.size() < 15) {
            return "无版本信息";
        }
        StringBuilder sb = new StringBuilder();
        if (isMicConnected(1)) {
            sb.append("A");
            sb.append("麦克风名称");
            sb.append(DongleUpdateUtil.getInstance().queryMicInfo(14));
            sb.append("\n");
            sb.append("VendorID: ");
            sb.append(DongleUpdateUtil.getInstance().queryMicInfo(10));
            sb.append("\n");
            sb.append("固件版本: ");
            sb.append(DongleUpdateUtil.getInstance().queryMicInfo(6));
            sb.append("\n");
            sb.append("序列号: ");
            sb.append(DongleUpdateUtil.getInstance().queryMicInfo(12));
        }
        if (isMicConnected(2)) {
            if (sb.length() > 0) {
                sb.append("\n");
            }
            sb.append("B");
            sb.append("麦克风名称");
            sb.append(DongleUpdateUtil.getInstance().queryMicInfo(15));
            sb.append("\n");
            sb.append("VendorID: ");
            sb.append(DongleUpdateUtil.getInstance().queryMicInfo(11));
            sb.append("\n");
            sb.append("固件版本: ");
            sb.append(DongleUpdateUtil.getInstance().queryMicInfo(7));
            sb.append("\n");
            sb.append("序列号: ");
            sb.append(DongleUpdateUtil.getInstance().queryMicInfo(13));
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override // com.loostone.libtuning.channel.byd.old.InsideDongle
    public int cancelPair(int i) {
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "cancelPair: " + i);
        IMicControl micControl = this.realInstance.getChannelMgr().getMicService().getMicControl();
        if (micControl != null) {
            return micControl.cancelPair(i);
        }
        return -1;
    }

    @Override // com.loostone.libtuning.channel.byd.upgrade.inf.IUpgradeAndReset
    public void checkDeviceInfo(final IUpgradeAndReset.DeviceInfoCallback deviceInfoCallback) {
        ThreadUtils.l().execute(new Runnable() { // from class: com.loostone.libtuning.channel.byd.old.b
            @Override // java.lang.Runnable
            public final void run() {
                this.f3838b.a(deviceInfoCallback);
            }
        });
    }

    @Override // com.loostone.libtuning.channel.byd.upgrade.inf.IUpgradeAndReset
    public void checkNewVersion(final IUpgradeAndReset.CheckNewVersionCallback checkNewVersionCallback) {
        ThreadUtils.l().execute(new Runnable() { // from class: com.loostone.libtuning.channel.byd.old.f
            @Override // java.lang.Runnable
            public final void run() {
                this.f3845b.b(checkNewVersionCallback);
            }
        });
    }

    @Override // com.loostone.libtuning.channel.byd.upgrade.inf.IUpgradeAndReset
    public void checkUpgradePrecondition(final IUpgradeAndReset.UpgradePreconditionCallback upgradePreconditionCallback) {
        ThreadUtils.l().execute(new Runnable() { // from class: com.loostone.libtuning.channel.byd.old.i
            @Override // java.lang.Runnable
            public final void run() {
                this.f3852b.c(upgradePreconditionCallback);
            }
        });
    }

    @Override // com.loostone.libtuning.channel.byd.old.InsideDongle
    public int confirmPair(int i) {
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "confirmPair: " + i);
        IMicControl micControl = this.realInstance.getChannelMgr().getMicService().getMicControl();
        if (micControl != null) {
            return micControl.confirmPair(i);
        }
        return -1;
    }

    public void deinit() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "deInit()");
        synchronized (PuremicTuning.class) {
            if (isInit) {
                this.realInstance.getDongleManager().unRegisterDongleListener(this.dongleListener);
                this.realInstance.release();
                this.localDongleListener = null;
                this.volumeAdjustment = null;
                this.musicFFTControl = null;
                this.audioTempoProcess = null;
            }
            this.mSystemPropertiesMap.clear();
            this.upgradePreconditionCb = null;
            this.checkNewVersionCb = null;
            this.upgradeCb = null;
            this.deviceInfoCb = null;
            this.mRealUpgrading = false;
            isInit = false;
        }
    }

    @Override // com.loostone.libtuning.channel.byd.old.InsideDongle
    public int enterPair(int i) {
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "enterPair: " + i);
        IMicControl micControl = this.realInstance.getChannelMgr().getMicService().getMicControl();
        if (micControl != null) {
            return micControl.enterPair(i);
        }
        return -1;
    }

    public int getMicBattery(int i) {
        IMicControl micControl = this.realInstance.getChannelMgr().getMicService().getMicControl();
        if (micControl != null) {
            return micControl.getMicBatteryPercentage(i);
        }
        return -1;
    }

    public Double getMicBatteryVoltage(int i) {
        IMicControl micControl = this.realInstance.getChannelMgr().getMicService().getMicControl();
        return micControl != null ? Double.valueOf(micControl.getMicBattery(i)) : Double.valueOf(1.0d);
    }

    public boolean getMicConnectionState() {
        return isMicConnected(1) || isMicConnected(2);
    }

    public String getMicSupplier(int i) {
        String strQueryMicInfo = i == 1 ? DongleUpdateUtil.getInstance().queryMicInfo(10) : i == 2 ? DongleUpdateUtil.getInstance().queryMicInfo(11) : BuildConfig.FLAVOR;
        return !TextUtils.isEmpty(strQueryMicInfo) ? convertMicSupplier(strQueryMicInfo) : BuildConfig.FLAVOR;
    }

    public IAudioFFTProcess getMusicAudioFFTControl() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "getMusicAudioFFTControl()");
        return this.musicFFTControl;
    }

    public IAudioTempoProcess getMusicAudioTempoControl() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "getMusicAudioTempoControl()");
        return this.audioTempoProcess;
    }

    public double getMusicCurveVolume() {
        OooOO0 oooOO0 = OooOO0.f490a;
        int i = oooOO0.i();
        int iJ = oooOO0.j();
        double d2 = 1.0d;
        if (iJ > 0 && i <= iJ) {
            d2 = (((double) i) * 1.0d) / ((double) iJ);
        }
        return getMusicCurveVolume(d2);
    }

    @Override // com.loostone.libtuning.channel.byd.old.InsideDongle
    public int getPairState(int i) {
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "getPairState: " + i);
        int pairState = this.realInstance.getChannelMgr().getMicService().getPairState(i - 1);
        OooO00o oooO00oF = this.realInstance.getDongleManager().getMCachedDongleDeviceManager().f();
        if (oooO00oF != null && oooO00oF.z() && (pairState == 4 || pairState == 11)) {
            return 6;
        }
        if (pairState == 1) {
            return 3;
        }
        if (pairState == 8) {
            return 2;
        }
        if (pairState == 3) {
            return 4;
        }
        if (pairState != 4) {
            if (pairState == 10) {
                return 5;
            }
            if (pairState != 11) {
                return -1;
            }
        }
        return 1;
    }

    @Override // com.loostone.libtuning.channel.byd.upgrade.inf.IUpgradeAndReset
    public String getSdkVersion() {
        OooO00o.OooO00o.OooO0O0.b.f41a.b(TAG, "getSdkVersion");
        String version = getVersion(1);
        String strF = OooO0O0.f531a.f();
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(version)) {
            sb.append("SDK:");
            sb.append(version);
        }
        if (!TextUtils.isEmpty(strF)) {
            sb.append("\n");
            sb.append("SO:");
            sb.append(strF);
        }
        return sb.toString();
    }

    @Override // com.loostone.libtuning.channel.byd.old.InsideDongle
    public String getVersion(int i) {
        IMicControl micControl = this.realInstance.getChannelMgr().getMicService().getMicControl();
        if (i == 1) {
            return com.loostone.libtuning.BuildConfig.VERSION_NAME;
        }
        switch (i) {
            case 11:
                return micControl != null ? micControl.getFirmwareVersion((byte) 1) : BuildConfig.FLAVOR;
            case 12:
                return micControl != null ? micControl.getFirmwareVersion((byte) 2) : BuildConfig.FLAVOR;
            case 13:
                return micControl != null ? micControl.getFirmwareVersion((byte) 3) : BuildConfig.FLAVOR;
            default:
                switch (i) {
                    case 21:
                        return micControl != null ? micControl.getHardwareVersion((byte) 5) : BuildConfig.FLAVOR;
                    case 22:
                        return micControl != null ? micControl.getHardwareVersion((byte) 1) : BuildConfig.FLAVOR;
                    case 23:
                        return micControl != null ? micControl.getHardwareVersion((byte) 2) : BuildConfig.FLAVOR;
                    default:
                        return BuildConfig.FLAVOR;
                }
        }
    }

    public boolean getVocalRemoveStatus() {
        OooO00o.OooO00o.OooO0O0.b.f41a.b(TAG, "getVocalRemoveStatus");
        return getVocalRemoveValue() > 30;
    }

    public int getVocalRemoveValue() {
        OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
        bVar.b(TAG, "getVocalRemoveValue");
        if (getAudioManager() != null) {
            String strReplace = getAudioManager().getParameters("pm_music_vocal_remove_status").replace("pm_music_vocal_remove_status=", BuildConfig.FLAVOR);
            bVar.d(TAG, "vocalRemoveValue = " + strReplace);
            if (!TextUtils.isEmpty(strReplace)) {
                try {
                    return Integer.parseInt(strReplace);
                } catch (Exception e) {
                    OooO00o.OooO00o.OooO0O0.b.f41a.d(TAG, "getVocalRemoveValue, parseInt error");
                    e.printStackTrace();
                }
            }
        }
        return -3;
    }

    public void init(Application application, int i) {
        init(application, i, 0);
    }

    public boolean isHardEffect() {
        IMicControl micControl = this.realInstance.getChannelMgr().getMicService().getMicControl();
        if (micControl != null) {
            return micControl.isHardEffect();
        }
        return false;
    }

    @Override // com.loostone.libtuning.channel.byd.old.InsideDongle
    public boolean isInsideMic() {
        boolean zB = this.realInstance.getDongleManager().getMEventManager().b();
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "isInsideMic: " + zB);
        return zB;
    }

    public boolean isMicCarBrandMatch() {
        try {
            LocalDongleManager dongleManager = this.realInstance.getDongleManager();
            OooO00o oooO00oF = dongleManager.getMCachedDongleDeviceManager().f();
            if (oooO00oF == null) {
                OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "isMicCarBrandMatch: false. No active device!");
                return false;
            }
            boolean zM = dongleManager.getMEventManager().m(oooO00oF.t());
            OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "isMicCarBrandMatch: " + zM);
            return zM;
        } catch (Exception e) {
            OooO00o.OooO00o.OooO0O0.b.f41a.d(TAG, "isMicCarBrandMatch, error.");
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.loostone.libtuning.channel.byd.old.InsideDongle
    public boolean isMicConnected(int i) {
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "isMicConnected: " + i);
        return this.realInstance.getChannelMgr().getMicService().getPairState(i - 1) == 3;
    }

    @Override // com.loostone.libtuning.channel.byd.old.InsideDongle
    public boolean isPremiumSpeakerConnected() {
        IMicControl micControl = this.realInstance.getChannelMgr().getMicService().getMicControl();
        if (micControl == null) {
            return false;
        }
        boolean premiumSpeakerState = micControl.getPremiumSpeakerState();
        OooO00o.OooO00o.OooO0O0.b.f41a.b(TAG, "isPremiumSpeakerConnected, status = " + premiumSpeakerState);
        return premiumSpeakerState;
    }

    @Override // com.loostone.libtuning.channel.byd.old.InsideDongle
    public int rebootDongle() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "rebootDongle: ");
        IMicControl micControl = this.realInstance.getChannelMgr().getMicService().getMicControl();
        if (micControl != null) {
            return micControl.rebootDongle();
        }
        return -1;
    }

    @Override // com.loostone.libtuning.channel.byd.old.InsideDongle
    public int rejectPair(int i) {
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "rejectPair: " + i);
        IMicControl micControl = this.realInstance.getChannelMgr().getMicService().getMicControl();
        if (micControl != null) {
            return micControl.rejectPair(i);
        }
        return -1;
    }

    @Override // com.loostone.libtuning.channel.byd.old.InsideDongle
    public int resetDongle() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "resetDongle: ");
        IMicControl micControl = this.realInstance.getChannelMgr().getMicService().getMicControl();
        if (micControl != null) {
            return micControl.resetDongle();
        }
        return -1;
    }

    @Override // com.loostone.libtuning.channel.byd.upgrade.inf.IUpgradeAndReset
    public void resetFactory(final IUpgradeAndReset.ResetFactoryCallback resetFactoryCallback) {
        OooO00o.OooO00o.OooO0O0.b.f41a.b(TAG, "resetFactory, callback = " + resetFactoryCallback);
        resetDongle();
        if (resetFactoryCallback != null) {
            ThreadUtils.q(new Runnable() { // from class: com.loostone.libtuning.channel.byd.old.m
                @Override // java.lang.Runnable
                public final void run() {
                    resetFactoryCallback.onResetFactorySuccess();
                }
            });
        }
    }

    @Override // com.loostone.libtuning.channel.byd.old.InsideDongle
    public int resetPair(int i) {
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "resetPair: " + i);
        IMicControl micControl = this.realInstance.getChannelMgr().getMicService().getMicControl();
        if (micControl != null) {
            return micControl.resetPair(i);
        }
        return -1;
    }

    public void setDongleListener(IDongleListener iDongleListener) {
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "setDongleListener()");
        this.localDongleListener = iDongleListener;
        if (getBiYaDiAMgr() != null) {
            getBiYaDiAMgr().setDongleListener(this.localDongleListener);
        }
    }

    public void setKaraokeEffect(int i) {
        setKaraokeEffect(i, false);
    }

    public void setMicPercentage(final int i) {
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "setMicPercentage(" + i + ")");
        w.d<String> dVar = this.micPercentageTask;
        if (dVar != null) {
            ThreadUtils.d(dVar);
        }
        w.d<String> dVar2 = new w.d<String>(null) { // from class: com.loostone.libtuning.channel.byd.old.PuremicTuning.3
            @Override // com.blankj.utilcode.util.ThreadUtils.e
            public String doInBackground() {
                OooO00o activeDongle = PuremicTuning.this.getActiveDongle();
                if (activeDongle == null) {
                    if (PuremicTuning.this.getBiYaDiAMgr() != null) {
                        PuremicTuning.this.getBiYaDiAMgr().setMicPercentage(i, false);
                    }
                    if (PuremicTuning.this.volumeAdjustment == null) {
                        return "setMicPercentage";
                    }
                    PuremicTuning.this.volumeAdjustment.setMicVolume(i);
                    return "setMicPercentage";
                }
                if (activeDongle.B()) {
                    if (PuremicTuning.this.getBiYaDiAMgr() == null) {
                        return "setMicPercentage";
                    }
                    PuremicTuning.this.getBiYaDiAMgr().setMicPercentage(i, false);
                    return "setMicPercentage";
                }
                if (PuremicTuning.this.volumeAdjustment == null) {
                    return "setMicPercentage";
                }
                PuremicTuning.this.volumeAdjustment.setMicVolume(i);
                return "setMicPercentage";
            }
        };
        this.micPercentageTask = dVar2;
        ThreadUtils.g(6, dVar2, getDelayTime(), TimeUnit.MILLISECONDS);
    }

    @Override // com.loostone.libtuning.channel.byd.old.InsideDongle
    public int setRejectPairTime(int i) {
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "setRejectPairTime: " + i);
        IMicControl micControl = this.realInstance.getChannelMgr().getMicService().getMicControl();
        if (micControl != null) {
            return micControl.setRejectPairTime(i);
        }
        return -1;
    }

    public void setReverbPercentage(final int i) {
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "setReverbPercentage(" + i + ")");
        w.d<String> dVar = this.rvbPercentageTask;
        if (dVar != null) {
            ThreadUtils.d(dVar);
        }
        w.d<String> dVar2 = new w.d<String>(null) { // from class: com.loostone.libtuning.channel.byd.old.PuremicTuning.4
            @Override // com.blankj.utilcode.util.ThreadUtils.e
            public String doInBackground() {
                BYDCache.INSTANCE.getReverbVolume().e(Integer.valueOf(i / 10));
                OooO00o activeDongle = PuremicTuning.this.getActiveDongle();
                if (activeDongle == null) {
                    if (PuremicTuning.this.getBiYaDiAMgr() != null) {
                        PuremicTuning.this.getBiYaDiAMgr().setReverbPercentage(i, false);
                    }
                    PuremicTuning.this.setFixGainEffect();
                    return "setReverbPercentage";
                }
                if (!activeDongle.B()) {
                    PuremicTuning.this.setFixGainEffect();
                    return "setReverbPercentage";
                }
                if (PuremicTuning.this.getBiYaDiAMgr() == null) {
                    return "setReverbPercentage";
                }
                PuremicTuning.this.getBiYaDiAMgr().setReverbPercentage(i, false);
                return "setReverbPercentage";
            }
        };
        this.rvbPercentageTask = dVar2;
        ThreadUtils.g(6, dVar2, getDelayTime(), TimeUnit.MILLISECONDS);
    }

    public void setScreenLiftState(final boolean z) {
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "setScreenLiftState, rise = " + z);
        this.mSystemPropertiesMap.put(BaseDefine.SystemPropertiesKey.SCREEN_LIFT_STATE, String.valueOf(z));
        if (isInit) {
            try {
                ThreadUtils.l().execute(new Runnable() { // from class: com.loostone.libtuning.channel.byd.old.c
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f3840b.g(z);
                    }
                });
            } catch (Exception e) {
                OooO00o.OooO00o.OooO0O0.b.f41a.d(TAG, "setTVLiftRiseState, error.");
                e.printStackTrace();
            }
        }
    }

    public void setStreamVolumeAndMax(int i, int i2) {
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "setStreamVolumeAndMax{streamVolume='" + i + "', streamMaxVolume='" + i2 + "'}");
    }

    public void setSystemPercentage(final int i) {
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "setSystemPercentage(" + i + ")");
        w.d<String> dVar = this.systemPercentageTask;
        if (dVar != null) {
            ThreadUtils.d(dVar);
        }
        w.d<String> dVar2 = new w.d<String>(null) { // from class: com.loostone.libtuning.channel.byd.old.PuremicTuning.5
            @Override // com.blankj.utilcode.util.ThreadUtils.e
            public String doInBackground() {
                if (PuremicTuning.this.getBiYaDiAMgr() == null) {
                    return "setSystemPercentage";
                }
                PuremicTuning.this.getBiYaDiAMgr().setSystemPercentage(i, false);
                return "setSystemPercentage";
            }
        };
        this.systemPercentageTask = dVar2;
        ThreadUtils.g(6, dVar2, getDelayTime(), TimeUnit.MILLISECONDS);
    }

    public void setSystemProperties(Map<String, String> map) {
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "setSystemProperties");
        if (map != null && !map.isEmpty()) {
            this.mSystemPropertiesMap.putAll(map);
        }
        if (isInit) {
            OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0O0.f354a.k(this.mSystemPropertiesMap);
        }
    }

    public void switchVocalRemove(boolean z) {
        OooO00o.OooO00o.OooO0O0.b.f41a.b(TAG, "switchVocalRemove, onOff = " + z);
        if (getAudioManager() != null) {
            if (getBiYaDiAMgr() != null) {
                getBiYaDiAMgr().switchVocalRemoveState(z);
            }
            getAudioManager().setParameters("pm_music={\"vocal_remove_onoff\":" + (z ? 1 : 0) + "}");
        }
    }

    @Override // com.loostone.libtuning.channel.byd.upgrade.inf.IUpgradeAndReset
    public void upgrade(final IUpgradeAndReset.UpgradeCallback upgradeCallback) {
        ThreadUtils.l().execute(new Runnable() { // from class: com.loostone.libtuning.channel.byd.old.j
            @Override // java.lang.Runnable
            public final void run() {
                this.f3854b.j(upgradeCallback);
            }
        });
    }

    public void init(Application application, int i, int i2) {
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "Car Type: " + i + ", speakerNum:" + i2);
        ArrayMap<String, String> arrayMap = new ArrayMap<>();
        arrayMap.put(BaseDefine.SystemPropertiesKey.CAR_TYPE_CODE, String.valueOf(i));
        if (i2 > 0) {
            arrayMap.put(BaseDefine.SystemPropertiesKey.AMPLIFIER_COUNT, String.valueOf(i2));
        }
        init(application, arrayMap);
    }

    public void setKaraokeEffect(final int i, boolean z) {
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "setKaraokeEffect(" + i + ") " + z);
        if (z) {
            OooO0OO.f24a.d(getEffectTipTone(i), 0.5f);
        }
        w.d<String> dVar = this.karaokeEffectTask;
        if (dVar != null) {
            ThreadUtils.d(dVar);
        }
        w.d<String> dVar2 = new w.d<String>(null) { // from class: com.loostone.libtuning.channel.byd.old.PuremicTuning.6
            @Override // com.blankj.utilcode.util.ThreadUtils.e
            public String doInBackground() {
                BYDCache.INSTANCE.getKaraokeEffect().e(Integer.valueOf(i));
                OooO00o activeDongle = PuremicTuning.this.getActiveDongle();
                if (activeDongle == null) {
                    if (PuremicTuning.this.getBiYaDiAMgr() != null) {
                        PuremicTuning.this.getBiYaDiAMgr().setKaraokeEffect(i, false);
                    }
                    PuremicTuning.this.setFixGainEffect();
                    return "setKaraokeEffect";
                }
                if (!activeDongle.B()) {
                    PuremicTuning.this.setFixGainEffect();
                    return "setKaraokeEffect";
                }
                if (PuremicTuning.this.getBiYaDiAMgr() == null) {
                    return "setKaraokeEffect";
                }
                PuremicTuning.this.getBiYaDiAMgr().setKaraokeEffect(i, false);
                return "setKaraokeEffect";
            }
        };
        this.karaokeEffectTask = dVar2;
        ThreadUtils.g(6, dVar2, getDelayTime(), TimeUnit.MILLISECONDS);
    }

    public double getMusicCurveVolume(double d2) {
        return getMusicCurveVolume(100, 100, d2);
    }

    public double getMusicCurveVolume(int i, int i2) {
        OooOO0 oooOO0 = OooOO0.f490a;
        int i3 = oooOO0.i();
        int iJ = oooOO0.j();
        double d2 = 1.0d;
        if (iJ > 0 && i3 <= iJ) {
            d2 = (((double) i3) * 1.0d) / ((double) iJ);
        }
        return getMusicCurveVolume(i, i2, d2);
    }

    public void init(Application application, ArrayMap<String, String> arrayMap) {
        if (arrayMap != null && !arrayMap.isEmpty()) {
            this.mSystemPropertiesMap.putAll((ArrayMap<? extends String, ? extends String>) arrayMap);
        }
        synchronized (PuremicTuning.class) {
            OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
            bVar.b(TAG, " will init, flag = " + isInit);
            if (!isInit) {
                this.localDongleListener = null;
                this.realInstance.init(application, 400000L, this.mSystemPropertiesMap);
                this.volumeAdjustment = this.realInstance.getVolumeAdjustment();
                this.realInstance.getDongleManager().registerDongleListener(this.dongleListener);
                this.musicFFTControl = new IAudioFFTProcess() { // from class: com.loostone.libtuning.channel.byd.old.PuremicTuning.1
                    @Override // com.loostone.libtuning.inf.extern.IAudioFFTProcess
                    public void pause() {
                    }

                    @Override // com.loostone.libtuning.inf.extern.IAudioFFTProcess
                    public void registerFFTReadListener(IFFTReadListener iFFTReadListener) {
                    }

                    @Override // com.loostone.libtuning.inf.extern.IAudioFFTProcess
                    public void release() {
                    }

                    @Override // com.loostone.libtuning.inf.extern.IAudioFFTProcess
                    public void resume() {
                    }

                    @Override // com.loostone.libtuning.inf.extern.IAudioFFTProcess
                    public void start() {
                    }

                    @Override // com.loostone.libtuning.inf.extern.IAudioFFTProcess
                    public void stop() {
                    }

                    @Override // com.loostone.libtuning.inf.extern.IAudioFFTProcess
                    public void unRegisterFFTReadListener() {
                    }
                };
                this.audioTempoProcess = new IAudioTempoProcess() { // from class: com.loostone.libtuning.channel.byd.old.PuremicTuning.2
                    @Override // com.loostone.libtuning.inf.extern.IAudioTempoProcess
                    public void deinit() {
                    }

                    @Override // com.loostone.libtuning.inf.extern.IAudioTempoProcess
                    public int getBrightness() {
                        return 0;
                    }

                    @Override // com.loostone.libtuning.inf.extern.IAudioTempoProcess
                    public int getColor() {
                        return 0;
                    }

                    @Override // com.loostone.libtuning.inf.extern.IAudioTempoProcess
                    public boolean getColorChangeOrNot() {
                        return false;
                    }

                    @Override // com.loostone.libtuning.inf.extern.IAudioTempoProcess
                    public void init() {
                    }

                    @Override // com.loostone.libtuning.inf.extern.IAudioTempoProcess
                    public void initSong() {
                    }

                    @Override // com.loostone.libtuning.inf.extern.IAudioTempoProcess
                    public void setColors(int[] iArr) {
                    }

                    @Override // com.loostone.libtuning.inf.extern.IAudioTempoProcess
                    public void setListener(ITempoCallBack iTempoCallBack) {
                    }

                    @Override // com.loostone.libtuning.inf.extern.IAudioTempoProcess
                    public void setMode(int i, int i2) {
                    }

                    @Override // com.loostone.libtuning.inf.extern.IAudioTempoProcess
                    public void setSleepTime(int i) {
                    }
                };
                isInit = true;
                bVar.b(TAG, " init complete ");
            }
        }
    }

    public double getMusicCurveVolume(int i, int i2, double d2) {
        return (OooOO0.f490a.a(d2) * ((double) i)) / ((double) i2);
    }
}
