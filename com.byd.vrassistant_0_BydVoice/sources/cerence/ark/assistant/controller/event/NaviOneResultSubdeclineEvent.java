package cerence.ark.assistant.controller.event;

import cerence.ark.assistant.api.event.AppEvent;

/* JADX INFO: loaded from: classes.dex */
public class NaviOneResultSubdeclineEvent extends AppEvent {
    private NaviOneResultSubdeclineEvent() {
        super("NAVI_ONE_RESULT_SUBDECLINE");
    }

    public static class Builder {
        public NaviOneResultSubdeclineEvent build() {
            return new NaviOneResultSubdeclineEvent();
        }
    }
}
