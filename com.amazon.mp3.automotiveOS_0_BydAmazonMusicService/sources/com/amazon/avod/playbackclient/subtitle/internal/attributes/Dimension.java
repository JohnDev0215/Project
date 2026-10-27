package com.amazon.avod.playbackclient.subtitle.internal.attributes;

import com.amazon.avod.util.DLog;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class Dimension {
    public static final Dimension AUTO = new Dimension(-1.0d, -1.0d);
    private static final Pattern PERCENTAGE_REGEX = Pattern.compile("\\s*(\\d{0,2}\\.?\\d{0,2}|\\d{0,2}\\.?\\d{0,2})[%|vh|vw]\\s+(\\d{0,2}\\.?\\d{0,2}|\\d{0,2}\\.?\\d{0,2})[%|vh|vw]\\s*");
    private final double mX;
    private final double mY;

    private static boolean isDimensionWithinBounds(double d, double d2) {
        return d >= 0.0d && d2 >= 0.0d && d <= 100.0d && d2 <= 100.0d;
    }

    public static boolean isValid(@Nullable String str) {
        if (str == null) {
            return false;
        }
        if (DebugKt.DEBUG_PROPERTY_VALUE_AUTO.equals(str)) {
            return true;
        }
        Matcher matcher = PERCENTAGE_REGEX.matcher(str);
        if (!matchesRegex(matcher)) {
            return false;
        }
        try {
            Double.parseDouble(matcher.group(1));
            Double.parseDouble(matcher.group(2));
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    @Nullable
    public static Dimension from(@Nonnull String str) {
        Preconditions.checkNotNull(str, "Cannot parse a null dimension");
        if (DebugKt.DEBUG_PROPERTY_VALUE_AUTO.equals(str)) {
            return AUTO;
        }
        Matcher matcher = PERCENTAGE_REGEX.matcher(str);
        Preconditions.checkArgument(matchesRegex(matcher), "Could not parse dimension string %s", str);
        double d = Double.parseDouble(matcher.group(1));
        double d2 = Double.parseDouble(matcher.group(2));
        if (isDimensionWithinBounds(d, d2)) {
            return new Dimension(d, d2);
        }
        DLog.errorf("Invalid dimension values received: %f, %f", Double.valueOf(d), Double.valueOf(d2));
        return null;
    }

    private Dimension(double d, double d2) {
        this.mX = d;
        this.mY = d2;
    }

    public double getX() {
        return this.mX;
    }

    public double getY() {
        return this.mY;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("x", this.mX).add("y", this.mY).toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Dimension)) {
            return false;
        }
        Dimension dimension = (Dimension) obj;
        return Objects.equal(Double.valueOf(this.mX), Double.valueOf(dimension.getX())) && Objects.equal(Double.valueOf(this.mY), Double.valueOf(dimension.getY()));
    }

    public int hashCode() {
        return Objects.hashCode(Double.valueOf(this.mX), Double.valueOf(this.mY));
    }

    private static boolean matchesRegex(Matcher matcher) {
        return matcher.matches() && matcher.groupCount() == 2;
    }
}
