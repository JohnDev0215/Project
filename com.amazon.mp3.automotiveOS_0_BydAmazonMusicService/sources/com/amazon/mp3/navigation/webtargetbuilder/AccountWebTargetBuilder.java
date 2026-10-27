package com.amazon.mp3.navigation.webtargetbuilder;

import android.content.Context;
import android.util.Base64;
import com.amazon.mp3.account.AccountInfo;
import com.amazon.mp3.account.DeviceAttributes;
import com.amazon.mp3.web.webtarget.WebTarget;
import com.amazon.mp3.web.webtargetbuilder.AccountWebTargetEndpoints;
import com.amazon.mp3.web.webtargetbuilder.BaseWebTargetBuilder;
import com.amazon.mp3.web.webtargetbuilder.WebTargetEndpoints;
import com.amazon.music.inappmessaging.internal.model.Splash;

/* JADX INFO: loaded from: classes3.dex */
public class AccountWebTargetBuilder extends BaseWebTargetBuilder {
    public AccountWebTargetBuilder(final Context context) {
        this(new AccountInfo(context), new AccountWebTargetEndpoints(), new DeviceAttributes(context));
    }

    public AccountWebTargetBuilder(final AccountInfo accountInfo, final WebTargetEndpoints endpoints, final DeviceAttributes deviceAttributes) {
        super(accountInfo, endpoints, deviceAttributes);
        addAdditionalParams();
    }

    private void addAdditionalParams() {
        this.uriBuilder.appendQueryParameter(Splash.PARAMS_MUSIC_TERRITORY, this.accountInfo.getMusicTerritory());
        this.uriBuilder.appendQueryParameter("successUrl", encode("/result=Success"));
        this.uriBuilder.appendQueryParameter("cancelUrl", encode("/result=Cancel"));
    }

    String encode(final String inputString) {
        return Base64.encodeToString(inputString.getBytes(), 0).substring(0, r3.length() - 1);
    }

    @Override // com.amazon.mp3.web.webtargetbuilder.BaseWebTargetBuilder, com.amazon.mp3.web.webtargetbuilder.WebTargetBuilder
    public WebTarget build() {
        return super.build();
    }
}
