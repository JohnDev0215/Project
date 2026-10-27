package ts.car.service.systemlog.config;

/* JADX INFO: loaded from: classes.dex */
public class LogConfig {
    public boolean isAbsolutePath;
    public int logFileNum;
    public int logFileSize;
    public int logLevel;
    public String apid = "";
    public String ctid = "";
    public String logLoopback = "";
    public String logPath = "";

    public String toString() {
        return "ProviderConfig{apid='" + this.apid + "', ctid='" + this.ctid + "', logFileSize=" + this.logFileSize + ", logFileNum=" + this.logFileNum + ", logLevel=" + this.logLevel + ", logLoopback='" + this.logLoopback + "', logPath='" + this.logPath + "', isAbsolutePath=" + this.isAbsolutePath + '}';
    }
}
