package com.android.networkstack.netlink;

import android.util.Log;
import com.android.networkstack.metrics.NetworkIpProvisioningReported;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class TcpInfo {
    static final int LOST_OFFSET = getFieldOffset(Field.LOST);
    static final int RETRANSMITS_OFFSET = getFieldOffset(Field.RETRANSMITS);
    static final int SEGS_IN_OFFSET = getFieldOffset(Field.SEGS_IN);
    static final int SEGS_OUT_OFFSET = getFieldOffset(Field.SEGS_OUT);
    final int mLost;
    final int mRetransmits;
    final int mSegsIn;
    final int mSegsOut;

    public enum Field {
        STATE(1),
        CASTATE(1),
        RETRANSMITS(1),
        PROBES(1),
        BACKOFF(1),
        OPTIONS(1),
        WSCALE(1),
        DELIVERY_RATE_APP_LIMITED(1),
        RTO(4),
        ATO(4),
        SND_MSS(4),
        RCV_MSS(4),
        UNACKED(4),
        SACKED(4),
        LOST(4),
        RETRANS(4),
        FACKETS(4),
        LAST_DATA_SENT(4),
        LAST_ACK_SENT(4),
        LAST_DATA_RECV(4),
        LAST_ACK_RECV(4),
        PMTU(4),
        RCV_SSTHRESH(4),
        RTT(4),
        RTTVAR(4),
        SND_SSTHRESH(4),
        SND_CWND(4),
        ADVMSS(4),
        REORDERING(4),
        RCV_RTT(4),
        RCV_SPACE(4),
        TOTAL_RETRANS(4),
        PACING_RATE(8),
        MAX_PACING_RATE(8),
        BYTES_ACKED(8),
        BYTES_RECEIVED(8),
        SEGS_OUT(4),
        SEGS_IN(4),
        NOTSENT_BYTES(4),
        MIN_RTT(4),
        DATA_SEGS_IN(4),
        DATA_SEGS_OUT(4),
        DELIVERY_RATE(8),
        BUSY_TIME(8),
        RWND_LIMITED(8),
        SNDBUF_LIMITED(8);

        public final int size;

        Field(int i) {
            this.size = i;
        }
    }

    private static int getFieldOffset(Field field) {
        int i = 0;
        for (Field field2 : Field.values()) {
            if (field2 == field) {
                return i;
            }
            i += field2.size;
        }
        throw new IllegalArgumentException("Unknown field");
    }

    private TcpInfo(ByteBuffer byteBuffer, int i) {
        if (SEGS_IN_OFFSET + Field.SEGS_IN.size > i) {
            throw new IllegalArgumentException("Length " + i + " is less than required.");
        }
        int iPosition = byteBuffer.position();
        this.mSegsIn = byteBuffer.getInt(SEGS_IN_OFFSET + iPosition);
        this.mSegsOut = byteBuffer.getInt(SEGS_OUT_OFFSET + iPosition);
        this.mLost = byteBuffer.getInt(LOST_OFFSET + iPosition);
        this.mRetransmits = byteBuffer.get(RETRANSMITS_OFFSET + iPosition);
        byteBuffer.position(Math.min(i + iPosition, byteBuffer.limit()));
    }

    TcpInfo(int i, int i2, int i3, int i4) {
        this.mRetransmits = i;
        this.mLost = i2;
        this.mSegsOut = i3;
        this.mSegsIn = i4;
    }

    public static TcpInfo parse(ByteBuffer byteBuffer, int i) {
        try {
            return new TcpInfo(byteBuffer, i);
        } catch (IllegalArgumentException | IndexOutOfBoundsException | BufferOverflowException | BufferUnderflowException e) {
            Log.e("TcpInfo", "parsing error.", e);
            return null;
        }
    }

    static String getTcpStateName(int i) {
        switch (i) {
            case 1:
                return "TCP_ESTABLISHED";
            case 2:
                return "TCP_SYN_SENT";
            case 3:
                return "TCP_SYN_RECV";
            case 4:
                return "TCP_FIN_WAIT1";
            case 5:
                return "TCP_FIN_WAIT2";
            case 6:
                return "TCP_TIME_WAIT";
            case NetworkIpProvisioningReported.RANDOM_NUMBER_FIELD_NUMBER /* 7 */:
                return "TCP_CLOSE";
            case 8:
                return "TCP_CLOSE_WAIT";
            case 9:
                return "TCP_LAST_ACK";
            case 10:
                return "TCP_LISTEN";
            case 11:
                return "TCP_CLOSING";
            default:
                return "UNKNOWN:" + Integer.toString(i);
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TcpInfo)) {
            return false;
        }
        TcpInfo tcpInfo = (TcpInfo) obj;
        return this.mSegsIn == tcpInfo.mSegsIn && this.mSegsOut == tcpInfo.mSegsOut && this.mRetransmits == tcpInfo.mRetransmits && this.mLost == tcpInfo.mLost;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mLost), Integer.valueOf(this.mRetransmits), Integer.valueOf(this.mSegsIn), Integer.valueOf(this.mSegsOut));
    }

    public String toString() {
        return "TcpInfo{lost=" + this.mLost + ", retransmit=" + this.mRetransmits + ", received=" + this.mSegsIn + ", sent=" + this.mSegsOut + "}";
    }
}
