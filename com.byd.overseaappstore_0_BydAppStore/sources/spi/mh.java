package spi;

import android.content.Context;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import com.byd.overseaappstore.module.ability.util.notify.router.iuu.IUUState;
import com.byd.overseaappstore.ui.MainActivity;
import com.byd.overseaappstore.ui.view.base.BaseActivity;
import com.byd.overseaappstore.ui.view.dialog.iuu.IUUDialog;
import com.byd.overseaappstore.ui.view.dialog.iuu.IUUProcessDialog;
import com.byd.overseaappstore.ui.vm.MainViewModel;
import java.util.Objects;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@DebugMetadata(c = "com.byd.overseaappstore.ui.MainActivity$comprehensiveWork$4", f = "MainActivity.kt", i = {}, l = {721}, m = "invokeSuspend", n = {}, s = {})
public final class mh extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int a;
    public final /* synthetic */ MainActivity b;

    @DebugMetadata(c = "com.byd.overseaappstore.ui.MainActivity$comprehensiveWork$4$1", f = "MainActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    public static final class a extends SuspendLambda implements Function2<IUUState, Continuation<? super Unit>, Object> {
        public /* synthetic */ Object a;
        public final /* synthetic */ MainActivity b;

        /* JADX INFO: renamed from: spi.mh$a$a, reason: collision with other inner class name */
        public static final class C0133a extends Lambda implements Function1<IUUState, Unit> {
            public static final C0133a a = new C0133a();

            public C0133a() {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Unit invoke(IUUState iUUState) {
                return Unit.INSTANCE;
            }
        }

        public static final class b extends Lambda implements Function1<IUUState, Unit> {
            public final /* synthetic */ MainActivity a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(MainActivity mainActivity) {
                super(1);
                this.a = mainActivity;
            }

            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(IUUState iUUState) {
                IUUState toIUUState;
                IUUState iuuState = iUUState;
                if (iuuState == null) {
                    ct.b("iuu iUUDialog clickWork iUUState = null", new Object[0]);
                } else {
                    MainActivity mainActivity = this.a;
                    int i = MainActivity.s;
                    MainViewModel mainViewModelS = mainActivity.s();
                    MainActivity context = this.a;
                    Objects.requireNonNull(mainViewModelS);
                    Intrinsics.checkNotNullParameter(iuuState, "iuuState");
                    Intrinsics.checkNotNullParameter(context, "context");
                    ct.a(xi.f("iuu iUUWork state:", iuuState.getState(), " pkn:", iuuState.getSoftwareOptParam().getPackageName()), new Object[0]);
                    int state = iuuState.getState();
                    if (state == 0) {
                        toIUUState = mainViewModelS.g.parseToIUUState(iuuState.getSoftwareOptParam(), 3);
                    } else if (state == 1) {
                        toIUUState = mainViewModelS.g.parseToIUUState(iuuState.getSoftwareOptParam(), 4);
                    } else if (state != 2) {
                        ct.b(xi.f("iuu iUUWork other state:", iuuState.getState(), " pkn:", iuuState.getSoftwareOptParam().getPackageName()), new Object[0]);
                    } else {
                        IUUState toIUUState2 = mainViewModelS.g.parseToIUUState(iuuState.getSoftwareOptParam(), 5);
                        mainViewModelS.b.addBizTypeAndData(3, toIUUState2).workNotify();
                        aa aaVar = mainViewModelS.e;
                        SoftwareOptParam softwareOptParam = toIUUState2.getSoftwareOptParam();
                        Objects.requireNonNull(aaVar);
                        Intrinsics.checkNotNullParameter(softwareOptParam, "softwareOptParam");
                        Intrinsics.checkNotNullParameter(context, "context");
                        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(JobKt__JobKt.Job$default((Job) null, 1, (Object) null).plus(Dispatchers.getIO())), null, null, new ea(aaVar, softwareOptParam, context, null), 3, null);
                    }
                    mainViewModelS.b.addBizTypeAndData(3, toIUUState).workNotify();
                    mainViewModelS.e.b(toIUUState.getSoftwareOptParam(), context);
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(MainActivity mainActivity, Continuation<? super a> continuation) {
            super(2, continuation);
            this.b = mainActivity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            a aVar = new a(this.b, continuation);
            aVar.a = obj;
            return aVar;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(IUUState iUUState, Continuation<? super Unit> continuation) {
            return ((a) create(iUUState, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            MainActivity mainActivity;
            vm vmVar;
            Context applicationContext;
            int i;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ResultKt.throwOnFailure(obj);
            IUUState iUUState = (IUUState) this.a;
            int state = iUUState.getState();
            switch (state) {
                case 0:
                    ct.a(xi.f("iuu collect iUUState show installDialog already doNothing operateState:", state, " pkn:", iUUState.getSoftwareOptParam().getPackageName()), new Object[0]);
                    break;
                case 1:
                    ct.a(xi.f("iuu collect iUUState show updateDialog already doNothing operateState:", state, " pkn:", iUUState.getSoftwareOptParam().getPackageName()), new Object[0]);
                    break;
                case 2:
                    String strF = d.f(IUUDialog.class.getName(), "_UNINSTALL");
                    if (!sc.a.b(this.b, strF)) {
                        ct.a(xi.f("iuu collect iUUState show uninstallDialog operateState:", state, " pkn:", iUUState.getSoftwareOptParam().getPackageName()), new Object[0]);
                        IUUDialog iUUDialog = new IUUDialog();
                        iUUDialog.d = false;
                        iUUDialog.c = false;
                        iUUDialog.g = iUUState;
                        iUUDialog.e = C0133a.a;
                        iUUDialog.f = new b(this.b);
                        FragmentManager supportFragmentManager = this.b.getSupportFragmentManager();
                        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
                        iUUDialog.show(supportFragmentManager, strF);
                    } else {
                        ct.a(xi.f("iuu collect iUUState show uninstallDialog already doNothing operateState:", state, " pkn:", iUUState.getSoftwareOptParam().getPackageName()), new Object[0]);
                    }
                    break;
                case 3:
                    ct.a(xi.f("iuu collect iUUState show PROCESSING_INSTALL already doNothing operateState:", state, " pkn:", iUUState.getSoftwareOptParam().getPackageName()), new Object[0]);
                    break;
                case 4:
                    ct.a(xi.f("iuu collect iUUState show PROCESSING_UPDATE already doNothing operateState:", state, " pkn:", iUUState.getSoftwareOptParam().getPackageName()), new Object[0]);
                    break;
                case 5:
                    String name = IUUProcessDialog.class.getName();
                    if (!sc.a.b(this.b, name)) {
                        ct.a(xi.f("iuu collect iUUState show PROCESSING_UNINSTALL operateState:", state, " pkn:", iUUState.getSoftwareOptParam().getPackageName()), new Object[0]);
                        IUUProcessDialog iUUProcessDialog = new IUUProcessDialog();
                        iUUProcessDialog.d = false;
                        iUUProcessDialog.c = false;
                        iUUProcessDialog.e = iUUState;
                        FragmentManager supportFragmentManager2 = this.b.getSupportFragmentManager();
                        Intrinsics.checkNotNullExpressionValue(supportFragmentManager2, "supportFragmentManager");
                        iUUProcessDialog.show(supportFragmentManager2, name);
                    } else {
                        ct.a(xi.f("iuu collect iUUState show PROCESSING_UNINSTALL already doNothing operateState:", state, " pkn:", iUUState.getSoftwareOptParam().getPackageName()), new Object[0]);
                    }
                    break;
                case 6:
                    ct.a(xi.f("iuu collect iUUState show install finish already doNothing operateState:", state, " pkn:", iUUState.getSoftwareOptParam().getPackageName()), new Object[0]);
                    break;
                case 7:
                    ct.a(xi.f("iuu collect iUUState uninstall finish operateState:", state, " pkn:", iUUState.getSoftwareOptParam().getPackageName()), new Object[0]);
                    DialogFragment dialogFragmentA = sc.a.a(this.b, IUUProcessDialog.class.getName());
                    if (dialogFragmentA instanceof IUUProcessDialog) {
                        ((IUUProcessDialog) dialogFragmentA).dismissAllowingStateLoss();
                    } else {
                        StringBuilder sbH = xi.h("iuu collect iUUState iUUProcessDialog.dismiss error operateState:", state, " pkn:", iUUState.getSoftwareOptParam().getPackageName(), " iUUProcessDialog:");
                        sbH.append(dialogFragmentA);
                        ct.a(sbH.toString(), new Object[0]);
                    }
                    mainActivity = this.b;
                    vmVar = vm.a;
                    applicationContext = mainActivity.getApplicationContext();
                    Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
                    i = R.string.iuu_uninstalled_alert_tip;
                    BaseActivity.m(mainActivity, vmVar.e(applicationContext, i), 0, 2, null);
                    break;
                case 8:
                    ct.b(xi.f("iuu collect iUUState error operateState:", state, " pkn:", iUUState.getSoftwareOptParam().getPackageName()), new Object[0]);
                    DialogFragment dialogFragmentA2 = sc.a.a(this.b, IUUProcessDialog.class.getName());
                    if (dialogFragmentA2 instanceof IUUProcessDialog) {
                        ((IUUProcessDialog) dialogFragmentA2).dismissAllowingStateLoss();
                    } else {
                        StringBuilder sbH2 = xi.h("iuu collect iUUState iUUProcessDialog.dismiss error operateState:", state, " pkn:", iUUState.getSoftwareOptParam().getPackageName(), " iUUProcessDialog:");
                        sbH2.append(dialogFragmentA2);
                        ct.a(sbH2.toString(), new Object[0]);
                    }
                    mainActivity = this.b;
                    vmVar = vm.a;
                    applicationContext = mainActivity.getApplicationContext();
                    Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
                    i = R.string.iuu_install_fail_alert_tip;
                    BaseActivity.m(mainActivity, vmVar.e(applicationContext, i), 0, 2, null);
                    break;
                case 9:
                    DialogFragment dialogFragmentA3 = sc.a.a(this.b, IUUProcessDialog.class.getName());
                    if (dialogFragmentA3 instanceof IUUProcessDialog) {
                        ((IUUProcessDialog) dialogFragmentA3).dismissAllowingStateLoss();
                    } else {
                        StringBuilder sbH3 = xi.h("iuu collect iUUState iUUProcessDialog.dismiss fault_tolerant operateState:", state, " pkn:", iUUState.getSoftwareOptParam().getPackageName(), " iUUProcessDialog:");
                        sbH3.append(dialogFragmentA3);
                        ct.a(sbH3.toString(), new Object[0]);
                    }
                    ct.a(d.f("iuu collect iUUState fault_tolerant unKnown state pkn:", iUUState.getSoftwareOptParam().getPackageName()), new Object[0]);
                    break;
                default:
                    DialogFragment dialogFragmentA4 = sc.a.a(this.b, IUUProcessDialog.class.getName());
                    if (dialogFragmentA4 instanceof IUUProcessDialog) {
                        ((IUUProcessDialog) dialogFragmentA4).dismissAllowingStateLoss();
                    } else {
                        StringBuilder sbH4 = xi.h("iuu collect iUUState iUUProcessDialog.dismiss error operateState:", state, " pkn:", iUUState.getSoftwareOptParam().getPackageName(), " iUUProcessDialog:");
                        sbH4.append(dialogFragmentA4);
                        ct.a(sbH4.toString(), new Object[0]);
                    }
                    ct.b(d.f("iuu collect iUUState unKnown state pkn:", iUUState.getSoftwareOptParam().getPackageName()), new Object[0]);
                    break;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public mh(MainActivity mainActivity, Continuation<? super mh> continuation) {
        super(2, continuation);
        this.b = mainActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new mh(this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((mh) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MainActivity mainActivity = this.b;
            int i2 = MainActivity.s;
            MutableSharedFlow<IUUState> mutableSharedFlow = mainActivity.s().p;
            a aVar = new a(this.b, null);
            this.a = 1;
            if (FlowKt.collectLatest(mutableSharedFlow, aVar, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
