package com.byd.dilink50.media;

import android.car.Car;
import android.content.Context;
import android.media.AudioManager;
import android.media.VehicleAudioStateManager;
import android.util.Log;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.MemorySafeContext;
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
@Platform(versions = {DiLink.VERSION_NAME_5_0})
@ServiceImpl(service = IDiLinkAudioManager.class, singleton = true)
public class DiLinkAudioManager implements IDiLinkAudioManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private AudioManager f1414a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DiLink5_0_OnDenoiseFocusChangeListener f1415b = new DiLink5_0_OnDenoiseFocusChangeListener();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private DiLink5_0_CallbackHolders f1416c = new DiLink5_0_CallbackHolders();

    public static class DiLink5_0_CallbackHolders extends DiLinkCallbackHolders<IDiLinkAudioStateCallback, VehicleAudioStateManager.VehicleAudioStateCallback> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public VehicleAudioStateManager.VehicleAudioStateCallback createInnerCallback(final IDiLinkAudioStateCallback iDiLinkAudioStateCallback) {
            return new VehicleAudioStateManager.VehicleAudioStateCallback() { // from class: com.byd.dilink50.media.DiLinkAudioManager.DiLink5_0_CallbackHolders.1
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

    public static class DiLink5_0_OnDenoiseFocusChangeListener extends DiLinkCallbackHolders<IDiLinkOnDenoiseFocusChangeListener, AudioManager.OnDenoiseFocusChangeListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public AudioManager.OnDenoiseFocusChangeListener createInnerCallback(final IDiLinkOnDenoiseFocusChangeListener iDiLinkOnDenoiseFocusChangeListener) {
            return new AudioManager.OnDenoiseFocusChangeListener() { // from class: com.byd.dilink50.media.DiLinkAudioManager.DiLink5_0_OnDenoiseFocusChangeListener.1
                @Keep
                public void OnDenoiseFocusChange(int i) {
                    iDiLinkOnDenoiseFocusChangeListener.OnDenoiseFocusChange(i);
                }
            };
        }
    }

    public DiLinkAudioManager(Context context) {
        this.f1414a = (AudioManager) context.getSystemService(Car.AUDIO_SERVICE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(Context context, VehicleAudioStateManager.VehicleAudioStateCallback vehicleAudioStateCallback) {
        VehicleAudioStateManager.getInstance(MemorySafeContext.get(context)).registerVehicleAudioStateCallback(vehicleAudioStateCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(AtomicInteger atomicInteger, int i, AudioManager.OnDenoiseFocusChangeListener onDenoiseFocusChangeListener) {
        atomicInteger.set(this.f1414a.releaseDenoiseMode(i, onDenoiseFocusChangeListener));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void c(Context context, VehicleAudioStateManager.VehicleAudioStateCallback vehicleAudioStateCallback) {
        VehicleAudioStateManager.getInstance(MemorySafeContext.get(context)).unregisterVehicleAudioStateCallback(vehicleAudioStateCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(AtomicInteger atomicInteger, int i, AudioManager.OnDenoiseFocusChangeListener onDenoiseFocusChangeListener) {
        atomicInteger.set(this.f1414a.requestDenoiseMode(i, onDenoiseFocusChangeListener));
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int changeAudioSlotStateOnAccOffState(int i, boolean z) {
        throw new UnsupportedTargetPlatformException("Di5.0 没有 changeAudioSlotStateOnAccOffState方法");
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    @NonNull
    public String getCurrentAudioFocusPackage() {
        return this.f1414a.getCurrentAudioFocusPackage();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public String getCurrentAudioFocusPackage(int i) {
        throw new UnsupportedTargetPlatformException("5.0平台不存在该接口!");
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    @NonNull
    public int getCurrentAudioFocusStream() {
        return this.f1414a.getCurrentAudioFocusStream();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    @NonNull
    public int getCurrentAudioFocusStream(int i) {
        return this.f1414a.getCurrentAudioFocusStream(i);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int getCurrentAudioFocusStream(@NonNull String str) {
        return this.f1414a.getCurrentAudioFocusStream(str);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int getHeadUnitOutputDevice() {
        return this.f1414a.getHeadUnitOutputDevice();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public boolean getMuteState(int i) {
        return this.f1414a.getMuteState(i);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public boolean getNaviMuteState() {
        return this.f1414a.getNaviMuteState();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int getPlayMode() {
        return this.f1414a.getPlayMode();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int getPlayProgress() {
        return this.f1414a.getPlayProgress();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int getPlayState() {
        return this.f1414a.getPlayState();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int getProjectVideoModeHost(Context context) {
        return VehicleAudioStateManager.getInstance(MemorySafeContext.get(context)).getProjectVideoModeHost();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int getSecondaryAudioFocusStream() {
        return this.f1414a.getSecondaryAudioFocusStream();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int getStreamVolume(int i, int i2) {
        return this.f1414a.getStreamVolume(i, i2);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public String getTargetDeviceAudioFocusPackage(Context context, int i) {
        return VehicleAudioStateManager.getInstance(MemorySafeContext.get(context)).getTargetDeviceAudioFocusPackage(i);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public boolean getTargetDeviceMusicPlayState(Context context, int i) {
        return VehicleAudioStateManager.getInstance(MemorySafeContext.get(context)).getTargetDeviceMusicPlayState(i);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int getTargetDeviceUseSpeakerState(Context context, int i) {
        return VehicleAudioStateManager.getInstance(MemorySafeContext.get(context)).getTargetDeviceUseSpeakerState(i);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int getVehiclePhoneState(Context context) {
        return VehicleAudioStateManager.getInstance(MemorySafeContext.get(context)).getVehiclePhoneState();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public boolean getVehicleSpeakerPermitState(Context context) {
        return VehicleAudioStateManager.getInstance(MemorySafeContext.get(context)).getVehicleSpeakerPermitState();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public boolean isCurrentSystemUseVehicleSpeaker(Context context) {
        return VehicleAudioStateManager.getInstance(MemorySafeContext.get(context)).isCurrentSystemUseVehicleSpeaker();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public boolean isProjectVideoMode(Context context) {
        return VehicleAudioStateManager.getInstance(MemorySafeContext.get(context)).isProjectVideoMode();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public boolean isStreamAllowed(int i, @NonNull String str) {
        return this.f1414a.isStreamAllowed(i, str);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void registerDiLinkAudioStateCallback(final Context context, IDiLinkAudioStateCallback iDiLinkAudioStateCallback) {
        this.f1416c.addCallbackIgnoreRemoteException(iDiLinkAudioStateCallback, new IDiLinkCallbackHandler() { // from class: com.byd.dilink50.media.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                DiLinkAudioManager.a(context, (VehicleAudioStateManager.VehicleAudioStateCallback) obj);
            }
        });
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int releaseDenoiseMode(final int i, IDiLinkOnDenoiseFocusChangeListener iDiLinkOnDenoiseFocusChangeListener) {
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        this.f1415b.removeCallbackIgnoreRemoteException(iDiLinkOnDenoiseFocusChangeListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink50.media.d
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1426a.b(atomicInteger, i, (AudioManager.OnDenoiseFocusChangeListener) obj);
            }
        });
        return atomicInteger.get();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int requestDenoiseMode(final int i, IDiLinkOnDenoiseFocusChangeListener iDiLinkOnDenoiseFocusChangeListener) {
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        if (!this.f1415b.addCallbackIgnoreRemoteException(iDiLinkOnDenoiseFocusChangeListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink50.media.c
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1423a.d(atomicInteger, i, (AudioManager.OnDenoiseFocusChangeListener) obj);
            }
        })) {
            Log.e("DiLinkAudioManager", "requestDenoiseMode方法执行失败");
        }
        return atomicInteger.get();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int requestUseVehicleSpeaker(Context context) {
        return VehicleAudioStateManager.getInstance(MemorySafeContext.get(context)).requestUseVehicleSpeaker();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int setAutoVoiceActiveState(boolean z, boolean z2) {
        return this.f1414a.setAutoVoiceActiveState(z, z2);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setCallState(int i, int i2) {
        this.f1414a.setCallState(i2, i2);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setHeadUnitOutputDevice(int i) {
        this.f1414a.setHeadUnitOutputDevice(i);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setMediaType(int i) {
        this.f1414a.setMediaType(i);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setMuteState(int i, boolean z) {
        this.f1414a.setMuteState(i, z);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setNaviMuteState(boolean z) {
        this.f1414a.setNaviMuteState(z);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setPlayMode(int i, int i2) {
        this.f1414a.setPlayMode(i, i2);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setPlayProgress(int i, int i2) {
        this.f1414a.setPlayProgress(i, i2);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setPlayState(int i, int i2) {
        this.f1414a.setPlayState(i, i2);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setProjectVideoMode(Context context, boolean z, int i) {
        VehicleAudioStateManager.getInstance(MemorySafeContext.get(context)).setProjectVideoMode(z, i);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setStreamMuteForSystem(int i, boolean z) {
        this.f1414a.setStreamMuteForSystem(i, z);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setStreamVolume(int i, int i2, int i3, int i4, int i5) {
        this.f1414a.setStreamVolume(i, i2, i3, i4, i5);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int setTrackVolumeDuck(boolean z) {
        return this.f1414a.setTrackVolumeDuck(z);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setVehicleSpeakerPermitState(Context context, boolean z) {
        VehicleAudioStateManager.getInstance(MemorySafeContext.get(context)).setVehicleSpeakerPermitState(z);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void startAudioOutput() {
        this.f1414a.startAudioOutput();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void startAudioOutput(@NonNull String str) {
        this.f1414a.startAudioOutput(str);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void stopAudioOutput() {
        this.f1414a.stopAudioOutput();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void stopAudioOutput(@NonNull String str) {
        this.f1414a.stopAudioOutput(str);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void unregisterDiLinkAudioStateCallback(final Context context, IDiLinkAudioStateCallback iDiLinkAudioStateCallback) {
        this.f1416c.removeCallbackIgnoreRemoteException(iDiLinkAudioStateCallback, new IDiLinkCallbackHandler() { // from class: com.byd.dilink50.media.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                DiLinkAudioManager.c(context, (VehicleAudioStateManager.VehicleAudioStateCallback) obj);
            }
        });
    }
}
