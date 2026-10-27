package com.amazon.device.crashmanager;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class Artifact implements Closeable {
    private String mCrashDescriptor;
    private final long mCreationTimeUTCMillis;
    private final InputStream mInputStream;
    private Map<String, String> mMetadataMap = new HashMap();
    private final String mTag;

    public boolean shouldUploadArtifact() {
        return true;
    }

    public Artifact(String str, InputStream inputStream, long j) {
        if (str == null) {
            throw new IllegalArgumentException("Artifact tag cannot be null");
        }
        if (inputStream == null) {
            throw new IllegalArgumentException("Artifact InputSteam cannot be null");
        }
        if (j < 0) {
            throw new IllegalArgumentException("Creation Time cannot be negative");
        }
        this.mTag = str;
        this.mInputStream = inputStream;
        this.mCreationTimeUTCMillis = j;
    }

    public String getTag() {
        return this.mTag;
    }

    public long getCreationTimeUTCMillis() {
        return this.mCreationTimeUTCMillis;
    }

    public InputStream getInputStream() {
        return this.mInputStream;
    }

    public String getCrashDescriptor() {
        return this.mCrashDescriptor;
    }

    public void setCrashDescriptor(String str) {
        this.mCrashDescriptor = str;
    }

    public Map<String, String> getMetadataMap() {
        return this.mMetadataMap;
    }

    public void addMetadata(String str, String str2) {
        this.mMetadataMap.put(str, str2);
    }

    public void removeMetadata(String str) {
        this.mMetadataMap.remove(str);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        InputStream inputStream = this.mInputStream;
        if (inputStream != null) {
            inputStream.close();
        }
    }
}
