package F8;

/* JADX INFO: loaded from: classes.dex */
public final class P extends O {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f2450a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AbstractC0287w f2451b;

    public P(int i7, AbstractC0287w abstractC0287w) {
        if (i7 == 0) {
            e(0);
            throw null;
        }
        if (abstractC0287w == null) {
            e(1);
            throw null;
        }
        this.f2450a = i7;
        this.f2451b = abstractC0287w;
    }

    public static /* synthetic */ void e(int i7) {
        String str = (i7 == 4 || i7 == 5) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i7 == 4 || i7 == 5) ? 2 : 3];
        switch (i7) {
            case 1:
            case 2:
            case 3:
                objArr[0] = "type";
                break;
            case 4:
            case 5:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/types/TypeProjectionImpl";
                break;
            case 6:
                objArr[0] = "kotlinTypeRefiner";
                break;
            default:
                objArr[0] = "projection";
                break;
        }
        if (i7 == 4) {
            objArr[1] = "getProjectionKind";
        } else if (i7 != 5) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/types/TypeProjectionImpl";
        } else {
            objArr[1] = "getType";
        }
        if (i7 == 3) {
            objArr[2] = "replaceType";
        } else if (i7 != 4 && i7 != 5) {
            if (i7 != 6) {
                objArr[2] = "<init>";
            } else {
                objArr[2] = "refine";
            }
        }
        String str2 = String.format(str, objArr);
        if (i7 != 4 && i7 != 5) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    @Override // F8.O
    public final int a() {
        int i7 = this.f2450a;
        if (i7 != 0) {
            return i7;
        }
        e(4);
        throw null;
    }

    @Override // F8.O
    public final AbstractC0287w b() {
        AbstractC0287w abstractC0287w = this.f2451b;
        if (abstractC0287w != null) {
            return abstractC0287w;
        }
        e(5);
        throw null;
    }

    @Override // F8.O
    public final boolean c() {
        return false;
    }

    @Override // F8.O
    public final O d(G8.f fVar) {
        if (fVar == null) {
            e(6);
            throw null;
        }
        AbstractC0287w abstractC0287w = this.f2451b;
        A7.m.f(abstractC0287w, "type");
        return new P(this.f2450a, abstractC0287w);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public P(AbstractC0287w abstractC0287w) {
        this(1, abstractC0287w);
        if (abstractC0287w != null) {
        } else {
            e(2);
            throw null;
        }
    }
}
