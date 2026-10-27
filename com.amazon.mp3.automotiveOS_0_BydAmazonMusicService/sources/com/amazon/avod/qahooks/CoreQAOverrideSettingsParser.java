package com.amazon.avod.qahooks;

import android.content.Context;
import android.os.Build;
import com.amazon.avod.fileio.MediaStoreFileUtil;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
@SuppressFBWarnings(justification = "mSettings is used by child classes", value = {"URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD"})
public abstract class CoreQAOverrideSettingsParser {
    private final Context mContext;
    private final File mFile;
    protected ImmutableMap<String, String> mSettings;
    private final Properties mProperties = new Properties();
    private final MediaStoreFileUtil mMediaStoreFileUtil = new MediaStoreFileUtil();

    protected CoreQAOverrideSettingsParser(@Nonnull File file, @Nonnull Context context) {
        this.mFile = (File) Preconditions.checkNotNull(file, "file");
        this.mContext = context;
        parse();
    }

    private void parse() {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        try {
            InputStream inputStream = getInputStream(this.mFile, this.mContext);
            try {
                if (inputStream == null) {
                    this.mSettings = ImmutableMap.of();
                    if (inputStream != null) {
                        inputStream.close();
                        return;
                    }
                    return;
                }
                this.mProperties.load(inputStream);
                for (String str : this.mProperties.stringPropertyNames()) {
                    builder.put(str, this.mProperties.getProperty(str));
                }
                this.mSettings = builder.build();
                inputStream.close();
            } finally {
            }
        } catch (IOException e) {
            DLog.exceptionf(e, String.format("IOException CoreQAOverrideSettingsParser: %s", e.getMessage()), new Object[0]);
            this.mSettings = ImmutableMap.of();
        }
    }

    private InputStream getInputStream(@Nonnull File file, @Nonnull Context context) throws FileNotFoundException {
        if (Build.VERSION.SDK_INT < 29) {
            return new FileInputStream(file);
        }
        return this.mMediaStoreFileUtil.openInputStream(file.getName(), context);
    }
}
