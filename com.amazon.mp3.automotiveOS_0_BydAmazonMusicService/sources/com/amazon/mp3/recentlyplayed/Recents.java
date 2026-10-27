package com.amazon.mp3.recentlyplayed;

import android.app.Application;
import androidx.annotation.NonNull;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.recents.RecentsController;
import com.amazon.music.recents.RecentsException;
import com.amazon.music.recents.model.ActivityType;
import com.amazon.music.recents.model.RecentTrackActivityRequest;
import com.amazon.music.recents.model.RecentlyPlayedTrack;
import com.amazon.music.recents.model.RecentlyPlayedTracksResponse;
import com.amazon.music.subscription.UserSubscription;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class Recents {
    private static final String TAG = "Recents";

    private Recents() {
    }

    public static final class Builder {
        private List<ActivityType> mActivityTypes;
        private Application mApplication;
        private boolean mFilterSubscription;
        private String mPageToken;
        private boolean mRemoveDuplicateTracks;
        private final Set<String> mSeenTrackAsins;

        private Builder(final Application application) {
            this.mSeenTrackAsins = new HashSet();
            this.mApplication = application;
        }

        public Builder withActivityTypes(@NonNull final ActivityType... activityTypes) {
            this.mActivityTypes = Arrays.asList(activityTypes);
            return this;
        }

        public Builder withPageToken(@NonNull final String pageToken) {
            this.mPageToken = pageToken;
            return this;
        }

        public Builder withFilterSubscription(boolean filterSubscription) {
            this.mFilterSubscription = filterSubscription;
            return this;
        }

        public Builder withRemoveDuplicateTracks(boolean removeDuplicateTracks) {
            this.mRemoveDuplicateTracks = removeDuplicateTracks;
            return this;
        }

        public RecentsResponse getRecents() throws RecentsException {
            ContentOwnershipStatus contentOwnershipStatus;
            ContentCatalogStatus contentCatalogStatus;
            RecentsController recentsControllerCreateRecentsController = RecentsFactory.createRecentsController(this.mApplication);
            AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(this.mApplication);
            RecentlyPlayedTracksResponse recentTrackActivity = recentsControllerCreateRecentsController.getRecentTrackActivity(new RecentTrackActivityRequest.Builder(AccountDetailUtil.getMusicTerritoryOfResidence(), accountCredentialStorage.getDeviceId(), accountCredentialStorage.getDeviceType(), this.mActivityTypes).pageToken(this.mPageToken).build());
            Validate.notNull(recentTrackActivity, "Response from recents service was null", new Object[0]);
            List<RecentlyPlayedTrack> recentTrackList = recentTrackActivity.getRecentTrackList();
            Validate.notNull(recentTrackList, "Tracks array from recents service was null", new Object[0]);
            Log.debug(Recents.TAG, "Got %d tracks from recents service", Integer.valueOf(recentTrackList.size()));
            ArrayList arrayList = new ArrayList(recentTrackList.size());
            UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
            for (RecentlyPlayedTrack recentlyPlayedTrack : recentTrackList) {
                if (this.mFilterSubscription && !recentlyPlayedTrack.isPurchased().booleanValue()) {
                    if (!userSubscription.isPrimeOnly() || recentlyPlayedTrack.isPrime().booleanValue()) {
                        if (!userSubscription.isHawkfireAllDevices() || recentlyPlayedTrack.isMusicSubscription().booleanValue()) {
                        }
                    }
                }
                if (this.mRemoveDuplicateTracks) {
                    if (!this.mSeenTrackAsins.contains(recentlyPlayedTrack.getAsin())) {
                        this.mSeenTrackAsins.add(recentlyPlayedTrack.getAsin());
                    }
                }
                RecentTrack recentTrack = new RecentTrack(recentlyPlayedTrack.getDisplayName(), recentlyPlayedTrack.getArtistName(), "cirrus");
                recentTrack.setAlbumAsin(recentlyPlayedTrack.getAlbumAsin());
                recentTrack.setAlbumName(recentlyPlayedTrack.getAlbumName());
                recentTrack.setArtistAsin(recentlyPlayedTrack.getArtistAsin());
                recentTrack.setAsin(recentlyPlayedTrack.getAsin());
                recentTrack.setHasLyrics(falseIfNull(recentlyPlayedTrack.getHasLyrics()));
                recentTrack.setArtworkImageUrl(recentlyPlayedTrack.getImage());
                if (falseIfNull(recentlyPlayedTrack.isPurchased())) {
                    contentOwnershipStatus = ContentOwnershipStatus.OWNED;
                } else {
                    contentOwnershipStatus = falseIfNull(recentlyPlayedTrack.getInLibrary()) ? ContentOwnershipStatus.ADDED : ContentOwnershipStatus.NOT_IN_LIBRARY;
                }
                recentTrack.setOwnershipStatus(contentOwnershipStatus);
                if (falseIfNull(recentlyPlayedTrack.isMusicSubscription())) {
                    contentCatalogStatus = ContentCatalogStatus.HAWKFIRE;
                } else {
                    contentCatalogStatus = falseIfNull(recentlyPlayedTrack.isPrime()) ? ContentCatalogStatus.PRIME : ContentCatalogStatus.UNKNOWN;
                }
                recentTrack.setCatalogStatus(contentCatalogStatus);
                arrayList.add(recentTrack);
            }
            return new RecentsResponse(arrayList);
        }

        private boolean falseIfNull(Boolean value) {
            return value != null && value.booleanValue();
        }
    }

    public static Builder get(@NonNull final Application application) {
        return new Builder(application);
    }

    public static class RecentsResponse {
        private final List<RecentTrack> tracks;

        RecentsResponse(final List<RecentTrack> tracks) {
            this.tracks = tracks;
        }

        public List<RecentTrack> getTracks() {
            return this.tracks;
        }
    }
}
