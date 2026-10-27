package com.amazon.identity.auth.device;

import org.w3c.dom.Element;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class nd extends rd {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f465a;
    public final String b;

    public nd(String str, String str2) {
        this.f465a = str;
        this.b = str2;
    }

    @Override // com.amazon.identity.auth.device.rd
    public void a(Element element) {
        element.setAttribute(this.f465a, this.b);
    }
}
