package com.android.server.telecom.settings;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.provider.BlockedNumberContract;
import android.telephony.PhoneNumberUtils;
import android.text.SpannableString;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import com.android.server.telecom.R;

/* JADX INFO: loaded from: classes.dex */
public class BlockedNumbersAdapter extends SimpleCursorAdapter {
    public BlockedNumbersAdapter(Context context, int i, Cursor cursor, String[] strArr, int[] iArr, int i2) {
        super(context, i, cursor, strArr, iArr, i2);
    }

    @Override // android.widget.SimpleCursorAdapter, android.widget.CursorAdapter
    public void bindView(View view, final Context context, Cursor cursor) {
        super.bindView(view, context, cursor);
        final String string = cursor.getString(cursor.getColumnIndex("original_number"));
        final String number = BlockedNumbersUtil.formatNumber(string);
        TextView textView = (TextView) view.findViewById(R.id.blocked_number);
        if ((number != null && number.contains("@")) || number.contains("%40")) {
            textView.setText(number);
        } else {
            SpannableString spannableString = new SpannableString(number);
            PhoneNumberUtils.addTtsSpan(spannableString, 0, spannableString.length());
            textView.setText(spannableString);
        }
        view.findViewById(R.id.delete_blocked_number).setOnClickListener(new View.OnClickListener() { // from class: com.android.server.telecom.settings.BlockedNumbersAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                BlockedNumbersAdapter.this.showDeleteBlockedNumberDialog(context, string, number);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDeleteBlockedNumberDialog(final Context context, final String str, String str2) {
        String string = context.getString(R.string.unblock_dialog_body, str2);
        int iIndexOf = string.indexOf(str2);
        SpannableString spannableString = new SpannableString(string);
        PhoneNumberUtils.addTtsSpan(spannableString, iIndexOf, str2.length() + iIndexOf);
        AlertDialog alertDialogCreate = new AlertDialog.Builder(context).setMessage(spannableString).setPositiveButton(R.string.unblock_button, new DialogInterface.OnClickListener() { // from class: com.android.server.telecom.settings.BlockedNumbersAdapter.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                BlockedNumbersAdapter.this.deleteBlockedNumber(context, str);
            }
        }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener(this) { // from class: com.android.server.telecom.settings.BlockedNumbersAdapter.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create();
        alertDialogCreate.setOnShowListener(new DialogInterface.OnShowListener(this) { // from class: com.android.server.telecom.settings.BlockedNumbersAdapter.4
            @Override // android.content.DialogInterface.OnShowListener
            public void onShow(DialogInterface dialogInterface) {
                AlertDialog alertDialog = (AlertDialog) dialogInterface;
                alertDialog.getButton(-1).setAllCaps(false);
                alertDialog.getButton(-2).setAllCaps(false);
            }
        });
        alertDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteBlockedNumber(Context context, String str) {
        context.getContentResolver().delete(BlockedNumberContract.BlockedNumbers.CONTENT_URI, "original_number=?", new String[]{str});
        BlockedNumbersUtil.showToastWithFormattedNumber(((SimpleCursorAdapter) this).mContext, R.string.blocked_numbers_number_unblocked_message, str);
    }
}
