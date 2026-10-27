package api.structs.com.neusoft.onoff.onoffmaster;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ProcessInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OCTA;
    private static final long serialVersionUID = -1477218882;

    @SerializedName("ID")
    private BigInteger iD = BigInteger.ZERO;

    @SerializedName("ProcessName")
    private String processName = "";

    @SerializedName("Exe")
    private String exe = "";

    @SerializedName("Args")
    private String args = "";

    @SerializedName("Pause")
    private Long pause = 0L;

    @SerializedName("IsRunning")
    private Boolean isRunning = false;

    public ProcessInfo() {
    }

    public ProcessInfo(BigInteger bigInteger, String str, String str2, String str3, Long l, Boolean bool) {
        setID(bigInteger);
        setProcessName(str);
        setExe(str2);
        setArgs(str3);
        setPause(l);
        setIsRunning(bool);
    }

    public BigInteger getID() {
        return this.iD;
    }

    public ProcessInfo setID(BigInteger bigInteger) {
        if (bigInteger == null) {
            throw new NullPointerException("iD cannot be null");
        }
        this.iD = bigInteger;
        return this;
    }

    public String getProcessName() {
        return this.processName;
    }

    public ProcessInfo setProcessName(String str) {
        if (str == null) {
            throw new NullPointerException("processName cannot be null");
        }
        this.processName = str;
        return this;
    }

    public String getExe() {
        return this.exe;
    }

    public ProcessInfo setExe(String str) {
        if (str == null) {
            throw new NullPointerException("exe cannot be null");
        }
        this.exe = str;
        return this;
    }

    public String getArgs() {
        return this.args;
    }

    public ProcessInfo setArgs(String str) {
        if (str == null) {
            throw new NullPointerException("args cannot be null");
        }
        this.args = str;
        return this;
    }

    public Long getPause() {
        return this.pause;
    }

    public ProcessInfo setPause(Long l) {
        if (l == null) {
            throw new NullPointerException("pause cannot be null");
        }
        this.pause = l;
        return this;
    }

    public Boolean getIsRunning() {
        return this.isRunning;
    }

    public ProcessInfo setIsRunning(Boolean bool) {
        if (bool == null) {
            throw new NullPointerException("isRunning cannot be null");
        }
        this.isRunning = bool;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ProcessInfo processInfo = (ProcessInfo) obj;
        return Objects.equals(this.iD, processInfo.iD) && Objects.equals(this.processName, processInfo.processName) && Objects.equals(this.exe, processInfo.exe) && Objects.equals(this.args, processInfo.args) && Objects.equals(this.pause, processInfo.pause) && Objects.equals(this.isRunning, processInfo.isRunning);
    }

    public int hashCode() {
        return Objects.hash(this.iD, this.processName, this.exe, this.args, this.pause, this.isRunning);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
