package com.byd.vrassistant.hmi;

import android.util.Log;
import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.controller.RequestHandler;
import cerence.ark.assistant.api.notification.AsrResultUpdated;
import cerence.ark.assistant.api.notification.DialogEvent;
import cerence.ark.assistant.api.notification.DialogStateChanged;
import cerence.ark.assistant.api.notification.INotification;
import cerence.ark.assistant.controller.domain.hmi.AbsHmiListener;
import cerence.ark.assistant.controller.domain.hmi.HMIRequestsEnum;
import com.byd.vrassistant.eventprocessor.DialogEventProcessor;
import com.byd.vrassistant.view.ViewManager;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class DemoHmi extends AbsHmiListener {
    private static final String TAG = "NEU_DemoHmi";

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsHmiListener
    public void loadRequestHandlers(Map<String, RequestHandler<? extends BaseResult>> map) {
        map.put(HMIRequestsEnum.SHOW_PICKLIST, new ShowPicklistHandler());
        map.put(HMIRequestsEnum.DISPLAY, new DisplayHandler());
        map.put(HMIRequestsEnum.HIGHLIGHT_ITEM, new HighlightHandler());
        map.put(HMIRequestsEnum.PAGE_CONTROL, new PageControlHandler());
    }

    @Override // cerence.ark.assistant.api.hmi.HmiListener
    public void onNotification(INotification iNotification) {
        if (iNotification instanceof AsrResultUpdated) {
            ViewManager.getViewStateManager().setAsrResultUpdated((AsrResultUpdated) iNotification);
            return;
        }
        if (iNotification instanceof DialogEvent) {
            DialogEvent dialogEvent = (DialogEvent) iNotification;
            DialogEventProcessor.getInstance().setDialogEvent(dialogEvent);
            try {
                DialogEventHandler.get().handle(dialogEvent);
                return;
            } catch (Exception e) {
                Log.e(TAG, "onNotification: handle DialogEvent", e);
                return;
            }
        }
        if (iNotification instanceof DialogStateChanged) {
            ViewManager.getViewStateManager().setDialogState(((DialogStateChanged) iNotification).getState(), System.currentTimeMillis());
        }
    }
}
