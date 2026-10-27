package com.amazon.mp3.navigation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.fragment.VideoPlaylistDetailFragment;
import com.amazon.mp3.library.fragment.VideoPlaylistDetailModel;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.playlist.api.MC2PlaylistApi;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.util.CatalogPlaylistConverter;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.coroutines.LookupError;
import com.amazon.music.destination.HomeDestination;
import com.amazon.music.destination.PlaylistDestination;
import com.amazon.music.destination.parser.Action;
import com.amazon.music.router.DestinationHandler;
import com.amazon.music.storeservice.model.PlaylistItem;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
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

/* JADX INFO: compiled from: PlaylistDestinationHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00122\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0012B\u001d\b\u0007\u0012\u0014\b\u0002\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\u0010\u0007J\u0018\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0002H\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/navigation/PlaylistDestinationHandler;", "Lcom/amazon/music/router/DestinationHandler;", "Lcom/amazon/music/destination/PlaylistDestination;", "onError", "Lkotlin/Function1;", "Lcom/amazon/mp3/util/coroutines/LookupError;", "", "(Lkotlin/jvm/functions/Function1;)V", "coroutineExceptionHandler", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "playlistIdentifier", "", "navigateTo", "context", "Landroid/content/Context;", "destination", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistDestinationHandler implements DestinationHandler<PlaylistDestination> {
    private static final String TAG = PlaylistDestinationHandler.class.getSimpleName();
    private final CoroutineExceptionHandler coroutineExceptionHandler;
    private final CoroutineScope coroutineScope;
    private String playlistIdentifier;

    @JvmOverloads
    public PlaylistDestinationHandler() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @JvmOverloads
    public PlaylistDestinationHandler(Function1<? super LookupError, Unit> onError) {
        Intrinsics.checkNotNullParameter(onError, "onError");
        PlaylistDestinationHandler$special$$inlined$CoroutineExceptionHandler$1 playlistDestinationHandler$special$$inlined$CoroutineExceptionHandler$1 = new PlaylistDestinationHandler$special$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.INSTANCE, this, onError);
        this.coroutineExceptionHandler = playlistDestinationHandler$special$$inlined$CoroutineExceptionHandler$1;
        this.coroutineScope = CoroutineScopeKt.plus(GlobalScope.INSTANCE, playlistDestinationHandler$special$$inlined$CoroutineExceptionHandler$1);
        this.playlistIdentifier = "";
    }

    public /* synthetic */ PlaylistDestinationHandler(Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new Function1<LookupError, Unit>() { // from class: com.amazon.mp3.navigation.PlaylistDestinationHandler.1
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

    /* JADX INFO: renamed from: com.amazon.mp3.navigation.PlaylistDestinationHandler$navigateTo$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PlaylistDestinationHandler.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.navigation.PlaylistDestinationHandler$navigateTo$1", f = "PlaylistDestinationHandler.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03741 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ PlaylistDestination $destination;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03741(PlaylistDestination playlistDestination, Context context, Continuation<? super C03741> continuation) {
            super(2, continuation);
            this.$destination = playlistDestination;
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PlaylistDestinationHandler.this.new C03741(this.$destination, this.$context, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03741) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws ServiceException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
            PlaylistItem playlistItemConvertToPlaylistItem;
            Intent intentForContentUri;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            PlaylistDestinationHandler playlistDestinationHandler = PlaylistDestinationHandler.this;
            String collectionAsin = this.$destination.getCollectionAsin();
            Intrinsics.checkNotNullExpressionValue(collectionAsin, "destination.collectionAsin");
            playlistDestinationHandler.playlistIdentifier = collectionAsin;
            if (PrimePlaylistUtil.isAsin(PlaylistDestinationHandler.this.playlistIdentifier)) {
                playlistItemConvertToPlaylistItem = VideoPlaylistDetailModel.INSTANCE.getPlaylistContent(PlaylistDestinationHandler.this.playlistIdentifier);
                Log.info("Playlist destination handler", Intrinsics.stringPlus("Fetched playlist content from MECS: ", playlistItemConvertToPlaylistItem));
            } else {
                CatalogPlaylist playlistByKey = new MC2PlaylistApi().getPlaylistByKey(PlaylistDestinationHandler.this.playlistIdentifier);
                Intrinsics.checkNotNullExpressionValue(playlistByKey, "api.getPlaylistByKey(playlistIdentifier)");
                playlistItemConvertToPlaylistItem = CatalogPlaylistConverter.INSTANCE.convertToPlaylistItem(playlistByKey);
                Log.info("Playlist destination handler", Intrinsics.stringPlus("Fetched playlist content from MUPS: ", playlistItemConvertToPlaylistItem));
            }
            String trackAsin = this.$destination.getTrackAsin();
            Uri contentUri = MediaProvider.PrimePlaylists.getContentUri("cirrus", PlaylistDestinationHandler.this.playlistIdentifier);
            if (playlistItemConvertToPlaylistItem.getContentTypes() != null && playlistItemConvertToPlaylistItem.getContentTypes().contains("VIDEO")) {
                if (ChildUserUtil.INSTANCE.isChildUser(this.$context)) {
                    Intent intent = new HomeDestinationHandler().getIntent(this.$context, new HomeDestination(null, null, null, null));
                    Intrinsics.checkNotNullExpressionValue(intent, "HomeDestinationHandler()…(null, null, null, null))");
                    intent.addFlags(1073741824);
                    intent.putExtra("unavailableDeeplinkForChildUser", true);
                    this.$context.startActivity(intent);
                    return Unit.INSTANCE;
                }
                intentForContentUri = new Intent();
                intentForContentUri.setClass(this.$context, MusicHomeActivity.class);
                intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_CONTENT_URI", contentUri);
                intentForContentUri.putExtra("com.amazon.mp3.fragment.extra", VideoPlaylistDetailFragment.class.getSimpleName());
                intentForContentUri.putExtra("arg_asin", PlaylistDestinationHandler.this.playlistIdentifier);
            } else {
                intentForContentUri = LibraryActivityFactory.getIntentForContentUri(this.$context, contentUri);
                Intrinsics.checkNotNullExpressionValue(intentForContentUri, "getIntentForContentUri(context, playlistUri)");
            }
            Action action = this.$destination.getAction();
            intentForContentUri.addFlags(268435456);
            intentForContentUri.putExtra("com.amazon.mp3.library.fragment.EXTRA_DEEPLINK_ACTION", action);
            intentForContentUri.putExtra("com.amazon.mp3.library.fragment.EXTRA_DEEPLINK_TRACK_ASIN", trackAsin);
            intentForContentUri.putExtra(DeeplinkIntentKey.KEY_REF_MARKER.toString(), this.$destination.getRef());
            intentForContentUri.putExtra(DeeplinkIntentKey.KEY_ASSOCIATE_TAG.toString(), this.$destination.getTag());
            if (this.$destination.getStoryAsin() != null) {
                intentForContentUri.putExtra("PLAYLIST_ASIN", PlaylistDestinationHandler.this.playlistIdentifier);
                intentForContentUri.putExtra("STORY_ASIN", this.$destination.getStoryAsin());
            }
            this.$context.startActivity(intentForContentUri);
            return Unit.INSTANCE;
        }
    }

    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(Context context, PlaylistDestination destination) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(destination, "destination");
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new C03741(destination, context, null), 3, null);
    }
}
