package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListThingsResult implements Serializable {
    private String nextToken;
    private List<ThingAttribute> things;

    public List<ThingAttribute> getThings() {
        return this.things;
    }

    public void setThings(Collection<ThingAttribute> collection) {
        if (collection == null) {
            this.things = null;
        } else {
            this.things = new ArrayList(collection);
        }
    }

    public ListThingsResult withThings(ThingAttribute... thingAttributeArr) {
        if (getThings() == null) {
            this.things = new ArrayList(thingAttributeArr.length);
        }
        for (ThingAttribute thingAttribute : thingAttributeArr) {
            this.things.add(thingAttribute);
        }
        return this;
    }

    public ListThingsResult withThings(Collection<ThingAttribute> collection) {
        setThings(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListThingsResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThings() != null) {
            sb.append("things: " + getThings() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getThings() == null ? 0 : getThings().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListThingsResult)) {
            return false;
        }
        ListThingsResult listThingsResult = (ListThingsResult) obj;
        if ((listThingsResult.getThings() == null) ^ (getThings() == null)) {
            return false;
        }
        if (listThingsResult.getThings() != null && !listThingsResult.getThings().equals(getThings())) {
            return false;
        }
        if ((listThingsResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listThingsResult.getNextToken() == null || listThingsResult.getNextToken().equals(getNextToken());
    }
}
