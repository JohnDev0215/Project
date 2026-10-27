package com.android.car.developeroptions.homepage.contextualcards;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.android.car.developeroptions.R;
import com.android.internal.app.AlertActivity;
import com.android.internal.app.AlertController;

/* JADX INFO: loaded from: classes.dex */
public class ContextualCardFeedbackDialog extends AlertActivity implements DialogInterface.OnClickListener {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        AlertController.AlertParams alertParams = ((AlertActivity) this).mAlertParams;
        alertParams.mMessage = getText(R.string.contextual_card_feedback_confirm_message);
        alertParams.mPositiveButtonText = getText(R.string.contextual_card_feedback_send);
        alertParams.mPositiveButtonListener = this;
        alertParams.mNegativeButtonText = getText(R.string.skip_label);
        setupAlert();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        String stringExtra = getIntent().getStringExtra("card_name");
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("mailto:" + getIntent().getStringExtra("feedback_email")));
        intent.putExtra("android.intent.extra.SUBJECT", "Settings Contextual Card Feedback - " + stringExtra);
        intent.addFlags(268435456);
        try {
            startActivity(intent);
        } catch (Exception e) {
            Log.e("CardFeedbackDialog", "Send feedback failed.", e);
        }
        finish();
    }
}
