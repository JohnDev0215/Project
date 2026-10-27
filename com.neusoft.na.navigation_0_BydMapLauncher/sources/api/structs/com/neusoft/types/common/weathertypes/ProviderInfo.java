package api.structs.com.neusoft.types.common.weathertypes;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ProviderInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478137506;

    @SerializedName("Provider")
    private Provider provider = Provider.NONE;

    @SerializedName("Name")
    private String name = "";

    @SerializedName("Url")
    private String url = "";

    @SerializedName("Available")
    private Boolean available = false;

    public ProviderInfo() {
    }

    public ProviderInfo(Provider provider, String str, String str2, Boolean bool) {
        setProvider(provider);
        setName(str);
        setUrl(str2);
        setAvailable(bool);
    }

    public Provider getProvider() {
        return this.provider;
    }

    public ProviderInfo setProvider(Provider provider) {
        this.provider = provider;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public ProviderInfo setName(String str) {
        if (str == null) {
            throw new NullPointerException("name cannot be null");
        }
        this.name = str;
        return this;
    }

    public String getUrl() {
        return this.url;
    }

    public ProviderInfo setUrl(String str) {
        if (str == null) {
            throw new NullPointerException("url cannot be null");
        }
        this.url = str;
        return this;
    }

    public Boolean getAvailable() {
        return this.available;
    }

    public ProviderInfo setAvailable(Boolean bool) {
        if (bool == null) {
            throw new NullPointerException("available cannot be null");
        }
        this.available = bool;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ProviderInfo providerInfo = (ProviderInfo) obj;
        return Objects.equals(this.provider, providerInfo.provider) && Objects.equals(this.name, providerInfo.name) && Objects.equals(this.url, providerInfo.url) && Objects.equals(this.available, providerInfo.available);
    }

    public int hashCode() {
        return Objects.hash(this.provider, this.name, this.url, this.available);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
