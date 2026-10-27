package com.amazon.mp3.brush.v3.converters;

import android.content.Context;
import com.amazon.mp3.AmazonApplication;
import com.amazon.music.page.api.model.Filter;
import com.amazon.music.page.api.model.Page;
import com.amazon.music.page.api.model.PageContent;
import com.amazon.music.page.api.model.Widget;
import com.amazon.music.views.library.converter.BaseContainerModelConverter;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: BrushV3PageGridConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 %2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001%B\u000f\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b#\u0010$J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\"\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0003\u001a\u00020\u00022\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u0016J\u0018\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0016R\u0017\u0010\r\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0013\u001a\n \u0012*\u0004\u0018\u00010\u00110\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0017\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001b\u0010\u001d\u001a\u00020\u00198BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u0016\u001a\u0004\b\u001b\u0010\u001cR\u001b\u0010\"\u001a\u00020\u001e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010\u0016\u001a\u0004\b \u0010!¨\u0006&"}, d2 = {"Lcom/amazon/mp3/brush/v3/converters/BrushV3PageGridConverter;", "Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "Lcom/amazon/music/page/api/model/Page;", "data", "", "isBaseDetailPage", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "children", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "convert", "getChildrenModels", "Landroid/content/Context;", "mContext", "Landroid/content/Context;", "getMContext", "()Landroid/content/Context;", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "LOG", "Lorg/slf4j/Logger;", "isTopVideoRecEnabled$delegate", "Lkotlin/Lazy;", "isTopVideoRecEnabled", "()Z", "Lcom/amazon/mp3/brush/v3/converters/BrushV3PageSeeMoreConverter;", "mSeeMoreConverter$delegate", "getMSeeMoreConverter", "()Lcom/amazon/mp3/brush/v3/converters/BrushV3PageSeeMoreConverter;", "mSeeMoreConverter", "Lcom/amazon/mp3/brush/v3/converters/BrushV3CarModePageSeeMoreConverter;", "carModeDetailsPageConverter$delegate", "getCarModeDetailsPageConverter", "()Lcom/amazon/mp3/brush/v3/converters/BrushV3CarModePageSeeMoreConverter;", "carModeDetailsPageConverter", "<init>", "(Landroid/content/Context;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushV3PageGridConverter implements BaseContainerModelConverter<Page> {
    private final Logger LOG;

    /* JADX INFO: renamed from: carModeDetailsPageConverter$delegate, reason: from kotlin metadata */
    private final Lazy carModeDetailsPageConverter;

    /* JADX INFO: renamed from: isTopVideoRecEnabled$delegate, reason: from kotlin metadata */
    private final Lazy isTopVideoRecEnabled;
    private final Context mContext;

    /* JADX INFO: renamed from: mSeeMoreConverter$delegate, reason: from kotlin metadata */
    private final Lazy mSeeMoreConverter;
    private static final Companion Companion = new Companion(null);

    @Deprecated
    private static final List<String> BASE_DETAIL_PAGE_URIS = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"uri://uhd", "uri://3d", "uri://ra360", "uri://atmos", "uri://playlists", "uri://new", "uri://popular"});

    public BrushV3PageGridConverter(Context mContext) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        this.mContext = mContext;
        this.LOG = LoggerFactory.getLogger((Class<?>) BrushV3PageGridConverter.class);
        this.isTopVideoRecEnabled = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.amazon.mp3.brush.v3.converters.BrushV3PageGridConverter.isTopVideoRecEnabled.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.valueOf(AmazonApplication.getCapabilities().isTopVideoRecEnabled());
            }
        });
        this.mSeeMoreConverter = LazyKt__LazyJVMKt.lazy(new Function0<BrushV3PageSeeMoreConverter>() { // from class: com.amazon.mp3.brush.v3.converters.BrushV3PageGridConverter$mSeeMoreConverter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BrushV3PageSeeMoreConverter invoke() {
                return new BrushV3PageSeeMoreConverter();
            }
        });
        this.carModeDetailsPageConverter = LazyKt__LazyJVMKt.lazy(new Function0<BrushV3CarModePageSeeMoreConverter>() { // from class: com.amazon.mp3.brush.v3.converters.BrushV3PageGridConverter$carModeDetailsPageConverter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BrushV3CarModePageSeeMoreConverter invoke() {
                return new BrushV3CarModePageSeeMoreConverter();
            }
        });
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public /* bridge */ /* synthetic */ BaseViewContainerModel convert(Page page, List list) {
        return convert2(page, (List<? extends BaseViewModel>) list);
    }

    private final boolean isTopVideoRecEnabled() {
        return ((Boolean) this.isTopVideoRecEnabled.getValue()).booleanValue();
    }

    private final BrushV3PageSeeMoreConverter getMSeeMoreConverter() {
        return (BrushV3PageSeeMoreConverter) this.mSeeMoreConverter.getValue();
    }

    private final BrushV3CarModePageSeeMoreConverter getCarModeDetailsPageConverter() {
        return (BrushV3CarModePageSeeMoreConverter) this.carModeDetailsPageConverter.getValue();
    }

    /* JADX INFO: renamed from: convert, reason: avoid collision after fix types in other method */
    public PageGridViewModel convert2(Page data, List<? extends BaseViewModel> children) {
        Intrinsics.checkNotNullParameter(data, "data");
        PageContent content = data.getContent();
        List<Widget> widgets = content == null ? null : content.getWidgets();
        if (getMSeeMoreConverter().isSeeMorePage(widgets)) {
            ArrayList arrayList = new ArrayList();
            BaseViewModel baseViewModelConvert = BrushV3ConverterFactory.INSTANCE.getGridListHeaderConverter().convert(data);
            if (baseViewModelConvert == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.models.base.BaseViewModel");
            }
            arrayList.add(baseViewModelConvert);
            if (children != null) {
                arrayList.addAll(children);
            }
            return getMSeeMoreConverter().convert2(data, (List<? extends BaseViewModel>) arrayList);
        }
        if (getCarModeDetailsPageConverter().isSeeMorePage(widgets)) {
            return getCarModeDetailsPageConverter().convert2(data, children);
        }
        List<Filter> filters = data.getFilters();
        if (children == null || children.isEmpty()) {
            return null;
        }
        String title = data.getTitle();
        String blockRef = data.getBlockRef();
        PageContent content2 = data.getContent();
        return new PageGridViewModel(blockRef, null, title, null, children, 2, 0, content2 == null ? null : content2.getNextToken(), BrushV3FilterConverter.INSTANCE.convert(filters), null, null, 1546, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00a2  */
    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<com.amazon.music.views.library.models.base.BaseViewModel> getChildrenModels(com.amazon.music.page.api.model.Page r11) {
        /*
            Method dump skipped, instruction units count: 483
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.brush.v3.converters.BrushV3PageGridConverter.getChildrenModels(com.amazon.music.page.api.model.Page):java.util.List");
    }

    private final boolean isBaseDetailPage(Page data) {
        List<String> list = BASE_DETAIL_PAGE_URIS;
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        for (String str : list) {
            String uri = data.getUri();
            Intrinsics.checkNotNullExpressionValue(uri, "data.uri");
            if (StringsKt__StringsJVMKt.startsWith$default(uri, str, false, 2, null)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: compiled from: BrushV3PageGridConverter.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/brush/v3/converters/BrushV3PageGridConverter$Companion;", "", "()V", "BASE_DETAIL_PAGE_URIS", "", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
