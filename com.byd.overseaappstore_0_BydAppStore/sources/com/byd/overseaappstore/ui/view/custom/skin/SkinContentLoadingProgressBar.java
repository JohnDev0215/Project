package com.byd.overseaappstore.ui.view.custom.skin;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.core.widget.ContentLoadingProgressBar;
import com.byd.overseaappstore.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import spi.ct;
import spi.e;
import spi.vm;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0011\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u0019\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u0005\u0010\t¨\u0006\n"}, d2 = {"Lcom/byd/overseaappstore/ui/view/custom/skin/SkinContentLoadingProgressBar;", "Landroidx/core/widget/ContentLoadingProgressBar;", "", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/util/AttributeSet;", "attrs", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class SkinContentLoadingProgressBar extends ContentLoadingProgressBar {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SkinContentLoadingProgressBar(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SkinContentLoadingProgressBar(@NotNull Context context, @NotNull AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.styleSkin);
        Intrinsics.checkNotNullExpressionValue(typedArrayObtainStyledAttributes, "context.obtainStyledAttr…s, R.styleable.styleSkin)");
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(2, 0);
        ct.a(e.c("UIStyle initStyle resProgressBarId:", resourceId), new Object[0]);
        typedArrayObtainStyledAttributes.recycle();
        if (resourceId != 0) {
            vm vmVar = vm.a;
            Context context2 = getContext();
            Intrinsics.checkNotNullExpressionValue(context2, "context");
            Context context3 = getContext();
            Intrinsics.checkNotNullExpressionValue(context3, "context");
            setProgressDrawable(vmVar.c(context2, vmVar.a(context3, resourceId)));
        }
    }
}
