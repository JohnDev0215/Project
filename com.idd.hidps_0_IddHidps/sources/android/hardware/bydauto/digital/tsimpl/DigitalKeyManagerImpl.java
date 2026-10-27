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
    private static final String TAG = "DigitalKeyManagerImpl";
    private static volatile DigitalKeyManagerImpl sInstance;
    private Context mContext;
    private int[] mFeatureIds;
    private IDigitalKeyLisenter mIDigitalKeyLisenter;
    private OtaSdkManager mOtaSdkManager;
    private TsManagerImpl mTsManagerImpl;
    private final CopyOnWriteArrayList<IDigitalKeyLisenter> mDigitalKeyListeners = new CopyOnWriteArrayList<>();
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();
    IAbsBYDTBoxListener.Stub bydTboxListener = new IAbsBYDTBoxListener.Stub() { // from class: android.hardware.bydauto.digital.tsimpl.DigitalKeyManagerImpl.2
        public void onAppActivateResult(int i, int i2) {
        }

        public void onDeleteAllNfcKeyResult(int i, int i2) {
            Log.w(DigitalKeyManagerImpl.TAG, "onDeleteAllNfcKeyResult: type->" + i + " result->" + i2);
            Iterator it = DigitalKeyManagerImpl.this.mDigitalKeyListeners.iterator();
            while (it.hasNext()) {
                ((IDigitalKeyLisenter) it.next()).onDeleteAllNfcKeyResult(i, i2);
            }
        }

        public void onDeleteNfcKeyByKeyNoResult(String str, int i) {
            Log.w(DigitalKeyManagerImpl.TAG, "onDeleteNfcKeyByKeyNoResult: keyNo->" + str + " result->" + i);
            Iterator it = DigitalKeyManagerImpl.this.mDigitalKeyListeners.iterator();
            while (it.hasNext()) {
                ((IDigitalKeyLisenter) it.next()).onDeleteNfcKeyByKeyNoResult(str, i);
            }
        }

        public void onDeleteNfcKeyResult(int i, int i2) {
            Log.w(DigitalKeyManagerImpl.TAG, "onDeleteNfcKeyResult: op->" + i + " result->" + i2);
            Iterator it = DigitalKeyManagerImpl.this.mDigitalKeyListeners.iterator();
            while (it.hasNext()) {
                ((IDigitalKeyLisenter) it.next()).onDeleteNfcKeyResult(i, i2);
            }
        }

        public void onDeleteOwnIphoneKeyResult(String str, int i) {
            Log.w(DigitalKeyManagerImpl.TAG, "onDeleteOwnIphoneKeyResult: keyNo->" + str + " result->" + i);
            Iterator it = DigitalKeyManagerImpl.this.mDigitalKeyListeners.iterator();
            while (it.hasNext()) {
                ((IDigitalKeyLisenter) it.next()).onDeleteOwnIphoneKeyResult(str, i);
            }
        }

        public void onKeyListPageRefresh() {
            Log.w(DigitalKeyManagerImpl.TAG, "onKeyListPageRefresh");
            Iterator it = DigitalKeyManagerImpl.this.mDigitalKeyListeners.iterator();
            while (it.hasNext()) {
                ((IDigitalKeyLisenter) it.next()).onKeyListPageRefresh();
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
            Iterator it = DigitalKeyManagerImpl.this.mDigitalKeyListeners.iterator();
            while (it.hasNext()) {
                ((IDigitalKeyLisenter) it.next()).onKeyListResponse(i, arrayList);
            }
        }

        public void onMobileNetworkStateChanged(int i) {
        }

        public void onNfcOperationCommand(int i) {
            Log.w(DigitalKeyManagerImpl.TAG, "onNfcOperationCommand: cmd->" + i);
            Iterator it = DigitalKeyManagerImpl.this.mDigitalKeyListeners.iterator();
            while (it.hasNext()) {
                ((IDigitalKeyLisenter) it.next()).onNfcOperationCommand(i);
            }
        }
    };

    DigitalKeyManagerImpl(Context context) {
        this.mContext = context;
        this.mTsManagerImpl = TsManagerImpl.getInstance(context);
        this.mOtaSdkManager = OtaSdkManager.getInstance(context);
        this.mTsManagerImpl.setOnAssistChangedListener(new OnAssistChangedListener() { // from class: android.hardware.bydauto.digital.tsimpl.DigitalKeyManagerImpl.1
            public void onAssistChanged(int i, int i2, CarAssistPacket carAssistPacket) {
                Log.v(DigitalKeyManagerImpl.TAG, DigitalKeyManagerImpl.TAG + carAssistPacket.toString());
                if (DigitalKeyManagerImpl.this.mDigitalKeyListeners.isEmpty()) {
                    return;
                }
                DigitalKeyManagerImpl.this.assistCallback(i, i2, carAssistPacket);
            }

            public void onBinderStateChanged(boolean z) {
                Log.d(DigitalKeyManagerImpl.TAG, "onBinderStateChanged is " + z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void assistCallback(int i, int i2, CarAssistPacket carAssistPacket) {
        Log.v(TAG, "AssistCallback propertyId:" + i);
        switch (i) {
            case DigitalHalId.ID_START_FOR_NOKEY_R /* 557846277 */:
                int startForNoKeyR = carAssistPacket.getStartForNoKeyR();
                Iterator<IDigitalKeyLisenter> it = this.mDigitalKeyListeners.iterator();
                while (it.hasNext()) {
                    it.next().onAllowStartTimeAfterUnlockChange(startForNoKeyR);
                }
                break;
            case DigitalHalId.ID_QUERY_OWNER_KEY_RESULT_SINGLE /* 557846280 */:
                int queryOwnerKeyResultSingle = carAssistPacket.getQueryOwnerKeyResultSingle();
                Iterator<IDigitalKeyLisenter> it2 = this.mDigitalKeyListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onOwnerKeyIdentifierResult(queryOwnerKeyResultSingle);
                }
                caseFeatureIds(3912, Integer.valueOf(carAssistPacket.getQueryOwnerKeyResultSingle()));
                break;
            case DigitalHalId.ID_QUERY_OWNER_KEY_RESULT_DOUBLE /* 557846281 */:
                int queryOwnerKeyResultDouble = carAssistPacket.getQueryOwnerKeyResultDouble();
                Iterator<IDigitalKeyLisenter> it3 = this.mDigitalKeyListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onOwnerKeyIdentifierResult(queryOwnerKeyResultDouble);
                }
                caseFeatureIds(4168, Integer.valueOf(carAssistPacket.getQueryOwnerKeyResultDouble()));
                break;
            case DigitalHalId.ID_FIND_KEY_RESULT_SINGLE /* 557846282 */:
                int findKeyResultSingle = carAssistPacket.getFindKeyResultSingle();
                Iterator<IDigitalKeyLisenter> it4 = this.mDigitalKeyListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onKeyFindingResults(findKeyResultSingle);
                }
                caseFeatureIds(4171, Integer.valueOf(carAssistPacket.getFindKeyResultSingle()));
                break;
            case DigitalHalId.ID_FIND_KEY_RESULT_DOUBLE /* 557846283 */:
                int findKeyResultDouble = carAssistPacket.getFindKeyResultDouble();
                Iterator<IDigitalKeyLisenter> it5 = this.mDigitalKeyListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onKeyFindingResults(findKeyResultDouble);
                }
                caseFeatureIds(4086, Integer.valueOf(carAssistPacket.getFindKeyResultDouble()));
                break;
            case DigitalHalId.ID_PAIRING_PROCESS_CALLBACK_SINGLE /* 557846284 */:
                int pairingProcessCallBackSingle = carAssistPacket.getPairingProcessCallBackSingle();
                Iterator<IDigitalKeyLisenter> it6 = this.mDigitalKeyListeners.iterator();
                while (it6.hasNext()) {
                    it6.next().onPairingProcessCallBack(pairingProcessCallBackSingle);
                }
                caseFeatureIds(4104, Integer.valueOf(carAssistPacket.getPairingProcessCallBackSingle()));
                break;
            case DigitalHalId.ID_PAIRING_PROCESS_CALLBACK_DOUBLE /* 557846285 */:
                int pairingProcessCallBackDouble = carAssistPacket.getPairingProcessCallBackDouble();
                Iterator<IDigitalKeyLisenter> it7 = this.mDigitalKeyListeners.iterator();
                while (it7.hasNext()) {
                    it7.next().onPairingProcessCallBack(pairingProcessCallBackDouble);
                }
                caseFeatureIds(4106, Integer.valueOf(carAssistPacket.getPairingProcessCallBackDouble()));
                break;
            case DigitalHalId.ID_PAIRING_RESULT_SINGLE /* 557846286 */:
                int pairingResultSingle = carAssistPacket.getPairingResultSingle();
                Iterator<IDigitalKeyLisenter> it8 = this.mDigitalKeyListeners.iterator();
                while (it8.hasNext()) {
                    it8.next().onOfflineOperateResult(pairingResultSingle);
                }
                caseFeatureIds(4107, Integer.valueOf(carAssistPacket.getPairingResultSingle()));
                break;
            case DigitalHalId.ID_PAIRING_RESULT_DOUBLE /* 557846287 */:
                int pairingResultDouble = carAssistPacket.getPairingResultDouble();
                Iterator<IDigitalKeyLisenter> it9 = this.mDigitalKeyListeners.iterator();
                while (it9.hasNext()) {
                    it9.next().onOfflineOperateResult(pairingResultDouble);
                }
                caseFeatureIds(4105, Integer.valueOf(carAssistPacket.getPairingResultDouble()));
                break;
            case DigitalHalId.ID_OFF_LINE_OPERAT_PAIRING_RESULTR /* 557846288 */:
                int offlineOperatPairingResult = carAssistPacket.getOfflineOperatPairingResult();
                Iterator<IDigitalKeyLisenter> it10 = this.mDigitalKeyListeners.iterator();
                while (it10.hasNext()) {
                    it10.next().onOfflineOperateCmd(offlineOperatPairingResult);
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

    public void deleteNfcKey(int i) {
        Log.v(TAG, "deleteNfcKey is execute");
        OtaSdkManager otaSdkManager = this.mOtaSdkManager;
        if (otaSdkManager == null) {
            Log.e(TAG, "mOtaSdkManager is null");
        } else {
            otaSdkManager.deleteNfcKey(i);
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

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bYDAutoEventValue.intArrayValue = new int[iArr.length];
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i : iArr) {
                if (i > 0) {
                    if (i == 1350) {
                        int nFCConfigurationInformation = getNFCConfigurationInformation();
                        bYDAutoEventValue.intValue = nFCConfigurationInformation;
                        sparseArray.put(1350, Integer.valueOf(nFCConfigurationInformation));
                    } else if (i != 3693) {
                        Log.d(TAG, "case fail ! please check out");
                    } else {
                        int ikConfigKeylessTime = getIkConfigKeylessTime();
                        bYDAutoEventValue.intValue = ikConfigKeylessTime;
                        sparseArray.put(3693, Integer.valueOf(ikConfigKeylessTime));
                    }
                    int i2 = 0;
                    for (int i3 : iArr) {
                        try {
                            bYDAutoEventValue.intArrayValue[i2] = ((Integer) sparseArray.get(i3)).intValue();
                        } catch (Exception unused) {
                            Log.e(TAG, "Feature id doesn't exist");
                        }
                        i2++;
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

    public int getKeyFindingResults() {
        Log.v(TAG, "getKeyFindingResults is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getKeyFindingResults();
        }
        Log.e(TAG, "adapter is not connect");
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

    public int getOwnerKeyIdentifierResult() {
        Log.v(TAG, "getOwnerKeyIdentifierResult is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getOwnerKeyIdentifierResult();
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

    public void registerListener(IDigitalKeyLisenter iDigitalKeyLisenter) {
        registerListener(iDigitalKeyLisenter, new int[0]);
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

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int i, Object obj) {
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
        if (i == 740) {
            setAllowStartTimeAfterUnlockOut(iIntValue);
            Log.v(TAG, "setAllowStartTimeAfterUnlockOut:" + iIntValue);
            return 1;
        }
        if (i == 755) {
            setRegisterKeyStartPairingInOut(iIntValue);
            Log.v(TAG, "setRegisterKeyStartPairing:" + iIntValue);
            return 1;
        }
        if (i == 782) {
            setQueryOwnersKeyInOut(iIntValue);
            Log.v(TAG, "setQueryOwnersKeyInOut:" + iIntValue);
            return 1;
        }
        if (i == 1799) {
            setAllowStartTimeAfterUnlockOut(iIntValue);
            return 1;
        }
        if (i == 4158) {
            setRegisterKeyStartPairingOut(iIntValue);
            Log.v(TAG, "setRegisterKeyStartPairingOut:" + iIntValue);
            return 1;
        }
        if (i != 4169) {
            Log.d(TAG, "case fail ! please check out");
            return -1;
        }
        setQueryOwnersKeyOut(iIntValue);
        Log.v(TAG, "setQueryOwnersKeyOut:" + iIntValue);
        return 1;
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
        Log.d(TAG, "unregisterListener is run. ");
        if (iDigitalKeyLisenter == null) {
            return;
        }
        if (this.mDigitalKeyListeners.contains(iDigitalKeyLisenter)) {
            this.mDigitalKeyListeners.remove(iDigitalKeyLisenter);
        }
        unregisterConnectListener(iDigitalKeyLisenter);
        if (this.mOtaSdkManager != null) {
            Log.d(TAG, "unregister TboxListener success!!!");
            this.mOtaSdkManager.unregisterTboxListener(this.bydTboxListener);
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
        if (this.mOtaSdkManager != null) {
            Log.d(TAG, "register TboxListener success!!!");
            this.mOtaSdkManager.registerTboxListener(this.bydTboxListener);
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

    public void queryKeyListReq(int i, int i2, int i3, String str, byte[] bArr) {
        Log.v(TAG, "queryKeyListReq is execute");
        OtaSdkManager otaSdkManager = this.mOtaSdkManager;
        if (otaSdkManager == null) {
            Log.e(TAG, "mOtaSdkManager is null");
        } else {
            otaSdkManager.queryKeyListReq(i, i2, i3, str, bArr);
        }
    }
}
