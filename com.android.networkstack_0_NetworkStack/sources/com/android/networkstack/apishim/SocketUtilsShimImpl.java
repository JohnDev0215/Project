package com.android.networkstack.apishim;

import android.net.util.SocketUtils;
import com.android.networkstack.apishim.common.ShimUtils;
import com.android.networkstack.apishim.common.SocketUtilsShim;
import java.net.SocketAddress;

/* JADX INFO: loaded from: classes.dex */
public class SocketUtilsShimImpl extends com.android.networkstack.apishim.api29.SocketUtilsShimImpl {
    protected SocketUtilsShimImpl() {
    }

    public static SocketUtilsShim newInstance() {
        if (!ShimUtils.isReleaseOrDevelopmentApiAbove(29)) {
            return com.android.networkstack.apishim.api29.SocketUtilsShimImpl.newInstance();
        }
        return new SocketUtilsShimImpl();
    }

    @Override // com.android.networkstack.apishim.api29.SocketUtilsShimImpl, com.android.networkstack.apishim.common.SocketUtilsShim
    public SocketAddress makePacketSocketAddress(int i, int i2, byte[] bArr) {
        return SocketUtils.makePacketSocketAddress(i, i2, bArr);
    }
}
