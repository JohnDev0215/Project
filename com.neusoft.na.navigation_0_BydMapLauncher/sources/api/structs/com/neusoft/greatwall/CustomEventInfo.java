package api.structs.com.neusoft.greatwall;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class CustomEventInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480909922;

    @SerializedName("CustomEventName")
    private String customEventName = "";

    @SerializedName("CustomEventArgs")
    private Map<String, String> customEventArgs = new LinkedHashMap();

    public CustomEventInfo() {
    }

    public CustomEventInfo(String str, Map<String, String> map) {
        setCustomEventName(str);
        setCustomEventArgs(map);
    }

    public String getCustomEventName() {
        return this.customEventName;
    }

    public CustomEventInfo setCustomEventName(String str) {
        if (str == null) {
            throw new NullPointerException("customEventName cannot be null");
        }
        this.customEventName = str;
        return this;
    }

    public Map<String, String> getCustomEventArgs() {
        return this.customEventArgs;
    }

    public CustomEventInfo setCustomEventArgs(Map<String, String> map) {
        if (map == null) {
            throw new NullPointerException("customEventArgs cannot be null");
        }
        this.customEventArgs = map;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CustomEventInfo customEventInfo = (CustomEventInfo) obj;
        return Objects.equals(this.customEventName, customEventInfo.customEventName) && Objects.equals(this.customEventArgs, customEventInfo.customEventArgs);
    }

    public int hashCode() {
        return Objects.hash(this.customEventName, this.customEventArgs);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
