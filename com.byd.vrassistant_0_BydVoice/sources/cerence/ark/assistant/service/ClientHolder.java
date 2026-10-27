package cerence.ark.assistant.service;

/* JADX INFO: loaded from: classes.dex */
class ClientHolder {
    private final String mPackageName;
    private final IRequestListener mRequestListener;

    public ClientHolder(String str, IRequestListener iRequestListener) {
        this.mPackageName = str;
        this.mRequestListener = iRequestListener;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public IRequestListener getRequestListener() {
        return this.mRequestListener;
    }
}
