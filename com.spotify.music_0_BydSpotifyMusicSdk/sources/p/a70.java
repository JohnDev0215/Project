package p;

import okhttp3.Call;
import okhttp3.OkHttpClient;

/* JADX INFO: loaded from: classes.dex */
public final class a70 implements f21 {
    public final Call.Factory a;

    public a70(OkHttpClient okHttpClient) {
        v03.j(okHttpClient, "okHttpClient");
        this.a = okHttpClient;
    }
}
