package android.service.contentcapture;

import android.annotation.SystemApi;
import android.os.ParcelFileDescriptor;

/* JADX INFO: loaded from: classes3.dex */
@SystemApi
public interface DataShareReadAdapter {
    void onError(int i);

    void onStart(ParcelFileDescriptor parcelFileDescriptor);
}
