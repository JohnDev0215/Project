package com.android.networkstack.metrics;

import android.net.networkstack.util.HexDump;
import android.net.util.NetworkStackUtils;
import android.net.wifi.WifiInfo;
import com.android.networkstack.protobuf.nano.MessageNano;
import com.android.server.connectivity.nano.CellularData;
import com.android.server.connectivity.nano.DnsEvent;
import com.android.server.connectivity.nano.WifiData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class DataStallDetectionStats {
    public static final int UNSPECIFIED_TCP_FAIL_RATE = -1;
    public static final int UNSPECIFIED_TCP_PACKETS_COUNT = -1;
    public final byte[] mCellularInfo;
    public final byte[] mDns;
    public final int mEvaluationType;
    public final int mNetworkType;
    public final int mTcpFailRate;
    public final int mTcpSentSinceLastRecv;
    public final byte[] mWifiInfo;

    public DataStallDetectionStats(byte[] bArr, byte[] bArr2, int[] iArr, long[] jArr, int i, int i2, int i3, int i4) {
        this.mCellularInfo = emptyCellDataIfNull(bArr);
        this.mWifiInfo = emptyWifiInfoIfNull(bArr2);
        DnsEvent dnsEvent = new DnsEvent();
        dnsEvent.dnsReturnCode = iArr;
        dnsEvent.dnsTime = jArr;
        this.mDns = MessageNano.toByteArray(dnsEvent);
        this.mEvaluationType = i;
        this.mNetworkType = i2;
        this.mTcpFailRate = i3;
        this.mTcpSentSinceLastRecv = i4;
    }

    public static byte[] emptyCellDataIfNull(byte[] bArr) {
        if (bArr != null) {
            return bArr;
        }
        CellularData cellularData = new CellularData();
        cellularData.ratType = 0;
        cellularData.networkMccmnc = "";
        cellularData.simMccmnc = "";
        cellularData.signalStrength = -1;
        return MessageNano.toByteArray(cellularData);
    }

    public static byte[] emptyWifiInfoIfNull(byte[] bArr) {
        if (bArr != null) {
            return bArr;
        }
        WifiData wifiData = new WifiData();
        wifiData.wifiBand = 0;
        wifiData.signalStrength = -1;
        return MessageNano.toByteArray(wifiData);
    }

    public String toString() {
        return "type: " + this.mNetworkType + ", evaluation type: " + this.mEvaluationType + ", wifi info: " + HexDump.toHexString(this.mWifiInfo) + ", cell info: " + HexDump.toHexString(this.mCellularInfo) + ", dns: " + HexDump.toHexString(this.mDns) + ", tcp fail rate: " + this.mTcpFailRate + ", tcp received: " + this.mTcpSentSinceLastRecv;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DataStallDetectionStats)) {
            return false;
        }
        DataStallDetectionStats dataStallDetectionStats = (DataStallDetectionStats) obj;
        return this.mNetworkType == dataStallDetectionStats.mNetworkType && this.mEvaluationType == dataStallDetectionStats.mEvaluationType && Arrays.equals(this.mWifiInfo, dataStallDetectionStats.mWifiInfo) && Arrays.equals(this.mCellularInfo, dataStallDetectionStats.mCellularInfo) && Arrays.equals(this.mDns, dataStallDetectionStats.mDns) && this.mTcpFailRate == dataStallDetectionStats.mTcpFailRate && this.mTcpSentSinceLastRecv == dataStallDetectionStats.mTcpSentSinceLastRecv;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mNetworkType), Integer.valueOf(this.mEvaluationType), this.mWifiInfo, this.mCellularInfo, this.mDns, Integer.valueOf(this.mTcpFailRate), Integer.valueOf(this.mTcpSentSinceLastRecv));
    }

    public static class Builder {
        private byte[] mCellularInfo;
        private int mEvaluationType;
        private int mNetworkType;
        private byte[] mWifiInfo;
        private final List<Integer> mDnsReturnCode = new ArrayList();
        private final List<Long> mDnsTimeStamp = new ArrayList();
        private int mTcpFailRate = -1;
        private int mTcpSentSinceLastRecv = -1;

        public Builder addDnsEvent(int i, long j) {
            this.mDnsReturnCode.add(Integer.valueOf(i));
            this.mDnsTimeStamp.add(Long.valueOf(j));
            return this;
        }

        public Builder setEvaluationType(int i) {
            this.mEvaluationType = i;
            return this;
        }

        public Builder setNetworkType(int i) {
            this.mNetworkType = i;
            return this;
        }

        public Builder setTcpFailRate(int i) {
            this.mTcpFailRate = i;
            return this;
        }

        public Builder setTcpSentSinceLastRecv(int i) {
            this.mTcpSentSinceLastRecv = i;
            return this;
        }

        public Builder setWiFiData(WifiInfo wifiInfo) {
            WifiData wifiData = new WifiData();
            wifiData.wifiBand = getWifiBand(wifiInfo);
            wifiData.signalStrength = wifiInfo != null ? wifiInfo.getRssi() : -1;
            this.mWifiInfo = MessageNano.toByteArray(wifiData);
            return this;
        }

        private static int getWifiBand(WifiInfo wifiInfo) {
            if (wifiInfo == null) {
                return 0;
            }
            int frequency = wifiInfo.getFrequency();
            if (frequency >= 5160 && frequency <= 5865) {
                return 2;
            }
            if (frequency < 2412 || frequency > 2484) {
                return (frequency < 5945 || frequency > 7105) ? 0 : 3;
            }
            return 1;
        }

        public Builder setCellData(int i, boolean z, String str, String str2, int i2) {
            CellularData cellularData = new CellularData();
            cellularData.ratType = i;
            cellularData.isRoaming = z;
            cellularData.networkMccmnc = str;
            cellularData.simMccmnc = str2;
            cellularData.signalStrength = i2;
            this.mCellularInfo = MessageNano.toByteArray(cellularData);
            return this;
        }

        public DataStallDetectionStats build() {
            return new DataStallDetectionStats(this.mCellularInfo, this.mWifiInfo, NetworkStackUtils.convertToIntArray(this.mDnsReturnCode), NetworkStackUtils.convertToLongArray(this.mDnsTimeStamp), this.mEvaluationType, this.mNetworkType, this.mTcpFailRate, this.mTcpSentSinceLastRecv);
        }
    }
}
