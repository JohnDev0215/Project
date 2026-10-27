package org.chromium.media;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class HdrMetadata {
    public long a;
    public final Object b = new Object();

    public HdrMetadata(long j) {
        this.a = j;
    }

    private void close() {
        synchronized (this.b) {
            this.a = 0L;
        }
    }

    public static HdrMetadata create(long j) {
        return new HdrMetadata(j);
    }
}
