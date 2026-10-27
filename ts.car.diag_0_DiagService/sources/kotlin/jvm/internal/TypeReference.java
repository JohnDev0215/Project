package kotlin.jvm.internal;

import java.lang.annotation.Annotation;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.SinceKotlin;
import kotlin.collections.w;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.KVariance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.4")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0006\u001a\u00020\u0002*\u00020\u0005H\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0010\u0010\u0004R\u001c\u0010\u0011\u001a\u00020\n8\u0016@\u0000X\u0096\u0004¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0006\u001a\u00020\u00158\u0016@\u0000X\u0096\u0004¢\u0006\f\n\u0004\b\u0006\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u001c\u001a\u00020\u00198\u0016@\u0000X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u001c\u0010\u001d\u001a\u00020\u00198\u0016@\u0000X\u0096\u0004¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001d\u0010\u001bR\u001a\u0010\u0011\u001a\u00020\u0002*\u00020\u001f8\u0002@\u0000X\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010 ¨\u0006!"}, d2 = {"Lkotlin/jvm/internal/TypeReference;", "Lkotlin/reflect/o;", "Lkotlin/String;", "b", "()Ljava/lang/String;", "Lkotlin/reflect/p;", "c", "(Lkotlin/reflect/p;)Ljava/lang/String;", "Lkotlin/Any;", "other", "Lkotlin/Boolean;", "equals", "(Ljava/lang/Object;)Z", "Lkotlin/Int;", "hashCode", "()I", "toString", "e", "Z", "g", "()Z", "Lkotlin/reflect/d;", "Lkotlin/reflect/d;", "f", "()Lkotlin/reflect/d;", "Ljava/util/List;", "getAnnotations", "()Ljava/util/List;", "annotations", "d", "Ljava/util/List;", "Ljava/lang/Class;", "(Ljava/lang/Class;)Ljava/lang/String;", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
public final class TypeReference implements kotlin.reflect.o {

    @NotNull
    private final kotlin.reflect.d c;

    @NotNull
    private final List<KTypeProjection> d;
    private final boolean e;

    private final String b() {
        kotlin.reflect.d c2 = getC();
        if (!(c2 instanceof kotlin.reflect.c)) {
            c2 = null;
        }
        kotlin.reflect.c cVar = (kotlin.reflect.c) c2;
        Class<?> clsA = cVar != null ? kotlin.jvm.a.a(cVar) : null;
        return (clsA == null ? getC().toString() : clsA.isArray() ? e(clsA) : clsA.getName()) + (d().isEmpty() ? "" : w.g(d(), ", ", "<", ">", 0, null, new kotlin.jvm.b.l<KTypeProjection, String>() { // from class: kotlin.jvm.internal.TypeReference$asString$args$1
            {
                super(1);
            }

            @Override // kotlin.jvm.b.l
            @NotNull
            public final String invoke(@NotNull KTypeProjection kTypeProjection) {
                q.c(kTypeProjection, "it");
                return this.this$0.c(kTypeProjection);
            }
        }, 24, null)) + (getE() ? "?" : "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String c(@NotNull KTypeProjection kTypeProjection) {
        String strValueOf;
        if (kTypeProjection.getVariance() == null) {
            return "*";
        }
        kotlin.reflect.o type = kTypeProjection.getType();
        if (!(type instanceof TypeReference)) {
            type = null;
        }
        TypeReference typeReference = (TypeReference) type;
        if (typeReference == null || (strValueOf = typeReference.b()) == null) {
            strValueOf = String.valueOf(kTypeProjection.getType());
        }
        KVariance variance = kTypeProjection.getVariance();
        if (variance != null) {
            int i = v.f1519a[variance.ordinal()];
            if (i == 1) {
                return strValueOf;
            }
            if (i == 2) {
                return "in " + strValueOf;
            }
            if (i == 3) {
                return "out " + strValueOf;
            }
        }
        throw new NoWhenBranchMatchedException();
    }

    private final String e(@NotNull Class<?> cls) {
        return q.a(cls, boolean[].class) ? "kotlin.BooleanArray" : q.a(cls, char[].class) ? "kotlin.CharArray" : q.a(cls, byte[].class) ? "kotlin.ByteArray" : q.a(cls, short[].class) ? "kotlin.ShortArray" : q.a(cls, int[].class) ? "kotlin.IntArray" : q.a(cls, float[].class) ? "kotlin.FloatArray" : q.a(cls, long[].class) ? "kotlin.LongArray" : q.a(cls, double[].class) ? "kotlin.DoubleArray" : "kotlin.Array";
    }

    @NotNull
    public List<KTypeProjection> d() {
        return this.d;
    }

    public boolean equals(@Nullable Object other) {
        if (other instanceof TypeReference) {
            TypeReference typeReference = (TypeReference) other;
            if (q.a(getC(), typeReference.getC()) && q.a(d(), typeReference.d()) && getE() == typeReference.getE()) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    /* JADX INFO: renamed from: f, reason: from getter */
    public kotlin.reflect.d getC() {
        return this.c;
    }

    /* JADX INFO: renamed from: g, reason: from getter */
    public boolean getE() {
        return this.e;
    }

    @Override // kotlin.reflect.a
    @NotNull
    public List<Annotation> getAnnotations() {
        return kotlin.collections.o.b();
    }

    public int hashCode() {
        return (((getC().hashCode() * 31) + d().hashCode()) * 31) + Boolean.valueOf(getE()).hashCode();
    }

    @NotNull
    public String toString() {
        return b() + " (Kotlin reflection is not available)";
    }
}
