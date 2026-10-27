package android.view;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public abstract class DisplayAddress implements Parcelable {
    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static Physical fromPhysicalDisplayId(long j) {
        return new Physical(j);
    }

    public static Physical fromPortAndModel(byte b, Long l) {
        return new Physical(b, l);
    }

    public static Network fromMacAddress(String str) {
        return new Network(str);
    }

    public static final class Physical extends DisplayAddress {
        public static final Parcelable.Creator<Physical> CREATOR = new Parcelable.Creator<Physical>() { // from class: android.view.DisplayAddress.Physical.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Physical createFromParcel(Parcel parcel) {
                return new Physical(parcel.readLong());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Physical[] newArray(int i) {
                return new Physical[i];
            }
        };
        private static final int MODEL_SHIFT = 8;
        private static final long UNKNOWN_MODEL = 0;
        private final long mPhysicalDisplayId;

        public long getPhysicalDisplayId() {
            return this.mPhysicalDisplayId;
        }

        public byte getPort() {
            return (byte) this.mPhysicalDisplayId;
        }

        public Long getModel() {
            long j = this.mPhysicalDisplayId >>> 8;
            if (j == 0) {
                return null;
            }
            return Long.valueOf(j);
        }

        public boolean equals(Object obj) {
            return (obj instanceof Physical) && this.mPhysicalDisplayId == ((Physical) obj).mPhysicalDisplayId;
        }

        public String toString() {
            StringBuilder sbAppend = new StringBuilder("{").append("port=").append(Byte.toUnsignedInt(getPort()));
            Long model = getModel();
            if (model != null) {
                sbAppend.append(", model=0x").append(Long.toHexString(model.longValue()));
            }
            return sbAppend.append("}").toString();
        }

        public int hashCode() {
            return Long.hashCode(this.mPhysicalDisplayId);
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(this.mPhysicalDisplayId);
        }

        private Physical(long j) {
            this.mPhysicalDisplayId = j;
        }

        private Physical(byte b, Long l) {
            this.mPhysicalDisplayId = (l == null ? 0L : l.longValue() << 8) | Byte.toUnsignedLong(b);
        }
    }

    public static final class Network extends DisplayAddress {
        public static final Parcelable.Creator<Network> CREATOR = new Parcelable.Creator<Network>() { // from class: android.view.DisplayAddress.Network.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Network createFromParcel(Parcel parcel) {
                return new Network(parcel.readString());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Network[] newArray(int i) {
                return new Network[i];
            }
        };
        private final String mMacAddress;

        public boolean equals(Object obj) {
            return (obj instanceof Network) && this.mMacAddress.equals(((Network) obj).mMacAddress);
        }

        public String toString() {
            return this.mMacAddress;
        }

        public int hashCode() {
            return this.mMacAddress.hashCode();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mMacAddress);
        }

        private Network(String str) {
            this.mMacAddress = str;
        }
    }
}
