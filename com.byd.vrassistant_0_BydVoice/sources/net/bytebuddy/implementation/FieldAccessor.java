package net.bytebuddy.implementation;

import java.lang.reflect.Field;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.scaffold.FieldLocator;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.MethodVisitor;

/* JADX INFO: loaded from: classes3.dex */
public abstract class FieldAccessor implements Implementation {
    protected final Assigner assigner;
    protected final FieldLocation fieldLocation;
    protected final Assigner.Typing typing;

    public interface AssignerConfigurable extends PropertyConfigurable {
        PropertyConfigurable withAssigner(Assigner assigner, Assigner.Typing typing);
    }

    public interface OwnerTypeLocatable extends AssignerConfigurable {
        AssignerConfigurable in(Class<?> cls);

        AssignerConfigurable in(TypeDescription typeDescription);

        AssignerConfigurable in(FieldLocator.Factory factory);
    }

    public interface PropertyConfigurable extends Implementation {
        Implementation.Composable setsArgumentAt(int i);
    }

    @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
    public InstrumentedType prepare(InstrumentedType instrumentedType) {
        return instrumentedType;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof FieldAccessor;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FieldAccessor)) {
            return false;
        }
        FieldAccessor fieldAccessor = (FieldAccessor) obj;
        if (!fieldAccessor.canEqual(this)) {
            return false;
        }
        FieldLocation fieldLocation = this.fieldLocation;
        FieldLocation fieldLocation2 = fieldAccessor.fieldLocation;
        if (fieldLocation != null ? !fieldLocation.equals(fieldLocation2) : fieldLocation2 != null) {
            return false;
        }
        Assigner assigner = this.assigner;
        Assigner assigner2 = fieldAccessor.assigner;
        if (assigner != null ? !assigner.equals(assigner2) : assigner2 != null) {
            return false;
        }
        Assigner.Typing typing = this.typing;
        Assigner.Typing typing2 = fieldAccessor.typing;
        return typing != null ? typing.equals(typing2) : typing2 == null;
    }

    public int hashCode() {
        FieldLocation fieldLocation = this.fieldLocation;
        int iHashCode = fieldLocation == null ? 43 : fieldLocation.hashCode();
        Assigner assigner = this.assigner;
        int iHashCode2 = ((iHashCode + 59) * 59) + (assigner == null ? 43 : assigner.hashCode());
        Assigner.Typing typing = this.typing;
        return (iHashCode2 * 59) + (typing != null ? typing.hashCode() : 43);
    }

    protected FieldAccessor(FieldLocation fieldLocation, Assigner assigner, Assigner.Typing typing) {
        this.fieldLocation = fieldLocation;
        this.assigner = assigner;
        this.typing = typing;
    }

    public static OwnerTypeLocatable ofField(String str) {
        return of(new FieldNameExtractor.ForFixedValue(str));
    }

    public static OwnerTypeLocatable ofBeanProperty() {
        return of(FieldNameExtractor.ForBeanProperty.INSTANCE);
    }

    public static OwnerTypeLocatable of(FieldNameExtractor fieldNameExtractor) {
        return new ForImplicitProperty(new FieldLocation.Relative(fieldNameExtractor));
    }

    public static AssignerConfigurable of(Field field) {
        return of(new FieldDescription.ForLoadedField(field));
    }

    public static AssignerConfigurable of(FieldDescription fieldDescription) {
        return new ForImplicitProperty(new FieldLocation.Absolute(fieldDescription));
    }

    protected StackManipulation getter(FieldDescription fieldDescription, MethodDescription methodDescription) {
        return access(fieldDescription, methodDescription, new StackManipulation.Compound(FieldAccess.forField(fieldDescription).read(), this.assigner.assign(fieldDescription.getType(), methodDescription.getReturnType(), this.typing)));
    }

    protected StackManipulation setter(FieldDescription fieldDescription, ParameterDescription parameterDescription) {
        if (fieldDescription.isFinal() && parameterDescription.getDeclaringMethod().isMethod()) {
            throw new IllegalArgumentException("Cannot set final field " + fieldDescription + " from " + parameterDescription.getDeclaringMethod());
        }
        return access(fieldDescription, parameterDescription.getDeclaringMethod(), new StackManipulation.Compound(MethodVariableAccess.load(parameterDescription), this.assigner.assign(parameterDescription.getType(), fieldDescription.getType(), this.typing), FieldAccess.forField(fieldDescription).write()));
    }

    private StackManipulation access(FieldDescription fieldDescription, MethodDescription methodDescription, StackManipulation stackManipulation) {
        if (!stackManipulation.isValid()) {
            throw new IllegalStateException("Incompatible type of " + fieldDescription + " and " + methodDescription);
        }
        if (methodDescription.isStatic() && !fieldDescription.isStatic()) {
            throw new IllegalArgumentException("Cannot call instance field " + fieldDescription + " from static method " + methodDescription);
        }
        StackManipulation[] stackManipulationArr = new StackManipulation[2];
        stackManipulationArr[0] = fieldDescription.isStatic() ? StackManipulation.Trivial.INSTANCE : MethodVariableAccess.loadThis();
        stackManipulationArr[1] = stackManipulation;
        return new StackManipulation.Compound(stackManipulationArr);
    }

    protected interface FieldLocation {

        public interface Prepared {
            FieldDescription resolve(MethodDescription methodDescription);
        }

        Prepared prepare(TypeDescription typeDescription);

        FieldLocation with(FieldLocator.Factory factory);

        public static class Absolute implements FieldLocation, Prepared {
            private final FieldDescription fieldDescription;

            protected boolean canEqual(Object obj) {
                return obj instanceof Absolute;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Absolute)) {
                    return false;
                }
                Absolute absolute = (Absolute) obj;
                if (!absolute.canEqual(this)) {
                    return false;
                }
                FieldDescription fieldDescription = this.fieldDescription;
                FieldDescription fieldDescription2 = absolute.fieldDescription;
                return fieldDescription != null ? fieldDescription.equals(fieldDescription2) : fieldDescription2 == null;
            }

            public int hashCode() {
                FieldDescription fieldDescription = this.fieldDescription;
                return 59 + (fieldDescription == null ? 43 : fieldDescription.hashCode());
            }

            protected Absolute(FieldDescription fieldDescription) {
                this.fieldDescription = fieldDescription;
            }

            @Override // net.bytebuddy.implementation.FieldAccessor.FieldLocation
            public FieldLocation with(FieldLocator.Factory factory) {
                throw new IllegalStateException("Cannot specify a field locator factory for an absolute field location");
            }

            @Override // net.bytebuddy.implementation.FieldAccessor.FieldLocation
            public Prepared prepare(TypeDescription typeDescription) {
                if (!typeDescription.isAssignableTo(this.fieldDescription.getDeclaringType().asErasure())) {
                    throw new IllegalStateException(this.fieldDescription + " is not declared by " + typeDescription);
                }
                if (this.fieldDescription.isVisibleTo(typeDescription)) {
                    return this;
                }
                throw new IllegalStateException("Cannot access " + this.fieldDescription + " from " + typeDescription);
            }

            @Override // net.bytebuddy.implementation.FieldAccessor.FieldLocation.Prepared
            public FieldDescription resolve(MethodDescription methodDescription) {
                return this.fieldDescription;
            }
        }

        public static class Relative implements FieldLocation {
            private final FieldLocator.Factory fieldLocatorFactory;
            private final FieldNameExtractor fieldNameExtractor;

            protected boolean canEqual(Object obj) {
                return obj instanceof Relative;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Relative)) {
                    return false;
                }
                Relative relative = (Relative) obj;
                if (!relative.canEqual(this)) {
                    return false;
                }
                FieldNameExtractor fieldNameExtractor = this.fieldNameExtractor;
                FieldNameExtractor fieldNameExtractor2 = relative.fieldNameExtractor;
                if (fieldNameExtractor != null ? !fieldNameExtractor.equals(fieldNameExtractor2) : fieldNameExtractor2 != null) {
                    return false;
                }
                FieldLocator.Factory factory = this.fieldLocatorFactory;
                FieldLocator.Factory factory2 = relative.fieldLocatorFactory;
                return factory != null ? factory.equals(factory2) : factory2 == null;
            }

            public int hashCode() {
                FieldNameExtractor fieldNameExtractor = this.fieldNameExtractor;
                int iHashCode = fieldNameExtractor == null ? 43 : fieldNameExtractor.hashCode();
                FieldLocator.Factory factory = this.fieldLocatorFactory;
                return ((iHashCode + 59) * 59) + (factory != null ? factory.hashCode() : 43);
            }

            protected Relative(FieldNameExtractor fieldNameExtractor) {
                this(fieldNameExtractor, FieldLocator.ForClassHierarchy.Factory.INSTANCE);
            }

            private Relative(FieldNameExtractor fieldNameExtractor, FieldLocator.Factory factory) {
                this.fieldNameExtractor = fieldNameExtractor;
                this.fieldLocatorFactory = factory;
            }

            @Override // net.bytebuddy.implementation.FieldAccessor.FieldLocation
            public FieldLocation with(FieldLocator.Factory factory) {
                return new Relative(this.fieldNameExtractor, factory);
            }

            @Override // net.bytebuddy.implementation.FieldAccessor.FieldLocation
            public Prepared prepare(TypeDescription typeDescription) {
                return new Prepared(this.fieldNameExtractor, this.fieldLocatorFactory.make(typeDescription));
            }

            protected static class Prepared implements Prepared {
                private final FieldLocator fieldLocator;
                private final FieldNameExtractor fieldNameExtractor;

                protected boolean canEqual(Object obj) {
                    return obj instanceof Prepared;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Prepared)) {
                        return false;
                    }
                    Prepared prepared = (Prepared) obj;
                    if (!prepared.canEqual(this)) {
                        return false;
                    }
                    FieldNameExtractor fieldNameExtractor = this.fieldNameExtractor;
                    FieldNameExtractor fieldNameExtractor2 = prepared.fieldNameExtractor;
                    if (fieldNameExtractor != null ? !fieldNameExtractor.equals(fieldNameExtractor2) : fieldNameExtractor2 != null) {
                        return false;
                    }
                    FieldLocator fieldLocator = this.fieldLocator;
                    FieldLocator fieldLocator2 = prepared.fieldLocator;
                    return fieldLocator != null ? fieldLocator.equals(fieldLocator2) : fieldLocator2 == null;
                }

                public int hashCode() {
                    FieldNameExtractor fieldNameExtractor = this.fieldNameExtractor;
                    int iHashCode = fieldNameExtractor == null ? 43 : fieldNameExtractor.hashCode();
                    FieldLocator fieldLocator = this.fieldLocator;
                    return ((iHashCode + 59) * 59) + (fieldLocator != null ? fieldLocator.hashCode() : 43);
                }

                protected Prepared(FieldNameExtractor fieldNameExtractor, FieldLocator fieldLocator) {
                    this.fieldNameExtractor = fieldNameExtractor;
                    this.fieldLocator = fieldLocator;
                }

                @Override // net.bytebuddy.implementation.FieldAccessor.FieldLocation.Prepared
                public FieldDescription resolve(MethodDescription methodDescription) {
                    FieldLocator.Resolution resolutionLocate = this.fieldLocator.locate(this.fieldNameExtractor.resolve(methodDescription));
                    if (!resolutionLocate.isResolved()) {
                        throw new IllegalStateException("Cannot resolve field for " + methodDescription + " using " + this.fieldLocator);
                    }
                    return resolutionLocate.getField();
                }
            }
        }
    }

    public interface FieldNameExtractor {
        String resolve(MethodDescription methodDescription);

        public enum ForBeanProperty implements FieldNameExtractor {
            INSTANCE;

            @Override // net.bytebuddy.implementation.FieldAccessor.FieldNameExtractor
            public String resolve(MethodDescription methodDescription) {
                int i;
                String internalName = methodDescription.getInternalName();
                if (internalName.startsWith("get") || internalName.startsWith("set")) {
                    i = 3;
                } else {
                    if (!internalName.startsWith("is")) {
                        throw new IllegalArgumentException(methodDescription + " does not follow Java bean naming conventions");
                    }
                    i = 2;
                }
                String strSubstring = internalName.substring(i);
                if (strSubstring.length() == 0) {
                    throw new IllegalArgumentException(methodDescription + " does not specify a bean name");
                }
                return Character.toLowerCase(strSubstring.charAt(0)) + strSubstring.substring(1);
            }
        }

        public static class ForFixedValue implements FieldNameExtractor {
            private final String name;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForFixedValue;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForFixedValue)) {
                    return false;
                }
                ForFixedValue forFixedValue = (ForFixedValue) obj;
                if (!forFixedValue.canEqual(this)) {
                    return false;
                }
                String str = this.name;
                String str2 = forFixedValue.name;
                return str != null ? str.equals(str2) : str2 == null;
            }

            public int hashCode() {
                String str = this.name;
                return 59 + (str == null ? 43 : str.hashCode());
            }

            protected ForFixedValue(String str) {
                this.name = str;
            }

            @Override // net.bytebuddy.implementation.FieldAccessor.FieldNameExtractor
            public String resolve(MethodDescription methodDescription) {
                return this.name;
            }
        }
    }

    protected static class ForImplicitProperty extends FieldAccessor implements OwnerTypeLocatable {
        protected ForImplicitProperty(FieldLocation fieldLocation) {
            this(fieldLocation, Assigner.DEFAULT, Assigner.Typing.STATIC);
        }

        private ForImplicitProperty(FieldLocation fieldLocation, Assigner assigner, Assigner.Typing typing) {
            super(fieldLocation, assigner, typing);
        }

        @Override // net.bytebuddy.implementation.Implementation
        public ByteCodeAppender appender(Implementation.Target target) {
            return new Appender(this.fieldLocation.prepare(target.getInstrumentedType()));
        }

        @Override // net.bytebuddy.implementation.FieldAccessor.PropertyConfigurable
        public Implementation.Composable setsArgumentAt(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("A parameter index cannot be negative: " + i);
            }
            return new ForParameterSetter(this.fieldLocation, this.assigner, this.typing, i);
        }

        @Override // net.bytebuddy.implementation.FieldAccessor.AssignerConfigurable
        public PropertyConfigurable withAssigner(Assigner assigner, Assigner.Typing typing) {
            return new ForImplicitProperty(this.fieldLocation, assigner, typing);
        }

        @Override // net.bytebuddy.implementation.FieldAccessor.OwnerTypeLocatable
        public AssignerConfigurable in(Class<?> cls) {
            return in(new TypeDescription.ForLoadedType(cls));
        }

        @Override // net.bytebuddy.implementation.FieldAccessor.OwnerTypeLocatable
        public AssignerConfigurable in(TypeDescription typeDescription) {
            return in(new FieldLocator.ForExactType.Factory(typeDescription));
        }

        @Override // net.bytebuddy.implementation.FieldAccessor.OwnerTypeLocatable
        public AssignerConfigurable in(FieldLocator.Factory factory) {
            return new ForImplicitProperty(this.fieldLocation.with(factory), this.assigner, this.typing);
        }

        protected class Appender implements ByteCodeAppender {
            private final FieldLocation.Prepared fieldLocation;

            protected Appender(FieldLocation.Prepared prepared) {
                this.fieldLocation = prepared;
            }

            @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
            public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                StackManipulation.Compound compound;
                if (!methodDescription.isMethod()) {
                    throw new IllegalArgumentException(methodDescription + " does not describe a field getter or setter");
                }
                FieldDescription fieldDescriptionResolve = this.fieldLocation.resolve(methodDescription);
                if (!methodDescription.getReturnType().represents(Void.TYPE)) {
                    compound = new StackManipulation.Compound(ForImplicitProperty.this.getter(fieldDescriptionResolve, methodDescription), MethodReturn.of(methodDescription.getReturnType()));
                } else if (methodDescription.getReturnType().represents(Void.TYPE) && methodDescription.getParameters().size() == 1) {
                    compound = new StackManipulation.Compound(ForImplicitProperty.this.setter(fieldDescriptionResolve, (ParameterDescription) methodDescription.getParameters().get(0)), MethodReturn.VOID);
                } else {
                    throw new IllegalArgumentException("Method " + context + " is no bean property");
                }
                return new ByteCodeAppender.Size(compound.apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
            }

            private ForImplicitProperty getOuter() {
                return ForImplicitProperty.this;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                Appender appender = (Appender) obj;
                return this.fieldLocation.equals(appender.fieldLocation) && ForImplicitProperty.this.equals(appender.getOuter());
            }

            public int hashCode() {
                return this.fieldLocation.hashCode() + (ForImplicitProperty.this.hashCode() * 31);
            }
        }
    }

    protected static class ForParameterSetter extends FieldAccessor implements Implementation.Composable {
        private final int index;
        private final TerminationHandler terminationHandler;

        protected enum TerminationHandler {
            RETURNING { // from class: net.bytebuddy.implementation.FieldAccessor.ForParameterSetter.TerminationHandler.1
                @Override // net.bytebuddy.implementation.FieldAccessor.ForParameterSetter.TerminationHandler
                protected StackManipulation resolve(MethodDescription methodDescription) {
                    if (!methodDescription.getReturnType().represents(Void.TYPE)) {
                        throw new IllegalStateException("Cannot implement setter with return value for " + methodDescription);
                    }
                    return MethodReturn.VOID;
                }
            },
            NON_OPERATIONAL { // from class: net.bytebuddy.implementation.FieldAccessor.ForParameterSetter.TerminationHandler.2
                @Override // net.bytebuddy.implementation.FieldAccessor.ForParameterSetter.TerminationHandler
                protected StackManipulation resolve(MethodDescription methodDescription) {
                    return StackManipulation.Trivial.INSTANCE;
                }
            };

            protected abstract StackManipulation resolve(MethodDescription methodDescription);
        }

        @Override // net.bytebuddy.implementation.FieldAccessor
        protected boolean canEqual(Object obj) {
            return obj instanceof ForParameterSetter;
        }

        @Override // net.bytebuddy.implementation.FieldAccessor
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ForParameterSetter)) {
                return false;
            }
            ForParameterSetter forParameterSetter = (ForParameterSetter) obj;
            if (!forParameterSetter.canEqual(this) || !super.equals(obj) || this.index != forParameterSetter.index) {
                return false;
            }
            TerminationHandler terminationHandler = this.terminationHandler;
            TerminationHandler terminationHandler2 = forParameterSetter.terminationHandler;
            return terminationHandler != null ? terminationHandler.equals(terminationHandler2) : terminationHandler2 == null;
        }

        @Override // net.bytebuddy.implementation.FieldAccessor
        public int hashCode() {
            int iHashCode = ((super.hashCode() + 59) * 59) + this.index;
            TerminationHandler terminationHandler = this.terminationHandler;
            return (iHashCode * 59) + (terminationHandler == null ? 43 : terminationHandler.hashCode());
        }

        protected ForParameterSetter(FieldLocation fieldLocation, Assigner assigner, Assigner.Typing typing, int i) {
            this(fieldLocation, assigner, typing, i, TerminationHandler.RETURNING);
        }

        private ForParameterSetter(FieldLocation fieldLocation, Assigner assigner, Assigner.Typing typing, int i, TerminationHandler terminationHandler) {
            super(fieldLocation, assigner, typing);
            this.index = i;
            this.terminationHandler = terminationHandler;
        }

        @Override // net.bytebuddy.implementation.Implementation
        public ByteCodeAppender appender(Implementation.Target target) {
            return new Appender(this.fieldLocation.prepare(target.getInstrumentedType()));
        }

        @Override // net.bytebuddy.implementation.Implementation.Composable
        public Implementation andThen(Implementation implementation) {
            return new Implementation.Compound(new ForParameterSetter(this.fieldLocation, this.assigner, this.typing, this.index, TerminationHandler.NON_OPERATIONAL), implementation);
        }

        protected class Appender implements ByteCodeAppender {
            private final FieldLocation.Prepared fieldLocation;

            protected Appender(FieldLocation.Prepared prepared) {
                this.fieldLocation = prepared;
            }

            @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
            public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                if (methodDescription.getParameters().size() <= ForParameterSetter.this.index) {
                    throw new IllegalStateException(methodDescription + " does not define a parameter with index " + ForParameterSetter.this.index);
                }
                return new ByteCodeAppender.Size(new StackManipulation.Compound(ForParameterSetter.this.setter(this.fieldLocation.resolve(methodDescription), (ParameterDescription) methodDescription.getParameters().get(ForParameterSetter.this.index)), ForParameterSetter.this.terminationHandler.resolve(methodDescription)).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
            }

            private ForParameterSetter getOuter() {
                return ForParameterSetter.this;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                Appender appender = (Appender) obj;
                return this.fieldLocation.equals(appender.fieldLocation) && ForParameterSetter.this.equals(appender.getOuter());
            }

            public int hashCode() {
                return this.fieldLocation.hashCode() + (ForParameterSetter.this.hashCode() * 31);
            }
        }
    }
}
