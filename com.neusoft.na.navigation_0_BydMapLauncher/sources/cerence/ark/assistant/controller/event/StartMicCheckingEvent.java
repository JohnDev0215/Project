package cerence.ark.assistant.controller.event;

import cerence.ark.assistant.api.event.AppEvent;

/* JADX INFO: loaded from: classes2.dex */
public class StartMicCheckingEvent extends AppEvent {
    private StartMicCheckingEvent() {
        super("START_MIC_CHECKING");
    }

    public static class Builder {
        public StartMicCheckingEvent build() {
            return new StartMicCheckingEvent();
        }
    }
}
