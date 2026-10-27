package com.android.textclassifier;

import androidx.collection.ArrayMap;
import com.google.android.textclassifier.AnnotatorModel;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
final class EntityConfidence {
    private final ArrayMap<String, Float> entityConfidence = new ArrayMap<>();
    private final ArrayList<String> sortedEntities = new ArrayList<>();

    static {
        new EntityConfidence(Collections.emptyMap());
    }

    EntityConfidence(Map<String, Float> map) {
        Preconditions.checkNotNull(map);
        this.entityConfidence.ensureCapacity(map.size());
        for (Map.Entry<String, Float> entry : map.entrySet()) {
            if (entry.getValue().floatValue() > AnnotatorModel.INVALID_LOCATION_ACCURACY_METERS) {
                this.entityConfidence.put(entry.getKey(), Float.valueOf(Math.min(1.0f, entry.getValue().floatValue())));
            }
        }
        resetSortedEntitiesFromMap();
    }

    public List<String> getEntities() {
        return Collections.unmodifiableList(this.sortedEntities);
    }

    public String toString() {
        return this.entityConfidence.toString();
    }

    private void resetSortedEntitiesFromMap() {
        this.sortedEntities.clear();
        this.sortedEntities.ensureCapacity(this.entityConfidence.size());
        this.sortedEntities.addAll(this.entityConfidence.keySet());
        this.sortedEntities.sort(new Comparator() { // from class: com.android.textclassifier.-$$Lambda$EntityConfidence$hoZFXqIZjQVxadsNS4Jf5wV-hgo
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return this.f$0.lambda$resetSortedEntitiesFromMap$0$EntityConfidence((String) obj, (String) obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$resetSortedEntitiesFromMap$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ int lambda$resetSortedEntitiesFromMap$0$EntityConfidence(String str, String str2) {
        return Float.compare(this.entityConfidence.get(str2).floatValue(), this.entityConfidence.get(str).floatValue());
    }
}
