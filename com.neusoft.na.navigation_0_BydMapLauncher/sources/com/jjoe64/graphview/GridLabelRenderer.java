package com.jjoe64.graphview;

import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.TypedValue;
import androidx.core.view.ViewCompat;
import com.jjoe64.graphview.Viewport;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class GridLabelRenderer {
    private final GraphView mGraphView;
    private String mHorizontalAxisTitle;
    private boolean mHumanRounding;
    protected boolean mIsAdjusted;
    private LabelFormatter mLabelFormatter;
    private Integer mLabelHorizontalHeight;
    private boolean mLabelHorizontalHeightFixed;
    private Integer mLabelHorizontalWidth;
    private Integer mLabelVerticalHeight;
    private Integer mLabelVerticalSecondScaleHeight;
    private Integer mLabelVerticalSecondScaleWidth;
    private Integer mLabelVerticalWidth;
    private boolean mLabelVerticalWidthFixed;
    private int mNumHorizontalLabels;
    private int mNumVerticalLabels;
    private Paint mPaintAxisTitle;
    private Paint mPaintLabel;
    private Paint mPaintLine;
    private Map<Integer, Double> mStepsHorizontal;
    private Map<Integer, Double> mStepsVertical;
    private Map<Integer, Double> mStepsVerticalSecondScale;
    protected Styles mStyles;
    private String mVerticalAxisTitle;

    public enum VerticalLabelsVAlign {
        ABOVE,
        MID,
        BELOW
    }

    public final class Styles {
        public int gridColor;
        GridStyle gridStyle;
        public boolean highlightZeroLines;
        public int horizontalAxisTitleColor;
        public float horizontalAxisTitleTextSize;
        public float horizontalLabelsAngle;
        public int horizontalLabelsColor;
        boolean horizontalLabelsVisible;
        int labelsSpace;
        public int padding;
        public float textSize;
        public int verticalAxisTitleColor;
        public float verticalAxisTitleTextSize;
        public Paint.Align verticalLabelsAlign;
        public int verticalLabelsColor;
        public Paint.Align verticalLabelsSecondScaleAlign;
        public int verticalLabelsSecondScaleColor;
        VerticalLabelsVAlign verticalLabelsVAlign = VerticalLabelsVAlign.MID;
        boolean verticalLabelsVisible;

        public Styles() {
        }
    }

    public enum GridStyle {
        BOTH,
        VERTICAL,
        HORIZONTAL,
        NONE;

        public boolean drawVertical() {
            return this == BOTH || (this == VERTICAL && this != NONE);
        }

        public boolean drawHorizontal() {
            return this == BOTH || (this == HORIZONTAL && this != NONE);
        }
    }

    public void setSecondScaleLabelVerticalWidth(Integer num) {
        this.mLabelVerticalSecondScaleWidth = num;
    }

    public GridLabelRenderer(GraphView graphView) {
        this.mGraphView = graphView;
        setLabelFormatter(new DefaultLabelFormatter());
        this.mStyles = new Styles();
        resetStyles();
        this.mNumVerticalLabels = 5;
        this.mNumHorizontalLabels = 5;
        this.mHumanRounding = true;
    }

    public void resetStyles() {
        int dimensionPixelSize;
        TypedValue typedValue = new TypedValue();
        this.mGraphView.getContext().getTheme().resolveAttribute(android.R.attr.textAppearanceSmall, typedValue, true);
        int i = -7829368;
        int i2 = ViewCompat.MEASURED_STATE_MASK;
        int i3 = 20;
        try {
            TypedArray typedArrayObtainStyledAttributes = this.mGraphView.getContext().obtainStyledAttributes(typedValue.data, new int[]{android.R.attr.textColorPrimary, android.R.attr.textColorSecondary, android.R.attr.textSize, android.R.attr.horizontalGap});
            int color = typedArrayObtainStyledAttributes.getColor(0, ViewCompat.MEASURED_STATE_MASK);
            int color2 = typedArrayObtainStyledAttributes.getColor(1, -7829368);
            int dimensionPixelSize2 = typedArrayObtainStyledAttributes.getDimensionPixelSize(2, 20);
            dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(3, 20);
            typedArrayObtainStyledAttributes.recycle();
            i2 = color;
            i = color2;
            i3 = dimensionPixelSize2;
        } catch (Exception unused) {
            dimensionPixelSize = 20;
        }
        this.mStyles.verticalLabelsColor = i2;
        this.mStyles.verticalLabelsSecondScaleColor = i2;
        this.mStyles.horizontalLabelsColor = i2;
        this.mStyles.gridColor = i;
        this.mStyles.textSize = i3;
        this.mStyles.padding = dimensionPixelSize;
        Styles styles = this.mStyles;
        styles.labelsSpace = ((int) styles.textSize) / 5;
        this.mStyles.verticalLabelsAlign = Paint.Align.RIGHT;
        this.mStyles.verticalLabelsSecondScaleAlign = Paint.Align.LEFT;
        this.mStyles.highlightZeroLines = true;
        Styles styles2 = this.mStyles;
        styles2.verticalAxisTitleColor = styles2.verticalLabelsColor;
        Styles styles3 = this.mStyles;
        styles3.horizontalAxisTitleColor = styles3.horizontalLabelsColor;
        Styles styles4 = this.mStyles;
        styles4.verticalAxisTitleTextSize = styles4.textSize;
        Styles styles5 = this.mStyles;
        styles5.horizontalAxisTitleTextSize = styles5.textSize;
        this.mStyles.horizontalLabelsVisible = true;
        this.mStyles.verticalLabelsVisible = true;
        this.mStyles.horizontalLabelsAngle = 0.0f;
        this.mStyles.gridStyle = GridStyle.BOTH;
        reloadStyles();
    }

    public void reloadStyles() {
        Paint paint = new Paint();
        this.mPaintLine = paint;
        paint.setColor(this.mStyles.gridColor);
        this.mPaintLine.setStrokeWidth(0.0f);
        Paint paint2 = new Paint();
        this.mPaintLabel = paint2;
        paint2.setTextSize(getTextSize());
        this.mPaintLabel.setAntiAlias(true);
        Paint paint3 = new Paint();
        this.mPaintAxisTitle = paint3;
        paint3.setTextSize(getTextSize());
        this.mPaintAxisTitle.setTextAlign(Paint.Align.CENTER);
    }

    public boolean isHumanRounding() {
        return this.mHumanRounding;
    }

    public void setHumanRounding(boolean z) {
        this.mHumanRounding = z;
    }

    public float getTextSize() {
        return this.mStyles.textSize;
    }

    public int getVerticalLabelsColor() {
        return this.mStyles.verticalLabelsColor;
    }

    public Paint.Align getVerticalLabelsAlign() {
        return this.mStyles.verticalLabelsAlign;
    }

    public int getHorizontalLabelsColor() {
        return this.mStyles.horizontalLabelsColor;
    }

    public float getHorizontalLabelsAngle() {
        return this.mStyles.horizontalLabelsAngle;
    }

    public void invalidate(boolean z, boolean z2) {
        if (!z2) {
            this.mIsAdjusted = false;
        }
        if (z) {
            return;
        }
        if (!this.mLabelVerticalWidthFixed) {
            this.mLabelVerticalWidth = null;
        }
        this.mLabelVerticalHeight = null;
        this.mLabelVerticalSecondScaleWidth = null;
        this.mLabelVerticalSecondScaleHeight = null;
    }

    protected boolean adjustVerticalSecondScale() {
        double dDoubleValue;
        if (this.mLabelHorizontalHeight == null) {
            return false;
        }
        if (this.mGraphView.mSecondScale == null) {
            return true;
        }
        double minY = this.mGraphView.mSecondScale.getMinY(false);
        double maxY = this.mGraphView.mSecondScale.getMaxY(false);
        int i = this.mNumVerticalLabels;
        if (this.mGraphView.mSecondScale.isYAxisBoundsManual()) {
            double d = maxY - minY;
            double dRound = Math.round((d / ((double) (i - 1))) * 1000000.0d) / 1000000.0d;
            Map<Integer, Double> map = this.mStepsVerticalSecondScale;
            if (map != null && map.size() > 1) {
                Iterator<Double> it = this.mStepsVerticalSecondScale.values().iterator();
                int i2 = 0;
                double dDoubleValue2 = 0.0d;
                while (true) {
                    if (!it.hasNext()) {
                        dDoubleValue = 0.0d;
                        break;
                    }
                    Double next = it.next();
                    if (i2 == 0) {
                        dDoubleValue2 = next.doubleValue();
                        i2++;
                    } else {
                        dDoubleValue = next.doubleValue();
                        break;
                    }
                }
                double d2 = dDoubleValue - dDoubleValue2;
                if (d2 > 0.0d) {
                    dRound = d2 > dRound ? d2 / 2.0d : d2 < dRound ? 2.0d * d2 : Double.NaN;
                    int i3 = (int) (d / d2);
                    int i4 = (int) (d / dRound);
                    boolean z = i3 > i || i4 > i || i4 > i3;
                    if (dRound == Double.NaN || !z || i4 > i) {
                        dRound = d2;
                    }
                }
            }
            double d3 = this.mGraphView.getSecondScale().mReferenceY;
            double dFloor = (Math.floor((minY - d3) / dRound) * dRound) + d3;
            int iHeight = ((int) ((this.mGraphView.getSecondScale().mCurrentViewport.height() * (-1.0d)) / dRound)) + 2;
            Map<Integer, Double> map2 = this.mStepsVerticalSecondScale;
            if (map2 != null) {
                map2.clear();
            } else {
                this.mStepsVerticalSecondScale = new LinkedHashMap(iHeight);
            }
            double graphContentHeight = (((double) this.mGraphView.getGraphContentHeight()) / this.mGraphView.getSecondScale().mCurrentViewport.height()) * (-1.0d);
            for (int i5 = 0; i5 < iHeight; i5++) {
                double d4 = (((double) i5) * dRound) + dFloor;
                if (d4 <= this.mGraphView.getSecondScale().mCurrentViewport.top && d4 >= this.mGraphView.getSecondScale().mCurrentViewport.bottom) {
                    this.mStepsVerticalSecondScale.put(Integer.valueOf((int) ((d4 - this.mGraphView.getSecondScale().mCurrentViewport.bottom) * graphContentHeight)), Double.valueOf(d4));
                }
            }
            return true;
        }
        throw new IllegalStateException("Not yet implemented");
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0128  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected boolean adjustVertical(boolean r24) {
        /*
            Method dump skipped, instruction units count: 360
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jjoe64.graphview.GridLabelRenderer.adjustVertical(boolean):boolean");
    }

    protected boolean adjustHorizontal(boolean z) {
        double dDoubleValue;
        if (this.mLabelVerticalWidth == null) {
            return false;
        }
        double minX = this.mGraphView.getViewport().getMinX(false);
        double maxX = this.mGraphView.getViewport().getMaxX(false);
        if (minX == maxX) {
            return false;
        }
        int i = this.mNumHorizontalLabels;
        double d = maxX - minX;
        double d2 = i - 1;
        double dRound = Math.round((d / d2) * 1000000.0d) / 1000000.0d;
        if (isHumanRounding()) {
            dRound = humanRound(dRound, false);
        } else {
            Map<Integer, Double> map = this.mStepsHorizontal;
            if (map != null && map.size() > 1) {
                Iterator<Double> it = this.mStepsHorizontal.values().iterator();
                int i2 = 0;
                double dDoubleValue2 = 0.0d;
                while (true) {
                    if (!it.hasNext()) {
                        dDoubleValue = 0.0d;
                        break;
                    }
                    Double next = it.next();
                    if (i2 == 0) {
                        dDoubleValue2 = next.doubleValue();
                        i2++;
                    } else {
                        dDoubleValue = next.doubleValue();
                        break;
                    }
                }
                double d3 = dDoubleValue - dDoubleValue2;
                if (d3 > 0.0d) {
                    dRound = d3 > dRound ? d3 / 2.0d : d3 < dRound ? 2.0d * d3 : Double.NaN;
                    int i3 = (int) (d / d3);
                    int i4 = (int) (d / dRound);
                    boolean z2 = i3 > i || i4 > i || i4 > i3;
                    if (dRound == Double.NaN || !z2 || i4 > i) {
                        dRound = d3;
                    }
                }
            }
        }
        double referenceX = this.mGraphView.getViewport().getReferenceX();
        double dFloor = (Math.floor((minX - referenceX) / dRound) * dRound) + referenceX;
        if (z) {
            this.mGraphView.getViewport().setMinX(dFloor);
            this.mGraphView.getViewport().setMaxX((d2 * dRound) + dFloor);
            this.mGraphView.getViewport().mXAxisBoundsStatus = Viewport.AxisBoundsStatus.AUTO_ADJUSTED;
        }
        int iWidth = ((int) (this.mGraphView.getViewport().mCurrentViewport.width() / dRound)) + 1;
        Map<Integer, Double> map2 = this.mStepsHorizontal;
        if (map2 != null) {
            map2.clear();
        } else {
            this.mStepsHorizontal = new LinkedHashMap(iWidth);
        }
        double graphContentWidth = ((double) this.mGraphView.getGraphContentWidth()) / this.mGraphView.getViewport().mCurrentViewport.width();
        for (int i5 = 0; i5 < iWidth; i5++) {
            double d4 = (((double) i5) * dRound) + dFloor;
            if (d4 >= this.mGraphView.getViewport().mCurrentViewport.left) {
                this.mStepsHorizontal.put(Integer.valueOf((int) ((d4 - this.mGraphView.getViewport().mCurrentViewport.left) * graphContentWidth)), Double.valueOf(d4));
            }
        }
        return true;
    }

    protected void adjustSteps() {
        boolean zAdjustVertical = adjustVertical(!Viewport.AxisBoundsStatus.FIX.equals(this.mGraphView.getViewport().mYAxisBoundsStatus));
        this.mIsAdjusted = zAdjustVertical;
        boolean zAdjustVerticalSecondScale = zAdjustVertical & adjustVerticalSecondScale();
        this.mIsAdjusted = zAdjustVerticalSecondScale;
        this.mIsAdjusted = zAdjustVerticalSecondScale & adjustHorizontal(!Viewport.AxisBoundsStatus.FIX.equals(this.mGraphView.getViewport().mXAxisBoundsStatus));
    }

    protected void calcLabelVerticalSize(Canvas canvas) {
        String label = this.mLabelFormatter.formatLabel(this.mGraphView.getViewport().getMaxY(false), false);
        if (label == null) {
            label = "";
        }
        Rect rect = new Rect();
        this.mPaintLabel.getTextBounds(label, 0, label.length(), rect);
        this.mLabelVerticalWidth = Integer.valueOf(rect.width());
        this.mLabelVerticalHeight = Integer.valueOf(rect.height());
        String label2 = this.mLabelFormatter.formatLabel(this.mGraphView.getViewport().getMinY(false), false);
        String str = label2 != null ? label2 : "";
        this.mPaintLabel.getTextBounds(str, 0, str.length(), rect);
        Integer numValueOf = Integer.valueOf(Math.max(this.mLabelVerticalWidth.intValue(), rect.width()));
        this.mLabelVerticalWidth = numValueOf;
        Integer numValueOf2 = Integer.valueOf(numValueOf.intValue() + 6);
        this.mLabelVerticalWidth = numValueOf2;
        this.mLabelVerticalWidth = Integer.valueOf(numValueOf2.intValue() + this.mStyles.labelsSpace);
        int i = 1;
        for (byte b : str.getBytes()) {
            if (b == 10) {
                i++;
            }
        }
        this.mLabelVerticalHeight = Integer.valueOf(this.mLabelVerticalHeight.intValue() * i);
    }

    protected void calcLabelVerticalSecondScaleSize(Canvas canvas) {
        if (this.mGraphView.mSecondScale == null) {
            this.mLabelVerticalSecondScaleWidth = 0;
            this.mLabelVerticalSecondScaleHeight = 0;
            return;
        }
        String label = this.mGraphView.mSecondScale.getLabelFormatter().formatLabel(((this.mGraphView.mSecondScale.getMaxY(false) - this.mGraphView.mSecondScale.getMinY(false)) * 0.783d) + this.mGraphView.mSecondScale.getMinY(false), false);
        Rect rect = new Rect();
        this.mPaintLabel.getTextBounds(label, 0, label.length(), rect);
        this.mLabelVerticalSecondScaleWidth = Integer.valueOf(rect.width());
        this.mLabelVerticalSecondScaleHeight = Integer.valueOf(rect.height());
        int i = 1;
        for (byte b : label.getBytes()) {
            if (b == 10) {
                i++;
            }
        }
        this.mLabelVerticalSecondScaleHeight = Integer.valueOf(this.mLabelVerticalSecondScaleHeight.intValue() * i);
    }

    protected void calcLabelHorizontalSize(Canvas canvas) {
        int i = 1;
        String label = this.mLabelFormatter.formatLabel(((this.mGraphView.getViewport().getMaxX(false) - this.mGraphView.getViewport().getMinX(false)) * 0.783d) + this.mGraphView.getViewport().getMinX(false), true);
        if (label == null) {
            label = "";
        }
        Rect rect = new Rect();
        this.mPaintLabel.getTextBounds(label, 0, label.length(), rect);
        this.mLabelHorizontalWidth = Integer.valueOf(rect.width());
        if (!this.mLabelHorizontalHeightFixed) {
            this.mLabelHorizontalHeight = Integer.valueOf(rect.height());
            for (byte b : label.getBytes()) {
                if (b == 10) {
                    i++;
                }
            }
            this.mLabelHorizontalHeight = Integer.valueOf(this.mLabelHorizontalHeight.intValue() * i);
            this.mLabelHorizontalHeight = Integer.valueOf((int) Math.max(r7.intValue(), this.mStyles.textSize));
        }
        if (this.mStyles.horizontalLabelsAngle > 0.0f && this.mStyles.horizontalLabelsAngle <= 180.0f) {
            int iRound = (int) Math.round(Math.abs(((double) this.mLabelHorizontalHeight.intValue()) * Math.cos(Math.toRadians(this.mStyles.horizontalLabelsAngle))));
            int iRound2 = (int) Math.round(Math.abs(((double) this.mLabelHorizontalWidth.intValue()) * Math.sin(Math.toRadians(this.mStyles.horizontalLabelsAngle))));
            int iRound3 = (int) Math.round(Math.abs(((double) this.mLabelHorizontalHeight.intValue()) * Math.sin(Math.toRadians(this.mStyles.horizontalLabelsAngle))));
            int iRound4 = (int) Math.round(Math.abs(((double) this.mLabelHorizontalWidth.intValue()) * Math.cos(Math.toRadians(this.mStyles.horizontalLabelsAngle))));
            this.mLabelHorizontalHeight = Integer.valueOf(iRound + iRound2);
            this.mLabelHorizontalWidth = Integer.valueOf(iRound3 + iRound4);
        }
        this.mLabelHorizontalHeight = Integer.valueOf(this.mLabelHorizontalHeight.intValue() + this.mStyles.labelsSpace);
    }

    public void draw(Canvas canvas) {
        boolean z;
        boolean z2 = true;
        if (this.mLabelHorizontalWidth == null) {
            calcLabelHorizontalSize(canvas);
            z = true;
        } else {
            z = false;
        }
        if (this.mLabelVerticalWidth == null) {
            calcLabelVerticalSize(canvas);
            z = true;
        }
        if (this.mLabelVerticalSecondScaleWidth == null) {
            calcLabelVerticalSecondScaleSize(canvas);
        } else {
            z2 = z;
        }
        if (z2) {
            this.mGraphView.drawGraphElements(canvas);
            return;
        }
        if (!this.mIsAdjusted) {
            adjustSteps();
        }
        if (this.mIsAdjusted) {
            drawVerticalSteps(canvas);
            drawVerticalStepsSecondScale(canvas);
            drawHorizontalSteps(canvas);
            drawHorizontalAxisTitle(canvas);
            drawVerticalAxisTitle(canvas);
            if (this.mGraphView.mSecondScale != null) {
                this.mGraphView.mSecondScale.drawVerticalAxisTitle(canvas);
            }
        }
    }

    protected void drawHorizontalAxisTitle(Canvas canvas) {
        String str = this.mHorizontalAxisTitle;
        if (str == null || str.length() <= 0) {
            return;
        }
        this.mPaintAxisTitle.setColor(getHorizontalAxisTitleColor());
        this.mPaintAxisTitle.setTextSize(getHorizontalAxisTitleTextSize());
        canvas.drawText(this.mHorizontalAxisTitle, canvas.getWidth() / 2, canvas.getHeight() - this.mStyles.padding, this.mPaintAxisTitle);
    }

    protected void drawVerticalAxisTitle(Canvas canvas) {
        String str = this.mVerticalAxisTitle;
        if (str == null || str.length() <= 0) {
            return;
        }
        this.mPaintAxisTitle.setColor(getVerticalAxisTitleColor());
        this.mPaintAxisTitle.setTextSize(getVerticalAxisTitleTextSize());
        float verticalAxisTitleWidth = getVerticalAxisTitleWidth();
        float height = canvas.getHeight() / 2;
        canvas.save();
        canvas.rotate(-90.0f, verticalAxisTitleWidth, height);
        canvas.drawText(this.mVerticalAxisTitle, verticalAxisTitleWidth, height, this.mPaintAxisTitle);
        canvas.restore();
    }

    public int getHorizontalAxisTitleHeight() {
        String str = this.mHorizontalAxisTitle;
        if (str == null || str.length() <= 0) {
            return 0;
        }
        return (int) getHorizontalAxisTitleTextSize();
    }

    public int getVerticalAxisTitleWidth() {
        String str = this.mVerticalAxisTitle;
        if (str == null || str.length() <= 0) {
            return 0;
        }
        return (int) getVerticalAxisTitleTextSize();
    }

    protected void drawHorizontalSteps(Canvas canvas) {
        int iAbs;
        this.mPaintLabel.setColor(getHorizontalLabelsColor());
        int i = 0;
        for (Map.Entry<Integer, Double> entry : this.mStepsHorizontal.entrySet()) {
            if (this.mStyles.highlightZeroLines) {
                if (entry.getValue().doubleValue() == 0.0d) {
                    this.mPaintLine.setStrokeWidth(5.0f);
                } else {
                    this.mPaintLine.setStrokeWidth(0.0f);
                }
            }
            if (this.mStyles.gridStyle.drawVertical() && entry.getKey().intValue() <= this.mGraphView.getGraphContentWidth()) {
                canvas.drawLine(this.mGraphView.getGraphContentLeft() + entry.getKey().intValue(), this.mGraphView.getGraphContentTop(), this.mGraphView.getGraphContentLeft() + entry.getKey().intValue(), this.mGraphView.getGraphContentTop() + this.mGraphView.getGraphContentHeight(), this.mPaintLine);
            }
            if (isHorizontalLabelsVisible()) {
                float f = 90.0f;
                if (this.mStyles.horizontalLabelsAngle <= 0.0f || this.mStyles.horizontalLabelsAngle > 180.0f) {
                    this.mPaintLabel.setTextAlign(Paint.Align.CENTER);
                    if (i == this.mStepsHorizontal.size() - 1) {
                        this.mPaintLabel.setTextAlign(Paint.Align.RIGHT);
                    }
                    if (i == 0) {
                        this.mPaintLabel.setTextAlign(Paint.Align.LEFT);
                    }
                } else if (this.mStyles.horizontalLabelsAngle < 90.0f) {
                    this.mPaintLabel.setTextAlign(Paint.Align.RIGHT);
                } else if (this.mStyles.horizontalLabelsAngle <= 180.0f) {
                    this.mPaintLabel.setTextAlign(Paint.Align.LEFT);
                }
                String label = this.mLabelFormatter.formatLabel(entry.getValue().doubleValue(), true);
                if (label == null) {
                    label = "";
                }
                String[] strArrSplit = label.split("\n");
                if (this.mStyles.horizontalLabelsAngle <= 0.0f || this.mStyles.horizontalLabelsAngle > 180.0f) {
                    iAbs = 0;
                } else {
                    Rect rect = new Rect();
                    Paint paint = this.mPaintLabel;
                    String str = strArrSplit[0];
                    paint.getTextBounds(str, 0, str.length(), rect);
                    iAbs = (int) Math.abs(((double) rect.width()) * Math.cos(Math.toRadians(this.mStyles.horizontalLabelsAngle)));
                }
                int i2 = 0;
                while (i2 < strArrSplit.length) {
                    float height = (((canvas.getHeight() - this.mStyles.padding) - getHorizontalAxisTitleHeight()) - ((((strArrSplit.length - i2) - 1) * getTextSize()) * 1.1f)) + this.mStyles.labelsSpace;
                    float graphContentLeft = this.mGraphView.getGraphContentLeft() + entry.getKey().intValue();
                    if (this.mStyles.horizontalLabelsAngle > 0.0f && this.mStyles.horizontalLabelsAngle < f) {
                        canvas.save();
                        float f2 = graphContentLeft + iAbs;
                        canvas.rotate(this.mStyles.horizontalLabelsAngle, f2, height);
                        canvas.drawText(strArrSplit[i2], f2, height, this.mPaintLabel);
                        canvas.restore();
                    } else if (this.mStyles.horizontalLabelsAngle > 0.0f && this.mStyles.horizontalLabelsAngle <= 180.0f) {
                        canvas.save();
                        float f3 = graphContentLeft - iAbs;
                        canvas.rotate(this.mStyles.horizontalLabelsAngle - 180.0f, f3, height);
                        canvas.drawText(strArrSplit[i2], f3, height, this.mPaintLabel);
                        canvas.restore();
                    } else {
                        canvas.drawText(strArrSplit[i2], graphContentLeft, height, this.mPaintLabel);
                    }
                    i2++;
                    f = 90.0f;
                }
            }
            i++;
        }
    }

    protected void drawVerticalStepsSecondScale(Canvas canvas) {
        if (this.mGraphView.mSecondScale == null) {
            return;
        }
        float graphContentLeft = this.mGraphView.getGraphContentLeft() + this.mGraphView.getGraphContentWidth();
        this.mPaintLabel.setColor(getVerticalLabelsSecondScaleColor());
        this.mPaintLabel.setTextAlign(getVerticalLabelsSecondScaleAlign());
        for (Map.Entry<Integer, Double> entry : this.mStepsVerticalSecondScale.entrySet()) {
            float graphContentTop = (this.mGraphView.getGraphContentTop() + this.mGraphView.getGraphContentHeight()) - entry.getKey().intValue();
            int iIntValue = this.mLabelVerticalSecondScaleWidth.intValue();
            int i = (int) graphContentLeft;
            if (getVerticalLabelsSecondScaleAlign() == Paint.Align.RIGHT) {
                i += iIntValue;
            } else if (getVerticalLabelsSecondScaleAlign() == Paint.Align.CENTER) {
                iIntValue /= 2;
                i += iIntValue;
            }
            LabelFormatter labelFormatter = this.mGraphView.mSecondScale.mLabelFormatter;
            double dDoubleValue = entry.getValue().doubleValue();
            String[] strArrSplit = labelFormatter.formatLabel(dDoubleValue, false).split("\n");
            float length = graphContentTop + (((strArrSplit.length * getTextSize()) * 1.1f) / 2.0f);
            for (int i2 = 0; i2 < strArrSplit.length; i2++) {
                canvas.drawText(strArrSplit[i2], i, length - ((((strArrSplit.length - i2) - 1) * getTextSize()) * 1.1f), this.mPaintLabel);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x0128 A[LOOP:1: B:46:0x0125->B:48:0x0128, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void drawVerticalSteps(android.graphics.Canvas r15) {
        /*
            Method dump skipped, instruction units count: 324
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jjoe64.graphview.GridLabelRenderer.drawVerticalSteps(android.graphics.Canvas):void");
    }

    /* JADX INFO: renamed from: com.jjoe64.graphview.GridLabelRenderer$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$jjoe64$graphview$GridLabelRenderer$VerticalLabelsVAlign;

        static {
            int[] iArr = new int[VerticalLabelsVAlign.values().length];
            $SwitchMap$com$jjoe64$graphview$GridLabelRenderer$VerticalLabelsVAlign = iArr;
            try {
                iArr[VerticalLabelsVAlign.MID.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$jjoe64$graphview$GridLabelRenderer$VerticalLabelsVAlign[VerticalLabelsVAlign.ABOVE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$jjoe64$graphview$GridLabelRenderer$VerticalLabelsVAlign[VerticalLabelsVAlign.BELOW.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0058  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected double humanRound(double r10, boolean r12) {
        /*
            r9 = this;
            r0 = 0
        L1:
            double r1 = java.lang.Math.abs(r10)
            r3 = 4621819117588971520(0x4024000000000000, double:10.0)
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 < 0) goto Lf
            double r10 = r10 / r3
            int r0 = r0 + 1
            goto L1
        Lf:
            double r1 = java.lang.Math.abs(r10)
            r5 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r1 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r1 >= 0) goto L1d
            double r10 = r10 * r3
            int r0 = r0 + (-1)
            goto Lf
        L1d:
            r1 = 4617315517961601024(0x4014000000000000, double:5.0)
            r7 = 4611686018427387904(0x4000000000000000, double:2.0)
            if (r12 == 0) goto L37
            int r12 = (r10 > r5 ? 1 : (r10 == r5 ? 0 : -1))
            if (r12 != 0) goto L28
            goto L59
        L28:
            int r12 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
            if (r12 > 0) goto L2d
            goto L45
        L2d:
            int r12 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r12 > 0) goto L32
            goto L50
        L32:
            int r12 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r12 >= 0) goto L59
            goto L58
        L37:
            int r12 = (r10 > r5 ? 1 : (r10 == r5 ? 0 : -1))
            if (r12 != 0) goto L3c
            goto L59
        L3c:
            r5 = 4617202927970916762(0x401399999999999a, double:4.9)
            int r12 = (r10 > r5 ? 1 : (r10 == r5 ? 0 : -1))
            if (r12 > 0) goto L47
        L45:
            r10 = r7
            goto L59
        L47:
            r5 = 4621762822593629389(0x4023cccccccccccd, double:9.9)
            int r12 = (r10 > r5 ? 1 : (r10 == r5 ? 0 : -1))
            if (r12 > 0) goto L52
        L50:
            r10 = r1
            goto L59
        L52:
            r1 = 4624633867356078080(0x402e000000000000, double:15.0)
            int r12 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r12 >= 0) goto L59
        L58:
            r10 = r3
        L59:
            double r0 = (double) r0
            double r0 = java.lang.Math.pow(r3, r0)
            double r10 = r10 * r0
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jjoe64.graphview.GridLabelRenderer.humanRound(double, boolean):double");
    }

    public Styles getStyles() {
        return this.mStyles;
    }

    public int getLabelVerticalWidth() {
        if (this.mStyles.verticalLabelsVAlign == VerticalLabelsVAlign.ABOVE || this.mStyles.verticalLabelsVAlign == VerticalLabelsVAlign.BELOW || this.mLabelVerticalWidth == null || !isVerticalLabelsVisible()) {
            return 0;
        }
        return this.mLabelVerticalWidth.intValue();
    }

    public void setLabelVerticalWidth(Integer num) {
        this.mLabelVerticalWidth = num;
        this.mLabelVerticalWidthFixed = num != null;
    }

    public int getLabelHorizontalHeight() {
        if (this.mLabelHorizontalHeight == null || !isHorizontalLabelsVisible()) {
            return 0;
        }
        return this.mLabelHorizontalHeight.intValue();
    }

    public void setLabelHorizontalHeight(Integer num) {
        this.mLabelHorizontalHeight = num;
        this.mLabelHorizontalHeightFixed = num != null;
    }

    public int getGridColor() {
        return this.mStyles.gridColor;
    }

    public boolean isHighlightZeroLines() {
        return this.mStyles.highlightZeroLines;
    }

    public int getPadding() {
        return this.mStyles.padding;
    }

    public void setTextSize(float f) {
        this.mStyles.textSize = f;
        reloadStyles();
    }

    public void setVerticalLabelsAlign(Paint.Align align) {
        this.mStyles.verticalLabelsAlign = align;
    }

    public void setVerticalLabelsColor(int i) {
        this.mStyles.verticalLabelsColor = i;
    }

    public void setHorizontalLabelsColor(int i) {
        this.mStyles.horizontalLabelsColor = i;
    }

    public void setHorizontalLabelsAngle(int i) {
        this.mStyles.horizontalLabelsAngle = i;
    }

    public void setGridColor(int i) {
        this.mStyles.gridColor = i;
        reloadStyles();
    }

    public void setHighlightZeroLines(boolean z) {
        this.mStyles.highlightZeroLines = z;
    }

    public void setPadding(int i) {
        this.mStyles.padding = i;
    }

    public LabelFormatter getLabelFormatter() {
        return this.mLabelFormatter;
    }

    public void setLabelFormatter(LabelFormatter labelFormatter) {
        this.mLabelFormatter = labelFormatter;
        labelFormatter.setViewport(this.mGraphView.getViewport());
    }

    public String getHorizontalAxisTitle() {
        return this.mHorizontalAxisTitle;
    }

    public void setHorizontalAxisTitle(String str) {
        this.mHorizontalAxisTitle = str;
    }

    public String getVerticalAxisTitle() {
        return this.mVerticalAxisTitle;
    }

    public void setVerticalAxisTitle(String str) {
        this.mVerticalAxisTitle = str;
    }

    public float getVerticalAxisTitleTextSize() {
        return this.mStyles.verticalAxisTitleTextSize;
    }

    public void setVerticalAxisTitleTextSize(float f) {
        this.mStyles.verticalAxisTitleTextSize = f;
    }

    public int getVerticalAxisTitleColor() {
        return this.mStyles.verticalAxisTitleColor;
    }

    public void setVerticalAxisTitleColor(int i) {
        this.mStyles.verticalAxisTitleColor = i;
    }

    public float getHorizontalAxisTitleTextSize() {
        return this.mStyles.horizontalAxisTitleTextSize;
    }

    public void setHorizontalAxisTitleTextSize(float f) {
        this.mStyles.horizontalAxisTitleTextSize = f;
    }

    public int getHorizontalAxisTitleColor() {
        return this.mStyles.horizontalAxisTitleColor;
    }

    public void setHorizontalAxisTitleColor(int i) {
        this.mStyles.horizontalAxisTitleColor = i;
    }

    public Paint.Align getVerticalLabelsSecondScaleAlign() {
        return this.mStyles.verticalLabelsSecondScaleAlign;
    }

    public void setVerticalLabelsSecondScaleAlign(Paint.Align align) {
        this.mStyles.verticalLabelsSecondScaleAlign = align;
    }

    public int getVerticalLabelsSecondScaleColor() {
        return this.mStyles.verticalLabelsSecondScaleColor;
    }

    public void setVerticalLabelsSecondScaleColor(int i) {
        this.mStyles.verticalLabelsSecondScaleColor = i;
    }

    public int getLabelVerticalSecondScaleWidth() {
        Integer num = this.mLabelVerticalSecondScaleWidth;
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public boolean isHorizontalLabelsVisible() {
        return this.mStyles.horizontalLabelsVisible;
    }

    public void setHorizontalLabelsVisible(boolean z) {
        this.mStyles.horizontalLabelsVisible = z;
    }

    public boolean isVerticalLabelsVisible() {
        return this.mStyles.verticalLabelsVisible;
    }

    public void setVerticalLabelsVisible(boolean z) {
        this.mStyles.verticalLabelsVisible = z;
    }

    public int getNumVerticalLabels() {
        return this.mNumVerticalLabels;
    }

    public void setNumVerticalLabels(int i) {
        this.mNumVerticalLabels = i;
    }

    public int getNumHorizontalLabels() {
        return this.mNumHorizontalLabels;
    }

    public void setNumHorizontalLabels(int i) {
        this.mNumHorizontalLabels = i;
    }

    public GridStyle getGridStyle() {
        return this.mStyles.gridStyle;
    }

    public void setGridStyle(GridStyle gridStyle) {
        this.mStyles.gridStyle = gridStyle;
    }

    public int getLabelsSpace() {
        return this.mStyles.labelsSpace;
    }

    public void setLabelsSpace(int i) {
        this.mStyles.labelsSpace = i;
    }

    public void setVerticalLabelsVAlign(VerticalLabelsVAlign verticalLabelsVAlign) {
        this.mStyles.verticalLabelsVAlign = verticalLabelsVAlign;
    }

    public VerticalLabelsVAlign getVerticalLabelsVAlign() {
        return this.mStyles.verticalLabelsVAlign;
    }
}
