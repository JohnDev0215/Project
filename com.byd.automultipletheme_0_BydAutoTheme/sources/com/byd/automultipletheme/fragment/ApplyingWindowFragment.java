package com.byd.automultipletheme.fragment;

import android.app.Dialog;
import android.content.ContentResolver;
import android.hardware.bydauto.properties.CarSettings;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import com.blankj.utilcode.util.Utils;
import com.byd.automultipletheme.R;
import com.byd.automultipletheme.bean.AutoTheme;
import com.byd.automultipletheme.bean.AutoWallpaper;
import com.byd.automultipletheme.bean.EventBusBean;
import com.byd.automultipletheme.common.AutoUtils;
import com.byd.automultipletheme.common.Const;
import com.byd.automultipletheme.common.EventString;
import com.byd.automultipletheme.common.LogUtils;
import com.byd.automultipletheme.common.SettingsObserver;
import com.byd.automultipletheme.common.db.DBUtils;
import com.byd.automultipletheme.databinding.FragmentApplyingWindowBinding;
import com.byd.automultipletheme.fragment.ApplyingWindowFragment;
import com.byd.automultipletheme.vm.CarouselAddViewModel;
import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: ApplyingWindowFragment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 '2\u00020\u0001:\u0001'B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0015H\u0002J\u0012\u0010\u0017\u001a\u00020\u00152\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J$\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010 \u001a\u00020\u0015H\u0016J\u0010\u0010!\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020#H\u0007J\b\u0010$\u001a\u00020\u0015H\u0016J\u001a\u0010%\u001a\u00020\u00152\u0006\u0010&\u001a\u00020\u001b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011¨\u0006("}, d2 = {"Lcom/byd/automultipletheme/fragment/ApplyingWindowFragment;", "Landroidx/fragment/app/DialogFragment;", "()V", "binding", "Lcom/byd/automultipletheme/databinding/FragmentApplyingWindowBinding;", "contentResolver", "Landroid/content/ContentResolver;", "kotlin.jvm.PlatformType", "countDownTimer", "Landroid/os/CountDownTimer;", "mSettingsObserver", "Lcom/byd/automultipletheme/common/SettingsObserver;", "setType", "", "viewModel", "Lcom/byd/automultipletheme/vm/CarouselAddViewModel;", "getViewModel", "()Lcom/byd/automultipletheme/vm/CarouselAddViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "afterFifteenSeconds", "", "initData", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "onEvent", NotificationCompat.CATEGORY_EVENT, "Lcom/byd/automultipletheme/bean/EventBusBean;", "onStart", "onViewCreated", "view", "Companion", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ApplyingWindowFragment extends DialogFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "ApplyingWindowFragment";
    private FragmentApplyingWindowBinding binding;
    private final ContentResolver contentResolver;
    private CountDownTimer countDownTimer;
    private final SettingsObserver mSettingsObserver;
    private Object setType;

    /* JADX INFO: renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    public ApplyingWindowFragment() {
        final Function0<ViewModelStoreOwner> function0 = new Function0<ViewModelStoreOwner>() { // from class: com.byd.automultipletheme.fragment.ApplyingWindowFragment$viewModel$2
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
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(CarouselAddViewModel.class), new Function0<ViewModelStore>() { // from class: com.byd.automultipletheme.fragment.ApplyingWindowFragment$special$$inlined$viewModels$default$1
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
        this.mSettingsObserver = new SettingsObserver(null);
        this.contentResolver = Utils.getApp().getContentResolver();
    }

    private final CarouselAddViewModel getViewModel() {
        return (CarouselAddViewModel) this.viewModel.getValue();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (window != null) {
                Intrinsics.checkNotNullExpressionValue(window, "window");
                int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.dp_168);
                int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.dp_140);
                if (AutoUtils.INSTANCE.getModelType() == 3) {
                    window.setBackgroundDrawableResource(R.drawable.ic_dialog_bg_denza);
                } else {
                    window.setBackgroundDrawableResource(R.drawable.ic_dialog_bg);
                }
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.width = dimensionPixelSize;
                attributes.height = dimensionPixelSize2;
                attributes.gravity = 17;
                window.setAttributes(attributes);
            }
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentApplyingWindowBinding fragmentApplyingWindowBindingInflate = FragmentApplyingWindowBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentApplyingWindowBindingInflate, "inflate(inflater, container, false)");
        this.binding = fragmentApplyingWindowBindingInflate;
        if (fragmentApplyingWindowBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentApplyingWindowBindingInflate = null;
        }
        LinearLayout root = fragmentApplyingWindowBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            Serializable serializable = arguments.getSerializable(TAG);
            Objects.requireNonNull(serializable, "null cannot be cast to non-null type kotlin.Any");
            this.setType = serializable;
        }
        ContentResolver contentResolver = this.contentResolver;
        if (contentResolver != null) {
            contentResolver.registerContentObserver(CarSettings.UserTableData.CONTENT_URI, true, this.mSettingsObserver);
        }
        if (AutoUtils.INSTANCE.getModelType() == 3) {
            FragmentApplyingWindowBinding fragmentApplyingWindowBinding = this.binding;
            if (fragmentApplyingWindowBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentApplyingWindowBinding = null;
            }
            fragmentApplyingWindowBinding.loadingTxt.setTextColor(getResources().getColor(R.color.ffDAE1EB));
        }
        initData();
        afterFifteenSeconds();
    }

    /* JADX INFO: renamed from: com.byd.automultipletheme.fragment.ApplyingWindowFragment$afterFifteenSeconds$1, reason: invalid class name */
    /* JADX INFO: compiled from: ApplyingWindowFragment.kt */
    @Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/byd/automultipletheme/fragment/ApplyingWindowFragment$afterFifteenSeconds$1", "Landroid/os/CountDownTimer;", "onFinish", "", "onTick", "p0", "", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class AnonymousClass1 extends CountDownTimer {
        final /* synthetic */ ApplyingWindowFragment this$0;

        @Override // android.os.CountDownTimer
        public void onTick(long p0) {
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(long j, long j2, ApplyingWindowFragment applyingWindowFragment) {
            super(j, j2);
            this.this$0 = applyingWindowFragment;
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            LogUtils.INSTANCE.i("15 seconds! Apply failed");
            FragmentApplyingWindowBinding fragmentApplyingWindowBinding = this.this$0.binding;
            FragmentApplyingWindowBinding fragmentApplyingWindowBinding2 = null;
            if (fragmentApplyingWindowBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentApplyingWindowBinding = null;
            }
            fragmentApplyingWindowBinding.loadingBar.setVisibility(8);
            FragmentApplyingWindowBinding fragmentApplyingWindowBinding3 = this.this$0.binding;
            if (fragmentApplyingWindowBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentApplyingWindowBinding3 = null;
            }
            fragmentApplyingWindowBinding3.loadingState.setVisibility(0);
            FragmentApplyingWindowBinding fragmentApplyingWindowBinding4 = this.this$0.binding;
            if (fragmentApplyingWindowBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentApplyingWindowBinding4 = null;
            }
            fragmentApplyingWindowBinding4.loadingState.setImageResource(R.mipmap.ic_fail);
            FragmentApplyingWindowBinding fragmentApplyingWindowBinding5 = this.this$0.binding;
            if (fragmentApplyingWindowBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentApplyingWindowBinding5 = null;
            }
            fragmentApplyingWindowBinding5.loadingTxt.setText(R.string.str_failed);
            Dialog dialog = this.this$0.getDialog();
            if (dialog != null) {
                dialog.setCancelable(false);
            }
            FragmentApplyingWindowBinding fragmentApplyingWindowBinding6 = this.this$0.binding;
            if (fragmentApplyingWindowBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentApplyingWindowBinding2 = fragmentApplyingWindowBinding6;
            }
            LinearLayout root = fragmentApplyingWindowBinding2.getRoot();
            final ApplyingWindowFragment applyingWindowFragment = this.this$0;
            root.postDelayed(new Runnable() { // from class: com.byd.automultipletheme.fragment.ApplyingWindowFragment$afterFifteenSeconds$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ApplyingWindowFragment.AnonymousClass1.m61onFinish$lambda0(applyingWindowFragment);
                }
            }, 300L);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: onFinish$lambda-0, reason: not valid java name */
        public static final void m61onFinish$lambda0(ApplyingWindowFragment this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.dismissAllowingStateLoss();
        }
    }

    private final void afterFifteenSeconds() {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(15000L, 1000L, this);
        this.countDownTimer = anonymousClass1;
        anonymousClass1.start();
    }

    private final void initData() {
        Object obj = this.setType;
        if (obj == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setType");
            obj = Unit.INSTANCE;
        }
        if (obj instanceof AutoTheme) {
            LogUtils.INSTANCE.i("setAutoTheme");
            AutoUtils autoUtils = AutoUtils.INSTANCE;
            Object obj2 = this.setType;
            if (obj2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("setType");
                obj2 = Unit.INSTANCE;
            }
            autoUtils.setBYDCurrentThemeId((AutoTheme) obj2);
            return;
        }
        if (obj instanceof AutoWallpaper) {
            LogUtils logUtils = LogUtils.INSTANCE;
            Object[] objArr = new Object[2];
            objArr[0] = "rsType: ";
            Object obj3 = this.setType;
            if (obj3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("setType");
                obj3 = Unit.INSTANCE;
            }
            objArr[1] = Integer.valueOf(((AutoWallpaper) obj3).getRsType());
            logUtils.i(objArr);
            Object obj4 = this.setType;
            if (obj4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("setType");
                obj4 = Unit.INSTANCE;
            }
            if (((AutoWallpaper) obj4).getRsType() != 1) {
                DBUtils dBUtils = DBUtils.INSTANCE;
                Object obj5 = this.setType;
                if (obj5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("setType");
                    obj5 = Unit.INSTANCE;
                }
                int rsType = ((AutoWallpaper) obj5).getRsType();
                Object obj6 = this.setType;
                if (obj6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("setType");
                    obj6 = Unit.INSTANCE;
                }
                dBUtils.setWallpaper(Const.CONTENT_WALLPAPER_PATH, rsType, ((AutoWallpaper) obj6).getRsId(), new Function1<Integer, Unit>() { // from class: com.byd.automultipletheme.fragment.ApplyingWindowFragment.initData.1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                        invoke(num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i) {
                        LogUtils.INSTANCE.i("set common wallpaper done");
                    }
                });
                return;
            }
            return;
        }
        LogUtils.INSTANCE.i("setWallpaper is else");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onEvent(final EventBusBean event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (Intrinsics.areEqual(event.getEventType(), EventString.SAVE) && event.getEventResult() == 0) {
            Object obj = this.setType;
            if (obj == null) {
                Intrinsics.throwUninitializedPropertyAccessException("setType");
                obj = Unit.INSTANCE;
            }
            if (obj instanceof AutoWallpaper) {
                LogUtils.INSTANCE.i("set carousel wallpaper");
                DBUtils dBUtils = DBUtils.INSTANCE;
                Object obj2 = this.setType;
                if (obj2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("setType");
                    obj2 = Unit.INSTANCE;
                }
                int rsType = ((AutoWallpaper) obj2).getRsType();
                Object obj3 = this.setType;
                if (obj3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("setType");
                    obj3 = Unit.INSTANCE;
                }
                dBUtils.setWallpaper(Const.CONTENT_WALLPAPER_PATH, rsType, ((AutoWallpaper) obj3).getRsId(), new Function1<Integer, Unit>() { // from class: com.byd.automultipletheme.fragment.ApplyingWindowFragment.onEvent.1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                        invoke(num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i) {
                        LogUtils.INSTANCE.i("set carousel wallpaper done");
                    }
                });
                return;
            }
            LogUtils.INSTANCE.i("setWallpaper is else");
            return;
        }
        if (Intrinsics.areEqual(event.getEventType(), EventString.RESULT)) {
            LogUtils.INSTANCE.i("callBack result", Integer.valueOf(event.getEventResult()));
            FragmentApplyingWindowBinding fragmentApplyingWindowBinding = this.binding;
            if (fragmentApplyingWindowBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentApplyingWindowBinding = null;
            }
            fragmentApplyingWindowBinding.getRoot().postDelayed(new Runnable() { // from class: com.byd.automultipletheme.fragment.ApplyingWindowFragment$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() throws ExecutionException, InterruptedException {
                    ApplyingWindowFragment.m57onEvent$lambda5(this.f$0, event);
                }
            }, 400L);
            return;
        }
        if (Intrinsics.areEqual(event.getEventType(), EventString.LAND)) {
            try {
                getViewModel().saveAnotherPresetCarouselResources(getViewModel().getCarouselRes());
                LogUtils.INSTANCE.i("start saving port wallpaper ...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onEvent$lambda-5, reason: not valid java name */
    public static final void m57onEvent$lambda5(final ApplyingWindowFragment this$0, EventBusBean event) throws ExecutionException, InterruptedException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(event, "$event");
        FragmentApplyingWindowBinding fragmentApplyingWindowBinding = this$0.binding;
        FragmentApplyingWindowBinding fragmentApplyingWindowBinding2 = null;
        if (fragmentApplyingWindowBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentApplyingWindowBinding = null;
        }
        fragmentApplyingWindowBinding.loadingBar.setVisibility(8);
        FragmentApplyingWindowBinding fragmentApplyingWindowBinding3 = this$0.binding;
        if (fragmentApplyingWindowBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentApplyingWindowBinding3 = null;
        }
        fragmentApplyingWindowBinding3.loadingState.setVisibility(0);
        if (event.getEventResult() == 1) {
            Object obj = this$0.setType;
            if (obj == null) {
                Intrinsics.throwUninitializedPropertyAccessException("setType");
                obj = Unit.INSTANCE;
            }
            if (obj instanceof AutoTheme) {
                LogUtils.INSTANCE.i("Remember the current AutoTheme ID");
                AutoUtils autoUtils = AutoUtils.INSTANCE;
                Object obj2 = this$0.setType;
                if (obj2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("setType");
                    obj2 = Unit.INSTANCE;
                }
                DBUtils.INSTANCE.setCurrentWallpaperId(autoUtils.convertAutoThemeToAutoWallpaper((AutoTheme) obj2).getRsId());
            } else if (obj instanceof AutoWallpaper) {
                LogUtils.INSTANCE.i("The wallpaper is set successfully. Remember the current wallpaper ID");
                DBUtils dBUtils = DBUtils.INSTANCE;
                Object obj3 = this$0.setType;
                if (obj3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("setType");
                    obj3 = Unit.INSTANCE;
                }
                dBUtils.setCurrentWallpaperId(((AutoWallpaper) obj3).getRsId());
            } else {
                LogUtils.INSTANCE.i("nothing to do");
            }
            FragmentApplyingWindowBinding fragmentApplyingWindowBinding4 = this$0.binding;
            if (fragmentApplyingWindowBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentApplyingWindowBinding4 = null;
            }
            fragmentApplyingWindowBinding4.loadingState.setImageResource(R.mipmap.ic_success);
            FragmentApplyingWindowBinding fragmentApplyingWindowBinding5 = this$0.binding;
            if (fragmentApplyingWindowBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentApplyingWindowBinding5 = null;
            }
            fragmentApplyingWindowBinding5.loadingTxt.setText(R.string.str_applied);
            FragmentApplyingWindowBinding fragmentApplyingWindowBinding6 = this$0.binding;
            if (fragmentApplyingWindowBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentApplyingWindowBinding2 = fragmentApplyingWindowBinding6;
            }
            fragmentApplyingWindowBinding2.getRoot().postDelayed(new Runnable() { // from class: com.byd.automultipletheme.fragment.ApplyingWindowFragment$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ApplyingWindowFragment.m58onEvent$lambda5$lambda3(this.f$0);
                }
            }, 400L);
            return;
        }
        FragmentApplyingWindowBinding fragmentApplyingWindowBinding7 = this$0.binding;
        if (fragmentApplyingWindowBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentApplyingWindowBinding7 = null;
        }
        fragmentApplyingWindowBinding7.loadingState.setImageResource(R.mipmap.ic_fail);
        FragmentApplyingWindowBinding fragmentApplyingWindowBinding8 = this$0.binding;
        if (fragmentApplyingWindowBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentApplyingWindowBinding8 = null;
        }
        fragmentApplyingWindowBinding8.loadingTxt.setText(R.string.str_failed);
        Dialog dialog = this$0.getDialog();
        if (dialog != null) {
            dialog.setCancelable(false);
        }
        FragmentApplyingWindowBinding fragmentApplyingWindowBinding9 = this$0.binding;
        if (fragmentApplyingWindowBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentApplyingWindowBinding2 = fragmentApplyingWindowBinding9;
        }
        fragmentApplyingWindowBinding2.getRoot().postDelayed(new Runnable() { // from class: com.byd.automultipletheme.fragment.ApplyingWindowFragment$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                ApplyingWindowFragment.m59onEvent$lambda5$lambda4(this.f$0);
            }
        }, 400L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onEvent$lambda-5$lambda-3, reason: not valid java name */
    public static final void m58onEvent$lambda5$lambda3(ApplyingWindowFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isAdded()) {
            this$0.dismissAllowingStateLoss();
            LogUtils.INSTANCE.i("fragmentManager != null");
        }
        EventBus.getDefault().post(new EventBusBean(EventString.SUCCESS, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onEvent$lambda-5$lambda-4, reason: not valid java name */
    public static final void m59onEvent$lambda5$lambda4(ApplyingWindowFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismissAllowingStateLoss();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            if (countDownTimer == null) {
                Intrinsics.throwUninitializedPropertyAccessException("countDownTimer");
                countDownTimer = null;
            }
            countDownTimer.cancel();
        } else {
            LogUtils.INSTANCE.i("countDownTimer is not initialized");
        }
        ContentResolver contentResolver = this.contentResolver;
        if (contentResolver != null) {
            contentResolver.unregisterContentObserver(this.mSettingsObserver);
        }
    }

    /* JADX INFO: compiled from: ApplyingWindowFragment.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0001R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/byd/automultipletheme/fragment/ApplyingWindowFragment$Companion;", "", "()V", "TAG", "", "showDialog", "", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "any", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void showDialog(FragmentManager fragmentManager, Object any) {
            Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
            Intrinsics.checkNotNullParameter(any, "any");
            ApplyingWindowFragment applyingWindowFragment = new ApplyingWindowFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(ApplyingWindowFragment.TAG, (Serializable) any);
            applyingWindowFragment.setArguments(bundle);
            applyingWindowFragment.show(fragmentManager, ApplyingWindowFragment.TAG);
        }
    }
}
