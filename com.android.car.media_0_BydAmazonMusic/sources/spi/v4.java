package spi;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.Size;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.car.ui.R$color;
import java.util.HashSet;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import spi.f6;
import spi.v4.a;

/* JADX INFO: compiled from: ImageBinder.java */
/* JADX INFO: loaded from: classes.dex */
public class v4<T extends a> {
    public final b a;
    public final Size b;

    @Nullable
    public final Consumer<Drawable> c;
    public T d;
    public w4 e;
    public BiConsumer<w4, Drawable> f;
    public ColorDrawable g;

    /* JADX INFO: compiled from: ImageBinder.java */
    public interface a {
    }

    /* JADX INFO: compiled from: ImageBinder.java */
    public enum b {
        NONE,
        FOREGROUND,
        BACKGROUND
    }

    public v4(@NonNull b bVar, @NonNull Size size, @NonNull Consumer<Drawable> consumer) {
        a(bVar, "Need a type");
        this.a = bVar;
        a(size, "Need a size");
        this.b = size;
        a(consumer, "Cannot bind a null consumer");
        this.c = consumer;
    }

    @NonNull
    public static <T> T a(@Nullable T t, @NonNull Object obj) {
        Objects.requireNonNull(t, String.valueOf(obj));
        return t;
    }

    /* JADX WARN: Type inference failed for: r0v12, types: [java.util.HashMap, java.util.Map<spi.w4, spi.f6$b>] */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.util.HashMap, java.util.Map<spi.w4, java.util.HashSet<java.util.function.BiConsumer<spi.w4, android.graphics.drawable.Drawable>>>] */
    /* JADX WARN: Type inference failed for: r4v4, types: [java.util.HashMap, java.util.Map<spi.w4, java.util.HashSet<java.util.function.BiConsumer<spi.w4, android.graphics.drawable.Drawable>>>] */
    public void b(Context context) {
        ColorDrawable colorDrawable = null;
        if (this.e != null) {
            f6 f6VarA = f6.a(context);
            w4 w4Var = this.e;
            BiConsumer<w4, Drawable> biConsumer = this.f;
            HashSet hashSet = (HashSet) f6VarA.c.get(w4Var);
            if (hashSet != null) {
                boolean zRemove = hashSet.remove(biConsumer);
                if (hashSet.isEmpty()) {
                    f6VarA.c.remove(w4Var);
                    f6.b bVar = (f6.b) f6VarA.d.remove(w4Var);
                    if (bVar != null) {
                        bVar.cancel(true);
                        if (f6.h) {
                            StringBuilder sbK = c.k("Canceled task ");
                            sbK.append(w4Var.a);
                            Log.d("LocalImageFetcher", sbK.toString());
                        }
                    } else if (f6.g) {
                        Log.w("LocalImageFetcher", "cancelRequest missing task for: " + w4Var);
                    }
                }
                if (!zRemove && f6.g) {
                    Log.w("LocalImageFetcher", "cancelRequest missing consumer for: " + w4Var);
                }
            } else if (f6.g) {
                Log.w("LocalImageFetcher", "cancelRequest has no consumers for: " + w4Var);
            }
            this.e = null;
            this.f = null;
        }
        if (this.a != b.NONE) {
            if (this.g == null) {
                this.g = new ColorDrawable(context.getColor(R$color.loading_image_placeholder_color));
            }
            colorDrawable = this.g;
        }
        c(colorDrawable);
    }

    public void c(@Nullable Drawable drawable) {
        Consumer<Drawable> consumer = this.c;
        if (consumer != null) {
            consumer.accept(drawable);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x004e  */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void d(final android.content.Context r8, @androidx.annotation.Nullable T r9) {
        /*
            Method dump skipped, instruction units count: 360
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.v4.d(android.content.Context, spi.v4$a):void");
    }

    public v4(@NonNull b bVar, @NonNull Size size) {
        a(bVar, "Need a type");
        this.a = bVar;
        a(size, "Need a size");
        this.b = size;
        this.c = null;
    }
}
