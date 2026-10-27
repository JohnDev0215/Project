package com.amazon.music.find.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.FragmentActivity;
import com.amazon.music.find.R;
import com.amazon.music.find.model.event.SearchViewQueryTextEvent;
import com.amazon.music.find.utils.KeyboardUtils;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.CornerSizeKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.styles.StatefulStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.views.BaseButton;
import com.google.android.gms.actions.SearchIntents;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: FindSearchBarView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 s2\u00020\u0001:\u0001sB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010<\u001a\u00020=J\u0010\u0010>\u001a\u00020=2\b\b\u0002\u0010/\u001a\u000200J\u0006\u0010?\u001a\u00020=J\u0006\u0010@\u001a\u00020=J\f\u0010A\u001a\b\u0012\u0004\u0012\u00020C0BJ\u0006\u0010D\u001a\u00020=J\u0006\u0010E\u001a\u00020=J\u0006\u0010F\u001a\u00020=J\u0010\u0010G\u001a\u00020=2\b\u0010H\u001a\u0004\u0018\u00010IJ\u0010\u0010J\u001a\u00020=2\b\u0010K\u001a\u0004\u0018\u00010IJ\u0010\u0010L\u001a\u00020=2\b\u0010H\u001a\u0004\u0018\u00010IJ\u0010\u0010M\u001a\u00020=2\b\u0010H\u001a\u0004\u0018\u00010IJ\u000e\u0010N\u001a\u00020=2\u0006\u0010O\u001a\u00020PJ\u0010\u0010Q\u001a\u00020=2\u0006\u0010R\u001a\u00020!H\u0002J\u0018\u0010S\u001a\u00020=2\u0006\u0010T\u001a\u00020P2\b\b\u0002\u0010/\u001a\u000200J\u0010\u0010U\u001a\u00020=2\b\u0010V\u001a\u0004\u0018\u00010WJ\u0010\u0010X\u001a\u00020=2\b\u0010H\u001a\u0004\u0018\u00010IJ\u0010\u0010Y\u001a\u00020=2\b\u0010Z\u001a\u0004\u0018\u00010[J\u000e\u0010\\\u001a\u00020=2\u0006\u0010]\u001a\u00020^J\u000e\u0010_\u001a\u00020=2\u0006\u0010`\u001a\u000200J\u000e\u0010a\u001a\u00020=2\u0006\u0010b\u001a\u000200J\u000e\u0010c\u001a\u00020=2\u0006\u0010b\u001a\u000200J\u000e\u0010d\u001a\u00020=2\u0006\u0010b\u001a\u000200J\"\u0010e\u001a\u00020=2\u0006\u0010b\u001a\u0002002\b\b\u0002\u0010f\u001a\u0002002\b\b\u0002\u0010g\u001a\u000200J\u0010\u0010h\u001a\u00020=2\b\b\u0002\u0010i\u001a\u000200J\u0006\u0010j\u001a\u00020=J\u000e\u0010k\u001a\u00020=2\u0006\u0010l\u001a\u00020PJ0\u0010m\u001a\u00020=2\u0006\u0010n\u001a\u00020o2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u000e\u0010p\u001a\u00020=2\u0006\u0010n\u001a\u00020oJ\u0010\u0010q\u001a\u00020=2\u0006\u0010*\u001a\u00020+H\u0002J\u0018\u0010r\u001a\u00020=2\u0006\u0010n\u001a\u00020o2\u0006\u00104\u001a\u000205H\u0002R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\f\u0010\bR\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\n\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\n\u001a\u0004\b\u0015\u0010\u0016R\u001b\u0010\u0018\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\n\u001a\u0004\b\u0019\u0010\u0011R\u001b\u0010\u001b\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\n\u001a\u0004\b\u001c\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u001b\u0010 \u001a\u00020!8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b$\u0010\n\u001a\u0004\b\"\u0010#R\u001b\u0010%\u001a\u00020&8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b)\u0010\n\u001a\u0004\b'\u0010(R\u001b\u0010*\u001a\u00020+8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b.\u0010\n\u001a\u0004\b,\u0010-R\u000e\u0010/\u001a\u000200X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u00101\u001a\u0004\u0018\u000102X\u0082\u000e¢\u0006\u0004\n\u0002\u00103R\u001b\u00104\u001a\u0002058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b8\u0010\n\u001a\u0004\b6\u00107R\u001b\u00109\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b;\u0010\n\u001a\u0004\b:\u0010\b¨\u0006t"}, d2 = {"Lcom/amazon/music/find/view/FindSearchBarView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "fragmentActivity", "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;)V", "alexaButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "getAlexaButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "alexaButton$delegate", "Lkotlin/Lazy;", "backButton", "getBackButton", "backButton$delegate", "clearImageView", "Landroid/widget/ImageView;", "getClearImageView", "()Landroid/widget/ImageView;", "clearImageView$delegate", "emptyElement", "Landroid/widget/LinearLayout;", "getEmptyElement", "()Landroid/widget/LinearLayout;", "emptyElement$delegate", "filterActiveCircleIcon", "getFilterActiveCircleIcon", "filterActiveCircleIcon$delegate", "filterButton", "getFilterButton", "filterButton$delegate", "getFragmentActivity", "()Landroidx/fragment/app/FragmentActivity;", "searchButton", "Landroid/view/View;", "getSearchButton", "()Landroid/view/View;", "searchButton$delegate", "searchEditView", "Landroid/widget/AutoCompleteTextView;", "getSearchEditView", "()Landroid/widget/AutoCompleteTextView;", "searchEditView$delegate", "searchView", "Landroid/widget/SearchView;", "getSearchView", "()Landroid/widget/SearchView;", "searchView$delegate", "shouldTriggerTextChangeListener", "", "smallSpacer", "", "Ljava/lang/Integer;", "titleView", "Landroid/widget/TextView;", "getTitleView", "()Landroid/widget/TextView;", "titleView$delegate", "titleViewRightConstraint", "getTitleViewRightConstraint", "titleViewRightConstraint$delegate", "clearAllListeners", "", "clearSearchQuery", "clearSearchViewFocus", "disableSearchViewKeyboard", "getSearchViewQueryTextObservable", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/amazon/music/find/model/event/SearchViewQueryTextEvent;", "hideKeyboard", "performSearchButtonClick", "requestSearchViewFocus", "setAlexaButtonListener", "onClickListener", "Landroid/view/View$OnClickListener;", "setBackButtonListener", "onBackClick", "setClearImageViewListener", "setFilterButtonListener", "setQueryHint", "queryHint", "", "setRightButtonConstraintToSearchView", "view", "setSearchQuery", SearchIntents.EXTRA_QUERY, "setSearchViewFocusChangeListener", "onFocusChangeListener", "Landroid/view/View$OnFocusChangeListener;", "setSearchViewOnClickListener", "setSearchViewOnKeyListener", "onKeyListener", "Landroid/view/View$OnKeyListener;", "setSearchViewOnQueryTextListener", "onQueryTextListener", "Landroid/widget/SearchView$OnQueryTextListener;", "setShowSoftInputOnFocus", "value", "showAlexaButton", "shouldDisplay", "showBackButton", "showClearButton", "showFilterButton", "isFilterActive", "areRefinementsExposed", "showKeyboard", "force", "showSearchBox", "showTitle", "title", "styleButtons", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleFindSearchBarView", "styleSearchBoxView", "styleTitle", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FindSearchBarView extends ConstraintLayout {
    public static final long INVALIDATE_INTERVAL_MS = 200;
    private static final String TAG = "SPL";
    public static final String URI_PREFIX = "uri://";

    /* JADX INFO: renamed from: alexaButton$delegate, reason: from kotlin metadata */
    private final Lazy alexaButton;

    /* JADX INFO: renamed from: backButton$delegate, reason: from kotlin metadata */
    private final Lazy backButton;

    /* JADX INFO: renamed from: clearImageView$delegate, reason: from kotlin metadata */
    private final Lazy clearImageView;

    /* JADX INFO: renamed from: emptyElement$delegate, reason: from kotlin metadata */
    private final Lazy emptyElement;

    /* JADX INFO: renamed from: filterActiveCircleIcon$delegate, reason: from kotlin metadata */
    private final Lazy filterActiveCircleIcon;

    /* JADX INFO: renamed from: filterButton$delegate, reason: from kotlin metadata */
    private final Lazy filterButton;
    private final FragmentActivity fragmentActivity;

    /* JADX INFO: renamed from: searchButton$delegate, reason: from kotlin metadata */
    private final Lazy searchButton;

    /* JADX INFO: renamed from: searchEditView$delegate, reason: from kotlin metadata */
    private final Lazy searchEditView;

    /* JADX INFO: renamed from: searchView$delegate, reason: from kotlin metadata */
    private final Lazy searchView;
    private boolean shouldTriggerTextChangeListener;
    private Integer smallSpacer;

    /* JADX INFO: renamed from: titleView$delegate, reason: from kotlin metadata */
    private final Lazy titleView;

    /* JADX INFO: renamed from: titleViewRightConstraint$delegate, reason: from kotlin metadata */
    private final Lazy titleViewRightConstraint;
    private static final Logger logger = LoggerFactory.getLogger(FindSearchBarView.class.getName());

    public final FragmentActivity getFragmentActivity() {
        return this.fragmentActivity;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FindSearchBarView(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        Intrinsics.checkNotNullParameter(fragmentActivity, "fragmentActivity");
        this.fragmentActivity = fragmentActivity;
        this.titleView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.find.view.FindSearchBarView$titleView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R.id.title_view);
            }
        });
        this.searchView = LazyKt__LazyJVMKt.lazy(new Function0<SearchView>() { // from class: com.amazon.music.find.view.FindSearchBarView$searchView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final SearchView invoke() {
                return (SearchView) this.this$0.findViewById(R.id.search_view);
            }
        });
        this.backButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.find.view.FindSearchBarView$backButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.back_button);
            }
        });
        this.filterButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.find.view.FindSearchBarView$filterButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.filter_button);
            }
        });
        this.filterActiveCircleIcon = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.music.find.view.FindSearchBarView$filterActiveCircleIcon$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageView invoke() {
                return (ImageView) this.this$0.findViewById(R.id.filter_active_circle_icon);
            }
        });
        this.titleViewRightConstraint = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.find.view.FindSearchBarView$titleViewRightConstraint$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.title_view_right_constraint);
            }
        });
        this.alexaButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.find.view.FindSearchBarView$alexaButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.search_alexa_button);
            }
        });
        this.clearImageView = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.music.find.view.FindSearchBarView$clearImageView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageView invoke() {
                return (ImageView) this.this$0.getSearchView().findViewById(this.this$0.getSearchView().getContext().getResources().getIdentifier("android:id/search_close_btn", null, null));
            }
        });
        this.searchEditView = LazyKt__LazyJVMKt.lazy(new Function0<AutoCompleteTextView>() { // from class: com.amazon.music.find.view.FindSearchBarView$searchEditView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final AutoCompleteTextView invoke() {
                return (AutoCompleteTextView) this.this$0.getSearchView().findViewById(this.this$0.getSearchView().getContext().getResources().getIdentifier("android:id/search_src_text", null, null));
            }
        });
        this.searchButton = LazyKt__LazyJVMKt.lazy(new Function0<View>() { // from class: com.amazon.music.find.view.FindSearchBarView$searchButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final View invoke() {
                return this.this$0.getSearchView().findViewById(this.this$0.getSearchView().getContext().getResources().getIdentifier("android:id/search_button", null, null));
            }
        });
        this.emptyElement = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.find.view.FindSearchBarView$emptyElement$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R.id.empty_element);
            }
        });
        this.shouldTriggerTextChangeListener = true;
        LayoutInflater.from(getContext()).cloneInContext(new ContextThemeWrapper(getContext(), R.style.HeaderSearch)).inflate(R.layout.find_search_bar_view, (ViewGroup) this, true);
        setLayoutTransition(null);
    }

    private final TextView getTitleView() {
        Object value = this.titleView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-titleView>(...)");
        return (TextView) value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SearchView getSearchView() {
        Object value = this.searchView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-searchView>(...)");
        return (SearchView) value;
    }

    private final BaseButton getBackButton() {
        Object value = this.backButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-backButton>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getFilterButton() {
        Object value = this.filterButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-filterButton>(...)");
        return (BaseButton) value;
    }

    private final ImageView getFilterActiveCircleIcon() {
        Object value = this.filterActiveCircleIcon.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-filterActiveCircleIcon>(...)");
        return (ImageView) value;
    }

    private final BaseButton getTitleViewRightConstraint() {
        Object value = this.titleViewRightConstraint.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-titleViewRightConstraint>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getAlexaButton() {
        Object value = this.alexaButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-alexaButton>(...)");
        return (BaseButton) value;
    }

    private final ImageView getClearImageView() {
        Object value = this.clearImageView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-clearImageView>(...)");
        return (ImageView) value;
    }

    private final AutoCompleteTextView getSearchEditView() {
        Object value = this.searchEditView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-searchEditView>(...)");
        return (AutoCompleteTextView) value;
    }

    private final View getSearchButton() {
        Object value = this.searchButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-searchButton>(...)");
        return (View) value;
    }

    private final LinearLayout getEmptyElement() {
        Object value = this.emptyElement.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-emptyElement>(...)");
        return (LinearLayout) value;
    }

    public final void styleFindSearchBarView(StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.smallSpacer = styleSheet.getSpacerInPixels(SpacerKey.SMALL);
        styleTitle(styleSheet, getTitleView());
        styleSearchBoxView(getSearchView());
        styleButtons(styleSheet, getBackButton(), getFilterButton(), getTitleViewRightConstraint(), getAlexaButton());
    }

    public final void setShowSoftInputOnFocus(boolean value) {
        getSearchEditView().setShowSoftInputOnFocus(value);
    }

    public final void setQueryHint(String queryHint) {
        Intrinsics.checkNotNullParameter(queryHint, "queryHint");
        getSearchView().setQueryHint(queryHint);
    }

    public static /* synthetic */ void showKeyboard$default(FindSearchBarView findSearchBarView, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        findSearchBarView.showKeyboard(z);
    }

    public final void showKeyboard(boolean force) {
        KeyboardUtils keyboardUtils = KeyboardUtils.INSTANCE;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        KeyboardUtils.showKeyboard$default(keyboardUtils, context, getSearchView(), force, 0, 0, 0, 56, null);
    }

    public final void hideKeyboard() {
        KeyboardUtils keyboardUtils = KeyboardUtils.INSTANCE;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        KeyboardUtils.hideKeyboard$default(keyboardUtils, context, getSearchView(), 0, 4, null);
    }

    public final void performSearchButtonClick() {
        getSearchButton().performClick();
    }

    public final void clearSearchViewFocus() {
        getEmptyElement().requestFocus();
    }

    public final void requestSearchViewFocus() {
        getSearchView().requestFocus();
    }

    public final void disableSearchViewKeyboard() {
        getSearchEditView().setRawInputType(0);
    }

    public static /* synthetic */ void clearSearchQuery$default(FindSearchBarView findSearchBarView, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        findSearchBarView.clearSearchQuery(z);
    }

    public final void clearSearchQuery(boolean shouldTriggerTextChangeListener) {
        setSearchQuery("", shouldTriggerTextChangeListener);
    }

    public static /* synthetic */ void setSearchQuery$default(FindSearchBarView findSearchBarView, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        findSearchBarView.setSearchQuery(str, z);
    }

    public final void setSearchQuery(String query, boolean shouldTriggerTextChangeListener) {
        Intrinsics.checkNotNullParameter(query, "query");
        this.shouldTriggerTextChangeListener = shouldTriggerTextChangeListener;
        getSearchView().setQuery(query, false);
    }

    public final void clearAllListeners() {
        getBackButton().setOnClickListener(null);
        getFilterButton().setOnClickListener(null);
        getSearchView().setOnKeyListener(null);
        getSearchView().setOnQueryTextListener(null);
        getSearchView().setOnQueryTextFocusChangeListener(null);
        getSearchView().setOnClickListener(null);
        getSearchEditView().setOnClickListener(null);
        getClearImageView().setOnClickListener(null);
        getAlexaButton().setOnClickListener(null);
    }

    public final void showTitle(String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        getSearchView().setVisibility(4);
        getTitleView().setVisibility(0);
        getTitleView().setText(title);
        getTitleViewRightConstraint().setVisibility(4);
    }

    public final void showSearchBox() {
        getTitleView().setVisibility(8);
        getSearchView().setVisibility(0);
    }

    public static /* synthetic */ void showFilterButton$default(FindSearchBarView findSearchBarView, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 2) != 0) {
            z2 = false;
        }
        if ((i & 4) != 0) {
            z3 = false;
        }
        findSearchBarView.showFilterButton(z, z2, z3);
    }

    public final void showFilterButton(boolean shouldDisplay, boolean isFilterActive, boolean areRefinementsExposed) {
        if (shouldDisplay) {
            setRightButtonConstraintToSearchView(getFilterButton());
            getFilterButton().setActive(areRefinementsExposed);
            getFilterButton().setVisibility(0);
            getFilterActiveCircleIcon().setVisibility(isFilterActive ? 0 : 4);
            return;
        }
        getFilterActiveCircleIcon().setVisibility(8);
        getFilterButton().setVisibility(8);
    }

    public final void showClearButton(boolean shouldDisplay) {
        if (shouldDisplay) {
            getClearImageView().setImageAlpha(255);
            getClearImageView().setEnabled(true);
        } else {
            getClearImageView().setEnabled(false);
            getClearImageView().setImageAlpha(0);
        }
    }

    public final void showBackButton(boolean shouldDisplay) {
        getBackButton().setVisibility(shouldDisplay ? 0 : 8);
    }

    public final void showAlexaButton(boolean shouldDisplay) {
        getAlexaButton().setVisibility(shouldDisplay ? 0 : 8);
    }

    public final void setBackButtonListener(View.OnClickListener onBackClick) {
        getBackButton().setOnClickListener(onBackClick);
    }

    public final void setFilterButtonListener(View.OnClickListener onClickListener) {
        getFilterButton().setOnClickListener(onClickListener);
    }

    public final void setClearImageViewListener(View.OnClickListener onClickListener) {
        getClearImageView().setOnClickListener(onClickListener);
    }

    public final void setAlexaButtonListener(View.OnClickListener onClickListener) {
        getAlexaButton().setOnClickListener(onClickListener);
    }

    public final void setSearchViewFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        getSearchView().setOnQueryTextFocusChangeListener(onFocusChangeListener);
    }

    public final void setSearchViewOnKeyListener(View.OnKeyListener onKeyListener) {
        getSearchView().setOnKeyListener(onKeyListener);
    }

    public final void setSearchViewOnClickListener(View.OnClickListener onClickListener) {
        getSearchView().setOnClickListener(onClickListener);
    }

    public final void setSearchViewOnQueryTextListener(SearchView.OnQueryTextListener onQueryTextListener) {
        Intrinsics.checkNotNullParameter(onQueryTextListener, "onQueryTextListener");
        getSearchView().setOnQueryTextListener(onQueryTextListener);
    }

    public final Observable<SearchViewQueryTextEvent> getSearchViewQueryTextObservable() {
        Observable<SearchViewQueryTextEvent> observableCreate = Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.music.find.view.FindSearchBarView$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                FindSearchBarView.m2064getSearchViewQueryTextObservable$lambda0(this.f$0, observableEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "create { emitter ->\n    …\n            })\n        }");
        return observableCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getSearchViewQueryTextObservable$lambda-0, reason: not valid java name */
    public static final void m2064getSearchViewQueryTextObservable$lambda0(final FindSearchBarView this$0, final ObservableEmitter observableEmitter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getSearchView().setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: com.amazon.music.find.view.FindSearchBarView$getSearchViewQueryTextObservable$1$1
            @Override // android.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextChange(String query) {
                Intrinsics.checkNotNullParameter(query, "query");
                FindSearchBarView.logger.debug("SPL::getSearchViewQueryTextObservable->onQueryTextChange. query: " + query + ", triggerListener: " + this.this$0.shouldTriggerTextChangeListener);
                if (this.this$0.shouldTriggerTextChangeListener) {
                    observableEmitter.onNext(new SearchViewQueryTextEvent(query, false));
                }
                if (query.length() > 0) {
                    this.this$0.showAlexaButton(false);
                }
                this.this$0.shouldTriggerTextChangeListener = true;
                return true;
            }

            @Override // android.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextSubmit(String query) {
                Intrinsics.checkNotNullParameter(query, "query");
                this.this$0.showClearButton(false);
                this.this$0.showAlexaButton(false);
                observableEmitter.onNext(new SearchViewQueryTextEvent(query, true));
                return true;
            }
        });
    }

    private final void styleTitle(StyleSheet styleSheet, TextView titleView) {
        FontStyle fontStyle = styleSheet.getFontStyle(FontStyleKey.HEADLINE_4);
        if (fontStyle == null) {
            return;
        }
        FontUtil.INSTANCE.applyFontStyle(titleView, fontStyle);
    }

    private final void styleSearchBoxView(SearchView searchView) {
        View viewFindViewById = searchView.findViewById(searchView.getContext().getResources().getIdentifier("android:id/search_edit_frame", null, null));
        if (viewFindViewById != null) {
            viewFindViewById.setBackgroundResource(R.drawable.search_bar_view);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
            layoutParams.setMarginStart(0);
            viewFindViewById.setLayoutParams(layoutParams);
        }
        View viewFindViewById2 = searchView.findViewById(searchView.getContext().getResources().getIdentifier("android:id/search_plate", null, null));
        if (viewFindViewById2 != null) {
            viewFindViewById2.setBackgroundResource(R.drawable.search_bar_view);
        }
        ((ImageView) searchView.findViewById(getResources().getIdentifier("android:id/search_close_btn", null, null))).setVisibility(8);
    }

    private final void setRightButtonConstraintToSearchView(View view) {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this);
        constraintSet.connect(getSearchView().getId(), 7, view.getId(), 6, 0);
        constraintSet.applyTo(this);
    }

    private final void styleButtons(StyleSheet styleSheet, BaseButton backButton, BaseButton filterButton, BaseButton titleViewRightConstraint, BaseButton alexaButton) {
        BaseButton.StyleBuilder styleBuilderWithStyle;
        IconSizeKey iconSizeKey = IconSizeKey.MEDIUM;
        IconStyleKey iconStyleKey = IconStyleKey.PRIMARY;
        BaseButton.StyleBuilder iconBuilder = styleSheet.getIconBuilder(iconSizeKey, iconStyleKey);
        ButtonSize buttonSizeCopy = null;
        if (iconBuilder != null) {
            Drawable drawable = getResources().getDrawable(R.drawable.ic_navigation_goback, null);
            Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.…_navigation_goback, null)");
            BaseButton.StyleBuilder styleBuilderWithIcon = iconBuilder.withIcon(drawable);
            if (styleBuilderWithIcon != null) {
                styleBuilderWithIcon.applyStyle(backButton);
            }
        }
        BaseButton.StyleBuilder iconBuilder2 = styleSheet.getIconBuilder(IconSizeKey.TINY, iconStyleKey);
        if (iconBuilder2 != null) {
            Drawable drawable2 = getResources().getDrawable(R.drawable.ic_alexa_bauhaus, null);
            Intrinsics.checkNotNullExpressionValue(drawable2, "resources.getDrawable(R.…e.ic_alexa_bauhaus, null)");
            BaseButton.StyleBuilder styleBuilderWithIcon2 = iconBuilder2.withIcon(drawable2);
            if (styleBuilderWithIcon2 != null && (styleBuilderWithStyle = styleBuilderWithIcon2.withStyle(new ButtonStyle(new StatefulStyle(Integer.valueOf(getResources().getColor(R.color.white)), Integer.valueOf(getResources().getColor(R.color.blue_accent)), Integer.valueOf(getResources().getColor(R.color.blue_accent_30)), null, Integer.valueOf(getResources().getColor(R.color.black)), null, null, null, 232, null), null, null, null, 14, null))) != null) {
                styleBuilderWithStyle.applyStyle(alexaButton);
            }
        }
        BaseButton.StyleBuilder iconBuilder3 = styleSheet.getIconBuilder(iconSizeKey, iconStyleKey);
        if (iconBuilder3 != null) {
            Drawable drawable3 = getResources().getDrawable(R.drawable.ic_navigation_goback, null);
            Intrinsics.checkNotNullExpressionValue(drawable3, "resources.getDrawable(R.…_navigation_goback, null)");
            BaseButton.StyleBuilder styleBuilderWithIcon3 = iconBuilder3.withIcon(drawable3);
            if (styleBuilderWithIcon3 != null) {
                styleBuilderWithIcon3.applyStyle(titleViewRightConstraint);
            }
        }
        ButtonSize iconSize = styleSheet.getIconSize(IconSizeKey.SMALL);
        if (iconSize != null) {
            buttonSizeCopy = iconSize.copy((4026 & 1) != 0 ? iconSize.height : Integer.valueOf((int) getContext().getResources().getDimension(R.dimen.spacer_36)), (4026 & 2) != 0 ? iconSize.width : Integer.valueOf((int) getContext().getResources().getDimension(R.dimen.spacer_48)), (4026 & 4) != 0 ? iconSize.paddingHorizontal : 0, (4026 & 8) != 0 ? iconSize.paddingVertical : null, (4026 & 16) != 0 ? iconSize.minWidth : null, (4026 & 32) != 0 ? iconSize.cornerRadius : null, (4026 & 64) != 0 ? iconSize.textSize : null, (4026 & 128) != 0 ? iconSize.textTypeface : null, (4026 & 256) != 0 ? iconSize.textAllCaps : null, (4026 & 512) != 0 ? iconSize.iconSize : null, (4026 & 1024) != 0 ? iconSize.iconPaddingLeft : null, (4026 & 2048) != 0 ? iconSize.iconPaddingRight : null);
        }
        ButtonStyle iconStyle = styleSheet.getIconStyle(IconStyleKey.GLASS);
        Integer cornerSize = styleSheet.getCornerSize(CornerSizeKey.MEDIUM);
        if (buttonSizeCopy == null || iconStyle == null || cornerSize == null) {
            return;
        }
        BaseButton.StyleBuilder styleBuilderWithStyle2 = new BaseButton.StyleBuilder().withSize(buttonSizeCopy).withStyle(iconStyle);
        Drawable drawable4 = getContext().getResources().getDrawable(R.drawable.ic_other_filter1);
        Intrinsics.checkNotNullExpressionValue(drawable4, "context.resources.getDra…rawable.ic_other_filter1)");
        BaseButton.StyleBuilder styleBuilderWithIcon4 = styleBuilderWithStyle2.withIcon(drawable4);
        Drawable drawable5 = getContext().getResources().getDrawable(R.drawable.ic_other_filter1cancel);
        Intrinsics.checkNotNullExpressionValue(drawable5, "context.resources.getDra…e.ic_other_filter1cancel)");
        styleBuilderWithIcon4.withActiveIcon(drawable5).withCornerRadius(cornerSize.intValue()).applyStyle(filterButton);
    }
}
