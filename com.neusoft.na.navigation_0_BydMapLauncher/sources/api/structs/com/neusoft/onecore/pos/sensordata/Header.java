package api.structs.com.neusoft.onecore.pos.sensordata;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class Header implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478137469;

    @SerializedName("Timestamp")
    private Long timestamp = 0L;

    @SerializedName("Sequence")
    private Long sequence = 0L;

    @SerializedName("Status")
    private Status status = Status.UNKNOWN;

    public Header() {
    }

    public Header(Long l, Long l2, Status status) {
        setTimestamp(l);
        setSequence(l2);
        setStatus(status);
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public Header setTimestamp(Long l) {
        if (l == null) {
            throw new NullPointerException("timestamp cannot be null");
        }
        this.timestamp = l;
        return this;
    }

    public Long getSequence() {
        return this.sequence;
    }

    public Header setSequence(Long l) {
        if (l == null) {
            throw new NullPointerException("sequence cannot be null");
        }
        this.sequence = l;
        return this;
    }

    public Status getStatus() {
        return this.status;
    }

    public Header setStatus(Status status) {
        this.status = status;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Header header = (Header) obj;
        return Objects.equals(this.timestamp, header.timestamp) && Objects.equals(this.sequence, header.sequence) && Objects.equals(this.status, header.status);
    }

    public int hashCode() {
        return Objects.hash(this.timestamp, this.sequence, this.status);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
