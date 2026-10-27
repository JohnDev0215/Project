package com.android.car.audio;

import android.media.AudioDeviceAttributes;
import android.media.AudioDeviceInfo;
import android.util.Log;
import com.android.car.CarLog;
import com.android.internal.util.Preconditions;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
class CarAudioZone {
    private final int mId;
    private final String mName;
    private final List<CarVolumeGroup> mVolumeGroups = new ArrayList();
    private List<AudioDeviceAttributes> mInputAudioDevice = new ArrayList();

    CarAudioZone(int id, String name) {
        this.mId = id;
        this.mName = name;
    }

    int getId() {
        return this.mId;
    }

    String getName() {
        return this.mName;
    }

    boolean isPrimaryZone() {
        return this.mId == 0;
    }

    void addVolumeGroup(CarVolumeGroup volumeGroup) {
        this.mVolumeGroups.add(volumeGroup);
    }

    CarVolumeGroup getVolumeGroup(int groupId) {
        Preconditions.checkArgumentInRange(groupId, 0, this.mVolumeGroups.size() - 1, "groupId(" + groupId + ") is out of range");
        return this.mVolumeGroups.get(groupId);
    }

    List<AudioDeviceInfo> getAudioDeviceInfos() {
        List<AudioDeviceInfo> devices = new ArrayList<>();
        for (CarVolumeGroup group : this.mVolumeGroups) {
            for (String address : group.getAddresses()) {
                devices.add(group.getCarAudioDeviceInfoForAddress(address).getAudioDeviceInfo());
            }
        }
        return devices;
    }

    int getVolumeGroupCount() {
        return this.mVolumeGroups.size();
    }

    CarVolumeGroup[] getVolumeGroups() {
        return (CarVolumeGroup[]) this.mVolumeGroups.toArray(new CarVolumeGroup[0]);
    }

    boolean validateVolumeGroups() {
        Set<Integer> contextSet = new HashSet<>();
        Set<String> addresses = new HashSet<>();
        for (CarVolumeGroup group : this.mVolumeGroups) {
            for (int context : group.getContexts()) {
                if (!contextSet.add(Integer.valueOf(context))) {
                    Log.e(CarLog.TAG_AUDIO, "Context appears in two groups: " + context);
                    return false;
                }
            }
            for (String address : group.getAddresses()) {
                if (!addresses.add(address)) {
                    Log.e(CarLog.TAG_AUDIO, "Address appears in two groups: " + address);
                    return false;
                }
            }
        }
        if (contextSet.size() != CarAudioContext.CONTEXTS.length) {
            Log.e(CarLog.TAG_AUDIO, "Some contexts are not assigned to group");
            Log.e(CarLog.TAG_AUDIO, "Assigned contexts " + contextSet);
            Log.e(CarLog.TAG_AUDIO, "All contexts " + Arrays.toString(CarAudioContext.CONTEXTS));
            return false;
        }
        return true;
    }

    void synchronizeCurrentGainIndex() {
        for (CarVolumeGroup group : this.mVolumeGroups) {
            group.setCurrentGainIndex(group.getCurrentGainIndex());
        }
    }

    void dump(String indent, PrintWriter writer) {
        String internalIndent = indent + "\t";
        writer.printf("%sCarAudioZone(%s:%d) isPrimary? %b\n", indent, this.mName, Integer.valueOf(this.mId), Boolean.valueOf(isPrimaryZone()));
        for (CarVolumeGroup group : this.mVolumeGroups) {
            group.dump(internalIndent, writer);
        }
        writer.printf("%sInput Audio Device Addresses\n", internalIndent);
        String devicesIndent = internalIndent + "\t";
        for (AudioDeviceAttributes audioDevice : this.mInputAudioDevice) {
            writer.printf("%sDevice Address(%s)\n", devicesIndent, audioDevice.getAddress());
        }
        writer.println();
    }

    String getAddressForContext(int audioContext) {
        CarAudioContext.preconditionCheckAudioContext(audioContext);
        for (CarVolumeGroup volumeGroup : getVolumeGroups()) {
            String deviceAddress = volumeGroup.getAddressForContext(audioContext);
            if (deviceAddress != null) {
                return deviceAddress;
            }
        }
        throw new IllegalStateException("Could not find output device in zone " + this.mId + " for audio context " + audioContext);
    }

    public void updateVolumeGroupsForUser(int userId) {
        for (CarVolumeGroup group : this.mVolumeGroups) {
            group.loadVolumesForUser(userId);
        }
    }

    void addInputAudioDevice(AudioDeviceAttributes device) {
        this.mInputAudioDevice.add(device);
    }

    List<AudioDeviceAttributes> getInputAudioDevices() {
        return this.mInputAudioDevice;
    }
}
