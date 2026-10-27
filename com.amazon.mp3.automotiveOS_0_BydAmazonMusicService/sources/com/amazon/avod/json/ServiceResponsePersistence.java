package com.amazon.avod.json;

import android.os.Environment;
import com.amazon.avod.util.DLog;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.google.common.io.Files;
import java.io.File;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ServiceResponsePersistence {
    private static final Pattern INVALID_FILENAME_CHARACTERS_PATTERN = Pattern.compile("[^a-zA-Z0-9\\.\\-\\_]+");
    private static final String AVOD_SERVER_RESPONSES_PATH = Environment.getExternalStorageDirectory().getPath() + File.separator + "avodServerResponses";

    public static void persistToDisk(@Nonnull byte[] bArr, @Nonnull String str) throws Throwable {
        File file = new File(AVOD_SERVER_RESPONSES_PATH, sanitizeFilename(str));
        DLog.devf("Saving new service response to %s", file.getAbsolutePath());
        try {
            Files.createParentDirs(file);
            Files.write(bArr, file);
        } catch (Exception e) {
            DLog.exceptionf(e, "Json response persist failed! File: %s", str);
        }
    }

    @Nonnull
    private static String sanitizeFilename(@Nonnull String str) {
        return INVALID_FILENAME_CHARACTERS_PATTERN.matcher(str).replaceAll(Attributes.PREDEFINED_ATTRIBUTE_PREFIX);
    }
}
