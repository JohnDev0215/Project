package com.amazon.device.utils.det;

/* JADX INFO: loaded from: classes2.dex */
public interface StatusNotifier {
    void broadcastUploadStatus(CharSequence charSequence);

    void fireNotification(CharSequence charSequence, int i);

    void updateFilesCount(int i);
}
