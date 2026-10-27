package api.structs.com.neusoft.ecarx;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class PoiType implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480913027;

    @SerializedName("TypeCode")
    private String typeCode = "";

    @SerializedName("TypeName")
    private String typeName = "";

    public PoiType() {
    }

    public PoiType(String str, String str2) {
        setTypeCode(str);
        setTypeName(str2);
    }

    public String getTypeCode() {
        return this.typeCode;
    }

    public PoiType setTypeCode(String str) {
        if (str == null) {
            throw new NullPointerException("typeCode cannot be null");
        }
        this.typeCode = str;
        return this;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public PoiType setTypeName(String str) {
        if (str == null) {
            throw new NullPointerException("typeName cannot be null");
        }
        this.typeName = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PoiType poiType = (PoiType) obj;
        return Objects.equals(this.typeCode, poiType.typeCode) && Objects.equals(this.typeName, poiType.typeName);
    }

    public int hashCode() {
        return Objects.hash(this.typeCode, this.typeName);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
