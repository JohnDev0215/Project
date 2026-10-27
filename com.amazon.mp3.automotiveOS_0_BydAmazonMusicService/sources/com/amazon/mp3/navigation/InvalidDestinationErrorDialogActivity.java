package com.amazon.mp3.navigation;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.dialog.ErrorDialogButton;
import com.amazon.mp3.dialog.OneButtonErrorDialog;
import com.amazon.mp3.util.ChildUserUtil;

/* JADX INFO: loaded from: classes3.dex */
public class InvalidDestinationErrorDialogActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showErrorDialog();
    }

    private void showErrorDialog() {
        String string;
        String string2;
        String string3;
        if (ChildUserUtil.INSTANCE.isChildUser(getApplicationContext())) {
            string = getString(R.string.dmusic_dialog_prime_kids_content_not_found_title);
            string2 = getString(R.string.dmusic_dialog_prime_kids_content_not_found_message);
            string3 = getString(R.string.dmusic_dialog_prime_kids_content_not_found_button);
        } else {
            string = getString(R.string.dmusic_dialog_deep_link_content_not_found_title);
            string2 = getString(R.string.dmusic_dialog_deep_link_content_not_found_message);
            string3 = getString(R.string.dmusic_dialog_deep_link_content_not_found_button);
        }
        new OneButtonErrorDialog(this, string, string2, new ErrorDialogButton(string3, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.navigation.InvalidDestinationErrorDialogActivity.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(final DialogInterface dialog, final int which) {
                InvalidDestinationErrorDialogActivity.this.finish();
            }
        })).show();
    }
}
