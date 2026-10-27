package androidx.leanback.widget;

import android.view.View;
import androidx.leanback.widget.ItemAlignmentFacet;

/* JADX INFO: loaded from: classes3.dex */
class ItemAlignment {
    public final Axis horizontal;
    private Axis mMainAxis;
    private Axis mSecondAxis;
    private int mOrientation = 0;
    public final Axis vertical = new Axis(1);

    ItemAlignment() {
        Axis axis = new Axis(0);
        this.horizontal = axis;
        this.mMainAxis = axis;
        this.mSecondAxis = this.vertical;
    }

    static final class Axis extends ItemAlignmentFacet.ItemAlignmentDef {
        private int mOrientation;

        Axis(int orientation) {
            this.mOrientation = orientation;
        }

        public int getAlignmentPosition(View itemView) {
            return ItemAlignmentFacetHelper.getAlignmentPosition(itemView, this, this.mOrientation);
        }
    }

    public final Axis mainAxis() {
        return this.mMainAxis;
    }

    public final Axis secondAxis() {
        return this.mSecondAxis;
    }

    public final void setOrientation(int orientation) {
        this.mOrientation = orientation;
        if (orientation == 0) {
            this.mMainAxis = this.horizontal;
            this.mSecondAxis = this.vertical;
        } else {
            this.mMainAxis = this.vertical;
            this.mSecondAxis = this.horizontal;
        }
    }

    public final int getOrientation() {
        return this.mOrientation;
    }
}
