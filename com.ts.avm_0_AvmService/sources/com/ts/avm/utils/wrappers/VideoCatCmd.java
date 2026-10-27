package com.ts.avm.utils.wrappers;

import com.ts.avm.bean.CmdBean;
import com.ts.avm.utils.LogManager;

/* JADX INFO: loaded from: classes.dex */
public class VideoCatCmd {
    private static final int BYTE_ARRAY_INDEX0 = 0;
    private static final int BYTE_ARRAY_INDEX1 = 1;
    private static final int BYTE_ARRAY_INDEX2 = 2;
    private static final int BYTE_ARRAY_INDEX3 = 3;
    private static final int INTEGER_INDEX16 = 16;
    private static final int INTEGER_INDEX24 = 24;
    private static final int INTEGER_INDEX8 = 8;
    private static final int MAX_BYTE = 255;
    private static final String TAG = VideoCatCmd.class.getSimpleName();
    private final byte[] data = new byte[64];
    public int flags;
    public int getOrSet;
    public int header;
    public int id;
    public int payLoadSize;
    public int priority;
    public int srcId;
    public int targetId;
    public int transferId;
    public int type;
    public int[] value;

    public byte[] getData() {
        return this.data;
    }

    public void setHeader(int header) {
        this.header = header;
    }

    public void setPayLoadSize(int payLoadSize) {
        this.payLoadSize = payLoadSize;
    }

    public void setSrcId(int srcId) {
        this.srcId = srcId;
        byte[] idDataArray = intToByteArray(srcId);
        for (int i = 0; i < 4; i++) {
            this.data[8 + i] = idDataArray[i];
        }
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
        byte[] idDataArray = intToByteArray(targetId);
        for (int i = 0; i < 4; i++) {
            this.data[12 + i] = idDataArray[i];
        }
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public void setId(int id) {
        this.id = id;
        byte[] idDataArray = intToByteArray(id);
        for (int i = 0; i < 4; i++) {
            this.data[20 + i] = idDataArray[i];
        }
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setGetOrSet(int getOrSet) {
        this.getOrSet = getOrSet;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public void setValue(int[] value) {
        this.value = value;
        if (value.length < 4) {
            LogManager.loge(TAG, "setValue: error length of the value array.");
            return;
        }
        for (int j = 0; j < 4; j++) {
            byte[] valueDataArray = intToByteArray(value[j]);
            int startIndex = (j + 10) * 4;
            for (int i = 0; i < 4; i++) {
                this.data[startIndex + i] = valueDataArray[i];
            }
        }
    }

    public static byte[] intToByteArray(int data) {
        byte[] array = {(byte) (data & MAX_BYTE), (byte) ((data >> 8) & MAX_BYTE), (byte) ((data >> INTEGER_INDEX16) & MAX_BYTE), (byte) ((data >> INTEGER_INDEX24) & MAX_BYTE)};
        return array;
    }

    public static int bytes2int(byte[] bytes, int startIndex) {
        int tempA = bytes[startIndex] & 255;
        int tempB = (bytes[startIndex + 1] & 255) << 8;
        int tempC = (bytes[startIndex + 2] & 255) << INTEGER_INDEX16;
        int tempD = (bytes[startIndex + 3] & 255) << INTEGER_INDEX24;
        return tempA | tempB | tempC | tempD;
    }

    private static class Wrapper {
        private VideoCatCmd cmd;

        Wrapper(VideoCatCmd cmd) {
            this.cmd = cmd;
        }

        public CmdBean getCmdBean() {
            CmdBean bean = new CmdBean();
            bean.setId(this.cmd.id);
            bean.setSrcId(this.cmd.srcId);
            bean.setTargetId(this.cmd.targetId);
            bean.setValue(new int[]{this.cmd.value[0], this.cmd.value[1], this.cmd.value[2], this.cmd.value[3]});
            return bean;
        }
    }

    public static CmdBean CmdWrapper(VideoCatCmd cmd) {
        return new Wrapper(cmd).getCmdBean();
    }
}
