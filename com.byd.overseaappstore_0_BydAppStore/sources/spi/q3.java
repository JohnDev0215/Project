package spi;

import java.lang.reflect.Method;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class q3 {
    public static final a d = new a(null);
    public final Method a;
    public final Method b;
    public final Method c;

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public q3(@Nullable Method method, @Nullable Method method2, @Nullable Method method3) {
        this.a = method;
        this.b = method2;
        this.c = method3;
    }
}
