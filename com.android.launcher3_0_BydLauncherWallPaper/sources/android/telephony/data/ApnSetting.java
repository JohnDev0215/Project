package android.telephony.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.Telephony;
import android.telephony.CarrierConfigManager;
import android.telephony.ServiceState;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class ApnSetting implements Parcelable {
    private static final Map<Integer, String> APN_TYPE_INT_MAP;
    private static final Map<String, Integer> APN_TYPE_STRING_MAP;
    public static final int AUTH_TYPE_CHAP = 2;
    public static final int AUTH_TYPE_NONE = 0;
    public static final int AUTH_TYPE_PAP = 1;
    public static final int AUTH_TYPE_PAP_OR_CHAP = 3;
    public static final Parcelable.Creator<ApnSetting> CREATOR;
    private static final String LOG_TAG = "ApnSetting";
    public static final int MVNO_TYPE_GID = 2;
    public static final int MVNO_TYPE_ICCID = 3;
    public static final int MVNO_TYPE_IMSI = 1;
    private static final Map<Integer, String> MVNO_TYPE_INT_MAP;
    public static final int MVNO_TYPE_SPN = 0;
    private static final Map<String, Integer> MVNO_TYPE_STRING_MAP;
    private static final Map<Integer, String> PROTOCOL_INT_MAP;
    public static final int PROTOCOL_IP = 0;
    public static final int PROTOCOL_IPV4V6 = 2;
    public static final int PROTOCOL_IPV6 = 1;
    public static final int PROTOCOL_NON_IP = 4;
    public static final int PROTOCOL_PPP = 3;
    private static final Map<String, Integer> PROTOCOL_STRING_MAP;
    public static final int PROTOCOL_UNSTRUCTURED = 5;
    public static final int TYPE_ALL = 255;
    public static final String TYPE_ALL_STRING = "*";
    public static final int TYPE_CBS = 128;
    public static final String TYPE_CBS_STRING = "cbs";
    public static final int TYPE_DEFAULT = 17;
    public static final String TYPE_DEFAULT_STRING = "default";
    public static final int TYPE_DUN = 8;
    public static final String TYPE_DUN_STRING = "dun";
    public static final int TYPE_EMERGENCY = 512;
    public static final String TYPE_EMERGENCY_STRING = "emergency";
    public static final int TYPE_FOTA = 32;
    public static final String TYPE_FOTA_STRING = "fota";
    public static final int TYPE_HIPRI = 16;
    public static final String TYPE_HIPRI_STRING = "hipri";
    public static final int TYPE_IA = 256;
    public static final String TYPE_IA_STRING = "ia";
    public static final int TYPE_IMS = 64;
    public static final String TYPE_IMS_STRING = "ims";
    public static final int TYPE_MCX = 1024;
    public static final String TYPE_MCX_STRING = "mcx";
    public static final int TYPE_MMS = 2;
    public static final String TYPE_MMS_STRING = "mms";
    public static final int TYPE_NONE = 0;
    public static final int TYPE_SUPL = 4;
    public static final String TYPE_SUPL_STRING = "supl";
    public static final int TYPE_XCAP = 2048;
    public static final String TYPE_XCAP_STRING = "xcap";
    public static final int UNSET_MTU = 0;
    private static final int UNSPECIFIED_INT = -1;
    private static final String UNSPECIFIED_STRING = "";
    private static final String V2_FORMAT_REGEX = "^\\[ApnSettingV2\\]\\s*";
    private static final String V3_FORMAT_REGEX = "^\\[ApnSettingV3\\]\\s*";
    private static final String V4_FORMAT_REGEX = "^\\[ApnSettingV4\\]\\s*";
    private static final String V5_FORMAT_REGEX = "^\\[ApnSettingV5\\]\\s*";
    private static final String V6_FORMAT_REGEX = "^\\[ApnSettingV6\\]\\s*";
    private static final String V7_FORMAT_REGEX = "^\\[ApnSettingV7\\]\\s*";
    private static final boolean VDBG = false;
    private final String mApnName;
    private final int mApnSetId;
    private final int mApnTypeBitmask;
    private final int mAuthType;
    private final boolean mCarrierEnabled;
    private final int mCarrierId;
    private final String mEntryName;
    private final int mId;
    private final int mMaxConns;
    private final int mMaxConnsTime;
    private final String mMmsProxyAddress;
    private final int mMmsProxyPort;
    private final Uri mMmsc;
    private final int mMtu;
    private final String mMvnoMatchData;
    private final int mMvnoType;
    private final int mNetworkTypeBitmask;
    private final String mOperatorNumeric;
    private final String mPassword;
    private boolean mPermanentFailed;
    private final boolean mPersistent;
    private final int mProfileId;
    private final int mProtocol;
    private final String mProxyAddress;
    private final int mProxyPort;
    private final int mRoamingProtocol;
    private final int mSkip464Xlat;
    private final String mUser;
    private final int mWaitTime;

    @Retention(RetentionPolicy.SOURCE)
    public @interface AuthType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface MvnoType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ProtocolType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Skip464XlatStatus {
    }

    private String nullToEmpty(String str) {
        return str == null ? "" : str;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    static {
        ArrayMap arrayMap = new ArrayMap();
        APN_TYPE_STRING_MAP = arrayMap;
        arrayMap.put("*", 255);
        arrayMap.put("default", 17);
        arrayMap.put("mms", 2);
        arrayMap.put("supl", 4);
        arrayMap.put("dun", 8);
        arrayMap.put("hipri", 16);
        arrayMap.put("fota", 32);
        arrayMap.put("ims", 64);
        arrayMap.put("cbs", 128);
        arrayMap.put("ia", 256);
        arrayMap.put("emergency", 512);
        arrayMap.put("mcx", 1024);
        arrayMap.put("xcap", 2048);
        ArrayMap arrayMap2 = new ArrayMap();
        APN_TYPE_INT_MAP = arrayMap2;
        arrayMap2.put(17, "default");
        arrayMap2.put(2, "mms");
        arrayMap2.put(4, "supl");
        arrayMap2.put(8, "dun");
        arrayMap2.put(16, "hipri");
        arrayMap2.put(32, "fota");
        arrayMap2.put(64, "ims");
        arrayMap2.put(128, "cbs");
        arrayMap2.put(256, "ia");
        arrayMap2.put(512, "emergency");
        arrayMap2.put(1024, "mcx");
        arrayMap2.put(2048, "xcap");
        ArrayMap arrayMap3 = new ArrayMap();
        PROTOCOL_STRING_MAP = arrayMap3;
        arrayMap3.put(CarrierConfigManager.Apn.PROTOCOL_IPV4, 0);
        arrayMap3.put(CarrierConfigManager.Apn.PROTOCOL_IPV6, 1);
        arrayMap3.put(CarrierConfigManager.Apn.PROTOCOL_IPV4V6, 2);
        arrayMap3.put("PPP", 3);
        arrayMap3.put("NON-IP", 4);
        arrayMap3.put("UNSTRUCTURED", 5);
        ArrayMap arrayMap4 = new ArrayMap();
        PROTOCOL_INT_MAP = arrayMap4;
        arrayMap4.put(0, CarrierConfigManager.Apn.PROTOCOL_IPV4);
        arrayMap4.put(1, CarrierConfigManager.Apn.PROTOCOL_IPV6);
        arrayMap4.put(2, CarrierConfigManager.Apn.PROTOCOL_IPV4V6);
        arrayMap4.put(3, "PPP");
        arrayMap4.put(4, "NON-IP");
        arrayMap4.put(5, "UNSTRUCTURED");
        ArrayMap arrayMap5 = new ArrayMap();
        MVNO_TYPE_STRING_MAP = arrayMap5;
        arrayMap5.put(Telephony.CarrierId.All.SPN, 0);
        arrayMap5.put("imsi", 1);
        arrayMap5.put("gid", 2);
        arrayMap5.put("iccid", 3);
        ArrayMap arrayMap6 = new ArrayMap();
        MVNO_TYPE_INT_MAP = arrayMap6;
        arrayMap6.put(0, Telephony.CarrierId.All.SPN);
        arrayMap6.put(1, "imsi");
        arrayMap6.put(2, "gid");
        arrayMap6.put(3, "iccid");
        CREATOR = new Parcelable.Creator<ApnSetting>() { // from class: android.telephony.data.ApnSetting.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ApnSetting createFromParcel(Parcel parcel) {
                return ApnSetting.readFromParcel(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ApnSetting[] newArray(int i) {
                return new ApnSetting[i];
            }
        };
    }

    public int getMtu() {
        return this.mMtu;
    }

    public int getProfileId() {
        return this.mProfileId;
    }

    public boolean isPersistent() {
        return this.mPersistent;
    }

    public int getMaxConns() {
        return this.mMaxConns;
    }

    public int getWaitTime() {
        return this.mWaitTime;
    }

    public int getMaxConnsTime() {
        return this.mMaxConnsTime;
    }

    public String getMvnoMatchData() {
        return this.mMvnoMatchData;
    }

    public int getApnSetId() {
        return this.mApnSetId;
    }

    public boolean getPermanentFailed() {
        return this.mPermanentFailed;
    }

    public void setPermanentFailed(boolean z) {
        this.mPermanentFailed = z;
    }

    public String getEntryName() {
        return this.mEntryName;
    }

    public String getApnName() {
        return this.mApnName;
    }

    @Deprecated
    public InetAddress getProxyAddress() {
        return inetAddressFromString(this.mProxyAddress);
    }

    public String getProxyAddressAsString() {
        return this.mProxyAddress;
    }

    public int getProxyPort() {
        return this.mProxyPort;
    }

    public Uri getMmsc() {
        return this.mMmsc;
    }

    @Deprecated
    public InetAddress getMmsProxyAddress() {
        return inetAddressFromString(this.mMmsProxyAddress);
    }

    public String getMmsProxyAddressAsString() {
        return this.mMmsProxyAddress;
    }

    public int getMmsProxyPort() {
        return this.mMmsProxyPort;
    }

    public String getUser() {
        return this.mUser;
    }

    public String getPassword() {
        return this.mPassword;
    }

    public int getAuthType() {
        return this.mAuthType;
    }

    public int getApnTypeBitmask() {
        return this.mApnTypeBitmask;
    }

    public int getId() {
        return this.mId;
    }

    public String getOperatorNumeric() {
        return this.mOperatorNumeric;
    }

    public int getProtocol() {
        return this.mProtocol;
    }

    public int getRoamingProtocol() {
        return this.mRoamingProtocol;
    }

    public boolean isEnabled() {
        return this.mCarrierEnabled;
    }

    public int getNetworkTypeBitmask() {
        return this.mNetworkTypeBitmask;
    }

    public int getMvnoType() {
        return this.mMvnoType;
    }

    public int getCarrierId() {
        return this.mCarrierId;
    }

    public int getSkip464Xlat() {
        return this.mSkip464Xlat;
    }

    private ApnSetting(Builder builder) {
        this.mPermanentFailed = false;
        this.mEntryName = builder.mEntryName;
        this.mApnName = builder.mApnName;
        this.mProxyAddress = builder.mProxyAddress;
        this.mProxyPort = builder.mProxyPort;
        this.mMmsc = builder.mMmsc;
        this.mMmsProxyAddress = builder.mMmsProxyAddress;
        this.mMmsProxyPort = builder.mMmsProxyPort;
        this.mUser = builder.mUser;
        this.mPassword = builder.mPassword;
        this.mAuthType = builder.mAuthType;
        this.mApnTypeBitmask = builder.mApnTypeBitmask;
        this.mId = builder.mId;
        this.mOperatorNumeric = builder.mOperatorNumeric;
        this.mProtocol = builder.mProtocol;
        this.mRoamingProtocol = builder.mRoamingProtocol;
        this.mMtu = builder.mMtu;
        this.mCarrierEnabled = builder.mCarrierEnabled;
        this.mNetworkTypeBitmask = builder.mNetworkTypeBitmask;
        this.mProfileId = builder.mProfileId;
        this.mPersistent = builder.mModemCognitive;
        this.mMaxConns = builder.mMaxConns;
        this.mWaitTime = builder.mWaitTime;
        this.mMaxConnsTime = builder.mMaxConnsTime;
        this.mMvnoType = builder.mMvnoType;
        this.mMvnoMatchData = builder.mMvnoMatchData;
        this.mApnSetId = builder.mApnSetId;
        this.mCarrierId = builder.mCarrierId;
        this.mSkip464Xlat = builder.mSkip464Xlat;
    }

    public static ApnSetting makeApnSetting(int i, String str, String str2, String str3, String str4, int i2, Uri uri, String str5, int i3, String str6, String str7, int i4, int i5, int i6, int i7, boolean z, int i8, int i9, boolean z2, int i10, int i11, int i12, int i13, int i14, String str8, int i15, int i16, int i17) {
        return new Builder().setId(i).setOperatorNumeric(str).setEntryName(str2).setApnName(str3).setProxyAddress(str4).setProxyPort(i2).setMmsc(uri).setMmsProxyAddress(str5).setMmsProxyPort(i3).setUser(str6).setPassword(str7).setAuthType(i4).setApnTypeBitmask(i5).setProtocol(i6).setRoamingProtocol(i7).setCarrierEnabled(z).setNetworkTypeBitmask(i8).setProfileId(i9).setModemCognitive(z2).setMaxConns(i10).setWaitTime(i11).setMaxConnsTime(i12).setMtu(i13).setMvnoType(i14).setMvnoMatchData(str8).setApnSetId(i15).setCarrierId(i16).setSkip464Xlat(i17).buildWithoutCheck();
    }

    public static ApnSetting makeApnSetting(int i, String str, String str2, String str3, String str4, int i2, Uri uri, String str5, int i3, String str6, String str7, int i4, int i5, int i6, int i7, boolean z, int i8, int i9, boolean z2, int i10, int i11, int i12, int i13, int i14, String str8) {
        return makeApnSetting(i, str, str2, str3, str4, i2, uri, str5, i3, str6, str7, i4, i5, i6, i7, z, i8, i9, z2, i10, i11, i12, i13, i14, str8, 0, -1, -1);
    }

    public static ApnSetting makeApnSetting(Cursor cursor) {
        int apnTypesBitmaskFromString = getApnTypesBitmaskFromString(cursor.getString(cursor.getColumnIndexOrThrow("type")));
        int iConvertBearerBitmaskToNetworkTypeBitmask = cursor.getInt(cursor.getColumnIndexOrThrow(Telephony.Carriers.NETWORK_TYPE_BITMASK));
        if (iConvertBearerBitmaskToNetworkTypeBitmask == 0) {
            iConvertBearerBitmaskToNetworkTypeBitmask = ServiceState.convertBearerBitmaskToNetworkTypeBitmask(cursor.getInt(cursor.getColumnIndexOrThrow(Telephony.Carriers.BEARER_BITMASK)));
        }
        int i = iConvertBearerBitmaskToNetworkTypeBitmask;
        return makeApnSetting(cursor.getInt(cursor.getColumnIndexOrThrow("_id")), cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Carriers.NUMERIC)), cursor.getString(cursor.getColumnIndexOrThrow("name")), cursor.getString(cursor.getColumnIndexOrThrow("apn")), cursor.getString(cursor.getColumnIndexOrThrow("proxy")), portFromString(cursor.getString(cursor.getColumnIndexOrThrow("port"))), UriFromString(cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Carriers.MMSC))), cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Carriers.MMSPROXY)), portFromString(cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Carriers.MMSPORT))), cursor.getString(cursor.getColumnIndexOrThrow("user")), cursor.getString(cursor.getColumnIndexOrThrow("password")), cursor.getInt(cursor.getColumnIndexOrThrow(Telephony.Carriers.AUTH_TYPE)), apnTypesBitmaskFromString, getProtocolIntFromString(cursor.getString(cursor.getColumnIndexOrThrow("protocol"))), getProtocolIntFromString(cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Carriers.ROAMING_PROTOCOL))), cursor.getInt(cursor.getColumnIndexOrThrow(Telephony.Carriers.CARRIER_ENABLED)) == 1, i, cursor.getInt(cursor.getColumnIndexOrThrow(Telephony.Carriers.PROFILE_ID)), cursor.getInt(cursor.getColumnIndexOrThrow(Telephony.Carriers.MODEM_PERSIST)) == 1, cursor.getInt(cursor.getColumnIndexOrThrow(Telephony.Carriers.MAX_CONNECTIONS)), cursor.getInt(cursor.getColumnIndexOrThrow(Telephony.Carriers.WAIT_TIME_RETRY)), cursor.getInt(cursor.getColumnIndexOrThrow(Telephony.Carriers.TIME_LIMIT_FOR_MAX_CONNECTIONS)), cursor.getInt(cursor.getColumnIndexOrThrow("mtu")), getMvnoTypeIntFromString(cursor.getString(cursor.getColumnIndexOrThrow("mvno_type"))), cursor.getString(cursor.getColumnIndexOrThrow("mvno_match_data")), cursor.getInt(cursor.getColumnIndexOrThrow(Telephony.Carriers.APN_SET_ID)), cursor.getInt(cursor.getColumnIndexOrThrow("carrier_id")), cursor.getInt(cursor.getColumnIndexOrThrow(Telephony.Carriers.SKIP_464XLAT)));
    }

    public static ApnSetting makeApnSetting(ApnSetting apnSetting) {
        return makeApnSetting(apnSetting.mId, apnSetting.mOperatorNumeric, apnSetting.mEntryName, apnSetting.mApnName, apnSetting.mProxyAddress, apnSetting.mProxyPort, apnSetting.mMmsc, apnSetting.mMmsProxyAddress, apnSetting.mMmsProxyPort, apnSetting.mUser, apnSetting.mPassword, apnSetting.mAuthType, apnSetting.mApnTypeBitmask, apnSetting.mProtocol, apnSetting.mRoamingProtocol, apnSetting.mCarrierEnabled, apnSetting.mNetworkTypeBitmask, apnSetting.mProfileId, apnSetting.mPersistent, apnSetting.mMaxConns, apnSetting.mWaitTime, apnSetting.mMaxConnsTime, apnSetting.mMtu, apnSetting.mMvnoType, apnSetting.mMvnoMatchData, apnSetting.mApnSetId, apnSetting.mCarrierId, apnSetting.mSkip464Xlat);
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0129 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x016d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.telephony.data.ApnSetting fromString(java.lang.String r42) {
        /*
            Method dump skipped, instruction units count: 522
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.telephony.data.ApnSetting.fromString(java.lang.String):android.telephony.data.ApnSetting");
    }

    public static List<ApnSetting> arrayFromString(String str) {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        for (String str2 : str.split("\\s*;\\s*")) {
            ApnSetting apnSettingFromString = fromString(str2);
            if (apnSettingFromString != null) {
                arrayList.add(apnSettingFromString);
            }
        }
        return arrayList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ApnSettingV7] ").append(this.mEntryName).append(", ").append(this.mId).append(", ").append(this.mOperatorNumeric).append(", ").append(this.mApnName).append(", ").append(this.mProxyAddress).append(", ").append(UriToString(this.mMmsc)).append(", ").append(this.mMmsProxyAddress).append(", ").append(portToString(this.mMmsProxyPort)).append(", ").append(portToString(this.mProxyPort)).append(", ").append(this.mAuthType).append(", ");
        sb.append(TextUtils.join(" | ", getApnTypesStringFromBitmask(this.mApnTypeBitmask).split(SmsManager.REGEX_PREFIX_DELIMITER)));
        StringBuilder sbAppend = sb.append(", ");
        Map<Integer, String> map = PROTOCOL_INT_MAP;
        sbAppend.append(map.get(Integer.valueOf(this.mProtocol)));
        sb.append(", ").append(map.get(Integer.valueOf(this.mRoamingProtocol)));
        sb.append(", ").append(this.mCarrierEnabled);
        sb.append(", ").append(this.mProfileId);
        sb.append(", ").append(this.mPersistent);
        sb.append(", ").append(this.mMaxConns);
        sb.append(", ").append(this.mWaitTime);
        sb.append(", ").append(this.mMaxConnsTime);
        sb.append(", ").append(this.mMtu);
        sb.append(", ").append(MVNO_TYPE_INT_MAP.get(Integer.valueOf(this.mMvnoType)));
        sb.append(", ").append(this.mMvnoMatchData);
        sb.append(", ").append(this.mPermanentFailed);
        sb.append(", ").append(this.mNetworkTypeBitmask);
        sb.append(", ").append(this.mApnSetId);
        sb.append(", ").append(this.mCarrierId);
        sb.append(", ").append(this.mSkip464Xlat);
        return sb.toString();
    }

    public boolean hasMvnoParams() {
        return (TextUtils.isEmpty(getMvnoTypeStringFromInt(this.mMvnoType)) || TextUtils.isEmpty(this.mMvnoMatchData)) ? false : true;
    }

    private boolean hasApnType(int i) {
        return (this.mApnTypeBitmask & i) == i;
    }

    public boolean isEmergencyApn() {
        return hasApnType(512);
    }

    public boolean canHandleType(int i) {
        return this.mCarrierEnabled && hasApnType(i);
    }

    private boolean typeSameAny(ApnSetting apnSetting, ApnSetting apnSetting2) {
        return (apnSetting.mApnTypeBitmask & apnSetting2.mApnTypeBitmask) != 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ApnSetting)) {
            return false;
        }
        ApnSetting apnSetting = (ApnSetting) obj;
        return this.mEntryName.equals(apnSetting.mEntryName) && Objects.equals(Integer.valueOf(this.mId), Integer.valueOf(apnSetting.mId)) && Objects.equals(this.mOperatorNumeric, apnSetting.mOperatorNumeric) && Objects.equals(this.mApnName, apnSetting.mApnName) && Objects.equals(this.mProxyAddress, apnSetting.mProxyAddress) && Objects.equals(this.mMmsc, apnSetting.mMmsc) && Objects.equals(this.mMmsProxyAddress, apnSetting.mMmsProxyAddress) && Objects.equals(Integer.valueOf(this.mMmsProxyPort), Integer.valueOf(apnSetting.mMmsProxyPort)) && Objects.equals(Integer.valueOf(this.mProxyPort), Integer.valueOf(apnSetting.mProxyPort)) && Objects.equals(this.mUser, apnSetting.mUser) && Objects.equals(this.mPassword, apnSetting.mPassword) && Objects.equals(Integer.valueOf(this.mAuthType), Integer.valueOf(apnSetting.mAuthType)) && Objects.equals(Integer.valueOf(this.mApnTypeBitmask), Integer.valueOf(apnSetting.mApnTypeBitmask)) && Objects.equals(Integer.valueOf(this.mProtocol), Integer.valueOf(apnSetting.mProtocol)) && Objects.equals(Integer.valueOf(this.mRoamingProtocol), Integer.valueOf(apnSetting.mRoamingProtocol)) && Objects.equals(Boolean.valueOf(this.mCarrierEnabled), Boolean.valueOf(apnSetting.mCarrierEnabled)) && Objects.equals(Integer.valueOf(this.mProfileId), Integer.valueOf(apnSetting.mProfileId)) && Objects.equals(Boolean.valueOf(this.mPersistent), Boolean.valueOf(apnSetting.mPersistent)) && Objects.equals(Integer.valueOf(this.mMaxConns), Integer.valueOf(apnSetting.mMaxConns)) && Objects.equals(Integer.valueOf(this.mWaitTime), Integer.valueOf(apnSetting.mWaitTime)) && Objects.equals(Integer.valueOf(this.mMaxConnsTime), Integer.valueOf(apnSetting.mMaxConnsTime)) && Objects.equals(Integer.valueOf(this.mMtu), Integer.valueOf(apnSetting.mMtu)) && Objects.equals(Integer.valueOf(this.mMvnoType), Integer.valueOf(apnSetting.mMvnoType)) && Objects.equals(this.mMvnoMatchData, apnSetting.mMvnoMatchData) && Objects.equals(Integer.valueOf(this.mNetworkTypeBitmask), Integer.valueOf(apnSetting.mNetworkTypeBitmask)) && Objects.equals(Integer.valueOf(this.mApnSetId), Integer.valueOf(apnSetting.mApnSetId)) && Objects.equals(Integer.valueOf(this.mCarrierId), Integer.valueOf(apnSetting.mCarrierId)) && Objects.equals(Integer.valueOf(this.mSkip464Xlat), Integer.valueOf(apnSetting.mSkip464Xlat));
    }

    public boolean equals(Object obj, boolean z) {
        if (!(obj instanceof ApnSetting)) {
            return false;
        }
        ApnSetting apnSetting = (ApnSetting) obj;
        if (!this.mEntryName.equals(apnSetting.mEntryName) || !Objects.equals(this.mOperatorNumeric, apnSetting.mOperatorNumeric) || !Objects.equals(this.mApnName, apnSetting.mApnName) || !Objects.equals(this.mProxyAddress, apnSetting.mProxyAddress) || !Objects.equals(this.mMmsc, apnSetting.mMmsc) || !Objects.equals(this.mMmsProxyAddress, apnSetting.mMmsProxyAddress) || !Objects.equals(Integer.valueOf(this.mMmsProxyPort), Integer.valueOf(apnSetting.mMmsProxyPort)) || !Objects.equals(Integer.valueOf(this.mProxyPort), Integer.valueOf(apnSetting.mProxyPort)) || !Objects.equals(this.mUser, apnSetting.mUser) || !Objects.equals(this.mPassword, apnSetting.mPassword) || !Objects.equals(Integer.valueOf(this.mAuthType), Integer.valueOf(apnSetting.mAuthType)) || !Objects.equals(Integer.valueOf(this.mApnTypeBitmask), Integer.valueOf(apnSetting.mApnTypeBitmask))) {
            return false;
        }
        if (z || Objects.equals(Integer.valueOf(this.mProtocol), Integer.valueOf(apnSetting.mProtocol))) {
            return (!z || Objects.equals(Integer.valueOf(this.mRoamingProtocol), Integer.valueOf(apnSetting.mRoamingProtocol))) && Objects.equals(Boolean.valueOf(this.mCarrierEnabled), Boolean.valueOf(apnSetting.mCarrierEnabled)) && Objects.equals(Integer.valueOf(this.mProfileId), Integer.valueOf(apnSetting.mProfileId)) && Objects.equals(Boolean.valueOf(this.mPersistent), Boolean.valueOf(apnSetting.mPersistent)) && Objects.equals(Integer.valueOf(this.mMaxConns), Integer.valueOf(apnSetting.mMaxConns)) && Objects.equals(Integer.valueOf(this.mWaitTime), Integer.valueOf(apnSetting.mWaitTime)) && Objects.equals(Integer.valueOf(this.mMaxConnsTime), Integer.valueOf(apnSetting.mMaxConnsTime)) && Objects.equals(Integer.valueOf(this.mMtu), Integer.valueOf(apnSetting.mMtu)) && Objects.equals(Integer.valueOf(this.mMvnoType), Integer.valueOf(apnSetting.mMvnoType)) && Objects.equals(this.mMvnoMatchData, apnSetting.mMvnoMatchData) && Objects.equals(Integer.valueOf(this.mApnSetId), Integer.valueOf(apnSetting.mApnSetId)) && Objects.equals(Integer.valueOf(this.mCarrierId), Integer.valueOf(apnSetting.mCarrierId)) && Objects.equals(Integer.valueOf(this.mSkip464Xlat), Integer.valueOf(apnSetting.mSkip464Xlat));
        }
        return false;
    }

    public boolean similar(ApnSetting apnSetting) {
        return !canHandleType(8) && !apnSetting.canHandleType(8) && Objects.equals(this.mApnName, apnSetting.mApnName) && !typeSameAny(this, apnSetting) && xorEqualsString(this.mProxyAddress, apnSetting.mProxyAddress) && xorEqualsInt(this.mProxyPort, apnSetting.mProxyPort) && xorEquals(Integer.valueOf(this.mProtocol), Integer.valueOf(apnSetting.mProtocol)) && xorEquals(Integer.valueOf(this.mRoamingProtocol), Integer.valueOf(apnSetting.mRoamingProtocol)) && Objects.equals(Boolean.valueOf(this.mCarrierEnabled), Boolean.valueOf(apnSetting.mCarrierEnabled)) && Objects.equals(Integer.valueOf(this.mProfileId), Integer.valueOf(apnSetting.mProfileId)) && Objects.equals(Integer.valueOf(this.mMvnoType), Integer.valueOf(apnSetting.mMvnoType)) && Objects.equals(this.mMvnoMatchData, apnSetting.mMvnoMatchData) && xorEquals(this.mMmsc, apnSetting.mMmsc) && xorEqualsString(this.mMmsProxyAddress, apnSetting.mMmsProxyAddress) && xorEqualsInt(this.mMmsProxyPort, apnSetting.mMmsProxyPort) && Objects.equals(Integer.valueOf(this.mNetworkTypeBitmask), Integer.valueOf(apnSetting.mNetworkTypeBitmask)) && Objects.equals(Integer.valueOf(this.mApnSetId), Integer.valueOf(apnSetting.mApnSetId)) && Objects.equals(Integer.valueOf(this.mCarrierId), Integer.valueOf(apnSetting.mCarrierId)) && Objects.equals(Integer.valueOf(this.mSkip464Xlat), Integer.valueOf(apnSetting.mSkip464Xlat));
    }

    private boolean xorEquals(Object obj, Object obj2) {
        return obj == null || obj2 == null || obj.equals(obj2);
    }

    private boolean xorEqualsString(String str, String str2) {
        return TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || str.equals(str2);
    }

    private boolean xorEqualsInt(int i, int i2) {
        return i == -1 || i2 == -1 || Objects.equals(Integer.valueOf(i), Integer.valueOf(i2));
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Telephony.Carriers.NUMERIC, nullToEmpty(this.mOperatorNumeric));
        contentValues.put("name", nullToEmpty(this.mEntryName));
        contentValues.put("apn", nullToEmpty(this.mApnName));
        contentValues.put("proxy", nullToEmpty(this.mProxyAddress));
        contentValues.put("port", nullToEmpty(portToString(this.mProxyPort)));
        contentValues.put(Telephony.Carriers.MMSC, nullToEmpty(UriToString(this.mMmsc)));
        contentValues.put(Telephony.Carriers.MMSPORT, nullToEmpty(portToString(this.mMmsProxyPort)));
        contentValues.put(Telephony.Carriers.MMSPROXY, nullToEmpty(this.mMmsProxyAddress));
        contentValues.put("user", nullToEmpty(this.mUser));
        contentValues.put("password", nullToEmpty(this.mPassword));
        contentValues.put(Telephony.Carriers.AUTH_TYPE, Integer.valueOf(this.mAuthType));
        contentValues.put("type", nullToEmpty(getApnTypesStringFromBitmask(this.mApnTypeBitmask)));
        contentValues.put("protocol", getProtocolStringFromInt(this.mProtocol));
        contentValues.put(Telephony.Carriers.ROAMING_PROTOCOL, getProtocolStringFromInt(this.mRoamingProtocol));
        contentValues.put(Telephony.Carriers.CARRIER_ENABLED, Boolean.valueOf(this.mCarrierEnabled));
        contentValues.put("mvno_type", getMvnoTypeStringFromInt(this.mMvnoType));
        contentValues.put(Telephony.Carriers.NETWORK_TYPE_BITMASK, Integer.valueOf(this.mNetworkTypeBitmask));
        contentValues.put("carrier_id", Integer.valueOf(this.mCarrierId));
        contentValues.put(Telephony.Carriers.SKIP_464XLAT, Integer.valueOf(this.mSkip464Xlat));
        return contentValues;
    }

    public List<Integer> getApnTypes() {
        ArrayList arrayList = new ArrayList();
        for (Integer num : APN_TYPE_INT_MAP.keySet()) {
            if ((this.mApnTypeBitmask & num.intValue()) == num.intValue()) {
                arrayList.add(num);
            }
        }
        return arrayList;
    }

    public static String getApnTypesStringFromBitmask(int i) {
        ArrayList arrayList = new ArrayList();
        for (Integer num : APN_TYPE_INT_MAP.keySet()) {
            if ((num.intValue() & i) == num.intValue()) {
                arrayList.add(APN_TYPE_INT_MAP.get(num));
            }
        }
        return TextUtils.join(SmsManager.REGEX_PREFIX_DELIMITER, arrayList);
    }

    public static String getApnTypeString(int i) {
        if (i == 255) {
            return "*";
        }
        String str = APN_TYPE_INT_MAP.get(Integer.valueOf(i));
        return str == null ? "Unknown" : str;
    }

    public static int getApnTypesBitmaskFromString(String str) {
        if (TextUtils.isEmpty(str)) {
            return 255;
        }
        int iIntValue = 0;
        for (String str2 : str.split(SmsManager.REGEX_PREFIX_DELIMITER)) {
            Integer num = APN_TYPE_STRING_MAP.get(str2.toLowerCase());
            if (num != null) {
                iIntValue |= num.intValue();
            }
        }
        return iIntValue;
    }

    public static int getMvnoTypeIntFromString(String str) {
        if (!TextUtils.isEmpty(str)) {
            str = str.toLowerCase();
        }
        Integer num = MVNO_TYPE_STRING_MAP.get(str);
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    public static String getMvnoTypeStringFromInt(int i) {
        String str = MVNO_TYPE_INT_MAP.get(Integer.valueOf(i));
        return str == null ? "" : str;
    }

    public static int getProtocolIntFromString(String str) {
        Integer num = PROTOCOL_STRING_MAP.get(str);
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    public static String getProtocolStringFromInt(int i) {
        String str = PROTOCOL_INT_MAP.get(Integer.valueOf(i));
        return str == null ? "" : str;
    }

    private static Uri UriFromString(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return Uri.parse(str);
    }

    private static String UriToString(Uri uri) {
        if (uri == null) {
            return null;
        }
        return uri.toString();
    }

    public static InetAddress inetAddressFromString(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return InetAddress.getByName(str);
        } catch (UnknownHostException unused) {
            Log.e(LOG_TAG, "Can't parse InetAddress from string: unknown host.");
            return null;
        }
    }

    public static String inetAddressToString(InetAddress inetAddress) {
        if (inetAddress == null) {
            return null;
        }
        String string = inetAddress.toString();
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        String strSubstring = string.substring(0, string.indexOf("/"));
        String strSubstring2 = string.substring(string.indexOf("/") + 1);
        if (TextUtils.isEmpty(strSubstring) && TextUtils.isEmpty(strSubstring2)) {
            return null;
        }
        return TextUtils.isEmpty(strSubstring) ? strSubstring2 : strSubstring;
    }

    private static int portFromString(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException unused) {
                Log.e(LOG_TAG, "Can't parse port from String");
            }
        }
        return -1;
    }

    private static String portToString(int i) {
        if (i == -1) {
            return null;
        }
        return Integer.toString(i);
    }

    public boolean canSupportNetworkType(int i) {
        if (i != 16 || (((long) this.mNetworkTypeBitmask) & 3) == 0) {
            return ServiceState.bitmaskHasTech(this.mNetworkTypeBitmask, i);
        }
        return true;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mId);
        parcel.writeString(this.mOperatorNumeric);
        parcel.writeString(this.mEntryName);
        parcel.writeString(this.mApnName);
        parcel.writeString(this.mProxyAddress);
        parcel.writeInt(this.mProxyPort);
        parcel.writeValue(this.mMmsc);
        parcel.writeString(this.mMmsProxyAddress);
        parcel.writeInt(this.mMmsProxyPort);
        parcel.writeString(this.mUser);
        parcel.writeString(this.mPassword);
        parcel.writeInt(this.mAuthType);
        parcel.writeInt(this.mApnTypeBitmask);
        parcel.writeInt(this.mProtocol);
        parcel.writeInt(this.mRoamingProtocol);
        parcel.writeBoolean(this.mCarrierEnabled);
        parcel.writeInt(this.mMvnoType);
        parcel.writeInt(this.mNetworkTypeBitmask);
        parcel.writeInt(this.mApnSetId);
        parcel.writeInt(this.mCarrierId);
        parcel.writeInt(this.mSkip464Xlat);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ApnSetting readFromParcel(Parcel parcel) {
        return makeApnSetting(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), (Uri) parcel.readValue(Uri.class.getClassLoader()), parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readBoolean(), parcel.readInt(), 0, false, 0, 0, 0, 0, parcel.readInt(), null, parcel.readInt(), parcel.readInt(), parcel.readInt());
    }

    public static class Builder {
        private String mApnName;
        private int mApnSetId;
        private int mApnTypeBitmask;
        private int mAuthType;
        private boolean mCarrierEnabled;
        private String mEntryName;
        private int mId;
        private int mMaxConns;
        private int mMaxConnsTime;
        private String mMmsProxyAddress;
        private Uri mMmsc;
        private boolean mModemCognitive;
        private int mMtu;
        private String mMvnoMatchData;
        private int mNetworkTypeBitmask;
        private String mOperatorNumeric;
        private String mPassword;
        private int mProfileId;
        private String mProxyAddress;
        private String mUser;
        private int mWaitTime;
        private int mProxyPort = -1;
        private int mMmsProxyPort = -1;
        private int mProtocol = -1;
        private int mRoamingProtocol = -1;
        private int mMvnoType = -1;
        private int mCarrierId = -1;
        private int mSkip464Xlat = -1;

        /* JADX INFO: Access modifiers changed from: private */
        public Builder setId(int i) {
            this.mId = i;
            return this;
        }

        public Builder setMtu(int i) {
            this.mMtu = i;
            return this;
        }

        public Builder setProfileId(int i) {
            this.mProfileId = i;
            return this;
        }

        public Builder setModemCognitive(boolean z) {
            this.mModemCognitive = z;
            return this;
        }

        public Builder setMaxConns(int i) {
            this.mMaxConns = i;
            return this;
        }

        public Builder setWaitTime(int i) {
            this.mWaitTime = i;
            return this;
        }

        public Builder setMaxConnsTime(int i) {
            this.mMaxConnsTime = i;
            return this;
        }

        public Builder setMvnoMatchData(String str) {
            this.mMvnoMatchData = str;
            return this;
        }

        public Builder setApnSetId(int i) {
            this.mApnSetId = i;
            return this;
        }

        public Builder setEntryName(String str) {
            this.mEntryName = str;
            return this;
        }

        public Builder setApnName(String str) {
            this.mApnName = str;
            return this;
        }

        @Deprecated
        public Builder setProxyAddress(InetAddress inetAddress) {
            this.mProxyAddress = ApnSetting.inetAddressToString(inetAddress);
            return this;
        }

        public Builder setProxyAddress(String str) {
            this.mProxyAddress = str;
            return this;
        }

        public Builder setProxyPort(int i) {
            this.mProxyPort = i;
            return this;
        }

        public Builder setMmsc(Uri uri) {
            this.mMmsc = uri;
            return this;
        }

        @Deprecated
        public Builder setMmsProxyAddress(InetAddress inetAddress) {
            this.mMmsProxyAddress = ApnSetting.inetAddressToString(inetAddress);
            return this;
        }

        public Builder setMmsProxyAddress(String str) {
            this.mMmsProxyAddress = str;
            return this;
        }

        public Builder setMmsProxyPort(int i) {
            this.mMmsProxyPort = i;
            return this;
        }

        public Builder setUser(String str) {
            this.mUser = str;
            return this;
        }

        public Builder setPassword(String str) {
            this.mPassword = str;
            return this;
        }

        public Builder setAuthType(int i) {
            this.mAuthType = i;
            return this;
        }

        public Builder setApnTypeBitmask(int i) {
            this.mApnTypeBitmask = i;
            return this;
        }

        public Builder setOperatorNumeric(String str) {
            this.mOperatorNumeric = str;
            return this;
        }

        public Builder setProtocol(int i) {
            this.mProtocol = i;
            return this;
        }

        public Builder setRoamingProtocol(int i) {
            this.mRoamingProtocol = i;
            return this;
        }

        public Builder setCarrierEnabled(boolean z) {
            this.mCarrierEnabled = z;
            return this;
        }

        public Builder setNetworkTypeBitmask(int i) {
            this.mNetworkTypeBitmask = i;
            return this;
        }

        public Builder setMvnoType(int i) {
            this.mMvnoType = i;
            return this;
        }

        public Builder setCarrierId(int i) {
            this.mCarrierId = i;
            return this;
        }

        public Builder setSkip464Xlat(int i) {
            this.mSkip464Xlat = i;
            return this;
        }

        public ApnSetting build() {
            if ((this.mApnTypeBitmask & 4095) == 0 || TextUtils.isEmpty(this.mApnName) || TextUtils.isEmpty(this.mEntryName)) {
                return null;
            }
            return new ApnSetting(this);
        }

        public ApnSetting buildWithoutCheck() {
            return new ApnSetting(this);
        }
    }
}
