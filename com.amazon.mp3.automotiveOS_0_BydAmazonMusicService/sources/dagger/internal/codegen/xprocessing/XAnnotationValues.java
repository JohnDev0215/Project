package dagger.internal.codegen.xprocessing;

import com.amplifyframework.core.model.ModelIdentifier;
import com.google.common.base.Equivalence;
import com.squareup.javapoet.CodeBlock;
import dagger.spi.shaded.androidx.room.compiler.processing.XAnnotationValue;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes7.dex */
public final class XAnnotationValues {
    private static final Equivalence<XAnnotationValue> XANNOTATION_VALUE_EQUIVALENCE = new Equivalence<XAnnotationValue>() { // from class: dagger.internal.codegen.xprocessing.XAnnotationValues.1
        public String toString() {
            return "XAnnotationValues.equivalence()";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.base.Equivalence
        public boolean doEquivalent(XAnnotationValue left, XAnnotationValue right) {
            if (left.hasAnnotationValue()) {
                return right.hasAnnotationValue() && XAnnotations.equivalence().equivalent(left.asAnnotation(), right.asAnnotation());
            }
            if (left.hasListValue()) {
                return right.hasListValue() && XAnnotationValues.equivalence().pairwise().equivalent(left.asAnnotationValueList(), right.asAnnotationValueList());
            }
            if (left.hasTypeValue()) {
                return right.hasTypeValue() && XTypes.equivalence().equivalent(left.asType(), right.asType());
            }
            return left.getValue().equals(right.getValue());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.base.Equivalence
        public int doHash(XAnnotationValue value) {
            if (value.hasAnnotationValue()) {
                return XAnnotations.equivalence().hash(value.asAnnotation());
            }
            if (value.hasListValue()) {
                return XAnnotationValues.equivalence().pairwise().hash(value.asAnnotationValueList());
            }
            if (value.hasTypeValue()) {
                return XTypes.equivalence().hash(value.asType());
            }
            return value.getValue().hashCode();
        }
    };

    public static Equivalence<XAnnotationValue> equivalence() {
        return XANNOTATION_VALUE_EQUIVALENCE;
    }

    public static String toStableString(XAnnotationValue value) {
        try {
            if (value.getValue() == null) {
                return "<error>";
            }
            if (value.hasListValue()) {
                return (String) value.asAnnotationValueList().stream().map(new Function() { // from class: dagger.internal.codegen.xprocessing.XAnnotationValues$$ExternalSyntheticLambda0
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return XAnnotationValues.toStableString((XAnnotationValue) obj);
                    }
                }).collect(Collectors.joining(", ", "{", "}"));
            }
            if (value.hasAnnotationValue()) {
                return XAnnotations.toStableString(value.asAnnotation());
            }
            if (value.hasEnumValue()) {
                return XElements.getSimpleName(value.asEnum());
            }
            if (value.hasTypeValue()) {
                return value.asType().getTypeElement().getQualifiedName();
            }
            if (value.hasStringValue()) {
                return CodeBlock.of("$S", value.asString()).toString();
            }
            if (value.hasCharValue()) {
                return characterLiteralWithSingleQuotes(value.asChar());
            }
            return value.getValue().toString();
        } catch (TypeNotPresentException e) {
            return e.typeName();
        }
    }

    public static String characterLiteralWithSingleQuotes(char c) {
        return "'" + characterLiteralWithoutSingleQuotes(c) + "'";
    }

    private static String characterLiteralWithoutSingleQuotes(char c) {
        if (c == '\f') {
            return "\\f";
        }
        if (c == '\r') {
            return "\\r";
        }
        if (c == '\"') {
            return ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR;
        }
        if (c == '\'') {
            return "\\'";
        }
        if (c == '\\') {
            return "\\\\";
        }
        switch (c) {
            case '\b':
                return "\\b";
            case '\t':
                return "\\t";
            case '\n':
                return "\\n";
            default:
                return Character.isISOControl(c) ? String.format("\\u%04x", Integer.valueOf(c)) : Character.toString(c);
        }
    }

    private XAnnotationValues() {
    }
}
