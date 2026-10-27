package com.amplifyframework.datastore.syncengine;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import com.amplifyframework.core.model.ModelAssociation;
import com.amplifyframework.core.model.ModelProvider;
import com.amplifyframework.core.model.ModelSchema;
import com.amplifyframework.core.model.SchemaRegistry;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;

/* JADX INFO: loaded from: classes5.dex */
final class TopologicalOrdering {
    private final List<ModelSchema> modelSchema;

    interface Factory {
        TopologicalOrdering create();
    }

    private TopologicalOrdering(List<ModelSchema> list) {
        this.modelSchema = list;
    }

    @SuppressLint({"SyntheticAccessor"})
    static TopologicalOrdering forRegisteredModels(@NonNull SchemaRegistry schemaRegistry, @NonNull ModelProvider modelProvider) {
        Objects.requireNonNull(modelProvider);
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = modelProvider.modelNames().iterator();
        while (it.hasNext()) {
            arrayList.add(schemaRegistry.getModelSchemaForModelClass(it.next()));
        }
        return new TopologicalOrdering(new TopologicalSort(arrayList).result());
    }

    int compare(@NonNull ModelSchema modelSchema, @NonNull ModelSchema modelSchema2) {
        Objects.requireNonNull(modelSchema);
        Objects.requireNonNull(modelSchema2);
        int i = -1;
        int i2 = -1;
        for (int i3 = 0; i3 < this.modelSchema.size(); i3++) {
            if (this.modelSchema.get(i3).equals(modelSchema)) {
                i = i3;
            }
            if (this.modelSchema.get(i3).equals(modelSchema2)) {
                i2 = i3;
            }
        }
        return i - i2;
    }

    @NonNull
    @SuppressLint({"SyntheticAccessor"})
    OrderingCheck check(@NonNull ModelSchema modelSchema) {
        Objects.requireNonNull(modelSchema);
        if (!this.modelSchema.contains(modelSchema)) {
            throw new NoSuchElementException("No model schema matching " + modelSchema.getName());
        }
        return new OrderingCheck(modelSchema);
    }

    private static final class TopologicalSort {
        private final List<ModelSchema> input;
        private final Stack<ModelSchema> result;
        private final Set<ModelSchema> unvisited;

        private TopologicalSort(List<ModelSchema> list) {
            this.input = list;
            this.unvisited = new HashSet(list);
            this.result = new Stack<>();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public List<ModelSchema> result() {
            while (!this.unvisited.isEmpty()) {
                visit(this.unvisited.iterator().next());
            }
            return this.result;
        }

        private void visit(ModelSchema modelSchema) {
            this.unvisited.remove(modelSchema);
            Iterator<ModelSchema> it = findUnvisitedAssociationOwners(modelSchema).iterator();
            while (it.hasNext()) {
                visit(it.next());
            }
            this.result.push(modelSchema);
        }

        private Set<ModelSchema> findUnvisitedAssociationOwners(ModelSchema modelSchema) {
            HashSet hashSet = new HashSet();
            for (ModelSchema modelSchema2 : findAssociationOwners(modelSchema)) {
                if (this.unvisited.contains(modelSchema2)) {
                    hashSet.add(modelSchema2);
                }
            }
            return hashSet;
        }

        private Set<ModelSchema> findAssociationOwners(ModelSchema modelSchema) {
            HashSet hashSet = new HashSet();
            for (ModelAssociation modelAssociation : modelSchema.getAssociations().values()) {
                if (modelAssociation.isOwner()) {
                    hashSet.add(findInputSchemaByName(modelAssociation.getAssociatedType()));
                }
            }
            return hashSet;
        }

        private ModelSchema findInputSchemaByName(String str) {
            for (ModelSchema modelSchema : this.input) {
                if (modelSchema.getName().equals(str)) {
                    return modelSchema;
                }
            }
            throw new NoSuchElementException("No model schema provided with name = " + str);
        }
    }

    final class OrderingCheck {
        private final ModelSchema modelSchema;

        private OrderingCheck(@NonNull ModelSchema modelSchema) {
            Objects.requireNonNull(modelSchema);
            this.modelSchema = modelSchema;
        }

        boolean isAfter(@NonNull ModelSchema modelSchema) {
            Objects.requireNonNull(modelSchema);
            return TopologicalOrdering.this.compare(modelSchema, this.modelSchema) < 0;
        }

        boolean isBefore(@NonNull ModelSchema modelSchema) {
            Objects.requireNonNull(modelSchema);
            return TopologicalOrdering.this.compare(modelSchema, this.modelSchema) > 0;
        }
    }
}
