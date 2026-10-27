package com.byd.music.view.homeMusic.lrc;

import android.animation.ValueAnimator;
import android.os.Build;
import android.provider.SettingsStringUtil;
import android.text.TextUtils;
import com.google.android.material.timepicker.TimeModel;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes5.dex */
class LrcUtils {
    private static final Pattern PATTERN_LINE = Pattern.compile("((\\[\\d\\d:\\d\\d\\.\\d{2,3}\\])+)(.+)");
    private static final Pattern PATTERN_TIME = Pattern.compile("\\[(\\d\\d):(\\d\\d)\\.(\\d{2,3})\\]");

    LrcUtils() {
    }

    static List<LrcEntry> parseLrc(File[] fileArr) {
        if (fileArr == null || fileArr.length != 2 || fileArr[0] == null) {
            return null;
        }
        File file = fileArr[0];
        File file2 = fileArr[1];
        List<LrcEntry> lrc = parseLrc(file);
        List<LrcEntry> lrc2 = parseLrc(file2);
        if (lrc != null && lrc2 != null) {
            for (LrcEntry lrcEntry : lrc) {
                for (LrcEntry lrcEntry2 : lrc2) {
                    if (lrcEntry.getTime() == lrcEntry2.getTime()) {
                        lrcEntry.setSecondText(lrcEntry2.getText());
                    }
                }
            }
        }
        return lrc;
    }

    private static List<LrcEntry> parseLrc(File file) {
        BufferedReader bufferedReader;
        if (file == null || !file.exists()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            List<LrcEntry> line2 = parseLine(line);
            if (line2 != null && !line2.isEmpty()) {
                arrayList.addAll(line2);
            }
            Collections.sort(arrayList);
            return arrayList;
        }
        bufferedReader.close();
        Collections.sort(arrayList);
        return arrayList;
    }

    static List<LrcEntry> parseLrc(String[] strArr) {
        if (strArr == null || strArr.length != 2 || TextUtils.isEmpty(strArr[0])) {
            return null;
        }
        String str = strArr[0];
        String str2 = strArr[1];
        List<LrcEntry> lrc = parseLrc(str);
        List<LrcEntry> lrc2 = parseLrc(str2);
        if (lrc != null && lrc2 != null) {
            for (LrcEntry lrcEntry : lrc) {
                for (LrcEntry lrcEntry2 : lrc2) {
                    if (lrcEntry.getTime() == lrcEntry2.getTime()) {
                        lrcEntry.setSecondText(lrcEntry2.getText());
                    }
                }
            }
        }
        return lrc;
    }

    private static List<LrcEntry> parseLrc(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.startsWith("\ufeff")) {
            str = str.replace("\ufeff", "");
        }
        ArrayList arrayList = new ArrayList();
        for (String str2 : str.split("\\n")) {
            List<LrcEntry> line = parseLine(str2);
            if (line != null && !line.isEmpty()) {
                arrayList.addAll(line);
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    static String getContentFromNetwork(String str, String str2) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(10000);
            if (httpURLConnection.getResponseCode() != 200) {
                return null;
            }
            InputStream inputStream = httpURLConnection.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int i = inputStream.read(bArr);
                if (i != -1) {
                    byteArrayOutputStream.write(bArr, 0, i);
                } else {
                    inputStream.close();
                    byteArrayOutputStream.close();
                    return byteArrayOutputStream.toString(str2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static List<LrcEntry> parseLine(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Matcher matcher = PATTERN_LINE.matcher(str.trim());
        if (!matcher.matches()) {
            return null;
        }
        String strGroup = matcher.group(1);
        String strGroup2 = matcher.group(3);
        ArrayList arrayList = new ArrayList();
        Matcher matcher2 = PATTERN_TIME.matcher(strGroup);
        while (matcher2.find()) {
            long j = Long.parseLong(matcher2.group(1));
            long j2 = Long.parseLong(matcher2.group(2));
            String strGroup3 = matcher2.group(3);
            long j3 = Long.parseLong(strGroup3);
            if (strGroup3.length() == 2) {
                j3 *= 10;
            }
            arrayList.add(new LrcEntry((j * 60000) + (j2 * 1000) + j3, strGroup2));
        }
        return arrayList;
    }

    static String formatTime(long j) {
        return String.format(Locale.getDefault(), TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf((int) (j / 60000))) + SettingsStringUtil.DELIMITER + String.format(Locale.getDefault(), TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf((int) ((j / 1000) % 60)));
    }

    static void resetDurationScale() {
        try {
            if (Build.VERSION.SDK_INT <= 28) {
                Field declaredField = ValueAnimator.class.getDeclaredField("sDurationScale");
                declaredField.setAccessible(true);
                declaredField.setFloat(null, 1.0f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
