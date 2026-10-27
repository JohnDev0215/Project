package dagger.spi.shaded.auto.common;

import com.google.common.base.Equivalence;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.function.Function;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.util.SimpleAnnotationValueVisitor8;

/* JADX INFO: loaded from: classes7.dex */
public final class AnnotationValues {
    private static final Equivalence<AnnotationValue> ANNOTATION_VALUE_EQUIVALENCE = new Equivalence<AnnotationValue>() { // from class: dagger.spi.shaded.auto.common.AnnotationValues.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.base.Equivalence
        public boolean doEquivalent(AnnotationValue annotationValue, AnnotationValue annotationValue2) {
            return ((Boolean) annotationValue.accept(new SimpleAnnotationValueVisitor8<Boolean, AnnotationValue>(this) { // from class: dagger.spi.shaded.auto.common.AnnotationValues.1.1
            }, annotationValue2)).booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.base.Equivalence
        public int doHash(AnnotationValue annotationValue) {
            return ((Integer) annotationValue.accept(new SimpleAnnotationValueVisitor8<Integer, Void>(this) { // from class: dagger.spi.shaded.auto.common.AnnotationValues.1.2
            }, (Object) null)).intValue();
        }
    };
    private static final ArrayVisitor<DeclaredType> TYPE_MIRRORS_VISITOR = new ArrayVisitor<>(new Function() { // from class: dagger.spi.shaded.auto.common.AnnotationValues$$ExternalSyntheticLambda0
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return AnnotationValues.getTypeMirror((AnnotationValue) obj);
        }
    });
    private static final ArrayVisitor<AnnotationMirror> ANNOTATION_MIRRORS_VISITOR = new ArrayVisitor<>(new Function() { // from class: dagger.spi.shaded.auto.common.AnnotationValues$$ExternalSyntheticLambda4
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return AnnotationValues.getAnnotationMirror((AnnotationValue) obj);
        }
    });
    private static final ArrayVisitor<VariableElement> ENUMS_VISITOR = new ArrayVisitor<>(new Function() { // from class: dagger.spi.shaded.auto.common.AnnotationValues$$ExternalSyntheticLambda5
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return AnnotationValues.getEnum((AnnotationValue) obj);
        }
    });
    private static final ArrayVisitor<String> STRINGS_VISITOR = new ArrayVisitor<>(new Function() { // from class: dagger.spi.shaded.auto.common.AnnotationValues$$ExternalSyntheticLambda6
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return AnnotationValues.getString((AnnotationValue) obj);
        }
    });
    private static final ArrayVisitor<Integer> INTS_VISITOR = new ArrayVisitor<>(new Function() { // from class: dagger.spi.shaded.auto.common.AnnotationValues$$ExternalSyntheticLambda7
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return Integer.valueOf(AnnotationValues.getInt((AnnotationValue) obj));
        }
    });
    private static final ArrayVisitor<Long> LONGS_VISITOR = new ArrayVisitor<>(new Function() { // from class: dagger.spi.shaded.auto.common.AnnotationValues$$ExternalSyntheticLambda8
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return Long.valueOf(AnnotationValues.getLong((AnnotationValue) obj));
        }
    });
    private static final ArrayVisitor<Byte> BYTES_VISITOR = new ArrayVisitor<>(new Function() { // from class: dagger.spi.shaded.auto.common.AnnotationValues$$ExternalSyntheticLambda9
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return Byte.valueOf(AnnotationValues.getByte((AnnotationValue) obj));
        }
    });
    private static final ArrayVisitor<Short> SHORTS_VISITOR = new ArrayVisitor<>(new Function() { // from class: dagger.spi.shaded.auto.common.AnnotationValues$$ExternalSyntheticLambda10
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return Short.valueOf(AnnotationValues.getShort((AnnotationValue) obj));
        }
    });
    private static final ArrayVisitor<Float> FLOATS_VISITOR = new ArrayVisitor<>(new Function() { // from class: dagger.spi.shaded.auto.common.AnnotationValues$$ExternalSyntheticLambda11
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return Float.valueOf(AnnotationValues.getFloat((AnnotationValue) obj));
        }
    });
    private static final ArrayVisitor<Double> DOUBLES_VISITOR = new ArrayVisitor<>(new Function() { // from class: dagger.spi.shaded.auto.common.AnnotationValues$$ExternalSyntheticLambda12
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return Double.valueOf(AnnotationValues.getDouble((AnnotationValue) obj));
        }
    });
    private static final ArrayVisitor<Boolean> BOOLEANS_VISITOR = new ArrayVisitor<>(new Function() { // from class: dagger.spi.shaded.auto.common.AnnotationValues$$ExternalSyntheticLambda1
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return Boolean.valueOf(AnnotationValues.getBoolean((AnnotationValue) obj));
        }
    });
    private static final ArrayVisitor<Character> CHARS_VISITOR = new ArrayVisitor<>(new Function() { // from class: dagger.spi.shaded.auto.common.AnnotationValues$$ExternalSyntheticLambda2
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return Character.valueOf(AnnotationValues.getChar((AnnotationValue) obj));
        }
    });
    private static final ArrayVisitor<AnnotationValue> ANNOTATION_VALUES_VISITOR = new ArrayVisitor<>(new Function() { // from class: dagger.spi.shaded.auto.common.AnnotationValues$$ExternalSyntheticLambda3
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return AnnotationValues.lambda$static$0((AnnotationValue) obj);
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ AnnotationValue lambda$static$0(AnnotationValue annotationValue) {
        return annotationValue;
    }

    public static Equivalence<AnnotationValue> equivalence() {
        return ANNOTATION_VALUE_EQUIVALENCE;
    }

    private static class DefaultVisitor<T> extends SimpleAnnotationValueVisitor8<T, Void> {
        final Class<T> clazz;

        DefaultVisitor(Class<T> cls) {
            this.clazz = (Class) Preconditions.checkNotNull(cls);
        }
    }

    private static final class TypeMirrorVisitor extends DefaultVisitor<DeclaredType> {
        static final TypeMirrorVisitor INSTANCE = new TypeMirrorVisitor();

        TypeMirrorVisitor() {
            super(DeclaredType.class);
        }
    }

    public static DeclaredType getTypeMirror(AnnotationValue annotationValue) {
        return (DeclaredType) TypeMirrorVisitor.INSTANCE.visit(annotationValue);
    }

    private static final class AnnotationMirrorVisitor extends DefaultVisitor<AnnotationMirror> {
        static final AnnotationMirrorVisitor INSTANCE = new AnnotationMirrorVisitor();

        AnnotationMirrorVisitor() {
            super(AnnotationMirror.class);
        }
    }

    public static AnnotationMirror getAnnotationMirror(AnnotationValue annotationValue) {
        return (AnnotationMirror) AnnotationMirrorVisitor.INSTANCE.visit(annotationValue);
    }

    private static final class EnumVisitor extends DefaultVisitor<VariableElement> {
        static final EnumVisitor INSTANCE = new EnumVisitor();

        EnumVisitor() {
            super(VariableElement.class);
        }
    }

    public static VariableElement getEnum(AnnotationValue annotationValue) {
        return (VariableElement) EnumVisitor.INSTANCE.visit(annotationValue);
    }

    private static <T> T valueOfType(AnnotationValue annotationValue, Class<T> cls) {
        Object value = annotationValue.getValue();
        if (!cls.isInstance(value)) {
            String simpleName = cls.getSimpleName();
            String strValueOf = String.valueOf(value);
            StringBuilder sb = new StringBuilder(simpleName.length() + 24 + strValueOf.length());
            sb.append("Expected ");
            sb.append(simpleName);
            sb.append(", got instead: ");
            sb.append(strValueOf);
            throw new IllegalArgumentException(sb.toString());
        }
        return cls.cast(value);
    }

    public static String getString(AnnotationValue annotationValue) {
        return (String) valueOfType(annotationValue, String.class);
    }

    public static int getInt(AnnotationValue annotationValue) {
        return ((Integer) valueOfType(annotationValue, Integer.class)).intValue();
    }

    public static long getLong(AnnotationValue annotationValue) {
        return ((Long) valueOfType(annotationValue, Long.class)).longValue();
    }

    public static byte getByte(AnnotationValue annotationValue) {
        return ((Byte) valueOfType(annotationValue, Byte.class)).byteValue();
    }

    public static short getShort(AnnotationValue annotationValue) {
        return ((Short) valueOfType(annotationValue, Short.class)).shortValue();
    }

    public static float getFloat(AnnotationValue annotationValue) {
        return ((Float) valueOfType(annotationValue, Float.class)).floatValue();
    }

    public static double getDouble(AnnotationValue annotationValue) {
        return ((Double) valueOfType(annotationValue, Double.class)).doubleValue();
    }

    public static boolean getBoolean(AnnotationValue annotationValue) {
        return ((Boolean) valueOfType(annotationValue, Boolean.class)).booleanValue();
    }

    public static char getChar(AnnotationValue annotationValue) {
        return ((Character) valueOfType(annotationValue, Character.class)).charValue();
    }

    private static final class ArrayVisitor<T> extends SimpleAnnotationValueVisitor8<ImmutableList<T>, Void> {
        final Function<AnnotationValue, T> visitT;

        ArrayVisitor(Function<AnnotationValue, T> function) {
            this.visitT = (Function) Preconditions.checkNotNull(function);
        }
    }

    private AnnotationValues() {
    }
}
