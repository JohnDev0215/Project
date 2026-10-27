package cerence.ark.assistant.framework.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class LogConsumer implements Parcelable {
    public static final Parcelable.Creator<LogConsumer> CREATOR = new Parcelable.Creator<LogConsumer>() { // from class: cerence.ark.assistant.framework.data.LogConsumer.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LogConsumer createFromParcel(Parcel parcel) {
            return new LogConsumer(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LogConsumer[] newArray(int i) {
            return new LogConsumer[i];
        }
    };
    private final String mLogModules;
    private final String mName;
    private final String mOutput;
    private final String mSuppressModules;
    private final String mZones;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private LogConsumer(Builder builder) {
        this.mName = builder.mName;
        this.mOutput = builder.mOutput;
        this.mLogModules = builder.mLogModules;
        this.mSuppressModules = builder.mSuppressModules;
        this.mZones = builder.mZones;
    }

    protected LogConsumer(Parcel parcel) {
        this.mName = parcel.readString();
        this.mOutput = parcel.readString();
        this.mLogModules = parcel.readString();
        this.mSuppressModules = parcel.readString();
        this.mZones = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mName);
        parcel.writeString(this.mOutput);
        parcel.writeString(this.mLogModules);
        parcel.writeString(this.mSuppressModules);
        parcel.writeString(this.mZones);
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getName() {
        return this.mName;
    }

    public String getOutput() {
        return this.mOutput;
    }

    public String getLogModules() {
        return this.mLogModules;
    }

    public String getSuppressModules() {
        return this.mSuppressModules;
    }

    public String getZones() {
        return this.mZones;
    }

    public String toString() {
        return "LogConsumer{name: " + this.mName + ", output: " + this.mOutput + ", logModules: " + this.mLogModules + ", suppressModules: " + this.mSuppressModules + ", zones: " + this.mZones + "}";
    }

    public static final class Builder {
        private String mLogModules;
        private String mName;
        private String mOutput;
        private String mSuppressModules;
        private String mZones;

        public Builder setName(String str) {
            this.mName = str;
            return this;
        }

        public Builder setOutput(String str) {
            this.mOutput = str;
            return this;
        }

        public Builder setLogModules(String str) {
            this.mLogModules = str;
            return this;
        }

        public Builder setSuppressModules(String str) {
            this.mSuppressModules = str;
            return this;
        }

        public Builder setZones(String str) {
            this.mZones = str;
            return this;
        }

        public LogConsumer build() {
            return new LogConsumer(this);
        }
    }
}
