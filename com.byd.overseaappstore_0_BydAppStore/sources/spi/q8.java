package spi;

import android.content.Context;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.module.ability.util.ipc.EnterAppData;
import com.byd.overseaappstore.module.ability.util.ipc.IpcSoftware;
import com.byd.overseaappstore.module.ability.util.notify.router.ExecutorDialogRouterMgr;
import com.byd.overseaappstore.module.ability.util.notify.router.gear.NotPGearState;
import java.util.Iterator;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
public final class q8 implements p8 {

    @NotNull
    public final ExecutorDialogRouterMgr a;

    public static final class a extends Lambda implements Function2<Boolean, Integer, Unit> {
        public a() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Unit mo6invoke(Boolean bool, Integer num) {
            boolean zBooleanValue = bool.booleanValue();
            num.intValue();
            q8.this.a.addBizTypeAndData(4, new NotPGearState(zBooleanValue)).workNotify();
            return Unit.INSTANCE;
        }
    }

    public static final class b extends Lambda implements Function2<Boolean, Integer, Unit> {
        public b() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Unit mo6invoke(Boolean bool, Integer num) {
            boolean zBooleanValue = bool.booleanValue();
            num.intValue();
            q8.this.a.addBizTypeAndData(4, new NotPGearState(zBooleanValue)).workNotify();
            return Unit.INSTANCE;
        }
    }

    public q8(@NotNull ExecutorDialogRouterMgr executorDialogRouterMgr) {
        Intrinsics.checkNotNullParameter(executorDialogRouterMgr, "executorDialogRouterMgr");
        this.a = executorDialogRouterMgr;
    }

    @Override // spi.p8
    public final void a(@NotNull Context context, @NotNull String scheme, @NotNull EnterAppData<IpcSoftware, String> enterAppData, @NotNull NavController navController, @NotNull Function0<Unit> block) {
        NavBackStackEntry next;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(scheme, "scheme");
        Intrinsics.checkNotNullParameter(enterAppData, "enterAppData");
        Intrinsics.checkNotNullParameter(navController, "navController");
        Intrinsics.checkNotNullParameter(block, "block");
        Iterator<NavBackStackEntry> it = navController.getBackQueue().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            String displayName = next.getDestination().getDisplayName();
            Intrinsics.checkNotNullExpressionValue("FeatureFragment", "FeatureFragment::class.java.simpleName");
            if (StringsKt__StringsKt.contains(displayName, (CharSequence) "FeatureFragment", true)) {
                break;
            }
        }
        if (next != null) {
            ct.a("enterAppWork enterYY normalGo noIntervene ipcSoftwareIsNull ACTIVITY_DEFAULT or ACTIVITY_DESTROY ", new Object[0]);
            return;
        }
        ct.a("enterAppWork enterYY normalGo dstIsNull", new Object[0]);
        block.invoke();
        av.a.f(context, new b());
        navController.navigate(R.id.nav_graph_panel);
    }

    @Override // spi.p8
    public final void b(@NotNull Context context, @NotNull String scheme, @NotNull EnterAppData<IpcSoftware, String> enterAppData, @NotNull NavController navController, @NotNull Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(scheme, "scheme");
        Intrinsics.checkNotNullParameter(enterAppData, "enterAppData");
        Intrinsics.checkNotNullParameter(navController, "navController");
        Intrinsics.checkNotNullParameter(block, "block");
        ct.a("enterAppWork enterNY normalGo ", new Object[0]);
        block.invoke();
    }

    @Override // spi.p8
    public final void c(@NotNull Context context, @NotNull String scheme, @NotNull EnterAppData<IpcSoftware, String> enterAppData, @NotNull NavController navController) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(scheme, "scheme");
        Intrinsics.checkNotNullParameter(enterAppData, "enterAppData");
        Intrinsics.checkNotNullParameter(navController, "navController");
        NavDestination currentDestination = navController.getCurrentDestination();
        if (currentDestination != null && currentDestination.getId() == R.id.startUpProtocolFragment) {
            ct.a("enterAppWork enterYN normalGo noIntervene retIsTrue", new Object[0]);
            return;
        }
        ct.a("enterAppWork enterYN normalGo gearGo", new Object[0]);
        av.a.f(context, new a());
        navController.navigate(cs.a.a());
    }

    @Override // spi.p8
    public final void d(@NotNull Context context, @NotNull String scheme, @NotNull EnterAppData<IpcSoftware, String> enterAppData, @NotNull NavController navController, @NotNull Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(scheme, "scheme");
        Intrinsics.checkNotNullParameter(enterAppData, "enterAppData");
        Intrinsics.checkNotNullParameter(navController, "navController");
        Intrinsics.checkNotNullParameter(block, "block");
        ct.a("enterAppWork enterNN normalGo ", new Object[0]);
        block.invoke();
    }
}
