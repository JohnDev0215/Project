package com.amazon.mp3.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.dialog.fragment.ExplicitContentUnavailableDialogFragment;
import com.amazon.mp3.elf.ExplicitLanguageFilterMetrics;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.mp3.util.ChildUserUtil;

/* JADX INFO: loaded from: classes3.dex */
public class ExplicitFilterDialogBuilder {
    private boolean mContentUnavailable;
    private final Context mContext;
    private boolean mIsExplicitFilterEnabled;
    private String mMessage;
    private DialogInterface.OnClickListener mNegativeOnClickListener;
    private DialogInterface.OnClickListener mOnClickListener;
    private String mTitle;

    private ExplicitFilterDialogBuilder(final Context context) {
        this.mContext = context;
    }

    public static ExplicitFilterDialogBuilder builder(final Context context) {
        return new ExplicitFilterDialogBuilder(context);
    }

    public ExplicitFilterDialogBuilder withExplicitFilterEnabled(final boolean isEnabled) {
        this.mIsExplicitFilterEnabled = isEnabled;
        return this;
    }

    public ExplicitFilterDialogBuilder withContentUnavailable() {
        this.mContentUnavailable = true;
        return this;
    }

    public ExplicitFilterDialogBuilder withPositiveButtonOnClickListener(final DialogInterface.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
        return this;
    }

    public ExplicitFilterDialogBuilder withNegativeButtonOnClickListener(final DialogInterface.OnClickListener onClickListener) {
        this.mNegativeOnClickListener = onClickListener;
        return this;
    }

    public ExplicitFilterDialogBuilder withTitle(final String title) {
        this.mTitle = title;
        return this;
    }

    public ExplicitFilterDialogBuilder withMessage(final String message) {
        this.mMessage = message;
        return this;
    }

    public Dialog build() {
        return build(ExplicitContentUnavailableDialogFragment.ExplicitContentType.AUDIO);
    }

    public Dialog build(final ExplicitContentUnavailableDialogFragment.ExplicitContentType explicitContentType) {
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(this.mContext);
        String str = this.mTitle;
        if (str == null) {
            bauhausDialogBuilder.setTitle(getTitleResource(explicitContentType));
        } else {
            bauhausDialogBuilder.setTitle(str);
        }
        String str2 = this.mMessage;
        if (str2 == null) {
            bauhausDialogBuilder.setMessage(getMessageResource(explicitContentType));
        } else {
            bauhausDialogBuilder.setMessage(str2);
        }
        bauhausDialogBuilder.setCancelable(false);
        if (!ChildUserUtil.INSTANCE.isChildUser(this.mContext)) {
            bauhausDialogBuilder.setPositiveButton(getPositiveButtonResource(explicitContentType), this.mOnClickListener);
        }
        DialogInterface.OnClickListener onClickListener = this.mNegativeOnClickListener;
        if (onClickListener == null) {
            onClickListener = new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.dialog.ExplicitFilterDialogBuilder$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$0.lambda$build$0(dialogInterface, i);
                }
            };
        }
        bauhausDialogBuilder.setNegativeButton(R.string.dmusic_button_cancel, onClickListener);
        return bauhausDialogBuilder.create();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$build$0(DialogInterface dialogInterface, int i) {
        this.mContext.sendBroadcast(new Intent("com.amazon.mp3.library.activity.nowplaying.finish"));
        ExplicitLanguageFilterMetrics.emitCancelClickMetric(!this.mContentUnavailable);
    }

    protected int getMessageResource(final ExplicitContentUnavailableDialogFragment.ExplicitContentType explicitContentType) {
        return ChildUserUtil.INSTANCE.isChildUser(this.mContext) ? R.string.dmusic_explicit_filter_blocked_toast : explicitContentType == ExplicitContentUnavailableDialogFragment.ExplicitContentType.VIDEO ? R.string.dmusic_explicit_filter_unblock_message_video : this.mIsExplicitFilterEnabled ? R.string.dmusic_explicit_filter_unblock_message : R.string.dmusic_explicit_filter_block_message;
    }

    protected int getPositiveButtonResource(final ExplicitContentUnavailableDialogFragment.ExplicitContentType explicitContentType) {
        return explicitContentType == ExplicitContentUnavailableDialogFragment.ExplicitContentType.VIDEO ? R.string.dmusic_explicit_filter_unblock_button_video : this.mIsExplicitFilterEnabled ? R.string.dmusic_explicit_filter_unblock_button : R.string.dmusic_explicit_filter_block_button;
    }

    protected int getTitleResource(final ExplicitContentUnavailableDialogFragment.ExplicitContentType explicitContentType) {
        return explicitContentType == ExplicitContentUnavailableDialogFragment.ExplicitContentType.VIDEO ? R.string.dmusic_explicit_filter_unblock_title_video : this.mContentUnavailable ? R.string.dmusic_explicit_filter_content_unavailable_title : this.mIsExplicitFilterEnabled ? R.string.dmusic_explicit_filter_unblock_title : R.string.dmusic_explicit_filter_block_title;
    }
}
