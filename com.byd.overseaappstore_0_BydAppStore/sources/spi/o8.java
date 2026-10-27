package spi;

import android.content.Context;
import androidx.navigation.NavController;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.module.ability.util.ipc.EnterAppData;
import com.byd.overseaappstore.module.ability.util.ipc.IpcSoftware;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
public final class o8 implements p8 {

    @NotNull
    public final p8 a;

    @NotNull
    public final p8 b;

    public o8(@NotNull p8 enterAppWorkNormal, @NotNull p8 enterAppWorkVoice) {
        Intrinsics.checkNotNullParameter(enterAppWorkNormal, "enterAppWorkNormal");
        Intrinsics.checkNotNullParameter(enterAppWorkVoice, "enterAppWorkVoice");
        this.a = enterAppWorkNormal;
        this.b = enterAppWorkVoice;
    }

    @Override // spi.p8
    public final void a(@NotNull Context context, @NotNull String scheme, @NotNull EnterAppData<IpcSoftware, String> enterAppData, @NotNull NavController navController, @NotNull Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(scheme, "scheme");
        Intrinsics.checkNotNullParameter(enterAppData, "enterAppData");
        Intrinsics.checkNotNullParameter(navController, "navController");
        Intrinsics.checkNotNullParameter(block, "block");
        e(context, scheme).a(context, scheme, enterAppData, navController, block);
    }

    @Override // spi.p8
    public final void b(@NotNull Context context, @NotNull String scheme, @NotNull EnterAppData<IpcSoftware, String> enterAppData, @NotNull NavController navController, @NotNull Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(scheme, "scheme");
        Intrinsics.checkNotNullParameter(enterAppData, "enterAppData");
        Intrinsics.checkNotNullParameter(navController, "navController");
        Intrinsics.checkNotNullParameter(block, "block");
        e(context, scheme).b(context, scheme, enterAppData, navController, block);
    }

    @Override // spi.p8
    public final void c(@NotNull Context context, @NotNull String scheme, @NotNull EnterAppData<IpcSoftware, String> enterAppData, @NotNull NavController navController) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(scheme, "scheme");
        Intrinsics.checkNotNullParameter(enterAppData, "enterAppData");
        Intrinsics.checkNotNullParameter(navController, "navController");
        e(context, scheme).c(context, scheme, enterAppData, navController);
    }

    @Override // spi.p8
    public final void d(@NotNull Context context, @NotNull String scheme, @NotNull EnterAppData<IpcSoftware, String> enterAppData, @NotNull NavController navController, @NotNull Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(scheme, "scheme");
        Intrinsics.checkNotNullParameter(enterAppData, "enterAppData");
        Intrinsics.checkNotNullParameter(navController, "navController");
        Intrinsics.checkNotNullParameter(block, "block");
        e(context, scheme).d(context, scheme, enterAppData, navController, block);
    }

    public final p8 e(Context context, String str) {
        p8 p8Var = Intrinsics.areEqual(str, vm.a.e(context, R.string.scheme_voice_to_store)) ? this.b : this.a;
        ct.a(d.f("enterAppWork getEnterWork scheme:", str), new Object[0]);
        return p8Var;
    }
}
