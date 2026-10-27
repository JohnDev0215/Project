package androidx.test.internal.platform.content;

/* JADX INFO: loaded from: classes5.dex */
public interface PermissionGranter {
    void addPermissions(String... permissions);

    void requestPermissions();
}
