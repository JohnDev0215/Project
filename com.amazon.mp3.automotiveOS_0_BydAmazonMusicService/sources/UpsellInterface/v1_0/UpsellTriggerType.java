package UpsellInterface.v1_0;

/* JADX INFO: loaded from: classes.dex */
public class UpsellTriggerType {
    public static final String PERSISTENT_UPSELL = "PERSISTENT_UPSELL";
    public static final String INELIGIBLE_CONTENT_REQUEST = "INELIGIBLE_CONTENT_REQUEST";
    private static final String[] values = {PERSISTENT_UPSELL, INELIGIBLE_CONTENT_REQUEST};

    private UpsellTriggerType() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
