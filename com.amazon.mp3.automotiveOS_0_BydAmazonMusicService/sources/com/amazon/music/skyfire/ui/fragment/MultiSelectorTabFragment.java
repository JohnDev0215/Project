package com.amazon.music.skyfire.ui.fragment;

import Touch.MultiSelectorTemplateInterface.v1_0.SelectableTextButtonListingElement;
import Touch.WidgetsInterface.v1_0.TextButtonElement;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.skyfire.ui.R;
import com.amazon.music.skyfire.ui.ktx.ViewGroupKt;
import com.amazon.music.skyfire.ui.providers.ElementBindingProvider;
import com.amazon.music.skyfire.ui.utils.ViewFinder;
import com.amazon.music.skyfire.ui.utils.ViewFinderKt;
import com.amazon.music.skyfire.ui.widgets.adapters.TextButtonAdapter;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ScreenSizeKey;
import com.google.android.flexbox.FlexboxItemDecoration;
import com.google.android.flexbox.FlexboxLayoutManager;
import dagger.android.support.AndroidSupportInjection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MultiSelectorTabFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0012\u0010\u0019\u001a\u00020\u00142\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J&\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u001a\u0010\"\u001a\u00020\u00142\u0006\u0010#\u001a\u00020\u001d2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010$\u001a\u00020\u0014H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0011\u001a\u00020\u00128\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/amazon/music/skyfire/ui/fragment/MultiSelectorTabFragment;", "Landroidx/fragment/app/Fragment;", "()V", "adapter", "Lcom/amazon/music/skyfire/ui/widgets/adapters/TextButtonAdapter;", "element", "LTouch/MultiSelectorTemplateInterface/v1_0/SelectableTextButtonListingElement;", "getElement", "()LTouch/MultiSelectorTemplateInterface/v1_0/SelectableTextButtonListingElement;", "setElement", "(LTouch/MultiSelectorTemplateInterface/v1_0/SelectableTextButtonListingElement;)V", "elementBindingProvider", "Lcom/amazon/music/skyfire/ui/providers/ElementBindingProvider;", "getElementBindingProvider", "()Lcom/amazon/music/skyfire/ui/providers/ElementBindingProvider;", "setElementBindingProvider", "(Lcom/amazon/music/skyfire/ui/providers/ElementBindingProvider;)V", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "decorateFlexboxItem", "", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "context", "Landroid/content/Context;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "rebind", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MultiSelectorTabFragment extends Fragment {
    private TextButtonAdapter adapter;
    private SelectableTextButtonListingElement element;
    private ElementBindingProvider elementBindingProvider;

    @ViewFinder.AView
    private RecyclerView recyclerView;

    /* JADX INFO: compiled from: MultiSelectorTabFragment.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ScreenSizeKey.values().length];
            iArr[ScreenSizeKey.XSMALL.ordinal()] = 1;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final ElementBindingProvider getElementBindingProvider() {
        return this.elementBindingProvider;
    }

    public final void setElementBindingProvider(ElementBindingProvider elementBindingProvider) {
        this.elementBindingProvider = elementBindingProvider;
    }

    public final SelectableTextButtonListingElement getElement() {
        return this.element;
    }

    public final void setElement(SelectableTextButtonListingElement selectableTextButtonListingElement) {
        this.element = selectableTextButtonListingElement;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
        setRetainInstance(true);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate$default;
        Context context;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        TextButtonAdapter textButtonAdapter = null;
        View viewFindViews = (container == null || (viewInflate$default = ViewGroupKt.inflate$default(container, R.layout.pill_listing_view, false, 2, null)) == null) ? null : ViewFinderKt.findViews(viewInflate$default, this);
        if (viewFindViews == null || (context = getContext()) == null) {
            return null;
        }
        ElementBindingProvider elementBindingProvider = this.elementBindingProvider;
        if (elementBindingProvider != null) {
            this.adapter = new TextButtonAdapter(elementBindingProvider);
            FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(context);
            flexboxLayoutManager.setFlexDirection(0);
            flexboxLayoutManager.setFlexWrap(1);
            flexboxLayoutManager.setJustifyContent(0);
            flexboxLayoutManager.setAlignItems(0);
            RecyclerView recyclerView = this.recyclerView;
            if (recyclerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
                recyclerView = null;
            }
            recyclerView.setLayoutManager(flexboxLayoutManager);
            RecyclerView recyclerView2 = this.recyclerView;
            if (recyclerView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
                recyclerView2 = null;
            }
            TextButtonAdapter textButtonAdapter2 = this.adapter;
            if (textButtonAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                textButtonAdapter = textButtonAdapter2;
            }
            recyclerView2.setAdapter(textButtonAdapter);
            decorateFlexboxItem(elementBindingProvider.getStyleSheet(), context);
        }
        return viewFindViews;
    }

    private final void decorateFlexboxItem(StyleSheet styleSheet, Context context) {
        FlexboxItemDecoration flexboxItemDecoration = new FlexboxItemDecoration(context);
        if (WhenMappings.$EnumSwitchMapping$0[styleSheet.getWidthScreenSizeKey(context).ordinal()] == 1) {
            flexboxItemDecoration.setDrawable(context.getDrawable(R.drawable.flexbox_divider_xs));
        } else {
            flexboxItemDecoration.setDrawable(context.getDrawable(R.drawable.flexbox_divider));
        }
        flexboxItemDecoration.setOrientation(3);
        RecyclerView recyclerView = this.recyclerView;
        RecyclerView recyclerView2 = null;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            recyclerView = null;
        }
        if (recyclerView.getItemDecorationCount() == 0) {
            RecyclerView recyclerView3 = this.recyclerView;
            if (recyclerView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            } else {
                recyclerView2 = recyclerView3;
            }
            recyclerView2.addItemDecoration(flexboxItemDecoration);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        rebind();
    }

    private final void rebind() {
        TextButtonAdapter textButtonAdapter = this.adapter;
        TextButtonAdapter textButtonAdapter2 = null;
        if (textButtonAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            textButtonAdapter = null;
        }
        textButtonAdapter.clear();
        SelectableTextButtonListingElement selectableTextButtonListingElement = this.element;
        if (selectableTextButtonListingElement != null) {
            TextButtonAdapter textButtonAdapter3 = this.adapter;
            if (textButtonAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                textButtonAdapter3 = null;
            }
            List<TextButtonElement> listItems = selectableTextButtonListingElement.items();
            Intrinsics.checkNotNullExpressionValue(listItems, "it.items()");
            textButtonAdapter3.addItems(listItems);
        }
        TextButtonAdapter textButtonAdapter4 = this.adapter;
        if (textButtonAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            textButtonAdapter2 = textButtonAdapter4;
        }
        textButtonAdapter2.notifyDataSetChanged();
    }
}
