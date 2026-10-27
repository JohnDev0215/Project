package net.bytebuddy.dynamic.scaffold;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.Transformer;
import net.bytebuddy.dynamic.scaffold.TypeWriter;
import net.bytebuddy.implementation.attribute.FieldAttributeAppender;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.LatentMatcher;

/* JADX INFO: loaded from: classes3.dex */
public interface FieldRegistry {
    Compiled compile(TypeDescription typeDescription);

    FieldRegistry prepend(LatentMatcher<? super FieldDescription> latentMatcher, FieldAttributeAppender.Factory factory, Object obj, Transformer<FieldDescription> transformer);

    public interface Compiled extends TypeWriter.FieldPool {

        public enum NoOp implements Compiled {
            INSTANCE;

            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.FieldPool
            public TypeWriter.FieldPool.Record target(FieldDescription fieldDescription) {
                return new TypeWriter.FieldPool.Record.ForImplicitField(fieldDescription);
            }
        }
    }

    public static class Default implements FieldRegistry {
        private final List<Entry> entries;

        protected boolean canEqual(Object obj) {
            return obj instanceof Default;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Default)) {
                return false;
            }
            Default r4 = (Default) obj;
            if (!r4.canEqual(this)) {
                return false;
            }
            List<Entry> list = this.entries;
            List<Entry> list2 = r4.entries;
            return list != null ? list.equals(list2) : list2 == null;
        }

        public int hashCode() {
            List<Entry> list = this.entries;
            return 59 + (list == null ? 43 : list.hashCode());
        }

        public Default() {
            this(Collections.emptyList());
        }

        private Default(List<Entry> list) {
            this.entries = list;
        }

        @Override // net.bytebuddy.dynamic.scaffold.FieldRegistry
        public FieldRegistry prepend(LatentMatcher<? super FieldDescription> latentMatcher, FieldAttributeAppender.Factory factory, Object obj, Transformer<FieldDescription> transformer) {
            ArrayList arrayList = new ArrayList(this.entries.size() + 1);
            arrayList.add(new Entry(latentMatcher, factory, obj, transformer));
            arrayList.addAll(this.entries);
            return new Default(arrayList);
        }

        @Override // net.bytebuddy.dynamic.scaffold.FieldRegistry
        public Compiled compile(TypeDescription typeDescription) {
            ArrayList arrayList = new ArrayList(this.entries.size());
            HashMap map = new HashMap();
            for (Entry entry : this.entries) {
                FieldAttributeAppender fieldAttributeAppenderMake = (FieldAttributeAppender) map.get(entry.getFieldAttributeAppenderFactory());
                if (fieldAttributeAppenderMake == null) {
                    fieldAttributeAppenderMake = entry.getFieldAttributeAppenderFactory().make(typeDescription);
                    map.put(entry.getFieldAttributeAppenderFactory(), fieldAttributeAppenderMake);
                }
                arrayList.add(new Compiled.Entry(entry.resolve(typeDescription), fieldAttributeAppenderMake, entry.getDefaultValue(), entry.getTransformer()));
            }
            return new Compiled(typeDescription, arrayList);
        }

        protected static class Entry implements LatentMatcher<FieldDescription> {
            private final Object defaultValue;
            private final FieldAttributeAppender.Factory fieldAttributeAppenderFactory;
            private final LatentMatcher<? super FieldDescription> matcher;
            private final Transformer<FieldDescription> transformer;

            protected boolean canEqual(Object obj) {
                return obj instanceof Entry;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Entry)) {
                    return false;
                }
                Entry entry = (Entry) obj;
                if (!entry.canEqual(this)) {
                    return false;
                }
                LatentMatcher<? super FieldDescription> latentMatcher = this.matcher;
                LatentMatcher<? super FieldDescription> latentMatcher2 = entry.matcher;
                if (latentMatcher != null ? !latentMatcher.equals(latentMatcher2) : latentMatcher2 != null) {
                    return false;
                }
                FieldAttributeAppender.Factory fieldAttributeAppenderFactory = getFieldAttributeAppenderFactory();
                FieldAttributeAppender.Factory fieldAttributeAppenderFactory2 = entry.getFieldAttributeAppenderFactory();
                if (fieldAttributeAppenderFactory != null ? !fieldAttributeAppenderFactory.equals(fieldAttributeAppenderFactory2) : fieldAttributeAppenderFactory2 != null) {
                    return false;
                }
                Object defaultValue = getDefaultValue();
                Object defaultValue2 = entry.getDefaultValue();
                if (defaultValue != null ? !defaultValue.equals(defaultValue2) : defaultValue2 != null) {
                    return false;
                }
                Transformer<FieldDescription> transformer = getTransformer();
                Transformer<FieldDescription> transformer2 = entry.getTransformer();
                return transformer != null ? transformer.equals(transformer2) : transformer2 == null;
            }

            public int hashCode() {
                LatentMatcher<? super FieldDescription> latentMatcher = this.matcher;
                int iHashCode = latentMatcher == null ? 43 : latentMatcher.hashCode();
                FieldAttributeAppender.Factory fieldAttributeAppenderFactory = getFieldAttributeAppenderFactory();
                int iHashCode2 = ((iHashCode + 59) * 59) + (fieldAttributeAppenderFactory == null ? 43 : fieldAttributeAppenderFactory.hashCode());
                Object defaultValue = getDefaultValue();
                int iHashCode3 = (iHashCode2 * 59) + (defaultValue == null ? 43 : defaultValue.hashCode());
                Transformer<FieldDescription> transformer = getTransformer();
                return (iHashCode3 * 59) + (transformer != null ? transformer.hashCode() : 43);
            }

            protected Entry(LatentMatcher<? super FieldDescription> latentMatcher, FieldAttributeAppender.Factory factory, Object obj, Transformer<FieldDescription> transformer) {
                this.matcher = latentMatcher;
                this.fieldAttributeAppenderFactory = factory;
                this.defaultValue = obj;
                this.transformer = transformer;
            }

            protected FieldAttributeAppender.Factory getFieldAttributeAppenderFactory() {
                return this.fieldAttributeAppenderFactory;
            }

            protected Object getDefaultValue() {
                return this.defaultValue;
            }

            protected Transformer<FieldDescription> getTransformer() {
                return this.transformer;
            }

            @Override // net.bytebuddy.matcher.LatentMatcher
            public ElementMatcher<? super FieldDescription> resolve(TypeDescription typeDescription) {
                return this.matcher.resolve(typeDescription);
            }
        }

        protected static class Compiled implements Compiled {
            private final List<Entry> entries;
            private final TypeDescription instrumentedType;

            protected boolean canEqual(Object obj) {
                return obj instanceof Compiled;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Compiled)) {
                    return false;
                }
                Compiled compiled = (Compiled) obj;
                if (!compiled.canEqual(this)) {
                    return false;
                }
                TypeDescription typeDescription = this.instrumentedType;
                TypeDescription typeDescription2 = compiled.instrumentedType;
                if (typeDescription != null ? !typeDescription.equals(typeDescription2) : typeDescription2 != null) {
                    return false;
                }
                List<Entry> list = this.entries;
                List<Entry> list2 = compiled.entries;
                return list != null ? list.equals(list2) : list2 == null;
            }

            public int hashCode() {
                TypeDescription typeDescription = this.instrumentedType;
                int iHashCode = typeDescription == null ? 43 : typeDescription.hashCode();
                List<Entry> list = this.entries;
                return ((iHashCode + 59) * 59) + (list != null ? list.hashCode() : 43);
            }

            protected Compiled(TypeDescription typeDescription, List<Entry> list) {
                this.instrumentedType = typeDescription;
                this.entries = list;
            }

            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.FieldPool
            public TypeWriter.FieldPool.Record target(FieldDescription fieldDescription) {
                for (Entry entry : this.entries) {
                    if (entry.matches(fieldDescription)) {
                        return entry.bind(this.instrumentedType, fieldDescription);
                    }
                }
                return new TypeWriter.FieldPool.Record.ForImplicitField(fieldDescription);
            }

            protected static class Entry implements ElementMatcher<FieldDescription> {
                private final Object defaultValue;
                private final FieldAttributeAppender fieldAttributeAppender;
                private final ElementMatcher<? super FieldDescription> matcher;
                private final Transformer<FieldDescription> transformer;

                protected boolean canEqual(Object obj) {
                    return obj instanceof Entry;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Entry)) {
                        return false;
                    }
                    Entry entry = (Entry) obj;
                    if (!entry.canEqual(this)) {
                        return false;
                    }
                    ElementMatcher<? super FieldDescription> elementMatcher = this.matcher;
                    ElementMatcher<? super FieldDescription> elementMatcher2 = entry.matcher;
                    if (elementMatcher != null ? !elementMatcher.equals(elementMatcher2) : elementMatcher2 != null) {
                        return false;
                    }
                    FieldAttributeAppender fieldAttributeAppender = this.fieldAttributeAppender;
                    FieldAttributeAppender fieldAttributeAppender2 = entry.fieldAttributeAppender;
                    if (fieldAttributeAppender != null ? !fieldAttributeAppender.equals(fieldAttributeAppender2) : fieldAttributeAppender2 != null) {
                        return false;
                    }
                    Object obj2 = this.defaultValue;
                    Object obj3 = entry.defaultValue;
                    if (obj2 != null ? !obj2.equals(obj3) : obj3 != null) {
                        return false;
                    }
                    Transformer<FieldDescription> transformer = this.transformer;
                    Transformer<FieldDescription> transformer2 = entry.transformer;
                    return transformer != null ? transformer.equals(transformer2) : transformer2 == null;
                }

                public int hashCode() {
                    ElementMatcher<? super FieldDescription> elementMatcher = this.matcher;
                    int iHashCode = elementMatcher == null ? 43 : elementMatcher.hashCode();
                    FieldAttributeAppender fieldAttributeAppender = this.fieldAttributeAppender;
                    int iHashCode2 = ((iHashCode + 59) * 59) + (fieldAttributeAppender == null ? 43 : fieldAttributeAppender.hashCode());
                    Object obj = this.defaultValue;
                    int iHashCode3 = (iHashCode2 * 59) + (obj == null ? 43 : obj.hashCode());
                    Transformer<FieldDescription> transformer = this.transformer;
                    return (iHashCode3 * 59) + (transformer != null ? transformer.hashCode() : 43);
                }

                protected Entry(ElementMatcher<? super FieldDescription> elementMatcher, FieldAttributeAppender fieldAttributeAppender, Object obj, Transformer<FieldDescription> transformer) {
                    this.matcher = elementMatcher;
                    this.fieldAttributeAppender = fieldAttributeAppender;
                    this.defaultValue = obj;
                    this.transformer = transformer;
                }

                protected TypeWriter.FieldPool.Record bind(TypeDescription typeDescription, FieldDescription fieldDescription) {
                    return new TypeWriter.FieldPool.Record.ForExplicitField(this.fieldAttributeAppender, this.defaultValue, this.transformer.transform(typeDescription, fieldDescription));
                }

                @Override // net.bytebuddy.matcher.ElementMatcher
                public boolean matches(FieldDescription fieldDescription) {
                    return this.matcher.matches(fieldDescription);
                }
            }
        }
    }
}
