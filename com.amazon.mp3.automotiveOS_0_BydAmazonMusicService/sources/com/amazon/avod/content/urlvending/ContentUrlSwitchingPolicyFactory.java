package com.amazon.avod.content.urlvending;

import com.amazon.avod.content.ContentException;
import com.google.common.collect.ImmutableMap;

/* JADX INFO: loaded from: classes2.dex */
public class ContentUrlSwitchingPolicyFactory {
    public ContentUrlSwitchingPolicy newContentUrlSwitchingPolicyForFragment() {
        CdnSwitchConfig cdnSwitchConfig = CdnSwitchConfig.INSTANCE;
        return new SmoothStreamingContentUrlSwitchingPolicy(cdnSwitchConfig.getMaxNumberTriesPerFragment(), new ImmutableMap.Builder().put(ContentException.ContentError.CDN_ERROR, Integer.valueOf(cdnSwitchConfig.getMaxNumberTriesForCdnError())).put(ContentException.ContentError.NETWORK_ERROR, Integer.valueOf(cdnSwitchConfig.getMaxNumberTriesForNetworkError())).build(), cdnSwitchConfig.getWindowToRememberErrors(), cdnSwitchConfig.getMaxNumberOfCDNSwitchesPerWindow(), cdnSwitchConfig.getWindowToRememberCDNSwitches());
    }

    public ContentUrlSwitchingPolicy newContentUrlSwitchingPolicyForManifest() {
        CdnSwitchConfig cdnSwitchConfig = CdnSwitchConfig.INSTANCE;
        return new SmoothStreamingContentUrlSwitchingPolicy(cdnSwitchConfig.getMaxNumberTriesPerManifest(), new ImmutableMap.Builder().build(), cdnSwitchConfig.getWindowToRememberErrors(), Integer.MAX_VALUE, cdnSwitchConfig.getWindowToRememberCDNSwitches());
    }
}
