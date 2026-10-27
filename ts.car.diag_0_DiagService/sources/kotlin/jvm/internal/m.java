package kotlin.jvm.internal;

import java.lang.annotation.Annotation;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.KotlinReflectionNotSupportedError;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0011\b\u0016\u0012\u0006\u0010\u001a\u001a\u00020\u0015¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u0004\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0014\u001a\u00020\u00118\u0016@\u0000X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0016\u001a\u00020\u00158\u0016@\u0000X\u0096\u0004¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001d"}, d2 = {"Lkotlin/jvm/internal/m;", "Lkotlin/reflect/c;", "Lkotlin/jvm/internal/l;", "Lkotlin/Nothing;", "b", "()Ljava/lang/Void;", "Lkotlin/Any;", "other", "Lkotlin/Boolean;", "equals", "(Ljava/lang/Object;)Z", "Lkotlin/Int;", "hashCode", "()I", "Lkotlin/String;", "toString", "()Ljava/lang/String;", "Ljava/util/List;", "getAnnotations", "()Ljava/util/List;", "annotations", "Ljava/lang/Class;", "c", "Ljava/lang/Class;", "a", "()Ljava/lang/Class;", "p0", "<init>", "(Ljava/lang/Class;)V", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
public final class m implements kotlin.reflect.c<Object>, l {

    @NotNull
    private final Class<?> c;

    public m(@NotNull Class<?> cls) {
        q.c(cls, "jClass");
        this.c = cls;
    }

    private final Void b() {
        throw new KotlinReflectionNotSupportedError();
    }

    @Override // kotlin.jvm.internal.l
    @NotNull
    public Class<?> a() {
        return this.c;
    }

    public boolean equals(@Nullable Object other) {
        return (other instanceof m) && q.a(kotlin.jvm.a.b(this), kotlin.jvm.a.b((kotlin.reflect.c) other));
    }

    @Override // kotlin.reflect.a
    @NotNull
    public List<Annotation> getAnnotations() {
        b();
        throw null;
    }

    public int hashCode() {
        return kotlin.jvm.a.b(this).hashCode();
    }

    @NotNull
    public String toString() {
        return a().toString() + " (Kotlin reflection is not available)";
    }
}
