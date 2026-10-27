package com.byd.overseaappstore.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleCoroutineScope;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.ActivityKt;
import androidx.navigation.NavController;
import androidx.viewbinding.ViewBinding;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.broadcast.LanguageChangeBroadcastReceiver;
import com.byd.overseaappstore.broadcast.NetWorkStateBroadcastReceiver;
import com.byd.overseaappstore.broadcast.SoftwareBroadcastReceiver;
import com.byd.overseaappstore.databinding.ActivityMainBinding;
import com.byd.overseaappstore.databinding.DialogOneButtonConfirmBinding;
import com.byd.overseaappstore.module.ability.util.ipc.EnterAppData;
import com.byd.overseaappstore.module.ability.util.ipc.IpcSoftware;
import com.byd.overseaappstore.module.ability.util.notify.router.ExecutorDialogRouterMgr;
import com.byd.overseaappstore.module.ability.util.notify.router.gear.ExecutorNotPGearState;
import com.byd.overseaappstore.module.ability.util.notify.router.gear.NotPBanDialogState;
import com.byd.overseaappstore.module.ability.util.notify.router.gear.NotPGearState;
import com.byd.overseaappstore.module.ability.util.notify.router.net.ExecutorNetContextState;
import com.byd.overseaappstore.ui.view.custom.skin.SkinTextView;
import com.byd.overseaappstore.ui.view.dialog.NotPBanDialog;
import com.byd.overseaappstore.ui.view.dialog.PermissionAlertDialog;
import com.byd.overseaappstore.ui.vm.MainViewModel;
import java.util.Objects;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.MutableStateFlow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.av;
import spi.ct;
import spi.hi;
import spi.i9;
import spi.j4;
import spi.l9;
import spi.nh;
import spi.o8;
import spi.oh;
import spi.qg;
import spi.qh;
import spi.r4;
import spi.s4;
import spi.sf;
import spi.vm;
import spi.yk;
import spi.z3;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/byd/overseaappstore/ui/MainActivity;", "Lcom/byd/overseaappstore/ui/view/base/BaseActivity;", "Lcom/byd/overseaappstore/databinding/ActivityMainBinding;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class MainActivity extends Hilt_MainActivity<ActivityMainBinding> {
    public static final /* synthetic */ int s = 0;

    @NotNull
    public final ViewModelLazy f;

    @Nullable
    public NotPBanDialog g;

    @Nullable
    public s4 h;
    public SoftwareBroadcastReceiver i;
    public NetWorkStateBroadcastReceiver j;
    public LanguageChangeBroadcastReceiver k;
    public i9 l;
    public l9 m;
    public ExecutorNotPGearState n;
    public o8 o;
    public ExecutorDialogRouterMgr p;
    public ExecutorNetContextState q;

    @NotNull
    public final ActivityResultLauncher<Intent> r;

    public static final class a extends Lambda implements Function0<Unit> {
        public a() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Unit invoke() {
            MainActivity mainActivity = MainActivity.this;
            int i = MainActivity.s;
            Objects.requireNonNull(mainActivity);
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(mainActivity), null, null, new nh(mainActivity, null), 3, null);
            return Unit.INSTANCE;
        }
    }

    public static final class b extends Lambda implements Function0<Unit> {
        public final /* synthetic */ boolean b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(boolean z) {
            super(0);
            this.b = z;
        }

        @Override // kotlin.jvm.functions.Function0
        public final Unit invoke() {
            MainActivity mainActivity = MainActivity.this;
            int i = MainActivity.s;
            Objects.requireNonNull(mainActivity);
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(mainActivity), null, null, new nh(mainActivity, null), 3, null);
            boolean z = this.b;
            if (z) {
                ct.a(hi.e("executeToOpen NY Go block alertPermission.show isPermissionBack:", z), new Object[0]);
                PermissionAlertDialog permissionAlertDialog = new PermissionAlertDialog();
                permissionAlertDialog.d = false;
                com.byd.overseaappstore.ui.a block = new com.byd.overseaappstore.ui.a(MainActivity.this);
                Intrinsics.checkNotNullParameter(block, "block");
                permissionAlertDialog.b = block;
                String title = MainActivity.this.getResources().getString(R.string.permission_denied_dialog_title);
                Intrinsics.checkNotNullExpressionValue(title, "resources.getString(R.st…sion_denied_dialog_title)");
                Intrinsics.checkNotNullParameter(title, "title");
                permissionAlertDialog.g = title;
                String normalText = MainActivity.this.getResources().getString(R.string.permission_denied_dialog_warning_text);
                Intrinsics.checkNotNullExpressionValue(normalText, "resources.getString(R.st…nied_dialog_warning_text)");
                Intrinsics.checkNotNullParameter(normalText, "normalText");
                permissionAlertDialog.h = normalText;
                String startButtonText = MainActivity.this.getResources().getString(R.string.permission_denied_dialog_start_button_setting);
                Intrinsics.checkNotNullExpressionValue(startButtonText, "resources.getString(R.st…log_start_button_setting)");
                Intrinsics.checkNotNullParameter(startButtonText, "startButtonText");
                permissionAlertDialog.i = startButtonText;
                String endButtonText = MainActivity.this.getResources().getString(R.string.permission_denied_dialog_end_button_cancel);
                Intrinsics.checkNotNullExpressionValue(endButtonText, "resources.getString(R.st…dialog_end_button_cancel)");
                Intrinsics.checkNotNullParameter(endButtonText, "endButtonText");
                permissionAlertDialog.j = endButtonText;
                com.byd.overseaappstore.ui.b block2 = new com.byd.overseaappstore.ui.b(permissionAlertDialog, MainActivity.this);
                Intrinsics.checkNotNullParameter(block2, "block");
                permissionAlertDialog.e = block2;
                com.byd.overseaappstore.ui.c block3 = new com.byd.overseaappstore.ui.c(permissionAlertDialog, MainActivity.this);
                Intrinsics.checkNotNullParameter(block3, "block");
                permissionAlertDialog.f = block3;
                FragmentManager supportFragmentManager = MainActivity.this.getSupportFragmentManager();
                Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
                permissionAlertDialog.show(supportFragmentManager, PermissionAlertDialog.class.getName());
            } else {
                ct.a(hi.e("executeToOpen NY Go block reqSysPermission.show isPermissionBack:", z), new Object[0]);
                ActivityCompat.requestPermissions(MainActivity.this, yk.a.b(), 1);
            }
            return Unit.INSTANCE;
        }
    }

    public static final class c extends Lambda implements Function0<Unit> {
        public final /* synthetic */ boolean a;
        public final /* synthetic */ MainActivity b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(boolean z, MainActivity mainActivity) {
            super(0);
            this.a = z;
            this.b = mainActivity;
        }

        @Override // kotlin.jvm.functions.Function0
        public final Unit invoke() {
            boolean z = this.a;
            if (z) {
                ct.a(hi.e("executeToOpen NN Go block alertPermission.show isPermissionBack:", z), new Object[0]);
                PermissionAlertDialog permissionAlertDialog = new PermissionAlertDialog();
                permissionAlertDialog.d = false;
                com.byd.overseaappstore.ui.d block = new com.byd.overseaappstore.ui.d(this.b);
                Intrinsics.checkNotNullParameter(block, "block");
                permissionAlertDialog.b = block;
                String title = this.b.getResources().getString(R.string.permission_denied_dialog_title);
                Intrinsics.checkNotNullExpressionValue(title, "resources.getString(R.st…sion_denied_dialog_title)");
                Intrinsics.checkNotNullParameter(title, "title");
                permissionAlertDialog.g = title;
                String normalText = this.b.getResources().getString(R.string.permission_denied_dialog_warning_text);
                Intrinsics.checkNotNullExpressionValue(normalText, "resources.getString(R.st…nied_dialog_warning_text)");
                Intrinsics.checkNotNullParameter(normalText, "normalText");
                permissionAlertDialog.h = normalText;
                String startButtonText = this.b.getResources().getString(R.string.permission_denied_dialog_start_button_setting);
                Intrinsics.checkNotNullExpressionValue(startButtonText, "resources.getString(R.st…log_start_button_setting)");
                Intrinsics.checkNotNullParameter(startButtonText, "startButtonText");
                permissionAlertDialog.i = startButtonText;
                String endButtonText = this.b.getResources().getString(R.string.permission_denied_dialog_end_button_cancel);
                Intrinsics.checkNotNullExpressionValue(endButtonText, "resources.getString(R.st…dialog_end_button_cancel)");
                Intrinsics.checkNotNullParameter(endButtonText, "endButtonText");
                permissionAlertDialog.j = endButtonText;
                com.byd.overseaappstore.ui.e block2 = new com.byd.overseaappstore.ui.e(permissionAlertDialog, this.b);
                Intrinsics.checkNotNullParameter(block2, "block");
                permissionAlertDialog.e = block2;
                com.byd.overseaappstore.ui.f block3 = new com.byd.overseaappstore.ui.f(permissionAlertDialog, this.b);
                Intrinsics.checkNotNullParameter(block3, "block");
                permissionAlertDialog.f = block3;
                FragmentManager supportFragmentManager = this.b.getSupportFragmentManager();
                Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
                permissionAlertDialog.show(supportFragmentManager, PermissionAlertDialog.class.getName());
            } else {
                ct.a(hi.e("executeToOpen NN Go block reqSysPermission.show isPermissionBack:", z), new Object[0]);
                ActivityCompat.requestPermissions(this.b, yk.a.b(), 1);
            }
            return Unit.INSTANCE;
        }
    }

    public static final class d extends Lambda implements Function1<View, Unit> {
        public d() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Unit invoke(View view) {
            View it = view;
            Intrinsics.checkNotNullParameter(it, "it");
            s4 s4Var = MainActivity.this.h;
            if (s4Var != null) {
                s4Var.a();
            }
            NotPBanDialogState.INSTANCE.setShowing(false);
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault()), null, null, new com.byd.overseaappstore.ui.g(MainActivity.this, null), 3, null);
            return Unit.INSTANCE;
        }
    }

    public static final class e extends Lambda implements Function1<Long, Unit> {
        public e() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Unit invoke(Long l) {
            long jLongValue = l.longValue();
            ct.a(spi.d.d("countDownTimerTwoParamsUtil onTick : ", jLongValue), new Object[0]);
            if (jLongValue > 0) {
                NotPBanDialog notPBanDialog = MainActivity.this.g;
                if (notPBanDialog != null) {
                    notPBanDialog.g = Long.valueOf(jLongValue);
                    DialogOneButtonConfirmBinding dialogOneButtonConfirmBinding = (DialogOneButtonConfirmBinding) notPBanDialog.a;
                    SkinTextView skinTextView = dialogOneButtonConfirmBinding != null ? dialogOneButtonConfirmBinding.c : null;
                    if (skinTextView != null) {
                        vm vmVar = vm.a;
                        Context contextRequireContext = notPBanDialog.requireContext();
                        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
                        skinTextView.setText(vmVar.f(contextRequireContext, R.string.not_p_ban_dialog_button, String.valueOf(notPBanDialog.g)));
                    }
                }
            } else {
                s4 s4Var = MainActivity.this.h;
                if (s4Var != null) {
                    s4Var.a();
                }
                MainActivity.this.h = null;
                NotPBanDialogState.INSTANCE.setShowing(false);
                BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault()), null, null, new h(MainActivity.this, null), 3, null);
            }
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.ui.MainActivity$observeData$1", f = "MainActivity.kt", i = {}, l = {211}, m = "invokeSuspend", n = {}, s = {})
    public static final class f extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        public static final class a<T> implements FlowCollector {
            public final /* synthetic */ MainActivity a;

            public a(MainActivity mainActivity) {
                this.a = mainActivity;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object obj, Continuation continuation) {
                NotPGearState notPGearState = (NotPGearState) obj;
                ct.a("showNotPDialogStateDataFlow : isPGear:" + notPGearState.getNotPGearState() + " , MainActivityLifecycle:" + this.a.getLifecycle().getCurrentState(), new Object[0]);
                if (notPGearState.getNotPGearState()) {
                    s4 s4Var = this.a.h;
                    if (s4Var != null) {
                        s4Var.a();
                    }
                    s4 s4Var2 = this.a.h;
                    if (s4Var2 != null) {
                        Long GEARBOX_P_TIMER_TOTAL = j4.c;
                        Intrinsics.checkNotNullExpressionValue(GEARBOX_P_TIMER_TOTAL, "GEARBOX_P_TIMER_TOTAL");
                        s4Var2.e = GEARBOX_P_TIMER_TOTAL.longValue();
                    }
                    NotPBanDialog notPBanDialog = this.a.g;
                    if (notPBanDialog != null) {
                        notPBanDialog.dismissAllowingStateLoss();
                    }
                    NotPBanDialogState.INSTANCE.setShowing(false);
                    MainActivity mainActivity = this.a;
                    mainActivity.g = null;
                    MainViewModel mainViewModelS = mainActivity.s();
                    Objects.requireNonNull(mainViewModelS);
                    ct.a("setInnitCurrentShowingType", new Object[0]);
                    mainViewModelS.b.updateCurrentShowingBizType(0);
                    this.a.s().b.workNotify();
                } else if (this.a.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED || this.a.getLifecycle().getCurrentState() == Lifecycle.State.STARTED) {
                    ct.a("SettingCleanCacheDialog show", new Object[0]);
                    this.a.g = new NotPBanDialog();
                    MainActivity mainActivity2 = this.a;
                    NotPBanDialog notPBanDialog2 = mainActivity2.g;
                    if (notPBanDialog2 != null) {
                        notPBanDialog2.d = false;
                    }
                    if (notPBanDialog2 != null) {
                        notPBanDialog2.c = false;
                    }
                    if (notPBanDialog2 != null) {
                        vm vmVar = vm.a;
                        String title = vmVar.e(mainActivity2, R.string.not_p_ban_dialog_title);
                        Intrinsics.checkNotNullParameter(title, "title");
                        notPBanDialog2.e = title;
                        String buttonText = vmVar.e(this.a, R.string.not_p_ban_dialog_button);
                        Intrinsics.checkNotNullParameter(buttonText, "buttonText");
                        notPBanDialog2.f = buttonText;
                        j block = new j(this.a);
                        Intrinsics.checkNotNullParameter(block, "block");
                        notPBanDialog2.h = block;
                    }
                    MainActivity mainActivity3 = this.a;
                    NotPBanDialog notPBanDialog3 = mainActivity3.g;
                    if (notPBanDialog3 != null) {
                        FragmentManager supportFragmentManager = mainActivity3.getSupportFragmentManager();
                        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "this@MainActivity.supportFragmentManager");
                        notPBanDialog3.show(supportFragmentManager, NotPBanDialog.class.getName());
                    }
                    NotPBanDialogState.INSTANCE.setShowing(true);
                    s4 s4Var3 = this.a.h;
                    if (s4Var3 != null) {
                        if (s4Var3.b != null) {
                            ct.b("CountDownTimerTwoParams already start", new Object[0]);
                        } else {
                            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new r4(s4Var3, null), 3, null);
                        }
                    }
                    MainViewModel mainViewModelS2 = this.a.s();
                    Objects.requireNonNull(mainViewModelS2);
                    ct.a("setInnitCurrentShowingType", new Object[0]);
                    mainViewModelS2.b.updateCurrentShowingBizType(4);
                }
                return Unit.INSTANCE;
            }
        }

        public f(Continuation<? super f> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return MainActivity.this.new f(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((f) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ExecutorNotPGearState executorNotPGearState = MainActivity.this.n;
                if (executorNotPGearState == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("executorNotPGearState");
                    executorNotPGearState = null;
                }
                MutableStateFlow<NotPGearState> notPGearStateDataFlow = executorNotPGearState.getNotPGearStateDataFlow();
                a aVar = new a(MainActivity.this);
                this.a = 1;
                if (notPGearStateDataFlow.collect(aVar, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            throw new KotlinNothingValueException();
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.ui.MainActivity$onDestroy$1", f = "MainActivity.kt", i = {}, l = {279, 280}, m = "invokeSuspend", n = {}, s = {})
    public static final class g extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        public g(Continuation<? super g> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return MainActivity.this.new g(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((g) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:71:0x0196  */
        /* JADX WARN: Removed duplicated region for block: B:72:0x0198  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r9) {
            /*
                Method dump skipped, instruction units count: 419
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.ui.MainActivity.g.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public MainActivity() {
        final Function0 function0 = null;
        this.f = new ViewModelLazy(Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: com.byd.overseaappstore.ui.MainActivity$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = this.getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "viewModelStore");
                return viewModelStore;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.byd.overseaappstore.ui.MainActivity$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory = this.getDefaultViewModelProviderFactory();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "defaultViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        }, new Function0<CreationExtras>() { // from class: com.byd.overseaappstore.ui.MainActivity$special$$inlined$viewModels$default$3
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
                CreationExtras defaultViewModelCreationExtras = this.getDefaultViewModelCreationExtras();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "this.defaultViewModelCreationExtras");
                return defaultViewModelCreationExtras;
            }
        });
        ActivityResultLauncher<Intent> activityResultLauncherRegisterForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new sf(this));
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult, "registerForActivityResul…n(actInState,false)\n    }");
        this.r = activityResultLauncherRegisterForActivityResult;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object n(com.byd.overseaappstore.ui.MainActivity r20, kotlin.coroutines.Continuation r21) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 234
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.ui.MainActivity.n(com.byd.overseaappstore.ui.MainActivity, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseActivity
    public final ViewBinding h() {
        ActivityMainBinding activityMainBindingA = ActivityMainBinding.a(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityMainBindingA, "inflate(layoutInflater)");
        return activityMainBindingA;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseActivity
    public final void i() {
        NotPBanDialog notPBanDialog = this.g;
        if (notPBanDialog != null) {
            notPBanDialog.d = false;
        }
        if (notPBanDialog != null) {
            notPBanDialog.c = false;
        }
        if (notPBanDialog != null) {
            vm vmVar = vm.a;
            String title = vmVar.e(this, R.string.not_p_ban_dialog_title);
            Intrinsics.checkNotNullParameter(title, "title");
            notPBanDialog.e = title;
            String buttonText = vmVar.e(this, R.string.not_p_ban_dialog_button);
            Intrinsics.checkNotNullParameter(buttonText, "buttonText");
            notPBanDialog.f = buttonText;
            d block = new d();
            Intrinsics.checkNotNullParameter(block, "block");
            notPBanDialog.h = block;
        }
        s4 s4Var = this.h;
        if (s4Var != null) {
            e block2 = new e();
            Intrinsics.checkNotNullParameter(block2, "block");
            s4Var.a = block2;
        }
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseActivity
    public final void j() {
        this.g = new NotPBanDialog();
        Long GEARBOX_P_TIMER_INTERVAL = j4.b;
        Intrinsics.checkNotNullExpressionValue(GEARBOX_P_TIMER_INTERVAL, "GEARBOX_P_TIMER_INTERVAL");
        long jLongValue = GEARBOX_P_TIMER_INTERVAL.longValue();
        Long GEARBOX_P_TIMER_TOTAL = j4.c;
        Intrinsics.checkNotNullExpressionValue(GEARBOX_P_TIMER_TOTAL, "GEARBOX_P_TIMER_TOTAL");
        this.h = new s4(jLongValue, GEARBOX_P_TIMER_TOTAL.longValue());
        av avVar = av.a;
        int iC = avVar.c(this);
        Objects.requireNonNull(avVar);
        Objects.requireNonNull(qg.a.a);
        String strB = avVar.b(this);
        String strA = avVar.a();
        StringBuilder sbL = spi.e.l("CarInfo from vehicle : vehicleType is ", iC, ", screenOrientation is ", 3, ", platform is ");
        sbL.append(strB);
        sbL.append(", countryCode is ");
        sbL.append(strA);
        ct.a(sbL.toString(), new Object[0]);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseActivity
    public final void k() {
        MainViewModel mainViewModelS = s();
        z3 z3Var = mainViewModelS.d;
        z3Var.a.b(z3Var.b);
        z3 z3Var2 = mainViewModelS.d;
        int iC = z3Var2.a.c(z3Var2.b);
        z3 z3Var3 = mainViewModelS.d;
        boolean zA = z3Var3.a.a(z3Var3.b);
        z3 z3Var4 = mainViewModelS.d;
        ct.a("UIStyle: " + iC + " isDynasty:" + zA + " isDenza:" + z3Var4.a.e(z3Var4.b), new Object[0]);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new f(null), 3, null);
        MainViewModel mainViewModelS2 = s();
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        LifecycleCoroutineScope coroutineScope = LifecycleOwnerKt.getLifecycleScope(this);
        Objects.requireNonNull(mainViewModelS2);
        Intrinsics.checkNotNullParameter(applicationContext, "applicationContext");
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new qh(mainViewModelS2, applicationContext, null), 3, null);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseActivity
    public final void l() {
        LifecycleOwnerKt.getLifecycleScope(this).launchWhenResumed(new oh(this, null));
    }

    public final void o(@NotNull String actInState, boolean z) {
        Object objM63constructorimpl;
        Object objM63constructorimpl2;
        Pair pair;
        Intrinsics.checkNotNullParameter(actInState, "actInState");
        MainViewModel mainViewModelS = s();
        Intent intent = getIntent();
        Objects.requireNonNull(mainViewModelS);
        Intrinsics.checkNotNullParameter(this, "context");
        if (intent == null) {
            ct.b("onIntentTargetDataGet intent is null", new Object[0]);
            pair = new Pair("", new EnterAppData(null, "ACTIVITY_DEFAULT"));
        } else {
            Uri data = intent.getData();
            if (data == null) {
                ct.a("onIntentTargetDataGet uri is null user coming", new Object[0]);
                pair = new Pair("", new EnterAppData(null, "ACTIVITY_DEFAULT"));
            } else {
                String scheme = data.getScheme();
                boolean z2 = true;
                if (scheme == null || scheme.length() == 0) {
                    ct.a("onIntentTargetDataGet scheme is isNullOrEmpty user coming", new Object[0]);
                    pair = new Pair("", new EnterAppData(null, "ACTIVITY_DEFAULT"));
                } else {
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        objM63constructorimpl = Result.m63constructorimpl(data.getQueryParameter(vm.a.e(this, R.string.uri_query_json_key)));
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM63constructorimpl = Result.m63constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m69isFailureimpl(objM63constructorimpl)) {
                        objM63constructorimpl = null;
                    }
                    String str = (String) objM63constructorimpl;
                    if (str == null || str.length() == 0) {
                        ct.b("onIntentTargetDataGet json is null uri:" + data + " json:" + str, new Object[0]);
                        pair = new Pair(scheme, new EnterAppData(null, "ACTIVITY_DEFAULT"));
                    } else {
                        try {
                            Result.Companion companion3 = Result.INSTANCE;
                            objM63constructorimpl2 = Result.m63constructorimpl(data.getQueryParameter(vm.a.e(this, R.string.uri_query_act_state_key)));
                        } catch (Throwable th2) {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM63constructorimpl2 = Result.m63constructorimpl(ResultKt.createFailure(th2));
                        }
                        if (Result.m69isFailureimpl(objM63constructorimpl2)) {
                            objM63constructorimpl2 = null;
                        }
                        String str2 = (String) objM63constructorimpl2;
                        if (str2 != null && str2.length() != 0) {
                            z2 = false;
                        }
                        if (z2) {
                            ct.b("onIntentTargetDataGet actState is null uri:" + data + " json:" + str, new Object[0]);
                            pair = new Pair(scheme, new EnterAppData(null, "ACTIVITY_DEFAULT"));
                        } else {
                            IpcSoftware toIpcSoftware = mainViewModelS.g.parseToIpcSoftware(str);
                            if (toIpcSoftware == null) {
                                ct.b("onIntentTargetDataGet ipcSoftware is null uri:" + data + " json:" + str, new Object[0]);
                                pair = new Pair(scheme, new EnterAppData(null, "ACTIVITY_DEFAULT"));
                            } else {
                                pair = new Pair(scheme, new EnterAppData(toIpcSoftware, str2));
                            }
                        }
                    }
                }
            }
        }
        String str3 = (String) pair.getFirst();
        EnterAppData<IpcSoftware, String> enterAppData = (EnterAppData) pair.getSecond();
        StringBuilder sbL = spi.d.l("executeToOpen ipcLogicState:", enterAppData.getState(), " actInState:", actInState, " isPermissionBack:");
        sbL.append(z);
        ct.a(sbL.toString(), new Object[0]);
        NavController navControllerFindNavController = ActivityKt.findNavController(this, R.id.fcv_main);
        int iHashCode = actInState.hashCode();
        if (iHashCode == 2496) {
            if (actInState.equals("NN")) {
                p().d(this, str3, enterAppData, navControllerFindNavController, new c(z, this));
                return;
            }
            return;
        }
        if (iHashCode == 2507) {
            if (actInState.equals("NY")) {
                p().b(this, str3, enterAppData, navControllerFindNavController, new b(z));
            }
        } else {
            if (iHashCode == 2837) {
                if (actInState.equals("YN")) {
                    ct.a("executeToOpen YN Go ", new Object[0]);
                    p().c(this, str3, enterAppData, navControllerFindNavController);
                    return;
                }
                return;
            }
            if (iHashCode == 2848 && actInState.equals("YY")) {
                ct.a("executeToOpen YY Go ", new Object[0]);
                p().a(this, str3, enterAppData, navControllerFindNavController, new a());
            }
        }
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onDestroy() {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new g(null), 3, null);
        super.onDestroy();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x003c  */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onRequestPermissionsResult(int r7, @org.jetbrains.annotations.NotNull java.lang.String[] r8, @org.jetbrains.annotations.NotNull int[] r9) {
        /*
            r6 = this;
            java.lang.String r0 = "permissions"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            java.lang.String r1 = "grantResults"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r1)
            super.onRequestPermissionsResult(r7, r8, r9)
            spi.yk r2 = spi.yk.a
            android.content.Context r3 = r6.getApplicationContext()
            java.lang.String r4 = "applicationContext"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            java.util.Objects.requireNonNull(r2)
            java.lang.String r5 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r5)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r1)
            r0 = 0
            r1 = 1
            if (r7 != r1) goto L3c
            java.util.List r7 = r2.a(r3)
            int r9 = r9.length
            int r8 = r8.length
            java.util.ArrayList r7 = (java.util.ArrayList) r7
            int r7 = r7.size()
            int r8 = r8 - r7
            if (r9 != r8) goto L3c
            r7 = r1
            goto L3d
        L3c:
            r7 = r0
        L3d:
            com.byd.overseaappstore.ui.vm.MainViewModel r8 = r6.s()
            android.content.Context r9 = r6.getApplicationContext()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r4)
            boolean r8 = r8.f(r9)
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r2 = "permissionWork onRequestPermissionsResult allGranted:"
            r9.append(r2)
            r9.append(r7)
            java.lang.String r2 = " protocolAgree:"
            r9.append(r2)
            r9.append(r8)
            java.lang.String r2 = " "
            r9.append(r2)
            java.lang.String r9 = r9.toString()
            java.lang.Object[] r0 = new java.lang.Object[r0]
            spi.ct.a(r9, r0)
            com.byd.overseaappstore.ui.vm.MainViewModel r9 = r6.s()
            java.lang.String r7 = r9.a(r7, r8)
            r6.o(r7, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.ui.MainActivity.onRequestPermissionsResult(int, java.lang.String[], int[]):void");
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public final void onStateChanged(@NotNull LifecycleOwner source, @NotNull Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(event, "event");
        Objects.requireNonNull(s());
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(event, "event");
    }

    @NotNull
    public final o8 p() {
        o8 o8Var = this.o;
        if (o8Var != null) {
            return o8Var;
        }
        Intrinsics.throwUninitializedPropertyAccessException("enterAppMgr");
        return null;
    }

    @NotNull
    public final ExecutorDialogRouterMgr q() {
        ExecutorDialogRouterMgr executorDialogRouterMgr = this.p;
        if (executorDialogRouterMgr != null) {
            return executorDialogRouterMgr;
        }
        Intrinsics.throwUninitializedPropertyAccessException("executorDialogRouterMgr");
        return null;
    }

    @NotNull
    public final l9 r() {
        l9 l9Var = this.m;
        if (l9Var != null) {
            return l9Var;
        }
        Intrinsics.throwUninitializedPropertyAccessException("executorDownloadTaskMgr");
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final MainViewModel s() {
        return (MainViewModel) this.f.getValue();
    }
}
