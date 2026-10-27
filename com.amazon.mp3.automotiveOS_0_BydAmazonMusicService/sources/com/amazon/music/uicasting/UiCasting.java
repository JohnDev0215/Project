package com.amazon.music.uicasting;

import com.amazon.music.casting.Casting;
import com.amazon.music.casting.CastingException;

/* JADX INFO: loaded from: classes4.dex */
public class UiCasting {
    private final Casting casting;
    private final UiConverter uiConverter = new UiConverter();

    public UiCasting(Casting casting) {
        this.casting = casting;
    }

    public UiDeviceListResult getAmazonDeviceList() throws CastingException {
        return UiConverter.resultToUiResult(this.casting.getAmazonDeviceList());
    }

    public UiChromecastDeviceListFeed getUiChromecastDeviceListFeed() {
        return new UiChromecastDeviceListFeed(this.casting);
    }
}
