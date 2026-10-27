package defpackage;

import android.view.ViewStructure;
import org.chromium.content.browser.accessibility.ViewStructureBuilder;

/* JADX INFO: compiled from: OViewStructureBuilder.java */
/* JADX INFO: loaded from: classes3.dex */
public final class yp1 extends ViewStructureBuilder {
    public yp1(za2 za2Var) {
        super(za2Var);
    }

    @Override // org.chromium.content.browser.accessibility.ViewStructureBuilder
    public final void setViewStructureNodeHtmlInfo(ViewStructure viewStructure, String str, String str2, String[][] strArr) {
        super.setViewStructureNodeHtmlInfo(viewStructure, str, str2, strArr);
        ViewStructure.HtmlInfo.Builder builderNewHtmlInfoBuilder = viewStructure.newHtmlInfoBuilder(str);
        if (builderNewHtmlInfoBuilder != null) {
            builderNewHtmlInfoBuilder.addAttribute("display", str2);
            for (String[] strArr2 : strArr) {
                builderNewHtmlInfoBuilder.addAttribute(strArr2[0], strArr2[1]);
            }
            viewStructure.setHtmlInfo(builderNewHtmlInfoBuilder.build());
        }
    }
}
