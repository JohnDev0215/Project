package org.chromium.chrome.browser.omnibox.geo;

import com.google.protobuf.c0;

/* JADX INFO: compiled from: PartnerLocationDescriptor.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d implements c0.b {
    public static final d a = new d();

    @Override // com.google.protobuf.c0.b
    public final boolean a(int i) {
        char c;
        if (i != 0) {
            c = 2;
            if (i != 1) {
                if (i != 2) {
                    c = 4;
                    if (i != 3) {
                        c = i != 4 ? (char) 0 : (char) 5;
                    }
                } else {
                    c = 3;
                }
            }
        } else {
            c = 1;
        }
        return c != 0;
    }
}
