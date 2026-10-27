package android.hardware.bydauto.power.tsimpl;

import android.hardware.bydauto.power.AbsBYDAutoPowerListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import com.byd.vrassistant.Constant;

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

    public void setAbsBYDAutoPowerListener(AbsBYDAutoPowerListener absBYDAutoPowerListener) {
        Log.d(TAG, "setAbsBYDAutoPowerListener:" + absBYDAutoPowerListener);
        this.mAbsBYDAutoPowerListener = absBYDAutoPowerListener;
    }

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
        Log.d(TAG, "dispatchMessageCallBack:" + (this.mAbsBYDAutoPowerListener == null));
        if (this.mAbsBYDAutoPowerListener != null) {
            int i = message.what;
            int i2 = message.arg1;
            int i3 = message.arg2;
            Object obj = message.obj;
            switch (i) {
                case ONBATTERYREMAINPOWEREVCHANGED /* 715827883 */:
                    this.mAbsBYDAutoPowerListener.onBatteryRemainPowerEVChanged(((Double) obj).doubleValue());
                    break;
                case ONPOWERCTLSTATUSCHANGED /* 715827884 */:
                    byte[] bArr = (byte[]) obj;
                    this.mAbsBYDAutoPowerListener.onPowerCtlStatusChanged(bArr[0], bArr[1]);
                    break;
                case ONTPDISPLAYCONTROLLERCHANGED /* 715827885 */:
                    byte[] bArr2 = (byte[]) obj;
                    this.mAbsBYDAutoPowerListener.onTpDisplayControllerChanged(bArr2[0], bArr2[1]);
                    break;
                case ONTFTBACKLIGHTCHANGED /* 715827886 */:
                    this.mAbsBYDAutoPowerListener.onTftBacklightChanged(((Integer) obj).intValue());
                    break;
                case ONMCUSTATUSCHANGED /* 715827887 */:
                    this.mAbsBYDAutoPowerListener.onMcuStatusChanged(((Integer) obj).intValue());
                    break;
                case ONSHUTDOWNINFOCHANGED /* 715827888 */:
                    byte[] bArr3 = (byte[]) obj;
                    this.mAbsBYDAutoPowerListener.onShutdownInfoChanged(bArr3[0], bArr3[1]);
                    break;
                case ONBATTERYLOWVOLTAGESTATECHANGED /* 715827889 */:
                    this.mAbsBYDAutoPowerListener.onBatteryLowVoltageStateChanged(((Integer) obj).intValue());
                    break;
                case ONERROR /* 715827890 */:
                    this.mAbsBYDAutoPowerListener.onError(((Integer) obj).intValue(), Constant.VersionType_Test);
                    break;
                case ONDATACHANGED /* 715827891 */:
                    this.mAbsBYDAutoPowerListener.onDataChanged(null);
                    break;
                case ONDATAEVENTCHANGED /* 715827892 */:
                    this.mAbsBYDAutoPowerListener.onDataEventChanged(((Integer) obj).intValue(), null);
                    break;
            }
        }
    }
}
