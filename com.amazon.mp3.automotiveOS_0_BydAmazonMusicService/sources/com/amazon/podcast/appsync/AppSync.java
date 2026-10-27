package com.amazon.podcast.appsync;

import Podcast.BiteBookmarkInterface.v1_0.BiteBookmarkWriteElement;
import Podcast.BookmarkInterface.v1_0.BookmarkMetadataWriteElement;
import Podcast.BookmarkInterface.v1_0.BookmarkWriteElement;
import Podcast.CategoryFollowInterface.v1_0.CategoryFollowWriteElement;
import Podcast.CompletedInterface.v1_0.CompletedMetadataWriteElement;
import Podcast.CompletedInterface.v1_0.CompletedWriteElement;
import Podcast.DownloadInterface.v1_0.DownloadWriteElement;
import Podcast.FollowInterface.v1_0.FollowMetadataWriteElement;
import Podcast.FollowInterface.v1_0.FollowWriteElement;
import Podcast.PlaybackMetricsInterface.v1_0.PlaybackMetricWriteElement;
import Podcast.SaveInterface.v1_0.SaveMetadataWriteElement;
import Podcast.SaveInterface.v1_0.SaveWriteElement;
import Podcast.UIMetricsInterface.v1_0.UIMetricWriteElement;
import SOAAppSyncInterface.v1_0.ResolveOperationMethod;
import SOAAppSyncInterface.v1_0.RetryOperationMethod;
import SOAAppSyncInterface.v1_0.StartMethod;
import SOAAppSyncInterface.v1_0.WriteElement;
import SOAAppSyncInterface.v1_0.WriteLocalMethod;
import SOAAppSyncInterface.v1_0.WriteMethod;
import SOACoreInterface.v1_0.Method;
import android.content.Context;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.biteBookmark.BiteBookmark;
import com.amazon.podcast.biteBookmark.BiteBookmarkQueryParameters;
import com.amazon.podcast.biteBookmark.BiteBookmarkSyncData;
import com.amazon.podcast.bookmark.Bookmark;
import com.amazon.podcast.bookmark.BookmarkQueryParameters;
import com.amazon.podcast.bookmark.BookmarkSyncData;
import com.amazon.podcast.categoryFollow.CategoryFollow;
import com.amazon.podcast.categoryFollow.CategoryFollowSyncData;
import com.amazon.podcast.completed.Completed;
import com.amazon.podcast.completed.CompletedQueryParameters;
import com.amazon.podcast.completed.CompletedSyncData;
import com.amazon.podcast.downloads.Download;
import com.amazon.podcast.downloads.DownloadQueryParameters;
import com.amazon.podcast.downloads.DownloadSyncData;
import com.amazon.podcast.follow.Follow;
import com.amazon.podcast.follow.FollowSyncData;
import com.amazon.podcast.metrics.playback.PlaybackMetricSyncData;
import com.amazon.podcast.metrics.ui.UiMetricSyncData;
import com.amazon.podcast.save.Save;
import com.amazon.podcast.save.SaveQueryParameters;
import com.amazon.podcast.save.SaveSyncData;
import com.amazon.podcast.storage.PodcastRoomDatabase;
import com.amazon.soa.core.Engine;
import com.amazon.soa.core.MethodCallback;
import com.amazon.soa.core.OnMethodExecutedListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class AppSync implements MethodCallback, MethodsDispatcher {
    private final Sync<BiteBookmarkWriteElement, WriteElement, BiteBookmark, BiteBookmarkQueryParameters> biteBookmarkSync;
    private final BiteBookmarkSyncData biteBookmarkSyncData;
    private final Sync<BookmarkWriteElement, BookmarkMetadataWriteElement, Bookmark, BookmarkQueryParameters> bookmarkSync;
    private final BookmarkSyncData bookmarkSyncData;
    private final Sync<CategoryFollowWriteElement, WriteElement, CategoryFollow, QueryParameters> categoryFollowSync;
    private final CategoryFollowSyncData categoryFollowSyncData;
    private final Sync<CompletedWriteElement, CompletedMetadataWriteElement, Completed, CompletedQueryParameters> completedSync;
    private final CompletedSyncData completedSyncData;
    private final Context context;
    private final PodcastRoomDatabase database;
    private final Sync<DownloadWriteElement, WriteElement, Download, DownloadQueryParameters> downloadSync;
    private final DownloadSyncData downloadSyncData;
    private final Engine engine;
    private final Sync<FollowWriteElement, FollowMetadataWriteElement, Follow, QueryParameters> followSync;
    private final FollowSyncData followSyncData;
    private final PlaybackMetricSyncData playbackMetricSyncData;
    private final Sync<PlaybackMetricWriteElement, WriteElement, Void, QueryParameters> playbackMetricsSync;
    private final Sync<SaveWriteElement, SaveMetadataWriteElement, Save, SaveQueryParameters> saveSync;
    private final SaveSyncData saveSyncData;
    private final Map<String, Sync> syncs;
    private final UiMetricSyncData uiMetricSyncData;
    private final Sync<UIMetricWriteElement, WriteElement, Void, QueryParameters> uiMetricsSync;

    public AppSync(final Engine engine, final Context context, final PodcastRoomDatabase database) {
        HashMap map = new HashMap();
        this.syncs = map;
        this.engine = engine;
        this.context = context;
        this.database = database;
        engine.registerOwner("follow");
        engine.registerOwner("bookmark");
        engine.registerOwner("biteBookmark");
        engine.registerOwner("save");
        engine.registerOwner("download");
        engine.registerOwner("playbackMetrics");
        engine.registerOwner("uiMetrics");
        engine.registerOwner("completed");
        engine.registerOwner("categoryFollow");
        HashSet hashSet = new HashSet();
        hashSet.add(StartMethod.class.getCanonicalName());
        hashSet.add(WriteMethod.class.getCanonicalName());
        hashSet.add(WriteLocalMethod.class.getCanonicalName());
        hashSet.add(ResolveOperationMethod.class.getCanonicalName());
        hashSet.add(RetryOperationMethod.class.getCanonicalName());
        engine.registerMethods(Collections.emptySet(), hashSet, this);
        FollowSyncData followSyncData = new FollowSyncData(engine, "follow", context, database);
        this.followSyncData = followSyncData;
        BookmarkSyncData bookmarkSyncData = new BookmarkSyncData(engine, "bookmark", context, database);
        this.bookmarkSyncData = bookmarkSyncData;
        BiteBookmarkSyncData biteBookmarkSyncData = new BiteBookmarkSyncData(engine, "biteBookmark", context, database);
        this.biteBookmarkSyncData = biteBookmarkSyncData;
        SaveSyncData saveSyncData = new SaveSyncData(engine, "save", context, database);
        this.saveSyncData = saveSyncData;
        DownloadSyncData downloadSyncData = new DownloadSyncData(engine, "download", context, database);
        this.downloadSyncData = downloadSyncData;
        PlaybackMetricSyncData playbackMetricSyncData = new PlaybackMetricSyncData(engine, "playbackMetrics", context, database);
        this.playbackMetricSyncData = playbackMetricSyncData;
        UiMetricSyncData uiMetricSyncData = new UiMetricSyncData(engine, "uiMetrics", context, database);
        this.uiMetricSyncData = uiMetricSyncData;
        CompletedSyncData completedSyncData = new CompletedSyncData(engine, "completed", context, database);
        this.completedSyncData = completedSyncData;
        CategoryFollowSyncData categoryFollowSyncData = new CategoryFollowSyncData(engine, "categoryFollow", context, database);
        this.categoryFollowSyncData = categoryFollowSyncData;
        Sync<FollowWriteElement, FollowMetadataWriteElement, Follow, QueryParameters> sync = new Sync<>("follow", followSyncData, this);
        this.followSync = sync;
        Sync<BookmarkWriteElement, BookmarkMetadataWriteElement, Bookmark, BookmarkQueryParameters> sync2 = new Sync<>("bookmark", bookmarkSyncData, this);
        this.bookmarkSync = sync2;
        Sync<BiteBookmarkWriteElement, WriteElement, BiteBookmark, BiteBookmarkQueryParameters> sync3 = new Sync<>("biteBookmark", biteBookmarkSyncData, this);
        this.biteBookmarkSync = sync3;
        Sync<SaveWriteElement, SaveMetadataWriteElement, Save, SaveQueryParameters> sync4 = new Sync<>("save", saveSyncData, this);
        this.saveSync = sync4;
        this.downloadSync = new Sync<>("download", downloadSyncData, this);
        Sync<PlaybackMetricWriteElement, WriteElement, Void, QueryParameters> sync5 = new Sync<>("playbackMetrics", playbackMetricSyncData, this);
        this.playbackMetricsSync = sync5;
        Sync<UIMetricWriteElement, WriteElement, Void, QueryParameters> sync6 = new Sync<>("uiMetrics", uiMetricSyncData, this);
        this.uiMetricsSync = sync6;
        Sync<CompletedWriteElement, CompletedMetadataWriteElement, Completed, CompletedQueryParameters> sync7 = new Sync<>("completed", completedSyncData, this);
        this.completedSync = sync7;
        Sync<CategoryFollowWriteElement, WriteElement, CategoryFollow, QueryParameters> sync8 = new Sync<>("categoryFollow", categoryFollowSyncData, this);
        this.categoryFollowSync = sync8;
        map.put("follow", sync);
        map.put("bookmark", sync2);
        map.put("biteBookmark", sync3);
        map.put("save", sync4);
        map.put("playbackMetrics", sync5);
        map.put("uiMetrics", sync6);
        map.put("completed", sync7);
        map.put("categoryFollow", sync8);
    }

    private void startMethod(final Method method, final OnMethodExecutedListener listener) {
        this.followSync.submitForReporting(this.context);
        this.bookmarkSync.submitForReporting(this.context);
        this.biteBookmarkSync.submitForReporting(this.context);
        this.saveSync.submitForReporting(this.context);
        this.playbackMetricsSync.submitForReporting(this.context);
        this.uiMetricsSync.submitForReporting(this.context);
        this.completedSync.submitForReporting(this.context);
        this.categoryFollowSync.submitForReporting(this.context);
        listener.onMethodExecuted(method);
    }

    public Sync<FollowWriteElement, FollowMetadataWriteElement, Follow, QueryParameters> follow() {
        return this.followSync;
    }

    public Sync<BookmarkWriteElement, BookmarkMetadataWriteElement, Bookmark, BookmarkQueryParameters> bookmark() {
        return this.bookmarkSync;
    }

    public Sync<BiteBookmarkWriteElement, WriteElement, BiteBookmark, BiteBookmarkQueryParameters> biteBookmark() {
        return this.biteBookmarkSync;
    }

    public Sync<SaveWriteElement, SaveMetadataWriteElement, Save, SaveQueryParameters> save() {
        return this.saveSync;
    }

    public Sync<DownloadWriteElement, WriteElement, Download, DownloadQueryParameters> download() {
        return this.downloadSync;
    }

    public Sync<PlaybackMetricWriteElement, WriteElement, Void, QueryParameters> playbackMetrics() {
        return this.playbackMetricsSync;
    }

    public Sync<UIMetricWriteElement, WriteElement, Void, QueryParameters> uiMetrics() {
        return this.uiMetricsSync;
    }

    public Sync<CompletedWriteElement, CompletedMetadataWriteElement, Completed, CompletedQueryParameters> completed() {
        return this.completedSync;
    }

    public Sync<CategoryFollowWriteElement, WriteElement, CategoryFollow, QueryParameters> categoryFollow() {
        return this.categoryFollowSync;
    }

    public static void clearData(final Context context) {
        FollowSyncData.clearData(context);
        BookmarkSyncData.clearData(context);
        BiteBookmarkSyncData.clearData(context);
        SaveSyncData.clearData(context);
        DownloadSyncData.clearData(context);
        PlaybackMetricSyncData.clearData(context);
        UiMetricSyncData.clearData(context);
        CompletedSyncData.clearData(context);
        CategoryFollowSyncData.clearData(context);
    }

    @Override // com.amazon.soa.core.MethodCallback
    public void executeMethod(final Method method, final String ownerId, final OnMethodExecutedListener listener) {
        if (method instanceof StartMethod) {
            startMethod(method, listener);
            return;
        }
        if (method instanceof WriteMethod) {
            writeMethod((WriteMethod) method, listener);
            return;
        }
        if (method instanceof WriteLocalMethod) {
            writeLocalMethod((WriteLocalMethod) method, listener);
        } else if (method instanceof ResolveOperationMethod) {
            resolveOperation(ownerId, (ResolveOperationMethod) method, listener);
        } else if (method instanceof RetryOperationMethod) {
            retryOperation(ownerId, (RetryOperationMethod) method, listener);
        }
    }

    @Override // com.amazon.podcast.MethodsDispatcher
    public void dispatch(final String ownerId, final List<Method> methods) {
        this.engine.handleMethods(ownerId, methods);
    }

    private void writeMethod(final WriteMethod method, final OnMethodExecutedListener listener) {
        for (WriteElement writeElement : method.getElement()) {
            if (writeElement instanceof FollowWriteElement) {
                this.followSync.write(this.context, (FollowWriteElement) writeElement, method.getCondition());
            } else if (writeElement instanceof BookmarkWriteElement) {
                this.bookmarkSync.write(this.context, (BookmarkWriteElement) writeElement, method.getCondition());
            } else if (writeElement instanceof BiteBookmarkWriteElement) {
                this.biteBookmarkSync.write(this.context, (BiteBookmarkWriteElement) writeElement, method.getCondition());
            } else if (writeElement instanceof SaveWriteElement) {
                this.saveSync.write(this.context, (SaveWriteElement) writeElement, method.getCondition());
            } else if (writeElement instanceof DownloadWriteElement) {
                this.downloadSync.write(this.context, (DownloadWriteElement) writeElement, method.getCondition());
            } else if (writeElement instanceof PlaybackMetricWriteElement) {
                this.playbackMetricsSync.write(this.context, (PlaybackMetricWriteElement) writeElement, method.getCondition());
            } else if (writeElement instanceof UIMetricWriteElement) {
                this.uiMetricsSync.write(this.context, (UIMetricWriteElement) writeElement, method.getCondition());
            } else if (writeElement instanceof CompletedWriteElement) {
                this.completedSync.write(this.context, (CompletedWriteElement) writeElement, method.getCondition());
            } else if (writeElement instanceof CategoryFollowWriteElement) {
                this.categoryFollowSync.write(this.context, (CategoryFollowWriteElement) writeElement, method.getCondition());
            } else if (writeElement instanceof BookmarkMetadataWriteElement) {
                this.bookmarkSync.writeMetadata((BookmarkMetadataWriteElement) writeElement);
            } else if (writeElement instanceof CompletedMetadataWriteElement) {
                this.completedSync.writeMetadata((CompletedMetadataWriteElement) writeElement);
            } else if (writeElement instanceof SaveMetadataWriteElement) {
                this.saveSync.writeMetadata((SaveMetadataWriteElement) writeElement);
            } else if (writeElement instanceof FollowMetadataWriteElement) {
                this.followSync.writeMetadata((FollowMetadataWriteElement) writeElement);
            }
        }
        listener.onMethodExecuted(method);
    }

    private void writeLocalMethod(final WriteLocalMethod method, final OnMethodExecutedListener listener) {
        for (WriteElement writeElement : method.getElement()) {
            if (writeElement instanceof BookmarkWriteElement) {
                this.bookmarkSync.writeLocal(this.context, (BookmarkWriteElement) writeElement, method.getCondition());
            }
        }
        listener.onMethodExecuted(method);
    }

    private void resolveOperation(final String ownerId, final ResolveOperationMethod method, final OnMethodExecutedListener listener) {
        Sync sync = this.syncs.get(ownerId);
        if (sync == null) {
            throw new IllegalArgumentException(String.format("OwnerId %s does not exist", ownerId));
        }
        sync.resolveOperations(method.getOperations());
        listener.onMethodExecuted(method);
    }

    private void retryOperation(final String ownerId, final RetryOperationMethod method, final OnMethodExecutedListener listener) {
        Sync sync = this.syncs.get(ownerId);
        if (sync == null) {
            throw new IllegalArgumentException(String.format("OwnerId %s does not exist", ownerId));
        }
        List<String> operations = method.getOperations();
        if (operations == null || operations.isEmpty()) {
            listener.onMethodExecuted(method);
        } else {
            sync.retryOperations(method.getOperations());
            listener.onMethodExecuted(method);
        }
    }
}
