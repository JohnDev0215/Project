package com.amplifyframework.storage;

import androidx.annotation.NonNull;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.category.Category;
import com.amplifyframework.core.category.CategoryType;
import com.amplifyframework.storage.operation.StorageDownloadFileOperation;
import com.amplifyframework.storage.operation.StorageGetUrlOperation;
import com.amplifyframework.storage.operation.StorageListOperation;
import com.amplifyframework.storage.operation.StorageRemoveOperation;
import com.amplifyframework.storage.operation.StorageUploadFileOperation;
import com.amplifyframework.storage.operation.StorageUploadInputStreamOperation;
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
import com.amplifyframework.storage.result.StorageTransferProgress;
import com.amplifyframework.storage.result.StorageUploadFileResult;
import com.amplifyframework.storage.result.StorageUploadInputStreamResult;
import java.io.File;
import java.io.InputStream;

/* JADX INFO: loaded from: classes5.dex */
public final class StorageCategory extends Category<StoragePlugin<?>> implements StorageCategoryBehavior {
    @Override // com.amplifyframework.core.category.CategoryTypeable
    @NonNull
    public CategoryType getCategoryType() {
        return CategoryType.STORAGE;
    }

    @Override // com.amplifyframework.storage.StorageCategoryBehavior
    @NonNull
    public StorageGetUrlOperation<?> getUrl(@NonNull String str, @NonNull Consumer<StorageGetUrlResult> consumer, @NonNull Consumer<StorageException> consumer2) {
        return getSelectedPlugin().getUrl(str, consumer, consumer2);
    }

    @Override // com.amplifyframework.storage.StorageCategoryBehavior
    @NonNull
    public StorageGetUrlOperation<?> getUrl(@NonNull String str, @NonNull StorageGetUrlOptions storageGetUrlOptions, @NonNull Consumer<StorageGetUrlResult> consumer, @NonNull Consumer<StorageException> consumer2) {
        return getSelectedPlugin().getUrl(str, storageGetUrlOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.storage.StorageCategoryBehavior
    @NonNull
    public StorageDownloadFileOperation<?> downloadFile(@NonNull String str, @NonNull File file, @NonNull Consumer<StorageDownloadFileResult> consumer, @NonNull Consumer<StorageException> consumer2) {
        return getSelectedPlugin().downloadFile(str, file, consumer, consumer2);
    }

    @Override // com.amplifyframework.storage.StorageCategoryBehavior
    @NonNull
    public StorageDownloadFileOperation<?> downloadFile(@NonNull String str, @NonNull File file, @NonNull StorageDownloadFileOptions storageDownloadFileOptions, @NonNull Consumer<StorageDownloadFileResult> consumer, @NonNull Consumer<StorageException> consumer2) {
        return getSelectedPlugin().downloadFile(str, file, storageDownloadFileOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.storage.StorageCategoryBehavior
    @NonNull
    public StorageDownloadFileOperation<?> downloadFile(@NonNull String str, @NonNull File file, @NonNull StorageDownloadFileOptions storageDownloadFileOptions, @NonNull Consumer<StorageTransferProgress> consumer, @NonNull Consumer<StorageDownloadFileResult> consumer2, @NonNull Consumer<StorageException> consumer3) {
        return getSelectedPlugin().downloadFile(str, file, storageDownloadFileOptions, consumer, consumer2, consumer3);
    }

    @Override // com.amplifyframework.storage.StorageCategoryBehavior
    @NonNull
    public StorageUploadFileOperation<?> uploadFile(@NonNull String str, @NonNull File file, @NonNull Consumer<StorageUploadFileResult> consumer, @NonNull Consumer<StorageException> consumer2) {
        return getSelectedPlugin().uploadFile(str, file, consumer, consumer2);
    }

    @Override // com.amplifyframework.storage.StorageCategoryBehavior
    @NonNull
    public StorageUploadFileOperation<?> uploadFile(@NonNull String str, @NonNull File file, @NonNull StorageUploadFileOptions storageUploadFileOptions, @NonNull Consumer<StorageUploadFileResult> consumer, @NonNull Consumer<StorageException> consumer2) {
        return getSelectedPlugin().uploadFile(str, file, storageUploadFileOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.storage.StorageCategoryBehavior
    @NonNull
    public StorageUploadFileOperation<?> uploadFile(@NonNull String str, @NonNull File file, @NonNull StorageUploadFileOptions storageUploadFileOptions, @NonNull Consumer<StorageTransferProgress> consumer, @NonNull Consumer<StorageUploadFileResult> consumer2, @NonNull Consumer<StorageException> consumer3) {
        return getSelectedPlugin().uploadFile(str, file, storageUploadFileOptions, consumer, consumer2, consumer3);
    }

    @Override // com.amplifyframework.storage.StorageCategoryBehavior
    @NonNull
    public StorageUploadInputStreamOperation<?> uploadInputStream(@NonNull String str, @NonNull InputStream inputStream, @NonNull Consumer<StorageUploadInputStreamResult> consumer, @NonNull Consumer<StorageException> consumer2) {
        return getSelectedPlugin().uploadInputStream(str, inputStream, consumer, consumer2);
    }

    @Override // com.amplifyframework.storage.StorageCategoryBehavior
    @NonNull
    public StorageUploadInputStreamOperation<?> uploadInputStream(@NonNull String str, @NonNull InputStream inputStream, @NonNull StorageUploadInputStreamOptions storageUploadInputStreamOptions, @NonNull Consumer<StorageUploadInputStreamResult> consumer, @NonNull Consumer<StorageException> consumer2) {
        return getSelectedPlugin().uploadInputStream(str, inputStream, storageUploadInputStreamOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.storage.StorageCategoryBehavior
    @NonNull
    public StorageUploadInputStreamOperation<?> uploadInputStream(@NonNull String str, @NonNull InputStream inputStream, @NonNull StorageUploadInputStreamOptions storageUploadInputStreamOptions, @NonNull Consumer<StorageTransferProgress> consumer, @NonNull Consumer<StorageUploadInputStreamResult> consumer2, @NonNull Consumer<StorageException> consumer3) {
        return getSelectedPlugin().uploadInputStream(str, inputStream, storageUploadInputStreamOptions, consumer, consumer2, consumer3);
    }

    @Override // com.amplifyframework.storage.StorageCategoryBehavior
    @NonNull
    public StorageRemoveOperation<?> remove(@NonNull String str, @NonNull Consumer<StorageRemoveResult> consumer, @NonNull Consumer<StorageException> consumer2) {
        return getSelectedPlugin().remove(str, consumer, consumer2);
    }

    @Override // com.amplifyframework.storage.StorageCategoryBehavior
    @NonNull
    public StorageRemoveOperation<?> remove(@NonNull String str, @NonNull StorageRemoveOptions storageRemoveOptions, @NonNull Consumer<StorageRemoveResult> consumer, @NonNull Consumer<StorageException> consumer2) {
        return getSelectedPlugin().remove(str, storageRemoveOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.storage.StorageCategoryBehavior
    @NonNull
    public StorageListOperation<?> list(@NonNull String str, @NonNull Consumer<StorageListResult> consumer, @NonNull Consumer<StorageException> consumer2) {
        return getSelectedPlugin().list(str, consumer, consumer2);
    }

    @Override // com.amplifyframework.storage.StorageCategoryBehavior
    @NonNull
    public StorageListOperation<?> list(@NonNull String str, @NonNull StorageListOptions storageListOptions, @NonNull Consumer<StorageListResult> consumer, @NonNull Consumer<StorageException> consumer2) {
        return getSelectedPlugin().list(str, storageListOptions, consumer, consumer2);
    }
}
