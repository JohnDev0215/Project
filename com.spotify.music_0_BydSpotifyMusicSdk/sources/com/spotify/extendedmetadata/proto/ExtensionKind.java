package com.spotify.extendedmetadata.proto;

import com.spotify.connect.esperanto.proto.ConnectMessages$StateResponse;
import com.spotify.connectivity.flags.LoadedFlagsKt;
import p.n51;

/* JADX INFO: loaded from: classes.dex */
public enum ExtensionKind implements n51 {
    UNKNOWN_EXTENSION(0),
    CANVAZ(1),
    STORYLINES(2),
    PODCAST_TOPICS(3),
    PODCAST_SEGMENTS(4),
    AUDIO_FILES(5),
    TRACK_DESCRIPTOR(6),
    PODCAST_COUNTER(7),
    ARTIST_V4(8),
    ALBUM_V4(9),
    TRACK_V4(10),
    SHOW_V4(11),
    EPISODE_V4(12),
    PODCAST_HTML_DESCRIPTION(13),
    PODCAST_QUOTES(14),
    USER_PROFILE(15),
    CANVAS_V1(16),
    SHOW_V4_BASE(17),
    SHOW_V4_EPISODES_ASSOC(18),
    TRACK_DESCRIPTOR_SIGNATURES(19),
    PODCAST_AD_SEGMENTS(20),
    EPISODE_TRANSCRIPTS(21),
    PODCAST_SUBSCRIPTIONS(22),
    EXTRACTED_COLOR(23),
    PODCAST_VIRALITY(24),
    IMAGE_SPARKLES_HACK(25),
    PODCAST_POPULARITY_HACK(26),
    AUTOMIX_MODE(27),
    CUEPOINTS(28),
    PODCAST_POLL(29),
    EPISODE_ACCESS(30),
    SHOW_ACCESS(31),
    PODCAST_QNA(32),
    CLIPS(33),
    SHOW_V5(34),
    EPISODE_V5(35),
    PODCAST_CTA_CARDS(36),
    PODCAST_RATING(37),
    DISPLAY_SEGMENTS(38),
    GREENROOM(39),
    USER_CREATED(40),
    SHOW_DESCRIPTION(41),
    SHOW_HTML_DESCRIPTION(42),
    SHOW_PLAYABILITY(43),
    EPISODE_DESCRIPTION(44),
    EPISODE_HTML_DESCRIPTION(45),
    EPISODE_PLAYABILITY(46),
    SHOW_EPISODES_ASSOC(47),
    CLIENT_CONFIG(48),
    PLAYLISTABILITY(49),
    AUDIOBOOK_V5(50),
    CHAPTER_V5(51),
    AUDIOBOOK_SPECIFICS(52),
    EPISODE_RANKING(53),
    HTML_DESCRIPTION(54),
    CREATOR_CHANNEL(55),
    AUDIOBOOK_PROVIDERS(56),
    PLAY_TRAIT(57),
    CONTENT_WARNING(58),
    IMAGE_CUE(59),
    STREAM_COUNT(60),
    AUDIO_ATTRIBUTES(61),
    NAVIGABLE_TRAIT(62),
    NEXT_BEST_EPISODE(63),
    AUDIOBOOK_PRICE(64),
    EXPRESSIVE_PLAYLISTS(65),
    DYNAMIC_SHOW_EPISODE(66),
    LIVE(67),
    SKIP_PLAYED(68),
    AD_BREAK_FREE_PODCASTS(69),
    ASSOCIATIONS(70),
    PLAYLIST_EVALUATION(71),
    CACHE_INVALIDATIONS(72),
    LIVESTREAM_ENTITY(73),
    SINGLE_TAP_REACTIONS(74),
    USER_COMMENTS(75),
    CLIENT_RESTRICTIONS(76),
    PODCAST_GUEST(77),
    PLAYABILITY(78),
    COVER_IMAGE(79),
    SHARE_TRAIT(80),
    INSTANCE_SHARING(81),
    ARTIST_TOUR(82),
    AUDIOBOOK_GENRE(83),
    CONCEPT(84),
    ORIGINAL_VIDEO(85),
    SMART_SHUFFLE(86),
    LIVE_EVENTS(87),
    AUDIOBOOK_RELATIONS(88),
    HOME_POC_BASECARD(89),
    AUDIOBOOK_SUPPLEMENTS(90),
    PAID_PODCAST_BANNER(91),
    FEWER_ADS(92),
    WATCH_FEED_SHOW_EXPLORER(93),
    TRACK_EXTRA_DESCRIPTORS(94),
    TRACK_EXTRA_AUDIO_ATTRIBUTES(95),
    TRACK_EXTENDED_CREDITS(96),
    SIMPLE_TRAIT(97),
    AUDIO_ASSOCIATIONS(98),
    VIDEO_ASSOCIATIONS(99),
    PLAYLIST_TUNER(100),
    ARTIST_VIDEOS_ENTRYPOINT(101),
    ALBUM_PRERELEASE(102),
    CONTENT_ALTERNATIVES(103),
    SNAPSHOT_SHARING(105),
    DISPLAY_SEGMENTS_COUNT(106),
    PODCAST_FEATURED_EPISODE(107),
    PODCAST_SPONSORED_CONTENT(108),
    PODCAST_EPISODE_TOPICS_LLM(109),
    PODCAST_EPISODE_TOPICS_KG(110),
    EPISODE_RANKING_POPULARITY(111),
    MERCH(112),
    COMPANION_CONTENT(113),
    WATCH_FEED_ENTITY_EXPLORER(114),
    ANCHOR_CARD_TRAIT(115),
    AUDIO_PREVIEW_PLAYBACK_TRAIT(116),
    VIDEO_PREVIEW_STILL_TRAIT(117),
    PREVIEW_CARD_TRAIT(118),
    SHORTCUTS_CARD_TRAIT(119),
    VIDEO_PREVIEW_PLAYBACK_TRAIT(120),
    COURSE_SPECIFICS(121),
    CONCERT(122),
    CONCERT_LOCATION(123),
    CONCERT_MARKETING(124),
    CONCERT_PERFORMERS(125),
    UNRECOGNIZED(-1);

    private final int value;

    ExtensionKind(int i) {
        this.value = i;
    }

    public static ExtensionKind a(int i) {
        switch (i) {
            case 0:
                return UNKNOWN_EXTENSION;
            case 1:
                return CANVAZ;
            case 2:
                return STORYLINES;
            case 3:
                return PODCAST_TOPICS;
            case 4:
                return PODCAST_SEGMENTS;
            case 5:
                return AUDIO_FILES;
            case 6:
                return TRACK_DESCRIPTOR;
            case 7:
                return PODCAST_COUNTER;
            case 8:
                return ARTIST_V4;
            case 9:
                return ALBUM_V4;
            case 10:
                return TRACK_V4;
            case 11:
                return SHOW_V4;
            case 12:
                return EPISODE_V4;
            case 13:
                return PODCAST_HTML_DESCRIPTION;
            case 14:
                return PODCAST_QUOTES;
            case 15:
                return USER_PROFILE;
            case 16:
                return CANVAS_V1;
            case 17:
                return SHOW_V4_BASE;
            case 18:
                return SHOW_V4_EPISODES_ASSOC;
            case 19:
                return TRACK_DESCRIPTOR_SIGNATURES;
            case 20:
                return PODCAST_AD_SEGMENTS;
            case 21:
                return EPISODE_TRANSCRIPTS;
            case 22:
                return PODCAST_SUBSCRIPTIONS;
            case 23:
                return EXTRACTED_COLOR;
            case 24:
                return PODCAST_VIRALITY;
            case 25:
                return IMAGE_SPARKLES_HACK;
            case 26:
                return PODCAST_POPULARITY_HACK;
            case 27:
                return AUTOMIX_MODE;
            case 28:
                return CUEPOINTS;
            case 29:
                return PODCAST_POLL;
            case 30:
                return EPISODE_ACCESS;
            case 31:
                return SHOW_ACCESS;
            case ConnectMessages$StateResponse.Device.VOLUME_STEPS_FIELD_NUMBER /* 32 */:
                return PODCAST_QNA;
            case ConnectMessages$StateResponse.Device.CAPABILITIES_FIELD_NUMBER /* 33 */:
                return CLIPS;
            case ConnectMessages$StateResponse.Device.INCARNATIONS_FIELD_NUMBER /* 34 */:
                return SHOW_V5;
            case ConnectMessages$StateResponse.Device.ALIAS_ID_FIELD_NUMBER /* 35 */:
                return EPISODE_V5;
            case ConnectMessages$StateResponse.Device.SUPPORTS_DJ_FIELD_NUMBER /* 36 */:
                return PODCAST_CTA_CARDS;
            case ConnectMessages$StateResponse.Device.LIBRARY_VERSION_FIELD_NUMBER /* 37 */:
                return PODCAST_RATING;
            case ConnectMessages$StateResponse.Device.AUDIO_OUTPUT_DEVICE_NAME_FIELD_NUMBER /* 38 */:
                return DISPLAY_SEGMENTS;
            case ConnectMessages$StateResponse.Device.AUDIO_OUTPUT_DEVICE_TYPE_FIELD_NUMBER /* 39 */:
                return GREENROOM;
            case 40:
                return USER_CREATED;
            case 41:
                return SHOW_DESCRIPTION;
            case 42:
                return SHOW_HTML_DESCRIPTION;
            case 43:
                return SHOW_PLAYABILITY;
            case 44:
                return EPISODE_DESCRIPTION;
            case 45:
                return EPISODE_HTML_DESCRIPTION;
            case 46:
                return EPISODE_PLAYABILITY;
            case 47:
                return SHOW_EPISODES_ASSOC;
            case 48:
                return CLIENT_CONFIG;
            case 49:
                return PLAYLISTABILITY;
            case 50:
                return AUDIOBOOK_V5;
            case 51:
                return CHAPTER_V5;
            case 52:
                return AUDIOBOOK_SPECIFICS;
            case 53:
                return EPISODE_RANKING;
            case 54:
                return HTML_DESCRIPTION;
            case 55:
                return CREATOR_CHANNEL;
            case 56:
                return AUDIOBOOK_PROVIDERS;
            case 57:
                return PLAY_TRAIT;
            case 58:
                return CONTENT_WARNING;
            case 59:
                return IMAGE_CUE;
            case 60:
                return STREAM_COUNT;
            case 61:
                return AUDIO_ATTRIBUTES;
            case 62:
                return NAVIGABLE_TRAIT;
            case 63:
                return NEXT_BEST_EPISODE;
            case 64:
                return AUDIOBOOK_PRICE;
            case 65:
                return EXPRESSIVE_PLAYLISTS;
            case 66:
                return DYNAMIC_SHOW_EPISODE;
            case 67:
                return LIVE;
            case 68:
                return SKIP_PLAYED;
            case 69:
                return AD_BREAK_FREE_PODCASTS;
            case 70:
                return ASSOCIATIONS;
            case 71:
                return PLAYLIST_EVALUATION;
            case 72:
                return CACHE_INVALIDATIONS;
            case 73:
                return LIVESTREAM_ENTITY;
            case 74:
                return SINGLE_TAP_REACTIONS;
            case 75:
                return USER_COMMENTS;
            case 76:
                return CLIENT_RESTRICTIONS;
            case 77:
                return PODCAST_GUEST;
            case 78:
                return PLAYABILITY;
            case 79:
                return COVER_IMAGE;
            case 80:
                return SHARE_TRAIT;
            case 81:
                return INSTANCE_SHARING;
            case 82:
                return ARTIST_TOUR;
            case 83:
                return AUDIOBOOK_GENRE;
            case 84:
                return CONCEPT;
            case 85:
                return ORIGINAL_VIDEO;
            case 86:
                return SMART_SHUFFLE;
            case 87:
                return LIVE_EVENTS;
            case 88:
                return AUDIOBOOK_RELATIONS;
            case 89:
                return HOME_POC_BASECARD;
            case 90:
                return AUDIOBOOK_SUPPLEMENTS;
            case 91:
                return PAID_PODCAST_BANNER;
            case 92:
                return FEWER_ADS;
            case 93:
                return WATCH_FEED_SHOW_EXPLORER;
            case 94:
                return TRACK_EXTRA_DESCRIPTORS;
            case 95:
                return TRACK_EXTRA_AUDIO_ATTRIBUTES;
            case 96:
                return TRACK_EXTENDED_CREDITS;
            case 97:
                return SIMPLE_TRAIT;
            case 98:
                return AUDIO_ASSOCIATIONS;
            case 99:
                return VIDEO_ASSOCIATIONS;
            case LoadedFlagsKt.INITIAL_CAPACITY /* 100 */:
                return PLAYLIST_TUNER;
            case 101:
                return ARTIST_VIDEOS_ENTRYPOINT;
            case 102:
                return ALBUM_PRERELEASE;
            case 103:
                return CONTENT_ALTERNATIVES;
            case 104:
            default:
                return null;
            case 105:
                return SNAPSHOT_SHARING;
            case 106:
                return DISPLAY_SEGMENTS_COUNT;
            case 107:
                return PODCAST_FEATURED_EPISODE;
            case 108:
                return PODCAST_SPONSORED_CONTENT;
            case 109:
                return PODCAST_EPISODE_TOPICS_LLM;
            case 110:
                return PODCAST_EPISODE_TOPICS_KG;
            case 111:
                return EPISODE_RANKING_POPULARITY;
            case 112:
                return MERCH;
            case 113:
                return COMPANION_CONTENT;
            case 114:
                return WATCH_FEED_ENTITY_EXPLORER;
            case 115:
                return ANCHOR_CARD_TRAIT;
            case 116:
                return AUDIO_PREVIEW_PLAYBACK_TRAIT;
            case 117:
                return VIDEO_PREVIEW_STILL_TRAIT;
            case 118:
                return PREVIEW_CARD_TRAIT;
            case 119:
                return SHORTCUTS_CARD_TRAIT;
            case 120:
                return VIDEO_PREVIEW_PLAYBACK_TRAIT;
            case 121:
                return COURSE_SPECIFICS;
            case 122:
                return CONCERT;
            case 123:
                return CONCERT_LOCATION;
            case 124:
                return CONCERT_MARKETING;
            case 125:
                return CONCERT_PERFORMERS;
        }
    }

    @Override // p.n51
    public final int getNumber() {
        if (this != UNRECOGNIZED) {
            return this.value;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }
}
