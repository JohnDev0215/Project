package UpsellInterface.v1_0;

/* JADX INFO: loaded from: classes.dex */
public class ContentTrait {
    public static final String AD_FREE = "AD_FREE";
    public static final String PARTIALLY_AD_FREE = "PARTIALLY_AD_FREE";
    public static final String AD_SUPPORTED = "AD_SUPPORTED";
    public static final String PARTIALLY_AD_SUPPORTED = "PARTIALLY_AD_SUPPORTED";
    public static final String PRE_RELEASE = "PRE_RELEASE";
    public static final String BONUS = "BONUS";
    public static final String EXCLUSIVE = "EXCLUSIVE";
    private static final String[] values = {AD_FREE, PARTIALLY_AD_FREE, AD_SUPPORTED, PARTIALLY_AD_SUPPORTED, PRE_RELEASE, BONUS, EXCLUSIVE};

    private ContentTrait() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
