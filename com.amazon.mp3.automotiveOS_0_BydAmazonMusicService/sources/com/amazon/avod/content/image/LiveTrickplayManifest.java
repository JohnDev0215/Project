package com.amazon.avod.content.image;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.NavigableMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public final class LiveTrickplayManifest implements TrickplayManifest {
    private final int mImageHeight;
    private final int mImageWidth;
    private final Object mMutex = new Object();
    private final NavigableMap<Long, String> mTimecodeToFilepathMap = Maps.newTreeMap();

    @Override // com.amazon.avod.content.image.TrickplayManifest
    public boolean isLive() {
        return true;
    }

    @Override // com.amazon.avod.content.image.TrickplayManifest
    public boolean isValid() {
        return true;
    }

    public LiveTrickplayManifest(int i, int i2) {
        this.mImageWidth = i;
        this.mImageHeight = i2;
    }

    @Override // com.amazon.avod.content.image.TrickplayManifest
    public void put(long j, @Nonnull String str) {
        synchronized (this.mMutex) {
            this.mTimecodeToFilepathMap.put(Long.valueOf(j), str);
        }
    }

    @Override // com.amazon.avod.content.image.TrickplayManifest
    public void delete(long j) {
        synchronized (this.mMutex) {
            this.mTimecodeToFilepathMap.remove(Long.valueOf(j));
        }
    }

    @Override // com.amazon.avod.content.image.TrickplayManifest
    @Nonnull
    public NavigableMap<Long, String> getTimecodeToFilepathMap() {
        NavigableMap<Long, String> navigableMap;
        synchronized (this.mMutex) {
            navigableMap = this.mTimecodeToFilepathMap;
        }
        return navigableMap;
    }

    @Override // com.amazon.avod.content.image.TrickplayManifest
    @Nullable
    public Map.Entry<Long, String> getClosest(long j) {
        Map.Entry<Long, String> entryCeilingEntry;
        synchronized (this.mMutex) {
            entryCeilingEntry = this.mTimecodeToFilepathMap.ceilingEntry(Long.valueOf(j));
        }
        return entryCeilingEntry;
    }

    @Override // com.amazon.avod.content.image.TrickplayManifest
    public int getImageWidth() {
        return this.mImageWidth;
    }

    @Override // com.amazon.avod.content.image.TrickplayManifest
    public int getImageHeight() {
        return this.mImageHeight;
    }
}
