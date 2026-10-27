package spi;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import androidx.appcompat.content.res.AppCompatResources;
import java.util.Arrays;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class vm {

    @NotNull
    public static final vm a = new vm();

    private vm() {
    }

    public final int a(@NotNull Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        r3 r3Var = r3.a;
        if (r3Var.e() || r3Var.d() || r3Var.f() || r3Var.h() || r3Var.g() || r3Var.i()) {
            String resourceEntryName = context.getResources().getResourceEntryName(i);
            Intrinsics.checkNotNullExpressionValue(resourceEntryName, "context.resources.getResourceEntryName(sourceId)");
            String resourceTypeName = context.getResources().getResourceTypeName(i);
            Intrinsics.checkNotNullExpressionValue(resourceTypeName, "context.resources.getResourceTypeName(sourceId)");
            String packageName = context.getPackageName();
            Intrinsics.checkNotNullExpressionValue(packageName, "context.packageName");
            av avVar = av.a;
            Objects.requireNonNull(avVar);
            if (!TextUtils.isEmpty(av.b)) {
                Objects.requireNonNull(avVar);
                resourceEntryName = d.f(resourceEntryName, av.b);
            }
            StringBuilder sbL = d.l("UIStyle generateStyleSourceId resName: ", resourceEntryName, ", resourceTypeName: ", resourceTypeName, ", packageName: ");
            sbL.append(packageName);
            ct.a(sbL.toString(), new Object[0]);
            int identifier = context.getResources().getIdentifier(resourceEntryName, resourceTypeName, packageName);
            if (identifier != 0) {
                i = identifier;
            }
            ct.a(e.c("UIStyle generateStyleSourceId resId: ", i), new Object[0]);
        }
        return i;
    }

    public final int b(@NotNull Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        return context.getColor(i);
    }

    @Nullable
    public final Drawable c(@NotNull Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        return AppCompatResources.getDrawable(context, a(context, i));
    }

    public final int d(@NotNull Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        return context.getColor(i);
    }

    @NotNull
    public final String e(@NotNull Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = context.getString(i);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(resId)");
        return string;
    }

    @NotNull
    public final String f(@NotNull Context context, int i, @NotNull Object... args) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(args, "args");
        String string = context.getString(i, Arrays.copyOf(args, args.length));
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(resId, *args)");
        return string;
    }
}
