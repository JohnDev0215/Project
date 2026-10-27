package com.android.networkstack.apishim.api29;

import android.net.IpPrefix;
import android.net.LinkProperties;
import android.net.NetworkCapabilities;
import android.net.Uri;
import com.android.networkstack.apishim.common.CaptivePortalDataShim;
import com.android.networkstack.apishim.common.NetworkInformationShim;
import java.net.Inet4Address;

/* JADX INFO: loaded from: classes.dex */
public class NetworkInformationShimImpl implements NetworkInformationShim {
    public static boolean useApiAboveQ() {
        return false;
    }

    @Override // com.android.networkstack.apishim.common.NetworkInformationShim
    public Uri getCaptivePortalApiUrl(LinkProperties linkProperties) {
        return null;
    }

    @Override // com.android.networkstack.apishim.common.NetworkInformationShim
    public CaptivePortalDataShim getCaptivePortalData(LinkProperties linkProperties) {
        return null;
    }

    @Override // com.android.networkstack.apishim.common.NetworkInformationShim
    public IpPrefix getNat64Prefix(LinkProperties linkProperties) {
        return null;
    }

    @Override // com.android.networkstack.apishim.common.NetworkInformationShim
    public String getSsid(NetworkCapabilities networkCapabilities) {
        return null;
    }

    @Override // com.android.networkstack.apishim.common.NetworkInformationShim
    public void setCaptivePortalApiUrl(LinkProperties linkProperties, Uri uri) {
    }

    @Override // com.android.networkstack.apishim.common.NetworkInformationShim
    public void setDhcpServerAddress(LinkProperties linkProperties, Inet4Address inet4Address) {
    }

    @Override // com.android.networkstack.apishim.common.NetworkInformationShim
    public void setNat64Prefix(LinkProperties linkProperties, IpPrefix ipPrefix) {
    }

    protected NetworkInformationShimImpl() {
    }

    public static NetworkInformationShim newInstance() {
        return new NetworkInformationShimImpl();
    }

    @Override // com.android.networkstack.apishim.common.NetworkInformationShim
    public LinkProperties makeSensitiveFieldsParcelingCopy(LinkProperties linkProperties) {
        return new LinkProperties(linkProperties);
    }
}
