package com.android.networkstack.apishim;

import android.net.Network;
import com.android.networkstack.apishim.common.NetworkShim;
import com.android.networkstack.apishim.common.ShimUtils;

/* JADX INFO: loaded from: classes.dex */
public class NetworkShimImpl extends com.android.networkstack.apishim.api29.NetworkShimImpl {
    protected NetworkShimImpl(Network network) {
        super(network);
    }

    public static NetworkShim newInstance(Network network) {
        if (!ShimUtils.isReleaseOrDevelopmentApiAbove(29)) {
            return com.android.networkstack.apishim.api29.NetworkShimImpl.newInstance(network);
        }
        return new NetworkShimImpl(network);
    }

    @Override // com.android.networkstack.apishim.api29.NetworkShimImpl, com.android.networkstack.apishim.common.NetworkShim
    public int getNetId() {
        return this.mNetwork.getNetId();
    }
}
