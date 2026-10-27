package org.mockito.internal.creation.bytebuddy;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Modifier;
import java.security.ProtectionDomain;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.scaffold.TypeValidation;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.RandomString;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.creation.bytebuddy.MockMethodAdvice;
import org.mockito.internal.util.StringUtil;
import org.mockito.internal.util.concurrent.WeakConcurrentMap;
import org.mockito.internal.util.concurrent.WeakConcurrentSet;
import org.mockito.mock.SerializableMode;

/* JADX INFO: loaded from: classes4.dex */
public class InlineBytecodeGenerator implements BytecodeGenerator, ClassFileTransformer {
    static final Set<Class<?>> EXCLUDES = new HashSet(Arrays.asList(Class.class, Boolean.class, Byte.class, Short.class, Character.class, Integer.class, Long.class, Float.class, Double.class, String.class));
    private final MockMethodAdvice advice;
    private final String identifier;
    private final Instrumentation instrumentation;
    private volatile Throwable lastException;
    private final BytecodeGenerator subclassEngine;
    private final ByteBuddy byteBuddy = new ByteBuddy().with(TypeValidation.DISABLED).with(Implementation.Context.Disabled.Factory.INSTANCE);
    private final WeakConcurrentSet<Class<?>> mocked = new WeakConcurrentSet<>(WeakConcurrentSet.Cleaner.INLINE);

    public InlineBytecodeGenerator(Instrumentation instrumentation, WeakConcurrentMap<Object, MockMethodInterceptor> weakConcurrentMap) {
        this.instrumentation = instrumentation;
        String strMake = RandomString.make();
        this.identifier = strMake;
        MockMethodAdvice mockMethodAdvice = new MockMethodAdvice(weakConcurrentMap, strMake);
        this.advice = mockMethodAdvice;
        this.subclassEngine = new TypeCachingBytecodeGenerator(new SubclassBytecodeGenerator(MethodDelegation.withDefaultConfiguration().withBinders(TargetMethodAnnotationDrivenBinder.ParameterBinder.ForFixedValue.OfConstant.of(MockMethodAdvice.Identifier.class, strMake)).to(MockMethodAdvice.ForReadObject.class), ElementMatchers.isAbstract().or(ElementMatchers.isNative()).or(ElementMatchers.isToString())), false);
        MockMethodDispatcher.set(strMake, mockMethodAdvice);
        instrumentation.addTransformer(this, true);
    }

    @Override // org.mockito.internal.creation.bytebuddy.BytecodeGenerator
    public <T> Class<? extends T> mockClass(MockFeatures<T> mockFeatures) {
        boolean z = (mockFeatures.interfaces.isEmpty() && mockFeatures.serializableMode == SerializableMode.NONE && !Modifier.isAbstract(mockFeatures.mockedType.getModifiers())) ? false : true;
        checkSupportedCombination(z, mockFeatures);
        synchronized (this) {
            triggerRetransformation(mockFeatures);
        }
        return z ? this.subclassEngine.mockClass(mockFeatures) : mockFeatures.mockedType;
    }

    private <T> void triggerRetransformation(MockFeatures<T> mockFeatures) {
        Set<Class<?>> hashSet = new HashSet<>();
        Class<T> superclass = mockFeatures.mockedType;
        do {
            if (this.mocked.add(superclass)) {
                hashSet.add(superclass);
                addInterfaces(hashSet, superclass.getInterfaces());
            }
            superclass = superclass.getSuperclass();
        } while (superclass != null);
        if (hashSet.isEmpty()) {
            return;
        }
        try {
            try {
                this.instrumentation.retransformClasses((Class[]) hashSet.toArray(new Class[hashSet.size()]));
                Throwable th = this.lastException;
                if (th == null) {
                } else {
                    throw new IllegalStateException(StringUtil.join("Byte Buddy could not instrument all classes within the mock's type hierarchy", "", "This problem should never occur for javac-compiled classes. This problem has been observed for classes that are:", " - Compiled by older versions of scalac", " - Classes that are part of the Android distribution"), th);
                }
            } catch (Exception e) {
                Iterator<Class<?>> it = hashSet.iterator();
                while (it.hasNext()) {
                    this.mocked.remove(it.next());
                }
                throw new MockitoException("Could not modify all classes " + hashSet, e);
            }
        } finally {
            this.lastException = null;
        }
    }

    private <T> void checkSupportedCombination(boolean z, MockFeatures<T> mockFeatures) {
        if (z && !mockFeatures.mockedType.isArray() && !mockFeatures.mockedType.isPrimitive() && Modifier.isFinal(mockFeatures.mockedType.getModifiers())) {
            throw new MockitoException("Unsupported settings with this type '" + mockFeatures.mockedType.getName() + "'");
        }
    }

    private void addInterfaces(Set<Class<?>> set, Class<?>[] clsArr) {
        for (Class<?> cls : clsArr) {
            if (this.mocked.add(cls)) {
                set.add(cls);
                addInterfaces(set, cls.getInterfaces());
            }
        }
    }

    public byte[] transform(ClassLoader classLoader, String str, Class<?> cls, ProtectionDomain protectionDomain, byte[] bArr) throws IllegalClassFormatException {
        if (cls != null && this.mocked.contains(cls) && !EXCLUDES.contains(cls)) {
            try {
                return this.byteBuddy.redefine(cls, ClassFileLocator.Simple.of(cls.getName(), bArr)).visit(new ParameterWritingVisitorWrapper(cls)).visit(Advice.withCustomMapping().bind(MockMethodAdvice.Identifier.class, this.identifier).to(MockMethodAdvice.class).on(ElementMatchers.isVirtual().and(ElementMatchers.not(ElementMatchers.isBridge().or(ElementMatchers.isHashCode()).or(ElementMatchers.isEquals()).or(ElementMatchers.isDefaultFinalizer()))).and(ElementMatchers.not(ElementMatchers.isDeclaredBy(ElementMatchers.nameStartsWith("java.")).and(ElementMatchers.isPackagePrivate()))))).visit(Advice.withCustomMapping().bind(MockMethodAdvice.Identifier.class, this.identifier).to(MockMethodAdvice.ForHashCode.class).on(ElementMatchers.isHashCode())).visit(Advice.withCustomMapping().bind(MockMethodAdvice.Identifier.class, this.identifier).to(MockMethodAdvice.ForEquals.class).on(ElementMatchers.isEquals())).make().getBytes();
            } catch (Throwable th) {
                this.lastException = th;
            }
        }
        return null;
    }

    private static class ParameterWritingVisitorWrapper extends AsmVisitorWrapper.AbstractBase {
        private final Class<?> type;

        private ParameterWritingVisitorWrapper(Class<?> cls) {
            this.type = cls;
        }

        @Override // net.bytebuddy.asm.AsmVisitorWrapper
        public ClassVisitor wrap(TypeDescription typeDescription, ClassVisitor classVisitor, Implementation.Context context, TypePool typePool, FieldList<FieldDescription.InDefinedShape> fieldList, MethodList<?> methodList, int i, int i2) {
            return context.getClassFileVersion().isAtLeast(ClassFileVersion.JAVA_V8) ? new ParameterAddingClassVisitor(classVisitor, new TypeDescription.ForLoadedType(this.type)) : classVisitor;
        }

        private static class ParameterAddingClassVisitor extends ClassVisitor {
            private final TypeDescription typeDescription;

            private ParameterAddingClassVisitor(ClassVisitor classVisitor, TypeDescription typeDescription) {
                super(Opcodes.ASM5, classVisitor);
                this.typeDescription = typeDescription;
            }

            @Override // net.bytebuddy.jar.asm.ClassVisitor
            public MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
                ElementMatcher.Junction junctionNamed;
                MethodVisitor methodVisitorVisitMethod = super.visitMethod(i, str, str2, str3, strArr);
                MethodList<MethodDescription.InDefinedShape> declaredMethods = this.typeDescription.getDeclaredMethods();
                if (str.equals(MethodDescription.CONSTRUCTOR_INTERNAL_NAME)) {
                    junctionNamed = ElementMatchers.isConstructor();
                } else {
                    junctionNamed = ElementMatchers.named(str);
                }
                MethodList methodListFilter = declaredMethods.filter(junctionNamed.and(ElementMatchers.hasDescriptor(str2)));
                if (methodListFilter.size() != 1 || !((MethodDescription) methodListFilter.getOnly()).getParameters().hasExplicitMetaData()) {
                    return methodVisitorVisitMethod;
                }
                Iterator it = ((MethodDescription) methodListFilter.getOnly()).getParameters().iterator();
                while (it.hasNext()) {
                    ParameterDescription parameterDescription = (ParameterDescription) it.next();
                    methodVisitorVisitMethod.visitParameter(parameterDescription.getName(), parameterDescription.getModifiers());
                }
                return new MethodParameterStrippingMethodVisitor(methodVisitorVisitMethod);
            }
        }

        private static class MethodParameterStrippingMethodVisitor extends MethodVisitor {
            @Override // net.bytebuddy.jar.asm.MethodVisitor
            public void visitParameter(String str, int i) {
            }

            public MethodParameterStrippingMethodVisitor(MethodVisitor methodVisitor) {
                super(Opcodes.ASM5, methodVisitor);
            }
        }
    }
}
