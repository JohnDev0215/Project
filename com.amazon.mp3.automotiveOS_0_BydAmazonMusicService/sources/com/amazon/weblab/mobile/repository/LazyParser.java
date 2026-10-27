package com.amazon.weblab.mobile.repository;

import com.amazon.weblab.mobile.model.LazyTreatmentAssignment;
import com.amazon.weblab.mobile.model.TreatmentAssignment;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;

/* JADX INFO: loaded from: classes5.dex */
public class LazyParser {
    private String mAppVersion;
    private String mMarketplaceId;
    private byte[] mMemoryCache;
    private Map<String, int[]> mNodeIndexes;
    private String mSessionId;
    private String mWeblabClientIdentifier;
    private Inflator mInflator = new Inflator();
    private Map<String, TreatmentAssignment> mTreatmentAssignments = new HashMap();
    private BytesUtils mUtils = new BytesUtils();

    LazyParser(String str) {
        this.mWeblabClientIdentifier = str;
    }

    public String getAppVersion() {
        return this.mAppVersion;
    }

    public String getSessionId() {
        return this.mSessionId;
    }

    public String getMarketplaceId() {
        return this.mMarketplaceId;
    }

    public Map<String, TreatmentAssignment> getTreatmentAssignments() {
        return this.mTreatmentAssignments;
    }

    public void parse(File file) throws Throwable {
        readFileBytes(file);
        parseEmbeddedIdentifiers();
        buildIndex(0);
        for (Map.Entry<String, int[]> entry : this.mNodeIndexes.entrySet()) {
            this.mTreatmentAssignments.put(entry.getKey(), new LazyTreatmentAssignment(entry.getKey(), this.mInflator));
        }
    }

    private void readFileBytes(File file) throws Throwable {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            this.mMemoryCache = new byte[(int) file.length()];
            fileInputStream = new FileInputStream(file);
        } catch (Throwable th) {
            th = th;
        }
        try {
            fileInputStream.read(this.mMemoryCache);
            fileInputStream.close();
        } catch (Throwable th2) {
            th = th2;
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            throw th;
        }
    }

    private void parseEmbeddedIdentifiers() throws JSONException {
        String stringValue = this.mUtils.getStringValue(this.mMemoryCache, LazyJSONKeys.APP_VERSION, 0);
        this.mAppVersion = stringValue;
        if (stringValue == null) {
            throw new JSONException("app_version not found");
        }
        String stringValue2 = this.mUtils.getStringValue(this.mMemoryCache, LazyJSONKeys.SESSION_ID, 0);
        this.mSessionId = stringValue2;
        if (stringValue2 == null) {
            throw new JSONException("session_id not found");
        }
        String stringValue3 = this.mUtils.getStringValue(this.mMemoryCache, LazyJSONKeys.MARKETPLACE_ID, 0);
        this.mMarketplaceId = stringValue3;
        if (stringValue3 == null) {
            throw new JSONException("marketplace_id not found");
        }
    }

    private void buildIndex(int i) throws JSONException {
        this.mNodeIndexes = new HashMap();
        int iFindEndPositionOfString = this.mUtils.findEndPositionOfString(this.mMemoryCache, LazyJSONKeys.TREATMENT_ASSIGNMENTS, i);
        if (iFindEndPositionOfString == -1) {
            throw new JSONException("Cannot find treatment_assignments key");
        }
        int iFindNextDelimiter = this.mUtils.findNextDelimiter(this.mMemoryCache, LazyJSONKeys.LEFT_BRACKET, iFindEndPositionOfString) + 1;
        if (iFindNextDelimiter == -1) {
            throw new JSONException("Cannot find left bracket '[' for the treatment assignment array");
        }
        int iFindNextDelimiter2 = this.mUtils.findNextDelimiter(this.mMemoryCache, LazyJSONKeys.RIGHT_BRACKET, iFindNextDelimiter);
        if (iFindNextDelimiter2 == -1) {
            throw new JSONException("Cannot find right bracket ']' for the treatment assignment array");
        }
        while (iFindNextDelimiter < iFindNextDelimiter2) {
            int iFindNextDelimiter3 = this.mUtils.findNextDelimiter(this.mMemoryCache, LazyJSONKeys.LEFT_CURLY_BRACKET, iFindNextDelimiter);
            if (iFindNextDelimiter3 == -1) {
                return;
            }
            int i2 = iFindNextDelimiter3 + 1;
            int[] rangeOfStringValue = this.mUtils.getRangeOfStringValue(this.mMemoryCache, LazyJSONKeys.WEBLAB, i2);
            if (rangeOfStringValue == null) {
                throw new JSONException("Weblab must have a name");
            }
            String stringFromRange = this.mUtils.getStringFromRange(this.mMemoryCache, rangeOfStringValue);
            int[] iArr = {i2, this.mUtils.findNextDelimiter(this.mMemoryCache, LazyJSONKeys.RIGHT_CURLY_BRACKET, rangeOfStringValue[1])};
            this.mNodeIndexes.put(stringFromRange, iArr);
            iFindNextDelimiter = iArr[1];
        }
    }

    public class Inflator {
        public Inflator() {
        }

        public String getString(String str, byte[] bArr) throws JSONException {
            if (!LazyParser.this.mNodeIndexes.containsKey(str)) {
                return null;
            }
            int[] iArr = (int[]) LazyParser.this.mNodeIndexes.get(str);
            return LazyParser.this.mUtils.getStringValue(LazyParser.this.mMemoryCache, bArr, iArr[0], iArr[1]);
        }

        public boolean getBool(String str, byte[] bArr) throws JSONException {
            if (LazyParser.this.mNodeIndexes.containsKey(str)) {
                return LazyParser.this.mUtils.getBooleanValue(LazyParser.this.mMemoryCache, bArr, ((int[]) LazyParser.this.mNodeIndexes.get(str))[0]);
            }
            throw new JSONException("Property not found");
        }

        public long getLong(String str, byte[] bArr) throws JSONException {
            if (LazyParser.this.mNodeIndexes.containsKey(str)) {
                return LazyParser.this.mUtils.getLongValue(LazyParser.this.mMemoryCache, bArr, ((int[]) LazyParser.this.mNodeIndexes.get(str))[0]);
            }
            throw new JSONException("Property not found");
        }
    }
}
