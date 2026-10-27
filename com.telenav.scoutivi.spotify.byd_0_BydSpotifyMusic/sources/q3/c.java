package q3;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import h3.g;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

/* JADX INFO: compiled from: ThreadLogCatStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public class c implements g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f9155a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Handler f9156b;

    /* JADX INFO: compiled from: ThreadLogCatStrategy.java */
    static class a extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f9157a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f9158b;

        a(Looper looper, String str, int i7) {
            super((Looper) c.b(looper));
            this.f9157a = (String) c.b(str);
            this.f9158b = i7;
        }

        private File a(String str, String str2) {
            File file;
            c.b(str);
            c.b(str2);
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
            return (file == null || file.length() >= ((long) this.f9158b)) ? file3 : file;
        }

        private void b(FileWriter fileWriter, String str) {
            c.b(fileWriter);
            c.b(str);
            fileWriter.append((CharSequence) str);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            FileWriter fileWriter;
            String str = (String) message.obj;
            FileWriter fileWriter2 = null;
            try {
                fileWriter = new FileWriter(a(this.f9157a, "logs"), true);
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

    public c(int i7, String str) {
        this.f9155a = i7;
        HandlerThread handlerThread = new HandlerThread("AndroidFileLogger." + str);
        handlerThread.start();
        this.f9156b = new a(handlerThread.getLooper(), str, 512000);
    }

    static <T> T b(T t6) {
        Objects.requireNonNull(t6);
        return t6;
    }

    @Override // h3.g
    public void a(int i7, String str, String str2) {
        if (i7 < this.f9155a) {
            return;
        }
        b(str2);
        Handler handler = this.f9156b;
        handler.sendMessage(handler.obtainMessage(i7, Process.myPid() + "-" + Process.myTid() + " " + str2));
    }
}
