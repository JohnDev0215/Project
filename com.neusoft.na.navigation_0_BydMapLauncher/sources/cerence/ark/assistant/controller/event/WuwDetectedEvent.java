package cerence.ark.assistant.controller.event;

import cerence.ark.assistant.api.event.AppEvent;

/* JADX INFO: loaded from: classes2.dex */
public class WuwDetectedEvent extends AppEvent {
    private WuwDetectedEvent() {
        super("WUW_DETECTED");
    }

    public static class Builder {
        public WuwDetectedEvent build() {
            return new WuwDetectedEvent();
        }
    }
}
