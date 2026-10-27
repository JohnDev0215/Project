package com.amazon.music.curate.provider;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.music.curate.data.PlaylistService;
import com.amazon.music.curate.data.PlaylistServiceApi;
import com.amazon.music.curate.data.RecentsService;
import com.amazon.music.curate.data.RecentsServiceApi;
import com.amazon.music.curate.data.database.AppDatabase;
import com.amazon.music.curate.data.database.PlaylistDB;
import com.amazon.music.curate.data.database.RecsCache;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InternalProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001:B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010-\u001a\u00020\n2\u0006\u0010.\u001a\u00020/2\u0006\u0010!\u001a\u00020\"J\u000e\u00100\u001a\u00020\u00042\u0006\u0010.\u001a\u00020/J\u000e\u00101\u001a\u00020\u00102\u0006\u00102\u001a\u000203J\u000e\u00104\u001a\u00020\u00162\u0006\u00102\u001a\u000203J\u000e\u00105\u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\"J\u001e\u00106\u001a\u00020\"2\u0006\u00102\u001a\u0002032\u0006\u0010.\u001a\u00020/2\u0006\u00107\u001a\u000208J\u000e\u00109\u001a\u00020(2\u0006\u00102\u001a\u000203R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001c\u0010!\u001a\u0004\u0018\u00010\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001c\u0010'\u001a\u0004\u0018\u00010(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,¨\u0006;"}, d2 = {"Lcom/amazon/music/curate/provider/InternalProvider;", "", "()V", "database", "Lcom/amazon/music/curate/data/database/AppDatabase;", "getDatabase", "()Lcom/amazon/music/curate/data/database/AppDatabase;", "setDatabase", "(Lcom/amazon/music/curate/data/database/AppDatabase;)V", "playlistDB", "Lcom/amazon/music/curate/data/database/PlaylistDB;", "getPlaylistDB", "()Lcom/amazon/music/curate/data/database/PlaylistDB;", "setPlaylistDB", "(Lcom/amazon/music/curate/data/database/PlaylistDB;)V", "recentsService", "Lcom/amazon/music/curate/data/RecentsService;", "getRecentsService", "()Lcom/amazon/music/curate/data/RecentsService;", "setRecentsService", "(Lcom/amazon/music/curate/data/RecentsService;)V", "recentsServiceApi", "Lcom/amazon/music/curate/data/RecentsServiceApi;", "getRecentsServiceApi", "()Lcom/amazon/music/curate/data/RecentsServiceApi;", "setRecentsServiceApi", "(Lcom/amazon/music/curate/data/RecentsServiceApi;)V", "recsCache", "Lcom/amazon/music/curate/data/database/RecsCache;", "getRecsCache", "()Lcom/amazon/music/curate/data/database/RecsCache;", "setRecsCache", "(Lcom/amazon/music/curate/data/database/RecsCache;)V", NotificationCompat.CATEGORY_SERVICE, "Lcom/amazon/music/curate/data/PlaylistService;", "getService", "()Lcom/amazon/music/curate/data/PlaylistService;", "setService", "(Lcom/amazon/music/curate/data/PlaylistService;)V", "serviceApi", "Lcom/amazon/music/curate/data/PlaylistServiceApi;", "getServiceApi", "()Lcom/amazon/music/curate/data/PlaylistServiceApi;", "setServiceApi", "(Lcom/amazon/music/curate/data/PlaylistServiceApi;)V", "provideCache", "context", "Landroid/content/Context;", "provideDatabase", "provideRecentsService", "requestInterceptor", "Lcom/amazon/hermes/RequestInterceptor;", "provideRecentsServiceApi", "provideRecsCache", "provideService", "featureFlagProvider", "Lcom/amazon/music/curate/provider/FeatureFlagProvider;", "provideServiceApi", "Singleton", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class InternalProvider {
    private AppDatabase database;
    private PlaylistDB playlistDB;
    private RecentsService recentsService;
    private RecentsServiceApi recentsServiceApi;
    private RecsCache recsCache;
    private PlaylistService service;
    private PlaylistServiceApi serviceApi;

    /* JADX INFO: compiled from: InternalProvider.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/curate/provider/InternalProvider$Singleton;", "", "()V", "instance", "Lcom/amazon/music/curate/provider/InternalProvider;", "get", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Singleton {
        public static final Singleton INSTANCE = new Singleton();
        private static InternalProvider instance;

        private Singleton() {
        }

        public final InternalProvider get() {
            if (instance == null) {
                instance = new InternalProvider();
            }
            InternalProvider internalProvider = instance;
            Intrinsics.checkNotNull(internalProvider);
            return internalProvider;
        }
    }

    public final AppDatabase getDatabase() {
        return this.database;
    }

    public final void setDatabase(AppDatabase appDatabase) {
        this.database = appDatabase;
    }

    public final PlaylistDB getPlaylistDB() {
        return this.playlistDB;
    }

    public final void setPlaylistDB(PlaylistDB playlistDB) {
        this.playlistDB = playlistDB;
    }

    public final RecsCache getRecsCache() {
        return this.recsCache;
    }

    public final void setRecsCache(RecsCache recsCache) {
        this.recsCache = recsCache;
    }

    public final PlaylistService getService() {
        return this.service;
    }

    public final void setService(PlaylistService playlistService) {
        this.service = playlistService;
    }

    public final PlaylistServiceApi getServiceApi() {
        return this.serviceApi;
    }

    public final void setServiceApi(PlaylistServiceApi playlistServiceApi) {
        this.serviceApi = playlistServiceApi;
    }

    public final RecentsService getRecentsService() {
        return this.recentsService;
    }

    public final void setRecentsService(RecentsService recentsService) {
        this.recentsService = recentsService;
    }

    public final RecentsServiceApi getRecentsServiceApi() {
        return this.recentsServiceApi;
    }

    public final void setRecentsServiceApi(RecentsServiceApi recentsServiceApi) {
        this.recentsServiceApi = recentsServiceApi;
    }

    public final AppDatabase provideDatabase(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (this.database == null) {
            this.database = AppDatabase.INSTANCE.getDatabase(context);
        }
        AppDatabase appDatabase = this.database;
        Intrinsics.checkNotNull(appDatabase);
        return appDatabase;
    }

    public final PlaylistDB provideCache(Context context, PlaylistService service) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(service, "service");
        if (this.playlistDB == null) {
            AppDatabase appDatabaseProvideDatabase = provideDatabase(context);
            this.playlistDB = new PlaylistDB(appDatabaseProvideDatabase.trackDao(), appDatabaseProvideDatabase.playlistDao(), appDatabaseProvideDatabase.playlistTrackDao(), appDatabaseProvideDatabase.libraryContextMappingDao(), service);
        }
        PlaylistDB playlistDB = this.playlistDB;
        Intrinsics.checkNotNull(playlistDB);
        return playlistDB;
    }

    public final RecsCache provideRecsCache(PlaylistService service) {
        Intrinsics.checkNotNullParameter(service, "service");
        if (this.recsCache == null) {
            this.recsCache = new RecsCache(service);
        }
        RecsCache recsCache = this.recsCache;
        Intrinsics.checkNotNull(recsCache);
        return recsCache;
    }

    public final PlaylistService provideService(RequestInterceptor requestInterceptor, Context context, FeatureFlagProvider featureFlagProvider) {
        Intrinsics.checkNotNullParameter(requestInterceptor, "requestInterceptor");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(featureFlagProvider, "featureFlagProvider");
        if (this.service == null) {
            this.service = new PlaylistService(provideServiceApi(requestInterceptor), context, featureFlagProvider);
        }
        PlaylistService playlistService = this.service;
        Intrinsics.checkNotNull(playlistService);
        return playlistService;
    }

    public final PlaylistServiceApi provideServiceApi(RequestInterceptor requestInterceptor) {
        Intrinsics.checkNotNullParameter(requestInterceptor, "requestInterceptor");
        if (this.serviceApi == null) {
            this.serviceApi = new PlaylistServiceApi(requestInterceptor);
        }
        PlaylistServiceApi playlistServiceApi = this.serviceApi;
        Intrinsics.checkNotNull(playlistServiceApi);
        return playlistServiceApi;
    }

    public final RecentsService provideRecentsService(RequestInterceptor requestInterceptor) {
        Intrinsics.checkNotNullParameter(requestInterceptor, "requestInterceptor");
        if (this.recentsService == null) {
            this.recentsService = new RecentsService(provideRecentsServiceApi(requestInterceptor));
        }
        RecentsService recentsService = this.recentsService;
        Intrinsics.checkNotNull(recentsService);
        return recentsService;
    }

    public final RecentsServiceApi provideRecentsServiceApi(RequestInterceptor requestInterceptor) {
        Intrinsics.checkNotNullParameter(requestInterceptor, "requestInterceptor");
        if (this.recentsServiceApi == null) {
            this.recentsServiceApi = new RecentsServiceApi(requestInterceptor);
        }
        RecentsServiceApi recentsServiceApi = this.recentsServiceApi;
        Intrinsics.checkNotNull(recentsServiceApi);
        return recentsServiceApi;
    }
}
