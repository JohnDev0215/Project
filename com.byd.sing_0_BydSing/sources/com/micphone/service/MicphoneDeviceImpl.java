package com.micphone.service;

import android.micphone.IMicphoneNotificationCallback;
import android.micphone.IMicphoneService;
import android.os.RemoteException;
import com.android.volley.BuildConfig;
import com.audio.usbmic.UsbAudioNativeLoopConfig;
import com.audio.usbmic.UsbMicDevice;

/* JADX INFO: loaded from: classes.dex */
public class MicphoneDeviceImpl extends IMicphoneService.Stub {
    private ICallbackListener mListener;

    MicphoneDeviceImpl(ICallbackListener iCallbackListener) {
        this.mListener = iCallbackListener;
    }

    @Override // android.micphone.IMicphoneService
    public int LoadMicAudioEffect(byte[] bArr) throws RemoteException {
        return UsbAudioNativeLoopConfig.isSoftEffect() ? UsbMicDevice.loadEffect(bArr) : UsbMicDevice.usbMicLoadAudioEffect(bArr);
    }

    @Override // android.micphone.IMicphoneService
    public int change2OtaMode() throws RemoteException {
        return UsbMicDevice.change2OtaMode();
    }

    @Override // android.micphone.IMicphoneService
    public int getInternalEffect() throws RemoteException {
        return UsbMicDevice.getInternalEffect();
    }

    @Override // android.micphone.IMicphoneService
    public int getInternalEffectGroup() throws RemoteException {
        return UsbMicDevice.getInternalEffectGroup();
    }

    @Override // android.micphone.IMicphoneService
    public int getMicAConnect() throws RemoteException {
        return UsbMicDevice.usbMicIsConnected(0);
    }

    @Override // android.micphone.IMicphoneService
    public int getMicAFreq() throws RemoteException {
        return UsbMicDevice.usbMicGetFreq(0);
    }

    @Override // android.micphone.IMicphoneService
    public int getMicAPowerLevel() throws RemoteException {
        return UsbMicDevice.usbMicGetMicPower(0);
    }

    @Override // android.micphone.IMicphoneService
    public String getMicAProductName() throws RemoteException {
        return BuildConfig.FLAVOR;
    }

    @Override // android.micphone.IMicphoneService
    public int getMicARssi() throws RemoteException {
        return UsbMicDevice.usbMicGetRssi(0);
    }

    @Override // android.micphone.IMicphoneService
    public String getMicAVersion() throws RemoteException {
        return BuildConfig.FLAVOR;
    }

    @Override // android.micphone.IMicphoneService
    public int getMicBConnect() throws RemoteException {
        return UsbMicDevice.usbMicIsConnected(1);
    }

    @Override // android.micphone.IMicphoneService
    public int getMicBFreq() throws RemoteException {
        return UsbMicDevice.usbMicGetFreq(1);
    }

    @Override // android.micphone.IMicphoneService
    public int getMicBPowerLevel() throws RemoteException {
        return UsbMicDevice.usbMicGetMicPower(1);
    }

    @Override // android.micphone.IMicphoneService
    public String getMicBProductName() throws RemoteException {
        return BuildConfig.FLAVOR;
    }

    @Override // android.micphone.IMicphoneService
    public int getMicBRssi() throws RemoteException {
        return UsbMicDevice.usbMicGetRssi(1);
    }

    @Override // android.micphone.IMicphoneService
    public String getMicBVersion() throws RemoteException {
        return BuildConfig.FLAVOR;
    }

    @Override // android.micphone.IMicphoneService
    public String getMicDeviceId() throws RemoteException {
        return UsbMicDevice.usbMicGetDeviceId();
    }

    @Override // android.micphone.IMicphoneService
    public int getMicFreq(int i) {
        return UsbMicDevice.usbMicGetFreq(i);
    }

    @Override // android.micphone.IMicphoneService
    public int getMicIDByMic(int i) throws RemoteException {
        return UsbMicDevice.usbMicGetMicID(i);
    }

    @Override // android.micphone.IMicphoneService
    public int getMicIsConnected(int i) {
        return UsbMicDevice.usbMicIsConnected(i);
    }

    @Override // android.micphone.IMicphoneService
    public int getMicMax() throws RemoteException {
        return UsbMicDevice.getMicMax();
    }

    @Override // android.micphone.IMicphoneService
    public String getMicMicName(int i) throws RemoteException {
        return UsbMicDevice.usbMicGetMicName(i);
    }

    @Override // android.micphone.IMicphoneService
    public String getMicMicVersion(int i) throws RemoteException {
        return UsbMicDevice.usbMicGetMicVersion(i);
    }

    @Override // android.micphone.IMicphoneService
    public int getMicPower(int i) {
        return UsbMicDevice.usbMicGetMicPower(i);
    }

    @Override // android.micphone.IMicphoneService
    public String getMicProductName() throws RemoteException {
        return UsbMicDevice.usbMicGetDongleProductName();
    }

    @Override // android.micphone.IMicphoneService
    public int getMicReverb() throws RemoteException {
        return UsbAudioNativeLoopConfig.isSoftEffect() ? UsbMicDevice.getReverb() : UsbMicDevice.usbMicGetReverb();
    }

    @Override // android.micphone.IMicphoneService
    public int getMicRssi(int i) {
        return UsbMicDevice.usbMicGetRssi(i);
    }

    @Override // android.micphone.IMicphoneService
    public String getMicVersion() throws RemoteException {
        return UsbMicDevice.usbMicGetDongleVersion();
    }

    @Override // android.micphone.IMicphoneService
    public int getMicVolume() throws RemoteException {
        return UsbAudioNativeLoopConfig.isSoftEffect() ? UsbMicDevice.getVolume() : UsbMicDevice.usbMicGetMicVolume(0);
    }

    @Override // android.micphone.IMicphoneService
    public int getVolumeByMic(int i) throws RemoteException {
        return UsbMicDevice.usbMicGetMicVolume(i);
    }

    @Override // android.micphone.IMicphoneService
    public int isSupportOta() throws RemoteException {
        return UsbMicDevice.isSupportOta();
    }

    @Override // android.micphone.IMicphoneService
    public int isThunderMicphone(int i, int i2) throws RemoteException {
        return UsbMicDevice.usbMicAuthcheck(BuildConfig.FLAVOR, i, i2);
    }

    @Override // android.micphone.IMicphoneService
    public int pauseLoopback(int i) throws RemoteException {
        UsbMicDevice.loopbackPause(i);
        return 0;
    }

    @Override // android.micphone.IMicphoneService
    public void registerMicphoneNotificationCallback(IMicphoneNotificationCallback iMicphoneNotificationCallback) throws RemoteException {
        ICallbackListener iCallbackListener = this.mListener;
        if (iCallbackListener != null) {
            iCallbackListener.registerCallback(iMicphoneNotificationCallback);
        }
    }

    @Override // android.micphone.IMicphoneService
    public int saveEffect() throws RemoteException {
        return UsbMicDevice.usbMicSaveEffect();
    }

    @Override // android.micphone.IMicphoneService
    public int selectInternalEffect(int i) throws RemoteException {
        return UsbMicDevice.selectInternalEffect(i);
    }

    @Override // android.micphone.IMicphoneService
    public int selectInternalEffectGroup(int i) throws RemoteException {
        return UsbMicDevice.selectInternalEffectGroup(i);
    }

    @Override // android.micphone.IMicphoneService
    public int setLoopbackLatency(int i) throws RemoteException {
        return UsbMicDevice.loopbackLatency(i);
    }

    @Override // android.micphone.IMicphoneService
    public int setMicReverb(int i) throws RemoteException {
        return UsbAudioNativeLoopConfig.isSoftEffect() ? UsbMicDevice.setReverb(i) : UsbMicDevice.usbMicSetReverb(i);
    }

    @Override // android.micphone.IMicphoneService
    public int setMicVolume(int i) throws RemoteException {
        return UsbAudioNativeLoopConfig.isSoftEffect() ? UsbMicDevice.setVolume(i) : UsbMicDevice.usbMicSetMicVolume(0, i);
    }

    @Override // android.micphone.IMicphoneService
    public int setVolumeByMic(int i, int i2) throws RemoteException {
        return UsbMicDevice.usbMicSetMicVolume(i, i2);
    }

    @Override // android.micphone.IMicphoneService
    public int startLoopback() throws RemoteException {
        UsbMicDevice.loopbackStart();
        return 0;
    }

    @Override // android.micphone.IMicphoneService
    public int stopLoopback() throws RemoteException {
        UsbMicDevice.loopbackStop();
        return 0;
    }

    @Override // android.micphone.IMicphoneService
    public int switchEffect(int i) throws RemoteException {
        return UsbMicDevice.usbMicSwitchEffect(i);
    }

    @Override // android.micphone.IMicphoneService
    public void unregisterMicphoneNotificationCallback(IMicphoneNotificationCallback iMicphoneNotificationCallback) throws RemoteException {
        ICallbackListener iCallbackListener = this.mListener;
        if (iCallbackListener != null) {
            iCallbackListener.unregisterCallback(iMicphoneNotificationCallback);
        }
    }

    @Override // android.micphone.IMicphoneService
    public int upgrade(byte[] bArr) throws RemoteException {
        return UsbMicDevice.upgrade(bArr);
    }
}
