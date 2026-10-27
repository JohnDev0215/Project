package cerence.ark.assistant.core.dcc;

import cerence.ark.assistant.api.data.DynamicWord;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.library.Utils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DccUtils {
    private static final String TAG = "DccUtils";

    private DccUtils() {
    }

    public static List<String> getDynamicWords(List<DynamicWord> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                DynamicWord dynamicWord = list.get(i);
                if (dynamicWord == null) {
                    Logger.warn(TAG, Utils.format("the dynamic word is null, index = %s", Integer.valueOf(i)));
                } else {
                    arrayList.add(dynamicWord.getId() + DccConstant.DCC_SEPARATOR + dynamicWord.getPhrase());
                }
            }
        }
        return arrayList;
    }
}
