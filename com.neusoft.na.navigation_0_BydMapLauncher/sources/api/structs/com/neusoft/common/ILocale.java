package api.structs.com.neusoft.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ILocale implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478140444;

    @SerializedName("Language")
    private String language = "";

    @SerializedName("Country")
    private String country = "";

    @SerializedName("Script")
    private String script = "";

    public ILocale() {
    }

    public ILocale(String str, String str2, String str3) {
        setLanguage(str);
        setCountry(str2);
        setScript(str3);
    }

    public String getLanguage() {
        return this.language;
    }

    public ILocale setLanguage(String str) {
        if (str == null) {
            throw new NullPointerException("language cannot be null");
        }
        this.language = str;
        return this;
    }

    public String getCountry() {
        return this.country;
    }

    public ILocale setCountry(String str) {
        if (str == null) {
            throw new NullPointerException("country cannot be null");
        }
        this.country = str;
        return this;
    }

    public String getScript() {
        return this.script;
    }

    public ILocale setScript(String str) {
        if (str == null) {
            throw new NullPointerException("script cannot be null");
        }
        this.script = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ILocale iLocale = (ILocale) obj;
        return Objects.equals(this.language, iLocale.language) && Objects.equals(this.country, iLocale.country) && Objects.equals(this.script, iLocale.script);
    }

    public int hashCode() {
        return Objects.hash(this.language, this.country, this.script);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
