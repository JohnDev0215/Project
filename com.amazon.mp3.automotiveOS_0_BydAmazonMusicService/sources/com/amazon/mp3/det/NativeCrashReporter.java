package com.amazon.mp3.det;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.device.crashmanager.CrashDetailsCollectable;
import com.amazon.device.crashmanager.CrashDetectionHelper;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.config.ApplicationAttributes;
import com.amazon.mp3.det.BinaryFileDetUploader;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.config.PlaybackConfig;
import com.amazon.music.media.playback.player.Player;
import com.amazon.music.media.playback.player.impl.HarleyPlayer;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import okhttp3.Response;

/* JADX INFO: loaded from: classes3.dex */
public class NativeCrashReporter {
    private static final String TAG = "NativeCrashReporter";
    private static String mricToken = ChildUserUtil.INSTANCE.getMusicRequestIdentityContextToken(AmazonApplication.getApplication().getApplicationContext());

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Map lambda$uploadHarleyCrashCountException$0(Throwable th) {
        return null;
    }

    static class HarleyDumpFile {
        File file;
        boolean isInVersionSubdir;

        public HarleyDumpFile(File file, boolean isInVersionSubdir) {
            this.file = file;
            this.isInVersionSubdir = isInVersionSubdir;
        }
    }

    public static void uploadHarleyTombstones(@NonNull final Context context) {
        if (!ConnectivityUtil.isConnectionHighSpeed(context)) {
            Log.debug(TAG, "Skipping dmp uploading when a device is not connected to the net.");
            return;
        }
        AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(context);
        String deviceId = accountCredentialStorage.getDeviceId();
        String deviceType = accountCredentialStorage.getDeviceType();
        String version = ApplicationAttributes.getInstance(context).getVersion();
        BinaryFileDetUploader binaryFileDetUploader = new BinaryFileDetUploader(deviceId, deviceType, version, "CRASH", DetUploader.SERIAL_EXECUTOR, new BinaryFileDetUploader.DetUploadListener() { // from class: com.amazon.mp3.det.NativeCrashReporter.1
            @Override // com.amazon.mp3.det.BinaryFileDetUploader.DetUploadListener
            public void onSuccess(File file) {
                Log.debug(NativeCrashReporter.TAG, "DET report uploaded: " + file.getName());
                NativeCrashReporter.reportCrashUploaded(file.getName());
                deleteDmpFile(file);
                NativeCrashReporter.uploadLogs(context);
                NativeCrashReporter.uploadHarleyCrashCountException();
            }

            private void deleteDmpFile(File file) {
                Log.debug(NativeCrashReporter.TAG, "deleting the file: " + file.getAbsolutePath());
                try {
                    if (file.delete()) {
                        return;
                    }
                    Log.debug(NativeCrashReporter.TAG, "Failed deleting the file: " + file.getAbsolutePath());
                } catch (Exception e) {
                    Log.error(NativeCrashReporter.TAG, "Failed deleting the file: " + e.getMessage(), e);
                }
            }

            @Override // com.amazon.mp3.det.BinaryFileDetUploader.DetUploadListener
            public void onError(File file, Response response) {
                Log.error(NativeCrashReporter.TAG, "DET upload failed: %s, response: %s", file.getAbsolutePath(), response);
                NativeCrashReporter.reportCrashUploadFailed(file.getName(), response.message());
            }

            @Override // com.amazon.mp3.det.BinaryFileDetUploader.DetUploadListener
            public void onError(File file, Exception e) {
                Log.error(NativeCrashReporter.TAG, String.format("DET upload failed: %s, error: %s", file.getAbsolutePath(), e.getMessage()), e);
                NativeCrashReporter.reportCrashUploadFailed(file.getName(), e.getMessage());
            }
        });
        File dmpLocation = getDmpLocation();
        if (dmpLocation == null) {
            Log.warning(TAG, "DMMPlayer did not provide a crash folder. No dmp crashes will be reported.");
            return;
        }
        if (!dmpLocation.canRead()) {
            Log.error(TAG, "Unable to read: " + dmpLocation.getAbsolutePath());
            return;
        }
        ArrayList<HarleyDumpFile> arrayList = new ArrayList();
        findHarleyCoreDumps(arrayList, dmpLocation, 0);
        for (HarleyDumpFile harleyDumpFile : arrayList) {
            binaryFileDetUploader.submitReport(generateReportKey(version, harleyDumpFile), harleyDumpFile.file);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void uploadHarleyCrashCountException() {
        if (PlatformUtil.isFireOS()) {
            return;
        }
        String str = "HarleyVersion: ";
        PlaybackConfig playbackConfig = Playback.getInstance().getPlaybackConfig();
        if (playbackConfig != null) {
            Player localPlayer = playbackConfig.getLocalPlayer(mricToken);
            if (localPlayer instanceof HarleyPlayer) {
                str = "HarleyVersion: " + ((HarleyPlayer) localPlayer).getVersion();
            }
        }
        CrashDetectionHelper.getInstance().caughtException(new Exception(str), CrashDetectionHelper.CrashTypeCaughtException.CRASH_TYPE_NATIVE_EXCEPTION, new CrashDetailsCollectable() { // from class: com.amazon.mp3.det.NativeCrashReporter$$ExternalSyntheticLambda0
            @Override // com.amazon.device.crashmanager.CrashDetailsCollectable
            public final Map collect(Throwable th) {
                return NativeCrashReporter.lambda$uploadHarleyCrashCountException$0(th);
            }
        });
        Log.debug(TAG, "uploadHarleyCrashCountException submitted");
    }

    @NonNull
    private static String generateReportKey(@NonNull String appVersion, @NonNull HarleyDumpFile harleyDumpFile) {
        String name = harleyDumpFile.file.getName();
        try {
            String harleyVersion = DetermineHarleyVersion.getHarleyVersion(harleyDumpFile);
            if (!harleyVersion.equals("not harley crash")) {
                if (harleyVersion.equals("version not found")) {
                    reportHarleyVersion(1.0f, name, harleyVersion);
                } else {
                    reportHarleyVersion(0.0f, name, harleyVersion);
                    return name.replaceFirst(".dmp", "." + appVersion + "." + harleyVersion + ".dmp");
                }
            }
        } catch (Exception e) {
            Log.debug(TAG, "Error retrieving harley version from crash", e);
        }
        return name.replaceFirst(".dmp", "." + appVersion + ".dmp");
    }

    private static void findHarleyCoreDumps(ArrayList<HarleyDumpFile> files, File root, int level) {
        if (level > 1) {
            return;
        }
        for (File file : root.listFiles()) {
            if (file.getName().endsWith(".dmp")) {
                if (file.canRead()) {
                    files.add(new HarleyDumpFile(file, level > 0));
                } else {
                    Log.warning(TAG, "Unable to read: " + file.getAbsolutePath());
                }
            } else if (file.isDirectory()) {
                findHarleyCoreDumps(files, file, level + 1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void reportCrashUploaded(@NonNull final String fileName) {
        MetricsLogger.sendEvent(FlexEvent.builder("androidHarleyCrash").withFlexNum1(Float.valueOf(0.0f)).withFlexStr1(fileName).build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void reportCrashUploadFailed(@NonNull final String fileName, @NonNull final String message) {
        MetricsLogger.sendEvent(FlexEvent.builder("androidHarleyCrash").withFlexNum1(Float.valueOf(1.0f)).withFlexStr1(fileName).withFlexStr2(message).build());
    }

    private static void reportHarleyVersion(@NonNull final float success, @NonNull final String fileName, @NonNull final String harleyVersion) {
        MetricsLogger.sendEvent(FlexEvent.builder("androidHarleyCrashVersion").withFlexNum1(Float.valueOf(success)).withFlexStr1(fileName).withFlexStr2(harleyVersion).build());
    }

    @Nullable
    private static File getDmpLocation() {
        Playback playback = Playback.getInstance();
        if (playback == null || playback.getPlaybackConfig() == null) {
            return null;
        }
        Player localPlayer = playback.getPlaybackConfig().getLocalPlayer(mricToken);
        if (localPlayer instanceof HarleyPlayer) {
            return ((HarleyPlayer) localPlayer).getCrashDirectory();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void uploadLogs(@NonNull final Context context) {
        new PendingCrashLogs().uploadLogAfterNativeCrash(context);
    }
}
