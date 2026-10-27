package com.amazon.avod.content.smoothstream.manifest.acquisition;

import com.amazon.avod.util.DLog;
import com.amazon.avod.util.Preconditions2;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ExternalStorageManifestWriter implements ManifestUploader {
    private static AtomicBoolean hasClearedData = new AtomicBoolean(false);

    public static void clearData() {
        ManifestCapturerConfig manifestCapturerConfig = ManifestCapturerConfig.INSTANCE;
        if (manifestCapturerConfig.getShouldCleanupExternalStorageDownloadPath() && !hasClearedData.getAndSet(true)) {
            Preconditions2.checkNotMainThreadWeakly();
            File file = new File(manifestCapturerConfig.getExternalStorageDownloadPath());
            if (file.exists() && file.isDirectory() && file.canWrite()) {
                DLog.warnf("Deleting PostManifest files at %s", file.getAbsolutePath());
                try {
                    Runtime.getRuntime().exec("rm -rf " + file.getAbsolutePath());
                } catch (IOException | SecurityException e) {
                    DLog.exceptionf(e, "IOException attempting to flush ExternalStorageManifestWriter", new Object[0]);
                }
            }
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestUploader
    public void upload(@Nonnull List<PostManifestPayload> list) {
        Preconditions.checkNotNull(list, "payloads");
        Iterator<PostManifestPayload> it = list.iterator();
        while (it.hasNext()) {
            saveToDisk(it.next());
        }
    }

    private void saveToDisk(PostManifestPayload postManifestPayload) {
        try {
            File file = new File(String.format(Locale.US, "%s/%s/%s", ManifestCapturerConfig.INSTANCE.getExternalStorageDownloadPath(), postManifestPayload.getDeviceId(), postManifestPayload.getConsumptionId()));
            if (!file.exists() && !file.mkdirs()) {
                DLog.errorf("Failed to create directory at: %s", file.getAbsolutePath());
                return;
            }
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(new File(file.getAbsolutePath(), String.valueOf(postManifestPayload.getMsSinceEpoch())).getAbsoluteFile()), Charsets.UTF_8);
            outputStreamWriter.write(postManifestPayload.toJSON());
            outputStreamWriter.close();
        } catch (FileNotFoundException e) {
            e = e;
            DLog.errorf("Cannot save manifest: %s", e.getMessage());
        } catch (UnsupportedEncodingException e2) {
            e = e2;
            DLog.errorf("Cannot save manifest: %s", e.getMessage());
        } catch (IOException e3) {
            DLog.errorf("Cannot save file: %s", e3.getMessage());
        }
    }
}
