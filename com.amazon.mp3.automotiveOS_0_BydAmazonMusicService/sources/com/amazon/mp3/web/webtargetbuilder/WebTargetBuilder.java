package com.amazon.mp3.web.webtargetbuilder;

import com.amazon.mp3.web.webtarget.WebTarget;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public interface WebTargetBuilder {
    WebTarget build();

    WebTargetBuilder withQueryParamsToForward(Map<String, String> queryParameters);
}
