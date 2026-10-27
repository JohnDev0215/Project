package android.service.contentcapture;

import android.annotation.SystemApi;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
@SystemApi
public interface DataShareCallback {
    void onAccept(Executor executor, DataShareReadAdapter dataShareReadAdapter);

    void onReject();
}
