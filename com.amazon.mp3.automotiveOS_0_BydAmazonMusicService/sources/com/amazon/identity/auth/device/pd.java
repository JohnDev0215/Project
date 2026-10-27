package com.amazon.identity.auth.device;

import com.amazon.music.inappmessaging.internal.model.Splash;
import java.util.Map;
import org.w3c.dom.Element;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class pd extends rd {
    public static final String b = "com.amazon.identity.auth.device.pd";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<String, jd> f497a;

    public pd(Map<String, jd> map) {
        this.f497a = map;
    }

    @Override // com.amazon.identity.auth.device.rd
    public void a(Element element) {
        Map<String, jd> map = this.f497a;
        if (map == null || map.size() == 0) {
            return;
        }
        qd qdVar = new qd("deviceTypeSoftwareVersionMap", null, new rd[0]);
        for (Map.Entry<String, jd> entry : this.f497a.entrySet()) {
            jd value = entry.getValue();
            if (value == null || value.f400a == null || value.b == null || entry.getKey() == null) {
                ga.d(b, "Skipping version for entry %s - version not present", entry.getKey());
            } else {
                qdVar.c.add(new qd("entry", null, new nd(Splash.PARAMS_DEVICE_TYPE, entry.getKey()), new nd("version", entry.getValue().f400a.toString()), new nd("softwareComponentId", entry.getValue().b)));
            }
        }
        element.appendChild(qdVar.a(element.getOwnerDocument()));
    }
}
