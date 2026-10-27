package com.jjoe64.graphview.series;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPointInterface;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class PointsGraphSeries<E extends DataPointInterface> extends BaseSeries<E> {
    private CustomShape mCustomShape;
    private Paint mPaint;
    private PointsGraphSeries<E>.Styles mStyles;

    public interface CustomShape {
        void draw(Canvas canvas, Paint paint, float f, float f2, DataPointInterface dataPointInterface);
    }

    public enum Shape {
        POINT,
        TRIANGLE,
        RECTANGLE
    }

    private final class Styles {
        Shape shape;
        float size;

        private Styles() {
        }
    }

    public PointsGraphSeries() {
        init();
    }

    public PointsGraphSeries(E[] eArr) {
        super(eArr);
        init();
    }

    protected void init() {
        PointsGraphSeries<E>.Styles styles = new Styles();
        this.mStyles = styles;
        styles.size = 20.0f;
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setStrokeCap(Paint.Cap.ROUND);
        setShape(Shape.POINT);
    }

    @Override // com.jjoe64.graphview.series.Series
    public void draw(GraphView graphView, Canvas canvas, boolean z) {
        double maxY;
        double minY;
        float f;
        float f2;
        float f3;
        float f4;
        boolean z2;
        resetDataPoints();
        double maxX = graphView.getViewport().getMaxX(false);
        double minX = graphView.getViewport().getMinX(false);
        if (z) {
            maxY = graphView.getSecondScale().getMaxY(false);
            minY = graphView.getSecondScale().getMinY(false);
        } else {
            maxY = graphView.getViewport().getMaxY(false);
            minY = graphView.getViewport().getMinY(false);
        }
        double d = minY;
        Iterator<E> values = getValues(minX, maxX);
        this.mPaint.setColor(getColor());
        double d2 = maxY - d;
        double d3 = maxX - minX;
        float graphContentHeight = graphView.getGraphContentHeight();
        float graphContentWidth = graphView.getGraphContentWidth();
        float graphContentLeft = graphView.getGraphContentLeft();
        float graphContentTop = graphView.getGraphContentTop();
        while (values.hasNext()) {
            E next = values.next();
            double d4 = minX;
            double d5 = graphContentHeight;
            double y = ((next.getY() - d) / d2) * d5;
            double d6 = d;
            double d7 = graphContentWidth;
            Iterator<E> it = values;
            double x = d7 * ((next.getX() - d4) / d3);
            boolean z3 = x > d7;
            if (y < 0.0d) {
                z3 = true;
            }
            if (y > d5) {
                z3 = true;
            }
            if (x < 0.0d) {
                z3 = true;
            }
            float f5 = ((float) x) + 1.0f + graphContentLeft;
            float f6 = ((float) (((double) graphContentTop) - y)) + graphContentHeight;
            registerDataPoint(f5, f6, next);
            if (z3) {
                f = graphContentTop;
                f2 = graphContentLeft;
                f3 = graphContentWidth;
                f4 = graphContentHeight;
            } else {
                CustomShape customShape = this.mCustomShape;
                if (customShape != null) {
                    f = graphContentTop;
                    f2 = graphContentLeft;
                    f3 = graphContentWidth;
                    f4 = graphContentHeight;
                    customShape.draw(canvas, this.mPaint, f5, f6, next);
                } else {
                    f = graphContentTop;
                    f2 = graphContentLeft;
                    f3 = graphContentWidth;
                    f4 = graphContentHeight;
                    if (this.mStyles.shape == Shape.POINT) {
                        canvas.drawCircle(f5, f6, this.mStyles.size, this.mPaint);
                    } else if (this.mStyles.shape == Shape.RECTANGLE) {
                        canvas.drawRect(f5 - this.mStyles.size, f6 - this.mStyles.size, f5 + this.mStyles.size, f6 + this.mStyles.size, this.mPaint);
                    } else {
                        if (this.mStyles.shape == Shape.TRIANGLE) {
                            z2 = false;
                            double d8 = f6;
                            drawArrows(new Point[]{new Point((int) f5, (int) (f6 - getSize())), new Point((int) (getSize() + f5), (int) ((((double) getSize()) * 0.67d) + d8)), new Point((int) (f5 - getSize()), (int) (d8 + (((double) getSize()) * 0.67d)))}, canvas, this.mPaint);
                        }
                        graphContentTop = f;
                        graphContentLeft = f2;
                        graphContentWidth = f3;
                        graphContentHeight = f4;
                        minX = d4;
                        d = d6;
                        values = it;
                    }
                }
            }
            z2 = false;
            graphContentTop = f;
            graphContentLeft = f2;
            graphContentWidth = f3;
            graphContentHeight = f4;
            minX = d4;
            d = d6;
            values = it;
        }
    }

    private void drawArrows(Point[] pointArr, Canvas canvas, Paint paint) {
        canvas.drawVertices(Canvas.VertexMode.TRIANGLES, 8, new float[]{pointArr[0].x, pointArr[0].y, pointArr[1].x, pointArr[1].y, pointArr[2].x, pointArr[2].y, pointArr[0].x, pointArr[0].y}, 0, null, 0, null, 0, null, 0, 0, paint);
        Path path = new Path();
        path.moveTo(pointArr[0].x, pointArr[0].y);
        path.lineTo(pointArr[1].x, pointArr[1].y);
        path.lineTo(pointArr[2].x, pointArr[2].y);
        canvas.drawPath(path, paint);
    }

    public float getSize() {
        return this.mStyles.size;
    }

    public void setSize(float f) {
        this.mStyles.size = f;
    }

    public Shape getShape() {
        return this.mStyles.shape;
    }

    public void setShape(Shape shape) {
        this.mStyles.shape = shape;
    }

    public void setCustomShape(CustomShape customShape) {
        this.mCustomShape = customShape;
    }
}
