package org.chromium.base;

import android.content.Context;
import android.provider.Settings;
import java.io.File;

/* JADX INFO: loaded from: classes3.dex */
public final class CommandLineInitUtil {
    private static final String COMMAND_LINE_FILE_PATH = "/data/local";
    private static final String COMMAND_LINE_FILE_PATH_DEBUG_APP = "/data/local/tmp";
    private static final String TAG = "CommandLineInitUtil";

    private CommandLineInitUtil() {
    }

    public static void initCommandLine(Context context, String str) throws Throwable {
        if (CommandLine.isInitialized()) {
            return;
        }
        File alternativeCommandLinePath = getAlternativeCommandLinePath(context, str);
        if (alternativeCommandLinePath == null) {
            alternativeCommandLinePath = new File(COMMAND_LINE_FILE_PATH, str);
        }
        CommandLine.initFromFile(alternativeCommandLinePath.getPath());
    }

    private static File getAlternativeCommandLinePath(Context context, String str) {
        File file = new File(COMMAND_LINE_FILE_PATH_DEBUG_APP, str);
        if (!file.exists()) {
            return null;
        }
        try {
            String debugAppJBMR1 = getDebugAppJBMR1(context);
            if (debugAppJBMR1 != null && debugAppJBMR1.equals(context.getApplicationContext().getPackageName())) {
                Log.i(TAG, "Using alternative command line file in " + file.getPath(), new Object[0]);
                return file;
            }
        } catch (RuntimeException unused) {
            Log.e(TAG, "Unable to detect alternative command line file", new Object[0]);
        }
        return null;
    }

    private static String getDebugAppJBMR1(Context context) {
        if (Settings.Global.getInt(context.getContentResolver(), "adb_enabled", 0) == 1) {
            return Settings.Global.getString(context.getContentResolver(), "debug_app");
        }
        return null;
    }

    private static String getDebugAppPreJBMR1(Context context) {
        if (Settings.System.getInt(context.getContentResolver(), "adb_enabled", 0) == 1) {
            return Settings.System.getString(context.getContentResolver(), "debug_app");
        }
        return null;
    }
}
