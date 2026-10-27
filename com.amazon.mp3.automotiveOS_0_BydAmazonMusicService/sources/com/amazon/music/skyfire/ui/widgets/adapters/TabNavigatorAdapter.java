package com.amazon.music.skyfire.ui.widgets.adapters;

import Touch.MultiSelectorTemplateInterface.v1_0.TabNavigatorWidgetItemElement;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.skyfire.ui.fragment.MultiSelectorTabFragment;
import com.amazon.music.skyfire.ui.providers.ElementBindingProvider;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TabNavigatorAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000bH\u0016J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u000e\u001a\u00020\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/adapters/TabNavigatorAdapter;", "Landroidx/fragment/app/FragmentStatePagerAdapter;", "bindingProvider", "Lcom/amazon/music/skyfire/ui/providers/ElementBindingProvider;", ContextMappingConstants.ITEMS, "", "LTouch/MultiSelectorTemplateInterface/v1_0/TabNavigatorWidgetItemElement;", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "(Lcom/amazon/music/skyfire/ui/providers/ElementBindingProvider;Ljava/util/List;Landroidx/fragment/app/FragmentManager;)V", "getCount", "", "getItem", "Landroidx/fragment/app/Fragment;", "position", "getPageTitle", "", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TabNavigatorAdapter extends FragmentStatePagerAdapter {
    private final ElementBindingProvider bindingProvider;
    private final List<TabNavigatorWidgetItemElement> items;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public TabNavigatorAdapter(ElementBindingProvider bindingProvider, List<? extends TabNavigatorWidgetItemElement> items, FragmentManager fragmentManager) {
        super(fragmentManager);
        Intrinsics.checkNotNullParameter(bindingProvider, "bindingProvider");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
        this.bindingProvider = bindingProvider;
        this.items = items;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.items.size();
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter
    public Fragment getItem(int position) {
        MultiSelectorTabFragment multiSelectorTabFragment = new MultiSelectorTabFragment();
        multiSelectorTabFragment.setElementBindingProvider(this.bindingProvider);
        multiSelectorTabFragment.setElement(this.items.get(position).items());
        return multiSelectorTabFragment;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(int position) {
        return this.items.get(position).label();
    }
}
