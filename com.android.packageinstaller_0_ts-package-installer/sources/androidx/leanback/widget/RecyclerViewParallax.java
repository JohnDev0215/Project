package androidx.leanback.widget;

import android.graphics.Rect;
import android.util.Property;
import android.view.View;
import androidx.leanback.widget.Parallax;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class RecyclerViewParallax extends Parallax<ChildPositionProperty> {
    boolean mIsVertical;
    RecyclerView mRecylerView;
    RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() { // from class: androidx.leanback.widget.RecyclerViewParallax.1
        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            RecyclerViewParallax.this.updateValues();
        }
    };
    View.OnLayoutChangeListener mOnLayoutChangeListener = new View.OnLayoutChangeListener() { // from class: androidx.leanback.widget.RecyclerViewParallax.2
        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int l, int t, int r, int b, int oldL, int oldT, int oldR, int oldB) {
            RecyclerViewParallax.this.updateValues();
        }
    };

    public static final class ChildPositionProperty extends Parallax.IntProperty {
        int mAdapterPosition;
        float mFraction;
        int mOffset;
        int mViewId;

        ChildPositionProperty(String name, int index) {
            super(name, index);
        }

        public ChildPositionProperty adapterPosition(int adapterPosition) {
            this.mAdapterPosition = adapterPosition;
            return this;
        }

        public ChildPositionProperty viewId(int viewId) {
            this.mViewId = viewId;
            return this;
        }

        public ChildPositionProperty offset(int offset) {
            this.mOffset = offset;
            return this;
        }

        public ChildPositionProperty fraction(float fraction) {
            this.mFraction = fraction;
            return this;
        }

        public int getAdapterPosition() {
            return this.mAdapterPosition;
        }

        public int getViewId() {
            return this.mViewId;
        }

        public int getOffset() {
            return this.mOffset;
        }

        public float getFraction() {
            return this.mFraction;
        }

        void updateValue(RecyclerViewParallax source) {
            RecyclerView recyclerView = source.mRecylerView;
            RecyclerView.ViewHolder viewHolder = recyclerView == null ? null : recyclerView.findViewHolderForAdapterPosition(this.mAdapterPosition);
            if (viewHolder == null) {
                if (recyclerView != null && recyclerView.getLayoutManager().getChildCount() != 0) {
                    View firstChild = recyclerView.getLayoutManager().getChildAt(0);
                    RecyclerView.ViewHolder vh = recyclerView.findContainingViewHolder(firstChild);
                    int firstPosition = vh.getAbsoluteAdapterPosition();
                    if (firstPosition < this.mAdapterPosition) {
                        source.setIntPropertyValue(getIndex(), Integer.MAX_VALUE);
                        return;
                    } else {
                        source.setIntPropertyValue(getIndex(), Integer.MIN_VALUE);
                        return;
                    }
                }
                source.setIntPropertyValue(getIndex(), Integer.MAX_VALUE);
                return;
            }
            View trackingView = viewHolder.itemView.findViewById(this.mViewId);
            if (trackingView == null) {
                return;
            }
            Rect rect = new Rect(0, 0, trackingView.getWidth(), trackingView.getHeight());
            recyclerView.offsetDescendantRectToMyCoords(trackingView, rect);
            float tx = 0.0f;
            float ty = 0.0f;
            while (trackingView != recyclerView && trackingView != null) {
                if (trackingView.getParent() != recyclerView || !recyclerView.isAnimating()) {
                    tx += trackingView.getTranslationX();
                    ty += trackingView.getTranslationY();
                }
                trackingView = (View) trackingView.getParent();
            }
            rect.offset((int) tx, (int) ty);
            if (source.mIsVertical) {
                source.setIntPropertyValue(getIndex(), rect.top + this.mOffset + ((int) (this.mFraction * rect.height())));
            } else {
                source.setIntPropertyValue(getIndex(), rect.left + this.mOffset + ((int) (this.mFraction * rect.width())));
            }
        }
    }

    @Override // androidx.leanback.widget.Parallax
    public ChildPositionProperty createProperty(String name, int index) {
        return new ChildPositionProperty(name, index);
    }

    @Override // androidx.leanback.widget.Parallax
    public float getMaxValue() {
        if (this.mRecylerView == null) {
            return 0.0f;
        }
        return this.mIsVertical ? r0.getHeight() : r0.getWidth();
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.mRecylerView;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            recyclerView2.removeOnScrollListener(this.mOnScrollListener);
            this.mRecylerView.removeOnLayoutChangeListener(this.mOnLayoutChangeListener);
        }
        this.mRecylerView = recyclerView;
        if (recyclerView != null) {
            recyclerView.getLayoutManager();
            RecyclerView.LayoutManager.Properties properties = RecyclerView.LayoutManager.getProperties(this.mRecylerView.getContext(), null, 0, 0);
            this.mIsVertical = properties.orientation == 1;
            this.mRecylerView.addOnScrollListener(this.mOnScrollListener);
            this.mRecylerView.addOnLayoutChangeListener(this.mOnLayoutChangeListener);
        }
    }

    @Override // androidx.leanback.widget.Parallax
    public void updateValues() {
        for (Property prop : getProperties()) {
            ((ChildPositionProperty) prop).updateValue(this);
        }
        super.updateValues();
    }

    public RecyclerView getRecyclerView() {
        return this.mRecylerView;
    }
}
