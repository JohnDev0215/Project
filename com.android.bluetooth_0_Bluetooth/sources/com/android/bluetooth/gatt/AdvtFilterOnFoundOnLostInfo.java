package com.android.bluetooth.gatt;

/* JADX INFO: loaded from: classes.dex */
public class AdvtFilterOnFoundOnLostInfo {
    private int mAddrType;
    private String mAddress;
    private int mAdvInfoPresent;
    private byte[] mAdvPkt;
    private int mAdvPktLen;
    private int mAdvState;
    private int mClientIf;
    private int mFiltIndex;
    private int mRssiValue;
    private byte[] mScanRsp;
    private int mScanRspLen;
    private int mTimeStamp;
    private int mTxPower;

    public AdvtFilterOnFoundOnLostInfo(int clientIf, int advPktLen, byte[] advPkt, int scanRspLen, byte[] scanRsp, int filtIndex, int advState, int advInfoPresent, String address, int addrType, int txPower, int rssiValue, int timeStamp) {
        this.mClientIf = clientIf;
        this.mAdvPktLen = advPktLen;
        this.mAdvPkt = advPkt;
        this.mScanRspLen = scanRspLen;
        this.mScanRsp = scanRsp;
        this.mFiltIndex = filtIndex;
        this.mAdvState = advState;
        this.mAdvInfoPresent = advInfoPresent;
        this.mAddress = address;
        this.mAddrType = addrType;
        this.mTxPower = txPower;
        this.mRssiValue = rssiValue;
        this.mTimeStamp = timeStamp;
    }

    public int getClientIf() {
        return this.mClientIf;
    }

    public int getFiltIndex() {
        return this.mFiltIndex;
    }

    public int getAdvState() {
        return this.mAdvState;
    }

    public int getTxPower() {
        return this.mTxPower;
    }

    public int getTimeStamp() {
        return this.mTimeStamp;
    }

    public int getRSSIValue() {
        return this.mRssiValue;
    }

    public int getAdvInfoPresent() {
        return this.mAdvInfoPresent;
    }

    public String getAddress() {
        return this.mAddress;
    }

    public int getAddressType() {
        return this.mAddrType;
    }

    public byte[] getAdvPacketData() {
        return this.mAdvPkt;
    }

    public int getAdvPacketLen() {
        return this.mAdvPktLen;
    }

    public byte[] getScanRspData() {
        return this.mScanRsp;
    }

    public int getScanRspLen() {
        return this.mScanRspLen;
    }

    public byte[] getResult() {
        int length = this.mAdvPkt.length;
        byte[] bArr = this.mScanRsp;
        int resultLength = length + (bArr != null ? bArr.length : 0);
        byte[] result = new byte[resultLength];
        byte[] bArr2 = this.mAdvPkt;
        System.arraycopy(bArr2, 0, result, 0, bArr2.length);
        byte[] bArr3 = this.mScanRsp;
        if (bArr3 != null) {
            System.arraycopy(bArr3, 0, result, this.mAdvPkt.length, bArr3.length);
        }
        return result;
    }
}
