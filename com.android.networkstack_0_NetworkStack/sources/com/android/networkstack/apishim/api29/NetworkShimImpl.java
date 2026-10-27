package com.android.networkstack.apishim.api29;

import android.net.Network;
import com.android.networkstack.apishim.common.NetworkShim;
import com.android.networkstack.apishim.common.UnsupportedApiLevelException;

/* JADX INFO: loaded from: classes.dex */
public class NetworkShimImpl implements NetworkShim {
    protected final Network mNetwork;

    protected NetworkShimImpl(Network network) {
        this.mNetwork = network;
    }

    public static NetworkShim newInstance(Network network) {
        return new NetworkShimImpl(network);
    }

    @Override // com.android.networkstack.apishim.common.NetworkShim
    public int getNetId() throws UnsupportedApiLevelException {
        throw new UnsupportedApiLevelException("Not supported in API 29.");
    }
}
