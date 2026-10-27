package net.bytebuddy.jar.asm;

import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.android.multidex.ClassPathElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import net.bytebuddy.pool.TypePool;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
public class Type {
    public static final int ARRAY = 9;
    public static final int BOOLEAN = 1;
    public static final int BYTE = 3;
    public static final int CHAR = 2;
    public static final int DOUBLE = 8;
    public static final int FLOAT = 6;
    public static final int INT = 5;
    public static final int LONG = 7;
    public static final int METHOD = 11;
    public static final int OBJECT = 10;
    public static final int SHORT = 4;
    public static final int VOID = 0;
    private final char[] buf;
    private final int len;
    private final int off;
    private final int sort;
    public static final Type VOID_TYPE = new Type(0, null, 1443168256, 1);
    public static final Type BOOLEAN_TYPE = new Type(1, null, 1509950721, 1);
    public static final Type CHAR_TYPE = new Type(2, null, 1124075009, 1);
    public static final Type BYTE_TYPE = new Type(3, null, 1107297537, 1);
    public static final Type SHORT_TYPE = new Type(4, null, 1392510721, 1);
    public static final Type INT_TYPE = new Type(5, null, 1224736769, 1);
    public static final Type FLOAT_TYPE = new Type(6, null, 1174536705, 1);
    public static final Type LONG_TYPE = new Type(7, null, 1241579778, 1);
    public static final Type DOUBLE_TYPE = new Type(8, null, 1141048066, 1);

    private Type(int i, char[] cArr, int i2, int i3) {
        this.sort = i;
        this.buf = cArr;
        this.off = i2;
        this.len = i3;
    }

    public static Type getType(String str) {
        return getType(str.toCharArray(), 0);
    }

    public static Type getObjectType(String str) {
        char[] charArray = str.toCharArray();
        return new Type(charArray[0] == '[' ? 9 : 10, charArray, 0, charArray.length);
    }

    public static Type getMethodType(String str) {
        return getType(str.toCharArray(), 0);
    }

    public static Type getMethodType(Type type, Type... typeArr) {
        return getType(getMethodDescriptor(type, typeArr));
    }

    public static Type getType(Class<?> cls) {
        if (cls.isPrimitive()) {
            if (cls == Integer.TYPE) {
                return INT_TYPE;
            }
            if (cls == Void.TYPE) {
                return VOID_TYPE;
            }
            if (cls == Boolean.TYPE) {
                return BOOLEAN_TYPE;
            }
            if (cls == Byte.TYPE) {
                return BYTE_TYPE;
            }
            if (cls == Character.TYPE) {
                return CHAR_TYPE;
            }
            if (cls == Short.TYPE) {
                return SHORT_TYPE;
            }
            if (cls == Double.TYPE) {
                return DOUBLE_TYPE;
            }
            if (cls == Float.TYPE) {
                return FLOAT_TYPE;
            }
            return LONG_TYPE;
        }
        return getType(getDescriptor(cls));
    }

    public static Type getType(Constructor<?> constructor) {
        return getType(getConstructorDescriptor(constructor));
    }

    public static Type getType(Method method) {
        return getType(getMethodDescriptor(method));
    }

    public static Type[] getArgumentTypes(String str) {
        char[] charArray = str.toCharArray();
        int i = 1;
        int i2 = 1;
        int i3 = 0;
        while (true) {
            int i4 = i2 + 1;
            char c = charArray[i2];
            if (c == ')') {
                break;
            }
            if (c == 'L') {
                while (true) {
                    i2 = i4 + 1;
                    if (charArray[i4] == ';') {
                        break;
                    }
                    i4 = i2;
                }
                i3++;
            } else {
                if (c != '[') {
                    i3++;
                }
                i2 = i4;
            }
        }
        Type[] typeArr = new Type[i3];
        int i5 = 0;
        while (charArray[i] != ')') {
            Type type = getType(charArray, i);
            typeArr[i5] = type;
            i += type.len + (type.sort == 10 ? 2 : 0);
            i5++;
        }
        return typeArr;
    }

    public static Type[] getArgumentTypes(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        Type[] typeArr = new Type[parameterTypes.length];
        for (int length = parameterTypes.length - 1; length >= 0; length--) {
            typeArr[length] = getType(parameterTypes[length]);
        }
        return typeArr;
    }

    public static Type getReturnType(String str) {
        char[] charArray = str.toCharArray();
        int i = 1;
        while (true) {
            int i2 = i + 1;
            char c = charArray[i];
            if (c == ')') {
                return getType(charArray, i2);
            }
            if (c == 'L') {
                while (true) {
                    i = i2 + 1;
                    if (charArray[i2] != ';') {
                        i2 = i;
                    }
                }
            } else {
                i = i2;
            }
        }
    }

    public static Type getReturnType(Method method) {
        return getType(method.getReturnType());
    }

    public static int getArgumentsAndReturnSizes(String str) {
        int i;
        char cCharAt;
        int i2 = 1;
        int i3 = 1;
        int i4 = 1;
        while (true) {
            i = i3 + 1;
            char cCharAt2 = str.charAt(i3);
            if (cCharAt2 == ')') {
                break;
            }
            if (cCharAt2 == 'L') {
                while (true) {
                    i3 = i + 1;
                    if (str.charAt(i) == ';') {
                        break;
                    }
                    i = i3;
                }
                i4++;
            } else {
                if (cCharAt2 == '[') {
                    while (true) {
                        cCharAt = str.charAt(i);
                        if (cCharAt != '[') {
                            break;
                        }
                        i++;
                    }
                    if (cCharAt == 'D' || cCharAt == 'J') {
                        i4--;
                    }
                } else {
                    i4 = (cCharAt2 == 'D' || cCharAt2 == 'J') ? i4 + 2 : i4 + 1;
                }
                i3 = i;
            }
        }
        char cCharAt3 = str.charAt(i);
        int i5 = i4 << 2;
        if (cCharAt3 == 'V') {
            i2 = 0;
        } else if (cCharAt3 == 'D' || cCharAt3 == 'J') {
            i2 = 2;
        }
        return i5 | i2;
    }

    private static Type getType(char[] cArr, int i) {
        char c;
        char c2 = cArr[i];
        if (c2 == 'F') {
            return FLOAT_TYPE;
        }
        if (c2 == 'L') {
            int i2 = 1;
            while (cArr[i + i2] != ';') {
                i2++;
            }
            return new Type(10, cArr, i + 1, i2 - 1);
        }
        if (c2 == 'S') {
            return SHORT_TYPE;
        }
        if (c2 == 'V') {
            return VOID_TYPE;
        }
        if (c2 == 'I') {
            return INT_TYPE;
        }
        if (c2 == 'J') {
            return LONG_TYPE;
        }
        if (c2 == 'Z') {
            return BOOLEAN_TYPE;
        }
        if (c2 != '[') {
            switch (c2) {
                case 'B':
                    return BYTE_TYPE;
                case 'C':
                    return CHAR_TYPE;
                case 'D':
                    return DOUBLE_TYPE;
                default:
                    return new Type(11, cArr, i, cArr.length - i);
            }
        }
        int i3 = 1;
        while (true) {
            c = cArr[i + i3];
            if (c != '[') {
                break;
            }
            i3++;
        }
        if (c == 'L') {
            do {
                i3++;
            } while (cArr[i + i3] != ';');
        }
        return new Type(9, cArr, i, i3 + 1);
    }

    public int getSort() {
        return this.sort;
    }

    public int getDimensions() {
        int i = 1;
        while (this.buf[this.off + i] == '[') {
            i++;
        }
        return i;
    }

    public Type getElementType() {
        return getType(this.buf, this.off + getDimensions());
    }

    public String getClassName() {
        switch (this.sort) {
            case 0:
                return "void";
            case 1:
                return "boolean";
            case 2:
                return "char";
            case 3:
                return "byte";
            case 4:
                return "short";
            case 5:
                return "int";
            case 6:
                return "float";
            case 7:
                return "long";
            case 8:
                return "double";
            case 9:
                StringBuilder sb = new StringBuilder(getElementType().getClassName());
                for (int dimensions = getDimensions(); dimensions > 0; dimensions--) {
                    sb.append(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
                }
                return sb.toString();
            case 10:
                return new String(this.buf, this.off, this.len).replace(ClassPathElement.SEPARATOR_CHAR, TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH);
            default:
                return null;
        }
    }

    public String getInternalName() {
        return new String(this.buf, this.off, this.len);
    }

    public Type[] getArgumentTypes() {
        return getArgumentTypes(getDescriptor());
    }

    public Type getReturnType() {
        return getReturnType(getDescriptor());
    }

    public int getArgumentsAndReturnSizes() {
        return getArgumentsAndReturnSizes(getDescriptor());
    }

    public String getDescriptor() {
        StringBuilder sb = new StringBuilder();
        getDescriptor(sb);
        return sb.toString();
    }

    public static String getMethodDescriptor(Type type, Type... typeArr) {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        for (Type type2 : typeArr) {
            type2.getDescriptor(sb);
        }
        sb.append(')');
        type.getDescriptor(sb);
        return sb.toString();
    }

    private void getDescriptor(StringBuilder sb) {
        char[] cArr = this.buf;
        if (cArr == null) {
            sb.append((char) ((this.off & ViewCompat.MEASURED_STATE_MASK) >>> 24));
        } else {
            if (this.sort == 10) {
                sb.append('L');
                sb.append(this.buf, this.off, this.len);
                sb.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.INDEXED_TYPE_DELIMITER);
                return;
            }
            sb.append(cArr, this.off, this.len);
        }
    }

    public static String getInternalName(Class<?> cls) {
        return cls.getName().replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, ClassPathElement.SEPARATOR_CHAR);
    }

    public static String getDescriptor(Class<?> cls) {
        StringBuilder sb = new StringBuilder();
        getDescriptor(sb, cls);
        return sb.toString();
    }

    public static String getConstructorDescriptor(Constructor<?> constructor) {
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        for (Class<?> cls : parameterTypes) {
            getDescriptor(sb, cls);
        }
        return sb.append(")V").toString();
    }

    public static String getMethodDescriptor(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        for (Class<?> cls : parameterTypes) {
            getDescriptor(sb, cls);
        }
        sb.append(')');
        getDescriptor(sb, method.getReturnType());
        return sb.toString();
    }

    private static void getDescriptor(StringBuilder sb, Class<?> cls) {
        char c;
        while (!cls.isPrimitive()) {
            if (cls.isArray()) {
                sb.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
                cls = cls.getComponentType();
            } else {
                sb.append('L');
                String name = cls.getName();
                int length = name.length();
                for (int i = 0; i < length; i++) {
                    char cCharAt = name.charAt(i);
                    if (cCharAt == '.') {
                        cCharAt = ClassPathElement.SEPARATOR_CHAR;
                    }
                    sb.append(cCharAt);
                }
                sb.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.INDEXED_TYPE_DELIMITER);
                return;
            }
        }
        if (cls == Integer.TYPE) {
            c = 'I';
        } else if (cls == Void.TYPE) {
            c = 'V';
        } else if (cls == Boolean.TYPE) {
            c = 'Z';
        } else if (cls == Byte.TYPE) {
            c = 'B';
        } else if (cls == Character.TYPE) {
            c = 'C';
        } else if (cls == Short.TYPE) {
            c = 'S';
        } else if (cls == Double.TYPE) {
            c = 'D';
        } else {
            c = cls == Float.TYPE ? 'F' : 'J';
        }
        sb.append(c);
    }

    public int getSize() {
        if (this.buf == null) {
            return this.off & 255;
        }
        return 1;
    }

    public int getOpcode(int i) {
        if (i == 46 || i == 79) {
            return i + (this.buf == null ? (this.off & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8 : 4);
        }
        return i + (this.buf == null ? (this.off & 16711680) >> 16 : 4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Type)) {
            return false;
        }
        Type type = (Type) obj;
        int i = this.sort;
        if (i != type.sort) {
            return false;
        }
        if (i >= 9) {
            int i2 = this.len;
            if (i2 != type.len) {
                return false;
            }
            int i3 = this.off;
            int i4 = type.off;
            int i5 = i2 + i3;
            while (i3 < i5) {
                if (this.buf[i3] != type.buf[i4]) {
                    return false;
                }
                i3++;
                i4++;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = this.sort;
        int i2 = i * 13;
        if (i >= 9) {
            int i3 = this.off;
            int i4 = this.len + i3;
            while (i3 < i4) {
                i2 = (i2 + this.buf[i3]) * 17;
                i3++;
            }
        }
        return i2;
    }

    public String toString() {
        return getDescriptor();
    }
}
