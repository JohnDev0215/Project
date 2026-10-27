package android.hardware.bydauto.digital.tsimpl;

import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.digital.IDigitalKeyLisenter;
import android.util.Log;
import com.ts.lib.caradapter.assist.CarAssistPacket;
import com.ts.lib.caradapter.assist.OnAssistChangedListener;
import com.ts.ota.otasdkmgr.DigitalKeyBeanSdk;
import com.ts.ota.otasdkmgr.IAbsBYDTBoxListener;
import com.ts.ota.otasdkmgr.OtaSdkManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import spi.d;
import spi.e;

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
    public IAbsBYDTBoxListener.Stub bydTboxListener = new IAbsBYDTBoxListener.Stub() { // from class: android.hardware.bydauto.digital.tsimpl.DigitalKeyManagerImpl.2
        public void onAppActivateResult(int i, int i2) {
        }

        public void onDeleteAllNfcKeyResult(int i, int i2) {
            Log.w(DigitalKeyManagerImpl.TAG, "onDeleteAllNfcKeyResult: type->" + i + " result->" + i2);
            Iterator it = DigitalKeyManagerImpl.this.mTboxListeners.iterator();
            while (it.hasNext()) {
                ((IDigitalKeyTboxListener) it.next()).onDeleteAllNfcKeyResult(i, i2);
            }
        }

        public void onDeleteNfcKeyByKeyNoResult(String str, int i) {
            Log.w(DigitalKeyManagerImpl.TAG, "onDeleteNfcKeyByKeyNoResult: keyNo->" + str + " result->" + i);
            Iterator it = DigitalKeyManagerImpl.this.mTboxListeners.iterator();
            while (it.hasNext()) {
                ((IDigitalKeyTboxListener) it.next()).onDeleteNfcKeyByKeyNoResult(str, i);
            }
        }

        public void onDeleteNfcKeyResult(int i, int i2) {
        }

        public void onDeleteOwnIphoneKeyResult(String str, int i) {
            Log.w(DigitalKeyManagerImpl.TAG, "onDeleteOwnIphoneKeyResult: keyNo->" + str + " result->" + i);
            Iterator it = DigitalKeyManagerImpl.this.mTboxListeners.iterator();
            while (it.hasNext()) {
                ((IDigitalKeyTboxListener) it.next()).onDeleteOwnIphoneKeyResult(str, i);
            }
        }

        public void onKeyListPageRefresh() {
            Log.w(DigitalKeyManagerImpl.TAG, "onKeyListPageRefresh");
            Iterator it = DigitalKeyManagerImpl.this.mTboxListeners.iterator();
            while (it.hasNext()) {
                ((IDigitalKeyTboxListener) it.next()).onKeyListPageRefresh();
            }
        }

        public void onKeyListResponse(int i, List<DigitalKeyBeanSdk> list) {
            Log.w(DigitalKeyManagerImpl.TAG, "onKeyListResponse: resp is " + list);
            ArrayList arrayList = new ArrayList();
            for (DigitalKeyBeanSdk digitalKeyBeanSdk : list) {
                DigitalKeyBean digitalKeyBean = new DigitalKeyBean();
                digitalKeyBean.init(digitalKeyBeanSdk.generateForApi());
                arrayList.add(digitalKeyBean);
            }
            Iterator it = DigitalKeyManagerImpl.this.mTboxListeners.iterator();
            while (it.hasNext()) {
                ((IDigitalKeyTboxListener) it.next()).onKeyListResponse(i, arrayList);
            }
        }

        public void onMobileNetworkStateChanged(int i) {
        }

        public void onNfcOperationCommand(int i) {
            d.x("onNfcOperationCommand: cmd->", i, DigitalKeyManagerImpl.TAG);
            Iterator it = DigitalKeyManagerImpl.this.mDigitalKeyListeners.iterator();
            while (it.hasNext()) {
                ((IDigitalKeyLisenter) it.next()).onNfcOperationCommand(i);
            }
        }
    };

    public DigitalKeyManagerImpl(Context context) {
        this.mContext = context;
        this.mTsManagerImpl = TsManagerImpl.getInstance(context);
        this.mOtaSdkManager = OtaSdkManager.getInstance(context);
        this.mTsManagerImpl.setOnAssistChangedListener(new OnAssistChangedListener() { // from class: android.hardware.bydauto.digital.tsimpl.DigitalKeyManagerImpl.1
            public void onAssistChanged(int i, int i2, CarAssistPacket carAssistPacket) {
                StringBuilder sbK = e.k(DigitalKeyManagerImpl.TAG);
                sbK.append(carAssistPacket.toString());
                Log.v(DigitalKeyManagerImpl.TAG, sbK.toString());
                if (DigitalKeyManagerImpl.this.mDigitalKeyListeners.isEmpty()) {
                    return;
                }
                try {
                    DigitalKeyManagerImpl.this.assistCallback(i, i2, carAssistPacket);
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
    public void assistCallback(int i, int i2, CarAssistPacket carAssistPacket) {
        int i3;
        int queryOwnerKeyResultSingle;
        e.r("AssistCallback propertyId:", i, TAG);
        if (i == 557846277) {
            int startForNoKeyR = carAssistPacket.getStartForNoKeyR();
            Iterator<IDigitalKeyLisenter> it = this.mDigitalKeyListeners.iterator();
            while (it.hasNext()) {
                it.next().onAllowStartTimeAfterUnlockChange(startForNoKeyR);
            }
            return;
        }
        if (i == 557846293) {
            int responseFeedbackInformR = carAssistPacket.getResponseFeedbackInformR();
            Iterator<IDigitalKeyLisenter> it2 = this.mDigitalKeyListeners.iterator();
            while (it2.hasNext()) {
                it2.next().onResponseFeedbackInform(responseFeedbackInformR);
            }
            return;
        }
        if (i == 557846297) {
            int padOfflineOperationRespR = carAssistPacket.getPadOfflineOperationRespR();
            Iterator<IDigitalKeyLisenter> it3 = this.mDigitalKeyListeners.iterator();
            while (it3.hasNext()) {
                it3.next().onPadOfflineOperationResp(padOfflineOperationRespR);
            }
            return;
        }
        switch (i) {
            case DigitalHalId.ID_QUERY_OWNER_KEY_RESULT_SINGLE /* 557846280 */:
                int queryOwnerKeyResultSingle2 = carAssistPacket.getQueryOwnerKeyResultSingle();
                Iterator<IDigitalKeyLisenter> it4 = this.mDigitalKeyListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onOwnerKeyIdentifierResult(queryOwnerKeyResultSingle2, 0);
                }
                i3 = 3912;
                queryOwnerKeyResultSingle = carAssistPacket.getQueryOwnerKeyResultSingle();
                break;
            case DigitalHalId.ID_QUERY_OWNER_KEY_RESULT_DOUBLE /* 557846281 */:
                int queryOwnerKeyResultDouble = carAssistPacket.getQueryOwnerKeyResultDouble();
                Iterator<IDigitalKeyLisenter> it5 = this.mDigitalKeyListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onOwnerKeyIdentifierResult(queryOwnerKeyResultDouble, 1);
                }
                i3 = 4168;
                queryOwnerKeyResultSingle = carAssistPacket.getQueryOwnerKeyResultDouble();
                break;
            case DigitalHalId.ID_FIND_KEY_RESULT_SINGLE /* 557846282 */:
                int findKeyResultSingle = carAssistPacket.getFindKeyResultSingle();
                Iterator<IDigitalKeyLisenter> it6 = this.mDigitalKeyListeners.iterator();
                while (it6.hasNext()) {
                    it6.next().onKeyFindingResults(findKeyResultSingle, 0);
                }
                i3 = 4171;
                queryOwnerKeyResultSingle = carAssistPacket.getFindKeyResultSingle();
                break;
            case DigitalHalId.ID_FIND_KEY_RESULT_DOUBLE /* 557846283 */:
                int findKeyResultDouble = carAssistPacket.getFindKeyResultDouble();
                Iterator<IDigitalKeyLisenter> it7 = this.mDigitalKeyListeners.iterator();
                while (it7.hasNext()) {
                    it7.next().onKeyFindingResults(findKeyResultDouble, 1);
                }
                i3 = 4086;
                queryOwnerKeyResultSingle = carAssistPacket.getFindKeyResultDouble();
                break;
            case DigitalHalId.ID_PAIRING_PROCESS_CALLBACK_SINGLE /* 557846284 */:
                int pairingProcessCallBackSingle = carAssistPacket.getPairingProcessCallBackSingle();
                Iterator<IDigitalKeyLisenter> it8 = this.mDigitalKeyListeners.iterator();
                while (it8.hasNext()) {
                    it8.next().onPairingProcessCallBack(pairingProcessCallBackSingle, 0);
                }
                i3 = 4104;
                queryOwnerKeyResultSingle = carAssistPacket.getPairingProcessCallBackSingle();
                break;
            case DigitalHalId.ID_PAIRING_PROCESS_CALLBACK_DOUBLE /* 557846285 */:
                int pairingProcessCallBackDouble = carAssistPacket.getPairingProcessCallBackDouble();
                Iterator<IDigitalKeyLisenter> it9 = this.mDigitalKeyListeners.iterator();
                while (it9.hasNext()) {
                    it9.next().onPairingProcessCallBack(pairingProcessCallBackDouble, 1);
                }
                i3 = 4106;
                queryOwnerKeyResultSingle = carAssistPacket.getPairingProcessCallBackDouble();
                break;
            case DigitalHalId.ID_PAIRING_RESULT_SINGLE /* 557846286 */:
                int pairingResultSingle = carAssistPacket.getPairingResultSingle();
                Iterator<IDigitalKeyLisenter> it10 = this.mDigitalKeyListeners.iterator();
                while (it10.hasNext()) {
                    it10.next().onOfflineOperateResult(pairingResultSingle, 0);
                }
                i3 = 4107;
                queryOwnerKeyResultSingle = carAssistPacket.getPairingResultSingle();
                break;
            case DigitalHalId.ID_PAIRING_RESULT_DOUBLE /* 557846287 */:
                int pairingResultDouble = carAssistPacket.getPairingResultDouble();
                Iterator<IDigitalKeyLisenter> it11 = this.mDigitalKeyListeners.iterator();
                while (it11.hasNext()) {
                    it11.next().onOfflineOperateResult(pairingResultDouble, 1);
                }
                i3 = 4105;
                queryOwnerKeyResultSingle = carAssistPacket.getPairingResultDouble();
                break;
            case DigitalHalId.ID_OFF_LINE_OPERAT_PAIRING_RESULTR /* 557846288 */:
                int offlineOperatPairingResult = carAssistPacket.getOfflineOperatPairingResult();
                Iterator<IDigitalKeyLisenter> it12 = this.mDigitalKeyListeners.iterator();
                while (it12.hasNext()) {
                    it12.next().onOfflineOperateCmd(offlineOperatPairingResult);
                }
                return;
            default:
                switch (i) {
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
        }
        caseFeatureIds(i3, Integer.valueOf(queryOwnerKeyResultSingle));
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

    public void deleteNfcKey(int i) {
        Log.v(TAG, "deleteNfcKey is execute");
        OtaSdkManager otaSdkManager = this.mOtaSdkManager;
        if (otaSdkManager == null) {
            Log.e(TAG, "mOtaSdkManager is null");
        } else {
            otaSdkManager.deleteNfcKey(i);
        }
    }

    public void deleteNfcKey(int i, String str) {
        Log.v(TAG, "delete single nfckey is execute");
        OtaSdkManager otaSdkManager = this.mOtaSdkManager;
        if (otaSdkManager == null) {
            Log.e(TAG, "mOtaSdkManager is null");
        } else {
            otaSdkManager.deleteSingleNfcKey(i, str);
        }
    }

    public void deleteOwnIphoneKey(int i, String str) {
        Log.v(TAG, "delete iphone nfckey is execute");
        OtaSdkManager otaSdkManager = this.mOtaSdkManager;
        if (otaSdkManager == null) {
            Log.e(TAG, "mOtaSdkManager is null");
        } else {
            otaSdkManager.deleteOwnIphoneKey(i, str);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.hardware.bydauto.BYDAutoEventValue get(int[] r12, java.lang.Class<?> r13) {
        /*
            r11 = this;
            android.hardware.bydauto.BYDAutoEventValue r13 = new android.hardware.bydauto.BYDAutoEventValue
            r13.<init>()
            android.util.SparseArray r0 = new android.util.SparseArray
            r0.<init>()
            int r1 = r12.length
            int[] r1 = new int[r1]
            r13.intArrayValue = r1
            int r1 = r12.length
            r2 = -2147482648(0xffffffff800003e8, float:-1.401E-42)
            r3 = 1
            if (r1 >= r3) goto L19
            r13.intValue = r2
            goto L69
        L19:
            int r1 = r12.length
            r3 = 0
            r4 = r3
        L1c:
            if (r4 >= r1) goto L69
            r5 = r12[r4]
            if (r5 <= 0) goto L64
            java.lang.String r6 = "DigitalKeyManagerImpl"
            r7 = 1350(0x546, float:1.892E-42)
            if (r5 == r7) goto L37
            r7 = 3693(0xe6d, float:5.175E-42)
            if (r5 == r7) goto L32
            java.lang.String r5 = "case fail ! please check out"
            android.util.Log.i(r6, r5)
            goto L44
        L32:
            int r5 = r11.getIkConfigKeylessTime()
            goto L3b
        L37:
            int r5 = r11.getNFCConfigurationInformation()
        L3b:
            r13.intValue = r5
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r0.put(r7, r5)
        L44:
            int r5 = r12.length
            r7 = r3
            r8 = r7
        L47:
            if (r7 >= r5) goto L66
            r9 = r12[r7]
            java.lang.Object r9 = r0.get(r9)     // Catch: java.lang.Exception -> L5a
            java.lang.Integer r9 = (java.lang.Integer) r9     // Catch: java.lang.Exception -> L5a
            int r9 = r9.intValue()     // Catch: java.lang.Exception -> L5a
            int[] r10 = r13.intArrayValue     // Catch: java.lang.Exception -> L5a
            r10[r8] = r9     // Catch: java.lang.Exception -> L5a
            goto L5f
        L5a:
            java.lang.String r9 = "Feature id doesn't exist"
            android.util.Log.e(r6, r9)
        L5f:
            int r8 = r8 + 1
            int r7 = r7 + 1
            goto L47
        L64:
            r13.intValue = r2
        L66:
            int r4 = r4 + 1
            goto L1c
        L69:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: android.hardware.bydauto.digital.tsimpl.DigitalKeyManagerImpl.get(int[], java.lang.Class):android.hardware.bydauto.BYDAutoEventValue");
    }

    public int getIkConfigKeylessTime() {
        Log.v(TAG, "getIkConfigKeylessTime is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getIkConfigKeylessTime();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getKeyFindingResults(int i) {
        e.r("getKeyFindingResults is execute, config is ", i, TAG);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 0) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getOwnerKeyIdentifierResultSingle();
        }
        if (i == 1) {
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

    public int getOwnerKeyIdentifierResult(int i) {
        e.r("getOwnerKeyIdentifierResult is execute, config is ", i, TAG);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 0) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getQueryOwnerKeyResultSingle();
        }
        if (i == 1) {
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
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Iterator<IDigitalKeyLisenter> it = this.mDigitalKeyListeners.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i, bYDAutoEventValue);
        }
    }

    public void queryKeyListReq(int i, int i2, int i3, String str, String str2) {
        Log.v(TAG, "queryKeyListReq is execute");
        OtaSdkManager otaSdkManager = this.mOtaSdkManager;
        if (otaSdkManager == null) {
            Log.e(TAG, "mOtaSdkManager is null");
        } else {
            otaSdkManager.queryKeyListReq(i, i2, i3, str, str2);
        }
    }

    public void queryKeyListReq(int i, int i2, int i3, String str, byte[] bArr) {
        Log.v(TAG, "queryKeyListReq is execute");
        OtaSdkManager otaSdkManager = this.mOtaSdkManager;
        if (otaSdkManager == null) {
            Log.e(TAG, "mOtaSdkManager is null");
        } else {
            otaSdkManager.queryKeyListReq(i, i2, i3, str, bArr);
        }
    }

    public void registerListener(IDigitalKeyLisenter iDigitalKeyLisenter) {
        registerListener(iDigitalKeyLisenter, new int[0]);
    }

    public void registerListener(IDigitalKeyLisenter iDigitalKeyLisenter, int[] iArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mContext.getPackageName());
        sb.append(" byd_subscribe digital listener ");
        sb.append(iDigitalKeyLisenter);
        sb.append(" featureIDs = ");
        d.v(iArr, sb, TAG);
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

    public boolean sendRemoteLockOrUnlockStatus(int i, int i2) {
        if (this.mForTest == 0) {
            Log.v(TAG, " sendRemoteLockOrUnlockStatus mForTestValue is 0 execute return");
        }
        return false;
    }

    public boolean setAllowStartTimeAfterUnlock(int i, int i2) {
        Log.v(TAG, "setAllowStartTimeAfterUnlockOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mOtaSdkManager.setAllowStartTimeAfterUnlock(i, i2);
        }
        Log.e(TAG, "adapter is not connect");
        return false;
    }

    public void setAllowStartTimeAfterUnlockInOut(int i) {
        Log.v(TAG, "setAllowStartTimeAfterUnlockInOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().setAllowStartTimeAfterUnlockInOut(i);
        }
    }

    public void setAllowStartTimeAfterUnlockOut(int i) {
        Log.v(TAG, "setAllowStartTimeAfterUnlockOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().setAllowStartTimeAfterUnlockOut(i);
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
    public int setForOneFeatureId(int i, Object obj) {
        int iIntValue;
        StringBuilder sb;
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
        if (i != 740) {
            if (i != 755) {
                if (i == 782) {
                    setQueryOwnersKey(1, iIntValue);
                    sb = new StringBuilder();
                } else {
                    if (i == 1799) {
                        setAllowStartTimeAfterUnlock(1, iIntValue);
                        return 1;
                    }
                    if (i == 4158) {
                        setRegisterKeyStartPairing(0, iIntValue);
                        sb = new StringBuilder();
                    } else {
                        if (i != 4169) {
                            Log.v(TAG, "case fail ! please check out");
                            return -1;
                        }
                        setQueryOwnersKey(0, iIntValue);
                        sb = new StringBuilder();
                    }
                }
                sb.append("setQueryOwnersKey:");
            } else {
                setRegisterKeyStartPairing(1, iIntValue);
                sb = new StringBuilder();
            }
            sb.append("setRegisterKeyStartPairing:");
        } else {
            setAllowStartTimeAfterUnlock(0, iIntValue);
            sb = new StringBuilder();
            sb.append("setAllowStartTimeAfterUnlock:");
        }
        sb.append(iIntValue);
        Log.v(TAG, sb.toString());
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

    public int setOperationCmdAndKeyType(int i, int i2) {
        Log.v(TAG, "setOperationCmdAndKeyType cmd is " + i + ",type is " + i2);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mOtaSdkManager.setOperationCmdAndKeyType(i, i2);
        }
        Log.e(TAG, "adapter is not connect");
        return -1;
    }

    public boolean setQueryOwnersKey(int i, int i2) {
        Log.v(TAG, "setQueryOwnersKeyInOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mOtaSdkManager.setQueryOwnersKey(i, i2);
        }
        Log.e(TAG, "adapter is not connect");
        return false;
    }

    public void setQueryOwnersKeyInOut(int i) {
        Log.v(TAG, "setQueryOwnersKeyInOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().setQueryOwnersKeyInOut(i);
        }
    }

    public void setQueryOwnersKeyOut(int i) {
        Log.v(TAG, "setQueryOwnersKeyOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().setQueryOwnersKeyOut(i);
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

    public boolean setRegisterKeyStartPairing(int i, int i2) {
        Log.v(TAG, "setRegisterKeyStartPairingOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mOtaSdkManager.setRegisterKeyStartPairing(i, i2);
        }
        Log.e(TAG, "adapter is not connect");
        return false;
    }

    public void setRegisterKeyStartPairingInOut(int i) {
        Log.v(TAG, "setRegisterKeyStartPairingInOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().setRegisterKeyStartPairingInOut(i);
        }
    }

    public void setRegisterKeyStartPairingOut(int i) {
        Log.v(TAG, "setRegisterKeyStartPairingOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().setRegisterKeyStartPairingOut(i);
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
}
