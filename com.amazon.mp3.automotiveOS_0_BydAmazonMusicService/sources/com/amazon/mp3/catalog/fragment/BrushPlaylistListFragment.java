package com.amazon.mp3.catalog.fragment;

import android.app.Application;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.amazon.mp3.actionbar.view.FilterActionBarView;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel;
import com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModelFactory;
import com.amazon.mp3.fragment.navigation.BrushFragmentNavigation;
import com.amazon.music.views.library.models.FilterItemModel;
import com.amazon.music.views.library.models.PageGridViewModel;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushPlaylistListFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b'\u0010(J\u0012\u0010\u0006\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0016J&\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\b\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0016J\b\u0010\r\u001a\u00020\u0005H\u0016J\b\u0010\u000e\u001a\u00020\u0005H\u0014J\b\u0010\u000f\u001a\u00020\u0005H\u0014J\u0012\u0010\u0012\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010\u0013\u001a\u00020\u0005H\u0014J\u0010\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0003H\u0016J\b\u0010\u0016\u001a\u00020\u0005H\u0014R\u0014\u0010\u0018\u001a\u00020\u00178\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R#\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u001a8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR$\u0010!\u001a\u0004\u0018\u00010 8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&¨\u0006)"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/BrushPlaylistListFragment;", "Lcom/amazon/mp3/catalog/fragment/BrushFragment;", "Lcom/amazon/mp3/catalog/fragment/FilterableFragment;", "Landroid/os/Bundle;", "savedInstanceState", "", "onCreate", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/view/View;", "onCreateView", "onResume", "initActionBarView", "initDefaultViews", "Lcom/amazon/music/views/library/models/FilterItemModel;", "filterModel", "loadFilteredPage", "handleSwipeRefresh", "outState", "onSaveInstanceState", "initPageDataModel", "", "ACTIVE_FILTER_KEY", "Ljava/lang/String;", "Landroidx/lifecycle/MutableLiveData;", "activeFilter$delegate", "Lkotlin/Lazy;", "getActiveFilter", "()Landroidx/lifecycle/MutableLiveData;", "activeFilter", "Lcom/amazon/mp3/actionbar/view/FilterActionBarView;", "actionBarView", "Lcom/amazon/mp3/actionbar/view/FilterActionBarView;", "getActionBarView", "()Lcom/amazon/mp3/actionbar/view/FilterActionBarView;", "setActionBarView", "(Lcom/amazon/mp3/actionbar/view/FilterActionBarView;)V", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushPlaylistListFragment extends BrushFragment implements FilterableFragment {
    private FilterActionBarView actionBarView;
    private final String ACTIVE_FILTER_KEY = "active_filter";

    /* JADX INFO: renamed from: activeFilter$delegate, reason: from kotlin metadata */
    private final Lazy activeFilter = LazyKt__LazyJVMKt.lazy(new Function0<MutableLiveData<FilterItemModel>>() { // from class: com.amazon.mp3.catalog.fragment.BrushPlaylistListFragment$activeFilter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final MutableLiveData<FilterItemModel> invoke() {
            return new MutableLiveData<>();
        }
    });

    @Override // com.amazon.mp3.catalog.fragment.FilterableFragment
    public MutableLiveData<FilterItemModel> getActiveFilter() {
        return (MutableLiveData) this.activeFilter.getValue();
    }

    public final void setActionBarView(FilterActionBarView filterActionBarView) {
        this.actionBarView = filterActionBarView;
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        getActiveFilter().setValue(savedInstanceState == null ? null : (FilterItemModel) savedInstanceState.getParcelable(this.ACTIVE_FILTER_KEY));
        super.onCreate(savedInstanceState);
        getActiveFilter().observe(this, new Observer() { // from class: com.amazon.mp3.catalog.fragment.BrushPlaylistListFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BrushPlaylistListFragment.m470onCreate$lambda0(this.f$0, (FilterItemModel) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-0, reason: not valid java name */
    public static final void m470onCreate$lambda0(BrushPlaylistListFragment this$0, FilterItemModel filterItemModel) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.loadFilteredPage(filterItemModel);
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment
    protected void initActionBarView() {
        FragmentActivity activity;
        Resources resources;
        PageGridViewModel value = this.mPageDataModel.getPageModelLiveData().getValue();
        if (value == null) {
            return;
        }
        String title = value.getTitle();
        if (title == null) {
            FragmentActivity activity2 = getActivity();
            title = (activity2 == null || (resources = activity2.getResources()) == null) ? null : resources.getString(R.string.dmusic_library_playlists_tab);
        }
        if (this.actionBarView == null && (activity = getActivity()) != null) {
            setActionBarView(new FilterActionBarView(activity, title, value.getFilters(), this));
        }
        MusicHomeActivity musicHomeActivity = (MusicHomeActivity) getActivity();
        if (musicHomeActivity != null) {
            musicHomeActivity.setHeaderView(this.actionBarView);
        }
        MusicHomeActivity musicHomeActivity2 = (MusicHomeActivity) getActivity();
        if (musicHomeActivity2 != null) {
            musicHomeActivity2.requestHomeButtonAsBack();
        }
        FilterActionBarView filterActionBarView = this.actionBarView;
        if (filterActionBarView == null) {
            return;
        }
        filterActionBarView.setLayoutParams(new Toolbar.LayoutParams(-1, -2));
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment
    protected void initDefaultViews() {
        initActionBarView();
    }

    public void loadFilteredPage(FilterItemModel filterModel) {
        BrushViewModel brushViewModel;
        if (filterModel != null) {
            String browseId = filterModel.getBrowseId();
            if (browseId == null || (brushViewModel = this.mPageDataModel) == null) {
                return;
            }
            brushViewModel.fetchFilteredPageData(browseId);
            return;
        }
        BrushViewModel brushViewModel2 = this.mPageDataModel;
        if (brushViewModel2 == null) {
            return;
        }
        brushViewModel2.fetchData(0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.mp3.catalog.fragment.BrushFragment
    /* JADX INFO: renamed from: handleSwipeRefresh */
    public void lambda$setupLayoutAndScrollPosition$9() {
        if (getActiveFilter().getValue() == null) {
            super.lambda$setupLayoutAndScrollPosition$9();
        } else {
            this.brushPageScrollHelper.reset();
            loadFilteredPage(getActiveFilter().getValue());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        outState.putParcelable(this.ACTIVE_FILTER_KEY, getActiveFilter().getValue());
        super.onSaveInstanceState(outState);
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment
    protected void initPageDataModel() {
        Application application;
        Bundle arguments = getArguments();
        if (arguments != null && !TextUtils.isEmpty(arguments.getString("brush_uri_extra"))) {
            String string = arguments.getString("brush_uri_extra");
            Intrinsics.checkNotNull(string);
            Intrinsics.checkNotNullExpressionValue(string, "args?.getString(BRUSH_URI_EXTRA)!!");
            this.mPageUri = BrushFragmentNavigation.generateUri(string, arguments);
        }
        BrushViewModelFactory brushViewModelFactory = null;
        brushViewModelFactory = null;
        String string2 = arguments == null ? null : arguments.getString("brush_nextpagetoken_extra");
        FragmentActivity activity = getActivity();
        if (activity != null && (application = activity.getApplication()) != null) {
            String mPageUri = this.mPageUri;
            Intrinsics.checkNotNullExpressionValue(mPageUri, "mPageUri");
            boolean z = false;
            boolean z2 = false;
            FilterItemModel value = getActiveFilter().getValue();
            brushViewModelFactory = new BrushViewModelFactory(application, mPageUri, string2, z, z2, value != null ? value.getBrowseId() : null, null, 64, null);
        }
        BrushViewModel brushViewModel = (BrushViewModel) ViewModelProviders.of(this, brushViewModelFactory).get(BrushViewModel.class);
        this.mPageDataModel = brushViewModel;
        this.mLibraryStateProvider = brushViewModel.getLibraryStateProvider();
    }
}
