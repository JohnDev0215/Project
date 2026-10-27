package android.app.role;

import android.annotation.SystemApi;
import android.os.UserHandle;

/* JADX INFO: loaded from: classes.dex */
@SystemApi
public interface OnRoleHoldersChangedListener {
    void onRoleHoldersChanged(String str, UserHandle userHandle);
}
