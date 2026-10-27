package android.hardware.location;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.telecom.Logging.Session;
import java.util.Arrays;
import libcore.util.HexEncoding;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class NanoAppMessage implements Parcelable {
    public static final Parcelable.Creator<NanoAppMessage> CREATOR = new Parcelable.Creator<NanoAppMessage>() { // from class: android.hardware.location.NanoAppMessage.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NanoAppMessage createFromParcel(Parcel parcel) {
            return new NanoAppMessage(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NanoAppMessage[] newArray(int i) {
            return new NanoAppMessage[i];
        }
    };
    private static final int DEBUG_LOG_NUM_BYTES = 16;
    private boolean mIsBroadcasted;
    private byte[] mMessageBody;
    private int mMessageType;
    private long mNanoAppId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private NanoAppMessage(long j, int i, byte[] bArr, boolean z) {
        this.mNanoAppId = j;
        this.mMessageType = i;
        this.mMessageBody = bArr;
        this.mIsBroadcasted = z;
    }

    public static NanoAppMessage createMessageToNanoApp(long j, int i, byte[] bArr) {
        return new NanoAppMessage(j, i, bArr, false);
    }

    public static NanoAppMessage createMessageFromNanoApp(long j, int i, byte[] bArr, boolean z) {
        return new NanoAppMessage(j, i, bArr, z);
    }

    public long getNanoAppId() {
        return this.mNanoAppId;
    }

    public int getMessageType() {
        return this.mMessageType;
    }

    public byte[] getMessageBody() {
        return this.mMessageBody;
    }

    public boolean isBroadcastMessage() {
        return this.mIsBroadcasted;
    }

    private NanoAppMessage(Parcel parcel) {
        this.mNanoAppId = parcel.readLong();
        this.mIsBroadcasted = parcel.readInt() == 1;
        this.mMessageType = parcel.readInt();
        byte[] bArr = new byte[parcel.readInt()];
        this.mMessageBody = bArr;
        parcel.readByteArray(bArr);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.mNanoAppId);
        parcel.writeInt(this.mIsBroadcasted ? 1 : 0);
        parcel.writeInt(this.mMessageType);
        parcel.writeInt(this.mMessageBody.length);
        parcel.writeByteArray(this.mMessageBody);
    }

    public String toString() {
        int length = this.mMessageBody.length;
        String str = "NanoAppMessage[type = " + this.mMessageType + ", length = " + this.mMessageBody.length + " bytes, " + (this.mIsBroadcasted ? "broadcast" : "unicast") + ", nanoapp = 0x" + Long.toHexString(this.mNanoAppId) + "](";
        if (length > 0) {
            str = str + "data = 0x";
        }
        int i = 0;
        while (i < Math.min(length, 16)) {
            str = str + HexEncoding.encodeToString(this.mMessageBody[i], true);
            i++;
            if (i % 4 == 0) {
                str = str + " ";
            }
        }
        if (length > 16) {
            str = str + Session.TRUNCATE_STRING;
        }
        return str + ")";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NanoAppMessage)) {
            return false;
        }
        NanoAppMessage nanoAppMessage = (NanoAppMessage) obj;
        return nanoAppMessage.getNanoAppId() == this.mNanoAppId && nanoAppMessage.getMessageType() == this.mMessageType && nanoAppMessage.isBroadcastMessage() == this.mIsBroadcasted && Arrays.equals(nanoAppMessage.getMessageBody(), this.mMessageBody);
    }
}
