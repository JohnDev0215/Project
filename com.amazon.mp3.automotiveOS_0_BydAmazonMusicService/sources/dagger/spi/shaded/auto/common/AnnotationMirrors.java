package dagger.spi.shaded.auto.common;

import com.google.common.base.Equivalence;
import com.google.common.collect.ImmutableMap;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.util.ElementFilter;

/* JADX INFO: loaded from: classes7.dex */
public final class AnnotationMirrors {
    private static final Equivalence<AnnotationMirror> ANNOTATION_MIRROR_EQUIVALENCE = new Equivalence<AnnotationMirror>() { // from class: dagger.spi.shaded.auto.common.AnnotationMirrors.1
        public String toString() {
            return "AnnotationMirrors.equivalence()";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.base.Equivalence
        public boolean doEquivalent(AnnotationMirror annotationMirror, AnnotationMirror annotationMirror2) {
            return MoreTypes.equivalence().equivalent(annotationMirror.getAnnotationType(), annotationMirror2.getAnnotationType()) && AnnotationValues.equivalence().pairwise().equivalent(AnnotationMirrors.getAnnotationValuesWithDefaults(annotationMirror).values(), AnnotationMirrors.getAnnotationValuesWithDefaults(annotationMirror2).values());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.base.Equivalence
        public int doHash(AnnotationMirror annotationMirror) {
            return Arrays.hashCode(new int[]{MoreTypes.equivalence().hash(annotationMirror.getAnnotationType()), AnnotationValues.equivalence().pairwise().hash(AnnotationMirrors.getAnnotationValuesWithDefaults(annotationMirror).values())});
        }
    };

    public static ImmutableMap<ExecutableElement, AnnotationValue> getAnnotationValuesWithDefaults(AnnotationMirror annotationMirror) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        Map mapUnmodifiableMap = Collections.unmodifiableMap(annotationMirror.getElementValues());
        for (ExecutableElement executableElement : ElementFilter.methodsIn(annotationMirror.getAnnotationType().asElement().getEnclosedElements())) {
            if (mapUnmodifiableMap.containsKey(executableElement)) {
                builder.put(executableElement, (AnnotationValue) mapUnmodifiableMap.get(executableElement));
            } else if (executableElement.getDefaultValue() != null) {
                builder.put(executableElement, executableElement.getDefaultValue());
            } else {
                String strValueOf = String.valueOf(MoreElements.asType(executableElement.getEnclosingElement()).getQualifiedName());
                String strValueOf2 = String.valueOf(executableElement.getSimpleName());
                StringBuilder sb = new StringBuilder(strValueOf.length() + 63 + strValueOf2.length());
                sb.append("Unset annotation value without default should never happen: ");
                sb.append(strValueOf);
                sb.append('.');
                sb.append(strValueOf2);
                sb.append("()");
                throw new IllegalStateException(sb.toString());
            }
        }
        return builder.build();
    }

    public static String toString(AnnotationMirror annotationMirror) {
        return AnnotationOutput.toString(annotationMirror);
    }

    private AnnotationMirrors() {
    }
}
