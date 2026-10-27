package i8;

import h8.m;

/* JADX INFO: renamed from: i8.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1418c extends A9.b {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f15789n;
    public final /* synthetic */ m o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1418c(m mVar, int i7) {
        super(7);
        this.f15789n = i7;
        this.o = mVar;
    }

    @Override // A9.b
    public final void Z0(String[] strArr) {
        switch (this.f15789n) {
            case 0:
                if (strArr == null) {
                    throw new IllegalArgumentException("Argument for @NotNull parameter 'result' of kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor$KotlinMetadataArgumentVisitor$1.visitEnd must not be null");
                }
                ((C1419d) this.o).f15790m.d = strArr;
                return;
            case 1:
                if (strArr == null) {
                    throw new IllegalArgumentException("Argument for @NotNull parameter 'result' of kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor$KotlinMetadataArgumentVisitor$2.visitEnd must not be null");
                }
                ((C1419d) this.o).f15790m.f15796e = strArr;
                return;
            default:
                if (strArr == null) {
                    throw new IllegalArgumentException("Argument for @NotNull parameter 'result' of kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor$KotlinSerializedIrArgumentVisitor$1.visitEnd must not be null");
                }
                ((C1419d) this.o).f15790m.f15799h = strArr;
                return;
        }
    }
}
