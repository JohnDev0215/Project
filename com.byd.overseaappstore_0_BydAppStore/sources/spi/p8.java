package spi;

import android.content.Context;
import androidx.navigation.NavController;
import com.byd.overseaappstore.module.ability.util.ipc.EnterAppData;
import com.byd.overseaappstore.module.ability.util.ipc.IpcSoftware;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
public interface p8 {
    void a(@NotNull Context context, @NotNull String str, @NotNull EnterAppData<IpcSoftware, String> enterAppData, @NotNull NavController navController, @NotNull Function0<Unit> function0);

    void b(@NotNull Context context, @NotNull String str, @NotNull EnterAppData<IpcSoftware, String> enterAppData, @NotNull NavController navController, @NotNull Function0<Unit> function0);

    void c(@NotNull Context context, @NotNull String str, @NotNull EnterAppData<IpcSoftware, String> enterAppData, @NotNull NavController navController);

    void d(@NotNull Context context, @NotNull String str, @NotNull EnterAppData<IpcSoftware, String> enterAppData, @NotNull NavController navController, @NotNull Function0<Unit> function0);
}
