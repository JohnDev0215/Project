package cerence.ark.assistant.api.notification;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsNotification implements INotification {
    protected static final String TAG = "AbsNotification";
    private final String mName;
    private final String mParams;

    protected abstract void parseParam(String str);

    protected AbsNotification(String str, String str2) {
        this.mName = str;
        this.mParams = str2;
        parseParam(str2);
    }

    @Override // cerence.ark.assistant.api.notification.INotification
    public String getName() {
        return this.mName;
    }

    @Override // cerence.ark.assistant.api.notification.INotification
    public String getParams() {
        return this.mParams;
    }
}
