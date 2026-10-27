package com.amazon.mp3.brush.converters;

import android.net.Uri;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.music.views.library.converter.BaseContainerModelConverter;
import com.amazon.music.views.library.metadata.NavigationMetadata;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.PodcastTileModel;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.PodcastEpisode;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushPodcastEpisodeConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u000e2\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001:\u0001\u000eB\u0015\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\u0004\b\f\u0010\rJ0\u0010\b\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0005\u0018\u00010\u00072\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0002H\u0016J\u001e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000b¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushPodcastEpisodeConverter;", "Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "", "Lcom/amazon/podcast/PodcastEpisode;", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "children", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "convert", "getChildrenModels", "containerModel", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "<init>", "(Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushPodcastEpisodeConverter implements BaseContainerModelConverter<List<? extends PodcastEpisode>> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final BaseViewContainerModel<BaseViewModel> containerModel;

    public BrushPodcastEpisodeConverter(BaseViewContainerModel<BaseViewModel> containerModel) {
        Intrinsics.checkNotNullParameter(containerModel, "containerModel");
        this.containerModel = containerModel;
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public /* bridge */ /* synthetic */ BaseViewContainerModel convert(List<? extends PodcastEpisode> list, List list2) {
        return convert2((List<PodcastEpisode>) list, (List<? extends BaseViewModel>) list2);
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public /* bridge */ /* synthetic */ List getChildrenModels(List<? extends PodcastEpisode> list) {
        return getChildrenModels2((List<PodcastEpisode>) list);
    }

    /* JADX INFO: renamed from: convert, reason: avoid collision after fix types in other method */
    public BaseViewContainerModel<? extends BaseViewModel> convert2(List<PodcastEpisode> data, List<? extends BaseViewModel> children) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (children != null) {
            this.containerModel.setModels(children);
        }
        return this.containerModel;
    }

    /* JADX INFO: compiled from: BrushPodcastEpisodeConverter.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u00020\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\nJ\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\n2\u0006\u0010\r\u001a\u00020\u000eJ\u0016\u0010\u000f\u001a\u00020\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\nJ\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\n2\u0006\u0010\r\u001a\u00020\u000eJ\u0016\u0010\u0011\u001a\u00020\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushPodcastEpisodeConverter$Companion;", "", "()V", "PODCAST_EPISODE_PROGRESS_TAG", "", "PODCAST_JUMP_BACK_IN_EPISODES", "PODCAST_SAVED_EPISODES", "containsPodcastEpisodeProgressTag", "", "tags", "", "containsPodcastJumpBackInShoveler", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "pageModel", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "containsPodcastJumpBackInTag", "containsPodcastSavedEpisodesShoveler", "containsPodcastSavedEpisodesTag", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean containsPodcastSavedEpisodesTag(List<String> tags) {
            return tags != null && tags.contains("podcast-saved-episodes");
        }

        public final boolean containsPodcastJumpBackInTag(List<String> tags) {
            return tags != null && tags.contains("podcast-jump-back-in-episodes");
        }

        public final List<BaseViewModel> containsPodcastSavedEpisodesShoveler(PageGridViewModel pageModel) {
            Intrinsics.checkNotNullParameter(pageModel, "pageModel");
            List<BaseViewModel> models = pageModel.getModels();
            ArrayList arrayList = new ArrayList();
            for (Object obj : models) {
                if (obj instanceof BaseViewContainerModel) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = new ArrayList();
            for (Object obj2 : arrayList) {
                if (BrushPodcastEpisodeConverter.INSTANCE.containsPodcastSavedEpisodesTag(((BaseViewContainerModel) obj2).getTags())) {
                    arrayList2.add(obj2);
                }
            }
            return arrayList2;
        }

        public final List<BaseViewModel> containsPodcastJumpBackInShoveler(PageGridViewModel pageModel) {
            Intrinsics.checkNotNullParameter(pageModel, "pageModel");
            List<BaseViewModel> models = pageModel.getModels();
            ArrayList arrayList = new ArrayList();
            for (Object obj : models) {
                if (obj instanceof BaseViewContainerModel) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = new ArrayList();
            for (Object obj2 : arrayList) {
                if (BrushPodcastEpisodeConverter.INSTANCE.containsPodcastJumpBackInTag(((BaseViewContainerModel) obj2).getTags())) {
                    arrayList2.add(obj2);
                }
            }
            return arrayList2;
        }

        public final boolean containsPodcastEpisodeProgressTag(List<String> tags) {
            if (tags == null) {
                return false;
            }
            return tags.contains("podcast-episode-progress");
        }
    }

    /* JADX INFO: renamed from: getChildrenModels, reason: avoid collision after fix types in other method */
    public List<BaseViewModel> getChildrenModels2(List<PodcastEpisode> data) {
        String podcastEpisodeVariantId;
        Intrinsics.checkNotNullParameter(data, "data");
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(data, 10));
        for (PodcastEpisode podcastEpisode : data) {
            if (Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled()) {
                podcastEpisodeVariantId = podcastEpisode.getPodcastEpisodeVariantId();
                if (podcastEpisodeVariantId == null) {
                    podcastEpisodeVariantId = podcastEpisode.getEpisodeId();
                }
            } else {
                podcastEpisodeVariantId = null;
            }
            String str = podcastEpisodeVariantId;
            String title = podcastEpisode.getTitle();
            String image = podcastEpisode.getImage();
            String deeplink = podcastEpisode.getDeeplink();
            Uri barkerUri = ContentPlaybackUtils.INSTANCE.getBarkerUri();
            Intrinsics.checkNotNullExpressionValue(deeplink, "deeplink");
            NavigationMetadata navigationMetadata = new NavigationMetadata(str, barkerUri, null, deeplink, null, null, null, 116, null);
            int totalDurationMilliseconds = podcastEpisode.getTotalDurationMilliseconds();
            String totalDurationText = podcastEpisode.getTotalDurationText();
            Integer progress = podcastEpisode.getProgress();
            String remainingDurationText = podcastEpisode.getRemainingDurationText();
            Intrinsics.checkNotNullExpressionValue(image, "image");
            arrayList.add(new PodcastTileModel(null, title, null, null, image, 2, navigationMetadata, true, Integer.valueOf(totalDurationMilliseconds), totalDurationText, progress, remainingDurationText, 8, null));
        }
        return arrayList;
    }
}
