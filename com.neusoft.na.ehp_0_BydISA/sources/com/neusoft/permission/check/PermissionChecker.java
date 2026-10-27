package com.neusoft.permission.check;

import android.util.Log;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class PermissionChecker {
    private final String TAG = "NeuPermissionChecker";

    public native void startChecker(String path);

    static {
        System.loadLibrary("c++_shared");
        System.loadLibrary("native-lib");
    }

    public void start(final String cPath, final String javaPath) {
        Log.i("NeuPermissionChecker", "[start] --> cPath == " + cPath + ", javaPath ==" + javaPath);
        new Thread(new Runnable() { // from class: com.neusoft.permission.check.PermissionChecker$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m69lambda$start$0$comneusoftpermissioncheckPermissionChecker(javaPath, cPath);
            }
        }).start();
    }

    /* JADX INFO: renamed from: lambda$start$0$com-neusoft-permission-check-PermissionChecker, reason: not valid java name */
    /* synthetic */ void m69lambda$start$0$comneusoftpermissioncheckPermissionChecker(String str, String str2) {
        Log.i("NeuPermissionChecker", "startChecker start...");
        checkAndroidPermission(str);
        startChecker(str2);
        Log.i("NeuPermissionChecker", "startChecker end.");
    }

    private void checkAndroidPermission(String path) {
        Log.i("NeuPermissionChecker", "[checkAndroidPermission]");
        if (!new File(path).exists()) {
            Log.e("NeuPermissionChecker", "[checkAndroidPermission] --> path not exists!");
            return;
        }
        File file = new File(path, (System.currentTimeMillis() / 1000) + ".txt");
        try {
            if (file.createNewFile()) {
                Log.i("NeuPermissionChecker", "file create success in " + path);
                Log.i("NeuPermissionChecker", "delete test file " + (file.delete() ? "success" : "fail!"));
                return;
            }
            Log.e("NeuPermissionChecker", "file create fail in " + path + "!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
