package spi;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
public final class r3 {

    @NotNull
    public static final r3 a = new r3();

    private r3() {
    }

    public final boolean a() {
        return Intrinsics.areEqual("flavor_8155f_pro", "flavor_6125");
    }

    public final boolean b() {
        return Intrinsics.areEqual("flavor_8155f_pro", "flavor_6125_dev");
    }

    public final boolean c() {
        return Intrinsics.areEqual("flavor_8155f_pro", "flavor_6125f_dev");
    }

    public final boolean d() {
        return Intrinsics.areEqual("flavor_8155f_pro", "flavor_8155f_master");
    }

    public final boolean e() {
        return Intrinsics.areEqual("flavor_8155f_pro", "flavor_8155f_master_dev");
    }

    public final boolean f() {
        return Intrinsics.areEqual("flavor_8155f_pro", "flavor_8155f_pro");
    }

    public final boolean g() {
        return Intrinsics.areEqual("flavor_8155f_pro", "flavor_d100f_self_master");
    }

    public final boolean h() {
        return Intrinsics.areEqual("flavor_8155f_pro", "flavor_d100f_self_master_dev");
    }

    public final boolean i() {
        return Intrinsics.areEqual("flavor_8155f_pro", "flavor_d100f_self_pro");
    }
}
