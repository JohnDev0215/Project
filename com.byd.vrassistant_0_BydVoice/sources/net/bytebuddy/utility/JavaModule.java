package net.bytebuddy.utility;

import java.io.InputStream;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import net.bytebuddy.description.NamedElement;

/* JADX INFO: loaded from: classes3.dex */
public class JavaModule implements NamedElement.WithOptionalName {
    private static final Dispatcher DISPATCHER = (Dispatcher) AccessController.doPrivileged(Dispatcher.CreationAction.INSTANCE);
    public static final JavaModule UNSUPPORTED = null;
    private final Object module;

    protected JavaModule(Object obj) {
        this.module = obj;
    }

    public static JavaModule ofType(Class<?> cls) {
        return DISPATCHER.moduleOf(cls);
    }

    public static JavaModule of(Object obj) {
        if (!JavaType.MODULE.getTypeStub().isInstance(obj)) {
            throw new IllegalArgumentException("Not a Java module: " + obj);
        }
        return new JavaModule(obj);
    }

    public static boolean isSupported() {
        return DISPATCHER.isAlive();
    }

    @Override // net.bytebuddy.description.NamedElement.WithOptionalName
    public boolean isNamed() {
        return DISPATCHER.isNamed(this.module);
    }

    @Override // net.bytebuddy.description.NamedElement
    public String getActualName() {
        return DISPATCHER.getName(this.module);
    }

    public InputStream getResourceAsStream(String str) {
        return DISPATCHER.getResourceAsStream(this.module, str);
    }

    public ClassLoader getClassLoader() {
        return DISPATCHER.getClassLoader(this.module);
    }

    public Object unwrap() {
        return this.module;
    }

    public boolean canRead(JavaModule javaModule) {
        return DISPATCHER.canRead(this.module, javaModule.unwrap());
    }

    public void addReads(Instrumentation instrumentation, JavaModule javaModule) {
        DISPATCHER.addReads(instrumentation, this.module, javaModule.unwrap());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof JavaModule) {
            return this.module.equals(((JavaModule) obj).module);
        }
        return false;
    }

    public int hashCode() {
        return this.module.hashCode();
    }

    public String toString() {
        return this.module.toString();
    }

    protected interface Dispatcher {
        void addReads(Instrumentation instrumentation, Object obj, Object obj2);

        boolean canRead(Object obj, Object obj2);

        ClassLoader getClassLoader(Object obj);

        String getName(Object obj);

        InputStream getResourceAsStream(Object obj, String str);

        boolean isAlive();

        boolean isNamed(Object obj);

        JavaModule moduleOf(Class<?> cls);

        public enum CreationAction implements PrivilegedAction<Dispatcher> {
            INSTANCE;

            @Override // java.security.PrivilegedAction
            public Dispatcher run() {
                try {
                    Class<?> cls = Class.forName("java.lang.Module");
                    return new Enabled(Class.class.getMethod("getModule", new Class[0]), cls.getMethod("getClassLoader", new Class[0]), cls.getMethod("isNamed", new Class[0]), cls.getMethod("getName", new Class[0]), cls.getMethod("getResourceAsStream", String.class), cls.getMethod("canRead", cls), Instrumentation.class.getMethod("isModifiableModule", cls), Instrumentation.class.getMethod("redefineModule", cls, Set.class, Map.class, Map.class, Set.class, Map.class));
                } catch (Exception unused) {
                    return Disabled.INSTANCE;
                }
            }
        }

        public static class Enabled implements Dispatcher {
            private final Method canRead;
            private final Method getClassLoader;
            private final Method getModule;
            private final Method getName;
            private final Method getResourceAsStream;
            private final Method isModifiableModule;
            private final Method isNamed;
            private final Method redefineModule;

            @Override // net.bytebuddy.utility.JavaModule.Dispatcher
            public boolean isAlive() {
                return true;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof Enabled;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Enabled)) {
                    return false;
                }
                Enabled enabled = (Enabled) obj;
                if (!enabled.canEqual(this)) {
                    return false;
                }
                Method method = this.getModule;
                Method method2 = enabled.getModule;
                if (method != null ? !method.equals(method2) : method2 != null) {
                    return false;
                }
                Method method3 = this.getClassLoader;
                Method method4 = enabled.getClassLoader;
                if (method3 != null ? !method3.equals(method4) : method4 != null) {
                    return false;
                }
                Method method5 = this.isNamed;
                Method method6 = enabled.isNamed;
                if (method5 != null ? !method5.equals(method6) : method6 != null) {
                    return false;
                }
                Method method7 = this.getName;
                Method method8 = enabled.getName;
                if (method7 != null ? !method7.equals(method8) : method8 != null) {
                    return false;
                }
                Method method9 = this.getResourceAsStream;
                Method method10 = enabled.getResourceAsStream;
                if (method9 != null ? !method9.equals(method10) : method10 != null) {
                    return false;
                }
                Method method11 = this.canRead;
                Method method12 = enabled.canRead;
                if (method11 != null ? !method11.equals(method12) : method12 != null) {
                    return false;
                }
                Method method13 = this.isModifiableModule;
                Method method14 = enabled.isModifiableModule;
                if (method13 != null ? !method13.equals(method14) : method14 != null) {
                    return false;
                }
                Method method15 = this.redefineModule;
                Method method16 = enabled.redefineModule;
                return method15 != null ? method15.equals(method16) : method16 == null;
            }

            public int hashCode() {
                Method method = this.getModule;
                int iHashCode = method == null ? 43 : method.hashCode();
                Method method2 = this.getClassLoader;
                int iHashCode2 = ((iHashCode + 59) * 59) + (method2 == null ? 43 : method2.hashCode());
                Method method3 = this.isNamed;
                int iHashCode3 = (iHashCode2 * 59) + (method3 == null ? 43 : method3.hashCode());
                Method method4 = this.getName;
                int iHashCode4 = (iHashCode3 * 59) + (method4 == null ? 43 : method4.hashCode());
                Method method5 = this.getResourceAsStream;
                int iHashCode5 = (iHashCode4 * 59) + (method5 == null ? 43 : method5.hashCode());
                Method method6 = this.canRead;
                int iHashCode6 = (iHashCode5 * 59) + (method6 == null ? 43 : method6.hashCode());
                Method method7 = this.isModifiableModule;
                int iHashCode7 = (iHashCode6 * 59) + (method7 == null ? 43 : method7.hashCode());
                Method method8 = this.redefineModule;
                return (iHashCode7 * 59) + (method8 != null ? method8.hashCode() : 43);
            }

            protected Enabled(Method method, Method method2, Method method3, Method method4, Method method5, Method method6, Method method7, Method method8) {
                this.getModule = method;
                this.getClassLoader = method2;
                this.isNamed = method3;
                this.getName = method4;
                this.getResourceAsStream = method5;
                this.canRead = method6;
                this.isModifiableModule = method7;
                this.redefineModule = method8;
            }

            @Override // net.bytebuddy.utility.JavaModule.Dispatcher
            public JavaModule moduleOf(Class<?> cls) {
                try {
                    return new JavaModule(this.getModule.invoke(cls, new Object[0]));
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Cannot access " + this.getModule, e);
                } catch (InvocationTargetException e2) {
                    throw new IllegalStateException("Cannot invoke " + this.getModule, e2.getCause());
                }
            }

            @Override // net.bytebuddy.utility.JavaModule.Dispatcher
            public InputStream getResourceAsStream(Object obj, String str) {
                try {
                    return (InputStream) this.getResourceAsStream.invoke(obj, str);
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Cannot access " + this.getResourceAsStream, e);
                } catch (InvocationTargetException e2) {
                    throw new IllegalStateException("Cannot invoke " + this.getResourceAsStream, e2.getCause());
                }
            }

            @Override // net.bytebuddy.utility.JavaModule.Dispatcher
            public ClassLoader getClassLoader(Object obj) {
                try {
                    return (ClassLoader) this.getClassLoader.invoke(obj, new Object[0]);
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Cannot access " + this.getClassLoader, e);
                } catch (InvocationTargetException e2) {
                    throw new IllegalStateException("Cannot invoke " + this.getClassLoader, e2.getCause());
                }
            }

            @Override // net.bytebuddy.utility.JavaModule.Dispatcher
            public boolean isNamed(Object obj) {
                try {
                    return ((Boolean) this.isNamed.invoke(obj, new Object[0])).booleanValue();
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Cannot access " + this.isNamed, e);
                } catch (InvocationTargetException e2) {
                    throw new IllegalStateException("Cannot invoke " + this.isNamed, e2.getCause());
                }
            }

            @Override // net.bytebuddy.utility.JavaModule.Dispatcher
            public String getName(Object obj) {
                try {
                    return (String) this.getName.invoke(obj, new Object[0]);
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Cannot access " + this.getName, e);
                } catch (InvocationTargetException e2) {
                    throw new IllegalStateException("Cannot invoke " + this.getName, e2.getCause());
                }
            }

            @Override // net.bytebuddy.utility.JavaModule.Dispatcher
            public boolean canRead(Object obj, Object obj2) {
                try {
                    return ((Boolean) this.canRead.invoke(obj, obj2)).booleanValue();
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Cannot access " + this.canRead, e);
                } catch (InvocationTargetException e2) {
                    throw new IllegalStateException("Cannot invoke " + this.canRead, e2.getCause());
                }
            }

            @Override // net.bytebuddy.utility.JavaModule.Dispatcher
            public void addReads(Instrumentation instrumentation, Object obj, Object obj2) {
                try {
                    if (!((Boolean) this.isModifiableModule.invoke(instrumentation, obj)).booleanValue()) {
                        throw new IllegalStateException(obj + " is not modifable");
                    }
                    try {
                        this.redefineModule.invoke(instrumentation, obj, Collections.singleton(obj2), Collections.emptyMap(), Collections.emptyMap(), Collections.emptySet(), Collections.emptyMap());
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access " + this.redefineModule, e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Cannot invoke " + this.redefineModule, e2.getCause());
                    }
                } catch (IllegalAccessException e3) {
                    throw new IllegalStateException("Cannot access " + this.redefineModule, e3);
                } catch (InvocationTargetException e4) {
                    throw new IllegalStateException("Cannot invoke " + this.redefineModule, e4.getCause());
                }
            }
        }

        public enum Disabled implements Dispatcher {
            INSTANCE;

            @Override // net.bytebuddy.utility.JavaModule.Dispatcher
            public boolean isAlive() {
                return false;
            }

            @Override // net.bytebuddy.utility.JavaModule.Dispatcher
            public JavaModule moduleOf(Class<?> cls) {
                return JavaModule.UNSUPPORTED;
            }

            @Override // net.bytebuddy.utility.JavaModule.Dispatcher
            public ClassLoader getClassLoader(Object obj) {
                throw new IllegalStateException("Current VM does not support modules");
            }

            @Override // net.bytebuddy.utility.JavaModule.Dispatcher
            public boolean isNamed(Object obj) {
                throw new IllegalStateException("Current VM does not support modules");
            }

            @Override // net.bytebuddy.utility.JavaModule.Dispatcher
            public String getName(Object obj) {
                throw new IllegalStateException("Current VM does not support modules");
            }

            @Override // net.bytebuddy.utility.JavaModule.Dispatcher
            public InputStream getResourceAsStream(Object obj, String str) {
                throw new IllegalStateException("Current VM does not support modules");
            }

            @Override // net.bytebuddy.utility.JavaModule.Dispatcher
            public boolean canRead(Object obj, Object obj2) {
                throw new IllegalStateException("Current VM does not support modules");
            }

            @Override // net.bytebuddy.utility.JavaModule.Dispatcher
            public void addReads(Instrumentation instrumentation, Object obj, Object obj2) {
                throw new IllegalStateException("Current VM does not support modules");
            }
        }
    }
}
