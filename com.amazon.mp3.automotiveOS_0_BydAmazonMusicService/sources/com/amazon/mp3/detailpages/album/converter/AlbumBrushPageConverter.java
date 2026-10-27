package com.amazon.mp3.detailpages.album.converter;

import android.content.Context;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.baseviews.model.configuration.AlbumModelConfiguration;
import com.amazon.mp3.brush.converters.BrushButtonNavigatorConverter;
import com.amazon.mp3.brush.converters.BrushConverterFactory;
import com.amazon.mp3.brush.converters.BrushConverterUtils;
import com.amazon.mp3.brush.converters.BrushPageGridConverter;
import com.amazon.mp3.cache.AlbumCache;
import com.amazon.mp3.eligibility.CachedCapabilityActionProviderImpl;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.views.library.converter.BaseContainerModelConverter;
import com.amazon.music.views.library.converter.BaseModelConverter;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.models.ButtonNavigatorModel;
import com.amazon.music.views.library.models.DescriptiveTextItemModel;
import com.amazon.music.views.library.models.DescriptiveTextModel;
import com.amazon.music.views.library.models.FeaturedToggleViewModel;
import com.amazon.music.views.library.models.MultiStateButtonModel;
import com.amazon.music.views.library.models.TextRowItemModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.base.Hideable;
import com.amazon.music.views.library.models.base.Insettable;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.TextRowItemView;
import com.amazon.musicensembleservice.brush.Album;
import com.amazon.musicensembleservice.brush.Artist;
import com.amazon.musicensembleservice.brush.Block;
import com.amazon.musicensembleservice.brush.Entity;
import com.amazon.musicensembleservice.brush.Track;
import com.visualon.OSMPUtils.voOSType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: AlbumBrushPageConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\r\u001a\u00020\u000eJ2\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0014J\u001e\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0014J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0012\u001a\u00020\u0013H\u0014R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/amazon/mp3/detailpages/album/converter/AlbumBrushPageConverter;", "Lcom/amazon/mp3/brush/converters/BrushPageGridConverter;", "mContext", "Landroid/content/Context;", "albumCache", "Lcom/amazon/mp3/cache/AlbumCache;", "(Landroid/content/Context;Lcom/amazon/mp3/cache/AlbumCache;)V", "cachedCapabilityActionProvider", "Lcom/amazon/mp3/eligibility/CachedCapabilityActionProviderImpl;", "getMContext", "()Landroid/content/Context;", "modelConfiguration", "Lcom/amazon/mp3/baseviews/model/configuration/AlbumModelConfiguration;", "clear", "", "convertBlock", "entity", "Lcom/amazon/musicensembleservice/brush/Entity;", "block", "Lcom/amazon/musicensembleservice/brush/Block;", "converter", "Lcom/amazon/music/views/library/converter/BaseModelConverter;", "sectionModels", "Ljava/util/ArrayList;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convertEntity", "getConverter", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AlbumBrushPageConverter extends BrushPageGridConverter {
    private final AlbumCache albumCache;
    private CachedCapabilityActionProviderImpl cachedCapabilityActionProvider;
    private final Context mContext;
    private AlbumModelConfiguration modelConfiguration;

    @Override // com.amazon.mp3.brush.converters.BrushPageGridConverter
    public Context getMContext() {
        return this.mContext;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlbumBrushPageConverter(Context mContext, AlbumCache albumCache) {
        super(mContext);
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        this.mContext = mContext;
        this.albumCache = albumCache;
    }

    public final void clear() {
        AlbumCache albumCache = this.albumCache;
        if (albumCache != null) {
            albumCache.clearCache();
        }
        this.modelConfiguration = null;
        this.cachedCapabilityActionProvider = null;
    }

    @Override // com.amazon.mp3.brush.converters.BrushPageGridConverter
    protected void convertEntity(Entity entity, ArrayList<BaseViewModel> sectionModels) {
        Intrinsics.checkNotNullParameter(entity, "entity");
        Intrinsics.checkNotNullParameter(sectionModels, "sectionModels");
        if (!(entity instanceof Album)) {
            super.convertEntity(entity, sectionModels);
            return;
        }
        Album album = (Album) entity;
        AlbumMetadata albumMetadata = BrushConverterUtils.INSTANCE.getAlbumMetadata(album, this.albumCache);
        CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl = new CachedCapabilityActionProviderImpl(null, 1, null);
        this.cachedCapabilityActionProvider = cachedCapabilityActionProviderImpl;
        this.modelConfiguration = new AlbumModelConfiguration(albumMetadata, cachedCapabilityActionProviderImpl);
        BrushConverterFactory brushConverterFactory = BrushConverterFactory.INSTANCE;
        BaseViewModel baseViewModelConvert = brushConverterFactory.getDetailHeaderConverter(getMContext(), this.modelConfiguration, this.albumCache).convert(entity);
        if (baseViewModelConvert != null) {
            sectionModels.add(baseViewModelConvert);
        }
        BaseViewModel baseViewModelConvert2 = brushConverterFactory.getRefinementsConverter(this.modelConfiguration).convert(entity);
        if (baseViewModelConvert2 != null) {
            sectionModels.add(baseViewModelConvert2);
        }
        BaseModelConverter converter = brushConverterFactory.getConverter("BUTTON_NAVIGATOR");
        SingleBaseModelConverter singleBaseModelConverter = converter instanceof SingleBaseModelConverter ? (SingleBaseModelConverter) converter : null;
        BrushButtonNavigatorConverter brushButtonNavigatorConverter = singleBaseModelConverter instanceof BrushButtonNavigatorConverter ? (BrushButtonNavigatorConverter) singleBaseModelConverter : null;
        if (brushButtonNavigatorConverter != null) {
            brushButtonNavigatorConverter.setModelConfiguration(this.modelConfiguration);
        }
        BaseViewModel baseViewModelConvert3 = singleBaseModelConverter == null ? null : singleBaseModelConverter.convert(entity);
        if (baseViewModelConvert3 != null) {
            sectionModels.add(baseViewModelConvert3);
        }
        TextRowItemModel textRowItemModel = new AlbumModelConfiguration(null, null, 3, null).shouldShowShuffleText(albumMetadata) ? new TextRowItemModel(null, getMContext().getString(R.string.dmusic_shuffle_album_text_SR), Boolean.FALSE, Boolean.TRUE, TextRowItemView.TextStyle.HEADER, new AlbumModelConfiguration(null, null, 3, null).shouldShowShuffledWithSection(albumMetadata), 1, null) : null;
        if (textRowItemModel != null) {
            sectionModels.add(textRowItemModel);
        }
        AlbumModelConfiguration albumModelConfiguration = this.modelConfiguration;
        if (albumModelConfiguration == null || !albumModelConfiguration.shouldShowCommentaryToggle() || album.getCommentary() == null) {
            return;
        }
        String string = getMContext().getString(R.string.dmusic_artist_commentary);
        FeaturedToggleViewModel.ToggleType toggleType = FeaturedToggleViewModel.ToggleType.ARTIST_COMMENTARY;
        String displayText = album.getCommentary().getDisplayText();
        String url = album.getImage().getUrl();
        Intrinsics.checkNotNullExpressionValue(url, "entity.image.url");
        FeaturedToggleViewModel.ImageModel imageModel = new FeaturedToggleViewModel.ImageModel(url, 0);
        List<Artist> artists = album.getArtists();
        Intrinsics.checkNotNullExpressionValue(artists, "entity.artists");
        String url2 = ((Artist) CollectionsKt___CollectionsKt.first((List) artists)).getImage().getUrl();
        Intrinsics.checkNotNullExpressionValue(url2, "entity.artists.first().image.url");
        FeaturedToggleViewModel.ImageModel imageModel2 = new FeaturedToggleViewModel.ImageModel(url2, 4);
        HashMap mapHashMapOf = MapsKt__MapsKt.hashMapOf(TuplesKt.to(ContextMappingConstants.ENTITY_ID, album.getAsin()), TuplesKt.to(ContextMappingConstants.ENTITY_ID_TYPE, EntityIdType.ASIN.getMetricsValue()), TuplesKt.to(PageUriUtils.ENTITY_TYPE_QUERY_PARAM, EntityType.ALBUM.getMetricsValue()));
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.dmusic_artist_commentary)");
        Intrinsics.checkNotNullExpressionValue(displayText, "displayText");
        sectionModels.add(new FeaturedToggleViewModel(null, string, displayText, toggleType, imageModel, imageModel2, mapHashMapOf, false, voOSType.VOOSMP_PID_AUDIO_EFFECT_ENDPOINT_TYPE, null));
    }

    @Override // com.amazon.mp3.brush.converters.BrushPageGridConverter
    protected BaseModelConverter getConverter(Block block) {
        Intrinsics.checkNotNullParameter(block, "block");
        return BrushConverterFactory.INSTANCE.getAlbumConverter(block, this.modelConfiguration, this.cachedCapabilityActionProvider, getMContext(), this.albumCache);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazon.mp3.brush.converters.BrushPageGridConverter
    protected void convertBlock(Entity entity, Block block, BaseModelConverter converter, ArrayList<BaseViewModel> sectionModels) {
        int iNextIndex;
        Object next;
        Intrinsics.checkNotNullParameter(block, "block");
        Intrinsics.checkNotNullParameter(sectionModels, "sectionModels");
        boolean z = entity instanceof Album;
        if (!z || !(converter instanceof BaseContainerModelConverter) || !StringsKt__StringsJVMKt.equals(block.getRenderingType(), "DESCRIPTIVE_LISTING", true)) {
            super.convertBlock(entity, block, converter, sectionModels);
            return;
        }
        if (z && StringsKt__StringsJVMKt.equals(block.getRenderingType(), "DESCRIPTIVE_LISTING", true)) {
            block.getTags().add("FILTERABLE_MODELS_CONTAINER_TAG");
            block.getTags().add("TRACK_LIST_TAG");
            Album album = (Album) entity;
            AlbumMetadata albumMetadata = BrushConverterUtils.INSTANCE.getAlbumMetadata(album, this.albumCache);
            boolean zShouldShowShuffledWithSection = new AlbumModelConfiguration(null, null, 3, null).shouldShowShuffledWithSection(albumMetadata);
            boolean zShouldShowTracklistPreview = new AlbumModelConfiguration(null, null, 3, null).shouldShowTracklistPreview(albumMetadata);
            ArrayList arrayList = new ArrayList();
            Album album2 = z ? album : null;
            if (album2 != null) {
                List<Entity> entities = block.getContent().getEntities();
                Intrinsics.checkNotNullExpressionValue(entities, "block.content.entities");
                ArrayList<Track> arrayList2 = new ArrayList();
                for (Entity entity2 : entities) {
                    Track track = entity2 instanceof Track ? (Track) entity2 : null;
                    if (track != null) {
                        arrayList2.add(track);
                    }
                }
                for (Track track2 : arrayList2) {
                    if (track2.getCommentary() == null) {
                        List<Track> tracks = album2.getTracks();
                        Intrinsics.checkNotNullExpressionValue(tracks, "album.tracks");
                        Iterator<T> it = tracks.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                next = it.next();
                                if (Intrinsics.areEqual(((Track) next).getAsin(), track2.getAsin())) {
                                    break;
                                }
                            } else {
                                next = null;
                                break;
                            }
                        }
                        Track track3 = (Track) next;
                        if (track3 != null) {
                            track2.setCommentary(track3.getCommentary());
                        }
                        if (zShouldShowShuffledWithSection) {
                            arrayList.add(new DescriptiveTextItemModel(track2.getPrimaryTitle(), null));
                        }
                    }
                }
            }
            int iNextIndex2 = -1;
            if (zShouldShowShuffledWithSection) {
                ListIterator<BaseViewModel> listIterator = sectionModels.listIterator(sectionModels.size());
                while (true) {
                    if (listIterator.hasPrevious()) {
                        if (listIterator.previous() instanceof ButtonNavigatorModel) {
                            iNextIndex = listIterator.nextIndex();
                            break;
                        }
                    } else {
                        iNextIndex = -1;
                        break;
                    }
                }
                TextRowItemModel textRowItemModel = new TextRowItemModel(null, getMContext().getString(R.string.dmusic_hide_tracklist_shuffle_text), Boolean.FALSE, Boolean.TRUE, TextRowItemView.TextStyle.HEADER, false, 33, null);
                String string = getMContext().getString(R.string.dmusic_hide_tracklist_footer_text);
                Intrinsics.checkNotNullExpressionValue(string, "mContext.getString(R.str…de_tracklist_footer_text)");
                sectionModels.addAll(iNextIndex + 1, CollectionsKt__CollectionsKt.listOf((Object[]) new BaseViewModel[]{textRowItemModel, new DescriptiveTextModel(arrayList, string, " • ", null, 0, 0, false, 120, null)}));
            }
            if (zShouldShowTracklistPreview) {
                ListIterator<BaseViewModel> listIterator2 = sectionModels.listIterator(sectionModels.size());
                while (true) {
                    if (listIterator2.hasPrevious()) {
                        if (listIterator2.previous() instanceof DescriptiveTextModel) {
                            iNextIndex2 = listIterator2.nextIndex();
                            break;
                        }
                    } else {
                        break;
                    }
                }
                String string2 = getMContext().getString(R.string.dmusic_preview_tracklist_button_primary);
                String string3 = getMContext().getString(R.string.dmusic_preview_tracklist_button_secondary);
                ButtonSizeKey buttonSizeKey = ButtonSizeKey.MEDIUM;
                ButtonStyleKey buttonStyleKey = ButtonStyleKey.GLASS_OUTLINE;
                SpacerKey spacerKey = SpacerKey.MEDIUM;
                SpacerKey spacerKey2 = SpacerKey.SMALL;
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.dmusi…tracklist_button_primary)");
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.dmusi…acklist_button_secondary)");
                sectionModels.add(iNextIndex2 + 1, new MultiStateButtonModel(null, null, -2, string2, string3, false, false, Boolean.TRUE, buttonSizeKey, buttonStyleKey, spacerKey, spacerKey2, 99, null));
            }
            BaseViewModel baseViewModel = (BaseViewModel) CollectionsKt___CollectionsKt.lastOrNull((List) sectionModels);
            if (baseViewModel != null) {
                List<String> tags = baseViewModel.getTags();
                if (tags == null) {
                    tags = CollectionsKt__CollectionsKt.emptyList();
                }
                baseViewModel.setTags(CollectionsKt___CollectionsKt.plus((Collection<? extends String>) tags, "PRE_FILTERABLE_CONTENT_TAG"));
            }
            List<BaseViewModel> childrenModels = ((BaseContainerModelConverter) converter).getChildrenModels(block);
            if (childrenModels != null) {
                for (BaseViewModel baseViewModel2 : childrenModels) {
                    List<String> tags2 = baseViewModel2.getTags();
                    if (tags2 == null) {
                        tags2 = CollectionsKt__CollectionsKt.emptyList();
                    }
                    baseViewModel2.setTags(CollectionsKt___CollectionsKt.plus((Collection) tags2, (Iterable) CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"FILTERABLE_MODEL_TAG", "TRACK_TAG"})));
                    Insettable insettable = baseViewModel2 instanceof Insettable ? (Insettable) baseViewModel2 : null;
                    if (insettable != null) {
                        insettable.setInsetHorizontally(true);
                    }
                    if (zShouldShowShuffledWithSection) {
                        Hideable hideable = baseViewModel2 instanceof Hideable ? (Hideable) baseViewModel2 : null;
                        if (hideable != null) {
                            hideable.updateVisibility(true);
                        }
                    }
                }
                CollectionsKt__MutableCollectionsKt.addAll(sectionModels, childrenModels);
            }
            TextRowItemModel textRowItemModel2 = new TextRowItemModel(null, album.getCopyright(), Boolean.TRUE, null, TextRowItemView.TextStyle.COPYRIGHT_TEXT, false, 41, null);
            List<String> tags3 = textRowItemModel2.getTags();
            if (tags3 == null) {
                tags3 = CollectionsKt__CollectionsKt.emptyList();
            }
            textRowItemModel2.setTags(CollectionsKt___CollectionsKt.plus((Collection<? extends String>) tags3, "COPYRIGHT"));
            sectionModels.add(textRowItemModel2);
        }
    }
}
