package com.amazon.device.crashmanager;

import android.content.Context;
import android.util.Log;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.device.crashmanager.source.ArtifactSource;
import com.amazon.device.crashmanager.utils.CrashDescriptorUtil;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.amazon.music.destination.parser.ParserUtil;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
class AppFileArtifactSource implements ArtifactSource {
    private static final char CRASH_FILE_NAME_DELIMITER = '#';
    private static final String CRASH_LOG_FILENAME_PREFIX = "crashlog.v2.amzcl";
    private static final int CRASH_TAG_GROUP = 1;
    private static final boolean LOG_CONTENTS = false;
    private static final int MAX_KEY_LENGTH = 50;
    private static final int MAX_VALUE_LENGTH = 100;
    private static final String TAG = "com.amazon.device.crashmanager.AppFileArtifactSource";
    private final Context mContext;
    private final CrashDetailsCollectable mCrashDetailsCollectable;
    private final Pattern pattern;

    public AppFileArtifactSource(Context context, CrashDetailsCollectable crashDetailsCollectable) {
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null.");
        }
        if (crashDetailsCollectable == null) {
            throw new IllegalArgumentException("Crash details collector must not be null.");
        }
        this.mContext = context;
        this.mCrashDetailsCollectable = crashDetailsCollectable;
        this.pattern = Pattern.compile("#(.*?)#");
    }

    public Artifact getNextArtifact(MetricEvent metricEvent) {
        return getNextArtifact(metricEvent, CrashManagerActions.ARTIFACT_UPLOAD);
    }

    @Override // com.amazon.device.crashmanager.source.ArtifactSource
    public Artifact getNextArtifact(MetricEvent metricEvent, String str) {
        File crashReportFile = getCrashReportFile();
        if (crashReportFile != null && crashReportFile.exists()) {
            try {
                return new Artifact(getCrashTagFromFileName(crashReportFile.getName()), new FileInputStream(crashReportFile), crashReportFile.lastModified());
            } catch (Exception e) {
                Log.w(TAG, "Could not find crash file.", e);
            }
        }
        return null;
    }

    @Override // com.amazon.device.crashmanager.source.ArtifactSource
    public void saveCurrentIndex() {
        File crashReportFile = getCrashReportFile();
        if (crashReportFile == null || !crashReportFile.exists() || deleteCrashReportFile(crashReportFile)) {
            return;
        }
        Log.e(TAG, "Failed to delete crash report file " + crashReportFile.getName());
    }

    public void saveCrash(Throwable th) {
        saveCrash(th, "data_app_crash", null);
    }

    void saveCrash(Throwable th, String str, CrashDetailsCollectable crashDetailsCollectable) {
        Map<String, String> mapCollect;
        String str2 = TAG;
        Log.e(str2, "=========================");
        Log.e(str2, "CRASH HAS OCCURRED", th);
        Log.e(str2, "Collecting details and saving to disk");
        Log.e(str2, "=========================");
        try {
            Map<String, String> mapCollect2 = this.mCrashDetailsCollectable.collect(th);
            if (crashDetailsCollectable != null && (mapCollect = crashDetailsCollectable.collect(th)) != null) {
                mapCollect2.putAll(mapCollect);
            }
            mapCollect2.put("CrashType", str);
            mapCollect2.put("ContentType", "ThirdPartyJavaCrash");
            mapCollect2.put("process", mapCollect2.get(CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME));
            mapCollect2.put("version", mapCollect2.get("packageVersionName"));
            mapCollect2.put("osBuildNumber", mapCollect2.get("androidBuildVersion"));
            saveCrashReportToFile(generateCrashReport(mapCollect2), CrashDescriptorUtil.calculateCrashDescriptor(th, mapCollect2.get(CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME)), str);
        } catch (Exception e) {
            Log.e(TAG, "Could not handle uncaught exception", e);
        }
    }

    private File getCrashReportFile() {
        File[] fileArrListFiles = this.mContext.getFileStreamPath("").listFiles(new FileFilter() { // from class: com.amazon.device.crashmanager.AppFileArtifactSource.1
            @Override // java.io.FileFilter
            public boolean accept(File file) {
                return file.getName().startsWith(AppFileArtifactSource.CRASH_LOG_FILENAME_PREFIX);
            }
        });
        if (fileArrListFiles == null || fileArrListFiles.length == 0) {
            return null;
        }
        return fileArrListFiles[0];
    }

    private boolean deleteCrashReportFile(File file) {
        return this.mContext.deleteFile(file.getName());
    }

    private String generateCrashReport(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        Map<String, String> metadataAndDebugDetails = getMetadataAndDebugDetails(map);
        sb.append(metadataAndDebugDetails.get("metadataDetail"));
        sb.append("\n");
        sb.append(map.get("stackTrace"));
        sb.append("\n");
        sb.append("=== Complete details ===\n\n");
        sb.append(metadataAndDebugDetails.get("debugDetail"));
        return sb.toString();
    }

    private Map<String, String> getMetadataAndDebugDetails(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        HashMap map2 = new HashMap();
        ArrayList<String> arrayList = new ArrayList(map.keySet());
        Collections.sort(arrayList);
        for (String str : arrayList) {
            String str2 = map.get(str);
            if (isHeaderMetadata(str, str2)) {
                addDetailToCrashReport(sb, str, str2);
            } else {
                addDetailToCrashReport(sb2, str, str2);
            }
        }
        map2.put("metadataDetail", sb.toString());
        map2.put("debugDetail", sb2.toString());
        return map2;
    }

    private boolean isHeaderMetadata(String str, String str2) {
        return str != null && str2 != null && str.length() < 50 && str2.length() < 100 && str.indexOf(10) < 0 && str2.indexOf(10) < 0;
    }

    private void saveCrashReportToFile(String str, String str2, String str3) {
        if (str == null || str.length() == 0) {
            return;
        }
        try {
            String fileName = getFileName(str2, str3);
            Log.i(TAG, String.format("Saving crash report to file: %s", this.mContext.getFileStreamPath(fileName).getAbsolutePath()));
            synchronized (AppFileArtifactSource.class) {
                FileOutputStream fileOutputStreamOpenFileOutput = this.mContext.openFileOutput(fileName, 0);
                try {
                    fileOutputStreamOpenFileOutput.write(str.getBytes(Charset.forName(ParserUtil.UTF_8)));
                    try {
                        fileOutputStreamOpenFileOutput.close();
                    } catch (IOException unused) {
                    }
                } catch (Throwable th) {
                    if (fileOutputStreamOpenFileOutput != null) {
                        try {
                            fileOutputStreamOpenFileOutput.close();
                        } catch (IOException unused2) {
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "Could not save crash report to file", e);
        }
    }

    private void addDetailToCrashReport(StringBuilder sb, String str, String str2) {
        if (isNullOrEmpty(str)) {
            return;
        }
        if (isNullOrEmpty(str2)) {
            str2 = "null";
        }
        sb.append(str);
        sb.append(": ");
        sb.append(str2);
        sb.append("\n");
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private String getFileName(String str, String str2) {
        return "crashlog.v2.amzcl#" + str2 + CRASH_FILE_NAME_DELIMITER + str;
    }

    private String getCrashTagFromFileName(String str) {
        Matcher matcher = this.pattern.matcher(str);
        return matcher.find() ? matcher.group(1) : "data_app_crash";
    }
}
