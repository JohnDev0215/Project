package com.android.car;

import android.car.drivingstate.CarUxRestrictionsConfiguration;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes3.dex */
public final class CarUxRestrictionsConfigurationXmlParser {
    private static final String CONTENT_RESTRICTIONS = "ContentRestrictions";
    private static final String DRIVING_STATE = "DrivingState";
    private static final float INVALID_SPEED = -1.0f;
    private static final String RESTRICTIONS = "Restrictions";
    private static final String RESTRICTION_MAPPING = "RestrictionMapping";
    private static final String RESTRICTION_PARAMETERS = "RestrictionParameters";
    private static final String ROOT_ELEMENT = "UxRestrictions";
    private static final String STRING_RESTRICTIONS = "StringRestrictions";
    private static final String TAG = "UxRConfigParser";
    private static final int UX_RESTRICTIONS_UNKNOWN = -1;
    private final Context mContext;
    private int mMaxRestrictedStringLength = -1;
    private int mMaxCumulativeContentItems = -1;
    private int mMaxContentDepth = -1;
    private final List<CarUxRestrictionsConfiguration.Builder> mConfigBuilders = new ArrayList();

    private CarUxRestrictionsConfigurationXmlParser(Context context) {
        this.mContext = context;
    }

    public static List<CarUxRestrictionsConfiguration> parse(Context context, int xmlResource) throws XmlPullParserException, IOException {
        return new CarUxRestrictionsConfigurationXmlParser(context).parse(xmlResource);
    }

    private List<CarUxRestrictionsConfiguration> parse(int xmlResource) throws XmlPullParserException, IOException {
        XmlResourceParser parser = this.mContext.getResources().getXml(xmlResource);
        if (parser == null) {
            Log.e(TAG, "Invalid Xml resource");
            return null;
        }
        if (!traverseUntilStartTag(parser)) {
            Log.e(TAG, "XML root element invalid: " + parser.getName());
            return null;
        }
        if (!traverseUntilEndOfDocument(parser)) {
            Log.e(TAG, "Could not parse XML to end");
            return null;
        }
        List<CarUxRestrictionsConfiguration> configs = new ArrayList<>();
        for (CarUxRestrictionsConfiguration.Builder builder : this.mConfigBuilders) {
            builder.setMaxStringLength(this.mMaxRestrictedStringLength).setMaxCumulativeContentItems(this.mMaxCumulativeContentItems).setMaxContentDepth(this.mMaxContentDepth);
            configs.add(builder.build());
        }
        return configs;
    }

    private boolean traverseUntilStartTag(XmlResourceParser parser) throws XmlPullParserException, IOException {
        int type;
        do {
            type = parser.next();
            if (type == 1) {
                break;
            }
        } while (type != 2);
        return ROOT_ELEMENT.equals(parser.getName());
    }

    private boolean traverseUntilEndOfDocument(XmlResourceParser parser) throws XmlPullParserException, IOException {
        AttributeSet attrs = Xml.asAttributeSet(parser);
        while (parser.getEventType() != 1) {
            if (parser.next() == 2) {
                String name = parser.getName();
                byte b = -1;
                int iHashCode = name.hashCode();
                if (iHashCode != 545370806) {
                    if (iHashCode == 1664662402 && name.equals(RESTRICTION_MAPPING)) {
                        b = 0;
                    }
                } else if (name.equals(RESTRICTION_PARAMETERS)) {
                    b = 1;
                }
                if (b == 0) {
                    this.mConfigBuilders.add(new CarUxRestrictionsConfiguration.Builder());
                    if (!mapDrivingStateToRestrictions(parser, attrs)) {
                        Log.e(TAG, "Could not map driving state to restriction.");
                        return false;
                    }
                } else if (b == 1) {
                    if (!parseRestrictionParameters(parser, attrs) && Log.isLoggable(TAG, 4)) {
                        Log.i(TAG, "Error reading restrictions parameters. Falling back to platform defaults.");
                    }
                } else {
                    Log.w(TAG, "Unknown class:" + parser.getName());
                }
            }
        }
        return true;
    }

    private boolean mapDrivingStateToRestrictions(XmlResourceParser parser, AttributeSet attrs) throws XmlPullParserException, IOException {
        if (parser == null || attrs == null) {
            Log.e(TAG, "Invalid arguments");
            return false;
        }
        if (!RESTRICTION_MAPPING.equals(parser.getName())) {
            Log.e(TAG, "Parser not at RestrictionMapping element: " + parser.getName());
            return false;
        }
        TypedArray a = this.mContext.getResources().obtainAttributes(attrs, R.styleable.UxRestrictions_RestrictionMapping);
        if (a.hasValue(0)) {
            int portValue = a.getInt(0, 0);
            byte port = CarUxRestrictionsConfiguration.Builder.validatePort(portValue);
            getCurrentBuilder().setPhysicalPort(port);
        }
        a.recycle();
        if (!traverseToTag(parser, DRIVING_STATE)) {
            Log.e(TAG, "No <DrivingState> tag in XML");
            return false;
        }
        while (DRIVING_STATE.equals(parser.getName())) {
            if (parser.getEventType() == 2) {
                TypedArray a2 = this.mContext.getResources().obtainAttributes(attrs, R.styleable.UxRestrictions_DrivingState);
                int drivingState = a2.getInt(2, -1);
                float minSpeed = a2.getFloat(1, INVALID_SPEED);
                float maxSpeed = a2.getFloat(0, Float.POSITIVE_INFINITY);
                a2.recycle();
                if (!traverseToTag(parser, RESTRICTIONS)) {
                    Log.e(TAG, "No <Restrictions> tag in XML");
                    return false;
                }
                CarUxRestrictionsConfiguration.Builder.SpeedRange speedRange = parseSpeedRange(minSpeed, maxSpeed);
                if (!parseAllRestrictions(parser, attrs, drivingState, speedRange)) {
                    Log.e(TAG, "Could not parse restrictions for driving state:" + drivingState);
                    return false;
                }
            }
            parser.next();
        }
        return true;
    }

    private boolean parseAllRestrictions(XmlResourceParser parser, AttributeSet attrs, int drivingState, CarUxRestrictionsConfiguration.Builder.SpeedRange speedRange) throws XmlPullParserException, IOException {
        if (parser == null || attrs == null) {
            Log.e(TAG, "Invalid arguments");
            return false;
        }
        if (!RESTRICTIONS.equals(parser.getName())) {
            Log.e(TAG, "Parser not at Restrictions element: " + parser.getName());
            return false;
        }
        while (RESTRICTIONS.equals(parser.getName())) {
            if (parser.getEventType() == 2) {
                CarUxRestrictionsConfiguration.DrivingStateRestrictions restrictions = parseRestrictions(parser, attrs);
                if (restrictions == null) {
                    Log.e(TAG, "");
                    return false;
                }
                restrictions.setSpeedRange(speedRange);
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Map " + drivingState + " : " + restrictions);
                }
                if (drivingState != -1 && restrictions != null) {
                    getCurrentBuilder().setUxRestrictions(drivingState, restrictions);
                }
            }
            parser.next();
        }
        return true;
    }

    private CarUxRestrictionsConfiguration.DrivingStateRestrictions parseRestrictions(XmlResourceParser parser, AttributeSet attrs) throws XmlPullParserException, IOException {
        if (parser == null || attrs == null) {
            Log.e(TAG, "Invalid Arguments");
            return null;
        }
        int restrictions = -1;
        String restrictionMode = "baseline";
        boolean requiresOpt = true;
        while (RESTRICTIONS.equals(parser.getName()) && parser.getEventType() == 2) {
            TypedArray a = this.mContext.getResources().obtainAttributes(attrs, R.styleable.UxRestrictions_Restrictions);
            restrictions = a.getInt(2, 511);
            requiresOpt = a.getBoolean(1, true);
            restrictionMode = a.getString(0);
            a.recycle();
            parser.next();
        }
        if (restrictionMode == null) {
            restrictionMode = "baseline";
        }
        return new CarUxRestrictionsConfiguration.DrivingStateRestrictions().setDistractionOptimizationRequired(requiresOpt).setRestrictions(restrictions).setMode(restrictionMode);
    }

    private CarUxRestrictionsConfiguration.Builder.SpeedRange parseSpeedRange(float minSpeed, float maxSpeed) {
        if (Float.compare(minSpeed, 0.0f) < 0 || Float.compare(maxSpeed, 0.0f) < 0) {
            return null;
        }
        return new CarUxRestrictionsConfiguration.Builder.SpeedRange(minSpeed, maxSpeed);
    }

    private boolean traverseToTag(XmlResourceParser parser, String tag) throws XmlPullParserException, IOException {
        if (tag == null || parser == null) {
            return false;
        }
        while (true) {
            int type = parser.next();
            if (type == 1) {
                return false;
            }
            if (type == 2 && parser.getName().equals(tag)) {
                return true;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00c5 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x002e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean parseRestrictionParameters(android.content.res.XmlResourceParser r12, android.util.AttributeSet r13) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 209
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.CarUxRestrictionsConfigurationXmlParser.parseRestrictionParameters(android.content.res.XmlResourceParser, android.util.AttributeSet):boolean");
    }

    private CarUxRestrictionsConfiguration.Builder getCurrentBuilder() {
        return this.mConfigBuilders.get(r0.size() - 1);
    }
}
