package SOATemplateListInterface.v1_0;

/* JADX INFO: loaded from: classes.dex */
public class TemplateScreenMode {
    public static final String standard = "standard";
    public static final String templateShard = "templateShard";
    public static final String templateList = "templateList";
    private static final String[] values = {"standard", templateShard, templateList};

    private TemplateScreenMode() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
