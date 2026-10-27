package com.amazon.identity.auth.device;

import org.w3c.dom.Element;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class od extends rd {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f481a;

    public od(String str) {
        this.f481a = str;
    }

    @Override // com.amazon.identity.auth.device.rd
    public void a(Element element) {
        element.appendChild(element.getOwnerDocument().createCDATASection(this.f481a));
    }
}
