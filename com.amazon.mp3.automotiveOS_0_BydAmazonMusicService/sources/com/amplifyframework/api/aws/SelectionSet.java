package com.amplifyframework.api.aws;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.graphql.Operation;
import com.amplifyframework.api.graphql.QueryType;
import com.amplifyframework.core.model.AuthRule;
import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.CustomTypeField;
import com.amplifyframework.core.model.CustomTypeSchema;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelAssociation;
import com.amplifyframework.core.model.ModelField;
import com.amplifyframework.core.model.ModelSchema;
import com.amplifyframework.core.model.SchemaRegistry;
import com.amplifyframework.core.model.SerializedModel;
import com.amplifyframework.core.model.types.JavaFieldType;
import com.amplifyframework.util.Empty;
import com.amplifyframework.util.FieldFinder;
import com.amplifyframework.util.Wrap;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public final class SelectionSet {
    private static final String INDENT = "  ";
    private final Set<SelectionSet> nodes;
    private final String value;

    public SelectionSet(SelectionSet selectionSet) {
        this(selectionSet.value, new HashSet(selectionSet.nodes));
    }

    public SelectionSet(String str) {
        this(str, Collections.emptySet());
    }

    public SelectionSet(String str, @NonNull Set<SelectionSet> set) {
        this.value = str;
        Objects.requireNonNull(set);
        this.nodes = set;
    }

    @NonNull
    public Set<SelectionSet> getNodes() {
        return this.nodes;
    }

    public String toString() {
        return toString("");
    }

    public String toString(String str) {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        String str2 = this.value;
        if (str2 != null) {
            sb.append(str2);
        }
        if (!Empty.check(this.nodes)) {
            Iterator<SelectionSet> it = this.nodes.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().toString(str + INDENT));
            }
            Collections.sort(arrayList);
            sb.append(Wrap.inPrettyBraces(TextUtils.join("\n" + str + INDENT, arrayList), str, INDENT));
        }
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SelectionSet.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(this.value, ((SelectionSet) obj).value);
    }

    public int hashCode() {
        return ObjectsCompat.hash(this.value);
    }

    public static Builder builder() {
        return new Builder();
    }

    static final class Builder {
        private Class<? extends Model> modelClass;
        private ModelSchema modelSchema;
        private Operation operation;
        private GraphQLRequestOptions requestOptions;

        Builder() {
        }

        public Builder modelClass(@NonNull Class<? extends Model> cls) {
            Objects.requireNonNull(cls);
            this.modelClass = cls;
            return this;
        }

        public Builder modelSchema(@NonNull ModelSchema modelSchema) {
            Objects.requireNonNull(modelSchema);
            this.modelSchema = modelSchema;
            return this;
        }

        public Builder operation(@NonNull Operation operation) {
            Objects.requireNonNull(operation);
            this.operation = operation;
            return this;
        }

        public Builder requestOptions(@NonNull GraphQLRequestOptions graphQLRequestOptions) {
            Objects.requireNonNull(graphQLRequestOptions);
            this.requestOptions = graphQLRequestOptions;
            return this;
        }

        public SelectionSet build() throws AmplifyException {
            Set<SelectionSet> modelFields;
            if (this.modelClass == null && this.modelSchema == null) {
                throw new AmplifyException("Both modelClass and modelSchema cannot be null", "Provide either a modelClass or a modelSchema to build the selection set");
            }
            Objects.requireNonNull(this.operation);
            Class<? extends Model> cls = this.modelClass;
            if (SerializedModel.class == cls) {
                modelFields = getModelFields(this.modelSchema, this.requestOptions.maxDepth(), this.operation);
            } else {
                modelFields = getModelFields(cls, this.requestOptions.maxDepth(), this.operation);
            }
            SelectionSet selectionSet = new SelectionSet(null, modelFields);
            return (QueryType.LIST.equals(this.operation) || QueryType.SYNC.equals(this.operation)) ? wrapPagination(selectionSet) : selectionSet;
        }

        private SelectionSet wrapPagination(SelectionSet selectionSet) {
            return new SelectionSet(null, wrapPagination(selectionSet.getNodes()));
        }

        private Set<SelectionSet> wrapPagination(Set<SelectionSet> set) {
            HashSet hashSet = new HashSet();
            hashSet.add(new SelectionSet(this.requestOptions.listField(), set));
            Iterator<String> it = this.requestOptions.paginationFields().iterator();
            while (it.hasNext()) {
                hashSet.add(new SelectionSet(it.next()));
            }
            return hashSet;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private Set<SelectionSet> getModelFields(Class<? extends Model> cls, int i, Operation operation) throws AmplifyException {
            if (i < 0) {
                return new HashSet();
            }
            HashSet hashSet = new HashSet();
            ModelSchema modelSchemaFromModelClass = ModelSchema.fromModelClass(cls);
            if (i == 0 && LeafSerializationBehavior.JUST_ID.equals(this.requestOptions.leafSerializationBehavior()) && operation != QueryType.SYNC) {
                Iterator<String> it = modelSchemaFromModelClass.getPrimaryIndexFields().iterator();
                while (it.hasNext()) {
                    hashSet.add(new SelectionSet(it.next()));
                }
                return hashSet;
            }
            for (Field field : FieldFinder.findModelFieldsIn(cls)) {
                String name = field.getName();
                if (modelSchemaFromModelClass.getAssociations().containsKey(name)) {
                    if (List.class.isAssignableFrom(field.getType())) {
                        if (i >= 1) {
                            hashSet.add(new SelectionSet(name, wrapPagination(getModelFields((Class<? extends Model>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0], i - 1, operation))));
                        }
                    } else if (i >= 1) {
                        hashSet.add(new SelectionSet(name, getModelFields((Class<? extends Model>) field.getType(), i - 1, operation)));
                    }
                } else if (isCustomType(field)) {
                    hashSet.add(new SelectionSet(name, getNestedCustomTypeFields(getClassForField(field))));
                } else {
                    hashSet.add(new SelectionSet(name));
                }
                Iterator<AuthRule> it2 = modelSchemaFromModelClass.getAuthRules().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        AuthRule next = it2.next();
                        if (AuthStrategy.OWNER.equals(next.getAuthStrategy())) {
                            hashSet.add(new SelectionSet(next.getOwnerFieldOrDefault()));
                            break;
                        }
                    }
                }
            }
            Iterator<String> it3 = this.requestOptions.modelMetaFields().iterator();
            while (it3.hasNext()) {
                hashSet.add(new SelectionSet(it3.next()));
            }
            return hashSet;
        }

        private Set<SelectionSet> getNestedCustomTypeFields(Class<?> cls) {
            HashSet hashSet = new HashSet();
            for (Field field : FieldFinder.findNonTransientFieldsIn(cls)) {
                String name = field.getName();
                if (isCustomType(field)) {
                    hashSet.add(new SelectionSet(name, getNestedCustomTypeFields(getClassForField(field))));
                } else {
                    hashSet.add(new SelectionSet(name));
                }
            }
            return hashSet;
        }

        private static boolean isCustomType(@NonNull Field field) {
            Class<?> classForField = getClassForField(field);
            if (Model.class.isAssignableFrom(classForField) || Enum.class.isAssignableFrom(classForField)) {
                return false;
            }
            try {
                JavaFieldType.from(classForField);
                return false;
            } catch (IllegalArgumentException unused) {
                return true;
            }
        }

        static Class<?> getClassForField(Field field) {
            if (Collection.class.isAssignableFrom(field.getType())) {
                return (Class) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
            }
            return field.getType();
        }

        private Set<SelectionSet> getModelFields(ModelSchema modelSchema, int i, Operation operation) {
            Set<SelectionSet> modelFields;
            if (i < 0) {
                return new HashSet();
            }
            HashSet hashSet = new HashSet();
            if (i == 0 && LeafSerializationBehavior.JUST_ID.equals(this.requestOptions.leafSerializationBehavior()) && operation != QueryType.SYNC) {
                ListIterator<String> listIterator = modelSchema.getPrimaryIndexFields().listIterator();
                if (listIterator.hasNext()) {
                    hashSet.add(new SelectionSet(listIterator.next()));
                }
            }
            SchemaRegistry schemaRegistryInstance = SchemaRegistry.instance();
            for (Map.Entry<String, ModelField> entry : modelSchema.getFields().entrySet()) {
                String key = entry.getKey();
                ModelAssociation modelAssociation = modelSchema.getAssociations().get(key);
                if (modelAssociation != null) {
                    if (i >= 1) {
                        ModelSchema modelSchemaForModelClass = schemaRegistryInstance.getModelSchemaForModelClass(modelAssociation.getAssociatedType());
                        if (entry.getValue().isArray()) {
                            modelFields = wrapPagination(getModelFields(modelSchemaForModelClass, i - 1, operation));
                        } else {
                            modelFields = getModelFields(modelSchemaForModelClass, i - 1, operation);
                        }
                        hashSet.add(new SelectionSet(key, modelFields));
                    }
                } else if (entry.getValue().isCustomType()) {
                    hashSet.add(new SelectionSet(key, getCustomTypeFields(schemaRegistryInstance.getCustomTypeSchemaForCustomTypeClass(entry.getValue().getTargetType()))));
                } else {
                    hashSet.add(new SelectionSet(key));
                }
                Iterator<AuthRule> it = modelSchema.getAuthRules().iterator();
                while (true) {
                    if (it.hasNext()) {
                        AuthRule next = it.next();
                        if (AuthStrategy.OWNER.equals(next.getAuthStrategy())) {
                            hashSet.add(new SelectionSet(next.getOwnerFieldOrDefault()));
                            break;
                        }
                    }
                }
            }
            Iterator<String> it2 = this.requestOptions.modelMetaFields().iterator();
            while (it2.hasNext()) {
                hashSet.add(new SelectionSet(it2.next()));
            }
            return hashSet;
        }

        private Set<SelectionSet> getCustomTypeFields(@NonNull CustomTypeSchema customTypeSchema) {
            SchemaRegistry schemaRegistryInstance = SchemaRegistry.instance();
            HashSet hashSet = new HashSet();
            for (Map.Entry<String, CustomTypeField> entry : customTypeSchema.getFields().entrySet()) {
                String key = entry.getKey();
                if (entry.getValue().isCustomType()) {
                    hashSet.add(new SelectionSet(key, getCustomTypeFields(schemaRegistryInstance.getCustomTypeSchemaForCustomTypeClass(entry.getValue().getTargetType()))));
                } else {
                    hashSet.add(new SelectionSet(key));
                }
            }
            return hashSet;
        }
    }
}
