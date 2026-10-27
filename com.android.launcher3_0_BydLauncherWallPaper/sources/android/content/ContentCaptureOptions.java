package android.content;

import android.app.ActivityThread;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArraySet;
import android.util.Log;
import android.view.contentcapture.ContentCaptureManager;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public final class ContentCaptureOptions implements Parcelable {
    public static final Parcelable.Creator<ContentCaptureOptions> CREATOR = new Parcelable.Creator<ContentCaptureOptions>() { // from class: android.content.ContentCaptureOptions.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ContentCaptureOptions createFromParcel(Parcel parcel) {
            boolean z = parcel.readBoolean();
            int i = parcel.readInt();
            if (z) {
                return new ContentCaptureOptions(i);
            }
            return new ContentCaptureOptions(i, parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readArraySet(null));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ContentCaptureOptions[] newArray(int i) {
            return new ContentCaptureOptions[i];
        }
    };
    private static final String TAG = "ContentCaptureOptions";
    public final int idleFlushingFrequencyMs;
    public final boolean lite;
    public final int logHistorySize;
    public final int loggingLevel;
    public final int maxBufferSize;
    public final int textChangeFlushingFrequencyMs;
    public final ArraySet<ComponentName> whitelistedComponents;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ContentCaptureOptions(int i) {
        this(true, i, 0, 0, 0, 0, null);
    }

    public ContentCaptureOptions(int i, int i2, int i3, int i4, int i5, ArraySet<ComponentName> arraySet) {
        this(false, i, i2, i3, i4, i5, arraySet);
    }

    public ContentCaptureOptions(ArraySet<ComponentName> arraySet) {
        this(2, 500, 5000, 1000, 10, arraySet);
    }

    private ContentCaptureOptions(boolean z, int i, int i2, int i3, int i4, int i5, ArraySet<ComponentName> arraySet) {
        this.lite = z;
        this.loggingLevel = i;
        this.maxBufferSize = i2;
        this.idleFlushingFrequencyMs = i3;
        this.textChangeFlushingFrequencyMs = i4;
        this.logHistorySize = i5;
        this.whitelistedComponents = arraySet;
    }

    public static ContentCaptureOptions forWhitelistingItself() {
        ActivityThread activityThreadCurrentActivityThread = ActivityThread.currentActivityThread();
        if (activityThreadCurrentActivityThread == null) {
            throw new IllegalStateException("No ActivityThread");
        }
        String packageName = activityThreadCurrentActivityThread.getApplication().getPackageName();
        if (!"android.contentcaptureservice.cts".equals(packageName)) {
            Log.e(TAG, "forWhitelistingItself(): called by " + packageName);
            throw new SecurityException("Thou shall not pass!");
        }
        ContentCaptureOptions contentCaptureOptions = new ContentCaptureOptions((ArraySet<ComponentName>) null);
        Log.i(TAG, "forWhitelistingItself(" + packageName + "): " + contentCaptureOptions);
        return contentCaptureOptions;
    }

    public boolean isWhitelisted(Context context) {
        if (this.whitelistedComponents == null) {
            return true;
        }
        ContentCaptureManager.ContentCaptureClient contentCaptureClient = context.getContentCaptureClient();
        if (contentCaptureClient == null) {
            Log.w(TAG, "isWhitelisted(): no ContentCaptureClient on " + context);
            return false;
        }
        return this.whitelistedComponents.contains(contentCaptureClient.contentCaptureClientGetComponentName());
    }

    public String toString() {
        if (this.lite) {
            return "ContentCaptureOptions [loggingLevel=" + this.loggingLevel + " (lite)]";
        }
        StringBuilder sb = new StringBuilder("ContentCaptureOptions [");
        sb.append("loggingLevel=").append(this.loggingLevel).append(", maxBufferSize=").append(this.maxBufferSize).append(", idleFlushingFrequencyMs=").append(this.idleFlushingFrequencyMs).append(", textChangeFlushingFrequencyMs=").append(this.textChangeFlushingFrequencyMs).append(", logHistorySize=").append(this.logHistorySize);
        if (this.whitelistedComponents != null) {
            sb.append(", whitelisted=").append(this.whitelistedComponents);
        }
        return sb.append(']').toString();
    }

    public void dumpShort(PrintWriter printWriter) {
        printWriter.print("logLvl=");
        printWriter.print(this.loggingLevel);
        if (this.lite) {
            printWriter.print(", lite");
            return;
        }
        printWriter.print(", bufferSize=");
        printWriter.print(this.maxBufferSize);
        printWriter.print(", idle=");
        printWriter.print(this.idleFlushingFrequencyMs);
        printWriter.print(", textIdle=");
        printWriter.print(this.textChangeFlushingFrequencyMs);
        printWriter.print(", logSize=");
        printWriter.print(this.logHistorySize);
        if (this.whitelistedComponents != null) {
            printWriter.print(", whitelisted=");
            printWriter.print(this.whitelistedComponents);
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBoolean(this.lite);
        parcel.writeInt(this.loggingLevel);
        if (this.lite) {
            return;
        }
        parcel.writeInt(this.maxBufferSize);
        parcel.writeInt(this.idleFlushingFrequencyMs);
        parcel.writeInt(this.textChangeFlushingFrequencyMs);
        parcel.writeInt(this.logHistorySize);
        parcel.writeArraySet(this.whitelistedComponents);
    }
}
