package cerence.ark.assistant.controller.event;

import cerence.ark.assistant.api.event.AppEvent;
import cerence.ark.assistant.controller.enumeration.PageControlTypeEnum;

/* JADX INFO: loaded from: classes2.dex */
public class NextPageEvent extends AppEvent {
    private NextPageEvent() {
        super(PageControlTypeEnum.NEXT_PAGE);
    }

    public static class Builder {
        public NextPageEvent build() {
            return new NextPageEvent();
        }
    }
}
