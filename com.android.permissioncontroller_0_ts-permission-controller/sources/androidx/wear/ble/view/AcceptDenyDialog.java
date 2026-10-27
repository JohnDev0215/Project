package androidx.wear.ble.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;
import com.android.permissioncontroller.R;

/* JADX INFO: loaded from: classes7.dex */
public class AcceptDenyDialog extends Dialog {
    private final View.OnClickListener mButtonHandler;
    protected View mButtonPanel;
    protected ImageView mIcon;
    protected TextView mMessage;
    protected ImageButton mNegativeButton;
    protected DialogInterface.OnClickListener mNegativeButtonListener;
    protected ImageButton mPositiveButton;
    protected DialogInterface.OnClickListener mPositiveButtonListener;
    protected View mSpacer;
    protected TextView mTitle;

    public /* synthetic */ void lambda$new$0$AcceptDenyDialog(View v) {
        DialogInterface.OnClickListener onClickListener;
        DialogInterface.OnClickListener onClickListener2;
        if (v == this.mPositiveButton && (onClickListener2 = this.mPositiveButtonListener) != null) {
            onClickListener2.onClick(this, -1);
            dismiss();
        } else if (v == this.mNegativeButton && (onClickListener = this.mNegativeButtonListener) != null) {
            onClickListener.onClick(this, -2);
            dismiss();
        }
    }

    public AcceptDenyDialog(Context context) {
        this(context, 0);
    }

    public AcceptDenyDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.mButtonHandler = new View.OnClickListener() { // from class: androidx.wear.ble.view.-$$Lambda$AcceptDenyDialog$FKiFlA_m1qiTdlHK4kjx3s9nQCI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$0$AcceptDenyDialog(view);
            }
        };
        setContentView(R.layout.accept_deny_dialog);
        this.mTitle = (TextView) findViewById(android.R.id.title);
        this.mMessage = (TextView) findViewById(android.R.id.message);
        this.mIcon = (ImageView) findViewById(android.R.id.icon);
        ImageButton imageButton = (ImageButton) findViewById(android.R.id.button1);
        this.mPositiveButton = imageButton;
        imageButton.setOnClickListener(this.mButtonHandler);
        ImageButton imageButton2 = (ImageButton) findViewById(android.R.id.button2);
        this.mNegativeButton = imageButton2;
        imageButton2.setOnClickListener(this.mButtonHandler);
        this.mSpacer = (Space) findViewById(R.id.spacer);
        this.mButtonPanel = findViewById(R.id.buttonPanel);
    }

    public ImageButton getButton(int whichButton) {
        if (whichButton == -2) {
            return this.mNegativeButton;
        }
        if (whichButton == -1) {
            return this.mPositiveButton;
        }
        return null;
    }

    public void setIcon(Drawable icon) {
        this.mIcon.setVisibility(icon == null ? 8 : 0);
        this.mIcon.setImageDrawable(icon);
    }

    public void setIcon(int resId) {
        this.mIcon.setVisibility(resId == 0 ? 8 : 0);
        this.mIcon.setImageResource(resId);
    }

    public void setMessage(CharSequence message) {
        this.mMessage.setText(message);
        this.mMessage.setVisibility(message == null ? 8 : 0);
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence title) {
        this.mTitle.setText(title);
    }

    public void setButton(int whichButton, DialogInterface.OnClickListener listener) {
        if (whichButton == -2) {
            this.mNegativeButtonListener = listener;
        } else if (whichButton == -1) {
            this.mPositiveButtonListener = listener;
        } else {
            return;
        }
        this.mSpacer.setVisibility((this.mPositiveButtonListener == null || this.mNegativeButtonListener == null) ? 8 : 4);
        this.mPositiveButton.setVisibility(this.mPositiveButtonListener == null ? 8 : 0);
        this.mNegativeButton.setVisibility(this.mNegativeButtonListener == null ? 8 : 0);
        this.mButtonPanel.setVisibility((this.mPositiveButtonListener == null && this.mNegativeButtonListener == null) ? 8 : 0);
    }

    public void setPositiveButton(DialogInterface.OnClickListener listener) {
        setButton(-1, listener);
    }

    public void setNegativeButton(DialogInterface.OnClickListener listener) {
        setButton(-2, listener);
    }
}
