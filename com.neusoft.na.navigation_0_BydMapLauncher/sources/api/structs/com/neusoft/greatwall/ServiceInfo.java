package api.structs.com.neusoft.greatwall;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ServiceInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480909922;

    @SerializedName("ServiceEventName")
    private String serviceEventName = "";

    @SerializedName("ServiceArgs")
    private Map<String, String> serviceArgs = new LinkedHashMap();

    public ServiceInfo() {
    }

    public ServiceInfo(String str, Map<String, String> map) {
        setServiceEventName(str);
        setServiceArgs(map);
    }

    public String getServiceEventName() {
        return this.serviceEventName;
    }

    public ServiceInfo setServiceEventName(String str) {
        if (str == null) {
            throw new NullPointerException("serviceEventName cannot be null");
        }
        this.serviceEventName = str;
        return this;
    }

    public Map<String, String> getServiceArgs() {
        return this.serviceArgs;
    }

    public ServiceInfo setServiceArgs(Map<String, String> map) {
        if (map == null) {
            throw new NullPointerException("serviceArgs cannot be null");
        }
        this.serviceArgs = map;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ServiceInfo serviceInfo = (ServiceInfo) obj;
        return Objects.equals(this.serviceEventName, serviceInfo.serviceEventName) && Objects.equals(this.serviceArgs, serviceInfo.serviceArgs);
    }

    public int hashCode() {
        return Objects.hash(this.serviceEventName, this.serviceArgs);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
