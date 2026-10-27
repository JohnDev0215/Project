package com.amazon.music.inappmessaging.external.model;

import com.amazon.music.inappmessaging.external.WeblabHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class DynamicMessageTemplate {
    private final String name;
    private final String version;
    public static DynamicMessageTemplate DIGITAL_MUSIC_DYNMSG_RIBBON = new DynamicMessageTemplate("digital-music-dynmsg-ribbon", "1.0.0");
    public static DynamicMessageTemplate DM_DYNMSG_FULL_SCREEN_BAUHAUS_TEMPLATE = new DynamicMessageTemplate("dm-dynmsg-full-screen-bauhaus-modal", "1.0.0");
    public static DynamicMessageTemplate DM_DYNMSG_FULL_SCREEN_DEEPLINK = new DynamicMessageTemplate("dm-dynmsg-full-screen-deeplink", "1.0.0");
    public static DynamicMessageTemplate DM_DYNMSG_LEGACY_TEMPLATE = new DynamicMessageTemplate("dm-dynmsg-legacy-modal", "1.0.0");
    public static DynamicMessageTemplate DM_DYNMSG_BEHAVIORAL_ADS_TEMPLATE = new DynamicMessageTemplate("digital-music-iam-behavioral-ads-notification", "1.0.0");
    public static DynamicMessageTemplate DIGITAL_MUSIC_IAM_EVERGREEN = new DynamicMessageTemplate("digital-music-iam-evergreen", "1.0.0");
    public static DynamicMessageTemplate DM_ENGAGEMENT_1CTA_NO_ACTION = new DynamicMessageTemplate("digital-music-iam-engagement-template-1-cta-no-action", "1.0.0");
    public static DynamicMessageTemplate DM_3P_HOSTED_IAM_TEMPLATE = new DynamicMessageTemplate("third-party-hosted-iam-template", "1.0.0");

    private DynamicMessageTemplate(final String name, final String version) {
        this.name = name;
        this.version = version;
    }

    public String getName() {
        return this.name;
    }

    public String getVersion() {
        return this.version;
    }

    public static List<DynamicMessageTemplate> getAllTemplates() {
        ArrayList arrayList = new ArrayList(Arrays.asList(DIGITAL_MUSIC_DYNMSG_RIBBON, DM_DYNMSG_FULL_SCREEN_BAUHAUS_TEMPLATE, DM_DYNMSG_FULL_SCREEN_DEEPLINK, DM_DYNMSG_LEGACY_TEMPLATE, DM_DYNMSG_BEHAVIORAL_ADS_TEMPLATE));
        if (WeblabHelper.getInstance().isSplashV2Enabled()) {
            arrayList.addAll(Arrays.asList(DIGITAL_MUSIC_IAM_EVERGREEN, DM_ENGAGEMENT_1CTA_NO_ACTION, DM_3P_HOSTED_IAM_TEMPLATE));
        }
        return arrayList;
    }

    public static List<DynamicMessageTemplate> getFTUTemplates() {
        return Arrays.asList(DIGITAL_MUSIC_IAM_EVERGREEN, DM_ENGAGEMENT_1CTA_NO_ACTION, DM_3P_HOSTED_IAM_TEMPLATE);
    }
}
