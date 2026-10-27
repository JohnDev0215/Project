package android.view;

import android.graphics.Rect;
import com.android.internal.util.Preconditions;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
class GestureExclusionTracker {
    private boolean mGestureExclusionViewsChanged = false;
    private boolean mRootGestureExclusionRectsChanged = false;
    private List<Rect> mRootGestureExclusionRects = Collections.emptyList();
    private List<GestureExclusionViewInfo> mGestureExclusionViewInfos = new ArrayList();
    private List<Rect> mGestureExclusionRects = Collections.emptyList();

    GestureExclusionTracker() {
    }

    public void updateRectsForView(View view) {
        boolean z;
        Iterator<GestureExclusionViewInfo> it = this.mGestureExclusionViewInfos.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            GestureExclusionViewInfo next = it.next();
            View view2 = next.getView();
            if (view2 == null || !view2.isAttachedToWindow() || !view2.isAggregatedVisible()) {
                this.mGestureExclusionViewsChanged = true;
                it.remove();
            } else if (view2 == view) {
                next.mDirty = true;
                z = true;
                break;
            }
        }
        if (z || !view.isAttachedToWindow()) {
            return;
        }
        this.mGestureExclusionViewInfos.add(new GestureExclusionViewInfo(view));
        this.mGestureExclusionViewsChanged = true;
    }

    public List<Rect> computeChangedRects() {
        boolean z = this.mRootGestureExclusionRectsChanged;
        Iterator<GestureExclusionViewInfo> it = this.mGestureExclusionViewInfos.iterator();
        ArrayList arrayList = new ArrayList(this.mRootGestureExclusionRects);
        while (it.hasNext()) {
            GestureExclusionViewInfo next = it.next();
            int iUpdate = next.update();
            if (iUpdate == 0) {
                z = true;
            } else if (iUpdate != 1) {
                if (iUpdate == 2) {
                    this.mGestureExclusionViewsChanged = true;
                    it.remove();
                }
            }
            arrayList.addAll(next.mExclusionRects);
        }
        if (!z && !this.mGestureExclusionViewsChanged) {
            return null;
        }
        this.mGestureExclusionViewsChanged = false;
        this.mRootGestureExclusionRectsChanged = false;
        if (this.mGestureExclusionRects.equals(arrayList)) {
            return null;
        }
        this.mGestureExclusionRects = arrayList;
        return arrayList;
    }

    public void setRootSystemGestureExclusionRects(List<Rect> list) {
        Preconditions.checkNotNull(list, "rects must not be null");
        this.mRootGestureExclusionRects = list;
        this.mRootGestureExclusionRectsChanged = true;
    }

    public List<Rect> getRootSystemGestureExclusionRects() {
        return this.mRootGestureExclusionRects;
    }

    private static class GestureExclusionViewInfo {
        public static final int CHANGED = 0;
        public static final int GONE = 2;
        public static final int UNCHANGED = 1;
        boolean mDirty = true;
        List<Rect> mExclusionRects = Collections.emptyList();
        private final WeakReference<View> mView;

        GestureExclusionViewInfo(View view) {
            this.mView = new WeakReference<>(view);
        }

        public View getView() {
            return this.mView.get();
        }

        public int update() {
            View view = getView();
            if (view == null || !view.isAttachedToWindow() || !view.isAggregatedVisible()) {
                return 2;
            }
            List<Rect> systemGestureExclusionRects = view.getSystemGestureExclusionRects();
            ArrayList arrayList = new ArrayList(systemGestureExclusionRects.size());
            Iterator<Rect> it = systemGestureExclusionRects.iterator();
            while (it.hasNext()) {
                Rect rect = new Rect(it.next());
                ViewParent parent = view.getParent();
                if (parent != null && parent.getChildVisibleRect(view, rect, null)) {
                    arrayList.add(rect);
                }
            }
            if (this.mExclusionRects.equals(systemGestureExclusionRects)) {
                return 1;
            }
            this.mExclusionRects = arrayList;
            return 0;
        }
    }
}
