package com.byd.overseaappstore.ui.view.custom.skin;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.content.ContextCompat;
import com.byd.overseaappstore.R;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import spi.ct;
import spi.e;
import spi.ns;
import spi.vm;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u0011\b\u0016\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eB\u0019\b\u0016\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\r\u0010\u0011B!\b\u0016\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0012\u001a\u00020\u0003¢\u0006\u0004\b\r\u0010\u0013J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0016J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0003H\u0016J\u000e\u0010\n\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0003¨\u0006\u0014"}, d2 = {"Lcom/byd/overseaappstore/ui/view/custom/skin/SkinView;", "Landroid/view/View;", "", "", TypedValues.Custom.S_COLOR, "", "setBackgroundColor", "resId", "setBackgroundResource", "drawableId", "setBackgroundDrawableId", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/util/AttributeSet;", "attrs", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class SkinView extends View {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SkinView(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SkinView(@NotNull Context context, @NotNull AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        a(attrs);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SkinView(@NotNull Context context, @NotNull AttributeSet attrs, int i) {
        super(context, attrs, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        a(attrs);
    }

    public final void a(@NotNull AttributeSet attrs) {
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.styleSkin);
        Intrinsics.checkNotNullExpressionValue(typedArrayObtainStyledAttributes, "context.obtainStyledAttr…s, R.styleable.styleSkin)");
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        ct.a(e.c("UIStyle initStyle resBgId:", resourceId), new Object[0]);
        typedArrayObtainStyledAttributes.recycle();
        if (resourceId != 0) {
            vm vmVar = vm.a;
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            Context context2 = getContext();
            Intrinsics.checkNotNullExpressionValue(context2, "context");
            setBackground(vmVar.c(context, vmVar.a(context2, resourceId)));
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int color) {
        ns nsVar = ns.a.a;
        getContext();
        Objects.requireNonNull(nsVar);
        vm vmVar = vm.a;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        super.setBackgroundColor(vmVar.d(context, color));
    }

    public final void setBackgroundDrawableId(int drawableId) {
        ns nsVar = ns.a.a;
        getContext();
        Objects.requireNonNull(nsVar);
        setBackground(ContextCompat.getDrawable(getContext(), drawableId));
    }

    @Override // android.view.View
    public void setBackgroundResource(int resId) {
        ns nsVar = ns.a.a;
        getContext();
        Objects.requireNonNull(nsVar);
        super.setBackgroundResource(resId);
    }
}
