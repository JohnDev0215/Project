package api.structs.com.neusoft.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class MessageInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478140480;

    @SerializedName("Delay")
    private DelayPeriod delay = DelayPeriod.LONG_DELAY;

    @SerializedName("Message")
    private String message = "";

    public MessageInfo() {
    }

    public MessageInfo(DelayPeriod delayPeriod, String str) {
        setDelay(delayPeriod);
        setMessage(str);
    }

    public DelayPeriod getDelay() {
        return this.delay;
    }

    public MessageInfo setDelay(DelayPeriod delayPeriod) {
        this.delay = delayPeriod;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public MessageInfo setMessage(String str) {
        if (str == null) {
            throw new NullPointerException("message cannot be null");
        }
        this.message = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MessageInfo messageInfo = (MessageInfo) obj;
        return Objects.equals(this.delay, messageInfo.delay) && Objects.equals(this.message, messageInfo.message);
    }

    public int hashCode() {
        return Objects.hash(this.delay, this.message);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
