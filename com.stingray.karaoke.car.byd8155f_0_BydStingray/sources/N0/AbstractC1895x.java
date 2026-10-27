package n0;

import android.graphics.ColorSpace;
import java.util.function.DoubleUnaryOperator;
import o0.AbstractC2007c;
import o0.C2008d;
import o0.C2020p;
import o0.C2021q;
import o0.C2022r;
import o0.C2023s;
import o0.InterfaceC2013i;

/* JADX INFO: renamed from: n0.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1895x {
    public static final ColorSpace a(AbstractC2007c abstractC2007c) {
        C2021q c2021q;
        ColorSpace.Rgb.TransferParameters transferParameters;
        ColorSpace.Rgb rgb;
        if (A7.m.a(abstractC2007c, C2008d.c)) {
            return ColorSpace.get(ColorSpace.Named.SRGB);
        }
        if (A7.m.a(abstractC2007c, C2008d.o)) {
            return ColorSpace.get(ColorSpace.Named.ACES);
        }
        if (A7.m.a(abstractC2007c, C2008d.f20191p)) {
            return ColorSpace.get(ColorSpace.Named.ACESCG);
        }
        if (A7.m.a(abstractC2007c, C2008d.f20189m)) {
            return ColorSpace.get(ColorSpace.Named.ADOBE_RGB);
        }
        if (A7.m.a(abstractC2007c, C2008d.f20185h)) {
            return ColorSpace.get(ColorSpace.Named.BT2020);
        }
        if (A7.m.a(abstractC2007c, C2008d.f20184g)) {
            return ColorSpace.get(ColorSpace.Named.BT709);
        }
        if (A7.m.a(abstractC2007c, C2008d.f20193r)) {
            return ColorSpace.get(ColorSpace.Named.CIE_LAB);
        }
        if (A7.m.a(abstractC2007c, C2008d.f20192q)) {
            return ColorSpace.get(ColorSpace.Named.CIE_XYZ);
        }
        if (A7.m.a(abstractC2007c, C2008d.f20186i)) {
            return ColorSpace.get(ColorSpace.Named.DCI_P3);
        }
        if (A7.m.a(abstractC2007c, C2008d.f20187j)) {
            return ColorSpace.get(ColorSpace.Named.DISPLAY_P3);
        }
        if (A7.m.a(abstractC2007c, C2008d.f20182e)) {
            return ColorSpace.get(ColorSpace.Named.EXTENDED_SRGB);
        }
        if (A7.m.a(abstractC2007c, C2008d.f20183f)) {
            return ColorSpace.get(ColorSpace.Named.LINEAR_EXTENDED_SRGB);
        }
        if (A7.m.a(abstractC2007c, C2008d.d)) {
            return ColorSpace.get(ColorSpace.Named.LINEAR_SRGB);
        }
        if (A7.m.a(abstractC2007c, C2008d.f20188k)) {
            return ColorSpace.get(ColorSpace.Named.NTSC_1953);
        }
        if (A7.m.a(abstractC2007c, C2008d.f20190n)) {
            return ColorSpace.get(ColorSpace.Named.PRO_PHOTO_RGB);
        }
        if (A7.m.a(abstractC2007c, C2008d.l)) {
            return ColorSpace.get(ColorSpace.Named.SMPTE_C);
        }
        if (!(abstractC2007c instanceof C2021q)) {
            return ColorSpace.get(ColorSpace.Named.SRGB);
        }
        C2021q c2021q2 = (C2021q) abstractC2007c;
        float[] fArrA = c2021q2.d.a();
        C2022r c2022r = c2021q2.f20217g;
        if (c2022r != null) {
            c2021q = c2021q2;
            transferParameters = new ColorSpace.Rgb.TransferParameters(c2022r.f20227b, c2022r.c, c2022r.d, c2022r.f20228e, c2022r.f20229f, c2022r.f20230g, c2022r.f20226a);
        } else {
            c2021q = c2021q2;
            transferParameters = null;
        }
        if (transferParameters != null) {
            rgb = new ColorSpace.Rgb(abstractC2007c.f20178a, c2021q.f20218h, fArrA, transferParameters);
        } else {
            C2021q c2021q3 = c2021q;
            String str = abstractC2007c.f20178a;
            final C2020p c2020p = c2021q3.l;
            final int i7 = 0;
            DoubleUnaryOperator doubleUnaryOperator = new DoubleUnaryOperator() { // from class: n0.v
                @Override // java.util.function.DoubleUnaryOperator
                public final double applyAsDouble(double d) {
                    switch (i7) {
                        case 0:
                            return ((Number) ((C2020p) c2020p).b(Double.valueOf(d))).doubleValue();
                        default:
                            return ((Number) ((C2020p) c2020p).b(Double.valueOf(d))).doubleValue();
                    }
                }
            };
            final C2020p c2020p2 = c2021q3.o;
            final int i10 = 1;
            C2021q c2021q4 = (C2021q) abstractC2007c;
            rgb = new ColorSpace.Rgb(str, c2021q3.f20218h, fArrA, doubleUnaryOperator, new DoubleUnaryOperator() { // from class: n0.v
                @Override // java.util.function.DoubleUnaryOperator
                public final double applyAsDouble(double d) {
                    switch (i10) {
                        case 0:
                            return ((Number) ((C2020p) c2020p2).b(Double.valueOf(d))).doubleValue();
                        default:
                            return ((Number) ((C2020p) c2020p2).b(Double.valueOf(d))).doubleValue();
                    }
                }
            }, c2021q4.f20215e, c2021q4.f20216f);
        }
        return rgb;
    }

    public static final AbstractC2007c b(final ColorSpace colorSpace) {
        C2023s c2023s;
        C2023s c2023s2;
        C2022r c2022r;
        int id = colorSpace.getId();
        if (id == ColorSpace.Named.SRGB.ordinal()) {
            return C2008d.c;
        }
        if (id == ColorSpace.Named.ACES.ordinal()) {
            return C2008d.o;
        }
        if (id == ColorSpace.Named.ACESCG.ordinal()) {
            return C2008d.f20191p;
        }
        if (id == ColorSpace.Named.ADOBE_RGB.ordinal()) {
            return C2008d.f20189m;
        }
        if (id == ColorSpace.Named.BT2020.ordinal()) {
            return C2008d.f20185h;
        }
        if (id == ColorSpace.Named.BT709.ordinal()) {
            return C2008d.f20184g;
        }
        if (id == ColorSpace.Named.CIE_LAB.ordinal()) {
            return C2008d.f20193r;
        }
        if (id == ColorSpace.Named.CIE_XYZ.ordinal()) {
            return C2008d.f20192q;
        }
        if (id == ColorSpace.Named.DCI_P3.ordinal()) {
            return C2008d.f20186i;
        }
        if (id == ColorSpace.Named.DISPLAY_P3.ordinal()) {
            return C2008d.f20187j;
        }
        if (id == ColorSpace.Named.EXTENDED_SRGB.ordinal()) {
            return C2008d.f20182e;
        }
        if (id == ColorSpace.Named.LINEAR_EXTENDED_SRGB.ordinal()) {
            return C2008d.f20183f;
        }
        if (id == ColorSpace.Named.LINEAR_SRGB.ordinal()) {
            return C2008d.d;
        }
        if (id == ColorSpace.Named.NTSC_1953.ordinal()) {
            return C2008d.f20188k;
        }
        if (id == ColorSpace.Named.PRO_PHOTO_RGB.ordinal()) {
            return C2008d.f20190n;
        }
        if (id == ColorSpace.Named.SMPTE_C.ordinal()) {
            return C2008d.l;
        }
        if (!(colorSpace instanceof ColorSpace.Rgb)) {
            return C2008d.c;
        }
        ColorSpace.Rgb rgb = (ColorSpace.Rgb) colorSpace;
        ColorSpace.Rgb.TransferParameters transferParameters = rgb.getTransferParameters();
        if (rgb.getWhitePoint().length == 3) {
            float f10 = rgb.getWhitePoint()[0];
            float f11 = rgb.getWhitePoint()[1];
            float f12 = f10 + f11 + rgb.getWhitePoint()[2];
            c2023s = new C2023s(f10 / f12, f11 / f12);
        } else {
            c2023s = new C2023s(rgb.getWhitePoint()[0], rgb.getWhitePoint()[1]);
        }
        C2023s c2023s3 = c2023s;
        if (transferParameters != null) {
            c2023s2 = c2023s3;
            c2022r = new C2022r(transferParameters.g, transferParameters.a, transferParameters.b, transferParameters.c, transferParameters.d, transferParameters.e, transferParameters.f);
        } else {
            c2023s2 = c2023s3;
            c2022r = null;
        }
        String name = rgb.getName();
        float[] primaries = rgb.getPrimaries();
        float[] transform = rgb.getTransform();
        final int i7 = 0;
        InterfaceC2013i interfaceC2013i = new InterfaceC2013i() { // from class: n0.w
            @Override // o0.InterfaceC2013i
            public final double c(double d) {
                switch (i7) {
                    case 0:
                        return ((ColorSpace.Rgb) colorSpace).getOetf().applyAsDouble(d);
                    default:
                        return ((ColorSpace.Rgb) colorSpace).getEotf().applyAsDouble(d);
                }
            }
        };
        final int i10 = 1;
        return new C2021q(name, primaries, c2023s2, transform, interfaceC2013i, new InterfaceC2013i() { // from class: n0.w
            @Override // o0.InterfaceC2013i
            public final double c(double d) {
                switch (i10) {
                    case 0:
                        return ((ColorSpace.Rgb) colorSpace).getOetf().applyAsDouble(d);
                    default:
                        return ((ColorSpace.Rgb) colorSpace).getEotf().applyAsDouble(d);
                }
            }
        }, colorSpace.getMinValue(0), colorSpace.getMaxValue(0), c2022r, rgb.getId());
    }
}
