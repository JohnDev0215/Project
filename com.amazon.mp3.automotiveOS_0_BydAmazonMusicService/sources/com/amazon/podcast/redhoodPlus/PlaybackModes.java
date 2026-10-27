package com.amazon.podcast.redhoodPlus;

import UpsellInterface.v1_0.ContentTrait;
import UpsellInterface.v1_0.PlaybackMode;
import com.amazonaws.util.DateUtils;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class PlaybackModes {
    private static final Logger logger = LoggerFactory.getLogger(PlaybackModes.class.getSimpleName());

    public static final String mode(final String playbackMode) {
        if (!StringUtils.isBlank(playbackMode) && Arrays.asList(PlaybackMode.values()).contains(playbackMode)) {
            return playbackMode;
        }
        return null;
    }

    public static final String normalizeWithAvailabilityDate(final List<String> traits, final String availabilityDateString, final String playbackMode) {
        String strMode = mode(playbackMode);
        List<String> listTraits = ContentTraits.traits(traits);
        if (StringUtils.isBlank(availabilityDateString) || listTraits == null || listTraits.isEmpty() || !listTraits.contains(ContentTrait.PRE_RELEASE)) {
            return strMode;
        }
        try {
            return new SimpleDateFormat(DateUtils.ALTERNATE_ISO8601_DATE_PATTERN).parse(availabilityDateString).before(new Date()) ? PlaybackMode.FULL_ACCESS : strMode;
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            return PlaybackMode.FULL_ACCESS;
        }
    }
}
