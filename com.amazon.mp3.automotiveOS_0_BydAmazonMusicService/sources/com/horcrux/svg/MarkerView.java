package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: loaded from: classes7.dex */
@SuppressLint({"ViewConstructor"})
class MarkerView extends GroupView {
    String mAlign;
    private SVGLength mMarkerHeight;
    private String mMarkerUnits;
    private SVGLength mMarkerWidth;
    int mMeetOrSlice;
    private float mMinX;
    private float mMinY;
    private String mOrient;
    private SVGLength mRefX;
    private SVGLength mRefY;
    private float mVbHeight;
    private float mVbWidth;
    Matrix markerTransform;

    public MarkerView(ReactContext reactContext) {
        super(reactContext);
        this.markerTransform = new Matrix();
    }

    public void setRefX(Dynamic dynamic) {
        this.mRefX = SVGLength.from(dynamic);
        invalidate();
    }

    public void setRefX(String str) {
        this.mRefX = SVGLength.from(str);
        invalidate();
    }

    public void setRefX(Double d) {
        this.mRefX = SVGLength.from(d);
        invalidate();
    }

    public void setRefY(Dynamic dynamic) {
        this.mRefY = SVGLength.from(dynamic);
        invalidate();
    }

    public void setRefY(String str) {
        this.mRefY = SVGLength.from(str);
        invalidate();
    }

    public void setRefY(Double d) {
        this.mRefY = SVGLength.from(d);
        invalidate();
    }

    public void setMarkerWidth(Dynamic dynamic) {
        this.mMarkerWidth = SVGLength.from(dynamic);
        invalidate();
    }

    public void setMarkerWidth(String str) {
        this.mMarkerWidth = SVGLength.from(str);
        invalidate();
    }

    public void setMarkerWidth(Double d) {
        this.mMarkerWidth = SVGLength.from(d);
        invalidate();
    }

    public void setMarkerHeight(Dynamic dynamic) {
        this.mMarkerHeight = SVGLength.from(dynamic);
        invalidate();
    }

    public void setMarkerHeight(String str) {
        this.mMarkerHeight = SVGLength.from(str);
        invalidate();
    }

    public void setMarkerHeight(Double d) {
        this.mMarkerHeight = SVGLength.from(d);
        invalidate();
    }

    public void setMarkerUnits(String str) {
        this.mMarkerUnits = str;
        invalidate();
    }

    public void setOrient(String str) {
        this.mOrient = str;
        invalidate();
    }

    public void setMinX(float f) {
        this.mMinX = f;
        invalidate();
    }

    public void setMinY(float f) {
        this.mMinY = f;
        invalidate();
    }

    public void setVbWidth(float f) {
        this.mVbWidth = f;
        invalidate();
    }

    public void setVbHeight(float f) {
        this.mVbHeight = f;
        invalidate();
    }

    public void setAlign(String str) {
        this.mAlign = str;
        invalidate();
    }

    public void setMeetOrSlice(int i) {
        this.mMeetOrSlice = i;
        invalidate();
    }

    @Override // com.horcrux.svg.GroupView, com.horcrux.svg.VirtualView
    void saveDefinition() {
        if (this.mName != null) {
            getSvgView().defineMarker(this, this.mName);
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                if (childAt instanceof VirtualView) {
                    ((VirtualView) childAt).saveDefinition();
                }
            }
        }
    }

    void renderMarker(Canvas canvas, Paint paint, float f, RNSVGMarkerPosition rNSVGMarkerPosition, float f2) {
        int iSaveAndSetupCanvas = saveAndSetupCanvas(canvas, this.mCTM);
        this.markerTransform.reset();
        Point point = rNSVGMarkerPosition.origin;
        Matrix matrix = this.markerTransform;
        float f3 = (float) point.x;
        float f4 = this.mScale;
        matrix.setTranslate(f3 * f4, ((float) point.y) * f4);
        double d = DebugKt.DEBUG_PROPERTY_VALUE_AUTO.equals(this.mOrient) ? -1.0d : Double.parseDouble(this.mOrient);
        if (d == -1.0d) {
            d = rNSVGMarkerPosition.angle;
        }
        this.markerTransform.preRotate(((float) d) + 180.0f);
        if ("strokeWidth".equals(this.mMarkerUnits)) {
            this.markerTransform.preScale(f2, f2);
        }
        RectF rectF = new RectF(0.0f, 0.0f, (float) (relativeOnWidth(this.mMarkerWidth) / ((double) this.mScale)), (float) (relativeOnHeight(this.mMarkerHeight) / ((double) this.mScale)));
        if (this.mAlign != null) {
            float f5 = this.mMinX;
            float f6 = this.mScale;
            float f7 = this.mMinY;
            Matrix transform = ViewBox.getTransform(new RectF(f5 * f6, f7 * f6, (f5 + this.mVbWidth) * f6, (f7 + this.mVbHeight) * f6), rectF, this.mAlign, this.mMeetOrSlice);
            float[] fArr = new float[9];
            transform.getValues(fArr);
            this.markerTransform.preScale(fArr[0], fArr[4]);
        }
        this.markerTransform.preTranslate((float) (-relativeOnWidth(this.mRefX)), (float) (-relativeOnHeight(this.mRefY)));
        canvas.concat(this.markerTransform);
        drawGroup(canvas, paint, f);
        restoreCanvas(canvas, iSaveAndSetupCanvas);
    }
}
