package api.structs.com.neusoft.greatwall;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class GwWTInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479063911;

    @SerializedName("CondCode")
    private String condCode = "";

    @SerializedName("CondTxt")
    private String condTxt = "";

    @SerializedName("Pres")
    private String pres = "";

    @SerializedName("Tmp")
    private String tmp = "";

    @SerializedName("WindDir")
    private String windDir = "";

    @SerializedName("WindSpd")
    private String windSpd = "";

    public GwWTInfo() {
    }

    public GwWTInfo(String str, String str2, String str3, String str4, String str5, String str6) {
        setCondCode(str);
        setCondTxt(str2);
        setPres(str3);
        setTmp(str4);
        setWindDir(str5);
        setWindSpd(str6);
    }

    public String getCondCode() {
        return this.condCode;
    }

    public GwWTInfo setCondCode(String str) {
        if (str == null) {
            throw new NullPointerException("condCode cannot be null");
        }
        this.condCode = str;
        return this;
    }

    public String getCondTxt() {
        return this.condTxt;
    }

    public GwWTInfo setCondTxt(String str) {
        if (str == null) {
            throw new NullPointerException("condTxt cannot be null");
        }
        this.condTxt = str;
        return this;
    }

    public String getPres() {
        return this.pres;
    }

    public GwWTInfo setPres(String str) {
        if (str == null) {
            throw new NullPointerException("pres cannot be null");
        }
        this.pres = str;
        return this;
    }

    public String getTmp() {
        return this.tmp;
    }

    public GwWTInfo setTmp(String str) {
        if (str == null) {
            throw new NullPointerException("tmp cannot be null");
        }
        this.tmp = str;
        return this;
    }

    public String getWindDir() {
        return this.windDir;
    }

    public GwWTInfo setWindDir(String str) {
        if (str == null) {
            throw new NullPointerException("windDir cannot be null");
        }
        this.windDir = str;
        return this;
    }

    public String getWindSpd() {
        return this.windSpd;
    }

    public GwWTInfo setWindSpd(String str) {
        if (str == null) {
            throw new NullPointerException("windSpd cannot be null");
        }
        this.windSpd = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GwWTInfo gwWTInfo = (GwWTInfo) obj;
        return Objects.equals(this.condCode, gwWTInfo.condCode) && Objects.equals(this.condTxt, gwWTInfo.condTxt) && Objects.equals(this.pres, gwWTInfo.pres) && Objects.equals(this.tmp, gwWTInfo.tmp) && Objects.equals(this.windDir, gwWTInfo.windDir) && Objects.equals(this.windSpd, gwWTInfo.windSpd);
    }

    public int hashCode() {
        return Objects.hash(this.condCode, this.condTxt, this.pres, this.tmp, this.windDir, this.windSpd);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
