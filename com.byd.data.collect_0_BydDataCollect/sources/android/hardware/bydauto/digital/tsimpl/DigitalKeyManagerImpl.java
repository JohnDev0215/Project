package android.hardware.bydauto.digital.tsimpl;

import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
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
import java.util.Iterator;
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
        public void onAppActivateResult(int i2, int i3) {
        }

        public void onDeleteAllNfcKeyResult(int i2, int i3) {
            Log.w(DigitalKeyManagerImpl.TAG, "onDeleteAllNfcKeyResult: type->" + i2 + " result->" + i3);
            Iterator it = DigitalKeyManagerImpl.this.mTboxListeners.iterator();
            while (it.hasNext()) {
                ((IDigitalKeyTboxListener) it.next()).onDeleteAllNfcKeyResult(i2, i3);
            }
        }

        public void onDeleteNfcKeyByKeyNoResult(String str, int i2) {
            Log.w(DigitalKeyManagerImpl.TAG, "onDeleteNfcKeyByKeyNoResult: keyNo->" + str + " result->" + i2);
            Iterator it = DigitalKeyManagerImpl.this.mTboxListeners.iterator();
            while (it.hasNext()) {
                ((IDigitalKeyTboxListener) it.next()).onDeleteNfcKeyByKeyNoResult(str, i2);
            }
        }

        public void onDeleteNfcKeyResult(int i2, int i3) {
        }

        public void onDeleteOwnIphoneKeyResult(String str, int i2) {
            Log.w(DigitalKeyManagerImpl.TAG, "onDeleteOwnIphoneKeyResult: keyNo->" + str + " result->" + i2);
            Iterator it = DigitalKeyManagerImpl.this.mTboxListeners.iterator();
            while (it.hasNext()) {
                ((IDigitalKeyTboxListener) it.next()).onDeleteOwnIphoneKeyResult(str, i2);
            }
        }

        public void onKeyListPageRefresh() {
            Log.w(DigitalKeyManagerImpl.TAG, "onKeyListPageRefresh");
            Iterator it = DigitalKeyManagerImpl.this.mTboxListeners.iterator();
            while (it.hasNext()) {
                ((IDigitalKeyTboxListener) it.next()).onKeyListPageRefresh();
            }
        }

        public void onKeyListResponse(int i2, List<DigitalKeyBeanSdk> list) {
            Log.w(DigitalKeyManagerImpl.TAG, "onKeyListResponse: resp is " + list);
            ArrayList arrayList = new ArrayList();
            for (DigitalKeyBeanSdk digitalKeyBeanSdk : list) {
                DigitalKeyBean digitalKeyBean = new DigitalKeyBean();
                digitalKeyBean.init(digitalKeyBeanSdk.generateForApi());
                arrayList.add(digitalKeyBean);
            }
            Iterator it = DigitalKeyManagerImpl.this.mTboxListeners.iterator();
            while (it.hasNext()) {
                ((IDigitalKeyTboxListener) it.next()).onKeyListResponse(i2, arrayList);
            }
        }

        public void onMobileNetworkStateChanged(int i2) {
        }

        public void onNfcOperationCommand(int i2) {
            Log.w(DigitalKeyManagerImpl.TAG, "onNfcOperationCommand: cmd->" + i2);
            Iterator it = DigitalKeyManagerImpl.this.mDigitalKeyListeners.iterator();
            while (it.hasNext()) {
                ((IDigitalKeyLisenter) it.next()).onNfcOperationCommand(i2);
            }
        }
    };

    DigitalKeyManagerImpl(Context context) {
        this.mContext = context;
        this.mTsManagerImpl = TsManagerImpl.getInstance(context);
        this.mOtaSdkManager = OtaSdkManager.getInstance(context);
        this.mTsManagerImpl.setOnAssistChangedListener(new OnAssistChangedListener() { // from class: android.hardware.bydauto.digital.tsimpl.DigitalKeyManagerImpl.1
            public void onAssistChanged(int i2, int i3, CarAssistPacket carAssistPacket) {
                Log.v(DigitalKeyManagerImpl.TAG, DigitalKeyManagerImpl.TAG + carAssistPacket.toString());
                if (DigitalKeyManagerImpl.this.mDigitalKeyListeners.isEmpty()) {
                    return;
                }
                try {
                    DigitalKeyManagerImpl.this.assistCallback(i2, i3, carAssistPacket);
                } catch (Exception unused) {
                    Log.e(DigitalKeyManagerImpl.TAG, "assistCallback is error");
                }
            }

            public void onBinderStateChanged(boolean z) {
                Log.d(DigitalKeyManagerImpl.TAG, "onBinderStateChanged is " + z);
            }
        });
        this.mOtaSdkManager.registerTboxListener(this.bydTboxListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void assistCallback(int i2, int i3, CarAssistPacket carAssistPacket) {
        Log.v(TAG, "AssistCallback propertyId:" + i2);
        if (i2 == 557846277) {
            int startForNoKeyR = carAssistPacket.getStartForNoKeyR();
            Iterator<IDigitalKeyLisenter> it = this.mDigitalKeyListeners.iterator();
            while (it.hasNext()) {
                it.next().onAllowStartTimeAfterUnlockChange(startForNoKeyR);
            }
            return;
        }
        if (i2 == 557846293) {
            int responseFeedbackInformR = carAssistPacket.getResponseFeedbackInformR();
            Iterator<IDigitalKeyLisenter> it2 = this.mDigitalKeyListeners.iterator();
            while (it2.hasNext()) {
                it2.next().onResponseFeedbackInform(responseFeedbackInformR);
            }
            return;
        }
        if (i2 == 557846297) {
            int padOfflineOperationRespR = carAssistPacket.getPadOfflineOperationRespR();
            Iterator<IDigitalKeyLisenter> it3 = this.mDigitalKeyListeners.iterator();
            while (it3.hasNext()) {
                it3.next().onPadOfflineOperationResp(padOfflineOperationRespR);
            }
            return;
        }
        switch (i2) {
            case DigitalHalId.ID_QUERY_OWNER_KEY_RESULT_SINGLE /* 557846280 */:
                int queryOwnerKeyResultSingle = carAssistPacket.getQueryOwnerKeyResultSingle();
                Iterator<IDigitalKeyLisenter> it4 = this.mDigitalKeyListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onOwnerKeyIdentifierResult(queryOwnerKeyResultSingle, 0);
                }
                caseFeatureIds(3912, Integer.valueOf(carAssistPacket.getQueryOwnerKeyResultSingle()));
                break;
            case DigitalHalId.ID_QUERY_OWNER_KEY_RESULT_DOUBLE /* 557846281 */:
                int queryOwnerKeyResultDouble = carAssistPacket.getQueryOwnerKeyResultDouble();
                Iterator<IDigitalKeyLisenter> it5 = this.mDigitalKeyListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onOwnerKeyIdentifierResult(queryOwnerKeyResultDouble, 1);
                }
                caseFeatureIds(4168, Integer.valueOf(carAssistPacket.getQueryOwnerKeyResultDouble()));
                break;
            case DigitalHalId.ID_FIND_KEY_RESULT_SINGLE /* 557846282 */:
                int findKeyResultSingle = carAssistPacket.getFindKeyResultSingle();
                Iterator<IDigitalKeyLisenter> it6 = this.mDigitalKeyListeners.iterator();
                while (it6.hasNext()) {
                    it6.next().onKeyFindingResults(findKeyResultSingle, 0);
                }
                caseFeatureIds(4171, Integer.valueOf(carAssistPacket.getFindKeyResultSingle()));
                break;
            case DigitalHalId.ID_FIND_KEY_RESULT_DOUBLE /* 557846283 */:
                int findKeyResultDouble = carAssistPacket.getFindKeyResultDouble();
                Iterator<IDigitalKeyLisenter> it7 = this.mDigitalKeyListeners.iterator();
                while (it7.hasNext()) {
                    it7.next().onKeyFindingResults(findKeyResultDouble, 1);
                }
                caseFeatureIds(4086, Integer.valueOf(carAssistPacket.getFindKeyResultDouble()));
                break;
            case DigitalHalId.ID_PAIRING_PROCESS_CALLBACK_SINGLE /* 557846284 */:
                int pairingProcessCallBackSingle = carAssistPacket.getPairingProcessCallBackSingle();
                Iterator<IDigitalKeyLisenter> it8 = this.mDigitalKeyListeners.iterator();
                while (it8.hasNext()) {
                    it8.next().onPairingProcessCallBack(pairingProcessCallBackSingle, 0);
                }
                caseFeatureIds(4104, Integer.valueOf(carAssistPacket.getPairingProcessCallBackSingle()));
                break;
            case DigitalHalId.ID_PAIRING_PROCESS_CALLBACK_DOUBLE /* 557846285 */:
                int pairingProcessCallBackDouble = carAssistPacket.getPairingProcessCallBackDouble();
                Iterator<IDigitalKeyLisenter> it9 = this.mDigitalKeyListeners.iterator();
                while (it9.hasNext()) {
                    it9.next().onPairingProcessCallBack(pairingProcessCallBackDouble, 1);
                }
                caseFeatureIds(4106, Integer.valueOf(carAssistPacket.getPairingProcessCallBackDouble()));
                break;
            case DigitalHalId.ID_PAIRING_RESULT_SINGLE /* 557846286 */:
                int pairingResultSingle = carAssistPacket.getPairingResultSingle();
                Iterator<IDigitalKeyLisenter> it10 = this.mDigitalKeyListeners.iterator();
                while (it10.hasNext()) {
                    it10.next().onOfflineOperateResult(pairingResultSingle, 0);
                }
                caseFeatureIds(4107, Integer.valueOf(carAssistPacket.getPairingResultSingle()));
                break;
            case DigitalHalId.ID_PAIRING_RESULT_DOUBLE /* 557846287 */:
                int pairingResultDouble = carAssistPacket.getPairingResultDouble();
                Iterator<IDigitalKeyLisenter> it11 = this.mDigitalKeyListeners.iterator();
                while (it11.hasNext()) {
                    it11.next().onOfflineOperateResult(pairingResultDouble, 1);
                }
                caseFeatureIds(4105, Integer.valueOf(carAssistPacket.getPairingResultDouble()));
                break;
            case DigitalHalId.ID_OFF_LINE_OPERAT_PAIRING_RESULTR /* 557846288 */:
                int offlineOperatPairingResult = carAssistPacket.getOfflineOperatPairingResult();
                Iterator<IDigitalKeyLisenter> it12 = this.mDigitalKeyListeners.iterator();
                while (it12.hasNext()) {
                    it12.next().onOfflineOperateCmd(offlineOperatPairingResult);
                }
                break;
            default:
                switch (i2) {
                    case DigitalHalId.ID_BLE_OPERATE_COMMAND_R /* 557846303 */:
                        int bleOperateCommandR = carAssistPacket.getBleOperateCommandR();
                        Iterator<IDigitalKeyLisenter> it13 = this.mDigitalKeyListeners.iterator();
                        while (it13.hasNext()) {
                            it13.next().onBleOperateCommand(bleOperateCommandR);
                        }
                        break;
                    case DigitalHalId.ID_BLE_OPERATE_RESULT_R /* 557846304 */:
                        int bleOperateResultR = carAssistPacket.getBleOperateResultR();
                        Iterator<IDigitalKeyLisenter> it14 = this.mDigitalKeyListeners.iterator();
                        while (it14.hasNext()) {
                            it14.next().onBleOperateResult(bleOperateResultR);
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

    public boolean deleteNfcFindKeyV2(byte[] bArr) {
        Log.v(TAG, "deleteNfcFindKeyV2 is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mOtaSdkManager.deleteNfcFindKeyV2(bArr);
        }
        Log.e(TAG, "adapter is not connect");
        return false;
    }

    public void deleteNfcKey(int i2) {
        Log.v(TAG, "deleteNfcKey is execute");
        OtaSdkManager otaSdkManager = this.mOtaSdkManager;
        if (otaSdkManager == null) {
            Log.e(TAG, "mOtaSdkManager is null");
        } else {
            otaSdkManager.deleteNfcKey(i2);
        }
    }

    public void deleteOwnIphoneKey(int i2, String str) {
        Log.v(TAG, "delete iphone nfckey is execute");
        OtaSdkManager otaSdkManager = this.mOtaSdkManager;
        if (otaSdkManager == null) {
            Log.e(TAG, "mOtaSdkManager is null");
        } else {
            otaSdkManager.deleteOwnIphoneKey(i2, str);
        }
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bYDAutoEventValue.intArrayValue = new int[iArr.length];
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i2 : iArr) {
                if (i2 > 0) {
                    if (i2 == 1350) {
                        int nFCConfigurationInformation = getNFCConfigurationInformation();
                        bYDAutoEventValue.intValue = nFCConfigurationInformation;
                        sparseArray.put(1350, Integer.valueOf(nFCConfigurationInformation));
                    } else if (i2 != 3693) {
                        Log.i(TAG, "case fail ! please check out");
                    } else {
                        int ikConfigKeylessTime = getIkConfigKeylessTime();
                        bYDAutoEventValue.intValue = ikConfigKeylessTime;
                        sparseArray.put(3693, Integer.valueOf(ikConfigKeylessTime));
                    }
                    int i3 = 0;
                    for (int i4 : iArr) {
                        try {
                            bYDAutoEventValue.intArrayValue[i3] = ((Integer) sparseArray.get(i4)).intValue();
                        } catch (Exception unused) {
                            Log.e(TAG, "Feature id doesn't exist");
                        }
                        i3++;
                    }
                } else {
                    bYDAutoEventValue.intValue = -2147482648;
                }
            }
        }
        return bYDAutoEventValue;
    }

    public int getIkConfigKeylessTime() {
        Log.v(TAG, "getIkConfigKeylessTime is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getIkConfigKeylessTime();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getKeyFindingResults(int i2) {
        Log.v(TAG, "getKeyFindingResults is execute, config is " + i2);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (i2 == 0) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getOwnerKeyIdentifierResultSingle();
        }
        if (i2 == 1) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getKeyFindingResults();
        }
        return 0;
    }

    public int getNFCConfigurationInformation() {
        Log.v(TAG, "getNFCConfigurationInformation is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getNFCConfigurationInformation();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getOperateCmd() {
        Log.v(TAG, "getPairingProcess is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getOperateCmd();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getOperateResult() {
        Log.v(TAG, "getPairingProcess is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getOperateResult();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getOperationCommand() {
        if (this.mForTest != 0) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getOperationCommand();
        }
        Log.v(TAG, " getOperationCommand mForTestValue is 0 execute return");
        return -1;
    }

    public int getOperationCommand19C33() {
        Log.v(TAG, "getOperationCommand19C33 is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getOperationCommand19C33();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getOperationResult19C33() {
        Log.v(TAG, "getOperationResult19C33 is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getOperationResult19C33();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getOperationType() {
        if (this.mForTest != 0) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getOperationType();
        }
        Log.v(TAG, " getOperationType mForTestValue is 0 execute return");
        return -1;
    }

    public int getOperationType19C33() {
        Log.v(TAG, "getOperationType19C33 is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getOperationType19C33();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getOwnerKeyIdentifierResult(int i2) {
        Log.v(TAG, "getOwnerKeyIdentifierResult is execute, config is " + i2);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (i2 == 0) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getQueryOwnerKeyResultSingle();
        }
        if (i2 == 1) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getOwnerKeyIdentifierResult();
        }
        return 0;
    }

    public int getPadOfflineOperationResp() {
        Log.v(TAG, "getPadOfflineOperationResp is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getPadOfflineOperationResp();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getPairingProcess() {
        Log.v(TAG, "getPairingProcess is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getPairingProcess();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getResponseFeedbackInform() {
        Log.v(TAG, "getResponseFeedbackInform is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getResponseFeedbackInform();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getResultsOfEnforcement() {
        if (this.mForTest != 0) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getResultsOfEnforcement();
        }
        Log.v(TAG, " getResultsOfEnforcement mForTestValue is 0 execute return");
        return -1;
    }

    public int getStatusOfLockSetting() {
        if (this.mForTest != 0) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getStatusOfLockSetting();
        }
        Log.v(TAG, " getStatusOfLockSetting mForTestValue is 0 execute return");
        return -1;
    }

    public int getStatusOfUnlockSetting() {
        if (this.mForTest != 0) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getStatusOfUnlockSetting();
        }
        Log.v(TAG, " getStatusOfUnlockSetting mForTestValue is 0 execute return");
        return -1;
    }

    public boolean offOnlineRemoveKey97V2(byte[] bArr) {
        Log.v(TAG, "offOnlineRemoveKey97V2 is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mOtaSdkManager.offOnlineRemoveKey97V2(bArr);
        }
        Log.e(TAG, "adapter is not connect");
        return false;
    }

    public boolean offOnlineRemoveKey98V2(byte[] bArr) {
        Log.v(TAG, "offOnlineRemoveKey98V2 is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mOtaSdkManager.offOnlineRemoveKey98V2(bArr);
        }
        Log.e(TAG, "adapter is not connect");
        return false;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i2, BYDAutoEventValue bYDAutoEventValue) {
        Iterator<IDigitalKeyLisenter> it = this.mDigitalKeyListeners.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i2, bYDAutoEventValue);
        }
    }

    public void queryKeyListReq(int i2, int i3, int i4, String str, String str2) {
        Log.v(TAG, "queryKeyListReq is execute");
        OtaSdkManager otaSdkManager = this.mOtaSdkManager;
        if (otaSdkManager == null) {
            Log.e(TAG, "mOtaSdkManager is null");
        } else {
            otaSdkManager.queryKeyListReq(i2, i3, i4, str, str2);
        }
    }

    public void registerListener(IDigitalKeyLisenter iDigitalKeyLisenter) {
        registerListener(iDigitalKeyLisenter, new int[0]);
    }

    public void registerTboxListener(IDigitalKeyTboxListener iDigitalKeyTboxListener) {
        Log.w(TAG, "registerTboxListener is run " + iDigitalKeyTboxListener);
        synchronized (this.mTboxListeners) {
            if (!this.mTboxListeners.contains(iDigitalKeyTboxListener)) {
                Log.w(TAG, "registerTboxListener: listener->" + iDigitalKeyTboxListener);
                this.mTboxListeners.add(iDigitalKeyTboxListener);
            }
        }
    }

    public boolean relayDataToTbox40A(byte[] bArr) {
        if (this.mForTest == 0) {
            Log.v(TAG, " relayDataToTbox40A mForTestValue is 0 execute return");
        }
        return false;
    }

    public boolean sendRemoteLockOrUnlockStatus(int i2, int i3) {
        if (this.mForTest == 0) {
            Log.v(TAG, " sendRemoteLockOrUnlockStatus mForTestValue is 0 execute return");
        }
        return false;
    }

    public boolean setAllowStartTimeAfterUnlock(int i2, int i3) {
        Log.v(TAG, "setAllowStartTimeAfterUnlockOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mOtaSdkManager.setAllowStartTimeAfterUnlock(i2, i3);
        }
        Log.e(TAG, "adapter is not connect");
        return false;
    }

    public void setAllowStartTimeAfterUnlockInOut(int i2) {
        Log.v(TAG, "setAllowStartTimeAfterUnlockInOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().setAllowStartTimeAfterUnlockInOut(i2);
        }
    }

    public void setAllowStartTimeAfterUnlockOut(int i2) {
        Log.v(TAG, "setAllowStartTimeAfterUnlockOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().setAllowStartTimeAfterUnlockOut(i2);
        }
    }

    public boolean setAllowStartTimeAfterUnlockV2(byte[] bArr) {
        Log.v(TAG, "setAllowStartTimeAfterUnlockV2 is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mOtaSdkManager.setAllowStartTimeAfterUnlockV2(bArr);
        }
        Log.e(TAG, "adapter is not connect");
        return false;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int i2, Object obj) {
        int iIntValue;
        if (obj instanceof Integer) {
            iIntValue = ((Integer) obj).intValue();
        } else {
            if (obj instanceof Float) {
                ((Float) obj).floatValue();
            } else if (obj instanceof Double) {
                ((Double) obj).doubleValue();
            } else {
                if (!(obj instanceof byte[])) {
                    Log.d(TAG, "the type of value is not a suitable type");
                    return -1;
                }
            }
            iIntValue = 0;
        }
        if (i2 == 740) {
            setAllowStartTimeAfterUnlock(0, iIntValue);
            Log.v(TAG, "setAllowStartTimeAfterUnlock:" + iIntValue);
        } else if (i2 == 755) {
            setRegisterKeyStartPairing(1, iIntValue);
            Log.v(TAG, "setRegisterKeyStartPairing:" + iIntValue);
        } else if (i2 == 782) {
            setQueryOwnersKey(1, iIntValue);
            Log.v(TAG, "setQueryOwnersKey:" + iIntValue);
        } else if (i2 == 1799) {
            setAllowStartTimeAfterUnlock(1, iIntValue);
        } else if (i2 == 4158) {
            setRegisterKeyStartPairing(0, iIntValue);
            Log.v(TAG, "setRegisterKeyStartPairing:" + iIntValue);
        } else {
            if (i2 != 4169) {
                Log.v(TAG, "case fail ! please check out");
                return -1;
            }
            setQueryOwnersKey(0, iIntValue);
            Log.v(TAG, "setQueryOwnersKey:" + iIntValue);
        }
        return 1;
    }

    public int setKeyIdOne(byte[] bArr) {
        Log.v(TAG, "setKeyIdOne byte[] is " + bArr);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mOtaSdkManager.setKeyIdOne(bArr);
        }
        Log.e(TAG, "adapter is not connect");
        return -1;
    }

    public int setKeyIdTwo(byte[] bArr) {
        Log.v(TAG, "setKeyIdTwo byte[] is " + bArr);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mOtaSdkManager.setKeyIdTwo(bArr);
        }
        Log.e(TAG, "adapter is not connect");
        return -1;
    }

    public int setOperationCmdAndKeyType(int i2, int i3) {
        Log.v(TAG, "setOperationCmdAndKeyType cmd is " + i2 + ",type is " + i3);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mOtaSdkManager.setOperationCmdAndKeyType(i2, i3);
        }
        Log.e(TAG, "adapter is not connect");
        return -1;
    }

    public boolean setQueryOwnersKey(int i2, int i3) {
        Log.v(TAG, "setQueryOwnersKeyInOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mOtaSdkManager.setQueryOwnersKey(i2, i3);
        }
        Log.e(TAG, "adapter is not connect");
        return false;
    }

    public void setQueryOwnersKeyInOut(int i2) {
        Log.v(TAG, "setQueryOwnersKeyInOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().setQueryOwnersKeyInOut(i2);
        }
    }

    public void setQueryOwnersKeyOut(int i2) {
        Log.v(TAG, "setQueryOwnersKeyOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().setQueryOwnersKeyOut(i2);
        }
    }

    public boolean setQueryOwnersKeyV2(byte[] bArr) {
        Log.v(TAG, "setQueryOwnersKeyV2 is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mOtaSdkManager.setQueryOwnersKeyV2(bArr);
        }
        Log.e(TAG, "adapter is not connect");
        return false;
    }

    public boolean setRegisterKeyStartPairing(int i2, int i3) {
        Log.v(TAG, "setRegisterKeyStartPairingOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mOtaSdkManager.setRegisterKeyStartPairing(i2, i3);
        }
        Log.e(TAG, "adapter is not connect");
        return false;
    }

    public void setRegisterKeyStartPairingInOut(int i2) {
        Log.v(TAG, "setRegisterKeyStartPairingInOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().setRegisterKeyStartPairingInOut(i2);
        }
    }

    public void setRegisterKeyStartPairingOut(int i2) {
        Log.v(TAG, "setRegisterKeyStartPairingOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().setRegisterKeyStartPairingOut(i2);
        }
    }

    public boolean setRegisterKeyStartPairingV2(byte[] bArr) {
        Log.v(TAG, "setRegisterKeyStartPairingV2 is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mOtaSdkManager.setRegisterKeyStartPairingV2(bArr);
        }
        Log.e(TAG, "adapter is not connect");
        return false;
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

    public void unregisterListener(IDigitalKeyLisenter iDigitalKeyLisenter) {
        Log.i(TAG, "unregisterListener is run. ");
        if (iDigitalKeyLisenter == null) {
            return;
        }
        if (this.mDigitalKeyListeners.contains(iDigitalKeyLisenter)) {
            this.mDigitalKeyListeners.remove(iDigitalKeyLisenter);
        }
        unregisterConnectListener(iDigitalKeyLisenter);
    }

    public void unregisterTboxListener(IDigitalKeyTboxListener iDigitalKeyTboxListener) {
        Log.w(TAG, "unregisterTboxListener is run " + iDigitalKeyTboxListener);
        synchronized (this.mTboxListeners) {
            if (this.mTboxListeners.contains(iDigitalKeyTboxListener)) {
                Log.w(TAG, "unregisterTboxListener: listener->" + iDigitalKeyTboxListener);
                this.mTboxListeners.remove(iDigitalKeyTboxListener);
            }
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

    public void registerListener(IDigitalKeyLisenter iDigitalKeyLisenter, int[] iArr) {
        Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe digital listener " + iDigitalKeyLisenter + " featureIDs = " + Arrays.toString(iArr));
        if (iDigitalKeyLisenter == null) {
            return;
        }
        if (!this.mDigitalKeyListeners.contains(iDigitalKeyLisenter)) {
            this.mDigitalKeyListeners.add(iDigitalKeyLisenter);
        }
        setFeatureIds(iArr);
        registerConnectListener(this.mContext, iDigitalKeyLisenter);
        TsManagerImpl.getInstance(this.mContext).registerAssistCallback();
    }

    public void deleteNfcKey(int i2, String str) {
        Log.v(TAG, "delete single nfckey is execute");
        OtaSdkManager otaSdkManager = this.mOtaSdkManager;
        if (otaSdkManager == null) {
            Log.e(TAG, "mOtaSdkManager is null");
        } else {
            otaSdkManager.deleteSingleNfcKey(i2, str);
        }
    }

    public void queryKeyListReq(int i2, int i3, int i4, String str, byte[] bArr) {
        Log.v(TAG, "queryKeyListReq is execute");
        OtaSdkManager otaSdkManager = this.mOtaSdkManager;
        if (otaSdkManager == null) {
            Log.e(TAG, "mOtaSdkManager is null");
        } else {
            otaSdkManager.queryKeyListReq(i2, i3, i4, str, bArr);
        }
    }
}
