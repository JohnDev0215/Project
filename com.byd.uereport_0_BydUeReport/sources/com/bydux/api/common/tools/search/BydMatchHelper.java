package com.bydux.api.common.tools.search;

import android.text.TextUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes2.dex */
public class BydMatchHelper {
    private static final int MAX_PLAN = 100;
    private static final String TAG = "BydMatchHelper";
    private static int[][] mPlanComb;
    private static int mPlanCombCursor;

    private BydMatchHelper() {
    }

    private static String getMatchPyResult(String str, String[] strArr) {
        int i8 = 0;
        String str2 = "";
        int i9 = 0;
        int i10 = 0;
        boolean z7 = false;
        while (true) {
            int[][] iArr = mPlanComb;
            if (i9 >= iArr.length) {
                break;
            }
            int[] iArr2 = iArr[i9];
            int i11 = 1;
            while (i11 < strArr.length && i10 < str.length()) {
                String[] strArrSplit = strArr[i11].split("\\|");
                if (strArrSplit == null || strArrSplit.length <= 1) {
                    if (!strArr[i11].toLowerCase().equals(String.valueOf(str.charAt(i10)))) {
                        if (z7) {
                            str2 = "";
                            i10 = 0;
                            z7 = false;
                            break;
                        }
                    } else {
                        str2 = str2 + strArr[i11];
                        i10++;
                        z7 = true;
                    }
                    if (i11 != strArr.length - 1 && i10 < str.length()) {
                        str2 = "";
                        i10 = 0;
                        z7 = false;
                    }
                    i11++;
                    i8 = 0;
                } else {
                    String str3 = strArrSplit[i8];
                    String str4 = strArrSplit[iArr2[i11 - 1] + 1];
                    int i12 = i8;
                    int i13 = i12;
                    while (true) {
                        if (i12 < str4.length() && i10 < str.length()) {
                            if (i12 != 0) {
                                if (str4.charAt(i12) != str.charAt(i10)) {
                                    break;
                                }
                                i10++;
                                i12++;
                                i8 = 0;
                            } else {
                                if (str4.charAt(i12) != str.charAt(i10)) {
                                    i13 = i8;
                                    break;
                                }
                                str2 = str2 + str3;
                                i10++;
                                z7 = true;
                                i13 = 1;
                                i12++;
                                i8 = 0;
                            }
                        } else {
                            break;
                        }
                    }
                    if (i13 == 0 && z7) {
                        str2 = "";
                        i10 = 0;
                        z7 = false;
                        break;
                    }
                    if (i11 != strArr.length - 1) {
                    }
                    i11++;
                    i8 = 0;
                }
            }
            if (z7 && i10 >= str.length()) {
                break;
            }
            i9++;
            i8 = 0;
        }
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        return str2;
    }

    private static String getMatchT9Result(String str, String[] strArr) {
        int i8 = 0;
        String str2 = "";
        int i9 = 0;
        int i10 = 0;
        boolean z7 = false;
        while (true) {
            int[][] iArr = mPlanComb;
            if (i9 >= iArr.length) {
                break;
            }
            int[] iArr2 = iArr[i9];
            int i11 = i8;
            while (true) {
                if (i11 >= strArr.length || i10 >= str.length()) {
                    break;
                }
                String[] strArrSplit = strArr[i11].split("\\|");
                String str3 = strArrSplit[i8];
                if (iArr2.length >= i11 && strArrSplit.length >= iArr2[i11] + 1) {
                    String str4 = strArrSplit[iArr2[i11] + 1];
                    int i12 = i8;
                    int i13 = i12;
                    while (true) {
                        if (i12 < str4.length() && i10 < str.length()) {
                            if (i12 != 0) {
                                if (str4.charAt(i12) != str.charAt(i10)) {
                                    break;
                                }
                                i10++;
                                i12++;
                                i8 = 0;
                            } else {
                                if (str4.charAt(i12) != str.charAt(i10)) {
                                    i13 = i8;
                                    break;
                                }
                                str2 = str2 + str3;
                                i10++;
                                z7 = true;
                                i13 = 1;
                                i12++;
                                i8 = 0;
                            }
                        } else {
                            break;
                        }
                    }
                    if (i13 == 0 && z7) {
                        str2 = "";
                        i10 = 0;
                        z7 = false;
                        break;
                    }
                    if (i11 == strArr.length - 1 && i10 < str.length()) {
                        str2 = "";
                        i10 = 0;
                        z7 = false;
                    }
                }
                i11++;
                i8 = 0;
            }
            if (z7 && i10 >= str.length()) {
                break;
            }
            i9++;
            i8 = 0;
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
        int i8 = 1;
        for (int i9 = 1; i9 < strArr.length; i9++) {
            String[] strArrSplit = strArr[i9].split("\\|");
            if (strArrSplit == null || strArrSplit.length <= 1) {
                iArr[i9 - 1] = 1;
            } else {
                int i10 = i9 - 1;
                iArr[i10] = strArrSplit.length - 1;
                i8 *= iArr[i10];
            }
        }
        if (i8 > 100) {
            mPlanComb = (int[][]) Array.newInstance((Class<?>) int.class, 1, strArr.length - 1);
        } else {
            preparePlayComb(i8, iArr);
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
        int i8 = 1;
        for (int i9 = 0; i9 < strArrRemoveHead.length; i9++) {
            iArr[i9] = strArrRemoveHead[i9].split("\\|").length - 1;
            i8 *= iArr[i9];
        }
        if (i8 > 100) {
            mPlanComb = (int[][]) Array.newInstance((Class<?>) int.class, 1, strArrRemoveHead.length);
        } else {
            preparePlayComb(i8, iArr);
            if (mPlanComb == null) {
                BydLogHelper.logE(TAG, "matchT9", "no plan comb!");
                return null;
            }
        }
        return getMatchT9Result(str, strArrRemoveHead);
    }

    private static void preparePlayComb(int i8, int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            mPlanComb = null;
            mPlanCombCursor = 0;
        } else {
            mPlanComb = (int[][]) Array.newInstance((Class<?>) int.class, i8, iArr.length);
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
        int i8 = 0;
        while (i8 < length) {
            int i9 = i8 + 1;
            strArr2[i8] = strArr[i9];
            i8 = i9;
        }
        return strArr2;
    }

    private static void scanPlayComb(int[] iArr, int i8) {
        if (iArr.length <= 0) {
            return;
        }
        int i9 = 0;
        if (i8 < iArr.length) {
            int[][] iArr2 = mPlanComb;
            if (iArr2.length <= 0 || iArr2[mPlanCombCursor].length <= 0) {
                return;
            }
            while (i9 < iArr[i8]) {
                mPlanComb[mPlanCombCursor][i8] = i9;
                scanPlayComb(iArr, i8 + 1);
                i9++;
            }
            return;
        }
        int i10 = mPlanCombCursor + 1;
        mPlanCombCursor = i10;
        if (i10 < mPlanComb.length) {
            while (i9 < iArr.length - 1) {
                int[][] iArr3 = mPlanComb;
                int i11 = mPlanCombCursor;
                iArr3[i11][i9] = iArr3[i11 - 1][i9];
                i9++;
            }
        }
    }
}
