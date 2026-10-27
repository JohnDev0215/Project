package com.byd.bluetoothcall.view.d;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.recyclerview.widget.RecyclerView;
import com.bydux.api.R;

/* JADX INFO: compiled from: VerticalScrollBarDecoration.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends RecyclerView.n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Paint f3453a = new Paint();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private transient Context f3454b;

    public d(Context context) {
        this.f3454b = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void i(Canvas canvas, RecyclerView recyclerView, RecyclerView.z zVar) {
        super.i(canvas, recyclerView, zVar);
        int iComputeVerticalScrollExtent = recyclerView.computeVerticalScrollExtent();
        int iComputeVerticalScrollRange = recyclerView.computeVerticalScrollRange();
        int iComputeVerticalScrollOffset = recyclerView.computeVerticalScrollOffset();
        int i = iComputeVerticalScrollRange - iComputeVerticalScrollExtent;
        if (i > 0) {
            float width = (recyclerView.getWidth() - 15.0f) - 5.0f;
            float height = (recyclerView.getHeight() / 2.0f) - 85.0f;
            this.f3453a.setAntiAlias(true);
            this.f3453a.setColor(this.f3454b.getColor(R.color.list_scroolbar_color));
            this.f3453a.setStrokeCap(Paint.Cap.ROUND);
            this.f3453a.setStrokeWidth(5.0f);
            this.f3453a.setColor(this.f3454b.getColor(R.color.list_scroolbar_bg_color));
            canvas.drawLine(width, height, width, height + 170.0f, this.f3453a);
            this.f3453a.setColor(this.f3454b.getColor(R.color.list_scroolbar_color));
            float f = (iComputeVerticalScrollOffset / i) * 60.0f;
            canvas.drawLine(width, height + f, width, height + 110.0f + f, this.f3453a);
        }
    }
}
