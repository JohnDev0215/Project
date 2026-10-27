package api.structs.com.neusoft.greatwall;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class RateLimiting implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480909959;

    @SerializedName("SpeedLimit")
    private Integer speedLimit = 0;

    public RateLimiting() {
    }

    public RateLimiting(Integer num) {
        setSpeedLimit(num);
    }

    public Integer getSpeedLimit() {
        return this.speedLimit;
    }

    public RateLimiting setSpeedLimit(Integer num) {
        if (num == null) {
            throw new NullPointerException("speedLimit cannot be null");
        }
        this.speedLimit = num;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.speedLimit, ((RateLimiting) obj).speedLimit);
    }

    public int hashCode() {
        return Objects.hash(this.speedLimit);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
