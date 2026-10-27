package g5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/* JADX INFO: compiled from: FileUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class f {
    public static String a(String str) {
        FileReader fileReader;
        if (!new File(str).exists()) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            fileReader = new FileReader(str);
        } catch (Exception e8) {
            e8.printStackTrace();
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (true) {
                try {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    stringBuffer.append(line);
                } finally {
                }
            }
            bufferedReader.close();
            fileReader.close();
            return stringBuffer.toString();
        } finally {
        }
    }

    public static void b(String str, String str2, boolean z7) {
        try {
            File file = new File(str);
            File file2 = new File(file.getParent());
            if (!file2.exists()) {
                file2.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, z7);
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                try {
                    bufferedWriter.write(str2);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    fileWriter.close();
                } finally {
                }
            } finally {
            }
        } catch (IOException e8) {
            e8.printStackTrace();
        }
    }
}
