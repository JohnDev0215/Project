package h2;

import com.alibaba.fastjson.JSONException;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.sql.Clob;
import java.sql.SQLException;

/* JADX INFO: compiled from: ClobSeriliazer.java */
/* JADX INFO: loaded from: classes.dex */
public class s implements t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final s f5305a = new s();

    @Override // h2.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i8) throws IOException {
        try {
            if (obj == null) {
                i0Var.J();
                return;
            }
            Reader characterStream = ((Clob) obj).getCharacterStream();
            StringBuilder sb = new StringBuilder();
            try {
                char[] cArr = new char[2048];
                while (true) {
                    int i9 = characterStream.read(cArr, 0, 2048);
                    if (i9 < 0) {
                        String string = sb.toString();
                        characterStream.close();
                        i0Var.I(string);
                        return;
                    }
                    sb.append(cArr, 0, i9);
                }
            } catch (Exception e8) {
                throw new JSONException("read string from reader error", e8);
            }
        } catch (SQLException e9) {
            throw new IOException("write clob error", e9);
        }
    }
}
