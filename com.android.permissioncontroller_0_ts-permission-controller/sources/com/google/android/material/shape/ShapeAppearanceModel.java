package com.google.android.material.shape;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import com.google.android.material.R;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes8.dex */
public class ShapeAppearanceModel {
    private EdgeTreatment bottomEdge;
    private CornerTreatment bottomLeftCorner;
    private CornerTreatment bottomRightCorner;
    private EdgeTreatment leftEdge;
    private final Set<OnChangedListener> onChangedListeners;
    private EdgeTreatment rightEdge;
    private EdgeTreatment topEdge;
    private CornerTreatment topLeftCorner;
    private CornerTreatment topRightCorner;

    public interface OnChangedListener {
        void onShapeAppearanceModelChanged();
    }

    public ShapeAppearanceModel() {
        this.onChangedListeners = new LinkedHashSet();
        setTopLeftCornerInternal(MaterialShapeUtils.createDefaultCornerTreatment());
        setTopRightCornerInternal(MaterialShapeUtils.createDefaultCornerTreatment());
        setBottomRightCornerInternal(MaterialShapeUtils.createDefaultCornerTreatment());
        setBottomLeftCornerInternal(MaterialShapeUtils.createDefaultCornerTreatment());
        setLeftEdgeInternal(MaterialShapeUtils.createDefaultEdgeTreatment());
        setTopEdgeInternal(MaterialShapeUtils.createDefaultEdgeTreatment());
        setRightEdgeInternal(MaterialShapeUtils.createDefaultEdgeTreatment());
        setBottomEdgeInternal(MaterialShapeUtils.createDefaultEdgeTreatment());
        onShapeAppearanceModelChanged();
    }

    public ShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel) {
        this.onChangedListeners = new LinkedHashSet();
        setTopLeftCornerInternal(shapeAppearanceModel.getTopLeftCorner().m13clone());
        setTopRightCornerInternal(shapeAppearanceModel.getTopRightCorner().m13clone());
        setBottomRightCornerInternal(shapeAppearanceModel.getBottomRightCorner().m13clone());
        setBottomLeftCornerInternal(shapeAppearanceModel.getBottomLeftCorner().m13clone());
        setLeftEdgeInternal(shapeAppearanceModel.getLeftEdge().m14clone());
        setTopEdgeInternal(shapeAppearanceModel.getTopEdge().m14clone());
        setRightEdgeInternal(shapeAppearanceModel.getRightEdge().m14clone());
        setBottomEdgeInternal(shapeAppearanceModel.getBottomEdge().m14clone());
    }

    public ShapeAppearanceModel(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, attrs, defStyleAttr, defStyleRes, 0);
    }

    public ShapeAppearanceModel(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, int defaultCornerSize) {
        Context context2 = context;
        this.onChangedListeners = new LinkedHashSet();
        TypedArray a = context2.obtainStyledAttributes(attrs, R.styleable.MaterialShape, defStyleAttr, defStyleRes);
        int shapeAppearanceResId = a.getResourceId(R.styleable.MaterialShape_shapeAppearance, 0);
        int shapeAppearanceOverlayResId = a.getResourceId(R.styleable.MaterialShape_shapeAppearanceOverlay, 0);
        a.recycle();
        if (shapeAppearanceOverlayResId != 0) {
            context2 = new ContextThemeWrapper(context2, shapeAppearanceResId);
            shapeAppearanceResId = shapeAppearanceOverlayResId;
        }
        TypedArray a2 = context2.obtainStyledAttributes(shapeAppearanceResId, R.styleable.ShapeAppearance);
        int cornerFamily = a2.getInt(R.styleable.ShapeAppearance_cornerFamily, 0);
        int cornerFamilyTopLeft = a2.getInt(R.styleable.ShapeAppearance_cornerFamilyTopLeft, cornerFamily);
        int cornerFamilyTopRight = a2.getInt(R.styleable.ShapeAppearance_cornerFamilyTopRight, cornerFamily);
        int cornerFamilyBottomRight = a2.getInt(R.styleable.ShapeAppearance_cornerFamilyBottomRight, cornerFamily);
        int cornerFamilyBottomLeft = a2.getInt(R.styleable.ShapeAppearance_cornerFamilyBottomLeft, cornerFamily);
        int cornerSize = a2.getDimensionPixelSize(R.styleable.ShapeAppearance_cornerSize, defaultCornerSize);
        int cornerSizeTopLeft = a2.getDimensionPixelSize(R.styleable.ShapeAppearance_cornerSizeTopLeft, cornerSize);
        int cornerSizeTopRight = a2.getDimensionPixelSize(R.styleable.ShapeAppearance_cornerSizeTopRight, cornerSize);
        int cornerSizeBottomRight = a2.getDimensionPixelSize(R.styleable.ShapeAppearance_cornerSizeBottomRight, cornerSize);
        int cornerSizeBottomLeft = a2.getDimensionPixelSize(R.styleable.ShapeAppearance_cornerSizeBottomLeft, cornerSize);
        setTopLeftCornerInternal(MaterialShapeUtils.createCornerTreatment(cornerFamilyTopLeft, cornerSizeTopLeft));
        setTopRightCornerInternal(MaterialShapeUtils.createCornerTreatment(cornerFamilyTopRight, cornerSizeTopRight));
        setBottomRightCornerInternal(MaterialShapeUtils.createCornerTreatment(cornerFamilyBottomRight, cornerSizeBottomRight));
        setBottomLeftCornerInternal(MaterialShapeUtils.createCornerTreatment(cornerFamilyBottomLeft, cornerSizeBottomLeft));
        setTopEdgeInternal(MaterialShapeUtils.createDefaultEdgeTreatment());
        setRightEdgeInternal(MaterialShapeUtils.createDefaultEdgeTreatment());
        setBottomEdgeInternal(MaterialShapeUtils.createDefaultEdgeTreatment());
        setLeftEdgeInternal(MaterialShapeUtils.createDefaultEdgeTreatment());
        a2.recycle();
    }

    public void setAllCorners(int cornerFamily, int cornerSize) {
        setAllCorners(MaterialShapeUtils.createCornerTreatment(cornerFamily, cornerSize));
    }

    public void setAllCorners(CornerTreatment cornerTreatment) {
        boolean changed = setTopLeftCornerInternal(cornerTreatment.m13clone());
        if (changed | setTopRightCornerInternal(cornerTreatment.m13clone()) | setBottomRightCornerInternal(cornerTreatment.m13clone()) | setBottomLeftCornerInternal(cornerTreatment.m13clone())) {
            onShapeAppearanceModelChanged();
        }
    }

    public void setCornerRadius(float cornerRadius) {
        setCornerRadii(cornerRadius, cornerRadius, cornerRadius, cornerRadius);
    }

    public void setCornerRadii(float topLeftCornerRadius, float topRightCornerRadius, float bottomRightCornerRadius, float bottomLeftCornerRadius) {
        boolean changed = setTopLeftCornerSizeInternal(topLeftCornerRadius);
        if (changed | setTopRightCornerSizeInternal(topRightCornerRadius) | setBottomRightCornerSizeInternal(bottomRightCornerRadius) | setBottomLeftCornerSizeInternal(bottomLeftCornerRadius)) {
            onShapeAppearanceModelChanged();
        }
    }

    private boolean setTopLeftCornerSizeInternal(float topLeftCornerSize) {
        if (this.topLeftCorner.cornerSize == topLeftCornerSize) {
            return false;
        }
        this.topLeftCorner.cornerSize = topLeftCornerSize;
        return true;
    }

    private boolean setTopRightCornerSizeInternal(float topRightCornerSize) {
        if (this.topRightCorner.cornerSize == topRightCornerSize) {
            return false;
        }
        this.topRightCorner.cornerSize = topRightCornerSize;
        return true;
    }

    private boolean setBottomRightCornerSizeInternal(float bottomRightCornerSize) {
        if (this.bottomRightCorner.cornerSize == bottomRightCornerSize) {
            return false;
        }
        this.bottomRightCorner.cornerSize = bottomRightCornerSize;
        return true;
    }

    private boolean setBottomLeftCornerSizeInternal(float bottomLeftCornerSize) {
        if (this.bottomLeftCorner.cornerSize == bottomLeftCornerSize) {
            return false;
        }
        this.bottomLeftCorner.cornerSize = bottomLeftCornerSize;
        return true;
    }

    public void setAllEdges(EdgeTreatment edgeTreatment) {
        boolean changed = setLeftEdgeInternal(edgeTreatment.m14clone());
        if (changed | setTopEdgeInternal(edgeTreatment.m14clone()) | setRightEdgeInternal(edgeTreatment.m14clone()) | setBottomEdgeInternal(edgeTreatment.m14clone())) {
            onShapeAppearanceModelChanged();
        }
    }

    public void setCornerTreatments(CornerTreatment topLeftCorner, CornerTreatment topRightCorner, CornerTreatment bottomRightCorner, CornerTreatment bottomLeftCorner) {
        boolean changed = setTopLeftCornerInternal(topLeftCorner);
        if (changed | setTopRightCornerInternal(topRightCorner) | setBottomRightCornerInternal(bottomRightCorner) | setBottomLeftCornerInternal(bottomLeftCorner)) {
            onShapeAppearanceModelChanged();
        }
    }

    public void setEdgeTreatments(EdgeTreatment leftEdge, EdgeTreatment topEdge, EdgeTreatment rightEdge, EdgeTreatment bottomEdge) {
        boolean changed = setLeftEdgeInternal(leftEdge);
        if (changed | setTopEdgeInternal(topEdge) | setRightEdgeInternal(rightEdge) | setBottomEdgeInternal(bottomEdge)) {
            onShapeAppearanceModelChanged();
        }
    }

    public void setTopLeftCorner(int cornerFamily, int cornerSize) {
        setTopLeftCorner(MaterialShapeUtils.createCornerTreatment(cornerFamily, cornerSize));
    }

    public void setTopLeftCorner(CornerTreatment topLeftCorner) {
        if (setTopLeftCornerInternal(topLeftCorner)) {
            onShapeAppearanceModelChanged();
        }
    }

    private boolean setTopLeftCornerInternal(CornerTreatment topLeftCorner) {
        if (this.topLeftCorner == topLeftCorner) {
            return false;
        }
        this.topLeftCorner = topLeftCorner;
        return true;
    }

    public CornerTreatment getTopLeftCorner() {
        return this.topLeftCorner;
    }

    public void setTopRightCorner(int cornerFamily, int cornerSize) {
        setTopRightCorner(MaterialShapeUtils.createCornerTreatment(cornerFamily, cornerSize));
    }

    public void setTopRightCorner(CornerTreatment topRightCorner) {
        if (setTopRightCornerInternal(topRightCorner)) {
            onShapeAppearanceModelChanged();
        }
    }

    private boolean setTopRightCornerInternal(CornerTreatment topRightCorner) {
        if (this.topRightCorner == topRightCorner) {
            return false;
        }
        this.topRightCorner = topRightCorner;
        return true;
    }

    public CornerTreatment getTopRightCorner() {
        return this.topRightCorner;
    }

    public void setBottomRightCorner(int cornerFamily, int cornerSize) {
        setBottomRightCorner(MaterialShapeUtils.createCornerTreatment(cornerFamily, cornerSize));
    }

    public void setBottomRightCorner(CornerTreatment bottomRightCorner) {
        if (setBottomRightCornerInternal(bottomRightCorner)) {
            onShapeAppearanceModelChanged();
        }
    }

    private boolean setBottomRightCornerInternal(CornerTreatment bottomRightCorner) {
        if (this.bottomRightCorner == bottomRightCorner) {
            return false;
        }
        this.bottomRightCorner = bottomRightCorner;
        return true;
    }

    public CornerTreatment getBottomRightCorner() {
        return this.bottomRightCorner;
    }

    public void setBottomLeftCorner(int cornerFamily, int cornerSize) {
        setBottomLeftCorner(MaterialShapeUtils.createCornerTreatment(cornerFamily, cornerSize));
    }

    public void setBottomLeftCorner(CornerTreatment bottomLeftCorner) {
        if (setBottomLeftCornerInternal(bottomLeftCorner)) {
            onShapeAppearanceModelChanged();
        }
    }

    private boolean setBottomLeftCornerInternal(CornerTreatment bottomLeftCorner) {
        if (this.bottomLeftCorner == bottomLeftCorner) {
            return false;
        }
        this.bottomLeftCorner = bottomLeftCorner;
        return true;
    }

    public CornerTreatment getBottomLeftCorner() {
        return this.bottomLeftCorner;
    }

    public void setLeftEdge(EdgeTreatment leftEdge) {
        if (setLeftEdgeInternal(leftEdge)) {
            onShapeAppearanceModelChanged();
        }
    }

    private boolean setLeftEdgeInternal(EdgeTreatment leftEdge) {
        if (this.leftEdge == leftEdge) {
            return false;
        }
        this.leftEdge = leftEdge;
        return true;
    }

    public EdgeTreatment getLeftEdge() {
        return this.leftEdge;
    }

    public void setTopEdge(EdgeTreatment topEdge) {
        if (setTopEdgeInternal(topEdge)) {
            onShapeAppearanceModelChanged();
        }
    }

    private boolean setTopEdgeInternal(EdgeTreatment topEdge) {
        if (this.topEdge == topEdge) {
            return false;
        }
        this.topEdge = topEdge;
        return true;
    }

    public EdgeTreatment getTopEdge() {
        return this.topEdge;
    }

    public void setRightEdge(EdgeTreatment rightEdge) {
        if (setRightEdgeInternal(rightEdge)) {
            onShapeAppearanceModelChanged();
        }
    }

    private boolean setRightEdgeInternal(EdgeTreatment rightEdge) {
        if (this.rightEdge == rightEdge) {
            return false;
        }
        this.rightEdge = rightEdge;
        return true;
    }

    public EdgeTreatment getRightEdge() {
        return this.rightEdge;
    }

    public void setBottomEdge(EdgeTreatment bottomEdge) {
        if (setBottomEdgeInternal(bottomEdge)) {
            onShapeAppearanceModelChanged();
        }
    }

    private boolean setBottomEdgeInternal(EdgeTreatment bottomEdge) {
        if (this.bottomEdge == bottomEdge) {
            return false;
        }
        this.bottomEdge = bottomEdge;
        return true;
    }

    public EdgeTreatment getBottomEdge() {
        return this.bottomEdge;
    }

    void addOnChangedListener(OnChangedListener onChangedListener) {
        this.onChangedListeners.add(onChangedListener);
    }

    void removeOnChangedListener(OnChangedListener onChangedListener) {
        this.onChangedListeners.remove(onChangedListener);
    }

    private void onShapeAppearanceModelChanged() {
        for (OnChangedListener onChangedListener : this.onChangedListeners) {
            if (onChangedListener != null) {
                onChangedListener.onShapeAppearanceModelChanged();
            }
        }
    }

    public boolean isRoundRect() {
        boolean hasDefaultEdges = this.leftEdge.getClass().equals(EdgeTreatment.class) && this.rightEdge.getClass().equals(EdgeTreatment.class) && this.topEdge.getClass().equals(EdgeTreatment.class) && this.bottomEdge.getClass().equals(EdgeTreatment.class);
        float cornerSize = this.topLeftCorner.getCornerSize();
        boolean cornersHaveSameSize = this.topRightCorner.getCornerSize() == cornerSize && this.bottomLeftCorner.getCornerSize() == cornerSize && this.bottomRightCorner.getCornerSize() == cornerSize;
        boolean hasRoundedCorners = (this.topRightCorner instanceof RoundedCornerTreatment) && (this.topLeftCorner instanceof RoundedCornerTreatment) && (this.bottomRightCorner instanceof RoundedCornerTreatment) && (this.bottomLeftCorner instanceof RoundedCornerTreatment);
        return hasDefaultEdges && cornersHaveSameSize && hasRoundedCorners;
    }
}
