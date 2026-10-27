package com.amazon.avod.xray;

import com.amazon.avod.util.json.ParserMapFunction;
import com.amazon.avod.xray.XRayAddAction;
import com.amazon.avod.xray.XRayItemActionTarget;
import com.amazon.avod.xray.XRayLegacyFragment;
import com.amazon.avod.xray.XRayLiveSwiftFragment;
import com.amazon.avod.xray.XRayRemoveAction;
import com.amazon.avod.xray.XRaySwiftFragment;
import com.amazon.avod.xray.XRayUpdateAction;
import com.amazon.avod.xray.XRayWidgetActionTarget;
import com.amazon.identity.auth.device.utils.AccountConstants;

/* JADX INFO: loaded from: classes2.dex */
public class ParserConstants {
    public static final ParserMapFunction<XRayFragmentBase> XRAYFRAGMENTBASE_TYPE_PARSER_MAP_FUNCTION = ParserMapFunction.Builder.newBuilder().addMatch(XRayLegacyFragment.Parser.class, "version", FragmentVersion.LEGACY.getValue()).addMatch(XRaySwiftFragment.Parser.class, "version", FragmentVersion.SWIFT.getValue()).addMatch(XRayLiveSwiftFragment.Parser.class, "version", FragmentVersion.LIVE_SWIFT.getValue()).build();
    public static final ParserMapFunction<XRayActionTargetBase> XRAYACTIONTARGETBASE_TYPE_PARSER_MAP_FUNCTION = ParserMapFunction.Builder.newBuilder().addMatch(XRayWidgetActionTarget.Parser.class, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, XRayActionTargetType.WIDGET.getValue(), "version", "1").addMatch(XRayItemActionTarget.Parser.class, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, XRayActionTargetType.ITEM.getValue(), "version", "1").build();
    public static final ParserMapFunction<XRayBaseAction> XRAYBASEACTION_TYPE_PARSER_MAP_FUNCTION = ParserMapFunction.Builder.newBuilder().addMatch(XRayRemoveAction.Parser.class, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, XRayActionType.REMOVE.getValue(), "version", "1").addMatch(XRayAddAction.Parser.class, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, XRayActionType.ADD.getValue(), "version", "1").addMatch(XRayUpdateAction.Parser.class, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, XRayActionType.UPDATE.getValue(), "version", "1").build();
}
