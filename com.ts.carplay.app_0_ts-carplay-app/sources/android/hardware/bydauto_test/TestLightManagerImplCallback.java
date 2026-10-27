package android.hardware.bydauto_test;

import android.hardware.bydauto.light.AbsBYDAutoLightListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
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
    private Handler mHandler;

    public void setAbsBYDAutoLightListener(AbsBYDAutoLightListener listener) {
        if (listener == null) {
            return;
        }
        this.mAbsBYDAutoLightListener = listener;
    }

    public TestLightManagerImplCallback() {
        HandlerThread handlerThread = new HandlerThread(TAG);
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto_test.TestLightManagerImplCallback.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                TestLightManagerImplCallback.this.dispatchMessageCallBack(msg);
            }
        };
    }

    public void enqueueMessage(String what, Object... args) {
        if (this.mHandler != null) {
            Bundle bundle = new Bundle();
            bundle.putString("what", what);
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof Integer) {
                    bundle.putInt(String.valueOf(i + 1), ((Integer) args[i]).intValue());
                }
                if (args[i] instanceof Float) {
                    bundle.putFloat(String.valueOf(i + 1), ((Float) args[i]).floatValue());
                }
            }
            Message message = Message.obtain();
            message.obj = bundle;
            this.mHandler.sendMessage(message);
        }
    }

    public void dispatchMessageCallBack(Message msg) {
        if (this.mAbsBYDAutoLightListener != null) {
            Bundle bundle = (Bundle) msg.obj;
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
            String what = bundle.getString("what");
            Process(bundle, what);
        }
    }

    private void Process(Bundle bundle, String what) {
        int i;
        try {
            Method[] methods = AbsBYDAutoLightListener.class.getDeclaredMethods();
            int length = methods.length;
            int i2 = 0;
            int i3 = 0;
            while (i3 < length) {
                Method method = methods[i3];
                try {
                    if (!method.getName().equals(nameChange(what))) {
                        i = i2;
                    } else {
                        int count = method.getParameterCount();
                        if (count == 0) {
                            method.invoke(this.mAbsBYDAutoLightListener, new Object[i2]);
                        }
                        if (count == 1) {
                            AbsBYDAutoLightListener absBYDAutoLightListener = this.mAbsBYDAutoLightListener;
                            Object[] objArr = new Object[1];
                            objArr[i2] = bundle.get("1");
                            method.invoke(absBYDAutoLightListener, objArr);
                        }
                        if (count == 2) {
                            AbsBYDAutoLightListener absBYDAutoLightListener2 = this.mAbsBYDAutoLightListener;
                            Object[] objArr2 = new Object[2];
                            objArr2[i2] = bundle.get("1");
                            objArr2[1] = bundle.get("2");
                            method.invoke(absBYDAutoLightListener2, objArr2);
                        }
                        if (count == 3) {
                            AbsBYDAutoLightListener absBYDAutoLightListener3 = this.mAbsBYDAutoLightListener;
                            Object[] objArr3 = new Object[3];
                            objArr3[i2] = bundle.get("1");
                            objArr3[1] = bundle.get("2");
                            objArr3[2] = bundle.get("3");
                            method.invoke(absBYDAutoLightListener3, objArr3);
                        }
                        if (count == 4) {
                            AbsBYDAutoLightListener absBYDAutoLightListener4 = this.mAbsBYDAutoLightListener;
                            Object[] objArr4 = new Object[4];
                            objArr4[i2] = bundle.get("1");
                            objArr4[1] = bundle.get("2");
                            objArr4[2] = bundle.get("3");
                            objArr4[3] = bundle.get("4");
                            method.invoke(absBYDAutoLightListener4, objArr4);
                        }
                        if (count == 5) {
                            method.invoke(this.mAbsBYDAutoLightListener, bundle.get("1"), bundle.get("2"), bundle.get("3"), bundle.get("4"), bundle.get("5"));
                        }
                        if (count != 6) {
                            i = 0;
                        } else {
                            i = 0;
                            method.invoke(this.mAbsBYDAutoLightListener, bundle.get("1"), bundle.get("2"), bundle.get("3"), bundle.get("4"), bundle.get("5"), bundle.get("6"));
                        }
                    }
                    i3++;
                    i2 = i;
                } catch (Exception e) {
                    ex = e;
                    Log.d(TAG, "errormessage=" + ex.getMessage());
                    return;
                }
            }
        } catch (Exception e2) {
            ex = e2;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:29:0x005a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String nameChange(java.lang.String r3) {
        /*
            r2 = this;
            java.lang.String r0 = ""
            int r1 = r3.hashCode()
            switch(r1) {
                case -1803708878: goto L50;
                case 20681282: goto L46;
                case 44715391: goto L3c;
                case 545096241: goto L32;
                case 1679968259: goto L28;
                case 1771655275: goto L1e;
                case 1924322527: goto L14;
                case 1949792925: goto La;
                default: goto L9;
            }
        L9:
            goto L5a
        La:
            java.lang.String r1 = "setTurnLightsFlashState"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L9
            r1 = 7
            goto L5b
        L14:
            java.lang.String r1 = "setAFSSwitchSate"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L9
            r1 = 3
            goto L5b
        L1e:
            java.lang.String r1 = "setDoorLightState"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L9
            r1 = 2
            goto L5b
        L28:
            java.lang.String r1 = "setAmbientMulticolorMode"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L9
            r1 = 5
            goto L5b
        L32:
            java.lang.String r1 = "setAmbientMulticolorState"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L9
            r1 = 6
            goto L5b
        L3c:
            java.lang.String r1 = "setAmbientMusicModeState"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L9
            r1 = 0
            goto L5b
        L46:
            java.lang.String r1 = "setDayTimeLightState"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L9
            r1 = 1
            goto L5b
        L50:
            java.lang.String r1 = "setAmbientMusicMode"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L9
            r1 = 4
            goto L5b
        L5a:
            r1 = -1
        L5b:
            switch(r1) {
                case 0: goto L76;
                case 1: goto L73;
                case 2: goto L70;
                case 3: goto L6d;
                case 4: goto L6a;
                case 5: goto L67;
                case 6: goto L64;
                case 7: goto L61;
                default: goto L5e;
            }
        L5e:
            java.lang.String r0 = ""
            goto L79
        L61:
            java.lang.String r0 = "onTurnLightFlashState"
            goto L79
        L64:
            java.lang.String r0 = "onAmbientMulticolorStateChanged"
            goto L79
        L67:
            java.lang.String r0 = "onAmbientMulticolorModeChanged"
            goto L79
        L6a:
            java.lang.String r0 = "onAmbientMusicModechanged"
            goto L79
        L6d:
            java.lang.String r0 = "onAFSSwitchStateChange"
            goto L79
        L70:
            java.lang.String r0 = "onDoorLightStateChanged"
            goto L79
        L73:
            java.lang.String r0 = "onDayTimeLightChanged"
            goto L79
        L76:
            java.lang.String r0 = "onAmbientMusicModeState"
        L79:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.hardware.bydauto_test.TestLightManagerImplCallback.nameChange(java.lang.String):java.lang.String");
    }
}
