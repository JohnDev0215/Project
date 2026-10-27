package com.byd.dilink30.media;

import android.car.Car;
import android.content.Context;
import android.media.AudioManager;
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
@Platform(versions = {DiLink.VERSION_NAME_3_0})
@ServiceImpl(service = IDiLinkAudioManager.class, singleton = true)
public class DiLinkAudioManager implements IDiLinkAudioManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private AudioManager f1226a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DiLink3_0_OnDenoiseFocusChangeListener f1227b = new DiLink3_0_OnDenoiseFocusChangeListener();

    public static class DiLink3_0_OnDenoiseFocusChangeListener extends DiLinkCallbackHolders<IDiLinkOnDenoiseFocusChangeListener, AudioManager.OnDenoiseFocusChangeListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public AudioManager.OnDenoiseFocusChangeListener createInnerCallback(final IDiLinkOnDenoiseFocusChangeListener iDiLinkOnDenoiseFocusChangeListener) {
            return new AudioManager.OnDenoiseFocusChangeListener() { // from class: com.byd.dilink30.media.DiLinkAudioManager.DiLink3_0_OnDenoiseFocusChangeListener.1
                @Keep
                public void OnDenoiseFocusChange(int i) {
                    iDiLinkOnDenoiseFocusChangeListener.OnDenoiseFocusChange(i);
                }
            };
        }
    }

    public DiLinkAudioManager(Context context) {
        this.f1226a = (AudioManager) context.getSystemService(Car.AUDIO_SERVICE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(AtomicInteger atomicInteger, int i, AudioManager.OnDenoiseFocusChangeListener onDenoiseFocusChangeListener) {
        atomicInteger.set(this.f1226a.requestDenoiseMode(onDenoiseFocusChangeListener, i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(AtomicInteger atomicInteger, int i, AudioManager.OnDenoiseFocusChangeListener onDenoiseFocusChangeListener) {
        atomicInteger.set(this.f1226a.releaseDenoiseMode(onDenoiseFocusChangeListener, i));
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int changeAudioSlotStateOnAccOffState(int i, boolean z) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 changeAudioSlotStateOnAccOffState方法");
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    @NonNull
    public String getCurrentAudioFocusPackage() {
        return this.f1226a.getCurrentAudioFocusPackage();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public String getCurrentAudioFocusPackage(int i) {
        throw new UnsupportedTargetPlatformException("3.0平台不存在该接口!");
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    @NonNull
    public int getCurrentAudioFocusStream() {
        return this.f1226a.getCurrentAudioFocusStream();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    @NonNull
    public int getCurrentAudioFocusStream(int i) {
        throw new UnsupportedTargetPlatformException("3.0平台不存在该接口!");
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int getCurrentAudioFocusStream(@NonNull String str) {
        return this.f1226a.getCurrentAudioFocusStream(str);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int getHeadUnitOutputDevice() {
        throw new UnsupportedTargetPlatformException("3.0平台不存在该接口!");
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public boolean getMuteState(int i) {
        return this.f1226a.getMuteState(i);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public boolean getNaviMuteState() {
        return this.f1226a.getNaviMuteState();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int getPlayMode() {
        return this.f1226a.getPlayMode();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int getPlayProgress() {
        return this.f1226a.getPlayProgress();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int getPlayState() {
        return this.f1226a.getPlayState();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int getProjectVideoModeHost(Context context) {
        throw new UnsupportedTargetPlatformException("3.0平台不存在该接口!");
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int getSecondaryAudioFocusStream() {
        return this.f1226a.getSecondaryAudioFocusStream();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int getStreamVolume(int i, int i2) {
        throw new UnsupportedTargetPlatformException("3.0平台不存在该接口!");
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public String getTargetDeviceAudioFocusPackage(Context context, int i) {
        throw new UnsupportedTargetPlatformException("3.0平台不存在该接口!");
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public boolean getTargetDeviceMusicPlayState(Context context, int i) {
        throw new UnsupportedTargetPlatformException("3.0平台不存在该接口!");
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int getTargetDeviceUseSpeakerState(Context context, int i) {
        throw new UnsupportedTargetPlatformException("3.0平台不存在该接口!");
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int getVehiclePhoneState(Context context) {
        throw new UnsupportedTargetPlatformException("3.0平台不存在该接口!");
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public boolean getVehicleSpeakerPermitState(Context context) {
        throw new UnsupportedTargetPlatformException("3.0平台不存在该接口!");
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public boolean isCurrentSystemUseVehicleSpeaker(Context context) {
        throw new UnsupportedTargetPlatformException("3.0平台不存在该接口!");
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public boolean isProjectVideoMode(Context context) {
        throw new UnsupportedTargetPlatformException("3.0平台不存在该接口!");
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public boolean isStreamAllowed(int i, @NonNull String str) {
        return this.f1226a.isStreamAllowed(i, str);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void registerDiLinkAudioStateCallback(Context context, IDiLinkAudioStateCallback iDiLinkAudioStateCallback) {
        throw new UnsupportedTargetPlatformException("3.0平台不存在该接口!");
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int releaseDenoiseMode(final int i, IDiLinkOnDenoiseFocusChangeListener iDiLinkOnDenoiseFocusChangeListener) {
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        this.f1227b.removeCallbackIgnoreRemoteException(iDiLinkOnDenoiseFocusChangeListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink30.media.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1233a.b(atomicInteger, i, (AudioManager.OnDenoiseFocusChangeListener) obj);
            }
        });
        return atomicInteger.get();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int requestDenoiseMode(final int i, IDiLinkOnDenoiseFocusChangeListener iDiLinkOnDenoiseFocusChangeListener) {
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        if (!this.f1227b.addCallbackIgnoreRemoteException(iDiLinkOnDenoiseFocusChangeListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink30.media.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1230a.a(atomicInteger, i, (AudioManager.OnDenoiseFocusChangeListener) obj);
            }
        })) {
            Log.e("DiLinkAudioManager", "requestDenoiseMode方法执行失败");
        }
        return atomicInteger.get();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int requestUseVehicleSpeaker(Context context) {
        throw new UnsupportedTargetPlatformException("3.0平台不存在该接口!");
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int setAutoVoiceActiveState(boolean z, boolean z2) {
        throw new UnsupportedTargetPlatformException("3.0平台不存在该接口!");
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setCallState(int i, int i2) {
        this.f1226a.setCallState(i2, i2);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setHeadUnitOutputDevice(int i) {
        throw new UnsupportedTargetPlatformException("3.0平台不存在该接口!");
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setMediaType(int i) {
        this.f1226a.setMediaType(i);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setMuteState(int i, boolean z) {
        this.f1226a.setMuteState(i, z);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setNaviMuteState(boolean z) {
        this.f1226a.setNaviMuteState(z);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setPlayMode(int i, int i2) {
        this.f1226a.setPlayMode(i, i2);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setPlayProgress(int i, int i2) {
        this.f1226a.setPlayProgress(i, i2);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setPlayState(int i, int i2) {
        this.f1226a.setPlayState(i, i2);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setProjectVideoMode(Context context, boolean z, int i) {
        throw new UnsupportedTargetPlatformException("3.0平台不存在该接口!");
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setStreamMuteForSystem(int i, boolean z) {
        this.f1226a.setStreamMuteForSystem(i, z);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setStreamVolume(int i, int i2, int i3, int i4, int i5) {
        throw new UnsupportedTargetPlatformException("3.0平台不存在该接口!");
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public int setTrackVolumeDuck(boolean z) {
        return this.f1226a.setTrackVolumeDuck(z);
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void setVehicleSpeakerPermitState(Context context, boolean z) {
        throw new UnsupportedTargetPlatformException("3.0平台不存在该接口!");
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void startAudioOutput() {
        this.f1226a.startAudioOutput();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void startAudioOutput(@NonNull String str) {
        throw new UnsupportedTargetPlatformException("3.0平台不存在该接口!");
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void stopAudioOutput() {
        this.f1226a.stopAudioOutput();
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void stopAudioOutput(@NonNull String str) {
        throw new UnsupportedTargetPlatformException("3.0平台不存在该接口!");
    }

    @Override // com.byd.dilink.media.IDiLinkAudioManager
    public void unregisterDiLinkAudioStateCallback(Context context, IDiLinkAudioStateCallback iDiLinkAudioStateCallback) {
        throw new UnsupportedTargetPlatformException("3.0平台不存在该接口!");
    }
}
