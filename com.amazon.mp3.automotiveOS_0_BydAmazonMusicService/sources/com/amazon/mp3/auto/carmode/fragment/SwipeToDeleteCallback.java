package com.amazon.mp3.auto.carmode.fragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.auto.carmode.CarModeUtility;
import com.amazon.mp3.automotiveOS.R;
import com.facebook.react.modules.appstate.AppStateModule;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SwipeToDeleteCallback.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J2\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0015H\u0002J\u0018\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J@\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u00152\u0006\u0010!\u001a\u00020\b2\u0006\u0010\"\u001a\u00020#H\u0016J \u0010$\u001a\u00020#2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010%\u001a\u00020\u001dH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000eR\u0012\u0010\u000f\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000e¨\u0006&"}, d2 = {"Lcom/amazon/mp3/auto/carmode/fragment/SwipeToDeleteCallback;", "Landroidx/recyclerview/widget/ItemTouchHelper$SimpleCallback;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", AppStateModule.APP_STATE_BACKGROUND, "Landroid/graphics/drawable/ColorDrawable;", "backgroundColor", "", "clearPaint", "Landroid/graphics/Paint;", "deleteIcon", "Landroid/graphics/drawable/Drawable;", "intrinsicHeight", "Ljava/lang/Integer;", "intrinsicWidth", "clearCanvas", "", "c", "Landroid/graphics/Canvas;", "left", "", "top", "right", "bottom", "getMovementFlags", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "viewHolder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "onChildDraw", "dX", "dY", "actionState", "isCurrentlyActive", "", "onMove", "target", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {
    private final ColorDrawable background;
    private final int backgroundColor;
    private final Paint clearPaint;
    private final Drawable deleteIcon;
    private final Integer intrinsicHeight;
    private final Integer intrinsicWidth;

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        Intrinsics.checkNotNullParameter(target, "target");
        return false;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SwipeToDeleteCallback(Context context) {
        super(0, 4);
        Intrinsics.checkNotNullParameter(context, "context");
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.ic_delete);
        this.deleteIcon = drawable;
        this.intrinsicWidth = drawable == null ? null : Integer.valueOf(drawable.getIntrinsicWidth());
        this.intrinsicHeight = drawable != null ? Integer.valueOf(drawable.getIntrinsicHeight()) : null;
        this.background = new ColorDrawable();
        this.backgroundColor = ContextCompat.getColor(context, R.color.red_delete);
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        this.clearPaint = paint;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback, androidx.recyclerview.widget.ItemTouchHelper.Callback
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        if (viewHolder.getAdapterPosition() == CarModeUtility.INSTANCE.getCurrentPlayingPresetNumber()) {
            return 0;
        }
        return super.getMovementFlags(recyclerView, viewHolder);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        View view = viewHolder.itemView;
        Intrinsics.checkNotNullExpressionValue(view, "viewHolder.itemView");
        int bottom = view.getBottom() - view.getTop();
        if (((dX > 0.0f ? 1 : (dX == 0.0f ? 0 : -1)) == 0) && !isCurrentlyActive) {
            clearCanvas(c, view.getRight() + dX, view.getTop(), view.getRight(), view.getBottom());
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            return;
        }
        this.background.setColor(this.backgroundColor);
        this.background.setBounds(view.getRight() + ((int) dX), view.getTop(), view.getRight(), view.getBottom());
        this.background.draw(c);
        Integer num = this.intrinsicHeight;
        if (num != null) {
            int iIntValue = num.intValue();
            int i = (bottom - iIntValue) / 2;
            int top = view.getTop() + i;
            int right = view.getRight() - i;
            Integer num2 = this.intrinsicWidth;
            Intrinsics.checkNotNull(num2);
            int iIntValue2 = right - num2.intValue();
            int right2 = view.getRight() - i;
            int i2 = iIntValue + top;
            Drawable drawable = this.deleteIcon;
            if (drawable != null) {
                drawable.setBounds(iIntValue2, top, right2, i2);
            }
            Drawable drawable2 = this.deleteIcon;
            if (drawable2 != null) {
                drawable2.draw(c);
            }
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    private final void clearCanvas(Canvas c, float left, float top, float right, float bottom) {
        if (c == null) {
            return;
        }
        c.drawRect(left, top, right, bottom, this.clearPaint);
    }
}
