package cerence.ark.assistant.api.context.grpc;

/* JADX INFO: loaded from: classes2.dex */
public enum LocationSourceGrpc {
    GPS("LOCATION_SOURCE_GPS"),
    RECKONING("LOCATION_SOURCE_RECKONING"),
    LAST_KNOWN("LOCATION_SOURCE_LAST_KNOWN"),
    STATIC("LOCATION_SOURCE_STATIC");

    private final String mValue;

    LocationSourceGrpc(String str) {
        this.mValue = str;
    }

    public String getValue() {
        return this.mValue;
    }
}
