package spi;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import com.byd.foundation.utils.LogUtils;
import com.byd.foundation.utils.Singleton;
import com.byd.spi.Spi;
import com.byd.spi.config.IpcConfig;
import com.byd.spi.ipc.cursor.BinderCursor;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: RemoteServiceManager.java */
/* JADX INFO: loaded from: classes.dex */
public class e {
    private static final String b = "com.byd.spi.ipc.provider.BinderProvider";
    private static final Uri c = Uri.parse("content://com.byd.spi.ipc.provider.BinderProvider/sync_binder");
    private static Singleton<e> d = new a();
    private ConcurrentHashMap<String, IInterface> a = new ConcurrentHashMap<>();

    /* JADX INFO: compiled from: RemoteServiceManager.java */
    public static class a extends Singleton<e> {
        @Override // com.byd.foundation.utils.Singleton
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public e create() {
            return new e();
        }
    }

    /* JADX INFO: compiled from: RemoteServiceManager.java */
    public class b implements IBinder.DeathRecipient {
        public final /* synthetic */ Class a;

        public b(Class cls) {
            this.a = cls;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            e.this.a.remove(this.a.getCanonicalName());
        }
    }

    public static e a() {
        return d.get();
    }

    public <T> T a(Context context, Class<T> cls) {
        Uri uri;
        IInterface iInterface = this.a.get(cls.getCanonicalName());
        if (iInterface != null) {
            if (iInterface.asBinder().isBinderAlive()) {
                LogUtils.i(spi.a.e, "spi get service from remote alive cache: " + cls.getCanonicalName() + " instance:" + iInterface);
                return cls.cast(iInterface);
            }
            this.a.remove(cls.getCanonicalName());
        }
        ContentResolver contentResolver = context.getContentResolver();
        IpcConfig ipcConfig = Spi.getConfig().ipc;
        if (ipcConfig == null || (uri = ipcConfig.contentUri) == null) {
            LogUtils.i(spi.a.e, "spi get service from remote failed: ipc uri is null!");
            return null;
        }
        Cursor cursorQuery = contentResolver.query(uri, null, null, new String[]{cls.getCanonicalName()}, null);
        if (cursorQuery == null) {
            LogUtils.i(spi.a.e, "spi get service from remote failed: cannot find car service!");
            return null;
        }
        Bundle extras = cursorQuery.getExtras();
        extras.setClassLoader(BinderCursor.BinderParcelable.class.getClassLoader());
        try {
            IBinder binder = ((BinderCursor.BinderParcelable) extras.getParcelable(BinderCursor.KEY_BINDER)).getBinder();
            IInterface iInterface2 = (IInterface) Class.forName(cls.getName() + "$Stub").getDeclaredMethod("asInterface", IBinder.class).invoke(null, binder);
            binder.linkToDeath(new b(cls), 0);
            this.a.put(cls.getCanonicalName(), iInterface2);
            LogUtils.i(spi.a.e, "spi get service from remote: " + cls.getCanonicalName() + " instance:" + iInterface2);
            return cls.cast(iInterface2);
        } catch (Throwable th) {
            try {
                LogUtils.e(spi.a.e, "spi get service from remote failed:", th);
                try {
                    cursorQuery.close();
                } catch (Throwable unused) {
                }
                return null;
            } finally {
                try {
                    cursorQuery.close();
                } catch (Throwable unused2) {
                }
            }
        }
    }
}
