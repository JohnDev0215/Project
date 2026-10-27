package com.amazon.mp3.find.view;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import com.amazon.mp3.actionbar.view.BauhausActionBarView;
import com.amazon.mp3.actionbar.view.BauhausFilterActionBarView;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.find.util.AlexaUtil;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.music.find.utils.KeyboardUtils;
import com.amazon.music.find.view.FindSearchBarView;
import com.amazon.music.find.viewmodels.FindPageGridViewModelContext;
import com.amazon.music.find.viewmodels.SearchExperienceMode;
import com.amazon.music.find.viewmodels.SearchHostViewModel;
import com.amazon.music.find.viewmodels.SearchHostViewModelFactory;
import com.amazon.music.views.library.binders.RefinementPillBinder;
import com.amazon.music.views.library.models.FilterItemModel;
import com.amazon.music.views.library.models.FilterModel;
import com.amazon.music.views.library.models.RefinementPillModel;
import com.amazon.music.views.library.providers.RefinementClickListenerProvider;
import com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import dagger.android.support.AndroidSupportInjection;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: SearchHostFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000¡\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r*\u0001H\u0018\u0000 `2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001`B\u0007¢\u0006\u0004\b^\u0010_J\b\u0010\u0004\u001a\u00020\u0003H\u0002J\b\u0010\u0006\u001a\u00020\u0005H\u0002J\b\u0010\u0007\u001a\u00020\u0005H\u0002J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\bH\u0002J\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u000bH\u0002J\u0018\u0010\u000e\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000bH\u0002J\u001a\u0010\u0011\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\f\u001a\u00020\u000bH\u0002J\u0010\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0003H\u0002J\b\u0010\u0014\u001a\u00020\u0005H\u0002J\b\u0010\u0015\u001a\u00020\u0005H\u0002J\b\u0010\u0016\u001a\u00020\u0005H\u0002J\b\u0010\u0017\u001a\u00020\u0005H\u0002J\b\u0010\u0018\u001a\u00020\u0005H\u0002J\b\u0010\u0019\u001a\u00020\u0005H\u0002J\b\u0010\u001a\u001a\u00020\u0005H\u0002J\u0012\u0010\u001c\u001a\u00020\u00052\b\b\u0002\u0010\u001b\u001a\u00020\u0003H\u0002J\u0010\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0003H\u0002J\u0014\u0010\"\u001a\u0004\u0018\u00010!2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0002J\u0014\u0010$\u001a\u0004\u0018\u00010#2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0002J\u0012\u0010%\u001a\u00020\u00052\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0002J\u0012\u0010&\u001a\u00020\u00052\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0002J&\u0010-\u001a\u0004\u0018\u00010,2\u0006\u0010(\u001a\u00020'2\b\u0010*\u001a\u0004\u0018\u00010)2\b\u0010+\u001a\u0004\u0018\u00010\u001fH\u0016J\u0010\u00100\u001a\u00020\u00052\u0006\u0010/\u001a\u00020.H\u0016J\u0012\u00101\u001a\u00020\u00052\b\u0010+\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u00102\u001a\u00020\u0005H\u0016J\b\u00103\u001a\u00020\u0005H\u0016J\b\u00104\u001a\u00020\u0005H\u0016J\b\u00105\u001a\u00020\u0005H\u0016J\b\u00106\u001a\u00020\u0005H\u0016J\u0012\u00107\u001a\u00020\u00022\b\u0010+\u001a\u0004\u0018\u00010\u001fH\u0014J$\u0010<\u001a\u00020\b2\u0006\u00109\u001a\u0002082\b\u0010:\u001a\u0004\u0018\u00010!2\b\u0010;\u001a\u0004\u0018\u00010!H\u0014J\u0010\u0010?\u001a\u00020\u00052\u0006\u0010>\u001a\u00020=H\u0014J\b\u0010@\u001a\u00020\u0003H\u0014J\b\u0010A\u001a\u00020\u0005H\u0014J\u0012\u0010D\u001a\u00020\u00052\b\u0010C\u001a\u0004\u0018\u00010BH\u0014R\u0014\u0010F\u001a\u00020E8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bF\u0010GR\u0014\u0010I\u001a\u00020H8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bI\u0010JR\u0014\u0010L\u001a\u00020K8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bL\u0010MR\u0014\u0010O\u001a\u00020N8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bO\u0010PR\u0014\u0010Q\u001a\u00020E8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bQ\u0010GR\u0014\u0010R\u001a\u00020E8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bR\u0010GR\u0014\u0010S\u001a\u00020E8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bS\u0010GR\"\u0010U\u001a\u00020T8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\bU\u0010V\u001a\u0004\bW\u0010X\"\u0004\bY\u0010ZR\u0011\u0010]\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\b[\u0010\\¨\u0006a"}, d2 = {"Lcom/amazon/mp3/find/view/SearchHostFragment;", "Lcom/amazon/mp3/find/view/BaseHostFragment;", "Lcom/amazon/music/find/viewmodels/SearchHostViewModel;", "", "isOffline", "", "requestSearchViewFocus", "clearSearchBarViewFocus", "Lcom/amazon/mp3/actionbar/view/BauhausActionBarView;", "actionBarView", "updateQueryHint", "Lcom/amazon/music/find/viewmodels/SearchHostViewModel$ActionBarState;", "actionBarState", "updateActionBar", "configureActionBar", "Lcom/amazon/music/find/view/FindSearchBarView;", "findActionBarView", "configureFindSearchBar", "focus", "updateSearchViewFocus", "setupFindSearchBar", "handleSearchViewQueryChange", "clearFindSearchBarListeners", "handleActionBarState", "handleSearchQueryTextUpdated", "handleExternalNavigationRequested", "handleSearchViewFocusUpdated", "matchLeftRightMargins", "styleActionBar", "hasFocus", "restoreSearchViewClearButton", "Landroid/os/Bundle;", "bundle", "", "getPageUriFromExtras", "Lcom/amazon/music/find/viewmodels/SearchExperienceMode;", "getExperienceModeFromExtras", "markPageAsInitialized", "markPageUriAsInitialized", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "savedInstanceState", "Landroid/view/View;", "onCreateView", "Landroid/content/Context;", "context", "onAttach", "onActivityCreated", "onStart", "onResume", "onStop", "onDestroy", "onDestroyView", "createViewModel", "Landroidx/fragment/app/FragmentActivity;", "fragmentActivity", "title", "subTitle", "createActionBarView", "Lcom/amazon/mp3/actionbar/view/BauhausFilterActionBarView;", "actionBar", "setupActionBarPills", "shouldErrorLayoutBeVisible", "setBottomBarTab", "Lcom/amazon/music/find/viewmodels/FindPageGridViewModelContext;", "pageGridViewModelContext", "afterRenderingCompleted", "Landroid/view/View$OnClickListener;", "mBackButtonListener", "Landroid/view/View$OnClickListener;", "com/amazon/mp3/find/view/SearchHostFragment$mRefinementClickListenerProvider$1", "mRefinementClickListenerProvider", "Lcom/amazon/mp3/find/view/SearchHostFragment$mRefinementClickListenerProvider$1;", "Landroid/view/View$OnKeyListener;", "mSearchViewOnKeyListener", "Landroid/view/View$OnKeyListener;", "Landroid/view/View$OnFocusChangeListener;", "mSearchViewFocusChangeListener", "Landroid/view/View$OnFocusChangeListener;", "mSearchViewOnClickListener", "mSearchClearImageViewClickListener", "mFilterButtonClickListener", "Lcom/amazon/music/find/viewmodels/SearchHostViewModelFactory;", "searchViewModelFactory", "Lcom/amazon/music/find/viewmodels/SearchHostViewModelFactory;", "getSearchViewModelFactory", "()Lcom/amazon/music/find/viewmodels/SearchHostViewModelFactory;", "setSearchViewModelFactory", "(Lcom/amazon/music/find/viewmodels/SearchHostViewModelFactory;)V", "getSearchViewModel", "()Lcom/amazon/music/find/viewmodels/SearchHostViewModel;", "searchViewModel", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class SearchHostFragment extends BaseHostFragment<SearchHostViewModel> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Logger logger = LoggerFactory.getLogger(SearchHostFragment.class.getName());
    public SearchHostViewModelFactory searchViewModelFactory;
    private final View.OnClickListener mBackButtonListener = new View.OnClickListener() { // from class: com.amazon.mp3.find.view.SearchHostFragment$$ExternalSyntheticLambda1
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            SearchHostFragment.m1067mBackButtonListener$lambda0(this.f$0, view);
        }
    };
    private final SearchHostFragment$mRefinementClickListenerProvider$1 mRefinementClickListenerProvider = new RefinementClickListenerProvider() { // from class: com.amazon.mp3.find.view.SearchHostFragment$mRefinementClickListenerProvider$1
        @Override // com.amazon.music.views.library.providers.RefinementClickListenerProvider
        public void onClick(RefinementPillModel model, View view) {
            FilterItemModel itemModel;
            String browseId;
            Intrinsics.checkNotNullParameter(model, "model");
            FilterModel content = model.getContent();
            if (content == null) {
                return;
            }
            SearchHostFragment searchHostFragment = this.this$0;
            if (content.isCollection() || (itemModel = content.getItemModel()) == null || (browseId = itemModel.getBrowseId()) == null) {
                return;
            }
            searchHostFragment.getViewModel().toggleFilterSelection(browseId);
        }
    };
    private final View.OnKeyListener mSearchViewOnKeyListener = new View.OnKeyListener() { // from class: com.amazon.mp3.find.view.SearchHostFragment$$ExternalSyntheticLambda2
        @Override // android.view.View.OnKeyListener
        public final boolean onKey(View view, int i, KeyEvent keyEvent) {
            return SearchHostFragment.m1072mSearchViewOnKeyListener$lambda1(this.f$0, view, i, keyEvent);
        }
    };
    private final View.OnFocusChangeListener mSearchViewFocusChangeListener = new View.OnFocusChangeListener() { // from class: com.amazon.mp3.find.view.SearchHostFragment$$ExternalSyntheticLambda3
        @Override // android.view.View.OnFocusChangeListener
        public final void onFocusChange(View view, boolean z) {
            SearchHostFragment.m1070mSearchViewFocusChangeListener$lambda3(this.f$0, view, z);
        }
    };
    private final View.OnClickListener mSearchViewOnClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.find.view.SearchHostFragment$$ExternalSyntheticLambda4
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            SearchHostFragment.m1071mSearchViewOnClickListener$lambda4(this.f$0, view);
        }
    };
    private final View.OnClickListener mSearchClearImageViewClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.find.view.SearchHostFragment$$ExternalSyntheticLambda5
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            SearchHostFragment.m1069mSearchClearImageViewClickListener$lambda5(this.f$0, view);
        }
    };
    private final View.OnClickListener mFilterButtonClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.find.view.SearchHostFragment$$ExternalSyntheticLambda6
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            SearchHostFragment.m1068mFilterButtonClickListener$lambda7(this.f$0, view);
        }
    };

    @Override // com.amazon.mp3.find.view.BaseHostFragment
    protected boolean shouldErrorLayoutBeVisible() {
        return false;
    }

    /* JADX INFO: compiled from: SearchHostFragment.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0007J\u001e\u0010\n\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\rH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/find/view/SearchHostFragment$Companion;", "", "()V", "SEARCH_EXPERIENCE_MODE_EXTRA", "", "SEARCH_PAGE_URI_EXTRA", "TAG", "logger", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "newInstance", "Lcom/amazon/mp3/find/view/SearchHostFragment;", "bundle", "Landroid/os/Bundle;", "mode", "Lcom/amazon/music/find/viewmodels/SearchExperienceMode;", "extras", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final SearchHostFragment newInstance(Bundle bundle) {
            SearchHostFragment searchHostFragment = new SearchHostFragment();
            searchHostFragment.setArguments(bundle);
            return searchHostFragment;
        }

        @JvmStatic
        public final SearchHostFragment newInstance(SearchExperienceMode mode, Bundle extras) {
            Bundle bundle = extras != null ? new Bundle(extras) : new Bundle();
            if (mode != null) {
                bundle.putString("SEARCH_EXPERIENCE_MODE_EXTRA", mode.toString());
            }
            return newInstance(bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: mBackButtonListener$lambda-0, reason: not valid java name */
    public static final void m1067mBackButtonListener$lambda0(SearchHostFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().goBack();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: mSearchViewOnKeyListener$lambda-1, reason: not valid java name */
    public static final boolean m1072mSearchViewOnKeyListener$lambda1(SearchHostFragment this$0, View view, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (keyEvent != null && keyEvent.getKeyCode() == 66) {
            if (keyEvent != null && keyEvent.getAction() == 0) {
                this$0.getViewModel().executeSearchFromQuery();
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: mSearchViewFocusChangeListener$lambda-3, reason: not valid java name */
    public static final void m1070mSearchViewFocusChangeListener$lambda3(SearchHostFragment this$0, View v, boolean z) {
        Context context;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.restoreSearchViewClearButton(z);
        logger.debug(Intrinsics.stringPlus("SPL::mSearchViewFocusChangeListener. hasFocus=", Boolean.valueOf(z)));
        this$0.getViewModel().changeSearchFocus(z);
        if (z || (context = this$0.getContext()) == null) {
            return;
        }
        KeyboardUtils keyboardUtils = KeyboardUtils.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(v, "v");
        KeyboardUtils.hideKeyboard$default(keyboardUtils, context, v, 0, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: mSearchViewOnClickListener$lambda-4, reason: not valid java name */
    public static final void m1071mSearchViewOnClickListener$lambda4(SearchHostFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().initiateSearch();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: mSearchClearImageViewClickListener$lambda-5, reason: not valid java name */
    public static final void m1069mSearchClearImageViewClickListener$lambda5(SearchHostFragment this$0, View view) {
        FindSearchBarView findSearchBarView;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BauhausActionBarView mActionBarView = this$0.getMActionBarView();
        if (mActionBarView != null && (findSearchBarView = mActionBarView.getFindSearchBarView()) != null) {
            FindSearchBarView.clearSearchQuery$default(findSearchBarView, false, 1, null);
        }
        this$0.getViewModel().clearSearchQuery();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: mFilterButtonClickListener$lambda-7, reason: not valid java name */
    public static final void m1068mFilterButtonClickListener$lambda7(SearchHostFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getViewModel().showIvyRefinementPills()) {
            BauhausActionBarView mActionBarView = this$0.getMActionBarView();
            if ((mActionBarView instanceof BauhausFilterActionBarView ? (BauhausFilterActionBarView) mActionBarView : null) == null) {
                return;
            }
            this$0.getViewModel().toggleExposedRefinements();
        }
    }

    public final SearchHostViewModelFactory getSearchViewModelFactory() {
        SearchHostViewModelFactory searchHostViewModelFactory = this.searchViewModelFactory;
        if (searchHostViewModelFactory != null) {
            return searchHostViewModelFactory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("searchViewModelFactory");
        return null;
    }

    private final boolean isOffline() {
        return getViewModel().isOffline();
    }

    private final void requestSearchViewFocus() {
        FindSearchBarView findSearchBarView;
        Logger logger2 = logger;
        logger2.debug("SPL::requestSearchViewFocus");
        BauhausActionBarView mActionBarView = getMActionBarView();
        if (mActionBarView == null || (findSearchBarView = mActionBarView.getFindSearchBarView()) == null) {
            return;
        }
        logger2.debug("SPL::requestSearchViewFocus->requesting focus");
        findSearchBarView.performSearchButtonClick();
    }

    private final void clearSearchBarViewFocus() {
        FindSearchBarView findSearchBarView;
        logger.debug("SPL::clearSearchBarViewFocus");
        BauhausActionBarView mActionBarView = getMActionBarView();
        if (mActionBarView == null || (findSearchBarView = mActionBarView.getFindSearchBarView()) == null) {
            return;
        }
        findSearchBarView.clearSearchViewFocus();
    }

    private final void updateQueryHint(BauhausActionBarView actionBarView) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        int i = isOffline() ? R.string.dmusic_prime_search_library_offline_query_hint : PodcastFeatureGating.PODCAST.isEnabled() && !ChildUserUtil.INSTANCE.isChildUser(context) ? R.string.dmusic_prime_search_music_and_podcasts_query_hint : R.string.dmusic_prime_search_prime_query_hint;
        FindSearchBarView findSearchBarView = actionBarView.getFindSearchBarView();
        if (findSearchBarView == null) {
            return;
        }
        String string = context.getString(i);
        Intrinsics.checkNotNullExpressionValue(string, "it.getString(hintId)");
        findSearchBarView.setQueryHint(string);
    }

    private final void updateActionBar(SearchHostViewModel.ActionBarState actionBarState) {
        BauhausActionBarView mActionBarView = getMActionBarView();
        if (mActionBarView == null) {
            return;
        }
        logger.debug(Intrinsics.stringPlus("SPL::updateActionBar. actionBarState=", actionBarState));
        configureActionBar(mActionBarView, actionBarState);
        configureFindSearchBar(mActionBarView.getFindSearchBarView(), actionBarState);
    }

    private final void configureActionBar(BauhausActionBarView actionBarView, SearchHostViewModel.ActionBarState actionBarState) {
        if (actionBarState.getShowActionBar()) {
            actionBarView.showActionBar();
        } else {
            actionBarView.hideActionBar();
        }
        if (actionBarState.getUpdateQueryHint()) {
            updateQueryHint(actionBarView);
        }
        if (getViewModel().showIvyRefinementPills() && getViewModel().hasFilterItemModels()) {
            BauhausFilterActionBarView bauhausFilterActionBarView = actionBarView instanceof BauhausFilterActionBarView ? (BauhausFilterActionBarView) actionBarView : null;
            if (bauhausFilterActionBarView == null) {
                return;
            }
            if (actionBarState.getShowExposedRefinements()) {
                bauhausFilterActionBarView.setFilters(getViewModel().getFilterItemModels());
            }
            BauhausFilterActionBarView.showExposedRefinements$default(bauhausFilterActionBarView, actionBarState.getShowExposedRefinements(), null, 2, null);
        }
    }

    private final void configureFindSearchBar(FindSearchBarView findActionBarView, SearchHostViewModel.ActionBarState actionBarState) {
        if (getMStyleSheet() == null || findActionBarView == null) {
            return;
        }
        Logger logger2 = logger;
        logger2.debug("SPL::update actionBarView start");
        findActionBarView.showBackButton(actionBarState.getShowBackButton());
        findActionBarView.showAlexaButton(AlexaUtil.INSTANCE.shouldShowAlexaButton() && actionBarState.getShowAlexaButton());
        String findSearchBarTitle = actionBarState.getFindSearchBarTitle();
        if (findSearchBarTitle != null) {
            styleActionBar(true);
            findActionBarView.showTitle(findSearchBarTitle);
        } else {
            styleActionBar$default(this, false, 1, null);
            findActionBarView.showSearchBox();
            findActionBarView.showClearButton(actionBarState.getShowClearButton());
        }
        if (getViewModel().hasFilterItemModels()) {
            findActionBarView.showFilterButton(actionBarState.getShowFilterButton(), actionBarState.getActiveFilter(), actionBarState.getShowExposedRefinements());
        }
        logger2.debug("SPL::update actionBarView finish");
    }

    private final void updateSearchViewFocus(boolean focus) {
        if (focus) {
            requestSearchViewFocus();
        } else {
            clearSearchBarViewFocus();
        }
    }

    private final void setupFindSearchBar() {
        FindSearchBarView findSearchBarView;
        BauhausActionBarView mActionBarView = getMActionBarView();
        if (mActionBarView == null || (findSearchBarView = mActionBarView.getFindSearchBarView()) == null) {
            return;
        }
        findSearchBarView.setSearchViewOnKeyListener(this.mSearchViewOnKeyListener);
        findSearchBarView.setSearchViewFocusChangeListener(this.mSearchViewFocusChangeListener);
        findSearchBarView.setSearchViewOnClickListener(this.mSearchViewOnClickListener);
        findSearchBarView.setBackButtonListener(this.mBackButtonListener);
        findSearchBarView.setClearImageViewListener(this.mSearchClearImageViewClickListener);
        findSearchBarView.setFilterButtonListener(this.mFilterButtonClickListener);
    }

    private final void handleSearchViewQueryChange() {
        FindSearchBarView findSearchBarView;
        BauhausActionBarView mActionBarView = getMActionBarView();
        if (mActionBarView == null || (findSearchBarView = mActionBarView.getFindSearchBarView()) == null) {
            return;
        }
        addDisposable(getViewModel().subscribeToSearchViewQueryTextChange(findSearchBarView.getSearchViewQueryTextObservable()));
    }

    private final void clearFindSearchBarListeners() {
        FindSearchBarView findSearchBarView;
        BauhausActionBarView mActionBarView = getMActionBarView();
        if (mActionBarView == null || (findSearchBarView = mActionBarView.getFindSearchBarView()) == null) {
            return;
        }
        findSearchBarView.clearAllListeners();
    }

    private final void handleActionBarState() {
        addDisposable(getViewModel().m2081getActionBarState().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.mp3.find.view.SearchHostFragment$$ExternalSyntheticLambda8
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SearchHostFragment.m1063handleActionBarState$lambda17(this.f$0, (SearchHostViewModel.ActionBarState) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleActionBarState$lambda-17, reason: not valid java name */
    public static final void m1063handleActionBarState$lambda17(SearchHostFragment this$0, SearchHostViewModel.ActionBarState it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        logger.debug("SPL::handleActionBarState.");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.updateActionBar(it);
    }

    private final void handleSearchQueryTextUpdated() {
        addDisposable(getViewModel().getSearchQueryTextUpdated().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.mp3.find.view.SearchHostFragment$$ExternalSyntheticLambda9
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SearchHostFragment.m1065handleSearchQueryTextUpdated$lambda18(this.f$0, (String) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleSearchQueryTextUpdated$lambda-18, reason: not valid java name */
    public static final void m1065handleSearchQueryTextUpdated$lambda18(SearchHostFragment this$0, String it) {
        FindSearchBarView findSearchBarView;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        logger.debug(Intrinsics.stringPlus("SPL::handleSearchQueryTextUpdated. query: ", it));
        BauhausActionBarView mActionBarView = this$0.getMActionBarView();
        if (mActionBarView == null || (findSearchBarView = mActionBarView.getFindSearchBarView()) == null) {
            return;
        }
        Intrinsics.checkNotNullExpressionValue(it, "it");
        findSearchBarView.setSearchQuery(it, false);
    }

    private final void handleExternalNavigationRequested() {
        addDisposable(getViewModel().getExternalNavigationRequested().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.mp3.find.view.SearchHostFragment$$ExternalSyntheticLambda7
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SearchHostFragment.m1064handleExternalNavigationRequested$lambda19(this.f$0, (Unit) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleExternalNavigationRequested$lambda-19, reason: not valid java name */
    public static final void m1064handleExternalNavigationRequested$lambda19(SearchHostFragment this$0, Unit unit) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        logger.debug("SPL::handleExternalNavigationRequested");
        FragmentActivity activity = this$0.getActivity();
        SearchTabFragmentHost.selectFindTab(activity instanceof MusicHomeActivity ? (MusicHomeActivity) activity : null, null, false, true);
    }

    private final void handleSearchViewFocusUpdated() {
        addDisposable(getViewModel().getSearchViewFocusUpdated().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.mp3.find.view.SearchHostFragment$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SearchHostFragment.m1066handleSearchViewFocusUpdated$lambda20(this.f$0, (Boolean) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleSearchViewFocusUpdated$lambda-20, reason: not valid java name */
    public static final void m1066handleSearchViewFocusUpdated$lambda20(SearchHostFragment this$0, Boolean it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        logger.debug(Intrinsics.stringPlus("SPL::handleSearchViewFocusUpdated. visible: ", it));
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.updateSearchViewFocus(it.booleanValue());
    }

    static /* synthetic */ void styleActionBar$default(SearchHostFragment searchHostFragment, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        searchHostFragment.styleActionBar(z);
    }

    private final void styleActionBar(boolean matchLeftRightMargins) {
        BauhausActionBarView mActionBarView;
        StyleSheet mStyleSheet = getMStyleSheet();
        if (mStyleSheet == null || (mActionBarView = getMActionBarView()) == null) {
            return;
        }
        Logger logger2 = logger;
        logger2.debug("SPL::styleActionBar->update actionBarView");
        FindSearchBarView findSearchBarView = mActionBarView.getFindSearchBarView();
        if (findSearchBarView != null) {
            logger2.debug("SPL::styleActionBar->update searchBarView");
            findSearchBarView.styleFindSearchBarView(mStyleSheet);
        }
        int spacerInPixels = mStyleSheet.getSpacerInPixels(SpacerKey.MEDIUM);
        if (matchLeftRightMargins) {
            spacerInPixels = 0;
        }
        mActionBarView.setSearchBarMargins(0, 0, spacerInPixels, 0);
        mActionBarView.addTopMarginToToolBar();
    }

    private final void restoreSearchViewClearButton(boolean hasFocus) {
        BauhausActionBarView mActionBarView;
        FindSearchBarView findSearchBarView;
        if (!hasFocus || getMStyleSheet() == null || (mActionBarView = getMActionBarView()) == null || (findSearchBarView = mActionBarView.getFindSearchBarView()) == null) {
            return;
        }
        logger.debug(Intrinsics.stringPlus("SPL::restoreSearchViewClearButton ", Boolean.valueOf(hasFocus)));
        findSearchBarView.showClearButton(true);
    }

    private final String getPageUriFromExtras(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return bundle.getString("SEARCH_PAGE_URI_EXTRA");
    }

    private final SearchExperienceMode getExperienceModeFromExtras(Bundle bundle) {
        String string = bundle == null ? null : bundle.getString("SEARCH_EXPERIENCE_MODE_EXTRA");
        if (string == null) {
            return null;
        }
        try {
            return SearchExperienceMode.valueOf(string);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    private final void markPageAsInitialized(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        bundle.remove("SEARCH_EXPERIENCE_MODE_EXTRA");
    }

    private final void markPageUriAsInitialized(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        bundle.remove("SEARCH_PAGE_URI_EXTRA");
    }

    @Override // com.amazon.mp3.find.view.BaseHostFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        logger.debug("SPL::onCreateView");
        View viewOnCreateView = super.onCreateView(inflater, container, savedInstanceState);
        if (viewOnCreateView == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
        }
        setRoot((ViewGroup) viewOnCreateView);
        return getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        logger.debug("SPL::onAttach");
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override // com.amazon.mp3.find.view.BaseHostFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        logger.debug("SPL::onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        handleActionBarState();
        setupFindSearchBar();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        logger.debug("SPL::onStart");
        super.onStart();
        handleSearchQueryTextUpdated();
        handleExternalNavigationRequested();
        handleSearchViewQueryChange();
        handleSearchViewFocusUpdated();
    }

    @Override // com.amazon.mp3.find.view.BaseHostFragment, androidx.fragment.app.Fragment
    public void onResume() {
        logger.debug("SPL::onResume");
        setBottomBarTab();
        if (!getSearchViewModel().actionBarStateHasObservers()) {
            handleActionBarState();
        }
        SearchHostViewModel.ActionBarState actionBarState = getSearchViewModel().getActionBarState();
        if (actionBarState != null && actionBarState.getFindSearchBarTitle() != null) {
            styleActionBar(true);
        } else {
            styleActionBar$default(this, false, 1, null);
        }
        super.onResume();
    }

    @Override // com.amazon.mp3.find.view.BaseHostFragment, androidx.fragment.app.Fragment
    public void onStop() {
        logger.debug("SPL::onStop");
        super.onStop();
    }

    @Override // com.amazon.mp3.find.view.BaseHostFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        logger.debug("SPL::onDestroy");
        super.onDestroy();
    }

    @Override // com.amazon.mp3.find.view.BaseHostFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        logger.debug("SPL::onDestroyView");
        clearSearchBarViewFocus();
        clearFindSearchBarListeners();
        super.onDestroyView();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.mp3.find.view.BaseHostFragment
    public SearchHostViewModel createViewModel(Bundle savedInstanceState) {
        logger.debug("SPL::createViewModel");
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
        SearchHostViewModel searchHostViewModel = (SearchHostViewModel) new ViewModelProvider(fragmentActivityRequireActivity, getSearchViewModelFactory()).get(SearchHostViewModel.class);
        if (getPageUriFromExtras(savedInstanceState) == null) {
            searchHostViewModel.setPageUri(getPageUriFromExtras(getArguments()));
            markPageUriAsInitialized(getArguments());
        }
        if (getExperienceModeFromExtras(savedInstanceState) == null) {
            searchHostViewModel.setInitialExperienceMode(getExperienceModeFromExtras(getArguments()));
            markPageAsInitialized(getArguments());
        }
        return searchHostViewModel;
    }

    @Override // com.amazon.mp3.find.view.BaseHostFragment
    protected BauhausActionBarView createActionBarView(FragmentActivity fragmentActivity, String title, String subTitle) {
        BauhausActionBarView bauhausActionBarView;
        Intrinsics.checkNotNullParameter(fragmentActivity, "fragmentActivity");
        FindSearchBarView findSearchBarView = new FindSearchBarView(fragmentActivity);
        if (((SearchHostViewModel) getViewModel()).showIvyRefinementPills()) {
            BauhausFilterActionBarView bauhausFilterActionBarView = new BauhausFilterActionBarView(getActivity(), null, false, false, findSearchBarView);
            if (((SearchHostViewModel) getViewModel()).hasFilterItemModels()) {
                setupActionBarPills(bauhausFilterActionBarView);
                bauhausActionBarView = bauhausFilterActionBarView;
            } else {
                bauhausFilterActionBarView.showFilterButton(false);
                bauhausActionBarView = bauhausFilterActionBarView;
            }
        } else {
            FragmentActivity activity = getActivity();
            Boolean bool = Boolean.FALSE;
            bauhausActionBarView = new BauhausActionBarView(activity, null, bool, bool, findSearchBarView);
        }
        bauhausActionBarView.hideFeedButton();
        bauhausActionBarView.setLayoutTransition(null);
        SearchHostViewModel.ActionBarState actionBarState = getSearchViewModel().getActionBarState();
        if (actionBarState != null) {
            configureActionBar(bauhausActionBarView, actionBarState);
            configureFindSearchBar(findSearchBarView, actionBarState);
        }
        return bauhausActionBarView;
    }

    @Override // com.amazon.mp3.find.view.BaseHostFragment
    protected void setupActionBarPills(BauhausFilterActionBarView actionBar) {
        Intrinsics.checkNotNullParameter(actionBar, "actionBar");
        boolean zIsEnabled = Feature.use_optimized_observable_browse_views_factory.isEnabled();
        List<FilterItemModel> filterItemModels = getViewModel().getFilterItemModels();
        actionBar.setFilters(filterItemModels);
        StyleSheet mStyleSheet = getMStyleSheet();
        if (mStyleSheet == null) {
            return;
        }
        RefinementPillBinder refinementPillBinder = new RefinementPillBinder(mStyleSheet, this.mRefinementClickListenerProvider);
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        FragmentActivity fragmentActivityRequireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity2, "requireActivity()");
        actionBar.setAdapterProvider(new BaseViewModelAdapterProvider(fragmentActivityRequireActivity, null, LifecycleOwnerKt.getLifecycleScope(fragmentActivityRequireActivity2), zIsEnabled), refinementPillBinder, filterItemModels);
    }

    protected void setBottomBarTab() {
        if (BottomNavigationBarContainer.getInstance().isEnabled()) {
            BottomNavigationBarContainer.getInstance().setBottomBarTab(LastViewedScreenUtil.LastViewedScreen.SEARCH);
        }
    }

    @Override // com.amazon.mp3.find.view.BaseHostFragment
    protected void afterRenderingCompleted(FindPageGridViewModelContext pageGridViewModelContext) {
        Boolean searchViewFocus;
        if (pageGridViewModelContext == null || !pageGridViewModelContext.getExecutionContext().getRequestSearchViewFocus() || (searchViewFocus = pageGridViewModelContext.getExecutionContext().getSearchViewFocus()) == null) {
            return;
        }
        updateSearchViewFocus(searchViewFocus.booleanValue());
    }

    public final SearchHostViewModel getSearchViewModel() {
        return getViewModel();
    }
}
