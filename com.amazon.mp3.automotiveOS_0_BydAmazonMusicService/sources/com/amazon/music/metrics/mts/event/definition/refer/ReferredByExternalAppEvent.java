package com.amazon.music.metrics.mts.event.definition.refer;

import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.util.StringUtil;

/* JADX INFO: loaded from: classes4.dex */
public class ReferredByExternalAppEvent extends MTSEvent {
    public ReferredByExternalAppEvent(String str) throws InvalidReferrerException {
        super("referredByExternalApp");
        addEventAttributes(str);
    }

    private void addEventAttributes(String str) throws InvalidReferrerException {
        if (StringUtil.isNullOrWhiteSpace(str)) {
            throw new InvalidReferrerException();
        }
        addAttribute("appIdentity", str);
    }
}
