package b.a.a.n;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: ServiceLoader.java */
/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Set<String> f461a = new HashSet();

    public static <T> Set<T> a(Class<T> cls, ClassLoader classLoader) {
        if (classLoader == null) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet();
        String str = "META-INF/services/" + cls.getName();
        HashSet hashSet2 = new HashSet();
        try {
            Enumeration<URL> resources = classLoader.getResources(str);
            while (resources.hasMoreElements()) {
                URL urlNextElement = resources.nextElement();
                Set<String> set = f461a;
                if (!set.contains(urlNextElement.toString())) {
                    b(urlNextElement, hashSet2);
                    set.add(urlNextElement.toString());
                }
            }
        } catch (Throwable unused) {
        }
        Iterator it = hashSet2.iterator();
        while (it.hasNext()) {
            try {
                hashSet.add(classLoader.loadClass((String) it.next()).newInstance());
            } catch (Exception unused2) {
            }
        }
        return hashSet;
    }

    public static void b(URL url, Set<String> set) throws Throwable {
        InputStream inputStreamOpenStream;
        BufferedReader bufferedReader = null;
        try {
            inputStreamOpenStream = url.openStream();
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStreamOpenStream, "utf-8"));
                while (true) {
                    try {
                        String line = bufferedReader2.readLine();
                        if (line == null) {
                            e.a(bufferedReader2);
                            e.a(inputStreamOpenStream);
                            return;
                        }
                        int iIndexOf = line.indexOf(35);
                        if (iIndexOf >= 0) {
                            line = line.substring(0, iIndexOf);
                        }
                        String strTrim = line.trim();
                        if (strTrim.length() != 0) {
                            set.add(strTrim);
                        }
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        e.a(bufferedReader);
                        e.a(inputStreamOpenStream);
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            inputStreamOpenStream = null;
        }
    }
}
