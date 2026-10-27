package android.net;

import android.os.Parcel;
import android.os.Parcelable;
import android.telephony.SmsManager;
import android.text.TextUtils;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ProxyInfo implements Parcelable {
    public static final Parcelable.Creator<ProxyInfo> CREATOR = new Parcelable.Creator<ProxyInfo>() { // from class: android.net.ProxyInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProxyInfo createFromParcel(Parcel parcel) {
            String string;
            int i;
            if (parcel.readByte() != 0) {
                return new ProxyInfo(Uri.CREATOR.createFromParcel(parcel), parcel.readInt());
            }
            if (parcel.readByte() != 0) {
                string = parcel.readString();
                i = parcel.readInt();
            } else {
                string = null;
                i = 0;
            }
            return new ProxyInfo(string, i, parcel.readString(), parcel.readStringArray());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProxyInfo[] newArray(int i) {
            return new ProxyInfo[i];
        }
    };
    public static final String LOCAL_EXCL_LIST = "";
    public static final String LOCAL_HOST = "localhost";
    public static final int LOCAL_PORT = -1;
    private final String mExclusionList;
    private final String mHost;
    private final Uri mPacFileUrl;
    private final String[] mParsedExclusionList;
    private final int mPort;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static ProxyInfo buildDirectProxy(String str, int i) {
        return new ProxyInfo(str, i, null);
    }

    public static ProxyInfo buildDirectProxy(String str, int i, List<String> list) {
        String[] strArr = (String[]) list.toArray(new String[list.size()]);
        return new ProxyInfo(str, i, TextUtils.join(SmsManager.REGEX_PREFIX_DELIMITER, strArr), strArr);
    }

    public static ProxyInfo buildPacProxy(Uri uri) {
        return new ProxyInfo(uri);
    }

    public static ProxyInfo buildPacProxy(Uri uri, int i) {
        return new ProxyInfo(uri, i);
    }

    public ProxyInfo(String str, int i, String str2) {
        this.mHost = str;
        this.mPort = i;
        this.mExclusionList = str2;
        this.mParsedExclusionList = parseExclusionList(str2);
        this.mPacFileUrl = Uri.EMPTY;
    }

    public ProxyInfo(Uri uri) {
        this.mHost = LOCAL_HOST;
        this.mPort = -1;
        this.mExclusionList = "";
        this.mParsedExclusionList = parseExclusionList("");
        Objects.requireNonNull(uri);
        this.mPacFileUrl = uri;
    }

    public ProxyInfo(String str) {
        this.mHost = LOCAL_HOST;
        this.mPort = -1;
        this.mExclusionList = "";
        this.mParsedExclusionList = parseExclusionList("");
        this.mPacFileUrl = Uri.parse(str);
    }

    public ProxyInfo(Uri uri, int i) {
        this.mHost = LOCAL_HOST;
        this.mPort = i;
        this.mExclusionList = "";
        this.mParsedExclusionList = parseExclusionList("");
        Objects.requireNonNull(uri);
        this.mPacFileUrl = uri;
    }

    private static String[] parseExclusionList(String str) {
        return str == null ? new String[0] : str.toLowerCase(Locale.ROOT).split(SmsManager.REGEX_PREFIX_DELIMITER);
    }

    private ProxyInfo(String str, int i, String str2, String[] strArr) {
        this.mHost = str;
        this.mPort = i;
        this.mExclusionList = str2;
        this.mParsedExclusionList = strArr;
        this.mPacFileUrl = Uri.EMPTY;
    }

    public ProxyInfo(ProxyInfo proxyInfo) {
        if (proxyInfo != null) {
            this.mHost = proxyInfo.getHost();
            this.mPort = proxyInfo.getPort();
            this.mPacFileUrl = proxyInfo.mPacFileUrl;
            this.mExclusionList = proxyInfo.getExclusionListAsString();
            this.mParsedExclusionList = proxyInfo.mParsedExclusionList;
            return;
        }
        this.mHost = null;
        this.mPort = 0;
        this.mExclusionList = null;
        this.mParsedExclusionList = null;
        this.mPacFileUrl = Uri.EMPTY;
    }

    public InetSocketAddress getSocketAddress() {
        try {
            return new InetSocketAddress(this.mHost, this.mPort);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public Uri getPacFileUrl() {
        return this.mPacFileUrl;
    }

    public String getHost() {
        return this.mHost;
    }

    public int getPort() {
        return this.mPort;
    }

    public String[] getExclusionList() {
        return this.mParsedExclusionList;
    }

    public String getExclusionListAsString() {
        return this.mExclusionList;
    }

    public boolean isValid() {
        if (!Uri.EMPTY.equals(this.mPacFileUrl)) {
            return true;
        }
        String str = this.mHost;
        if (str == null) {
            str = "";
        }
        int i = this.mPort;
        String string = i == 0 ? "" : Integer.toString(i);
        String str2 = this.mExclusionList;
        return Proxy.validate(str, string, str2 != null ? str2 : "") == 0;
    }

    public java.net.Proxy makeProxy() {
        java.net.Proxy proxy = java.net.Proxy.NO_PROXY;
        if (this.mHost == null) {
            return proxy;
        }
        try {
            return new java.net.Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.mHost, this.mPort));
        } catch (IllegalArgumentException unused) {
            return proxy;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!Uri.EMPTY.equals(this.mPacFileUrl)) {
            sb.append("PAC Script: ");
            sb.append(this.mPacFileUrl);
        }
        if (this.mHost != null) {
            sb.append("[");
            sb.append(this.mHost);
            sb.append("] ");
            sb.append(Integer.toString(this.mPort));
            if (this.mExclusionList != null) {
                sb.append(" xl=").append(this.mExclusionList);
            }
        } else {
            sb.append("[ProxyProperties.mHost == null]");
        }
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ProxyInfo)) {
            return false;
        }
        ProxyInfo proxyInfo = (ProxyInfo) obj;
        if (!Uri.EMPTY.equals(this.mPacFileUrl)) {
            return this.mPacFileUrl.equals(proxyInfo.getPacFileUrl()) && this.mPort == proxyInfo.mPort;
        }
        if (!Uri.EMPTY.equals(proxyInfo.mPacFileUrl)) {
            return false;
        }
        String str = this.mExclusionList;
        if (str != null && !str.equals(proxyInfo.getExclusionListAsString())) {
            return false;
        }
        if (this.mHost != null && proxyInfo.getHost() != null && !this.mHost.equals(proxyInfo.getHost())) {
            return false;
        }
        String str2 = this.mHost;
        if (str2 == null || proxyInfo.mHost != null) {
            return (str2 != null || proxyInfo.mHost == null) && this.mPort == proxyInfo.mPort;
        }
        return false;
    }

    public int hashCode() {
        String str = this.mHost;
        int iHashCode = str == null ? 0 : str.hashCode();
        String str2 = this.mExclusionList;
        return iHashCode + (str2 != null ? str2.hashCode() : 0) + this.mPort;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (!Uri.EMPTY.equals(this.mPacFileUrl)) {
            parcel.writeByte((byte) 1);
            this.mPacFileUrl.writeToParcel(parcel, 0);
            parcel.writeInt(this.mPort);
            return;
        }
        parcel.writeByte((byte) 0);
        if (this.mHost != null) {
            parcel.writeByte((byte) 1);
            parcel.writeString(this.mHost);
            parcel.writeInt(this.mPort);
        } else {
            parcel.writeByte((byte) 0);
        }
        parcel.writeString(this.mExclusionList);
        parcel.writeStringArray(this.mParsedExclusionList);
    }
}
