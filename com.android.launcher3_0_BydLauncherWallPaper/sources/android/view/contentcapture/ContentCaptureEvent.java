package android.view.contentcapture;

import android.annotation.SystemApi;
import android.graphics.Insets;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.autofill.AutofillId;
import com.android.internal.util.Preconditions;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
@SystemApi
public final class ContentCaptureEvent implements Parcelable {
    public static final Parcelable.Creator<ContentCaptureEvent> CREATOR = new Parcelable.Creator<ContentCaptureEvent>() { // from class: android.view.contentcapture.ContentCaptureEvent.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ContentCaptureEvent createFromParcel(Parcel parcel) {
            int i = parcel.readInt();
            int i2 = parcel.readInt();
            ContentCaptureEvent contentCaptureEvent = new ContentCaptureEvent(i, i2, parcel.readLong());
            AutofillId autofillId = (AutofillId) parcel.readParcelable(null);
            if (autofillId != null) {
                contentCaptureEvent.setAutofillId(autofillId);
            }
            ArrayList<AutofillId> arrayListCreateTypedArrayList = parcel.createTypedArrayList(AutofillId.CREATOR);
            if (arrayListCreateTypedArrayList != null) {
                contentCaptureEvent.setAutofillIds(arrayListCreateTypedArrayList);
            }
            ViewNode fromParcel = ViewNode.readFromParcel(parcel);
            if (fromParcel != null) {
                contentCaptureEvent.setViewNode(fromParcel);
            }
            contentCaptureEvent.setText(parcel.readCharSequence());
            if (i2 == -1 || i2 == -2) {
                contentCaptureEvent.setParentSessionId(parcel.readInt());
            }
            if (i2 == -1 || i2 == 6) {
                contentCaptureEvent.setClientContext((ContentCaptureContext) parcel.readParcelable(null));
            }
            if (i2 == 9) {
                contentCaptureEvent.setInsets((Insets) parcel.readParcelable(null));
            }
            return contentCaptureEvent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ContentCaptureEvent[] newArray(int i) {
            return new ContentCaptureEvent[i];
        }
    };
    private static final String TAG = "ContentCaptureEvent";
    public static final int TYPE_CONTEXT_UPDATED = 6;
    public static final int TYPE_SESSION_FINISHED = -2;
    public static final int TYPE_SESSION_PAUSED = 8;
    public static final int TYPE_SESSION_RESUMED = 7;
    public static final int TYPE_SESSION_STARTED = -1;
    public static final int TYPE_VIEW_APPEARED = 1;
    public static final int TYPE_VIEW_DISAPPEARED = 2;
    public static final int TYPE_VIEW_INSETS_CHANGED = 9;
    public static final int TYPE_VIEW_TEXT_CHANGED = 3;
    public static final int TYPE_VIEW_TREE_APPEARED = 5;
    public static final int TYPE_VIEW_TREE_APPEARING = 4;
    private ContentCaptureContext mClientContext;
    private final long mEventTime;
    private AutofillId mId;
    private ArrayList<AutofillId> mIds;
    private Insets mInsets;
    private ViewNode mNode;
    private int mParentSessionId;
    private final int mSessionId;
    private CharSequence mText;
    private final int mType;

    @Retention(RetentionPolicy.SOURCE)
    public @interface EventType {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ContentCaptureEvent(int i, int i2, long j) {
        this.mParentSessionId = 0;
        this.mSessionId = i;
        this.mType = i2;
        this.mEventTime = j;
    }

    public ContentCaptureEvent(int i, int i2) {
        this(i, i2, System.currentTimeMillis());
    }

    public ContentCaptureEvent setAutofillId(AutofillId autofillId) {
        this.mId = (AutofillId) Preconditions.checkNotNull(autofillId);
        return this;
    }

    public ContentCaptureEvent setAutofillIds(ArrayList<AutofillId> arrayList) {
        this.mIds = (ArrayList) Preconditions.checkNotNull(arrayList);
        return this;
    }

    public ContentCaptureEvent addAutofillId(AutofillId autofillId) {
        Preconditions.checkNotNull(autofillId);
        if (this.mIds == null) {
            ArrayList<AutofillId> arrayList = new ArrayList<>();
            this.mIds = arrayList;
            AutofillId autofillId2 = this.mId;
            if (autofillId2 == null) {
                Log.w(TAG, "addAutofillId(" + autofillId + ") called without an initial id");
            } else {
                arrayList.add(autofillId2);
                this.mId = null;
            }
        }
        this.mIds.add(autofillId);
        return this;
    }

    public ContentCaptureEvent setParentSessionId(int i) {
        this.mParentSessionId = i;
        return this;
    }

    public ContentCaptureEvent setClientContext(ContentCaptureContext contentCaptureContext) {
        this.mClientContext = contentCaptureContext;
        return this;
    }

    public int getSessionId() {
        return this.mSessionId;
    }

    public int getParentSessionId() {
        return this.mParentSessionId;
    }

    public ContentCaptureContext getContentCaptureContext() {
        return this.mClientContext;
    }

    public ContentCaptureEvent setViewNode(ViewNode viewNode) {
        this.mNode = (ViewNode) Preconditions.checkNotNull(viewNode);
        return this;
    }

    public ContentCaptureEvent setText(CharSequence charSequence) {
        this.mText = charSequence;
        return this;
    }

    public ContentCaptureEvent setInsets(Insets insets) {
        this.mInsets = insets;
        return this;
    }

    public int getType() {
        return this.mType;
    }

    public long getEventTime() {
        return this.mEventTime;
    }

    public ViewNode getViewNode() {
        return this.mNode;
    }

    public AutofillId getId() {
        return this.mId;
    }

    public List<AutofillId> getIds() {
        return this.mIds;
    }

    public CharSequence getText() {
        return this.mText;
    }

    public Insets getInsets() {
        return this.mInsets;
    }

    public void mergeEvent(ContentCaptureEvent contentCaptureEvent) {
        Preconditions.checkNotNull(contentCaptureEvent);
        int type = contentCaptureEvent.getType();
        if (this.mType != type) {
            Log.e(TAG, "mergeEvent(" + getTypeAsString(type) + ") cannot be merged with different eventType=" + getTypeAsString(this.mType));
            return;
        }
        if (type != 2) {
            if (type == 3) {
                setText(contentCaptureEvent.getText());
                return;
            } else {
                Log.e(TAG, "mergeEvent(" + getTypeAsString(type) + ") does not support this event type.");
                return;
            }
        }
        List<AutofillId> ids = contentCaptureEvent.getIds();
        AutofillId id = contentCaptureEvent.getId();
        if (ids == null) {
            if (id != null) {
                addAutofillId(id);
                return;
            }
            throw new IllegalArgumentException("mergeEvent(): got TYPE_VIEW_DISAPPEARED event with neither id or ids: " + contentCaptureEvent);
        }
        if (id != null) {
            Log.w(TAG, "got TYPE_VIEW_DISAPPEARED event with both id and ids: " + contentCaptureEvent);
        }
        for (int i = 0; i < ids.size(); i++) {
            addAutofillId(ids.get(i));
        }
    }

    public void dump(PrintWriter printWriter) {
        printWriter.print("type=");
        printWriter.print(getTypeAsString(this.mType));
        printWriter.print(", time=");
        printWriter.print(this.mEventTime);
        if (this.mId != null) {
            printWriter.print(", id=");
            printWriter.print(this.mId);
        }
        if (this.mIds != null) {
            printWriter.print(", ids=");
            printWriter.print(this.mIds);
        }
        if (this.mNode != null) {
            printWriter.print(", mNode.id=");
            printWriter.print(this.mNode.getAutofillId());
        }
        if (this.mSessionId != 0) {
            printWriter.print(", sessionId=");
            printWriter.print(this.mSessionId);
        }
        if (this.mParentSessionId != 0) {
            printWriter.print(", parentSessionId=");
            printWriter.print(this.mParentSessionId);
        }
        if (this.mText != null) {
            printWriter.print(", text=");
            printWriter.println(ContentCaptureHelper.getSanitizedString(this.mText));
        }
        if (this.mClientContext != null) {
            printWriter.print(", context=");
            this.mClientContext.dump(printWriter);
            printWriter.println();
        }
        if (this.mInsets != null) {
            printWriter.print(", insets=");
            printWriter.println(this.mInsets);
        }
    }

    public String toString() {
        StringBuilder sbAppend = new StringBuilder("ContentCaptureEvent[type=").append(getTypeAsString(this.mType));
        sbAppend.append(", session=").append(this.mSessionId);
        if (this.mType == -1 && this.mParentSessionId != 0) {
            sbAppend.append(", parent=").append(this.mParentSessionId);
        }
        if (this.mId != null) {
            sbAppend.append(", id=").append(this.mId);
        }
        if (this.mIds != null) {
            sbAppend.append(", ids=").append(this.mIds);
        }
        ViewNode viewNode = this.mNode;
        if (viewNode != null) {
            String className = viewNode.getClassName();
            if (this.mNode != null) {
                sbAppend.append(", class=").append(className);
            }
            sbAppend.append(", id=").append(this.mNode.getAutofillId());
        }
        if (this.mText != null) {
            sbAppend.append(", text=").append(ContentCaptureHelper.getSanitizedString(this.mText));
        }
        if (this.mClientContext != null) {
            sbAppend.append(", context=").append(this.mClientContext);
        }
        if (this.mInsets != null) {
            sbAppend.append(", insets=").append(this.mInsets);
        }
        return sbAppend.append(']').toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mSessionId);
        parcel.writeInt(this.mType);
        parcel.writeLong(this.mEventTime);
        parcel.writeParcelable(this.mId, i);
        parcel.writeTypedList(this.mIds);
        ViewNode.writeToParcel(parcel, this.mNode, i);
        parcel.writeCharSequence(this.mText);
        int i2 = this.mType;
        if (i2 == -1 || i2 == -2) {
            parcel.writeInt(this.mParentSessionId);
        }
        int i3 = this.mType;
        if (i3 == -1 || i3 == 6) {
            parcel.writeParcelable(this.mClientContext, i);
        }
        if (this.mType == 9) {
            parcel.writeParcelable(this.mInsets, i);
        }
    }

    public static String getTypeAsString(int i) {
        switch (i) {
            case -2:
                return "SESSION_FINISHED";
            case -1:
                return "SESSION_STARTED";
            case 0:
            default:
                return "UKNOWN_TYPE: " + i;
            case 1:
                return "VIEW_APPEARED";
            case 2:
                return "VIEW_DISAPPEARED";
            case 3:
                return "VIEW_TEXT_CHANGED";
            case 4:
                return "VIEW_TREE_APPEARING";
            case 5:
                return "VIEW_TREE_APPEARED";
            case 6:
                return "CONTEXT_UPDATED";
            case 7:
                return "SESSION_RESUMED";
            case 8:
                return "SESSION_PAUSED";
            case 9:
                return "VIEW_INSETS_CHANGED";
        }
    }
}
