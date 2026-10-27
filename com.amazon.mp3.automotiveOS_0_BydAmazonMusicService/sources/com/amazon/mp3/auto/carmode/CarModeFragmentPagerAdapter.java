package com.amazon.mp3.auto.carmode;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarModeFragmentPagerAdapter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000bH\u0016R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/auto/carmode/CarModeFragmentPagerAdapter;", "Landroidx/fragment/app/FragmentPagerAdapter;", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "fragmentList", "", "Landroidx/fragment/app/Fragment;", "(Landroidx/fragment/app/FragmentManager;Ljava/util/List;)V", "getFragmentList", "()Ljava/util/List;", "getCount", "", "getItem", "position", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarModeFragmentPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragmentList;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public CarModeFragmentPagerAdapter(FragmentManager fragmentManager, List<? extends Fragment> fragmentList) {
        super(fragmentManager);
        Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
        Intrinsics.checkNotNullParameter(fragmentList, "fragmentList");
        this.fragmentList = fragmentList;
    }

    public final List<Fragment> getFragmentList() {
        return this.fragmentList;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.fragmentList.size();
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    public Fragment getItem(int position) {
        return this.fragmentList.get(position);
    }
}
