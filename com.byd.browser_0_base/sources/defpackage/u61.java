package defpackage;

import J.N;
import defpackage.ll;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Locale;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.state.LevelDBPersistedDataStorage;

/* JADX INFO: compiled from: LevelDBPersistedTabDataStorage.java */
/* JADX INFO: loaded from: classes2.dex */
public final class u61 implements q02 {
    public final LevelDBPersistedDataStorage a;

    public u61(Profile profile) {
        this.a = new LevelDBPersistedDataStorage(profile);
    }

    public static final String h(int i, String str) {
        return String.format(Locale.US, "%d-%s", Integer.valueOf(i), str);
    }

    @Override // defpackage.q02
    public final void a(int i, String str, j02 j02Var) {
        String strH = h(i, str);
        t02 t02Var = new t02(j02Var, 5);
        LevelDBPersistedDataStorage levelDBPersistedDataStorage = this.a;
        N.MqUV_juQ(levelDBPersistedDataStorage.a, levelDBPersistedDataStorage.a(strH), t02Var);
    }

    @Override // defpackage.q02
    public final void b(int i, String str) {
        String strH = h(i, str);
        LevelDBPersistedDataStorage levelDBPersistedDataStorage = this.a;
        N.Mf_BEvgG(levelDBPersistedDataStorage.a, levelDBPersistedDataStorage.a(strH), null);
    }

    @Override // defpackage.q02
    public final p02 c(int i, String str) {
        return null;
    }

    @Override // defpackage.q02
    public final void e(String str, ArrayList arrayList) {
        int size = arrayList.size();
        String[] strArr = new String[size];
        for (int i = 0; i < arrayList.size(); i++) {
            strArr[i] = h(((Integer) arrayList.get(i)).intValue(), str);
        }
        LevelDBPersistedDataStorage levelDBPersistedDataStorage = this.a;
        long j = levelDBPersistedDataStorage.a;
        String[] strArr2 = new String[size];
        for (int i2 = 0; i2 < size; i2++) {
            strArr2[i2] = levelDBPersistedDataStorage.a(strArr[i2]);
        }
        N.MQnSvNoI(j, strArr2, str, null);
    }

    @Override // defpackage.q02
    public final void f(int i, String str, k02 k02Var) {
        byte[] bArrArray;
        k02Var.m();
        String strH = h(i, str);
        ByteBuffer byteBuffer = (ByteBuffer) k02Var.get();
        if (byteBuffer == null) {
            bArrArray = null;
        } else if (byteBuffer.hasArray() && byteBuffer.arrayOffset() == 0) {
            bArrArray = byteBuffer.array();
        } else {
            byte[] bArr = new byte[byteBuffer.limit()];
            byteBuffer.rewind();
            byteBuffer.get(bArr);
            bArrArray = bArr;
        }
        LevelDBPersistedDataStorage levelDBPersistedDataStorage = this.a;
        N.MDiWNRLP(levelDBPersistedDataStorage.a, levelDBPersistedDataStorage.a(strH), bArrArray, null);
    }

    @Override // defpackage.q02
    public final void d(int i, String str, ll.b bVar) {
    }

    @Override // defpackage.q02
    public final void g(int i, String str, k02 k02Var, Callback callback) {
    }
}
