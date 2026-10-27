package defpackage;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.view.ViewTreeObserver;
import com.google.android.material.internal.NavigationMenuView;
import com.google.android.material.navigation.NavigationView;

/* JADX INFO: compiled from: NavigationView.java */
/* JADX INFO: loaded from: classes.dex */
public final class kl1 implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ NavigationView e;

    public kl1(NavigationView navigationView) {
        this.e = navigationView;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        Activity activity;
        NavigationView navigationView = this.e;
        navigationView.getLocationOnScreen(navigationView.m);
        boolean z = navigationView.m[1] == 0;
        cl1 cl1Var = navigationView.k;
        if (cl1Var.z != z) {
            cl1Var.z = z;
            int i = (cl1Var.f.getChildCount() == 0 && cl1Var.z) ? cl1Var.B : 0;
            NavigationMenuView navigationMenuView = cl1Var.e;
            navigationMenuView.setPadding(0, i, 0, navigationMenuView.getPaddingBottom());
        }
        navigationView.setDrawTopInsetForeground(z && navigationView.p);
        Context context = navigationView.getContext();
        while (true) {
            if (!(context instanceof ContextWrapper)) {
                activity = null;
                break;
            } else {
                if (context instanceof Activity) {
                    activity = (Activity) context;
                    break;
                }
                context = ((ContextWrapper) context).getBaseContext();
            }
        }
        if (activity != null) {
            navigationView.setDrawBottomInsetForeground((activity.findViewById(R.id.content).getHeight() == navigationView.getHeight()) && (Color.alpha(activity.getWindow().getNavigationBarColor()) != 0) && navigationView.q);
        }
    }
}
