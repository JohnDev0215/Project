package api.structs.com.neusoft.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class SpaceInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478140575;

    @SerializedName("PartitionName")
    private String partitionName = "";

    @SerializedName("Capacity")
    private BigInteger capacity = BigInteger.ZERO;

    @SerializedName("Free")
    private BigInteger free = BigInteger.ZERO;

    @SerializedName("Available")
    private BigInteger available = BigInteger.ZERO;

    public SpaceInfo() {
    }

    public SpaceInfo(String str, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        setPartitionName(str);
        setCapacity(bigInteger);
        setFree(bigInteger2);
        setAvailable(bigInteger3);
    }

    public String getPartitionName() {
        return this.partitionName;
    }

    public SpaceInfo setPartitionName(String str) {
        if (str == null) {
            throw new NullPointerException("partitionName cannot be null");
        }
        this.partitionName = str;
        return this;
    }

    public BigInteger getCapacity() {
        return this.capacity;
    }

    public SpaceInfo setCapacity(BigInteger bigInteger) {
        if (bigInteger == null) {
            throw new NullPointerException("capacity cannot be null");
        }
        this.capacity = bigInteger;
        return this;
    }

    public BigInteger getFree() {
        return this.free;
    }

    public SpaceInfo setFree(BigInteger bigInteger) {
        if (bigInteger == null) {
            throw new NullPointerException("free cannot be null");
        }
        this.free = bigInteger;
        return this;
    }

    public BigInteger getAvailable() {
        return this.available;
    }

    public SpaceInfo setAvailable(BigInteger bigInteger) {
        if (bigInteger == null) {
            throw new NullPointerException("available cannot be null");
        }
        this.available = bigInteger;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SpaceInfo spaceInfo = (SpaceInfo) obj;
        return Objects.equals(this.partitionName, spaceInfo.partitionName) && Objects.equals(this.capacity, spaceInfo.capacity) && Objects.equals(this.free, spaceInfo.free) && Objects.equals(this.available, spaceInfo.available);
    }

    public int hashCode() {
        return Objects.hash(this.partitionName, this.capacity, this.free, this.available);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
