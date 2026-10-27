package net.bytebuddy.implementation;

import java.lang.reflect.InvocationHandler;
import java.util.ArrayList;
import java.util.List;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.dynamic.scaffold.FieldLocator;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.LoadedTypeInitializer;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.collection.ArrayFactory;
import net.bytebuddy.implementation.bytecode.constant.MethodConstant;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.RandomString;

/* JADX INFO: loaded from: classes3.dex */
public abstract class InvocationHandlerAdapter implements Implementation {
    protected static final boolean CACHING = true;
    private static final TypeDescription.Generic INVOCATION_HANDLER_TYPE = new TypeDescription.Generic.OfNonGenericType.ForLoadedType(InvocationHandler.class);
    private static final boolean NO_CACHING = false;
    protected final Assigner assigner;
    protected final boolean cacheMethods;
    protected final String fieldName;

    protected interface AssignerConfigurable extends Implementation {
        Implementation withAssigner(Assigner assigner);
    }

    public abstract AssignerConfigurable withoutMethodCache();

    protected boolean canEqual(Object obj) {
        return obj instanceof InvocationHandlerAdapter;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof InvocationHandlerAdapter)) {
            return false;
        }
        InvocationHandlerAdapter invocationHandlerAdapter = (InvocationHandlerAdapter) obj;
        if (!invocationHandlerAdapter.canEqual(this)) {
            return false;
        }
        String str = this.fieldName;
        String str2 = invocationHandlerAdapter.fieldName;
        if (str != null ? !str.equals(str2) : str2 != null) {
            return false;
        }
        Assigner assigner = this.assigner;
        Assigner assigner2 = invocationHandlerAdapter.assigner;
        if (assigner != null ? assigner.equals(assigner2) : assigner2 == null) {
            return this.cacheMethods == invocationHandlerAdapter.cacheMethods;
        }
        return false;
    }

    public int hashCode() {
        String str = this.fieldName;
        int iHashCode = str == null ? 43 : str.hashCode();
        Assigner assigner = this.assigner;
        return ((((iHashCode + 59) * 59) + (assigner != null ? assigner.hashCode() : 43)) * 59) + (this.cacheMethods ? 79 : 97);
    }

    protected InvocationHandlerAdapter(String str, boolean z, Assigner assigner) {
        this.fieldName = str;
        this.cacheMethods = z;
        this.assigner = assigner;
    }

    public static InvocationHandlerAdapter of(InvocationHandler invocationHandler) {
        return of(invocationHandler, String.format("%s$%s", "invocationHandler", RandomString.hashOf(invocationHandler.hashCode())));
    }

    public static InvocationHandlerAdapter of(InvocationHandler invocationHandler, String str) {
        return new ForInstance(str, true, Assigner.DEFAULT, invocationHandler);
    }

    public static InvocationHandlerAdapter toField(String str) {
        return toField(str, FieldLocator.ForClassHierarchy.Factory.INSTANCE);
    }

    public static InvocationHandlerAdapter toField(String str, FieldLocator.Factory factory) {
        return new ForField(str, true, Assigner.DEFAULT, factory);
    }

    private List<StackManipulation> argumentValuesOf(MethodDescription methodDescription) {
        TypeList.Generic<TypeDescription.Generic> genericAsTypeList = methodDescription.getParameters().asTypeList();
        ArrayList arrayList = new ArrayList(genericAsTypeList.size());
        int size = 1;
        for (TypeDescription.Generic generic : genericAsTypeList) {
            arrayList.add(new StackManipulation.Compound(MethodVariableAccess.of(generic).loadFrom(size), this.assigner.assign(generic, TypeDescription.Generic.OBJECT, Assigner.Typing.STATIC)));
            size += generic.getStackSize().getSize();
        }
        return arrayList;
    }

    protected ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription, StackManipulation stackManipulation, FieldDescription fieldDescription) {
        StackManipulation stackManipulationForMethod;
        if (methodDescription.isStatic()) {
            throw new IllegalStateException("It is not possible to apply an invocation handler onto the static method " + methodDescription);
        }
        StackManipulation[] stackManipulationArr = new StackManipulation[8];
        stackManipulationArr[0] = stackManipulation;
        stackManipulationArr[1] = FieldAccess.forField(fieldDescription).read();
        stackManipulationArr[2] = MethodVariableAccess.loadThis();
        if (this.cacheMethods) {
            stackManipulationForMethod = MethodConstant.forMethod(methodDescription.asDefined()).cached();
        } else {
            stackManipulationForMethod = MethodConstant.forMethod(methodDescription.asDefined());
        }
        stackManipulationArr[3] = stackManipulationForMethod;
        stackManipulationArr[4] = ArrayFactory.forType(TypeDescription.Generic.OBJECT).withValues(argumentValuesOf(methodDescription));
        stackManipulationArr[5] = MethodInvocation.invoke(INVOCATION_HANDLER_TYPE.getDeclaredMethods().getOnly());
        stackManipulationArr[6] = this.assigner.assign(TypeDescription.Generic.OBJECT, methodDescription.getReturnType(), Assigner.Typing.DYNAMIC);
        stackManipulationArr[7] = MethodReturn.of(methodDescription.getReturnType());
        return new ByteCodeAppender.Size(new StackManipulation.Compound(stackManipulationArr).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
    }

    protected static class ForInstance extends InvocationHandlerAdapter implements AssignerConfigurable {
        private static final String PREFIX = "invocationHandler";
        protected final InvocationHandler invocationHandler;

        @Override // net.bytebuddy.implementation.InvocationHandlerAdapter
        protected boolean canEqual(Object obj) {
            return obj instanceof ForInstance;
        }

        @Override // net.bytebuddy.implementation.InvocationHandlerAdapter
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ForInstance)) {
                return false;
            }
            ForInstance forInstance = (ForInstance) obj;
            if (!forInstance.canEqual(this) || !super.equals(obj)) {
                return false;
            }
            InvocationHandler invocationHandler = this.invocationHandler;
            InvocationHandler invocationHandler2 = forInstance.invocationHandler;
            return invocationHandler != null ? invocationHandler.equals(invocationHandler2) : invocationHandler2 == null;
        }

        @Override // net.bytebuddy.implementation.InvocationHandlerAdapter
        public int hashCode() {
            int iHashCode = super.hashCode() + 59;
            InvocationHandler invocationHandler = this.invocationHandler;
            return (iHashCode * 59) + (invocationHandler == null ? 43 : invocationHandler.hashCode());
        }

        protected ForInstance(String str, boolean z, Assigner assigner, InvocationHandler invocationHandler) {
            super(str, z, assigner);
            this.invocationHandler = invocationHandler;
        }

        @Override // net.bytebuddy.implementation.InvocationHandlerAdapter
        public AssignerConfigurable withoutMethodCache() {
            return new ForInstance(this.fieldName, false, this.assigner, this.invocationHandler);
        }

        @Override // net.bytebuddy.implementation.InvocationHandlerAdapter.AssignerConfigurable
        public Implementation withAssigner(Assigner assigner) {
            return new ForInstance(this.fieldName, this.cacheMethods, assigner, this.invocationHandler);
        }

        @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            return instrumentedType.withField(new FieldDescription.Token(this.fieldName, 4105, InvocationHandlerAdapter.INVOCATION_HANDLER_TYPE)).withInitializer(new LoadedTypeInitializer.ForStaticField(this.fieldName, this.invocationHandler));
        }

        @Override // net.bytebuddy.implementation.Implementation
        public ByteCodeAppender appender(Implementation.Target target) {
            return new Appender(target.getInstrumentedType());
        }

        protected class Appender implements ByteCodeAppender {
            private final TypeDescription instrumentedType;

            protected Appender(TypeDescription typeDescription) {
                this.instrumentedType = typeDescription;
            }

            @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
            public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                return ForInstance.this.apply(methodVisitor, context, methodDescription, StackManipulation.Trivial.INSTANCE, (FieldDescription) this.instrumentedType.getDeclaredFields().filter(ElementMatchers.named(ForInstance.this.fieldName).and(ElementMatchers.genericFieldType(InvocationHandlerAdapter.INVOCATION_HANDLER_TYPE))).getOnly());
            }

            private InvocationHandlerAdapter getInvocationHandlerAdapter() {
                return ForInstance.this;
            }

            public boolean equals(Object obj) {
                if (this != obj) {
                    if (obj != null && getClass() == obj.getClass()) {
                        Appender appender = (Appender) obj;
                        if (!this.instrumentedType.equals(appender.instrumentedType) || !ForInstance.this.equals(appender.getInvocationHandlerAdapter())) {
                        }
                    }
                    return false;
                }
                return true;
            }

            public int hashCode() {
                return (ForInstance.this.hashCode() * 31) + this.instrumentedType.hashCode();
            }
        }
    }

    protected static class ForField extends InvocationHandlerAdapter implements AssignerConfigurable {
        private final FieldLocator.Factory fieldLocatorFactory;

        @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            return instrumentedType;
        }

        @Override // net.bytebuddy.implementation.InvocationHandlerAdapter
        protected boolean canEqual(Object obj) {
            return obj instanceof ForField;
        }

        @Override // net.bytebuddy.implementation.InvocationHandlerAdapter
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ForField)) {
                return false;
            }
            ForField forField = (ForField) obj;
            if (!forField.canEqual(this) || !super.equals(obj)) {
                return false;
            }
            FieldLocator.Factory factory = this.fieldLocatorFactory;
            FieldLocator.Factory factory2 = forField.fieldLocatorFactory;
            return factory != null ? factory.equals(factory2) : factory2 == null;
        }

        @Override // net.bytebuddy.implementation.InvocationHandlerAdapter
        public int hashCode() {
            int iHashCode = super.hashCode() + 59;
            FieldLocator.Factory factory = this.fieldLocatorFactory;
            return (iHashCode * 59) + (factory == null ? 43 : factory.hashCode());
        }

        protected ForField(String str, boolean z, Assigner assigner, FieldLocator.Factory factory) {
            super(str, z, assigner);
            this.fieldLocatorFactory = factory;
        }

        @Override // net.bytebuddy.implementation.InvocationHandlerAdapter
        public AssignerConfigurable withoutMethodCache() {
            return new ForField(this.fieldName, false, this.assigner, this.fieldLocatorFactory);
        }

        @Override // net.bytebuddy.implementation.InvocationHandlerAdapter.AssignerConfigurable
        public Implementation withAssigner(Assigner assigner) {
            return new ForField(this.fieldName, this.cacheMethods, assigner, this.fieldLocatorFactory);
        }

        @Override // net.bytebuddy.implementation.Implementation
        public ByteCodeAppender appender(Implementation.Target target) {
            FieldLocator.Resolution resolutionLocate = this.fieldLocatorFactory.make(target.getInstrumentedType()).locate(this.fieldName);
            if (!resolutionLocate.isResolved()) {
                throw new IllegalStateException("Could not find a field named '" + this.fieldName + "' for " + target.getInstrumentedType());
            }
            if (!resolutionLocate.getField().getType().asErasure().isAssignableTo(InvocationHandler.class)) {
                throw new IllegalStateException("Field " + resolutionLocate.getField() + " does not declare a type that is assignable to invocation handler");
            }
            return new Appender(target.getInstrumentedType(), resolutionLocate.getField());
        }

        protected class Appender implements ByteCodeAppender {
            private final FieldDescription fieldDescription;
            private final TypeDescription instrumentedType;

            protected Appender(TypeDescription typeDescription, FieldDescription fieldDescription) {
                this.instrumentedType = typeDescription;
                this.fieldDescription = fieldDescription;
            }

            @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
            public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                return ForField.this.apply(methodVisitor, context, methodDescription, this.fieldDescription.isStatic() ? StackManipulation.Trivial.INSTANCE : MethodVariableAccess.loadThis(), this.fieldDescription);
            }

            public boolean equals(Object obj) {
                if (this != obj) {
                    if (obj != null && getClass() == obj.getClass()) {
                        Appender appender = (Appender) obj;
                        if (!this.instrumentedType.equals(appender.instrumentedType) || !this.fieldDescription.equals(appender.fieldDescription) || !ForField.this.equals(appender.getInvocationHandlerAdapter())) {
                        }
                    }
                    return false;
                }
                return true;
            }

            private InvocationHandlerAdapter getInvocationHandlerAdapter() {
                return ForField.this;
            }

            public int hashCode() {
                return (((ForField.this.hashCode() * 31) + this.instrumentedType.hashCode()) * 31) + this.fieldDescription.hashCode();
            }
        }
    }
}
