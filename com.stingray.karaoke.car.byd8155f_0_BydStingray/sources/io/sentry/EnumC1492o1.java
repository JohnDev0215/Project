package io.sentry;

/* JADX INFO: renamed from: io.sentry.o1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public enum EnumC1492o1 implements InterfaceC1485m0 {
    Session("session"),
    Event("event"),
    UserFeedback("user_report"),
    Attachment("attachment"),
    Transaction("transaction"),
    Profile("profile"),
    ClientReport("client_report"),
    ReplayEvent("replay_event"),
    ReplayRecording("replay_recording"),
    ReplayVideo("replay_video"),
    CheckIn("check_in"),
    Statsd("statsd"),
    Feedback("feedback"),
    Unknown("__unknown__");

    private final String itemType;

    EnumC1492o1(String str) {
        this.itemType = str;
    }

    public static EnumC1492o1 resolve(Object obj) {
        return obj instanceof C1480k1 ? Event : obj instanceof io.sentry.protocol.A ? Transaction : obj instanceof R1 ? Session : obj instanceof io.sentry.clientreport.b ? ClientReport : Attachment;
    }

    public static EnumC1492o1 valueOfLabel(String str) {
        for (EnumC1492o1 enumC1492o1 : values()) {
            if (enumC1492o1.itemType.equals(str)) {
                return enumC1492o1;
            }
        }
        return Unknown;
    }

    public String getItemType() {
        return this.itemType;
    }

    @Override // io.sentry.InterfaceC1485m0
    public void serialize(C0 c02, K k10) {
        ((C1489n1) c02).L(this.itemType);
    }
}
