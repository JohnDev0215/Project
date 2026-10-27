package com.amazon.mp3.likes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.library.cache.likes.LikesStateCacheManager;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.library.service.sync.MyLikesPlaylistDBUpdater;
import com.amazon.mp3.likes.LikesServiceProxy;
import com.amazon.mp3.platform.util.MetricsConverter;
import com.amazon.mp3.prime.CTAPrimeCache;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.prime.browse.metadata.CatalogStatusTiers;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.store.html5.map.MAPCookiesManager;
import com.amazon.mp3.util.DbUtil;
import com.amazon.music.connect.IdentityProvider;
import com.amazon.music.connect.firefly.LikesService;
import com.amazon.music.connect.firefly.LikesServiceImpl;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.ratings.LikeState;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.page.api.model.Entity;
import com.amazon.music.platform.metrics.event.FlexWrapper;
import com.amazon.music.platform.metrics.event.MetricsWrapper;
import com.amazon.musicensembleservice.brush.Track;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: compiled from: LikesServiceProxy.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \n2\u00020\u0001:\u0002\n\u000bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u0004\u0018\u00010\tR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/likes/LikesServiceProxy;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "API_KEY", "", "DEFAULT_SERVICE_ENDPOINT", "get", "Lcom/amazon/music/connect/firefly/LikesService;", "Companion", "MyIdentityProvider", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LikesServiceProxy {
    private static boolean mDoesMyLikesExist;
    private static boolean mIsLikesInit;
    private final String API_KEY;
    private final String DEFAULT_SERVICE_ENDPOINT;
    private final Context context;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = LikesServiceProxy.class.getSimpleName();
    private static final LikesStateCacheManager mLikesCache = LikesStateCacheManager.Companion.getInstance$default(LikesStateCacheManager.INSTANCE, null, 1, null);

    public LikesServiceProxy(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.DEFAULT_SERVICE_ENDPOINT = "https://gql.music.amazon.dev/";
        this.API_KEY = "amzn1.application.d5f54ca32dbe412b8adca604f36814f9";
    }

    /* JADX INFO: compiled from: LikesServiceProxy.kt */
    @Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ\n\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0002H\u0016J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016J\n\u0010\u0006\u001a\u0004\u0018\u00010\u0002H\u0016R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\tR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/likes/LikesServiceProxy$MyIdentityProvider;", "Lcom/amazon/music/connect/IdentityProvider;", "", "getToken", "getCustomerId", "getDeviceId", "getDeviceType", "Lcom/amazon/mp3/account/details/AccountDetailUtil;", "accountDetailUtil", "Lcom/amazon/mp3/account/details/AccountDetailUtil;", "Lcom/amazon/mp3/account/credentials/AccountCredentialStorage;", "accountCredentialStorage", "Lcom/amazon/mp3/account/credentials/AccountCredentialStorage;", "<init>", "(Lcom/amazon/mp3/account/details/AccountDetailUtil;Lcom/amazon/mp3/account/credentials/AccountCredentialStorage;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class MyIdentityProvider implements IdentityProvider {
        private final AccountCredentialStorage accountCredentialStorage;
        private final AccountDetailUtil accountDetailUtil;

        public MyIdentityProvider(AccountDetailUtil accountDetailUtil, AccountCredentialStorage accountCredentialStorage) {
            Intrinsics.checkNotNullParameter(accountDetailUtil, "accountDetailUtil");
            Intrinsics.checkNotNullParameter(accountCredentialStorage, "accountCredentialStorage");
            this.accountDetailUtil = accountDetailUtil;
            this.accountCredentialStorage = accountCredentialStorage;
        }

        @Override // com.amazon.music.connect.IdentityProvider
        public String getToken() {
            try {
                return MAPCookiesManager.getCookiesManager().getToken();
            } catch (Exception e) {
                Log.d("LikesServiceProxy", e.toString());
                return null;
            }
        }

        @Override // com.amazon.music.connect.IdentityProvider
        public String getCustomerId() {
            AccountDetailUtil accountDetailUtil = this.accountDetailUtil;
            if (accountDetailUtil == null) {
                return null;
            }
            return accountDetailUtil.getCustomerId();
        }

        @Override // com.amazon.music.connect.IdentityProvider
        public String getDeviceId() {
            AccountCredentialStorage accountCredentialStorage = this.accountCredentialStorage;
            if (accountCredentialStorage == null) {
                return null;
            }
            return accountCredentialStorage.getDeviceId();
        }

        @Override // com.amazon.music.connect.IdentityProvider
        public String getDeviceType() {
            AccountCredentialStorage accountCredentialStorage = this.accountCredentialStorage;
            if (accountCredentialStorage == null) {
                return null;
            }
            return accountCredentialStorage.getDeviceType();
        }
    }

    public final LikesService get() {
        boolean z = AmazonApplication.getCapabilities().isNPSLikesSupported() || AmazonApplication.getCapabilities().isLikesEverywhereEnabled();
        AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(AmazonApplication.getApplication().getApplicationContext());
        AccountDetailUtil accountDetailUtil = AccountDetailUtil.get(AmazonApplication.getApplication().getApplicationContext());
        String str = this.DEFAULT_SERVICE_ENDPOINT;
        Intrinsics.checkNotNullExpressionValue(accountDetailUtil, "accountDetailUtil");
        Intrinsics.checkNotNullExpressionValue(accountCredentialStorage, "accountCredentialStorage");
        MyIdentityProvider myIdentityProvider = new MyIdentityProvider(accountDetailUtil, accountCredentialStorage);
        if (!z) {
            str = "";
        }
        return LikesServiceImpl.INSTANCE.getInstance(myIdentityProvider, str, this.API_KEY);
    }

    /* JADX INFO: compiled from: LikesServiceProxy.kt */
    @Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011J \u0010\u0013\u001a\u00020\u000f2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0011J \u0010\u0018\u001a\u00020\u000f2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00190\u0011J\"\u0010\u001a\u001a\u00020\u000f2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u0011J(\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00112\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0011J\"\u0010 \u001a\u00020\u000f2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0011J\u0010\u0010!\u001a\u00020\u000f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015J\u0010\u0010\"\u001a\u00020\n2\b\u0010#\u001a\u0004\u0018\u00010$J\"\u0010%\u001a\u00020\n2\u0006\u0010#\u001a\u00020$2\u0006\u0010&\u001a\u00020'2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015J\u001e\u0010(\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010#\u001a\u00020$2\u0006\u0010)\u001a\u00020\u001eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \b*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/amazon/mp3/likes/LikesServiceProxy$Companion;", "", "()V", "LIKES_FAILURE_EVENT", "", "MAX_LIKES_PER_REQUEST", "", "TAG", "kotlin.jvm.PlatformType", "mDoesMyLikesExist", "", "mIsLikesInit", "mLikesCache", "Lcom/amazon/mp3/library/cache/likes/LikesStateCacheManager;", "addTrackContentValuesToCache", "", "contentValues", "", "Landroid/content/ContentValues;", "cacheTracksFromBrushV3Entities", "context", "Landroid/content/Context;", "entities", "Lcom/amazon/music/page/api/model/Entity;", "cacheTracksFromMuseBrushEntities", "Lcom/amazon/musicensembleservice/brush/Entity;", "getLikesStateForMusicTrack", "tracks", "Lcom/amazon/mp3/library/item/MusicTrack;", "getLikesStateForTrackAsins", "Lcom/amazon/music/media/playback/ratings/LikeState;", "asinList", "getLikesStateForTrackAsinsAndCache", "initLikesCache", "shouldShowLikesFeature", "mediaItem", "Lcom/amazon/music/media/playback/MediaItem;", "toggleLikeStatus", "playbackController", "Lcom/amazon/music/media/playback/PlaybackController;", "updateMyLikesDB", "likeState", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean shouldShowLikesFeature(MediaItem mediaItem) {
            return mediaItem != null && mediaItem.getCanLike() && AmazonApplication.getCapabilities().isNPSLikesSupported() && MediaItem.getMediaItemIdString(mediaItem, MediaItemId.Type.ASIN, null) != null && MediaItem.getMediaItemIdString(mediaItem, MediaItemId.Type.PID, null) == null;
        }

        public static /* synthetic */ List getLikesStateForTrackAsins$default(Companion companion, Context context, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                context = null;
            }
            return companion.getLikesStateForTrackAsins(context, list);
        }

        public final List<LikeState> getLikesStateForTrackAsins(Context context, List<String> asinList) {
            ArrayList arrayList;
            getLikesStateForTrackAsinsAndCache(context, asinList);
            if (asinList != null) {
                synchronized (this) {
                    arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(asinList, 10));
                    for (String str : asinList) {
                        LikesStateCacheManager likesStateCacheManager = LikesServiceProxy.mLikesCache;
                        Intrinsics.checkNotNull(likesStateCacheManager);
                        Boolean bool = likesStateCacheManager.get(str);
                        arrayList.add(bool == null ? false : bool.booleanValue() ? LikeState.LIKE : LikeState.NEUTRAL);
                    }
                }
                return arrayList;
            }
            return CollectionsKt__CollectionsKt.emptyList();
        }

        public final void getLikesStateForTrackAsinsAndCache(Context context, List<String> asinList) {
            ArrayList arrayList;
            List<LikeState> likeStates;
            initLikesCache(context);
            if (context == null) {
                context = AmazonApplication.getApplication().getApplicationContext();
            }
            Intrinsics.checkNotNullExpressionValue(context, "context ?: AmazonApplica…).getApplicationContext()");
            LikesService likesService = new LikesServiceProxy(context).get();
            if (asinList == null) {
                arrayList = null;
            } else {
                arrayList = new ArrayList();
                for (Object obj : asinList) {
                    String str = (String) obj;
                    LikesStateCacheManager likesStateCacheManager = LikesServiceProxy.mLikesCache;
                    if (likesStateCacheManager == null ? false : Intrinsics.areEqual(likesStateCacheManager.contains(str), Boolean.FALSE)) {
                        arrayList.add(obj);
                    }
                }
            }
            List<List<String>> listChunked = arrayList == null ? null : CollectionsKt___CollectionsKt.chunked(arrayList, 20);
            ArrayList arrayList2 = new ArrayList();
            if (LikesServiceProxy.mDoesMyLikesExist) {
                return;
            }
            HashMap map = new HashMap();
            if (listChunked != null) {
                ArrayList<List> arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listChunked, 10));
                for (List<String> list : listChunked) {
                    if (likesService == null) {
                        likeStates = null;
                    } else {
                        try {
                            likeStates = likesService.getLikeStates(list);
                        } catch (Exception e) {
                            com.amazon.mp3.util.Log.error(LikesServiceProxy.TAG, Intrinsics.stringPlus("LikesService returns exception ", e));
                            FlexWrapper.Builder builderWithStr1 = new FlexWrapper.Builder().withName("likesCallFailure").withStr1(e.getMessage());
                            Throwable cause = e.getCause();
                            MTSEvent mTSEventConvertToEvent = MetricsConverter.INSTANCE.convertToEvent((MetricsWrapper) builderWithStr1.withStr2(cause != null ? cause.getMessage() : null).build());
                            if (mTSEventConvertToEvent == null) {
                                return;
                            }
                            MetricsHolder.getManager().handleEvent(mTSEventConvertToEvent);
                            return;
                        }
                    }
                    arrayList3.add(likeStates);
                }
                for (List list2 : arrayList3) {
                    if (list2 != null) {
                        arrayList2.addAll(list2);
                    }
                }
            }
            if (arrayList != null) {
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    int i2 = i + 1;
                    map.put(arrayList.get(i), Boolean.valueOf(arrayList2.get(i) == LikeState.LIKE));
                    i = i2;
                }
            }
            synchronized (this) {
                LikesStateCacheManager likesStateCacheManager2 = LikesServiceProxy.mLikesCache;
                if (likesStateCacheManager2 != null) {
                    likesStateCacheManager2.addMap(map);
                    Unit unit = Unit.INSTANCE;
                }
            }
        }

        public static /* synthetic */ void cacheTracksFromMuseBrushEntities$default(Companion companion, Context context, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                context = null;
            }
            companion.cacheTracksFromMuseBrushEntities(context, list);
        }

        public static /* synthetic */ void cacheTracksFromBrushV3Entities$default(Companion companion, Context context, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                context = null;
            }
            companion.cacheTracksFromBrushV3Entities(context, list);
        }

        public final void initLikesCache(Context context) {
            if (LikesServiceProxy.mIsLikesInit) {
                return;
            }
            Cursor cursorRawQuery = CirrusDatabase.getWritableDatabase(context).rawQuery("SELECT track_luid FROM Playlist INNER JOIN PlaylistTrack ON Playlist._id = PlaylistTrack.udo_playlist_id WHERE Playlist.canLikeTracks = ?", new String[]{"0"});
            if (cursorRawQuery != null && cursorRawQuery.moveToFirst()) {
                LikesServiceProxy.mDoesMyLikesExist = true;
                HashMap map = new HashMap();
                LikesStateCacheManager likesStateCacheManager = LikesServiceProxy.mLikesCache;
                Boolean boolIsEmpty = likesStateCacheManager == null ? null : likesStateCacheManager.isEmpty();
                Intrinsics.checkNotNull(boolIsEmpty);
                if (boolIsEmpty.booleanValue()) {
                    do {
                        String string = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("track_luid"));
                        if (string != null && PrimePlaylistUtil.isAsin(string)) {
                            map.put(string, Boolean.TRUE);
                        }
                    } while (cursorRawQuery.moveToNext());
                }
                synchronized (this) {
                    LikesServiceProxy.mLikesCache.addMap(map);
                    Unit unit = Unit.INSTANCE;
                }
            }
            DbUtil.closeCursor(cursorRawQuery);
            LikesServiceProxy.mIsLikesInit = true;
        }

        public final void addTrackContentValuesToCache(List<ContentValues> contentValues) {
            Intrinsics.checkNotNullParameter(contentValues, "contentValues");
            HashMap map = new HashMap();
            ArrayList arrayList = new ArrayList();
            for (Object obj : contentValues) {
                if (PrimePlaylistUtil.isAsin(((ContentValues) obj).getAsString("track_luid"))) {
                    arrayList.add(obj);
                }
            }
            ArrayList<String> arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList2.add(((ContentValues) it.next()).getAsString("track_luid"));
            }
            for (String it2 : arrayList2) {
                Intrinsics.checkNotNullExpressionValue(it2, "it");
                map.put(it2, Boolean.TRUE);
            }
            synchronized (this) {
                LikesStateCacheManager likesStateCacheManager = LikesServiceProxy.mLikesCache;
                if (likesStateCacheManager != null) {
                    likesStateCacheManager.clear();
                }
                LikesStateCacheManager likesStateCacheManager2 = LikesServiceProxy.mLikesCache;
                if (likesStateCacheManager2 != null) {
                    likesStateCacheManager2.addMap(map);
                }
                LikesServiceProxy.mDoesMyLikesExist = true;
                Unit unit = Unit.INSTANCE;
            }
        }

        public final void updateMyLikesDB(Context context, MediaItem mediaItem, final LikeState likeState) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(mediaItem, "mediaItem");
            Intrinsics.checkNotNullParameter(likeState, "likeState");
            CTAPrimeCache cTAPrimeCache = new CTAPrimeCache(context);
            String mediaItemIdString = MediaItem.getMediaItemIdString(mediaItem, MediaItemId.Type.ASIN, null);
            String albumAsin = MediaItemHelper.getAlbumAsin(mediaItem);
            final PrimeTrack primeTrack = new PrimeTrack(mediaItem.getName(), mediaItemIdString, cTAPrimeCache.getArtworkUrlForAsin(albumAsin), mediaItem.getArtistName(), MediaItemHelper.getArtistAsin(mediaItem), mediaItem.getAlbumName(), albumAsin, mediaItem.getArtistName(), false, ContentOwnershipStatus.UNKNOWN, new CatalogStatusTiers(false, false, false, false, false, false, false, WorkQueueKt.MASK, null));
            AmazonApplication.getBackgroundHandler().post(new Runnable() { // from class: com.amazon.mp3.likes.LikesServiceProxy$Companion$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    LikesServiceProxy.Companion.m1354updateMyLikesDB$lambda18(primeTrack, likeState);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: updateMyLikesDB$lambda-18, reason: not valid java name */
        public static final void m1354updateMyLikesDB$lambda18(PrimeTrack primeTrack, LikeState likeState) {
            Intrinsics.checkNotNullParameter(primeTrack, "$primeTrack");
            Intrinsics.checkNotNullParameter(likeState, "$likeState");
            MyLikesPlaylistDBUpdater.Companion companion = MyLikesPlaylistDBUpdater.INSTANCE;
            companion.addTrackToDB(primeTrack);
            Context applicationContext = AmazonApplication.getApplication().getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplication().applicationContext");
            companion.updateMyLikesPlaylistInDB(applicationContext, primeTrack, likeState);
        }

        public static /* synthetic */ boolean toggleLikeStatus$default(Companion companion, MediaItem mediaItem, PlaybackController playbackController, Context context, int i, Object obj) {
            if ((i & 4) != 0) {
                context = null;
            }
            return companion.toggleLikeStatus(mediaItem, playbackController, context);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v1 */
        /* JADX WARN: Type inference failed for: r1v2, types: [boolean, int] */
        /* JADX WARN: Type inference failed for: r1v3 */
        public final boolean toggleLikeStatus(MediaItem mediaItem, PlaybackController playbackController, Context context) {
            int currentIndex;
            Intrinsics.checkNotNullParameter(mediaItem, "mediaItem");
            Intrinsics.checkNotNullParameter(playbackController, "playbackController");
            LikeState likeState = mediaItem.getLikeState();
            LikeState likeState2 = LikeState.LIKE;
            LikeState likeState3 = likeState == likeState2 ? LikeState.NEUTRAL : likeState2;
            ?? r1 = likeState3 == likeState2 ? 1 : 0;
            if (playbackController.canRateMediaItems()) {
                try {
                    currentIndex = playbackController.getCurrentIndex();
                } catch (Exception unused) {
                    com.amazon.mp3.util.Log.debug(LikesServiceProxy.TAG, "Sequencer does not support indices.");
                    currentIndex = 0;
                }
                mediaItem.setRating(r1, currentIndex);
            } else {
                mediaItem.setLikeState(likeState3);
            }
            String mediaItemIdString = MediaItem.getMediaItemIdString(mediaItem, MediaItemId.Type.ASIN, null);
            boolean z = likeState3 == LikeState.LIKE;
            if (mediaItemIdString != null) {
                LikesStateCacheManager companion = LikesStateCacheManager.INSTANCE.getInstance(context);
                Intrinsics.checkNotNull(companion);
                companion.add(mediaItemIdString, z);
                Context applicationContext = AmazonApplication.getApplication().getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplication().getApplicationContext()");
                updateMyLikesDB(applicationContext, mediaItem, likeState3);
            }
            return r1;
        }

        public final void cacheTracksFromBrushV3Entities(Context context, List<? extends Entity> entities) {
            Intrinsics.checkNotNullParameter(entities, "entities");
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(entities, 10));
            Iterator<T> it = entities.iterator();
            while (it.hasNext()) {
                arrayList.add(((Entity) it.next()).getTrack().getAsin());
            }
            getLikesStateForTrackAsinsAndCache(context, arrayList);
        }

        public final void cacheTracksFromMuseBrushEntities(Context context, List<? extends com.amazon.musicensembleservice.brush.Entity> entities) {
            Intrinsics.checkNotNullParameter(entities, "entities");
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(entities, 10));
            Iterator<T> it = entities.iterator();
            while (it.hasNext()) {
                arrayList.add(((Track) ((com.amazon.musicensembleservice.brush.Entity) it.next())).getAsin());
            }
            getLikesStateForTrackAsinsAndCache(context, arrayList);
        }

        public final void getLikesStateForMusicTrack(Context context, List<? extends MusicTrack> tracks) {
            if (tracks != null) {
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(tracks, 10));
                Iterator<T> it = tracks.iterator();
                while (it.hasNext()) {
                    arrayList.add(((MusicTrack) it.next()).getAsin());
                }
                getLikesStateForTrackAsinsAndCache(context, arrayList);
                synchronized (this) {
                    for (MusicTrack musicTrack : tracks) {
                        LikesStateCacheManager likesStateCacheManager = LikesServiceProxy.mLikesCache;
                        Intrinsics.checkNotNull(likesStateCacheManager);
                        String asin = musicTrack.getAsin();
                        Intrinsics.checkNotNullExpressionValue(asin, "track.asin");
                        Boolean bool = likesStateCacheManager.get(asin);
                        musicTrack.setIsLiked(bool == null ? false : bool.booleanValue());
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
        }
    }
}
