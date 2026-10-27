package com.amazon.music.views.library.binders;

import android.content.Context;
import android.view.ViewGroup;
import com.amazon.music.uicontentview.ContentViewedListener;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.models.CarouselModel;
import com.amazon.music.views.library.models.FeatureBarkerModel;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.FeatureBarkerView;
import com.amazon.music.views.library.views.carousel.Carousel;
import com.amazon.music.views.library.views.carousel.CarouselAdapter;
import java.util.HashMap;
import java.util.List;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: CarouselBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 &2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001&B\u001f\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\u0006\u0010\u0018\u001a\u00020\u0017\u0012\u0006\u0010\u001b\u001a\u00020\u001a¢\u0006\u0004\b$\u0010%J\u0018\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u0010\u0010\f\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0018\u0010\u000f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0003H\u0016J\u001a\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\n2\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0010R\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0018\u001a\u00020\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001b\u001a\u00020\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\"\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u001d8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u001a\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00030 8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"¨\u0006'"}, d2 = {"Lcom/amazon/music/views/library/binders/CarouselBinder;", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Lcom/amazon/music/views/library/views/carousel/Carousel;", "Lcom/amazon/music/views/library/models/CarouselModel;", "", "identifier", "", "numItemsAccessible", "", "reportContentView", "Landroid/content/Context;", "context", "createView", "view", "carouselModel", "bind", "", "models", "Lcom/amazon/music/views/library/views/carousel/CarouselAdapter;", "createAdapter", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "Lcom/amazon/music/views/library/binders/FeatureBarkerBinder;", "featureBarkerBinder", "Lcom/amazon/music/views/library/binders/FeatureBarkerBinder;", "Lcom/amazon/music/uicontentview/ContentViewedListener;", "contentViewedListener", "Lcom/amazon/music/uicontentview/ContentViewedListener;", "Ljava/util/WeakHashMap;", "carousels", "Ljava/util/WeakHashMap;", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "modelClass", "<init>", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/binders/FeatureBarkerBinder;Lcom/amazon/music/uicontentview/ContentViewedListener;)V", "Companion", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class CarouselBinder implements UniversalBinder<Carousel, CarouselModel> {
    private WeakHashMap<Carousel, CarouselModel> carousels;
    private final ContentViewedListener contentViewedListener;
    private final FeatureBarkerBinder featureBarkerBinder;
    private final StyleSheet styleSheet;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<CarouselModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(CarouselModel.class);
    }

    public CarouselBinder(StyleSheet styleSheet, FeatureBarkerBinder featureBarkerBinder, ContentViewedListener contentViewedListener) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(featureBarkerBinder, "featureBarkerBinder");
        Intrinsics.checkNotNullParameter(contentViewedListener, "contentViewedListener");
        this.styleSheet = styleSheet;
        this.featureBarkerBinder = featureBarkerBinder;
        this.contentViewedListener = contentViewedListener;
        this.carousels = new WeakHashMap<>();
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public Carousel createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Carousel carousel = new Carousel(context);
        carousel.setItemPadding(context.getResources().getDimensionPixelSize(R$dimen.gutter));
        this.carousels.put(carousel, null);
        return carousel;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(Carousel view, CarouselModel carouselModel) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(carouselModel, "carouselModel");
        if (carouselModel == this.carousels.get(view)) {
            return;
        }
        Context context = view.getContext();
        view.setNumPrefetchViews(0);
        this.carousels.put(view, carouselModel);
        Integer spacerInDp = this.styleSheet.getSpacerInDp(SpacerKey.LARGE);
        int iIntValue = spacerInDp == null ? 0 : spacerInDp.intValue();
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-2, -2);
        marginLayoutParams.setMargins(0, iIntValue, 0, iIntValue);
        view.setLayoutParams(marginLayoutParams);
        if (carouselModel.getModels() != null) {
            List<FeatureBarkerModel> models = carouselModel.getModels();
            if (!models.isEmpty()) {
                String viewId = carouselModel.getViewId();
                if (viewId != null) {
                    this.featureBarkerBinder.setContainerBlockRef(viewId);
                    reportContentView(viewId, models.size());
                }
                CarouselAdapter adapter = view.getAdapter();
                if (adapter != null) {
                    adapter.clear();
                    adapter.addItems(models);
                    adapter.notifyDataSetChanged();
                } else {
                    Intrinsics.checkNotNullExpressionValue(context, "context");
                    CarouselAdapter carouselAdapterCreateAdapter = createAdapter(context, models);
                    carouselAdapterCreateAdapter.addItems(models);
                    view.setAdapter(carouselAdapterCreateAdapter);
                }
                view.stop();
                view.start();
                return;
            }
            return;
        }
        view.stop();
    }

    public final CarouselAdapter createAdapter(Context context, List<?> models) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(models, "models");
        return new CarouselAdapter(context, this, models) { // from class: com.amazon.music.views.library.binders.CarouselBinder.createAdapter.1
            final /* synthetic */ Context $context;
            final /* synthetic */ List<?> $models;
            final /* synthetic */ CarouselBinder this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(context);
                this.$context = context;
                this.this$0 = this;
                this.$models = models;
            }

            @Override // com.amazon.music.views.library.views.carousel.CarouselAdapter
            public FeatureBarkerView createView() {
                return this.this$0.featureBarkerBinder.createView(this.$context);
            }

            @Override // com.amazon.music.views.library.views.carousel.CarouselAdapter
            public void bindView(FeatureBarkerView view, FeatureBarkerModel model, int position) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(model, "model");
                this.this$0.featureBarkerBinder.setPosition(position);
                this.this$0.featureBarkerBinder.setNumItemsAccessible(this.$models.size());
                this.this$0.featureBarkerBinder.bind(view, model);
                String altDescription = model.getAltDescription();
                if (altDescription == null) {
                    altDescription = "";
                }
                view.setContentDescription(altDescription);
            }
        };
    }

    private final void reportContentView(String identifier, int numItemsAccessible) {
        HashMap map = new HashMap();
        map.put("blockRef", identifier);
        map.put("firstViewableIndex", "0");
        String string = Integer.toString(numItemsAccessible);
        Intrinsics.checkNotNullExpressionValue(string, "toString(numItemsAccessible)");
        map.put("numItemsAccessible", string);
        map.put("lastViewableIndex", "0");
        this.contentViewedListener.onItemRendered(identifier, map);
    }
}
