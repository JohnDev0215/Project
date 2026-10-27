package com.byd.spi.ipc;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import androidx.annotation.NonNull;
import com.byd.foundation.utils.LogUtils;
import com.byd.foundation.utils.Singleton;
import com.byd.spi.BuildConfig;
import com.byd.spi.Spi;
import com.byd.spi.config.IpcConfig;
import com.byd.spi.ipc.cursor.BinderCursor;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public class RemoteServiceManager {
    private static final String AUTHORITY = "com.byd.spi.ipc.provider.BinderProvider";
    private static final Uri CONTENT_URI = Uri.parse("content://com.byd.spi.ipc.provider.BinderProvider/sync_binder");
    private static Singleton<RemoteServiceManager> gDefault = new Singleton<RemoteServiceManager>() { // from class: com.byd.spi.ipc.RemoteServiceManager.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.byd.foundation.utils.Singleton
        public RemoteServiceManager create() {
            return new RemoteServiceManager();
        }
    };
    private ConcurrentHashMap<String, IInterface> mBinderCache = new ConcurrentHashMap<>();

    public static RemoteServiceManager get() {
        return gDefault.get();
    }

    public <T> T getRemoteService(@NonNull Context context, @NonNull Class<T> cls) {
        Uri uri;
        Cursor cursorQuery;
        IInterface iInterface = this.mBinderCache.get(cls.getCanonicalName());
        if (iInterface != null && iInterface.asBinder().isBinderAlive()) {
            return cls.cast(iInterface);
        }
        ContentResolver contentResolver = context.getContentResolver();
        IpcConfig ipcConfig = Spi.getConfig().ipc;
        if (ipcConfig == null || (uri = ipcConfig.contentUri) == null || (cursorQuery = contentResolver.query(uri, null, null, new String[]{cls.getCanonicalName()}, null)) == null) {
            return null;
        }
        Bundle extras = cursorQuery.getExtras();
        extras.setClassLoader(BinderCursor.BinderParcelable.class.getClassLoader());
        try {
            IInterface iInterface2 = (IInterface) Class.forName(cls.getName() + "$Stub").getDeclaredMethod("asInterface", IBinder.class).invoke(null, ((BinderCursor.BinderParcelable) extras.getParcelable("binder")).getBinder());
            this.mBinderCache.put(cls.getCanonicalName(), iInterface2);
            return cls.cast(iInterface2);
        } catch (Throwable th) {
            LogUtils.e(BuildConfig.TAG, "IPC服务转换异常", th);
            return null;
        }
    }
}
