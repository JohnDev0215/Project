package api.structs.com.neusoft.ecarx;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class NameInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480912130;

    @SerializedName("LanguageCode")
    private String languageCode = "";

    @SerializedName("Name")
    private String name = "";

    public NameInfo() {
    }

    public NameInfo(String str, String str2) {
        setLanguageCode(str);
        setName(str2);
    }

    public String getLanguageCode() {
        return this.languageCode;
    }

    public NameInfo setLanguageCode(String str) {
        if (str == null) {
            throw new NullPointerException("languageCode cannot be null");
        }
        this.languageCode = str;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public NameInfo setName(String str) {
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
        NameInfo nameInfo = (NameInfo) obj;
        return Objects.equals(this.languageCode, nameInfo.languageCode) && Objects.equals(this.name, nameInfo.name);
    }

    public int hashCode() {
        return Objects.hash(this.languageCode, this.name);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
