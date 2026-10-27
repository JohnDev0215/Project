package dagger.internal.codegen.xprocessing;

import com.google.common.base.Equivalence;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;
import com.squareup.javapoet.WildcardTypeName;
import dagger.spi.shaded.androidx.room.compiler.processing.XType;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes7.dex */
public final class XTypes {
    private static final Equivalence<XType> XTYPE_EQUIVALENCE = new Equivalence<XType>() { // from class: dagger.internal.codegen.xprocessing.XTypes.1
        public String toString() {
            return "XTypes.equivalence()";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.base.Equivalence
        public boolean doEquivalent(XType left, XType right) {
            return left.getTypeName().equals(right.getTypeName());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.base.Equivalence
        public int doHash(XType type) {
            return type.getTypeName().hashCode();
        }
    };

    public static Equivalence<XType> equivalence() {
        return XTYPE_EQUIVALENCE;
    }

    public static String toStableString(XType type) {
        try {
            return toStableString(type.getTypeName());
        } catch (TypeNotPresentException e) {
            return e.typeName();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String toStableString(TypeName typeName) {
        if (typeName instanceof ClassName) {
            return ((ClassName) typeName).canonicalName();
        }
        if (typeName instanceof ArrayTypeName) {
            return String.format("%s[]", toStableString(((ArrayTypeName) typeName).componentType));
        }
        if (typeName instanceof ParameterizedTypeName) {
            ParameterizedTypeName parameterizedTypeName = (ParameterizedTypeName) typeName;
            return String.format("%s<%s>", parameterizedTypeName.rawType, parameterizedTypeName.typeArguments.stream().map(new Function() { // from class: dagger.internal.codegen.xprocessing.XTypes$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return XTypes.toStableString((TypeName) obj);
                }
            }).collect(Collectors.joining(",")));
        }
        if (typeName instanceof WildcardTypeName) {
            WildcardTypeName wildcardTypeName = (WildcardTypeName) typeName;
            TypeName typeName2 = (TypeName) Iterables.getOnlyElement(wildcardTypeName.upperBounds);
            if (typeName2.equals(TypeName.OBJECT)) {
                return !wildcardTypeName.lowerBounds.isEmpty() ? String.format("? super %s", toStableString((TypeName) Iterables.getOnlyElement(wildcardTypeName.lowerBounds))) : "?";
            }
            Preconditions.checkState(wildcardTypeName.lowerBounds.isEmpty());
            return String.format("? extends %s", toStableString(typeName2));
        }
        if (typeName instanceof TypeVariableName) {
            return ((TypeVariableName) typeName).name;
        }
        return typeName.toString();
    }

    private XTypes() {
    }
}
