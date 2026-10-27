package UpsellInterface.v1_0;

/* JADX INFO: loaded from: classes.dex */
public class UpsellType {
    public static final String FREE = "FREE";
    public static final String PRIME = "PRIME";
    public static final String UNLIMITED = "UNLIMITED";
    private static final String[] values = {FREE, PRIME, UNLIMITED};

    private UpsellType() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
