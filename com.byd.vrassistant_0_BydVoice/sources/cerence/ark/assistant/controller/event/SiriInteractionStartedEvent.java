package cerence.ark.assistant.controller.event;

import cerence.ark.assistant.api.event.AppEvent;

/* JADX INFO: loaded from: classes.dex */
public class SiriInteractionStartedEvent extends AppEvent {
    private SiriInteractionStartedEvent() {
        super("SIRI_INTERACTION_STARTED");
    }

    public static class Builder {
        public SiriInteractionStartedEvent build() {
            return new SiriInteractionStartedEvent();
        }
    }
}
