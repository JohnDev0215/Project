package androidx.core.graphics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.content.res.e;
import androidx.core.provider.b;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"NewApi"})
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final j f487a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final b.a.f<String, Typeface> f488b;

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            f487a = new i();
        } else if (i >= 28) {
            f487a = new h();
        } else if (i >= 26) {
            f487a = new g();
        } else if (i >= 24 && f.m()) {
            f487a = new f();
        } else if (i >= 21) {
            f487a = new e();
        } else {
            f487a = new j();
        }
        f488b = new b.a.f<>(16);
    }

    @NonNull
    public static Typeface a(@NonNull Context context, @Nullable Typeface typeface, int i) {
        Typeface typefaceG;
        if (context != null) {
            return (Build.VERSION.SDK_INT >= 21 || (typefaceG = g(context, typeface, i)) == null) ? Typeface.create(typeface, i) : typefaceG;
        }
        throw new IllegalArgumentException("Context cannot be null");
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static Typeface b(@NonNull Context context, @Nullable CancellationSignal cancellationSignal, @NonNull b.f[] fVarArr, int i) {
        return f487a.c(context, cancellationSignal, fVarArr, i);
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static Typeface c(@NonNull Context context, @NonNull FontResourcesParserCompat.a aVar, @NonNull Resources resources, int i, int i2, @Nullable e.b bVar, @Nullable Handler handler, boolean z) {
        Typeface typefaceB;
        if (aVar instanceof FontResourcesParserCompat.d) {
            FontResourcesParserCompat.d dVar = (FontResourcesParserCompat.d) aVar;
            boolean z2 = false;
            if (!z ? bVar == null : dVar.a() == 0) {
                z2 = true;
            }
            typefaceB = androidx.core.provider.b.f(context, dVar.b(), bVar, handler, z2, z ? dVar.c() : -1, i2);
        } else {
            typefaceB = f487a.b(context, (FontResourcesParserCompat.b) aVar, resources, i2);
            if (bVar != null) {
                if (typefaceB != null) {
                    bVar.b(typefaceB, handler);
                } else {
                    bVar.a(-3, handler);
                }
            }
        }
        if (typefaceB != null) {
            f488b.d(e(resources, i, i2), typefaceB);
        }
        return typefaceB;
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static Typeface d(@NonNull Context context, @NonNull Resources resources, int i, String str, int i2) {
        Typeface typefaceE = f487a.e(context, resources, i, str, i2);
        if (typefaceE != null) {
            f488b.d(e(resources, i, i2), typefaceE);
        }
        return typefaceE;
    }

    private static String e(Resources resources, int i, int i2) {
        return resources.getResourcePackageName(i) + "-" + i + "-" + i2;
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static Typeface f(@NonNull Resources resources, int i, int i2) {
        return f488b.c(e(resources, i, i2));
    }

    @Nullable
    private static Typeface g(Context context, Typeface typeface, int i) {
        FontResourcesParserCompat.b bVarI = f487a.i(typeface);
        if (bVarI == null) {
            return null;
        }
        return f487a.b(context, bVarI, context.getResources(), i);
    }
}
