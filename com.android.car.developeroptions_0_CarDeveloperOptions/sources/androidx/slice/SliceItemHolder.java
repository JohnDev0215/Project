package androidx.slice;

import android.os.Parcelable;
import androidx.core.text.HtmlCompat;
import androidx.core.util.Pair;
import androidx.versionedparcelable.VersionedParcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class SliceItemHolder implements VersionedParcelable {
    public static HolderHandler sHandler;
    public static final Object sSerializeLock = new Object();
    Object mCallback;
    int mInt;
    long mLong;
    Parcelable mParcelable;
    private SliceItemPool mPool;
    String mStr;
    public VersionedParcelable mVersionedParcelable;

    public interface HolderHandler {
        void handle(SliceItemHolder sliceItemHolder, String str);
    }

    SliceItemHolder(SliceItemPool sliceItemPool) {
        this.mVersionedParcelable = null;
        this.mParcelable = null;
        this.mStr = null;
        this.mInt = 0;
        this.mLong = 0L;
        this.mPool = sliceItemPool;
    }

    public void release() {
        SliceItemPool sliceItemPool = this.mPool;
        if (sliceItemPool != null) {
            sliceItemPool.release(this);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:26:0x005f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public SliceItemHolder(java.lang.String r4, java.lang.Object r5, boolean r6) {
        /*
            Method dump skipped, instruction units count: 230
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.slice.SliceItemHolder.<init>(java.lang.String, java.lang.Object, boolean):void");
    }

    public Object getObj(String str) {
        HolderHandler holderHandler = sHandler;
        if (holderHandler != null) {
            holderHandler.handle(this, str);
        }
        switch (str) {
            case "action":
                if (this.mParcelable == null && this.mVersionedParcelable == null) {
                    return null;
                }
                Object obj = this.mParcelable;
                if (obj == null) {
                    obj = this.mCallback;
                }
                return new Pair(obj, (Slice) this.mVersionedParcelable);
            case "image":
            case "slice":
                return this.mVersionedParcelable;
            case "input":
                return this.mParcelable;
            case "text":
                String str2 = this.mStr;
                return (str2 == null || str2.length() == 0) ? "" : HtmlCompat.fromHtml(this.mStr, 0);
            case "int":
                return Integer.valueOf(this.mInt);
            case "long":
                return Long.valueOf(this.mLong);
            default:
                throw new IllegalArgumentException("Unrecognized format " + str);
        }
    }

    public static class SliceItemPool {
        private final ArrayList<SliceItemHolder> mCached = new ArrayList<>();

        public SliceItemHolder get() {
            if (this.mCached.size() > 0) {
                return this.mCached.remove(r1.size() - 1);
            }
            return new SliceItemHolder(this);
        }

        public void release(SliceItemHolder sliceItemHolder) {
            sliceItemHolder.mParcelable = null;
            sliceItemHolder.mCallback = null;
            sliceItemHolder.mVersionedParcelable = null;
            sliceItemHolder.mInt = 0;
            sliceItemHolder.mLong = 0L;
            sliceItemHolder.mStr = null;
            this.mCached.add(sliceItemHolder);
        }
    }
}
