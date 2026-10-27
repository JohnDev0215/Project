package T7;

import Q7.InterfaceC0657j;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import o8.C2061f;

/* JADX INFO: renamed from: T7.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0784c extends AbstractC0789h {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractC0784c(E8.o oVar, InterfaceC0657j interfaceC0657j, R7.h hVar, C2061f c2061f, int i7, boolean z6, int i10, Q7.O o) {
        super(oVar, interfaceC0657j, hVar, c2061f, i7, z6, i10, o);
        if (oVar == null) {
            K0(0);
            throw null;
        }
        if (interfaceC0657j == null) {
            K0(1);
            throw null;
        }
        if (i7 == 0) {
            K0(4);
            throw null;
        }
        if (o != null) {
        } else {
            K0(6);
            throw null;
        }
    }

    public static /* synthetic */ void K0(int i7) {
        Object[] objArr = new Object[3];
        switch (i7) {
            case 1:
                objArr[0] = "containingDeclaration";
                break;
            case 2:
                objArr[0] = "annotations";
                break;
            case 3:
                objArr[0] = "name";
                break;
            case 4:
                objArr[0] = "variance";
                break;
            case 5:
                objArr[0] = BYDAutoMultimediaDevice.PARAM_SOURCE;
                break;
            case 6:
                objArr[0] = "supertypeLoopChecker";
                break;
            default:
                objArr[0] = "storageManager";
                break;
        }
        objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/AbstractLazyTypeParameterDescriptor";
        objArr[2] = "<init>";
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    @Override // T7.AbstractC0794m, A9.b
    public final String toString() {
        return (this.f8838r ? "reified " : "") + (e0() != 1 ? A7.k.x(e0()).concat(" ") : "") + getName();
    }
}
