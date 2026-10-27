package android.media.audiopolicy;

import android.annotation.SystemApi;
import android.media.AudioAttributes;
import android.media.AudioSystem;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.util.Preconditions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class AudioProductStrategy implements Parcelable {
    public static final int DEFAULT_GROUP = -1;
    private static final String TAG = "AudioProductStrategy";
    private static List<AudioProductStrategy> sAudioProductStrategies;
    private final AudioAttributesGroup[] mAudioAttributesGroups;
    private int mId;
    private final String mName;
    private static final Object sLock = new Object();
    public static final Parcelable.Creator<AudioProductStrategy> CREATOR = new Parcelable.Creator<AudioProductStrategy>() { // from class: android.media.audiopolicy.AudioProductStrategy.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AudioProductStrategy createFromParcel(Parcel parcel) {
            String string = parcel.readString();
            int i = parcel.readInt();
            int i2 = parcel.readInt();
            AudioAttributesGroup[] audioAttributesGroupArr = new AudioAttributesGroup[i2];
            for (int i3 = 0; i3 < i2; i3++) {
                audioAttributesGroupArr[i3] = AudioAttributesGroup.CREATOR.createFromParcel(parcel);
            }
            return new AudioProductStrategy(string, i, audioAttributesGroupArr);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AudioProductStrategy[] newArray(int i) {
            return new AudioProductStrategy[i];
        }
    };
    public static final AudioAttributes sDefaultAttributes = new AudioAttributes.Builder().setCapturePreset(0).build();

    private static native int native_list_audio_product_strategies(ArrayList<AudioProductStrategy> arrayList);

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static List<AudioProductStrategy> getAudioProductStrategies() {
        if (sAudioProductStrategies == null) {
            synchronized (sLock) {
                if (sAudioProductStrategies == null) {
                    sAudioProductStrategies = initializeAudioProductStrategies();
                }
            }
        }
        return sAudioProductStrategies;
    }

    public static AudioProductStrategy getAudioProductStrategyWithId(int i) {
        synchronized (sLock) {
            if (sAudioProductStrategies == null) {
                sAudioProductStrategies = initializeAudioProductStrategies();
            }
            for (AudioProductStrategy audioProductStrategy : sAudioProductStrategies) {
                if (audioProductStrategy.getId() == i) {
                    return audioProductStrategy;
                }
            }
            return null;
        }
    }

    @SystemApi
    public static AudioProductStrategy createInvalidAudioProductStrategy(int i) {
        return new AudioProductStrategy("dummy strategy", i, new AudioAttributesGroup[0]);
    }

    public static AudioAttributes getAudioAttributesForStrategyWithLegacyStreamType(int i) {
        Iterator<AudioProductStrategy> it = getAudioProductStrategies().iterator();
        while (it.hasNext()) {
            AudioAttributes audioAttributesForLegacyStreamType = it.next().getAudioAttributesForLegacyStreamType(i);
            if (audioAttributesForLegacyStreamType != null) {
                return audioAttributesForLegacyStreamType;
            }
        }
        return new AudioAttributes.Builder().setContentType(0).setUsage(0).build();
    }

    public static int getLegacyStreamTypeForStrategyWithAudioAttributes(AudioAttributes audioAttributes) {
        Preconditions.checkNotNull(audioAttributes, "AudioAttributes must not be null");
        for (AudioProductStrategy audioProductStrategy : getAudioProductStrategies()) {
            if (audioProductStrategy.supportsAudioAttributes(audioAttributes)) {
                int legacyStreamTypeForAudioAttributes = audioProductStrategy.getLegacyStreamTypeForAudioAttributes(audioAttributes);
                if (legacyStreamTypeForAudioAttributes == -1) {
                    Log.w(TAG, "Attributes " + audioAttributes.toString() + " ported by strategy " + audioProductStrategy.getId() + " has no stream type associated, DO NOT USE STREAM TO CONTROL THE VOLUME");
                    return 3;
                }
                if (legacyStreamTypeForAudioAttributes < AudioSystem.getNumStreamTypes()) {
                    return legacyStreamTypeForAudioAttributes;
                }
            }
        }
        return 3;
    }

    private static List<AudioProductStrategy> initializeAudioProductStrategies() {
        ArrayList arrayList = new ArrayList();
        if (native_list_audio_product_strategies(arrayList) != 0) {
            Log.w(TAG, ": initializeAudioProductStrategies failed");
        }
        return arrayList;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AudioProductStrategy audioProductStrategy = (AudioProductStrategy) obj;
        return this.mName == audioProductStrategy.mName && this.mId == audioProductStrategy.mId && this.mAudioAttributesGroups.equals(audioProductStrategy.mAudioAttributesGroups);
    }

    private AudioProductStrategy(String str, int i, AudioAttributesGroup[] audioAttributesGroupArr) {
        Preconditions.checkNotNull(str, "name must not be null");
        Preconditions.checkNotNull(audioAttributesGroupArr, "AudioAttributesGroups must not be null");
        this.mName = str;
        this.mId = i;
        this.mAudioAttributesGroups = audioAttributesGroupArr;
    }

    @SystemApi
    public int getId() {
        return this.mId;
    }

    @SystemApi
    public AudioAttributes getAudioAttributes() {
        AudioAttributesGroup[] audioAttributesGroupArr = this.mAudioAttributesGroups;
        return audioAttributesGroupArr.length == 0 ? new AudioAttributes.Builder().build() : audioAttributesGroupArr[0].getAudioAttributes();
    }

    public AudioAttributes getAudioAttributesForLegacyStreamType(int i) {
        for (AudioAttributesGroup audioAttributesGroup : this.mAudioAttributesGroups) {
            if (audioAttributesGroup.supportsStreamType(i)) {
                return audioAttributesGroup.getAudioAttributes();
            }
        }
        return null;
    }

    public int getLegacyStreamTypeForAudioAttributes(AudioAttributes audioAttributes) {
        Preconditions.checkNotNull(audioAttributes, "AudioAttributes must not be null");
        for (AudioAttributesGroup audioAttributesGroup : this.mAudioAttributesGroups) {
            if (audioAttributesGroup.supportsAttributes(audioAttributes)) {
                return audioAttributesGroup.getStreamType();
            }
        }
        return -1;
    }

    @SystemApi
    public boolean supportsAudioAttributes(AudioAttributes audioAttributes) {
        Preconditions.checkNotNull(audioAttributes, "AudioAttributes must not be null");
        for (AudioAttributesGroup audioAttributesGroup : this.mAudioAttributesGroups) {
            if (audioAttributesGroup.supportsAttributes(audioAttributes)) {
                return true;
            }
        }
        return false;
    }

    public int getVolumeGroupIdForLegacyStreamType(int i) {
        for (AudioAttributesGroup audioAttributesGroup : this.mAudioAttributesGroups) {
            if (audioAttributesGroup.supportsStreamType(i)) {
                return audioAttributesGroup.getVolumeGroupId();
            }
        }
        return -1;
    }

    public int getVolumeGroupIdForAudioAttributes(AudioAttributes audioAttributes) {
        Preconditions.checkNotNull(audioAttributes, "AudioAttributes must not be null");
        for (AudioAttributesGroup audioAttributesGroup : this.mAudioAttributesGroups) {
            if (audioAttributesGroup.supportsAttributes(audioAttributes)) {
                return audioAttributesGroup.getVolumeGroupId();
            }
        }
        return -1;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mName);
        parcel.writeInt(this.mId);
        parcel.writeInt(this.mAudioAttributesGroups.length);
        for (AudioAttributesGroup audioAttributesGroup : this.mAudioAttributesGroups) {
            audioAttributesGroup.writeToParcel(parcel, i);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n Name: ");
        sb.append(this.mName);
        sb.append(" Id: ");
        sb.append(Integer.toString(this.mId));
        for (AudioAttributesGroup audioAttributesGroup : this.mAudioAttributesGroups) {
            sb.append(audioAttributesGroup.toString());
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean attributesMatches(AudioAttributes audioAttributes, AudioAttributes audioAttributes2) {
        Preconditions.checkNotNull(audioAttributes, "refAttr must not be null");
        Preconditions.checkNotNull(audioAttributes2, "attr must not be null");
        String strJoin = TextUtils.join(";", audioAttributes.getTags());
        String strJoin2 = TextUtils.join(";", audioAttributes2.getTags());
        if (audioAttributes.equals(sDefaultAttributes)) {
            return false;
        }
        if (audioAttributes.getSystemUsage() != 0 && audioAttributes2.getSystemUsage() != audioAttributes.getSystemUsage()) {
            return false;
        }
        if (audioAttributes.getContentType() != 0 && audioAttributes2.getContentType() != audioAttributes.getContentType()) {
            return false;
        }
        if (audioAttributes.getAllFlags() == 0 || (audioAttributes2.getAllFlags() != 0 && (audioAttributes2.getAllFlags() & audioAttributes.getAllFlags()) == audioAttributes.getAllFlags())) {
            return strJoin.length() == 0 || strJoin.equals(strJoin2);
        }
        return false;
    }

    private static final class AudioAttributesGroup implements Parcelable {
        public static final Parcelable.Creator<AudioAttributesGroup> CREATOR = new Parcelable.Creator<AudioAttributesGroup>() { // from class: android.media.audiopolicy.AudioProductStrategy.AudioAttributesGroup.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public AudioAttributesGroup createFromParcel(Parcel parcel) {
                int i = parcel.readInt();
                int i2 = parcel.readInt();
                int i3 = parcel.readInt();
                AudioAttributes[] audioAttributesArr = new AudioAttributes[i3];
                for (int i4 = 0; i4 < i3; i4++) {
                    audioAttributesArr[i4] = AudioAttributes.CREATOR.createFromParcel(parcel);
                }
                return new AudioAttributesGroup(i, i2, audioAttributesArr);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public AudioAttributesGroup[] newArray(int i) {
                return new AudioAttributesGroup[i];
            }
        };
        private final AudioAttributes[] mAudioAttributes;
        private int mLegacyStreamType;
        private int mVolumeGroupId;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        AudioAttributesGroup(int i, int i2, AudioAttributes[] audioAttributesArr) {
            this.mVolumeGroupId = i;
            this.mLegacyStreamType = i2;
            this.mAudioAttributes = audioAttributesArr;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            AudioAttributesGroup audioAttributesGroup = (AudioAttributesGroup) obj;
            return this.mVolumeGroupId == audioAttributesGroup.mVolumeGroupId && this.mLegacyStreamType == audioAttributesGroup.mLegacyStreamType && this.mAudioAttributes.equals(audioAttributesGroup.mAudioAttributes);
        }

        public int getStreamType() {
            return this.mLegacyStreamType;
        }

        public int getVolumeGroupId() {
            return this.mVolumeGroupId;
        }

        public AudioAttributes getAudioAttributes() {
            AudioAttributes[] audioAttributesArr = this.mAudioAttributes;
            return audioAttributesArr.length == 0 ? new AudioAttributes.Builder().build() : audioAttributesArr[0];
        }

        public boolean supportsAttributes(AudioAttributes audioAttributes) {
            for (AudioAttributes audioAttributes2 : this.mAudioAttributes) {
                if (audioAttributes2.equals(audioAttributes) || AudioProductStrategy.attributesMatches(audioAttributes2, audioAttributes)) {
                    return true;
                }
            }
            return false;
        }

        public boolean supportsStreamType(int i) {
            return this.mLegacyStreamType == i;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mVolumeGroupId);
            parcel.writeInt(this.mLegacyStreamType);
            parcel.writeInt(this.mAudioAttributes.length);
            for (AudioAttributes audioAttributes : this.mAudioAttributes) {
                audioAttributes.writeToParcel(parcel, i | 1);
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("\n    Legacy Stream Type: ");
            sb.append(Integer.toString(this.mLegacyStreamType));
            sb.append(" Volume Group Id: ");
            sb.append(Integer.toString(this.mVolumeGroupId));
            for (AudioAttributes audioAttributes : this.mAudioAttributes) {
                sb.append("\n    -");
                sb.append(audioAttributes.toString());
            }
            return sb.toString();
        }
    }
}
