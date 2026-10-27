package cerence.ark.assistant.controller.event;

import cerence.ark.assistant.api.event.AppEvent;

/* JADX INFO: loaded from: classes.dex */
public class DisableSiriEvent extends AppEvent {
    private DisableSiriEvent() {
        super("DISABLE_SIRI");
    }

    public static class Builder {
        public DisableSiriEvent build() {
            return new DisableSiriEvent();
        }
    }
}
