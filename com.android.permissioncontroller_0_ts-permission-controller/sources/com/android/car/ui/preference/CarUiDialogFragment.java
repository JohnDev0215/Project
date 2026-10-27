package com.android.car.ui.preference;

import android.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import com.android.car.ui.utils.CarUiUtils;

/* JADX INFO: loaded from: classes7.dex */
public abstract class CarUiDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {
    private static final String SAVE_STATE_ICON = "CarUiDialogFragment.icon";
    private static final String SAVE_STATE_LAYOUT = "CarUiDialogFragment.layout";
    private static final String SAVE_STATE_MESSAGE = "CarUiDialogFragment.message";
    private static final String SAVE_STATE_NEGATIVE_TEXT = "CarUiDialogFragment.negativeText";
    private static final String SAVE_STATE_POSITIVE_TEXT = "CarUiDialogFragment.positiveText";
    private static final String SAVE_STATE_TITLE = "CarUiDialogFragment.title";
    protected BitmapDrawable mDialogIcon;
    protected int mDialogLayoutRes;
    protected CharSequence mDialogMessage;
    protected CharSequence mDialogTitle;
    protected CharSequence mNegativeButtonText;
    protected CharSequence mPositiveButtonText;
    private int mWhichButtonClicked;

    protected abstract void onDialogClosed(boolean z);

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.mDialogTitle = savedInstanceState.getCharSequence(SAVE_STATE_TITLE);
            this.mPositiveButtonText = savedInstanceState.getCharSequence(SAVE_STATE_POSITIVE_TEXT);
            this.mNegativeButtonText = savedInstanceState.getCharSequence(SAVE_STATE_NEGATIVE_TEXT);
            this.mDialogMessage = savedInstanceState.getCharSequence(SAVE_STATE_MESSAGE);
            this.mDialogLayoutRes = savedInstanceState.getInt(SAVE_STATE_LAYOUT, 0);
            Bitmap bitmap = (Bitmap) savedInstanceState.getParcelable(SAVE_STATE_ICON);
            if (bitmap != null) {
                this.mDialogIcon = new BitmapDrawable(getResources(), bitmap);
            }
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence(SAVE_STATE_TITLE, this.mDialogTitle);
        outState.putCharSequence(SAVE_STATE_POSITIVE_TEXT, this.mPositiveButtonText);
        outState.putCharSequence(SAVE_STATE_NEGATIVE_TEXT, this.mNegativeButtonText);
        outState.putCharSequence(SAVE_STATE_MESSAGE, this.mDialogMessage);
        outState.putInt(SAVE_STATE_LAYOUT, this.mDialogLayoutRes);
        BitmapDrawable bitmapDrawable = this.mDialogIcon;
        if (bitmapDrawable != null) {
            outState.putParcelable(SAVE_STATE_ICON, bitmapDrawable.getBitmap());
        }
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity();
        this.mWhichButtonClicked = -2;
        AlertDialog.Builder builder = new AlertDialog.Builder(context).setTitle(this.mDialogTitle).setIcon(this.mDialogIcon).setPositiveButton(this.mPositiveButtonText, this).setNegativeButton(this.mNegativeButtonText, this);
        View contentView = onCreateDialogView(context);
        if (contentView != null) {
            onBindDialogView(contentView);
            builder.setView(contentView);
        } else {
            builder.setMessage(this.mDialogMessage);
        }
        onPrepareDialogBuilder(builder);
        final Dialog dialog = builder.create();
        if (needInputMethod()) {
            dialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.android.car.ui.preference.-$$Lambda$CarUiDialogFragment$SpCk5feImT4yEJooxbC98MBarQI
                @Override // android.content.DialogInterface.OnShowListener
                public final void onShow(DialogInterface dialogInterface) {
                    this.f$0.lambda$onCreateDialog$0$CarUiDialogFragment(dialog, dialogInterface);
                }
            });
        }
        return dialog;
    }

    public /* synthetic */ void lambda$onCreateDialog$0$CarUiDialogFragment(Dialog dialog, DialogInterface d) {
        requestInputMethod(dialog);
    }

    protected void onPrepareDialogBuilder(AlertDialog.Builder builder) {
    }

    protected boolean needInputMethod() {
        return false;
    }

    private void requestInputMethod(Dialog dialog) {
        Window window = dialog.getWindow();
        window.setSoftInputMode(5);
    }

    protected View onCreateDialogView(Context context) {
        int resId = this.mDialogLayoutRes;
        if (resId == 0) {
            return null;
        }
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(resId, (ViewGroup) null);
    }

    protected void onBindDialogView(View view) {
        View dialogMessageView = CarUiUtils.findViewByRefId(view, R.id.message);
        if (dialogMessageView != null) {
            CharSequence message = this.mDialogMessage;
            int newVisibility = 8;
            if (!TextUtils.isEmpty(message)) {
                if (dialogMessageView instanceof TextView) {
                    ((TextView) dialogMessageView).setText(message);
                }
                newVisibility = 0;
            }
            if (dialogMessageView.getVisibility() != newVisibility) {
                dialogMessageView.setVisibility(newVisibility);
            }
        }
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialog, int which) {
        this.mWhichButtonClicked = which;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        onDialogClosed(this.mWhichButtonClicked == -1);
    }
}
