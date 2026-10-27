package SOATemplateListInterface.v1_0;

/* JADX INFO: loaded from: classes.dex */
public class TemplateLaunchMode {
    public static final String standard = "standard";
    public static final String singleUse = "singleUse";
    public static final String singleInstance = "singleInstance";
    private static final String[] values = {"standard", singleUse, singleInstance};

    private TemplateLaunchMode() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
