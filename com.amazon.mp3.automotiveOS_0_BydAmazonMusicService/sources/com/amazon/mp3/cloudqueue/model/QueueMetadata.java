package com.amazon.mp3.cloudqueue.model;

import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class QueueMetadata {
    private Map<String, String> imageUrlMap;
    private int maximumNumberOfTracksThatCanBeRequestedPerPage;
    private int maximumTrackLookAhead;
    private int numberOfTracksRemainingBeforeReplenishing;
    private ServiceTier serviceTier;
    private String title;

    public QueueMetadata(ServiceTier serviceTier, String title, Map<String, String> imageUrlMap, int maximumNumberOfTracksThatCanBeRequestedPerPage, int maximumTrackLookAhead, int numberOfTracksRemainingBeforeReplenishing) {
        this.serviceTier = serviceTier;
        this.title = title;
        this.imageUrlMap = imageUrlMap;
        this.maximumNumberOfTracksThatCanBeRequestedPerPage = maximumNumberOfTracksThatCanBeRequestedPerPage;
        this.maximumTrackLookAhead = maximumTrackLookAhead;
        this.numberOfTracksRemainingBeforeReplenishing = numberOfTracksRemainingBeforeReplenishing;
    }

    public String getTitle() {
        return this.title;
    }

    public Map<String, String> getImageUrlMap() {
        return this.imageUrlMap;
    }

    public int getMaximumNumberOfTracksThatCanBeRequestedPerPage() {
        return this.maximumNumberOfTracksThatCanBeRequestedPerPage;
    }

    public void setMaximumNumberOfTracksThatCanBeRequestedPerPage(int maximumNumberOfTracksThatCanBeRequestedPerPage) {
        this.maximumNumberOfTracksThatCanBeRequestedPerPage = maximumNumberOfTracksThatCanBeRequestedPerPage;
    }

    public int getMaximumTrackLookAhead() {
        return this.maximumTrackLookAhead;
    }

    public void setMaximumTrackLookAhead(int maximumTrackLookAhead) {
        this.maximumTrackLookAhead = maximumTrackLookAhead;
    }

    public int getNumberOfTracksRemainingBeforeReplenishing() {
        return this.numberOfTracksRemainingBeforeReplenishing;
    }

    public void setNumberOfTracksRemainingBeforeReplenishing(int numberOfTracksRemainingBeforeReplenishing) {
        this.numberOfTracksRemainingBeforeReplenishing = numberOfTracksRemainingBeforeReplenishing;
    }
}
