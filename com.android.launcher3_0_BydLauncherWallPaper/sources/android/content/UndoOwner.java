package android.content;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class UndoOwner {
    Object mData;
    final UndoManager mManager;
    int mOpCount;
    int mSavedIdx;
    int mStateSeq;
    final String mTag;

    UndoOwner(String str, UndoManager undoManager) {
        Objects.requireNonNull(str, "tag can't be null");
        Objects.requireNonNull(undoManager, "manager can't be null");
        this.mTag = str;
        this.mManager = undoManager;
    }

    public String getTag() {
        return this.mTag;
    }

    public Object getData() {
        return this.mData;
    }

    public String toString() {
        return "UndoOwner:[mTag=" + this.mTag + " mManager=" + this.mManager + " mData=" + this.mData + " mData=" + this.mData + " mOpCount=" + this.mOpCount + " mStateSeq=" + this.mStateSeq + " mSavedIdx=" + this.mSavedIdx + "]";
    }
}
