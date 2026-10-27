package a.a.e;

import android.hardware.bydauto.light.AbsBYDAutoLightListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: TestLightManagerImplCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private AbsBYDAutoLightListener f27a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f28b;

    /* JADX INFO: compiled from: TestLightManagerImplCallback.java */
    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            k.this.b(message);
        }
    }

    public k() {
        HandlerThread handlerThread = new HandlerThread("TestLightManagerImplCallback");
        handlerThread.start();
        this.f28b = new a(handlerThread.getLooper());
    }

    private void a(Bundle bundle, String str) {
        int i2;
        try {
            Method[] declaredMethods = AbsBYDAutoLightListener.class.getDeclaredMethods();
            int length = declaredMethods.length;
            int i3 = 0;
            int i4 = 0;
            while (i4 < length) {
                Method method = declaredMethods[i4];
                if (method.getName().equals(d(str))) {
                    int parameterCount = method.getParameterCount();
                    if (parameterCount == 0) {
                        method.invoke(this.f27a, new Object[i3]);
                    }
                    if (parameterCount == 1) {
                        AbsBYDAutoLightListener absBYDAutoLightListener = this.f27a;
                        Object[] objArr = new Object[1];
                        objArr[i3] = bundle.get("1");
                        method.invoke(absBYDAutoLightListener, objArr);
                    }
                    if (parameterCount == 2) {
                        AbsBYDAutoLightListener absBYDAutoLightListener2 = this.f27a;
                        Object[] objArr2 = new Object[2];
                        objArr2[i3] = bundle.get("1");
                        objArr2[1] = bundle.get("2");
                        method.invoke(absBYDAutoLightListener2, objArr2);
                    }
                    if (parameterCount == 3) {
                        AbsBYDAutoLightListener absBYDAutoLightListener3 = this.f27a;
                        Object[] objArr3 = new Object[3];
                        objArr3[i3] = bundle.get("1");
                        objArr3[1] = bundle.get("2");
                        objArr3[2] = bundle.get("3");
                        method.invoke(absBYDAutoLightListener3, objArr3);
                    }
                    if (parameterCount == 4) {
                        AbsBYDAutoLightListener absBYDAutoLightListener4 = this.f27a;
                        Object[] objArr4 = new Object[4];
                        objArr4[i3] = bundle.get("1");
                        objArr4[1] = bundle.get("2");
                        objArr4[2] = bundle.get("3");
                        objArr4[3] = bundle.get("4");
                        method.invoke(absBYDAutoLightListener4, objArr4);
                    }
                    if (parameterCount == 5) {
                        method.invoke(this.f27a, bundle.get("1"), bundle.get("2"), bundle.get("3"), bundle.get("4"), bundle.get("5"));
                    }
                    if (parameterCount == 6) {
                        i2 = 0;
                        method.invoke(this.f27a, bundle.get("1"), bundle.get("2"), bundle.get("3"), bundle.get("4"), bundle.get("5"), bundle.get("6"));
                    } else {
                        i2 = 0;
                    }
                } else {
                    i2 = i3;
                }
                i4++;
                i3 = i2;
            }
        } catch (Exception e2) {
            Log.d("TestLightManagerImplCallback", "errormessage=" + e2.getMessage());
        }
    }

    private String d(String str) {
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

    public void b(Message message) {
        if (this.f27a != null) {
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
            a(bundle, bundle.getString("what"));
        }
    }

    public void c(String str, Object... objArr) {
        if (this.f28b != null) {
            Bundle bundle = new Bundle();
            bundle.putString("what", str);
            for (int i2 = 0; i2 < objArr.length; i2++) {
                if (objArr[i2] instanceof Integer) {
                    bundle.putInt(String.valueOf(i2 + 1), ((Integer) objArr[i2]).intValue());
                }
                if (objArr[i2] instanceof Float) {
                    bundle.putFloat(String.valueOf(i2 + 1), ((Float) objArr[i2]).floatValue());
                }
            }
            Message messageObtain = Message.obtain();
            messageObtain.obj = bundle;
            this.f28b.sendMessage(messageObtain);
        }
    }

    public void e(AbsBYDAutoLightListener absBYDAutoLightListener) {
        if (absBYDAutoLightListener == null) {
            return;
        }
        this.f27a = absBYDAutoLightListener;
    }
}
