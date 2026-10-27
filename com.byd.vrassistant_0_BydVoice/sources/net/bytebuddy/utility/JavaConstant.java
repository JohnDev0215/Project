package net.bytebuddy.utility;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.constant.JavaConstantValue;
import net.bytebuddy.jar.asm.Handle;
import net.bytebuddy.jar.asm.Type;

/* JADX INFO: loaded from: classes3.dex */
public interface JavaConstant {
    Object asConstantPoolValue();

    StackManipulation asStackManipulation();

    TypeDescription getType();

    public static class MethodType implements JavaConstant {
        private static final Dispatcher DISPATCHER = (Dispatcher) AccessController.doPrivileged(Dispatcher.CreationAction.INSTANCE);
        private final List<? extends TypeDescription> parameterTypes;
        private final TypeDescription returnType;

        protected MethodType(TypeDescription typeDescription, List<? extends TypeDescription> list) {
            this.returnType = typeDescription;
            this.parameterTypes = list;
        }

        public static MethodType ofLoaded(Object obj) {
            if (!JavaType.METHOD_TYPE.getTypeStub().isInstance(obj)) {
                throw new IllegalArgumentException("Expected method type object: " + obj);
            }
            Dispatcher dispatcher = DISPATCHER;
            return of(dispatcher.returnType(obj), dispatcher.parameterArray(obj));
        }

        public static MethodType of(Class<?> cls, Class<?>... clsArr) {
            return of(new TypeDescription.ForLoadedType(cls), new TypeList.ForLoadedTypes(clsArr));
        }

        public static MethodType of(TypeDescription typeDescription, List<? extends TypeDescription> list) {
            return new MethodType(typeDescription, list);
        }

        public static MethodType of(Method method) {
            return of(new MethodDescription.ForLoadedMethod(method));
        }

        public static MethodType of(Constructor<?> constructor) {
            return of(new MethodDescription.ForLoadedConstructor(constructor));
        }

        public static MethodType of(MethodDescription methodDescription) {
            return new MethodType(methodDescription.getReturnType().asErasure(), methodDescription.getParameters().asTypeList().asErasures());
        }

        public static MethodType ofSetter(Field field) {
            return ofSetter(new FieldDescription.ForLoadedField(field));
        }

        public static MethodType ofSetter(FieldDescription fieldDescription) {
            return new MethodType(TypeDescription.VOID, Collections.singletonList(fieldDescription.getType().asErasure()));
        }

        public static MethodType ofGetter(Field field) {
            return ofGetter(new FieldDescription.ForLoadedField(field));
        }

        public static MethodType ofGetter(FieldDescription fieldDescription) {
            return new MethodType(fieldDescription.getType().asErasure(), Collections.emptyList());
        }

        public static MethodType ofConstant(Object obj) {
            return ofConstant(obj.getClass());
        }

        public static MethodType ofConstant(Class<?> cls) {
            return ofConstant((TypeDescription) new TypeDescription.ForLoadedType(cls));
        }

        public static MethodType ofConstant(TypeDescription typeDescription) {
            return new MethodType(typeDescription, Collections.emptyList());
        }

        public TypeDescription getReturnType() {
            return this.returnType;
        }

        public TypeList getParameterTypes() {
            return new TypeList.Explicit(this.parameterTypes);
        }

        public String getDescriptor() {
            StringBuilder sb = new StringBuilder("(");
            Iterator<? extends TypeDescription> it = this.parameterTypes.iterator();
            while (it.hasNext()) {
                sb.append(it.next().getDescriptor());
            }
            return sb.append(')').append(this.returnType.getDescriptor()).toString();
        }

        @Override // net.bytebuddy.utility.JavaConstant
        public Object asConstantPoolValue() {
            StringBuilder sb = new StringBuilder("(");
            Iterator it = getParameterTypes().iterator();
            while (it.hasNext()) {
                sb.append(((TypeDescription) it.next()).getDescriptor());
            }
            return Type.getMethodType(sb.append(")").append(getReturnType().getDescriptor()).toString());
        }

        @Override // net.bytebuddy.utility.JavaConstant
        public StackManipulation asStackManipulation() {
            return new JavaConstantValue(this);
        }

        @Override // net.bytebuddy.utility.JavaConstant
        public TypeDescription getType() {
            return JavaType.METHOD_TYPE.getTypeStub();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MethodType)) {
                return false;
            }
            MethodType methodType = (MethodType) obj;
            return this.parameterTypes.equals(methodType.parameterTypes) && this.returnType.equals(methodType.returnType);
        }

        public int hashCode() {
            return (this.returnType.hashCode() * 31) + this.parameterTypes.hashCode();
        }

        protected interface Dispatcher {
            Class<?>[] parameterArray(Object obj);

            Class<?> returnType(Object obj);

            public enum CreationAction implements PrivilegedAction<Dispatcher> {
                INSTANCE;

                @Override // java.security.PrivilegedAction
                public Dispatcher run() {
                    try {
                        Class<?> clsLoad = JavaType.METHOD_TYPE.load();
                        return new ForJava7CapableVm(clsLoad.getMethod("returnType", new Class[0]), clsLoad.getMethod("parameterArray", new Class[0]));
                    } catch (Exception unused) {
                        return ForLegacyVm.INSTANCE;
                    }
                }
            }

            public static class ForJava7CapableVm implements Dispatcher {
                private final Method parameterArray;
                private final Method returnType;

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForJava7CapableVm;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForJava7CapableVm)) {
                        return false;
                    }
                    ForJava7CapableVm forJava7CapableVm = (ForJava7CapableVm) obj;
                    if (!forJava7CapableVm.canEqual(this)) {
                        return false;
                    }
                    Method method = this.returnType;
                    Method method2 = forJava7CapableVm.returnType;
                    if (method != null ? !method.equals(method2) : method2 != null) {
                        return false;
                    }
                    Method method3 = this.parameterArray;
                    Method method4 = forJava7CapableVm.parameterArray;
                    return method3 != null ? method3.equals(method4) : method4 == null;
                }

                public int hashCode() {
                    Method method = this.returnType;
                    int iHashCode = method == null ? 43 : method.hashCode();
                    Method method2 = this.parameterArray;
                    return ((iHashCode + 59) * 59) + (method2 != null ? method2.hashCode() : 43);
                }

                protected ForJava7CapableVm(Method method, Method method2) {
                    this.returnType = method;
                    this.parameterArray = method2;
                }

                @Override // net.bytebuddy.utility.JavaConstant.MethodType.Dispatcher
                public Class<?> returnType(Object obj) {
                    try {
                        return (Class) this.returnType.invoke(obj, new Object[0]);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.invoke.MethodType#returnType", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.invoke.MethodType#returnType", e2.getCause());
                    }
                }

                @Override // net.bytebuddy.utility.JavaConstant.MethodType.Dispatcher
                public Class<?>[] parameterArray(Object obj) {
                    try {
                        return (Class[]) this.parameterArray.invoke(obj, new Object[0]);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.invoke.MethodType#parameterArray", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.invoke.MethodType#parameterArray", e2.getCause());
                    }
                }
            }

            public enum ForLegacyVm implements Dispatcher {
                INSTANCE;

                @Override // net.bytebuddy.utility.JavaConstant.MethodType.Dispatcher
                public Class<?> returnType(Object obj) {
                    throw new IllegalStateException("Unsupported type for the current JVM: java.lang.invoke.MethodType");
                }

                @Override // net.bytebuddy.utility.JavaConstant.MethodType.Dispatcher
                public Class<?>[] parameterArray(Object obj) {
                    throw new IllegalStateException("Unsupported type for the current JVM: java.lang.invoke.MethodType");
                }
            }
        }
    }

    public static class MethodHandle implements JavaConstant {
        private static final Dispatcher.Initializable DISPATCHER = (Dispatcher.Initializable) AccessController.doPrivileged(Dispatcher.CreationAction.INSTANCE);
        private final HandleType handleType;
        private final String name;
        private final TypeDescription ownerType;
        private final List<? extends TypeDescription> parameterTypes;
        private final TypeDescription returnType;

        protected MethodHandle(HandleType handleType, TypeDescription typeDescription, String str, TypeDescription typeDescription2, List<? extends TypeDescription> list) {
            this.handleType = handleType;
            this.ownerType = typeDescription;
            this.name = str;
            this.returnType = typeDescription2;
            this.parameterTypes = list;
        }

        public static MethodHandle ofLoaded(Object obj) {
            return ofLoaded(obj, DISPATCHER.publicLookup());
        }

        public static MethodHandle ofLoaded(Object obj, Object obj2) {
            if (!JavaType.METHOD_HANDLE.getTypeStub().isInstance(obj)) {
                throw new IllegalArgumentException("Expected method handle object: " + obj);
            }
            if (!JavaType.METHOD_HANDLES_LOOKUP.getTypeStub().isInstance(obj2)) {
                throw new IllegalArgumentException("Expected method handle lookup object: " + obj2);
            }
            Dispatcher dispatcherInitialize = DISPATCHER.initialize();
            Object objReveal = dispatcherInitialize.reveal(obj2, obj);
            Object methodType = dispatcherInitialize.getMethodType(objReveal);
            return new MethodHandle(HandleType.of(dispatcherInitialize.getReferenceKind(objReveal)), new TypeDescription.ForLoadedType(dispatcherInitialize.getDeclaringClass(objReveal)), dispatcherInitialize.getName(objReveal), new TypeDescription.ForLoadedType(dispatcherInitialize.returnType(methodType)), new TypeList.ForLoadedTypes(dispatcherInitialize.parameterArray(methodType)));
        }

        public static MethodHandle of(Method method) {
            return of(new MethodDescription.ForLoadedMethod(method));
        }

        public static MethodHandle of(Constructor<?> constructor) {
            return of(new MethodDescription.ForLoadedConstructor(constructor));
        }

        public static MethodHandle of(MethodDescription.InDefinedShape inDefinedShape) {
            return new MethodHandle(HandleType.of(inDefinedShape), inDefinedShape.getDeclaringType().asErasure(), inDefinedShape.getInternalName(), inDefinedShape.getReturnType().asErasure(), inDefinedShape.getParameters().asTypeList().asErasures());
        }

        public static MethodHandle ofSpecial(Method method, Class<?> cls) {
            return ofSpecial(new MethodDescription.ForLoadedMethod(method), new TypeDescription.ForLoadedType(cls));
        }

        public static MethodHandle ofSpecial(MethodDescription.InDefinedShape inDefinedShape, TypeDescription typeDescription) {
            if (!inDefinedShape.isSpecializableFor(typeDescription)) {
                throw new IllegalArgumentException("Cannot specialize " + inDefinedShape + " for " + typeDescription);
            }
            return new MethodHandle(HandleType.ofSpecial(inDefinedShape), typeDescription, inDefinedShape.getInternalName(), inDefinedShape.getReturnType().asErasure(), inDefinedShape.getParameters().asTypeList().asErasures());
        }

        public static MethodHandle ofGetter(Field field) {
            return ofGetter(new FieldDescription.ForLoadedField(field));
        }

        public static MethodHandle ofGetter(FieldDescription.InDefinedShape inDefinedShape) {
            return new MethodHandle(HandleType.ofGetter(inDefinedShape), inDefinedShape.getDeclaringType().asErasure(), inDefinedShape.getInternalName(), inDefinedShape.getType().asErasure(), Collections.emptyList());
        }

        public static MethodHandle ofSetter(Field field) {
            return ofSetter(new FieldDescription.ForLoadedField(field));
        }

        public static MethodHandle ofSetter(FieldDescription.InDefinedShape inDefinedShape) {
            return new MethodHandle(HandleType.ofSetter(inDefinedShape), inDefinedShape.getDeclaringType().asErasure(), inDefinedShape.getInternalName(), TypeDescription.VOID, Collections.singletonList(inDefinedShape.getType().asErasure()));
        }

        @Override // net.bytebuddy.utility.JavaConstant
        public Object asConstantPoolValue() {
            StringBuilder sb = new StringBuilder("(");
            Iterator it = getParameterTypes().iterator();
            while (it.hasNext()) {
                sb.append(((TypeDescription) it.next()).getDescriptor());
            }
            return new Handle(getHandleType().getIdentifier(), getOwnerType().getInternalName(), getName(), sb.append(")").append(getReturnType().getDescriptor()).toString(), getOwnerType().isInterface());
        }

        @Override // net.bytebuddy.utility.JavaConstant
        public StackManipulation asStackManipulation() {
            return new JavaConstantValue(this);
        }

        @Override // net.bytebuddy.utility.JavaConstant
        public TypeDescription getType() {
            return JavaType.METHOD_HANDLE.getTypeStub();
        }

        public HandleType getHandleType() {
            return this.handleType;
        }

        public TypeDescription getOwnerType() {
            return this.ownerType;
        }

        public String getName() {
            return this.name;
        }

        public TypeDescription getReturnType() {
            return this.returnType;
        }

        public TypeList getParameterTypes() {
            return new TypeList.Explicit(this.parameterTypes);
        }

        public String getDescriptor() {
            StringBuilder sb = new StringBuilder("(");
            Iterator<? extends TypeDescription> it = this.parameterTypes.iterator();
            while (it.hasNext()) {
                sb.append(it.next().getDescriptor());
            }
            return sb.append(')').append(this.returnType.getDescriptor()).toString();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MethodHandle)) {
                return false;
            }
            MethodHandle methodHandle = (MethodHandle) obj;
            return this.handleType == methodHandle.handleType && this.name.equals(methodHandle.name) && this.ownerType.equals(methodHandle.ownerType) && this.parameterTypes.equals(methodHandle.parameterTypes) && this.returnType.equals(methodHandle.returnType);
        }

        public int hashCode() {
            return (((((((this.handleType.hashCode() * 31) + this.ownerType.hashCode()) * 31) + this.name.hashCode()) * 31) + this.returnType.hashCode()) * 31) + this.parameterTypes.hashCode();
        }

        public static Class<?> lookupType(Object obj) {
            return DISPATCHER.lookupType(obj);
        }

        protected interface Dispatcher {

            public interface Initializable {
                Dispatcher initialize();

                Class<?> lookupType(Object obj);

                Object publicLookup();
            }

            Class<?> getDeclaringClass(Object obj);

            Object getMethodType(Object obj);

            String getName(Object obj);

            int getReferenceKind(Object obj);

            List<? extends Class<?>> parameterArray(Object obj);

            Class<?> returnType(Object obj);

            Object reveal(Object obj, Object obj2);

            public enum CreationAction implements PrivilegedAction<Initializable> {
                INSTANCE;

                @Override // java.security.PrivilegedAction
                public Initializable run() {
                    String str;
                    try {
                        str = "lookupClass";
                    } catch (Exception unused) {
                        str = "lookupClass";
                    }
                    try {
                        return new ForJava8CapableVm(Class.forName("java.lang.invoke.MethodHandles").getMethod("publicLookup", new Class[0]), Class.forName("java.lang.invoke.MethodHandleInfo").getMethod("getName", new Class[0]), Class.forName("java.lang.invoke.MethodHandleInfo").getMethod("getDeclaringClass", new Class[0]), Class.forName("java.lang.invoke.MethodHandleInfo").getMethod("getReferenceKind", new Class[0]), Class.forName("java.lang.invoke.MethodHandleInfo").getMethod("getMethodType", new Class[0]), JavaType.METHOD_TYPE.load().getMethod("returnType", new Class[0]), JavaType.METHOD_TYPE.load().getMethod("parameterArray", new Class[0]), JavaType.METHOD_HANDLES_LOOKUP.load().getMethod("lookupClass", new Class[0]), JavaType.METHOD_HANDLES_LOOKUP.load().getMethod("revealDirect", JavaType.METHOD_HANDLE.load()));
                    } catch (Exception unused2) {
                        try {
                            return new ForJava7CapableVm(Class.forName("java.lang.invoke.MethodHandles").getMethod("publicLookup", new Class[0]), Class.forName("java.lang.invoke.MethodHandleInfo").getMethod("getName", new Class[0]), Class.forName("java.lang.invoke.MethodHandleInfo").getMethod("getDeclaringClass", new Class[0]), Class.forName("java.lang.invoke.MethodHandleInfo").getMethod("getReferenceKind", new Class[0]), Class.forName("java.lang.invoke.MethodHandleInfo").getMethod("getMethodType", new Class[0]), JavaType.METHOD_TYPE.load().getMethod("returnType", new Class[0]), JavaType.METHOD_TYPE.load().getMethod("parameterArray", new Class[0]), JavaType.METHOD_HANDLES_LOOKUP.load().getMethod(str, new Class[0]), Class.forName("java.lang.invoke.MethodHandleInfo").getConstructor(JavaType.METHOD_HANDLE.load()));
                        } catch (Exception unused3) {
                            return ForLegacyVm.INSTANCE;
                        }
                    }
                }
            }

            public static abstract class AbstractBase implements Dispatcher, Initializable {
                protected final Method getDeclaringClass;
                protected final Method getMethodType;
                protected final Method getName;
                protected final Method getReferenceKind;
                protected final Method lookupClass;
                protected final Method parameterArray;
                protected final Method publicLookup;
                protected final Method returnType;

                protected boolean canEqual(Object obj) {
                    return obj instanceof AbstractBase;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof AbstractBase)) {
                        return false;
                    }
                    AbstractBase abstractBase = (AbstractBase) obj;
                    if (!abstractBase.canEqual(this)) {
                        return false;
                    }
                    Method method = this.publicLookup;
                    Method method2 = abstractBase.publicLookup;
                    if (method != null ? !method.equals(method2) : method2 != null) {
                        return false;
                    }
                    Method method3 = this.getName;
                    Method method4 = abstractBase.getName;
                    if (method3 != null ? !method3.equals(method4) : method4 != null) {
                        return false;
                    }
                    Method method5 = this.getDeclaringClass;
                    Method method6 = abstractBase.getDeclaringClass;
                    if (method5 != null ? !method5.equals(method6) : method6 != null) {
                        return false;
                    }
                    Method method7 = this.getReferenceKind;
                    Method method8 = abstractBase.getReferenceKind;
                    if (method7 != null ? !method7.equals(method8) : method8 != null) {
                        return false;
                    }
                    Method method9 = this.getMethodType;
                    Method method10 = abstractBase.getMethodType;
                    if (method9 != null ? !method9.equals(method10) : method10 != null) {
                        return false;
                    }
                    Method method11 = this.returnType;
                    Method method12 = abstractBase.returnType;
                    if (method11 != null ? !method11.equals(method12) : method12 != null) {
                        return false;
                    }
                    Method method13 = this.parameterArray;
                    Method method14 = abstractBase.parameterArray;
                    if (method13 != null ? !method13.equals(method14) : method14 != null) {
                        return false;
                    }
                    Method method15 = this.lookupClass;
                    Method method16 = abstractBase.lookupClass;
                    return method15 != null ? method15.equals(method16) : method16 == null;
                }

                public int hashCode() {
                    Method method = this.publicLookup;
                    int iHashCode = method == null ? 43 : method.hashCode();
                    Method method2 = this.getName;
                    int iHashCode2 = ((iHashCode + 59) * 59) + (method2 == null ? 43 : method2.hashCode());
                    Method method3 = this.getDeclaringClass;
                    int iHashCode3 = (iHashCode2 * 59) + (method3 == null ? 43 : method3.hashCode());
                    Method method4 = this.getReferenceKind;
                    int iHashCode4 = (iHashCode3 * 59) + (method4 == null ? 43 : method4.hashCode());
                    Method method5 = this.getMethodType;
                    int iHashCode5 = (iHashCode4 * 59) + (method5 == null ? 43 : method5.hashCode());
                    Method method6 = this.returnType;
                    int iHashCode6 = (iHashCode5 * 59) + (method6 == null ? 43 : method6.hashCode());
                    Method method7 = this.parameterArray;
                    int iHashCode7 = (iHashCode6 * 59) + (method7 == null ? 43 : method7.hashCode());
                    Method method8 = this.lookupClass;
                    return (iHashCode7 * 59) + (method8 != null ? method8.hashCode() : 43);
                }

                protected AbstractBase(Method method, Method method2, Method method3, Method method4, Method method5, Method method6, Method method7, Method method8) {
                    this.publicLookup = method;
                    this.getName = method2;
                    this.getDeclaringClass = method3;
                    this.getReferenceKind = method4;
                    this.getMethodType = method5;
                    this.returnType = method6;
                    this.parameterArray = method7;
                    this.lookupClass = method8;
                }

                @Override // net.bytebuddy.utility.JavaConstant.MethodHandle.Dispatcher.Initializable
                public Object publicLookup() {
                    try {
                        return this.publicLookup.invoke(null, new Object[0]);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.invoke.MethodHandles#publicLookup", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.invoke.MethodHandles#publicLookup", e2.getCause());
                    }
                }

                @Override // net.bytebuddy.utility.JavaConstant.MethodHandle.Dispatcher
                public Object getMethodType(Object obj) {
                    try {
                        return this.getMethodType.invoke(obj, new Object[0]);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.invoke.MethodHandleInfo#getMethodType", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.invoke.MethodHandleInfo#getMethodType", e2.getCause());
                    }
                }

                @Override // net.bytebuddy.utility.JavaConstant.MethodHandle.Dispatcher
                public int getReferenceKind(Object obj) {
                    try {
                        return ((Integer) this.getReferenceKind.invoke(obj, new Object[0])).intValue();
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.invoke.MethodHandleInfo#getReferenceKind", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.invoke.MethodHandleInfo#getReferenceKind", e2.getCause());
                    }
                }

                @Override // net.bytebuddy.utility.JavaConstant.MethodHandle.Dispatcher
                public Class<?> getDeclaringClass(Object obj) {
                    try {
                        return (Class) this.getDeclaringClass.invoke(obj, new Object[0]);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.invoke.MethodHandleInfo#getDeclaringClass", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.invoke.MethodHandleInfo#getDeclaringClass", e2.getCause());
                    }
                }

                @Override // net.bytebuddy.utility.JavaConstant.MethodHandle.Dispatcher
                public String getName(Object obj) {
                    try {
                        return (String) this.getName.invoke(obj, new Object[0]);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.invoke.MethodHandleInfo#getName", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.invoke.MethodHandleInfo#getName", e2.getCause());
                    }
                }

                @Override // net.bytebuddy.utility.JavaConstant.MethodHandle.Dispatcher
                public Class<?> returnType(Object obj) {
                    try {
                        return (Class) this.returnType.invoke(obj, new Object[0]);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.invoke.MethodType#returnType", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.reflect.MethodType#returnType", e2.getCause());
                    }
                }

                @Override // net.bytebuddy.utility.JavaConstant.MethodHandle.Dispatcher
                public List<? extends Class<?>> parameterArray(Object obj) {
                    try {
                        return Arrays.asList((Class[]) this.parameterArray.invoke(obj, new Object[0]));
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.reflect.MethodType#parameterArray", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.reflect.MethodType#parameterArray", e2.getCause());
                    }
                }

                @Override // net.bytebuddy.utility.JavaConstant.MethodHandle.Dispatcher.Initializable
                public Class<?> lookupType(Object obj) {
                    try {
                        return (Class) this.lookupClass.invoke(obj, new Object[0]);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.reflect.MethodHandles.Lookup#lookupClass", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.reflect.MethodHandles.Lookup#lookupClass", e2.getCause());
                    }
                }
            }

            public static class ForJava8CapableVm extends AbstractBase {
                private final Method revealDirect;

                @Override // net.bytebuddy.utility.JavaConstant.MethodHandle.Dispatcher.Initializable
                public Dispatcher initialize() {
                    return this;
                }

                @Override // net.bytebuddy.utility.JavaConstant.MethodHandle.Dispatcher.AbstractBase
                protected boolean canEqual(Object obj) {
                    return obj instanceof ForJava8CapableVm;
                }

                @Override // net.bytebuddy.utility.JavaConstant.MethodHandle.Dispatcher.AbstractBase
                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForJava8CapableVm)) {
                        return false;
                    }
                    ForJava8CapableVm forJava8CapableVm = (ForJava8CapableVm) obj;
                    if (!forJava8CapableVm.canEqual(this) || !super.equals(obj)) {
                        return false;
                    }
                    Method method = this.revealDirect;
                    Method method2 = forJava8CapableVm.revealDirect;
                    return method != null ? method.equals(method2) : method2 == null;
                }

                @Override // net.bytebuddy.utility.JavaConstant.MethodHandle.Dispatcher.AbstractBase
                public int hashCode() {
                    int iHashCode = super.hashCode() + 59;
                    Method method = this.revealDirect;
                    return (iHashCode * 59) + (method == null ? 43 : method.hashCode());
                }

                protected ForJava8CapableVm(Method method, Method method2, Method method3, Method method4, Method method5, Method method6, Method method7, Method method8, Method method9) {
                    super(method, method2, method3, method4, method5, method6, method7, method8);
                    this.revealDirect = method9;
                }

                @Override // net.bytebuddy.utility.JavaConstant.MethodHandle.Dispatcher
                public Object reveal(Object obj, Object obj2) {
                    try {
                        return this.revealDirect.invoke(obj, obj2);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.invoke.MethodHandles.Lookup#revealDirect", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.invoke.MethodHandles.Lookup#revealDirect", e2.getCause());
                    }
                }
            }

            public static class ForJava7CapableVm extends AbstractBase implements PrivilegedAction<Dispatcher> {
                private final Constructor<?> methodInfo;

                @Override // net.bytebuddy.utility.JavaConstant.MethodHandle.Dispatcher.AbstractBase
                protected boolean canEqual(Object obj) {
                    return obj instanceof ForJava7CapableVm;
                }

                @Override // net.bytebuddy.utility.JavaConstant.MethodHandle.Dispatcher.AbstractBase
                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForJava7CapableVm)) {
                        return false;
                    }
                    ForJava7CapableVm forJava7CapableVm = (ForJava7CapableVm) obj;
                    if (!forJava7CapableVm.canEqual(this) || !super.equals(obj)) {
                        return false;
                    }
                    Constructor<?> constructor = this.methodInfo;
                    Constructor<?> constructor2 = forJava7CapableVm.methodInfo;
                    return constructor != null ? constructor.equals(constructor2) : constructor2 == null;
                }

                @Override // net.bytebuddy.utility.JavaConstant.MethodHandle.Dispatcher.AbstractBase
                public int hashCode() {
                    int iHashCode = super.hashCode() + 59;
                    Constructor<?> constructor = this.methodInfo;
                    return (iHashCode * 59) + (constructor == null ? 43 : constructor.hashCode());
                }

                protected ForJava7CapableVm(Method method, Method method2, Method method3, Method method4, Method method5, Method method6, Method method7, Method method8, Constructor<?> constructor) {
                    super(method, method2, method3, method4, method5, method6, method7, method8);
                    this.methodInfo = constructor;
                }

                @Override // net.bytebuddy.utility.JavaConstant.MethodHandle.Dispatcher.Initializable
                public Dispatcher initialize() {
                    return (Dispatcher) AccessController.doPrivileged(this);
                }

                @Override // java.security.PrivilegedAction
                public Dispatcher run() {
                    this.methodInfo.setAccessible(true);
                    this.getName.setAccessible(true);
                    this.getDeclaringClass.setAccessible(true);
                    this.getReferenceKind.setAccessible(true);
                    this.getMethodType.setAccessible(true);
                    return this;
                }

                @Override // net.bytebuddy.utility.JavaConstant.MethodHandle.Dispatcher
                public Object reveal(Object obj, Object obj2) {
                    try {
                        return this.methodInfo.newInstance(obj2);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.invoke.MethodInfo()", e);
                    } catch (InstantiationException e2) {
                        throw new IllegalStateException("Error constructing java.lang.invoke.MethodInfo", e2);
                    } catch (InvocationTargetException e3) {
                        throw new IllegalStateException("Error invoking java.lang.invoke.MethodInfo()", e3.getCause());
                    }
                }
            }

            public enum ForLegacyVm implements Initializable {
                INSTANCE;

                @Override // net.bytebuddy.utility.JavaConstant.MethodHandle.Dispatcher.Initializable
                public Dispatcher initialize() {
                    throw new IllegalStateException("Unsupported type on current JVM: java.lang.invoke.MethodHandle");
                }

                @Override // net.bytebuddy.utility.JavaConstant.MethodHandle.Dispatcher.Initializable
                public Object publicLookup() {
                    throw new IllegalStateException("Unsupported type on current JVM: java.lang.invoke.MethodHandle");
                }

                @Override // net.bytebuddy.utility.JavaConstant.MethodHandle.Dispatcher.Initializable
                public Class<?> lookupType(Object obj) {
                    throw new IllegalStateException("Unsupported type on current JVM: java.lang.invoke.MethodHandle");
                }
            }
        }

        public enum HandleType {
            INVOKE_VIRTUAL(5),
            INVOKE_STATIC(6),
            INVOKE_SPECIAL(7),
            INVOKE_INTERFACE(9),
            INVOKE_SPECIAL_CONSTRUCTOR(8),
            PUT_FIELD(3),
            GET_FIELD(1),
            PUT_STATIC_FIELD(4),
            GET_STATIC_FIELD(2);

            private final int identifier;

            HandleType(int i) {
                this.identifier = i;
            }

            protected static HandleType of(MethodDescription.InDefinedShape inDefinedShape) {
                if (inDefinedShape.isStatic()) {
                    return INVOKE_STATIC;
                }
                if (inDefinedShape.isPrivate()) {
                    return INVOKE_SPECIAL;
                }
                if (inDefinedShape.isConstructor()) {
                    return INVOKE_SPECIAL_CONSTRUCTOR;
                }
                if (inDefinedShape.getDeclaringType().isInterface()) {
                    return INVOKE_INTERFACE;
                }
                return INVOKE_VIRTUAL;
            }

            protected static HandleType of(int i) {
                for (HandleType handleType : values()) {
                    if (handleType.getIdentifier() == i) {
                        return handleType;
                    }
                }
                throw new IllegalArgumentException("Unknown handle type: " + i);
            }

            protected static HandleType ofSpecial(MethodDescription.InDefinedShape inDefinedShape) {
                if (inDefinedShape.isStatic() || inDefinedShape.isAbstract()) {
                    throw new IllegalArgumentException("Cannot invoke " + inDefinedShape + " via invokespecial");
                }
                return inDefinedShape.isConstructor() ? INVOKE_SPECIAL_CONSTRUCTOR : INVOKE_SPECIAL;
            }

            protected static HandleType ofGetter(FieldDescription.InDefinedShape inDefinedShape) {
                return inDefinedShape.isStatic() ? GET_STATIC_FIELD : GET_FIELD;
            }

            protected static HandleType ofSetter(FieldDescription.InDefinedShape inDefinedShape) {
                return inDefinedShape.isStatic() ? PUT_STATIC_FIELD : PUT_FIELD;
            }

            public int getIdentifier() {
                return this.identifier;
            }
        }
    }
}
