package com.amazon.mp3.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.core.view.PointerIconCompat;
import com.BV.LinearGradient.LinearGradientManager;
import com.amazon.mp3.automotiveOS.R;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AnchoredPopupWindowBuilder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/view/AnchoredPopupWindowBuilder;", "", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AnchoredPopupWindowBuilder {
    private static View mAnchorView;
    private static Context mContext;
    private static int mMargin;
    private static String mMessage;
    private static PopupWindow mPopupWindow;
    private static int[] mPopupWindowLocation;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static int mContentViewId = R.layout.popup_info;
    private static int mGravity = 48;

    /* JADX INFO: compiled from: AnchoredPopupWindowBuilder.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000e\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0000H\u0007J\b\u0010\u0012\u001a\u00020\u0010H\u0002J\u0018\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0006H\u0002J\u001a\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u0014\u001a\u00020\u0006H\u0002J\u0010\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u0004H\u0007J\u0010\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u0006H\u0007J\u0010\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020\u0006H\u0007J\u0010\u0010!\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020\u0006H\u0007J\u0010\u0010#\u001a\u00020\u00002\u0006\u0010$\u001a\u00020\fH\u0007J\b\u0010%\u001a\u00020\u0004H\u0007J\u0010\u0010&\u001a\u00020\u00002\u0006\u0010'\u001a\u00020\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/amazon/mp3/view/AnchoredPopupWindowBuilder$Companion;", "", "()V", "mAnchorView", "Landroid/view/View;", "mContentViewId", "", "mContext", "Landroid/content/Context;", "mGravity", "mMargin", "mMessage", "", "mPopupWindow", "Landroid/widget/PopupWindow;", "mPopupWindowLocation", "", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "buildPopupWindow", "computeAvailableWidth", "screenWidth", "yPosition", "getRotateDrawable", "Landroid/graphics/drawable/Drawable;", "drawable", LinearGradientManager.PROP_ANGLE, "", "setAnchorView", "anchorView", "setContentView", "contentViewId", "setGravity", "gravity", "setMargin", "margin", "setMessage", "message", "show", JsonPOJOBuilder.DEFAULT_WITH_PREFIX, "context", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Companion with(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            AnchoredPopupWindowBuilder.mContext = context;
            return this;
        }

        @JvmStatic
        public final Companion setMessage(String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            AnchoredPopupWindowBuilder.mMessage = message;
            return this;
        }

        @JvmStatic
        public final Companion setContentView(int contentViewId) {
            AnchoredPopupWindowBuilder.mContentViewId = contentViewId;
            return this;
        }

        @JvmStatic
        public final Companion setAnchorView(View anchorView) {
            Intrinsics.checkNotNullParameter(anchorView, "anchorView");
            AnchoredPopupWindowBuilder.mAnchorView = anchorView;
            return this;
        }

        @JvmStatic
        public final Companion setGravity(int gravity) {
            AnchoredPopupWindowBuilder.mGravity = gravity;
            return this;
        }

        @JvmStatic
        public final Companion setMargin(int margin) {
            AnchoredPopupWindowBuilder.mMargin = margin;
            return this;
        }

        @JvmStatic
        public final Companion build() {
            AnchoredPopupWindowBuilder.mPopupWindowLocation = buildPopupWindow();
            return this;
        }

        @JvmStatic
        public final View show() {
            PopupWindow popupWindow = AnchoredPopupWindowBuilder.mPopupWindow;
            PopupWindow popupWindow2 = null;
            if (popupWindow == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mPopupWindow");
                popupWindow = null;
            }
            View view = AnchoredPopupWindowBuilder.mAnchorView;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mAnchorView");
                view = null;
            }
            int[] iArr = AnchoredPopupWindowBuilder.mPopupWindowLocation;
            if (iArr == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mPopupWindowLocation");
                iArr = null;
            }
            int i = iArr[0];
            int[] iArr2 = AnchoredPopupWindowBuilder.mPopupWindowLocation;
            if (iArr2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mPopupWindowLocation");
                iArr2 = null;
            }
            popupWindow.showAtLocation(view, 0, i, iArr2[1]);
            PopupWindow popupWindow3 = AnchoredPopupWindowBuilder.mPopupWindow;
            if (popupWindow3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mPopupWindow");
            } else {
                popupWindow2 = popupWindow3;
            }
            View contentView = popupWindow2.getContentView();
            Intrinsics.checkNotNullExpressionValue(contentView, "mPopupWindow.contentView");
            return contentView;
        }

        private final int[] buildPopupWindow() {
            int[] iArr;
            int[] iArr2 = new int[2];
            View view = AnchoredPopupWindowBuilder.mAnchorView;
            View view2 = null;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mAnchorView");
                view = null;
            }
            view.getLocationOnScreen(iArr2);
            Context context = AnchoredPopupWindowBuilder.mContext;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context = null;
            }
            Object systemService = context.getApplicationContext().getSystemService("layout_inflater");
            if (systemService != null) {
                LayoutInflater layoutInflater = (LayoutInflater) systemService;
                int i = AnchoredPopupWindowBuilder.mContentViewId;
                Context context2 = AnchoredPopupWindowBuilder.mContext;
                if (context2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mContext");
                    context2 = null;
                }
                View viewInflate = layoutInflater.inflate(i, new LinearLayout(context2.getApplicationContext()));
                Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(mConten…text.applicationContext))");
                View viewFindViewById = viewInflate.findViewById(R.id.tv_description);
                Intrinsics.checkNotNullExpressionValue(viewFindViewById, "popupView.findViewById(R.id.tv_description)");
                TextView textView = (TextView) viewFindViewById;
                String str = AnchoredPopupWindowBuilder.mMessage;
                if (str == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mMessage");
                    str = null;
                }
                textView.setText(str);
                viewInflate.measure(View.MeasureSpec.makeMeasureSpec(computeAvailableWidth(screenWidth(), iArr2[0]), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(0, 0));
                int measuredHeight = viewInflate.getMeasuredHeight();
                int measuredWidth = viewInflate.getMeasuredWidth();
                AnchoredPopupWindowBuilder.mPopupWindow = new PopupWindow(viewInflate, measuredWidth, measuredHeight, true);
                PopupWindow popupWindow = AnchoredPopupWindowBuilder.mPopupWindow;
                if (popupWindow == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mPopupWindow");
                    popupWindow = null;
                }
                popupWindow.setClippingEnabled(false);
                if (AnchoredPopupWindowBuilder.mGravity == 48) {
                    AnchoredPopupWindowBuilder.mGravity = iArr2[1] >= measuredHeight * 2 ? 48 : 80;
                }
                PopupWindow popupWindow2 = AnchoredPopupWindowBuilder.mPopupWindow;
                if (popupWindow2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mPopupWindow");
                    popupWindow2 = null;
                }
                popupWindow2.setWindowLayoutType(PointerIconCompat.TYPE_HELP);
                int i2 = AnchoredPopupWindowBuilder.mGravity;
                if (i2 == 48) {
                    iArr = new int[2];
                    int i3 = iArr2[0] - (measuredWidth / 2);
                    View view3 = AnchoredPopupWindowBuilder.mAnchorView;
                    if (view3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mAnchorView");
                        view3 = null;
                    }
                    iArr[0] = i3 + (view3.getWidth() / 2);
                    int i4 = iArr2[1];
                    View view4 = AnchoredPopupWindowBuilder.mAnchorView;
                    if (view4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mAnchorView");
                    } else {
                        view2 = view4;
                    }
                    iArr[1] = (i4 - (measuredHeight + view2.getHeight())) - AnchoredPopupWindowBuilder.mMargin;
                } else if (i2 == 80) {
                    Drawable background = textView.getBackground();
                    Intrinsics.checkNotNullExpressionValue(background, "messageView.background");
                    textView.setBackground(getRotateDrawable(background, 180.0f));
                    iArr = new int[2];
                    int i5 = iArr2[0] - (measuredWidth / 2);
                    View view5 = AnchoredPopupWindowBuilder.mAnchorView;
                    if (view5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mAnchorView");
                    } else {
                        view2 = view5;
                    }
                    iArr[0] = i5 + (view2.getWidth() / 2);
                    iArr[1] = iArr2[1] + AnchoredPopupWindowBuilder.mMargin;
                } else {
                    iArr = new int[2];
                    int i6 = iArr2[0] - (measuredWidth / 2);
                    View view6 = AnchoredPopupWindowBuilder.mAnchorView;
                    if (view6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mAnchorView");
                        view6 = null;
                    }
                    iArr[0] = i6 + (view6.getWidth() / 2);
                    int i7 = iArr2[1];
                    View view7 = AnchoredPopupWindowBuilder.mAnchorView;
                    if (view7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mAnchorView");
                    } else {
                        view2 = view7;
                    }
                    iArr[1] = (i7 - (measuredHeight + view2.getHeight())) - AnchoredPopupWindowBuilder.mMargin;
                }
                return iArr;
            }
            throw new NullPointerException("null cannot be cast to non-null type android.view.LayoutInflater");
        }

        private final int computeAvailableWidth(int screenWidth, int yPosition) {
            double d;
            int i = screenWidth - yPosition;
            if (i < yPosition) {
                if (i < screenWidth / 3) {
                    return i * 2;
                }
                d = i;
            } else {
                if (yPosition < screenWidth / 3) {
                    return yPosition * 2;
                }
                d = yPosition;
            }
            return (int) (d * 1.3d);
        }

        private final int screenWidth() {
            Context context = AnchoredPopupWindowBuilder.mContext;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context = null;
            }
            Object systemService = context.getSystemService("window");
            if (systemService == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.view.WindowManager");
            }
            return ((WindowManager) systemService).getDefaultDisplay().getWidth();
        }

        private final Drawable getRotateDrawable(final Drawable drawable, final float angle) {
            final Drawable[] drawableArr = {drawable};
            return new LayerDrawable(drawableArr, angle, drawable) { // from class: com.amazon.mp3.view.AnchoredPopupWindowBuilder$Companion$getRotateDrawable$1
                final /* synthetic */ float $angle;
                final /* synthetic */ Drawable[] $arrayOfDrawable;
                final /* synthetic */ Drawable $drawable;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(drawableArr);
                    this.$arrayOfDrawable = drawableArr;
                    this.$angle = angle;
                    this.$drawable = drawable;
                }

                @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
                public void draw(Canvas canvas) {
                    Intrinsics.checkNotNullParameter(canvas, "canvas");
                    canvas.save();
                    canvas.rotate(this.$angle, this.$drawable.getBounds().width() / 2.0f, this.$drawable.getBounds().height() / 2.0f);
                    super.draw(canvas);
                    canvas.restore();
                }
            };
        }
    }
}
