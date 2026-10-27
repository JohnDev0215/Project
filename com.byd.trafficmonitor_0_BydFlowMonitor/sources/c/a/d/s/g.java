package c.a.d.s;

import com.bydux.api.common.tools.log.BydLogHelper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/* JADX INFO: compiled from: FileUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class g {
    public static String a(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        if (!new File(str).exists()) {
            return stringBuffer.toString();
        }
        try {
            FileReader fileReader = new FileReader(str);
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
            } finally {
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return stringBuffer.toString();
    }

    public static void b(String str, String str2, boolean z) {
        try {
            File file = new File(str);
            File file2 = new File(file.getParent());
            if (file2.isFile()) {
                BydLogHelper.logE("traffic_monitor", "writeFile: " + file2.getAbsolutePath() + " is Not a directory,delete.");
                file2.delete();
            }
            if (!file2.exists()) {
                file2.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, z);
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
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }
}
