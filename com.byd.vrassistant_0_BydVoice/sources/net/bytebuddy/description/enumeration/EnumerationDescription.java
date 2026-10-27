package net.bytebuddy.description.enumeration;

import java.util.ArrayList;
import java.util.List;
import net.bytebuddy.description.NamedElement;
import net.bytebuddy.description.type.TypeDescription;

/* JADX INFO: loaded from: classes3.dex */
public interface EnumerationDescription extends NamedElement {
    TypeDescription getEnumerationType();

    String getValue();

    <T extends Enum<T>> T load(Class<T> cls);

    public static abstract class AbstractBase implements EnumerationDescription {
        @Override // net.bytebuddy.description.NamedElement
        public String getActualName() {
            return getValue();
        }

        public boolean equals(Object obj) {
            if (obj != this) {
                if (obj instanceof EnumerationDescription) {
                    EnumerationDescription enumerationDescription = (EnumerationDescription) obj;
                    if (!enumerationDescription.getEnumerationType().equals(getEnumerationType()) || !enumerationDescription.getValue().equals(getValue())) {
                    }
                }
                return false;
            }
            return true;
        }

        public int hashCode() {
            return getValue().hashCode() + (getEnumerationType().hashCode() * 31);
        }

        public String toString() {
            return getValue();
        }
    }

    public static class ForLoadedEnumeration extends AbstractBase {
        private final Enum<?> value;

        public ForLoadedEnumeration(Enum<?> r1) {
            this.value = r1;
        }

        public static List<EnumerationDescription> asList(Enum<?>[] enumArr) {
            ArrayList arrayList = new ArrayList(enumArr.length);
            for (Enum<?> r0 : enumArr) {
                arrayList.add(new ForLoadedEnumeration(r0));
            }
            return arrayList;
        }

        @Override // net.bytebuddy.description.enumeration.EnumerationDescription
        public String getValue() {
            return this.value.name();
        }

        @Override // net.bytebuddy.description.enumeration.EnumerationDescription
        public TypeDescription getEnumerationType() {
            return new TypeDescription.ForLoadedType(this.value.getDeclaringClass());
        }

        @Override // net.bytebuddy.description.enumeration.EnumerationDescription
        public <T extends Enum<T>> T load(Class<T> cls) {
            return this.value.getDeclaringClass() == cls ? (T) this.value : (T) Enum.valueOf(cls, this.value.name());
        }
    }

    public static class Latent extends AbstractBase {
        private final TypeDescription enumerationType;
        private final String value;

        public Latent(TypeDescription typeDescription, String str) {
            this.enumerationType = typeDescription;
            this.value = str;
        }

        @Override // net.bytebuddy.description.enumeration.EnumerationDescription
        public String getValue() {
            return this.value;
        }

        @Override // net.bytebuddy.description.enumeration.EnumerationDescription
        public TypeDescription getEnumerationType() {
            return this.enumerationType;
        }

        @Override // net.bytebuddy.description.enumeration.EnumerationDescription
        public <T extends Enum<T>> T load(Class<T> cls) {
            if (!this.enumerationType.represents(cls)) {
                throw new IllegalArgumentException(cls + " does not represent " + this.enumerationType);
            }
            return (T) Enum.valueOf(cls, this.value);
        }
    }
}
