package com.amazon.mp3.catalog.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.Observer;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.view.RefinementOverflowContainer;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
import com.amazon.music.views.library.models.FilterCollectionModel;
import com.amazon.music.views.library.models.FilterModel;
import com.amazon.music.views.library.providers.RefinementCallback;
import com.amazon.music.views.library.providers.SortOrderChangeProvider;
import com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.RefinementOverflowView;
import com.google.firebase.perf.util.Constants;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RefinementOverflowDialog.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0016\u0018\u0000 ^2\u00020\u00012\u00020\u0002:\u0001^B\u000f\u0012\u0006\u00100\u001a\u00020/¢\u0006\u0004\b\\\u0010]J\b\u0010\u0004\u001a\u00020\u0003H\u0002J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0002J\b\u0010\b\u001a\u00020\u0003H\u0002J\b\u0010\t\u001a\u00020\u0003H\u0002J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\nH\u0002J\u0012\u0010\u000f\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010\u0010\u001a\u00020\u0003H\u0016J&\u0010\u0015\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0012\u001a\u00020\u00112\b\u0010\u0014\u001a\u0004\u0018\u00010\u00132\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0016J\u001a\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010\u0017\u001a\u00020\u0003H\u0016J\u001a\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\n2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0016J\u001a\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\n2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001cJ\u000e\u0010 \u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\nJ\u001e\u0010&\u001a\u00020\u00032\u000e\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\"0!2\u0006\u0010%\u001a\u00020$J\u0010\u0010)\u001a\u00020\u00032\u0006\u0010(\u001a\u00020'H\u0016J\u0010\u0010+\u001a\u00020\u00032\u0006\u0010*\u001a\u00020\rH\u0016J\u0012\u0010.\u001a\u00020\u00032\b\u0010-\u001a\u0004\u0018\u00010,H\u0016R\u001a\u00100\u001a\u00020/8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b0\u00101\u001a\u0004\b2\u00103R*\u00105\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u0001048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b5\u00106\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\"\u0010<\u001a\u00020;8\u0016@\u0016X\u0096.¢\u0006\u0012\n\u0004\b<\u0010=\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\u0016\u0010C\u001a\u00020B8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bC\u0010DR\u0016\u0010F\u001a\u00020E8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bF\u0010GR\u001e\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\"0!8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b#\u0010HR$\u0010J\u001a\u0004\u0018\u00010I8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\bJ\u0010K\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR$\u0010P\u001a\u0004\u0018\u00010I8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\bP\u0010K\u001a\u0004\bQ\u0010M\"\u0004\bR\u0010OR\u0016\u0010S\u001a\u00020$8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bS\u0010TR\u0016\u0010U\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bU\u0010VR\u0016\u0010W\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bW\u0010VR\u0016\u0010X\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bX\u0010VR\u0018\u0010Y\u001a\u0004\u0018\u00010\u001c8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bY\u0010ZR\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010[¨\u0006_"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/RefinementOverflowDialog;", "Landroidx/fragment/app/DialogFragment;", "Lcom/amazon/mp3/view/RefinementOverflowContainer;", "", "prepareBackButtonFunctionality", "Landroid/view/View;", "view", "prepareDialogViewFunctionality", "setRefinementActionSheetOffset", "showFilterCategory", "", "showCategory", "updateVisibility", "Landroid/os/Bundle;", "savedInstanceState", "onCreate", "onPause", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "onCreateView", "onViewCreated", "onStart", "isShown", "Lcom/amazon/music/views/library/providers/SortOrderChangeProvider;", "sortOrderChangeProvider", "showSortButton", "Landroid/view/View$OnClickListener;", "onClickListener", "showApplyButton", Constants.ENABLE_DISABLE, "setApplyButtonEnabled", "", "Lcom/amazon/music/views/library/models/FilterModel;", "filters", "", "title", "setFilters", "Landroid/content/DialogInterface;", "dialog", "onDismiss", "outState", "onSaveInstanceState", "", "content", "onClick", "Lcom/amazon/music/views/library/providers/RefinementCallback;", "refinementCallback", "Lcom/amazon/music/views/library/providers/RefinementCallback;", "getRefinementCallback", "()Lcom/amazon/music/views/library/providers/RefinementCallback;", "Lkotlin/Function0;", "onDismissedListener", "Lkotlin/jvm/functions/Function0;", "getOnDismissedListener", "()Lkotlin/jvm/functions/Function0;", "setOnDismissedListener", "(Lkotlin/jvm/functions/Function0;)V", "Lcom/amazon/music/views/library/views/RefinementOverflowView;", "refinementOverflowView", "Lcom/amazon/music/views/library/views/RefinementOverflowView;", "getRefinementOverflowView", "()Lcom/amazon/music/views/library/views/RefinementOverflowView;", "setRefinementOverflowView", "(Lcom/amazon/music/views/library/views/RefinementOverflowView;)V", "Landroidx/constraintlayout/widget/ConstraintLayout;", SearchMetricsServiceDefault.CONTENT_NAME_FILTER_DIALOG, "Landroidx/constraintlayout/widget/ConstraintLayout;", "Landroid/widget/FrameLayout;", "backButton", "Landroid/widget/FrameLayout;", "Ljava/util/List;", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "currentFilterCollection", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "getCurrentFilterCollection", "()Lcom/amazon/music/views/library/models/FilterCollectionModel;", "setCurrentFilterCollection", "(Lcom/amazon/music/views/library/models/FilterCollectionModel;)V", "singleCollectionModel", "getSingleCollectionModel", "setSingleCollectionModel", "categoryTitle", "Ljava/lang/String;", "isSortButtonShown", "Z", "isApplyButtonShown", "isApplyButtonEnabled", "applyButtonClickListener", "Landroid/view/View$OnClickListener;", "Lcom/amazon/music/views/library/providers/SortOrderChangeProvider;", "<init>", "(Lcom/amazon/music/views/library/providers/RefinementCallback;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class RefinementOverflowDialog extends DialogFragment implements RefinementOverflowContainer {
    private View.OnClickListener applyButtonClickListener;
    private FrameLayout backButton;
    private String categoryTitle;
    private FilterCollectionModel currentFilterCollection;
    private ConstraintLayout filterDialog;
    private List<? extends FilterModel> filters;
    private boolean isApplyButtonEnabled;
    private boolean isApplyButtonShown;
    private boolean isSortButtonShown;
    private Function0<Unit> onDismissedListener;
    private final RefinementCallback refinementCallback;
    public RefinementOverflowView refinementOverflowView;
    private FilterCollectionModel singleCollectionModel;
    private SortOrderChangeProvider sortOrderChangeProvider;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String ARG_FILTER_COLLECTION_KEY = "filters";
    private static final String ARG_FITLER_TITLE_KEY = "title";
    private static final String TAG = RefinementOverflowDialog.class.getSimpleName();

    @Override // com.amazon.mp3.view.RefinementOverflowContainer
    public RefinementCallback getRefinementCallback() {
        return this.refinementCallback;
    }

    @Override // com.amazon.music.views.library.providers.StateChangeProvider
    public void onResetState(String str) {
        RefinementOverflowContainer.DefaultImpls.onResetState(this, str);
    }

    public void setCatalogModel(FilterCollectionModel filterCollectionModel) {
        RefinementOverflowContainer.DefaultImpls.setCatalogModel(this, filterCollectionModel);
    }

    public RefinementOverflowDialog(RefinementCallback refinementCallback) {
        Intrinsics.checkNotNullParameter(refinementCallback, "refinementCallback");
        this.refinementCallback = refinementCallback;
        this.categoryTitle = "Filters";
    }

    @Override // com.amazon.mp3.view.RefinementOverflowContainer
    public RefinementOverflowView getRefinementOverflowView() {
        RefinementOverflowView refinementOverflowView = this.refinementOverflowView;
        if (refinementOverflowView != null) {
            return refinementOverflowView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("refinementOverflowView");
        return null;
    }

    public void setRefinementOverflowView(RefinementOverflowView refinementOverflowView) {
        Intrinsics.checkNotNullParameter(refinementOverflowView, "<set-?>");
        this.refinementOverflowView = refinementOverflowView;
    }

    @Override // com.amazon.mp3.view.RefinementOverflowContainer
    public FilterCollectionModel getCurrentFilterCollection() {
        return this.currentFilterCollection;
    }

    @Override // com.amazon.mp3.view.RefinementOverflowContainer
    public void setCurrentFilterCollection(FilterCollectionModel filterCollectionModel) {
        this.currentFilterCollection = filterCollectionModel;
    }

    @Override // com.amazon.mp3.view.RefinementOverflowContainer
    public FilterCollectionModel getSingleCollectionModel() {
        return this.singleCollectionModel;
    }

    @Override // com.amazon.mp3.view.RefinementOverflowContainer
    public void setSingleCollectionModel(FilterCollectionModel filterCollectionModel) {
        this.singleCollectionModel = filterCollectionModel;
    }

    /* JADX INFO: compiled from: RefinementOverflowDialog.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J.\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0010\u0010\f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0018\u00010\r2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0019\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/RefinementOverflowDialog$Companion;", "", "()V", "ARG_FILTER_COLLECTION_KEY", "", "ARG_FITLER_TITLE_KEY", "TAG", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "newInstance", "Lcom/amazon/mp3/catalog/fragment/RefinementOverflowDialog;", "filters", "", "Lcom/amazon/music/views/library/models/FilterModel;", "title", "refinementCallback", "Lcom/amazon/music/views/library/providers/RefinementCallback;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG() {
            return RefinementOverflowDialog.TAG;
        }

        public static /* synthetic */ RefinementOverflowDialog newInstance$default(Companion companion, List list, String str, RefinementCallback refinementCallback, int i, Object obj) {
            if ((i & 2) != 0) {
                str = null;
            }
            return companion.newInstance(list, str, refinementCallback);
        }

        public final RefinementOverflowDialog newInstance(List<? extends FilterModel> filters, String title, RefinementCallback refinementCallback) {
            Intrinsics.checkNotNullParameter(refinementCallback, "refinementCallback");
            if (filters == null) {
                return null;
            }
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(RefinementOverflowDialog.ARG_FILTER_COLLECTION_KEY, new ArrayList<>(filters));
            if (title != null) {
                bundle.putString(RefinementOverflowDialog.ARG_FITLER_TITLE_KEY, title);
            }
            RefinementOverflowDialog refinementOverflowDialog = new RefinementOverflowDialog(refinementCallback);
            refinementOverflowDialog.setArguments(bundle);
            return refinementOverflowDialog;
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean z = false;
        setStyle(0, R.style.RefinementDialog);
        Bundle arguments = getArguments();
        ArrayList parcelableArrayList = arguments == null ? null : arguments.getParcelableArrayList(ARG_FILTER_COLLECTION_KEY);
        Intrinsics.checkNotNull(parcelableArrayList);
        Intrinsics.checkNotNullExpressionValue(parcelableArrayList, "arguments?.getParcelable…_FILTER_COLLECTION_KEY)!!");
        this.filters = parcelableArrayList;
        Bundle arguments2 = getArguments();
        if (arguments2 != null && arguments2.containsKey(ARG_FITLER_TITLE_KEY)) {
            z = true;
        }
        if (z) {
            Bundle arguments3 = getArguments();
            String string = arguments3 != null ? arguments3.getString(ARG_FITLER_TITLE_KEY) : null;
            if (string == null) {
                string = this.categoryTitle;
            }
            this.categoryTitle = string;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        dismissAllowingStateLoss();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View view = inflater.inflate(R.layout.filter_selection_dialog_layout, container, false);
        View viewFindViewById = view.findViewById(R.id.back_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "view.findViewById(R.id.back_button)");
        this.backButton = (FrameLayout) viewFindViewById;
        View viewFindViewById2 = view.findViewById(R.id.refinement_overflow);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "view.findViewById(R.id.refinement_overflow)");
        setRefinementOverflowView((RefinementOverflowView) viewFindViewById2);
        View viewFindViewById3 = view.findViewById(R.id.filter_dialog);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "view.findViewById(R.id.filter_dialog)");
        this.filterDialog = (ConstraintLayout) viewFindViewById3;
        prepareBackButtonFunctionality();
        Intrinsics.checkNotNullExpressionValue(view, "view");
        prepareDialogViewFunctionality(view);
        setRefinementActionSheetOffset();
        return view;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        final boolean zIsEnabled = Feature.use_optimized_observable_browse_views_factory.isEnabled();
        StyleSheetProvider.getStyleSheet().observe(this, new Observer() { // from class: com.amazon.mp3.catalog.fragment.RefinementOverflowDialog$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                RefinementOverflowDialog.m530onViewCreated$lambda1(this.f$0, zIsEnabled, (StyleSheet) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-1, reason: not valid java name */
    public static final void m530onViewCreated$lambda1(final RefinementOverflowDialog this$0, boolean z, StyleSheet it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        RefinementOverflowView refinementOverflowView = this$0.getRefinementOverflowView();
        BaseViewModelAdapterProvider baseViewModelAdapterProvider = new BaseViewModelAdapterProvider(this$0, null, LifecycleOwnerKt.getLifecycleScope(this$0), z);
        Intrinsics.checkNotNullExpressionValue(it, "it");
        refinementOverflowView.init(baseViewModelAdapterProvider, it, this$0);
        this$0.getRefinementOverflowView().setResetButtonClickListener(this$0);
        this$0.getRefinementOverflowView().showApplyButton(this$0.isApplyButtonShown, this$0.isApplyButtonEnabled, this$0.applyButtonClickListener);
        this$0.getRefinementOverflowView().showSortButton(this$0.isSortButtonShown, new Function1<SortOrderChangeProvider.SortOrder, Unit>() { // from class: com.amazon.mp3.catalog.fragment.RefinementOverflowDialog$onViewCreated$1$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SortOrderChangeProvider.SortOrder sortOrder) {
                invoke2(sortOrder);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SortOrderChangeProvider.SortOrder sortOrder) {
                Intrinsics.checkNotNullParameter(sortOrder, "sortOrder");
                SortOrderChangeProvider sortOrderChangeProvider = this.this$0.sortOrderChangeProvider;
                if (sortOrderChangeProvider == null) {
                    return;
                }
                sortOrderChangeProvider.onSortOrderChanged(sortOrder);
            }
        });
        this$0.getRefinementOverflowView().showDismissButton(true, new View.OnClickListener() { // from class: com.amazon.mp3.catalog.fragment.RefinementOverflowDialog$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RefinementOverflowDialog.m531onViewCreated$lambda1$lambda0(this.f$0, view);
            }
        });
        this$0.showFilterCategory();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-1$lambda-0, reason: not valid java name */
    public static final void m531onViewCreated$lambda1$lambda0(RefinementOverflowDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
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

    public void showSortButton(boolean isShown, SortOrderChangeProvider sortOrderChangeProvider) {
        this.isSortButtonShown = isShown;
        this.sortOrderChangeProvider = sortOrderChangeProvider;
        if (this.refinementOverflowView != null) {
            getRefinementOverflowView().showSortButton(this.isSortButtonShown, new Function1<SortOrderChangeProvider.SortOrder, Unit>() { // from class: com.amazon.mp3.catalog.fragment.RefinementOverflowDialog.showSortButton.2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SortOrderChangeProvider.SortOrder sortOrder) {
                    invoke2(sortOrder);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SortOrderChangeProvider.SortOrder it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    SortOrderChangeProvider sortOrderChangeProvider2 = RefinementOverflowDialog.this.sortOrderChangeProvider;
                    if (sortOrderChangeProvider2 == null) {
                        return;
                    }
                    sortOrderChangeProvider2.onSortOrderChanged(it);
                }
            });
        }
    }

    public static /* synthetic */ void showApplyButton$default(RefinementOverflowDialog refinementOverflowDialog, boolean z, View.OnClickListener onClickListener, int i, Object obj) {
        if ((i & 2) != 0) {
            onClickListener = null;
        }
        refinementOverflowDialog.showApplyButton(z, onClickListener);
    }

    public final void showApplyButton(boolean isShown, View.OnClickListener onClickListener) {
        this.isApplyButtonShown = isShown;
        this.applyButtonClickListener = onClickListener;
        if (this.refinementOverflowView != null) {
            getRefinementOverflowView().showApplyButton(isShown, this.isApplyButtonEnabled, onClickListener);
        }
    }

    public final void setApplyButtonEnabled(boolean isEnabled) {
        this.isApplyButtonEnabled = isEnabled;
        if (this.refinementOverflowView != null) {
            getRefinementOverflowView().showApplyButton(this.isApplyButtonShown, isEnabled, this.applyButtonClickListener);
        }
    }

    public final void setFilters(List<? extends FilterModel> filters, String title) {
        Intrinsics.checkNotNullParameter(filters, "filters");
        Intrinsics.checkNotNullParameter(title, "title");
        this.filters = filters;
        this.categoryTitle = title;
        if (isVisible()) {
            getRefinementOverflowView().display(filters, title);
        }
        Bundle arguments = getArguments();
        if (arguments != null) {
            arguments.putParcelableArrayList(ARG_FILTER_COLLECTION_KEY, new ArrayList<>(filters));
        }
        Bundle arguments2 = getArguments();
        if (arguments2 == null) {
            return;
        }
        arguments2.putString(ARG_FITLER_TITLE_KEY, title);
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        super.onDismiss(dialog);
        Function0<Unit> function0 = this.onDismissedListener;
        if (function0 == null) {
            return;
        }
        function0.invoke();
    }

    private final void prepareBackButtonFunctionality() {
        FrameLayout frameLayout = this.backButton;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backButton");
            frameLayout = null;
        }
        frameLayout.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.catalog.fragment.RefinementOverflowDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RefinementOverflowDialog.m532prepareBackButtonFunctionality$lambda2(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: prepareBackButtonFunctionality$lambda-2, reason: not valid java name */
    public static final void m532prepareBackButtonFunctionality$lambda2(RefinementOverflowDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showFilterCategory();
    }

    private final void prepareDialogViewFunctionality(View view) {
        view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.catalog.fragment.RefinementOverflowDialog$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RefinementOverflowDialog.m533prepareDialogViewFunctionality$lambda3(this.f$0, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: prepareDialogViewFunctionality$lambda-3, reason: not valid java name */
    public static final void m533prepareDialogViewFunctionality$lambda3(RefinementOverflowDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    private final void setRefinementActionSheetOffset() {
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.action_bar_height) + getResources().getDimensionPixelSize(R.dimen.spacer_32);
        ConstraintLayout constraintLayout = this.filterDialog;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException(SearchMetricsServiceDefault.CONTENT_NAME_FILTER_DIALOG);
            constraintLayout = null;
        }
        constraintLayout.setPadding(0, dimensionPixelSize, 0, 0);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
    }

    private final void showFilterCategory() {
        RefinementOverflowView refinementOverflowView = getRefinementOverflowView();
        List<? extends FilterModel> list = this.filters;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filters");
            list = null;
        }
        refinementOverflowView.display(list, this.categoryTitle);
        updateVisibility(true);
        setCurrentFilterCollection(null);
    }

    @Override // com.amazon.music.views.library.providers.SimpleClickListenerProvider
    public void onClick(Object content) {
        RefinementOverflowContainer.DefaultImpls.onClick(this, content);
        if (content instanceof FilterCollectionModel) {
            updateVisibility(false);
        }
    }

    private final void updateVisibility(boolean showCategory) {
        FrameLayout frameLayout = this.backButton;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backButton");
            frameLayout = null;
        }
        frameLayout.setVisibility(showCategory ? 8 : 0);
    }
}
