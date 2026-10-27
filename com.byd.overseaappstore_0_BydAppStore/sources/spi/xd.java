package spi;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.engine.GlideException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class xd implements pm<Drawable> {
    public final /* synthetic */ Function1<Boolean, Unit> a;
    public final /* synthetic */ Class<Object> b;

    /* JADX WARN: Multi-variable type inference failed */
    public xd(Function1<? super Boolean, Unit> function1, Class<Object> cls) {
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
