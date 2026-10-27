package com.amazon.music.browse.util;

import com.amazon.musicensembleservice.AllowedParentalControls;

/* JADX INFO: loaded from: classes4.dex */
public class ParentalControlsUtil {
    public static AllowedParentalControls getParentalControls(boolean z) {
        AllowedParentalControls allowedParentalControls = new AllowedParentalControls();
        allowedParentalControls.setHasExplicitLanguage(Boolean.valueOf(!z));
        return allowedParentalControls;
    }

    public static com.amazon.musicensembleservice.brush.AllowedParentalControls getBrushParentalControls(boolean z) {
        com.amazon.musicensembleservice.brush.AllowedParentalControls allowedParentalControls = new com.amazon.musicensembleservice.brush.AllowedParentalControls();
        allowedParentalControls.setHasExplicitLanguage(Boolean.valueOf(!z));
        return allowedParentalControls;
    }
}
