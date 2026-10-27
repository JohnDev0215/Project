package cerence.ark.assistant.controller.event;

import cerence.ark.assistant.api.event.AppEvent;
import cerence.ark.assistant.controller.enumeration.PhoneCallActionEnum;

/* JADX INFO: loaded from: classes2.dex */
public class RejectIncomingCallEvent extends AppEvent {
    private RejectIncomingCallEvent() {
        super(PhoneCallActionEnum.REJECT_INCOMING_CALL);
    }

    public static class Builder {
        public RejectIncomingCallEvent build() {
            return new RejectIncomingCallEvent();
        }
    }
}
