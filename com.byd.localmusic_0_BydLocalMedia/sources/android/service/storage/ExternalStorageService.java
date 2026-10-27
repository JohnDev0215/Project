package android.service.storage;

import android.annotation.SystemApi;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.ParcelableException;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.os.storage.StorageVolume;
import android.service.storage.IExternalStorageService;
import com.android.internal.os.BackgroundThread;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes3.dex */
@SystemApi
public abstract class ExternalStorageService extends Service {
    public static final String EXTRA_ERROR = "android.service.storage.extra.error";
    public static final String EXTRA_SESSION_ID = "android.service.storage.extra.session_id";
    public static final int FLAG_SESSION_ATTRIBUTE_INDEXABLE = 2;
    public static final int FLAG_SESSION_TYPE_FUSE = 1;
    public static final String SERVICE_INTERFACE = "android.service.storage.ExternalStorageService";
    private final ExternalStorageServiceWrapper mWrapper = new ExternalStorageServiceWrapper();
    private final Handler mHandler = BackgroundThread.getHandler();

    @Retention(RetentionPolicy.SOURCE)
    public @interface SessionFlag {
    }

    public abstract void onEndSession(String str) throws IOException;

    public abstract void onStartSession(String str, int i, ParcelFileDescriptor parcelFileDescriptor, File file, File file2) throws IOException;

    public abstract void onVolumeStateChanged(StorageVolume storageVolume) throws IOException;

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.mWrapper;
    }

    /* JADX INFO: Access modifiers changed from: private */
    class ExternalStorageServiceWrapper extends IExternalStorageService.Stub {
        private ExternalStorageServiceWrapper() {
        }

        @Override // android.service.storage.IExternalStorageService
        public void startSession(final String str, final int i, final ParcelFileDescriptor parcelFileDescriptor, final String str2, final String str3, final RemoteCallback remoteCallback) throws RemoteException {
            ExternalStorageService.this.mHandler.post(new Runnable() { // from class: android.service.storage.-$$Lambda$ExternalStorageService$ExternalStorageServiceWrapper$tdFhGuYAp6crc6-QZ-oDKVYQsTo
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$startSession$0$ExternalStorageService$ExternalStorageServiceWrapper(str, i, parcelFileDescriptor, str2, str3, remoteCallback);
                }
            });
        }

        public /* synthetic */ void lambda$startSession$0$ExternalStorageService$ExternalStorageServiceWrapper(String str, int i, ParcelFileDescriptor parcelFileDescriptor, String str2, String str3, RemoteCallback remoteCallback) {
            try {
                ExternalStorageService.this.onStartSession(str, i, parcelFileDescriptor, new File(str2), new File(str3));
                sendResult(str, null, remoteCallback);
            } catch (Throwable th) {
                sendResult(str, th, remoteCallback);
            }
        }

        @Override // android.service.storage.IExternalStorageService
        public void notifyVolumeStateChanged(final String str, final StorageVolume storageVolume, final RemoteCallback remoteCallback) {
            ExternalStorageService.this.mHandler.post(new Runnable() { // from class: android.service.storage.-$$Lambda$ExternalStorageService$ExternalStorageServiceWrapper$QTZTHzgj4CuoCrqZdfOB1w2jnLc
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$notifyVolumeStateChanged$1$ExternalStorageService$ExternalStorageServiceWrapper(storageVolume, str, remoteCallback);
                }
            });
        }

        public /* synthetic */ void lambda$notifyVolumeStateChanged$1$ExternalStorageService$ExternalStorageServiceWrapper(StorageVolume storageVolume, String str, RemoteCallback remoteCallback) {
            try {
                ExternalStorageService.this.onVolumeStateChanged(storageVolume);
                sendResult(str, null, remoteCallback);
            } catch (Throwable th) {
                sendResult(str, th, remoteCallback);
            }
        }

        @Override // android.service.storage.IExternalStorageService
        public void endSession(final String str, final RemoteCallback remoteCallback) throws RemoteException {
            ExternalStorageService.this.mHandler.post(new Runnable() { // from class: android.service.storage.-$$Lambda$ExternalStorageService$ExternalStorageServiceWrapper$gN8yhoyB-zewcbm1c8jB01Hc5Lw
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$endSession$2$ExternalStorageService$ExternalStorageServiceWrapper(str, remoteCallback);
                }
            });
        }

        public /* synthetic */ void lambda$endSession$2$ExternalStorageService$ExternalStorageServiceWrapper(String str, RemoteCallback remoteCallback) {
            try {
                ExternalStorageService.this.onEndSession(str);
                sendResult(str, null, remoteCallback);
            } catch (Throwable th) {
                sendResult(str, th, remoteCallback);
            }
        }

        private void sendResult(String str, Throwable th, RemoteCallback remoteCallback) {
            Bundle bundle = new Bundle();
            bundle.putString(ExternalStorageService.EXTRA_SESSION_ID, str);
            if (th != null) {
                bundle.putParcelable(ExternalStorageService.EXTRA_ERROR, new ParcelableException(th));
            }
            remoteCallback.sendResult(bundle);
        }
    }
}
