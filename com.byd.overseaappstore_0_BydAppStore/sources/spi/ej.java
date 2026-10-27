package spi;

import android.os.Bundle;
import android.view.View;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;
import com.byd.overseaappstore.databinding.FragmentManagementBinding;
import com.byd.overseaappstore.ui.view.fragment.manage.ManagementFragment;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class ej implements View.OnClickListener {
    public final /* synthetic */ int a = 0;
    public final /* synthetic */ int b;
    public final /* synthetic */ Object c;

    public /* synthetic */ ej(int i, Bundle bundle) {
        this.b = i;
        this.c = bundle;
    }

    public /* synthetic */ ej(ManagementFragment managementFragment, int i) {
        this.c = managementFragment;
        this.b = i;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        ViewPager2 viewPager2;
        switch (this.a) {
            case 0:
                Navigation.m36createNavigateOnClickListener$lambda0(this.b, (Bundle) this.c, view);
                break;
            default:
                ManagementFragment this$0 = (ManagementFragment) this.c;
                int i = this.b;
                int i2 = ManagementFragment.l;
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                FragmentManagementBinding fragmentManagementBinding = (FragmentManagementBinding) this$0.a;
                if (fragmentManagementBinding != null && (viewPager2 = fragmentManagementBinding.e) != null) {
                    viewPager2.setCurrentItem(i, false);
                    break;
                }
                break;
        }
    }
}
