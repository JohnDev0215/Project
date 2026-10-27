package androidx.constraintlayout.core.widgets.analyzer;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
final class WidgetRun$RunType {
    public static final WidgetRun$RunType c;
    public static final WidgetRun$RunType e;
    public static final /* synthetic */ WidgetRun$RunType[] k;

    static {
        WidgetRun$RunType widgetRun$RunType = new WidgetRun$RunType("NONE", 0);
        c = widgetRun$RunType;
        WidgetRun$RunType widgetRun$RunType2 = new WidgetRun$RunType("START", 1);
        WidgetRun$RunType widgetRun$RunType3 = new WidgetRun$RunType("END", 2);
        WidgetRun$RunType widgetRun$RunType4 = new WidgetRun$RunType("CENTER", 3);
        e = widgetRun$RunType4;
        k = new WidgetRun$RunType[]{widgetRun$RunType, widgetRun$RunType2, widgetRun$RunType3, widgetRun$RunType4};
    }

    public static WidgetRun$RunType valueOf(String str) {
        return (WidgetRun$RunType) Enum.valueOf(WidgetRun$RunType.class, str);
    }

    public static WidgetRun$RunType[] values() {
        return (WidgetRun$RunType[]) k.clone();
    }
}
