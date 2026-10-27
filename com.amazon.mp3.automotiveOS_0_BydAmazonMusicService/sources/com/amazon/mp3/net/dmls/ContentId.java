package com.amazon.mp3.net.dmls;

import com.amazon.mp3.net.dmls.DMLSExceptions;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class ContentId {
    private final String mIdentifier;
    private final IdentifierType mIdentifierType;

    public ContentId() {
        this.mIdentifierType = IdentifierType.UNDEFINED;
        this.mIdentifier = null;
    }

    public ContentId(IdentifierType type, String identifier) throws DMLSExceptions.InvalidContentIdException {
        if (type == null || identifier == null) {
            throw new DMLSExceptions.InvalidContentIdException();
        }
        this.mIdentifierType = type;
        this.mIdentifier = identifier;
    }

    public IdentifierType getType() {
        return this.mIdentifierType;
    }

    public String getId() {
        return this.mIdentifier;
    }

    public String toString() {
        return "ID: " + getId() + ", Type: " + getType();
    }

    public static ContentId createFromJSON(JSONObject json) throws JSONException, DMLSExceptions.InvalidContentIdException {
        String string = json.getString("identifierType");
        return new ContentId(IdentifierType.valueOf(string), json.getString("identifier"));
    }

    public JSONObject toJSON() throws JSONException {
        return new JSONObject().put("identifier", this.mIdentifier).put("identifierType", this.mIdentifierType);
    }

    public int hashCode() {
        String str = this.mIdentifier;
        int iHashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
        IdentifierType identifierType = this.mIdentifierType;
        return iHashCode + (identifierType != null ? identifierType.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ContentId contentId = (ContentId) obj;
        String str = this.mIdentifier;
        if (str == null) {
            if (contentId.mIdentifier != null) {
                return false;
            }
        } else if (!str.equals(contentId.mIdentifier)) {
            return false;
        }
        return this.mIdentifierType == contentId.mIdentifierType;
    }
}
