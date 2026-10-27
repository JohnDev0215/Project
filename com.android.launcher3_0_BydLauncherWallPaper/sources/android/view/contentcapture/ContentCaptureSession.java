package android.view.contentcapture;

import android.database.sqlite.SQLiteGlobal;
import android.graphics.Insets;
import android.util.DebugUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewStructure;
import android.view.autofill.AutofillId;
import android.view.contentcapture.ViewNode;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.Preconditions;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.security.SecureRandom;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public abstract class ContentCaptureSession implements AutoCloseable {
    public static final int FLUSH_REASON_FULL = 1;
    public static final int FLUSH_REASON_IDLE_TIMEOUT = 5;
    public static final int FLUSH_REASON_SESSION_CONNECTED = 7;
    public static final int FLUSH_REASON_SESSION_FINISHED = 4;
    public static final int FLUSH_REASON_SESSION_STARTED = 3;
    public static final int FLUSH_REASON_TEXT_CHANGE_TIMEOUT = 6;
    public static final int FLUSH_REASON_VIEW_ROOT_ENTERED = 2;
    private static final SecureRandom ID_GENERATOR = new SecureRandom();
    private static final int INITIAL_CHILDREN_CAPACITY = 5;
    public static final int STATE_ACTIVE = 2;
    public static final int STATE_BY_APP = 64;
    public static final int STATE_DISABLED = 4;
    public static final int STATE_DUPLICATED_ID = 8;
    public static final int STATE_FLAG_SECURE = 32;
    public static final int STATE_INTERNAL_ERROR = 256;
    public static final int STATE_NOT_WHITELISTED = 512;
    public static final int STATE_NO_RESPONSE = 128;
    public static final int STATE_NO_SERVICE = 16;
    public static final int STATE_SERVICE_DIED = 1024;
    public static final int STATE_SERVICE_RESURRECTED = 4096;
    public static final int STATE_SERVICE_UPDATING = 2048;
    public static final int STATE_WAITING_FOR_SERVER = 1;
    private static final String TAG = "ContentCaptureSession";
    public static final int UNKNOWN_STATE = 0;
    private ArrayList<ContentCaptureSession> mChildren;
    private ContentCaptureContext mClientContext;
    private ContentCaptureSessionId mContentCaptureSessionId;
    private boolean mDestroyed;
    protected final int mId;
    private final Object mLock;
    private int mState;

    @Retention(RetentionPolicy.SOURCE)
    public @interface FlushReason {
    }

    abstract void flush(int i);

    abstract MainContentCaptureSession getMainCaptureSession();

    abstract void internalNotifySessionPaused();

    abstract void internalNotifySessionResumed();

    abstract void internalNotifyViewAppeared(ViewNode.ViewStructureImpl viewStructureImpl);

    abstract void internalNotifyViewDisappeared(AutofillId autofillId);

    abstract void internalNotifyViewInsetsChanged(Insets insets);

    abstract void internalNotifyViewTextChanged(AutofillId autofillId, CharSequence charSequence);

    public abstract void internalNotifyViewTreeEvent(boolean z);

    abstract ContentCaptureSession newChild(ContentCaptureContext contentCaptureContext);

    abstract void onDestroy();

    abstract void updateContentCaptureContext(ContentCaptureContext contentCaptureContext);

    protected ContentCaptureSession() {
        this(getRandomSessionId());
    }

    public ContentCaptureSession(int i) {
        this.mLock = new Object();
        this.mState = 0;
        Preconditions.checkArgument(i != 0);
        this.mId = i;
    }

    ContentCaptureSession(ContentCaptureContext contentCaptureContext) {
        this();
        this.mClientContext = (ContentCaptureContext) Preconditions.checkNotNull(contentCaptureContext);
    }

    public final ContentCaptureSessionId getContentCaptureSessionId() {
        if (this.mContentCaptureSessionId == null) {
            this.mContentCaptureSessionId = new ContentCaptureSessionId(this.mId);
        }
        return this.mContentCaptureSessionId;
    }

    public int getId() {
        return this.mId;
    }

    public final ContentCaptureSession createContentCaptureSession(ContentCaptureContext contentCaptureContext) {
        ContentCaptureSession contentCaptureSessionNewChild = newChild(contentCaptureContext);
        if (ContentCaptureHelper.sDebug) {
            Log.d(TAG, "createContentCaptureSession(" + contentCaptureContext + ": parent=" + this.mId + ", child=" + contentCaptureSessionNewChild.mId);
        }
        synchronized (this.mLock) {
            if (this.mChildren == null) {
                this.mChildren = new ArrayList<>(5);
            }
            this.mChildren.add(contentCaptureSessionNewChild);
        }
        return contentCaptureSessionNewChild;
    }

    public final void setContentCaptureContext(ContentCaptureContext contentCaptureContext) {
        this.mClientContext = contentCaptureContext;
        updateContentCaptureContext(contentCaptureContext);
    }

    public final ContentCaptureContext getContentCaptureContext() {
        return this.mClientContext;
    }

    public final void destroy() {
        synchronized (this.mLock) {
            if (this.mDestroyed) {
                if (ContentCaptureHelper.sDebug) {
                    Log.d(TAG, "destroy(" + this.mId + "): already destroyed");
                }
                return;
            }
            this.mDestroyed = true;
            if (ContentCaptureHelper.sVerbose) {
                Log.v(TAG, "destroy(): state=" + getStateAsString(this.mState) + ", mId=" + this.mId);
            }
            ArrayList<ContentCaptureSession> arrayList = this.mChildren;
            if (arrayList != null) {
                int size = arrayList.size();
                if (ContentCaptureHelper.sVerbose) {
                    Log.v(TAG, "Destroying " + size + " children first");
                }
                for (int i = 0; i < size; i++) {
                    try {
                        this.mChildren.get(i).destroy();
                    } catch (Exception e) {
                        Log.w(TAG, "exception destroying child session #" + i + ": " + e);
                    }
                }
            }
            try {
                flush(4);
            } finally {
                onDestroy();
            }
        }
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        destroy();
    }

    public final void notifyViewAppeared(ViewStructure viewStructure) {
        Preconditions.checkNotNull(viewStructure);
        if (isContentCaptureEnabled()) {
            if (!(viewStructure instanceof ViewNode.ViewStructureImpl)) {
                throw new IllegalArgumentException("Invalid node class: " + viewStructure.getClass());
            }
            internalNotifyViewAppeared((ViewNode.ViewStructureImpl) viewStructure);
        }
    }

    public final void notifyViewDisappeared(AutofillId autofillId) {
        Preconditions.checkNotNull(autofillId);
        if (isContentCaptureEnabled()) {
            internalNotifyViewDisappeared(autofillId);
        }
    }

    public final void notifyViewsDisappeared(AutofillId autofillId, long[] jArr) {
        Preconditions.checkArgument(autofillId.isNonVirtual(), "hostId cannot be virtual: %s", autofillId);
        Preconditions.checkArgument(!ArrayUtils.isEmpty(jArr), "virtual ids cannot be empty");
        if (isContentCaptureEnabled()) {
            for (long j : jArr) {
                internalNotifyViewDisappeared(new AutofillId(autofillId, j, this.mId));
            }
        }
    }

    public final void notifyViewTextChanged(AutofillId autofillId, CharSequence charSequence) {
        Preconditions.checkNotNull(autofillId);
        if (isContentCaptureEnabled()) {
            internalNotifyViewTextChanged(autofillId, charSequence);
        }
    }

    public final void notifyViewInsetsChanged(Insets insets) {
        Preconditions.checkNotNull(insets);
        if (isContentCaptureEnabled()) {
            internalNotifyViewInsetsChanged(insets);
        }
    }

    public final void notifySessionResumed() {
        if (isContentCaptureEnabled()) {
            internalNotifySessionResumed();
        }
    }

    public final void notifySessionPaused() {
        if (isContentCaptureEnabled()) {
            internalNotifySessionPaused();
        }
    }

    public final ViewStructure newViewStructure(View view) {
        return new ViewNode.ViewStructureImpl(view);
    }

    public AutofillId newAutofillId(AutofillId autofillId, long j) {
        Preconditions.checkNotNull(autofillId);
        Preconditions.checkArgument(autofillId.isNonVirtual(), "hostId cannot be virtual: %s", autofillId);
        return new AutofillId(autofillId, j, this.mId);
    }

    public final ViewStructure newVirtualViewStructure(AutofillId autofillId, long j) {
        return new ViewNode.ViewStructureImpl(autofillId, j, this.mId);
    }

    boolean isContentCaptureEnabled() {
        boolean z;
        synchronized (this.mLock) {
            z = !this.mDestroyed;
        }
        return z;
    }

    void dump(String str, PrintWriter printWriter) {
        printWriter.print(str);
        printWriter.print("id: ");
        printWriter.println(this.mId);
        if (this.mClientContext != null) {
            printWriter.print(str);
            this.mClientContext.dump(printWriter);
            printWriter.println();
        }
        synchronized (this.mLock) {
            printWriter.print(str);
            printWriter.print("destroyed: ");
            printWriter.println(this.mDestroyed);
            ArrayList<ContentCaptureSession> arrayList = this.mChildren;
            if (arrayList != null && !arrayList.isEmpty()) {
                String str2 = str + "  ";
                int size = this.mChildren.size();
                printWriter.print(str);
                printWriter.print("number children: ");
                printWriter.println(size);
                for (int i = 0; i < size; i++) {
                    ContentCaptureSession contentCaptureSession = this.mChildren.get(i);
                    printWriter.print(str);
                    printWriter.print(i);
                    printWriter.println(": ");
                    contentCaptureSession.dump(str2, printWriter);
                }
            }
        }
    }

    public String toString() {
        return Integer.toString(this.mId);
    }

    protected static String getStateAsString(int i) {
        return i + " (" + (i == 0 ? "UNKNOWN" : DebugUtils.flagsToString(ContentCaptureSession.class, "STATE_", i)) + ")";
    }

    public static String getFlushReasonAsString(int i) {
        switch (i) {
            case 1:
                return SQLiteGlobal.SYNC_MODE_FULL;
            case 2:
                return "VIEW_ROOT";
            case 3:
                return "STARTED";
            case 4:
                return "FINISHED";
            case 5:
                return "IDLE";
            case 6:
                return "TEXT_CHANGE";
            case 7:
                return "CONNECTED";
            default:
                return "UNKOWN-" + i;
        }
    }

    private static int getRandomSessionId() {
        int iNextInt;
        do {
            iNextInt = ID_GENERATOR.nextInt();
        } while (iNextInt == 0);
        return iNextInt;
    }
}
