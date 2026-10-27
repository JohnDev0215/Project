package com.android.car.audio;

import android.media.AudioDevicePort;
import android.util.Log;
import android.util.SparseArray;
import com.android.car.CarLog;
import com.android.internal.util.Preconditions;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

/* JADX INFO: loaded from: classes3.dex */
final class CarVolumeGroup {
    private final Map<String, CarAudioDeviceInfo> mAddressToCarAudioDeviceInfo;
    private final SparseArray<String> mContextToAddress;
    private int mCurrentGainIndex;
    private int mDefaultGain;
    private final int mId;
    private final Object mLock;
    private int mMaxGain;
    private int mMinGain;
    private CarAudioSettings mSettingsManager;
    private int mStepSize;
    private int mStoredGainIndex;
    private int mUserId;
    private final int mZoneId;

    CarVolumeGroup(CarAudioSettings settings, int zoneId, int id) {
        this.mContextToAddress = new SparseArray<>();
        this.mAddressToCarAudioDeviceInfo = new HashMap();
        this.mLock = new Object();
        this.mDefaultGain = Integer.MIN_VALUE;
        this.mMaxGain = Integer.MIN_VALUE;
        this.mMinGain = Integer.MAX_VALUE;
        this.mStepSize = 0;
        this.mCurrentGainIndex = -1;
        this.mUserId = -2;
        this.mSettingsManager = settings;
        this.mZoneId = zoneId;
        this.mId = id;
        this.mStoredGainIndex = settings.getStoredVolumeGainIndexForUser(-2, zoneId, id);
    }

    @Deprecated
    CarVolumeGroup(CarAudioSettings settings, int zoneId, int id, int[] contexts) {
        this(settings, zoneId, id);
        for (int audioContext : contexts) {
            this.mContextToAddress.put(audioContext, null);
        }
    }

    CarAudioDeviceInfo getCarAudioDeviceInfoForAddress(String address) {
        return this.mAddressToCarAudioDeviceInfo.get(address);
    }

    int[] getContexts() {
        int[] carAudioContexts = new int[this.mContextToAddress.size()];
        for (int i = 0; i < carAudioContexts.length; i++) {
            carAudioContexts[i] = this.mContextToAddress.keyAt(i);
        }
        return carAudioContexts;
    }

    String getAddressForContext(int audioContext) {
        return this.mContextToAddress.get(audioContext);
    }

    int[] getContextsForAddress(String address) {
        List<Integer> carAudioContexts = new ArrayList<>();
        for (int i = 0; i < this.mContextToAddress.size(); i++) {
            String value = this.mContextToAddress.valueAt(i);
            if (address.equals(value)) {
                carAudioContexts.add(Integer.valueOf(this.mContextToAddress.keyAt(i)));
            }
        }
        return carAudioContexts.stream().mapToInt(new ToIntFunction() { // from class: com.android.car.audio.-$$Lambda$CarVolumeGroup$MBr0Vwn5rQqGb9ThXpVdJ4MkIIg
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((Integer) obj).intValue();
            }
        }).toArray();
    }

    List<String> getAddresses() {
        return new ArrayList(this.mAddressToCarAudioDeviceInfo.keySet());
    }

    void bind(int carAudioContext, CarAudioDeviceInfo info) {
        boolean z = true;
        Preconditions.checkArgument(this.mContextToAddress.get(carAudioContext) == null, String.format("Context %s has already been bound to %s", CarAudioContext.toString(carAudioContext), this.mContextToAddress.get(carAudioContext)));
        synchronized (this.mLock) {
            if (this.mAddressToCarAudioDeviceInfo.size() == 0) {
                this.mStepSize = info.getStepValue();
            } else {
                if (info.getStepValue() != this.mStepSize) {
                    z = false;
                }
                Preconditions.checkArgument(z, "Gain controls within one group must have same step value");
            }
            this.mAddressToCarAudioDeviceInfo.put(info.getAddress(), info);
            this.mContextToAddress.put(carAudioContext, info.getAddress());
            if (info.getDefaultGain() > this.mDefaultGain) {
                this.mDefaultGain = info.getDefaultGain();
            }
            if (info.getMaxGain() > this.mMaxGain) {
                this.mMaxGain = info.getMaxGain();
            }
            if (info.getMinGain() < this.mMinGain) {
                this.mMinGain = info.getMinGain();
            }
            updateCurrentGainIndexLocked();
        }
    }

    private void updateUserIdLocked(int userId) {
        this.mUserId = userId;
        this.mStoredGainIndex = getCurrentGainIndexForUserLocked();
    }

    private int getCurrentGainIndexForUserLocked() {
        int gainIndexForUser = this.mSettingsManager.getStoredVolumeGainIndexForUser(this.mUserId, this.mZoneId, this.mId);
        Log.i(CarLog.TAG_AUDIO, "updateUserId userId " + this.mUserId + " gainIndexForUser " + gainIndexForUser);
        return gainIndexForUser;
    }

    private void updateCurrentGainIndexLocked() {
        synchronized (this.mLock) {
            if (this.mStoredGainIndex < getIndexForGainLocked(this.mMinGain) || this.mStoredGainIndex > getIndexForGainLocked(this.mMaxGain)) {
                this.mCurrentGainIndex = getIndexForGainLocked(this.mDefaultGain);
            } else {
                this.mCurrentGainIndex = this.mStoredGainIndex;
            }
        }
    }

    private int getDefaultGainIndex() {
        int indexForGainLocked;
        synchronized (this.mLock) {
            indexForGainLocked = getIndexForGainLocked(this.mDefaultGain);
        }
        return indexForGainLocked;
    }

    int getMaxGainIndex() {
        int indexForGainLocked;
        synchronized (this.mLock) {
            indexForGainLocked = getIndexForGainLocked(this.mMaxGain);
        }
        return indexForGainLocked;
    }

    int getMinGainIndex() {
        int indexForGainLocked;
        synchronized (this.mLock) {
            indexForGainLocked = getIndexForGainLocked(this.mMinGain);
        }
        return indexForGainLocked;
    }

    int getCurrentGainIndex() {
        int i;
        synchronized (this.mLock) {
            i = this.mCurrentGainIndex;
        }
        return i;
    }

    void setCurrentGainIndex(int gainIndex) {
        synchronized (this.mLock) {
            int gainInMillibels = getGainForIndexLocked(gainIndex);
            Preconditions.checkArgument(gainInMillibels >= this.mMinGain && gainInMillibels <= this.mMaxGain, "Gain out of range (" + this.mMinGain + ":" + this.mMaxGain + ") " + gainInMillibels + "index " + gainIndex);
            for (String address : this.mAddressToCarAudioDeviceInfo.keySet()) {
                CarAudioDeviceInfo info = this.mAddressToCarAudioDeviceInfo.get(address);
                info.setCurrentGain(gainInMillibels);
            }
            this.mCurrentGainIndex = gainIndex;
            storeGainIndexForUserLocked(gainIndex, this.mUserId);
        }
    }

    private void storeGainIndexForUserLocked(int gainIndex, int userId) {
        this.mSettingsManager.storeVolumeGainIndexForUser(userId, this.mZoneId, this.mId, gainIndex);
    }

    private int getGainForIndexLocked(int gainIndex) {
        return this.mMinGain + (this.mStepSize * gainIndex);
    }

    private int getIndexForGainLocked(int gainInMillibel) {
        return (gainInMillibel - this.mMinGain) / this.mStepSize;
    }

    AudioDevicePort getAudioDevicePortForContext(int carAudioContext) {
        String address = this.mContextToAddress.get(carAudioContext);
        if (address == null || this.mAddressToCarAudioDeviceInfo.get(address) == null) {
            return null;
        }
        return this.mAddressToCarAudioDeviceInfo.get(address).getAudioDevicePort();
    }

    public String toString() {
        return "CarVolumeGroup id: " + this.mId + " currentGainIndex: " + this.mCurrentGainIndex + " contexts: " + Arrays.toString(getContexts()) + " addresses: " + String.join(", ", getAddresses());
    }

    void dump(final String indent, final PrintWriter writer) {
        synchronized (this.mLock) {
            writer.printf("%sCarVolumeGroup(%d)\n", indent, Integer.valueOf(this.mId));
            writer.printf("%sUserId(%d)\n", indent, Integer.valueOf(this.mUserId));
            writer.printf("%sGain values (min / max / default/ current): %d %d %d %d\n", indent, Integer.valueOf(this.mMinGain), Integer.valueOf(this.mMaxGain), Integer.valueOf(this.mDefaultGain), Integer.valueOf(getGainForIndexLocked(this.mCurrentGainIndex)));
            writer.printf("%sGain indexes (min / max / default / current): %d %d %d %d\n", indent, Integer.valueOf(getMinGainIndex()), Integer.valueOf(getMaxGainIndex()), Integer.valueOf(getDefaultGainIndex()), Integer.valueOf(this.mCurrentGainIndex));
            for (int i = 0; i < this.mContextToAddress.size(); i++) {
                writer.printf("%sContext: %s -> Address: %s\n", indent, CarAudioContext.toString(this.mContextToAddress.keyAt(i)), this.mContextToAddress.valueAt(i));
            }
            Stream<String> stream = this.mAddressToCarAudioDeviceInfo.keySet().stream();
            final Map<String, CarAudioDeviceInfo> map = this.mAddressToCarAudioDeviceInfo;
            Objects.requireNonNull(map);
            stream.map(new Function() { // from class: com.android.car.audio.-$$Lambda$AYdlo0c1HJp3JD3tKh3aHC1uLds
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return (CarAudioDeviceInfo) map.get((String) obj);
                }
            }).forEach(new Consumer() { // from class: com.android.car.audio.-$$Lambda$CarVolumeGroup$lk8_uruEQBXa-LZFwcs2Mb8qag0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((CarAudioDeviceInfo) obj).dump(indent, writer);
                }
            });
            writer.println();
        }
    }

    void loadVolumesForUser(int userId) {
        synchronized (this.mLock) {
            updateUserIdLocked(userId);
            updateCurrentGainIndexLocked();
        }
        setCurrentGainIndex(getCurrentGainIndex());
    }
}
