package net.bytebuddy.implementation;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.Duplication;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.Throw;
import net.bytebuddy.implementation.bytecode.TypeCreation;
import net.bytebuddy.implementation.bytecode.constant.TextConstant;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.matcher.ElementMatchers;

/* JADX INFO: loaded from: classes3.dex */
public class ExceptionMethod implements Implementation, ByteCodeAppender {
    private final ConstructionDelegate constructionDelegate;
    private final TypeDescription throwableType;

    @Override // net.bytebuddy.implementation.Implementation
    public ByteCodeAppender appender(Implementation.Target target) {
        return this;
    }

    @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
    public InstrumentedType prepare(InstrumentedType instrumentedType) {
        return instrumentedType;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof ExceptionMethod;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ExceptionMethod)) {
            return false;
        }
        ExceptionMethod exceptionMethod = (ExceptionMethod) obj;
        if (!exceptionMethod.canEqual(this)) {
            return false;
        }
        TypeDescription typeDescription = this.throwableType;
        TypeDescription typeDescription2 = exceptionMethod.throwableType;
        if (typeDescription != null ? !typeDescription.equals(typeDescription2) : typeDescription2 != null) {
            return false;
        }
        ConstructionDelegate constructionDelegate = this.constructionDelegate;
        ConstructionDelegate constructionDelegate2 = exceptionMethod.constructionDelegate;
        return constructionDelegate != null ? constructionDelegate.equals(constructionDelegate2) : constructionDelegate2 == null;
    }

    public int hashCode() {
        TypeDescription typeDescription = this.throwableType;
        int iHashCode = typeDescription == null ? 43 : typeDescription.hashCode();
        ConstructionDelegate constructionDelegate = this.constructionDelegate;
        return ((iHashCode + 59) * 59) + (constructionDelegate != null ? constructionDelegate.hashCode() : 43);
    }

    public ExceptionMethod(TypeDescription typeDescription, ConstructionDelegate constructionDelegate) {
        this.throwableType = typeDescription;
        this.constructionDelegate = constructionDelegate;
    }

    public static Implementation throwing(Class<? extends Throwable> cls) {
        return throwing(new TypeDescription.ForLoadedType(cls));
    }

    public static Implementation throwing(TypeDescription typeDescription) {
        if (!typeDescription.isAssignableTo(Throwable.class)) {
            throw new IllegalArgumentException(typeDescription + " does not extend throwable");
        }
        return new ExceptionMethod(typeDescription, new ConstructionDelegate.ForDefaultConstructor(typeDescription));
    }

    public static Implementation throwing(Class<? extends Throwable> cls, String str) {
        return throwing(new TypeDescription.ForLoadedType(cls), str);
    }

    public static Implementation throwing(TypeDescription typeDescription, String str) {
        if (!typeDescription.isAssignableTo(Throwable.class)) {
            throw new IllegalArgumentException(typeDescription + " does not extend throwable");
        }
        return new ExceptionMethod(typeDescription, new ConstructionDelegate.ForStringConstructor(typeDescription, str));
    }

    @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
    public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
        return new ByteCodeAppender.Size(new StackManipulation.Compound(this.constructionDelegate.make(), Throw.INSTANCE).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
    }

    public interface ConstructionDelegate {
        StackManipulation make();

        public static class ForDefaultConstructor implements ConstructionDelegate {
            private final TypeDescription exceptionType;
            private final MethodDescription targetConstructor;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForDefaultConstructor;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForDefaultConstructor)) {
                    return false;
                }
                ForDefaultConstructor forDefaultConstructor = (ForDefaultConstructor) obj;
                if (!forDefaultConstructor.canEqual(this)) {
                    return false;
                }
                TypeDescription typeDescription = this.exceptionType;
                TypeDescription typeDescription2 = forDefaultConstructor.exceptionType;
                if (typeDescription != null ? !typeDescription.equals(typeDescription2) : typeDescription2 != null) {
                    return false;
                }
                MethodDescription methodDescription = this.targetConstructor;
                MethodDescription methodDescription2 = forDefaultConstructor.targetConstructor;
                return methodDescription != null ? methodDescription.equals(methodDescription2) : methodDescription2 == null;
            }

            public int hashCode() {
                TypeDescription typeDescription = this.exceptionType;
                int iHashCode = typeDescription == null ? 43 : typeDescription.hashCode();
                MethodDescription methodDescription = this.targetConstructor;
                return ((iHashCode + 59) * 59) + (methodDescription != null ? methodDescription.hashCode() : 43);
            }

            public ForDefaultConstructor(TypeDescription typeDescription) {
                this.exceptionType = typeDescription;
                this.targetConstructor = (MethodDescription) typeDescription.getDeclaredMethods().filter(ElementMatchers.isConstructor().and(ElementMatchers.takesArguments(0))).getOnly();
            }

            @Override // net.bytebuddy.implementation.ExceptionMethod.ConstructionDelegate
            public StackManipulation make() {
                return new StackManipulation.Compound(TypeCreation.of(this.exceptionType), Duplication.SINGLE, MethodInvocation.invoke(this.targetConstructor));
            }
        }

        public static class ForStringConstructor implements ConstructionDelegate {
            private final TypeDescription exceptionType;
            private final String message;
            private final MethodDescription targetConstructor;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForStringConstructor;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForStringConstructor)) {
                    return false;
                }
                ForStringConstructor forStringConstructor = (ForStringConstructor) obj;
                if (!forStringConstructor.canEqual(this)) {
                    return false;
                }
                TypeDescription typeDescription = this.exceptionType;
                TypeDescription typeDescription2 = forStringConstructor.exceptionType;
                if (typeDescription != null ? !typeDescription.equals(typeDescription2) : typeDescription2 != null) {
                    return false;
                }
                MethodDescription methodDescription = this.targetConstructor;
                MethodDescription methodDescription2 = forStringConstructor.targetConstructor;
                if (methodDescription != null ? !methodDescription.equals(methodDescription2) : methodDescription2 != null) {
                    return false;
                }
                String str = this.message;
                String str2 = forStringConstructor.message;
                return str != null ? str.equals(str2) : str2 == null;
            }

            public int hashCode() {
                TypeDescription typeDescription = this.exceptionType;
                int iHashCode = typeDescription == null ? 43 : typeDescription.hashCode();
                MethodDescription methodDescription = this.targetConstructor;
                int iHashCode2 = ((iHashCode + 59) * 59) + (methodDescription == null ? 43 : methodDescription.hashCode());
                String str = this.message;
                return (iHashCode2 * 59) + (str != null ? str.hashCode() : 43);
            }

            public ForStringConstructor(TypeDescription typeDescription, String str) {
                this.exceptionType = typeDescription;
                this.targetConstructor = (MethodDescription) typeDescription.getDeclaredMethods().filter(ElementMatchers.isConstructor().and(ElementMatchers.takesArguments((Class<?>[]) new Class[]{String.class}))).getOnly();
                this.message = str;
            }

            @Override // net.bytebuddy.implementation.ExceptionMethod.ConstructionDelegate
            public StackManipulation make() {
                return new StackManipulation.Compound(TypeCreation.of(this.exceptionType), Duplication.SINGLE, new TextConstant(this.message), MethodInvocation.invoke(this.targetConstructor));
            }
        }
    }
}
