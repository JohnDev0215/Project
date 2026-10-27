package com.amazon.avod.content.smoothstream.manifest.acquisition;

import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationCompat;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.PlayableContent;
import com.amazon.avod.content.smoothstream.manifest.Manifest;
import com.amazon.avod.content.smoothstream.manifest.ManifestParser;
import com.amazon.avod.content.urlvending.ContentUrlSelector;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.playback.LifecycleProfiler;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PostManifestAcquirer implements ManifestAcquirerInterface {

    @Nonnull
    private final ManifestParser mParser;

    @Nonnull
    private final ArrayList<Date> mSortedKeys;

    @Nonnull
    private final Date mStartTime;

    @Nonnull
    private final Stopwatch mStopwatch;

    @Nonnull
    private final NavigableMap<Date, PostManifestFile> mStoredManifests;

    @Override // com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestAcquirerInterface
    public void shutdown() {
    }

    @VisibleForTesting
    static class PostManifestFile {

        @Nonnull
        private final String mMPD;

        @Nonnull
        private final Date mTimeOfDownload;

        PostManifestFile(@Nonnull String str) {
            Preconditions.checkNotNull(str, "filename");
            File file = new File(str);
            this.mTimeOfDownload = new Date(Long.parseLong(file.getName()));
            try {
                this.mMPD = Files.toString(file, Charsets.UTF_8);
            } catch (IOException unused) {
                throw new RuntimeException("unable to parse " + str);
            }
        }

        Date getDate() {
            return this.mTimeOfDownload;
        }

        ByteBuffer getByteBuffer() {
            return ByteBuffer.wrap(this.mMPD.getBytes(Charsets.UTF_8));
        }
    }

    public PostManifestAcquirer(@Nonnull ManifestParser manifestParser) {
        this(manifestParser, Stopwatch.createUnstarted(Tickers.androidTicker()), loadManifestsFromDisk());
    }

    @VisibleForTesting
    PostManifestAcquirer(@Nonnull ManifestParser manifestParser, @Nonnull Stopwatch stopwatch, @Nonnull TreeMap<Date, PostManifestFile> treeMap) {
        this.mParser = (ManifestParser) Preconditions.checkNotNull(manifestParser, "parser");
        this.mStopwatch = (Stopwatch) Preconditions.checkNotNull(stopwatch, NotificationCompat.CATEGORY_STOPWATCH);
        NavigableMap<Date, PostManifestFile> navigableMap = (NavigableMap) Preconditions.checkNotNull(treeMap, "storedManifests");
        this.mStoredManifests = navigableMap;
        this.mStartTime = navigableMap.firstKey();
        this.mSortedKeys = new ArrayList<>(navigableMap.keySet());
    }

    private static TreeMap<Date, PostManifestFile> loadManifestsFromDisk() {
        DLog.logf("PostManifest: PostManifest enabled. We are replaying this title.");
        TreeMap<Date, PostManifestFile> treeMap = new TreeMap<>();
        File[] fileArrListFiles = new File(ManifestCapturerConfig.INSTANCE.getPostManifestInputFolder()).listFiles();
        if (fileArrListFiles == null) {
            return treeMap;
        }
        for (File file : fileArrListFiles) {
            PostManifestFile postManifestFile = new PostManifestFile(file.getAbsolutePath());
            treeMap.put(postManifestFile.getDate(), postManifestFile);
        }
        DLog.logf("PostManifest: Loaded %s Manifests from Disk.", Integer.valueOf(treeMap.size()));
        return treeMap;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestAcquirerInterface
    public Manifest getManifestFromUrl(@Nonnull ContentUrlSelector contentUrlSelector, @Nonnull String str, @Nonnull File file, @Nonnull PlayableContent playableContent, @Nonnull ContentSessionType contentSessionType, boolean z, @Nonnull DrmScheme drmScheme, @Nonnull LifecycleProfiler lifecycleProfiler, @Nullable String str2, @Nonnull ContentType contentType) throws ContentException {
        if (!this.mStopwatch.isRunning()) {
            this.mStopwatch.start();
        }
        Date recordedManifestForElapsedTime = getRecordedManifestForElapsedTime(this.mStopwatch.elapsed(TimeUnit.MILLISECONDS));
        PostManifestFile postManifestFile = this.mStoredManifests.get(recordedManifestForElapsedTime);
        DLog.logf("PostManifest: Loading manifest %s of %s \n", Integer.valueOf(Arrays.binarySearch(this.mSortedKeys.toArray(), recordedManifestForElapsedTime) + 1), Integer.valueOf(this.mSortedKeys.size()));
        return this.mParser.parse(postManifestFile.getByteBuffer(), drmScheme);
    }

    protected Date getRecordedManifestForElapsedTime(long j) {
        Date date = new Date(this.mStartTime.getTime() + j);
        DLog.logf("PostManifest: Loading manifest at target date %s\n", date.toString());
        return this.mStoredManifests.floorEntry(date).getKey();
    }
}
