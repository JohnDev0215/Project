package android.hardware.bydauto.ota.tsimpl;

import android.hardware.IBYDAutoEvent;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.ota.AbsBYDAutoOtaListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import com.byd.vrassistant.Constant;

/* JADX INFO: loaded from: classes.dex */
public class TestOtaManagerImplCallback {
    public static final int ON7DFEXIST = 1073741853;
    public static final int ONBACKUPAREADOWNLOADSTATE = 1073741852;
    public static final int ONBATTERYPOWERVOLTAGECHANGED = 1073741837;
    public static final int ONBATTERYVOLTAGECHANGED = 1073741833;
    public static final int ONCANINFORECEIVED = 1073741836;
    public static final int ONDATACHANGED = 1073741843;
    public static final int ONDATAEVENTCHANGED = 1073741844;
    public static final int ONDCSTATE = 1073741849;
    public static final int ONDISCHARGEMAINCONTACTORSTATECHANGED = 1073741832;
    public static final int ONECUSOFTCODERECEIVED = 1073741827;
    public static final int ONECUVERSIONANDSOFTCODERECEIVED = 1073741828;
    public static final int ONECUVERSIONRECEIVED = 1073741826;
    public static final int ONERROR = 1073741842;
    public static final int ONFAULTCODEINFORECEIVED = 1073741838;
    public static final int ONIG3RELAYSTATE = 1073741851;
    public static final int ONLFDOORLOCKSTATECHANGED = 1073741835;
    public static final int ONLOCALDIAGSTATUSCHANGED = 1073741825;
    public static final int ONMCUVERSIONANDSOFTCODERECEIVED = 1073741829;
    public static final int ONNEEDRENDREGISTERTABLE = 1073741840;
    public static final int ONOTAINFOACKCHANGED = 1073741823;
    public static final int ONOTASTATECHANGED = 1073741830;
    public static final int ONOTATIMECOUNTSTATECHANGED = 1073741831;
    public static final int ONPOWEROFFINFORMCHANGED = 1073741824;
    public static final int ONPOWERONFORBIDSTATECHANGED = 1073741834;
    public static final int ONSMARTCHARGESTATE = 1073741850;
    public static final int ONSMARTPOWERSTATE = 1073741848;
    public static final int ONSOCVALUECHANGED = 1073741854;
    public static final int ONSYNCMCUSTATECHANGED = 1073741839;
    public static final int ONTURNOFFSTATE = 1073741847;
    public static final int ONUPGRADEPROGRESS = 1073741845;
    public static final int ONUPGRADERESULT = 1073741846;
    public static final int ONWHOLEFRAMEDATACHANGED = 1073741841;
    public static final int SET_BASE = 1073741823;
    protected static final String TAG = "TS.TestOtaManagerImplCallback";
    private AbsBYDAutoOtaListener mAbsBydAutoOtaListener;
    private Handler mHandler;

    public void setAbsBYDAutoOtaListener(AbsBYDAutoOtaListener absBYDAutoOtaListener) {
        Log.d(TAG, "setAbsBYDAutoOtaListener:" + absBYDAutoOtaListener);
        this.mAbsBydAutoOtaListener = absBYDAutoOtaListener;
    }

    public TestOtaManagerImplCallback() {
        Log.d(TAG, "TestOtaManagerImplCallback:");
        HandlerThread handlerThread = new HandlerThread("TestOtaManagerImplCallback");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                TestOtaManagerImplCallback.this.dispatchMessageCallBack(message);
            }
        };
    }

    private void enqueueMessage(int i, int i2, int i3, Object obj) {
        Log.d(TAG, "enqueueMessage:" + i + "/" + i2 + "/" + i3 + "/" + obj + "/" + (this.mHandler == null));
        if (this.mHandler != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            messageObtain.arg2 = i3;
            messageObtain.obj = obj;
            this.mHandler.sendMessage(messageObtain);
        }
    }

    public void enqueueMessage(int i, Object obj) {
        enqueueMessage(i, -1, -1, obj);
    }

    public void enqueueMessage(int i, int i2, Object obj) {
        enqueueMessage(i, i2, -1, obj);
    }

    public void dispatchMessageCallBack(Message message) {
        Log.d(TAG, "dispatchMessageCallBack:" + (this.mAbsBydAutoOtaListener == null));
        if (this.mAbsBydAutoOtaListener != null) {
            int i = message.what;
            int i2 = message.arg1;
            int i3 = message.arg2;
            Object obj = message.obj;
            switch (i) {
                case 1073741824:
                    this.mAbsBydAutoOtaListener.onPoweroffInformChanged(((Integer) obj).intValue());
                    break;
                case ONLOCALDIAGSTATUSCHANGED /* 1073741825 */:
                    this.mAbsBydAutoOtaListener.onLocalDiagStatusChanged(((Integer) obj).intValue());
                    break;
                case ONECUVERSIONRECEIVED /* 1073741826 */:
                    this.mAbsBydAutoOtaListener.onECUVersionReceived((byte[]) obj);
                    break;
                case ONECUSOFTCODERECEIVED /* 1073741827 */:
                    this.mAbsBydAutoOtaListener.onECUSoftcodeReceived((byte[]) obj);
                    break;
                case ONECUVERSIONANDSOFTCODERECEIVED /* 1073741828 */:
                    this.mAbsBydAutoOtaListener.onECUVersionAndSoftcodeReceived((byte[]) obj);
                    break;
                case ONMCUVERSIONANDSOFTCODERECEIVED /* 1073741829 */:
                    this.mAbsBydAutoOtaListener.onMCUVersionAndSoftcodeReceived((byte[]) obj);
                    break;
                case ONOTASTATECHANGED /* 1073741830 */:
                    this.mAbsBydAutoOtaListener.onOTAStateChanged(((Integer) obj).intValue());
                    break;
                case ONOTATIMECOUNTSTATECHANGED /* 1073741831 */:
                    this.mAbsBydAutoOtaListener.onOTATimecountStateChanged(((Integer) obj).intValue());
                    break;
                case ONDISCHARGEMAINCONTACTORSTATECHANGED /* 1073741832 */:
                    this.mAbsBydAutoOtaListener.onDischargeMainContactorStateChanged(((Integer) obj).intValue());
                    break;
                case ONBATTERYVOLTAGECHANGED /* 1073741833 */:
                    this.mAbsBydAutoOtaListener.onBatteryVoltageChanged(((Double) obj).doubleValue());
                    break;
                case ONPOWERONFORBIDSTATECHANGED /* 1073741834 */:
                    this.mAbsBydAutoOtaListener.onPowerOnForbidStateChanged(((Integer) obj).intValue());
                    break;
                case ONLFDOORLOCKSTATECHANGED /* 1073741835 */:
                    this.mAbsBydAutoOtaListener.onLFDoorLockStateChanged(((Integer) obj).intValue());
                    break;
                case ONCANINFORECEIVED /* 1073741836 */:
                    this.mAbsBydAutoOtaListener.onCanInfoReceived((byte[]) obj);
                    break;
                case ONBATTERYPOWERVOLTAGECHANGED /* 1073741837 */:
                    this.mAbsBydAutoOtaListener.onBatteryPowerVoltageChanged(((Double) obj).doubleValue());
                    break;
                case ONFAULTCODEINFORECEIVED /* 1073741838 */:
                    this.mAbsBydAutoOtaListener.onFaultCodeInfoReceived((byte[]) obj);
                    break;
                case ONSYNCMCUSTATECHANGED /* 1073741839 */:
                    this.mAbsBydAutoOtaListener.onSyncMcuStateChanged(((Integer) obj).intValue());
                    break;
                case ONNEEDRENDREGISTERTABLE /* 1073741840 */:
                    this.mAbsBydAutoOtaListener.onNeedRendRegisterTable(((Integer) obj).intValue());
                    break;
                case ONWHOLEFRAMEDATACHANGED /* 1073741841 */:
                    this.mAbsBydAutoOtaListener.onWholeFrameDataChanged((byte[]) obj);
                    break;
                case ONERROR /* 1073741842 */:
                    this.mAbsBydAutoOtaListener.onError(((Integer) obj).intValue(), Constant.VersionType_Test);
                    break;
                case ONDATACHANGED /* 1073741843 */:
                    this.mAbsBydAutoOtaListener.onDataChanged((IBYDAutoEvent) obj);
                    break;
                case ONDATAEVENTCHANGED /* 1073741844 */:
                    this.mAbsBydAutoOtaListener.onDataEventChanged(i2, (BYDAutoEventValue) obj);
                    break;
                case ONUPGRADEPROGRESS /* 1073741845 */:
                    byte[] bArr = (byte[]) obj;
                    this.mAbsBydAutoOtaListener.onUpgradeProgress(bArr[0], bArr[1]);
                    break;
                case ONUPGRADERESULT /* 1073741846 */:
                    byte[] bArr2 = (byte[]) obj;
                    this.mAbsBydAutoOtaListener.onUpgradeResult(bArr2[0], bArr2[1], bArr2[2]);
                    break;
                case ONTURNOFFSTATE /* 1073741847 */:
                    this.mAbsBydAutoOtaListener.onTurnOffState(((Integer) obj).intValue());
                    break;
                case ONSMARTPOWERSTATE /* 1073741848 */:
                    this.mAbsBydAutoOtaListener.onSmartPowerState(((Integer) obj).intValue());
                    break;
                case ONDCSTATE /* 1073741849 */:
                    this.mAbsBydAutoOtaListener.onDcState(((Integer) obj).intValue());
                    break;
                case ONSMARTCHARGESTATE /* 1073741850 */:
                    this.mAbsBydAutoOtaListener.onSmartChargeState(((Integer) obj).intValue());
                    break;
                case ONIG3RELAYSTATE /* 1073741851 */:
                    this.mAbsBydAutoOtaListener.onIg3RelayState(((Integer) obj).intValue());
                    break;
                case ONBACKUPAREADOWNLOADSTATE /* 1073741852 */:
                    this.mAbsBydAutoOtaListener.onBackupAreaDownloadState(((Integer) obj).intValue());
                    break;
                case ON7DFEXIST /* 1073741853 */:
                    this.mAbsBydAutoOtaListener.on7dfExistState(((Boolean) obj).booleanValue());
                    break;
                case 1073741854:
                    this.mAbsBydAutoOtaListener.onSocValueChanged(((Integer) obj).intValue());
                    break;
            }
        }
    }
}
