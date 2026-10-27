package com.byd.automultipletheme.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import com.blankj.utilcode.util.BarUtils;
import com.byd.automultipletheme.R;
import com.byd.automultipletheme.bean.CarouselPicture;
import com.byd.automultipletheme.common.AutoUtils;
import com.byd.automultipletheme.common.Const;
import com.byd.automultipletheme.databinding.ActivityCropBinding;
import com.byd.automultipletheme.vm.CropViewModel;
import java.io.Serializable;
import java.util.Objects;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: CropActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u000f"}, d2 = {"Lcom/byd/automultipletheme/activity/CropActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/byd/automultipletheme/databinding/ActivityCropBinding;", "viewModel", "Lcom/byd/automultipletheme/vm/CropViewModel;", "getViewModel", "()Lcom/byd/automultipletheme/vm/CropViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CropActivity extends AppCompatActivity {
    private ActivityCropBinding binding;

    /* JADX INFO: renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    public CropActivity() {
        final CropActivity cropActivity = this;
        this.viewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(CropViewModel.class), new Function0<ViewModelStore>() { // from class: com.byd.automultipletheme.activity.CropActivity$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = cropActivity.getViewModelStore();
                Intrinsics.checkExpressionValueIsNotNull(viewModelStore, "viewModelStore");
                return viewModelStore;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.byd.automultipletheme.activity.CropActivity$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory = cropActivity.getDefaultViewModelProviderFactory();
                Intrinsics.checkExpressionValueIsNotNull(defaultViewModelProviderFactory, "defaultViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        });
    }

    private final CropViewModel getViewModel() {
        return (CropViewModel) this.viewModel.getValue();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCropBinding activityCropBindingInflate = ActivityCropBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityCropBindingInflate, "inflate(layoutInflater)");
        this.binding = activityCropBindingInflate;
        ActivityCropBinding activityCropBinding = null;
        if (activityCropBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCropBindingInflate = null;
        }
        setContentView(activityCropBindingInflate.getRoot());
        if (AutoUtils.INSTANCE.getModelType() == 3) {
            CropActivity cropActivity = this;
            BarUtils.setStatusBarColor(this, ContextCompat.getColor(cropActivity, R.color.backgroundColorDenza));
            ActivityCropBinding activityCropBinding2 = this.binding;
            if (activityCropBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityCropBinding2 = null;
            }
            activityCropBinding2.getRoot().setBackground(ContextCompat.getDrawable(cropActivity, R.drawable.img_main_bg_denza));
        } else {
            BarUtils.setStatusBarColor(this, ContextCompat.getColor(this, R.color.statusBarColor));
        }
        ActivityCropBinding activityCropBinding3 = this.binding;
        if (activityCropBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityCropBinding = activityCropBinding3;
        }
        BarUtils.addMarginTopEqualStatusBarHeight(activityCropBinding.getRoot());
        Serializable serializableExtra = getIntent().getSerializableExtra(Const.CAROUSEL_CROP);
        CropViewModel viewModel = getViewModel();
        Objects.requireNonNull(serializableExtra, "null cannot be cast to non-null type com.byd.automultipletheme.bean.CarouselPicture");
        viewModel.setCarouselPictureLiveData((CarouselPicture) serializableExtra);
    }
}
