package com.amazon.mp3.view.refinements;

import android.content.Context;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.catalog.fragment.RefinementOverflowDialog;
import com.amazon.mp3.view.RefinementOverflowPopupWindow;
import com.amazon.music.views.library.models.FilterCollectionModel;
import com.amazon.music.views.library.models.FilterItemModel;
import com.amazon.music.views.library.models.FilterModel;
import com.amazon.music.views.library.models.RefinementPillModel;
import com.amazon.music.views.library.providers.RefinementCallback;
import com.amazon.music.views.library.styles.StyleSheet;
import com.google.firebase.perf.util.Constants;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseViewsRefinementOverflowManager.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0001¢\u0006\u0002\u0010\u0007J\u0006\u0010\u0012\u001a\u00020\u0013J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u001a\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010 \u001a\u00020\u0013H\u0016J\u0010\u0010 \u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020\u0013H\u0002J\u000e\u0010#\u001a\u00020\u00132\u0006\u0010$\u001a\u00020\u0015J\b\u0010%\u001a\u00020\u0015H\u0002J\u0018\u0010&\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020!2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fJ,\u0010&\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020!2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010'\u001a\u00020\u000b2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010)J\u0010\u0010*\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020!H\u0002R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0006\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/amazon/mp3/view/refinements/BaseViewsRefinementOverflowManager;", "Lcom/amazon/music/views/library/providers/RefinementCallback;", "fragment", "Landroidx/fragment/app/Fragment;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "refinementCallback", "(Landroidx/fragment/app/Fragment;Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/providers/RefinementCallback;)V", "_fragment", "Ljava/lang/ref/WeakReference;", "currentSession", "Lcom/amazon/mp3/view/refinements/RefinementOverflowSession;", "getFragment", "()Landroidx/fragment/app/Fragment;", "refinementFilterActionSheetOverflow", "Lcom/amazon/mp3/catalog/fragment/RefinementOverflowDialog;", "refinementFilterViewOverflow", "Lcom/amazon/mp3/view/RefinementOverflowPopupWindow;", "dismissActionSheet", "", "isShowingRefinementActionSheetOverflow", "", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "onChildFilterClick", "filterModel", "Lcom/amazon/music/views/library/models/FilterModel;", "onClick", "model", "Lcom/amazon/music/views/library/models/RefinementPillModel;", "view", "Landroid/view/View;", "onFiltersReset", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "resetSession", "setApplyButtonEnabled", Constants.ENABLE_DISABLE, "shouldUseOverlayOverflow", "showRefinementOverflow", "refinementOverflowSession", "onApplyClickListener", "Landroid/view/View$OnClickListener;", "updateFilters", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BaseViewsRefinementOverflowManager implements RefinementCallback {
    private final WeakReference<Fragment> _fragment;
    private RefinementOverflowSession currentSession;
    private final RefinementCallback refinementCallback;
    private final RefinementOverflowDialog refinementFilterActionSheetOverflow;
    private final RefinementOverflowPopupWindow refinementFilterViewOverflow;

    private final boolean shouldUseOverlayOverflow() {
        return true;
    }

    public BaseViewsRefinementOverflowManager(Fragment fragment, StyleSheet styleSheet, RefinementCallback refinementCallback) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(refinementCallback, "refinementCallback");
        this.refinementCallback = refinementCallback;
        this._fragment = new WeakReference<>(fragment);
        Context contextRequireContext = fragment.requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "fragment.requireContext()");
        RefinementOverflowPopupWindow refinementOverflowPopupWindow = new RefinementOverflowPopupWindow(contextRequireContext, this);
        this.refinementFilterViewOverflow = refinementOverflowPopupWindow;
        refinementOverflowPopupWindow.initStyleSheet(styleSheet);
        this.refinementFilterActionSheetOverflow = RefinementOverflowDialog.Companion.newInstance$default(RefinementOverflowDialog.INSTANCE, CollectionsKt__CollectionsKt.emptyList(), null, this, 2, null);
    }

    private final Fragment getFragment() {
        return this._fragment.get();
    }

    public final void showRefinementOverflow(FilterCollectionModel model, View view, RefinementOverflowSession refinementOverflowSession, final View.OnClickListener onApplyClickListener) {
        FragmentActivity activity;
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(refinementOverflowSession, "refinementOverflowSession");
        if (shouldUseOverlayOverflow()) {
            Fragment fragment = getFragment();
            if (fragment == null || (activity = fragment.getActivity()) == null || isShowingRefinementActionSheetOverflow(activity)) {
                return;
            }
            this.currentSession = refinementOverflowSession;
            updateFilters(refinementOverflowSession.getTempCollection());
            RefinementOverflowDialog refinementOverflowDialog = this.refinementFilterActionSheetOverflow;
            if (refinementOverflowDialog != null) {
                refinementOverflowDialog.showSortButton(false, null);
            }
            RefinementOverflowDialog refinementOverflowDialog2 = this.refinementFilterActionSheetOverflow;
            if (refinementOverflowDialog2 != null) {
                refinementOverflowDialog2.showApplyButton(true, new View.OnClickListener() { // from class: com.amazon.mp3.view.refinements.BaseViewsRefinementOverflowManager$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        BaseViewsRefinementOverflowManager.m1731showRefinementOverflow$lambda1$lambda0(this.f$0, onApplyClickListener, view2);
                    }
                });
            }
            RefinementOverflowDialog refinementOverflowDialog3 = this.refinementFilterActionSheetOverflow;
            if (refinementOverflowDialog3 != null) {
                refinementOverflowDialog3.setApplyButtonEnabled(false);
            }
            RefinementOverflowDialog refinementOverflowDialog4 = this.refinementFilterActionSheetOverflow;
            if (refinementOverflowDialog4 != null) {
                refinementOverflowDialog4.show(activity.getSupportFragmentManager(), RefinementOverflowDialog.INSTANCE.getTAG());
            }
            RefinementOverflowDialog refinementOverflowDialog5 = this.refinementFilterActionSheetOverflow;
            if (refinementOverflowDialog5 == null) {
                return;
            }
            refinementOverflowDialog5.setCatalogModel(model);
            return;
        }
        RefinementOverflowPopupWindow refinementOverflowPopupWindow = this.refinementFilterViewOverflow;
        if (refinementOverflowPopupWindow == null) {
            return;
        }
        refinementOverflowPopupWindow.show(model.getFilters(), model.getTitle(), view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showRefinementOverflow$lambda-1$lambda-0, reason: not valid java name */
    public static final void m1731showRefinementOverflow$lambda1$lambda0(BaseViewsRefinementOverflowManager this$0, View.OnClickListener onClickListener, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        RefinementOverflowSession refinementOverflowSession = this$0.currentSession;
        if (refinementOverflowSession != null) {
            refinementOverflowSession.commit();
        }
        this$0.currentSession = null;
        this$0.refinementFilterActionSheetOverflow.dismiss();
        if (onClickListener == null) {
            return;
        }
        onClickListener.onClick(view);
    }

    public final void showRefinementOverflow(FilterCollectionModel model, View view) {
        FragmentActivity activity;
        Intrinsics.checkNotNullParameter(model, "model");
        if (shouldUseOverlayOverflow()) {
            Fragment fragment = getFragment();
            if (fragment == null || (activity = fragment.getActivity()) == null || isShowingRefinementActionSheetOverflow(activity)) {
                return;
            }
            updateFilters(model);
            RefinementOverflowDialog refinementOverflowDialog = this.refinementFilterActionSheetOverflow;
            if (refinementOverflowDialog != null) {
                refinementOverflowDialog.showSortButton(false, null);
            }
            RefinementOverflowDialog refinementOverflowDialog2 = this.refinementFilterActionSheetOverflow;
            if (refinementOverflowDialog2 != null) {
                RefinementOverflowDialog.showApplyButton$default(refinementOverflowDialog2, false, null, 2, null);
            }
            RefinementOverflowDialog refinementOverflowDialog3 = this.refinementFilterActionSheetOverflow;
            if (refinementOverflowDialog3 != null) {
                refinementOverflowDialog3.show(activity.getSupportFragmentManager(), RefinementOverflowDialog.INSTANCE.getTAG());
            }
            RefinementOverflowDialog refinementOverflowDialog4 = this.refinementFilterActionSheetOverflow;
            if (refinementOverflowDialog4 == null) {
                return;
            }
            refinementOverflowDialog4.setCatalogModel(model);
            return;
        }
        RefinementOverflowPopupWindow refinementOverflowPopupWindow = this.refinementFilterViewOverflow;
        if (refinementOverflowPopupWindow == null) {
            return;
        }
        refinementOverflowPopupWindow.show(model.getFilters(), model.getTitle(), view);
    }

    public final void dismissActionSheet() {
        RefinementOverflowDialog refinementOverflowDialog = this.refinementFilterActionSheetOverflow;
        if (refinementOverflowDialog == null) {
            return;
        }
        refinementOverflowDialog.dismiss();
    }

    private final boolean isShowingRefinementActionSheetOverflow(FragmentActivity activity) {
        activity.getSupportFragmentManager().executePendingTransactions();
        RefinementOverflowDialog refinementOverflowDialog = this.refinementFilterActionSheetOverflow;
        return refinementOverflowDialog != null && refinementOverflowDialog.isAdded();
    }

    private final void updateFilters(FilterCollectionModel model) {
        RefinementOverflowDialog refinementOverflowDialog;
        if (!shouldUseOverlayOverflow() || (refinementOverflowDialog = this.refinementFilterActionSheetOverflow) == null) {
            return;
        }
        refinementOverflowDialog.setFilters(model.getFilters(), model.getTitle());
    }

    public final void setApplyButtonEnabled(boolean isEnabled) {
        RefinementOverflowDialog refinementOverflowDialog;
        if (!shouldUseOverlayOverflow() || (refinementOverflowDialog = this.refinementFilterActionSheetOverflow) == null) {
            return;
        }
        refinementOverflowDialog.setApplyButtonEnabled(isEnabled);
    }

    @Override // com.amazon.music.views.library.providers.RefinementCallback
    public void onChildFilterClick(FilterModel filterModel) {
        RefinementOverflowSession refinementOverflowSession;
        FilterCollectionModel parentCollection;
        Intrinsics.checkNotNullParameter(filterModel, "filterModel");
        if (!filterModel.isCollection() && (refinementOverflowSession = this.currentSession) != null) {
            FilterItemModel itemModel = filterModel.getItemModel();
            refinementOverflowSession.updateFilterCollectionModel(itemModel);
            if (itemModel != null && (parentCollection = itemModel.getParentCollection()) != null) {
                updateFilters(parentCollection);
            }
            setApplyButtonEnabled(refinementOverflowSession.isDataChanged());
        }
        this.refinementCallback.onChildFilterClick(filterModel);
    }

    @Override // com.amazon.music.views.library.providers.RefinementCallback
    public void onFiltersReset() {
        resetSession();
        this.refinementCallback.onFiltersReset();
    }

    @Override // com.amazon.music.views.library.providers.RefinementCallback
    public void onFiltersReset(FilterCollectionModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
        resetSession();
        this.refinementCallback.onFiltersReset(model);
    }

    private final void resetSession() {
        RefinementOverflowSession refinementOverflowSession = this.currentSession;
        if (refinementOverflowSession == null) {
            return;
        }
        refinementOverflowSession.reset();
        updateFilters(refinementOverflowSession.getTempCollection());
        setApplyButtonEnabled(refinementOverflowSession.isDataChanged());
    }

    @Override // com.amazon.music.views.library.providers.RefinementClickListenerProvider
    public void onClick(RefinementPillModel model, View view) {
        Intrinsics.checkNotNullParameter(model, "model");
        this.refinementCallback.onClick(model, view);
    }
}
