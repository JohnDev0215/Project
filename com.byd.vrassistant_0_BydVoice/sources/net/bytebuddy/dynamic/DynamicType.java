package net.bytebuddy.dynamic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.annotation.AnnotationValue;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.modifier.FieldManifestation;
import net.bytebuddy.description.modifier.MethodManifestation;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.description.modifier.Ownership;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.description.type.TypeVariableToken;
import net.bytebuddy.dynamic.Transformer;
import net.bytebuddy.dynamic.TypeResolutionStrategy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import net.bytebuddy.dynamic.scaffold.FieldRegistry;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.dynamic.scaffold.MethodRegistry;
import net.bytebuddy.dynamic.scaffold.TypeValidation;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.LoadedTypeInitializer;
import net.bytebuddy.implementation.attribute.AnnotationRetention;
import net.bytebuddy.implementation.attribute.AnnotationValueFilter;
import net.bytebuddy.implementation.attribute.FieldAttributeAppender;
import net.bytebuddy.implementation.attribute.MethodAttributeAppender;
import net.bytebuddy.implementation.attribute.TypeAttributeAppender;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.matcher.LatentMatcher;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.CompoundList;

/* JADX INFO: loaded from: classes3.dex */
public interface DynamicType {

    public interface Loaded<T> extends DynamicType {
        Class<? extends T> getLoaded();

        Map<TypeDescription, Class<?>> getLoadedAuxiliaryTypes();
    }

    public interface Unloaded<T> extends DynamicType {
        Unloaded<T> include(List<? extends DynamicType> list);

        Unloaded<T> include(DynamicType... dynamicTypeArr);

        Loaded<T> load(ClassLoader classLoader);

        <S extends ClassLoader> Loaded<T> load(S s, ClassLoadingStrategy<? super S> classLoadingStrategy);
    }

    Map<TypeDescription, byte[]> getAllTypes();

    Map<TypeDescription, byte[]> getAuxiliaryTypes();

    byte[] getBytes();

    Map<TypeDescription, LoadedTypeInitializer> getLoadedTypeInitializers();

    TypeDescription getTypeDescription();

    boolean hasAliveLoadedTypeInitializers();

    File inject(File file) throws IOException;

    File inject(File file, File file2) throws IOException;

    Map<TypeDescription, File> saveIn(File file) throws IOException;

    File toJar(File file) throws IOException;

    File toJar(File file, Manifest manifest) throws IOException;

    public interface Builder<T> {
        Builder<T> annotateType(Collection<? extends AnnotationDescription> collection);

        Builder<T> annotateType(List<? extends Annotation> list);

        Builder<T> annotateType(Annotation... annotationArr);

        Builder<T> annotateType(AnnotationDescription... annotationDescriptionArr);

        Builder<T> attribute(TypeAttributeAppender typeAttributeAppender);

        MethodDefinition.ImplementationDefinition<T> constructor(ElementMatcher<? super MethodDescription> elementMatcher);

        FieldDefinition.Optional.Valuable<T> define(Field field);

        FieldDefinition.Optional.Valuable<T> define(FieldDescription fieldDescription);

        MethodDefinition.ImplementationDefinition<T> define(Constructor<?> constructor);

        MethodDefinition.ImplementationDefinition<T> define(Method method);

        MethodDefinition.ImplementationDefinition<T> define(MethodDescription methodDescription);

        MethodDefinition.ParameterDefinition.Initial<T> defineConstructor(int i);

        MethodDefinition.ParameterDefinition.Initial<T> defineConstructor(Collection<? extends ModifierContributor.ForMethod> collection);

        MethodDefinition.ParameterDefinition.Initial<T> defineConstructor(ModifierContributor.ForMethod... forMethodArr);

        FieldDefinition.Optional.Valuable<T> defineField(String str, Type type, int i);

        FieldDefinition.Optional.Valuable<T> defineField(String str, Type type, Collection<? extends ModifierContributor.ForField> collection);

        FieldDefinition.Optional.Valuable<T> defineField(String str, Type type, ModifierContributor.ForField... forFieldArr);

        FieldDefinition.Optional.Valuable<T> defineField(String str, TypeDefinition typeDefinition, int i);

        FieldDefinition.Optional.Valuable<T> defineField(String str, TypeDefinition typeDefinition, Collection<? extends ModifierContributor.ForField> collection);

        FieldDefinition.Optional.Valuable<T> defineField(String str, TypeDefinition typeDefinition, ModifierContributor.ForField... forFieldArr);

        MethodDefinition.ParameterDefinition.Initial<T> defineMethod(String str, Type type, int i);

        MethodDefinition.ParameterDefinition.Initial<T> defineMethod(String str, Type type, Collection<? extends ModifierContributor.ForMethod> collection);

        MethodDefinition.ParameterDefinition.Initial<T> defineMethod(String str, Type type, ModifierContributor.ForMethod... forMethodArr);

        MethodDefinition.ParameterDefinition.Initial<T> defineMethod(String str, TypeDefinition typeDefinition, int i);

        MethodDefinition.ParameterDefinition.Initial<T> defineMethod(String str, TypeDefinition typeDefinition, Collection<? extends ModifierContributor.ForMethod> collection);

        MethodDefinition.ParameterDefinition.Initial<T> defineMethod(String str, TypeDefinition typeDefinition, ModifierContributor.ForMethod... forMethodArr);

        FieldDefinition.Optional<T> defineProperty(String str, Type type);

        FieldDefinition.Optional<T> defineProperty(String str, Type type, boolean z);

        FieldDefinition.Optional<T> defineProperty(String str, TypeDefinition typeDefinition);

        FieldDefinition.Optional<T> defineProperty(String str, TypeDefinition typeDefinition, boolean z);

        FieldDefinition.Valuable<T> field(ElementMatcher<? super FieldDescription> elementMatcher);

        FieldDefinition.Valuable<T> field(LatentMatcher<? super FieldDescription> latentMatcher);

        Builder<T> ignoreAlso(ElementMatcher<? super MethodDescription> elementMatcher);

        Builder<T> ignoreAlso(LatentMatcher<? super MethodDescription> latentMatcher);

        MethodDefinition.ImplementationDefinition.Optional<T> implement(Collection<? extends TypeDefinition> collection);

        MethodDefinition.ImplementationDefinition.Optional<T> implement(List<? extends Type> list);

        MethodDefinition.ImplementationDefinition.Optional<T> implement(Type... typeArr);

        MethodDefinition.ImplementationDefinition.Optional<T> implement(TypeDefinition... typeDefinitionArr);

        Builder<T> initializer(LoadedTypeInitializer loadedTypeInitializer);

        Builder<T> initializer(ByteCodeAppender byteCodeAppender);

        MethodDefinition.ImplementationDefinition<T> invokable(ElementMatcher<? super MethodDescription> elementMatcher);

        MethodDefinition.ImplementationDefinition<T> invokable(LatentMatcher<? super MethodDescription> latentMatcher);

        Unloaded<T> make();

        Unloaded<T> make(TypeResolutionStrategy typeResolutionStrategy);

        Unloaded<T> make(TypeResolutionStrategy typeResolutionStrategy, TypePool typePool);

        Unloaded<T> make(TypePool typePool);

        Builder<T> merge(Collection<? extends ModifierContributor.ForType> collection);

        Builder<T> merge(ModifierContributor.ForType... forTypeArr);

        MethodDefinition.ImplementationDefinition<T> method(ElementMatcher<? super MethodDescription> elementMatcher);

        Builder<T> modifiers(int i);

        Builder<T> modifiers(Collection<? extends ModifierContributor.ForType> collection);

        Builder<T> modifiers(ModifierContributor.ForType... forTypeArr);

        Builder<T> name(String str);

        FieldDefinition.Optional<T> serialVersionUid(long j);

        Builder<T> transform(ElementMatcher<? super TypeDescription.Generic> elementMatcher, Transformer<TypeVariableToken> transformer);

        TypeVariableDefinition<T> typeVariable(String str);

        TypeVariableDefinition<T> typeVariable(String str, Collection<? extends TypeDefinition> collection);

        TypeVariableDefinition<T> typeVariable(String str, List<? extends Type> list);

        TypeVariableDefinition<T> typeVariable(String str, Type... typeArr);

        TypeVariableDefinition<T> typeVariable(String str, TypeDefinition... typeDefinitionArr);

        Builder<T> visit(AsmVisitorWrapper asmVisitorWrapper);

        public interface TypeVariableDefinition<S> extends Builder<S> {
            TypeVariableDefinition<S> annotateTypeVariable(Collection<? extends AnnotationDescription> collection);

            TypeVariableDefinition<S> annotateTypeVariable(List<? extends Annotation> list);

            TypeVariableDefinition<S> annotateTypeVariable(Annotation... annotationArr);

            TypeVariableDefinition<S> annotateTypeVariable(AnnotationDescription... annotationDescriptionArr);

            public static abstract class AbstractBase<U> extends AbstractBase.Delegator<U> implements TypeVariableDefinition<U> {
                @Override // net.bytebuddy.dynamic.DynamicType.Builder.TypeVariableDefinition
                public TypeVariableDefinition<U> annotateTypeVariable(Annotation... annotationArr) {
                    return annotateTypeVariable(Arrays.asList(annotationArr));
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder.TypeVariableDefinition
                public TypeVariableDefinition<U> annotateTypeVariable(List<? extends Annotation> list) {
                    return annotateTypeVariable((Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder.TypeVariableDefinition
                public TypeVariableDefinition<U> annotateTypeVariable(AnnotationDescription... annotationDescriptionArr) {
                    return annotateTypeVariable((Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
                }
            }
        }

        public interface FieldDefinition<S> {

            public interface Valuable<U> extends FieldDefinition<U> {
                Optional<U> value(double d);

                Optional<U> value(float f);

                Optional<U> value(int i);

                Optional<U> value(long j);

                Optional<U> value(String str);

                Optional<U> value(boolean z);
            }

            Optional<S> annotateField(Collection<? extends AnnotationDescription> collection);

            Optional<S> annotateField(List<? extends Annotation> list);

            Optional<S> annotateField(Annotation... annotationArr);

            Optional<S> annotateField(AnnotationDescription... annotationDescriptionArr);

            Optional<S> attribute(FieldAttributeAppender.Factory factory);

            Optional<S> transform(Transformer<FieldDescription> transformer);

            public interface Optional<U> extends FieldDefinition<U>, Builder<U> {

                public interface Valuable<V> extends Valuable<V>, Optional<V> {

                    public static abstract class AbstractBase<U> extends AbstractBase<U> implements Valuable<U> {
                        protected abstract Optional<U> defaultValue(Object obj);

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Valuable
                        public Optional<U> value(boolean z) {
                            return defaultValue(Integer.valueOf(z ? 1 : 0));
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Valuable
                        public Optional<U> value(int i) {
                            return defaultValue(Integer.valueOf(i));
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Valuable
                        public Optional<U> value(long j) {
                            return defaultValue(Long.valueOf(j));
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Valuable
                        public Optional<U> value(float f) {
                            return defaultValue(Float.valueOf(f));
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Valuable
                        public Optional<U> value(double d) {
                            return defaultValue(Double.valueOf(d));
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Valuable
                        public Optional<U> value(String str) {
                            if (str == null) {
                                throw new IllegalArgumentException("Cannot set null as a default value");
                            }
                            return defaultValue(str);
                        }

                        protected static abstract class Adapter<V> extends AbstractBase<V> {
                            protected final Object defaultValue;
                            protected final FieldAttributeAppender.Factory fieldAttributeAppenderFactory;
                            protected final Transformer<FieldDescription> transformer;

                            protected abstract Optional<V> materialize(FieldAttributeAppender.Factory factory, Transformer<FieldDescription> transformer, Object obj);

                            protected boolean canEqual(Object obj) {
                                return obj instanceof Adapter;
                            }

                            public boolean equals(Object obj) {
                                if (obj == this) {
                                    return true;
                                }
                                if (!(obj instanceof Adapter)) {
                                    return false;
                                }
                                Adapter adapter = (Adapter) obj;
                                if (!adapter.canEqual(this)) {
                                    return false;
                                }
                                FieldAttributeAppender.Factory factory = this.fieldAttributeAppenderFactory;
                                FieldAttributeAppender.Factory factory2 = adapter.fieldAttributeAppenderFactory;
                                if (factory != null ? !factory.equals(factory2) : factory2 != null) {
                                    return false;
                                }
                                Transformer<FieldDescription> transformer = this.transformer;
                                Transformer<FieldDescription> transformer2 = adapter.transformer;
                                if (transformer != null ? !transformer.equals(transformer2) : transformer2 != null) {
                                    return false;
                                }
                                Object obj2 = this.defaultValue;
                                Object obj3 = adapter.defaultValue;
                                return obj2 != null ? obj2.equals(obj3) : obj3 == null;
                            }

                            public int hashCode() {
                                FieldAttributeAppender.Factory factory = this.fieldAttributeAppenderFactory;
                                int iHashCode = factory == null ? 43 : factory.hashCode();
                                Transformer<FieldDescription> transformer = this.transformer;
                                int iHashCode2 = ((iHashCode + 59) * 59) + (transformer == null ? 43 : transformer.hashCode());
                                Object obj = this.defaultValue;
                                return (iHashCode2 * 59) + (obj != null ? obj.hashCode() : 43);
                            }

                            protected Adapter(FieldAttributeAppender.Factory factory, Transformer<FieldDescription> transformer, Object obj) {
                                this.fieldAttributeAppenderFactory = factory;
                                this.transformer = transformer;
                                this.defaultValue = obj;
                            }

                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition
                            public Optional<V> attribute(FieldAttributeAppender.Factory factory) {
                                return materialize(new FieldAttributeAppender.Factory.Compound(this.fieldAttributeAppenderFactory, factory), this.transformer, this.defaultValue);
                            }

                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition
                            public Optional<V> transform(Transformer<FieldDescription> transformer) {
                                return materialize(this.fieldAttributeAppenderFactory, new Transformer.Compound(this.transformer, transformer), this.defaultValue);
                            }

                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional.Valuable.AbstractBase
                            protected Optional<V> defaultValue(Object obj) {
                                return materialize(this.fieldAttributeAppenderFactory, this.transformer, obj);
                            }
                        }
                    }
                }

                public static abstract class AbstractBase<U> extends AbstractBase.Delegator<U> implements Optional<U> {
                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition
                    public Optional<U> annotateField(Annotation... annotationArr) {
                        return annotateField(Arrays.asList(annotationArr));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition
                    public Optional<U> annotateField(List<? extends Annotation> list) {
                        return annotateField((Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition
                    public Optional<U> annotateField(AnnotationDescription... annotationDescriptionArr) {
                        return annotateField((Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
                    }
                }
            }
        }

        public interface MethodDefinition<S> extends Builder<S> {
            MethodDefinition<S> annotateMethod(Collection<? extends AnnotationDescription> collection);

            MethodDefinition<S> annotateMethod(List<? extends Annotation> list);

            MethodDefinition<S> annotateMethod(Annotation... annotationArr);

            MethodDefinition<S> annotateMethod(AnnotationDescription... annotationDescriptionArr);

            MethodDefinition<S> annotateParameter(int i, Collection<? extends AnnotationDescription> collection);

            MethodDefinition<S> annotateParameter(int i, List<? extends Annotation> list);

            MethodDefinition<S> annotateParameter(int i, Annotation... annotationArr);

            MethodDefinition<S> annotateParameter(int i, AnnotationDescription... annotationDescriptionArr);

            MethodDefinition<S> attribute(MethodAttributeAppender.Factory factory);

            MethodDefinition<S> transform(Transformer<MethodDescription> transformer);

            public interface ReceiverTypeDefinition<U> extends MethodDefinition<U> {
                MethodDefinition<U> receiverType(AnnotatedElement annotatedElement);

                MethodDefinition<U> receiverType(TypeDescription.Generic generic);

                public static abstract class AbstractBase<V> extends AbstractBase<V> implements ReceiverTypeDefinition<V> {
                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ReceiverTypeDefinition
                    public MethodDefinition<V> receiverType(AnnotatedElement annotatedElement) {
                        return receiverType(TypeDescription.Generic.AnnotationReader.DISPATCHER.resolve(annotatedElement));
                    }
                }
            }

            public interface ImplementationDefinition<U> {

                public interface Optional<V> extends ImplementationDefinition<V>, Builder<V> {
                }

                <W> ReceiverTypeDefinition<U> defaultValue(W w, Class<? extends W> cls);

                ReceiverTypeDefinition<U> defaultValue(AnnotationValue<?, ?> annotationValue);

                ReceiverTypeDefinition<U> intercept(Implementation implementation);

                ReceiverTypeDefinition<U> withoutCode();

                public static abstract class AbstractBase<V> implements ImplementationDefinition<V> {
                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                    public <W> ReceiverTypeDefinition<V> defaultValue(W w, Class<? extends W> cls) {
                        return defaultValue(AnnotationDescription.ForLoadedAnnotation.asValue(w, cls));
                    }
                }
            }

            public interface TypeVariableDefinition<U> extends ImplementationDefinition<U> {
                Annotatable<U> typeVariable(String str);

                Annotatable<U> typeVariable(String str, Collection<? extends TypeDefinition> collection);

                Annotatable<U> typeVariable(String str, List<? extends Type> list);

                Annotatable<U> typeVariable(String str, Type... typeArr);

                Annotatable<U> typeVariable(String str, TypeDefinition... typeDefinitionArr);

                public interface Annotatable<V> extends TypeVariableDefinition<V> {
                    Annotatable<V> annotateTypeVariable(Collection<? extends AnnotationDescription> collection);

                    Annotatable<V> annotateTypeVariable(List<? extends Annotation> list);

                    Annotatable<V> annotateTypeVariable(Annotation... annotationArr);

                    Annotatable<V> annotateTypeVariable(AnnotationDescription... annotationDescriptionArr);

                    public static abstract class AbstractBase<W> extends AbstractBase<W> implements Annotatable<W> {
                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.TypeVariableDefinition.Annotatable
                        public Annotatable<W> annotateTypeVariable(Annotation... annotationArr) {
                            return annotateTypeVariable(Arrays.asList(annotationArr));
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.TypeVariableDefinition.Annotatable
                        public Annotatable<W> annotateTypeVariable(List<? extends Annotation> list) {
                            return annotateTypeVariable((Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.TypeVariableDefinition.Annotatable
                        public Annotatable<W> annotateTypeVariable(AnnotationDescription... annotationDescriptionArr) {
                            return annotateTypeVariable((Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
                        }

                        protected static abstract class Adapter<X> extends AbstractBase<X> {
                            protected abstract ParameterDefinition<X> materialize();

                            protected Adapter() {
                            }

                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.TypeVariableDefinition
                            public Annotatable<X> typeVariable(String str, Collection<? extends TypeDefinition> collection) {
                                return materialize().typeVariable(str, collection);
                            }

                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                            public ReceiverTypeDefinition<X> intercept(Implementation implementation) {
                                return materialize().intercept(implementation);
                            }

                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                            public ReceiverTypeDefinition<X> withoutCode() {
                                return materialize().withoutCode();
                            }

                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                            public ReceiverTypeDefinition<X> defaultValue(AnnotationValue<?, ?> annotationValue) {
                                return materialize().defaultValue(annotationValue);
                            }

                            /* JADX WARN: Multi-variable type inference failed */
                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition.AbstractBase, net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                            public <V> ReceiverTypeDefinition<X> defaultValue(V v, Class<? extends V> cls) {
                                return materialize().defaultValue(v, cls);
                            }
                        }
                    }
                }

                public static abstract class AbstractBase<V> extends ImplementationDefinition.AbstractBase<V> implements TypeVariableDefinition<V> {
                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.TypeVariableDefinition
                    public Annotatable<V> typeVariable(String str) {
                        return typeVariable(str, Collections.singletonList(Object.class));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.TypeVariableDefinition
                    public Annotatable<V> typeVariable(String str, Type... typeArr) {
                        return typeVariable(str, Arrays.asList(typeArr));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.TypeVariableDefinition
                    public Annotatable<V> typeVariable(String str, List<? extends Type> list) {
                        return typeVariable(str, (Collection<? extends TypeDefinition>) new TypeList.Generic.ForLoadedTypes(list));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.TypeVariableDefinition
                    public Annotatable<V> typeVariable(String str, TypeDefinition... typeDefinitionArr) {
                        return typeVariable(str, (Collection<? extends TypeDefinition>) Arrays.asList(typeDefinitionArr));
                    }
                }
            }

            public interface ExceptionDefinition<U> extends TypeVariableDefinition<U> {
                ExceptionDefinition<U> throwing(Collection<? extends TypeDefinition> collection);

                ExceptionDefinition<U> throwing(List<? extends Type> list);

                ExceptionDefinition<U> throwing(Type... typeArr);

                ExceptionDefinition<U> throwing(TypeDefinition... typeDefinitionArr);

                public static abstract class AbstractBase<V> extends TypeVariableDefinition.AbstractBase<V> implements ExceptionDefinition<V> {
                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ExceptionDefinition
                    public ExceptionDefinition<V> throwing(Type... typeArr) {
                        return throwing(Arrays.asList(typeArr));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ExceptionDefinition
                    public ExceptionDefinition<V> throwing(List<? extends Type> list) {
                        return throwing((Collection<? extends TypeDefinition>) new TypeList.Generic.ForLoadedTypes(list));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ExceptionDefinition
                    public ExceptionDefinition<V> throwing(TypeDefinition... typeDefinitionArr) {
                        return throwing((Collection<? extends TypeDefinition>) Arrays.asList(typeDefinitionArr));
                    }
                }
            }

            public interface ParameterDefinition<U> extends ExceptionDefinition<U> {
                Annotatable<U> withParameter(Type type, String str, int i);

                Annotatable<U> withParameter(Type type, String str, Collection<? extends ModifierContributor.ForParameter> collection);

                Annotatable<U> withParameter(Type type, String str, ModifierContributor.ForParameter... forParameterArr);

                Annotatable<U> withParameter(TypeDefinition typeDefinition, String str, int i);

                Annotatable<U> withParameter(TypeDefinition typeDefinition, String str, Collection<? extends ModifierContributor.ForParameter> collection);

                Annotatable<U> withParameter(TypeDefinition typeDefinition, String str, ModifierContributor.ForParameter... forParameterArr);

                public interface Annotatable<V> extends ParameterDefinition<V> {
                    Annotatable<V> annotateParameter(Collection<? extends AnnotationDescription> collection);

                    Annotatable<V> annotateParameter(List<? extends Annotation> list);

                    Annotatable<V> annotateParameter(Annotation... annotationArr);

                    Annotatable<V> annotateParameter(AnnotationDescription... annotationDescriptionArr);

                    public static abstract class AbstractBase<W> extends AbstractBase<W> implements Annotatable<W> {
                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Annotatable
                        public Annotatable<W> annotateParameter(Annotation... annotationArr) {
                            return annotateParameter(Arrays.asList(annotationArr));
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Annotatable
                        public Annotatable<W> annotateParameter(List<? extends Annotation> list) {
                            return annotateParameter((Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Annotatable
                        public Annotatable<W> annotateParameter(AnnotationDescription... annotationDescriptionArr) {
                            return annotateParameter((Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
                        }

                        protected static abstract class Adapter<X> extends AbstractBase<X> {
                            protected abstract ParameterDefinition<X> materialize();

                            protected Adapter() {
                            }

                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition
                            public Annotatable<X> withParameter(TypeDefinition typeDefinition, String str, int i) {
                                return materialize().withParameter(typeDefinition, str, i);
                            }

                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ExceptionDefinition
                            public ExceptionDefinition<X> throwing(Collection<? extends TypeDefinition> collection) {
                                return materialize().throwing(collection);
                            }

                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.TypeVariableDefinition
                            public TypeVariableDefinition.Annotatable<X> typeVariable(String str, Collection<? extends TypeDefinition> collection) {
                                return materialize().typeVariable(str, collection);
                            }

                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                            public ReceiverTypeDefinition<X> intercept(Implementation implementation) {
                                return materialize().intercept(implementation);
                            }

                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                            public ReceiverTypeDefinition<X> withoutCode() {
                                return materialize().withoutCode();
                            }

                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                            public ReceiverTypeDefinition<X> defaultValue(AnnotationValue<?, ?> annotationValue) {
                                return materialize().defaultValue(annotationValue);
                            }

                            /* JADX WARN: Multi-variable type inference failed */
                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition.AbstractBase, net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                            public <V> ReceiverTypeDefinition<X> defaultValue(V v, Class<? extends V> cls) {
                                return materialize().defaultValue(v, cls);
                            }
                        }
                    }
                }

                public interface Simple<V> extends ExceptionDefinition<V> {
                    Annotatable<V> withParameter(Type type);

                    Annotatable<V> withParameter(TypeDefinition typeDefinition);

                    public interface Annotatable<V> extends Simple<V> {
                        Annotatable<V> annotateParameter(Collection<? extends AnnotationDescription> collection);

                        Annotatable<V> annotateParameter(List<? extends Annotation> list);

                        Annotatable<V> annotateParameter(Annotation... annotationArr);

                        Annotatable<V> annotateParameter(AnnotationDescription... annotationDescriptionArr);

                        public static abstract class AbstractBase<W> extends AbstractBase<W> implements Annotatable<W> {
                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Simple.Annotatable
                            public Annotatable<W> annotateParameter(Annotation... annotationArr) {
                                return annotateParameter(Arrays.asList(annotationArr));
                            }

                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Simple.Annotatable
                            public Annotatable<W> annotateParameter(List<? extends Annotation> list) {
                                return annotateParameter((Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
                            }

                            @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Simple.Annotatable
                            public Annotatable<W> annotateParameter(AnnotationDescription... annotationDescriptionArr) {
                                return annotateParameter((Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
                            }

                            protected static abstract class Adapter<X> extends AbstractBase<X> {
                                protected abstract Simple<X> materialize();

                                protected Adapter() {
                                }

                                @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Simple
                                public Annotatable<X> withParameter(TypeDefinition typeDefinition) {
                                    return materialize().withParameter(typeDefinition);
                                }

                                @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ExceptionDefinition
                                public ExceptionDefinition<X> throwing(Collection<? extends TypeDefinition> collection) {
                                    return materialize().throwing(collection);
                                }

                                @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.TypeVariableDefinition
                                public TypeVariableDefinition.Annotatable<X> typeVariable(String str, Collection<? extends TypeDefinition> collection) {
                                    return materialize().typeVariable(str, collection);
                                }

                                @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                                public ReceiverTypeDefinition<X> intercept(Implementation implementation) {
                                    return materialize().intercept(implementation);
                                }

                                @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                                public ReceiverTypeDefinition<X> withoutCode() {
                                    return materialize().withoutCode();
                                }

                                @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                                public ReceiverTypeDefinition<X> defaultValue(AnnotationValue<?, ?> annotationValue) {
                                    return materialize().defaultValue(annotationValue);
                                }

                                /* JADX WARN: Multi-variable type inference failed */
                                @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition.AbstractBase, net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                                public <V> ReceiverTypeDefinition<X> defaultValue(V v, Class<? extends V> cls) {
                                    return materialize().defaultValue(v, cls);
                                }
                            }
                        }
                    }

                    public static abstract class AbstractBase<W> extends ExceptionDefinition.AbstractBase<W> implements Simple<W> {
                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Simple
                        public Annotatable<W> withParameter(Type type) {
                            return withParameter(TypeDefinition.Sort.describe(type));
                        }
                    }
                }

                public interface Initial<V> extends ParameterDefinition<V>, Simple<V> {
                    ExceptionDefinition<V> withParameters(Collection<? extends TypeDefinition> collection);

                    ExceptionDefinition<V> withParameters(List<? extends Type> list);

                    ExceptionDefinition<V> withParameters(Type... typeArr);

                    ExceptionDefinition<V> withParameters(TypeDefinition... typeDefinitionArr);

                    public static abstract class AbstractBase<W> extends AbstractBase<W> implements Initial<W> {
                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Simple
                        public Simple.Annotatable<W> withParameter(Type type) {
                            return withParameter(TypeDefinition.Sort.describe(type));
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Initial
                        public ExceptionDefinition<W> withParameters(Type... typeArr) {
                            return withParameters(Arrays.asList(typeArr));
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Initial
                        public ExceptionDefinition<W> withParameters(List<? extends Type> list) {
                            return withParameters((Collection<? extends TypeDefinition>) new TypeList.Generic.ForLoadedTypes(list));
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Initial
                        public ExceptionDefinition<W> withParameters(TypeDefinition... typeDefinitionArr) {
                            return withParameters((Collection<? extends TypeDefinition>) Arrays.asList(typeDefinitionArr));
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Initial
                        public ExceptionDefinition<W> withParameters(Collection<? extends TypeDefinition> collection) {
                            Iterator<? extends TypeDefinition> it = collection.iterator();
                            Simple simpleWithParameter = this;
                            while (it.hasNext()) {
                                simpleWithParameter = simpleWithParameter.withParameter(it.next());
                            }
                            return simpleWithParameter;
                        }
                    }
                }

                public static abstract class AbstractBase<V> extends ExceptionDefinition.AbstractBase<V> implements ParameterDefinition<V> {
                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition
                    public Annotatable<V> withParameter(Type type, String str, ModifierContributor.ForParameter... forParameterArr) {
                        return withParameter(type, str, Arrays.asList(forParameterArr));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition
                    public Annotatable<V> withParameter(Type type, String str, Collection<? extends ModifierContributor.ForParameter> collection) {
                        return withParameter(type, str, ModifierContributor.Resolver.of(collection).resolve());
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition
                    public Annotatable<V> withParameter(Type type, String str, int i) {
                        return withParameter(TypeDefinition.Sort.describe(type), str, i);
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition
                    public Annotatable<V> withParameter(TypeDefinition typeDefinition, String str, ModifierContributor.ForParameter... forParameterArr) {
                        return withParameter(typeDefinition, str, Arrays.asList(forParameterArr));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition
                    public Annotatable<V> withParameter(TypeDefinition typeDefinition, String str, Collection<? extends ModifierContributor.ForParameter> collection) {
                        return withParameter(typeDefinition, str, ModifierContributor.Resolver.of(collection).resolve());
                    }
                }
            }

            public static abstract class AbstractBase<U> extends AbstractBase.Delegator<U> implements MethodDefinition<U> {
                @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition
                public MethodDefinition<U> annotateMethod(Annotation... annotationArr) {
                    return annotateMethod(Arrays.asList(annotationArr));
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition
                public MethodDefinition<U> annotateMethod(List<? extends Annotation> list) {
                    return annotateMethod((Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition
                public MethodDefinition<U> annotateMethod(AnnotationDescription... annotationDescriptionArr) {
                    return annotateMethod((Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition
                public MethodDefinition<U> annotateParameter(int i, Annotation... annotationArr) {
                    return annotateParameter(i, Arrays.asList(annotationArr));
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition
                public MethodDefinition<U> annotateParameter(int i, List<? extends Annotation> list) {
                    return annotateParameter(i, (Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition
                public MethodDefinition<U> annotateParameter(int i, AnnotationDescription... annotationDescriptionArr) {
                    return annotateParameter(i, (Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
                }

                protected static abstract class Adapter<V> extends ReceiverTypeDefinition.AbstractBase<V> {
                    protected final MethodRegistry.Handler handler;
                    protected final MethodAttributeAppender.Factory methodAttributeAppenderFactory;
                    protected final Transformer<MethodDescription> transformer;

                    protected abstract MethodDefinition<V> materialize(MethodRegistry.Handler handler, MethodAttributeAppender.Factory factory, Transformer<MethodDescription> transformer);

                    protected boolean canEqual(Object obj) {
                        return obj instanceof Adapter;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof Adapter)) {
                            return false;
                        }
                        Adapter adapter = (Adapter) obj;
                        if (!adapter.canEqual(this)) {
                            return false;
                        }
                        MethodRegistry.Handler handler = this.handler;
                        MethodRegistry.Handler handler2 = adapter.handler;
                        if (handler != null ? !handler.equals(handler2) : handler2 != null) {
                            return false;
                        }
                        MethodAttributeAppender.Factory factory = this.methodAttributeAppenderFactory;
                        MethodAttributeAppender.Factory factory2 = adapter.methodAttributeAppenderFactory;
                        if (factory != null ? !factory.equals(factory2) : factory2 != null) {
                            return false;
                        }
                        Transformer<MethodDescription> transformer = this.transformer;
                        Transformer<MethodDescription> transformer2 = adapter.transformer;
                        return transformer != null ? transformer.equals(transformer2) : transformer2 == null;
                    }

                    public int hashCode() {
                        MethodRegistry.Handler handler = this.handler;
                        int iHashCode = handler == null ? 43 : handler.hashCode();
                        MethodAttributeAppender.Factory factory = this.methodAttributeAppenderFactory;
                        int iHashCode2 = ((iHashCode + 59) * 59) + (factory == null ? 43 : factory.hashCode());
                        Transformer<MethodDescription> transformer = this.transformer;
                        return (iHashCode2 * 59) + (transformer != null ? transformer.hashCode() : 43);
                    }

                    protected Adapter(MethodRegistry.Handler handler, MethodAttributeAppender.Factory factory, Transformer<MethodDescription> transformer) {
                        this.handler = handler;
                        this.methodAttributeAppenderFactory = factory;
                        this.transformer = transformer;
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition
                    public MethodDefinition<V> attribute(MethodAttributeAppender.Factory factory) {
                        return materialize(this.handler, new MethodAttributeAppender.Factory.Compound(this.methodAttributeAppenderFactory, factory), this.transformer);
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition
                    public MethodDefinition<V> transform(Transformer<MethodDescription> transformer) {
                        return materialize(this.handler, this.methodAttributeAppenderFactory, new Transformer.Compound(this.transformer, transformer));
                    }
                }
            }
        }

        public static abstract class AbstractBase<S> implements Builder<S> {
            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public Builder<S> annotateType(Annotation... annotationArr) {
                return annotateType(Arrays.asList(annotationArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public Builder<S> annotateType(List<? extends Annotation> list) {
                return annotateType((Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public Builder<S> annotateType(AnnotationDescription... annotationDescriptionArr) {
                return annotateType((Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public Builder<S> modifiers(ModifierContributor.ForType... forTypeArr) {
                return modifiers(Arrays.asList(forTypeArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public Builder<S> modifiers(Collection<? extends ModifierContributor.ForType> collection) {
                return modifiers(ModifierContributor.Resolver.of(collection).resolve());
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public Builder<S> merge(ModifierContributor.ForType... forTypeArr) {
                return merge(Arrays.asList(forTypeArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public MethodDefinition.ImplementationDefinition.Optional<S> implement(Type... typeArr) {
                return implement(Arrays.asList(typeArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public MethodDefinition.ImplementationDefinition.Optional<S> implement(List<? extends Type> list) {
                return implement((Collection<? extends TypeDefinition>) new TypeList.Generic.ForLoadedTypes(list));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public MethodDefinition.ImplementationDefinition.Optional<S> implement(TypeDefinition... typeDefinitionArr) {
                return implement((Collection<? extends TypeDefinition>) Arrays.asList(typeDefinitionArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public TypeVariableDefinition<S> typeVariable(String str) {
                return typeVariable(str, TypeDescription.Generic.OBJECT);
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public TypeVariableDefinition<S> typeVariable(String str, Type... typeArr) {
                return typeVariable(str, Arrays.asList(typeArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public TypeVariableDefinition<S> typeVariable(String str, List<? extends Type> list) {
                return typeVariable(str, (Collection<? extends TypeDefinition>) new TypeList.Generic.ForLoadedTypes(list));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public TypeVariableDefinition<S> typeVariable(String str, TypeDefinition... typeDefinitionArr) {
                return typeVariable(str, (Collection<? extends TypeDefinition>) Arrays.asList(typeDefinitionArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public FieldDefinition.Optional.Valuable<S> defineField(String str, Type type, ModifierContributor.ForField... forFieldArr) {
                return defineField(str, type, Arrays.asList(forFieldArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public FieldDefinition.Optional.Valuable<S> defineField(String str, Type type, Collection<? extends ModifierContributor.ForField> collection) {
                return defineField(str, type, ModifierContributor.Resolver.of(collection).resolve());
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public FieldDefinition.Optional.Valuable<S> defineField(String str, Type type, int i) {
                return defineField(str, TypeDefinition.Sort.describe(type), i);
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public FieldDefinition.Optional.Valuable<S> defineField(String str, TypeDefinition typeDefinition, ModifierContributor.ForField... forFieldArr) {
                return defineField(str, typeDefinition, Arrays.asList(forFieldArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public FieldDefinition.Optional.Valuable<S> defineField(String str, TypeDefinition typeDefinition, Collection<? extends ModifierContributor.ForField> collection) {
                return defineField(str, typeDefinition, ModifierContributor.Resolver.of(collection).resolve());
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public FieldDefinition.Optional.Valuable<S> define(Field field) {
                return define(new FieldDescription.ForLoadedField(field));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public FieldDefinition.Optional.Valuable<S> define(FieldDescription fieldDescription) {
                return defineField(fieldDescription.getName(), fieldDescription.getType(), fieldDescription.getModifiers());
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public FieldDefinition.Optional<S> serialVersionUid(long j) {
                return defineField("serialVersionUID", Long.TYPE, Visibility.PRIVATE, FieldManifestation.FINAL, Ownership.STATIC).value(j);
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public FieldDefinition.Valuable<S> field(ElementMatcher<? super FieldDescription> elementMatcher) {
                return field(new LatentMatcher.Resolved(elementMatcher));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public Builder<S> ignoreAlso(ElementMatcher<? super MethodDescription> elementMatcher) {
                return ignoreAlso(new LatentMatcher.Resolved(elementMatcher));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public MethodDefinition.ParameterDefinition.Initial<S> defineMethod(String str, Type type, ModifierContributor.ForMethod... forMethodArr) {
                return defineMethod(str, type, Arrays.asList(forMethodArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public MethodDefinition.ParameterDefinition.Initial<S> defineMethod(String str, Type type, Collection<? extends ModifierContributor.ForMethod> collection) {
                return defineMethod(str, type, ModifierContributor.Resolver.of(collection).resolve());
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public MethodDefinition.ParameterDefinition.Initial<S> defineMethod(String str, Type type, int i) {
                return defineMethod(str, TypeDefinition.Sort.describe(type), i);
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public MethodDefinition.ParameterDefinition.Initial<S> defineMethod(String str, TypeDefinition typeDefinition, ModifierContributor.ForMethod... forMethodArr) {
                return defineMethod(str, typeDefinition, Arrays.asList(forMethodArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public MethodDefinition.ParameterDefinition.Initial<S> defineMethod(String str, TypeDefinition typeDefinition, Collection<? extends ModifierContributor.ForMethod> collection) {
                return defineMethod(str, typeDefinition, ModifierContributor.Resolver.of(collection).resolve());
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public MethodDefinition.ParameterDefinition.Initial<S> defineConstructor(ModifierContributor.ForMethod... forMethodArr) {
                return defineConstructor(Arrays.asList(forMethodArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public MethodDefinition.ParameterDefinition.Initial<S> defineConstructor(Collection<? extends ModifierContributor.ForMethod> collection) {
                return defineConstructor(ModifierContributor.Resolver.of(collection).resolve());
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public MethodDefinition.ImplementationDefinition<S> define(Method method) {
                return define(new MethodDescription.ForLoadedMethod(method));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public MethodDefinition.ImplementationDefinition<S> define(Constructor<?> constructor) {
                return define(new MethodDescription.ForLoadedConstructor(constructor));
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r0v17 */
            /* JADX WARN: Type inference failed for: r0v18 */
            /* JADX WARN: Type inference failed for: r0v4, types: [net.bytebuddy.dynamic.DynamicType$Builder$MethodDefinition$ExceptionDefinition] */
            /* JADX WARN: Type inference failed for: r0v5, types: [net.bytebuddy.dynamic.DynamicType$Builder$MethodDefinition$ExceptionDefinition] */
            /* JADX WARN: Type inference failed for: r0v9, types: [net.bytebuddy.dynamic.DynamicType$Builder$MethodDefinition$ParameterDefinition] */
            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public MethodDefinition.ImplementationDefinition<S> define(MethodDescription methodDescription) {
                MethodDefinition.ParameterDefinition.Initial initialDefineMethod;
                ?? WithParameters;
                if (methodDescription.isConstructor()) {
                    initialDefineMethod = defineConstructor(methodDescription.getModifiers());
                } else {
                    initialDefineMethod = defineMethod(methodDescription.getInternalName(), methodDescription.getReturnType(), methodDescription.getModifiers());
                }
                ParameterList<?> parameters = methodDescription.getParameters();
                if (parameters.hasExplicitMetaData()) {
                    Iterator it = parameters.iterator();
                    WithParameters = initialDefineMethod;
                    while (it.hasNext()) {
                        ParameterDescription parameterDescription = (ParameterDescription) it.next();
                        WithParameters = WithParameters.withParameter(parameterDescription.getType(), parameterDescription.getName(), parameterDescription.getModifiers());
                    }
                } else {
                    WithParameters = initialDefineMethod.withParameters((Collection<? extends TypeDefinition>) parameters.asTypeList());
                }
                MethodDefinition.TypeVariableDefinition typeVariableDefinitionThrowing = WithParameters.throwing(methodDescription.getExceptionTypes());
                for (TypeDescription.Generic generic : methodDescription.getTypeVariables()) {
                    typeVariableDefinitionThrowing = typeVariableDefinitionThrowing.typeVariable(generic.getSymbol(), (Collection<? extends TypeDefinition>) generic.getUpperBounds());
                }
                return typeVariableDefinitionThrowing;
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public FieldDefinition.Optional<S> defineProperty(String str, Type type) {
                return defineProperty(str, TypeDefinition.Sort.describe(type));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public FieldDefinition.Optional<S> defineProperty(String str, Type type, boolean z) {
                return defineProperty(str, TypeDefinition.Sort.describe(type), z);
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public FieldDefinition.Optional<S> defineProperty(String str, TypeDefinition typeDefinition) {
                return defineProperty(str, typeDefinition, false);
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public FieldDefinition.Optional<S> defineProperty(String str, TypeDefinition typeDefinition, boolean z) {
                FieldManifestation fieldManifestation;
                Builder builderIntercept;
                if (str.isEmpty()) {
                    throw new IllegalArgumentException("A bean property cannot have an empty name");
                }
                if (typeDefinition.represents(Void.TYPE)) {
                    throw new IllegalArgumentException("A bean property cannot have a void type");
                }
                if (!z) {
                    builderIntercept = defineMethod("set" + Character.toUpperCase(str.charAt(0)) + str.substring(1), Void.TYPE, Visibility.PUBLIC).withParameters(typeDefinition).intercept(FieldAccessor.ofField(str));
                    fieldManifestation = FieldManifestation.PLAIN;
                } else {
                    fieldManifestation = FieldManifestation.FINAL;
                    builderIntercept = this;
                }
                return builderIntercept.defineMethod(((typeDefinition.represents(Boolean.TYPE) || typeDefinition.represents(Boolean.class)) ? "is" : "get") + Character.toUpperCase(str.charAt(0)) + str.substring(1), typeDefinition, Visibility.PUBLIC).intercept(FieldAccessor.ofField(str)).defineField(str, typeDefinition, Visibility.PRIVATE, fieldManifestation);
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public MethodDefinition.ImplementationDefinition<S> method(ElementMatcher<? super MethodDescription> elementMatcher) {
                return invokable(ElementMatchers.isMethod().and(elementMatcher));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public MethodDefinition.ImplementationDefinition<S> constructor(ElementMatcher<? super MethodDescription> elementMatcher) {
                return invokable(ElementMatchers.isConstructor().and(elementMatcher));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public MethodDefinition.ImplementationDefinition<S> invokable(ElementMatcher<? super MethodDescription> elementMatcher) {
                return invokable(new LatentMatcher.Resolved(elementMatcher));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public Unloaded<S> make(TypePool typePool) {
                return make(TypeResolutionStrategy.Passive.INSTANCE, typePool);
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Builder
            public Unloaded<S> make() {
                return make(TypeResolutionStrategy.Passive.INSTANCE);
            }

            public static abstract class Delegator<U> extends AbstractBase<U> {
                protected abstract Builder<U> materialize();

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> visit(AsmVisitorWrapper asmVisitorWrapper) {
                    return materialize().visit(asmVisitorWrapper);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> initializer(LoadedTypeInitializer loadedTypeInitializer) {
                    return materialize().initializer(loadedTypeInitializer);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> annotateType(Collection<? extends AnnotationDescription> collection) {
                    return materialize().annotateType(collection);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> attribute(TypeAttributeAppender typeAttributeAppender) {
                    return materialize().attribute(typeAttributeAppender);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> modifiers(int i) {
                    return materialize().modifiers(i);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> merge(Collection<? extends ModifierContributor.ForType> collection) {
                    return materialize().merge(collection);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> name(String str) {
                    return materialize().name(str);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public MethodDefinition.ImplementationDefinition.Optional<U> implement(Collection<? extends TypeDefinition> collection) {
                    return materialize().implement(collection);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> initializer(ByteCodeAppender byteCodeAppender) {
                    return materialize().initializer(byteCodeAppender);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase, net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> ignoreAlso(ElementMatcher<? super MethodDescription> elementMatcher) {
                    return materialize().ignoreAlso(elementMatcher);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> ignoreAlso(LatentMatcher<? super MethodDescription> latentMatcher) {
                    return materialize().ignoreAlso(latentMatcher);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public TypeVariableDefinition<U> typeVariable(String str, Collection<? extends TypeDefinition> collection) {
                    return materialize().typeVariable(str, collection);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> transform(ElementMatcher<? super TypeDescription.Generic> elementMatcher, Transformer<TypeVariableToken> transformer) {
                    return materialize().transform(elementMatcher, transformer);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public FieldDefinition.Optional.Valuable<U> defineField(String str, TypeDefinition typeDefinition, int i) {
                    return materialize().defineField(str, typeDefinition, i);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public FieldDefinition.Valuable<U> field(LatentMatcher<? super FieldDescription> latentMatcher) {
                    return materialize().field(latentMatcher);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public MethodDefinition.ParameterDefinition.Initial<U> defineMethod(String str, TypeDefinition typeDefinition, int i) {
                    return materialize().defineMethod(str, typeDefinition, i);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public MethodDefinition.ParameterDefinition.Initial<U> defineConstructor(int i) {
                    return materialize().defineConstructor(i);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public MethodDefinition.ImplementationDefinition<U> invokable(LatentMatcher<? super MethodDescription> latentMatcher) {
                    return materialize().invokable(latentMatcher);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase, net.bytebuddy.dynamic.DynamicType.Builder
                public Unloaded<U> make() {
                    return materialize().make();
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Unloaded<U> make(TypeResolutionStrategy typeResolutionStrategy) {
                    return materialize().make(typeResolutionStrategy);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase, net.bytebuddy.dynamic.DynamicType.Builder
                public Unloaded<U> make(TypePool typePool) {
                    return materialize().make(typePool);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Unloaded<U> make(TypeResolutionStrategy typeResolutionStrategy, TypePool typePool) {
                    return materialize().make(typeResolutionStrategy, typePool);
                }
            }

            public static abstract class Adapter<U> extends AbstractBase<U> {
                protected final AnnotationRetention annotationRetention;
                protected final AnnotationValueFilter.Factory annotationValueFilterFactory;
                protected final AsmVisitorWrapper asmVisitorWrapper;
                protected final AuxiliaryType.NamingStrategy auxiliaryTypeNamingStrategy;
                protected final ClassFileVersion classFileVersion;
                protected final FieldRegistry fieldRegistry;
                protected final LatentMatcher<? super MethodDescription> ignoredMethods;
                protected final Implementation.Context.Factory implementationContextFactory;
                protected final InstrumentedType.WithFlexibleName instrumentedType;
                protected final MethodGraph.Compiler methodGraphCompiler;
                protected final MethodRegistry methodRegistry;
                protected final TypeAttributeAppender typeAttributeAppender;
                protected final TypeValidation typeValidation;

                protected abstract Builder<U> materialize(InstrumentedType.WithFlexibleName withFlexibleName, FieldRegistry fieldRegistry, MethodRegistry methodRegistry, TypeAttributeAppender typeAttributeAppender, AsmVisitorWrapper asmVisitorWrapper, ClassFileVersion classFileVersion, AuxiliaryType.NamingStrategy namingStrategy, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention, Implementation.Context.Factory factory2, MethodGraph.Compiler compiler, TypeValidation typeValidation, LatentMatcher<? super MethodDescription> latentMatcher);

                protected boolean canEqual(Object obj) {
                    return obj instanceof Adapter;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Adapter)) {
                        return false;
                    }
                    Adapter adapter = (Adapter) obj;
                    if (!adapter.canEqual(this)) {
                        return false;
                    }
                    InstrumentedType.WithFlexibleName withFlexibleName = this.instrumentedType;
                    InstrumentedType.WithFlexibleName withFlexibleName2 = adapter.instrumentedType;
                    if (withFlexibleName != null ? !withFlexibleName.equals(withFlexibleName2) : withFlexibleName2 != null) {
                        return false;
                    }
                    FieldRegistry fieldRegistry = this.fieldRegistry;
                    FieldRegistry fieldRegistry2 = adapter.fieldRegistry;
                    if (fieldRegistry != null ? !fieldRegistry.equals(fieldRegistry2) : fieldRegistry2 != null) {
                        return false;
                    }
                    MethodRegistry methodRegistry = this.methodRegistry;
                    MethodRegistry methodRegistry2 = adapter.methodRegistry;
                    if (methodRegistry != null ? !methodRegistry.equals(methodRegistry2) : methodRegistry2 != null) {
                        return false;
                    }
                    TypeAttributeAppender typeAttributeAppender = this.typeAttributeAppender;
                    TypeAttributeAppender typeAttributeAppender2 = adapter.typeAttributeAppender;
                    if (typeAttributeAppender != null ? !typeAttributeAppender.equals(typeAttributeAppender2) : typeAttributeAppender2 != null) {
                        return false;
                    }
                    AsmVisitorWrapper asmVisitorWrapper = this.asmVisitorWrapper;
                    AsmVisitorWrapper asmVisitorWrapper2 = adapter.asmVisitorWrapper;
                    if (asmVisitorWrapper != null ? !asmVisitorWrapper.equals(asmVisitorWrapper2) : asmVisitorWrapper2 != null) {
                        return false;
                    }
                    ClassFileVersion classFileVersion = this.classFileVersion;
                    ClassFileVersion classFileVersion2 = adapter.classFileVersion;
                    if (classFileVersion != null ? !classFileVersion.equals(classFileVersion2) : classFileVersion2 != null) {
                        return false;
                    }
                    AuxiliaryType.NamingStrategy namingStrategy = this.auxiliaryTypeNamingStrategy;
                    AuxiliaryType.NamingStrategy namingStrategy2 = adapter.auxiliaryTypeNamingStrategy;
                    if (namingStrategy != null ? !namingStrategy.equals(namingStrategy2) : namingStrategy2 != null) {
                        return false;
                    }
                    AnnotationValueFilter.Factory factory = this.annotationValueFilterFactory;
                    AnnotationValueFilter.Factory factory2 = adapter.annotationValueFilterFactory;
                    if (factory != null ? !factory.equals(factory2) : factory2 != null) {
                        return false;
                    }
                    AnnotationRetention annotationRetention = this.annotationRetention;
                    AnnotationRetention annotationRetention2 = adapter.annotationRetention;
                    if (annotationRetention != null ? !annotationRetention.equals(annotationRetention2) : annotationRetention2 != null) {
                        return false;
                    }
                    Implementation.Context.Factory factory3 = this.implementationContextFactory;
                    Implementation.Context.Factory factory4 = adapter.implementationContextFactory;
                    if (factory3 != null ? !factory3.equals(factory4) : factory4 != null) {
                        return false;
                    }
                    MethodGraph.Compiler compiler = this.methodGraphCompiler;
                    MethodGraph.Compiler compiler2 = adapter.methodGraphCompiler;
                    if (compiler != null ? !compiler.equals(compiler2) : compiler2 != null) {
                        return false;
                    }
                    TypeValidation typeValidation = this.typeValidation;
                    TypeValidation typeValidation2 = adapter.typeValidation;
                    if (typeValidation != null ? !typeValidation.equals(typeValidation2) : typeValidation2 != null) {
                        return false;
                    }
                    LatentMatcher<? super MethodDescription> latentMatcher = this.ignoredMethods;
                    LatentMatcher<? super MethodDescription> latentMatcher2 = adapter.ignoredMethods;
                    return latentMatcher != null ? latentMatcher.equals(latentMatcher2) : latentMatcher2 == null;
                }

                public int hashCode() {
                    InstrumentedType.WithFlexibleName withFlexibleName = this.instrumentedType;
                    int iHashCode = withFlexibleName == null ? 43 : withFlexibleName.hashCode();
                    FieldRegistry fieldRegistry = this.fieldRegistry;
                    int iHashCode2 = ((iHashCode + 59) * 59) + (fieldRegistry == null ? 43 : fieldRegistry.hashCode());
                    MethodRegistry methodRegistry = this.methodRegistry;
                    int iHashCode3 = (iHashCode2 * 59) + (methodRegistry == null ? 43 : methodRegistry.hashCode());
                    TypeAttributeAppender typeAttributeAppender = this.typeAttributeAppender;
                    int iHashCode4 = (iHashCode3 * 59) + (typeAttributeAppender == null ? 43 : typeAttributeAppender.hashCode());
                    AsmVisitorWrapper asmVisitorWrapper = this.asmVisitorWrapper;
                    int iHashCode5 = (iHashCode4 * 59) + (asmVisitorWrapper == null ? 43 : asmVisitorWrapper.hashCode());
                    ClassFileVersion classFileVersion = this.classFileVersion;
                    int iHashCode6 = (iHashCode5 * 59) + (classFileVersion == null ? 43 : classFileVersion.hashCode());
                    AuxiliaryType.NamingStrategy namingStrategy = this.auxiliaryTypeNamingStrategy;
                    int iHashCode7 = (iHashCode6 * 59) + (namingStrategy == null ? 43 : namingStrategy.hashCode());
                    AnnotationValueFilter.Factory factory = this.annotationValueFilterFactory;
                    int iHashCode8 = (iHashCode7 * 59) + (factory == null ? 43 : factory.hashCode());
                    AnnotationRetention annotationRetention = this.annotationRetention;
                    int iHashCode9 = (iHashCode8 * 59) + (annotationRetention == null ? 43 : annotationRetention.hashCode());
                    Implementation.Context.Factory factory2 = this.implementationContextFactory;
                    int iHashCode10 = (iHashCode9 * 59) + (factory2 == null ? 43 : factory2.hashCode());
                    MethodGraph.Compiler compiler = this.methodGraphCompiler;
                    int iHashCode11 = (iHashCode10 * 59) + (compiler == null ? 43 : compiler.hashCode());
                    TypeValidation typeValidation = this.typeValidation;
                    int iHashCode12 = (iHashCode11 * 59) + (typeValidation == null ? 43 : typeValidation.hashCode());
                    LatentMatcher<? super MethodDescription> latentMatcher = this.ignoredMethods;
                    return (iHashCode12 * 59) + (latentMatcher != null ? latentMatcher.hashCode() : 43);
                }

                protected Adapter(InstrumentedType.WithFlexibleName withFlexibleName, FieldRegistry fieldRegistry, MethodRegistry methodRegistry, TypeAttributeAppender typeAttributeAppender, AsmVisitorWrapper asmVisitorWrapper, ClassFileVersion classFileVersion, AuxiliaryType.NamingStrategy namingStrategy, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention, Implementation.Context.Factory factory2, MethodGraph.Compiler compiler, TypeValidation typeValidation, LatentMatcher<? super MethodDescription> latentMatcher) {
                    this.instrumentedType = withFlexibleName;
                    this.fieldRegistry = fieldRegistry;
                    this.methodRegistry = methodRegistry;
                    this.typeAttributeAppender = typeAttributeAppender;
                    this.asmVisitorWrapper = asmVisitorWrapper;
                    this.classFileVersion = classFileVersion;
                    this.auxiliaryTypeNamingStrategy = namingStrategy;
                    this.annotationValueFilterFactory = factory;
                    this.annotationRetention = annotationRetention;
                    this.implementationContextFactory = factory2;
                    this.methodGraphCompiler = compiler;
                    this.typeValidation = typeValidation;
                    this.ignoredMethods = latentMatcher;
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public FieldDefinition.Optional.Valuable<U> defineField(String str, TypeDefinition typeDefinition, int i) {
                    return new FieldDefinitionAdapter(this, new FieldDescription.Token(str, i, typeDefinition.asGenericType()));
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public FieldDefinition.Valuable<U> field(LatentMatcher<? super FieldDescription> latentMatcher) {
                    return new FieldMatchAdapter(this, latentMatcher);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public MethodDefinition.ParameterDefinition.Initial<U> defineMethod(String str, TypeDefinition typeDefinition, int i) {
                    return new MethodDefinitionAdapter(new MethodDescription.Token(str, i, typeDefinition.asGenericType()));
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public MethodDefinition.ParameterDefinition.Initial<U> defineConstructor(int i) {
                    return new MethodDefinitionAdapter(new MethodDescription.Token(i));
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public MethodDefinition.ImplementationDefinition<U> invokable(LatentMatcher<? super MethodDescription> latentMatcher) {
                    return new MethodMatchAdapter(latentMatcher);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public MethodDefinition.ImplementationDefinition.Optional<U> implement(Collection<? extends TypeDefinition> collection) {
                    return new OptionalMethodMatchAdapter(new TypeList.Generic.Explicit(new ArrayList(collection)));
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> ignoreAlso(LatentMatcher<? super MethodDescription> latentMatcher) {
                    return materialize(this.instrumentedType, this.fieldRegistry, this.methodRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, new LatentMatcher.Disjunction(this.ignoredMethods, latentMatcher));
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> initializer(ByteCodeAppender byteCodeAppender) {
                    return materialize(this.instrumentedType.withInitializer(byteCodeAppender), this.fieldRegistry, this.methodRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.ignoredMethods);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> initializer(LoadedTypeInitializer loadedTypeInitializer) {
                    return materialize(this.instrumentedType.withInitializer(loadedTypeInitializer), this.fieldRegistry, this.methodRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.ignoredMethods);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> name(String str) {
                    return materialize(this.instrumentedType.withName(str), this.fieldRegistry, this.methodRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.ignoredMethods);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> modifiers(int i) {
                    return materialize(this.instrumentedType.withModifiers(i), this.fieldRegistry, this.methodRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.ignoredMethods);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> merge(Collection<? extends ModifierContributor.ForType> collection) {
                    return materialize(this.instrumentedType.withModifiers(ModifierContributor.Resolver.of(collection).resolve(this.instrumentedType.getModifiers())), this.fieldRegistry, this.methodRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.ignoredMethods);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public TypeVariableDefinition<U> typeVariable(String str, Collection<? extends TypeDefinition> collection) {
                    return new TypeVariableDefinitionAdapter(new TypeVariableToken(str, new TypeList.Generic.Explicit(new ArrayList(collection))));
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> transform(ElementMatcher<? super TypeDescription.Generic> elementMatcher, Transformer<TypeVariableToken> transformer) {
                    return materialize(this.instrumentedType.withTypeVariables(elementMatcher, transformer), this.fieldRegistry, this.methodRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.ignoredMethods);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> attribute(TypeAttributeAppender typeAttributeAppender) {
                    return materialize(this.instrumentedType, this.fieldRegistry, this.methodRegistry, new TypeAttributeAppender.Compound(this.typeAttributeAppender, typeAttributeAppender), this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.ignoredMethods);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> annotateType(Collection<? extends AnnotationDescription> collection) {
                    return materialize(this.instrumentedType.withAnnotations((List<? extends AnnotationDescription>) new ArrayList(collection)), this.fieldRegistry, this.methodRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.ignoredMethods);
                }

                @Override // net.bytebuddy.dynamic.DynamicType.Builder
                public Builder<U> visit(AsmVisitorWrapper asmVisitorWrapper) {
                    return materialize(this.instrumentedType, this.fieldRegistry, this.methodRegistry, this.typeAttributeAppender, new AsmVisitorWrapper.Compound(this.asmVisitorWrapper, asmVisitorWrapper), this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.ignoredMethods);
                }

                protected class TypeVariableDefinitionAdapter extends TypeVariableDefinition.AbstractBase<U> {
                    private final TypeVariableToken token;

                    protected TypeVariableDefinitionAdapter(TypeVariableToken typeVariableToken) {
                        this.token = typeVariableToken;
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.TypeVariableDefinition
                    public TypeVariableDefinition<U> annotateTypeVariable(Collection<? extends AnnotationDescription> collection) {
                        return new TypeVariableDefinitionAdapter(new TypeVariableToken(this.token.getSymbol(), this.token.getBounds(), CompoundList.of((List) this.token.getAnnotations(), (List) new ArrayList(collection))));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase.Delegator
                    protected Builder<U> materialize() {
                        Adapter adapter = Adapter.this;
                        return adapter.materialize(adapter.instrumentedType.withTypeVariable(this.token), Adapter.this.fieldRegistry, Adapter.this.methodRegistry, Adapter.this.typeAttributeAppender, Adapter.this.asmVisitorWrapper, Adapter.this.classFileVersion, Adapter.this.auxiliaryTypeNamingStrategy, Adapter.this.annotationValueFilterFactory, Adapter.this.annotationRetention, Adapter.this.implementationContextFactory, Adapter.this.methodGraphCompiler, Adapter.this.typeValidation, Adapter.this.ignoredMethods);
                    }

                    private Adapter<?> getOuter() {
                        return Adapter.this;
                    }

                    public boolean equals(Object obj) {
                        if (this != obj) {
                            if (obj != null && getClass() == obj.getClass()) {
                                TypeVariableDefinitionAdapter typeVariableDefinitionAdapter = (TypeVariableDefinitionAdapter) obj;
                                if (!getOuter().equals(typeVariableDefinitionAdapter.getOuter()) || !this.token.equals(typeVariableDefinitionAdapter.token)) {
                                }
                            }
                            return false;
                        }
                        return true;
                    }

                    public int hashCode() {
                        return (getOuter().hashCode() * 31) + this.token.hashCode();
                    }
                }

                protected class FieldDefinitionAdapter extends FieldDefinition.Optional.Valuable.AbstractBase.Adapter<U> {
                    private final FieldDescription.Token token;

                    protected FieldDefinitionAdapter(Adapter adapter, FieldDescription.Token token) {
                        this(FieldAttributeAppender.ForInstrumentedField.INSTANCE, Transformer.NoOp.make(), FieldDescription.NO_DEFAULT_VALUE, token);
                    }

                    protected FieldDefinitionAdapter(FieldAttributeAppender.Factory factory, Transformer<FieldDescription> transformer, Object obj, FieldDescription.Token token) {
                        super(factory, transformer, obj);
                        this.token = token;
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition
                    public FieldDefinition.Optional<U> annotateField(Collection<? extends AnnotationDescription> collection) {
                        return new FieldDefinitionAdapter(this.fieldAttributeAppenderFactory, this.transformer, this.defaultValue, new FieldDescription.Token(this.token.getName(), this.token.getModifiers(), this.token.getType(), CompoundList.of((List) this.token.getAnnotations(), (List) new ArrayList(collection))));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase.Delegator
                    protected Builder<U> materialize() {
                        Adapter adapter = Adapter.this;
                        return adapter.materialize(adapter.instrumentedType.withField(this.token), Adapter.this.fieldRegistry.prepend(new LatentMatcher.ForFieldToken(this.token), this.fieldAttributeAppenderFactory, this.defaultValue, this.transformer), Adapter.this.methodRegistry, Adapter.this.typeAttributeAppender, Adapter.this.asmVisitorWrapper, Adapter.this.classFileVersion, Adapter.this.auxiliaryTypeNamingStrategy, Adapter.this.annotationValueFilterFactory, Adapter.this.annotationRetention, Adapter.this.implementationContextFactory, Adapter.this.methodGraphCompiler, Adapter.this.typeValidation, Adapter.this.ignoredMethods);
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional.Valuable.AbstractBase.Adapter
                    protected FieldDefinition.Optional<U> materialize(FieldAttributeAppender.Factory factory, Transformer<FieldDescription> transformer, Object obj) {
                        return new FieldDefinitionAdapter(factory, transformer, obj, this.token);
                    }

                    private Adapter<?> getOuter() {
                        return Adapter.this;
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional.Valuable.AbstractBase.Adapter
                    public boolean equals(Object obj) {
                        if (this != obj) {
                            if (obj != null && getClass() == obj.getClass() && super.equals(obj)) {
                                FieldDefinitionAdapter fieldDefinitionAdapter = (FieldDefinitionAdapter) obj;
                                if (!getOuter().equals(fieldDefinitionAdapter.getOuter()) || !this.token.equals(fieldDefinitionAdapter.token)) {
                                }
                            }
                            return false;
                        }
                        return true;
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional.Valuable.AbstractBase.Adapter
                    public int hashCode() {
                        return (((super.hashCode() * 31) + getOuter().hashCode()) * 31) + this.token.hashCode();
                    }
                }

                protected class FieldMatchAdapter extends FieldDefinition.Optional.Valuable.AbstractBase.Adapter<U> {
                    private final LatentMatcher<? super FieldDescription> matcher;

                    protected FieldMatchAdapter(Adapter adapter, LatentMatcher<? super FieldDescription> latentMatcher) {
                        this(FieldAttributeAppender.NoOp.INSTANCE, Transformer.NoOp.make(), FieldDescription.NO_DEFAULT_VALUE, latentMatcher);
                    }

                    protected FieldMatchAdapter(FieldAttributeAppender.Factory factory, Transformer<FieldDescription> transformer, Object obj, LatentMatcher<? super FieldDescription> latentMatcher) {
                        super(factory, transformer, obj);
                        this.matcher = latentMatcher;
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition
                    public FieldDefinition.Optional<U> annotateField(Collection<? extends AnnotationDescription> collection) {
                        return attribute(new FieldAttributeAppender.Explicit(new ArrayList(collection)));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase.Delegator
                    protected Builder<U> materialize() {
                        Adapter adapter = Adapter.this;
                        return adapter.materialize(adapter.instrumentedType, Adapter.this.fieldRegistry.prepend(this.matcher, this.fieldAttributeAppenderFactory, this.defaultValue, this.transformer), Adapter.this.methodRegistry, Adapter.this.typeAttributeAppender, Adapter.this.asmVisitorWrapper, Adapter.this.classFileVersion, Adapter.this.auxiliaryTypeNamingStrategy, Adapter.this.annotationValueFilterFactory, Adapter.this.annotationRetention, Adapter.this.implementationContextFactory, Adapter.this.methodGraphCompiler, Adapter.this.typeValidation, Adapter.this.ignoredMethods);
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional.Valuable.AbstractBase.Adapter
                    protected FieldDefinition.Optional<U> materialize(FieldAttributeAppender.Factory factory, Transformer<FieldDescription> transformer, Object obj) {
                        return new FieldMatchAdapter(factory, transformer, obj, this.matcher);
                    }

                    private Adapter<?> getOuter() {
                        return Adapter.this;
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional.Valuable.AbstractBase.Adapter
                    public boolean equals(Object obj) {
                        if (this != obj) {
                            if (obj != null && getClass() == obj.getClass() && super.equals(obj)) {
                                FieldMatchAdapter fieldMatchAdapter = (FieldMatchAdapter) obj;
                                if (!getOuter().equals(fieldMatchAdapter.getOuter()) || !this.matcher.equals(fieldMatchAdapter.matcher)) {
                                }
                            }
                            return false;
                        }
                        return true;
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional.Valuable.AbstractBase.Adapter
                    public int hashCode() {
                        return (((super.hashCode() * 31) + getOuter().hashCode()) * 31) + this.matcher.hashCode();
                    }
                }

                protected class MethodDefinitionAdapter extends MethodDefinition.ParameterDefinition.Initial.AbstractBase<U> {
                    private final MethodDescription.Token token;

                    protected MethodDefinitionAdapter(MethodDescription.Token token) {
                        this.token = token;
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition
                    public MethodDefinition.ParameterDefinition.Annotatable<U> withParameter(TypeDefinition typeDefinition, String str, int i) {
                        return new ParameterAnnotationAdapter(new ParameterDescription.Token(typeDefinition.asGenericType(), str, Integer.valueOf(i)));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Simple
                    public MethodDefinition.ParameterDefinition.Simple.Annotatable<U> withParameter(TypeDefinition typeDefinition) {
                        return new SimpleParameterAnnotationAdapter(new ParameterDescription.Token(typeDefinition.asGenericType()));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ExceptionDefinition
                    public MethodDefinition.ExceptionDefinition<U> throwing(Collection<? extends TypeDefinition> collection) {
                        return new MethodDefinitionAdapter(new MethodDescription.Token(this.token.getName(), this.token.getModifiers(), this.token.getTypeVariableTokens(), this.token.getReturnType(), this.token.getParameterTokens(), CompoundList.of((List) this.token.getExceptionTypes(), (List) new TypeList.Generic.Explicit(new ArrayList(collection))), this.token.getAnnotations(), this.token.getDefaultValue(), this.token.getReceiverType()));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.TypeVariableDefinition
                    public MethodDefinition.TypeVariableDefinition.Annotatable<U> typeVariable(String str, Collection<? extends TypeDefinition> collection) {
                        return new TypeVariableAnnotationAdapter(new TypeVariableToken(str, new TypeList.Generic.Explicit(new ArrayList(collection))));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                    public MethodDefinition.ReceiverTypeDefinition<U> intercept(Implementation implementation) {
                        return materialize(new MethodRegistry.Handler.ForImplementation(implementation));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                    public MethodDefinition.ReceiverTypeDefinition<U> withoutCode() {
                        return new MethodDefinitionAdapter(new MethodDescription.Token(this.token.getName(), ModifierContributor.Resolver.of(MethodManifestation.ABSTRACT).resolve(this.token.getModifiers()), this.token.getTypeVariableTokens(), this.token.getReturnType(), this.token.getParameterTokens(), this.token.getExceptionTypes(), this.token.getAnnotations(), this.token.getDefaultValue(), this.token.getReceiverType())).materialize(MethodRegistry.Handler.ForAbstractMethod.INSTANCE);
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                    public MethodDefinition.ReceiverTypeDefinition<U> defaultValue(AnnotationValue<?, ?> annotationValue) {
                        return new MethodDefinitionAdapter(new MethodDescription.Token(this.token.getName(), ModifierContributor.Resolver.of(MethodManifestation.ABSTRACT).resolve(this.token.getModifiers()), this.token.getTypeVariableTokens(), this.token.getReturnType(), this.token.getParameterTokens(), this.token.getExceptionTypes(), this.token.getAnnotations(), annotationValue, this.token.getReceiverType())).materialize(new MethodRegistry.Handler.ForAnnotationValue(annotationValue));
                    }

                    private MethodDefinition.ReceiverTypeDefinition<U> materialize(MethodRegistry.Handler handler) {
                        return new AnnotationAdapter(this, handler);
                    }

                    private Adapter<?> getOuter() {
                        return Adapter.this;
                    }

                    public boolean equals(Object obj) {
                        if (this != obj) {
                            if (obj != null && getClass() == obj.getClass()) {
                                MethodDefinitionAdapter methodDefinitionAdapter = (MethodDefinitionAdapter) obj;
                                if (!this.token.equals(methodDefinitionAdapter.token) || !getOuter().equals(methodDefinitionAdapter.getOuter())) {
                                }
                            }
                            return false;
                        }
                        return true;
                    }

                    public int hashCode() {
                        return (getOuter().hashCode() * 31) + this.token.hashCode();
                    }

                    protected class TypeVariableAnnotationAdapter extends MethodDefinition.TypeVariableDefinition.Annotatable.AbstractBase.Adapter<U> {
                        private final TypeVariableToken token;

                        protected TypeVariableAnnotationAdapter(TypeVariableToken typeVariableToken) {
                            this.token = typeVariableToken;
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.TypeVariableDefinition.Annotatable.AbstractBase.Adapter
                        protected MethodDefinition.ParameterDefinition<U> materialize() {
                            return new MethodDefinitionAdapter(new MethodDescription.Token(MethodDefinitionAdapter.this.token.getName(), MethodDefinitionAdapter.this.token.getModifiers(), CompoundList.of(MethodDefinitionAdapter.this.token.getTypeVariableTokens(), this.token), MethodDefinitionAdapter.this.token.getReturnType(), MethodDefinitionAdapter.this.token.getParameterTokens(), MethodDefinitionAdapter.this.token.getExceptionTypes(), MethodDefinitionAdapter.this.token.getAnnotations(), MethodDefinitionAdapter.this.token.getDefaultValue(), MethodDefinitionAdapter.this.token.getReceiverType()));
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.TypeVariableDefinition.Annotatable
                        public MethodDefinition.TypeVariableDefinition.Annotatable<U> annotateTypeVariable(Collection<? extends AnnotationDescription> collection) {
                            return MethodDefinitionAdapter.this.new TypeVariableAnnotationAdapter(new TypeVariableToken(this.token.getSymbol(), this.token.getBounds(), CompoundList.of((List) this.token.getAnnotations(), (List) new ArrayList(collection))));
                        }

                        private Adapter<U>.MethodDefinitionAdapter getOuter() {
                            return MethodDefinitionAdapter.this;
                        }

                        public boolean equals(Object obj) {
                            if (this != obj) {
                                if (obj != null && getClass() == obj.getClass()) {
                                    TypeVariableAnnotationAdapter typeVariableAnnotationAdapter = (TypeVariableAnnotationAdapter) obj;
                                    if (!this.token.equals(typeVariableAnnotationAdapter.token) || !getOuter().equals(typeVariableAnnotationAdapter.getOuter())) {
                                    }
                                }
                                return false;
                            }
                            return true;
                        }

                        public int hashCode() {
                            return (getOuter().hashCode() * 31) + this.token.hashCode();
                        }
                    }

                    protected class ParameterAnnotationAdapter extends MethodDefinition.ParameterDefinition.Annotatable.AbstractBase.Adapter<U> {
                        private final ParameterDescription.Token token;

                        protected ParameterAnnotationAdapter(ParameterDescription.Token token) {
                            this.token = token;
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Annotatable
                        public MethodDefinition.ParameterDefinition.Annotatable<U> annotateParameter(Collection<? extends AnnotationDescription> collection) {
                            return MethodDefinitionAdapter.this.new ParameterAnnotationAdapter(new ParameterDescription.Token(this.token.getType(), CompoundList.of((List) this.token.getAnnotations(), (List) new ArrayList(collection)), this.token.getName(), this.token.getModifiers()));
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Annotatable.AbstractBase.Adapter
                        protected MethodDefinition.ParameterDefinition<U> materialize() {
                            return new MethodDefinitionAdapter(new MethodDescription.Token(MethodDefinitionAdapter.this.token.getName(), MethodDefinitionAdapter.this.token.getModifiers(), MethodDefinitionAdapter.this.token.getTypeVariableTokens(), MethodDefinitionAdapter.this.token.getReturnType(), CompoundList.of(MethodDefinitionAdapter.this.token.getParameterTokens(), this.token), MethodDefinitionAdapter.this.token.getExceptionTypes(), MethodDefinitionAdapter.this.token.getAnnotations(), MethodDefinitionAdapter.this.token.getDefaultValue(), MethodDefinitionAdapter.this.token.getReceiverType()));
                        }

                        private Adapter<U>.MethodDefinitionAdapter getOuter() {
                            return MethodDefinitionAdapter.this;
                        }

                        public boolean equals(Object obj) {
                            if (this != obj) {
                                if (obj != null && getClass() == obj.getClass()) {
                                    ParameterAnnotationAdapter parameterAnnotationAdapter = (ParameterAnnotationAdapter) obj;
                                    if (!this.token.equals(parameterAnnotationAdapter.token) || !getOuter().equals(parameterAnnotationAdapter.getOuter())) {
                                    }
                                }
                                return false;
                            }
                            return true;
                        }

                        public int hashCode() {
                            return (getOuter().hashCode() * 31) + this.token.hashCode();
                        }
                    }

                    protected class SimpleParameterAnnotationAdapter extends MethodDefinition.ParameterDefinition.Simple.Annotatable.AbstractBase.Adapter<U> {
                        private final ParameterDescription.Token token;

                        protected SimpleParameterAnnotationAdapter(ParameterDescription.Token token) {
                            this.token = token;
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Simple.Annotatable
                        public MethodDefinition.ParameterDefinition.Simple.Annotatable<U> annotateParameter(Collection<? extends AnnotationDescription> collection) {
                            return MethodDefinitionAdapter.this.new SimpleParameterAnnotationAdapter(new ParameterDescription.Token(this.token.getType(), CompoundList.of((List) this.token.getAnnotations(), (List) new ArrayList(collection)), this.token.getName(), this.token.getModifiers()));
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition.Simple.Annotatable.AbstractBase.Adapter
                        protected MethodDefinition.ParameterDefinition.Simple<U> materialize() {
                            return new MethodDefinitionAdapter(new MethodDescription.Token(MethodDefinitionAdapter.this.token.getName(), MethodDefinitionAdapter.this.token.getModifiers(), MethodDefinitionAdapter.this.token.getTypeVariableTokens(), MethodDefinitionAdapter.this.token.getReturnType(), CompoundList.of(MethodDefinitionAdapter.this.token.getParameterTokens(), this.token), MethodDefinitionAdapter.this.token.getExceptionTypes(), MethodDefinitionAdapter.this.token.getAnnotations(), MethodDefinitionAdapter.this.token.getDefaultValue(), MethodDefinitionAdapter.this.token.getReceiverType()));
                        }

                        private Adapter<U>.MethodDefinitionAdapter getOuter() {
                            return MethodDefinitionAdapter.this;
                        }

                        public boolean equals(Object obj) {
                            if (this != obj) {
                                if (obj != null && getClass() == obj.getClass()) {
                                    SimpleParameterAnnotationAdapter simpleParameterAnnotationAdapter = (SimpleParameterAnnotationAdapter) obj;
                                    if (!this.token.equals(simpleParameterAnnotationAdapter.token) || !getOuter().equals(simpleParameterAnnotationAdapter.getOuter())) {
                                    }
                                }
                                return false;
                            }
                            return true;
                        }

                        public int hashCode() {
                            return (getOuter().hashCode() * 31) + this.token.hashCode();
                        }
                    }

                    protected class AnnotationAdapter extends MethodDefinition.AbstractBase.Adapter<U> {
                        protected AnnotationAdapter(MethodDefinitionAdapter methodDefinitionAdapter, MethodRegistry.Handler handler) {
                            this(handler, MethodAttributeAppender.ForInstrumentedMethod.INCLUDING_RECEIVER, Transformer.NoOp.make());
                        }

                        protected AnnotationAdapter(MethodRegistry.Handler handler, MethodAttributeAppender.Factory factory, Transformer<MethodDescription> transformer) {
                            super(handler, factory, transformer);
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ReceiverTypeDefinition
                        public MethodDefinition<U> receiverType(TypeDescription.Generic generic) {
                            return new MethodDefinitionAdapter(new MethodDescription.Token(MethodDefinitionAdapter.this.token.getName(), MethodDefinitionAdapter.this.token.getModifiers(), MethodDefinitionAdapter.this.token.getTypeVariableTokens(), MethodDefinitionAdapter.this.token.getReturnType(), MethodDefinitionAdapter.this.token.getParameterTokens(), MethodDefinitionAdapter.this.token.getExceptionTypes(), MethodDefinitionAdapter.this.token.getAnnotations(), MethodDefinitionAdapter.this.token.getDefaultValue(), generic)).new AnnotationAdapter(this.handler, this.methodAttributeAppenderFactory, this.transformer);
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition
                        public MethodDefinition<U> annotateMethod(Collection<? extends AnnotationDescription> collection) {
                            return new MethodDefinitionAdapter(new MethodDescription.Token(MethodDefinitionAdapter.this.token.getName(), MethodDefinitionAdapter.this.token.getModifiers(), MethodDefinitionAdapter.this.token.getTypeVariableTokens(), MethodDefinitionAdapter.this.token.getReturnType(), MethodDefinitionAdapter.this.token.getParameterTokens(), MethodDefinitionAdapter.this.token.getExceptionTypes(), CompoundList.of((List) MethodDefinitionAdapter.this.token.getAnnotations(), (List) new ArrayList(collection)), MethodDefinitionAdapter.this.token.getDefaultValue(), MethodDefinitionAdapter.this.token.getReceiverType())).new AnnotationAdapter(this.handler, this.methodAttributeAppenderFactory, this.transformer);
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition
                        public MethodDefinition<U> annotateParameter(int i, Collection<? extends AnnotationDescription> collection) {
                            ArrayList arrayList = new ArrayList(MethodDefinitionAdapter.this.token.getParameterTokens());
                            arrayList.set(i, new ParameterDescription.Token(((ParameterDescription.Token) MethodDefinitionAdapter.this.token.getParameterTokens().get(i)).getType(), CompoundList.of((List) ((ParameterDescription.Token) MethodDefinitionAdapter.this.token.getParameterTokens().get(i)).getAnnotations(), (List) new ArrayList(collection)), ((ParameterDescription.Token) MethodDefinitionAdapter.this.token.getParameterTokens().get(i)).getName(), ((ParameterDescription.Token) MethodDefinitionAdapter.this.token.getParameterTokens().get(i)).getModifiers()));
                            return new MethodDefinitionAdapter(new MethodDescription.Token(MethodDefinitionAdapter.this.token.getName(), MethodDefinitionAdapter.this.token.getModifiers(), MethodDefinitionAdapter.this.token.getTypeVariableTokens(), MethodDefinitionAdapter.this.token.getReturnType(), arrayList, MethodDefinitionAdapter.this.token.getExceptionTypes(), MethodDefinitionAdapter.this.token.getAnnotations(), MethodDefinitionAdapter.this.token.getDefaultValue(), MethodDefinitionAdapter.this.token.getReceiverType())).new AnnotationAdapter(this.handler, this.methodAttributeAppenderFactory, this.transformer);
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.AbstractBase.Adapter
                        protected MethodDefinition<U> materialize(MethodRegistry.Handler handler, MethodAttributeAppender.Factory factory, Transformer<MethodDescription> transformer) {
                            return MethodDefinitionAdapter.this.new AnnotationAdapter(handler, factory, transformer);
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase.Delegator
                        protected Builder<U> materialize() {
                            return Adapter.this.materialize(Adapter.this.instrumentedType.withMethod(MethodDefinitionAdapter.this.token), Adapter.this.fieldRegistry, Adapter.this.methodRegistry.prepend(new LatentMatcher.ForMethodToken(MethodDefinitionAdapter.this.token), this.handler, this.methodAttributeAppenderFactory, this.transformer), Adapter.this.typeAttributeAppender, Adapter.this.asmVisitorWrapper, Adapter.this.classFileVersion, Adapter.this.auxiliaryTypeNamingStrategy, Adapter.this.annotationValueFilterFactory, Adapter.this.annotationRetention, Adapter.this.implementationContextFactory, Adapter.this.methodGraphCompiler, Adapter.this.typeValidation, Adapter.this.ignoredMethods);
                        }

                        private Adapter<U>.MethodDefinitionAdapter getOuter() {
                            return MethodDefinitionAdapter.this;
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.AbstractBase.Adapter
                        public boolean equals(Object obj) {
                            return this == obj || (obj != null && getClass() == obj.getClass() && super.equals(obj) && getOuter().equals(((AnnotationAdapter) obj).getOuter()));
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.AbstractBase.Adapter
                        public int hashCode() {
                            return super.hashCode() + getOuter().hashCode();
                        }
                    }
                }

                protected class MethodMatchAdapter extends MethodDefinition.ImplementationDefinition.AbstractBase<U> {
                    private final LatentMatcher<? super MethodDescription> matcher;

                    protected MethodMatchAdapter(LatentMatcher<? super MethodDescription> latentMatcher) {
                        this.matcher = latentMatcher;
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                    public MethodDefinition.ReceiverTypeDefinition<U> intercept(Implementation implementation) {
                        return materialize(new MethodRegistry.Handler.ForImplementation(implementation));
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                    public MethodDefinition.ReceiverTypeDefinition<U> withoutCode() {
                        return materialize(MethodRegistry.Handler.ForAbstractMethod.INSTANCE);
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                    public MethodDefinition.ReceiverTypeDefinition<U> defaultValue(AnnotationValue<?, ?> annotationValue) {
                        return materialize(new MethodRegistry.Handler.ForAnnotationValue(annotationValue));
                    }

                    private MethodDefinition.ReceiverTypeDefinition<U> materialize(MethodRegistry.Handler handler) {
                        return new AnnotationAdapter(this, handler);
                    }

                    private Adapter<?> getOuter() {
                        return Adapter.this;
                    }

                    public boolean equals(Object obj) {
                        if (this != obj) {
                            if (obj != null && getClass() == obj.getClass()) {
                                MethodMatchAdapter methodMatchAdapter = (MethodMatchAdapter) obj;
                                if (!this.matcher.equals(methodMatchAdapter.matcher) || !getOuter().equals(methodMatchAdapter.getOuter())) {
                                }
                            }
                            return false;
                        }
                        return true;
                    }

                    public int hashCode() {
                        return (getOuter().hashCode() * 31) + this.matcher.hashCode();
                    }

                    protected class AnnotationAdapter extends MethodDefinition.AbstractBase.Adapter<U> {
                        protected AnnotationAdapter(MethodMatchAdapter methodMatchAdapter, MethodRegistry.Handler handler) {
                            this(handler, MethodAttributeAppender.NoOp.INSTANCE, Transformer.NoOp.make());
                        }

                        protected AnnotationAdapter(MethodRegistry.Handler handler, MethodAttributeAppender.Factory factory, Transformer<MethodDescription> transformer) {
                            super(handler, factory, transformer);
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ReceiverTypeDefinition
                        public MethodDefinition<U> receiverType(TypeDescription.Generic generic) {
                            return MethodMatchAdapter.this.new AnnotationAdapter(this.handler, new MethodAttributeAppender.Factory.Compound(this.methodAttributeAppenderFactory, new MethodAttributeAppender.ForReceiverType(generic)), this.transformer);
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition
                        public MethodDefinition<U> annotateMethod(Collection<? extends AnnotationDescription> collection) {
                            return MethodMatchAdapter.this.new AnnotationAdapter(this.handler, new MethodAttributeAppender.Factory.Compound(this.methodAttributeAppenderFactory, new MethodAttributeAppender.Explicit(new ArrayList(collection))), this.transformer);
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition
                        public MethodDefinition<U> annotateParameter(int i, Collection<? extends AnnotationDescription> collection) {
                            return MethodMatchAdapter.this.new AnnotationAdapter(this.handler, new MethodAttributeAppender.Factory.Compound(this.methodAttributeAppenderFactory, new MethodAttributeAppender.Explicit(i, new ArrayList(collection))), this.transformer);
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.AbstractBase.Adapter
                        protected MethodDefinition<U> materialize(MethodRegistry.Handler handler, MethodAttributeAppender.Factory factory, Transformer<MethodDescription> transformer) {
                            return MethodMatchAdapter.this.new AnnotationAdapter(handler, factory, transformer);
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase.Delegator
                        protected Builder<U> materialize() {
                            return Adapter.this.materialize(Adapter.this.instrumentedType, Adapter.this.fieldRegistry, Adapter.this.methodRegistry.prepend(MethodMatchAdapter.this.matcher, this.handler, this.methodAttributeAppenderFactory, this.transformer), Adapter.this.typeAttributeAppender, Adapter.this.asmVisitorWrapper, Adapter.this.classFileVersion, Adapter.this.auxiliaryTypeNamingStrategy, Adapter.this.annotationValueFilterFactory, Adapter.this.annotationRetention, Adapter.this.implementationContextFactory, Adapter.this.methodGraphCompiler, Adapter.this.typeValidation, Adapter.this.ignoredMethods);
                        }

                        private Adapter<U>.MethodMatchAdapter getOuter() {
                            return MethodMatchAdapter.this;
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.AbstractBase.Adapter
                        public boolean equals(Object obj) {
                            return this == obj || (obj != null && getClass() == obj.getClass() && super.equals(obj) && getOuter().equals(((AnnotationAdapter) obj).getOuter()));
                        }

                        @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.AbstractBase.Adapter
                        public int hashCode() {
                            return super.hashCode() + getOuter().hashCode();
                        }
                    }
                }

                protected class OptionalMethodMatchAdapter extends Delegator<U> implements MethodDefinition.ImplementationDefinition.Optional<U> {
                    private final TypeList.Generic interfaces;

                    protected OptionalMethodMatchAdapter(TypeList.Generic generic) {
                        this.interfaces = generic;
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.AbstractBase.Delegator
                    protected Builder<U> materialize() {
                        Adapter adapter = Adapter.this;
                        return adapter.materialize(adapter.instrumentedType.withInterfaces(this.interfaces), Adapter.this.fieldRegistry, Adapter.this.methodRegistry, Adapter.this.typeAttributeAppender, Adapter.this.asmVisitorWrapper, Adapter.this.classFileVersion, Adapter.this.auxiliaryTypeNamingStrategy, Adapter.this.annotationValueFilterFactory, Adapter.this.annotationRetention, Adapter.this.implementationContextFactory, Adapter.this.methodGraphCompiler, Adapter.this.typeValidation, Adapter.this.ignoredMethods);
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                    public MethodDefinition.ReceiverTypeDefinition<U> intercept(Implementation implementation) {
                        return interfaceType().intercept(implementation);
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                    public MethodDefinition.ReceiverTypeDefinition<U> withoutCode() {
                        return interfaceType().withoutCode();
                    }

                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                    public MethodDefinition.ReceiverTypeDefinition<U> defaultValue(AnnotationValue<?, ?> annotationValue) {
                        return interfaceType().defaultValue(annotationValue);
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition
                    public <V> MethodDefinition.ReceiverTypeDefinition<U> defaultValue(V v, Class<? extends V> cls) {
                        return interfaceType().defaultValue(v, cls);
                    }

                    private MethodDefinition.ImplementationDefinition<U> interfaceType() {
                        ElementMatcher.Junction junctionNone = ElementMatchers.none();
                        Iterator it = this.interfaces.asErasures().iterator();
                        while (it.hasNext()) {
                            junctionNone = junctionNone.or(ElementMatchers.isSuperTypeOf((TypeDescription) it.next()));
                        }
                        return materialize().invokable(ElementMatchers.isDeclaredBy(ElementMatchers.isInterface().and(junctionNone)));
                    }

                    private Adapter<U> getOuter() {
                        return Adapter.this;
                    }

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        OptionalMethodMatchAdapter optionalMethodMatchAdapter = (OptionalMethodMatchAdapter) obj;
                        return this.interfaces.equals(optionalMethodMatchAdapter.interfaces) && getOuter().equals(optionalMethodMatchAdapter.getOuter());
                    }

                    public int hashCode() {
                        return (getOuter().hashCode() * 31) + this.interfaces.hashCode();
                    }
                }
            }
        }
    }

    public static class Default implements DynamicType {
        private static final int BUFFER_SIZE = 1024;
        private static final String CLASS_FILE_EXTENSION = ".class";
        private static final int END_OF_FILE = -1;
        private static final int FROM_BEGINNING = 0;
        private static final String MANIFEST_VERSION = "1.0";
        private static final String TEMP_SUFFIX = "tmp";
        protected final List<? extends DynamicType> auxiliaryTypes;
        protected final byte[] binaryRepresentation;
        protected final LoadedTypeInitializer loadedTypeInitializer;
        protected final TypeDescription typeDescription;

        public Default(TypeDescription typeDescription, byte[] bArr, LoadedTypeInitializer loadedTypeInitializer, List<? extends DynamicType> list) {
            this.typeDescription = typeDescription;
            this.binaryRepresentation = bArr;
            this.loadedTypeInitializer = loadedTypeInitializer;
            this.auxiliaryTypes = list;
        }

        @Override // net.bytebuddy.dynamic.DynamicType
        public TypeDescription getTypeDescription() {
            return this.typeDescription;
        }

        @Override // net.bytebuddy.dynamic.DynamicType
        public Map<TypeDescription, byte[]> getAllTypes() {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put(this.typeDescription, this.binaryRepresentation);
            Iterator<? extends DynamicType> it = this.auxiliaryTypes.iterator();
            while (it.hasNext()) {
                linkedHashMap.putAll(it.next().getAllTypes());
            }
            return linkedHashMap;
        }

        @Override // net.bytebuddy.dynamic.DynamicType
        public Map<TypeDescription, LoadedTypeInitializer> getLoadedTypeInitializers() {
            HashMap map = new HashMap();
            Iterator<? extends DynamicType> it = this.auxiliaryTypes.iterator();
            while (it.hasNext()) {
                map.putAll(it.next().getLoadedTypeInitializers());
            }
            map.put(this.typeDescription, this.loadedTypeInitializer);
            return map;
        }

        @Override // net.bytebuddy.dynamic.DynamicType
        public boolean hasAliveLoadedTypeInitializers() {
            Iterator<LoadedTypeInitializer> it = getLoadedTypeInitializers().values().iterator();
            while (it.hasNext()) {
                if (it.next().isAlive()) {
                    return true;
                }
            }
            return false;
        }

        @Override // net.bytebuddy.dynamic.DynamicType
        public byte[] getBytes() {
            return this.binaryRepresentation;
        }

        @Override // net.bytebuddy.dynamic.DynamicType
        public Map<TypeDescription, byte[]> getAuxiliaryTypes() {
            HashMap map = new HashMap();
            for (DynamicType dynamicType : this.auxiliaryTypes) {
                map.put(dynamicType.getTypeDescription(), dynamicType.getBytes());
                map.putAll(dynamicType.getAuxiliaryTypes());
            }
            return map;
        }

        @Override // net.bytebuddy.dynamic.DynamicType
        public Map<TypeDescription, File> saveIn(File file) throws IOException {
            HashMap map = new HashMap();
            File file2 = new File(file, this.typeDescription.getName().replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, File.separatorChar) + ".class");
            if (file2.getParentFile() != null && !file2.getParentFile().isDirectory() && !file2.getParentFile().mkdirs()) {
                throw new IllegalArgumentException("Could not create directory: " + file2.getParentFile());
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            try {
                fileOutputStream.write(this.binaryRepresentation);
                fileOutputStream.close();
                map.put(this.typeDescription, file2);
                Iterator<? extends DynamicType> it = this.auxiliaryTypes.iterator();
                while (it.hasNext()) {
                    map.putAll(it.next().saveIn(file));
                }
                return map;
            } catch (Throwable th) {
                fileOutputStream.close();
                throw th;
            }
        }

        @Override // net.bytebuddy.dynamic.DynamicType
        public File inject(File file, File file2) throws IOException {
            JarInputStream jarInputStream = new JarInputStream(new BufferedInputStream(new FileInputStream(file)));
            try {
                if (!file2.isFile() && !file2.createNewFile()) {
                    throw new IllegalArgumentException("Could not create file: " + file2);
                }
                Manifest manifest = jarInputStream.getManifest();
                JarOutputStream jarOutputStream = manifest == null ? new JarOutputStream(new FileOutputStream(file2)) : new JarOutputStream(new FileOutputStream(file2), manifest);
                try {
                    Map<TypeDescription, byte[]> auxiliaryTypes = getAuxiliaryTypes();
                    HashMap map = new HashMap();
                    for (Map.Entry<TypeDescription, byte[]> entry : auxiliaryTypes.entrySet()) {
                        map.put(entry.getKey().getInternalName() + ".class", entry.getValue());
                    }
                    map.put(this.typeDescription.getInternalName() + ".class", this.binaryRepresentation);
                    while (true) {
                        JarEntry nextJarEntry = jarInputStream.getNextJarEntry();
                        if (nextJarEntry == null) {
                            break;
                        }
                        jarOutputStream.putNextEntry(nextJarEntry);
                        byte[] bArr = (byte[]) map.remove(nextJarEntry.getName());
                        if (bArr == null) {
                            byte[] bArr2 = new byte[1024];
                            while (true) {
                                int i = jarInputStream.read(bArr2);
                                if (i != -1) {
                                    jarOutputStream.write(bArr2, 0, i);
                                }
                            }
                        } else {
                            jarOutputStream.write(bArr);
                        }
                        jarInputStream.closeEntry();
                        jarOutputStream.closeEntry();
                    }
                    for (Map.Entry entry2 : map.entrySet()) {
                        jarOutputStream.putNextEntry(new JarEntry((String) entry2.getKey()));
                        jarOutputStream.write((byte[]) entry2.getValue());
                        jarOutputStream.closeEntry();
                    }
                    return file2;
                } finally {
                    jarOutputStream.close();
                }
            } finally {
                jarInputStream.close();
            }
        }

        @Override // net.bytebuddy.dynamic.DynamicType
        public File inject(File file) throws IOException {
            File fileInject = inject(file, File.createTempFile(file.getName(), TEMP_SUFFIX));
            try {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(fileInject));
                try {
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int i = bufferedInputStream.read(bArr);
                            if (i == -1) {
                                break;
                            }
                            bufferedOutputStream.write(bArr, 0, i);
                        }
                        return file;
                    } finally {
                        bufferedOutputStream.close();
                    }
                } finally {
                    bufferedInputStream.close();
                }
            } finally {
                if (!fileInject.delete()) {
                    fileInject.deleteOnExit();
                }
            }
        }

        @Override // net.bytebuddy.dynamic.DynamicType
        public File toJar(File file) throws IOException {
            Manifest manifest = new Manifest();
            manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
            return toJar(file, manifest);
        }

        @Override // net.bytebuddy.dynamic.DynamicType
        public File toJar(File file, Manifest manifest) throws IOException {
            if (!file.isFile() && !file.createNewFile()) {
                throw new IllegalArgumentException("Could not create file: " + file);
            }
            JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(file), manifest);
            try {
                for (Map.Entry<TypeDescription, byte[]> entry : getAuxiliaryTypes().entrySet()) {
                    jarOutputStream.putNextEntry(new JarEntry(entry.getKey().getInternalName() + ".class"));
                    jarOutputStream.write(entry.getValue());
                    jarOutputStream.closeEntry();
                }
                jarOutputStream.putNextEntry(new JarEntry(this.typeDescription.getInternalName() + ".class"));
                jarOutputStream.write(this.binaryRepresentation);
                jarOutputStream.closeEntry();
                return file;
            } finally {
                jarOutputStream.close();
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Default r5 = (Default) obj;
            return this.auxiliaryTypes.equals(r5.auxiliaryTypes) && Arrays.equals(this.binaryRepresentation, r5.binaryRepresentation) && this.typeDescription.equals(r5.typeDescription) && this.loadedTypeInitializer.equals(r5.loadedTypeInitializer);
        }

        public int hashCode() {
            return (((((this.typeDescription.hashCode() * 31) + Arrays.hashCode(this.binaryRepresentation)) * 31) + this.loadedTypeInitializer.hashCode()) * 31) + this.auxiliaryTypes.hashCode();
        }

        public static class Unloaded<T> extends Default implements Unloaded<T> {
            private final TypeResolutionStrategy.Resolved typeResolutionStrategy;

            public Unloaded(TypeDescription typeDescription, byte[] bArr, LoadedTypeInitializer loadedTypeInitializer, List<? extends DynamicType> list, TypeResolutionStrategy.Resolved resolved) {
                super(typeDescription, bArr, loadedTypeInitializer, list);
                this.typeResolutionStrategy = resolved;
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Unloaded
            public Loaded<T> load(ClassLoader classLoader) {
                if (classLoader == null) {
                    return load(ClassLoadingStrategy.BOOTSTRAP_LOADER, ClassLoadingStrategy.Default.WRAPPER);
                }
                if (classLoader instanceof InjectionClassLoader) {
                    return load((InjectionClassLoader) classLoader, InjectionClassLoader.Strategy.INSTANCE);
                }
                return load(classLoader, ClassLoadingStrategy.Default.INJECTION);
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Unloaded
            public <S extends ClassLoader> Loaded<T> load(S s, ClassLoadingStrategy<? super S> classLoadingStrategy) {
                return new Loaded(this.typeDescription, this.binaryRepresentation, this.loadedTypeInitializer, this.auxiliaryTypes, this.typeResolutionStrategy.initialize(this, s, classLoadingStrategy));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Unloaded
            public Unloaded<T> include(DynamicType... dynamicTypeArr) {
                return include(Arrays.asList(dynamicTypeArr));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Unloaded
            public Unloaded<T> include(List<? extends DynamicType> list) {
                return new Unloaded(this.typeDescription, this.binaryRepresentation, this.loadedTypeInitializer, CompoundList.of((List) this.auxiliaryTypes, (List) list), this.typeResolutionStrategy);
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Default
            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj != null && getClass() == obj.getClass() && super.equals(obj)) {
                    return this.typeResolutionStrategy.equals(((Unloaded) obj).typeResolutionStrategy);
                }
                return false;
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Default
            public int hashCode() {
                return (super.hashCode() * 31) + this.typeResolutionStrategy.hashCode();
            }
        }

        protected static class Loaded<T> extends Default implements Loaded<T> {
            private final Map<TypeDescription, Class<?>> loadedTypes;

            protected Loaded(TypeDescription typeDescription, byte[] bArr, LoadedTypeInitializer loadedTypeInitializer, List<? extends DynamicType> list, Map<TypeDescription, Class<?>> map) {
                super(typeDescription, bArr, loadedTypeInitializer, list);
                this.loadedTypes = map;
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Loaded
            public Class<? extends T> getLoaded() {
                return (Class) this.loadedTypes.get(this.typeDescription);
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Loaded
            public Map<TypeDescription, Class<?>> getLoadedAuxiliaryTypes() {
                HashMap map = new HashMap(this.loadedTypes);
                map.remove(this.typeDescription);
                return map;
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Default
            public boolean equals(Object obj) {
                return this == obj || (obj != null && getClass() == obj.getClass() && super.equals(obj) && this.loadedTypes.equals(((Loaded) obj).loadedTypes));
            }

            @Override // net.bytebuddy.dynamic.DynamicType.Default
            public int hashCode() {
                return (super.hashCode() * 31) + this.loadedTypes.hashCode();
            }
        }
    }
}
