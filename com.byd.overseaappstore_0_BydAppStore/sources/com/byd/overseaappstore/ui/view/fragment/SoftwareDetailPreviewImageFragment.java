package com.byd.overseaappstore.ui.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.NavArgsLazy;
import androidx.viewbinding.ViewBinding;
import androidx.viewpager2.widget.ViewPager2;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.databinding.FragmentSoftwareDetailPreviewImageBinding;
import com.byd.overseaappstore.module.ability.data.upper.ImagePath;
import com.byd.overseaappstore.module.ability.data.upper.SoftwareDetailPreviewImages;
import com.byd.overseaappstore.ui.view.adapter.SoftwareDetailPreviewFragmentAdapter;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatImageView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinImageView;
import com.byd.overseaappstore.ui.vm.MainViewModel;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import spi.e;
import spi.y6;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/byd/overseaappstore/ui/view/fragment/SoftwareDetailPreviewImageFragment;", "Lcom/byd/overseaappstore/ui/view/base/BaseFragment;", "Lcom/byd/overseaappstore/databinding/FragmentSoftwareDetailPreviewImageBinding;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class SoftwareDetailPreviewImageFragment extends Hilt_SoftwareDetailPreviewImageFragment<FragmentSoftwareDetailPreviewImageBinding> {
    public static final /* synthetic */ int p = 0;

    @NotNull
    public final Lazy h;
    public int k;
    public int l;
    public int m;
    public int n;

    @NotNull
    public final NavArgsLazy i = new NavArgsLazy(Reflection.getOrCreateKotlinClass(SoftwareDetailPreviewImageFragmentArgs.class), new Function0<Bundle>() { // from class: com.byd.overseaappstore.ui.view.fragment.SoftwareDetailPreviewImageFragment$special$$inlined$navArgs$1
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final Bundle invoke() {
            Bundle arguments = this.getArguments();
            if (arguments != null) {
                return arguments;
            }
            StringBuilder sbK = e.k("Fragment ");
            sbK.append(this);
            sbK.append(" has null arguments");
            throw new IllegalStateException(sbK.toString());
        }
    });

    @NotNull
    public final SoftwareDetailPreviewFragmentAdapter j = new SoftwareDetailPreviewFragmentAdapter();

    @NotNull
    public final SoftwareDetailPreviewImageFragment$onPreviewImageChangeCallback$1 o = new ViewPager2.OnPageChangeCallback() { // from class: com.byd.overseaappstore.ui.view.fragment.SoftwareDetailPreviewImageFragment$onPreviewImageChangeCallback$1
        @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
        public final void onPageSelected(int i) {
            LinearLayout linearLayout;
            View childAt;
            LinearLayout linearLayout2;
            View childAt2;
            SoftwareDetailPreviewImageFragment softwareDetailPreviewImageFragment = this.a;
            if (softwareDetailPreviewImageFragment.m <= 1 || softwareDetailPreviewImageFragment.n == i) {
                return;
            }
            FragmentSoftwareDetailPreviewImageBinding fragmentSoftwareDetailPreviewImageBinding = (FragmentSoftwareDetailPreviewImageBinding) softwareDetailPreviewImageFragment.a;
            if (fragmentSoftwareDetailPreviewImageBinding != null && (linearLayout2 = fragmentSoftwareDetailPreviewImageBinding.c) != null && (childAt2 = linearLayout2.getChildAt(i)) != null) {
                childAt2.setBackgroundResource(R.drawable.point_image_checked);
            }
            FragmentSoftwareDetailPreviewImageBinding fragmentSoftwareDetailPreviewImageBinding2 = (FragmentSoftwareDetailPreviewImageBinding) softwareDetailPreviewImageFragment.a;
            if (fragmentSoftwareDetailPreviewImageBinding2 != null && (linearLayout = fragmentSoftwareDetailPreviewImageBinding2.c) != null && (childAt = linearLayout.getChildAt(softwareDetailPreviewImageFragment.n)) != null) {
                childAt.setBackgroundResource(R.drawable.point_image_normal);
            }
            softwareDetailPreviewImageFragment.n = i;
        }
    };

    /* JADX WARN: Type inference failed for: r0v5, types: [com.byd.overseaappstore.ui.view.fragment.SoftwareDetailPreviewImageFragment$onPreviewImageChangeCallback$1] */
    public SoftwareDetailPreviewImageFragment() {
        final Function0 function0 = null;
        this.h = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: com.byd.overseaappstore.ui.view.fragment.SoftwareDetailPreviewImageFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0<CreationExtras>() { // from class: com.byd.overseaappstore.ui.view.fragment.SoftwareDetailPreviewImageFragment$special$$inlined$activityViewModels$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function02 = function0;
                if (function02 != null && (creationExtras = (CreationExtras) function02.invoke()) != null) {
                    return creationExtras;
                }
                CreationExtras defaultViewModelCreationExtras = this.requireActivity().getDefaultViewModelCreationExtras();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "requireActivity().defaultViewModelCreationExtras");
                return defaultViewModelCreationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.byd.overseaappstore.ui.view.fragment.SoftwareDetailPreviewImageFragment$special$$inlined$activityViewModels$default$3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory = this.requireActivity().getDefaultViewModelProviderFactory();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "requireActivity().defaultViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        });
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final ViewBinding b() {
        FragmentSoftwareDetailPreviewImageBinding fragmentSoftwareDetailPreviewImageBindingA = FragmentSoftwareDetailPreviewImageBinding.a(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(fragmentSoftwareDetailPreviewImageBindingA, "inflate(layoutInflater)");
        return fragmentSoftwareDetailPreviewImageBindingA;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void c() {
        SkinAppCompatImageView skinAppCompatImageView;
        FragmentSoftwareDetailPreviewImageBinding fragmentSoftwareDetailPreviewImageBinding = (FragmentSoftwareDetailPreviewImageBinding) this.a;
        if (fragmentSoftwareDetailPreviewImageBinding == null || (skinAppCompatImageView = fragmentSoftwareDetailPreviewImageBinding.b) == null) {
            return;
        }
        skinAppCompatImageView.setOnClickListener(new y6(this, 7));
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void d() {
        ViewPager2 viewPager2;
        ViewPager2 viewPager22;
        List<ImagePath> previewImages;
        LinearLayout linearLayout;
        LinearLayout linearLayout2;
        SoftwareDetailPreviewImages softwareDetailPreviewImages = i().imagesPath;
        if (softwareDetailPreviewImages != null && (previewImages = softwareDetailPreviewImages.getPreviewImages()) != null) {
            int size = previewImages.size();
            FragmentSoftwareDetailPreviewImageBinding fragmentSoftwareDetailPreviewImageBinding = (FragmentSoftwareDetailPreviewImageBinding) this.a;
            if (fragmentSoftwareDetailPreviewImageBinding != null && (linearLayout2 = fragmentSoftwareDetailPreviewImageBinding.c) != null) {
                linearLayout2.removeAllViews();
            }
            this.m = size;
            this.n = i().imagePosition;
            this.k = requireContext().getResources().getDimensionPixelSize(R.dimen.dp_4);
            this.l = requireContext().getResources().getDimensionPixelSize(R.dimen.dp_24);
            int i = this.m;
            if (i > 1) {
                int i2 = 0;
                while (i2 < i) {
                    View skinImageView = new SkinImageView(requireContext());
                    skinImageView.setBackgroundResource(i2 == i().imagePosition ? R.drawable.point_image_checked : R.drawable.point_image_normal);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    int i3 = this.k;
                    int i4 = this.l;
                    layoutParams.setMargins(i3, i4, i3, i4);
                    skinImageView.setLayoutParams(layoutParams);
                    FragmentSoftwareDetailPreviewImageBinding fragmentSoftwareDetailPreviewImageBinding2 = (FragmentSoftwareDetailPreviewImageBinding) this.a;
                    if (fragmentSoftwareDetailPreviewImageBinding2 != null && (linearLayout = fragmentSoftwareDetailPreviewImageBinding2.c) != null) {
                        linearLayout.addView(skinImageView);
                    }
                    i2++;
                }
            }
        }
        FragmentSoftwareDetailPreviewImageBinding fragmentSoftwareDetailPreviewImageBinding3 = (FragmentSoftwareDetailPreviewImageBinding) this.a;
        if (fragmentSoftwareDetailPreviewImageBinding3 != null && (viewPager22 = fragmentSoftwareDetailPreviewImageBinding3.e) != null) {
            viewPager22.setAdapter(this.j);
            viewPager22.unregisterOnPageChangeCallback(this.o);
            viewPager22.registerOnPageChangeCallback(this.o);
        }
        FragmentSoftwareDetailPreviewImageBinding fragmentSoftwareDetailPreviewImageBinding4 = (FragmentSoftwareDetailPreviewImageBinding) this.a;
        if (fragmentSoftwareDetailPreviewImageBinding4 == null || (viewPager2 = fragmentSoftwareDetailPreviewImageBinding4.e) == null) {
            return;
        }
        viewPager2.setCurrentItem(i().imagePosition, false);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void e() {
        SoftwareDetailPreviewFragmentAdapter softwareDetailPreviewFragmentAdapter = this.j;
        SoftwareDetailPreviewImages softwareDetailPreviewImages = i().imagesPath;
        softwareDetailPreviewFragmentAdapter.submitList(softwareDetailPreviewImages != null ? softwareDetailPreviewImages.getPreviewImages() : null);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void f() {
        ConstraintLayout constraintLayout;
        FragmentSoftwareDetailPreviewImageBinding fragmentSoftwareDetailPreviewImageBinding;
        SkinAppCompatImageView skinAppCompatImageView;
        VB vb = this.a;
        FragmentSoftwareDetailPreviewImageBinding fragmentSoftwareDetailPreviewImageBinding2 = (FragmentSoftwareDetailPreviewImageBinding) vb;
        if (fragmentSoftwareDetailPreviewImageBinding2 == null || (constraintLayout = fragmentSoftwareDetailPreviewImageBinding2.d) == null || (fragmentSoftwareDetailPreviewImageBinding = (FragmentSoftwareDetailPreviewImageBinding) vb) == null || (skinAppCompatImageView = fragmentSoftwareDetailPreviewImageBinding.b) == null) {
            return;
        }
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.clear(skinAppCompatImageView.getId(), 1);
        constraintSet.connect(skinAppCompatImageView.getId(), 2, 0, 2, getResources().getDimensionPixelSize(R.dimen.dp_20));
        constraintSet.applyTo(constraintLayout);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void g() {
        ConstraintLayout constraintLayout;
        FragmentSoftwareDetailPreviewImageBinding fragmentSoftwareDetailPreviewImageBinding;
        SkinAppCompatImageView skinAppCompatImageView;
        VB vb = this.a;
        FragmentSoftwareDetailPreviewImageBinding fragmentSoftwareDetailPreviewImageBinding2 = (FragmentSoftwareDetailPreviewImageBinding) vb;
        if (fragmentSoftwareDetailPreviewImageBinding2 == null || (constraintLayout = fragmentSoftwareDetailPreviewImageBinding2.d) == null || (fragmentSoftwareDetailPreviewImageBinding = (FragmentSoftwareDetailPreviewImageBinding) vb) == null || (skinAppCompatImageView = fragmentSoftwareDetailPreviewImageBinding.b) == null) {
            return;
        }
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.clear(skinAppCompatImageView.getId(), 6);
        constraintSet.connect(skinAppCompatImageView.getId(), 7, 0, 7, getResources().getDimensionPixelSize(R.dimen.dp_30));
        constraintSet.applyTo(constraintLayout);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final SoftwareDetailPreviewImageFragmentArgs i() {
        return (SoftwareDetailPreviewImageFragmentArgs) this.i.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public final void onResume() {
        super.onResume();
        MainViewModel mainViewModel = (MainViewModel) this.h.getValue();
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        mainViewModel.b(contextRequireContext);
    }
}
