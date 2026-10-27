package api.structs.com.neusoft.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class CommonStringInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478140386;

    @SerializedName("InfoString")
    private String infoString = "";

    public CommonStringInfo() {
    }

    public CommonStringInfo(String str) {
        setInfoString(str);
    }

    public String getInfoString() {
        return this.infoString;
    }

    public CommonStringInfo setInfoString(String str) {
        if (str == null) {
            throw new NullPointerException("infoString cannot be null");
        }
        this.infoString = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.infoString, ((CommonStringInfo) obj).infoString);
    }

    public int hashCode() {
        return Objects.hash(this.infoString);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
