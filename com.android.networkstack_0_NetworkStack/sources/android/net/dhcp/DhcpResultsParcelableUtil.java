package android.net.dhcp;

import android.net.DhcpResultsParcelable;
import android.net.networkstack.DhcpResults;
import android.net.shared.IpConfigurationParcelableUtil;

/* JADX INFO: loaded from: classes.dex */
public final class DhcpResultsParcelableUtil {
    public static DhcpResultsParcelable toStableParcelable(DhcpResults dhcpResults) {
        if (dhcpResults == null) {
            return null;
        }
        DhcpResultsParcelable dhcpResultsParcelable = new DhcpResultsParcelable();
        dhcpResultsParcelable.baseConfiguration = dhcpResults.toStaticIpConfiguration();
        dhcpResultsParcelable.leaseDuration = dhcpResults.leaseDuration;
        dhcpResultsParcelable.mtu = dhcpResults.mtu;
        dhcpResultsParcelable.serverAddress = IpConfigurationParcelableUtil.parcelAddress(dhcpResults.serverAddress);
        dhcpResultsParcelable.vendorInfo = dhcpResults.vendorInfo;
        dhcpResultsParcelable.serverHostName = dhcpResults.serverHostName;
        dhcpResultsParcelable.captivePortalApiUrl = dhcpResults.captivePortalApiUrl;
        return dhcpResultsParcelable;
    }
}
