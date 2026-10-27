package net.bytebuddy.implementation;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.scaffold.FieldLocator;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.LoadedTypeInitializer;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.Duplication;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.TypeCreation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.CompoundList;
import net.bytebuddy.utility.RandomString;

/* JADX INFO: loaded from: classes3.dex */
public class MethodDelegation implements Implementation.Composable {
    private final MethodDelegationBinder.AmbiguityResolver ambiguityResolver;
    private final Assigner assigner;
    private final MethodDelegationBinder.BindingResolver bindingResolver;
    private final ImplementationDelegate implementationDelegate;
    private final List<TargetMethodAnnotationDrivenBinder.ParameterBinder<?>> parameterBinders;
    private final MethodDelegationBinder.TerminationHandler terminationHandler;

    protected boolean canEqual(Object obj) {
        return obj instanceof MethodDelegation;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MethodDelegation)) {
            return false;
        }
        MethodDelegation methodDelegation = (MethodDelegation) obj;
        if (!methodDelegation.canEqual(this)) {
            return false;
        }
        ImplementationDelegate implementationDelegate = this.implementationDelegate;
        ImplementationDelegate implementationDelegate2 = methodDelegation.implementationDelegate;
        if (implementationDelegate != null ? !implementationDelegate.equals(implementationDelegate2) : implementationDelegate2 != null) {
            return false;
        }
        List<TargetMethodAnnotationDrivenBinder.ParameterBinder<?>> list = this.parameterBinders;
        List<TargetMethodAnnotationDrivenBinder.ParameterBinder<?>> list2 = methodDelegation.parameterBinders;
        if (list != null ? !list.equals(list2) : list2 != null) {
            return false;
        }
        MethodDelegationBinder.AmbiguityResolver ambiguityResolver = this.ambiguityResolver;
        MethodDelegationBinder.AmbiguityResolver ambiguityResolver2 = methodDelegation.ambiguityResolver;
        if (ambiguityResolver != null ? !ambiguityResolver.equals(ambiguityResolver2) : ambiguityResolver2 != null) {
            return false;
        }
        MethodDelegationBinder.TerminationHandler terminationHandler = this.terminationHandler;
        MethodDelegationBinder.TerminationHandler terminationHandler2 = methodDelegation.terminationHandler;
        if (terminationHandler != null ? !terminationHandler.equals(terminationHandler2) : terminationHandler2 != null) {
            return false;
        }
        MethodDelegationBinder.BindingResolver bindingResolver = this.bindingResolver;
        MethodDelegationBinder.BindingResolver bindingResolver2 = methodDelegation.bindingResolver;
        if (bindingResolver != null ? !bindingResolver.equals(bindingResolver2) : bindingResolver2 != null) {
            return false;
        }
        Assigner assigner = this.assigner;
        Assigner assigner2 = methodDelegation.assigner;
        return assigner != null ? assigner.equals(assigner2) : assigner2 == null;
    }

    public int hashCode() {
        ImplementationDelegate implementationDelegate = this.implementationDelegate;
        int iHashCode = implementationDelegate == null ? 43 : implementationDelegate.hashCode();
        List<TargetMethodAnnotationDrivenBinder.ParameterBinder<?>> list = this.parameterBinders;
        int iHashCode2 = ((iHashCode + 59) * 59) + (list == null ? 43 : list.hashCode());
        MethodDelegationBinder.AmbiguityResolver ambiguityResolver = this.ambiguityResolver;
        int iHashCode3 = (iHashCode2 * 59) + (ambiguityResolver == null ? 43 : ambiguityResolver.hashCode());
        MethodDelegationBinder.TerminationHandler terminationHandler = this.terminationHandler;
        int iHashCode4 = (iHashCode3 * 59) + (terminationHandler == null ? 43 : terminationHandler.hashCode());
        MethodDelegationBinder.BindingResolver bindingResolver = this.bindingResolver;
        int iHashCode5 = (iHashCode4 * 59) + (bindingResolver == null ? 43 : bindingResolver.hashCode());
        Assigner assigner = this.assigner;
        return (iHashCode5 * 59) + (assigner != null ? assigner.hashCode() : 43);
    }

    protected MethodDelegation(ImplementationDelegate implementationDelegate, List<TargetMethodAnnotationDrivenBinder.ParameterBinder<?>> list, MethodDelegationBinder.AmbiguityResolver ambiguityResolver, MethodDelegationBinder.BindingResolver bindingResolver) {
        this(implementationDelegate, list, ambiguityResolver, MethodDelegationBinder.TerminationHandler.Default.RETURNING, bindingResolver, Assigner.DEFAULT);
    }

    private MethodDelegation(ImplementationDelegate implementationDelegate, List<TargetMethodAnnotationDrivenBinder.ParameterBinder<?>> list, MethodDelegationBinder.AmbiguityResolver ambiguityResolver, MethodDelegationBinder.TerminationHandler terminationHandler, MethodDelegationBinder.BindingResolver bindingResolver, Assigner assigner) {
        this.implementationDelegate = implementationDelegate;
        this.parameterBinders = list;
        this.terminationHandler = terminationHandler;
        this.ambiguityResolver = ambiguityResolver;
        this.bindingResolver = bindingResolver;
        this.assigner = assigner;
    }

    public static MethodDelegation to(Class<?> cls) {
        return withDefaultConfiguration().to(cls);
    }

    public static MethodDelegation to(TypeDescription typeDescription) {
        return withDefaultConfiguration().to(typeDescription);
    }

    public static MethodDelegation to(Object obj) {
        return withDefaultConfiguration().to(obj);
    }

    public static MethodDelegation to(Object obj, MethodGraph.Compiler compiler) {
        return withDefaultConfiguration().to(obj, compiler);
    }

    public static MethodDelegation to(Object obj, String str) {
        return withDefaultConfiguration().to(obj, str);
    }

    public static MethodDelegation to(Object obj, String str, MethodGraph.Compiler compiler) {
        return withDefaultConfiguration().to(obj, str, compiler);
    }

    public static MethodDelegation to(Object obj, Type type) {
        return withDefaultConfiguration().to(obj, type);
    }

    public static MethodDelegation to(Object obj, Type type, MethodGraph.Compiler compiler) {
        return withDefaultConfiguration().to(obj, type, compiler);
    }

    public static MethodDelegation to(Object obj, Type type, String str) {
        return withDefaultConfiguration().to(obj, type, str);
    }

    public static MethodDelegation to(Object obj, Type type, String str, MethodGraph.Compiler compiler) {
        return withDefaultConfiguration().to(obj, type, str, compiler);
    }

    public static MethodDelegation toConstructor(Class<?> cls) {
        return withDefaultConfiguration().toConstructor(cls);
    }

    public static MethodDelegation toConstructor(TypeDescription typeDescription) {
        return withDefaultConfiguration().toConstructor(typeDescription);
    }

    public static MethodDelegation toField(String str) {
        return withDefaultConfiguration().toField(str);
    }

    public static MethodDelegation toField(String str, FieldLocator.Factory factory) {
        return withDefaultConfiguration().toField(str, factory);
    }

    public static MethodDelegation toField(String str, MethodGraph.Compiler compiler) {
        return withDefaultConfiguration().toField(str, compiler);
    }

    public static MethodDelegation toField(String str, FieldLocator.Factory factory, MethodGraph.Compiler compiler) {
        return withDefaultConfiguration().toField(str, factory, compiler);
    }

    public static WithCustomProperties withDefaultConfiguration() {
        return new WithCustomProperties(MethodDelegationBinder.AmbiguityResolver.DEFAULT, TargetMethodAnnotationDrivenBinder.ParameterBinder.DEFAULTS);
    }

    public static WithCustomProperties withEmptyConfiguration() {
        return new WithCustomProperties(MethodDelegationBinder.AmbiguityResolver.NoOp.INSTANCE, Collections.emptyList());
    }

    public Implementation.Composable withAssigner(Assigner assigner) {
        return new MethodDelegation(this.implementationDelegate, this.parameterBinders, this.ambiguityResolver, this.terminationHandler, this.bindingResolver, assigner);
    }

    @Override // net.bytebuddy.implementation.Implementation.Composable
    public Implementation andThen(Implementation implementation) {
        return new Implementation.Compound(new MethodDelegation(this.implementationDelegate, this.parameterBinders, this.ambiguityResolver, MethodDelegationBinder.TerminationHandler.Default.DROPPING, this.bindingResolver, this.assigner), implementation);
    }

    @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
    public InstrumentedType prepare(InstrumentedType instrumentedType) {
        return this.implementationDelegate.prepare(instrumentedType);
    }

    @Override // net.bytebuddy.implementation.Implementation
    public ByteCodeAppender appender(Implementation.Target target) {
        ImplementationDelegate.Compiled compiledCompile = this.implementationDelegate.compile(target.getInstrumentedType());
        return new Appender(target, new MethodDelegationBinder.Processor(compiledCompile.getRecords(), this.ambiguityResolver, this.bindingResolver), this.terminationHandler, this.assigner, compiledCompile);
    }

    protected interface ImplementationDelegate extends InstrumentedType.Prepareable {
        public static final String FIELD_NAME_PREFIX = "delegate";

        Compiled compile(TypeDescription typeDescription);

        public interface Compiled {
            List<MethodDelegationBinder.Record> getRecords();

            MethodDelegationBinder.MethodInvoker invoke();

            StackManipulation prepare(MethodDescription methodDescription);

            public static class ForStaticCall implements Compiled {
                private final List<MethodDelegationBinder.Record> records;

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForStaticCall;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForStaticCall)) {
                        return false;
                    }
                    ForStaticCall forStaticCall = (ForStaticCall) obj;
                    if (!forStaticCall.canEqual(this)) {
                        return false;
                    }
                    List<MethodDelegationBinder.Record> records = getRecords();
                    List<MethodDelegationBinder.Record> records2 = forStaticCall.getRecords();
                    return records != null ? records.equals(records2) : records2 == null;
                }

                public int hashCode() {
                    List<MethodDelegationBinder.Record> records = getRecords();
                    return 59 + (records == null ? 43 : records.hashCode());
                }

                protected ForStaticCall(List<MethodDelegationBinder.Record> list) {
                    this.records = list;
                }

                @Override // net.bytebuddy.implementation.MethodDelegation.ImplementationDelegate.Compiled
                public StackManipulation prepare(MethodDescription methodDescription) {
                    return StackManipulation.Trivial.INSTANCE;
                }

                @Override // net.bytebuddy.implementation.MethodDelegation.ImplementationDelegate.Compiled
                public MethodDelegationBinder.MethodInvoker invoke() {
                    return MethodDelegationBinder.MethodInvoker.Simple.INSTANCE;
                }

                @Override // net.bytebuddy.implementation.MethodDelegation.ImplementationDelegate.Compiled
                public List<MethodDelegationBinder.Record> getRecords() {
                    return this.records;
                }
            }

            public static class ForField implements Compiled {
                private final FieldDescription fieldDescription;
                private final List<MethodDelegationBinder.Record> records;

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForField;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForField)) {
                        return false;
                    }
                    ForField forField = (ForField) obj;
                    if (!forField.canEqual(this)) {
                        return false;
                    }
                    FieldDescription fieldDescription = this.fieldDescription;
                    FieldDescription fieldDescription2 = forField.fieldDescription;
                    if (fieldDescription != null ? !fieldDescription.equals(fieldDescription2) : fieldDescription2 != null) {
                        return false;
                    }
                    List<MethodDelegationBinder.Record> records = getRecords();
                    List<MethodDelegationBinder.Record> records2 = forField.getRecords();
                    return records != null ? records.equals(records2) : records2 == null;
                }

                public int hashCode() {
                    FieldDescription fieldDescription = this.fieldDescription;
                    int iHashCode = fieldDescription == null ? 43 : fieldDescription.hashCode();
                    List<MethodDelegationBinder.Record> records = getRecords();
                    return ((iHashCode + 59) * 59) + (records != null ? records.hashCode() : 43);
                }

                protected ForField(FieldDescription fieldDescription, List<MethodDelegationBinder.Record> list) {
                    this.fieldDescription = fieldDescription;
                    this.records = list;
                }

                @Override // net.bytebuddy.implementation.MethodDelegation.ImplementationDelegate.Compiled
                public StackManipulation prepare(MethodDescription methodDescription) {
                    if (methodDescription.isStatic() && !this.fieldDescription.isStatic()) {
                        throw new IllegalStateException("Cannot read " + this.fieldDescription + " from " + methodDescription);
                    }
                    StackManipulation[] stackManipulationArr = new StackManipulation[2];
                    stackManipulationArr[0] = this.fieldDescription.isStatic() ? StackManipulation.Trivial.INSTANCE : MethodVariableAccess.loadThis();
                    stackManipulationArr[1] = FieldAccess.forField(this.fieldDescription).read();
                    return new StackManipulation.Compound(stackManipulationArr);
                }

                @Override // net.bytebuddy.implementation.MethodDelegation.ImplementationDelegate.Compiled
                public MethodDelegationBinder.MethodInvoker invoke() {
                    return new MethodDelegationBinder.MethodInvoker.Virtual(this.fieldDescription.getType().asErasure());
                }

                @Override // net.bytebuddy.implementation.MethodDelegation.ImplementationDelegate.Compiled
                public List<MethodDelegationBinder.Record> getRecords() {
                    return this.records;
                }
            }

            public static class ForConstruction implements Compiled {
                private final List<MethodDelegationBinder.Record> records;
                private final TypeDescription typeDescription;

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForConstruction;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForConstruction)) {
                        return false;
                    }
                    ForConstruction forConstruction = (ForConstruction) obj;
                    if (!forConstruction.canEqual(this)) {
                        return false;
                    }
                    TypeDescription typeDescription = this.typeDescription;
                    TypeDescription typeDescription2 = forConstruction.typeDescription;
                    if (typeDescription != null ? !typeDescription.equals(typeDescription2) : typeDescription2 != null) {
                        return false;
                    }
                    List<MethodDelegationBinder.Record> records = getRecords();
                    List<MethodDelegationBinder.Record> records2 = forConstruction.getRecords();
                    return records != null ? records.equals(records2) : records2 == null;
                }

                public int hashCode() {
                    TypeDescription typeDescription = this.typeDescription;
                    int iHashCode = typeDescription == null ? 43 : typeDescription.hashCode();
                    List<MethodDelegationBinder.Record> records = getRecords();
                    return ((iHashCode + 59) * 59) + (records != null ? records.hashCode() : 43);
                }

                protected ForConstruction(TypeDescription typeDescription, List<MethodDelegationBinder.Record> list) {
                    this.typeDescription = typeDescription;
                    this.records = list;
                }

                @Override // net.bytebuddy.implementation.MethodDelegation.ImplementationDelegate.Compiled
                public StackManipulation prepare(MethodDescription methodDescription) {
                    return new StackManipulation.Compound(TypeCreation.of(this.typeDescription), Duplication.SINGLE);
                }

                @Override // net.bytebuddy.implementation.MethodDelegation.ImplementationDelegate.Compiled
                public MethodDelegationBinder.MethodInvoker invoke() {
                    return MethodDelegationBinder.MethodInvoker.Simple.INSTANCE;
                }

                @Override // net.bytebuddy.implementation.MethodDelegation.ImplementationDelegate.Compiled
                public List<MethodDelegationBinder.Record> getRecords() {
                    return this.records;
                }
            }
        }

        public static class ForStaticMethod implements ImplementationDelegate {
            private final List<MethodDelegationBinder.Record> records;

            @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof ForStaticMethod;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForStaticMethod)) {
                    return false;
                }
                ForStaticMethod forStaticMethod = (ForStaticMethod) obj;
                if (!forStaticMethod.canEqual(this)) {
                    return false;
                }
                List<MethodDelegationBinder.Record> list = this.records;
                List<MethodDelegationBinder.Record> list2 = forStaticMethod.records;
                return list != null ? list.equals(list2) : list2 == null;
            }

            public int hashCode() {
                List<MethodDelegationBinder.Record> list = this.records;
                return 59 + (list == null ? 43 : list.hashCode());
            }

            protected ForStaticMethod(List<MethodDelegationBinder.Record> list) {
                this.records = list;
            }

            protected static ImplementationDelegate of(MethodList<?> methodList, MethodDelegationBinder methodDelegationBinder) {
                ArrayList arrayList = new ArrayList(methodList.size());
                Iterator it = methodList.iterator();
                while (it.hasNext()) {
                    arrayList.add(methodDelegationBinder.compile((MethodDescription) it.next()));
                }
                return new ForStaticMethod(arrayList);
            }

            @Override // net.bytebuddy.implementation.MethodDelegation.ImplementationDelegate
            public Compiled compile(TypeDescription typeDescription) {
                return new Compiled.ForStaticCall(this.records);
            }
        }

        public static abstract class ForField implements ImplementationDelegate {
            protected final String fieldName;
            protected final ElementMatcher<? super MethodDescription> matcher;
            protected final MethodGraph.Compiler methodGraphCompiler;
            protected final List<? extends TargetMethodAnnotationDrivenBinder.ParameterBinder<?>> parameterBinders;

            protected abstract FieldDescription resolve(TypeDescription typeDescription);

            protected boolean canEqual(Object obj) {
                return obj instanceof ForField;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForField)) {
                    return false;
                }
                ForField forField = (ForField) obj;
                if (!forField.canEqual(this)) {
                    return false;
                }
                String str = this.fieldName;
                String str2 = forField.fieldName;
                if (str != null ? !str.equals(str2) : str2 != null) {
                    return false;
                }
                MethodGraph.Compiler compiler = this.methodGraphCompiler;
                MethodGraph.Compiler compiler2 = forField.methodGraphCompiler;
                if (compiler != null ? !compiler.equals(compiler2) : compiler2 != null) {
                    return false;
                }
                List<? extends TargetMethodAnnotationDrivenBinder.ParameterBinder<?>> list = this.parameterBinders;
                List<? extends TargetMethodAnnotationDrivenBinder.ParameterBinder<?>> list2 = forField.parameterBinders;
                if (list != null ? !list.equals(list2) : list2 != null) {
                    return false;
                }
                ElementMatcher<? super MethodDescription> elementMatcher = this.matcher;
                ElementMatcher<? super MethodDescription> elementMatcher2 = forField.matcher;
                return elementMatcher != null ? elementMatcher.equals(elementMatcher2) : elementMatcher2 == null;
            }

            public int hashCode() {
                String str = this.fieldName;
                int iHashCode = str == null ? 43 : str.hashCode();
                MethodGraph.Compiler compiler = this.methodGraphCompiler;
                int iHashCode2 = ((iHashCode + 59) * 59) + (compiler == null ? 43 : compiler.hashCode());
                List<? extends TargetMethodAnnotationDrivenBinder.ParameterBinder<?>> list = this.parameterBinders;
                int iHashCode3 = (iHashCode2 * 59) + (list == null ? 43 : list.hashCode());
                ElementMatcher<? super MethodDescription> elementMatcher = this.matcher;
                return (iHashCode3 * 59) + (elementMatcher != null ? elementMatcher.hashCode() : 43);
            }

            protected ForField(String str, MethodGraph.Compiler compiler, List<? extends TargetMethodAnnotationDrivenBinder.ParameterBinder<?>> list, ElementMatcher<? super MethodDescription> elementMatcher) {
                this.fieldName = str;
                this.methodGraphCompiler = compiler;
                this.parameterBinders = list;
                this.matcher = elementMatcher;
            }

            @Override // net.bytebuddy.implementation.MethodDelegation.ImplementationDelegate
            public Compiled compile(TypeDescription typeDescription) {
                FieldDescription fieldDescriptionResolve = resolve(typeDescription);
                if (!fieldDescriptionResolve.getType().asErasure().isVisibleTo(typeDescription)) {
                    throw new IllegalStateException(fieldDescriptionResolve + " is not visible to " + typeDescription);
                }
                MethodList methodListFilter = this.methodGraphCompiler.compile(fieldDescriptionResolve.getType(), typeDescription).listNodes().asMethodList().filter(this.matcher);
                ArrayList arrayList = new ArrayList(methodListFilter.size());
                MethodDelegationBinder methodDelegationBinderOf = TargetMethodAnnotationDrivenBinder.of(this.parameterBinders);
                Iterator it = methodListFilter.iterator();
                while (it.hasNext()) {
                    arrayList.add(methodDelegationBinderOf.compile((MethodDescription) it.next()));
                }
                return new Compiled.ForField(fieldDescriptionResolve, arrayList);
            }

            protected static class WithInstance extends ForField {
                private final TypeDescription.Generic fieldType;
                private final Object target;

                @Override // net.bytebuddy.implementation.MethodDelegation.ImplementationDelegate.ForField
                protected boolean canEqual(Object obj) {
                    return obj instanceof WithInstance;
                }

                @Override // net.bytebuddy.implementation.MethodDelegation.ImplementationDelegate.ForField
                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof WithInstance)) {
                        return false;
                    }
                    WithInstance withInstance = (WithInstance) obj;
                    if (!withInstance.canEqual(this) || !super.equals(obj)) {
                        return false;
                    }
                    Object obj2 = this.target;
                    Object obj3 = withInstance.target;
                    if (obj2 != null ? !obj2.equals(obj3) : obj3 != null) {
                        return false;
                    }
                    TypeDescription.Generic generic = this.fieldType;
                    TypeDescription.Generic generic2 = withInstance.fieldType;
                    return generic != null ? generic.equals(generic2) : generic2 == null;
                }

                @Override // net.bytebuddy.implementation.MethodDelegation.ImplementationDelegate.ForField
                public int hashCode() {
                    int iHashCode = super.hashCode() + 59;
                    Object obj = this.target;
                    int iHashCode2 = (iHashCode * 59) + (obj == null ? 43 : obj.hashCode());
                    TypeDescription.Generic generic = this.fieldType;
                    return (iHashCode2 * 59) + (generic != null ? generic.hashCode() : 43);
                }

                protected WithInstance(String str, MethodGraph.Compiler compiler, List<? extends TargetMethodAnnotationDrivenBinder.ParameterBinder<?>> list, ElementMatcher<? super MethodDescription> elementMatcher, Object obj, TypeDescription.Generic generic) {
                    super(str, compiler, list, elementMatcher);
                    this.target = obj;
                    this.fieldType = generic;
                }

                @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType.withField(new FieldDescription.Token(this.fieldName, 4105, this.fieldType)).withInitializer(new LoadedTypeInitializer.ForStaticField(this.fieldName, this.target));
                }

                @Override // net.bytebuddy.implementation.MethodDelegation.ImplementationDelegate.ForField
                protected FieldDescription resolve(TypeDescription typeDescription) {
                    if (!this.fieldType.asErasure().isVisibleTo(typeDescription)) {
                        throw new IllegalStateException(this.fieldType + " is not visible to " + typeDescription);
                    }
                    return (FieldDescription) typeDescription.getDeclaredFields().filter(ElementMatchers.named(this.fieldName).and(ElementMatchers.fieldType(this.fieldType.asErasure()))).getOnly();
                }
            }

            protected static class WithLookup extends ForField {
                private final FieldLocator.Factory fieldLocatorFactory;

                @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                @Override // net.bytebuddy.implementation.MethodDelegation.ImplementationDelegate.ForField
                protected boolean canEqual(Object obj) {
                    return obj instanceof WithLookup;
                }

                @Override // net.bytebuddy.implementation.MethodDelegation.ImplementationDelegate.ForField
                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof WithLookup)) {
                        return false;
                    }
                    WithLookup withLookup = (WithLookup) obj;
                    if (!withLookup.canEqual(this) || !super.equals(obj)) {
                        return false;
                    }
                    FieldLocator.Factory factory = this.fieldLocatorFactory;
                    FieldLocator.Factory factory2 = withLookup.fieldLocatorFactory;
                    return factory != null ? factory.equals(factory2) : factory2 == null;
                }

                @Override // net.bytebuddy.implementation.MethodDelegation.ImplementationDelegate.ForField
                public int hashCode() {
                    int iHashCode = super.hashCode() + 59;
                    FieldLocator.Factory factory = this.fieldLocatorFactory;
                    return (iHashCode * 59) + (factory == null ? 43 : factory.hashCode());
                }

                protected WithLookup(String str, MethodGraph.Compiler compiler, List<? extends TargetMethodAnnotationDrivenBinder.ParameterBinder<?>> list, ElementMatcher<? super MethodDescription> elementMatcher, FieldLocator.Factory factory) {
                    super(str, compiler, list, elementMatcher);
                    this.fieldLocatorFactory = factory;
                }

                @Override // net.bytebuddy.implementation.MethodDelegation.ImplementationDelegate.ForField
                protected FieldDescription resolve(TypeDescription typeDescription) {
                    FieldLocator.Resolution resolutionLocate = this.fieldLocatorFactory.make(typeDescription).locate(this.fieldName);
                    if (!resolutionLocate.isResolved()) {
                        throw new IllegalStateException("Could not locate " + this.fieldName + " on " + typeDescription);
                    }
                    return resolutionLocate.getField();
                }
            }
        }

        public static class ForConstruction implements ImplementationDelegate {
            private final List<MethodDelegationBinder.Record> records;
            private final TypeDescription typeDescription;

            @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof ForConstruction;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForConstruction)) {
                    return false;
                }
                ForConstruction forConstruction = (ForConstruction) obj;
                if (!forConstruction.canEqual(this)) {
                    return false;
                }
                TypeDescription typeDescription = this.typeDescription;
                TypeDescription typeDescription2 = forConstruction.typeDescription;
                if (typeDescription != null ? !typeDescription.equals(typeDescription2) : typeDescription2 != null) {
                    return false;
                }
                List<MethodDelegationBinder.Record> list = this.records;
                List<MethodDelegationBinder.Record> list2 = forConstruction.records;
                return list != null ? list.equals(list2) : list2 == null;
            }

            public int hashCode() {
                TypeDescription typeDescription = this.typeDescription;
                int iHashCode = typeDescription == null ? 43 : typeDescription.hashCode();
                List<MethodDelegationBinder.Record> list = this.records;
                return ((iHashCode + 59) * 59) + (list != null ? list.hashCode() : 43);
            }

            protected ForConstruction(TypeDescription typeDescription, List<MethodDelegationBinder.Record> list) {
                this.typeDescription = typeDescription;
                this.records = list;
            }

            protected static ImplementationDelegate of(TypeDescription typeDescription, MethodList<?> methodList, MethodDelegationBinder methodDelegationBinder) {
                ArrayList arrayList = new ArrayList(methodList.size());
                Iterator it = methodList.iterator();
                while (it.hasNext()) {
                    arrayList.add(methodDelegationBinder.compile((MethodDescription) it.next()));
                }
                return new ForConstruction(typeDescription, arrayList);
            }

            @Override // net.bytebuddy.implementation.MethodDelegation.ImplementationDelegate
            public Compiled compile(TypeDescription typeDescription) {
                return new Compiled.ForConstruction(this.typeDescription, this.records);
            }
        }
    }

    protected static class Appender implements ByteCodeAppender {
        private final Assigner assigner;
        private final ImplementationDelegate.Compiled compiled;
        private final Implementation.Target implementationTarget;
        private final MethodDelegationBinder.Record processor;
        private final MethodDelegationBinder.TerminationHandler terminationHandler;

        protected boolean canEqual(Object obj) {
            return obj instanceof Appender;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Appender)) {
                return false;
            }
            Appender appender = (Appender) obj;
            if (!appender.canEqual(this)) {
                return false;
            }
            Implementation.Target target = this.implementationTarget;
            Implementation.Target target2 = appender.implementationTarget;
            if (target != null ? !target.equals(target2) : target2 != null) {
                return false;
            }
            MethodDelegationBinder.Record record = this.processor;
            MethodDelegationBinder.Record record2 = appender.processor;
            if (record != null ? !record.equals(record2) : record2 != null) {
                return false;
            }
            MethodDelegationBinder.TerminationHandler terminationHandler = this.terminationHandler;
            MethodDelegationBinder.TerminationHandler terminationHandler2 = appender.terminationHandler;
            if (terminationHandler != null ? !terminationHandler.equals(terminationHandler2) : terminationHandler2 != null) {
                return false;
            }
            Assigner assigner = this.assigner;
            Assigner assigner2 = appender.assigner;
            if (assigner != null ? !assigner.equals(assigner2) : assigner2 != null) {
                return false;
            }
            ImplementationDelegate.Compiled compiled = this.compiled;
            ImplementationDelegate.Compiled compiled2 = appender.compiled;
            return compiled != null ? compiled.equals(compiled2) : compiled2 == null;
        }

        public int hashCode() {
            Implementation.Target target = this.implementationTarget;
            int iHashCode = target == null ? 43 : target.hashCode();
            MethodDelegationBinder.Record record = this.processor;
            int iHashCode2 = ((iHashCode + 59) * 59) + (record == null ? 43 : record.hashCode());
            MethodDelegationBinder.TerminationHandler terminationHandler = this.terminationHandler;
            int iHashCode3 = (iHashCode2 * 59) + (terminationHandler == null ? 43 : terminationHandler.hashCode());
            Assigner assigner = this.assigner;
            int iHashCode4 = (iHashCode3 * 59) + (assigner == null ? 43 : assigner.hashCode());
            ImplementationDelegate.Compiled compiled = this.compiled;
            return (iHashCode4 * 59) + (compiled != null ? compiled.hashCode() : 43);
        }

        protected Appender(Implementation.Target target, MethodDelegationBinder.Record record, MethodDelegationBinder.TerminationHandler terminationHandler, Assigner assigner, ImplementationDelegate.Compiled compiled) {
            this.implementationTarget = target;
            this.processor = record;
            this.terminationHandler = terminationHandler;
            this.assigner = assigner;
            this.compiled = compiled;
        }

        @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            return new ByteCodeAppender.Size(new StackManipulation.Compound(this.compiled.prepare(methodDescription), this.processor.bind(this.implementationTarget, methodDescription, this.terminationHandler, this.compiled.invoke(), this.assigner)).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
        }
    }

    public static class WithCustomProperties {
        private final MethodDelegationBinder.AmbiguityResolver ambiguityResolver;
        private final MethodDelegationBinder.BindingResolver bindingResolver;
        private final ElementMatcher<? super MethodDescription> matcher;
        private final List<TargetMethodAnnotationDrivenBinder.ParameterBinder<?>> parameterBinders;

        protected boolean canEqual(Object obj) {
            return obj instanceof WithCustomProperties;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof WithCustomProperties)) {
                return false;
            }
            WithCustomProperties withCustomProperties = (WithCustomProperties) obj;
            if (!withCustomProperties.canEqual(this)) {
                return false;
            }
            MethodDelegationBinder.AmbiguityResolver ambiguityResolver = this.ambiguityResolver;
            MethodDelegationBinder.AmbiguityResolver ambiguityResolver2 = withCustomProperties.ambiguityResolver;
            if (ambiguityResolver != null ? !ambiguityResolver.equals(ambiguityResolver2) : ambiguityResolver2 != null) {
                return false;
            }
            List<TargetMethodAnnotationDrivenBinder.ParameterBinder<?>> list = this.parameterBinders;
            List<TargetMethodAnnotationDrivenBinder.ParameterBinder<?>> list2 = withCustomProperties.parameterBinders;
            if (list != null ? !list.equals(list2) : list2 != null) {
                return false;
            }
            MethodDelegationBinder.BindingResolver bindingResolver = this.bindingResolver;
            MethodDelegationBinder.BindingResolver bindingResolver2 = withCustomProperties.bindingResolver;
            if (bindingResolver != null ? !bindingResolver.equals(bindingResolver2) : bindingResolver2 != null) {
                return false;
            }
            ElementMatcher<? super MethodDescription> elementMatcher = this.matcher;
            ElementMatcher<? super MethodDescription> elementMatcher2 = withCustomProperties.matcher;
            return elementMatcher != null ? elementMatcher.equals(elementMatcher2) : elementMatcher2 == null;
        }

        public int hashCode() {
            MethodDelegationBinder.AmbiguityResolver ambiguityResolver = this.ambiguityResolver;
            int iHashCode = ambiguityResolver == null ? 43 : ambiguityResolver.hashCode();
            List<TargetMethodAnnotationDrivenBinder.ParameterBinder<?>> list = this.parameterBinders;
            int iHashCode2 = ((iHashCode + 59) * 59) + (list == null ? 43 : list.hashCode());
            MethodDelegationBinder.BindingResolver bindingResolver = this.bindingResolver;
            int iHashCode3 = (iHashCode2 * 59) + (bindingResolver == null ? 43 : bindingResolver.hashCode());
            ElementMatcher<? super MethodDescription> elementMatcher = this.matcher;
            return (iHashCode3 * 59) + (elementMatcher != null ? elementMatcher.hashCode() : 43);
        }

        protected WithCustomProperties(MethodDelegationBinder.AmbiguityResolver ambiguityResolver, List<TargetMethodAnnotationDrivenBinder.ParameterBinder<?>> list) {
            this(ambiguityResolver, list, MethodDelegationBinder.BindingResolver.Default.INSTANCE, ElementMatchers.any());
        }

        private WithCustomProperties(MethodDelegationBinder.AmbiguityResolver ambiguityResolver, List<TargetMethodAnnotationDrivenBinder.ParameterBinder<?>> list, MethodDelegationBinder.BindingResolver bindingResolver, ElementMatcher<? super MethodDescription> elementMatcher) {
            this.ambiguityResolver = ambiguityResolver;
            this.parameterBinders = list;
            this.bindingResolver = bindingResolver;
            this.matcher = elementMatcher;
        }

        public WithCustomProperties withResolvers(MethodDelegationBinder.AmbiguityResolver... ambiguityResolverArr) {
            return withResolvers(Arrays.asList(ambiguityResolverArr));
        }

        public WithCustomProperties withResolvers(List<? extends MethodDelegationBinder.AmbiguityResolver> list) {
            return new WithCustomProperties(new MethodDelegationBinder.AmbiguityResolver.Compound((List<? extends MethodDelegationBinder.AmbiguityResolver>) CompoundList.of(this.ambiguityResolver, list)), this.parameterBinders, this.bindingResolver, this.matcher);
        }

        public WithCustomProperties withBinders(TargetMethodAnnotationDrivenBinder.ParameterBinder<?>... parameterBinderArr) {
            return withBinders(Arrays.asList(parameterBinderArr));
        }

        public WithCustomProperties withBinders(List<? extends TargetMethodAnnotationDrivenBinder.ParameterBinder<?>> list) {
            return new WithCustomProperties(this.ambiguityResolver, CompoundList.of((List) this.parameterBinders, (List) list), this.bindingResolver, this.matcher);
        }

        public WithCustomProperties withBindingResolver(MethodDelegationBinder.BindingResolver bindingResolver) {
            return new WithCustomProperties(this.ambiguityResolver, this.parameterBinders, bindingResolver, this.matcher);
        }

        public WithCustomProperties filter(ElementMatcher<? super MethodDescription> elementMatcher) {
            return new WithCustomProperties(this.ambiguityResolver, this.parameterBinders, this.bindingResolver, new ElementMatcher.Junction.Conjunction(this.matcher, elementMatcher));
        }

        public MethodDelegation to(Class<?> cls) {
            return to((TypeDescription) new TypeDescription.ForLoadedType(cls));
        }

        public MethodDelegation to(TypeDescription typeDescription) {
            if (typeDescription.isArray()) {
                throw new IllegalArgumentException("Cannot delegate to array " + typeDescription);
            }
            if (typeDescription.isPrimitive()) {
                throw new IllegalArgumentException("Cannot delegate to primitive " + typeDescription);
            }
            return new MethodDelegation(ImplementationDelegate.ForStaticMethod.of(typeDescription.getDeclaredMethods().filter(ElementMatchers.isStatic().and(this.matcher)), TargetMethodAnnotationDrivenBinder.of(this.parameterBinders)), this.parameterBinders, this.ambiguityResolver, this.bindingResolver);
        }

        public MethodDelegation to(Object obj) {
            return to(obj, MethodGraph.Compiler.DEFAULT);
        }

        public MethodDelegation to(Object obj, MethodGraph.Compiler compiler) {
            return to(obj, obj.getClass(), compiler);
        }

        public MethodDelegation to(Object obj, String str) {
            return to(obj, str, MethodGraph.Compiler.DEFAULT);
        }

        public MethodDelegation to(Object obj, String str, MethodGraph.Compiler compiler) {
            return to(obj, obj.getClass(), str, compiler);
        }

        public MethodDelegation to(Object obj, Type type) {
            return to(obj, type, MethodGraph.Compiler.DEFAULT);
        }

        public MethodDelegation to(Object obj, Type type, MethodGraph.Compiler compiler) {
            return to(obj, type, String.format("%s$%s", ImplementationDelegate.FIELD_NAME_PREFIX, RandomString.hashOf(obj.hashCode())), compiler);
        }

        public MethodDelegation to(Object obj, Type type, String str) {
            return to(obj, type, str, MethodGraph.Compiler.DEFAULT);
        }

        public MethodDelegation to(Object obj, Type type, String str, MethodGraph.Compiler compiler) {
            TypeDescription.Generic genericDescribe = TypeDefinition.Sort.describe(type);
            if (!genericDescribe.asErasure().isInstance(obj)) {
                throw new IllegalArgumentException(obj + " is not an instance of " + type);
            }
            return new MethodDelegation(new ImplementationDelegate.ForField.WithInstance(str, compiler, this.parameterBinders, this.matcher, obj, genericDescribe), this.parameterBinders, this.ambiguityResolver, this.bindingResolver);
        }

        public MethodDelegation toConstructor(Class<?> cls) {
            return toConstructor(new TypeDescription.ForLoadedType(cls));
        }

        public MethodDelegation toConstructor(TypeDescription typeDescription) {
            return new MethodDelegation(ImplementationDelegate.ForConstruction.of(typeDescription, typeDescription.getDeclaredMethods().filter(ElementMatchers.isConstructor().and(this.matcher)), TargetMethodAnnotationDrivenBinder.of(this.parameterBinders)), this.parameterBinders, this.ambiguityResolver, this.bindingResolver);
        }

        public MethodDelegation toField(String str) {
            return toField(str, FieldLocator.ForClassHierarchy.Factory.INSTANCE);
        }

        public MethodDelegation toField(String str, FieldLocator.Factory factory) {
            return toField(str, factory, MethodGraph.Compiler.DEFAULT);
        }

        public MethodDelegation toField(String str, MethodGraph.Compiler compiler) {
            return toField(str, FieldLocator.ForClassHierarchy.Factory.INSTANCE, compiler);
        }

        public MethodDelegation toField(String str, FieldLocator.Factory factory, MethodGraph.Compiler compiler) {
            return new MethodDelegation(new ImplementationDelegate.ForField.WithLookup(str, compiler, this.parameterBinders, this.matcher, factory), this.parameterBinders, this.ambiguityResolver, this.bindingResolver);
        }
    }
}
