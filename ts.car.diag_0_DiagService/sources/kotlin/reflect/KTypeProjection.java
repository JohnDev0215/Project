package kotlin.reflect;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.q;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: renamed from: kotlin.reflect.p, reason: from toString */
/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.1")
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \u0014J\u000f\u0010\u0002\u001a\u00020\u0001H\u0016¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\n\u0010\u000bR\u0019\u0010\r\u001a\u00020\f8\u0006@\u0000¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\u000f\u001a\u00020\u00118\u0006@\u0000¢\u0006\f\n\u0004\b\u000f\u0010\u0012\u001a\u0004\b\r\u0010\u0013¨\u0006\u0015"}, d2 = {"Lkotlin/reflect/p;", "Lkotlin/String;", "toString", "()Ljava/lang/String;", "Lkotlin/Int;", "hashCode", "()I", "Lkotlin/Any;", "other", "Lkotlin/Boolean;", "equals", "(Ljava/lang/Object;)Z", "Lkotlin/reflect/KVariance;", "a", "Lkotlin/reflect/KVariance;", "b", "()Lkotlin/reflect/KVariance;", "Lkotlin/reflect/o;", "Lkotlin/reflect/o;", "()Lkotlin/reflect/o;", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
public final /* data */ class KTypeProjection {

    /* JADX INFO: renamed from: a, reason: collision with root package name and from kotlin metadata and from toString */
    @Nullable
    private final KVariance variance;

    /* JADX INFO: renamed from: b, reason: collision with root package name and from kotlin metadata and from toString */
    @Nullable
    private final o type;

    @Nullable
    /* JADX INFO: renamed from: a, reason: from getter */
    public final o getType() {
        return this.type;
    }

    @Nullable
    /* JADX INFO: renamed from: b, reason: from getter */
    public final KVariance getVariance() {
        return this.variance;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KTypeProjection)) {
            return false;
        }
        KTypeProjection kTypeProjection = (KTypeProjection) other;
        return q.a(this.variance, kTypeProjection.variance) && q.a(this.type, kTypeProjection.type);
    }

    public int hashCode() {
        KVariance kVariance = this.variance;
        int iHashCode = (kVariance != null ? kVariance.hashCode() : 0) * 31;
        o oVar = this.type;
        return iHashCode + (oVar != null ? oVar.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "KTypeProjection(variance=" + this.variance + ", type=" + this.type + ")";
    }
}
