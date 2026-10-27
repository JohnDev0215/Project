package com.amazon.music.inappmessaging.ui.notification;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.amazon.music.inappmessaging.R;
import com.squareup.picasso.Picasso;
import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public abstract class NotificationDialog extends DialogFragment {
    private Button buttonLeft;
    private Button buttonRight;
    private TextView caption;
    private TextView header1;
    private TextView header2;
    private ImageView image;
    private Notification notification;

    public abstract void onImageClicked();

    public abstract void onNotificationDismissed();

    public abstract void onNotificationReported();

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Notification notification = (Notification) getArguments().getSerializable("com.amazon.music.messaging.NOTIFICATION");
        this.notification = notification;
        Validate.notNull(notification, "notification can't be null", new Object[0]);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        this.buttonLeft.setOnClickListener(null);
        this.buttonRight.setOnClickListener(null);
        super.onDestroy();
    }

    @Override // androidx.fragment.app.DialogFragment
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.notification_dialog);
        bindView(dialog);
        bindData();
        bindOnClickDismiss();
        bindOnClickReport();
        bindOnImageClicked();
        return dialog;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        onNotificationDismissed();
    }

    private void bindView(final Dialog dialog) {
        this.image = (ImageView) dialog.findViewById(R.id.notification_dialog_image);
        this.caption = (TextView) dialog.findViewById(R.id.notification_dialog_caption);
        this.header1 = (TextView) dialog.findViewById(R.id.notification_dialog_header1);
        this.header2 = (TextView) dialog.findViewById(R.id.notification_dialog_header2);
        this.buttonLeft = (Button) dialog.findViewById(R.id.notification_dialog_button_left);
        this.buttonRight = (Button) dialog.findViewById(R.id.notification_dialog_button_right);
    }

    private void bindData() {
        String str;
        if (2 == getResources().getConfiguration().orientation) {
            str = this.notification.imageLand;
        } else {
            str = this.notification.imagePort;
        }
        Picasso.get().load(str).into(this.image);
        this.header1.setText(this.notification.header1);
        this.header2.setText(this.notification.header2);
        bindTextView(this.caption, this.notification.caption);
        bindTextView(this.buttonLeft, this.notification.buttonLeftText);
        bindTextView(this.buttonRight, this.notification.buttonRightText);
    }

    private void bindOnClickDismiss() {
        this.buttonRight.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.inappmessaging.ui.notification.NotificationDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                NotificationDialog.this.onNotificationDismissed();
                NotificationDialog.this.dismiss();
            }
        });
    }

    private void bindOnClickReport() {
        this.buttonLeft.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.inappmessaging.ui.notification.NotificationDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                NotificationDialog.this.onNotificationReported();
                NotificationDialog.this.dismiss();
            }
        });
    }

    private void bindOnImageClicked() {
        this.image.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.inappmessaging.ui.notification.NotificationDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                NotificationDialog.this.onImageClicked();
                NotificationDialog.this.dismiss();
            }
        });
    }

    private void bindTextView(final TextView view, final String text) {
        if (StringUtils.isEmpty(text)) {
            view.setVisibility(8);
        } else {
            view.setText(text);
        }
    }

    public static final class Notification implements Serializable {
        public final String buttonLeftText;
        public final String buttonRightText;
        public final String caption;
        public final String header1;
        public final String header2;
        public final String imageLand;
        public final String imagePort;

        public Notification(final String imageLand, final String imagePort, final String caption, final String header1, final String header2, final String buttonLeftText, final String buttonRightText) {
            this.imageLand = imageLand;
            this.imagePort = imagePort;
            this.caption = caption;
            this.header1 = header1;
            this.header2 = header2;
            this.buttonLeftText = buttonLeftText;
            this.buttonRightText = buttonRightText;
        }
    }
}
