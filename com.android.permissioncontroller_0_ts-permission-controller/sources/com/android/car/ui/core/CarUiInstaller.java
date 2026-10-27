package com.android.car.ui.core;

import android.app.Activity;
import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import com.android.car.ui.baselayout.Insets;
import java.lang.reflect.Method;
import java.util.Locale;

/* JADX INFO: loaded from: classes7.dex */
public class CarUiInstaller extends ContentProvider {
    private static final String CAR_UI_INSET_BOTTOM = "CAR_UI_INSET_BOTTOM";
    private static final String CAR_UI_INSET_LEFT = "CAR_UI_INSET_LEFT";
    private static final String CAR_UI_INSET_RIGHT = "CAR_UI_INSET_RIGHT";
    private static final String CAR_UI_INSET_TOP = "CAR_UI_INSET_TOP";
    private static final boolean IS_DEBUG_DEVICE;
    private static final String TAG = "CarUiInstaller";

    static {
        IS_DEBUG_DEVICE = Build.TYPE.toLowerCase(Locale.ROOT).contains("debug") || Build.TYPE.toLowerCase(Locale.ROOT).equals("eng");
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        Context context = getContext();
        if (context == null) {
            Log.e(TAG, "CarUiInstaller had a null context!");
            return false;
        }
        Log.i(TAG, "CarUiInstaller started for " + context.getPackageName());
        Application application = (Application) context.getApplicationContext();
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.android.car.ui.core.CarUiInstaller.1
            private Insets mInsets = null;
            private boolean mIsActivityStartedForFirstTime = false;

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                if (!activity.getClassLoader().equals(CarUiInstaller.class.getClassLoader())) {
                    CarUiInstaller.callBaseLayoutControllerMethod("build", activity);
                } else {
                    BaseLayoutController.build(activity);
                }
                if (savedInstanceState != null) {
                    int inset_left = savedInstanceState.getInt(CarUiInstaller.CAR_UI_INSET_LEFT);
                    int inset_top = savedInstanceState.getInt(CarUiInstaller.CAR_UI_INSET_TOP);
                    int inset_right = savedInstanceState.getInt(CarUiInstaller.CAR_UI_INSET_RIGHT);
                    int inset_bottom = savedInstanceState.getInt(CarUiInstaller.CAR_UI_INSET_BOTTOM);
                    this.mInsets = new Insets(inset_left, inset_top, inset_right, inset_bottom);
                }
                this.mIsActivityStartedForFirstTime = true;
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPostStarted(Activity activity) {
                BaseLayoutController controller = CarUi.getBaseLayoutController(activity);
                Insets insets = this.mInsets;
                if (insets != null && controller != null && this.mIsActivityStartedForFirstTime) {
                    controller.dispatchNewInsets(insets);
                    this.mIsActivityStartedForFirstTime = false;
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                BaseLayoutController controller = CarUi.getBaseLayoutController(activity);
                if (controller != null) {
                    Insets insets = controller.getInsets();
                    outState.putInt(CarUiInstaller.CAR_UI_INSET_LEFT, insets.getLeft());
                    outState.putInt(CarUiInstaller.CAR_UI_INSET_TOP, insets.getTop());
                    outState.putInt(CarUiInstaller.CAR_UI_INSET_RIGHT, insets.getRight());
                    outState.putInt(CarUiInstaller.CAR_UI_INSET_BOTTOM, insets.getBottom());
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
                if (!activity.getClassLoader().equals(CarUiInstaller.class.getClassLoader())) {
                    CarUiInstaller.callBaseLayoutControllerMethod("destroy", activity);
                } else {
                    BaseLayoutController.destroy(activity);
                }
            }
        });
        if (IS_DEBUG_DEVICE) {
            Log.i(TAG, "To resolve the memory leak, note out the following code.");
            return true;
        }
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void callBaseLayoutControllerMethod(String methodName, Activity activity) {
        try {
            Class<?> baseLayoutControllerClass = activity.getClassLoader().loadClass(BaseLayoutController.class.getName());
            Method method = baseLayoutControllerClass.getDeclaredMethod(methodName, Activity.class);
            method.invoke(null, activity);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}
