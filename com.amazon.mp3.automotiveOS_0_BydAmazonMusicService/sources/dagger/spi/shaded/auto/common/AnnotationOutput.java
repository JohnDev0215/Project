package dagger.spi.shaded.auto.common;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Map;
import java.util.Optional;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor8;

/* JADX INFO: loaded from: classes7.dex */
final class AnnotationOutput {
    private static final AnnotationValueVisitor<AnnotationValue, AnnotationValue> ARRAY_VISITOR = new SimpleAnnotationValueVisitor8<AnnotationValue, AnnotationValue>() { // from class: dagger.spi.shaded.auto.common.AnnotationOutput.1
    };

    private AnnotationOutput() {
    }

    private static class SourceFormVisitor extends SimpleAnnotationValueVisitor8<Void, StringBuilder> {
        private SourceFormVisitor() {
        }

        private String formatType(TypeMirror typeMirror) {
            return MoreTypes.asTypeElement(typeMirror).getQualifiedName().toString();
        }

        public Void visitAnnotation(AnnotationMirror annotationMirror, StringBuilder sb) {
            sb.append('@');
            sb.append(formatType(annotationMirror.getAnnotationType()));
            ImmutableMap immutableMapCopyOf = ImmutableMap.copyOf(annotationMirror.getElementValues());
            if (immutableMapCopyOf.isEmpty()) {
                return null;
            }
            sb.append('(');
            Optional optionalShortForm = AnnotationOutput.shortForm(immutableMapCopyOf);
            if (optionalShortForm.isPresent()) {
                visit(AnnotationOutput.maybeShorten((AnnotationValue) optionalShortForm.get()), sb);
            } else {
                UnmodifiableIterator it = immutableMapCopyOf.entrySet().iterator();
                String str = "";
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    sb.append(str);
                    sb.append((CharSequence) ((ExecutableElement) entry.getKey()).getSimpleName());
                    sb.append(" = ");
                    visit(AnnotationOutput.maybeShorten((AnnotationValue) entry.getValue()), sb);
                    str = ", ";
                }
            }
            sb.append(')');
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static AnnotationValue maybeShorten(AnnotationValue annotationValue) {
        return (AnnotationValue) ARRAY_VISITOR.visit(annotationValue, annotationValue);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Optional<AnnotationValue> shortForm(Map<ExecutableElement, AnnotationValue> map) {
        if (map.size() == 1 && ((ExecutableElement) Iterables.getOnlyElement(map.keySet())).getSimpleName().contentEquals("value")) {
            return Optional.of((AnnotationValue) Iterables.getOnlyElement(map.values()));
        }
        return Optional.empty();
    }

    static String toString(AnnotationMirror annotationMirror) {
        StringBuilder sb = new StringBuilder();
        new SourceFormVisitor().visitAnnotation(annotationMirror, sb);
        return sb.toString();
    }
}
