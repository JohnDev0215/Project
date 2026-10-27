package com.amazon.mp3.account;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.config.ApplicationAttributes;
import com.amazon.mp3.config.AssociateTag;
import com.amazon.music.destination.parser.ParserUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/* JADX INFO: loaded from: classes3.dex */
public class DeviceAttributes {
    private final Context context;

    public DeviceAttributes(final Context context) {
        this.context = context;
    }

    public String getDeviceType() {
        return AccountCredentialStorage.get(this.context).getDeviceType();
    }

    public String getDeviceId() {
        return AccountCredentialStorage.get(this.context).getDeviceId();
    }

    public String getAppVersion() {
        return ApplicationAttributes.getInstance(this.context).getVersion();
    }

    public String getPreloadAssociateTagOrDefault() {
        String associateTag = AssociateTag.getInstance(this.context).getAssociateTag();
        if (TextUtils.isEmpty(associateTag)) {
            return "";
        }
        try {
            return URLEncoder.encode(associateTag, ParserUtil.UTF_8);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unable to encode associate tag.", e);
        }
    }
}
