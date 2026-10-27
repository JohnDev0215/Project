package org.chromium.components.browser_ui.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

/* JADX INFO: loaded from: classes2.dex */
public interface SettingsLauncher {
    Intent a(Context context, String str, Bundle bundle);

    void b(Context context, Class<? extends Fragment> cls, Bundle bundle);

    Intent e(FragmentActivity fragmentActivity, String str);

    void g(Context context, Class<? extends Fragment> cls);

    void i(Context context);
}
