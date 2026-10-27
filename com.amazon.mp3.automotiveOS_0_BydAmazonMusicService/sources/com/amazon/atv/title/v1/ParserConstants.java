package com.amazon.atv.title.v1;

import com.amazon.atv.title.v1.fragment.accessibility.v1.AccessibilityFragmentV1;
import com.amazon.atv.title.v1.fragment.acquisition.v1.AcquisitionFragmentV1;
import com.amazon.atv.title.v1.fragment.catalog.v1.CatalogFragmentV1;
import com.amazon.atv.title.v1.fragment.computed.v1.ComputedFragmentV1;
import com.amazon.atv.title.v1.fragment.family.v1.FamilyFragmentV1;
import com.amazon.atv.title.v1.fragment.images.v1.ImagesFragmentV1;
import com.amazon.atv.title.v1.fragment.imdb.v1.ImdbFragmentV1;
import com.amazon.atv.title.v1.fragment.playback.v2.PlaybackFragmentV2;
import com.amazon.atv.title.v1.fragment.reviews.v1.ReviewsFragmentV1;
import com.amazon.avod.util.json.ParserMapFunction;

/* JADX INFO: loaded from: classes2.dex */
public class ParserConstants {
    public static final ParserMapFunction<AccessibilityFragment> ACCESSIBILITYFRAGMENT_TYPE_PARSER_MAP_FUNCTION = ParserMapFunction.Builder.newBuilder().addMatch(AccessibilityFragmentV1.Parser.class, "version", "1.0").build();
    public static final ParserMapFunction<AcquisitionFragment> ACQUISITIONFRAGMENT_TYPE_PARSER_MAP_FUNCTION = ParserMapFunction.Builder.newBuilder().addMatch(AcquisitionFragmentV1.Parser.class, "version", "1.0").build();
    public static final ParserMapFunction<CatalogFragment> CATALOGFRAGMENT_TYPE_PARSER_MAP_FUNCTION = ParserMapFunction.Builder.newBuilder().addMatch(CatalogFragmentV1.Parser.class, "version", "1.0").build();
    public static final ParserMapFunction<ImagesFragment> IMAGESFRAGMENT_TYPE_PARSER_MAP_FUNCTION = ParserMapFunction.Builder.newBuilder().addMatch(ImagesFragmentV1.Parser.class, "version", "1.0").build();
    public static final ParserMapFunction<ImdbFragment> IMDBFRAGMENT_TYPE_PARSER_MAP_FUNCTION = ParserMapFunction.Builder.newBuilder().addMatch(ImdbFragmentV1.Parser.class, "version", "1.0").build();
    public static final ParserMapFunction<ReviewsFragment> REVIEWSFRAGMENT_TYPE_PARSER_MAP_FUNCTION = ParserMapFunction.Builder.newBuilder().addMatch(ReviewsFragmentV1.Parser.class, "version", "1.0").build();
    public static final ParserMapFunction<ComputedFragment> COMPUTEDFRAGMENT_TYPE_PARSER_MAP_FUNCTION = ParserMapFunction.Builder.newBuilder().addMatch(ComputedFragmentV1.Parser.class, "version", "1.0").build();
    public static final ParserMapFunction<PlaybackFragment> PLAYBACKFRAGMENT_TYPE_PARSER_MAP_FUNCTION = ParserMapFunction.Builder.newBuilder().addMatch(PlaybackFragmentV2.Parser.class, "version", "2.0").build();
    public static final ParserMapFunction<FamilyFragment> FAMILYFRAGMENT_TYPE_PARSER_MAP_FUNCTION = ParserMapFunction.Builder.newBuilder().addMatch(FamilyFragmentV1.Parser.class, "version", "1.0").build();
}
