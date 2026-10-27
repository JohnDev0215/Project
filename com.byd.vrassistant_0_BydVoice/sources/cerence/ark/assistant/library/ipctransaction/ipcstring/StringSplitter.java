package cerence.ark.assistant.library.ipctransaction.ipcstring;

import android.text.TextUtils;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.library.ipctransaction.ISplitter;

/* JADX INFO: loaded from: classes.dex */
public class StringSplitter implements ISplitter<String> {
    private static final int MAX_TRANSFER_STRING_LENGTH = 51200;
    private static final String TAG = "StringSplitter";

    @Override // cerence.ark.assistant.library.ipctransaction.ISplitter
    public void split(String str, ISplitter.SplitterListener<String> splitterListener) {
        if (splitterListener == null) {
            Logger.error(TAG, "The splitter listener is null");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            splitterListener.onSplit(str, true, true);
            return;
        }
        int length = str.length();
        if (length <= MAX_TRANSFER_STRING_LENGTH) {
            splitterListener.onSplit(str, true, true);
            return;
        }
        int i = length / MAX_TRANSFER_STRING_LENGTH;
        if (length % MAX_TRANSFER_STRING_LENGTH > 0) {
            i++;
        }
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            if (i2 == i - 1) {
                splitterListener.onSplit(str.substring(i3, length), false, true);
                i3 = length;
            } else {
                int i4 = i3 + MAX_TRANSFER_STRING_LENGTH;
                splitterListener.onSplit(str.substring(i3, i4), i2 == 0, false);
                i3 = i4;
            }
            i2++;
        }
    }
}
