package android.telephony;

import android.annotation.SystemApi;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.CalendarContract;
import android.telephony.NetworkRegistrationInfo;
import android.text.TextUtils;
import com.android.internal.telephony.DctConstants;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes3.dex */
public class ServiceState implements Parcelable {
    static final boolean DBG = false;
    public static final int DUPLEX_MODE_FDD = 1;
    public static final int DUPLEX_MODE_TDD = 2;
    public static final int DUPLEX_MODE_UNKNOWN = 0;
    private static final String EXTRA_SERVICE_STATE = "android.intent.extra.SERVICE_STATE";
    public static final int FREQUENCY_RANGE_HIGH = 3;
    public static final int FREQUENCY_RANGE_LOW = 1;
    public static final int FREQUENCY_RANGE_MID = 2;
    public static final int FREQUENCY_RANGE_MMWAVE = 4;
    public static final int FREQUENCY_RANGE_UNKNOWN = 0;
    static final String LOG_TAG = "PHONE";
    private static final int NEXT_RIL_RADIO_TECHNOLOGY = 21;
    public static final int RIL_RADIO_CDMA_TECHNOLOGY_BITMASK = 6392;
    public static final int RIL_RADIO_TECHNOLOGY_1xRTT = 6;
    public static final int RIL_RADIO_TECHNOLOGY_EDGE = 2;
    public static final int RIL_RADIO_TECHNOLOGY_EHRPD = 13;
    public static final int RIL_RADIO_TECHNOLOGY_EVDO_0 = 7;
    public static final int RIL_RADIO_TECHNOLOGY_EVDO_A = 8;
    public static final int RIL_RADIO_TECHNOLOGY_EVDO_B = 12;
    public static final int RIL_RADIO_TECHNOLOGY_GPRS = 1;
    public static final int RIL_RADIO_TECHNOLOGY_GSM = 16;
    public static final int RIL_RADIO_TECHNOLOGY_HSDPA = 9;
    public static final int RIL_RADIO_TECHNOLOGY_HSPA = 11;
    public static final int RIL_RADIO_TECHNOLOGY_HSPAP = 15;
    public static final int RIL_RADIO_TECHNOLOGY_HSUPA = 10;
    public static final int RIL_RADIO_TECHNOLOGY_IS95A = 4;
    public static final int RIL_RADIO_TECHNOLOGY_IS95B = 5;
    public static final int RIL_RADIO_TECHNOLOGY_IWLAN = 18;
    public static final int RIL_RADIO_TECHNOLOGY_LTE = 14;
    public static final int RIL_RADIO_TECHNOLOGY_LTE_CA = 19;
    public static final int RIL_RADIO_TECHNOLOGY_NR = 20;
    public static final int RIL_RADIO_TECHNOLOGY_TD_SCDMA = 17;
    public static final int RIL_RADIO_TECHNOLOGY_UMTS = 3;
    public static final int RIL_RADIO_TECHNOLOGY_UNKNOWN = 0;

    @SystemApi
    public static final int ROAMING_TYPE_DOMESTIC = 2;

    @SystemApi
    public static final int ROAMING_TYPE_INTERNATIONAL = 3;

    @SystemApi
    public static final int ROAMING_TYPE_NOT_ROAMING = 0;

    @SystemApi
    public static final int ROAMING_TYPE_UNKNOWN = 1;
    public static final int STATE_EMERGENCY_ONLY = 2;
    public static final int STATE_IN_SERVICE = 0;
    public static final int STATE_OUT_OF_SERVICE = 1;
    public static final int STATE_POWER_OFF = 3;
    public static final int UNKNOWN_ID = -1;
    static final boolean VDBG = false;
    private int mCdmaDefaultRoamingIndicator;
    private int mCdmaEriIconIndex;
    private int mCdmaEriIconMode;
    private int mCdmaRoamingIndicator;
    private int[] mCellBandwidths;
    private int mChannelNumber;
    private boolean mCssIndicator;
    private int mDataRegState;
    private boolean mIsDataRoamingFromRegistration;
    private boolean mIsEmergencyOnly;
    private boolean mIsIwlanPreferred;
    private boolean mIsManualNetworkSelection;
    private int mLteEarfcnRsrpBoost;
    private int mNetworkId;
    private final List<NetworkRegistrationInfo> mNetworkRegistrationInfos;
    private int mNrFrequencyRange;
    private String mOperatorAlphaLong;
    private String mOperatorAlphaLongRaw;
    private String mOperatorAlphaShort;
    private String mOperatorAlphaShortRaw;
    private String mOperatorNumeric;
    private int mSystemId;
    private int mVoiceRegState;
    private static final List<Integer> FREQUENCY_RANGE_ORDER = Arrays.asList(0, 1, 2, 3, 4);
    public static final Parcelable.Creator<ServiceState> CREATOR = new Parcelable.Creator<ServiceState>() { // from class: android.telephony.ServiceState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ServiceState createFromParcel(Parcel parcel) {
            return new ServiceState(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ServiceState[] newArray(int i) {
            return new ServiceState[i];
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    public @interface DuplexMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FrequencyRange {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RegState {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RilRadioTechnology {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RoamingType {
    }

    public static boolean bitmaskHasTech(int i, int i2) {
        if (i == 0) {
            return true;
        }
        return i2 >= 1 && (i & (1 << (i2 - 1))) != 0;
    }

    public static int getBitmaskForTech(int i) {
        if (i >= 1) {
            return 1 << (i - 1);
        }
        return 0;
    }

    public static final String getRoamingLogString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? "UNKNOWN" : "International Roaming" : "Domestic Roaming" : "roaming" : CalendarContract.CalendarCache.TIMEZONE_TYPE_HOME;
    }

    public static boolean isCdma(int i) {
        return i == 4 || i == 5 || i == 6 || i == 7 || i == 8 || i == 12 || i == 13;
    }

    public static boolean isGsm(int i) {
        return i == 1 || i == 2 || i == 3 || i == 9 || i == 10 || i == 11 || i == 14 || i == 15 || i == 16 || i == 17 || i == 18 || i == 19 || i == 20;
    }

    public static boolean isPsOnlyTech(int i) {
        return i == 14 || i == 19 || i == 20;
    }

    public static int networkTypeToRilRadioTechnology(int i) {
        switch (i) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            case 5:
                return 7;
            case 6:
                return 8;
            case 7:
                return 6;
            case 8:
                return 9;
            case 9:
                return 10;
            case 10:
                return 11;
            case 11:
            default:
                return 0;
            case 12:
                return 12;
            case 13:
                return 14;
            case 14:
                return 13;
            case 15:
                return 15;
            case 16:
                return 16;
            case 17:
                return 17;
            case 18:
                return 18;
            case 19:
                return 19;
            case 20:
                return 20;
        }
    }

    public static int rilRadioTechnologyToAccessNetworkType(int i) {
        switch (i) {
            case 1:
            case 2:
            case 16:
                return 1;
            case 3:
            case 9:
            case 10:
            case 11:
            case 15:
            case 17:
                return 2;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 12:
            case 13:
                return 4;
            case 14:
            case 19:
                return 3;
            case 18:
                return 5;
            case 20:
                return 6;
            default:
                return 0;
        }
    }

    public static int rilRadioTechnologyToNetworkType(int i) {
        switch (i) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
            case 5:
                return 4;
            case 6:
                return 7;
            case 7:
                return 5;
            case 8:
                return 6;
            case 9:
                return 8;
            case 10:
                return 9;
            case 11:
                return 10;
            case 12:
                return 12;
            case 13:
                return 14;
            case 14:
                return 13;
            case 15:
                return 15;
            case 16:
                return 16;
            case 17:
                return 17;
            case 18:
                return 18;
            case 19:
                return 19;
            case 20:
                return 20;
            default:
                return 0;
        }
    }

    public static String rilServiceStateToString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? "UNKNOWN" : "POWER_OFF" : "EMERGENCY_ONLY" : "OUT_OF_SERVICE" : "IN_SERVICE";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static ServiceState newFromBundle(Bundle bundle) {
        ServiceState serviceState = new ServiceState();
        serviceState.setFromNotifierBundle(bundle);
        return serviceState;
    }

    public ServiceState() {
        this.mVoiceRegState = 1;
        this.mDataRegState = 1;
        this.mCellBandwidths = new int[0];
        this.mLteEarfcnRsrpBoost = 0;
        this.mNetworkRegistrationInfos = new ArrayList();
    }

    public ServiceState(ServiceState serviceState) {
        this.mVoiceRegState = 1;
        this.mDataRegState = 1;
        this.mCellBandwidths = new int[0];
        this.mLteEarfcnRsrpBoost = 0;
        this.mNetworkRegistrationInfos = new ArrayList();
        copyFrom(serviceState);
    }

    protected void copyFrom(ServiceState serviceState) {
        this.mVoiceRegState = serviceState.mVoiceRegState;
        this.mDataRegState = serviceState.mDataRegState;
        this.mOperatorAlphaLong = serviceState.mOperatorAlphaLong;
        this.mOperatorAlphaShort = serviceState.mOperatorAlphaShort;
        this.mOperatorNumeric = serviceState.mOperatorNumeric;
        this.mIsManualNetworkSelection = serviceState.mIsManualNetworkSelection;
        this.mCssIndicator = serviceState.mCssIndicator;
        this.mNetworkId = serviceState.mNetworkId;
        this.mSystemId = serviceState.mSystemId;
        this.mCdmaRoamingIndicator = serviceState.mCdmaRoamingIndicator;
        this.mCdmaDefaultRoamingIndicator = serviceState.mCdmaDefaultRoamingIndicator;
        this.mCdmaEriIconIndex = serviceState.mCdmaEriIconIndex;
        this.mCdmaEriIconMode = serviceState.mCdmaEriIconMode;
        this.mIsEmergencyOnly = serviceState.mIsEmergencyOnly;
        this.mChannelNumber = serviceState.mChannelNumber;
        int[] iArr = serviceState.mCellBandwidths;
        this.mCellBandwidths = iArr == null ? null : Arrays.copyOf(iArr, iArr.length);
        this.mLteEarfcnRsrpBoost = serviceState.mLteEarfcnRsrpBoost;
        synchronized (this.mNetworkRegistrationInfos) {
            this.mNetworkRegistrationInfos.clear();
            this.mNetworkRegistrationInfos.addAll(serviceState.getNetworkRegistrationInfoList());
        }
        this.mNrFrequencyRange = serviceState.mNrFrequencyRange;
        this.mOperatorAlphaLongRaw = serviceState.mOperatorAlphaLongRaw;
        this.mOperatorAlphaShortRaw = serviceState.mOperatorAlphaShortRaw;
        this.mIsDataRoamingFromRegistration = serviceState.mIsDataRoamingFromRegistration;
        this.mIsIwlanPreferred = serviceState.mIsIwlanPreferred;
    }

    @Deprecated
    public ServiceState(Parcel parcel) {
        this.mVoiceRegState = 1;
        this.mDataRegState = 1;
        this.mCellBandwidths = new int[0];
        this.mLteEarfcnRsrpBoost = 0;
        ArrayList arrayList = new ArrayList();
        this.mNetworkRegistrationInfos = arrayList;
        this.mVoiceRegState = parcel.readInt();
        this.mDataRegState = parcel.readInt();
        this.mOperatorAlphaLong = parcel.readString();
        this.mOperatorAlphaShort = parcel.readString();
        this.mOperatorNumeric = parcel.readString();
        this.mIsManualNetworkSelection = parcel.readInt() != 0;
        this.mCssIndicator = parcel.readInt() != 0;
        this.mNetworkId = parcel.readInt();
        this.mSystemId = parcel.readInt();
        this.mCdmaRoamingIndicator = parcel.readInt();
        this.mCdmaDefaultRoamingIndicator = parcel.readInt();
        this.mCdmaEriIconIndex = parcel.readInt();
        this.mCdmaEriIconMode = parcel.readInt();
        this.mIsEmergencyOnly = parcel.readInt() != 0;
        this.mLteEarfcnRsrpBoost = parcel.readInt();
        synchronized (arrayList) {
            parcel.readList(arrayList, NetworkRegistrationInfo.class.getClassLoader());
        }
        this.mChannelNumber = parcel.readInt();
        this.mCellBandwidths = parcel.createIntArray();
        this.mNrFrequencyRange = parcel.readInt();
        this.mOperatorAlphaLongRaw = parcel.readString();
        this.mOperatorAlphaShortRaw = parcel.readString();
        this.mIsDataRoamingFromRegistration = parcel.readBoolean();
        this.mIsIwlanPreferred = parcel.readBoolean();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mVoiceRegState);
        parcel.writeInt(this.mDataRegState);
        parcel.writeString(this.mOperatorAlphaLong);
        parcel.writeString(this.mOperatorAlphaShort);
        parcel.writeString(this.mOperatorNumeric);
        parcel.writeInt(this.mIsManualNetworkSelection ? 1 : 0);
        parcel.writeInt(this.mCssIndicator ? 1 : 0);
        parcel.writeInt(this.mNetworkId);
        parcel.writeInt(this.mSystemId);
        parcel.writeInt(this.mCdmaRoamingIndicator);
        parcel.writeInt(this.mCdmaDefaultRoamingIndicator);
        parcel.writeInt(this.mCdmaEriIconIndex);
        parcel.writeInt(this.mCdmaEriIconMode);
        parcel.writeInt(this.mIsEmergencyOnly ? 1 : 0);
        parcel.writeInt(this.mLteEarfcnRsrpBoost);
        synchronized (this.mNetworkRegistrationInfos) {
            parcel.writeList(this.mNetworkRegistrationInfos);
        }
        parcel.writeInt(this.mChannelNumber);
        parcel.writeIntArray(this.mCellBandwidths);
        parcel.writeInt(this.mNrFrequencyRange);
        parcel.writeString(this.mOperatorAlphaLongRaw);
        parcel.writeString(this.mOperatorAlphaShortRaw);
        parcel.writeBoolean(this.mIsDataRoamingFromRegistration);
        parcel.writeBoolean(this.mIsIwlanPreferred);
    }

    public int getState() {
        return getVoiceRegState();
    }

    public int getVoiceRegState() {
        return this.mVoiceRegState;
    }

    public int getDataRegState() {
        return this.mDataRegState;
    }

    public int getDataRegistrationState() {
        return getDataRegState();
    }

    public int getDuplexMode() {
        if (isPsOnlyTech(getRilDataRadioTechnology())) {
            return AccessNetworkUtils.getDuplexModeForEutranBand(AccessNetworkUtils.getOperatingBandForEarfcn(this.mChannelNumber));
        }
        return 0;
    }

    public int getChannelNumber() {
        return this.mChannelNumber;
    }

    public int[] getCellBandwidths() {
        int[] iArr = this.mCellBandwidths;
        return iArr == null ? new int[0] : iArr;
    }

    public boolean getRoaming() {
        return getVoiceRoaming() || getDataRoaming();
    }

    public boolean getVoiceRoaming() {
        return getVoiceRoamingType() != 0;
    }

    public int getVoiceRoamingType() {
        NetworkRegistrationInfo networkRegistrationInfo = getNetworkRegistrationInfo(1, 1);
        if (networkRegistrationInfo != null) {
            return networkRegistrationInfo.getRoamingType();
        }
        return 0;
    }

    public boolean getDataRoaming() {
        return getDataRoamingType() != 0;
    }

    public void setDataRoamingFromRegistration(boolean z) {
        this.mIsDataRoamingFromRegistration = z;
    }

    public boolean getDataRoamingFromRegistration() {
        return this.mIsDataRoamingFromRegistration;
    }

    public int getDataRoamingType() {
        NetworkRegistrationInfo networkRegistrationInfo = getNetworkRegistrationInfo(2, 1);
        if (networkRegistrationInfo != null) {
            return networkRegistrationInfo.getRoamingType();
        }
        return 0;
    }

    public boolean isEmergencyOnly() {
        return this.mIsEmergencyOnly;
    }

    public int getCdmaRoamingIndicator() {
        return this.mCdmaRoamingIndicator;
    }

    public int getCdmaDefaultRoamingIndicator() {
        return this.mCdmaDefaultRoamingIndicator;
    }

    public int getCdmaEriIconIndex() {
        return this.mCdmaEriIconIndex;
    }

    public int getCdmaEriIconMode() {
        return this.mCdmaEriIconMode;
    }

    public String getOperatorAlphaLong() {
        return this.mOperatorAlphaLong;
    }

    public String getVoiceOperatorAlphaLong() {
        return this.mOperatorAlphaLong;
    }

    public String getOperatorAlphaShort() {
        return this.mOperatorAlphaShort;
    }

    public String getVoiceOperatorAlphaShort() {
        return this.mOperatorAlphaShort;
    }

    public String getDataOperatorAlphaShort() {
        return this.mOperatorAlphaShort;
    }

    public String getOperatorAlpha() {
        if (TextUtils.isEmpty(this.mOperatorAlphaLong)) {
            return this.mOperatorAlphaShort;
        }
        return this.mOperatorAlphaLong;
    }

    public String getOperatorNumeric() {
        return this.mOperatorNumeric;
    }

    public String getVoiceOperatorNumeric() {
        return this.mOperatorNumeric;
    }

    public String getDataOperatorNumeric() {
        return this.mOperatorNumeric;
    }

    public boolean getIsManualSelection() {
        return this.mIsManualNetworkSelection;
    }

    public int hashCode() {
        int iHash;
        synchronized (this.mNetworkRegistrationInfos) {
            iHash = Objects.hash(Integer.valueOf(this.mVoiceRegState), Integer.valueOf(this.mDataRegState), Integer.valueOf(this.mChannelNumber), Integer.valueOf(Arrays.hashCode(this.mCellBandwidths)), this.mOperatorAlphaLong, this.mOperatorAlphaShort, this.mOperatorNumeric, Boolean.valueOf(this.mIsManualNetworkSelection), Boolean.valueOf(this.mCssIndicator), Integer.valueOf(this.mNetworkId), Integer.valueOf(this.mSystemId), Integer.valueOf(this.mCdmaRoamingIndicator), Integer.valueOf(this.mCdmaDefaultRoamingIndicator), Integer.valueOf(this.mCdmaEriIconIndex), Integer.valueOf(this.mCdmaEriIconMode), Boolean.valueOf(this.mIsEmergencyOnly), Integer.valueOf(this.mLteEarfcnRsrpBoost), this.mNetworkRegistrationInfos, Integer.valueOf(this.mNrFrequencyRange), this.mOperatorAlphaLongRaw, this.mOperatorAlphaShortRaw, Boolean.valueOf(this.mIsDataRoamingFromRegistration), Boolean.valueOf(this.mIsIwlanPreferred));
        }
        return iHash;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof ServiceState)) {
            return false;
        }
        ServiceState serviceState = (ServiceState) obj;
        synchronized (this.mNetworkRegistrationInfos) {
            if (this.mVoiceRegState == serviceState.mVoiceRegState && this.mDataRegState == serviceState.mDataRegState && this.mIsManualNetworkSelection == serviceState.mIsManualNetworkSelection && this.mChannelNumber == serviceState.mChannelNumber && Arrays.equals(this.mCellBandwidths, serviceState.mCellBandwidths) && equalsHandlesNulls(this.mOperatorAlphaLong, serviceState.mOperatorAlphaLong) && equalsHandlesNulls(this.mOperatorAlphaShort, serviceState.mOperatorAlphaShort) && equalsHandlesNulls(this.mOperatorNumeric, serviceState.mOperatorNumeric) && equalsHandlesNulls(Boolean.valueOf(this.mCssIndicator), Boolean.valueOf(serviceState.mCssIndicator)) && equalsHandlesNulls(Integer.valueOf(this.mNetworkId), Integer.valueOf(serviceState.mNetworkId)) && equalsHandlesNulls(Integer.valueOf(this.mSystemId), Integer.valueOf(serviceState.mSystemId)) && equalsHandlesNulls(Integer.valueOf(this.mCdmaRoamingIndicator), Integer.valueOf(serviceState.mCdmaRoamingIndicator)) && equalsHandlesNulls(Integer.valueOf(this.mCdmaDefaultRoamingIndicator), Integer.valueOf(serviceState.mCdmaDefaultRoamingIndicator)) && this.mIsEmergencyOnly == serviceState.mIsEmergencyOnly && equalsHandlesNulls(this.mOperatorAlphaLongRaw, serviceState.mOperatorAlphaLongRaw) && equalsHandlesNulls(this.mOperatorAlphaShortRaw, serviceState.mOperatorAlphaShortRaw) && this.mNetworkRegistrationInfos.size() == serviceState.mNetworkRegistrationInfos.size() && this.mNetworkRegistrationInfos.containsAll(serviceState.mNetworkRegistrationInfos) && this.mNrFrequencyRange == serviceState.mNrFrequencyRange && this.mIsDataRoamingFromRegistration == serviceState.mIsDataRoamingFromRegistration && this.mIsIwlanPreferred == serviceState.mIsIwlanPreferred) {
                z = true;
            }
        }
        return z;
    }

    public static String roamingTypeToString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? "Unknown roaming type " + i : "INTERNATIONAL" : "DOMESTIC" : "UNKNOWN" : "NOT_ROAMING";
    }

    public static String rilRadioTechnologyToString(int i) {
        switch (i) {
            case 0:
                return "Unknown";
            case 1:
                return "GPRS";
            case 2:
                return "EDGE";
            case 3:
                return "UMTS";
            case 4:
                return "CDMA-IS95A";
            case 5:
                return "CDMA-IS95B";
            case 6:
                return "1xRTT";
            case 7:
                return "EvDo-rev.0";
            case 8:
                return "EvDo-rev.A";
            case 9:
                return "HSDPA";
            case 10:
                return "HSUPA";
            case 11:
                return "HSPA";
            case 12:
                return "EvDo-rev.B";
            case 13:
                return "eHRPD";
            case 14:
                return DctConstants.RAT_NAME_LTE;
            case 15:
                return "HSPAP";
            case 16:
                return "GSM";
            case 17:
                return "TD-SCDMA";
            case 18:
                return "IWLAN";
            case 19:
                return "LTE_CA";
            case 20:
                return "NR_SA";
            default:
                com.android.telephony.Rlog.w(LOG_TAG, "Unexpected radioTechnology=" + i);
                return "Unexpected";
        }
    }

    public static String frequencyRangeToString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? Integer.toString(i) : "MMWAVE" : "HIGH" : "MID" : "LOW" : "UNKNOWN";
    }

    public String toString() {
        String str;
        synchronized (this.mNetworkRegistrationInfos) {
            str = "{mVoiceRegState=" + this.mVoiceRegState + ("(" + rilServiceStateToString(this.mVoiceRegState) + ")") + ", mDataRegState=" + this.mDataRegState + ("(" + rilServiceStateToString(this.mDataRegState) + ")") + ", mChannelNumber=" + this.mChannelNumber + ", duplexMode()=" + getDuplexMode() + ", mCellBandwidths=" + Arrays.toString(this.mCellBandwidths) + ", mOperatorAlphaLong=" + this.mOperatorAlphaLong + ", mOperatorAlphaShort=" + this.mOperatorAlphaShort + ", isManualNetworkSelection=" + this.mIsManualNetworkSelection + (this.mIsManualNetworkSelection ? "(manual)" : "(automatic)") + ", getRilVoiceRadioTechnology=" + getRilVoiceRadioTechnology() + ("(" + rilRadioTechnologyToString(getRilVoiceRadioTechnology()) + ")") + ", getRilDataRadioTechnology=" + getRilDataRadioTechnology() + ("(" + rilRadioTechnologyToString(getRilDataRadioTechnology()) + ")") + ", mCssIndicator=" + (this.mCssIndicator ? "supported" : "unsupported") + ", mNetworkId=" + this.mNetworkId + ", mSystemId=" + this.mSystemId + ", mCdmaRoamingIndicator=" + this.mCdmaRoamingIndicator + ", mCdmaDefaultRoamingIndicator=" + this.mCdmaDefaultRoamingIndicator + ", mIsEmergencyOnly=" + this.mIsEmergencyOnly + ", isUsingCarrierAggregation=" + isUsingCarrierAggregation() + ", mLteEarfcnRsrpBoost=" + this.mLteEarfcnRsrpBoost + ", mNetworkRegistrationInfos=" + this.mNetworkRegistrationInfos + ", mNrFrequencyRange=" + this.mNrFrequencyRange + ", mOperatorAlphaLongRaw=" + this.mOperatorAlphaLongRaw + ", mOperatorAlphaShortRaw=" + this.mOperatorAlphaShortRaw + ", mIsDataRoamingFromRegistration=" + this.mIsDataRoamingFromRegistration + ", mIsIwlanPreferred=" + this.mIsIwlanPreferred + "}";
        }
        return str;
    }

    private void init() {
        this.mVoiceRegState = 1;
        this.mDataRegState = 1;
        this.mChannelNumber = -1;
        this.mCellBandwidths = new int[0];
        this.mOperatorAlphaLong = null;
        this.mOperatorAlphaShort = null;
        this.mOperatorNumeric = null;
        this.mIsManualNetworkSelection = false;
        this.mCssIndicator = false;
        this.mNetworkId = -1;
        this.mSystemId = -1;
        this.mCdmaRoamingIndicator = -1;
        this.mCdmaDefaultRoamingIndicator = -1;
        this.mCdmaEriIconIndex = -1;
        this.mCdmaEriIconMode = -1;
        this.mIsEmergencyOnly = false;
        this.mLteEarfcnRsrpBoost = 0;
        this.mNrFrequencyRange = 0;
        synchronized (this.mNetworkRegistrationInfos) {
            this.mNetworkRegistrationInfos.clear();
            addNetworkRegistrationInfo(new NetworkRegistrationInfo.Builder().setDomain(1).setTransportType(1).setRegistrationState(4).build());
            addNetworkRegistrationInfo(new NetworkRegistrationInfo.Builder().setDomain(2).setTransportType(1).setRegistrationState(4).build());
        }
        this.mOperatorAlphaLongRaw = null;
        this.mOperatorAlphaShortRaw = null;
        this.mIsDataRoamingFromRegistration = false;
        this.mIsIwlanPreferred = false;
    }

    public void setStateOutOfService() {
        init();
    }

    public void setStateOff() {
        init();
        this.mVoiceRegState = 3;
        this.mDataRegState = 3;
    }

    public void setState(int i) {
        setVoiceRegState(i);
    }

    public void setVoiceRegState(int i) {
        this.mVoiceRegState = i;
    }

    public void setDataRegState(int i) {
        this.mDataRegState = i;
    }

    public void setCellBandwidths(int[] iArr) {
        this.mCellBandwidths = iArr;
    }

    public void setChannelNumber(int i) {
        this.mChannelNumber = i;
    }

    public void setRoaming(boolean z) {
        setVoiceRoaming(z);
        setDataRoaming(z);
    }

    public void setVoiceRoaming(boolean z) {
        setVoiceRoamingType(z ? 1 : 0);
    }

    public void setVoiceRoamingType(int i) {
        NetworkRegistrationInfo networkRegistrationInfo = getNetworkRegistrationInfo(1, 1);
        if (networkRegistrationInfo == null) {
            networkRegistrationInfo = new NetworkRegistrationInfo.Builder().setDomain(1).setTransportType(1).build();
        }
        networkRegistrationInfo.setRoamingType(i);
        addNetworkRegistrationInfo(networkRegistrationInfo);
    }

    public void setDataRoaming(boolean z) {
        setDataRoamingType(z ? 1 : 0);
    }

    public void setDataRoamingType(int i) {
        NetworkRegistrationInfo networkRegistrationInfo = getNetworkRegistrationInfo(2, 1);
        if (networkRegistrationInfo == null) {
            networkRegistrationInfo = new NetworkRegistrationInfo.Builder().setDomain(2).setTransportType(1).build();
        }
        networkRegistrationInfo.setRoamingType(i);
        addNetworkRegistrationInfo(networkRegistrationInfo);
    }

    public void setEmergencyOnly(boolean z) {
        this.mIsEmergencyOnly = z;
    }

    public void setCdmaRoamingIndicator(int i) {
        this.mCdmaRoamingIndicator = i;
    }

    public void setCdmaDefaultRoamingIndicator(int i) {
        this.mCdmaDefaultRoamingIndicator = i;
    }

    public void setCdmaEriIconIndex(int i) {
        this.mCdmaEriIconIndex = i;
    }

    public void setCdmaEriIconMode(int i) {
        this.mCdmaEriIconMode = i;
    }

    public void setOperatorName(String str, String str2, String str3) {
        this.mOperatorAlphaLong = str;
        this.mOperatorAlphaShort = str2;
        this.mOperatorNumeric = str3;
    }

    public void setOperatorAlphaLong(String str) {
        this.mOperatorAlphaLong = str;
    }

    public void setIsManualSelection(boolean z) {
        this.mIsManualNetworkSelection = z;
    }

    private static boolean equalsHandlesNulls(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    private void setFromNotifierBundle(Bundle bundle) {
        ServiceState serviceState = (ServiceState) bundle.getParcelable(EXTRA_SERVICE_STATE);
        if (serviceState != null) {
            copyFrom(serviceState);
        }
    }

    public void fillInNotifierBundle(Bundle bundle) {
        bundle.putParcelable(EXTRA_SERVICE_STATE, this);
        bundle.putInt(Intent.EXTRA_VOICE_REG_STATE, this.mVoiceRegState);
        bundle.putInt(Intent.EXTRA_DATA_REG_STATE, this.mDataRegState);
        bundle.putInt(Intent.EXTRA_DATA_ROAMING_TYPE, getDataRoamingType());
        bundle.putInt(Intent.EXTRA_VOICE_ROAMING_TYPE, getVoiceRoamingType());
        bundle.putString(Intent.EXTRA_OPERATOR_ALPHA_LONG, this.mOperatorAlphaLong);
        bundle.putString(Intent.EXTRA_OPERATOR_ALPHA_SHORT, this.mOperatorAlphaShort);
        bundle.putString(Intent.EXTRA_OPERATOR_NUMERIC, this.mOperatorNumeric);
        bundle.putString(Intent.EXTRA_DATA_OPERATOR_ALPHA_LONG, this.mOperatorAlphaLong);
        bundle.putString(Intent.EXTRA_DATA_OPERATOR_ALPHA_SHORT, this.mOperatorAlphaShort);
        bundle.putString(Intent.EXTRA_DATA_OPERATOR_NUMERIC, this.mOperatorNumeric);
        bundle.putBoolean(Intent.EXTRA_MANUAL, this.mIsManualNetworkSelection);
        bundle.putInt(Intent.EXTRA_VOICE_RADIO_TECH, getRilVoiceRadioTechnology());
        bundle.putInt(Intent.EXTRA_DATA_RADIO_TECH, getRadioTechnology());
        bundle.putBoolean(Intent.EXTRA_CSS_INDICATOR, this.mCssIndicator);
        bundle.putInt(Intent.EXTRA_NETWORK_ID, this.mNetworkId);
        bundle.putInt(Intent.EXTRA_SYSTEM_ID, this.mSystemId);
        bundle.putInt(Intent.EXTRA_CDMA_ROAMING_INDICATOR, this.mCdmaRoamingIndicator);
        bundle.putInt(Intent.EXTRA_CDMA_DEFAULT_ROAMING_INDICATOR, this.mCdmaDefaultRoamingIndicator);
        bundle.putBoolean(Intent.EXTRA_EMERGENCY_ONLY, this.mIsEmergencyOnly);
        bundle.putBoolean(Intent.EXTRA_IS_DATA_ROAMING_FROM_REGISTRATION, getDataRoamingFromRegistration());
        bundle.putBoolean(Intent.EXTRA_IS_USING_CARRIER_AGGREGATION, isUsingCarrierAggregation());
        bundle.putInt(Intent.EXTRA_LTE_EARFCN_RSRP_BOOST, this.mLteEarfcnRsrpBoost);
        bundle.putInt("ChannelNumber", this.mChannelNumber);
        bundle.putIntArray("CellBandwidths", this.mCellBandwidths);
        bundle.putInt("mNrFrequencyRange", this.mNrFrequencyRange);
        bundle.putString("operator-alpha-long-raw", this.mOperatorAlphaLongRaw);
        bundle.putString("operator-alpha-short-raw", this.mOperatorAlphaShortRaw);
    }

    public void setRilVoiceRadioTechnology(int i) {
        com.android.telephony.Rlog.e(LOG_TAG, "ServiceState.setRilVoiceRadioTechnology() called. It's encouraged to use addNetworkRegistrationInfo() instead *******");
        NetworkRegistrationInfo networkRegistrationInfo = getNetworkRegistrationInfo(1, 1);
        if (networkRegistrationInfo == null) {
            networkRegistrationInfo = new NetworkRegistrationInfo.Builder().setDomain(1).setTransportType(1).build();
        }
        networkRegistrationInfo.setAccessNetworkTechnology(rilRadioTechnologyToNetworkType(i));
        addNetworkRegistrationInfo(networkRegistrationInfo);
    }

    public void setRilDataRadioTechnology(int i) {
        com.android.telephony.Rlog.e(LOG_TAG, "ServiceState.setRilDataRadioTechnology() called. It's encouraged to use addNetworkRegistrationInfo() instead *******");
        NetworkRegistrationInfo networkRegistrationInfo = getNetworkRegistrationInfo(2, 1);
        if (networkRegistrationInfo == null) {
            networkRegistrationInfo = new NetworkRegistrationInfo.Builder().setDomain(2).setTransportType(1).build();
        }
        networkRegistrationInfo.setAccessNetworkTechnology(rilRadioTechnologyToNetworkType(i));
        addNetworkRegistrationInfo(networkRegistrationInfo);
    }

    public boolean isUsingCarrierAggregation() {
        DataSpecificRegistrationInfo dataSpecificInfo;
        NetworkRegistrationInfo networkRegistrationInfo = getNetworkRegistrationInfo(2, 1);
        return ((networkRegistrationInfo == null || (dataSpecificInfo = networkRegistrationInfo.getDataSpecificInfo()) == null) ? false : dataSpecificInfo.isUsingCarrierAggregation()) || getCellBandwidths().length > 1;
    }

    public void setIsUsingCarrierAggregation(boolean z) {
        DataSpecificRegistrationInfo dataSpecificInfo;
        NetworkRegistrationInfo networkRegistrationInfo = getNetworkRegistrationInfo(2, 1);
        if (networkRegistrationInfo == null || (dataSpecificInfo = networkRegistrationInfo.getDataSpecificInfo()) == null) {
            return;
        }
        dataSpecificInfo.setIsUsingCarrierAggregation(z);
        addNetworkRegistrationInfo(networkRegistrationInfo);
    }

    public int getNrFrequencyRange() {
        return this.mNrFrequencyRange;
    }

    public int getNrState() {
        NetworkRegistrationInfo networkRegistrationInfo = getNetworkRegistrationInfo(2, 1);
        if (networkRegistrationInfo == null) {
            return 0;
        }
        return networkRegistrationInfo.getNrState();
    }

    public void setNrFrequencyRange(int i) {
        this.mNrFrequencyRange = i;
    }

    public int getLteEarfcnRsrpBoost() {
        return this.mLteEarfcnRsrpBoost;
    }

    public void setLteEarfcnRsrpBoost(int i) {
        this.mLteEarfcnRsrpBoost = i;
    }

    public void setCssIndicator(int i) {
        this.mCssIndicator = i != 0;
    }

    public void setCdmaSystemAndNetworkId(int i, int i2) {
        this.mSystemId = i;
        this.mNetworkId = i2;
    }

    public int getRilVoiceRadioTechnology() {
        NetworkRegistrationInfo networkRegistrationInfo = getNetworkRegistrationInfo(1, 1);
        if (networkRegistrationInfo != null) {
            return networkTypeToRilRadioTechnology(networkRegistrationInfo.getAccessNetworkTechnology());
        }
        return 0;
    }

    public int getRilDataRadioTechnology() {
        return networkTypeToRilRadioTechnology(getDataNetworkType());
    }

    public int getRadioTechnology() {
        com.android.telephony.Rlog.e(LOG_TAG, "ServiceState.getRadioTechnology() DEPRECATED will be removed *******");
        return getRilDataRadioTechnology();
    }

    public int getDataNetworkType() {
        NetworkRegistrationInfo networkRegistrationInfo = getNetworkRegistrationInfo(2, 2);
        NetworkRegistrationInfo networkRegistrationInfo2 = getNetworkRegistrationInfo(2, 1);
        if (networkRegistrationInfo == null || !networkRegistrationInfo.isInService()) {
            if (networkRegistrationInfo2 != null) {
                return networkRegistrationInfo2.getAccessNetworkTechnology();
            }
            return 0;
        }
        if (!networkRegistrationInfo2.isInService() || this.mIsIwlanPreferred) {
            return networkRegistrationInfo.getAccessNetworkTechnology();
        }
        return networkRegistrationInfo2.getAccessNetworkTechnology();
    }

    public int getVoiceNetworkType() {
        NetworkRegistrationInfo networkRegistrationInfo = getNetworkRegistrationInfo(1, 1);
        if (networkRegistrationInfo != null) {
            return networkRegistrationInfo.getAccessNetworkTechnology();
        }
        return 0;
    }

    public int getCssIndicator() {
        return this.mCssIndicator ? 1 : 0;
    }

    public int getCdmaNetworkId() {
        return this.mNetworkId;
    }

    public int getCdmaSystemId() {
        return this.mSystemId;
    }

    public static boolean bearerBitmapHasCdma(int i) {
        return (convertNetworkTypeBitmaskToBearerBitmask(i) & RIL_RADIO_CDMA_TECHNOLOGY_BITMASK) != 0;
    }

    public static int getBitmaskFromString(String str) {
        int bitmaskForTech = 0;
        for (String str2 : str.split("\\|")) {
            try {
                int i = Integer.parseInt(str2.trim());
                if (i == 0) {
                    return 0;
                }
                bitmaskForTech |= getBitmaskForTech(i);
            } catch (NumberFormatException unused) {
                return 0;
            }
        }
        return bitmaskForTech;
    }

    public static int convertNetworkTypeBitmaskToBearerBitmask(int i) {
        if (i == 0) {
            return 0;
        }
        int bitmaskForTech = 0;
        for (int i2 = 0; i2 < 21; i2++) {
            if (bitmaskHasTech(i, rilRadioTechnologyToNetworkType(i2))) {
                bitmaskForTech |= getBitmaskForTech(i2);
            }
        }
        return bitmaskForTech;
    }

    public static int convertBearerBitmaskToNetworkTypeBitmask(int i) {
        if (i == 0) {
            return 0;
        }
        int bitmaskForTech = 0;
        for (int i2 = 0; i2 < 21; i2++) {
            if (bitmaskHasTech(i, i2)) {
                bitmaskForTech |= getBitmaskForTech(rilRadioTechnologyToNetworkType(i2));
            }
        }
        return bitmaskForTech;
    }

    public static ServiceState mergeServiceStates(ServiceState serviceState, ServiceState serviceState2) {
        if (serviceState2.mVoiceRegState != 0) {
            return serviceState;
        }
        ServiceState serviceState3 = new ServiceState(serviceState);
        serviceState3.mVoiceRegState = serviceState2.mVoiceRegState;
        serviceState3.mIsEmergencyOnly = false;
        return serviceState3;
    }

    public List<NetworkRegistrationInfo> getNetworkRegistrationInfoList() {
        ArrayList arrayList;
        synchronized (this.mNetworkRegistrationInfos) {
            arrayList = new ArrayList();
            Iterator<NetworkRegistrationInfo> it = this.mNetworkRegistrationInfos.iterator();
            while (it.hasNext()) {
                arrayList.add(new NetworkRegistrationInfo(it.next()));
            }
        }
        return arrayList;
    }

    @SystemApi
    public List<NetworkRegistrationInfo> getNetworkRegistrationInfoListForTransportType(int i) {
        ArrayList arrayList = new ArrayList();
        synchronized (this.mNetworkRegistrationInfos) {
            for (NetworkRegistrationInfo networkRegistrationInfo : this.mNetworkRegistrationInfos) {
                if (networkRegistrationInfo.getTransportType() == i) {
                    arrayList.add(new NetworkRegistrationInfo(networkRegistrationInfo));
                }
            }
        }
        return arrayList;
    }

    @SystemApi
    public List<NetworkRegistrationInfo> getNetworkRegistrationInfoListForDomain(int i) {
        ArrayList arrayList = new ArrayList();
        synchronized (this.mNetworkRegistrationInfos) {
            for (NetworkRegistrationInfo networkRegistrationInfo : this.mNetworkRegistrationInfos) {
                if ((networkRegistrationInfo.getDomain() & i) != 0) {
                    arrayList.add(new NetworkRegistrationInfo(networkRegistrationInfo));
                }
            }
        }
        return arrayList;
    }

    @SystemApi
    public NetworkRegistrationInfo getNetworkRegistrationInfo(int i, int i2) {
        synchronized (this.mNetworkRegistrationInfos) {
            for (NetworkRegistrationInfo networkRegistrationInfo : this.mNetworkRegistrationInfos) {
                if (networkRegistrationInfo.getTransportType() == i2 && (networkRegistrationInfo.getDomain() & i) != 0) {
                    return new NetworkRegistrationInfo(networkRegistrationInfo);
                }
            }
            return null;
        }
    }

    public void addNetworkRegistrationInfo(NetworkRegistrationInfo networkRegistrationInfo) {
        if (networkRegistrationInfo == null) {
            return;
        }
        synchronized (this.mNetworkRegistrationInfos) {
            int i = 0;
            while (true) {
                if (i >= this.mNetworkRegistrationInfos.size()) {
                    break;
                }
                NetworkRegistrationInfo networkRegistrationInfo2 = this.mNetworkRegistrationInfos.get(i);
                if (networkRegistrationInfo2.getTransportType() == networkRegistrationInfo.getTransportType() && networkRegistrationInfo2.getDomain() == networkRegistrationInfo.getDomain()) {
                    this.mNetworkRegistrationInfos.remove(i);
                    break;
                }
                i++;
            }
            this.mNetworkRegistrationInfos.add(new NetworkRegistrationInfo(networkRegistrationInfo));
        }
    }

    public static final int getBetterNRFrequencyRange(int i, int i2) {
        List<Integer> list = FREQUENCY_RANGE_ORDER;
        return list.indexOf(Integer.valueOf(i)) > list.indexOf(Integer.valueOf(i2)) ? i : i2;
    }

    public ServiceState createLocationInfoSanitizedCopy(boolean z) {
        ServiceState serviceState = new ServiceState(this);
        synchronized (serviceState.mNetworkRegistrationInfos) {
            List list = (List) serviceState.mNetworkRegistrationInfos.stream().map(new Function() { // from class: android.telephony.-$$Lambda$TTUQe1VxfZ5DMGuNhvszdcyaJP8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((NetworkRegistrationInfo) obj).sanitizeLocationInfo();
                }
            }).collect(Collectors.toList());
            serviceState.mNetworkRegistrationInfos.clear();
            serviceState.mNetworkRegistrationInfos.addAll(list);
        }
        if (!z) {
            return serviceState;
        }
        serviceState.mOperatorAlphaLong = null;
        serviceState.mOperatorAlphaShort = null;
        serviceState.mOperatorNumeric = null;
        return serviceState;
    }

    public void setOperatorAlphaLongRaw(String str) {
        this.mOperatorAlphaLongRaw = str;
    }

    public String getOperatorAlphaLongRaw() {
        return this.mOperatorAlphaLongRaw;
    }

    public void setOperatorAlphaShortRaw(String str) {
        this.mOperatorAlphaShortRaw = str;
    }

    public String getOperatorAlphaShortRaw() {
        return this.mOperatorAlphaShortRaw;
    }

    public void setIwlanPreferred(boolean z) {
        this.mIsIwlanPreferred = z;
    }

    public boolean isIwlanPreferred() {
        return this.mIsIwlanPreferred;
    }

    public boolean isSearching() {
        NetworkRegistrationInfo networkRegistrationInfo = getNetworkRegistrationInfo(2, 1);
        if (networkRegistrationInfo != null && networkRegistrationInfo.getRegistrationState() == 2) {
            return true;
        }
        NetworkRegistrationInfo networkRegistrationInfo2 = getNetworkRegistrationInfo(1, 1);
        return networkRegistrationInfo2 != null && networkRegistrationInfo2.getRegistrationState() == 2;
    }
}
