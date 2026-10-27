package spi;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCallerKt;
import androidx.constraintlayout.core.state.Interpolator;
import androidx.constraintlayout.core.state.Transition;
import androidx.core.view.GravityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.viewpager2.widget.ViewPager2;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.databinding.FragmentManagementBinding;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatImageView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatTextView;
import com.byd.overseaappstore.ui.view.fragment.ProtocolFragment;
import com.byd.overseaappstore.ui.view.fragment.manage.ManagementFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class l implements ActivityResultCallback, Interpolator, NestedScrollView.OnScrollChangeListener, TabLayoutMediator.TabConfigurationStrategy {
    public final /* synthetic */ Object a;

    public /* synthetic */ l(Object obj) {
        this.a = obj;
    }

    @Override // androidx.constraintlayout.core.state.Interpolator
    public final float getInterpolation(float f) {
        return Transition.lambda$getInterpolator$0((String) this.a, f);
    }

    @Override // androidx.activity.result.ActivityResultCallback
    public final void onActivityResult(Object obj) {
        ActivityResultCallerKt.m4registerForActivityResult$lambda1((Function1) this.a, obj);
    }

    @Override // com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
    public final void onConfigureTab(TabLayout.Tab tab, final int i) {
        TabLayout.TabView tabView;
        View.OnClickListener ejVar;
        final ManagementFragment this$0 = (ManagementFragment) this.a;
        int i2 = ManagementFragment.l;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(tab, "tab");
        if (i != 0) {
            tab.setCustomView(R.layout.item_management_title);
            TabLayout.TabView tabView2 = tab.view;
            if (i != 1) {
                tabView2.setGravity(GravityCompat.END);
                ImageView imageView = (ImageView) tab.view.findViewById(R.id.iv_management_title_icon);
                if (imageView != null) {
                    imageView.setImageResource(R.drawable.ic_uninstall_unselected);
                }
                TextView textView = (TextView) tab.view.findViewById(R.id.tv_management_title_text);
                if (textView != null) {
                    textView.setTextAppearance(R.style.ManagementTabTextUnSelected);
                }
                SkinAppCompatTextView skinAppCompatTextView = (SkinAppCompatTextView) tab.view.findViewById(R.id.tv_management_title_text);
                if (skinAppCompatTextView != null) {
                    skinAppCompatTextView.setStyleTextColor(R.color.management_tab_unselected);
                }
                TextView textView2 = (TextView) tab.view.findViewById(R.id.tv_management_title_text);
                if (textView2 != null) {
                    textView2.setText(this$0.getString(R.string.management_fragment_uninstall_list));
                }
                tabView = tab.view;
                ejVar = new View.OnClickListener() { // from class: spi.wh
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ViewPager2 viewPager2;
                        ManagementFragment this$02 = this$0;
                        int i3 = i;
                        int i4 = ManagementFragment.l;
                        Intrinsics.checkNotNullParameter(this$02, "this$0");
                        FragmentManagementBinding fragmentManagementBinding = (FragmentManagementBinding) this$02.a;
                        if (fragmentManagementBinding == null || (viewPager2 = fragmentManagementBinding.e) == null) {
                            return;
                        }
                        viewPager2.setCurrentItem(i3, false);
                    }
                };
            } else {
                ImageView imageView2 = (ImageView) tabView2.findViewById(R.id.iv_management_title_icon);
                if (imageView2 != null) {
                    imageView2.setImageResource(R.drawable.ic_update_unselected);
                }
                TextView textView3 = (TextView) tab.view.findViewById(R.id.tv_management_title_text);
                if (textView3 != null) {
                    textView3.setTextAppearance(R.style.ManagementTabTextUnSelected);
                }
                SkinAppCompatTextView skinAppCompatTextView2 = (SkinAppCompatTextView) tab.view.findViewById(R.id.tv_management_title_text);
                if (skinAppCompatTextView2 != null) {
                    skinAppCompatTextView2.setStyleTextColor(R.color.management_tab_unselected);
                }
                TextView textView4 = (TextView) tab.view.findViewById(R.id.tv_management_title_text);
                if (textView4 != null) {
                    textView4.setText(this$0.getString(R.string.management_fragment_update_list));
                }
                tabView = tab.view;
                ejVar = new ej(this$0, i);
            }
        } else {
            tab.setCustomView(R.layout.item_management_title);
            tab.view.setGravity(GravityCompat.START);
            ImageView imageView3 = (ImageView) tab.view.findViewById(R.id.iv_management_title_icon);
            if (imageView3 != null) {
                imageView3.setImageResource(R.drawable.ic_download_selected);
            }
            TextView textView5 = (TextView) tab.view.findViewById(R.id.tv_management_title_text);
            if (textView5 != null) {
                textView5.setTextAppearance(R.style.ManagementTabTextSelected);
            }
            SkinAppCompatTextView skinAppCompatTextView3 = (SkinAppCompatTextView) tab.view.findViewById(R.id.tv_management_title_text);
            if (skinAppCompatTextView3 != null) {
                skinAppCompatTextView3.setStyleTextColor(R.color.management_tab_selected);
            }
            SkinAppCompatImageView skinAppCompatImageView = (SkinAppCompatImageView) tab.view.findViewById(R.id.iv_management_title_indicator);
            if (skinAppCompatImageView != null) {
                skinAppCompatImageView.setVisibility(0);
            }
            TextView textView6 = (TextView) tab.view.findViewById(R.id.tv_management_title_text);
            if (textView6 != null) {
                textView6.setText(this$0.getString(R.string.management_fragment_download_list));
            }
            tabView = tab.view;
            ejVar = new View.OnClickListener() { // from class: spi.vh
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ViewPager2 viewPager2;
                    ManagementFragment this$02 = this$0;
                    int i3 = i;
                    int i4 = ManagementFragment.l;
                    Intrinsics.checkNotNullParameter(this$02, "this$0");
                    FragmentManagementBinding fragmentManagementBinding = (FragmentManagementBinding) this$02.a;
                    if (fragmentManagementBinding == null || (viewPager2 = fragmentManagementBinding.e) == null) {
                        return;
                    }
                    viewPager2.setCurrentItem(i3, false);
                }
            };
        }
        tabView.setOnClickListener(ejVar);
    }

    @Override // androidx.core.widget.NestedScrollView.OnScrollChangeListener
    public final void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
        ProtocolFragment this$0 = (ProtocolFragment) this.a;
        int i5 = ProtocolFragment.n;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(nestedScrollView, "nestedScrollView");
        this$0.j = i;
        this$0.k = i2;
    }
}
