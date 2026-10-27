package com.amazon.mp3.detailpages.album.click;

import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.detailpages.album.click.CollectionTrackClickListener;
import com.amazon.mp3.recents.click.NoOpClickListener;
import com.amazon.mp3.util.Log;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.views.library.click.ClickListenerFactory;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CollectionTrackClickListenerFactory.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u0000 12\u00020\u0001:\u00011B\u0080\u0001\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\u0006\u0010\u001a\u001a\u00020\u0019\u0012S\u0010&\u001aO\u0012\u0015\u0012\u0013\u0018\u00010\u000b¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(!\u0012\u0015\u0012\u0013\u0018\u00010\u000b¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(\"\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(#\u0012\u0004\u0012\u00020$0\u001ej\u0002`%\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010*¢\u0006\u0004\b/\u00100J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J(\u0010\u0006\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016J\u0010\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\u0002H\u0016R\u001a\u0010\u0010\u001a\u00020\u000f8\u0004X\u0084\u0004¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0015\u001a\u00020\u00148\u0004X\u0084\u0004¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u001a\u001a\u00020\u00198\u0004X\u0084\u0004¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dRg\u0010&\u001aO\u0012\u0015\u0012\u0013\u0018\u00010\u000b¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(!\u0012\u0015\u0012\u0013\u0018\u00010\u000b¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(\"\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(#\u0012\u0004\u0012\u00020$0\u001ej\u0002`%8\u0004X\u0084\u0004¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)R\u001c\u0010+\u001a\u0004\u0018\u00010*8\u0004X\u0084\u0004¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.¨\u00062"}, d2 = {"Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListenerFactory;", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "contentMetadata", "Landroid/view/View$OnClickListener;", "createClickListener", "create", "", "contentMetadataList", "", "position", "", "title", "Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListener;", "getCollectionTrackClickListenerForTrack", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "getActivity", "()Landroidx/fragment/app/FragmentActivity;", "Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListener$CollectionInformationFetcher;", "collectionInfoFetcher", "Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListener$CollectionInformationFetcher;", "getCollectionInfoFetcher", "()Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListener$CollectionInformationFetcher;", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "playbackPageType", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "getPlaybackPageType", "()Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "entityAsin", ContextMappingConstants.ENTITY_ID, "entityPos", "", "Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListenerOnClickCallback;", "onClickCallback", "Lkotlin/jvm/functions/Function3;", "getOnClickCallback", "()Lkotlin/jvm/functions/Function3;", "Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListener$PlaybackAugmentationProvider;", "playbackAugmentationProvider", "Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListener$PlaybackAugmentationProvider;", "getPlaybackAugmentationProvider", "()Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListener$PlaybackAugmentationProvider;", "<init>", "(Landroidx/fragment/app/FragmentActivity;Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListener$CollectionInformationFetcher;Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;Lkotlin/jvm/functions/Function3;Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListener$PlaybackAugmentationProvider;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class CollectionTrackClickListenerFactory implements ClickListenerFactory {
    private static final String TAG = CollectionTrackClickListenerFactory.class.getSimpleName();
    private final FragmentActivity activity;
    private final CollectionTrackClickListener.CollectionInformationFetcher collectionInfoFetcher;
    private final Function3<String, String, Integer, Unit> onClickCallback;
    private final CollectionTrackClickListener.PlaybackAugmentationProvider playbackAugmentationProvider;
    private final PlaybackPageType playbackPageType;

    /* JADX WARN: Multi-variable type inference failed */
    public CollectionTrackClickListenerFactory(FragmentActivity activity, CollectionTrackClickListener.CollectionInformationFetcher collectionInfoFetcher, PlaybackPageType playbackPageType, Function3<? super String, ? super String, ? super Integer, Unit> onClickCallback, CollectionTrackClickListener.PlaybackAugmentationProvider playbackAugmentationProvider) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(collectionInfoFetcher, "collectionInfoFetcher");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        Intrinsics.checkNotNullParameter(onClickCallback, "onClickCallback");
        this.activity = activity;
        this.collectionInfoFetcher = collectionInfoFetcher;
        this.playbackPageType = playbackPageType;
        this.onClickCallback = onClickCallback;
        this.playbackAugmentationProvider = playbackAugmentationProvider;
    }

    public /* synthetic */ CollectionTrackClickListenerFactory(FragmentActivity fragmentActivity, CollectionTrackClickListener.CollectionInformationFetcher collectionInformationFetcher, PlaybackPageType playbackPageType, Function3 function3, CollectionTrackClickListener.PlaybackAugmentationProvider playbackAugmentationProvider, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(fragmentActivity, collectionInformationFetcher, playbackPageType, function3, (i & 16) != 0 ? null : playbackAugmentationProvider);
    }

    protected final FragmentActivity getActivity() {
        return this.activity;
    }

    protected final CollectionTrackClickListener.CollectionInformationFetcher getCollectionInfoFetcher() {
        return this.collectionInfoFetcher;
    }

    protected final PlaybackPageType getPlaybackPageType() {
        return this.playbackPageType;
    }

    protected final Function3<String, String, Integer, Unit> getOnClickCallback() {
        return this.onClickCallback;
    }

    @Override // com.amazon.music.views.library.click.ClickListenerFactory
    public View.OnClickListener create(ContentMetadata contentMetadata) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        return createClickListener(contentMetadata);
    }

    @Override // com.amazon.music.views.library.click.ClickListenerFactory
    public View.OnClickListener create(List<? extends ContentMetadata> contentMetadataList, int position, String title) {
        Intrinsics.checkNotNullParameter(contentMetadataList, "contentMetadataList");
        return createClickListener(contentMetadataList.get(position));
    }

    private final View.OnClickListener createClickListener(ContentMetadata contentMetadata) {
        if (contentMetadata instanceof TrackMetadata) {
            return getCollectionTrackClickListenerForTrack(contentMetadata);
        }
        Log.error(TAG, "Unsupported content metadata: " + ((Object) contentMetadata.getClass().getSimpleName()) + ", creating NoOpClickListener");
        return new NoOpClickListener();
    }

    public CollectionTrackClickListener getCollectionTrackClickListenerForTrack(ContentMetadata contentMetadata) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        return new CollectionTrackClickListener(this.activity, this.collectionInfoFetcher, this.playbackPageType, contentMetadata.getUri(), this.onClickCallback, this.playbackAugmentationProvider);
    }
}
