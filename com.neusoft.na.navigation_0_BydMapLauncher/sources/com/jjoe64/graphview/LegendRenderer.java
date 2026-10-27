package com.jjoe64.graphview;

import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.TypedValue;
import androidx.core.view.ViewCompat;
import com.jjoe64.graphview.series.Series;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class LegendRenderer {
    private int cachedLegendWidth;
    private final GraphView mGraphView;
    private boolean mIsVisible = false;
    private Paint mPaint;
    private Styles mStyles;

    public enum LegendAlign {
        TOP,
        MIDDLE,
        BOTTOM
    }

    private final class Styles {
        LegendAlign align;
        int backgroundColor;
        Point fixedPosition;
        int margin;
        int padding;
        int spacing;
        int textColor;
        float textSize;
        int width;

        private Styles() {
        }

        /* synthetic */ Styles(LegendRenderer legendRenderer, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public LegendRenderer(GraphView graphView) {
        this.mGraphView = graphView;
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setTextAlign(Paint.Align.LEFT);
        this.mStyles = new Styles(this, null);
        this.cachedLegendWidth = 0;
        resetStyles();
    }

    public void resetStyles() {
        this.mStyles.align = LegendAlign.MIDDLE;
        this.mStyles.textSize = this.mGraphView.getGridLabelRenderer().getTextSize();
        Styles styles = this.mStyles;
        styles.spacing = (int) (styles.textSize / 5.0f);
        Styles styles2 = this.mStyles;
        styles2.padding = (int) (styles2.textSize / 2.0f);
        this.mStyles.width = 0;
        this.mStyles.backgroundColor = Color.argb(180, 100, 100, 100);
        Styles styles3 = this.mStyles;
        styles3.margin = (int) (styles3.textSize / 5.0f);
        TypedValue typedValue = new TypedValue();
        this.mGraphView.getContext().getTheme().resolveAttribute(android.R.attr.textAppearanceSmall, typedValue, true);
        int i = ViewCompat.MEASURED_STATE_MASK;
        try {
            TypedArray typedArrayObtainStyledAttributes = this.mGraphView.getContext().obtainStyledAttributes(typedValue.data, new int[]{android.R.attr.textColorPrimary});
            int color = typedArrayObtainStyledAttributes.getColor(0, ViewCompat.MEASURED_STATE_MASK);
            typedArrayObtainStyledAttributes.recycle();
            i = color;
        } catch (Exception unused) {
        }
        this.mStyles.textColor = i;
        this.cachedLegendWidth = 0;
    }

    public void draw(Canvas canvas) {
        float graphContentTop;
        float graphContentTop2;
        float graphContentLeft;
        float height;
        float f;
        if (this.mIsVisible) {
            this.mPaint.setTextSize(this.mStyles.textSize);
            int i = (int) (((double) this.mStyles.textSize) * 0.8d);
            ArrayList<Series> arrayList = new ArrayList();
            arrayList.addAll(this.mGraphView.getSeries());
            if (this.mGraphView.mSecondScale != null) {
                arrayList.addAll(this.mGraphView.getSecondScale().getSeries());
            }
            int iMax = this.mStyles.width;
            int i2 = 0;
            if (iMax == 0 && (iMax = this.cachedLegendWidth) == 0) {
                Rect rect = new Rect();
                for (Series series : arrayList) {
                    if (series.getTitle() != null) {
                        this.mPaint.getTextBounds(series.getTitle(), 0, series.getTitle().length(), rect);
                        iMax = Math.max(iMax, rect.width());
                    }
                }
                if (iMax == 0) {
                    iMax = 1;
                }
                iMax += (this.mStyles.padding * 2) + i + this.mStyles.spacing;
                this.cachedLegendWidth = iMax;
            }
            float size = ((this.mStyles.textSize + this.mStyles.spacing) * arrayList.size()) - this.mStyles.spacing;
            if (this.mStyles.fixedPosition != null) {
                graphContentLeft = this.mGraphView.getGraphContentLeft() + this.mStyles.margin + this.mStyles.fixedPosition.x;
                graphContentTop2 = this.mGraphView.getGraphContentTop() + this.mStyles.margin + this.mStyles.fixedPosition.y;
            } else {
                float graphContentLeft2 = ((this.mGraphView.getGraphContentLeft() + this.mGraphView.getGraphContentWidth()) - iMax) - this.mStyles.margin;
                int i3 = AnonymousClass1.$SwitchMap$com$jjoe64$graphview$LegendRenderer$LegendAlign[this.mStyles.align.ordinal()];
                if (i3 != 1) {
                    if (i3 == 2) {
                        height = this.mGraphView.getHeight() / 2;
                        f = size / 2.0f;
                    } else {
                        height = ((this.mGraphView.getGraphContentTop() + this.mGraphView.getGraphContentHeight()) - this.mStyles.margin) - size;
                        f = this.mStyles.padding * 2;
                    }
                    graphContentTop = height - f;
                } else {
                    graphContentTop = this.mGraphView.getGraphContentTop() + this.mStyles.margin;
                }
                graphContentTop2 = graphContentTop;
                graphContentLeft = graphContentLeft2;
            }
            this.mPaint.setColor(this.mStyles.backgroundColor);
            canvas.drawRoundRect(new RectF(graphContentLeft, graphContentTop2, iMax + graphContentLeft, size + graphContentTop2 + (this.mStyles.padding * 2)), 8.0f, 8.0f, this.mPaint);
            for (Series series2 : arrayList) {
                this.mPaint.setColor(series2.getColor());
                float f2 = i2;
                float f3 = i;
                int i4 = i;
                canvas.drawRect(new RectF(this.mStyles.padding + graphContentLeft, this.mStyles.padding + graphContentTop2 + ((this.mStyles.textSize + this.mStyles.spacing) * f2), this.mStyles.padding + graphContentLeft + f3, this.mStyles.padding + graphContentTop2 + ((this.mStyles.textSize + this.mStyles.spacing) * f2) + f3), this.mPaint);
                if (series2.getTitle() != null) {
                    this.mPaint.setColor(this.mStyles.textColor);
                    canvas.drawText(series2.getTitle(), this.mStyles.padding + graphContentLeft + f3 + this.mStyles.spacing, this.mStyles.padding + graphContentTop2 + this.mStyles.textSize + (f2 * (this.mStyles.textSize + this.mStyles.spacing)), this.mPaint);
                }
                i2++;
                i = i4;
            }
        }
    }

    /* JADX INFO: renamed from: com.jjoe64.graphview.LegendRenderer$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$jjoe64$graphview$LegendRenderer$LegendAlign;

        static {
            int[] iArr = new int[LegendAlign.values().length];
            $SwitchMap$com$jjoe64$graphview$LegendRenderer$LegendAlign = iArr;
            try {
                iArr[LegendAlign.TOP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$jjoe64$graphview$LegendRenderer$LegendAlign[LegendAlign.MIDDLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public boolean isVisible() {
        return this.mIsVisible;
    }

    public void setVisible(boolean z) {
        this.mIsVisible = z;
    }

    public float getTextSize() {
        return this.mStyles.textSize;
    }

    public void setTextSize(float f) {
        this.mStyles.textSize = f;
        this.cachedLegendWidth = 0;
    }

    public int getSpacing() {
        return this.mStyles.spacing;
    }

    public void setSpacing(int i) {
        this.mStyles.spacing = i;
    }

    public int getPadding() {
        return this.mStyles.padding;
    }

    public void setPadding(int i) {
        this.mStyles.padding = i;
    }

    public int getWidth() {
        return this.mStyles.width;
    }

    public void setWidth(int i) {
        this.mStyles.width = i;
    }

    public int getBackgroundColor() {
        return this.mStyles.backgroundColor;
    }

    public void setBackgroundColor(int i) {
        this.mStyles.backgroundColor = i;
    }

    public int getMargin() {
        return this.mStyles.margin;
    }

    public void setMargin(int i) {
        this.mStyles.margin = i;
    }

    public LegendAlign getAlign() {
        return this.mStyles.align;
    }

    public void setAlign(LegendAlign legendAlign) {
        this.mStyles.align = legendAlign;
    }

    public int getTextColor() {
        return this.mStyles.textColor;
    }

    public void setTextColor(int i) {
        this.mStyles.textColor = i;
    }

    public void setFixedPosition(int i, int i2) {
        this.mStyles.fixedPosition = new Point(i, i2);
    }
}
