package api.structs.com.neusoft.onecore.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class Version implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478137622;

    @SerializedName("MajorNum")
    private Integer majorNum = 0;

    @SerializedName("MinorNum")
    private Integer minorNum = 0;

    @SerializedName("MicroNum")
    private Integer microNum = 0;

    @SerializedName("Date")
    private String date = "";

    public Version() {
    }

    public Version(Integer num, Integer num2, Integer num3, String str) {
        setMajorNum(num);
        setMinorNum(num2);
        setMicroNum(num3);
        setDate(str);
    }

    public Integer getMajorNum() {
        return this.majorNum;
    }

    public Version setMajorNum(Integer num) {
        if (num == null) {
            throw new NullPointerException("majorNum cannot be null");
        }
        this.majorNum = num;
        return this;
    }

    public Integer getMinorNum() {
        return this.minorNum;
    }

    public Version setMinorNum(Integer num) {
        if (num == null) {
            throw new NullPointerException("minorNum cannot be null");
        }
        this.minorNum = num;
        return this;
    }

    public Integer getMicroNum() {
        return this.microNum;
    }

    public Version setMicroNum(Integer num) {
        if (num == null) {
            throw new NullPointerException("microNum cannot be null");
        }
        this.microNum = num;
        return this;
    }

    public String getDate() {
        return this.date;
    }

    public Version setDate(String str) {
        if (str == null) {
            throw new NullPointerException("date cannot be null");
        }
        this.date = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Version version = (Version) obj;
        return Objects.equals(this.majorNum, version.majorNum) && Objects.equals(this.minorNum, version.minorNum) && Objects.equals(this.microNum, version.microNum) && Objects.equals(this.date, version.date);
    }

    public int hashCode() {
        return Objects.hash(this.majorNum, this.minorNum, this.microNum, this.date);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
