package com.android.networkstack.apishim.api29;

import android.net.util.SocketUtils;
import com.android.networkstack.apishim.common.SocketUtilsShim;
import java.net.SocketAddress;

/* JADX INFO: loaded from: classes.dex */
public class SocketUtilsShimImpl implements SocketUtilsShim {
    protected SocketUtilsShimImpl() {
    }

    public static SocketUtilsShim newInstance() {
        return new SocketUtilsShimImpl();
    }

    @Override // com.android.networkstack.apishim.common.SocketUtilsShim
    public SocketAddress makePacketSocketAddress(int i, int i2, byte[] bArr) {
        return SocketUtils.makePacketSocketAddress(i2, bArr);
    }
}
