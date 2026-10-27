package com.amazon.mp3.catalog.fragment.datasource.repository;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.baseviews.model.configuration.PlaylistModelConfiguration;
import com.amazon.mp3.catalog.fragment.datasource.dao.PageViewModelDao;
import com.amazon.mp3.catalog.fragment.datasource.dao.PlaylistDetailDao;
import com.amazon.mp3.catalog.fragment.datasource.helper.CommunityPlaylistActions;
import com.amazon.mp3.catalog.fragment.datasource.helper.PlaylistFireflyProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.FetchType;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.playlist.db.PrimePlaylistDatabase;
import com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager;
import com.amazon.music.views.library.models.PageGridViewModel;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaylistDetailDataRepository.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010 \u001a\u00020\u001f\u0012\b\u0010$\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010'\u001a\u00020&\u0012\b\u0010)\u001a\u0004\u0018\u00010&\u0012\b\b\u0002\u0010,\u001a\u00020+\u0012\u0006\u0010;\u001a\u00020:¢\u0006\u0004\b<\u0010=J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\t\u001a\u00020\bH\u0002J\u001e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nJ\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0006J0\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u00152\b\u0010\u0017\u001a\u0004\u0018\u00010\u000eH\u0016J\u0015\u0010\u001b\u001a\u0004\u0018\u00010\nH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ\b\u0010\u001e\u001a\u0004\u0018\u00010\u001dR\u0017\u0010 \u001a\u00020\u001f8\u0006¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R\u0018\u0010$\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b$\u0010%R\u0014\u0010'\u001a\u00020&8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010(R\u0016\u0010)\u001a\u0004\u0018\u00010&8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b)\u0010*R\u0014\u0010,\u001a\u00020+8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b,\u0010-R\u001c\u0010/\u001a\n .*\u0004\u0018\u00010\u000e0\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u00100R\u0014\u00102\u001a\u0002018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u00103R\u0014\u00105\u001a\u0002048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u00106R\u0014\u00108\u001a\u0002078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b8\u00109\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006>"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/datasource/repository/PlaylistDetailDataRepository;", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;", "Lcom/amazon/mp3/playlist/CatalogPlaylist;", Environment.PLAYLIST_PATH, "", "updateUri", "Landroid/net/Uri;", "createUri", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/GetPrimePlaylistAction;", "getAction", "", "from", "to", "Lio/reactivex/Observable;", "", "reorderPlaylist", "uri", "Lcom/amazon/mp3/library/item/MusicTrack;", "getMusicTrack", "pageIndex", "pageSize", "Lcom/amazon/mp3/catalog/fragment/viewmodel/FetchType;", "fetchType", "nextPageToken", "Lio/reactivex/Single;", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "fetchPageViewModels", "getFollowerCount", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/amazon/mp3/library/item/AbstractItem;", "getPlaylist", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "playlistUri", "Landroid/net/Uri;", "", "isCatalog", "Z", "addToLibrary", "Ljava/lang/Boolean;", "Lcom/amazon/mp3/baseviews/model/configuration/PlaylistModelConfiguration;", "configuration", "Lcom/amazon/mp3/baseviews/model/configuration/PlaylistModelConfiguration;", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "Lcom/amazon/mp3/playlist/db/PrimePlaylistDatabase;", "db", "Lcom/amazon/mp3/playlist/db/PrimePlaylistDatabase;", "Lcom/amazon/mp3/catalog/fragment/datasource/helper/PlaylistFireflyProvider;", "playlistFireflyProvider", "Lcom/amazon/mp3/catalog/fragment/datasource/helper/PlaylistFireflyProvider;", "Lcom/amazon/mp3/catalog/fragment/datasource/helper/CommunityPlaylistActions;", "communityPlaylistActions", "Lcom/amazon/mp3/catalog/fragment/datasource/helper/CommunityPlaylistActions;", "Lcom/amazon/mp3/catalog/fragment/datasource/dao/PageViewModelDao;", "modelDao", "<init>", "(Landroid/content/Context;Landroid/net/Uri;ZLjava/lang/Boolean;Lcom/amazon/mp3/baseviews/model/configuration/PlaylistModelConfiguration;Lcom/amazon/mp3/catalog/fragment/datasource/dao/PageViewModelDao;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class PlaylistDetailDataRepository extends PageDataRepository {
    private final String TAG;
    private final Boolean addToLibrary;
    private final CommunityPlaylistActions communityPlaylistActions;
    private final PlaylistModelConfiguration configuration;
    private final Context context;
    private final PrimePlaylistDatabase db;
    private final boolean isCatalog;
    private final PlaylistFireflyProvider playlistFireflyProvider;
    private Uri playlistUri;

    /* JADX INFO: renamed from: com.amazon.mp3.catalog.fragment.datasource.repository.PlaylistDetailDataRepository$getFollowerCount$1, reason: invalid class name */
    /* JADX INFO: compiled from: PlaylistDetailDataRepository.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.catalog.fragment.datasource.repository.PlaylistDetailDataRepository", f = "PlaylistDetailDataRepository.kt", i = {0}, l = {117}, m = "getFollowerCount", n = {"this"}, s = {"L$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PlaylistDetailDataRepository.this.getFollowerCount(this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlaylistDetailDataRepository(Context context, Uri uri, boolean z, Boolean bool, PlaylistModelConfiguration configuration, PageViewModelDao modelDao) {
        super(modelDao);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(modelDao, "modelDao");
        this.context = context;
        this.playlistUri = uri;
        this.isCatalog = z;
        this.addToLibrary = bool;
        this.configuration = configuration;
        this.TAG = PlaylistDetailDataRepository.class.getSimpleName();
        this.db = new PrimePlaylistDatabaseManager(context);
        PlaylistFireflyProvider playlistFireflyProvider = new PlaylistFireflyProvider();
        this.playlistFireflyProvider = playlistFireflyProvider;
        this.communityPlaylistActions = new CommunityPlaylistActions(playlistFireflyProvider);
    }

    public final Observable<String> reorderPlaylist(int from, int to) {
        PageViewModelDao modelDao = getModelDao();
        PlaylistDetailDao playlistDetailDao = modelDao instanceof PlaylistDetailDao ? (PlaylistDetailDao) modelDao : null;
        if (playlistDetailDao == null) {
            return null;
        }
        return playlistDetailDao.reorderPlaylist(from, to);
    }

    public final MusicTrack getMusicTrack(Uri uri) {
        PageViewModelDao modelDao = getModelDao();
        PlaylistDetailDao playlistDetailDao = modelDao instanceof PlaylistDetailDao ? (PlaylistDetailDao) modelDao : null;
        if (playlistDetailDao == null) {
            return null;
        }
        return playlistDetailDao.getMusicTrack(uri);
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.repository.PageDataRepository
    public Single<PageGridViewModel> fetchPageViewModels(int pageIndex, int pageSize, FetchType fetchType, String nextPageToken) {
        Intrinsics.checkNotNullParameter(fetchType, "fetchType");
        if (this.isCatalog) {
            Single<PageGridViewModel> singleCreate = Single.create(new SingleOnSubscribe() { // from class: com.amazon.mp3.catalog.fragment.datasource.repository.PlaylistDetailDataRepository$$ExternalSyntheticLambda0
                @Override // io.reactivex.SingleOnSubscribe
                public final void subscribe(SingleEmitter singleEmitter) {
                    PlaylistDetailDataRepository.m576fetchPageViewModels$lambda1(this.f$0, singleEmitter);
                }
            });
            Intrinsics.checkNotNullExpressionValue(singleCreate, "create { emitter ->\n    …          }\n            }");
            return singleCreate;
        }
        return PageViewModelDao.DefaultImpls.fetchViewModelItems$default(getModelDao(), pageIndex, pageSize, fetchType, null, 8, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchPageViewModels$lambda-1, reason: not valid java name */
    public static final void m576fetchPageViewModels$lambda1(PlaylistDetailDataRepository this$0, SingleEmitter emitter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        try {
            CatalogPlaylist catalogPlaylistRun = this$0.getAction().run();
            if (catalogPlaylistRun != null) {
                this$0.updateUri(catalogPlaylistRun);
            }
            PageViewModelDao modelDao = this$0.getModelDao();
            PageGridViewModel pageGridViewModelFetchPageModel = null;
            PlaylistDetailDao playlistDetailDao = modelDao instanceof PlaylistDetailDao ? (PlaylistDetailDao) modelDao : null;
            if (playlistDetailDao != null) {
                pageGridViewModelFetchPageModel = playlistDetailDao.fetchPageModel();
            }
            if (pageGridViewModelFetchPageModel == null) {
                pageGridViewModelFetchPageModel = new PageGridViewModel(null, null, null, null, CollectionsKt__CollectionsKt.emptyList(), 2, 0, null, null, null, null, 1807, null);
            }
            emitter.onSuccess(pageGridViewModelFetchPageModel);
        } catch (Exception e) {
            emitter.tryOnError(e);
        }
    }

    private final void updateUri(CatalogPlaylist playlist) {
        Uri uriCreateUri = createUri(playlist);
        this.playlistUri = uriCreateUri;
        PageViewModelDao modelDao = getModelDao();
        PlaylistDetailDao playlistDetailDao = modelDao instanceof PlaylistDetailDao ? (PlaylistDetailDao) modelDao : null;
        if (playlistDetailDao == null) {
            return;
        }
        playlistDetailDao.updatePlaylistUri(uriCreateUri);
    }

    private final Uri createUri(CatalogPlaylist playlist) {
        List<String> pathSegments;
        Uri uri = this.playlistUri;
        String str = null;
        if (uri != null && (pathSegments = uri.getPathSegments()) != null) {
            str = (String) CollectionsKt___CollectionsKt.getOrNull(pathSegments, 1);
        }
        List<String> pathSegments2 = playlist.getContentUri().getPathSegments();
        Intrinsics.checkNotNullExpressionValue(pathSegments2, "playlist.contentUri.pathSegments");
        String str2 = (String) CollectionsKt___CollectionsKt.getOrNull(pathSegments2, 4);
        if (str != null && str2 != null) {
            Uri contentUri = MediaProvider.PrimePlaylists.getContentUri(str, str2);
            Intrinsics.checkNotNullExpressionValue(contentUri, "getContentUri(source, asin)");
            return contentUri;
        }
        Uri contentUri2 = playlist.getContentUri();
        Intrinsics.checkNotNullExpressionValue(contentUri2, "playlist.contentUri");
        return contentUri2;
    }

    private final GetPrimePlaylistAction getAction() {
        return new GetPrimePlaylistAction(this.db, MediaProvider.Playlists.getCatalogPlaylistId(this.playlistUri), 0, this.addToLibrary, MediaProvider.SharedUserPlaylists.isSharedUserPlaylist(this.playlistUri), this.context);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getFollowerCount(kotlin.coroutines.Continuation<? super java.lang.Integer> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.amazon.mp3.catalog.fragment.datasource.repository.PlaylistDetailDataRepository.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r5
            com.amazon.mp3.catalog.fragment.datasource.repository.PlaylistDetailDataRepository$getFollowerCount$1 r0 = (com.amazon.mp3.catalog.fragment.datasource.repository.PlaylistDetailDataRepository.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.catalog.fragment.datasource.repository.PlaylistDetailDataRepository$getFollowerCount$1 r0 = new com.amazon.mp3.catalog.fragment.datasource.repository.PlaylistDetailDataRepository$getFollowerCount$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L37
            if (r2 != r3) goto L2f
            java.lang.Object r0 = r0.L$0
            com.amazon.mp3.catalog.fragment.datasource.repository.PlaylistDetailDataRepository r0 = (com.amazon.mp3.catalog.fragment.datasource.repository.PlaylistDetailDataRepository) r0
            kotlin.ResultKt.throwOnFailure(r5)     // Catch: java.lang.Exception -> L2d
            goto L56
        L2d:
            r5 = move-exception
            goto L5b
        L2f:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L37:
            kotlin.ResultKt.throwOnFailure(r5)
            com.amazon.mp3.library.item.AbstractItem r5 = r4.getPlaylist()     // Catch: java.lang.Exception -> L59
            if (r5 != 0) goto L41
            goto L6b
        L41:
            com.amazon.mp3.baseviews.model.configuration.PlaylistModelConfiguration r2 = r4.configuration     // Catch: java.lang.Exception -> L59
            boolean r2 = r2.shouldDisplayFollowerCount(r5)     // Catch: java.lang.Exception -> L59
            if (r2 == 0) goto L57
            com.amazon.mp3.catalog.fragment.datasource.helper.CommunityPlaylistActions r2 = r4.communityPlaylistActions     // Catch: java.lang.Exception -> L59
            r0.L$0 = r4     // Catch: java.lang.Exception -> L59
            r0.label = r3     // Catch: java.lang.Exception -> L59
            java.lang.Object r5 = r2.fetchFollowerCount(r5, r0)     // Catch: java.lang.Exception -> L59
            if (r5 != r1) goto L56
            return r1
        L56:
            return r5
        L57:
            r5 = 0
            return r5
        L59:
            r5 = move-exception
            r0 = r4
        L5b:
            java.lang.String r0 = r0.TAG
            java.lang.String r5 = r5.getMessage()
            java.lang.String r1 = "unable to retrieve follower count due to error "
            java.lang.String r5 = kotlin.jvm.internal.Intrinsics.stringPlus(r1, r5)
            com.amazon.mp3.util.Log.error(r0, r5)
        L6b:
            r5 = -1
            java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.datasource.repository.PlaylistDetailDataRepository.getFollowerCount(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final AbstractItem getPlaylist() {
        PageViewModelDao modelDao = getModelDao();
        PlaylistDetailDao playlistDetailDao = modelDao instanceof PlaylistDetailDao ? (PlaylistDetailDao) modelDao : null;
        if (playlistDetailDao == null) {
            return null;
        }
        return playlistDetailDao.getPlaylist();
    }
}
