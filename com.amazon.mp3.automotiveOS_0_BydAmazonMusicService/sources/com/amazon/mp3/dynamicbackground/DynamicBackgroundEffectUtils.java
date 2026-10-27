package com.amazon.mp3.dynamicbackground;

import android.content.Context;
import android.graphics.Bitmap;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.ui.foundations.styles.GradientStyle;
import com.amazon.ui.foundations.utils.BitmapEffectUtils;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: DynamicBackgroundEffectUtils.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J \u0010\f\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J \u0010\r\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J.\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u000f2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u000bH\u0007J,\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u00122\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u000b¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/dynamicbackground/DynamicBackgroundEffectUtils;", "", "()V", "applyBlurStyle", "Landroid/graphics/Bitmap;", "context", "Landroid/content/Context;", "bitmap", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "condition", "Lcom/amazon/mp3/dynamicbackground/DynamicBackgroundImageCondition;", "applyColorOverlay", "applyGradientOverlay", "getTransformBlurredImageSubscription", "Lrx/Observable;", "dynamicBackgroundImageCondition", "getTransformBlurredImageSubscriptionV2", "Lio/reactivex/rxjava3/core/Observable;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DynamicBackgroundEffectUtils {
    public static final DynamicBackgroundEffectUtils INSTANCE = new DynamicBackgroundEffectUtils();

    private DynamicBackgroundEffectUtils() {
    }

    @Deprecated(message = "Use getTransformBlurredImageSubscriptionV2")
    public final synchronized Observable<Bitmap> getTransformBlurredImageSubscription(final Bitmap bitmap, final Context context, final StyleSheet styleSheet, final DynamicBackgroundImageCondition dynamicBackgroundImageCondition) {
        Observable<Bitmap> observableObserveOn;
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(dynamicBackgroundImageCondition, "dynamicBackgroundImageCondition");
        observableObserveOn = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.dynamicbackground.DynamicBackgroundEffectUtils$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DynamicBackgroundEffectUtils.m962getTransformBlurredImageSubscription$lambda6(bitmap, dynamicBackgroundImageCondition, styleSheet, context, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
        Intrinsics.checkNotNullExpressionValue(observableObserveOn, "create<Bitmap>{ subscrib…dSchedulers.mainThread())");
        return observableObserveOn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getTransformBlurredImageSubscription$lambda-6, reason: not valid java name */
    public static final void m962getTransformBlurredImageSubscription$lambda6(Bitmap bitmap, DynamicBackgroundImageCondition dynamicBackgroundImageCondition, StyleSheet styleSheet, Context context, Subscriber subscriber) {
        Integer color;
        Intrinsics.checkNotNullParameter(bitmap, "$bitmap");
        Intrinsics.checkNotNullParameter(dynamicBackgroundImageCondition, "$dynamicBackgroundImageCondition");
        Intrinsics.checkNotNullParameter(styleSheet, "$styleSheet");
        Intrinsics.checkNotNullParameter(context, "$context");
        Bitmap bitmap2 = null;
        if (dynamicBackgroundImageCondition.getBlurStyleKey() != null) {
            Float blurStyle = styleSheet.getBlurStyle(dynamicBackgroundImageCondition.getBlurStyleKey());
            if (blurStyle == null) {
                bitmap = null;
            } else {
                bitmap = BitmapEffectUtils.INSTANCE.getBlurBitmap(context, bitmap, blurStyle.floatValue());
            }
        }
        if (bitmap != null) {
            if (dynamicBackgroundImageCondition.getColorOverlayColorKey() != null) {
                if (dynamicBackgroundImageCondition.getColorOverlayAlphaKey() != null) {
                    color = styleSheet.getColor(dynamicBackgroundImageCondition.getColorOverlayColorKey(), dynamicBackgroundImageCondition.getColorOverlayAlphaKey());
                } else {
                    color = styleSheet.getColor(dynamicBackgroundImageCondition.getColorOverlayColorKey());
                }
                if (color == null) {
                    bitmap = null;
                } else {
                    bitmap = BitmapEffectUtils.INSTANCE.getColorOverlayBitmap(bitmap, color.intValue());
                }
            }
            if (bitmap != null) {
                if (dynamicBackgroundImageCondition.getGradientStyleKey() == null) {
                    bitmap2 = bitmap;
                } else {
                    GradientStyle gradientStyle = styleSheet.getGradientStyle(dynamicBackgroundImageCondition.getGradientStyleKey());
                    if (gradientStyle != null) {
                        bitmap = BitmapEffectUtils.INSTANCE.getGradientOverlayBitmap(bitmap, false, CollectionsKt___CollectionsKt.toFloatArray(gradientStyle.getPositions()), CollectionsKt___CollectionsKt.toIntArray(gradientStyle.getColors()));
                        bitmap2 = bitmap;
                    }
                }
            }
        }
        subscriber.onNext(bitmap2);
        subscriber.onCompleted();
    }

    public final io.reactivex.rxjava3.core.Observable<Bitmap> getTransformBlurredImageSubscriptionV2(final Bitmap bitmap, final Context context, final StyleSheet styleSheet, final DynamicBackgroundImageCondition dynamicBackgroundImageCondition) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(dynamicBackgroundImageCondition, "dynamicBackgroundImageCondition");
        io.reactivex.rxjava3.core.Observable<Bitmap> observableObserveOn = io.reactivex.rxjava3.core.Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.mp3.dynamicbackground.DynamicBackgroundEffectUtils$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                DynamicBackgroundEffectUtils.m963getTransformBlurredImageSubscriptionV2$lambda7(context, bitmap, styleSheet, dynamicBackgroundImageCondition, observableEmitter);
            }
        }).subscribeOn(io.reactivex.rxjava3.schedulers.Schedulers.computation()).observeOn(io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread());
        Intrinsics.checkNotNullExpressionValue(observableObserveOn, "create<Bitmap> {\n       …dSchedulers.mainThread())");
        return observableObserveOn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getTransformBlurredImageSubscriptionV2$lambda-7, reason: not valid java name */
    public static final void m963getTransformBlurredImageSubscriptionV2$lambda7(Context context, Bitmap bitmap, StyleSheet styleSheet, DynamicBackgroundImageCondition dynamicBackgroundImageCondition, ObservableEmitter observableEmitter) {
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(bitmap, "$bitmap");
        Intrinsics.checkNotNullParameter(styleSheet, "$styleSheet");
        Intrinsics.checkNotNullParameter(dynamicBackgroundImageCondition, "$dynamicBackgroundImageCondition");
        DynamicBackgroundEffectUtils dynamicBackgroundEffectUtils = INSTANCE;
        observableEmitter.onNext(dynamicBackgroundEffectUtils.applyGradientOverlay(dynamicBackgroundEffectUtils.applyColorOverlay(dynamicBackgroundEffectUtils.applyBlurStyle(context, bitmap, styleSheet, dynamicBackgroundImageCondition), styleSheet, dynamicBackgroundImageCondition), styleSheet, dynamicBackgroundImageCondition));
    }

    private final Bitmap applyBlurStyle(Context context, Bitmap bitmap, StyleSheet styleSheet, DynamicBackgroundImageCondition condition) {
        Float blurStyle;
        if (condition.getBlurStyleKey() == null || (blurStyle = styleSheet.getBlurStyle(condition.getBlurStyleKey())) == null) {
            return bitmap;
        }
        Bitmap blurBitmap = BitmapEffectUtils.INSTANCE.getBlurBitmap(context, bitmap, blurStyle.floatValue());
        bitmap.recycle();
        return blurBitmap == null ? bitmap : blurBitmap;
    }

    private final Bitmap applyColorOverlay(Bitmap bitmap, StyleSheet styleSheet, DynamicBackgroundImageCondition condition) {
        Integer color;
        if (condition.getColorOverlayColorKey() == null) {
            return bitmap;
        }
        if (condition.getColorOverlayAlphaKey() != null) {
            color = styleSheet.getColor(condition.getColorOverlayColorKey(), condition.getColorOverlayAlphaKey());
        } else {
            color = styleSheet.getColor(condition.getColorOverlayColorKey());
        }
        if (color == null) {
            return bitmap;
        }
        Bitmap colorOverlayBitmap = BitmapEffectUtils.INSTANCE.getColorOverlayBitmap(bitmap, color.intValue());
        bitmap.recycle();
        return colorOverlayBitmap == null ? bitmap : colorOverlayBitmap;
    }

    private final Bitmap applyGradientOverlay(Bitmap bitmap, StyleSheet styleSheet, DynamicBackgroundImageCondition condition) {
        GradientStyle gradientStyle;
        if (condition.getGradientStyleKey() == null || (gradientStyle = styleSheet.getGradientStyle(condition.getGradientStyleKey())) == null) {
            return bitmap;
        }
        Bitmap gradientOverlayBitmap = BitmapEffectUtils.INSTANCE.getGradientOverlayBitmap(bitmap, false, CollectionsKt___CollectionsKt.toFloatArray(gradientStyle.getPositions()), CollectionsKt___CollectionsKt.toIntArray(gradientStyle.getColors()));
        bitmap.recycle();
        return gradientOverlayBitmap == null ? bitmap : gradientOverlayBitmap;
    }
}
