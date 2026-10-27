package cerence.ark.assistant.controller.event;

import cerence.ark.assistant.api.event.AppEvent;

/* JADX INFO: loaded from: classes.dex */
public class NaviOneResultSubcomfirmEvent extends AppEvent {
    private NaviOneResultSubcomfirmEvent() {
        super("NAVI_ONE_RESULT_SUBCOMFIRM");
    }

    public static class Builder {
        public NaviOneResultSubcomfirmEvent build() {
            return new NaviOneResultSubcomfirmEvent();
        }
    }
}
