package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.util.Hashtable;
import java.util.StringTokenizer;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/* JADX INFO: loaded from: classes.dex */
public class KeySubst extends Task {
    private File source = null;
    private File dest = null;
    private String sep = "*";
    private Hashtable replacements = new Hashtable();

    /* JADX WARN: Removed duplicated region for block: B:54:0x008f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0088 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:71:? A[SYNTHETIC] */
    @Override // org.apache.tools.ant.Task
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void execute() throws java.lang.Throwable {
        /*
            r6 = this;
            java.lang.String r0 = "!! KeySubst is deprecated. Use Filter + Copy instead. !!"
            r6.log(r0)
            java.lang.String r0 = "Performing Substitutions"
            r6.log(r0)
            java.io.File r0 = r6.source
            if (r0 == 0) goto L95
            java.io.File r0 = r6.dest
            if (r0 != 0) goto L14
            goto L95
        L14:
            r0 = 0
            r1 = 0
            r2 = 0
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L6f java.io.IOException -> L71
            java.io.FileReader r4 = new java.io.FileReader     // Catch: java.lang.Throwable -> L6f java.io.IOException -> L71
            java.io.File r5 = r6.source     // Catch: java.lang.Throwable -> L6f java.io.IOException -> L71
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L6f java.io.IOException -> L71
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L6f java.io.IOException -> L71
            r0 = r3
            java.io.File r3 = r6.dest     // Catch: java.lang.Throwable -> L6f java.io.IOException -> L71
            r3.delete()     // Catch: java.lang.Throwable -> L6f java.io.IOException -> L71
            java.io.BufferedWriter r3 = new java.io.BufferedWriter     // Catch: java.lang.Throwable -> L6f java.io.IOException -> L71
            java.io.FileWriter r4 = new java.io.FileWriter     // Catch: java.lang.Throwable -> L6f java.io.IOException -> L71
            java.io.File r5 = r6.dest     // Catch: java.lang.Throwable -> L6f java.io.IOException -> L71
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L6f java.io.IOException -> L71
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L6f java.io.IOException -> L71
            r1 = r3
            r2 = 0
            r3 = 0
            java.lang.String r4 = r0.readLine()     // Catch: java.lang.Throwable -> L6f java.io.IOException -> L71
            r2 = r4
        L3e:
            if (r2 != 0) goto L52
            r1.flush()     // Catch: java.lang.Throwable -> L6f java.io.IOException -> L71
            r1.close()     // Catch: java.io.IOException -> L48
            goto L4a
        L48:
            r2 = move-exception
        L4a:
            r0.close()     // Catch: java.io.IOException -> L4f
            goto L51
        L4f:
            r2 = move-exception
        L51:
            goto L84
        L52:
            int r4 = r2.length()     // Catch: java.lang.Throwable -> L6f java.io.IOException -> L71
            if (r4 != 0) goto L5c
            r1.newLine()     // Catch: java.lang.Throwable -> L6f java.io.IOException -> L71
            goto L69
        L5c:
            java.util.Hashtable r4 = r6.replacements     // Catch: java.lang.Throwable -> L6f java.io.IOException -> L71
            java.lang.String r4 = replace(r2, r4)     // Catch: java.lang.Throwable -> L6f java.io.IOException -> L71
            r3 = r4
            r1.write(r3)     // Catch: java.lang.Throwable -> L6f java.io.IOException -> L71
            r1.newLine()     // Catch: java.lang.Throwable -> L6f java.io.IOException -> L71
        L69:
            java.lang.String r4 = r0.readLine()     // Catch: java.lang.Throwable -> L6f java.io.IOException -> L71
            r2 = r4
            goto L3e
        L6f:
            r3 = move-exception
            goto L86
        L71:
            r2 = move-exception
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L85
            if (r1 == 0) goto L7c
            r1.close()     // Catch: java.io.IOException -> L7b
            goto L7c
        L7b:
            r3 = move-exception
        L7c:
            if (r0 == 0) goto L84
            r0.close()     // Catch: java.io.IOException -> L82
            goto L84
        L82:
            r3 = move-exception
        L84:
            return
        L85:
            r3 = move-exception
        L86:
            if (r1 == 0) goto L8d
            r1.close()     // Catch: java.io.IOException -> L8c
            goto L8d
        L8c:
            r4 = move-exception
        L8d:
            if (r0 == 0) goto L94
            r0.close()     // Catch: java.io.IOException -> L93
            goto L94
        L93:
            r4 = move-exception
        L94:
            throw r3
        L95:
            java.lang.String r0 = "Source and destinations must not be null"
            r6.log(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.KeySubst.execute():void");
    }

    public void setSrc(File s) {
        this.source = s;
    }

    public void setDest(File dest) {
        this.dest = dest;
    }

    public void setSep(String sep) {
        this.sep = sep;
    }

    public void setKeys(String keys) {
        if (keys != null && keys.length() > 0) {
            StringTokenizer tok = new StringTokenizer(keys, this.sep, false);
            while (tok.hasMoreTokens()) {
                String token = tok.nextToken().trim();
                StringTokenizer itok = new StringTokenizer(token, "=", false);
                String name = itok.nextToken();
                String value = itok.nextToken();
                this.replacements.put(name, value);
            }
        }
    }

    public static void main(String[] args) {
        try {
            Hashtable hash = new Hashtable();
            hash.put("VERSION", "1.0.3");
            hash.put("b", "ffff");
            System.out.println(replace("$f ${VERSION} f ${b} jj $", hash));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String replace(String origString, Hashtable keys) throws BuildException {
        StringBuffer finalString = new StringBuffer();
        int i = 0;
        while (true) {
            int index = origString.indexOf("${", i);
            if (index > -1) {
                String key = origString.substring(index + 2, origString.indexOf("}", index + 3));
                finalString.append(origString.substring(i, index));
                if (keys.containsKey(key)) {
                    finalString.append(keys.get(key));
                } else {
                    finalString.append("${");
                    finalString.append(key);
                    finalString.append("}");
                }
                i = index + 3 + key.length();
            } else {
                finalString.append(origString.substring(i));
                return finalString.toString();
            }
        }
    }
}
