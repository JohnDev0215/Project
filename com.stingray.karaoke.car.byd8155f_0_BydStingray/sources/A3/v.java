package A3;

import E5.H;
import T.C0773y;
import X5.AbstractC0855e;
import c9.B;
import c9.C;
import com.stingray.karaoke.car.utils.LayoutMode;
import com.stingray.kca.model.AssetTypeV2;
import com.stingray.kca.model.AudioCodec;
import com.stingray.kca.model.ImageType;
import com.stingray.kca.model.KaraokeItemChildType;
import com.stingray.kca.model.KaraokeItemSortType;
import com.stingray.kca.model.KaraokeItemType;
import com.stingray.kca.model.Resolution;
import com.stingray.kca.model.SupportedFeatures;
import com.stingray.kca.model.VideoCodec;
import k6.AbstractC1692D;
import k6.C1701i;
import n0.AbstractC1861K;
import n0.C1880i;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class v implements InterfaceC2772a {
    public final /* synthetic */ int l;

    public /* synthetic */ v(int i7) {
        this.l = i7;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        m7.q qVar = m7.q.f19267a;
        switch (this.l) {
            case 0:
                return null;
            case 1:
                B b10 = A9.a.d;
                b10.getClass();
                return new C(b10);
            case 2:
                E4.b bVar = A9.i.f488a;
                A7.m.f(bVar, "moshiBuilder");
                bVar.a(AssetTypeV2.class, F5.a.e(AssetTypeV2.class).f(AssetTypeV2.UnknownDefaultOpenApi));
                bVar.a(AudioCodec.class, F5.a.e(AudioCodec.class).f(AudioCodec.UnknownDefaultOpenApi));
                bVar.a(ImageType.class, F5.a.e(ImageType.class).f(ImageType.UnknownDefaultOpenApi));
                bVar.a(KaraokeItemChildType.class, F5.a.e(KaraokeItemChildType.class).f(KaraokeItemChildType.UnknownDefaultOpenApi));
                bVar.a(KaraokeItemSortType.class, F5.a.e(KaraokeItemSortType.class).f(KaraokeItemSortType.UnknownDefaultOpenApi));
                bVar.a(KaraokeItemType.class, F5.a.e(KaraokeItemType.class).f(KaraokeItemType.UnknownDefaultOpenApi));
                bVar.a(Resolution.class, F5.a.e(Resolution.class).f(Resolution.UnknownDefaultOpenApi));
                bVar.a(SupportedFeatures.class, F5.a.e(SupportedFeatures.class).f(SupportedFeatures.UnknownDefaultOpenApi));
                bVar.a(VideoCodec.class, F5.a.e(VideoCodec.class).f(VideoCodec.UnknownDefaultOpenApi));
                return new H(bVar);
            case 3:
                return qVar;
            case 4:
                return new O5.a();
            case 5:
                return new O5.d();
            case 6:
                C1880i c1880iI = AbstractC1861K.i();
                c1880iI.f(1);
                return c1880iI;
            case 7:
                n7.z.b(null, true, AbstractC0855e.f10233a, 7);
                return qVar;
            case 8:
                C0773y c0773y = k6.s.f18295a;
                return Boolean.TRUE;
            case 9:
                return LayoutMode.WIDE_NORMAL;
            case 10:
                C0773y c0773y2 = AbstractC1692D.f18238a;
                return Boolean.FALSE;
            case 11:
                return new k6.u(0, 0);
            case 12:
                return new C1701i(0.0f, 7);
            case 13:
                return l6.d.c;
            case 14:
                return System.getProperties().getProperty("org.openapitools.client.baseUrl", "http://localhost");
            case 15:
                return System.getProperties().getProperty("org.openapitools.client.baseUrl", "http://localhost");
            case 16:
                return System.getProperties().getProperty("org.openapitools.client.baseUrl", "http://localhost");
            case 17:
                return System.getProperties().getProperty("org.openapitools.client.baseUrl", "http://localhost");
            case 18:
                return System.getProperties().getProperty("org.openapitools.client.baseUrl", "http://localhost");
            case 19:
                return System.getProperties().getProperty("org.openapitools.client.baseUrl", "http://localhost");
            default:
                return new C(new B());
        }
    }
}
