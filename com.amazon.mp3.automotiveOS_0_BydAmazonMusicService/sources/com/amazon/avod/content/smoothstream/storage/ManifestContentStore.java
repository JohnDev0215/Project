package com.amazon.avod.content.smoothstream.storage;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.ContentStore;
import com.amazon.avod.content.PlayableContent;
import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface ManifestContentStore extends ContentStore {
    boolean isManifestAvailable(@Nonnull ContentSessionType contentSessionType, @Nullable File file, @Nullable PlayableContent playableContent, @Nullable String str);

    @Nonnull
    ByteBuffer loadManifest(@Nonnull ContentSessionType contentSessionType, @Nullable File file, @Nullable PlayableContent playableContent, @Nullable String str) throws ContentException;

    void storeManifest(@Nonnull ContentSessionType contentSessionType, @Nullable File file, @Nullable PlayableContent playableContent, @Nullable String str, @Nonnull InputStream inputStream, @Nonnegative int i, boolean z) throws ContentException;

    boolean supportsCompressedManifests(@Nonnull ContentSessionType contentSessionType);
}
