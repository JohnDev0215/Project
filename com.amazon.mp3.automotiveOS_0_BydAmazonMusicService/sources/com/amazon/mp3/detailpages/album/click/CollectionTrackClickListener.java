package com.amazon.mp3.detailpages.album.click;

import android.net.Uri;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.amazon.digitalmusicxp.clients.CloudQueueClient;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.basequeue.NoOpCloudQueueClient;
import com.amazon.mp3.catalog.fragment.provider.LibraryContentEnabilityProvider;
import com.amazon.mp3.cloudqueue.CloudQueueClientSingleton;
import com.amazon.mp3.detailpages.album.actions.PlayTrackInCollectionAction;
import com.amazon.mp3.haptics.HapticsUtil;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.playback.harley.LibraryAccessProviderImpl;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.service.metrics.PlaybackMetricInformation;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.metrics.mts.event.types.DirectedPlayStatus;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.platform.playback.augmentation.PlaybackAugmentation;
import io.reactivex.functions.Consumer;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: CollectionTrackClickListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\b\u0016\u0018\u0000 >2\u00020\u0001:\u0003?>@B\u0088\u0001\u0012\u0006\u0010\u001a\u001a\u00020\u0019\u0012\u0006\u0010\u001f\u001a\u00020\u001e\u0012\u0006\u0010$\u001a\u00020#\u0012\u0006\u0010'\u001a\u00020&\u0012S\u00103\u001aO\u0012\u0015\u0012\u0013\u0018\u00010,¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0015\u0012\u0013\u0018\u00010,¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(0\u0012\u0013\u0012\u00110\b¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(1\u0012\u0004\u0012\u00020\u00040+j\u0002`2\u0012\n\b\u0002\u00108\u001a\u0004\u0018\u000107¢\u0006\u0004\b<\u0010=J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J6\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0002JH\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J6\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0002J\u001e\u0010\u0014\u001a\u00020\b2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0012\u0010\u0017\u001a\u00020\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0016J\u0010\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0004R\u001a\u0010\u001a\u001a\u00020\u00198\u0004X\u0084\u0004¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001f\u001a\u00020\u001e8\u0004X\u0084\u0004¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"R\u0014\u0010$\u001a\u00020#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%R\u001a\u0010'\u001a\u00020&8\u0004X\u0084\u0004¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*Rg\u00103\u001aO\u0012\u0015\u0012\u0013\u0018\u00010,¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0015\u0012\u0013\u0018\u00010,¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(0\u0012\u0013\u0012\u00110\b¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(1\u0012\u0004\u0012\u00020\u00040+j\u0002`28\u0004X\u0084\u0004¢\u0006\f\n\u0004\b3\u00104\u001a\u0004\b5\u00106R\u001c\u00108\u001a\u0004\u0018\u0001078\u0004X\u0084\u0004¢\u0006\f\n\u0004\b8\u00109\u001a\u0004\b:\u0010;¨\u0006A"}, d2 = {"Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListener;", "Landroid/view/View$OnClickListener;", "Lcom/amazon/mp3/library/item/MusicTrack;", "track", "", "initiatePlayback", "", "trackList", "", "trackPositionInTrackList", "", "shuffle", "Lcom/amazon/mp3/playback/service/metrics/PlaybackMetricInformation;", "playbackMetricInfo", "startPlayback", "isCustomerSelectedEntity", "Lcom/amazon/digitalmusicxp/clients/CloudQueueClient;", SearchMetricsServiceDefault.CONTENT_SRC_CLIENT, "startCloudQueuePlayback", "startPlayQueuePlayback", "findTrackPositionInList", "Landroid/view/View;", "p0", "onClick", "invokeOnClickCallback", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "getActivity", "()Landroidx/fragment/app/FragmentActivity;", "Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListener$CollectionInformationFetcher;", "collectionInfoFetcher", "Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListener$CollectionInformationFetcher;", "getCollectionInfoFetcher", "()Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListener$CollectionInformationFetcher;", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "playbackPageType", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "Landroid/net/Uri;", "uri", "Landroid/net/Uri;", "getUri", "()Landroid/net/Uri;", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "entityAsin", ContextMappingConstants.ENTITY_ID, "entityPos", "Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListenerOnClickCallback;", "onClickCallback", "Lkotlin/jvm/functions/Function3;", "getOnClickCallback", "()Lkotlin/jvm/functions/Function3;", "Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListener$PlaybackAugmentationProvider;", "playbackAugmentationProvider", "Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListener$PlaybackAugmentationProvider;", "getPlaybackAugmentationProvider", "()Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListener$PlaybackAugmentationProvider;", "<init>", "(Landroidx/fragment/app/FragmentActivity;Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListener$CollectionInformationFetcher;Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;Landroid/net/Uri;Lkotlin/jvm/functions/Function3;Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListener$PlaybackAugmentationProvider;)V", "Companion", "CollectionInformationFetcher", "PlaybackAugmentationProvider", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class CollectionTrackClickListener implements View.OnClickListener {
    private static final String TAG = CollectionTrackClickListener.class.getSimpleName();
    private final FragmentActivity activity;
    private final CollectionInformationFetcher collectionInfoFetcher;
    private final Function3<String, String, Integer, Unit> onClickCallback;
    private final PlaybackAugmentationProvider playbackAugmentationProvider;
    private final PlaybackPageType playbackPageType;
    private final Uri uri;

    /* JADX INFO: compiled from: CollectionTrackClickListener.kt */
    @Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&J\u0014\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00070\u0004H&J\b\u0010\n\u001a\u00020\tH&J\n\u0010\u000b\u001a\u0004\u0018\u00010\u0002H&J\b\u0010\f\u001a\u00020\tH&J\u0018\u0010\u000e\u001a\u0004\u0018\u00010\t2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H&J%\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u000f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H&¢\u0006\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListener$CollectionInformationFetcher;", "", "Landroid/net/Uri;", "uri", "Lrx/Observable;", "Lcom/amazon/mp3/library/item/MusicTrack;", "getTrack", "", "getAllTracks", "", "getSortOrder", "getCollectionUri", "getCollectionName", "trackList", "getSelection", "", "getSelectionArgs", "(Ljava/util/List;)[Ljava/lang/String;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public interface CollectionInformationFetcher {
        Observable<List<MusicTrack>> getAllTracks();

        String getCollectionName();

        Uri getCollectionUri();

        String getSelection(List<? extends MusicTrack> trackList);

        String[] getSelectionArgs(List<? extends MusicTrack> trackList);

        String getSortOrder();

        Observable<MusicTrack> getTrack(Uri uri);
    }

    /* JADX INFO: compiled from: CollectionTrackClickListener.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&¨\u0006\u0005"}, d2 = {"Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListener$PlaybackAugmentationProvider;", "", "getPlaybackAugmentations", "", "Lcom/amazon/music/platform/playback/augmentation/PlaybackAugmentation;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface PlaybackAugmentationProvider {
        List<PlaybackAugmentation> getPlaybackAugmentations();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CollectionTrackClickListener(FragmentActivity activity, CollectionInformationFetcher collectionInfoFetcher, PlaybackPageType playbackPageType, Uri uri, Function3<? super String, ? super String, ? super Integer, Unit> onClickCallback, PlaybackAugmentationProvider playbackAugmentationProvider) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(collectionInfoFetcher, "collectionInfoFetcher");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(onClickCallback, "onClickCallback");
        this.activity = activity;
        this.collectionInfoFetcher = collectionInfoFetcher;
        this.playbackPageType = playbackPageType;
        this.uri = uri;
        this.onClickCallback = onClickCallback;
        this.playbackAugmentationProvider = playbackAugmentationProvider;
    }

    public /* synthetic */ CollectionTrackClickListener(FragmentActivity fragmentActivity, CollectionInformationFetcher collectionInformationFetcher, PlaybackPageType playbackPageType, Uri uri, Function3 function3, PlaybackAugmentationProvider playbackAugmentationProvider, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(fragmentActivity, collectionInformationFetcher, playbackPageType, uri, function3, (i & 32) != 0 ? null : playbackAugmentationProvider);
    }

    protected final FragmentActivity getActivity() {
        return this.activity;
    }

    protected final CollectionInformationFetcher getCollectionInfoFetcher() {
        return this.collectionInfoFetcher;
    }

    protected final Uri getUri() {
        return this.uri;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View p0) {
        FragmentActivity fragmentActivity = this.activity;
        final LibraryContentEnabilityProvider libraryContentEnabilityProvider = new LibraryContentEnabilityProvider(fragmentActivity, fragmentActivity);
        final LibraryAccessProviderImpl libraryAccessProviderImpl = LibraryAccessProviderImpl.getInstance(this.activity);
        HapticsUtil.INSTANCE.hapticFeedbackOnPlayback();
        this.collectionInfoFetcher.getTrack(this.uri).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.detailpages.album.click.CollectionTrackClickListener$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CollectionTrackClickListener.m832onClick$lambda1(this.f$0, libraryContentEnabilityProvider, libraryAccessProviderImpl, (MusicTrack) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onClick$lambda-1, reason: not valid java name */
    public static final void m832onClick$lambda1(CollectionTrackClickListener this$0, LibraryContentEnabilityProvider contentEnabilityProvider, LibraryAccessProviderImpl libraryAccessProviderImpl, MusicTrack musicTrack) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(contentEnabilityProvider, "$contentEnabilityProvider");
        if (musicTrack == null) {
            return;
        }
        this$0.invokeOnClickCallback(musicTrack);
        if (!contentEnabilityProvider.isContentAccessAvailable(musicTrack)) {
            contentEnabilityProvider.handleContentAccessUnavailable(musicTrack);
        } else if (ConnectivityUtil.hasAnyInternetConnection(this$0.getActivity()) || libraryAccessProviderImpl.isAvailableOffline(musicTrack, true)) {
            this$0.initiatePlayback(musicTrack);
        } else {
            contentEnabilityProvider.handleContentAccessUnavailable(musicTrack);
        }
    }

    protected final void invokeOnClickCallback(final MusicTrack track) {
        Intrinsics.checkNotNullParameter(track, "track");
        this.collectionInfoFetcher.getAllTracks().subscribeOn(Schedulers.io()).subscribe(new Action1() { // from class: com.amazon.mp3.detailpages.album.click.CollectionTrackClickListener$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CollectionTrackClickListener.m831invokeOnClickCallback$lambda2(this.f$0, track, (List) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: invokeOnClickCallback$lambda-2, reason: not valid java name */
    public static final void m831invokeOnClickCallback$lambda2(CollectionTrackClickListener this$0, MusicTrack track, List trackList) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(track, "$track");
        Intrinsics.checkNotNullExpressionValue(trackList, "trackList");
        this$0.onClickCallback.invoke(track.getAsin(), track.getId(), Integer.valueOf(this$0.findTrackPositionInList(trackList, track) + 1));
    }

    private final void initiatePlayback(final MusicTrack track) {
        final PlaybackMetricInformation playbackMetricInformation = new PlaybackMetricInformation(DirectedPlayStatus.DIRECTED, this.playbackPageType);
        final boolean zIsShuffled = PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI).isShuffled();
        this.collectionInfoFetcher.getAllTracks().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.detailpages.album.click.CollectionTrackClickListener$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CollectionTrackClickListener.m829initiatePlayback$lambda3(this.f$0, track, zIsShuffled, playbackMetricInformation, (List) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.detailpages.album.click.CollectionTrackClickListener$$ExternalSyntheticLambda3
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CollectionTrackClickListener.m830initiatePlayback$lambda4((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initiatePlayback$lambda-3, reason: not valid java name */
    public static final void m829initiatePlayback$lambda3(CollectionTrackClickListener this$0, MusicTrack track, boolean z, PlaybackMetricInformation playbackMetricInfo, List trackList) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(track, "$track");
        Intrinsics.checkNotNullParameter(playbackMetricInfo, "$playbackMetricInfo");
        Intrinsics.checkNotNullExpressionValue(trackList, "trackList");
        this$0.startPlayback(track, trackList, this$0.findTrackPositionInList(trackList, track), z, playbackMetricInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initiatePlayback$lambda-4, reason: not valid java name */
    public static final void m830initiatePlayback$lambda4(Throwable th) {
        Log.error(TAG, Intrinsics.stringPlus("Error initiating playback: ", th.getMessage()));
    }

    private final void startPlayback(final MusicTrack track, final List<? extends MusicTrack> trackList, final int trackPositionInTrackList, final boolean shuffle, final PlaybackMetricInformation playbackMetricInfo) {
        CloudQueueClientSingleton.INSTANCE.getCloudQueueClientObservable().subscribe(new Consumer() { // from class: com.amazon.mp3.detailpages.album.click.CollectionTrackClickListener$$ExternalSyntheticLambda4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                CollectionTrackClickListener.m833startPlayback$lambda5(this.f$0, track, trackList, trackPositionInTrackList, shuffle, playbackMetricInfo, (CloudQueueClient) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startPlayback$lambda-5, reason: not valid java name */
    public static final void m833startPlayback$lambda5(CollectionTrackClickListener this$0, MusicTrack track, List trackList, int i, boolean z, PlaybackMetricInformation playbackMetricInfo, CloudQueueClient cloudQueueClient) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(track, "$track");
        Intrinsics.checkNotNullParameter(trackList, "$trackList");
        Intrinsics.checkNotNullParameter(playbackMetricInfo, "$playbackMetricInfo");
        if ((cloudQueueClient instanceof NoOpCloudQueueClient) || !AmazonApplication.getCapabilities().isCloudQueuePlaybackEnabled()) {
            this$0.startPlayQueuePlayback(track, trackList, i, z, playbackMetricInfo);
        } else {
            Intrinsics.checkNotNullExpressionValue(cloudQueueClient, "cloudQueueClient");
            this$0.startCloudQueuePlayback(track, trackList, i, z, playbackMetricInfo, true, cloudQueueClient);
        }
    }

    private final void startCloudQueuePlayback(MusicTrack track, List<? extends MusicTrack> trackList, int trackPositionInTrackList, boolean shuffle, PlaybackMetricInformation playbackMetricInfo, boolean isCustomerSelectedEntity, CloudQueueClient client) {
        Log.debug(TAG, "cloud queue playback");
        PlayTrackInCollectionAction playTrackInCollectionAction = PlayTrackInCollectionAction.INSTANCE;
        FragmentActivity fragmentActivity = this.activity;
        Uri collectionUri = this.collectionInfoFetcher.getCollectionUri();
        String selection = this.collectionInfoFetcher.getSelection(trackList);
        String[] selectionArgs = this.collectionInfoFetcher.getSelectionArgs(trackList);
        String sortOrder = this.collectionInfoFetcher.getSortOrder();
        String collectionName = this.collectionInfoFetcher.getCollectionName();
        PlaybackAugmentationProvider playbackAugmentationProvider = this.playbackAugmentationProvider;
        playTrackInCollectionAction.initiateCloudQueuePlayback(fragmentActivity, track, collectionUri, shuffle, selection, selectionArgs, playbackMetricInfo, isCustomerSelectedEntity, true, sortOrder, collectionName, trackList, playbackAugmentationProvider == null ? null : playbackAugmentationProvider.getPlaybackAugmentations());
    }

    private final void startPlayQueuePlayback(MusicTrack track, List<? extends MusicTrack> trackList, int trackPositionInTrackList, boolean shuffle, PlaybackMetricInformation playbackMetricInfo) {
        Log.debug(TAG, "play queue playback");
        PlayTrackInCollectionAction.INSTANCE.initiatePlayQueuePlayback(this.activity, track, this.collectionInfoFetcher.getCollectionUri(), trackPositionInTrackList, shuffle, this.collectionInfoFetcher.getSelection(trackList), this.collectionInfoFetcher.getSelectionArgs(trackList), playbackMetricInfo, true, this.collectionInfoFetcher.getSortOrder());
    }

    private final int findTrackPositionInList(List<? extends MusicTrack> trackList, MusicTrack track) {
        int size = trackList.size();
        int i = 0;
        while (i < size) {
            int i2 = i + 1;
            if (Intrinsics.areEqual(trackList.get(i).getContentUri(), track.getContentUri())) {
                return i;
            }
            i = i2;
        }
        return 0;
    }
}
