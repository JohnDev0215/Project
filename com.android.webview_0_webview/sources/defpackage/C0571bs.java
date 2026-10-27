package defpackage;

import java.io.IOException;
import java.io.InputStream;
import org.chromium.support_lib_boundary.WebSettingsBoundaryInterface;

/* JADX INFO: renamed from: bs, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0571bs extends AbstractC0065Dl {
    public static final int COUNTRY_FIELD_NUMBER = 2;
    public static final int DATE_FIELD_NUMBER = 6;
    public static final int DATE_HEADER_FIELD_NUMBER = 3;
    public static final C0571bs DEFAULT_INSTANCE;
    public static final int IS_GZIP_COMPRESSED_FIELD_NUMBER = 4;
    public static volatile InterfaceC0900hm PARSER = null;
    public static final int SEED_DATA_FIELD_NUMBER = 5;
    public static final int SIGNATURE_FIELD_NUMBER = 1;
    public int bitField0_;
    public long date_;
    public boolean isGzipCompressed_;
    public String signature_ = "";
    public String country_ = "";
    public String dateHeader_ = "";
    public AbstractC0731el seedData_ = AbstractC0731el.t;

    static {
        C0571bs c0571bs = new C0571bs();
        DEFAULT_INSTANCE = c0571bs;
        AbstractC0065Dl.defaultInstanceMap.put(C0571bs.class, c0571bs);
    }

    public static C0571bs k(InputStream inputStream) throws C0155Il {
        C0571bs c0571bs = DEFAULT_INSTANCE;
        C0954il c0954il = new C0954il(inputStream, 4096, null);
        C1506sl c1506slA = C1506sl.a();
        AbstractC0065Dl abstractC0065Dl = (AbstractC0065Dl) c0571bs.c(EnumC0047Cl.NEW_MUTABLE_INSTANCE, null, null);
        try {
            InterfaceC1178mm interfaceC1178mmB = C1011jm.c.b(abstractC0065Dl);
            C1066kl c1066kl = c0954il.d;
            if (c1066kl == null) {
                c1066kl = new C1066kl(c0954il);
            }
            interfaceC1178mmB.c(abstractC0065Dl, c1066kl, c1506slA);
            interfaceC1178mmB.h(abstractC0065Dl);
            if (abstractC0065Dl == null || abstractC0065Dl.h()) {
                return (C0571bs) abstractC0065Dl;
            }
            throw new C0155Il(new C1452rm().getMessage());
        } catch (IOException e) {
            if (e.getCause() instanceof C0155Il) {
                throw ((C0155Il) e.getCause());
            }
            throw new C0155Il(e.getMessage());
        } catch (RuntimeException e2) {
            if (e2.getCause() instanceof C0155Il) {
                throw ((C0155Il) e2.getCause());
            }
            throw e2;
        }
    }

    @Override // defpackage.AbstractC0065Dl
    public final Object c(EnumC0047Cl enumC0047Cl, Object obj, Object obj2) {
        switch (enumC0047Cl.ordinal()) {
            case WebSettingsBoundaryInterface.ForceDarkBehavior.FORCE_DARK_ONLY /* 0 */:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return new C1067km(DEFAULT_INSTANCE, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002\u0004\u0007\u0003\u0005\n\u0004\u0006\u0002\u0005", new Object[]{"bitField0_", "signature_", "country_", "dateHeader_", "isGzipCompressed_", "seedData_", "date_"});
            case 3:
                return new C0571bs();
            case IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                return new C0515as(null);
            case SEED_DATA_FIELD_NUMBER /* 5 */:
                return DEFAULT_INSTANCE;
            case DATE_FIELD_NUMBER /* 6 */:
                InterfaceC0900hm c0011Al = PARSER;
                if (c0011Al == null) {
                    synchronized (C0571bs.class) {
                        c0011Al = PARSER;
                        if (c0011Al == null) {
                            c0011Al = new C0011Al(DEFAULT_INSTANCE);
                            PARSER = c0011Al;
                        }
                        break;
                    }
                }
                return c0011Al;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
