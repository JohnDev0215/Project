package cerence.ark.assistant.controller.event;

import cerence.ark.assistant.api.event.AppEvent;

/* JADX INFO: loaded from: classes.dex */
public class FirstPageEvent extends AppEvent {
    private FirstPageEvent() {
        super("FIRST_PAGE");
    }

    public static class Builder {
        public FirstPageEvent build() {
            return new FirstPageEvent();
        }
    }
}
