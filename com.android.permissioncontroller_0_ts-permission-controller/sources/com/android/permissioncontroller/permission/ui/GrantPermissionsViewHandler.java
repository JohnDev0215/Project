package com.android.permissioncontroller.permission.ui;

import android.content.res.Configuration;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public interface GrantPermissionsViewHandler {
    public static final int CANCELED = -1;
    public static final int DENIED = 2;
    public static final int DENIED_DO_NOT_ASK_AGAIN = 3;
    public static final int GRANTED_ALWAYS = 0;
    public static final int GRANTED_FOREGROUND_ONLY = 1;
    public static final int GRANTED_ONE_TIME = 4;
    public static final int LINKED_TO_SETTINGS = -2;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Result {
    }

    public interface ResultListener {
        void onPermissionGrantResult(String str, int i);
    }

    View createView();

    void loadInstanceState(Bundle bundle);

    void onBackPressed();

    void onConfigurationChanged(Configuration configuration);

    void onDestroy();

    void saveInstanceState(Bundle bundle);

    GrantPermissionsViewHandler setResultListener(ResultListener resultListener);

    void updateUi(String str, int i, int i2, Icon icon, CharSequence charSequence, CharSequence charSequence2, boolean[] zArr);

    void updateUiList(List<String> list);

    void updateWindowAttributes(WindowManager.LayoutParams layoutParams);
}
