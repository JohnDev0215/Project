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
        int i3 = 0;
        String str2 = "";
        int i4 = 0;
        int i5 = 0;
        boolean z3 = false;
        while (true) {
            int[][] iArr = mPlanComb;
            if (i4 >= iArr.length) {
                break;
            }
            int[] iArr2 = iArr[i4];
            int i6 = 1;
            while (i6 < strArr.length && i5 < str.length()) {
                String[] strArrSplit = strArr[i6].split("\\|");
                if (strArrSplit == null || strArrSplit.length <= 1) {
                    if (!strArr[i6].toLowerCase().equals(String.valueOf(str.charAt(i5)))) {
                        if (z3) {
                            str2 = "";
                            i5 = 0;
                            z3 = false;
                            break;
                        }
                    } else {
                        str2 = str2 + strArr[i6];
                        i5++;
                        z3 = true;
                    }
                    if (i6 != strArr.length - 1 && i5 < str.length()) {
                        str2 = "";
                        i5 = 0;
                        z3 = false;
                    }
                    i6++;
                    i3 = 0;
                } else {
                    String str3 = strArrSplit[i3];
                    String str4 = strArrSplit[iArr2[i6 - 1] + 1];
                    int i7 = i3;
                    int i8 = i7;
                    while (true) {
                        if (i7 < str4.length() && i5 < str.length()) {
                            if (i7 != 0) {
                                if (str4.charAt(i7) != str.charAt(i5)) {
                                    break;
                                }
                                i5++;
                                i7++;
                                i3 = 0;
                            } else {
                                if (str4.charAt(i7) != str.charAt(i5)) {
                                    i8 = i3;
                                    break;
                                }
                                str2 = str2 + str3;
                                i5++;
                                z3 = true;
                                i8 = 1;
                                i7++;
                                i3 = 0;
                            }
                        } else {
                            break;
                        }
                    }
                    if (i8 == 0 && z3) {
                        str2 = "";
                        i5 = 0;
                        z3 = false;
                        break;
                    }
                    if (i6 != strArr.length - 1) {
                    }
                    i6++;
                    i3 = 0;
                }
            }
            if (z3 && i5 >= str.length()) {
                break;
            }
            i4++;
            i3 = 0;
        }
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        return str2;
    }

    private static String getMatchT9Result(String str, String[] strArr) {
        int i3 = 0;
        String str2 = "";
        int i4 = 0;
        int i5 = 0;
        boolean z3 = false;
        while (true) {
            int[][] iArr = mPlanComb;
            if (i4 >= iArr.length) {
                break;
            }
            int[] iArr2 = iArr[i4];
            int i6 = i3;
            while (true) {
                if (i6 >= strArr.length || i5 >= str.length()) {
                    break;
                }
                String[] strArrSplit = strArr[i6].split("\\|");
                String str3 = strArrSplit[i3];
                if (iArr2.length >= i6 && strArrSplit.length >= iArr2[i6] + 1) {
                    String str4 = strArrSplit[iArr2[i6] + 1];
                    int i7 = i3;
                    int i8 = i7;
                    while (true) {
                        if (i7 < str4.length() && i5 < str.length()) {
                            if (i7 != 0) {
                                if (str4.charAt(i7) != str.charAt(i5)) {
                                    break;
                                }
                                i5++;
                                i7++;
                                i3 = 0;
                            } else {
                                if (str4.charAt(i7) != str.charAt(i5)) {
                                    i8 = i3;
                                    break;
                                }
                                str2 = str2 + str3;
                                i5++;
                                z3 = true;
                                i8 = 1;
                                i7++;
                                i3 = 0;
                            }
                        } else {
                            break;
                        }
                    }
                    if (i8 == 0 && z3) {
                        str2 = "";
                        i5 = 0;
                        z3 = false;
                        break;
                    }
                    if (i6 == strArr.length - 1 && i5 < str.length()) {
                        str2 = "";
                        i5 = 0;
                        z3 = false;
                    }
                }
                i6++;
                i3 = 0;
            }
            if (z3 && i5 >= str.length()) {
                break;
            }
            i4++;
            i3 = 0;
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
        int i3 = 1;
        for (int i4 = 1; i4 < strArr.length; i4++) {
            String[] strArrSplit = strArr[i4].split("\\|");
            if (strArrSplit == null || strArrSplit.length <= 1) {
                iArr[i4 - 1] = 1;
            } else {
                int i5 = i4 - 1;
                iArr[i5] = strArrSplit.length - 1;
                i3 *= iArr[i5];
            }
        }
        if (i3 > 100) {
            mPlanComb = (int[][]) Array.newInstance((Class<?>) int.class, 1, strArr.length - 1);
        } else {
            preparePlayComb(i3, iArr);
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
        int i3 = 1;
        for (int i4 = 0; i4 < strArrRemoveHead.length; i4++) {
            iArr[i4] = strArrRemoveHead[i4].split("\\|").length - 1;
            i3 *= iArr[i4];
        }
        if (i3 > 100) {
            mPlanComb = (int[][]) Array.newInstance((Class<?>) int.class, 1, strArrRemoveHead.length);
        } else {
            preparePlayComb(i3, iArr);
            if (mPlanComb == null) {
                BydLogHelper.logE(TAG, "matchT9", "no plan comb!");
                return null;
            }
        }
        return getMatchT9Result(str, strArrRemoveHead);
    }

    private static void preparePlayComb(int i3, int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            mPlanComb = null;
            mPlanCombCursor = 0;
        } else {
            mPlanComb = (int[][]) Array.newInstance((Class<?>) int.class, i3, iArr.length);
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
        int i3 = 0;
        while (i3 < length) {
            int i4 = i3 + 1;
            strArr2[i3] = strArr[i4];
            i3 = i4;
        }
        return strArr2;
    }

    private static void scanPlayComb(int[] iArr, int i3) {
        if (iArr.length <= 0) {
            return;
        }
        int i4 = 0;
        if (i3 < iArr.length) {
            int[][] iArr2 = mPlanComb;
            if (iArr2.length <= 0 || iArr2[mPlanCombCursor].length <= 0) {
                return;
            }
            while (i4 < iArr[i3]) {
                mPlanComb[mPlanCombCursor][i3] = i4;
                scanPlayComb(iArr, i3 + 1);
                i4++;
            }
            return;
        }
        int i5 = mPlanCombCursor + 1;
        mPlanCombCursor = i5;
        if (i5 < mPlanComb.length) {
            while (i4 < iArr.length - 1) {
                int[][] iArr3 = mPlanComb;
                int i6 = mPlanCombCursor;
                iArr3[i6][i4] = iArr3[i6 - 1][i4];
                i4++;
            }
        }
    }
}
