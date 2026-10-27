package com.amazon.music.views.library.views;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.utils.ScreenUtil;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BauhausToast.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000g\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u001e\u0018\u0000 /2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001/B\u001f\u0012\u0006\u0010,\u001a\u00020+\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b-\u0010.J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0002J\b\u0010\u0005\u001a\u00020\u0002H\u0002J\b\u0010\u0006\u001a\u00020\u0002H\u0002J\b\u0010\u0007\u001a\u00020\u0002H\u0016J\u0014\u0010\n\u001a\u00020\u00002\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\bJ\u0016\u0010\f\u001a\u00020\u00002\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\bJ\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\rR\u0017\u0010\u0011\u001a\u00020\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0016\u0010\"\u001a\u00020!8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\"\u0010#R\u0016\u0010%\u001a\u00020$8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b%\u0010&R\u001e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010'R\u001e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000b\u0010'R\u0018\u0010)\u001a\u0004\u0018\u00010(8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b)\u0010*¨\u00060"}, d2 = {"Lcom/amazon/music/views/library/views/BauhausToast;", "Lcom/google/android/material/snackbar/BaseTransientBottomBar;", "", "setMargins", "startCountdown", "stopCountdown", "initContentInteractionListeners", "show", "Lkotlin/Function0;", "onContentClickListener", "setToastClickListener", "onDismissListener", "setOnDismissListener", "Landroid/view/View;", "anchorView", "setAnchor", "Lcom/amazon/music/views/library/views/ToastView;", "content", "Lcom/amazon/music/views/library/views/ToastView;", "getContent", "()Lcom/amazon/music/views/library/views/ToastView;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "Ljava/lang/Runnable;", "countdownCallback", "Ljava/lang/Runnable;", "Landroid/os/Handler;", "handler", "Landroid/os/Handler;", "com/amazon/music/views/library/views/BauhausToast$toastGestureListener$1", "toastGestureListener", "Lcom/amazon/music/views/library/views/BauhausToast$toastGestureListener$1;", "", "toastDuration", "J", "", "isInLongPressed", "Z", "Lkotlin/jvm/functions/Function0;", "", "extraBottomMargin", "Ljava/lang/Integer;", "Landroid/view/ViewGroup;", "parent", "<init>", "(Landroid/view/ViewGroup;Lcom/amazon/music/views/library/views/ToastView;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "Companion", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class BauhausToast extends BaseTransientBottomBar<BauhausToast> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final ToastView content;
    private final Runnable countdownCallback;
    private Integer extraBottomMargin;
    private final Handler handler;
    private boolean isInLongPressed;
    private Function0<Unit> onContentClickListener;
    private Function0<Unit> onDismissListener;
    private final StyleSheet styleSheet;
    private long toastDuration;
    private final BauhausToast$toastGestureListener$1 toastGestureListener;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-2, reason: not valid java name */
    public static final WindowInsetsCompat m2396_init_$lambda2(View noName_0, WindowInsetsCompat insets) {
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(insets, "insets");
        return insets;
    }

    public final ToastView getContent() {
        return this.content;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r3v3, types: [com.amazon.music.views.library.views.BauhausToast$toastGestureListener$1] */
    public BauhausToast(ViewGroup parent, ToastView content, StyleSheet styleSheet) {
        super(parent, content, content);
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.content = content;
        this.styleSheet = styleSheet;
        this.countdownCallback = new Runnable() { // from class: com.amazon.music.views.library.views.BauhausToast$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                BauhausToast.m2397countdownCallback$lambda0(this.f$0);
            }
        };
        this.handler = new Handler(getContext().getMainLooper());
        this.toastGestureListener = new GestureDetector.SimpleOnGestureListener() { // from class: com.amazon.music.views.library.views.BauhausToast$toastGestureListener$1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent e) {
                Intrinsics.checkNotNullParameter(e, "e");
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent e) {
                Intrinsics.checkNotNullParameter(e, "e");
                this.this$0.isInLongPressed = true;
                this.this$0.stopCountdown();
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent e) {
                Intrinsics.checkNotNullParameter(e, "e");
                Function0 function0 = this.this$0.onContentClickListener;
                if (function0 == null) {
                    return true;
                }
                function0.invoke();
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Intrinsics.checkNotNullParameter(e1, "e1");
                Intrinsics.checkNotNullParameter(e2, "e2");
                Function0 function0 = this.this$0.onDismissListener;
                if (function0 != null) {
                    function0.invoke();
                }
                this.this$0.onDismissListener = null;
                this.this$0.dismiss();
                return true;
            }
        };
        this.toastDuration = 3000L;
        setDuration(-2);
        getView().setBackgroundColor(ContextCompat.getColor(this.view.getContext(), R.color.transparent));
        getView().setPadding(0, 0, 0, 0);
        ViewGroup.LayoutParams layoutParams = getView().getLayoutParams();
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            layoutParams.width = -2;
            ((FrameLayout.LayoutParams) layoutParams).gravity = 81;
        }
        ViewGroup.LayoutParams layoutParams2 = content.getLayoutParams();
        FrameLayout.LayoutParams layoutParams3 = layoutParams2 instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) layoutParams2 : null;
        if (layoutParams3 != null) {
            layoutParams3.width = -2;
            layoutParams3.gravity = 17;
        }
        ViewCompat.setOnApplyWindowInsetsListener(getView(), new OnApplyWindowInsetsListener() { // from class: com.amazon.music.views.library.views.BauhausToast$$ExternalSyntheticLambda1
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                return BauhausToast.m2396_init_$lambda2(view, windowInsetsCompat);
            }
        });
        initContentInteractionListeners();
    }

    /* JADX INFO: compiled from: BauhausToast.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0007J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0007¨\u0006\r"}, d2 = {"Lcom/amazon/music/views/library/views/BauhausToast$Companion;", "", "()V", "makeText", "Lcom/amazon/music/views/library/views/BauhausToast;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroid/app/Activity;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "text", "", "parent", "Landroid/view/ViewGroup;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final BauhausToast makeText(Activity activity, StyleSheet styleSheet, String text) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
            View viewFindViewById = activity.findViewById(R.id.content);
            ViewGroup viewGroup = viewFindViewById instanceof ViewGroup ? (ViewGroup) viewFindViewById : null;
            if (viewGroup == null) {
                return null;
            }
            return BauhausToast.INSTANCE.makeText(viewGroup, styleSheet, text);
        }

        @JvmStatic
        public final BauhausToast makeText(ViewGroup parent, StyleSheet styleSheet, String text) {
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
            Context context = parent.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "parent.context");
            TextToastView textToastView = new TextToastView(context, styleSheet);
            textToastView.setText(text);
            return new BauhausToast(parent, textToastView, styleSheet);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: countdownCallback$lambda-0, reason: not valid java name */
    public static final void m2397countdownCallback$lambda0(BauhausToast this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function0<Unit> function0 = this$0.onDismissListener;
        if (function0 != null) {
            function0.invoke();
        }
        this$0.onDismissListener = null;
        this$0.dismiss();
    }

    @Override // com.google.android.material.snackbar.BaseTransientBottomBar
    public void show() {
        super.show();
        setMargins();
        startCountdown();
    }

    public final BauhausToast setToastClickListener(Function0<Unit> onContentClickListener) {
        Intrinsics.checkNotNullParameter(onContentClickListener, "onContentClickListener");
        this.onContentClickListener = onContentClickListener;
        return this;
    }

    public final BauhausToast setOnDismissListener(Function0<Unit> onDismissListener) {
        this.onDismissListener = onDismissListener;
        return this;
    }

    public final BauhausToast setAnchor(View anchorView) {
        Intrinsics.checkNotNullParameter(anchorView, "anchorView");
        int[] iArr = new int[2];
        anchorView.getLocationOnScreen(iArr);
        Object systemService = getContext().getSystemService("window");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.WindowManager");
        }
        this.extraBottomMargin = Integer.valueOf(ScreenUtil.INSTANCE.getScreenHeightPixels((WindowManager) systemService) - iArr[1]);
        return this;
    }

    private final void setMargins() {
        ViewGroup.LayoutParams layoutParams = getView().getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            Integer spacerInPixels = this.styleSheet.getSpacerInPixels(SpacerKey.SMALL);
            int iIntValue = spacerInPixels == null ? 0 : spacerInPixels.intValue();
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            Integer num = this.extraBottomMargin;
            marginLayoutParams.setMargins(iIntValue, iIntValue, iIntValue, (num != null ? num.intValue() : 0) + iIntValue);
        }
    }

    private final void startCountdown() {
        this.handler.postDelayed(this.countdownCallback, this.toastDuration);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopCountdown() {
        this.handler.removeCallbacks(this.countdownCallback);
    }

    private final void initContentInteractionListeners() {
        this.content.setGestureListener(this.toastGestureListener);
        this.content.setOnTouchListener(new View.OnTouchListener() { // from class: com.amazon.music.views.library.views.BauhausToast$$ExternalSyntheticLambda2
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return BauhausToast.m2398initContentInteractionListeners$lambda3(this.f$0, view, motionEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initContentInteractionListeners$lambda-3, reason: not valid java name */
    public static final boolean m2398initContentInteractionListeners$lambda3(BauhausToast this$0, View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if ((motionEvent.getAction() == 1 || motionEvent.getAction() == 3) && this$0.isInLongPressed) {
            this$0.startCountdown();
            this$0.isInLongPressed = false;
        }
        return false;
    }
}
