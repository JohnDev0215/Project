package com.bugsnag.android;

import com.amazon.music.inappmessaging.internal.model.Splash;
import com.bugsnag.android.JsonStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: compiled from: Device.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0019\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B_\b\u0000\u0012\u0006\u0010?\u001a\u00020>\u0012\u000e\u0010$\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010#\u0012\b\u0010+\u001a\u0004\u0018\u00010*\u0012\b\u00101\u001a\u0004\u0018\u00010\u0003\u0012\b\u00104\u001a\u0004\u0018\u00010\u0003\u0012\b\u00108\u001a\u0004\u0018\u000107\u0012\u0014\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0002¢\u0006\u0004\b@\u0010AJ,\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00022\u0014\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0002H\u0002J\u0017\u0010\f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0010¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0016R$\u0010\u000e\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R$\u0010\u0014\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R$\u0010\u0017\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\u000f\u001a\u0004\b\u0018\u0010\u0011\"\u0004\b\u0019\u0010\u0013R$\u0010\u001a\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u000f\u001a\u0004\b\u001b\u0010\u0011\"\u0004\b\u001c\u0010\u0013RF\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00022\u0014\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00028\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R*\u0010$\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010#8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R$\u0010+\u001a\u0004\u0018\u00010*8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R$\u00101\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b1\u0010\u000f\u001a\u0004\b2\u0010\u0011\"\u0004\b3\u0010\u0013R$\u00104\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b4\u0010\u000f\u001a\u0004\b5\u0010\u0011\"\u0004\b6\u0010\u0013R$\u00108\u001a\u0004\u0018\u0001078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b8\u00109\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=¨\u0006B"}, d2 = {"Lcom/bugsnag/android/Device;", "Lcom/bugsnag/android/JsonStream$Streamable;", "", "", "", "value", "sanitizeRuntimeVersions", "Lcom/bugsnag/android/JsonStream;", "writer", "", "serializeFields$bugsnag_android_core_release", "(Lcom/bugsnag/android/JsonStream;)V", "serializeFields", "toStream", "manufacturer", "Ljava/lang/String;", "getManufacturer", "()Ljava/lang/String;", "setManufacturer", "(Ljava/lang/String;)V", "model", "getModel", "setModel", "osName", "getOsName", "setOsName", "osVersion", "getOsVersion", "setOsVersion", "runtimeVersions", "Ljava/util/Map;", "getRuntimeVersions", "()Ljava/util/Map;", "setRuntimeVersions", "(Ljava/util/Map;)V", "", "cpuAbi", "[Ljava/lang/String;", "getCpuAbi", "()[Ljava/lang/String;", "setCpuAbi", "([Ljava/lang/String;)V", "", "jailbroken", "Ljava/lang/Boolean;", "getJailbroken", "()Ljava/lang/Boolean;", "setJailbroken", "(Ljava/lang/Boolean;)V", "id", "getId", "setId", Splash.PARAMS_LOCALE, "getLocale", "setLocale", "", "totalMemory", "Ljava/lang/Long;", "getTotalMemory", "()Ljava/lang/Long;", "setTotalMemory", "(Ljava/lang/Long;)V", "Lcom/bugsnag/android/DeviceBuildInfo;", "buildInfo", "<init>", "(Lcom/bugsnag/android/DeviceBuildInfo;[Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/util/Map;)V", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public class Device implements JsonStream.Streamable {
    private String[] cpuAbi;
    private String id;
    private Boolean jailbroken;
    private String locale;
    private String manufacturer;
    private String model;
    private String osName = "android";
    private String osVersion;
    private Map<String, Object> runtimeVersions;
    private Long totalMemory;

    public Device(DeviceBuildInfo deviceBuildInfo, String[] strArr, Boolean bool, String str, String str2, Long l, Map<String, Object> map) {
        this.cpuAbi = strArr;
        this.jailbroken = bool;
        this.id = str;
        this.locale = str2;
        this.totalMemory = l;
        this.manufacturer = deviceBuildInfo.getManufacturer();
        this.model = deviceBuildInfo.getModel();
        this.osVersion = deviceBuildInfo.getOsVersion();
        this.runtimeVersions = sanitizeRuntimeVersions(map);
    }

    public final String[] getCpuAbi() {
        return this.cpuAbi;
    }

    public final Boolean getJailbroken() {
        return this.jailbroken;
    }

    public final String getId() {
        return this.id;
    }

    public final String getLocale() {
        return this.locale;
    }

    public final Long getTotalMemory() {
        return this.totalMemory;
    }

    public final String getManufacturer() {
        return this.manufacturer;
    }

    public final String getModel() {
        return this.model;
    }

    public final String getOsName() {
        return this.osName;
    }

    public final String getOsVersion() {
        return this.osVersion;
    }

    public final Map<String, Object> getRuntimeVersions() {
        return this.runtimeVersions;
    }

    public void serializeFields$bugsnag_android_core_release(JsonStream writer) throws IOException {
        writer.name("cpuAbi").value(this.cpuAbi);
        writer.name("jailbroken").value(this.jailbroken);
        writer.name("id").value(this.id);
        writer.name(Splash.PARAMS_LOCALE).value(this.locale);
        writer.name("manufacturer").value(this.manufacturer);
        writer.name("model").value(this.model);
        writer.name("osName").value(this.osName);
        writer.name("osVersion").value(this.osVersion);
        writer.name("runtimeVersions").value(this.runtimeVersions);
        writer.name("totalMemory").value((Number) this.totalMemory);
    }

    @Override // com.bugsnag.android.JsonStream.Streamable
    public void toStream(JsonStream writer) throws IOException {
        writer.beginObject();
        serializeFields$bugsnag_android_core_release(writer);
        writer.endObject();
    }

    private final Map<String, Object> sanitizeRuntimeVersions(Map<String, Object> value) {
        if (value == null) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<T> it = value.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            linkedHashMap.put(entry.getKey(), entry.getValue().toString());
        }
        return linkedHashMap;
    }
}
