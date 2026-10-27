package com.android.networkstack.apishim;

import android.net.IpPrefix;
import android.net.LinkProperties;
import android.net.NetworkCapabilities;
import android.net.Uri;
import com.android.networkstack.apishim.common.CaptivePortalDataShim;
import com.android.networkstack.apishim.common.NetworkInformationShim;
import com.android.networkstack.apishim.common.ShimUtils;
import java.net.Inet4Address;

/* JADX INFO: loaded from: classes.dex */
public class NetworkInformationShimImpl extends com.android.networkstack.apishim.api29.NetworkInformationShimImpl {
    protected NetworkInformationShimImpl() {
    }

    public static NetworkInformationShim newInstance() {
        if (!useApiAboveQ()) {
            return com.android.networkstack.apishim.api29.NetworkInformationShimImpl.newInstance();
        }
        return new NetworkInformationShimImpl();
    }

    public static boolean useApiAboveQ() {
        return ShimUtils.isReleaseOrDevelopmentApiAbove(29);
    }

    @Override // com.android.networkstack.apishim.api29.NetworkInformationShimImpl, com.android.networkstack.apishim.common.NetworkInformationShim
    public Uri getCaptivePortalApiUrl(LinkProperties linkProperties) {
        if (linkProperties == null) {
            return null;
        }
        return linkProperties.getCaptivePortalApiUrl();
    }

    @Override // com.android.networkstack.apishim.api29.NetworkInformationShimImpl, com.android.networkstack.apishim.common.NetworkInformationShim
    public void setCaptivePortalApiUrl(LinkProperties linkProperties, Uri uri) {
        linkProperties.setCaptivePortalApiUrl(uri);
    }

    @Override // com.android.networkstack.apishim.api29.NetworkInformationShimImpl, com.android.networkstack.apishim.common.NetworkInformationShim
    public CaptivePortalDataShim getCaptivePortalData(LinkProperties linkProperties) {
        if (linkProperties == null || linkProperties.getCaptivePortalData() == null) {
            return null;
        }
        return new CaptivePortalDataShimImpl(linkProperties.getCaptivePortalData());
    }

    @Override // com.android.networkstack.apishim.api29.NetworkInformationShimImpl, com.android.networkstack.apishim.common.NetworkInformationShim
    public IpPrefix getNat64Prefix(LinkProperties linkProperties) {
        return linkProperties.getNat64Prefix();
    }

    @Override // com.android.networkstack.apishim.api29.NetworkInformationShimImpl, com.android.networkstack.apishim.common.NetworkInformationShim
    public void setNat64Prefix(LinkProperties linkProperties, IpPrefix ipPrefix) {
        linkProperties.setNat64Prefix(ipPrefix);
    }

    @Override // com.android.networkstack.apishim.api29.NetworkInformationShimImpl, com.android.networkstack.apishim.common.NetworkInformationShim
    public String getSsid(NetworkCapabilities networkCapabilities) {
        if (networkCapabilities == null) {
            return null;
        }
        return networkCapabilities.getSsid();
    }

    @Override // com.android.networkstack.apishim.api29.NetworkInformationShimImpl, com.android.networkstack.apishim.common.NetworkInformationShim
    public LinkProperties makeSensitiveFieldsParcelingCopy(LinkProperties linkProperties) {
        return new LinkProperties(linkProperties, true);
    }

    @Override // com.android.networkstack.apishim.api29.NetworkInformationShimImpl, com.android.networkstack.apishim.common.NetworkInformationShim
    public void setDhcpServerAddress(LinkProperties linkProperties, Inet4Address inet4Address) {
        linkProperties.setDhcpServerAddress(inet4Address);
    }
}
