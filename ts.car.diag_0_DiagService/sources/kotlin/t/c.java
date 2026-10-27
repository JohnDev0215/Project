package kotlin.t;

import kotlin.Metadata;
import kotlin.jvm.internal.o;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX WARN: Unexpected interfaces in signature: [java.lang.Object<java.lang.Integer>] */
/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public final class c extends kotlin.t.a {
    public static final a h = new a(null);

    @NotNull
    private static final c g = new c(1, 0);

    /* JADX INFO: compiled from: caradapter-bug */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u0000:\u0001\u0002B\t\b\u0012¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0004\u0010\bR\u0013\u0010\u0002\u001a\u00020\u00018\u0006@\u0000¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003¨\u0006\t"}, d2 = {"Lkotlin/t/c$a;", "Lkotlin/t/c;", "a", "()Lkotlin/t/c;", "<init>", "()V", "Lkotlin/jvm/internal/o;", "p0", "(Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
    public static final class a {
        private a() {
        }

        @NotNull
        public final c a() {
            return c.g;
        }

        public /* synthetic */ a(o oVar) {
            this();
        }
    }

    public c(int i, int i2) {
        super(i, i2, 1);
    }

    @Override // kotlin.t.a
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof c) {
            if (!isEmpty() || !((c) obj).isEmpty()) {
                c cVar = (c) obj;
                if (a() != cVar.a() || b() != cVar.b()) {
                }
            }
            return true;
        }
        return false;
    }

    @NotNull
    public Integer f() {
        return Integer.valueOf(b());
    }

    @NotNull
    public Integer g() {
        return Integer.valueOf(a());
    }

    @Override // kotlin.t.a
    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return b() + (a() * 31);
    }

    @Override // kotlin.t.a
    public boolean isEmpty() {
        return a() > b();
    }

    @Override // kotlin.t.a
    @NotNull
    public String toString() {
        return a() + ".." + b();
    }
}
