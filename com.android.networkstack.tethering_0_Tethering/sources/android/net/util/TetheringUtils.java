package android.net.util;

import android.net.TetherStatsParcel;
import android.net.TetheringRequestParcel;
import java.io.FileDescriptor;
import java.net.SocketException;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class TetheringUtils {
    public static native void setupRaSocket(FileDescriptor fileDescriptor, int i) throws SocketException;

    public static int uint16(short s) {
        return s & 65535;
    }

    public static class ForwardedStats {
        public final long rxBytes;
        public final long rxPackets;
        public final long txBytes;
        public final long txPackets;

        public ForwardedStats() {
            this.rxBytes = 0L;
            this.rxPackets = 0L;
            this.txBytes = 0L;
            this.txPackets = 0L;
        }

        public ForwardedStats(long j, long j2, long j3, long j4) {
            this.rxBytes = j;
            this.rxPackets = j2;
            this.txBytes = j3;
            this.txPackets = j4;
        }

        public ForwardedStats(TetherStatsParcel tetherStatsParcel) {
            this.rxBytes = tetherStatsParcel.rxBytes;
            this.rxPackets = tetherStatsParcel.rxPackets;
            this.txBytes = tetherStatsParcel.txBytes;
            this.txPackets = tetherStatsParcel.txPackets;
        }

        public ForwardedStats subtract(ForwardedStats forwardedStats) {
            return new ForwardedStats(Math.max(this.rxBytes - forwardedStats.rxBytes, 0L), Math.max(this.rxPackets - forwardedStats.rxPackets, 0L), Math.max(this.txBytes - forwardedStats.txBytes, 0L), Math.max(this.txPackets - forwardedStats.txPackets, 0L));
        }

        public String toString() {
            return String.format("ForwardedStats(rxb: %d, rxp: %d, txb: %d, txp: %d)", Long.valueOf(this.rxBytes), Long.valueOf(this.rxPackets), Long.valueOf(this.txBytes), Long.valueOf(this.txPackets));
        }
    }

    public static boolean isTetheringRequestEquals(TetheringRequestParcel tetheringRequestParcel, TetheringRequestParcel tetheringRequestParcel2) {
        if (tetheringRequestParcel == tetheringRequestParcel2) {
            return true;
        }
        return tetheringRequestParcel != null && tetheringRequestParcel2 != null && tetheringRequestParcel.tetheringType == tetheringRequestParcel2.tetheringType && Objects.equals(tetheringRequestParcel.localIPv4Address, tetheringRequestParcel2.localIPv4Address) && Objects.equals(tetheringRequestParcel.staticClientAddress, tetheringRequestParcel2.staticClientAddress) && tetheringRequestParcel.exemptFromEntitlementCheck == tetheringRequestParcel2.exemptFromEntitlementCheck && tetheringRequestParcel.showProvisioningUi == tetheringRequestParcel2.showProvisioningUi;
    }
}
