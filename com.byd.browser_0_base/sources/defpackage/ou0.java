package defpackage;

import com.google.protobuf.c0;

/* JADX INFO: compiled from: HintsProto.java */
/* JADX INFO: loaded from: classes3.dex */
public enum ou0 implements c0.a {
    TYPE_UNSPECIFIED(0),
    NOSCRIPT(1),
    RESOURCE_LOADING(2),
    LITE_PAGE_REDIRECT(3),
    METADATA_FETCH_VALIDATION(4),
    DEFER_ALL_SCRIPT(5),
    PERFORMANCE_HINTS(6),
    LITE_PAGE(7),
    COMPRESS_PUBLIC_IMAGES(8),
    LOADING_PREDICTOR(9),
    FAST_HOST_HINTS(10),
    LITE_VIDEO(13),
    LINK_PERFORMANCE(14),
    SHOPPING_PAGE_PREDICTOR(15),
    LOGIN_DETECTION(16),
    MERCHANT_TRUST_SIGNALS(17),
    PRICE_TRACKING(18),
    BLOOM_FILTER_VALIDATION(19),
    ABOUT_THIS_SITE(20),
    MERCHANT_TRUST_SIGNALS_V2(21),
    PAGE_ENTITIES(22),
    HISTORY_CLUSTERS(23),
    THANK_CREATOR_ELIGIBLE(24),
    IBAN_AUTOFILL_BLOCKED(25),
    SALIENT_IMAGE(26),
    AUTOFILL_SAMPLING_RATE(27),
    VCN_MERCHANT_OPT_OUT_VISA(28);

    public final int e;

    /* JADX INFO: compiled from: HintsProto.java */
    public static final class a implements c0.b {
        public static final a a = new a();

        @Override // com.google.protobuf.c0.b
        public final boolean a(int i) {
            return ou0.a(i) != null;
        }
    }

    ou0(int i) {
        this.e = i;
    }

    public static ou0 a(int i) {
        switch (i) {
            case 0:
                return TYPE_UNSPECIFIED;
            case 1:
                return NOSCRIPT;
            case 2:
                return RESOURCE_LOADING;
            case 3:
                return LITE_PAGE_REDIRECT;
            case 4:
                return METADATA_FETCH_VALIDATION;
            case 5:
                return DEFER_ALL_SCRIPT;
            case 6:
                return PERFORMANCE_HINTS;
            case 7:
                return LITE_PAGE;
            case 8:
                return COMPRESS_PUBLIC_IMAGES;
            case 9:
                return LOADING_PREDICTOR;
            case 10:
                return FAST_HOST_HINTS;
            case 11:
            case 12:
            default:
                return null;
            case 13:
                return LITE_VIDEO;
            case 14:
                return LINK_PERFORMANCE;
            case 15:
                return SHOPPING_PAGE_PREDICTOR;
            case 16:
                return LOGIN_DETECTION;
            case 17:
                return MERCHANT_TRUST_SIGNALS;
            case 18:
                return PRICE_TRACKING;
            case 19:
                return BLOOM_FILTER_VALIDATION;
            case 20:
                return ABOUT_THIS_SITE;
            case 21:
                return MERCHANT_TRUST_SIGNALS_V2;
            case 22:
                return PAGE_ENTITIES;
            case 23:
                return HISTORY_CLUSTERS;
            case 24:
                return THANK_CREATOR_ELIGIBLE;
            case 25:
                return IBAN_AUTOFILL_BLOCKED;
            case 26:
                return SALIENT_IMAGE;
            case 27:
                return AUTOFILL_SAMPLING_RATE;
            case 28:
                return VCN_MERCHANT_OPT_OUT_VISA;
        }
    }

    @Override // com.google.protobuf.c0.a
    public final int b() {
        return this.e;
    }
}
