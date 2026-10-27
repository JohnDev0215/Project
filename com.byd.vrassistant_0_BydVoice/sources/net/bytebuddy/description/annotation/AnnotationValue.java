package net.bytebuddy.description.annotation;

import cerence.ark.assistant.controller.enumeration.TemperatureUnitEnum;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.enumeration.EnumerationDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.pool.TypePool;

/* JADX INFO: loaded from: classes3.dex */
public interface AnnotationValue<T, S> {
    public static final AnnotationValue<?, ?> UNDEFINED = null;

    Loaded<S> load(ClassLoader classLoader) throws ClassNotFoundException;

    Loaded<S> loadSilent(ClassLoader classLoader);

    T resolve();

    <W> W resolve(Class<? extends W> cls);

    public enum RenderingDispatcher {
        LEGACY_VM(TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH, ']') { // from class: net.bytebuddy.description.annotation.AnnotationValue.RenderingDispatcher.1
            @Override // net.bytebuddy.description.annotation.AnnotationValue.RenderingDispatcher
            public String toSourceString(String str) {
                return str;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue.RenderingDispatcher
            public String toSourceString(char c) {
                return Character.toString(c);
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue.RenderingDispatcher
            public String toSourceString(long j) {
                return Long.toString(j);
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue.RenderingDispatcher
            public String toSourceString(float f) {
                return Float.toString(f);
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue.RenderingDispatcher
            public String toSourceString(double d) {
                return Double.toString(d);
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue.RenderingDispatcher
            public String toSourceString(TypeDescription typeDescription) {
                return typeDescription.toString();
            }
        },
        JAVA_9_CAPABLE_VM('{', '}') { // from class: net.bytebuddy.description.annotation.AnnotationValue.RenderingDispatcher.2
            @Override // net.bytebuddy.description.annotation.AnnotationValue.RenderingDispatcher
            public String toSourceString(char c) {
                StringBuilder sb = new StringBuilder("'");
                if (c == '\'') {
                    sb.append("\\'");
                } else {
                    sb.append(c);
                }
                return sb.append('\'').toString();
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue.RenderingDispatcher
            public String toSourceString(long j) {
                return Math.abs(j) <= 2147483647L ? String.valueOf(j) : j + TemperatureUnitEnum.L;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue.RenderingDispatcher
            public String toSourceString(float f) {
                if (Math.abs(f) <= Float.MAX_VALUE) {
                    return Float.toString(f) + "f";
                }
                return Float.isInfinite(f) ? f < 0.0f ? "-1.0f/0.0f" : "1.0f/0.0f" : "0.0f/0.0f";
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue.RenderingDispatcher
            public String toSourceString(double d) {
                if (Math.abs(d) <= Double.MAX_VALUE) {
                    return Double.toString(d);
                }
                return Double.isInfinite(d) ? d < 0.0d ? "-1.0/0.0" : "1.0/0.0" : "0.0/0.0";
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue.RenderingDispatcher
            public String toSourceString(String str) {
                StringBuilder sb = new StringBuilder("\"");
                if (str.indexOf(34) != -1) {
                    str = str.replace("\"", "\\\"");
                }
                return sb.append(str).append("\"").toString();
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue.RenderingDispatcher
            public String toSourceString(TypeDescription typeDescription) {
                return typeDescription.getActualName() + ClassFileLocator.CLASS_FILE_EXTENSION;
            }
        };

        public static final RenderingDispatcher CURRENT;
        private final char closingBrace;
        private final char openingBrace;

        public abstract String toSourceString(char c);

        public abstract String toSourceString(double d);

        public abstract String toSourceString(float f);

        public abstract String toSourceString(long j);

        public abstract String toSourceString(String str);

        public abstract String toSourceString(TypeDescription typeDescription);

        static {
            RenderingDispatcher renderingDispatcher = LEGACY_VM;
            CURRENT = ClassFileVersion.ofThisVm(ClassFileVersion.JAVA_V6).isAtLeast(ClassFileVersion.JAVA_V9) ? JAVA_9_CAPABLE_VM : renderingDispatcher;
        }

        RenderingDispatcher(char c, char c2) {
            this.openingBrace = c;
            this.closingBrace = c2;
        }

        public String toSourceString(boolean z) {
            return Boolean.toString(z);
        }

        public String toSourceString(byte b) {
            return Byte.toString(b);
        }

        public String toSourceString(short s) {
            return Short.toString(s);
        }

        public String toSourceString(int i) {
            return Integer.toString(i);
        }

        public String toSourceString(List<?> list) {
            StringBuilder sbAppend = new StringBuilder().append(this.openingBrace);
            boolean z = true;
            for (Object obj : list) {
                if (z) {
                    z = false;
                } else {
                    sbAppend.append(", ");
                }
                sbAppend.append(obj);
            }
            return sbAppend.append(this.closingBrace).toString();
        }
    }

    public interface Loaded<U> {
        State getState();

        boolean represents(Object obj);

        U resolve();

        <V> V resolve(Class<? extends V> cls);

        public enum State {
            UNDEFINED,
            UNRESOLVED,
            RESOLVED;

            public boolean isDefined() {
                return this != UNDEFINED;
            }

            public boolean isResolved() {
                return this == RESOLVED;
            }
        }

        public static abstract class AbstractBase<W> implements Loaded<W> {
            @Override // net.bytebuddy.description.annotation.AnnotationValue.Loaded
            public <X> X resolve(Class<? extends X> cls) {
                return cls.cast(resolve());
            }
        }
    }

    public static abstract class AbstractBase<U, V> implements AnnotationValue<U, V> {
        @Override // net.bytebuddy.description.annotation.AnnotationValue
        public <W> W resolve(Class<? extends W> cls) {
            return cls.cast(resolve());
        }

        @Override // net.bytebuddy.description.annotation.AnnotationValue
        public Loaded<V> loadSilent(ClassLoader classLoader) {
            try {
                return load(classLoader);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Cannot load " + this, e);
            }
        }
    }

    public static class ForConstant<U> extends AbstractBase<U, U> {
        private final PropertyDelegate propertyDelegate;
        private final U value;

        protected ForConstant(U u, PropertyDelegate propertyDelegate) {
            this.value = u;
            this.propertyDelegate = propertyDelegate;
        }

        public static AnnotationValue<Boolean, Boolean> of(boolean z) {
            return new ForConstant(Boolean.valueOf(z), PropertyDelegate.ForNonArrayType.BOOLEAN);
        }

        public static AnnotationValue<Byte, Byte> of(byte b) {
            return new ForConstant(Byte.valueOf(b), PropertyDelegate.ForNonArrayType.BYTE);
        }

        public static AnnotationValue<Short, Short> of(short s) {
            return new ForConstant(Short.valueOf(s), PropertyDelegate.ForNonArrayType.SHORT);
        }

        public static AnnotationValue<Character, Character> of(char c) {
            return new ForConstant(Character.valueOf(c), PropertyDelegate.ForNonArrayType.CHARACTER);
        }

        public static AnnotationValue<Integer, Integer> of(int i) {
            return new ForConstant(Integer.valueOf(i), PropertyDelegate.ForNonArrayType.INTEGER);
        }

        public static AnnotationValue<Long, Long> of(long j) {
            return new ForConstant(Long.valueOf(j), PropertyDelegate.ForNonArrayType.LONG);
        }

        public static AnnotationValue<Float, Float> of(float f) {
            return new ForConstant(Float.valueOf(f), PropertyDelegate.ForNonArrayType.FLOAT);
        }

        public static AnnotationValue<Double, Double> of(double d) {
            return new ForConstant(Double.valueOf(d), PropertyDelegate.ForNonArrayType.DOUBLE);
        }

        public static AnnotationValue<String, String> of(String str) {
            return new ForConstant(str, PropertyDelegate.ForNonArrayType.STRING);
        }

        public static AnnotationValue<boolean[], boolean[]> of(boolean... zArr) {
            return new ForConstant(zArr, PropertyDelegate.ForArrayType.BOOLEAN);
        }

        public static AnnotationValue<byte[], byte[]> of(byte... bArr) {
            return new ForConstant(bArr, PropertyDelegate.ForArrayType.BYTE);
        }

        public static AnnotationValue<short[], short[]> of(short... sArr) {
            return new ForConstant(sArr, PropertyDelegate.ForArrayType.SHORT);
        }

        public static AnnotationValue<char[], char[]> of(char... cArr) {
            return new ForConstant(cArr, PropertyDelegate.ForArrayType.CHARACTER);
        }

        public static AnnotationValue<int[], int[]> of(int... iArr) {
            return new ForConstant(iArr, PropertyDelegate.ForArrayType.INTEGER);
        }

        public static AnnotationValue<long[], long[]> of(long... jArr) {
            return new ForConstant(jArr, PropertyDelegate.ForArrayType.LONG);
        }

        public static AnnotationValue<float[], float[]> of(float... fArr) {
            return new ForConstant(fArr, PropertyDelegate.ForArrayType.FLOAT);
        }

        public static AnnotationValue<double[], double[]> of(double... dArr) {
            return new ForConstant(dArr, PropertyDelegate.ForArrayType.DOUBLE);
        }

        public static AnnotationValue<String[], String[]> of(String... strArr) {
            return new ForConstant(strArr, PropertyDelegate.ForArrayType.STRING);
        }

        public static AnnotationValue<?, ?> of(Object obj) {
            if (obj instanceof Boolean) {
                return of(((Boolean) obj).booleanValue());
            }
            if (obj instanceof Byte) {
                return of(((Byte) obj).byteValue());
            }
            if (obj instanceof Short) {
                return of(((Short) obj).shortValue());
            }
            if (obj instanceof Character) {
                return of(((Character) obj).charValue());
            }
            if (obj instanceof Integer) {
                return of(((Integer) obj).intValue());
            }
            if (obj instanceof Long) {
                return of(((Long) obj).longValue());
            }
            if (obj instanceof Float) {
                return of(((Float) obj).floatValue());
            }
            if (obj instanceof Double) {
                return of(((Double) obj).doubleValue());
            }
            if (obj instanceof String) {
                return of((String) obj);
            }
            if (obj instanceof boolean[]) {
                return of((boolean[]) obj);
            }
            if (obj instanceof byte[]) {
                return of((byte[]) obj);
            }
            if (obj instanceof short[]) {
                return of((short[]) obj);
            }
            if (obj instanceof char[]) {
                return of((char[]) obj);
            }
            if (obj instanceof int[]) {
                return of((int[]) obj);
            }
            if (obj instanceof long[]) {
                return of((long[]) obj);
            }
            if (obj instanceof float[]) {
                return of((float[]) obj);
            }
            if (obj instanceof double[]) {
                return of((double[]) obj);
            }
            if (obj instanceof String[]) {
                return of((String[]) obj);
            }
            throw new IllegalArgumentException("Not a constant annotation value: " + obj);
        }

        @Override // net.bytebuddy.description.annotation.AnnotationValue
        public U resolve() {
            return this.value;
        }

        @Override // net.bytebuddy.description.annotation.AnnotationValue
        public Loaded<U> load(ClassLoader classLoader) {
            return new Loaded(this.value, this.propertyDelegate);
        }

        public boolean equals(Object obj) {
            return obj == this || ((obj instanceof AnnotationValue) && this.propertyDelegate.equals(this.value, ((AnnotationValue) obj).resolve()));
        }

        public int hashCode() {
            return this.propertyDelegate.hashCode(this.value);
        }

        public String toString() {
            return this.propertyDelegate.toString(this.value);
        }

        protected interface PropertyDelegate {
            <S> S copy(S s);

            boolean equals(Object obj, Object obj2);

            int hashCode(Object obj);

            String toString(Object obj);

            public enum ForNonArrayType implements PropertyDelegate {
                BOOLEAN { // from class: net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForNonArrayType.1
                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate
                    public String toString(Object obj) {
                        return RenderingDispatcher.CURRENT.toSourceString(((Boolean) obj).booleanValue());
                    }
                },
                BYTE { // from class: net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForNonArrayType.2
                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate
                    public String toString(Object obj) {
                        return RenderingDispatcher.CURRENT.toSourceString(((Byte) obj).byteValue());
                    }
                },
                SHORT { // from class: net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForNonArrayType.3
                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate
                    public String toString(Object obj) {
                        return RenderingDispatcher.CURRENT.toSourceString(((Short) obj).shortValue());
                    }
                },
                CHARACTER { // from class: net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForNonArrayType.4
                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate
                    public String toString(Object obj) {
                        return RenderingDispatcher.CURRENT.toSourceString(((Character) obj).charValue());
                    }
                },
                INTEGER { // from class: net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForNonArrayType.5
                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate
                    public String toString(Object obj) {
                        return RenderingDispatcher.CURRENT.toSourceString(((Integer) obj).intValue());
                    }
                },
                LONG { // from class: net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForNonArrayType.6
                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate
                    public String toString(Object obj) {
                        return RenderingDispatcher.CURRENT.toSourceString(((Long) obj).longValue());
                    }
                },
                FLOAT { // from class: net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForNonArrayType.7
                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate
                    public String toString(Object obj) {
                        return RenderingDispatcher.CURRENT.toSourceString(((Float) obj).floatValue());
                    }
                },
                DOUBLE { // from class: net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForNonArrayType.8
                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate
                    public String toString(Object obj) {
                        return RenderingDispatcher.CURRENT.toSourceString(((Double) obj).doubleValue());
                    }
                },
                STRING { // from class: net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForNonArrayType.9
                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate
                    public String toString(Object obj) {
                        return RenderingDispatcher.CURRENT.toSourceString((String) obj);
                    }
                };

                @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate
                public <S> S copy(S s) {
                    return s;
                }

                @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate
                public int hashCode(Object obj) {
                    return obj.hashCode();
                }

                @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate
                public boolean equals(Object obj, Object obj2) {
                    return obj.equals(obj2);
                }
            }

            public enum ForArrayType implements PropertyDelegate {
                BOOLEAN { // from class: net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForArrayType.1
                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForArrayType
                    protected Object doCopy(Object obj) {
                        return ((boolean[]) obj).clone();
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate
                    public int hashCode(Object obj) {
                        return Arrays.hashCode((boolean[]) obj);
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate
                    public boolean equals(Object obj, Object obj2) {
                        return (obj2 instanceof boolean[]) && Arrays.equals((boolean[]) obj, (boolean[]) obj2);
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForArrayType
                    protected String toString(Object obj, int i) {
                        return ForNonArrayType.BOOLEAN.toString(Boolean.valueOf(Array.getBoolean(obj, i)));
                    }
                },
                BYTE { // from class: net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForArrayType.2
                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForArrayType
                    protected Object doCopy(Object obj) {
                        return ((byte[]) obj).clone();
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate
                    public int hashCode(Object obj) {
                        return Arrays.hashCode((byte[]) obj);
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate
                    public boolean equals(Object obj, Object obj2) {
                        return (obj2 instanceof byte[]) && Arrays.equals((byte[]) obj, (byte[]) obj2);
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForArrayType
                    protected String toString(Object obj, int i) {
                        return ForNonArrayType.BYTE.toString(Byte.valueOf(Array.getByte(obj, i)));
                    }
                },
                SHORT { // from class: net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForArrayType.3
                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForArrayType
                    protected Object doCopy(Object obj) {
                        return ((short[]) obj).clone();
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate
                    public int hashCode(Object obj) {
                        return Arrays.hashCode((short[]) obj);
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate
                    public boolean equals(Object obj, Object obj2) {
                        return (obj2 instanceof short[]) && Arrays.equals((short[]) obj, (short[]) obj2);
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForArrayType
                    protected String toString(Object obj, int i) {
                        return ForNonArrayType.SHORT.toString(Short.valueOf(Array.getShort(obj, i)));
                    }
                },
                CHARACTER { // from class: net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForArrayType.4
                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForArrayType
                    protected Object doCopy(Object obj) {
                        return ((char[]) obj).clone();
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate
                    public int hashCode(Object obj) {
                        return Arrays.hashCode((char[]) obj);
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate
                    public boolean equals(Object obj, Object obj2) {
                        return (obj2 instanceof char[]) && Arrays.equals((char[]) obj, (char[]) obj2);
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForArrayType
                    protected String toString(Object obj, int i) {
                        return ForNonArrayType.CHARACTER.toString(Character.valueOf(Array.getChar(obj, i)));
                    }
                },
                INTEGER { // from class: net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForArrayType.5
                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForArrayType
                    protected Object doCopy(Object obj) {
                        return ((int[]) obj).clone();
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate
                    public int hashCode(Object obj) {
                        return Arrays.hashCode((int[]) obj);
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate
                    public boolean equals(Object obj, Object obj2) {
                        return (obj2 instanceof int[]) && Arrays.equals((int[]) obj, (int[]) obj2);
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForArrayType
                    protected String toString(Object obj, int i) {
                        return ForNonArrayType.INTEGER.toString(Integer.valueOf(Array.getInt(obj, i)));
                    }
                },
                LONG { // from class: net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForArrayType.6
                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForArrayType
                    protected Object doCopy(Object obj) {
                        return ((long[]) obj).clone();
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate
                    public int hashCode(Object obj) {
                        return Arrays.hashCode((long[]) obj);
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate
                    public boolean equals(Object obj, Object obj2) {
                        return (obj2 instanceof long[]) && Arrays.equals((long[]) obj, (long[]) obj2);
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForArrayType
                    protected String toString(Object obj, int i) {
                        return ForNonArrayType.LONG.toString(Long.valueOf(Array.getLong(obj, i)));
                    }
                },
                FLOAT { // from class: net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForArrayType.7
                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForArrayType
                    protected Object doCopy(Object obj) {
                        return ((float[]) obj).clone();
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate
                    public int hashCode(Object obj) {
                        return Arrays.hashCode((float[]) obj);
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate
                    public boolean equals(Object obj, Object obj2) {
                        return (obj2 instanceof float[]) && Arrays.equals((float[]) obj, (float[]) obj2);
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForArrayType
                    protected String toString(Object obj, int i) {
                        return ForNonArrayType.FLOAT.toString(Float.valueOf(Array.getFloat(obj, i)));
                    }
                },
                DOUBLE { // from class: net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForArrayType.8
                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForArrayType
                    protected Object doCopy(Object obj) {
                        return ((double[]) obj).clone();
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate
                    public int hashCode(Object obj) {
                        return Arrays.hashCode((double[]) obj);
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate
                    public boolean equals(Object obj, Object obj2) {
                        return (obj2 instanceof double[]) && Arrays.equals((double[]) obj, (double[]) obj2);
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForArrayType
                    protected String toString(Object obj, int i) {
                        return ForNonArrayType.DOUBLE.toString(Double.valueOf(Array.getDouble(obj, i)));
                    }
                },
                STRING { // from class: net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForArrayType.9
                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForArrayType
                    protected Object doCopy(Object obj) {
                        return ((String[]) obj).clone();
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate
                    public int hashCode(Object obj) {
                        return Arrays.hashCode((String[]) obj);
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate
                    public boolean equals(Object obj, Object obj2) {
                        return (obj2 instanceof String[]) && Arrays.equals((String[]) obj, (String[]) obj2);
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate.ForArrayType
                    protected String toString(Object obj, int i) {
                        return ForNonArrayType.STRING.toString(Array.get(obj, i));
                    }
                };

                protected abstract Object doCopy(Object obj);

                protected abstract String toString(Object obj, int i);

                @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate
                public <S> S copy(S s) {
                    return (S) doCopy(s);
                }

                @Override // net.bytebuddy.description.annotation.AnnotationValue.ForConstant.PropertyDelegate
                public String toString(Object obj) {
                    ArrayList arrayList = new ArrayList(Array.getLength(obj));
                    for (int i = 0; i < Array.getLength(obj); i++) {
                        arrayList.add(toString(obj, i));
                    }
                    return RenderingDispatcher.CURRENT.toSourceString(arrayList);
                }
            }
        }

        protected static class Loaded<V> extends Loaded.AbstractBase<V> {
            private final PropertyDelegate propertyDelegate;
            private final V value;

            protected Loaded(V v, PropertyDelegate propertyDelegate) {
                this.value = v;
                this.propertyDelegate = propertyDelegate;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue.Loaded
            public Loaded.State getState() {
                return Loaded.State.RESOLVED;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue.Loaded, net.bytebuddy.description.annotation.AnnotationValue
            public V resolve() {
                return (V) this.propertyDelegate.copy(this.value);
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue.Loaded
            public boolean represents(Object obj) {
                return this.propertyDelegate.equals(this.value, obj);
            }

            public int hashCode() {
                return this.propertyDelegate.hashCode(this.value);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Loaded)) {
                    return false;
                }
                Loaded loaded = (Loaded) obj;
                return loaded.getState().isResolved() && this.propertyDelegate.equals(this.value, loaded.resolve());
            }

            public String toString() {
                return this.propertyDelegate.toString(this.value);
            }
        }
    }

    public static class ForAnnotationDescription<U extends Annotation> extends AbstractBase<AnnotationDescription, U> {
        private final AnnotationDescription annotationDescription;

        public ForAnnotationDescription(AnnotationDescription annotationDescription) {
            this.annotationDescription = annotationDescription;
        }

        public static <V extends Annotation> AnnotationValue<AnnotationDescription, V> of(TypeDescription typeDescription, Map<String, ? extends AnnotationValue<?, ?>> map) {
            return new ForAnnotationDescription(new AnnotationDescription.Latent(typeDescription, map));
        }

        @Override // net.bytebuddy.description.annotation.AnnotationValue
        public AnnotationDescription resolve() {
            return this.annotationDescription;
        }

        @Override // net.bytebuddy.description.annotation.AnnotationValue
        public Loaded<U> load(ClassLoader classLoader) throws ClassNotFoundException {
            return new Loaded(this.annotationDescription.prepare(Class.forName(this.annotationDescription.getAnnotationType().getName(), false, classLoader)).load());
        }

        public boolean equals(Object obj) {
            return this == obj || ((obj instanceof AnnotationValue) && this.annotationDescription.equals(((AnnotationValue) obj).resolve()));
        }

        public int hashCode() {
            return this.annotationDescription.hashCode();
        }

        public String toString() {
            return this.annotationDescription.toString();
        }

        public static class Loaded<V extends Annotation> extends Loaded.AbstractBase<V> {
            private final V annotation;

            public Loaded(V v) {
                this.annotation = v;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue.Loaded
            public Loaded.State getState() {
                return Loaded.State.RESOLVED;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue.Loaded, net.bytebuddy.description.annotation.AnnotationValue
            public V resolve() {
                return this.annotation;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue.Loaded
            public boolean represents(Object obj) {
                return this.annotation.equals(obj);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Loaded)) {
                    return false;
                }
                Loaded loaded = (Loaded) obj;
                return loaded.getState().isResolved() && this.annotation.equals(loaded.resolve());
            }

            public int hashCode() {
                return this.annotation.hashCode();
            }

            public String toString() {
                return this.annotation.toString();
            }
        }

        public static class IncompatibleRuntimeType extends Loaded.AbstractBase<Annotation> {
            private final Class<?> incompatibleType;

            @Override // net.bytebuddy.description.annotation.AnnotationValue.Loaded
            public boolean represents(Object obj) {
                return false;
            }

            public IncompatibleRuntimeType(Class<?> cls) {
                this.incompatibleType = cls;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue.Loaded
            public Loaded.State getState() {
                return Loaded.State.UNRESOLVED;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue.Loaded, net.bytebuddy.description.annotation.AnnotationValue
            public Annotation resolve() {
                throw new IncompatibleClassChangeError("Not an annotation type: " + this.incompatibleType.toString());
            }
        }
    }

    public static class ForEnumerationDescription<U extends Enum<U>> extends AbstractBase<EnumerationDescription, U> {
        private final EnumerationDescription enumerationDescription;

        protected ForEnumerationDescription(EnumerationDescription enumerationDescription) {
            this.enumerationDescription = enumerationDescription;
        }

        public static <V extends Enum<V>> AnnotationValue<EnumerationDescription, V> of(EnumerationDescription enumerationDescription) {
            return new ForEnumerationDescription(enumerationDescription);
        }

        @Override // net.bytebuddy.description.annotation.AnnotationValue
        public EnumerationDescription resolve() {
            return this.enumerationDescription;
        }

        @Override // net.bytebuddy.description.annotation.AnnotationValue
        public Loaded<U> load(ClassLoader classLoader) throws ClassNotFoundException {
            return new Loaded(this.enumerationDescription.load(Class.forName(this.enumerationDescription.getEnumerationType().getName(), false, classLoader)));
        }

        public boolean equals(Object obj) {
            return this == obj || ((obj instanceof AnnotationValue) && this.enumerationDescription.equals(((AnnotationValue) obj).resolve()));
        }

        public int hashCode() {
            return this.enumerationDescription.hashCode();
        }

        public String toString() {
            return this.enumerationDescription.toString();
        }

        public static class Loaded<V extends Enum<V>> extends Loaded.AbstractBase<V> {
            private final V enumeration;

            public Loaded(V v) {
                this.enumeration = v;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue.Loaded
            public Loaded.State getState() {
                return Loaded.State.RESOLVED;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue.Loaded, net.bytebuddy.description.annotation.AnnotationValue
            public V resolve() {
                return this.enumeration;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue.Loaded
            public boolean represents(Object obj) {
                return this.enumeration.equals(obj);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Loaded)) {
                    return false;
                }
                Loaded loaded = (Loaded) obj;
                return loaded.getState().isResolved() && this.enumeration.equals(loaded.resolve());
            }

            public int hashCode() {
                return this.enumeration.hashCode();
            }

            public String toString() {
                return this.enumeration.toString();
            }
        }

        public static class UnknownRuntimeEnumeration extends Loaded.AbstractBase<Enum<?>> {
            private final Class<? extends Enum<?>> enumType;
            private final String value;

            @Override // net.bytebuddy.description.annotation.AnnotationValue.Loaded
            public boolean represents(Object obj) {
                return false;
            }

            public UnknownRuntimeEnumeration(Class<? extends Enum<?>> cls, String str) {
                this.enumType = cls;
                this.value = str;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue.Loaded
            public Loaded.State getState() {
                return Loaded.State.UNRESOLVED;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue.Loaded, net.bytebuddy.description.annotation.AnnotationValue
            public Enum<?> resolve() {
                throw new EnumConstantNotPresentException(this.enumType, this.value);
            }
        }

        public static class IncompatibleRuntimeType extends Loaded.AbstractBase<Enum<?>> {
            private final Class<?> type;

            @Override // net.bytebuddy.description.annotation.AnnotationValue.Loaded
            public boolean represents(Object obj) {
                return false;
            }

            public IncompatibleRuntimeType(Class<?> cls) {
                this.type = cls;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue.Loaded
            public Loaded.State getState() {
                return Loaded.State.UNRESOLVED;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue.Loaded, net.bytebuddy.description.annotation.AnnotationValue
            public Enum<?> resolve() {
                throw new IncompatibleClassChangeError("Not an enumeration type: " + this.type.toString());
            }
        }
    }

    public static class ForTypeDescription<U extends Class<U>> extends AbstractBase<TypeDescription, U> {
        private static final boolean NO_INITIALIZATION = false;
        private final TypeDescription typeDescription;

        protected ForTypeDescription(TypeDescription typeDescription) {
            this.typeDescription = typeDescription;
        }

        public static <V extends Class<V>> AnnotationValue<TypeDescription, V> of(TypeDescription typeDescription) {
            return new ForTypeDescription(typeDescription);
        }

        @Override // net.bytebuddy.description.annotation.AnnotationValue
        public TypeDescription resolve() {
            return this.typeDescription;
        }

        @Override // net.bytebuddy.description.annotation.AnnotationValue
        public Loaded<U> load(ClassLoader classLoader) throws ClassNotFoundException {
            return new Loaded(Class.forName(this.typeDescription.getName(), false, classLoader));
        }

        public boolean equals(Object obj) {
            return this == obj || ((obj instanceof AnnotationValue) && this.typeDescription.equals(((AnnotationValue) obj).resolve()));
        }

        public int hashCode() {
            return this.typeDescription.hashCode();
        }

        public String toString() {
            return RenderingDispatcher.CURRENT.toSourceString(this.typeDescription);
        }

        protected static class Loaded<U extends Class<U>> extends Loaded.AbstractBase<U> {
            private final U type;

            public Loaded(U u) {
                this.type = u;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue.Loaded
            public Loaded.State getState() {
                return Loaded.State.RESOLVED;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue.Loaded, net.bytebuddy.description.annotation.AnnotationValue
            public U resolve() {
                return this.type;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue.Loaded
            public boolean represents(Object obj) {
                return this.type.equals(obj);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Loaded)) {
                    return false;
                }
                Loaded loaded = (Loaded) obj;
                return loaded.getState().isResolved() && this.type.equals(loaded.resolve());
            }

            public int hashCode() {
                return this.type.hashCode();
            }

            public String toString() {
                return RenderingDispatcher.CURRENT.toSourceString(new TypeDescription.ForLoadedType(this.type));
            }
        }
    }

    public static class ForDescriptionArray<U, V> extends AbstractBase<U[], V[]> {
        private final TypeDescription componentType;
        private final Class<?> unloadedComponentType;
        private final List<? extends AnnotationValue<?, ?>> values;

        protected ForDescriptionArray(Class<?> cls, TypeDescription typeDescription, List<? extends AnnotationValue<?, ?>> list) {
            this.unloadedComponentType = cls;
            this.componentType = typeDescription;
            this.values = list;
        }

        public static <W extends Enum<W>> AnnotationValue<EnumerationDescription[], W[]> of(TypeDescription typeDescription, EnumerationDescription[] enumerationDescriptionArr) {
            ArrayList arrayList = new ArrayList(enumerationDescriptionArr.length);
            for (EnumerationDescription enumerationDescription : enumerationDescriptionArr) {
                if (!enumerationDescription.getEnumerationType().equals(typeDescription)) {
                    throw new IllegalArgumentException(enumerationDescription + " is not of " + typeDescription);
                }
                arrayList.add(ForEnumerationDescription.of(enumerationDescription));
            }
            return new ForDescriptionArray(EnumerationDescription.class, typeDescription, arrayList);
        }

        public static <W extends Annotation> AnnotationValue<AnnotationDescription[], W[]> of(TypeDescription typeDescription, AnnotationDescription[] annotationDescriptionArr) {
            ArrayList arrayList = new ArrayList(annotationDescriptionArr.length);
            for (AnnotationDescription annotationDescription : annotationDescriptionArr) {
                if (!annotationDescription.getAnnotationType().equals(typeDescription)) {
                    throw new IllegalArgumentException(annotationDescription + " is not of " + typeDescription);
                }
                arrayList.add(new ForAnnotationDescription(annotationDescription));
            }
            return new ForDescriptionArray(AnnotationDescription.class, typeDescription, arrayList);
        }

        public static AnnotationValue<TypeDescription[], Class<?>[]> of(TypeDescription[] typeDescriptionArr) {
            ArrayList arrayList = new ArrayList(typeDescriptionArr.length);
            for (TypeDescription typeDescription : typeDescriptionArr) {
                arrayList.add(ForTypeDescription.of(typeDescription));
            }
            return new ForDescriptionArray(TypeDescription.class, TypeDescription.CLASS, arrayList);
        }

        @Override // net.bytebuddy.description.annotation.AnnotationValue
        public U[] resolve() {
            U[] uArr = (U[]) ((Object[]) Array.newInstance(this.unloadedComponentType, this.values.size()));
            Iterator<? extends AnnotationValue<?, ?>> it = this.values.iterator();
            int i = 0;
            while (it.hasNext()) {
                Array.set(uArr, i, it.next().resolve());
                i++;
            }
            return uArr;
        }

        @Override // net.bytebuddy.description.annotation.AnnotationValue
        public Loaded<V[]> load(ClassLoader classLoader) throws ClassNotFoundException {
            ArrayList arrayList = new ArrayList(this.values.size());
            Iterator<? extends AnnotationValue<?, ?>> it = this.values.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().load(classLoader));
            }
            return new Loaded(Class.forName(this.componentType.getName(), false, classLoader), arrayList);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AnnotationValue)) {
                return false;
            }
            Object objResolve = ((AnnotationValue) obj).resolve();
            if (!(objResolve instanceof Object[])) {
                return false;
            }
            Object[] objArr = (Object[]) objResolve;
            if (this.values.size() != objArr.length) {
                return false;
            }
            Iterator<? extends AnnotationValue<?, ?>> it = this.values.iterator();
            for (Object obj2 : objArr) {
                if (!it.next().resolve().equals(obj2)) {
                    return false;
                }
            }
            return true;
        }

        public int hashCode() {
            Iterator<? extends AnnotationValue<?, ?>> it = this.values.iterator();
            int iHashCode = 1;
            while (it.hasNext()) {
                iHashCode = (iHashCode * 31) + it.next().hashCode();
            }
            return iHashCode;
        }

        public String toString() {
            return RenderingDispatcher.CURRENT.toSourceString(this.values);
        }

        protected static class Loaded<W> extends Loaded.AbstractBase<W[]> {
            private final Class<W> componentType;
            private final List<Loaded<?>> values;

            protected Loaded(Class<W> cls, List<Loaded<?>> list) {
                this.componentType = cls;
                this.values = list;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue.Loaded
            public Loaded.State getState() {
                Iterator<Loaded<?>> it = this.values.iterator();
                while (it.hasNext()) {
                    if (!it.next().getState().isResolved()) {
                        return Loaded.State.UNRESOLVED;
                    }
                }
                return Loaded.State.RESOLVED;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue.Loaded, net.bytebuddy.description.annotation.AnnotationValue
            public W[] resolve() {
                W[] wArr = (W[]) ((Object[]) Array.newInstance((Class<?>) this.componentType, this.values.size()));
                Iterator<Loaded<?>> it = this.values.iterator();
                int i = 0;
                while (it.hasNext()) {
                    Array.set(wArr, i, it.next().resolve());
                    i++;
                }
                return wArr;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationValue.Loaded
            public boolean represents(Object obj) {
                if (!(obj instanceof Object[]) || obj.getClass().getComponentType() != this.componentType) {
                    return false;
                }
                Object[] objArr = (Object[]) obj;
                if (this.values.size() != objArr.length) {
                    return false;
                }
                Iterator<Loaded<?>> it = this.values.iterator();
                for (Object obj2 : objArr) {
                    if (!it.next().represents(obj2)) {
                        return false;
                    }
                }
                return true;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Loaded)) {
                    return false;
                }
                Loaded loaded = (Loaded) obj;
                if (!loaded.getState().isResolved()) {
                    return false;
                }
                Object objResolve = loaded.resolve();
                if (!(objResolve instanceof Object[])) {
                    return false;
                }
                Object[] objArr = (Object[]) objResolve;
                if (this.values.size() != objArr.length) {
                    return false;
                }
                Iterator<Loaded<?>> it = this.values.iterator();
                for (Object obj2 : objArr) {
                    Loaded<?> next = it.next();
                    if (!next.getState().isResolved() || !next.resolve().equals(obj2)) {
                        return false;
                    }
                }
                return true;
            }

            public int hashCode() {
                Iterator<Loaded<?>> it = this.values.iterator();
                int iHashCode = 1;
                while (it.hasNext()) {
                    iHashCode = (iHashCode * 31) + it.next().hashCode();
                }
                return iHashCode;
            }

            public String toString() {
                return RenderingDispatcher.CURRENT.toSourceString(this.values);
            }
        }
    }
}
