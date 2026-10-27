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

    public TestPowerManagerImplCallback() {
        Log.d(TAG, "TestPowerManagerImplCallback:");
        HandlerThread handlerThread = new HandlerThread("TestPowerManagerImplCallback");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto.power.tsimpl.TestPowerManagerImplCallback.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                TestPowerManagerImplCallback.this.dispatchMessageCallBack(message);
            }
        };
    }

    private void enqueueMessage(int i, int i2, int i3, Object obj) {
        StringBuilder sb = new StringBuilder();
        sb.append("enqueueMessage:");
        sb.append(i);
        sb.append("/");
        sb.append(i2);
        sb.append("/");
        sb.append(i3);
        sb.append("/");
        sb.append(obj);
        sb.append("/");
        sb.append(this.mHandler == null);
        Log.d(TAG, sb.toString());
        if (this.mHandler != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            messageObtain.arg2 = i3;
            messageObtain.obj = obj;
            this.mHandler.sendMessage(messageObtain);
        }
    }

    public void dispatchMessageCallBack(Message message) {
        StringBuilder sb = new StringBuilder();
        sb.append("dispatchMessageCallBack:");
        sb.append(this.mAbsBYDAutoPowerListener == null);
        Log.d(TAG, sb.toString());
        AbsBYDAutoPowerListener absBYDAutoPowerListener = this.mAbsBYDAutoPowerListener;
        if (absBYDAutoPowerListener != null) {
            int i = message.what;
            Object obj = message.obj;
            switch (i) {
                case ONBATTERYREMAINPOWEREVCHANGED /* 715827883 */:
                    absBYDAutoPowerListener.onBatteryRemainPowerEVChanged(((Double) obj).doubleValue());
                    break;
                case ONPOWERCTLSTATUSCHANGED /* 715827884 */:
                    byte[] bArr = (byte[]) obj;
                    absBYDAutoPowerListener.onPowerCtlStatusChanged(bArr[0], bArr[1]);
                    break;
                case ONTPDISPLAYCONTROLLERCHANGED /* 715827885 */:
                    byte[] bArr2 = (byte[]) obj;
                    absBYDAutoPowerListener.onTpDisplayControllerChanged(bArr2[0], bArr2[1]);
                    break;
                case ONTFTBACKLIGHTCHANGED /* 715827886 */:
                    absBYDAutoPowerListener.onTftBacklightChanged(((Integer) obj).intValue());
                    break;
                case ONMCUSTATUSCHANGED /* 715827887 */:
                    absBYDAutoPowerListener.onMcuStatusChanged(((Integer) obj).intValue());
                    break;
                case ONSHUTDOWNINFOCHANGED /* 715827888 */:
                    byte[] bArr3 = (byte[]) obj;
                    absBYDAutoPowerListener.onShutdownInfoChanged(bArr3[0], bArr3[1]);
                    break;
                case ONBATTERYLOWVOLTAGESTATECHANGED /* 715827889 */:
                    absBYDAutoPowerListener.onBatteryLowVoltageStateChanged(((Integer) obj).intValue());
                    break;
                case ONERROR /* 715827890 */:
                    absBYDAutoPowerListener.onError(((Integer) obj).intValue(), "test");
                    break;
                case ONDATACHANGED /* 715827891 */:
                    absBYDAutoPowerListener.onDataChanged(null);
                    break;
                case ONDATAEVENTCHANGED /* 715827892 */:
                    absBYDAutoPowerListener.onDataEventChanged(((Integer) obj).intValue(), null);
                    break;
            }
        }
    }

    public void enqueueMessage(int i, int i2, Object obj) {
        enqueueMessage(i, i2, -1, obj);
    }

    public void enqueueMessage(int i, Object obj) {
        enqueueMessage(i, -1, -1, obj);
    }

    public void setAbsBYDAutoPowerListener(AbsBYDAutoPowerListener absBYDAutoPowerListener) {
        Log.d(TAG, "setAbsBYDAutoPowerListener:" + absBYDAutoPowerListener);
        this.mAbsBYDAutoPowerListener = absBYDAutoPowerListener;
    }
}
