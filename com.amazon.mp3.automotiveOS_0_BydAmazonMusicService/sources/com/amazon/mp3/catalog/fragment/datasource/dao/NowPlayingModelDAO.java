package com.amazon.mp3.catalog.fragment.datasource.dao;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.catalog.fragment.datasource.dao.EditablePageViewModelDao;
import com.amazon.mp3.catalog.fragment.viewmodel.FetchType;
import com.amazon.mp3.library.cache.likes.LikesStateCacheManager;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingQueueTables;
import com.amazon.mp3.likes.LikesServiceProxy;
import com.amazon.mp3.lyrics.item.LyricsStatus;
import com.amazon.mp3.playback.harley.PlayQueueSequencer;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.mp3.prime.CTAPrimeCache;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.view.model.BadgeModel;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.SimpleHorizontalTileModel;
import com.amazon.music.views.library.models.VisualRowItemModel;
import com.amazon.music.views.library.models.base.BaseViewContentModel;
import com.amazon.music.views.library.models.base.LibraryAddableModel;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NowPlayingModelDAO.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 *2\u00020\u0001:\u0001*B\u0019\u0012\u0006\u0010\u001c\u001a\u00020\u001b\u0012\b\b\u0002\u0010 \u001a\u00020\u0002¢\u0006\u0004\b(\u0010)J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0018\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0002H\u0002J0\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0016J\u0018\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\nH\u0016J\u0010\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\nH\u0016J\b\u0010\u001a\u001a\u00020\u0016H\u0016R\u0017\u0010\u001c\u001a\u00020\u001b8\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!R\u001b\u0010'\u001a\u00020\"8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&¨\u0006+"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/datasource/dao/NowPlayingModelDAO;", "Lcom/amazon/mp3/catalog/fragment/datasource/dao/EditablePageViewModelDao;", "", "shuffled", "Landroid/database/Cursor;", "getPlayQueueCursor", "cursor", "isShuffled", "Lcom/amazon/music/views/library/models/base/BaseViewContentModel;", "convertPlayQueueRowToBaseViewModel", "", "pageIndex", "pageSize", "Lcom/amazon/mp3/catalog/fragment/viewmodel/FetchType;", "fetchType", "", "nextPageToken", "Lio/reactivex/Single;", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "fetchViewModelItems", "from", "to", "", "reorderItem", "index", "deleteItem", "clearItems", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "isCarModePage", "Z", "Lcom/amazon/mp3/prime/CTAPrimeCache;", "ctaPrimeCache$delegate", "Lkotlin/Lazy;", "getCtaPrimeCache", "()Lcom/amazon/mp3/prime/CTAPrimeCache;", "ctaPrimeCache", "<init>", "(Landroid/content/Context;Z)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class NowPlayingModelDAO implements EditablePageViewModelDao {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = NowPlayingModelDAO.class.getSimpleName();
    private final Context context;

    /* JADX INFO: renamed from: ctaPrimeCache$delegate, reason: from kotlin metadata */
    private final Lazy ctaPrimeCache;
    private final boolean isCarModePage;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: clearItems$lambda-11, reason: not valid java name */
    public static final void m559clearItems$lambda11() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: deleteItem$lambda-8, reason: not valid java name */
    public static final void m561deleteItem$lambda8() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: reorderItem$lambda-5, reason: not valid java name */
    public static final void m565reorderItem$lambda5() {
    }

    public NowPlayingModelDAO(Context context, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.isCarModePage = z;
        this.ctaPrimeCache = LazyKt__LazyJVMKt.lazy(new Function0<CTAPrimeCache>() { // from class: com.amazon.mp3.catalog.fragment.datasource.dao.NowPlayingModelDAO$ctaPrimeCache$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CTAPrimeCache invoke() {
                return new CTAPrimeCache(this.this$0.getContext());
            }
        });
    }

    public final Context getContext() {
        return this.context;
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.EditablePageViewModelDao, com.amazon.mp3.catalog.fragment.datasource.dao.PageViewModelDao
    public int getDefaultPageSize() {
        return EditablePageViewModelDao.DefaultImpls.getDefaultPageSize(this);
    }

    /* JADX INFO: compiled from: NowPlayingModelDAO.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/datasource/dao/NowPlayingModelDAO$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final CTAPrimeCache getCtaPrimeCache() {
        return (CTAPrimeCache) this.ctaPrimeCache.getValue();
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.PageViewModelDao
    public Single<PageGridViewModel> fetchViewModelItems(final int pageIndex, int pageSize, FetchType fetchType, String nextPageToken) {
        Intrinsics.checkNotNullParameter(fetchType, "fetchType");
        Single singleCreate = Single.create(new SingleOnSubscribe() { // from class: com.amazon.mp3.catalog.fragment.datasource.dao.NowPlayingModelDAO$$ExternalSyntheticLambda8
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                NowPlayingModelDAO.m563fetchViewModelItems$lambda3(this.f$0, pageIndex, singleEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate, "create { emitter ->\n    …\n            ))\n        }");
        Single<PageGridViewModel> singleSubscribeOn = singleCreate.subscribeOn(Schedulers.io());
        Intrinsics.checkNotNullExpressionValue(singleSubscribeOn, "single.subscribeOn(Schedulers.io())");
        return singleSubscribeOn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchViewModelItems$lambda-3, reason: not valid java name */
    public static final void m563fetchViewModelItems$lambda3(NowPlayingModelDAO this$0, int i, SingleEmitter emitter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        boolean zIsShuffled = PlayQueueSequencer.getInstance().isShuffled();
        Cursor playQueueCursor = this$0.getPlayQueueCursor(zIsShuffled);
        ArrayList<BaseViewContentModel> arrayList = new ArrayList();
        if (playQueueCursor != null) {
            boolean zMoveToPosition = playQueueCursor.moveToPosition(i * 20);
            int i2 = 20;
            while (zMoveToPosition) {
                arrayList.add(this$0.convertPlayQueueRowToBaseViewModel(playQueueCursor, zIsShuffled));
                i2--;
                if (!playQueueCursor.moveToNext() || i2 == 0) {
                    break;
                }
            }
        }
        if (playQueueCursor != null) {
            playQueueCursor.close();
        }
        if (!this$0.isCarModePage) {
            try {
                LikesStateCacheManager instance$default = LikesStateCacheManager.Companion.getInstance$default(LikesStateCacheManager.INSTANCE, null, 1, null);
                ArrayList arrayList2 = new ArrayList();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    TrackMetadata trackMetadata = (TrackMetadata) ((BaseViewContentModel) it.next()).getMetadata();
                    if (trackMetadata != null) {
                        String asin = trackMetadata.getAsin();
                        if (!(asin == null || asin.length() == 0)) {
                            String asin2 = trackMetadata.getAsin();
                            Intrinsics.checkNotNull(asin2);
                            arrayList2.add(asin2);
                        }
                    }
                }
                LikesServiceProxy.INSTANCE.getLikesStateForTrackAsinsAndCache(this$0.context, arrayList2);
                for (BaseViewContentModel baseViewContentModel : arrayList) {
                    LibraryAddableModel libraryAddableModel = baseViewContentModel instanceof LibraryAddableModel ? (LibraryAddableModel) baseViewContentModel : null;
                    ContentMetadata metadata = libraryAddableModel == null ? null : libraryAddableModel.getMetadata();
                    TrackMetadata trackMetadata2 = metadata instanceof TrackMetadata ? (TrackMetadata) metadata : null;
                    String asin3 = trackMetadata2 == null ? null : trackMetadata2.getAsin();
                    Boolean bool = (asin3 == null || instance$default == null) ? null : instance$default.get(asin3);
                    if (bool != null) {
                        libraryAddableModel.setLiked(bool.booleanValue());
                    }
                }
            } catch (Exception e) {
                Log.error(INSTANCE.getClass().getName(), e.getMessage());
            }
        }
        emitter.onSuccess(new PageGridViewModel(null, null, null, null, arrayList, 2, i, null, null, null, null, 1807, null));
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.EditablePageViewModelDao
    public void reorderItem(final int from, final int to) {
        Completable.create(new CompletableOnSubscribe() { // from class: com.amazon.mp3.catalog.fragment.datasource.dao.NowPlayingModelDAO$$ExternalSyntheticLambda2
            @Override // io.reactivex.CompletableOnSubscribe
            public final void subscribe(CompletableEmitter completableEmitter) {
                NowPlayingModelDAO.m564reorderItem$lambda4(from, to, completableEmitter);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action() { // from class: com.amazon.mp3.catalog.fragment.datasource.dao.NowPlayingModelDAO$$ExternalSyntheticLambda3
            @Override // io.reactivex.functions.Action
            public final void run() {
                NowPlayingModelDAO.m565reorderItem$lambda5();
            }
        }, new Consumer() { // from class: com.amazon.mp3.catalog.fragment.datasource.dao.NowPlayingModelDAO$$ExternalSyntheticLambda4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                NowPlayingModelDAO.m566reorderItem$lambda6(this.f$0, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: reorderItem$lambda-4, reason: not valid java name */
    public static final void m564reorderItem$lambda4(int i, int i2, CompletableEmitter onSubscribe) {
        Intrinsics.checkNotNullParameter(onSubscribe, "onSubscribe");
        PlayQueueSequencer.getInstance().move(i, i2);
        onSubscribe.onComplete();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: reorderItem$lambda-6, reason: not valid java name */
    public static final void m566reorderItem$lambda6(NowPlayingModelDAO this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.error(TAG, Intrinsics.stringPlus("Error happens when reordering items ", th));
        this$0.context.sendBroadcast(new Intent("com.amazon.mp3.playback.video.actionRefreshNowPlayingQueueScreen"));
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.EditablePageViewModelDao
    public void deleteItem(final int index) {
        Completable.create(new CompletableOnSubscribe() { // from class: com.amazon.mp3.catalog.fragment.datasource.dao.NowPlayingModelDAO$$ExternalSyntheticLambda5
            @Override // io.reactivex.CompletableOnSubscribe
            public final void subscribe(CompletableEmitter completableEmitter) {
                NowPlayingModelDAO.m560deleteItem$lambda7(index, completableEmitter);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action() { // from class: com.amazon.mp3.catalog.fragment.datasource.dao.NowPlayingModelDAO$$ExternalSyntheticLambda6
            @Override // io.reactivex.functions.Action
            public final void run() {
                NowPlayingModelDAO.m561deleteItem$lambda8();
            }
        }, new Consumer() { // from class: com.amazon.mp3.catalog.fragment.datasource.dao.NowPlayingModelDAO$$ExternalSyntheticLambda7
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                NowPlayingModelDAO.m562deleteItem$lambda9(this.f$0, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: deleteItem$lambda-7, reason: not valid java name */
    public static final void m560deleteItem$lambda7(int i, CompletableEmitter onSubscribe) {
        Intrinsics.checkNotNullParameter(onSubscribe, "onSubscribe");
        PlayQueueSequencer.getInstance().removeFromPlayQueue(i);
        onSubscribe.onComplete();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: deleteItem$lambda-9, reason: not valid java name */
    public static final void m562deleteItem$lambda9(NowPlayingModelDAO this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.error(TAG, Intrinsics.stringPlus("Error happens when deleting items ", th));
        this$0.context.sendBroadcast(new Intent("com.amazon.mp3.playback.video.actionRefreshNowPlayingQueueScreen"));
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.EditablePageViewModelDao
    public void clearItems() {
        Completable.create(new CompletableOnSubscribe() { // from class: com.amazon.mp3.catalog.fragment.datasource.dao.NowPlayingModelDAO$$ExternalSyntheticLambda0
            @Override // io.reactivex.CompletableOnSubscribe
            public final void subscribe(CompletableEmitter completableEmitter) {
                NowPlayingModelDAO.m558clearItems$lambda10(completableEmitter);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action() { // from class: com.amazon.mp3.catalog.fragment.datasource.dao.NowPlayingModelDAO$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Action
            public final void run() {
                NowPlayingModelDAO.m559clearItems$lambda11();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: clearItems$lambda-10, reason: not valid java name */
    public static final void m558clearItems$lambda10(CompletableEmitter onSubscribe) {
        Intrinsics.checkNotNullParameter(onSubscribe, "onSubscribe");
        PlayQueueSequencer.getInstance().clear();
        onSubscribe.onComplete();
    }

    private final Cursor getPlayQueueCursor(boolean shuffled) {
        return CirrusDatabase.getReadOnlyDatabase(this.context).rawQuery(Intrinsics.stringPlus(NowPlayingQueueTables.PLAYQUEUE_QUERY, shuffled ? " ORDER BY shuffle" : " ORDER BY udo"), null);
    }

    private final BaseViewContentModel convertPlayQueueRowToBaseViewModel(Cursor cursor, boolean isShuffled) {
        Uri uri;
        BadgeModel badgeModel;
        int i = cursor.getInt(cursor.getColumnIndex(isShuffled ? "shuffle" : "udo"));
        int columnIndex = cursor.getColumnIndex("album_art_url_thumbnail");
        String string = cursor.getString(columnIndex);
        String string2 = cursor.getString(cursor.getColumnIndex("asin"));
        if (string2 == null) {
            string2 = "";
        }
        String string3 = cursor.getString(cursor.getColumnIndex("album_asin"));
        if (TextUtils.isEmpty(string)) {
            string = getCtaPrimeCache().getArtworkUrlForAsin(string3);
        }
        ArtworkFrameModel artworkFrameModel = new ArtworkFrameModel("", string, 2, null, null, null, null, null, null, null, null, null, 4088, null);
        String title = cursor.getString(cursor.getColumnIndex("title"));
        String string4 = cursor.getString(cursor.getColumnIndex("artist"));
        String string5 = cursor.getString(cursor.getColumnIndex("album"));
        if (ChildUserUtil.INSTANCE.isChildUser(this.context)) {
            string5 = StringUtil.removeExplicitLabelFromAlbumOrTrackTitle(string5);
        }
        String str = string5;
        String string6 = cursor.getString(cursor.getColumnIndex("date_updated"));
        String str2 = string6 == null ? "" : string6;
        String string7 = cursor.getString(cursor.getColumnIndex("duration"));
        String str3 = string7 != null ? string7 : "";
        cursor.getInt(cursor.getColumnIndex("download_state"));
        boolean zAreEqual = Intrinsics.areEqual(LyricsStatus.AVAILABLE.toString(), cursor.getString(cursor.getColumnIndex("lyrics_state")));
        String string8 = cursor.getString(cursor.getColumnIndex("asset_type"));
        if (string8 == null) {
            string8 = "AUDIO";
        }
        ContentPlaybackUtils contentPlaybackUtils = ContentPlaybackUtils.INSTANCE;
        Uri trackUri = contentPlaybackUtils.getTrackUri(string2);
        if (!Intrinsics.areEqual(trackUri, contentPlaybackUtils.getDEFAULT_URI()) || columnIndex < 0) {
            uri = trackUri;
        } else {
            Uri primeAdditionalTrackContentUri = MediaProvider.Tracks.getPrimeAdditionalTrackContentUri("cirrus-local", "primeplaylist", string2);
            Intrinsics.checkNotNullExpressionValue(primeAdditionalTrackContentUri, "getPrimeAdditionalTrackC…URCE_PRIMEPLAYLIST, asin)");
            uri = primeAdditionalTrackContentUri;
        }
        MusicTrack trackIncludingPrimePlaylistTrack = new CirrusSourceLibraryItemFactory(this.context).getTrackIncludingPrimePlaylistTrack(uri);
        BadgeModel encodingBadgeModel = ContentEncodingDecoder.getEncodingBadgeModel(trackIncludingPrimePlaylistTrack == null ? null : trackIncludingPrimePlaylistTrack.getContentEncodings(), this.context, 8);
        Intrinsics.checkNotNullExpressionValue(encodingBadgeModel, "getEncodingBadgeModel(tr…ings, context, View.GONE)");
        if (AmazonApplication.getCapabilities().isSpatialAudioEnabled(this.context)) {
            badgeModel = ContentEncodingDecoder.getImmersiveContentBadgeModel(trackIncludingPrimePlaylistTrack == null ? null : trackIncludingPrimePlaylistTrack.getContentEncodings(), this.context, 8);
        } else {
            badgeModel = new BadgeModel("", "", 8, null, 8, null);
        }
        BadgeModel badgeModel2 = badgeModel;
        TrackMetadata trackMetadata = new TrackMetadata(null, uri, null, string2, title, string, null, trackIncludingPrimePlaylistTrack == null ? null : trackIncludingPrimePlaylistTrack.getAlbumAsin(), trackIncludingPrimePlaylistTrack == null ? null : trackIncludingPrimePlaylistTrack.getAlbumName(), trackIncludingPrimePlaylistTrack == null ? null : trackIncludingPrimePlaylistTrack.getAlbumArtistName(), trackIncludingPrimePlaylistTrack == null ? null : trackIncludingPrimePlaylistTrack.getArtistAsin(), null, trackIncludingPrimePlaylistTrack == null ? null : trackIncludingPrimePlaylistTrack.getSource(), null, trackIncludingPrimePlaylistTrack == null ? null : Boolean.valueOf(trackIncludingPrimePlaylistTrack.isInLibrary()), trackIncludingPrimePlaylistTrack == null ? null : Boolean.valueOf(trackIncludingPrimePlaylistTrack.isAllOwned()), trackIncludingPrimePlaylistTrack == null ? null : Integer.valueOf(trackIncludingPrimePlaylistTrack.getDownloadState()), trackIncludingPrimePlaylistTrack == null ? null : Boolean.valueOf(trackIncludingPrimePlaylistTrack.isExplicit()), Integer.valueOf(i), trackIncludingPrimePlaylistTrack == null ? null : trackIncludingPrimePlaylistTrack.getSupportedTiers(), string8, trackIncludingPrimePlaylistTrack == null ? null : trackIncludingPrimePlaylistTrack.getAssetQualities(), 0, null, null, null, null, null, null, false, null, false, trackIncludingPrimePlaylistTrack == null ? null : trackIncludingPrimePlaylistTrack.getPlaymodeEligibility(), trackIncludingPrimePlaylistTrack != null ? trackIncludingPrimePlaylistTrack.getOnDemandTiers() : null, null, null, -4183995, 12, null);
        if (this.isCarModePage) {
            return new SimpleHorizontalTileModel(null, title, string4, null, artworkFrameModel, false, false, null, 2, trackMetadata, null, false, 3304, null);
        }
        String badgeText = encodingBadgeModel.getBadgeText();
        String badgeText2 = badgeModel2.getBadgeText();
        boolean z = !Intrinsics.areEqual(string8, "VIDEO");
        boolean zIsInLibrary = trackIncludingPrimePlaylistTrack == null ? false : trackIncludingPrimePlaylistTrack.isInLibrary();
        boolean zIsLiked = trackIncludingPrimePlaylistTrack == null ? false : trackIncludingPrimePlaylistTrack.isLiked();
        boolean zAreEqual2 = Intrinsics.areEqual(string8, "VIDEO");
        Integer numValueOf = Integer.valueOf(i);
        Intrinsics.checkNotNullExpressionValue(title, "title");
        Boolean boolValueOf = Boolean.valueOf(zAreEqual);
        Boolean bool = Boolean.TRUE;
        return new VisualRowItemModel(numValueOf, title, string4, str, str2, str3, boolValueOf, bool, badgeText, badgeText2, Boolean.FALSE, null, null, artworkFrameModel, null, null, string2, bool, Boolean.valueOf(zAreEqual2), null, null, null, false, "", 2, trackMetadata, zIsInLibrary, false, z, null, false, zIsLiked, 1752750080, null);
    }
}
