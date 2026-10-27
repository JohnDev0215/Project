package cerence.ark.assistant.library.ipctransaction.ipclist;

import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.library.Utils;
import cerence.ark.assistant.library.ipctransaction.ISplitter;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ListSplitter<T> implements ISplitter<List<T>> {
    private static final int MAX_TRANSFER_ITEM_COUNT = 2000;
    private static final String TAG = "ListSplitter";

    @Override // cerence.ark.assistant.library.ipctransaction.ISplitter
    public void split(List<T> list, ISplitter.SplitterListener<List<T>> splitterListener) {
        if (splitterListener == null) {
            Logger.error(TAG, "The splitter listener is null");
            return;
        }
        String strGenerateUid = Utils.generateUid();
        int i = 0;
        if (list == null || list.isEmpty()) {
            splitterListener.onSplit(list, strGenerateUid, 0, 1);
            return;
        }
        int size = list.size();
        if (size <= 2000) {
            splitterListener.onSplit(list, strGenerateUid, 0, 1);
            return;
        }
        int i2 = size / 2000;
        if (size % 2000 > 0) {
            i2++;
        }
        int i3 = 0;
        while (i < i2) {
            int i4 = i == i2 + (-1) ? size : i3 + 2000;
            splitterListener.onSplit(new ArrayList(list.subList(i3, i4)), strGenerateUid, i, i2);
            i++;
            i3 = i4;
        }
    }
}
