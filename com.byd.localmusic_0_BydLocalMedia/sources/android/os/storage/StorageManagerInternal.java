package android.os.storage;

import android.os.IVold;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public abstract class StorageManagerInternal {

    public interface ExternalStorageMountPolicy {
        int getMountMode(int i, String str);

        boolean hasExternalStorage(int i, String str);
    }

    public interface ResetListener {
        void onReset(IVold iVold);
    }

    public abstract void addExternalStoragePolicy(ExternalStorageMountPolicy externalStorageMountPolicy);

    public abstract void addResetListener(ResetListener resetListener);

    public abstract int getExternalStorageMountMode(int i, String str);

    public abstract boolean hasLegacyExternalStorage(int i);

    public abstract boolean isExternalStorageService(int i);

    public abstract void onAppOpsChanged(int i, int i2, String str, int i3, int i4);

    public abstract void onExternalStoragePolicyChanged(int i, String str);

    public abstract void prepareAppDataAfterInstall(String str, int i);

    public abstract boolean prepareStorageDirs(int i, Set<String> set, String str);

    public abstract void resetUser(int i);
}
