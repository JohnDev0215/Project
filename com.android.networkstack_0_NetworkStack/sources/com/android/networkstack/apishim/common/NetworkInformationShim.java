package com.android.networkstack.apishim.common;

import android.net.IpPrefix;
import android.net.LinkProperties;
import android.net.NetworkCapabilities;
import android.net.Uri;
import java.net.Inet4Address;

/* JADX INFO: loaded from: classes.dex */
public interface NetworkInformationShim {
    Uri getCaptivePortalApiUrl(LinkProperties linkProperties);

    CaptivePortalDataShim getCaptivePortalData(LinkProperties linkProperties);

    IpPrefix getNat64Prefix(LinkProperties linkProperties);

    String getSsid(NetworkCapabilities networkCapabilities);

    LinkProperties makeSensitiveFieldsParcelingCopy(LinkProperties linkProperties);

    void setCaptivePortalApiUrl(LinkProperties linkProperties, Uri uri);

    void setDhcpServerAddress(LinkProperties linkProperties, Inet4Address inet4Address);

    void setNat64Prefix(LinkProperties linkProperties, IpPrefix ipPrefix);
}
