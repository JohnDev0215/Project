package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListThingTypesResult implements Serializable {
    private String nextToken;
    private List<ThingTypeDefinition> thingTypes;

    public List<ThingTypeDefinition> getThingTypes() {
        return this.thingTypes;
    }

    public void setThingTypes(Collection<ThingTypeDefinition> collection) {
        if (collection == null) {
            this.thingTypes = null;
        } else {
            this.thingTypes = new ArrayList(collection);
        }
    }

    public ListThingTypesResult withThingTypes(ThingTypeDefinition... thingTypeDefinitionArr) {
        if (getThingTypes() == null) {
            this.thingTypes = new ArrayList(thingTypeDefinitionArr.length);
        }
        for (ThingTypeDefinition thingTypeDefinition : thingTypeDefinitionArr) {
            this.thingTypes.add(thingTypeDefinition);
        }
        return this;
    }

    public ListThingTypesResult withThingTypes(Collection<ThingTypeDefinition> collection) {
        setThingTypes(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListThingTypesResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingTypes() != null) {
            sb.append("thingTypes: " + getThingTypes() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getThingTypes() == null ? 0 : getThingTypes().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListThingTypesResult)) {
            return false;
        }
        ListThingTypesResult listThingTypesResult = (ListThingTypesResult) obj;
        if ((listThingTypesResult.getThingTypes() == null) ^ (getThingTypes() == null)) {
            return false;
        }
        if (listThingTypesResult.getThingTypes() != null && !listThingTypesResult.getThingTypes().equals(getThingTypes())) {
            return false;
        }
        if ((listThingTypesResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listThingTypesResult.getNextToken() == null || listThingTypesResult.getNextToken().equals(getNextToken());
    }
}
