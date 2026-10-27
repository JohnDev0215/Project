package com.amazon.mp3.environment.url;

import com.amazon.music.featuregating.rules.URLConfigurationValue;
import java.net.URL;
import kotlin.Metadata;

/* JADX INFO: compiled from: ConfigurableURLs.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u0004\u0018\u00010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/environment/url/ConfigurableURLs;", "", "key", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getKey", "()Ljava/lang/String;", "getValue", "Ljava/net/URL;", "IVY_LEARN_MORE_URL", "MUSIC_HD_LEARN_MORE_URL", "LEGAL_TERMS_OF_USE_URL", "LEGAL_ALEXA_DEVICE_TERMS", "LEGAL_PRIVACY_NOTES_URL", "LEGAL_AD_PRIVACY_CHOICES_URL", "LEGAL_ATTRIBUTION_NOTICES", "LEGAL_CHILDREN_PRIVACY_NOTICE", "LEGAL_ACCOUNT_DELETION", "COOKIE_CONSENT_PREFERENCE_URL", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum ConfigurableURLs {
    IVY_LEARN_MORE_URL("ivy_learn_more_url"),
    MUSIC_HD_LEARN_MORE_URL("musichd_learn_more_url"),
    LEGAL_TERMS_OF_USE_URL("url_legal_terms_of_use"),
    LEGAL_ALEXA_DEVICE_TERMS("url_legal_alexa_device_terms"),
    LEGAL_PRIVACY_NOTES_URL("url_legal_privacy_notes"),
    LEGAL_AD_PRIVACY_CHOICES_URL("url_legal_ad_privacy_choices"),
    LEGAL_ATTRIBUTION_NOTICES("url_legal_attribution_notices"),
    LEGAL_CHILDREN_PRIVACY_NOTICE("url_legal_children_privacy_notice"),
    LEGAL_ACCOUNT_DELETION("url_legal_account_deletion"),
    COOKIE_CONSENT_PREFERENCE_URL("cookie_consent_preference_url");

    private final String key;

    ConfigurableURLs(String str) {
        this.key = str;
    }

    public final String getKey() {
        return this.key;
    }

    public final URL getValue() {
        return new URLConfigurationValue(this.key).getValue();
    }
}
