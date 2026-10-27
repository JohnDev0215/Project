package android.car.media;

import android.car.Car;
import android.car.CarManagerBase;
import android.car.media.CarEnhancedAudioManager;
import android.car.media.ICarBalanceCallback;
import android.car.media.ICarEnhancedAudio;
import android.car.media.ICarFocusCallback;
import android.car.media.ICarGroupMutingCallback;
import android.media.AudioFocusInfo;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.IntPredicate;

/* JADX INFO: loaded from: classes.dex */
public final class CarEnhancedAudioManager extends CarManagerBase {
    private static final String TAG = CarEnhancedAudioManager.class.getSimpleName();
    private final ICarBalanceCallback mCarBalanceCallbackImpl;
    private final CopyOnWriteArrayList<CarBalanceCallback> mCarBalanceCallbacks;
    private final ICarEnhancedAudio mCarEnhancedAudioService;
    private final ICarFocusCallback mCarFocusCallbackImpl;
    private final Map<CarFocusCallback, int[]> mCarFocusCallbackMap;
    private final ICarGroupMutingCallback mCarGroupMutingCallbackImpl;
    private final CopyOnWriteArrayList<CarGroupMutingCallback> mCarGroupMutingCallbacks;

    public static abstract class CarBalanceCallback {
        public void onBalanceTowardRightLevelChanged() {
        }

        public void onFadeTowardFrontLevelChanged() {
        }
    }

    public static abstract class CarFocusCallback {
        public void onFocusChanged(int i, List<AudioFocusInfo> list) {
        }
    }

    public static abstract class CarGroupMutingCallback {
        public void onGroupMuteChanged(int i, int i2, int i3) {
        }
    }

    /* JADX INFO: renamed from: android.car.media.CarEnhancedAudioManager$1, reason: invalid class name */
    class AnonymousClass1 extends ICarFocusCallback.Stub {
        static /* synthetic */ boolean lambda$onFocusChanged$0(int i, int i2) {
            return i2 == i;
        }

        AnonymousClass1() {
        }

        @Override // android.car.media.ICarFocusCallback
        public void onFocusChanged(final int i, List<AudioFocusInfo> list) {
            for (CarFocusCallback carFocusCallback : CarEnhancedAudioManager.this.mCarFocusCallbackMap.keySet()) {
                if (Arrays.stream((int[]) CarEnhancedAudioManager.this.mCarFocusCallbackMap.get(carFocusCallback)).anyMatch(new IntPredicate() { // from class: android.car.media.-$$Lambda$CarEnhancedAudioManager$1$LdVlEQqkRwhBPvwWiM5z6EEOTTs
                    @Override // java.util.function.IntPredicate
                    public final boolean test(int i2) {
                        return CarEnhancedAudioManager.AnonymousClass1.lambda$onFocusChanged$0(i, i2);
                    }
                })) {
                    carFocusCallback.onFocusChanged(i, list);
                }
            }
        }
    }

    public CarEnhancedAudioManager(Car car, IBinder iBinder) {
        super(car);
        this.mCarFocusCallbackImpl = new AnonymousClass1();
        this.mCarGroupMutingCallbackImpl = new ICarGroupMutingCallback.Stub() { // from class: android.car.media.CarEnhancedAudioManager.2
            @Override // android.car.media.ICarGroupMutingCallback
            public void onGroupMuteChanged(int i, int i2, int i3) {
                Iterator it = CarEnhancedAudioManager.this.mCarGroupMutingCallbacks.iterator();
                while (it.hasNext()) {
                    ((CarGroupMutingCallback) it.next()).onGroupMuteChanged(i, i2, i3);
                }
            }
        };
        this.mCarBalanceCallbackImpl = new ICarBalanceCallback.Stub() { // from class: android.car.media.CarEnhancedAudioManager.3
            @Override // android.car.media.ICarBalanceCallback
            public void onBalanceTowardRightLevelChanged() {
                Iterator it = CarEnhancedAudioManager.this.mCarBalanceCallbacks.iterator();
                while (it.hasNext()) {
                    ((CarBalanceCallback) it.next()).onBalanceTowardRightLevelChanged();
                }
            }

            @Override // android.car.media.ICarBalanceCallback
            public void onFadeTowardFrontLevelChanged() {
                Iterator it = CarEnhancedAudioManager.this.mCarBalanceCallbacks.iterator();
                while (it.hasNext()) {
                    ((CarBalanceCallback) it.next()).onFadeTowardFrontLevelChanged();
                }
            }
        };
        this.mCarEnhancedAudioService = ICarEnhancedAudio.Stub.asInterface(iBinder);
        this.mCarFocusCallbackMap = new HashMap();
        this.mCarGroupMutingCallbacks = new CopyOnWriteArrayList<>();
        this.mCarBalanceCallbacks = new CopyOnWriteArrayList<>();
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
        if (this.mCarEnhancedAudioService != null) {
            unregisterGroupMutingCallback();
            unregisterBalanceCallback();
            unregisterFocusCallback();
        }
    }

    public ICarAudioControl getCarAudioControlInstance() {
        try {
            return this.mCarEnhancedAudioService.getCarAudioControlInstance();
        } catch (RemoteException e) {
            return (ICarAudioControl) handleRemoteExceptionFromCarService(e, null);
        }
    }

    public ICarEqualizer getCarEqualizerInstance() {
        try {
            return this.mCarEnhancedAudioService.getCarEqualizerInstance();
        } catch (RemoteException e) {
            return (ICarEqualizer) handleRemoteExceptionFromCarService(e, null);
        }
    }

    public List<AudioFocusInfo> getAudioFocusHoldersForZoneId(int i) {
        ArrayList arrayList = new ArrayList();
        try {
            return this.mCarEnhancedAudioService.getAudioFocusHoldersForZoneId(i);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
            return arrayList;
        }
    }

    public List<AudioFocusInfo> getAudioFocusLosersForZoneId(int i) {
        ArrayList arrayList = new ArrayList();
        try {
            return this.mCarEnhancedAudioService.getAudioFocusLosersForZoneId(i);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
            return arrayList;
        }
    }

    public boolean isPlaybackOnVolumeGroupActive(int i, int i2) {
        try {
            return this.mCarEnhancedAudioService.isPlaybackOnVolumeGroupActive(i, i2);
        } catch (RemoteException e) {
            return ((Boolean) handleRemoteExceptionFromCarService(e, false)).booleanValue();
        }
    }

    public void registerCarFocusCallback(CarFocusCallback carFocusCallback, int[] iArr) {
        Objects.requireNonNull(carFocusCallback);
        Objects.requireNonNull(iArr);
        if (this.mCarFocusCallbackMap.isEmpty()) {
            registerFocusCallback();
        }
        if (this.mCarFocusCallbackMap.containsKey(carFocusCallback)) {
            this.mCarFocusCallbackMap.replace(carFocusCallback, iArr);
        } else {
            this.mCarFocusCallbackMap.put(carFocusCallback, iArr);
        }
    }

    public void unregisterCarFocusCallback(CarFocusCallback carFocusCallback) {
        Objects.requireNonNull(carFocusCallback);
        this.mCarFocusCallbackMap.remove(carFocusCallback);
        if (this.mCarFocusCallbackMap.isEmpty()) {
            unregisterFocusCallback();
        }
    }

    public void registerCarGroupMutingCallback(CarGroupMutingCallback carGroupMutingCallback) {
        Objects.requireNonNull(carGroupMutingCallback);
        if (this.mCarGroupMutingCallbacks.isEmpty()) {
            registerGroupMutingCallback();
        }
        this.mCarGroupMutingCallbacks.add(carGroupMutingCallback);
    }

    public void unregisterCarGroupMutingCallback(CarGroupMutingCallback carGroupMutingCallback) {
        Objects.requireNonNull(carGroupMutingCallback);
        if (this.mCarGroupMutingCallbacks.remove(carGroupMutingCallback) && this.mCarGroupMutingCallbacks.isEmpty()) {
            unregisterGroupMutingCallback();
        }
    }

    public void registerCarBalanceCallback(CarBalanceCallback carBalanceCallback) {
        Objects.requireNonNull(carBalanceCallback);
        if (this.mCarBalanceCallbacks.isEmpty()) {
            registerBalanceCallback();
        }
        this.mCarBalanceCallbacks.add(carBalanceCallback);
    }

    public void unregisterCarBalanceCallback(CarBalanceCallback carBalanceCallback) {
        Objects.requireNonNull(carBalanceCallback);
        if (this.mCarBalanceCallbacks.remove(carBalanceCallback) && this.mCarBalanceCallbacks.isEmpty()) {
            unregisterBalanceCallback();
        }
    }

    public void setVolumeGroupMute(int i, int i2, boolean z, int i3) {
        try {
            this.mCarEnhancedAudioService.setVolumeGroupMute(i, i2, z, i3);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public boolean isVolumeGroupMuted(int i, int i2) {
        try {
            return this.mCarEnhancedAudioService.isVolumeGroupMuted(i, i2);
        } catch (RemoteException e) {
            return ((Boolean) handleRemoteExceptionFromCarService(e, false)).booleanValue();
        }
    }

    public void adjustVolume(int i, int i2, int i3, int i4) {
        try {
            this.mCarEnhancedAudioService.adjustVolume(i, i2, i3, i4);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public void adjustSuggestedGroupVolume(int i, int i2, int i3, int i4, int i5) {
        try {
            this.mCarEnhancedAudioService.adjustSuggestedGroupVolume(i, i2, i3, i4, i5);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public void adjustSuggestedGroupVolumeToMaximum(int i, int i2, int i3) {
        try {
            this.mCarEnhancedAudioService.adjustSuggestedGroupVolumeToMaximum(i, i2, i3);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public void adjustSuggestedGroupVolumeToMinimum(int i, int i2, int i3) {
        try {
            this.mCarEnhancedAudioService.adjustSuggestedGroupVolumeToMinimum(i, i2, i3);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public void allowBootVolumeAutoAdjustment(int i, boolean z) {
        try {
            this.mCarEnhancedAudioService.allowBootVolumeAutoAdjustment(i, z);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public boolean isBootVolumeAutoAdjustmentAllowed(int i) {
        try {
            return this.mCarEnhancedAudioService.isBootVolumeAutoAdjustmentAllowed(i);
        } catch (RemoteException e) {
            return ((Boolean) handleRemoteExceptionFromCarService(e, false)).booleanValue();
        }
    }

    public void duckAudio(int i, int i2) {
        try {
            this.mCarEnhancedAudioService.duckAudio(i, i2);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public void unduckAudio(int i) {
        try {
            this.mCarEnhancedAudioService.unduckAudio(i);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public void setBalanceTowardRightLevel(int i) {
        try {
            this.mCarEnhancedAudioService.setBalanceTowardRightLevel(i);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public int getBalanceTowardRightLevel() {
        try {
            return this.mCarEnhancedAudioService.getBalanceTowardRightLevel();
        } catch (RemoteException e) {
            return ((Integer) handleRemoteExceptionFromCarService(e, 0)).intValue();
        }
    }

    public int[] getBalanceTowardRightLevelRange() {
        try {
            return this.mCarEnhancedAudioService.getBalanceTowardRightLevelRange();
        } catch (RemoteException e) {
            return (int[]) handleRemoteExceptionFromCarService(e, new int[2]);
        }
    }

    public void setFadeTowardFrontLevel(int i) {
        try {
            this.mCarEnhancedAudioService.setFadeTowardFrontLevel(i);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public int getFadeTowardFrontLevel() {
        try {
            return this.mCarEnhancedAudioService.getFadeTowardFrontLevel();
        } catch (RemoteException e) {
            return ((Integer) handleRemoteExceptionFromCarService(e, 0)).intValue();
        }
    }

    public int[] getFadeTowardFrontLevelRange() {
        try {
            return this.mCarEnhancedAudioService.getFadeTowardFrontLevelRange();
        } catch (RemoteException e) {
            return (int[]) handleRemoteExceptionFromCarService(e, new int[2]);
        }
    }

    public boolean isBalanceTowardRightSupported() {
        try {
            return this.mCarEnhancedAudioService.isBalanceTowardRightSupported();
        } catch (RemoteException e) {
            return ((Boolean) handleRemoteExceptionFromCarService(e, false)).booleanValue();
        }
    }

    public boolean isFadeTowardFrontSupported() {
        try {
            return this.mCarEnhancedAudioService.isFadeTowardFrontSupported();
        } catch (RemoteException e) {
            return ((Boolean) handleRemoteExceptionFromCarService(e, false)).booleanValue();
        }
    }

    private void registerFocusCallback() {
        try {
            this.mCarEnhancedAudioService.registerFocusCallback(this.mCarFocusCallbackImpl.asBinder());
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    private void unregisterFocusCallback() {
        try {
            this.mCarEnhancedAudioService.unregisterFocusCallback(this.mCarFocusCallbackImpl.asBinder());
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    private void registerGroupMutingCallback() {
        try {
            this.mCarEnhancedAudioService.registerGroupMutingCallback(this.mCarGroupMutingCallbackImpl.asBinder());
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    private void unregisterGroupMutingCallback() {
        try {
            this.mCarEnhancedAudioService.unregisterGroupMutingCallback(this.mCarGroupMutingCallbackImpl.asBinder());
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    private void registerBalanceCallback() {
        try {
            this.mCarEnhancedAudioService.registerBalanceCallback(this.mCarBalanceCallbackImpl.asBinder());
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    private void unregisterBalanceCallback() {
        try {
            this.mCarEnhancedAudioService.unregisterBalanceCallback(this.mCarBalanceCallbackImpl.asBinder());
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }
}
