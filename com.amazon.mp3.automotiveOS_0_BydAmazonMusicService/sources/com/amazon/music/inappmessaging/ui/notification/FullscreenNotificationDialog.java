package com.amazon.music.inappmessaging.ui.notification;

import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.amazon.music.inappmessaging.R;
import com.squareup.picasso.Picasso;
import java.io.Serializable;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public abstract class FullscreenNotificationDialog extends DialogFragment {
    private Button buttonLeft;
    private Button buttonRight;
    private ImageView image;
    private Notification notification;

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
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.notification_full_screen_fragment, container, false);
        bindView(viewInflate);
        bindData();
        bindOnClickDismiss();
        bindOnClickAccept();
        return viewInflate;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        onNotificationDismissed();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        this.buttonLeft.setOnClickListener(null);
        this.buttonRight.setOnClickListener(null);
        super.onDestroy();
    }

    private void bindView(final View view) {
        this.image = (ImageView) view.findViewById(R.id.notification_full_image);
        this.buttonLeft = (Button) view.findViewById(R.id.notification_full_button_left);
        this.buttonRight = (Button) view.findViewById(R.id.notification_full_button_right);
    }

    private void bindData() {
        String str;
        this.buttonLeft.setText(this.notification.dismissText);
        if (!TextUtils.isEmpty(this.notification.reportText)) {
            this.buttonRight.setVisibility(0);
            this.buttonRight.setText(this.notification.reportText);
        }
        if (2 == getResources().getConfiguration().orientation) {
            str = this.notification.imageLand;
        } else {
            str = this.notification.imagePort;
        }
        Point screenDimensions = getScreenDimensions();
        Picasso.get().load(str).resize(screenDimensions.x, screenDimensions.y).centerCrop().into(this.image);
    }

    private Point getScreenDimensions() {
        Point point = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(point);
        return point;
    }

    private void bindOnClickDismiss() {
        this.buttonLeft.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.inappmessaging.ui.notification.FullscreenNotificationDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FullscreenNotificationDialog.this.onNotificationDismissed();
                FullscreenNotificationDialog.this.dismiss();
            }
        });
    }

    private void bindOnClickAccept() {
        this.buttonRight.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.inappmessaging.ui.notification.FullscreenNotificationDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FullscreenNotificationDialog.this.onNotificationReported();
                FullscreenNotificationDialog.this.dismiss();
            }
        });
    }

    public static final class Notification implements Serializable {
        public final String dismissText;
        public final String imageLand;
        public final String imagePort;
        public final String reportText;

        public Notification(final String imageLand, final String imagePort, final String dismissText, final String reportText) {
            this.imageLand = imageLand;
            this.imagePort = imagePort;
            this.dismissText = dismissText;
            this.reportText = reportText;
        }
    }
}
