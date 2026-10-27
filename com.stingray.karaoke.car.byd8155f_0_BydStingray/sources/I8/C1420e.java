package i8;

/* JADX INFO: renamed from: i8.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1420e extends A9.b {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f15791n;
    public final /* synthetic */ C1419d o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1420e(C1419d c1419d, int i7) {
        super(7);
        this.f15791n = i7;
        this.o = c1419d;
    }

    @Override // A9.b
    public final void Z0(String[] strArr) {
        switch (this.f15791n) {
            case 0:
                if (strArr == null) {
                    throw new IllegalArgumentException("Argument for @NotNull parameter 'data' of kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor$OldDeprecatedAnnotationArgumentVisitor$1.visitEnd must not be null");
                }
                this.o.f15790m.d = strArr;
                return;
            default:
                if (strArr == null) {
                    throw new IllegalArgumentException("Argument for @NotNull parameter 'data' of kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor$OldDeprecatedAnnotationArgumentVisitor$2.visitEnd must not be null");
                }
                this.o.f15790m.f15796e = strArr;
                return;
        }
    }
}
