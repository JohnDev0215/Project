package com.amazon.mp3.catalog.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.adapter.FilterCategoryAdapter;
import com.amazon.mp3.library.adapter.FilterDialogAdapter;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiPageViewEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.models.FilterCollectionModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FilterSelectionDialogFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 ;2\u00020\u0001:\u0001;B\u0007¢\u0006\u0004\b9\u0010:J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0002J \u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nH\u0002J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u000eH\u0002J\u0012\u0010\u0013\u001a\u00020\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0016J\b\u0010\u0014\u001a\u00020\u0004H\u0016J&\u0010\u0019\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0016\u001a\u00020\u00152\b\u0010\u0018\u001a\u0004\u0018\u00010\u00172\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0016J\b\u0010\u001a\u001a\u00020\u0004H\u0016J\u0010\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u0011H\u0016J\u0006\u0010\u001d\u001a\u00020\u0004J\u000e\u0010 \u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u001eR\u0016\u0010\"\u001a\u00020!8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\"\u0010#R\u001a\u0010%\u001a\u0006\u0012\u0002\b\u00030$8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b%\u0010&R\u0016\u0010(\u001a\u00020'8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b(\u0010)R\u0016\u0010*\u001a\u00020!8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b*\u0010#R\u001a\u0010+\u001a\u0006\u0012\u0002\b\u00030$8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b+\u0010&R\u0016\u0010,\u001a\u00020'8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b,\u0010)R\u0016\u0010.\u001a\u00020-8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b.\u0010/R\u0016\u00100\u001a\u00020-8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b0\u0010/R\u0016\u00102\u001a\u0002018\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b2\u00103R\u0016\u00104\u001a\u0002018\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b4\u00103R\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\f\u00105R\u0016\u00107\u001a\u0002068\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b7\u00108¨\u0006<"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/FilterSelectionDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "Landroid/view/View;", "view", "", "prepareDismissButtonFunctionality", "prepareBackButtonFunctionality", "", "showCategory", "updateVisibility", "", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "filters", "appendAllMoodsAndGenresModel", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "pageType", "sendUiPageViewMetric", "Landroid/os/Bundle;", "savedInstanceState", "onCreate", "onPause", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "onCreateView", "onStart", "outState", "onSaveInstanceState", "showFilterCategory", "", "position", "showFilterDetail", "Landroidx/recyclerview/widget/RecyclerView;", "filterSelectionRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "viewAdapter", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/LinearLayoutManager;", "viewManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "categoryRecyclerView", "categoryViewAdapter", "categoryViewManager", "Landroid/widget/TextView;", "filterTitle", "Landroid/widget/TextView;", "detailFilterTitle", "Landroid/widget/FrameLayout;", "backButton", "Landroid/widget/FrameLayout;", "dismissButton", "Ljava/util/List;", "Lcom/amazon/mp3/catalog/fragment/FilterableFragment;", "filterableFragment", "Lcom/amazon/mp3/catalog/fragment/FilterableFragment;", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class FilterSelectionDialogFragment extends DialogFragment {
    private static final String ARG_FILTER_COLLECTION_KEY;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private static final String TAG;
    private FrameLayout backButton;
    private RecyclerView categoryRecyclerView;
    private RecyclerView.Adapter<?> categoryViewAdapter;
    private LinearLayoutManager categoryViewManager;
    private TextView detailFilterTitle;
    private FrameLayout dismissButton;
    private RecyclerView filterSelectionRecyclerView;
    private TextView filterTitle;
    private FilterableFragment filterableFragment;
    private List<FilterCollectionModel> filters;
    private RecyclerView.Adapter<?> viewAdapter;
    private LinearLayoutManager viewManager;

    /* JADX INFO: compiled from: FilterSelectionDialogFragment.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\t\u001a\u0004\u0018\u00010\n2\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0019\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/FilterSelectionDialogFragment$Companion;", "", "()V", "ARG_FILTER_COLLECTION_KEY", "", "TAG", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "newInstance", "Lcom/amazon/mp3/catalog/fragment/FilterSelectionDialogFragment;", "filters", "", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG() {
            return FilterSelectionDialogFragment.TAG;
        }

        public final FilterSelectionDialogFragment newInstance(List<FilterCollectionModel> filters) {
            if (filters == null) {
                Log.debug(getTAG(), "New instance called with filters = null");
                return null;
            }
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(FilterSelectionDialogFragment.ARG_FILTER_COLLECTION_KEY, new ArrayList<>(filters));
            FilterSelectionDialogFragment filterSelectionDialogFragment = new FilterSelectionDialogFragment();
            filterSelectionDialogFragment.setArguments(bundle);
            return filterSelectionDialogFragment;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        ARG_FILTER_COLLECTION_KEY = "filters";
        TAG = companion.getClass().getSimpleName();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LifecycleOwner targetFragment = getTargetFragment();
        if (targetFragment == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.catalog.fragment.FilterableFragment");
        }
        this.filterableFragment = (FilterableFragment) targetFragment;
        setStyle(0, R.style.SheetDialog);
        Bundle arguments = getArguments();
        ArrayList arrayList = null;
        ArrayList parcelableArrayList = arguments == null ? null : arguments.getParcelableArrayList(ARG_FILTER_COLLECTION_KEY);
        Intrinsics.checkNotNull(parcelableArrayList);
        Intrinsics.checkNotNullExpressionValue(parcelableArrayList, "arguments?.getParcelable…_FILTER_COLLECTION_KEY)!!");
        this.filters = parcelableArrayList;
        if (parcelableArrayList == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filters");
        } else {
            arrayList = parcelableArrayList;
        }
        List<FilterCollectionModel> listAppendAllMoodsAndGenresModel = appendAllMoodsAndGenresModel(arrayList);
        Intrinsics.checkNotNull(listAppendAllMoodsAndGenresModel);
        this.filters = listAppendAllMoodsAndGenresModel;
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        dismissAllowingStateLoss();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View view = inflater.inflate(R.layout.playlist_bottom_sheet_filter, container, false);
        View viewFindViewById = view.findViewById(R.id.back_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "view.findViewById(R.id.back_button)");
        this.backButton = (FrameLayout) viewFindViewById;
        View viewFindViewById2 = view.findViewById(R.id.detail_title_text);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "view.findViewById(R.id.detail_title_text)");
        this.detailFilterTitle = (TextView) viewFindViewById2;
        View viewFindViewById3 = view.findViewById(R.id.filter_selection_container);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "view.findViewById(R.id.filter_selection_container)");
        this.filterSelectionRecyclerView = (RecyclerView) viewFindViewById3;
        View viewFindViewById4 = view.findViewById(R.id.title_text);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "view.findViewById(R.id.title_text)");
        this.filterTitle = (TextView) viewFindViewById4;
        View viewFindViewById5 = view.findViewById(R.id.dismiss_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "view.findViewById(R.id.dismiss_button)");
        this.dismissButton = (FrameLayout) viewFindViewById5;
        View viewFindViewById6 = view.findViewById(R.id.filter_category_container);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "view.findViewById(R.id.filter_category_container)");
        this.categoryRecyclerView = (RecyclerView) viewFindViewById6;
        this.categoryViewManager = new LinearLayoutManager(getContext());
        this.viewManager = new LinearLayoutManager(getContext());
        Intrinsics.checkNotNullExpressionValue(view, "view");
        prepareBackButtonFunctionality(view);
        prepareDismissButtonFunctionality(view);
        showFilterCategory();
        sendUiPageViewMetric(PageType.BROWSE_PLAYLISTS_FILTER);
        return view;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        Window window;
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog == null || (window = dialog.getWindow()) == null) {
            return;
        }
        window.setLayout(-1, -1);
    }

    private final void prepareDismissButtonFunctionality(View view) {
        FrameLayout frameLayout = this.dismissButton;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dismissButton");
            frameLayout = null;
        }
        frameLayout.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.catalog.fragment.FilterSelectionDialogFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FilterSelectionDialogFragment.m479prepareDismissButtonFunctionality$lambda0(this.f$0, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: prepareDismissButtonFunctionality$lambda-0, reason: not valid java name */
    public static final void m479prepareDismissButtonFunctionality$lambda0(FilterSelectionDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    private final void prepareBackButtonFunctionality(View view) {
        FrameLayout frameLayout = this.backButton;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backButton");
            frameLayout = null;
        }
        frameLayout.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.catalog.fragment.FilterSelectionDialogFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FilterSelectionDialogFragment.m478prepareBackButtonFunctionality$lambda1(this.f$0, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: prepareBackButtonFunctionality$lambda-1, reason: not valid java name */
    public static final void m478prepareBackButtonFunctionality$lambda1(FilterSelectionDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showFilterCategory();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
    }

    public final void showFilterCategory() {
        List<FilterCollectionModel> list = this.filters;
        LinearLayoutManager linearLayoutManager = null;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filters");
            list = null;
        }
        FilterableFragment filterableFragment = this.filterableFragment;
        if (filterableFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filterableFragment");
            filterableFragment = null;
        }
        this.categoryViewAdapter = new FilterCategoryAdapter(list, this, filterableFragment);
        RecyclerView recyclerView = this.categoryRecyclerView;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("categoryRecyclerView");
            recyclerView = null;
        }
        LinearLayoutManager linearLayoutManager2 = this.categoryViewManager;
        if (linearLayoutManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("categoryViewManager");
            linearLayoutManager2 = null;
        }
        recyclerView.setLayoutManager(linearLayoutManager2);
        RecyclerView.Adapter<?> adapter = this.categoryViewAdapter;
        if (adapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("categoryViewAdapter");
            adapter = null;
        }
        recyclerView.setAdapter(adapter);
        Context context = recyclerView.getContext();
        LinearLayoutManager linearLayoutManager3 = this.categoryViewManager;
        if (linearLayoutManager3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("categoryViewManager");
        } else {
            linearLayoutManager = linearLayoutManager3;
        }
        recyclerView.addItemDecoration(new DividerItemDecoration(context, linearLayoutManager.getOrientation()));
        updateVisibility(true);
    }

    public final void showFilterDetail(int position) {
        List<FilterCollectionModel> list = this.filters;
        List<FilterCollectionModel> list2 = null;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filters");
            list = null;
        }
        if (position >= list.size()) {
            return;
        }
        List<FilterCollectionModel> list3 = this.filters;
        if (list3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filters");
            list3 = null;
        }
        FilterCollectionModel filterCollectionModel = list3.get(position);
        FilterableFragment filterableFragment = this.filterableFragment;
        if (filterableFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filterableFragment");
            filterableFragment = null;
        }
        this.viewAdapter = new FilterDialogAdapter(filterCollectionModel, this, filterableFragment);
        RecyclerView recyclerView = this.filterSelectionRecyclerView;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filterSelectionRecyclerView");
            recyclerView = null;
        }
        LinearLayoutManager linearLayoutManager = this.viewManager;
        if (linearLayoutManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewManager");
            linearLayoutManager = null;
        }
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.Adapter<?> adapter = this.viewAdapter;
        if (adapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewAdapter");
            adapter = null;
        }
        recyclerView.setAdapter(adapter);
        Context context = recyclerView.getContext();
        LinearLayoutManager linearLayoutManager2 = this.viewManager;
        if (linearLayoutManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewManager");
            linearLayoutManager2 = null;
        }
        recyclerView.addItemDecoration(new DividerItemDecoration(context, linearLayoutManager2.getOrientation()));
        TextView textView = this.detailFilterTitle;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("detailFilterTitle");
            textView = null;
        }
        List<FilterCollectionModel> list4 = this.filters;
        if (list4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filters");
        } else {
            list2 = list4;
        }
        textView.setText(list2.get(position).getTitle());
        updateVisibility(false);
    }

    private final void updateVisibility(boolean showCategory) {
        TextView textView = this.filterTitle;
        RecyclerView recyclerView = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filterTitle");
            textView = null;
        }
        textView.setVisibility(showCategory ? 0 : 8);
        FrameLayout frameLayout = this.dismissButton;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dismissButton");
            frameLayout = null;
        }
        frameLayout.setVisibility(showCategory ? 0 : 8);
        TextView textView2 = this.detailFilterTitle;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("detailFilterTitle");
            textView2 = null;
        }
        textView2.setVisibility(showCategory ? 8 : 0);
        FrameLayout frameLayout2 = this.backButton;
        if (frameLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backButton");
            frameLayout2 = null;
        }
        frameLayout2.setVisibility(showCategory ? 8 : 0);
        RecyclerView recyclerView2 = this.filterSelectionRecyclerView;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filterSelectionRecyclerView");
            recyclerView2 = null;
        }
        recyclerView2.setVisibility(showCategory ? 8 : 0);
        RecyclerView recyclerView3 = this.categoryRecyclerView;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("categoryRecyclerView");
            recyclerView3 = null;
        }
        recyclerView3.setVisibility(showCategory ? 0 : 8);
        RecyclerView recyclerView4 = this.filterSelectionRecyclerView;
        if (recyclerView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filterSelectionRecyclerView");
            recyclerView4 = null;
        }
        recyclerView4.setEnabled(!showCategory);
        RecyclerView recyclerView5 = this.categoryRecyclerView;
        if (recyclerView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("categoryRecyclerView");
        } else {
            recyclerView = recyclerView5;
        }
        recyclerView.setEnabled(showCategory);
    }

    private final List<FilterCollectionModel> appendAllMoodsAndGenresModel(List<FilterCollectionModel> filters) {
        if (filters == null) {
            return null;
        }
        List<FilterCollectionModel> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) filters);
        Context context = getContext();
        String string = context == null ? null : context.getString(R.string.dmusic_all_moods_and_genres);
        FilterCollectionModel filterCollectionModel = string != null ? new FilterCollectionModel(string, CollectionsKt__CollectionsKt.emptyList(), "", null, false, null, false, 120, null) : null;
        Intrinsics.checkNotNull(filterCollectionModel);
        mutableList.add(0, filterCollectionModel);
        return mutableList;
    }

    private final void sendUiPageViewMetric(PageType pageType) {
        UiPageViewEvent uiPageViewEventBuild = UiPageViewEvent.builder(pageType.getMetricsValue()).withViewType(ScreenUtil.getScreenViewType(getContext())).build();
        if (uiPageViewEventBuild != null) {
            MetricsHolder.getManager().handleEvent(uiPageViewEventBuild);
        }
    }
}
