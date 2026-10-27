package android.net.util;

import android.annotation.SystemApi;
import android.net.NetworkUtils;
import android.system.ErrnoException;
import android.system.NetlinkSocketAddress;
import android.system.Os;
import android.system.OsConstants;
import android.system.PacketSocketAddress;
import java.io.FileDescriptor;
import java.io.IOException;
import java.net.SocketAddress;
import libcore.io.IoBridge;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class SocketUtils {
    public static void bindSocketToInterface(FileDescriptor fileDescriptor, String str) throws ErrnoException {
        Os.setsockoptIfreq(fileDescriptor, OsConstants.SOL_SOCKET, OsConstants.SO_BINDTODEVICE, str);
        NetworkUtils.protectFromVpn(fileDescriptor);
    }

    public static SocketAddress makeNetlinkSocketAddress(int i, int i2) {
        return new NetlinkSocketAddress(i, i2);
    }

    public static SocketAddress makePacketSocketAddress(int i, int i2) {
        return new PacketSocketAddress(i, i2, (byte[]) null);
    }

    @Deprecated
    public static SocketAddress makePacketSocketAddress(int i, byte[] bArr) {
        return new PacketSocketAddress(0, i, bArr);
    }

    public static SocketAddress makePacketSocketAddress(int i, int i2, byte[] bArr) {
        return new PacketSocketAddress(i, i2, bArr);
    }

    public static void closeSocket(FileDescriptor fileDescriptor) throws IOException {
        IoBridge.closeAndSignalBlockedThreads(fileDescriptor);
    }

    private SocketUtils() {
    }
}
