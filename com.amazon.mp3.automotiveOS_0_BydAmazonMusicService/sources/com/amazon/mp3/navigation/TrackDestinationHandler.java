package com.amazon.mp3.navigation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.NetworkErrorDialogActivity;
import com.amazon.mp3.external.api.uri.QueryAlbumByAsin;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.data.FireFlyServiceUtil;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.prime.activity.PrimeActivityFactory;
import com.amazon.mp3.prime.browse.BrowseFactory;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.coroutines.LookupError;
import com.amazon.music.browse.BrowseException;
import com.amazon.music.browse.TrackLookup;
import com.amazon.music.destination.TrackDestination;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.platform.graphql.generated.TracksIdsQuery;
import com.amazon.music.router.DestinationHandler;
import com.amazon.music.storeservice.model.TrackItem;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: TrackDestinationHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 *2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001*B\u001f\b\u0007\u0012\u0014\b\u0002\u0010'\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u00060%¢\u0006\u0004\b(\u0010)J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0002H\u0002J\u0018\u0010\b\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0002H\u0002J\"\u0010\n\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u00022\u0006\u0010\t\u001a\u00020\u0002H\u0002J\u0018\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0002H\u0002J\u0018\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\fH\u0002J$\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00022\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0010H\u0002J\u0018\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0002H\u0002J\u0018\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0002H\u0016R\u0017\u0010\u0017\u001a\u00020\u00168\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u001c\u001a\u00020\u001b8\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\"\u0010\u0012\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010 \u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$¨\u0006+"}, d2 = {"Lcom/amazon/mp3/navigation/TrackDestinationHandler;", "Lcom/amazon/music/router/DestinationHandler;", "Lcom/amazon/music/destination/TrackDestination;", "Landroid/content/Context;", "context", "destination", "", "navigateToUsingLookup", "navigateToUsingFirefly", "originalDestination", "navigateToAlbumDetails", "handleAlbumUriError", "Landroid/content/Intent;", MAPAccountManager.KEY_INTENT, "startIntent", "trackDestination", "", "albumAsin", ParserUtil.TRACK_ASIN_QUERY_PARAM_NAME, "updateTrackDestinationWithAlbumSubstitution", "openAlbumDetail", "navigateTo", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "coroutineExceptionHandler", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "getCoroutineExceptionHandler", "()Lkotlinx/coroutines/CoroutineExceptionHandler;", "Lkotlinx/coroutines/CoroutineScope;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "getCoroutineScope", "()Lkotlinx/coroutines/CoroutineScope;", "Ljava/lang/String;", "getTrackAsin", "()Ljava/lang/String;", "setTrackAsin", "(Ljava/lang/String;)V", "Lkotlin/Function1;", "Lcom/amazon/mp3/util/coroutines/LookupError;", "onError", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class TrackDestinationHandler implements DestinationHandler<TrackDestination> {
    private static final String TAG = TrackDestinationHandler.class.getSimpleName();
    private final CoroutineExceptionHandler coroutineExceptionHandler;
    private final CoroutineScope coroutineScope;
    private String trackAsin;

    @JvmOverloads
    public TrackDestinationHandler() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @JvmOverloads
    public TrackDestinationHandler(Function1<? super LookupError, Unit> onError) {
        Intrinsics.checkNotNullParameter(onError, "onError");
        TrackDestinationHandler$special$$inlined$CoroutineExceptionHandler$1 trackDestinationHandler$special$$inlined$CoroutineExceptionHandler$1 = new TrackDestinationHandler$special$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.INSTANCE, this, onError);
        this.coroutineExceptionHandler = trackDestinationHandler$special$$inlined$CoroutineExceptionHandler$1;
        this.coroutineScope = CoroutineScopeKt.plus(GlobalScope.INSTANCE, trackDestinationHandler$special$$inlined$CoroutineExceptionHandler$1);
        this.trackAsin = "";
    }

    public /* synthetic */ TrackDestinationHandler(Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new Function1<LookupError, Unit>() { // from class: com.amazon.mp3.navigation.TrackDestinationHandler.1
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LookupError it) {
                Intrinsics.checkNotNullParameter(it, "it");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LookupError lookupError) {
                invoke2(lookupError);
                return Unit.INSTANCE;
            }
        } : function1);
    }

    public final String getTrackAsin() {
        return this.trackAsin;
    }

    public final void setTrackAsin(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.trackAsin = str;
    }

    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(Context context, TrackDestination destination) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (Feature.is_lookup_deprecated.isEnabled()) {
            navigateToUsingFirefly(context, destination);
        } else {
            navigateToUsingLookup(context, destination);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.navigation.TrackDestinationHandler$navigateToUsingLookup$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TrackDestinationHandler.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.navigation.TrackDestinationHandler$navigateToUsingLookup$1", f = "TrackDestinationHandler.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03771 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ TrackDestination $destination;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03771(TrackDestination trackDestination, Context context, Continuation<? super C03771> continuation) {
            super(2, continuation);
            this.$destination = trackDestination;
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return TrackDestinationHandler.this.new C03771(this.$destination, this.$context, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03771) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                TrackDestinationHandler trackDestinationHandler = TrackDestinationHandler.this;
                String trackAsin = this.$destination.getTrackAsin();
                Intrinsics.checkNotNullExpressionValue(trackAsin, "destination.trackAsin");
                trackDestinationHandler.setTrackAsin(trackAsin);
                TrackLookup.Response responseLookupTrack = BrowseFactory.lookupTrack(this.$context, this.$destination.getTrackAsin());
                if (responseLookupTrack != null && !responseLookupTrack.tracks.isEmpty() && responseLookupTrack.tracks.get(0).getAlbum() != null) {
                    TrackItem trackItem = responseLookupTrack.tracks.get(0);
                    TrackDestinationHandler.this.navigateToAlbumDetails(this.$context, TrackDestinationHandler.this.updateTrackDestinationWithAlbumSubstitution(this.$destination, trackItem.getAlbum().getAsin(), trackItem.getAsin()), this.$destination);
                }
            } catch (BrowseException e) {
                Log.error(TrackDestinationHandler.TAG, Intrinsics.stringPlus("Attempt to lookup track asin failed: ", this.$destination.getTrackAsin()), e);
                TrackDestinationHandler.this.handleAlbumUriError(this.$context, this.$destination);
            }
            return Unit.INSTANCE;
        }
    }

    private final void navigateToUsingLookup(Context context, TrackDestination destination) {
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new C03771(destination, context, null), 3, null);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.navigation.TrackDestinationHandler$navigateToUsingFirefly$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TrackDestinationHandler.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.navigation.TrackDestinationHandler$navigateToUsingFirefly$1", f = "TrackDestinationHandler.kt", i = {}, l = {97}, m = "invokeSuspend", n = {}, s = {})
    static final class C03761 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ TrackDestination $destination;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03761(TrackDestination trackDestination, Context context, Continuation<? super C03761> continuation) {
            super(2, continuation);
            this.$destination = trackDestination;
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return TrackDestinationHandler.this.new C03761(this.$destination, this.$context, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03761) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            TracksIdsQuery.Track track;
            TracksIdsQuery.Track track2;
            TracksIdsQuery.Album album;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                TrackDestinationHandler trackDestinationHandler = TrackDestinationHandler.this;
                String trackAsin = this.$destination.getTrackAsin();
                Intrinsics.checkNotNullExpressionValue(trackAsin, "destination.trackAsin");
                trackDestinationHandler.setTrackAsin(trackAsin);
                FireFlyServiceUtil fireFlyServiceUtil = FireFlyServiceUtil.INSTANCE;
                List<String> listListOf = CollectionsKt__CollectionsJVMKt.listOf(TrackDestinationHandler.this.getTrackAsin());
                this.label = 1;
                obj = fireFlyServiceUtil.getTrackAndAlbumAsins(listListOf, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            List list = (List) obj;
            String id = null;
            String id2 = (list == null || (track = (TracksIdsQuery.Track) list.get(0)) == null) ? null : track.getId();
            if (list != null && (track2 = (TracksIdsQuery.Track) list.get(0)) != null && (album = track2.getAlbum()) != null) {
                id = album.getId();
            }
            if (id2 == null || id == null) {
                Log.error(TrackDestinationHandler.TAG, Intrinsics.stringPlus("Attempt to look up track asin on Firefly failed: ", this.$destination.getTrackAsin()));
                TrackDestinationHandler.this.handleAlbumUriError(this.$context, this.$destination);
            } else {
                TrackDestinationHandler.this.navigateToAlbumDetails(this.$context, TrackDestinationHandler.this.updateTrackDestinationWithAlbumSubstitution(this.$destination, id, id2), this.$destination);
            }
            return Unit.INSTANCE;
        }
    }

    private final void navigateToUsingFirefly(Context context, TrackDestination destination) {
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new C03761(destination, context, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void navigateToAlbumDetails(final Context context, TrackDestination destination, final TrackDestination originalDestination) {
        if (destination == null || destination.getCollectionAsin() == null) {
            handleAlbumUriError(context, originalDestination);
            return;
        }
        openAlbumDetail(context, destination);
        PrimeCollectionTask.createPlaybackTask(context, destination.getCollectionAsin(), destination.getTrackAsin(), PlaybackPageType.ALBUM_DETAIL, PrimeCollectionTask.Task.NONE, new QueryAlbumByAsin(context), new PrimeCollectionTask.OnUriReadyCallback() { // from class: com.amazon.mp3.navigation.TrackDestinationHandler$navigateToAlbumDetails$albumUriCallback$1
            @Override // com.amazon.mp3.prime.cta.PrimeCollectionTask.OnUriReadyCallback
            public void onAlbumUriReady(Uri uri) {
                Intrinsics.checkNotNullParameter(uri, "uri");
            }

            @Override // com.amazon.mp3.prime.cta.PrimeCollectionTask.OnUriReadyCallback
            public void onError(Exception e) {
                Intrinsics.checkNotNullParameter(e, "e");
                Log.error(TrackDestinationHandler.TAG, "Failed to get album uri. Showing error dialog.", e);
                this.this$0.handleAlbumUriError(context, originalDestination);
            }
        }).execute();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleAlbumUriError(Context context, TrackDestination destination) {
        destination.setSuccess(false);
        if (!ConnectivityUtil.hasAnyInternetConnection()) {
            startIntent(context, new Intent(context, (Class<?>) NetworkErrorDialogActivity.class));
            return;
        }
        Intent homeIntent = PrimeActivityFactory.getHomeIntent(context);
        Intrinsics.checkNotNullExpressionValue(homeIntent, "getHomeIntent(context)");
        startIntent(context, homeIntent);
        startIntent(context, new Intent(context, (Class<?>) InvalidDestinationErrorDialogActivity.class));
    }

    private final void startIntent(Context context, Intent intent) {
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TrackDestination updateTrackDestinationWithAlbumSubstitution(TrackDestination trackDestination, String albumAsin, String trackAsin) {
        return new TrackDestination(albumAsin, trackAsin, trackDestination.getAction(), trackDestination.getRef(), trackDestination.getTag());
    }

    private final void openAlbumDetail(Context context, TrackDestination destination) {
        Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(context, MediaProvider.Albums.getContentUri("cirrus", 0L));
        if (intentForContentUri != null) {
            if (AmazonApplication.getCapabilities().isBauhausADPEnabled()) {
                intentForContentUri.putExtra("EXTRA_ALBUM_ASIN", destination.getCollectionAsin());
                intentForContentUri.putExtra("com.amazon.mp3.library.fragment.EXTRA_DEEPLINK_ACTION", destination.getAction());
                intentForContentUri.putExtra("com.amazon.mp3.library.fragment.EXTRA_DEEPLINK_TRACK_ASIN", destination.getTrackAsin());
            } else {
                intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_SHOW_HEADER", true);
                intentForContentUri.putExtra("com.amazon.mp3.library.activity.EXTRA_ALBUM_ASIN", destination.getCollectionAsin());
                intentForContentUri.putExtra("com.amazon.mp3.EXTRA_ALWAYS_SHOW_NON_LIB_CONTENT", true);
                intentForContentUri.putExtra("com.amazon.mp3.library.fragment.EXTRA_DEEPLINK_ACTION", destination.getAction());
                intentForContentUri.putExtra("com.amazon.mp3.library.fragment.EXTRA_DEEPLINK_TRACK_ASIN", destination.getTrackAsin());
            }
            intentForContentUri.putExtra(DeeplinkIntentKey.KEY_REF_MARKER.toString(), destination.getRef());
            intentForContentUri.putExtra(DeeplinkIntentKey.KEY_ASSOCIATE_TAG.toString(), destination.getTag());
            intentForContentUri.addFlags(268435456);
            context.startActivity(intentForContentUri);
        }
    }
}
