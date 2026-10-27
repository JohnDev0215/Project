package com.engine.tbox.utils;

import com.engine.tbox.core.utils.LogUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import vendor.bosch.tee.V1_0.BoschResult;
import vendor.bosch.tee.V1_0.IBoschTeeService;

/* JADX INFO: loaded from: classes.dex */
public class PrivateKeyStore {
    public static final int DATA_ENCRYPT = 1;
    public static final int DATA_WRITE_POS = 0;
    public static final int HAL_BOSCH_TEE_OK = 0;
    private static final int HSM_ASE_KEY_LENGTH = 32;
    private static final int HSM_DATA_RESERVED_SPACE_2K = 2048;
    private static final int HSM_DATA_RESERVED_SPACE_4K = 4096;
    private static final int HSM_REAL_DATA_FLAG_LENGTH = 2;
    private static final int KEY_LENGTH = 32;
    private static final String TAG = "TboxKeyStore";
    private AtomicBoolean mKeyDelte = new AtomicBoolean(false);
    IBoschTeeService mTeeService;

    public PrivateKeyStore() {
        getTeeService();
    }

    private void getTeeService() {
        try {
            this.mTeeService = IBoschTeeService.getService();
            LogUtils.i(TAG, " onCreate initPrivateKeyStore " + this.mTeeService, new Object[0]);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public boolean saveDataToHsm(int keyId, byte[] dataArray) {
        ArrayList<Byte> data = byteArrayToArrayList(dataArray);
        if (data == null) {
            LogUtils.e(TAG, "saveDataToHsm dataArray is null", new Object[0]);
            return false;
        }
        if (this.mTeeService == null) {
            getTeeService();
            if (this.mTeeService == null) {
                LogUtils.e(TAG, "saveDataToHsm mTeeService == null , keyId " + keyId + " fail", new Object[0]);
                return false;
            }
        }
        BoschResult saveResult = null;
        try {
            int currentDataLength = data.size() + 2;
            int createDataLength = HSM_DATA_RESERVED_SPACE_4K;
            if (currentDataLength > HSM_DATA_RESERVED_SPACE_4K) {
                createDataLength = currentDataLength + HSM_DATA_RESERVED_SPACE_2K;
            }
            LogUtils.i(TAG, "currentDataLength " + currentDataLength + " reservedSpace " + HSM_DATA_RESERVED_SPACE_4K + " createDataLength " + createDataLength, new Object[0]);
            LogUtils.i(TAG, " createData DATA_ENCRYPT : 1", new Object[0]);
            BoschResult createResult = this.mTeeService.createData(keyId, createDataLength, 0, 1);
            LogUtils.i(TAG, "  saveDataToHSM keyId " + keyId + " createResult " + createResult, new Object[0]);
            StringBuilder sb = new StringBuilder();
            sb.append("saveDataToHSM data ");
            sb.append(data);
            LogUtils.v(TAG, sb.toString(), new Object[0]);
            byte height = (byte) (data.size() / 127);
            byte low = (byte) (data.size() % 127);
            List<Byte> lengthData = new ArrayList<>(2);
            lengthData.add(Byte.valueOf(height));
            lengthData.add(Byte.valueOf(low));
            ArrayList<Byte> saveData = new ArrayList<>();
            saveData.addAll(0, lengthData);
            saveData.addAll(data);
            saveResult = this.mTeeService.writeData(keyId, 0, saveData);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("  saveDataToHSM keyId ");
            sb2.append(keyId);
            sb2.append(" dataSize:");
            sb2.append(saveData.size());
            sb2.append(" ,real dataSize:");
            sb2.append(saveData.size() - 2);
            sb2.append(" ,height:");
            sb2.append((int) height);
            sb2.append(" ,low:");
            sb2.append((int) low);
            sb2.append(" writeData saveResult ");
            sb2.append(saveResult.ret);
            LogUtils.d(TAG, sb2.toString(), new Object[0]);
            if (saveResult.ret == 0) {
                LogUtils.i(TAG, "save to HSM success " + keyId, new Object[0]);
                this.mKeyDelte.set(false);
                return true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        LogUtils.e(TAG, keyId + "  save to HSM fail , saveResult " + saveResult, new Object[0]);
        return false;
    }

    public byte[] readDataFromHsm(int keyId) {
        boolean enable = this.mKeyDelte.get();
        if (enable) {
            LogUtils.i(TAG, "remove data from hsm", new Object[0]);
            return null;
        }
        if (this.mTeeService == null) {
            getTeeService();
            if (this.mTeeService == null) {
                LogUtils.e(TAG, "readDataFromHsm mTeeService:" + this.mTeeService + " mKeyDelte:" + enable, new Object[0]);
                return null;
            }
        }
        try {
            BoschResult dataExist = this.mTeeService.isDataExist(keyId);
            LogUtils.i(TAG, " readDataFromHsm " + dataExist, new Object[0]);
            if (dataExist.ret == 0) {
                ArrayList<Byte> arrayList = dataExist.extra_data;
                BoschResult boschResult = this.mTeeService.readData(keyId, 0, 34);
                LogUtils.i(TAG, " keyId " + keyId + " dataLength 34", new Object[0]);
                if (boschResult.ret != 0) {
                    LogUtils.e(TAG, "read from bosch tee failed", new Object[0]);
                    return null;
                }
                ArrayList<Byte> extraData = boschResult.extra_data;
                Byte height = extraData.get(0);
                Byte low = extraData.get(1);
                int realDataLength = (height.byteValue() * 127) + low.byteValue();
                LogUtils.i(TAG, "readDataFromHsm keyId:" + keyId + " ,realDataLength:" + realDataLength + " ,height:" + height + " ,low:" + low, new Object[0]);
                if (realDataLength != 32) {
                    LogUtils.e(TAG, "realDataLength:" + realDataLength + " not match:32", new Object[0]);
                    return null;
                }
                Collection<? extends Byte> subList = extraData.subList(2, realDataLength + 2);
                ArrayList<Byte> realData = new ArrayList<>();
                realData.addAll(subList);
                LogUtils.v(TAG, "readDataFromHsm keyId " + keyId + " realData " + realData, new Object[0]);
                this.mKeyDelte.set(false);
                return byteListToArray(realData);
            }
            return null;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public void deleteKey() {
        this.mKeyDelte.set(true);
    }

    private static byte[] byteListToArray(ArrayList<Byte> bytes) {
        if (bytes == null) {
            return null;
        }
        byte[] data = new byte[bytes.size()];
        for (int i = 0; i < bytes.size(); i++) {
            data[i] = bytes.get(i).byteValue();
        }
        return data;
    }

    private static ArrayList<Byte> byteArrayToArrayList(byte[] sourceData) {
        if (sourceData == null) {
            return null;
        }
        ArrayList<Byte> data = new ArrayList<>();
        for (byte b : sourceData) {
            data.add(Byte.valueOf(b));
        }
        return data;
    }
}
