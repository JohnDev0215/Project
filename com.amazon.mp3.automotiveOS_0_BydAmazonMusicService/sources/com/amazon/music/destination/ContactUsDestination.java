package com.amazon.music.destination;

import com.amazon.music.destination.core.Destination;

/* JADX INFO: loaded from: classes4.dex */
public class ContactUsDestination extends Destination {
    public static final String CHAT = "chat";
    private final ContactUsTarget target;

    public ContactUsDestination(ContactUsTarget contactUsTarget, String str, String str2) {
        super(str, str2);
        this.target = contactUsTarget;
    }

    public ContactUsDestination(ContactUsTarget contactUsTarget) {
        super(null, null);
        this.target = contactUsTarget;
    }

    public ContactUsTarget getTarget() {
        return this.target;
    }

    public enum ContactUsTarget {
        LANDING(""),
        CHAT(ContactUsDestination.CHAT);

        private final String value;

        ContactUsTarget(String str) {
            this.value = str;
        }

        public String getValue() {
            return this.value;
        }

        public static ContactUsTarget fromString(String str) {
            for (ContactUsTarget contactUsTarget : values()) {
                if (contactUsTarget.value.equals(str)) {
                    return contactUsTarget;
                }
            }
            return null;
        }
    }
}
