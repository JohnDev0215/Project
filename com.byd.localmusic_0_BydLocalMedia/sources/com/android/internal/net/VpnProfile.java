package com.android.internal.net;

import android.net.Ikev2VpnProfile;
import android.net.ProxyInfo;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public final class VpnProfile implements Cloneable, Parcelable {
    public static final Parcelable.Creator<VpnProfile> CREATOR = new Parcelable.Creator<VpnProfile>() { // from class: com.android.internal.net.VpnProfile.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VpnProfile createFromParcel(Parcel parcel) {
            return new VpnProfile(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VpnProfile[] newArray(int i) {
            return new VpnProfile[i];
        }
    };
    private static final String ENCODED_NULL_PROXY_INFO = "\u0000\u0000\u0000\u0000";
    static final String LIST_DELIMITER = ",";
    public static final int PROXY_MANUAL = 1;
    public static final int PROXY_NONE = 0;
    private static final String TAG = "VpnProfile";
    public static final int TYPE_IKEV2_IPSEC_PSK = 7;
    public static final int TYPE_IKEV2_IPSEC_RSA = 8;
    public static final int TYPE_IKEV2_IPSEC_USER_PASS = 6;
    public static final int TYPE_IPSEC_HYBRID_RSA = 5;
    public static final int TYPE_IPSEC_XAUTH_PSK = 3;
    public static final int TYPE_IPSEC_XAUTH_RSA = 4;
    public static final int TYPE_L2TP_IPSEC_PSK = 1;
    public static final int TYPE_L2TP_IPSEC_RSA = 2;
    public static final int TYPE_MAX = 8;
    public static final int TYPE_PPTP = 0;
    static final String VALUE_DELIMITER = "\u0000";
    public boolean areAuthParamsInline;
    public String dnsServers;
    public String ipsecCaCert;
    public String ipsecIdentifier;
    public String ipsecSecret;
    public String ipsecServerCert;
    public String ipsecUserCert;
    public boolean isBypassable;
    public boolean isMetered;
    public final boolean isRestrictedToTestNetworks;
    public final String key;
    public String l2tpSecret;
    private List<String> mAllowedAlgorithms;
    public int maxMtu;
    public boolean mppe;
    public String name;
    public String password;
    public ProxyInfo proxy;
    public String routes;
    public transient boolean saveLogin;
    public String searchDomains;
    public String server;
    public int type;
    public String username;

    public static boolean isLegacyType(int i) {
        return (i == 6 || i == 7 || i == 8) ? false : true;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public VpnProfile(String str) {
        this(str, false);
    }

    public VpnProfile(String str, boolean z) {
        this.name = "";
        this.type = 0;
        this.server = "";
        this.username = "";
        this.password = "";
        this.dnsServers = "";
        this.searchDomains = "";
        this.routes = "";
        this.mppe = true;
        this.l2tpSecret = "";
        this.ipsecIdentifier = "";
        this.ipsecSecret = "";
        this.ipsecUserCert = "";
        this.ipsecCaCert = "";
        this.ipsecServerCert = "";
        this.proxy = null;
        this.mAllowedAlgorithms = new ArrayList();
        this.isBypassable = false;
        this.isMetered = false;
        this.maxMtu = 1360;
        this.areAuthParamsInline = false;
        this.saveLogin = false;
        this.key = str;
        this.isRestrictedToTestNetworks = z;
    }

    public VpnProfile(Parcel parcel) {
        this.name = "";
        this.type = 0;
        this.server = "";
        this.username = "";
        this.password = "";
        this.dnsServers = "";
        this.searchDomains = "";
        this.routes = "";
        this.mppe = true;
        this.l2tpSecret = "";
        this.ipsecIdentifier = "";
        this.ipsecSecret = "";
        this.ipsecUserCert = "";
        this.ipsecCaCert = "";
        this.ipsecServerCert = "";
        this.proxy = null;
        this.mAllowedAlgorithms = new ArrayList();
        this.isBypassable = false;
        this.isMetered = false;
        this.maxMtu = 1360;
        this.areAuthParamsInline = false;
        this.saveLogin = false;
        this.key = parcel.readString();
        this.name = parcel.readString();
        this.type = parcel.readInt();
        this.server = parcel.readString();
        this.username = parcel.readString();
        this.password = parcel.readString();
        this.dnsServers = parcel.readString();
        this.searchDomains = parcel.readString();
        this.routes = parcel.readString();
        this.mppe = parcel.readInt() != 0;
        this.l2tpSecret = parcel.readString();
        this.ipsecIdentifier = parcel.readString();
        this.ipsecSecret = parcel.readString();
        this.ipsecUserCert = parcel.readString();
        this.ipsecCaCert = parcel.readString();
        this.ipsecServerCert = parcel.readString();
        this.saveLogin = parcel.readInt() != 0;
        this.proxy = (ProxyInfo) parcel.readParcelable(null);
        ArrayList arrayList = new ArrayList();
        this.mAllowedAlgorithms = arrayList;
        parcel.readList(arrayList, null);
        this.isBypassable = parcel.readBoolean();
        this.isMetered = parcel.readBoolean();
        this.maxMtu = parcel.readInt();
        this.areAuthParamsInline = parcel.readBoolean();
        this.isRestrictedToTestNetworks = parcel.readBoolean();
    }

    public List<String> getAllowedAlgorithms() {
        return Collections.unmodifiableList(this.mAllowedAlgorithms);
    }

    public void setAllowedAlgorithms(List<String> list) {
        validateAllowedAlgorithms(list);
        this.mAllowedAlgorithms = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.key);
        parcel.writeString(this.name);
        parcel.writeInt(this.type);
        parcel.writeString(this.server);
        parcel.writeString(this.username);
        parcel.writeString(this.password);
        parcel.writeString(this.dnsServers);
        parcel.writeString(this.searchDomains);
        parcel.writeString(this.routes);
        parcel.writeInt(this.mppe ? 1 : 0);
        parcel.writeString(this.l2tpSecret);
        parcel.writeString(this.ipsecIdentifier);
        parcel.writeString(this.ipsecSecret);
        parcel.writeString(this.ipsecUserCert);
        parcel.writeString(this.ipsecCaCert);
        parcel.writeString(this.ipsecServerCert);
        parcel.writeInt(this.saveLogin ? 1 : 0);
        parcel.writeParcelable(this.proxy, i);
        parcel.writeList(this.mAllowedAlgorithms);
        parcel.writeBoolean(this.isBypassable);
        parcel.writeBoolean(this.isMetered);
        parcel.writeInt(this.maxMtu);
        parcel.writeBoolean(this.areAuthParamsInline);
        parcel.writeBoolean(this.isRestrictedToTestNetworks);
    }

    public static VpnProfile decode(String str, byte[] bArr) {
        if (str == null) {
            return null;
        }
        try {
            String[] strArrSplit = new String(bArr, StandardCharsets.UTF_8).split(VALUE_DELIMITER, -1);
            if ((strArrSplit.length < 14 || strArrSplit.length > 19) && strArrSplit.length != 24 && strArrSplit.length != 25) {
                return null;
            }
            VpnProfile vpnProfile = new VpnProfile(str, strArrSplit.length >= 25 ? Boolean.parseBoolean(strArrSplit[24]) : false);
            vpnProfile.name = strArrSplit[0];
            int i = Integer.parseInt(strArrSplit[1]);
            vpnProfile.type = i;
            if (i >= 0 && i <= 8) {
                vpnProfile.server = strArrSplit[2];
                vpnProfile.username = strArrSplit[3];
                vpnProfile.password = strArrSplit[4];
                vpnProfile.dnsServers = strArrSplit[5];
                vpnProfile.searchDomains = strArrSplit[6];
                vpnProfile.routes = strArrSplit[7];
                vpnProfile.mppe = Boolean.parseBoolean(strArrSplit[8]);
                vpnProfile.l2tpSecret = strArrSplit[9];
                vpnProfile.ipsecIdentifier = strArrSplit[10];
                vpnProfile.ipsecSecret = strArrSplit[11];
                vpnProfile.ipsecUserCert = strArrSplit[12];
                vpnProfile.ipsecCaCert = strArrSplit[13];
                vpnProfile.ipsecServerCert = strArrSplit.length > 14 ? strArrSplit[14] : "";
                if (strArrSplit.length > 15) {
                    String str2 = strArrSplit.length > 15 ? strArrSplit[15] : "";
                    String str3 = strArrSplit.length > 16 ? strArrSplit[16] : "";
                    String str4 = strArrSplit.length > 17 ? strArrSplit[17] : "";
                    String str5 = strArrSplit.length > 18 ? strArrSplit[18] : "";
                    if (!str2.isEmpty() || !str3.isEmpty() || !str4.isEmpty()) {
                        vpnProfile.proxy = new ProxyInfo(str2, str3.isEmpty() ? 0 : Integer.parseInt(str3), str4);
                    } else if (!str5.isEmpty()) {
                        vpnProfile.proxy = new ProxyInfo(str5);
                    }
                }
                if (strArrSplit.length >= 24) {
                    vpnProfile.mAllowedAlgorithms = Arrays.asList(strArrSplit[19].split(","));
                    vpnProfile.isBypassable = Boolean.parseBoolean(strArrSplit[20]);
                    vpnProfile.isMetered = Boolean.parseBoolean(strArrSplit[21]);
                    vpnProfile.maxMtu = Integer.parseInt(strArrSplit[22]);
                    vpnProfile.areAuthParamsInline = Boolean.parseBoolean(strArrSplit[23]);
                }
                vpnProfile.saveLogin = (vpnProfile.username.isEmpty() && vpnProfile.password.isEmpty()) ? false : true;
                return vpnProfile;
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public byte[] encode() {
        StringBuilder sb = new StringBuilder(this.name);
        sb.append(VALUE_DELIMITER);
        sb.append(this.type);
        sb.append(VALUE_DELIMITER);
        sb.append(this.server);
        sb.append(VALUE_DELIMITER);
        sb.append(this.saveLogin ? this.username : "");
        sb.append(VALUE_DELIMITER);
        sb.append(this.saveLogin ? this.password : "");
        sb.append(VALUE_DELIMITER);
        sb.append(this.dnsServers);
        sb.append(VALUE_DELIMITER);
        sb.append(this.searchDomains);
        sb.append(VALUE_DELIMITER);
        sb.append(this.routes);
        sb.append(VALUE_DELIMITER);
        sb.append(this.mppe);
        sb.append(VALUE_DELIMITER);
        sb.append(this.l2tpSecret);
        sb.append(VALUE_DELIMITER);
        sb.append(this.ipsecIdentifier);
        sb.append(VALUE_DELIMITER);
        sb.append(this.ipsecSecret);
        sb.append(VALUE_DELIMITER);
        sb.append(this.ipsecUserCert);
        sb.append(VALUE_DELIMITER);
        sb.append(this.ipsecCaCert);
        sb.append(VALUE_DELIMITER);
        sb.append(this.ipsecServerCert);
        if (this.proxy != null) {
            sb.append(VALUE_DELIMITER);
            sb.append(this.proxy.getHost() != null ? this.proxy.getHost() : "");
            sb.append(VALUE_DELIMITER);
            sb.append(this.proxy.getPort());
            sb.append(VALUE_DELIMITER);
            sb.append(this.proxy.getExclusionListAsString() != null ? this.proxy.getExclusionListAsString() : "");
            sb.append(VALUE_DELIMITER);
            sb.append(this.proxy.getPacFileUrl().toString());
        } else {
            sb.append(ENCODED_NULL_PROXY_INFO);
        }
        sb.append(VALUE_DELIMITER);
        sb.append(String.join(",", this.mAllowedAlgorithms));
        sb.append(VALUE_DELIMITER);
        sb.append(this.isBypassable);
        sb.append(VALUE_DELIMITER);
        sb.append(this.isMetered);
        sb.append(VALUE_DELIMITER);
        sb.append(this.maxMtu);
        sb.append(VALUE_DELIMITER);
        sb.append(this.areAuthParamsInline);
        sb.append(VALUE_DELIMITER);
        sb.append(this.isRestrictedToTestNetworks);
        return sb.toString().getBytes(StandardCharsets.UTF_8);
    }

    private boolean isValidLockdownLegacyVpnProfile() {
        return isLegacyType(this.type) && isServerAddressNumeric() && hasDns() && areDnsAddressesNumeric();
    }

    private boolean isValidLockdownPlatformVpnProfile() {
        return Ikev2VpnProfile.isValidVpnProfile(this);
    }

    public boolean isValidLockdownProfile() {
        return isTypeValidForLockdown() && (isValidLockdownLegacyVpnProfile() || isValidLockdownPlatformVpnProfile());
    }

    public boolean isTypeValidForLockdown() {
        return this.type != 0;
    }

    public boolean isServerAddressNumeric() {
        try {
            InetAddress.parseNumericAddress(this.server);
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public boolean hasDns() {
        return !TextUtils.isEmpty(this.dnsServers);
    }

    public boolean areDnsAddressesNumeric() {
        try {
            for (String str : this.dnsServers.split(" +")) {
                InetAddress.parseNumericAddress(str);
            }
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public static void validateAllowedAlgorithms(List<String> list) {
        for (String str : list) {
            if (str.contains(VALUE_DELIMITER) || str.contains(",")) {
                throw new IllegalArgumentException("Algorithm contained illegal ('\u0000' or ',') character");
            }
        }
    }

    public int hashCode() {
        return Objects.hash(this.key, Integer.valueOf(this.type), this.server, this.username, this.password, this.dnsServers, this.searchDomains, this.routes, Boolean.valueOf(this.mppe), this.l2tpSecret, this.ipsecIdentifier, this.ipsecSecret, this.ipsecUserCert, this.ipsecCaCert, this.ipsecServerCert, this.proxy, this.mAllowedAlgorithms, Boolean.valueOf(this.isBypassable), Boolean.valueOf(this.isMetered), Integer.valueOf(this.maxMtu), Boolean.valueOf(this.areAuthParamsInline), Boolean.valueOf(this.isRestrictedToTestNetworks));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof VpnProfile)) {
            return false;
        }
        VpnProfile vpnProfile = (VpnProfile) obj;
        return Objects.equals(this.key, vpnProfile.key) && Objects.equals(this.name, vpnProfile.name) && this.type == vpnProfile.type && Objects.equals(this.server, vpnProfile.server) && Objects.equals(this.username, vpnProfile.username) && Objects.equals(this.password, vpnProfile.password) && Objects.equals(this.dnsServers, vpnProfile.dnsServers) && Objects.equals(this.searchDomains, vpnProfile.searchDomains) && Objects.equals(this.routes, vpnProfile.routes) && this.mppe == vpnProfile.mppe && Objects.equals(this.l2tpSecret, vpnProfile.l2tpSecret) && Objects.equals(this.ipsecIdentifier, vpnProfile.ipsecIdentifier) && Objects.equals(this.ipsecSecret, vpnProfile.ipsecSecret) && Objects.equals(this.ipsecUserCert, vpnProfile.ipsecUserCert) && Objects.equals(this.ipsecCaCert, vpnProfile.ipsecCaCert) && Objects.equals(this.ipsecServerCert, vpnProfile.ipsecServerCert) && Objects.equals(this.proxy, vpnProfile.proxy) && Objects.equals(this.mAllowedAlgorithms, vpnProfile.mAllowedAlgorithms) && this.isBypassable == vpnProfile.isBypassable && this.isMetered == vpnProfile.isMetered && this.maxMtu == vpnProfile.maxMtu && this.areAuthParamsInline == vpnProfile.areAuthParamsInline && this.isRestrictedToTestNetworks == vpnProfile.isRestrictedToTestNetworks;
    }
}
