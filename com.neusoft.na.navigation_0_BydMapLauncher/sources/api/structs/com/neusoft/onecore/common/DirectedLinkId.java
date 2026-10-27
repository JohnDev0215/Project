package api.structs.com.neusoft.onecore.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import com.neusoft.na.ocservice.Broadcast.MotsBroadcastReceiver;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class DirectedLinkId implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478137622;

    @SerializedName(MotsBroadcastReceiver.ACTION_PRESENTATION_VALUE)
    private BigInteger value = BigInteger.ZERO;

    public DirectedLinkId() {
    }

    public DirectedLinkId(BigInteger bigInteger) {
        setValue(bigInteger);
    }

    public BigInteger getValue() {
        return this.value;
    }

    public DirectedLinkId setValue(BigInteger bigInteger) {
        if (bigInteger == null) {
            throw new NullPointerException("value cannot be null");
        }
        this.value = bigInteger;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.value, ((DirectedLinkId) obj).value);
    }

    public int hashCode() {
        return Objects.hash(this.value);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
