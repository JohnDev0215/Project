package android.hardware.bydauto_test;

import android.hardware.bydauto.light.AbsBYDAutoLightListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import defpackage.C0173a;
import java.lang.reflect.Method;

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
    private Handler mHandler = new Handler(C0173a.h(TAG).getLooper()) { // from class: android.hardware.bydauto_test.TestLightManagerImplCallback.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            TestLightManagerImplCallback.this.dispatchMessageCallBack(message);
        }
    };

    private void Process(Bundle bundle, String str) {
        int i;
        try {
            Method[] declaredMethods = AbsBYDAutoLightListener.class.getDeclaredMethods();
            int length = declaredMethods.length;
            int i2 = 0;
            int i3 = 0;
            while (i3 < length) {
                Method method = declaredMethods[i3];
                if (method.getName().equals(nameChange(str))) {
                    int parameterCount = method.getParameterCount();
                    if (parameterCount == 0) {
                        method.invoke(this.mAbsBYDAutoLightListener, new Object[i2]);
                    }
                    if (parameterCount == 1) {
                        AbsBYDAutoLightListener absBYDAutoLightListener = this.mAbsBYDAutoLightListener;
                        Object[] objArr = new Object[1];
                        objArr[i2] = bundle.get("1");
                        method.invoke(absBYDAutoLightListener, objArr);
                    }
                    if (parameterCount == 2) {
                        AbsBYDAutoLightListener absBYDAutoLightListener2 = this.mAbsBYDAutoLightListener;
                        Object[] objArr2 = new Object[2];
                        objArr2[i2] = bundle.get("1");
                        objArr2[1] = bundle.get("2");
                        method.invoke(absBYDAutoLightListener2, objArr2);
                    }
                    if (parameterCount == 3) {
                        AbsBYDAutoLightListener absBYDAutoLightListener3 = this.mAbsBYDAutoLightListener;
                        Object[] objArr3 = new Object[3];
                        objArr3[i2] = bundle.get("1");
                        objArr3[1] = bundle.get("2");
                        objArr3[2] = bundle.get("3");
                        method.invoke(absBYDAutoLightListener3, objArr3);
                    }
                    if (parameterCount == 4) {
                        AbsBYDAutoLightListener absBYDAutoLightListener4 = this.mAbsBYDAutoLightListener;
                        Object[] objArr4 = new Object[4];
                        objArr4[i2] = bundle.get("1");
                        objArr4[1] = bundle.get("2");
                        objArr4[2] = bundle.get("3");
                        objArr4[3] = bundle.get("4");
                        method.invoke(absBYDAutoLightListener4, objArr4);
                    }
                    if (parameterCount == 5) {
                        method.invoke(this.mAbsBYDAutoLightListener, bundle.get("1"), bundle.get("2"), bundle.get("3"), bundle.get("4"), bundle.get("5"));
                    }
                    if (parameterCount == 6) {
                        i = 0;
                        method.invoke(this.mAbsBYDAutoLightListener, bundle.get("1"), bundle.get("2"), bundle.get("3"), bundle.get("4"), bundle.get("5"), bundle.get("6"));
                    } else {
                        i = 0;
                    }
                } else {
                    i = i2;
                }
                i3++;
                i2 = i;
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private String nameChange(String str) {
        str.getClass();
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

    public void dispatchMessageCallBack(Message message) {
        if (this.mAbsBYDAutoLightListener != null) {
            Bundle bundle = (Bundle) message.obj;
            if (bundle.get("1") == null) {
                bundle.putInt("1", -1);
            }
            if (bundle.get("2") == null) {
                bundle.putInt("2", -1);
            }
            if (bundle.get("3") == null) {
                bundle.putInt("3", -1);
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

    public void setAbsBYDAutoLightListener(AbsBYDAutoLightListener absBYDAutoLightListener) {
        if (absBYDAutoLightListener == null) {
            return;
        }
        this.mAbsBYDAutoLightListener = absBYDAutoLightListener;
    }
}
