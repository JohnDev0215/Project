package api.structs.com.neusoft.onecore.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class Locale implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478137564;

    @SerializedName("LanguageCode")
    private String languageCode = "";

    @SerializedName("CountryCode")
    private String countryCode = "";

    @SerializedName("ScriptCode")
    private String scriptCode = "";

    public Locale() {
    }

    public Locale(String str, String str2, String str3) {
        setLanguageCode(str);
        setCountryCode(str2);
        setScriptCode(str3);
    }

    public String getLanguageCode() {
        return this.languageCode;
    }

    public Locale setLanguageCode(String str) {
        if (str == null) {
            throw new NullPointerException("languageCode cannot be null");
        }
        this.languageCode = str;
        return this;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public Locale setCountryCode(String str) {
        if (str == null) {
            throw new NullPointerException("countryCode cannot be null");
        }
        this.countryCode = str;
        return this;
    }

    public String getScriptCode() {
        return this.scriptCode;
    }

    public Locale setScriptCode(String str) {
        if (str == null) {
            throw new NullPointerException("scriptCode cannot be null");
        }
        this.scriptCode = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Locale locale = (Locale) obj;
        return Objects.equals(this.languageCode, locale.languageCode) && Objects.equals(this.countryCode, locale.countryCode) && Objects.equals(this.scriptCode, locale.scriptCode);
    }

    public int hashCode() {
        return Objects.hash(this.languageCode, this.countryCode, this.scriptCode);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
