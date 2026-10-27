package com.android.settingslib;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import android.os.UserManager;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: com.android.settingslib.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    public static class C0045a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        @Nullable
        public ComponentName f1312a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        @Nullable
        public String f1313b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        @Nullable
        public UserHandle f1314c;
    }

    public static Intent a(Context context, C0045a c0045a) {
        Intent intent = new Intent("android.settings.SHOW_ADMIN_SUPPORT_DETAILS");
        if (c0045a != null) {
            ComponentName componentName = c0045a.f1312a;
            if (componentName != null) {
                intent.putExtra("android.app.extra.DEVICE_ADMIN", componentName);
            }
            intent.putExtra("android.intent.extra.USER", c0045a.f1314c);
        }
        return intent;
    }

    public static boolean b(Context context, int i) {
        return ((UserManager) context.getSystemService(UserManager.class)).getUserProfiles().contains(UserHandle.of(i));
    }

    public static void c(Context context, C0045a c0045a) {
        Intent intentA = a(context, c0045a);
        int iMyUserId = UserHandle.myUserId();
        if (c0045a != null) {
            UserHandle userHandle = c0045a.f1314c;
            if (userHandle != null && b(context, userHandle.getIdentifier())) {
                iMyUserId = c0045a.f1314c.getIdentifier();
            }
            intentA.putExtra("android.app.extra.RESTRICTION", c0045a.f1313b);
        }
        context.startActivityAsUser(intentA, UserHandle.of(iMyUserId));
    }
}
