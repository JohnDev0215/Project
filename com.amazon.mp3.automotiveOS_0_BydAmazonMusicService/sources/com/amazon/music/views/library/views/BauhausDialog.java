package com.amazon.music.views.library.views;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.amazon.music.views.library.R$style;
import com.amazon.music.views.library.styles.StyleSheet;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BauhausDialog.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0002\u0017\u0018B\u001f\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0012\u0010\b\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0014J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH\u0016R\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/amazon/music/views/library/views/BauhausDialog;", "Landroid/app/Dialog;", "Lcom/amazon/music/views/library/views/BauhausDialog$BauhausDialogMetaData;", "metadata", "", "init", "Landroid/os/Bundle;", "savedInstanceState", "onCreate", "", "flag", "setCancelable", "Lcom/amazon/music/views/library/views/BauhausDialogContainerView;", "dialogContainer", "Lcom/amazon/music/views/library/views/BauhausDialogContainerView;", "Landroid/content/Context;", "context", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "", "themeResId", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;I)V", "BauhausDialogMetaData", "Builder", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class BauhausDialog extends Dialog {
    private final BauhausDialogContainerView dialogContainer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BauhausDialog(Context context, StyleSheet styleSheet, int i) {
        super(context, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.dialogContainer = new BauhausDialogContainerView(context, styleSheet);
    }

    /* JADX INFO: compiled from: BauhausDialog.kt */
    @Metadata(bv = {}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u001f\u001a\u00020\u001e\u0012\u0006\u0010\"\u001a\u00020!\u0012\u0006\u0010$\u001a\u00020\b¢\u0006\u0004\b0\u00101B\u0019\b\u0016\u0012\u0006\u0010\u001f\u001a\u00020\u001e\u0012\u0006\u0010\"\u001a\u00020!¢\u0006\u0004\b0\u00102J\u0010\u0010\u0004\u001a\u00020\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002J\u0010\u0010\u0007\u001a\u00020\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005J\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\bJ\u0010\u0010\u000b\u001a\u00020\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005J\u001a\u0010\u000e\u001a\u00020\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\r\u001a\u0004\u0018\u00010\fJ\u001a\u0010\u000f\u001a\u00020\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\r\u001a\u0004\u0018\u00010\fJ\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0010J\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0013J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0016J\u000e\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u0019J\u0006\u0010\u001d\u001a\u00020\u001cR\u0014\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010\"\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%R\u0014\u0010'\u001a\u00020&8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010(R\u0016\u0010)\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b)\u0010*R\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010+R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010,R\u0018\u0010.\u001a\u0004\u0018\u00010-8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b.\u0010/¨\u00063"}, d2 = {"Lcom/amazon/music/views/library/views/BauhausDialog$Builder;", "", "Landroid/view/View;", "view", "setView", "", "text", "setTitle", "", "alignment", "setTitleTextAlignment", "setMessage", "Landroid/content/DialogInterface$OnClickListener;", "onClickListener", "setPositiveButton", "setNegativeButton", "Lcom/amazon/music/views/library/views/Orientation;", "orientation", "setButtonOrientation", "", "isCancelable", "setCancelable", "Landroid/content/DialogInterface$OnCancelListener;", "onCancelListener", "setOnCancelListener", "Landroid/content/DialogInterface$OnDismissListener;", "onDismissListener", "setOnDismissListener", "Lcom/amazon/music/views/library/views/BauhausDialog;", "create", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "themeResId", "I", "Lcom/amazon/music/views/library/views/BauhausDialog$BauhausDialogMetaData;", "metadata", "Lcom/amazon/music/views/library/views/BauhausDialog$BauhausDialogMetaData;", "cancelable", "Z", "Landroid/content/DialogInterface$OnCancelListener;", "Landroid/content/DialogInterface$OnDismissListener;", "Landroid/content/DialogInterface$OnKeyListener;", "onKeyListener", "Landroid/content/DialogInterface$OnKeyListener;", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;I)V", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
    public static final class Builder {
        private boolean cancelable;
        private final Context context;
        private final BauhausDialogMetaData metadata;
        private DialogInterface.OnCancelListener onCancelListener;
        private DialogInterface.OnDismissListener onDismissListener;
        private DialogInterface.OnKeyListener onKeyListener;
        private final StyleSheet styleSheet;
        private final int themeResId;

        public Builder(Context context, StyleSheet styleSheet, int i) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
            this.context = context;
            this.styleSheet = styleSheet;
            this.themeResId = i;
            this.metadata = new BauhausDialogMetaData(null, null, null, null, null, null, null, null, null, 0, 1023, null);
            this.cancelable = true;
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public Builder(Context context, StyleSheet styleSheet) {
            this(context, styleSheet, R$style.dmmviewlibrary_bauhaus_dialog);
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        }

        public final Builder setView(View view) {
            this.metadata.setContent(view);
            return this;
        }

        public final Builder setTitle(CharSequence text) {
            this.metadata.setTitle(text);
            return this;
        }

        public final Builder setTitleTextAlignment(int alignment) {
            this.metadata.setTitleAlignment(alignment);
            return this;
        }

        public final Builder setMessage(CharSequence text) {
            this.metadata.setMessage(text);
            return this;
        }

        public final Builder setPositiveButton(CharSequence text, DialogInterface.OnClickListener onClickListener) {
            this.metadata.setPositiveButtonText(text);
            this.metadata.setPositiveButtonOnClickListener(onClickListener);
            return this;
        }

        public final Builder setNegativeButton(CharSequence text, DialogInterface.OnClickListener onClickListener) {
            this.metadata.setNegativeButtonText(text);
            this.metadata.setNegativeButtonOnClickListener(onClickListener);
            return this;
        }

        public final Builder setButtonOrientation(Orientation orientation) {
            Intrinsics.checkNotNullParameter(orientation, "orientation");
            this.metadata.setButtonOrientation(orientation);
            return this;
        }

        public final Builder setCancelable(boolean isCancelable) {
            this.cancelable = isCancelable;
            return this;
        }

        public final Builder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
            Intrinsics.checkNotNullParameter(onCancelListener, "onCancelListener");
            this.onCancelListener = onCancelListener;
            return this;
        }

        public final Builder setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
            Intrinsics.checkNotNullParameter(onDismissListener, "onDismissListener");
            this.onDismissListener = onDismissListener;
            return this;
        }

        public final BauhausDialog create() {
            BauhausDialog bauhausDialog = new BauhausDialog(this.context, this.styleSheet, this.themeResId);
            bauhausDialog.init(this.metadata);
            bauhausDialog.setCancelable(this.cancelable);
            if (this.cancelable) {
                bauhausDialog.setCanceledOnTouchOutside(true);
            }
            bauhausDialog.setOnCancelListener(this.onCancelListener);
            bauhausDialog.setOnDismissListener(this.onDismissListener);
            DialogInterface.OnKeyListener onKeyListener = this.onKeyListener;
            if (onKeyListener != null) {
                bauhausDialog.setOnKeyListener(onKeyListener);
            }
            return bauhausDialog;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: BauhausDialog.kt */
    @Metadata(bv = {}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\r\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0082\b\u0018\u00002\u00020\u0001B{\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001a\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010(\u001a\u0004\u0018\u00010'\u0012\n\b\u0002\u0010.\u001a\u0004\u0018\u00010'\u0012\b\b\u0002\u00102\u001a\u000201\u0012\b\b\u0002\u00108\u001a\u00020\u0004¢\u0006\u0004\b>\u0010?J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R$\u0010\n\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R$\u0010\u0017\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\u000b\u001a\u0004\b\u0018\u0010\r\"\u0004\b\u0019\u0010\u000fR$\u0010\u001b\u001a\u0004\u0018\u00010\u001a8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R$\u0010!\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b!\u0010\u000b\u001a\u0004\b\"\u0010\r\"\u0004\b#\u0010\u000fR$\u0010$\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b$\u0010\u000b\u001a\u0004\b%\u0010\r\"\u0004\b&\u0010\u000fR$\u0010(\u001a\u0004\u0018\u00010'8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R$\u0010.\u001a\u0004\u0018\u00010'8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b.\u0010)\u001a\u0004\b/\u0010+\"\u0004\b0\u0010-R\"\u00102\u001a\u0002018\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b2\u00103\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\"\u00108\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b8\u00109\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=¨\u0006@"}, d2 = {"Lcom/amazon/music/views/library/views/BauhausDialog$BauhausDialogMetaData;", "", "", "toString", "", "hashCode", "other", "", "equals", "", "title", "Ljava/lang/CharSequence;", "getTitle", "()Ljava/lang/CharSequence;", "setTitle", "(Ljava/lang/CharSequence;)V", "Landroid/graphics/drawable/Drawable;", "icon", "Landroid/graphics/drawable/Drawable;", "getIcon", "()Landroid/graphics/drawable/Drawable;", "setIcon", "(Landroid/graphics/drawable/Drawable;)V", "message", "getMessage", "setMessage", "Landroid/view/View;", "content", "Landroid/view/View;", "getContent", "()Landroid/view/View;", "setContent", "(Landroid/view/View;)V", "positiveButtonText", "getPositiveButtonText", "setPositiveButtonText", "negativeButtonText", "getNegativeButtonText", "setNegativeButtonText", "Landroid/content/DialogInterface$OnClickListener;", "positiveButtonOnClickListener", "Landroid/content/DialogInterface$OnClickListener;", "getPositiveButtonOnClickListener", "()Landroid/content/DialogInterface$OnClickListener;", "setPositiveButtonOnClickListener", "(Landroid/content/DialogInterface$OnClickListener;)V", "negativeButtonOnClickListener", "getNegativeButtonOnClickListener", "setNegativeButtonOnClickListener", "Lcom/amazon/music/views/library/views/Orientation;", "buttonOrientation", "Lcom/amazon/music/views/library/views/Orientation;", "getButtonOrientation", "()Lcom/amazon/music/views/library/views/Orientation;", "setButtonOrientation", "(Lcom/amazon/music/views/library/views/Orientation;)V", "titleAlignment", "I", "getTitleAlignment", "()I", "setTitleAlignment", "(I)V", "<init>", "(Ljava/lang/CharSequence;Landroid/graphics/drawable/Drawable;Ljava/lang/CharSequence;Landroid/view/View;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;Landroid/content/DialogInterface$OnClickListener;Lcom/amazon/music/views/library/views/Orientation;I)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
    static final /* data */ class BauhausDialogMetaData {
        private Orientation buttonOrientation;
        private View content;
        private Drawable icon;
        private CharSequence message;
        private DialogInterface.OnClickListener negativeButtonOnClickListener;
        private CharSequence negativeButtonText;
        private DialogInterface.OnClickListener positiveButtonOnClickListener;
        private CharSequence positiveButtonText;
        private CharSequence title;
        private int titleAlignment;

        public BauhausDialogMetaData() {
            this(null, null, null, null, null, null, null, null, null, 0, 1023, null);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BauhausDialogMetaData)) {
                return false;
            }
            BauhausDialogMetaData bauhausDialogMetaData = (BauhausDialogMetaData) other;
            return Intrinsics.areEqual(this.title, bauhausDialogMetaData.title) && Intrinsics.areEqual(this.icon, bauhausDialogMetaData.icon) && Intrinsics.areEqual(this.message, bauhausDialogMetaData.message) && Intrinsics.areEqual(this.content, bauhausDialogMetaData.content) && Intrinsics.areEqual(this.positiveButtonText, bauhausDialogMetaData.positiveButtonText) && Intrinsics.areEqual(this.negativeButtonText, bauhausDialogMetaData.negativeButtonText) && Intrinsics.areEqual(this.positiveButtonOnClickListener, bauhausDialogMetaData.positiveButtonOnClickListener) && Intrinsics.areEqual(this.negativeButtonOnClickListener, bauhausDialogMetaData.negativeButtonOnClickListener) && this.buttonOrientation == bauhausDialogMetaData.buttonOrientation && this.titleAlignment == bauhausDialogMetaData.titleAlignment;
        }

        public int hashCode() {
            CharSequence charSequence = this.title;
            int iHashCode = (charSequence == null ? 0 : charSequence.hashCode()) * 31;
            Drawable drawable = this.icon;
            int iHashCode2 = (iHashCode + (drawable == null ? 0 : drawable.hashCode())) * 31;
            CharSequence charSequence2 = this.message;
            int iHashCode3 = (iHashCode2 + (charSequence2 == null ? 0 : charSequence2.hashCode())) * 31;
            View view = this.content;
            int iHashCode4 = (iHashCode3 + (view == null ? 0 : view.hashCode())) * 31;
            CharSequence charSequence3 = this.positiveButtonText;
            int iHashCode5 = (iHashCode4 + (charSequence3 == null ? 0 : charSequence3.hashCode())) * 31;
            CharSequence charSequence4 = this.negativeButtonText;
            int iHashCode6 = (iHashCode5 + (charSequence4 == null ? 0 : charSequence4.hashCode())) * 31;
            DialogInterface.OnClickListener onClickListener = this.positiveButtonOnClickListener;
            int iHashCode7 = (iHashCode6 + (onClickListener == null ? 0 : onClickListener.hashCode())) * 31;
            DialogInterface.OnClickListener onClickListener2 = this.negativeButtonOnClickListener;
            return ((((iHashCode7 + (onClickListener2 != null ? onClickListener2.hashCode() : 0)) * 31) + this.buttonOrientation.hashCode()) * 31) + Integer.hashCode(this.titleAlignment);
        }

        public String toString() {
            return "BauhausDialogMetaData(title=" + ((Object) this.title) + ", icon=" + this.icon + ", message=" + ((Object) this.message) + ", content=" + this.content + ", positiveButtonText=" + ((Object) this.positiveButtonText) + ", negativeButtonText=" + ((Object) this.negativeButtonText) + ", positiveButtonOnClickListener=" + this.positiveButtonOnClickListener + ", negativeButtonOnClickListener=" + this.negativeButtonOnClickListener + ", buttonOrientation=" + this.buttonOrientation + ", titleAlignment=" + this.titleAlignment + ')';
        }

        public BauhausDialogMetaData(CharSequence charSequence, Drawable drawable, CharSequence charSequence2, View view, CharSequence charSequence3, CharSequence charSequence4, DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onClickListener2, Orientation buttonOrientation, int i) {
            Intrinsics.checkNotNullParameter(buttonOrientation, "buttonOrientation");
            this.title = charSequence;
            this.icon = drawable;
            this.message = charSequence2;
            this.content = view;
            this.positiveButtonText = charSequence3;
            this.negativeButtonText = charSequence4;
            this.positiveButtonOnClickListener = onClickListener;
            this.negativeButtonOnClickListener = onClickListener2;
            this.buttonOrientation = buttonOrientation;
            this.titleAlignment = i;
        }

        public final CharSequence getTitle() {
            return this.title;
        }

        public final void setTitle(CharSequence charSequence) {
            this.title = charSequence;
        }

        public final Drawable getIcon() {
            return this.icon;
        }

        public final CharSequence getMessage() {
            return this.message;
        }

        public final void setMessage(CharSequence charSequence) {
            this.message = charSequence;
        }

        public final View getContent() {
            return this.content;
        }

        public final void setContent(View view) {
            this.content = view;
        }

        public final CharSequence getPositiveButtonText() {
            return this.positiveButtonText;
        }

        public final void setPositiveButtonText(CharSequence charSequence) {
            this.positiveButtonText = charSequence;
        }

        public final CharSequence getNegativeButtonText() {
            return this.negativeButtonText;
        }

        public final void setNegativeButtonText(CharSequence charSequence) {
            this.negativeButtonText = charSequence;
        }

        public final DialogInterface.OnClickListener getPositiveButtonOnClickListener() {
            return this.positiveButtonOnClickListener;
        }

        public final void setPositiveButtonOnClickListener(DialogInterface.OnClickListener onClickListener) {
            this.positiveButtonOnClickListener = onClickListener;
        }

        public final DialogInterface.OnClickListener getNegativeButtonOnClickListener() {
            return this.negativeButtonOnClickListener;
        }

        public final void setNegativeButtonOnClickListener(DialogInterface.OnClickListener onClickListener) {
            this.negativeButtonOnClickListener = onClickListener;
        }

        public /* synthetic */ BauhausDialogMetaData(CharSequence charSequence, Drawable drawable, CharSequence charSequence2, View view, CharSequence charSequence3, CharSequence charSequence4, DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onClickListener2, Orientation orientation, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? null : charSequence, (i2 & 2) != 0 ? null : drawable, (i2 & 4) != 0 ? null : charSequence2, (i2 & 8) != 0 ? null : view, (i2 & 16) != 0 ? null : charSequence3, (i2 & 32) != 0 ? null : charSequence4, (i2 & 64) != 0 ? null : onClickListener, (i2 & 128) == 0 ? onClickListener2 : null, (i2 & 256) != 0 ? Orientation.HORIZONTAL : orientation, (i2 & 512) != 0 ? 2 : i);
        }

        public final Orientation getButtonOrientation() {
            return this.buttonOrientation;
        }

        public final void setButtonOrientation(Orientation orientation) {
            Intrinsics.checkNotNullParameter(orientation, "<set-?>");
            this.buttonOrientation = orientation;
        }

        public final int getTitleAlignment() {
            return this.titleAlignment;
        }

        public final void setTitleAlignment(int i) {
            this.titleAlignment = i;
        }
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(this.dialogContainer, new ViewGroup.LayoutParams(-1, -1));
        Window window = getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(-1, -1);
    }

    @Override // android.app.Dialog
    public void setCancelable(boolean flag) {
        super.setCancelable(flag);
        if (flag) {
            this.dialogContainer.setOnCloseButtonClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.views.BauhausDialog$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BauhausDialog.m2394setCancelable$lambda0(this.f$0, view);
                }
            });
        } else {
            this.dialogContainer.setOnCloseButtonClickListener(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setCancelable$lambda-0, reason: not valid java name */
    public static final void m2394setCancelable$lambda0(BauhausDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.cancel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void init(final BauhausDialogMetaData metadata) {
        this.dialogContainer.setTitle(metadata.getTitle(), metadata.getTitleAlignment());
        this.dialogContainer.setMessage(metadata.getMessage());
        this.dialogContainer.setIcon(metadata.getIcon());
        this.dialogContainer.setContent(metadata.getContent());
        CharSequence positiveButtonText = metadata.getPositiveButtonText();
        if (positiveButtonText != null) {
            this.dialogContainer.setPrimaryButton(positiveButtonText, new View.OnClickListener() { // from class: com.amazon.music.views.library.views.BauhausDialog$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BauhausDialog.m2392init$lambda2$lambda1(metadata, this, view);
                }
            });
        }
        CharSequence negativeButtonText = metadata.getNegativeButtonText();
        if (negativeButtonText != null) {
            this.dialogContainer.setSecondaryButton(negativeButtonText, new View.OnClickListener() { // from class: com.amazon.music.views.library.views.BauhausDialog$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BauhausDialog.m2393init$lambda4$lambda3(metadata, this, view);
                }
            });
        }
        this.dialogContainer.setButtonOrientation(metadata.getButtonOrientation());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: init$lambda-2$lambda-1, reason: not valid java name */
    public static final void m2392init$lambda2$lambda1(BauhausDialogMetaData metadata, BauhausDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(metadata, "$metadata");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DialogInterface.OnClickListener positiveButtonOnClickListener = metadata.getPositiveButtonOnClickListener();
        if (positiveButtonOnClickListener != null) {
            positiveButtonOnClickListener.onClick(this$0, -1);
        }
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: init$lambda-4$lambda-3, reason: not valid java name */
    public static final void m2393init$lambda4$lambda3(BauhausDialogMetaData metadata, BauhausDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(metadata, "$metadata");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DialogInterface.OnClickListener negativeButtonOnClickListener = metadata.getNegativeButtonOnClickListener();
        if (negativeButtonOnClickListener != null) {
            negativeButtonOnClickListener.onClick(this$0, -2);
        }
        this$0.dismiss();
    }
}
