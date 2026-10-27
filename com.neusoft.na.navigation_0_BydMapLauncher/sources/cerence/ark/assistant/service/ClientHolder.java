package cerence.ark.assistant.service;

/* JADX INFO: loaded from: classes2.dex */
class ClientHolder {
    private final String mClientUid;
    private final IRequestListener mRequestListener;

    public ClientHolder(String str, IRequestListener iRequestListener) {
        this.mClientUid = str;
        this.mRequestListener = iRequestListener;
    }

    public String getClientUid() {
        return this.mClientUid;
    }

    public IRequestListener getRequestListener() {
        return this.mRequestListener;
    }
}
