package com.byd.dilink51_main.media;

import android.car.Car;
import android.content.Context;
import android.media.AudioManager;
import android.media.VehicleAudioStateManager;
import android.util.Log;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.callbackholder.DiLinkCallbackHolders;
import com.byd.dilink.callbackholder.IDiLinkCallbackHandler;
import com.byd.dilink.media.IDiLinkAudioManager;
import com.byd.dilink.media.IDiLinkAudioStateCallback;
import com.byd.dilink.media.IDiLinkOnDenoiseFocusChangeListener;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkAudioManager.class, singleton = true)
public class DiLinkAudioManager implements IDiLinkAudioManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private AudioManager f1795a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DiLink5_1_CallbackHolders f1796b = new DiLink5_1_CallbackHolders();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private DiLink5_1_OnDenoiseFocusChangeListener f1797c = new DiLink5_1_OnDenoiseFocusChangeListener();

    public static class DiLink5_1_CallbackHolders extends DiLinkCallbackHolders<IDiLinkAudioStateCallback, VehicleAudioStateManager.VehicleAudioStateCallback> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public VehicleAudioStateManager.VehicleAudioStateCallback createInnerCallback(final IDiLinkAudioStateCallback iDiLinkAudioStateCallback) {
            return new VehicleAudioStateManager.VehicleAudioStateCallback() { // from class: com.byd.dilink51_main.media.DiLinkAudioManager.DiLink5_1_CallbackHolders.1
                @Keep
                public void onAnyDeviceAudioFocusPackageChanged(int i, String str) {
                    iDiLinkAudioStateCallback.onAnyDeviceAudioFocusPackageChanged(i, str);
                }

                @Keep
                public void onAnyDeviceMusicPlayStateChanged(int i, boolean z) {
                    iDiLinkAudioStateCallback.onAnyDeviceMusicPlayStateChanged(i, z);
                }

                @Keep
                public void onAnyDeviceUseSpeakerStateChanged(int i, int i2) {
                    iDiLinkAudioStateCallback.onAnyDeviceUseSpeakerStateChanged(i, i2);
                }

                @Keep
                public void onIVISwitchStateChanged(boolean z) {
                    iDiLinkAudioStateCallback.onIVISwitchStateChanged(z);
                }

                @Keep
                public void onPhoneStateChanged(int i) {
                    iDiLinkAudioStateCallback.onPhoneStateChanged(i);
                }

                @Keep
                public void onProjectVideoModeChanged(boolean z, int i) {
                    iDiLinkAudioStateCallback.onProjectVideoModeChanged(z, i);
                }

                @Keep
                public void onUseVehicleSpeakerClientChanged(int i) {
                    iDiLinkAudioStateCallback.onUseVehicleSpeakerClientChanged(i);
                }
            };
        }
    }

    public static class DiLink5_1_OnDenoiseFocusChangeListener extends DiLinkCallbackHolders<IDiLinkOnDenoiseFocusChangeListener, AudioManager.OnDenoiseFocusChangeListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public AudioManager.OnDenoiseFocusChangeListener createInnerCallback(final IDiLinkOnDenoiseFocusChangeListener iDiLinkOnDenoiseFocusChangeListener) {
            return new AudioManager.OnDenoiseFocusChangeListener() { // from class: com.byd.dilink51_main.media.DiLinkAudioManager.DiLink5_1_OnDenoiseFocusChangeListener.1
                @Keep
                public void OnDenoiseFocusChange(int i) {
                    iDiLinkOnDenoiseFocusChangeListener.OnDenoiseFocusChange(i);
                }
            };
        }
    }

    public DiLinkAudioManager(Context context) {
        this.f1795a = (AudioManager) context.getSystemService(Car.AUDIO_SERVICE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(Context context, VehicleAudioStateManager.VehicleAudioStateCallback vehicleAudioStateCallback) {
        VehicleAudioStateManager.getInstance(context).registerVehicleAudioStateCallback(vehicleAudioStateCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(AtomicInteger atomicInteger, int i, AudioManager.OnDenoiseFocusChangeListener onDenoiseFocusChangeListener) {
        atomicInteger.set(this.f1795a.releaseDenoiseMode(i, onDenoiseFocusChangeListener));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void c(Context context, VehicleAudioStateManager.VehicleAudioStateCallback vehicleAudioStateCallback) {
        VehicleAudioStateManager.getInstance(context).unregisterVehicleAudioStateCallback(vehicleAudioStateCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(AtomicInteger atomicInteger, int i, AudioManager.OnDenoiseFocusChangeListener onDenoiseFocusChangeListener) {
        atomicInteger.set(this.f1795a.requestDenoiseMode(i, onDenoiseFocusChangeListener));
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int changeAudioSlotStateOnAccOffState(int i, boolean z) {
        throw new UnsupportedTargetPlatformException("Di5.1 没有 changeAudioSlotStateOnAccOffState方法");
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    @NonNull
    public String getCurrentAudioFocusPackage() {
        return this.f1795a.getCurrentAudioFocusPackage();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public String getCurrentAudioFocusPackage(int i) {
        return this.f1795a.getCurrentAudioFocusPackage(i);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    @NonNull
    public int getCurrentAudioFocusStream() {
        return this.f1795a.getCurrentAudioFocusStream();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    @NonNull
    public int getCurrentAudioFocusStream(int i) {
        return this.f1795a.getCurrentAudioFocusStream(i);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int getCurrentAudioFocusStream(@NonNull String str) {
        return this.f1795a.getCurrentAudioFocusStream(str);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int getHeadUnitOutputDevice() {
        return this.f1795a.getHeadUnitOutputDevice();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public boolean getMuteState(int i) {
        return this.f1795a.getMuteState(i);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public boolean getNaviMuteState() {
        return this.f1795a.getNaviMuteState();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int getPlayMode() {
        return this.f1795a.getPlayMode();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int getPlayProgress() {
        return this.f1795a.getPlayProgress();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int getPlayState() {
        return this.f1795a.getPlayState();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int getProjectVideoModeHost(Context context) {
        return VehicleAudioStateManager.getInstance(context).getProjectVideoModeHost();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int getSecondaryAudioFocusStream() {
        return this.f1795a.getSecondaryAudioFocusStream();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int getStreamVolume(int i, int i2) {
        return this.f1795a.getStreamVolume(i, i2);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public String getTargetDeviceAudioFocusPackage(Context context, int i) {
        return VehicleAudioStateManager.getInstance(context).getTargetDeviceAudioFocusPackage(i);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public boolean getTargetDeviceMusicPlayState(Context context, int i) {
        return VehicleAudioStateManager.getInstance(context).getTargetDeviceMusicPlayState(i);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int getTargetDeviceUseSpeakerState(Context context, int i) {
        return VehicleAudioStateManager.getInstance(context).getTargetDeviceUseSpeakerState(i);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int getVehiclePhoneState(Context context) {
        return VehicleAudioStateManager.getInstance(context).getVehiclePhoneState();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public boolean getVehicleSpeakerPermitState(Context context) {
        return VehicleAudioStateManager.getInstance(context).getVehicleSpeakerPermitState();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public boolean isCurrentSystemUseVehicleSpeaker(Context context) {
        return VehicleAudioStateManager.getInstance(context).isCurrentSystemUseVehicleSpeaker();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public boolean isProjectVideoMode(Context context) {
        return VehicleAudioStateManager.getInstance(context).isProjectVideoMode();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public boolean isStreamAllowed(int i, @NonNull String str) {
        return this.f1795a.isStreamAllowed(i, str);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void registerDiLinkAudioStateCallback(final Context context, IDiLinkAudioStateCallback iDiLinkAudioStateCallback) {
        this.f1796b.addCallbackIgnoreRemoteException(iDiLinkAudioStateCallback, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.media.d
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                DiLinkAudioManager.a(context, (VehicleAudioStateManager.VehicleAudioStateCallback) obj);
            }
        });
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int releaseDenoiseMode(final int i, IDiLinkOnDenoiseFocusChangeListener iDiLinkOnDenoiseFocusChangeListener) {
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        this.f1797c.removeCallbackIgnoreRemoteException(iDiLinkOnDenoiseFocusChangeListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.media.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1805a.b(atomicInteger, i, (AudioManager.OnDenoiseFocusChangeListener) obj);
            }
        });
        return atomicInteger.get();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int requestDenoiseMode(final int i, IDiLinkOnDenoiseFocusChangeListener iDiLinkOnDenoiseFocusChangeListener) {
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        if (!this.f1797c.addCallbackIgnoreRemoteException(iDiLinkOnDenoiseFocusChangeListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.media.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1802a.d(atomicInteger, i, (AudioManager.OnDenoiseFocusChangeListener) obj);
            }
        })) {
            Log.e("DiLinkAudioManager", "requestDenoiseMode方法执行失败");
        }
        return atomicInteger.get();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int requestUseVehicleSpeaker(Context context) {
        return VehicleAudioStateManager.getInstance(context).requestUseVehicleSpeaker();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int setAutoVoiceActiveState(boolean z, boolean z2) {
        return this.f1795a.setAutoVoiceActiveState(z, z2);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setCallState(int i, int i2) {
        this.f1795a.setCallState(i2, i2);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setHeadUnitOutputDevice(int i) {
        this.f1795a.setHeadUnitOutputDevice(i);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setMediaType(int i) {
        this.f1795a.setMediaType(i);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setMuteState(int i, boolean z) {
        this.f1795a.setMuteState(i, z);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setNaviMuteState(boolean z) {
        this.f1795a.setNaviMuteState(z);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setPlayMode(int i, int i2) {
        this.f1795a.setPlayMode(i, i2);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setPlayProgress(int i, int i2) {
        this.f1795a.setPlayProgress(i, i2);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setPlayState(int i, int i2) {
        this.f1795a.setPlayState(i, i2);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setProjectVideoMode(Context context, boolean z, int i) {
        VehicleAudioStateManager.getInstance(context).setProjectVideoMode(z, i);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setStreamMuteForSystem(int i, boolean z) {
        this.f1795a.setStreamMuteForSystem(i, z);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setStreamVolume(int i, int i2, int i3, int i4, int i5) {
        this.f1795a.setStreamVolume(i, i2, i3, i4, i5);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int setTrackVolumeDuck(boolean z) {
        return this.f1795a.setTrackVolumeDuck(z);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setVehicleSpeakerPermitState(Context context, boolean z) {
        VehicleAudioStateManager.getInstance(context).setVehicleSpeakerPermitState(z);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void startAudioOutput() {
        this.f1795a.startAudioOutput();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void startAudioOutput(@NonNull String str) {
        this.f1795a.startAudioOutput(str);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void stopAudioOutput() {
        this.f1795a.stopAudioOutput();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void stopAudioOutput(@NonNull String str) {
        this.f1795a.stopAudioOutput(str);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void unregisterDiLinkAudioStateCallback(final Context context, IDiLinkAudioStateCallback iDiLinkAudioStateCallback) {
        this.f1796b.removeCallbackIgnoreRemoteException(iDiLinkAudioStateCallback, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.media.c
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                DiLinkAudioManager.c(context, (VehicleAudioStateManager.VehicleAudioStateCallback) obj);
            }
        });
    }
}
