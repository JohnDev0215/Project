package net.bytebuddy.dynamic.scaffold;

import java.util.Iterator;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

/* JADX INFO: loaded from: classes3.dex */
public interface FieldLocator {

    public interface Factory {
        FieldLocator make(TypeDescription typeDescription);
    }

    Resolution locate(String str);

    Resolution locate(String str, TypeDescription typeDescription);

    public interface Resolution {
        FieldDescription getField();

        boolean isResolved();

        public enum Illegal implements Resolution {
            INSTANCE;

            @Override // net.bytebuddy.dynamic.scaffold.FieldLocator.Resolution
            public boolean isResolved() {
                return false;
            }

            @Override // net.bytebuddy.dynamic.scaffold.FieldLocator.Resolution
            public FieldDescription getField() {
                throw new IllegalStateException("Could not locate field");
            }
        }

        public static class Simple implements Resolution {
            private final FieldDescription fieldDescription;

            @Override // net.bytebuddy.dynamic.scaffold.FieldLocator.Resolution
            public boolean isResolved() {
                return true;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof Simple;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Simple)) {
                    return false;
                }
                Simple simple = (Simple) obj;
                if (!simple.canEqual(this)) {
                    return false;
                }
                FieldDescription fieldDescription = this.fieldDescription;
                FieldDescription fieldDescription2 = simple.fieldDescription;
                return fieldDescription != null ? fieldDescription.equals(fieldDescription2) : fieldDescription2 == null;
            }

            public int hashCode() {
                FieldDescription fieldDescription = this.fieldDescription;
                return 59 + (fieldDescription == null ? 43 : fieldDescription.hashCode());
            }

            protected Simple(FieldDescription fieldDescription) {
                this.fieldDescription = fieldDescription;
            }

            @Override // net.bytebuddy.dynamic.scaffold.FieldLocator.Resolution
            public FieldDescription getField() {
                return this.fieldDescription;
            }
        }
    }

    public enum NoOp implements FieldLocator, Factory {
        INSTANCE;

        @Override // net.bytebuddy.dynamic.scaffold.FieldLocator.Factory
        public FieldLocator make(TypeDescription typeDescription) {
            return this;
        }

        @Override // net.bytebuddy.dynamic.scaffold.FieldLocator
        public Resolution locate(String str) {
            return Resolution.Illegal.INSTANCE;
        }

        @Override // net.bytebuddy.dynamic.scaffold.FieldLocator
        public Resolution locate(String str, TypeDescription typeDescription) {
            return Resolution.Illegal.INSTANCE;
        }
    }

    public static abstract class AbstractBase implements FieldLocator {
        protected final TypeDescription accessingType;

        protected abstract FieldList<?> locate(ElementMatcher<? super FieldDescription> elementMatcher);

        protected boolean canEqual(Object obj) {
            return obj instanceof AbstractBase;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AbstractBase)) {
                return false;
            }
            AbstractBase abstractBase = (AbstractBase) obj;
            if (!abstractBase.canEqual(this)) {
                return false;
            }
            TypeDescription typeDescription = this.accessingType;
            TypeDescription typeDescription2 = abstractBase.accessingType;
            return typeDescription != null ? typeDescription.equals(typeDescription2) : typeDescription2 == null;
        }

        public int hashCode() {
            TypeDescription typeDescription = this.accessingType;
            return 59 + (typeDescription == null ? 43 : typeDescription.hashCode());
        }

        protected AbstractBase(TypeDescription typeDescription) {
            this.accessingType = typeDescription;
        }

        @Override // net.bytebuddy.dynamic.scaffold.FieldLocator
        public Resolution locate(String str) {
            FieldList<?> fieldListLocate = locate(ElementMatchers.named(str).and(ElementMatchers.isVisibleTo(this.accessingType)));
            return fieldListLocate.size() == 1 ? new Resolution.Simple((FieldDescription) fieldListLocate.getOnly()) : Resolution.Illegal.INSTANCE;
        }

        @Override // net.bytebuddy.dynamic.scaffold.FieldLocator
        public Resolution locate(String str, TypeDescription typeDescription) {
            FieldList<?> fieldListLocate = locate(ElementMatchers.named(str).and(ElementMatchers.fieldType(typeDescription)).and(ElementMatchers.isVisibleTo(this.accessingType)));
            return fieldListLocate.size() == 1 ? new Resolution.Simple((FieldDescription) fieldListLocate.getOnly()) : Resolution.Illegal.INSTANCE;
        }
    }

    public static class ForExactType extends AbstractBase {
        private final TypeDescription typeDescription;

        @Override // net.bytebuddy.dynamic.scaffold.FieldLocator.AbstractBase
        protected boolean canEqual(Object obj) {
            return obj instanceof ForExactType;
        }

        @Override // net.bytebuddy.dynamic.scaffold.FieldLocator.AbstractBase
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ForExactType)) {
                return false;
            }
            ForExactType forExactType = (ForExactType) obj;
            if (!forExactType.canEqual(this) || !super.equals(obj)) {
                return false;
            }
            TypeDescription typeDescription = this.typeDescription;
            TypeDescription typeDescription2 = forExactType.typeDescription;
            return typeDescription != null ? typeDescription.equals(typeDescription2) : typeDescription2 == null;
        }

        @Override // net.bytebuddy.dynamic.scaffold.FieldLocator.AbstractBase
        public int hashCode() {
            int iHashCode = super.hashCode() + 59;
            TypeDescription typeDescription = this.typeDescription;
            return (iHashCode * 59) + (typeDescription == null ? 43 : typeDescription.hashCode());
        }

        public ForExactType(TypeDescription typeDescription) {
            this(typeDescription, typeDescription);
        }

        public ForExactType(TypeDescription typeDescription, TypeDescription typeDescription2) {
            super(typeDescription2);
            this.typeDescription = typeDescription;
        }

        @Override // net.bytebuddy.dynamic.scaffold.FieldLocator.AbstractBase
        protected FieldList<?> locate(ElementMatcher<? super FieldDescription> elementMatcher) {
            return (FieldList) this.typeDescription.getDeclaredFields().filter(elementMatcher);
        }

        public static class Factory implements Factory {
            private final TypeDescription typeDescription;

            protected boolean canEqual(Object obj) {
                return obj instanceof Factory;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Factory)) {
                    return false;
                }
                Factory factory = (Factory) obj;
                if (!factory.canEqual(this)) {
                    return false;
                }
                TypeDescription typeDescription = this.typeDescription;
                TypeDescription typeDescription2 = factory.typeDescription;
                return typeDescription != null ? typeDescription.equals(typeDescription2) : typeDescription2 == null;
            }

            public int hashCode() {
                TypeDescription typeDescription = this.typeDescription;
                return 59 + (typeDescription == null ? 43 : typeDescription.hashCode());
            }

            public Factory(TypeDescription typeDescription) {
                this.typeDescription = typeDescription;
            }

            @Override // net.bytebuddy.dynamic.scaffold.FieldLocator.Factory
            public FieldLocator make(TypeDescription typeDescription) {
                return new ForExactType(this.typeDescription, typeDescription);
            }
        }
    }

    public static class ForClassHierarchy extends AbstractBase {
        private final TypeDescription typeDescription;

        @Override // net.bytebuddy.dynamic.scaffold.FieldLocator.AbstractBase
        protected boolean canEqual(Object obj) {
            return obj instanceof ForClassHierarchy;
        }

        @Override // net.bytebuddy.dynamic.scaffold.FieldLocator.AbstractBase
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ForClassHierarchy)) {
                return false;
            }
            ForClassHierarchy forClassHierarchy = (ForClassHierarchy) obj;
            if (!forClassHierarchy.canEqual(this) || !super.equals(obj)) {
                return false;
            }
            TypeDescription typeDescription = this.typeDescription;
            TypeDescription typeDescription2 = forClassHierarchy.typeDescription;
            return typeDescription != null ? typeDescription.equals(typeDescription2) : typeDescription2 == null;
        }

        @Override // net.bytebuddy.dynamic.scaffold.FieldLocator.AbstractBase
        public int hashCode() {
            int iHashCode = super.hashCode() + 59;
            TypeDescription typeDescription = this.typeDescription;
            return (iHashCode * 59) + (typeDescription == null ? 43 : typeDescription.hashCode());
        }

        public ForClassHierarchy(TypeDescription typeDescription) {
            this(typeDescription, typeDescription);
        }

        public ForClassHierarchy(TypeDescription typeDescription, TypeDescription typeDescription2) {
            super(typeDescription2);
            this.typeDescription = typeDescription;
        }

        @Override // net.bytebuddy.dynamic.scaffold.FieldLocator.AbstractBase
        protected FieldList<?> locate(ElementMatcher<? super FieldDescription> elementMatcher) {
            Iterator it = this.typeDescription.iterator();
            while (it.hasNext()) {
                FieldList<?> fieldList = (FieldList) ((TypeDefinition) it.next()).getDeclaredFields().filter(elementMatcher);
                if (!fieldList.isEmpty()) {
                    return fieldList;
                }
            }
            return new FieldList.Empty();
        }

        public enum Factory implements Factory {
            INSTANCE;

            @Override // net.bytebuddy.dynamic.scaffold.FieldLocator.Factory
            public FieldLocator make(TypeDescription typeDescription) {
                return new ForClassHierarchy(typeDescription);
            }
        }
    }

    public static class ForTopLevelType extends AbstractBase {
        protected ForTopLevelType(TypeDescription typeDescription) {
            super(typeDescription);
        }

        @Override // net.bytebuddy.dynamic.scaffold.FieldLocator.AbstractBase
        protected FieldList<?> locate(ElementMatcher<? super FieldDescription> elementMatcher) {
            return (FieldList) this.accessingType.getDeclaredFields().filter(elementMatcher);
        }

        public enum Factory implements Factory {
            INSTANCE;

            @Override // net.bytebuddy.dynamic.scaffold.FieldLocator.Factory
            public FieldLocator make(TypeDescription typeDescription) {
                return new ForTopLevelType(typeDescription);
            }
        }
    }
}
