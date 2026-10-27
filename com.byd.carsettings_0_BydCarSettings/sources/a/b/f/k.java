package a.b.f;

import android.hardware.bydauto.light.AbsBYDAutoLightListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: TestLightManagerImplCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AbsBYDAutoLightListener f41a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f42b;

    /* JADX INFO: compiled from: TestLightManagerImplCallback.java */
    public class a extends Handler {
        public a(Looper looper) {
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
        this.f42b = new a(handlerThread.getLooper());
    }

    public final void a(Bundle bundle, String str) {
        int i;
        try {
            Method[] declaredMethods = AbsBYDAutoLightListener.class.getDeclaredMethods();
            int length = declaredMethods.length;
            int i2 = 0;
            int i3 = 0;
            while (i3 < length) {
                Method method = declaredMethods[i3];
                if (method.getName().equals(d(str))) {
                    int parameterCount = method.getParameterCount();
                    if (parameterCount == 0) {
                        method.invoke(this.f41a, new Object[i2]);
                    }
                    if (parameterCount == 1) {
                        AbsBYDAutoLightListener absBYDAutoLightListener = this.f41a;
                        Object[] objArr = new Object[1];
                        objArr[i2] = bundle.get("1");
                        method.invoke(absBYDAutoLightListener, objArr);
                    }
                    if (parameterCount == 2) {
                        AbsBYDAutoLightListener absBYDAutoLightListener2 = this.f41a;
                        Object[] objArr2 = new Object[2];
                        objArr2[i2] = bundle.get("1");
                        objArr2[1] = bundle.get(ExifInterface.GPS_MEASUREMENT_2D);
                        method.invoke(absBYDAutoLightListener2, objArr2);
                    }
                    if (parameterCount == 3) {
                        AbsBYDAutoLightListener absBYDAutoLightListener3 = this.f41a;
                        Object[] objArr3 = new Object[3];
                        objArr3[i2] = bundle.get("1");
                        objArr3[1] = bundle.get(ExifInterface.GPS_MEASUREMENT_2D);
                        objArr3[2] = bundle.get(ExifInterface.GPS_MEASUREMENT_3D);
                        method.invoke(absBYDAutoLightListener3, objArr3);
                    }
                    if (parameterCount == 4) {
                        AbsBYDAutoLightListener absBYDAutoLightListener4 = this.f41a;
                        Object[] objArr4 = new Object[4];
                        objArr4[i2] = bundle.get("1");
                        objArr4[1] = bundle.get(ExifInterface.GPS_MEASUREMENT_2D);
                        objArr4[2] = bundle.get(ExifInterface.GPS_MEASUREMENT_3D);
                        objArr4[3] = bundle.get("4");
                        method.invoke(absBYDAutoLightListener4, objArr4);
                    }
                    if (parameterCount == 5) {
                        method.invoke(this.f41a, bundle.get("1"), bundle.get(ExifInterface.GPS_MEASUREMENT_2D), bundle.get(ExifInterface.GPS_MEASUREMENT_3D), bundle.get("4"), bundle.get("5"));
                    }
                    if (parameterCount == 6) {
                        i = 0;
                        method.invoke(this.f41a, bundle.get("1"), bundle.get(ExifInterface.GPS_MEASUREMENT_2D), bundle.get(ExifInterface.GPS_MEASUREMENT_3D), bundle.get("4"), bundle.get("5"), bundle.get("6"));
                    } else {
                        i = 0;
                    }
                } else {
                    i = i2;
                }
                i3++;
                i2 = i;
            }
        } catch (Exception e2) {
            Log.d("TestLightManagerImplCallback", "errormessage=" + e2.getMessage());
        }
    }

    public void b(Message message) {
        if (this.f41a != null) {
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
            a(bundle, bundle.getString("what"));
        }
    }

    public void c(String str, Object... objArr) {
        if (this.f42b != null) {
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
            this.f42b.sendMessage(messageObtain);
        }
    }

    public final String d(String str) {
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

    public void e(AbsBYDAutoLightListener absBYDAutoLightListener) {
        if (absBYDAutoLightListener == null) {
            return;
        }
        this.f41a = absBYDAutoLightListener;
    }
}
