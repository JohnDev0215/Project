package spi;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.load.engine.GlideException;
import com.byd.overseaappstore.module.ability.R$drawable;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class vd {

    @NotNull
    public static final vd a = new vd();

    public static final class a implements pm<Drawable> {
        public final /* synthetic */ Class<T> a;

        public a(Class<T> cls) {
            this.a = cls;
        }

        /* JADX WARN: Incorrect return type in method signature: (Lcom/bumptech/glide/load/engine/GlideException;Ljava/lang/Object;Lspi/rs<Landroid/graphics/drawable/Drawable;>;Z)Z */
        @Override // spi.pm
        public final void a(@Nullable GlideException glideException, @Nullable rs rsVar) {
            Unit unit;
            String name = this.a.getName();
            if (glideException != null) {
                glideException.printStackTrace();
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            ct.b("GlideUtil listener onLoadFailed :  at class: " + name + "   Exception: " + unit + "   " + (glideException != null ? glideException.getMessage() : null) + "  target: " + rsVar, new Object[0]);
        }

        @Override // spi.pm
        public final void b(Object obj, rs rsVar) {
            ct.a("GlideUtil listener onResourceReady :  at class: " + this.a.getName() + " resource: " + ((Drawable) obj) + "  target: " + rsVar, new Object[0]);
        }
    }

    public static final class b implements pm<Drawable> {
        public final /* synthetic */ Function1<Boolean, Unit> a;
        public final /* synthetic */ Class<T> b;

        /* JADX WARN: Multi-variable type inference failed */
        public b(Function1<? super Boolean, Unit> function1, Class<T> cls) {
            this.a = function1;
            this.b = cls;
        }

        /* JADX WARN: Incorrect return type in method signature: (Lcom/bumptech/glide/load/engine/GlideException;Ljava/lang/Object;Lspi/rs<Landroid/graphics/drawable/Drawable;>;Z)Z */
        @Override // spi.pm
        public final void a(@Nullable GlideException glideException, @Nullable rs rsVar) {
            Unit unit;
            this.a.invoke(Boolean.TRUE);
            String name = this.b.getName();
            if (glideException != null) {
                glideException.printStackTrace();
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            ct.b("GlideUtil listener onLoadFailed :  at class: " + name + "   Exception: " + unit + "   " + (glideException != null ? glideException.getMessage() : null) + "  target: " + rsVar, new Object[0]);
        }

        @Override // spi.pm
        public final void b(Object obj, rs rsVar) {
            ct.a("GlideUtil listener onResourceReady :  at class: " + this.b.getName() + " resource: " + ((Drawable) obj) + "  target: " + rsVar, new Object[0]);
        }
    }

    private vd() {
    }

    public final <T> void a(@NotNull Context context, @Nullable String str, @NotNull ImageView intoImageView, @NotNull Class<T> clazz) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intoImageView, "intoImageView");
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        com.bumptech.glide.a.e(context).o(str).h(vm.a.a(context, R$drawable.bg_app_detial_default_bg)).f(i6.c).A(new a(clazz)).G(intoImageView);
    }

    public final <T> void b(@NotNull Context context, @Nullable String str, @NotNull ImageView intoImageView, @NotNull Class<T> clazz, @NotNull Function1<? super Boolean, Unit> block) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intoImageView, "intoImageView");
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.checkNotNullParameter(block, "block");
        nm<Drawable> nmVarO = com.bumptech.glide.a.e(context).o(str);
        vm vmVar = vm.a;
        int i = R$drawable.bg_app_detial_default_bg;
        nmVarO.h(vmVar.a(context, i)).a(new tm().p(vmVar.a(context, i))).A(new b(block, clazz)).G(intoImageView);
    }
}
