package com.loostone.libtuning.channel.skyworth;

import OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO00o;
import OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO;
import OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0o;
import a.a.d.c.a;
import a.a.d.c.c;
import android.app.Instrumentation;
import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.loostone.libtuning.R;
import com.loostone.libtuning.channel.BaseChannelMgr;
import com.loostone.libtuning.channel.skyworth.util.SkyworthBlueToothMgr;
import com.loostone.libtuning.inf.extern.IBaseKeyService;
import com.loostone.libtuning.inf.extern.IKeyListener;
import java.util.ArrayList;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class SkyworthBMgr extends BaseChannelMgr implements a.a.d.c.a, IKeyListener, OooO00o.OooO00o.OooO0Oo.a.b<Boolean>, a.a.d.c.c, SkyworthBlueToothMgr.IBTDeviceConnectedChangedListener {
    public static final Companion Companion = new Companion(null);
    private static final int KEYCODE_ASSIST = 219;
    private static final String TAG = "SkyworthBMgr";
    private final Handler mHandler;
    private boolean strState;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(kotlin.jvm.internal.f fVar) {
            this();
        }
    }

    public SkyworthBMgr() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public /* synthetic */ SkyworthBMgr(Context context, int i, kotlin.jvm.internal.f fVar) {
        this((i & 1) != 0 ? OooO00o.OooO00o.OooO0O0.a.f39a.a() : context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onDeviceChanged$lambda-4, reason: not valid java name */
    public static final void m19onDeviceChanged$lambda4(SkyworthBMgr skyworthBMgr) {
        h.d(skyworthBMgr, "this$0");
        if (SkyworthBlueToothMgr.getInstance().isConnectAudioBt() && skyworthBMgr.getPowerStatus() == 3) {
            Toast.makeText(OooO00o.OooO00o.OooO0O0.a.f39a.a(), R.string.mic_power_on_bt, 1).show();
            SkyworthBlueToothMgr.getInstance().disconnectBtAudioDevice();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onPower$lambda-2, reason: not valid java name */
    public static final void m20onPower$lambda2() {
        Toast.makeText(OooO00o.OooO00o.OooO0O0.a.f39a.a(), R.string.mic_power_on_bt, 1).show();
        SkyworthBlueToothMgr.getInstance().disconnectBtAudioDevice();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onPower$lambda-3, reason: not valid java name */
    public static final void m21onPower$lambda3() {
        Toast.makeText(OooO00o.OooO00o.OooO0O0.a.f39a.a(), R.string.mic_power_off_bt, 1).show();
        SkyworthBlueToothMgr.getInstance().connectBtAudioDevice();
    }

    @Override // com.loostone.libtuning.channel.BaseChannelMgr
    public void init() {
        super.init();
        getLocalDongleManager().getMEventManager().g(this);
        getKeyService().registerKeyListener(this);
        getInfoReceiver().d(this);
        SkyworthBlueToothMgr.getInstance().init(OooO00o.OooO00o.OooO0O0.a.f39a.a());
        SkyworthBlueToothMgr.getInstance().setListener(this);
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
        if (this.strState) {
            bVar.e(TAG, " ------> STR待机状态，不响应USB插入事件");
            return;
        }
        int iA = OooO00o.OooO00o.OooO0O0.OooO0o0.b.a(oooO00o.t());
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
    }

    @Override // com.loostone.libtuning.channel.skyworth.util.SkyworthBlueToothMgr.IBTDeviceConnectedChangedListener
    public void onDeviceChanged() {
        if (getPowerStatus() == 3) {
            this.mHandler.postDelayed(new Runnable() { // from class: com.loostone.libtuning.channel.skyworth.f
                @Override // java.lang.Runnable
                public final void run() {
                    SkyworthBMgr.m19onDeviceChanged$lambda4(this.f3885b);
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
        stopKaraoke();
        getKeyService().stop();
    }

    @Override // a.a.d.c.a
    public void onDonglePermissionDenied() {
    }

    @Override // a.a.d.c.a
    public void onDonglePermissionGranted(UsbDevice usbDevice) {
        h.d(usbDevice, "usbDevice");
        OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
        bVar.e(TAG, h.i(" ------> 权限申请成功 ", usbDevice.getProductName()));
        bVar.e(TAG, h.i(" ------> 设备数量 ", Integer.valueOf(((ArrayList) getLocalDongleManager().getMCachedDongleDeviceManager().i()).size())));
    }

    @Override // OooO00o.OooO00o.OooO0Oo.a.b
    public void onError() {
        if (getVerifyStatus() != 2) {
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
            if (SkyworthBlueToothMgr.getInstance().isConnectAudioBt()) {
                this.mHandler.post(new Runnable() { // from class: com.loostone.libtuning.channel.skyworth.e
                    @Override // java.lang.Runnable
                    public final void run() {
                        SkyworthBMgr.m20onPower$lambda2();
                    }
                });
                return;
            }
            return;
        }
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, " ------> 电源弹起");
        getFullSceneMode().a(0);
        if ((true ^ SkyworthBlueToothMgr.getInstance().isAddressNull()) && (SkyworthBlueToothMgr.getInstance().isConnectAudioBt() ^ true)) {
            this.mHandler.post(new Runnable() { // from class: com.loostone.libtuning.channel.skyworth.d
                @Override // java.lang.Runnable
                public final void run() {
                    SkyworthBMgr.m21onPower$lambda3();
                }
            });
        }
    }

    @Override // a.a.d.c.c
    public void onScreenChange(boolean z) {
        this.strState = !z;
        if (z) {
            getFullSceneMode().d();
        } else {
            getFullSceneMode().e();
        }
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
        if (i == 0 && !this.strState) {
            if (!z) {
                OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, " ------> 语音弹起");
                getVolumeAdjustment().setMicVolumeEnable(true);
            } else {
                getVolumeAdjustment().setMicVolumeEnable(false);
                new Instrumentation().sendKeyDownUpSync(219);
                OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, " ------> 语音按下");
            }
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
        SkyworthBlueToothMgr.getInstance().deinit();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SkyworthBMgr(Context context) {
        super(context);
        h.d(context, "context");
        this.mHandler = new Handler(Looper.getMainLooper());
    }
}
