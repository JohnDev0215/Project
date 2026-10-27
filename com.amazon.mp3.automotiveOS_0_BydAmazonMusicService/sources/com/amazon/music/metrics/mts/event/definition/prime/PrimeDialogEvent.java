package com.amazon.music.metrics.mts.event.definition.prime;

import com.amazon.music.metrics.mts.MTSEvent;

/* JADX INFO: loaded from: classes4.dex */
abstract class PrimeDialogEvent extends MTSEvent {
    public PrimeDialogEvent(String str, String str2) {
        super(str);
        addEventAttributes(str2);
    }

    private void addEventAttributes(String str) {
        addAttribute("externalId", str);
    }
}
