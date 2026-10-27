package com.amazon.mp3.det;

import com.amazon.mp3.det.NativeCrashReporter;
import com.amazon.mp3.det.minidump.LocationDescription;
import com.amazon.mp3.det.minidump.MiniDumpFromFile;
import com.amazon.mp3.det.minidump.ModuleStream;
import com.amazon.mp3.util.Log;
import java.io.File;

/* JADX INFO: loaded from: classes3.dex */
public class DetermineHarleyVersion {
    private static final String TAG = "DetermineHarleyVersion";

    private static String getVersionFromPath(File dumpFile) {
        String str = dumpFile.getPath().split("/")[r0.length - 2];
        if (!str.matches("\\d+\\.\\d+\\.\\d+\\.\\d+")) {
            Log.debug(TAG, "Could not determine Harley version from path: " + dumpFile.getPath());
            return "version not found";
        }
        Log.debug(TAG, "Determined Harley version is: " + str + ".");
        return str;
    }

    public static String getHarleyVersion(NativeCrashReporter.HarleyDumpFile harleyDumpFile) throws Exception {
        String versionFromPath;
        File file = harleyDumpFile.file;
        String str = TAG;
        Log.debug(str, "Checking crash for Harley version.");
        MiniDumpFromFile miniDumpFromFile = new MiniDumpFromFile(file);
        miniDumpFromFile.open();
        LocationDescription codeviewRecordLocationDescription = getCodeviewRecordLocationDescription(miniDumpFromFile);
        miniDumpFromFile.close();
        if (codeviewRecordLocationDescription == null) {
            versionFromPath = "not harley crash";
        } else {
            versionFromPath = harleyDumpFile.isInVersionSubdir ? getVersionFromPath(file) : "version not found";
        }
        Log.debug(str, "Determined Harley version is" + versionFromPath + ".");
        return versionFromPath;
    }

    public static LocationDescription getCodeviewRecordLocationDescription(MiniDumpFromFile miniDump) {
        for (ModuleStream moduleStream : miniDump.getModuleStreams()) {
            if (moduleStream.toString().contains("libdmengine.so")) {
                return moduleStream.getCvRecordLd();
            }
        }
        return null;
    }
}
