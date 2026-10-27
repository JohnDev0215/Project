package dagger.spi.shaded.androidx.room.compiler.processing.ksp;

import com.google.devtools.ksp.processing.Resolver;
import com.google.devtools.ksp.symbol.KSClassDeclaration;
import com.google.devtools.ksp.symbol.KSType;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ResolverExt.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0000\u001a\u0014\u0010\u0005\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0000\u001a\u0014\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0000¨\u0006\b"}, d2 = {"Lcom/google/devtools/ksp/processing/Resolver;", "", "qName", "Lcom/google/devtools/ksp/symbol/KSClassDeclaration;", "findClass", "requireClass", "Lcom/google/devtools/ksp/symbol/KSType;", "requireType", "room-compiler-processing"}, k = 2, mv = {1, 7, 1})
public final class ResolverExtKt {
    public static final KSClassDeclaration findClass(Resolver resolver, String qName) {
        Intrinsics.checkNotNullParameter(resolver, "<this>");
        Intrinsics.checkNotNullParameter(qName, "qName");
        return resolver.getClassDeclarationByName(resolver.getKSNameFromString(qName));
    }

    public static final KSClassDeclaration requireClass(Resolver resolver, String qName) {
        Intrinsics.checkNotNullParameter(resolver, "<this>");
        Intrinsics.checkNotNullParameter(qName, "qName");
        KSClassDeclaration kSClassDeclarationFindClass = findClass(resolver, qName);
        if (kSClassDeclarationFindClass != null) {
            return kSClassDeclarationFindClass;
        }
        throw new IllegalStateException(("cannot find class " + qName).toString());
    }

    public static final KSType requireType(Resolver resolver, String qName) {
        Intrinsics.checkNotNullParameter(resolver, "<this>");
        Intrinsics.checkNotNullParameter(qName, "qName");
        return requireClass(resolver, qName).asType(CollectionsKt__CollectionsKt.emptyList());
    }
}
