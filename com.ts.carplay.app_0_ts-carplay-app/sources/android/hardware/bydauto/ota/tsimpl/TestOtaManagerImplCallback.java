package android.hardware.bydauto.ota.tsimpl;

import android.hardware.IBYDAutoEvent;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.ota.AbsBYDAutoOtaListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

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

    public void setAbsBYDAutoOtaListener(AbsBYDAutoOtaListener listener) {
        Log.d(TAG, "setAbsBYDAutoOtaListener:" + listener);
        this.mAbsBydAutoOtaListener = listener;
    }

    public TestOtaManagerImplCallback() {
        Log.d(TAG, "TestOtaManagerImplCallback:");
        HandlerThread handlerThread = new HandlerThread("TestOtaManagerImplCallback");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                TestOtaManagerImplCallback.this.dispatchMessageCallBack(msg);
            }
        };
    }

    private void enqueueMessage(int what, int arg1, int arg2, Object para1object) {
        StringBuilder sb = new StringBuilder();
        sb.append("enqueueMessage:");
        sb.append(what);
        sb.append("/");
        sb.append(arg1);
        sb.append("/");
        sb.append(arg2);
        sb.append("/");
        sb.append(para1object);
        sb.append("/");
        sb.append(this.mHandler == null);
        Log.d(TAG, sb.toString());
        if (this.mHandler != null) {
            Message message = Message.obtain();
            message.what = what;
            message.arg1 = arg1;
            message.arg2 = arg2;
            message.obj = para1object;
            this.mHandler.sendMessage(message);
        }
    }

    public void enqueueMessage(int what, Object para1object) {
        enqueueMessage(what, -1, -1, para1object);
    }

    public void enqueueMessage(int what, int arg1, Object para1object) {
        enqueueMessage(what, arg1, -1, para1object);
    }

    public void dispatchMessageCallBack(Message msg) {
        StringBuilder sb = new StringBuilder();
        sb.append("dispatchMessageCallBack:");
        sb.append(this.mAbsBydAutoOtaListener == null);
        Log.d(TAG, sb.toString());
        if (this.mAbsBydAutoOtaListener != null) {
            int what = msg.what;
            int arg1 = msg.arg1;
            int i = msg.arg2;
            Object para1object = msg.obj;
            switch (what) {
                case ONPOWEROFFINFORMCHANGED /* 1073741824 */:
                    this.mAbsBydAutoOtaListener.onPoweroffInformChanged(((Integer) para1object).intValue());
                    break;
                case ONLOCALDIAGSTATUSCHANGED /* 1073741825 */:
                    this.mAbsBydAutoOtaListener.onLocalDiagStatusChanged(((Integer) para1object).intValue());
                    break;
                case ONECUVERSIONRECEIVED /* 1073741826 */:
                    this.mAbsBydAutoOtaListener.onECUVersionReceived((byte[]) para1object);
                    break;
                case ONECUSOFTCODERECEIVED /* 1073741827 */:
                    this.mAbsBydAutoOtaListener.onECUSoftcodeReceived((byte[]) para1object);
                    break;
                case ONECUVERSIONANDSOFTCODERECEIVED /* 1073741828 */:
                    this.mAbsBydAutoOtaListener.onECUVersionAndSoftcodeReceived((byte[]) para1object);
                    break;
                case ONMCUVERSIONANDSOFTCODERECEIVED /* 1073741829 */:
                    this.mAbsBydAutoOtaListener.onMCUVersionAndSoftcodeReceived((byte[]) para1object);
                    break;
                case ONOTASTATECHANGED /* 1073741830 */:
                    this.mAbsBydAutoOtaListener.onOTAStateChanged(((Integer) para1object).intValue());
                    break;
                case ONOTATIMECOUNTSTATECHANGED /* 1073741831 */:
                    this.mAbsBydAutoOtaListener.onOTATimecountStateChanged(((Integer) para1object).intValue());
                    break;
                case ONDISCHARGEMAINCONTACTORSTATECHANGED /* 1073741832 */:
                    this.mAbsBydAutoOtaListener.onDischargeMainContactorStateChanged(((Integer) para1object).intValue());
                    break;
                case ONBATTERYVOLTAGECHANGED /* 1073741833 */:
                    this.mAbsBydAutoOtaListener.onBatteryVoltageChanged(((Double) para1object).doubleValue());
                    break;
                case ONPOWERONFORBIDSTATECHANGED /* 1073741834 */:
                    this.mAbsBydAutoOtaListener.onPowerOnForbidStateChanged(((Integer) para1object).intValue());
                    break;
                case ONLFDOORLOCKSTATECHANGED /* 1073741835 */:
                    this.mAbsBydAutoOtaListener.onLFDoorLockStateChanged(((Integer) para1object).intValue());
                    break;
                case ONCANINFORECEIVED /* 1073741836 */:
                    this.mAbsBydAutoOtaListener.onCanInfoReceived((byte[]) para1object);
                    break;
                case ONBATTERYPOWERVOLTAGECHANGED /* 1073741837 */:
                    this.mAbsBydAutoOtaListener.onBatteryPowerVoltageChanged(((Double) para1object).doubleValue());
                    break;
                case ONFAULTCODEINFORECEIVED /* 1073741838 */:
                    this.mAbsBydAutoOtaListener.onFaultCodeInfoReceived((byte[]) para1object);
                    break;
                case ONSYNCMCUSTATECHANGED /* 1073741839 */:
                    this.mAbsBydAutoOtaListener.onSyncMcuStateChanged(((Integer) para1object).intValue());
                    break;
                case ONNEEDRENDREGISTERTABLE /* 1073741840 */:
                    this.mAbsBydAutoOtaListener.onNeedRendRegisterTable(((Integer) para1object).intValue());
                    break;
                case ONWHOLEFRAMEDATACHANGED /* 1073741841 */:
                    this.mAbsBydAutoOtaListener.onWholeFrameDataChanged((byte[]) para1object);
                    break;
                case ONERROR /* 1073741842 */:
                    this.mAbsBydAutoOtaListener.onError(((Integer) para1object).intValue(), "test");
                    break;
                case ONDATACHANGED /* 1073741843 */:
                    this.mAbsBydAutoOtaListener.onDataChanged((IBYDAutoEvent) para1object);
                    break;
                case ONDATAEVENTCHANGED /* 1073741844 */:
                    this.mAbsBydAutoOtaListener.onDataEventChanged(arg1, (BYDAutoEventValue) para1object);
                    break;
                case ONUPGRADEPROGRESS /* 1073741845 */:
                    byte[] mpara = (byte[]) para1object;
                    this.mAbsBydAutoOtaListener.onUpgradeProgress(mpara[0], mpara[1]);
                    break;
                case ONUPGRADERESULT /* 1073741846 */:
                    byte[] mpara2 = (byte[]) para1object;
                    this.mAbsBydAutoOtaListener.onUpgradeResult(mpara2[0], mpara2[1], mpara2[2]);
                    break;
                case ONTURNOFFSTATE /* 1073741847 */:
                    this.mAbsBydAutoOtaListener.onTurnOffState(((Integer) para1object).intValue());
                    break;
                case ONSMARTPOWERSTATE /* 1073741848 */:
                    this.mAbsBydAutoOtaListener.onSmartPowerState(((Integer) para1object).intValue());
                    break;
                case ONDCSTATE /* 1073741849 */:
                    this.mAbsBydAutoOtaListener.onDcState(((Integer) para1object).intValue());
                    break;
                case ONSMARTCHARGESTATE /* 1073741850 */:
                    this.mAbsBydAutoOtaListener.onSmartChargeState(((Integer) para1object).intValue());
                    break;
                case ONIG3RELAYSTATE /* 1073741851 */:
                    this.mAbsBydAutoOtaListener.onIg3RelayState(((Integer) para1object).intValue());
                    break;
                case ONBACKUPAREADOWNLOADSTATE /* 1073741852 */:
                    this.mAbsBydAutoOtaListener.onBackupAreaDownloadState(((Integer) para1object).intValue());
                    break;
                case ON7DFEXIST /* 1073741853 */:
                    this.mAbsBydAutoOtaListener.on7dfExistState(((Boolean) para1object).booleanValue());
                    break;
                case 1073741854:
                    this.mAbsBydAutoOtaListener.onSocValueChanged(((Integer) para1object).intValue());
                    break;
            }
        }
    }
}
