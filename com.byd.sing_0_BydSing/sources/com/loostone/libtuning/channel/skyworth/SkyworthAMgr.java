package com.loostone.libtuning.channel.skyworth;

import OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO00o;
import OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO;
import OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0o;
import a.a.d.c.a;
import a.a.d.c.c;
import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.loostone.libtuning.R;
import com.loostone.libtuning.channel.BaseChannelMgr;
import com.loostone.libtuning.channel.skyworth.inf.ISkySystemProperty;
import com.loostone.libtuning.channel.skyworth.old.ai.AiControlFactory;
import com.loostone.libtuning.channel.skyworth.old.record.RecordMgr;
import com.loostone.libtuning.channel.skyworth.util.SkySystemPropertyService;
import com.loostone.libtuning.channel.skyworth.util.SkyworthAIControl;
import com.loostone.libtuning.channel.skyworth.util.SkyworthBlueToothMgr;
import com.loostone.libtuning.inf.extern.IBaseKeyService;
import com.loostone.libtuning.inf.extern.IKeyListener;
import java.util.ArrayList;
import java.util.Locale;
import kotlin.jvm.internal.h;
import kotlin.text.o;

/* JADX INFO: loaded from: classes.dex */
public final class SkyworthAMgr extends BaseChannelMgr implements a.a.d.c.a, IKeyListener, OooO00o.OooO00o.OooO0Oo.a.b<Boolean>, a.a.d.c.c, ISkySystemProperty, SkyworthBlueToothMgr.IBTDeviceConnectedChangedListener {
    public static final Companion Companion = new Companion(null);
    private static final String DONGLE_CARD = "third.pmkara.mic.card";
    private static final String DONGLE_PRODUCT_NAME = "third.pmkara.mic.name";
    private static final String MIC_STATUS = "third.pmkara.mic.status";
    private static final String TAG = "SkyworthAMgr";
    private final Handler mHandler;
    private final kotlin.a skyworthAIControl$delegate;
    private final kotlin.a systemProperty$delegate;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(kotlin.jvm.internal.f fVar) {
            this();
        }
    }

    public SkyworthAMgr() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public /* synthetic */ SkyworthAMgr(Context context, int i, kotlin.jvm.internal.f fVar) {
        this((i & 1) != 0 ? OooO00o.OooO00o.OooO0O0.a.f39a.a() : context);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final String getSkyModelName() {
        OooO00o.OooO00o.OooO0O0.OooO0o0.c cVar = OooO00o.OooO00o.OooO0O0.OooO0o0.c.f35a;
        String strD = OooO00o.OooO00o.OooO0O0.OooO0o0.b.d(cVar.b("ro.build.skymid"));
        h.c(strD, "trim(SystemProperty[\"ro.build.skymid\"])");
        Locale locale = Locale.getDefault();
        h.c(locale, "getDefault()");
        String upperCase = strD.toUpperCase(locale);
        h.c(upperCase, "(this as java.lang.String).toUpperCase(locale)");
        String strD2 = OooO00o.OooO00o.OooO0O0.OooO0o0.b.d(cVar.b("ro.build.skymodel"));
        h.c(strD2, "trim(SystemProperty[\"ro.build.skymodel\"])");
        Locale locale2 = Locale.getDefault();
        h.c(locale2, "getDefault()");
        String upperCase2 = strD2.toUpperCase(locale2);
        h.c(upperCase2, "(this as java.lang.String).toUpperCase(locale)");
        String strD3 = OooO00o.OooO00o.OooO0O0.OooO0o0.b.d(cVar.b("ro.build.skytype"));
        h.c(strD3, "trim(SystemProperty[\"ro.build.skytype\"])");
        Locale locale3 = Locale.getDefault();
        h.c(locale3, "getDefault()");
        String upperCase3 = strD3.toUpperCase(locale3);
        h.c(upperCase3, "(this as java.lang.String).toUpperCase(locale)");
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "platform = " + upperCase + " , movement = " + upperCase2 + " , modelStr = " + upperCase3);
        switch (upperCase.hashCode()) {
            case -2011195287:
                if (upperCase.equals("MT9638")) {
                    if (!o.l(upperCase2, "7T8", false, 2, null)) {
                        return upperCase;
                    }
                    return upperCase2 + '_' + upperCase3;
                }
                break;
            case -2011192350:
                if (upperCase.equals("MT9950")) {
                    return upperCase;
                }
                break;
            case 612510786:
                if (upperCase.equals("RTK2885N")) {
                    return upperCase;
                }
                break;
            case 1758030915:
                if (upperCase.equals("HI3751V811")) {
                    return upperCase;
                }
                break;
        }
        return upperCase2 + '_' + upperCase3;
    }

    private final SkyworthAIControl getSkyworthAIControl() {
        return (SkyworthAIControl) this.skyworthAIControl$delegate.getValue();
    }

    private final SkySystemPropertyService getSystemProperty() {
        return (SkySystemPropertyService) this.systemProperty$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onDeviceChanged$lambda-4, reason: not valid java name */
    public static final void m16onDeviceChanged$lambda4(SkyworthAMgr skyworthAMgr) {
        h.d(skyworthAMgr, "this$0");
        if (SkyworthBlueToothMgr.getInstance().isConnectAudioBt() && skyworthAMgr.getPowerStatus() == 3) {
            Toast.makeText(OooO00o.OooO00o.OooO0O0.a.f39a.a(), R.string.mic_power_on_bt, 1).show();
            SkyworthBlueToothMgr.getInstance().disconnectBtAudioDevice();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onPower$lambda-2, reason: not valid java name */
    public static final void m17onPower$lambda2() {
        Toast.makeText(OooO00o.OooO00o.OooO0O0.a.f39a.a(), R.string.mic_power_on_bt, 1).show();
        SkyworthBlueToothMgr.getInstance().disconnectBtAudioDevice();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onPower$lambda-3, reason: not valid java name */
    public static final void m18onPower$lambda3() {
        Toast.makeText(OooO00o.OooO00o.OooO0O0.a.f39a.a(), R.string.mic_power_off_bt, 1).show();
        SkyworthBlueToothMgr.getInstance().connectBtAudioDevice();
    }

    @Override // com.loostone.libtuning.channel.BaseChannelMgr
    public void init() {
        super.init();
        getLocalDongleManager().getMEventManager().g(this);
        getKeyService().registerKeyListener(this);
        getInfoReceiver().d(this);
        getSystemProperty().registerCallback(this);
        SkyworthBlueToothMgr skyworthBlueToothMgr = SkyworthBlueToothMgr.getInstance();
        OooO00o.OooO00o.OooO0O0.a aVar = OooO00o.OooO00o.OooO0O0.a.f39a;
        skyworthBlueToothMgr.init(aVar.a());
        SkyworthBlueToothMgr.getInstance().setListener(this);
        AiControlFactory.createAiControl(aVar.a(), 0);
        getSystemProperty().start();
    }

    @Override // a.a.d.c.c
    public void onBydMicKeyEvent(int i) {
        c.a.a(this);
    }

    @Override // a.a.d.c.a
    public void onDeviceAddedWithHid(OooO00o oooO00o) {
        h.d(oooO00o, "cachedDevice");
        OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
        bVar.b(TAG, " ------> 设备验证通过, 打开so或者ko");
        int iA = OooO00o.OooO00o.OooO0O0.OooO0o0.b.a(oooO00o.t());
        if (iA > 0) {
            OooO00o.OooO00o.OooO0O0.OooO0o0.c cVar = OooO00o.OooO00o.OooO0O0.OooO0o0.c.f35a;
            cVar.d(DONGLE_CARD, 'c' + iA + "d0");
            cVar.d(DONGLE_PRODUCT_NAME, oooO00o.t());
        } else {
            OooO00o.OooO00o.OooO0O0.OooO0o0.c cVar2 = OooO00o.OooO00o.OooO0O0.OooO0o0.c.f35a;
            cVar2.d(DONGLE_CARD, "null");
            cVar2.d(DONGLE_PRODUCT_NAME, "null");
        }
        Integer numA = OooO0o.f425a.M().a();
        if (numA != null && numA.intValue() == 3) {
            OooO oooO = OooO.f199a;
            oooO.D0().e("000");
            oooO.U1().e(iA + "00");
            bVar.e(TAG, " ------> 设置capture和playback节点 c: 000 p: " + iA + "00");
        }
        startKaraoke();
        IBaseKeyService keyService = getKeyService();
        keyService.setDevice(oooO00o.w());
        keyService.start();
        OooO00o.OooO00o.OooO0Oo.a.a aVarA = getNetworkMgr().a();
        aVarA.b(oooO00o, this);
        aVarA.a(oooO00o);
        getSkyworthAIControl().micChanged(1);
    }

    @Override // com.loostone.libtuning.channel.skyworth.util.SkyworthBlueToothMgr.IBTDeviceConnectedChangedListener
    public void onDeviceChanged() {
        if (getPowerStatus() == 3) {
            this.mHandler.postDelayed(new Runnable() { // from class: com.loostone.libtuning.channel.skyworth.c
                @Override // java.lang.Runnable
                public final void run() {
                    SkyworthAMgr.m16onDeviceChanged$lambda4(this.f3882b);
                }
            }, 5000L);
        }
    }

    @Override // a.a.d.c.a
    public void onDeviceDeniedByHid(OooO00o oooO00o) {
        h.d(oooO00o, "cachedDevice");
    }

    @Override // a.a.d.c.a
    public void onDeviceRemoved(OooO00o oooO00o) {
        h.d(oooO00o, "cachedDevice");
        OooO00o.OooO00o.OooO0O0.b.f41a.f(TAG, h.i(" ------> 设备移除 ", oooO00o.t()));
        OooO00o.OooO00o.OooO0O0.OooO0o0.c cVar = OooO00o.OooO00o.OooO0O0.OooO0o0.c.f35a;
        cVar.d(DONGLE_CARD, "null");
        cVar.d(DONGLE_PRODUCT_NAME, "null");
        stopKaraoke();
        getKeyService().stop();
    }

    @Override // a.a.d.c.a
    public void onDonglePermissionDenied() {
        getSkyworthAIControl().micChanged(0);
    }

    @Override // a.a.d.c.a
    public void onDonglePermissionGranted(UsbDevice usbDevice) {
        h.d(usbDevice, "usbDevice");
        OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
        bVar.e(TAG, h.i(" ------> 权限申请成功 ", usbDevice.getProductName()));
        bVar.e(TAG, h.i(" ------> 设备数量 ", Integer.valueOf(((ArrayList) getLocalDongleManager().getMCachedDongleDeviceManager().i()).size())));
    }

    @Override // com.loostone.libtuning.channel.skyworth.inf.ISkySystemProperty
    public void onDormancy(boolean z) {
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, h.i("休眠静音监听 ------> ", Boolean.valueOf(z)));
        getVolumeAdjustment().setMicVolumeEnable(!z);
    }

    @Override // OooO00o.OooO00o.OooO0Oo.a.b
    public void onError() {
        if (getVerifyStatus() != 2) {
            getSkyworthAIControl().micChanged(0);
            stopKaraoke();
            getKeyService().stop();
        }
    }

    @Override // a.a.d.c.a
    public void onMicConnectState(UsbDevice usbDevice, boolean z) {
        a.C0027a.a(this, usbDevice, z);
    }

    @Override // com.loostone.libtuning.inf.extern.IKeyListener
    public void onPairStateChanged(int i, int i2) {
        IKeyListener.DefaultImpls.onPairStateChanged(this, i, i2);
    }

    @Override // com.loostone.libtuning.inf.extern.IKeyListener
    public void onPower(int i, boolean z) {
        if (z) {
            OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, h.i(" ------> 电源按下 ", Integer.valueOf(i)));
        } else {
            OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, h.i(" ------> 电源弹起 ", Integer.valueOf(i)));
        }
        if (i != 0) {
            return;
        }
        if (z) {
            OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, " ------> 电源按下");
            getFullSceneMode().a(1);
            OooO00o.OooO00o.OooO0O0.OooO0o0.c.f35a.d(MIC_STATUS, "1");
            if (SkyworthBlueToothMgr.getInstance().isConnectAudioBt()) {
                this.mHandler.post(new Runnable() { // from class: com.loostone.libtuning.channel.skyworth.b
                    @Override // java.lang.Runnable
                    public final void run() {
                        SkyworthAMgr.m17onPower$lambda2();
                    }
                });
                return;
            }
            return;
        }
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, " ------> 电源弹起");
        getFullSceneMode().a(0);
        OooO00o.OooO00o.OooO0O0.OooO0o0.c.f35a.d(MIC_STATUS, "0");
        if ((!SkyworthBlueToothMgr.getInstance().isConnectAudioBt()) && (!SkyworthBlueToothMgr.getInstance().isAddressNull())) {
            this.mHandler.post(new Runnable() { // from class: com.loostone.libtuning.channel.skyworth.a
                @Override // java.lang.Runnable
                public final void run() {
                    SkyworthAMgr.m18onPower$lambda3();
                }
            });
        }
    }

    @Override // a.a.d.c.c
    public void onScreenChange(boolean z) {
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, " ------> 亮屏息屏");
    }

    @Override // a.a.d.c.c
    public void onSkyworthKey(int i) {
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, " ------> 按键监听");
    }

    @Override // OooO00o.OooO00o.OooO0Oo.a.b
    public /* bridge */ /* synthetic */ void onSuccess(Boolean bool) {
        onSuccess(bool.booleanValue());
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
            RecordMgr.getInstance().create();
            getVolumeAdjustment().setMicVolumeEnable(false);
            OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, " ------> 语音按下");
        } else {
            OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, " ------> 语音弹起");
            getVolumeAdjustment().setMicVolumeEnable(true);
        }
        if (AiControlFactory.getAiControl() != null) {
            AiControlFactory.getAiControl().onVoiceKey(z ? 1 : 0);
        }
    }

    @Override // a.a.d.c.c
    public void onVolumeChange(int i) {
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, h.i(" ------> 设置音量 ", Integer.valueOf(i)));
    }

    @Override // com.loostone.libtuning.inf.extern.IKeyListener
    public void onVolumeDown(int i, boolean z) {
        if (i != 0) {
            return;
        }
        if (!z) {
            OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, " ------> 音量减弹起");
        } else {
            OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, " ------> 音量减按下");
            getVolumeAdjustment().volumeDown(1);
        }
    }

    @Override // com.loostone.libtuning.inf.extern.IKeyListener
    public void onVolumeUp(int i, boolean z) {
        if (i != 0) {
            return;
        }
        if (!z) {
            OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, " ------> 音量加弹起");
        } else {
            OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, " ------> 音量加按下");
            getVolumeAdjustment().volumeUp(1);
        }
    }

    @Override // com.loostone.libtuning.channel.BaseChannelMgr
    public void release() {
        super.release();
        getLocalDongleManager().getMEventManager().q(this);
        getKeyService().unRegisterKeyListener(this);
        getInfoReceiver().h(this);
        getSystemProperty().unRegisterCallback(this);
        SkyworthBlueToothMgr.getInstance().deinit();
        getSystemProperty().stop();
    }

    public void onSuccess(boolean z) {
        getSkyworthAIControl().micChanged(1);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SkyworthAMgr(Context context) {
        super(context);
        h.d(context, "context");
        this.skyworthAIControl$delegate = kotlin.d.b(new kotlin.jvm.b.a<SkyworthAIControl>() { // from class: com.loostone.libtuning.channel.skyworth.SkyworthAMgr$skyworthAIControl$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.b.a
            public final SkyworthAIControl invoke() {
                return new SkyworthAIControl(null, 1, null);
            }
        });
        this.systemProperty$delegate = kotlin.d.b(new kotlin.jvm.b.a<SkySystemPropertyService>() { // from class: com.loostone.libtuning.channel.skyworth.SkyworthAMgr$systemProperty$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.b.a
            public final SkySystemPropertyService invoke() {
                return new SkySystemPropertyService();
            }
        });
        this.mHandler = new Handler(Looper.getMainLooper());
    }
}
