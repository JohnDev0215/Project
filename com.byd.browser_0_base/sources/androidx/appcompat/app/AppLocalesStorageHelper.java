package androidx.appcompat.app;

import android.content.Context;
import android.util.Log;
import android.util.Xml;
import androidx.appcompat.app.AppLocalesStorageHelper;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.concurrent.Executor;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes.dex */
public class AppLocalesStorageHelper {

    public static class SerialExecutor implements Executor {
        public final Object e = new Object();
        public final ArrayDeque f = new ArrayDeque();
        public final Executor g;
        public Runnable h;

        public SerialExecutor(Executor executor) {
            this.g = executor;
        }

        public final void a() {
            synchronized (this.e) {
                Runnable runnable = (Runnable) this.f.poll();
                this.h = runnable;
                if (runnable != null) {
                    this.g.execute(runnable);
                }
            }
        }

        @Override // java.util.concurrent.Executor
        public final void execute(final Runnable runnable) {
            synchronized (this.e) {
                this.f.add(new Runnable() { // from class: androidx.appcompat.app.c
                    @Override // java.lang.Runnable
                    public final void run() {
                        Runnable runnable2 = runnable;
                        AppLocalesStorageHelper.SerialExecutor serialExecutor = this.e;
                        serialExecutor.getClass();
                        try {
                            runnable2.run();
                        } finally {
                            serialExecutor.a();
                        }
                    }
                });
                if (this.h == null) {
                    a();
                }
            }
        }
    }

    public static class ThreadPerTaskExecutor implements Executor {
        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            new Thread(runnable).start();
        }
    }

    private AppLocalesStorageHelper() {
    }

    public static void a(Context context, String str) {
        if (str.equals("")) {
            context.deleteFile("androidx.appcompat.app.AppCompatDelegate.application_locales_record_file");
            return;
        }
        try {
            FileOutputStream fileOutputStreamOpenFileOutput = context.openFileOutput("androidx.appcompat.app.AppCompatDelegate.application_locales_record_file", 0);
            XmlSerializer xmlSerializerNewSerializer = Xml.newSerializer();
            try {
                try {
                    xmlSerializerNewSerializer.setOutput(fileOutputStreamOpenFileOutput, null);
                    xmlSerializerNewSerializer.startDocument("UTF-8", Boolean.TRUE);
                    xmlSerializerNewSerializer.startTag(null, "locales");
                    xmlSerializerNewSerializer.attribute(null, "application_locales", str);
                    xmlSerializerNewSerializer.endTag(null, "locales");
                    xmlSerializerNewSerializer.endDocument();
                    if (fileOutputStreamOpenFileOutput == null) {
                        return;
                    }
                } catch (Exception e) {
                    Log.w("AppLocalesStorageHelper", "Storing App Locales : Failed to persist app-locales in storage ", e);
                    if (fileOutputStreamOpenFileOutput == null) {
                        return;
                    }
                }
                try {
                    fileOutputStreamOpenFileOutput.close();
                } catch (IOException unused) {
                }
            } catch (Throwable th) {
                if (fileOutputStreamOpenFileOutput != null) {
                    try {
                        fileOutputStreamOpenFileOutput.close();
                    } catch (IOException unused2) {
                    }
                }
                throw th;
            }
        } catch (FileNotFoundException unused3) {
            Log.w("AppLocalesStorageHelper", String.format("Storing App Locales : FileNotFoundException: Cannot open file %s for writing ", "androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0037, code lost:
    
        r1 = r3.getAttributeValue(null, "application_locales");
     */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0040 A[EXC_TOP_SPLITTER, PHI: r1
  0x0040: PHI (r1v2 java.lang.String) = (r1v0 java.lang.String), (r1v4 java.lang.String) binds: [B:24:0x004d, B:18:0x003e] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String b(android.content.Context r8) {
        /*
            java.lang.String r0 = "androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            java.lang.String r1 = ""
            java.io.FileInputStream r2 = r8.openFileInput(r0)     // Catch: java.io.FileNotFoundException -> L61
            org.xmlpull.v1.XmlPullParser r3 = android.util.Xml.newPullParser()     // Catch: java.lang.Throwable -> L44 java.lang.Throwable -> L46
            java.lang.String r4 = "UTF-8"
            r3.setInput(r2, r4)     // Catch: java.lang.Throwable -> L44 java.lang.Throwable -> L46
            int r4 = r3.getDepth()     // Catch: java.lang.Throwable -> L44 java.lang.Throwable -> L46
        L15:
            int r5 = r3.next()     // Catch: java.lang.Throwable -> L44 java.lang.Throwable -> L46
            r6 = 1
            if (r5 == r6) goto L3e
            r6 = 3
            if (r5 != r6) goto L25
            int r7 = r3.getDepth()     // Catch: java.lang.Throwable -> L44 java.lang.Throwable -> L46
            if (r7 <= r4) goto L3e
        L25:
            if (r5 == r6) goto L15
            r6 = 4
            if (r5 != r6) goto L2b
            goto L15
        L2b:
            java.lang.String r5 = r3.getName()     // Catch: java.lang.Throwable -> L44 java.lang.Throwable -> L46
            java.lang.String r6 = "locales"
            boolean r5 = r5.equals(r6)     // Catch: java.lang.Throwable -> L44 java.lang.Throwable -> L46
            if (r5 == 0) goto L15
            java.lang.String r4 = "application_locales"
            r5 = 0
            java.lang.String r1 = r3.getAttributeValue(r5, r4)     // Catch: java.lang.Throwable -> L44 java.lang.Throwable -> L46
        L3e:
            if (r2 == 0) goto L50
        L40:
            r2.close()     // Catch: java.io.IOException -> L50
            goto L50
        L44:
            r8 = move-exception
            goto L5b
        L46:
            java.lang.String r3 = "AppLocalesStorageHelper"
            java.lang.String r4 = "Reading app Locales : Unable to parse through file :androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            android.util.Log.w(r3, r4)     // Catch: java.lang.Throwable -> L44
            if (r2 == 0) goto L50
            goto L40
        L50:
            boolean r2 = r1.isEmpty()
            if (r2 != 0) goto L57
            goto L5a
        L57:
            r8.deleteFile(r0)
        L5a:
            return r1
        L5b:
            if (r2 == 0) goto L60
            r2.close()     // Catch: java.io.IOException -> L60
        L60:
            throw r8
        L61:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppLocalesStorageHelper.b(android.content.Context):java.lang.String");
    }
}
