package net.bytebuddy;

import cerence.ark.assistant.library.JsonKey;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.jar.asm.ClassReader;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.Opcodes;

/* JADX INFO: loaded from: classes3.dex */
public class ClassFileVersion implements Comparable<ClassFileVersion> {
    protected static final int BASE_VERSION = 44;
    private final int versionNumber;
    public static final ClassFileVersion JAVA_V1 = new ClassFileVersion(Opcodes.V1_1);
    public static final ClassFileVersion JAVA_V2 = new ClassFileVersion(46);
    public static final ClassFileVersion JAVA_V3 = new ClassFileVersion(47);
    public static final ClassFileVersion JAVA_V4 = new ClassFileVersion(48);
    public static final ClassFileVersion JAVA_V5 = new ClassFileVersion(49);
    public static final ClassFileVersion JAVA_V6 = new ClassFileVersion(50);
    public static final ClassFileVersion JAVA_V7 = new ClassFileVersion(51);
    public static final ClassFileVersion JAVA_V8 = new ClassFileVersion(52);
    public static final ClassFileVersion JAVA_V9 = new ClassFileVersion(53);
    public static final ClassFileVersion JAVA_V10 = new ClassFileVersion(53);
    private static final VersionLocator VERSION_LOCATOR = (VersionLocator) AccessController.doPrivileged(VersionLocator.CreationAction.INSTANCE);

    protected boolean canEqual(Object obj) {
        return obj instanceof ClassFileVersion;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ClassFileVersion)) {
            return false;
        }
        ClassFileVersion classFileVersion = (ClassFileVersion) obj;
        return classFileVersion.canEqual(this) && this.versionNumber == classFileVersion.versionNumber;
    }

    public int hashCode() {
        return 59 + this.versionNumber;
    }

    protected ClassFileVersion(int i) {
        this.versionNumber = i;
    }

    public static ClassFileVersion ofMinorMajor(int i) {
        ClassFileVersion classFileVersion = new ClassFileVersion(i);
        if (classFileVersion.getMajorVersion() > 44) {
            return classFileVersion;
        }
        throw new IllegalArgumentException("Class version " + i + " is not valid");
    }

    public static ClassFileVersion ofJavaVersion(int i) {
        switch (i) {
            case 1:
                return JAVA_V1;
            case 2:
                return JAVA_V2;
            case 3:
                return JAVA_V3;
            case 4:
                return JAVA_V4;
            case 5:
                return JAVA_V5;
            case 6:
                return JAVA_V6;
            case 7:
                return JAVA_V7;
            case 8:
                return JAVA_V8;
            case 9:
                return JAVA_V9;
            case 10:
                return JAVA_V10;
            default:
                throw new IllegalArgumentException("Unknown Java version: " + i);
        }
    }

    public static ClassFileVersion ofThisVm() {
        return VERSION_LOCATOR.locate();
    }

    public static ClassFileVersion ofThisVm(ClassFileVersion classFileVersion) {
        try {
            return ofThisVm();
        } catch (Exception unused) {
            return classFileVersion;
        }
    }

    public static ClassFileVersion of(Class<?> cls) throws IOException {
        return of(cls, ClassFileLocator.ForClassLoader.of(cls.getClassLoader()));
    }

    public static ClassFileVersion of(Class<?> cls, ClassFileLocator classFileLocator) throws IOException {
        return of(new TypeDescription.ForLoadedType(cls), classFileLocator);
    }

    public static ClassFileVersion of(TypeDescription typeDescription, ClassFileLocator classFileLocator) throws IOException {
        ClassReader classReader = new ClassReader(classFileLocator.locate(typeDescription.getName()).resolve());
        VersionExtractor versionExtractor = new VersionExtractor();
        classReader.accept(versionExtractor, 1);
        return ofMinorMajor(versionExtractor.getClassFileVersionNumber());
    }

    public int getMinorMajorVersion() {
        return this.versionNumber;
    }

    public int getMajorVersion() {
        return this.versionNumber & 255;
    }

    public int getMinorVersion() {
        return this.versionNumber >> 16;
    }

    public int getJavaVersion() {
        return getMajorVersion() - 44;
    }

    public boolean isAtLeast(ClassFileVersion classFileVersion) {
        return compareTo(classFileVersion) > -1;
    }

    public boolean isGreaterThan(ClassFileVersion classFileVersion) {
        return compareTo(classFileVersion) > 0;
    }

    public boolean isAtMost(ClassFileVersion classFileVersion) {
        return compareTo(classFileVersion) < 1;
    }

    public boolean isLessThan(ClassFileVersion classFileVersion) {
        return compareTo(classFileVersion) < 0;
    }

    @Override // java.lang.Comparable
    public int compareTo(ClassFileVersion classFileVersion) {
        int majorVersion;
        int majorVersion2;
        if (getMajorVersion() == classFileVersion.getMajorVersion()) {
            majorVersion = getMinorVersion();
            majorVersion2 = classFileVersion.getMinorVersion();
        } else {
            majorVersion = getMajorVersion();
            majorVersion2 = classFileVersion.getMajorVersion();
        }
        return Integer.signum(majorVersion - majorVersion2);
    }

    protected interface VersionLocator {
        ClassFileVersion locate();

        public enum CreationAction implements PrivilegedAction<VersionLocator> {
            INSTANCE;

            @Override // java.security.PrivilegedAction
            public VersionLocator run() {
                try {
                    return new ForJava9CapableVm(Runtime.class.getMethod(JsonKey.VERSION_NAME, new Class[0]), Class.forName("java.lang.Runtime$Version").getMethod("major", new Class[0]));
                } catch (Exception unused) {
                    return ForLegacyVm.INSTANCE;
                }
            }
        }

        public static class ForJava9CapableVm implements VersionLocator {
            private static final Object STATIC_METHOD = null;
            private final Method current;
            private final Method major;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForJava9CapableVm;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForJava9CapableVm)) {
                    return false;
                }
                ForJava9CapableVm forJava9CapableVm = (ForJava9CapableVm) obj;
                if (!forJava9CapableVm.canEqual(this)) {
                    return false;
                }
                Method method = this.current;
                Method method2 = forJava9CapableVm.current;
                if (method != null ? !method.equals(method2) : method2 != null) {
                    return false;
                }
                Method method3 = this.major;
                Method method4 = forJava9CapableVm.major;
                return method3 != null ? method3.equals(method4) : method4 == null;
            }

            public int hashCode() {
                Method method = this.current;
                int iHashCode = method == null ? 43 : method.hashCode();
                Method method2 = this.major;
                return ((iHashCode + 59) * 59) + (method2 != null ? method2.hashCode() : 43);
            }

            protected ForJava9CapableVm(Method method, Method method2) {
                this.current = method;
                this.major = method2;
            }

            @Override // net.bytebuddy.ClassFileVersion.VersionLocator
            public ClassFileVersion locate() {
                try {
                    return ClassFileVersion.ofJavaVersion(((Integer) this.major.invoke(this.current.invoke(STATIC_METHOD, new Object[0]), new Object[0])).intValue());
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Could not access VM version lookup", e);
                } catch (InvocationTargetException e2) {
                    throw new IllegalStateException("Could not look up VM version", e2.getCause());
                }
            }
        }

        public enum ForLegacyVm implements VersionLocator, PrivilegedAction<String> {
            INSTANCE;

            private static final String JAVA_VERSION_PROPERTY = "java.version";

            @Override // net.bytebuddy.ClassFileVersion.VersionLocator
            public ClassFileVersion locate() {
                String str = (String) AccessController.doPrivileged(this);
                int[] iArr = {-1, 0, 0};
                for (int i = 1; i < 3; i++) {
                    int iIndexOf = str.indexOf(46, iArr[i - 1] + 1);
                    iArr[i] = iIndexOf;
                    if (iIndexOf == -1) {
                        throw new IllegalStateException("This JVM's version string does not seem to be valid: " + str);
                    }
                }
                return ClassFileVersion.ofJavaVersion(Integer.parseInt(str.substring(iArr[1] + 1, iArr[2])));
            }

            @Override // java.security.PrivilegedAction
            public String run() {
                return System.getProperty(JAVA_VERSION_PROPERTY);
            }
        }
    }

    protected static class VersionExtractor extends ClassVisitor {
        private int classFileVersionNumber;

        protected VersionExtractor() {
            super(Opcodes.ASM6);
        }

        @Override // net.bytebuddy.jar.asm.ClassVisitor
        public void visit(int i, int i2, String str, String str2, String str3, String[] strArr) {
            this.classFileVersionNumber = i;
        }

        protected int getClassFileVersionNumber() {
            return this.classFileVersionNumber;
        }
    }
}
