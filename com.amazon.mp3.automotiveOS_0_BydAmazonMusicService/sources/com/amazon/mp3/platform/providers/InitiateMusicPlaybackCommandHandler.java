package com.amazon.mp3.platform.providers;

import android.net.Uri;
import android.os.AsyncTask;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.auto.provider.GetPrimePlaylistTask;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler;
import com.amazon.mp3.external.api.uri.QueryAlbumByAsin;
import com.amazon.mp3.external.api.uri.QueryPlaylistByAsin;
import com.amazon.mp3.external.api.uri.QueryPlaylistByLuid;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.item.factory.LibraryItemFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.navigation.playback.StationPlaybackHelper;
import com.amazon.mp3.playback.start.StartPlaybackUtil;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.playlist.EntityPlaylistFetchListener;
import com.amazon.mp3.playlist.EntityPlaylistProvider;
import com.amazon.mp3.playlist.PlaylistSeedEntityType;
import com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.PrimePlaylistsTable;
import com.amazon.mp3.prime.browse.BrowseFactory;
import com.amazon.mp3.prime.browse.PrimeArtistPlaybackHandler;
import com.amazon.mp3.prime.browse.metadata.PrimeArtist;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.mp3.providers.LibraryTracksPlaybackHandler;
import com.amazon.mp3.util.Log;
import com.amazon.music.browse.BrowseException;
import com.amazon.music.browse.TrackLookup;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.platform.data.entity.CollectionType;
import com.amazon.music.platform.data.entity.EntityId;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.data.entity.EntityItemType;
import com.amazon.music.platform.data.entity.SingleType;
import com.amazon.music.platform.data.identifiers.Asin;
import com.amazon.music.platform.data.identifiers.LocalId;
import com.amazon.music.platform.data.identifiers.ObjectId;
import com.amazon.music.platform.data.identifiers.PlaylistId;
import com.amazon.music.platform.data.identifiers.QueueId;
import com.amazon.music.platform.data.identifiers.StationKey;
import com.amazon.music.platform.data.identifiers.StationSeedId;
import com.amazon.music.platform.data.identifiers.UserPlaylistId;
import com.amazon.music.platform.playback.commands.PlaybackCommandEvent;
import com.amazon.music.platform.playback.commands.PlaybackCommandEvents;
import com.amazon.music.platform.playback.commands.PlaybackCommandHandler;
import com.amazon.music.platform.playback.data.PlaybackRequest;
import com.amazon.music.platform.playback.queueseeds.EntitySeed;
import com.amazon.music.platform.playback.queueseeds.PlayMode;
import com.amazon.music.platform.playback.queueseeds.PlayableEntitySeed;
import com.amazon.music.platform.playback.queueseeds.QueueSeed;
import com.amazon.music.platform.playback.state.ShuffleMode;
import com.amazon.music.storeservice.model.TrackItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.GlobalScope;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: InitiateMusicPlaybackCommandHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 (2\u00020\u0001:\u0001(B\u0007¢\u0006\u0004\b&\u0010'J&\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00072\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002J\u0010\u0010\u000b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0002J\u001b\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0082@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\rJ \u0010\u0014\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0011H\u0002J\u0010\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0015H\u0016J)\u0010\u001a\u001a\u00020\t2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u00042\u0006\u0010\n\u001a\u00020\tH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001bR\u0017\u0010\u001d\u001a\u00020\u001c8\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0017\u0010\"\u001a\u00020!8\u0006¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006)"}, d2 = {"Lcom/amazon/mp3/platform/providers/InitiateMusicPlaybackCommandHandler;", "", "", "index", "", "Lcom/amazon/mp3/library/item/MusicTrack;", "trackList", "Lrx/Observable;", "fetchTrackAtIndex", "", "firstQueueSeedIdentifier", "getCollectionAsinFromLookup", "getCollectionAsinFromFirefly", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "playlistName", "Landroid/net/Uri;", "playlistUriWithTracksAtTheEnd", "Lcom/amazon/music/platform/playback/data/PlaybackRequest;", "playbackRequest", "", "startPlaybackForUserPlaylist", "Lcom/amazon/music/platform/playback/commands/PlaybackCommandEvent;", NotificationCompat.CATEGORY_EVENT, "handle", "Lcom/amazon/music/platform/playback/queueseeds/QueueSeed;", "queueSeeds", "getCollectionAsin", "(Ljava/util/List;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "coroutineExceptionHandler", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "getCoroutineExceptionHandler", "()Lkotlinx/coroutines/CoroutineExceptionHandler;", "Lkotlinx/coroutines/CoroutineScope;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "getCoroutineScope", "()Lkotlinx/coroutines/CoroutineScope;", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class InitiateMusicPlaybackCommandHandler implements PlaybackCommandHandler {
    private static final String TAG;
    private final CoroutineExceptionHandler coroutineExceptionHandler;
    private final CoroutineScope coroutineScope;

    /* JADX INFO: renamed from: com.amazon.mp3.platform.providers.InitiateMusicPlaybackCommandHandler$getCollectionAsin$1, reason: invalid class name */
    /* JADX INFO: compiled from: InitiateMusicPlaybackCommandHandler.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.platform.providers.InitiateMusicPlaybackCommandHandler", f = "InitiateMusicPlaybackCommandHandler.kt", i = {0}, l = {425}, m = "getCollectionAsin", n = {"firstQueueSeedIdentifier"}, s = {"L$0"})
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
            return InitiateMusicPlaybackCommandHandler.this.getCollectionAsin(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.platform.providers.InitiateMusicPlaybackCommandHandler$getCollectionAsinFromFirefly$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InitiateMusicPlaybackCommandHandler.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.platform.providers.InitiateMusicPlaybackCommandHandler", f = "InitiateMusicPlaybackCommandHandler.kt", i = {}, l = {460}, m = "getCollectionAsinFromFirefly", n = {}, s = {})
    static final class C03811 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C03811(Continuation<? super C03811> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InitiateMusicPlaybackCommandHandler.this.getCollectionAsinFromFirefly(null, this);
        }
    }

    public InitiateMusicPlaybackCommandHandler() {
        InitiateMusicPlaybackCommandHandler$special$$inlined$CoroutineExceptionHandler$1 initiateMusicPlaybackCommandHandler$special$$inlined$CoroutineExceptionHandler$1 = new InitiateMusicPlaybackCommandHandler$special$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.INSTANCE);
        this.coroutineExceptionHandler = initiateMusicPlaybackCommandHandler$special$$inlined$CoroutineExceptionHandler$1;
        this.coroutineScope = CoroutineScopeKt.plus(GlobalScope.INSTANCE, initiateMusicPlaybackCommandHandler$special$$inlined$CoroutineExceptionHandler$1);
    }

    static {
        String simpleName = InitiateMusicPlaybackCommandHandler.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "InitiateMusicPlaybackCom…er::class.java.simpleName");
        TAG = simpleName;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [com.amazon.mp3.providers.LibraryTracksPlaybackHandler] */
    /* JADX WARN: Type inference failed for: r14v0, types: [java.util.List] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.amazon.music.platform.playback.commands.PlaybackCommandHandler
    public void handle(final PlaybackCommandEvent event) {
        String identifierType;
        String id;
        Intrinsics.checkNotNullParameter(event, "event");
        if (event instanceof PlaybackCommandEvents.InitiateCommandEvent) {
            PlaybackCommandEvents.InitiateCommandEvent initiateCommandEvent = (PlaybackCommandEvents.InitiateCommandEvent) event;
            QueueSeed queueSeed = (QueueSeed) CollectionsKt___CollectionsKt.first((List) initiateCommandEvent.getPlaybackRequest().getPlaybackSeed());
            if (queueSeed instanceof EntitySeed) {
                EntitySeed entitySeed = (EntitySeed) queueSeed;
                EntityItemIdentifier entityItemIdentifier = entitySeed.getEntityItemIdentifier();
                if ((entityItemIdentifier == null ? null : entityItemIdentifier.getId()) instanceof UserPlaylistId) {
                    EntityItemIdentifier entityItemIdentifier2 = entitySeed.getEntityItemIdentifier();
                    EntityId id2 = entityItemIdentifier2 == null ? null : entityItemIdentifier2.getId();
                    if (id2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.platform.data.identifiers.UserPlaylistId");
                    }
                    Uri uri = Uri.parse(((UserPlaylistId) id2).getIdentifier());
                    Uri playlistUriWithTracksAtTheEnd = new QueryPlaylistByLuid(AmazonApplication.getApplication()).convertCollectionUriToTracksUri(uri);
                    Playlist playlist = AmazonApplication.getLibraryItemFactory().getPlaylist(uri);
                    if (playlist != null && (id = playlist.getId()) != null) {
                        objEmptyList = Long.valueOf(Long.parseLong(id));
                    }
                    if (objEmptyList != null) {
                        String id3 = playlist.getId();
                        Intrinsics.checkNotNullExpressionValue(id3, "userPlaylist.id");
                        if (Long.parseLong(id3) > Long.MIN_VALUE) {
                            String name = playlist.getName();
                            Intrinsics.checkNotNullExpressionValue(name, "userPlaylist.name");
                            Intrinsics.checkNotNullExpressionValue(playlistUriWithTracksAtTheEnd, "playlistUriWithTracksAtTheEnd");
                            startPlaybackForUserPlaylist(name, playlistUriWithTracksAtTheEnd, initiateCommandEvent.getPlaybackRequest());
                            return;
                        }
                    }
                    CatalogPlaylist catalogPlaylist = AmazonApplication.getLibraryItemFactory().getCatalogPlaylist(uri);
                    Uri playlistUriWithTracksAtTheEnd2 = new QueryPlaylistByAsin(AmazonApplication.getApplication()).convertCollectionUriToTracksUri(uri);
                    String title = catalogPlaylist.getTitle();
                    Intrinsics.checkNotNullExpressionValue(title, "catalogPlaylist.title");
                    Intrinsics.checkNotNullExpressionValue(playlistUriWithTracksAtTheEnd2, "playlistUriWithTracksAtTheEnd");
                    startPlaybackForUserPlaylist(title, playlistUriWithTracksAtTheEnd2, initiateCommandEvent.getPlaybackRequest());
                    return;
                }
                EntityItemIdentifier entityItemIdentifier3 = entitySeed.getEntityItemIdentifier();
                if ((entityItemIdentifier3 == null ? null : entityItemIdentifier3.getId()) instanceof PlaylistId) {
                    if (queueSeed.getPlayMode() == PlayMode.AUGMENTED || queueSeed.getPlayMode() == PlayMode.SIMILAR || queueSeed.getPlayMode() != PlayMode.ON_DEMAND) {
                        return;
                    }
                    BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new C03821(queueSeed, this, event, null), 3, null);
                    return;
                }
                EntityItemIdentifier entityItemIdentifier4 = entitySeed.getEntityItemIdentifier();
                if ((entityItemIdentifier4 == null ? null : entityItemIdentifier4.getId()) instanceof Asin) {
                    EntityItemIdentifier entityItemIdentifier5 = entitySeed.getEntityItemIdentifier();
                    EntityId id4 = entityItemIdentifier5 == null ? null : entityItemIdentifier5.getId();
                    if (id4 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.platform.data.identifiers.Asin");
                    }
                    String identifier = ((Asin) id4).getIdentifier();
                    if (queueSeed.getPlayMode() == PlayMode.AUGMENTED) {
                        EntityItemIdentifier entityItemIdentifier6 = ((EntitySeed) queueSeed).getEntityItemIdentifier();
                        if ((entityItemIdentifier6 != null ? entityItemIdentifier6.getType() : null) instanceof CollectionType.Artist) {
                            new PrimeArtistPlaybackHandler(AmazonApplication.getApplication()).startPlayback(new PrimeArtist(null, identifier, null, 0, 0, null, ContentOwnershipStatus.NOT_IN_LIBRARY, null), PrimeCollectionTask.Task.PLAYBACK_FULLSCREEN);
                            return;
                        }
                        return;
                    }
                    if (queueSeed.getPlayMode() == PlayMode.SIMILAR) {
                        EntitySeed entitySeed2 = (EntitySeed) queueSeed;
                        EntityItemIdentifier entityItemIdentifier7 = entitySeed2.getEntityItemIdentifier();
                        EntityItemType type = entityItemIdentifier7 == null ? null : entityItemIdentifier7.getType();
                        if (Intrinsics.areEqual(type, CollectionType.Playlist.INSTANCE)) {
                            identifierType = "PLAYLIST_SEED";
                        } else if (Intrinsics.areEqual(type, SingleType.Track.INSTANCE)) {
                            identifierType = "TRACK_SEED";
                        } else if (Intrinsics.areEqual(type, CollectionType.Artist.INSTANCE)) {
                            identifierType = "ARTIST_SEED";
                        } else {
                            EntityItemIdentifier entityItemIdentifier8 = entitySeed2.getEntityItemIdentifier();
                            objEmptyList = entityItemIdentifier8 != null ? entityItemIdentifier8.getId() : null;
                            if (objEmptyList == null) {
                                throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.platform.data.identifiers.Asin");
                            }
                            identifierType = ((Asin) objEmptyList).getIdentifierType();
                        }
                        StationUtils.startSFA(AmazonApplication.getApplication(), identifier, identifierType, PlaybackPageType.REACT_NATIVE, ControlSource.getControlSource(initiateCommandEvent.getPlaybackRequest().getInitiatedFrom()), initiateCommandEvent.getPlaybackRequest().getIsUserInitiated());
                        return;
                    }
                    if (queueSeed.getPlayMode() == PlayMode.ON_DEMAND) {
                        EntityItemIdentifier entityItemIdentifier9 = ((EntitySeed) queueSeed).getEntityItemIdentifier();
                        if (!((entityItemIdentifier9 == null ? null : entityItemIdentifier9.getType()) instanceof CollectionType.Playlist)) {
                            BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new AnonymousClass2(identifier, this, event, null), 3, null);
                            return;
                        } else {
                            new GetPrimePlaylistTask(AmazonApplication.getApplication(), new GetPrimePlaylistTask.PrimePlaylistRetrievedListener() { // from class: com.amazon.mp3.platform.providers.InitiateMusicPlaybackCommandHandler$handle$getPrimePlaylistTask$1
                                @Override // com.amazon.mp3.auto.provider.GetPrimePlaylistTask.PrimePlaylistRetrievedListener
                                public void onPrimePlaylistRetrieved(CatalogPlaylist catalogPlaylist2, boolean startCallBack, long requestTime) {
                                    if (catalogPlaylist2 == null) {
                                        return;
                                    }
                                    InitiateMusicPlaybackCommandHandler initiateMusicPlaybackCommandHandler = this.this$0;
                                    String title2 = catalogPlaylist2.getTitle();
                                    Intrinsics.checkNotNullExpressionValue(title2, "catalogPlaylist.title");
                                    Uri contentUri = catalogPlaylist2.getContentUri();
                                    Intrinsics.checkNotNullExpressionValue(contentUri, "catalogPlaylist.contentUri");
                                    initiateMusicPlaybackCommandHandler.startPlaybackForUserPlaylist(title2, contentUri, ((PlaybackCommandEvents.InitiateCommandEvent) event).getPlaybackRequest());
                                }

                                @Override // com.amazon.mp3.auto.provider.GetPrimePlaylistTask.PrimePlaylistRetrievedListener
                                public void onPlaylistRetrievalFailed(Exception e) {
                                    Intrinsics.checkNotNullParameter(e, "e");
                                    Log.error(InitiateMusicPlaybackCommandHandler.TAG, "onPlaylistRetrievalFailed: ", e);
                                }
                            }, new PrimePlaylistDatabaseManager(AmazonApplication.getApplication()), identifier, PrimePlaylistsTable.translateSource("cirrus"), false, true, Clock.now()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                            return;
                        }
                    }
                    return;
                }
                EntityItemIdentifier entityItemIdentifier10 = entitySeed.getEntityItemIdentifier();
                if (!((entityItemIdentifier10 == null ? null : entityItemIdentifier10.getId()) instanceof LocalId)) {
                    EntityItemIdentifier entityItemIdentifier11 = entitySeed.getEntityItemIdentifier();
                    if (!((entityItemIdentifier11 == null ? null : entityItemIdentifier11.getId()) instanceof ObjectId)) {
                        EntityItemIdentifier entityItemIdentifier12 = entitySeed.getEntityItemIdentifier();
                        if ((entityItemIdentifier12 == null ? null : entityItemIdentifier12.getId()) instanceof QueueId) {
                            EntityItemIdentifier entityItemIdentifier13 = entitySeed.getEntityItemIdentifier();
                            objEmptyList = entityItemIdentifier13 != null ? entityItemIdentifier13.getId() : null;
                            if (objEmptyList == null) {
                                throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.platform.data.identifiers.QueueId");
                            }
                            StartPlaybackUtil startPlaybackUtil = StartPlaybackUtil.INSTANCE;
                            AmazonApplication application = AmazonApplication.getApplication();
                            Intrinsics.checkNotNullExpressionValue(application, "getApplication()");
                            String identifier2 = ((QueueId) objEmptyList).getIdentifier();
                            ControlSource controlSource = ControlSource.getControlSource(initiateCommandEvent.getPlaybackRequest().getInitiatedFrom());
                            Intrinsics.checkNotNullExpressionValue(controlSource, "getControlSource(event.p…ackRequest.initiatedFrom)");
                            startPlaybackUtil.startCQPlayback(application, identifier2, controlSource, null, initiateCommandEvent.getPlaybackRequest().getPlaybackOptions().getShowNowPlaying(), initiateCommandEvent.getPlaybackRequest().getIsUserInitiated());
                            return;
                        }
                        EntityItemIdentifier entityItemIdentifier14 = entitySeed.getEntityItemIdentifier();
                        if ((entityItemIdentifier14 == null ? null : entityItemIdentifier14.getId()) instanceof StationKey) {
                            EntityItemIdentifier entityItemIdentifier15 = entitySeed.getEntityItemIdentifier();
                            objEmptyList = entityItemIdentifier15 != null ? entityItemIdentifier15.getId() : null;
                            if (objEmptyList == null) {
                                throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.platform.data.identifiers.StationKey");
                            }
                            new StationPlaybackHelper(AmazonApplication.getApplication()).play(null, ((StationKey) objEmptyList).getIdentifier(), true, ControlSource.getControlSource(initiateCommandEvent.getPlaybackRequest().getInitiatedFrom()), initiateCommandEvent.getPlaybackRequest().getIsUserInitiated());
                            return;
                        }
                        EntityItemIdentifier entityItemIdentifier16 = entitySeed.getEntityItemIdentifier();
                        if ((entityItemIdentifier16 == null ? null : entityItemIdentifier16.getId()) instanceof StationSeedId) {
                            EntityItemIdentifier entityItemIdentifier17 = entitySeed.getEntityItemIdentifier();
                            objEmptyList = entityItemIdentifier17 != null ? entityItemIdentifier17.getId() : null;
                            if (objEmptyList == null) {
                                throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.platform.data.identifiers.StationSeedId");
                            }
                            StationUtils.startSFA(AmazonApplication.getApplication(), ((StationSeedId) objEmptyList).getIdentifier(), "STATION_SEED_ID", PlaybackPageType.REACT_NATIVE, ControlSource.getControlSource(initiateCommandEvent.getPlaybackRequest().getInitiatedFrom()), initiateCommandEvent.getPlaybackRequest().getIsUserInitiated());
                            return;
                        }
                        return;
                    }
                }
                List<QueueSeed> playbackSeed = initiateCommandEvent.getPlaybackRequest().getPlaybackSeed();
                ArrayList arrayList = new ArrayList();
                for (Object obj : playbackSeed) {
                    if (obj instanceof PlayableEntitySeed) {
                        arrayList.add(obj);
                    }
                }
                ArrayList arrayList2 = new ArrayList();
                for (Object obj2 : arrayList) {
                    if (((PlayableEntitySeed) obj2).getId() instanceof LocalId) {
                        arrayList2.add(obj2);
                    }
                }
                ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList2, 10));
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    arrayList3.add(((PlayableEntitySeed) it.next()).getId().getIdentifier());
                }
                LibraryItemFactory.CursorIterator<MusicTrack> tracks = AmazonApplication.getLibraryItemFactory().getTracks("cirrus-local", arrayList3);
                if (tracks != null) {
                    ArrayList arrayList4 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(tracks, 10));
                    Iterator<MusicTrack> it2 = tracks.iterator();
                    while (it2.hasNext()) {
                        arrayList4.add(it2.next());
                    }
                    objEmptyList = CollectionsKt___CollectionsKt.toMutableList((Collection) arrayList4);
                }
                if (objEmptyList == null) {
                    objEmptyList = CollectionsKt__CollectionsKt.emptyList();
                }
                final ?? r14 = objEmptyList;
                ?? r10 = LibraryTracksPlaybackHandler.INSTANCE;
                AmazonApplication application2 = AmazonApplication.getApplication();
                int size = r14.size();
                Uri filterContentUri = MediaProvider.Tracks.getFilterContentUri("cirrus-local", "");
                ControlSource controlSource2 = ControlSource.getControlSource(initiateCommandEvent.getPlaybackRequest().getInitiatedFrom());
                boolean isUserInitiated = initiateCommandEvent.getPlaybackRequest().getIsUserInitiated();
                Intrinsics.checkNotNullExpressionValue(application2, "getApplication()");
                Function1<Integer, Observable<MusicTrack>> function1 = new Function1<Integer, Observable<MusicTrack>>() { // from class: com.amazon.mp3.platform.providers.InitiateMusicPlaybackCommandHandler.handle.3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Observable<MusicTrack> invoke(Integer num) {
                        return invoke(num.intValue());
                    }

                    public final Observable<MusicTrack> invoke(int i) {
                        return InitiateMusicPlaybackCommandHandler.this.fetchTrackAtIndex(i, r14);
                    }
                };
                Intrinsics.checkNotNullExpressionValue(filterContentUri, "getFilterContentUri(Cirr…MediaSource.ID_LOCAL, \"\")");
                Intrinsics.checkNotNullExpressionValue(controlSource2, "getControlSource(event.p…ackRequest.initiatedFrom)");
                r10.initiatePlayback(application2, null, size, r14, function1, 0, false, filterContentUri, null, null, null, controlSource2, isUserInitiated);
            }
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.platform.providers.InitiateMusicPlaybackCommandHandler$handle$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InitiateMusicPlaybackCommandHandler.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.platform.providers.InitiateMusicPlaybackCommandHandler$handle$1", f = "InitiateMusicPlaybackCommandHandler.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03821 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ PlaybackCommandEvent $event;
        final /* synthetic */ QueueSeed $queueSeed;
        int label;
        final /* synthetic */ InitiateMusicPlaybackCommandHandler this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03821(QueueSeed queueSeed, InitiateMusicPlaybackCommandHandler initiateMusicPlaybackCommandHandler, PlaybackCommandEvent playbackCommandEvent, Continuation<? super C03821> continuation) {
            super(2, continuation);
            this.$queueSeed = queueSeed;
            this.this$0 = initiateMusicPlaybackCommandHandler;
            this.$event = playbackCommandEvent;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03821(this.$queueSeed, this.this$0, this.$event, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03821) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                EntityItemIdentifier entityItemIdentifier = ((EntitySeed) this.$queueSeed).getEntityItemIdentifier();
                EntityId id = entityItemIdentifier == null ? null : entityItemIdentifier.getId();
                if (id == null) {
                    throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.platform.data.identifiers.PlaylistId");
                }
                final String identifier = ((PlaylistId) id).getIdentifier();
                EntityPlaylistProvider entityPlaylistProvider = EntityPlaylistProvider.INSTANCE;
                AmazonApplication application = AmazonApplication.getApplication();
                PlaylistSeedEntityType playlistSeedEntityType = PlaylistSeedEntityType.PLAYLIST;
                final InitiateMusicPlaybackCommandHandler initiateMusicPlaybackCommandHandler = this.this$0;
                final PlaybackCommandEvent playbackCommandEvent = this.$event;
                entityPlaylistProvider.startPlaylistFetch(application, identifier, playlistSeedEntityType, new EntityPlaylistFetchListener() { // from class: com.amazon.mp3.platform.providers.InitiateMusicPlaybackCommandHandler.handle.1.1
                    @Override // com.amazon.mp3.playlist.EntityPlaylistFetchListener
                    public void onEntityPlaylistFetchCompleted(CatalogPlaylist playlist) {
                        Intrinsics.checkNotNullParameter(playlist, "playlist");
                        InitiateMusicPlaybackCommandHandler initiateMusicPlaybackCommandHandler2 = initiateMusicPlaybackCommandHandler;
                        String title = playlist.getTitle();
                        Intrinsics.checkNotNullExpressionValue(title, "playlist.title");
                        Uri contentUri = playlist.getContentUri();
                        Intrinsics.checkNotNullExpressionValue(contentUri, "playlist.contentUri");
                        initiateMusicPlaybackCommandHandler2.startPlaybackForUserPlaylist(title, contentUri, ((PlaybackCommandEvents.InitiateCommandEvent) playbackCommandEvent).getPlaybackRequest());
                    }

                    @Override // com.amazon.mp3.playlist.EntityPlaylistFetchListener
                    public void onEntityPlaylistFetchError(Throwable exception) {
                        Intrinsics.checkNotNullParameter(exception, "exception");
                        Log.error(InitiateMusicPlaybackCommandHandler.TAG, "Failed to fetch entity playlist for entity id: " + identifier + '.', exception);
                    }
                });
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.platform.providers.InitiateMusicPlaybackCommandHandler$handle$2, reason: invalid class name */
    /* JADX INFO: compiled from: InitiateMusicPlaybackCommandHandler.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.platform.providers.InitiateMusicPlaybackCommandHandler$handle$2", f = "InitiateMusicPlaybackCommandHandler.kt", i = {0}, l = {328}, m = "invokeSuspend", n = {"entityAsin"}, s = {"L$0"})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ PlaybackCommandEvent $event;
        final /* synthetic */ String $identifier;
        Object L$0;
        int label;
        final /* synthetic */ InitiateMusicPlaybackCommandHandler this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, InitiateMusicPlaybackCommandHandler initiateMusicPlaybackCommandHandler, PlaybackCommandEvent playbackCommandEvent, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$identifier = str;
            this.this$0 = initiateMusicPlaybackCommandHandler;
            this.$event = playbackCommandEvent;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$identifier, this.this$0, this.$event, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            String str;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                String str2 = this.$identifier;
                InitiateMusicPlaybackCommandHandler initiateMusicPlaybackCommandHandler = this.this$0;
                List<QueueSeed> playbackSeed = ((PlaybackCommandEvents.InitiateCommandEvent) this.$event).getPlaybackRequest().getPlaybackSeed();
                this.L$0 = str2;
                this.label = 1;
                Object collectionAsin = initiateMusicPlaybackCommandHandler.getCollectionAsin(playbackSeed, str2, this);
                if (collectionAsin == coroutine_suspended) {
                    return coroutine_suspended;
                }
                str = str2;
                obj = collectionAsin;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                String str3 = (String) this.L$0;
                ResultKt.throwOnFailure(obj);
                str = str3;
            }
            PrimeCollectionTask primeCollectionTaskCreatePlaybackTask = PrimeCollectionTask.createPlaybackTask(AmazonApplication.getApplication(), (String) obj, str, PlaybackPageType.ALBUM_DETAIL, PrimeCollectionTask.Task.PLAYBACK, new QueryAlbumByAsin(AmazonApplication.getApplication()), (PrimeCollectionTask.OnUriReadyCallback) null);
            primeCollectionTaskCreatePlaybackTask.withPlaybackControlSource(ControlSource.getControlSource(((PlaybackCommandEvents.InitiateCommandEvent) this.$event).getPlaybackRequest().getInitiatedFrom()));
            primeCollectionTaskCreatePlaybackTask.withUserInitiatedValue(((PlaybackCommandEvents.InitiateCommandEvent) this.$event).getPlaybackRequest().getIsUserInitiated());
            primeCollectionTaskCreatePlaybackTask.execute();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Observable<MusicTrack> fetchTrackAtIndex(final int index, final List<? extends MusicTrack> trackList) {
        Observable<MusicTrack> observableSubscribeOn = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.platform.providers.InitiateMusicPlaybackCommandHandler$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                InitiateMusicPlaybackCommandHandler.m1374fetchTrackAtIndex$lambda4(trackList, index, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.io());
        Intrinsics.checkNotNullExpressionValue(observableSubscribeOn, "create<MusicTrack> {\n   …hedulers.Schedulers.io())");
        return observableSubscribeOn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchTrackAtIndex$lambda-4, reason: not valid java name */
    public static final void m1374fetchTrackAtIndex$lambda4(List trackList, int i, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(trackList, "$trackList");
        subscriber.onNext(CollectionsKt___CollectionsKt.getOrNull(trackList, i));
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getCollectionAsin(java.util.List<? extends com.amazon.music.platform.playback.queueseeds.QueueSeed> r5, java.lang.String r6, kotlin.coroutines.Continuation<? super java.lang.String> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof com.amazon.mp3.platform.providers.InitiateMusicPlaybackCommandHandler.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r7
            com.amazon.mp3.platform.providers.InitiateMusicPlaybackCommandHandler$getCollectionAsin$1 r0 = (com.amazon.mp3.platform.providers.InitiateMusicPlaybackCommandHandler.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.platform.providers.InitiateMusicPlaybackCommandHandler$getCollectionAsin$1 r0 = new com.amazon.mp3.platform.providers.InitiateMusicPlaybackCommandHandler$getCollectionAsin$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L38
            if (r2 != r3) goto L30
            java.lang.Object r5 = r0.L$0
            r6 = r5
            java.lang.String r6 = (java.lang.String) r6
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: com.amazon.music.browse.BrowseException -> L2e
            goto L82
        L2e:
            r5 = move-exception
            goto L88
        L30:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L38:
            kotlin.ResultKt.throwOnFailure(r7)
            java.util.Iterator r5 = r5.iterator()
        L3f:
            boolean r7 = r5.hasNext()
            if (r7 == 0) goto L6f
            java.lang.Object r7 = r5.next()
            com.amazon.music.platform.playback.queueseeds.QueueSeed r7 = (com.amazon.music.platform.playback.queueseeds.QueueSeed) r7
            boolean r2 = r7 instanceof com.amazon.music.platform.playback.queueseeds.CollectionSeed
            if (r2 == 0) goto L3f
            com.amazon.music.platform.playback.queueseeds.CollectionSeed r7 = (com.amazon.music.platform.playback.queueseeds.CollectionSeed) r7
            com.amazon.music.platform.playback.queueseeds.EntitySingleSeed r7 = r7.getStartSeed()
            r2 = 0
            if (r7 != 0) goto L59
            goto L6b
        L59:
            com.amazon.music.platform.data.entity.EntityItemIdentifier r7 = r7.getEntityItemIdentifier()
            if (r7 != 0) goto L60
            goto L6b
        L60:
            com.amazon.music.platform.data.entity.EntityId r7 = r7.getId()
            if (r7 != 0) goto L67
            goto L6b
        L67:
            java.lang.String r2 = r7.getIdentifier()
        L6b:
            if (r2 != 0) goto L6e
            goto L3f
        L6e:
            return r2
        L6f:
            com.amazon.mp3.featuregate.Feature r5 = com.amazon.mp3.featuregate.Feature.is_lookup_deprecated     // Catch: com.amazon.music.browse.BrowseException -> L2e
            boolean r5 = r5.isEnabled()     // Catch: com.amazon.music.browse.BrowseException -> L2e
            if (r5 == 0) goto L83
            r0.L$0 = r6     // Catch: com.amazon.music.browse.BrowseException -> L2e
            r0.label = r3     // Catch: com.amazon.music.browse.BrowseException -> L2e
            java.lang.Object r7 = r4.getCollectionAsinFromFirefly(r6, r0)     // Catch: com.amazon.music.browse.BrowseException -> L2e
            if (r7 != r1) goto L82
            return r1
        L82:
            return r7
        L83:
            java.lang.String r5 = r4.getCollectionAsinFromLookup(r6)     // Catch: com.amazon.music.browse.BrowseException -> L2e
            return r5
        L88:
            java.lang.String r7 = com.amazon.mp3.platform.providers.InitiateMusicPlaybackCommandHandler.TAG
            java.lang.String r0 = "Attempt to lookup track asin failed: "
            java.lang.String r6 = kotlin.jvm.internal.Intrinsics.stringPlus(r0, r6)
            com.amazon.mp3.util.Log.error(r7, r6, r5)
            java.lang.String r5 = ""
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.platform.providers.InitiateMusicPlaybackCommandHandler.getCollectionAsin(java.util.List, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final String getCollectionAsinFromLookup(String firstQueueSeedIdentifier) throws BrowseException {
        TrackLookup.Response responseLookupTrack = BrowseFactory.lookupTrack(AmazonApplication.getApplication(), firstQueueSeedIdentifier);
        if (responseLookupTrack == null || responseLookupTrack.tracks.isEmpty() || responseLookupTrack.tracks.get(0).getAlbum() == null) {
            return "";
        }
        TrackItem trackItem = responseLookupTrack.tracks.get(0);
        Intrinsics.checkNotNullExpressionValue(trackItem, "response.tracks[0]");
        String asin = trackItem.getAlbum().getAsin();
        Intrinsics.checkNotNullExpressionValue(asin, "track.album.asin");
        return asin;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getCollectionAsinFromFirefly(java.lang.String r5, kotlin.coroutines.Continuation<? super java.lang.String> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.amazon.mp3.platform.providers.InitiateMusicPlaybackCommandHandler.C03811
            if (r0 == 0) goto L13
            r0 = r6
            com.amazon.mp3.platform.providers.InitiateMusicPlaybackCommandHandler$getCollectionAsinFromFirefly$1 r0 = (com.amazon.mp3.platform.providers.InitiateMusicPlaybackCommandHandler.C03811) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.platform.providers.InitiateMusicPlaybackCommandHandler$getCollectionAsinFromFirefly$1 r0 = new com.amazon.mp3.platform.providers.InitiateMusicPlaybackCommandHandler$getCollectionAsinFromFirefly$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.throwOnFailure(r6)
            goto L43
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            kotlin.ResultKt.throwOnFailure(r6)
            com.amazon.mp3.library.data.FireFlyServiceUtil r6 = com.amazon.mp3.library.data.FireFlyServiceUtil.INSTANCE
            java.util.List r5 = kotlin.collections.CollectionsKt.listOf(r5)
            r0.label = r3
            java.lang.Object r6 = r6.getTrackAndAlbumAsins(r5, r0)
            if (r6 != r1) goto L43
            return r1
        L43:
            java.util.List r6 = (java.util.List) r6
            java.lang.String r5 = ""
            if (r6 != 0) goto L4a
            goto L63
        L4a:
            r0 = 0
            java.lang.Object r6 = r6.get(r0)
            com.amazon.music.platform.graphql.generated.TracksIdsQuery$Track r6 = (com.amazon.music.platform.graphql.generated.TracksIdsQuery.Track) r6
            if (r6 != 0) goto L54
            goto L63
        L54:
            com.amazon.music.platform.graphql.generated.TracksIdsQuery$Album r6 = r6.getAlbum()
            if (r6 != 0) goto L5b
            goto L63
        L5b:
            java.lang.String r6 = r6.getId()
            if (r6 != 0) goto L62
            goto L63
        L62:
            r5 = r6
        L63:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.platform.providers.InitiateMusicPlaybackCommandHandler.getCollectionAsinFromFirefly(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startPlaybackForUserPlaylist(String playlistName, Uri playlistUriWithTracksAtTheEnd, PlaybackRequest playbackRequest) {
        AmazonApplication application = AmazonApplication.getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "getApplication()");
        boolean z = playbackRequest.getPlaybackOptions().getShuffleMode() == ShuffleMode.SHUFFLE_ON;
        PlaybackPageType playbackPageType = PlaybackPageType.PLAYLIST_DETAIL;
        ControlSource controlSource = ControlSource.getControlSource(playbackRequest.getInitiatedFrom());
        Intrinsics.checkNotNullExpressionValue(controlSource, "getControlSource(playbackRequest.initiatedFrom)");
        StartPlaybackUtil.startCollectionPlayback(application, (Data.MAX_DATA_BYTES & 2) != 0 ? null : playlistName, playlistUriWithTracksAtTheEnd, null, (Data.MAX_DATA_BYTES & 16) != 0 ? 0 : 0, (Data.MAX_DATA_BYTES & 32) != 0 ? false : z, (Data.MAX_DATA_BYTES & 64) != 0 ? true : true, (Data.MAX_DATA_BYTES & 128) != 0 ? null : null, playbackPageType, (Data.MAX_DATA_BYTES & 512) != 0 ? false : true, controlSource, (Data.MAX_DATA_BYTES & 2048) != 0 ? null : null, (Data.MAX_DATA_BYTES & 4096) != 0 ? false : false, (Data.MAX_DATA_BYTES & 8192) != 0 ? null : null, (Data.MAX_DATA_BYTES & 16384) != 0 ? null : null, (Data.MAX_DATA_BYTES & 32768) != 0 ? false : false, playbackRequest.getIsUserInitiated(), new NoOpCloudQueueClientHandler() { // from class: com.amazon.mp3.platform.providers.InitiateMusicPlaybackCommandHandler.startPlaybackForUserPlaylist.1
            @Override // com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler
            public void handlePlayback() {
            }
        });
    }
}
