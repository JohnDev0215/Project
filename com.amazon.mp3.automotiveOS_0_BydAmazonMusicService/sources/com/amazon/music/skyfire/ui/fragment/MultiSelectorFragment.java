package com.amazon.music.skyfire.ui.fragment;

import CoreInterface.v1_0.Method;
import ExternalActionInterface.v1_0.TriggerExternalActionMethod;
import Touch.LiveStreamPlayerTemplateInterface.v1_0.DisplayGenericErrorMethod;
import Touch.MultiSelectorTemplateInterface.v1_0.ImmutableSelectedElementsClientInformation;
import Touch.MultiSelectorTemplateInterface.v1_0.MultiSelectorTemplate;
import Touch.MultiSelectorTemplateInterface.v1_0.TabNavigatorWidgetElement;
import Touch.MultiSelectorTemplateInterface.v1_0.TabNavigatorWidgetItemElement;
import Touch.MultiSelectorTemplateInterface.v1_0.TriggerExternalSelectElementMethod;
import Touch.WidgetsInterface.v1_0.ButtonElement;
import Touch.WidgetsInterface.v1_0.TextButtonElement;
import Touch.WidgetsInterface.v1_0.WidgetElement;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.view.KeyEventDispatcher;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.skyfire.ui.R;
import com.amazon.music.skyfire.ui.ktx.ViewGroupKt;
import com.amazon.music.skyfire.ui.providers.ElementBindingProvider;
import com.amazon.music.skyfire.ui.skyfire.Mapper;
import com.amazon.music.skyfire.ui.skyfire.Methods;
import com.amazon.music.skyfire.ui.utils.NoOpLongClickListener;
import com.amazon.music.skyfire.ui.utils.ViewFinder;
import com.amazon.music.skyfire.ui.utils.ViewFinderKt;
import com.amazon.music.skyfire.ui.widgets.adapters.TabNavigatorAdapter;
import com.amazon.music.skyfire.ui.widgets.adapters.WidgetElementAdapter;
import com.amazon.music.skyfire.ui.widgets.binders.TextButtonBinder;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.ScreenSizeKey;
import com.amazon.music.views.library.views.presentation.ActionBarOverlayable;
import com.amazon.ui.foundations.ktx.TextViewKt;
import com.amazon.ui.foundations.ktx.ViewKt;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.google.android.material.tabs.TabLayout;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MultiSelectorFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0002H\u0002J\u0010\u0010$\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0002H\u0002J\b\u0010%\u001a\u00020\"H\u0002J\b\u0010&\u001a\u00020\u0012H\u0002J\u0010\u0010'\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0002H\u0002J&\u0010(\u001a\u0004\u0018\u00010\u00072\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010,2\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\b\u0010/\u001a\u00020\"H\u0016J\u0010\u00100\u001a\u00020\"2\u0006\u00101\u001a\u000202H\u0016J\b\u00103\u001a\u00020\"H\u0016J\b\u00104\u001a\u00020\"H\u0016J\u001a\u00105\u001a\u00020\"2\u0006\u00106\u001a\u00020\u00072\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\b\u00107\u001a\u00020\"H\u0002J\u0018\u00108\u001a\u00020\"2\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<H\u0002R\u0012\u0010\u0004\u001a\u00020\u00058\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u00020\u00078\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00020\u00078\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u00020\f8\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u00020\u000e8\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u00020\u000e8\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R \u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00130\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0014\u001a\u00020\u00158\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u0012\u0010\u0016\u001a\u00020\u00178\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u0012\u0010\u0018\u001a\u00020\u00198\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082.¢\u0006\u0002\n\u0000R\u0012\u0010\u001c\u001a\u00020\u001d8\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Lcom/amazon/music/skyfire/ui/fragment/MultiSelectorFragment;", "Lcom/amazon/music/skyfire/ui/fragment/MusicHomeFragment;", "LTouch/MultiSelectorTemplateInterface/v1_0/MultiSelectorTemplate;", "()V", "backdrop", "Landroid/widget/ImageView;", "divider", "Landroid/view/View;", "errorView", "inErrorState", "", "mainContent", "Landroidx/recyclerview/widget/RecyclerView;", ContextMappingConstants.PRIMARY_TEXT, "Landroid/widget/TextView;", ContextMappingConstants.SECONDARY_TEXT, "selectedElements", "", "", "", "spinner", "Landroid/widget/ProgressBar;", "swipeRefreshLayout", "Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;", "tabLayout", "Lcom/google/android/material/tabs/TabLayout;", "tabNavigatorAdapter", "Lcom/amazon/music/skyfire/ui/widgets/adapters/TabNavigatorAdapter;", "viewPager", "Landroidx/viewpager/widget/ViewPager;", "widgetElementAdapter", "Lcom/amazon/music/skyfire/ui/widgets/adapters/WidgetElementAdapter;", "LTouch/WidgetsInterface/v1_0/WidgetElement;", "bindWidgetElements", "", "template", "configureActionBarButtons", "enableActionButton", "getSelectedTags", "onConfigureActionBar", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onHandleMethod", "method", "LCoreInterface/v1_0/Method;", "onStyleActionBar", "onTemplateChanged", "onViewCreated", "view", "rebind", "setTextStyleAndMargin", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MultiSelectorFragment extends MusicHomeFragment<MultiSelectorTemplate> {

    @ViewFinder.AView
    private ImageView backdrop;

    @ViewFinder.AView
    private View divider;

    @ViewFinder.AView
    private View errorView;
    private boolean inErrorState;

    @ViewFinder.AView
    private RecyclerView mainContent;

    @ViewFinder.AView
    private TextView primaryText;

    @ViewFinder.AView
    private TextView secondaryText;

    @ViewFinder.AView
    private ProgressBar spinner;

    @ViewFinder.AView
    private SwipeRefreshLayout swipeRefreshLayout;

    @ViewFinder.AView
    private TabLayout tabLayout;
    private TabNavigatorAdapter tabNavigatorAdapter;

    @ViewFinder.AView
    private ViewPager viewPager;
    private final WidgetElementAdapter<WidgetElement> widgetElementAdapter = new WidgetElementAdapter<>();
    private Map<String, Set<String>> selectedElements = new LinkedHashMap();

    /* JADX INFO: compiled from: MultiSelectorFragment.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ScreenSizeKey.values().length];
            iArr[ScreenSizeKey.XSMALL.ordinal()] = 1;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate$default;
        StyleSheet styleSheet;
        Context context;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        RecyclerView recyclerView = null;
        View viewFindViews = (container == null || (viewInflate$default = ViewGroupKt.inflate$default(container, R.layout.fragment_multi_selector, false, 2, null)) == null) ? null : ViewFinderKt.findViews(viewInflate$default, this);
        if (viewFindViews == null || (styleSheet = getStyleSheet()) == null || (context = getContext()) == null) {
            return null;
        }
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
        setTextStyleAndMargin(context, styleSheet);
        RecyclerView recyclerView2 = this.mainContent;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainContent");
            recyclerView2 = null;
        }
        recyclerView2.setLayoutManager(new LinearLayoutManager(context, 1, false));
        this.widgetElementAdapter.setStyleSheet(styleSheet);
        this.widgetElementAdapter.setElementBindingProvider(getElementBindingProvider());
        RecyclerView recyclerView3 = this.mainContent;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainContent");
        } else {
            recyclerView = recyclerView3;
        }
        recyclerView.setAdapter(this.widgetElementAdapter);
        viewFindViews.setOnLongClickListener(new NoOpLongClickListener());
        return viewFindViews;
    }

    @Override // com.amazon.music.skyfire.ui.fragment.MusicHomeFragment, com.amazon.music.skyfire.ui.fragment.TemplateFragment, androidx.fragment.app.Fragment
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
        this.selectedElements.clear();
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
        if (method instanceof TriggerExternalSelectElementMethod) {
            TriggerExternalSelectElementMethod triggerExternalSelectElementMethod = (TriggerExternalSelectElementMethod) method;
            if (!this.selectedElements.containsKey(triggerExternalSelectElementMethod.selectionType())) {
                Map<String, Set<String>> map = this.selectedElements;
                String strSelectionType = triggerExternalSelectElementMethod.selectionType();
                Intrinsics.checkNotNullExpressionValue(strSelectionType, "method.selectionType()");
                map.put(strSelectionType, new LinkedHashSet());
            }
            Set<String> set = this.selectedElements.get(triggerExternalSelectElementMethod.selectionType());
            Intrinsics.checkNotNull(set);
            Set<String> set2 = set;
            if (set2.contains(triggerExternalSelectElementMethod.selectionKey())) {
                set2.remove(triggerExternalSelectElementMethod.selectionKey());
                if (set2.isEmpty()) {
                    this.selectedElements.remove(triggerExternalSelectElementMethod.selectionType());
                }
            } else {
                String strSelectionKey = triggerExternalSelectElementMethod.selectionKey();
                Intrinsics.checkNotNullExpressionValue(strSelectionKey, "method.selectionKey()");
                set2.add(strSelectionKey);
            }
            enableActionButton();
            return;
        }
        if (method instanceof TriggerExternalActionMethod) {
            if (Intrinsics.areEqual(((TriggerExternalActionMethod) method).name(), Methods.createAndPlayMix)) {
                getNavigationProvider().openESCMix(getSelectedTags());
                return;
            } else {
                super.onHandleMethod(method);
                return;
            }
        }
        super.onHandleMethod(method);
    }

    @Override // com.amazon.music.skyfire.ui.fragment.MusicHomeFragment
    public void onStyleActionBar() {
        getActionBarView().setBackgroundOpacity(0.0f);
        StyleSheet styleSheet = getStyleSheet();
        if (styleSheet != null && styleSheet.getWidthScreenSizeKey().compareTo(ScreenSizeKey.LARGE) > 0) {
            LayoutParamUtils.INSTANCE.setLayoutMargins(getActionBarView().getActionButtonView(), -2, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : Integer.valueOf(getResources().getDimensionPixelSize(R.dimen.spacer_mini)), (240 & 128) != 0 ? null : null);
        }
    }

    private final void onConfigureActionBar(MultiSelectorTemplate template) {
        configureActionBarButtons(template);
    }

    private final void configureActionBarButtons(MultiSelectorTemplate template) {
        if (template.callToActionButton() instanceof TextButtonElement) {
            TextButtonBinder textButtonBinder = new TextButtonBinder();
            textButtonBinder.setButtonStyleKey(ButtonStyleKey.SOLID);
            textButtonBinder.setBindingProvider(getElementBindingProvider());
            ButtonElement buttonElementCallToActionButton = template.callToActionButton();
            if (buttonElementCallToActionButton == null) {
                throw new NullPointerException("null cannot be cast to non-null type Touch.WidgetsInterface.v1_0.TextButtonElement");
            }
            textButtonBinder.bind((TextButtonElement) buttonElementCallToActionButton, getActionBarView().getActionButtonView());
        } else {
            ButtonElement buttonElementCallToActionButton2 = template.callToActionButton();
            if (buttonElementCallToActionButton2 != null) {
                getElementBindingProvider().bind(buttonElementCallToActionButton2, getActionBarView().getActionButtonView());
            }
        }
        enableActionButton();
    }

    private final String getSelectedTags() {
        ImmutableSelectedElementsClientInformation selectedElementsClientInformation = ImmutableSelectedElementsClientInformation.builder().elementIds(this.selectedElements).build();
        Mapper mapper = Mapper.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(selectedElementsClientInformation, "selectedElementsClientInformation");
        String strObjectToString = mapper.objectToString(selectedElementsClientInformation);
        return strObjectToString == null ? "" : strObjectToString;
    }

    private final void rebind() {
        MultiSelectorTemplate multiSelectorTemplate = (MultiSelectorTemplate) getTemplate().getValue();
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
        boolean z = true;
        ViewKt.collapseIf(progressBar, multiSelectorTemplate != null || this.inErrorState);
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
        if (multiSelectorTemplate != null && !this.inErrorState) {
            z = false;
        }
        ViewKt.hideIf(recyclerView, z);
        if (multiSelectorTemplate == null) {
            return;
        }
        TextView textView = this.primaryText;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException(ContextMappingConstants.PRIMARY_TEXT);
            textView = null;
        }
        textView.setText(multiSelectorTemplate.title());
        TextView textView2 = this.primaryText;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(ContextMappingConstants.PRIMARY_TEXT);
            textView2 = null;
        }
        TextViewKt.collapseIfEmpty(textView2);
        TextView textView3 = this.secondaryText;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(ContextMappingConstants.SECONDARY_TEXT);
            textView3 = null;
        }
        textView3.setText(multiSelectorTemplate.subtitle());
        TextView textView4 = this.secondaryText;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(ContextMappingConstants.SECONDARY_TEXT);
            textView4 = null;
        }
        TextViewKt.collapseIfEmpty(textView4);
        View view2 = this.divider;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("divider");
            view2 = null;
        }
        view2.setVisibility(0);
        onConfigureActionBar(multiSelectorTemplate);
        String strBackgroundImage = multiSelectorTemplate.backgroundImage();
        if (strBackgroundImage != null) {
            ImageView imageView2 = this.backdrop;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("backdrop");
                imageView2 = null;
            }
            com.amazon.music.skyfire.ui.ktx.ViewKt.setImageUrl$default(imageView2, strBackgroundImage, null, 2, null);
        }
        SwipeRefreshLayout swipeRefreshLayout3 = this.swipeRefreshLayout;
        if (swipeRefreshLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeRefreshLayout");
        } else {
            swipeRefreshLayout2 = swipeRefreshLayout3;
        }
        swipeRefreshLayout2.setEnabled(false);
        if (getElementBindingProvider().getContext() != null) {
            bindWidgetElements(multiSelectorTemplate);
        }
    }

    private final void bindWidgetElements(MultiSelectorTemplate template) {
        TabNavigatorWidgetElement tabNavigatorWidgetElement = template.tabNavigatorWidgetElement();
        if (tabNavigatorWidgetElement != null) {
            TabLayout tabLayout = this.tabLayout;
            ViewPager viewPager = null;
            if (tabLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tabLayout");
                tabLayout = null;
            }
            tabLayout.setVisibility(0);
            ViewPager viewPager2 = this.viewPager;
            if (viewPager2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewPager");
                viewPager2 = null;
            }
            viewPager2.setVisibility(0);
            ElementBindingProvider elementBindingProvider = getElementBindingProvider();
            List<TabNavigatorWidgetItemElement> listItems = tabNavigatorWidgetElement.items();
            Intrinsics.checkNotNullExpressionValue(listItems, "tabNavigatorWidgetElement.items()");
            FragmentManager childFragmentManager = getChildFragmentManager();
            Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
            this.tabNavigatorAdapter = new TabNavigatorAdapter(elementBindingProvider, listItems, childFragmentManager);
            ViewPager viewPager3 = this.viewPager;
            if (viewPager3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewPager");
                viewPager3 = null;
            }
            TabNavigatorAdapter tabNavigatorAdapter = this.tabNavigatorAdapter;
            if (tabNavigatorAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tabNavigatorAdapter");
                tabNavigatorAdapter = null;
            }
            viewPager3.setAdapter(tabNavigatorAdapter);
            TabLayout tabLayout2 = this.tabLayout;
            if (tabLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tabLayout");
                tabLayout2 = null;
            }
            ViewPager viewPager4 = this.viewPager;
            if (viewPager4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewPager");
                viewPager4 = null;
            }
            tabLayout2.setupWithViewPager(viewPager4);
            ViewPager viewPager5 = this.viewPager;
            if (viewPager5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            } else {
                viewPager = viewPager5;
            }
            Integer numSelectedItem = tabNavigatorWidgetElement.selectedItem();
            Intrinsics.checkNotNullExpressionValue(numSelectedItem, "tabNavigatorWidgetElement.selectedItem()");
            viewPager.setCurrentItem(numSelectedItem.intValue());
        }
        WidgetElementAdapter<WidgetElement> widgetElementAdapter = this.widgetElementAdapter;
        List<WidgetElement> listWidgetElements = template.widgetElements();
        Intrinsics.checkNotNullExpressionValue(listWidgetElements, "template.widgetElements()");
        widgetElementAdapter.resetAndNotify(listWidgetElements);
    }

    private final void setTextStyleAndMargin(Context context, StyleSheet styleSheet) {
        TextView textView = this.primaryText;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException(ContextMappingConstants.PRIMARY_TEXT);
            textView = null;
        }
        com.amazon.music.skyfire.ui.ktx.ViewKt.applyFontStyle(textView, styleSheet, FontStyleKey.HEADLINE_2);
        TextView textView3 = this.secondaryText;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(ContextMappingConstants.SECONDARY_TEXT);
            textView3 = null;
        }
        com.amazon.music.skyfire.ui.ktx.ViewKt.applyFontStyle(textView3, styleSheet, FontStyleKey.SECONDARY);
        int dimension = (int) getResources().getDimension(R.dimen.spacer_large);
        if (WhenMappings.$EnumSwitchMapping$0[styleSheet.getWidthScreenSizeKey(context).ordinal()] == 1) {
            int dimension2 = (int) getResources().getDimension(R.dimen.spacer_small);
            TextView textView4 = this.primaryText;
            if (textView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(ContextMappingConstants.PRIMARY_TEXT);
                textView4 = null;
            }
            textView4.setPadding(dimension, dimension2, dimension, 0);
            TextView textView5 = this.secondaryText;
            if (textView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(ContextMappingConstants.SECONDARY_TEXT);
            } else {
                textView2 = textView5;
            }
            textView2.setVisibility(8);
            return;
        }
        int dimension3 = (int) getResources().getDimension(R.dimen.spacer_base);
        TextView textView6 = this.primaryText;
        if (textView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(ContextMappingConstants.PRIMARY_TEXT);
            textView6 = null;
        }
        textView6.setPadding(dimension, dimension, dimension, 0);
        TextView textView7 = this.secondaryText;
        if (textView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(ContextMappingConstants.SECONDARY_TEXT);
        } else {
            textView2 = textView7;
        }
        textView2.setPadding(dimension, dimension3, dimension, 0);
    }

    private final void enableActionButton() {
        getActionBarView().getActionButtonView().setEnabled(!this.selectedElements.isEmpty());
    }
}
