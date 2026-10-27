package spi;

import android.app.UiStyleConfig;
import android.content.Context;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import spi.qg;

/* JADX INFO: loaded from: classes.dex */
public final class xu implements yu {
    @Override // spi.yu
    public final boolean a(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Objects.requireNonNull(av.a);
        Intrinsics.checkNotNullParameter(context, "context");
        return UiStyleConfig.getInstance(context).isDynasty();
    }

    @Override // spi.yu
    public final void b(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Objects.requireNonNull(av.a);
        Intrinsics.checkNotNullParameter(context, "context");
        r3 r3Var = r3.a;
        String str = "";
        if (r3Var.e() || r3Var.h()) {
            av.b = "";
        }
        if (r3Var.d() || r3Var.f() || r3Var.g() || r3Var.i()) {
            Intrinsics.checkNotNullParameter(context, "context");
            int uiStyle = UiStyleConfig.getInstance(context).getUiStyle();
            if (uiStyle != 0 && uiStyle == 1) {
                str = "_denza";
            }
            av.b = str;
        }
        ct.a(d.f("UIStyle initSkinSourceSuffix: ", av.b), new Object[0]);
    }

    @Override // spi.yu
    public final int c(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Objects.requireNonNull(av.a);
        Intrinsics.checkNotNullParameter(context, "context");
        return UiStyleConfig.getInstance(context).getUiStyle();
    }

    @Override // spi.yu
    public final int d(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return av.a.c(context);
    }

    @Override // spi.yu
    public final boolean e(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return av.a.d(context);
    }

    @Override // spi.yu
    public final void f() {
        Objects.requireNonNull(av.a);
        Objects.requireNonNull(qg.a.a);
    }

    @Override // spi.yu
    @NotNull
    public final String g(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return av.a.b(context);
    }

    @Override // spi.yu
    @NotNull
    public final String getCountryCode() {
        return av.a.a();
    }
}
