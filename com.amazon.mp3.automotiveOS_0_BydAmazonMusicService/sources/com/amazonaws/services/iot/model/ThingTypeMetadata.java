package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class ThingTypeMetadata implements Serializable {
    private Date creationDate;
    private Boolean deprecated;
    private Date deprecationDate;

    public Boolean isDeprecated() {
        return this.deprecated;
    }

    public Boolean getDeprecated() {
        return this.deprecated;
    }

    public void setDeprecated(Boolean bool) {
        this.deprecated = bool;
    }

    public ThingTypeMetadata withDeprecated(Boolean bool) {
        this.deprecated = bool;
        return this;
    }

    public Date getDeprecationDate() {
        return this.deprecationDate;
    }

    public void setDeprecationDate(Date date) {
        this.deprecationDate = date;
    }

    public ThingTypeMetadata withDeprecationDate(Date date) {
        this.deprecationDate = date;
        return this;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public ThingTypeMetadata withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getDeprecated() != null) {
            sb.append("deprecated: " + getDeprecated() + ",");
        }
        if (getDeprecationDate() != null) {
            sb.append("deprecationDate: " + getDeprecationDate() + ",");
        }
        if (getCreationDate() != null) {
            sb.append("creationDate: " + getCreationDate());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getDeprecated() == null ? 0 : getDeprecated().hashCode()) + 31) * 31) + (getDeprecationDate() == null ? 0 : getDeprecationDate().hashCode())) * 31) + (getCreationDate() != null ? getCreationDate().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ThingTypeMetadata)) {
            return false;
        }
        ThingTypeMetadata thingTypeMetadata = (ThingTypeMetadata) obj;
        if ((thingTypeMetadata.getDeprecated() == null) ^ (getDeprecated() == null)) {
            return false;
        }
        if (thingTypeMetadata.getDeprecated() != null && !thingTypeMetadata.getDeprecated().equals(getDeprecated())) {
            return false;
        }
        if ((thingTypeMetadata.getDeprecationDate() == null) ^ (getDeprecationDate() == null)) {
            return false;
        }
        if (thingTypeMetadata.getDeprecationDate() != null && !thingTypeMetadata.getDeprecationDate().equals(getDeprecationDate())) {
            return false;
        }
        if ((thingTypeMetadata.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        return thingTypeMetadata.getCreationDate() == null || thingTypeMetadata.getCreationDate().equals(getCreationDate());
    }
}
