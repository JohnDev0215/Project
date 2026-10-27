package android.hardware.bydauto.digital.tsimpl;

import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.BYDAutoFeatureIds;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.digital.IDigitalKeyLisenter;
import android.util.Log;
import android.util.SparseArray;
import com.ts.lib.caradapter.assist.CarAssistPacket;
import com.ts.lib.caradapter.assist.OnAssistChangedListener;
import com.ts.ota.otasdkmgr.DigitalKeyBeanSdk;
import com.ts.ota.otasdkmgr.IAbsBYDTBoxListener;
import com.ts.ota.otasdkmgr.OtaSdkManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class DigitalKeyManagerImpl extends AbsTsMyCar {
    private static final int S_FOR_TEST_VALUE = 0;
    private static final String TAG = "DigitalKeyManagerImpl";
    private static volatile DigitalKeyManagerImpl sInstance;
    private Context mContext;
    private int[] mFeatureIds;
    private IDigitalKeyLisenter mIDigitalKeyLisenter;
    private OtaSdkManager mOtaSdkManager;
    private TsManagerImpl mTsManagerImpl;
    private final CopyOnWriteArrayList<IDigitalKeyLisenter> mDigitalKeyListeners = new CopyOnWriteArrayList<>();
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();
    private final List<IDigitalKeyTboxListener> mTboxListeners = new ArrayList();
    private int mForTest = 1;
    IAbsBYDTBoxListener.Stub bydTboxListener = new IAbsBYDTBoxListener.Stub() { // from class: android.hardware.bydauto.digital.tsimpl.DigitalKeyManagerImpl.2
        public void onKeyListResponse(int totalNo, List<DigitalKeyBeanSdk> resp) {
            Log.w(DigitalKeyManagerImpl.TAG, "onKeyListResponse: resp is " + resp);
            List<DigitalKeyBean> digitalKeyBeanList = new ArrayList<>();
            for (DigitalKeyBeanSdk digitalKeyBeanSdk : resp) {
                DigitalKeyBean digitalKeyBean = new DigitalKeyBean();
                digitalKeyBean.init(digitalKeyBeanSdk.generateForApi());
                digitalKeyBeanList.add(digitalKeyBean);
            }
            for (IDigitalKeyTboxListener listener : DigitalKeyManagerImpl.this.mTboxListeners) {
                listener.onKeyListResponse(totalNo, digitalKeyBeanList);
            }
        }

        public void onDeleteAllNfcKeyResult(int type, int result) {
            Log.w(DigitalKeyManagerImpl.TAG, "onDeleteAllNfcKeyResult: type->" + type + " result->" + result);
            for (IDigitalKeyTboxListener listener : DigitalKeyManagerImpl.this.mTboxListeners) {
                listener.onDeleteAllNfcKeyResult(type, result);
            }
        }

        public void onDeleteNfcKeyByKeyNoResult(String keyNo, int result) {
            Log.w(DigitalKeyManagerImpl.TAG, "onDeleteNfcKeyByKeyNoResult: keyNo->" + keyNo + " result->" + result);
            for (IDigitalKeyTboxListener listener : DigitalKeyManagerImpl.this.mTboxListeners) {
                listener.onDeleteNfcKeyByKeyNoResult(keyNo, result);
            }
        }

        public void onDeleteOwnIphoneKeyResult(String keyNo, int result) {
            Log.w(DigitalKeyManagerImpl.TAG, "onDeleteOwnIphoneKeyResult: keyNo->" + keyNo + " result->" + result);
            for (IDigitalKeyTboxListener listener : DigitalKeyManagerImpl.this.mTboxListeners) {
                listener.onDeleteOwnIphoneKeyResult(keyNo, result);
            }
        }

        public void onKeyListPageRefresh() {
            Log.w(DigitalKeyManagerImpl.TAG, "onKeyListPageRefresh");
            for (IDigitalKeyTboxListener listener : DigitalKeyManagerImpl.this.mTboxListeners) {
                listener.onKeyListPageRefresh();
            }
        }

        public void onNfcOperationCommand(int cmd) {
            Log.w(DigitalKeyManagerImpl.TAG, "onNfcOperationCommand: cmd->" + cmd);
            for (IDigitalKeyLisenter listener : DigitalKeyManagerImpl.this.mDigitalKeyListeners) {
                listener.onNfcOperationCommand(cmd);
            }
        }

        public void onMobileNetworkStateChanged(int state) {
        }

        public void onAppActivateResult(int result, int appTag) {
        }

        public void onDeleteNfcKeyResult(int op, int result) {
        }
    };

    DigitalKeyManagerImpl(Context con) {
        this.mContext = con;
        this.mTsManagerImpl = TsManagerImpl.getInstance(con);
        this.mOtaSdkManager = OtaSdkManager.getInstance(con);
        this.mTsManagerImpl.setOnAssistChangedListener(new OnAssistChangedListener() { // from class: android.hardware.bydauto.digital.tsimpl.DigitalKeyManagerImpl.1
            public void onAssistChanged(int id, int areaId, CarAssistPacket packet) {
                Log.v(DigitalKeyManagerImpl.TAG, DigitalKeyManagerImpl.TAG + packet.toString());
                if (!DigitalKeyManagerImpl.this.mDigitalKeyListeners.isEmpty()) {
                    try {
                        DigitalKeyManagerImpl.this.assistCallback(id, areaId, packet);
                    } catch (Exception e) {
                        Log.e(DigitalKeyManagerImpl.TAG, "assistCallback is error");
                    }
                }
            }

            public void onBinderStateChanged(boolean binderState) {
                Log.d(DigitalKeyManagerImpl.TAG, "onBinderStateChanged is " + binderState);
            }
        });
        this.mOtaSdkManager.registerTboxListener(this.bydTboxListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void assistCallback(int id, int areaId, CarAssistPacket packet) {
        Log.v(TAG, "AssistCallback propertyId:" + id);
        switch (id) {
            case 557846277:
                int startForNoKeyR = packet.getStartForNoKeyR();
                for (IDigitalKeyLisenter listener : this.mDigitalKeyListeners) {
                    listener.onAllowStartTimeAfterUnlockChange(startForNoKeyR);
                }
                break;
            case 557846293:
                int responseFeedbackInformR = packet.getResponseFeedbackInformR();
                for (IDigitalKeyLisenter listener2 : this.mDigitalKeyListeners) {
                    listener2.onResponseFeedbackInform(responseFeedbackInformR);
                }
                break;
            case 557846297:
                int padOfflineOperationRespR = packet.getPadOfflineOperationRespR();
                for (IDigitalKeyLisenter listener3 : this.mDigitalKeyListeners) {
                    listener3.onPadOfflineOperationResp(padOfflineOperationRespR);
                }
                break;
            case 557846309:
                for (IDigitalKeyLisenter listener4 : this.mDigitalKeyListeners) {
                    listener4.onNfcKeyUnlockResultChange(packet.getNfcKeyUnlockResultR());
                }
                caseFeatureIds(BYDAutoFeatureIds.BODYWORK_NFC_KEY_UNLOCK_EXECUTE_RESULT, Integer.valueOf(packet.getNfcKeyUnlockResultR()));
                break;
            default:
                switch (id) {
                    case 557846280:
                        int queryOwnerKeyResultSingle = packet.getQueryOwnerKeyResultSingle();
                        for (IDigitalKeyLisenter listener5 : this.mDigitalKeyListeners) {
                            listener5.onOwnerKeyIdentifierResult(queryOwnerKeyResultSingle, 0);
                        }
                        caseFeatureIds(3912, Integer.valueOf(packet.getQueryOwnerKeyResultSingle()));
                        break;
                    case 557846281:
                        int queryOwnerKeyResultDouble = packet.getQueryOwnerKeyResultDouble();
                        for (IDigitalKeyLisenter listener6 : this.mDigitalKeyListeners) {
                            listener6.onOwnerKeyIdentifierResult(queryOwnerKeyResultDouble, 1);
                        }
                        caseFeatureIds(4168, Integer.valueOf(packet.getQueryOwnerKeyResultDouble()));
                        break;
                    case 557846282:
                        int findKeyResultSingle = packet.getFindKeyResultSingle();
                        for (IDigitalKeyLisenter listener7 : this.mDigitalKeyListeners) {
                            listener7.onKeyFindingResults(findKeyResultSingle, 0);
                        }
                        caseFeatureIds(4171, Integer.valueOf(packet.getFindKeyResultSingle()));
                        break;
                    case 557846283:
                        int findKeyResultDouble = packet.getFindKeyResultDouble();
                        for (IDigitalKeyLisenter listener8 : this.mDigitalKeyListeners) {
                            listener8.onKeyFindingResults(findKeyResultDouble, 1);
                        }
                        caseFeatureIds(4086, Integer.valueOf(packet.getFindKeyResultDouble()));
                        break;
                    case 557846284:
                        int processCallBackSingle = packet.getPairingProcessCallBackSingle();
                        for (IDigitalKeyLisenter listener9 : this.mDigitalKeyListeners) {
                            listener9.onPairingProcessCallBack(processCallBackSingle, 0);
                        }
                        caseFeatureIds(4104, Integer.valueOf(packet.getPairingProcessCallBackSingle()));
                        break;
                    case 557846285:
                        int processCallBackDouble = packet.getPairingProcessCallBackDouble();
                        for (IDigitalKeyLisenter listener10 : this.mDigitalKeyListeners) {
                            listener10.onPairingProcessCallBack(processCallBackDouble, 1);
                        }
                        caseFeatureIds(4106, Integer.valueOf(packet.getPairingProcessCallBackDouble()));
                        break;
                    case 557846286:
                        int pairingResultSingle = packet.getPairingResultSingle();
                        for (IDigitalKeyLisenter listener11 : this.mDigitalKeyListeners) {
                            listener11.onOfflineOperateResult(pairingResultSingle, 0);
                        }
                        caseFeatureIds(4107, Integer.valueOf(packet.getPairingResultSingle()));
                        break;
                    case 557846287:
                        int pairingResultDouble = packet.getPairingResultDouble();
                        for (IDigitalKeyLisenter listener12 : this.mDigitalKeyListeners) {
                            listener12.onOfflineOperateResult(pairingResultDouble, 1);
                        }
                        caseFeatureIds(4105, Integer.valueOf(packet.getPairingResultDouble()));
                        break;
                    case 557846288:
                        int offlineOperatPairingResult = packet.getOfflineOperatPairingResult();
                        for (IDigitalKeyLisenter listener13 : this.mDigitalKeyListeners) {
                            listener13.onOfflineOperateCmd(offlineOperatPairingResult);
                        }
                        break;
                    default:
                        switch (id) {
                            case 557846303:
                                int bleOperateCommandR = packet.getBleOperateCommandR();
                                for (IDigitalKeyLisenter listener14 : this.mDigitalKeyListeners) {
                                    listener14.onBleOperateCommand(bleOperateCommandR);
                                }
                                break;
                            case 557846304:
                                int bleOperateResultR = packet.getBleOperateResultR();
                                for (IDigitalKeyLisenter listener15 : this.mDigitalKeyListeners) {
                                    listener15.onBleOperateResult(bleOperateResultR);
                                }
                                break;
                            default:
                                switch (id) {
                                    case 557846313:
                                        for (IDigitalKeyLisenter listener16 : this.mDigitalKeyListeners) {
                                            listener16.onOpcodeStartFunctionChange(packet.getOpcodeStartFunctionR());
                                        }
                                        caseFeatureIds(BYDAutoFeatureIds.SETTING_UNLOCK_DRIVING_PASSWORD_POPUP_TYPE, Integer.valueOf(packet.getOpcodeStartFunctionR()));
                                        break;
                                    case 557846314:
                                        for (IDigitalKeyLisenter listener17 : this.mDigitalKeyListeners) {
                                            listener17.onPadExitPinCodeProcessChange(packet.getPadExitPinCodeProcessR());
                                        }
                                        caseFeatureIds(BYDAutoFeatureIds.SETTING_PAD_EXIT_PIN_CODE_PROCESS, Integer.valueOf(packet.getPadExitPinCodeProcessR()));
                                        break;
                                    default:
                                        switch (id) {
                                            case 557846316:
                                                for (IDigitalKeyLisenter listener18 : this.mDigitalKeyListeners) {
                                                    listener18.onOpcodeDataLengthChange(packet.getOpcodeDataLengthR());
                                                }
                                                caseFeatureIds(BYDAutoFeatureIds.SETTING_OPERATION_CODE_DATA_LENGTH, Integer.valueOf(packet.getOpcodeDataLengthR()));
                                                break;
                                            case 557846317:
                                                for (IDigitalKeyLisenter listener19 : this.mDigitalKeyListeners) {
                                                    listener19.onOpcodeChecksumValueChange(packet.getOpcodeChecksumValueR());
                                                }
                                                caseFeatureIds(BYDAutoFeatureIds.SETTING_OPERATION_CODE_CHECKSUM, Integer.valueOf(packet.getOpcodeChecksumValueR()));
                                                break;
                                            case 557846318:
                                                for (IDigitalKeyLisenter listener20 : this.mDigitalKeyListeners) {
                                                    listener20.onOpcodeCommandChange(packet.getOpcodeCommandR());
                                                }
                                                caseFeatureIds(BYDAutoFeatureIds.SETTING_OPERATION_CODE_COMMAND, Integer.valueOf(packet.getOpcodeCommandR()));
                                                break;
                                            case 557846319:
                                                for (IDigitalKeyLisenter listener21 : this.mDigitalKeyListeners) {
                                                    listener21.onResponseResultOfOpcodeChange(packet.getResponseResultOfOpcodeR());
                                                }
                                                caseFeatureIds(BYDAutoFeatureIds.SETTING_OPERATION_CODE_RESPONSE_RESULT, Integer.valueOf(packet.getResponseResultOfOpcodeR()));
                                                break;
                                            case DigitalHalId.ID_OPCODE_DATA_VALUE_R /* 557846320 */:
                                                for (IDigitalKeyLisenter listener22 : this.mDigitalKeyListeners) {
                                                    listener22.onOpcodeDataValueChange(packet.getOpcodeDataValueR());
                                                }
                                                caseFeatureIds(BYDAutoFeatureIds.SETTING_OPERATION_CODE_DATA_VALUE, packet.getOpcodeDataValueR());
                                                break;
                                        }
                                        break;
                                }
                                break;
                        }
                        break;
                }
                break;
        }
    }

    public static DigitalKeyManagerImpl getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (DigitalKeyManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new DigitalKeyManagerImpl(context);
                }
            }
        }
        return sInstance;
    }

    public int getNFCConfigurationInformation() {
        Log.v(TAG, "getNFCConfigurationInformation is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getNFCConfigurationInformation();
    }

    public boolean setQueryOwnersKey(int config, int op) {
        Log.v(TAG, "setQueryOwnersKeyInOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return false;
        }
        return this.mOtaSdkManager.setQueryOwnersKey(config, op);
    }

    public boolean setQueryOwnersKeyV2(byte[] data) {
        Log.v(TAG, "setQueryOwnersKeyV2 is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return false;
        }
        return this.mOtaSdkManager.setQueryOwnersKeyV2(data);
    }

    public void setQueryOwnersKeyOut(int op) {
        Log.v(TAG, "setQueryOwnersKeyOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().setQueryOwnersKeyOut(op);
        }
    }

    public void setQueryOwnersKeyInOut(int op) {
        Log.v(TAG, "setQueryOwnersKeyInOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().setQueryOwnersKeyInOut(op);
        }
    }

    public boolean setRegisterKeyStartPairing(int config, int op) {
        Log.v(TAG, "setRegisterKeyStartPairingOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return false;
        }
        return this.mOtaSdkManager.setRegisterKeyStartPairing(config, op);
    }

    public boolean setRegisterKeyStartPairingV2(byte[] data) {
        Log.v(TAG, "setRegisterKeyStartPairingV2 is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return false;
        }
        return this.mOtaSdkManager.setRegisterKeyStartPairingV2(data);
    }

    public void setRegisterKeyStartPairingOut(int op) {
        Log.v(TAG, "setRegisterKeyStartPairingOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().setRegisterKeyStartPairingOut(op);
        }
    }

    public void setRegisterKeyStartPairingInOut(int op) {
        Log.v(TAG, "setRegisterKeyStartPairingInOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().setRegisterKeyStartPairingInOut(op);
        }
    }

    public int getIkConfigKeylessTime() {
        Log.v(TAG, "getIkConfigKeylessTime is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getIkConfigKeylessTime();
    }

    public boolean setAllowStartTimeAfterUnlock(int config, int value) {
        Log.v(TAG, "setAllowStartTimeAfterUnlockOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return false;
        }
        return this.mOtaSdkManager.setAllowStartTimeAfterUnlock(config, value);
    }

    public boolean setAllowStartTimeAfterUnlockV2(byte[] data) {
        Log.v(TAG, "setAllowStartTimeAfterUnlockV2 is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return false;
        }
        return this.mOtaSdkManager.setAllowStartTimeAfterUnlockV2(data);
    }

    public boolean deleteNfcFindKeyV2(byte[] data) {
        Log.v(TAG, "deleteNfcFindKeyV2 is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return false;
        }
        return this.mOtaSdkManager.deleteNfcFindKeyV2(data);
    }

    public boolean offOnlineRemoveKey97V2(byte[] data) {
        Log.v(TAG, "offOnlineRemoveKey97V2 is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return false;
        }
        return this.mOtaSdkManager.offOnlineRemoveKey97V2(data);
    }

    public boolean offOnlineRemoveKey98V2(byte[] data) {
        Log.v(TAG, "offOnlineRemoveKey98V2 is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return false;
        }
        return this.mOtaSdkManager.offOnlineRemoveKey98V2(data);
    }

    public void setAllowStartTimeAfterUnlockOut(int value) {
        Log.v(TAG, "setAllowStartTimeAfterUnlockOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().setAllowStartTimeAfterUnlockOut(value);
        }
    }

    public void setAllowStartTimeAfterUnlockInOut(int value) {
        Log.v(TAG, "setAllowStartTimeAfterUnlockInOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().setAllowStartTimeAfterUnlockInOut(value);
        }
    }

    public void queryKeyListReq(int carAccount, int entityKeyTag, int keyType, String userId, String keyId) {
        Log.v(TAG, "queryKeyListReq is execute");
        OtaSdkManager otaSdkManager = this.mOtaSdkManager;
        if (otaSdkManager == null) {
            Log.e(TAG, "mOtaSdkManager is null");
        } else {
            otaSdkManager.queryKeyListReq(carAccount, entityKeyTag, keyType, userId, keyId);
        }
    }

    public void queryKeyListReq(int carAccount, int entityKeyTag, int keyType, String userId, byte[] keyId) {
        Log.v(TAG, "queryKeyListReq is execute");
        OtaSdkManager otaSdkManager = this.mOtaSdkManager;
        if (otaSdkManager == null) {
            Log.e(TAG, "mOtaSdkManager is null");
        } else {
            otaSdkManager.queryKeyListReq(carAccount, entityKeyTag, keyType, userId, keyId);
        }
    }

    public void uploadNfcOpenInfo() {
        Log.v(TAG, "uploadNfcOpenInfo is execute");
        OtaSdkManager otaSdkManager = this.mOtaSdkManager;
        if (otaSdkManager == null) {
            Log.e(TAG, "mOtaSdkManager is null");
        } else {
            otaSdkManager.uploadNfcOpenInfo();
        }
    }

    public void deleteNfcKey(int op) {
        Log.v(TAG, "deleteNfcKey is execute");
        OtaSdkManager otaSdkManager = this.mOtaSdkManager;
        if (otaSdkManager == null) {
            Log.e(TAG, "mOtaSdkManager is null");
        } else {
            otaSdkManager.deleteNfcKey(op);
        }
    }

    public void deleteNfcKey(int op, String no) {
        Log.v(TAG, "delete single nfckey is execute");
        OtaSdkManager otaSdkManager = this.mOtaSdkManager;
        if (otaSdkManager == null) {
            Log.e(TAG, "mOtaSdkManager is null");
        } else {
            otaSdkManager.deleteSingleNfcKey(op, no);
        }
    }

    public void deleteOwnIphoneKey(int op, String no) {
        Log.v(TAG, "delete iphone nfckey is execute");
        OtaSdkManager otaSdkManager = this.mOtaSdkManager;
        if (otaSdkManager == null) {
            Log.e(TAG, "mOtaSdkManager is null");
        } else {
            otaSdkManager.deleteOwnIphoneKey(op, no);
        }
    }

    public void startRegisterNFCKey() {
        Log.v(TAG, "startRegisterNFCKey is execute");
        OtaSdkManager otaSdkManager = this.mOtaSdkManager;
        if (otaSdkManager == null) {
            Log.e(TAG, "mOtaSdkManager is null");
        } else {
            otaSdkManager.startRegisterNFCKey();
        }
    }

    public void registerListener(IDigitalKeyLisenter listener) {
        registerListener(listener, new int[0]);
    }

    public int getKeyFindingResults(int config) {
        Log.v(TAG, "getKeyFindingResults is execute, config is " + config);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (config == 0) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getOwnerKeyIdentifierResultSingle();
        }
        if (config == 1) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getKeyFindingResults();
        }
        return 0;
    }

    public int getOwnerKeyIdentifierResult(int config) {
        Log.v(TAG, "getOwnerKeyIdentifierResult is execute, config is " + config);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (config == 0) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getQueryOwnerKeyResultSingle();
        }
        if (config == 1) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getOwnerKeyIdentifierResult();
        }
        return 0;
    }

    public int getPairingProcess() {
        Log.v(TAG, "getPairingProcess is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getPairingProcess();
    }

    public int getOperateResult() {
        Log.v(TAG, "getPairingProcess is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getOperateResult();
    }

    public int getOperateCmd() {
        Log.v(TAG, "getPairingProcess is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getOperateCmd();
    }

    public int getOperationType19C33() {
        Log.v(TAG, "getOperationType19C33 is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getOperationType19C33();
    }

    public int getOperationCommand19C33() {
        Log.v(TAG, "getOperationCommand19C33 is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getOperationCommand19C33();
    }

    public int getOperationResult19C33() {
        Log.v(TAG, "getOperationResult19C33 is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getOperationResult19C33();
    }

    public int getResponseFeedbackInform() {
        Log.v(TAG, "getResponseFeedbackInform is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getResponseFeedbackInform();
    }

    public int setOperationCmdAndKeyType(int cmd, int type) {
        Log.v(TAG, "setOperationCmdAndKeyType cmd is " + cmd + ",type is " + type);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return -1;
        }
        return this.mOtaSdkManager.setOperationCmdAndKeyType(cmd, type);
    }

    public int setKeyIdOne(byte[] arr) {
        Log.v(TAG, "setKeyIdOne byte[] is " + arr);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return -1;
        }
        return this.mOtaSdkManager.setKeyIdOne(arr);
    }

    public int setKeyIdTwo(byte[] arr) {
        Log.v(TAG, "setKeyIdTwo byte[] is " + arr);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return -1;
        }
        return this.mOtaSdkManager.setKeyIdTwo(arr);
    }

    public int getPadOfflineOperationResp() {
        Log.v(TAG, "getPadOfflineOperationResp is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getPadOfflineOperationResp();
    }

    public int getOperationType() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getOperationType mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getOperationType();
    }

    public int getOperationCommand() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getOperationCommand mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getOperationCommand();
    }

    public int getResultsOfEnforcement() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getResultsOfEnforcement mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getResultsOfEnforcement();
    }

    public int getStatusOfUnlockSetting() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getStatusOfUnlockSetting mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getStatusOfUnlockSetting();
    }

    public int getStatusOfLockSetting() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getStatusOfLockSetting mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getStatusOfLockSetting();
    }

    public boolean relayDataToTbox40A(byte[] data) {
        if (this.mForTest != 0) {
            return false;
        }
        Log.v(TAG, " relayDataToTbox40A mForTestValue is 0 execute return");
        return false;
    }

    public boolean sendRemoteLockOrUnlockStatus(int type, int status) {
        if (this.mForTest != 0) {
            return false;
        }
        Log.v(TAG, " sendRemoteLockOrUnlockStatus mForTestValue is 0 execute return");
        return false;
    }

    public void registerListener(IDigitalKeyLisenter listener, int[] featureIDs) {
        Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe digital listener " + listener + " featureIDs = " + Arrays.toString(featureIDs));
        if (listener == null) {
            return;
        }
        if (!this.mDigitalKeyListeners.contains(listener)) {
            this.mDigitalKeyListeners.add(listener);
        }
        setFeatureIds(featureIDs);
        registerConnectListener(this.mContext, listener);
        TsManagerImpl.getInstance(this.mContext).registerAssistCallback();
    }

    public void unregisterListener(IDigitalKeyLisenter listener) {
        Log.i(TAG, "unregisterListener is run. ");
        if (listener == null) {
            return;
        }
        if (this.mDigitalKeyListeners.contains(listener)) {
            this.mDigitalKeyListeners.remove(listener);
        }
        unregisterConnectListener(listener);
    }

    public void registerTboxListener(IDigitalKeyTboxListener listener) {
        Log.w(TAG, "registerTboxListener is run " + listener);
        synchronized (this.mTboxListeners) {
            if (!this.mTboxListeners.contains(listener)) {
                Log.w(TAG, "registerTboxListener: listener->" + listener);
                this.mTboxListeners.add(listener);
            }
        }
    }

    public void unregisterTboxListener(IDigitalKeyTboxListener listener) {
        Log.w(TAG, "unregisterTboxListener is run " + listener);
        synchronized (this.mTboxListeners) {
            if (this.mTboxListeners.contains(listener)) {
                Log.w(TAG, "unregisterTboxListener: listener->" + listener);
                this.mTboxListeners.remove(listener);
            }
        }
    }

    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        BYDAutoEventValue bydAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bydAutoEventValue.intArrayValue = new int[eventTypes.length];
        if (eventTypes.length < 1) {
            bydAutoEventValue.intValue = -2147482648;
        } else {
            for (int eventType : eventTypes) {
                if (eventType > 0) {
                    if (eventType != 1350) {
                        if (eventType != 3693) {
                            if (eventType != 4830) {
                                if (eventType != 4831) {
                                    if (eventType != 4835) {
                                        if (eventType == 4836) {
                                            bydAutoEventValue.intValue = getPadExitPinCodeProcess();
                                            sparseArray.put(BYDAutoFeatureIds.SETTING_PAD_EXIT_PIN_CODE_PROCESS, Integer.valueOf(bydAutoEventValue.intValue));
                                        } else {
                                            switch (eventType) {
                                                case BYDAutoFeatureIds.SETTING_OPERATION_CODE_DATA_LENGTH /* 4838 */:
                                                    bydAutoEventValue.intValue = getOpcodeDataLength();
                                                    sparseArray.put(BYDAutoFeatureIds.SETTING_OPERATION_CODE_DATA_LENGTH, Integer.valueOf(bydAutoEventValue.intValue));
                                                    break;
                                                case BYDAutoFeatureIds.SETTING_OPERATION_CODE_CHECKSUM /* 4839 */:
                                                    bydAutoEventValue.intValue = getOpcodeChecksumValue();
                                                    sparseArray.put(BYDAutoFeatureIds.SETTING_OPERATION_CODE_CHECKSUM, Integer.valueOf(bydAutoEventValue.intValue));
                                                    break;
                                                case BYDAutoFeatureIds.SETTING_OPERATION_CODE_COMMAND /* 4840 */:
                                                    bydAutoEventValue.intValue = getOpcodeCommand();
                                                    sparseArray.put(BYDAutoFeatureIds.SETTING_OPERATION_CODE_COMMAND, Integer.valueOf(bydAutoEventValue.intValue));
                                                    break;
                                                case BYDAutoFeatureIds.SETTING_OPERATION_CODE_RESPONSE_RESULT /* 4841 */:
                                                    bydAutoEventValue.intValue = getResponseResultOfOpcode();
                                                    sparseArray.put(BYDAutoFeatureIds.SETTING_OPERATION_CODE_RESPONSE_RESULT, Integer.valueOf(bydAutoEventValue.intValue));
                                                    break;
                                                case BYDAutoFeatureIds.SETTING_OPERATION_CODE_DATA_VALUE /* 4842 */:
                                                    bydAutoEventValue.bufferDataValue = getOpcodeDataValue();
                                                    sparseArray.put(BYDAutoFeatureIds.SETTING_OPERATION_CODE_DATA_VALUE, bydAutoEventValue.bufferDataValue);
                                                    break;
                                                case BYDAutoFeatureIds.SETTING_DRIVING_PASSWORD_ITEM_DISPLAY_SELF_STUDY /* 4843 */:
                                                    bydAutoEventValue.intValue = getDrivingPasswordSettingConfig();
                                                    sparseArray.put(BYDAutoFeatureIds.SETTING_DRIVING_PASSWORD_ITEM_DISPLAY_SELF_STUDY, Integer.valueOf(bydAutoEventValue.intValue));
                                                    break;
                                                case BYDAutoFeatureIds.SETTING_PAD_OPERATION_CODE_CONFIGURATION /* 4844 */:
                                                    bydAutoEventValue.intValue = getPadOpcodeConfig();
                                                    sparseArray.put(BYDAutoFeatureIds.SETTING_PAD_OPERATION_CODE_CONFIGURATION, Integer.valueOf(bydAutoEventValue.intValue));
                                                    break;
                                                case BYDAutoFeatureIds.BODYWORK_BLUETOOTH_UNLOCK_TYPE /* 4845 */:
                                                    bydAutoEventValue.intValue = getBluetoothUnlockType();
                                                    sparseArray.put(BYDAutoFeatureIds.BODYWORK_BLUETOOTH_UNLOCK_TYPE, Integer.valueOf(bydAutoEventValue.intValue));
                                                    break;
                                                default:
                                                    Log.i(TAG, "case fail ! please check out");
                                                    break;
                                            }
                                        }
                                    } else {
                                        bydAutoEventValue.intValue = getOpcodeStartFunction();
                                        sparseArray.put(BYDAutoFeatureIds.SETTING_UNLOCK_DRIVING_PASSWORD_POPUP_TYPE, Integer.valueOf(bydAutoEventValue.intValue));
                                    }
                                } else {
                                    bydAutoEventValue.intValue = getNfcKeyUnlockResult();
                                    sparseArray.put(BYDAutoFeatureIds.BODYWORK_NFC_KEY_UNLOCK_EXECUTE_RESULT, Integer.valueOf(bydAutoEventValue.intValue));
                                }
                            } else {
                                bydAutoEventValue.intValue = supportOpcodeStartup();
                                sparseArray.put(BYDAutoFeatureIds.SETTING_SUPPORT_OPERATION_CODE_START, Integer.valueOf(bydAutoEventValue.intValue));
                            }
                        } else {
                            bydAutoEventValue.intValue = getIkConfigKeylessTime();
                            sparseArray.put(3693, Integer.valueOf(bydAutoEventValue.intValue));
                        }
                    } else {
                        bydAutoEventValue.intValue = getNFCConfigurationInformation();
                        sparseArray.put(1350, Integer.valueOf(bydAutoEventValue.intValue));
                    }
                    int index = 0;
                    for (int i : eventTypes) {
                        try {
                            int getNum = ((Integer) sparseArray.get(i)).intValue();
                            bydAutoEventValue.intArrayValue[index] = getNum;
                        } catch (Exception e) {
                            Log.e(TAG, "Feature id doesn't exist");
                        }
                        index++;
                    }
                } else {
                    bydAutoEventValue.intValue = -2147482648;
                }
            }
        }
        return bydAutoEventValue;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int id, BYDAutoEventValue value) {
        for (IDigitalKeyLisenter listener : this.mDigitalKeyListeners) {
            listener.onDataEventChanged(id, value);
        }
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int eventType, Object value) {
        int intValue;
        long[] longArrayValue = null;
        if (value instanceof Integer) {
            int intValue2 = ((Integer) value).intValue();
            intValue = intValue2;
        } else if (value instanceof Float) {
            ((Float) value).floatValue();
            intValue = 0;
        } else if (value instanceof Double) {
            ((Double) value).doubleValue();
            intValue = 0;
        } else if (value instanceof byte[]) {
            intValue = 0;
        } else if (value instanceof int[]) {
            intValue = 0;
        } else if (value instanceof long[]) {
            longArrayValue = (long[]) value;
            intValue = 0;
        } else {
            Log.d(TAG, "the type of value is not a suitable type");
            return -1;
        }
        if (eventType == 740) {
            setAllowStartTimeAfterUnlock(0, intValue);
            Log.v(TAG, "setAllowStartTimeAfterUnlock:" + intValue);
            return 1;
        }
        if (eventType == 755) {
            setRegisterKeyStartPairing(1, intValue);
            Log.v(TAG, "setRegisterKeyStartPairing:" + intValue);
            return 1;
        }
        if (eventType == 782) {
            setQueryOwnersKey(1, intValue);
            Log.v(TAG, "setQueryOwnersKey:" + intValue);
            return 1;
        }
        if (eventType == 1799) {
            setAllowStartTimeAfterUnlock(1, intValue);
            return 1;
        }
        if (eventType == 4158) {
            setRegisterKeyStartPairing(0, intValue);
            Log.v(TAG, "setRegisterKeyStartPairing:" + intValue);
            return 1;
        }
        if (eventType == 4169) {
            setQueryOwnersKey(0, intValue);
            Log.v(TAG, "setQueryOwnersKey:" + intValue);
            return 1;
        }
        if (eventType == 4829) {
            sendDrivingPasswordSetting(intValue);
            return 1;
        }
        if (eventType == 4837) {
            sendOpcodeFunctionalFailure(intValue);
            return 1;
        }
        if (eventType == 4846) {
            if (longArrayValue == null) {
                Log.e(TAG, "4846 value array is null");
                return -1;
            }
            if (longArrayValue.length == 3) {
                sendOpcodeDataToSEC3(longArrayValue[0], (int) longArrayValue[1], (int) longArrayValue[2]);
                return 1;
            }
            Log.e(TAG, "4846 value is invalid, arrayLength:" + longArrayValue.length);
            return -1;
        }
        if (eventType != 4847) {
            switch (eventType) {
                case BYDAutoFeatureIds.SETTING_CURRENT_OPERATION_CODE_SWITCH_STATE_SET /* 4832 */:
                    sendOpcodeSwitchState(intValue);
                    return 1;
                case BYDAutoFeatureIds.SETTING_CURRENT_ACCOUNT_RELATED_SWITCH_STATE_FLAG_SET /* 4833 */:
                    sendCurAccountSwitchFlag(intValue);
                    return 1;
                case BYDAutoFeatureIds.SETTING_CURRENT_OPCODE_SWITCH_STATUS_SET /* 4834 */:
                    sendOpcodeSwitchStateSetting(intValue);
                    return 1;
                default:
                    Log.v(TAG, "case fail ! please check out");
                    return -1;
            }
        }
        if (longArrayValue == null) {
            Log.e(TAG, "4847 value array is null");
            return -1;
        }
        if (longArrayValue.length == 4) {
            sendOpcodeDataToSEC4(longArrayValue[0], longArrayValue[1], (int) longArrayValue[2], (int) longArrayValue[3]);
            return 1;
        }
        Log.e(TAG, "4847 value is invalid, arrayLength:" + longArrayValue.length);
        return -1;
    }

    public void sendDrivingPasswordSetting(int type) {
        if (this.mForTest == 0) {
            Log.v(TAG, " sendDrivingPasswordSetting mForTestValue is 0 execute return");
        } else if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().sendDrivingPasswordSetting(type);
        }
    }

    public int supportOpcodeStartup() {
        if (this.mForTest == 0) {
            Log.v(TAG, " supportOpcodeStartup mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().supportOpcodeStartup();
    }

    public int getNfcKeyStatus() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getNfcKeyStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getNfcKeyStatus();
    }

    public int getNfcKeyUnlockType() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getNfcKeyUnlockType mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getNfcKeyUnlockType();
    }

    public int getNfcKeyUnlockResult() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getNfcKeyUnlockResult mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getNfcKeyUnlockResult();
    }

    public void sendOpcodeSwitchState(int stats) {
        if (this.mForTest == 0) {
            Log.v(TAG, " sendOpcodeSwitchState mForTestValue is 0 execute return");
        } else if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().sendOpcodeSwitchState(stats);
        }
    }

    public void sendCurAccountSwitchFlag(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " sendCurAccountSwitchFlag mForTestValue is 0 execute return");
        } else if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().sendCurAccountSwitchFlag(state);
        }
    }

    public void sendOpcodeSwitchStateSetting(int stats) {
        if (this.mForTest == 0) {
            Log.v(TAG, " sendOpcodeSwitchStateSetting mForTestValue is 0 execute return");
        } else if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().sendOpcodeSwitchStateSetting(stats);
        }
    }

    public int getOpcodeStartFunction() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getOpcodeStartFunction mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getOpcodeStartFunction();
    }

    public int getPadExitPinCodeProcess() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getPadExitPinCodeProcess mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getPadExitPinCodeProcess();
    }

    public void sendOpcodeFunctionalFailure(int state) {
        if (this.mForTest == 0) {
            Log.v(TAG, " sendOpcodeFunctionalFailure mForTestValue is 0 execute return");
        } else if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().sendOpcodeFunctionalFailure(state);
        }
    }

    public int getOpcodeDataLength() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getOpcodeDataLength mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getOpcodeDataLength();
    }

    public int getOpcodeChecksumValue() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getOpcodeChecksumValue mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getOpcodeChecksumValue();
    }

    public int getOpcodeCommand() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getOpcodeCommand mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getOpcodeCommand();
    }

    public int getResponseResultOfOpcode() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getResponseResultOfOpcode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getResponseResultOfOpcode();
    }

    public byte[] getOpcodeDataValue() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getOpcodeDataValue mForTestValue is 0 execute return");
            return new byte[0];
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return new byte[0];
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getOpcodeDataValue();
    }

    public int getDrivingPasswordSettingConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getDrivingPasswordSettingConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getDrivingPasswordSettingConfig();
    }

    public int getPadOpcodeConfig() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getPadOpcodeConfig mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getStudyAdapterManager().getRunningCodingConfig();
    }

    public int getBluetoothUnlockType() {
        if (this.mForTest == 0) {
            Log.v(TAG, " getBluetoothUnlockType mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarAssistAdapterManager().getBluetoothUnlockType();
    }

    public void setMultiplexor(int data) {
        Log.e(TAG, "setMultiplexor no method");
    }

    public void sendOpcodeDataToSEC3(long userid, int random, int nfc) {
        if (this.mForTest == 0) {
            Log.v(TAG, " sendOpcodeDataToSecUserId mForTestValue is 0 execute return");
        } else if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().sendOpcodeDataToSec3(userid, random, nfc);
        }
    }

    public void sendOpcodeDataToSEC4(long userid, long opcode, int random, int nfc) {
        if (this.mForTest == 0) {
            Log.v(TAG, " sendOpcodeDataToSecUserId mForTestValue is 0 execute return");
        } else if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().sendOpcodeDataToSec4(userid, opcode, random, nfc);
        }
    }
}
