package com.byd.automultipletheme.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.Navigation;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.byd.automultipletheme.R;
import com.byd.automultipletheme.bean.CarouselPicture;
import com.byd.automultipletheme.common.AssetsUtils;
import com.byd.automultipletheme.common.AutoUtils;
import com.byd.automultipletheme.common.cropper.CropImageView;
import com.byd.automultipletheme.databinding.FragmentCropPortBinding;
import com.byd.automultipletheme.vm.CropViewModel;
import java.io.File;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: FragmentCropPort.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0002J\b\u0010\u0010\u001a\u00020\u000eH\u0002J\u0012\u0010\u0011\u001a\u00020\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J$\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u001a\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u00152\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u001c"}, d2 = {"Lcom/byd/automultipletheme/fragment/FragmentCropPort;", "Landroidx/fragment/app/Fragment;", "()V", "binding", "Lcom/byd/automultipletheme/databinding/FragmentCropPortBinding;", "editPath", "", "viewModel", "Lcom/byd/automultipletheme/vm/CropViewModel;", "getViewModel", "()Lcom/byd/automultipletheme/vm/CropViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "applyRudder", "", "initData", "initViews", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FragmentCropPort extends Fragment {
    private FragmentCropPortBinding binding;
    private String editPath;

    /* JADX INFO: renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    public FragmentCropPort() {
        final Function0<ViewModelStoreOwner> function0 = new Function0<ViewModelStoreOwner>() { // from class: com.byd.automultipletheme.fragment.FragmentCropPort$viewModel$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                FragmentActivity fragmentActivityRequireActivity = this.this$0.requireActivity();
                Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
                return fragmentActivityRequireActivity;
            }
        };
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(CropViewModel.class), new Function0<ViewModelStore>() { // from class: com.byd.automultipletheme.fragment.FragmentCropPort$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function0.invoke()).getViewModelStore();
                Intrinsics.checkExpressionValueIsNotNull(viewModelStore, "ownerProducer().viewModelStore");
                return viewModelStore;
            }
        }, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CropViewModel getViewModel() {
        return (CropViewModel) this.viewModel.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        OnBackPressedDispatcher onBackPressedDispatcher;
        super.onCreate(savedInstanceState);
        FragmentActivity activity = getActivity();
        if (activity == null || (onBackPressedDispatcher = activity.getOnBackPressedDispatcher()) == null) {
            return;
        }
        onBackPressedDispatcher.addCallback(this, new OnBackPressedCallback() { // from class: com.byd.automultipletheme.fragment.FragmentCropPort.onCreate.1
            {
                super(true);
            }

            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                FragmentCropPort.this.getViewModel().isFinishActionLiveData().setValue(false);
                FragmentActivity activity2 = FragmentCropPort.this.getActivity();
                if (activity2 != null) {
                    activity2.setResult(1000, new Intent().putExtra("isCancel", FragmentCropPort.this.getViewModel().isCancelActionLiveData().getValue()));
                }
                FragmentActivity activity3 = FragmentCropPort.this.getActivity();
                if (activity3 != null) {
                    activity3.finish();
                }
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentCropPortBinding fragmentCropPortBindingInflate = FragmentCropPortBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentCropPortBindingInflate, "inflate(inflater, container, false)");
        this.binding = fragmentCropPortBindingInflate;
        applyRudder();
        FragmentCropPortBinding fragmentCropPortBinding = this.binding;
        if (fragmentCropPortBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentCropPortBinding = null;
        }
        ConstraintLayout root = fragmentCropPortBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initData();
    }

    private final void initData() {
        getViewModel().getCarouselPictureLiveData().observe(getViewLifecycleOwner(), new Observer() { // from class: com.byd.automultipletheme.fragment.FragmentCropPort$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentCropPort.m90initData$lambda0(this.f$0, (CarouselPicture) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initData$lambda-0, reason: not valid java name */
    public static final void m90initData$lambda0(FragmentCropPort this$0, CarouselPicture carouselPicture) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String portLight = carouselPicture.getPortLight();
        Intrinsics.checkNotNull(portLight);
        this$0.editPath = portLight;
        FragmentCropPortBinding fragmentCropPortBinding = this$0.binding;
        String str = null;
        if (fragmentCropPortBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentCropPortBinding = null;
        }
        CropImageView cropImageView = fragmentCropPortBinding.cropImageView;
        String str2 = this$0.editPath;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editPath");
        } else {
            str = str2;
        }
        cropImageView.setImageUriAsync(Uri.fromFile(new File(str)));
    }

    private final void initViews() {
        FragmentCropPortBinding fragmentCropPortBinding = null;
        if (AutoUtils.INSTANCE.getModelType() == 3) {
            FragmentCropPortBinding fragmentCropPortBinding2 = this.binding;
            if (fragmentCropPortBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCropPortBinding2 = null;
            }
            fragmentCropPortBinding2.cropOver.setTextColor(getResources().getColor(R.color.d9DAE1EB));
            FragmentCropPortBinding fragmentCropPortBinding3 = this.binding;
            if (fragmentCropPortBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCropPortBinding3 = null;
            }
            fragmentCropPortBinding3.cropTitle.setTextColor(getResources().getColor(R.color.d9DAE1EB));
            FragmentCropPortBinding fragmentCropPortBinding4 = this.binding;
            if (fragmentCropPortBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCropPortBinding4 = null;
            }
            fragmentCropPortBinding4.cropAction.setTextColor(getResources().getColor(R.color.d9DAE1EB));
            FragmentCropPortBinding fragmentCropPortBinding5 = this.binding;
            if (fragmentCropPortBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCropPortBinding5 = null;
            }
            fragmentCropPortBinding5.getRoot().setBackground(ContextCompat.getDrawable(Utils.getApp(), R.drawable.img_main_bg_denza));
        }
        FragmentCropPortBinding fragmentCropPortBinding6 = this.binding;
        if (fragmentCropPortBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentCropPortBinding6 = null;
        }
        fragmentCropPortBinding6.cropImageView.setAspectRatio(9, 16);
        FragmentCropPortBinding fragmentCropPortBinding7 = this.binding;
        if (fragmentCropPortBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentCropPortBinding7 = null;
        }
        fragmentCropPortBinding7.cropAction.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_fragmentCropPort_to_fragmentCropLand));
        FragmentCropPortBinding fragmentCropPortBinding8 = this.binding;
        if (fragmentCropPortBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentCropPortBinding = fragmentCropPortBinding8;
        }
        fragmentCropPortBinding.cropOver.setOnClickListener(new View.OnClickListener() { // from class: com.byd.automultipletheme.fragment.FragmentCropPort$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentCropPort.m91initViews$lambda1(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initViews$lambda-1, reason: not valid java name */
    public static final void m91initViews$lambda1(FragmentCropPort this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().isCancelActionLiveData().setValue(false);
        AssetsUtils assetsUtils = AssetsUtils.INSTANCE;
        String str = this$0.editPath;
        FragmentCropPortBinding fragmentCropPortBinding = null;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editPath");
            str = null;
        }
        String str2 = "/storage/emulated/0/Android/data/com.byd.automultipletheme/files/res/wallpaper/110000010/port/" + assetsUtils.getPathName(str);
        LogUtils.i("FragmentCropPort", str2);
        try {
            CropViewModel viewModel = this$0.getViewModel();
            FragmentCropPortBinding fragmentCropPortBinding2 = this$0.binding;
            if (fragmentCropPortBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCropPortBinding2 = null;
            }
            Bitmap croppedImage = fragmentCropPortBinding2.cropImageView.getCroppedImage();
            Intrinsics.checkNotNullExpressionValue(croppedImage, "binding.cropImageView.croppedImage");
            viewModel.saveLandWallpaper(str2, croppedImage);
            FragmentCropPortBinding fragmentCropPortBinding3 = this$0.binding;
            if (fragmentCropPortBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentCropPortBinding = fragmentCropPortBinding3;
            }
            fragmentCropPortBinding.progressBar.setVisibility(0);
            FragmentActivity activity = this$0.getActivity();
            if (activity != null) {
                activity.setResult(1000, new Intent().putExtra("isFinish", this$0.getViewModel().isFinishActionLiveData().getValue()));
            }
            FragmentActivity activity2 = this$0.getActivity();
            if (activity2 != null) {
                activity2.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final void applyRudder() {
        if (AutoUtils.INSTANCE.isRightRudder()) {
            ConstraintSet constraintSet = new ConstraintSet();
            FragmentCropPortBinding fragmentCropPortBinding = this.binding;
            FragmentCropPortBinding fragmentCropPortBinding2 = null;
            if (fragmentCropPortBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCropPortBinding = null;
            }
            constraintSet.clone(fragmentCropPortBinding.tabTopLayout);
            FragmentCropPortBinding fragmentCropPortBinding3 = this.binding;
            if (fragmentCropPortBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCropPortBinding3 = null;
            }
            constraintSet.clear(fragmentCropPortBinding3.cropOver.getId(), 2);
            FragmentCropPortBinding fragmentCropPortBinding4 = this.binding;
            if (fragmentCropPortBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCropPortBinding4 = null;
            }
            constraintSet.connect(fragmentCropPortBinding4.cropOver.getId(), 1, 0, 1, 66);
            FragmentCropPortBinding fragmentCropPortBinding5 = this.binding;
            if (fragmentCropPortBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCropPortBinding5 = null;
            }
            constraintSet.clear(fragmentCropPortBinding5.cropAction.getId(), 1);
            FragmentCropPortBinding fragmentCropPortBinding6 = this.binding;
            if (fragmentCropPortBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCropPortBinding6 = null;
            }
            constraintSet.connect(fragmentCropPortBinding6.cropAction.getId(), 2, 0, 2, 66);
            FragmentCropPortBinding fragmentCropPortBinding7 = this.binding;
            if (fragmentCropPortBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentCropPortBinding2 = fragmentCropPortBinding7;
            }
            constraintSet.applyTo(fragmentCropPortBinding2.tabTopLayout);
        }
    }
}
