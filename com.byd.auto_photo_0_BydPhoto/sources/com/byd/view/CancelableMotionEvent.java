package com.byd.view;

import android.os.Handler;
import android.view.MotionEvent;
import com.byd.view.ViewMotionListener;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CancelableMotionEvent extends ViewMotionListener {
    public static final List<Handler> handlers = new LinkedList();

    public CancelableMotionEvent(ViewMotionListener.OnDoubleTapListener onDoubleTapListener) {
        super(onDoubleTapListener);
    }

    @Override // com.byd.view.ViewMotionListener
    protected boolean doEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            List<Handler> list = handlers;
            if (!list.contains(this.tapHandler)) {
                list.add(this.tapHandler);
            }
        }
        return super.doEvent(motionEvent);
    }

    @Override // com.byd.view.ViewMotionListener
    protected void removeAllEventAfterDown() {
        handlers.remove(this.tapHandler);
        super.removeAllEventAfterDown();
    }
}
