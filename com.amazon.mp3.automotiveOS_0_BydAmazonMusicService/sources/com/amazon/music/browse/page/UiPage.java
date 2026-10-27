package com.amazon.music.browse.page;

import com.amazon.music.browse.BrowseException;
import com.amazon.music.browse.BrowseService;
import com.amazon.music.browse.util.ParentalControlsUtil;
import com.amazon.musicensembleservice.brush.PageResponse;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class UiPage {
    private final Logger LOG = LoggerFactory.getLogger((Class<?>) UiPage.class);
    private final BrowseService browseService;

    public UiPage(BrowseService browseService) {
        Validate.notNull(browseService, "browseService can't be null", new Object[0]);
        this.browseService = browseService;
    }

    public PageResponse get(PageRequest pageRequest) throws BrowseException {
        return get(pageRequest, Boolean.FALSE, null);
    }

    public PageResponse get(PageRequest pageRequest, Boolean bool, String str) throws BrowseException {
        Validate.notNull(pageRequest, "request can't be null", new Object[0]);
        try {
            return this.browseService.getPageResponse(createRequest(pageRequest), bool, str);
        } catch (Exception e) {
            throw new BrowseException(e);
        }
    }

    public Boolean isCacheExpired(PageRequest pageRequest, String str) {
        Validate.notNull(pageRequest, "request can't be null", new Object[0]);
        return Boolean.valueOf(this.browseService.isCacheExpired(createRequest(pageRequest), str));
    }

    public Boolean invalidateCache(PageRequest pageRequest, String str) {
        Validate.notNull(pageRequest, "request can't be null", new Object[0]);
        return Boolean.valueOf(this.browseService.invalidateCache(createRequest(pageRequest), str));
    }

    private com.amazon.musicensembleservice.brush.PageRequest createRequest(PageRequest pageRequest) {
        com.amazon.musicensembleservice.brush.PageRequest pageRequest2 = new com.amazon.musicensembleservice.brush.PageRequest();
        pageRequest2.setMusicTerritory(pageRequest.getMusicTerritory());
        pageRequest2.setRequestedContent(pageRequest.getRequestedContent().toString());
        pageRequest2.setLocale(pageRequest.getLocale());
        pageRequest2.setDeviceId(pageRequest.getDeviceId());
        pageRequest2.setDeviceType(pageRequest.getDeviceType());
        pageRequest2.setStub(Boolean.valueOf(pageRequest.isStub()));
        pageRequest2.setCount(Integer.valueOf(pageRequest.getCount()));
        pageRequest2.setNextToken(pageRequest.getNextToken());
        pageRequest2.setContentFeatures(pageRequest.getContentFeatures());
        pageRequest2.setUri(pageRequest.getUri());
        pageRequest2.setAllowedParentalControls(ParentalControlsUtil.getBrushParentalControls(pageRequest.isExplicitFilterEnabled()));
        pageRequest2.setUpsellContent(pageRequest.getUpsellContent());
        pageRequest2.setBrowseId(pageRequest.getBrowseId());
        pageRequest2.setMusicRequestIdentityContextToken(pageRequest.getMusicRequestIdentityContextToken());
        return pageRequest2;
    }
}
