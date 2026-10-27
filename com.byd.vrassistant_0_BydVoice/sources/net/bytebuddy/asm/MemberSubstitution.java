package net.bytebuddy.asm;

import com.android.multidex.ClassPathElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.description.ByteCodeElement;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.Duplication;
import net.bytebuddy.implementation.bytecode.Removal;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.implementation.bytecode.constant.DefaultValue;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.CompoundList;

/* JADX INFO: loaded from: classes3.dex */
public class MemberSubstitution implements AsmVisitorWrapper.ForDeclaredMethods.MethodVisitorWrapper {
    private final MethodGraph.Compiler methodGraphCompiler;
    private final boolean strict;
    private final Substitution substitution;
    private final TypePoolResolver typePoolResolver;

    protected boolean canEqual(Object obj) {
        return obj instanceof MemberSubstitution;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MemberSubstitution)) {
            return false;
        }
        MemberSubstitution memberSubstitution = (MemberSubstitution) obj;
        if (!memberSubstitution.canEqual(this)) {
            return false;
        }
        MethodGraph.Compiler compiler = this.methodGraphCompiler;
        MethodGraph.Compiler compiler2 = memberSubstitution.methodGraphCompiler;
        if (compiler != null ? !compiler.equals(compiler2) : compiler2 != null) {
            return false;
        }
        if (this.strict != memberSubstitution.strict) {
            return false;
        }
        TypePoolResolver typePoolResolver = this.typePoolResolver;
        TypePoolResolver typePoolResolver2 = memberSubstitution.typePoolResolver;
        if (typePoolResolver != null ? !typePoolResolver.equals(typePoolResolver2) : typePoolResolver2 != null) {
            return false;
        }
        Substitution substitution = this.substitution;
        Substitution substitution2 = memberSubstitution.substitution;
        return substitution != null ? substitution.equals(substitution2) : substitution2 == null;
    }

    public int hashCode() {
        MethodGraph.Compiler compiler = this.methodGraphCompiler;
        int iHashCode = (((compiler == null ? 43 : compiler.hashCode()) + 59) * 59) + (this.strict ? 79 : 97);
        TypePoolResolver typePoolResolver = this.typePoolResolver;
        int iHashCode2 = (iHashCode * 59) + (typePoolResolver == null ? 43 : typePoolResolver.hashCode());
        Substitution substitution = this.substitution;
        return (iHashCode2 * 59) + (substitution != null ? substitution.hashCode() : 43);
    }

    /* synthetic */ MemberSubstitution(MethodGraph.Compiler compiler, TypePoolResolver typePoolResolver, boolean z, Substitution substitution, AnonymousClass1 anonymousClass1) {
        this(compiler, typePoolResolver, z, substitution);
    }

    protected MemberSubstitution(boolean z) {
        this(MethodGraph.Compiler.DEFAULT, TypePoolResolver.OfImplicitPool.INSTANCE, z, Substitution.NoOp.INSTANCE);
    }

    private MemberSubstitution(MethodGraph.Compiler compiler, TypePoolResolver typePoolResolver, boolean z, Substitution substitution) {
        this.methodGraphCompiler = compiler;
        this.typePoolResolver = typePoolResolver;
        this.strict = z;
        this.substitution = substitution;
    }

    public static MemberSubstitution strict() {
        return new MemberSubstitution(true);
    }

    public static MemberSubstitution relaxed() {
        return new MemberSubstitution(false);
    }

    public WithoutSpecification element(ElementMatcher<? super ByteCodeElement> elementMatcher) {
        return new WithoutSpecification.ForMatchedByteCodeElement(this.methodGraphCompiler, this.typePoolResolver, this.strict, this.substitution, elementMatcher);
    }

    public WithoutSpecification.ForMatchedField field(ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher) {
        return new WithoutSpecification.ForMatchedField(this.methodGraphCompiler, this.typePoolResolver, this.strict, this.substitution, elementMatcher);
    }

    public WithoutSpecification.ForMatchedMethod method(ElementMatcher<? super MethodDescription> elementMatcher) {
        return new WithoutSpecification.ForMatchedMethod(this.methodGraphCompiler, this.typePoolResolver, this.strict, this.substitution, elementMatcher);
    }

    public WithoutSpecification constructor(ElementMatcher<? super MethodDescription> elementMatcher) {
        return invokable(ElementMatchers.isConstructor().and(elementMatcher));
    }

    public WithoutSpecification invokable(ElementMatcher<? super MethodDescription> elementMatcher) {
        return new WithoutSpecification.ForMatchedMethod(this.methodGraphCompiler, this.typePoolResolver, this.strict, this.substitution, elementMatcher);
    }

    public MemberSubstitution with(MethodGraph.Compiler compiler) {
        return new MemberSubstitution(compiler, this.typePoolResolver, this.strict, this.substitution);
    }

    public MemberSubstitution with(TypePoolResolver typePoolResolver) {
        return new MemberSubstitution(this.methodGraphCompiler, typePoolResolver, this.strict, this.substitution);
    }

    public AsmVisitorWrapper.ForDeclaredMethods on(ElementMatcher<? super MethodDescription> elementMatcher) {
        return new AsmVisitorWrapper.ForDeclaredMethods().method(elementMatcher, this);
    }

    @Override // net.bytebuddy.asm.AsmVisitorWrapper.ForDeclaredMethods.MethodVisitorWrapper
    public MethodVisitor wrap(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, TypePool typePool, int i, int i2) {
        return new SubstitutingMethodVisitor(methodVisitor, this.methodGraphCompiler, this.strict, this.substitution, typeDescription, context, this.typePoolResolver.resolve(typeDescription, methodDescription, typePool));
    }

    public static abstract class WithoutSpecification {
        protected final MethodGraph.Compiler methodGraphCompiler;
        protected final boolean strict;
        protected final Substitution substitution;
        protected final TypePoolResolver typePoolResolver;

        protected abstract Substitution doReplaceWith(FieldDescription fieldDescription);

        protected abstract Substitution doReplaceWith(MethodDescription methodDescription);

        protected abstract Substitution doStub();

        protected boolean canEqual(Object obj) {
            return obj instanceof WithoutSpecification;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof WithoutSpecification)) {
                return false;
            }
            WithoutSpecification withoutSpecification = (WithoutSpecification) obj;
            if (!withoutSpecification.canEqual(this)) {
                return false;
            }
            MethodGraph.Compiler compiler = this.methodGraphCompiler;
            MethodGraph.Compiler compiler2 = withoutSpecification.methodGraphCompiler;
            if (compiler != null ? !compiler.equals(compiler2) : compiler2 != null) {
                return false;
            }
            TypePoolResolver typePoolResolver = this.typePoolResolver;
            TypePoolResolver typePoolResolver2 = withoutSpecification.typePoolResolver;
            if (typePoolResolver != null ? !typePoolResolver.equals(typePoolResolver2) : typePoolResolver2 != null) {
                return false;
            }
            if (this.strict != withoutSpecification.strict) {
                return false;
            }
            Substitution substitution = this.substitution;
            Substitution substitution2 = withoutSpecification.substitution;
            return substitution != null ? substitution.equals(substitution2) : substitution2 == null;
        }

        public int hashCode() {
            MethodGraph.Compiler compiler = this.methodGraphCompiler;
            int iHashCode = compiler == null ? 43 : compiler.hashCode();
            TypePoolResolver typePoolResolver = this.typePoolResolver;
            int iHashCode2 = ((((iHashCode + 59) * 59) + (typePoolResolver == null ? 43 : typePoolResolver.hashCode())) * 59) + (this.strict ? 79 : 97);
            Substitution substitution = this.substitution;
            return (iHashCode2 * 59) + (substitution != null ? substitution.hashCode() : 43);
        }

        protected WithoutSpecification(MethodGraph.Compiler compiler, TypePoolResolver typePoolResolver, boolean z, Substitution substitution) {
            this.methodGraphCompiler = compiler;
            this.typePoolResolver = typePoolResolver;
            this.strict = z;
            this.substitution = substitution;
        }

        public MemberSubstitution stub() {
            return new MemberSubstitution(this.methodGraphCompiler, this.typePoolResolver, this.strict, new Substitution.Compound(doStub(), this.substitution), null);
        }

        public MemberSubstitution replaceWith(Field field) {
            return replaceWith(new FieldDescription.ForLoadedField(field));
        }

        public MemberSubstitution replaceWith(FieldDescription fieldDescription) {
            return new MemberSubstitution(this.methodGraphCompiler, this.typePoolResolver, this.strict, new Substitution.Compound(doReplaceWith(fieldDescription), this.substitution), null);
        }

        public MemberSubstitution replaceWith(Method method) {
            return replaceWith(new MethodDescription.ForLoadedMethod(method));
        }

        public MemberSubstitution replaceWith(MethodDescription methodDescription) {
            if (!methodDescription.isMethod()) {
                throw new IllegalArgumentException("Cannot use " + methodDescription + " as a replacement");
            }
            return new MemberSubstitution(this.methodGraphCompiler, this.typePoolResolver, this.strict, new Substitution.Compound(doReplaceWith(methodDescription), this.substitution), null);
        }

        protected static class ForMatchedByteCodeElement extends WithoutSpecification {
            private final ElementMatcher<? super ByteCodeElement> matcher;

            @Override // net.bytebuddy.asm.MemberSubstitution.WithoutSpecification
            protected boolean canEqual(Object obj) {
                return obj instanceof ForMatchedByteCodeElement;
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.WithoutSpecification
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForMatchedByteCodeElement)) {
                    return false;
                }
                ForMatchedByteCodeElement forMatchedByteCodeElement = (ForMatchedByteCodeElement) obj;
                if (!forMatchedByteCodeElement.canEqual(this) || !super.equals(obj)) {
                    return false;
                }
                ElementMatcher<? super ByteCodeElement> elementMatcher = this.matcher;
                ElementMatcher<? super ByteCodeElement> elementMatcher2 = forMatchedByteCodeElement.matcher;
                return elementMatcher != null ? elementMatcher.equals(elementMatcher2) : elementMatcher2 == null;
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.WithoutSpecification
            public int hashCode() {
                int iHashCode = super.hashCode() + 59;
                ElementMatcher<? super ByteCodeElement> elementMatcher = this.matcher;
                return (iHashCode * 59) + (elementMatcher == null ? 43 : elementMatcher.hashCode());
            }

            protected ForMatchedByteCodeElement(MethodGraph.Compiler compiler, TypePoolResolver typePoolResolver, boolean z, Substitution substitution, ElementMatcher<? super ByteCodeElement> elementMatcher) {
                super(compiler, typePoolResolver, z, substitution);
                this.matcher = elementMatcher;
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.WithoutSpecification
            protected Substitution doStub() {
                return Substitution.ForElementMatchers.of(this.matcher, Substitution.Resolver.Stubbing.INSTANCE);
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.WithoutSpecification
            protected Substitution doReplaceWith(FieldDescription fieldDescription) {
                return Substitution.ForElementMatchers.of(this.matcher, new Substitution.Resolver.FieldAccessing(fieldDescription));
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.WithoutSpecification
            protected Substitution doReplaceWith(MethodDescription methodDescription) {
                return Substitution.ForElementMatchers.of(this.matcher, new Substitution.Resolver.MethodInvoking(methodDescription));
            }
        }

        public static class ForMatchedField extends WithoutSpecification {
            private final boolean matchRead;
            private final boolean matchWrite;
            private final ElementMatcher<? super FieldDescription.InDefinedShape> matcher;

            @Override // net.bytebuddy.asm.MemberSubstitution.WithoutSpecification
            protected boolean canEqual(Object obj) {
                return obj instanceof ForMatchedField;
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.WithoutSpecification
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForMatchedField)) {
                    return false;
                }
                ForMatchedField forMatchedField = (ForMatchedField) obj;
                if (!forMatchedField.canEqual(this) || !super.equals(obj)) {
                    return false;
                }
                ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher = this.matcher;
                ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher2 = forMatchedField.matcher;
                if (elementMatcher != null ? elementMatcher.equals(elementMatcher2) : elementMatcher2 == null) {
                    return this.matchRead == forMatchedField.matchRead && this.matchWrite == forMatchedField.matchWrite;
                }
                return false;
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.WithoutSpecification
            public int hashCode() {
                int iHashCode = super.hashCode() + 59;
                ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher = this.matcher;
                return (((((iHashCode * 59) + (elementMatcher == null ? 43 : elementMatcher.hashCode())) * 59) + (this.matchRead ? 79 : 97)) * 59) + (this.matchWrite ? 79 : 97);
            }

            protected ForMatchedField(MethodGraph.Compiler compiler, TypePoolResolver typePoolResolver, boolean z, Substitution substitution, ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher) {
                this(compiler, typePoolResolver, z, substitution, elementMatcher, true, true);
            }

            protected ForMatchedField(MethodGraph.Compiler compiler, TypePoolResolver typePoolResolver, boolean z, Substitution substitution, ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher, boolean z2, boolean z3) {
                super(compiler, typePoolResolver, z, substitution);
                this.matcher = elementMatcher;
                this.matchRead = z2;
                this.matchWrite = z3;
            }

            public WithoutSpecification onRead() {
                return new ForMatchedField(this.methodGraphCompiler, this.typePoolResolver, this.strict, this.substitution, this.matcher, true, false);
            }

            public WithoutSpecification onWrite() {
                return new ForMatchedField(this.methodGraphCompiler, this.typePoolResolver, this.strict, this.substitution, this.matcher, false, true);
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.WithoutSpecification
            protected Substitution doStub() {
                return Substitution.ForElementMatchers.ofField(this.matcher, this.matchRead, this.matchWrite, Substitution.Resolver.Stubbing.INSTANCE);
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.WithoutSpecification
            protected Substitution doReplaceWith(FieldDescription fieldDescription) {
                return Substitution.ForElementMatchers.ofField(this.matcher, this.matchRead, this.matchWrite, new Substitution.Resolver.FieldAccessing(fieldDescription));
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.WithoutSpecification
            protected Substitution doReplaceWith(MethodDescription methodDescription) {
                return Substitution.ForElementMatchers.ofField(this.matcher, this.matchRead, this.matchWrite, new Substitution.Resolver.MethodInvoking(methodDescription));
            }
        }

        public static class ForMatchedMethod extends WithoutSpecification {
            private final boolean includeSuperCalls;
            private final boolean includeVirtualCalls;
            private final ElementMatcher<? super MethodDescription> matcher;

            @Override // net.bytebuddy.asm.MemberSubstitution.WithoutSpecification
            protected boolean canEqual(Object obj) {
                return obj instanceof ForMatchedMethod;
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.WithoutSpecification
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForMatchedMethod)) {
                    return false;
                }
                ForMatchedMethod forMatchedMethod = (ForMatchedMethod) obj;
                if (!forMatchedMethod.canEqual(this) || !super.equals(obj)) {
                    return false;
                }
                ElementMatcher<? super MethodDescription> elementMatcher = this.matcher;
                ElementMatcher<? super MethodDescription> elementMatcher2 = forMatchedMethod.matcher;
                if (elementMatcher != null ? elementMatcher.equals(elementMatcher2) : elementMatcher2 == null) {
                    return this.includeVirtualCalls == forMatchedMethod.includeVirtualCalls && this.includeSuperCalls == forMatchedMethod.includeSuperCalls;
                }
                return false;
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.WithoutSpecification
            public int hashCode() {
                int iHashCode = super.hashCode() + 59;
                ElementMatcher<? super MethodDescription> elementMatcher = this.matcher;
                return (((((iHashCode * 59) + (elementMatcher == null ? 43 : elementMatcher.hashCode())) * 59) + (this.includeVirtualCalls ? 79 : 97)) * 59) + (this.includeSuperCalls ? 79 : 97);
            }

            protected ForMatchedMethod(MethodGraph.Compiler compiler, TypePoolResolver typePoolResolver, boolean z, Substitution substitution, ElementMatcher<? super MethodDescription> elementMatcher) {
                this(compiler, typePoolResolver, z, substitution, elementMatcher, true, true);
            }

            protected ForMatchedMethod(MethodGraph.Compiler compiler, TypePoolResolver typePoolResolver, boolean z, Substitution substitution, ElementMatcher<? super MethodDescription> elementMatcher, boolean z2, boolean z3) {
                super(compiler, typePoolResolver, z, substitution);
                this.matcher = elementMatcher;
                this.includeVirtualCalls = z2;
                this.includeSuperCalls = z3;
            }

            public WithoutSpecification onVirtualCall() {
                return new ForMatchedMethod(this.methodGraphCompiler, this.typePoolResolver, this.strict, this.substitution, ElementMatchers.isVirtual().and(this.matcher), true, false);
            }

            public WithoutSpecification onSuperCall() {
                return new ForMatchedMethod(this.methodGraphCompiler, this.typePoolResolver, this.strict, this.substitution, ElementMatchers.isVirtual().and(this.matcher), false, true);
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.WithoutSpecification
            protected Substitution doStub() {
                return Substitution.ForElementMatchers.ofMethod(this.matcher, this.includeVirtualCalls, this.includeSuperCalls, Substitution.Resolver.Stubbing.INSTANCE);
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.WithoutSpecification
            protected Substitution doReplaceWith(FieldDescription fieldDescription) {
                return Substitution.ForElementMatchers.ofMethod(this.matcher, this.includeVirtualCalls, this.includeSuperCalls, new Substitution.Resolver.FieldAccessing(fieldDescription));
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.WithoutSpecification
            protected Substitution doReplaceWith(MethodDescription methodDescription) {
                return Substitution.ForElementMatchers.ofMethod(this.matcher, this.includeVirtualCalls, this.includeSuperCalls, new Substitution.Resolver.MethodInvoking(methodDescription));
            }
        }
    }

    public interface TypePoolResolver {

        public enum OfImplicitPool implements TypePoolResolver {
            INSTANCE;

            @Override // net.bytebuddy.asm.MemberSubstitution.TypePoolResolver
            public TypePool resolve(TypeDescription typeDescription, MethodDescription methodDescription, TypePool typePool) {
                return typePool;
            }
        }

        TypePool resolve(TypeDescription typeDescription, MethodDescription methodDescription, TypePool typePool);

        public static class ForExplicitPool implements TypePoolResolver {
            private final TypePool typePool;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForExplicitPool;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForExplicitPool)) {
                    return false;
                }
                ForExplicitPool forExplicitPool = (ForExplicitPool) obj;
                if (!forExplicitPool.canEqual(this)) {
                    return false;
                }
                TypePool typePool = this.typePool;
                TypePool typePool2 = forExplicitPool.typePool;
                return typePool != null ? typePool.equals(typePool2) : typePool2 == null;
            }

            public int hashCode() {
                TypePool typePool = this.typePool;
                return 59 + (typePool == null ? 43 : typePool.hashCode());
            }

            public ForExplicitPool(TypePool typePool) {
                this.typePool = typePool;
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.TypePoolResolver
            public TypePool resolve(TypeDescription typeDescription, MethodDescription methodDescription, TypePool typePool) {
                return this.typePool;
            }
        }

        public static class ForClassFileLocator implements TypePoolResolver {
            private final ClassFileLocator classFileLocator;
            private final TypePool.Default.ReaderMode readerMode;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForClassFileLocator;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForClassFileLocator)) {
                    return false;
                }
                ForClassFileLocator forClassFileLocator = (ForClassFileLocator) obj;
                if (!forClassFileLocator.canEqual(this)) {
                    return false;
                }
                ClassFileLocator classFileLocator = this.classFileLocator;
                ClassFileLocator classFileLocator2 = forClassFileLocator.classFileLocator;
                if (classFileLocator != null ? !classFileLocator.equals(classFileLocator2) : classFileLocator2 != null) {
                    return false;
                }
                TypePool.Default.ReaderMode readerMode = this.readerMode;
                TypePool.Default.ReaderMode readerMode2 = forClassFileLocator.readerMode;
                return readerMode != null ? readerMode.equals(readerMode2) : readerMode2 == null;
            }

            public int hashCode() {
                ClassFileLocator classFileLocator = this.classFileLocator;
                int iHashCode = classFileLocator == null ? 43 : classFileLocator.hashCode();
                TypePool.Default.ReaderMode readerMode = this.readerMode;
                return ((iHashCode + 59) * 59) + (readerMode != null ? readerMode.hashCode() : 43);
            }

            public ForClassFileLocator(ClassFileLocator classFileLocator) {
                this(classFileLocator, TypePool.Default.ReaderMode.FAST);
            }

            public ForClassFileLocator(ClassFileLocator classFileLocator, TypePool.Default.ReaderMode readerMode) {
                this.classFileLocator = classFileLocator;
                this.readerMode = readerMode;
            }

            public static TypePoolResolver of(ClassLoader classLoader) {
                return new ForClassFileLocator(ClassFileLocator.ForClassLoader.of(classLoader));
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.TypePoolResolver
            public TypePool resolve(TypeDescription typeDescription, MethodDescription methodDescription, TypePool typePool) {
                return new TypePool.Default(new TypePool.CacheProvider.Simple(), this.classFileLocator, this.readerMode, typePool);
            }
        }
    }

    protected interface Substitution {
        Resolver resolve(FieldDescription.InDefinedShape inDefinedShape, boolean z);

        Resolver resolve(MethodDescription methodDescription, InvocationType invocationType);

        public interface Resolver {
            StackManipulation apply(TypeDescription typeDescription, ByteCodeElement byteCodeElement, TypeList.Generic generic, TypeDescription.Generic generic2);

            boolean isResolved();

            public enum Unresolved implements Resolver {
                INSTANCE;

                @Override // net.bytebuddy.asm.MemberSubstitution.Substitution.Resolver
                public boolean isResolved() {
                    return false;
                }

                @Override // net.bytebuddy.asm.MemberSubstitution.Substitution.Resolver
                public StackManipulation apply(TypeDescription typeDescription, ByteCodeElement byteCodeElement, TypeList.Generic generic, TypeDescription.Generic generic2) {
                    throw new IllegalStateException("Cannot apply unresolved resolver");
                }
            }

            public enum Stubbing implements Resolver {
                INSTANCE;

                @Override // net.bytebuddy.asm.MemberSubstitution.Substitution.Resolver
                public boolean isResolved() {
                    return true;
                }

                @Override // net.bytebuddy.asm.MemberSubstitution.Substitution.Resolver
                public StackManipulation apply(TypeDescription typeDescription, ByteCodeElement byteCodeElement, TypeList.Generic generic, TypeDescription.Generic generic2) {
                    ArrayList arrayList = new ArrayList(generic.size());
                    for (int size = generic.size() - 1; size >= 0; size--) {
                        arrayList.add(Removal.of((TypeDefinition) generic.get(size)));
                    }
                    return new StackManipulation.Compound((List<? extends StackManipulation>) CompoundList.of(arrayList, DefaultValue.of(generic2.asErasure())));
                }
            }

            public static class FieldAccessing implements Resolver {
                private final FieldDescription fieldDescription;

                @Override // net.bytebuddy.asm.MemberSubstitution.Substitution.Resolver
                public boolean isResolved() {
                    return true;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof FieldAccessing;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof FieldAccessing)) {
                        return false;
                    }
                    FieldAccessing fieldAccessing = (FieldAccessing) obj;
                    if (!fieldAccessing.canEqual(this)) {
                        return false;
                    }
                    FieldDescription fieldDescription = this.fieldDescription;
                    FieldDescription fieldDescription2 = fieldAccessing.fieldDescription;
                    return fieldDescription != null ? fieldDescription.equals(fieldDescription2) : fieldDescription2 == null;
                }

                public int hashCode() {
                    FieldDescription fieldDescription = this.fieldDescription;
                    return 59 + (fieldDescription == null ? 43 : fieldDescription.hashCode());
                }

                protected FieldAccessing(FieldDescription fieldDescription) {
                    this.fieldDescription = fieldDescription;
                }

                @Override // net.bytebuddy.asm.MemberSubstitution.Substitution.Resolver
                public StackManipulation apply(TypeDescription typeDescription, ByteCodeElement byteCodeElement, TypeList.Generic generic, TypeDescription.Generic generic2) {
                    if (!this.fieldDescription.isAccessibleTo(typeDescription)) {
                        throw new IllegalStateException(typeDescription + " cannot access " + this.fieldDescription);
                    }
                    if (generic2.represents(Void.TYPE)) {
                        if (generic.size() != (this.fieldDescription.isStatic() ? 1 : 2)) {
                            throw new IllegalStateException("Cannot set " + this.fieldDescription + " with " + generic);
                        }
                        if (!this.fieldDescription.isStatic() && !((TypeDescription.Generic) generic.get(0)).asErasure().isAssignableTo(this.fieldDescription.getDeclaringType().asErasure())) {
                            throw new IllegalStateException("Cannot set " + this.fieldDescription + " on " + generic.get(0));
                        }
                        if (!((TypeDescription.Generic) generic.get(!this.fieldDescription.isStatic() ? 1 : 0)).asErasure().isAssignableTo(this.fieldDescription.getType().asErasure())) {
                            throw new IllegalStateException("Cannot set " + this.fieldDescription + " to " + generic.get(!this.fieldDescription.isStatic() ? 1 : 0));
                        }
                        return FieldAccess.forField(this.fieldDescription).write();
                    }
                    if (generic.size() != (1 ^ (this.fieldDescription.isStatic() ? 1 : 0))) {
                        throw new IllegalStateException("Cannot set " + this.fieldDescription + " with " + generic);
                    }
                    if (!this.fieldDescription.isStatic() && !((TypeDescription.Generic) generic.get(0)).asErasure().isAssignableTo(this.fieldDescription.getDeclaringType().asErasure())) {
                        throw new IllegalStateException("Cannot get " + this.fieldDescription + " on " + generic.get(0));
                    }
                    if (!this.fieldDescription.getType().asErasure().isAssignableTo(generic2.asErasure())) {
                        throw new IllegalStateException("Cannot get " + this.fieldDescription + " as " + generic2);
                    }
                    return FieldAccess.forField(this.fieldDescription).read();
                }
            }

            public static class MethodInvoking implements Resolver {
                private final MethodDescription methodDescription;

                @Override // net.bytebuddy.asm.MemberSubstitution.Substitution.Resolver
                public boolean isResolved() {
                    return true;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof MethodInvoking;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof MethodInvoking)) {
                        return false;
                    }
                    MethodInvoking methodInvoking = (MethodInvoking) obj;
                    if (!methodInvoking.canEqual(this)) {
                        return false;
                    }
                    MethodDescription methodDescription = this.methodDescription;
                    MethodDescription methodDescription2 = methodInvoking.methodDescription;
                    return methodDescription != null ? methodDescription.equals(methodDescription2) : methodDescription2 == null;
                }

                public int hashCode() {
                    MethodDescription methodDescription = this.methodDescription;
                    return 59 + (methodDescription == null ? 43 : methodDescription.hashCode());
                }

                protected MethodInvoking(MethodDescription methodDescription) {
                    this.methodDescription = methodDescription;
                }

                @Override // net.bytebuddy.asm.MemberSubstitution.Substitution.Resolver
                public StackManipulation apply(TypeDescription typeDescription, ByteCodeElement byteCodeElement, TypeList.Generic generic, TypeDescription.Generic generic2) {
                    TypeList.Generic explicit;
                    if (!this.methodDescription.isAccessibleTo(typeDescription)) {
                        throw new IllegalStateException(typeDescription + " cannot access " + this.methodDescription);
                    }
                    if (this.methodDescription.isStatic()) {
                        explicit = this.methodDescription.getParameters().asTypeList();
                    } else {
                        explicit = new TypeList.Generic.Explicit((List<? extends TypeDefinition>) CompoundList.of(this.methodDescription.getDeclaringType(), this.methodDescription.getParameters().asTypeList()));
                    }
                    if (!this.methodDescription.getReturnType().asErasure().isAssignableTo(generic2.asErasure())) {
                        throw new IllegalStateException("Cannot assign return value of " + this.methodDescription + " to " + generic2);
                    }
                    if (explicit.size() != generic.size()) {
                        throw new IllegalStateException("Cannot invoke " + this.methodDescription + " on " + generic);
                    }
                    for (int i = 0; i < explicit.size(); i++) {
                        if (!((TypeDescription.Generic) explicit.get(i)).asErasure().isAssignableTo(((TypeDescription.Generic) generic.get(i)).asErasure())) {
                            throw new IllegalStateException("Cannot invoke " + this.methodDescription + " on " + generic);
                        }
                    }
                    if (this.methodDescription.isVirtual()) {
                        return MethodInvocation.invoke(this.methodDescription).virtual(byteCodeElement.getDeclaringType().asErasure());
                    }
                    return MethodInvocation.invoke(this.methodDescription);
                }
            }
        }

        public enum InvocationType {
            VIRTUAL,
            SUPER,
            OTHER;

            protected static InvocationType of(int i, MethodDescription methodDescription) {
                if (i != 182) {
                    if (i == 183) {
                        return methodDescription.isVirtual() ? SUPER : OTHER;
                    }
                    if (i != 185) {
                        return OTHER;
                    }
                }
                return VIRTUAL;
            }

            protected boolean matches(boolean z, boolean z2) {
                int i = AnonymousClass1.$SwitchMap$net$bytebuddy$asm$MemberSubstitution$Substitution$InvocationType[ordinal()];
                if (i == 1) {
                    return z;
                }
                if (i != 2) {
                    return true;
                }
                return z2;
            }
        }

        public enum NoOp implements Substitution {
            INSTANCE;

            @Override // net.bytebuddy.asm.MemberSubstitution.Substitution
            public Resolver resolve(FieldDescription.InDefinedShape inDefinedShape, boolean z) {
                return Resolver.Unresolved.INSTANCE;
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.Substitution
            public Resolver resolve(MethodDescription methodDescription, InvocationType invocationType) {
                return Resolver.Unresolved.INSTANCE;
            }
        }

        public static class ForElementMatchers implements Substitution {
            private final ElementMatcher<? super FieldDescription.InDefinedShape> fieldMatcher;
            private final boolean includeSuperCalls;
            private final boolean includeVirtualCalls;
            private final boolean matchFieldRead;
            private final boolean matchFieldWrite;
            private final ElementMatcher<? super MethodDescription> methodMatcher;
            private final Resolver resolver;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForElementMatchers;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForElementMatchers)) {
                    return false;
                }
                ForElementMatchers forElementMatchers = (ForElementMatchers) obj;
                if (!forElementMatchers.canEqual(this)) {
                    return false;
                }
                ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher = this.fieldMatcher;
                ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher2 = forElementMatchers.fieldMatcher;
                if (elementMatcher != null ? !elementMatcher.equals(elementMatcher2) : elementMatcher2 != null) {
                    return false;
                }
                ElementMatcher<? super MethodDescription> elementMatcher3 = this.methodMatcher;
                ElementMatcher<? super MethodDescription> elementMatcher4 = forElementMatchers.methodMatcher;
                if (elementMatcher3 != null ? !elementMatcher3.equals(elementMatcher4) : elementMatcher4 != null) {
                    return false;
                }
                if (this.matchFieldRead != forElementMatchers.matchFieldRead || this.matchFieldWrite != forElementMatchers.matchFieldWrite || this.includeVirtualCalls != forElementMatchers.includeVirtualCalls || this.includeSuperCalls != forElementMatchers.includeSuperCalls) {
                    return false;
                }
                Resolver resolver = this.resolver;
                Resolver resolver2 = forElementMatchers.resolver;
                return resolver != null ? resolver.equals(resolver2) : resolver2 == null;
            }

            public int hashCode() {
                ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher = this.fieldMatcher;
                int iHashCode = elementMatcher == null ? 43 : elementMatcher.hashCode();
                ElementMatcher<? super MethodDescription> elementMatcher2 = this.methodMatcher;
                int iHashCode2 = (((((((((iHashCode + 59) * 59) + (elementMatcher2 == null ? 43 : elementMatcher2.hashCode())) * 59) + (this.matchFieldRead ? 79 : 97)) * 59) + (this.matchFieldWrite ? 79 : 97)) * 59) + (this.includeVirtualCalls ? 79 : 97)) * 59;
                int i = this.includeSuperCalls ? 79 : 97;
                Resolver resolver = this.resolver;
                return ((iHashCode2 + i) * 59) + (resolver != null ? resolver.hashCode() : 43);
            }

            protected static Substitution of(ElementMatcher<? super ByteCodeElement> elementMatcher, Resolver resolver) {
                return new ForElementMatchers(elementMatcher, elementMatcher, true, true, true, true, resolver);
            }

            protected static Substitution ofField(ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher, boolean z, boolean z2, Resolver resolver) {
                return new ForElementMatchers(elementMatcher, ElementMatchers.none(), z, z2, false, false, resolver);
            }

            protected static Substitution ofMethod(ElementMatcher<? super MethodDescription> elementMatcher, boolean z, boolean z2, Resolver resolver) {
                return new ForElementMatchers(ElementMatchers.none(), elementMatcher, false, false, z, z2, resolver);
            }

            protected ForElementMatchers(ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher, ElementMatcher<? super MethodDescription> elementMatcher2, boolean z, boolean z2, boolean z3, boolean z4, Resolver resolver) {
                this.fieldMatcher = elementMatcher;
                this.methodMatcher = elementMatcher2;
                this.matchFieldRead = z;
                this.matchFieldWrite = z2;
                this.includeVirtualCalls = z3;
                this.includeSuperCalls = z4;
                this.resolver = resolver;
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.Substitution
            public Resolver resolve(FieldDescription.InDefinedShape inDefinedShape, boolean z) {
                if (!z ? this.matchFieldRead : this.matchFieldWrite) {
                    if (this.fieldMatcher.matches(inDefinedShape)) {
                        return this.resolver;
                    }
                }
                return Resolver.Unresolved.INSTANCE;
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.Substitution
            public Resolver resolve(MethodDescription methodDescription, InvocationType invocationType) {
                return (invocationType.matches(this.includeVirtualCalls, this.includeSuperCalls) && this.methodMatcher.matches(methodDescription)) ? this.resolver : Resolver.Unresolved.INSTANCE;
            }
        }

        public static class Compound implements Substitution {
            private final List<Substitution> substitutions;

            protected boolean canEqual(Object obj) {
                return obj instanceof Compound;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Compound)) {
                    return false;
                }
                Compound compound = (Compound) obj;
                if (!compound.canEqual(this)) {
                    return false;
                }
                List<Substitution> list = this.substitutions;
                List<Substitution> list2 = compound.substitutions;
                return list != null ? list.equals(list2) : list2 == null;
            }

            public int hashCode() {
                List<Substitution> list = this.substitutions;
                return 59 + (list == null ? 43 : list.hashCode());
            }

            protected Compound(Substitution... substitutionArr) {
                this((List<? extends Substitution>) Arrays.asList(substitutionArr));
            }

            protected Compound(List<? extends Substitution> list) {
                this.substitutions = new ArrayList(list.size());
                for (Substitution substitution : list) {
                    if (substitution instanceof Compound) {
                        this.substitutions.addAll(((Compound) substitution).substitutions);
                    } else if (!(substitution instanceof NoOp)) {
                        this.substitutions.add(substitution);
                    }
                }
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.Substitution
            public Resolver resolve(FieldDescription.InDefinedShape inDefinedShape, boolean z) {
                Iterator<Substitution> it = this.substitutions.iterator();
                while (it.hasNext()) {
                    Resolver resolverResolve = it.next().resolve(inDefinedShape, z);
                    if (resolverResolve.isResolved()) {
                        return resolverResolve;
                    }
                }
                return Resolver.Unresolved.INSTANCE;
            }

            @Override // net.bytebuddy.asm.MemberSubstitution.Substitution
            public Resolver resolve(MethodDescription methodDescription, InvocationType invocationType) {
                Iterator<Substitution> it = this.substitutions.iterator();
                while (it.hasNext()) {
                    Resolver resolverResolve = it.next().resolve(methodDescription, invocationType);
                    if (resolverResolve.isResolved()) {
                        return resolverResolve;
                    }
                }
                return Resolver.Unresolved.INSTANCE;
            }
        }
    }

    /* JADX INFO: renamed from: net.bytebuddy.asm.MemberSubstitution$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$net$bytebuddy$asm$MemberSubstitution$Substitution$InvocationType;

        static {
            int[] iArr = new int[Substitution.InvocationType.values().length];
            $SwitchMap$net$bytebuddy$asm$MemberSubstitution$Substitution$InvocationType = iArr;
            try {
                iArr[Substitution.InvocationType.VIRTUAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$net$bytebuddy$asm$MemberSubstitution$Substitution$InvocationType[Substitution.InvocationType.SUPER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    protected static class SubstitutingMethodVisitor extends MethodVisitor {
        private final Implementation.Context implementationContext;
        private final TypeDescription instrumentedType;
        private final MethodGraph.Compiler methodGraphCompiler;
        private int stackSizeBuffer;
        private final boolean strict;
        private final Substitution substitution;
        private final TypePool typePool;

        protected SubstitutingMethodVisitor(MethodVisitor methodVisitor, MethodGraph.Compiler compiler, boolean z, Substitution substitution, TypeDescription typeDescription, Implementation.Context context, TypePool typePool) {
            super(Opcodes.ASM6, methodVisitor);
            this.methodGraphCompiler = compiler;
            this.strict = z;
            this.substitution = substitution;
            this.instrumentedType = typeDescription;
            this.implementationContext = context;
            this.typePool = typePool;
            this.stackSizeBuffer = 0;
        }

        @Override // net.bytebuddy.jar.asm.MethodVisitor
        public void visitFieldInsn(int i, String str, String str2, String str3) {
            TypeList.Generic empty;
            TypeDescription.Generic type;
            TypePool.Resolution resolutionDescribe = this.typePool.describe(str.replace(ClassPathElement.SEPARATOR_CHAR, TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH));
            if (resolutionDescribe.isResolved()) {
                FieldList fieldListFilter = resolutionDescribe.resolve().getDeclaredFields().filter(ElementMatchers.named(str2).and(ElementMatchers.hasDescriptor(str3)));
                if (!fieldListFilter.isEmpty()) {
                    Substitution.Resolver resolverResolve = this.substitution.resolve((FieldDescription.InDefinedShape) fieldListFilter.getOnly(), i == 181 || i == 179);
                    if (resolverResolve.isResolved()) {
                        switch (i) {
                            case 178:
                                empty = new TypeList.Generic.Empty();
                                type = ((FieldDescription.InDefinedShape) fieldListFilter.getOnly()).getType();
                                break;
                            case 179:
                                empty = new TypeList.Generic.Explicit(((FieldDescription.InDefinedShape) fieldListFilter.getOnly()).getType());
                                type = TypeDescription.Generic.VOID;
                                break;
                            case 180:
                                empty = new TypeList.Generic.Explicit(((FieldDescription.InDefinedShape) fieldListFilter.getOnly()).getDeclaringType());
                                type = ((FieldDescription.InDefinedShape) fieldListFilter.getOnly()).getType();
                                break;
                            case 181:
                                empty = new TypeList.Generic.Explicit(((FieldDescription.InDefinedShape) fieldListFilter.getOnly()).getDeclaringType(), ((FieldDescription.InDefinedShape) fieldListFilter.getOnly()).getType());
                                type = TypeDescription.Generic.VOID;
                                break;
                            default:
                                throw new AssertionError();
                        }
                        resolverResolve.apply(this.instrumentedType, (ByteCodeElement) fieldListFilter.getOnly(), empty, type).apply(this.mv, this.implementationContext);
                        return;
                    }
                } else if (this.strict) {
                    throw new IllegalStateException("Could not resolve " + str.replace(ClassPathElement.SEPARATOR_CHAR, TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH) + "." + str2 + str3 + " using " + this.typePool);
                }
            } else if (this.strict) {
                throw new IllegalStateException("Could not resolve " + str.replace(ClassPathElement.SEPARATOR_CHAR, TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH) + " using " + this.typePool);
            }
            super.visitFieldInsn(i, str, str2, str3);
        }

        @Override // net.bytebuddy.jar.asm.MethodVisitor
        public void visitMethodInsn(int i, String str, String str2, String str3, boolean z) {
            MethodList methodListFilter;
            TypeDescription.Generic returnType;
            TypePool.Resolution resolutionDescribe = this.typePool.describe(str.replace(ClassPathElement.SEPARATOR_CHAR, TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH));
            if (resolutionDescribe.isResolved()) {
                if (i == 183 && str2.equals(MethodDescription.CONSTRUCTOR_INTERNAL_NAME)) {
                    methodListFilter = resolutionDescribe.resolve().getDeclaredMethods().filter(ElementMatchers.isConstructor().and(ElementMatchers.hasDescriptor(str3)));
                } else if (i == 184 || i == 183) {
                    methodListFilter = resolutionDescribe.resolve().getDeclaredMethods().filter(ElementMatchers.named(str2).and(ElementMatchers.hasDescriptor(str3)));
                } else {
                    methodListFilter = (MethodList) this.methodGraphCompiler.compile(resolutionDescribe.resolve()).listNodes().asMethodList().filter(ElementMatchers.named(str2).and(ElementMatchers.hasDescriptor(str3)));
                }
                if (!methodListFilter.isEmpty()) {
                    Substitution.Resolver resolverResolve = this.substitution.resolve((MethodDescription) methodListFilter.getOnly(), Substitution.InvocationType.of(i, (MethodDescription) methodListFilter.getOnly()));
                    if (resolverResolve.isResolved()) {
                        TypeDescription typeDescription = this.instrumentedType;
                        ByteCodeElement byteCodeElement = (ByteCodeElement) methodListFilter.getOnly();
                        TypeList.Generic genericAsTypeList = (((MethodDescription) methodListFilter.getOnly()).isStatic() || ((MethodDescription) methodListFilter.getOnly()).isConstructor()) ? ((MethodDescription) methodListFilter.getOnly()).getParameters().asTypeList() : new TypeList.Generic.Explicit((List<? extends TypeDefinition>) CompoundList.of(((MethodDescription) methodListFilter.getOnly()).getDeclaringType(), ((MethodDescription) methodListFilter.getOnly()).getParameters().asTypeList()));
                        if (((MethodDescription) methodListFilter.getOnly()).isConstructor()) {
                            returnType = ((MethodDescription) methodListFilter.getOnly()).getDeclaringType().asGenericType();
                        } else {
                            returnType = ((MethodDescription) methodListFilter.getOnly()).getReturnType();
                        }
                        resolverResolve.apply(typeDescription, byteCodeElement, genericAsTypeList, returnType).apply(this.mv, this.implementationContext);
                        if (((MethodDescription) methodListFilter.getOnly()).isConstructor()) {
                            this.stackSizeBuffer = new StackManipulation.Compound(Duplication.SINGLE.flipOver(TypeDescription.OBJECT), Removal.SINGLE, Removal.SINGLE, Duplication.SINGLE.flipOver(TypeDescription.OBJECT), Removal.SINGLE, Removal.SINGLE).apply(this.mv, this.implementationContext).getMaximalSize() + StackSize.SINGLE.getSize();
                            return;
                        }
                        return;
                    }
                } else if (this.strict) {
                    throw new IllegalStateException("Could not resolve " + str.replace(ClassPathElement.SEPARATOR_CHAR, TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH) + "." + str2 + str3 + " using " + this.typePool);
                }
            } else if (this.strict) {
                throw new IllegalStateException("Could not resolve " + str.replace(ClassPathElement.SEPARATOR_CHAR, TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH) + " using " + this.typePool);
            }
            super.visitMethodInsn(i, str, str2, str3, z);
        }

        @Override // net.bytebuddy.jar.asm.MethodVisitor
        public void visitMaxs(int i, int i2) {
            super.visitMaxs(i + this.stackSizeBuffer, i2);
        }
    }
}
