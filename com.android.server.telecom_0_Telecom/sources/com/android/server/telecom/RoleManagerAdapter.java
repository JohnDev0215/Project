package com.android.server.telecom;

import android.os.UserHandle;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes.dex */
public interface RoleManagerAdapter {
    void addOrRemoveTestCallCompanionApp(String str, boolean z);

    String getApplicationLabelForPackageName(String str);

    List<String> getCallCompanionApps();

    String getDefaultCallRedirectionApp();

    String getDefaultCallScreeningApp();

    String getDefaultDialerApp(int i);

    void observeDefaultDialerApp(Executor executor, IntConsumer intConsumer);

    void setCurrentUserHandle(UserHandle userHandle);

    void setTestDefaultCallRedirectionApp(String str);

    void setTestDefaultCallScreeningApp(String str);

    void setTestDefaultDialer(String str);
}
