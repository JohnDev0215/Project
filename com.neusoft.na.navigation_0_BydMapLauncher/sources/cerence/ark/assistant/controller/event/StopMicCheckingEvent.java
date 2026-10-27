package cerence.ark.assistant.controller.event;

import cerence.ark.assistant.api.event.AppEvent;

/* JADX INFO: loaded from: classes2.dex */
public class StopMicCheckingEvent extends AppEvent {
    private StopMicCheckingEvent() {
        super("STOP_MIC_CHECKING");
    }

    public static class Builder {
        public StopMicCheckingEvent build() {
            return new StopMicCheckingEvent();
        }
    }
}
