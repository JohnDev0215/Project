package com.amazon.avod.content.smoothstream.storage.singlefile;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public class JsonDownloadContentStatus implements DownloadContentStatus {

    @JsonProperty("path")
    private String mFilePath;

    @JsonProperty("files")
    private Map<String, MediaFileStatus> mFileStatusMap;

    @JsonIgnore
    private final ObjectMapper mObjectMapper;

    JsonDownloadContentStatus(String str, ObjectMapper objectMapper, Map<String, MediaFileStatus> map) {
        this.mFilePath = str;
        this.mObjectMapper = objectMapper;
        this.mFileStatusMap = map;
    }

    public static JsonDownloadContentStatus fromFileOrNew(@Nonnull String str) throws IOException {
        Preconditions.checkNotNull(str, "filePath");
        ObjectMapper visibility = new ObjectMapper().setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE).setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        File file = new File(str);
        if (file.exists() && file.length() != 0) {
            return (JsonDownloadContentStatus) visibility.readValue(file, JsonDownloadContentStatus.class);
        }
        return new JsonDownloadContentStatus(str, visibility, Maps.newConcurrentMap());
    }

    @JsonCreator
    static JsonDownloadContentStatus newInstance(@JsonProperty("path") @Nonnull String str, @JsonProperty("files") @Nonnull Map<String, MediaFileStatus> map) {
        Preconditions.checkNotNull(str, "filePath");
        Preconditions.checkNotNull(map, "fileStatusMap");
        return new JsonDownloadContentStatus(str, new ObjectMapper().setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE).setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY), map);
    }

    private JsonDownloadContentStatus() {
        this.mObjectMapper = new ObjectMapper().setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE).setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.singlefile.DownloadContentStatus
    public MediaFileStatus getMediaFileStatus(@Nonnull String str) {
        Preconditions.checkNotNull(str, "fileName");
        return this.mFileStatusMap.get(str);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.singlefile.DownloadContentStatus
    public MediaFileStatus addMediaFileStatus(@Nonnull String str, int i, long j) {
        Preconditions.checkNotNull(str, "fileName");
        return this.mFileStatusMap.put(str, new MediaFileStatus(i, j));
    }

    @Override // com.amazon.avod.content.smoothstream.storage.singlefile.DownloadContentStatus
    public Set<String> getFileNames() {
        return this.mFileStatusMap.keySet();
    }

    @Override // com.amazon.avod.content.smoothstream.storage.singlefile.DownloadContentStatus
    public void save() throws IOException {
        File file = new File(this.mFilePath);
        File parentFile = file.getParentFile();
        if (!parentFile.exists() && !parentFile.mkdirs()) {
            throw new IOException("Cannot create parent folder(s)");
        }
        this.mObjectMapper.writeValue(file, this);
    }
}
