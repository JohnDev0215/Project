package dagger.internal.codegen.xprocessing;

import com.google.common.base.Equivalence;
import com.squareup.javapoet.ClassName;
import dagger.spi.shaded.androidx.room.compiler.processing.XAnnotation;
import dagger.spi.shaded.androidx.room.compiler.processing.XAnnotationValue;
import dagger.spi.shaded.androidx.room.compiler.processing.XProcessingEnv;
import dagger.spi.shaded.androidx.room.compiler.processing.compat.XConverters;
import dagger.spi.shaded.auto.common.AnnotationMirrors;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes7.dex */
public final class XAnnotations {
    private static final Equivalence<XAnnotation> XANNOTATION_EQUIVALENCE = new Equivalence<XAnnotation>() { // from class: dagger.internal.codegen.xprocessing.XAnnotations.1
        public String toString() {
            return "XAnnotation.equivalence()";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.base.Equivalence
        public boolean doEquivalent(XAnnotation left, XAnnotation right) {
            return XTypes.equivalence().equivalent(left.getType(), right.getType()) && XAnnotationValues.equivalence().pairwise().equivalent(left.getAnnotationValues(), right.getAnnotationValues());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.base.Equivalence
        public int doHash(XAnnotation annotation) {
            return Arrays.hashCode(new int[]{XTypes.equivalence().hash(annotation.getType()), XAnnotationValues.equivalence().pairwise().hash(annotation.getAnnotationValues())});
        }
    };

    public static String toString(XAnnotation annotation) {
        if (XConverters.getProcessingEnv(annotation).getBackend() == XProcessingEnv.Backend.JAVAC) {
            return AnnotationMirrors.toString(XConverters.toJavac(annotation));
        }
        return toStableString(annotation);
    }

    public static ClassName getClassName(XAnnotation annotation) {
        return annotation.getType().getTypeElement().getClassName();
    }

    public static Equivalence<XAnnotation> equivalence() {
        return XANNOTATION_EQUIVALENCE;
    }

    public static String toStableString(final XAnnotation annotation) {
        try {
            if (!annotation.getType().isError()) {
                return annotation.getAnnotationValues().isEmpty() ? String.format("@%s", getClassName(annotation).canonicalName()) : String.format("@%s(%s)", getClassName(annotation).canonicalName(), annotation.getAnnotationValues().stream().map(new Function() { // from class: dagger.internal.codegen.xprocessing.XAnnotations$$ExternalSyntheticLambda0
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return XAnnotations.lambda$toStableString$0(annotation, (XAnnotationValue) obj);
                    }
                }).collect(Collectors.joining(", ")));
            }
            return "@" + annotation.getName();
        } catch (TypeNotPresentException e) {
            return e.typeName();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$toStableString$0(XAnnotation xAnnotation, XAnnotationValue xAnnotationValue) {
        String name = xAnnotationValue.getName();
        String stableString = XAnnotationValues.toStableString(xAnnotationValue);
        return (xAnnotation.getAnnotationValues().size() == 1 && name.contentEquals("value")) ? stableString : String.format("%s=%s", name, stableString);
    }

    private XAnnotations() {
    }
}
