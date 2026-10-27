package android.media;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaRoute2Info implements Parcelable {
    public static final int CONNECTION_STATE_CONNECTED = 2;
    public static final int CONNECTION_STATE_CONNECTING = 1;
    public static final int CONNECTION_STATE_DISCONNECTED = 0;
    public static final Parcelable.Creator<MediaRoute2Info> CREATOR = new Parcelable.Creator<MediaRoute2Info>() { // from class: android.media.MediaRoute2Info.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaRoute2Info createFromParcel(Parcel parcel) {
            return new MediaRoute2Info(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaRoute2Info[] newArray(int i) {
            return new MediaRoute2Info[i];
        }
    };
    public static final String FEATURE_LIVE_AUDIO = "android.media.route.feature.LIVE_AUDIO";
    public static final String FEATURE_LIVE_VIDEO = "android.media.route.feature.LIVE_VIDEO";
    public static final String FEATURE_LOCAL_PLAYBACK = "android.media.route.feature.LOCAL_PLAYBACK";
    public static final String FEATURE_REMOTE_AUDIO_PLAYBACK = "android.media.route.feature.REMOTE_AUDIO_PLAYBACK";
    public static final String FEATURE_REMOTE_GROUP_PLAYBACK = "android.media.route.feature.REMOTE_GROUP_PLAYBACK";
    public static final String FEATURE_REMOTE_PLAYBACK = "android.media.route.feature.REMOTE_PLAYBACK";
    public static final String FEATURE_REMOTE_VIDEO_PLAYBACK = "android.media.route.feature.REMOTE_VIDEO_PLAYBACK";
    public static final int PLAYBACK_VOLUME_FIXED = 0;
    public static final int PLAYBACK_VOLUME_VARIABLE = 1;
    public static final int TYPE_BLUETOOTH_A2DP = 8;
    public static final int TYPE_BUILTIN_SPEAKER = 2;
    public static final int TYPE_DOCK = 13;
    public static final int TYPE_GROUP = 2000;
    public static final int TYPE_HDMI = 9;
    public static final int TYPE_HEARING_AID = 23;
    public static final int TYPE_REMOTE_SPEAKER = 1002;
    public static final int TYPE_REMOTE_TV = 1001;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_USB_ACCESSORY = 12;
    public static final int TYPE_USB_DEVICE = 11;
    public static final int TYPE_USB_HEADSET = 22;
    public static final int TYPE_WIRED_HEADPHONES = 4;
    public static final int TYPE_WIRED_HEADSET = 3;
    final String mAddress;
    final String mClientPackageName;
    final int mConnectionState;
    final CharSequence mDescription;
    final Bundle mExtras;
    final List<String> mFeatures;
    final Uri mIconUri;
    final String mId;
    final boolean mIsSystem;
    final CharSequence mName;
    final String mProviderId;
    final int mType;
    final int mVolume;
    final int mVolumeHandling;
    final int mVolumeMax;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ConnectionState {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PlaybackVolume {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    MediaRoute2Info(Builder builder) {
        this.mId = builder.mId;
        this.mName = builder.mName;
        this.mFeatures = builder.mFeatures;
        this.mType = builder.mType;
        this.mIsSystem = builder.mIsSystem;
        this.mIconUri = builder.mIconUri;
        this.mDescription = builder.mDescription;
        this.mConnectionState = builder.mConnectionState;
        this.mClientPackageName = builder.mClientPackageName;
        this.mVolumeHandling = builder.mVolumeHandling;
        this.mVolumeMax = builder.mVolumeMax;
        this.mVolume = builder.mVolume;
        this.mAddress = builder.mAddress;
        this.mExtras = builder.mExtras;
        this.mProviderId = builder.mProviderId;
    }

    MediaRoute2Info(Parcel parcel) {
        this.mId = parcel.readString();
        this.mName = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.mFeatures = parcel.createStringArrayList();
        this.mType = parcel.readInt();
        this.mIsSystem = parcel.readBoolean();
        this.mIconUri = (Uri) parcel.readParcelable(null);
        this.mDescription = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.mConnectionState = parcel.readInt();
        this.mClientPackageName = parcel.readString();
        this.mVolumeHandling = parcel.readInt();
        this.mVolumeMax = parcel.readInt();
        this.mVolume = parcel.readInt();
        this.mAddress = parcel.readString();
        this.mExtras = parcel.readBundle();
        this.mProviderId = parcel.readString();
    }

    public String getId() {
        String str = this.mProviderId;
        if (str != null) {
            return MediaRouter2Utils.toUniqueId(str, this.mId);
        }
        return this.mId;
    }

    public CharSequence getName() {
        return this.mName;
    }

    public List<String> getFeatures() {
        return this.mFeatures;
    }

    public int getType() {
        return this.mType;
    }

    public boolean isSystemRoute() {
        return this.mIsSystem;
    }

    public Uri getIconUri() {
        return this.mIconUri;
    }

    public CharSequence getDescription() {
        return this.mDescription;
    }

    public int getConnectionState() {
        return this.mConnectionState;
    }

    public String getClientPackageName() {
        return this.mClientPackageName;
    }

    public int getVolumeHandling() {
        return this.mVolumeHandling;
    }

    public int getVolumeMax() {
        return this.mVolumeMax;
    }

    public int getVolume() {
        return this.mVolume;
    }

    public String getAddress() {
        return this.mAddress;
    }

    public Bundle getExtras() {
        if (this.mExtras == null) {
            return null;
        }
        return new Bundle(this.mExtras);
    }

    public String getOriginalId() {
        return this.mId;
    }

    public String getProviderId() {
        return this.mProviderId;
    }

    public boolean hasAnyFeatures(Collection<String> collection) {
        Objects.requireNonNull(collection, "features must not be null");
        Iterator<String> it = collection.iterator();
        while (it.hasNext()) {
            if (getFeatures().contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    public boolean isValid() {
        return (TextUtils.isEmpty(getId()) || TextUtils.isEmpty(getName()) || TextUtils.isEmpty(getProviderId())) ? false : true;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaRoute2Info)) {
            return false;
        }
        MediaRoute2Info mediaRoute2Info = (MediaRoute2Info) obj;
        return Objects.equals(this.mId, mediaRoute2Info.mId) && Objects.equals(this.mName, mediaRoute2Info.mName) && Objects.equals(this.mFeatures, mediaRoute2Info.mFeatures) && this.mType == mediaRoute2Info.mType && this.mIsSystem == mediaRoute2Info.mIsSystem && Objects.equals(this.mIconUri, mediaRoute2Info.mIconUri) && Objects.equals(this.mDescription, mediaRoute2Info.mDescription) && this.mConnectionState == mediaRoute2Info.mConnectionState && Objects.equals(this.mClientPackageName, mediaRoute2Info.mClientPackageName) && this.mVolumeHandling == mediaRoute2Info.mVolumeHandling && this.mVolumeMax == mediaRoute2Info.mVolumeMax && this.mVolume == mediaRoute2Info.mVolume && Objects.equals(this.mAddress, mediaRoute2Info.mAddress) && Objects.equals(this.mProviderId, mediaRoute2Info.mProviderId);
    }

    public int hashCode() {
        return Objects.hash(this.mId, this.mName, this.mFeatures, Integer.valueOf(this.mType), Boolean.valueOf(this.mIsSystem), this.mIconUri, this.mDescription, Integer.valueOf(this.mConnectionState), this.mClientPackageName, Integer.valueOf(this.mVolumeHandling), Integer.valueOf(this.mVolumeMax), Integer.valueOf(this.mVolume), this.mAddress, this.mProviderId);
    }

    public String toString() {
        return "MediaRoute2Info{ id=" + getId() + ", name=" + getName() + ", features=" + getFeatures() + ", iconUri=" + getIconUri() + ", description=" + getDescription() + ", connectionState=" + getConnectionState() + ", clientPackageName=" + getClientPackageName() + ", volumeHandling=" + getVolumeHandling() + ", volumeMax=" + getVolumeMax() + ", volume=" + getVolume() + ", providerId=" + getProviderId() + " }";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mId);
        TextUtils.writeToParcel(this.mName, parcel, i);
        parcel.writeStringList(this.mFeatures);
        parcel.writeInt(this.mType);
        parcel.writeBoolean(this.mIsSystem);
        parcel.writeParcelable(this.mIconUri, i);
        TextUtils.writeToParcel(this.mDescription, parcel, i);
        parcel.writeInt(this.mConnectionState);
        parcel.writeString(this.mClientPackageName);
        parcel.writeInt(this.mVolumeHandling);
        parcel.writeInt(this.mVolumeMax);
        parcel.writeInt(this.mVolume);
        parcel.writeString(this.mAddress);
        parcel.writeBundle(this.mExtras);
        parcel.writeString(this.mProviderId);
    }

    public static final class Builder {
        String mAddress;
        String mClientPackageName;
        int mConnectionState;
        CharSequence mDescription;
        Bundle mExtras;
        final List<String> mFeatures;
        Uri mIconUri;
        final String mId;
        boolean mIsSystem;
        final CharSequence mName;
        String mProviderId;
        int mType;
        int mVolume;
        int mVolumeHandling;
        int mVolumeMax;

        public Builder(String str, CharSequence charSequence) {
            this.mType = 0;
            this.mVolumeHandling = 0;
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("id must not be empty");
            }
            if (TextUtils.isEmpty(charSequence)) {
                throw new IllegalArgumentException("name must not be empty");
            }
            this.mId = str;
            this.mName = charSequence;
            this.mFeatures = new ArrayList();
        }

        public Builder(MediaRoute2Info mediaRoute2Info) {
            this(mediaRoute2Info.mId, mediaRoute2Info);
        }

        public Builder(String str, MediaRoute2Info mediaRoute2Info) {
            this.mType = 0;
            this.mVolumeHandling = 0;
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("id must not be empty");
            }
            Objects.requireNonNull(mediaRoute2Info, "routeInfo must not be null");
            this.mId = str;
            this.mName = mediaRoute2Info.mName;
            this.mFeatures = new ArrayList(mediaRoute2Info.mFeatures);
            this.mType = mediaRoute2Info.mType;
            this.mIsSystem = mediaRoute2Info.mIsSystem;
            this.mIconUri = mediaRoute2Info.mIconUri;
            this.mDescription = mediaRoute2Info.mDescription;
            this.mConnectionState = mediaRoute2Info.mConnectionState;
            this.mClientPackageName = mediaRoute2Info.mClientPackageName;
            this.mVolumeHandling = mediaRoute2Info.mVolumeHandling;
            this.mVolumeMax = mediaRoute2Info.mVolumeMax;
            this.mVolume = mediaRoute2Info.mVolume;
            this.mAddress = mediaRoute2Info.mAddress;
            if (mediaRoute2Info.mExtras != null) {
                this.mExtras = new Bundle(mediaRoute2Info.mExtras);
            }
            this.mProviderId = mediaRoute2Info.mProviderId;
        }

        public Builder addFeature(String str) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("feature must not be null or empty");
            }
            this.mFeatures.add(str);
            return this;
        }

        public Builder addFeatures(Collection<String> collection) {
            Objects.requireNonNull(collection, "features must not be null");
            Iterator<String> it = collection.iterator();
            while (it.hasNext()) {
                addFeature(it.next());
            }
            return this;
        }

        public Builder clearFeatures() {
            this.mFeatures.clear();
            return this;
        }

        public Builder setType(int i) {
            this.mType = i;
            return this;
        }

        public Builder setSystemRoute(boolean z) {
            this.mIsSystem = z;
            return this;
        }

        public Builder setIconUri(Uri uri) {
            this.mIconUri = uri;
            return this;
        }

        public Builder setDescription(CharSequence charSequence) {
            this.mDescription = charSequence;
            return this;
        }

        public Builder setConnectionState(int i) {
            this.mConnectionState = i;
            return this;
        }

        public Builder setClientPackageName(String str) {
            this.mClientPackageName = str;
            return this;
        }

        public Builder setVolumeHandling(int i) {
            this.mVolumeHandling = i;
            return this;
        }

        public Builder setVolumeMax(int i) {
            this.mVolumeMax = i;
            return this;
        }

        public Builder setVolume(int i) {
            this.mVolume = i;
            return this;
        }

        public Builder setAddress(String str) {
            this.mAddress = str;
            return this;
        }

        public Builder setExtras(Bundle bundle) {
            if (bundle == null) {
                this.mExtras = null;
                return this;
            }
            this.mExtras = new Bundle(bundle);
            return this;
        }

        public Builder setProviderId(String str) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("providerId must not be null or empty");
            }
            this.mProviderId = str;
            return this;
        }

        public MediaRoute2Info build() {
            if (this.mFeatures.isEmpty()) {
                throw new IllegalArgumentException("features must not be empty!");
            }
            return new MediaRoute2Info(this);
        }
    }
}
