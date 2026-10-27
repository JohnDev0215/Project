package com.amazon.mp3.actionbar.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.catalog.fragment.FilterSelectionDialogFragment;
import com.amazon.mp3.catalog.fragment.FilterableFragment;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.models.FilterCollectionModel;
import com.amazon.music.views.library.models.FilterItemModel;
import com.amazon.music.views.library.models.FilterModel;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.CornerSizeKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.views.BaseButton;
import com.google.android.material.button.MaterialButton;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FilterActionBarView.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B;\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\u0012\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u0018\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0002J\u0010\u0010&\u001a\u00020\u001e2\u0006\u0010'\u001a\u00020(H\u0002R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\"\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u0006)"}, d2 = {"Lcom/amazon/mp3/actionbar/view/FilterActionBarView;", "Lcom/amazon/mp3/actionbar/view/ActionBarView;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "title", "", "filters", "", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "filterableFragment", "Lcom/amazon/mp3/catalog/fragment/FilterableFragment;", "(Landroidx/fragment/app/FragmentActivity;Ljava/lang/String;Ljava/util/List;Lcom/amazon/mp3/catalog/fragment/FilterableFragment;)V", "filterBadge", "Landroid/widget/ImageView;", "filterDialogFragment", "Lcom/amazon/mp3/catalog/fragment/FilterSelectionDialogFragment;", "filterIngressObserver", "Landroidx/lifecycle/Observer;", "Lcom/amazon/music/views/library/models/FilterModel;", "filterView", "Lcom/amazon/ui/foundations/views/BaseButton;", "getFilterableFragment", "()Lcom/amazon/mp3/catalog/fragment/FilterableFragment;", "setFilterableFragment", "(Lcom/amazon/mp3/catalog/fragment/FilterableFragment;)V", "getFilters", "()Ljava/util/List;", "setFilters", "(Ljava/util/List;)V", "onViewRemoved", "", "view", "Landroid/view/View;", "sendUiClickMetric", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "setupRecyclerViewFilterBottomSheet", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FilterActionBarView extends ActionBarView {
    private ImageView filterBadge;
    private FilterSelectionDialogFragment filterDialogFragment;
    private Observer<FilterModel> filterIngressObserver;
    private BaseButton filterView;
    private FilterableFragment filterableFragment;
    private List<FilterCollectionModel> filters;

    public FilterActionBarView() {
        this(null, null, null, null, 15, null);
    }

    public /* synthetic */ FilterActionBarView(FragmentActivity fragmentActivity, String str, List list, FilterableFragment filterableFragment, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : fragmentActivity, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : list, (i & 8) != 0 ? null : filterableFragment);
    }

    public final List<FilterCollectionModel> getFilters() {
        return this.filters;
    }

    public final void setFilters(List<FilterCollectionModel> list) {
        this.filters = list;
    }

    public final FilterableFragment getFilterableFragment() {
        return this.filterableFragment;
    }

    public final void setFilterableFragment(FilterableFragment filterableFragment) {
        this.filterableFragment = filterableFragment;
    }

    public FilterActionBarView(FragmentActivity fragmentActivity, String str, List<FilterCollectionModel> list, FilterableFragment filterableFragment) {
        MutableLiveData<FilterItemModel> activeFilter;
        super(fragmentActivity, str);
        this.filters = list;
        this.filterableFragment = filterableFragment;
        BaseButton baseButton = (BaseButton) findViewById(R.id.filter_view);
        this.filterView = baseButton;
        MaterialButton button = baseButton == null ? null : baseButton.getButton();
        if (button != null) {
            button.setId(R.id.filter_view);
        }
        View viewFindViewById = findViewById(R.id.filter_badge);
        if (viewFindViewById == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.ImageView");
        }
        this.filterBadge = (ImageView) viewFindViewById;
        if (fragmentActivity != null) {
            StyleSheetProvider.getStyleSheet().observe(fragmentActivity, new Observer() { // from class: com.amazon.mp3.actionbar.view.FilterActionBarView$$ExternalSyntheticLambda1
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    FilterActionBarView.m137_init_$lambda0(this.f$0, (StyleSheet) obj);
                }
            });
        }
        this.filterIngressObserver = new Observer() { // from class: com.amazon.mp3.actionbar.view.FilterActionBarView$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FilterActionBarView.m138_init_$lambda1(this.f$0, (FilterModel) obj);
            }
        };
        FilterableFragment filterableFragment2 = this.filterableFragment;
        if (filterableFragment2 == null || (activeFilter = filterableFragment2.getActiveFilter()) == null) {
            return;
        }
        activeFilter.observeForever(this.filterIngressObserver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m137_init_$lambda0(FilterActionBarView this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this$0.setupRecyclerViewFilterBottomSheet(styleSheet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-1, reason: not valid java name */
    public static final void m138_init_$lambda1(FilterActionBarView this$0, FilterModel filterModel) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (filterModel != null) {
            ImageView imageView = this$0.filterBadge;
            if (imageView != null) {
                imageView.setVisibility(0);
            }
            BaseButton baseButton = this$0.filterView;
            if (baseButton == null) {
                return;
            }
            baseButton.setActive(true);
            return;
        }
        ImageView imageView2 = this$0.filterBadge;
        if (imageView2 != null) {
            imageView2.setVisibility(8);
        }
        BaseButton baseButton2 = this$0.filterView;
        if (baseButton2 == null) {
            return;
        }
        baseButton2.setActive(false);
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewRemoved(View view) {
        MutableLiveData<FilterItemModel> activeFilter;
        super.onViewRemoved(view);
        FilterableFragment filterableFragment = this.filterableFragment;
        if (filterableFragment == null || (activeFilter = filterableFragment.getActiveFilter()) == null) {
            return;
        }
        activeFilter.removeObserver(this.filterIngressObserver);
    }

    private final void setupRecyclerViewFilterBottomSheet(StyleSheet styleSheet) {
        ButtonSize buttonSizeCopy;
        BaseButton baseButton;
        MaterialButton button;
        FilterSelectionDialogFragment filterSelectionDialogFragmentNewInstance = FilterSelectionDialogFragment.INSTANCE.newInstance(this.filters);
        this.filterDialogFragment = filterSelectionDialogFragmentNewInstance;
        if (filterSelectionDialogFragmentNewInstance != null) {
            if (filterSelectionDialogFragmentNewInstance != null) {
                filterSelectionDialogFragmentNewInstance.setTargetFragment((Fragment) this.filterableFragment, 0);
            }
            BaseButton baseButton2 = this.filterView;
            if (baseButton2 != null) {
                baseButton2.setVisibility(0);
            }
            BaseButton baseButton3 = this.filterView;
            if (baseButton3 != null && (button = baseButton3.getButton()) != null) {
                button.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.actionbar.view.FilterActionBarView$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FilterActionBarView.m139setupRecyclerViewFilterBottomSheet$lambda2(this.f$0, view);
                    }
                });
            }
            ButtonSize iconSize = styleSheet.getIconSize(IconSizeKey.SMALL);
            if (iconSize == null) {
                buttonSizeCopy = null;
            } else {
                buttonSizeCopy = iconSize.copy((4026 & 1) != 0 ? iconSize.height : Integer.valueOf((int) getContext().getResources().getDimension(R.dimen.spacer_36)), (4026 & 2) != 0 ? iconSize.width : Integer.valueOf((int) getContext().getResources().getDimension(R.dimen.spacer_48)), (4026 & 4) != 0 ? iconSize.paddingHorizontal : 0, (4026 & 8) != 0 ? iconSize.paddingVertical : null, (4026 & 16) != 0 ? iconSize.minWidth : null, (4026 & 32) != 0 ? iconSize.cornerRadius : null, (4026 & 64) != 0 ? iconSize.textSize : null, (4026 & 128) != 0 ? iconSize.textTypeface : null, (4026 & 256) != 0 ? iconSize.textAllCaps : null, (4026 & 512) != 0 ? iconSize.iconSize : null, (4026 & 1024) != 0 ? iconSize.iconPaddingLeft : null, (4026 & 2048) != 0 ? iconSize.iconPaddingRight : null);
            }
            ButtonStyle iconStyle = styleSheet.getIconStyle(IconStyleKey.GLASS);
            Integer cornerSize = styleSheet.getCornerSize(CornerSizeKey.MEDIUM);
            if (buttonSizeCopy == null || iconStyle == null || cornerSize == null || (baseButton = this.filterView) == null) {
                return;
            }
            BaseButton.StyleBuilder styleBuilderWithStyle = new BaseButton.StyleBuilder().withSize(buttonSizeCopy).withStyle(iconStyle);
            Drawable drawable = getContext().getResources().getDrawable(R.drawable.ic_search_filter_btn);
            Intrinsics.checkNotNullExpressionValue(drawable, "context.resources.getDra…ble.ic_search_filter_btn)");
            BaseButton.StyleBuilder styleBuilderWithIcon = styleBuilderWithStyle.withIcon(drawable);
            Drawable drawable2 = getContext().getResources().getDrawable(R.drawable.ic_other_filter1cancel);
            Intrinsics.checkNotNullExpressionValue(drawable2, "context.resources.getDra…e.ic_other_filter1cancel)");
            styleBuilderWithIcon.withActiveIcon(drawable2).withCornerRadius(cornerSize.intValue()).applyStyle(baseButton);
            return;
        }
        BaseButton baseButton4 = this.filterView;
        if (baseButton4 == null) {
            return;
        }
        baseButton4.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupRecyclerViewFilterBottomSheet$lambda-2, reason: not valid java name */
    public static final void m139setupRecyclerViewFilterBottomSheet$lambda2(FilterActionBarView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.filterDialogFragment == null) {
            return;
        }
        if (this$0.filters != null) {
            Context context = this$0.getContext();
            if (context == null) {
                throw new NullPointerException("null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
            }
            FragmentActivity fragmentActivity = (FragmentActivity) context;
            FilterSelectionDialogFragment filterSelectionDialogFragment = this$0.filterDialogFragment;
            if (filterSelectionDialogFragment != null) {
                filterSelectionDialogFragment.show(fragmentActivity.getSupportFragmentManager(), FilterSelectionDialogFragment.INSTANCE.getTAG());
            }
        }
        this$0.sendUiClickMetric(ActionType.OPEN_FILTER_MENU, PageType.BROWSE_PLAYLISTS);
    }

    private final void sendUiClickMetric(ActionType actionType, PageType pageType) {
        UiClickEvent uiClickEventBuild = UiClickEvent.builder(actionType).withInteractionType(InteractionType.TAP).withPageType(pageType).withEventTime(System.currentTimeMillis()).build();
        if (uiClickEventBuild != null) {
            MetricsHolder.getManager().handleEvent(uiClickEventBuild);
        }
    }
}
