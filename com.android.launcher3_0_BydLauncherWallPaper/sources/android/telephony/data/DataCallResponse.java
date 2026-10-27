package android.telephony.data;

import android.annotation.SystemApi;
import android.net.LinkAddress;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
@SystemApi
public final class DataCallResponse implements Parcelable {
    public static final Parcelable.Creator<DataCallResponse> CREATOR = new Parcelable.Creator<DataCallResponse>() { // from class: android.telephony.data.DataCallResponse.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DataCallResponse createFromParcel(Parcel parcel) {
            return new DataCallResponse(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DataCallResponse[] newArray(int i) {
            return new DataCallResponse[i];
        }
    };
    public static final int LINK_STATUS_ACTIVE = 2;
    public static final int LINK_STATUS_DORMANT = 1;
    public static final int LINK_STATUS_INACTIVE = 0;
    public static final int LINK_STATUS_UNKNOWN = -1;
    private final List<LinkAddress> mAddresses;
    private final int mCause;
    private final List<InetAddress> mDnsAddresses;
    private final List<InetAddress> mGatewayAddresses;
    private final int mId;
    private final String mInterfaceName;
    private final int mLinkStatus;
    private final int mMtu;
    private final int mMtuV4;
    private final int mMtuV6;
    private final List<InetAddress> mPcscfAddresses;
    private final int mProtocolType;
    private final int mSuggestedRetryTime;

    @Retention(RetentionPolicy.SOURCE)
    public @interface LinkStatus {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DataCallResponse(int i, int i2, int i3, int i4, int i5, String str, List<LinkAddress> list, List<InetAddress> list2, List<InetAddress> list3, List<InetAddress> list4, int i6) {
        this.mCause = i;
        this.mSuggestedRetryTime = i2;
        this.mId = i3;
        this.mLinkStatus = i4;
        this.mProtocolType = i5;
        this.mInterfaceName = str == null ? "" : str;
        this.mAddresses = list == null ? new ArrayList() : new ArrayList(list);
        this.mDnsAddresses = list2 == null ? new ArrayList() : new ArrayList(list2);
        this.mGatewayAddresses = list3 == null ? new ArrayList() : new ArrayList(list3);
        this.mPcscfAddresses = list4 == null ? new ArrayList() : new ArrayList(list4);
        this.mMtuV6 = i6;
        this.mMtuV4 = i6;
        this.mMtu = i6;
    }

    private DataCallResponse(int i, int i2, int i3, int i4, int i5, String str, List<LinkAddress> list, List<InetAddress> list2, List<InetAddress> list3, List<InetAddress> list4, int i6, int i7, int i8) {
        this.mCause = i;
        this.mSuggestedRetryTime = i2;
        this.mId = i3;
        this.mLinkStatus = i4;
        this.mProtocolType = i5;
        this.mInterfaceName = str == null ? "" : str;
        this.mAddresses = list == null ? new ArrayList() : new ArrayList(list);
        this.mDnsAddresses = list2 == null ? new ArrayList() : new ArrayList(list2);
        this.mGatewayAddresses = list3 == null ? new ArrayList() : new ArrayList(list3);
        this.mPcscfAddresses = list4 == null ? new ArrayList() : new ArrayList(list4);
        this.mMtu = i6;
        this.mMtuV4 = i7;
        this.mMtuV6 = i8;
    }

    public DataCallResponse(Parcel parcel) {
        this.mCause = parcel.readInt();
        this.mSuggestedRetryTime = parcel.readInt();
        this.mId = parcel.readInt();
        this.mLinkStatus = parcel.readInt();
        this.mProtocolType = parcel.readInt();
        this.mInterfaceName = parcel.readString();
        ArrayList arrayList = new ArrayList();
        this.mAddresses = arrayList;
        parcel.readList(arrayList, LinkAddress.class.getClassLoader());
        ArrayList arrayList2 = new ArrayList();
        this.mDnsAddresses = arrayList2;
        parcel.readList(arrayList2, InetAddress.class.getClassLoader());
        ArrayList arrayList3 = new ArrayList();
        this.mGatewayAddresses = arrayList3;
        parcel.readList(arrayList3, InetAddress.class.getClassLoader());
        ArrayList arrayList4 = new ArrayList();
        this.mPcscfAddresses = arrayList4;
        parcel.readList(arrayList4, InetAddress.class.getClassLoader());
        this.mMtu = parcel.readInt();
        this.mMtuV4 = parcel.readInt();
        this.mMtuV6 = parcel.readInt();
    }

    public int getCause() {
        return this.mCause;
    }

    public int getSuggestedRetryTime() {
        return this.mSuggestedRetryTime;
    }

    public int getId() {
        return this.mId;
    }

    public int getLinkStatus() {
        return this.mLinkStatus;
    }

    public int getProtocolType() {
        return this.mProtocolType;
    }

    public String getInterfaceName() {
        return this.mInterfaceName;
    }

    public List<LinkAddress> getAddresses() {
        return this.mAddresses;
    }

    public List<InetAddress> getDnsAddresses() {
        return this.mDnsAddresses;
    }

    public List<InetAddress> getGatewayAddresses() {
        return this.mGatewayAddresses;
    }

    public List<InetAddress> getPcscfAddresses() {
        return this.mPcscfAddresses;
    }

    @Deprecated
    public int getMtu() {
        return this.mMtu;
    }

    public int getMtuV4() {
        return this.mMtuV4;
    }

    public int getMtuV6() {
        return this.mMtuV6;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("DataCallResponse: {").append(" cause=").append(this.mCause).append(" retry=").append(this.mSuggestedRetryTime).append(" cid=").append(this.mId).append(" linkStatus=").append(this.mLinkStatus).append(" protocolType=").append(this.mProtocolType).append(" ifname=").append(this.mInterfaceName).append(" addresses=").append(this.mAddresses).append(" dnses=").append(this.mDnsAddresses).append(" gateways=").append(this.mGatewayAddresses).append(" pcscf=").append(this.mPcscfAddresses).append(" mtu=").append(getMtu()).append(" mtuV4=").append(getMtuV4()).append(" mtuV6=").append(getMtuV6()).append("}");
        return stringBuffer.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DataCallResponse)) {
            return false;
        }
        DataCallResponse dataCallResponse = (DataCallResponse) obj;
        return this.mCause == dataCallResponse.mCause && this.mSuggestedRetryTime == dataCallResponse.mSuggestedRetryTime && this.mId == dataCallResponse.mId && this.mLinkStatus == dataCallResponse.mLinkStatus && this.mProtocolType == dataCallResponse.mProtocolType && this.mInterfaceName.equals(dataCallResponse.mInterfaceName) && this.mAddresses.size() == dataCallResponse.mAddresses.size() && this.mAddresses.containsAll(dataCallResponse.mAddresses) && this.mDnsAddresses.size() == dataCallResponse.mDnsAddresses.size() && this.mDnsAddresses.containsAll(dataCallResponse.mDnsAddresses) && this.mGatewayAddresses.size() == dataCallResponse.mGatewayAddresses.size() && this.mGatewayAddresses.containsAll(dataCallResponse.mGatewayAddresses) && this.mPcscfAddresses.size() == dataCallResponse.mPcscfAddresses.size() && this.mPcscfAddresses.containsAll(dataCallResponse.mPcscfAddresses) && this.mMtu == dataCallResponse.mMtu && this.mMtuV4 == dataCallResponse.mMtuV4 && this.mMtuV6 == dataCallResponse.mMtuV6;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mCause), Integer.valueOf(this.mSuggestedRetryTime), Integer.valueOf(this.mId), Integer.valueOf(this.mLinkStatus), Integer.valueOf(this.mProtocolType), this.mInterfaceName, this.mAddresses, this.mDnsAddresses, this.mGatewayAddresses, this.mPcscfAddresses, Integer.valueOf(this.mMtu), Integer.valueOf(this.mMtuV4), Integer.valueOf(this.mMtuV6));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mCause);
        parcel.writeInt(this.mSuggestedRetryTime);
        parcel.writeInt(this.mId);
        parcel.writeInt(this.mLinkStatus);
        parcel.writeInt(this.mProtocolType);
        parcel.writeString(this.mInterfaceName);
        parcel.writeList(this.mAddresses);
        parcel.writeList(this.mDnsAddresses);
        parcel.writeList(this.mGatewayAddresses);
        parcel.writeList(this.mPcscfAddresses);
        parcel.writeInt(this.mMtu);
        parcel.writeInt(this.mMtuV4);
        parcel.writeInt(this.mMtuV6);
    }

    public static final class Builder {
        private List<LinkAddress> mAddresses;
        private int mCause;
        private List<InetAddress> mDnsAddresses;
        private List<InetAddress> mGatewayAddresses;
        private int mId;
        private String mInterfaceName;
        private int mLinkStatus;
        private int mMtu;
        private int mMtuV4;
        private int mMtuV6;
        private List<InetAddress> mPcscfAddresses;
        private int mProtocolType;
        private int mSuggestedRetryTime;

        public Builder setCause(int i) {
            this.mCause = i;
            return this;
        }

        public Builder setSuggestedRetryTime(int i) {
            this.mSuggestedRetryTime = i;
            return this;
        }

        public Builder setId(int i) {
            this.mId = i;
            return this;
        }

        public Builder setLinkStatus(int i) {
            this.mLinkStatus = i;
            return this;
        }

        public Builder setProtocolType(int i) {
            this.mProtocolType = i;
            return this;
        }

        public Builder setInterfaceName(String str) {
            this.mInterfaceName = str;
            return this;
        }

        public Builder setAddresses(List<LinkAddress> list) {
            this.mAddresses = list;
            return this;
        }

        public Builder setDnsAddresses(List<InetAddress> list) {
            this.mDnsAddresses = list;
            return this;
        }

        public Builder setGatewayAddresses(List<InetAddress> list) {
            this.mGatewayAddresses = list;
            return this;
        }

        public Builder setPcscfAddresses(List<InetAddress> list) {
            this.mPcscfAddresses = list;
            return this;
        }

        public Builder setMtu(int i) {
            this.mMtu = i;
            return this;
        }

        public Builder setMtuV4(int i) {
            this.mMtuV4 = i;
            return this;
        }

        public Builder setMtuV6(int i) {
            this.mMtuV6 = i;
            return this;
        }

        public DataCallResponse build() {
            return new DataCallResponse(this.mCause, this.mSuggestedRetryTime, this.mId, this.mLinkStatus, this.mProtocolType, this.mInterfaceName, this.mAddresses, this.mDnsAddresses, this.mGatewayAddresses, this.mPcscfAddresses, this.mMtu, this.mMtuV4, this.mMtuV6);
        }
    }
}
