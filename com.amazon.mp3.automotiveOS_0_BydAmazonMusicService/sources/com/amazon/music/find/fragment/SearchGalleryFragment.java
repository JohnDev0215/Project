package com.amazon.music.find.fragment;

import CoreInterface.v1_0.Method;
import Touch.GalleryTemplateInterface.v1_0.GalleryTemplate;
import Touch.GalleryTemplateInterface.v1_0.ImmutableGalleryTemplate;
import Touch.LiveStreamPlayerTemplateInterface.v1_0.DisplayGenericErrorMethod;
import Touch.PageInterface.v1_0.AddVerticalItemsToLastWidgetMethod;
import Touch.PageInterface.v1_0.AddWidgetsToTemplateMethod;
import Touch.WidgetsInterface.v1_0.MessageElement;
import Touch.WidgetsInterface.v1_0.VerticalItemElement;
import Touch.WidgetsInterface.v1_0.VisualListingElement;
import Touch.WidgetsInterface.v1_0.WidgetElement;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.core.content.ContextCompat;
import androidx.core.view.KeyEventDispatcher;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.skyfire.common.Refreshable;
import com.amazon.music.skyfire.ui.R;
import com.amazon.music.skyfire.ui.fragment.TemplateFragment;
import com.amazon.music.skyfire.ui.ktx.SkyFireKt;
import com.amazon.music.skyfire.ui.ktx.ViewGroupKt;
import com.amazon.music.skyfire.ui.ktx.ViewKt;
import com.amazon.music.skyfire.ui.providers.ElementBindingProvider;
import com.amazon.music.skyfire.ui.skyfire.Mapper;
import com.amazon.music.skyfire.ui.skyfire.SkillMethodBuilder;
import com.amazon.music.skyfire.ui.utils.FadingBackdropHelper;
import com.amazon.music.skyfire.ui.utils.ViewFinder;
import com.amazon.music.skyfire.ui.utils.ViewFinderKt;
import com.amazon.music.skyfire.ui.widgets.adapters.WidgetElementAdapter;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.presentation.ActionBarOverlayable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsJvmKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchGalleryFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0002H\u0002J\u0010\u0010\"\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0002H\u0002J\b\u0010#\u001a\u00020 H\u0002J \u0010$\u001a\u00020 2\u0006\u0010%\u001a\u00020\u00122\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020'H\u0016J&\u0010)\u001a\u0004\u0018\u00010\b2\u0006\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J\b\u00100\u001a\u00020 H\u0016J\u0010\u00101\u001a\u00020 2\u0006\u0010\u001d\u001a\u000202H\u0016J\b\u00103\u001a\u00020 H\u0016J\u001a\u00104\u001a\u00020 2\u0006\u00105\u001a\u00020\b2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J\b\u00106\u001a\u00020 H\u0004J(\u00107\u001a\u00020 2\b\u00108\u001a\u0004\u0018\u0001092\u0014\u0010:\u001a\u0010\u0012\u0004\u0012\u000209\u0012\u0004\u0012\u000209\u0018\u00010;H\u0016J$\u0010<\u001a\b\u0012\u0004\u0012\u00020\u001b0=2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u001b0=2\u0006\u0010\u001d\u001a\u00020?H\u0002R\u0012\u0010\u0005\u001a\u00020\u00068\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00020\b8\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0011\u001a\u00020\u00128\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u0012\u0010\u0013\u001a\u00020\u00148\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u0012\u0010\u0015\u001a\u00020\u00168\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u0012\u0010\u0017\u001a\u00020\u00188\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006@"}, d2 = {"Lcom/amazon/music/find/fragment/SearchGalleryFragment;", "Lcom/amazon/music/skyfire/ui/fragment/TemplateFragment;", "LTouch/GalleryTemplateInterface/v1_0/GalleryTemplate;", "Lcom/amazon/music/find/skyfire/common/Refreshable;", "()V", "backdrop", "Landroid/widget/ImageView;", "errorView", "Landroid/view/View;", "fadingBackdropHelper", "Lcom/amazon/music/skyfire/ui/utils/FadingBackdropHelper;", "inErrorState", "", "getInErrorState", "()Z", "setInErrorState", "(Z)V", "mainContent", "Landroidx/recyclerview/widget/RecyclerView;", "messageContainer", "Landroid/widget/FrameLayout;", "spinner", "Landroid/widget/ProgressBar;", "swipeRefreshLayout", "Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;", "widgetElementAdapter", "Lcom/amazon/music/skyfire/ui/widgets/adapters/WidgetElementAdapter;", "LTouch/WidgetsInterface/v1_0/WidgetElement;", "appendToLastWidget", "method", "LTouch/PageInterface/v1_0/AddVerticalItemsToLastWidgetMethod;", "configureCoreComponents", "", "template", "configureWidgetAdapter", "initSwipeToRefresh", "onContentScrolled", "recyclerView", "dx", "", "dy", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onHandleMethod", "LCoreInterface/v1_0/Method;", "onTemplateChanged", "onViewCreated", "view", "rebind", "refresh", "uri", "", "params", "", "updateWidgetList", "", "widgetElements", "LTouch/PageInterface/v1_0/AddWidgetsToTemplateMethod;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class SearchGalleryFragment extends TemplateFragment<GalleryTemplate> implements Refreshable {

    @ViewFinder.AView
    private ImageView backdrop;

    @ViewFinder.AView
    private View errorView;
    private FadingBackdropHelper fadingBackdropHelper;
    private boolean inErrorState;

    @ViewFinder.AView
    private RecyclerView mainContent;

    @ViewFinder.AView
    private FrameLayout messageContainer;

    @ViewFinder.AView
    private ProgressBar spinner;

    @ViewFinder.AView
    private SwipeRefreshLayout swipeRefreshLayout;
    private final WidgetElementAdapter<WidgetElement> widgetElementAdapter = new WidgetElementAdapter<>();

    public void onContentScrolled(RecyclerView recyclerView, int dx, int dy) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
    }

    protected final boolean getInErrorState() {
        return this.inErrorState;
    }

    protected final void setInErrorState(boolean z) {
        this.inErrorState = z;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate$default;
        StyleSheet styleSheet;
        Context context;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        RecyclerView recyclerView = null;
        View viewFindViews = (container == null || (viewInflate$default = ViewGroupKt.inflate$default(container, R.layout.fragment_gallery, false, 2, null)) == null) ? null : ViewFinderKt.findViews(viewInflate$default, this);
        if (viewFindViews == null || (styleSheet = getStyleSheet()) == null || (context = getContext()) == null) {
            return null;
        }
        ImageView imageView = this.backdrop;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backdrop");
            imageView = null;
        }
        RecyclerView recyclerView2 = this.mainContent;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainContent");
            recyclerView2 = null;
        }
        this.fadingBackdropHelper = new FadingBackdropHelper(imageView, recyclerView2, styleSheet);
        SwipeRefreshLayout swipeRefreshLayout = this.swipeRefreshLayout;
        if (swipeRefreshLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeRefreshLayout");
            swipeRefreshLayout = null;
        }
        swipeRefreshLayout.setColorSchemeResources(android.R.color.white);
        SwipeRefreshLayout swipeRefreshLayout2 = this.swipeRefreshLayout;
        if (swipeRefreshLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeRefreshLayout");
            swipeRefreshLayout2 = null;
        }
        swipeRefreshLayout2.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(context, R.color.glass_2));
        SwipeRefreshLayout swipeRefreshLayout3 = this.swipeRefreshLayout;
        if (swipeRefreshLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeRefreshLayout");
            swipeRefreshLayout3 = null;
        }
        swipeRefreshLayout3.setEnabled(false);
        RecyclerView recyclerView3 = this.mainContent;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainContent");
            recyclerView3 = null;
        }
        recyclerView3.setLayoutManager(new LinearLayoutManager(context, 1, false));
        this.widgetElementAdapter.setStyleSheet(styleSheet);
        this.widgetElementAdapter.setElementBindingProvider(getElementBindingProvider());
        this.widgetElementAdapter.setDispatcherContext(this);
        RecyclerView recyclerView4 = this.mainContent;
        if (recyclerView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainContent");
            recyclerView4 = null;
        }
        recyclerView4.setAdapter(this.widgetElementAdapter);
        RecyclerView recyclerView5 = this.mainContent;
        if (recyclerView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainContent");
        } else {
            recyclerView = recyclerView5;
        }
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.amazon.music.find.fragment.SearchGalleryFragment.onCreateView.1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView6, int dx, int dy) {
                Intrinsics.checkNotNullParameter(recyclerView6, "recyclerView");
                SearchGalleryFragment.this.onContentScrolled(recyclerView6, dx, dy);
            }
        });
        return viewFindViews;
    }

    @Override // com.amazon.music.skyfire.ui.fragment.TemplateFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        KeyEventDispatcher.Component activity = getActivity();
        ActionBarOverlayable actionBarOverlayable = activity instanceof ActionBarOverlayable ? (ActionBarOverlayable) activity : null;
        if (actionBarOverlayable != null) {
            actionBarOverlayable.setActionBarOverlay(true);
        }
        rebind();
    }

    @Override // com.amazon.music.skyfire.ui.fragment.TemplateFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        KeyEventDispatcher.Component activity = getActivity();
        ActionBarOverlayable actionBarOverlayable = activity instanceof ActionBarOverlayable ? (ActionBarOverlayable) activity : null;
        if (actionBarOverlayable != null) {
            actionBarOverlayable.setActionBarOverlay(false);
        }
        super.onDestroyView();
    }

    @Override // com.amazon.music.skyfire.ui.fragment.TemplateFragment
    public void onTemplateChanged() {
        this.inErrorState = false;
        rebind();
    }

    @Override // com.amazon.music.skyfire.ui.fragment.TemplateFragment, com.amazon.music.skyfire.ui.skyfire.TemplateMethodListener
    public void onHandleMethod(Method method) throws IllegalAccessException, NoSuchFieldException {
        Intrinsics.checkNotNullParameter(method, "method");
        if (method instanceof DisplayGenericErrorMethod) {
            getLogger().error(method.toString());
            this.inErrorState = true;
            rebind();
            return;
        }
        if (method instanceof AddWidgetsToTemplateMethod) {
            GalleryTemplate value = getTemplate().getValue();
            if (value == null) {
                return;
            }
            List<WidgetElement> listWidgetElements = value.widgetElements();
            Intrinsics.checkNotNullExpressionValue(listWidgetElements, "template.widgetElements()");
            List<WidgetElement> listUpdateWidgetList = updateWidgetList(listWidgetElements, (AddWidgetsToTemplateMethod) method);
            ImmutableGalleryTemplate immutableGalleryTemplateBuild = ImmutableGalleryTemplate.builder().from(value).widgetElements(listUpdateWidgetList).build();
            Intrinsics.checkNotNullExpressionValue(immutableGalleryTemplateBuild, "builder()\n              …                 .build()");
            overrideViewModelTemplate(immutableGalleryTemplateBuild);
            this.widgetElementAdapter.resetAndNotify(listUpdateWidgetList);
            return;
        }
        if (method instanceof AddVerticalItemsToLastWidgetMethod) {
            GalleryTemplate value2 = getTemplate().getValue();
            if (value2 == null) {
                return;
            }
            AddVerticalItemsToLastWidgetMethod addVerticalItemsToLastWidgetMethod = (AddVerticalItemsToLastWidgetMethod) method;
            if (appendToLastWidget(addVerticalItemsToLastWidgetMethod)) {
                configureWidgetAdapter(value2);
                List<Method> listOnViewed = addVerticalItemsToLastWidgetMethod.onViewed();
                Intrinsics.checkNotNullExpressionValue(listOnViewed, "method.onViewed()");
                SkyFireKt.dispatch$default(this, listOnViewed, (String) null, 2, (Object) null);
                return;
            }
            return;
        }
        super.onHandleMethod(method);
    }

    protected final void rebind() {
        GalleryTemplate value = getTemplate().getValue();
        SwipeRefreshLayout swipeRefreshLayout = this.swipeRefreshLayout;
        SwipeRefreshLayout swipeRefreshLayout2 = null;
        if (swipeRefreshLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeRefreshLayout");
            swipeRefreshLayout = null;
        }
        swipeRefreshLayout.setRefreshing(false);
        ImageView imageView = this.backdrop;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backdrop");
            imageView = null;
        }
        ViewKt.hideIf(imageView, this.inErrorState);
        ProgressBar progressBar = this.spinner;
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("spinner");
            progressBar = null;
        }
        ViewKt.collapseIf(progressBar, value != null || this.inErrorState);
        View view = this.errorView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("errorView");
            view = null;
        }
        ViewKt.collapseIf(view, !this.inErrorState);
        RecyclerView recyclerView = this.mainContent;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainContent");
            recyclerView = null;
        }
        ViewKt.hideIf(recyclerView, value == null || value.messageElement() != null || this.inErrorState);
        FrameLayout frameLayout = this.messageContainer;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("messageContainer");
            frameLayout = null;
        }
        ViewKt.collapseIf(frameLayout, (value == null ? null : value.messageElement()) == null);
        if (value == null) {
            if (this.inErrorState) {
                initSwipeToRefresh();
                return;
            }
            return;
        }
        configureCoreComponents(value);
        configureWidgetAdapter(value);
        if (value.canSwipeToRefresh()) {
            initSwipeToRefresh();
            return;
        }
        SwipeRefreshLayout swipeRefreshLayout3 = this.swipeRefreshLayout;
        if (swipeRefreshLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeRefreshLayout");
        } else {
            swipeRefreshLayout2 = swipeRefreshLayout3;
        }
        swipeRefreshLayout2.setEnabled(false);
    }

    private final void configureCoreComponents(GalleryTemplate template) {
        MessageElement messageElement = template.messageElement();
        FrameLayout frameLayout = null;
        FadingBackdropHelper fadingBackdropHelper = null;
        if (messageElement != null) {
            FrameLayout frameLayout2 = this.messageContainer;
            if (frameLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("messageContainer");
                frameLayout2 = null;
            }
            frameLayout2.removeAllViews();
            ElementBindingProvider elementBindingProvider = getElementBindingProvider();
            FrameLayout frameLayout3 = this.messageContainer;
            if (frameLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("messageContainer");
            } else {
                frameLayout = frameLayout3;
            }
            elementBindingProvider.attachView(messageElement, frameLayout);
            return;
        }
        String strBackgroundImage = template.backgroundImage();
        if (strBackgroundImage == null) {
            return;
        }
        FadingBackdropHelper fadingBackdropHelper2 = this.fadingBackdropHelper;
        if (fadingBackdropHelper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fadingBackdropHelper");
        } else {
            fadingBackdropHelper = fadingBackdropHelper2;
        }
        fadingBackdropHelper.setBackdropURL(strBackgroundImage);
    }

    private final void configureWidgetAdapter(GalleryTemplate template) {
        List<Method> listOnEndOfWidget;
        List<WidgetElement> widgetElements = template.widgetElements();
        this.widgetElementAdapter.setOnEndOfList(null);
        Intrinsics.checkNotNullExpressionValue(widgetElements, "widgetElements");
        VisualListingElement visualListingElement = (VisualListingElement) CollectionsKt___CollectionsKt.firstOrNull(CollectionsKt___CollectionsJvmKt.filterIsInstance(widgetElements, VisualListingElement.class));
        if (visualListingElement != null && (listOnEndOfWidget = visualListingElement.onEndOfWidget()) != null) {
            this.widgetElementAdapter.setOnEndOfList(listOnEndOfWidget);
        }
        this.widgetElementAdapter.resetAndNotify(widgetElements);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final List<WidgetElement> updateWidgetList(List<? extends WidgetElement> widgetElements, AddWidgetsToTemplateMethod method) {
        Collection<? extends WidgetElement> newWidgets;
        if (method.shouldReplaceExistingWidgets()) {
            newWidgets = method.widgets();
        } else {
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(widgetElements, 10));
            Iterator<T> it = widgetElements.iterator();
            while (it.hasNext()) {
                arrayList.add(((WidgetElement) it.next()).widgetId());
            }
            Set set = CollectionsKt___CollectionsKt.toSet(arrayList);
            List<WidgetElement> listWidgets = method.widgets();
            Intrinsics.checkNotNullExpressionValue(listWidgets, "method.widgets()");
            newWidgets = new ArrayList<>();
            for (Object obj : listWidgets) {
                if (!set.contains(((WidgetElement) obj).widgetId())) {
                    newWidgets.add(obj);
                }
            }
        }
        if (method.shouldReplaceExistingWidgets()) {
            List<WidgetElement> listWidgets2 = method.widgets();
            Intrinsics.checkNotNullExpressionValue(listWidgets2, "method\n                .widgets()");
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listWidgets2, 10));
            Iterator<T> it2 = listWidgets2.iterator();
            while (it2.hasNext()) {
                arrayList2.add(((WidgetElement) it2.next()).widgetId());
            }
            Set set2 = CollectionsKt___CollectionsKt.toSet(arrayList2);
            ArrayList arrayList3 = new ArrayList();
            for (Object obj2 : widgetElements) {
                if (!set2.contains(((WidgetElement) obj2).widgetId())) {
                    arrayList3.add(obj2);
                }
            }
            widgetElements = arrayList3;
        }
        List<WidgetElement> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) widgetElements);
        int size = 0;
        if (!Intrinsics.areEqual(method.widgetId(), "")) {
            Iterator<WidgetElement> it3 = mutableList.iterator();
            while (true) {
                if (!it3.hasNext()) {
                    size = -1;
                    break;
                }
                if (Intrinsics.areEqual(it3.next().widgetId(), method.widgetId())) {
                    break;
                }
                size++;
            }
            size = size < 0 ? mutableList.size() : size + 1;
        }
        Intrinsics.checkNotNullExpressionValue(newWidgets, "newWidgets");
        mutableList.addAll(size, newWidgets);
        return mutableList;
    }

    private final void initSwipeToRefresh() {
        SwipeRefreshLayout swipeRefreshLayout = this.swipeRefreshLayout;
        SwipeRefreshLayout swipeRefreshLayout2 = null;
        if (swipeRefreshLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeRefreshLayout");
            swipeRefreshLayout = null;
        }
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.amazon.music.find.fragment.SearchGalleryFragment$$ExternalSyntheticLambda0
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                SearchGalleryFragment.m2030initSwipeToRefresh$lambda8(this.f$0);
            }
        });
        SwipeRefreshLayout swipeRefreshLayout3 = this.swipeRefreshLayout;
        if (swipeRefreshLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeRefreshLayout");
        } else {
            swipeRefreshLayout2 = swipeRefreshLayout3;
        }
        swipeRefreshLayout2.setEnabled(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initSwipeToRefresh$lambda-8, reason: not valid java name */
    public static final void m2030initSwipeToRefresh$lambda8(SearchGalleryFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.fetchInitialMethods();
    }

    private final boolean appendToLastWidget(AddVerticalItemsToLastWidgetMethod method) throws IllegalAccessException, NoSuchFieldException {
        GalleryTemplate value = getTemplate().getValue();
        if (value == null) {
            return false;
        }
        List<WidgetElement> listWidgetElements = value.widgetElements();
        Intrinsics.checkNotNullExpressionValue(listWidgetElements, "template.widgetElements()");
        WidgetElement widgetElement = (WidgetElement) CollectionsKt___CollectionsKt.lastOrNull((List) listWidgetElements);
        if (!(widgetElement instanceof VisualListingElement)) {
            return false;
        }
        List<VerticalItemElement> listItems = ((VisualListingElement) widgetElement).items();
        Intrinsics.checkNotNullExpressionValue(listItems, "lastItem.items()");
        List<VerticalItemElement> listItems2 = method.items();
        Intrinsics.checkNotNullExpressionValue(listItems2, "method.items()");
        SkyFireKt.setValue(widgetElement, ContextMappingConstants.ITEMS, CollectionsKt___CollectionsKt.plus((Collection) listItems, (Iterable) listItems2));
        SkyFireKt.setValue(widgetElement, "onEndOfWidget", method.onEndOfWidget());
        return true;
    }

    @Override // com.amazon.music.find.skyfire.common.Refreshable
    public void refresh(final String uri, final Map<String, String> params) {
        Method methodBuildSkillMethod = SkyFireKt.buildSkillMethod(getSkyFireConfigurationProvider(), new Function1<SkillMethodBuilder, Unit>() { // from class: com.amazon.music.find.fragment.SearchGalleryFragment$refresh$invokeMethod$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SkillMethodBuilder skillMethodBuilder) {
                invoke2(skillMethodBuilder);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SkillMethodBuilder buildSkillMethod) {
                Intrinsics.checkNotNullParameter(buildSkillMethod, "$this$buildSkillMethod");
                buildSkillMethod.setSkillPath(uri);
                Map<String, String> queryParams = buildSkillMethod.getQueryParams();
                Map<String, String> mapEmptyMap = params;
                if (mapEmptyMap == null) {
                    mapEmptyMap = MapsKt__MapsKt.emptyMap();
                }
                queryParams.putAll(mapEmptyMap);
            }
        });
        Bundle arguments = getArguments();
        if (arguments != null) {
            Mapper mapper = Mapper.INSTANCE;
            arguments.putString(TemplateFragment.ARG_INITIAL_METHODS, mapper.getInstance().writerFor(mapper.getInstance().getTypeFactory().constructCollectionType(List.class, Method.class)).writeValueAsString(CollectionsKt__CollectionsJVMKt.listOf(methodBuildSkillMethod)));
        }
        resetTemplate();
        fetchInitialMethods();
    }
}
