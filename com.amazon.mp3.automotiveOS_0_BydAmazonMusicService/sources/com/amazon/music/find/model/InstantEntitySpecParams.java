package com.amazon.music.find.model;

import com.amazon.music.skyfire.ui.skyfire.Contexts;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* JADX INFO: compiled from: InstantEntitySpecParams.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u001a\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\nR\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00040\b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\nR\u000e\u0010\u001d\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00040\b¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\nR\u000e\u0010 \u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/amazon/music/find/model/InstantEntitySpecParams;", "", "()V", "ALBUM_ASIN", "", "ALBUM_NAME", "ARTIST_ASIN", "ARTIST_FIELD_SPEC", "", "getARTIST_FIELD_SPEC", "()Ljava/util/List;", "ARTIST_NAME", "ARTIST_TYPE_SPEC", "ART_ORIGINAL", "ASIN", "CONTENT_TIER", "EXPLICIT_LANGUAGE_FILTER_FIELD", "getEXPLICIT_LANGUAGE_FILTER_FIELD", "()Ljava/lang/String;", "HAS_EXPLICIT_LANGUAGE", "ID", Contexts.LibraryArtistDetail.name, "PODCAST_SHOW_FIELD_SPEC", "getPODCAST_SHOW_FIELD_SPEC", "PODCAST_SHOW_TYPE_SPEC", "PROVIDER", "TERM", "TEXT_FIELD_SPEC", "getTEXT_FIELD_SPEC", "TITLE", "TRACK_FIELD_SPEC", "getTRACK_FIELD_SPEC", "TRACK_TYPE_SPEC", "VARIANT_ID", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class InstantEntitySpecParams {
    private static final String ARTIST_ASIN = "artistAsin";
    public static final String ARTIST_TYPE_SPEC = "catalog_artist";
    private static final String NAME = "name";
    public static final String PODCAST_SHOW_TYPE_SPEC = "catalog_podcast_show";
    public static final String TRACK_TYPE_SPEC = "catalog_track";
    public static final InstantEntitySpecParams INSTANCE = new InstantEntitySpecParams();
    private static final String EXPLICIT_LANGUAGE_FILTER_FIELD = "explicitLanguageFilter";
    private static final String ID = "id";
    private static final String TERM = "term";
    private static final List<String> TEXT_FIELD_SPEC = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{ID, TERM});
    private static final String ASIN = "asin";
    private static final String ART_ORIGINAL = "artOriginal";
    private static final List<String> ARTIST_FIELD_SPEC = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{ASIN, "name", ART_ORIGINAL});
    private static final String TITLE = "title";
    private static final String HAS_EXPLICIT_LANGUAGE = "parentalControls.hasExplicitLanguage";
    private static final String ALBUM_NAME = "albumName";
    private static final String ALBUM_ASIN = "albumAsin";
    private static final String CONTENT_TIER = "contentTier";
    private static final String ARTIST_NAME = "artistName";
    private static final List<String> TRACK_FIELD_SPEC = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{TITLE, ASIN, ART_ORIGINAL, HAS_EXPLICIT_LANGUAGE, ALBUM_NAME, ALBUM_ASIN, CONTENT_TIER, ARTIST_NAME, "artistAsin"});
    private static final String PROVIDER = "provider";
    private static final String VARIANT_ID = "variantId";
    private static final List<String> PODCAST_SHOW_FIELD_SPEC = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{TITLE, ID, HAS_EXPLICIT_LANGUAGE, ART_ORIGINAL, CONTENT_TIER, PROVIDER, VARIANT_ID});

    private InstantEntitySpecParams() {
    }

    public final String getEXPLICIT_LANGUAGE_FILTER_FIELD() {
        return EXPLICIT_LANGUAGE_FILTER_FIELD;
    }

    public final List<String> getTEXT_FIELD_SPEC() {
        return TEXT_FIELD_SPEC;
    }

    public final List<String> getARTIST_FIELD_SPEC() {
        return ARTIST_FIELD_SPEC;
    }

    public final List<String> getTRACK_FIELD_SPEC() {
        return TRACK_FIELD_SPEC;
    }

    public final List<String> getPODCAST_SHOW_FIELD_SPEC() {
        return PODCAST_SHOW_FIELD_SPEC;
    }
}
