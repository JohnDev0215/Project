package com.amazon.avod.content.smoothstream.storage;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.PlayableContent;
import com.amazon.avod.fileio.DiskUtils;
import com.amazon.avod.util.Preconditions2;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class DynamicManifestContentStore implements ManifestContentStore {
    private final Map<String, ByteBuffer> mManifestIdToManifestByteBufferMap;
    private final Object mMutex;

    @Override // com.amazon.avod.content.smoothstream.storage.ManifestContentStore
    public boolean supportsCompressedManifests(@Nonnull ContentSessionType contentSessionType) {
        return false;
    }

    public DynamicManifestContentStore() {
        this(Maps.newHashMap());
    }

    DynamicManifestContentStore(@Nonnull Map<String, ByteBuffer> map) {
        this.mMutex = new Object();
        this.mManifestIdToManifestByteBufferMap = (Map) Preconditions.checkNotNull(map, "manifestIdToManifestByteBufferMap");
    }

    @Override // com.amazon.avod.content.smoothstream.storage.ManifestContentStore
    public boolean isManifestAvailable(@Nonnull ContentSessionType contentSessionType, @Nullable File file, @Nullable PlayableContent playableContent, @Nullable String str) {
        boolean zContainsKey;
        Preconditions.checkNotNull(contentSessionType, "sessionType");
        synchronized (this.mMutex) {
            zContainsKey = this.mManifestIdToManifestByteBufferMap.containsKey(buildManifestId(contentSessionType, file, playableContent, str));
        }
        return zContainsKey;
    }

    @Override // com.amazon.avod.content.smoothstream.storage.ManifestContentStore
    @Nonnull
    public ByteBuffer loadManifest(@Nonnull ContentSessionType contentSessionType, @Nullable File file, @Nullable PlayableContent playableContent, @Nullable String str) throws ContentException {
        ByteBuffer byteBuffer;
        Preconditions.checkNotNull(contentSessionType, "sessionType");
        String strBuildManifestId = buildManifestId(contentSessionType, file, playableContent, str);
        synchronized (this.mMutex) {
            byteBuffer = this.mManifestIdToManifestByteBufferMap.get(strBuildManifestId);
            if (byteBuffer == null) {
                throw new ContentException(String.format(Locale.US, "No corresponding manifest cached, manifest id: %s", strBuildManifestId));
            }
            byteBuffer.rewind();
        }
        return byteBuffer;
    }

    @Override // com.amazon.avod.content.smoothstream.storage.ManifestContentStore
    public void storeManifest(@Nonnull ContentSessionType contentSessionType, @Nullable File file, @Nullable PlayableContent playableContent, @Nullable String str, @Nonnull InputStream inputStream, @Nonnegative int i, boolean z) throws ContentException {
        Preconditions.checkNotNull(contentSessionType, "sessionType");
        Preconditions.checkNotNull(inputStream, "data");
        Preconditions2.checkArgumentWeakly(!z, "compression not currently supported in memory content store");
        byte[] bArr = new byte[i];
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(i);
        byteBufferAllocateDirect.rewind();
        String strBuildManifestId = buildManifestId(contentSessionType, file, playableContent, str);
        int i2 = 0;
        while (i2 < i) {
            try {
                int i3 = inputStream.read(bArr, 0, Math.min(131072, i - i2));
                if (i3 == -1) {
                    break;
                }
                byteBufferAllocateDirect.put(bArr, 0, i3);
                i2 += i3;
            } catch (IOException e) {
                throw new ContentException(ContentException.ContentError.NETWORK_ERROR, String.format(Locale.US, "Cannot store manifest stream with id %s in %s due to %s", strBuildManifestId, getClass().getSimpleName(), e.getMessage()));
            } catch (BufferOverflowException e2) {
                throw new ContentException(ContentException.ContentError.DISK_ERROR, String.format(Locale.US, "Cannot store manifest stream with id %s in %s due to %s", strBuildManifestId, getClass().getSimpleName(), e2.getMessage()));
            }
        }
        if (i2 != i) {
            throw new ContentException(String.format(Locale.US, "Could not copy entire stream to buffer, input length: %d bytes copied: %d", Integer.valueOf(i), Integer.valueOf(i2)));
        }
        synchronized (this.mMutex) {
            this.mManifestIdToManifestByteBufferMap.put(strBuildManifestId, byteBufferAllocateDirect);
        }
    }

    @Override // com.amazon.avod.content.ContentStore
    public void releaseContent(@Nonnull ContentSessionType contentSessionType, @Nullable File file, @Nullable PlayableContent playableContent, @Nullable String str) {
        Preconditions.checkNotNull(contentSessionType, "sessionType");
        String strBuildManifestId = buildManifestId(contentSessionType, file, playableContent, null);
        synchronized (this.mMutex) {
            Iterator<String> it = this.mManifestIdToManifestByteBufferMap.keySet().iterator();
            while (it.hasNext()) {
                if (it.next().startsWith(strBuildManifestId)) {
                    it.remove();
                }
            }
        }
    }

    @Override // com.amazon.avod.content.ContentStore
    public void releaseContent(@Nonnull ContentSessionType contentSessionType, @Nullable File file, @Nullable PlayableContent playableContent, @Nullable String str, Optional<DiskUtils.DeletionProgressListener> optional) {
        releaseContent(contentSessionType, file, playableContent, str);
    }

    @Override // com.amazon.avod.content.ContentStore
    public void deleteAllContent(@Nonnull ContentSessionType contentSessionType, @Nullable File file) {
        Preconditions.checkNotNull(contentSessionType, "sessionType");
        synchronized (this.mMutex) {
            this.mManifestIdToManifestByteBufferMap.clear();
        }
    }

    @Nonnull
    private String buildManifestId(@Nonnull ContentSessionType contentSessionType, @Nullable File file, @Nullable PlayableContent playableContent, @Nullable String str) {
        Preconditions.checkNotNull(contentSessionType, "sessionType");
        StringBuilder sb = new StringBuilder();
        sb.append(contentSessionType.toString());
        if (file != null) {
            sb.append("#");
            sb.append(file.toString());
        }
        if (playableContent != null) {
            sb.append("#");
            sb.append(playableContent.toString());
        }
        if (str != null) {
            sb.append("#");
            sb.append(str);
        }
        return sb.toString();
    }
}
