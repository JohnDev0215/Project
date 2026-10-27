package cerence.ark.assistant.controller.event;

import cerence.ark.assistant.api.event.AppEvent;

/* JADX INFO: loaded from: classes.dex */
public class SiriInteractionStoppedEvent extends AppEvent {
    private SiriInteractionStoppedEvent() {
        super("SIRI_INTERACTION_STOPPED");
    }

    public static class Builder {
        public SiriInteractionStoppedEvent build() {
            return new SiriInteractionStoppedEvent();
        }
    }
}
