package com.engine.tbox.item;

import com.engine.tbox.core.data.CountSyncMessage;
import com.engine.tbox.core.data.IMsgIface;
import com.engine.tbox.core.utils.LogUtils;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class TboxMessage extends CountSyncMessage implements IMsgIface {
    private static final int BIT8 = 8;
    private static final int DEFAULT_BUFF_MAX_LENGTH = 2048;
    private static final int MAX_2_BIT = 255;
    private static final int MAX_RETRY_COUNT = 3;
    private static final int WIPE_DATA_LENGTH = 9;
    private ByteBuffer mBuffer;
    private byte mCks;
    private short mCmd;
    private byte[] mData = new byte[0];
    private int mLen;
    private static byte DLE = 13;
    private static byte SOM = 5;
    private static byte EOF = 10;

    TboxMessage() {
    }

    public void addMessageHeader() {
        this.mBuffer.put(DLE);
        this.mBuffer.put(SOM);
    }

    public void addMessageTail() {
        this.mBuffer.put(DLE);
        this.mBuffer.put(EOF);
    }

    public short getCmd() {
        return this.mCmd;
    }

    public void setCmd(short cmd) {
        this.mCmd = cmd;
    }

    public void addCmd(short cmd) {
        this.mCmd = cmd;
    }

    public int getLen() {
        return this.mLen;
    }

    public void addData(byte[] data) {
        if (data != null) {
            this.mData = data;
        }
    }

    public void putData(byte[] data) {
        this.mData = data;
    }

    public void putEmptyData() {
        this.mData = new byte[0];
    }

    public byte[] getData() {
        return this.mData;
    }

    public byte generateCheckSum() {
        short s = this.mCmd;
        int chk = (s >> 8) ^ (s & 255);
        int i = this.mLen;
        int chk2 = (chk ^ (i >> 8)) ^ (i & 255);
        for (int i2 = 0; i2 < this.mLen; i2++) {
            chk2 ^= this.mData[i2];
        }
        byte b = (byte) chk2;
        this.mCks = b;
        return b;
    }

    public static byte generateCheckSum(short cmd, int len, byte[] data) {
        int chk = (cmd >> 8) ^ (cmd & 255);
        LogUtils.v(LogUtils.TAG, "(cmd >> 8):" + (cmd >> 8) + " (cmd & 0xff):" + (cmd & 255), new Object[0]);
        int chk2 = (chk ^ (len >> 8)) ^ (len & 255);
        LogUtils.v(LogUtils.TAG, "(len >> 8):" + (len >> 8) + " (len & 0xff):" + (len & 255), new Object[0]);
        for (int i = 0; i < len; i++) {
            chk2 ^= data[i];
            LogUtils.v(LogUtils.TAG, "data[" + i + "]:" + ((int) data[i]), new Object[0]);
        }
        return (byte) chk2;
    }

    public byte getCheckSum() {
        return this.mCks;
    }

    @Override // com.engine.tbox.core.data.IMsgIface
    public ByteBuffer toByteBuffer() {
        byte[] bArr = this.mData;
        if (bArr != null) {
            this.mBuffer = ByteBuffer.allocate(bArr.length + 9);
        } else {
            this.mBuffer = ByteBuffer.allocate(9);
        }
        addMessageHeader();
        this.mBuffer.putShort(this.mCmd);
        byte[] bArr2 = this.mData;
        if (bArr2 != null) {
            int length = bArr2.length;
            this.mLen = length;
            this.mBuffer.putShort((short) length);
            this.mBuffer.put(this.mData);
        } else {
            this.mLen = 0;
            this.mBuffer.putShort((short) 0);
        }
        byte bGenerateCheckSum = generateCheckSum();
        this.mCks = bGenerateCheckSum;
        this.mBuffer.put(bGenerateCheckSum);
        addMessageTail();
        this.mBuffer.flip();
        return this.mBuffer;
    }

    @Override // com.engine.tbox.core.data.CountSyncMessage
    public int identify() {
        return this.mCmd;
    }

    @Override // com.engine.tbox.core.data.CountSyncMessage
    public int maxRetry() {
        return 3;
    }

    public boolean isEmptyData() {
        byte[] bArr = this.mData;
        return (bArr == null || bArr.length == 0) ? false : true;
    }

    public boolean isEnoughData(int len) {
        byte[] bArr = this.mData;
        return bArr != null && bArr.length >= len;
    }

    public static class Builder {
        private byte[] mDataArray = new byte[0];
        private short mCmd = 0;

        private Builder beforePutData(TboxMessage msg) {
            msg.addMessageHeader();
            return this;
        }

        public Builder addCmd(short cmd) {
            this.mCmd = cmd;
            return this;
        }

        public Builder putData(byte[] data) {
            this.mDataArray = data;
            return this;
        }

        private Builder afterPutData(TboxMessage msg) {
            msg.addMessageTail();
            return this;
        }

        public TboxMessage build() {
            TboxMessage msg = new TboxMessage();
            msg.addCmd(this.mCmd);
            msg.addData(this.mDataArray);
            return msg;
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(" this:" + hashCode());
        builder.append(" TboxMessage{mBuffer=");
        builder.append(this.mBuffer);
        builder.append(", mCmd=0x" + Integer.toHexString(this.mCmd));
        builder.append(", mLen=" + this.mLen + " hexLen:" + Integer.toHexString(this.mLen));
        StringBuilder sb = new StringBuilder();
        sb.append(", mCks=0x");
        sb.append(Integer.toHexString(this.mCks));
        builder.append(sb.toString());
        builder.append(", mData=" + Arrays.toString(this.mData) + "}");
        return builder.toString();
    }
}
