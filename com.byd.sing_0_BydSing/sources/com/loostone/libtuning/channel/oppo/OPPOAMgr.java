package com.loostone.libtuning.channel.oppo;

import OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO00o;
import OooO00o.OooO00o.OooO0Oo.a.b;
import OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO;
import OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0o;
import a.a.d.c.a;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.widget.Toast;
import com.loostone.libtuning.R;
import com.loostone.libtuning.channel.BaseChannelMgr;
import com.loostone.libtuning.component.provider.TuningProvider;
import com.loostone.libtuning.inf.extern.IBaseKeyService;
import com.loostone.libtuning.inf.extern.IKeyListener;
import java.util.ArrayList;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class OPPOAMgr extends BaseChannelMgr implements a, IKeyListener, b<Boolean> {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "OPPOAMgr";

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(f fVar) {
            this();
        }
    }

    public OPPOAMgr() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public /* synthetic */ OPPOAMgr(Context context, int i, f fVar) {
        this((i & 1) != 0 ? OooO00o.OooO00o.OooO0O0.a.f39a.a() : context);
    }

    @Override // com.loostone.libtuning.channel.BaseChannelMgr
    public void init() {
        super.init();
        getLocalDongleManager().getMEventManager().g(this);
        getKeyService().registerKeyListener(this);
    }

    @Override // a.a.d.c.a
    public void onDeviceAddedWithHid(OooO00o oooO00o) {
        h.d(oooO00o, "cachedDevice");
        OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
        bVar.b(TAG, " ------> 设备验证通过, 打开so或者ko");
        Toast.makeText(OooO00o.OooO00o.OooO0O0.a.f39a.a(), R.string.oppo_a_mic_in, 0).show();
        Integer numA = OooO0o.f425a.M().a();
        if ((numA == null ? 1 : numA.intValue()) == 1) {
            int iA = OooO00o.OooO00o.OooO0O0.OooO0o0.b.a(oooO00o.t());
            OooO.f199a.D0().e(iA + "00");
            bVar.e(TAG, "------> 设置capture card: " + iA + " device: 00");
        }
        startKaraoke();
        IBaseKeyService keyService = getKeyService();
        keyService.setDevice(oooO00o.w());
        keyService.start();
        OooO00o.OooO00o.OooO0Oo.a.a aVarA = getNetworkMgr().a();
        aVarA.b(oooO00o, this);
        aVarA.a(oooO00o);
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
        if (!z) {
            OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, " ------> 语音弹起");
            return;
        }
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, " ------> 语音按下");
        Intent intent = new Intent("heytap.intent.action.ACTIVATE_SPEECH_ASSIST");
        intent.setComponent(new ComponentName("com.heytap.speechassist.tv", "com.heytap.speechassist.tv.core.SpeechService"));
        intent.putExtra("caller_package", TuningProvider.AUTHORITY);
        intent.putExtra("start_type", 536870912);
        intent.setFlags(268435456);
        try {
            androidx.core.content.a.h(OooO00o.OooO00o.OooO0O0.a.f39a.a(), intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        getLocalDongleManager().getMEventManager().q(this);
        getKeyService().unRegisterKeyListener(this);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OPPOAMgr(Context context) {
        super(context);
        h.d(context, "context");
    }
}
