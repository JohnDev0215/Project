package com.amazon.music.find.fragment;

import ExternalActionInterface.v1_0.ImmutableTriggerExternalActionMethod;
import QueuesInterface.v1_0.ImmutableSingleThreadedQueue;
import Touch.GalleryTemplateInterface.v1_0.GalleryTemplate;
import Touch.GalleryTemplateInterface.v1_0.ImmutableGalleryTemplate;
import Touch.WidgetsInterface.v1_0.ImmutableNavigationBarElement;
import Touch.WidgetsInterface.v1_0.ImmutableSearchBarElement;
import Touch.WidgetsInterface.v1_0.NavigationBarElement;
import Touch.WidgetsInterface.v1_0.SearchBarElement;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.core.view.KeyEventDispatcher;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.find.R;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.skyfire.Constants;
import com.amazon.music.find.skyfire.Contexts;
import com.amazon.music.find.skyfire.Methods;
import com.amazon.music.find.view.FindSearchBarView;
import com.amazon.music.find.viewmodels.SearchExperienceMode;
import com.amazon.music.skyfire.ui.fragment.GalleryFragment;
import com.amazon.music.skyfire.ui.providers.ChromeProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.presentation.ActionBarOverlayable;
import com.amazon.music.views.library.views.presentation.TooltipExperienceDecorator;
import com.amazon.music.views.library.views.presentation.TranslucentActionBarDecorator;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FindSkyfireFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0012\u0010\u001a\u001a\u00020\u00182\b\u0010\u001b\u001a\u0004\u0018\u00010\u0014H\u0002J\b\u0010\u001c\u001a\u00020\u0018H\u0002J\u0010\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u0014H\u0016J \u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#H\u0016J\b\u0010%\u001a\u00020&H\u0016J\b\u0010'\u001a\u00020\u0010H\u0016J\b\u0010(\u001a\u00020\u0010H\u0002J\b\u0010)\u001a\u00020\u0010H\u0016J\b\u0010*\u001a\u00020\u0010H\u0016J$\u0010+\u001a\u00020\u00102\u0006\u0010,\u001a\u00020\u00162\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00160.H\u0016J\u001a\u0010/\u001a\u00020\u00102\u0006\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u000103H\u0016J\u0012\u00104\u001a\u00020\u00102\b\b\u0002\u00105\u001a\u00020\u0018H\u0002J\b\u00106\u001a\u00020\u0010H\u0002J\b\u00107\u001a\u00020\u0010H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u00068"}, d2 = {"Lcom/amazon/music/find/fragment/FindSkyfireFragment;", "Lcom/amazon/music/skyfire/ui/fragment/GalleryFragment;", "()V", "alexaTooltipExperience", "Lcom/amazon/music/views/library/views/presentation/TooltipExperienceDecorator;", "findSearchBarView", "Lcom/amazon/music/find/view/FindSearchBarView;", "getFindSearchBarView", "()Lcom/amazon/music/find/view/FindSearchBarView;", "searchFeaturesProvider", "Lcom/amazon/music/find/providers/SearchFeaturesProvider;", "getSearchFeaturesProvider", "()Lcom/amazon/music/find/providers/SearchFeaturesProvider;", "setSearchFeaturesProvider", "(Lcom/amazon/music/find/providers/SearchFeaturesProvider;)V", "bindSearchBarElement", "", "searchBarElement", "LTouch/WidgetsInterface/v1_0/SearchBarElement;", "getOfflineTemplate", "LTouch/GalleryTemplateInterface/v1_0/GalleryTemplate;", "getQueryHint", "", "isOffline", "", "getSearchBarElement", "isInOfflineModeOrUndefined", "value", "isSeeMoreTreatment", "onConfigureActionBar", "template", "onContentScrolled", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "dx", "", "dy", "onCreateActionBar", "Lcom/amazon/music/views/library/views/presentation/TranslucentActionBarDecorator;", "onDestroyView", "onHandleError", "onResume", "onStyleActionBar", "onTriggerExternalAction", "action", "args", "", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "setIsActionBarOverlay", "isActionBarOverlay", "styleFindLandingActionBar", "styleFindSeeMoreActionBar", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FindSkyfireFragment extends GalleryFragment {
    private TooltipExperienceDecorator alexaTooltipExperience;
    public SearchFeaturesProvider searchFeaturesProvider;

    @Override // com.amazon.music.skyfire.ui.fragment.GalleryFragment
    public void onContentScrolled(RecyclerView recyclerView, int dx, int dy) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
    }

    public final SearchFeaturesProvider getSearchFeaturesProvider() {
        SearchFeaturesProvider searchFeaturesProvider = this.searchFeaturesProvider;
        if (searchFeaturesProvider != null) {
            return searchFeaturesProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("searchFeaturesProvider");
        return null;
    }

    public final void setSearchFeaturesProvider(SearchFeaturesProvider searchFeaturesProvider) {
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "<set-?>");
        this.searchFeaturesProvider = searchFeaturesProvider;
    }

    private final FindSearchBarView getFindSearchBarView() {
        View searchBar = getActionBarView().getSearchBar();
        if (searchBar instanceof FindSearchBarView) {
            return (FindSearchBarView) searchBar;
        }
        return null;
    }

    @Override // com.amazon.music.skyfire.ui.fragment.GalleryFragment, com.amazon.music.skyfire.ui.fragment.MusicHomeFragment, com.amazon.music.skyfire.ui.fragment.TemplateFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        setIsActionBarOverlay$default(this, false, 1, null);
    }

    @Override // com.amazon.music.skyfire.ui.fragment.GalleryFragment, com.amazon.music.skyfire.ui.fragment.TemplateFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        TooltipExperienceDecorator tooltipExperienceDecorator = this.alexaTooltipExperience;
        if (tooltipExperienceDecorator != null) {
            tooltipExperienceDecorator.onDestroy();
        }
        super.onDestroyView();
    }

    @Override // com.amazon.music.skyfire.ui.fragment.GalleryFragment, com.amazon.music.skyfire.ui.fragment.MusicHomeFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        boolean zIsInOfflineModeOrUndefined = isInOfflineModeOrUndefined((GalleryTemplate) getTemplate().getValue());
        if (getSearchFeaturesProvider().isOffline()) {
            if (zIsInOfflineModeOrUndefined) {
                return;
            }
            overrideViewModelTemplate(getOfflineTemplate());
            rebind();
            return;
        }
        if (zIsInOfflineModeOrUndefined) {
            resetTemplate();
            fetchInitialMethods();
            rebind();
        }
    }

    @Override // com.amazon.music.skyfire.ui.fragment.TemplateFragment
    public void onTriggerExternalAction(String action, Map<String, String> args) {
        Intrinsics.checkNotNullParameter(action, "action");
        Intrinsics.checkNotNullParameter(args, "args");
        if (Intrinsics.areEqual(action, Methods.handleError)) {
            onHandleError();
        } else {
            super.onTriggerExternalAction(action, args);
        }
    }

    @Override // com.amazon.music.skyfire.ui.fragment.MusicHomeFragment
    public TranslucentActionBarDecorator onCreateActionBar() {
        ChromeProvider chromeProvider = getChromeProvider();
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
        return ChromeProvider.DefaultImpls.createTranslucentActionBarDecorator$default(chromeProvider, fragmentActivityRequireActivity, !isSeeMoreTreatment(), null, 4, null);
    }

    @Override // com.amazon.music.skyfire.ui.fragment.MusicHomeFragment
    public void onStyleActionBar() {
        if (isSeeMoreTreatment()) {
            styleFindSeeMoreActionBar();
        } else {
            styleFindLandingActionBar();
        }
    }

    @Override // com.amazon.music.skyfire.ui.fragment.GalleryFragment
    public void onConfigureActionBar(GalleryTemplate template) {
        Intrinsics.checkNotNullParameter(template, "template");
        super.onConfigureActionBar(template);
        if (isSeeMoreTreatment()) {
            return;
        }
        NavigationBarElement navigationBarElement = template.navigationBarElement();
        bindSearchBarElement(navigationBarElement == null ? null : navigationBarElement.searchBarElement());
    }

    private final void onHandleError() {
        if (!isSeeMoreTreatment()) {
            if (getSearchFeaturesProvider().isOffline()) {
                overrideViewModelTemplate(getOfflineTemplate());
            } else {
                setInErrorState(true);
                bindSearchBarElement(getSearchBarElement(false));
            }
        } else {
            setInErrorState(true);
        }
        rebind();
    }

    static /* synthetic */ void setIsActionBarOverlay$default(FindSkyfireFragment findSkyfireFragment, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        findSkyfireFragment.setIsActionBarOverlay(z);
    }

    private final void setIsActionBarOverlay(boolean isActionBarOverlay) {
        KeyEventDispatcher.Component activity = getActivity();
        ActionBarOverlayable actionBarOverlayable = activity instanceof ActionBarOverlayable ? (ActionBarOverlayable) activity : null;
        if (actionBarOverlayable == null) {
            return;
        }
        actionBarOverlayable.setActionBarOverlay(isActionBarOverlay);
    }

    private final void bindSearchBarElement(SearchBarElement searchBarElement) {
        View searchBar = getActionBarView().getSearchBar();
        FindSearchBarView findSearchBarView = searchBar instanceof FindSearchBarView ? (FindSearchBarView) searchBar : null;
        if (searchBarElement == null || findSearchBarView == null) {
            return;
        }
        getElementBindingProvider().bind(searchBarElement, findSearchBarView);
    }

    private final void styleFindLandingActionBar() {
        Context context;
        StyleSheet styleSheet = getStyleSheet();
        if (styleSheet == null) {
            return;
        }
        FindSearchBarView findSearchBarView = getFindSearchBarView();
        if (findSearchBarView != null) {
            boolean zIsAlexaSearchFindBrowseEnabled = getSearchFeaturesProvider().isAlexaSearchFindBrowseEnabled();
            findSearchBarView.styleFindSearchBarView(styleSheet);
            findSearchBarView.disableSearchViewKeyboard();
            findSearchBarView.showBackButton(false);
            findSearchBarView.showAlexaButton(zIsAlexaSearchFindBrowseEnabled);
            TooltipExperienceDecorator tooltipExperienceDecoratorCreateTooltipExperienceDecorator = null;
            if (zIsAlexaSearchFindBrowseEnabled && (context = getContext()) != null) {
                tooltipExperienceDecoratorCreateTooltipExperienceDecorator = getChromeProvider().createTooltipExperienceDecorator(context);
            }
            this.alexaTooltipExperience = tooltipExperienceDecoratorCreateTooltipExperienceDecorator;
            if (tooltipExperienceDecoratorCreateTooltipExperienceDecorator != null) {
                tooltipExperienceDecoratorCreateTooltipExperienceDecorator.showTooltip(findSearchBarView, styleSheet);
            }
        }
        Integer spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.MEDIUM);
        getActionBarView().setSearchBarMargins(spacerInPixels, spacerInPixels, spacerInPixels, spacerInPixels);
        getActionBarView().addTopMargin();
        getActionBarView().setBackButtonVisible(false);
        getActionBarView().setFeedButtonVisible(!getSearchFeaturesProvider().isFireOS());
    }

    private final void styleFindSeeMoreActionBar() {
        getActionBarView().setBackButtonVisible(true);
        getActionBarView().setFeedButtonVisible(false);
        FindSearchBarView findSearchBarView = getFindSearchBarView();
        if (findSearchBarView != null) {
            findSearchBarView.setVisibility(8);
        }
        FindSearchBarView findSearchBarView2 = getFindSearchBarView();
        if (findSearchBarView2 == null) {
            return;
        }
        findSearchBarView2.disableSearchViewKeyboard();
    }

    private final boolean isSeeMoreTreatment() {
        Bundle arguments = getArguments();
        return Intrinsics.areEqual(Constants.SHOW_FIND_CONTENT_SKILL, arguments == null ? null : arguments.get(Constants.SKILL_PATH_ID));
    }

    private final GalleryTemplate getOfflineTemplate() {
        ImmutableGalleryTemplate immutableGalleryTemplateBuild = ImmutableGalleryTemplate.builder().widgetElements(Collections.emptyList()).navigationBarElement(ImmutableNavigationBarElement.builder().primaryHeader("").searchBarElement(getSearchBarElement(true)).build()).build();
        Intrinsics.checkNotNullExpressionValue(immutableGalleryTemplateBuild, "builder()\n            .w…   )\n            .build()");
        return immutableGalleryTemplateBuild;
    }

    private final SearchBarElement getSearchBarElement(boolean isOffline) {
        String queryHint = getQueryHint(isOffline);
        List listListOf = CollectionsKt__CollectionsJVMKt.listOf(ImmutableTriggerExternalActionMethod.builder().name(Methods.openSearch).putContext(Contexts.OpenSearch.mode, SearchExperienceMode.SEARCH_HISTORY.name()).queue(ImmutableSingleThreadedQueue.builder().id("ST_HTTP").build()).build());
        ImmutableSearchBarElement immutableSearchBarElementBuild = ImmutableSearchBarElement.builder().queryHint(queryHint).addAllOnInputSelected(listListOf).addAllOnQueryChanged(listListOf).build();
        Intrinsics.checkNotNullExpressionValue(immutableSearchBarElementBuild, "builder()\n            .q…ods)\n            .build()");
        return immutableSearchBarElementBuild;
    }

    private final String getQueryHint(boolean isOffline) {
        Context context = getContext();
        if (context == null) {
            return null;
        }
        return context.getString(R.string.search_view_offline_query_hint);
    }

    private final boolean isInOfflineModeOrUndefined(GalleryTemplate value) {
        SearchBarElement searchBarElement;
        Context context = getContext();
        String strQueryHint = null;
        String string = context == null ? null : context.getString(R.string.search_view_offline_query_hint);
        if (value != null) {
            NavigationBarElement navigationBarElement = value.navigationBarElement();
            if (navigationBarElement != null && (searchBarElement = navigationBarElement.searchBarElement()) != null) {
                strQueryHint = searchBarElement.queryHint();
            }
            if (!Intrinsics.areEqual(strQueryHint, string)) {
                return false;
            }
        }
        return true;
    }
}
