package n8;

import A7.m;
import java.util.Arrays;
import l8.AbstractC1783a;

/* JADX INFO: loaded from: classes.dex */
public final class f extends AbstractC1783a {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final f f20166g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final f f20167h;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f20168f;

    static {
        f fVar = new f(new int[]{1, 8, 0}, false);
        f20166g = fVar;
        int i7 = fVar.c;
        int i10 = fVar.f18823b;
        f20167h = (i10 == 1 && i7 == 9) ? new f(new int[]{2, 0, 0}, false) : new f(new int[]{i10, i7 + 1, 0}, false);
        new f(new int[0], false);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(int[] iArr, boolean z6) {
        super(Arrays.copyOf(iArr, iArr.length));
        m.f(iArr, "versionArray");
        this.f20168f = z6;
    }

    public final boolean b(f fVar) {
        m.f(fVar, "metadataVersionFromLanguageVersion");
        f fVar2 = f20166g;
        int i7 = this.f18823b;
        int i10 = this.c;
        if (i7 == 2 && i10 == 0 && fVar2.f18823b == 1 && fVar2.c == 8) {
            return true;
        }
        if (!this.f20168f) {
            fVar2 = f20167h;
        }
        fVar2.getClass();
        int i11 = fVar.f18823b;
        int i12 = fVar2.f18823b;
        if (i12 > i11 || (i12 >= i11 && fVar2.c > fVar.c)) {
            fVar = fVar2;
        }
        boolean z6 = false;
        if ((i7 == 1 && i10 == 0) || i7 == 0) {
            return false;
        }
        int i13 = fVar.f18823b;
        if (i7 > i13 || (i7 >= i13 && i10 > fVar.c)) {
            z6 = true;
        }
        return !z6;
    }
}
