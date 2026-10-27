package com.amazon.mp3.detailpages.playlist;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.catalog.fragment.provider.LibraryContentEnabilityProvider;
import com.amazon.mp3.cloudqueue.FreeTierPlaybackUtil;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifier;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifierType;
import com.amazon.mp3.detailpages.album.click.CollectionTrackClickListener;
import com.amazon.mp3.detailpages.playlist.PlaylistTrackClickListener$onClick$addToPlaylistListener$2;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.haptics.HapticsUtil;
import com.amazon.mp3.library.activity.AllAccessPlaylistLimitReachedActivity;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.service.sync.PlaylistSyncHelper;
import com.amazon.mp3.library.util.AddToPlaylistByAsinTask;
import com.amazon.mp3.library.util.AddToPlaylistManager;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.prime.upsell.nightwing.UpsellManager;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageEntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageEntityType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.skyfire.platform.Log;
import java.util.HashSet;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: PlaylistTrackClickListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u0018\u0000 +2\u00020\u0001:\u0001+B´\u0001\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012S\u0010\u0010\u001aO\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\u0016¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00180\u0011j\u0002`\u0019\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u000f\u0012\u0006\u0010\u001b\u001a\u00020\u001c\u0012\u0006\u0010\u001d\u001a\u00020\u001c¢\u0006\u0002\u0010\u001eJ\b\u0010'\u001a\u00020\u0018H\u0002J\u0012\u0010(\u001a\u00020\u00182\b\u0010)\u001a\u0004\u0018\u00010*H\u0016R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00040\"8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b#\u0010$R\u0010\u0010\r\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006,²\u0006\n\u0010-\u001a\u00020.X\u008a\u0084\u0002"}, d2 = {"Lcom/amazon/mp3/detailpages/playlist/PlaylistTrackClickListener;", "Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListener;", "suggestionsAddedObserver", "Landroidx/lifecycle/Observer;", "", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "collectionInfoFetcher", "Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListener$CollectionInformationFetcher;", "playbackPageType", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", ParserUtil.TRACK_ASIN_QUERY_PARAM_NAME, "trackUri", "Landroid/net/Uri;", "onClickCallback", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "entityAsin", ContextMappingConstants.ENTITY_ID, "", "entityPos", "", "Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListenerOnClickCallback;", "playlistUri", "isCatalog", "", "isTrackSuggestion", "(Landroidx/lifecycle/Observer;Landroidx/fragment/app/FragmentActivity;Landroidx/lifecycle/LifecycleOwner;Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListener$CollectionInformationFetcher;Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;Ljava/lang/String;Landroid/net/Uri;Lkotlin/jvm/functions/Function3;Landroid/net/Uri;ZZ)V", "catalogPlaylist", "Lcom/amazon/mp3/playlist/CatalogPlaylist;", "suggestionAsin", "Landroidx/lifecycle/MutableLiveData;", "getSuggestionAsin", "()Landroidx/lifecycle/MutableLiveData;", "suggestionAsin$delegate", "Lkotlin/Lazy;", "getCatalogPlaylist", "onClick", "p0", "Landroid/view/View;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease", "addToPlaylistListener", "Lcom/amazon/mp3/library/util/AddToPlaylistManager$AddToPlaylistListener;"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistTrackClickListener extends CollectionTrackClickListener {
    private static final String TAG = PlaylistTrackClickListener.class.getSimpleName();
    private CatalogPlaylist catalogPlaylist;
    private final boolean isCatalog;
    private final boolean isTrackSuggestion;
    private final Uri playlistUri;

    /* JADX INFO: renamed from: suggestionAsin$delegate, reason: from kotlin metadata */
    private final Lazy suggestionAsin;
    private final String trackAsin;
    private final Uri trackUri;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlaylistTrackClickListener(Observer<String> suggestionsAddedObserver, FragmentActivity activity, LifecycleOwner lifecycleOwner, CollectionTrackClickListener.CollectionInformationFetcher collectionInfoFetcher, PlaybackPageType playbackPageType, String str, Uri trackUri, Function3<? super String, ? super String, ? super Integer, Unit> onClickCallback, Uri uri, boolean z, boolean z2) {
        super(activity, collectionInfoFetcher, playbackPageType, trackUri, onClickCallback, null, 32, null);
        Intrinsics.checkNotNullParameter(suggestionsAddedObserver, "suggestionsAddedObserver");
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(collectionInfoFetcher, "collectionInfoFetcher");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        Intrinsics.checkNotNullParameter(trackUri, "trackUri");
        Intrinsics.checkNotNullParameter(onClickCallback, "onClickCallback");
        this.trackAsin = str;
        this.trackUri = trackUri;
        this.playlistUri = uri;
        this.isCatalog = z;
        this.isTrackSuggestion = z2;
        this.suggestionAsin = LazyKt__LazyJVMKt.lazy(new Function0<MutableLiveData<String>>() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistTrackClickListener$suggestionAsin$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MutableLiveData<String> invoke() {
                return new MutableLiveData<>();
            }
        });
        if (z) {
            getCatalogPlaylist();
        }
        getSuggestionAsin().observe(lifecycleOwner, suggestionsAddedObserver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MutableLiveData<String> getSuggestionAsin() {
        return (MutableLiveData) this.suggestionAsin.getValue();
    }

    @Override // com.amazon.mp3.detailpages.album.click.CollectionTrackClickListener, android.view.View.OnClickListener
    public void onClick(View p0) {
        String asin;
        final boolean z;
        HapticsUtil.INSTANCE.hapticFeedbackOnPlayback();
        CatalogPlaylist catalogPlaylist = this.catalogPlaylist;
        final boolean zIsGoldenPlaylist = catalogPlaylist == null ? false : catalogPlaylist.isGoldenPlaylist();
        CatalogPlaylist catalogPlaylist2 = this.catalogPlaylist;
        if (catalogPlaylist2 == null || (asin = catalogPlaylist2.getAsin()) == null) {
            z = false;
        } else {
            z = AmazonApplication.getCapabilities().isOnDemandPlaylistsEnabled() && zIsGoldenPlaylist && GetOnDemandPlaylistsService.INSTANCE.get().isOnDemandPlaylist(asin);
        }
        if (this.isCatalog && UserSubscriptionUtil.isNightwingOnly() && !Feature.should_migrate_free_to_cq_for_non_station.isEnabled()) {
            final FragmentActivity activity = getActivity();
            final LibraryContentEnabilityProvider libraryContentEnabilityProvider = new LibraryContentEnabilityProvider(getActivity(), getActivity());
            getCollectionInfoFetcher().getTrack(getUri()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistTrackClickListener$$ExternalSyntheticLambda0
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    PlaylistTrackClickListener.m858onClick$lambda2(libraryContentEnabilityProvider, zIsGoldenPlaylist, z, activity, this, (MusicTrack) obj);
                }
            }, new Action1() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistTrackClickListener$$ExternalSyntheticLambda1
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    PlaylistTrackClickListener.m859onClick$lambda3(this.f$0, (Throwable) obj);
                }
            });
        } else {
            if (this.isTrackSuggestion) {
                final Context applicationContext = getActivity().getApplicationContext();
                Lazy lazy = LazyKt__LazyJVMKt.lazy(new Function0<PlaylistTrackClickListener$onClick$addToPlaylistListener$2.AnonymousClass1>() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistTrackClickListener$onClick$addToPlaylistListener$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX INFO: renamed from: com.amazon.mp3.detailpages.playlist.PlaylistTrackClickListener$onClick$addToPlaylistListener$2$1, reason: invalid class name */
                    /* JADX INFO: compiled from: PlaylistTrackClickListener.kt */
                    @Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016¨\u0006\u0007"}, d2 = {"com/amazon/mp3/detailpages/playlist/PlaylistTrackClickListener$onClick$addToPlaylistListener$2$1", "Lcom/amazon/mp3/library/util/AddToPlaylistManager$AddToPlaylistListener;", "onError", "", "errorCode", "Lcom/amazon/mp3/library/util/AddToPlaylistManager$ErrorReason;", "onSuccess", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
                    public static final class AnonymousClass1 implements AddToPlaylistManager.AddToPlaylistListener {
                        final /* synthetic */ Context $context;
                        final /* synthetic */ PlaylistTrackClickListener this$0;

                        /* JADX INFO: renamed from: com.amazon.mp3.detailpages.playlist.PlaylistTrackClickListener$onClick$addToPlaylistListener$2$1$WhenMappings */
                        /* JADX INFO: compiled from: PlaylistTrackClickListener.kt */
                        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                        public /* synthetic */ class WhenMappings {
                            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                            static {
                                int[] iArr = new int[AddToPlaylistManager.ErrorReason.values().length];
                                iArr[AddToPlaylistManager.ErrorReason.AAUP_RESTRICTION_LIMIT.ordinal()] = 1;
                                iArr[AddToPlaylistManager.ErrorReason.AAUP_PLAYLIST_TRACK_LIMIT.ordinal()] = 2;
                                $EnumSwitchMapping$0 = iArr;
                            }
                        }

                        AnonymousClass1(PlaylistTrackClickListener playlistTrackClickListener, Context context) {
                            this.this$0 = playlistTrackClickListener;
                            this.$context = context;
                        }

                        @Override // com.amazon.mp3.library.util.AddToPlaylistManager.AddToPlaylistListener
                        public void onSuccess() {
                            this.this$0.getSuggestionAsin().setValue(this.this$0.trackAsin);
                            final HashSet hashSet = new HashSet();
                            final Context context = this.$context;
                            final PlaylistTrackClickListener playlistTrackClickListener = this.this$0;
                            ThreadUtils.postInBackground(
                            /*  JADX ERROR: Method code generation error
                                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x001d: INVOKE 
                                  (wrap:java.lang.Runnable:0x001a: CONSTRUCTOR 
                                  (r0v2 'hashSet' java.util.HashSet A[DONT_INLINE])
                                  (r1v2 'context' android.content.Context A[DONT_INLINE])
                                  (r2v0 'playlistTrackClickListener' com.amazon.mp3.detailpages.playlist.PlaylistTrackClickListener A[DONT_INLINE])
                                 A[MD:(java.util.HashSet, android.content.Context, com.amazon.mp3.detailpages.playlist.PlaylistTrackClickListener):void (m), WRAPPED] call: com.amazon.mp3.detailpages.playlist.PlaylistTrackClickListener$onClick$addToPlaylistListener$2$1$$ExternalSyntheticLambda0.<init>(java.util.HashSet, android.content.Context, com.amazon.mp3.detailpages.playlist.PlaylistTrackClickListener):void type: CONSTRUCTOR)
                                 STATIC call: com.amazon.music.media.playback.util.ThreadUtils.postInBackground(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (m)] in method: com.amazon.mp3.detailpages.playlist.PlaylistTrackClickListener$onClick$addToPlaylistListener$2.1.onSuccess():void, file: classes3.dex
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:305)
                                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:284)
                                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:412)
                                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:337)
                                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:303)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
                                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
                                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.amazon.mp3.detailpages.playlist.PlaylistTrackClickListener$onClick$addToPlaylistListener$2$1$$ExternalSyntheticLambda0, state: NOT_LOADED
                                	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:306)
                                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:807)
                                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1143)
                                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:910)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                                	... 15 more
                                */
                            /*
                                this = this;
                                com.amazon.mp3.detailpages.playlist.PlaylistTrackClickListener r0 = r4.this$0
                                androidx.lifecycle.MutableLiveData r0 = com.amazon.mp3.detailpages.playlist.PlaylistTrackClickListener.access$getSuggestionAsin(r0)
                                com.amazon.mp3.detailpages.playlist.PlaylistTrackClickListener r1 = r4.this$0
                                java.lang.String r1 = com.amazon.mp3.detailpages.playlist.PlaylistTrackClickListener.access$getTrackAsin$p(r1)
                                r0.setValue(r1)
                                java.util.HashSet r0 = new java.util.HashSet
                                r0.<init>()
                                android.content.Context r1 = r4.$context
                                com.amazon.mp3.detailpages.playlist.PlaylistTrackClickListener r2 = r4.this$0
                                com.amazon.mp3.detailpages.playlist.PlaylistTrackClickListener$onClick$addToPlaylistListener$2$1$$ExternalSyntheticLambda0 r3 = new com.amazon.mp3.detailpages.playlist.PlaylistTrackClickListener$onClick$addToPlaylistListener$2$1$$ExternalSyntheticLambda0
                                r3.<init>(r0, r1, r2)
                                com.amazon.music.media.playback.util.ThreadUtils.postInBackground(r3)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.detailpages.playlist.PlaylistTrackClickListener$onClick$addToPlaylistListener$2.AnonymousClass1.onSuccess():void");
                        }

                        /* JADX INFO: Access modifiers changed from: private */
                        /* JADX INFO: renamed from: onSuccess$lambda-0, reason: not valid java name */
                        public static final void m862onSuccess$lambda0(HashSet playlistIdSet, Context context, PlaylistTrackClickListener this$0) throws ServiceException, AbstractHttpClient.HttpClientException {
                            Intrinsics.checkNotNullParameter(playlistIdSet, "$playlistIdSet");
                            Intrinsics.checkNotNullParameter(this$0, "this$0");
                            playlistIdSet.add(PlaylistUtil.getPlaylistLuid(context, this$0.playlistUri).toString());
                            PlaylistSyncHelper.syncUpdatedPlaylist(context, (Set<String>) playlistIdSet, true);
                        }

                        @Override // com.amazon.mp3.library.util.AddToPlaylistManager.AddToPlaylistListener
                        public void onError(AddToPlaylistManager.ErrorReason errorCode) {
                            Intrinsics.checkNotNullParameter(errorCode, "errorCode");
                            int i = WhenMappings.$EnumSwitchMapping$0[errorCode.ordinal()];
                            if (i == 1 || i == 2) {
                                this.this$0.getActivity().startActivity(AllAccessPlaylistLimitReachedActivity.getStartIntent(errorCode.toString()));
                            }
                        }
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final AnonymousClass1 invoke() {
                        return new AnonymousClass1(this.this$0, applicationContext);
                    }
                });
                ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistTrackClickListener$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        PlaylistTrackClickListener.m861onClick$lambda5(applicationContext, this);
                    }
                });
                if (ConnectivityUtil.checkConnectivityAndShowDialog(getActivity())) {
                    new AddToPlaylistByAsinTask(applicationContext, this.playlistUri, this.trackUri, m860onClick$lambda4(lazy)).execute(new Void[0]);
                    return;
                }
                return;
            }
            super.onClick(p0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onClick$lambda-2, reason: not valid java name */
    public static final void m858onClick$lambda2(LibraryContentEnabilityProvider contentEnabilityProvider, boolean z, boolean z2, FragmentActivity context, PlaylistTrackClickListener this$0, MusicTrack musicTrack) {
        Intrinsics.checkNotNullParameter(contentEnabilityProvider, "$contentEnabilityProvider");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (musicTrack != null) {
            this$0.invokeOnClickCallback(musicTrack);
        }
        if (musicTrack != null && musicTrack.isExplicit() && AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled()) {
            contentEnabilityProvider.handleContentAccessUnavailable(musicTrack);
            return;
        }
        if (!z) {
            UpsellManager.getInstance().raiseNonBlockingUpsell(context, UpsellUtil.getUpsellInformation(musicTrack, context, UpsellReason.PLAY, UpsellSourceEntity.PLAYLIST_DETAIL), MediaCollectionType.AMF_STATION_SEED);
            return;
        }
        if (z2) {
            CatalogPlaylist catalogPlaylist = this$0.catalogPlaylist;
            FreeTierPlaybackUtil.startPlaylistOnDemand(context, new PlayableEntityIdentifier(catalogPlaylist == null ? null : catalogPlaylist.getAsin(), PlayableEntityIdentifierType.ASIN), true, true, true, PlaybackPageType.PLAYLIST_DETAIL, MediaCollectionType.GOLDEN_PLAYLIST, musicTrack != null ? musicTrack.getAsin() : null);
        } else {
            CatalogPlaylist catalogPlaylist2 = this$0.catalogPlaylist;
            FreeTierPlaybackUtil.playCloudStation(context, new PlayableEntityIdentifier(catalogPlaylist2 != null ? catalogPlaylist2.getAsin() : null, PlayableEntityIdentifierType.ASIN), true, true, true, PlaybackPageType.PLAYLIST_DETAIL, MediaCollectionType.GOLDEN_PLAYLIST);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onClick$lambda-3, reason: not valid java name */
    public static final void m859onClick$lambda3(PlaylistTrackClickListener this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log log = Log.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        Log.error$default(log, TAG2, "Error happened when fetching MusicTrack for the track whose content uri is " + this$0.getUri() + ", message: " + ((Object) th.getMessage()), null, 4, null);
    }

    /* JADX INFO: renamed from: onClick$lambda-4, reason: not valid java name */
    private static final AddToPlaylistManager.AddToPlaylistListener m860onClick$lambda4(Lazy<PlaylistTrackClickListener$onClick$addToPlaylistListener$2.AnonymousClass1> lazy) {
        return lazy.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onClick$lambda-5, reason: not valid java name */
    public static final void m861onClick$lambda5(Context context, PlaylistTrackClickListener this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.ADD_TO_PLAYLIST_DP_RECS).withInteractionType(InteractionType.TAP).withPageType(PageType.DETAIL_PLAYLIST).withPageEntityId(PlaylistUtil.getPlaylistLuid(context, this$0.playlistUri)).withPageEntityIdType(PageEntityIdType.PLAYLIST_ID).withPageEntityType(PageEntityType.USER_PLAYLIST).withEntityId(this$0.trackAsin).withEntityIdType(EntityIdType.ASIN).withEntityType(EntityType.TRACK).withEventTime(System.currentTimeMillis()).build());
    }

    private final void getCatalogPlaylist() {
        Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistTrackClickListener$$ExternalSyntheticLambda3
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlaylistTrackClickListener.m856getCatalogPlaylist$lambda8(this.f$0, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistTrackClickListener$$ExternalSyntheticLambda4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlaylistTrackClickListener.m857getCatalogPlaylist$lambda9(this.f$0, (CatalogPlaylist) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistTrackClickListener$$ExternalSyntheticLambda5
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlaylistTrackClickListener.m855getCatalogPlaylist$lambda10(this.f$0, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getCatalogPlaylist$lambda-8, reason: not valid java name */
    public static final void m856getCatalogPlaylist$lambda8(PlaylistTrackClickListener this$0, Subscriber subscriber) {
        Unit unit;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CatalogPlaylist catalogPlaylist = AmazonApplication.getLibraryItemFactory().getCatalogPlaylist(this$0.playlistUri);
        if (catalogPlaylist == null) {
            unit = null;
        } else {
            subscriber.onNext(catalogPlaylist);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            subscriber.onError(new IllegalStateException(""));
        }
        subscriber.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getCatalogPlaylist$lambda-9, reason: not valid java name */
    public static final void m857getCatalogPlaylist$lambda9(PlaylistTrackClickListener this$0, CatalogPlaylist catalogPlaylist) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.catalogPlaylist = catalogPlaylist;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getCatalogPlaylist$lambda-10, reason: not valid java name */
    public static final void m855getCatalogPlaylist$lambda10(PlaylistTrackClickListener this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log log = Log.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        Log.error$default(log, TAG2, "Error happened when fetching CatalogPlaylist for the playlist whose content uri is " + this$0.playlistUri + ", message: " + ((Object) th.getMessage()), null, 4, null);
    }
}
