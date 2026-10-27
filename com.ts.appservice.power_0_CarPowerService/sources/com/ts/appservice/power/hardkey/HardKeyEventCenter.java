package com.ts.appservice.power.hardkey;

import android.util.Log;
import android.view.KeyEvent;
import ts.car.service.input.IKeyEventListener;
import ts.car.service.input.IKeyLongPressEventListener;

/* JADX INFO: loaded from: classes.dex */
public class HardKeyEventCenter implements IKeyEventListener, IKeyLongPressEventListener {
    private static final String TAG = HardKeyEventCenter.class.getSimpleName();

    @Override // ts.car.service.input.IKeyEventListener
    public void onKeyEvent(KeyEvent event, int sceneType, int targetDisplay) {
        Log.d(TAG, "onKeyEvent: event = " + event + "; sceneType = " + sceneType + "; targetDisplay" + targetDisplay);
    }

    @Override // ts.car.service.input.IKeyLongPressEventListener
    public void onKeyLongPressed(KeyEvent event, int sceneType, int targetDisplay) {
        Log.d(TAG, "onKeyLongPress: event = " + event + "; sceneType = " + sceneType + "; targetDisplay" + targetDisplay);
    }
}
