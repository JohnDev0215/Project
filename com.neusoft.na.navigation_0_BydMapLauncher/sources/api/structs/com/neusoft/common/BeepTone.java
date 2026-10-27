package api.structs.com.neusoft.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class BeepTone implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478140444;

    @SerializedName("Name")
    private String name = "";

    public BeepTone() {
    }

    public BeepTone(String str) {
        setName(str);
    }

    public String getName() {
        return this.name;
    }

    public BeepTone setName(String str) {
        if (str == null) {
            throw new NullPointerException("name cannot be null");
        }
        this.name = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.name, ((BeepTone) obj).name);
    }

    public int hashCode() {
        return Objects.hash(this.name);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
