package com.android.car.developeroptions.panel;

/* JADX INFO: loaded from: classes.dex */
public class PanelFeatureProviderImpl implements PanelFeatureProvider {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:20:0x003e  */
    @Override // com.android.car.developeroptions.panel.PanelFeatureProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.android.car.developeroptions.panel.PanelContent getPanel(android.content.Context r5, java.lang.String r6, java.lang.String r7) {
        /*
            r4 = this;
            int r4 = r6.hashCode()
            r0 = 4
            r1 = 3
            r2 = 2
            r3 = 1
            switch(r4) {
                case 66351017: goto L34;
                case 464243859: goto L2a;
                case 1215888444: goto L20;
                case 1827023883: goto L16;
                case 2057152695: goto Lc;
                default: goto Lb;
            }
        Lb:
            goto L3e
        Lc:
            java.lang.String r4 = "android.settings.panel.action.WIFI"
            boolean r4 = r6.equals(r4)
            if (r4 == 0) goto L3e
            r4 = r1
            goto L3f
        L16:
            java.lang.String r4 = "com.android.settings.panel.action.MEDIA_OUTPUT"
            boolean r4 = r6.equals(r4)
            if (r4 == 0) goto L3e
            r4 = r3
            goto L3f
        L20:
            java.lang.String r4 = "android.settings.panel.action.VOLUME"
            boolean r4 = r6.equals(r4)
            if (r4 == 0) goto L3e
            r4 = r0
            goto L3f
        L2a:
            java.lang.String r4 = "android.settings.panel.action.INTERNET_CONNECTIVITY"
            boolean r4 = r6.equals(r4)
            if (r4 == 0) goto L3e
            r4 = 0
            goto L3f
        L34:
            java.lang.String r4 = "android.settings.panel.action.NFC"
            boolean r4 = r6.equals(r4)
            if (r4 == 0) goto L3e
            r4 = r2
            goto L3f
        L3e:
            r4 = -1
        L3f:
            if (r4 == 0) goto L74
            if (r4 == r3) goto L6f
            if (r4 == r2) goto L6a
            if (r4 == r1) goto L65
            if (r4 != r0) goto L4e
            com.android.car.developeroptions.panel.VolumePanel r4 = com.android.car.developeroptions.panel.VolumePanel.create(r5)
            return r4
        L4e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r7 = "No matching panel for: "
            r5.append(r7)
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            throw r4
        L65:
            com.android.car.developeroptions.panel.WifiPanel r4 = com.android.car.developeroptions.panel.WifiPanel.create(r5)
            return r4
        L6a:
            com.android.car.developeroptions.panel.NfcPanel r4 = com.android.car.developeroptions.panel.NfcPanel.create(r5)
            return r4
        L6f:
            com.android.car.developeroptions.panel.MediaOutputPanel r4 = com.android.car.developeroptions.panel.MediaOutputPanel.create(r5, r7)
            return r4
        L74:
            com.android.car.developeroptions.panel.InternetConnectivityPanel r4 = com.android.car.developeroptions.panel.InternetConnectivityPanel.create(r5)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.developeroptions.panel.PanelFeatureProviderImpl.getPanel(android.content.Context, java.lang.String, java.lang.String):com.android.car.developeroptions.panel.PanelContent");
    }
}
