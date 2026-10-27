package com.amazon.music.skyfire.ui.widgets.binders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.amazon.music.views.library.views.PillCellView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PillNavigatorItemBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u001c\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u0012\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\u0010"}, d2 = {"com/amazon/music/skyfire/ui/widgets/binders/PillNavigatorItemBinder$bind$1$2$target$1", "Lcom/squareup/picasso/Target;", "onBitmapFailed", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "d", "Landroid/graphics/drawable/Drawable;", "onBitmapLoaded", "bmp", "Landroid/graphics/Bitmap;", "lf", "Lcom/squareup/picasso/Picasso$LoadedFrom;", "onPrepareLoad", "p0", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PillNavigatorItemBinder$bind$1$2$target$1 implements Target {
    final /* synthetic */ PillCellView $this_with;
    final /* synthetic */ PillCellView $view;

    @Override // com.squareup.picasso.Target
    public void onBitmapFailed(Exception e, Drawable d) {
        Intrinsics.checkNotNullParameter(e, "e");
    }

    @Override // com.squareup.picasso.Target
    public void onPrepareLoad(Drawable p0) {
    }

    PillNavigatorItemBinder$bind$1$2$target$1(PillCellView pillCellView, PillCellView pillCellView2) {
        this.$view = pillCellView;
        this.$this_with = pillCellView2;
    }

    @Override // com.squareup.picasso.Target
    public void onBitmapLoaded(final Bitmap bmp, Picasso.LoadedFrom lf) {
        if (bmp == null) {
            return;
        }
        PillCellView pillCellView = this.$view;
        final PillCellView pillCellView2 = this.$this_with;
        pillCellView.post(new Runnable() { // from class: com.amazon.music.skyfire.ui.widgets.binders.PillNavigatorItemBinder$bind$1$2$target$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                PillNavigatorItemBinder$bind$1$2$target$1.m2235onBitmapLoaded$lambda1(pillCellView2, bmp);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBitmapLoaded$lambda-1, reason: not valid java name */
    public static final void m2235onBitmapLoaded$lambda1(PillCellView this_with, Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(this_with, "$this_with");
        try {
            Context context = this_with.getContext();
            if (context == null) {
                return;
            }
            this_with.setIcon(new BitmapDrawable(context.getResources(), bitmap));
        } catch (Exception unused) {
            PillNavigatorItemBinder.logger.error("Unable to set pill cell view icon");
        }
    }
}
