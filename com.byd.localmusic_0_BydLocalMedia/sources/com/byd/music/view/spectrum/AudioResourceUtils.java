package com.byd.music.view.spectrum;

import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/* JADX INFO: loaded from: classes5.dex */
public class AudioResourceUtils {
    private AudioResourceUtils() {
    }

    public static String sanitizeResource(String str) {
        if (str.toLowerCase().endsWith("pls")) {
            return parsePLS(str);
        }
        if (str.toLowerCase().endsWith("m3u")) {
            return parseM3U(str);
        }
        if (str.toLowerCase().endsWith("asx")) {
            return parseASX(str);
        }
        return str.toLowerCase().endsWith("xspf") ? parseXSPF(str) : str;
    }

    private static String parseXSPF(String str) {
        try {
            for (String str2 : readTextFromUrl(new URL(str)).split(BydAdbHelper.COMMAND_LINE_END)) {
                if (str2.toLowerCase().contains("href")) {
                    return str2.replaceAll("(?i)<location>(.*)</location>.*", "$1");
                }
            }
            return "";
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String parseASX(String str) {
        try {
            for (String str2 : readTextFromUrl(new URL(str)).split(BydAdbHelper.COMMAND_LINE_END)) {
                if (str2.toLowerCase().contains("href")) {
                    return str2.replaceAll("(?i).*href=\"(.*)\".*", "$1");
                }
            }
            return "";
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String parsePLS(String str) {
        try {
            for (String str2 : readTextFromUrl(new URL(str)).split(BydAdbHelper.COMMAND_LINE_END)) {
                if (str2.startsWith("File1=")) {
                    return str2.replace("File1=", "").trim();
                }
            }
            return "";
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String parseM3U(String str) {
        try {
            for (String str2 : readTextFromUrl(new URL(str)).split(BydAdbHelper.COMMAND_LINE_END)) {
                if (!str2.trim().isEmpty() && !str2.trim().startsWith("#")) {
                    return str2.trim();
                }
            }
            return "";
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String readTextFromUrl(URL url) {
        BufferedReader bufferedReader;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            stringBuffer.append(line);
            stringBuffer.append(BydAdbHelper.COMMAND_LINE_END);
            return stringBuffer.toString();
        }
        bufferedReader.close();
        return stringBuffer.toString();
    }
}
