package com.ts.qnxapp.util;

import android.os.Bundle;
import android.os.Parcelable;
import com.autolink.ota.OtaProto;
import com.byd.ota.master.EcuInfo;
import com.ts.ota.otasdkmgr.DiagnosticAidlInfo;
import com.ts.ota.otasdkmgr.Info;
import com.ts.qnxapp.fdbusClientForAndroid.FdbusProtoBuilder;
import com.ts.qnxmanager.NetMap;
import ipc.fdbus.FdbusMessage;
import ipc.fdbus.FdbusMsgBuilder;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class OtaToBundle {
    private static final String APPOINTMENT_DAY = "appointmentDay";
    private static final String APPOINTMENT_HOUR = "appointmentHour";
    private static final String APPOINTMENT_MIN = "appointmentMin";
    private static final String APPOINTMENT_TIME_MODE = "appointmentTimeMode";
    private static final String APPOINTMENT_TIME_TYPE = "appointmentTimeType";
    private static final int APPOINTMENT_TIME_TYPE_DEFAULT = 1;
    private static final String CLEAR_ECU_DTC_LIST_REQ = "clearEcuDtcListReq";
    private static final String CLEAR_ECU_DTC_LIST_RSP = "clearEcuDtcListRsp";
    private static final String DIAG_REQ = "diagReq";
    private static final String DIAG_RSP = "diagRsp";
    private static final String DTC_INFO_TYPE = "readDTCInfoType";
    private static final String ECU_CONFIG_DID = "ecuConfigDId";
    private static final String ECU_CONFIG_INFO = "ecuConfigInfo";
    private static final String ECU_DIAL_DID = "ecuDialDid";
    private static final String ECU_DIAL_ID = "ecuDialId";
    private static final String ECU_DID_INFO = "ecuDidInfo";
    private static final String ECU_DTC_EXTDATA_NUMBER = "ecuDtcExtDataNumber";
    private static final String ECU_DTC_ID = "ecuDtcId";
    private static final String ECU_DTC_INFO = "ecuDtcInfo";
    private static final String ECU_DTC_LIST_REQ = "ecuDtcListReq";
    private static final String ECU_DTC_LIST_RSP = "ecuDtcListRsp";
    private static final String ECU_DTC_SNAPSHOT_NUMBER = "ecuDtcSnapshotNumber";
    private static final String ECU_HARD_DID = "ecuHardDid";
    private static final String ECU_HARD_VER = "ecuHardVer";
    private static final String ECU_ID = "ecuId";
    private static final String ECU_INFO = "ecuInfo";
    private static final String ECU_LIST_REQ = "ecuListReq";
    private static final String ECU_LIST_RSP = "ecuListRsp";
    private static final String ECU_REPROGRAM_STYLE = "ecuReprogramStyle";
    private static final String HEART_OTA_STATE = "heartOtaState";
    private static final String IGN_CYCLES_NUM = "ignCycleNum";
    private static final String IPB_REP_STATUS = "ipbRepStatus";
    private static final String IPB_TEST_STATUS = "ipbTestStatus";
    private static final String IVI_OBD_OPERATION_TYPE = "iviObdOperationType";
    private static final String IVI_OBD_STATUS = "iviObdStatus";
    private static final String MAP_OPEN_STATUS = "mapOpenStatus";
    private static final int MAX_MODULE_NUMBER = 100;
    private static final String MSG_REQ = "msgReq";
    private static final String MSG_RSP = "msgRsp";
    private static final String MSG_TYPE = "msgType";
    private static final String NETWORK_CONTROL_STATE = "networkControlState";
    private static final String NET_MAP_LIST = "netMapList";
    private static final String NULL_STR = "";
    private static final String OTA_STATE = "otaState";
    private static final String PARAMS = "params";
    private static final String PATH = "path";
    private static final String PROGRESS = "progress";
    private static final String RECORD_MASK = "recordMask";
    private static final String RESULT_NUMBER = "resultNumber";
    private static final String SLOT = "slot";
    private static final String SLOT_STATUS = "slotStatus";
    private static final String STATUS_MASK = "statusMask";
    private static final String SUB_TYPE = "subtype";
    private static final String SWITCH_SLOT = "switchSlot";
    private static final String TAG = "OtaToBundle";
    private static final String TEST = "test";
    private static final String UPDATE_FLAG = "updateFlag";
    private static final String WAKE_UP_STATUS = "wakeUpStatus";
    private static volatile OtaToBundle sInstance;

    public static OtaToBundle getInstance() {
        if (sInstance != null) {
            return sInstance;
        }
        synchronized (OtaToBundle.class) {
            if (sInstance == null) {
                sInstance = new OtaToBundle();
            }
        }
        return sInstance;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public Bundle messageOtaToBundle(FdbusMessage msg, int flag) {
        Bundle bundle = new Bundle();
        if (flag == 0) {
            int iCode = msg.code();
            if (iCode == 14) {
                try {
                    bundle.putInt(MSG_TYPE, 8);
                    bundle.putInt(SUB_TYPE, msg.code());
                    OtaProto.SetUpdateFlagRsp setUpdateFlagRsp = OtaProto.SetUpdateFlagRsp.parseFrom(msg.byteArray());
                    OtaProto.AppointmentTimeStatus result = setUpdateFlagRsp.getStatus();
                    int resultNumber = result.getNumber();
                    bundle.putInt(RESULT_NUMBER, resultNumber);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (iCode == 15) {
                try {
                    bundle.putInt(MSG_TYPE, 8);
                    bundle.putInt(SUB_TYPE, msg.code());
                    OtaProto.SetQnxOtaStateRsp setQnxOtaStateRsp = OtaProto.SetQnxOtaStateRsp.parseFrom(msg.byteArray());
                    OtaProto.AppointmentTimeStatus result2 = setQnxOtaStateRsp.getStatus();
                    int resultNumber2 = result2.getNumber();
                    bundle.putInt(RESULT_NUMBER, resultNumber2);
                } catch (Exception ex2) {
                    ex2.printStackTrace();
                }
            } else if (iCode != 23) {
                switch (iCode) {
                    case 0:
                    case 1:
                    case 2:
                    case 9:
                    case 10:
                    case 12:
                        try {
                            bundle.putInt(MSG_TYPE, 8);
                            bundle.putInt(SUB_TYPE, msg.code());
                            OtaProto.QnxResponse qnxResponse = OtaProto.QnxResponse.parseFrom(msg.byteArray());
                            OtaProto.Status qnxResponseRes = qnxResponse.getRes();
                            int resultNumber3 = qnxResponseRes.getNumber();
                            bundle.putInt(RESULT_NUMBER, resultNumber3);
                        } catch (Exception ex3) {
                            ex3.printStackTrace();
                        }
                        break;
                    case 3:
                    case 4:
                        try {
                            bundle.putInt(MSG_TYPE, 8);
                            bundle.putInt(SUB_TYPE, msg.code());
                            OtaProto.UpdateLogPath updateLogPath = OtaProto.UpdateLogPath.parseFrom(msg.byteArray());
                            bundle.putString(PATH, updateLogPath.getPath());
                            if (updateLogPath.hasParams()) {
                                bundle.putString(PARAMS, updateLogPath.getParams());
                            }
                        } catch (Exception ex4) {
                            ex4.printStackTrace();
                        }
                        break;
                    case 5:
                        try {
                            bundle.putInt(MSG_TYPE, 8);
                            bundle.putInt(SUB_TYPE, msg.code());
                            OtaProto.EcuUpdateLogPath ecuUpdateLogPath = OtaProto.EcuUpdateLogPath.parseFrom(msg.byteArray());
                            OtaProto.EcuInfo ecuInfo = ecuUpdateLogPath.getEcuInfo();
                            EcuInfo otaMasterEcuInfo = getOtaMasterEcuInfo(ecuInfo);
                            bundle.putParcelable(ECU_INFO, otaMasterEcuInfo);
                            bundle.putString(PATH, ecuUpdateLogPath.getPath());
                            if (ecuUpdateLogPath.hasParams()) {
                                bundle.putString(PARAMS, ecuUpdateLogPath.getParams());
                            }
                        } catch (Exception ex5) {
                            ex5.printStackTrace();
                        }
                        break;
                    case 6:
                    case 7:
                        try {
                            bundle.putInt(MSG_TYPE, 8);
                            bundle.putInt(SUB_TYPE, msg.code());
                            OtaProto.UpdateProgress updateProgress = OtaProto.UpdateProgress.parseFrom(msg.byteArray());
                            bundle.putInt(PROGRESS, updateProgress.getProgress());
                        } catch (Exception ex6) {
                            ex6.printStackTrace();
                        }
                        break;
                    case 8:
                        try {
                            bundle.putInt(MSG_TYPE, 8);
                            bundle.putInt(SUB_TYPE, msg.code());
                            OtaProto.EcuUpdateProgress ecuUpdateProgress = OtaProto.EcuUpdateProgress.parseFrom(msg.byteArray());
                            int ecuUpProgress = ecuUpdateProgress.getProgress();
                            bundle.putInt(PROGRESS, ecuUpProgress);
                            OtaProto.EcuInfo ecuInfo2 = ecuUpdateProgress.getEcuInfo();
                            EcuInfo otaMasterEcuInfo2 = getOtaMasterEcuInfo(ecuInfo2);
                            bundle.putParcelable(ECU_INFO, otaMasterEcuInfo2);
                        } catch (Exception ex7) {
                            ex7.printStackTrace();
                        }
                        break;
                    case 11:
                        try {
                            bundle.putInt(MSG_TYPE, 8);
                            bundle.putInt(SUB_TYPE, msg.code());
                            OtaProto.SlotStatusRes slotStatusRes = OtaProto.SlotStatusRes.parseFrom(msg.byteArray());
                            bundle.putInt(SLOT_STATUS, slotStatusRes.getSlotStatus().getNumber());
                        } catch (Exception ex8) {
                            ex8.printStackTrace();
                        }
                        break;
                }
            } else {
                try {
                    bundle.putInt(MSG_TYPE, 8);
                    bundle.putInt(SUB_TYPE, msg.code());
                    OtaProto.CommonNetworkControlMessagesRsp commonNetworkControlRsp = OtaProto.CommonNetworkControlMessagesRsp.parseFrom(msg.byteArray());
                    OtaProto.CommonNetworkControlMessagesRspStatus state = commonNetworkControlRsp.getState();
                    int resultNumber4 = state.getNumber();
                    bundle.putInt(RESULT_NUMBER, resultNumber4);
                } catch (Exception ex9) {
                    ex9.printStackTrace();
                }
            }
        } else if (flag == 2) {
            switch (msg.code()) {
                case 0:
                case 2:
                    try {
                        bundle.putInt(MSG_TYPE, 8);
                        bundle.putInt(SUB_TYPE, msg.code() + MAX_MODULE_NUMBER);
                        OtaProto.UpdateResult updateResult = OtaProto.UpdateResult.parseFrom(msg.byteArray());
                        OtaProto.ErrorCode result3 = updateResult.getResult();
                        int resultNumber5 = result3.getNumber();
                        bundle.putInt(RESULT_NUMBER, resultNumber5);
                    } catch (Exception ex10) {
                        ex10.printStackTrace();
                    }
                    break;
                case 1:
                case 3:
                    try {
                        bundle.putInt(MSG_TYPE, 8);
                        bundle.putInt(SUB_TYPE, msg.code() + MAX_MODULE_NUMBER);
                        OtaProto.UpdateProgress updateProgress2 = OtaProto.UpdateProgress.parseFrom(msg.byteArray());
                        bundle.putInt(PROGRESS, updateProgress2.getProgress());
                    } catch (Exception ex11) {
                        ex11.printStackTrace();
                    }
                    break;
                case 4:
                    try {
                        bundle.putInt(MSG_TYPE, 8);
                        bundle.putInt(SUB_TYPE, msg.code() + MAX_MODULE_NUMBER);
                        OtaProto.EcuUpdateResult ecuUpdateResult = OtaProto.EcuUpdateResult.parseFrom(msg.byteArray());
                        OtaProto.ErrorCode result4 = ecuUpdateResult.getResult();
                        int resultNumber6 = result4.getNumber();
                        bundle.putInt(RESULT_NUMBER, resultNumber6);
                        OtaProto.EcuInfo ecuInfo3 = ecuUpdateResult.getEcuInfo();
                        EcuInfo otaMasterEcuInfo3 = getOtaMasterEcuInfo(ecuInfo3);
                        bundle.putParcelable(ECU_INFO, otaMasterEcuInfo3);
                    } catch (Exception ex12) {
                        ex12.printStackTrace();
                    }
                    break;
                case 5:
                    try {
                        bundle.putInt(MSG_TYPE, 8);
                        bundle.putInt(SUB_TYPE, msg.code() + MAX_MODULE_NUMBER);
                        OtaProto.EcuUpdateProgress ecuUpdateProgress2 = OtaProto.EcuUpdateProgress.parseFrom(msg.byteArray());
                        int ecuUpProgress2 = ecuUpdateProgress2.getProgress();
                        bundle.putInt(PROGRESS, ecuUpProgress2);
                        OtaProto.EcuInfo ecuInfo4 = ecuUpdateProgress2.getEcuInfo();
                        EcuInfo otaMasterEcuInfo4 = getOtaMasterEcuInfo(ecuInfo4);
                        bundle.putParcelable(ECU_INFO, otaMasterEcuInfo4);
                    } catch (Exception ex13) {
                        ex13.printStackTrace();
                    }
                    break;
                case 6:
                    try {
                        bundle.putInt(MSG_TYPE, 8);
                        bundle.putInt(SUB_TYPE, msg.code() + MAX_MODULE_NUMBER);
                        OtaProto.EcuSwitchPartitionResult ecuSwitchPartition = OtaProto.EcuSwitchPartitionResult.parseFrom(msg.byteArray());
                        int slot = ecuSwitchPartition.getSlot();
                        bundle.putInt(SLOT, slot);
                        OtaProto.SwitchPartitionResult result5 = ecuSwitchPartition.getResult();
                        bundle.putInt(RESULT_NUMBER, result5.getNumber());
                        OtaProto.EcuInfo ecuSwitchEcuInfo = ecuSwitchPartition.getEcuInfo();
                        EcuInfo otaMasterEcuInfo5 = getOtaMasterEcuInfo(ecuSwitchEcuInfo);
                        bundle.putParcelable(ECU_INFO, otaMasterEcuInfo5);
                    } catch (Exception ex14) {
                        ex14.printStackTrace();
                    }
                    break;
                case 7:
                    try {
                        bundle.putInt(MSG_TYPE, 8);
                        bundle.putInt(SUB_TYPE, msg.code() + MAX_MODULE_NUMBER);
                        OtaProto.EcuDidInfoListRsp ecuDidInfoList = OtaProto.EcuDidInfoListRsp.parseFrom(msg.byteArray());
                        int ecuDidInfoListCount = ecuDidInfoList.getEcuDidInfoListCount();
                        ArrayList<? extends Parcelable> arrayList = new ArrayList<>(ecuDidInfoListCount);
                        for (int i = 0; i < ecuDidInfoListCount; i++) {
                            OtaProto.EcuDidInfoRsp ecuDidInfoRsp = ecuDidInfoList.getEcuDidInfoList(i);
                            com.ts.ota.otasdkmgr.EcuInfo ecuInfo5 = new com.ts.ota.otasdkmgr.EcuInfo();
                            ecuInfo5.setEcuId(ecuDidInfoRsp.getEcuId());
                            if (ecuDidInfoRsp.hasEcuHardDidInfo()) {
                                ecuInfo5.setEcuHardVer(ecuDidInfoRsp.getEcuHardDidInfo());
                            } else {
                                ecuInfo5.setEcuHardVer(NULL_STR);
                            }
                            int ecuDidInfoCount = ecuDidInfoRsp.getEcuDidInfoCount();
                            for (int ecuDidInfoIndex = 0; ecuDidInfoIndex < ecuDidInfoCount; ecuDidInfoIndex++) {
                                if (ecuDidInfoIndex == 0) {
                                    ecuInfo5.setEcuSoftVer(ecuDidInfoRsp.getEcuDidInfo(ecuDidInfoIndex));
                                } else if (ecuDidInfoIndex == 1) {
                                    ecuInfo5.setEcuSoftCode(ecuDidInfoRsp.getEcuDidInfo(ecuDidInfoIndex));
                                }
                            }
                            int ecuConfigInfoCount = ecuDidInfoRsp.getEcuConfigInfoCount();
                            for (int ecuConfigInfoIndex = 0; ecuConfigInfoIndex < ecuConfigInfoCount; ecuConfigInfoIndex++) {
                                if (ecuConfigInfoIndex == 0) {
                                    ecuInfo5.setEcuConfigVer(ecuDidInfoRsp.getEcuConfigInfo(ecuConfigInfoIndex));
                                } else if (ecuConfigInfoIndex == 1) {
                                    ecuInfo5.setEcuConfigCode(ecuDidInfoRsp.getEcuConfigInfo(ecuConfigInfoIndex));
                                }
                            }
                            arrayList.add(ecuInfo5);
                        }
                        bundle.putParcelableArrayList(ECU_LIST_RSP, arrayList);
                    } catch (Exception ex15) {
                        ex15.printStackTrace();
                    }
                    break;
                case 8:
                    try {
                        bundle.putInt(MSG_TYPE, 8);
                        bundle.putInt(SUB_TYPE, msg.code() + MAX_MODULE_NUMBER);
                        OtaProto.AppointmentTimeResult appointmentTimeRsp = OtaProto.AppointmentTimeResult.parseFrom(msg.byteArray());
                        OtaProto.AppointmentTimeType appointmentTimeType = appointmentTimeRsp.getType();
                        bundle.putInt(APPOINTMENT_TIME_TYPE, appointmentTimeType.getNumber());
                        if (appointmentTimeRsp.hasStatus()) {
                            OtaProto.AppointmentTimeStatus appointmentTimeStatus = appointmentTimeRsp.getStatus();
                            int resultNumber7 = appointmentTimeStatus.getNumber();
                            bundle.putInt(RESULT_NUMBER, resultNumber7);
                        }
                        if (appointmentTimeRsp.hasDay()) {
                            int day = appointmentTimeRsp.getDay();
                            bundle.putInt(APPOINTMENT_DAY, day);
                        }
                        if (appointmentTimeRsp.hasHour()) {
                            int hour = appointmentTimeRsp.getHour();
                            bundle.putInt(APPOINTMENT_HOUR, hour);
                        }
                        if (appointmentTimeRsp.hasMinute()) {
                            int minute = appointmentTimeRsp.getMinute();
                            bundle.putInt(APPOINTMENT_MIN, minute);
                        }
                        if (appointmentTimeRsp.hasMode()) {
                            OtaProto.AppointmentTimeMode appointmentTimeMode = appointmentTimeRsp.getMode();
                            bundle.putInt(APPOINTMENT_TIME_MODE, appointmentTimeMode.getNumber());
                        }
                    } catch (Exception ex16) {
                        ex16.printStackTrace();
                    }
                    break;
                case 11:
                    try {
                        bundle.putInt(MSG_TYPE, 8);
                        bundle.putInt(SUB_TYPE, msg.code() + MAX_MODULE_NUMBER);
                        OtaProto.EcuDTCInfoListRsp ecuDtcInfoListRsp = OtaProto.EcuDTCInfoListRsp.parseFrom(msg.byteArray());
                        int ecuDtcInfoListCount = ecuDtcInfoListRsp.getEcuDtcInfoListCount();
                        ArrayList<? extends Parcelable> arrayList2 = new ArrayList<>(ecuDtcInfoListCount);
                        for (int i2 = 0; i2 < ecuDtcInfoListCount; i2++) {
                            DiagnosticAidlInfo diagnosticAidlInfo = new DiagnosticAidlInfo();
                            OtaProto.EcuDTCInfoRsp ecuDtcInfoRsp = ecuDtcInfoListRsp.getEcuDtcInfoList(i2);
                            diagnosticAidlInfo.setEcuId(ecuDtcInfoRsp.getEcuId());
                            diagnosticAidlInfo.setEcuDiaId(ecuDtcInfoRsp.getEcuDialId());
                            diagnosticAidlInfo.setReadDtcInfoType(ecuDtcInfoRsp.getType().getNumber());
                            if (ecuDtcInfoRsp.hasEcuDtcSnapshotRecordNumber()) {
                                diagnosticAidlInfo.setSnapshotRecordNumber(ecuDtcInfoRsp.getEcuDtcSnapshotRecordNumber());
                            }
                            if (ecuDtcInfoRsp.hasEcuDtcExtDataRecordNumber()) {
                                diagnosticAidlInfo.setExtDataRecordNumber(ecuDtcInfoRsp.getEcuDtcExtDataRecordNumber());
                            }
                            if (ecuDtcInfoRsp.hasStatusMask()) {
                                diagnosticAidlInfo.setStatusMask(ecuDtcInfoRsp.getStatusMask());
                            }
                            if (ecuDtcInfoRsp.hasRecordMask()) {
                                diagnosticAidlInfo.setRecordMask(ecuDtcInfoRsp.getRecordMask());
                            }
                            if (ecuDtcInfoRsp.hasEcuDtcInfo()) {
                                diagnosticAidlInfo.setEcuDtcInfo(ecuDtcInfoRsp.getEcuDtcInfo());
                            }
                            arrayList2.add(diagnosticAidlInfo);
                        }
                        bundle.putParcelableArrayList(ECU_DTC_LIST_RSP, arrayList2);
                    } catch (Exception ex17) {
                        ex17.printStackTrace();
                    }
                    break;
                case 12:
                    try {
                        bundle.putInt(MSG_TYPE, 8);
                        bundle.putInt(SUB_TYPE, msg.code() + MAX_MODULE_NUMBER);
                        OtaProto.ClrEcuDTCInfoListRsp clrEcuDtcInfoListRsp = OtaProto.ClrEcuDTCInfoListRsp.parseFrom(msg.byteArray());
                        int clrEcuDtcInfoListCount = clrEcuDtcInfoListRsp.getEcuDtcInfoListCount();
                        ArrayList<? extends Parcelable> arrayList3 = new ArrayList<>(clrEcuDtcInfoListCount);
                        for (int i3 = 0; i3 < clrEcuDtcInfoListCount; i3++) {
                            DiagnosticAidlInfo clrDiagnosticAidlInfo = new DiagnosticAidlInfo();
                            OtaProto.ClrEcuDTCInfoRsp clrEcuDtcInfoRsp = clrEcuDtcInfoListRsp.getEcuDtcInfoList(i3);
                            clrDiagnosticAidlInfo.setEcuId(clrEcuDtcInfoRsp.getEcuId());
                            clrDiagnosticAidlInfo.setEcuDiaId(clrEcuDtcInfoRsp.getEcuDialId());
                            clrDiagnosticAidlInfo.setEcuDtcId(clrEcuDtcInfoRsp.getEcuDtcId());
                            clrDiagnosticAidlInfo.setEcuDtcCleanStatus(clrEcuDtcInfoRsp.getStatus().getNumber());
                            arrayList3.add(clrDiagnosticAidlInfo);
                        }
                        bundle.putParcelableArrayList(CLEAR_ECU_DTC_LIST_RSP, arrayList3);
                    } catch (Exception ex18) {
                        ex18.printStackTrace();
                    }
                    break;
                case 13:
                    try {
                        bundle.putInt(MSG_TYPE, 8);
                        bundle.putInt(SUB_TYPE, msg.code() + MAX_MODULE_NUMBER);
                        OtaProto.EcuUdsCommDiagCmdRsp ecuUdsCommDiagCmdRsp = OtaProto.EcuUdsCommDiagCmdRsp.parseFrom(msg.byteArray());
                        String ecuId = ecuUdsCommDiagCmdRsp.getEcuId();
                        String ecuDialId = ecuUdsCommDiagCmdRsp.getEcuDialId();
                        String diagRsp = ecuUdsCommDiagCmdRsp.getDiagRsp();
                        bundle.putString(ECU_ID, ecuId);
                        bundle.putString(ECU_DIAL_ID, ecuDialId);
                        bundle.putString(DIAG_RSP, diagRsp);
                    } catch (Exception ex19) {
                        ex19.printStackTrace();
                    }
                    break;
                case 14:
                    try {
                        bundle.putInt(MSG_TYPE, 8);
                        bundle.putInt(SUB_TYPE, msg.code() + MAX_MODULE_NUMBER);
                        OtaProto.Uac2OtaMasterCommProtocol uac2OtaMasterCommProtocolRsp = OtaProto.Uac2OtaMasterCommProtocol.parseFrom(msg.byteArray());
                        String msgRsp = uac2OtaMasterCommProtocolRsp.getMsg();
                        bundle.putString(MSG_RSP, msgRsp);
                    } catch (Exception ex20) {
                        ex20.printStackTrace();
                    }
                    break;
                case 15:
                    try {
                        bundle.putInt(MSG_TYPE, 8);
                        bundle.putInt(SUB_TYPE, msg.code() + MAX_MODULE_NUMBER);
                        OtaProto.IVIObdConfigureResult iviObdConfigureResultRsp = OtaProto.IVIObdConfigureResult.parseFrom(msg.byteArray());
                        int iviObdStatus = iviObdConfigureResultRsp.getStatus().getNumber();
                        int iviObdOperationType = iviObdConfigureResultRsp.getType().getNumber();
                        bundle.putInt(IVI_OBD_STATUS, iviObdStatus);
                        bundle.putInt(IVI_OBD_OPERATION_TYPE, iviObdOperationType);
                    } catch (Exception ex21) {
                        ex21.printStackTrace();
                    }
                    break;
                case 16:
                    try {
                        bundle.putInt(MSG_TYPE, 8);
                        bundle.putInt(SUB_TYPE, msg.code() + MAX_MODULE_NUMBER);
                        OtaProto.IPBENERGYSWITCHRESULT ipbEnergySwitchResult = OtaProto.IPBENERGYSWITCHRESULT.parseFrom(msg.byteArray());
                        int ipbRepStatus = ipbEnergySwitchResult.getStatus().getNumber();
                        bundle.putInt(IPB_REP_STATUS, ipbRepStatus);
                    } catch (Exception ex22) {
                        ex22.printStackTrace();
                    }
                    break;
            }
        }
        return bundle;
    }

    public Object getOtaMessage(int subType, String content, Bundle bundle) {
        ArrayList<NetMap> netMapArrayList;
        switch (subType) {
            case 0:
            case 1:
                try {
                    OtaProto.UpdateReq.Builder updateReqBuilder = OtaProto.UpdateReq.newBuilder();
                    if (bundle.containsKey(PATH)) {
                        updateReqBuilder.setPath(bundle.getString(PATH));
                    } else {
                        updateReqBuilder.setPath(TEST);
                    }
                    if (bundle.containsKey(PARAMS)) {
                        updateReqBuilder.setParams(bundle.getString(PARAMS));
                    } else {
                        updateReqBuilder.setParams(TEST);
                    }
                    OtaProto.UpdateReq req = updateReqBuilder.build();
                    FdbusMsgBuilder msg = new FdbusProtoBuilder(req);
                    return msg;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return null;
                }
            case 2:
                try {
                    OtaProto.EcuUpdateReq.Builder updateEcuReqBuilder = OtaProto.EcuUpdateReq.newBuilder();
                    OtaProto.EcuInfo.Builder ecuInfoBuilder = OtaProto.EcuInfo.newBuilder();
                    bundle.setClassLoader(getClass().getClassLoader());
                    if (bundle.containsKey(ECU_INFO)) {
                        EcuInfo ecuInfo = bundle.getParcelable(ECU_INFO);
                        if (ecuInfo.getEcuId() != null && ecuInfo.getEcuId().length() > 0) {
                            ecuInfoBuilder.setEcuId(ecuInfo.getEcuId());
                        }
                        if (ecuInfo.getEcuSoftCode() != null && ecuInfo.getEcuSoftCode().length() > 0) {
                            ecuInfoBuilder.setEcuSoftCode(ecuInfo.getEcuSoftCode());
                        }
                        if (ecuInfo.getEcuSoftVer() != null && ecuInfo.getEcuSoftVer().length() > 0) {
                            ecuInfoBuilder.setEcuSoftVer(ecuInfo.getEcuSoftVer());
                        }
                        if (ecuInfo.getEcuDiaId() != null && ecuInfo.getEcuDiaId().length() > 0) {
                            ecuInfoBuilder.setEcuDialId(ecuInfo.getEcuDiaId());
                        }
                        if (ecuInfo.getEcuDId() != null && ecuInfo.getEcuDId().length() > 0) {
                            ecuInfoBuilder.setEcuDialDid(ecuInfo.getEcuDId());
                        }
                        if (ecuInfo.getEcuConfigDid() != null && ecuInfo.getEcuConfigDid().length() > 0) {
                            ecuInfoBuilder.setEcuConfigDid(ecuInfo.getEcuConfigDid());
                        }
                        if (ecuInfo.getEcuUpdateStyle() != null && ecuInfo.getEcuUpdateStyle().length() > 0) {
                            ecuInfoBuilder.setEcuUpdateStyle(ecuInfo.getEcuUpdateStyle());
                        }
                        if (ecuInfo.getEcuReprogramType() != null && ecuInfo.getEcuReprogramType().length() > 0) {
                            ecuInfoBuilder.setEcuReprogramStyle(ecuInfo.getEcuReprogramType());
                        }
                        if (ecuInfo.getEcuReprogramWay() != null && ecuInfo.getEcuReprogramWay().length() > 0) {
                            ecuInfoBuilder.setEcuReprogramWay(ecuInfo.getEcuReprogramWay());
                        }
                        if (ecuInfo.getEcuSafetyAccessKey() != null && ecuInfo.getEcuSafetyAccessKey().length() > 0) {
                            ecuInfoBuilder.setEcuSafetyAccessKey(ecuInfo.getEcuSafetyAccessKey());
                        }
                        if (ecuInfo.getEcuPath() != null && ecuInfo.getEcuPath().length() > 0) {
                            ecuInfoBuilder.setEcuPath(ecuInfo.getEcuPath());
                        }
                    } else {
                        ecuInfoBuilder.setEcuId(TEST);
                        ecuInfoBuilder.setEcuSoftCode(TEST);
                        ecuInfoBuilder.setEcuSoftVer(TEST);
                        ecuInfoBuilder.setEcuDialId(TEST);
                        ecuInfoBuilder.setEcuDialDid(TEST);
                        ecuInfoBuilder.setEcuConfigDid(TEST);
                        ecuInfoBuilder.setEcuUpdateStyle(TEST);
                        ecuInfoBuilder.setEcuReprogramStyle(TEST);
                        ecuInfoBuilder.setEcuReprogramWay(TEST);
                        ecuInfoBuilder.setEcuSafetyAccessKey(TEST);
                        ecuInfoBuilder.setEcuPath(TEST);
                    }
                    updateEcuReqBuilder.setEcuInfo(ecuInfoBuilder);
                    if (bundle.containsKey(PATH)) {
                        updateEcuReqBuilder.setPath(bundle.getString(PATH));
                    } else {
                        updateEcuReqBuilder.setPath(TEST);
                    }
                    if (bundle.containsKey(PARAMS)) {
                        updateEcuReqBuilder.setParams(bundle.getString(PARAMS));
                    } else {
                        updateEcuReqBuilder.setParams(TEST);
                    }
                    OtaProto.EcuUpdateReq ecuReq = updateEcuReqBuilder.build();
                    FdbusMsgBuilder msg2 = new FdbusProtoBuilder(ecuReq);
                    return msg2;
                } catch (Exception ex2) {
                    ex2.printStackTrace();
                    return null;
                }
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 11:
                LogUtil.logE(TAG, "getOtaMessage: get OtaProto.MsgType value :" + subType);
                return null;
            case 9:
                try {
                    OtaProto.EcuInfo.Builder switchEcuInfoBuilder = OtaProto.EcuInfo.newBuilder();
                    bundle.setClassLoader(getClass().getClassLoader());
                    if (bundle.containsKey(ECU_INFO)) {
                        EcuInfo ecuInfoSwitch = bundle.getParcelable(ECU_INFO);
                        if (ecuInfoSwitch.getEcuId() != null && ecuInfoSwitch.getEcuId().length() > 0) {
                            switchEcuInfoBuilder.setEcuId(ecuInfoSwitch.getEcuId());
                        }
                        if (ecuInfoSwitch.getEcuSoftCode() != null && ecuInfoSwitch.getEcuSoftCode().length() > 0) {
                            switchEcuInfoBuilder.setEcuSoftCode(ecuInfoSwitch.getEcuSoftCode());
                        }
                        if (ecuInfoSwitch.getEcuSoftVer() != null && ecuInfoSwitch.getEcuSoftVer().length() > 0) {
                            switchEcuInfoBuilder.setEcuSoftVer(ecuInfoSwitch.getEcuSoftVer());
                        }
                        if (ecuInfoSwitch.getEcuDiaId() != null && ecuInfoSwitch.getEcuDiaId().length() > 0) {
                            switchEcuInfoBuilder.setEcuDialId(ecuInfoSwitch.getEcuDiaId());
                        }
                        if (ecuInfoSwitch.getEcuDId() != null && ecuInfoSwitch.getEcuDId().length() > 0) {
                            switchEcuInfoBuilder.setEcuDialDid(ecuInfoSwitch.getEcuDId());
                        }
                        if (ecuInfoSwitch.getEcuConfigDid() != null && ecuInfoSwitch.getEcuConfigDid().length() > 0) {
                            switchEcuInfoBuilder.setEcuConfigDid(ecuInfoSwitch.getEcuConfigDid());
                        }
                        if (ecuInfoSwitch.getEcuUpdateStyle() != null && ecuInfoSwitch.getEcuUpdateStyle().length() > 0) {
                            switchEcuInfoBuilder.setEcuUpdateStyle(ecuInfoSwitch.getEcuUpdateStyle());
                        }
                        if (ecuInfoSwitch.getEcuReprogramType() != null && ecuInfoSwitch.getEcuReprogramType().length() > 0) {
                            switchEcuInfoBuilder.setEcuReprogramStyle(ecuInfoSwitch.getEcuReprogramType());
                        }
                        if (ecuInfoSwitch.getEcuReprogramWay() != null && ecuInfoSwitch.getEcuReprogramWay().length() > 0) {
                            switchEcuInfoBuilder.setEcuReprogramWay(ecuInfoSwitch.getEcuReprogramWay());
                        }
                        if (ecuInfoSwitch.getEcuSafetyAccessKey() != null && ecuInfoSwitch.getEcuSafetyAccessKey().length() > 0) {
                            switchEcuInfoBuilder.setEcuSafetyAccessKey(ecuInfoSwitch.getEcuSafetyAccessKey());
                        }
                        if (ecuInfoSwitch.getEcuPath() != null && ecuInfoSwitch.getEcuPath().length() > 0) {
                            switchEcuInfoBuilder.setEcuPath(ecuInfoSwitch.getEcuPath());
                        }
                    } else {
                        switchEcuInfoBuilder.setEcuId(TEST);
                        switchEcuInfoBuilder.setEcuSoftCode(TEST);
                        switchEcuInfoBuilder.setEcuSoftVer(TEST);
                        switchEcuInfoBuilder.setEcuDialId(TEST);
                        switchEcuInfoBuilder.setEcuDialDid(TEST);
                        switchEcuInfoBuilder.setEcuConfigDid(TEST);
                        switchEcuInfoBuilder.setEcuUpdateStyle(TEST);
                        switchEcuInfoBuilder.setEcuReprogramStyle(TEST);
                        switchEcuInfoBuilder.setEcuReprogramWay(TEST);
                        switchEcuInfoBuilder.setEcuSafetyAccessKey(TEST);
                        switchEcuInfoBuilder.setEcuPath(TEST);
                    }
                    OtaProto.EcuInfo ecuSwitchInfo = switchEcuInfoBuilder.build();
                    FdbusMsgBuilder msg3 = new FdbusProtoBuilder(ecuSwitchInfo);
                    return msg3;
                } catch (Exception ex3) {
                    ex3.printStackTrace();
                    return null;
                }
            case 10:
                try {
                    bundle.setClassLoader(getClass().getClassLoader());
                    ArrayList<Info> ecuList = bundle.getParcelableArrayList(ECU_LIST_REQ);
                    OtaProto.EcuDidInfoListReq.Builder ecuDidListBuilder = OtaProto.EcuDidInfoListReq.newBuilder();
                    for (int i = 0; i < ecuList.size(); i++) {
                        Info info = ecuList.get(i);
                        OtaProto.EcuDidInfoReq.Builder ecuDidBuilder = OtaProto.EcuDidInfoReq.newBuilder();
                        ecuDidBuilder.setEcuId(info.getEcuId());
                        ecuDidBuilder.setEcuDialId(info.getEcuDiaId());
                        if (info.getEcuDId() != null) {
                            ecuDidBuilder.setEcuDialDid(info.getEcuDId());
                        } else {
                            ecuDidBuilder.setEcuDialDid(NULL_STR);
                        }
                        if (info.getEcuHardDid() != null) {
                            ecuDidBuilder.setEcuHardDid(info.getEcuHardDid());
                        } else {
                            ecuDidBuilder.setEcuHardDid(NULL_STR);
                        }
                        if (info.getEcuConfigDid() != null) {
                            ecuDidBuilder.setEcuConfigDid(info.getEcuConfigDid());
                        } else {
                            ecuDidBuilder.setEcuConfigDid(NULL_STR);
                        }
                        OtaProto.EcuDidInfoReq ecuDidInfoReq = ecuDidBuilder.build();
                        ecuDidListBuilder.addEcuDidList(ecuDidInfoReq);
                    }
                    OtaProto.EcuDidInfoListReq ecuDidInfoListReq = ecuDidListBuilder.build();
                    FdbusMsgBuilder msg4 = new FdbusProtoBuilder(ecuDidInfoListReq);
                    return msg4;
                } catch (Exception ex4) {
                    ex4.printStackTrace();
                    return null;
                }
            case 12:
                try {
                    OtaProto.SwitchSlotReq.Builder switchSlotBuilder = OtaProto.SwitchSlotReq.newBuilder();
                    if (bundle.containsKey(SWITCH_SLOT)) {
                        OtaProto.SwitchSlotStatus switchSlotStatus = OtaProto.SwitchSlotStatus.valueOf(bundle.getInt(SWITCH_SLOT));
                        switchSlotBuilder.setSwitchStatus(switchSlotStatus);
                    } else {
                        switchSlotBuilder.setSwitchStatus(OtaProto.SwitchSlotStatus.valueOf(0));
                    }
                    OtaProto.SwitchSlotReq switchSlotReq = switchSlotBuilder.build();
                    FdbusMsgBuilder msg5 = new FdbusProtoBuilder(switchSlotReq);
                    return msg5;
                } catch (Exception ex5) {
                    ex5.printStackTrace();
                    return null;
                }
            case 13:
                try {
                    OtaProto.AppointmentTimeReq.Builder setTimeBuilder = OtaProto.AppointmentTimeReq.newBuilder();
                    if (bundle.containsKey(APPOINTMENT_TIME_TYPE)) {
                        OtaProto.AppointmentTimeType appointmentTimeType = OtaProto.AppointmentTimeType.valueOf(bundle.getInt(APPOINTMENT_TIME_TYPE));
                        setTimeBuilder.setType(appointmentTimeType);
                    } else {
                        LogUtil.logI(TAG, "because of no containsKey appointmentTimeType so to use appointmentTimeType default");
                        OtaProto.AppointmentTimeType appointmentTimeType2 = OtaProto.AppointmentTimeType.valueOf(1);
                        setTimeBuilder.setType(appointmentTimeType2);
                    }
                    if (bundle.containsKey(APPOINTMENT_DAY)) {
                        setTimeBuilder.setDay(bundle.getInt(APPOINTMENT_DAY));
                    }
                    if (bundle.containsKey(APPOINTMENT_HOUR)) {
                        setTimeBuilder.setHour(bundle.getInt(APPOINTMENT_HOUR));
                    }
                    if (bundle.containsKey(APPOINTMENT_MIN)) {
                        setTimeBuilder.setMinute(bundle.getInt(APPOINTMENT_MIN));
                    }
                    if (bundle.containsKey(WAKE_UP_STATUS)) {
                        setTimeBuilder.setWakeup(bundle.getInt(WAKE_UP_STATUS));
                    }
                    if (bundle.containsKey(APPOINTMENT_TIME_MODE)) {
                        OtaProto.AppointmentTimeMode appointmentTimeMode = OtaProto.AppointmentTimeMode.valueOf(bundle.getInt(APPOINTMENT_TIME_MODE));
                        setTimeBuilder.setMode(appointmentTimeMode);
                    }
                    OtaProto.AppointmentTimeReq appointmentTimeReq = setTimeBuilder.build();
                    FdbusMsgBuilder msg6 = new FdbusProtoBuilder(appointmentTimeReq);
                    return msg6;
                } catch (Exception ex6) {
                    ex6.printStackTrace();
                    return null;
                }
            case 14:
                try {
                    OtaProto.SetUpdateFlagReq.Builder updateFlagBuilder = OtaProto.SetUpdateFlagReq.newBuilder();
                    if (bundle.containsKey(UPDATE_FLAG)) {
                        updateFlagBuilder.setUpdateFlag(bundle.getInt(UPDATE_FLAG));
                    }
                    OtaProto.SetUpdateFlagReq updateFlagReq = updateFlagBuilder.build();
                    FdbusMsgBuilder msg7 = new FdbusProtoBuilder(updateFlagReq);
                    return msg7;
                } catch (Exception ex7) {
                    ex7.printStackTrace();
                    return null;
                }
            case 15:
                try {
                    OtaProto.SetQnxOtaStateReq.Builder qnxOtaStateBuilder = OtaProto.SetQnxOtaStateReq.newBuilder();
                    if (bundle.containsKey(OTA_STATE)) {
                        OtaProto.SetQnxOtaState setQnxOtaState = OtaProto.SetQnxOtaState.valueOf(bundle.getInt(OTA_STATE) + 1);
                        qnxOtaStateBuilder.setState(setQnxOtaState);
                    }
                    OtaProto.SetQnxOtaStateReq qnxOtaStateReq = qnxOtaStateBuilder.build();
                    FdbusMsgBuilder msg8 = new FdbusProtoBuilder(qnxOtaStateReq);
                    return msg8;
                } catch (Exception ex8) {
                    ex8.printStackTrace();
                    return null;
                }
            case 16:
                try {
                    OtaProto.HeartBeatOtaState.Builder heartBeatOtaStateBuilder = OtaProto.HeartBeatOtaState.newBuilder();
                    if (bundle.containsKey(HEART_OTA_STATE)) {
                        OtaProto.SetQnxOtaState qnxOtaState = OtaProto.SetQnxOtaState.valueOf(bundle.getInt(HEART_OTA_STATE) + 1);
                        heartBeatOtaStateBuilder.setState(qnxOtaState);
                    }
                    OtaProto.HeartBeatOtaState heartBeatOtaStateReq = heartBeatOtaStateBuilder.build();
                    FdbusMsgBuilder msg9 = new FdbusProtoBuilder(heartBeatOtaStateReq);
                    return msg9;
                } catch (Exception ex9) {
                    ex9.printStackTrace();
                    return null;
                }
            case 17:
                try {
                    OtaProto.SetParams.Builder setParamsBuilder = OtaProto.SetParams.newBuilder();
                    if (bundle.containsKey(PARAMS)) {
                        setParamsBuilder.setParams(bundle.getString(PARAMS));
                    } else {
                        setParamsBuilder.setParams(TEST);
                    }
                    OtaProto.SetParams setParamsReq = setParamsBuilder.build();
                    FdbusMsgBuilder msg10 = new FdbusProtoBuilder(setParamsReq);
                    return msg10;
                } catch (Exception ex10) {
                    ex10.printStackTrace();
                    return null;
                }
            case 18:
                try {
                    bundle.setClassLoader(getClass().getClassLoader());
                    ArrayList<DiagnosticAidlInfo> dtcList = bundle.getParcelableArrayList(ECU_DTC_LIST_REQ);
                    OtaProto.EcuDTCInfoListReq.Builder ecuDtcListBuilder = OtaProto.EcuDTCInfoListReq.newBuilder();
                    for (int i2 = 0; i2 < dtcList.size(); i2++) {
                        DiagnosticAidlInfo dtcInfo = dtcList.get(i2);
                        OtaProto.EcuDTCInfoReq.Builder ecuDtcBuilder = OtaProto.EcuDTCInfoReq.newBuilder();
                        if (dtcInfo.getEcuId() != null) {
                            ecuDtcBuilder.setEcuId(dtcInfo.getEcuId());
                        } else {
                            ecuDtcBuilder.setEcuId(NULL_STR);
                        }
                        if (dtcInfo.getEcuDiaId() != null) {
                            ecuDtcBuilder.setEcuDialId(dtcInfo.getEcuDiaId());
                        } else {
                            ecuDtcBuilder.setEcuDialId(NULL_STR);
                        }
                        OtaProto.ReadDTCInfoType readDtcInfoType = OtaProto.ReadDTCInfoType.valueOf(dtcInfo.getReadDtcInfoType());
                        ecuDtcBuilder.setType(readDtcInfoType);
                        if (dtcInfo.getSnapshotRecordNumber() != null) {
                            ecuDtcBuilder.setEcuDtcSnapshotRecordNumber(dtcInfo.getSnapshotRecordNumber());
                        }
                        if (dtcInfo.getExtDataRecordNumber() != null) {
                            ecuDtcBuilder.setEcuDtcExtDataRecordNumber(dtcInfo.getExtDataRecordNumber());
                        }
                        if (dtcInfo.getStatusMask() != null) {
                            ecuDtcBuilder.setStatusMask(dtcInfo.getStatusMask());
                        }
                        if (dtcInfo.getRecordMask() != null) {
                            ecuDtcBuilder.setRecordMask(dtcInfo.getRecordMask());
                        }
                        OtaProto.EcuDTCInfoReq ecuDidInfoReq2 = ecuDtcBuilder.build();
                        ecuDtcListBuilder.addEcuDtcList(ecuDidInfoReq2);
                    }
                    OtaProto.EcuDTCInfoListReq ecuDtcInfoListReq = ecuDtcListBuilder.build();
                    FdbusMsgBuilder msg11 = new FdbusProtoBuilder(ecuDtcInfoListReq);
                    return msg11;
                } catch (Exception ex11) {
                    ex11.printStackTrace();
                    return null;
                }
            case 19:
                try {
                    bundle.setClassLoader(getClass().getClassLoader());
                    ArrayList<DiagnosticAidlInfo> clearDtcList = bundle.getParcelableArrayList(CLEAR_ECU_DTC_LIST_REQ);
                    OtaProto.ClrEcuDTCInfoListReq.Builder clrEcuDtcListBuilder = OtaProto.ClrEcuDTCInfoListReq.newBuilder();
                    for (int i3 = 0; i3 < clearDtcList.size(); i3++) {
                        DiagnosticAidlInfo clrDtcInfo = clearDtcList.get(i3);
                        OtaProto.ClrEcuDTCInfoReq.Builder clrEcuDtcBuilder = OtaProto.ClrEcuDTCInfoReq.newBuilder();
                        if (clrDtcInfo.getEcuId() != null) {
                            clrEcuDtcBuilder.setEcuId(clrDtcInfo.getEcuId());
                        } else {
                            clrEcuDtcBuilder.setEcuId(NULL_STR);
                        }
                        if (clrDtcInfo.getEcuDiaId() != null) {
                            clrEcuDtcBuilder.setEcuDialId(clrDtcInfo.getEcuDiaId());
                        } else {
                            clrEcuDtcBuilder.setEcuDialId(NULL_STR);
                        }
                        if (clrDtcInfo.getEcuDtcId() != null) {
                            clrEcuDtcBuilder.setEcuDtcId(clrDtcInfo.getEcuDtcId());
                        } else {
                            clrEcuDtcBuilder.setEcuDtcId(NULL_STR);
                        }
                        OtaProto.ClrEcuDTCInfoReq clrEcuDidInfoReq = clrEcuDtcBuilder.build();
                        clrEcuDtcListBuilder.addEcuDtcList(clrEcuDidInfoReq);
                    }
                    OtaProto.ClrEcuDTCInfoListReq ecuDtcInfoListReq2 = clrEcuDtcListBuilder.build();
                    FdbusMsgBuilder msg12 = new FdbusProtoBuilder(ecuDtcInfoListReq2);
                    return msg12;
                } catch (Exception ex12) {
                    ex12.printStackTrace();
                    return null;
                }
            case 20:
            default:
                return null;
            case 21:
                try {
                    OtaProto.EcuUdsCommDiagCmdReq.Builder ecuUdsCommDiagCmdReqBuilder = OtaProto.EcuUdsCommDiagCmdReq.newBuilder();
                    if (bundle.containsKey(ECU_ID)) {
                        ecuUdsCommDiagCmdReqBuilder.setEcuId(bundle.getString(ECU_ID));
                    } else {
                        ecuUdsCommDiagCmdReqBuilder.setEcuId(NULL_STR);
                    }
                    if (bundle.containsKey(ECU_DIAL_ID)) {
                        ecuUdsCommDiagCmdReqBuilder.setEcuDialId(bundle.getString(ECU_DIAL_ID));
                    } else {
                        ecuUdsCommDiagCmdReqBuilder.setEcuId(NULL_STR);
                    }
                    if (bundle.containsKey(DIAG_REQ)) {
                        ecuUdsCommDiagCmdReqBuilder.setDiagReq(bundle.getString(DIAG_REQ));
                    } else {
                        ecuUdsCommDiagCmdReqBuilder.setDiagReq(NULL_STR);
                    }
                    OtaProto.EcuUdsCommDiagCmdReq ecuUdsCommDiagCmdReq = ecuUdsCommDiagCmdReqBuilder.build();
                    FdbusMsgBuilder msg13 = new FdbusProtoBuilder(ecuUdsCommDiagCmdReq);
                    return msg13;
                } catch (Exception ex13) {
                    ex13.printStackTrace();
                    return null;
                }
            case 22:
                try {
                    OtaProto.OtaMaster2UacCommProtocol.Builder otaMaster2UacCommProtocolBuilder = OtaProto.OtaMaster2UacCommProtocol.newBuilder();
                    if (bundle.containsKey(MSG_REQ)) {
                        otaMaster2UacCommProtocolBuilder.setMsg(bundle.getString(MSG_REQ));
                    } else {
                        otaMaster2UacCommProtocolBuilder.setMsg(NULL_STR);
                    }
                    OtaProto.OtaMaster2UacCommProtocol otaMaster2UacCommProtocolReq = otaMaster2UacCommProtocolBuilder.build();
                    FdbusMsgBuilder msg14 = new FdbusProtoBuilder(otaMaster2UacCommProtocolReq);
                    return msg14;
                } catch (Exception ex14) {
                    ex14.printStackTrace();
                    return null;
                }
            case 23:
                try {
                    OtaProto.CommonNetworkControlMessages.Builder commonNetworkControlBuilder = OtaProto.CommonNetworkControlMessages.newBuilder();
                    if (bundle.containsKey(NETWORK_CONTROL_STATE)) {
                        OtaProto.SetCommonNetworkControlState networkControlState = OtaProto.SetCommonNetworkControlState.valueOf(bundle.getInt(NETWORK_CONTROL_STATE));
                        commonNetworkControlBuilder.setState(networkControlState);
                    }
                    OtaProto.CommonNetworkControlMessages commonNetworkControlReq = commonNetworkControlBuilder.build();
                    FdbusMsgBuilder msg15 = new FdbusProtoBuilder(commonNetworkControlReq);
                    return msg15;
                } catch (Exception ex15) {
                    ex15.printStackTrace();
                    return null;
                }
            case 24:
                try {
                    bundle.setClassLoader(getClass().getClassLoader());
                    OtaProto.IVIObdConfigureCmd.Builder iviObdConfigureCmdBuilder = OtaProto.IVIObdConfigureCmd.newBuilder();
                    if (bundle.containsKey(IVI_OBD_OPERATION_TYPE)) {
                        OtaProto.IVIObdOperationType iviObdOperationType = OtaProto.IVIObdOperationType.valueOf(bundle.getInt(IVI_OBD_OPERATION_TYPE));
                        iviObdConfigureCmdBuilder.setType(iviObdOperationType);
                    }
                    if (bundle.containsKey(IGN_CYCLES_NUM)) {
                        iviObdConfigureCmdBuilder.setNumOfIgnCycles(bundle.getInt(IGN_CYCLES_NUM));
                    }
                    if (bundle.containsKey(MAP_OPEN_STATUS)) {
                        iviObdConfigureCmdBuilder.setMappingIsopen(bundle.getBoolean(MAP_OPEN_STATUS));
                    }
                    if (bundle.containsKey(NET_MAP_LIST) && (netMapArrayList = bundle.getParcelableArrayList(NET_MAP_LIST)) != null && !netMapArrayList.isEmpty()) {
                        for (int i4 = 0; i4 < netMapArrayList.size(); i4++) {
                            NetMap netMap = netMapArrayList.get(i4);
                            int netSeg = netMap.getNetSeg();
                            ArrayList<Integer> idArrayList = (ArrayList) netMap.getIdArrayList();
                            OtaProto.IVIObdConfigureCmd.NetMapping.Builder netMappingBuilder = OtaProto.IVIObdConfigureCmd.NetMapping.newBuilder();
                            netMappingBuilder.setNetseg(netSeg);
                            if (idArrayList != null && !idArrayList.isEmpty()) {
                                for (int j = 0; j < idArrayList.size(); j++) {
                                    netMappingBuilder.addIds(idArrayList.get(j).intValue());
                                }
                            }
                            OtaProto.IVIObdConfigureCmd.NetMapping netMappingReq = netMappingBuilder.build();
                            iviObdConfigureCmdBuilder.addMaps(i4, netMappingReq);
                        }
                    }
                    OtaProto.IVIObdConfigureCmd iviObdConfigureCmdBuilderReq = iviObdConfigureCmdBuilder.build();
                    FdbusMsgBuilder msg16 = new FdbusProtoBuilder(iviObdConfigureCmdBuilderReq);
                    return msg16;
                } catch (Exception ex16) {
                    ex16.printStackTrace();
                    return null;
                }
            case 25:
                try {
                    OtaProto.IPBTESTCONFIGURECMD.Builder ipbTestConfigCmdBuilder = OtaProto.IPBTESTCONFIGURECMD.newBuilder();
                    if (bundle.containsKey(IPB_TEST_STATUS)) {
                        OtaProto.IPBtestStatus ipbTestStatus = OtaProto.IPBtestStatus.valueOf(bundle.getInt(IPB_TEST_STATUS));
                        ipbTestConfigCmdBuilder.setStatus(ipbTestStatus);
                    }
                    OtaProto.IPBTESTCONFIGURECMD ipbTestConfigCmdReq = ipbTestConfigCmdBuilder.build();
                    FdbusMsgBuilder msg17 = new FdbusProtoBuilder(ipbTestConfigCmdReq);
                    return msg17;
                } catch (Exception ex17) {
                    ex17.printStackTrace();
                    return null;
                }
        }
    }

    private EcuInfo getOtaMasterEcuInfo(OtaProto.EcuInfo ecuInfo) {
        EcuInfo ecuInfoParcelable = new EcuInfo();
        if (ecuInfo.hasEcuId()) {
            ecuInfoParcelable.setEcuId(ecuInfo.getEcuId());
        }
        if (ecuInfo.hasEcuSoftCode()) {
            ecuInfoParcelable.setEcuSoftCode(ecuInfo.getEcuSoftCode());
        }
        if (ecuInfo.hasEcuSoftVer()) {
            ecuInfoParcelable.setEcuSoftVer(ecuInfo.getEcuSoftVer());
        }
        if (ecuInfo.hasEcuDialId()) {
            ecuInfoParcelable.setEcuDiaId(ecuInfo.getEcuDialId());
        }
        if (ecuInfo.hasEcuDialDid()) {
            ecuInfoParcelable.setEcuDId(ecuInfo.getEcuDialDid());
        }
        if (ecuInfo.hasEcuConfigDid()) {
            ecuInfoParcelable.setEcuConfigDid(ecuInfo.getEcuConfigDid());
        }
        if (ecuInfo.hasEcuUpdateStyle()) {
            ecuInfoParcelable.setEcuUpdateStyle(ecuInfo.getEcuUpdateStyle());
        }
        if (ecuInfo.hasEcuReprogramStyle()) {
            ecuInfoParcelable.setEcuReprogramType(ecuInfo.getEcuReprogramStyle());
        }
        if (ecuInfo.hasEcuReprogramWay()) {
            ecuInfoParcelable.setEcuReprogramWay(ecuInfo.getEcuReprogramWay());
        }
        if (ecuInfo.hasEcuSafetyAccessKey()) {
            ecuInfoParcelable.setEcuSafetyAccessKey(ecuInfo.getEcuSafetyAccessKey());
        }
        if (ecuInfo.hasEcuPath()) {
            ecuInfoParcelable.setEcuPath(ecuInfo.getEcuPath());
        }
        return ecuInfoParcelable;
    }
}
