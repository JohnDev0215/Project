package com.android.settingslib;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import com.android.internal.annotations.VisibleForTesting;
import com.android.launcher3.icons.f;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class c {

    @VisibleForTesting
    static final String STORAGE_MANAGER_ENABLED_PROPERTY = "ro.storage_manager.enabled";

    public static Drawable a(Context context, ApplicationInfo applicationInfo) {
        return b(context, applicationInfo.loadUnbadgedIcon(context.getPackageManager()), UserHandle.getUserHandleForUid(applicationInfo.uid));
    }

    public static Drawable b(Context context, Drawable drawable, UserHandle userHandle) {
        f fVarM = f.m(context);
        try {
            BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(), fVarM.d(drawable, userHandle, true).f1301a);
            if (fVarM != null) {
                fVarM.close();
            }
            return bitmapDrawable;
        } catch (Throwable th) {
            if (fVarM != null) {
                try {
                    fVarM.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static ColorStateList c(Context context, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(new int[]{i});
        try {
            return typedArrayObtainStyledAttributes.getColorStateList(0);
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }
}
