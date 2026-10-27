package com.amazon.music.curate.data;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.JobIntentService;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.music.curate.data.database.PlaylistDB;
import com.amazon.music.curate.model.Playlist;
import com.amazon.music.curate.model.Track;
import com.amazon.music.curate.provider.FeatureFlagProvider;
import com.amazon.music.curate.provider.InternalProvider;
import com.amazon.music.curate.provider.PlaylistIntents;
import com.amazon.music.curate.skyfire.local.SyncScheduler;
import com.amazon.musicplaylist.model.PlaylistVersionException;
import dagger.android.AndroidInjection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.functions.Action1;

/* JADX INFO: compiled from: PlaylistIntentReceiverService.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 !2\u00020\u0001:\u0001!B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0018\u001a\u00020\u0014H\u0002J\u0010\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u001b\u001a\u00020\u0014H\u0016J\u0010\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0014J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/amazon/music/curate/data/PlaylistIntentReceiverService;", "Landroidx/core/app/JobIntentService;", "()V", "cache", "Lcom/amazon/music/curate/data/database/PlaylistDB;", "featureFlagProvider", "Lcom/amazon/music/curate/provider/FeatureFlagProvider;", "getFeatureFlagProvider", "()Lcom/amazon/music/curate/provider/FeatureFlagProvider;", "setFeatureFlagProvider", "(Lcom/amazon/music/curate/provider/FeatureFlagProvider;)V", "requestInterceptor", "Lcom/amazon/hermes/RequestInterceptor;", "getRequestInterceptor", "()Lcom/amazon/hermes/RequestInterceptor;", "setRequestInterceptor", "(Lcom/amazon/hermes/RequestInterceptor;)V", NotificationCompat.CATEGORY_SERVICE, "Lcom/amazon/music/curate/data/PlaylistService;", "handleRemoveCatalogPlaylist", "", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "handleRemoveTrackIntent", "handleResetLibraryLastSync", "handleUpdateCatalogPlaylist", "handleUpdatePlaylist", "onCreate", "onHandleWork", "updateCache", "Lcom/amazon/music/curate/data/GetUserPlaylistResponse;", "playlistId", "", "Companion", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistIntentReceiverService extends JobIntentService {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int JOB_ID;
    private static final Logger LOG;
    private static final String TAG;
    private PlaylistDB cache;
    public FeatureFlagProvider featureFlagProvider;
    public RequestInterceptor requestInterceptor;
    private PlaylistService service;

    @JvmStatic
    public static final void processRemoveCatalogPlaylist(Context context, String str) {
        INSTANCE.processRemoveCatalogPlaylist(context, str);
    }

    @JvmStatic
    public static final void processRequestLibrarySync(Context context) {
        INSTANCE.processRequestLibrarySync(context);
    }

    @JvmStatic
    public static final void processTrackRemoval(Context context, String str, String str2, int i) {
        INSTANCE.processTrackRemoval(context, str, str2, i);
    }

    @JvmStatic
    public static final void processUpdateCatalogPlaylist(Context context, String str) {
        INSTANCE.processUpdateCatalogPlaylist(context, str);
    }

    @JvmStatic
    public static final void processUpdatePlaylist(Context context, String str) {
        INSTANCE.processUpdatePlaylist(context, str);
    }

    public final RequestInterceptor getRequestInterceptor() {
        RequestInterceptor requestInterceptor = this.requestInterceptor;
        if (requestInterceptor != null) {
            return requestInterceptor;
        }
        Intrinsics.throwUninitializedPropertyAccessException("requestInterceptor");
        return null;
    }

    public final void setRequestInterceptor(RequestInterceptor requestInterceptor) {
        Intrinsics.checkNotNullParameter(requestInterceptor, "<set-?>");
        this.requestInterceptor = requestInterceptor;
    }

    public final FeatureFlagProvider getFeatureFlagProvider() {
        FeatureFlagProvider featureFlagProvider = this.featureFlagProvider;
        if (featureFlagProvider != null) {
            return featureFlagProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("featureFlagProvider");
        return null;
    }

    public final void setFeatureFlagProvider(FeatureFlagProvider featureFlagProvider) {
        Intrinsics.checkNotNullParameter(featureFlagProvider, "<set-?>");
        this.featureFlagProvider = featureFlagProvider;
    }

    /* JADX INFO: compiled from: PlaylistIntentReceiverService.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tH\u0007J\u0010\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007J(\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0004H\u0007J\u0018\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tH\u0007J\u0018\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \u0007*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/curate/data/PlaylistIntentReceiverService$Companion;", "", "()V", "JOB_ID", "", "LOG", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "TAG", "", "processRemoveCatalogPlaylist", "", "context", "Landroid/content/Context;", "playlistId", "processRequestLibrarySync", "processTrackRemoval", "trackId", "index", "processUpdateCatalogPlaylist", "processUpdatePlaylist", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final void processTrackRemoval(Context context, String playlistId, String trackId, int index) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(playlistId, "playlistId");
            Intrinsics.checkNotNullParameter(trackId, "trackId");
            PlaylistIntents.Companion companion = PlaylistIntents.INSTANCE;
            Intent intent = new Intent(companion.getACTION_REMOVE_TRACK_FROM_PLAYLIST());
            intent.putExtra(companion.getEXTRA_PLAYLIST_ID(), playlistId);
            intent.putExtra(companion.getEXTRA_TRACK_ID(), trackId);
            intent.putExtra(companion.getEXTRA_INDEX(), index);
            JobIntentService.enqueueWork(context, (Class<?>) PlaylistIntentReceiverService.class, PlaylistIntentReceiverService.JOB_ID, intent);
        }

        @JvmStatic
        public final void processUpdatePlaylist(Context context, String playlistId) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(playlistId, "playlistId");
            PlaylistIntents.Companion companion = PlaylistIntents.INSTANCE;
            Intent intent = new Intent(companion.getACTION_REFRESH_PLAYLIST());
            intent.putExtra(companion.getEXTRA_PLAYLIST_ID(), playlistId);
            JobIntentService.enqueueWork(context, (Class<?>) PlaylistIntentReceiverService.class, PlaylistIntentReceiverService.JOB_ID, intent);
        }

        @JvmStatic
        public final void processRemoveCatalogPlaylist(Context context, String playlistId) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(playlistId, "playlistId");
            PlaylistIntents.Companion companion = PlaylistIntents.INSTANCE;
            Intent intent = new Intent(companion.getACTION_REMOVE_CATALOG_PLAYLIST());
            intent.putExtra(companion.getEXTRA_PLAYLIST_ID(), playlistId);
            JobIntentService.enqueueWork(context, (Class<?>) PlaylistIntentReceiverService.class, PlaylistIntentReceiverService.JOB_ID, intent);
        }

        @JvmStatic
        public final void processUpdateCatalogPlaylist(Context context, String playlistId) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(playlistId, "playlistId");
            PlaylistIntents.Companion companion = PlaylistIntents.INSTANCE;
            Intent intent = new Intent(companion.getACTION_UPDATE_CATALOG_PLAYLIST());
            intent.putExtra(companion.getEXTRA_PLAYLIST_ID(), playlistId);
            JobIntentService.enqueueWork(context, (Class<?>) PlaylistIntentReceiverService.class, PlaylistIntentReceiverService.JOB_ID, intent);
        }

        @JvmStatic
        public final void processRequestLibrarySync(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            JobIntentService.enqueueWork(context, (Class<?>) PlaylistIntentReceiverService.class, PlaylistIntentReceiverService.JOB_ID, new Intent(PlaylistIntents.INSTANCE.getACTION_REQUEST_LIBRARY_SYNC()));
        }
    }

    static {
        String simpleName = PlaylistIntentReceiverService.class.getSimpleName();
        TAG = simpleName;
        LOG = LoggerFactory.getLogger(simpleName);
        JOB_ID = simpleName.hashCode();
    }

    @Override // androidx.core.app.JobIntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        AndroidInjection.inject(this);
        InternalProvider internalProvider = InternalProvider.Singleton.INSTANCE.get();
        PlaylistService playlistServiceProvideService = internalProvider.provideService(getRequestInterceptor(), this, getFeatureFlagProvider());
        this.service = playlistServiceProvideService;
        if (playlistServiceProvideService == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_SERVICE);
            playlistServiceProvideService = null;
        }
        this.cache = internalProvider.provideCache(this, playlistServiceProvideService);
    }

    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        String action = intent.getAction();
        PlaylistIntents.Companion companion = PlaylistIntents.INSTANCE;
        if (Intrinsics.areEqual(action, companion.getACTION_REMOVE_TRACK_FROM_PLAYLIST())) {
            handleRemoveTrackIntent(intent);
            return;
        }
        if (Intrinsics.areEqual(action, companion.getACTION_REFRESH_PLAYLIST())) {
            handleUpdatePlaylist(intent);
            return;
        }
        if (Intrinsics.areEqual(action, companion.getACTION_REMOVE_CATALOG_PLAYLIST())) {
            handleRemoveCatalogPlaylist(intent);
            return;
        }
        if (Intrinsics.areEqual(action, companion.getACTION_UPDATE_CATALOG_PLAYLIST())) {
            handleUpdateCatalogPlaylist(intent);
        } else if (Intrinsics.areEqual(action, companion.getACTION_REQUEST_LIBRARY_SYNC())) {
            handleResetLibraryLastSync();
        } else {
            LOG.warn(Intrinsics.stringPlus("Unknown intent action received: ", intent.getAction()));
        }
    }

    private final void handleRemoveTrackIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return;
        }
        PlaylistIntents.Companion companion = PlaylistIntents.INSTANCE;
        String string = extras.getString(companion.getEXTRA_PLAYLIST_ID(), null);
        String string2 = extras.getString(companion.getEXTRA_TRACK_ID(), null);
        int i = extras.getInt(companion.getEXTRA_INDEX(), -1);
        Logger logger = LOG;
        logger.debug("handleRemoveTrackIntent(playlistId = " + ((Object) string) + ", trackId = " + ((Object) string2) + ", index = " + i + ')');
        if (string != null && string2 != null && i >= 0) {
            PlaylistDB playlistDB = this.cache;
            if (playlistDB == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cache");
                playlistDB = null;
            }
            Playlist playlist = playlistDB.getPlaylist(string);
            int i2 = 0;
            boolean z = false;
            while (i2 < 3) {
                i2++;
                if (z) {
                    try {
                        playlist = updateCache(playlist.getId()).getPlaylist();
                    } catch (Throwable th) {
                        LOG.error("removeTracksFromPlaylist API call failed", th);
                        z = th instanceof PlaylistVersionException;
                    }
                }
                PlaylistDB playlistDB2 = this.cache;
                if (playlistDB2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("cache");
                    playlistDB2 = null;
                }
                Track track = playlistDB2.getTracksForPlaylist(string, 1, i).get(0);
                PlaylistDB playlistDB3 = this.cache;
                if (playlistDB3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("cache");
                    playlistDB3 = null;
                }
                playlistDB3.removeTrackFromPlaylist(string, string2, i);
                PlaylistService playlistService = this.service;
                if (playlistService == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_SERVICE);
                    playlistService = null;
                }
                String entryId = track.getEntryId();
                Intrinsics.checkNotNull(entryId);
                playlistService.removeTrackFromPlaylist(playlist, entryId);
                updateCache(playlist.getId());
            }
            LocalBroadcastManager.getInstance(this).sendBroadcast(PlaylistIntents.INSTANCE.createRefreshPlaylistIntent(playlist.getId()));
            return;
        }
        logger.warn("Not processing invalid intent");
    }

    private final GetUserPlaylistResponse updateCache(String playlistId) {
        PlaylistService playlistService = this.service;
        if (playlistService == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_SERVICE);
            playlistService = null;
        }
        GetUserPlaylistResponse userPlaylist$default = PlaylistService.getUserPlaylist$default(playlistService, playlistId, null, 2, null);
        PlaylistDB playlistDB = this.cache;
        if (playlistDB == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cache");
            playlistDB = null;
        }
        playlistDB.putTracksForPlaylist(userPlaylist$default.getTracks(), playlistId);
        PlaylistDB playlistDB2 = this.cache;
        if (playlistDB2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cache");
            playlistDB2 = null;
        }
        PlaylistDB.putPlaylists$default(playlistDB2, CollectionsKt__CollectionsJVMKt.listOf(userPlaylist$default.getPlaylist()), null, 2, null);
        return userPlaylist$default;
    }

    private final void handleUpdatePlaylist(final Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return;
        }
        PlaylistDB playlistDB = null;
        String string = extras.getString(PlaylistIntents.INSTANCE.getEXTRA_PLAYLIST_ID(), null);
        Logger logger = LOG;
        logger.debug(Intrinsics.stringPlus("handleUpdatePlaylist(playlistId = ", string));
        if (string != null) {
            PlaylistDB playlistDB2 = this.cache;
            if (playlistDB2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cache");
            } else {
                playlistDB = playlistDB2;
            }
            playlistDB.removePlaylist(string).subscribe(new Action1() { // from class: com.amazon.music.curate.data.PlaylistIntentReceiverService$$ExternalSyntheticLambda1
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    PlaylistIntentReceiverService.m1922handleUpdatePlaylist$lambda2$lambda1(this.f$0, intent, (Unit) obj);
                }
            });
            return;
        }
        logger.warn("Not processing invalid intent");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleUpdatePlaylist$lambda-2$lambda-1, reason: not valid java name */
    public static final void m1922handleUpdatePlaylist$lambda2$lambda1(PlaylistIntentReceiverService this$0, Intent intent, Unit unit) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(intent, "$intent");
        LocalBroadcastManager.getInstance(this$0).sendBroadcast(intent);
    }

    private final void handleRemoveCatalogPlaylist(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return;
        }
        PlaylistDB playlistDB = null;
        final String string = extras.getString(PlaylistIntents.INSTANCE.getEXTRA_PLAYLIST_ID(), null);
        Logger logger = LOG;
        logger.debug(Intrinsics.stringPlus("handleRemoveCatalogPlaylist(playlistId = ", string));
        if (string != null) {
            PlaylistDB playlistDB2 = this.cache;
            if (playlistDB2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cache");
            } else {
                playlistDB = playlistDB2;
            }
            playlistDB.removePlaylist(string).subscribe(new Action1() { // from class: com.amazon.music.curate.data.PlaylistIntentReceiverService$$ExternalSyntheticLambda0
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    PlaylistIntentReceiverService.m1920handleRemoveCatalogPlaylist$lambda4$lambda3(string, (Unit) obj);
                }
            });
            return;
        }
        logger.warn("Not processing invalid intent");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleRemoveCatalogPlaylist$lambda-4$lambda-3, reason: not valid java name */
    public static final void m1920handleRemoveCatalogPlaylist$lambda4$lambda3(String str, Unit unit) {
        LOG.debug(Intrinsics.stringPlus("removed playlist: (playlistId = ", str));
    }

    private final void handleUpdateCatalogPlaylist(final Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return;
        }
        PlaylistDB playlistDB = null;
        String string = extras.getString(PlaylistIntents.INSTANCE.getEXTRA_PLAYLIST_ID(), null);
        Logger logger = LOG;
        logger.debug(Intrinsics.stringPlus("handleUpdateCatalogPlaylist(playlistId = ", string));
        if (string != null) {
            PlaylistDB playlistDB2 = this.cache;
            if (playlistDB2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cache");
            } else {
                playlistDB = playlistDB2;
            }
            playlistDB.deletePersonalizedPlaylists().subscribe(new Action1() { // from class: com.amazon.music.curate.data.PlaylistIntentReceiverService$$ExternalSyntheticLambda2
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    PlaylistIntentReceiverService.m1921handleUpdateCatalogPlaylist$lambda6$lambda5(this.f$0, intent, (Unit) obj);
                }
            });
            return;
        }
        logger.warn("Not processing invalid intent");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleUpdateCatalogPlaylist$lambda-6$lambda-5, reason: not valid java name */
    public static final void m1921handleUpdateCatalogPlaylist$lambda6$lambda5(PlaylistIntentReceiverService this$0, Intent intent, Unit unit) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(intent, "$intent");
        LOG.debug("forced to refresh playlists");
        LocalBroadcastManager.getInstance(this$0).sendBroadcast(intent);
    }

    private final void handleResetLibraryLastSync() {
        SyncScheduler.resetContextMappingTableLastSync();
    }
}
