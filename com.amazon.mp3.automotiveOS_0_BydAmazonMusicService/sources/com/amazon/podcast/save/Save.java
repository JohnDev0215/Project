package com.amazon.podcast.save;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.podcast.storage.PodcastRoomTypeConverters;
import com.google.android.gms.cast.MediaTrack;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
@Entity(tableName = "saves")
public class Save {

    @NonNull
    @ColumnInfo(name = "authors")
    private String authors;

    @NonNull
    @ColumnInfo(name = "availabilityDate")
    private String availabilityDate;

    @NonNull
    @TypeConverters({PodcastRoomTypeConverters.class})
    @ColumnInfo(name = "availableUpsells")
    private List<String> availableUpsells;

    @NonNull
    @TypeConverters({PodcastRoomTypeConverters.class})
    @ColumnInfo(name = "contentTraits")
    private List<String> contentTraits;

    @NonNull
    @ColumnInfo(name = MediaTrack.ROLE_DESCRIPTION)
    private String description;

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String id;

    @NonNull
    @ColumnInfo(name = ContextMappingConstants.IMAGE)
    private String image;

    @NonNull
    @ColumnInfo(name = "playbackMode")
    private String playbackMode;

    @NonNull
    @ColumnInfo(name = "podcastEpisodeVariantId")
    private String podcastEpisodeVariantId;

    @NonNull
    @ColumnInfo(name = "podcastId")
    private String podcastId;

    @NonNull
    @ColumnInfo(name = "podcastImage")
    private String podcastImage;

    @NonNull
    @ColumnInfo(name = "podcastShowVariantId")
    private String podcastShowVariantId;

    @NonNull
    @ColumnInfo(name = "podcastTitle")
    private String podcastTitle;

    @NonNull
    @ColumnInfo(name = "publishDate")
    private String publishDate;

    @ColumnInfo(name = "saved")
    private boolean saved;

    @NonNull
    @ColumnInfo(name = "seasonNumber")
    private String seasonNumber;

    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "totalDurationMilliseconds")
    private long totalDurationMilliseconds;

    @ColumnInfo(name = "updatedTime")
    private long updatedTime;

    public Save(@NonNull final String id, @NonNull final String podcastId, @NonNull final String podcastTitle, @NonNull final String title, @NonNull final String image, @NonNull final String podcastImage, @NonNull final String publishDate, @NonNull final String description, @NonNull final String authors, @NonNull final String seasonNumber, final long totalDurationMilliseconds, final boolean saved, final long updatedTime, @NonNull final String playbackMode, @NonNull final List<String> contentTraits, @NonNull final List<String> availableUpsells, @NonNull final String availabilityDate, @NonNull final String podcastShowVariantId, @NonNull final String podcastEpisodeVariantId) {
        this.id = id;
        this.podcastId = podcastId;
        this.podcastTitle = podcastTitle;
        this.title = title;
        this.image = image;
        this.podcastImage = podcastImage;
        this.publishDate = publishDate;
        this.description = description;
        this.authors = authors;
        this.seasonNumber = seasonNumber;
        this.totalDurationMilliseconds = totalDurationMilliseconds;
        this.saved = saved;
        this.updatedTime = updatedTime;
        this.playbackMode = playbackMode;
        this.contentTraits = contentTraits;
        this.availableUpsells = availableUpsells;
        this.availabilityDate = availabilityDate;
        this.podcastShowVariantId = podcastShowVariantId;
        this.podcastEpisodeVariantId = podcastEpisodeVariantId;
    }

    public Save(@NonNull final Save save, @NonNull final String id, @NonNull final String podcastId, @NonNull final String podcastTitle, @NonNull final String title, @NonNull final String image, @NonNull final String podcastImage, @NonNull final String publishDate, @NonNull final String description, @NonNull final String authors, @NonNull final String seasonNumber, @NonNull final String playbackMode, @NonNull final List<String> contentTraits, @NonNull final List<String> availableUpsells, @NonNull final String availabilityDate, @NonNull final String podcastShowVariantId, @NonNull final String podcastEpisodeVariantId) {
        this.id = id;
        this.podcastId = podcastId;
        this.podcastTitle = podcastTitle;
        this.title = title;
        this.image = image;
        this.podcastImage = podcastImage;
        this.publishDate = publishDate;
        this.description = description;
        this.authors = authors;
        this.seasonNumber = seasonNumber;
        this.totalDurationMilliseconds = save.getTotalDurationMilliseconds();
        this.saved = save.isSaved();
        this.updatedTime = save.getUpdatedTime();
        this.playbackMode = playbackMode;
        this.contentTraits = contentTraits;
        this.availableUpsells = availableUpsells;
        this.availabilityDate = availabilityDate;
        this.podcastShowVariantId = podcastShowVariantId;
        this.podcastEpisodeVariantId = podcastEpisodeVariantId;
    }

    @NonNull
    public String getId() {
        return this.id;
    }

    @NonNull
    public String getPodcastId() {
        return this.podcastId;
    }

    @NonNull
    public String getPodcastTitle() {
        return this.podcastTitle;
    }

    @NonNull
    public String getTitle() {
        return this.title;
    }

    @NonNull
    public String getImage() {
        return this.image;
    }

    @NonNull
    public String getPodcastImage() {
        return this.podcastImage;
    }

    @NonNull
    public String getPublishDate() {
        return this.publishDate;
    }

    @NonNull
    public String getDescription() {
        return this.description;
    }

    @NonNull
    public String getAuthors() {
        return this.authors;
    }

    @NonNull
    public String getSeasonNumber() {
        return this.seasonNumber;
    }

    public long getTotalDurationMilliseconds() {
        return this.totalDurationMilliseconds;
    }

    public boolean isSaved() {
        return this.saved;
    }

    public long getUpdatedTime() {
        return this.updatedTime;
    }

    @NonNull
    public String getPlaybackMode() {
        return this.playbackMode;
    }

    @NonNull
    @TypeConverters({PodcastRoomTypeConverters.class})
    public List<String> getContentTraits() {
        return this.contentTraits;
    }

    @NonNull
    @TypeConverters({PodcastRoomTypeConverters.class})
    public List<String> getAvailableUpsells() {
        return this.availableUpsells;
    }

    @NonNull
    public String getAvailabilityDate() {
        return this.availabilityDate;
    }

    @NonNull
    public String getPodcastShowVariantId() {
        return this.podcastShowVariantId;
    }

    @NonNull
    public String getPodcastEpisodeVariantId() {
        return this.podcastEpisodeVariantId;
    }
}
