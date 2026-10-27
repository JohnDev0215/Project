package androidx.leanback.widget;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.leanback.widget.GridLayoutManager;
import androidx.leanback.widget.ItemAlignmentFacet;

/* JADX INFO: loaded from: classes.dex */
class ItemAlignmentFacetHelper {
    private static Rect sRect = new Rect();

    static int getAlignmentPosition(View itemView, ItemAlignmentFacet.ItemAlignmentDef facet, int orientation) {
        int alignPos;
        int alignPos2;
        GridLayoutManager.LayoutParams p = (GridLayoutManager.LayoutParams) itemView.getLayoutParams();
        View view = itemView;
        if (facet.mViewId != 0 && (view = itemView.findViewById(facet.mViewId)) == null) {
            view = itemView;
        }
        int alignPos3 = facet.mOffset;
        if (orientation == 0) {
            if (itemView.getLayoutDirection() == 1) {
                int alignPos4 = (view == itemView ? p.getOpticalWidth(view) : view.getWidth()) - alignPos3;
                if (facet.mOffsetWithPadding) {
                    if (facet.mOffsetPercent == 0.0f) {
                        alignPos4 -= view.getPaddingRight();
                    } else if (facet.mOffsetPercent == 100.0f) {
                        alignPos4 += view.getPaddingLeft();
                    }
                }
                if (facet.mOffsetPercent != -1.0f) {
                    alignPos4 -= (int) (((view == itemView ? p.getOpticalWidth(view) : view.getWidth()) * facet.mOffsetPercent) / 100.0f);
                }
                if (itemView != view) {
                    sRect.right = alignPos4;
                    ((ViewGroup) itemView).offsetDescendantRectToMyCoords(view, sRect);
                    return sRect.right + p.getOpticalRightInset();
                }
                return alignPos4;
            }
            if (facet.mOffsetWithPadding) {
                if (facet.mOffsetPercent == 0.0f) {
                    alignPos3 += view.getPaddingLeft();
                } else if (facet.mOffsetPercent == 100.0f) {
                    alignPos3 -= view.getPaddingRight();
                }
            }
            if (facet.mOffsetPercent != -1.0f) {
                alignPos2 = alignPos3 + ((int) (((view == itemView ? p.getOpticalWidth(view) : view.getWidth()) * facet.mOffsetPercent) / 100.0f));
            } else {
                alignPos2 = alignPos3;
            }
            if (itemView != view) {
                sRect.left = alignPos2;
                ((ViewGroup) itemView).offsetDescendantRectToMyCoords(view, sRect);
                return sRect.left - p.getOpticalLeftInset();
            }
            return alignPos2;
        }
        if (facet.mOffsetWithPadding) {
            if (facet.mOffsetPercent == 0.0f) {
                alignPos3 += view.getPaddingTop();
            } else if (facet.mOffsetPercent == 100.0f) {
                alignPos3 -= view.getPaddingBottom();
            }
        }
        if (facet.mOffsetPercent != -1.0f) {
            alignPos3 += (int) (((view == itemView ? p.getOpticalHeight(view) : view.getHeight()) * facet.mOffsetPercent) / 100.0f);
        }
        if (itemView == view) {
            alignPos = alignPos3;
        } else {
            sRect.top = alignPos3;
            ((ViewGroup) itemView).offsetDescendantRectToMyCoords(view, sRect);
            alignPos = sRect.top - p.getOpticalTopInset();
        }
        if (facet.isAlignedToTextViewBaseLine()) {
            return alignPos + view.getBaseline();
        }
        return alignPos;
    }

    private ItemAlignmentFacetHelper() {
    }
}
