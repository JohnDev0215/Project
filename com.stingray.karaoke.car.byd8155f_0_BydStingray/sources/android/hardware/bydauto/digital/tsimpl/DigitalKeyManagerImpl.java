package android.hardware.bydauto.digital.tsimpl;

import M5.b;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.ac.tsimpl.a;
import android.hardware.bydauto.digital.IDigitalKeyLisenter;
import android.util.Log;
import android.util.SparseArray;
import com.ts.lib.caradapter.assist.CarAssistPacket;
import com.ts.lib.caradapter.assist.OnAssistChangedListener;
import com.ts.ota.otasdkmgr.DigitalKeyBeanSdk;
import com.ts.ota.otasdkmgr.IAbsBYDTBoxListener;
import com.ts.ota.otasdkmgr.OtaSdkManager;
import h6.n;
import java.util.ArrayList;
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
    private final List<IDigitalKeyTboxListener> mTboxListeners = new ArrayList();
    IAbsBYDTBoxListener.Stub bydTboxListener = new IAbsBYDTBoxListener.Stub() { // from class: android.hardware.bydauto.digital.tsimpl.DigitalKeyManagerImpl.2
        public void onAppActivateResult(int i7, int i10) {
        }

        public void onDeleteAllNfcKeyResult(int i7, int i10) {
            n.t(DigitalKeyManagerImpl.TAG, "onDeleteAllNfcKeyResult: type->" + i7 + " result->" + i10);
            Iterator it = DigitalKeyManagerImpl.this.mTboxListeners.iterator();
            while (it.hasNext()) {
                ((IDigitalKeyTboxListener) it.next()).onDeleteAllNfcKeyResult(i7, i10);
            }
        }

        public void onDeleteNfcKeyByKeyNoResult(String str, int i7) {
            n.t(DigitalKeyManagerImpl.TAG, "onDeleteNfcKeyByKeyNoResult: keyNo->" + str + " result->" + i7);
            Iterator it = DigitalKeyManagerImpl.this.mTboxListeners.iterator();
            while (it.hasNext()) {
                ((IDigitalKeyTboxListener) it.next()).onDeleteNfcKeyByKeyNoResult(str, i7);
            }
        }

        public void onDeleteNfcKeyResult(int i7, int i10) {
        }

        public void onDeleteOwnIphoneKeyResult(String str, int i7) {
            n.t(DigitalKeyManagerImpl.TAG, "onDeleteOwnIphoneKeyResult: keyNo->" + str + " result->" + i7);
            Iterator it = DigitalKeyManagerImpl.this.mTboxListeners.iterator();
            while (it.hasNext()) {
                ((IDigitalKeyTboxListener) it.next()).onDeleteOwnIphoneKeyResult(str, i7);
            }
        }

        public void onKeyListPageRefresh() {
            n.t(DigitalKeyManagerImpl.TAG, "onKeyListPageRefresh");
            Iterator it = DigitalKeyManagerImpl.this.mTboxListeners.iterator();
            while (it.hasNext()) {
                ((IDigitalKeyTboxListener) it.next()).onKeyListPageRefresh();
            }
        }

        public void onKeyListResponse(int i7, List<DigitalKeyBeanSdk> list) {
            n.t(DigitalKeyManagerImpl.TAG, "onKeyListResponse: resp is " + list);
            ArrayList arrayList = new ArrayList();
            for (DigitalKeyBeanSdk digitalKeyBeanSdk : list) {
                DigitalKeyBean digitalKeyBean = new DigitalKeyBean();
                digitalKeyBean.init(digitalKeyBeanSdk.generateForApi());
                arrayList.add(digitalKeyBean);
            }
            Iterator it = DigitalKeyManagerImpl.this.mTboxListeners.iterator();
            while (it.hasNext()) {
                ((IDigitalKeyTboxListener) it.next()).onKeyListResponse(i7, arrayList);
            }
        }

        public void onMobileNetworkStateChanged(int i7) {
        }

        public void onNfcOperationCommand(int i7) {
            a.r("onNfcOperationCommand: cmd->", i7, DigitalKeyManagerImpl.TAG);
            Iterator it = DigitalKeyManagerImpl.this.mDigitalKeyListeners.iterator();
            while (it.hasNext()) {
                ((IDigitalKeyLisenter) it.next()).onNfcOperationCommand(i7);
            }
        }
    };

    public DigitalKeyManagerImpl(Context context) {
        this.mContext = context;
        this.mTsManagerImpl = TsManagerImpl.getInstance(context);
        this.mOtaSdkManager = OtaSdkManager.getInstance(context);
        this.mTsManagerImpl.setOnAssistChangedListener(new OnAssistChangedListener() { // from class: android.hardware.bydauto.digital.tsimpl.DigitalKeyManagerImpl.1
            public void onAssistChanged(int i7, int i10, CarAssistPacket carAssistPacket) {
                Log.v(DigitalKeyManagerImpl.TAG, DigitalKeyManagerImpl.TAG + carAssistPacket.toString());
                if (DigitalKeyManagerImpl.this.mDigitalKeyListeners.isEmpty()) {
                    return;
                }
                DigitalKeyManagerImpl.this.assistCallback(i7, i10, carAssistPacket);
            }

            public void onBinderStateChanged(boolean z6) {
                Log.d(DigitalKeyManagerImpl.TAG, "onBinderStateChanged is " + z6);
            }
        });
        this.mOtaSdkManager.registerTboxListener(this.bydTboxListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void assistCallback(int i7, int i10, CarAssistPacket carAssistPacket) {
        b.o("AssistCallback propertyId:", i7, TAG);
        if (i7 == 557846277) {
            int startForNoKeyR = carAssistPacket.getStartForNoKeyR();
            Iterator<IDigitalKeyLisenter> it = this.mDigitalKeyListeners.iterator();
            while (it.hasNext()) {
                it.next().onAllowStartTimeAfterUnlockChange(startForNoKeyR);
            }
            return;
        }
        if (i7 == 557846293) {
            int responseFeedbackInformR = carAssistPacket.getResponseFeedbackInformR();
            Iterator<IDigitalKeyLisenter> it2 = this.mDigitalKeyListeners.iterator();
            while (it2.hasNext()) {
                it2.next().onResponseFeedbackInform(responseFeedbackInformR);
            }
            return;
        }
        if (i7 == 557846297) {
            int padOfflineOperationRespR = carAssistPacket.getPadOfflineOperationRespR();
            Iterator<IDigitalKeyLisenter> it3 = this.mDigitalKeyListeners.iterator();
            while (it3.hasNext()) {
                it3.next().onPadOfflineOperationResp(padOfflineOperationRespR);
            }
            return;
        }
        switch (i7) {
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
        }
    }

    public static DigitalKeyManagerImpl getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (DigitalKeyManagerImpl.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new DigitalKeyManagerImpl(context);
                    }
                } finally {
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
        n.j(TAG, "adapter is not connect");
        return false;
    }

    public void deleteNfcKey(int i7) {
        Log.v(TAG, "deleteNfcKey is execute");
        OtaSdkManager otaSdkManager = this.mOtaSdkManager;
        if (otaSdkManager == null) {
            n.j(TAG, "mOtaSdkManager is null");
        } else {
            otaSdkManager.deleteNfcKey(i7);
        }
    }

    public void deleteOwnIphoneKey(int i7, String str) {
        Log.v(TAG, "delete iphone nfckey is execute");
        OtaSdkManager otaSdkManager = this.mOtaSdkManager;
        if (otaSdkManager == null) {
            n.j(TAG, "mOtaSdkManager is null");
        } else {
            otaSdkManager.deleteOwnIphoneKey(i7, str);
        }
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bYDAutoEventValue.intArrayValue = new int[iArr.length];
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i7 : iArr) {
                if (i7 > 0) {
                    if (i7 == 1350) {
                        int nFCConfigurationInformation = getNFCConfigurationInformation();
                        bYDAutoEventValue.intValue = nFCConfigurationInformation;
                        sparseArray.put(1350, Integer.valueOf(nFCConfigurationInformation));
                    } else if (i7 != 3693) {
                        Log.i(TAG, "case fail ! please check out");
                    } else {
                        int ikConfigKeylessTime = getIkConfigKeylessTime();
                        bYDAutoEventValue.intValue = ikConfigKeylessTime;
                        sparseArray.put(3693, Integer.valueOf(ikConfigKeylessTime));
                    }
                    int i10 = 0;
                    for (int i11 : iArr) {
                        try {
                            bYDAutoEventValue.intArrayValue[i10] = ((Integer) sparseArray.get(i11)).intValue();
                        } catch (Exception unused) {
                            n.j(TAG, "Feature id doesn't exist");
                        }
                        i10++;
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
        n.j(TAG, "adapter is not connect");
        return 0;
    }

    public int getKeyFindingResults(int i7) {
        b.o("getKeyFindingResults is execute, config is ", i7, TAG);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            n.j(TAG, "adapter is not connect");
            return 0;
        }
        if (i7 == 0) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getOwnerKeyIdentifierResultSingle();
        }
        if (i7 == 1) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getKeyFindingResults();
        }
        return 0;
    }

    public int getNFCConfigurationInformation() {
        Log.v(TAG, "getNFCConfigurationInformation is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getNFCConfigurationInformation();
        }
        n.j(TAG, "adapter is not connect");
        return 0;
    }

    public int getOperateCmd() {
        Log.v(TAG, "getPairingProcess is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getOperateCmd();
        }
        n.j(TAG, "adapter is not connect");
        return 0;
    }

    public int getOperateResult() {
        Log.v(TAG, "getPairingProcess is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getOperateResult();
        }
        n.j(TAG, "adapter is not connect");
        return 0;
    }

    public int getOperationCommand19C33() {
        Log.v(TAG, "getOperationCommand19C33 is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getOperationCommand19C33();
        }
        n.j(TAG, "adapter is not connect");
        return 0;
    }

    public int getOperationResult19C33() {
        Log.v(TAG, "getOperationResult19C33 is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getOperationResult19C33();
        }
        n.j(TAG, "adapter is not connect");
        return 0;
    }

    public int getOperationType19C33() {
        Log.v(TAG, "getOperationType19C33 is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getOperationType19C33();
        }
        n.j(TAG, "adapter is not connect");
        return 0;
    }

    public int getOwnerKeyIdentifierResult(int i7) {
        b.o("getOwnerKeyIdentifierResult is execute, config is ", i7, TAG);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            n.j(TAG, "adapter is not connect");
            return 0;
        }
        if (i7 == 0) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getQueryOwnerKeyResultSingle();
        }
        if (i7 == 1) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getOwnerKeyIdentifierResult();
        }
        return 0;
    }

    public int getPadOfflineOperationResp() {
        Log.v(TAG, "getPadOfflineOperationResp is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getPadOfflineOperationResp();
        }
        n.j(TAG, "adapter is not connect");
        return 0;
    }

    public int getPairingProcess() {
        Log.v(TAG, "getPairingProcess is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getPairingProcess();
        }
        n.j(TAG, "adapter is not connect");
        return 0;
    }

    public int getResponseFeedbackInform() {
        Log.v(TAG, "getResponseFeedbackInform is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mTsManagerImpl.getCarAssistAdapterManager().getResponseFeedbackInform();
        }
        n.j(TAG, "adapter is not connect");
        return 0;
    }

    public boolean offOnlineRemoveKey97V2(byte[] bArr) {
        Log.v(TAG, "offOnlineRemoveKey97V2 is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mOtaSdkManager.offOnlineRemoveKey97V2(bArr);
        }
        n.j(TAG, "adapter is not connect");
        return false;
    }

    public boolean offOnlineRemoveKey98V2(byte[] bArr) {
        Log.v(TAG, "offOnlineRemoveKey98V2 is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mOtaSdkManager.offOnlineRemoveKey98V2(bArr);
        }
        n.j(TAG, "adapter is not connect");
        return false;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
        Iterator<IDigitalKeyLisenter> it = this.mDigitalKeyListeners.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i7, bYDAutoEventValue);
        }
    }

    public void queryKeyListReq(int i7, int i10, int i11, String str, String str2) {
        Log.v(TAG, "queryKeyListReq is execute");
        OtaSdkManager otaSdkManager = this.mOtaSdkManager;
        if (otaSdkManager == null) {
            n.j(TAG, "mOtaSdkManager is null");
        } else {
            otaSdkManager.queryKeyListReq(i7, i10, i11, str, str2);
        }
    }

    public void registerListener(IDigitalKeyLisenter iDigitalKeyLisenter) {
        registerListener(iDigitalKeyLisenter, new int[0]);
    }

    public void registerTboxListener(IDigitalKeyTboxListener iDigitalKeyTboxListener) {
        n.t(TAG, "registerTboxListener is run " + iDigitalKeyTboxListener);
        synchronized (this.mTboxListeners) {
            try {
                if (!this.mTboxListeners.contains(iDigitalKeyTboxListener)) {
                    n.t(TAG, "registerTboxListener: listener->" + iDigitalKeyTboxListener);
                    this.mTboxListeners.add(iDigitalKeyTboxListener);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean setAllowStartTimeAfterUnlock(int i7, int i10) {
        Log.v(TAG, "setAllowStartTimeAfterUnlockOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mOtaSdkManager.setAllowStartTimeAfterUnlock(i7, i10);
        }
        n.j(TAG, "adapter is not connect");
        return false;
    }

    public void setAllowStartTimeAfterUnlockInOut(int i7) {
        Log.v(TAG, "setAllowStartTimeAfterUnlockInOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            n.j(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().setAllowStartTimeAfterUnlockInOut(i7);
        }
    }

    public void setAllowStartTimeAfterUnlockOut(int i7) {
        Log.v(TAG, "setAllowStartTimeAfterUnlockOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            n.j(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().setAllowStartTimeAfterUnlockOut(i7);
        }
    }

    public boolean setAllowStartTimeAfterUnlockV2(byte[] bArr) {
        Log.v(TAG, "setAllowStartTimeAfterUnlockV2 is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mOtaSdkManager.setAllowStartTimeAfterUnlockV2(bArr);
        }
        n.j(TAG, "adapter is not connect");
        return false;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public int setForOneFeatureId(int i7, Object obj) {
        int iIntValue;
        if (obj instanceof Integer) {
            iIntValue = ((Integer) obj).intValue();
        } else {
            if (obj instanceof Float) {
                ((Float) obj).getClass();
            } else if (obj instanceof Double) {
                ((Double) obj).getClass();
            } else if (!(obj instanceof byte[])) {
                Log.d(TAG, "the type of value is not a suitable type");
                return -1;
            }
            iIntValue = 0;
        }
        if (i7 == 740) {
            setAllowStartTimeAfterUnlock(0, iIntValue);
            Log.v(TAG, "setAllowStartTimeAfterUnlock:" + iIntValue);
        } else if (i7 == 755) {
            setRegisterKeyStartPairing(1, iIntValue);
            Log.v(TAG, "setRegisterKeyStartPairing:" + iIntValue);
        } else if (i7 == 782) {
            setQueryOwnersKey(1, iIntValue);
            Log.v(TAG, "setQueryOwnersKey:" + iIntValue);
        } else if (i7 == 1799) {
            setAllowStartTimeAfterUnlock(1, iIntValue);
        } else if (i7 == 4158) {
            setRegisterKeyStartPairing(0, iIntValue);
            Log.v(TAG, "setRegisterKeyStartPairing:" + iIntValue);
        } else {
            if (i7 != 4169) {
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
        n.j(TAG, "adapter is not connect");
        return -1;
    }

    public int setKeyIdTwo(byte[] bArr) {
        Log.v(TAG, "setKeyIdTwo byte[] is " + bArr);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mOtaSdkManager.setKeyIdTwo(bArr);
        }
        n.j(TAG, "adapter is not connect");
        return -1;
    }

    public int setOperationCmdAndKeyType(int i7, int i10) {
        Log.v(TAG, "setOperationCmdAndKeyType cmd is " + i7 + ",type is " + i10);
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mOtaSdkManager.setOperationCmdAndKeyType(i7, i10);
        }
        n.j(TAG, "adapter is not connect");
        return -1;
    }

    public boolean setQueryOwnersKey(int i7, int i10) {
        Log.v(TAG, "setQueryOwnersKeyInOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mOtaSdkManager.setQueryOwnersKey(i7, i10);
        }
        n.j(TAG, "adapter is not connect");
        return false;
    }

    public void setQueryOwnersKeyInOut(int i7) {
        Log.v(TAG, "setQueryOwnersKeyInOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            n.j(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().setQueryOwnersKeyInOut(i7);
        }
    }

    public void setQueryOwnersKeyOut(int i7) {
        Log.v(TAG, "setQueryOwnersKeyOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            n.j(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().setQueryOwnersKeyOut(i7);
        }
    }

    public boolean setQueryOwnersKeyV2(byte[] bArr) {
        Log.v(TAG, "setQueryOwnersKeyV2 is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mOtaSdkManager.setQueryOwnersKeyV2(bArr);
        }
        n.j(TAG, "adapter is not connect");
        return false;
    }

    public boolean setRegisterKeyStartPairing(int i7, int i10) {
        Log.v(TAG, "setRegisterKeyStartPairingOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mOtaSdkManager.setRegisterKeyStartPairing(i7, i10);
        }
        n.j(TAG, "adapter is not connect");
        return false;
    }

    public void setRegisterKeyStartPairingInOut(int i7) {
        Log.v(TAG, "setRegisterKeyStartPairingInOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            n.j(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().setRegisterKeyStartPairingInOut(i7);
        }
    }

    public void setRegisterKeyStartPairingOut(int i7) {
        Log.v(TAG, "setRegisterKeyStartPairingOut is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() == null) {
            n.j(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarAssistAdapterManager().setRegisterKeyStartPairingOut(i7);
        }
    }

    public boolean setRegisterKeyStartPairingV2(byte[] bArr) {
        Log.v(TAG, "setRegisterKeyStartPairingV2 is execute");
        if (this.mTsManagerImpl.getCarAssistAdapterManager() != null) {
            return this.mOtaSdkManager.setRegisterKeyStartPairingV2(bArr);
        }
        n.j(TAG, "adapter is not connect");
        return false;
    }

    public void startRegisterNFCKey() {
        Log.v(TAG, "startRegisterNFCKey is execute");
        OtaSdkManager otaSdkManager = this.mOtaSdkManager;
        if (otaSdkManager == null) {
            n.j(TAG, "mOtaSdkManager is null");
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
        n.t(TAG, "unregisterTboxListener is run " + iDigitalKeyTboxListener);
        synchronized (this.mTboxListeners) {
            try {
                if (this.mTboxListeners.contains(iDigitalKeyTboxListener)) {
                    n.t(TAG, "unregisterTboxListener: listener->" + iDigitalKeyTboxListener);
                    this.mTboxListeners.remove(iDigitalKeyTboxListener);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void uploadNfcOpenInfo() {
        Log.v(TAG, "uploadNfcOpenInfo is execute");
        OtaSdkManager otaSdkManager = this.mOtaSdkManager;
        if (otaSdkManager == null) {
            n.j(TAG, "mOtaSdkManager is null");
        } else {
            otaSdkManager.uploadNfcOpenInfo();
        }
    }

    public void registerListener(IDigitalKeyLisenter iDigitalKeyLisenter, int[] iArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mContext.getPackageName());
        sb.append(" byd_subscribe digital listener ");
        sb.append(iDigitalKeyLisenter);
        sb.append(" featureIDs = ");
        b.q(iArr, sb, TAG);
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

    public void deleteNfcKey(int i7, String str) {
        Log.v(TAG, "delete single nfckey is execute");
        OtaSdkManager otaSdkManager = this.mOtaSdkManager;
        if (otaSdkManager == null) {
            n.j(TAG, "mOtaSdkManager is null");
        } else {
            otaSdkManager.deleteSingleNfcKey(i7, str);
        }
    }

    public void queryKeyListReq(int i7, int i10, int i11, String str, byte[] bArr) {
        Log.v(TAG, "queryKeyListReq is execute");
        OtaSdkManager otaSdkManager = this.mOtaSdkManager;
        if (otaSdkManager == null) {
            n.j(TAG, "mOtaSdkManager is null");
        } else {
            otaSdkManager.queryKeyListReq(i7, i10, i11, str, bArr);
        }
    }
}
