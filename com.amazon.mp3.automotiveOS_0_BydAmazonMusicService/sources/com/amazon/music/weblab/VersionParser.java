package com.amazon.music.weblab;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes4.dex */
class VersionParser {
    private final String version;

    public VersionParser(Context context) {
        String versionName = getVersionName(context);
        String versionFromVersionName = !TextUtils.isEmpty(versionName) ? parseVersionFromVersionName(versionName) : null;
        this.version = TextUtils.isEmpty(versionFromVersionName) ? "0.0.0" : versionFromVersionName;
    }

    public String getVersion() {
        return this.version;
    }

    private String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    private String parseVersionFromVersionName(String str) {
        Matcher matcher = Pattern.compile("^(\\d+\\.)*\\d+").matcher(str);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }
}
