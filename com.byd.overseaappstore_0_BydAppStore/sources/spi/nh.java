package spi;

import android.content.IntentFilter;
import com.byd.overseaappstore.broadcast.LanguageChangeBroadcastReceiver;
import com.byd.overseaappstore.broadcast.NetWorkStateBroadcastReceiver;
import com.byd.overseaappstore.broadcast.SoftwareBroadcastReceiver;
import com.byd.overseaappstore.module.ability.util.notify.router.net.ExecutorNetContextState;
import com.byd.overseaappstore.ui.MainActivity;
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
@DebugMetadata(c = "com.byd.overseaappstore.ui.MainActivity$initConfig$1", f = "MainActivity.kt", i = {}, l = {505}, m = "invokeSuspend", n = {}, s = {})
public final class nh extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int a;
    public final /* synthetic */ MainActivity b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public nh(MainActivity mainActivity, Continuation<? super nh> continuation) {
        super(2, continuation);
        this.b = mainActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new nh(this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((nh) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ct.a("initConfig go", new Object[0]);
            ExecutorNetContextState executorNetContextState = this.b.q;
            LanguageChangeBroadcastReceiver languageChangeBroadcastReceiver = null;
            if (executorNetContextState == null) {
                Intrinsics.throwUninitializedPropertyAccessException("executorNetContextState");
                executorNetContextState = null;
            }
            executorNetContextState.initWifiChangedState();
            MainActivity mainActivity = this.b;
            Objects.requireNonNull(mainActivity);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
            intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
            intentFilter.addDataScheme("package");
            SoftwareBroadcastReceiver softwareBroadcastReceiver = mainActivity.i;
            if (softwareBroadcastReceiver == null) {
                Intrinsics.throwUninitializedPropertyAccessException("softwareBroadcastReceiver");
                softwareBroadcastReceiver = null;
            }
            mainActivity.registerReceiver(softwareBroadcastReceiver, intentFilter);
            MainActivity mainActivity2 = this.b;
            Objects.requireNonNull(mainActivity2);
            IntentFilter intentFilter2 = new IntentFilter();
            intentFilter2.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            NetWorkStateBroadcastReceiver netWorkStateBroadcastReceiver = mainActivity2.j;
            if (netWorkStateBroadcastReceiver == null) {
                Intrinsics.throwUninitializedPropertyAccessException("netWorkStateBroadcastReceiver");
                netWorkStateBroadcastReceiver = null;
            }
            mainActivity2.registerReceiver(netWorkStateBroadcastReceiver, intentFilter2);
            MainActivity mainActivity3 = this.b;
            Objects.requireNonNull(mainActivity3);
            IntentFilter intentFilter3 = new IntentFilter();
            intentFilter3.addAction("android.intent.action.LOCALE_CHANGED");
            LanguageChangeBroadcastReceiver languageChangeBroadcastReceiver2 = mainActivity3.k;
            if (languageChangeBroadcastReceiver2 != null) {
                languageChangeBroadcastReceiver = languageChangeBroadcastReceiver2;
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("languageChangeBroadcastReceiver");
            }
            mainActivity3.registerReceiver(languageChangeBroadcastReceiver, intentFilter3);
            MainActivity mainActivity4 = this.b;
            this.a = 1;
            if (MainActivity.n(mainActivity4, this) == coroutine_suspended) {
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
