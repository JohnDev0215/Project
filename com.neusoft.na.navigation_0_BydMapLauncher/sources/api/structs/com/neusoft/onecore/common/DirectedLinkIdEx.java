package api.structs.com.neusoft.onecore.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import com.neusoft.na.ocservice.Broadcast.MotsBroadcastReceiver;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class DirectedLinkIdEx implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478137622;

    @SerializedName(MotsBroadcastReceiver.ACTION_PRESENTATION_VALUE)
    private BigInteger value = BigInteger.ZERO;

    @SerializedName("URId")
    private Integer uRId = 0;

    @SerializedName("PackedTileId")
    private Integer packedTileId = 0;

    @SerializedName("LocalId")
    private Integer localId = 0;

    @SerializedName("Direction")
    private LinkDirection direction = LinkDirection.POSITIVE;

    public DirectedLinkIdEx() {
    }

    public DirectedLinkIdEx(BigInteger bigInteger, Integer num, Integer num2, Integer num3, LinkDirection linkDirection) {
        setValue(bigInteger);
        setURId(num);
        setPackedTileId(num2);
        setLocalId(num3);
        setDirection(linkDirection);
    }

    public BigInteger getValue() {
        return this.value;
    }

    public DirectedLinkIdEx setValue(BigInteger bigInteger) {
        if (bigInteger == null) {
            throw new NullPointerException("value cannot be null");
        }
        this.value = bigInteger;
        return this;
    }

    public Integer getURId() {
        return this.uRId;
    }

    public DirectedLinkIdEx setURId(Integer num) {
        if (num == null) {
            throw new NullPointerException("uRId cannot be null");
        }
        this.uRId = num;
        return this;
    }

    public Integer getPackedTileId() {
        return this.packedTileId;
    }

    public DirectedLinkIdEx setPackedTileId(Integer num) {
        if (num == null) {
            throw new NullPointerException("packedTileId cannot be null");
        }
        this.packedTileId = num;
        return this;
    }

    public Integer getLocalId() {
        return this.localId;
    }

    public DirectedLinkIdEx setLocalId(Integer num) {
        if (num == null) {
            throw new NullPointerException("localId cannot be null");
        }
        this.localId = num;
        return this;
    }

    public LinkDirection getDirection() {
        return this.direction;
    }

    public DirectedLinkIdEx setDirection(LinkDirection linkDirection) {
        this.direction = linkDirection;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DirectedLinkIdEx directedLinkIdEx = (DirectedLinkIdEx) obj;
        return Objects.equals(this.value, directedLinkIdEx.value) && Objects.equals(this.uRId, directedLinkIdEx.uRId) && Objects.equals(this.packedTileId, directedLinkIdEx.packedTileId) && Objects.equals(this.localId, directedLinkIdEx.localId) && Objects.equals(this.direction, directedLinkIdEx.direction);
    }

    public int hashCode() {
        return Objects.hash(this.value, this.uRId, this.packedTileId, this.localId, this.direction);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
