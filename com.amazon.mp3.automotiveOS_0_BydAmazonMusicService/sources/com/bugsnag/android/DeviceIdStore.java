package com.bugsnag.android;

import android.content.Context;
import java.io.File;
import java.util.UUID;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: DeviceIdStore.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001BS\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014J\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/bugsnag/android/DeviceIdStore;", "", "context", "Landroid/content/Context;", "deviceIdfile", "Ljava/io/File;", "deviceIdGenerator", "Lkotlin/Function0;", "Ljava/util/UUID;", "internalDeviceIdfile", "internalDeviceIdGenerator", "sharedPrefMigrator", "Lcom/bugsnag/android/SharedPrefMigrator;", "logger", "Lcom/bugsnag/android/Logger;", "(Landroid/content/Context;Ljava/io/File;Lkotlin/jvm/functions/Function0;Ljava/io/File;Lkotlin/jvm/functions/Function0;Lcom/bugsnag/android/SharedPrefMigrator;Lcom/bugsnag/android/Logger;)V", "internalPersistence", "Lcom/bugsnag/android/DeviceIdPersistence;", "persistence", "loadDeviceId", "", "loadInternalDeviceId", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class DeviceIdStore {
    private final DeviceIdPersistence internalPersistence;
    private final DeviceIdPersistence persistence;
    private final SharedPrefMigrator sharedPrefMigrator;

    @JvmOverloads
    public DeviceIdStore(Context context, File file, Function0<UUID> function0, File file2, Function0<UUID> function02, SharedPrefMigrator sharedPrefMigrator, Logger logger) {
        this.sharedPrefMigrator = sharedPrefMigrator;
        this.persistence = new DeviceIdFilePersistence(file, function0, logger);
        this.internalPersistence = new DeviceIdFilePersistence(file2, function02, logger);
    }

    public /* synthetic */ DeviceIdStore(Context context, File file, Function0 function0, File file2, Function0 function02, SharedPrefMigrator sharedPrefMigrator, Logger logger, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? new File(context.getFilesDir(), "device-id") : file, (i & 4) != 0 ? new Function0<UUID>() { // from class: com.bugsnag.android.DeviceIdStore.1
            @Override // kotlin.jvm.functions.Function0
            public final UUID invoke() {
                UUID uuidRandomUUID = UUID.randomUUID();
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(uuidRandomUUID, "UUID.randomUUID()");
                return uuidRandomUUID;
            }
        } : function0, (i & 8) != 0 ? new File(context.getFilesDir(), "internal-device-id") : file2, (i & 16) != 0 ? new Function0<UUID>() { // from class: com.bugsnag.android.DeviceIdStore.2
            @Override // kotlin.jvm.functions.Function0
            public final UUID invoke() {
                UUID uuidRandomUUID = UUID.randomUUID();
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(uuidRandomUUID, "UUID.randomUUID()");
                return uuidRandomUUID;
            }
        } : function02, sharedPrefMigrator, logger);
    }

    public final String loadDeviceId() {
        String strLoadDeviceId = this.persistence.loadDeviceId(false);
        if (strLoadDeviceId != null) {
            return strLoadDeviceId;
        }
        String strLoadDeviceId2 = this.sharedPrefMigrator.loadDeviceId(false);
        return strLoadDeviceId2 != null ? strLoadDeviceId2 : this.persistence.loadDeviceId(true);
    }

    public final String loadInternalDeviceId() {
        return this.internalPersistence.loadDeviceId(true);
    }
}
