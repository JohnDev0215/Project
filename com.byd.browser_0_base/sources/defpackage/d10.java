package defpackage;

import J.N;
import android.util.Log;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: CrashFileManager.java */
/* JADX INFO: loaded from: classes3.dex */
public final class d10 {
    public static final Pattern b = Pattern.compile("^[^.]+-([^-,]+?)\\.");
    public static final Pattern c = Pattern.compile("\\.dmp([0-9]*)\\z");
    public static final Pattern d = Pattern.compile("\\.(dmp|forced)([0-9]*)(\\.try([0-9]+))\\z");
    public static final Pattern e;
    public static final Pattern f;
    public static final a g;
    public final File a;

    /* JADX INFO: compiled from: CrashFileManager.java */
    public class a implements Comparator<File> {
        @Override // java.util.Comparator
        public final int compare(File file, File file2) {
            File file3 = file;
            File file4 = file2;
            return file3.lastModified() == file4.lastModified() ? file3.compareTo(file4) : file3.lastModified() < file4.lastModified() ? 1 : -1;
        }
    }

    /* JADX INFO: compiled from: CrashFileManager.java */
    public class b implements FilenameFilter {
        public final /* synthetic */ Pattern a;

        public b(Pattern pattern) {
            this.a = pattern;
        }

        @Override // java.io.FilenameFilter
        public final boolean accept(File file, String str) {
            return this.a.matcher(str).find();
        }
    }

    static {
        Pattern.compile("\\.(dmp)([0-9]*)(\\.try([0-9]+))\\z");
        e = Pattern.compile("\\.up([0-9]*)(\\.try([0-9]+))\\z");
        Pattern.compile("\\.forced([0-9]*)(\\.try([0-9]+))\\z");
        Pattern.compile("\\.skipped([0-9]*)(\\.try([0-9]+))\\z");
        f = Pattern.compile("\\.tmp\\z");
        g = new a();
    }

    public d10(File file) {
        if (file == null) {
            throw new NullPointerException("Specified context cannot be null.");
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(gq2.f(file.getAbsolutePath(), " is not a directory."));
        }
        this.a = file;
    }

    public static void c(File file) {
        if (file.delete()) {
            return;
        }
        Log.w("cr_CrashFileManager", "Unable to delete " + file.getAbsolutePath());
    }

    public static int h(String str) {
        int iLastIndexOf = str.lastIndexOf(".try");
        if (iLastIndexOf < 0) {
            return -1;
        }
        String strSubstring = str.substring(iLastIndexOf + 4);
        try {
            int iNextInt = new Scanner(strSubstring).useDelimiter("[^0-9]+").nextInt();
            if (strSubstring.indexOf(Integer.toString(iNextInt)) == 0) {
                return iNextInt;
            }
            return -1;
        } catch (NoSuchElementException unused) {
            return -1;
        }
    }

    public static void i(File file, String str) {
        if (file.renameTo(new File(file.getPath().replace(".dmp", str).replace(".forced", str)))) {
            return;
        }
        Log.w("cr_CrashFileManager", "Failed to rename " + file);
        if (file.delete()) {
            return;
        }
        Log.w("cr_CrashFileManager", "Failed to delete " + file);
    }

    public static String j(File file) {
        String strConcat;
        String path = file.getPath();
        int iH = h(path);
        if (iH >= 0) {
            int i = iH + 1;
            strConcat = path.replace(gq2.e(".try", iH), ".try" + i);
        } else {
            strConcat = path.concat(".try1");
        }
        if (file.renameTo(new File(strConcat))) {
            return strConcat;
        }
        return null;
    }

    public static void k(File file) {
        file.renameTo(new File(gq2.f(file.getPath(), ".try0")));
    }

    public final void a() {
        for (File file : g(e)) {
            c(file);
        }
        for (File file2 : g(f)) {
            c(file2);
        }
        int i = 0;
        for (File file3 : g(null)) {
            if (!file3.getName().equals("uploads.log")) {
                if (TimeUnit.DAYS.convert(new Date().getTime() - file3.lastModified(), TimeUnit.MILLISECONDS) > 30) {
                    c(file3);
                } else if (i < 10) {
                    i++;
                } else {
                    c(file3);
                }
            }
        }
    }

    public final File b(String str) {
        File file = new File(d(), str);
        if (!file.exists()) {
            return file;
        }
        if (file.delete()) {
            return new File(d(), str);
        }
        Log.w("cr_CrashFileManager", "Unable to delete previous logfile" + file.getAbsolutePath());
        return file;
    }

    public final File d() {
        return new File(this.a, "Crash Reports");
    }

    public final File[] e() {
        File[] fileArrG = g(d);
        ArrayList arrayList = new ArrayList();
        for (File file : fileArrG) {
            int iH = h(file.getName());
            if (iH < 0) {
                iH = 0;
            }
            if (iH < 3) {
                arrayList.add(file);
            }
        }
        return (File[]) arrayList.toArray(new File[arrayList.size()]);
    }

    public final void f() {
        File file = new File(this.a, "Crashpad");
        if (file.exists()) {
            File fileD = d();
            if (fileD.mkdir() || fileD.isDirectory()) {
                N.MjZjkDM7(file.getAbsolutePath(), d().getAbsolutePath());
            }
        }
    }

    public final File[] g(Pattern pattern) {
        File fileD = d();
        File[] fileArrListFiles = fileD.listFiles(pattern != null ? new b(pattern) : null);
        if (fileArrListFiles != null) {
            Arrays.sort(fileArrListFiles, g);
            return fileArrListFiles;
        }
        Log.w("cr_CrashFileManager", fileD.getAbsolutePath() + " does not exist or is not a directory");
        return new File[0];
    }
}
