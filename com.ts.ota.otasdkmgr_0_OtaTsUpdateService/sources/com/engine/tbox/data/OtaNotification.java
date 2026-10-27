package com.engine.tbox.data;

import com.engine.tbox.core.utils.LogUtils;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class OtaNotification {
    private static final int DELETE_NEW_LENGTH = 18;
    private static final int FACTORY_NEW_LENGTH = 23;
    private static final int FACTORY_TYPE_LENGTH = 7;
    private static final int MAX_NEW_MSG_LENGTH = 33;
    private static final int MIN_NEW_VERSION_LENGTH = 18;
    private static final int OTA_NEW_LENGTH = 26;
    private static final String TAG = "TBoxOtaNotification";
    public static final int TYPE_DELETE_OTA_PACKAGE = 2;
    private static final int TYPE_DELETE_OTA_PACKAGE_LENGTH = 1;
    public static final int TYPE_FACTORY = 1;
    private static final int TYPE_FACTORY_LENGTH = 6;
    public static final int TYPE_PHONE_OTA = 3;
    private static final int TYPE_POS = 16;
    public static final int UUID_LENGTH = 16;
    private OtaDeleteNotification deleteNty;
    private OtaFactoryNotification factoryNty;
    private OtaPhoneOtaNotification phoneNty;
    private int type;

    public OtaPhoneOtaNotification getPhoneNty() {
        return this.phoneNty;
    }

    public void setPhoneNty(OtaPhoneOtaNotification phoneNty) {
        this.phoneNty = phoneNty;
    }

    public OtaDeleteNotification getDeleteNty() {
        return this.deleteNty;
    }

    public void setDeleteNty(OtaDeleteNotification deleteNty) {
        this.deleteNty = deleteNty;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public OtaFactoryNotification getFactoryNty() {
        return this.factoryNty;
    }

    public void setFactoryNty(OtaFactoryNotification factoryNty) {
        this.factoryNty = factoryNty;
    }

    public byte[] getUuidArray() {
        OtaPhoneOtaNotification otaPhoneOtaNotification;
        OtaDeleteNotification otaDeleteNotification;
        OtaFactoryNotification otaFactoryNotification;
        if (this.type == 1 && (otaFactoryNotification = this.factoryNty) != null) {
            return otaFactoryNotification.getUuidByteArray();
        }
        if (this.type == 2 && (otaDeleteNotification = this.deleteNty) != null) {
            return otaDeleteNotification.getUuidByteArray();
        }
        if (this.type == 3 && (otaPhoneOtaNotification = this.phoneNty) != null) {
            return otaPhoneOtaNotification.getUuidByteArray();
        }
        return null;
    }

    @Deprecated
    public static byte[] toByte(OtaNotification otaNty) {
        if (otaNty.type == 1) {
            ByteBuffer buffer = ByteBuffer.allocate(7);
            buffer.put((byte) otaNty.getType());
            OtaFactoryNotification factoryNty = otaNty.getFactoryNty();
            buffer.putShort((short) factoryNty.getTimeout());
            buffer.put((byte) factoryNty.getUpgradeType());
            buffer.put((byte) factoryNty.getMinSoc());
            buffer.putShort((short) factoryNty.getMinVoltage());
            return buffer.array();
        }
        return new byte[0];
    }

    public static OtaNotification bytesOtaNotification(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        byte type = bytes[0];
        if (type == 1 && bytes.length == 7) {
            LogUtils.d(TAG, "use old version", new Object[0]);
            ByteBuffer buffer = ByteBuffer.allocate(7);
            buffer.put(bytes);
            buffer.flip();
            OtaNotification otaNotification = new OtaNotification();
            OtaFactoryNotification factoryNotification = new OtaFactoryNotification();
            otaNotification.setType(buffer.get());
            factoryNotification.setTimeout(buffer.getShort());
            factoryNotification.setUpgradeType(buffer.get());
            factoryNotification.setMinSoc(buffer.get());
            factoryNotification.setMinVoltage(buffer.getShort());
            otaNotification.setFactoryNty(factoryNotification);
            return otaNotification;
        }
        if (bytes.length < 18 || bytes.length > 33) {
            LogUtils.e(TAG, "invalid length:" + bytes.length, new Object[0]);
            return null;
        }
        OtaNotification otaNotification2 = new OtaNotification();
        ByteBuffer buffer2 = ByteBuffer.allocate(33);
        buffer2.put(bytes);
        buffer2.flip();
        otaNotification2.setType(type);
        byte[] uuidArray = new byte[16];
        buffer2.get(uuidArray);
        byte type2 = bytes[16];
        if (type2 == 1) {
            if (bytes.length < 23) {
                LogUtils.e(TAG, "Invalid length for factory notification", new Object[0]);
                return null;
            }
            OtaFactoryNotification factoryNotification2 = new OtaFactoryNotification();
            factoryNotification2.setUuidByteArray(uuidArray);
            otaNotification2.setType(buffer2.get());
            factoryNotification2.setTimeout(buffer2.getShort());
            factoryNotification2.setUpgradeType(buffer2.get());
            factoryNotification2.setMinSoc(buffer2.get());
            factoryNotification2.setMinVoltage(buffer2.getShort());
            otaNotification2.setFactoryNty(factoryNotification2);
            return otaNotification2;
        }
        if (type2 == 2) {
            if (bytes.length < 33) {
                LogUtils.e(TAG, "Invalid Length " + bytes.length + " for Delete Notification", new Object[0]);
                return null;
            }
            OtaDeleteNotification deleteNotification = new OtaDeleteNotification();
            deleteNotification.setUuidByteArray(uuidArray);
            otaNotification2.setType(buffer2.get());
            for (int i = 0; i < 6; i++) {
                buffer2.get();
            }
            int delete = buffer2.get();
            LogUtils.d(TAG, "TYPE_DELETE_OTA_PACKAGE delete:" + delete, new Object[0]);
            if (!OtaDeleteNotification.isValidDeleteOperation(delete)) {
                LogUtils.e(TAG, "Invalid length " + bytes.length + " Delete Operation", new Object[0]);
            }
            deleteNotification.setDelete(delete);
            otaNotification2.setDeleteNty(deleteNotification);
            return otaNotification2;
        }
        if (type2 != 3) {
            return null;
        }
        if (bytes.length < 33) {
            LogUtils.e(TAG, "Invalid length " + bytes.length + " for Phone OTA notification", new Object[0]);
            return null;
        }
        OtaPhoneOtaNotification phoneNotification = new OtaPhoneOtaNotification();
        phoneNotification.setUuidByteArray(uuidArray);
        otaNotification2.setType(buffer2.get());
        for (int i2 = 0; i2 < 7; i2++) {
            buffer2.get();
        }
        phoneNotification.setUpgradeType(buffer2.get());
        LogUtils.i(TAG, "bytebuff limit:" + buffer2.limit(), new Object[0]);
        LogUtils.i(TAG, "bytebuff position:" + buffer2.position(), new Object[0]);
        LogUtils.i(TAG, "bytebuff remaining:" + buffer2.remaining(), new Object[0]);
        phoneNotification.setTimestamp(buffer2.getLong());
        otaNotification2.setPhoneNty(phoneNotification);
        return otaNotification2;
    }

    public String toString() {
        int i = this.type;
        if (i == 1) {
            return "OtaNotification type:" + this.type + " factory:" + this.factoryNty;
        }
        if (i == 2) {
            return "OtaNotification type:" + this.type + " delete:" + this.deleteNty;
        }
        if (i == 3) {
            return "OtaNotification type:" + this.type + " phone:" + this.phoneNty;
        }
        return super.toString();
    }
}
