package android.hardware.bydauto_test;

import android.hardware.bydauto.light.AbsBYDAutoLightListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
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
            if (bundle.get("1") == null) {
                bundle.putInt("1", -1);
            }
            if (bundle.get(ExifInterface.GPS_MEASUREMENT_2D) == null) {
                bundle.putInt(ExifInterface.GPS_MEASUREMENT_2D, -1);
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
                        objArr2[1] = bundle.get(ExifInterface.GPS_MEASUREMENT_2D);
                        method.invoke(absBYDAutoLightListener2, objArr2);
                    }
                    if (parameterCount == 3) {
                        AbsBYDAutoLightListener absBYDAutoLightListener3 = this.mAbsBYDAutoLightListener;
                        Object[] objArr3 = new Object[3];
                        objArr3[i2] = bundle.get("1");
                        objArr3[1] = bundle.get(ExifInterface.GPS_MEASUREMENT_2D);
                        objArr3[2] = bundle.get(ExifInterface.GPS_MEASUREMENT_3D);
                        method.invoke(absBYDAutoLightListener3, objArr3);
                    }
                    if (parameterCount == 4) {
                        AbsBYDAutoLightListener absBYDAutoLightListener4 = this.mAbsBYDAutoLightListener;
                        Object[] objArr4 = new Object[4];
                        objArr4[i2] = bundle.get("1");
                        objArr4[1] = bundle.get(ExifInterface.GPS_MEASUREMENT_2D);
                        objArr4[2] = bundle.get(ExifInterface.GPS_MEASUREMENT_3D);
                        objArr4[3] = bundle.get("4");
                        method.invoke(absBYDAutoLightListener4, objArr4);
                    }
                    if (parameterCount == 5) {
                        method.invoke(this.mAbsBYDAutoLightListener, bundle.get("1"), bundle.get(ExifInterface.GPS_MEASUREMENT_2D), bundle.get(ExifInterface.GPS_MEASUREMENT_3D), bundle.get("4"), bundle.get("5"));
                    }
                    if (parameterCount == 6) {
                        i = 0;
                        method.invoke(this.mAbsBYDAutoLightListener, bundle.get("1"), bundle.get(ExifInterface.GPS_MEASUREMENT_2D), bundle.get(ExifInterface.GPS_MEASUREMENT_3D), bundle.get("4"), bundle.get("5"), bundle.get("6"));
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
            Log.d(TAG, "errormessage=" + e.getMessage());
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0058  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String nameChange(java.lang.String r2) {
        /*
            r1 = this;
            int r0 = r2.hashCode()
            switch(r0) {
                case -1803708878: goto L4e;
                case 20681282: goto L44;
                case 44715391: goto L3a;
                case 545096241: goto L30;
                case 1679968259: goto L26;
                case 1771655275: goto L1c;
                case 1924322527: goto L12;
                case 1949792925: goto L8;
                default: goto L7;
            }
        L7:
            goto L58
        L8:
            java.lang.String r0 = "setTurnLightsFlashState"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L58
            r2 = 7
            goto L59
        L12:
            java.lang.String r0 = "setAFSSwitchSate"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L58
            r2 = 3
            goto L59
        L1c:
            java.lang.String r0 = "setDoorLightState"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L58
            r2 = 2
            goto L59
        L26:
            java.lang.String r0 = "setAmbientMulticolorMode"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L58
            r2 = 5
            goto L59
        L30:
            java.lang.String r0 = "setAmbientMulticolorState"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L58
            r2 = 6
            goto L59
        L3a:
            java.lang.String r0 = "setAmbientMusicModeState"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L58
            r2 = 0
            goto L59
        L44:
            java.lang.String r0 = "setDayTimeLightState"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L58
            r2 = 1
            goto L59
        L4e:
            java.lang.String r0 = "setAmbientMusicMode"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L58
            r2 = 4
            goto L59
        L58:
            r2 = -1
        L59:
            switch(r2) {
                case 0: goto L74;
                case 1: goto L71;
                case 2: goto L6e;
                case 3: goto L6b;
                case 4: goto L68;
                case 5: goto L65;
                case 6: goto L62;
                case 7: goto L5f;
                default: goto L5c;
            }
        L5c:
            java.lang.String r2 = ""
            goto L76
        L5f:
            java.lang.String r2 = "onTurnLightFlashState"
            goto L76
        L62:
            java.lang.String r2 = "onAmbientMulticolorStateChanged"
            goto L76
        L65:
            java.lang.String r2 = "onAmbientMulticolorModeChanged"
            goto L76
        L68:
            java.lang.String r2 = "onAmbientMusicModechanged"
            goto L76
        L6b:
            java.lang.String r2 = "onAFSSwitchStateChange"
            goto L76
        L6e:
            java.lang.String r2 = "onDoorLightStateChanged"
            goto L76
        L71:
            java.lang.String r2 = "onDayTimeLightChanged"
            goto L76
        L74:
            java.lang.String r2 = "onAmbientMusicModeState"
        L76:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.hardware.bydauto_test.TestLightManagerImplCallback.nameChange(java.lang.String):java.lang.String");
    }
}
