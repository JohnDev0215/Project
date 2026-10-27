package com.byd.overseaappstore.ui.view.custom;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.ColorInt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatTextView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinContentLoadingProgressBar;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import spi.vm;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0017\u0010\u0018B\u0019\b\u0016\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\u0006\u0010\u001a\u001a\u00020\u0019¢\u0006\u0004\b\u0017\u0010\u001bB!\b\u0016\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\u0006\u0010\u001a\u001a\u00020\u0019\u0012\u0006\u0010\u001c\u001a\u00020\f¢\u0006\u0004\b\u0017\u0010\u001dB)\b\u0016\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\u0006\u0010\u001a\u001a\u00020\u0019\u0012\u0006\u0010\u001c\u001a\u00020\f\u0012\u0006\u0010\u001e\u001a\u00020\f¢\u0006\u0004\b\u0017\u0010\u001fJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u0012\u0010\u000b\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0002J\u0012\u0010\u000e\u001a\u00020\u00042\b\b\u0001\u0010\r\u001a\u00020\fH\u0002J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\fH\u0002J\u0010\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0006H\u0002J\u0010\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0006H\u0002J\u0010\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0006H\u0002J\u0010\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0006H\u0002¨\u0006 "}, d2 = {"Lcom/byd/overseaappstore/ui/view/custom/DownloadProgressButtonView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "", TypedValues.Custom.S_STRING, "", "setButtonText", "", "visible", "setProgressBarVisible", "Landroid/graphics/drawable/Drawable;", "drawable", "setStateBackground", "", "colorId", "setTextColor", "setStyleTextColor", "isProgressBarVisible", "setPauseState", "setContinueState", "setUpdatePause", "setUpdateContinue", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/util/AttributeSet;", "attrs", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class DownloadProgressButtonView extends ConstraintLayout {
    public static final /* synthetic */ int d = 0;
    public SkinContentLoadingProgressBar a;
    public View b;
    public SkinAppCompatTextView c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DownloadProgressButtonView(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        a();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DownloadProgressButtonView(@NotNull Context context, @NotNull AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        a();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DownloadProgressButtonView(@NotNull Context context, @NotNull AttributeSet attrs, int i) {
        super(context, attrs, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        a();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DownloadProgressButtonView(@NotNull Context context, @NotNull AttributeSet attrs, int i, int i2) {
        super(context, attrs, i, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        a();
    }

    private final void setButtonText(String string) {
        SkinAppCompatTextView skinAppCompatTextView = this.c;
        if (skinAppCompatTextView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("textView");
            skinAppCompatTextView = null;
        }
        skinAppCompatTextView.setText(string);
    }

    private final void setContinueState(boolean isProgressBarVisible) {
        String string = getContext().getString(R.string.button_state_continue);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.button_state_continue)");
        setButtonText(string);
        c();
        if (isProgressBarVisible) {
            c();
        } else {
            e();
        }
    }

    private final void setPauseState(boolean isProgressBarVisible) {
        String string = getContext().getString(R.string.button_state_pause);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.button_state_pause)");
        setButtonText(string);
        if (isProgressBarVisible) {
            c();
        } else {
            e();
        }
    }

    private final void setProgressBarVisible(boolean visible) {
        SkinContentLoadingProgressBar skinContentLoadingProgressBar = null;
        if (visible) {
            View view = this.b;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("stateBackground");
                view = null;
            }
            view.setVisibility(8);
            SkinContentLoadingProgressBar skinContentLoadingProgressBar2 = this.a;
            if (skinContentLoadingProgressBar2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressBar");
            } else {
                skinContentLoadingProgressBar = skinContentLoadingProgressBar2;
            }
            skinContentLoadingProgressBar.setVisibility(0);
            return;
        }
        View view2 = this.b;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stateBackground");
            view2 = null;
        }
        view2.setVisibility(0);
        SkinContentLoadingProgressBar skinContentLoadingProgressBar3 = this.a;
        if (skinContentLoadingProgressBar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressBar");
        } else {
            skinContentLoadingProgressBar = skinContentLoadingProgressBar3;
        }
        skinContentLoadingProgressBar.setVisibility(8);
    }

    private final void setStateBackground(Drawable drawable) {
        View view = this.b;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stateBackground");
            view = null;
        }
        view.setBackground(drawable);
    }

    private final void setStyleTextColor(int colorId) {
        vm vmVar = vm.a;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        int color = getContext().getColor(vmVar.a(context, colorId));
        SkinAppCompatTextView skinAppCompatTextView = this.c;
        if (skinAppCompatTextView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("textView");
            skinAppCompatTextView = null;
        }
        skinAppCompatTextView.setTextColor(color);
    }

    private final void setTextColor(@ColorInt int colorId) {
        SkinAppCompatTextView skinAppCompatTextView = this.c;
        if (skinAppCompatTextView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("textView");
            skinAppCompatTextView = null;
        }
        skinAppCompatTextView.setTextColor(colorId);
    }

    private final void setUpdateContinue(boolean isProgressBarVisible) {
        String string = getContext().getString(R.string.button_state_continue);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.button_state_continue)");
        setButtonText(string);
        c();
        if (isProgressBarVisible) {
            c();
        } else {
            e();
        }
    }

    private final void setUpdatePause(boolean isProgressBarVisible) {
        String string = getContext().getString(R.string.button_state_pause);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.button_state_pause)");
        setButtonText(string);
        c();
        if (isProgressBarVisible) {
            c();
        } else {
            e();
        }
    }

    public final void a() {
        Intrinsics.checkNotNullExpressionValue(LayoutInflater.from(getContext()).inflate(R.layout.view_download_progress_bar, this), "from(context).inflate(R.…nload_progress_bar, this)");
        View viewFindViewById = findViewById(R.id.pb_view_download_progress_bar);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(R.id.pb_view_download_progress_bar)");
        this.a = (SkinContentLoadingProgressBar) viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.v_view_download_progress_bar_state_background);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(R.id.v_view…ess_bar_state_background)");
        this.b = viewFindViewById2;
        View viewFindViewById3 = findViewById(R.id.tv_view_download_progress_bar);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(R.id.tv_view_download_progress_bar)");
        this.c = (SkinAppCompatTextView) viewFindViewById3;
    }

    public final void b() {
        setProgressBarVisible(false);
        vm vmVar = vm.a;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        setStateBackground(vmVar.c(context, R.drawable.bg_clickable_colored_button_open_install_uninstall));
        setStyleTextColor(R.color.common_normal_button_text);
    }

    public final void c() {
        setProgressBarVisible(true);
        setStyleTextColor(R.color.common_download_progress_bar_text);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00da A[PHI: r7
  0x00da: PHI (r7v16 com.byd.overseaappstore.ui.view.custom.skin.SkinContentLoadingProgressBar) = 
  (r7v5 com.byd.overseaappstore.ui.view.custom.skin.SkinContentLoadingProgressBar)
  (r7v6 com.byd.overseaappstore.ui.view.custom.skin.SkinContentLoadingProgressBar)
  (r7v15 com.byd.overseaappstore.ui.view.custom.skin.SkinContentLoadingProgressBar)
  (r7v17 com.byd.overseaappstore.ui.view.custom.skin.SkinContentLoadingProgressBar)
 binds: [B:29:0x00d4, B:24:0x00ca, B:15:0x006c, B:10:0x0061] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d(int r7, int r8, boolean r9) {
        /*
            Method dump skipped, instruction units count: 298
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.ui.view.custom.DownloadProgressButtonView.d(int, int, boolean):void");
    }

    public final void e() {
        setProgressBarVisible(false);
        vm vmVar = vm.a;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        setStateBackground(vmVar.c(context, R.drawable.bg_clickable_colored_button_continue_pause_download));
        setStyleTextColor(R.color.common_no_color_button_text);
    }

    public final void f() {
        String string = getContext().getString(R.string.button_state_wait);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.button_state_wait)");
        setButtonText(string);
        setProgressBarVisible(false);
        vm vmVar = vm.a;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        setStateBackground(vmVar.c(context, R.drawable.bg_clickable_colored_button_wait));
        setStyleTextColor(R.color.common_unable_button_text);
    }
}
