package com.amazon.music.views.library.views;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.amazon.music.views.library.R$style;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.ToolTipView;
import com.amazon.ui.foundations.utils.ScreenUtil;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ToolTipDialog.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u00011B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u000eH\u0002J\b\u0010\u001d\u001a\u00020\u000eH\u0002J\b\u0010\u001e\u001a\u00020\u000eH\u0002J\u0012\u0010\u001f\u001a\u00020\u001b2\b\u0010 \u001a\u0004\u0018\u00010!H\u0014J\u0016\u0010\"\u001a\u00020\u00002\u0006\u0010#\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020\u000eJ\u000e\u0010%\u001a\u00020\u00002\u0006\u0010&\u001a\u00020'J\u000e\u0010(\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\u000eJ\u000e\u0010)\u001a\u00020\u00002\u0006\u0010+\u001a\u00020,J\u000e\u0010-\u001a\u00020\u001b2\u0006\u0010.\u001a\u00020\u0012J\b\u0010/\u001a\u00020\u001bH\u0002J\b\u00100\u001a\u00020\u001bH\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017¨\u00062"}, d2 = {"Lcom/amazon/music/views/library/views/ToolTipDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "anchorViewClickRect", "Landroid/graphics/Rect;", "anchorViewPosition", "", "position", "Lcom/amazon/music/views/library/views/ToolTipDialog$Position;", "screenHeight", "", "screenWidth", "toolTipArrowMargin", "toolTipClickListener", "Landroid/view/View$OnClickListener;", "toolTipMargin", "toolTipView", "Lcom/amazon/music/views/library/views/ToolTipView;", "getToolTipView", "()Lcom/amazon/music/views/library/views/ToolTipView;", "toolTipView$delegate", "Lkotlin/Lazy;", "configureToolTipView", "", "getScreenHeight", "getScreenWidth", "getStatusBarHeight", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setAnchorPoint", "x", "y", "setAnchorView", "view", "Landroid/view/View;", "setPosition", "setText", "resId", "text", "", "setToolTipClickListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setToolTipMargins", "updateDialogPosition", "Position", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ToolTipDialog extends Dialog {
    private final Rect anchorViewClickRect;
    private final int[] anchorViewPosition;
    private Position position;
    private final int screenHeight;
    private final int screenWidth;
    private final StyleSheet styleSheet;
    private int toolTipArrowMargin;
    private View.OnClickListener toolTipClickListener;
    private int toolTipMargin;

    /* JADX INFO: renamed from: toolTipView$delegate, reason: from kotlin metadata */
    private final Lazy toolTipView;

    /* JADX INFO: compiled from: ToolTipDialog.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/views/library/views/ToolTipDialog$Position;", "", "(Ljava/lang/String;I)V", "LEFT", "TOP", "RIGHT", "BOTTOM", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum Position {
        LEFT,
        TOP,
        RIGHT,
        BOTTOM
    }

    /* JADX INFO: compiled from: ToolTipDialog.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Position.values().length];
            iArr[Position.LEFT.ordinal()] = 1;
            iArr[Position.TOP.ordinal()] = 2;
            iArr[Position.RIGHT.ordinal()] = 3;
            iArr[Position.BOTTOM.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ToolTipDialog(final Context context, StyleSheet styleSheet) {
        super(context, R$style.dmmviewlibrary_tool_tip_dialog);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.toolTipView = LazyKt__LazyJVMKt.lazy(new Function0<ToolTipView>() { // from class: com.amazon.music.views.library.views.ToolTipDialog$toolTipView$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ToolTipView invoke() {
                return new ToolTipView(context);
            }
        });
        this.anchorViewClickRect = new Rect();
        this.anchorViewPosition = new int[2];
        this.screenWidth = getScreenWidth();
        this.screenHeight = getScreenHeight();
        this.position = Position.TOP;
        Integer spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.SMALL);
        this.toolTipMargin = spacerInPixels == null ? 0 : spacerInPixels.intValue();
        Integer spacerInPixels2 = styleSheet.getSpacerInPixels(SpacerKey.MINI);
        this.toolTipArrowMargin = spacerInPixels2 != null ? spacerInPixels2.intValue() : 0;
    }

    private final ToolTipView getToolTipView() {
        return (ToolTipView) this.toolTipView.getValue();
    }

    public final ToolTipDialog setText(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        getToolTipView().setText(text);
        return this;
    }

    public final ToolTipDialog setText(int resId) {
        getToolTipView().setText(resId);
        return this;
    }

    public final ToolTipDialog setAnchorView(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.anchorViewClickRect.set(0, 0, view.getWidth(), view.getHeight());
        view.getLocationOnScreen(this.anchorViewPosition);
        int[] iArr = this.anchorViewPosition;
        iArr[1] = iArr[1] - getStatusBarHeight();
        return this;
    }

    public final ToolTipDialog setAnchorPoint(int x, int y) {
        this.anchorViewClickRect.set(0, 0, 0, 0);
        int[] iArr = this.anchorViewPosition;
        iArr[0] = x;
        iArr[1] = y;
        return this;
    }

    public final ToolTipDialog setPosition(Position position) {
        Intrinsics.checkNotNullParameter(position, "position");
        this.position = position;
        int i = WhenMappings.$EnumSwitchMapping$0[position.ordinal()];
        if (i == 1) {
            getToolTipView().setDirection(ToolTipView.Direction.RIGHT);
        } else if (i == 2) {
            getToolTipView().setDirection(ToolTipView.Direction.BOTTOM);
        } else if (i == 3) {
            getToolTipView().setDirection(ToolTipView.Direction.LEFT);
        } else if (i == 4) {
            getToolTipView().setDirection(ToolTipView.Direction.TOP);
        }
        return this;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolTipView().initStyleSheet(this.styleSheet);
        setContentView(getToolTipView());
        setToolTipMargins();
        updateDialogPosition();
        configureToolTipView();
    }

    public final void setToolTipClickListener(View.OnClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.toolTipClickListener = listener;
    }

    private final void configureToolTipView() {
        final View.OnClickListener onClickListener = this.toolTipClickListener;
        if (onClickListener == null) {
            return;
        }
        getToolTipView().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.views.ToolTipDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ToolTipDialog.m2435configureToolTipView$lambda1$lambda0(onClickListener, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: configureToolTipView$lambda-1$lambda-0, reason: not valid java name */
    public static final void m2435configureToolTipView$lambda1$lambda0(View.OnClickListener listener, View view) {
        Intrinsics.checkNotNullParameter(listener, "$listener");
        listener.onClick(view);
    }

    private final void setToolTipMargins() {
        ViewGroup.LayoutParams layoutParams = getToolTipView().getLayoutParams();
        FrameLayout.LayoutParams layoutParams2 = layoutParams instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) layoutParams : null;
        int i = this.toolTipMargin;
        int i2 = this.toolTipArrowMargin;
        int i3 = WhenMappings.$EnumSwitchMapping$0[this.position.ordinal()];
        if (i3 != 1) {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 == 4 && layoutParams2 != null) {
                        layoutParams2.setMargins(i, i2, i, i);
                    }
                } else if (layoutParams2 != null) {
                    layoutParams2.setMargins(i2, i, i, i);
                }
            } else if (layoutParams2 != null) {
                layoutParams2.setMargins(i, i, i, i2);
            }
        } else if (layoutParams2 != null) {
            layoutParams2.setMargins(i, i, i2, i);
        }
        getToolTipView().requestLayout();
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00e0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void updateDialogPosition() {
        /*
            Method dump skipped, instruction units count: 251
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.views.library.views.ToolTipDialog.updateDialogPosition():void");
    }

    private final int getStatusBarHeight() {
        Resources resources = getContext().getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
    }

    private final int getScreenWidth() {
        Object systemService = getContext().getSystemService("window");
        WindowManager windowManager = systemService instanceof WindowManager ? (WindowManager) systemService : null;
        if (windowManager == null) {
            return 0;
        }
        return ScreenUtil.INSTANCE.getScreenWidthPixels(windowManager);
    }

    private final int getScreenHeight() {
        Object systemService = getContext().getSystemService("window");
        WindowManager windowManager = systemService instanceof WindowManager ? (WindowManager) systemService : null;
        if (windowManager == null) {
            return 0;
        }
        return ScreenUtil.INSTANCE.getScreenHeightPixels(windowManager);
    }
}
