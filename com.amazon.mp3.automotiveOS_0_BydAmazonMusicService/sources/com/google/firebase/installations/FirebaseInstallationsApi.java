package com.google.firebase.installations;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.Task;

/* JADX INFO: loaded from: classes6.dex */
public interface FirebaseInstallationsApi {
    @NonNull
    Task<String> getId();

    @NonNull
    Task<InstallationTokenResult> getToken(boolean z);
}
