package android.hardware.bydauto_test;

import android.hardware.bydauto.light.AbsBYDAutoLightListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import androidx.exifinterface.media.ExifInterface;
import com.byd.automultipletheme.common.Const;

/* JADX INFO: loaded from: classes.dex */
public class TestLightManagerImplCallback {
    public static final int FIVE = 5;
    public static final int FOUR = 4;
    public static final int ONE = 1;
    public static final String SETAFSSWITCHSATE = "setAFSSwitchSate";
    public static final String SETAMBIENTMULTICOLORMODE = "setAmbientMulticolorMode";
    public static final String SETAMBIENTMULTICOLORSTATE = "setAmbientMulticolorState";
    public static final String SETAMBIENTMUSICMODE = "setAmbientMusicMode";
    public static final String SETAMBIENTMUSICMODESTATE = "setAmbientMusicModeState";
    public static final String SETDAYTIMELIGHTSTATE = "setDayTimeLightState";
    public static final String SETDOORLIGHTSTATE = "setDoorLightState";
    public static final String SETTURNLIGHTSFLASHSTATE = "setTurnLightsFlashState";
    public static final int SIX = 6;
    private static final String TAG = "TestLightManagerImplCallback";
    public static final int THREE = 3;
    public static final int TWO = 2;
    public static final int ZERO = 0;
    private AbsBYDAutoLightListener mAbsBYDAutoLightListener;
    private Handler mHandler;

    public void setAbsBYDAutoLightListener(AbsBYDAutoLightListener absBYDAutoLightListener) {
        if (absBYDAutoLightListener == null) {
            return;
        }
        this.mAbsBYDAutoLightListener = absBYDAutoLightListener;
    }

    public TestLightManagerImplCallback() {
        HandlerThread handlerThread = new HandlerThread(TAG);
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto_test.TestLightManagerImplCallback.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                TestLightManagerImplCallback.this.dispatchMessageCallBack(message);
            }
        };
    }

    public void enqueueMessage(String str, Object... objArr) {
        if (this.mHandler != null) {
            Bundle bundle = new Bundle();
            bundle.putString("what", str);
            for (int i = 0; i < objArr.length; i++) {
                if (objArr[i] instanceof Integer) {
                    bundle.putInt(String.valueOf(i + 1), ((Integer) objArr[i]).intValue());
                }
                if (objArr[i] instanceof Float) {
                    bundle.putFloat(String.valueOf(i + 1), ((Float) objArr[i]).floatValue());
                }
            }
            Message messageObtain = Message.obtain();
            messageObtain.obj = bundle;
            this.mHandler.sendMessage(messageObtain);
        }
    }

    public void dispatchMessageCallBack(Message message) {
        if (this.mAbsBYDAutoLightListener != null) {
            Bundle bundle = (Bundle) message.obj;
            if (bundle.get(Const.CarEv) == null) {
                bundle.putInt(Const.CarEv, -1);
            }
            if (bundle.get("2") == null) {
                bundle.putInt("2", -1);
            }
            if (bundle.get(ExifInterface.GPS_MEASUREMENT_3D) == null) {
                bundle.putInt(ExifInterface.GPS_MEASUREMENT_3D, -1);
            }
            if (bundle.get("4") == null) {
                bundle.putInt("4", -1);
            }
            if (bundle.get("5") == null) {
                bundle.putInt("5", -1);
            }
            if (bundle.get("6") == null) {
                bundle.putInt("6", -1);
            }
            Process(bundle, bundle.getString("what"));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x010c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void Process(android.os.Bundle r23, java.lang.String r24) {
        /*
            Method dump skipped, instruction units count: 302
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.hardware.bydauto_test.TestLightManagerImplCallback.Process(android.os.Bundle, java.lang.String):void");
    }

    private String nameChange(String str) {
        str.hashCode();
        switch (str) {
            case "setAmbientMusicMode":
                return "onAmbientMusicModechanged";
            case "setDayTimeLightState":
                return "onDayTimeLightChanged";
            case "setAmbientMusicModeState":
                return "onAmbientMusicModeState";
            case "setAmbientMulticolorState":
                return "onAmbientMulticolorStateChanged";
            case "setAmbientMulticolorMode":
                return "onAmbientMulticolorModeChanged";
            case "setDoorLightState":
                return "onDoorLightStateChanged";
            case "setAFSSwitchSate":
                return "onAFSSwitchStateChange";
            case "setTurnLightsFlashState":
                return "onTurnLightFlashState";
            default:
                return "";
        }
    }
}
