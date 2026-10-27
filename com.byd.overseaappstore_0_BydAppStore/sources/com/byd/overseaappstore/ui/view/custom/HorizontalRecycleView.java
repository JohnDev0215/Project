package com.byd.overseaappstore.ui.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/byd/overseaappstore/ui/view/custom/HorizontalRecycleView;", "Landroidx/recyclerview/widget/RecyclerView;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class HorizontalRecycleView extends RecyclerView {
    public float a;
    public float b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HorizontalRecycleView(@NotNull Context context, @NotNull AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this.a = -1.0f;
        this.b = -1.0f;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(@Nullable MotionEvent motionEvent) {
        float rawX = motionEvent != null ? motionEvent.getRawX() : 0.0f;
        float rawY = motionEvent != null ? motionEvent.getRawY() : 0.0f;
        Integer numValueOf = motionEvent != null ? Integer.valueOf(motionEvent.getAction()) : null;
        if (numValueOf != null && numValueOf.intValue() == 0) {
            getParent().requestDisallowInterceptTouchEvent(true);
        } else if (numValueOf != null && numValueOf.intValue() == 2) {
            if (Math.abs(rawX - this.a) + 0.0f > Math.abs(rawY - this.b) + 0.0f) {
                getParent().requestDisallowInterceptTouchEvent(true);
            } else {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
            this.a = rawX;
            this.b = rawY;
        }
        return super.dispatchTouchEvent(motionEvent);
    }
}
