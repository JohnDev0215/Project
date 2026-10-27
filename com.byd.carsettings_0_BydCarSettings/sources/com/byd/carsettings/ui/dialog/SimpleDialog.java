package com.byd.carsettings.ui.dialog;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.byd.base.view.dialog.BaseBydDialog;
import com.byd.base.view.dialog.FrameDialogView;
import com.byd.carsettings.R;

/* JADX INFO: loaded from: classes2.dex */
public class SimpleDialog extends BaseBydDialog {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f2983e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f2984f;
    public String g;
    public String h;
    public int i;

    public static SimpleDialog z(String str, String str2, String str3, String str4, int i) {
        SimpleDialog simpleDialog = new SimpleDialog();
        Bundle bundle = new Bundle();
        bundle.putString("title", str);
        bundle.putString(NotificationCompat.CATEGORY_MESSAGE, str2);
        bundle.putString("sure", str3);
        bundle.putString("cancel", str4);
        bundle.putInt(NotificationCompat.MessagingStyle.Message.KEY_DATA_MIME_TYPE, i);
        simpleDialog.setArguments(bundle);
        return simpleDialog;
    }

    @Override // com.byd.base.view.dialog.BaseBydDialog, com.byd.base.view.dialog.LeakDialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        this.f2983e = arguments.getString("title");
        this.f2984f = arguments.getString(NotificationCompat.CATEGORY_MESSAGE);
        this.g = arguments.getString("sure");
        this.h = arguments.getString("cancel");
        this.i = arguments.getInt(NotificationCompat.MessagingStyle.Message.KEY_DATA_MIME_TYPE);
    }

    @Override // com.byd.base.view.dialog.BaseBydDialog
    /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
    public FrameDialogView t(Bundle bundle) {
        SimpleView simpleView = new SimpleView(getContext(), this);
        int i = this.i;
        if (i == 102) {
            this.f2983e = getResources().getString(R.string.title_clear_app_data);
            this.f2984f = getResources().getString(R.string.msg_clear_app_data);
            this.g = getResources().getString(R.string.dialog_btn_ok);
            this.h = getResources().getString(R.string.dialog_btn_cancel);
        } else if (i == 103) {
            this.f2983e = getResources().getString(R.string.title_clear_app_cache);
            this.f2984f = getResources().getString(R.string.msg_clear_app_cache);
            this.g = getResources().getString(R.string.dialog_btn_ok);
            this.h = getResources().getString(R.string.dialog_btn_cancel);
        }
        simpleView.setShowText(this.f2983e, this.f2984f, this.g, this.h);
        simpleView.setYeWuType(this.i);
        return simpleView;
    }
}
