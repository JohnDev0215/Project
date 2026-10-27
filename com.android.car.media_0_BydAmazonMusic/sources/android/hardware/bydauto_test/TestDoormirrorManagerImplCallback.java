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

    public TestDoormirrorManagerImplCallback() {
        HandlerThread handlerThread = new HandlerThread("TestDoormirrorManagerImplCallback");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: android.hardware.bydauto_test.TestDoormirrorManagerImplCallback.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                TestDoormirrorManagerImplCallback.this.dispatchMessageCallBack(message);
            }
        };
    }

    public void dispatchMessageCallBack(Message message) {
        AbsBYDAutoRearViewMirrorListener absBYDAutoRearViewMirrorListener = this.mAbsBYDAutoRearViewMirrorListener;
        if (absBYDAutoRearViewMirrorListener != null) {
            int i = message.arg1;
            absBYDAutoRearViewMirrorListener.onAutoExternalRearMirrorStateChanged(i);
            this.mAbsBYDAutoRearViewMirrorListener.onAutoExternalRearMirrorAntiglareStateChanged(i);
            this.mAbsBYDAutoRearViewMirrorListener.onError(i, i + "");
            this.mAbsBYDAutoRearViewMirrorListener.onDataEventChanged(i, new BYDAutoEventValue());
            this.mAbsBYDAutoRearViewMirrorListener.onDataChanged(new IBYDAutoEvent() { // from class: android.hardware.bydauto_test.TestDoormirrorManagerImplCallback.2
                @Override // android.hardware.IBYDAutoEvent
                public byte[] getBufferData() {
                    return new byte[0];
                }

                @Override // android.hardware.IBYDAutoEvent
                public Object getData() {
                    return null;
                }

                @Override // android.hardware.IBYDAutoEvent
                public int getDeviceType() {
                    return 0;
                }

                @Override // android.hardware.IBYDAutoEvent
                public double getDoubleValue() {
                    return 0.0d;
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
                public void setData(Object obj) {
                }
            });
        }
    }

    public void enqueueMessage(int i, int i2) {
        if (this.mHandler != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            this.mHandler.sendEmptyMessage(i);
        }
    }

    public void setAbsBydAutoBodyworkListener(AbsBYDAutoRearViewMirrorListener absBYDAutoRearViewMirrorListener) {
        if (absBYDAutoRearViewMirrorListener == null) {
            return;
        }
        this.mAbsBYDAutoRearViewMirrorListener = absBYDAutoRearViewMirrorListener;
    }
}
