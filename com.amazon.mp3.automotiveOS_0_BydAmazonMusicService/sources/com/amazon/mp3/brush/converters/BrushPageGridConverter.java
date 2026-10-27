package com.amazon.mp3.brush.converters;

import android.content.Context;
import com.amazon.mp3.util.Log;
import com.amazon.music.views.library.converter.BaseContainerModelConverter;
import com.amazon.music.views.library.converter.BaseModelConverter;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.ArtistDetailHeaderModel;
import com.amazon.music.views.library.models.ExposedRefinementListModel;
import com.amazon.music.views.library.models.HorizontalTileModel;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.SectionGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.musicensembleservice.brush.Album;
import com.amazon.musicensembleservice.brush.Artist;
import com.amazon.musicensembleservice.brush.Block;
import com.amazon.musicensembleservice.brush.Content;
import com.amazon.musicensembleservice.brush.CustomerProfile;
import com.amazon.musicensembleservice.brush.Entity;
import com.amazon.musicensembleservice.brush.Header;
import com.amazon.musicensembleservice.brush.Page;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: BrushPageGridConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u001b\u001a\u00020\u001a¢\u0006\u0004\b+\u0010,J\"\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004H\u0016J\u0012\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\n\u001a\u00020\tH\u0014J\u001e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\r2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u000fH\u0014J2\u0010\u0014\u001a\u00020\u00112\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\n\u001a\u00020\t2\b\u0010\u0013\u001a\u0004\u0018\u00010\u000b2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u000fH\u0014J\u0018\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0016\u0010\u0019\u001a\u00020\u00182\u000e\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0004R\u001a\u0010\u001b\u001a\u00020\u001a8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u001c\u0010 \u001a\n \u001f*\u0004\u0018\u00010\u00160\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!R\u0016\u0010#\u001a\u00020\"8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b#\u0010$R\u001b\u0010*\u001a\u00020%8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)¨\u0006-"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushPageGridConverter;", "Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "Lcom/amazon/musicensembleservice/brush/Page;", "data", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "children", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "convert", "Lcom/amazon/musicensembleservice/brush/Block;", "block", "Lcom/amazon/music/views/library/converter/BaseModelConverter;", "getConverter", "Lcom/amazon/musicensembleservice/brush/Entity;", "entity", "Ljava/util/ArrayList;", "sectionModels", "", "convertEntity", "converter", "convertBlock", "getChildrenModels", "", "tags", "", "containsPopularSongsTag", "Landroid/content/Context;", "mContext", "Landroid/content/Context;", "getMContext", "()Landroid/content/Context;", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "Lcom/amazon/music/views/library/models/ArtistDetailHeaderModel;", "artistDetailHeaderModel", "Lcom/amazon/music/views/library/models/ArtistDetailHeaderModel;", "Lcom/amazon/mp3/brush/converters/BrushPageSeeMoreConverter;", "mSeeMoreConverter$delegate", "Lkotlin/Lazy;", "getMSeeMoreConverter", "()Lcom/amazon/mp3/brush/converters/BrushPageSeeMoreConverter;", "mSeeMoreConverter", "<init>", "(Landroid/content/Context;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class BrushPageGridConverter implements BaseContainerModelConverter<Page> {
    private final String TAG;
    private ArtistDetailHeaderModel artistDetailHeaderModel;
    private final Context mContext;

    /* JADX INFO: renamed from: mSeeMoreConverter$delegate, reason: from kotlin metadata */
    private final Lazy mSeeMoreConverter;

    public BrushPageGridConverter(Context mContext) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        this.mContext = mContext;
        this.TAG = BrushPageGridConverter.class.getSimpleName();
        this.mSeeMoreConverter = LazyKt__LazyJVMKt.lazy(new Function0<BrushPageSeeMoreConverter>() { // from class: com.amazon.mp3.brush.converters.BrushPageGridConverter$mSeeMoreConverter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BrushPageSeeMoreConverter invoke() {
                return new BrushPageSeeMoreConverter();
            }
        });
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public /* bridge */ /* synthetic */ BaseViewContainerModel convert(Page page, List list) {
        return convert2(page, (List<? extends BaseViewModel>) list);
    }

    public Context getMContext() {
        return this.mContext;
    }

    private final BrushPageSeeMoreConverter getMSeeMoreConverter() {
        return (BrushPageSeeMoreConverter) this.mSeeMoreConverter.getValue();
    }

    /* JADX INFO: renamed from: convert, reason: avoid collision after fix types in other method */
    public PageGridViewModel convert2(Page data, List<? extends BaseViewModel> children) {
        Intrinsics.checkNotNullParameter(data, "data");
        Content content = data.getContent();
        if (getMSeeMoreConverter().isSeeMorePage(content == null ? null : content.getBlocks(), children)) {
            return getMSeeMoreConverter().convert2(data, children);
        }
        Header header = data.getHeader();
        ExposedRefinementListModel exposedRefinementListModelConvert = header == null ? null : BrushHeaderConverter.INSTANCE.convert(header);
        if (children == null || !(!children.isEmpty())) {
            return null;
        }
        String title = data.getTitle();
        String blockRef = data.getBlockRef();
        Content content2 = data.getContent();
        return new PageGridViewModel(blockRef, null, title, null, children, 2, 0, content2 != null ? content2.getNextToken() : null, BrushFilterConverter.INSTANCE.convert(data.getFilters()), null, exposedRefinementListModelConvert, 522, null);
    }

    protected BaseModelConverter getConverter(Block block) {
        Intrinsics.checkNotNullParameter(block, "block");
        return BrushConverterFactory.INSTANCE.getParentConverter(block, getMContext());
    }

    protected void convertEntity(Entity entity, ArrayList<BaseViewModel> sectionModels) {
        Intrinsics.checkNotNullParameter(entity, "entity");
        Intrinsics.checkNotNullParameter(sectionModels, "sectionModels");
        if (entity instanceof CustomerProfile) {
            BaseModelConverter customerProfileConverter = BrushConverterFactory.INSTANCE.getCustomerProfileConverter(getMContext());
            if (customerProfileConverter == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.converter.SingleBaseModelConverter<com.amazon.musicensembleservice.brush.CustomerProfile>");
            }
            BaseViewModel baseViewModelConvert = ((SingleBaseModelConverter) customerProfileConverter).convert(entity);
            if (baseViewModelConvert == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.models.base.BaseViewModel");
            }
            sectionModels.add(baseViewModelConvert);
            return;
        }
        if (entity instanceof Artist) {
            BaseViewModel baseViewModelConvert2 = BrushConverterFactory.getDetailHeaderConverter$default(BrushConverterFactory.INSTANCE, getMContext(), null, null, 6, null).convert(entity);
            if (baseViewModelConvert2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.models.base.BaseViewModel");
            }
            sectionModels.add(baseViewModelConvert2);
        }
    }

    protected void convertBlock(Entity entity, Block block, BaseModelConverter converter, ArrayList<BaseViewModel> sectionModels) {
        Intrinsics.checkNotNullParameter(block, "block");
        Intrinsics.checkNotNullParameter(sectionModels, "sectionModels");
        if (converter instanceof BrushPresetShovelerConverter) {
            List<BaseViewModel> childrenModels2 = ((BrushPresetShovelerConverter) converter).getChildrenModels(block);
            if (childrenModels2 == null) {
                return;
            }
            sectionModels.addAll(childrenModels2);
            return;
        }
        if (!(converter instanceof BaseContainerModelConverter)) {
            if (!(converter instanceof SingleBaseModelConverter)) {
                Log.error(this.TAG, Intrinsics.stringPlus("Could not find converter to match RenderingType: ", block.getRenderingType()));
                return;
            }
            if (converter == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.converter.SingleBaseModelConverter<com.amazon.musicensembleservice.brush.Entity>");
            }
            List<Entity> entities = block.getContent().getEntities();
            Intrinsics.checkNotNullExpressionValue(entities, "block.content.entities");
            Object objFirst = CollectionsKt___CollectionsKt.first((List<? extends Object>) entities);
            Intrinsics.checkNotNullExpressionValue(objFirst, "block.content.entities.first()");
            BaseViewModel baseViewModelConvert = ((SingleBaseModelConverter) converter).convert(objFirst);
            if (baseViewModelConvert == null) {
                return;
            }
            sectionModels.add(baseViewModelConvert);
            return;
        }
        if (converter != null) {
            BaseContainerModelConverter baseContainerModelConverter = (BaseContainerModelConverter) converter;
            BaseViewContainerModel<? extends BaseViewModel> baseViewContainerModelConvert = baseContainerModelConverter.convert(block, baseContainerModelConverter.getChildrenModels(block));
            if (baseViewContainerModelConvert != null) {
                sectionModels.add(baseViewContainerModelConvert);
            }
            if ((baseViewContainerModelConvert instanceof SectionGridViewModel) && containsPopularSongsTag(block.getTags())) {
                SectionGridViewModel sectionGridViewModel = (SectionGridViewModel) baseViewContainerModelConvert;
                if (sectionGridViewModel.getModels().get(0) instanceof HorizontalTileModel) {
                    ArtistDetailHeaderModel artistDetailHeaderModel = this.artistDetailHeaderModel;
                    if (artistDetailHeaderModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("artistDetailHeaderModel");
                        artistDetailHeaderModel = null;
                    }
                    artistDetailHeaderModel.setMetadataList(((HorizontalTileModel) sectionGridViewModel.getModels().get(0)).getMetadataList());
                    return;
                }
                return;
            }
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.converter.BaseContainerModelConverter<com.amazon.musicensembleservice.brush.Block>");
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public List<BaseViewModel> getChildrenModels(Page data) {
        Intrinsics.checkNotNullParameter(data, "data");
        Content content = data.getContent();
        Entity entity = data.getEntity();
        List<Block> blocks = content == null ? null : content.getBlocks();
        ArrayList<BaseViewModel> arrayList = new ArrayList<>();
        if (entity != null) {
            Intrinsics.checkNotNullExpressionValue(entity, "entity");
            convertEntity(entity, arrayList);
        }
        if (blocks == null) {
            return null;
        }
        if (BrushPageSeeMoreConverter.isSeeMorePage$default(getMSeeMoreConverter(), blocks, null, 2, null) && !(entity instanceof Album)) {
            return getMSeeMoreConverter().getChildrenModels(data);
        }
        for (Block block : blocks) {
            if (block.getRenderingType() != null) {
                Intrinsics.checkNotNullExpressionValue(block, "block");
                convertBlock(entity, block, getConverter(block), arrayList);
            } else {
                Log.error(this.TAG, Intrinsics.stringPlus("RenderingType is null: ", block.getTitle()));
            }
        }
        return arrayList;
    }

    public final boolean containsPopularSongsTag(List<String> tags) {
        if (tags != null) {
            Iterator<String> it = tags.iterator();
            while (it.hasNext()) {
                if (StringsKt__StringsKt.contains$default((CharSequence) it.next(), (CharSequence) "artist-popular-songs", false, 2, (Object) null)) {
                    return true;
                }
            }
        }
        return false;
    }
}
