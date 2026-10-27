package cerence.ark.assistant.library.ipctransaction.ipcstring;

import android.text.TextUtils;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.library.Utils;
import cerence.ark.assistant.library.ipctransaction.ISplitter;

/* JADX INFO: loaded from: classes2.dex */
public class StringSplitter implements ISplitter<String> {
    private static final int MAX_TRANSFER_STRING_LENGTH = 51200;
    private static final String TAG = "StringSplitter";

    @Override // cerence.ark.assistant.library.ipctransaction.ISplitter
    public void split(String str, ISplitter.SplitterListener<String> splitterListener) {
        if (splitterListener == null) {
            Logger.error(TAG, "The splitter listener is null");
            return;
        }
        String strGenerateUid = Utils.generateUid();
        int i = 0;
        if (TextUtils.isEmpty(str)) {
            splitterListener.onSplit(str, strGenerateUid, 0, 1);
            return;
        }
        int length = str.length();
        if (length <= MAX_TRANSFER_STRING_LENGTH) {
            splitterListener.onSplit(str, strGenerateUid, 0, 1);
            return;
        }
        int i2 = length / MAX_TRANSFER_STRING_LENGTH;
        if (length % MAX_TRANSFER_STRING_LENGTH > 0) {
            i2++;
        }
        int i3 = 0;
        while (i < i2) {
            int i4 = i == i2 + (-1) ? length : i3 + MAX_TRANSFER_STRING_LENGTH;
            splitterListener.onSplit(str.substring(i3, i4), strGenerateUid, i, i2);
            i++;
            i3 = i4;
        }
    }
}
