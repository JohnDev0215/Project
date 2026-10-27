package com.amazon.music.views.library.binders;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.uicontentview.ContentViewedListener;
import com.amazon.music.views.library.R$integer;
import com.amazon.music.views.library.models.PodcastShovelerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.providers.SeeMoreProvider;
import com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider;
import com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.SectionWidget;
import com.amazon.ui.foundations.styles.GridSize;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: PodcastShovelerBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 02\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00020\u0004:\u00010Bc\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u00122\u0010\u000f\u001a\u001a\u0012\u0016\b\u0001\u0012\u0012\u0012\u0006\b\u0001\u0012\u00020\u0012\u0012\u0006\b\u0001\u0012\u00020\u00130\u00110\u0010\"\u0012\u0012\u0006\b\u0001\u0012\u00020\u0012\u0012\u0006\b\u0001\u0012\u00020\u00130\u0011¢\u0006\u0002\u0010\u0014J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u0002H\u0016J\u001e\u0010\u001f\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00032\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00130!H\u0002J\u0010\u0010\"\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0003H\u0002J\u0010\u0010#\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J \u0010$\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u00022\u0006\u0010%\u001a\u00020&H\u0016J\u0010\u0010'\u001a\u00020(2\u0006\u0010%\u001a\u00020&H\u0002J\u0010\u0010)\u001a\u00020(2\u0006\u0010\u001e\u001a\u00020\u0002H\u0002J\u0018\u0010*\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0002J\u0010\u0010/\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0003H\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000eX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lcom/amazon/music/views/library/binders/PodcastShovelerBinder;", "Lcom/amazon/music/views/library/binders/BaseViewContainerBinder;", "Lcom/amazon/music/views/library/models/PodcastShovelerModel;", "Lcom/amazon/music/views/library/views/SectionWidget;", "Lcom/amazon/music/views/library/binders/BaseStateChangeBinder;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "seeMoreProvider", "Lcom/amazon/music/views/library/providers/SeeMoreProvider;", "adapterProvider", "Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;", "contentViewedListener", "Lcom/amazon/music/uicontentview/ContentViewedListener;", "binders", "", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Landroid/view/View;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/providers/SeeMoreProvider;Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;Lcom/amazon/music/uicontentview/ContentViewedListener;[Lcom/amazon/music/views/library/binders/UniversalBinder;)V", "getContentViewedListener", "()Lcom/amazon/music/uicontentview/ContentViewedListener;", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "", "view", "model", "bindModels", "models", "", "cleanWidget", "createView", "handleStateChange", "payload", "", "isPodcastUserContentPayload", "", "isPodcastUserContentShoveler", "reportContentView", "identifier", "", "numItemsAccessible", "", "showView", "Companion", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PodcastShovelerBinder extends BaseViewContainerBinder<PodcastShovelerModel, SectionWidget> {
    private final BaseViewModelAdapterProvider adapterProvider;
    private final ContentViewedListener contentViewedListener;
    private Context context;
    private final SeeMoreProvider seeMoreProvider;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<PodcastShovelerModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(PodcastShovelerModel.class);
    }

    protected ContentViewedListener getContentViewedListener() {
        return this.contentViewedListener;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PodcastShovelerBinder(Context context, StyleSheet styleSheet, SeeMoreProvider seeMoreProvider, BaseViewModelAdapterProvider adapterProvider, ContentViewedListener contentViewedListener, UniversalBinder<? extends View, ? extends BaseViewModel>... binders) {
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
        buildBinders((UniversalBinder<? extends View, ? extends BaseViewModel>[]) Arrays.copyOf(binders, binders.length));
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public SectionWidget createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        SectionWidget sectionWidget = new SectionWidget(context, getStyleSheet());
        sectionWidget.setContentView(new RecyclerView(context));
        return sectionWidget;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(SectionWidget view, final PodcastShovelerModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        cleanWidget(view);
        if (!model.getModels().isEmpty()) {
            List<BaseViewModel> models = model.getModels();
            boolean z = model.getMoreText() != null;
            bindModels(view, models);
            String title = model.getTitle();
            if (title != null) {
                view.setTitle(title);
            }
            String subtitle = model.getSubtitle();
            if (subtitle != null) {
                view.setSubtitle(subtitle);
            }
            int integer = this.context.getResources().getInteger(R$integer.podcast_vertical_item_count);
            if (z && !TextUtils.isEmpty(model.getMoreTarget()) && models.size() > integer) {
                String moreText = model.getMoreText();
                Intrinsics.checkNotNull(moreText);
                SectionWidget.setMoreButtonText$default(view, moreText, false, 2, null);
                view.setMoreButtonOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.PodcastShovelerBinder$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        PodcastShovelerBinder.m2354bind$lambda2(this.f$0, model, view2);
                    }
                });
            } else {
                SectionWidget.setMoreButtonText$default(view, "", false, 2, null);
            }
            String viewReferenceId = model.getViewReferenceId();
            if (viewReferenceId != null) {
                reportContentView(viewReferenceId, models.size());
            }
            showView(view);
            return;
        }
        hideView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-2, reason: not valid java name */
    public static final void m2354bind$lambda2(PodcastShovelerBinder this$0, PodcastShovelerModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        SeeMoreProvider seeMoreProvider = this$0.seeMoreProvider;
        if (seeMoreProvider == null) {
            return;
        }
        seeMoreProvider.onSeeMoreClick(model.getMoreTarget(), model.getViewReferenceId(), model.getNextPageToken());
    }

    private final void showView(SectionWidget view) {
        RecyclerView recyclerView;
        Integer spacerInPixels = getStyleSheet().getSpacerInPixels(SpacerKey.LARGE);
        Integer spacerInPixels2 = getStyleSheet().getSpacerInPixels(SpacerKey.SMALL);
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        SectionWidget.setMargins$default(view, null, spacerInPixels, null, spacerInPixels2, null, 21, null);
        RecyclerView recyclerView2 = view.getRecyclerView();
        if ((recyclerView2 == null ? null : recyclerView2.getLayoutManager()) == null && (recyclerView = view.getRecyclerView()) != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), 0, false));
        }
        view.setVisibility(0);
        StyleSheet styleSheet = view.getStyleSheet();
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        GridSize gridSize = styleSheet.getGridSize(context);
        if (gridSize == null) {
            return;
        }
        SectionWidget.setTextMargins$default(view, Integer.valueOf(gridSize.getMarginSpace() + gridSize.getColumnSpace()), null, null, null, 14, null);
        RecyclerView recyclerView3 = view.getRecyclerView();
        if (recyclerView3 != null) {
            recyclerView3.setClipToPadding(false);
        }
        RecyclerView recyclerView4 = view.getRecyclerView();
        if (recyclerView4 == null) {
            return;
        }
        recyclerView4.setPadding(gridSize.getMarginSpace() + (gridSize.getColumnSpace() / 2), 0, 0, 0);
    }

    private final void cleanWidget(SectionWidget view) {
        view.setMoreButtonOnClickListener(null);
        view.setTitle("");
        view.setSubtitle("");
        SectionWidget.setMoreButtonText$default(view, "", false, 2, null);
        view.setMoreButtonOnClickListener(null);
        UniversalAdapter adapter = view.getAdapter();
        if (adapter != null) {
            adapter.clear();
        }
        RecyclerView recyclerView = view.getRecyclerView();
        if (recyclerView == null) {
            return;
        }
        recyclerView.setPadding(0, 0, 0, 0);
    }

    private final void bindModels(SectionWidget view, List<? extends BaseViewModel> models) {
        UniversalAdapter adapter = view.getAdapter();
        if (adapter != null) {
            adapter.clear();
            BaseViewModelAdapterProvider.addItems$default(this.adapterProvider, adapter, models, null, 4, null);
            adapter.notifyDataSetChanged();
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

    @Override // com.amazon.music.views.library.binders.BaseViewContainerBinder
    public void handleStateChange(SectionWidget view, PodcastShovelerModel model, Object payload) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(payload, "payload");
        if (isPodcastUserContentPayload(payload) && isPodcastUserContentShoveler(model)) {
            bind(view, model);
        }
    }

    private final void reportContentView(String identifier, int numItemsAccessible) {
        HashMap map = new HashMap();
        map.put("blockRef", identifier);
        String string = Integer.toString(0);
        Intrinsics.checkNotNullExpressionValue(string, "toString(0)");
        map.put("firstViewableIndex", string);
        String string2 = Integer.toString(numItemsAccessible - 1);
        Intrinsics.checkNotNullExpressionValue(string2, "toString(numItemsAccessible - 1)");
        map.put("lastViewableIndex", string2);
        String string3 = Integer.toString(numItemsAccessible);
        Intrinsics.checkNotNullExpressionValue(string3, "toString(numItemsAccessible)");
        map.put("numItemsAccessible", string3);
        getContentViewedListener().onItemRendered(identifier, map);
    }

    private final boolean isPodcastUserContentPayload(Object payload) {
        return Intrinsics.areEqual(payload, (Object) 10) || Intrinsics.areEqual(payload, (Object) 8) || Intrinsics.areEqual(payload, (Object) 9);
    }

    private final boolean isPodcastUserContentShoveler(PodcastShovelerModel model) {
        List<String> tags = model.getTags();
        if (!(tags != null && tags.contains("podcast-jump-back-in-episodes"))) {
            List<String> tags2 = model.getTags();
            if (!(tags2 != null && tags2.contains("podcast-followed-shows"))) {
                List<String> tags3 = model.getTags();
                if (!(tags3 != null && tags3.contains("podcast-saved-episodes"))) {
                    return false;
                }
            }
        }
        return true;
    }
}
