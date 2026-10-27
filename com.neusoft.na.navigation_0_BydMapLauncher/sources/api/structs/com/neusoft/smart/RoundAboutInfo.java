package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class RoundAboutInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479991489;

    @SerializedName("PassedExitNumber")
    private Integer passedExitNumber = 0;

    public RoundAboutInfo() {
    }

    public RoundAboutInfo(Integer num) {
        setPassedExitNumber(num);
    }

    public Integer getPassedExitNumber() {
        return this.passedExitNumber;
    }

    public RoundAboutInfo setPassedExitNumber(Integer num) {
        if (num == null) {
            throw new NullPointerException("passedExitNumber cannot be null");
        }
        this.passedExitNumber = num;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.passedExitNumber, ((RoundAboutInfo) obj).passedExitNumber);
    }

    public int hashCode() {
        return Objects.hash(this.passedExitNumber);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
