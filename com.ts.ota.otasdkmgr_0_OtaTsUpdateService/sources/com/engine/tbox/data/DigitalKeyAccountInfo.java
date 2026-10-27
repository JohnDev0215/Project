package com.engine.tbox.data;

import java.nio.ByteBuffer;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class DigitalKeyAccountInfo {
    private static final byte[] DEAFULT_KEYID_ARRAY = {-1, -1, -1, -1, -1, -1, -1, -1};
    public static final int HAVE_CAR_ACCOUNT = 1;
    public static final int INVALID_KEY_ENTITY = 0;
    private static final int KEY_ID_LENGTH = 8;
    public static final int KEY_TYPE_ANDROID = 2;
    public static final int KEY_TYPE_BLUETOOTH = 5;
    public static final int KEY_TYPE_CARD = 3;
    public static final int KEY_TYPE_CLOUD = 4;
    public static final int KEY_TYPE_ENTITY = 6;
    public static final int KEY_TYPE_IPHONE = 1;
    private static final int MIN_LENGTH = 12;
    public static final int NO_CAR_ACCOUNT = 0;
    private static final int USER_ID_MAX_LENGTH = 10;
    public static final int VALID_KEY_ENTITY = 1;
    private int carAccount;
    private int entityKeyTag;

    @Deprecated
    private String keyId;
    private byte[] keyIdArray = DEAFULT_KEYID_ARRAY;
    private int keyType;

    @Deprecated
    private String userId;
    private byte[] userIdArray;

    public int getCarAccount() {
        return this.carAccount;
    }

    public void setCarAccount(int carAccount) {
        this.carAccount = carAccount;
    }

    public int getEntityKeyTag() {
        return this.entityKeyTag;
    }

    public void setEntityKeyTag(int entityKeyTag) {
        this.entityKeyTag = entityKeyTag;
    }

    public int getKeyType() {
        return this.keyType;
    }

    public void setKeyType(int keyType) {
        this.keyType = keyType;
    }

    @Deprecated
    public String getUserId() {
        return this.userId;
    }

    @Deprecated
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public byte[] getUserIdArray() {
        return this.userIdArray;
    }

    public void setUserIdArray(byte[] array) {
        this.userIdArray = array;
    }

    @Deprecated
    public String getKeyId() {
        return this.keyId;
    }

    @Deprecated
    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public byte[] getKeyIdArray() {
        return this.keyIdArray;
    }

    public void setKeyIdArray(byte[] array) {
        this.keyIdArray = array;
    }

    public boolean isValidDigitalKeyAccountInfo() {
        byte[] bArr = this.keyIdArray;
        return bArr != null && bArr.length == 8 && isValidCarAccount(this.carAccount) && isValidKeyEntity(this.entityKeyTag) && isValidKeyType(this.keyType);
    }

    public static boolean isValidKeyType(int type) {
        return type != 0;
    }

    public static boolean isValidCarAccount(int account) {
        return account == 1 || account == 0;
    }

    public static boolean isValidKeyEntity(int entity) {
        return entity == 1 || entity == 0;
    }

    public String toString() {
        return "DigitalKeyAccountInfo [carAccount=" + this.carAccount + ", entityKeyTag=" + this.entityKeyTag + ", keyType=" + this.keyType + ", userId=" + this.userId + ", keyId=" + Arrays.toString(this.keyIdArray) + "]";
    }

    public byte[] toByteArray() {
        byte[] bArr = this.userIdArray;
        int len = Math.min(bArr == null ? 0 : bArr.length, 10);
        ByteBuffer buffer = ByteBuffer.allocate(len + 12);
        buffer.put((byte) this.carAccount);
        buffer.put((byte) this.entityKeyTag);
        buffer.put((byte) this.keyType);
        buffer.put((byte) len);
        if (len > 0) {
            buffer.put(this.userIdArray, 0, len);
        }
        byte[] bArr2 = this.keyIdArray;
        if (bArr2 != null) {
            buffer.put(bArr2, 0, Math.min(bArr2.length, 8));
        }
        return buffer.array();
    }
}
