package com.amplifyframework.rx;

import androidx.annotation.NonNull;
import com.amplifyframework.rx.RxStorageBinding;
import com.amplifyframework.storage.options.StorageDownloadFileOptions;
import com.amplifyframework.storage.options.StorageGetUrlOptions;
import com.amplifyframework.storage.options.StorageListOptions;
import com.amplifyframework.storage.options.StorageRemoveOptions;
import com.amplifyframework.storage.options.StorageUploadFileOptions;
import com.amplifyframework.storage.options.StorageUploadInputStreamOptions;
import com.amplifyframework.storage.result.StorageDownloadFileResult;
import com.amplifyframework.storage.result.StorageGetUrlResult;
import com.amplifyframework.storage.result.StorageListResult;
import com.amplifyframework.storage.result.StorageRemoveResult;
import com.amplifyframework.storage.result.StorageUploadFileResult;
import com.amplifyframework.storage.result.StorageUploadInputStreamResult;
import io.reactivex.rxjava3.core.Single;
import java.io.File;
import java.io.InputStream;

/* JADX INFO: loaded from: classes5.dex */
public interface RxStorageCategoryBehavior {
    @NonNull
    RxStorageBinding.RxProgressAwareSingleOperation<StorageDownloadFileResult> downloadFile(@NonNull String str, @NonNull File file);

    @NonNull
    RxStorageBinding.RxProgressAwareSingleOperation<StorageDownloadFileResult> downloadFile(@NonNull String str, @NonNull File file, @NonNull StorageDownloadFileOptions storageDownloadFileOptions);

    @NonNull
    Single<StorageGetUrlResult> getUrl(String str);

    @NonNull
    Single<StorageGetUrlResult> getUrl(@NonNull String str, @NonNull StorageGetUrlOptions storageGetUrlOptions);

    @NonNull
    Single<StorageListResult> list(@NonNull String str);

    @NonNull
    Single<StorageListResult> list(@NonNull String str, @NonNull StorageListOptions storageListOptions);

    @NonNull
    Single<StorageRemoveResult> remove(@NonNull String str);

    @NonNull
    Single<StorageRemoveResult> remove(@NonNull String str, @NonNull StorageRemoveOptions storageRemoveOptions);

    @NonNull
    RxStorageBinding.RxProgressAwareSingleOperation<StorageUploadFileResult> uploadFile(@NonNull String str, @NonNull File file);

    @NonNull
    RxStorageBinding.RxProgressAwareSingleOperation<StorageUploadFileResult> uploadFile(@NonNull String str, @NonNull File file, @NonNull StorageUploadFileOptions storageUploadFileOptions);

    @NonNull
    RxStorageBinding.RxProgressAwareSingleOperation<StorageUploadInputStreamResult> uploadInputStream(@NonNull String str, @NonNull InputStream inputStream);

    @NonNull
    RxStorageBinding.RxProgressAwareSingleOperation<StorageUploadInputStreamResult> uploadInputStream(@NonNull String str, @NonNull InputStream inputStream, @NonNull StorageUploadInputStreamOptions storageUploadInputStreamOptions);
}
