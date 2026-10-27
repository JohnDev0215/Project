package com.android.car.settings.common;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.car.ui.AlertDialogBuilder;
import com.android.car.ui.preference.CarUiDialogFragment;

/* JADX INFO: loaded from: classes5.dex */
public class ConfirmationDialogFragment extends CarUiDialogFragment {
    private static final String ALL_ARGUMENTS_KEY = "ConfirmationDialogFragment_all_arguments";
    private static final String ARGUMENTS_KEY = "ConfirmationDialogFragment_arguments";
    private static final String MESSAGE_KEY = "ConfirmationDialogFragment_message";
    private static final String NEGATIVE_KEY = "ConfirmationDialogFragment_negative";
    private static final String NEUTRAL_KEY = "ConfirmationDialogFragment_neutral";
    private static final String POSITIVE_KEY = "ConfirmationDialogFragment_positive";
    public static final String TAG = "ConfirmationDialogFragment";
    private static final String TITLE_KEY = "ConfirmationDialogFragment_title";
    private ConfirmListener mConfirmListener;
    private DismissListener mDismissListener;
    private String mMessage;
    private String mNegLabel;
    private String mNeuLabel;
    private NeutralListener mNeutralListener;
    private String mPosLabel;
    private RejectListener mRejectListener;
    private String mTitle;

    public interface ConfirmListener {
        void onConfirm(Bundle bundle);
    }

    public interface DismissListener {
        void onDismiss(Bundle bundle);
    }

    public interface NeutralListener {
        void onNeutral(Bundle bundle);
    }

    public interface RejectListener {
        void onReject(Bundle bundle);
    }

    public static class Builder {
        private Bundle mArgs;
        private ConfirmListener mConfirmListener;
        private final Context mContext;
        private DismissListener mDismissListener;
        private String mMessage;
        private String mNegLabel;
        private String mNeuLabel;
        private NeutralListener mNeutralListener;
        private String mPosLabel;
        private RejectListener mRejectListener;
        private String mTitle;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setTitle(String title) {
            this.mTitle = title;
            return this;
        }

        public Builder setTitle(int title) {
            this.mTitle = this.mContext.getString(title);
            return this;
        }

        public Builder setMessage(String message) {
            this.mMessage = message;
            return this;
        }

        public Builder setMessage(int message) {
            this.mMessage = this.mContext.getString(message);
            return this;
        }

        public Builder setPositiveButton(String label, ConfirmListener confirmListener) {
            this.mPosLabel = label;
            this.mConfirmListener = confirmListener;
            return this;
        }

        public Builder setPositiveButton(int label, ConfirmListener confirmListener) {
            this.mPosLabel = this.mContext.getString(label);
            this.mConfirmListener = confirmListener;
            return this;
        }

        public Builder setNegativeButton(String label, RejectListener rejectListener) {
            this.mNegLabel = label;
            this.mRejectListener = rejectListener;
            return this;
        }

        public Builder setNegativeButton(int label, RejectListener rejectListener) {
            this.mNegLabel = this.mContext.getString(label);
            this.mRejectListener = rejectListener;
            return this;
        }

        public Builder setNeutralButton(int label, NeutralListener neutralListener) {
            this.mNeuLabel = this.mContext.getString(label);
            this.mNeutralListener = neutralListener;
            return this;
        }

        public Builder setDismissListener(DismissListener dismissListener) {
            this.mDismissListener = dismissListener;
            return this;
        }

        public Builder addArgumentString(String argumentKey, String argument) {
            if (this.mArgs == null) {
                this.mArgs = new Bundle();
            }
            this.mArgs.putString(argumentKey, argument);
            return this;
        }

        public Builder addArgumentLong(String argumentKey, long argument) {
            if (this.mArgs == null) {
                this.mArgs = new Bundle();
            }
            this.mArgs.putLong(argumentKey, argument);
            return this;
        }

        public Builder addArgumentBoolean(String argumentKey, boolean argument) {
            if (this.mArgs == null) {
                this.mArgs = new Bundle();
            }
            this.mArgs.putBoolean(argumentKey, argument);
            return this;
        }

        public Builder addArgumentParcelable(String argumentKey, Parcelable argument) {
            if (this.mArgs == null) {
                this.mArgs = new Bundle();
            }
            this.mArgs.putParcelable(argumentKey, argument);
            return this;
        }

        public ConfirmationDialogFragment build() {
            return ConfirmationDialogFragment.init(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ConfirmationDialogFragment init(Builder builder) {
        ConfirmationDialogFragment dialogFragment = new ConfirmationDialogFragment();
        Bundle args = new Bundle();
        args.putBundle(ARGUMENTS_KEY, builder.mArgs);
        args.putString(TITLE_KEY, builder.mTitle);
        args.putString(MESSAGE_KEY, builder.mMessage);
        args.putString(POSITIVE_KEY, builder.mPosLabel);
        args.putString(NEGATIVE_KEY, builder.mNegLabel);
        args.putString(NEUTRAL_KEY, builder.mNeuLabel);
        dialogFragment.setArguments(args);
        dialogFragment.setConfirmListener(builder.mConfirmListener);
        dialogFragment.setRejectListener(builder.mRejectListener);
        dialogFragment.setNeutralListener(builder.mNeutralListener);
        dialogFragment.setDismissListener(builder.mDismissListener);
        return dialogFragment;
    }

    public static void resetListeners(ConfirmationDialogFragment dialogFragment, ConfirmListener confirmListener, RejectListener rejectListener, NeutralListener neutralListener) {
        if (dialogFragment != null) {
            dialogFragment.setConfirmListener(confirmListener);
            dialogFragment.setRejectListener(rejectListener);
            dialogFragment.setNeutralListener(neutralListener);
        }
    }

    public void setDismissListener(DismissListener dismissListener) {
        this.mDismissListener = dismissListener;
    }

    public DismissListener getDismissListener() {
        return this.mDismissListener;
    }

    private void setConfirmListener(ConfirmListener confirmListener) {
        this.mConfirmListener = confirmListener;
    }

    public ConfirmListener getConfirmListener() {
        return this.mConfirmListener;
    }

    private void setRejectListener(RejectListener rejectListener) {
        this.mRejectListener = rejectListener;
    }

    public RejectListener getRejectListener() {
        return this.mRejectListener;
    }

    private void setNeutralListener(NeutralListener neutralListener) {
        this.mNeutralListener = neutralListener;
    }

    public NeutralListener getNeutralListener() {
        return this.mNeutralListener;
    }

    @Override // com.android.car.ui.preference.CarUiDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (savedInstanceState != null) {
            args = savedInstanceState.getBundle(ALL_ARGUMENTS_KEY);
        }
        if (args != null) {
            this.mTitle = getArguments().getString(TITLE_KEY);
            this.mMessage = getArguments().getString(MESSAGE_KEY);
            this.mPosLabel = getArguments().getString(POSITIVE_KEY);
            this.mNegLabel = getArguments().getString(NEGATIVE_KEY);
            this.mNeuLabel = getArguments().getString(NEUTRAL_KEY);
        }
    }

    @Override // com.android.car.ui.preference.CarUiDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(ALL_ARGUMENTS_KEY, getArguments());
    }

    @Override // com.android.car.ui.preference.CarUiDialogFragment, androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialogBuilder builder = new AlertDialogBuilder(getContext());
        if (!TextUtils.isEmpty(this.mTitle)) {
            builder.setTitle(this.mTitle);
        }
        if (!TextUtils.isEmpty(this.mMessage)) {
            builder.setMessage(this.mMessage);
        }
        if (!TextUtils.isEmpty(this.mPosLabel)) {
            builder.setPositiveButton(this.mPosLabel, this);
        }
        if (!TextUtils.isEmpty(this.mNegLabel)) {
            builder.setNegativeButton(this.mNegLabel, this);
        }
        if (!TextUtils.isEmpty(this.mNeuLabel)) {
            builder.setNeutralButton(this.mNeuLabel, this);
        }
        return builder.create();
    }

    @Override // com.android.car.ui.preference.CarUiDialogFragment
    protected void onDialogClosed(boolean positiveResult) {
        DismissListener dismissListener = this.mDismissListener;
        if (dismissListener != null) {
            dismissListener.onDismiss(getArguments().getBundle(ARGUMENTS_KEY));
        }
    }

    @Override // com.android.car.ui.preference.CarUiDialogFragment, android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialog, int which) {
        NeutralListener neutralListener;
        if (which == -1) {
            ConfirmListener confirmListener = this.mConfirmListener;
            if (confirmListener != null) {
                confirmListener.onConfirm(getArguments().getBundle(ARGUMENTS_KEY));
                return;
            }
            return;
        }
        if (which == -2) {
            RejectListener rejectListener = this.mRejectListener;
            if (rejectListener != null) {
                rejectListener.onReject(getArguments().getBundle(ARGUMENTS_KEY));
                return;
            }
            return;
        }
        if (which == -3 && (neutralListener = this.mNeutralListener) != null) {
            neutralListener.onNeutral(getArguments().getBundle(ARGUMENTS_KEY));
        }
    }
}
