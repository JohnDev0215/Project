package net.bytebuddy.implementation.bytecode.constant;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.auxiliary.TypeProxy;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.collection.ArrayFactory;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.jar.asm.MethodVisitor;

/* JADX INFO: loaded from: classes3.dex */
public abstract class MethodConstant implements StackManipulation {
    private static final String CLASS_TYPE_INTERNAL_NAME = "java/lang/Class";
    protected final MethodDescription.InDefinedShape methodDescription;

    public interface CanCache extends StackManipulation {
        StackManipulation cached();
    }

    protected abstract MethodDescription accessorMethod();

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public boolean isValid() {
        return true;
    }

    protected abstract StackManipulation preparation();

    protected boolean canEqual(Object obj) {
        return obj instanceof MethodConstant;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MethodConstant)) {
            return false;
        }
        MethodConstant methodConstant = (MethodConstant) obj;
        if (!methodConstant.canEqual(this)) {
            return false;
        }
        MethodDescription.InDefinedShape inDefinedShape = this.methodDescription;
        MethodDescription.InDefinedShape inDefinedShape2 = methodConstant.methodDescription;
        return inDefinedShape != null ? inDefinedShape.equals(inDefinedShape2) : inDefinedShape2 == null;
    }

    public int hashCode() {
        MethodDescription.InDefinedShape inDefinedShape = this.methodDescription;
        return 59 + (inDefinedShape == null ? 43 : inDefinedShape.hashCode());
    }

    protected MethodConstant(MethodDescription.InDefinedShape inDefinedShape) {
        this.methodDescription = inDefinedShape;
    }

    public static CanCache forMethod(MethodDescription.InDefinedShape inDefinedShape) {
        if (inDefinedShape.isTypeInitializer()) {
            return CanCacheIllegal.INSTANCE;
        }
        if (inDefinedShape.isConstructor()) {
            return new ForConstructor(inDefinedShape);
        }
        return new ForMethod(inDefinedShape);
    }

    private static List<StackManipulation> typeConstantsFor(List<TypeDescription> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<TypeDescription> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(ClassConstant.of(it.next()));
        }
        return arrayList;
    }

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        return new StackManipulation.Compound(preparation(), ArrayFactory.forType(new TypeDescription.Generic.OfNonGenericType.ForLoadedType(Class.class)).withValues(typeConstantsFor(this.methodDescription.getParameters().asTypeList().asErasures())), MethodInvocation.invoke(accessorMethod())).apply(methodVisitor, context);
    }

    public StackManipulation cached() {
        return this.methodDescription.isConstructor() ? new CachedConstructor(this) : new CachedMethod(this);
    }

    protected enum CanCacheIllegal implements CanCache {
        INSTANCE;

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public boolean isValid() {
            return false;
        }

        @Override // net.bytebuddy.implementation.bytecode.constant.MethodConstant.CanCache
        public StackManipulation cached() {
            return StackManipulation.Illegal.INSTANCE;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            return StackManipulation.Illegal.INSTANCE.apply(methodVisitor, context);
        }
    }

    protected static class ForMethod extends MethodConstant implements CanCache {
        protected ForMethod(MethodDescription.InDefinedShape inDefinedShape) {
            super(inDefinedShape);
        }

        @Override // net.bytebuddy.implementation.bytecode.constant.MethodConstant
        protected StackManipulation preparation() {
            return new StackManipulation.Compound(ClassConstant.of(this.methodDescription.getDeclaringType()), new TextConstant(this.methodDescription.getInternalName()));
        }

        @Override // net.bytebuddy.implementation.bytecode.constant.MethodConstant
        protected MethodDescription accessorMethod() {
            try {
                return new MethodDescription.ForLoadedMethod(Class.class.getMethod("getDeclaredMethod", String.class, Class[].class));
            } catch (NoSuchMethodException e) {
                throw new IllegalStateException("Cannot locate Class::getDeclaredMethod", e);
            }
        }
    }

    protected static class ForConstructor extends MethodConstant implements CanCache {
        protected ForConstructor(MethodDescription.InDefinedShape inDefinedShape) {
            super(inDefinedShape);
        }

        @Override // net.bytebuddy.implementation.bytecode.constant.MethodConstant
        protected StackManipulation preparation() {
            return ClassConstant.of(this.methodDescription.getDeclaringType());
        }

        @Override // net.bytebuddy.implementation.bytecode.constant.MethodConstant
        protected MethodDescription accessorMethod() {
            try {
                return new MethodDescription.ForLoadedMethod(Class.class.getMethod(TypeProxy.SilentConstruction.Appender.GET_DECLARED_CONSTRUCTOR_METHOD_NAME, Class[].class));
            } catch (NoSuchMethodException e) {
                throw new IllegalStateException("Cannot locate Class::getDeclaredConstructor", e);
            }
        }
    }

    protected static class CachedMethod implements StackManipulation {
        private static final TypeDescription METHOD_TYPE = new TypeDescription.ForLoadedType(Method.class);
        private final StackManipulation methodConstant;

        protected boolean canEqual(Object obj) {
            return obj instanceof CachedMethod;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof CachedMethod)) {
                return false;
            }
            CachedMethod cachedMethod = (CachedMethod) obj;
            if (!cachedMethod.canEqual(this)) {
                return false;
            }
            StackManipulation stackManipulation = this.methodConstant;
            StackManipulation stackManipulation2 = cachedMethod.methodConstant;
            return stackManipulation != null ? stackManipulation.equals(stackManipulation2) : stackManipulation2 == null;
        }

        public int hashCode() {
            StackManipulation stackManipulation = this.methodConstant;
            return 59 + (stackManipulation == null ? 43 : stackManipulation.hashCode());
        }

        protected CachedMethod(StackManipulation stackManipulation) {
            this.methodConstant = stackManipulation;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public boolean isValid() {
            return this.methodConstant.isValid();
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            return FieldAccess.forField(context.cache(this.methodConstant, METHOD_TYPE)).read().apply(methodVisitor, context);
        }
    }

    protected static class CachedConstructor implements StackManipulation {
        private static final TypeDescription CONSTRUCTOR_TYPE = new TypeDescription.ForLoadedType(Constructor.class);
        private final StackManipulation constructorConstant;

        protected boolean canEqual(Object obj) {
            return obj instanceof CachedConstructor;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof CachedConstructor)) {
                return false;
            }
            CachedConstructor cachedConstructor = (CachedConstructor) obj;
            if (!cachedConstructor.canEqual(this)) {
                return false;
            }
            StackManipulation stackManipulation = this.constructorConstant;
            StackManipulation stackManipulation2 = cachedConstructor.constructorConstant;
            return stackManipulation != null ? stackManipulation.equals(stackManipulation2) : stackManipulation2 == null;
        }

        public int hashCode() {
            StackManipulation stackManipulation = this.constructorConstant;
            return 59 + (stackManipulation == null ? 43 : stackManipulation.hashCode());
        }

        protected CachedConstructor(StackManipulation stackManipulation) {
            this.constructorConstant = stackManipulation;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public boolean isValid() {
            return this.constructorConstant.isValid();
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            return FieldAccess.forField(context.cache(this.constructorConstant, CONSTRUCTOR_TYPE)).read().apply(methodVisitor, context);
        }
    }
}
