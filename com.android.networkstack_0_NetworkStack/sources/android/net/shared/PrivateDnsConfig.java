package android.net.shared;

import android.net.PrivateDnsConfigParcel;
import android.text.TextUtils;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.function.Function;

/* JADX INFO: loaded from: classes.dex */
public class PrivateDnsConfig {
    public final String hostname;
    public final InetAddress[] ips;
    public final boolean useTls;

    public PrivateDnsConfig() {
        this(false);
    }

    public PrivateDnsConfig(boolean z) {
        this.useTls = z;
        this.hostname = "";
        this.ips = new InetAddress[0];
    }

    public PrivateDnsConfig(String str, InetAddress[] inetAddressArr) {
        boolean z = !TextUtils.isEmpty(str);
        this.useTls = z;
        this.hostname = z ? str : "";
        this.ips = inetAddressArr == null ? new InetAddress[0] : inetAddressArr;
    }

    public boolean inStrictMode() {
        return this.useTls && !TextUtils.isEmpty(this.hostname);
    }

    public String toString() {
        return PrivateDnsConfig.class.getSimpleName() + "{" + this.useTls + ":" + this.hostname + "/" + Arrays.toString(this.ips) + "}";
    }

    public PrivateDnsConfigParcel toParcel() {
        PrivateDnsConfigParcel privateDnsConfigParcel = new PrivateDnsConfigParcel();
        privateDnsConfigParcel.hostname = this.hostname;
        privateDnsConfigParcel.ips = (String[]) ParcelableUtil.toParcelableArray(Arrays.asList(this.ips), new Function() { // from class: android.net.shared.-$$Lambda$OsobWheG5dMvEj_cOJtueqUBqBI
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return IpConfigurationParcelableUtil.parcelAddress((InetAddress) obj);
            }
        }, String.class);
        return privateDnsConfigParcel;
    }

    public static PrivateDnsConfig fromParcel(PrivateDnsConfigParcel privateDnsConfigParcel) {
        String[] strArr = privateDnsConfigParcel.ips;
        return new PrivateDnsConfig(privateDnsConfigParcel.hostname, (InetAddress[]) ParcelableUtil.fromParcelableArray(strArr, $$Lambda$SYWvjOUPlAZ_O2Z6yfFU9np1858.INSTANCE).toArray(new InetAddress[strArr.length]));
    }
}
