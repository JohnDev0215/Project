package com.bydux.api.common.tools.search;

import android.text.TextUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.reflect.Array;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class BydMatchHelper {
    private static final int MAX_PLAN = 100;
    private static final String TAG = "BydMatchHelper";
    private static int[][] mPlanComb;
    private static int mPlanCombCursor;

    private BydMatchHelper() {
    }

    private static String getMatchPyResult(String str, String[] strArr) {
        int i = 0;
        String str2 = "";
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
            while (i4 < strArr.length && i3 < str.length()) {
                String[] strArrSplit = strArr[i4].split("\\|");
                if (strArrSplit == null || strArrSplit.length <= 1) {
                    if (!strArr[i4].toLowerCase().equals(String.valueOf(str.charAt(i3)))) {
                        if (z) {
                            str2 = "";
                            i3 = 0;
                            z = false;
                            break;
                        }
                    } else {
                        str2 = str2 + strArr[i4];
                        i3++;
                        z = true;
                    }
                    if (i4 != strArr.length - 1 && i3 < str.length()) {
                        str2 = "";
                        i3 = 0;
                        z = false;
                    }
                    i4++;
                    i = 0;
                } else {
                    String str3 = strArrSplit[i];
                    String str4 = strArrSplit[iArr2[i4 - 1] + 1];
                    int i5 = i;
                    int i6 = i5;
                    while (true) {
                        if (i5 < str4.length() && i3 < str.length()) {
                            if (i5 != 0) {
                                if (str4.charAt(i5) != str.charAt(i3)) {
                                    break;
                                }
                                i3++;
                                i5++;
                                i = 0;
                            } else {
                                if (str4.charAt(i5) != str.charAt(i3)) {
                                    i6 = i;
                                    break;
                                }
                                str2 = str2 + str3;
                                i3++;
                                z = true;
                                i6 = 1;
                                i5++;
                                i = 0;
                            }
                        } else {
                            break;
                        }
                    }
                    if (i6 == 0 && z) {
                        str2 = "";
                        i3 = 0;
                        z = false;
                        break;
                    }
                    if (i4 != strArr.length - 1) {
                    }
                    i4++;
                    i = 0;
                }
            }
            if (z && i3 >= str.length()) {
                break;
            }
            i2++;
            i = 0;
        }
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        return str2;
    }

    private static String getMatchT9Result(String str, String[] strArr) {
        int i = 0;
        String str2 = "";
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
                if (i4 >= strArr.length || i3 >= str.length()) {
                    break;
                }
                String[] strArrSplit = strArr[i4].split("\\|");
                String str3 = strArrSplit[i];
                if (iArr2.length >= i4 && strArrSplit.length >= iArr2[i4] + 1) {
                    String str4 = strArrSplit[iArr2[i4] + 1];
                    int i5 = i;
                    int i6 = i5;
                    while (true) {
                        if (i5 < str4.length() && i3 < str.length()) {
                            if (i5 != 0) {
                                if (str4.charAt(i5) != str.charAt(i3)) {
                                    break;
                                }
                                i3++;
                                i5++;
                                i = 0;
                            } else {
                                if (str4.charAt(i5) != str.charAt(i3)) {
                                    i6 = i;
                                    break;
                                }
                                str2 = str2 + str3;
                                i3++;
                                z = true;
                                i6 = 1;
                                i5++;
                                i = 0;
                            }
                        } else {
                            break;
                        }
                    }
                    if (i6 == 0 && z) {
                        str2 = "";
                        i3 = 0;
                        z = false;
                        break;
                    }
                    if (i4 == strArr.length - 1 && i3 < str.length()) {
                        str2 = "";
                        i3 = 0;
                        z = false;
                    }
                }
                i4++;
                i = 0;
            }
            if (z && i3 >= str.length()) {
                break;
            }
            i2++;
            i = 0;
        }
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        return str2;
    }

    public static String match(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !str2.contains(str)) {
            return null;
        }
        return str;
    }

    public static String matchPy(String str, String[] strArr) {
        if (TextUtils.isEmpty(str) || strArr == null || strArr.length <= 1) {
            return null;
        }
        String lowerCase = str.toLowerCase();
        int[] iArr = new int[strArr.length - 1];
        int i = 1;
        for (int i2 = 1; i2 < strArr.length; i2++) {
            String[] strArrSplit = strArr[i2].split("\\|");
            if (strArrSplit == null || strArrSplit.length <= 1) {
                iArr[i2 - 1] = 1;
            } else {
                int i3 = i2 - 1;
                iArr[i3] = strArrSplit.length - 1;
                i *= iArr[i3];
            }
        }
        if (i > 100) {
            mPlanComb = (int[][]) Array.newInstance((Class<?>) int.class, 1, strArr.length - 1);
        } else {
            preparePlayComb(i, iArr);
            if (mPlanComb == null) {
                BydLogHelper.logE(TAG, "matchPy", "no plan comb!");
                return null;
            }
        }
        return getMatchPyResult(lowerCase, strArr);
    }

    public static String matchSinglePy(String str, String[] strArr) {
        if (TextUtils.isEmpty(str) || strArr == null || strArr.length <= 1) {
            return null;
        }
        String lowerCase = str.toLowerCase();
        mPlanComb = (int[][]) Array.newInstance((Class<?>) int.class, 1, strArr.length - 1);
        return getMatchPyResult(lowerCase, strArr);
    }

    public static String matchSingleT9(String str, String[] strArr) {
        if (TextUtils.isEmpty(str) || strArr == null || strArr.length == 0) {
            return null;
        }
        String[] strArrRemoveHead = removeHead(strArr);
        mPlanComb = (int[][]) Array.newInstance((Class<?>) int.class, 1, strArrRemoveHead.length);
        return getMatchT9Result(str, strArrRemoveHead);
    }

    public static String matchT9(String str, String[] strArr) {
        if (TextUtils.isEmpty(str) || strArr == null || strArr.length == 0) {
            return null;
        }
        String[] strArrRemoveHead = removeHead(strArr);
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
        return getMatchT9Result(str, strArrRemoveHead);
    }

    private static void preparePlayComb(int i, int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            mPlanComb = null;
            mPlanCombCursor = 0;
        } else {
            mPlanComb = (int[][]) Array.newInstance((Class<?>) int.class, i, iArr.length);
            mPlanCombCursor = 0;
            scanPlayComb(iArr, 0);
        }
    }

    private static String[] removeHead(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return strArr;
        }
        int length = strArr.length - 1;
        String[] strArr2 = new String[length];
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            strArr2[i] = strArr[i2];
            i = i2;
        }
        return strArr2;
    }

    private static void scanPlayComb(int[] iArr, int i) {
        if (iArr.length <= 0) {
            return;
        }
        int i2 = 0;
        if (i < iArr.length) {
            int[][] iArr2 = mPlanComb;
            if (iArr2.length <= 0 || iArr2[mPlanCombCursor].length <= 0) {
                return;
            }
            while (i2 < iArr[i]) {
                mPlanComb[mPlanCombCursor][i] = i2;
                scanPlayComb(iArr, i + 1);
                i2++;
            }
            return;
        }
        int i3 = mPlanCombCursor + 1;
        mPlanCombCursor = i3;
        if (i3 < mPlanComb.length) {
            while (i2 < iArr.length - 1) {
                int[][] iArr3 = mPlanComb;
                int i4 = mPlanCombCursor;
                iArr3[i4][i2] = iArr3[i4 - 1][i2];
                i2++;
            }
        }
    }
}
