package net.bytebuddy.implementation;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.Removal;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.MethodVisitor;

/* JADX INFO: loaded from: classes3.dex */
public enum SuperMethodCall implements Implementation.Composable {
    INSTANCE;

    @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
    public InstrumentedType prepare(InstrumentedType instrumentedType) {
        return instrumentedType;
    }

    @Override // net.bytebuddy.implementation.Implementation
    public ByteCodeAppender appender(Implementation.Target target) {
        return new Appender(target, Appender.TerminationHandler.RETURNING);
    }

    @Override // net.bytebuddy.implementation.Implementation.Composable
    public Implementation andThen(Implementation implementation) {
        return new Implementation.Compound(WithoutReturn.INSTANCE, implementation);
    }

    protected enum WithoutReturn implements Implementation {
        INSTANCE;

        @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            return instrumentedType;
        }

        @Override // net.bytebuddy.implementation.Implementation
        public ByteCodeAppender appender(Implementation.Target target) {
            return new Appender(target, Appender.TerminationHandler.DROPPING);
        }
    }

    protected static class Appender implements ByteCodeAppender {
        private final Implementation.Target implementationTarget;
        private final TerminationHandler terminationHandler;

        protected enum TerminationHandler {
            RETURNING { // from class: net.bytebuddy.implementation.SuperMethodCall.Appender.TerminationHandler.1
                @Override // net.bytebuddy.implementation.SuperMethodCall.Appender.TerminationHandler
                protected StackManipulation of(MethodDescription methodDescription) {
                    return MethodReturn.of(methodDescription.getReturnType());
                }
            },
            DROPPING { // from class: net.bytebuddy.implementation.SuperMethodCall.Appender.TerminationHandler.2
                @Override // net.bytebuddy.implementation.SuperMethodCall.Appender.TerminationHandler
                protected StackManipulation of(MethodDescription methodDescription) {
                    return Removal.of(methodDescription.getReturnType());
                }
            };

            protected abstract StackManipulation of(MethodDescription methodDescription);
        }

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
            TerminationHandler terminationHandler = this.terminationHandler;
            TerminationHandler terminationHandler2 = appender.terminationHandler;
            return terminationHandler != null ? terminationHandler.equals(terminationHandler2) : terminationHandler2 == null;
        }

        public int hashCode() {
            Implementation.Target target = this.implementationTarget;
            int iHashCode = target == null ? 43 : target.hashCode();
            TerminationHandler terminationHandler = this.terminationHandler;
            return ((iHashCode + 59) * 59) + (terminationHandler != null ? terminationHandler.hashCode() : 43);
        }

        protected Appender(Implementation.Target target, TerminationHandler terminationHandler) {
            this.implementationTarget = target;
            this.terminationHandler = terminationHandler;
        }

        @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            Implementation.SpecialMethodInvocation specialMethodInvocationInvokeDominant = this.implementationTarget.invokeDominant(methodDescription.asSignatureToken());
            if (!specialMethodInvocationInvokeDominant.isValid()) {
                throw new IllegalStateException("Cannot call super (or default) method for " + methodDescription);
            }
            return new ByteCodeAppender.Size(new StackManipulation.Compound(MethodVariableAccess.allArgumentsOf(methodDescription).prependThisReference(), specialMethodInvocationInvokeDominant, this.terminationHandler.of(methodDescription)).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
        }
    }
}
