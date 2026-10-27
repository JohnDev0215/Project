package com.amplifyframework.datastore.storage;

import androidx.annotation.NonNull;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.datastore.DataStoreItemChange;
import com.amplifyframework.datastore.storage.StorageItemChange;

/* JADX INFO: loaded from: classes5.dex */
public final class ItemChangeMapper {
    private ItemChangeMapper() {
    }

    public static <T extends Model> DataStoreItemChange<T> map(@NonNull StorageItemChange<T> storageItemChange) throws DataStoreException {
        return DataStoreItemChange.builder().initiator(map(storageItemChange.initiator())).item(storageItemChange.item()).itemClass(storageItemChange.modelSchema().getModelClass()).type(map(storageItemChange.type())).uuid(storageItemChange.changeId().toString()).build();
    }

    private static DataStoreItemChange.Initiator map(StorageItemChange.Initiator initiator) throws DataStoreException {
        int i = AnonymousClass1.$SwitchMap$com$amplifyframework$datastore$storage$StorageItemChange$Initiator[initiator.ordinal()];
        if (i == 1) {
            return DataStoreItemChange.Initiator.REMOTE;
        }
        if (i == 2) {
            return DataStoreItemChange.Initiator.LOCAL;
        }
        throw new DataStoreException("Unknown initiator of storage change: " + initiator, AmplifyException.TODO_RECOVERY_SUGGESTION);
    }

    /* JADX INFO: renamed from: com.amplifyframework.datastore.storage.ItemChangeMapper$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amplifyframework$datastore$storage$StorageItemChange$Initiator;
        static final /* synthetic */ int[] $SwitchMap$com$amplifyframework$datastore$storage$StorageItemChange$Type;

        static {
            int[] iArr = new int[StorageItemChange.Type.values().length];
            $SwitchMap$com$amplifyframework$datastore$storage$StorageItemChange$Type = iArr;
            try {
                iArr[StorageItemChange.Type.DELETE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amplifyframework$datastore$storage$StorageItemChange$Type[StorageItemChange.Type.UPDATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amplifyframework$datastore$storage$StorageItemChange$Type[StorageItemChange.Type.CREATE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[StorageItemChange.Initiator.values().length];
            $SwitchMap$com$amplifyframework$datastore$storage$StorageItemChange$Initiator = iArr2;
            try {
                iArr2[StorageItemChange.Initiator.SYNC_ENGINE.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amplifyframework$datastore$storage$StorageItemChange$Initiator[StorageItemChange.Initiator.DATA_STORE_API.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private static DataStoreItemChange.Type map(StorageItemChange.Type type) throws DataStoreException {
        int i = AnonymousClass1.$SwitchMap$com$amplifyframework$datastore$storage$StorageItemChange$Type[type.ordinal()];
        if (i == 1) {
            return DataStoreItemChange.Type.DELETE;
        }
        if (i == 2) {
            return DataStoreItemChange.Type.UPDATE;
        }
        if (i == 3) {
            return DataStoreItemChange.Type.CREATE;
        }
        throw new DataStoreException("Unknown type of storage change: " + type, AmplifyException.TODO_RECOVERY_SUGGESTION);
    }
}
