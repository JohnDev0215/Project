package com.byd.overseaappstore.ui.view.base;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.FragmentKt;
import androidx.viewbinding.ViewBinding;
import com.byd.overseaappstore.R;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.av;
import spi.ct;
import spi.d;
import spi.e;
import spi.xi;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/byd/overseaappstore/ui/view/base/BaseFragment;", "Landroidx/viewbinding/ViewBinding;", "VB", "Landroidx/fragment/app/Fragment;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public abstract class BaseFragment<VB extends ViewBinding> extends Fragment {
    public static final /* synthetic */ int b = 0;

    @Nullable
    public VB a;

    @Nullable
    public abstract VB b();

    public abstract void c();

    public abstract void d();

    public abstract void e();

    public abstract void f();

    public abstract void g();

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(@NotNull Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        ct.a(d.f("fg lifecycle onConfigurationChanged class:", getClass().getSimpleName()), new Object[0]);
        int i = newConfig.orientation;
        int i2 = newConfig.uiMode;
        ct.a(xi.f("orientation change uiMode:", i2, " tag:", getClass().getSimpleName()), new Object[0]);
        if (i == 1) {
            getParentFragmentManager().beginTransaction().detach(this).commitAllowingStateLoss();
            getParentFragmentManager().beginTransaction().attach(this).commitAllowingStateLoss();
            ct.a(xi.f("orientation change,orientation:PORT uiMode:", i2, " tag:", getClass().getSimpleName()), new Object[0]);
        } else {
            if (i != 2) {
                ct.a(xi.f("orientation change,orientation:unKnown uiMode:", i2, " tag:", getClass().getSimpleName()), new Object[0]);
                return;
            }
            getParentFragmentManager().beginTransaction().detach(this).commitAllowingStateLoss();
            getParentFragmentManager().beginTransaction().attach(this).commitAllowingStateLoss();
            ct.a(xi.f("orientation change,orientation:LAND uiMode:", i2, " tag:", getClass().getSimpleName()), new Object[0]);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ct.a(d.f("fg lifecycle onCreate class:", getClass().getSimpleName()), new Object[0]);
        e();
        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(this) { // from class: com.byd.overseaappstore.ui.view.base.BaseFragment$addBackPressedListener$callback$1
            public final /* synthetic */ BaseFragment<ViewBinding> a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(true);
                this.a = this;
            }

            @Override // androidx.activity.OnBackPressedCallback
            public final void handleOnBackPressed() {
                BaseFragment<ViewBinding> baseFragment = this.a;
                int i = BaseFragment.b;
                Objects.requireNonNull(baseFragment);
                ct.a("onBackPressWork begin======", new Object[0]);
                NavController navControllerFindNavController = FragmentKt.findNavController(baseFragment);
                NavDestination currentDestination = navControllerFindNavController.getCurrentDestination();
                boolean z = currentDestination != null && currentDestination.getId() == R.id.featureFragment;
                ArrayDeque<NavBackStackEntry> backQueue = navControllerFindNavController.getBackQueue();
                if (z) {
                    baseFragment.requireActivity().finish();
                    ct.a(xi.e("onBackPressWork ffTop size:", backQueue.size(), " finish end======"), new Object[0]);
                    return;
                }
                ct.a(xi.e("onBackPressWork rawForeachBegin rawQueueSize:", backQueue.size(), "===== "), new Object[0]);
                Iterator<NavBackStackEntry> it = backQueue.iterator();
                while (it.hasNext()) {
                    ct.a(e.d("onBackPressWork eachName:", it.next().getDestination().getDisplayName(), " "), new Object[0]);
                }
                ct.a("onBackPressWork rawForeachEnd=====", new Object[0]);
                HashSet hashSet = new HashSet();
                ArrayList arrayList = new ArrayList();
                for (NavBackStackEntry navBackStackEntry : backQueue) {
                    if (hashSet.add(navBackStackEntry.getDestination().getDisplayName())) {
                        arrayList.add(navBackStackEntry);
                    }
                }
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : arrayList) {
                    NavBackStackEntry navBackStackEntry2 = (NavBackStackEntry) obj;
                    if (!(navBackStackEntry2.getDestination().getId() == R.id.nav_graph_main || navBackStackEntry2.getDestination().getId() == R.id.nav_graph_panel)) {
                        arrayList2.add(obj);
                    }
                }
                ArrayList arrayList3 = new ArrayList();
                for (Object obj2 : arrayList2) {
                    if (!(((NavBackStackEntry) obj2).getDestination().getId() == R.id.startUpProtocolFragment)) {
                        arrayList3.add(obj2);
                    }
                }
                ct.a(xi.e("onBackPressWork fixFgQueueSize:", arrayList3.size(), " "), new Object[0]);
                if (arrayList3.size() <= 2) {
                    baseFragment.requireActivity().finish();
                    ct.a("onBackPressWork finish end======", new Object[0]);
                } else {
                    FragmentKt.findNavController(baseFragment).navigateUp();
                    ct.a("onBackPressWork popBackStack end======", new Object[0]);
                }
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public final View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ct.a(d.f("fg lifecycle onCreateView class:", getClass().getSimpleName()), new Object[0]);
        VB vb = (VB) b();
        this.a = vb;
        if (vb != null) {
            return vb.getRoot();
        }
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        ct.a(d.f("fg lifecycle onDestroyView class:", getClass().getSimpleName()), new Object[0]);
        this.a = null;
    }

    @Override // androidx.fragment.app.Fragment
    public final void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        ct.a(d.f("fg lifecycle onViewCreated class:", getClass().getSimpleName()), new Object[0]);
        d();
        av avVar = av.a;
        requireContext();
        boolean zE = avVar.e();
        if (zE) {
            int i = getResources().getConfiguration().orientation;
            ct.a("checkToUpdateRightRudderView rightRudder:" + zE + " orientation:" + i + " fragment:" + getClass().getSimpleName(), new Object[0]);
            if (i == 2) {
                f();
            } else if (i == 1) {
                g();
            }
        }
        c();
    }
}
