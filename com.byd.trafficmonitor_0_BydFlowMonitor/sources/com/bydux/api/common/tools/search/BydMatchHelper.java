package com.bydux.api.common.tools.search;

import android.text.TextUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes.dex */
public class BydMatchHelper {
    private static final int MAX_PLAN = 100;
    private static final String TAG = "BydMatchHelper";
    private static int[][] mPlanComb;
    private static int mPlanCombCursor;

    private BydMatchHelper() {
    }

    private static String getMatchPyResult(String str, String[] strArr) {
        int i2 = 0;
        String str2 = "";
        int i3 = 0;
        int i4 = 0;
        boolean z = false;
        while (true) {
            int[][] iArr = mPlanComb;
            if (i3 >= iArr.length) {
                break;
            }
            int[] iArr2 = iArr[i3];
            int i5 = 1;
            while (i5 < strArr.length && i4 < str.length()) {
                String[] strArrSplit = strArr[i5].split("\\|");
                if (strArrSplit == null || strArrSplit.length <= 1) {
                    if (!strArr[i5].toLowerCase().equals(String.valueOf(str.charAt(i4)))) {
                        if (z) {
                            str2 = "";
                            i4 = 0;
                            z = false;
                            break;
                        }
                    } else {
                        str2 = str2 + strArr[i5];
                        i4++;
                        z = true;
                    }
                    if (i5 != strArr.length - 1 && i4 < str.length()) {
                        str2 = "";
                        i4 = 0;
                        z = false;
                    }
                    i5++;
                    i2 = 0;
                } else {
                    String str3 = strArrSplit[i2];
                    String str4 = strArrSplit[iArr2[i5 - 1] + 1];
                    int i6 = i2;
                    int i7 = i6;
                    while (true) {
                        if (i6 < str4.length() && i4 < str.length()) {
                            if (i6 != 0) {
                                if (str4.charAt(i6) != str.charAt(i4)) {
                                    break;
                                }
                                i4++;
                                i6++;
                                i2 = 0;
                            } else {
                                if (str4.charAt(i6) != str.charAt(i4)) {
                                    i7 = i2;
                                    break;
                                }
                                str2 = str2 + str3;
                                i4++;
                                z = true;
                                i7 = 1;
                                i6++;
                                i2 = 0;
                            }
                        } else {
                            break;
                        }
                    }
                    if (i7 == 0 && z) {
                        str2 = "";
                        i4 = 0;
                        z = false;
                        break;
                    }
                    if (i5 != strArr.length - 1) {
                    }
                    i5++;
                    i2 = 0;
                }
            }
            if (z && i4 >= str.length()) {
                break;
            }
            i3++;
            i2 = 0;
        }
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        return str2;
    }

    private static String getMatchT9Result(String str, String[] strArr) {
        int i2 = 0;
        String str2 = "";
        int i3 = 0;
        int i4 = 0;
        boolean z = false;
        while (true) {
            int[][] iArr = mPlanComb;
            if (i3 >= iArr.length) {
                break;
            }
            int[] iArr2 = iArr[i3];
            int i5 = i2;
            while (true) {
                if (i5 >= strArr.length || i4 >= str.length()) {
                    break;
                }
                String[] strArrSplit = strArr[i5].split("\\|");
                String str3 = strArrSplit[i2];
                if (iArr2.length >= i5 && strArrSplit.length >= iArr2[i5] + 1) {
                    String str4 = strArrSplit[iArr2[i5] + 1];
                    int i6 = i2;
                    int i7 = i6;
                    while (true) {
                        if (i6 < str4.length() && i4 < str.length()) {
                            if (i6 != 0) {
                                if (str4.charAt(i6) != str.charAt(i4)) {
                                    break;
                                }
                                i4++;
                                i6++;
                                i2 = 0;
                            } else {
                                if (str4.charAt(i6) != str.charAt(i4)) {
                                    i7 = i2;
                                    break;
                                }
                                str2 = str2 + str3;
                                i4++;
                                z = true;
                                i7 = 1;
                                i6++;
                                i2 = 0;
                            }
                        } else {
                            break;
                        }
                    }
                    if (i7 == 0 && z) {
                        str2 = "";
                        i4 = 0;
                        z = false;
                        break;
                    }
                    if (i5 == strArr.length - 1 && i4 < str.length()) {
                        str2 = "";
                        i4 = 0;
                        z = false;
                    }
                }
                i5++;
                i2 = 0;
            }
            if (z && i4 >= str.length()) {
                break;
            }
            i3++;
            i2 = 0;
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
        int i2 = 1;
        for (int i3 = 1; i3 < strArr.length; i3++) {
            String[] strArrSplit = strArr[i3].split("\\|");
            if (strArrSplit == null || strArrSplit.length <= 1) {
                iArr[i3 - 1] = 1;
            } else {
                int i4 = i3 - 1;
                iArr[i4] = strArrSplit.length - 1;
                i2 *= iArr[i4];
            }
        }
        if (i2 > 100) {
            mPlanComb = (int[][]) Array.newInstance((Class<?>) int.class, 1, strArr.length - 1);
        } else {
            preparePlayComb(i2, iArr);
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
        int i2 = 1;
        for (int i3 = 0; i3 < strArrRemoveHead.length; i3++) {
            iArr[i3] = strArrRemoveHead[i3].split("\\|").length - 1;
            i2 *= iArr[i3];
        }
        if (i2 > 100) {
            mPlanComb = (int[][]) Array.newInstance((Class<?>) int.class, 1, strArrRemoveHead.length);
        } else {
            preparePlayComb(i2, iArr);
            if (mPlanComb == null) {
                BydLogHelper.logE(TAG, "matchT9", "no plan comb!");
                return null;
            }
        }
        return getMatchT9Result(str, strArrRemoveHead);
    }

    private static void preparePlayComb(int i2, int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            mPlanComb = null;
            mPlanCombCursor = 0;
        } else {
            mPlanComb = (int[][]) Array.newInstance((Class<?>) int.class, i2, iArr.length);
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
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2 + 1;
            strArr2[i2] = strArr[i3];
            i2 = i3;
        }
        return strArr2;
    }

    private static void scanPlayComb(int[] iArr, int i2) {
        if (iArr.length <= 0) {
            return;
        }
        int i3 = 0;
        if (i2 < iArr.length) {
            int[][] iArr2 = mPlanComb;
            if (iArr2.length <= 0 || iArr2[mPlanCombCursor].length <= 0) {
                return;
            }
            while (i3 < iArr[i2]) {
                mPlanComb[mPlanCombCursor][i2] = i3;
                scanPlayComb(iArr, i2 + 1);
                i3++;
            }
            return;
        }
        int i4 = mPlanCombCursor + 1;
        mPlanCombCursor = i4;
        if (i4 < mPlanComb.length) {
            while (i3 < iArr.length - 1) {
                int[][] iArr3 = mPlanComb;
                int i5 = mPlanCombCursor;
                iArr3[i5][i3] = iArr3[i5 - 1][i3];
                i3++;
            }
        }
    }
}
