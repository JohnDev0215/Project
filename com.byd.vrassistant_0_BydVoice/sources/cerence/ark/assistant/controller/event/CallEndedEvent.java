package cerence.ark.assistant.controller.event;

import cerence.ark.assistant.api.event.AppEvent;

/* JADX INFO: loaded from: classes.dex */
public class CallEndedEvent extends AppEvent {
    private CallEndedEvent() {
        super("CALL_ENDED");
    }

    public static class Builder {
        public CallEndedEvent build() {
            return new CallEndedEvent();
        }
    }
}
