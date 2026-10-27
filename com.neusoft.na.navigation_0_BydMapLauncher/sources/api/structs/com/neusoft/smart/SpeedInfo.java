package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class SpeedInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479990461;

    @SerializedName("limitSpeed")
    private Integer limitSpeed = 0;

    @SerializedName("overSpeedStatus")
    private Boolean overSpeedStatus = false;

    @SerializedName("limitSpeedExist")
    private Boolean limitSpeedExist = false;

    public SpeedInfo() {
    }

    public SpeedInfo(Integer num, Boolean bool, Boolean bool2) {
        setLimitSpeed(num);
        setOverSpeedStatus(bool);
        setLimitSpeedExist(bool2);
    }

    public Integer getLimitSpeed() {
        return this.limitSpeed;
    }

    public SpeedInfo setLimitSpeed(Integer num) {
        if (num == null) {
            throw new NullPointerException("limitSpeed cannot be null");
        }
        this.limitSpeed = num;
        return this;
    }

    public Boolean getOverSpeedStatus() {
        return this.overSpeedStatus;
    }

    public SpeedInfo setOverSpeedStatus(Boolean bool) {
        if (bool == null) {
            throw new NullPointerException("overSpeedStatus cannot be null");
        }
        this.overSpeedStatus = bool;
        return this;
    }

    public Boolean getLimitSpeedExist() {
        return this.limitSpeedExist;
    }

    public SpeedInfo setLimitSpeedExist(Boolean bool) {
        if (bool == null) {
            throw new NullPointerException("limitSpeedExist cannot be null");
        }
        this.limitSpeedExist = bool;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SpeedInfo speedInfo = (SpeedInfo) obj;
        return Objects.equals(this.limitSpeed, speedInfo.limitSpeed) && Objects.equals(this.overSpeedStatus, speedInfo.overSpeedStatus) && Objects.equals(this.limitSpeedExist, speedInfo.limitSpeedExist);
    }

    public int hashCode() {
        return Objects.hash(this.limitSpeed, this.overSpeedStatus, this.limitSpeedExist);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
