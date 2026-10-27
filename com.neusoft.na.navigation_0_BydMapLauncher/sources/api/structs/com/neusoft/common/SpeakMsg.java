package api.structs.com.neusoft.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class SpeakMsg implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478140444;

    @SerializedName("Msg")
    private String msg = "";

    public SpeakMsg() {
    }

    public SpeakMsg(String str) {
        setMsg(str);
    }

    public String getMsg() {
        return this.msg;
    }

    public SpeakMsg setMsg(String str) {
        if (str == null) {
            throw new NullPointerException("msg cannot be null");
        }
        this.msg = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.msg, ((SpeakMsg) obj).msg);
    }

    public int hashCode() {
        return Objects.hash(this.msg);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
