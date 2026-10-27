package com.amazon.avod.content.event;

import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.PlayableContent;
import com.amazon.avod.pmet.ContentStoreType;
import com.amazon.avod.util.DataUnit;
import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ContentStoreEvent extends ContentEventBase {
    private final long mAllocatedHeapInBytes;
    private final ContentStoreType mContentStoreType;
    private final boolean mIsMemoryBackedContentStoreEnabled;
    private final long mNativeMemoryAllocationMaxInBytes;
    private final long mNativeMemoryAllocationMinInBytes;

    public ContentStoreEvent(@Nonnull PlayableContent playableContent, @Nonnull ContentSessionType contentSessionType, @Nonnull ContentStoreType contentStoreType, long j, long j2, long j3, boolean z) {
        super((PlayableContent) Preconditions.checkNotNull(playableContent), (ContentSessionType) Preconditions.checkNotNull(contentSessionType));
        this.mContentStoreType = (ContentStoreType) Preconditions.checkNotNull(contentStoreType);
        this.mAllocatedHeapInBytes = j;
        this.mNativeMemoryAllocationMaxInBytes = j2;
        this.mNativeMemoryAllocationMinInBytes = j3;
        this.mIsMemoryBackedContentStoreEnabled = z;
    }

    @Nonnull
    public String getContentStoreInfo() {
        Locale locale = Locale.US;
        DataUnit dataUnit = DataUnit.BYTES;
        return String.format(locale, "%s,MaxMB:%.1f,MinMB:%.1f,AllocMB:%.1f,memStoreEnabled:%s", this.mContentStoreType.name(), Float.valueOf(dataUnit.toMegaBytes(this.mNativeMemoryAllocationMaxInBytes)), Float.valueOf(dataUnit.toMegaBytes(this.mNativeMemoryAllocationMinInBytes)), Float.valueOf(dataUnit.toMegaBytes(this.mAllocatedHeapInBytes)), Boolean.valueOf(this.mIsMemoryBackedContentStoreEnabled));
    }
}
