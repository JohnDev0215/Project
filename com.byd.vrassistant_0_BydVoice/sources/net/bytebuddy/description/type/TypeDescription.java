package net.bytebuddy.description.type;

import cerence.ark.assistant.controller.enumeration.TemperatureUnitEnum;
import com.android.multidex.ClassPathElement;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.GenericSignatureFormatError;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.text.Typography;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.description.ByteCodeElement;
import net.bytebuddy.description.ModifierReviewable;
import net.bytebuddy.description.TypeVariableSource;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.annotation.AnnotationSource;
import net.bytebuddy.description.enumeration.EnumerationDescription;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.type.PackageDescription;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.dynamic.TargetType;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.jar.asm.signature.SignatureVisitor;
import net.bytebuddy.jar.asm.signature.SignatureWriter;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.CompoundList;
import net.bytebuddy.utility.JavaType;
import net.bytebuddy.utility.privilege.GetSystemPropertyAction;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
public interface TypeDescription extends TypeDefinition, ByteCodeElement, TypeVariableSource {
    public static final TypeDescription OBJECT = new ForLoadedType(Object.class);
    public static final TypeDescription STRING = new ForLoadedType(String.class);
    public static final TypeDescription CLASS = new ForLoadedType(Class.class);
    public static final TypeDescription THROWABLE = new ForLoadedType(Throwable.class);
    public static final TypeDescription VOID = new ForLoadedType(Void.TYPE);
    public static final TypeList.Generic ARRAY_INTERFACES = new TypeList.Generic.ForLoadedTypes(Cloneable.class, Serializable.class);
    public static final TypeDescription UNDEFINED = null;

    TypeDescription asBoxed();

    TypeDescription asUnboxed();

    int getActualModifiers(boolean z);

    String getCanonicalName();

    @Override // net.bytebuddy.description.type.TypeDefinition
    TypeDescription getComponentType();

    @Override // net.bytebuddy.description.type.TypeDefinition
    FieldList<FieldDescription.InDefinedShape> getDeclaredFields();

    @Override // net.bytebuddy.description.type.TypeDefinition
    MethodList<MethodDescription.InDefinedShape> getDeclaredMethods();

    TypeList getDeclaredTypes();

    @Override // net.bytebuddy.description.DeclaredByType
    TypeDescription getDeclaringType();

    MethodDescription getEnclosingMethod();

    TypeDescription getEnclosingType();

    AnnotationList getInheritedAnnotations();

    PackageDescription getPackage();

    int getSegmentCount();

    String getSimpleName();

    boolean isAnnotationReturnType();

    boolean isAnnotationValue();

    boolean isAnnotationValue(Object obj);

    boolean isAnonymousClass();

    boolean isAssignableFrom(Class<?> cls);

    boolean isAssignableFrom(TypeDescription typeDescription);

    boolean isAssignableTo(Class<?> cls);

    boolean isAssignableTo(TypeDescription typeDescription);

    boolean isConstantPool();

    boolean isInstance(Object obj);

    boolean isLocalClass();

    boolean isMemberClass();

    boolean isPackageType();

    boolean isPrimitiveWrapper();

    boolean isSamePackage(TypeDescription typeDescription);

    public interface Generic extends TypeDefinition, AnnotationSource {
        public static final Generic OBJECT = new OfNonGenericType.ForLoadedType(Object.class);
        public static final Generic VOID = new OfNonGenericType.ForLoadedType(Void.TYPE);
        public static final Generic ANNOTATION = new OfNonGenericType.ForLoadedType(Annotation.class);
        public static final Generic UNDEFINED = null;

        <T> T accept(Visitor<T> visitor);

        Generic asRawType();

        Generic findBindingOf(Generic generic);

        @Override // net.bytebuddy.description.type.TypeDefinition
        Generic getComponentType();

        @Override // net.bytebuddy.description.type.TypeDefinition
        FieldList<FieldDescription.InGenericShape> getDeclaredFields();

        @Override // net.bytebuddy.description.type.TypeDefinition
        MethodList<MethodDescription.InGenericShape> getDeclaredMethods();

        TypeList.Generic getLowerBounds();

        Generic getOwnerType();

        String getSymbol();

        TypeList.Generic getTypeArguments();

        TypeVariableSource getTypeVariableSource();

        TypeList.Generic getUpperBounds();

        public interface Visitor<T> {

            public enum NoOp implements Visitor<Generic> {
                INSTANCE;

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Generic onGenericArray(Generic generic) {
                    return generic;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Generic onNonGenericType(Generic generic) {
                    return generic;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Generic onParameterizedType(Generic generic) {
                    return generic;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Generic onTypeVariable(Generic generic) {
                    return generic;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Generic onWildcard(Generic generic) {
                    return generic;
                }
            }

            T onGenericArray(Generic generic);

            T onNonGenericType(Generic generic);

            T onParameterizedType(Generic generic);

            T onTypeVariable(Generic generic);

            T onWildcard(Generic generic);

            public enum TypeErasing implements Visitor<Generic> {
                INSTANCE;

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Generic onGenericArray(Generic generic) {
                    return generic.asRawType();
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Generic onWildcard(Generic generic) {
                    throw new IllegalArgumentException("Cannot erase a wildcard type: " + generic);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Generic onParameterizedType(Generic generic) {
                    return generic.asRawType();
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Generic onTypeVariable(Generic generic) {
                    return generic.asRawType();
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Generic onNonGenericType(Generic generic) {
                    return generic.asRawType();
                }
            }

            public enum AnnotationStripper implements Visitor<Generic> {
                INSTANCE;

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Generic onGenericArray(Generic generic) {
                    return new OfGenericArray.Latent((Generic) generic.getComponentType().accept(this), AnnotationSource.Empty.INSTANCE);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Generic onWildcard(Generic generic) {
                    return new OfWildcardType.Latent(generic.getUpperBounds().accept(this), generic.getLowerBounds().accept(this), AnnotationSource.Empty.INSTANCE);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Generic onParameterizedType(Generic generic) {
                    Generic ownerType = generic.getOwnerType();
                    return new OfParameterizedType.Latent(generic.asErasure(), ownerType == null ? Generic.UNDEFINED : (Generic) ownerType.accept(this), generic.getTypeArguments().accept(this), AnnotationSource.Empty.INSTANCE);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Generic onTypeVariable(Generic generic) {
                    return new NonAnnotatedTypeVariable(generic);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Generic onNonGenericType(Generic generic) {
                    if (generic.isArray()) {
                        return new OfGenericArray.Latent(onNonGenericType(generic.getComponentType()), AnnotationSource.Empty.INSTANCE);
                    }
                    return new OfNonGenericType.Latent(generic.asErasure(), AnnotationSource.Empty.INSTANCE);
                }

                protected static class NonAnnotatedTypeVariable extends OfTypeVariable {
                    private final Generic typeVariable;

                    protected NonAnnotatedTypeVariable(Generic generic) {
                        this.typeVariable = generic;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic
                    public TypeList.Generic getUpperBounds() {
                        return this.typeVariable.getUpperBounds();
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic
                    public TypeVariableSource getTypeVariableSource() {
                        return this.typeVariable.getTypeVariableSource();
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic
                    public String getSymbol() {
                        return this.typeVariable.getSymbol();
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationSource
                    public AnnotationList getDeclaredAnnotations() {
                        return new AnnotationList.Empty();
                    }
                }
            }

            public enum Assigner implements Visitor<Dispatcher> {
                INSTANCE;

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Dispatcher onGenericArray(Generic generic) {
                    return new Dispatcher.ForGenericArray(generic);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Dispatcher onWildcard(Generic generic) {
                    throw new IllegalArgumentException("A wildcard is not a first level type: " + this);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Dispatcher onParameterizedType(Generic generic) {
                    return new Dispatcher.ForParameterizedType(generic);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Dispatcher onTypeVariable(Generic generic) {
                    return new Dispatcher.ForTypeVariable(generic);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Dispatcher onNonGenericType(Generic generic) {
                    return new Dispatcher.ForNonGenericType(generic.asErasure());
                }

                public interface Dispatcher {
                    boolean isAssignableFrom(Generic generic);

                    public static abstract class AbstractBase implements Dispatcher, Visitor<Boolean> {
                        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Assigner.Dispatcher
                        public boolean isAssignableFrom(Generic generic) {
                            return ((Boolean) generic.accept(this)).booleanValue();
                        }
                    }

                    public static class ForNonGenericType extends AbstractBase {
                        private final TypeDescription typeDescription;

                        protected boolean canEqual(Object obj) {
                            return obj instanceof ForNonGenericType;
                        }

                        public boolean equals(Object obj) {
                            if (obj == this) {
                                return true;
                            }
                            if (!(obj instanceof ForNonGenericType)) {
                                return false;
                            }
                            ForNonGenericType forNonGenericType = (ForNonGenericType) obj;
                            if (!forNonGenericType.canEqual(this)) {
                                return false;
                            }
                            TypeDescription typeDescription = this.typeDescription;
                            TypeDescription typeDescription2 = forNonGenericType.typeDescription;
                            return typeDescription != null ? typeDescription.equals(typeDescription2) : typeDescription2 == null;
                        }

                        public int hashCode() {
                            TypeDescription typeDescription = this.typeDescription;
                            return 59 + (typeDescription == null ? 43 : typeDescription.hashCode());
                        }

                        protected ForNonGenericType(TypeDescription typeDescription) {
                            this.typeDescription = typeDescription;
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                        public Boolean onGenericArray(Generic generic) {
                            boolean zBooleanValue;
                            if (this.typeDescription.isArray()) {
                                zBooleanValue = ((Boolean) generic.getComponentType().accept(new ForNonGenericType(this.typeDescription.getComponentType()))).booleanValue();
                            } else {
                                zBooleanValue = this.typeDescription.represents(Object.class) || TypeDescription.ARRAY_INTERFACES.contains(this.typeDescription.asGenericType());
                            }
                            return Boolean.valueOf(zBooleanValue);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                        public Boolean onWildcard(Generic generic) {
                            throw new IllegalArgumentException("A wildcard is not a first-level type: " + generic);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                        public Boolean onParameterizedType(Generic generic) {
                            if (this.typeDescription.equals(generic.asErasure())) {
                                return true;
                            }
                            Generic superClass = generic.getSuperClass();
                            if (superClass != null && isAssignableFrom(superClass)) {
                                return true;
                            }
                            Iterator it = generic.getInterfaces().iterator();
                            while (it.hasNext()) {
                                if (isAssignableFrom((Generic) it.next())) {
                                    return true;
                                }
                            }
                            return Boolean.valueOf(this.typeDescription.represents(Object.class));
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                        public Boolean onTypeVariable(Generic generic) {
                            Iterator it = generic.getUpperBounds().iterator();
                            while (it.hasNext()) {
                                if (isAssignableFrom((Generic) it.next())) {
                                    return true;
                                }
                            }
                            return false;
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                        public Boolean onNonGenericType(Generic generic) {
                            return Boolean.valueOf(this.typeDescription.isAssignableFrom(generic.asErasure()));
                        }
                    }

                    public static class ForTypeVariable extends AbstractBase {
                        private final Generic typeVariable;

                        protected boolean canEqual(Object obj) {
                            return obj instanceof ForTypeVariable;
                        }

                        public boolean equals(Object obj) {
                            if (obj == this) {
                                return true;
                            }
                            if (!(obj instanceof ForTypeVariable)) {
                                return false;
                            }
                            ForTypeVariable forTypeVariable = (ForTypeVariable) obj;
                            if (!forTypeVariable.canEqual(this)) {
                                return false;
                            }
                            Generic generic = this.typeVariable;
                            Generic generic2 = forTypeVariable.typeVariable;
                            return generic != null ? generic.equals(generic2) : generic2 == null;
                        }

                        public int hashCode() {
                            Generic generic = this.typeVariable;
                            return 59 + (generic == null ? 43 : generic.hashCode());
                        }

                        protected ForTypeVariable(Generic generic) {
                            this.typeVariable = generic;
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                        public Boolean onGenericArray(Generic generic) {
                            return false;
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                        public Boolean onWildcard(Generic generic) {
                            throw new IllegalArgumentException("A wildcard is not a first-level type: " + generic);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                        public Boolean onParameterizedType(Generic generic) {
                            return false;
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                        public Boolean onTypeVariable(Generic generic) {
                            if (generic.equals(this.typeVariable)) {
                                return true;
                            }
                            Iterator it = generic.getUpperBounds().iterator();
                            while (it.hasNext()) {
                                if (isAssignableFrom((Generic) it.next())) {
                                    return true;
                                }
                            }
                            return false;
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                        public Boolean onNonGenericType(Generic generic) {
                            return false;
                        }
                    }

                    public static class ForParameterizedType extends AbstractBase {
                        private final Generic parameterizedType;

                        protected boolean canEqual(Object obj) {
                            return obj instanceof ForParameterizedType;
                        }

                        public boolean equals(Object obj) {
                            if (obj == this) {
                                return true;
                            }
                            if (!(obj instanceof ForParameterizedType)) {
                                return false;
                            }
                            ForParameterizedType forParameterizedType = (ForParameterizedType) obj;
                            if (!forParameterizedType.canEqual(this)) {
                                return false;
                            }
                            Generic generic = this.parameterizedType;
                            Generic generic2 = forParameterizedType.parameterizedType;
                            return generic != null ? generic.equals(generic2) : generic2 == null;
                        }

                        public int hashCode() {
                            Generic generic = this.parameterizedType;
                            return 59 + (generic == null ? 43 : generic.hashCode());
                        }

                        protected ForParameterizedType(Generic generic) {
                            this.parameterizedType = generic;
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                        public Boolean onGenericArray(Generic generic) {
                            return false;
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                        public Boolean onWildcard(Generic generic) {
                            throw new IllegalArgumentException("A wildcard is not a first-level type: " + generic);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                        public Boolean onParameterizedType(Generic generic) {
                            if (this.parameterizedType.asErasure().equals(generic.asErasure())) {
                                Generic ownerType = this.parameterizedType.getOwnerType();
                                Generic ownerType2 = generic.getOwnerType();
                                if (ownerType == null || ownerType2 == null || ((Dispatcher) ownerType.accept(Assigner.INSTANCE)).isAssignableFrom(ownerType2)) {
                                    TypeList.Generic typeArguments = this.parameterizedType.getTypeArguments();
                                    TypeList.Generic typeArguments2 = generic.getTypeArguments();
                                    if (typeArguments.size() == typeArguments2.size()) {
                                        for (int i = 0; i < typeArguments.size(); i++) {
                                            if (!((Dispatcher) ((Generic) typeArguments.get(i)).accept(ParameterAssigner.INSTANCE)).isAssignableFrom((Generic) typeArguments2.get(i))) {
                                                return false;
                                            }
                                        }
                                        return true;
                                    }
                                    throw new IllegalArgumentException("Incompatible generic types: " + generic + " and " + this.parameterizedType);
                                }
                                return false;
                            }
                            Generic superClass = generic.getSuperClass();
                            if (superClass != null && isAssignableFrom(superClass)) {
                                return true;
                            }
                            Iterator it = generic.getInterfaces().iterator();
                            while (it.hasNext()) {
                                if (isAssignableFrom((Generic) it.next())) {
                                    return true;
                                }
                            }
                            return false;
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                        public Boolean onTypeVariable(Generic generic) {
                            Iterator it = generic.getUpperBounds().iterator();
                            while (it.hasNext()) {
                                if (isAssignableFrom((Generic) it.next())) {
                                    return true;
                                }
                            }
                            return false;
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                        public Boolean onNonGenericType(Generic generic) {
                            if (this.parameterizedType.asErasure().equals(generic.asErasure())) {
                                return true;
                            }
                            Generic superClass = generic.getSuperClass();
                            if (superClass != null && isAssignableFrom(superClass)) {
                                return true;
                            }
                            Iterator it = generic.getInterfaces().iterator();
                            while (it.hasNext()) {
                                if (isAssignableFrom((Generic) it.next())) {
                                    return true;
                                }
                            }
                            return false;
                        }

                        protected enum ParameterAssigner implements Visitor<Dispatcher> {
                            INSTANCE;

                            @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                            public Dispatcher onGenericArray(Generic generic) {
                                return new InvariantBinding(generic);
                            }

                            @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                            public Dispatcher onWildcard(Generic generic) {
                                TypeList.Generic lowerBounds = generic.getLowerBounds();
                                if (lowerBounds.isEmpty()) {
                                    return new CovariantBinding(generic.getUpperBounds().getOnly());
                                }
                                return new ContravariantBinding(lowerBounds.getOnly());
                            }

                            @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                            public Dispatcher onParameterizedType(Generic generic) {
                                return new InvariantBinding(generic);
                            }

                            @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                            public Dispatcher onTypeVariable(Generic generic) {
                                return new InvariantBinding(generic);
                            }

                            @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                            public Dispatcher onNonGenericType(Generic generic) {
                                return new InvariantBinding(generic);
                            }

                            protected static class InvariantBinding implements Dispatcher {
                                private final Generic typeDescription;

                                protected boolean canEqual(Object obj) {
                                    return obj instanceof InvariantBinding;
                                }

                                public boolean equals(Object obj) {
                                    if (obj == this) {
                                        return true;
                                    }
                                    if (!(obj instanceof InvariantBinding)) {
                                        return false;
                                    }
                                    InvariantBinding invariantBinding = (InvariantBinding) obj;
                                    if (!invariantBinding.canEqual(this)) {
                                        return false;
                                    }
                                    Generic generic = this.typeDescription;
                                    Generic generic2 = invariantBinding.typeDescription;
                                    return generic != null ? generic.equals(generic2) : generic2 == null;
                                }

                                public int hashCode() {
                                    Generic generic = this.typeDescription;
                                    return 59 + (generic == null ? 43 : generic.hashCode());
                                }

                                protected InvariantBinding(Generic generic) {
                                    this.typeDescription = generic;
                                }

                                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Assigner.Dispatcher
                                public boolean isAssignableFrom(Generic generic) {
                                    return generic.equals(this.typeDescription);
                                }
                            }

                            protected static class CovariantBinding implements Dispatcher {
                                private final Generic upperBound;

                                protected boolean canEqual(Object obj) {
                                    return obj instanceof CovariantBinding;
                                }

                                public boolean equals(Object obj) {
                                    if (obj == this) {
                                        return true;
                                    }
                                    if (!(obj instanceof CovariantBinding)) {
                                        return false;
                                    }
                                    CovariantBinding covariantBinding = (CovariantBinding) obj;
                                    if (!covariantBinding.canEqual(this)) {
                                        return false;
                                    }
                                    Generic generic = this.upperBound;
                                    Generic generic2 = covariantBinding.upperBound;
                                    return generic != null ? generic.equals(generic2) : generic2 == null;
                                }

                                public int hashCode() {
                                    Generic generic = this.upperBound;
                                    return 59 + (generic == null ? 43 : generic.hashCode());
                                }

                                protected CovariantBinding(Generic generic) {
                                    this.upperBound = generic;
                                }

                                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Assigner.Dispatcher
                                public boolean isAssignableFrom(Generic generic) {
                                    if (generic.getSort().isWildcard()) {
                                        return generic.getLowerBounds().isEmpty() && ((Dispatcher) this.upperBound.accept(Assigner.INSTANCE)).isAssignableFrom(generic.getUpperBounds().getOnly());
                                    }
                                    return ((Dispatcher) this.upperBound.accept(Assigner.INSTANCE)).isAssignableFrom(generic);
                                }
                            }

                            protected static class ContravariantBinding implements Dispatcher {
                                private final Generic lowerBound;

                                protected boolean canEqual(Object obj) {
                                    return obj instanceof ContravariantBinding;
                                }

                                public boolean equals(Object obj) {
                                    if (obj == this) {
                                        return true;
                                    }
                                    if (!(obj instanceof ContravariantBinding)) {
                                        return false;
                                    }
                                    ContravariantBinding contravariantBinding = (ContravariantBinding) obj;
                                    if (!contravariantBinding.canEqual(this)) {
                                        return false;
                                    }
                                    Generic generic = this.lowerBound;
                                    Generic generic2 = contravariantBinding.lowerBound;
                                    return generic != null ? generic.equals(generic2) : generic2 == null;
                                }

                                public int hashCode() {
                                    Generic generic = this.lowerBound;
                                    return 59 + (generic == null ? 43 : generic.hashCode());
                                }

                                protected ContravariantBinding(Generic generic) {
                                    this.lowerBound = generic;
                                }

                                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Assigner.Dispatcher
                                public boolean isAssignableFrom(Generic generic) {
                                    if (!generic.getSort().isWildcard()) {
                                        return generic.getSort().isWildcard() || ((Dispatcher) generic.accept(Assigner.INSTANCE)).isAssignableFrom(this.lowerBound);
                                    }
                                    TypeList.Generic lowerBounds = generic.getLowerBounds();
                                    return !lowerBounds.isEmpty() && ((Dispatcher) lowerBounds.getOnly().accept(Assigner.INSTANCE)).isAssignableFrom(this.lowerBound);
                                }
                            }
                        }
                    }

                    public static class ForGenericArray extends AbstractBase {
                        private final Generic genericArray;

                        protected boolean canEqual(Object obj) {
                            return obj instanceof ForGenericArray;
                        }

                        public boolean equals(Object obj) {
                            if (obj == this) {
                                return true;
                            }
                            if (!(obj instanceof ForGenericArray)) {
                                return false;
                            }
                            ForGenericArray forGenericArray = (ForGenericArray) obj;
                            if (!forGenericArray.canEqual(this)) {
                                return false;
                            }
                            Generic generic = this.genericArray;
                            Generic generic2 = forGenericArray.genericArray;
                            return generic != null ? generic.equals(generic2) : generic2 == null;
                        }

                        public int hashCode() {
                            Generic generic = this.genericArray;
                            return 59 + (generic == null ? 43 : generic.hashCode());
                        }

                        protected ForGenericArray(Generic generic) {
                            this.genericArray = generic;
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                        public Boolean onGenericArray(Generic generic) {
                            return Boolean.valueOf(((Dispatcher) this.genericArray.getComponentType().accept(Assigner.INSTANCE)).isAssignableFrom(generic.getComponentType()));
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                        public Boolean onWildcard(Generic generic) {
                            throw new IllegalArgumentException("A wildcard is not a first-level type: " + generic);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                        public Boolean onParameterizedType(Generic generic) {
                            return false;
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                        public Boolean onTypeVariable(Generic generic) {
                            return false;
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                        public Boolean onNonGenericType(Generic generic) {
                            return Boolean.valueOf(generic.isArray() && ((Dispatcher) this.genericArray.getComponentType().accept(Assigner.INSTANCE)).isAssignableFrom(generic.getComponentType()));
                        }
                    }
                }
            }

            public enum Validator implements Visitor<Boolean> {
                SUPER_CLASS(false, false, false, false) { // from class: net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Validator.1
                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Validator, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public /* bridge */ /* synthetic */ Boolean onGenericArray(Generic generic) {
                        return super.onGenericArray(generic);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Validator, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public /* bridge */ /* synthetic */ Boolean onTypeVariable(Generic generic) {
                        return super.onTypeVariable(generic);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Validator, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public /* bridge */ /* synthetic */ Boolean onWildcard(Generic generic) {
                        return super.onWildcard(generic);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Validator, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public Boolean onNonGenericType(Generic generic) {
                        return Boolean.valueOf(super.onNonGenericType(generic).booleanValue() && !generic.isInterface());
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Validator, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public Boolean onParameterizedType(Generic generic) {
                        return Boolean.valueOf(!generic.isInterface());
                    }
                },
                INTERFACE(false, false, false, false) { // from class: net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Validator.2
                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Validator, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public /* bridge */ /* synthetic */ Boolean onGenericArray(Generic generic) {
                        return super.onGenericArray(generic);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Validator, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public /* bridge */ /* synthetic */ Boolean onTypeVariable(Generic generic) {
                        return super.onTypeVariable(generic);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Validator, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public /* bridge */ /* synthetic */ Boolean onWildcard(Generic generic) {
                        return super.onWildcard(generic);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Validator, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public Boolean onNonGenericType(Generic generic) {
                        return Boolean.valueOf(super.onNonGenericType(generic).booleanValue() && generic.isInterface());
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Validator, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public Boolean onParameterizedType(Generic generic) {
                        return Boolean.valueOf(generic.isInterface());
                    }
                },
                TYPE_VARIABLE(false, false, true, false),
                FIELD(true, true, true, false),
                METHOD_RETURN(true, true, true, true),
                METHOD_PARAMETER(true, true, true, false),
                EXCEPTION(false, false, true, false) { // from class: net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Validator.3
                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Validator, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public /* bridge */ /* synthetic */ Boolean onGenericArray(Generic generic) {
                        return super.onGenericArray(generic);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Validator, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public /* bridge */ /* synthetic */ Boolean onWildcard(Generic generic) {
                        return super.onWildcard(generic);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Validator, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public Boolean onParameterizedType(Generic generic) {
                        return false;
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Validator, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public Boolean onTypeVariable(Generic generic) {
                        Iterator it = generic.getUpperBounds().iterator();
                        while (it.hasNext()) {
                            if (((Boolean) ((Generic) it.next()).accept(this)).booleanValue()) {
                                return true;
                            }
                        }
                        return false;
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Validator, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public Boolean onNonGenericType(Generic generic) {
                        return Boolean.valueOf(generic.asErasure().isAssignableTo(Throwable.class));
                    }
                },
                RECEIVER(false, false, false, false);

                private final boolean acceptsArray;
                private final boolean acceptsPrimitive;
                private final boolean acceptsVariable;
                private final boolean acceptsVoid;

                Validator(boolean z, boolean z2, boolean z3, boolean z4) {
                    this.acceptsArray = z;
                    this.acceptsPrimitive = z2;
                    this.acceptsVariable = z3;
                    this.acceptsVoid = z4;
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Boolean onGenericArray(Generic generic) {
                    return Boolean.valueOf(this.acceptsArray);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Boolean onWildcard(Generic generic) {
                    return false;
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Boolean onParameterizedType(Generic generic) {
                    return true;
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Boolean onTypeVariable(Generic generic) {
                    return Boolean.valueOf(this.acceptsVariable);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Boolean onNonGenericType(Generic generic) {
                    return Boolean.valueOf((this.acceptsArray || !generic.isArray()) && (this.acceptsPrimitive || !generic.isPrimitive()) && (this.acceptsVoid || !generic.represents(Void.TYPE)));
                }

                public enum ForTypeAnnotations implements Visitor<Boolean> {
                    INSTANCE;

                    private final ElementType typeParameter;
                    private final ElementType typeUse;

                    ForTypeAnnotations() {
                        ElementType elementType;
                        ElementType elementType2;
                        try {
                            elementType = (ElementType) Enum.valueOf(ElementType.class, "TYPE_USE");
                            elementType2 = (ElementType) Enum.valueOf(ElementType.class, "TYPE_PARAMETER");
                        } catch (IllegalArgumentException unused) {
                            elementType = null;
                            elementType2 = null;
                        }
                        this.typeUse = elementType;
                        this.typeParameter = elementType2;
                    }

                    public static boolean ofFormalTypeVariable(Generic generic) {
                        HashSet hashSet = new HashSet();
                        for (AnnotationDescription annotationDescription : generic.getDeclaredAnnotations()) {
                            if (!annotationDescription.getElementTypes().contains(INSTANCE.typeParameter) || !hashSet.add(annotationDescription.getAnnotationType())) {
                                return false;
                            }
                        }
                        return true;
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public Boolean onGenericArray(Generic generic) {
                        return Boolean.valueOf(isValid(generic) && ((Boolean) generic.getComponentType().accept(this)).booleanValue());
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public Boolean onWildcard(Generic generic) {
                        if (!isValid(generic)) {
                            return false;
                        }
                        TypeList.Generic lowerBounds = generic.getLowerBounds();
                        if (lowerBounds.isEmpty()) {
                            lowerBounds = generic.getUpperBounds();
                        }
                        return (Boolean) lowerBounds.getOnly().accept(this);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public Boolean onParameterizedType(Generic generic) {
                        if (!isValid(generic)) {
                            return false;
                        }
                        Generic ownerType = generic.getOwnerType();
                        if (ownerType != null && !((Boolean) ownerType.accept(this)).booleanValue()) {
                            return false;
                        }
                        Iterator it = generic.getTypeArguments().iterator();
                        while (it.hasNext()) {
                            if (!((Boolean) ((Generic) it.next()).accept(this)).booleanValue()) {
                                return false;
                            }
                        }
                        return true;
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public Boolean onTypeVariable(Generic generic) {
                        return Boolean.valueOf(isValid(generic));
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public Boolean onNonGenericType(Generic generic) {
                        return Boolean.valueOf(isValid(generic) && (!generic.isArray() || ((Boolean) generic.getComponentType().accept(this)).booleanValue()));
                    }

                    private boolean isValid(Generic generic) {
                        HashSet hashSet = new HashSet();
                        for (AnnotationDescription annotationDescription : generic.getDeclaredAnnotations()) {
                            if (!annotationDescription.getElementTypes().contains(this.typeUse) || !hashSet.add(annotationDescription.getAnnotationType())) {
                                return false;
                            }
                        }
                        return true;
                    }
                }
            }

            public enum Reifying implements Visitor<Generic> {
                INITIATING { // from class: net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Reifying.1
                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public Generic onParameterizedType(Generic generic) {
                        return generic;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Reifying, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public /* bridge */ /* synthetic */ Generic onGenericArray(Generic generic) {
                        return super.onGenericArray(generic);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Reifying, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public /* bridge */ /* synthetic */ Generic onNonGenericType(Generic generic) {
                        return super.onNonGenericType(generic);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Reifying, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public /* bridge */ /* synthetic */ Generic onTypeVariable(Generic generic) {
                        return super.onTypeVariable(generic);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Reifying, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public /* bridge */ /* synthetic */ Generic onWildcard(Generic generic) {
                        return super.onWildcard(generic);
                    }
                },
                INHERITING { // from class: net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Reifying.2
                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Reifying, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public /* bridge */ /* synthetic */ Generic onGenericArray(Generic generic) {
                        return super.onGenericArray(generic);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Reifying, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public /* bridge */ /* synthetic */ Generic onNonGenericType(Generic generic) {
                        return super.onNonGenericType(generic);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Reifying, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public /* bridge */ /* synthetic */ Generic onTypeVariable(Generic generic) {
                        return super.onTypeVariable(generic);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Reifying, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public /* bridge */ /* synthetic */ Generic onWildcard(Generic generic) {
                        return super.onWildcard(generic);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public Generic onParameterizedType(Generic generic) {
                        return new OfParameterizedType.ForReifiedType(generic);
                    }
                };

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Generic onGenericArray(Generic generic) {
                    throw new IllegalArgumentException("Cannot reify a generic array: " + generic);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Generic onWildcard(Generic generic) {
                    throw new IllegalArgumentException("Cannot reify a wildcard: " + generic);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Generic onTypeVariable(Generic generic) {
                    throw new IllegalArgumentException("Cannot reify a type variable: " + generic);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Generic onNonGenericType(Generic generic) {
                    TypeDescription typeDescriptionAsErasure = generic.asErasure();
                    return typeDescriptionAsErasure.isGenerified() ? new OfNonGenericType.ForReifiedErasure(typeDescriptionAsErasure) : generic;
                }
            }

            public static class ForSignatureVisitor implements Visitor<SignatureVisitor> {
                private static final int ONLY_CHARACTER = 0;
                protected final SignatureVisitor signatureVisitor;

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForSignatureVisitor;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForSignatureVisitor)) {
                        return false;
                    }
                    ForSignatureVisitor forSignatureVisitor = (ForSignatureVisitor) obj;
                    if (!forSignatureVisitor.canEqual(this)) {
                        return false;
                    }
                    SignatureVisitor signatureVisitor = this.signatureVisitor;
                    SignatureVisitor signatureVisitor2 = forSignatureVisitor.signatureVisitor;
                    return signatureVisitor != null ? signatureVisitor.equals(signatureVisitor2) : signatureVisitor2 == null;
                }

                public int hashCode() {
                    SignatureVisitor signatureVisitor = this.signatureVisitor;
                    return 59 + (signatureVisitor == null ? 43 : signatureVisitor.hashCode());
                }

                public ForSignatureVisitor(SignatureVisitor signatureVisitor) {
                    this.signatureVisitor = signatureVisitor;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public SignatureVisitor onGenericArray(Generic generic) {
                    generic.getComponentType().accept(new ForSignatureVisitor(this.signatureVisitor.visitArrayType()));
                    return this.signatureVisitor;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public SignatureVisitor onWildcard(Generic generic) {
                    throw new IllegalStateException("Unexpected wildcard: " + generic);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public SignatureVisitor onParameterizedType(Generic generic) {
                    onOwnableType(generic);
                    this.signatureVisitor.visitEnd();
                    return this.signatureVisitor;
                }

                private void onOwnableType(Generic generic) {
                    Generic ownerType = generic.getOwnerType();
                    if (ownerType != null && ownerType.getSort().isParameterized()) {
                        onOwnableType(ownerType);
                        this.signatureVisitor.visitInnerClassType(generic.asErasure().getSimpleName());
                    } else {
                        this.signatureVisitor.visitClassType(generic.asErasure().getInternalName());
                    }
                    Iterator it = generic.getTypeArguments().iterator();
                    while (it.hasNext()) {
                        ((Generic) it.next()).accept(new OfTypeArgument(this.signatureVisitor));
                    }
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public SignatureVisitor onTypeVariable(Generic generic) {
                    this.signatureVisitor.visitTypeVariable(generic.getSymbol());
                    return this.signatureVisitor;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public SignatureVisitor onNonGenericType(Generic generic) {
                    if (generic.isArray()) {
                        generic.getComponentType().accept(new ForSignatureVisitor(this.signatureVisitor.visitArrayType()));
                    } else if (generic.isPrimitive()) {
                        this.signatureVisitor.visitBaseType(generic.asErasure().getDescriptor().charAt(0));
                    } else {
                        this.signatureVisitor.visitClassType(generic.asErasure().getInternalName());
                        this.signatureVisitor.visitEnd();
                    }
                    return this.signatureVisitor;
                }

                protected static class OfTypeArgument extends ForSignatureVisitor {
                    protected OfTypeArgument(SignatureVisitor signatureVisitor) {
                        super(signatureVisitor);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.ForSignatureVisitor, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public SignatureVisitor onWildcard(Generic generic) {
                        TypeList.Generic upperBounds = generic.getUpperBounds();
                        TypeList.Generic lowerBounds = generic.getLowerBounds();
                        if (lowerBounds.isEmpty() && upperBounds.getOnly().represents(Object.class)) {
                            this.signatureVisitor.visitTypeArgument();
                        } else if (!lowerBounds.isEmpty()) {
                            lowerBounds.getOnly().accept(new ForSignatureVisitor(this.signatureVisitor.visitTypeArgument(SignatureVisitor.SUPER)));
                        } else {
                            upperBounds.getOnly().accept(new ForSignatureVisitor(this.signatureVisitor.visitTypeArgument(SignatureVisitor.EXTENDS)));
                        }
                        return this.signatureVisitor;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.ForSignatureVisitor, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public SignatureVisitor onGenericArray(Generic generic) {
                        generic.accept(new ForSignatureVisitor(this.signatureVisitor.visitTypeArgument(SignatureVisitor.INSTANCEOF)));
                        return this.signatureVisitor;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.ForSignatureVisitor, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public SignatureVisitor onParameterizedType(Generic generic) {
                        generic.accept(new ForSignatureVisitor(this.signatureVisitor.visitTypeArgument(SignatureVisitor.INSTANCEOF)));
                        return this.signatureVisitor;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.ForSignatureVisitor, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public SignatureVisitor onTypeVariable(Generic generic) {
                        generic.accept(new ForSignatureVisitor(this.signatureVisitor.visitTypeArgument(SignatureVisitor.INSTANCEOF)));
                        return this.signatureVisitor;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.ForSignatureVisitor, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public SignatureVisitor onNonGenericType(Generic generic) {
                        generic.accept(new ForSignatureVisitor(this.signatureVisitor.visitTypeArgument(SignatureVisitor.INSTANCEOF)));
                        return this.signatureVisitor;
                    }
                }
            }

            public static abstract class Substitutor implements Visitor<Generic> {
                protected abstract Generic onSimpleType(Generic generic);

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Generic onParameterizedType(Generic generic) {
                    Generic ownerType = generic.getOwnerType();
                    ArrayList arrayList = new ArrayList(generic.getTypeArguments().size());
                    Iterator it = generic.getTypeArguments().iterator();
                    while (it.hasNext()) {
                        arrayList.add(((Generic) it.next()).accept(this));
                    }
                    return new OfParameterizedType.Latent(((Generic) generic.asRawType().accept(this)).asErasure(), ownerType == null ? Generic.UNDEFINED : (Generic) ownerType.accept(this), arrayList, generic);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Generic onGenericArray(Generic generic) {
                    return new OfGenericArray.Latent((Generic) generic.getComponentType().accept(this), generic);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Generic onWildcard(Generic generic) {
                    return new OfWildcardType.Latent(generic.getUpperBounds().accept(this), generic.getLowerBounds().accept(this), generic);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Generic onNonGenericType(Generic generic) {
                    if (generic.isArray()) {
                        return new OfGenericArray.Latent((Generic) generic.getComponentType().accept(this), generic);
                    }
                    return onSimpleType(generic);
                }

                public static abstract class WithoutTypeSubstitution extends Substitutor {
                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Substitutor, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public Generic onNonGenericType(Generic generic) {
                        return generic;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Substitutor
                    protected Generic onSimpleType(Generic generic) {
                        return generic;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Substitutor, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public /* bridge */ /* synthetic */ Generic onGenericArray(Generic generic) {
                        return super.onGenericArray(generic);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Substitutor, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public /* bridge */ /* synthetic */ Generic onParameterizedType(Generic generic) {
                        return super.onParameterizedType(generic);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Substitutor, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public /* bridge */ /* synthetic */ Generic onWildcard(Generic generic) {
                        return super.onWildcard(generic);
                    }
                }

                public static class ForAttachment extends Substitutor {
                    private final TypeDescription declaringType;
                    private final TypeVariableSource typeVariableSource;

                    protected boolean canEqual(Object obj) {
                        return obj instanceof ForAttachment;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof ForAttachment)) {
                            return false;
                        }
                        ForAttachment forAttachment = (ForAttachment) obj;
                        if (!forAttachment.canEqual(this)) {
                            return false;
                        }
                        TypeDescription typeDescription = this.declaringType;
                        TypeDescription typeDescription2 = forAttachment.declaringType;
                        if (typeDescription != null ? !typeDescription.equals(typeDescription2) : typeDescription2 != null) {
                            return false;
                        }
                        TypeVariableSource typeVariableSource = this.typeVariableSource;
                        TypeVariableSource typeVariableSource2 = forAttachment.typeVariableSource;
                        return typeVariableSource != null ? typeVariableSource.equals(typeVariableSource2) : typeVariableSource2 == null;
                    }

                    public int hashCode() {
                        TypeDescription typeDescription = this.declaringType;
                        int iHashCode = typeDescription == null ? 43 : typeDescription.hashCode();
                        TypeVariableSource typeVariableSource = this.typeVariableSource;
                        return ((iHashCode + 59) * 59) + (typeVariableSource != null ? typeVariableSource.hashCode() : 43);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Substitutor, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public /* bridge */ /* synthetic */ Generic onGenericArray(Generic generic) {
                        return super.onGenericArray(generic);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Substitutor, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public /* bridge */ /* synthetic */ Generic onNonGenericType(Generic generic) {
                        return super.onNonGenericType(generic);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Substitutor, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public /* bridge */ /* synthetic */ Generic onParameterizedType(Generic generic) {
                        return super.onParameterizedType(generic);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Substitutor, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public /* bridge */ /* synthetic */ Generic onWildcard(Generic generic) {
                        return super.onWildcard(generic);
                    }

                    protected ForAttachment(TypeDefinition typeDefinition, TypeVariableSource typeVariableSource) {
                        this(typeDefinition.asErasure(), typeVariableSource);
                    }

                    protected ForAttachment(TypeDescription typeDescription, TypeVariableSource typeVariableSource) {
                        this.declaringType = typeDescription;
                        this.typeVariableSource = typeVariableSource;
                    }

                    public static ForAttachment of(FieldDescription fieldDescription) {
                        return new ForAttachment(fieldDescription.getDeclaringType(), fieldDescription.getDeclaringType().asErasure());
                    }

                    public static ForAttachment of(MethodDescription methodDescription) {
                        return new ForAttachment(methodDescription.getDeclaringType(), methodDescription);
                    }

                    public static ForAttachment of(ParameterDescription parameterDescription) {
                        return new ForAttachment(parameterDescription.getDeclaringMethod().getDeclaringType(), parameterDescription.getDeclaringMethod());
                    }

                    public static ForAttachment of(TypeDescription typeDescription) {
                        return new ForAttachment(typeDescription, (TypeVariableSource) typeDescription);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public Generic onTypeVariable(Generic generic) {
                        Generic genericFindVariable = this.typeVariableSource.findVariable(generic.getSymbol());
                        if (genericFindVariable == null) {
                            throw new IllegalArgumentException("Cannot attach undefined variable: " + generic);
                        }
                        return new OfTypeVariable.WithAnnotationOverlay(genericFindVariable, generic);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Substitutor
                    protected Generic onSimpleType(Generic generic) {
                        return generic.represents(TargetType.class) ? new OfNonGenericType.Latent(this.declaringType, generic) : generic;
                    }
                }

                public static class ForDetachment extends Substitutor {
                    private final ElementMatcher<? super TypeDescription> typeMatcher;

                    protected boolean canEqual(Object obj) {
                        return obj instanceof ForDetachment;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof ForDetachment)) {
                            return false;
                        }
                        ForDetachment forDetachment = (ForDetachment) obj;
                        if (!forDetachment.canEqual(this)) {
                            return false;
                        }
                        ElementMatcher<? super TypeDescription> elementMatcher = this.typeMatcher;
                        ElementMatcher<? super TypeDescription> elementMatcher2 = forDetachment.typeMatcher;
                        return elementMatcher != null ? elementMatcher.equals(elementMatcher2) : elementMatcher2 == null;
                    }

                    public int hashCode() {
                        ElementMatcher<? super TypeDescription> elementMatcher = this.typeMatcher;
                        return 59 + (elementMatcher == null ? 43 : elementMatcher.hashCode());
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Substitutor, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public /* bridge */ /* synthetic */ Generic onGenericArray(Generic generic) {
                        return super.onGenericArray(generic);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Substitutor, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public /* bridge */ /* synthetic */ Generic onNonGenericType(Generic generic) {
                        return super.onNonGenericType(generic);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Substitutor, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public /* bridge */ /* synthetic */ Generic onParameterizedType(Generic generic) {
                        return super.onParameterizedType(generic);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Substitutor, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public /* bridge */ /* synthetic */ Generic onWildcard(Generic generic) {
                        return super.onWildcard(generic);
                    }

                    public ForDetachment(ElementMatcher<? super TypeDescription> elementMatcher) {
                        this.typeMatcher = elementMatcher;
                    }

                    public static Visitor<Generic> of(TypeDefinition typeDefinition) {
                        return new ForDetachment(ElementMatchers.is(typeDefinition));
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public Generic onTypeVariable(Generic generic) {
                        return new OfTypeVariable.Symbolic(generic.getSymbol(), generic);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Substitutor
                    protected Generic onSimpleType(Generic generic) {
                        return this.typeMatcher.matches(generic.asErasure()) ? new OfNonGenericType.Latent(TargetType.DESCRIPTION, generic.getOwnerType(), generic) : generic;
                    }
                }

                public static class ForTypeVariableBinding extends WithoutTypeSubstitution {
                    private final Generic parameterizedType;

                    protected boolean canEqual(Object obj) {
                        return obj instanceof ForTypeVariableBinding;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof ForTypeVariableBinding)) {
                            return false;
                        }
                        ForTypeVariableBinding forTypeVariableBinding = (ForTypeVariableBinding) obj;
                        if (!forTypeVariableBinding.canEqual(this)) {
                            return false;
                        }
                        Generic generic = this.parameterizedType;
                        Generic generic2 = forTypeVariableBinding.parameterizedType;
                        return generic != null ? generic.equals(generic2) : generic2 == null;
                    }

                    public int hashCode() {
                        Generic generic = this.parameterizedType;
                        return 59 + (generic == null ? 43 : generic.hashCode());
                    }

                    protected ForTypeVariableBinding(Generic generic) {
                        this.parameterizedType = generic;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public Generic onTypeVariable(Generic generic) {
                        return (Generic) generic.getTypeVariableSource().accept(new TypeVariableSubstitutor(generic));
                    }

                    protected class TypeVariableSubstitutor implements TypeVariableSource.Visitor<Generic> {
                        private final Generic typeVariable;

                        protected TypeVariableSubstitutor(Generic generic) {
                            this.typeVariable = generic;
                        }

                        @Override // net.bytebuddy.description.TypeVariableSource.Visitor
                        public Generic onType(TypeDescription typeDescription) {
                            Generic genericFindBindingOf = ForTypeVariableBinding.this.parameterizedType.findBindingOf(this.typeVariable);
                            return genericFindBindingOf == null ? this.typeVariable.asRawType() : genericFindBindingOf;
                        }

                        @Override // net.bytebuddy.description.TypeVariableSource.Visitor
                        public Generic onMethod(MethodDescription.InDefinedShape inDefinedShape) {
                            return ForTypeVariableBinding.this.new RetainedMethodTypeVariable(this.typeVariable);
                        }

                        private ForTypeVariableBinding getOuter() {
                            return ForTypeVariableBinding.this;
                        }

                        public boolean equals(Object obj) {
                            if (this != obj) {
                                if (obj != null && getClass() == obj.getClass()) {
                                    TypeVariableSubstitutor typeVariableSubstitutor = (TypeVariableSubstitutor) obj;
                                    if (!getOuter().equals(typeVariableSubstitutor.getOuter()) || !this.typeVariable.equals(typeVariableSubstitutor.typeVariable)) {
                                    }
                                }
                                return false;
                            }
                            return true;
                        }

                        public int hashCode() {
                            return this.typeVariable.hashCode();
                        }
                    }

                    protected class RetainedMethodTypeVariable extends OfTypeVariable {
                        private final Generic typeVariable;

                        protected RetainedMethodTypeVariable(Generic generic) {
                            this.typeVariable = generic;
                        }

                        @Override // net.bytebuddy.description.type.TypeDescription.Generic
                        public TypeList.Generic getUpperBounds() {
                            return this.typeVariable.getUpperBounds().accept(ForTypeVariableBinding.this);
                        }

                        @Override // net.bytebuddy.description.type.TypeDescription.Generic
                        public TypeVariableSource getTypeVariableSource() {
                            return this.typeVariable.getTypeVariableSource();
                        }

                        @Override // net.bytebuddy.description.type.TypeDescription.Generic
                        public String getSymbol() {
                            return this.typeVariable.getSymbol();
                        }

                        @Override // net.bytebuddy.description.annotation.AnnotationSource
                        public AnnotationList getDeclaredAnnotations() {
                            return this.typeVariable.getDeclaredAnnotations();
                        }
                    }
                }

                public static class ForTokenNormalization extends Substitutor {
                    private final TypeDescription typeDescription;

                    protected boolean canEqual(Object obj) {
                        return obj instanceof ForTokenNormalization;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof ForTokenNormalization)) {
                            return false;
                        }
                        ForTokenNormalization forTokenNormalization = (ForTokenNormalization) obj;
                        if (!forTokenNormalization.canEqual(this)) {
                            return false;
                        }
                        TypeDescription typeDescription = this.typeDescription;
                        TypeDescription typeDescription2 = forTokenNormalization.typeDescription;
                        return typeDescription != null ? typeDescription.equals(typeDescription2) : typeDescription2 == null;
                    }

                    public int hashCode() {
                        TypeDescription typeDescription = this.typeDescription;
                        return 59 + (typeDescription == null ? 43 : typeDescription.hashCode());
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Substitutor, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public /* bridge */ /* synthetic */ Generic onGenericArray(Generic generic) {
                        return super.onGenericArray(generic);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Substitutor, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public /* bridge */ /* synthetic */ Generic onNonGenericType(Generic generic) {
                        return super.onNonGenericType(generic);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Substitutor, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public /* bridge */ /* synthetic */ Generic onParameterizedType(Generic generic) {
                        return super.onParameterizedType(generic);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Substitutor, net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public /* bridge */ /* synthetic */ Generic onWildcard(Generic generic) {
                        return super.onWildcard(generic);
                    }

                    public ForTokenNormalization(TypeDescription typeDescription) {
                        this.typeDescription = typeDescription;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Substitutor
                    protected Generic onSimpleType(Generic generic) {
                        return generic.represents(TargetType.class) ? new OfNonGenericType.Latent(this.typeDescription, generic) : generic;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                    public Generic onTypeVariable(Generic generic) {
                        return new OfTypeVariable.Symbolic(generic.getSymbol(), generic);
                    }
                }
            }

            public static class ForRawType implements Visitor<Generic> {
                private final TypeDescription declaringType;

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Generic onNonGenericType(Generic generic) {
                    return generic;
                }

                public ForRawType(TypeDescription typeDescription) {
                    this.declaringType = typeDescription;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Generic onGenericArray(Generic generic) {
                    return this.declaringType.isGenerified() ? new OfNonGenericType.Latent(generic.asErasure(), generic) : generic;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Generic onWildcard(Generic generic) {
                    throw new IllegalStateException("Did not expect wildcard on top-level: " + generic);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Generic onParameterizedType(Generic generic) {
                    return this.declaringType.isGenerified() ? new OfNonGenericType.Latent(generic.asErasure(), generic) : generic;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public Generic onTypeVariable(Generic generic) {
                    return this.declaringType.isGenerified() ? new OfNonGenericType.Latent(generic.asErasure(), generic) : generic;
                }
            }

            public static class Reducing implements Visitor<TypeDescription> {
                private final TypeDescription declaringType;
                private final List<? extends TypeVariableToken> typeVariableTokens;

                protected boolean canEqual(Object obj) {
                    return obj instanceof Reducing;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Reducing)) {
                        return false;
                    }
                    Reducing reducing = (Reducing) obj;
                    if (!reducing.canEqual(this)) {
                        return false;
                    }
                    TypeDescription typeDescription = this.declaringType;
                    TypeDescription typeDescription2 = reducing.declaringType;
                    if (typeDescription != null ? !typeDescription.equals(typeDescription2) : typeDescription2 != null) {
                        return false;
                    }
                    List<? extends TypeVariableToken> list = this.typeVariableTokens;
                    List<? extends TypeVariableToken> list2 = reducing.typeVariableTokens;
                    return list != null ? list.equals(list2) : list2 == null;
                }

                public int hashCode() {
                    TypeDescription typeDescription = this.declaringType;
                    int iHashCode = typeDescription == null ? 43 : typeDescription.hashCode();
                    List<? extends TypeVariableToken> list = this.typeVariableTokens;
                    return ((iHashCode + 59) * 59) + (list != null ? list.hashCode() : 43);
                }

                public Reducing(TypeDescription typeDescription) {
                    this(typeDescription, Collections.emptyList());
                }

                public Reducing(TypeDescription typeDescription, List<? extends TypeVariableToken> list) {
                    this.declaringType = typeDescription;
                    this.typeVariableTokens = list;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public TypeDescription onGenericArray(Generic generic) {
                    return TargetType.resolve(generic.asErasure(), this.declaringType);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public TypeDescription onWildcard(Generic generic) {
                    throw new IllegalStateException("A wildcard cannot be a top-level type: " + generic);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public TypeDescription onParameterizedType(Generic generic) {
                    return TargetType.resolve(generic.asErasure(), this.declaringType);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public TypeDescription onTypeVariable(Generic generic) {
                    for (TypeVariableToken typeVariableToken : this.typeVariableTokens) {
                        if (generic.getSymbol().equals(typeVariableToken.getSymbol())) {
                            return (TypeDescription) ((Generic) typeVariableToken.getBounds().get(0)).accept(this);
                        }
                    }
                    return TargetType.resolve(this.declaringType.findVariable(generic.getSymbol()).asErasure(), this.declaringType);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
                public TypeDescription onNonGenericType(Generic generic) {
                    return TargetType.resolve(generic.asErasure(), this.declaringType);
                }
            }
        }

        public interface AnnotationReader {
            public static final Dispatcher DISPATCHER = (Dispatcher) AccessController.doPrivileged(Dispatcher.CreationAction.INSTANCE);

            AnnotationList asList();

            AnnotationReader ofComponentType();

            AnnotationReader ofOuterClass();

            AnnotationReader ofOwnerType();

            AnnotationReader ofTypeArgument(int i);

            AnnotationReader ofTypeVariableBoundType(int i);

            AnnotationReader ofWildcardLowerBoundType(int i);

            AnnotationReader ofWildcardUpperBoundType(int i);

            AnnotatedElement resolve();

            public interface Dispatcher {
                Generic resolve(AnnotatedElement annotatedElement);

                AnnotationReader resolveExceptionType(AccessibleObject accessibleObject, int i);

                AnnotationReader resolveFieldType(Field field);

                AnnotationReader resolveInterfaceType(Class<?> cls, int i);

                AnnotationReader resolveParameterType(AccessibleObject accessibleObject, int i);

                Generic resolveReceiverType(AccessibleObject accessibleObject);

                AnnotationReader resolveReturnType(Method method);

                AnnotationReader resolveSuperClassType(Class<?> cls);

                AnnotationReader resolveTypeVariable(TypeVariable<?> typeVariable);

                public enum CreationAction implements PrivilegedAction<Dispatcher> {
                    INSTANCE;

                    @Override // java.security.PrivilegedAction
                    public Dispatcher run() {
                        try {
                            return new ForJava8CapableVm(Class.class.getMethod("getAnnotatedSuperclass", new Class[0]), Class.class.getMethod("getAnnotatedInterfaces", new Class[0]), Field.class.getMethod("getAnnotatedType", new Class[0]), Method.class.getMethod("getAnnotatedReturnType", new Class[0]), Class.forName("java.lang.reflect.Executable").getMethod("getAnnotatedParameterTypes", new Class[0]), Class.forName("java.lang.reflect.Executable").getMethod("getAnnotatedExceptionTypes", new Class[0]), Class.forName("java.lang.reflect.Executable").getMethod("getAnnotatedReceiverType", new Class[0]), Class.forName("java.lang.reflect.AnnotatedType").getMethod("getType", new Class[0]));
                        } catch (RuntimeException e) {
                            throw e;
                        } catch (Exception unused) {
                            return ForLegacyVm.INSTANCE;
                        }
                    }
                }

                public enum ForLegacyVm implements Dispatcher {
                    INSTANCE;

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Dispatcher
                    public AnnotationReader resolveTypeVariable(TypeVariable<?> typeVariable) {
                        return NoOp.INSTANCE;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Dispatcher
                    public AnnotationReader resolveSuperClassType(Class<?> cls) {
                        return NoOp.INSTANCE;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Dispatcher
                    public AnnotationReader resolveInterfaceType(Class<?> cls, int i) {
                        return NoOp.INSTANCE;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Dispatcher
                    public AnnotationReader resolveFieldType(Field field) {
                        return NoOp.INSTANCE;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Dispatcher
                    public AnnotationReader resolveReturnType(Method method) {
                        return NoOp.INSTANCE;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Dispatcher
                    public AnnotationReader resolveParameterType(AccessibleObject accessibleObject, int i) {
                        return NoOp.INSTANCE;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Dispatcher
                    public AnnotationReader resolveExceptionType(AccessibleObject accessibleObject, int i) {
                        return NoOp.INSTANCE;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Dispatcher
                    public Generic resolveReceiverType(AccessibleObject accessibleObject) {
                        return Generic.UNDEFINED;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Dispatcher
                    public Generic resolve(AnnotatedElement annotatedElement) {
                        throw new IllegalStateException("Loaded annotated type cannot be represented on this VM");
                    }
                }

                public static class ForJava8CapableVm implements Dispatcher {
                    private final Method getAnnotatedExceptionTypes;
                    private final Method getAnnotatedInterfaces;
                    private final Method getAnnotatedParameterTypes;
                    private final Method getAnnotatedReceiverType;
                    private final Method getAnnotatedReturnType;
                    private final Method getAnnotatedSuperclass;
                    private final Method getAnnotatedType;
                    private final Method getType;

                    protected boolean canEqual(Object obj) {
                        return obj instanceof ForJava8CapableVm;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof ForJava8CapableVm)) {
                            return false;
                        }
                        ForJava8CapableVm forJava8CapableVm = (ForJava8CapableVm) obj;
                        if (!forJava8CapableVm.canEqual(this)) {
                            return false;
                        }
                        Method method = this.getAnnotatedSuperclass;
                        Method method2 = forJava8CapableVm.getAnnotatedSuperclass;
                        if (method != null ? !method.equals(method2) : method2 != null) {
                            return false;
                        }
                        Method method3 = this.getAnnotatedInterfaces;
                        Method method4 = forJava8CapableVm.getAnnotatedInterfaces;
                        if (method3 != null ? !method3.equals(method4) : method4 != null) {
                            return false;
                        }
                        Method method5 = this.getAnnotatedType;
                        Method method6 = forJava8CapableVm.getAnnotatedType;
                        if (method5 != null ? !method5.equals(method6) : method6 != null) {
                            return false;
                        }
                        Method method7 = this.getAnnotatedReturnType;
                        Method method8 = forJava8CapableVm.getAnnotatedReturnType;
                        if (method7 != null ? !method7.equals(method8) : method8 != null) {
                            return false;
                        }
                        Method method9 = this.getAnnotatedParameterTypes;
                        Method method10 = forJava8CapableVm.getAnnotatedParameterTypes;
                        if (method9 != null ? !method9.equals(method10) : method10 != null) {
                            return false;
                        }
                        Method method11 = this.getAnnotatedExceptionTypes;
                        Method method12 = forJava8CapableVm.getAnnotatedExceptionTypes;
                        if (method11 != null ? !method11.equals(method12) : method12 != null) {
                            return false;
                        }
                        Method method13 = this.getAnnotatedReceiverType;
                        Method method14 = forJava8CapableVm.getAnnotatedReceiverType;
                        if (method13 != null ? !method13.equals(method14) : method14 != null) {
                            return false;
                        }
                        Method method15 = this.getType;
                        Method method16 = forJava8CapableVm.getType;
                        return method15 != null ? method15.equals(method16) : method16 == null;
                    }

                    public int hashCode() {
                        Method method = this.getAnnotatedSuperclass;
                        int iHashCode = method == null ? 43 : method.hashCode();
                        Method method2 = this.getAnnotatedInterfaces;
                        int iHashCode2 = ((iHashCode + 59) * 59) + (method2 == null ? 43 : method2.hashCode());
                        Method method3 = this.getAnnotatedType;
                        int iHashCode3 = (iHashCode2 * 59) + (method3 == null ? 43 : method3.hashCode());
                        Method method4 = this.getAnnotatedReturnType;
                        int iHashCode4 = (iHashCode3 * 59) + (method4 == null ? 43 : method4.hashCode());
                        Method method5 = this.getAnnotatedParameterTypes;
                        int iHashCode5 = (iHashCode4 * 59) + (method5 == null ? 43 : method5.hashCode());
                        Method method6 = this.getAnnotatedExceptionTypes;
                        int iHashCode6 = (iHashCode5 * 59) + (method6 == null ? 43 : method6.hashCode());
                        Method method7 = this.getAnnotatedReceiverType;
                        int iHashCode7 = (iHashCode6 * 59) + (method7 == null ? 43 : method7.hashCode());
                        Method method8 = this.getType;
                        return (iHashCode7 * 59) + (method8 != null ? method8.hashCode() : 43);
                    }

                    protected ForJava8CapableVm(Method method, Method method2, Method method3, Method method4, Method method5, Method method6, Method method7, Method method8) {
                        this.getAnnotatedSuperclass = method;
                        this.getAnnotatedInterfaces = method2;
                        this.getAnnotatedType = method3;
                        this.getAnnotatedReturnType = method4;
                        this.getAnnotatedParameterTypes = method5;
                        this.getAnnotatedExceptionTypes = method6;
                        this.getAnnotatedReceiverType = method7;
                        this.getType = method8;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Dispatcher
                    public AnnotationReader resolveTypeVariable(TypeVariable<?> typeVariable) {
                        return new AnnotatedTypeVariableType(typeVariable);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Dispatcher
                    public AnnotationReader resolveSuperClassType(Class<?> cls) {
                        return new AnnotatedSuperClass(cls);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Dispatcher
                    public AnnotationReader resolveInterfaceType(Class<?> cls, int i) {
                        return new AnnotatedInterfaceType(cls, i);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Dispatcher
                    public AnnotationReader resolveFieldType(Field field) {
                        return new AnnotatedFieldType(field);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Dispatcher
                    public AnnotationReader resolveReturnType(Method method) {
                        return new AnnotatedReturnType(method);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Dispatcher
                    public AnnotationReader resolveParameterType(AccessibleObject accessibleObject, int i) {
                        return new AnnotatedParameterizedType(accessibleObject, i);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Dispatcher
                    public AnnotationReader resolveExceptionType(AccessibleObject accessibleObject, int i) {
                        return new AnnotatedExceptionType(accessibleObject, i);
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Dispatcher
                    public Generic resolveReceiverType(AccessibleObject accessibleObject) {
                        try {
                            return resolve((AnnotatedElement) this.getAnnotatedReceiverType.invoke(accessibleObject, new Object[0]));
                        } catch (IllegalAccessException e) {
                            throw new IllegalStateException("Cannot access java.lang.reflect.Executable#getAnnotatedReceiverType", e);
                        } catch (InvocationTargetException e2) {
                            throw new IllegalStateException("Error invoking java.lang.reflect.Executable#getAnnotatedReceiverType", e2.getCause());
                        }
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Dispatcher
                    public Generic resolve(AnnotatedElement annotatedElement) {
                        Generic genericDescribe;
                        try {
                            if (annotatedElement == null) {
                                genericDescribe = Generic.UNDEFINED;
                            } else {
                                genericDescribe = TypeDefinition.Sort.describe((Type) this.getType.invoke(annotatedElement, new Object[0]), new Resolved(annotatedElement));
                            }
                            return genericDescribe;
                        } catch (IllegalAccessException e) {
                            throw new IllegalStateException("Cannot access java.lang.reflect.AnnotatedType#getType", e);
                        } catch (InvocationTargetException e2) {
                            throw new IllegalStateException("Error invoking java.lang.reflect.AnnotatedType#getType", e2.getCause());
                        }
                    }

                    protected static class Resolved extends Delegator {
                        private final AnnotatedElement annotatedElement;

                        protected boolean canEqual(Object obj) {
                            return obj instanceof Resolved;
                        }

                        public boolean equals(Object obj) {
                            if (obj == this) {
                                return true;
                            }
                            if (!(obj instanceof Resolved)) {
                                return false;
                            }
                            Resolved resolved = (Resolved) obj;
                            if (!resolved.canEqual(this)) {
                                return false;
                            }
                            AnnotatedElement annotatedElement = this.annotatedElement;
                            AnnotatedElement annotatedElement2 = resolved.annotatedElement;
                            return annotatedElement != null ? annotatedElement.equals(annotatedElement2) : annotatedElement2 == null;
                        }

                        public int hashCode() {
                            AnnotatedElement annotatedElement = this.annotatedElement;
                            return 59 + (annotatedElement == null ? 43 : annotatedElement.hashCode());
                        }

                        protected Resolved(AnnotatedElement annotatedElement) {
                            this.annotatedElement = annotatedElement;
                        }

                        @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader
                        public AnnotatedElement resolve() {
                            return this.annotatedElement;
                        }
                    }

                    protected static class AnnotatedTypeVariableType extends Delegator {
                        private final TypeVariable<?> typeVariable;

                        protected boolean canEqual(Object obj) {
                            return obj instanceof AnnotatedTypeVariableType;
                        }

                        public boolean equals(Object obj) {
                            if (obj == this) {
                                return true;
                            }
                            if (!(obj instanceof AnnotatedTypeVariableType)) {
                                return false;
                            }
                            AnnotatedTypeVariableType annotatedTypeVariableType = (AnnotatedTypeVariableType) obj;
                            if (!annotatedTypeVariableType.canEqual(this)) {
                                return false;
                            }
                            TypeVariable<?> typeVariable = this.typeVariable;
                            TypeVariable<?> typeVariable2 = annotatedTypeVariableType.typeVariable;
                            return typeVariable != null ? typeVariable.equals(typeVariable2) : typeVariable2 == null;
                        }

                        public int hashCode() {
                            TypeVariable<?> typeVariable = this.typeVariable;
                            return 59 + (typeVariable == null ? 43 : typeVariable.hashCode());
                        }

                        protected AnnotatedTypeVariableType(TypeVariable<?> typeVariable) {
                            this.typeVariable = typeVariable;
                        }

                        /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.reflect.AnnotatedElement, java.lang.reflect.TypeVariable<?>] */
                        @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader
                        public AnnotatedElement resolve() {
                            return this.typeVariable;
                        }

                        @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Delegator, net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader
                        public AnnotationReader ofTypeVariableBoundType(int i) {
                            return new ForTypeVariableBoundType.OfFormalTypeVariable(this.typeVariable, i);
                        }
                    }

                    protected class AnnotatedSuperClass extends Delegator {
                        private final Class<?> type;

                        protected AnnotatedSuperClass(Class<?> cls) {
                            this.type = cls;
                        }

                        @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader
                        public AnnotatedElement resolve() {
                            try {
                                return (AnnotatedElement) ForJava8CapableVm.this.getAnnotatedSuperclass.invoke(this.type, new Object[0]);
                            } catch (IllegalAccessException e) {
                                throw new IllegalStateException("Cannot access java.lang.Class#getAnnotatedSuperclass", e);
                            } catch (InvocationTargetException e2) {
                                throw new IllegalStateException("Error invoking java.lang.Class#getAnnotatedSuperclass", e2.getCause());
                            }
                        }

                        private ForJava8CapableVm getOuter() {
                            return ForJava8CapableVm.this;
                        }

                        public boolean equals(Object obj) {
                            if (this != obj) {
                                if (obj != null && getClass() == obj.getClass()) {
                                    AnnotatedSuperClass annotatedSuperClass = (AnnotatedSuperClass) obj;
                                    if (!getOuter().equals(annotatedSuperClass.getOuter()) || !this.type.equals(annotatedSuperClass.type)) {
                                    }
                                }
                                return false;
                            }
                            return true;
                        }

                        public int hashCode() {
                            return getOuter().hashCode() + (this.type.hashCode() * 31);
                        }
                    }

                    protected class AnnotatedInterfaceType extends Delegator {
                        private final int index;
                        private final Class<?> type;

                        protected AnnotatedInterfaceType(Class<?> cls, int i) {
                            this.type = cls;
                            this.index = i;
                        }

                        @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader
                        public AnnotatedElement resolve() {
                            try {
                                return (AnnotatedElement) Array.get(ForJava8CapableVm.this.getAnnotatedInterfaces.invoke(this.type, new Object[0]), this.index);
                            } catch (IllegalAccessException e) {
                                throw new IllegalStateException("Cannot access java.lang.Class#getAnnotatedInterfaces", e);
                            } catch (InvocationTargetException e2) {
                                throw new IllegalStateException("Error invoking java.lang.Class#getAnnotatedInterfaces", e2.getCause());
                            }
                        }

                        private ForJava8CapableVm getOuter() {
                            return ForJava8CapableVm.this;
                        }

                        public boolean equals(Object obj) {
                            if (this != obj) {
                                if (obj != null && getClass() == obj.getClass()) {
                                    AnnotatedInterfaceType annotatedInterfaceType = (AnnotatedInterfaceType) obj;
                                    if (!getOuter().equals(annotatedInterfaceType.getOuter()) || !this.type.equals(annotatedInterfaceType.type) || this.index != annotatedInterfaceType.index) {
                                    }
                                }
                                return false;
                            }
                            return true;
                        }

                        public int hashCode() {
                            return ((this.type.hashCode() + (getOuter().hashCode() * 31)) * 31) + this.index;
                        }
                    }

                    protected class AnnotatedFieldType extends Delegator {
                        private final Field field;

                        protected AnnotatedFieldType(Field field) {
                            this.field = field;
                        }

                        @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader
                        public AnnotatedElement resolve() {
                            try {
                                return (AnnotatedElement) ForJava8CapableVm.this.getAnnotatedType.invoke(this.field, new Object[0]);
                            } catch (IllegalAccessException e) {
                                throw new IllegalStateException("Cannot access java.lang.reflect.Field#getAnnotatedType", e);
                            } catch (InvocationTargetException e2) {
                                throw new IllegalStateException("Error invoking java.lang.reflect.Field#getAnnotatedType", e2.getCause());
                            }
                        }

                        private ForJava8CapableVm getOuter() {
                            return ForJava8CapableVm.this;
                        }

                        public boolean equals(Object obj) {
                            if (this != obj) {
                                if (obj != null && getClass() == obj.getClass()) {
                                    AnnotatedFieldType annotatedFieldType = (AnnotatedFieldType) obj;
                                    if (!getOuter().equals(annotatedFieldType.getOuter()) || !this.field.equals(annotatedFieldType.field)) {
                                    }
                                }
                                return false;
                            }
                            return true;
                        }

                        public int hashCode() {
                            return this.field.hashCode() + (getOuter().hashCode() * 31);
                        }
                    }

                    protected class AnnotatedReturnType extends Delegator {
                        private final Method method;

                        protected AnnotatedReturnType(Method method) {
                            this.method = method;
                        }

                        @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader
                        public AnnotatedElement resolve() {
                            try {
                                return (AnnotatedElement) ForJava8CapableVm.this.getAnnotatedReturnType.invoke(this.method, new Object[0]);
                            } catch (IllegalAccessException e) {
                                throw new IllegalStateException("Cannot access java.lang.reflect.Method#getAnnotatedReturnType", e);
                            } catch (InvocationTargetException e2) {
                                throw new IllegalStateException("Error invoking java.lang.reflect.Method#getAnnotatedReturnType", e2.getCause());
                            }
                        }

                        private ForJava8CapableVm getOuter() {
                            return ForJava8CapableVm.this;
                        }

                        public boolean equals(Object obj) {
                            if (this != obj) {
                                if (obj != null && getClass() == obj.getClass()) {
                                    AnnotatedReturnType annotatedReturnType = (AnnotatedReturnType) obj;
                                    if (!getOuter().equals(annotatedReturnType.getOuter()) || !this.method.equals(annotatedReturnType.method)) {
                                    }
                                }
                                return false;
                            }
                            return true;
                        }

                        public int hashCode() {
                            return (this.method.hashCode() * 31) + getOuter().hashCode();
                        }
                    }

                    protected class AnnotatedParameterizedType extends Delegator {
                        private final AccessibleObject executable;
                        private final int index;

                        protected AnnotatedParameterizedType(AccessibleObject accessibleObject, int i) {
                            this.executable = accessibleObject;
                            this.index = i;
                        }

                        @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader
                        public AnnotatedElement resolve() {
                            try {
                                return (AnnotatedElement) Array.get(ForJava8CapableVm.this.getAnnotatedParameterTypes.invoke(this.executable, new Object[0]), this.index);
                            } catch (IllegalAccessException e) {
                                throw new IllegalStateException("Cannot access java.lang.reflect.Executable#getAnnotatedParameterTypes", e);
                            } catch (InvocationTargetException e2) {
                                throw new IllegalStateException("Error invoking java.lang.reflect.Executable#getAnnotatedParameterTypes", e2.getCause());
                            }
                        }

                        private ForJava8CapableVm getOuter() {
                            return ForJava8CapableVm.this;
                        }

                        public boolean equals(Object obj) {
                            if (this != obj) {
                                if (obj != null && getClass() == obj.getClass()) {
                                    AnnotatedParameterizedType annotatedParameterizedType = (AnnotatedParameterizedType) obj;
                                    if (!getOuter().equals(annotatedParameterizedType.getOuter()) || !this.executable.equals(annotatedParameterizedType.executable) || this.index != annotatedParameterizedType.index) {
                                    }
                                }
                                return false;
                            }
                            return true;
                        }

                        public int hashCode() {
                            return ((this.executable.hashCode() + (this.index * 31)) * 31) + getOuter().hashCode();
                        }
                    }

                    protected class AnnotatedExceptionType extends Delegator {
                        private final AccessibleObject executable;
                        private final int index;

                        protected AnnotatedExceptionType(AccessibleObject accessibleObject, int i) {
                            this.executable = accessibleObject;
                            this.index = i;
                        }

                        @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader
                        public AnnotatedElement resolve() {
                            try {
                                return (AnnotatedElement) Array.get(ForJava8CapableVm.this.getAnnotatedExceptionTypes.invoke(this.executable, new Object[0]), this.index);
                            } catch (IllegalAccessException e) {
                                throw new IllegalStateException("Cannot access java.lang.reflect.Executable#getAnnotatedExceptionTypes", e);
                            } catch (InvocationTargetException e2) {
                                throw new IllegalStateException("Error invoking java.lang.reflect.Executable#getAnnotatedExceptionTypes", e2.getCause());
                            }
                        }

                        private ForJava8CapableVm getOuter() {
                            return ForJava8CapableVm.this;
                        }

                        public boolean equals(Object obj) {
                            if (this != obj) {
                                if (obj != null && getClass() == obj.getClass()) {
                                    AnnotatedExceptionType annotatedExceptionType = (AnnotatedExceptionType) obj;
                                    if (!getOuter().equals(annotatedExceptionType.getOuter()) || !this.executable.equals(annotatedExceptionType.executable) || this.index != annotatedExceptionType.index) {
                                    }
                                }
                                return false;
                            }
                            return true;
                        }

                        public int hashCode() {
                            return ((this.executable.hashCode() + (this.index * 31)) * 31) + getOuter().hashCode();
                        }
                    }
                }
            }

            public enum NoOp implements AnnotationReader, AnnotatedElement {
                INSTANCE;

                @Override // java.lang.reflect.AnnotatedElement
                public Annotation[] getDeclaredAnnotations() {
                    return new Annotation[0];
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader
                public AnnotationReader ofComponentType() {
                    return this;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader
                public AnnotationReader ofOuterClass() {
                    return this;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader
                public AnnotationReader ofOwnerType() {
                    return this;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader
                public AnnotationReader ofTypeArgument(int i) {
                    return this;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader
                public AnnotationReader ofTypeVariableBoundType(int i) {
                    return this;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader
                public AnnotationReader ofWildcardLowerBoundType(int i) {
                    return this;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader
                public AnnotationReader ofWildcardUpperBoundType(int i) {
                    return this;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader
                public AnnotatedElement resolve() {
                    return this;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader
                public AnnotationList asList() {
                    return new AnnotationList.Empty();
                }

                @Override // java.lang.reflect.AnnotatedElement
                public boolean isAnnotationPresent(Class<? extends Annotation> cls) {
                    throw new IllegalStateException("Cannot resolve annotations for no-op reader: " + this);
                }

                @Override // java.lang.reflect.AnnotatedElement
                public <T extends Annotation> T getAnnotation(Class<T> cls) {
                    throw new IllegalStateException("Cannot resolve annotations for no-op reader: " + this);
                }

                @Override // java.lang.reflect.AnnotatedElement
                public Annotation[] getAnnotations() {
                    throw new IllegalStateException("Cannot resolve annotations for no-op reader: " + this);
                }
            }

            public static abstract class Delegator implements AnnotationReader {
                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader
                public AnnotationReader ofWildcardUpperBoundType(int i) {
                    return new ForWildcardUpperBoundType(this, i);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader
                public AnnotationReader ofWildcardLowerBoundType(int i) {
                    return new ForWildcardLowerBoundType(this, i);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader
                public AnnotationReader ofTypeVariableBoundType(int i) {
                    return new ForTypeVariableBoundType(this, i);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader
                public AnnotationReader ofTypeArgument(int i) {
                    return new ForTypeArgument(this, i);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader
                public AnnotationReader ofOwnerType() {
                    return ForOwnerType.of(this);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader
                public AnnotationReader ofOuterClass() {
                    return ForOwnerType.of(this);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader
                public AnnotationReader ofComponentType() {
                    return new ForComponentType(this);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader
                public AnnotationList asList() {
                    return new AnnotationList.ForLoadedAnnotations(resolve().getDeclaredAnnotations());
                }

                protected static abstract class Chained extends Delegator {
                    protected static final Method NOT_AVAILABLE = null;
                    protected final AnnotationReader annotationReader;

                    protected abstract AnnotatedElement resolve(AnnotatedElement annotatedElement);

                    protected boolean canEqual(Object obj) {
                        return obj instanceof Chained;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof Chained)) {
                            return false;
                        }
                        Chained chained = (Chained) obj;
                        if (!chained.canEqual(this)) {
                            return false;
                        }
                        AnnotationReader annotationReader = this.annotationReader;
                        AnnotationReader annotationReader2 = chained.annotationReader;
                        return annotationReader != null ? annotationReader.equals(annotationReader2) : annotationReader2 == null;
                    }

                    public int hashCode() {
                        AnnotationReader annotationReader = this.annotationReader;
                        return 59 + (annotationReader == null ? 43 : annotationReader.hashCode());
                    }

                    protected Chained(AnnotationReader annotationReader) {
                        this.annotationReader = annotationReader;
                    }

                    protected static Method of(String str, String str2) {
                        try {
                            return Class.forName(str).getMethod(str2, new Class[0]);
                        } catch (Exception unused) {
                            return NOT_AVAILABLE;
                        }
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader
                    public AnnotatedElement resolve() {
                        return resolve(this.annotationReader.resolve());
                    }
                }
            }

            public static class ForWildcardUpperBoundType extends Delegator.Chained {
                private static final Method GET_ANNOTATED_UPPER_BOUNDS = of("java.lang.reflect.AnnotatedWildcardType", "getAnnotatedUpperBounds");
                private final int index;

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Delegator.Chained
                protected boolean canEqual(Object obj) {
                    return obj instanceof ForWildcardUpperBoundType;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Delegator.Chained
                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForWildcardUpperBoundType)) {
                        return false;
                    }
                    ForWildcardUpperBoundType forWildcardUpperBoundType = (ForWildcardUpperBoundType) obj;
                    return forWildcardUpperBoundType.canEqual(this) && super.equals(obj) && this.index == forWildcardUpperBoundType.index;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Delegator.Chained
                public int hashCode() {
                    return ((super.hashCode() + 59) * 59) + this.index;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Delegator.Chained, net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader
                public /* bridge */ /* synthetic */ AnnotatedElement resolve() {
                    return super.resolve();
                }

                protected ForWildcardUpperBoundType(AnnotationReader annotationReader, int i) {
                    super(annotationReader);
                    this.index = i;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Delegator.Chained
                protected AnnotatedElement resolve(AnnotatedElement annotatedElement) {
                    try {
                        Object objInvoke = GET_ANNOTATED_UPPER_BOUNDS.invoke(annotatedElement, new Object[0]);
                        return Array.getLength(objInvoke) == 0 ? NoOp.INSTANCE : (AnnotatedElement) Array.get(objInvoke, this.index);
                    } catch (ClassCastException unused) {
                        return NoOp.INSTANCE;
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.reflect.AnnotatedWildcardType#getAnnotatedUpperBounds", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.reflect.AnnotatedWildcardType#getAnnotatedUpperBounds", e2.getCause());
                    }
                }
            }

            public static class ForWildcardLowerBoundType extends Delegator.Chained {
                private static final Method GET_ANNOTATED_LOWER_BOUNDS = of("java.lang.reflect.AnnotatedWildcardType", "getAnnotatedLowerBounds");
                private final int index;

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Delegator.Chained
                protected boolean canEqual(Object obj) {
                    return obj instanceof ForWildcardLowerBoundType;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Delegator.Chained
                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForWildcardLowerBoundType)) {
                        return false;
                    }
                    ForWildcardLowerBoundType forWildcardLowerBoundType = (ForWildcardLowerBoundType) obj;
                    return forWildcardLowerBoundType.canEqual(this) && super.equals(obj) && this.index == forWildcardLowerBoundType.index;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Delegator.Chained
                public int hashCode() {
                    return ((super.hashCode() + 59) * 59) + this.index;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Delegator.Chained, net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader
                public /* bridge */ /* synthetic */ AnnotatedElement resolve() {
                    return super.resolve();
                }

                protected ForWildcardLowerBoundType(AnnotationReader annotationReader, int i) {
                    super(annotationReader);
                    this.index = i;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Delegator.Chained
                protected AnnotatedElement resolve(AnnotatedElement annotatedElement) {
                    try {
                        return (AnnotatedElement) Array.get(GET_ANNOTATED_LOWER_BOUNDS.invoke(annotatedElement, new Object[0]), this.index);
                    } catch (ClassCastException unused) {
                        return NoOp.INSTANCE;
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.reflect.AnnotatedWildcardType#getAnnotatedLowerBounds", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.reflect.AnnotatedWildcardType#getAnnotatedLowerBounds", e2.getCause());
                    }
                }
            }

            public static class ForTypeVariableBoundType extends Delegator.Chained {
                private static final Method GET_ANNOTATED_BOUNDS = of("java.lang.reflect.AnnotatedTypeVariable", "getAnnotatedBounds");
                private final int index;

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Delegator.Chained
                protected boolean canEqual(Object obj) {
                    return obj instanceof ForTypeVariableBoundType;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Delegator.Chained
                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForTypeVariableBoundType)) {
                        return false;
                    }
                    ForTypeVariableBoundType forTypeVariableBoundType = (ForTypeVariableBoundType) obj;
                    return forTypeVariableBoundType.canEqual(this) && super.equals(obj) && this.index == forTypeVariableBoundType.index;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Delegator.Chained
                public int hashCode() {
                    return ((super.hashCode() + 59) * 59) + this.index;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Delegator.Chained, net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader
                public /* bridge */ /* synthetic */ AnnotatedElement resolve() {
                    return super.resolve();
                }

                protected ForTypeVariableBoundType(AnnotationReader annotationReader, int i) {
                    super(annotationReader);
                    this.index = i;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Delegator.Chained
                protected AnnotatedElement resolve(AnnotatedElement annotatedElement) {
                    try {
                        return (AnnotatedElement) Array.get(GET_ANNOTATED_BOUNDS.invoke(annotatedElement, new Object[0]), this.index);
                    } catch (ClassCastException unused) {
                        return NoOp.INSTANCE;
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.reflect.AnnotatedTypeVariable#getAnnotatedBounds", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.reflect.AnnotatedTypeVariable#getAnnotatedBounds", e2.getCause());
                    }
                }

                protected static class OfFormalTypeVariable extends Delegator {
                    private static final Method GET_ANNOTATED_BOUNDS = Delegator.Chained.of(TypeVariable.class.getName(), "getAnnotatedBounds");
                    private final int index;
                    private final TypeVariable<?> typeVariable;

                    protected boolean canEqual(Object obj) {
                        return obj instanceof OfFormalTypeVariable;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof OfFormalTypeVariable)) {
                            return false;
                        }
                        OfFormalTypeVariable ofFormalTypeVariable = (OfFormalTypeVariable) obj;
                        if (!ofFormalTypeVariable.canEqual(this)) {
                            return false;
                        }
                        TypeVariable<?> typeVariable = this.typeVariable;
                        TypeVariable<?> typeVariable2 = ofFormalTypeVariable.typeVariable;
                        if (typeVariable != null ? typeVariable.equals(typeVariable2) : typeVariable2 == null) {
                            return this.index == ofFormalTypeVariable.index;
                        }
                        return false;
                    }

                    public int hashCode() {
                        TypeVariable<?> typeVariable = this.typeVariable;
                        return (((typeVariable == null ? 43 : typeVariable.hashCode()) + 59) * 59) + this.index;
                    }

                    protected OfFormalTypeVariable(TypeVariable<?> typeVariable, int i) {
                        this.typeVariable = typeVariable;
                        this.index = i;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader
                    public AnnotatedElement resolve() {
                        try {
                            return (AnnotatedElement) Array.get(GET_ANNOTATED_BOUNDS.invoke(this.typeVariable, new Object[0]), this.index);
                        } catch (ClassCastException unused) {
                            return NoOp.INSTANCE;
                        } catch (IllegalAccessException e) {
                            throw new IllegalStateException("Cannot access java.lang.reflect.TypeVariable#getAnnotatedBounds", e);
                        } catch (InvocationTargetException e2) {
                            throw new IllegalStateException("Error invoking java.lang.reflect.TypeVariable#getAnnotatedBounds", e2.getCause());
                        }
                    }
                }
            }

            public static class ForTypeArgument extends Delegator.Chained {
                private static final Method GET_ANNOTATED_ACTUAL_TYPE_ARGUMENTS = of("java.lang.reflect.AnnotatedParameterizedType", "getAnnotatedActualTypeArguments");
                private final int index;

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Delegator.Chained
                protected boolean canEqual(Object obj) {
                    return obj instanceof ForTypeArgument;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Delegator.Chained
                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForTypeArgument)) {
                        return false;
                    }
                    ForTypeArgument forTypeArgument = (ForTypeArgument) obj;
                    return forTypeArgument.canEqual(this) && super.equals(obj) && this.index == forTypeArgument.index;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Delegator.Chained
                public int hashCode() {
                    return ((super.hashCode() + 59) * 59) + this.index;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Delegator.Chained, net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader
                public /* bridge */ /* synthetic */ AnnotatedElement resolve() {
                    return super.resolve();
                }

                protected ForTypeArgument(AnnotationReader annotationReader, int i) {
                    super(annotationReader);
                    this.index = i;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Delegator.Chained
                protected AnnotatedElement resolve(AnnotatedElement annotatedElement) {
                    try {
                        return (AnnotatedElement) Array.get(GET_ANNOTATED_ACTUAL_TYPE_ARGUMENTS.invoke(annotatedElement, new Object[0]), this.index);
                    } catch (ClassCastException unused) {
                        return NoOp.INSTANCE;
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.reflect.AnnotatedParameterizedType#getAnnotatedActualTypeArguments", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.reflect.AnnotatedParameterizedType#getAnnotatedActualTypeArguments", e2.getCause());
                    }
                }
            }

            public static class ForComponentType extends Delegator.Chained {
                private static final Method GET_ANNOTATED_GENERIC_COMPONENT_TYPE = of("java.lang.reflect.AnnotatedArrayType", "getAnnotatedGenericComponentType");

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Delegator.Chained
                public /* bridge */ /* synthetic */ boolean equals(Object obj) {
                    return super.equals(obj);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Delegator.Chained
                public /* bridge */ /* synthetic */ int hashCode() {
                    return super.hashCode();
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Delegator.Chained, net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader
                public /* bridge */ /* synthetic */ AnnotatedElement resolve() {
                    return super.resolve();
                }

                protected ForComponentType(AnnotationReader annotationReader) {
                    super(annotationReader);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Delegator.Chained
                protected AnnotatedElement resolve(AnnotatedElement annotatedElement) {
                    try {
                        return (AnnotatedElement) GET_ANNOTATED_GENERIC_COMPONENT_TYPE.invoke(annotatedElement, new Object[0]);
                    } catch (ClassCastException unused) {
                        return NoOp.INSTANCE;
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.reflect.AnnotatedArrayType#getAnnotatedGenericComponentType", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.reflect.AnnotatedArrayType#getAnnotatedGenericComponentType", e2.getCause());
                    }
                }
            }

            public static class ForOwnerType extends Delegator.Chained {
                private static final Method GET_ANNOTATED_OWNER_TYPE = of("java.lang.reflect.AnnotatedType", "getAnnotatedOwnerType");

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Delegator.Chained
                public /* bridge */ /* synthetic */ boolean equals(Object obj) {
                    return super.equals(obj);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Delegator.Chained
                public /* bridge */ /* synthetic */ int hashCode() {
                    return super.hashCode();
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Delegator.Chained, net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader
                public /* bridge */ /* synthetic */ AnnotatedElement resolve() {
                    return super.resolve();
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static AnnotationReader of(AnnotationReader annotationReader) {
                    return GET_ANNOTATED_OWNER_TYPE == null ? NoOp.INSTANCE : new ForOwnerType(annotationReader);
                }

                protected ForOwnerType(AnnotationReader annotationReader) {
                    super(annotationReader);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Delegator.Chained
                protected AnnotatedElement resolve(AnnotatedElement annotatedElement) {
                    try {
                        AnnotatedElement annotatedElement2 = (AnnotatedElement) GET_ANNOTATED_OWNER_TYPE.invoke(annotatedElement, new Object[0]);
                        return annotatedElement2 == null ? NoOp.INSTANCE : annotatedElement2;
                    } catch (ClassCastException unused) {
                        return NoOp.INSTANCE;
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.reflect.AnnotatedType#getAnnotatedOwnerType", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.reflect.AnnotatedType#getAnnotatedOwnerType", e2.getCause());
                    }
                }
            }
        }

        public static abstract class AbstractBase extends ModifierReviewable.AbstractBase implements Generic {
            @Override // net.bytebuddy.description.type.TypeDefinition
            public Generic asGenericType() {
                return this;
            }

            @Override // net.bytebuddy.description.ModifierReviewable
            public int getModifiers() {
                return asErasure().getModifiers();
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public Generic asRawType() {
                return asErasure().asGenericType();
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public boolean represents(Type type) {
                return equals(TypeDefinition.Sort.describe(type));
            }
        }

        public static abstract class OfNonGenericType extends AbstractBase {
            @Override // net.bytebuddy.description.type.TypeDefinition
            public TypeDefinition.Sort getSort() {
                return TypeDefinition.Sort.NON_GENERIC;
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public Generic getSuperClass() {
                TypeDescription typeDescriptionAsErasure = asErasure();
                Generic superClass = typeDescriptionAsErasure.getSuperClass();
                return AbstractBase.RAW_TYPES ? superClass : superClass == null ? Generic.UNDEFINED : new LazyProjection.WithResolvedErasure(superClass, new Visitor.ForRawType(typeDescriptionAsErasure), AnnotationSource.Empty.INSTANCE);
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public TypeList.Generic getInterfaces() {
                TypeDescription typeDescriptionAsErasure = asErasure();
                if (AbstractBase.RAW_TYPES) {
                    return typeDescriptionAsErasure.getInterfaces();
                }
                return new TypeList.Generic.ForDetachedTypes.WithResolvedErasure(typeDescriptionAsErasure.getInterfaces(), new Visitor.ForRawType(typeDescriptionAsErasure));
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic, net.bytebuddy.description.type.TypeDefinition
            public FieldList<FieldDescription.InGenericShape> getDeclaredFields() {
                TypeDescription typeDescriptionAsErasure = asErasure();
                return new FieldList.TypeSubstituting(this, typeDescriptionAsErasure.getDeclaredFields(), AbstractBase.RAW_TYPES ? Visitor.NoOp.INSTANCE : new Visitor.ForRawType(typeDescriptionAsErasure));
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic, net.bytebuddy.description.type.TypeDefinition
            public MethodList<MethodDescription.InGenericShape> getDeclaredMethods() {
                TypeDescription typeDescriptionAsErasure = asErasure();
                return new MethodList.TypeSubstituting(this, typeDescriptionAsErasure.getDeclaredMethods(), AbstractBase.RAW_TYPES ? Visitor.NoOp.INSTANCE : new Visitor.ForRawType(typeDescriptionAsErasure));
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public TypeList.Generic getTypeArguments() {
                throw new IllegalStateException("A non-generic type does not imply type arguments: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public Generic findBindingOf(Generic generic) {
                throw new IllegalStateException("A non-generic type does not imply type arguments: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public <T> T accept(Visitor<T> visitor) {
                return visitor.onNonGenericType(this);
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public String getTypeName() {
                return asErasure().getTypeName();
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public TypeList.Generic getUpperBounds() {
                throw new IllegalStateException("A non-generic type does not imply upper type bounds: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public TypeList.Generic getLowerBounds() {
                throw new IllegalStateException("A non-generic type does not imply lower type bounds: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public TypeVariableSource getTypeVariableSource() {
                throw new IllegalStateException("A non-generic type does not imply a type variable source: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public String getSymbol() {
                throw new IllegalStateException("A non-generic type does not imply a symbol: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public StackSize getStackSize() {
                return asErasure().getStackSize();
            }

            @Override // net.bytebuddy.description.NamedElement
            public String getActualName() {
                return asErasure().getActualName();
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public boolean isArray() {
                return asErasure().isArray();
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public boolean isPrimitive() {
                return asErasure().isPrimitive();
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic.AbstractBase, net.bytebuddy.description.type.TypeDefinition
            public boolean represents(Type type) {
                return asErasure().represents(type);
            }

            @Override // java.lang.Iterable
            public Iterator<TypeDefinition> iterator() {
                return new TypeDefinition.SuperClassIterator(this);
            }

            public int hashCode() {
                return asErasure().hashCode();
            }

            public boolean equals(Object obj) {
                return asErasure().equals(obj);
            }

            public String toString() {
                return asErasure().toString();
            }

            public static class ForLoadedType extends OfNonGenericType {
                private final AnnotationReader annotationReader;
                private final Class<?> type;

                public ForLoadedType(Class<?> cls) {
                    this(cls, AnnotationReader.NoOp.INSTANCE);
                }

                protected ForLoadedType(Class<?> cls, AnnotationReader annotationReader) {
                    this.type = cls;
                    this.annotationReader = annotationReader;
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public TypeDescription asErasure() {
                    return new ForLoadedType(this.type);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic
                public Generic getOwnerType() {
                    Class<?> declaringClass = this.type.getDeclaringClass();
                    if (declaringClass == null) {
                        return Generic.UNDEFINED;
                    }
                    return new ForLoadedType(declaringClass, this.annotationReader.ofOuterClass());
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public Generic getComponentType() {
                    Class<?> componentType = this.type.getComponentType();
                    if (componentType == null) {
                        return Generic.UNDEFINED;
                    }
                    return new ForLoadedType(componentType, this.annotationReader.ofComponentType());
                }

                @Override // net.bytebuddy.description.annotation.AnnotationSource
                public AnnotationList getDeclaredAnnotations() {
                    return this.annotationReader.asList();
                }
            }

            public static class ForErasure extends OfNonGenericType {
                private final TypeDescription typeDescription;

                public ForErasure(TypeDescription typeDescription) {
                    this.typeDescription = typeDescription;
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public TypeDescription asErasure() {
                    return this.typeDescription;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic
                public Generic getOwnerType() {
                    TypeDescription declaringType = this.typeDescription.getDeclaringType();
                    if (declaringType == null) {
                        return Generic.UNDEFINED;
                    }
                    return declaringType.asGenericType();
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public Generic getComponentType() {
                    TypeDescription componentType = this.typeDescription.getComponentType();
                    if (componentType == null) {
                        return Generic.UNDEFINED;
                    }
                    return componentType.asGenericType();
                }

                @Override // net.bytebuddy.description.annotation.AnnotationSource
                public AnnotationList getDeclaredAnnotations() {
                    return new AnnotationList.Empty();
                }
            }

            public static class Latent extends OfNonGenericType {
                private final AnnotationSource annotationSource;
                private final Generic declaringType;
                private final TypeDescription typeDescription;

                public Latent(TypeDescription typeDescription, AnnotationSource annotationSource) {
                    this(typeDescription, typeDescription.getDeclaringType(), annotationSource);
                }

                /* JADX WARN: Illegal instructions before constructor call */
                private Latent(TypeDescription typeDescription, TypeDescription typeDescription2, AnnotationSource annotationSource) {
                    Generic genericAsGenericType;
                    if (typeDescription2 == null) {
                        genericAsGenericType = Generic.UNDEFINED;
                    } else {
                        genericAsGenericType = typeDescription2.asGenericType();
                    }
                    this(typeDescription, genericAsGenericType, annotationSource);
                }

                protected Latent(TypeDescription typeDescription, Generic generic, AnnotationSource annotationSource) {
                    this.typeDescription = typeDescription;
                    this.declaringType = generic;
                    this.annotationSource = annotationSource;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic
                public Generic getOwnerType() {
                    return this.declaringType;
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public Generic getComponentType() {
                    TypeDescription componentType = this.typeDescription.getComponentType();
                    if (componentType == null) {
                        return Generic.UNDEFINED;
                    }
                    return componentType.asGenericType();
                }

                @Override // net.bytebuddy.description.annotation.AnnotationSource
                public AnnotationList getDeclaredAnnotations() {
                    return this.annotationSource.getDeclaredAnnotations();
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public TypeDescription asErasure() {
                    return this.typeDescription;
                }
            }

            public static class ForReifiedErasure extends OfNonGenericType {
                private final TypeDescription typeDescription;

                protected ForReifiedErasure(TypeDescription typeDescription) {
                    this.typeDescription = typeDescription;
                }

                protected static Generic of(TypeDescription typeDescription) {
                    return typeDescription.isGenerified() ? new ForReifiedErasure(typeDescription) : new ForErasure(typeDescription);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.OfNonGenericType, net.bytebuddy.description.type.TypeDefinition
                public Generic getSuperClass() {
                    Generic superClass = this.typeDescription.getSuperClass();
                    return superClass == null ? Generic.UNDEFINED : new LazyProjection.WithResolvedErasure(superClass, Visitor.Reifying.INHERITING);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.OfNonGenericType, net.bytebuddy.description.type.TypeDefinition
                public TypeList.Generic getInterfaces() {
                    return new TypeList.Generic.ForDetachedTypes.WithResolvedErasure(this.typeDescription.getInterfaces(), Visitor.Reifying.INHERITING);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.OfNonGenericType, net.bytebuddy.description.type.TypeDescription.Generic, net.bytebuddy.description.type.TypeDefinition
                public FieldList<FieldDescription.InGenericShape> getDeclaredFields() {
                    return new FieldList.TypeSubstituting(this, this.typeDescription.getDeclaredFields(), Visitor.TypeErasing.INSTANCE);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.OfNonGenericType, net.bytebuddy.description.type.TypeDescription.Generic, net.bytebuddy.description.type.TypeDefinition
                public MethodList<MethodDescription.InGenericShape> getDeclaredMethods() {
                    return new MethodList.TypeSubstituting(this, this.typeDescription.getDeclaredMethods(), Visitor.TypeErasing.INSTANCE);
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public TypeDescription asErasure() {
                    return this.typeDescription;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic
                public Generic getOwnerType() {
                    TypeDescription declaringType = this.typeDescription.getDeclaringType();
                    if (declaringType == null) {
                        return Generic.UNDEFINED;
                    }
                    return of(declaringType);
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public Generic getComponentType() {
                    TypeDescription componentType = this.typeDescription.getComponentType();
                    if (componentType == null) {
                        return Generic.UNDEFINED;
                    }
                    return of(componentType);
                }

                @Override // net.bytebuddy.description.annotation.AnnotationSource
                public AnnotationList getDeclaredAnnotations() {
                    return new AnnotationList.Empty();
                }
            }
        }

        public static abstract class OfGenericArray extends AbstractBase {
            @Override // net.bytebuddy.description.type.TypeDefinition
            public boolean isArray() {
                return true;
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public boolean isPrimitive() {
                return false;
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public TypeDefinition.Sort getSort() {
                return getComponentType().getSort().isNonGeneric() ? TypeDefinition.Sort.NON_GENERIC : TypeDefinition.Sort.GENERIC_ARRAY;
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public TypeDescription asErasure() {
                return ArrayProjection.of(getComponentType().asErasure(), 1);
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public Generic getSuperClass() {
                return Generic.OBJECT;
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public TypeList.Generic getInterfaces() {
                return TypeDescription.ARRAY_INTERFACES;
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic, net.bytebuddy.description.type.TypeDefinition
            public FieldList<FieldDescription.InGenericShape> getDeclaredFields() {
                return new FieldList.Empty();
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic, net.bytebuddy.description.type.TypeDefinition
            public MethodList<MethodDescription.InGenericShape> getDeclaredMethods() {
                return new MethodList.Empty();
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public TypeList.Generic getUpperBounds() {
                throw new IllegalStateException("A generic array type does not imply upper type bounds: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public TypeList.Generic getLowerBounds() {
                throw new IllegalStateException("A generic array type does not imply lower type bounds: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public TypeVariableSource getTypeVariableSource() {
                throw new IllegalStateException("A generic array type does not imply a type variable source: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public TypeList.Generic getTypeArguments() {
                throw new IllegalStateException("A generic array type does not imply type arguments: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public Generic findBindingOf(Generic generic) {
                throw new IllegalStateException("A generic array type does not imply type arguments: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public Generic getOwnerType() {
                return Generic.UNDEFINED;
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public String getSymbol() {
                throw new IllegalStateException("A generic array type does not imply a symbol: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public String getTypeName() {
                if (getSort().isNonGeneric()) {
                    return asErasure().getTypeName();
                }
                return toString();
            }

            @Override // net.bytebuddy.description.NamedElement
            public String getActualName() {
                if (getSort().isNonGeneric()) {
                    return asErasure().getActualName();
                }
                return toString();
            }

            @Override // java.lang.Iterable
            public Iterator<TypeDefinition> iterator() {
                return new TypeDefinition.SuperClassIterator(this);
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public <T> T accept(Visitor<T> visitor) {
                if (getSort().isNonGeneric()) {
                    return visitor.onNonGenericType(this);
                }
                return visitor.onGenericArray(this);
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public StackSize getStackSize() {
                return StackSize.SINGLE;
            }

            public boolean equals(Object obj) {
                if (getSort().isNonGeneric()) {
                    return asErasure().equals(obj);
                }
                if (!(obj instanceof Generic)) {
                    return false;
                }
                Generic generic = (Generic) obj;
                return generic.getSort().isGenericArray() && getComponentType().equals(generic.getComponentType());
            }

            public int hashCode() {
                if (getSort().isNonGeneric()) {
                    return asErasure().hashCode();
                }
                return getComponentType().hashCode();
            }

            public String toString() {
                if (getSort().isNonGeneric()) {
                    return asErasure().toString();
                }
                return getComponentType().getTypeName() + HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
            }

            public static class ForLoadedType extends OfGenericArray {
                private final AnnotationReader annotationReader;
                private final GenericArrayType genericArrayType;

                public ForLoadedType(GenericArrayType genericArrayType) {
                    this(genericArrayType, AnnotationReader.NoOp.INSTANCE);
                }

                protected ForLoadedType(GenericArrayType genericArrayType, AnnotationReader annotationReader) {
                    this.genericArrayType = genericArrayType;
                    this.annotationReader = annotationReader;
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public Generic getComponentType() {
                    return TypeDefinition.Sort.describe(this.genericArrayType.getGenericComponentType(), this.annotationReader.ofComponentType());
                }

                @Override // net.bytebuddy.description.annotation.AnnotationSource
                public AnnotationList getDeclaredAnnotations() {
                    return this.annotationReader.asList();
                }
            }

            public static class Latent extends OfGenericArray {
                private final AnnotationSource annotationSource;
                private final Generic componentType;

                public Latent(Generic generic, AnnotationSource annotationSource) {
                    this.componentType = generic;
                    this.annotationSource = annotationSource;
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public Generic getComponentType() {
                    return this.componentType;
                }

                @Override // net.bytebuddy.description.annotation.AnnotationSource
                public AnnotationList getDeclaredAnnotations() {
                    return this.annotationSource.getDeclaredAnnotations();
                }
            }
        }

        public static abstract class OfWildcardType extends AbstractBase {
            public static final String SYMBOL = "?";

            @Override // net.bytebuddy.description.type.TypeDefinition
            public boolean isArray() {
                return false;
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public boolean isPrimitive() {
                return false;
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public TypeDefinition.Sort getSort() {
                return TypeDefinition.Sort.WILDCARD;
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public TypeDescription asErasure() {
                throw new IllegalStateException("A wildcard does not represent an erasable type: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public Generic getSuperClass() {
                throw new IllegalStateException("A wildcard does not imply a super type definition: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public TypeList.Generic getInterfaces() {
                throw new IllegalStateException("A wildcard does not imply an interface type definition: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic, net.bytebuddy.description.type.TypeDefinition
            public FieldList<FieldDescription.InGenericShape> getDeclaredFields() {
                throw new IllegalStateException("A wildcard does not imply field definitions: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic, net.bytebuddy.description.type.TypeDefinition
            public MethodList<MethodDescription.InGenericShape> getDeclaredMethods() {
                throw new IllegalStateException("A wildcard does not imply method definitions: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public Generic getComponentType() {
                throw new IllegalStateException("A wildcard does not imply a component type: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public TypeVariableSource getTypeVariableSource() {
                throw new IllegalStateException("A wildcard does not imply a type variable source: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public TypeList.Generic getTypeArguments() {
                throw new IllegalStateException("A wildcard does not imply type arguments: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public Generic findBindingOf(Generic generic) {
                throw new IllegalStateException("A wildcard does not imply type arguments: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public Generic getOwnerType() {
                throw new IllegalStateException("A wildcard does not imply an owner type: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public String getSymbol() {
                throw new IllegalStateException("A wildcard does not imply a symbol: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public String getTypeName() {
                return toString();
            }

            @Override // net.bytebuddy.description.NamedElement
            public String getActualName() {
                return toString();
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic.AbstractBase, net.bytebuddy.description.type.TypeDefinition
            public boolean represents(Type type) {
                return equals(TypeDefinition.Sort.describe(type));
            }

            @Override // java.lang.Iterable
            public Iterator<TypeDefinition> iterator() {
                throw new IllegalStateException("A wildcard does not imply a super type definition: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public <T> T accept(Visitor<T> visitor) {
                return visitor.onWildcard(this);
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public StackSize getStackSize() {
                throw new IllegalStateException("A wildcard does not imply an operand stack size: " + this);
            }

            public int hashCode() {
                Iterator it = getLowerBounds().iterator();
                int iHashCode = 1;
                int iHashCode2 = 1;
                while (it.hasNext()) {
                    iHashCode2 = (iHashCode2 * 31) + ((Generic) it.next()).hashCode();
                }
                Iterator it2 = getUpperBounds().iterator();
                while (it2.hasNext()) {
                    iHashCode = (iHashCode * 31) + ((Generic) it2.next()).hashCode();
                }
                return iHashCode2 ^ iHashCode;
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof Generic)) {
                    return false;
                }
                Generic generic = (Generic) obj;
                return generic.getSort().isWildcard() && getUpperBounds().equals(generic.getUpperBounds()) && getLowerBounds().equals(generic.getLowerBounds());
            }

            public String toString() {
                StringBuilder sb = new StringBuilder(SYMBOL);
                TypeList.Generic lowerBounds = getLowerBounds();
                if (!lowerBounds.isEmpty()) {
                    sb.append(" super ");
                } else {
                    lowerBounds = getUpperBounds();
                    if (lowerBounds.getOnly().equals(Generic.OBJECT)) {
                        return SYMBOL;
                    }
                    sb.append(" extends ");
                }
                return sb.append(lowerBounds.getOnly().getTypeName()).toString();
            }

            public static class ForLoadedType extends OfWildcardType {
                private final AnnotationReader annotationReader;
                private final WildcardType wildcardType;

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.OfWildcardType, net.bytebuddy.description.type.TypeDefinition
                public /* bridge */ /* synthetic */ TypeDefinition getComponentType() {
                    return super.getComponentType();
                }

                public ForLoadedType(WildcardType wildcardType) {
                    this(wildcardType, AnnotationReader.NoOp.INSTANCE);
                }

                protected ForLoadedType(WildcardType wildcardType, AnnotationReader annotationReader) {
                    this.wildcardType = wildcardType;
                    this.annotationReader = annotationReader;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic
                public TypeList.Generic getUpperBounds() {
                    return new WildcardUpperBoundTypeList(this.wildcardType.getUpperBounds(), this.annotationReader);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic
                public TypeList.Generic getLowerBounds() {
                    return new WildcardLowerBoundTypeList(this.wildcardType.getLowerBounds(), this.annotationReader);
                }

                @Override // net.bytebuddy.description.annotation.AnnotationSource
                public AnnotationList getDeclaredAnnotations() {
                    return this.annotationReader.asList();
                }

                protected static class WildcardUpperBoundTypeList extends TypeList.Generic.AbstractBase {
                    private final AnnotationReader annotationReader;
                    private final Type[] upperBound;

                    protected WildcardUpperBoundTypeList(Type[] typeArr, AnnotationReader annotationReader) {
                        this.upperBound = typeArr;
                        this.annotationReader = annotationReader;
                    }

                    @Override // java.util.AbstractList, java.util.List
                    public Generic get(int i) {
                        return TypeDefinition.Sort.describe(this.upperBound[i], this.annotationReader.ofWildcardUpperBoundType(i));
                    }

                    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                    public int size() {
                        return this.upperBound.length;
                    }
                }

                protected static class WildcardLowerBoundTypeList extends TypeList.Generic.AbstractBase {
                    private final AnnotationReader annotationReader;
                    private final Type[] lowerBound;

                    protected WildcardLowerBoundTypeList(Type[] typeArr, AnnotationReader annotationReader) {
                        this.lowerBound = typeArr;
                        this.annotationReader = annotationReader;
                    }

                    @Override // java.util.AbstractList, java.util.List
                    public Generic get(int i) {
                        return TypeDefinition.Sort.describe(this.lowerBound[i], this.annotationReader.ofWildcardLowerBoundType(i));
                    }

                    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                    public int size() {
                        return this.lowerBound.length;
                    }
                }
            }

            public static class Latent extends OfWildcardType {
                private final AnnotationSource annotationSource;
                private final List<? extends Generic> lowerBounds;
                private final List<? extends Generic> upperBounds;

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.OfWildcardType, net.bytebuddy.description.type.TypeDefinition
                public /* bridge */ /* synthetic */ TypeDefinition getComponentType() {
                    return super.getComponentType();
                }

                protected Latent(List<? extends Generic> list, List<? extends Generic> list2, AnnotationSource annotationSource) {
                    this.upperBounds = list;
                    this.lowerBounds = list2;
                    this.annotationSource = annotationSource;
                }

                public static Generic unbounded(AnnotationSource annotationSource) {
                    return new Latent(Collections.singletonList(Generic.OBJECT), Collections.emptyList(), annotationSource);
                }

                public static Generic boundedAbove(Generic generic, AnnotationSource annotationSource) {
                    return new Latent(Collections.singletonList(generic), Collections.emptyList(), annotationSource);
                }

                public static Generic boundedBelow(Generic generic, AnnotationSource annotationSource) {
                    return new Latent(Collections.singletonList(Generic.OBJECT), Collections.singletonList(generic), annotationSource);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic
                public TypeList.Generic getUpperBounds() {
                    return new TypeList.Generic.Explicit(this.upperBounds);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic
                public TypeList.Generic getLowerBounds() {
                    return new TypeList.Generic.Explicit(this.lowerBounds);
                }

                @Override // net.bytebuddy.description.annotation.AnnotationSource
                public AnnotationList getDeclaredAnnotations() {
                    return this.annotationSource.getDeclaredAnnotations();
                }
            }
        }

        public static abstract class OfParameterizedType extends AbstractBase {
            @Override // net.bytebuddy.description.type.TypeDefinition
            public boolean isArray() {
                return false;
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public boolean isPrimitive() {
                return false;
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public TypeDefinition.Sort getSort() {
                return TypeDefinition.Sort.PARAMETERIZED;
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public Generic getSuperClass() {
                Generic superClass = asErasure().getSuperClass();
                return superClass == null ? Generic.UNDEFINED : new LazyProjection.WithResolvedErasure(superClass, new Visitor.Substitutor.ForTypeVariableBinding(this));
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public TypeList.Generic getInterfaces() {
                return new TypeList.Generic.ForDetachedTypes.WithResolvedErasure(asErasure().getInterfaces(), new Visitor.Substitutor.ForTypeVariableBinding(this));
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic, net.bytebuddy.description.type.TypeDefinition
            public FieldList<FieldDescription.InGenericShape> getDeclaredFields() {
                return new FieldList.TypeSubstituting(this, asErasure().getDeclaredFields(), new Visitor.Substitutor.ForTypeVariableBinding(this));
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic, net.bytebuddy.description.type.TypeDefinition
            public MethodList<MethodDescription.InGenericShape> getDeclaredMethods() {
                return new MethodList.TypeSubstituting(this, asErasure().getDeclaredMethods(), new Visitor.Substitutor.ForTypeVariableBinding(this));
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public Generic findBindingOf(Generic generic) {
                Generic ownerType = this;
                do {
                    TypeList.Generic typeArguments = ownerType.getTypeArguments();
                    TypeList.Generic typeVariables = ownerType.asErasure().getTypeVariables();
                    for (int i = 0; i < Math.min(typeArguments.size(), typeVariables.size()); i++) {
                        if (generic.equals(typeVariables.get(i))) {
                            return (Generic) typeArguments.get(i);
                        }
                    }
                    ownerType = ownerType.getOwnerType();
                    if (ownerType == null) {
                        break;
                    }
                } while (ownerType.getSort().isParameterized());
                return Generic.UNDEFINED;
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public TypeList.Generic getUpperBounds() {
                throw new IllegalStateException("A parameterized type does not imply upper bounds: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public TypeList.Generic getLowerBounds() {
                throw new IllegalStateException("A parameterized type does not imply lower bounds: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public Generic getComponentType() {
                throw new IllegalStateException("A parameterized type does not imply a component type: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public TypeVariableSource getTypeVariableSource() {
                throw new IllegalStateException("A parameterized type does not imply a type variable source: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public String getSymbol() {
                throw new IllegalStateException("A parameterized type does not imply a symbol: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public String getTypeName() {
                return toString();
            }

            @Override // net.bytebuddy.description.NamedElement
            public String getActualName() {
                return toString();
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic.AbstractBase, net.bytebuddy.description.type.TypeDefinition
            public boolean represents(Type type) {
                return equals(TypeDefinition.Sort.describe(type));
            }

            @Override // java.lang.Iterable
            public Iterator<TypeDefinition> iterator() {
                return new TypeDefinition.SuperClassIterator(this);
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public <T> T accept(Visitor<T> visitor) {
                return visitor.onParameterizedType(this);
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public StackSize getStackSize() {
                return StackSize.SINGLE;
            }

            public int hashCode() {
                int iHashCode;
                Iterator it = getTypeArguments().iterator();
                int iHashCode2 = 1;
                while (it.hasNext()) {
                    iHashCode2 = (iHashCode2 * 31) + ((Generic) it.next()).hashCode();
                }
                Generic ownerType = getOwnerType();
                if (ownerType == null) {
                    iHashCode = asErasure().hashCode();
                } else {
                    iHashCode = ownerType.hashCode();
                }
                return iHashCode ^ iHashCode2;
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof Generic)) {
                    return false;
                }
                Generic generic = (Generic) obj;
                if (!generic.getSort().isParameterized()) {
                    return false;
                }
                Generic ownerType = getOwnerType();
                Generic ownerType2 = generic.getOwnerType();
                if (!asErasure().equals(generic.asErasure())) {
                    return false;
                }
                if (ownerType != null || ownerType2 == null) {
                    return (ownerType == null || ownerType.equals(ownerType2)) && getTypeArguments().equals(generic.getTypeArguments());
                }
                return false;
            }

            public String toString() {
                StringBuilder sb = new StringBuilder();
                Generic ownerType = getOwnerType();
                if (ownerType != null) {
                    RenderingDelegate.CURRENT.apply(sb.append(ownerType.getTypeName()), asErasure(), ownerType);
                } else {
                    sb.append(asErasure().getName());
                }
                TypeList.Generic<Generic> typeArguments = getTypeArguments();
                if (!typeArguments.isEmpty()) {
                    sb.append(Typography.less);
                    boolean z = false;
                    for (Generic generic : typeArguments) {
                        if (z) {
                            sb.append(", ");
                        }
                        sb.append(generic.getTypeName());
                        z = true;
                    }
                    sb.append(Typography.greater);
                }
                return sb.toString();
            }

            protected enum RenderingDelegate {
                LEGACY_VM { // from class: net.bytebuddy.description.type.TypeDescription.Generic.OfParameterizedType.RenderingDelegate.1
                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.OfParameterizedType.RenderingDelegate
                    protected void apply(StringBuilder sb, TypeDescription typeDescription, Generic generic) {
                        String name;
                        StringBuilder sbAppend = sb.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH);
                        if (generic.getSort().isParameterized()) {
                            name = typeDescription.getSimpleName();
                        } else {
                            name = typeDescription.getName();
                        }
                        sbAppend.append(name);
                    }
                },
                JAVA_9_CAPABLE_VM { // from class: net.bytebuddy.description.type.TypeDescription.Generic.OfParameterizedType.RenderingDelegate.2
                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.OfParameterizedType.RenderingDelegate
                    protected void apply(StringBuilder sb, TypeDescription typeDescription, Generic generic) {
                        sb.append(Typography.dollar).append(typeDescription.getSimpleName());
                    }
                };

                protected static final RenderingDelegate CURRENT;

                protected abstract void apply(StringBuilder sb, TypeDescription typeDescription, Generic generic);

                static {
                    RenderingDelegate renderingDelegate = LEGACY_VM;
                    CURRENT = ClassFileVersion.ofThisVm(ClassFileVersion.JAVA_V6).isAtLeast(ClassFileVersion.JAVA_V9) ? JAVA_9_CAPABLE_VM : renderingDelegate;
                }
            }

            public static class ForLoadedType extends OfParameterizedType {
                private final AnnotationReader annotationReader;
                private final ParameterizedType parameterizedType;

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.OfParameterizedType, net.bytebuddy.description.type.TypeDefinition
                public /* bridge */ /* synthetic */ TypeDefinition getComponentType() {
                    return super.getComponentType();
                }

                public ForLoadedType(ParameterizedType parameterizedType) {
                    this(parameterizedType, AnnotationReader.NoOp.INSTANCE);
                }

                protected ForLoadedType(ParameterizedType parameterizedType, AnnotationReader annotationReader) {
                    this.parameterizedType = parameterizedType;
                    this.annotationReader = annotationReader;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic
                public TypeList.Generic getTypeArguments() {
                    return new ParameterArgumentTypeList(this.parameterizedType.getActualTypeArguments(), this.annotationReader);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic
                public Generic getOwnerType() {
                    Type ownerType = this.parameterizedType.getOwnerType();
                    if (ownerType == null) {
                        return Generic.UNDEFINED;
                    }
                    return TypeDefinition.Sort.describe(ownerType, this.annotationReader.ofOwnerType());
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public TypeDescription asErasure() {
                    return new ForLoadedType((Class) this.parameterizedType.getRawType());
                }

                @Override // net.bytebuddy.description.annotation.AnnotationSource
                public AnnotationList getDeclaredAnnotations() {
                    return this.annotationReader.asList();
                }

                protected static class ParameterArgumentTypeList extends TypeList.Generic.AbstractBase {
                    private final AnnotationReader annotationReader;
                    private final Type[] argumentType;

                    protected ParameterArgumentTypeList(Type[] typeArr, AnnotationReader annotationReader) {
                        this.argumentType = typeArr;
                        this.annotationReader = annotationReader;
                    }

                    @Override // java.util.AbstractList, java.util.List
                    public Generic get(int i) {
                        return TypeDefinition.Sort.describe(this.argumentType[i], this.annotationReader.ofTypeArgument(i));
                    }

                    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                    public int size() {
                        return this.argumentType.length;
                    }
                }
            }

            public static class Latent extends OfParameterizedType {
                private final AnnotationSource annotationSource;
                private final Generic ownerType;
                private final List<? extends Generic> parameters;
                private final TypeDescription rawType;

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.OfParameterizedType, net.bytebuddy.description.type.TypeDefinition
                public /* bridge */ /* synthetic */ TypeDefinition getComponentType() {
                    return super.getComponentType();
                }

                public Latent(TypeDescription typeDescription, Generic generic, List<? extends Generic> list, AnnotationSource annotationSource) {
                    this.rawType = typeDescription;
                    this.ownerType = generic;
                    this.parameters = list;
                    this.annotationSource = annotationSource;
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public TypeDescription asErasure() {
                    return this.rawType;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic
                public Generic getOwnerType() {
                    return this.ownerType;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic
                public TypeList.Generic getTypeArguments() {
                    return new TypeList.Generic.Explicit(this.parameters);
                }

                @Override // net.bytebuddy.description.annotation.AnnotationSource
                public AnnotationList getDeclaredAnnotations() {
                    return this.annotationSource.getDeclaredAnnotations();
                }
            }

            public static class ForReifiedType extends OfParameterizedType {
                private final Generic parameterizedType;

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.OfParameterizedType, net.bytebuddy.description.type.TypeDefinition
                public /* bridge */ /* synthetic */ TypeDefinition getComponentType() {
                    return super.getComponentType();
                }

                protected ForReifiedType(Generic generic) {
                    this.parameterizedType = generic;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.OfParameterizedType, net.bytebuddy.description.type.TypeDefinition
                public Generic getSuperClass() {
                    Generic superClass = super.getSuperClass();
                    return superClass == null ? Generic.UNDEFINED : new LazyProjection.WithResolvedErasure(superClass, Visitor.Reifying.INHERITING);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.OfParameterizedType, net.bytebuddy.description.type.TypeDefinition
                public TypeList.Generic getInterfaces() {
                    return new TypeList.Generic.ForDetachedTypes.WithResolvedErasure(super.getInterfaces(), Visitor.Reifying.INHERITING);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.OfParameterizedType, net.bytebuddy.description.type.TypeDescription.Generic, net.bytebuddy.description.type.TypeDefinition
                public FieldList<FieldDescription.InGenericShape> getDeclaredFields() {
                    return new FieldList.TypeSubstituting(this, super.getDeclaredFields(), Visitor.TypeErasing.INSTANCE);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.OfParameterizedType, net.bytebuddy.description.type.TypeDescription.Generic, net.bytebuddy.description.type.TypeDefinition
                public MethodList<MethodDescription.InGenericShape> getDeclaredMethods() {
                    return new MethodList.TypeSubstituting(this, super.getDeclaredMethods(), Visitor.TypeErasing.INSTANCE);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic
                public TypeList.Generic getTypeArguments() {
                    return new TypeList.Generic.ForDetachedTypes(this.parameterizedType.getTypeArguments(), Visitor.TypeErasing.INSTANCE);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic
                public Generic getOwnerType() {
                    Generic ownerType = this.parameterizedType.getOwnerType();
                    if (ownerType == null) {
                        return Generic.UNDEFINED;
                    }
                    return (Generic) ownerType.accept(Visitor.Reifying.INHERITING);
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public TypeDescription asErasure() {
                    return this.parameterizedType.asErasure();
                }

                @Override // net.bytebuddy.description.annotation.AnnotationSource
                public AnnotationList getDeclaredAnnotations() {
                    return new AnnotationList.Empty();
                }
            }

            public static class ForGenerifiedErasure extends OfParameterizedType {
                private final TypeDescription typeDescription;

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.OfParameterizedType, net.bytebuddy.description.type.TypeDefinition
                public /* bridge */ /* synthetic */ TypeDefinition getComponentType() {
                    return super.getComponentType();
                }

                protected ForGenerifiedErasure(TypeDescription typeDescription) {
                    this.typeDescription = typeDescription;
                }

                public static Generic of(TypeDescription typeDescription) {
                    return typeDescription.isGenerified() ? new ForGenerifiedErasure(typeDescription) : new OfNonGenericType.ForErasure(typeDescription);
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public TypeDescription asErasure() {
                    return this.typeDescription;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic
                public TypeList.Generic getTypeArguments() {
                    return new TypeList.Generic.ForDetachedTypes(this.typeDescription.getTypeVariables(), Visitor.AnnotationStripper.INSTANCE);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic
                public Generic getOwnerType() {
                    TypeDescription declaringType = this.typeDescription.getDeclaringType();
                    if (declaringType == null) {
                        return Generic.UNDEFINED;
                    }
                    return of(declaringType);
                }

                @Override // net.bytebuddy.description.annotation.AnnotationSource
                public AnnotationList getDeclaredAnnotations() {
                    return new AnnotationList.Empty();
                }
            }
        }

        public static abstract class OfTypeVariable extends AbstractBase {
            @Override // net.bytebuddy.description.type.TypeDefinition
            public boolean isArray() {
                return false;
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public boolean isPrimitive() {
                return false;
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public TypeDefinition.Sort getSort() {
                return TypeDefinition.Sort.VARIABLE;
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public TypeDescription asErasure() {
                TypeList.Generic upperBounds = getUpperBounds();
                return upperBounds.isEmpty() ? TypeDescription.OBJECT : ((Generic) upperBounds.get(0)).asErasure();
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public Generic getSuperClass() {
                throw new IllegalStateException("A type variable does not imply a super type definition: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public TypeList.Generic getInterfaces() {
                throw new IllegalStateException("A type variable does not imply an interface type definition: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic, net.bytebuddy.description.type.TypeDefinition
            public FieldList<FieldDescription.InGenericShape> getDeclaredFields() {
                throw new IllegalStateException("A type variable does not imply field definitions: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic, net.bytebuddy.description.type.TypeDefinition
            public MethodList<MethodDescription.InGenericShape> getDeclaredMethods() {
                throw new IllegalStateException("A type variable does not imply method definitions: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public Generic getComponentType() {
                throw new IllegalStateException("A type variable does not imply a component type: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public TypeList.Generic getTypeArguments() {
                throw new IllegalStateException("A type variable does not imply type arguments: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public Generic findBindingOf(Generic generic) {
                throw new IllegalStateException("A type variable does not imply type arguments: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public TypeList.Generic getLowerBounds() {
                throw new IllegalStateException("A type variable does not imply lower bounds: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public Generic getOwnerType() {
                throw new IllegalStateException("A type variable does not imply an owner type: " + this);
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public String getTypeName() {
                return toString();
            }

            @Override // net.bytebuddy.description.NamedElement
            public String getActualName() {
                return getSymbol();
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public <T> T accept(Visitor<T> visitor) {
                return visitor.onTypeVariable(this);
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public StackSize getStackSize() {
                return StackSize.SINGLE;
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic.AbstractBase, net.bytebuddy.description.type.TypeDefinition
            public boolean represents(Type type) {
                return equals(TypeDefinition.Sort.describe(type));
            }

            @Override // java.lang.Iterable
            public Iterator<TypeDefinition> iterator() {
                throw new IllegalStateException("A type variable does not imply a super type definition: " + this);
            }

            public int hashCode() {
                return getTypeVariableSource().hashCode() ^ getSymbol().hashCode();
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof Generic)) {
                    return false;
                }
                Generic generic = (Generic) obj;
                return generic.getSort().isTypeVariable() && getSymbol().equals(generic.getSymbol()) && getTypeVariableSource().equals(generic.getTypeVariableSource());
            }

            public String toString() {
                return getSymbol();
            }

            public static class Symbolic extends AbstractBase {
                private final AnnotationSource annotationSource;
                private final String symbol;

                @Override // net.bytebuddy.description.type.TypeDefinition
                public boolean isArray() {
                    return false;
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public boolean isPrimitive() {
                    return false;
                }

                public Symbolic(String str, AnnotationSource annotationSource) {
                    this.symbol = str;
                    this.annotationSource = annotationSource;
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public TypeDefinition.Sort getSort() {
                    return TypeDefinition.Sort.VARIABLE_SYMBOLIC;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic
                public String getSymbol() {
                    return this.symbol;
                }

                @Override // net.bytebuddy.description.annotation.AnnotationSource
                public AnnotationList getDeclaredAnnotations() {
                    return this.annotationSource.getDeclaredAnnotations();
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public TypeDescription asErasure() {
                    throw new IllegalStateException("A symbolic type variable does not imply an erasure: " + this);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic
                public TypeList.Generic getUpperBounds() {
                    throw new IllegalStateException("A symbolic type variable does not imply an upper type bound: " + this);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic
                public TypeVariableSource getTypeVariableSource() {
                    throw new IllegalStateException("A symbolic type variable does not imply a variable source: " + this);
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public Generic getSuperClass() {
                    throw new IllegalStateException("A symbolic type variable does not imply a super type definition: " + this);
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public TypeList.Generic getInterfaces() {
                    throw new IllegalStateException("A symbolic type variable does not imply an interface type definition: " + this);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic, net.bytebuddy.description.type.TypeDefinition
                public FieldList<FieldDescription.InGenericShape> getDeclaredFields() {
                    throw new IllegalStateException("A symbolic type variable does not imply field definitions: " + this);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic, net.bytebuddy.description.type.TypeDefinition
                public MethodList<MethodDescription.InGenericShape> getDeclaredMethods() {
                    throw new IllegalStateException("A symbolic type variable does not imply method definitions: " + this);
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public Generic getComponentType() {
                    throw new IllegalStateException("A symbolic type variable does not imply a component type: " + this);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic
                public TypeList.Generic getTypeArguments() {
                    throw new IllegalStateException("A symbolic type variable does not imply type arguments: " + this);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic
                public Generic findBindingOf(Generic generic) {
                    throw new IllegalStateException("A symbolic type variable does not imply type arguments: " + this);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic
                public TypeList.Generic getLowerBounds() {
                    throw new IllegalStateException("A symbolic type variable does not imply lower bounds: " + this);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic
                public Generic getOwnerType() {
                    throw new IllegalStateException("A symbolic type variable does not imply an owner type: " + this);
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public String getTypeName() {
                    return toString();
                }

                @Override // net.bytebuddy.description.NamedElement
                public String getActualName() {
                    return getSymbol();
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic
                public <T> T accept(Visitor<T> visitor) {
                    return visitor.onTypeVariable(this);
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public StackSize getStackSize() {
                    return StackSize.SINGLE;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.AbstractBase, net.bytebuddy.description.type.TypeDefinition
                public boolean represents(Type type) {
                    type.getClass();
                    return false;
                }

                @Override // java.lang.Iterable
                public Iterator<TypeDefinition> iterator() {
                    throw new IllegalStateException("A symbolic type variable does not imply a super type definition: " + this);
                }

                public int hashCode() {
                    return this.symbol.hashCode();
                }

                public boolean equals(Object obj) {
                    if (!(obj instanceof Generic)) {
                        return false;
                    }
                    Generic generic = (Generic) obj;
                    return generic.getSort().isTypeVariable() && getSymbol().equals(generic.getSymbol());
                }

                public String toString() {
                    return getSymbol();
                }
            }

            public static class ForLoadedType extends OfTypeVariable {
                private final AnnotationReader annotationReader;
                private final TypeVariable<?> typeVariable;

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.OfTypeVariable, net.bytebuddy.description.type.TypeDefinition
                public /* bridge */ /* synthetic */ TypeDefinition getComponentType() {
                    return super.getComponentType();
                }

                public ForLoadedType(TypeVariable<?> typeVariable) {
                    this(typeVariable, AnnotationReader.NoOp.INSTANCE);
                }

                protected ForLoadedType(TypeVariable<?> typeVariable, AnnotationReader annotationReader) {
                    this.typeVariable = typeVariable;
                    this.annotationReader = annotationReader;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic
                public TypeVariableSource getTypeVariableSource() {
                    GenericDeclaration genericDeclaration = this.typeVariable.getGenericDeclaration();
                    if (genericDeclaration instanceof Class) {
                        return new ForLoadedType((Class) genericDeclaration);
                    }
                    if (genericDeclaration instanceof Method) {
                        return new MethodDescription.ForLoadedMethod((Method) genericDeclaration);
                    }
                    if (genericDeclaration instanceof Constructor) {
                        return new MethodDescription.ForLoadedConstructor((Constructor) genericDeclaration);
                    }
                    throw new IllegalStateException("Unknown declaration: " + genericDeclaration);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic
                public TypeList.Generic getUpperBounds() {
                    return new TypeVariableBoundList(this.typeVariable.getBounds(), this.annotationReader);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic
                public String getSymbol() {
                    return this.typeVariable.getName();
                }

                @Override // net.bytebuddy.description.annotation.AnnotationSource
                public AnnotationList getDeclaredAnnotations() {
                    return this.annotationReader.asList();
                }

                protected static class TypeVariableBoundList extends TypeList.Generic.AbstractBase {
                    private final AnnotationReader annotationReader;
                    private final Type[] bound;

                    protected TypeVariableBoundList(Type[] typeArr, AnnotationReader annotationReader) {
                        this.bound = typeArr;
                        this.annotationReader = annotationReader;
                    }

                    @Override // java.util.AbstractList, java.util.List
                    public Generic get(int i) {
                        return TypeDefinition.Sort.describe(this.bound[i], this.annotationReader.ofTypeVariableBoundType(i));
                    }

                    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                    public int size() {
                        return this.bound.length;
                    }
                }
            }

            public static class WithAnnotationOverlay extends OfTypeVariable {
                private final AnnotationSource annotationSource;
                private final Generic typeVariable;

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.OfTypeVariable, net.bytebuddy.description.type.TypeDefinition
                public /* bridge */ /* synthetic */ TypeDefinition getComponentType() {
                    return super.getComponentType();
                }

                public WithAnnotationOverlay(Generic generic, AnnotationSource annotationSource) {
                    this.typeVariable = generic;
                    this.annotationSource = annotationSource;
                }

                @Override // net.bytebuddy.description.annotation.AnnotationSource
                public AnnotationList getDeclaredAnnotations() {
                    return this.annotationSource.getDeclaredAnnotations();
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic
                public TypeList.Generic getUpperBounds() {
                    return this.typeVariable.getUpperBounds();
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic
                public TypeVariableSource getTypeVariableSource() {
                    return this.typeVariable.getTypeVariableSource();
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic
                public String getSymbol() {
                    return this.typeVariable.getSymbol();
                }
            }
        }

        public static abstract class LazyProjection extends AbstractBase {
            protected abstract Generic resolve();

            @Override // net.bytebuddy.description.type.TypeDefinition
            public TypeDefinition.Sort getSort() {
                return resolve().getSort();
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic, net.bytebuddy.description.type.TypeDefinition
            public FieldList<FieldDescription.InGenericShape> getDeclaredFields() {
                return resolve().getDeclaredFields();
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic, net.bytebuddy.description.type.TypeDefinition
            public MethodList<MethodDescription.InGenericShape> getDeclaredMethods() {
                return resolve().getDeclaredMethods();
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public TypeList.Generic getUpperBounds() {
                return resolve().getUpperBounds();
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public TypeList.Generic getLowerBounds() {
                return resolve().getLowerBounds();
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public Generic getComponentType() {
                return resolve().getComponentType();
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public TypeList.Generic getTypeArguments() {
                return resolve().getTypeArguments();
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public Generic findBindingOf(Generic generic) {
                return resolve().findBindingOf(generic);
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public TypeVariableSource getTypeVariableSource() {
                return resolve().getTypeVariableSource();
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public Generic getOwnerType() {
                return resolve().getOwnerType();
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public String getTypeName() {
                return resolve().getTypeName();
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public String getSymbol() {
                return resolve().getSymbol();
            }

            @Override // net.bytebuddy.description.NamedElement
            public String getActualName() {
                return resolve().getActualName();
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic
            public <T> T accept(Visitor<T> visitor) {
                return (T) resolve().accept(visitor);
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public StackSize getStackSize() {
                return asErasure().getStackSize();
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public boolean isArray() {
                return asErasure().isArray();
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public boolean isPrimitive() {
                return asErasure().isPrimitive();
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic.AbstractBase, net.bytebuddy.description.type.TypeDefinition
            public boolean represents(Type type) {
                return resolve().represents(type);
            }

            public int hashCode() {
                return resolve().hashCode();
            }

            public boolean equals(Object obj) {
                return (obj instanceof TypeDefinition) && resolve().equals(obj);
            }

            public String toString() {
                return resolve().toString();
            }

            public static abstract class WithLazyNavigation extends LazyProjection {
                @Override // net.bytebuddy.description.type.TypeDescription.Generic.LazyProjection, net.bytebuddy.description.type.TypeDefinition
                public /* bridge */ /* synthetic */ TypeDefinition getComponentType() {
                    return super.getComponentType();
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public Generic getSuperClass() {
                    return LazySuperClass.of(this);
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public TypeList.Generic getInterfaces() {
                    return LazyInterfaceList.of((LazyProjection) this);
                }

                @Override // java.lang.Iterable
                public Iterator<TypeDefinition> iterator() {
                    return new TypeDefinition.SuperClassIterator(this);
                }

                protected static class LazySuperClass extends WithLazyNavigation {
                    private final LazyProjection delegate;

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.LazyProjection.WithLazyNavigation, net.bytebuddy.description.type.TypeDescription.Generic.LazyProjection, net.bytebuddy.description.type.TypeDefinition
                    public /* bridge */ /* synthetic */ TypeDefinition getComponentType() {
                        return super.getComponentType();
                    }

                    protected LazySuperClass(LazyProjection lazyProjection) {
                        this.delegate = lazyProjection;
                    }

                    protected static Generic of(LazyProjection lazyProjection) {
                        return lazyProjection.asErasure().getSuperClass() == null ? Generic.UNDEFINED : new LazySuperClass(lazyProjection);
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationSource
                    public AnnotationList getDeclaredAnnotations() {
                        return resolve().getDeclaredAnnotations();
                    }

                    @Override // net.bytebuddy.description.type.TypeDefinition
                    public TypeDescription asErasure() {
                        return this.delegate.asErasure().getSuperClass().asErasure();
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.LazyProjection
                    protected Generic resolve() {
                        return this.delegate.resolve().getSuperClass();
                    }
                }

                protected static class LazyInterfaceType extends WithLazyNavigation {
                    private final LazyProjection delegate;
                    private final int index;
                    private final Generic rawInterface;

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.LazyProjection.WithLazyNavigation, net.bytebuddy.description.type.TypeDescription.Generic.LazyProjection, net.bytebuddy.description.type.TypeDefinition
                    public /* bridge */ /* synthetic */ TypeDefinition getComponentType() {
                        return super.getComponentType();
                    }

                    protected LazyInterfaceType(LazyProjection lazyProjection, int i, Generic generic) {
                        this.delegate = lazyProjection;
                        this.index = i;
                        this.rawInterface = generic;
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationSource
                    public AnnotationList getDeclaredAnnotations() {
                        return resolve().getDeclaredAnnotations();
                    }

                    @Override // net.bytebuddy.description.type.TypeDefinition
                    public TypeDescription asErasure() {
                        return this.rawInterface.asErasure();
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.LazyProjection
                    protected Generic resolve() {
                        return (Generic) this.delegate.resolve().getInterfaces().get(this.index);
                    }
                }

                protected static class LazyInterfaceList extends TypeList.Generic.AbstractBase {
                    private final LazyProjection delegate;
                    private final TypeList.Generic rawInterfaces;

                    protected LazyInterfaceList(LazyProjection lazyProjection, TypeList.Generic generic) {
                        this.delegate = lazyProjection;
                        this.rawInterfaces = generic;
                    }

                    protected static TypeList.Generic of(LazyProjection lazyProjection) {
                        return new LazyInterfaceList(lazyProjection, lazyProjection.asErasure().getInterfaces());
                    }

                    @Override // java.util.AbstractList, java.util.List
                    public Generic get(int i) {
                        return new LazyInterfaceType(this.delegate, i, (Generic) this.rawInterfaces.get(i));
                    }

                    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                    public int size() {
                        return this.rawInterfaces.size();
                    }
                }

                protected static abstract class OfAnnotatedElement extends WithLazyNavigation {
                    protected abstract AnnotationReader getAnnotationReader();

                    protected OfAnnotatedElement() {
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.LazyProjection.WithLazyNavigation, net.bytebuddy.description.type.TypeDescription.Generic.LazyProjection, net.bytebuddy.description.type.TypeDefinition
                    public /* bridge */ /* synthetic */ TypeDefinition getComponentType() {
                        return super.getComponentType();
                    }

                    public AnnotationList getDeclaredAnnotations() {
                        return getAnnotationReader().asList();
                    }
                }
            }

            public static abstract class WithEagerNavigation extends LazyProjection {
                @Override // net.bytebuddy.description.type.TypeDescription.Generic.LazyProjection, net.bytebuddy.description.type.TypeDefinition
                public /* bridge */ /* synthetic */ TypeDefinition getComponentType() {
                    return super.getComponentType();
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public Generic getSuperClass() {
                    return resolve().getSuperClass();
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public TypeList.Generic getInterfaces() {
                    return resolve().getInterfaces();
                }

                @Override // java.lang.Iterable
                public Iterator<TypeDefinition> iterator() {
                    return resolve().iterator();
                }

                protected static abstract class OfAnnotatedElement extends WithEagerNavigation {
                    protected abstract AnnotationReader getAnnotationReader();

                    protected OfAnnotatedElement() {
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.Generic.LazyProjection.WithEagerNavigation, net.bytebuddy.description.type.TypeDescription.Generic.LazyProjection, net.bytebuddy.description.type.TypeDefinition
                    public /* bridge */ /* synthetic */ TypeDefinition getComponentType() {
                        return super.getComponentType();
                    }

                    public AnnotationList getDeclaredAnnotations() {
                        return getAnnotationReader().asList();
                    }
                }
            }

            public static class ForLoadedSuperClass extends WithLazyNavigation.OfAnnotatedElement {
                private final Class<?> type;

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.LazyProjection.WithLazyNavigation.OfAnnotatedElement, net.bytebuddy.description.annotation.AnnotationSource
                public /* bridge */ /* synthetic */ AnnotationList getDeclaredAnnotations() {
                    return super.getDeclaredAnnotations();
                }

                public ForLoadedSuperClass(Class<?> cls) {
                    this.type = cls;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.LazyProjection
                protected Generic resolve() {
                    Type genericSuperclass = this.type.getGenericSuperclass();
                    if (genericSuperclass == null) {
                        return Generic.UNDEFINED;
                    }
                    return TypeDefinition.Sort.describe(genericSuperclass, getAnnotationReader());
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public TypeDescription asErasure() {
                    Class<? super Object> superclass = this.type.getSuperclass();
                    return superclass == null ? TypeDescription.UNDEFINED : new ForLoadedType(superclass);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.LazyProjection.WithLazyNavigation.OfAnnotatedElement
                protected AnnotationReader getAnnotationReader() {
                    return AnnotationReader.DISPATCHER.resolveSuperClassType(this.type);
                }
            }

            public static class ForLoadedFieldType extends WithEagerNavigation.OfAnnotatedElement {
                private final Field field;

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.LazyProjection.WithEagerNavigation.OfAnnotatedElement, net.bytebuddy.description.annotation.AnnotationSource
                public /* bridge */ /* synthetic */ AnnotationList getDeclaredAnnotations() {
                    return super.getDeclaredAnnotations();
                }

                public ForLoadedFieldType(Field field) {
                    this.field = field;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.LazyProjection
                protected Generic resolve() {
                    return TypeDefinition.Sort.describe(this.field.getGenericType(), getAnnotationReader());
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public TypeDescription asErasure() {
                    return new ForLoadedType(this.field.getType());
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.LazyProjection.WithEagerNavigation.OfAnnotatedElement
                protected AnnotationReader getAnnotationReader() {
                    return AnnotationReader.DISPATCHER.resolveFieldType(this.field);
                }
            }

            public static class ForLoadedReturnType extends WithEagerNavigation.OfAnnotatedElement {
                private final Method method;

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.LazyProjection.WithEagerNavigation.OfAnnotatedElement, net.bytebuddy.description.annotation.AnnotationSource
                public /* bridge */ /* synthetic */ AnnotationList getDeclaredAnnotations() {
                    return super.getDeclaredAnnotations();
                }

                public ForLoadedReturnType(Method method) {
                    this.method = method;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.LazyProjection
                protected Generic resolve() {
                    return TypeDefinition.Sort.describe(this.method.getGenericReturnType(), getAnnotationReader());
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public TypeDescription asErasure() {
                    return new ForLoadedType(this.method.getReturnType());
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.LazyProjection.WithEagerNavigation.OfAnnotatedElement
                protected AnnotationReader getAnnotationReader() {
                    return AnnotationReader.DISPATCHER.resolveReturnType(this.method);
                }
            }

            public static class OfConstructorParameter extends WithEagerNavigation.OfAnnotatedElement {
                private final Constructor<?> constructor;
                private final Class<?>[] erasure;
                private final int index;

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.LazyProjection.WithEagerNavigation.OfAnnotatedElement, net.bytebuddy.description.annotation.AnnotationSource
                public /* bridge */ /* synthetic */ AnnotationList getDeclaredAnnotations() {
                    return super.getDeclaredAnnotations();
                }

                public OfConstructorParameter(Constructor<?> constructor, int i, Class<?>[] clsArr) {
                    this.constructor = constructor;
                    this.index = i;
                    this.erasure = clsArr;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.LazyProjection
                protected Generic resolve() {
                    Type[] genericParameterTypes = this.constructor.getGenericParameterTypes();
                    return this.erasure.length == genericParameterTypes.length ? TypeDefinition.Sort.describe(genericParameterTypes[this.index], getAnnotationReader()) : new OfNonGenericType.ForLoadedType(this.erasure[this.index]);
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public TypeDescription asErasure() {
                    return new ForLoadedType(this.erasure[this.index]);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.LazyProjection.WithEagerNavigation.OfAnnotatedElement
                protected AnnotationReader getAnnotationReader() {
                    return AnnotationReader.DISPATCHER.resolveParameterType(this.constructor, this.index);
                }
            }

            public static class OfMethodParameter extends WithEagerNavigation.OfAnnotatedElement {
                private final Class<?>[] erasure;
                private final int index;
                private final Method method;

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.LazyProjection.WithEagerNavigation.OfAnnotatedElement, net.bytebuddy.description.annotation.AnnotationSource
                public /* bridge */ /* synthetic */ AnnotationList getDeclaredAnnotations() {
                    return super.getDeclaredAnnotations();
                }

                public OfMethodParameter(Method method, int i, Class<?>[] clsArr) {
                    this.method = method;
                    this.index = i;
                    this.erasure = clsArr;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.LazyProjection
                protected Generic resolve() {
                    Type[] genericParameterTypes = this.method.getGenericParameterTypes();
                    return this.erasure.length == genericParameterTypes.length ? TypeDefinition.Sort.describe(genericParameterTypes[this.index], getAnnotationReader()) : new OfNonGenericType.ForLoadedType(this.erasure[this.index]);
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public TypeDescription asErasure() {
                    return new ForLoadedType(this.erasure[this.index]);
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.LazyProjection.WithEagerNavigation.OfAnnotatedElement
                protected AnnotationReader getAnnotationReader() {
                    return AnnotationReader.DISPATCHER.resolveParameterType(this.method, this.index);
                }
            }

            public static class WithResolvedErasure extends WithEagerNavigation {
                private final AnnotationSource annotationSource;
                private final Generic delegate;
                private final Visitor<? extends Generic> visitor;

                public WithResolvedErasure(Generic generic, Visitor<? extends Generic> visitor) {
                    this(generic, visitor, generic);
                }

                public WithResolvedErasure(Generic generic, Visitor<? extends Generic> visitor, AnnotationSource annotationSource) {
                    this.delegate = generic;
                    this.visitor = visitor;
                    this.annotationSource = annotationSource;
                }

                @Override // net.bytebuddy.description.annotation.AnnotationSource
                public AnnotationList getDeclaredAnnotations() {
                    return this.annotationSource.getDeclaredAnnotations();
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public TypeDescription asErasure() {
                    return this.delegate.asErasure();
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.LazyProjection
                protected Generic resolve() {
                    return (Generic) this.delegate.accept(this.visitor);
                }
            }
        }

        public static abstract class Builder {
            private static final Type UNDEFINED = null;
            protected final List<? extends AnnotationDescription> annotations;

            protected abstract Builder doAnnotate(List<? extends AnnotationDescription> list);

            protected abstract Generic doBuild();

            protected boolean canEqual(Object obj) {
                return obj instanceof Builder;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Builder)) {
                    return false;
                }
                Builder builder = (Builder) obj;
                if (!builder.canEqual(this)) {
                    return false;
                }
                List<? extends AnnotationDescription> list = this.annotations;
                List<? extends AnnotationDescription> list2 = builder.annotations;
                return list != null ? list.equals(list2) : list2 == null;
            }

            public int hashCode() {
                List<? extends AnnotationDescription> list = this.annotations;
                return 59 + (list == null ? 43 : list.hashCode());
            }

            protected Builder(List<? extends AnnotationDescription> list) {
                this.annotations = list;
            }

            public static Builder rawType(Class<?> cls) {
                return rawType(new ForLoadedType(cls));
            }

            public static Builder rawType(TypeDescription typeDescription) {
                return new OfNonGenericType(typeDescription);
            }

            public static Builder rawType(Class<?> cls, Generic generic) {
                return rawType(new ForLoadedType(cls), generic);
            }

            public static Builder rawType(TypeDescription typeDescription, Generic generic) {
                TypeDescription declaringType = typeDescription.getDeclaringType();
                if (declaringType == null && generic != null) {
                    throw new IllegalArgumentException(typeDescription + " does not have a declaring type: " + generic);
                }
                if (declaringType != null && (generic == null || !declaringType.equals(generic.asErasure()))) {
                    throw new IllegalArgumentException(generic + " is not the declaring type of " + typeDescription);
                }
                return new OfNonGenericType(typeDescription, generic);
            }

            public static Generic unboundWildcard() {
                return unboundWildcard(Collections.emptySet());
            }

            public static Generic unboundWildcard(Annotation... annotationArr) {
                return unboundWildcard((List<? extends Annotation>) Arrays.asList(annotationArr));
            }

            public static Generic unboundWildcard(List<? extends Annotation> list) {
                return unboundWildcard((Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
            }

            public static Generic unboundWildcard(AnnotationDescription... annotationDescriptionArr) {
                return unboundWildcard((Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
            }

            public static Generic unboundWildcard(Collection<? extends AnnotationDescription> collection) {
                return OfWildcardType.Latent.unbounded(new AnnotationSource.Explicit(new ArrayList(collection)));
            }

            public static Builder typeVariable(String str) {
                return new OfTypeVariable(str);
            }

            public static Builder parameterizedType(Class<?> cls, Type... typeArr) {
                return parameterizedType(cls, (List<? extends Type>) Arrays.asList(typeArr));
            }

            public static Builder parameterizedType(Class<?> cls, List<? extends Type> list) {
                return parameterizedType(cls, UNDEFINED, list);
            }

            public static Builder parameterizedType(Class<?> cls, Type type, List<? extends Type> list) {
                return parameterizedType(new ForLoadedType(cls), type == null ? null : TypeDefinition.Sort.describe(type), new TypeList.Generic.ForLoadedTypes(list));
            }

            public static Builder parameterizedType(TypeDescription typeDescription, TypeDefinition... typeDefinitionArr) {
                return parameterizedType(typeDescription, Arrays.asList(typeDefinitionArr));
            }

            public static Builder parameterizedType(TypeDescription typeDescription, Collection<? extends TypeDefinition> collection) {
                return parameterizedType(typeDescription, Generic.UNDEFINED, collection);
            }

            public static Builder parameterizedType(TypeDescription typeDescription, Generic generic, Collection<? extends TypeDefinition> collection) {
                TypeDescription declaringType = typeDescription.getDeclaringType();
                if (generic == null && declaringType != null && typeDescription.isStatic()) {
                    generic = declaringType.asGenericType();
                }
                if (!typeDescription.isGenerified()) {
                    throw new IllegalArgumentException(typeDescription + " is not a parameterized type");
                }
                if (generic == null && declaringType != null && !typeDescription.isStatic()) {
                    throw new IllegalArgumentException(typeDescription + " requires an owner type");
                }
                if (generic != null && !generic.asErasure().equals(declaringType)) {
                    throw new IllegalArgumentException(generic + " does not represent required owner for " + typeDescription);
                }
                if (generic != null && (typeDescription.isStatic() ^ generic.getSort().isNonGeneric())) {
                    throw new IllegalArgumentException(generic + " does not define the correct parameters for owning " + typeDescription);
                }
                if (typeDescription.getTypeVariables().size() != collection.size()) {
                    throw new IllegalArgumentException(collection + " does not contain number of required parameters for " + typeDescription);
                }
                return new OfParameterizedType(typeDescription, generic, new TypeList.Generic.Explicit(new ArrayList(collection)));
            }

            public Generic asWildcardUpperBound() {
                return asWildcardUpperBound(Collections.emptySet());
            }

            public Generic asWildcardUpperBound(Annotation... annotationArr) {
                return asWildcardUpperBound(Arrays.asList(annotationArr));
            }

            public Generic asWildcardUpperBound(List<? extends Annotation> list) {
                return asWildcardUpperBound((Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
            }

            public Generic asWildcardUpperBound(AnnotationDescription... annotationDescriptionArr) {
                return asWildcardUpperBound((Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
            }

            public Generic asWildcardUpperBound(Collection<? extends AnnotationDescription> collection) {
                return OfWildcardType.Latent.boundedAbove(build(), new AnnotationSource.Explicit(new ArrayList(collection)));
            }

            public Generic asWildcardLowerBound() {
                return asWildcardLowerBound(Collections.emptySet());
            }

            public Generic asWildcardLowerBound(Annotation... annotationArr) {
                return asWildcardLowerBound(Arrays.asList(annotationArr));
            }

            public Generic asWildcardLowerBound(List<? extends Annotation> list) {
                return asWildcardLowerBound((Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
            }

            public Generic asWildcardLowerBound(AnnotationDescription... annotationDescriptionArr) {
                return asWildcardLowerBound((Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
            }

            public Generic asWildcardLowerBound(Collection<? extends AnnotationDescription> collection) {
                return OfWildcardType.Latent.boundedBelow(build(), new AnnotationSource.Explicit(new ArrayList(collection)));
            }

            public Builder asArray() {
                return asArray(1);
            }

            public Builder asArray(int i) {
                if (i < 1) {
                    throw new IllegalArgumentException("Cannot define an array of a non-positive arity: " + i);
                }
                Generic genericBuild = build();
                while (true) {
                    i--;
                    if (i > 0) {
                        genericBuild = new OfGenericArray.Latent(genericBuild, AnnotationSource.Empty.INSTANCE);
                    } else {
                        return new OfGenericArrayType(genericBuild);
                    }
                }
            }

            public Builder annotate(Annotation... annotationArr) {
                return annotate(Arrays.asList(annotationArr));
            }

            public Builder annotate(List<? extends Annotation> list) {
                return annotate((Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
            }

            public Builder annotate(AnnotationDescription... annotationDescriptionArr) {
                return annotate((Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
            }

            public Builder annotate(Collection<? extends AnnotationDescription> collection) {
                return doAnnotate(new ArrayList(collection));
            }

            public Generic build() {
                return doBuild();
            }

            public Generic build(Annotation... annotationArr) {
                return build(Arrays.asList(annotationArr));
            }

            public Generic build(List<? extends Annotation> list) {
                return build((Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
            }

            public Generic build(AnnotationDescription... annotationDescriptionArr) {
                return build((Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
            }

            public Generic build(Collection<? extends AnnotationDescription> collection) {
                return doAnnotate(new ArrayList(collection)).doBuild();
            }

            protected static class OfNonGenericType extends Builder {
                private final Generic ownerType;
                private final TypeDescription typeDescription;

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Builder
                protected boolean canEqual(Object obj) {
                    return obj instanceof OfNonGenericType;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Builder
                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof OfNonGenericType)) {
                        return false;
                    }
                    OfNonGenericType ofNonGenericType = (OfNonGenericType) obj;
                    if (!ofNonGenericType.canEqual(this) || !super.equals(obj)) {
                        return false;
                    }
                    TypeDescription typeDescription = this.typeDescription;
                    TypeDescription typeDescription2 = ofNonGenericType.typeDescription;
                    if (typeDescription != null ? !typeDescription.equals(typeDescription2) : typeDescription2 != null) {
                        return false;
                    }
                    Generic generic = this.ownerType;
                    Generic generic2 = ofNonGenericType.ownerType;
                    return generic != null ? generic.equals(generic2) : generic2 == null;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Builder
                public int hashCode() {
                    int iHashCode = super.hashCode() + 59;
                    TypeDescription typeDescription = this.typeDescription;
                    int iHashCode2 = (iHashCode * 59) + (typeDescription == null ? 43 : typeDescription.hashCode());
                    Generic generic = this.ownerType;
                    return (iHashCode2 * 59) + (generic != null ? generic.hashCode() : 43);
                }

                protected OfNonGenericType(TypeDescription typeDescription) {
                    this(typeDescription, typeDescription.getDeclaringType());
                }

                /* JADX WARN: Illegal instructions before constructor call */
                private OfNonGenericType(TypeDescription typeDescription, TypeDescription typeDescription2) {
                    Generic genericAsGenericType;
                    if (typeDescription2 == null) {
                        genericAsGenericType = Generic.UNDEFINED;
                    } else {
                        genericAsGenericType = typeDescription2.asGenericType();
                    }
                    this(typeDescription, genericAsGenericType);
                }

                protected OfNonGenericType(TypeDescription typeDescription, Generic generic) {
                    this(typeDescription, generic, Collections.emptyList());
                }

                protected OfNonGenericType(TypeDescription typeDescription, Generic generic, List<? extends AnnotationDescription> list) {
                    super(list);
                    this.ownerType = generic;
                    this.typeDescription = typeDescription;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Builder
                protected Builder doAnnotate(List<? extends AnnotationDescription> list) {
                    return new OfNonGenericType(this.typeDescription, this.ownerType, CompoundList.of((List) this.annotations, (List) list));
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Builder
                protected Generic doBuild() {
                    if (this.typeDescription.represents(Void.TYPE) && !this.annotations.isEmpty()) {
                        throw new IllegalArgumentException("The void non-type cannot be annotated");
                    }
                    return new OfNonGenericType.Latent(this.typeDescription, this.ownerType, new AnnotationSource.Explicit(this.annotations));
                }
            }

            protected static class OfParameterizedType extends Builder {
                private final Generic ownerType;
                private final List<? extends Generic> parameterTypes;
                private final TypeDescription rawType;

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Builder
                protected boolean canEqual(Object obj) {
                    return obj instanceof OfParameterizedType;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Builder
                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof OfParameterizedType)) {
                        return false;
                    }
                    OfParameterizedType ofParameterizedType = (OfParameterizedType) obj;
                    if (!ofParameterizedType.canEqual(this) || !super.equals(obj)) {
                        return false;
                    }
                    TypeDescription typeDescription = this.rawType;
                    TypeDescription typeDescription2 = ofParameterizedType.rawType;
                    if (typeDescription != null ? !typeDescription.equals(typeDescription2) : typeDescription2 != null) {
                        return false;
                    }
                    Generic generic = this.ownerType;
                    Generic generic2 = ofParameterizedType.ownerType;
                    if (generic != null ? !generic.equals(generic2) : generic2 != null) {
                        return false;
                    }
                    List<? extends Generic> list = this.parameterTypes;
                    List<? extends Generic> list2 = ofParameterizedType.parameterTypes;
                    return list != null ? list.equals(list2) : list2 == null;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Builder
                public int hashCode() {
                    int iHashCode = super.hashCode() + 59;
                    TypeDescription typeDescription = this.rawType;
                    int iHashCode2 = (iHashCode * 59) + (typeDescription == null ? 43 : typeDescription.hashCode());
                    Generic generic = this.ownerType;
                    int iHashCode3 = (iHashCode2 * 59) + (generic == null ? 43 : generic.hashCode());
                    List<? extends Generic> list = this.parameterTypes;
                    return (iHashCode3 * 59) + (list != null ? list.hashCode() : 43);
                }

                protected OfParameterizedType(TypeDescription typeDescription, Generic generic, List<? extends Generic> list) {
                    this(typeDescription, generic, list, Collections.emptyList());
                }

                protected OfParameterizedType(TypeDescription typeDescription, Generic generic, List<? extends Generic> list, List<? extends AnnotationDescription> list2) {
                    super(list2);
                    this.rawType = typeDescription;
                    this.ownerType = generic;
                    this.parameterTypes = list;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Builder
                protected Builder doAnnotate(List<? extends AnnotationDescription> list) {
                    return new OfParameterizedType(this.rawType, this.ownerType, this.parameterTypes, CompoundList.of((List) this.annotations, (List) list));
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Builder
                protected Generic doBuild() {
                    return new OfParameterizedType.Latent(this.rawType, this.ownerType, this.parameterTypes, new AnnotationSource.Explicit(this.annotations));
                }
            }

            protected static class OfGenericArrayType extends Builder {
                private final Generic componentType;

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Builder
                protected boolean canEqual(Object obj) {
                    return obj instanceof OfGenericArrayType;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Builder
                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof OfGenericArrayType)) {
                        return false;
                    }
                    OfGenericArrayType ofGenericArrayType = (OfGenericArrayType) obj;
                    if (!ofGenericArrayType.canEqual(this) || !super.equals(obj)) {
                        return false;
                    }
                    Generic generic = this.componentType;
                    Generic generic2 = ofGenericArrayType.componentType;
                    return generic != null ? generic.equals(generic2) : generic2 == null;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Builder
                public int hashCode() {
                    int iHashCode = super.hashCode() + 59;
                    Generic generic = this.componentType;
                    return (iHashCode * 59) + (generic == null ? 43 : generic.hashCode());
                }

                protected OfGenericArrayType(Generic generic) {
                    this(generic, Collections.emptyList());
                }

                protected OfGenericArrayType(Generic generic, List<? extends AnnotationDescription> list) {
                    super(list);
                    this.componentType = generic;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Builder
                protected Builder doAnnotate(List<? extends AnnotationDescription> list) {
                    return new OfGenericArrayType(this.componentType, CompoundList.of((List) this.annotations, (List) list));
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Builder
                protected Generic doBuild() {
                    return new OfGenericArray.Latent(this.componentType, new AnnotationSource.Explicit(this.annotations));
                }
            }

            protected static class OfTypeVariable extends Builder {
                private final String symbol;

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Builder
                protected boolean canEqual(Object obj) {
                    return obj instanceof OfTypeVariable;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Builder
                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof OfTypeVariable)) {
                        return false;
                    }
                    OfTypeVariable ofTypeVariable = (OfTypeVariable) obj;
                    if (!ofTypeVariable.canEqual(this) || !super.equals(obj)) {
                        return false;
                    }
                    String str = this.symbol;
                    String str2 = ofTypeVariable.symbol;
                    return str != null ? str.equals(str2) : str2 == null;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Builder
                public int hashCode() {
                    int iHashCode = super.hashCode() + 59;
                    String str = this.symbol;
                    return (iHashCode * 59) + (str == null ? 43 : str.hashCode());
                }

                protected OfTypeVariable(String str) {
                    this(str, Collections.emptyList());
                }

                protected OfTypeVariable(String str, List<? extends AnnotationDescription> list) {
                    super(list);
                    this.symbol = str;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Builder
                protected Builder doAnnotate(List<? extends AnnotationDescription> list) {
                    return new OfTypeVariable(this.symbol, CompoundList.of((List) this.annotations, (List) list));
                }

                @Override // net.bytebuddy.description.type.TypeDescription.Generic.Builder
                protected Generic doBuild() {
                    return new OfTypeVariable.Symbolic(this.symbol, new AnnotationSource.Explicit(this.annotations));
                }
            }
        }
    }

    public static abstract class AbstractBase extends TypeVariableSource.AbstractBase implements TypeDescription {
        public static final boolean RAW_TYPES;

        @Override // net.bytebuddy.description.type.TypeDefinition
        public TypeDescription asErasure() {
            return this;
        }

        static {
            boolean z;
            try {
                z = Boolean.parseBoolean((String) AccessController.doPrivileged(new GetSystemPropertyAction(TypeDefinition.RAW_TYPES_PROPERTY)));
            } catch (Exception unused) {
                z = false;
            }
            RAW_TYPES = z;
        }

        private static boolean isAssignable(TypeDescription typeDescription, TypeDescription typeDescription2) {
            if (typeDescription.equals(typeDescription2)) {
                return true;
            }
            if (typeDescription2.isArray()) {
                if (typeDescription.isArray()) {
                    return isAssignable(typeDescription.getComponentType(), typeDescription2.getComponentType());
                }
                return typeDescription.represents(Object.class) || ARRAY_INTERFACES.contains(typeDescription.asGenericType());
            }
            if (typeDescription.represents(Object.class)) {
                return !typeDescription2.isPrimitive();
            }
            Generic superClass = typeDescription2.getSuperClass();
            if (superClass != null && typeDescription.isAssignableFrom(superClass.asErasure())) {
                return true;
            }
            if (typeDescription.isInterface()) {
                Iterator it = typeDescription2.getInterfaces().asErasures().iterator();
                while (it.hasNext()) {
                    if (typeDescription.isAssignableFrom((TypeDescription) it.next())) {
                        return true;
                    }
                }
            }
            return false;
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public boolean isAssignableFrom(Class<?> cls) {
            return isAssignableFrom(new ForLoadedType(cls));
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public boolean isAssignableFrom(TypeDescription typeDescription) {
            return isAssignable(this, typeDescription);
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public boolean isAssignableTo(Class<?> cls) {
            return isAssignableTo(new ForLoadedType(cls));
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public boolean isAssignableTo(TypeDescription typeDescription) {
            return isAssignable(typeDescription, this);
        }

        @Override // net.bytebuddy.description.type.TypeDefinition
        public Generic asGenericType() {
            return new Generic.OfNonGenericType.ForErasure(this);
        }

        @Override // net.bytebuddy.description.type.TypeDefinition
        public TypeDefinition.Sort getSort() {
            return TypeDefinition.Sort.NON_GENERIC;
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public boolean isInstance(Object obj) {
            return isAssignableFrom(obj.getClass());
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public boolean isAnnotationValue(Object obj) {
            if ((represents(Class.class) && (obj instanceof TypeDescription)) || (((obj instanceof AnnotationDescription) && ((AnnotationDescription) obj).getAnnotationType().equals(this)) || (((obj instanceof EnumerationDescription) && ((EnumerationDescription) obj).getEnumerationType().equals(this)) || ((represents(String.class) && (obj instanceof String)) || ((represents(Boolean.TYPE) && (obj instanceof Boolean)) || ((represents(Byte.TYPE) && (obj instanceof Byte)) || ((represents(Short.TYPE) && (obj instanceof Short)) || ((represents(Character.TYPE) && (obj instanceof Character)) || ((represents(Integer.TYPE) && (obj instanceof Integer)) || ((represents(Long.TYPE) && (obj instanceof Long)) || ((represents(Float.TYPE) && (obj instanceof Float)) || ((represents(Double.TYPE) && (obj instanceof Double)) || ((represents(String[].class) && (obj instanceof String[])) || ((represents(boolean[].class) && (obj instanceof boolean[])) || ((represents(byte[].class) && (obj instanceof byte[])) || ((represents(short[].class) && (obj instanceof short[])) || ((represents(char[].class) && (obj instanceof char[])) || ((represents(int[].class) && (obj instanceof int[])) || ((represents(long[].class) && (obj instanceof long[])) || ((represents(float[].class) && (obj instanceof float[])) || ((represents(double[].class) && (obj instanceof double[])) || (represents(Class[].class) && (obj instanceof TypeDescription[]))))))))))))))))))))))) {
                return true;
            }
            if (isAssignableTo(Annotation[].class) && (obj instanceof AnnotationDescription[])) {
                for (AnnotationDescription annotationDescription : (AnnotationDescription[]) obj) {
                    if (!annotationDescription.getAnnotationType().equals(getComponentType())) {
                        return false;
                    }
                }
                return true;
            }
            if (!isAssignableTo(Enum[].class) || !(obj instanceof EnumerationDescription[])) {
                return false;
            }
            for (EnumerationDescription enumerationDescription : (EnumerationDescription[]) obj) {
                if (!enumerationDescription.getEnumerationType().equals(getComponentType())) {
                    return false;
                }
            }
            return true;
        }

        @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
        public String getInternalName() {
            return getName().replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, ClassPathElement.SEPARATOR_CHAR);
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public int getActualModifiers(boolean z) {
            int i;
            int modifiers = getModifiers() | (getDeclaredAnnotations().isAnnotationPresent(Deprecated.class) ? 131072 : 0);
            if (isPrivate()) {
                i = modifiers & (-11);
            } else {
                i = isProtected() ? (modifiers & (-13)) | 1 : modifiers & (-9);
            }
            return z ? i | 32 : i;
        }

        @Override // net.bytebuddy.description.ByteCodeElement
        public String getGenericSignature() {
            SignatureVisitor signatureVisitorVisitClassBound;
            try {
                SignatureWriter signatureWriter = new SignatureWriter();
                boolean z = false;
                for (Generic generic : getTypeVariables()) {
                    signatureWriter.visitFormalTypeParameter(generic.getSymbol());
                    for (Generic generic2 : generic.getUpperBounds()) {
                        if (generic2.asErasure().isInterface()) {
                            signatureVisitorVisitClassBound = signatureWriter.visitInterfaceBound();
                        } else {
                            signatureVisitorVisitClassBound = signatureWriter.visitClassBound();
                        }
                        generic2.accept(new Generic.Visitor.ForSignatureVisitor(signatureVisitorVisitClassBound));
                    }
                    z = true;
                }
                Generic superClass = getSuperClass();
                if (superClass == null) {
                    superClass = Generic.OBJECT;
                }
                superClass.accept(new Generic.Visitor.ForSignatureVisitor(signatureWriter.visitSuperclass()));
                boolean z2 = z || !superClass.getSort().isNonGeneric();
                for (Generic generic3 : getInterfaces()) {
                    generic3.accept(new Generic.Visitor.ForSignatureVisitor(signatureWriter.visitInterface()));
                    z2 = z2 || !generic3.getSort().isNonGeneric();
                }
                return z2 ? signatureWriter.toString() : NON_GENERIC_SIGNATURE;
            } catch (GenericSignatureFormatError unused) {
                return NON_GENERIC_SIGNATURE;
            }
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public boolean isSamePackage(TypeDescription typeDescription) {
            PackageDescription packageDescription = getPackage();
            PackageDescription packageDescription2 = typeDescription.getPackage();
            if (packageDescription == null || packageDescription2 == null) {
                return packageDescription == packageDescription2;
            }
            return packageDescription.equals(packageDescription2);
        }

        @Override // net.bytebuddy.description.ByteCodeElement
        public boolean isVisibleTo(TypeDescription typeDescription) {
            return isPrimitive() || (!isArray() ? !(isPublic() || isProtected() || isSamePackage(typeDescription)) : !getComponentType().isVisibleTo(typeDescription));
        }

        @Override // net.bytebuddy.description.ByteCodeElement
        public boolean isAccessibleTo(TypeDescription typeDescription) {
            return isPrimitive() || (!isArray() ? !(isPublic() || isSamePackage(typeDescription)) : !getComponentType().isVisibleTo(typeDescription));
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public AnnotationList getInheritedAnnotations() {
            Generic superClass = getSuperClass();
            AnnotationList declaredAnnotations = getDeclaredAnnotations();
            if (superClass == null) {
                return declaredAnnotations;
            }
            HashSet hashSet = new HashSet();
            Iterator it = declaredAnnotations.iterator();
            while (it.hasNext()) {
                hashSet.add(((AnnotationDescription) it.next()).getAnnotationType());
            }
            return new AnnotationList.Explicit((List<? extends AnnotationDescription>) CompoundList.of((List) declaredAnnotations, (List) superClass.asErasure().getInheritedAnnotations().inherited(hashSet)));
        }

        @Override // net.bytebuddy.description.NamedElement
        public String getActualName() {
            if (isArray()) {
                TypeDescription componentType = this;
                int i = 0;
                do {
                    i++;
                    componentType = componentType.getComponentType();
                } while (componentType.isArray());
                StringBuilder sb = new StringBuilder();
                sb.append(componentType.getActualName());
                for (int i2 = 0; i2 < i; i2++) {
                    sb.append(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
                }
                return sb.toString();
            }
            return getName();
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public boolean isConstantPool() {
            return represents(Integer.TYPE) || represents(Long.TYPE) || represents(Float.TYPE) || represents(Double.TYPE) || represents(String.class) || represents(Class.class) || JavaType.METHOD_HANDLE.getTypeStub().equals(this) || JavaType.METHOD_TYPE.getTypeStub().equals(this);
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public boolean isPrimitiveWrapper() {
            return represents(Boolean.class) || represents(Byte.class) || represents(Short.class) || represents(Character.class) || represents(Integer.class) || represents(Long.class) || represents(Float.class) || represents(Double.class);
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public boolean isAnnotationReturnType() {
            return isPrimitive() || represents(String.class) || (isAssignableTo(Enum.class) && !represents(Enum.class)) || ((isAssignableTo(Annotation.class) && !represents(Annotation.class)) || represents(Class.class) || (isArray() && !getComponentType().isArray() && getComponentType().isAnnotationReturnType()));
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public boolean isAnnotationValue() {
            return isPrimitive() || represents(String.class) || isAssignableTo(TypeDescription.class) || isAssignableTo(AnnotationDescription.class) || isAssignableTo(EnumerationDescription.class) || (isArray() && !getComponentType().isArray() && getComponentType().isAnnotationValue());
        }

        @Override // net.bytebuddy.description.type.TypeDefinition
        public boolean represents(Type type) {
            return equals(TypeDefinition.Sort.describe(type));
        }

        @Override // net.bytebuddy.description.type.TypeDefinition
        public String getTypeName() {
            return getName();
        }

        @Override // net.bytebuddy.description.TypeVariableSource
        public TypeVariableSource getEnclosingSource() {
            MethodDescription enclosingMethod = getEnclosingMethod();
            return enclosingMethod == null ? isStatic() ? TypeVariableSource.UNDEFINED : getEnclosingType() : enclosingMethod;
        }

        @Override // net.bytebuddy.description.TypeVariableSource
        public <T> T accept(TypeVariableSource.Visitor<T> visitor) {
            return visitor.onType(this);
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public boolean isPackageType() {
            return getSimpleName().equals(PackageDescription.PACKAGE_CLASS_NAME);
        }

        @Override // net.bytebuddy.description.TypeVariableSource
        public boolean isGenerified() {
            if (!getTypeVariables().isEmpty()) {
                return true;
            }
            if (isStatic()) {
                return false;
            }
            TypeDescription declaringType = getDeclaringType();
            return declaringType != null && declaringType.isGenerified();
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public int getSegmentCount() {
            TypeDescription declaringType;
            if (isStatic() || (declaringType = getDeclaringType()) == null) {
                return 0;
            }
            return declaringType.getSegmentCount() + 1;
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public TypeDescription asBoxed() {
            if (represents(Boolean.TYPE)) {
                return new ForLoadedType(Boolean.class);
            }
            if (represents(Byte.TYPE)) {
                return new ForLoadedType(Byte.class);
            }
            if (represents(Short.TYPE)) {
                return new ForLoadedType(Short.class);
            }
            if (represents(Character.TYPE)) {
                return new ForLoadedType(Character.class);
            }
            if (represents(Integer.TYPE)) {
                return new ForLoadedType(Integer.class);
            }
            if (represents(Long.TYPE)) {
                return new ForLoadedType(Long.class);
            }
            if (represents(Float.TYPE)) {
                return new ForLoadedType(Float.class);
            }
            return represents(Double.TYPE) ? new ForLoadedType(Double.class) : this;
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public TypeDescription asUnboxed() {
            if (represents(Boolean.class)) {
                return new ForLoadedType(Boolean.TYPE);
            }
            if (represents(Byte.class)) {
                return new ForLoadedType(Byte.TYPE);
            }
            if (represents(Short.class)) {
                return new ForLoadedType(Short.TYPE);
            }
            if (represents(Character.class)) {
                return new ForLoadedType(Character.TYPE);
            }
            if (represents(Integer.class)) {
                return new ForLoadedType(Integer.TYPE);
            }
            if (represents(Long.class)) {
                return new ForLoadedType(Long.TYPE);
            }
            if (represents(Float.class)) {
                return new ForLoadedType(Float.TYPE);
            }
            return represents(Double.class) ? new ForLoadedType(Double.TYPE) : this;
        }

        @Override // java.lang.Iterable
        public Iterator<TypeDefinition> iterator() {
            return new TypeDefinition.SuperClassIterator(this);
        }

        public boolean equals(Object obj) {
            if (obj != this) {
                if (obj instanceof TypeDefinition) {
                    TypeDefinition typeDefinition = (TypeDefinition) obj;
                    if (!typeDefinition.getSort().isNonGeneric() || !getName().equals(typeDefinition.asErasure().getName())) {
                    }
                }
                return false;
            }
            return true;
        }

        public int hashCode() {
            return getName().hashCode();
        }

        public String toString() {
            String strConcat;
            StringBuilder sb = new StringBuilder();
            if (isPrimitive()) {
                strConcat = "";
            } else {
                strConcat = (isInterface() ? "interface" : "class").concat(" ");
            }
            return sb.append(strConcat).append(getName()).toString();
        }

        public static abstract class OfSimpleType extends AbstractBase {
            @Override // net.bytebuddy.description.type.TypeDefinition
            public boolean isArray() {
                return false;
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public boolean isPrimitive() {
                return false;
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public TypeDescription getComponentType() {
                return TypeDescription.UNDEFINED;
            }

            @Override // net.bytebuddy.description.ByteCodeElement
            public String getDescriptor() {
                return TemperatureUnitEnum.L + getInternalName() + ";";
            }

            @Override // net.bytebuddy.description.type.TypeDescription
            public String getCanonicalName() {
                if (isAnonymousClass() || isLocalClass()) {
                    return NO_NAME;
                }
                return getName().replace(Typography.dollar, TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH);
            }

            @Override // net.bytebuddy.description.type.TypeDescription
            public String getSimpleName() {
                String internalName = getInternalName();
                int iLastIndexOf = internalName.lastIndexOf(36);
                if (iLastIndexOf == -1) {
                    iLastIndexOf = internalName.lastIndexOf(47);
                }
                if (iLastIndexOf == -1) {
                    return internalName;
                }
                while (iLastIndexOf < internalName.length() && !Character.isLetter(internalName.charAt(iLastIndexOf))) {
                    iLastIndexOf++;
                }
                return internalName.substring(iLastIndexOf);
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public StackSize getStackSize() {
                return StackSize.SINGLE;
            }

            public static abstract class WithDelegation extends OfSimpleType {
                protected abstract TypeDescription delegate();

                @Override // net.bytebuddy.description.type.TypeDescription.AbstractBase.OfSimpleType, net.bytebuddy.description.type.TypeDefinition
                public /* bridge */ /* synthetic */ TypeDefinition getComponentType() {
                    return super.getComponentType();
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public Generic getSuperClass() {
                    return delegate().getSuperClass();
                }

                @Override // net.bytebuddy.description.type.TypeDefinition
                public TypeList.Generic getInterfaces() {
                    return delegate().getInterfaces();
                }

                @Override // net.bytebuddy.description.type.TypeDescription, net.bytebuddy.description.type.TypeDefinition
                public FieldList<FieldDescription.InDefinedShape> getDeclaredFields() {
                    return delegate().getDeclaredFields();
                }

                @Override // net.bytebuddy.description.type.TypeDescription, net.bytebuddy.description.type.TypeDefinition
                public MethodList<MethodDescription.InDefinedShape> getDeclaredMethods() {
                    return delegate().getDeclaredMethods();
                }

                @Override // net.bytebuddy.description.DeclaredByType
                public TypeDescription getDeclaringType() {
                    return delegate().getDeclaringType();
                }

                @Override // net.bytebuddy.description.type.TypeDescription
                public MethodDescription getEnclosingMethod() {
                    return delegate().getEnclosingMethod();
                }

                @Override // net.bytebuddy.description.type.TypeDescription
                public TypeDescription getEnclosingType() {
                    return delegate().getEnclosingType();
                }

                @Override // net.bytebuddy.description.type.TypeDescription
                public TypeList getDeclaredTypes() {
                    return delegate().getDeclaredTypes();
                }

                @Override // net.bytebuddy.description.type.TypeDescription
                public boolean isAnonymousClass() {
                    return delegate().isAnonymousClass();
                }

                @Override // net.bytebuddy.description.type.TypeDescription
                public boolean isLocalClass() {
                    return delegate().isLocalClass();
                }

                @Override // net.bytebuddy.description.type.TypeDescription
                public boolean isMemberClass() {
                    return delegate().isMemberClass();
                }

                @Override // net.bytebuddy.description.type.TypeDescription
                public PackageDescription getPackage() {
                    return delegate().getPackage();
                }

                @Override // net.bytebuddy.description.annotation.AnnotationSource
                public AnnotationList getDeclaredAnnotations() {
                    return delegate().getDeclaredAnnotations();
                }

                @Override // net.bytebuddy.description.TypeVariableSource
                public TypeList.Generic getTypeVariables() {
                    return delegate().getTypeVariables();
                }

                @Override // net.bytebuddy.description.ModifierReviewable
                public int getModifiers() {
                    return delegate().getModifiers();
                }

                @Override // net.bytebuddy.description.type.TypeDescription.AbstractBase, net.bytebuddy.description.ByteCodeElement
                public String getGenericSignature() {
                    return delegate().getGenericSignature();
                }

                @Override // net.bytebuddy.description.type.TypeDescription.AbstractBase, net.bytebuddy.description.type.TypeDescription
                public int getActualModifiers(boolean z) {
                    return delegate().getActualModifiers(z);
                }
            }
        }
    }

    public static class ForLoadedType extends AbstractBase implements Serializable {
        private static final long serialVersionUID = 1;
        private final Class<?> type;

        public ForLoadedType(Class<?> cls) {
            this.type = cls;
        }

        public static String getName(Class<?> cls) {
            String name = cls.getName();
            int iIndexOf = name.indexOf(47);
            return iIndexOf == -1 ? name : name.substring(0, iIndexOf);
        }

        @Override // net.bytebuddy.description.type.TypeDescription.AbstractBase, net.bytebuddy.description.type.TypeDescription
        public boolean isAssignableFrom(Class<?> cls) {
            return this.type.isAssignableFrom(cls) || super.isAssignableFrom(cls);
        }

        @Override // net.bytebuddy.description.type.TypeDescription.AbstractBase, net.bytebuddy.description.type.TypeDescription
        public boolean isAssignableTo(Class<?> cls) {
            return cls.isAssignableFrom(this.type) || super.isAssignableTo(cls);
        }

        @Override // net.bytebuddy.description.type.TypeDescription.AbstractBase, net.bytebuddy.description.type.TypeDefinition
        public boolean represents(Type type) {
            return type == this.type || super.represents(type);
        }

        @Override // net.bytebuddy.description.type.TypeDefinition
        public TypeDescription getComponentType() {
            Class<?> componentType = this.type.getComponentType();
            return componentType == null ? TypeDescription.UNDEFINED : new ForLoadedType(componentType);
        }

        @Override // net.bytebuddy.description.type.TypeDefinition
        public boolean isArray() {
            return this.type.isArray();
        }

        @Override // net.bytebuddy.description.type.TypeDefinition
        public boolean isPrimitive() {
            return this.type.isPrimitive();
        }

        @Override // net.bytebuddy.description.ModifierReviewable.AbstractBase, net.bytebuddy.description.ModifierReviewable.ForTypeDefinition
        public boolean isAnnotation() {
            return this.type.isAnnotation();
        }

        @Override // net.bytebuddy.description.type.TypeDefinition
        public Generic getSuperClass() {
            return RAW_TYPES ? this.type.getSuperclass() == null ? Generic.UNDEFINED : new Generic.OfNonGenericType.ForLoadedType(this.type.getSuperclass()) : this.type.getSuperclass() == null ? Generic.UNDEFINED : new Generic.LazyProjection.ForLoadedSuperClass(this.type);
        }

        @Override // net.bytebuddy.description.type.TypeDefinition
        public TypeList.Generic getInterfaces() {
            return RAW_TYPES ? isArray() ? ARRAY_INTERFACES : new TypeList.Generic.ForLoadedTypes(this.type.getInterfaces()) : isArray() ? ARRAY_INTERFACES : new TypeList.Generic.OfLoadedInterfaceTypes(this.type);
        }

        @Override // net.bytebuddy.description.DeclaredByType
        public TypeDescription getDeclaringType() {
            Class<?> declaringClass = this.type.getDeclaringClass();
            return declaringClass == null ? TypeDescription.UNDEFINED : new ForLoadedType(declaringClass);
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public MethodDescription getEnclosingMethod() {
            Method enclosingMethod = this.type.getEnclosingMethod();
            Constructor<?> enclosingConstructor = this.type.getEnclosingConstructor();
            if (enclosingMethod != null) {
                return new MethodDescription.ForLoadedMethod(enclosingMethod);
            }
            if (enclosingConstructor != null) {
                return new MethodDescription.ForLoadedConstructor(enclosingConstructor);
            }
            return MethodDescription.UNDEFINED;
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public TypeDescription getEnclosingType() {
            Class<?> enclosingClass = this.type.getEnclosingClass();
            return enclosingClass == null ? TypeDescription.UNDEFINED : new ForLoadedType(enclosingClass);
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public TypeList getDeclaredTypes() {
            return new TypeList.ForLoadedTypes(this.type.getDeclaredClasses());
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public String getSimpleName() {
            String simpleName = this.type.getSimpleName();
            int iIndexOf = simpleName.indexOf(47);
            if (iIndexOf == -1) {
                return simpleName;
            }
            StringBuilder sb = new StringBuilder(simpleName.substring(0, iIndexOf));
            for (Class<?> componentType = this.type; componentType.isArray(); componentType = componentType.getComponentType()) {
                sb.append(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
            }
            return sb.toString();
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public boolean isAnonymousClass() {
            return this.type.isAnonymousClass();
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public boolean isLocalClass() {
            return this.type.isLocalClass();
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public boolean isMemberClass() {
            return this.type.isMemberClass();
        }

        @Override // net.bytebuddy.description.type.TypeDescription, net.bytebuddy.description.type.TypeDefinition
        public FieldList<FieldDescription.InDefinedShape> getDeclaredFields() {
            return new FieldList.ForLoadedFields(this.type.getDeclaredFields());
        }

        @Override // net.bytebuddy.description.type.TypeDescription, net.bytebuddy.description.type.TypeDefinition
        public MethodList<MethodDescription.InDefinedShape> getDeclaredMethods() {
            return new MethodList.ForLoadedMethods(this.type);
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public PackageDescription getPackage() {
            Package r0 = this.type.getPackage();
            return r0 == null ? PackageDescription.UNDEFINED : new PackageDescription.ForLoadedPackage(r0);
        }

        @Override // net.bytebuddy.description.type.TypeDefinition
        public StackSize getStackSize() {
            return StackSize.of(this.type);
        }

        @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
        public String getName() {
            return getName(this.type);
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public String getCanonicalName() {
            String canonicalName = this.type.getCanonicalName();
            if (canonicalName == null) {
                return NO_NAME;
            }
            int iIndexOf = canonicalName.indexOf(47);
            if (iIndexOf == -1) {
                return canonicalName;
            }
            StringBuilder sb = new StringBuilder(canonicalName.substring(0, iIndexOf));
            for (Class<?> componentType = this.type; componentType.isArray(); componentType = componentType.getComponentType()) {
                sb.append(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
            }
            return sb.toString();
        }

        @Override // net.bytebuddy.description.ByteCodeElement
        public String getDescriptor() {
            String name = this.type.getName();
            int iIndexOf = name.indexOf(47);
            if (iIndexOf == -1) {
                return net.bytebuddy.jar.asm.Type.getDescriptor(this.type);
            }
            return TemperatureUnitEnum.L + name.substring(0, iIndexOf).replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, ClassPathElement.SEPARATOR_CHAR) + ";";
        }

        @Override // net.bytebuddy.description.ModifierReviewable
        public int getModifiers() {
            return this.type.getModifiers();
        }

        @Override // net.bytebuddy.description.TypeVariableSource
        public TypeList.Generic getTypeVariables() {
            if (RAW_TYPES) {
                return new TypeList.Generic.Empty();
            }
            return TypeList.Generic.ForLoadedTypes.OfTypeVariables.of((GenericDeclaration) this.type);
        }

        @Override // net.bytebuddy.description.annotation.AnnotationSource
        public AnnotationList getDeclaredAnnotations() {
            return new AnnotationList.ForLoadedAnnotations(this.type.getDeclaredAnnotations());
        }
    }

    public static class ArrayProjection extends AbstractBase {
        private static final int ARRAY_EXCLUDED = 8712;
        private static final int ARRAY_IMPLIED = 1040;
        private final int arity;
        private final TypeDescription componentType;

        @Override // net.bytebuddy.description.type.TypeDescription
        public boolean isAnonymousClass() {
            return false;
        }

        @Override // net.bytebuddy.description.type.TypeDefinition
        public boolean isArray() {
            return true;
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public boolean isLocalClass() {
            return false;
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public boolean isMemberClass() {
            return false;
        }

        @Override // net.bytebuddy.description.type.TypeDefinition
        public boolean isPrimitive() {
            return false;
        }

        protected ArrayProjection(TypeDescription typeDescription, int i) {
            this.componentType = typeDescription;
            this.arity = i;
        }

        public static TypeDescription of(TypeDescription typeDescription) {
            return of(typeDescription, 1);
        }

        public static TypeDescription of(TypeDescription typeDescription, int i) {
            if (i < 0) {
                throw new IllegalArgumentException("Arrays cannot have a negative arity");
            }
            while (typeDescription.isArray()) {
                typeDescription = typeDescription.getComponentType();
                i++;
            }
            return i == 0 ? typeDescription : new ArrayProjection(typeDescription, i);
        }

        @Override // net.bytebuddy.description.type.TypeDefinition
        public TypeDescription getComponentType() {
            int i = this.arity;
            return i == 1 ? this.componentType : new ArrayProjection(this.componentType, i - 1);
        }

        @Override // net.bytebuddy.description.type.TypeDefinition
        public Generic getSuperClass() {
            return Generic.OBJECT;
        }

        @Override // net.bytebuddy.description.type.TypeDefinition
        public TypeList.Generic getInterfaces() {
            return ARRAY_INTERFACES;
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public MethodDescription getEnclosingMethod() {
            return MethodDescription.UNDEFINED;
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public TypeDescription getEnclosingType() {
            return TypeDescription.UNDEFINED;
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public TypeList getDeclaredTypes() {
            return new TypeList.Empty();
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public String getSimpleName() {
            StringBuilder sb = new StringBuilder(this.componentType.getSimpleName());
            for (int i = 0; i < this.arity; i++) {
                sb.append(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
            }
            return sb.toString();
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public String getCanonicalName() {
            String canonicalName = this.componentType.getCanonicalName();
            if (canonicalName == null) {
                return NO_NAME;
            }
            StringBuilder sb = new StringBuilder(canonicalName);
            for (int i = 0; i < this.arity; i++) {
                sb.append(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
            }
            return sb.toString();
        }

        @Override // net.bytebuddy.description.type.TypeDescription, net.bytebuddy.description.type.TypeDefinition
        public FieldList<FieldDescription.InDefinedShape> getDeclaredFields() {
            return new FieldList.Empty();
        }

        @Override // net.bytebuddy.description.type.TypeDescription, net.bytebuddy.description.type.TypeDefinition
        public MethodList<MethodDescription.InDefinedShape> getDeclaredMethods() {
            return new MethodList.Empty();
        }

        @Override // net.bytebuddy.description.type.TypeDefinition
        public StackSize getStackSize() {
            return StackSize.SINGLE;
        }

        @Override // net.bytebuddy.description.annotation.AnnotationSource
        public AnnotationList getDeclaredAnnotations() {
            return new AnnotationList.Empty();
        }

        @Override // net.bytebuddy.description.type.TypeDescription.AbstractBase, net.bytebuddy.description.type.TypeDescription
        public AnnotationList getInheritedAnnotations() {
            return new AnnotationList.Empty();
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public PackageDescription getPackage() {
            return PackageDescription.UNDEFINED;
        }

        @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
        public String getName() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < this.arity; i++) {
                sb.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
            }
            return sb.append(this.componentType.getDescriptor().replace(ClassPathElement.SEPARATOR_CHAR, TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH)).toString();
        }

        @Override // net.bytebuddy.description.ByteCodeElement
        public String getDescriptor() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < this.arity; i++) {
                sb.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
            }
            return sb.append(this.componentType.getDescriptor()).toString();
        }

        @Override // net.bytebuddy.description.DeclaredByType
        public TypeDescription getDeclaringType() {
            return TypeDescription.UNDEFINED;
        }

        @Override // net.bytebuddy.description.ModifierReviewable
        public int getModifiers() {
            return (getComponentType().getModifiers() & (-8713)) | 1040;
        }

        @Override // net.bytebuddy.description.TypeVariableSource
        public TypeList.Generic getTypeVariables() {
            return new TypeList.Generic.Empty();
        }
    }

    public static class Latent extends AbstractBase.OfSimpleType {
        private final List<? extends Generic> interfaces;
        private final int modifiers;
        private final String name;
        private final Generic superClass;

        public Latent(String str, int i, Generic generic, Generic... genericArr) {
            this(str, i, generic, (List<? extends Generic>) Arrays.asList(genericArr));
        }

        public Latent(String str, int i, Generic generic, List<? extends Generic> list) {
            this.name = str;
            this.modifiers = i;
            this.superClass = generic;
            this.interfaces = list;
        }

        @Override // net.bytebuddy.description.type.TypeDefinition
        public Generic getSuperClass() {
            return this.superClass;
        }

        @Override // net.bytebuddy.description.type.TypeDefinition
        public TypeList.Generic getInterfaces() {
            return new TypeList.Generic.Explicit(this.interfaces);
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public MethodDescription getEnclosingMethod() {
            throw new IllegalStateException("Cannot resolve enclosing method of a latent type description: " + this);
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public TypeDescription getEnclosingType() {
            throw new IllegalStateException("Cannot resolve enclosing type of a latent type description: " + this);
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public TypeList getDeclaredTypes() {
            throw new IllegalStateException("Cannot resolve inner types of a latent type description: " + this);
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public boolean isAnonymousClass() {
            throw new IllegalStateException("Cannot resolve anonymous type property of a latent type description: " + this);
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public boolean isLocalClass() {
            throw new IllegalStateException("Cannot resolve local class property of a latent type description: " + this);
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public boolean isMemberClass() {
            throw new IllegalStateException("Cannot resolve member class property of a latent type description: " + this);
        }

        @Override // net.bytebuddy.description.type.TypeDescription, net.bytebuddy.description.type.TypeDefinition
        public FieldList<FieldDescription.InDefinedShape> getDeclaredFields() {
            throw new IllegalStateException("Cannot resolve declared fields of a latent type description: " + this);
        }

        @Override // net.bytebuddy.description.type.TypeDescription, net.bytebuddy.description.type.TypeDefinition
        public MethodList<MethodDescription.InDefinedShape> getDeclaredMethods() {
            throw new IllegalStateException("Cannot resolve declared methods of a latent type description: " + this);
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public PackageDescription getPackage() {
            String name = getName();
            int iLastIndexOf = name.lastIndexOf(46);
            if (iLastIndexOf == -1) {
                return PackageDescription.UNDEFINED;
            }
            return new PackageDescription.Simple(name.substring(0, iLastIndexOf));
        }

        @Override // net.bytebuddy.description.annotation.AnnotationSource
        public AnnotationList getDeclaredAnnotations() {
            throw new IllegalStateException("Cannot resolve declared annotations of a latent type description: " + this);
        }

        @Override // net.bytebuddy.description.DeclaredByType
        public TypeDescription getDeclaringType() {
            throw new IllegalStateException("Cannot resolve declared type of a latent type description: " + this);
        }

        @Override // net.bytebuddy.description.ModifierReviewable
        public int getModifiers() {
            return this.modifiers;
        }

        @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
        public String getName() {
            return this.name;
        }

        @Override // net.bytebuddy.description.TypeVariableSource
        public TypeList.Generic getTypeVariables() {
            throw new IllegalStateException("Cannot resolve type variables of a latent type description: " + this);
        }
    }

    public static class ForPackageDescription extends AbstractBase.OfSimpleType {
        private final PackageDescription packageDescription;

        @Override // net.bytebuddy.description.ModifierReviewable
        public int getModifiers() {
            return PackageDescription.PACKAGE_MODIFIERS;
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public boolean isAnonymousClass() {
            return false;
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public boolean isLocalClass() {
            return false;
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public boolean isMemberClass() {
            return false;
        }

        public ForPackageDescription(PackageDescription packageDescription) {
            this.packageDescription = packageDescription;
        }

        @Override // net.bytebuddy.description.type.TypeDefinition
        public Generic getSuperClass() {
            return Generic.OBJECT;
        }

        @Override // net.bytebuddy.description.type.TypeDefinition
        public TypeList.Generic getInterfaces() {
            return new TypeList.Generic.Empty();
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public MethodDescription getEnclosingMethod() {
            return MethodDescription.UNDEFINED;
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public TypeDescription getEnclosingType() {
            return TypeDescription.UNDEFINED;
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public TypeList getDeclaredTypes() {
            return new TypeList.Empty();
        }

        @Override // net.bytebuddy.description.type.TypeDescription, net.bytebuddy.description.type.TypeDefinition
        public FieldList<FieldDescription.InDefinedShape> getDeclaredFields() {
            return new FieldList.Empty();
        }

        @Override // net.bytebuddy.description.type.TypeDescription, net.bytebuddy.description.type.TypeDefinition
        public MethodList<MethodDescription.InDefinedShape> getDeclaredMethods() {
            return new MethodList.Empty();
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public PackageDescription getPackage() {
            return this.packageDescription;
        }

        @Override // net.bytebuddy.description.annotation.AnnotationSource
        public AnnotationList getDeclaredAnnotations() {
            return this.packageDescription.getDeclaredAnnotations();
        }

        @Override // net.bytebuddy.description.DeclaredByType
        public TypeDescription getDeclaringType() {
            return TypeDescription.UNDEFINED;
        }

        @Override // net.bytebuddy.description.TypeVariableSource
        public TypeList.Generic getTypeVariables() {
            return new TypeList.Generic.Empty();
        }

        @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
        public String getName() {
            return this.packageDescription.getName() + ".package-info";
        }
    }

    public static class SuperTypeLoading extends AbstractBase {
        private final ClassLoader classLoader;
        private final ClassLoadingDelegate classLoadingDelegate;
        private final TypeDescription delegate;

        public SuperTypeLoading(TypeDescription typeDescription, ClassLoader classLoader) {
            this(typeDescription, classLoader, ClassLoadingDelegate.Simple.INSTANCE);
        }

        public SuperTypeLoading(TypeDescription typeDescription, ClassLoader classLoader, ClassLoadingDelegate classLoadingDelegate) {
            this.delegate = typeDescription;
            this.classLoader = classLoader;
            this.classLoadingDelegate = classLoadingDelegate;
        }

        @Override // net.bytebuddy.description.annotation.AnnotationSource
        public AnnotationList getDeclaredAnnotations() {
            return this.delegate.getDeclaredAnnotations();
        }

        @Override // net.bytebuddy.description.ModifierReviewable
        public int getModifiers() {
            return this.delegate.getModifiers();
        }

        @Override // net.bytebuddy.description.TypeVariableSource
        public TypeList.Generic getTypeVariables() {
            return this.delegate.getTypeVariables();
        }

        @Override // net.bytebuddy.description.ByteCodeElement
        public String getDescriptor() {
            return this.delegate.getDescriptor();
        }

        @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
        public String getName() {
            return this.delegate.getName();
        }

        @Override // net.bytebuddy.description.type.TypeDefinition
        public Generic getSuperClass() {
            Generic superClass = this.delegate.getSuperClass();
            return superClass == null ? Generic.UNDEFINED : new ClassLoadingTypeProjection(superClass, this.classLoader, this.classLoadingDelegate);
        }

        @Override // net.bytebuddy.description.type.TypeDefinition
        public TypeList.Generic getInterfaces() {
            return new ClassLoadingTypeList(this.delegate.getInterfaces(), this.classLoader, this.classLoadingDelegate);
        }

        @Override // net.bytebuddy.description.type.TypeDescription, net.bytebuddy.description.type.TypeDefinition
        public FieldList<FieldDescription.InDefinedShape> getDeclaredFields() {
            return this.delegate.getDeclaredFields();
        }

        @Override // net.bytebuddy.description.type.TypeDescription, net.bytebuddy.description.type.TypeDefinition
        public MethodList<MethodDescription.InDefinedShape> getDeclaredMethods() {
            return this.delegate.getDeclaredMethods();
        }

        @Override // net.bytebuddy.description.type.TypeDefinition
        public StackSize getStackSize() {
            return this.delegate.getStackSize();
        }

        @Override // net.bytebuddy.description.type.TypeDefinition
        public boolean isArray() {
            return this.delegate.isArray();
        }

        @Override // net.bytebuddy.description.type.TypeDefinition
        public boolean isPrimitive() {
            return this.delegate.isPrimitive();
        }

        @Override // net.bytebuddy.description.type.TypeDefinition
        public TypeDescription getComponentType() {
            return this.delegate.getComponentType();
        }

        @Override // net.bytebuddy.description.DeclaredByType
        public TypeDescription getDeclaringType() {
            return this.delegate.getDeclaringType();
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public TypeList getDeclaredTypes() {
            return this.delegate.getDeclaredTypes();
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public MethodDescription getEnclosingMethod() {
            return this.delegate.getEnclosingMethod();
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public TypeDescription getEnclosingType() {
            return this.delegate.getEnclosingType();
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public String getSimpleName() {
            return this.delegate.getSimpleName();
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public String getCanonicalName() {
            return this.delegate.getCanonicalName();
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public boolean isAnonymousClass() {
            return this.delegate.isAnonymousClass();
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public boolean isLocalClass() {
            return this.delegate.isLocalClass();
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public boolean isMemberClass() {
            return this.delegate.isMemberClass();
        }

        @Override // net.bytebuddy.description.type.TypeDescription
        public PackageDescription getPackage() {
            return this.delegate.getPackage();
        }

        public interface ClassLoadingDelegate {
            Class<?> load(String str, ClassLoader classLoader) throws ClassNotFoundException;

            public enum Simple implements ClassLoadingDelegate {
                INSTANCE;

                @Override // net.bytebuddy.description.type.TypeDescription.SuperTypeLoading.ClassLoadingDelegate
                public Class<?> load(String str, ClassLoader classLoader) throws ClassNotFoundException {
                    return Class.forName(str, false, classLoader);
                }
            }
        }

        protected static class ClassLoadingTypeProjection extends Generic.LazyProjection {
            private final ClassLoader classLoader;
            private final ClassLoadingDelegate classLoadingDelegate;
            private final Generic delegate;

            protected ClassLoadingTypeProjection(Generic generic, ClassLoader classLoader, ClassLoadingDelegate classLoadingDelegate) {
                this.delegate = generic;
                this.classLoader = classLoader;
                this.classLoadingDelegate = classLoadingDelegate;
            }

            @Override // net.bytebuddy.description.annotation.AnnotationSource
            public AnnotationList getDeclaredAnnotations() {
                return this.delegate.getDeclaredAnnotations();
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public TypeDescription asErasure() {
                try {
                    return new ForLoadedType(this.classLoadingDelegate.load(this.delegate.asErasure().getName(), this.classLoader));
                } catch (ClassNotFoundException unused) {
                    return this.delegate.asErasure();
                }
            }

            @Override // net.bytebuddy.description.type.TypeDescription.Generic.LazyProjection
            protected Generic resolve() {
                return this.delegate;
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public Generic getSuperClass() {
                Generic superClass = this.delegate.getSuperClass();
                if (superClass == null) {
                    return Generic.UNDEFINED;
                }
                try {
                    return new ClassLoadingTypeProjection(superClass, this.classLoadingDelegate.load(this.delegate.asErasure().getName(), this.classLoader).getClassLoader(), this.classLoadingDelegate);
                } catch (ClassNotFoundException unused) {
                    return superClass;
                }
            }

            @Override // net.bytebuddy.description.type.TypeDefinition
            public TypeList.Generic getInterfaces() {
                TypeList.Generic interfaces = this.delegate.getInterfaces();
                try {
                    return new ClassLoadingTypeList(interfaces, this.classLoadingDelegate.load(this.delegate.asErasure().getName(), this.classLoader).getClassLoader(), this.classLoadingDelegate);
                } catch (ClassNotFoundException unused) {
                    return interfaces;
                }
            }

            @Override // java.lang.Iterable
            public Iterator<TypeDefinition> iterator() {
                return new TypeDefinition.SuperClassIterator(this);
            }
        }

        protected static class ClassLoadingTypeList extends TypeList.Generic.AbstractBase {
            private final ClassLoader classLoader;
            private final ClassLoadingDelegate classLoadingDelegate;
            private final TypeList.Generic delegate;

            protected ClassLoadingTypeList(TypeList.Generic generic, ClassLoader classLoader, ClassLoadingDelegate classLoadingDelegate) {
                this.delegate = generic;
                this.classLoader = classLoader;
                this.classLoadingDelegate = classLoadingDelegate;
            }

            @Override // java.util.AbstractList, java.util.List
            public Generic get(int i) {
                return new ClassLoadingTypeProjection((Generic) this.delegate.get(i), this.classLoader, this.classLoadingDelegate);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
            public int size() {
                return this.delegate.size();
            }
        }
    }
}
