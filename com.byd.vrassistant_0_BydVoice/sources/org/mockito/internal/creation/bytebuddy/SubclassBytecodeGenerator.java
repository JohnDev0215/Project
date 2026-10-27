package org.mockito.internal.creation.bytebuddy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.modifier.SynchronizationState;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.Transformer;
import net.bytebuddy.dynamic.loading.MultipleParentClassLoader;
import net.bytebuddy.dynamic.scaffold.TypeValidation;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.attribute.MethodAttributeAppender;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.creation.bytebuddy.ByteBuddyCrossClassLoaderSerializationSupport;
import org.mockito.internal.creation.bytebuddy.MockMethodInterceptor;
import org.mockito.internal.util.StringUtil;
import org.mockito.mock.SerializableMode;

/* JADX INFO: loaded from: classes4.dex */
class SubclassBytecodeGenerator implements BytecodeGenerator {
    private final ByteBuddy byteBuddy;
    private final SubclassLoader loader;
    private final ElementMatcher<? super MethodDescription> matcher;
    private final Random random;
    private final Implementation readReplace;

    public SubclassBytecodeGenerator() {
        this(new SubclassInjectionLoader());
    }

    public SubclassBytecodeGenerator(SubclassLoader subclassLoader) {
        this(subclassLoader, null, ElementMatchers.any());
    }

    public SubclassBytecodeGenerator(Implementation implementation, ElementMatcher<? super MethodDescription> elementMatcher) {
        this(new SubclassInjectionLoader(), implementation, elementMatcher);
    }

    protected SubclassBytecodeGenerator(SubclassLoader subclassLoader, Implementation implementation, ElementMatcher<? super MethodDescription> elementMatcher) {
        this.loader = subclassLoader;
        this.readReplace = implementation;
        this.matcher = elementMatcher;
        this.byteBuddy = new ByteBuddy().with(TypeValidation.DISABLED);
        this.random = new Random();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.mockito.internal.creation.bytebuddy.BytecodeGenerator
    public <T> Class<? extends T> mockClass(MockFeatures<T> mockFeatures) {
        DynamicType.Builder builderIntercept = this.byteBuddy.subclass((Class) mockFeatures.mockedType).name(nameFor(mockFeatures.mockedType)).ignoreAlso(isGroovyMethod()).annotateType(mockFeatures.stripAnnotations ? new Annotation[0] : mockFeatures.mockedType.getAnnotations()).implement((List<? extends Type>) new ArrayList(mockFeatures.interfaces)).method(this.matcher).intercept(MethodDelegation.to((Class<?>) MockMethodInterceptor.DispatcherDefaultingToRealMethod.class)).transform(Transformer.ForMethod.withModifiers(SynchronizationState.PLAIN)).attribute(mockFeatures.stripAnnotations ? MethodAttributeAppender.NoOp.INSTANCE : MethodAttributeAppender.ForInstrumentedMethod.INCLUDING_RECEIVER).method(ElementMatchers.isHashCode()).intercept(MethodDelegation.to((Class<?>) MockMethodInterceptor.ForHashCode.class)).method(ElementMatchers.isEquals()).intercept(MethodDelegation.to((Class<?>) MockMethodInterceptor.ForEquals.class)).serialVersionUid(42L).defineField("mockitoInterceptor", MockMethodInterceptor.class, Visibility.PRIVATE).implement(MockAccess.class).intercept(FieldAccessor.ofBeanProperty());
        if (mockFeatures.serializableMode == SerializableMode.ACROSS_CLASSLOADERS) {
            builderIntercept = builderIntercept.implement(ByteBuddyCrossClassLoaderSerializationSupport.CrossClassLoaderSerializableMock.class).intercept(MethodDelegation.to((Class<?>) MockMethodInterceptor.ForWriteReplace.class));
        }
        if (this.readReplace != null) {
            builderIntercept = builderIntercept.defineMethod("readObject", Void.TYPE, Visibility.PRIVATE).withParameters(ObjectInputStream.class).throwing(ClassNotFoundException.class, IOException.class).intercept(this.readReplace);
        }
        ClassLoader classLoaderBuild = new MultipleParentClassLoader.Builder().append((Class<?>[]) new Class[]{mockFeatures.mockedType}).append(mockFeatures.interfaces).append(Thread.currentThread().getContextClassLoader()).append(MockAccess.class, MockMethodInterceptor.DispatcherDefaultingToRealMethod.class).append(MockMethodInterceptor.class, MockMethodInterceptor.ForHashCode.class, MockMethodInterceptor.ForEquals.class).build(MockMethodInterceptor.class.getClassLoader());
        if (classLoaderBuild != mockFeatures.mockedType.getClassLoader()) {
            assertVisibility(mockFeatures.mockedType);
            Iterator<Class<?>> it = mockFeatures.interfaces.iterator();
            while (it.hasNext()) {
                assertVisibility(it.next());
            }
            builderIntercept = builderIntercept.ignoreAlso(ElementMatchers.isPackagePrivate().or(ElementMatchers.returns(ElementMatchers.isPackagePrivate())).or(ElementMatchers.hasParameters(ElementMatchers.whereAny(ElementMatchers.hasType(ElementMatchers.isPackagePrivate())))));
        }
        return builderIntercept.make().load(classLoaderBuild, this.loader.getStrategy(mockFeatures.mockedType)).getLoaded();
    }

    private static ElementMatcher<MethodDescription> isGroovyMethod() {
        return ElementMatchers.isDeclaredBy(ElementMatchers.named("groovy.lang.GroovyObjectSupport"));
    }

    private String nameFor(Class<?> cls) {
        String name = cls.getName();
        if (isComingFromJDK(cls) || isComingFromSignedJar(cls) || isComingFromSealedPackage(cls)) {
            name = "codegen." + name;
        }
        return String.format("%s$%s$%d", name, "MockitoMock", Integer.valueOf(Math.abs(this.random.nextInt())));
    }

    private boolean isComingFromJDK(Class<?> cls) {
        return (cls.getPackage() != null && "Java Runtime Environment".equalsIgnoreCase(cls.getPackage().getImplementationTitle())) || cls.getName().startsWith("java.") || cls.getName().startsWith("javax.");
    }

    private boolean isComingFromSealedPackage(Class<?> cls) {
        return cls.getPackage() != null && cls.getPackage().isSealed();
    }

    private boolean isComingFromSignedJar(Class<?> cls) {
        return cls.getSigners() != null;
    }

    private static void assertVisibility(Class<?> cls) {
        if (!Modifier.isPublic(cls.getModifiers())) {
            throw new MockitoException(StringUtil.join("Cannot create mock for " + cls, "", "The type is not public and its mock class is loaded by a different class loader.", "This can have multiple reasons:", " - You are mocking a class with additional interfaces of another class loader", " - Mockito is loaded by a different class loader than the mocked type (e.g. with OSGi)", " - The thread's context class loader is different than the mock's class loader"));
        }
    }
}
