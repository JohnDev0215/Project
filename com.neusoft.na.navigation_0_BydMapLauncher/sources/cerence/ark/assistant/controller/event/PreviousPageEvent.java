package cerence.ark.assistant.controller.event;

import cerence.ark.assistant.api.event.AppEvent;

/* JADX INFO: loaded from: classes2.dex */
public class PreviousPageEvent extends AppEvent {
    private PreviousPageEvent() {
        super("PREVIOUS_PAGE");
    }

    public static class Builder {
        public PreviousPageEvent build() {
            return new PreviousPageEvent();
        }
    }
}
