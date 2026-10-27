package com.amazon.mp3.util;

import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.BauhausDialog;
import com.amazon.music.views.library.views.Orientation;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: BauhausDialogBuilder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 32\u00020\u0001:\u00013B\u001b\u0012\u0006\u0010\"\u001a\u00020!\u0012\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b0\u00101B\u0011\b\u0016\u0012\u0006\u0010\"\u001a\u00020!¢\u0006\u0004\b0\u00102J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0006\u001a\u00020\u00002\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0007J\u0010\u0010\t\u001a\u00020\u00002\b\u0010\u000b\u001a\u0004\u0018\u00010\nJ\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0007J\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0007J\u0010\u0010\u000e\u001a\u00020\u00002\b\u0010\u000b\u001a\u0004\u0018\u00010\nJ\u0018\u0010\u0011\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fJ\u001a\u0010\u0011\u001a\u00020\u00002\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fJ\u0018\u0010\u0012\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fJ\u001a\u0010\u0012\u001a\u00020\u00002\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fJ\u0018\u0010\u0013\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fJ\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0014J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0002J\u000e\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u0019J\u000e\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001cJ\b\u0010 \u001a\u00020\u001fH\u0016R\u0017\u0010\"\u001a\u00020!8\u0006¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%R\u0016\u0010'\u001a\u0004\u0018\u00010&8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010(R\u0016\u0010*\u001a\u0004\u0018\u00010)8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010+R\u0014\u0010-\u001a\u00020,8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b-\u0010.¨\u00064"}, d2 = {"Lcom/amazon/mp3/util/BauhausDialogBuilder;", "", "", "isBauhausDialogEnabled", "Landroid/view/View;", "view", "setView", "", "textResId", "setTitle", "", "text", "alignment", "setTitleTextAlignment", "setMessage", "Landroid/content/DialogInterface$OnClickListener;", "onClickListener", "setPositiveButton", "setNegativeButton", "setNeutralButton", "Lcom/amazon/music/views/library/views/Orientation;", "orientation", "setButtonOrientation", "isCancelable", "setCancelable", "Landroid/content/DialogInterface$OnCancelListener;", "onCancelListener", "setOnCancelListener", "Landroid/content/DialogInterface$OnDismissListener;", "onDismissListener", "setOnDismissListener", "Landroid/app/Dialog;", "create", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "Lcom/amazon/music/views/library/views/BauhausDialog$Builder;", "bauhausDialogBuilder", "Lcom/amazon/music/views/library/views/BauhausDialog$Builder;", "Landroid/app/AlertDialog$Builder;", "alertDialogBuilder", "Landroid/app/AlertDialog$Builder;", "themeResId", "<init>", "(Landroid/content/Context;Ljava/lang/Integer;)V", "(Landroid/content/Context;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BauhausDialogBuilder {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = Reflection.getOrCreateKotlinClass(BauhausDialogBuilder.class).getSimpleName();
    private static final boolean isBauhausIAMEnabled = AmazonApplication.getCapabilities().isBauhausInAppMessagingEnabled();
    private final AlertDialog.Builder alertDialogBuilder;
    private final BauhausDialog.Builder bauhausDialogBuilder;
    private final Context context;
    private final StyleSheet styleSheet;

    public BauhausDialogBuilder(Context context, Integer num) {
        BauhausDialog.Builder builder;
        AlertDialog.Builder builder2;
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        StyleSheet value = StyleSheetProvider.getStyleSheet().getValue();
        this.styleSheet = value;
        if (value == null || !value.getIsInitCompleted() || (context instanceof Application)) {
            if (context instanceof Application) {
                Log.error(TAG, "Dialog is not created successfully because context received is an Application context");
            }
            builder = null;
        } else if (num != null) {
            builder = new BauhausDialog.Builder(context, value, num.intValue());
        } else {
            builder = new BauhausDialog.Builder(context, value);
        }
        this.bauhausDialogBuilder = builder;
        if (num != null) {
            builder2 = new AlertDialog.Builder(context, num.intValue());
        } else {
            builder2 = new AlertDialog.Builder(context);
        }
        this.alertDialogBuilder = builder2;
    }

    /* JADX INFO: compiled from: BauhausDialogBuilder.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\u0006R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/util/BauhausDialogBuilder$Companion;", "", "()V", "TAG", "", "isBauhausIAMEnabled", "", "isBauhausDialogEnabled", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isBauhausDialogEnabled() {
            StyleSheet value = StyleSheetProvider.getStyleSheet().getValue();
            return BauhausDialogBuilder.isBauhausIAMEnabled && value != null && value.getIsInitCompleted();
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BauhausDialogBuilder(Context context) {
        this(context, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final BauhausDialogBuilder setView(View view) {
        if (isBauhausDialogEnabled()) {
            BauhausDialog.Builder builder = this.bauhausDialogBuilder;
            if (builder != null) {
                builder.setView(view);
            }
        } else {
            this.alertDialogBuilder.setView(view);
        }
        return this;
    }

    public final BauhausDialogBuilder setTitle(int textResId) {
        if (isBauhausDialogEnabled()) {
            String string = this.context.getString(textResId);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(textResId)");
            BauhausDialog.Builder builder = this.bauhausDialogBuilder;
            if (builder != null) {
                builder.setTitle(string);
            }
        } else {
            this.alertDialogBuilder.setTitle(textResId);
        }
        return this;
    }

    public final BauhausDialogBuilder setTitle(CharSequence text) {
        if (isBauhausDialogEnabled()) {
            BauhausDialog.Builder builder = this.bauhausDialogBuilder;
            if (builder != null) {
                builder.setTitle(text);
            }
        } else {
            this.alertDialogBuilder.setTitle(text);
        }
        return this;
    }

    public final BauhausDialogBuilder setTitleTextAlignment(int alignment) {
        BauhausDialog.Builder builder;
        if (isBauhausDialogEnabled() && (builder = this.bauhausDialogBuilder) != null) {
            builder.setTitleTextAlignment(alignment);
        }
        return this;
    }

    public final BauhausDialogBuilder setMessage(int textResId) {
        if (isBauhausDialogEnabled()) {
            String string = this.context.getString(textResId);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(textResId)");
            BauhausDialog.Builder builder = this.bauhausDialogBuilder;
            if (builder != null) {
                builder.setMessage(string);
            }
        } else {
            this.alertDialogBuilder.setMessage(textResId);
        }
        return this;
    }

    public final BauhausDialogBuilder setMessage(CharSequence text) {
        if (isBauhausDialogEnabled()) {
            BauhausDialog.Builder builder = this.bauhausDialogBuilder;
            if (builder != null) {
                builder.setMessage(text);
            }
        } else {
            this.alertDialogBuilder.setMessage(text);
        }
        return this;
    }

    public final BauhausDialogBuilder setPositiveButton(int textResId, DialogInterface.OnClickListener onClickListener) {
        if (isBauhausDialogEnabled()) {
            String string = this.context.getString(textResId);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(textResId)");
            BauhausDialog.Builder builder = this.bauhausDialogBuilder;
            if (builder != null) {
                builder.setPositiveButton(string, onClickListener);
            }
        } else {
            this.alertDialogBuilder.setPositiveButton(textResId, onClickListener);
        }
        return this;
    }

    public final BauhausDialogBuilder setPositiveButton(CharSequence text, DialogInterface.OnClickListener onClickListener) {
        if (isBauhausDialogEnabled()) {
            BauhausDialog.Builder builder = this.bauhausDialogBuilder;
            if (builder != null) {
                builder.setPositiveButton(text, onClickListener);
            }
        } else {
            this.alertDialogBuilder.setPositiveButton(text, onClickListener);
        }
        return this;
    }

    public final BauhausDialogBuilder setNegativeButton(int textResId, DialogInterface.OnClickListener onClickListener) {
        if (isBauhausDialogEnabled()) {
            String string = this.context.getString(textResId);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(textResId)");
            BauhausDialog.Builder builder = this.bauhausDialogBuilder;
            if (builder != null) {
                builder.setNegativeButton(string, onClickListener);
            }
        } else {
            this.alertDialogBuilder.setNegativeButton(textResId, onClickListener);
        }
        return this;
    }

    public final BauhausDialogBuilder setNegativeButton(CharSequence text, DialogInterface.OnClickListener onClickListener) {
        if (isBauhausDialogEnabled()) {
            BauhausDialog.Builder builder = this.bauhausDialogBuilder;
            if (builder != null) {
                builder.setNegativeButton(text, onClickListener);
            }
        } else {
            this.alertDialogBuilder.setNegativeButton(text, onClickListener);
        }
        return this;
    }

    public final BauhausDialogBuilder setNeutralButton(int textResId, DialogInterface.OnClickListener onClickListener) {
        if (isBauhausDialogEnabled()) {
            CharSequence text = this.context.getText(textResId);
            Intrinsics.checkNotNullExpressionValue(text, "context.getText(textResId)");
            BauhausDialog.Builder builder = this.bauhausDialogBuilder;
            if (builder != null) {
                builder.setPositiveButton(text, onClickListener);
            }
        } else {
            this.alertDialogBuilder.setNeutralButton(textResId, onClickListener);
        }
        return this;
    }

    public final BauhausDialogBuilder setButtonOrientation(Orientation orientation) {
        BauhausDialog.Builder builder;
        Intrinsics.checkNotNullParameter(orientation, "orientation");
        if (isBauhausDialogEnabled() && (builder = this.bauhausDialogBuilder) != null) {
            builder.setButtonOrientation(orientation);
        }
        return this;
    }

    public final BauhausDialogBuilder setCancelable(boolean isCancelable) {
        if (isBauhausDialogEnabled()) {
            BauhausDialog.Builder builder = this.bauhausDialogBuilder;
            if (builder != null) {
                builder.setCancelable(isCancelable);
            }
        } else {
            this.alertDialogBuilder.setCancelable(isCancelable);
        }
        return this;
    }

    public final BauhausDialogBuilder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        Intrinsics.checkNotNullParameter(onCancelListener, "onCancelListener");
        if (isBauhausDialogEnabled()) {
            BauhausDialog.Builder builder = this.bauhausDialogBuilder;
            if (builder != null) {
                builder.setOnCancelListener(onCancelListener);
            }
        } else {
            this.alertDialogBuilder.setOnCancelListener(onCancelListener);
        }
        return this;
    }

    public final BauhausDialogBuilder setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        Intrinsics.checkNotNullParameter(onDismissListener, "onDismissListener");
        if (isBauhausDialogEnabled()) {
            BauhausDialog.Builder builder = this.bauhausDialogBuilder;
            if (builder != null) {
                builder.setOnDismissListener(onDismissListener);
            }
        } else {
            this.alertDialogBuilder.setOnDismissListener(onDismissListener);
        }
        return this;
    }

    public Dialog create() {
        if (isBauhausDialogEnabled()) {
            BauhausDialog.Builder builder = this.bauhausDialogBuilder;
            Dialog dialogCreate = builder == null ? null : builder.create();
            if (dialogCreate == null) {
                dialogCreate = this.alertDialogBuilder.create();
            }
            Intrinsics.checkNotNullExpressionValue(dialogCreate, "{\n            bauhausDia…uilder.create()\n        }");
            return dialogCreate;
        }
        AlertDialog alertDialogCreate = this.alertDialogBuilder.create();
        Intrinsics.checkNotNullExpressionValue(alertDialogCreate, "{\n            alertDialo…uilder.create()\n        }");
        return alertDialogCreate;
    }

    private final boolean isBauhausDialogEnabled() {
        return isBauhausIAMEnabled && this.bauhausDialogBuilder != null;
    }
}
