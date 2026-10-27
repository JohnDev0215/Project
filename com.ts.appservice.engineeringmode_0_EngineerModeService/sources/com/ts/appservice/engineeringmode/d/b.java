package com.ts.appservice.engineeringmode.d;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static b f35b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private b.a.a.a.a f36a;

    private b() {
        try {
            this.f36a = b.a.a.a.a.d();
        } catch (Exception e) {
            com.ts.appservice.engineeringmode.e.a.d("DisplayManager", "DisplayManager.IDisplay.getService ", e);
        }
    }

    public static synchronized b c() {
        if (f35b == null) {
            f35b = new b();
        }
        return f35b;
    }

    public boolean a(int i) {
        boolean displayForwardLinkMarginValue;
        b.a.a.a.a aVar = this.f36a;
        if (aVar != null) {
            try {
                displayForwardLinkMarginValue = aVar.getDisplayForwardLinkMarginValue(i);
            } catch (Exception e) {
                com.ts.appservice.engineeringmode.e.a.d("DisplayManager", "mDisplay.getDisplayForwardLinkMarginValue : ", e);
                displayForwardLinkMarginValue = false;
            }
            com.ts.appservice.engineeringmode.e.a.g("DisplayManager", "getDisplayForwardLinkMarginValue : " + displayForwardLinkMarginValue);
            return displayForwardLinkMarginValue;
        }
        com.ts.appservice.engineeringmode.e.a.g("DisplayManager", "mDisplay == null");
        displayForwardLinkMarginValue = false;
        com.ts.appservice.engineeringmode.e.a.g("DisplayManager", "getDisplayForwardLinkMarginValue : " + displayForwardLinkMarginValue);
        return displayForwardLinkMarginValue;
    }

    public boolean b(int i) {
        boolean displayReverseLinkMarginValue;
        b.a.a.a.a aVar = this.f36a;
        if (aVar != null) {
            try {
                displayReverseLinkMarginValue = aVar.getDisplayReverseLinkMarginValue(i);
            } catch (Exception e) {
                com.ts.appservice.engineeringmode.e.a.d("DisplayManager", "mDisplay.getDisplayReverseLinkMarginValue : ", e);
                displayReverseLinkMarginValue = false;
            }
            com.ts.appservice.engineeringmode.e.a.g("DisplayManager", "getDisplayReverseLinkMarginValue : " + displayReverseLinkMarginValue);
            return displayReverseLinkMarginValue;
        }
        com.ts.appservice.engineeringmode.e.a.g("DisplayManager", "mDisplay == null");
        displayReverseLinkMarginValue = false;
        com.ts.appservice.engineeringmode.e.a.g("DisplayManager", "getDisplayReverseLinkMarginValue : " + displayReverseLinkMarginValue);
        return displayReverseLinkMarginValue;
    }
}
