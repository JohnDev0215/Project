package com.engine.tbox.item;

import com.engine.tbox.core.utils.LogUtils;
import com.engine.tbox.data.ApnArgs;
import com.engine.tbox.data.NetworkOperator;
import com.engine.tbox.emulator.RandomGenerator;
import com.engine.tbox.item.TboxMessage;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MessageUtils {
    private static final byte CAN_DATA_LENGTH = 8;
    public static final int CHANGE_IPHONE_DEVICE = 4;
    public static final int DELETE_ALL_IPHONE_KEY = 2;
    public static final int DELETE_ALL_KEY = 6;
    public static final int DELETE_SINGLE_KEY = 1;
    private static final byte HEX = 16;
    private static final int MAX_OP_LIST = 255;
    public static final int NACK_CHECKSUM_ERROR = 1;
    public static final int NACK_CMD_ERROR = 2;
    public static final int NACK_STATE_ERROR = 3;
    private static final byte NFC_KEY_LENGTH = 24;
    private static final int OP_LENGTH = 5;
    public static final int QUERY_ALL_KEY = 3;
    public static final int REGISTER_KEY = 5;
    private static final byte START_ZERO_BYTE = 48;
    public static final int STATIC_INFO_LENGTH_V87 = 79;
    public static final int STATIC_INFO_LENGTH_V901 = 97;
    public static final int TBOX_FTP_END_NTY = 1;
    public static final int TBOX_FTP_INFO_LENGTH = 170;
    public static final int TBOX_FTP_PASSWORD = 20;
    public static final int TBOX_FTP_PORT = 2;
    public static final int TBOX_FTP_PROCESS = 1;
    public static final int TBOX_FTP_RESULT = 1;
    public static final int TBOX_FTP_URL = 128;
    public static final int TBOX_FTP_USER = 20;
    public static final int TBOX_HARD_VERSION_LEN = 20;
    public static final int TBOX_ICCID_LEN = 20;
    public static final int TBOX_IMEI_LEN = 15;
    public static final int TBOX_IMSI_LEN = 15;
    public static final int TBOX_IVI_EXCHANGE_RES_LENGTH = 16;
    public static final int TBOX_IVI_PUBLIC_KEY = 128;
    public static final int TBOX_IVI_VIRTUAL_VIN_RSP_LEN = 20;
    public static final int TBOX_PHONENUMBER_LEN = 15;
    public static final int TBOX_SERIAL_LEN = 24;
    public static final int TBOX_SOFTCODE_LEN = 18;
    public static final int TBOX_SOFT_VERSION_LEN = 20;
    public static final int UPDATE_KEY = 5;

    public static TboxMessage buildLClientHeartbeatMessage() {
        return buildCustomMessage(MessageConstants.IVI_TBOX_KEEP_ALIVE_REQ, null);
    }

    public static TboxMessage buildLServerHeartbeatMessage() {
        return buildCustomMessage(513, null);
    }

    public static TboxMessage buildClientTBoxStaticInfoRequestMessage() {
        return buildCustomMessage(MessageConstants.IVI_TBOX_STATIC_INFO_REQ, null);
    }

    public static TboxMessage buildServerTBoxStaticInfoResponseMessage(byte[] data) {
        return buildCustomMessage(MessageConstants.TBOX_IVI_STATIC_INFO_RSP, data);
    }

    public static byte[] prepareServerTBoxStaticInfo() {
        byte[] tboxSoftVersion = new byte[20];
        byte[] tboxHardVersion = new byte[20];
        byte[] serial = new byte[24];
        byte[] imsi = new byte[15];
        byte[] softcode = new byte[18];
        RandomGenerator.generatorChar(tboxSoftVersion);
        RandomGenerator.generatorChar(tboxHardVersion);
        RandomGenerator.generatorChar(serial);
        RandomGenerator.generatorChar(imsi);
        RandomGenerator.generatorChar(softcode);
        byte[] data = new byte[97];
        System.arraycopy(tboxSoftVersion, 0, data, 0, tboxSoftVersion.length);
        int pos = 0 + tboxSoftVersion.length;
        System.arraycopy(tboxHardVersion, 0, data, pos, tboxHardVersion.length);
        int pos2 = pos + tboxHardVersion.length;
        System.arraycopy(serial, 0, data, pos2, serial.length);
        int pos3 = pos2 + serial.length;
        System.arraycopy(imsi, 0, data, pos3, imsi.length);
        System.arraycopy(softcode, 0, data, pos3 + imsi.length, softcode.length);
        return data;
    }

    public static byte[] prepareServerOpListData(int count) {
        byte[] bytes = {(byte) count};
        LogUtils.d(LogUtils.TAG, "prepareServerOpListData count:" + ((int) bytes[0]), new Object[0]);
        String basicString = new String(bytes, StandardCharsets.UTF_8).trim();
        LogUtils.d(LogUtils.TAG, "prepareServerOpListData count 2:" + ((int) basicString.getBytes(StandardCharsets.UTF_8)[0]), new Object[0]);
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(basicString);
        for (int i = 0; i < count; i++) {
            byte[] data = new byte[5];
            RandomGenerator.generatorNumberString(data);
            String tmpString = new String(data, StandardCharsets.UTF_8).trim();
            strBuilder.append(tmpString);
        }
        return strBuilder.toString().getBytes(StandardCharsets.UTF_8);
    }

    public static TboxMessage buildClientUpgradeReqMessage() {
        return buildCustomMessage(MessageConstants.IVI_TBOX_UPDATE_REQ, null);
    }

    public static TboxMessage buildServerUpgradeResponseMessage(byte[] data) {
        return buildCustomMessage(MessageConstants.TBOX_IVI_UPDATE_RSP, data);
    }

    public static byte[] prepareServerUpgradeResponseData() {
        byte[] ftpUrl = new byte[128];
        byte[] ftpPort = new byte[2];
        byte[] ftpUser = new byte[20];
        byte[] ftpPassword = new byte[20];
        RandomGenerator.generatorChar(ftpUrl);
        RandomGenerator.generatorNumber(ftpPort);
        RandomGenerator.generatorChar(ftpUser);
        RandomGenerator.generatorChar(ftpPassword);
        byte[] data = new byte[TBOX_FTP_INFO_LENGTH];
        System.arraycopy(ftpUrl, 0, data, 0, ftpUrl.length);
        int pos = 0 + ftpUrl.length;
        System.arraycopy(ftpPort, 0, data, pos, ftpPort.length);
        int pos2 = pos + ftpPort.length;
        System.arraycopy(ftpUser, 0, data, pos2, ftpUser.length);
        int pos3 = pos2 + ftpUser.length;
        System.arraycopy(ftpPassword, 0, data, pos3, ftpPassword.length);
        int length = pos3 + ftpPassword.length;
        return data;
    }

    public static TboxMessage buildServerUpgradeProcessMessage(byte[] data) {
        return buildCustomMessage(MessageConstants.TBOX_IVI_UPDATE_PROCESS, data);
    }

    public static TboxMessage buildLClientSendEndNotifyMessage(byte[] data) {
        return buildCustomMessage(MessageConstants.IVI_TBOX_UPDATE_DATA_NTY, data);
    }

    public static TboxMessage buildServerUpgradeResult(byte[] data) {
        return buildCustomMessage(MessageConstants.TBOX_IVI_UPDATE_RESULT, data);
    }

    public static TboxMessage buildClientExportLogReqMessage() {
        return buildCustomMessage(MessageConstants.IVI_TBOX_LOG_REQ, null);
    }

    public static TboxMessage buildServerExportLogResponseMessage(byte[] data) {
        return buildCustomMessage(MessageConstants.TBOX_IVI_LOG_RSP, data);
    }

    public static TboxMessage buildClientExportLogNotifyMessage(byte[] data) {
        return buildCustomMessage(MessageConstants.IVI_TBOX_LOG_NTY, data);
    }

    public static byte[] buildRestartTBoxMessageData(int state) {
        byte[] data = {(byte) state};
        return data;
    }

    public static TboxMessage buildRestartTBoxMessage(byte[] data) {
        return buildCustomMessage(MessageConstants.IVI_TBOX_RESTART_REQ, data);
    }

    public static TboxMessage buildCustomMessage(int cmd, byte[] data) {
        TboxMessage.Builder builder = new TboxMessage.Builder();
        if (data == null) {
            return builder.addCmd((short) cmd).build();
        }
        return builder.addCmd((short) cmd).putData(data).build();
    }

    public static TboxMessage buildResAckMessage() {
        return buildCustomMessage(MessageConstants.IVI_TBOX_RES_ACK, null);
    }

    public static TboxMessage buildResNAckMessage(byte[] data) {
        return buildCustomMessage(MessageConstants.IVI_TBOX_RES_NACK, data);
    }

    public static TboxMessage buildTBoxMessageExChangeOffer(byte[] data) {
        return buildCustomMessage(MessageConstants.IVI_TBOX_EXCHANGE_OFFER, data);
    }

    public static TboxMessage buildTBoxMessageExChangeReq() {
        byte[] bytes = {1};
        return buildCustomMessage(MessageConstants.IVI_TBOX_EXCHANGE_REQ, bytes);
    }

    public static TboxMessage buildTBoxMessageExChangeRes(byte[] data) {
        return buildCustomMessage(MessageConstants.TBOX_IVI_EXCHANGE_RES, data);
    }

    public static TboxMessage buildTBoxMessageExChangeInit(byte[] data) {
        return buildCustomMessage(MessageConstants.TBOX_IVI_EXCHANGE_INIT, data);
    }

    public static TboxMessage buildTBoxMessageExChangeResult(boolean z) {
        return new TboxMessage.Builder().addCmd((short) 787).putData(new byte[]{(byte) (!z ? 1 : 0)}).build();
    }

    public static TboxMessage buildTBoxMessageExChangeAck() {
        return buildCustomMessage(MessageConstants.TBOX_IVI_EXCHANGE_ACK, null);
    }

    public static TboxMessage buildServerNetworkTrafficReq(byte[] data) {
        return buildCustomMessage(MessageConstants.TBOX_IVI_NETWORK_TRAFFIC_QUERY_REQ, data);
    }

    public static TboxMessage buildNetwrokTrafficeRsp(byte[] data) {
        return buildCustomMessage(MessageConstants.IVI_TBOX_NETWORK_TRAFFIC_QUERY_RSP, data);
    }

    public static TboxMessage buildServerVersionReq() {
        return buildCustomMessage(MessageConstants.TBOX_IVI_VERSION_REQ, null);
    }

    public static TboxMessage buildVersionRsp(byte[] data) {
        return buildCustomMessage(MessageConstants.IVI_TBOX_VERSION_RSP, data);
    }

    public static TboxMessage buildServerSnReq() {
        return buildCustomMessage(MessageConstants.TBOX_IVI_SN_REQ, null);
    }

    public static TboxMessage buildSnRsp(byte[] data) {
        return buildCustomMessage(MessageConstants.IVI_TBOX_SN_RSP, data);
    }

    public static TboxMessage buildActiveCode(byte[] data) {
        return buildCustomMessage(MessageConstants.IVI_TBOX_ACTIVATION_REQ, data);
    }

    public static TboxMessage buildServeGetVirtualVinReq(byte[] data) {
        return buildCustomMessage(MessageConstants.TBOX_IVI_VIRTUAL_VIN_RSP, data);
    }

    public static TboxMessage buildServeActiveCodeRsp(byte[] data) {
        return buildCustomMessage(MessageConstants.TBOX_IVI_ACTIVATION_RSP, data);
    }

    public static TboxMessage buildServerTBoxVersionRsp(byte[] data) {
        return buildCustomMessage(MessageConstants.TBOX_IVI_CLOUD_VERSION_RSP, data);
    }

    public static TboxMessage buildServiceConnectRsp(byte[] data) {
        return buildCustomMessage(MessageConstants.TBOX_IVI_CLOUD_CONNECT_RSP, data);
    }

    public static TboxMessage buildServiceSwitchEnvRsp(byte[] data) {
        return buildCustomMessage(MessageConstants.TBOX_IVI_SWITCH_CLOUD_ENV_RSP, data);
    }

    public static TboxMessage buildServiceCloudEnvRsp(byte[] data) {
        return buildCustomMessage(MessageConstants.TBOX_IVI_CLOUD_ENV_RSP, data);
    }

    public static byte[] buildGetVirtualVinRsp(String str, boolean z) {
        byte[] bArr = new byte[20];
        bArr[0] = z ? (byte) 1 : (byte) 0;
        if (z) {
            byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
            System.arraycopy(bytes, 0, bArr, 1, bytes.length);
        }
        return bArr;
    }

    public static TboxMessage buildGetVirtualVinReq() {
        return buildCustomMessage(MessageConstants.IVI_TBOX_VIRTUAL_VIN_REQ, null);
    }

    public static TboxMessage buildGetVirtualVinNty(byte[] data) {
        return buildCustomMessage(MessageConstants.IVI_TBOX_VIRTUAL_VIN_NTY, data);
    }

    public static TboxMessage buildGetCloudServiceEnvReqMessage() {
        return buildCustomMessage(MessageConstants.IVI_TBOX_CLOUD_ENV_REQ, null);
    }

    public static TboxMessage buildGetCloudServiceEnvRspMessage(byte[] data) {
        return buildCustomMessage(MessageConstants.TBOX_IVI_CLOUD_ENV_RSP, data);
    }

    public static TboxMessage buildGetCloudServiceConnectState() {
        return buildCustomMessage(MessageConstants.IVI_TBOX_CLOUD_CONNECT_REQ, null);
    }

    public static byte[] buildSwitchCloudServiceEnvData(int env) {
        byte[] data = {(byte) env};
        return data;
    }

    public static TboxMessage buildSwitchCloudServiceEnv(byte[] data) {
        return buildCustomMessage(MessageConstants.IVI_TBOX_SWITCH_CLOUD_ENV_REQ, data);
    }

    public static TboxMessage buildCloudServiceVersion() {
        return buildCustomMessage(MessageConstants.IVI_TBOX_CLOUD_VERSION_REQ, null);
    }

    public static TboxMessage buildEnableApn2State(byte[] data) {
        return buildCustomMessage(MessageConstants.IVI_TBOX_APN2_STATE_REQ, data);
    }

    public static TboxMessage buildEnableApn3State(byte[] data) {
        return buildCustomMessage(MessageConstants.IVI_TBOX_APN3_REQ, data);
    }

    public static TboxMessage buildOtaInfo(byte[] bytes) {
        return buildCustomMessage(MessageConstants.TBOX_IVI_OTA_INFO_NTY, bytes);
    }

    public static TboxMessage buildOtaRes(byte[] bytes) {
        return buildCustomMessage(MessageConstants.IVI_TBOX_OTA_RES, bytes);
    }

    public static TboxMessage buildSendOtaState(byte[] bytes) {
        return buildCustomMessage(MessageConstants.IVI_TBOX_OTA_STATE_NTY, bytes);
    }

    public static TboxMessage buildChargePlaneDown(byte[] bytes) {
        return buildCustomMessage(MessageConstants.TBOX_IVI_CHARGE_PLAN_DOWN, bytes);
    }

    public static TboxMessage buildChargePlaneRes(byte[] bytes) {
        return buildCustomMessage(MessageConstants.IVI_TBOX_CHARGE_PLAN_RES, bytes);
    }

    public static TboxMessage buildUserChargePlaneDown(byte[] bytes) {
        return buildCustomMessage(MessageConstants.TBOX_IVI_CHARGE_CHANGE_DOWN, bytes);
    }

    public static TboxMessage buildUserChargePlaneRes(byte[] bytes) {
        return buildCustomMessage(MessageConstants.IVI_TBOX_CHARGE_CHANGE_RES, bytes);
    }

    public static TboxMessage buildChargePlaneUp(byte[] bytes) {
        return buildCustomMessage(MessageConstants.IVI_TBOX_CHARGE_PLAN_UP, bytes);
    }

    public static TboxMessage buildChargeControl(byte[] bytes) {
        return buildCustomMessage(MessageConstants.IVI_TBOX_CHARGE_CONTROL_UP, bytes);
    }

    public static TboxMessage buildTBoxGetOperationList() {
        return buildCustomMessage(6673, null);
    }

    public static TboxMessage buildTBoxServerOperationList(byte[] bytes) {
        return buildCustomMessage(6657, bytes);
    }

    public static TboxMessage buildTBoxSelectOperator(byte[] bytes) {
        return buildCustomMessage(6929, bytes);
    }

    public static byte[] buildNationData(int left, int nationalCode, String simpleCode) {
        ByteBuffer buffer = ByteBuffer.allocate(7);
        buffer.put((byte) left);
        buffer.putShort((short) nationalCode);
        byte[] bytes = simpleCode.getBytes(StandardCharsets.UTF_8);
        byte[] dest = new byte[4];
        System.arraycopy(bytes, 0, dest, 0, Math.min(bytes.length, dest.length));
        buffer.put(dest);
        buffer.flip();
        return buffer.array();
    }

    public static TboxMessage buildServerNationReqMessage() {
        return buildCustomMessage(MessageConstants.TBOX_IVI_NATION_REQ, null);
    }

    public static TboxMessage buildNationMessage(byte[] data) {
        return buildCustomMessage(MessageConstants.IVI_TBOX_NATION_RSP, data);
    }

    public static TboxMessage buildAppVersionCode(byte[] data) {
        return buildCustomMessage(MessageConstants.IVI_TBOX_VERSION_CODE, data);
    }

    public static TboxMessage buildNfcLockStatus(int type, int status) {
        byte[] data = new byte[3];
        int pos = 0 + 1;
        data[0] = (byte) type;
        data[pos] = 1;
        data[pos + 1] = (byte) status;
        return buildNfcReq(data);
    }

    public static TboxMessage buildNfcReq(byte[] data) {
        return buildCustomMessage(MessageConstants.IVI_TBOX_NFC_REQ, data);
    }

    public static TboxMessage buildNfcRsp(byte[] data) {
        return buildCustomMessage(MessageConstants.TBOX_IVI_NFC_RSP, data);
    }

    public static TboxMessage buildNfcPackageRsp(byte[] data) {
        return buildCustomMessage(MessageConstants.IVI_TBOX_NFC_ANS, data);
    }

    public static TboxMessage buildRegisterNfc() {
        byte[] data = {5, 0};
        return buildNfcReq(data);
    }

    public static TboxMessage buildQueryAllNfc(byte[] data) {
        byte[] sendData = new byte[data.length + 2];
        sendData[0] = 3;
        sendData[1] = (byte) data.length;
        System.arraycopy(data, 0, sendData, 2, data.length);
        LogUtils.i(LogUtils.TAG, "buildQueryAllNfc:" + Arrays.toString(sendData), new Object[0]);
        return buildNfcReq(sendData);
    }

    public static TboxMessage buildNfcUpdate(byte[] key) {
        byte[] data = new byte[key.length + 2];
        data[0] = 4;
        data[1] = (byte) key.length;
        System.arraycopy(key, 0, data, 2, key.length);
        return buildNfcReq(data);
    }

    public static TboxMessage buildNfcDeleteReq(byte[] data) {
        return buildCustomMessage(MessageConstants.IVI_TBOX_NFC_REQ, data);
    }

    public static TboxMessage buildNfcDeleteRsp(byte[] data) {
        return buildCustomMessage(MessageConstants.TBOX_IVI_DEL_NFC_RSP, data);
    }

    public static TboxMessage buildNfcDelete(int op, byte[] key) {
        int len = Math.min(key.length, 24);
        byte[] data = new byte[len + 2];
        data[0] = (byte) op;
        data[1] = (byte) len;
        System.arraycopy(key, 0, data, 2, len);
        return buildNfcDeleteReq(data);
    }

    public static TboxMessage buildNfcRecvPackage(int totalPacketCount, int packetCount) {
        byte[] data = {(byte) totalPacketCount, (byte) packetCount};
        return buildNfcPackageRsp(data);
    }

    public static String dataArrayToString(byte[] data, int pos, int len) {
        int index = pos;
        while (index < pos + len && data[index] != 0) {
            index++;
        }
        LogUtils.d(LogUtils.TAG, "pos:" + pos + " index:" + index, new Object[0]);
        return new String(data, pos, index - pos, StandardCharsets.UTF_8).trim();
    }

    public static TboxMessage buildNfcAllDelete(int op) {
        byte[] data = {(byte) op, 0};
        return buildNfcDeleteReq(data);
    }

    public static TboxMessage buildAdbRsp(byte[] data) {
        return buildCustomMessage(MessageConstants.IVI_TBOX_ADB_PPRT_REQ, data);
    }

    public static TboxMessage buildPrivatePolicy(byte[] data) {
        return buildCustomMessage(MessageConstants.IVI_TBOX_PRIVACY_SET_REQ, data);
    }

    public static TboxMessage buildNitzReq() {
        return buildCustomMessage(MessageConstants.IVI_TBOX_NITZ_DATA_REQ, null);
    }

    public static TboxMessage buildTcpdumpMessage(int req) {
        return buildIntTboxMessage(MessageConstants.IVI_TBOX_TCPDUMP_REQ, req);
    }

    public static TboxMessage buildDeleteTboxLogMessage(int req) {
        return buildIntTboxMessage(MessageConstants.IVI_TBOX_DELETE_LOG_REQ, req);
    }

    public static TboxMessage buildApn1Req() {
        return buildCustomMessage(MessageConstants.IVI_TBOX_APN_STATE_REQ, null);
    }

    public static TboxMessage buildBindCloudReq() {
        return buildCustomMessage(MessageConstants.IVI_TBOX_CLOUD_BIND_REQ, null);
    }

    public static TboxMessage buildBindCloudRes(int ack) {
        return buildCustomMessage(MessageConstants.IVI_TBOX_CLOUD_BIND_RES, new byte[]{(byte) ack});
    }

    private static TboxMessage buildIntTboxMessage(int msg, int req) {
        byte[] data = {(byte) req};
        return buildCustomMessage(msg, data);
    }

    public static byte[] arrayToStringArray(byte[] array, int startPos) {
        return arrayToStringArrayWithLen(array, startPos, array.length - startPos);
    }

    public static byte[] arrayToStringArrayWithLen(byte[] array, int startPos, int len) {
        if (array == null || array.length < startPos + len) {
            System.err.println("error args array:" + Arrays.toString(array) + " startPos:" + startPos);
            return null;
        }
        int expectLen = (len * 2) + startPos;
        byte[] expectArray = new byte[expectLen];
        StringBuilder sb = new StringBuilder();
        for (int i = startPos; i < startPos + len; i++) {
            sb.append(String.format("%02X", Byte.valueOf(array[i])));
        }
        byte[] stringArray = sb.toString().getBytes(StandardCharsets.UTF_8);
        System.arraycopy(array, 0, expectArray, 0, startPos);
        System.arraycopy(stringArray, 0, expectArray, startPos, stringArray.length);
        return expectArray;
    }

    public static byte[] stringArrayToArray(byte[] stringArray, int startPos) {
        if (stringArray == null || stringArray.length < startPos) {
            System.err.println("error args array:" + Arrays.toString(stringArray) + " startPos:" + startPos);
            return null;
        }
        int expectLen = ((stringArray.length - startPos) / 2) + startPos;
        byte[] expectArray = new byte[expectLen];
        byte[] array = new byte[(stringArray.length - startPos) / 2];
        int index = 0;
        int i = startPos;
        while (i + 1 < stringArray.length) {
            byte outputByte1 = (byte) (stringArray[i] - 48);
            byte outputByte2 = (byte) (stringArray[i + 1] - 48);
            array[index] = (byte) (((outputByte1 & (-1)) * 16) + ((byte) (outputByte2 & (-1))));
            i += 2;
            index++;
        }
        System.arraycopy(stringArray, 0, expectArray, 0, startPos);
        System.arraycopy(array, 0, expectArray, startPos, array.length);
        return expectArray;
    }

    @Deprecated
    public static TboxMessage buildNfcServerCanMessage(short id, byte subid, byte pos, byte[] data) {
        if (data == null) {
            LogUtils.e(LogUtils.TAG, "error data message length", new Object[0]);
            return null;
        }
        int length = data.length;
        int len = 5 + length;
        ByteBuffer buffer = ByteBuffer.allocate(len);
        buffer.putShort(id);
        buffer.put(subid);
        buffer.put((byte) length);
        buffer.put(pos);
        buffer.put(data);
        return buildCustomMessage(MessageConstants.IVI_TBOX_NFC_CAN_NTY, buffer.array());
    }

    public static TboxMessage buildNfcTboxMessage(short id, byte[] data) {
        if (data == null) {
            LogUtils.e(LogUtils.TAG, "buildNfcTboxMessage data is null", new Object[0]);
            data = new byte[8];
        }
        ByteBuffer buffer = ByteBuffer.allocate(data.length + 2);
        buffer.putShort(id);
        buffer.put(data);
        return buildCustomMessage(MessageConstants.IVI_TBOX_NFC_CAN_NTY, buffer.array());
    }

    public static TboxMessage buildOtaSupoortMessage(boolean z) {
        return buildCustomMessage(MessageConstants.IVI_TBOX_SUPPORT_RSP, new byte[]{z ? (byte) 1 : (byte) 0});
    }

    public static String toHexString(byte[] array) {
        if (array == null || array.length <= 0) {
            return null;
        }
        StringBuffer sb = new StringBuffer(array.length * 2);
        for (byte data : array) {
            sb.append(String.format("%02X", Byte.valueOf(data)));
        }
        String str = sb.toString();
        return str;
    }

    public static TboxMessage buildNetworkModeReq() {
        return buildCustomMessage(MessageConstants.IVI_TBOX_NETWORK_MODE_REQ, null);
    }

    public static TboxMessage buildNetworkModeNty(int mode) {
        byte[] data = {(byte) mode};
        return buildCustomMessage(MessageConstants.TBOX_IVI_NETWORK_MODE_NTY, data);
    }

    public static TboxMessage buildGetManualNetworkListReq() {
        return buildCustomMessage(6673, null);
    }

    public static TboxMessage buildtManualNetworkListNty(List<NetworkOperator> list) {
        int size = 0;
        if (list != null) {
            size = list.size();
        }
        int length = (size * 8) + 1;
        byte[] data = new byte[length];
        data[0] = (byte) size;
        if (list != null) {
            int pos = 1;
            for (NetworkOperator op : list) {
                byte[] srcData = op.getMccMnc().getBytes(StandardCharsets.UTF_8);
                int length2 = 6;
                if (srcData.length <= 6) {
                    length2 = srcData.length;
                }
                System.arraycopy(srcData, 0, data, pos, length2);
                int pos2 = pos + 6;
                int pos3 = pos2 + 1;
                data[pos2] = (byte) op.getEnable();
                pos = pos3 + 1;
                data[pos3] = (byte) op.getSigLevel();
            }
        }
        return buildCustomMessage(6657, data);
    }

    public static TboxMessage buildSelectManualNetworkReq(String mccMnc, int sigLevel) {
        byte[] data = new byte[7];
        byte[] srcData = mccMnc.getBytes(StandardCharsets.UTF_8);
        System.arraycopy(srcData, 0, data, 0, srcData.length > 6 ? 6 : srcData.length);
        int pos = 0 + 6;
        int i = pos + 1;
        data[pos] = (byte) sigLevel;
        return buildCustomMessage(6929, data);
    }

    public static TboxMessage buildSelectManualNty(String mccMnc, int sigLevel, int result, String lastMccMnc, int lastSigLevel) {
        byte[] data = new byte[15];
        byte[] srcData = mccMnc.getBytes(StandardCharsets.UTF_8);
        System.arraycopy(srcData, 0, data, 0, srcData.length > 6 ? 6 : srcData.length);
        int pos = 0 + 6;
        int pos2 = pos + 1;
        data[pos] = (byte) sigLevel;
        int pos3 = pos2 + 1;
        data[pos2] = (byte) result;
        byte[] srcData2 = lastMccMnc.getBytes(StandardCharsets.UTF_8);
        System.arraycopy(srcData2, 0, data, pos3, srcData2.length > 6 ? 6 : srcData2.length);
        int pos4 = pos3 + 6;
        int i = pos4 + 1;
        data[pos4] = (byte) lastSigLevel;
        return buildCustomMessage(MessageConstants.TBOX_IVI_SELECT_MUA_NET_NTY, data);
    }

    public static TboxMessage buildAutoNetSetReq() {
        return buildCustomMessage(MessageConstants.IVI_TBOX_AUTO_NET_SET_REQ, null);
    }

    public static TboxMessage buildAutoNetSetNty(int result) {
        byte[] data = {(byte) result};
        return buildCustomMessage(MessageConstants.TBOX_IVI_AUTO_NET_SET_NTY, data);
    }

    public static TboxMessage buildSimDiagReq() {
        return buildCustomMessage(MessageConstants.IVI_TBOX_SIM_REQ, null);
    }

    public static TboxMessage buildSimDigNty(int result) {
        byte[] data = {(byte) result};
        return buildCustomMessage(MessageConstants.TBOX_IVI_SIM_NTY, data);
    }

    public static TboxMessage buildExportQxdmLogReq(int start, int logCategory) {
        byte[] data = {(byte) start, (byte) logCategory};
        return buildCustomMessage(MessageConstants.IVI_TBOX_EXPORT_QXDM_LOG_REQ, data);
    }

    public static TboxMessage buildExportQxdmLogNty(int result) {
        byte[] data = {(byte) result};
        return buildCustomMessage(MessageConstants.TBOX_IVI_EXPORT_QXDM_LOG_RSP, data);
    }

    public static TboxMessage buildApnArgSetReq(ApnArgs apnArgs) {
        return buildCustomMessage(MessageConstants.IVI_TBOX_APN_ARG_SET, apnArgs.toByteBuf());
    }

    public static TboxMessage buildApnArgReq() {
        return buildCustomMessage(MessageConstants.IVI_TBOX_APN_ARG_REQ, null);
    }

    public static TboxMessage buildSyncSimReq(int type) {
        return buildCustomMessage(MessageConstants.IVI_TBOX_SIM_TYPE_REQ, new byte[]{(byte) type});
    }

    public static TboxMessage buildNetworkFlowReq(int type, String iccid) {
        byte[] data = new byte[21];
        data[0] = (byte) type;
        byte[] srcData = iccid.getBytes();
        System.arraycopy(srcData, 0, data, 1, Math.min(srcData.length, 20));
        return buildCustomMessage(MessageConstants.IVI_TBOX_NET_FLOW_REQ, data);
    }

    public static TboxMessage build539Or540Message(boolean is539, int v2gMode, String json) {
        byte[] jsonData = json.getBytes(StandardCharsets.UTF_8);
        int len = jsonData.length;
        int totalLen = len + 3;
        ByteBuffer buffer = ByteBuffer.allocate(totalLen);
        buffer.put((byte) v2gMode);
        buffer.putShort((short) len);
        buffer.put(jsonData);
        byte[] array = buffer.array();
        return buildCustomMessage(is539 ? MessageConstants.IVI_TBOX_V2G_RES : MessageConstants.IVI_TBOX_V2G_540_REPORT, array);
    }

    public static TboxMessage buildApnStateQuery(int apn) {
        byte[] data = {(byte) apn};
        return buildCustomMessage(MessageConstants.IVI_TBOX_APN_STATE_REQ, data);
    }

    public static TboxMessage buildRealNameSetReq(int real) {
        byte[] data = {(byte) real};
        return buildCustomMessage(MessageConstants.IVI_TBOX_REAL_NAME_SET_REQ, data);
    }
}
