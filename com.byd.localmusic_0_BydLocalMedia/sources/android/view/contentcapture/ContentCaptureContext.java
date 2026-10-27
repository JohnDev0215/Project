package android.view.contentcapture;

import android.annotation.SystemApi;
import android.content.ComponentName;
import android.content.LocusId;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes3.dex */
public final class ContentCaptureContext implements Parcelable {
    public static final Parcelable.Creator<ContentCaptureContext> CREATOR = new Parcelable.Creator<ContentCaptureContext>() { // from class: android.view.contentcapture.ContentCaptureContext.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ContentCaptureContext createFromParcel(Parcel parcel) {
            ContentCaptureContext contentCaptureContext;
            if (parcel.readInt() == 1) {
                LocusId locusId = (LocusId) parcel.readParcelable(null);
                Bundle bundle = parcel.readBundle();
                Builder builder = new Builder(locusId);
                if (bundle != null) {
                    builder.setExtras(bundle);
                }
                contentCaptureContext = new ContentCaptureContext(builder);
            } else {
                contentCaptureContext = null;
            }
            ComponentName componentName = (ComponentName) parcel.readParcelable(null);
            return componentName == null ? contentCaptureContext : new ContentCaptureContext(contentCaptureContext, componentName, parcel.readInt(), parcel.readInt(), parcel.readInt());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ContentCaptureContext[] newArray(int i) {
            return new ContentCaptureContext[i];
        }
    };

    @SystemApi
    public static final int FLAG_DISABLED_BY_APP = 1;

    @SystemApi
    public static final int FLAG_DISABLED_BY_FLAG_SECURE = 2;

    @SystemApi
    public static final int FLAG_RECONNECTED = 4;
    private final ComponentName mComponentName;
    private final int mDisplayId;
    private final Bundle mExtras;
    private final int mFlags;
    private final boolean mHasClientContext;
    private final LocusId mId;
    private int mParentSessionId;
    private final int mTaskId;

    @Retention(RetentionPolicy.SOURCE)
    @interface ContextCreationFlags {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ContentCaptureContext(ContentCaptureContext contentCaptureContext, ComponentName componentName, int i, int i2, int i3) {
        this.mParentSessionId = 0;
        if (contentCaptureContext != null) {
            this.mHasClientContext = true;
            this.mExtras = contentCaptureContext.mExtras;
            this.mId = contentCaptureContext.mId;
        } else {
            this.mHasClientContext = false;
            this.mExtras = null;
            this.mId = null;
        }
        this.mComponentName = (ComponentName) Preconditions.checkNotNull(componentName);
        this.mTaskId = i;
        this.mDisplayId = i2;
        this.mFlags = i3;
    }

    private ContentCaptureContext(Builder builder) {
        this.mParentSessionId = 0;
        this.mHasClientContext = true;
        this.mExtras = builder.mExtras;
        this.mId = builder.mId;
        this.mComponentName = null;
        this.mFlags = 0;
        this.mTaskId = 0;
        this.mDisplayId = -1;
    }

    public ContentCaptureContext(ContentCaptureContext contentCaptureContext, int i) {
        this.mParentSessionId = 0;
        this.mHasClientContext = contentCaptureContext.mHasClientContext;
        this.mExtras = contentCaptureContext.mExtras;
        this.mId = contentCaptureContext.mId;
        this.mComponentName = contentCaptureContext.mComponentName;
        this.mTaskId = contentCaptureContext.mTaskId;
        this.mFlags = i | contentCaptureContext.mFlags;
        this.mDisplayId = contentCaptureContext.mDisplayId;
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

    public LocusId getLocusId() {
        return this.mId;
    }

    @SystemApi
    public int getTaskId() {
        return this.mTaskId;
    }

    @SystemApi
    public ComponentName getActivityComponent() {
        return this.mComponentName;
    }

    @SystemApi
    public ContentCaptureSessionId getParentSessionId() {
        if (this.mParentSessionId == 0) {
            return null;
        }
        return new ContentCaptureSessionId(this.mParentSessionId);
    }

    public void setParentSessionId(int i) {
        this.mParentSessionId = i;
    }

    @SystemApi
    public int getDisplayId() {
        return this.mDisplayId;
    }

    @SystemApi
    public int getFlags() {
        return this.mFlags;
    }

    public static ContentCaptureContext forLocusId(String str) {
        return new Builder(new LocusId(str)).build();
    }

    public static final class Builder {
        private boolean mDestroyed;
        private Bundle mExtras;
        private final LocusId mId;

        public Builder(LocusId locusId) {
            this.mId = (LocusId) Preconditions.checkNotNull(locusId);
        }

        public Builder setExtras(Bundle bundle) {
            this.mExtras = (Bundle) Preconditions.checkNotNull(bundle);
            throwIfDestroyed();
            return this;
        }

        public ContentCaptureContext build() {
            throwIfDestroyed();
            this.mDestroyed = true;
            return new ContentCaptureContext(this);
        }

        private void throwIfDestroyed() {
            Preconditions.checkState(!this.mDestroyed, "Already called #build()");
        }
    }

    public void dump(PrintWriter printWriter) {
        if (this.mComponentName != null) {
            printWriter.print("activity=");
            printWriter.print(this.mComponentName.flattenToShortString());
        }
        if (this.mId != null) {
            printWriter.print(", id=");
            this.mId.dump(printWriter);
        }
        printWriter.print(", taskId=");
        printWriter.print(this.mTaskId);
        printWriter.print(", displayId=");
        printWriter.print(this.mDisplayId);
        if (this.mParentSessionId != 0) {
            printWriter.print(", parentId=");
            printWriter.print(this.mParentSessionId);
        }
        if (this.mFlags > 0) {
            printWriter.print(", flags=");
            printWriter.print(this.mFlags);
        }
        if (this.mExtras != null) {
            printWriter.print(", hasExtras");
        }
    }

    private boolean fromServer() {
        return this.mComponentName != null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Context[");
        if (fromServer()) {
            sb.append("act=");
            sb.append(ComponentName.flattenToShortString(this.mComponentName));
            sb.append(", taskId=");
            sb.append(this.mTaskId);
            sb.append(", displayId=");
            sb.append(this.mDisplayId);
            sb.append(", flags=");
            sb.append(this.mFlags);
        } else {
            sb.append("id=");
            sb.append(this.mId);
            if (this.mExtras != null) {
                sb.append(", hasExtras");
            }
        }
        if (this.mParentSessionId != 0) {
            sb.append(", parentId=");
            sb.append(this.mParentSessionId);
        }
        sb.append(']');
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mHasClientContext ? 1 : 0);
        if (this.mHasClientContext) {
            parcel.writeParcelable(this.mId, i);
            parcel.writeBundle(this.mExtras);
        }
        parcel.writeParcelable(this.mComponentName, i);
        if (fromServer()) {
            parcel.writeInt(this.mTaskId);
            parcel.writeInt(this.mDisplayId);
            parcel.writeInt(this.mFlags);
        }
    }
}
