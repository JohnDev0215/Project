package com.engine.tbox.utils;

import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes.dex */
public class FileUtils {
    private static String PATH = "/vehicleconfig/tbox/";
    private static String VIN_FILE = "virtual_vin.txt";
    private static String NITZ_FILE = "nitz.txt";

    private static File getFile(String fileName) {
        File file = new File(PATH);
        if (!file.exists()) {
            boolean result = file.mkdirs();
            System.out.println("create the path:" + PATH + " result:" + result);
            if (!file.exists()) {
                System.out.println("PATH:" + PATH + " creat dir failed");
                return null;
            }
        }
        return new File(PATH + fileName);
    }

    private static String readStringFromFile(String fileName) {
        if (TextUtils.isEmpty(fileName)) {
            System.err.println("readStringFromFile fileName:" + fileName);
            return null;
        }
        File file = getFile(fileName);
        if (file == null) {
            return null;
        }
        if (!file.exists()) {
            System.out.println("file:" + fileName + " file is not exist");
            return null;
        }
        BufferedReader reader = null;
        try {
            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
                String result = reader.readLine();
                try {
                    reader.close();
                } catch (IOException exp) {
                    exp.printStackTrace();
                }
                return result;
            } catch (IOException exp2) {
                exp2.printStackTrace();
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException exp3) {
                        exp3.printStackTrace();
                    }
                }
                return null;
            }
        } catch (Throwable th) {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException exp4) {
                    exp4.printStackTrace();
                }
            }
            throw th;
        }
    }

    private static boolean writeStringToFile(String fileName, String str) {
        File file = getFile(fileName);
        if (file == null) {
            return false;
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException exp) {
                exp.printStackTrace();
                System.err.println("crate file " + fileName + " failed");
                return false;
            }
        }
        BufferedWriter writer = null;
        try {
            try {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
                writer.write(str);
                try {
                    writer.close();
                } catch (IOException exp2) {
                    exp2.printStackTrace();
                }
                return true;
            } catch (IOException exp3) {
                exp3.printStackTrace();
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException exp4) {
                        exp4.printStackTrace();
                    }
                }
                return false;
            }
        } catch (Throwable th) {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException exp5) {
                    exp5.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static boolean writeVirtualVin(String virtualVin) {
        return writeStringToFile(VIN_FILE, virtualVin);
    }

    public static String readVirtualVin() {
        return readStringFromFile(VIN_FILE);
    }

    public static boolean writeNitz(String nitz) {
        return writeStringToFile(NITZ_FILE, nitz);
    }

    public static String readNitz() {
        return readStringFromFile(NITZ_FILE);
    }
}
