package com.amazon.music.skyfire.ui.fragment;

import CoreInterface.v1_0.Method;
import Touch.GalleryTemplateInterface.v1_0.GalleryTemplate;
import Touch.GalleryTemplateInterface.v1_0.ImmutableGalleryTemplate;
import Touch.LiveStreamPlayerTemplateInterface.v1_0.DisplayGenericErrorMethod;
import Touch.PageInterface.v1_0.AddVerticalItemsToLastWidgetMethod;
import Touch.PageInterface.v1_0.AddWidgetsToTemplateMethod;
import Touch.PageInterface.v1_0.RemoveWidgetsFromTemplateMethod;
import Touch.PageInterface.v1_0.ToggleWidgetsMethod;
import Touch.WidgetsInterface.v1_0.ButtonElement;
import Touch.WidgetsInterface.v1_0.MessageElement;
import Touch.WidgetsInterface.v1_0.NavigationBarElement;
import Touch.WidgetsInterface.v1_0.PlainIconButtonElement;
import Touch.WidgetsInterface.v1_0.RefinementCollectionElement;
import Touch.WidgetsInterface.v1_0.RetailHeaderElement;
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
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.skyfire.ui.R;
import com.amazon.music.skyfire.ui.ktx.SkyFireKt;
import com.amazon.music.skyfire.ui.ktx.ViewGroupKt;
import com.amazon.music.skyfire.ui.providers.ChromeProvider;
import com.amazon.music.skyfire.ui.providers.ElementBindingProvider;
import com.amazon.music.skyfire.ui.utils.FadingBackdropHelper;
import com.amazon.music.skyfire.ui.utils.SkyFireSkillPath;
import com.amazon.music.skyfire.ui.utils.ViewFinder;
import com.amazon.music.skyfire.ui.utils.ViewFinderKt;
import com.amazon.music.skyfire.ui.widgets.adapters.WidgetElementAdapter;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.ExposedRefinementListView;
import com.amazon.music.views.library.views.presentation.ActionBarOverlayable;
import com.amazon.music.views.library.views.presentation.TooltipExperienceDecorator;
import com.amazon.music.views.library.views.presentation.TranslucentActionBarDecorator;
import com.amazon.ui.foundations.ktx.ViewKt;
import com.amazon.ui.foundations.views.BaseButton;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsJvmKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: GalleryFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 U2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001UB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010&\u001a\u00020'H\u0002J\u0010\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020*H\u0002J\u0010\u0010+\u001a\u00020\u000f2\u0006\u0010,\u001a\u00020-H\u0002J\u0010\u0010.\u001a\u00020'2\u0006\u0010/\u001a\u00020\u0002H\u0002J\u0010\u00100\u001a\u00020'2\u0006\u0010/\u001a\u00020\u0002H\u0002J\u0010\u00101\u001a\u00020'2\u0006\u0010/\u001a\u00020\u0002H\u0002J\u0010\u00102\u001a\u00020'2\u0006\u0010/\u001a\u00020\u0002H\u0002J\n\u00103\u001a\u0004\u0018\u00010\u0005H\u0002J\u0010\u00104\u001a\u00020\u000f2\u0006\u0010/\u001a\u00020\u0002H\u0002J\b\u00105\u001a\u00020'H\u0002J\b\u00106\u001a\u00020'H\u0002J\u0010\u00107\u001a\u00020'2\u0006\u0010/\u001a\u00020\u0002H\u0016J \u00108\u001a\u00020'2\u0006\u00109\u001a\u00020\u00162\u0006\u0010:\u001a\u00020\u001a2\u0006\u0010;\u001a\u00020\u001aH\u0016J&\u0010<\u001a\u0004\u0018\u00010\u000b2\u0006\u0010=\u001a\u00020>2\b\u0010?\u001a\u0004\u0018\u00010@2\b\u0010A\u001a\u0004\u0018\u00010BH\u0016J\b\u0010C\u001a\u00020'H\u0016J\u0010\u0010D\u001a\u00020'2\u0006\u0010,\u001a\u00020EH\u0016J\b\u0010F\u001a\u00020'H\u0016J\b\u0010G\u001a\u00020'H\u0016J\b\u0010H\u001a\u00020'H\u0016J\u001a\u0010I\u001a\u00020'2\u0006\u0010J\u001a\u00020\u000b2\b\u0010A\u001a\u0004\u0018\u00010BH\u0016J\b\u0010K\u001a\u00020'H\u0004J\b\u0010L\u001a\u00020'H\u0002J\b\u0010M\u001a\u00020'H\u0002J\b\u0010N\u001a\u00020'H\u0002J$\u0010O\u001a\b\u0012\u0004\u0012\u00020%0P2\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020%0P2\u0006\u0010,\u001a\u00020RH\u0002J$\u0010S\u001a\b\u0012\u0004\u0012\u00020%0P2\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020%0P2\u0006\u0010,\u001a\u00020TH\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u00020\u00078\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00020\u000b8\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u00020\u000fX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0015\u001a\u00020\u00168\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u0012\u0010\u0017\u001a\u00020\u00188\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\u00020\u001a8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001f\u001a\u00020 8\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u0012\u0010!\u001a\u00020\"8\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u0014\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006V"}, d2 = {"Lcom/amazon/music/skyfire/ui/fragment/GalleryFragment;", "Lcom/amazon/music/skyfire/ui/fragment/MusicHomeFragment;", "LTouch/GalleryTemplateInterface/v1_0/GalleryTemplate;", "()V", "actionBarRefinementsView", "Lcom/amazon/music/views/library/views/ExposedRefinementListView;", "backdrop", "Landroid/widget/ImageView;", "bauhausTooltipExperience", "Lcom/amazon/music/views/library/views/presentation/TooltipExperienceDecorator;", "errorView", "Landroid/view/View;", "fadingBackdropHelper", "Lcom/amazon/music/skyfire/ui/utils/FadingBackdropHelper;", "inErrorState", "", "getInErrorState", "()Z", "setInErrorState", "(Z)V", "isFilterButtonActive", "mainContent", "Landroidx/recyclerview/widget/RecyclerView;", "messageContainer", "Landroid/widget/FrameLayout;", "refinementWidgetPosition", "", "getRefinementWidgetPosition", "()I", "smoothScroller", "Landroidx/recyclerview/widget/LinearSmoothScroller;", "spinner", "Landroid/widget/ProgressBar;", "swipeRefreshLayout", "Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;", "widgetElementAdapter", "Lcom/amazon/music/skyfire/ui/widgets/adapters/WidgetElementAdapter;", "LTouch/WidgetsInterface/v1_0/WidgetElement;", "addBauhausToolTip", "", "addRefinementsToActionBar", "element", "LTouch/WidgetsInterface/v1_0/RefinementCollectionElement;", "appendToLastWidget", "method", "LTouch/PageInterface/v1_0/AddVerticalItemsToLastWidgetMethod;", "configureActionBarButtons", "template", "configureActionBarTitle", "configureCoreComponents", "configureWidgetAdapter", "getRefinementWidget", "hasFilterButton", "hideFilterButton", "initSwipeToRefresh", "onConfigureActionBar", "onContentScrolled", "recyclerView", "dx", "dy", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onHandleMethod", "LCoreInterface/v1_0/Method;", "onPause", "onResume", "onTemplateChanged", "onViewCreated", "view", "rebind", "showFilterButton", "showOrHideRefinementsInActionBar", "smoothScrollToRefinementListAtTop", "updateWidgetList", "", "widgetElements", "LTouch/PageInterface/v1_0/AddWidgetsToTemplateMethod;", "updateWidgetListToRemove", "LTouch/PageInterface/v1_0/RemoveWidgetsFromTemplateMethod;", "Companion", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class GalleryFragment extends MusicHomeFragment<GalleryTemplate> {
    public static final String ARG_CONFIGURE_ACTIONBAR_TITLE = "com.amazon.music.skyfire.ui.fragment.GalleryFragment.configureActionBarTitle";
    public static final String ARG_IS_ACTIONBAR_OVERLAY = "com.amazon.music.skyfire.ui.fragment.GalleryFragment.isActionBarOverlay";
    public static final String ARTIST_FILTER_TOOL_TIPS_STATE_KEY = "ArtistFilterToolTip";
    public static final String FILTER_BUTTON_DESCRIPTION = "FILTER";
    private ExposedRefinementListView actionBarRefinementsView;

    @ViewFinder.AView
    private ImageView backdrop;
    private TooltipExperienceDecorator bauhausTooltipExperience;

    @ViewFinder.AView
    private View errorView;
    private FadingBackdropHelper fadingBackdropHelper;
    private boolean inErrorState;
    private boolean isFilterButtonActive;

    @ViewFinder.AView
    private RecyclerView mainContent;

    @ViewFinder.AView
    private FrameLayout messageContainer;
    private LinearSmoothScroller smoothScroller;

    @ViewFinder.AView
    private ProgressBar spinner;

    @ViewFinder.AView
    private SwipeRefreshLayout swipeRefreshLayout;
    private final WidgetElementAdapter<WidgetElement> widgetElementAdapter = new WidgetElementAdapter<>();

    protected final boolean getInErrorState() {
        return this.inErrorState;
    }

    protected final void setInErrorState(boolean z) {
        this.inErrorState = z;
    }

    private final int getRefinementWidgetPosition() {
        Integer numValueOf;
        GalleryTemplate galleryTemplate = (GalleryTemplate) getTemplate().getValue();
        if (galleryTemplate == null) {
            numValueOf = null;
        } else {
            List<WidgetElement> listWidgetElements = galleryTemplate.widgetElements();
            Intrinsics.checkNotNullExpressionValue(listWidgetElements, "it.widgetElements()");
            int i = 0;
            Iterator<WidgetElement> it = listWidgetElements.iterator();
            while (true) {
                if (!it.hasNext()) {
                    i = -1;
                    break;
                }
                if (it.next() instanceof RefinementCollectionElement) {
                    break;
                }
                i++;
            }
            numValueOf = Integer.valueOf(i);
        }
        if (numValueOf == null) {
            return -1;
        }
        return numValueOf.intValue();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate$default;
        StyleSheet styleSheet;
        final Context context;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        final RecyclerView recyclerView = null;
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
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(context, R.color.glass_2));
        swipeRefreshLayout.setEnabled(false);
        WidgetElementAdapter<WidgetElement> widgetElementAdapter = this.widgetElementAdapter;
        widgetElementAdapter.setStyleSheet(styleSheet);
        widgetElementAdapter.setElementBindingProvider(getElementBindingProvider());
        widgetElementAdapter.setDispatcherContext(this);
        RecyclerView recyclerView3 = this.mainContent;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainContent");
        } else {
            recyclerView = recyclerView3;
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(context, 1, false));
        recyclerView.setAdapter(this.widgetElementAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.amazon.music.skyfire.ui.fragment.GalleryFragment$onCreateView$3$1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView4, int dx, int dy) {
                Intrinsics.checkNotNullParameter(recyclerView4, "recyclerView");
                this.this$0.onContentScrolled(recyclerView4, dx, dy);
            }
        });
        this.smoothScroller = new LinearSmoothScroller(context, recyclerView) { // from class: com.amazon.music.skyfire.ui.fragment.GalleryFragment$onCreateView$3$2
            final /* synthetic */ Context $context;
            final /* synthetic */ RecyclerView $this_with;

            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            protected int getVerticalSnapPreference() {
                return 0;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(context);
                this.$context = context;
                this.$this_with = recyclerView;
            }

            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            public int calculateDtToFit(int viewStart, int viewEnd, int boxStart, int boxEnd, int snapPreference) {
                return ((boxStart - viewStart) + this.$this_with.getResources().getDimensionPixelSize(R.dimen.action_bar_height)) - 1;
            }
        };
        return viewFindViews;
    }

    @Override // com.amazon.music.skyfire.ui.fragment.MusicHomeFragment, com.amazon.music.skyfire.ui.fragment.TemplateFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        KeyEventDispatcher.Component activity = getActivity();
        ActionBarOverlayable actionBarOverlayable = activity instanceof ActionBarOverlayable ? (ActionBarOverlayable) activity : null;
        if (actionBarOverlayable != null) {
            Bundle arguments = getArguments();
            actionBarOverlayable.setActionBarOverlay(arguments != null ? arguments.getBoolean(ARG_IS_ACTIONBAR_OVERLAY, true) : true);
        }
        rebind();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        WidgetElementAdapter<WidgetElement> widgetElementAdapter = this.widgetElementAdapter;
        RecyclerView recyclerView = this.mainContent;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainContent");
            recyclerView = null;
        }
        widgetElementAdapter.onPause(recyclerView);
    }

    @Override // com.amazon.music.skyfire.ui.fragment.MusicHomeFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        WidgetElementAdapter<WidgetElement> widgetElementAdapter = this.widgetElementAdapter;
        RecyclerView recyclerView = this.mainContent;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainContent");
            recyclerView = null;
        }
        widgetElementAdapter.onResume(recyclerView);
    }

    @Override // com.amazon.music.skyfire.ui.fragment.TemplateFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        ExposedRefinementListView exposedRefinementListView = this.actionBarRefinementsView;
        if (exposedRefinementListView != null) {
            exposedRefinementListView.removeAllViews();
        }
        this.actionBarRefinementsView = null;
        KeyEventDispatcher.Component activity = getActivity();
        ActionBarOverlayable actionBarOverlayable = activity instanceof ActionBarOverlayable ? (ActionBarOverlayable) activity : null;
        if (actionBarOverlayable != null) {
            actionBarOverlayable.setActionBarOverlay(false);
        }
        TooltipExperienceDecorator tooltipExperienceDecorator = this.bauhausTooltipExperience;
        if (tooltipExperienceDecorator != null) {
            tooltipExperienceDecorator.onDestroy();
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
        Object obj = null;
        if (method instanceof ToggleWidgetsMethod) {
            if (this.isFilterButtonActive) {
                RemoveWidgetsFromTemplateMethod removeWidgetsFromTemplateMethodOnRemove = ((ToggleWidgetsMethod) method).onRemove();
                Intrinsics.checkNotNullExpressionValue(removeWidgetsFromTemplateMethodOnRemove, "method.onRemove()");
                SkyFireKt.dispatch$default(this, new Method[]{removeWidgetsFromTemplateMethodOnRemove}, (String) null, 2, (Object) null);
                this.isFilterButtonActive = false;
                return;
            }
            AddWidgetsToTemplateMethod addWidgetsToTemplateMethodOnAdd = ((ToggleWidgetsMethod) method).onAdd();
            Intrinsics.checkNotNullExpressionValue(addWidgetsToTemplateMethodOnAdd, "method.onAdd()");
            SkyFireKt.dispatch$default(this, new Method[]{addWidgetsToTemplateMethodOnAdd}, (String) null, 2, (Object) null);
            this.isFilterButtonActive = true;
            return;
        }
        if (method instanceof AddWidgetsToTemplateMethod) {
            GalleryTemplate galleryTemplate = (GalleryTemplate) getTemplate().getValue();
            if (galleryTemplate == null) {
                return;
            }
            List<WidgetElement> listWidgetElements = galleryTemplate.widgetElements();
            Intrinsics.checkNotNullExpressionValue(listWidgetElements, "template.widgetElements()");
            AddWidgetsToTemplateMethod addWidgetsToTemplateMethod = (AddWidgetsToTemplateMethod) method;
            List<WidgetElement> listUpdateWidgetList = updateWidgetList(listWidgetElements, addWidgetsToTemplateMethod);
            ImmutableGalleryTemplate immutableGalleryTemplateBuild = ImmutableGalleryTemplate.builder().from(galleryTemplate).widgetElements(listUpdateWidgetList).build();
            Intrinsics.checkNotNullExpressionValue(immutableGalleryTemplateBuild, "builder()\n              …                 .build()");
            overrideViewModelTemplate(immutableGalleryTemplateBuild);
            this.widgetElementAdapter.resetAndNotify(listUpdateWidgetList);
            if (addWidgetsToTemplateMethod.shouldScroll()) {
                List<WidgetElement> listWidgets = addWidgetsToTemplateMethod.widgets();
                if (listWidgets != null) {
                    Iterator<T> it = listWidgets.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Object next = it.next();
                        if (((WidgetElement) next) instanceof RefinementCollectionElement) {
                            obj = next;
                            break;
                        }
                    }
                    WidgetElement widgetElement = (WidgetElement) obj;
                    if (widgetElement != null) {
                        addRefinementsToActionBar((RefinementCollectionElement) widgetElement);
                    }
                }
                smoothScrollToRefinementListAtTop();
                TooltipExperienceDecorator tooltipExperienceDecorator = this.bauhausTooltipExperience;
                if (tooltipExperienceDecorator == null) {
                    return;
                }
                tooltipExperienceDecorator.dismissTooltip(true);
                return;
            }
            return;
        }
        if (method instanceof RemoveWidgetsFromTemplateMethod) {
            GalleryTemplate galleryTemplate2 = (GalleryTemplate) getTemplate().getValue();
            if (galleryTemplate2 == null) {
                return;
            }
            List<WidgetElement> listWidgetElements2 = galleryTemplate2.widgetElements();
            Intrinsics.checkNotNullExpressionValue(listWidgetElements2, "template.widgetElements()");
            List<WidgetElement> listUpdateWidgetListToRemove = updateWidgetListToRemove(listWidgetElements2, (RemoveWidgetsFromTemplateMethod) method);
            ImmutableGalleryTemplate immutableGalleryTemplateBuild2 = ImmutableGalleryTemplate.builder().from(galleryTemplate2).widgetElements(listUpdateWidgetListToRemove).build();
            Intrinsics.checkNotNullExpressionValue(immutableGalleryTemplateBuild2, "builder()\n              …                 .build()");
            overrideViewModelTemplate(immutableGalleryTemplateBuild2);
            this.widgetElementAdapter.resetAndNotify(listUpdateWidgetListToRemove);
            return;
        }
        if (method instanceof AddVerticalItemsToLastWidgetMethod) {
            GalleryTemplate galleryTemplate3 = (GalleryTemplate) getTemplate().getValue();
            if (galleryTemplate3 == null) {
                return;
            }
            AddVerticalItemsToLastWidgetMethod addVerticalItemsToLastWidgetMethod = (AddVerticalItemsToLastWidgetMethod) method;
            if (appendToLastWidget(addVerticalItemsToLastWidgetMethod)) {
                configureWidgetAdapter(galleryTemplate3);
                List<Method> listOnViewed = addVerticalItemsToLastWidgetMethod.onViewed();
                Intrinsics.checkNotNullExpressionValue(listOnViewed, "method.onViewed()");
                SkyFireKt.dispatch$default(this, listOnViewed, (String) null, 2, (Object) null);
                return;
            }
            return;
        }
        super.onHandleMethod(method);
    }

    private final void smoothScrollToRefinementListAtTop() {
        List<WidgetElement> listWidgetElements;
        Integer numValueOf;
        GalleryTemplate galleryTemplate = (GalleryTemplate) getTemplate().getValue();
        RecyclerView recyclerView = null;
        if (galleryTemplate == null || (listWidgetElements = galleryTemplate.widgetElements()) == null) {
            numValueOf = null;
        } else {
            int i = 0;
            Iterator<WidgetElement> it = listWidgetElements.iterator();
            while (true) {
                if (!it.hasNext()) {
                    i = -1;
                    break;
                } else if (it.next() instanceof RefinementCollectionElement) {
                    break;
                } else {
                    i++;
                }
            }
            numValueOf = Integer.valueOf(i);
        }
        if (numValueOf == null || numValueOf.intValue() == -1) {
            return;
        }
        LinearSmoothScroller linearSmoothScroller = this.smoothScroller;
        if (linearSmoothScroller != null) {
            linearSmoothScroller.setTargetPosition(numValueOf.intValue());
        }
        RecyclerView recyclerView2 = this.mainContent;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainContent");
        } else {
            recyclerView = recyclerView2;
        }
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager == null) {
            return;
        }
        layoutManager.startSmoothScroll(this.smoothScroller);
    }

    public void onConfigureActionBar(GalleryTemplate template) {
        Intrinsics.checkNotNullParameter(template, "template");
        configureActionBarTitle(template);
        configureActionBarButtons(template);
    }

    public void onContentScrolled(RecyclerView recyclerView, int dx, int dy) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        getActionBarView().updateBackgroundOpacity(recyclerView);
        if (getRefinementWidgetPosition() != -1) {
            showOrHideRefinementsInActionBar();
        }
    }

    protected final void rebind() {
        GalleryTemplate galleryTemplate = (GalleryTemplate) getTemplate().getValue();
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
        ViewKt.collapseIf(progressBar, galleryTemplate != null || this.inErrorState);
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
        ViewKt.hideIf(recyclerView, galleryTemplate == null || galleryTemplate.messageElement() != null || this.inErrorState);
        FrameLayout frameLayout = this.messageContainer;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("messageContainer");
            frameLayout = null;
        }
        ViewKt.collapseIf(frameLayout, (galleryTemplate == null ? null : galleryTemplate.messageElement()) == null);
        if (galleryTemplate == null) {
            if (this.inErrorState) {
                initSwipeToRefresh();
                return;
            }
            return;
        }
        if (getElementBindingProvider().getContext() != null) {
            onConfigureActionBar(galleryTemplate);
            configureCoreComponents(galleryTemplate);
            configureWidgetAdapter(galleryTemplate);
        }
        if (galleryTemplate.canSwipeToRefresh()) {
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

    private final void configureActionBarTitle(GalleryTemplate template) {
        String strPrimaryHeader = null;
        if (hasFilterButton(template)) {
            getActionBarView().setTitle(null);
            return;
        }
        TranslucentActionBarDecorator actionBarView = getActionBarView();
        Bundle arguments = getArguments();
        String string = arguments == null ? null : arguments.getString(ARG_CONFIGURE_ACTIONBAR_TITLE);
        if (string == null) {
            NavigationBarElement navigationBarElement = template.navigationBarElement();
            if (navigationBarElement != null) {
                strPrimaryHeader = navigationBarElement.primaryHeader();
            }
        } else {
            strPrimaryHeader = string;
        }
        actionBarView.setTitle(strPrimaryHeader);
    }

    private final boolean hasFilterButton(GalleryTemplate template) {
        NavigationBarElement navigationBarElement = template.navigationBarElement();
        if (!((navigationBarElement == null ? null : navigationBarElement.callToActionButton()) instanceof PlainIconButtonElement)) {
            return false;
        }
        NavigationBarElement navigationBarElement2 = template.navigationBarElement();
        ButtonElement buttonElementCallToActionButton = navigationBarElement2 != null ? navigationBarElement2.callToActionButton() : null;
        if (buttonElementCallToActionButton == null) {
            throw new NullPointerException("null cannot be cast to non-null type Touch.WidgetsInterface.v1_0.PlainIconButtonElement");
        }
        return Intrinsics.areEqual(((PlainIconButtonElement) buttonElementCallToActionButton).iconDescription(), "FILTER");
    }

    private final void configureActionBarButtons(GalleryTemplate template) {
        NavigationBarElement navigationBarElement = template.navigationBarElement();
        Object obj = null;
        if (navigationBarElement != null) {
            ButtonElement buttonElementCallToActionButton = navigationBarElement.callToActionButton();
            PlainIconButtonElement plainIconButtonElement = buttonElementCallToActionButton instanceof PlainIconButtonElement ? (PlainIconButtonElement) buttonElementCallToActionButton : null;
            BaseButton actionButton = getActionBarView().getActionButtonView();
            MaterialButton button = getActionBarView().getBackButtonView().getButton();
            button.setContentDescription(getString(R.string.content_description_back_button));
            button.sendAccessibilityEvent(8);
            boolean z = false;
            ViewKt.collapseIf(actionButton, plainIconButtonElement == null);
            if (plainIconButtonElement != null) {
                getElementBindingProvider().bind(plainIconButtonElement, actionButton);
            }
            String skillPath$default = TemplateFragment.getSkillPath$default(this, null, 1, null);
            if (Intrinsics.areEqual(getActionBarView().getActionButtonView().getButton().getContentDescription(), "FILTER")) {
                if (skillPath$default != null && StringsKt__StringsJVMKt.startsWith$default(skillPath$default, SkyFireSkillPath.showCatalogArtist.getPath(), false, 2, null)) {
                    z = true;
                }
                if (z) {
                    addBauhausToolTip();
                }
            }
        }
        List<WidgetElement> listWidgetElements = template.widgetElements();
        Intrinsics.checkNotNullExpressionValue(listWidgetElements, "template.widgetElements()");
        if (CollectionsKt___CollectionsKt.firstOrNull((List) listWidgetElements) instanceof RetailHeaderElement) {
            getActionBarView().setButtonBackgroundEnabled(true);
        }
        List<WidgetElement> listWidgetElements2 = template.widgetElements();
        Intrinsics.checkNotNullExpressionValue(listWidgetElements2, "template.widgetElements()");
        Iterator<T> it = listWidgetElements2.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (((WidgetElement) next) instanceof RefinementCollectionElement) {
                obj = next;
                break;
            }
        }
        WidgetElement widgetElement = (WidgetElement) obj;
        if (widgetElement == null) {
            return;
        }
        addRefinementsToActionBar((RefinementCollectionElement) widgetElement);
    }

    private final void addBauhausToolTip() {
        StyleSheet styleSheet = getStyleSheet();
        if (styleSheet != null) {
            Context context = getContext();
            TooltipExperienceDecorator tooltipExperienceDecoratorCreateBauhausTooltipExperienceDecorator = null;
            if (context != null) {
                ChromeProvider chromeProvider = getChromeProvider();
                String string = context.getResources().getString(R.string.dmusic_artist_detail_page_filter_tooltip_text);
                Intrinsics.checkNotNullExpressionValue(string, "c.resources.getString(R.…page_filter_tooltip_text)");
                PageType pageType = PageType.BROWSE_ARTIST_DETAIL;
                String metricsValue = ContentName.ARTIST_FILTER_TOOLTIP.getMetricsValue();
                Intrinsics.checkNotNullExpressionValue(metricsValue, "ARTIST_FILTER_TOOLTIP.getMetricValue()");
                RecyclerView recyclerView = this.mainContent;
                if (recyclerView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mainContent");
                    recyclerView = null;
                }
                tooltipExperienceDecoratorCreateBauhausTooltipExperienceDecorator = chromeProvider.createBauhausTooltipExperienceDecorator(ARTIST_FILTER_TOOL_TIPS_STATE_KEY, string, styleSheet, context, pageType, metricsValue, recyclerView, null, "0", 3, true);
            }
            this.bauhausTooltipExperience = tooltipExperienceDecoratorCreateBauhausTooltipExperienceDecorator;
        }
        StyleSheet styleSheet2 = getStyleSheet();
        if (styleSheet2 == null) {
            return;
        }
        TranslucentActionBarDecorator actionBarView = getActionBarView();
        TooltipExperienceDecorator tooltipExperienceDecorator = this.bauhausTooltipExperience;
        if (tooltipExperienceDecorator == null) {
            return;
        }
        tooltipExperienceDecorator.showTooltip(actionBarView.getActionButtonView(), styleSheet2);
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

    private final List<WidgetElement> updateWidgetListToRemove(List<? extends WidgetElement> widgetElements, RemoveWidgetsFromTemplateMethod method) {
        List<WidgetElement> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) widgetElements);
        if (method.widgetIds() != null) {
            List<String> listWidgetIds = method.widgetIds();
            Intrinsics.checkNotNull(listWidgetIds);
            for (String str : listWidgetIds) {
                Intrinsics.checkNotNullExpressionValue(str, "method.widgetIds()!!");
                String str2 = str;
                int i = 0;
                Iterator<? extends WidgetElement> it = widgetElements.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        i = -1;
                        break;
                    }
                    if (Intrinsics.areEqual(it.next().widgetId(), str2)) {
                        break;
                    }
                    i++;
                }
                if (i != -1) {
                    mutableList.remove(i);
                }
            }
        }
        return mutableList;
    }

    private final void addRefinementsToActionBar(RefinementCollectionElement element) {
        Object actionBarView = getActionBarView();
        View view = null;
        ViewGroup viewGroup = actionBarView instanceof ViewGroup ? (ViewGroup) actionBarView : null;
        if (viewGroup == null) {
            return;
        }
        Iterator<View> it = ViewGroupKt.getChildren(viewGroup).iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            View next = it.next();
            if (next instanceof ExposedRefinementListView) {
                view = next;
                break;
            }
        }
        View view2 = view;
        if (view2 != null) {
            viewGroup.removeView(view2);
        }
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.spacer_medium);
        View viewAttachView = getElementBindingProvider().attachView(element, viewGroup);
        if (viewAttachView != null) {
            viewAttachView.setPadding(viewAttachView.getPaddingLeft(), dimensionPixelSize, viewAttachView.getPaddingRight(), dimensionPixelSize);
            this.actionBarRefinementsView = (ExposedRefinementListView) viewAttachView;
        }
        showOrHideRefinementsInActionBar();
    }

    private final void showOrHideRefinementsInActionBar() {
        Unit unit;
        NavigationBarElement navigationBarElement;
        NavigationBarElement navigationBarElement2;
        ExposedRefinementListView refinementWidget = getRefinementWidget();
        String strPrimaryHeader = null;
        if (refinementWidget == null) {
            unit = null;
        } else {
            if (refinementWidget.getTop() < getResources().getDimensionPixelSize(R.dimen.action_bar_height)) {
                ExposedRefinementListView exposedRefinementListView = this.actionBarRefinementsView;
                if (exposedRefinementListView != null) {
                    exposedRefinementListView.setVisibility(0);
                }
                TranslucentActionBarDecorator actionBarView = getActionBarView();
                GalleryTemplate galleryTemplate = (GalleryTemplate) getTemplate().getValue();
                actionBarView.setTitle((galleryTemplate == null || (navigationBarElement = galleryTemplate.navigationBarElement()) == null) ? null : navigationBarElement.primaryHeader());
                refinementWidget.setVisibility(4);
                hideFilterButton();
            } else {
                ExposedRefinementListView exposedRefinementListView2 = this.actionBarRefinementsView;
                if (exposedRefinementListView2 != null) {
                    exposedRefinementListView2.setVisibility(8);
                }
                getActionBarView().setTitle(null);
                refinementWidget.setVisibility(0);
                showFilterButton();
            }
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            RecyclerView recyclerView = this.mainContent;
            if (recyclerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mainContent");
                recyclerView = null;
            }
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            LinearLayoutManager linearLayoutManager = layoutManager instanceof LinearLayoutManager ? (LinearLayoutManager) layoutManager : null;
            if (linearLayoutManager == null) {
                return;
            }
            if (linearLayoutManager.findFirstVisibleItemPosition() < 0 || linearLayoutManager.findFirstVisibleItemPosition() >= getRefinementWidgetPosition()) {
                ExposedRefinementListView exposedRefinementListView3 = this.actionBarRefinementsView;
                if (exposedRefinementListView3 != null) {
                    exposedRefinementListView3.setVisibility(0);
                }
                TranslucentActionBarDecorator actionBarView2 = getActionBarView();
                GalleryTemplate galleryTemplate2 = (GalleryTemplate) getTemplate().getValue();
                if (galleryTemplate2 != null && (navigationBarElement2 = galleryTemplate2.navigationBarElement()) != null) {
                    strPrimaryHeader = navigationBarElement2.primaryHeader();
                }
                actionBarView2.setTitle(strPrimaryHeader);
                hideFilterButton();
                return;
            }
            ExposedRefinementListView exposedRefinementListView4 = this.actionBarRefinementsView;
            if (exposedRefinementListView4 != null) {
                exposedRefinementListView4.setVisibility(8);
            }
            getActionBarView().setTitle(null);
            showFilterButton();
        }
    }

    private final void hideFilterButton() {
        if (Intrinsics.areEqual(getActionBarView().getActionButtonView().getButton().getContentDescription(), "FILTER")) {
            getActionBarView().getActionButtonView().getButton().setVisibility(4);
        }
    }

    private final void showFilterButton() {
        if (Intrinsics.areEqual(getActionBarView().getActionButtonView().getButton().getContentDescription(), "FILTER")) {
            getActionBarView().getActionButtonView().getButton().setVisibility(0);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.amazon.music.views.library.views.ExposedRefinementListView getRefinementWidget() {
        /*
            r4 = this;
            androidx.lifecycle.LiveData r0 = r4.getTemplate()
            java.lang.Object r0 = r0.getValue()
            Touch.GalleryTemplateInterface.v1_0.GalleryTemplate r0 = (Touch.GalleryTemplateInterface.v1_0.GalleryTemplate) r0
            r1 = 0
            if (r0 != 0) goto Le
            goto L38
        Le:
            java.util.List r0 = r0.widgetElements()
            if (r0 != 0) goto L16
        L14:
            r0 = r1
            goto L36
        L16:
            java.util.Iterator r0 = r0.iterator()
        L1a:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L2c
            java.lang.Object r2 = r0.next()
            r3 = r2
            Touch.WidgetsInterface.v1_0.WidgetElement r3 = (Touch.WidgetsInterface.v1_0.WidgetElement) r3
            boolean r3 = r3 instanceof Touch.WidgetsInterface.v1_0.RefinementCollectionElement
            if (r3 == 0) goto L1a
            goto L2d
        L2c:
            r2 = r1
        L2d:
            Touch.WidgetsInterface.v1_0.WidgetElement r2 = (Touch.WidgetsInterface.v1_0.WidgetElement) r2
            if (r2 != 0) goto L32
            goto L14
        L32:
            java.lang.String r0 = r2.widgetId()
        L36:
            if (r0 != 0) goto L3c
        L38:
            r0 = r1
            com.amazon.music.views.library.views.ExposedRefinementListView r0 = (com.amazon.music.views.library.views.ExposedRefinementListView) r0
            return r1
        L3c:
            androidx.recyclerview.widget.RecyclerView r2 = r4.mainContent
            if (r2 != 0) goto L46
            java.lang.String r2 = "mainContent"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r2 = r1
        L46:
            android.view.View r0 = r2.findViewWithTag(r0)
            boolean r2 = r0 instanceof com.amazon.music.views.library.views.ExposedRefinementListView
            if (r2 == 0) goto L51
            r1 = r0
            com.amazon.music.views.library.views.ExposedRefinementListView r1 = (com.amazon.music.views.library.views.ExposedRefinementListView) r1
        L51:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.skyfire.ui.fragment.GalleryFragment.getRefinementWidget():com.amazon.music.views.library.views.ExposedRefinementListView");
    }

    private final void initSwipeToRefresh() {
        SwipeRefreshLayout swipeRefreshLayout = this.swipeRefreshLayout;
        SwipeRefreshLayout swipeRefreshLayout2 = null;
        if (swipeRefreshLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeRefreshLayout");
            swipeRefreshLayout = null;
        }
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.amazon.music.skyfire.ui.fragment.GalleryFragment$$ExternalSyntheticLambda0
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                GalleryFragment.m2195initSwipeToRefresh$lambda38(this.f$0);
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
    /* JADX INFO: renamed from: initSwipeToRefresh$lambda-38, reason: not valid java name */
    public static final void m2195initSwipeToRefresh$lambda38(GalleryFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.fetchInitialMethods();
    }

    private final boolean appendToLastWidget(AddVerticalItemsToLastWidgetMethod method) throws IllegalAccessException, NoSuchFieldException {
        GalleryTemplate galleryTemplate = (GalleryTemplate) getTemplate().getValue();
        if (galleryTemplate == null) {
            return false;
        }
        List<WidgetElement> listWidgetElements = galleryTemplate.widgetElements();
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
}
