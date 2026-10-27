package com.amazon.music.account;

import com.amazon.music.account.User;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.marketplace.MarketplaceResolver;
import java.util.HashSet;
import java.util.Iterator;
import org.apache.commons.lang3.Validate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
class UserSerializer {
    private static final Logger LOG = LoggerFactory.getLogger(UserSerializer.class.getSimpleName());

    UserSerializer() {
    }

    public String serialize(User user) {
        Validate.notNull(user);
        try {
            return userToJson(user).toString();
        } catch (JSONException e) {
            LOG.error("Unable to serialize User", (Throwable) e);
            return null;
        }
    }

    public User deserialize(String str) {
        Validate.notNull(str);
        try {
            return jsonToUser(new JSONObject(str));
        } catch (JSONException e) {
            LOG.error("Unable to deserialize User", (Throwable) e);
            return null;
        }
    }

    private JSONObject userToJson(User user) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (user.getCountryOfResidence() != null) {
            jSONObject.put("countryOfResidence", user.getCountryOfResidence().getId());
        }
        Marketplace libraryHomeMarketplace = user.getLibraryHomeMarketplace();
        if (libraryHomeMarketplace != null) {
            jSONObject.put("libraryHomeMarketplace", libraryHomeMarketplace.getId());
        }
        Marketplace termsOfUseMarketplace = user.getTermsOfUseMarketplace();
        if (termsOfUseMarketplace != null) {
            jSONObject.put("termsOfUseMarketplace", termsOfUseMarketplace.getId());
        }
        jSONObject.put("customerId", user.getCustomerId());
        jSONObject.put("musicAccountStatus", user.getMusicAccountStatus().toString());
        jSONObject.put("musicTerritoryOfResidence", user.getMusicTerritoryOfResidence());
        jSONObject.put("acceptedTermsOfUse", user.hasAcceptedTermsOfUse());
        jSONObject.put("cloudPlayerAccountExists", user.hasCloudPlayerAccount());
        jSONObject.put("accountVerified", user.isAccountVerified());
        jSONObject.put("inKatanaMarketplace", user.isInKatanaMarketplace());
        jSONObject.put("inNightMarketplace", user.isInNightwingMarketplace());
        jSONObject.put("inSonicRushMusicTerritory", user.isInSonicRushMusicTerritory());
        jSONObject.put("inPrimeMarketplace", user.isInPrimeMarketplace());
        jSONObject.put("inHawkfireMarketplace", user.isInHawkfireMarketplace());
        jSONObject.put("inBundesligaMarketplace", user.isInBundesligaMarketplace());
        jSONObject.put("cloudAccountStatus", user.getCloudAccountStatus().toString());
        jSONObject.put("explicitFilterEnabledOnDevice", user.isExplicitFilterEnabledOnDevice());
        jSONObject.put("isTargetedAdsEnabled", user.isTargetedAdsEnabled());
        jSONObject.put("isCCBAOptOut", user.isCCBAOptOut());
        jSONObject.put("creationDate", user.getCreationDate());
        JSONArray jSONArray = new JSONArray();
        Iterator<User.Benefit> it = user.getBenefits().iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().name());
        }
        jSONObject.put("benefits", jSONArray);
        return jSONObject;
    }

    private User jsonToUser(JSONObject jSONObject) throws JSONException {
        String strOptString = jSONObject.optString("customerId", null);
        User.MusicAccountStatus musicAccountStatusFromString = User.MusicAccountStatus.fromString(jSONObject.optString("musicAccountStatus", User.MusicAccountStatus.NOT_CREATED.toString()));
        String strOptString2 = jSONObject.optString("countryOfResidence", null);
        Marketplace marketplaceLookupById = strOptString2 == null ? null : MarketplaceResolver.lookupById(strOptString2);
        String strOptString3 = jSONObject.optString("libraryHomeMarketplace", null);
        Marketplace marketplaceLookupById2 = strOptString3 == null ? null : MarketplaceResolver.lookupById(strOptString3);
        String strOptString4 = jSONObject.optString("termsOfUseMarketplace", null);
        Marketplace marketplaceLookupById3 = strOptString4 == null ? null : MarketplaceResolver.lookupById(strOptString4);
        String strOptString5 = jSONObject.optString("musicTerritoryOfResidence", null);
        boolean zOptBoolean = jSONObject.optBoolean("acceptedTermsOfUse", false);
        boolean zOptBoolean2 = jSONObject.optBoolean("cloudPlayerAccountExists", false);
        boolean zOptBoolean3 = jSONObject.optBoolean("accountVerified", false);
        boolean zOptBoolean4 = jSONObject.optBoolean("inKatanaMarketplace", false);
        boolean zOptBoolean5 = jSONObject.optBoolean("inNightMarketplace", false);
        boolean zOptBoolean6 = jSONObject.optBoolean("inSonicRushMusicTerritory", false);
        boolean zOptBoolean7 = jSONObject.optBoolean("inPrimeMarketplace", false);
        boolean zOptBoolean8 = jSONObject.optBoolean("inHawkfireMarketplace", false);
        boolean zOptBoolean9 = jSONObject.optBoolean("inBundesligaMarketplace", false);
        User.CloudAccountStatus cloudAccountStatusFromString = User.CloudAccountStatus.fromString(jSONObject.optString("cloudAccountStatus", User.CloudAccountStatus.UNKNOWN.toString()));
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("benefits");
        for (int i = 0; i < jSONArray.length(); i++) {
            hashSet.add(User.Benefit.valueOf(jSONArray.getString(i)));
        }
        return new User(strOptString, musicAccountStatusFromString, marketplaceLookupById, marketplaceLookupById2, marketplaceLookupById3, strOptString5, zOptBoolean, zOptBoolean2, zOptBoolean3, zOptBoolean4, zOptBoolean5, zOptBoolean6, zOptBoolean7, zOptBoolean8, zOptBoolean9, cloudAccountStatusFromString, hashSet, jSONObject.optBoolean("explicitFilterEnabledOnDevice", false), jSONObject.optBoolean("isTargetedAdsEnabled", false), jSONObject.optBoolean("isCCBAOptOut", false), jSONObject.optLong("creationDate", 0L));
    }
}
