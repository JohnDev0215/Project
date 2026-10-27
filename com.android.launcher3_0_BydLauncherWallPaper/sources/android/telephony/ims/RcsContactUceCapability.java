package android.telephony.ims;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class RcsContactUceCapability implements Parcelable {
    public static final int CAPABILITY_CALL_COMPOSER = 4194304;
    public static final int CAPABILITY_CHAT_BOT = 67108864;
    public static final int CAPABILITY_CHAT_BOT_ROLE = 134217728;
    public static final int CAPABILITY_CHAT_SESSION = 2;
    public static final int CAPABILITY_CHAT_SESSION_STORE_FORWARD = 4;
    public static final int CAPABILITY_CHAT_STANDALONE = 1;
    public static final int CAPABILITY_DISCOVERY_VIA_PRESENCE = 4096;
    public static final int CAPABILITY_FILE_TRANSFER = 8;
    public static final int CAPABILITY_FILE_TRANSFER_HTTP = 64;
    public static final int CAPABILITY_FILE_TRANSFER_SMS = 128;
    public static final int CAPABILITY_FILE_TRANSFER_STORE_FORWARD = 32;
    public static final int CAPABILITY_FILE_TRANSFER_THUMBNAIL = 16;
    public static final int CAPABILITY_GEOLOCATION_PULL = 131072;
    public static final int CAPABILITY_GEOLOCATION_PULL_FILE_TRANSFER = 262144;
    public static final int CAPABILITY_GEOLOCATION_PUSH = 32768;
    public static final int CAPABILITY_GEOLOCATION_PUSH_SMS = 65536;
    public static final int CAPABILITY_IMAGE_SHARE = 256;
    public static final int CAPABILITY_IP_VIDEO_CALL = 16384;
    public static final int CAPABILITY_IP_VOICE_CALL = 8192;
    public static final int CAPABILITY_MMTEL_CALL_COMPOSER = 1073741824;
    public static final int CAPABILITY_PLUG_IN = 268435456;
    public static final int CAPABILITY_POST_CALL = 8388608;
    public static final int CAPABILITY_RCS_VIDEO_CALL = 1048576;
    public static final int CAPABILITY_RCS_VIDEO_ONLY_CALL = 2097152;
    public static final int CAPABILITY_RCS_VOICE_CALL = 524288;
    public static final int CAPABILITY_SHARED_MAP = 16777216;
    public static final int CAPABILITY_SHARED_SKETCH = 33554432;
    public static final int CAPABILITY_SOCIAL_PRESENCE = 2048;
    public static final int CAPABILITY_STANDALONE_CHAT_BOT = 536870912;
    public static final int CAPABILITY_VIDEO_SHARE = 1024;
    public static final int CAPABILITY_VIDEO_SHARE_DURING_CS_CALL = 512;
    public static final Parcelable.Creator<RcsContactUceCapability> CREATOR = new Parcelable.Creator<RcsContactUceCapability>() { // from class: android.telephony.ims.RcsContactUceCapability.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RcsContactUceCapability createFromParcel(Parcel parcel) {
            return new RcsContactUceCapability(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RcsContactUceCapability[] newArray(int i) {
            return new RcsContactUceCapability[i];
        }
    };
    private long mCapabilities;
    private final Uri mContactUri;
    private List<String> mExtensionTags;
    private Map<Long, Uri> mServiceMap;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CapabilityFlag {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    static /* synthetic */ long access$078(RcsContactUceCapability rcsContactUceCapability, long j) {
        long j2 = j | rcsContactUceCapability.mCapabilities;
        rcsContactUceCapability.mCapabilities = j2;
        return j2;
    }

    public static class Builder {
        private final RcsContactUceCapability mCapabilities;

        public Builder(Uri uri) {
            this.mCapabilities = new RcsContactUceCapability(uri);
        }

        public Builder add(long j, Uri uri) {
            RcsContactUceCapability.access$078(this.mCapabilities, j);
            for (long j2 = 0; j2 < 32; j2++) {
                long j3 = ((long) (1 << ((int) j2))) & j;
                if (j3 != 0) {
                    this.mCapabilities.mServiceMap.put(Long.valueOf(j3), uri);
                    j &= ~j3;
                }
                if (j == 0) {
                    break;
                }
            }
            return this;
        }

        public Builder add(long j) {
            RcsContactUceCapability.access$078(this.mCapabilities, j);
            return this;
        }

        public Builder add(String str) {
            this.mCapabilities.mExtensionTags.add(str);
            return this;
        }

        public RcsContactUceCapability build() {
            return this.mCapabilities;
        }
    }

    RcsContactUceCapability(Uri uri) {
        this.mExtensionTags = new ArrayList();
        this.mServiceMap = new HashMap();
        this.mContactUri = uri;
    }

    private RcsContactUceCapability(Parcel parcel) {
        this.mExtensionTags = new ArrayList();
        this.mServiceMap = new HashMap();
        this.mContactUri = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.mCapabilities = parcel.readLong();
        parcel.readStringList(this.mExtensionTags);
        int i = parcel.readInt();
        for (int i2 = 0; i2 < i; i2++) {
            this.mServiceMap.put(Long.valueOf(parcel.readLong()), (Uri) parcel.readParcelable(Uri.class.getClassLoader()));
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mContactUri, 0);
        parcel.writeLong(this.mCapabilities);
        parcel.writeStringList(this.mExtensionTags);
        parcel.writeInt(this.mServiceMap.keySet().size());
        Iterator<Long> it = this.mServiceMap.keySet().iterator();
        while (it.hasNext()) {
            long jLongValue = it.next().longValue();
            parcel.writeLong(jLongValue);
            parcel.writeParcelable(this.mServiceMap.get(Long.valueOf(jLongValue)), 0);
        }
    }

    public boolean isCapable(long j) {
        return (this.mCapabilities & j) > 0;
    }

    public boolean isCapable(String str) {
        return this.mExtensionTags.contains(str);
    }

    public List<String> getCapableExtensionTags() {
        return Collections.unmodifiableList(this.mExtensionTags);
    }

    public Uri getServiceUri(long j) {
        Uri orDefault = this.mServiceMap.getOrDefault(Long.valueOf(j), null);
        if (orDefault != null) {
            return orDefault;
        }
        if (isCapable(j)) {
            return getContactUri();
        }
        return null;
    }

    public Uri getContactUri() {
        return this.mContactUri;
    }
}
