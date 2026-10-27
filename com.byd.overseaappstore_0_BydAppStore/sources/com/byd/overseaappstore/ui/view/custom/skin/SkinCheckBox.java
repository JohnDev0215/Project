package com.byd.overseaappstore.ui.view.custom.skin;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatCheckBox;
import com.byd.overseaappstore.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import spi.ct;
import spi.e;
import spi.vm;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0011\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u0019\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u0005\u0010\tB!\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u0005\u0010\f¨\u0006\r"}, d2 = {"Lcom/byd/overseaappstore/ui/view/custom/skin/SkinCheckBox;", "Landroidx/appcompat/widget/AppCompatCheckBox;", "", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/util/AttributeSet;", "attrs", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "", "defStyleAttr", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class SkinCheckBox extends AppCompatCheckBox {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SkinCheckBox(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SkinCheckBox(@NotNull Context context, @NotNull AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        a(attrs);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SkinCheckBox(@NotNull Context context, @NotNull AttributeSet attrs, int i) {
        super(context, attrs, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        a(attrs);
    }

    public final void a(@NotNull AttributeSet attrs) {
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.styleSkin);
        Intrinsics.checkNotNullExpressionValue(typedArrayObtainStyledAttributes, "context.obtainStyledAttr…s, R.styleable.styleSkin)");
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(1, 0);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(4, 0);
        ct.a(e.c("UIStyle initStyle resBgId:", resourceId), new Object[0]);
        typedArrayObtainStyledAttributes.recycle();
        if (resourceId != 0) {
            vm vmVar = vm.a;
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            Context context2 = getContext();
            Intrinsics.checkNotNullExpressionValue(context2, "context");
            setCompoundDrawablesRelativeWithIntrinsicBounds(vmVar.c(context, vmVar.a(context2, resourceId)), (Drawable) null, (Drawable) null, (Drawable) null);
        }
        if (resourceId2 != 0) {
            vm vmVar2 = vm.a;
            Context context3 = getContext();
            Intrinsics.checkNotNullExpressionValue(context3, "context");
            Context context4 = getContext();
            Intrinsics.checkNotNullExpressionValue(context4, "context");
            setTextColor(vmVar2.b(context3, vmVar2.a(context4, resourceId2)));
        }
    }
}
