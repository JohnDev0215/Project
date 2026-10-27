package com.amazon.mp3.actionbar;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager;
import com.amazon.mp3.actionbar.view.BauhausActionBarView;
import com.amazon.mp3.actionbar.view.BauhausFilterActionBarView;
import com.amazon.mp3.providers.MetadataContextMenuHandler;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.ExposedRefinementListModel;
import com.amazon.music.views.library.models.FilterModel;
import com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider;
import com.amazon.music.views.library.views.presentation.ActionBarOverlayable;
import java.lang.ref.WeakReference;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseViewsFilterActionBarManager.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b*\u0002\u0017\u001a\u0018\u0000 A2\u00020\u0001:\u0002ABB+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u0006\u0010\u001c\u001a\u00020\u001dJ\b\u0010\u001e\u001a\u0004\u0018\u00010\u0013J\u0006\u0010\u001f\u001a\u00020\u001dJ\u0006\u0010 \u001a\u00020\u001dJ\u0010\u0010!\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020#H\u0016J\u0010\u0010$\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020#H\u0016J\u0010\u0010%\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020#H\u0016J\u0006\u0010&\u001a\u00020\u001dJ\u0016\u0010'\u001a\u00020\u001d2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+J\u001a\u0010,\u001a\u00020\u001d2\u0006\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u000100H\u0002J,\u00101\u001a\u00020\u001d2\u0006\u00102\u001a\u0002032\u000e\u00104\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u0003052\f\u00106\u001a\b\u0012\u0004\u0012\u00020807J\u0010\u00109\u001a\u00020\u001d2\b\b\u0002\u0010:\u001a\u00020.J\u0006\u0010;\u001a\u00020\u001dJ\u0006\u0010<\u001a\u00020\u001dJ\u0006\u0010=\u001a\u00020\u001dJ\b\u0010>\u001a\u00020\u001dH\u0002J\b\u0010?\u001a\u00020\u001dH\u0002J\b\u0010@\u001a\u00020\u001dH\u0002R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001b¨\u0006C"}, d2 = {"Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "fragment", "Landroidx/fragment/app/Fragment;", "infoProvider", "Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager$InfoProvider;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "actionBarProvider", "Lcom/amazon/mp3/actionbar/ActionBarProvider;", "(Landroidx/fragment/app/Fragment;Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager$InfoProvider;Landroidx/recyclerview/widget/RecyclerView;Lcom/amazon/mp3/actionbar/ActionBarProvider;)V", "_fragment", "Ljava/lang/ref/WeakReference;", "<set-?>", "", "actionBarHeight", "getActionBarHeight", "()I", "actionBarView", "Lcom/amazon/mp3/actionbar/view/BauhausFilterActionBarView;", "getFragment", "()Landroidx/fragment/app/Fragment;", "onScrollListener", "com/amazon/mp3/actionbar/BaseViewsFilterActionBarManager$onScrollListener$1", "Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager$onScrollListener$1;", "smoothScroller", "com/amazon/mp3/actionbar/BaseViewsFilterActionBarManager$smoothScroller$1", "Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager$smoothScroller$1;", "forceShowTitle", "", "getActionBarView", "hideContextMenuOverflowButton", "hideFilterAndContextButtons", "onDestroy", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER, "Landroidx/lifecycle/LifecycleOwner;", "onStart", "onStop", "refreshActionBarFilters", "setActionBarContextMenuProvider", "contentMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "contextMenuProvider", "Lcom/amazon/mp3/providers/MetadataContextMenuHandler;", "setActionBarOverlayStyle", "isOverlay", "", "actionBarOverlayable", "Lcom/amazon/music/views/library/views/presentation/ActionBarOverlayable;", "setAdapterProviderForActionBarView", "adapterProvider", "Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;", "refinementBinder", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "filterModels", "", "Lcom/amazon/music/views/library/models/FilterModel;", "shouldShowExposedRefinementList", "forceShow", "shouldShowFilterButton", "shouldShowPageComponentInTopAppBar", "showContextMenuOverflowButton", "showOrHideArtwork", "showOrHideTitle", "smoothScrollOnFilterButtonClick", "Companion", "InfoProvider", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BaseViewsFilterActionBarManager implements DefaultLifecycleObserver {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = BaseViewsFilterActionBarManager.class.getSimpleName();
    private final WeakReference<Fragment> _fragment;
    private int actionBarHeight;
    private BauhausFilterActionBarView actionBarView;
    private InfoProvider infoProvider;
    private final BaseViewsFilterActionBarManager$onScrollListener$1 onScrollListener;
    private RecyclerView recyclerView;
    private BaseViewsFilterActionBarManager$smoothScroller$1 smoothScroller;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v3, types: [androidx.recyclerview.widget.RecyclerView$OnScrollListener, com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager$onScrollListener$1] */
    /* JADX WARN: Type inference failed for: r11v1, types: [com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager$smoothScroller$1] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public BaseViewsFilterActionBarManager(Fragment fragment, InfoProvider infoProvider, RecyclerView recyclerView, ActionBarProvider actionBarProvider) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        this.infoProvider = infoProvider;
        this.recyclerView = recyclerView;
        this._fragment = new WeakReference<>(fragment);
        final Context contextRequireContext = fragment.requireContext();
        this.smoothScroller = new LinearSmoothScroller(contextRequireContext) { // from class: com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager$smoothScroller$1
            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            protected int getVerticalSnapPreference() {
                return 0;
            }

            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            public int calculateDtToFit(int viewStart, int viewEnd, int boxStart, int boxEnd, int snapPreference) {
                return ((boxStart - viewStart) + this.this$0.getActionBarHeight()) - 1;
            }
        };
        ?? r10 = new RecyclerView.OnScrollListener() { // from class: com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager$onScrollListener$1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView2, int dx, int dy) {
                Intrinsics.checkNotNullParameter(recyclerView2, "recyclerView");
                super.onScrolled(recyclerView2, dx, dy);
                if (recyclerView2.getVisibility() != 0) {
                    return;
                }
                BaseViewsFilterActionBarManager.InfoProvider infoProvider2 = this.this$0.infoProvider;
                boolean z = false;
                if (infoProvider2 != null && infoProvider2.doesHaveRefinement()) {
                    BaseViewsFilterActionBarManager.shouldShowExposedRefinementList$default(this.this$0, false, 1, null);
                }
                this.this$0.shouldShowPageComponentInTopAppBar();
                BaseViewsFilterActionBarManager.InfoProvider infoProvider3 = this.this$0.infoProvider;
                if (infoProvider3 != null && infoProvider3.shouldDisplayFilterAndContextButtons()) {
                    this.this$0.shouldShowFilterButton();
                } else {
                    this.this$0.hideFilterAndContextButtons();
                }
                BaseViewsFilterActionBarManager.InfoProvider infoProvider4 = this.this$0.infoProvider;
                if (infoProvider4 != null && infoProvider4.shouldDisplayContextMenuOverflowButton()) {
                    z = true;
                }
                if (z) {
                    this.this$0.showContextMenuOverflowButton();
                }
            }
        };
        this.onScrollListener = r10;
        BauhausFilterActionBarView bauhausFilterActionBarView = new BauhausFilterActionBarView(fragment.getActivity(), null, false, false, null, 30, null);
        this.actionBarView = bauhausFilterActionBarView;
        bauhausFilterActionBarView.setOnFilterButtonClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseViewsFilterActionBarManager.m112_init_$lambda0(this.f$0, view);
            }
        });
        if (actionBarProvider != null) {
            actionBarProvider.setHeaderView(this.actionBarView);
        }
        if (actionBarProvider != null) {
            actionBarProvider.requestHomeButtonAsBack();
        }
        BauhausActionBarView.Companion companion = BauhausActionBarView.INSTANCE;
        Resources resources = fragment.getResources();
        Intrinsics.checkNotNullExpressionValue(resources, "fragment.resources");
        this.actionBarHeight = companion.getActionBarHeight(resources);
        RecyclerView recyclerView2 = this.recyclerView;
        if (recyclerView2 == 0) {
            return;
        }
        recyclerView2.addOnScrollListener(r10);
    }

    private final Fragment getFragment() {
        return this._fragment.get();
    }

    /* JADX INFO: compiled from: BaseViewsFilterActionBarManager.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager$Companion;", "", "()V", "DEFAULT_REQUEST_CODE", "", "TAG", "", "kotlin.jvm.PlatformType", "isTargetViewUnderActionBarView", "", "targetPosition", "actionBarHeight", "layoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isTargetViewUnderActionBarView(int targetPosition, int actionBarHeight, LinearLayoutManager layoutManager) {
            Boolean boolValueOf;
            if (layoutManager == null) {
                return false;
            }
            View viewFindViewByPosition = layoutManager.findViewByPosition(targetPosition);
            if (viewFindViewByPosition == null) {
                boolValueOf = null;
            } else {
                boolValueOf = Boolean.valueOf(viewFindViewByPosition.getTop() < actionBarHeight);
            }
            if (boolValueOf == null) {
                return layoutManager.findFirstVisibleItemPosition() > targetPosition;
            }
            return boolValueOf.booleanValue();
        }
    }

    /* JADX INFO: compiled from: BaseViewsFilterActionBarManager.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\n\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u000f\u0010\b\u001a\u0004\u0018\u00010\tH&¢\u0006\u0002\u0010\nJ\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH&J\n\u0010\u000e\u001a\u0004\u0018\u00010\u000fH&J\n\u0010\u0010\u001a\u0004\u0018\u00010\u0005H\u0016J\b\u0010\u0011\u001a\u00020\u0003H\u0016J\b\u0010\u0012\u001a\u00020\u0003H&J\u001a\u0010\u0013\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\tH\u0016J\u0010\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\tH&J\b\u0010\u0018\u001a\u00020\u0003H&J\b\u0010\u0019\u001a\u00020\u0003H\u0016¨\u0006\u001a"}, d2 = {"Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager$InfoProvider;", "", "doesHaveRefinement", "", "getArtworkUrl", "", "getContentMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "getFilterButtonClickDestination", "", "()Ljava/lang/Integer;", "getRefinementFilterModels", "", "Lcom/amazon/music/views/library/models/FilterModel;", "getRefinementListModel", "Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "getTitle", "shouldDisplayContextMenuOverflowButton", "shouldDisplayFilterAndContextButtons", "shouldDisplayFilterButton", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "actionBarHeight", "shouldDisplayPageComponentsInTopAppBar", "shouldDisplayTitle", "shouldHideRefinementsOnLoad", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface InfoProvider {
        boolean doesHaveRefinement();

        String getArtworkUrl();

        ContentMetadata getContentMetadata();

        Integer getFilterButtonClickDestination();

        List<FilterModel> getRefinementFilterModels();

        ExposedRefinementListModel getRefinementListModel();

        String getTitle();

        boolean shouldDisplayContextMenuOverflowButton();

        boolean shouldDisplayFilterAndContextButtons();

        boolean shouldDisplayFilterButton(RecyclerView recyclerView, int actionBarHeight);

        boolean shouldDisplayPageComponentsInTopAppBar(int actionBarHeight);

        boolean shouldDisplayTitle();

        boolean shouldHideRefinementsOnLoad();

        /* JADX INFO: compiled from: BaseViewsFilterActionBarManager.kt */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        public static final class DefaultImpls {
            public static boolean doesHaveRefinement(InfoProvider infoProvider) {
                Intrinsics.checkNotNullParameter(infoProvider, "this");
                return true;
            }

            public static String getTitle(InfoProvider infoProvider) {
                Intrinsics.checkNotNullParameter(infoProvider, "this");
                return null;
            }

            public static boolean shouldDisplayContextMenuOverflowButton(InfoProvider infoProvider) {
                Intrinsics.checkNotNullParameter(infoProvider, "this");
                return false;
            }

            public static boolean shouldHideRefinementsOnLoad(InfoProvider infoProvider) {
                Intrinsics.checkNotNullParameter(infoProvider, "this");
                return false;
            }

            public static boolean shouldDisplayFilterButton(InfoProvider infoProvider, RecyclerView recyclerView, int i) {
                View viewFindViewByPosition;
                Intrinsics.checkNotNullParameter(infoProvider, "this");
                RecyclerView.LayoutManager layoutManager = recyclerView == null ? null : recyclerView.getLayoutManager();
                LinearLayoutManager linearLayoutManager = layoutManager instanceof LinearLayoutManager ? (LinearLayoutManager) layoutManager : null;
                Integer numValueOf = linearLayoutManager != null ? Integer.valueOf(linearLayoutManager.findFirstVisibleItemPosition()) : null;
                return numValueOf != null && numValueOf.intValue() == 0 && (viewFindViewByPosition = linearLayoutManager.findViewByPosition(0)) != null && viewFindViewByPosition.getTop() == i;
            }
        }
    }

    public final int getActionBarHeight() {
        return this.actionBarHeight;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m112_init_$lambda0(BaseViewsFilterActionBarManager this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        InfoProvider infoProvider = this$0.infoProvider;
        ExposedRefinementListModel refinementListModel = infoProvider == null ? null : infoProvider.getRefinementListModel();
        boolean z = false;
        if (refinementListModel != null && !refinementListModel.isExposed()) {
            z = true;
        }
        if (z) {
            ExposedRefinementListModel.show$default(refinementListModel, null, 1, null);
        }
        this$0.smoothScrollOnFilterButtonClick();
    }

    public final void setAdapterProviderForActionBarView(BaseViewModelAdapterProvider adapterProvider, UniversalBinder<?, ?> refinementBinder, List<? extends FilterModel> filterModels) {
        Intrinsics.checkNotNullParameter(adapterProvider, "adapterProvider");
        Intrinsics.checkNotNullParameter(refinementBinder, "refinementBinder");
        Intrinsics.checkNotNullParameter(filterModels, "filterModels");
        BauhausFilterActionBarView bauhausFilterActionBarView = this.actionBarView;
        if (bauhausFilterActionBarView == null) {
            return;
        }
        bauhausFilterActionBarView.setAdapterProvider(adapterProvider, refinementBinder, filterModels);
    }

    public static /* synthetic */ void shouldShowExposedRefinementList$default(BaseViewsFilterActionBarManager baseViewsFilterActionBarManager, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        baseViewsFilterActionBarManager.shouldShowExposedRefinementList(z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void shouldShowExposedRefinementList(boolean forceShow) {
        InfoProvider infoProvider = this.infoProvider;
        ExposedRefinementListModel refinementListModel = infoProvider == null ? null : infoProvider.getRefinementListModel();
        if (((refinementListModel == null || refinementListModel.isExposed()) ? false : true) != false) {
            InfoProvider infoProvider2 = this.infoProvider;
            if ((infoProvider2 != null && infoProvider2.shouldHideRefinementsOnLoad()) != false) {
                BauhausFilterActionBarView bauhausFilterActionBarView = this.actionBarView;
                if (bauhausFilterActionBarView != null) {
                    BauhausFilterActionBarView.showExposedRefinements$default(bauhausFilterActionBarView, false, null, 2, null);
                }
                if (refinementListModel == null) {
                    return;
                }
                refinementListModel.hide(8);
                return;
            }
        }
        InfoProvider infoProvider3 = this.infoProvider;
        if ((infoProvider3 != null && infoProvider3.shouldDisplayPageComponentsInTopAppBar(this.actionBarHeight)) != false || forceShow) {
            Integer numValueOf = refinementListModel == null ? null : Integer.valueOf(refinementListModel.hide(4));
            BauhausFilterActionBarView bauhausFilterActionBarView2 = this.actionBarView;
            if (((bauhausFilterActionBarView2 == null || bauhausFilterActionBarView2.isExposedRefinementsShown()) ? false : true) != false) {
                BauhausFilterActionBarView bauhausFilterActionBarView3 = this.actionBarView;
                if (bauhausFilterActionBarView3 != null) {
                    InfoProvider infoProvider4 = this.infoProvider;
                    bauhausFilterActionBarView3.setFilters(infoProvider4 != null ? infoProvider4.getRefinementFilterModels() : null);
                }
                BauhausFilterActionBarView bauhausFilterActionBarView4 = this.actionBarView;
                if (bauhausFilterActionBarView4 != null) {
                    bauhausFilterActionBarView4.showExposedRefinements(true, numValueOf);
                }
            }
            InfoProvider infoProvider5 = this.infoProvider;
            if (infoProvider5 != null && infoProvider5.shouldDisplayFilterAndContextButtons()) {
                shouldShowFilterButton();
                return;
            }
            return;
        }
        BauhausFilterActionBarView bauhausFilterActionBarView5 = this.actionBarView;
        if (bauhausFilterActionBarView5 != null) {
            BauhausFilterActionBarView.showExposedRefinements$default(bauhausFilterActionBarView5, false, null, 2, null);
        }
        if (refinementListModel == null) {
            return;
        }
        BauhausFilterActionBarView bauhausFilterActionBarView6 = this.actionBarView;
        refinementListModel.show(bauhausFilterActionBarView6 != null ? Integer.valueOf(bauhausFilterActionBarView6.getRefinementListScrollOffset()) : null);
    }

    public final BauhausFilterActionBarView getActionBarView() {
        return this.actionBarView;
    }

    public final void refreshActionBarFilters() {
        BauhausFilterActionBarView bauhausFilterActionBarView = this.actionBarView;
        if (bauhausFilterActionBarView == null) {
            return;
        }
        InfoProvider infoProvider = this.infoProvider;
        bauhausFilterActionBarView.setFilters(infoProvider == null ? null : infoProvider.getRefinementFilterModels());
    }

    public final void forceShowTitle() {
        BauhausFilterActionBarView bauhausFilterActionBarView = this.actionBarView;
        if (bauhausFilterActionBarView != null) {
            InfoProvider infoProvider = this.infoProvider;
            bauhausFilterActionBarView.setTitles(infoProvider == null ? null : infoProvider.getTitle(), null);
        }
        BauhausFilterActionBarView bauhausFilterActionBarView2 = this.actionBarView;
        if (bauhausFilterActionBarView2 == null) {
            return;
        }
        bauhausFilterActionBarView2.showTitle();
    }

    public final void shouldShowPageComponentInTopAppBar() {
        InfoProvider infoProvider = this.infoProvider;
        boolean z = false;
        if (infoProvider != null && infoProvider.shouldDisplayTitle()) {
            z = true;
        }
        if (z) {
            showOrHideTitle();
        } else {
            showOrHideArtwork();
        }
    }

    private final void showOrHideTitle() {
        InfoProvider infoProvider = this.infoProvider;
        boolean z = false;
        if (infoProvider != null && infoProvider.shouldDisplayPageComponentsInTopAppBar(this.actionBarHeight)) {
            z = true;
        }
        if (z) {
            BauhausFilterActionBarView bauhausFilterActionBarView = this.actionBarView;
            if (bauhausFilterActionBarView != null) {
                InfoProvider infoProvider2 = this.infoProvider;
                bauhausFilterActionBarView.setTitles(infoProvider2 == null ? null : infoProvider2.getTitle(), null);
            }
            BauhausFilterActionBarView bauhausFilterActionBarView2 = this.actionBarView;
            if (bauhausFilterActionBarView2 == null) {
                return;
            }
            bauhausFilterActionBarView2.showTitle();
            return;
        }
        BauhausFilterActionBarView bauhausFilterActionBarView3 = this.actionBarView;
        if (bauhausFilterActionBarView3 == null) {
            return;
        }
        bauhausFilterActionBarView3.hideTitle();
    }

    private final void showOrHideArtwork() {
        InfoProvider infoProvider;
        BauhausFilterActionBarView bauhausFilterActionBarView;
        String artworkUrl;
        Unit unit;
        InfoProvider infoProvider2 = this.infoProvider;
        Unit unit2 = null;
        if (infoProvider2 != null && (artworkUrl = infoProvider2.getArtworkUrl()) != null) {
            InfoProvider infoProvider3 = this.infoProvider;
            boolean z = false;
            if (infoProvider3 != null && infoProvider3.shouldDisplayPageComponentsInTopAppBar(getActionBarHeight())) {
                z = true;
            }
            if (z) {
                BauhausFilterActionBarView bauhausFilterActionBarView2 = this.actionBarView;
                if (bauhausFilterActionBarView2 != null) {
                    bauhausFilterActionBarView2.showAlbumImage(artworkUrl);
                    unit = Unit.INSTANCE;
                    unit2 = unit;
                }
            } else {
                BauhausFilterActionBarView bauhausFilterActionBarView3 = this.actionBarView;
                if (bauhausFilterActionBarView3 != null) {
                    bauhausFilterActionBarView3.hideAlbumImage();
                    unit = Unit.INSTANCE;
                    unit2 = unit;
                }
            }
        }
        if (unit2 != null || (infoProvider = this.infoProvider) == null || (bauhausFilterActionBarView = this.actionBarView) == null) {
            return;
        }
        bauhausFilterActionBarView.showCachedImage(infoProvider.shouldDisplayPageComponentsInTopAppBar(getActionBarHeight()), infoProvider.getContentMetadata());
    }

    public final void shouldShowFilterButton() {
        BauhausFilterActionBarView bauhausFilterActionBarView;
        InfoProvider infoProvider = this.infoProvider;
        if (infoProvider == null || (bauhausFilterActionBarView = this.actionBarView) == null) {
            return;
        }
        bauhausFilterActionBarView.showFilterButton(infoProvider.shouldDisplayFilterButton(this.recyclerView, getActionBarHeight()));
    }

    public final void hideFilterAndContextButtons() {
        BauhausFilterActionBarView bauhausFilterActionBarView = this.actionBarView;
        if (bauhausFilterActionBarView == null) {
            return;
        }
        bauhausFilterActionBarView.hideFilterAndContextButtons();
    }

    public final void showContextMenuOverflowButton() {
        BauhausFilterActionBarView bauhausFilterActionBarView = this.actionBarView;
        if (bauhausFilterActionBarView == null) {
            return;
        }
        bauhausFilterActionBarView.showContextMenuOverflowButton(true);
    }

    public final void hideContextMenuOverflowButton() {
        BauhausFilterActionBarView bauhausFilterActionBarView = this.actionBarView;
        if (bauhausFilterActionBarView == null) {
            return;
        }
        bauhausFilterActionBarView.showContextMenuOverflowButton(false);
    }

    private final void setActionBarOverlayStyle(boolean isOverlay, ActionBarOverlayable actionBarOverlayable) {
        RecyclerView.OnScrollListener onScrollListener;
        if (actionBarOverlayable != null) {
            actionBarOverlayable.setActionBarOverlay(isOverlay);
        }
        BauhausFilterActionBarView bauhausFilterActionBarView = this.actionBarView;
        if (bauhausFilterActionBarView == null || (onScrollListener = bauhausFilterActionBarView.getOnScrollListener()) == null) {
            return;
        }
        if (isOverlay) {
            RecyclerView recyclerView = this.recyclerView;
            if (recyclerView == null) {
                return;
            }
            recyclerView.addOnScrollListener(onScrollListener);
            return;
        }
        RecyclerView recyclerView2 = this.recyclerView;
        if (recyclerView2 == null) {
            return;
        }
        recyclerView2.removeOnScrollListener(onScrollListener);
    }

    public final void setActionBarContextMenuProvider(final ContentMetadata contentMetadata, final MetadataContextMenuHandler contextMenuProvider) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        Intrinsics.checkNotNullParameter(contextMenuProvider, "contextMenuProvider");
        BauhausFilterActionBarView bauhausFilterActionBarView = this.actionBarView;
        if (bauhausFilterActionBarView == null) {
            return;
        }
        bauhausFilterActionBarView.setContextMenuOverflowOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseViewsFilterActionBarManager.m113setActionBarContextMenuProvider$lambda6(contextMenuProvider, contentMetadata, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setActionBarContextMenuProvider$lambda-6, reason: not valid java name */
    public static final void m113setActionBarContextMenuProvider$lambda6(MetadataContextMenuHandler contextMenuProvider, ContentMetadata contentMetadata, View it) {
        Intrinsics.checkNotNullParameter(contextMenuProvider, "$contextMenuProvider");
        Intrinsics.checkNotNullParameter(contentMetadata, "$contentMetadata");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        contextMenuProvider.openContextMenu(it, contentMetadata, InteractionType.TAP);
    }

    private final void smoothScrollOnFilterButtonClick() {
        RecyclerView.LayoutManager layoutManager;
        InfoProvider infoProvider = this.infoProvider;
        Integer filterButtonClickDestination = infoProvider == null ? null : infoProvider.getFilterButtonClickDestination();
        if (filterButtonClickDestination == null || filterButtonClickDestination.intValue() == -1) {
            return;
        }
        setTargetPosition(filterButtonClickDestination.intValue());
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView == null || (layoutManager = recyclerView.getLayoutManager()) == null) {
            return;
        }
        layoutManager.startSmoothScroll(this.smoothScroller);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onStart(LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        super.onStart(owner);
        Fragment fragment = getFragment();
        FragmentActivity activity = fragment == null ? null : fragment.getActivity();
        ActionBarOverlayable actionBarOverlayable = activity instanceof ActionBarOverlayable ? activity : null;
        if (actionBarOverlayable == null) {
            return;
        }
        setActionBarOverlayStyle(true, actionBarOverlayable);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onStop(LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        super.onStop(owner);
        Fragment fragment = getFragment();
        FragmentActivity activity = fragment == null ? null : fragment.getActivity();
        ActionBarOverlayable actionBarOverlayable = activity instanceof ActionBarOverlayable ? activity : null;
        if (actionBarOverlayable == null) {
            return;
        }
        setActionBarOverlayStyle(false, actionBarOverlayable);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onDestroy(LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        super.onDestroy(owner);
        BauhausFilterActionBarView bauhausFilterActionBarView = this.actionBarView;
        if (bauhausFilterActionBarView != null) {
            bauhausFilterActionBarView.clearSubscriptions();
        }
        this.infoProvider = null;
        this.recyclerView = null;
    }
}
