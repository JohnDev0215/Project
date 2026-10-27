package com.amazon.mls.config.internal.sushi.writer;

import com.amazon.mls.config.internal.core.configuration.Region;
import com.amazon.mls.config.internal.core.logcat.LogLevel;
import com.amazon.mls.config.internal.core.logcat.LogcatProxy;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class FileWriterNameFormatter {
    private long fileId;
    private Region region;
    private String version;

    private FileWriterNameFormatter() {
    }

    public static FileWriterNameFormatter fromFileName(String str) {
        FileWriterNameFormatter fileWriterNameFormatter = new FileWriterNameFormatter();
        try {
            String[] strArrSplit = str.split(Attributes.PREDEFINED_ATTRIBUTE_PREFIX, 3);
            if (strArrSplit.length != 3) {
                return null;
            }
            fileWriterNameFormatter.version = strArrSplit[0];
            fileWriterNameFormatter.fileId = Long.parseLong(strArrSplit[1]);
            fileWriterNameFormatter.region = Region.getRegionFromString(strArrSplit[2]);
            return fileWriterNameFormatter;
        } catch (IllegalArgumentException e) {
            LogcatProxy.log(LogLevel.ERROR, String.format(Locale.US, "Failed to parse the file name for sushi file writer with fileName=%s", str), e);
            return null;
        }
    }

    public long getFileId() {
        return this.fileId;
    }

    public Region getRegion() {
        return this.region;
    }

    public String getFileVersion() {
        return this.version;
    }
}
