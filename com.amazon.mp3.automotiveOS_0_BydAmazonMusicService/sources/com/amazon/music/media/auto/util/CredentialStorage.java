package com.amazon.music.media.auto.util;

import com.amazon.music.browse.BrowseContentSelector;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.marketplace.Marketplace;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CredentialStorage.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001:\u0002\u0012\u0013B\u0007¢\u0006\u0004\b\u0010\u0010\u0011R$\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\n\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/media/auto/util/CredentialStorage;", "", "Lcom/amazon/music/media/auto/util/CredentialStorage$Credentials;", "credentials", "Lcom/amazon/music/media/auto/util/CredentialStorage$Credentials;", "getCredentials", "()Lcom/amazon/music/media/auto/util/CredentialStorage$Credentials;", "setCredentials", "(Lcom/amazon/music/media/auto/util/CredentialStorage$Credentials;)V", "Lcom/amazon/music/media/auto/util/CredentialStorage$GoogleAssistantGatewayCredentials;", "googleAssistantGatewayCredentials", "Lcom/amazon/music/media/auto/util/CredentialStorage$GoogleAssistantGatewayCredentials;", "getGoogleAssistantGatewayCredentials", "()Lcom/amazon/music/media/auto/util/CredentialStorage$GoogleAssistantGatewayCredentials;", "setGoogleAssistantGatewayCredentials", "(Lcom/amazon/music/media/auto/util/CredentialStorage$GoogleAssistantGatewayCredentials;)V", "<init>", "()V", "Credentials", "GoogleAssistantGatewayCredentials", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public final class CredentialStorage {
    private Credentials credentials;
    private GoogleAssistantGatewayCredentials googleAssistantGatewayCredentials;

    public final Credentials getCredentials() {
        return this.credentials;
    }

    public final void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public final void setGoogleAssistantGatewayCredentials(GoogleAssistantGatewayCredentials googleAssistantGatewayCredentials) {
        this.googleAssistantGatewayCredentials = googleAssistantGatewayCredentials;
    }

    /* JADX INFO: compiled from: CredentialStorage.kt */
    @Metadata(bv = {}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u0002\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0019\u001a\u00020\u0002\u0012\u0006\u0010\u001d\u001a\u00020\u001c\u0012\u0006\u0010$\u001a\u00020#\u0012\b\b\u0002\u0010*\u001a\u00020\u0007¢\u0006\u0004\b/\u00100J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\"\u0010\t\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\"\u0010\u000f\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\"\u0010\u0013\u001a\u00020\u00128\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\"\u0010\u0019\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\n\u001a\u0004\b\u001a\u0010\f\"\u0004\b\u001b\u0010\u000eR\"\u0010\u001d\u001a\u00020\u001c8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\"\u0010$\u001a\u00020#8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\"\u0010*\u001a\u00020\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b*\u0010+\u001a\u0004\b*\u0010,\"\u0004\b-\u0010.¨\u00061"}, d2 = {"Lcom/amazon/music/media/auto/util/CredentialStorage$Credentials;", "", "", "toString", "", "hashCode", "other", "", "equals", Splash.PARAMS_DEVICE_TYPE, "Ljava/lang/String;", "getDeviceType", "()Ljava/lang/String;", "setDeviceType", "(Ljava/lang/String;)V", Splash.PARAMS_DEVICE_ID, "getDeviceId", "setDeviceId", "Ljava/util/Locale;", Splash.PARAMS_LOCALE, "Ljava/util/Locale;", "getLocale", "()Ljava/util/Locale;", "setLocale", "(Ljava/util/Locale;)V", Splash.PARAMS_MUSIC_TERRITORY, "getMusicTerritory", "setMusicTerritory", "Lcom/amazon/music/marketplace/Marketplace;", "marketPlace", "Lcom/amazon/music/marketplace/Marketplace;", "getMarketPlace", "()Lcom/amazon/music/marketplace/Marketplace;", "setMarketPlace", "(Lcom/amazon/music/marketplace/Marketplace;)V", "Lcom/amazon/music/browse/BrowseContentSelector;", "contentType", "Lcom/amazon/music/browse/BrowseContentSelector;", "getContentType", "()Lcom/amazon/music/browse/BrowseContentSelector;", "setContentType", "(Lcom/amazon/music/browse/BrowseContentSelector;)V", "isExplicitLanguageFilterEnabled", "Z", "()Z", "setExplicitLanguageFilterEnabled", "(Z)V", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Locale;Ljava/lang/String;Lcom/amazon/music/marketplace/Marketplace;Lcom/amazon/music/browse/BrowseContentSelector;Z)V", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class Credentials {
        private BrowseContentSelector contentType;
        private String deviceId;
        private String deviceType;
        private boolean isExplicitLanguageFilterEnabled;
        private Locale locale;
        private Marketplace marketPlace;
        private String musicTerritory;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Credentials)) {
                return false;
            }
            Credentials credentials = (Credentials) other;
            return Intrinsics.areEqual(this.deviceType, credentials.deviceType) && Intrinsics.areEqual(this.deviceId, credentials.deviceId) && Intrinsics.areEqual(this.locale, credentials.locale) && Intrinsics.areEqual(this.musicTerritory, credentials.musicTerritory) && Intrinsics.areEqual(this.marketPlace, credentials.marketPlace) && this.contentType == credentials.contentType && this.isExplicitLanguageFilterEnabled == credentials.isExplicitLanguageFilterEnabled;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v11, types: [int] */
        /* JADX WARN: Type inference failed for: r1v12 */
        /* JADX WARN: Type inference failed for: r1v13 */
        public int hashCode() {
            int iHashCode = ((((((((((this.deviceType.hashCode() * 31) + this.deviceId.hashCode()) * 31) + this.locale.hashCode()) * 31) + this.musicTerritory.hashCode()) * 31) + this.marketPlace.hashCode()) * 31) + this.contentType.hashCode()) * 31;
            boolean z = this.isExplicitLanguageFilterEnabled;
            ?? r1 = z;
            if (z) {
                r1 = 1;
            }
            return iHashCode + r1;
        }

        public String toString() {
            return "Credentials(deviceType=" + this.deviceType + ", deviceId=" + this.deviceId + ", locale=" + this.locale + ", musicTerritory=" + this.musicTerritory + ", marketPlace=" + this.marketPlace + ", contentType=" + this.contentType + ", isExplicitLanguageFilterEnabled=" + this.isExplicitLanguageFilterEnabled + ')';
        }

        public Credentials(String deviceType, String deviceId, Locale locale, String musicTerritory, Marketplace marketPlace, BrowseContentSelector contentType, boolean z) {
            Intrinsics.checkNotNullParameter(deviceType, "deviceType");
            Intrinsics.checkNotNullParameter(deviceId, "deviceId");
            Intrinsics.checkNotNullParameter(locale, "locale");
            Intrinsics.checkNotNullParameter(musicTerritory, "musicTerritory");
            Intrinsics.checkNotNullParameter(marketPlace, "marketPlace");
            Intrinsics.checkNotNullParameter(contentType, "contentType");
            this.deviceType = deviceType;
            this.deviceId = deviceId;
            this.locale = locale;
            this.musicTerritory = musicTerritory;
            this.marketPlace = marketPlace;
            this.contentType = contentType;
            this.isExplicitLanguageFilterEnabled = z;
        }

        public final String getDeviceType() {
            return this.deviceType;
        }

        public final String getDeviceId() {
            return this.deviceId;
        }

        public final Locale getLocale() {
            return this.locale;
        }

        public final String getMusicTerritory() {
            return this.musicTerritory;
        }

        public final Marketplace getMarketPlace() {
            return this.marketPlace;
        }

        public final BrowseContentSelector getContentType() {
            return this.contentType;
        }
    }

    /* JADX INFO: compiled from: CredentialStorage.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007¢\u0006\u0004\b\u000e\u0010\rJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\"\u0010\t\u001a\u00020\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\t\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/media/auto/util/CredentialStorage$GoogleAssistantGatewayCredentials;", "", "", "toString", "", "hashCode", "other", "", "equals", "isExplicitLanguageFilterEnabled", "Z", "()Z", "setExplicitLanguageFilterEnabled", "(Z)V", "<init>", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class GoogleAssistantGatewayCredentials {
        private boolean isExplicitLanguageFilterEnabled;

        public GoogleAssistantGatewayCredentials() {
            this(false, 1, null);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof GoogleAssistantGatewayCredentials) && this.isExplicitLanguageFilterEnabled == ((GoogleAssistantGatewayCredentials) other).isExplicitLanguageFilterEnabled;
        }

        public int hashCode() {
            boolean z = this.isExplicitLanguageFilterEnabled;
            if (z) {
                return 1;
            }
            return z ? 1 : 0;
        }

        public String toString() {
            return "GoogleAssistantGatewayCredentials(isExplicitLanguageFilterEnabled=" + this.isExplicitLanguageFilterEnabled + ')';
        }

        public GoogleAssistantGatewayCredentials(boolean z) {
            this.isExplicitLanguageFilterEnabled = z;
        }

        public /* synthetic */ GoogleAssistantGatewayCredentials(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? false : z);
        }
    }
}
