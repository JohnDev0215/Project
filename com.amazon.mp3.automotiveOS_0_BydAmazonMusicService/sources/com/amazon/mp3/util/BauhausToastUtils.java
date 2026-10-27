package com.amazon.mp3.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.DrawableRes;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.extensions.ContextKt;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.BauhausToast;
import com.amazon.music.views.library.views.ToastView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: BauhausToastUtils.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\t\u001a\u00020\n2\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\fJ\u0016\u0010\r\u001a\u00020\n2\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\fJ\"\u0010\u000f\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0007J$\u0010\u000f\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0014\u001a\u00020\u0013H\u0007J\"\u0010\u0017\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0007J$\u0010\u0017\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0014\u001a\u00020\u0013H\u0007J@\u0010\u0018\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0014\u001a\u00020\u00132\b\b\u0001\u0010\u0019\u001a\u00020\u00132\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\fH\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/amazon/mp3/util/BauhausToastUtils;", "", "()V", "currentDisplayedBauhausToast", "Lcom/amazon/music/views/library/views/BauhausToast;", "currentDisplayedToast", "Landroid/widget/Toast;", "isBauhausIAMEnabled", "", "setOnContentClickListener", "", "onContentClickListener", "Lkotlin/Function0;", "setOnDismissListener", "onDismissListener", "showDefaultToast", "context", "Landroid/content/Context;", "textResId", "", "duration", "text", "", "showTextToast", "showTextToastWithIcon", "iconResource", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BauhausToastUtils {
    private static BauhausToast currentDisplayedBauhausToast;
    private static Toast currentDisplayedToast;
    public static final BauhausToastUtils INSTANCE = new BauhausToastUtils();
    private static final boolean isBauhausIAMEnabled = AmazonApplication.getCapabilities().isBauhausInAppMessagingEnabled();

    private BauhausToastUtils() {
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x003a A[Catch: all -> 0x003f, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0011, B:14:0x0023, B:18:0x002b, B:17:0x0028, B:11:0x001b, B:19:0x003a), top: B:25:0x0003 }] */
    @kotlin.jvm.JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final synchronized void showTextToast(final android.content.Context r5, final java.lang.String r6, int r7) {
        /*
            java.lang.Class<com.amazon.mp3.util.BauhausToastUtils> r0 = com.amazon.mp3.util.BauhausToastUtils.class
            monitor-enter(r0)
            androidx.lifecycle.MutableLiveData r1 = com.amazon.mp3.styles.StyleSheetProvider.getStyleSheet()     // Catch: java.lang.Throwable -> L3f
            java.lang.Object r1 = r1.getValue()     // Catch: java.lang.Throwable -> L3f
            com.amazon.music.views.library.styles.StyleSheet r1 = (com.amazon.music.views.library.styles.StyleSheet) r1     // Catch: java.lang.Throwable -> L3f
            boolean r2 = com.amazon.mp3.util.BauhausToastUtils.isBauhausIAMEnabled     // Catch: java.lang.Throwable -> L3f
            if (r2 == 0) goto L3a
            boolean r2 = r5 instanceof android.app.Activity     // Catch: java.lang.Throwable -> L3f
            if (r2 == 0) goto L3a
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L1b
        L19:
            r2 = r3
            goto L21
        L1b:
            boolean r4 = r1.getIsInitCompleted()     // Catch: java.lang.Throwable -> L3f
            if (r4 != r2) goto L19
        L21:
            if (r2 == 0) goto L3a
            android.widget.Toast r7 = com.amazon.mp3.util.BauhausToastUtils.currentDisplayedToast     // Catch: java.lang.Throwable -> L3f
            if (r7 != 0) goto L28
            goto L2b
        L28:
            r7.cancel()     // Catch: java.lang.Throwable -> L3f
        L2b:
            r7 = 0
            com.amazon.mp3.util.BauhausToastUtils.currentDisplayedToast = r7     // Catch: java.lang.Throwable -> L3f
            r7 = r5
            android.app.Activity r7 = (android.app.Activity) r7     // Catch: java.lang.Throwable -> L3f
            com.amazon.mp3.util.BauhausToastUtils$$ExternalSyntheticLambda0 r2 = new com.amazon.mp3.util.BauhausToastUtils$$ExternalSyntheticLambda0     // Catch: java.lang.Throwable -> L3f
            r2.<init>()     // Catch: java.lang.Throwable -> L3f
            r7.runOnUiThread(r2)     // Catch: java.lang.Throwable -> L3f
            goto L3d
        L3a:
            showDefaultToast(r5, r6, r7)     // Catch: java.lang.Throwable -> L3f
        L3d:
            monitor-exit(r0)
            return
        L3f:
            r5 = move-exception
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.util.BauhausToastUtils.showTextToast(android.content.Context, java.lang.String, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showTextToast$lambda-1, reason: not valid java name */
    public static final void m1646showTextToast$lambda1(Context context, StyleSheet styleSheet, String str) {
        BauhausToast bauhausToastMakeText = BauhausToast.INSTANCE.makeText((Activity) context, styleSheet, str);
        if ((context instanceof MusicHomeActivity) && bauhausToastMakeText != null) {
            View anchorViewForToast = ((MusicHomeActivity) context).getAnchorViewForToast();
            Intrinsics.checkNotNullExpressionValue(anchorViewForToast, "context.anchorViewForToast");
            bauhausToastMakeText.setAnchor(anchorViewForToast);
        }
        if (bauhausToastMakeText != null) {
            bauhausToastMakeText.show();
        }
        currentDisplayedBauhausToast = bauhausToastMakeText;
    }

    @JvmStatic
    public static final synchronized void showTextToastWithIcon(Context context, String text, int duration, @DrawableRes final int iconResource, Function0<Unit> onContentClickListener) {
        ToastView content;
        final TextView textView;
        BauhausToast bauhausToast;
        showTextToast(context, text, duration);
        BauhausToast bauhausToast2 = currentDisplayedBauhausToast;
        if (bauhausToast2 != null && (content = bauhausToast2.getContent()) != null && (textView = content.getTextView()) != null) {
            StyleSheetProvider.getStyleSheetObservable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.util.BauhausToastUtils$$ExternalSyntheticLambda1
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    BauhausToastUtils.m1647showTextToastWithIcon$lambda4$lambda3(textView, iconResource, (StyleSheet) obj);
                }
            });
        }
        if (onContentClickListener != null && (bauhausToast = currentDisplayedBauhausToast) != null) {
            bauhausToast.setToastClickListener(onContentClickListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showTextToastWithIcon$lambda-4$lambda-3, reason: not valid java name */
    public static final void m1647showTextToastWithIcon$lambda4$lambda3(TextView this_apply, int i, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        Integer spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.LARGE);
        if (spacerInPixels == null) {
            return;
        }
        int iIntValue = spacerInPixels.intValue();
        this_apply.setCompoundDrawablesWithIntrinsicBounds(0, 0, i, 0);
        this_apply.setCompoundDrawablePadding(iIntValue);
    }

    public final void setOnContentClickListener(Function0<Unit> onContentClickListener) {
        BauhausToast bauhausToast;
        if (onContentClickListener == null || (bauhausToast = currentDisplayedBauhausToast) == null) {
            return;
        }
        bauhausToast.setToastClickListener(onContentClickListener);
    }

    public final void setOnDismissListener(Function0<Unit> onDismissListener) {
        BauhausToast bauhausToast = currentDisplayedBauhausToast;
        if (bauhausToast == null) {
            return;
        }
        bauhausToast.setOnDismissListener(onDismissListener);
    }

    @JvmStatic
    public static final synchronized void showTextToast(Context context, int textResId, int duration) {
        showTextToast(context, context == null ? null : context.getString(textResId), duration);
    }

    @JvmStatic
    public static final synchronized void showDefaultToast(Context context, int textResId, int duration) {
        showDefaultToast(context, context == null ? null : context.getString(textResId), duration);
    }

    @JvmStatic
    public static final synchronized void showDefaultToast(Context context, final String text, final int duration) {
        Context applicationContext;
        BauhausToast bauhausToast = currentDisplayedBauhausToast;
        if (bauhausToast != null) {
            bauhausToast.dismiss();
        }
        currentDisplayedBauhausToast = null;
        if (context != null && (applicationContext = context.getApplicationContext()) != null) {
            ContextKt.runOnUiThread$default(applicationContext, 0L, new Function1<Context, Unit>() { // from class: com.amazon.mp3.util.BauhausToastUtils.showDefaultToast.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Context context2) {
                    invoke2(context2);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Context it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    BauhausToastUtils bauhausToastUtils = BauhausToastUtils.INSTANCE;
                    Toast toastMakeText = Toast.makeText(it.getApplicationContext(), String.valueOf(text), duration);
                    if (toastMakeText != null) {
                        toastMakeText.show();
                    }
                    BauhausToastUtils.currentDisplayedToast = toastMakeText;
                }
            }, 1, null);
        }
    }
}
