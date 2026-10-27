package android.hardware.bydauto.radar.tsimpl;

import android.hardware.bydauto.radar.AbsBYDAutoRadarListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class TestRadarManagerImplCallback extends AbsBYDAutoRadarListener {
    public static final String TAG = "TestRadarManagerImplCallback";
    private AbsBYDAutoRadarListener mAbsBYDAutoRadarListener;
    private Handler mHandler;

    public static class InvokeMethod {
        private Method method;
        private ArrayList<Object> params;

        private InvokeMethod() {
            this.params = new ArrayList<>();
        }
    }

    public TestRadarManagerImplCallback() {
        HandlerThread handlerThread = new HandlerThread(TAG);
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto.radar.tsimpl.TestRadarManagerImplCallback.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                TestRadarManagerImplCallback.this.dispatchMessageCallBack(message);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchMessageCallBack(Message message) {
        if (this.mAbsBYDAutoRadarListener != null) {
            InvokeMethod invokeMethod = (InvokeMethod) message.obj;
            Log.d(TAG, "method  " + invokeMethod.method.getName() + "  params" + String.valueOf(invokeMethod.params));
            try {
                Log.d(TAG, "method.invoke");
                invokeMethod.method.invoke(this.mAbsBYDAutoRadarListener, invokeMethod.params.toArray());
            } catch (IllegalAccessException e8) {
                e8.printStackTrace();
            } catch (InvocationTargetException e9) {
                e9.printStackTrace();
            }
        }
    }

    private Method getMethod(Class cls, String str, int i8) {
        ArrayList arrayList = new ArrayList();
        for (Method method : cls.getMethods()) {
            if (method.getName().equals(str) && method.getParameters().length == i8) {
                arrayList.add(method);
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        if (arrayList.size() == 1) {
            return (Method) arrayList.get(0);
        }
        try {
            throw new Exception("size>1");
        } catch (Exception e8) {
            e8.printStackTrace();
            return null;
        }
    }

    @Override // android.hardware.bydauto.radar.AbsBYDAutoRadarListener, b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z7) {
        super.isConnected(z7);
    }

    @Override // android.hardware.bydauto.radar.AbsBYDAutoRadarListener, b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z7) {
        super.isOptimizationApi(z7);
    }

    @Override // android.hardware.bydauto.radar.AbsBYDAutoRadarListener
    public void onReverseRadarSwitchStateChanged(int i8) {
        Method method = getMethod(AbsBYDAutoRadarListener.class, "onReverseRadarSwitchStateChanged", 1);
        if (method == null) {
            Log.e(TAG, "method==null ");
            return;
        }
        Message messageObtain = Message.obtain();
        InvokeMethod invokeMethod = new InvokeMethod();
        invokeMethod.method = method;
        invokeMethod.params.add(Integer.valueOf(i8));
        messageObtain.obj = invokeMethod;
        this.mHandler.sendMessage(messageObtain);
    }

    public void setAbsBYDAutoRadarListener(AbsBYDAutoRadarListener absBYDAutoRadarListener) {
        this.mAbsBYDAutoRadarListener = absBYDAutoRadarListener;
    }
}
