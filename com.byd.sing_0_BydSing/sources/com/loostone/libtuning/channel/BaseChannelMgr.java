package com.loostone.libtuning.channel;

import OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b;
import OooO00o.OooO00o.OooO0Oo.OooO0OO.d.c;
import OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO;
import OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0o;
import OooO00o.OooO00o.OooO0o0.OooO0o0.OooO00o.OooO00o;
import OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0OO.OooO0o0.OooO0O0;
import OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0OO.OooO0o0.OooO0OO;
import a.a.d.c.a;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.media.AudioManager;
import com.android.volley.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loostone.libserver.version2.bean.MicItem;
import com.loostone.libserver.version2.data.config.DefaultMicConfig;
import com.loostone.libtuning.channel.byd.upgrade.util.DongleUpdateUtil;
import com.loostone.libtuning.inf.extern.IBaseKeyService;
import com.loostone.libtuning.inf.extern.IChannelInfo;
import com.loostone.libtuning.inf.extern.IKeyListener;
import com.loostone.libtuning.inf.extern.IMicControl;
import com.loostone.libtuning.inf.extern.IVolumeAdjustment;
import com.loostone.libtuning.process.dongle.LocalDongleManager;
import java.io.File;
import java.util.List;
import java.util.Objects;
import kotlin.a;
import kotlin.d;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseChannelMgr implements IChannelInfo {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "BaseChannelMgr";
    private final a audioManager$delegate;
    private final Context context;
    private final BaseChannelMgr$dongleCallback$1 dongleCallback;
    private final a fullSceneMode$delegate;
    private final a infoReceiver$delegate;
    private final IKeyListener keyListener;
    private final a keyService$delegate;
    private final a localDongleManager$delegate;
    private final b mActivateCallback;
    private final b mFirmwareCallback;
    private final b mJsonCallback;
    private int micStatus;
    private final a networkMgr$delegate;
    private final a tuningDeviceManager$delegate;
    private int verifyStatus;
    private final a volumeAdjustment$delegate;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(f fVar) {
            this();
        }
    }

    public BaseChannelMgr() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Type inference failed for: r2v19, types: [com.loostone.libtuning.channel.BaseChannelMgr$dongleCallback$1] */
    public BaseChannelMgr(Context context) {
        h.d(context, "context");
        this.context = context;
        this.micStatus = 4;
        this.localDongleManager$delegate = d.b(new kotlin.jvm.b.a<LocalDongleManager>() { // from class: com.loostone.libtuning.channel.BaseChannelMgr$localDongleManager$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.b.a
            public final LocalDongleManager invoke() {
                return new LocalDongleManager(this.this$0.context);
            }
        });
        this.tuningDeviceManager$delegate = d.b(new kotlin.jvm.b.a<OooO00o>() { // from class: com.loostone.libtuning.channel.BaseChannelMgr$tuningDeviceManager$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.b.a
            public final OooO00o invoke() {
                return new OooO00o(this.this$0.context);
            }
        });
        this.fullSceneMode$delegate = d.b(new kotlin.jvm.b.a<OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0OO.OooO0o0.a>() { // from class: com.loostone.libtuning.channel.BaseChannelMgr$fullSceneMode$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.b.a
            public final OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0OO.OooO0o0.a invoke() {
                Integer numA = OooO0o.f425a.M().a();
                if (numA != null && numA.intValue() == 2) {
                    OooO00o.OooO00o.OooO0O0.b.f41a.e("BaseChannelMgr", "workMode() ------> ko mode");
                    return new OooO0O0();
                }
                if (numA != null && numA.intValue() == 3) {
                    OooO00o.OooO00o.OooO0O0.b.f41a.e("BaseChannelMgr", "workMode() ------> so&ko mode");
                    return new OooO0OO();
                }
                OooO00o.OooO00o.OooO0O0.b.f41a.e("BaseChannelMgr", "workMode() ------> so mode");
                return new OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0OO.OooO0o0.OooO0o();
            }
        });
        this.volumeAdjustment$delegate = d.b(new kotlin.jvm.b.a<IVolumeAdjustment>() { // from class: com.loostone.libtuning.channel.BaseChannelMgr$volumeAdjustment$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.b.a
            public final IVolumeAdjustment invoke() {
                return this.this$0.getFullSceneMode().b();
            }
        });
        this.keyService$delegate = d.b(new kotlin.jvm.b.a<IBaseKeyService>() { // from class: com.loostone.libtuning.channel.BaseChannelMgr$keyService$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.b.a
            public final IBaseKeyService invoke() {
                Integer numA = OooO0o.f425a.p().a();
                if (numA != null && numA.intValue() == 2) {
                    OooO00o.OooO00o.OooO0O0.b.f41a.e("BaseChannelMgr", "  keyMode KEYBOARD");
                    return new OooO00o.OooO00o.OooO0o0.OooO00o.OooO0O0.OooO0OO();
                }
                OooO00o.OooO00o.OooO0O0.b.f41a.e("BaseChannelMgr", "  keyMode HID");
                return new OooO00o.OooO00o.OooO0o0.OooO00o.OooO0O0.OooO00o(null);
            }
        });
        this.networkMgr$delegate = d.b(new kotlin.jvm.b.a<OooO00o.OooO00o.OooO0Oo.OooO00o>() { // from class: com.loostone.libtuning.channel.BaseChannelMgr$networkMgr$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.b.a
            public final OooO00o.OooO00o.OooO0Oo.OooO00o invoke() {
                return new OooO00o.OooO00o.OooO0Oo.OooO00o(OooO00o.OooO00o.OooO0O0.a.f39a.a());
            }
        });
        this.audioManager$delegate = d.b(new kotlin.jvm.b.a<AudioManager>() { // from class: com.loostone.libtuning.channel.BaseChannelMgr$audioManager$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.b.a
            public final AudioManager invoke() {
                Object systemService = this.this$0.context.getSystemService("audio");
                Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.media.AudioManager");
                return (AudioManager) systemService;
            }
        });
        this.infoReceiver$delegate = d.b(new kotlin.jvm.b.a<OooO00o.OooO00o.OooO0o0.OooO00o.OooO00o.OooO00o>() { // from class: com.loostone.libtuning.channel.BaseChannelMgr$infoReceiver$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.b.a
            public final OooO00o.OooO00o.OooO0o0.OooO00o.OooO00o.OooO00o invoke() {
                return new OooO00o.OooO00o.OooO0o0.OooO00o.OooO00o.OooO00o(OooO00o.OooO00o.OooO0O0.a.f39a.a());
            }
        });
        this.keyListener = new IKeyListener() { // from class: com.loostone.libtuning.channel.BaseChannelMgr$keyListener$1
            @Override // com.loostone.libtuning.inf.extern.IKeyListener
            public void onPairStateChanged(int i, int i2) {
                IKeyListener.DefaultImpls.onPairStateChanged(this, i, i2);
            }

            @Override // com.loostone.libtuning.inf.extern.IKeyListener
            public void onPower(int i, boolean z) {
                this.this$0.dynamicAdjustCompPregain();
                if (i != 0) {
                    return;
                }
                int i2 = 3;
                this.this$0.sendDongleInfo(z ? 3 : 4);
                BaseChannelMgr baseChannelMgr = this.this$0;
                if (z) {
                    baseChannelMgr.getAudioManager().setParameters("GlobalMicLowLatency=enable");
                    OooO00o.OooO00o.OooO0O0.b.f41a.e("BaseChannelMgr", "低延迟开启 ------> GlobalMicLowLatency=enable");
                } else {
                    baseChannelMgr.getAudioManager().setParameters("GlobalMicLowLatency=disable");
                    OooO00o.OooO00o.OooO0O0.b.f41a.e("BaseChannelMgr", "低延迟关闭 ------> GlobalMicLowLatency=disable");
                    i2 = 4;
                }
                baseChannelMgr.setMicStatus(i2);
            }

            @Override // com.loostone.libtuning.inf.extern.IKeyListener
            public void onTVPhoneSwitch(int i, boolean z) {
            }

            @Override // com.loostone.libtuning.inf.extern.IKeyListener
            public void onVoice(int i, boolean z) {
            }

            @Override // com.loostone.libtuning.inf.extern.IKeyListener
            public void onVolumeDown(int i, boolean z) {
            }

            @Override // com.loostone.libtuning.inf.extern.IKeyListener
            public void onVolumeUp(int i, boolean z) {
            }
        };
        this.dongleCallback = new a.a.d.c.a() { // from class: com.loostone.libtuning.channel.BaseChannelMgr$dongleCallback$1
            @Override // a.a.d.c.a
            public void onDeviceAddedWithHid(OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO00o oooO00o) {
                h.d(oooO00o, "cachedDevice");
                this.this$0.verifyStatus = 2;
                this.this$0.sendDongleInfo(2);
            }

            @Override // a.a.d.c.a
            public void onDeviceDeniedByHid(OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO00o oooO00o) {
                h.d(oooO00o, "cachedDevice");
            }

            @Override // a.a.d.c.a
            public void onDeviceRemoved(OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO00o oooO00o) {
                h.d(oooO00o, "cachedDevice");
                this.this$0.verifyStatus = 0;
                this.this$0.sendDongleInfo(0);
            }

            @Override // a.a.d.c.a
            public void onDonglePermissionDenied() {
                this.this$0.verifyStatus = 0;
            }

            @Override // a.a.d.c.a
            public void onDonglePermissionGranted(UsbDevice usbDevice) {
                h.d(usbDevice, "usbDevice");
            }

            @Override // a.a.d.c.a
            public void onMicConnectState(UsbDevice usbDevice, boolean z) {
                a.C0027a.a(this, usbDevice, z);
            }
        };
        this.mActivateCallback = new b() { // from class: com.loostone.libtuning.channel.BaseChannelMgr$mActivateCallback$1
            @Override // OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b
            public void onError(String str) {
                this.this$0.verifyStatus = 1;
                this.this$0.getKeyService().stop();
                this.this$0.stopKaraoke();
            }

            @Override // OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b
            public void onSuccess(String str) {
            }
        };
        this.mJsonCallback = new b() { // from class: com.loostone.libtuning.channel.BaseChannelMgr$mJsonCallback$1
            @Override // OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b
            public void onError(String str) {
                OooO00o.OooO00o.OooO0O0.b.f41a.d("BaseChannelMgr", h.i(" ", str));
            }

            /* JADX WARN: Removed duplicated region for block: B:9:0x001d  */
            @Override // OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onSuccess(java.lang.String r6) {
                /*
                    r5 = this;
                    OooO00o.OooO00o.OooO0O0.b r0 = OooO00o.OooO00o.OooO0O0.b.f41a
                    java.lang.String r1 = "mJsonCallback = "
                    java.lang.String r1 = kotlin.jvm.internal.h.i(r1, r6)
                    java.lang.String r2 = "BaseChannelMgr"
                    r0.e(r2, r1)
                    r0 = 1
                    r1 = 0
                    if (r6 != 0) goto L12
                    goto L1d
                L12:
                    java.lang.String r2 = "micList"
                    r3 = 2
                    r4 = 0
                    boolean r2 = kotlin.text.f.o(r6, r2, r1, r3, r4)     // Catch: java.lang.Exception -> L4a
                    if (r2 != r0) goto L1d
                    goto L1e
                L1d:
                    r0 = r1
                L1e:
                    if (r0 == 0) goto L37
                    com.loostone.libtuning.channel.BaseChannelMgr r0 = r5.this$0     // Catch: java.lang.Exception -> L4a
                    java.lang.String r6 = r6.toString()     // Catch: java.lang.Exception -> L4a
                    com.loostone.libtuning.channel.BaseChannelMgr.access$upgradeMicList(r0, r6)     // Catch: java.lang.Exception -> L4a
                    com.loostone.libtuning.channel.BaseChannelMgr r6 = r5.this$0     // Catch: java.lang.Exception -> L4a
                    OooO00o.OooO00o.OooO0Oo.OooO00o r6 = r6.getNetworkMgr()     // Catch: java.lang.Exception -> L4a
                    OooO00o.OooO00o.OooO0Oo.OooO0Oo.OooO0O0 r6 = r6.c()     // Catch: java.lang.Exception -> L4a
                    r6.a()     // Catch: java.lang.Exception -> L4a
                    goto L4e
                L37:
                    OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0O0 r0 = OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0O0.f354a     // Catch: java.lang.Exception -> L4a
                    r0.q(r6)     // Catch: java.lang.Exception -> L4a
                    com.loostone.libtuning.channel.BaseChannelMgr r6 = r5.this$0     // Catch: java.lang.Exception -> L4a
                    OooO00o.OooO00o.OooO0Oo.OooO00o r6 = r6.getNetworkMgr()     // Catch: java.lang.Exception -> L4a
                    OooO00o.OooO00o.OooO0Oo.OooO0Oo.OooO0O0 r6 = r6.c()     // Catch: java.lang.Exception -> L4a
                    r6.q()     // Catch: java.lang.Exception -> L4a
                    goto L4e
                L4a:
                    r6 = move-exception
                    r6.printStackTrace()
                L4e:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.loostone.libtuning.channel.BaseChannelMgr$mJsonCallback$1.onSuccess(java.lang.String):void");
            }
        };
        this.mFirmwareCallback = new b() { // from class: com.loostone.libtuning.channel.BaseChannelMgr$mFirmwareCallback$1
            @Override // OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b
            public void onError(String str) {
                OooO00o.OooO00o.OooO0O0.b.f41a.d("BaseChannelMgr", h.i(" ", str));
            }

            @Override // OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b
            public void onSuccess(String str) {
                OooO00o.OooO00o.OooO0O0.b.f41a.e("BaseChannelMgr", h.i(" ", str));
                if (str == null) {
                    return;
                }
                this.this$0.checkLocalFirmware(str);
            }
        };
    }

    private final void adjustDongleVolume(String str) {
        a.a.d.b.a aVar = a.a.d.b.a.f564a;
        Integer num = aVar.p().get(str);
        aVar.c(num == null ? 0 : num.intValue());
        OooO oooO = OooO.f199a;
        oooO.l().e(oooO.l().a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkLocalFirmware(String str) {
        File file = new File(str);
        try {
            if (file.exists() && file.isFile()) {
                OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "start update firmware");
                DongleUpdateUtil.getInstance().startUpdate(file, null);
            } else {
                OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "no local firmware");
            }
        } catch (Exception e) {
            OooO00o.OooO00o.OooO0O0.b.f41a.d(TAG, "check local firmware error");
            e.printStackTrace();
        }
    }

    private final List<MicItem> getMicList() {
        String strA = c.a(this.context, "mic_list");
        if (!h.a(strA, BuildConfig.FLAVOR) && strA != null) {
            return (List) new Gson().fromJson(strA, new TypeToken<List<MicItem>>() { // from class: com.loostone.libtuning.channel.BaseChannelMgr.getMicList.1
            }.getType());
        }
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "micList is null");
        return null;
    }

    private final OooO00o getTuningDeviceManager() {
        return (OooO00o) this.tuningDeviceManager$delegate.getValue();
    }

    private final void registerReceiver() {
        getInfoReceiver().m();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendDongleInfo(int i) {
        Intent intent = new Intent();
        intent.setAction("com.loostone.tuning.BROAD.ACTION.DONGLE_INFO");
        intent.putExtra("dongleStatus", i);
        this.context.sendBroadcast(intent);
    }

    private final void setMicOffset(List<MicItem> list) {
        if (list == null) {
            return;
        }
        for (MicItem micItem : list) {
            a.a.d.b.a.f564a.p().put(micItem.getModelName(), Integer.valueOf(micItem.getPregainMic()));
        }
    }

    private final void unRegisterReceiver() {
        try {
            getInfoReceiver().n();
        } catch (Exception e) {
            OooO00o.OooO00o.OooO0O0.b.f41a.d(TAG, "unRegisterReceiver error");
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void upgradeMicList(String str) {
        DefaultMicConfig defaultMicConfig = (DefaultMicConfig) new Gson().fromJson(str, DefaultMicConfig.class);
        List<MicItem> micList = defaultMicConfig.getMicList();
        setMicOffset(micList);
        dynamicAdjustCompPregain();
        OooO00o.OooO00o.OooO0O0.a aVar = OooO00o.OooO00o.OooO0O0.a.f39a;
        c.b(aVar.a(), "mic_period_ota", defaultMicConfig.getMicPeriod());
        c.b(aVar.a(), "mic_version_ota", defaultMicConfig.getMicListVersion());
        c.c(aVar.a(), "mic_list", new Gson().toJson(micList));
    }

    public final void adjustMicVolume(int i) {
        a.a.d.b.a.f564a.g(i);
        OooO oooO = OooO.f199a;
        oooO.l().e(oooO.l().a());
    }

    public final void dynamicAdjustCompPregain() {
        OooO00o.OooO00o.OooO0o0.OooO0O0.OooO0OO.OooO00o oooO00o;
        OooO0o oooO0o = OooO0o.f425a;
        Integer numA = oooO0o.l().a();
        if (numA != null && numA.intValue() == 1) {
            Integer numA2 = oooO0o.H().a();
            if ((numA2 != null && numA2.intValue() == 0) || (oooO00o = (OooO00o.OooO00o.OooO0o0.OooO0O0.OooO0OO.OooO00o) getMicControlService()) == null) {
                return;
            }
            byte[] bArrO = oooO00o.o();
            if (bArrO.length < 2) {
                return;
            }
            OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "micKey A = " + ((int) bArrO[0]) + ", B = " + ((int) bArrO[1]));
            if (((byte) (bArrO[0] & 64)) != 64 || ((byte) (bArrO[1] & 64)) != 64) {
                adjustMicVolume(0);
            } else {
                Integer numA3 = oooO0o.H().a();
                adjustMicVolume(numA3 != null ? numA3.intValue() : 0);
            }
        }
    }

    public final AudioManager getAudioManager() {
        return (AudioManager) this.audioManager$delegate.getValue();
    }

    public final OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0OO.OooO0o0.a getFullSceneMode() {
        return (OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0OO.OooO0o0.a) this.fullSceneMode$delegate.getValue();
    }

    public final OooO00o.OooO00o.OooO0o0.OooO00o.OooO00o.OooO00o getInfoReceiver() {
        return (OooO00o.OooO00o.OooO0o0.OooO00o.OooO00o.OooO00o) this.infoReceiver$delegate.getValue();
    }

    public final IBaseKeyService getKeyService() {
        return (IBaseKeyService) this.keyService$delegate.getValue();
    }

    public final LocalDongleManager getLocalDongleManager() {
        return (LocalDongleManager) this.localDongleManager$delegate.getValue();
    }

    public final b getMActivateCallback() {
        return this.mActivateCallback;
    }

    public final b getMFirmwareCallback() {
        return this.mFirmwareCallback;
    }

    public final b getMJsonCallback() {
        return this.mJsonCallback;
    }

    @Override // com.loostone.libtuning.inf.extern.IChannelInfo
    public IMicControl getMicControlService() {
        return getKeyService().getMicControl();
    }

    @Override // com.loostone.libtuning.inf.extern.IChannelInfo
    public IBaseKeyService getMicService() {
        return getKeyService();
    }

    public final int getMicStatus() {
        return this.micStatus;
    }

    public final OooO00o.OooO00o.OooO0Oo.OooO00o getNetMgr() {
        return getNetworkMgr();
    }

    public final OooO00o.OooO00o.OooO0Oo.OooO00o getNetworkMgr() {
        return (OooO00o.OooO00o.OooO0Oo.OooO00o) this.networkMgr$delegate.getValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IChannelInfo
    public int getPowerStatus() {
        return this.micStatus;
    }

    @Override // com.loostone.libtuning.inf.extern.IChannelInfo
    public int getVerifyStatus() {
        return this.verifyStatus;
    }

    public final IVolumeAdjustment getVolumeAdjustment() {
        return (IVolumeAdjustment) this.volumeAdjustment$delegate.getValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IChannelInfo
    public boolean hasDongle() {
        return getLocalDongleManager().getMEventManager().J();
    }

    public void init() {
        setMicOffset(getMicList());
        getLocalDongleManager().init();
        getTuningDeviceManager().k();
        registerReceiver();
        getLocalDongleManager().getMEventManager().g(this.dongleCallback);
        getKeyService().registerKeyListener(this.keyListener);
    }

    public void release() {
        getLocalDongleManager().release();
        getTuningDeviceManager().l();
        unRegisterReceiver();
        getFullSceneMode().e();
        getLocalDongleManager().getMEventManager().q(this.dongleCallback);
        getKeyService().unRegisterKeyListener(this.keyListener);
    }

    public final void setMicStatus(int i) {
        this.micStatus = i;
    }

    public void startKaraoke() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, h.i("startKaraoke ------> verifyStatus = ", Integer.valueOf(this.verifyStatus)));
        if (this.verifyStatus == 2) {
            getFullSceneMode().d();
        }
    }

    public void stopKaraoke() {
        getFullSceneMode().e();
    }

    public /* synthetic */ BaseChannelMgr(Context context, int i, f fVar) {
        this((i & 1) != 0 ? OooO00o.OooO00o.OooO0O0.a.f39a.a() : context);
    }
}
