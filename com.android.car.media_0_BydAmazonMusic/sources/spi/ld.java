package spi;

import android.net.Uri;
import android.os.IInterface;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: RemoteServiceManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ld {
    public static fa<ld> b;
    public ConcurrentHashMap<String, IInterface> a = new ConcurrentHashMap<>();

    /* JADX INFO: compiled from: RemoteServiceManager.java */
    public static class a extends fa<ld> {
        @Override // spi.fa
        public final ld a() {
            return new ld();
        }
    }

    static {
        Uri.parse("content://com.byd.spi.ipc.provider.BinderProvider/sync_binder");
        b = new a();
    }
}
