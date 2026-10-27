package com.amazon.music.skyfire.ui.providers.impl;

import CoreInterface.v1_0.Element;
import Touch.WidgetsInterface.v1_0.CircleHorizontalItemElement;
import Touch.WidgetsInterface.v1_0.CollectionHeaderElement;
import Touch.WidgetsInterface.v1_0.DescriptiveListingElement;
import Touch.WidgetsInterface.v1_0.DescriptiveShowcaseElement;
import Touch.WidgetsInterface.v1_0.DescriptiveTextWidgetElement;
import Touch.WidgetsInterface.v1_0.ExclusiveCarouselElement;
import Touch.WidgetsInterface.v1_0.FeaturedBannerElement;
import Touch.WidgetsInterface.v1_0.FeaturedCardElement;
import Touch.WidgetsInterface.v1_0.FeaturedHorizontalShovelerElement;
import Touch.WidgetsInterface.v1_0.FlexibleTextContainerElement;
import Touch.WidgetsInterface.v1_0.FullScreenButtonElement;
import Touch.WidgetsInterface.v1_0.FullScreenButtonWidgetElement;
import Touch.WidgetsInterface.v1_0.GenericHeaderElement;
import Touch.WidgetsInterface.v1_0.HTMLTextElement;
import Touch.WidgetsInterface.v1_0.HorizontalItemElement;
import Touch.WidgetsInterface.v1_0.IconButtonElement;
import Touch.WidgetsInterface.v1_0.InvertedRectangleVerticalItemElement;
import Touch.WidgetsInterface.v1_0.LiveStreamBarkerElement;
import Touch.WidgetsInterface.v1_0.MessageElement;
import Touch.WidgetsInterface.v1_0.NavigationBarSpacerElement;
import Touch.WidgetsInterface.v1_0.PillNavigatorWidgetElement;
import Touch.WidgetsInterface.v1_0.PillNavigatorWidgetItemElement;
import Touch.WidgetsInterface.v1_0.PlainIconButtonElement;
import Touch.WidgetsInterface.v1_0.PlainTextElement;
import Touch.WidgetsInterface.v1_0.RefinementButtonElement;
import Touch.WidgetsInterface.v1_0.RefinementCollectionElement;
import Touch.WidgetsInterface.v1_0.SecondaryGenericHeaderElement;
import Touch.WidgetsInterface.v1_0.SectionElement;
import Touch.WidgetsInterface.v1_0.SelectionElement;
import Touch.WidgetsInterface.v1_0.SelectorElement;
import Touch.WidgetsInterface.v1_0.SpotlightCarouselElement;
import Touch.WidgetsInterface.v1_0.StaticCarouselHeaderElement;
import Touch.WidgetsInterface.v1_0.TextButtonElement;
import Touch.WidgetsInterface.v1_0.ThumbnailNavigatorItemElement;
import Touch.WidgetsInterface.v1_0.ThumbnailNavigatorWidgetElement;
import Touch.WidgetsInterface.v1_0.VerticalItemElement;
import Touch.WidgetsInterface.v1_0.VisualShovelerElement;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.amazon.music.skyfire.ui.ktx.ViewGroupKt;
import com.amazon.music.skyfire.ui.providers.DownloadProvider;
import com.amazon.music.skyfire.ui.providers.ElementBindingProvider;
import com.amazon.music.skyfire.ui.providers.StyleSheetProvider;
import com.amazon.music.skyfire.ui.providers.UserSubscriptionProvider;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.skyfire.ui.widgets.binders.CircleHorizontalItemBinder;
import com.amazon.music.skyfire.ui.widgets.binders.CollectionHeaderBinder;
import com.amazon.music.skyfire.ui.widgets.binders.DescriptiveListingBinder;
import com.amazon.music.skyfire.ui.widgets.binders.DescriptiveShowcaseBinder;
import com.amazon.music.skyfire.ui.widgets.binders.DescriptiveTextBinder;
import com.amazon.music.skyfire.ui.widgets.binders.ElementBinder;
import com.amazon.music.skyfire.ui.widgets.binders.ExclusiveCarouselBinder;
import com.amazon.music.skyfire.ui.widgets.binders.FallbackBinder;
import com.amazon.music.skyfire.ui.widgets.binders.FeaturedBannerBinder;
import com.amazon.music.skyfire.ui.widgets.binders.FeaturedCardBinder;
import com.amazon.music.skyfire.ui.widgets.binders.FeaturedShovelerBinder;
import com.amazon.music.skyfire.ui.widgets.binders.FlexibleTextContainerBinder;
import com.amazon.music.skyfire.ui.widgets.binders.FullScreenButtonBinder;
import com.amazon.music.skyfire.ui.widgets.binders.FullScreenButtonWidgetBinder;
import com.amazon.music.skyfire.ui.widgets.binders.GenericHeaderBinder;
import com.amazon.music.skyfire.ui.widgets.binders.HTMLTextBinder;
import com.amazon.music.skyfire.ui.widgets.binders.HorizontalItemBinder;
import com.amazon.music.skyfire.ui.widgets.binders.IconButtonBinder;
import com.amazon.music.skyfire.ui.widgets.binders.InvertedRectangleVerticalItemBinder;
import com.amazon.music.skyfire.ui.widgets.binders.ItemElementBinder;
import com.amazon.music.skyfire.ui.widgets.binders.LiveStreamBarkerBinder;
import com.amazon.music.skyfire.ui.widgets.binders.MessageBinder;
import com.amazon.music.skyfire.ui.widgets.binders.NavigationBarSpacerBinder;
import com.amazon.music.skyfire.ui.widgets.binders.PillNavigatorBinder;
import com.amazon.music.skyfire.ui.widgets.binders.PillNavigatorItemBinder;
import com.amazon.music.skyfire.ui.widgets.binders.PlainIconButtonBinder;
import com.amazon.music.skyfire.ui.widgets.binders.PlainTextBinder;
import com.amazon.music.skyfire.ui.widgets.binders.RefinementButtonBinder;
import com.amazon.music.skyfire.ui.widgets.binders.RefinementCollectionBinder;
import com.amazon.music.skyfire.ui.widgets.binders.SecondaryGenericHeaderBinder;
import com.amazon.music.skyfire.ui.widgets.binders.SectionBinder;
import com.amazon.music.skyfire.ui.widgets.binders.SelectionBinder;
import com.amazon.music.skyfire.ui.widgets.binders.SelectorBinder;
import com.amazon.music.skyfire.ui.widgets.binders.SpotlightCarouselBinder;
import com.amazon.music.skyfire.ui.widgets.binders.StaticCarouselHeaderBinder;
import com.amazon.music.skyfire.ui.widgets.binders.TextButtonBinder;
import com.amazon.music.skyfire.ui.widgets.binders.ThumbnailNavigatorBinder;
import com.amazon.music.skyfire.ui.widgets.binders.ThumbnailNavigatorItemBinder;
import com.amazon.music.skyfire.ui.widgets.binders.VerticalItemBinder;
import com.amazon.music.skyfire.ui.widgets.binders.VisualListingRowBinder;
import com.amazon.music.skyfire.ui.widgets.binders.VisualShovelerBinder;
import com.amazon.music.skyfire.ui.widgets.elements.VisualListingRowElement;
import com.amazon.music.views.library.styles.StyleSheet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: DefaultElementBindingProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000e\b\u0016\u0018\u0000 C2\u00020\u0001:\u0002BCB\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u001a\u00100\u001a\u0004\u0018\u0001012\u0006\u00102\u001a\u00020\f2\u0006\u00103\u001a\u000204H\u0016J\u0018\u00105\u001a\u0002062\u0006\u00102\u001a\u00020\f2\u0006\u00107\u001a\u000201H\u0016J \u00108\u001a\u0002062\u0006\u00102\u001a\u00020\f2\u0006\u00107\u001a\u0002012\u0006\u00109\u001a\u00020\u000fH\u0016J\u001c\u0010:\u001a\u0004\u0018\u0001012\u0006\u00102\u001a\u00020\f2\b\u00103\u001a\u0004\u0018\u000104H\u0016J\u0018\u0010:\u001a\u0002012\u0006\u0010;\u001a\u00020\u000f2\u0006\u00103\u001a\u000204H\u0016J\u001e\u0010<\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u000201\u0018\u00010\u00132\u0006\u00102\u001a\u00020\fH\u0016J\u001a\u0010=\u001a\u0004\u0018\u00010\r2\u000e\u0010>\u001a\n\u0012\u0006\b\u0001\u0012\u00020\f0\u000bH\u0002J\u001e\u0010?\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u000201\u0018\u00010\u00132\u0006\u00102\u001a\u00020\fH\u0002J\u0018\u0010@\u001a\u00020\u000f2\u000e\u0010>\u001a\n\u0012\u0006\b\u0001\u0012\u00020\f0\u000bH\u0016J6\u0010A\u001a0\u0012,\u0012*\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\f0\u000b\u0012\u0014\u0012\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00130\u000b0\u0012j\u0002`\u00140\u0011H\u0014R\"\u0010\t\u001a\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\f0\u000b\u0012\u0004\u0012\u00020\r0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\r0\nX\u0082\u0004¢\u0006\u0002\n\u0000RI\u0010\u0010\u001a0\u0012,\u0012*\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\f0\u000b\u0012\u0014\u0012\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00130\u000b0\u0012j\u0002`\u00140\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR<\u0010\u001f\u001a0\u0012,\u0012*\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\f0\u000b\u0012\u0014\u0012\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00130\u000b0\u0012j\u0002`\u00140\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010 \u001a\u0004\u0018\u00010!X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0014\u0010(\u001a\u00020)8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/¨\u0006D"}, d2 = {"Lcom/amazon/music/skyfire/ui/providers/impl/DefaultElementBindingProvider;", "Lcom/amazon/music/skyfire/ui/providers/ElementBindingProvider;", "userSubscriptionProvider", "Lcom/amazon/music/skyfire/ui/providers/UserSubscriptionProvider;", "styleSheetProvider", "Lcom/amazon/music/skyfire/ui/providers/StyleSheetProvider;", "downloadProvider", "Lcom/amazon/music/skyfire/ui/providers/DownloadProvider;", "(Lcom/amazon/music/skyfire/ui/providers/UserSubscriptionProvider;Lcom/amazon/music/skyfire/ui/providers/StyleSheetProvider;Lcom/amazon/music/skyfire/ui/providers/DownloadProvider;)V", "binderCache", "", "Ljava/lang/Class;", "LCoreInterface/v1_0/Element;", "Lcom/amazon/music/skyfire/ui/providers/impl/DefaultElementBindingProvider$BinderSpec;", "binderIdCache", "", "binderTypeMap", "", "Lkotlin/Pair;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "Lcom/amazon/music/skyfire/ui/providers/impl/ElementBindingPair;", "getBinderTypeMap", "()Ljava/util/List;", "binderTypeMap$delegate", "Lkotlin/Lazy;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "defaultBinderTypeMap", "dispatcherContext", "Lcom/amazon/music/skyfire/ui/skyfire/DispatcherContext;", "getDispatcherContext", "()Lcom/amazon/music/skyfire/ui/skyfire/DispatcherContext;", "setDispatcherContext", "(Lcom/amazon/music/skyfire/ui/skyfire/DispatcherContext;)V", "getDownloadProvider", "()Lcom/amazon/music/skyfire/ui/providers/DownloadProvider;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheetProvider", "()Lcom/amazon/music/skyfire/ui/providers/StyleSheetProvider;", "getUserSubscriptionProvider", "()Lcom/amazon/music/skyfire/ui/providers/UserSubscriptionProvider;", "attachView", "Landroid/view/View;", "element", "parent", "Landroid/view/ViewGroup;", "bind", "", "view", "bindItem", "width", "createView", "viewType", "getBinder", "getBinderSpec", "elementType", "getOrCreateBinder", "getViewType", "initializeBindingPairs", "BinderSpec", "Companion", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class DefaultElementBindingProvider implements ElementBindingProvider {
    private static final Logger logger = LoggerFactory.getLogger(ElementBindingProvider.class.getSimpleName());
    private final Map<Class<? extends Element>, BinderSpec> binderCache;
    private final Map<Integer, BinderSpec> binderIdCache;

    /* JADX INFO: renamed from: binderTypeMap$delegate, reason: from kotlin metadata */
    private final Lazy binderTypeMap;
    private Context context;
    private final List<Pair<Class<? extends Element>, Class<? extends ElementBinder<?, ?>>>> defaultBinderTypeMap;
    private DispatcherContext dispatcherContext;
    private final DownloadProvider downloadProvider;
    private final StyleSheetProvider styleSheetProvider;
    private final UserSubscriptionProvider userSubscriptionProvider;

    public DefaultElementBindingProvider(UserSubscriptionProvider userSubscriptionProvider, StyleSheetProvider styleSheetProvider, DownloadProvider downloadProvider) {
        Intrinsics.checkNotNullParameter(userSubscriptionProvider, "userSubscriptionProvider");
        Intrinsics.checkNotNullParameter(styleSheetProvider, "styleSheetProvider");
        Intrinsics.checkNotNullParameter(downloadProvider, "downloadProvider");
        this.userSubscriptionProvider = userSubscriptionProvider;
        this.styleSheetProvider = styleSheetProvider;
        this.downloadProvider = downloadProvider;
        this.binderCache = new LinkedHashMap();
        this.binderIdCache = new LinkedHashMap();
        this.defaultBinderTypeMap = CollectionsKt__CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(ExclusiveCarouselElement.class, ExclusiveCarouselBinder.class), TuplesKt.to(NavigationBarSpacerElement.class, NavigationBarSpacerBinder.class), TuplesKt.to(FeaturedCardElement.class, FeaturedCardBinder.class), TuplesKt.to(FeaturedBannerElement.class, FeaturedBannerBinder.class), TuplesKt.to(VisualListingRowElement.class, VisualListingRowBinder.class), TuplesKt.to(RefinementButtonElement.class, RefinementButtonBinder.class), TuplesKt.to(RefinementCollectionElement.class, RefinementCollectionBinder.class), TuplesKt.to(FlexibleTextContainerElement.class, FlexibleTextContainerBinder.class), TuplesKt.to(DescriptiveTextWidgetElement.class, DescriptiveTextBinder.class), TuplesKt.to(MessageElement.class, MessageBinder.class), TuplesKt.to(GenericHeaderElement.class, GenericHeaderBinder.class), TuplesKt.to(CollectionHeaderElement.class, CollectionHeaderBinder.class), TuplesKt.to(StaticCarouselHeaderElement.class, StaticCarouselHeaderBinder.class), TuplesKt.to(SecondaryGenericHeaderElement.class, SecondaryGenericHeaderBinder.class), TuplesKt.to(FullScreenButtonWidgetElement.class, FullScreenButtonWidgetBinder.class), TuplesKt.to(FullScreenButtonElement.class, FullScreenButtonBinder.class), TuplesKt.to(HTMLTextElement.class, HTMLTextBinder.class), TuplesKt.to(PlainTextElement.class, PlainTextBinder.class), TuplesKt.to(TextButtonElement.class, TextButtonBinder.class), TuplesKt.to(PlainIconButtonElement.class, PlainIconButtonBinder.class), TuplesKt.to(DescriptiveShowcaseElement.class, DescriptiveShowcaseBinder.class), TuplesKt.to(FeaturedHorizontalShovelerElement.class, FeaturedShovelerBinder.class), TuplesKt.to(VisualShovelerElement.class, VisualShovelerBinder.class), TuplesKt.to(InvertedRectangleVerticalItemElement.class, InvertedRectangleVerticalItemBinder.class), TuplesKt.to(VerticalItemElement.class, VerticalItemBinder.class), TuplesKt.to(CircleHorizontalItemElement.class, CircleHorizontalItemBinder.class), TuplesKt.to(HorizontalItemElement.class, HorizontalItemBinder.class), TuplesKt.to(PillNavigatorWidgetElement.class, PillNavigatorBinder.class), TuplesKt.to(PillNavigatorWidgetItemElement.class, PillNavigatorItemBinder.class), TuplesKt.to(ThumbnailNavigatorWidgetElement.class, ThumbnailNavigatorBinder.class), TuplesKt.to(ThumbnailNavigatorItemElement.class, ThumbnailNavigatorItemBinder.class), TuplesKt.to(SectionElement.class, SectionBinder.class), TuplesKt.to(SelectorElement.class, SelectorBinder.class), TuplesKt.to(SelectionElement.class, SelectionBinder.class), TuplesKt.to(SpotlightCarouselElement.class, SpotlightCarouselBinder.class), TuplesKt.to(LiveStreamBarkerElement.class, LiveStreamBarkerBinder.class), TuplesKt.to(IconButtonElement.class, IconButtonBinder.class), TuplesKt.to(DescriptiveListingElement.class, DescriptiveListingBinder.class), TuplesKt.to(Element.class, FallbackBinder.class)});
        this.binderTypeMap = LazyKt__LazyJVMKt.lazy(new Function0<List<? extends Pair<? extends Class<? extends Element>, ? extends Class<? extends ElementBinder<?, ?>>>>>() { // from class: com.amazon.music.skyfire.ui.providers.impl.DefaultElementBindingProvider$binderTypeMap$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends Pair<? extends Class<? extends Element>, ? extends Class<? extends ElementBinder<?, ?>>>> invoke() {
                return this.this$0.initializeBindingPairs();
            }
        });
    }

    public final UserSubscriptionProvider getUserSubscriptionProvider() {
        return this.userSubscriptionProvider;
    }

    public final StyleSheetProvider getStyleSheetProvider() {
        return this.styleSheetProvider;
    }

    public final DownloadProvider getDownloadProvider() {
        return this.downloadProvider;
    }

    private final List<Pair<Class<? extends Element>, Class<? extends ElementBinder<?, ?>>>> getBinderTypeMap() {
        return (List) this.binderTypeMap.getValue();
    }

    @Override // com.amazon.music.skyfire.ui.providers.ElementBindingProvider
    public Context getContext() {
        return this.context;
    }

    @Override // com.amazon.music.skyfire.ui.providers.ElementBindingProvider
    public void setContext(Context context) {
        this.context = context;
    }

    @Override // com.amazon.music.skyfire.ui.providers.ElementBindingProvider
    public DispatcherContext getDispatcherContext() {
        return this.dispatcherContext;
    }

    @Override // com.amazon.music.skyfire.ui.providers.ElementBindingProvider
    public void setDispatcherContext(DispatcherContext dispatcherContext) {
        this.dispatcherContext = dispatcherContext;
    }

    @Override // com.amazon.music.skyfire.ui.providers.ElementBindingProvider
    public StyleSheet getStyleSheet() {
        StyleSheet value = this.styleSheetProvider.getStyleSheet().getValue();
        Intrinsics.checkNotNull(value);
        Intrinsics.checkNotNullExpressionValue(value, "styleSheetProvider.styleSheet.value!!");
        return value;
    }

    @Override // com.amazon.music.skyfire.ui.providers.ElementBindingProvider
    public View createView(Element element, ViewGroup parent) throws IllegalAccessException, InstantiationException {
        Intrinsics.checkNotNullParameter(element, "element");
        ElementBinder<Element, View> orCreateBinder = getOrCreateBinder(element);
        if (orCreateBinder == null) {
            return null;
        }
        return orCreateBinder.createView(parent);
    }

    @Override // com.amazon.music.skyfire.ui.providers.ElementBindingProvider
    public View createView(int viewType, ViewGroup parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        BinderSpec binderSpec = this.binderIdCache.get(Integer.valueOf(viewType));
        ElementBinder<?, ?> binder = binderSpec == null ? null : binderSpec.getBinder();
        if (binder == null) {
            throw new IllegalStateException(Intrinsics.stringPlus("Missing binder for type ", Integer.valueOf(viewType)).toString());
        }
        return binder.createView(parent);
    }

    @Override // com.amazon.music.skyfire.ui.providers.ElementBindingProvider
    public void bind(Element element, View view) throws IllegalAccessException, InstantiationException {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        ElementBinder<Element, View> orCreateBinder = getOrCreateBinder(element);
        if (orCreateBinder == null) {
            return;
        }
        orCreateBinder.bind(element, view);
    }

    @Override // com.amazon.music.skyfire.ui.providers.ElementBindingProvider
    public void bindItem(Element element, View view, int width) throws IllegalAccessException, InstantiationException {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        ElementBinder<Element, View> orCreateBinder = getOrCreateBinder(element);
        ItemElementBinder itemElementBinder = orCreateBinder instanceof ItemElementBinder ? (ItemElementBinder) orCreateBinder : null;
        if (itemElementBinder == null) {
            return;
        }
        itemElementBinder.bind(element, view, width);
    }

    @Override // com.amazon.music.skyfire.ui.providers.ElementBindingProvider
    public View attachView(Element element, ViewGroup parent) throws IllegalAccessException, InstantiationException {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(parent, "parent");
        View viewCreateView = createView(element, parent);
        if (viewCreateView == null) {
            return null;
        }
        ViewGroupKt.plusAssign(parent, viewCreateView);
        bind(element, viewCreateView);
        return viewCreateView;
    }

    @Override // com.amazon.music.skyfire.ui.providers.ElementBindingProvider
    public ElementBinder<Element, View> getBinder(Element element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return getOrCreateBinder(element);
    }

    protected List<Pair<Class<? extends Element>, Class<? extends ElementBinder<?, ?>>>> initializeBindingPairs() {
        return this.defaultBinderTypeMap;
    }

    private final BinderSpec getBinderSpec(Class<? extends Element> elementType) throws IllegalAccessException, InstantiationException {
        BinderSpec binderSpec = this.binderCache.get(elementType);
        if (binderSpec == null) {
            int i = 0;
            Iterator<Pair<Class<? extends Element>, Class<? extends ElementBinder<?, ?>>>> it = getBinderTypeMap().iterator();
            while (true) {
                if (!it.hasNext()) {
                    i = -1;
                    break;
                }
                if (it.next().getFirst().isAssignableFrom(elementType)) {
                    break;
                }
                i++;
            }
            if (i != -1) {
                ElementBinder<?, ?> binder = getBinderTypeMap().get(i).getSecond().newInstance();
                binder.setBindingProvider(this);
                binder.setDownloadProvider(getDownloadProvider());
                Intrinsics.checkNotNullExpressionValue(binder, "binder");
                BinderSpec binderSpec2 = new BinderSpec(i, binder);
                this.binderCache.put(elementType, binderSpec2);
                this.binderIdCache.put(Integer.valueOf(i), binderSpec2);
                return binderSpec2;
            }
            logger.warn(Intrinsics.stringPlus("No fallback binder registered for ", elementType));
        }
        return binderSpec;
    }

    @Override // com.amazon.music.skyfire.ui.providers.ElementBindingProvider
    public int getViewType(Class<? extends Element> elementType) throws IllegalAccessException, InstantiationException {
        Intrinsics.checkNotNullParameter(elementType, "elementType");
        BinderSpec binderSpec = getBinderSpec(elementType);
        if (binderSpec == null) {
            return -1;
        }
        return binderSpec.getIndex();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [com.amazon.music.skyfire.ui.providers.impl.DefaultElementBindingProvider] */
    private final ElementBinder<Element, View> getOrCreateBinder(Element element) throws IllegalAccessException, InstantiationException {
        if (getContext() == null || getDispatcherContext() == null) {
            return null;
        }
        BinderSpec binderSpec = getBinderSpec(element.getClass());
        ElementBinder binder = binderSpec == null ? null : binderSpec.getBinder();
        if (binder instanceof ElementBinder) {
            return binder;
        }
        return null;
    }

    /* JADX INFO: compiled from: DefaultElementBindingProvider.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u0011\u0010\f\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0005HÆ\u0003J%\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0019\u0010\u0004\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/skyfire/ui/providers/impl/DefaultElementBindingProvider$BinderSpec;", "", "index", "", "binder", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "(ILcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;)V", "getBinder", "()Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "getIndex", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private static final /* data */ class BinderSpec {
        private final ElementBinder<?, ?> binder;
        private final int index;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ BinderSpec copy$default(BinderSpec binderSpec, int i, ElementBinder elementBinder, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = binderSpec.index;
            }
            if ((i2 & 2) != 0) {
                elementBinder = binderSpec.binder;
            }
            return binderSpec.copy(i, elementBinder);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getIndex() {
            return this.index;
        }

        public final ElementBinder<?, ?> component2() {
            return this.binder;
        }

        public final BinderSpec copy(int index, ElementBinder<?, ?> binder) {
            Intrinsics.checkNotNullParameter(binder, "binder");
            return new BinderSpec(index, binder);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BinderSpec)) {
                return false;
            }
            BinderSpec binderSpec = (BinderSpec) other;
            return this.index == binderSpec.index && Intrinsics.areEqual(this.binder, binderSpec.binder);
        }

        public int hashCode() {
            return (Integer.hashCode(this.index) * 31) + this.binder.hashCode();
        }

        public String toString() {
            return "BinderSpec(index=" + this.index + ", binder=" + this.binder + ')';
        }

        public BinderSpec(int i, ElementBinder<?, ?> binder) {
            Intrinsics.checkNotNullParameter(binder, "binder");
            this.index = i;
            this.binder = binder;
        }

        public final int getIndex() {
            return this.index;
        }

        public final ElementBinder<?, ?> getBinder() {
            return this.binder;
        }
    }
}
