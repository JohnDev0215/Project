package defpackage;

import org.chromium.chrome.browser.omnibox.suggestions.d;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ol2 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ String f;
    public final /* synthetic */ long g;
    public final /* synthetic */ Object h;

    public /* synthetic */ ol2(long j, Object obj, int i, String str) {
        this.e = i;
        this.h = obj;
        this.f = str;
        this.g = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        long j = this.g;
        String str = this.f;
        Object obj = this.h;
        switch (i) {
            case 0:
                ((pl2) obj).a().edit().putLong(pl2.b(str), j).commit();
                break;
            default:
                ((d) obj).e(j, str);
                break;
        }
    }
}
