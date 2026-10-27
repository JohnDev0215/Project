package net.bytebuddy.description.annotation;

import java.util.Arrays;
import java.util.List;
import net.bytebuddy.description.annotation.AnnotationList;

/* JADX INFO: loaded from: classes3.dex */
public interface AnnotationSource {
    AnnotationList getDeclaredAnnotations();

    public enum Empty implements AnnotationSource {
        INSTANCE;

        @Override // net.bytebuddy.description.annotation.AnnotationSource
        public AnnotationList getDeclaredAnnotations() {
            return new AnnotationList.Empty();
        }
    }

    public static class Explicit implements AnnotationSource {
        private final List<? extends AnnotationDescription> annotations;

        protected boolean canEqual(Object obj) {
            return obj instanceof Explicit;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Explicit)) {
                return false;
            }
            Explicit explicit = (Explicit) obj;
            if (!explicit.canEqual(this)) {
                return false;
            }
            List<? extends AnnotationDescription> list = this.annotations;
            List<? extends AnnotationDescription> list2 = explicit.annotations;
            return list != null ? list.equals(list2) : list2 == null;
        }

        public int hashCode() {
            List<? extends AnnotationDescription> list = this.annotations;
            return 59 + (list == null ? 43 : list.hashCode());
        }

        public Explicit(AnnotationDescription... annotationDescriptionArr) {
            this((List<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
        }

        public Explicit(List<? extends AnnotationDescription> list) {
            this.annotations = list;
        }

        @Override // net.bytebuddy.description.annotation.AnnotationSource
        public AnnotationList getDeclaredAnnotations() {
            return new AnnotationList.Explicit(this.annotations);
        }
    }
}
