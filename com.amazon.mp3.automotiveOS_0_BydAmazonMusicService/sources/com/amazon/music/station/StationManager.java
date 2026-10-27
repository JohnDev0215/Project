package com.amazon.music.station;

import com.amazon.music.station.model.RefinementItem;
import com.amazon.music.station.model.SectionItem;
import com.amazon.music.storeservice.model.GetStationRecommendationsRequest;
import com.amazon.music.storeservice.model.GetStationRecommendationsResponse;
import com.amazon.musicensembleservice.AllowedParentalControls;
import com.amazon.musicensembleservice.stations.Category;
import com.amazon.musicensembleservice.stations.GetStationSectionsRequest;
import com.amazon.musicensembleservice.stations.GetStationSectionsResponse;
import com.amazon.musicensembleservice.stations.Section;
import com.android.volley.VolleyError;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public final class StationManager {
    private AllowedParentalControls allowedParentalControls;
    private String musicRequestIdentityContextToken;
    private Map<SectionItem, List<RefinementItem>> refinementsBySection;
    private List<SectionItem> sectionItems;
    private Map<String, StationItem> stationItemByKey;
    private Map<String, StationItem> stationItemBySeedId;
    private Map<RefinementItem, List<StationItem>> stationItemsByRefinement;
    private final StationService stationService;

    public StationManager(StationService stationService, AllowedParentalControls allowedParentalControls, String str) {
        this.stationService = stationService;
        this.allowedParentalControls = allowedParentalControls;
        this.musicRequestIdentityContextToken = str;
    }

    public synchronized List<SectionItem> getSectionItems() throws StationException {
        if (this.sectionItems == null) {
            buildCache();
        }
        return this.sectionItems;
    }

    public synchronized List<RefinementItem> getRefinementsBySection(SectionItem sectionItem) throws StationException {
        List<RefinementItem> list;
        Validate.notNull(sectionItem, "section can't be null", new Object[0]);
        if (this.refinementsBySection == null) {
            buildCache();
        }
        list = this.refinementsBySection.get(sectionItem);
        if (list == null) {
            throw new StationException("Section: " + sectionItem + " does not exist");
        }
        return list;
    }

    public synchronized List<StationItem> getStationItemsByRefinement(RefinementItem refinementItem) throws StationException {
        List<StationItem> list;
        Validate.notNull(refinementItem, "refinement can't be null", new Object[0]);
        if (this.stationItemsByRefinement == null) {
            buildCache();
        }
        list = this.stationItemsByRefinement.get(refinementItem);
        if (list == null) {
            throw new StationException("Refinement: " + refinementItem + " does not exist");
        }
        return list;
    }

    public synchronized StationItem getStationItemByKey(String str) throws StationException, StationNotFoundException {
        Validate.notNull(str, "key can't be null", new Object[0]);
        if (this.stationItemByKey == null) {
            buildCache();
        }
        StationItem stationItem = this.stationItemByKey.get(str);
        if (stationItem != null) {
            return stationItem;
        }
        if ("A2TD3E6KPR6WBW".equals(str)) {
            return new StationItem(true, true, false, false, "", null, null, null, str);
        }
        throw new StationNotFoundException("key " + str + " does not exist");
    }

    public synchronized StationItem getStationItemBySeedId(String str) throws StationException, StationNotFoundException {
        StationItem stationItem;
        Validate.notNull(str, "seedId can't be null", new Object[0]);
        if (this.stationItemBySeedId == null) {
            buildCache();
        }
        stationItem = this.stationItemBySeedId.get(str);
        if (stationItem == null) {
            throw new StationNotFoundException("seedId " + str + " does not exist");
        }
        return stationItem;
    }

    public synchronized void rebuildCache() throws StationException {
        buildCache();
    }

    public synchronized GetStationRecommendationsResponse getStationRecommendations(int i, int i2, AllowedParentalControls allowedParentalControls, Map map) throws VolleyError {
        GetStationRecommendationsRequest getStationRecommendationsRequest;
        boolean z = true;
        Validate.isTrue(i >= 0);
        Validate.isTrue(i2 > 0);
        if (i2 < i) {
            z = false;
        }
        Validate.isTrue(z);
        Validate.notNull(allowedParentalControls);
        getStationRecommendationsRequest = new GetStationRecommendationsRequest();
        getStationRecommendationsRequest.setMinResultsPerWidget(Integer.valueOf(i));
        getStationRecommendationsRequest.setMaxResultsPerWidget(Integer.valueOf(i2));
        getStationRecommendationsRequest.setAllowedParentalControls(allowedParentalControls);
        if (map != null) {
            getStationRecommendationsRequest.setWidgetIdTokenMap(map);
        }
        return this.stationService.getStationRecommendations(getStationRecommendationsRequest);
    }

    private void buildCache() throws StationException {
        try {
            GetStationSectionsResponse getStationSectionsResponseFetchDataOverNetwork = fetchDataOverNetwork();
            buildSectionItems(getStationSectionsResponseFetchDataOverNetwork);
            buildRefinementsBySection(getStationSectionsResponseFetchDataOverNetwork);
            buildStationItemsByRefinement(getStationSectionsResponseFetchDataOverNetwork);
            buildStationItemsByKeyAndSeedId(getStationSectionsResponseFetchDataOverNetwork);
        } catch (Exception e) {
            throw new StationException(e);
        }
    }

    private GetStationSectionsResponse fetchDataOverNetwork() throws VolleyError {
        GetStationSectionsRequest getStationSectionsRequest = new GetStationSectionsRequest();
        getStationSectionsRequest.setAllowedParentalControls(this.allowedParentalControls);
        getStationSectionsRequest.setMusicRequestIdentityContextToken(this.musicRequestIdentityContextToken);
        return this.stationService.getStationSections(getStationSectionsRequest);
    }

    private void buildSectionItems(GetStationSectionsResponse getStationSectionsResponse) {
        List<Section> sections = getStationSectionsResponse.getSections();
        this.sectionItems = new ArrayList(sections.size());
        for (Section section : sections) {
            this.sectionItems.add(new SectionItem(section.getSectionId(), section.getSectionTitle()));
        }
    }

    private void buildRefinementsBySection(GetStationSectionsResponse getStationSectionsResponse) {
        List<Section> sections = getStationSectionsResponse.getSections();
        Map<String, Category> categories = getStationSectionsResponse.getCategories();
        this.refinementsBySection = new HashMap(sections.size());
        for (Section section : sections) {
            ArrayList arrayList = new ArrayList();
            Iterator<String> it = section.getCategoryMapIds().iterator();
            while (it.hasNext()) {
                Category category = categories.get(it.next());
                arrayList.add(new RefinementItem(category.getTitle(), category.getCategoryId(), category.getForegroundImageUrl()));
            }
            this.refinementsBySection.put(new SectionItem(section.getSectionId(), section.getSectionTitle()), arrayList);
        }
    }

    private void buildStationItemsByRefinement(GetStationSectionsResponse getStationSectionsResponse) {
        this.stationItemsByRefinement = new HashMap();
        Map<String, Category> categories = getStationSectionsResponse.getCategories();
        Map<String, com.amazon.musicensembleservice.stations.Station> stations = getStationSectionsResponse.getStations();
        for (Category category : categories.values()) {
            RefinementItem refinementItem = new RefinementItem(category.getTitle(), category.getCategoryId(), category.getForegroundImageUrl());
            List<String> stationMapIds = category.getStationMapIds();
            ArrayList arrayList = new ArrayList(stationMapIds.size());
            for (String str : stationMapIds) {
                arrayList.add(StationItem.from(stations.get(str), str));
            }
            this.stationItemsByRefinement.put(refinementItem, arrayList);
        }
    }

    private void buildStationItemsByKeyAndSeedId(GetStationSectionsResponse getStationSectionsResponse) {
        Set<Map.Entry<String, com.amazon.musicensembleservice.stations.Station>> setEntrySet = getStationSectionsResponse.getStations().entrySet();
        this.stationItemByKey = new HashMap(setEntrySet.size());
        this.stationItemBySeedId = new HashMap(this.stationItemByKey.size());
        for (Map.Entry<String, com.amazon.musicensembleservice.stations.Station> entry : setEntrySet) {
            String key = entry.getKey();
            StationItem stationItemFrom = StationItem.from(entry.getValue(), key);
            this.stationItemByKey.put(key, stationItemFrom);
            this.stationItemBySeedId.put(stationItemFrom.getSeedId(), stationItemFrom);
        }
    }
}
