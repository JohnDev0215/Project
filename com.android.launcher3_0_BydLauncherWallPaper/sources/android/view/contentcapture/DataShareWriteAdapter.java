package android.view.contentcapture;

import android.os.ParcelFileDescriptor;

/* JADX INFO: loaded from: classes3.dex */
public interface DataShareWriteAdapter {
    default void onError(int i) {
    }

    void onRejected();

    void onWrite(ParcelFileDescriptor parcelFileDescriptor);
}
