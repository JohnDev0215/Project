package android.ext.services.autofill;

import android.os.Bundle;
import android.service.autofill.AutofillFieldClassificationService;
import android.util.Log;
import android.view.autofill.AutofillValue;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class AutofillFieldClassificationServiceImpl extends AutofillFieldClassificationService {
    public float[][] onCalculateScores(List<AutofillValue> list, List<String> list2, List<String> list3, String str, Bundle bundle, Map map, Map map2) {
        if (list == null || list2 == null || list.isEmpty() || list2.isEmpty()) {
            Log.w("AutofillFieldClassificationServiceImpl", "calculateScores(): empty currentvalues (" + list + ") or userValues (" + list2 + ")");
            return null;
        }
        return calculateScores(list, list2, list3, str, bundle, (HashMap) map, (HashMap) map2);
    }

    public float[][] calculateScores(List<AutofillValue> list, List<String> list2, List<String> list3, String str, Bundle bundle, HashMap<String, String> map, HashMap<String, Bundle> map2) {
        HashMap<String, String> map3 = map;
        int size = list.size();
        int size2 = list2.size();
        int i = 0;
        float[][] fArr = (float[][]) Array.newInstance((Class<?>) float.class, size, size2);
        int i2 = 0;
        while (i2 < size2) {
            String str2 = list3.get(i2);
            String str3 = (map3 == null || !map3.containsKey(str2)) ? str : map3.get(str2);
            Bundle bundle2 = (map2 == null || !map2.containsKey(str2)) ? bundle : map2.get(str2);
            if (str3 == null || (!str3.equals("EDIT_DISTANCE") && !str3.equals("EXACT_MATCH") && !str3.equals("CREDIT_CARD"))) {
                Log.w("AutofillFieldClassificationServiceImpl", "algorithmName is " + str3 + ", defaulting to EDIT_DISTANCE");
                str3 = "EDIT_DISTANCE";
            }
            for (int i3 = i; i3 < size; i3++) {
                if (str3.equals("EDIT_DISTANCE")) {
                    fArr[i3][i2] = EditDistanceScorer.calculateScore(list.get(i3), list2.get(i2), bundle2);
                } else if (str3.equals("EXACT_MATCH")) {
                    fArr[i3][i2] = ExactMatch.calculateScore(list.get(i3), list2.get(i2), bundle2);
                } else {
                    fArr[i3][i2] = CreditCardMatcher.calculateScore(list.get(i3), list2.get(i2), bundle2);
                }
            }
            i2++;
            map3 = map;
            i = 0;
        }
        return fArr;
    }
}
