package android.net.ip;

import android.net.netlink.NetlinkConstants;
import android.net.netlink.NetlinkErrorMessage;
import android.net.netlink.NetlinkMessage;
import android.net.netlink.NetlinkSocket;
import android.net.util.PacketReader;
import android.net.util.SharedLog;
import android.net.util.SocketUtils;
import android.os.Handler;
import android.os.SystemClock;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import java.io.FileDescriptor;
import java.io.IOException;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes.dex */
public class NetlinkMonitor extends PacketReader {
    private final int mBindGroups;
    private final int mFamily;
    protected final SharedLog mLog;
    protected final String mTag;

    protected void processNetlinkMessage(NetlinkMessage netlinkMessage, long j) {
    }

    public NetlinkMonitor(Handler handler, SharedLog sharedLog, String str, int i, int i2) {
        super(handler, 8192);
        this.mLog = sharedLog.forSubComponent(str);
        this.mTag = str;
        this.mFamily = i;
        this.mBindGroups = i2;
    }

    @Override // android.net.util.FdEventsReader
    protected FileDescriptor createFd() {
        FileDescriptor fileDescriptorSocket;
        try {
            fileDescriptorSocket = Os.socket(OsConstants.AF_NETLINK, OsConstants.SOCK_DGRAM | OsConstants.SOCK_NONBLOCK, this.mFamily);
        } catch (ErrnoException | SocketException e) {
            e = e;
            fileDescriptorSocket = null;
        }
        try {
            Os.bind(fileDescriptorSocket, SocketUtils.makeNetlinkSocketAddress(0, this.mBindGroups));
            NetlinkSocket.connectToKernel(fileDescriptorSocket);
            return fileDescriptorSocket;
        } catch (ErrnoException | SocketException e2) {
            e = e2;
            logError("Failed to create rtnetlink socket", e);
            closeSocketQuietly(fileDescriptorSocket);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.net.util.FdEventsReader
    public void handlePacket(byte[] bArr, int i) {
        int iPosition;
        NetlinkMessage netlinkMessage;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr, 0, i);
        byteBufferWrap.order(ByteOrder.nativeOrder());
        while (byteBufferWrap.remaining() > 0) {
            try {
                iPosition = byteBufferWrap.position();
                netlinkMessage = NetlinkMessage.parse(byteBufferWrap);
            } catch (Exception e) {
                this.mLog.e("Error handling netlink message", e);
            }
            if (netlinkMessage != null && netlinkMessage.getHeader() != null) {
                if (netlinkMessage instanceof NetlinkErrorMessage) {
                    this.mLog.e("netlink error: " + netlinkMessage);
                } else {
                    processNetlinkMessage(netlinkMessage, jElapsedRealtime);
                }
            }
            byteBufferWrap.position(iPosition);
            this.mLog.e("unparsable netlink msg: " + NetlinkConstants.hexify(byteBufferWrap));
            return;
        }
    }

    private void closeSocketQuietly(FileDescriptor fileDescriptor) {
        try {
            SocketUtils.closeSocket(fileDescriptor);
        } catch (IOException unused) {
        }
    }
}
