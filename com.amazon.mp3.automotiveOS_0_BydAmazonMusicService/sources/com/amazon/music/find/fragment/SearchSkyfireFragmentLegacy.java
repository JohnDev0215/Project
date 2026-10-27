package com.amazon.music.find.fragment;

import Touch.GalleryTemplateInterface.v1_0.GalleryTemplate;
import Touch.WidgetsInterface.v1_0.NavigationBarElement;
import Touch.WidgetsInterface.v1_0.SearchBarElement;
import android.os.Bundle;
import android.view.View;
import androidx.core.view.KeyEventDispatcher;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.find.view.FindSearchBarView;
import com.amazon.music.skyfire.ui.fragment.GalleryFragment;
import com.amazon.music.skyfire.ui.providers.ChromeProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.presentation.ActionBarOverlayable;
import com.amazon.music.views.library.views.presentation.TranslucentActionBarDecorator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchSkyfireFragmentLegacy.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0016J \u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\bH\u0016J\u001a\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0012\u0010\u001c\u001a\u00020\b2\b\b\u0002\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\bH\u0002R\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006 "}, d2 = {"Lcom/amazon/music/find/fragment/SearchSkyfireFragmentLegacy;", "Lcom/amazon/music/skyfire/ui/fragment/GalleryFragment;", "()V", "findSearchBarView", "Lcom/amazon/music/find/view/FindSearchBarView;", "getFindSearchBarView", "()Lcom/amazon/music/find/view/FindSearchBarView;", "bindSearchBarElement", "", "searchBarElement", "LTouch/WidgetsInterface/v1_0/SearchBarElement;", "onConfigureActionBar", "template", "LTouch/GalleryTemplateInterface/v1_0/GalleryTemplate;", "onContentScrolled", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "dx", "", "dy", "onCreateActionBar", "Lcom/amazon/music/views/library/views/presentation/TranslucentActionBarDecorator;", "onStyleActionBar", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "setIsActionBarOverlay", "isActionBarOverlay", "", "styleActionBar", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchSkyfireFragmentLegacy extends GalleryFragment {
    @Override // com.amazon.music.skyfire.ui.fragment.GalleryFragment
    public void onContentScrolled(RecyclerView recyclerView, int dx, int dy) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
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

    @Override // com.amazon.music.skyfire.ui.fragment.MusicHomeFragment
    public TranslucentActionBarDecorator onCreateActionBar() {
        ChromeProvider chromeProvider = getChromeProvider();
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
        return ChromeProvider.DefaultImpls.createTranslucentActionBarDecorator$default(chromeProvider, fragmentActivityRequireActivity, false, null, 4, null);
    }

    @Override // com.amazon.music.skyfire.ui.fragment.MusicHomeFragment
    public void onStyleActionBar() {
        styleActionBar();
    }

    @Override // com.amazon.music.skyfire.ui.fragment.GalleryFragment
    public void onConfigureActionBar(GalleryTemplate template) {
        Intrinsics.checkNotNullParameter(template, "template");
        super.onConfigureActionBar(template);
        NavigationBarElement navigationBarElement = template.navigationBarElement();
        bindSearchBarElement(navigationBarElement == null ? null : navigationBarElement.searchBarElement());
    }

    static /* synthetic */ void setIsActionBarOverlay$default(SearchSkyfireFragmentLegacy searchSkyfireFragmentLegacy, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        searchSkyfireFragmentLegacy.setIsActionBarOverlay(z);
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

    private final void styleActionBar() {
        getActionBarView().setActionBarVisible(false);
        StyleSheet styleSheet = getStyleSheet();
        if (styleSheet == null) {
            return;
        }
        FindSearchBarView findSearchBarView = getFindSearchBarView();
        if (findSearchBarView != null) {
            findSearchBarView.styleFindSearchBarView(styleSheet);
        }
        getActionBarView().setSearchBarMargins(0, 0, styleSheet.getSpacerInPixels(SpacerKey.MEDIUM), 0);
        getActionBarView().addTopMargin();
    }
}
