package net.bytebuddy.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.MethodVisitor;

/* JADX INFO: loaded from: classes3.dex */
public class DefaultMethodCall implements Implementation {
    private final List<TypeDescription> prioritizedInterfaces;

    @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
    public InstrumentedType prepare(InstrumentedType instrumentedType) {
        return instrumentedType;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof DefaultMethodCall;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DefaultMethodCall)) {
            return false;
        }
        DefaultMethodCall defaultMethodCall = (DefaultMethodCall) obj;
        if (!defaultMethodCall.canEqual(this)) {
            return false;
        }
        List<TypeDescription> list = this.prioritizedInterfaces;
        List<TypeDescription> list2 = defaultMethodCall.prioritizedInterfaces;
        return list != null ? list.equals(list2) : list2 == null;
    }

    public int hashCode() {
        List<TypeDescription> list = this.prioritizedInterfaces;
        return 59 + (list == null ? 43 : list.hashCode());
    }

    protected DefaultMethodCall(List<TypeDescription> list) {
        this.prioritizedInterfaces = list;
    }

    public static Implementation prioritize(Class<?>... clsArr) {
        return prioritize((Collection<? extends TypeDescription>) new TypeList.ForLoadedTypes(clsArr));
    }

    public static Implementation prioritize(Iterable<? extends Class<?>> iterable) {
        ArrayList arrayList = new ArrayList();
        Iterator<? extends Class<?>> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return prioritize((Collection<? extends TypeDescription>) new TypeList.ForLoadedTypes(arrayList));
    }

    public static Implementation prioritize(TypeDescription... typeDescriptionArr) {
        return prioritize((Collection<? extends TypeDescription>) Arrays.asList(typeDescriptionArr));
    }

    public static Implementation prioritize(Collection<? extends TypeDescription> collection) {
        return new DefaultMethodCall(new ArrayList(collection));
    }

    public static Implementation unambiguousOnly() {
        return new DefaultMethodCall(Collections.emptyList());
    }

    @Override // net.bytebuddy.implementation.Implementation
    public ByteCodeAppender appender(Implementation.Target target) {
        return new Appender(target, filterRelevant(target.getInstrumentedType()));
    }

    private List<TypeDescription> filterRelevant(TypeDescription typeDescription) {
        ArrayList arrayList = new ArrayList(this.prioritizedInterfaces.size());
        HashSet hashSet = new HashSet(typeDescription.getInterfaces().asErasures());
        for (TypeDescription typeDescription2 : this.prioritizedInterfaces) {
            if (hashSet.remove(typeDescription2)) {
                arrayList.add(typeDescription2);
            }
        }
        return arrayList;
    }

    protected static class Appender implements ByteCodeAppender {
        private final Implementation.Target implementationTarget;
        private final Set<TypeDescription> nonPrioritizedInterfaces;
        private final List<TypeDescription> prioritizedInterfaces;

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
            List<TypeDescription> list = this.prioritizedInterfaces;
            List<TypeDescription> list2 = appender.prioritizedInterfaces;
            return list != null ? list.equals(list2) : list2 == null;
        }

        public int hashCode() {
            Implementation.Target target = this.implementationTarget;
            int iHashCode = target == null ? 43 : target.hashCode();
            List<TypeDescription> list = this.prioritizedInterfaces;
            return ((iHashCode + 59) * 59) + (list != null ? list.hashCode() : 43);
        }

        protected Appender(Implementation.Target target, List<TypeDescription> list) {
            this.implementationTarget = target;
            this.prioritizedInterfaces = list;
            HashSet hashSet = new HashSet(target.getInstrumentedType().getInterfaces().asErasures());
            this.nonPrioritizedInterfaces = hashSet;
            hashSet.removeAll(list);
        }

        @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            StackManipulation stackManipulationLocateDefault = locateDefault(methodDescription);
            if (!stackManipulationLocateDefault.isValid()) {
                throw new IllegalStateException("Cannot invoke default method on " + methodDescription);
            }
            return new ByteCodeAppender.Size(new StackManipulation.Compound(MethodVariableAccess.allArgumentsOf(methodDescription).prependThisReference(), stackManipulationLocateDefault, MethodReturn.of(methodDescription.getReturnType())).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
        }

        private StackManipulation locateDefault(MethodDescription methodDescription) {
            MethodDescription.SignatureToken signatureTokenAsSignatureToken = methodDescription.asSignatureToken();
            Implementation.SpecialMethodInvocation specialMethodInvocationInvokeDefault = Implementation.SpecialMethodInvocation.Illegal.INSTANCE;
            Iterator<TypeDescription> it = this.prioritizedInterfaces.iterator();
            while (it.hasNext()) {
                specialMethodInvocationInvokeDefault = this.implementationTarget.invokeDefault(signatureTokenAsSignatureToken, it.next());
                if (specialMethodInvocationInvokeDefault.isValid()) {
                    return specialMethodInvocationInvokeDefault;
                }
            }
            Iterator<TypeDescription> it2 = this.nonPrioritizedInterfaces.iterator();
            while (it2.hasNext()) {
                Implementation.SpecialMethodInvocation specialMethodInvocationInvokeDefault2 = this.implementationTarget.invokeDefault(signatureTokenAsSignatureToken, it2.next());
                if (specialMethodInvocationInvokeDefault.isValid() && specialMethodInvocationInvokeDefault2.isValid()) {
                    throw new IllegalStateException(methodDescription + " has an ambiguous default method with " + specialMethodInvocationInvokeDefault2.getMethodDescription() + " and " + specialMethodInvocationInvokeDefault.getMethodDescription());
                }
                specialMethodInvocationInvokeDefault = specialMethodInvocationInvokeDefault2;
            }
            return specialMethodInvocationInvokeDefault;
        }
    }
}
