package com.byd.overseaappstore.ui.view.adapter;

import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.byd.overseaappstore.ui.view.base.BaseFragment;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u001c\u0010\b\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u00060\u00050\u0004¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/byd/overseaappstore/ui/view/adapter/ManageVpAdapter;", "Landroidx/viewpager2/adapter/FragmentStateAdapter;", "Landroidx/fragment/app/Fragment;", "fragment", "", "Ljava/lang/Class;", "Lcom/byd/overseaappstore/ui/view/base/BaseFragment;", "Landroidx/viewbinding/ViewBinding;", "fragmentClassList", "<init>", "(Landroidx/fragment/app/Fragment;Ljava/util/List;)V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class ManageVpAdapter extends FragmentStateAdapter {

    @NotNull
    public final List<Class<? extends BaseFragment<? extends ViewBinding>>> a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ManageVpAdapter(@NotNull Fragment fragment, @NotNull List<? extends Class<? extends BaseFragment<? extends ViewBinding>>> fragmentClassList) {
        super(fragment);
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(fragmentClassList, "fragmentClassList");
        this.a = fragmentClassList;
    }

    @Override // androidx.viewpager2.adapter.FragmentStateAdapter
    @NotNull
    public final Fragment createFragment(int i) throws IllegalAccessException, InstantiationException {
        BaseFragment<? extends ViewBinding> baseFragmentNewInstance = this.a.get(i).newInstance();
        Intrinsics.checkNotNullExpressionValue(baseFragmentNewInstance, "fragmentClassList[position].newInstance()");
        return baseFragmentNewInstance;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        return this.a.size();
    }
}
