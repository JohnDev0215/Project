package R7;

/* JADX INFO: loaded from: classes.dex */
public enum d {
    FIELD(null),
    FILE(null),
    PROPERTY(null),
    PROPERTY_GETTER("get"),
    PROPERTY_SETTER("set"),
    RECEIVER(null),
    CONSTRUCTOR_PARAMETER("param"),
    SETTER_PARAMETER("setparam"),
    PROPERTY_DELEGATE_FIELD("delegate");

    public final String l;

    d(String str) {
        this.l = str == null ? io.sentry.config.a.n0(name()) : str;
    }
}
