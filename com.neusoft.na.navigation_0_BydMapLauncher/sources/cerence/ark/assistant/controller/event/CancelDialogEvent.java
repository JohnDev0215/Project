package cerence.ark.assistant.controller.event;

import cerence.ark.assistant.api.event.AppEvent;

/* JADX INFO: loaded from: classes2.dex */
public class CancelDialogEvent extends AppEvent {
    private CancelDialogEvent() {
        super("CANCEL_DIALOG");
    }

    public static class Builder {
        public CancelDialogEvent build() {
            return new CancelDialogEvent();
        }
    }
}
