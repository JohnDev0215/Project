package kotlinx.metadata;

import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: extensions.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0017\u0012\u000e\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\t¢\u0006\u0004\b\f\u0010\rJ\u0013\u0010\u0004\u001a\u00020\u00032\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0016R\u001c\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lkotlinx/metadata/KmExtensionType;", "", "other", "", "equals", "", "hashCode", "", "toString", "Lkotlin/reflect/KClass;", "klass", "Lkotlin/reflect/KClass;", "<init>", "(Lkotlin/reflect/KClass;)V", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1})
public final class KmExtensionType {
    private final KClass<Object> klass;

    public KmExtensionType(KClass<Object> klass) {
        Intrinsics.checkNotNullParameter(klass, "klass");
        this.klass = klass;
    }

    public boolean equals(Object other) {
        return (other instanceof KmExtensionType) && Intrinsics.areEqual(this.klass, ((KmExtensionType) other).klass);
    }

    public int hashCode() {
        return this.klass.hashCode();
    }

    public String toString() {
        String name = JvmClassMappingKt.getJavaClass((KClass) this.klass).getName();
        Intrinsics.checkNotNullExpressionValue(name, "klass.java.name");
        return name;
    }
}
