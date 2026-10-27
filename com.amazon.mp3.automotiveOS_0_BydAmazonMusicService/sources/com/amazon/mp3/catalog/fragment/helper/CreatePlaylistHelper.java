package com.amazon.mp3.catalog.fragment.helper;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import androidx.core.view.KeyEventDispatcher;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.detailpages.playlist.PlaylistDetailFactory;
import com.amazon.mp3.fragment.navigation.NavigationProvider;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.fragment.CuratePlaylistFragment;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.service.sync.PlaylistUnificationCallback;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.playlist.api.MC2PlaylistApi;
import com.amazon.mp3.recentlyplayed.Recents;
import com.amazon.mp3.util.Clock;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.recents.RecentsException;
import com.amazon.music.recents.model.ActivityType;
import com.amazon.music.subscription.UserSubscription;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: CreatePlaylistHelper.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \u001e2\u00020\u0001:\u0004\u001e\u001f !B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J2\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012J\u001e\u0010\u0013\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u0016J#\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001aJ\u001e\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u0016J\b\u0010\u001d\u001a\u00020\bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\""}, d2 = {"Lcom/amazon/mp3/catalog/fragment/helper/CreatePlaylistHelper;", "", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;)V", "recentActivitySubscription", "Lrx/Subscription;", "createPlaylist", "", "context", "Landroid/content/Context;", "playlistName", "", "isPublicToggleEnabled", "", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "playlistCreationCompletion", "Lcom/amazon/mp3/catalog/fragment/helper/CreatePlaylistHelper$PlaylistCreationListener;", "handlePlaylistCreationCompletion", "playlistId", "playlistUri", "Landroid/net/Uri;", "savePlaylist", "Lcom/amazon/mp3/catalog/fragment/helper/CreatePlaylistHelper$PlaylistCreationResult;", "sourceId", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "showPlaylistCreateExperiment", "playlistAsin", "unsubscribeRecentActivitySubscription", "Companion", "PlaylistCreationListener", "PlaylistCreationResult", "PlaylistInfo", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CreatePlaylistHelper {
    private static final String TAG = CreatePlaylistHelper.class.getSimpleName();
    private final FragmentActivity activity;
    private Subscription recentActivitySubscription;

    /* JADX INFO: compiled from: CreatePlaylistHelper.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/helper/CreatePlaylistHelper$PlaylistCreationListener;", "", "onCompleted", "", "success", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface PlaylistCreationListener {
        void onCompleted(boolean success);
    }

    public CreatePlaylistHelper(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activity = activity;
    }

    public final void showPlaylistCreateExperiment(final String playlistAsin, final String playlistName, final Uri playlistUri) {
        Intrinsics.checkNotNullParameter(playlistAsin, "playlistAsin");
        Intrinsics.checkNotNullParameter(playlistName, "playlistName");
        Intrinsics.checkNotNullParameter(playlistUri, "playlistUri");
        unsubscribeRecentActivitySubscription();
        this.recentActivitySubscription = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.catalog.fragment.helper.CreatePlaylistHelper$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CreatePlaylistHelper.m580showPlaylistCreateExperiment$lambda1((Subscriber) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.catalog.fragment.helper.CreatePlaylistHelper$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CreatePlaylistHelper.m581showPlaylistCreateExperiment$lambda3(playlistAsin, playlistName, this, playlistUri, (Recents.RecentsResponse) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.catalog.fragment.helper.CreatePlaylistHelper$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CreatePlaylistHelper.m582showPlaylistCreateExperiment$lambda4(this.f$0, playlistUri, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showPlaylistCreateExperiment$lambda-1, reason: not valid java name */
    public static final void m580showPlaylistCreateExperiment$lambda1(Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(subscriber, "subscriber");
        try {
            subscriber.onNext(Recents.get(AmazonApplication.getApplication()).withActivityTypes(ActivityType.PLAYED).withRemoveDuplicateTracks(true).withFilterSubscription(true).getRecents());
            subscriber.onCompleted();
        } catch (RecentsException e) {
            Log.error(TAG, "Failed to get recent tracks list", e);
            subscriber.onError(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showPlaylistCreateExperiment$lambda-3, reason: not valid java name */
    public static final void m581showPlaylistCreateExperiment$lambda3(String playlistAsin, String playlistName, CreatePlaylistHelper this$0, Uri playlistUri, Recents.RecentsResponse result) {
        Intrinsics.checkNotNullParameter(playlistAsin, "$playlistAsin");
        Intrinsics.checkNotNullParameter(playlistName, "$playlistName");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(playlistUri, "$playlistUri");
        Intrinsics.checkNotNullParameter(result, "result");
        Intrinsics.checkNotNullExpressionValue(result.getTracks(), "result.tracks");
        if (!r5.isEmpty()) {
            CuratePlaylistFragment curatePlaylistFragmentBuild = CuratePlaylistFragment.create().withId(playlistAsin).withName(playlistName).build();
            Intrinsics.checkNotNullExpressionValue(curatePlaylistFragmentBuild, "create()\n               …                 .build()");
            KeyEventDispatcher.Component component = this$0.activity;
            NavigationProvider navigationProvider = component instanceof NavigationProvider ? (NavigationProvider) component : null;
            if (navigationProvider == null) {
                return;
            }
            navigationProvider.changeScreenFragment(curatePlaylistFragmentBuild, true, false, true);
            return;
        }
        PlaylistUtil.startEditPlaylist(this$0.activity, playlistUri, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showPlaylistCreateExperiment$lambda-4, reason: not valid java name */
    public static final void m582showPlaylistCreateExperiment$lambda4(CreatePlaylistHelper this$0, Uri playlistUri, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(playlistUri, "$playlistUri");
        PlaylistUtil.startEditPlaylist(this$0.activity, playlistUri, true);
    }

    private final void unsubscribeRecentActivitySubscription() {
        Subscription subscription = this.recentActivitySubscription;
        if (subscription == null || subscription.isUnsubscribed()) {
            return;
        }
        subscription.unsubscribe();
    }

    /* JADX INFO: renamed from: com.amazon.mp3.catalog.fragment.helper.CreatePlaylistHelper$savePlaylist$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CreatePlaylistHelper.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "Lcom/amazon/mp3/catalog/fragment/helper/CreatePlaylistHelper$PlaylistCreationResult;", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.catalog.fragment.helper.CreatePlaylistHelper$savePlaylist$2", f = "CreatePlaylistHelper.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03252 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PlaylistCreationResult>, Object> {
        final /* synthetic */ String $playlistName;
        final /* synthetic */ String $sourceId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03252(String str, String str2, Continuation<? super C03252> continuation) {
            super(2, continuation);
            this.$playlistName = str;
            this.$sourceId = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CreatePlaylistHelper.this.new C03252(this.$playlistName, this.$sourceId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super PlaylistCreationResult> continuation) {
            return ((C03252) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            ContentResolver contentResolver = CreatePlaylistHelper.this.activity.getContentResolver();
            Intrinsics.checkNotNullExpressionValue(contentResolver, "activity.contentResolver");
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", this.$playlistName);
            long jCurrentTimeSeconds = Clock.currentTimeSeconds();
            contentValues.put("dateCreated", Boxing.boxLong(jCurrentTimeSeconds));
            contentValues.put("dateLastModified", Boxing.boxLong(jCurrentTimeSeconds));
            Uri uriInsert = contentResolver.insert(MediaProvider.UdoPlaylists.getContentUri(this.$sourceId), contentValues);
            String playlistLuid = MediaProvider.Playlists.getPlaylistLuid(uriInsert);
            if (uriInsert == null) {
                return null;
            }
            Intrinsics.checkNotNullExpressionValue(playlistLuid, "playlistLuid");
            return new PlaylistCreationResult(uriInsert, playlistLuid);
        }
    }

    public final Object savePlaylist(String str, String str2, Continuation<? super PlaylistCreationResult> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C03252(str, str2, null), continuation);
    }

    public static /* synthetic */ void createPlaylist$default(CreatePlaylistHelper createPlaylistHelper, Context context, String str, boolean z, PageType pageType, PlaylistCreationListener playlistCreationListener, int i, Object obj) {
        if ((i & 16) != 0) {
            playlistCreationListener = null;
        }
        createPlaylistHelper.createPlaylist(context, str, z, pageType, playlistCreationListener);
    }

    public final void createPlaylist(final Context context, final String playlistName, final boolean isPublicToggleEnabled, final PageType pageType, final PlaylistCreationListener playlistCreationCompletion) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(playlistName, "playlistName");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Observable.fromCallable(new Callable() { // from class: com.amazon.mp3.catalog.fragment.helper.CreatePlaylistHelper$$ExternalSyntheticLambda3
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return CreatePlaylistHelper.m579createPlaylist$lambda6(context, playlistName, isPublicToggleEnabled);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<PlaylistCreationResult>() { // from class: com.amazon.mp3.catalog.fragment.helper.CreatePlaylistHelper.createPlaylist.2
            @Override // rx.Observer
            public void onCompleted() {
            }

            @Override // rx.Observer
            public void onError(Throwable error) {
                Log.error(CreatePlaylistHelper.TAG, "Error when creating playlist", error);
                PlaylistCreationListener playlistCreationListener = playlistCreationCompletion;
                if (playlistCreationListener == null) {
                    return;
                }
                playlistCreationListener.onCompleted(false);
            }

            @Override // rx.Observer
            public void onNext(PlaylistCreationResult playlistCreationResult) {
                Intrinsics.checkNotNullParameter(playlistCreationResult, "playlistCreationResult");
                MetricsHolder.getManager().handleEvent(UiClickEvent.builder(isPublicToggleEnabled ? ActionType.CREATE_PUBLIC_PLAYLIST : ActionType.CREATE_PRIVATE_PLAYLIST).withInteractionType(InteractionType.TAP).withPageType(pageType).withEntityId(playlistCreationResult.getMPlaylistLuid()).withEntityIdType(EntityIdType.PLAYLIST_ID).withEventTime(System.currentTimeMillis()).build());
                PlaylistCreationListener playlistCreationListener = playlistCreationCompletion;
                if (playlistCreationListener != null) {
                    playlistCreationListener.onCompleted(true);
                }
                this.handlePlaylistCreationCompletion(playlistName, playlistCreationResult.getMPlaylistLuid(), playlistCreationResult.getMPlaylistUri());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: createPlaylist$lambda-6, reason: not valid java name */
    public static final PlaylistCreationResult m579createPlaylist$lambda6(Context context, String playlistName, boolean z) throws ServiceException, AbstractHttpClient.HttpClientException {
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(playlistName, "$playlistName");
        try {
            Uri uriCreatePlaylist = new MC2PlaylistApi().createPlaylist(context, playlistName, false, (PlaylistUnificationCallback) null, z);
            if (uriCreatePlaylist == null) {
                throw new IllegalStateException("uri is null, unable to create playlist");
            }
            String playlistId = MediaProvider.Playlists.getPlaylistLuid(uriCreatePlaylist);
            Intrinsics.checkNotNullExpressionValue(playlistId, "playlistId");
            return new PlaylistCreationResult(uriCreatePlaylist, playlistId);
        } catch (Error e) {
            throw new IllegalStateException(e.toString());
        }
    }

    public final void handlePlaylistCreationCompletion(String playlistName, String playlistId, Uri playlistUri) {
        Intrinsics.checkNotNullParameter(playlistName, "playlistName");
        Intrinsics.checkNotNullParameter(playlistId, "playlistId");
        Intrinsics.checkNotNullParameter(playlistUri, "playlistUri");
        if (AmazonApplication.getCapabilities().isPlaylistSongRecommendationWidgetEnabled()) {
            Fragment fragmentCreateFragment = PlaylistDetailFactory.createFragment(LibraryActivityFactory.getIntentForContentUri(AmazonApplication.getApplication(), playlistUri), true, null);
            KeyEventDispatcher.Component component = this.activity;
            NavigationProvider navigationProvider = component instanceof NavigationProvider ? (NavigationProvider) component : null;
            if (navigationProvider == null) {
                return;
            }
            navigationProvider.changeScreenFragment(fragmentCreateFragment, true, false, true);
            return;
        }
        UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
        boolean zIsFreeTierUserPlaylistEditingEnabled = AmazonApplication.getCapabilities().isFreeTierUserPlaylistEditingEnabled();
        if ((userSubscription.isHawkfireHome() || !userSubscription.hasSubscription()) && !zIsFreeTierUserPlaylistEditingEnabled) {
            PlaylistUtil.startEditPlaylist(this.activity, playlistUri, true);
        } else {
            showPlaylistCreateExperiment(playlistId, playlistName, playlistUri);
        }
    }

    /* JADX INFO: compiled from: CreatePlaylistHelper.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/helper/CreatePlaylistHelper$PlaylistCreationResult;", "", "mPlaylistUri", "Landroid/net/Uri;", "mPlaylistLuid", "", "(Landroid/net/Uri;Ljava/lang/String;)V", "getMPlaylistLuid", "()Ljava/lang/String;", "getMPlaylistUri", "()Landroid/net/Uri;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class PlaylistCreationResult {
        private final String mPlaylistLuid;
        private final Uri mPlaylistUri;

        public PlaylistCreationResult(Uri mPlaylistUri, String mPlaylistLuid) {
            Intrinsics.checkNotNullParameter(mPlaylistUri, "mPlaylistUri");
            Intrinsics.checkNotNullParameter(mPlaylistLuid, "mPlaylistLuid");
            this.mPlaylistUri = mPlaylistUri;
            this.mPlaylistLuid = mPlaylistLuid;
        }

        public final String getMPlaylistLuid() {
            return this.mPlaylistLuid;
        }

        public final Uri getMPlaylistUri() {
            return this.mPlaylistUri;
        }
    }

    /* JADX INFO: compiled from: CreatePlaylistHelper.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B%\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000e\u0010\u000fR\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0004\u001a\u0004\b\b\u0010\u0006R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/helper/CreatePlaylistHelper$PlaylistInfo;", "", "", "mPlaylistId", "Ljava/lang/String;", "getMPlaylistId", "()Ljava/lang/String;", "mPlaylistName", "getMPlaylistName", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "mPageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "getMPageType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class PlaylistInfo {
        private final PageType mPageType;
        private final String mPlaylistId;
        private final String mPlaylistName;

        public PlaylistInfo(String str, String str2, PageType pageType) {
            this.mPlaylistId = str;
            this.mPlaylistName = str2;
            this.mPageType = pageType;
        }

        public final String getMPlaylistId() {
            return this.mPlaylistId;
        }

        public final String getMPlaylistName() {
            return this.mPlaylistName;
        }
    }
}
