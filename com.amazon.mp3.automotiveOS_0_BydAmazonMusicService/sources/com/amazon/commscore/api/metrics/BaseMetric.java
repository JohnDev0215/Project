package com.amazon.commscore.api.metrics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.commscore.api.metrics.BaseMetric;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes2.dex */
public class BaseMetric<T extends BaseMetric<?>> {
    private final String component;
    private Map<String, Object> customEntries;
    private final String name;
    private final String ownerIdentifier;
    private final String subComponent;

    BaseMetric(@NonNull String str, @Nullable String str2, @Nullable String str3, @NonNull String str4) {
        this.name = str;
        this.component = str2;
        this.subComponent = str3;
        this.customEntries = new HashMap();
        this.ownerIdentifier = str4;
    }

    BaseMetric(@NonNull String str, @Nullable String str2, @Nullable String str3) {
        this(str, str2, str3, "41c6c2ed-e8be-474b-96c3-4c6c1681b2f2");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public T setCustomEntries(@Nullable Map<String, Object> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        this.customEntries = map;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public String getComponent() {
        return this.component;
    }

    public String getSubComponent() {
        return this.subComponent;
    }

    public String getOwnerIdentifier() {
        return this.ownerIdentifier;
    }
}
