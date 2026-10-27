package com.amazon.music.skyfire.ui.fragment;

import CoreInterface.v1_0.Method;
import Touch.DetailTemplateInterface.v1_0.AddVerticalItemsMethod;
import Touch.DetailTemplateInterface.v1_0.DetailTemplate;
import Touch.DetailTemplateInterface.v1_0.ImmutableDetailTemplate;
import Touch.LiveStreamPlayerTemplateInterface.v1_0.DisplayGenericErrorMethod;
import Touch.WidgetsInterface.v1_0.VerticalItemElement;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.amazon.music.skyfire.ui.R;
import com.amazon.music.skyfire.ui.ktx.ViewGroupKt;
import com.amazon.music.skyfire.ui.providers.UserSubscriptionProvider;
import com.amazon.music.skyfire.ui.utils.ViewFinder;
import com.amazon.music.skyfire.ui.utils.ViewFinderKt;
import com.amazon.music.skyfire.ui.widgets.adapters.VerticalItemAdapter;
import com.amazon.music.views.library.binders.GridLayoutUtils;
import com.amazon.music.views.library.styles.StyleSheet;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DetailFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0002H\u0002J\u0010\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0002H\u0002J&\u0010\u001c\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u0006\u001a\u0004\u0018\u00010\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0010\u0010\"\u001a\u00020\u00192\u0006\u0010#\u001a\u00020$H\u0016J\b\u0010%\u001a\u00020\u0019H\u0016J\u001a\u0010&\u001a\u00020\u00192\u0006\u0010'\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\b\u0010(\u001a\u00020\u0019H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u00020\u00078\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00020\t8\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u00020\r8\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u00020\u000f8\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u00020\u00118\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u001e\u0010\u0012\u001a\u00020\u00138\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006)"}, d2 = {"Lcom/amazon/music/skyfire/ui/fragment/DetailFragment;", "Lcom/amazon/music/skyfire/ui/fragment/MusicHomeFragment;", "LTouch/DetailTemplateInterface/v1_0/DetailTemplate;", "()V", "adapter", "Lcom/amazon/music/skyfire/ui/widgets/adapters/VerticalItemAdapter;", "container", "Landroid/widget/LinearLayout;", "errorView", "Landroid/view/View;", "inErrorState", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "spinner", "Landroid/widget/ProgressBar;", "swipeRefreshLayout", "Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;", "userSubscriptionProvider", "Lcom/amazon/music/skyfire/ui/providers/UserSubscriptionProvider;", "getUserSubscriptionProvider", "()Lcom/amazon/music/skyfire/ui/providers/UserSubscriptionProvider;", "setUserSubscriptionProvider", "(Lcom/amazon/music/skyfire/ui/providers/UserSubscriptionProvider;)V", "configureActionBarTitle", "", "template", "onConfigureActionBar", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onHandleMethod", "method", "LCoreInterface/v1_0/Method;", "onTemplateChanged", "onViewCreated", "view", "rebind", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DetailFragment extends MusicHomeFragment<DetailTemplate> {
    private VerticalItemAdapter adapter;

    @ViewFinder.AView
    private LinearLayout container;

    @ViewFinder.AView
    private View errorView;
    private boolean inErrorState;

    @ViewFinder.AView
    private RecyclerView recyclerView;

    @ViewFinder.AView
    private ProgressBar spinner;

    @ViewFinder.AView
    private SwipeRefreshLayout swipeRefreshLayout;
    public UserSubscriptionProvider userSubscriptionProvider;

    public final UserSubscriptionProvider getUserSubscriptionProvider() {
        UserSubscriptionProvider userSubscriptionProvider = this.userSubscriptionProvider;
        if (userSubscriptionProvider != null) {
            return userSubscriptionProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("userSubscriptionProvider");
        return null;
    }

    public final void setUserSubscriptionProvider(UserSubscriptionProvider userSubscriptionProvider) {
        Intrinsics.checkNotNullParameter(userSubscriptionProvider, "<set-?>");
        this.userSubscriptionProvider = userSubscriptionProvider;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate$default;
        RecyclerView recyclerView;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        VerticalItemAdapter verticalItemAdapter = null;
        View viewFindViews = (container == null || (viewInflate$default = ViewGroupKt.inflate$default(container, R.layout.dmsf_fragment_detail, false, 2, null)) == null) ? null : ViewFinderKt.findViews(viewInflate$default, this);
        if (viewFindViews == null) {
            return null;
        }
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        Intrinsics.checkNotNullExpressionValue(context, "context!!");
        StyleSheet styleSheet = getStyleSheet();
        if (styleSheet == null) {
            return null;
        }
        GridLayoutUtils gridLayoutUtils = new GridLayoutUtils(context, styleSheet);
        RecyclerView recyclerView2 = this.recyclerView;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            recyclerView = null;
        } else {
            recyclerView = recyclerView2;
        }
        GridLayoutUtils.setLayoutManager$default(gridLayoutUtils, recyclerView, 1, null, false, false, null, null, 96, null);
        SwipeRefreshLayout swipeRefreshLayout = this.swipeRefreshLayout;
        if (swipeRefreshLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeRefreshLayout");
            swipeRefreshLayout = null;
        }
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.amazon.music.skyfire.ui.fragment.DetailFragment$$ExternalSyntheticLambda0
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                DetailFragment.m2194onCreateView$lambda0(this.f$0);
            }
        });
        this.adapter = new VerticalItemAdapter(getListenerId(), getSkyFireApplication(), getElementBindingProvider());
        RecyclerView recyclerView3 = this.recyclerView;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            recyclerView3 = null;
        }
        VerticalItemAdapter verticalItemAdapter2 = this.adapter;
        if (verticalItemAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            verticalItemAdapter = verticalItemAdapter2;
        }
        recyclerView3.setAdapter(verticalItemAdapter);
        return viewFindViews;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-0, reason: not valid java name */
    public static final void m2194onCreateView$lambda0(DetailFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.resetTemplate();
        this$0.fetchInitialMethods();
    }

    @Override // com.amazon.music.skyfire.ui.fragment.MusicHomeFragment, com.amazon.music.skyfire.ui.fragment.TemplateFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        rebind();
    }

    @Override // com.amazon.music.skyfire.ui.fragment.TemplateFragment
    public void onTemplateChanged() {
        SwipeRefreshLayout swipeRefreshLayout = this.swipeRefreshLayout;
        if (swipeRefreshLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeRefreshLayout");
            swipeRefreshLayout = null;
        }
        swipeRefreshLayout.setRefreshing(false);
        rebind();
    }

    @Override // com.amazon.music.skyfire.ui.fragment.TemplateFragment, com.amazon.music.skyfire.ui.skyfire.TemplateMethodListener
    public void onHandleMethod(Method method) throws IllegalAccessException, NoSuchFieldException {
        Intrinsics.checkNotNullParameter(method, "method");
        SwipeRefreshLayout swipeRefreshLayout = null;
        if (method instanceof DisplayGenericErrorMethod) {
            getLogger().error(method.toString());
            this.inErrorState = true;
            rebind();
            SwipeRefreshLayout swipeRefreshLayout2 = this.swipeRefreshLayout;
            if (swipeRefreshLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("swipeRefreshLayout");
            } else {
                swipeRefreshLayout = swipeRefreshLayout2;
            }
            swipeRefreshLayout.setRefreshing(false);
            return;
        }
        if (method instanceof AddVerticalItemsMethod) {
            AddVerticalItemsMethod addVerticalItemsMethod = (AddVerticalItemsMethod) method;
            ImmutableDetailTemplate immutableDetailTemplateBuild = ImmutableDetailTemplate.builder().from((DetailTemplate) getTemplate().getValue()).addAllItems(addVerticalItemsMethod.items()).onEndOfList(addVerticalItemsMethod.onEndOfList()).build();
            Intrinsics.checkNotNullExpressionValue(immutableDetailTemplateBuild, "builder()\n              …                 .build()");
            overrideViewModelTemplate(immutableDetailTemplateBuild);
            VerticalItemAdapter verticalItemAdapter = this.adapter;
            if (verticalItemAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                verticalItemAdapter = null;
            }
            verticalItemAdapter.setOnEndOfList(addVerticalItemsMethod.onEndOfList());
            VerticalItemAdapter verticalItemAdapter2 = this.adapter;
            if (verticalItemAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                verticalItemAdapter2 = null;
            }
            List<VerticalItemElement> listItems = addVerticalItemsMethod.items();
            Intrinsics.checkNotNullExpressionValue(listItems, "method.items()");
            verticalItemAdapter2.addItems(listItems);
            SwipeRefreshLayout swipeRefreshLayout3 = this.swipeRefreshLayout;
            if (swipeRefreshLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("swipeRefreshLayout");
            } else {
                swipeRefreshLayout = swipeRefreshLayout3;
            }
            swipeRefreshLayout.setRefreshing(false);
            return;
        }
        super.onHandleMethod(method);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void rebind() {
        /*
            r6 = this;
            androidx.lifecycle.LiveData r0 = r6.getTemplate()
            java.lang.Object r0 = r0.getValue()
            Touch.DetailTemplateInterface.v1_0.DetailTemplate r0 = (Touch.DetailTemplateInterface.v1_0.DetailTemplate) r0
            android.widget.ProgressBar r1 = r6.spinner
            r2 = 0
            if (r1 != 0) goto L16
            java.lang.String r1 = "spinner"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r1 = r2
        L16:
            r3 = 1
            java.lang.String r4 = "adapter"
            if (r0 != 0) goto L30
            com.amazon.music.skyfire.ui.widgets.adapters.VerticalItemAdapter r5 = r6.adapter
            if (r5 != 0) goto L23
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r5 = r2
        L23:
            int r5 = r5.getItemCount()
            if (r5 > 0) goto L30
            boolean r5 = r6.inErrorState
            if (r5 == 0) goto L2e
            goto L30
        L2e:
            r5 = 0
            goto L31
        L30:
            r5 = r3
        L31:
            com.amazon.ui.foundations.ktx.ViewKt.collapseIf(r1, r5)
            android.view.View r1 = r6.errorView
            if (r1 != 0) goto L3e
            java.lang.String r1 = "errorView"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r1 = r2
        L3e:
            boolean r5 = r6.inErrorState
            r3 = r3 ^ r5
            com.amazon.ui.foundations.ktx.ViewKt.collapseIf(r1, r3)
            if (r0 != 0) goto L47
            return
        L47:
            r6.onConfigureActionBar(r0)
            com.amazon.music.skyfire.ui.widgets.adapters.VerticalItemAdapter r1 = r6.adapter
            if (r1 != 0) goto L52
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r1 = r2
        L52:
            r1.clear()
            com.amazon.music.skyfire.ui.widgets.adapters.VerticalItemAdapter r1 = r6.adapter
            if (r1 != 0) goto L5d
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r1 = r2
        L5d:
            java.util.List r3 = r0.onEndOfList()
            r1.setOnEndOfList(r3)
            com.amazon.music.skyfire.ui.widgets.adapters.VerticalItemAdapter r1 = r6.adapter
            if (r1 != 0) goto L6c
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            goto L6d
        L6c:
            r2 = r1
        L6d:
            java.util.List r0 = r0.items()
            java.lang.String r1 = "template.items()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r2.addItems(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.skyfire.ui.fragment.DetailFragment.rebind():void");
    }

    private final void onConfigureActionBar(DetailTemplate template) {
        configureActionBarTitle(template);
    }

    private final void configureActionBarTitle(DetailTemplate template) {
        getActionBarView().setTitle(template.header());
    }
}
