package c.a.j.f;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* JADX INFO: compiled from: ConfigFileAnalysis.java */
/* JADX INFO: loaded from: classes.dex */
public class e {
    public static h a(String str) {
        h hVar = new h();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(str)));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                if (line.substring(0, 3).equals("IV:")) {
                    hVar.h(line.substring(3, line.length()));
                } else if (line.substring(0, 7).equals("Secret:")) {
                    hVar.k(line.substring(7, line.length()));
                } else if (line.substring(0, 4).equals("MD5:")) {
                    hVar.j(line.substring(4, line.length()));
                } else if (line.substring(0, 12).equals("UpgradeType:")) {
                    hVar.p(line.substring(12, line.length()));
                } else if (line.substring(0, 10).equals("TimeStamp:")) {
                    String strSubstring = line.substring(10, line.length());
                    if (strSubstring != null) {
                        String[] strArrSplit = new String(g.a.f.j.a.a(strSubstring)).split(":");
                        hVar.o(strArrSplit[0]);
                        hVar.q(strArrSplit[1]);
                    }
                } else if (line.substring(0, 7).equals("Length:")) {
                    hVar.i(line.substring(7, line.length()));
                } else if (line.substring(0, 7).equals("Sha256:")) {
                    hVar.l(line.substring(7, line.length()));
                } else if (line.substring(0, 7).equals("Signer:")) {
                    hVar.n(line.substring(7, line.length()));
                } else if (line.substring(0, 10).equals("Signature:")) {
                    hVar.m(line.substring(10, line.length()));
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return hVar;
    }

    public static i b(String str) {
        i iVar = new i();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(str)));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                if (line.substring(0, 5).equals("Data:")) {
                    iVar.d(line.substring(5, line.length()));
                } else if (line.substring(0, 7).equals("Issuer:")) {
                    iVar.e(line.substring(7, line.length()));
                } else if (line.substring(0, 7).equals("Signer:")) {
                    iVar.g(line.substring(7, line.length()));
                } else if (line.substring(0, 10).equals("Signature:")) {
                    iVar.f(line.substring(10, line.length()));
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return iVar;
    }

    public static j c(String str) {
        String strSubstring;
        j jVar = new j();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(str)));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                if (line.substring(0, 3).equals("IV:")) {
                    jVar.l(line.substring(3, line.length()));
                } else if (line.substring(0, 7).equals("Secret:")) {
                    jVar.n(line.substring(7, line.length()));
                } else if (line.substring(0, 4).equals("MD5:")) {
                    jVar.m(line.substring(4, line.length()));
                } else if (line.substring(0, 5).equals("Data:")) {
                    jVar.j(line.substring(5, line.length()));
                } else if (line.substring(0, 7).equals("Issuer:")) {
                    jVar.k(line.substring(7, line.length()));
                } else if (line.substring(0, 7).equals("Signer:")) {
                    jVar.p(line.substring(7, line.length()));
                } else if (line.substring(0, 10).equals("Signature:")) {
                    jVar.o(line.substring(10, line.length()));
                } else if (line.substring(0, 11).equals("CipherText:")) {
                    jVar.i(line.substring(11, line.length()));
                } else if (line.substring(0, 10).equals("TimeStamp:") && (strSubstring = line.substring(10, line.length())) != null) {
                    String[] strArrSplit = new String(g.a.f.j.a.a(strSubstring)).split(":");
                    jVar.q(strArrSplit[0]);
                    jVar.r(strArrSplit[1]);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jVar;
    }
}
