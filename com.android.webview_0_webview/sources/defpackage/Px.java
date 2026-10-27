package defpackage;

import J.N;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Px {
    public static final Pattern b = Pattern.compile("^[^.]+-([^-,]+?)\\.");
    public static final Pattern c = Pattern.compile("\\.dmp([0-9]*)\\z");
    public static final Pattern d = Pattern.compile("\\.(dmp|forced)([0-9]*)(\\.try([0-9]+))\\z");
    public static final Pattern e = Pattern.compile("\\.(dmp)([0-9]*)(\\.try([0-9]+))\\z");
    public static final Pattern f = Pattern.compile("\\.up([0-9]*)(\\.try([0-9]+))\\z");
    public static final Pattern g = Pattern.compile("\\.forced([0-9]*)(\\.try([0-9]+))\\z");
    public static final Pattern h = Pattern.compile("\\.skipped([0-9]*)(\\.try([0-9]+))\\z");
    public static final Pattern i = Pattern.compile("\\.tmp\\z");
    public static final Comparator j = new Nx();
    public final File a;

    public Px(File file) {
        if (file == null) {
            throw new NullPointerException("Specified context cannot be null.");
        }
        if (file.isDirectory()) {
            this.a = file;
            return;
        }
        throw new IllegalArgumentException(file.getAbsolutePath() + " is not a directory.");
    }

    public static boolean c(File file) {
        boolean zDelete = file.delete();
        if (!zDelete) {
            StringBuilder sbF = Z7.f("Unable to delete ");
            sbF.append(file.getAbsolutePath());
            Ts.f("CrashFileManager", sbF.toString(), new Object[0]);
        }
        return zDelete;
    }

    public static String g(String str) {
        Matcher matcher = b.matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public static boolean j(File file) {
        return file.getName().contains(".forced");
    }

    public static int l(String str) {
        int iM = m(str);
        if (iM >= 0) {
            return iM;
        }
        return 0;
    }

    public static int m(String str) {
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

    public static void n(File file, String str) {
        if (file.renameTo(new File(file.getPath().replace(".dmp", str).replace(".forced", str)))) {
            return;
        }
        Ts.f("CrashFileManager", "Failed to rename " + file, new Object[0]);
        if (file.delete()) {
            return;
        }
        Ts.f("CrashFileManager", "Failed to delete " + file, new Object[0]);
    }

    public static String o(File file) {
        String strD;
        String path = file.getPath();
        int iM = m(path);
        if (iM >= 0) {
            int i2 = iM + 1;
            strD = path.replace(Z7.a(".try", iM), ".try" + i2);
        } else {
            strD = Z7.d(path, ".try", "1");
        }
        if (file.renameTo(new File(strD))) {
            return strD;
        }
        return null;
    }

    public void a() {
        for (File file : k(f)) {
            c(file);
        }
        for (File file2 : k(i)) {
            c(file2);
        }
        int i2 = 0;
        for (File file3 : k(null)) {
            if (!file3.getName().equals("uploads.log")) {
                if (TimeUnit.DAYS.convert(new Date().getTime() - file3.lastModified(), TimeUnit.MILLISECONDS) > 30) {
                    c(file3);
                } else if (i2 < 10) {
                    i2++;
                } else {
                    c(file3);
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:114:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01b6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01a3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.io.File b(java.io.FileDescriptor r17, java.io.File r18, int r19) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 462
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.Px.b(java.io.FileDescriptor, java.io.File, int):java.io.File");
    }

    public final boolean d() {
        File fileE = e();
        return fileE.mkdir() || fileE.isDirectory();
    }

    public File e() {
        return new File(this.a, "Crash Reports");
    }

    public File f(String str) {
        for (File file : k(null)) {
            if ((file.getName().contains(".dmp") || file.getName().contains(".skipped") || file.getName().contains(".forced")) && file.getName().split("\\.")[0].endsWith(str)) {
                return file;
            }
        }
        return null;
    }

    public File[] h(int i2) {
        File[] fileArrK = k(d);
        ArrayList arrayList = new ArrayList();
        for (File file : fileArrK) {
            if (l(file.getName()) < i2) {
                arrayList.add(file);
            }
        }
        return (File[]) arrayList.toArray(new File[arrayList.size()]);
    }

    public Map i() {
        File file = new File(this.a, "Crashpad");
        if (!file.exists() || !d()) {
            return null;
        }
        String[] strArrMdQEl6jg = N.MdQEl6jg(file.getAbsolutePath(), e().getAbsolutePath());
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        for (int i2 = 0; i2 < strArrMdQEl6jg.length; i2 += 2) {
            String str = strArrMdQEl6jg[i2];
            String str2 = strArrMdQEl6jg[i2 + 1];
            if (str.equals("upload_file_minidump")) {
                map.put(str2, map2);
                map2 = new HashMap();
            } else {
                map2.put(str, str2);
            }
        }
        return map;
    }

    public File[] k(Pattern pattern) {
        File fileE = e();
        File[] fileArrListFiles = fileE.listFiles(pattern != null ? new Ox(this, pattern) : null);
        if (fileArrListFiles != null) {
            Arrays.sort(fileArrListFiles, j);
            return fileArrListFiles;
        }
        Ts.f("CrashFileManager", fileE.getAbsolutePath() + " does not exist or is not a directory", new Object[0]);
        return new File[0];
    }
}
