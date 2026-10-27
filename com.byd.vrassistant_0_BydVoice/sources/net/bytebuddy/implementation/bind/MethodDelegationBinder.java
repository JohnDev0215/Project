package net.bytebuddy.implementation.bind;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bind.annotation.BindingPriority;
import net.bytebuddy.implementation.bytecode.Removal;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.utility.CompoundList;

/* JADX INFO: loaded from: classes3.dex */
public interface MethodDelegationBinder {
    Record compile(MethodDescription methodDescription);

    public interface Record {
        MethodBinding bind(Implementation.Target target, MethodDescription methodDescription, TerminationHandler terminationHandler, MethodInvoker methodInvoker, Assigner assigner);

        public enum Illegal implements Record {
            INSTANCE;

            @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.Record
            public MethodBinding bind(Implementation.Target target, MethodDescription methodDescription, TerminationHandler terminationHandler, MethodInvoker methodInvoker, Assigner assigner) {
                return MethodBinding.Illegal.INSTANCE;
            }
        }
    }

    public interface MethodInvoker {
        StackManipulation invoke(MethodDescription methodDescription);

        public enum Simple implements MethodInvoker {
            INSTANCE;

            @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.MethodInvoker
            public StackManipulation invoke(MethodDescription methodDescription) {
                return MethodInvocation.invoke(methodDescription);
            }
        }

        public static class Virtual implements MethodInvoker {
            private final TypeDescription typeDescription;

            protected boolean canEqual(Object obj) {
                return obj instanceof Virtual;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Virtual)) {
                    return false;
                }
                Virtual virtual = (Virtual) obj;
                if (!virtual.canEqual(this)) {
                    return false;
                }
                TypeDescription typeDescription = this.typeDescription;
                TypeDescription typeDescription2 = virtual.typeDescription;
                return typeDescription != null ? typeDescription.equals(typeDescription2) : typeDescription2 == null;
            }

            public int hashCode() {
                TypeDescription typeDescription = this.typeDescription;
                return 59 + (typeDescription == null ? 43 : typeDescription.hashCode());
            }

            public Virtual(TypeDescription typeDescription) {
                this.typeDescription = typeDescription;
            }

            @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.MethodInvoker
            public StackManipulation invoke(MethodDescription methodDescription) {
                return MethodInvocation.invoke(methodDescription).virtual(this.typeDescription);
            }
        }
    }

    public interface ParameterBinding<T> extends StackManipulation {
        T getIdentificationToken();

        public enum Illegal implements ParameterBinding<Void> {
            INSTANCE;

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public boolean isValid() {
                return false;
            }

            @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.ParameterBinding
            public Void getIdentificationToken() {
                throw new IllegalStateException("An illegal binding does not define an identification token");
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                throw new IllegalStateException("An illegal parameter binding must not be applied");
            }
        }

        public static class Anonymous implements ParameterBinding<Object> {
            private final Object anonymousToken = new Object();
            private final StackManipulation delegate;

            protected boolean canEqual(Object obj) {
                return obj instanceof Anonymous;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Anonymous)) {
                    return false;
                }
                Anonymous anonymous = (Anonymous) obj;
                if (!anonymous.canEqual(this)) {
                    return false;
                }
                StackManipulation stackManipulation = this.delegate;
                StackManipulation stackManipulation2 = anonymous.delegate;
                return stackManipulation != null ? stackManipulation.equals(stackManipulation2) : stackManipulation2 == null;
            }

            public int hashCode() {
                StackManipulation stackManipulation = this.delegate;
                return 59 + (stackManipulation == null ? 43 : stackManipulation.hashCode());
            }

            public Anonymous(StackManipulation stackManipulation) {
                this.delegate = stackManipulation;
            }

            @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.ParameterBinding
            public Object getIdentificationToken() {
                return this.anonymousToken;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public boolean isValid() {
                return this.delegate.isValid();
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                return this.delegate.apply(methodVisitor, context);
            }
        }

        public static class Unique<T> implements ParameterBinding<T> {
            private final StackManipulation delegate;
            private final T identificationToken;

            protected boolean canEqual(Object obj) {
                return obj instanceof Unique;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Unique)) {
                    return false;
                }
                Unique unique = (Unique) obj;
                if (!unique.canEqual(this)) {
                    return false;
                }
                T identificationToken = getIdentificationToken();
                Object identificationToken2 = unique.getIdentificationToken();
                if (identificationToken != null ? !identificationToken.equals(identificationToken2) : identificationToken2 != null) {
                    return false;
                }
                StackManipulation stackManipulation = this.delegate;
                StackManipulation stackManipulation2 = unique.delegate;
                return stackManipulation != null ? stackManipulation.equals(stackManipulation2) : stackManipulation2 == null;
            }

            public int hashCode() {
                T identificationToken = getIdentificationToken();
                int iHashCode = identificationToken == null ? 43 : identificationToken.hashCode();
                StackManipulation stackManipulation = this.delegate;
                return ((iHashCode + 59) * 59) + (stackManipulation != null ? stackManipulation.hashCode() : 43);
            }

            public Unique(StackManipulation stackManipulation, T t) {
                this.delegate = stackManipulation;
                this.identificationToken = t;
            }

            public static <S> Unique<S> of(StackManipulation stackManipulation, S s) {
                return new Unique<>(stackManipulation, s);
            }

            @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.ParameterBinding
            public T getIdentificationToken() {
                return this.identificationToken;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public boolean isValid() {
                return this.delegate.isValid();
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                return this.delegate.apply(methodVisitor, context);
            }
        }
    }

    public interface MethodBinding extends StackManipulation {
        MethodDescription getTarget();

        Integer getTargetParameterIndex(Object obj);

        public enum Illegal implements MethodBinding {
            INSTANCE;

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public boolean isValid() {
                return false;
            }

            @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.MethodBinding
            public Integer getTargetParameterIndex(Object obj) {
                throw new IllegalStateException("Method is not bound");
            }

            @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.MethodBinding
            public MethodDescription getTarget() {
                throw new IllegalStateException("Method is not bound");
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                throw new IllegalStateException("Cannot delegate to an unbound method");
            }
        }

        public static class Builder {
            private final MethodDescription candidate;
            private final MethodInvoker methodInvoker;
            private final List<StackManipulation> parameterStackManipulations;
            private final LinkedHashMap<Object, Integer> registeredTargetIndices = new LinkedHashMap<>();
            private int nextParameterIndex = 0;

            public Builder(MethodInvoker methodInvoker, MethodDescription methodDescription) {
                this.methodInvoker = methodInvoker;
                this.candidate = methodDescription;
                this.parameterStackManipulations = new ArrayList(methodDescription.getParameters().size());
            }

            public boolean append(ParameterBinding<?> parameterBinding) {
                this.parameterStackManipulations.add(parameterBinding);
                LinkedHashMap<Object, Integer> linkedHashMap = this.registeredTargetIndices;
                Object identificationToken = parameterBinding.getIdentificationToken();
                int i = this.nextParameterIndex;
                this.nextParameterIndex = i + 1;
                return linkedHashMap.put(identificationToken, Integer.valueOf(i)) == null;
            }

            public MethodBinding build(StackManipulation stackManipulation) {
                if (this.candidate.getParameters().size() != this.nextParameterIndex) {
                    throw new IllegalStateException("The number of parameters bound does not equal the target's number of parameters");
                }
                MethodDescription methodDescription = this.candidate;
                return new Build(methodDescription, this.registeredTargetIndices, this.methodInvoker.invoke(methodDescription), this.parameterStackManipulations, stackManipulation);
            }

            protected static class Build implements MethodBinding {
                private final StackManipulation methodInvocation;
                private final List<StackManipulation> parameterStackManipulations;
                private final Map<?, Integer> registeredTargetIndices;
                private final MethodDescription target;
                private final StackManipulation terminatingStackManipulation;

                protected boolean canEqual(Object obj) {
                    return obj instanceof Build;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Build)) {
                        return false;
                    }
                    Build build = (Build) obj;
                    if (!build.canEqual(this)) {
                        return false;
                    }
                    MethodDescription target = getTarget();
                    MethodDescription target2 = build.getTarget();
                    if (target != null ? !target.equals(target2) : target2 != null) {
                        return false;
                    }
                    Map<?, Integer> map = this.registeredTargetIndices;
                    Map<?, Integer> map2 = build.registeredTargetIndices;
                    if (map != null ? !map.equals(map2) : map2 != null) {
                        return false;
                    }
                    StackManipulation stackManipulation = this.methodInvocation;
                    StackManipulation stackManipulation2 = build.methodInvocation;
                    if (stackManipulation != null ? !stackManipulation.equals(stackManipulation2) : stackManipulation2 != null) {
                        return false;
                    }
                    List<StackManipulation> list = this.parameterStackManipulations;
                    List<StackManipulation> list2 = build.parameterStackManipulations;
                    if (list != null ? !list.equals(list2) : list2 != null) {
                        return false;
                    }
                    StackManipulation stackManipulation3 = this.terminatingStackManipulation;
                    StackManipulation stackManipulation4 = build.terminatingStackManipulation;
                    return stackManipulation3 != null ? stackManipulation3.equals(stackManipulation4) : stackManipulation4 == null;
                }

                public int hashCode() {
                    MethodDescription target = getTarget();
                    int iHashCode = target == null ? 43 : target.hashCode();
                    Map<?, Integer> map = this.registeredTargetIndices;
                    int iHashCode2 = ((iHashCode + 59) * 59) + (map == null ? 43 : map.hashCode());
                    StackManipulation stackManipulation = this.methodInvocation;
                    int iHashCode3 = (iHashCode2 * 59) + (stackManipulation == null ? 43 : stackManipulation.hashCode());
                    List<StackManipulation> list = this.parameterStackManipulations;
                    int iHashCode4 = (iHashCode3 * 59) + (list == null ? 43 : list.hashCode());
                    StackManipulation stackManipulation2 = this.terminatingStackManipulation;
                    return (iHashCode4 * 59) + (stackManipulation2 != null ? stackManipulation2.hashCode() : 43);
                }

                protected Build(MethodDescription methodDescription, Map<?, Integer> map, StackManipulation stackManipulation, List<StackManipulation> list, StackManipulation stackManipulation2) {
                    this.target = methodDescription;
                    this.registeredTargetIndices = new HashMap(map);
                    this.methodInvocation = stackManipulation;
                    this.parameterStackManipulations = new ArrayList(list);
                    this.terminatingStackManipulation = stackManipulation2;
                }

                @Override // net.bytebuddy.implementation.bytecode.StackManipulation
                public boolean isValid() {
                    boolean zIsValid = this.methodInvocation.isValid() && this.terminatingStackManipulation.isValid();
                    Iterator<StackManipulation> it = this.parameterStackManipulations.iterator();
                    while (zIsValid && it.hasNext()) {
                        zIsValid = it.next().isValid();
                    }
                    return zIsValid;
                }

                @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.MethodBinding
                public Integer getTargetParameterIndex(Object obj) {
                    return this.registeredTargetIndices.get(obj);
                }

                @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.MethodBinding
                public MethodDescription getTarget() {
                    return this.target;
                }

                @Override // net.bytebuddy.implementation.bytecode.StackManipulation
                public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                    return new StackManipulation.Compound((List<? extends StackManipulation>) CompoundList.of((List) this.parameterStackManipulations, Arrays.asList(this.methodInvocation, this.terminatingStackManipulation))).apply(methodVisitor, context);
                }
            }
        }
    }

    public interface BindingResolver {
        MethodBinding resolve(AmbiguityResolver ambiguityResolver, MethodDescription methodDescription, List<MethodBinding> list);

        public enum Default implements BindingResolver {
            INSTANCE;

            private static final int LEFT = 0;
            private static final int ONLY = 0;
            private static final int RIGHT = 1;

            @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.BindingResolver
            public MethodBinding resolve(AmbiguityResolver ambiguityResolver, MethodDescription methodDescription, List<MethodBinding> list) {
                return doResolve(ambiguityResolver, methodDescription, new ArrayList(list));
            }

            private MethodBinding doResolve(AmbiguityResolver ambiguityResolver, MethodDescription methodDescription, List<MethodBinding> list) {
                int size = list.size();
                if (size == 1) {
                    return list.get(0);
                }
                if (size == 2) {
                    MethodBinding methodBinding = list.get(0);
                    MethodBinding methodBinding2 = list.get(1);
                    int i = AnonymousClass1.$SwitchMap$net$bytebuddy$implementation$bind$MethodDelegationBinder$AmbiguityResolver$Resolution[ambiguityResolver.resolve(methodDescription, methodBinding, methodBinding2).ordinal()];
                    if (i == 1) {
                        return methodBinding;
                    }
                    if (i == 2) {
                        return methodBinding2;
                    }
                    if (i == 3 || i == 4) {
                        throw new IllegalArgumentException("Cannot resolve ambiguous delegation of " + methodDescription + " to " + methodBinding + " or " + methodBinding2);
                    }
                    throw new AssertionError();
                }
                MethodBinding methodBinding3 = list.get(0);
                MethodBinding methodBinding4 = list.get(1);
                int i2 = AnonymousClass1.$SwitchMap$net$bytebuddy$implementation$bind$MethodDelegationBinder$AmbiguityResolver$Resolution[ambiguityResolver.resolve(methodDescription, methodBinding3, methodBinding4).ordinal()];
                if (i2 == 1) {
                    list.remove(1);
                    return doResolve(ambiguityResolver, methodDescription, list);
                }
                if (i2 == 2) {
                    list.remove(0);
                    return doResolve(ambiguityResolver, methodDescription, list);
                }
                if (i2 == 3 || i2 == 4) {
                    list.remove(1);
                    list.remove(0);
                    MethodBinding methodBindingDoResolve = doResolve(ambiguityResolver, methodDescription, list);
                    int i3 = AnonymousClass1.$SwitchMap$net$bytebuddy$implementation$bind$MethodDelegationBinder$AmbiguityResolver$Resolution[ambiguityResolver.resolve(methodDescription, methodBinding3, methodBindingDoResolve).merge(ambiguityResolver.resolve(methodDescription, methodBinding4, methodBindingDoResolve)).ordinal()];
                    if (i3 != 1) {
                        if (i3 == 2) {
                            return methodBindingDoResolve;
                        }
                        if (i3 != 3 && i3 != 4) {
                            throw new AssertionError();
                        }
                    }
                    throw new IllegalArgumentException("Cannot resolve ambiguous delegation of " + methodDescription + " to " + methodBinding3 + " or " + methodBinding4);
                }
                throw new IllegalStateException("Unexpected amount of targets: " + list);
            }
        }

        public enum Unique implements BindingResolver {
            INSTANCE;

            private static final int ONLY = 0;

            @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.BindingResolver
            public MethodBinding resolve(AmbiguityResolver ambiguityResolver, MethodDescription methodDescription, List<MethodBinding> list) {
                if (list.size() == 1) {
                    return list.get(0);
                }
                throw new IllegalStateException(methodDescription + " allowed for more than one binding: " + list);
            }
        }

        public static class StreamWriting implements BindingResolver {
            private final BindingResolver delegate;
            private final PrintStream printStream;

            protected boolean canEqual(Object obj) {
                return obj instanceof StreamWriting;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof StreamWriting)) {
                    return false;
                }
                StreamWriting streamWriting = (StreamWriting) obj;
                if (!streamWriting.canEqual(this)) {
                    return false;
                }
                BindingResolver bindingResolver = this.delegate;
                BindingResolver bindingResolver2 = streamWriting.delegate;
                if (bindingResolver != null ? !bindingResolver.equals(bindingResolver2) : bindingResolver2 != null) {
                    return false;
                }
                PrintStream printStream = this.printStream;
                PrintStream printStream2 = streamWriting.printStream;
                return printStream != null ? printStream.equals(printStream2) : printStream2 == null;
            }

            public int hashCode() {
                BindingResolver bindingResolver = this.delegate;
                int iHashCode = bindingResolver == null ? 43 : bindingResolver.hashCode();
                PrintStream printStream = this.printStream;
                return ((iHashCode + 59) * 59) + (printStream != null ? printStream.hashCode() : 43);
            }

            public StreamWriting(BindingResolver bindingResolver, PrintStream printStream) {
                this.delegate = bindingResolver;
                this.printStream = printStream;
            }

            public static BindingResolver toSystemOut() {
                return toSystemOut(Default.INSTANCE);
            }

            public static BindingResolver toSystemOut(BindingResolver bindingResolver) {
                return new StreamWriting(bindingResolver, System.out);
            }

            public static BindingResolver toSystemError() {
                return toSystemError(Default.INSTANCE);
            }

            public static BindingResolver toSystemError(BindingResolver bindingResolver) {
                return new StreamWriting(bindingResolver, System.err);
            }

            @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.BindingResolver
            public MethodBinding resolve(AmbiguityResolver ambiguityResolver, MethodDescription methodDescription, List<MethodBinding> list) {
                MethodBinding methodBindingResolve = this.delegate.resolve(ambiguityResolver, methodDescription, list);
                this.printStream.println("Binding " + methodDescription + " as delegation to " + methodBindingResolve.getTarget());
                return methodBindingResolve;
            }
        }
    }

    /* JADX INFO: renamed from: net.bytebuddy.implementation.bind.MethodDelegationBinder$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$net$bytebuddy$implementation$bind$MethodDelegationBinder$AmbiguityResolver$Resolution;

        static {
            int[] iArr = new int[AmbiguityResolver.Resolution.values().length];
            $SwitchMap$net$bytebuddy$implementation$bind$MethodDelegationBinder$AmbiguityResolver$Resolution = iArr;
            try {
                iArr[AmbiguityResolver.Resolution.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$net$bytebuddy$implementation$bind$MethodDelegationBinder$AmbiguityResolver$Resolution[AmbiguityResolver.Resolution.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$net$bytebuddy$implementation$bind$MethodDelegationBinder$AmbiguityResolver$Resolution[AmbiguityResolver.Resolution.AMBIGUOUS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$net$bytebuddy$implementation$bind$MethodDelegationBinder$AmbiguityResolver$Resolution[AmbiguityResolver.Resolution.UNKNOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public interface AmbiguityResolver {
        public static final AmbiguityResolver DEFAULT = new Compound(BindingPriority.Resolver.INSTANCE, DeclaringTypeResolver.INSTANCE, ArgumentTypeResolver.INSTANCE, MethodNameEqualityResolver.INSTANCE, ParameterLengthResolver.INSTANCE);

        Resolution resolve(MethodDescription methodDescription, MethodBinding methodBinding, MethodBinding methodBinding2);

        public enum Resolution {
            UNKNOWN(true),
            LEFT(false),
            RIGHT(false),
            AMBIGUOUS(true);

            private final boolean unresolved;

            Resolution(boolean z) {
                this.unresolved = z;
            }

            public boolean isUnresolved() {
                return this.unresolved;
            }

            public Resolution merge(Resolution resolution) {
                int i = AnonymousClass1.$SwitchMap$net$bytebuddy$implementation$bind$MethodDelegationBinder$AmbiguityResolver$Resolution[ordinal()];
                if (i == 1 || i == 2) {
                    return (resolution == UNKNOWN || resolution == this) ? this : AMBIGUOUS;
                }
                if (i == 3) {
                    return AMBIGUOUS;
                }
                if (i == 4) {
                    return resolution;
                }
                throw new AssertionError();
            }
        }

        public enum NoOp implements AmbiguityResolver {
            INSTANCE;

            @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.AmbiguityResolver
            public Resolution resolve(MethodDescription methodDescription, MethodBinding methodBinding, MethodBinding methodBinding2) {
                return Resolution.UNKNOWN;
            }
        }

        public enum Directional implements AmbiguityResolver {
            LEFT(true),
            RIGHT(false);

            private final boolean left;

            Directional(boolean z) {
                this.left = z;
            }

            @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.AmbiguityResolver
            public Resolution resolve(MethodDescription methodDescription, MethodBinding methodBinding, MethodBinding methodBinding2) {
                return this.left ? Resolution.LEFT : Resolution.RIGHT;
            }
        }

        public static class Compound implements AmbiguityResolver {
            private final List<AmbiguityResolver> ambiguityResolvers;

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
                List<AmbiguityResolver> list = this.ambiguityResolvers;
                List<AmbiguityResolver> list2 = compound.ambiguityResolvers;
                return list != null ? list.equals(list2) : list2 == null;
            }

            public int hashCode() {
                List<AmbiguityResolver> list = this.ambiguityResolvers;
                return 59 + (list == null ? 43 : list.hashCode());
            }

            public Compound(AmbiguityResolver... ambiguityResolverArr) {
                this((List<? extends AmbiguityResolver>) Arrays.asList(ambiguityResolverArr));
            }

            public Compound(List<? extends AmbiguityResolver> list) {
                this.ambiguityResolvers = new ArrayList();
                for (AmbiguityResolver ambiguityResolver : list) {
                    if (ambiguityResolver instanceof Compound) {
                        this.ambiguityResolvers.addAll(((Compound) ambiguityResolver).ambiguityResolvers);
                    } else if (!(ambiguityResolver instanceof NoOp)) {
                        this.ambiguityResolvers.add(ambiguityResolver);
                    }
                }
            }

            @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.AmbiguityResolver
            public Resolution resolve(MethodDescription methodDescription, MethodBinding methodBinding, MethodBinding methodBinding2) {
                Resolution resolutionResolve = Resolution.UNKNOWN;
                Iterator<AmbiguityResolver> it = this.ambiguityResolvers.iterator();
                while (resolutionResolve.isUnresolved() && it.hasNext()) {
                    resolutionResolve = it.next().resolve(methodDescription, methodBinding, methodBinding2);
                }
                return resolutionResolve;
            }
        }
    }

    public interface TerminationHandler {
        StackManipulation resolve(Assigner assigner, Assigner.Typing typing, MethodDescription methodDescription, MethodDescription methodDescription2);

        public enum Default implements TerminationHandler {
            RETURNING { // from class: net.bytebuddy.implementation.bind.MethodDelegationBinder.TerminationHandler.Default.1
                @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.TerminationHandler
                public StackManipulation resolve(Assigner assigner, Assigner.Typing typing, MethodDescription methodDescription, MethodDescription methodDescription2) {
                    TypeDescription.Generic returnType;
                    StackManipulation[] stackManipulationArr = new StackManipulation[2];
                    if (methodDescription2.isConstructor()) {
                        returnType = methodDescription2.getDeclaringType().asGenericType();
                    } else {
                        returnType = methodDescription2.getReturnType();
                    }
                    stackManipulationArr[0] = assigner.assign(returnType, methodDescription.getReturnType(), typing);
                    stackManipulationArr[1] = MethodReturn.of(methodDescription.getReturnType());
                    return new StackManipulation.Compound(stackManipulationArr);
                }
            },
            DROPPING { // from class: net.bytebuddy.implementation.bind.MethodDelegationBinder.TerminationHandler.Default.2
                @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.TerminationHandler
                public StackManipulation resolve(Assigner assigner, Assigner.Typing typing, MethodDescription methodDescription, MethodDescription methodDescription2) {
                    TypeDefinition returnType;
                    if (methodDescription2.isConstructor()) {
                        returnType = methodDescription2.getDeclaringType();
                    } else {
                        returnType = methodDescription2.getReturnType();
                    }
                    return Removal.of(returnType);
                }
            };

            /* synthetic */ Default(AnonymousClass1 anonymousClass1) {
                this();
            }
        }
    }

    public static class Processor implements Record {
        private final AmbiguityResolver ambiguityResolver;
        private final BindingResolver bindingResolver;
        private final List<? extends Record> records;

        protected boolean canEqual(Object obj) {
            return obj instanceof Processor;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Processor)) {
                return false;
            }
            Processor processor = (Processor) obj;
            if (!processor.canEqual(this)) {
                return false;
            }
            List<? extends Record> list = this.records;
            List<? extends Record> list2 = processor.records;
            if (list != null ? !list.equals(list2) : list2 != null) {
                return false;
            }
            AmbiguityResolver ambiguityResolver = this.ambiguityResolver;
            AmbiguityResolver ambiguityResolver2 = processor.ambiguityResolver;
            if (ambiguityResolver != null ? !ambiguityResolver.equals(ambiguityResolver2) : ambiguityResolver2 != null) {
                return false;
            }
            BindingResolver bindingResolver = this.bindingResolver;
            BindingResolver bindingResolver2 = processor.bindingResolver;
            return bindingResolver != null ? bindingResolver.equals(bindingResolver2) : bindingResolver2 == null;
        }

        public int hashCode() {
            List<? extends Record> list = this.records;
            int iHashCode = list == null ? 43 : list.hashCode();
            AmbiguityResolver ambiguityResolver = this.ambiguityResolver;
            int iHashCode2 = ((iHashCode + 59) * 59) + (ambiguityResolver == null ? 43 : ambiguityResolver.hashCode());
            BindingResolver bindingResolver = this.bindingResolver;
            return (iHashCode2 * 59) + (bindingResolver != null ? bindingResolver.hashCode() : 43);
        }

        public Processor(List<? extends Record> list, AmbiguityResolver ambiguityResolver, BindingResolver bindingResolver) {
            this.records = list;
            this.ambiguityResolver = ambiguityResolver;
            this.bindingResolver = bindingResolver;
        }

        @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.Record
        public MethodBinding bind(Implementation.Target target, MethodDescription methodDescription, TerminationHandler terminationHandler, MethodInvoker methodInvoker, Assigner assigner) {
            ArrayList arrayList = new ArrayList();
            Iterator<? extends Record> it = this.records.iterator();
            while (it.hasNext()) {
                MethodBinding methodBindingBind = it.next().bind(target, methodDescription, terminationHandler, methodInvoker, assigner);
                if (methodBindingBind.isValid()) {
                    arrayList.add(methodBindingBind);
                }
            }
            if (arrayList.isEmpty()) {
                throw new IllegalArgumentException("None of " + this.records + " allows for delegation from " + methodDescription);
            }
            return this.bindingResolver.resolve(this.ambiguityResolver, methodDescription, arrayList);
        }
    }
}
