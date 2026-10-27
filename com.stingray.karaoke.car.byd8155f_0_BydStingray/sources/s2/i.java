package s2;

import h2.AbstractC1343A;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class i implements u {
    public static final i l = new i();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final i f22094m = new i();

    @Override // s2.u
    public int a(Object obj) {
        String str = ((n) obj).f22095a;
        if (str.startsWith("OMX.google") || str.startsWith("c2.android")) {
            return 1;
        }
        return (AbstractC1343A.f15195a >= 26 || !str.equals("OMX.MTK.AUDIO.DECODER.RAW")) ? 0 : -1;
    }
}
