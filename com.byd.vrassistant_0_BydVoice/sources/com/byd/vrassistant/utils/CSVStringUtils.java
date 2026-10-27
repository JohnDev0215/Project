package com.byd.vrassistant.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CSVStringUtils {
    public static void writeCSVFile(ArrayList<List<String>> arrayList, File file, boolean z) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
        StringBuilder sb = new StringBuilder();
        for (List<String> list : arrayList) {
            for (int i = 0; i < list.size(); i++) {
                if (i != list.size() - 1) {
                    sb.append((Object) list.get(i)).append(",");
                } else {
                    sb.append((Object) list.get(i)).append(System.getProperty("line.separator"));
                }
            }
        }
        bufferedWriter.write(sb.toString());
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    public static String transUnitBySize(long j) {
        String str;
        String str2 = "0";
        if ((j / 1024) / 1024 > 0) {
            str2 = new DecimalFormat("0").format(new BigDecimal(j).divide(new BigDecimal(1048576)));
            str = "MB";
        } else {
            str = "";
        }
        return str2 + str;
    }
}
