package android.car.storagemonitoring;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonWriter;
import com.bydux.api.common.views.skin.entity.AttrFactory;
import java.io.IOException;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
@SystemApi
public final class IoStatsEntry implements Parcelable {
    public static final Parcelable.Creator<IoStatsEntry> CREATOR = new Parcelable.Creator<IoStatsEntry>() { // from class: android.car.storagemonitoring.IoStatsEntry.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IoStatsEntry createFromParcel(Parcel parcel) {
            return new IoStatsEntry(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IoStatsEntry[] newArray(int i) {
            return new IoStatsEntry[i];
        }
    };
    public final Metrics background;
    public final Metrics foreground;
    public final long runtimeMillis;
    public final int uid;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IoStatsEntry(int i, long j, Metrics metrics, Metrics metrics2) {
        this.uid = i;
        this.runtimeMillis = j;
        this.foreground = (Metrics) Objects.requireNonNull(metrics);
        this.background = (Metrics) Objects.requireNonNull(metrics2);
    }

    public IoStatsEntry(Parcel parcel) {
        this.uid = parcel.readInt();
        this.runtimeMillis = parcel.readLong();
        this.foreground = (Metrics) parcel.readParcelable(Metrics.class.getClassLoader());
        this.background = (Metrics) parcel.readParcelable(Metrics.class.getClassLoader());
    }

    public IoStatsEntry(UidIoRecord uidIoRecord, long j) {
        this.uid = uidIoRecord.uid;
        this.runtimeMillis = j;
        this.foreground = new Metrics(uidIoRecord.foreground_rchar, uidIoRecord.foreground_wchar, uidIoRecord.foreground_read_bytes, uidIoRecord.foreground_write_bytes, uidIoRecord.foreground_fsync);
        this.background = new Metrics(uidIoRecord.background_rchar, uidIoRecord.background_wchar, uidIoRecord.background_read_bytes, uidIoRecord.background_write_bytes, uidIoRecord.background_fsync);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.uid);
        parcel.writeLong(this.runtimeMillis);
        parcel.writeParcelable(this.foreground, i);
        parcel.writeParcelable(this.background, i);
    }

    public void writeToJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("uid").value(this.uid);
        jsonWriter.name("runtimeMillis").value(this.runtimeMillis);
        jsonWriter.name("foreground");
        this.foreground.writeToJson(jsonWriter);
        jsonWriter.name(AttrFactory.BACKGROUND);
        this.background.writeToJson(jsonWriter);
        jsonWriter.endObject();
    }

    public IoStatsEntry(JSONObject jSONObject) throws JSONException {
        this.uid = jSONObject.getInt("uid");
        this.runtimeMillis = jSONObject.getLong("runtimeMillis");
        this.foreground = new Metrics(jSONObject.getJSONObject("foreground"));
        this.background = new Metrics(jSONObject.getJSONObject(AttrFactory.BACKGROUND));
    }

    public IoStatsEntry delta(IoStatsEntry ioStatsEntry) {
        int i = this.uid;
        if (i != ioStatsEntry.uid) {
            throw new IllegalArgumentException("cannot calculate delta between different user IDs");
        }
        return new IoStatsEntry(i, this.runtimeMillis - ioStatsEntry.runtimeMillis, this.foreground.delta(ioStatsEntry.foreground), this.background.delta(ioStatsEntry.background));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof IoStatsEntry)) {
            return false;
        }
        IoStatsEntry ioStatsEntry = (IoStatsEntry) obj;
        return this.uid == ioStatsEntry.uid && this.runtimeMillis == ioStatsEntry.runtimeMillis && this.foreground.equals(ioStatsEntry.foreground) && this.background.equals(ioStatsEntry.background);
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.uid), Long.valueOf(this.runtimeMillis), this.foreground, this.background);
    }

    public String toString() {
        return String.format("uid = %d, runtime = %d, foreground = %s, background = %s", Integer.valueOf(this.uid), Long.valueOf(this.runtimeMillis), this.foreground, this.background);
    }

    public boolean representsSameMetrics(UidIoRecord uidIoRecord) {
        return uidIoRecord.uid == this.uid && uidIoRecord.foreground_rchar == this.foreground.bytesRead && uidIoRecord.foreground_wchar == this.foreground.bytesWritten && uidIoRecord.foreground_read_bytes == this.foreground.bytesReadFromStorage && uidIoRecord.foreground_write_bytes == this.foreground.bytesWrittenToStorage && uidIoRecord.foreground_fsync == this.foreground.fsyncCalls && uidIoRecord.background_rchar == this.background.bytesRead && uidIoRecord.background_wchar == this.background.bytesWritten && uidIoRecord.background_read_bytes == this.background.bytesReadFromStorage && uidIoRecord.background_write_bytes == this.background.bytesWrittenToStorage && uidIoRecord.background_fsync == this.background.fsyncCalls;
    }

    public static final class Metrics implements Parcelable {
        public static final Parcelable.Creator<Metrics> CREATOR = new Parcelable.Creator<Metrics>() { // from class: android.car.storagemonitoring.IoStatsEntry.Metrics.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Metrics createFromParcel(Parcel parcel) {
                return new Metrics(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Metrics[] newArray(int i) {
                return new Metrics[i];
            }
        };
        public final long bytesRead;
        public final long bytesReadFromStorage;
        public final long bytesWritten;
        public final long bytesWrittenToStorage;
        public final long fsyncCalls;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public Metrics(long j, long j2, long j3, long j4, long j5) {
            this.bytesRead = j;
            this.bytesWritten = j2;
            this.bytesReadFromStorage = j3;
            this.bytesWrittenToStorage = j4;
            this.fsyncCalls = j5;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(this.bytesRead);
            parcel.writeLong(this.bytesWritten);
            parcel.writeLong(this.bytesReadFromStorage);
            parcel.writeLong(this.bytesWrittenToStorage);
            parcel.writeLong(this.fsyncCalls);
        }

        public void writeToJson(JsonWriter jsonWriter) throws IOException {
            jsonWriter.beginObject();
            jsonWriter.name("bytesRead").value(this.bytesRead);
            jsonWriter.name("bytesWritten").value(this.bytesWritten);
            jsonWriter.name("bytesReadFromStorage").value(this.bytesReadFromStorage);
            jsonWriter.name("bytesWrittenToStorage").value(this.bytesWrittenToStorage);
            jsonWriter.name("fsyncCalls").value(this.fsyncCalls);
            jsonWriter.endObject();
        }

        public Metrics(Parcel parcel) {
            this.bytesRead = parcel.readLong();
            this.bytesWritten = parcel.readLong();
            this.bytesReadFromStorage = parcel.readLong();
            this.bytesWrittenToStorage = parcel.readLong();
            this.fsyncCalls = parcel.readLong();
        }

        public Metrics(JSONObject jSONObject) throws JSONException {
            this.bytesRead = jSONObject.getLong("bytesRead");
            this.bytesWritten = jSONObject.getLong("bytesWritten");
            this.bytesReadFromStorage = jSONObject.getLong("bytesReadFromStorage");
            this.bytesWrittenToStorage = jSONObject.getLong("bytesWrittenToStorage");
            this.fsyncCalls = jSONObject.getLong("fsyncCalls");
        }

        public Metrics delta(Metrics metrics) {
            return new Metrics(this.bytesRead - metrics.bytesRead, this.bytesWritten - metrics.bytesWritten, this.bytesReadFromStorage - metrics.bytesReadFromStorage, this.bytesWrittenToStorage - metrics.bytesWrittenToStorage, this.fsyncCalls - metrics.fsyncCalls);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Metrics)) {
                return false;
            }
            Metrics metrics = (Metrics) obj;
            return this.bytesRead == metrics.bytesRead && this.bytesWritten == metrics.bytesWritten && this.bytesReadFromStorage == metrics.bytesReadFromStorage && this.bytesWrittenToStorage == metrics.bytesWrittenToStorage && this.fsyncCalls == metrics.fsyncCalls;
        }

        public int hashCode() {
            return Objects.hash(Long.valueOf(this.bytesRead), Long.valueOf(this.bytesWritten), Long.valueOf(this.bytesReadFromStorage), Long.valueOf(this.bytesWrittenToStorage), Long.valueOf(this.fsyncCalls));
        }

        public String toString() {
            return String.format("bytesRead=%d, bytesWritten=%d, bytesReadFromStorage=%d, bytesWrittenToStorage=%d, fsyncCalls=%d", Long.valueOf(this.bytesRead), Long.valueOf(this.bytesWritten), Long.valueOf(this.bytesReadFromStorage), Long.valueOf(this.bytesWrittenToStorage), Long.valueOf(this.fsyncCalls));
        }
    }
}
