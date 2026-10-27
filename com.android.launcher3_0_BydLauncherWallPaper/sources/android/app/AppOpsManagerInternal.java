package android.app;

import android.util.SparseArray;
import android.util.SparseIntArray;
import com.android.internal.app.IAppOpsCallback;
import com.android.internal.util.function.HeptFunction;
import com.android.internal.util.function.QuadFunction;

/* JADX INFO: loaded from: classes.dex */
public abstract class AppOpsManagerInternal {

    public interface CheckOpsDelegate {
        int checkAudioOperation(int i, int i2, int i3, String str, QuadFunction<Integer, Integer, Integer, String, Integer> quadFunction);

        int checkOperation(int i, int i2, String str, boolean z, QuadFunction<Integer, Integer, String, Boolean, Integer> quadFunction);

        int noteOperation(int i, int i2, String str, String str2, boolean z, String str3, boolean z2, HeptFunction<Integer, Integer, String, String, Boolean, String, Boolean, Integer> heptFunction);
    }

    public abstract void setDeviceAndProfileOwners(SparseIntArray sparseIntArray);

    public abstract void setModeFromPermissionPolicy(int i, int i2, String str, int i3, IAppOpsCallback iAppOpsCallback);

    public abstract void setUidModeFromPermissionPolicy(int i, int i2, int i3, IAppOpsCallback iAppOpsCallback);

    public abstract void updateAppWidgetVisibility(SparseArray<String> sparseArray, boolean z);
}
