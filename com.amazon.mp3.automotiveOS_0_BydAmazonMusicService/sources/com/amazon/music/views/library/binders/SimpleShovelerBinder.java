package com.amazon.music.views.library.binders;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.uicontentview.ContentViewedListener;
import com.amazon.music.views.library.models.SimpleShovelerModel;
import com.amazon.music.views.library.models.VerticalTileModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.providers.SeeMoreProvider;
import com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider;
import com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.SimpleSectionWidget;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: SimpleShovelerBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001Bc\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00122\u0010\u000e\u001a\u001a\u0012\u0016\b\u0001\u0012\u0012\u0012\u0006\b\u0001\u0012\u00020\u0011\u0012\u0006\b\u0001\u0012\u00020\u00120\u00100\u000f\"\u0012\u0012\u0006\b\u0001\u0012\u00020\u0011\u0012\u0006\b\u0001\u0012\u00020\u00120\u0010¢\u0006\u0002\u0010\u0013J\u0018\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u0002H\u0016J\u001e\u0010 \u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00032\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00120\"H\u0002J\u0010\u0010#\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0003H\u0002J\u0010\u0010$\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010%\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)H\u0002J\u0018\u0010*\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u0002H\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\rX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00020\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/amazon/music/views/library/binders/SimpleShovelerBinder;", "Lcom/amazon/music/views/library/binders/BaseViewContainerBinder;", "Lcom/amazon/music/views/library/models/SimpleShovelerModel;", "Lcom/amazon/music/views/library/views/SimpleSectionWidget;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "seeMoreProvider", "Lcom/amazon/music/views/library/providers/SeeMoreProvider;", "adapterProvider", "Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;", "contentViewedListener", "Lcom/amazon/music/uicontentview/ContentViewedListener;", "binders", "", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Landroid/view/View;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/providers/SeeMoreProvider;Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;Lcom/amazon/music/uicontentview/ContentViewedListener;[Lcom/amazon/music/views/library/binders/UniversalBinder;)V", "getContentViewedListener", "()Lcom/amazon/music/uicontentview/ContentViewedListener;", "gridLayoutUtils", "Lcom/amazon/music/views/library/binders/GridLayoutUtils;", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "", "view", "model", "bindModels", "models", "", "cleanWidget", "createView", "reportContentView", "identifier", "", "numItemsAccessible", "", "showView", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SimpleShovelerBinder extends BaseViewContainerBinder<SimpleShovelerModel, SimpleSectionWidget> {
    private final BaseViewModelAdapterProvider adapterProvider;
    private final ContentViewedListener contentViewedListener;
    private Context context;
    private final GridLayoutUtils gridLayoutUtils;
    private final SeeMoreProvider seeMoreProvider;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<SimpleShovelerModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(SimpleShovelerModel.class);
    }

    protected ContentViewedListener getContentViewedListener() {
        return this.contentViewedListener;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SimpleShovelerBinder(Context context, StyleSheet styleSheet, SeeMoreProvider seeMoreProvider, BaseViewModelAdapterProvider adapterProvider, ContentViewedListener contentViewedListener, UniversalBinder<? extends View, ? extends BaseViewModel>... binders) {
        super(styleSheet, contentViewedListener);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(adapterProvider, "adapterProvider");
        Intrinsics.checkNotNullParameter(contentViewedListener, "contentViewedListener");
        Intrinsics.checkNotNullParameter(binders, "binders");
        this.context = context;
        this.seeMoreProvider = seeMoreProvider;
        this.adapterProvider = adapterProvider;
        this.contentViewedListener = contentViewedListener;
        this.gridLayoutUtils = new GridLayoutUtils(context, styleSheet);
        buildBinders((UniversalBinder<? extends View, ? extends BaseViewModel>[]) Arrays.copyOf(binders, binders.length));
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public SimpleSectionWidget createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        SimpleSectionWidget simpleSectionWidget = new SimpleSectionWidget(context, getStyleSheet());
        simpleSectionWidget.setContentView(new RecyclerView(context));
        return simpleSectionWidget;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(SimpleSectionWidget view, final SimpleShovelerModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        cleanWidget(view);
        List<BaseViewModel> models = model.getModels();
        if (!(models == null || models.isEmpty())) {
            List<BaseViewModel> models2 = model.getModels();
            bindModels(view, models2);
            String title = model.getTitle();
            if (title != null) {
                view.setTitle(title);
            }
            if (!TextUtils.isEmpty(model.getMoreTarget()) && this.seeMoreProvider != null) {
                view.updateMoreButtonVisibility(true);
                view.setMoreButtonOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.SimpleShovelerBinder$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        SimpleShovelerBinder.m2364bind$lambda1(this.f$0, model, view2);
                    }
                });
            }
            String viewReferenceId = model.getViewReferenceId();
            if (viewReferenceId != null) {
                reportContentView(viewReferenceId, models2.size());
            }
            showView(view, model);
            return;
        }
        hideView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-1, reason: not valid java name */
    public static final void m2364bind$lambda1(SimpleShovelerBinder this$0, SimpleShovelerModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        this$0.seeMoreProvider.onSeeMoreClick(model.getMoreTarget(), model.getViewReferenceId(), model.getNextPageToken());
    }

    private final void showView(SimpleSectionWidget view, SimpleShovelerModel model) {
        RecyclerView recyclerView = view.getRecyclerView();
        if (recyclerView != null) {
            GridLayoutUtils.setLayoutManager$default(this.gridLayoutUtils, recyclerView, model.getTileType(), Integer.valueOf(model.getModels().size()), false, false, null, null, 120, null);
        }
        view.setVisibility(0);
    }

    private final void cleanWidget(SimpleSectionWidget view) {
        view.setMoreButtonOnClickListener(null);
        view.setTitle("");
        view.updateMoreButtonVisibility(false);
        view.setMoreButtonOnClickListener(null);
        UniversalAdapter adapter = view.getAdapter();
        if (adapter != null) {
            adapter.clear();
        }
        view.setInitialMargins();
        RecyclerView recyclerView = view.getRecyclerView();
        if (recyclerView == null) {
            return;
        }
        recyclerView.setPadding(0, 0, 0, 0);
    }

    private final void bindModels(SimpleSectionWidget view, List<? extends BaseViewModel> models) {
        for (BaseViewModel baseViewModel : models) {
            if (baseViewModel instanceof VerticalTileModel) {
                ((VerticalTileModel) baseViewModel).setSubTitle(null);
            }
        }
        UniversalAdapter adapter = view.getAdapter();
        if (adapter != null) {
            this.adapterProvider.updateItems(adapter, models);
            return;
        }
        List<UniversalBinder<? extends View, ? extends BaseViewModel>> binders = getBinders();
        if (binders == null) {
            return;
        }
        BaseViewModelAdapterProvider baseViewModelAdapterProvider = this.adapterProvider;
        Object[] array = binders.toArray(new UniversalBinder[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        UniversalBinder[] universalBinderArr = (UniversalBinder[]) array;
        view.setAdapter(baseViewModelAdapterProvider.getAdapter(models, (UniversalBinder[]) Arrays.copyOf(universalBinderArr, universalBinderArr.length)));
    }

    private final void reportContentView(String identifier, int numItemsAccessible) {
        HashMap map = new HashMap();
        map.put("blockRef", identifier);
        map.put("firstViewableIndex", "0");
        map.put("lastViewableIndex", String.valueOf(numItemsAccessible - 1));
        map.put("numItemsAccessible", String.valueOf(numItemsAccessible));
        getContentViewedListener().onItemRendered(identifier, map);
    }
}
