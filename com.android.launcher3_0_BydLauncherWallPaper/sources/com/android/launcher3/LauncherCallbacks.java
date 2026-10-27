package com.android.launcher3;

import android.content.Intent;
import android.os.Bundle;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes4.dex */
public interface LauncherCallbacks {
    void dump(String prefix, FileDescriptor fd, PrintWriter w, String[] args);

    boolean handleBackPressed();

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onAttachedToWindow();

    void onCreate(Bundle savedInstanceState);

    void onDestroy();

    void onDetachedFromWindow();

    void onHomeIntent(boolean internalStateHandled);

    void onLauncherProviderChange();

    void onPause();

    void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);

    void onResume();

    void onSaveInstanceState(Bundle outState);

    void onStart();

    default void onStateChanged() {
    }

    void onStop();

    void onTrimMemory(int level);

    boolean startSearch(String initialQuery, boolean selectInitialQuery, Bundle appSearchData);
}
