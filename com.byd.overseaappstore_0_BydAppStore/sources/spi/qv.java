package spi;

import android.net.Uri;
import android.os.IInterface;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public final class qv {
    public static op<qv> b;
    public ConcurrentHashMap<String, IInterface> a = new ConcurrentHashMap<>();

    public static class a extends op<qv> {
        @Override // spi.op
        public final qv a() {
            return new qv();
        }
    }

    static {
        Uri.parse("content://com.byd.spi.ipc.provider.BinderProvider/sync_binder");
        b = new a();
    }
}
