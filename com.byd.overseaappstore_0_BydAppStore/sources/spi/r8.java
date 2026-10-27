package spi;

import android.content.Context;
import android.os.Bundle;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.module.ability.util.ipc.EnterAppData;
import com.byd.overseaappstore.module.ability.util.ipc.IpcSoftware;
import com.byd.overseaappstore.module.ability.util.notify.router.ExecutorDialogRouterMgr;
import com.byd.overseaappstore.module.ability.util.notify.router.gear.NotPGearState;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
public final class r8 implements p8 {

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
            r8.this.a.addBizTypeAndData(4, new NotPGearState(zBooleanValue)).workNotify();
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
            r8.this.a.addBizTypeAndData(4, new NotPGearState(zBooleanValue)).workNotify();
            return Unit.INSTANCE;
        }
    }

    public static final class c extends Lambda implements Function2<Boolean, Integer, Unit> {
        public c() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Unit mo6invoke(Boolean bool, Integer num) {
            boolean zBooleanValue = bool.booleanValue();
            num.intValue();
            r8.this.a.addBizTypeAndData(4, new NotPGearState(zBooleanValue)).workNotify();
            return Unit.INSTANCE;
        }
    }

    public static final class d extends Lambda implements Function2<Boolean, Integer, Unit> {
        public d() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Unit mo6invoke(Boolean bool, Integer num) {
            boolean zBooleanValue = bool.booleanValue();
            num.intValue();
            r8.this.a.addBizTypeAndData(4, new NotPGearState(zBooleanValue)).workNotify();
            return Unit.INSTANCE;
        }
    }

    public r8(@NotNull ExecutorDialogRouterMgr executorDialogRouterMgr) {
        Intrinsics.checkNotNullParameter(executorDialogRouterMgr, "executorDialogRouterMgr");
        this.a = executorDialogRouterMgr;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // spi.p8
    public final void a(@NotNull Context context, @NotNull String scheme, @NotNull EnterAppData<IpcSoftware, String> enterAppData, @NotNull NavController navController, @NotNull Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(scheme, "scheme");
        Intrinsics.checkNotNullParameter(enterAppData, "enterAppData");
        Intrinsics.checkNotNullParameter(navController, "navController");
        Intrinsics.checkNotNullParameter(block, "block");
        IpcSoftware data = enterAppData.getData();
        String state = enterAppData.getState();
        if (data == null) {
            block.invoke();
            ct.b("enterAppWork enterYY outerVoiceGo  ipcSoftwareIsNull", new Object[0]);
        }
        Bundle bundle = new Bundle();
        bundle.putInt("software_id", data.getId());
        switch (state.hashCode()) {
            case -1127191311:
                if (!state.equals("ACTIVITY_DEFAULT")) {
                }
                ct.a("enterAppWork enterYY outerVoiceGo  ACTIVITY_DEFAULT or ACTIVITY_DESTROY", new Object[0]);
                block.invoke();
                av.a.f(context, new d());
                navController.navigate(R.id.nav_graph_panel, bundle);
                break;
            case -1114622294:
                if (!state.equals("ACTIVITY_DESTROY")) {
                }
                ct.a("enterAppWork enterYY outerVoiceGo  ACTIVITY_DEFAULT or ACTIVITY_DESTROY", new Object[0]);
                block.invoke();
                av.a.f(context, new d());
                navController.navigate(R.id.nav_graph_panel, bundle);
                break;
            case 87758685:
                if (state.equals("ACTIVITY_RESUME")) {
                    ct.a("enterAppWork enterYY outerVoiceGo ACTIVITY_RESUME ", new Object[0]);
                    navController.navigate(R.id.nav_graph_panel, bundle);
                    break;
                }
                break;
            case 2109105175:
                if (state.equals("ACTIVITY_BACK")) {
                    ct.a("enterAppWork enterYY outerVoiceGo ACTIVITY_BACK ", new Object[0]);
                    navController.navigate(R.id.nav_graph_panel, bundle);
                    break;
                }
                break;
        }
    }

    @Override // spi.p8
    public final void b(@NotNull Context context, @NotNull String scheme, @NotNull EnterAppData<IpcSoftware, String> enterAppData, @NotNull NavController navController, @NotNull Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(scheme, "scheme");
        Intrinsics.checkNotNullParameter(enterAppData, "enterAppData");
        Intrinsics.checkNotNullParameter(navController, "navController");
        Intrinsics.checkNotNullParameter(block, "block");
        ct.a("enterAppWork enterYN outerVoiceGo", new Object[0]);
        block.invoke();
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // spi.p8
    public final void c(@NotNull Context context, @NotNull String scheme, @NotNull EnterAppData<IpcSoftware, String> enterAppData, @NotNull NavController navController) {
        av avVar;
        Function2<? super Boolean, ? super Integer, Unit> aVar;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(scheme, "scheme");
        Intrinsics.checkNotNullParameter(enterAppData, "enterAppData");
        Intrinsics.checkNotNullParameter(navController, "navController");
        IpcSoftware data = enterAppData.getData();
        String state = enterAppData.getState();
        if (data == null) {
            ct.b("enterAppWork enterYN outerVoiceGo ipcSoftwareIsNull", new Object[0]);
        }
        switch (state.hashCode()) {
            case -1127191311:
                if (!state.equals("ACTIVITY_DEFAULT")) {
                }
                ct.a("enterAppWork enterYN outerVoiceGo gearGo ACTIVITY_DEFAULT or ACTIVITY_DESTROY ", new Object[0]);
                avVar = av.a;
                aVar = new a();
                avVar.f(context, aVar);
                navController.navigate(cs.a.a());
                break;
            case -1114622294:
                if (!state.equals("ACTIVITY_DESTROY")) {
                }
                ct.a("enterAppWork enterYN outerVoiceGo gearGo ACTIVITY_DEFAULT or ACTIVITY_DESTROY ", new Object[0]);
                avVar = av.a;
                aVar = new a();
                avVar.f(context, aVar);
                navController.navigate(cs.a.a());
                break;
            case 87758685:
                if (state.equals("ACTIVITY_RESUME")) {
                    NavDestination currentDestination = navController.getCurrentDestination();
                    if (!(currentDestination != null && currentDestination.getId() == R.id.startUpProtocolFragment)) {
                        ct.a("enterAppWork enterYN outerVoiceGo gearGo ACTIVITY_RESUME", new Object[0]);
                        avVar = av.a;
                        aVar = new b();
                        avVar.f(context, aVar);
                        navController.navigate(cs.a.a());
                    } else {
                        ct.a("enterAppWork enterYN outerVoiceGo noIntervene ACTIVITY_RESUME ", new Object[0]);
                    }
                    break;
                }
                break;
            case 2109105175:
                if (state.equals("ACTIVITY_BACK")) {
                    NavDestination currentDestination2 = navController.getCurrentDestination();
                    if (!(currentDestination2 != null && currentDestination2.getId() == R.id.startUpProtocolFragment)) {
                        ct.a("enterAppWork enterYN outerVoiceGo gearGo ACTIVITY_BACK", new Object[0]);
                        avVar = av.a;
                        aVar = new c();
                        avVar.f(context, aVar);
                        navController.navigate(cs.a.a());
                    } else {
                        ct.a("enterAppWork enterYN outerVoiceGo noIntervene ACTIVITY_BACK ", new Object[0]);
                    }
                    break;
                }
                break;
        }
    }

    @Override // spi.p8
    public final void d(@NotNull Context context, @NotNull String scheme, @NotNull EnterAppData<IpcSoftware, String> enterAppData, @NotNull NavController navController, @NotNull Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(scheme, "scheme");
        Intrinsics.checkNotNullParameter(enterAppData, "enterAppData");
        Intrinsics.checkNotNullParameter(navController, "navController");
        Intrinsics.checkNotNullParameter(block, "block");
        ct.a("enterAppWork enterNN outerVoiceGo", new Object[0]);
        block.invoke();
    }
}
