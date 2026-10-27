package com.android.providers.downloads;

import android.os.Build;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public class Constants {
    public static final String DEFAULT_USER_AGENT;
    public static final boolean LOGV;
    public static final boolean LOGVV;

    static {
        StringBuilder sb = new StringBuilder();
        boolean z = !TextUtils.isEmpty(Build.VERSION.RELEASE_OR_CODENAME);
        boolean z2 = !TextUtils.isEmpty(Build.ID);
        boolean z3 = "REL".equals(Build.VERSION.CODENAME) && !TextUtils.isEmpty(Build.MODEL);
        sb.append("AndroidDownloadManager");
        if (z) {
            sb.append("/");
            sb.append(Build.VERSION.RELEASE_OR_CODENAME);
        }
        sb.append(" (Linux; U; Android");
        if (z) {
            sb.append(" ");
            sb.append(Build.VERSION.RELEASE_OR_CODENAME);
        }
        if (z3 || z2) {
            sb.append(";");
            if (z3) {
                sb.append(" ");
                sb.append(Build.MODEL);
            }
            if (z2) {
                sb.append(" Build/");
                sb.append(Build.ID);
            }
        }
        sb.append(")");
        DEFAULT_USER_AGENT = sb.toString();
        LOGV = false;
        LOGVV = false;
    }
}
