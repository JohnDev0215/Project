package UIMetricsInterface.v1_0;

/* JADX INFO: loaded from: classes.dex */
public class UISignalType {
    public static final String UI_CLICK = "UI_CLICK";
    public static final String UI_CONTENT_VIEW = "UI_CONTENT_VIEW";
    public static final String UI_PAGE_VIEW = "UI_PAGE_VIEW";
    private static final String[] values = {UI_CLICK, UI_CONTENT_VIEW, UI_PAGE_VIEW};

    private UISignalType() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
