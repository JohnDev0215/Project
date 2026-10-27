package android.net;

import android.net.wifi.WifiInfo;
import android.os.Parcel;
import android.os.Parcelable;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.BackupUtils;
import android.util.Log;
import com.android.internal.util.ArrayUtils;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class NetworkTemplate implements Parcelable {
    private static final int BACKUP_VERSION = 1;
    public static final Parcelable.Creator<NetworkTemplate> CREATOR = new Parcelable.Creator<NetworkTemplate>() { // from class: android.net.NetworkTemplate.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NetworkTemplate createFromParcel(Parcel parcel) {
            return new NetworkTemplate(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NetworkTemplate[] newArray(int i) {
            return new NetworkTemplate[i];
        }
    };
    public static final int MATCH_BLUETOOTH = 8;
    public static final int MATCH_ETHERNET = 5;
    public static final int MATCH_MOBILE = 1;
    public static final int MATCH_MOBILE_WILDCARD = 6;
    public static final int MATCH_PROXY = 9;
    public static final int MATCH_WIFI = 4;
    public static final int MATCH_WIFI_WILDCARD = 7;
    public static final int NETWORK_TYPE_5G_NSA = -2;
    public static final int NETWORK_TYPE_ALL = -1;
    private static final String TAG = "NetworkTemplate";
    private static boolean sForceAllNetworkTypes = false;
    private final int mDefaultNetwork;
    private final int mMatchRule;
    private final String[] mMatchSubscriberIds;
    private final int mMetered;
    private final String mNetworkId;
    private final int mRoaming;
    private final int mSubType;
    private final String mSubscriberId;

    public static int getCollapsedRatType(int i) {
        int i2 = -2;
        if (i != -2) {
            i2 = 20;
            if (i != 20) {
                switch (i) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                    case 16:
                        return 16;
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                    case 17:
                        return 3;
                    case 13:
                    case 18:
                        return 13;
                    default:
                        return 0;
                }
            }
        }
        return i2;
    }

    private static boolean isKnownMatchRule(int i) {
        if (i != 1) {
            switch (i) {
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static void forceAllNetworkTypes() {
        sForceAllNetworkTypes = true;
    }

    public static void resetForceAllNetworkTypes() {
        sForceAllNetworkTypes = false;
    }

    public static NetworkTemplate buildTemplateMobileAll(String str) {
        return new NetworkTemplate(1, str, null);
    }

    public static NetworkTemplate buildTemplateMobileWithRatType(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return new NetworkTemplate(6, null, null, null, -1, -1, -1, i);
        }
        return new NetworkTemplate(1, str, new String[]{str}, null, -1, -1, -1, i);
    }

    public static NetworkTemplate buildTemplateMobileWildcard() {
        return new NetworkTemplate(6, null, null);
    }

    public static NetworkTemplate buildTemplateWifiWildcard() {
        return new NetworkTemplate(7, null, null);
    }

    @Deprecated
    public static NetworkTemplate buildTemplateWifi() {
        return buildTemplateWifiWildcard();
    }

    public static NetworkTemplate buildTemplateWifi(String str) {
        return new NetworkTemplate(4, null, str);
    }

    public static NetworkTemplate buildTemplateEthernet() {
        return new NetworkTemplate(5, null, null);
    }

    public static NetworkTemplate buildTemplateBluetooth() {
        return new NetworkTemplate(8, null, null);
    }

    public static NetworkTemplate buildTemplateProxy() {
        return new NetworkTemplate(9, null, null);
    }

    public NetworkTemplate(int i, String str, String str2) {
        this(i, str, new String[]{str}, str2);
    }

    public NetworkTemplate(int i, String str, String[] strArr, String str2) {
        this(i, str, strArr, str2, -1, -1, -1, -1);
    }

    public NetworkTemplate(int i, String str, String[] strArr, String str2, int i2, int i3, int i4, int i5) {
        this.mMatchRule = i;
        this.mSubscriberId = str;
        this.mMatchSubscriberIds = strArr;
        this.mNetworkId = str2;
        this.mMetered = i2;
        this.mRoaming = i3;
        this.mDefaultNetwork = i4;
        this.mSubType = i5;
        if (isKnownMatchRule(i)) {
            return;
        }
        Log.e(TAG, "Unknown network template rule " + i + " will not match any identity.");
    }

    private NetworkTemplate(Parcel parcel) {
        this.mMatchRule = parcel.readInt();
        this.mSubscriberId = parcel.readString();
        this.mMatchSubscriberIds = parcel.createStringArray();
        this.mNetworkId = parcel.readString();
        this.mMetered = parcel.readInt();
        this.mRoaming = parcel.readInt();
        this.mDefaultNetwork = parcel.readInt();
        this.mSubType = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mMatchRule);
        parcel.writeString(this.mSubscriberId);
        parcel.writeStringArray(this.mMatchSubscriberIds);
        parcel.writeString(this.mNetworkId);
        parcel.writeInt(this.mMetered);
        parcel.writeInt(this.mRoaming);
        parcel.writeInt(this.mDefaultNetwork);
        parcel.writeInt(this.mSubType);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("NetworkTemplate: ");
        sb.append("matchRule=");
        sb.append(getMatchRuleName(this.mMatchRule));
        if (this.mSubscriberId != null) {
            sb.append(", subscriberId=");
            sb.append(NetworkIdentity.scrubSubscriberId(this.mSubscriberId));
        }
        if (this.mMatchSubscriberIds != null) {
            sb.append(", matchSubscriberIds=");
            sb.append(Arrays.toString(NetworkIdentity.scrubSubscriberId(this.mMatchSubscriberIds)));
        }
        if (this.mNetworkId != null) {
            sb.append(", networkId=");
            sb.append(this.mNetworkId);
        }
        if (this.mMetered != -1) {
            sb.append(", metered=");
            sb.append(NetworkStats.meteredToString(this.mMetered));
        }
        if (this.mRoaming != -1) {
            sb.append(", roaming=");
            sb.append(NetworkStats.roamingToString(this.mRoaming));
        }
        if (this.mDefaultNetwork != -1) {
            sb.append(", defaultNetwork=");
            sb.append(NetworkStats.defaultNetworkToString(this.mDefaultNetwork));
        }
        if (this.mSubType != -1) {
            sb.append(", subType=");
            sb.append(this.mSubType);
        }
        return sb.toString();
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mMatchRule), this.mSubscriberId, this.mNetworkId, Integer.valueOf(this.mMetered), Integer.valueOf(this.mRoaming), Integer.valueOf(this.mDefaultNetwork), Integer.valueOf(this.mSubType));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof NetworkTemplate)) {
            return false;
        }
        NetworkTemplate networkTemplate = (NetworkTemplate) obj;
        return this.mMatchRule == networkTemplate.mMatchRule && Objects.equals(this.mSubscriberId, networkTemplate.mSubscriberId) && Objects.equals(this.mNetworkId, networkTemplate.mNetworkId) && this.mMetered == networkTemplate.mMetered && this.mRoaming == networkTemplate.mRoaming && this.mDefaultNetwork == networkTemplate.mDefaultNetwork && this.mSubType == networkTemplate.mSubType;
    }

    public boolean isMatchRuleMobile() {
        int i = this.mMatchRule;
        return i == 1 || i == 6;
    }

    public boolean isPersistable() {
        int i = this.mMatchRule;
        return (i == 6 || i == 7) ? false : true;
    }

    public int getMatchRule() {
        return this.mMatchRule;
    }

    public String getSubscriberId() {
        return this.mSubscriberId;
    }

    public String getNetworkId() {
        return this.mNetworkId;
    }

    public boolean matches(NetworkIdentity networkIdentity) {
        if (!matchesMetered(networkIdentity) || !matchesRoaming(networkIdentity) || !matchesDefaultNetwork(networkIdentity)) {
            return false;
        }
        int i = this.mMatchRule;
        if (i == 1) {
            return matchesMobile(networkIdentity);
        }
        switch (i) {
        }
        return false;
    }

    private boolean matchesMetered(NetworkIdentity networkIdentity) {
        int i = this.mMetered;
        if (i == -1) {
            return true;
        }
        if (i == 1 && networkIdentity.mMetered) {
            return true;
        }
        return this.mMetered == 0 && !networkIdentity.mMetered;
    }

    private boolean matchesRoaming(NetworkIdentity networkIdentity) {
        int i = this.mRoaming;
        if (i == -1) {
            return true;
        }
        if (i == 1 && networkIdentity.mRoaming) {
            return true;
        }
        return this.mRoaming == 0 && !networkIdentity.mRoaming;
    }

    private boolean matchesDefaultNetwork(NetworkIdentity networkIdentity) {
        int i = this.mDefaultNetwork;
        if (i == -1) {
            return true;
        }
        if (i == 1 && networkIdentity.mDefaultNetwork) {
            return true;
        }
        return this.mDefaultNetwork == 0 && !networkIdentity.mDefaultNetwork;
    }

    private boolean matchesCollapsedRatType(NetworkIdentity networkIdentity) {
        int i = this.mSubType;
        return i == -1 || getCollapsedRatType(i) == getCollapsedRatType(networkIdentity.mSubType);
    }

    public boolean matchesSubscriberId(String str) {
        return ArrayUtils.contains(this.mMatchSubscriberIds, str);
    }

    private boolean matchesMobile(NetworkIdentity networkIdentity) {
        if (networkIdentity.mType == 6) {
            return true;
        }
        return (sForceAllNetworkTypes || (networkIdentity.mType == 0 && networkIdentity.mMetered)) && !ArrayUtils.isEmpty(this.mMatchSubscriberIds) && ArrayUtils.contains(this.mMatchSubscriberIds, networkIdentity.mSubscriberId) && matchesCollapsedRatType(networkIdentity);
    }

    public static final int[] getAllCollapsedRatTypes() {
        int[] allNetworkTypes = TelephonyManager.getAllNetworkTypes();
        HashSet hashSet = new HashSet();
        for (int i : allNetworkTypes) {
            hashSet.add(Integer.valueOf(getCollapsedRatType(i)));
        }
        hashSet.add(Integer.valueOf(getCollapsedRatType(-2)));
        hashSet.add(0);
        return toIntArray(hashSet);
    }

    private static int[] toIntArray(Collection<Integer> collection) {
        int[] iArr = new int[collection.size()];
        Iterator<Integer> it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            iArr[i] = it.next().intValue();
            i++;
        }
        return iArr;
    }

    private boolean matchesWifi(NetworkIdentity networkIdentity) {
        if (networkIdentity.mType != 1) {
            return false;
        }
        return Objects.equals(WifiInfo.sanitizeSsid(this.mNetworkId), WifiInfo.sanitizeSsid(networkIdentity.mNetworkId));
    }

    private boolean matchesEthernet(NetworkIdentity networkIdentity) {
        return networkIdentity.mType == 9;
    }

    private boolean matchesMobileWildcard(NetworkIdentity networkIdentity) {
        if (networkIdentity.mType == 6) {
            return true;
        }
        return (sForceAllNetworkTypes || (networkIdentity.mType == 0 && networkIdentity.mMetered)) && matchesCollapsedRatType(networkIdentity);
    }

    private boolean matchesWifiWildcard(NetworkIdentity networkIdentity) {
        int i = networkIdentity.mType;
        return i == 1 || i == 13;
    }

    private boolean matchesBluetooth(NetworkIdentity networkIdentity) {
        return networkIdentity.mType == 7;
    }

    private boolean matchesProxy(NetworkIdentity networkIdentity) {
        return networkIdentity.mType == 16;
    }

    private static String getMatchRuleName(int i) {
        if (i == 1) {
            return "MOBILE";
        }
        switch (i) {
            case 4:
                return "WIFI";
            case 5:
                return "ETHERNET";
            case 6:
                return "MOBILE_WILDCARD";
            case 7:
                return "WIFI_WILDCARD";
            case 8:
                return "BLUETOOTH";
            case 9:
                return "PROXY";
            default:
                return "UNKNOWN(" + i + ")";
        }
    }

    public static NetworkTemplate normalize(NetworkTemplate networkTemplate, String[] strArr) {
        return normalize(networkTemplate, (List<String[]>) Arrays.asList(strArr));
    }

    public static NetworkTemplate normalize(NetworkTemplate networkTemplate, List<String[]> list) {
        if (!networkTemplate.isMatchRuleMobile()) {
            return networkTemplate;
        }
        for (String[] strArr : list) {
            if (ArrayUtils.contains(strArr, networkTemplate.mSubscriberId)) {
                return new NetworkTemplate(networkTemplate.mMatchRule, strArr[0], strArr, networkTemplate.mNetworkId);
            }
        }
        return networkTemplate;
    }

    public byte[] getBytesForBackup() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeInt(1);
        dataOutputStream.writeInt(this.mMatchRule);
        BackupUtils.writeString(dataOutputStream, this.mSubscriberId);
        BackupUtils.writeString(dataOutputStream, this.mNetworkId);
        return byteArrayOutputStream.toByteArray();
    }

    public static NetworkTemplate getNetworkTemplateFromBackup(DataInputStream dataInputStream) throws BackupUtils.BadVersionException, IOException {
        int i = dataInputStream.readInt();
        if (i < 1 || i > 1) {
            throw new BackupUtils.BadVersionException("Unknown Backup Serialization Version");
        }
        int i2 = dataInputStream.readInt();
        String string = BackupUtils.readString(dataInputStream);
        String string2 = BackupUtils.readString(dataInputStream);
        if (!isKnownMatchRule(i2)) {
            throw new BackupUtils.BadVersionException("Restored network template contains unknown match rule " + i2);
        }
        return new NetworkTemplate(i2, string, string2);
    }
}
