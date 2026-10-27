package android.hardware.bydauto.power.tsimpl;

import android.hardware.bydauto.power.AbsBYDAutoPowerListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class TestPowerManagerImplCallback {
    public static final int ONBATTERYLOWVOLTAGESTATECHANGED = 715827889;
    public static final int ONBATTERYREMAINPOWEREVCHANGED = 715827883;
    public static final int ONDATACHANGED = 715827891;
    public static final int ONDATAEVENTCHANGED = 715827892;
    public static final int ONERROR = 715827890;
    public static final int ONMCUSTATUSCHANGED = 715827887;
    public static final int ONPOWERCTLSTATUSCHANGED = 715827884;
    public static final int ONSHUTDOWNINFOCHANGED = 715827888;
    public static final int ONTFTBACKLIGHTCHANGED = 715827886;
    public static final int ONTPDISPLAYCONTROLLERCHANGED = 715827885;
    public static final int SET_BASE = 715827882;
    protected static final String TAG = "TS.TestPowerManagerImplCallback";
    private AbsBYDAutoPowerListener mAbsBYDAutoPowerListener;
    private Handler mHandler;

    public void setAbsBYDAutoPowerListener(AbsBYDAutoPowerListener listener) {
        Log.d(TAG, "setAbsBYDAutoPowerListener:" + listener);
        this.mAbsBYDAutoPowerListener = listener;
    }

    public TestPowerManagerImplCallback() {
        Log.d(TAG, "TestPowerManagerImplCallback:");
        HandlerThread handlerThread = new HandlerThread("TestPowerManagerImplCallback");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto.power.tsimpl.TestPowerManagerImplCallback.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                TestPowerManagerImplCallback.this.dispatchMessageCallBack(msg);
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
        sb.append(this.mAbsBYDAutoPowerListener == null);
        Log.d(TAG, sb.toString());
        if (this.mAbsBYDAutoPowerListener != null) {
            int what = msg.what;
            int i = msg.arg1;
            int i2 = msg.arg2;
            Object para1object = msg.obj;
            switch (what) {
                case ONBATTERYREMAINPOWEREVCHANGED /* 715827883 */:
                    this.mAbsBYDAutoPowerListener.onBatteryRemainPowerEVChanged(((Double) para1object).doubleValue());
                    break;
                case ONPOWERCTLSTATUSCHANGED /* 715827884 */:
                    byte[] mpara = (byte[]) para1object;
                    this.mAbsBYDAutoPowerListener.onPowerCtlStatusChanged(mpara[0], mpara[1]);
                    break;
                case ONTPDISPLAYCONTROLLERCHANGED /* 715827885 */:
                    byte[] mpara2 = (byte[]) para1object;
                    this.mAbsBYDAutoPowerListener.onTpDisplayControllerChanged(mpara2[0], mpara2[1]);
                    break;
                case ONTFTBACKLIGHTCHANGED /* 715827886 */:
                    this.mAbsBYDAutoPowerListener.onTftBacklightChanged(((Integer) para1object).intValue());
                    break;
                case ONMCUSTATUSCHANGED /* 715827887 */:
                    this.mAbsBYDAutoPowerListener.onMcuStatusChanged(((Integer) para1object).intValue());
                    break;
                case ONSHUTDOWNINFOCHANGED /* 715827888 */:
                    byte[] mpara3 = (byte[]) para1object;
                    this.mAbsBYDAutoPowerListener.onShutdownInfoChanged(mpara3[0], mpara3[1]);
                    break;
                case ONBATTERYLOWVOLTAGESTATECHANGED /* 715827889 */:
                    this.mAbsBYDAutoPowerListener.onBatteryLowVoltageStateChanged(((Integer) para1object).intValue());
                    break;
                case ONERROR /* 715827890 */:
                    this.mAbsBYDAutoPowerListener.onError(((Integer) para1object).intValue(), "test");
                    break;
                case ONDATACHANGED /* 715827891 */:
                    this.mAbsBYDAutoPowerListener.onDataChanged(null);
                    break;
                case ONDATAEVENTCHANGED /* 715827892 */:
                    this.mAbsBYDAutoPowerListener.onDataEventChanged(((Integer) para1object).intValue(), null);
                    break;
            }
        }
    }
}
