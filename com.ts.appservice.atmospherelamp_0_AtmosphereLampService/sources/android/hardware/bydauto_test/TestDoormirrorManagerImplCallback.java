package android.hardware.bydauto_test;

import android.hardware.IBYDAutoEvent;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.doormirror.AbsBYDAutoRearViewMirrorListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
public class TestDoormirrorManagerImplCallback {
    private AbsBYDAutoRearViewMirrorListener mAbsBYDAutoRearViewMirrorListener;
    private Handler mHandler;

    public void setAbsBydAutoBodyworkListener(AbsBYDAutoRearViewMirrorListener absBYDAutoRearViewMirrorListener) {
        if (absBYDAutoRearViewMirrorListener == null) {
            return;
        }
        this.mAbsBYDAutoRearViewMirrorListener = absBYDAutoRearViewMirrorListener;
    }

    public TestDoormirrorManagerImplCallback() {
        HandlerThread handlerThread = new HandlerThread("TestDoormirrorManagerImplCallback");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto_test.TestDoormirrorManagerImplCallback.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                TestDoormirrorManagerImplCallback.this.dispatchMessageCallBack(msg);
            }
        };
    }

    public void enqueueMessage(int what, int arg1) {
        if (this.mHandler != null) {
            Message message = Message.obtain();
            message.what = what;
            message.arg1 = arg1;
            this.mHandler.sendEmptyMessage(what);
        }
    }

    public void dispatchMessageCallBack(Message msg) {
        if (this.mAbsBYDAutoRearViewMirrorListener != null) {
            int arg1 = msg.arg1;
            this.mAbsBYDAutoRearViewMirrorListener.onAutoExternalRearMirrorStateChanged(arg1);
            this.mAbsBYDAutoRearViewMirrorListener.onAutoExternalRearMirrorAntiglareStateChanged(arg1);
            this.mAbsBYDAutoRearViewMirrorListener.onError(arg1, arg1 + "");
            this.mAbsBYDAutoRearViewMirrorListener.onDataEventChanged(arg1, new BYDAutoEventValue());
            this.mAbsBYDAutoRearViewMirrorListener.onDataChanged(new IBYDAutoEvent() { // from class: android.hardware.bydauto_test.TestDoormirrorManagerImplCallback.2
                @Override // android.hardware.IBYDAutoEvent
                public int getDeviceType() {
                    return 0;
                }

                @Override // android.hardware.IBYDAutoEvent
                public int getEventType() {
                    return 0;
                }

                @Override // android.hardware.IBYDAutoEvent
                public int getValue() {
                    return 0;
                }

                @Override // android.hardware.IBYDAutoEvent
                public double getDoubleValue() {
                    return 0.0d;
                }

                @Override // android.hardware.IBYDAutoEvent
                public Object getData() {
                    return null;
                }

                @Override // android.hardware.IBYDAutoEvent
                public void setData(Object var1) {
                }

                @Override // android.hardware.IBYDAutoEvent
                public byte[] getBufferData() {
                    return new byte[0];
                }
            });
        }
    }
}
