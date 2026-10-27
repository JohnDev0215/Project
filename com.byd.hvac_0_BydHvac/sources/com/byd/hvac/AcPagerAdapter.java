package com.byd.hvac;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import b.a.a.f.g1;
import com.byd.hvac.view.accontrol.AcFrontControlFragment;
import com.byd.hvac.view.accontrol.AcRearControlFragment;
import java.util.ArrayList;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class AcPagerAdapter extends FragmentStateAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f977a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ArrayList<Fragment> f978b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f979c;

    public AcPagerAdapter(FragmentManager fragmentManager, Lifecycle lifecycle, int i) {
        super(fragmentManager, lifecycle);
        this.f977a = i;
        this.f978b = new ArrayList<>();
        this.f979c = g1.e().i();
    }

    public void a() {
        ArrayList<Fragment> arrayList = this.f978b;
        if (arrayList != null) {
            arrayList.clear();
        }
    }

    public ArrayList<Fragment> b() {
        return this.f978b;
    }

    @Override // androidx.viewpager2.adapter.FragmentStateAdapter
    @NonNull
    public Fragment createFragment(int i) {
        if (i == 1) {
            AcRearControlFragment acRearControlFragment = new AcRearControlFragment();
            ArrayList<Fragment> arrayList = this.f978b;
            if (arrayList != null) {
                arrayList.add(acRearControlFragment);
            }
            return acRearControlFragment;
        }
        AcFrontControlFragment acFrontControlFragment = new AcFrontControlFragment();
        ArrayList<Fragment> arrayList2 = this.f978b;
        if (arrayList2 != null) {
            arrayList2.add(acFrontControlFragment);
        }
        return acFrontControlFragment;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f977a;
    }
}
