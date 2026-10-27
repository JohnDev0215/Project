package com.amazon.music.inappmessaging.external.model;

import android.net.Uri;
import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.music.destination.parser.ParserUtil;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

/* JADX INFO: loaded from: classes4.dex */
public class DynamicMessageContextualEventBuilder {
    private static String TAG = "DynamicMessageContextualEventBuilder";

    public static Builder builder(Trigger trigger) {
        return new Builder(trigger);
    }

    public static class Builder {
        private Uri.Builder eventBuilder;

        private Builder(Trigger trigger) {
            this.eventBuilder = Uri.parse(trigger.getName()).buildUpon();
        }

        public Builder withArtistName(@Nullable final String artistName) {
            this.eventBuilder.appendQueryParameter(ContextualTriggerAllowList.ARTIST_NAME_KEY, artistName);
            return this;
        }

        public Builder withItemName(@Nullable final String itemName) {
            this.eventBuilder.appendQueryParameter(ContextualTriggerAllowList.ITEM_NAME_KEY, itemName);
            if (itemName != null) {
                try {
                    this.eventBuilder.appendQueryParameter(ContextualTriggerAllowList.URL_ENCODED_ITEM_NAME_KEY, URLEncoder.encode(itemName, ParserUtil.UTF_8));
                } catch (UnsupportedEncodingException e) {
                    Log.e(DynamicMessageContextualEventBuilder.TAG, "Error url encoding item name " + itemName + " due to UnsupportedEncodingException with message " + e.getMessage());
                }
            }
            return this;
        }

        public Builder withAlbumAsin(final String albumAsin) {
            this.eventBuilder.appendQueryParameter(ContextualTriggerAllowList.ALBUM_ASIN_KEY, albumAsin);
            return this;
        }

        public Builder withItemUrl(final String itemUrl) {
            this.eventBuilder.appendQueryParameter(ContextualTriggerAllowList.ITEM_URL_KEY, itemUrl);
            if (itemUrl != null) {
                try {
                    this.eventBuilder.appendQueryParameter(ContextualTriggerAllowList.URL_ENCODED_ITEM_URL_KEY, URLEncoder.encode(itemUrl, ParserUtil.UTF_8));
                } catch (UnsupportedEncodingException e) {
                    Log.e(DynamicMessageContextualEventBuilder.TAG, "Error url encoding item url " + itemUrl + " due to UnsupportedEncodingException with message " + e.getMessage());
                }
            }
            return this;
        }

        public Builder withTrackAsinQueryParameter(final String trackAsinQueryParameter) {
            this.eventBuilder.appendQueryParameter(ContextualTriggerAllowList.TRACK_ASIN_QUERY_PARAMETER_KEY, trackAsinQueryParameter);
            return this;
        }

        public Builder withCampaignName(final String campaignName) {
            this.eventBuilder.appendQueryParameter(ContextualTriggerAllowList.CAMPAIGN_NAME, campaignName);
            return this;
        }

        public Builder withCategoryName(final String categoryName) {
            this.eventBuilder.appendQueryParameter(ContextualTriggerAllowList.CATEGORY_NAME, categoryName);
            return this;
        }

        public DynamicMessageEvent build() {
            return new DynamicMessageEvent(URI.create(this.eventBuilder.build().toString()));
        }
    }
}
