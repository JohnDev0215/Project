package android.telephony;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.security.keystore.KeyProperties;
import android.text.TextUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes3.dex */
public final class NetworkRegistrationInfo implements Parcelable {
    public static final Parcelable.Creator<NetworkRegistrationInfo> CREATOR = new Parcelable.Creator<NetworkRegistrationInfo>() { // from class: android.telephony.NetworkRegistrationInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NetworkRegistrationInfo createFromParcel(Parcel parcel) {
            return new NetworkRegistrationInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NetworkRegistrationInfo[] newArray(int i) {
            return new NetworkRegistrationInfo[i];
        }
    };
    public static final int DOMAIN_CS = 1;
    public static final int DOMAIN_CS_PS = 3;
    public static final int DOMAIN_PS = 2;
    public static final int DOMAIN_UNKNOWN = 0;
    public static final int NR_STATE_CONNECTED = 3;
    public static final int NR_STATE_NONE = 0;
    public static final int NR_STATE_NOT_RESTRICTED = 2;
    public static final int NR_STATE_RESTRICTED = 1;

    @SystemApi
    public static final int REGISTRATION_STATE_DENIED = 3;

    @SystemApi
    public static final int REGISTRATION_STATE_HOME = 1;

    @SystemApi
    public static final int REGISTRATION_STATE_NOT_REGISTERED_OR_SEARCHING = 0;

    @SystemApi
    public static final int REGISTRATION_STATE_NOT_REGISTERED_SEARCHING = 2;

    @SystemApi
    public static final int REGISTRATION_STATE_ROAMING = 5;

    @SystemApi
    public static final int REGISTRATION_STATE_UNKNOWN = 4;
    public static final int SERVICE_TYPE_DATA = 2;
    public static final int SERVICE_TYPE_EMERGENCY = 5;
    public static final int SERVICE_TYPE_SMS = 3;
    public static final int SERVICE_TYPE_UNKNOWN = 0;
    public static final int SERVICE_TYPE_VIDEO = 4;
    public static final int SERVICE_TYPE_VOICE = 1;
    private int mAccessNetworkTechnology;
    private final ArrayList<Integer> mAvailableServices;
    private CellIdentity mCellIdentity;
    private DataSpecificRegistrationInfo mDataSpecificInfo;
    private final int mDomain;
    private final boolean mEmergencyOnly;
    private int mNrState;
    private final int mRegistrationState;
    private final int mRejectCause;
    private int mRoamingType;
    private String mRplmn;
    private final int mTransportType;
    private VoiceSpecificRegistrationInfo mVoiceSpecificInfo;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Domain {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface NRState {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RegistrationState {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ServiceType {
    }

    static String domainToString(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "UNKNOWN" : "CS_PS" : "PS" : "CS";
    }

    public static String nrStateToString(int i) {
        return i != 1 ? i != 2 ? i != 3 ? KeyProperties.DIGEST_NONE : "CONNECTED" : "NOT_RESTRICTED" : "RESTRICTED";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private NetworkRegistrationInfo(int i, int i2, int i3, int i4, int i5, boolean z, List<Integer> list, CellIdentity cellIdentity, String str) {
        this.mDomain = i;
        this.mTransportType = i2;
        this.mRegistrationState = i3;
        this.mRoamingType = i3 == 5 ? 1 : 0;
        this.mAccessNetworkTechnology = i4;
        this.mRejectCause = i5;
        this.mAvailableServices = list != null ? new ArrayList<>(list) : new ArrayList<>();
        this.mCellIdentity = cellIdentity;
        this.mEmergencyOnly = z;
        this.mNrState = 0;
        this.mRplmn = str;
    }

    public NetworkRegistrationInfo(int i, int i2, int i3, int i4, int i5, boolean z, List<Integer> list, CellIdentity cellIdentity, String str, boolean z2, int i6, int i7, int i8) {
        this(i, i2, i3, i4, i5, z, list, cellIdentity, str);
        this.mVoiceSpecificInfo = new VoiceSpecificRegistrationInfo(z2, i6, i7, i8);
    }

    public NetworkRegistrationInfo(int i, int i2, int i3, int i4, int i5, boolean z, List<Integer> list, CellIdentity cellIdentity, String str, int i6, boolean z2, boolean z3, boolean z4, LteVopsSupportInfo lteVopsSupportInfo, boolean z5) {
        this(i, i2, i3, i4, i5, z, list, cellIdentity, str);
        this.mDataSpecificInfo = new DataSpecificRegistrationInfo(i6, z2, z3, z4, lteVopsSupportInfo, z5);
        updateNrState();
    }

    private NetworkRegistrationInfo(Parcel parcel) {
        this.mDomain = parcel.readInt();
        this.mTransportType = parcel.readInt();
        this.mRegistrationState = parcel.readInt();
        this.mRoamingType = parcel.readInt();
        this.mAccessNetworkTechnology = parcel.readInt();
        this.mRejectCause = parcel.readInt();
        this.mEmergencyOnly = parcel.readBoolean();
        ArrayList<Integer> arrayList = new ArrayList<>();
        this.mAvailableServices = arrayList;
        parcel.readList(arrayList, Integer.class.getClassLoader());
        this.mCellIdentity = (CellIdentity) parcel.readParcelable(CellIdentity.class.getClassLoader());
        this.mVoiceSpecificInfo = (VoiceSpecificRegistrationInfo) parcel.readParcelable(VoiceSpecificRegistrationInfo.class.getClassLoader());
        this.mDataSpecificInfo = (DataSpecificRegistrationInfo) parcel.readParcelable(DataSpecificRegistrationInfo.class.getClassLoader());
        this.mNrState = parcel.readInt();
        this.mRplmn = parcel.readString();
    }

    public NetworkRegistrationInfo(NetworkRegistrationInfo networkRegistrationInfo) {
        this.mDomain = networkRegistrationInfo.mDomain;
        this.mTransportType = networkRegistrationInfo.mTransportType;
        this.mRegistrationState = networkRegistrationInfo.mRegistrationState;
        this.mRoamingType = networkRegistrationInfo.mRoamingType;
        this.mAccessNetworkTechnology = networkRegistrationInfo.mAccessNetworkTechnology;
        this.mRejectCause = networkRegistrationInfo.mRejectCause;
        this.mEmergencyOnly = networkRegistrationInfo.mEmergencyOnly;
        this.mAvailableServices = new ArrayList<>(networkRegistrationInfo.mAvailableServices);
        if (networkRegistrationInfo.mCellIdentity != null) {
            Parcel parcelObtain = Parcel.obtain();
            networkRegistrationInfo.mCellIdentity.writeToParcel(parcelObtain, 0);
            parcelObtain.setDataPosition(0);
            this.mCellIdentity = CellIdentity.CREATOR.createFromParcel(parcelObtain);
        }
        if (networkRegistrationInfo.mVoiceSpecificInfo != null) {
            this.mVoiceSpecificInfo = new VoiceSpecificRegistrationInfo(networkRegistrationInfo.mVoiceSpecificInfo);
        }
        if (networkRegistrationInfo.mDataSpecificInfo != null) {
            this.mDataSpecificInfo = new DataSpecificRegistrationInfo(networkRegistrationInfo.mDataSpecificInfo);
        }
        this.mNrState = networkRegistrationInfo.mNrState;
        this.mRplmn = networkRegistrationInfo.mRplmn;
    }

    public int getTransportType() {
        return this.mTransportType;
    }

    public int getDomain() {
        return this.mDomain;
    }

    public int getNrState() {
        return this.mNrState;
    }

    public void setNrState(int i) {
        this.mNrState = i;
    }

    @SystemApi
    public int getRegistrationState() {
        return this.mRegistrationState;
    }

    public boolean isRegistered() {
        int i = this.mRegistrationState;
        return i == 1 || i == 5;
    }

    public boolean isSearching() {
        return this.mRegistrationState == 2;
    }

    public String getRegisteredPlmn() {
        return this.mRplmn;
    }

    public boolean isRoaming() {
        return this.mRoamingType != 0;
    }

    public boolean isInService() {
        int i = this.mRegistrationState;
        return i == 1 || i == 5;
    }

    public void setRoamingType(int i) {
        this.mRoamingType = i;
    }

    @SystemApi
    public int getRoamingType() {
        return this.mRoamingType;
    }

    @SystemApi
    public boolean isEmergencyEnabled() {
        return this.mEmergencyOnly;
    }

    public List<Integer> getAvailableServices() {
        return Collections.unmodifiableList(this.mAvailableServices);
    }

    public int getAccessNetworkTechnology() {
        return this.mAccessNetworkTechnology;
    }

    public void setAccessNetworkTechnology(int i) {
        if (i == 19) {
            i = 13;
            DataSpecificRegistrationInfo dataSpecificRegistrationInfo = this.mDataSpecificInfo;
            if (dataSpecificRegistrationInfo != null) {
                dataSpecificRegistrationInfo.setIsUsingCarrierAggregation(true);
            }
        }
        this.mAccessNetworkTechnology = i;
    }

    @SystemApi
    public int getRejectCause() {
        return this.mRejectCause;
    }

    public CellIdentity getCellIdentity() {
        return this.mCellIdentity;
    }

    public VoiceSpecificRegistrationInfo getVoiceSpecificInfo() {
        return this.mVoiceSpecificInfo;
    }

    @SystemApi
    public DataSpecificRegistrationInfo getDataSpecificInfo() {
        return this.mDataSpecificInfo;
    }

    public static String serviceTypeToString(int i) {
        if (i == 1) {
            return "VOICE";
        }
        if (i == 2) {
            return "DATA";
        }
        if (i == 3) {
            return "SMS";
        }
        if (i == 4) {
            return "VIDEO";
        }
        if (i == 5) {
            return "EMERGENCY";
        }
        return "Unknown service type " + i;
    }

    public static String registrationStateToString(int i) {
        if (i == 0) {
            return "NOT_REG_OR_SEARCHING";
        }
        if (i == 1) {
            return "HOME";
        }
        if (i == 2) {
            return "NOT_REG_SEARCHING";
        }
        if (i == 3) {
            return "DENIED";
        }
        if (i == 4) {
            return "UNKNOWN";
        }
        if (i == 5) {
            return "ROAMING";
        }
        return "Unknown reg state " + i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("NetworkRegistrationInfo{");
        sb.append(" domain=");
        sb.append(domainToString(this.mDomain));
        sb.append(" transportType=");
        sb.append(AccessNetworkConstants.transportTypeToString(this.mTransportType));
        sb.append(" registrationState=");
        sb.append(registrationStateToString(this.mRegistrationState));
        sb.append(" roamingType=");
        sb.append(ServiceState.roamingTypeToString(this.mRoamingType));
        sb.append(" accessNetworkTechnology=");
        sb.append(TelephonyManager.getNetworkTypeName(this.mAccessNetworkTechnology));
        sb.append(" rejectCause=");
        sb.append(this.mRejectCause);
        sb.append(" emergencyEnabled=");
        sb.append(this.mEmergencyOnly);
        sb.append(" availableServices=");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("[");
        ArrayList<Integer> arrayList = this.mAvailableServices;
        sb2.append(arrayList != null ? (String) arrayList.stream().map(new Function() { // from class: android.telephony.-$$Lambda$NetworkRegistrationInfo$1JuZmO5PoYGZY8bHhZYwvmqwOB0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return NetworkRegistrationInfo.serviceTypeToString(((Integer) obj).intValue());
            }
        }).collect(Collectors.joining(",")) : null);
        sb2.append("]");
        sb.append(sb2.toString());
        sb.append(" cellIdentity=");
        sb.append(this.mCellIdentity);
        sb.append(" voiceSpecificInfo=");
        sb.append(this.mVoiceSpecificInfo);
        sb.append(" dataSpecificInfo=");
        sb.append(this.mDataSpecificInfo);
        sb.append(" nrState=");
        sb.append(nrStateToString(this.mNrState));
        sb.append(" rRplmn=");
        sb.append(this.mRplmn);
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mDomain), Integer.valueOf(this.mTransportType), Integer.valueOf(this.mRegistrationState), Integer.valueOf(this.mRoamingType), Integer.valueOf(this.mAccessNetworkTechnology), Integer.valueOf(this.mRejectCause), Boolean.valueOf(this.mEmergencyOnly), this.mAvailableServices, this.mCellIdentity, this.mVoiceSpecificInfo, this.mDataSpecificInfo, Integer.valueOf(this.mNrState), this.mRplmn);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NetworkRegistrationInfo)) {
            return false;
        }
        NetworkRegistrationInfo networkRegistrationInfo = (NetworkRegistrationInfo) obj;
        return this.mDomain == networkRegistrationInfo.mDomain && this.mTransportType == networkRegistrationInfo.mTransportType && this.mRegistrationState == networkRegistrationInfo.mRegistrationState && this.mRoamingType == networkRegistrationInfo.mRoamingType && this.mAccessNetworkTechnology == networkRegistrationInfo.mAccessNetworkTechnology && this.mRejectCause == networkRegistrationInfo.mRejectCause && this.mEmergencyOnly == networkRegistrationInfo.mEmergencyOnly && this.mAvailableServices.equals(networkRegistrationInfo.mAvailableServices) && Objects.equals(this.mCellIdentity, networkRegistrationInfo.mCellIdentity) && Objects.equals(this.mVoiceSpecificInfo, networkRegistrationInfo.mVoiceSpecificInfo) && Objects.equals(this.mDataSpecificInfo, networkRegistrationInfo.mDataSpecificInfo) && TextUtils.equals(this.mRplmn, networkRegistrationInfo.mRplmn) && this.mNrState == networkRegistrationInfo.mNrState;
    }

    @Override // android.os.Parcelable
    @SystemApi
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mDomain);
        parcel.writeInt(this.mTransportType);
        parcel.writeInt(this.mRegistrationState);
        parcel.writeInt(this.mRoamingType);
        parcel.writeInt(this.mAccessNetworkTechnology);
        parcel.writeInt(this.mRejectCause);
        parcel.writeBoolean(this.mEmergencyOnly);
        parcel.writeList(this.mAvailableServices);
        parcel.writeParcelable(this.mCellIdentity, 0);
        parcel.writeParcelable(this.mVoiceSpecificInfo, 0);
        parcel.writeParcelable(this.mDataSpecificInfo, 0);
        parcel.writeInt(this.mNrState);
        parcel.writeString(this.mRplmn);
    }

    public void updateNrState() {
        this.mNrState = 0;
        DataSpecificRegistrationInfo dataSpecificRegistrationInfo = this.mDataSpecificInfo;
        if (dataSpecificRegistrationInfo == null || !dataSpecificRegistrationInfo.isEnDcAvailable) {
            return;
        }
        if (!this.mDataSpecificInfo.isDcNrRestricted && this.mDataSpecificInfo.isNrAvailable) {
            this.mNrState = 2;
        } else {
            this.mNrState = 1;
        }
    }

    public NetworkRegistrationInfo sanitizeLocationInfo() {
        NetworkRegistrationInfo networkRegistrationInfoCopy = copy();
        networkRegistrationInfoCopy.mCellIdentity = null;
        return networkRegistrationInfoCopy;
    }

    private NetworkRegistrationInfo copy() {
        Parcel parcelObtain = Parcel.obtain();
        writeToParcel(parcelObtain, 0);
        parcelObtain.setDataPosition(0);
        NetworkRegistrationInfo networkRegistrationInfo = new NetworkRegistrationInfo(parcelObtain);
        parcelObtain.recycle();
        return networkRegistrationInfo;
    }

    @SystemApi
    public static final class Builder {
        private int mAccessNetworkTechnology;
        private List<Integer> mAvailableServices;
        private CellIdentity mCellIdentity;
        private int mDomain;
        private boolean mEmergencyOnly;
        private int mRegistrationState;
        private int mRejectCause;
        private String mRplmn = "";
        private int mTransportType;

        public Builder setDomain(int i) {
            this.mDomain = i;
            return this;
        }

        public Builder setTransportType(int i) {
            this.mTransportType = i;
            return this;
        }

        public Builder setRegistrationState(int i) {
            this.mRegistrationState = i;
            return this;
        }

        public Builder setAccessNetworkTechnology(int i) {
            this.mAccessNetworkTechnology = i;
            return this;
        }

        public Builder setRejectCause(int i) {
            this.mRejectCause = i;
            return this;
        }

        @SystemApi
        public Builder setEmergencyOnly(boolean z) {
            this.mEmergencyOnly = z;
            return this;
        }

        @SystemApi
        public Builder setAvailableServices(List<Integer> list) {
            this.mAvailableServices = list;
            return this;
        }

        @SystemApi
        public Builder setCellIdentity(CellIdentity cellIdentity) {
            this.mCellIdentity = cellIdentity;
            return this;
        }

        public Builder setRegisteredPlmn(String str) {
            this.mRplmn = str;
            return this;
        }

        @SystemApi
        public NetworkRegistrationInfo build() {
            return new NetworkRegistrationInfo(this.mDomain, this.mTransportType, this.mRegistrationState, this.mAccessNetworkTechnology, this.mRejectCause, this.mEmergencyOnly, this.mAvailableServices, this.mCellIdentity, this.mRplmn);
        }
    }
}
