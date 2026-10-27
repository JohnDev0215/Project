package com.bydux.api.common.tools.search;

import android.text.TextUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes5.dex */
public class BydMatchHelper {
    private static final int MAX_PLAN = 100;
    private static final String TAG = "BydMatchHelper";
    private static int[][] mPlanComb;
    private static int mPlanCombCursor;

    private BydMatchHelper() {
    }

    public static String match(String keyWord, String content) {
        if (TextUtils.isEmpty(keyWord) || TextUtils.isEmpty(content) || !content.contains(keyWord)) {
            return null;
        }
        return keyWord;
    }

    public static String matchPy(String keyWord, String[] pyArray) {
        if (TextUtils.isEmpty(keyWord) || pyArray == null || pyArray.length <= 1) {
            return null;
        }
        String lowerCase = keyWord.toLowerCase();
        int[] iArr = new int[pyArray.length - 1];
        int i = 1;
        for (int i2 = 1; i2 < pyArray.length; i2++) {
            String[] strArrSplit = pyArray[i2].split("\\|");
            if (strArrSplit == null || strArrSplit.length <= 1) {
                iArr[i2 - 1] = 1;
            } else {
                int i3 = i2 - 1;
                iArr[i3] = strArrSplit.length - 1;
                i *= iArr[i3];
            }
        }
        if (i > 100) {
            mPlanComb = (int[][]) Array.newInstance((Class<?>) int.class, 1, pyArray.length - 1);
        } else {
            preparePlayComb(i, iArr);
            if (mPlanComb == null) {
                BydLogHelper.logE(TAG, "matchPy", "no plan comb!");
                return null;
            }
        }
        return getMatchPyResult(lowerCase, pyArray);
    }

    public static String matchSinglePy(String keyWord, String[] pyArray) {
        if (TextUtils.isEmpty(keyWord) || pyArray == null || pyArray.length <= 1) {
            return null;
        }
        String lowerCase = keyWord.toLowerCase();
        mPlanComb = (int[][]) Array.newInstance((Class<?>) int.class, 1, pyArray.length - 1);
        return getMatchPyResult(lowerCase, pyArray);
    }

    public static String matchT9(String keyWord, String[] t9Array) {
        if (TextUtils.isEmpty(keyWord) || t9Array == null || t9Array.length == 0) {
            return null;
        }
        String[] strArrRemoveHead = removeHead(t9Array);
        int[] iArr = new int[strArrRemoveHead.length];
        int i = 1;
        for (int i2 = 0; i2 < strArrRemoveHead.length; i2++) {
            iArr[i2] = strArrRemoveHead[i2].split("\\|").length - 1;
            i *= iArr[i2];
        }
        if (i > 100) {
            mPlanComb = (int[][]) Array.newInstance((Class<?>) int.class, 1, strArrRemoveHead.length);
        } else {
            preparePlayComb(i, iArr);
            if (mPlanComb == null) {
                BydLogHelper.logE(TAG, "matchT9", "no plan comb!");
                return null;
            }
        }
        return getMatchT9Result(keyWord, strArrRemoveHead);
    }

    public static String matchSingleT9(String keyWord, String[] t9Array) {
        if (TextUtils.isEmpty(keyWord) || t9Array == null || t9Array.length == 0) {
            return null;
        }
        String[] strArrRemoveHead = removeHead(t9Array);
        mPlanComb = (int[][]) Array.newInstance((Class<?>) int.class, 1, strArrRemoveHead.length);
        return getMatchT9Result(keyWord, strArrRemoveHead);
    }

    private static String getMatchPyResult(String keyWord, String[] pyArray) {
        int i = 0;
        String str = "";
        int i2 = 0;
        int i3 = 0;
        boolean z = false;
        while (true) {
            int[][] iArr = mPlanComb;
            if (i2 >= iArr.length) {
                break;
            }
            int[] iArr2 = iArr[i2];
            int i4 = 1;
            while (i4 < pyArray.length && i3 < keyWord.length()) {
                String[] strArrSplit = pyArray[i4].split("\\|");
                if (strArrSplit == null || strArrSplit.length <= 1) {
                    if (!pyArray[i4].toLowerCase().equals(String.valueOf(keyWord.charAt(i3)))) {
                        if (z) {
                            str = "";
                            i3 = 0;
                            z = false;
                            break;
                        }
                    } else {
                        str = str + pyArray[i4];
                        i3++;
                        z = true;
                    }
                    if (i4 != pyArray.length - 1 && i3 < keyWord.length()) {
                        str = "";
                        i3 = 0;
                        z = false;
                    }
                    i4++;
                    i = 0;
                } else {
                    String str2 = strArrSplit[i];
                    String str3 = strArrSplit[iArr2[i4 - 1] + 1];
                    int i5 = i;
                    int i6 = i5;
                    while (true) {
                        if (i5 < str3.length() && i3 < keyWord.length()) {
                            if (i5 == 0) {
                                if (str3.charAt(i5) != keyWord.charAt(i3)) {
                                    i6 = i;
                                    break;
                                }
                                str = str + str2;
                                i3++;
                                z = true;
                                i6 = 1;
                                i5++;
                                i = 0;
                            } else {
                                if (str3.charAt(i5) != keyWord.charAt(i3)) {
                                    break;
                                }
                                i3++;
                                i5++;
                                i = 0;
                            }
                        } else {
                            break;
                        }
                    }
                    if (i6 == 0 && z) {
                        str = "";
                        i3 = 0;
                        z = false;
                        break;
                    }
                    if (i4 != pyArray.length - 1) {
                    }
                    i4++;
                    i = 0;
                }
            }
            if (z && i3 >= keyWord.length()) {
                break;
            }
            i2++;
            i = 0;
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str;
    }

    private static String getMatchT9Result(String keyWord, String[] t9Array) {
        int i = 0;
        String str = "";
        int i2 = 0;
        int i3 = 0;
        boolean z = false;
        while (true) {
            int[][] iArr = mPlanComb;
            if (i2 >= iArr.length) {
                break;
            }
            int[] iArr2 = iArr[i2];
            int i4 = i;
            while (true) {
                if (i4 >= t9Array.length || i3 >= keyWord.length()) {
                    break;
                }
                String[] strArrSplit = t9Array[i4].split("\\|");
                String str2 = strArrSplit[i];
                if (iArr2.length >= i4 && strArrSplit.length >= iArr2[i4] + 1) {
                    String str3 = strArrSplit[iArr2[i4] + 1];
                    int i5 = i;
                    int i6 = i5;
                    while (true) {
                        if (i5 < str3.length() && i3 < keyWord.length()) {
                            if (i5 == 0) {
                                if (str3.charAt(i5) != keyWord.charAt(i3)) {
                                    i6 = i;
                                    break;
                                }
                                str = str + str2;
                                i3++;
                                z = true;
                                i6 = 1;
                                i5++;
                                i = 0;
                            } else {
                                if (str3.charAt(i5) != keyWord.charAt(i3)) {
                                    break;
                                }
                                i3++;
                                i5++;
                                i = 0;
                            }
                        } else {
                            break;
                        }
                    }
                    if (i6 == 0 && z) {
                        str = "";
                        i3 = 0;
                        z = false;
                        break;
                    }
                    if (i4 == t9Array.length - 1 && i3 < keyWord.length()) {
                        str = "";
                        i3 = 0;
                        z = false;
                    }
                }
                i4++;
                i = 0;
            }
            if (z && i3 >= keyWord.length()) {
                break;
            }
            i2++;
            i = 0;
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str;
    }

    private static void preparePlayComb(int planTotal, int[] planSub) {
        if (planSub == null || planSub.length == 0) {
            mPlanComb = null;
            mPlanCombCursor = 0;
        } else {
            mPlanComb = (int[][]) Array.newInstance((Class<?>) int.class, planTotal, planSub.length);
            mPlanCombCursor = 0;
            scanPlayComb(planSub, 0);
        }
    }

    private static void scanPlayComb(int[] planSub, int index) {
        if (planSub.length <= 0) {
            return;
        }
        int i = 0;
        if (index >= planSub.length) {
            int i2 = mPlanCombCursor + 1;
            mPlanCombCursor = i2;
            if (i2 < mPlanComb.length) {
                while (i < planSub.length - 1) {
                    int[][] iArr = mPlanComb;
                    int i3 = mPlanCombCursor;
                    iArr[i3][i] = iArr[i3 - 1][i];
                    i++;
                }
                return;
            }
            return;
        }
        int[][] iArr2 = mPlanComb;
        if (iArr2.length <= 0 || iArr2[mPlanCombCursor].length <= 0) {
            return;
        }
        while (i < planSub[index]) {
            mPlanComb[mPlanCombCursor][index] = i;
            scanPlayComb(planSub, index + 1);
            i++;
        }
    }

    private static String[] removeHead(String[] array) {
        if (array == null || array.length == 0) {
            return array;
        }
        int length = array.length - 1;
        String[] strArr = new String[length];
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            strArr[i] = array[i2];
            i = i2;
        }
        return strArr;
    }
}
