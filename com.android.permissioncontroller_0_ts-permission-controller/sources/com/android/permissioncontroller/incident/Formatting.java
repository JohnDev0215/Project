package com.android.permissioncontroller.incident;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.android.permissioncontroller.permission.utils.Utils;
import java.text.DateFormat;
import java.util.Date;

/* JADX INFO: loaded from: classes8.dex */
public class Formatting {
    private final Context mContext;
    private final DateFormat mDateFormat;
    private final PackageManager mPm;
    private final DateFormat mTimeFormat;

    Formatting(Context context) {
        this.mContext = context;
        this.mPm = context.getPackageManager();
        this.mDateFormat = android.text.format.DateFormat.getDateFormat(context);
        this.mTimeFormat = android.text.format.DateFormat.getTimeFormat(context);
    }

    String getAppLabel(String pkg) {
        try {
            ApplicationInfo app = this.mPm.getApplicationInfo(pkg, 0);
            return Utils.getAppLabel(app, this.mContext);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    String getDate(long wallTimeMs) {
        return this.mDateFormat.format(new Date(wallTimeMs));
    }

    String getTime(long wallTimeMs) {
        return this.mTimeFormat.format(new Date(wallTimeMs));
    }
}
