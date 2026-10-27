package net.bytebuddy.description.field;

import java.lang.reflect.Field;
import java.lang.reflect.GenericSignatureFormatError;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;
import net.bytebuddy.description.ByteCodeElement;
import net.bytebuddy.description.ModifierReviewable;
import net.bytebuddy.description.NamedElement;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.jar.asm.signature.SignatureVisitor;
import net.bytebuddy.jar.asm.signature.SignatureWriter;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public interface FieldDescription extends ByteCodeElement, ModifierReviewable.ForFieldDescription, NamedElement.WithGenericName, ByteCodeElement.TypeDependant<InDefinedShape, Token> {
    public static final Object NO_DEFAULT_VALUE = null;

    public interface InDefinedShape extends FieldDescription {

        public static abstract class AbstractBase extends AbstractBase implements InDefinedShape {
            @Override // net.bytebuddy.description.ByteCodeElement.TypeDependant
            public InDefinedShape asDefined() {
                return this;
            }
        }

        @Override // net.bytebuddy.description.DeclaredByType
        TypeDescription getDeclaringType();
    }

    public interface InGenericShape extends FieldDescription {
        @Override // net.bytebuddy.description.DeclaredByType
        TypeDescription.Generic getDeclaringType();
    }

    SignatureToken asSignatureToken();

    int getActualModifiers();

    TypeDescription.Generic getType();

    public static abstract class AbstractBase extends ModifierReviewable.AbstractBase implements FieldDescription {
        @Override // net.bytebuddy.description.ByteCodeElement.TypeDependant
        public /* bridge */ /* synthetic */ ByteCodeElement.Token asToken(ElementMatcher elementMatcher) {
            return asToken((ElementMatcher<? super TypeDescription>) elementMatcher);
        }

        @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
        public String getInternalName() {
            return getName();
        }

        @Override // net.bytebuddy.description.NamedElement
        public String getActualName() {
            return getName();
        }

        @Override // net.bytebuddy.description.ByteCodeElement
        public String getDescriptor() {
            return getType().asErasure().getDescriptor();
        }

        @Override // net.bytebuddy.description.ByteCodeElement
        public String getGenericSignature() {
            TypeDescription.Generic type = getType();
            try {
                return type.getSort().isNonGeneric() ? NON_GENERIC_SIGNATURE : ((SignatureVisitor) type.accept(new TypeDescription.Generic.Visitor.ForSignatureVisitor(new SignatureWriter()))).toString();
            } catch (GenericSignatureFormatError unused) {
                return NON_GENERIC_SIGNATURE;
            }
        }

        @Override // net.bytebuddy.description.ByteCodeElement
        public boolean isVisibleTo(TypeDescription typeDescription) {
            return getDeclaringType().asErasure().isVisibleTo(typeDescription) && (isPublic() || typeDescription.equals(getDeclaringType().asErasure()) || ((isProtected() && getDeclaringType().asErasure().isAssignableFrom(typeDescription)) || (!isPrivate() && typeDescription.isSamePackage(getDeclaringType().asErasure()))));
        }

        @Override // net.bytebuddy.description.ByteCodeElement
        public boolean isAccessibleTo(TypeDescription typeDescription) {
            return isPublic() || typeDescription.equals(getDeclaringType().asErasure()) || (!isPrivate() && typeDescription.isSamePackage(getDeclaringType().asErasure()));
        }

        @Override // net.bytebuddy.description.field.FieldDescription
        public int getActualModifiers() {
            return getModifiers() | (getDeclaredAnnotations().isAnnotationPresent(Deprecated.class) ? 131072 : 0);
        }

        @Override // net.bytebuddy.description.ByteCodeElement.TypeDependant
        public Token asToken(ElementMatcher<? super TypeDescription> elementMatcher) {
            return new Token(getName(), getModifiers(), (TypeDescription.Generic) getType().accept(new TypeDescription.Generic.Visitor.Substitutor.ForDetachment(elementMatcher)), getDeclaredAnnotations());
        }

        @Override // net.bytebuddy.description.field.FieldDescription
        public SignatureToken asSignatureToken() {
            return new SignatureToken(getInternalName(), getType().asErasure());
        }

        public boolean equals(Object obj) {
            if (obj != this) {
                if (obj instanceof FieldDescription) {
                    FieldDescription fieldDescription = (FieldDescription) obj;
                    if (!getName().equals(fieldDescription.getName()) || !getDeclaringType().equals(fieldDescription.getDeclaringType())) {
                    }
                }
                return false;
            }
            return true;
        }

        public int hashCode() {
            return getDeclaringType().hashCode() + ((getName().hashCode() + 17) * 31);
        }

        @Override // net.bytebuddy.description.NamedElement.WithGenericName
        public String toGenericString() {
            StringBuilder sb = new StringBuilder();
            if (getModifiers() != 0) {
                sb.append(Modifier.toString(getModifiers())).append(" ");
            }
            sb.append(getType().getActualName()).append(" ");
            sb.append(getDeclaringType().asErasure().getActualName()).append(".");
            return sb.append(getName()).toString();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (getModifiers() != 0) {
                sb.append(Modifier.toString(getModifiers())).append(" ");
            }
            sb.append(getType().asErasure().getActualName()).append(" ");
            sb.append(getDeclaringType().asErasure().getActualName()).append(".");
            return sb.append(getName()).toString();
        }
    }

    public static class ForLoadedField extends InDefinedShape.AbstractBase {
        private final Field field;

        public ForLoadedField(Field field) {
            this.field = field;
        }

        @Override // net.bytebuddy.description.field.FieldDescription
        public TypeDescription.Generic getType() {
            if (TypeDescription.AbstractBase.RAW_TYPES) {
                return new TypeDescription.Generic.OfNonGenericType.ForLoadedType(this.field.getType());
            }
            return new TypeDescription.Generic.LazyProjection.ForLoadedFieldType(this.field);
        }

        @Override // net.bytebuddy.description.annotation.AnnotationSource
        public AnnotationList getDeclaredAnnotations() {
            return new AnnotationList.ForLoadedAnnotations(this.field.getDeclaredAnnotations());
        }

        @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
        public String getName() {
            return this.field.getName();
        }

        @Override // net.bytebuddy.description.DeclaredByType
        public TypeDescription getDeclaringType() {
            return new TypeDescription.ForLoadedType(this.field.getDeclaringClass());
        }

        @Override // net.bytebuddy.description.ModifierReviewable
        public int getModifiers() {
            return this.field.getModifiers();
        }

        @Override // net.bytebuddy.description.ModifierReviewable.AbstractBase, net.bytebuddy.description.ModifierReviewable
        public boolean isSynthetic() {
            return this.field.isSynthetic();
        }
    }

    public static class Latent extends InDefinedShape.AbstractBase {
        private final List<? extends AnnotationDescription> declaredAnnotations;
        private final TypeDescription declaringType;
        private final String fieldName;
        private final TypeDescription.Generic fieldType;
        private final int modifiers;

        public Latent(TypeDescription typeDescription, Token token) {
            this(typeDescription, token.getName(), token.getModifiers(), token.getType(), token.getAnnotations());
        }

        public Latent(TypeDescription typeDescription, String str, int i, TypeDescription.Generic generic, List<? extends AnnotationDescription> list) {
            this.declaringType = typeDescription;
            this.fieldName = str;
            this.modifiers = i;
            this.fieldType = generic;
            this.declaredAnnotations = list;
        }

        @Override // net.bytebuddy.description.field.FieldDescription
        public TypeDescription.Generic getType() {
            return (TypeDescription.Generic) this.fieldType.accept(TypeDescription.Generic.Visitor.Substitutor.ForAttachment.of(this));
        }

        @Override // net.bytebuddy.description.annotation.AnnotationSource
        public AnnotationList getDeclaredAnnotations() {
            return new AnnotationList.Explicit(this.declaredAnnotations);
        }

        @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
        public String getName() {
            return this.fieldName;
        }

        @Override // net.bytebuddy.description.DeclaredByType
        public TypeDescription getDeclaringType() {
            return this.declaringType;
        }

        @Override // net.bytebuddy.description.ModifierReviewable
        public int getModifiers() {
            return this.modifiers;
        }
    }

    public static class TypeSubstituting extends AbstractBase implements InGenericShape {
        private final TypeDescription.Generic declaringType;
        private final FieldDescription fieldDescription;
        private final TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor;

        public TypeSubstituting(TypeDescription.Generic generic, FieldDescription fieldDescription, TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor) {
            this.declaringType = generic;
            this.fieldDescription = fieldDescription;
            this.visitor = visitor;
        }

        @Override // net.bytebuddy.description.field.FieldDescription
        public TypeDescription.Generic getType() {
            return (TypeDescription.Generic) this.fieldDescription.getType().accept(this.visitor);
        }

        @Override // net.bytebuddy.description.annotation.AnnotationSource
        public AnnotationList getDeclaredAnnotations() {
            return this.fieldDescription.getDeclaredAnnotations();
        }

        @Override // net.bytebuddy.description.DeclaredByType
        public TypeDescription.Generic getDeclaringType() {
            return this.declaringType;
        }

        @Override // net.bytebuddy.description.ModifierReviewable
        public int getModifiers() {
            return this.fieldDescription.getModifiers();
        }

        @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
        public String getName() {
            return this.fieldDescription.getName();
        }

        @Override // net.bytebuddy.description.ByteCodeElement.TypeDependant
        public InDefinedShape asDefined() {
            return this.fieldDescription.asDefined();
        }
    }

    public static class Token implements ByteCodeElement.Token<Token> {
        private final List<? extends AnnotationDescription> annotations;
        private final int modifiers;
        private final String name;
        private final TypeDescription.Generic type;

        @Override // net.bytebuddy.description.ByteCodeElement.Token
        public /* bridge */ /* synthetic */ ByteCodeElement.Token accept(TypeDescription.Generic.Visitor visitor) {
            return accept((TypeDescription.Generic.Visitor<? extends TypeDescription.Generic>) visitor);
        }

        public Token(String str, int i, TypeDescription.Generic generic) {
            this(str, i, generic, Collections.emptyList());
        }

        public Token(String str, int i, TypeDescription.Generic generic, List<? extends AnnotationDescription> list) {
            this.name = str;
            this.modifiers = i;
            this.type = generic;
            this.annotations = list;
        }

        public String getName() {
            return this.name;
        }

        public TypeDescription.Generic getType() {
            return this.type;
        }

        public int getModifiers() {
            return this.modifiers;
        }

        public AnnotationList getAnnotations() {
            return new AnnotationList.Explicit(this.annotations);
        }

        @Override // net.bytebuddy.description.ByteCodeElement.Token
        public Token accept(TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor) {
            return new Token(this.name, this.modifiers, (TypeDescription.Generic) this.type.accept(visitor), this.annotations);
        }

        public SignatureToken asSignatureToken(TypeDescription typeDescription) {
            return new SignatureToken(this.name, (TypeDescription) this.type.accept(new TypeDescription.Generic.Visitor.Reducing(typeDescription)));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Token token = (Token) obj;
            return this.modifiers == token.modifiers && this.name.equals(token.name) && this.type.equals(token.type) && this.annotations.equals(token.annotations);
        }

        public int hashCode() {
            return (((((this.name.hashCode() * 31) + this.modifiers) * 31) + this.type.hashCode()) * 31) + this.annotations.hashCode();
        }
    }

    public static class SignatureToken {
        private final String name;
        private final TypeDescription type;

        public SignatureToken(String str, TypeDescription typeDescription) {
            this.name = str;
            this.type = typeDescription;
        }

        public String getName() {
            return this.name;
        }

        public TypeDescription getType() {
            return this.type;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SignatureToken)) {
                return false;
            }
            SignatureToken signatureToken = (SignatureToken) obj;
            return this.name.equals(signatureToken.name) && this.type.equals(signatureToken.type);
        }

        public int hashCode() {
            return (this.name.hashCode() * 31) + this.type.hashCode();
        }

        public String toString() {
            return this.type + " " + this.name;
        }
    }
}
