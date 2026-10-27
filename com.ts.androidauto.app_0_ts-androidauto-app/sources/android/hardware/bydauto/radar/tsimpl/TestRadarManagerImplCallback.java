package android.hardware.bydauto.radar.tsimpl;

import android.hardware.bydauto.radar.AbsBYDAutoRadarListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class TestRadarManagerImplCallback extends AbsBYDAutoRadarListener {
    protected static final String TAG = "TestRadarManagerImplCallback";
    private AbsBYDAutoRadarListener mAbsBYDAutoRadarListener;
    private Handler mHandler;

    public void setAbsBYDAutoRadarListener(AbsBYDAutoRadarListener absBYDAutoRadarListener) {
        this.mAbsBYDAutoRadarListener = absBYDAutoRadarListener;
    }

    public TestRadarManagerImplCallback() {
        HandlerThread handlerThread = new HandlerThread(TAG);
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto.radar.tsimpl.TestRadarManagerImplCallback.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                TestRadarManagerImplCallback.this.dispatchMessageCallBack(msg);
            }
        };
    }

    @Override // android.hardware.bydauto.radar.AbsBYDAutoRadarListener
    public void onReverseRadarSwitchStateChanged(int state) {
        Method method = getMethod(AbsBYDAutoRadarListener.class, "onReverseRadarSwitchStateChanged", 1);
        if (method == null) {
            Log.e(TAG, "method==null ");
            return;
        }
        Message message = Message.obtain();
        InvokeMethod invokeMethod = new InvokeMethod();
        invokeMethod.method = method;
        invokeMethod.params.add(Integer.valueOf(state));
        message.obj = invokeMethod;
        this.mHandler.sendMessage(message);
    }

    private Method getMethod(Class c, String name, int paramSize) {
        List<Method> methods = new ArrayList<>();
        for (Method method : c.getMethods()) {
            if (method.getName().equals(name) && method.getParameters().length == paramSize) {
                methods.add(method);
            }
        }
        if (methods.size() == 0) {
            return null;
        }
        if (methods.size() == 1) {
            return methods.get(0);
        }
        try {
            throw new Exception("size>1");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchMessageCallBack(Message msg) {
        if (this.mAbsBYDAutoRadarListener != null) {
            InvokeMethod invokeMethod = (InvokeMethod) msg.obj;
            Log.d(TAG, "method  " + invokeMethod.method.getName() + "  params" + String.valueOf(invokeMethod.params));
            try {
                Log.d(TAG, "method.invoke");
                invokeMethod.method.invoke(this.mAbsBYDAutoRadarListener, invokeMethod.params.toArray());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e2) {
                e2.printStackTrace();
            }
        }
    }

    private static class InvokeMethod {
        private Method method;
        private ArrayList<Object> params;

        private InvokeMethod() {
            this.params = new ArrayList<>();
        }
    }
}
