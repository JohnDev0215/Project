package cerence.ark.assistant.api.context.http;

/* JADX INFO: loaded from: classes.dex */
public enum LocationSource {
    GPS("gps"),
    RECKONING("reckoning"),
    LAST_KNOWN("lastknown"),
    STATIC("static");

    private final String mValue;

    LocationSource(String str) {
        this.mValue = str;
    }

    public String getValue() {
        return this.mValue;
    }
}
