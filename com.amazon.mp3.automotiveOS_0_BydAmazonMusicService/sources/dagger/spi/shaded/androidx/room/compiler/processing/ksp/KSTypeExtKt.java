package dagger.spi.shaded.androidx.room.compiler.processing.ksp;

import com.google.devtools.ksp.symbol.KSAnnotated;
import com.google.devtools.ksp.symbol.KSAnnotation;
import com.google.devtools.ksp.symbol.KSDeclaration;
import com.google.devtools.ksp.symbol.KSName;
import com.google.devtools.ksp.symbol.KSType;
import com.google.devtools.ksp.symbol.KSTypeArgument;
import com.google.devtools.ksp.symbol.KSTypeReference;
import dagger.spi.shaded.androidx.room.compiler.processing.XNullability;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.JvmWildcard;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KSTypeExt.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\f\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0000\u001a\f\u0010\u0005\u001a\u00020\u0004*\u00020\u0003H\u0000\u001a\u0014\u0010\b\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0000\u001a\u0014\u0010\f\u001a\u00020\u000b*\u00020\t2\u0006\u0010\n\u001a\u00020\u0001H\u0002\u001a\f\u0010\r\u001a\u00020\u000b*\u00020\tH\u0000¨\u0006\u000e"}, d2 = {"Lcom/google/devtools/ksp/symbol/KSDeclaration;", "", "getNormalizedPackageName", "Lcom/google/devtools/ksp/symbol/KSTypeArgument;", "Lcom/google/devtools/ksp/symbol/KSType;", "requireType", "Ldagger/spi/shaded/androidx/room/compiler/processing/XNullability;", "nullability", "withNullability", "Lcom/google/devtools/ksp/symbol/KSAnnotated;", "qName", "", "hasAnnotation", "hasJvmWildcardAnnotation", "room-compiler-processing"}, k = 2, mv = {1, 7, 1})
public final class KSTypeExtKt {

    /* JADX INFO: compiled from: KSTypeExt.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[XNullability.values().length];
            try {
                iArr[XNullability.NULLABLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[XNullability.NONNULL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final String getNormalizedPackageName(KSDeclaration kSDeclaration) {
        Intrinsics.checkNotNullParameter(kSDeclaration, "<this>");
        String strAsString = kSDeclaration.getPackageName().asString();
        return Intrinsics.areEqual(strAsString, "<root>") ? "" : strAsString;
    }

    public static final KSType requireType(KSTypeArgument kSTypeArgument) {
        Intrinsics.checkNotNullParameter(kSTypeArgument, "<this>");
        KSTypeReference type = kSTypeArgument.getType();
        KSType resolved = type != null ? type.getResolved() : null;
        if (resolved != null) {
            return resolved;
        }
        throw new IllegalStateException(("KSTypeArgument.type should not have been null, please file a bug. " + kSTypeArgument).toString());
    }

    public static final KSType withNullability(KSType kSType, XNullability nullability) {
        Intrinsics.checkNotNullParameter(kSType, "<this>");
        Intrinsics.checkNotNullParameter(nullability, "nullability");
        int i = WhenMappings.$EnumSwitchMapping$0[nullability.ordinal()];
        if (i == 1) {
            return kSType.makeNullable();
        }
        if (i == 2) {
            return kSType.makeNotNullable();
        }
        throw new IllegalArgumentException("Cannot set KSType nullability to platform");
    }

    private static final boolean hasAnnotation(KSAnnotated kSAnnotated, String str) {
        Iterator<KSAnnotation> it = kSAnnotated.getAnnotations().iterator();
        while (it.hasNext()) {
            KSName qualifiedName = it.next().getAnnotationType().getResolved().getDeclaration().getQualifiedName();
            if (Intrinsics.areEqual(qualifiedName != null ? qualifiedName.asString() : null, str)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean hasJvmWildcardAnnotation(KSAnnotated kSAnnotated) {
        Intrinsics.checkNotNullParameter(kSAnnotated, "<this>");
        String canonicalName = JvmWildcard.class.getCanonicalName();
        Intrinsics.checkNotNull(canonicalName);
        return hasAnnotation(kSAnnotated, canonicalName);
    }
}
