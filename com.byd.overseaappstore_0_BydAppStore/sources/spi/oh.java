package spi;

import android.content.Context;
import androidx.fragment.app.DialogFragment;
import com.byd.overseaappstore.ui.MainActivity;
import com.byd.overseaappstore.ui.view.dialog.PermissionAlertDialog;
import com.byd.overseaappstore.ui.vm.MainViewModel;
import java.util.ArrayList;
import java.util.Objects;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@DebugMetadata(c = "com.byd.overseaappstore.ui.MainActivity$onIntentWork$1", f = "MainActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class oh extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ MainActivity a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public oh(MainActivity mainActivity, Continuation<? super oh> continuation) {
        super(2, continuation);
        this.a = mainActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new oh(this.a, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((oh) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        MainActivity mainActivity = this.a;
        String name = PermissionAlertDialog.class.getName();
        Intrinsics.checkNotNullExpressionValue(name, "PermissionAlertDialog::class.java.name");
        int i = MainActivity.s;
        Objects.requireNonNull(mainActivity);
        sc scVar = sc.a;
        boolean zB = scVar.b(mainActivity, name);
        if (zB) {
            DialogFragment dialogFragmentA = scVar.a(mainActivity, name);
            if (dialogFragmentA == null) {
                ct.b("tolerantPermissionDialog dialog is null noIntervene dialogFgTag:" + name + " ret:" + zB, new Object[0]);
            } else if (dialogFragmentA instanceof PermissionAlertDialog) {
                ct.a("tolerantPermissionDialog dialog is PermissionAlertDialog tolerantDismiss it dialogFgTag:" + name + " ret:" + zB, new Object[0]);
                ((PermissionAlertDialog) dialogFragmentA).dismissAllowingStateLoss();
            } else {
                ct.b("tolerantPermissionDialog unKnow dialog  noIntervene dialogFgTag:" + name + " ret:" + zB, new Object[0]);
            }
        } else {
            ct.a("tolerantPermissionDialog notAdd noIntervene dialogFgTag:" + name + " ret:" + zB, new Object[0]);
        }
        yk ykVar = yk.a;
        Context context = this.a.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(context, "applicationContext");
        Objects.requireNonNull(ykVar);
        Intrinsics.checkNotNullParameter(context, "context");
        boolean zIsEmpty = ((ArrayList) ykVar.a(context)).isEmpty();
        MainViewModel mainViewModelS = this.a.s();
        Context applicationContext = this.a.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        boolean zF = mainViewModelS.f(applicationContext);
        ct.a("onIntentWork launchWhenResumed allGranted:" + zIsEmpty + " protocolAgree:" + zF + " ", new Object[0]);
        this.a.o(this.a.s().a(zIsEmpty, zF), false);
        return Unit.INSTANCE;
    }
}
