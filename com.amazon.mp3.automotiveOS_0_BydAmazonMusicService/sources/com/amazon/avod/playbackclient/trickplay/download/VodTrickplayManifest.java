package com.amazon.avod.playbackclient.trickplay.download;

import android.graphics.BitmapFactory;
import com.amazon.avod.content.image.TrickplayManifest;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.io.Serializable;
import java.util.Map;
import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public final class VodTrickplayManifest implements Serializable, TrickplayManifest {
    public static final VodTrickplayManifest EMPTY_MANIFEST = new VodTrickplayManifest();
    private int mImageHeight;
    private int mImageWidth;
    private final NavigableMap<Long, String> mTimecodeToFilepathMap;

    @Override // com.amazon.avod.content.image.TrickplayManifest
    public void delete(long j) {
    }

    @Override // com.amazon.avod.content.image.TrickplayManifest
    public boolean isLive() {
        return false;
    }

    private VodTrickplayManifest() {
        this.mImageWidth = -1;
        this.mImageHeight = -1;
        this.mTimecodeToFilepathMap = Maps.newTreeMap();
    }

    @Override // com.amazon.avod.content.image.TrickplayManifest
    @Nonnull
    public NavigableMap<Long, String> getTimecodeToFilepathMap() {
        return new TreeMap((SortedMap) this.mTimecodeToFilepathMap);
    }

    @Override // com.amazon.avod.content.image.TrickplayManifest
    @Nullable
    public Map.Entry<Long, String> getClosest(long j) {
        return this.mTimecodeToFilepathMap.floorEntry(Long.valueOf(j));
    }

    @Override // com.amazon.avod.content.image.TrickplayManifest
    public void put(long j, @Nonnull String str) {
        this.mTimecodeToFilepathMap.put(Long.valueOf(j), str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void determineImageDimensions() {
        if (this.mTimecodeToFilepathMap.isEmpty()) {
            return;
        }
        String value = this.mTimecodeToFilepathMap.firstEntry().getValue();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(value, options);
        this.mImageWidth = options.outWidth;
        this.mImageHeight = options.outHeight;
    }

    @Override // com.amazon.avod.content.image.TrickplayManifest
    public int getImageWidth() {
        return this.mImageWidth;
    }

    @Override // com.amazon.avod.content.image.TrickplayManifest
    public int getImageHeight() {
        return this.mImageHeight;
    }

    @Override // com.amazon.avod.content.image.TrickplayManifest
    public boolean isValid() {
        return !this.mTimecodeToFilepathMap.isEmpty() && this.mImageWidth > 0 && this.mImageHeight > 0;
    }

    @NotThreadSafe
    public static class Builder {
        private VodTrickplayManifest mManifest = new VodTrickplayManifest();

        public Builder putFileAtTimecode(long j, @Nonnull String str) {
            Preconditions.checkNotNull(str);
            this.mManifest.put(j, str);
            return this;
        }

        public VodTrickplayManifest build() {
            this.mManifest.determineImageDimensions();
            VodTrickplayManifest vodTrickplayManifest = this.mManifest;
            this.mManifest = new VodTrickplayManifest();
            return vodTrickplayManifest;
        }
    }
}
