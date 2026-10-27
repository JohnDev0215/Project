package com.android.internal.content.om;

import android.content.pm.PackageParser;
import android.util.ArrayMap;
import android.util.Log;
import java.io.File;
import java.util.Collection;

/* JADX INFO: loaded from: classes4.dex */
public class OverlayScanner {
    private final ArrayMap<String, ParsedOverlayInfo> mParsedOverlayInfos = new ArrayMap<>();

    public static class ParsedOverlayInfo {
        public final boolean isStatic;
        public final String packageName;
        public final File path;
        public final int priority;
        public final String targetPackageName;
        public final int targetSdkVersion;

        public ParsedOverlayInfo(String str, String str2, int i, boolean z, int i2, File file) {
            this.packageName = str;
            this.targetPackageName = str2;
            this.targetSdkVersion = i;
            this.isStatic = z;
            this.priority = i2;
            this.path = file;
        }

        public String toString() {
            return getClass().getSimpleName() + String.format("{packageName=%s, targetPackageName=%s, targetSdkVersion=%s, isStatic=%s, priority=%s, path=%s}", this.packageName, this.targetPackageName, Integer.valueOf(this.targetSdkVersion), Boolean.valueOf(this.isStatic), Integer.valueOf(this.priority), this.path);
        }
    }

    public final ParsedOverlayInfo getParsedInfo(String str) {
        return this.mParsedOverlayInfos.get(str);
    }

    final Collection<ParsedOverlayInfo> getAllParsedInfos() {
        return this.mParsedOverlayInfos.values();
    }

    public void scanDir(File file) {
        ParsedOverlayInfo overlayManifest;
        if (file.exists() && file.isDirectory()) {
            if (!file.canRead()) {
                Log.w("OverlayConfig", "Directory " + file + " cannot be read");
                return;
            }
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null) {
                return;
            }
            for (File file2 : fileArrListFiles) {
                if (file2.isDirectory()) {
                    scanDir(file2);
                }
                if (file2.isFile() && file2.getPath().endsWith(PackageParser.APK_FILE_EXTENSION) && (overlayManifest = parseOverlayManifest(file2)) != null) {
                    this.mParsedOverlayInfos.put(overlayManifest.packageName, overlayManifest);
                }
            }
        }
    }

    public ParsedOverlayInfo parseOverlayManifest(File file) {
        try {
            PackageParser.ApkLite apkLite = PackageParser.parseApkLite(file, 0);
            if (apkLite.targetPackageName == null) {
                return null;
            }
            return new ParsedOverlayInfo(apkLite.packageName, apkLite.targetPackageName, apkLite.targetSdkVersion, apkLite.overlayIsStatic, apkLite.overlayPriority, new File(apkLite.codePath));
        } catch (PackageParser.PackageParserException e) {
            Log.w("OverlayConfig", "Got exception loading overlay.", e);
            return null;
        }
    }
}
