package api.structs.com.neusoft.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ActivateResultDetails implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478140538;

    @SerializedName("ActivateStatus")
    private ActivateStatus activateStatus = ActivateStatus.E_SUCCESS;

    @SerializedName("ActivationTime")
    private BigInteger activationTime = BigInteger.ZERO;

    @SerializedName("ExpiryTime")
    private BigInteger expiryTime = BigInteger.ZERO;

    @SerializedName("ActiveArea")
    private Integer activeArea = 0;

    public ActivateResultDetails() {
    }

    public ActivateResultDetails(ActivateStatus activateStatus, BigInteger bigInteger, BigInteger bigInteger2, Integer num) {
        setActivateStatus(activateStatus);
        setActivationTime(bigInteger);
        setExpiryTime(bigInteger2);
        setActiveArea(num);
    }

    public ActivateStatus getActivateStatus() {
        return this.activateStatus;
    }

    public ActivateResultDetails setActivateStatus(ActivateStatus activateStatus) {
        this.activateStatus = activateStatus;
        return this;
    }

    public BigInteger getActivationTime() {
        return this.activationTime;
    }

    public ActivateResultDetails setActivationTime(BigInteger bigInteger) {
        if (bigInteger == null) {
            throw new NullPointerException("activationTime cannot be null");
        }
        this.activationTime = bigInteger;
        return this;
    }

    public BigInteger getExpiryTime() {
        return this.expiryTime;
    }

    public ActivateResultDetails setExpiryTime(BigInteger bigInteger) {
        if (bigInteger == null) {
            throw new NullPointerException("expiryTime cannot be null");
        }
        this.expiryTime = bigInteger;
        return this;
    }

    public Integer getActiveArea() {
        return this.activeArea;
    }

    public ActivateResultDetails setActiveArea(Integer num) {
        if (num == null) {
            throw new NullPointerException("activeArea cannot be null");
        }
        this.activeArea = num;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ActivateResultDetails activateResultDetails = (ActivateResultDetails) obj;
        return Objects.equals(this.activateStatus, activateResultDetails.activateStatus) && Objects.equals(this.activationTime, activateResultDetails.activationTime) && Objects.equals(this.expiryTime, activateResultDetails.expiryTime) && Objects.equals(this.activeArea, activateResultDetails.activeArea);
    }

    public int hashCode() {
        return Objects.hash(this.activateStatus, this.activationTime, this.expiryTime, this.activeArea);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
