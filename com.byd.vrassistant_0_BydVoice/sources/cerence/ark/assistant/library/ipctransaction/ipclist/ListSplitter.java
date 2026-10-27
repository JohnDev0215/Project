package cerence.ark.assistant.library.ipctransaction.ipclist;

import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.library.ipctransaction.ISplitter;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ListSplitter<T> implements ISplitter<List<T>> {
    private static final int MAX_TRANSFER_ITEM_COUNT = 2000;
    private static final String TAG = "ListSplitter";

    @Override // cerence.ark.assistant.library.ipctransaction.ISplitter
    public void split(List<T> list, ISplitter.SplitterListener<List<T>> splitterListener) {
        if (splitterListener == null) {
            Logger.error(TAG, "The splitter listener is null");
            return;
        }
        if (list == null || list.isEmpty()) {
            splitterListener.onSplit(list, true, true);
            return;
        }
        int size = list.size();
        if (size <= 2000) {
            splitterListener.onSplit(list, true, true);
            return;
        }
        int i = size / 2000;
        if (size % 2000 > 0) {
            i++;
        }
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            if (i2 == i - 1) {
                splitterListener.onSplit(new ArrayList(list.subList(i3, size)), false, true);
                i3 = size;
            } else {
                int i4 = i3 + 2000;
                splitterListener.onSplit(new ArrayList(list.subList(i3, i4)), i2 == 0, false);
                i3 = i4;
            }
            i2++;
        }
    }
}
