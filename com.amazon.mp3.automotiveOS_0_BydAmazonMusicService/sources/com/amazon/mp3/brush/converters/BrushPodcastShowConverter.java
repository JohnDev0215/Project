package com.amazon.mp3.brush.converters;

import android.net.Uri;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.music.views.library.converter.BaseContainerModelConverter;
import com.amazon.music.views.library.metadata.NavigationMetadata;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.PodcastTileModel;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.podcast.PodcastShow;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushPodcastShowConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u000e2\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001:\u0001\u000eB\u0015\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\u0004\b\f\u0010\rJ0\u0010\b\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0005\u0018\u00010\u00072\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0002H\u0016J\u001e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000b¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushPodcastShowConverter;", "Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "", "Lcom/amazon/podcast/PodcastShow;", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "children", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "convert", "getChildrenModels", "containerModel", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "<init>", "(Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushPodcastShowConverter implements BaseContainerModelConverter<List<? extends PodcastShow>> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final BaseViewContainerModel<BaseViewModel> containerModel;

    public BrushPodcastShowConverter(BaseViewContainerModel<BaseViewModel> containerModel) {
        Intrinsics.checkNotNullParameter(containerModel, "containerModel");
        this.containerModel = containerModel;
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public /* bridge */ /* synthetic */ BaseViewContainerModel convert(List<? extends PodcastShow> list, List list2) {
        return convert2((List<PodcastShow>) list, (List<? extends BaseViewModel>) list2);
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public /* bridge */ /* synthetic */ List getChildrenModels(List<? extends PodcastShow> list) {
        return getChildrenModels2((List<PodcastShow>) list);
    }

    /* JADX INFO: renamed from: convert, reason: avoid collision after fix types in other method */
    public BaseViewContainerModel<? extends BaseViewModel> convert2(List<PodcastShow> data, List<? extends BaseViewModel> children) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (children != null) {
            this.containerModel.setModels(children);
        }
        return this.containerModel;
    }

    /* JADX INFO: compiled from: BrushPodcastShowConverter.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u000b2\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushPodcastShowConverter$Companion;", "", "()V", "PODCAST_FOLLOWED_SHOWS", "", "containsPodcastFollowedShowsShoveler", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "pageModel", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "containsPodcastShowTag", "", "tags", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean containsPodcastShowTag(List<String> tags) {
            return tags != null && tags.contains("podcast-followed-shows");
        }

        public final List<BaseViewModel> containsPodcastFollowedShowsShoveler(PageGridViewModel pageModel) {
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
                if (BrushPodcastShowConverter.INSTANCE.containsPodcastShowTag(((BaseViewContainerModel) obj2).getTags())) {
                    arrayList2.add(obj2);
                }
            }
            return arrayList2;
        }
    }

    /* JADX INFO: renamed from: getChildrenModels, reason: avoid collision after fix types in other method */
    public List<BaseViewModel> getChildrenModels2(List<PodcastShow> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(data, 10));
        for (PodcastShow podcastShow : data) {
            String image = podcastShow.getImage();
            String deeplink = podcastShow.getDeeplink();
            Uri barkerUri = ContentPlaybackUtils.INSTANCE.getBarkerUri();
            Intrinsics.checkNotNullExpressionValue(deeplink, "deeplink");
            NavigationMetadata navigationMetadata = new NavigationMetadata(null, barkerUri, null, deeplink, null, null, null, 116, null);
            Intrinsics.checkNotNullExpressionValue(image, "image");
            arrayList.add(new PodcastTileModel(null, null, null, null, image, 2, navigationMetadata, false, null, null, null, null, 3983, null));
        }
        return arrayList;
    }
}
