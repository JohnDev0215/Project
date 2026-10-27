package com.android.car.settings.units;

import com.android.car.settings.R;
import java.util.HashMap;

/* JADX INFO: loaded from: classes5.dex */
public final class UnitsMap {
    protected static final Unit METER_PER_SEC = new Unit(1, R.string.units_unit_abbreviation_meter_per_sec, R.string.units_unit_name_meter_per_sec);
    protected static final Unit RPM = new Unit(2, R.string.units_unit_abbreviation_rpm, R.string.units_unit_name_rpm);
    protected static final Unit HERTZ = new Unit(3, R.string.units_unit_abbreviation_hertz, R.string.units_unit_name_hertz);
    protected static final Unit PERCENTILE = new Unit(16, R.string.units_unit_abbreviation_percentile, R.string.units_unit_name_percentile);
    protected static final Unit MILLIMETER = new Unit(32, R.string.units_unit_abbreviation_millimeter, R.string.units_unit_name_millimeter);
    protected static final Unit METER = new Unit(33, R.string.units_unit_abbreviation_meter, R.string.units_unit_name_meter);
    protected static final Unit KILOMETER = new Unit(35, R.string.units_unit_abbreviation_kilometer, R.string.units_unit_name_kilometer);
    protected static final Unit MILE = new Unit(36, R.string.units_unit_abbreviation_mile, R.string.units_unit_name_mile);
    protected static final Unit CELSIUS = new Unit(48, R.string.units_unit_abbreviation_celsius, R.string.units_unit_name_celsius);
    protected static final Unit FAHRENHEIT = new Unit(49, R.string.units_unit_abbreviation_fahrenheit, R.string.units_unit_name_fahrenheit);
    protected static final Unit KELVIN = new Unit(50, R.string.units_unit_abbreviation_kelvin, R.string.units_unit_name_kelvin);
    protected static final Unit MILLILITER = new Unit(64, R.string.units_unit_abbreviation_milliliter, R.string.units_unit_name_milliliter);
    protected static final Unit LITER = new Unit(65, R.string.units_unit_abbreviation_liter, R.string.units_unit_name_liter);
    protected static final Unit US_GALLON = new Unit(66, R.string.units_unit_abbreviation_us_gallon, R.string.units_unit_name_us_gallon);
    protected static final Unit IMPERIAL_GALLON = new Unit(67, R.string.units_unit_abbreviation_imperial_gallon, R.string.units_unit_name_imperial_gallon);
    protected static final Unit NANO_SECS = new Unit(80, R.string.units_unit_abbreviation_nano_secs, R.string.units_unit_name_nano_secs);
    protected static final Unit SECS = new Unit(83, R.string.units_unit_abbreviation_secs, R.string.units_unit_name_secs);
    protected static final Unit YEAR = new Unit(89, R.string.units_unit_abbreviation_year, R.string.units_unit_name_year);
    protected static final Unit KILOPASCAL = new Unit(112, R.string.units_unit_abbreviation_kilopascal, R.string.units_unit_name_kilopascal);
    protected static final Unit WATT_HOUR = new Unit(96, R.string.units_unit_abbreviation_watt_hour, R.string.units_unit_name_watt_hour);
    protected static final Unit MILLIAMPERE = new Unit(97, R.string.units_unit_abbreviation_milliampere, R.string.units_unit_name_milliampere);
    protected static final Unit MILLIVOLT = new Unit(98, R.string.units_unit_abbreviation_millivolt, R.string.units_unit_name_millivolt);
    protected static final Unit MILLIWATTS = new Unit(99, R.string.units_unit_abbreviation_milliwatts, R.string.units_unit_name_milliwatts);
    protected static final Unit AMPERE_HOURS = new Unit(100, R.string.units_unit_abbreviation_ampere_hour, R.string.units_unit_name_ampere_hour);
    protected static final Unit KILOWATT_HOUR = new Unit(101, R.string.units_unit_abbreviation_kilowatt_hour, R.string.units_unit_name_kilowatt_hour);
    protected static final Unit PSI = new Unit(113, R.string.units_unit_abbreviation_psi, R.string.units_unit_name_psi);
    protected static final Unit BAR = new Unit(114, R.string.units_unit_abbreviation_bar, R.string.units_unit_name_bar);
    protected static final Unit DEGREES = new Unit(128, R.string.units_unit_abbreviation_degrees, R.string.units_unit_name_degrees);
    protected static final Unit MILES_PER_HOUR = new Unit(144, R.string.units_unit_abbreviation_miles_per_hour, R.string.units_unit_name_miles_per_hour);
    protected static final Unit KILOMETERS_PER_HOUR = new Unit(145, R.string.units_unit_abbreviation_kilometers_per_hour, R.string.units_unit_name_kilometers_per_hour);
    public static final HashMap<Integer, Unit> MAP = createMap();

    private static HashMap<Integer, Unit> createMap() {
        HashMap<Integer, Unit> map = new HashMap<>();
        map.put(1, METER_PER_SEC);
        map.put(2, RPM);
        map.put(3, HERTZ);
        map.put(16, PERCENTILE);
        map.put(32, MILLIMETER);
        map.put(33, METER);
        map.put(35, KILOMETER);
        map.put(36, MILE);
        map.put(48, CELSIUS);
        map.put(49, FAHRENHEIT);
        map.put(50, KELVIN);
        map.put(64, MILLILITER);
        map.put(65, LITER);
        map.put(66, US_GALLON);
        map.put(67, IMPERIAL_GALLON);
        map.put(80, NANO_SECS);
        map.put(83, SECS);
        map.put(89, YEAR);
        map.put(112, KILOPASCAL);
        map.put(96, WATT_HOUR);
        map.put(97, MILLIAMPERE);
        map.put(98, MILLIVOLT);
        map.put(99, MILLIWATTS);
        map.put(100, AMPERE_HOURS);
        map.put(101, KILOWATT_HOUR);
        map.put(113, PSI);
        map.put(114, BAR);
        map.put(128, DEGREES);
        map.put(144, MILES_PER_HOUR);
        map.put(145, KILOMETERS_PER_HOUR);
        return map;
    }

    private UnitsMap() {
    }
}
