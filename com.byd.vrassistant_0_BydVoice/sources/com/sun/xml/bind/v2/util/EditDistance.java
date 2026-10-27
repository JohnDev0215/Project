package com.sun.xml.bind.v2.util;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes3.dex */
public class EditDistance {
    private static final WeakHashMap<AbstractMap.SimpleEntry<String, String>, Integer> CACHE = new WeakHashMap<>();
    private final String a;
    private final String b;
    private int[] back;
    private int[] cost;

    public static int editDistance(String str, String str2) {
        AbstractMap.SimpleEntry<String, String> simpleEntry = new AbstractMap.SimpleEntry<>(str, str2);
        WeakHashMap<AbstractMap.SimpleEntry<String, String>, Integer> weakHashMap = CACHE;
        Integer numValueOf = weakHashMap.containsKey(simpleEntry) ? weakHashMap.get(simpleEntry) : null;
        if (numValueOf == null) {
            numValueOf = Integer.valueOf(new EditDistance(str, str2).calc());
            weakHashMap.put(simpleEntry, numValueOf);
        }
        return numValueOf.intValue();
    }

    public static String findNearest(String str, String[] strArr) {
        return findNearest(str, Arrays.asList(strArr));
    }

    public static String findNearest(String str, Collection<String> collection) {
        int i = Integer.MAX_VALUE;
        String str2 = null;
        for (String str3 : collection) {
            int iEditDistance = editDistance(str, str3);
            if (i > iEditDistance) {
                str2 = str3;
                i = iEditDistance;
            }
        }
        return str2;
    }

    private EditDistance(String str, String str2) {
        this.a = str;
        this.b = str2;
        this.cost = new int[str.length() + 1];
        this.back = new int[str.length() + 1];
        for (int i = 0; i <= str.length(); i++) {
            this.cost[i] = i;
        }
    }

    private void flip() {
        int[] iArr = this.cost;
        this.cost = this.back;
        this.back = iArr;
    }

    private int min(int i, int i2, int i3) {
        return Math.min(i, Math.min(i2, i3));
    }

    private int calc() {
        int i = 0;
        while (i < this.b.length()) {
            flip();
            int i2 = i + 1;
            this.cost[0] = i2;
            int i3 = 0;
            while (i3 < this.a.length()) {
                int i4 = this.a.charAt(i3) == this.b.charAt(i) ? 0 : 1;
                int[] iArr = this.cost;
                int i5 = i3 + 1;
                int[] iArr2 = this.back;
                iArr[i5] = min(iArr2[i3] + i4, iArr[i3] + 1, iArr2[i5] + 1);
                i3 = i5;
            }
            i = i2;
        }
        return this.cost[this.a.length()];
    }
}
