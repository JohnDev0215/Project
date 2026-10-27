package com.amazon.avod.content.smoothstream.storage;

import com.amazon.avod.media.framework.error.MediaException;
import java.nio.ByteBuffer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface MemoryAllocator {
    long allocate(int i) throws MediaException;

    void begin(int i, float f, int i2, int i3, boolean z) throws MediaException, OutOfMemoryError;

    void end() throws MediaException;

    void free(long j) throws MediaException;

    int getAvailableSizeInBytes() throws MediaException;

    int getHeapAllocatedSizeInBytes() throws MediaException;

    int getHeapUsedSizeInBytes() throws MediaException;

    int getHeapVersion();

    @Nullable
    ByteBuffer map(long j) throws MediaException;

    void unmap(@Nonnull ByteBuffer byteBuffer) throws MediaException;
}
