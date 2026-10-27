package com.amazon.weblab.mobile.model;

import android.util.Log;
import com.amazon.weblab.mobile.repository.LazyJSONKeys;
import com.amazon.weblab.mobile.repository.LazyParser;
import org.json.JSONException;

/* JADX INFO: loaded from: classes5.dex */
public class LazyTreatmentAssignment extends TreatmentAssignment {
    boolean mCanTriggerInflated;
    boolean mDateModifiedInflated;
    private LazyParser.Inflator mInflator;
    boolean mKeepInCacheInflated;
    boolean mLockedInflated;
    boolean mSuggestedExpirationInflated;
    boolean mTreatmentInflated;
    boolean mVersionInflated;

    public LazyTreatmentAssignment(String str, LazyParser.Inflator inflator) {
        super(str);
        this.mInflator = inflator;
    }

    @Override // com.amazon.weblab.mobile.model.TreatmentAssignment
    public boolean canTrigger() {
        if (!this.mCanTriggerInflated) {
            try {
                try {
                    this.mCanTrigger = this.mInflator.getBool(this.mWeblab, LazyJSONKeys.CAN_TRIGGER);
                } catch (JSONException e) {
                    Log.e("MWAC", "canTrigger Exception", e);
                }
            } finally {
                this.mCanTriggerInflated = true;
            }
        }
        return this.mCanTrigger;
    }

    @Override // com.amazon.weblab.mobile.model.TreatmentAssignment
    public String getVersion() {
        if (!this.mVersionInflated) {
            try {
                try {
                    this.mVersion = this.mInflator.getString(this.mWeblab, LazyJSONKeys.VERSION);
                } catch (JSONException e) {
                    Log.e("MWAC", "getVersion Exception", e);
                }
            } finally {
                this.mVersionInflated = true;
            }
        }
        return this.mVersion;
    }

    @Override // com.amazon.weblab.mobile.model.TreatmentAssignment
    public boolean isLocked() {
        if (!this.mLockedInflated) {
            try {
                try {
                    this.mLocked = this.mInflator.getBool(this.mWeblab, LazyJSONKeys.IS_LOCKED);
                } catch (JSONException e) {
                    Log.e("MWAC", "isLocked Exception", e);
                }
            } finally {
                this.mLockedInflated = true;
            }
        }
        return this.mLocked;
    }

    @Override // com.amazon.weblab.mobile.model.TreatmentAssignment
    public String getTreatment() {
        if (!this.mTreatmentInflated) {
            try {
                try {
                    this.mTreatment = this.mInflator.getString(this.mWeblab, LazyJSONKeys.TREATMENT);
                } catch (JSONException e) {
                    Log.e("MWAC", "getTreatment Exception", e);
                }
            } finally {
                this.mTreatmentInflated = true;
            }
        }
        return this.mTreatment;
    }

    @Override // com.amazon.weblab.mobile.model.TreatmentAssignment
    public Long getDateModified() {
        if (!this.mDateModifiedInflated) {
            try {
                try {
                    this.mDateModified = Long.valueOf(this.mInflator.getLong(this.mWeblab, LazyJSONKeys.DATE_MODIFIED));
                } catch (JSONException e) {
                    Log.e("MWAC", "getDateModified Exception", e);
                }
            } finally {
                this.mDateModifiedInflated = true;
            }
        }
        return this.mDateModified;
    }

    @Override // com.amazon.weblab.mobile.model.TreatmentAssignment
    public Long getSuggestedExpiration() {
        if (!this.mSuggestedExpirationInflated) {
            try {
                try {
                    this.mSuggestedExpiration = Long.valueOf(this.mInflator.getLong(this.mWeblab, LazyJSONKeys.SUGGESTED_EXPIRATION));
                } catch (JSONException e) {
                    Log.e("MWAC", "getSuggestedExpiration Exception", e);
                }
            } finally {
                this.mSuggestedExpirationInflated = true;
            }
        }
        return this.mSuggestedExpiration;
    }

    @Override // com.amazon.weblab.mobile.model.TreatmentAssignment
    public long getKeepInCacheDateInMillis() {
        if (!this.mKeepInCacheInflated) {
            try {
                try {
                    this.mKeepInCacheDateInMillis = this.mInflator.getLong(this.mWeblab, LazyJSONKeys.KEEP_IN_CACHE);
                } catch (JSONException e) {
                    Log.e("MWAC", "getKeepInCacheDateInMillis Exception", e);
                }
            } finally {
                this.mKeepInCacheInflated = true;
            }
        }
        return this.mKeepInCacheDateInMillis;
    }
}
