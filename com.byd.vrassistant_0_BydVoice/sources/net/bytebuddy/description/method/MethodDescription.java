package net.bytebuddy.description.method;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.GenericSignatureFormatError;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.description.ByteCodeElement;
import net.bytebuddy.description.ModifierReviewable;
import net.bytebuddy.description.NamedElement;
import net.bytebuddy.description.TypeVariableSource;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.annotation.AnnotationValue;
import net.bytebuddy.description.enumeration.EnumerationDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.description.type.TypeVariableToken;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.jar.asm.signature.SignatureVisitor;
import net.bytebuddy.jar.asm.signature.SignatureWriter;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaConstant;
import net.bytebuddy.utility.JavaType;

/* JADX INFO: loaded from: classes3.dex */
public interface MethodDescription extends TypeVariableSource, ModifierReviewable.ForMethodDescription, NamedElement.WithGenericName, ByteCodeElement, ByteCodeElement.TypeDependant<InDefinedShape, Token> {
    public static final String CONSTRUCTOR_INTERNAL_NAME = "<init>";
    public static final String TYPE_INITIALIZER_INTERNAL_NAME = "<clinit>";
    public static final int TYPE_INITIALIZER_MODIFIER = 8;
    public static final MethodDescription UNDEFINED = null;

    public interface InGenericShape extends MethodDescription {
        @Override // net.bytebuddy.description.DeclaredByType
        TypeDescription.Generic getDeclaringType();

        @Override // net.bytebuddy.description.method.MethodDescription, net.bytebuddy.description.method.MethodDescription.InDefinedShape
        ParameterList<ParameterDescription.InGenericShape> getParameters();
    }

    SignatureToken asSignatureToken();

    TypeToken asTypeToken();

    int getActualModifiers();

    int getActualModifiers(boolean z);

    int getActualModifiers(boolean z, Visibility visibility);

    <T> T getDefaultValue(Class<T> cls);

    AnnotationValue<?, ?> getDefaultValue();

    TypeList.Generic getExceptionTypes();

    ParameterList<?> getParameters();

    TypeDescription.Generic getReceiverType();

    TypeDescription.Generic getReturnType();

    int getStackSize();

    boolean isBootstrap();

    boolean isBootstrap(List<?> list);

    boolean isBridgeCompatible(TypeToken typeToken);

    boolean isConstructor();

    boolean isDefaultMethod();

    boolean isDefaultValue();

    boolean isDefaultValue(AnnotationValue<?, ?> annotationValue);

    boolean isInvokableOn(TypeDescription typeDescription);

    boolean isMethod();

    boolean isSpecializableFor(TypeDescription typeDescription);

    boolean isTypeInitializer();

    boolean isVirtual();

    boolean represents(Constructor<?> constructor);

    boolean represents(Method method);

    public interface InDefinedShape extends MethodDescription {
        @Override // net.bytebuddy.description.DeclaredByType
        TypeDescription getDeclaringType();

        ParameterList<ParameterDescription.InDefinedShape> getParameters();

        public static abstract class AbstractBase extends AbstractBase implements InDefinedShape {
            @Override // net.bytebuddy.description.ByteCodeElement.TypeDependant
            public InDefinedShape asDefined() {
                return this;
            }

            public TypeDescription.Generic getReceiverType() {
                if (isStatic()) {
                    return TypeDescription.Generic.UNDEFINED;
                }
                if (isConstructor()) {
                    TypeDescription declaringType = getDeclaringType();
                    TypeDescription enclosingType = getDeclaringType().getEnclosingType();
                    if (enclosingType == null) {
                        return TypeDescription.Generic.OfParameterizedType.ForGenerifiedErasure.of(declaringType);
                    }
                    if (declaringType.isStatic()) {
                        return enclosingType.asGenericType();
                    }
                    return TypeDescription.Generic.OfParameterizedType.ForGenerifiedErasure.of(enclosingType);
                }
                return TypeDescription.Generic.OfParameterizedType.ForGenerifiedErasure.of(getDeclaringType());
            }
        }
    }

    public static abstract class AbstractBase extends TypeVariableSource.AbstractBase implements MethodDescription {
        private static final int SOURCE_MODIFIERS = 1343;

        @Override // net.bytebuddy.description.ByteCodeElement.TypeDependant
        public /* bridge */ /* synthetic */ ByteCodeElement.Token asToken(ElementMatcher elementMatcher) {
            return asToken((ElementMatcher<? super TypeDescription>) elementMatcher);
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public int getStackSize() {
            return getParameters().asTypeList().getStackSize() + (!isStatic() ? 1 : 0);
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public boolean isMethod() {
            return (isConstructor() || isTypeInitializer()) ? false : true;
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public boolean isConstructor() {
            return MethodDescription.CONSTRUCTOR_INTERNAL_NAME.equals(getInternalName());
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public boolean isTypeInitializer() {
            return MethodDescription.TYPE_INITIALIZER_INTERNAL_NAME.equals(getInternalName());
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public boolean represents(Method method) {
            return equals(new ForLoadedMethod(method));
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public boolean represents(Constructor<?> constructor) {
            return equals(new ForLoadedConstructor(constructor));
        }

        @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
        public String getName() {
            if (isMethod()) {
                return getInternalName();
            }
            return getDeclaringType().asErasure().getName();
        }

        @Override // net.bytebuddy.description.NamedElement
        public String getActualName() {
            return isMethod() ? getName() : "";
        }

        @Override // net.bytebuddy.description.ByteCodeElement
        public String getDescriptor() {
            StringBuilder sb = new StringBuilder("(");
            Iterator it = getParameters().asTypeList().asErasures().iterator();
            while (it.hasNext()) {
                sb.append(((TypeDescription) it.next()).getDescriptor());
            }
            return sb.append(")").append(getReturnType().asErasure().getDescriptor()).toString();
        }

        @Override // net.bytebuddy.description.ByteCodeElement
        public String getGenericSignature() {
            SignatureVisitor signatureVisitorVisitInterfaceBound;
            try {
                SignatureWriter signatureWriter = new SignatureWriter();
                boolean z = false;
                for (TypeDescription.Generic generic : getTypeVariables()) {
                    signatureWriter.visitFormalTypeParameter(generic.getSymbol());
                    boolean z2 = true;
                    for (TypeDescription.Generic generic2 : generic.getUpperBounds()) {
                        if (z2) {
                            signatureVisitorVisitInterfaceBound = signatureWriter.visitClassBound();
                        } else {
                            signatureVisitorVisitInterfaceBound = signatureWriter.visitInterfaceBound();
                        }
                        generic2.accept(new TypeDescription.Generic.Visitor.ForSignatureVisitor(signatureVisitorVisitInterfaceBound));
                        z2 = false;
                    }
                    z = true;
                }
                for (TypeDescription.Generic generic3 : getParameters().asTypeList()) {
                    generic3.accept(new TypeDescription.Generic.Visitor.ForSignatureVisitor(signatureWriter.visitParameterType()));
                    z = z || !generic3.getSort().isNonGeneric();
                }
                TypeDescription.Generic returnType = getReturnType();
                returnType.accept(new TypeDescription.Generic.Visitor.ForSignatureVisitor(signatureWriter.visitReturnType()));
                boolean z3 = z || !returnType.getSort().isNonGeneric();
                TypeList.Generic<TypeDescription.Generic> exceptionTypes = getExceptionTypes();
                if (!exceptionTypes.filter(ElementMatchers.not(ElementMatchers.ofSort(TypeDefinition.Sort.NON_GENERIC))).isEmpty()) {
                    for (TypeDescription.Generic generic4 : exceptionTypes) {
                        generic4.accept(new TypeDescription.Generic.Visitor.ForSignatureVisitor(signatureWriter.visitExceptionType()));
                        z3 = z3 || !generic4.getSort().isNonGeneric();
                    }
                }
                return z3 ? signatureWriter.toString() : NON_GENERIC_SIGNATURE;
            } catch (GenericSignatureFormatError unused) {
                return NON_GENERIC_SIGNATURE;
            }
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public int getActualModifiers() {
            return getModifiers() | (getDeclaredAnnotations().isAnnotationPresent(Deprecated.class) ? 131072 : 0);
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public int getActualModifiers(boolean z) {
            if (z) {
                return getActualModifiers() & (-1281);
            }
            return (getActualModifiers() & (-257)) | 1024;
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public int getActualModifiers(boolean z, Visibility visibility) {
            return ModifierContributor.Resolver.of(Collections.singleton(getVisibility().expandTo(visibility))).resolve(getActualModifiers(z));
        }

        @Override // net.bytebuddy.description.ByteCodeElement
        public boolean isVisibleTo(TypeDescription typeDescription) {
            return (isVirtual() || getDeclaringType().asErasure().isVisibleTo(typeDescription)) && (isPublic() || typeDescription.equals(getDeclaringType().asErasure()) || ((isProtected() && getDeclaringType().asErasure().isAssignableFrom(typeDescription)) || (!isPrivate() && typeDescription.isSamePackage(getDeclaringType().asErasure()))));
        }

        @Override // net.bytebuddy.description.ByteCodeElement
        public boolean isAccessibleTo(TypeDescription typeDescription) {
            return (isVirtual() || getDeclaringType().asErasure().isVisibleTo(typeDescription)) && (isPublic() || typeDescription.equals(getDeclaringType().asErasure()) || (!isPrivate() && typeDescription.isSamePackage(getDeclaringType().asErasure())));
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public boolean isVirtual() {
            return (isConstructor() || isPrivate() || isStatic() || isTypeInitializer()) ? false : true;
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public boolean isDefaultMethod() {
            return (isAbstract() || isBridge() || !getDeclaringType().isInterface()) ? false : true;
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public boolean isSpecializableFor(TypeDescription typeDescription) {
            if (isStatic()) {
                return false;
            }
            if (isPrivate() || isConstructor()) {
                return getDeclaringType().equals(typeDescription);
            }
            return !isAbstract() && getDeclaringType().asErasure().isAssignableFrom(typeDescription);
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public <T> T getDefaultValue(Class<T> cls) {
            return cls.cast(getDefaultValue());
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public boolean isInvokableOn(TypeDescription typeDescription) {
            return !isStatic() && !isTypeInitializer() && isVisibleTo(typeDescription) && (!isVirtual() ? !getDeclaringType().asErasure().equals(typeDescription) : !getDeclaringType().asErasure().isAssignableFrom(typeDescription));
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public boolean isBootstrap() {
            TypeList typeListAsErasures;
            int size;
            TypeDescription typeDescriptionAsErasure = getReturnType().asErasure();
            if ((isMethod() && (!isStatic() || (!JavaType.CALL_SITE.getTypeStub().isAssignableFrom(typeDescriptionAsErasure) && !JavaType.CALL_SITE.getTypeStub().isAssignableTo(typeDescriptionAsErasure)))) || ((isConstructor() && !JavaType.CALL_SITE.getTypeStub().isAssignableFrom(getDeclaringType().asErasure())) || (size = (typeListAsErasures = getParameters().asTypeList().asErasures()).size()) == 0)) {
                return false;
            }
            if (size == 1) {
                return typeListAsErasures.getOnly().represents(Object[].class);
            }
            if (size == 2) {
                return JavaType.METHOD_HANDLES_LOOKUP.getTypeStub().isAssignableTo((TypeDescription) typeListAsErasures.get(0)) && ((TypeDescription) typeListAsErasures.get(1)).represents(Object[].class);
            }
            if (size == 3) {
                if (!JavaType.METHOD_HANDLES_LOOKUP.getTypeStub().isAssignableTo((TypeDescription) typeListAsErasures.get(0))) {
                    return false;
                }
                if (((TypeDescription) typeListAsErasures.get(1)).represents(Object.class) || ((TypeDescription) typeListAsErasures.get(1)).represents(String.class)) {
                    return ((TypeDescription) typeListAsErasures.get(2)).represents(Object[].class) || JavaType.METHOD_TYPE.getTypeStub().isAssignableTo((TypeDescription) typeListAsErasures.get(2));
                }
                return false;
            }
            if (!JavaType.METHOD_HANDLES_LOOKUP.getTypeStub().isAssignableTo((TypeDescription) typeListAsErasures.get(0)) || ((!((TypeDescription) typeListAsErasures.get(1)).represents(Object.class) && !((TypeDescription) typeListAsErasures.get(1)).represents(String.class)) || !JavaType.METHOD_TYPE.getTypeStub().isAssignableTo((TypeDescription) typeListAsErasures.get(2)))) {
                return false;
            }
            int i = 4;
            for (TypeDescription typeDescription : typeListAsErasures.subList(3, typeListAsErasures.size())) {
                if (!typeDescription.represents(Object.class) && !typeDescription.isConstantPool()) {
                    return typeDescription.represents(Object[].class) && i == typeListAsErasures.size();
                }
                i++;
            }
            return true;
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public boolean isBootstrap(List<?> list) {
            if (!isBootstrap()) {
                return false;
            }
            for (Object obj : list) {
                Class<?> cls = obj.getClass();
                if (cls != String.class && cls != Integer.class && cls != Long.class && cls != Float.class && cls != Double.class && !TypeDescription.class.isAssignableFrom(cls) && !JavaConstant.MethodHandle.class.isAssignableFrom(cls) && !JavaConstant.MethodType.class.isAssignableFrom(cls)) {
                    throw new IllegalArgumentException("Not a bootstrap argument: " + obj);
                }
            }
            TypeList typeListAsErasures = getParameters().asTypeList().asErasures();
            int i = 4;
            if (typeListAsErasures.size() < 4) {
                return list.isEmpty() || ((TypeDescription) typeListAsErasures.get(typeListAsErasures.size() - 1)).represents(Object[].class);
            }
            Iterator<?> it = list.iterator();
            for (TypeDescription typeDescription : typeListAsErasures.subList(3, typeListAsErasures.size())) {
                boolean z = !it.hasNext();
                if (!z) {
                    Class<?> cls2 = it.next().getClass();
                    z = ((typeDescription.represents(String.class) && cls2 == String.class) || (typeDescription.represents(Integer.TYPE) && cls2 == Integer.class) || ((typeDescription.represents(Long.TYPE) && cls2 == Long.class) || ((typeDescription.represents(Float.TYPE) && cls2 == Float.class) || ((typeDescription.represents(Double.TYPE) && cls2 == Double.class) || ((typeDescription.represents(Class.class) && TypeDescription.class.isAssignableFrom(cls2)) || ((typeDescription.isAssignableFrom(JavaType.METHOD_HANDLE.getTypeStub()) && JavaConstant.MethodHandle.class.isAssignableFrom(cls2)) || (typeDescription.equals(JavaType.METHOD_TYPE.getTypeStub()) && JavaConstant.MethodType.class.isAssignableFrom(cls2)))))))) ? false : true;
                }
                if (z) {
                    return i == typeListAsErasures.size() && typeDescription.represents(Object[].class);
                }
                i++;
            }
            return true;
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public boolean isDefaultValue() {
            return !isConstructor() && !isStatic() && getReturnType().asErasure().isAnnotationReturnType() && getParameters().isEmpty();
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public boolean isDefaultValue(AnnotationValue<?, ?> annotationValue) {
            if (!isDefaultValue()) {
                return false;
            }
            TypeDescription typeDescriptionAsErasure = getReturnType().asErasure();
            Object objResolve = annotationValue.resolve();
            return (typeDescriptionAsErasure.represents(Boolean.TYPE) && (objResolve instanceof Boolean)) || (typeDescriptionAsErasure.represents(Byte.TYPE) && (objResolve instanceof Byte)) || ((typeDescriptionAsErasure.represents(Character.TYPE) && (objResolve instanceof Character)) || ((typeDescriptionAsErasure.represents(Short.TYPE) && (objResolve instanceof Short)) || ((typeDescriptionAsErasure.represents(Integer.TYPE) && (objResolve instanceof Integer)) || ((typeDescriptionAsErasure.represents(Long.TYPE) && (objResolve instanceof Long)) || ((typeDescriptionAsErasure.represents(Float.TYPE) && (objResolve instanceof Float)) || ((typeDescriptionAsErasure.represents(Double.TYPE) && (objResolve instanceof Double)) || ((typeDescriptionAsErasure.represents(String.class) && (objResolve instanceof String)) || ((typeDescriptionAsErasure.isAssignableTo(Enum.class) && (objResolve instanceof EnumerationDescription) && isEnumerationType(typeDescriptionAsErasure, (EnumerationDescription) objResolve)) || ((typeDescriptionAsErasure.isAssignableTo(Annotation.class) && (objResolve instanceof AnnotationDescription) && isAnnotationType(typeDescriptionAsErasure, (AnnotationDescription) objResolve)) || ((typeDescriptionAsErasure.represents(Class.class) && (objResolve instanceof TypeDescription)) || ((typeDescriptionAsErasure.represents(boolean[].class) && (objResolve instanceof boolean[])) || ((typeDescriptionAsErasure.represents(byte[].class) && (objResolve instanceof byte[])) || ((typeDescriptionAsErasure.represents(char[].class) && (objResolve instanceof char[])) || ((typeDescriptionAsErasure.represents(short[].class) && (objResolve instanceof short[])) || ((typeDescriptionAsErasure.represents(int[].class) && (objResolve instanceof int[])) || ((typeDescriptionAsErasure.represents(long[].class) && (objResolve instanceof long[])) || ((typeDescriptionAsErasure.represents(float[].class) && (objResolve instanceof float[])) || ((typeDescriptionAsErasure.represents(double[].class) && (objResolve instanceof double[])) || ((typeDescriptionAsErasure.represents(String[].class) && (objResolve instanceof String[])) || ((typeDescriptionAsErasure.isAssignableTo(Enum[].class) && (objResolve instanceof EnumerationDescription[]) && isEnumerationType(typeDescriptionAsErasure.getComponentType(), (EnumerationDescription[]) objResolve)) || ((typeDescriptionAsErasure.isAssignableTo(Annotation[].class) && (objResolve instanceof AnnotationDescription[]) && isAnnotationType(typeDescriptionAsErasure.getComponentType(), (AnnotationDescription[]) objResolve)) || (typeDescriptionAsErasure.represents(Class[].class) && (objResolve instanceof TypeDescription[])))))))))))))))))))))));
        }

        private static boolean isEnumerationType(TypeDescription typeDescription, EnumerationDescription... enumerationDescriptionArr) {
            for (EnumerationDescription enumerationDescription : enumerationDescriptionArr) {
                if (!enumerationDescription.getEnumerationType().equals(typeDescription)) {
                    return false;
                }
            }
            return true;
        }

        private static boolean isAnnotationType(TypeDescription typeDescription, AnnotationDescription... annotationDescriptionArr) {
            for (AnnotationDescription annotationDescription : annotationDescriptionArr) {
                if (!annotationDescription.getAnnotationType().equals(typeDescription)) {
                    return false;
                }
            }
            return true;
        }

        @Override // net.bytebuddy.description.TypeVariableSource
        public TypeVariableSource getEnclosingSource() {
            return isStatic() ? TypeVariableSource.UNDEFINED : getDeclaringType().asErasure();
        }

        @Override // net.bytebuddy.description.TypeVariableSource
        public <T> T accept(TypeVariableSource.Visitor<T> visitor) {
            return visitor.onMethod(asDefined());
        }

        @Override // net.bytebuddy.description.TypeVariableSource
        public boolean isGenerified() {
            return !getTypeVariables().isEmpty();
        }

        @Override // net.bytebuddy.description.ByteCodeElement.TypeDependant
        public Token asToken(ElementMatcher<? super TypeDescription> elementMatcher) {
            TypeDescription.Generic receiverType = getReceiverType();
            return new Token(getInternalName(), getModifiers(), getTypeVariables().asTokenList(elementMatcher), (TypeDescription.Generic) getReturnType().accept(new TypeDescription.Generic.Visitor.Substitutor.ForDetachment(elementMatcher)), getParameters().asTokenList(elementMatcher), getExceptionTypes().accept(new TypeDescription.Generic.Visitor.Substitutor.ForDetachment(elementMatcher)), getDeclaredAnnotations(), getDefaultValue(), receiverType == null ? TypeDescription.Generic.UNDEFINED : (TypeDescription.Generic) receiverType.accept(new TypeDescription.Generic.Visitor.Substitutor.ForDetachment(elementMatcher)));
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public SignatureToken asSignatureToken() {
            return new SignatureToken(getInternalName(), getReturnType().asErasure(), getParameters().asTypeList().asErasures());
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public TypeToken asTypeToken() {
            return new TypeToken(getReturnType().asErasure(), getParameters().asTypeList().asErasures());
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public boolean isBridgeCompatible(TypeToken typeToken) {
            TypeList typeListAsErasures = getParameters().asTypeList().asErasures();
            List<TypeDescription> parameterTypes = typeToken.getParameterTypes();
            if (typeListAsErasures.size() != parameterTypes.size()) {
                return false;
            }
            for (int i = 0; i < typeListAsErasures.size(); i++) {
                if (!typeListAsErasures.get(i).equals(parameterTypes.get(i)) && (typeListAsErasures.get(i).isPrimitive() || parameterTypes.get(i).isPrimitive())) {
                    return false;
                }
            }
            TypeDescription typeDescriptionAsErasure = getReturnType().asErasure();
            TypeDescription returnType = typeToken.getReturnType();
            return typeDescriptionAsErasure.equals(returnType) || !(typeDescriptionAsErasure.isPrimitive() || returnType.isPrimitive());
        }

        public boolean equals(Object obj) {
            if (obj != this) {
                if (obj instanceof MethodDescription) {
                    MethodDescription methodDescription = (MethodDescription) obj;
                    if (!getInternalName().equals(methodDescription.getInternalName()) || !getDeclaringType().equals(methodDescription.getDeclaringType()) || !getReturnType().asErasure().equals(methodDescription.getReturnType().asErasure()) || !getParameters().asTypeList().asErasures().equals(methodDescription.getParameters().asTypeList().asErasures())) {
                    }
                }
                return false;
            }
            return true;
        }

        public int hashCode() {
            return ((((((getDeclaringType().hashCode() + 17) * 31) + getInternalName().hashCode()) * 31) + getReturnType().asErasure().hashCode()) * 31) + getParameters().asTypeList().asErasures().hashCode();
        }

        @Override // net.bytebuddy.description.NamedElement.WithGenericName
        public String toGenericString() {
            StringBuilder sb = new StringBuilder();
            int modifiers = getModifiers() & 1343;
            if (modifiers != 0) {
                sb.append(Modifier.toString(modifiers)).append(" ");
            }
            if (isMethod()) {
                sb.append(getReturnType().getActualName()).append(" ");
                sb.append(getDeclaringType().asErasure().getActualName()).append(".");
            }
            sb.append(getName()).append("(");
            boolean z = true;
            boolean z2 = true;
            for (TypeDescription.Generic generic : getParameters().asTypeList()) {
                if (z2) {
                    z2 = false;
                } else {
                    sb.append(",");
                }
                sb.append(generic.getActualName());
            }
            sb.append(")");
            TypeList.Generic<TypeDescription.Generic> exceptionTypes = getExceptionTypes();
            if (!exceptionTypes.isEmpty()) {
                sb.append(" throws ");
                for (TypeDescription.Generic generic2 : exceptionTypes) {
                    if (z) {
                        z = false;
                    } else {
                        sb.append(",");
                    }
                    sb.append(generic2.getActualName());
                }
            }
            return sb.toString();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            int modifiers = getModifiers() & 1343;
            if (modifiers != 0) {
                sb.append(Modifier.toString(modifiers)).append(" ");
            }
            if (isMethod()) {
                sb.append(getReturnType().asErasure().getActualName()).append(" ");
                sb.append(getDeclaringType().asErasure().getActualName()).append(".");
            }
            sb.append(getName()).append("(");
            boolean z = true;
            boolean z2 = true;
            for (TypeDescription typeDescription : getParameters().asTypeList().asErasures()) {
                if (z2) {
                    z2 = false;
                } else {
                    sb.append(",");
                }
                sb.append(typeDescription.getActualName());
            }
            sb.append(")");
            TypeList<TypeDescription> typeListAsErasures = getExceptionTypes().asErasures();
            if (!typeListAsErasures.isEmpty()) {
                sb.append(" throws ");
                for (TypeDescription typeDescription2 : typeListAsErasures) {
                    if (z) {
                        z = false;
                    } else {
                        sb.append(",");
                    }
                    sb.append(typeDescription2.getActualName());
                }
            }
            return sb.toString();
        }
    }

    public static class ForLoadedConstructor extends InDefinedShape.AbstractBase {
        private final Constructor<?> constructor;

        @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
        public String getInternalName() {
            return MethodDescription.CONSTRUCTOR_INTERNAL_NAME;
        }

        @Override // net.bytebuddy.description.method.MethodDescription.AbstractBase, net.bytebuddy.description.method.MethodDescription
        public boolean isConstructor() {
            return true;
        }

        @Override // net.bytebuddy.description.method.MethodDescription.AbstractBase, net.bytebuddy.description.method.MethodDescription
        public boolean isTypeInitializer() {
            return false;
        }

        @Override // net.bytebuddy.description.method.MethodDescription.AbstractBase, net.bytebuddy.description.method.MethodDescription
        public boolean represents(Method method) {
            return false;
        }

        public ForLoadedConstructor(Constructor<?> constructor) {
            this.constructor = constructor;
        }

        @Override // net.bytebuddy.description.DeclaredByType
        public TypeDescription getDeclaringType() {
            return new TypeDescription.ForLoadedType(this.constructor.getDeclaringClass());
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public TypeDescription.Generic getReturnType() {
            return TypeDescription.Generic.VOID;
        }

        @Override // net.bytebuddy.description.method.MethodDescription, net.bytebuddy.description.method.MethodDescription.InDefinedShape
        public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
            return ParameterList.ForLoadedExecutable.of(this.constructor);
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public TypeList.Generic getExceptionTypes() {
            return new TypeList.Generic.OfConstructorExceptionTypes(this.constructor);
        }

        @Override // net.bytebuddy.description.method.MethodDescription.AbstractBase, net.bytebuddy.description.method.MethodDescription
        public boolean represents(Constructor<?> constructor) {
            return this.constructor.equals(constructor) || equals(new ForLoadedConstructor(constructor));
        }

        @Override // net.bytebuddy.description.method.MethodDescription.AbstractBase, net.bytebuddy.description.NamedElement.WithRuntimeName
        public String getName() {
            return this.constructor.getName();
        }

        @Override // net.bytebuddy.description.ModifierReviewable
        public int getModifiers() {
            return this.constructor.getModifiers();
        }

        @Override // net.bytebuddy.description.ModifierReviewable.AbstractBase, net.bytebuddy.description.ModifierReviewable
        public boolean isSynthetic() {
            return this.constructor.isSynthetic();
        }

        @Override // net.bytebuddy.description.method.MethodDescription.AbstractBase, net.bytebuddy.description.ByteCodeElement
        public String getDescriptor() {
            return Type.getConstructorDescriptor(this.constructor);
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public AnnotationValue<?, ?> getDefaultValue() {
            return AnnotationValue.UNDEFINED;
        }

        @Override // net.bytebuddy.description.annotation.AnnotationSource
        public AnnotationList getDeclaredAnnotations() {
            return new AnnotationList.ForLoadedAnnotations(this.constructor.getDeclaredAnnotations());
        }

        @Override // net.bytebuddy.description.TypeVariableSource
        public TypeList.Generic getTypeVariables() {
            return TypeList.Generic.ForLoadedTypes.OfTypeVariables.of((GenericDeclaration) this.constructor);
        }

        @Override // net.bytebuddy.description.method.MethodDescription.InDefinedShape.AbstractBase, net.bytebuddy.description.method.MethodDescription
        public TypeDescription.Generic getReceiverType() {
            TypeDescription.Generic genericResolveReceiverType = TypeDescription.Generic.AnnotationReader.DISPATCHER.resolveReceiverType(this.constructor);
            return genericResolveReceiverType == null ? super.getReceiverType() : genericResolveReceiverType;
        }
    }

    public static class ForLoadedMethod extends InDefinedShape.AbstractBase {
        private final Method method;

        @Override // net.bytebuddy.description.method.MethodDescription.AbstractBase, net.bytebuddy.description.method.MethodDescription
        public boolean isConstructor() {
            return false;
        }

        @Override // net.bytebuddy.description.method.MethodDescription.AbstractBase, net.bytebuddy.description.method.MethodDescription
        public boolean isTypeInitializer() {
            return false;
        }

        @Override // net.bytebuddy.description.method.MethodDescription.AbstractBase, net.bytebuddy.description.method.MethodDescription
        public boolean represents(Constructor<?> constructor) {
            return false;
        }

        public ForLoadedMethod(Method method) {
            this.method = method;
        }

        @Override // net.bytebuddy.description.DeclaredByType
        public TypeDescription getDeclaringType() {
            return new TypeDescription.ForLoadedType(this.method.getDeclaringClass());
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public TypeDescription.Generic getReturnType() {
            if (TypeDescription.AbstractBase.RAW_TYPES) {
                return new TypeDescription.Generic.OfNonGenericType.ForLoadedType(this.method.getReturnType());
            }
            return new TypeDescription.Generic.LazyProjection.ForLoadedReturnType(this.method);
        }

        @Override // net.bytebuddy.description.method.MethodDescription, net.bytebuddy.description.method.MethodDescription.InDefinedShape
        public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
            return ParameterList.ForLoadedExecutable.of(this.method);
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public TypeList.Generic getExceptionTypes() {
            if (TypeDescription.AbstractBase.RAW_TYPES) {
                return new TypeList.Generic.ForLoadedTypes(this.method.getExceptionTypes());
            }
            return new TypeList.Generic.OfMethodExceptionTypes(this.method);
        }

        @Override // net.bytebuddy.description.ModifierReviewable.AbstractBase, net.bytebuddy.description.ModifierReviewable.ForMethodDescription
        public boolean isBridge() {
            return this.method.isBridge();
        }

        @Override // net.bytebuddy.description.method.MethodDescription.AbstractBase, net.bytebuddy.description.method.MethodDescription
        public boolean represents(Method method) {
            return this.method.equals(method) || equals(new ForLoadedMethod(method));
        }

        @Override // net.bytebuddy.description.method.MethodDescription.AbstractBase, net.bytebuddy.description.NamedElement.WithRuntimeName
        public String getName() {
            return this.method.getName();
        }

        @Override // net.bytebuddy.description.ModifierReviewable
        public int getModifiers() {
            return this.method.getModifiers();
        }

        @Override // net.bytebuddy.description.ModifierReviewable.AbstractBase, net.bytebuddy.description.ModifierReviewable
        public boolean isSynthetic() {
            return this.method.isSynthetic();
        }

        @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
        public String getInternalName() {
            return this.method.getName();
        }

        @Override // net.bytebuddy.description.method.MethodDescription.AbstractBase, net.bytebuddy.description.ByteCodeElement
        public String getDescriptor() {
            return Type.getMethodDescriptor(this.method);
        }

        public Method getLoadedMethod() {
            return this.method;
        }

        @Override // net.bytebuddy.description.annotation.AnnotationSource
        public AnnotationList getDeclaredAnnotations() {
            return new AnnotationList.ForLoadedAnnotations(this.method.getDeclaredAnnotations());
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public AnnotationValue<?, ?> getDefaultValue() {
            Object defaultValue = this.method.getDefaultValue();
            if (defaultValue == null) {
                return AnnotationValue.UNDEFINED;
            }
            return AnnotationDescription.ForLoadedAnnotation.asValue(defaultValue, this.method.getReturnType());
        }

        @Override // net.bytebuddy.description.TypeVariableSource
        public TypeList.Generic getTypeVariables() {
            if (TypeDescription.AbstractBase.RAW_TYPES) {
                return new TypeList.Generic.Empty();
            }
            return TypeList.Generic.ForLoadedTypes.OfTypeVariables.of((GenericDeclaration) this.method);
        }

        @Override // net.bytebuddy.description.method.MethodDescription.InDefinedShape.AbstractBase, net.bytebuddy.description.method.MethodDescription
        public TypeDescription.Generic getReceiverType() {
            if (TypeDescription.AbstractBase.RAW_TYPES) {
                return super.getReceiverType();
            }
            TypeDescription.Generic genericResolveReceiverType = TypeDescription.Generic.AnnotationReader.DISPATCHER.resolveReceiverType(this.method);
            return genericResolveReceiverType == null ? super.getReceiverType() : genericResolveReceiverType;
        }
    }

    public static class Latent extends InDefinedShape.AbstractBase {
        private final List<? extends AnnotationDescription> declaredAnnotations;
        private final TypeDescription declaringType;
        private final AnnotationValue<?, ?> defaultValue;
        private final List<? extends TypeDescription.Generic> exceptionTypes;
        private final String internalName;
        private final int modifiers;
        private final List<? extends ParameterDescription.Token> parameterTokens;
        private final TypeDescription.Generic receiverType;
        private final TypeDescription.Generic returnType;
        private final List<? extends TypeVariableToken> typeVariables;

        public Latent(TypeDescription typeDescription, Token token) {
            this(typeDescription, token.getName(), token.getModifiers(), token.getTypeVariableTokens(), token.getReturnType(), token.getParameterTokens(), token.getExceptionTypes(), token.getAnnotations(), token.getDefaultValue(), token.getReceiverType());
        }

        public Latent(TypeDescription typeDescription, String str, int i, List<? extends TypeVariableToken> list, TypeDescription.Generic generic, List<? extends ParameterDescription.Token> list2, List<? extends TypeDescription.Generic> list3, List<? extends AnnotationDescription> list4, AnnotationValue<?, ?> annotationValue, TypeDescription.Generic generic2) {
            this.declaringType = typeDescription;
            this.internalName = str;
            this.modifiers = i;
            this.typeVariables = list;
            this.returnType = generic;
            this.parameterTokens = list2;
            this.exceptionTypes = list3;
            this.declaredAnnotations = list4;
            this.defaultValue = annotationValue;
            this.receiverType = generic2;
        }

        @Override // net.bytebuddy.description.TypeVariableSource
        public TypeList.Generic getTypeVariables() {
            return TypeList.Generic.ForDetachedTypes.attachVariables(this, this.typeVariables);
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public TypeDescription.Generic getReturnType() {
            return (TypeDescription.Generic) this.returnType.accept(TypeDescription.Generic.Visitor.Substitutor.ForAttachment.of(this));
        }

        @Override // net.bytebuddy.description.method.MethodDescription, net.bytebuddy.description.method.MethodDescription.InDefinedShape
        public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
            return new ParameterList.ForTokens(this, this.parameterTokens);
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public TypeList.Generic getExceptionTypes() {
            return TypeList.Generic.ForDetachedTypes.attach(this, this.exceptionTypes);
        }

        @Override // net.bytebuddy.description.annotation.AnnotationSource
        public AnnotationList getDeclaredAnnotations() {
            return new AnnotationList.Explicit(this.declaredAnnotations);
        }

        @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
        public String getInternalName() {
            return this.internalName;
        }

        @Override // net.bytebuddy.description.DeclaredByType
        public TypeDescription getDeclaringType() {
            return this.declaringType;
        }

        @Override // net.bytebuddy.description.ModifierReviewable
        public int getModifiers() {
            return this.modifiers;
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public AnnotationValue<?, ?> getDefaultValue() {
            return this.defaultValue;
        }

        @Override // net.bytebuddy.description.method.MethodDescription.InDefinedShape.AbstractBase, net.bytebuddy.description.method.MethodDescription
        public TypeDescription.Generic getReceiverType() {
            TypeDescription.Generic generic = this.receiverType;
            if (generic == null) {
                return super.getReceiverType();
            }
            return (TypeDescription.Generic) generic.accept(TypeDescription.Generic.Visitor.Substitutor.ForAttachment.of(this));
        }

        public static class TypeInitializer extends InDefinedShape.AbstractBase {
            private final TypeDescription typeDescription;

            @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
            public String getInternalName() {
                return MethodDescription.TYPE_INITIALIZER_INTERNAL_NAME;
            }

            @Override // net.bytebuddy.description.ModifierReviewable
            public int getModifiers() {
                return 8;
            }

            public TypeInitializer(TypeDescription typeDescription) {
                this.typeDescription = typeDescription;
            }

            @Override // net.bytebuddy.description.method.MethodDescription
            public TypeDescription.Generic getReturnType() {
                return TypeDescription.Generic.VOID;
            }

            @Override // net.bytebuddy.description.method.MethodDescription, net.bytebuddy.description.method.MethodDescription.InDefinedShape
            public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
                return new ParameterList.Empty();
            }

            @Override // net.bytebuddy.description.method.MethodDescription
            public TypeList.Generic getExceptionTypes() {
                return new TypeList.Generic.Empty();
            }

            @Override // net.bytebuddy.description.method.MethodDescription
            public AnnotationValue<?, ?> getDefaultValue() {
                return AnnotationValue.UNDEFINED;
            }

            @Override // net.bytebuddy.description.TypeVariableSource
            public TypeList.Generic getTypeVariables() {
                return new TypeList.Generic.Empty();
            }

            @Override // net.bytebuddy.description.annotation.AnnotationSource
            public AnnotationList getDeclaredAnnotations() {
                return new AnnotationList.Empty();
            }

            @Override // net.bytebuddy.description.DeclaredByType
            public TypeDescription getDeclaringType() {
                return this.typeDescription;
            }
        }
    }

    public static class TypeSubstituting extends AbstractBase implements InGenericShape {
        private final TypeDescription.Generic declaringType;
        private final MethodDescription methodDescription;
        private final TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor;

        public TypeSubstituting(TypeDescription.Generic generic, MethodDescription methodDescription, TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor) {
            this.declaringType = generic;
            this.methodDescription = methodDescription;
            this.visitor = visitor;
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public TypeDescription.Generic getReturnType() {
            return (TypeDescription.Generic) this.methodDescription.getReturnType().accept(this.visitor);
        }

        @Override // net.bytebuddy.description.TypeVariableSource
        public TypeList.Generic getTypeVariables() {
            return this.methodDescription.getTypeVariables().accept(this.visitor).filter(ElementMatchers.ofSort(TypeDefinition.Sort.VARIABLE));
        }

        @Override // net.bytebuddy.description.method.MethodDescription, net.bytebuddy.description.method.MethodDescription.InDefinedShape
        public ParameterList<ParameterDescription.InGenericShape> getParameters() {
            return new ParameterList.TypeSubstituting(this, this.methodDescription.getParameters(), this.visitor);
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public TypeList.Generic getExceptionTypes() {
            return new TypeList.Generic.ForDetachedTypes(this.methodDescription.getExceptionTypes(), this.visitor);
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public AnnotationValue<?, ?> getDefaultValue() {
            return this.methodDescription.getDefaultValue();
        }

        @Override // net.bytebuddy.description.method.MethodDescription
        public TypeDescription.Generic getReceiverType() {
            TypeDescription.Generic receiverType = this.methodDescription.getReceiverType();
            if (receiverType == null) {
                return TypeDescription.Generic.UNDEFINED;
            }
            return (TypeDescription.Generic) receiverType.accept(this.visitor);
        }

        @Override // net.bytebuddy.description.annotation.AnnotationSource
        public AnnotationList getDeclaredAnnotations() {
            return this.methodDescription.getDeclaredAnnotations();
        }

        @Override // net.bytebuddy.description.DeclaredByType
        public TypeDescription.Generic getDeclaringType() {
            return this.declaringType;
        }

        @Override // net.bytebuddy.description.ModifierReviewable
        public int getModifiers() {
            return this.methodDescription.getModifiers();
        }

        @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
        public String getInternalName() {
            return this.methodDescription.getInternalName();
        }

        @Override // net.bytebuddy.description.ByteCodeElement.TypeDependant
        public InDefinedShape asDefined() {
            return this.methodDescription.asDefined();
        }
    }

    public static class Token implements ByteCodeElement.Token<Token> {
        private final List<? extends AnnotationDescription> annotations;
        private final AnnotationValue<?, ?> defaultValue;
        private final List<? extends TypeDescription.Generic> exceptionTypes;
        private final int modifiers;
        private final String name;
        private final List<? extends ParameterDescription.Token> parameterTokens;
        private final TypeDescription.Generic receiverType;
        private final TypeDescription.Generic returnType;
        private final List<? extends TypeVariableToken> typeVariableTokens;

        @Override // net.bytebuddy.description.ByteCodeElement.Token
        public /* bridge */ /* synthetic */ ByteCodeElement.Token accept(TypeDescription.Generic.Visitor visitor) {
            return accept((TypeDescription.Generic.Visitor<? extends TypeDescription.Generic>) visitor);
        }

        public Token(int i) {
            this(MethodDescription.CONSTRUCTOR_INTERNAL_NAME, i, TypeDescription.Generic.VOID);
        }

        public Token(String str, int i, TypeDescription.Generic generic) {
            this(str, i, generic, Collections.emptyList());
        }

        public Token(String str, int i, TypeDescription.Generic generic, List<? extends TypeDescription.Generic> list) {
            this(str, i, Collections.emptyList(), generic, new ParameterDescription.Token.TypeList(list), Collections.emptyList(), Collections.emptyList(), AnnotationValue.UNDEFINED, TypeDescription.Generic.UNDEFINED);
        }

        public Token(String str, int i, List<? extends TypeVariableToken> list, TypeDescription.Generic generic, List<? extends ParameterDescription.Token> list2, List<? extends TypeDescription.Generic> list3, List<? extends AnnotationDescription> list4, AnnotationValue<?, ?> annotationValue, TypeDescription.Generic generic2) {
            this.name = str;
            this.modifiers = i;
            this.typeVariableTokens = list;
            this.returnType = generic;
            this.parameterTokens = list2;
            this.exceptionTypes = list3;
            this.annotations = list4;
            this.defaultValue = annotationValue;
            this.receiverType = generic2;
        }

        public String getName() {
            return this.name;
        }

        public int getModifiers() {
            return this.modifiers;
        }

        public ByteCodeElement.Token.TokenList<TypeVariableToken> getTypeVariableTokens() {
            return new ByteCodeElement.Token.TokenList<>(this.typeVariableTokens);
        }

        public TypeDescription.Generic getReturnType() {
            return this.returnType;
        }

        public ByteCodeElement.Token.TokenList<ParameterDescription.Token> getParameterTokens() {
            return new ByteCodeElement.Token.TokenList<>(this.parameterTokens);
        }

        public TypeList.Generic getExceptionTypes() {
            return new TypeList.Generic.Explicit(this.exceptionTypes);
        }

        public AnnotationList getAnnotations() {
            return new AnnotationList.Explicit(this.annotations);
        }

        public AnnotationValue<?, ?> getDefaultValue() {
            return this.defaultValue;
        }

        public TypeDescription.Generic getReceiverType() {
            return this.receiverType;
        }

        @Override // net.bytebuddy.description.ByteCodeElement.Token
        public Token accept(TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor) {
            String str = this.name;
            int i = this.modifiers;
            List listAccept = getTypeVariableTokens().accept(visitor);
            TypeDescription.Generic generic = (TypeDescription.Generic) this.returnType.accept(visitor);
            List listAccept2 = getParameterTokens().accept(visitor);
            TypeList.Generic genericAccept = getExceptionTypes().accept(visitor);
            List<? extends AnnotationDescription> list = this.annotations;
            AnnotationValue<?, ?> annotationValue = this.defaultValue;
            TypeDescription.Generic generic2 = this.receiverType;
            return new Token(str, i, listAccept, generic, listAccept2, genericAccept, list, annotationValue, generic2 == null ? TypeDescription.Generic.UNDEFINED : (TypeDescription.Generic) generic2.accept(visitor));
        }

        public SignatureToken asSignatureToken(TypeDescription typeDescription) {
            TypeDescription.Generic.Visitor.Reducing reducing = new TypeDescription.Generic.Visitor.Reducing(typeDescription, this.typeVariableTokens);
            ArrayList arrayList = new ArrayList(this.parameterTokens.size());
            Iterator<? extends ParameterDescription.Token> it = this.parameterTokens.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getType().accept(reducing));
            }
            return new SignatureToken(this.name, (TypeDescription) this.returnType.accept(reducing), arrayList);
        }

        public boolean equals(Object obj) {
            AnnotationValue<?, ?> annotationValue;
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Token token = (Token) obj;
            if (this.modifiers == token.modifiers && this.name.equals(token.name) && this.typeVariableTokens.equals(token.typeVariableTokens) && this.returnType.equals(token.returnType) && this.parameterTokens.equals(token.parameterTokens) && this.exceptionTypes.equals(token.exceptionTypes) && this.annotations.equals(token.annotations) && ((annotationValue = this.defaultValue) == null ? token.defaultValue == null : annotationValue.equals(token.defaultValue))) {
                TypeDescription.Generic generic = this.receiverType;
                if (generic != null) {
                    if (generic.equals(token.receiverType)) {
                        return true;
                    }
                } else if (token.receiverType == null) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            int iHashCode = ((((((((((((this.name.hashCode() * 31) + this.modifiers) * 31) + this.typeVariableTokens.hashCode()) * 31) + this.returnType.hashCode()) * 31) + this.parameterTokens.hashCode()) * 31) + this.exceptionTypes.hashCode()) * 31) + this.annotations.hashCode()) * 31;
            AnnotationValue<?, ?> annotationValue = this.defaultValue;
            int iHashCode2 = (iHashCode + (annotationValue != null ? annotationValue.hashCode() : 0)) * 31;
            TypeDescription.Generic generic = this.receiverType;
            return iHashCode2 + (generic != null ? generic.hashCode() : 0);
        }

        public String toString() {
            return "MethodDescription.Token{name='" + this.name + "', modifiers=" + this.modifiers + ", typeVariableTokens=" + this.typeVariableTokens + ", returnType=" + this.returnType + ", parameterTokens=" + this.parameterTokens + ", exceptionTypes=" + this.exceptionTypes + ", annotations=" + this.annotations + ", defaultValue=" + this.defaultValue + ", receiverType=" + this.receiverType + '}';
        }
    }

    public static class SignatureToken {
        private final String name;
        private final List<? extends TypeDescription> parameterTypes;
        private final TypeDescription returnType;

        public SignatureToken(String str, TypeDescription typeDescription, List<? extends TypeDescription> list) {
            this.name = str;
            this.returnType = typeDescription;
            this.parameterTypes = list;
        }

        public String getName() {
            return this.name;
        }

        public TypeDescription getReturnType() {
            return this.returnType;
        }

        public List<TypeDescription> getParameterTypes() {
            return new ArrayList(this.parameterTypes);
        }

        public TypeToken asTypeToken() {
            return new TypeToken(this.returnType, this.parameterTypes);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SignatureToken)) {
                return false;
            }
            SignatureToken signatureToken = (SignatureToken) obj;
            return this.name.equals(signatureToken.name) && this.returnType.equals(signatureToken.returnType) && this.parameterTypes.equals(signatureToken.parameterTypes);
        }

        public int hashCode() {
            return (((this.name.hashCode() * 31) + this.returnType.hashCode()) * 31) + this.parameterTypes.hashCode();
        }

        public String toString() {
            StringBuilder sbAppend = new StringBuilder().append(this.returnType).append(' ').append(this.name).append('(');
            boolean z = true;
            for (TypeDescription typeDescription : this.parameterTypes) {
                if (z) {
                    z = false;
                } else {
                    sbAppend.append(",");
                }
                sbAppend.append(typeDescription);
            }
            return sbAppend.append(')').toString();
        }
    }

    public static class TypeToken {
        private final List<? extends TypeDescription> parameterTypes;
        private final TypeDescription returnType;

        public TypeToken(TypeDescription typeDescription, List<? extends TypeDescription> list) {
            this.returnType = typeDescription;
            this.parameterTypes = list;
        }

        public TypeDescription getReturnType() {
            return this.returnType;
        }

        public List<TypeDescription> getParameterTypes() {
            return new ArrayList(this.parameterTypes);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TypeToken)) {
                return false;
            }
            TypeToken typeToken = (TypeToken) obj;
            return this.returnType.equals(typeToken.returnType) && this.parameterTypes.equals(typeToken.parameterTypes);
        }

        public int hashCode() {
            return (this.returnType.hashCode() * 31) + this.parameterTypes.hashCode();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("(");
            Iterator<? extends TypeDescription> it = this.parameterTypes.iterator();
            while (it.hasNext()) {
                sb.append(it.next().getDescriptor());
            }
            return sb.append(')').append(this.returnType.getDescriptor()).toString();
        }
    }
}
