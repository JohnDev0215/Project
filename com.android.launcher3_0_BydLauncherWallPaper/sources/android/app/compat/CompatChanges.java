package android.app.compat;

import android.annotation.SystemApi;
import android.compat.Compatibility;
import android.os.UserHandle;

/* JADX INFO: loaded from: classes.dex */
@SystemApi
public final class CompatChanges {
    private static final ChangeIdStateCache QUERY_CACHE = new ChangeIdStateCache();

    private CompatChanges() {
    }

    public static boolean isChangeEnabled(long j) {
        return Compatibility.isChangeEnabled(j);
    }

    public static boolean isChangeEnabled(long j, String str, UserHandle userHandle) {
        return QUERY_CACHE.query(ChangeIdStateQuery.byPackageName(j, str, userHandle.getIdentifier())).booleanValue();
    }

    public static boolean isChangeEnabled(long j, int i) {
        return QUERY_CACHE.query(ChangeIdStateQuery.byUid(j, i)).booleanValue();
    }
}
