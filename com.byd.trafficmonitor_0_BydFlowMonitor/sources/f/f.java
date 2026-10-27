package f;

import java.io.IOException;

/* JADX INFO: compiled from: Callback.java */
/* JADX INFO: loaded from: classes.dex */
public interface f {
    void onFailure(e eVar, IOException iOException);

    void onResponse(e eVar, y yVar) throws IOException;
}
