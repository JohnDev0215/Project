package com.amazon.mp3.account.preauth;

import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.marketplace.MusicTerritory;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PreAuthUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tR\u001e\u0010\u0003\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/account/preauth/PreAuthUtil;", "", "()V", "LOCALE_COUNTRY_TO_MARKETPLACE", "Ljava/util/HashMap;", "", "Lcom/amazon/music/marketplace/Marketplace;", "getMarketplaceFromLocaleCountry", Splash.PARAMS_LOCALE, "Ljava/util/Locale;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PreAuthUtil {
    public static final PreAuthUtil INSTANCE = new PreAuthUtil();
    private static final HashMap<String, Marketplace> LOCALE_COUNTRY_TO_MARKETPLACE = new HashMap<String, Marketplace>() { // from class: com.amazon.mp3.account.preauth.PreAuthUtil$LOCALE_COUNTRY_TO_MARKETPLACE$1
        {
            put(MusicTerritory.US.value, Marketplace.USA);
            put(MusicTerritory.UK.value, Marketplace.UK);
            put(MusicTerritory.GERMANY.value, Marketplace.GERMANY);
            put(MusicTerritory.JAPAN.value, Marketplace.JAPAN);
            put(MusicTerritory.FRANCE.value, Marketplace.FRANCE);
            put(MusicTerritory.ITALY.value, Marketplace.ITALY);
            put(MusicTerritory.SPAIN.value, Marketplace.SPAIN);
            String str = MusicTerritory.CANADA.value;
            Marketplace marketplace = Marketplace.ROW_NA;
            put(str, marketplace);
            String str2 = MusicTerritory.AUSTRALIA.value;
            Marketplace marketplace2 = Marketplace.ROW_FE;
            put(str2, marketplace2);
            put(MusicTerritory.NEW_ZEALAND.value, marketplace2);
            put(MusicTerritory.MEXICO.value, marketplace);
            put(MusicTerritory.BRAZIL.value, marketplace);
            String str3 = MusicTerritory.INDIA.value;
            Marketplace marketplace3 = Marketplace.ROE_EU;
            put(str3, marketplace3);
            put(MusicTerritory.SAUDI_ARABIA.value, marketplace3);
            put(MusicTerritory.EGYPT.value, marketplace3);
            put(MusicTerritory.UAE.value, marketplace3);
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ boolean containsKey(Object obj) {
            if (obj == null ? true : obj instanceof String) {
                return containsKey((String) obj);
            }
            return false;
        }

        public /* bridge */ boolean containsKey(String str) {
            return super.containsKey((Object) str);
        }

        public /* bridge */ boolean containsValue(Marketplace marketplace) {
            return super.containsValue((Object) marketplace);
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ boolean containsValue(Object obj) {
            if (obj == null ? true : obj instanceof Marketplace) {
                return containsValue((Marketplace) obj);
            }
            return false;
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ Set<Map.Entry<String, Marketplace>> entrySet() {
            return getEntries();
        }

        public /* bridge */ Set<Map.Entry<String, Marketplace>> getEntries() {
            return super.entrySet();
        }

        public /* bridge */ Set<String> getKeys() {
            return super.keySet();
        }

        public /* bridge */ int getSize() {
            return super.size();
        }

        public /* bridge */ Collection<Marketplace> getValues() {
            return super.values();
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ Set<String> keySet() {
            return getKeys();
        }

        @Override // java.util.HashMap, java.util.Map
        public final /* bridge */ boolean remove(Object obj, Object obj2) {
            if (!(obj == null ? true : obj instanceof String)) {
                return false;
            }
            if (obj2 != null ? obj2 instanceof Marketplace : true) {
                return remove((String) obj, (Marketplace) obj2);
            }
            return false;
        }

        public /* bridge */ boolean remove(String str, Marketplace marketplace) {
            return super.remove((Object) str, (Object) marketplace);
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ int size() {
            return getSize();
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ Collection<Marketplace> values() {
            return getValues();
        }
    };

    private PreAuthUtil() {
    }

    public final Marketplace getMarketplaceFromLocaleCountry(Locale locale) {
        Marketplace marketplace = LOCALE_COUNTRY_TO_MARKETPLACE.get(locale == null ? null : locale.getCountry());
        if (marketplace != null) {
            return marketplace;
        }
        Marketplace USA = Marketplace.USA;
        Intrinsics.checkNotNullExpressionValue(USA, "USA");
        return USA;
    }
}
