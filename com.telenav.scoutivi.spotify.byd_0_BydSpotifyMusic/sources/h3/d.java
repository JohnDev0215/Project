package h3;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/* JADX INFO: compiled from: DiskLogStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public class d implements g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Handler f6622a;

    /* JADX INFO: compiled from: DiskLogStrategy.java */
    static class a extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f6623a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f6624b;

        a(Looper looper, String str, int i7) {
            super((Looper) m.a(looper));
            this.f6623a = (String) m.a(str);
            this.f6624b = i7;
        }

        private File a(String str, String str2) {
            File file;
            m.a(str);
            m.a(str2);
            File file2 = new File(str);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            File file3 = null;
            File file4 = new File(file2, String.format("%s_%s.csv", str2, 0));
            int i7 = 0;
            while (true) {
                File file5 = file4;
                file = file3;
                file3 = file5;
                if (!file3.exists()) {
                    break;
                }
                i7++;
                file4 = new File(file2, String.format("%s_%s.csv", str2, Integer.valueOf(i7)));
            }
            return (file == null || file.length() >= ((long) this.f6624b)) ? file3 : file;
        }

        private void b(FileWriter fileWriter, String str) {
            m.a(fileWriter);
            m.a(str);
            fileWriter.append((CharSequence) str);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            FileWriter fileWriter;
            String str = (String) message.obj;
            FileWriter fileWriter2 = null;
            try {
                fileWriter = new FileWriter(a(this.f6623a, "logs"), true);
            } catch (IOException unused) {
            }
            try {
                b(fileWriter, str);
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException unused2) {
                fileWriter2 = fileWriter;
                if (fileWriter2 != null) {
                    try {
                        fileWriter2.flush();
                        fileWriter2.close();
                    } catch (IOException unused3) {
                    }
                }
            }
        }
    }

    public d(Handler handler) {
        this.f6622a = (Handler) m.a(handler);
    }

    @Override // h3.g
    public void a(int i7, String str, String str2) {
        m.a(str2);
        Handler handler = this.f6622a;
        handler.sendMessage(handler.obtainMessage(i7, str2));
    }
}
