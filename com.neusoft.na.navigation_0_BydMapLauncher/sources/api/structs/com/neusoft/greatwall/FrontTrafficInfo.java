package api.structs.com.neusoft.greatwall;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class FrontTrafficInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480909214;

    @SerializedName("IsDelay")
    private Boolean isDelay = false;

    @SerializedName("ToDelayDist")
    private Integer toDelayDist = 0;

    @SerializedName("DelayDist")
    private Integer delayDist = 0;

    @SerializedName("DelayTime")
    private Integer delayTime = 0;

    public FrontTrafficInfo() {
    }

    public FrontTrafficInfo(Boolean bool, Integer num, Integer num2, Integer num3) {
        setIsDelay(bool);
        setToDelayDist(num);
        setDelayDist(num2);
        setDelayTime(num3);
    }

    public Boolean getIsDelay() {
        return this.isDelay;
    }

    public FrontTrafficInfo setIsDelay(Boolean bool) {
        if (bool == null) {
            throw new NullPointerException("isDelay cannot be null");
        }
        this.isDelay = bool;
        return this;
    }

    public Integer getToDelayDist() {
        return this.toDelayDist;
    }

    public FrontTrafficInfo setToDelayDist(Integer num) {
        if (num == null) {
            throw new NullPointerException("toDelayDist cannot be null");
        }
        this.toDelayDist = num;
        return this;
    }

    public Integer getDelayDist() {
        return this.delayDist;
    }

    public FrontTrafficInfo setDelayDist(Integer num) {
        if (num == null) {
            throw new NullPointerException("delayDist cannot be null");
        }
        this.delayDist = num;
        return this;
    }

    public Integer getDelayTime() {
        return this.delayTime;
    }

    public FrontTrafficInfo setDelayTime(Integer num) {
        if (num == null) {
            throw new NullPointerException("delayTime cannot be null");
        }
        this.delayTime = num;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FrontTrafficInfo frontTrafficInfo = (FrontTrafficInfo) obj;
        return Objects.equals(this.isDelay, frontTrafficInfo.isDelay) && Objects.equals(this.toDelayDist, frontTrafficInfo.toDelayDist) && Objects.equals(this.delayDist, frontTrafficInfo.delayDist) && Objects.equals(this.delayTime, frontTrafficInfo.delayTime);
    }

    public int hashCode() {
        return Objects.hash(this.isDelay, this.toDelayDist, this.delayDist, this.delayTime);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
