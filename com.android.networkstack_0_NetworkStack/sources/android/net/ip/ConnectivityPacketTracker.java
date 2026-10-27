package android.net.ip;

import android.net.networkstack.util.HexDump;
import android.net.networkstack.util.LocalLog;
import android.net.networkstack.util.TokenBucket;
import android.net.util.ConnectivityPacketSummary;
import android.net.util.FdEventsReader;
import android.net.util.InterfaceParams;
import android.net.util.NetworkStackUtils;
import android.net.util.PacketReader;
import android.net.util.SocketUtils;
import android.os.Handler;
import android.os.SystemClock;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.text.TextUtils;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class ConnectivityPacketTracker {
    private static final String TAG = "ConnectivityPacketTracker";
    private String mDisplayName;
    private final LocalLog mLog;
    private final PacketReader mPacketListener;
    private boolean mRunning;
    private final String mTag;
    private final TokenBucket mTokenBucket = new TokenBucket(50, 100);
    private long mLastRateLimitLogTimeMs = 0;

    public ConnectivityPacketTracker(Handler handler, InterfaceParams interfaceParams, LocalLog localLog) {
        if (interfaceParams == null) {
            throw new IllegalArgumentException("null InterfaceParams");
        }
        this.mTag = TAG + "." + interfaceParams.name;
        this.mLog = localLog;
        this.mPacketListener = new PacketListener(handler, interfaceParams);
    }

    public void start(String str) {
        this.mRunning = true;
        this.mDisplayName = str;
        this.mPacketListener.start();
    }

    public void stop() {
        this.mPacketListener.stop();
        this.mRunning = false;
        this.mDisplayName = null;
    }

    private final class PacketListener extends PacketReader {
        private final InterfaceParams mInterface;

        PacketListener(Handler handler, InterfaceParams interfaceParams) {
            super(handler, interfaceParams.defaultMtu);
            this.mInterface = interfaceParams;
        }

        @Override // android.net.util.FdEventsReader
        protected FileDescriptor createFd() {
            FileDescriptor fileDescriptorSocket;
            try {
                fileDescriptorSocket = Os.socket(OsConstants.AF_PACKET, OsConstants.SOCK_RAW | OsConstants.SOCK_NONBLOCK, 0);
                try {
                    NetworkStackUtils.attachControlPacketFilter(fileDescriptorSocket, OsConstants.ARPHRD_ETHER);
                    Os.bind(fileDescriptorSocket, SocketUtils.makePacketSocketAddress(OsConstants.ETH_P_ALL, this.mInterface.index));
                    return fileDescriptorSocket;
                } catch (ErrnoException | IOException e) {
                    e = e;
                    logError("Failed to create packet tracking socket: ", e);
                    FdEventsReader.closeFd(fileDescriptorSocket);
                    return null;
                }
            } catch (ErrnoException | IOException e2) {
                e = e2;
                fileDescriptorSocket = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.net.util.FdEventsReader
        public void handlePacket(byte[] bArr, int i) {
            if (!ConnectivityPacketTracker.this.mTokenBucket.get()) {
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                if (jElapsedRealtime >= ConnectivityPacketTracker.this.mLastRateLimitLogTimeMs + 1000) {
                    addLogEntry("Warning: too many packets, rate-limiting to one every 50ms");
                    ConnectivityPacketTracker.this.mLastRateLimitLogTimeMs = jElapsedRealtime;
                    return;
                }
                return;
            }
            try {
                String strSummarize = ConnectivityPacketSummary.summarize(this.mInterface.macAddr, bArr, i);
                if (strSummarize == null) {
                    return;
                }
                addLogEntry(strSummarize + "\n[" + HexDump.toHexString(bArr, 0, i) + "]");
            } catch (Exception unused) {
            }
        }

        @Override // android.net.util.FdEventsReader
        protected void onStart() {
            ConnectivityPacketTracker.this.mLog.log(TextUtils.isEmpty(ConnectivityPacketTracker.this.mDisplayName) ? "--- START ---" : String.format("--- START (%s) ---", ConnectivityPacketTracker.this.mDisplayName));
        }

        @Override // android.net.util.FdEventsReader
        protected void onStop() {
            String str = TextUtils.isEmpty(ConnectivityPacketTracker.this.mDisplayName) ? "--- STOP ---" : String.format("--- STOP (%s) ---", ConnectivityPacketTracker.this.mDisplayName);
            if (!ConnectivityPacketTracker.this.mRunning) {
                str = str + " (packet listener stopped unexpectedly)";
            }
            ConnectivityPacketTracker.this.mLog.log(str);
        }

        @Override // android.net.util.FdEventsReader
        protected void logError(String str, Exception exc) {
            Log.e(ConnectivityPacketTracker.this.mTag, str, exc);
            addLogEntry(str + exc);
        }

        private void addLogEntry(String str) {
            ConnectivityPacketTracker.this.mLog.log(str);
        }
    }
}
