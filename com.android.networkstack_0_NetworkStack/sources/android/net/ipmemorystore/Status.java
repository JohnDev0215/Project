package android.net.ipmemorystore;

/* JADX INFO: loaded from: classes.dex */
public class Status {
    public final int resultCode;

    public Status(int i) {
        this.resultCode = i;
    }

    public Status(StatusParcelable statusParcelable) {
        this(statusParcelable.resultCode);
    }

    public StatusParcelable toParcelable() {
        StatusParcelable statusParcelable = new StatusParcelable();
        statusParcelable.resultCode = this.resultCode;
        return statusParcelable;
    }

    public boolean isSuccess() {
        return this.resultCode == 0;
    }

    public String toString() {
        int i = this.resultCode;
        return i != -4 ? i != -3 ? i != -2 ? i != -1 ? i != 0 ? "Unknown value ?!" : "SUCCESS" : "GENERIC ERROR" : "ILLEGAL ARGUMENT" : "DATABASE CANNOT BE OPENED" : "DATABASE STORAGE ERROR";
    }
}
