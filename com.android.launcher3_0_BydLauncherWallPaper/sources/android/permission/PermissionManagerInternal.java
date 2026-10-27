package android.permission;

import android.os.UserHandle;
import com.android.internal.util.function.TriFunction;
import java.util.function.BiFunction;

/* JADX INFO: loaded from: classes2.dex */
public abstract class PermissionManagerInternal {

    public interface CheckPermissionDelegate {
        int checkPermission(String str, String str2, int i, TriFunction<String, String, Integer, Integer> triFunction);

        int checkUidPermission(String str, int i, BiFunction<String, Integer, Integer> biFunction);
    }

    public interface OnRuntimePermissionStateChangedListener {
        void onRuntimePermissionStateChanged(String str, int i);
    }

    public abstract void addOnRuntimePermissionStateChangedListener(OnRuntimePermissionStateChangedListener onRuntimePermissionStateChangedListener);

    public abstract byte[] backupRuntimePermissions(UserHandle userHandle);

    public abstract void removeOnRuntimePermissionStateChangedListener(OnRuntimePermissionStateChangedListener onRuntimePermissionStateChangedListener);

    public abstract void restoreDelayedRuntimePermissions(String str, UserHandle userHandle);

    public abstract void restoreRuntimePermissions(byte[] bArr, UserHandle userHandle);
}
