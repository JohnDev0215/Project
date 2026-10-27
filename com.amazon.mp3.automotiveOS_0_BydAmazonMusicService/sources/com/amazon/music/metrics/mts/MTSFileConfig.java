package com.amazon.music.metrics.mts;

import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.file.FileWriterConfig;

/* JADX INFO: loaded from: classes4.dex */
public class MTSFileConfig implements FileWriterConfig {
    private String mCharsetName;
    private String mFilePath;
    private long mMaxFileSize;

    @Override // com.amazon.music.file.FileWriterConfig
    public final String getDelimiter() {
        return ",\n";
    }

    public MTSFileConfig(String str, long j) {
        this(str, j, ParserUtil.UTF_8);
    }

    public MTSFileConfig(String str, long j, String str2) {
        this.mFilePath = str;
        this.mMaxFileSize = j;
        this.mCharsetName = str2;
    }

    @Override // com.amazon.music.file.FileWriterConfig
    public long getMaxFileSizeBytes() {
        return this.mMaxFileSize;
    }

    @Override // com.amazon.music.file.FileAccessorConfig
    public String getFilePath() {
        return this.mFilePath;
    }

    @Override // com.amazon.music.file.FileReaderConfig
    public String getCharsetName() {
        return this.mCharsetName;
    }
}
