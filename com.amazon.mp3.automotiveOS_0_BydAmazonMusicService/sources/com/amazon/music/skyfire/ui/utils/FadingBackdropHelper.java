package com.amazon.music.skyfire.ui.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.ImageView;
import androidx.core.graphics.ColorUtils;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.skyfire.ui.ktx.ViewKt;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.GradientStyleKey;
import com.amazon.ui.foundations.styles.GradientStyle;
import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: FadingBackdropHelper.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\u000bB\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\u000eBI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0002\u0010\u0014J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\u0010\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u0013H\u0002J\u000e\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001fJ\b\u0010 \u001a\u00020\u001aH\u0002R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\u00020\r8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/amazon/music/skyfire/ui/utils/FadingBackdropHelper;", "", "backdropView", "Landroid/widget/ImageView;", "scrollView", "Landroidx/core/widget/NestedScrollView;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/widget/ImageView;Landroidx/core/widget/NestedScrollView;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "(Landroid/widget/ImageView;Landroidx/recyclerview/widget/RecyclerView;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "backdropOverlay", "Landroid/view/View;", "(Landroid/widget/ImageView;Landroid/view/View;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "startGradient", "Lcom/amazon/ui/foundations/styles/GradientStyle;", "endGradient", "backdropTint", "", "(Landroid/widget/ImageView;Landroidx/core/widget/NestedScrollView;Landroidx/recyclerview/widget/RecyclerView;Landroid/view/View;Lcom/amazon/ui/foundations/styles/GradientStyle;Lcom/amazon/ui/foundations/styles/GradientStyle;Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "contentView", "getContentView", "()Landroid/view/View;", "resetBackdropMatrix", "", "resetContentGradient", "scrollY", "setBackdropURL", ImagesContract.URL, "", "setup", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FadingBackdropHelper {
    private final View backdropOverlay;
    private final Integer backdropTint;
    private final ImageView backdropView;
    private final GradientStyle endGradient;
    private final RecyclerView recyclerView;
    private final NestedScrollView scrollView;
    private final GradientStyle startGradient;

    public FadingBackdropHelper(ImageView backdropView, NestedScrollView nestedScrollView, RecyclerView recyclerView, View view, GradientStyle gradientStyle, GradientStyle gradientStyle2, Integer num) {
        Intrinsics.checkNotNullParameter(backdropView, "backdropView");
        this.backdropView = backdropView;
        this.scrollView = nestedScrollView;
        this.recyclerView = recyclerView;
        this.backdropOverlay = view;
        this.startGradient = gradientStyle;
        this.endGradient = gradientStyle2;
        this.backdropTint = num;
        setup();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FadingBackdropHelper(ImageView backdropView, NestedScrollView scrollView, StyleSheet styleSheet) {
        this(backdropView, scrollView, null, null, styleSheet.getGradientStyle(GradientStyleKey.GRADIENT_1), styleSheet.getGradientStyle(GradientStyleKey.GRADIENT_2), styleSheet.getColor(ColorKey.COLOR_SECONDARY, AlphaKey.GLASS_4));
        Intrinsics.checkNotNullParameter(backdropView, "backdropView");
        Intrinsics.checkNotNullParameter(scrollView, "scrollView");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FadingBackdropHelper(ImageView backdropView, RecyclerView recyclerView, StyleSheet styleSheet) {
        this(backdropView, null, recyclerView, null, styleSheet.getGradientStyle(GradientStyleKey.GRADIENT_1), styleSheet.getGradientStyle(GradientStyleKey.GRADIENT_2), styleSheet.getColor(ColorKey.COLOR_SECONDARY, AlphaKey.GLASS_4));
        Intrinsics.checkNotNullParameter(backdropView, "backdropView");
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FadingBackdropHelper(ImageView backdropView, View backdropOverlay, StyleSheet styleSheet) {
        this(backdropView, null, null, backdropOverlay, styleSheet.getGradientStyle(GradientStyleKey.GRADIENT_1), styleSheet.getGradientStyle(GradientStyleKey.GRADIENT_2), styleSheet.getColor(ColorKey.COLOR_SECONDARY, AlphaKey.GLASS_4));
        Intrinsics.checkNotNullParameter(backdropView, "backdropView");
        Intrinsics.checkNotNullParameter(backdropOverlay, "backdropOverlay");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
    }

    public final void setBackdropURL(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        ViewKt.setImageUrl(this.backdropView, url, new Function0<Unit>() { // from class: com.amazon.music.skyfire.ui.utils.FadingBackdropHelper.setBackdropURL.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                FadingBackdropHelper.this.resetBackdropMatrix();
            }
        });
    }

    private final void setup() {
        NestedScrollView nestedScrollView = this.scrollView;
        if (nestedScrollView != null) {
            nestedScrollView.post(new Runnable() { // from class: com.amazon.music.skyfire.ui.utils.FadingBackdropHelper$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    FadingBackdropHelper.m2206setup$lambda1(this.f$0);
                }
            });
        }
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView != null) {
            recyclerView.post(new Runnable() { // from class: com.amazon.music.skyfire.ui.utils.FadingBackdropHelper$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    FadingBackdropHelper.m2208setup$lambda2(this.f$0);
                }
            });
        }
        View view = this.backdropOverlay;
        if (view != null) {
            view.post(new Runnable() { // from class: com.amazon.music.skyfire.ui.utils.FadingBackdropHelper$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    FadingBackdropHelper.m2209setup$lambda3(this.f$0);
                }
            });
        }
        this.backdropView.post(new Runnable() { // from class: com.amazon.music.skyfire.ui.utils.FadingBackdropHelper$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                FadingBackdropHelper.m2210setup$lambda5(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setup$lambda-1, reason: not valid java name */
    public static final void m2206setup$lambda1(final FadingBackdropHelper this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() { // from class: com.amazon.music.skyfire.ui.utils.FadingBackdropHelper$$ExternalSyntheticLambda4
            @Override // androidx.core.widget.NestedScrollView.OnScrollChangeListener
            public final void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
                FadingBackdropHelper.m2207setup$lambda1$lambda0(this.f$0, nestedScrollView, i, i2, i3, i4);
            }
        });
        this$0.resetContentGradient(this$0.scrollView.getScrollY());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setup$lambda-1$lambda-0, reason: not valid java name */
    public static final void m2207setup$lambda1$lambda0(FadingBackdropHelper this$0, NestedScrollView noName_0, int i, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        this$0.resetContentGradient(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setup$lambda-2, reason: not valid java name */
    public static final void m2208setup$lambda2(final FadingBackdropHelper this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.amazon.music.skyfire.ui.utils.FadingBackdropHelper$setup$2$1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
                this.this$0.resetContentGradient(recyclerView.computeVerticalScrollOffset());
            }
        });
        this$0.resetContentGradient(this$0.recyclerView.computeVerticalScrollOffset());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setup$lambda-3, reason: not valid java name */
    public static final void m2209setup$lambda3(FadingBackdropHelper this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.resetContentGradient(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setup$lambda-5, reason: not valid java name */
    public static final void m2210setup$lambda5(FadingBackdropHelper this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Integer num = this$0.backdropTint;
        if (num != null) {
            this$0.backdropView.setColorFilter(num.intValue());
        }
        this$0.resetBackdropMatrix();
    }

    private final View getContentView() {
        NestedScrollView nestedScrollView = this.scrollView;
        if (!(nestedScrollView instanceof View)) {
            nestedScrollView = null;
        }
        if (nestedScrollView != null) {
            return nestedScrollView;
        }
        RecyclerView recyclerView = this.recyclerView;
        if (!(recyclerView instanceof View)) {
            recyclerView = null;
        }
        if (recyclerView != null) {
            return recyclerView;
        }
        View view = this.backdropOverlay;
        View view2 = view instanceof View ? view : null;
        if (view2 != null) {
            return view2;
        }
        throw new IllegalStateException("No content view provided".toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetBackdropMatrix() {
        float width = getContentView().getWidth();
        float height = getContentView().getHeight();
        Drawable drawable = this.backdropView.getDrawable();
        BitmapDrawable bitmapDrawable = drawable instanceof BitmapDrawable ? (BitmapDrawable) drawable : null;
        Bitmap bitmap = bitmapDrawable != null ? bitmapDrawable.getBitmap() : null;
        if (bitmap == null) {
            return;
        }
        int width2 = bitmap.getWidth();
        int height2 = bitmap.getHeight();
        if (width2 == 0 || height2 == 0) {
            return;
        }
        ImageView imageView = this.backdropView;
        Matrix matrix = new Matrix();
        float f = width2;
        float fMax = Math.max(height / height2, width / f);
        matrix.setScale(fMax, fMax);
        matrix.postTranslate((width - (f * fMax)) / 2, 0.0f);
        imageView.setImageMatrix(matrix);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetContentGradient(int scrollY) {
        GradientStyle gradientStyle;
        GradientStyle gradientStyle2 = this.startGradient;
        if (gradientStyle2 == null || (gradientStyle = this.endGradient) == null) {
            return;
        }
        float fCoerceIn = RangesKt___RangesKt.coerceIn(scrollY / getContentView().getHeight(), 0.0f, 1.0f);
        int[] iArr = {ColorUtils.blendARGB(gradientStyle2.getColors().get(0).intValue(), gradientStyle.getColors().get(0).intValue(), fCoerceIn), ColorUtils.blendARGB(gradientStyle2.getColors().get(1).intValue(), gradientStyle.getColors().get(1).intValue(), fCoerceIn)};
        Drawable background = getContentView().getBackground();
        if (background instanceof GradientDrawable) {
            ((GradientDrawable) background).setColors(iArr);
        } else {
            getContentView().setBackground(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, iArr));
        }
    }
}
