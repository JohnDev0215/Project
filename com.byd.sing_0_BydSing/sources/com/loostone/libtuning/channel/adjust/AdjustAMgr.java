package com.loostone.libtuning.channel.adjust;

import OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO00o;
import OooO00o.OooO00o.OooO0Oo.a.b;
import OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO;
import OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0o;
import a.a.d.c.a;
import android.content.Context;
import android.hardware.usb.UsbDevice;
import com.android.volley.BuildConfig;
import com.google.gson.Gson;
import com.loostone.libtuning.channel.BaseChannelMgr;
import com.loostone.libtuning.data.bean.EQItem;
import com.loostone.libtuning.data.config.DefaultConfig;
import com.loostone.libtuning.inf.extern.IAdjustEffect;
import com.loostone.libtuning.inf.extern.IKeyListener;
import java.util.ArrayList;
import kotlin.collections.k;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class AdjustAMgr extends BaseChannelMgr implements a, IKeyListener, IAdjustEffect, b<Boolean> {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "AdjustMgr";

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(f fVar) {
            this();
        }
    }

    public AdjustAMgr() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public /* synthetic */ AdjustAMgr(Context context, int i, f fVar) {
        this((i & 1) != 0 ? OooO00o.OooO00o.OooO0O0.a.f39a.a() : context);
    }

    @Override // com.loostone.libtuning.inf.extern.IAdjustEffect
    public String getConfigAll() {
        DefaultConfig defaultConfig = new DefaultConfig();
        OooO0o oooO0o = OooO0o.f425a;
        Long lA = oooO0o.j().a();
        defaultConfig.setChannel(lA == null ? 0L : lA.longValue());
        Integer numA = oooO0o.M().a();
        defaultConfig.setWorkModeInt(numA == null ? 0 : numA.intValue());
        OooO oooO = OooO.f199a;
        String strA = oooO.H1().a();
        String str = BuildConfig.FLAVOR;
        if (strA == null) {
            strA = BuildConfig.FLAVOR;
        }
        defaultConfig.setWorkMode(strA);
        Integer numA2 = oooO.A0().a();
        defaultConfig.setBeta(numA2 == null ? 0 : numA2.intValue());
        String strA2 = oooO.b1().a();
        if (strA2 != null) {
            str = strA2;
        }
        defaultConfig.setLogLevel(str);
        defaultConfig.setMicVolume(getVolumeAdjustment().getMicVolume());
        defaultConfig.setMusicVolume(getVolumeAdjustment().getMusicVolume());
        defaultConfig.setAef1ReverbEnable(getVolumeAdjustment().isAef1ReverbEnable() ? 1 : 0);
        defaultConfig.setAef1ReverbOutGain(Integer.valueOf(getVolumeAdjustment().getAef1ReverbOutGain()));
        defaultConfig.setAef2EQEnable(getVolumeAdjustment().isAef2EQEnable() ? 1 : 0);
        defaultConfig.setAef2MusicEQEnable(getVolumeAdjustment().isAef2MusicEQEnable() ? 1 : 0);
        EQItem eQItem = new EQItem();
        eQItem.setEq1(Integer.valueOf(getVolumeAdjustment().getAef2EQ1()));
        eQItem.setEq2(Integer.valueOf(getVolumeAdjustment().getAef2EQ2()));
        eQItem.setEq3(Integer.valueOf(getVolumeAdjustment().getAef2EQ3()));
        eQItem.setEq4(Integer.valueOf(getVolumeAdjustment().getAef2EQ4()));
        eQItem.setEq5(Integer.valueOf(getVolumeAdjustment().getAef2EQ5()));
        eQItem.setEq6(Integer.valueOf(getVolumeAdjustment().getAef2EQ6()));
        eQItem.setEq7(Integer.valueOf(getVolumeAdjustment().getAef2EQ7()));
        eQItem.setEq8(Integer.valueOf(getVolumeAdjustment().getAef2EQ8()));
        eQItem.setEq9(Integer.valueOf(getVolumeAdjustment().getAef2EQ9()));
        eQItem.setFrequency1(Integer.valueOf(getVolumeAdjustment().getAef2EQ1Frequency()));
        eQItem.setFrequency2(Integer.valueOf(getVolumeAdjustment().getAef2EQ2Frequency()));
        eQItem.setFrequency3(Integer.valueOf(getVolumeAdjustment().getAef2EQ3Frequency()));
        eQItem.setFrequency4(Integer.valueOf(getVolumeAdjustment().getAef2EQ4Frequency()));
        eQItem.setFrequency5(Integer.valueOf(getVolumeAdjustment().getAef2EQ5Frequency()));
        eQItem.setFrequency6(Integer.valueOf(getVolumeAdjustment().getAef2EQ6Frequency()));
        eQItem.setFrequency7(Integer.valueOf(getVolumeAdjustment().getAef2EQ7Frequency()));
        eQItem.setFrequency8(Integer.valueOf(getVolumeAdjustment().getAef2EQ8Frequency()));
        eQItem.setFrequency9(Integer.valueOf(getVolumeAdjustment().getAef2EQ9Frequency()));
        eQItem.setQ1(Integer.valueOf(getVolumeAdjustment().getAef3EqQ1()));
        eQItem.setQ2(Integer.valueOf(getVolumeAdjustment().getAef3EqQ2()));
        eQItem.setQ3(Integer.valueOf(getVolumeAdjustment().getAef3EqQ3()));
        eQItem.setQ4(Integer.valueOf(getVolumeAdjustment().getAef3EqQ4()));
        eQItem.setQ5(Integer.valueOf(getVolumeAdjustment().getAef3EqQ5()));
        eQItem.setQ6(Integer.valueOf(getVolumeAdjustment().getAef3EqQ6()));
        eQItem.setQ7(Integer.valueOf(getVolumeAdjustment().getAef3EqQ7()));
        eQItem.setQ8(Integer.valueOf(getVolumeAdjustment().getAef3EqQ8()));
        eQItem.setQ9(Integer.valueOf(getVolumeAdjustment().getAef3EqQ9()));
        eQItem.setType1(Integer.valueOf(getVolumeAdjustment().getAef3EQ1Type()));
        eQItem.setType2(Integer.valueOf(getVolumeAdjustment().getAef3EQ2Type()));
        eQItem.setType3(Integer.valueOf(getVolumeAdjustment().getAef3EQ3Type()));
        eQItem.setType4(Integer.valueOf(getVolumeAdjustment().getAef3EQ4Type()));
        eQItem.setType5(Integer.valueOf(getVolumeAdjustment().getAef3EQ5Type()));
        eQItem.setType6(Integer.valueOf(getVolumeAdjustment().getAef3EQ6Type()));
        eQItem.setType7(Integer.valueOf(getVolumeAdjustment().getAef3EQ7Type()));
        eQItem.setType8(Integer.valueOf(getVolumeAdjustment().getAef3EQ8Type()));
        eQItem.setType9(Integer.valueOf(getVolumeAdjustment().getAef3EQ9Type()));
        EQItem eQItem2 = new EQItem();
        eQItem2.setEq1(Integer.valueOf(getVolumeAdjustment().getAef2MusicEQ1()));
        eQItem2.setEq2(Integer.valueOf(getVolumeAdjustment().getAef2MusicEQ2()));
        eQItem2.setEq3(Integer.valueOf(getVolumeAdjustment().getAef2MusicEQ3()));
        eQItem2.setEq4(Integer.valueOf(getVolumeAdjustment().getAef2MusicEQ4()));
        eQItem2.setEq5(Integer.valueOf(getVolumeAdjustment().getAef2MusicEQ5()));
        eQItem2.setEq6(Integer.valueOf(getVolumeAdjustment().getAef2MusicEQ6()));
        eQItem2.setEq7(Integer.valueOf(getVolumeAdjustment().getAef2MusicEQ7()));
        eQItem2.setEq8(Integer.valueOf(getVolumeAdjustment().getAef2MusicEQ8()));
        eQItem2.setEq9(Integer.valueOf(getVolumeAdjustment().getAef2MusicEQ9()));
        eQItem2.setFrequency1(Integer.valueOf(getVolumeAdjustment().getAef2MusicEQ1Frequency()));
        eQItem2.setFrequency2(Integer.valueOf(getVolumeAdjustment().getAef2MusicEQ2Frequency()));
        eQItem2.setFrequency3(Integer.valueOf(getVolumeAdjustment().getAef2MusicEQ3Frequency()));
        eQItem2.setFrequency4(Integer.valueOf(getVolumeAdjustment().getAef2MusicEQ4Frequency()));
        eQItem2.setFrequency5(Integer.valueOf(getVolumeAdjustment().getAef2MusicEQ5Frequency()));
        eQItem2.setFrequency6(Integer.valueOf(getVolumeAdjustment().getAef2MusicEQ6Frequency()));
        eQItem2.setFrequency7(Integer.valueOf(getVolumeAdjustment().getAef2MusicEQ7Frequency()));
        eQItem2.setFrequency8(Integer.valueOf(getVolumeAdjustment().getAef2MusicEQ8Frequency()));
        eQItem2.setFrequency9(Integer.valueOf(getVolumeAdjustment().getAef2MusicEQ9Frequency()));
        defaultConfig.setEqList(k.c(eQItem, eQItem2));
        defaultConfig.setAef2EchoEnable(getVolumeAdjustment().isAef2EchoEnable() ? 1 : 0);
        defaultConfig.setAef2EchoGain(Integer.valueOf(getVolumeAdjustment().getAef2EchoGain()));
        defaultConfig.setAef2EchoType(Integer.valueOf(getVolumeAdjustment().getAef2EchoType()));
        defaultConfig.setAef2EchoTime(Integer.valueOf(getVolumeAdjustment().getAef2EchoTime()));
        defaultConfig.setAef2EchoFeedback(Integer.valueOf(getVolumeAdjustment().getAef2EchoFeedBack()));
        defaultConfig.setAef2ReverbEnable(getVolumeAdjustment().isAef2ReverbEnable() ? 1 : 0);
        defaultConfig.setAef2ReverbType(Integer.valueOf(getVolumeAdjustment().getAef2ReverbMode()));
        defaultConfig.setAef2ReverbVolume(Integer.valueOf(getVolumeAdjustment().getAef2ReverbVolume()));
        defaultConfig.setAef2ReverbTime(Integer.valueOf(getVolumeAdjustment().getAef2ReverbTime()));
        defaultConfig.setAef2VoiceMode(Integer.valueOf(getVolumeAdjustment().getAef2VoiceMode()));
        defaultConfig.setAef2CompressorEnable(getVolumeAdjustment().isAef2CompressorEnable() ? 1 : 0);
        defaultConfig.setAef2CompressorThreshold(Integer.valueOf(getVolumeAdjustment().getAef2CompressorThreshold()));
        defaultConfig.setAef2CompressorRatio(Integer.valueOf(getVolumeAdjustment().getAef2CompressorRatio()));
        defaultConfig.setAef2CompressorAttack(Integer.valueOf(getVolumeAdjustment().getAef2CompressorAttack()));
        defaultConfig.setAef2CompressorRelease(Integer.valueOf(getVolumeAdjustment().getAef2CompressorRelease()));
        defaultConfig.setAef2CompressorPregain(Integer.valueOf(getVolumeAdjustment().getAef2CompressorPregain()));
        String json = new Gson().toJson(defaultConfig);
        h.c(json, "Gson().toJson(config)");
        return json;
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
        OooO00o.OooO00o.OooO0O0.b.f41a.b(TAG, "设备验证通过, 打开so或者ko");
    }

    @Override // a.a.d.c.a
    public void onDeviceDeniedByHid(OooO00o oooO00o) {
        h.d(oooO00o, "cachedDevice");
    }

    @Override // a.a.d.c.a
    public void onDeviceRemoved(OooO00o oooO00o) {
        h.d(oooO00o, "cachedDevice");
        OooO00o.OooO00o.OooO0O0.b.f41a.f(TAG, h.i("设备移除 ", oooO00o.t()));
    }

    @Override // a.a.d.c.a
    public void onDonglePermissionDenied() {
    }

    @Override // a.a.d.c.a
    public void onDonglePermissionGranted(UsbDevice usbDevice) {
        h.d(usbDevice, "usbDevice");
        OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
        bVar.e(TAG, h.i("权限申请成功 ", usbDevice.getProductName()));
        bVar.e(TAG, h.i("设备数量 ", Integer.valueOf(((ArrayList) getLocalDongleManager().getMCachedDongleDeviceManager().i()).size())));
    }

    @Override // OooO00o.OooO00o.OooO0Oo.a.b
    public void onError() {
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
            OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "电源按下");
        } else {
            OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "电源弹起");
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
            OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "TV按下");
        } else {
            OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "TV弹起");
        }
    }

    @Override // com.loostone.libtuning.inf.extern.IKeyListener
    public void onVoice(int i, boolean z) {
        if (i != 0) {
            return;
        }
        if (z) {
            OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "语音按下");
        } else {
            OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "语音弹起");
        }
    }

    @Override // com.loostone.libtuning.inf.extern.IKeyListener
    public void onVolumeDown(int i, boolean z) {
        if (i != 0) {
            return;
        }
        if (z) {
            OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "音量减按下");
        } else {
            OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "音量减弹起");
        }
    }

    @Override // com.loostone.libtuning.inf.extern.IKeyListener
    public void onVolumeUp(int i, boolean z) {
        if (i != 0) {
            return;
        }
        if (z) {
            OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "音量加按下");
        } else {
            OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "音量加弹起");
        }
    }

    @Override // com.loostone.libtuning.channel.BaseChannelMgr
    public void release() {
        super.release();
        getLocalDongleManager().getMEventManager().q(this);
        getKeyService().unRegisterKeyListener(this);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AdjustAMgr(Context context) {
        super(context);
        h.d(context, "context");
    }
}
