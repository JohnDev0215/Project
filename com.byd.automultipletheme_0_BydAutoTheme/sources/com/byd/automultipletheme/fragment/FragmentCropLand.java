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
import androidx.navigation.fragment.FragmentKt;
import com.blankj.utilcode.util.Utils;
import com.byd.automultipletheme.R;
import com.byd.automultipletheme.bean.CarouselPicture;
import com.byd.automultipletheme.common.AssetsUtils;
import com.byd.automultipletheme.common.AutoUtils;
import com.byd.automultipletheme.common.cropper.CropImageView;
import com.byd.automultipletheme.databinding.FragmentCropLandBinding;
import com.byd.automultipletheme.vm.CropViewModel;
import java.io.File;
import java.util.concurrent.ExecutionException;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: FragmentCropLand.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0002J\b\u0010\u0010\u001a\u00020\u000eH\u0002J\u0012\u0010\u0011\u001a\u00020\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J$\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u001a\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u00152\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u001c"}, d2 = {"Lcom/byd/automultipletheme/fragment/FragmentCropLand;", "Landroidx/fragment/app/Fragment;", "()V", "binding", "Lcom/byd/automultipletheme/databinding/FragmentCropLandBinding;", "editPath", "", "viewModel", "Lcom/byd/automultipletheme/vm/CropViewModel;", "getViewModel", "()Lcom/byd/automultipletheme/vm/CropViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "applyRudder", "", "initData", "initViews", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FragmentCropLand extends Fragment {
    private FragmentCropLandBinding binding;
    private String editPath;

    /* JADX INFO: renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    public FragmentCropLand() {
        final Function0<ViewModelStoreOwner> function0 = new Function0<ViewModelStoreOwner>() { // from class: com.byd.automultipletheme.fragment.FragmentCropLand$viewModel$2
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
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(CropViewModel.class), new Function0<ViewModelStore>() { // from class: com.byd.automultipletheme.fragment.FragmentCropLand$special$$inlined$viewModels$default$1
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
        onBackPressedDispatcher.addCallback(this, new OnBackPressedCallback() { // from class: com.byd.automultipletheme.fragment.FragmentCropLand.onCreate.1
            {
                super(true);
            }

            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                FragmentCropLand.this.getViewModel().isFinishActionLiveData().setValue(false);
                FragmentActivity activity2 = FragmentCropLand.this.getActivity();
                if (activity2 != null) {
                    activity2.setResult(1000, new Intent().putExtra("isCancel", FragmentCropLand.this.getViewModel().isCancelActionLiveData().getValue()));
                }
                FragmentActivity activity3 = FragmentCropLand.this.getActivity();
                if (activity3 != null) {
                    activity3.finish();
                }
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentCropLandBinding fragmentCropLandBindingInflate = FragmentCropLandBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentCropLandBindingInflate, "inflate(inflater, container, false)");
        this.binding = fragmentCropLandBindingInflate;
        applyRudder();
        FragmentCropLandBinding fragmentCropLandBinding = this.binding;
        if (fragmentCropLandBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentCropLandBinding = null;
        }
        ConstraintLayout root = fragmentCropLandBinding.getRoot();
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
        getViewModel().getCarouselPictureLiveData().observe(getViewLifecycleOwner(), new Observer() { // from class: com.byd.automultipletheme.fragment.FragmentCropLand$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentCropLand.m85initData$lambda0(this.f$0, (CarouselPicture) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initData$lambda-0, reason: not valid java name */
    public static final void m85initData$lambda0(FragmentCropLand this$0, CarouselPicture carouselPicture) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String landLight = carouselPicture.getLandLight();
        Intrinsics.checkNotNull(landLight);
        this$0.editPath = landLight;
        FragmentCropLandBinding fragmentCropLandBinding = this$0.binding;
        String str = null;
        if (fragmentCropLandBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentCropLandBinding = null;
        }
        CropImageView cropImageView = fragmentCropLandBinding.cropImageView;
        String str2 = this$0.editPath;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editPath");
        } else {
            str = str2;
        }
        cropImageView.setImageUriAsync(Uri.fromFile(new File(str)));
    }

    private final void initViews() {
        FragmentCropLandBinding fragmentCropLandBinding = null;
        if (AutoUtils.INSTANCE.getModelType() == 3) {
            FragmentCropLandBinding fragmentCropLandBinding2 = this.binding;
            if (fragmentCropLandBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCropLandBinding2 = null;
            }
            fragmentCropLandBinding2.cropCancel.setTextColor(getResources().getColor(R.color.d9DAE1EB));
            FragmentCropLandBinding fragmentCropLandBinding3 = this.binding;
            if (fragmentCropLandBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCropLandBinding3 = null;
            }
            fragmentCropLandBinding3.cropTitle.setTextColor(getResources().getColor(R.color.d9DAE1EB));
            FragmentCropLandBinding fragmentCropLandBinding4 = this.binding;
            if (fragmentCropLandBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCropLandBinding4 = null;
            }
            fragmentCropLandBinding4.cropAction.setTextColor(getResources().getColor(R.color.d9DAE1EB));
            FragmentCropLandBinding fragmentCropLandBinding5 = this.binding;
            if (fragmentCropLandBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCropLandBinding5 = null;
            }
            fragmentCropLandBinding5.getRoot().setBackground(ContextCompat.getDrawable(Utils.getApp(), R.drawable.img_main_bg_denza));
            FragmentCropLandBinding fragmentCropLandBinding6 = this.binding;
            if (fragmentCropLandBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCropLandBinding6 = null;
            }
            fragmentCropLandBinding6.cropAction.setText(getResources().getText(R.string.str_complete));
        }
        FragmentCropLandBinding fragmentCropLandBinding7 = this.binding;
        if (fragmentCropLandBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentCropLandBinding7 = null;
        }
        fragmentCropLandBinding7.cropImageView.setAspectRatio(16, 9);
        FragmentCropLandBinding fragmentCropLandBinding8 = this.binding;
        if (fragmentCropLandBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentCropLandBinding8 = null;
        }
        fragmentCropLandBinding8.cropAction.setOnClickListener(new View.OnClickListener() { // from class: com.byd.automultipletheme.fragment.FragmentCropLand$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentCropLand.m86initViews$lambda1(this.f$0, view);
            }
        });
        FragmentCropLandBinding fragmentCropLandBinding9 = this.binding;
        if (fragmentCropLandBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentCropLandBinding9 = null;
        }
        fragmentCropLandBinding9.cropCancel.setOnClickListener(new View.OnClickListener() { // from class: com.byd.automultipletheme.fragment.FragmentCropLand$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentCropLand.m87initViews$lambda2(this.f$0, view);
            }
        });
        FragmentCropLandBinding fragmentCropLandBinding10 = this.binding;
        if (fragmentCropLandBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentCropLandBinding = fragmentCropLandBinding10;
        }
        fragmentCropLandBinding.cropImageView.setOnCropImageCompleteListener(new CropImageView.OnCropImageCompleteListener() { // from class: com.byd.automultipletheme.fragment.FragmentCropLand$$ExternalSyntheticLambda3
            @Override // com.byd.automultipletheme.common.cropper.CropImageView.OnCropImageCompleteListener
            public final void onCropImageComplete(CropImageView cropImageView, CropImageView.CropResult cropResult) throws ExecutionException, InterruptedException {
                FragmentCropLand.m88initViews$lambda3(this.f$0, cropImageView, cropResult);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initViews$lambda-1, reason: not valid java name */
    public static final void m86initViews$lambda1(FragmentCropLand this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentCropLandBinding fragmentCropLandBinding = this$0.binding;
        if (fragmentCropLandBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentCropLandBinding = null;
        }
        fragmentCropLandBinding.cropImageView.getCroppedImageAsync();
        if (AutoUtils.INSTANCE.getModelType() == 3) {
            this$0.getViewModel().isCancelActionLiveData().setValue(false);
            FragmentActivity activity = this$0.getActivity();
            if (activity != null) {
                activity.setResult(1000, new Intent().putExtra("isFinish", true));
                return;
            }
            return;
        }
        FragmentKt.findNavController(this$0).navigate(R.id.fragmentCropPort);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initViews$lambda-2, reason: not valid java name */
    public static final void m87initViews$lambda2(FragmentCropLand this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().isFinishActionLiveData().setValue(false);
        FragmentActivity activity = this$0.getActivity();
        if (activity != null) {
            activity.setResult(1000, new Intent().putExtra("isCancel", this$0.getViewModel().isCancelActionLiveData().getValue()));
        }
        FragmentActivity activity2 = this$0.getActivity();
        if (activity2 != null) {
            activity2.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initViews$lambda-3, reason: not valid java name */
    public static final void m88initViews$lambda3(FragmentCropLand this$0, CropImageView cropImageView, CropImageView.CropResult cropResult) throws ExecutionException, InterruptedException {
        FragmentActivity activity;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AssetsUtils assetsUtils = AssetsUtils.INSTANCE;
        String str = this$0.editPath;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editPath");
            str = null;
        }
        String str2 = "/storage/emulated/0/Android/data/com.byd.automultipletheme/files/res/wallpaper/110000010/land/" + assetsUtils.getPathName(str);
        CropViewModel viewModel = this$0.getViewModel();
        Bitmap bitmap = cropResult.getBitmap();
        Intrinsics.checkNotNullExpressionValue(bitmap, "result.bitmap");
        viewModel.saveLandWallpaper(str2, bitmap);
        if (AutoUtils.INSTANCE.getModelType() != 3 || (activity = this$0.getActivity()) == null) {
            return;
        }
        activity.finish();
    }

    private final void applyRudder() {
        if (AutoUtils.INSTANCE.isRightRudder()) {
            ConstraintSet constraintSet = new ConstraintSet();
            FragmentCropLandBinding fragmentCropLandBinding = this.binding;
            FragmentCropLandBinding fragmentCropLandBinding2 = null;
            if (fragmentCropLandBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCropLandBinding = null;
            }
            constraintSet.clone(fragmentCropLandBinding.tabTopLayout);
            FragmentCropLandBinding fragmentCropLandBinding3 = this.binding;
            if (fragmentCropLandBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCropLandBinding3 = null;
            }
            constraintSet.clear(fragmentCropLandBinding3.cropCancel.getId(), 1);
            FragmentCropLandBinding fragmentCropLandBinding4 = this.binding;
            if (fragmentCropLandBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCropLandBinding4 = null;
            }
            constraintSet.connect(fragmentCropLandBinding4.cropCancel.getId(), 2, 0, 2, 66);
            FragmentCropLandBinding fragmentCropLandBinding5 = this.binding;
            if (fragmentCropLandBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCropLandBinding5 = null;
            }
            constraintSet.clear(fragmentCropLandBinding5.cropAction.getId(), 2);
            FragmentCropLandBinding fragmentCropLandBinding6 = this.binding;
            if (fragmentCropLandBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCropLandBinding6 = null;
            }
            constraintSet.connect(fragmentCropLandBinding6.cropAction.getId(), 1, 0, 1, 66);
            FragmentCropLandBinding fragmentCropLandBinding7 = this.binding;
            if (fragmentCropLandBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentCropLandBinding2 = fragmentCropLandBinding7;
            }
            constraintSet.applyTo(fragmentCropLandBinding2.tabTopLayout);
        }
    }
}
