package a.b.a.a.h;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.netinsight.sye.syeClient.SyeAPIConfig;
import java.util.UUID;
import kotlin.TypeCastException;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0012a f68a = new C0012a();

    @JvmField
    public final String b;

    @JvmField
    public final int d;

    @JvmField
    public final String e = b();

    @JvmField
    public final String f = a();

    @JvmField
    public final String c = c();

    /* JADX INFO: renamed from: a.b.a.a.h.a$a, reason: collision with other inner class name */
    public static final class C0012a {
    }

    public a(Context context) {
        this.b = a(context);
        d();
        this.d = Build.VERSION.SDK_INT;
        b(context);
    }

    public final String a() {
        String str = Build.MANUFACTURER;
        Intrinsics.checkExpressionValueIsNotNull(str, "Build.MANUFACTURER");
        return StringsKt__StringsJVMKt.capitalize(StringsKt__StringsJVMKt.replace$default(str, " ", Attributes.PREDEFINED_ATTRIBUTE_PREFIX, false, 4, (Object) null));
    }

    public final String a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("SyePreferences", 0);
        SyeAPIConfig.INSTANCE.getClass();
        String string = SyeAPIConfig.f729a.getString(Splash.PARAMS_DEVICE_ID);
        if (string == null) {
            string = sharedPreferences.getString(Splash.PARAMS_DEVICE_ID, UUID.randomUUID().toString());
            if (string == null) {
                Intrinsics.throwNpe();
            }
            sharedPreferences.edit().putString(Splash.PARAMS_DEVICE_ID, string).apply();
            Intrinsics.checkExpressionValueIsNotNull(string, "preferences\n            …is).apply()\n            }");
        }
        return string;
    }

    public final String b() {
        String str = Build.MANUFACTURER;
        Intrinsics.checkExpressionValueIsNotNull(str, "Build.MANUFACTURER");
        String strCapitalize = StringsKt__StringsJVMKt.capitalize(str);
        String model = Build.MODEL;
        Intrinsics.checkExpressionValueIsNotNull(model, "model");
        if (StringsKt__StringsJVMKt.startsWith$default(model, strCapitalize, false, 2, null) && strCapitalize.length() <= model.length()) {
            model = model.substring(strCapitalize.length());
            Intrinsics.checkExpressionValueIsNotNull(model, "(this as java.lang.String).substring(startIndex)");
            if (StringsKt__StringsJVMKt.startsWith$default(model, Attributes.PREDEFINED_ATTRIBUTE_PREFIX, false, 2, null) || StringsKt__StringsJVMKt.startsWith$default(model, " ", false, 2, null)) {
                if (model == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                model = model.substring(1);
                Intrinsics.checkExpressionValueIsNotNull(model, "(this as java.lang.String).substring(startIndex)");
            }
        }
        Intrinsics.checkExpressionValueIsNotNull(model, "model");
        return StringsKt__StringsJVMKt.capitalize(StringsKt__StringsJVMKt.replace$default(model, " ", Attributes.PREDEFINED_ATTRIBUTE_PREFIX, false, 4, (Object) null));
    }

    public final boolean b(Context context) {
        return !context.getPackageManager().hasSystemFeature("android.hardware.touchscreen");
    }

    public final String c() {
        return String.valueOf(Build.VERSION.SDK_INT);
    }

    public final int d() {
        return Build.VERSION.SDK_INT;
    }
}
