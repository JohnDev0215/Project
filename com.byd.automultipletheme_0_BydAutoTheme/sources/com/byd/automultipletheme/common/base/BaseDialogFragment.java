package com.byd.automultipletheme.common.base;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.Utils;
import com.byd.automultipletheme.R;
import com.byd.automultipletheme.bean.AutoWallpaper;
import com.byd.automultipletheme.common.AutoUtils;
import com.byd.automultipletheme.common.LogUtils;
import com.byd.automultipletheme.databinding.FragmentBaseDialogBinding;
import com.byd.automultipletheme.fragment.ApplyingWindowFragment;
import com.byd.automultipletheme.vm.CarouselAddViewModel;
import java.io.Serializable;
import java.util.Objects;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: BaseDialogFragment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0007H\u0002J\b\u0010\u0012\u001a\u00020\u0007H\u0002J\b\u0010\u0013\u001a\u00020\u0007H\u0002J$\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u0007H\u0016J\u001a\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u00152\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/byd/automultipletheme/common/base/BaseDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "()V", "binding", "Lcom/byd/automultipletheme/databinding/FragmentBaseDialogBinding;", "cancelCallback", "Lkotlin/Function0;", "", "okCallback", "viewModel", "Lcom/byd/automultipletheme/vm/CarouselAddViewModel;", "getViewModel", "()Lcom/byd/automultipletheme/vm/CarouselAddViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "wallpaperType", "Lcom/byd/automultipletheme/bean/AutoWallpaper;", "applyRudder", "initArguments", "initViews", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onStart", "onViewCreated", "view", "Companion", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BaseDialogFragment extends DialogFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "BaseDialogFragment";
    private static final String cancel_txt_tag = "cancel_txt_tag";
    private static final String content_tag = "content_tag";
    private static final String ok_txt_tag = "ok_txt_tag";
    private static final String rsType_tag = "rsType_tag";
    private static final String title_tag = "title_tag";
    private FragmentBaseDialogBinding binding;
    private Function0<Unit> cancelCallback;
    private Function0<Unit> okCallback;

    /* JADX INFO: renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;
    private AutoWallpaper wallpaperType;

    public BaseDialogFragment() {
        final Function0<ViewModelStoreOwner> function0 = new Function0<ViewModelStoreOwner>() { // from class: com.byd.automultipletheme.common.base.BaseDialogFragment$viewModel$2
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
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(CarouselAddViewModel.class), new Function0<ViewModelStore>() { // from class: com.byd.automultipletheme.common.base.BaseDialogFragment$special$$inlined$viewModels$default$1
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

    private final CarouselAddViewModel getViewModel() {
        return (CarouselAddViewModel) this.viewModel.getValue();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (window != null) {
                Intrinsics.checkNotNullExpressionValue(window, "window");
                window.setLayout(getResources().getDimensionPixelSize(R.dimen.dp_560), getResources().getDimensionPixelSize(R.dimen.dp_264));
                if (AutoUtils.INSTANCE.getModelType() == 3) {
                    window.setBackgroundDrawableResource(R.drawable.ic_dialog_bg_denza);
                } else {
                    window.setBackgroundDrawableResource(R.drawable.ic_dialog_bg);
                }
            }
            dialog.setCanceledOnTouchOutside(false);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentBaseDialogBinding fragmentBaseDialogBindingInflate = FragmentBaseDialogBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentBaseDialogBindingInflate, "inflate(inflater, container, false)");
        this.binding = fragmentBaseDialogBindingInflate;
        applyRudder();
        FragmentBaseDialogBinding fragmentBaseDialogBinding = this.binding;
        if (fragmentBaseDialogBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentBaseDialogBinding = null;
        }
        ConstraintLayout root = fragmentBaseDialogBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initArguments();
        initViews();
    }

    private final void initViews() {
        FragmentBaseDialogBinding fragmentBaseDialogBinding = null;
        if (AutoUtils.INSTANCE.getModelType() == 3) {
            FragmentBaseDialogBinding fragmentBaseDialogBinding2 = this.binding;
            if (fragmentBaseDialogBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentBaseDialogBinding2 = null;
            }
            fragmentBaseDialogBinding2.dialogOk.setBackground(ContextCompat.getDrawable(Utils.getApp(), R.drawable.ic_dialog_btn_ok_denza));
            FragmentBaseDialogBinding fragmentBaseDialogBinding3 = this.binding;
            if (fragmentBaseDialogBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentBaseDialogBinding3 = null;
            }
            fragmentBaseDialogBinding3.dialogOk.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.btnDialogTextColor_denza));
            FragmentBaseDialogBinding fragmentBaseDialogBinding4 = this.binding;
            if (fragmentBaseDialogBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentBaseDialogBinding4 = null;
            }
            fragmentBaseDialogBinding4.dialogCancel.setBackground(ContextCompat.getDrawable(Utils.getApp(), R.drawable.ic_dialog_btn_cancel_denza));
            FragmentBaseDialogBinding fragmentBaseDialogBinding5 = this.binding;
            if (fragmentBaseDialogBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentBaseDialogBinding5 = null;
            }
            fragmentBaseDialogBinding5.dialogCancel.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.btnDialogTextColor_denza));
            FragmentBaseDialogBinding fragmentBaseDialogBinding6 = this.binding;
            if (fragmentBaseDialogBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentBaseDialogBinding6 = null;
            }
            fragmentBaseDialogBinding6.dialogContent.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.btnDialogTextColor_denza));
        }
        FragmentBaseDialogBinding fragmentBaseDialogBinding7 = this.binding;
        if (fragmentBaseDialogBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentBaseDialogBinding7 = null;
        }
        fragmentBaseDialogBinding7.dialogTitle.setText(getString(R.string.str_safety_dialog_title));
        FragmentBaseDialogBinding fragmentBaseDialogBinding8 = this.binding;
        if (fragmentBaseDialogBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentBaseDialogBinding8 = null;
        }
        fragmentBaseDialogBinding8.dialogContent.setText(getString(R.string.str_dialog_tips));
        FragmentBaseDialogBinding fragmentBaseDialogBinding9 = this.binding;
        if (fragmentBaseDialogBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentBaseDialogBinding9 = null;
        }
        fragmentBaseDialogBinding9.dialogOk.setText(getString(R.string.str_dialog_tips_ok));
        FragmentBaseDialogBinding fragmentBaseDialogBinding10 = this.binding;
        if (fragmentBaseDialogBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentBaseDialogBinding10 = null;
        }
        fragmentBaseDialogBinding10.dialogCancel.setText(getString(R.string.str_dialog_tips_cancel));
        FragmentBaseDialogBinding fragmentBaseDialogBinding11 = this.binding;
        if (fragmentBaseDialogBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentBaseDialogBinding11 = null;
        }
        fragmentBaseDialogBinding11.dialogContent.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.byd.automultipletheme.common.base.BaseDialogFragment.initViews.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                FragmentBaseDialogBinding fragmentBaseDialogBinding12 = BaseDialogFragment.this.binding;
                FragmentBaseDialogBinding fragmentBaseDialogBinding13 = null;
                if (fragmentBaseDialogBinding12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentBaseDialogBinding12 = null;
                }
                fragmentBaseDialogBinding12.dialogContent.getViewTreeObserver().removeOnPreDrawListener(this);
                LogUtils logUtils = LogUtils.INSTANCE;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("TextView 行数：");
                FragmentBaseDialogBinding fragmentBaseDialogBinding14 = BaseDialogFragment.this.binding;
                if (fragmentBaseDialogBinding14 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentBaseDialogBinding14 = null;
                }
                sb.append(fragmentBaseDialogBinding14.dialogContent.getLineCount());
                objArr[0] = sb.toString();
                logUtils.e(objArr);
                FragmentBaseDialogBinding fragmentBaseDialogBinding15 = BaseDialogFragment.this.binding;
                if (fragmentBaseDialogBinding15 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentBaseDialogBinding15 = null;
                }
                if (fragmentBaseDialogBinding15.dialogContent.getLineCount() > 2) {
                    FragmentBaseDialogBinding fragmentBaseDialogBinding16 = BaseDialogFragment.this.binding;
                    if (fragmentBaseDialogBinding16 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentBaseDialogBinding13 = fragmentBaseDialogBinding16;
                    }
                    fragmentBaseDialogBinding13.dialogContent.setGravity(GravityCompat.START);
                } else {
                    FragmentBaseDialogBinding fragmentBaseDialogBinding17 = BaseDialogFragment.this.binding;
                    if (fragmentBaseDialogBinding17 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentBaseDialogBinding13 = fragmentBaseDialogBinding17;
                    }
                    fragmentBaseDialogBinding13.dialogContent.setGravity(1);
                }
                return false;
            }
        });
        FragmentBaseDialogBinding fragmentBaseDialogBinding12 = this.binding;
        if (fragmentBaseDialogBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentBaseDialogBinding12 = null;
        }
        fragmentBaseDialogBinding12.dialogOk.setOnClickListener(new View.OnClickListener() { // from class: com.byd.automultipletheme.common.base.BaseDialogFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseDialogFragment.m53initViews$lambda2(this.f$0, view);
            }
        });
        FragmentBaseDialogBinding fragmentBaseDialogBinding13 = this.binding;
        if (fragmentBaseDialogBinding13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentBaseDialogBinding = fragmentBaseDialogBinding13;
        }
        fragmentBaseDialogBinding.dialogCancel.setOnClickListener(new View.OnClickListener() { // from class: com.byd.automultipletheme.common.base.BaseDialogFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseDialogFragment.m54initViews$lambda3(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /* JADX INFO: renamed from: initViews$lambda-2, reason: not valid java name */
    public static final void m53initViews$lambda2(BaseDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function0<Unit> function0 = null;
        AutoWallpaper autoWallpaper = null;
        AutoWallpaper autoWallpaper2 = null;
        if (this$0.okCallback == null) {
            LogUtils logUtils = LogUtils.INSTANCE;
            Object[] objArr = new Object[2];
            objArr[0] = "wallpapreType: ";
            AutoWallpaper autoWallpaper3 = this$0.wallpaperType;
            if (autoWallpaper3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("wallpaperType");
                autoWallpaper3 = null;
            }
            objArr[1] = Integer.valueOf(autoWallpaper3.getRsType());
            logUtils.i(objArr);
            AutoWallpaper autoWallpaper4 = this$0.wallpaperType;
            if (autoWallpaper4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("wallpaperType");
                autoWallpaper4 = null;
            }
            if (autoWallpaper4.getRsType() == 1) {
                try {
                    this$0.getViewModel().saveCarouselResources(this$0.getViewModel().getCarouselRes());
                    LogUtils.INSTANCE.i("preset wallpaper ...");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ApplyingWindowFragment.Companion companion = ApplyingWindowFragment.INSTANCE;
                FragmentManager childFragmentManager = this$0.getChildFragmentManager();
                Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
                AutoWallpaper autoWallpaper5 = this$0.wallpaperType;
                if (autoWallpaper5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("wallpaperType");
                } else {
                    autoWallpaper2 = autoWallpaper5;
                }
                companion.showDialog(childFragmentManager, autoWallpaper2);
                return;
            }
            AutoWallpaper autoWallpaper6 = this$0.wallpaperType;
            if (autoWallpaper6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("wallpaperType");
                autoWallpaper6 = null;
            }
            if (autoWallpaper6.getRsType() == 15) {
                ApplyingWindowFragment.Companion companion2 = ApplyingWindowFragment.INSTANCE;
                FragmentManager childFragmentManager2 = this$0.getChildFragmentManager();
                Intrinsics.checkNotNullExpressionValue(childFragmentManager2, "childFragmentManager");
                AutoWallpaper autoWallpaper7 = this$0.wallpaperType;
                if (autoWallpaper7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("wallpaperType");
                } else {
                    autoWallpaper = autoWallpaper7;
                }
                companion2.showDialog(childFragmentManager2, autoWallpaper);
                return;
            }
            return;
        }
        this$0.dismiss();
        Function0<Unit> function02 = this$0.okCallback;
        if (function02 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("okCallback");
        } else {
            function0 = function02;
        }
        function0.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initViews$lambda-3, reason: not valid java name */
    public static final void m54initViews$lambda3(BaseDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
        try {
            Function0<Unit> function0 = this$0.cancelCallback;
            if (function0 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cancelCallback");
                function0 = null;
            }
            function0.invoke();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final void initArguments() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            Serializable serializable = arguments.getSerializable(rsType_tag);
            Objects.requireNonNull(serializable, "null cannot be cast to non-null type com.byd.automultipletheme.bean.AutoWallpaper");
            this.wallpaperType = (AutoWallpaper) serializable;
        }
    }

    private final void applyRudder() {
        if (AutoUtils.INSTANCE.isRightRudder()) {
            LogUtils.INSTANCE.i("isRightRudder: ", Boolean.valueOf(AutoUtils.INSTANCE.isRightRudder()), " isLandscape: ", Boolean.valueOf(ScreenUtils.isLandscape()));
            ConstraintSet constraintSet = new ConstraintSet();
            FragmentBaseDialogBinding fragmentBaseDialogBinding = this.binding;
            FragmentBaseDialogBinding fragmentBaseDialogBinding2 = null;
            if (fragmentBaseDialogBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentBaseDialogBinding = null;
            }
            constraintSet.clone(fragmentBaseDialogBinding.getRoot());
            FragmentBaseDialogBinding fragmentBaseDialogBinding3 = this.binding;
            if (fragmentBaseDialogBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentBaseDialogBinding3 = null;
            }
            constraintSet.clear(fragmentBaseDialogBinding3.dialogOk.getId(), 2);
            FragmentBaseDialogBinding fragmentBaseDialogBinding4 = this.binding;
            if (fragmentBaseDialogBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentBaseDialogBinding4 = null;
            }
            int id = fragmentBaseDialogBinding4.dialogOk.getId();
            FragmentBaseDialogBinding fragmentBaseDialogBinding5 = this.binding;
            if (fragmentBaseDialogBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentBaseDialogBinding5 = null;
            }
            constraintSet.connect(id, 1, fragmentBaseDialogBinding5.glv50.getId(), 2, 36);
            FragmentBaseDialogBinding fragmentBaseDialogBinding6 = this.binding;
            if (fragmentBaseDialogBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentBaseDialogBinding6 = null;
            }
            constraintSet.clear(fragmentBaseDialogBinding6.dialogCancel.getId(), 1);
            FragmentBaseDialogBinding fragmentBaseDialogBinding7 = this.binding;
            if (fragmentBaseDialogBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentBaseDialogBinding7 = null;
            }
            int id2 = fragmentBaseDialogBinding7.dialogCancel.getId();
            FragmentBaseDialogBinding fragmentBaseDialogBinding8 = this.binding;
            if (fragmentBaseDialogBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentBaseDialogBinding8 = null;
            }
            constraintSet.connect(id2, 2, fragmentBaseDialogBinding8.glv50.getId(), 1, 36);
            FragmentBaseDialogBinding fragmentBaseDialogBinding9 = this.binding;
            if (fragmentBaseDialogBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentBaseDialogBinding2 = fragmentBaseDialogBinding9;
            }
            constraintSet.applyTo(fragmentBaseDialogBinding2.getRoot());
        }
    }

    /* JADX INFO: compiled from: BaseDialogFragment.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J<\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00042\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0014J4\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00042\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0014JB\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00042\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0014JJ\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00042\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/byd/automultipletheme/common/base/BaseDialogFragment$Companion;", "", "()V", "TAG", "", BaseDialogFragment.cancel_txt_tag, BaseDialogFragment.content_tag, BaseDialogFragment.ok_txt_tag, BaseDialogFragment.rsType_tag, BaseDialogFragment.title_tag, "showDialog", "", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "rsType", "Lcom/byd/automultipletheme/bean/AutoWallpaper;", "content", "okTxt", "cancelTxt", "okCallback", "Lkotlin/Function0;", "cancelCallback", "title", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void showDialog(FragmentManager fragmentManager, String title, String content, String okTxt, String cancelTxt, Function0<Unit> okCallback, Function0<Unit> cancelCallback) {
            Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(content, "content");
            Intrinsics.checkNotNullParameter(okTxt, "okTxt");
            Intrinsics.checkNotNullParameter(cancelTxt, "cancelTxt");
            Intrinsics.checkNotNullParameter(okCallback, "okCallback");
            Intrinsics.checkNotNullParameter(cancelCallback, "cancelCallback");
            BaseDialogFragment baseDialogFragment = new BaseDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString(BaseDialogFragment.title_tag, title);
            bundle.putString(BaseDialogFragment.content_tag, content);
            bundle.putString(BaseDialogFragment.ok_txt_tag, okTxt);
            bundle.putString(BaseDialogFragment.cancel_txt_tag, cancelTxt);
            baseDialogFragment.setArguments(bundle);
            baseDialogFragment.okCallback = okCallback;
            baseDialogFragment.cancelCallback = cancelCallback;
            baseDialogFragment.show(fragmentManager, BaseDialogFragment.TAG);
        }

        public final void showDialog(FragmentManager fragmentManager, String content, String okTxt, String cancelTxt, Function0<Unit> okCallback, Function0<Unit> cancelCallback) {
            Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
            Intrinsics.checkNotNullParameter(content, "content");
            Intrinsics.checkNotNullParameter(okTxt, "okTxt");
            Intrinsics.checkNotNullParameter(cancelTxt, "cancelTxt");
            Intrinsics.checkNotNullParameter(okCallback, "okCallback");
            Intrinsics.checkNotNullParameter(cancelCallback, "cancelCallback");
            BaseDialogFragment baseDialogFragment = new BaseDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString(BaseDialogFragment.content_tag, content);
            bundle.putString(BaseDialogFragment.ok_txt_tag, okTxt);
            bundle.putString(BaseDialogFragment.cancel_txt_tag, cancelTxt);
            baseDialogFragment.setArguments(bundle);
            baseDialogFragment.okCallback = okCallback;
            baseDialogFragment.cancelCallback = cancelCallback;
            baseDialogFragment.show(fragmentManager, BaseDialogFragment.TAG);
        }

        public final void showDialog(FragmentManager fragmentManager, AutoWallpaper rsType, String content, String okTxt, String cancelTxt, Function0<Unit> okCallback) {
            Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
            Intrinsics.checkNotNullParameter(rsType, "rsType");
            Intrinsics.checkNotNullParameter(content, "content");
            Intrinsics.checkNotNullParameter(okTxt, "okTxt");
            Intrinsics.checkNotNullParameter(cancelTxt, "cancelTxt");
            Intrinsics.checkNotNullParameter(okCallback, "okCallback");
            BaseDialogFragment baseDialogFragment = new BaseDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString(BaseDialogFragment.content_tag, content);
            bundle.putSerializable(BaseDialogFragment.rsType_tag, rsType);
            bundle.putString(BaseDialogFragment.ok_txt_tag, okTxt);
            bundle.putString(BaseDialogFragment.cancel_txt_tag, cancelTxt);
            baseDialogFragment.setArguments(bundle);
            baseDialogFragment.okCallback = okCallback;
            baseDialogFragment.cancelCallback = new Function0<Unit>() { // from class: com.byd.automultipletheme.common.base.BaseDialogFragment$Companion$showDialog$1
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
            baseDialogFragment.show(fragmentManager, BaseDialogFragment.TAG);
        }

        public final void showDialog(FragmentManager fragmentManager, String content, String okTxt, String cancelTxt, Function0<Unit> okCallback) {
            Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
            Intrinsics.checkNotNullParameter(content, "content");
            Intrinsics.checkNotNullParameter(okTxt, "okTxt");
            Intrinsics.checkNotNullParameter(cancelTxt, "cancelTxt");
            Intrinsics.checkNotNullParameter(okCallback, "okCallback");
            BaseDialogFragment baseDialogFragment = new BaseDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString(BaseDialogFragment.content_tag, content);
            bundle.putString(BaseDialogFragment.ok_txt_tag, okTxt);
            bundle.putString(BaseDialogFragment.cancel_txt_tag, cancelTxt);
            baseDialogFragment.setArguments(bundle);
            baseDialogFragment.okCallback = okCallback;
            baseDialogFragment.cancelCallback = new Function0<Unit>() { // from class: com.byd.automultipletheme.common.base.BaseDialogFragment$Companion$showDialog$2
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
            baseDialogFragment.show(fragmentManager, BaseDialogFragment.TAG);
        }
    }
}
