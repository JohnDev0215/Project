package com.amazon.musicensembleservice.brush;

import com.amazon.musicensembleservice.RequestIdentity;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class PageRequest extends RequestIdentity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.PageRequest");
    private AllowedParentalControls allowedParentalControls;
    private List<String> allowedParentalControlsString;
    private String artistVideoStoryEntityAsin;
    private String browseId;
    private String campaignsXml;
    private List<String> contentFeatures;
    private Integer count;
    private Integer countOfEntitiesPerWidget;
    private Boolean debug;
    private String ipAddress;
    private List<String> languagesOfPerformance;
    private String locale;
    private String nextToken;
    private Integer offset;
    private Boolean stub;
    private Boolean testTraffic;
    private String upsellContent;
    private String uri;
    private ValidationPayload validationPayload;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicensembleservice.RequestIdentity, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated RequestIdentity requestIdentity) {
        if (requestIdentity == null) {
            return -1;
        }
        if (requestIdentity == this) {
            return 0;
        }
        if (!(requestIdentity instanceof PageRequest)) {
            return 1;
        }
        PageRequest pageRequest = (PageRequest) requestIdentity;
        String ipAddress = getIpAddress();
        String ipAddress2 = pageRequest.getIpAddress();
        if (ipAddress != ipAddress2) {
            if (ipAddress == null) {
                return -1;
            }
            if (ipAddress2 == null) {
                return 1;
            }
            int iCompareTo = ipAddress.compareTo(ipAddress2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Integer count = getCount();
        Integer count2 = pageRequest.getCount();
        if (count != count2) {
            if (count == null) {
                return -1;
            }
            if (count2 == null) {
                return 1;
            }
            int iCompareTo2 = count.compareTo(count2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        List<String> allowedParentalControlsString = getAllowedParentalControlsString();
        List<String> allowedParentalControlsString2 = pageRequest.getAllowedParentalControlsString();
        if (allowedParentalControlsString != allowedParentalControlsString2) {
            if (allowedParentalControlsString == null) {
                return -1;
            }
            if (allowedParentalControlsString2 == null) {
                return 1;
            }
            if (allowedParentalControlsString instanceof Comparable) {
                int iCompareTo3 = ((Comparable) allowedParentalControlsString).compareTo(allowedParentalControlsString2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!allowedParentalControlsString.equals(allowedParentalControlsString2)) {
                int iHashCode = allowedParentalControlsString.hashCode();
                int iHashCode2 = allowedParentalControlsString2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        ValidationPayload validationPayload = getValidationPayload();
        ValidationPayload validationPayload2 = pageRequest.getValidationPayload();
        if (validationPayload != validationPayload2) {
            if (validationPayload == null) {
                return -1;
            }
            if (validationPayload2 == null) {
                return 1;
            }
            int iCompareTo4 = validationPayload.compareTo(validationPayload2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String campaignsXml = getCampaignsXml();
        String campaignsXml2 = pageRequest.getCampaignsXml();
        if (campaignsXml != campaignsXml2) {
            if (campaignsXml == null) {
                return -1;
            }
            if (campaignsXml2 == null) {
                return 1;
            }
            int iCompareTo5 = campaignsXml.compareTo(campaignsXml2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        Boolean boolIsTestTraffic = isTestTraffic();
        Boolean boolIsTestTraffic2 = pageRequest.isTestTraffic();
        if (boolIsTestTraffic != boolIsTestTraffic2) {
            if (boolIsTestTraffic == null) {
                return -1;
            }
            if (boolIsTestTraffic2 == null) {
                return 1;
            }
            int iCompareTo6 = boolIsTestTraffic.compareTo(boolIsTestTraffic2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String locale = getLocale();
        String locale2 = pageRequest.getLocale();
        if (locale != locale2) {
            if (locale == null) {
                return -1;
            }
            if (locale2 == null) {
                return 1;
            }
            int iCompareTo7 = locale.compareTo(locale2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String browseId = getBrowseId();
        String browseId2 = pageRequest.getBrowseId();
        if (browseId != browseId2) {
            if (browseId == null) {
                return -1;
            }
            if (browseId2 == null) {
                return 1;
            }
            int iCompareTo8 = browseId.compareTo(browseId2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        Boolean boolIsDebug = isDebug();
        Boolean boolIsDebug2 = pageRequest.isDebug();
        if (boolIsDebug != boolIsDebug2) {
            if (boolIsDebug == null) {
                return -1;
            }
            if (boolIsDebug2 == null) {
                return 1;
            }
            int iCompareTo9 = boolIsDebug.compareTo(boolIsDebug2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        List<String> languagesOfPerformance = getLanguagesOfPerformance();
        List<String> languagesOfPerformance2 = pageRequest.getLanguagesOfPerformance();
        if (languagesOfPerformance != languagesOfPerformance2) {
            if (languagesOfPerformance == null) {
                return -1;
            }
            if (languagesOfPerformance2 == null) {
                return 1;
            }
            if (languagesOfPerformance instanceof Comparable) {
                int iCompareTo10 = ((Comparable) languagesOfPerformance).compareTo(languagesOfPerformance2);
                if (iCompareTo10 != 0) {
                    return iCompareTo10;
                }
            } else if (!languagesOfPerformance.equals(languagesOfPerformance2)) {
                int iHashCode3 = languagesOfPerformance.hashCode();
                int iHashCode4 = languagesOfPerformance2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        String nextToken = getNextToken();
        String nextToken2 = pageRequest.getNextToken();
        if (nextToken != nextToken2) {
            if (nextToken == null) {
                return -1;
            }
            if (nextToken2 == null) {
                return 1;
            }
            int iCompareTo11 = nextToken.compareTo(nextToken2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        Integer countOfEntitiesPerWidget = getCountOfEntitiesPerWidget();
        Integer countOfEntitiesPerWidget2 = pageRequest.getCountOfEntitiesPerWidget();
        if (countOfEntitiesPerWidget != countOfEntitiesPerWidget2) {
            if (countOfEntitiesPerWidget == null) {
                return -1;
            }
            if (countOfEntitiesPerWidget2 == null) {
                return 1;
            }
            int iCompareTo12 = countOfEntitiesPerWidget.compareTo(countOfEntitiesPerWidget2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        String upsellContent = getUpsellContent();
        String upsellContent2 = pageRequest.getUpsellContent();
        if (upsellContent != upsellContent2) {
            if (upsellContent == null) {
                return -1;
            }
            if (upsellContent2 == null) {
                return 1;
            }
            int iCompareTo13 = upsellContent.compareTo(upsellContent2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        AllowedParentalControls allowedParentalControls = getAllowedParentalControls();
        AllowedParentalControls allowedParentalControls2 = pageRequest.getAllowedParentalControls();
        if (allowedParentalControls != allowedParentalControls2) {
            if (allowedParentalControls == null) {
                return -1;
            }
            if (allowedParentalControls2 == null) {
                return 1;
            }
            int iCompareTo14 = allowedParentalControls.compareTo(allowedParentalControls2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        Integer offset = getOffset();
        Integer offset2 = pageRequest.getOffset();
        if (offset != offset2) {
            if (offset == null) {
                return -1;
            }
            if (offset2 == null) {
                return 1;
            }
            int iCompareTo15 = offset.compareTo(offset2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        Boolean boolIsStub = isStub();
        Boolean boolIsStub2 = pageRequest.isStub();
        if (boolIsStub != boolIsStub2) {
            if (boolIsStub == null) {
                return -1;
            }
            if (boolIsStub2 == null) {
                return 1;
            }
            int iCompareTo16 = boolIsStub.compareTo(boolIsStub2);
            if (iCompareTo16 != 0) {
                return iCompareTo16;
            }
        }
        String artistVideoStoryEntityAsin = getArtistVideoStoryEntityAsin();
        String artistVideoStoryEntityAsin2 = pageRequest.getArtistVideoStoryEntityAsin();
        if (artistVideoStoryEntityAsin != artistVideoStoryEntityAsin2) {
            if (artistVideoStoryEntityAsin == null) {
                return -1;
            }
            if (artistVideoStoryEntityAsin2 == null) {
                return 1;
            }
            int iCompareTo17 = artistVideoStoryEntityAsin.compareTo(artistVideoStoryEntityAsin2);
            if (iCompareTo17 != 0) {
                return iCompareTo17;
            }
        }
        String uri = getUri();
        String uri2 = pageRequest.getUri();
        if (uri != uri2) {
            if (uri == null) {
                return -1;
            }
            if (uri2 == null) {
                return 1;
            }
            int iCompareTo18 = uri.compareTo(uri2);
            if (iCompareTo18 != 0) {
                return iCompareTo18;
            }
        }
        List<String> contentFeatures = getContentFeatures();
        List<String> contentFeatures2 = pageRequest.getContentFeatures();
        if (contentFeatures != contentFeatures2) {
            if (contentFeatures == null) {
                return -1;
            }
            if (contentFeatures2 == null) {
                return 1;
            }
            if (contentFeatures instanceof Comparable) {
                int iCompareTo19 = ((Comparable) contentFeatures).compareTo(contentFeatures2);
                if (iCompareTo19 != 0) {
                    return iCompareTo19;
                }
            } else if (!contentFeatures.equals(contentFeatures2)) {
                int iHashCode5 = contentFeatures.hashCode();
                int iHashCode6 = contentFeatures2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        return super.compareTo(requestIdentity);
    }

    @Override // com.amazon.musicensembleservice.RequestIdentity
    public boolean equals(Object obj) {
        if (!(obj instanceof PageRequest)) {
            return false;
        }
        PageRequest pageRequest = (PageRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getIpAddress(), pageRequest.getIpAddress()) && internalEqualityCheck(getCount(), pageRequest.getCount()) && internalEqualityCheck(getAllowedParentalControlsString(), pageRequest.getAllowedParentalControlsString()) && internalEqualityCheck(getValidationPayload(), pageRequest.getValidationPayload()) && internalEqualityCheck(getCampaignsXml(), pageRequest.getCampaignsXml()) && internalEqualityCheck(isTestTraffic(), pageRequest.isTestTraffic()) && internalEqualityCheck(getLocale(), pageRequest.getLocale()) && internalEqualityCheck(getBrowseId(), pageRequest.getBrowseId()) && internalEqualityCheck(isDebug(), pageRequest.isDebug()) && internalEqualityCheck(getLanguagesOfPerformance(), pageRequest.getLanguagesOfPerformance()) && internalEqualityCheck(getNextToken(), pageRequest.getNextToken()) && internalEqualityCheck(getCountOfEntitiesPerWidget(), pageRequest.getCountOfEntitiesPerWidget()) && internalEqualityCheck(getUpsellContent(), pageRequest.getUpsellContent()) && internalEqualityCheck(getAllowedParentalControls(), pageRequest.getAllowedParentalControls()) && internalEqualityCheck(getOffset(), pageRequest.getOffset()) && internalEqualityCheck(isStub(), pageRequest.isStub()) && internalEqualityCheck(getArtistVideoStoryEntityAsin(), pageRequest.getArtistVideoStoryEntityAsin()) && internalEqualityCheck(getUri(), pageRequest.getUri()) && internalEqualityCheck(getContentFeatures(), pageRequest.getContentFeatures());
    }

    public AllowedParentalControls getAllowedParentalControls() {
        return this.allowedParentalControls;
    }

    public List<String> getAllowedParentalControlsString() {
        return this.allowedParentalControlsString;
    }

    public String getArtistVideoStoryEntityAsin() {
        return this.artistVideoStoryEntityAsin;
    }

    public String getBrowseId() {
        return this.browseId;
    }

    public String getCampaignsXml() {
        return this.campaignsXml;
    }

    public List<String> getContentFeatures() {
        return this.contentFeatures;
    }

    public Integer getCount() {
        return this.count;
    }

    public Integer getCountOfEntitiesPerWidget() {
        return this.countOfEntitiesPerWidget;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public List<String> getLanguagesOfPerformance() {
        return this.languagesOfPerformance;
    }

    public String getLocale() {
        return this.locale;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public Integer getOffset() {
        return this.offset;
    }

    public String getUpsellContent() {
        return this.upsellContent;
    }

    public String getUri() {
        return this.uri;
    }

    public ValidationPayload getValidationPayload() {
        return this.validationPayload;
    }

    @Override // com.amazon.musicensembleservice.RequestIdentity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getIpAddress(), getCount(), getAllowedParentalControlsString(), getValidationPayload(), getCampaignsXml(), isTestTraffic(), getLocale(), getBrowseId(), isDebug(), getLanguagesOfPerformance(), getNextToken(), getCountOfEntitiesPerWidget(), getUpsellContent(), getAllowedParentalControls(), getOffset(), isStub(), getArtistVideoStoryEntityAsin(), getUri(), getContentFeatures());
    }

    public Boolean isDebug() {
        return this.debug;
    }

    public Boolean isStub() {
        return this.stub;
    }

    public Boolean isTestTraffic() {
        return this.testTraffic;
    }

    public void setAllowedParentalControls(AllowedParentalControls allowedParentalControls) {
        this.allowedParentalControls = allowedParentalControls;
    }

    public void setBrowseId(String str) {
        this.browseId = str;
    }

    public void setContentFeatures(List<String> list) {
        this.contentFeatures = list;
    }

    public void setCount(Integer num) {
        this.count = num;
    }

    public void setLocale(String str) {
        this.locale = str;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setStub(Boolean bool) {
        this.stub = bool;
    }

    public void setUpsellContent(String str) {
        this.upsellContent = str;
    }

    public void setUri(String str) {
        this.uri = str;
    }
}
