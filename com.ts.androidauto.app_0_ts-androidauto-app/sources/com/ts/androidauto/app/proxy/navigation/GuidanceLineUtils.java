package com.ts.androidauto.app.proxy.navigation;

import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import ts.car.service.androidauto.data.NavigationDirectionData;
import ts.car.service.androidauto.data.NavigationLaneData;
import ts.car.service.androidauto.data.NavigationStepData;
import ts.car.service.carplay.data.LaneInformation;

/* JADX INFO: loaded from: classes2.dex */
public class GuidanceLineUtils {
    public static final int ANGLE_112 = 112;
    public static final int ANGLE_135 = 135;
    public static final int ANGLE_157 = 157;
    public static final int ANGLE_180 = 180;
    public static final int ANGLE_202 = 202;
    public static final int ANGLE_247 = 247;
    public static final int ANGLE_292 = 292;
    public static final int ANGLE_337 = 337;
    public static final int ANGLE_360 = 360;
    public static final int ANGLE_45 = 45;
    public static final int ANGLE_67 = 67;
    public static final int ANGLE_90 = 90;
    public static final int ANGLE_N_135 = -135;
    public static final int ANGLE_N_180 = -180;
    public static final int ANGLE_N_181 = -181;
    public static final int ANGLE_N_45 = -45;
    public static final int ANGLE_N_90 = -90;
    private static final float BASE_ANGLE = 22.5f;
    private static final int ICON_ID_200 = 200;
    private static final int ICON_ID_201 = 201;
    private static final int ICON_ID_300 = 8;
    private static final int ICON_ID_301 = 1;
    private static final int ICON_ID_302 = 2;
    private static final int ICON_ID_304 = 7;
    private static final int ICON_ID_311 = 8;
    private static final int ICON_ID_312 = 12;
    private static final int ICON_ID_313 = 3;
    private static final int ICON_ID_314 = 4;
    private static final int ICON_ID_315 = 58;
    private static final int ICON_ID_320 = 1;
    private static final int ICON_ID_321 = 2;
    private static final int ICON_ID_322 = 51;
    private static final int ICON_ID_323 = 53;
    private static final int ICON_ID_324 = 204;
    private static final int ICON_ID_325 = 203;
    private static final int ICON_ID_347 = 5;
    private static final int ICON_ID_348 = 6;
    private static final int ICON_ID_349 = 3;
    private static final int ICON_ID_350 = 4;
    private static final int ICON_ID_351 = 15;
    private static final int ICON_ID_354 = 202;
    private static final int ICON_ID_400 = 400;
    private static final int ICON_ID_401 = 401;
    private static final int ICON_ID_402 = 402;
    private static final int ICON_ID_403 = 403;
    private static final int ICON_ID_404 = 404;
    private static final int ICON_ID_405 = 405;
    private static final int ICON_ID_406 = 406;
    private static final int ICON_ID_408 = 408;
    private static final int ICON_ID_409 = 409;
    private static final int ICON_ID_410 = 410;
    private static final int ICON_ID_411 = 411;
    private static final int ICON_ID_412 = 412;
    private static final int ICON_ID_413 = 14;
    private static final int ICON_ID_414 = 414;
    private static final int ICON_ID_415 = 16;
    private static final int ICON_ID_416 = 17;
    private static final int ICON_ID_417 = 18;
    private static final int ICON_ID_418 = 19;
    private static final int ICON_ID_419 = 20;
    private static final int ICON_ID_420 = 21;
    private static final int ICON_ID_421 = 22;
    private static final int ICON_ID_422 = 23;
    private static final int ICON_ID_424 = 424;
    private static final int ICON_ID_426 = 426;
    private static final int ICON_ID_427 = 427;
    private static final int ICON_ID_428 = 428;
    private static final int ICON_ID_429 = 429;
    private static final int ICON_ID_430 = 430;
    private static final int ICON_ID_432 = 432;
    private static final int ICON_ID_433 = 433;
    private static final int ICON_ID_434 = 434;
    private static final int ICON_ID_436 = 436;
    private static final int ICON_ID_437 = 437;
    private static final int ICON_ID_440 = 440;
    private static final int ICON_ID_442 = 442;
    private static final int ICON_ID_443 = 44;
    private static final int ICON_ID_444 = 45;
    private static final int ICON_ID_445 = 46;
    private static final int ICON_ID_446 = 47;
    private static final int ICON_ID_448 = 448;
    private static final int ICON_ID_449 = 449;
    private static final int ICON_ID_452 = 452;
    private static final int ICON_ID_454 = 454;
    private static final int ICON_ID_456 = 456;
    private static final int ICON_ID_457 = 457;
    private static final int ICON_ID_458 = 59;
    private static final int ICON_ID_459 = 60;
    private static final int ICON_ID_460 = 61;
    private static final int ICON_ID_461 = 62;
    private static final int ICON_ID_462 = 63;
    private static final int ICON_ID_463 = 64;
    private static final int ICON_ID_464 = 49;
    private static final int ICON_ID_465 = 48;
    private static final int ICON_ID_466 = 466;
    private static final int ICON_ID_467 = 467;
    private static final int ICON_ID_468 = 468;
    private static final int ICON_ID_470 = 470;
    private static final int ICON_ID_471 = 471;
    private static final int ICON_ID_472 = 472;
    private static final int ICON_ID_473 = 473;
    private static final int ICON_ID_474 = 474;
    private static final int ICON_ID_476 = 476;
    private static final int ICON_ID_477 = 477;
    private static final int ICON_ID_478 = 478;
    private static final int ICON_ID_480 = 480;
    private static final int ICON_ID_481 = 481;
    private static final int ICON_ID_482 = 482;
    private static final int ICON_ID_483 = 483;
    private static final int ICON_ID_484 = 484;
    private static final int ICON_ID_486 = 486;
    private static final int ICON_ID_487 = 487;
    private static final int ICON_ID_488 = 488;
    private static final int ICON_ID_490 = 490;
    private static final int ICON_ID_491 = 491;
    private static final int ICON_ID_494 = 494;
    private static final int ICON_ID_496 = 496;
    private static final int ICON_ID_498 = 498;
    private static final int ICON_ID_499 = 499;
    private static final int ICON_ID_500 = 500;
    private static final int ICON_ID_502 = 502;
    private static final int ICON_ID_503 = 503;
    private static final int ICON_ID_506 = 506;
    private static final int ICON_ID_508 = 508;
    private static final int ICON_ID_510 = 510;
    private static final int ICON_ID_511 = 511;
    private static final int ICON_ID_514 = 514;
    private static final int ICON_ID_516 = 516;
    private static final int ICON_ID_534 = 534;
    private static final int ICON_ID_535 = 535;
    private static final int ICON_ID_536 = 536;
    private static final int ICON_ID_538 = 538;
    private static final int ICON_ID_539 = 539;
    private static final int ICON_ID_542 = 542;
    private static final int ICON_ID_544 = 544;
    private static final int ICON_ID_546 = 546;
    private static final int ICON_ID_547 = 547;
    private static final int ICON_ID_550 = 550;
    private static final int ICON_ID_552 = 552;
    private static final int ICON_ID_554 = 554;
    private static final int ICON_ID_555 = 555;
    private static final int ICON_ID_556 = 556;
    private static final int ICON_ID_557 = 557;
    private static final int ICON_ID_558 = 558;
    private static final int ICON_ID_560 = 560;
    private static final int ICON_ID_561 = 561;
    private static final int ICON_ID_562 = 562;
    private static final int ICON_ID_564 = 564;
    private static final int ICON_ID_565 = 565;
    private static final int ICON_ID_568 = 568;
    private static final int ICON_ID_570 = 570;
    private static final int ICON_ID_572 = 572;
    private static final int ICON_ID_573 = 573;
    private static final int ICON_ID_574 = 574;
    private static final int ICON_ID_576 = 576;
    private static final int ICON_ID_577 = 577;
    private static final int ICON_ID_580 = 580;
    private static final int ICON_ID_582 = 582;
    private static final int ICON_ID_584 = 584;
    private static final int ICON_ID_585 = 585;
    private static final int ICON_ID_588 = 588;
    private static final int ICON_ID_590 = 590;
    private static final int ICON_ID_592 = 592;
    private static final int ICON_ID_593 = 593;
    private static final int ICON_ID_594 = 594;
    private static final int ICON_ID_596 = 596;
    private static final int ICON_ID_597 = 597;
    private static final int ICON_ID_598 = 598;
    private static final int ICON_ID_600 = 600;
    private static final int ICON_ID_601 = 601;
    private static final int ICON_ID_604 = 604;
    private static final int ICON_ID_606 = 606;
    private static final int ICON_ID_608 = 608;
    private static final int ICON_ID_609 = 609;
    private static final int ICON_ID_610 = 610;
    private static final int ICON_ID_612 = 612;
    private static final int ICON_ID_613 = 613;
    private static final int ICON_ID_614 = 614;
    private static final int ICON_ID_616 = 616;
    private static final int ICON_ID_617 = 617;
    private static final int ICON_ID_620 = 620;
    private static final int ICON_ID_622 = 622;
    private static final int ICON_ID_624 = 624;
    private static final int ICON_ID_625 = 625;
    private static final int ICON_ID_628 = 628;
    private static final int ICON_ID_630 = 630;
    private static final int ICON_ID_632 = 632;
    private static final int ICON_ID_633 = 633;
    private static final int ICON_ID_636 = 636;
    private static final int ICON_ID_638 = 638;
    private static final int ICON_ID_640 = 640;
    private static final int ICON_ID_641 = 641;
    private static final int ICON_ID_642 = 642;
    private static final int ICON_ID_643 = 643;
    private static final int ICON_ID_644 = 644;
    private static final int ICON_ID_646 = 646;
    private static final int ICON_ID_647 = 647;
    private static final int ICON_ID_648 = 648;
    private static final int ICON_ID_650 = 650;
    private static final int ICON_ID_651 = 651;
    private static final int ICON_ID_652 = 652;
    private static final int NOT_GOOD_STATUS = 0;
    private static final int ONE_LINE = 1;
    private static final String TAG = "GuidanceLineUtils";
    private static final int THREE_LINE = 3;
    private static final int TWO_LINE = 2;
    private static Map<Integer, GuidanceLineInfo> mOneAngleGrayGuidanceMap = new HashMap();
    private static Map<Integer, GuidanceLineInfo> mOneAngleHighlightGuidanceMap = new HashMap();
    private static Map<Integer, GuidanceLineInfo> mTwoAngleGrayGuidanceMap = new HashMap();
    private static Map<Integer, GuidanceLineInfo> mTwoAngleHighlightGuidanceMap = new HashMap();
    private static Map<Integer, GuidanceLineInfo> mThreeAngleGrayGuidanceMap = new HashMap();
    private static Map<Integer, GuidanceLineInfo> mThreeAngleHighlightGuidanceMap = new HashMap();

    public static void initGuidanceLineIconMap() {
        mOneAngleGrayGuidanceMap.put(400, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.1
            {
                add(0);
            }
        }, 0));
        mOneAngleGrayGuidanceMap.put(402, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.2
            {
                add(45);
            }
        }, 0));
        mOneAngleGrayGuidanceMap.put(408, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.3
            {
                add(90);
            }
        }, 0));
        mOneAngleGrayGuidanceMap.put(426, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.4
            {
                add(135);
            }
        }, 0));
        mOneAngleGrayGuidanceMap.put(456, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.5
            {
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_180));
            }
        }, 0));
        mOneAngleGrayGuidanceMap.put(470, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.6
            {
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_135));
            }
        }, 0));
        mOneAngleGrayGuidanceMap.put(480, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.7
            {
                add(-90);
            }
        }, 0));
        mOneAngleGrayGuidanceMap.put(554, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.8
            {
                add(-45);
            }
        }, 0));
        mOneAngleGrayGuidanceMap.put(640, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.9
            {
                add(180);
            }
        }, 0));
        mOneAngleHighlightGuidanceMap.put(401, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.10
            {
                add(0);
            }
        }, 0));
        mOneAngleHighlightGuidanceMap.put(403, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.11
            {
                add(45);
            }
        }, 45));
        mOneAngleHighlightGuidanceMap.put(409, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.12
            {
                add(90);
            }
        }, 90));
        mOneAngleHighlightGuidanceMap.put(427, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.13
            {
                add(135);
            }
        }, 135));
        mOneAngleHighlightGuidanceMap.put(457, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.14
            {
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_180));
            }
        }, ANGLE_N_180));
        mOneAngleHighlightGuidanceMap.put(471, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.15
            {
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_135));
            }
        }, ANGLE_N_135));
        mOneAngleHighlightGuidanceMap.put(481, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.16
            {
                add(-90);
            }
        }, -90));
        mOneAngleHighlightGuidanceMap.put(555, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.17
            {
                add(-45);
            }
        }, -45));
        mOneAngleHighlightGuidanceMap.put(641, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.18
            {
                add(180);
            }
        }, 180));
        mTwoAngleGrayGuidanceMap.put(404, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.19
            {
                add(0);
                add(45);
            }
        }, 0));
        mTwoAngleGrayGuidanceMap.put(410, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.20
            {
                add(0);
                add(90);
            }
        }, 0));
        mTwoAngleGrayGuidanceMap.put(414, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.21
            {
                add(45);
                add(90);
            }
        }, 0));
        mTwoAngleGrayGuidanceMap.put(428, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.22
            {
                add(0);
                add(135);
            }
        }, 0));
        mTwoAngleGrayGuidanceMap.put(432, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.23
            {
                add(45);
                add(135);
            }
        }, 0));
        mTwoAngleGrayGuidanceMap.put(45, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.24
            {
                add(90);
                add(135);
            }
        }, 0));
        mTwoAngleGrayGuidanceMap.put(59, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.25
            {
                add(0);
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_180));
            }
        }, 0));
        mTwoAngleGrayGuidanceMap.put(63, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.26
            {
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_180));
                add(45);
            }
        }, 0));
        mTwoAngleGrayGuidanceMap.put(466, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.27
            {
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_180));
                add(90);
            }
        }, 0));
        mTwoAngleGrayGuidanceMap.put(472, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.28
            {
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_135));
                add(0);
            }
        }, 0));
        mTwoAngleGrayGuidanceMap.put(476, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.29
            {
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_180));
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_135));
            }
        }, 0));
        mTwoAngleGrayGuidanceMap.put(482, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.30
            {
                add(-90);
                add(0);
            }
        }, 0));
        mTwoAngleGrayGuidanceMap.put(486, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.31
            {
                add(-90);
                add(45);
            }
        }, 0));
        mTwoAngleGrayGuidanceMap.put(498, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.32
            {
                add(-90);
                add(90);
            }
        }, 0));
        mTwoAngleGrayGuidanceMap.put(534, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.33
            {
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_180));
                add(-90);
            }
        }, 0));
        mTwoAngleGrayGuidanceMap.put(556, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.34
            {
                add(-45);
                add(0);
            }
        }, 0));
        mTwoAngleGrayGuidanceMap.put(560, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.35
            {
                add(-45);
                add(45);
            }
        }, -45));
        mTwoAngleGrayGuidanceMap.put(572, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.36
            {
                add(-45);
                add(90);
            }
        }, 0));
        mTwoAngleGrayGuidanceMap.put(592, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.37
            {
                add(-45);
                add(135);
            }
        }, 0));
        mTwoAngleGrayGuidanceMap.put(596, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.38
            {
                add(-45);
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_180));
            }
        }, 0));
        mTwoAngleGrayGuidanceMap.put(608, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.39
            {
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_135));
                add(-45);
            }
        }, 0));
        mTwoAngleGrayGuidanceMap.put(612, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.40
            {
                add(-90);
                add(-45);
            }
        }, 0));
        mTwoAngleGrayGuidanceMap.put(642, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.41
            {
                add(0);
                add(180);
            }
        }, 0));
        mTwoAngleGrayGuidanceMap.put(646, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.42
            {
                add(45);
                add(180);
            }
        }, 0));
        mTwoAngleGrayGuidanceMap.put(650, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.43
            {
                add(90);
                add(180);
            }
        }, 0));
        mTwoAngleHighlightGuidanceMap.put(405, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.44
            {
                add(0);
                add(45);
            }
        }, 0));
        mTwoAngleHighlightGuidanceMap.put(406, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.45
            {
                add(0);
                add(45);
            }
        }, 45));
        mTwoAngleHighlightGuidanceMap.put(411, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.46
            {
                add(0);
                add(90);
            }
        }, 0));
        mTwoAngleHighlightGuidanceMap.put(412, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.47
            {
                add(0);
                add(90);
            }
        }, 90));
        mTwoAngleHighlightGuidanceMap.put(16, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.48
            {
                add(45);
                add(90);
            }
        }, 45));
        mTwoAngleHighlightGuidanceMap.put(17, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.49
            {
                add(45);
                add(90);
            }
        }, 90));
        mTwoAngleHighlightGuidanceMap.put(429, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.50
            {
                add(0);
                add(135);
            }
        }, 0));
        mTwoAngleHighlightGuidanceMap.put(430, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.51
            {
                add(0);
                add(135);
            }
        }, 135));
        mTwoAngleHighlightGuidanceMap.put(433, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.52
            {
                add(45);
                add(135);
            }
        }, 45));
        mTwoAngleHighlightGuidanceMap.put(434, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.53
            {
                add(45);
                add(135);
            }
        }, 135));
        mTwoAngleHighlightGuidanceMap.put(46, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.54
            {
                add(90);
                add(135);
            }
        }, 90));
        mTwoAngleHighlightGuidanceMap.put(47, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.55
            {
                add(90);
                add(135);
            }
        }, 135));
        mTwoAngleHighlightGuidanceMap.put(60, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.56
            {
                add(0);
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_180));
            }
        }, 0));
        mTwoAngleHighlightGuidanceMap.put(61, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.57
            {
                add(0);
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_180));
            }
        }, ANGLE_N_180));
        mTwoAngleHighlightGuidanceMap.put(64, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.58
            {
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_180));
                add(45);
            }
        }, 45));
        mTwoAngleHighlightGuidanceMap.put(49, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.59
            {
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_180));
                add(45);
            }
        }, ANGLE_N_180));
        mTwoAngleHighlightGuidanceMap.put(467, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.60
            {
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_180));
                add(90);
            }
        }, 90));
        mTwoAngleHighlightGuidanceMap.put(468, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.61
            {
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_180));
                add(90);
            }
        }, ANGLE_N_180));
        mTwoAngleHighlightGuidanceMap.put(473, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.62
            {
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_135));
                add(0);
            }
        }, 0));
        mTwoAngleHighlightGuidanceMap.put(474, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.63
            {
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_135));
                add(0);
            }
        }, ANGLE_N_135));
        mTwoAngleHighlightGuidanceMap.put(477, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.64
            {
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_180));
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_135));
            }
        }, ANGLE_N_180));
        mTwoAngleHighlightGuidanceMap.put(478, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.65
            {
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_180));
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_135));
            }
        }, ANGLE_N_135));
        mTwoAngleHighlightGuidanceMap.put(483, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.66
            {
                add(-90);
                add(0);
            }
        }, 0));
        mTwoAngleHighlightGuidanceMap.put(484, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.67
            {
                add(-90);
                add(0);
            }
        }, -90));
        mTwoAngleHighlightGuidanceMap.put(487, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.68
            {
                add(-90);
                add(45);
            }
        }, 45));
        mTwoAngleHighlightGuidanceMap.put(488, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.69
            {
                add(-90);
                add(45);
            }
        }, -90));
        mTwoAngleHighlightGuidanceMap.put(499, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.70
            {
                add(-90);
                add(90);
            }
        }, 90));
        mTwoAngleHighlightGuidanceMap.put(500, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.71
            {
                add(-90);
                add(90);
            }
        }, -90));
        mTwoAngleHighlightGuidanceMap.put(535, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.72
            {
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_180));
                add(-90);
            }
        }, ANGLE_N_180));
        mTwoAngleHighlightGuidanceMap.put(536, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.73
            {
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_180));
                add(-90);
            }
        }, -90));
        mTwoAngleHighlightGuidanceMap.put(557, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.74
            {
                add(-45);
                add(0);
            }
        }, 0));
        mTwoAngleHighlightGuidanceMap.put(558, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.75
            {
                add(-45);
                add(0);
            }
        }, -45));
        mTwoAngleHighlightGuidanceMap.put(561, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.76
            {
                add(-45);
                add(45);
            }
        }, 45));
        mTwoAngleHighlightGuidanceMap.put(562, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.77
            {
                add(-45);
                add(45);
            }
        }, -45));
        mTwoAngleHighlightGuidanceMap.put(573, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.78
            {
                add(-45);
                add(90);
            }
        }, 90));
        mTwoAngleHighlightGuidanceMap.put(574, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.79
            {
                add(-45);
                add(90);
            }
        }, -45));
        mTwoAngleHighlightGuidanceMap.put(593, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.80
            {
                add(-45);
                add(135);
            }
        }, 135));
        mTwoAngleHighlightGuidanceMap.put(594, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.81
            {
                add(-45);
                add(135);
            }
        }, -45));
        mTwoAngleHighlightGuidanceMap.put(597, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.82
            {
                add(-45);
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_180));
            }
        }, ANGLE_N_180));
        mTwoAngleHighlightGuidanceMap.put(598, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.83
            {
                add(-45);
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_180));
            }
        }, -45));
        mTwoAngleHighlightGuidanceMap.put(609, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.84
            {
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_135));
                add(-45);
            }
        }, ANGLE_N_135));
        mTwoAngleHighlightGuidanceMap.put(610, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.85
            {
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_135));
                add(-45);
            }
        }, -45));
        mTwoAngleHighlightGuidanceMap.put(613, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.86
            {
                add(-90);
                add(-45);
            }
        }, -90));
        mTwoAngleHighlightGuidanceMap.put(614, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.87
            {
                add(-90);
                add(-45);
            }
        }, -45));
        mTwoAngleHighlightGuidanceMap.put(643, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.88
            {
                add(0);
                add(180);
            }
        }, 0));
        mTwoAngleHighlightGuidanceMap.put(644, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.89
            {
                add(0);
                add(180);
            }
        }, 180));
        mTwoAngleHighlightGuidanceMap.put(647, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.90
            {
                add(45);
                add(180);
            }
        }, 45));
        mTwoAngleHighlightGuidanceMap.put(648, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.91
            {
                add(45);
                add(180);
            }
        }, 180));
        mTwoAngleHighlightGuidanceMap.put(651, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.92
            {
                add(90);
                add(180);
            }
        }, 90));
        mTwoAngleHighlightGuidanceMap.put(652, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.93
            {
                add(90);
                add(180);
            }
        }, 180));
        mThreeAngleGrayGuidanceMap.put(19, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.94
            {
                add(0);
                add(45);
                add(90);
            }
        }, 0));
        mThreeAngleGrayGuidanceMap.put(436, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.95
            {
                add(0);
                add(45);
                add(135);
            }
        }, 0));
        mThreeAngleGrayGuidanceMap.put(448, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.96
            {
                add(0);
                add(90);
                add(135);
            }
        }, 0));
        mThreeAngleGrayGuidanceMap.put(490, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.97
            {
                add(-90);
                add(0);
                add(45);
            }
        }, 0));
        mThreeAngleGrayGuidanceMap.put(502, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.98
            {
                add(-90);
                add(0);
                add(90);
            }
        }, 0));
        mThreeAngleGrayGuidanceMap.put(510, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.99
            {
                add(-90);
                add(45);
                add(90);
            }
        }, 45));
        mThreeAngleGrayGuidanceMap.put(538, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.100
            {
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_180));
                add(-90);
                add(0);
            }
        }, 0));
        mThreeAngleGrayGuidanceMap.put(546, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.101
            {
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_135));
                add(-90);
                add(0);
            }
        }, 0));
        mThreeAngleGrayGuidanceMap.put(564, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.102
            {
                add(-45);
                add(0);
                add(45);
            }
        }, 0));
        mThreeAngleGrayGuidanceMap.put(576, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.103
            {
                add(-45);
                add(0);
                add(90);
            }
        }, 0));
        mThreeAngleGrayGuidanceMap.put(584, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.104
            {
                add(-45);
                add(45);
                add(90);
            }
        }, 0));
        mThreeAngleGrayGuidanceMap.put(600, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.105
            {
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_180));
                add(-45);
                add(0);
            }
        }, 0));
        mThreeAngleGrayGuidanceMap.put(616, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.106
            {
                add(-90);
                add(-45);
                add(0);
            }
        }, 0));
        mThreeAngleGrayGuidanceMap.put(624, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.107
            {
                add(-90);
                add(-45);
                add(45);
            }
        }, 0));
        mThreeAngleGrayGuidanceMap.put(632, new GuidanceLineInfo(0, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.108
            {
                add(-90);
                add(-45);
                add(90);
            }
        }, 0));
        mThreeAngleHighlightGuidanceMap.put(20, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.109
            {
                add(0);
                add(45);
                add(90);
            }
        }, 0));
        mThreeAngleHighlightGuidanceMap.put(23, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.110
            {
                add(0);
                add(45);
                add(90);
            }
        }, 45));
        mThreeAngleHighlightGuidanceMap.put(424, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.111
            {
                add(0);
                add(45);
                add(90);
            }
        }, 90));
        mThreeAngleHighlightGuidanceMap.put(437, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.112
            {
                add(0);
                add(45);
                add(135);
            }
        }, 0));
        mThreeAngleHighlightGuidanceMap.put(440, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.113
            {
                add(0);
                add(45);
                add(135);
            }
        }, 45));
        mThreeAngleHighlightGuidanceMap.put(442, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.114
            {
                add(0);
                add(45);
                add(135);
            }
        }, 135));
        mThreeAngleHighlightGuidanceMap.put(449, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.115
            {
                add(0);
                add(90);
                add(135);
            }
        }, 0));
        mThreeAngleHighlightGuidanceMap.put(452, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.116
            {
                add(0);
                add(90);
                add(135);
            }
        }, 90));
        mThreeAngleHighlightGuidanceMap.put(454, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.117
            {
                add(0);
                add(90);
                add(135);
            }
        }, 135));
        mThreeAngleHighlightGuidanceMap.put(491, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.118
            {
                add(-90);
                add(0);
                add(45);
            }
        }, 0));
        mThreeAngleHighlightGuidanceMap.put(494, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.119
            {
                add(-90);
                add(0);
                add(45);
            }
        }, 45));
        mThreeAngleHighlightGuidanceMap.put(496, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.120
            {
                add(-90);
                add(0);
                add(45);
            }
        }, -90));
        mThreeAngleHighlightGuidanceMap.put(503, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.121
            {
                add(-90);
                add(0);
                add(90);
            }
        }, 0));
        mThreeAngleHighlightGuidanceMap.put(506, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.122
            {
                add(-90);
                add(0);
                add(90);
            }
        }, 90));
        mThreeAngleHighlightGuidanceMap.put(508, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.123
            {
                add(-90);
                add(0);
                add(90);
            }
        }, -90));
        mThreeAngleHighlightGuidanceMap.put(511, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.124
            {
                add(-90);
                add(45);
                add(90);
            }
        }, 45));
        mThreeAngleHighlightGuidanceMap.put(514, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.125
            {
                add(-90);
                add(45);
                add(90);
            }
        }, 90));
        mThreeAngleHighlightGuidanceMap.put(516, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.126
            {
                add(-90);
                add(45);
                add(90);
            }
        }, -90));
        mThreeAngleHighlightGuidanceMap.put(539, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.127
            {
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_180));
                add(-90);
                add(0);
            }
        }, 0));
        mThreeAngleHighlightGuidanceMap.put(542, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.128
            {
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_180));
                add(-90);
                add(0);
            }
        }, ANGLE_N_180));
        mThreeAngleHighlightGuidanceMap.put(544, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.129
            {
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_180));
                add(-90);
                add(0);
            }
        }, -90));
        mThreeAngleHighlightGuidanceMap.put(547, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.130
            {
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_135));
                add(-90);
                add(0);
            }
        }, 0));
        mThreeAngleHighlightGuidanceMap.put(550, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.131
            {
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_135));
                add(-90);
                add(0);
            }
        }, ANGLE_N_135));
        mThreeAngleHighlightGuidanceMap.put(552, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.132
            {
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_135));
                add(-90);
                add(0);
            }
        }, -90));
        mThreeAngleHighlightGuidanceMap.put(565, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.133
            {
                add(-45);
                add(0);
                add(45);
            }
        }, 0));
        mThreeAngleHighlightGuidanceMap.put(568, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.134
            {
                add(-45);
                add(0);
                add(45);
            }
        }, 45));
        mThreeAngleHighlightGuidanceMap.put(570, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.135
            {
                add(-45);
                add(0);
                add(45);
            }
        }, -45));
        mThreeAngleHighlightGuidanceMap.put(577, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.136
            {
                add(-45);
                add(0);
                add(90);
            }
        }, 0));
        mThreeAngleHighlightGuidanceMap.put(580, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.137
            {
                add(-45);
                add(0);
                add(90);
            }
        }, 90));
        mThreeAngleHighlightGuidanceMap.put(582, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.138
            {
                add(-45);
                add(0);
                add(90);
            }
        }, -45));
        mThreeAngleHighlightGuidanceMap.put(585, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.139
            {
                add(-45);
                add(45);
                add(90);
            }
        }, 45));
        mThreeAngleHighlightGuidanceMap.put(588, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.140
            {
                add(-45);
                add(45);
                add(90);
            }
        }, 90));
        mThreeAngleHighlightGuidanceMap.put(590, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.141
            {
                add(-45);
                add(45);
                add(90);
            }
        }, -45));
        mThreeAngleHighlightGuidanceMap.put(601, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.142
            {
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_180));
                add(-45);
                add(0);
            }
        }, 0));
        mThreeAngleHighlightGuidanceMap.put(604, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.143
            {
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_180));
                add(-45);
                add(0);
            }
        }, ANGLE_N_180));
        mThreeAngleHighlightGuidanceMap.put(606, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.144
            {
                add(Integer.valueOf(GuidanceLineUtils.ANGLE_N_180));
                add(-45);
                add(0);
            }
        }, -45));
        mThreeAngleHighlightGuidanceMap.put(617, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.145
            {
                add(-90);
                add(-45);
                add(0);
            }
        }, 0));
        mThreeAngleHighlightGuidanceMap.put(620, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.146
            {
                add(-90);
                add(-45);
                add(0);
            }
        }, -90));
        mThreeAngleHighlightGuidanceMap.put(622, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.147
            {
                add(-90);
                add(-45);
                add(0);
            }
        }, -45));
        mThreeAngleHighlightGuidanceMap.put(625, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.148
            {
                add(-90);
                add(-45);
                add(45);
            }
        }, 45));
        mThreeAngleHighlightGuidanceMap.put(628, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.149
            {
                add(-90);
                add(-45);
                add(45);
            }
        }, -90));
        mThreeAngleHighlightGuidanceMap.put(630, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.150
            {
                add(-90);
                add(-45);
                add(45);
            }
        }, -45));
        mThreeAngleHighlightGuidanceMap.put(633, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.151
            {
                add(-90);
                add(-45);
                add(90);
            }
        }, 90));
        mThreeAngleHighlightGuidanceMap.put(636, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.152
            {
                add(-90);
                add(-45);
                add(90);
            }
        }, -90));
        mThreeAngleHighlightGuidanceMap.put(638, new GuidanceLineInfo(1, new ArrayList<Integer>() { // from class: com.ts.androidauto.app.proxy.navigation.GuidanceLineUtils.153
            {
                add(-90);
                add(-45);
                add(90);
            }
        }, -45));
    }

    public static void removeGuidanceLineIconMap() {
        Map<Integer, GuidanceLineInfo> map = mOneAngleGrayGuidanceMap;
        if (map != null) {
            map.clear();
        }
        Map<Integer, GuidanceLineInfo> map2 = mOneAngleHighlightGuidanceMap;
        if (map2 != null) {
            map2.clear();
        }
        Map<Integer, GuidanceLineInfo> map3 = mTwoAngleGrayGuidanceMap;
        if (map3 != null) {
            map3.clear();
        }
        Map<Integer, GuidanceLineInfo> map4 = mTwoAngleHighlightGuidanceMap;
        if (map4 != null) {
            map4.clear();
        }
        Map<Integer, GuidanceLineInfo> map5 = mThreeAngleGrayGuidanceMap;
        if (map5 != null) {
            map5.clear();
        }
        Map<Integer, GuidanceLineInfo> map6 = mThreeAngleHighlightGuidanceMap;
        if (map6 != null) {
            map6.clear();
        }
    }

    public static List<GuidanceLineMapBean> getGuidanceLineMapIds(NavigationStepData stepData) {
        Log.d(TAG, " start getGuidanceLineMapIds.");
        List<GuidanceLineMapBean> guidanceLineMapIds = new ArrayList<>();
        List<NavigationLaneData> laneDataList = stepData.getLaneData();
        if (laneDataList == null || laneDataList.size() <= 0) {
            Log.e(TAG, "getGuidanceLineMapIds: laneDataList is null or size is 0.");
            return guidanceLineMapIds;
        }
        Log.d(TAG, "stepData.getLaneData() --> List<NavigationLaneData>: " + laneDataList.toString());
        ArrayList<GuidanceLineInfoWithIndex> guidanceLineInfoWithIndexList = new ArrayList<>();
        for (int laneIndex = 0; laneIndex < laneDataList.size(); laneIndex++) {
            GuidanceLineInfoWithIndex guidanceLineInfoWithIndex = new GuidanceLineInfoWithIndex();
            guidanceLineInfoWithIndex.setmIndex(laneIndex);
            NavigationLaneData laneData = laneDataList.get(laneIndex);
            if (laneData == null) {
                Log.e(TAG, "getGuidanceLineMapIds: laneData is null.");
            } else {
                List<NavigationDirectionData> directionDataList = laneData.getNavigationDirectionData();
                if (directionDataList == null) {
                    Log.e(TAG, "getGuidanceLineMapIds: directionDataList is null.");
                } else {
                    ArrayList<Integer> angles = new ArrayList<>();
                    for (NavigationDirectionData directionData : directionDataList) {
                        int shape = directionData.getShape();
                        int angle = convertShapeToAngle(shape);
                        if (angle != -181) {
                            angles.add(Integer.valueOf(angle));
                        }
                        int highlighted = directionData.getHighlighted();
                        if (highlighted > 0) {
                            guidanceLineInfoWithIndex.setmHighlightAngle(angle);
                            guidanceLineInfoWithIndex.setmStatus(1);
                        }
                    }
                    guidanceLineInfoWithIndex.setmAngles(angles);
                }
            }
            guidanceLineInfoWithIndexList.add(guidanceLineInfoWithIndex);
        }
        int laneIndex2 = guidanceLineInfoWithIndexList.size();
        if (laneIndex2 > 0) {
            Log.d(TAG, "guidanceLineInfoWithIndexList: " + guidanceLineInfoWithIndexList.toString());
            return getGuidanceLineIdsForAa(guidanceLineInfoWithIndexList);
        }
        return guidanceLineMapIds;
    }

    public static List<GuidanceLineMapBean> getGuidanceLineIdsForAa(List<GuidanceLineInfoWithIndex> laneInformationList) {
        List<GuidanceLineMapBean> mapBeans = new ArrayList<>();
        GuidanceLineInfo guidanceLineInfo = new GuidanceLineInfo();
        if (laneInformationList != null && laneInformationList.size() > 0) {
            for (GuidanceLineInfoWithIndex guidanceLineInfoWithIndex : laneInformationList) {
                guidanceLineInfo.setmStatus(guidanceLineInfoWithIndex.getmStatus());
                guidanceLineInfo.setmAngles(guidanceLineInfoWithIndex.getmAngles());
                guidanceLineInfo.setmHighlightAngle(guidanceLineInfoWithIndex.getmHighlightAngle());
                int guidanceLineMapId = getGuidanceLineId(guidanceLineInfo);
                mapBeans.add(new GuidanceLineMapBean(guidanceLineInfoWithIndex.getmIndex(), guidanceLineMapId));
            }
        }
        return mapBeans;
    }

    public static List<GuidanceLineMapBean> getGuidanceLineIdsForCp(List<LaneInformation> laneInformationList) {
        List<GuidanceLineMapBean> mapBeans = new ArrayList<>();
        GuidanceLineInfo guidanceLineInfo = new GuidanceLineInfo();
        if (laneInformationList != null && laneInformationList.size() > 0) {
            for (LaneInformation laneInformation : laneInformationList) {
                guidanceLineInfo.setmStatus(laneInformation.getLaneStatus());
                guidanceLineInfo.setmAngles(laneInformation.getLaneAngles());
                guidanceLineInfo.setmHighlightAngle(laneInformation.getLaneAngleHighlight());
                int guidanceLineMapId = getGuidanceLineId(guidanceLineInfo);
                mapBeans.add(new GuidanceLineMapBean(laneInformation.getIndex(), guidanceLineMapId));
            }
        }
        return mapBeans;
    }

    private static int convertShapeToAngle(int shape) {
        int resultAngle = 0;
        switch (shape) {
            case 0:
                resultAngle = ANGLE_N_181;
                break;
            case 1:
                resultAngle = 0;
                break;
            case 2:
                resultAngle = -45;
                break;
            case 3:
                resultAngle = 45;
                break;
            case 4:
                resultAngle = -90;
                break;
            case 5:
                resultAngle = 90;
                break;
            case 6:
                resultAngle = ANGLE_N_135;
                break;
            case 7:
                resultAngle = 135;
                break;
            case 8:
                resultAngle = ANGLE_N_180;
                break;
            case 9:
                resultAngle = 180;
                break;
        }
        Log.d(TAG, "convertShapeToAngle: shape = " + shape + ", resultAngle = " + resultAngle);
        return resultAngle;
    }

    private static int getGuidanceLineId(GuidanceLineInfo guidanceLineInfo) {
        GuidanceLineInfo arrangedGuidanceLineInfo = arrangeAngles(guidanceLineInfo);
        if (arrangedGuidanceLineInfo == null) {
            return -1;
        }
        int status = arrangedGuidanceLineInfo.getmStatus();
        List<Integer> angles = arrangedGuidanceLineInfo.getmAngles();
        if (angles.size() == 1) {
            if (status == 0) {
                int guidanceLineId = findGuidanceLineIdInOneAngleGrayMap(arrangedGuidanceLineInfo);
                return guidanceLineId;
            }
            if (status <= 0) {
                return -1;
            }
            int guidanceLineId2 = findGuidanceLineIdInOneAngleHighlightMap(arrangedGuidanceLineInfo);
            return guidanceLineId2;
        }
        if (angles.size() <= 1) {
            return -1;
        }
        GuidanceLineInfo sortedGuidanceLineInfo = sortAnglesByAsc(arrangedGuidanceLineInfo);
        if (status == 0) {
            int size = angles.size();
            if (size == 2) {
                int guidanceLineId3 = findGuidanceLineIdInTwoAngleGrayMap(sortedGuidanceLineInfo);
                return guidanceLineId3;
            }
            if (size == 3) {
                int guidanceLineId4 = findGuidanceLineIdInThreeAngleGrayMap(sortedGuidanceLineInfo);
                return guidanceLineId4;
            }
            Log.i(TAG, "getGuidanceLineId: Guidance line angles is more than three.");
            return -1;
        }
        if (status <= 0) {
            return -1;
        }
        int size2 = angles.size();
        if (size2 == 2) {
            int guidanceLineId5 = findGuidanceLineIdInTwoAngleHighlightMap(sortedGuidanceLineInfo);
            return guidanceLineId5;
        }
        if (size2 == 3) {
            int guidanceLineId6 = findGuidanceLineIdInThreeAngleHighlightMap(sortedGuidanceLineInfo);
            return guidanceLineId6;
        }
        Log.i(TAG, "getGuidanceLineId: Guidance line angles is more than three.");
        return -1;
    }

    private static GuidanceLineInfo arrangeAngles(GuidanceLineInfo guidanceLineInfo) {
        int status = guidanceLineInfo.getmStatus();
        if (status == 0) {
            GuidanceLineInfo arrangedLineInfo = arrangeGrayAngles(guidanceLineInfo);
            return arrangedLineInfo;
        }
        if (status <= 0) {
            return null;
        }
        GuidanceLineInfo arrangedLineInfo2 = arrangeHighlightAngles(guidanceLineInfo);
        return arrangedLineInfo2;
    }

    private static GuidanceLineInfo arrangeGrayAngles(GuidanceLineInfo guidanceLineInfo) {
        List<Integer> angles = guidanceLineInfo.getmAngles();
        if (angles == null || angles.size() <= 0) {
            ArrayList<Integer> addAngles = new ArrayList<>();
            addAngles.add(Integer.valueOf(guidanceLineInfo.getmHighlightAngle()));
            guidanceLineInfo.setmAngles(addAngles);
        }
        return guidanceLineInfo;
    }

    private static GuidanceLineInfo arrangeHighlightAngles(GuidanceLineInfo guidanceLineInfo) {
        List<Integer> angles = guidanceLineInfo.getmAngles();
        if (angles == null || angles.size() <= 0) {
            ArrayList<Integer> addAngles = new ArrayList<>();
            addAngles.add(Integer.valueOf(guidanceLineInfo.getmHighlightAngle()));
            guidanceLineInfo.setmAngles(addAngles);
        } else if (!angles.contains(Integer.valueOf(guidanceLineInfo.getmHighlightAngle()))) {
            angles.add(Integer.valueOf(guidanceLineInfo.getmHighlightAngle()));
        }
        return guidanceLineInfo;
    }

    private static GuidanceLineInfo sortAnglesByAsc(GuidanceLineInfo guidanceLineInfo) {
        List<Integer> angles = guidanceLineInfo.getmAngles();
        Collections.sort(angles);
        guidanceLineInfo.setmAngles(angles);
        return guidanceLineInfo;
    }

    private static int findFirstAngle(int angle) {
        if (angle <= -157.5f) {
            return ANGLE_N_180;
        }
        if (angle >= 157.5f) {
            return 180;
        }
        if (angle < -112.5f && angle > -157.5f) {
            return ANGLE_N_135;
        }
        if (angle <= -67.5f && angle >= -112.5f) {
            return -90;
        }
        if (angle < -22.5f && angle > -67.5f) {
            return -45;
        }
        if (angle <= BASE_ANGLE && angle >= -22.5f) {
            return 0;
        }
        if (angle < 67.5f && angle > BASE_ANGLE) {
            return 45;
        }
        if (angle <= 112.5f && angle >= 67.5f) {
            return 90;
        }
        if (angle >= 157.5f || angle <= 112.5f) {
            return 0;
        }
        return 135;
    }

    private static int findMoreSimilarAngleInTwoAngles(int angle1, int angle2) {
        int similarityAngle1 = Math.abs(angle1) % 45;
        int similarityAngle2 = Math.abs(angle1) % 45;
        int min = Math.min(similarityAngle1, similarityAngle2);
        if (min == similarityAngle1) {
            return angle1;
        }
        return angle2;
    }

    private static int findMoreSimilarAngleInThreeAngles(int angle1, int angle2, int angle3) {
        int similarityAngle1 = Math.abs(angle1) % 45;
        int similarityAngle2 = Math.abs(angle2) % 45;
        int similarityAngle3 = Math.abs(angle3) % 45;
        int min12 = Math.min(similarityAngle1, similarityAngle2);
        if (min12 == similarityAngle1) {
            int min13 = Math.min(similarityAngle1, similarityAngle3);
            if (min13 == similarityAngle1) {
                return angle1;
            }
            return angle3;
        }
        int min23 = Math.min(similarityAngle2, similarityAngle3);
        if (min23 == similarityAngle2) {
            return similarityAngle2;
        }
        return similarityAngle3;
    }

    private static int findSecondLargeAngle(int firstAngle, int angle2) {
        int secondAngle = findFirstAngle(angle2);
        if (secondAngle == firstAngle) {
            int secondAngle2 = firstAngle + 45;
            if (secondAngle2 > 180) {
                return secondAngle2 - 360;
            }
            return secondAngle2;
        }
        return secondAngle;
    }

    private static int findSecondSmallAngle(int firstAngle, int angle2) {
        int secondAngle = findFirstAngle(angle2);
        if (secondAngle == firstAngle) {
            int secondAngle2 = firstAngle - 45;
            if (secondAngle2 < -180) {
                return secondAngle2 + 360;
            }
            return secondAngle2;
        }
        return secondAngle;
    }

    private static int findGuidanceLineIdInOneAngleGrayMap(GuidanceLineInfo guidanceLineInfo) {
        int grayOneAngleMapId = -1;
        if (mOneAngleGrayGuidanceMap != null) {
            Integer angle = guidanceLineInfo.getmAngles().get(0);
            int firstAngle = findFirstAngle(angle.intValue());
            Set<Map.Entry<Integer, GuidanceLineInfo>> entrySet = mOneAngleGrayGuidanceMap.entrySet();
            for (Map.Entry<Integer, GuidanceLineInfo> entry : entrySet) {
                if (entry.getValue().getmAngles().get(0).intValue() == firstAngle) {
                    grayOneAngleMapId = entry.getKey().intValue();
                }
            }
        } else {
            Log.e(TAG, "findGuidanceLineIdInOneAngleGrayMap: mOneAngleGrayGuidanceMap is null.");
        }
        return grayOneAngleMapId;
    }

    private static int findGuidanceLineIdInOneAngleHighlightMap(GuidanceLineInfo guidanceLineInfo) {
        int highlinghtOneAngleMapId = -1;
        if (mOneAngleHighlightGuidanceMap != null) {
            Integer angle = guidanceLineInfo.getmAngles().get(0);
            int firstAngle = findFirstAngle(angle.intValue());
            Set<Map.Entry<Integer, GuidanceLineInfo>> entrySet = mOneAngleHighlightGuidanceMap.entrySet();
            for (Map.Entry<Integer, GuidanceLineInfo> entry : entrySet) {
                if (entry.getValue().getmAngles().get(0).intValue() == firstAngle) {
                    highlinghtOneAngleMapId = entry.getKey().intValue();
                }
            }
        } else {
            Log.e(TAG, "findGuidanceLineIdInOneAngleHighlightMap: mOneAngleHighlightGuidanceMap is null.");
        }
        return highlinghtOneAngleMapId;
    }

    private static int findGuidanceLineIdInTwoAngleGrayMap(GuidanceLineInfo guidanceLineInfo) {
        int firstAngle;
        int secondAngle;
        int grayTwoAngleMapId = -1;
        if (mTwoAngleGrayGuidanceMap != null) {
            Integer angle1 = guidanceLineInfo.getmAngles().get(0);
            Integer angle2 = guidanceLineInfo.getmAngles().get(1);
            int moreSimilarAngle = findMoreSimilarAngleInTwoAngles(angle1.intValue(), angle2.intValue());
            if (moreSimilarAngle == angle1.intValue()) {
                firstAngle = findFirstAngle(angle1.intValue());
                secondAngle = findSecondLargeAngle(firstAngle, angle2.intValue());
            } else {
                firstAngle = findFirstAngle(angle2.intValue());
                secondAngle = findSecondSmallAngle(firstAngle, angle1.intValue());
            }
            Set<Map.Entry<Integer, GuidanceLineInfo>> entrySet = mTwoAngleGrayGuidanceMap.entrySet();
            for (Map.Entry<Integer, GuidanceLineInfo> entry : entrySet) {
                List<Integer> angleList = entry.getValue().getmAngles();
                if (angleList.contains(Integer.valueOf(firstAngle)) && angleList.contains(Integer.valueOf(secondAngle))) {
                    grayTwoAngleMapId = entry.getKey().intValue();
                }
            }
        } else {
            Log.e(TAG, "findGuidanceLineIdInTwoAngleGrayMap: mTwoAngleGrayGuidanceMap is null.");
        }
        return grayTwoAngleMapId;
    }

    private static int findGuidanceLineIdInTwoAngleHighlightMap(GuidanceLineInfo guidanceLineInfo) {
        int highlightTwoAngleMapId = -1;
        if (mTwoAngleHighlightGuidanceMap != null) {
            int highlightAngle = guidanceLineInfo.getmHighlightAngle();
            Integer angle1 = guidanceLineInfo.getmAngles().get(0);
            Integer angle2 = guidanceLineInfo.getmAngles().get(1);
            if (highlightAngle == angle1.intValue()) {
                int firstAngle = findFirstAngle(angle1.intValue());
                int secondAngle = findSecondLargeAngle(firstAngle, angle2.intValue());
                Set<Map.Entry<Integer, GuidanceLineInfo>> entrySet = mTwoAngleHighlightGuidanceMap.entrySet();
                for (Map.Entry<Integer, GuidanceLineInfo> entry : entrySet) {
                    List<Integer> angleList = entry.getValue().getmAngles();
                    if (angleList.contains(Integer.valueOf(firstAngle)) && angleList.contains(Integer.valueOf(secondAngle)) && entry.getValue().getmHighlightAngle() == firstAngle) {
                        highlightTwoAngleMapId = entry.getKey().intValue();
                    }
                }
            } else if (highlightAngle == angle2.intValue()) {
                int firstAngle2 = findFirstAngle(angle2.intValue());
                int secondAngle2 = findSecondSmallAngle(firstAngle2, angle1.intValue());
                Set<Map.Entry<Integer, GuidanceLineInfo>> entrySet2 = mTwoAngleHighlightGuidanceMap.entrySet();
                for (Map.Entry<Integer, GuidanceLineInfo> entry2 : entrySet2) {
                    List<Integer> angleList2 = entry2.getValue().getmAngles();
                    if (angleList2.contains(Integer.valueOf(firstAngle2)) && angleList2.contains(Integer.valueOf(secondAngle2)) && entry2.getValue().getmHighlightAngle() == firstAngle2) {
                        highlightTwoAngleMapId = entry2.getKey().intValue();
                    }
                }
            }
        } else {
            Log.e(TAG, "findGuidanceLineIdInTwoAngleHighlightMap: mTwoAngleHighlightGuidanceMap is null.");
        }
        return highlightTwoAngleMapId;
    }

    private static int findGuidanceLineIdInThreeAngleGrayMap(GuidanceLineInfo guidanceLineInfo) {
        int grayThreeAngleMapId = -1;
        if (mThreeAngleGrayGuidanceMap != null) {
            Integer angle1 = guidanceLineInfo.getmAngles().get(0);
            Integer angle2 = guidanceLineInfo.getmAngles().get(1);
            Integer angle3 = guidanceLineInfo.getmAngles().get(2);
            int firstAngle = findFirstAngle(angle1.intValue());
            int secondAngle = findFirstAngle(angle2.intValue());
            int thirdAngle = findFirstAngle(angle3.intValue());
            if (secondAngle == firstAngle || secondAngle == thirdAngle) {
                if (secondAngle == firstAngle && secondAngle == thirdAngle) {
                    int moreSimilarAngleInThreeAngles = findMoreSimilarAngleInThreeAngles(angle1.intValue(), angle2.intValue(), angle3.intValue());
                    if (moreSimilarAngleInThreeAngles == angle1.intValue()) {
                        secondAngle = firstAngle + 45;
                        if (secondAngle > 180) {
                            secondAngle -= 360;
                        }
                        thirdAngle = secondAngle + 45;
                        if (thirdAngle > 180) {
                            thirdAngle -= 360;
                        }
                    } else if (moreSimilarAngleInThreeAngles == angle2.intValue()) {
                        firstAngle = secondAngle - 45;
                        if (firstAngle < -180) {
                            firstAngle += 360;
                        }
                        thirdAngle = secondAngle + 45;
                        if (thirdAngle > 180) {
                            thirdAngle -= 360;
                        }
                    } else {
                        secondAngle = thirdAngle - 45;
                        if (secondAngle < -180) {
                            secondAngle += 360;
                        }
                        firstAngle = secondAngle - 45;
                        if (firstAngle < -180) {
                            firstAngle += 360;
                        }
                    }
                } else if (firstAngle == secondAngle) {
                    int moreSimilarAngleInTwoAngles = findMoreSimilarAngleInTwoAngles(angle1.intValue(), angle2.intValue());
                    if (moreSimilarAngleInTwoAngles == angle1.intValue()) {
                        secondAngle = firstAngle + 45;
                        if (secondAngle > 180) {
                            secondAngle -= 360;
                        }
                        if (secondAngle == thirdAngle) {
                            thirdAngle = secondAngle + 45;
                        }
                        if (thirdAngle > 180) {
                            thirdAngle -= 360;
                        }
                    } else {
                        firstAngle = secondAngle - 45;
                        if (firstAngle < -180) {
                            firstAngle += 360;
                        }
                        if (firstAngle == thirdAngle) {
                            thirdAngle = firstAngle - 45;
                        }
                        if (thirdAngle < -180) {
                            thirdAngle += 360;
                        }
                    }
                } else if (secondAngle == thirdAngle) {
                    int moreSimilarAngleInTwoAngles2 = findMoreSimilarAngleInTwoAngles(angle2.intValue(), angle3.intValue());
                    if (moreSimilarAngleInTwoAngles2 == angle2.intValue()) {
                        thirdAngle = secondAngle + 45;
                        if (thirdAngle > 180) {
                            thirdAngle -= 360;
                        }
                        if (thirdAngle == firstAngle) {
                            firstAngle = thirdAngle + 45;
                        }
                        if (firstAngle > 180) {
                            firstAngle -= 360;
                        }
                    } else {
                        secondAngle = thirdAngle - 45;
                        if (secondAngle < -180) {
                            secondAngle += 360;
                        }
                        if (secondAngle == firstAngle) {
                            firstAngle = secondAngle - 45;
                        }
                        if (firstAngle < -180) {
                            firstAngle += 360;
                        }
                    }
                }
            }
            Set<Map.Entry<Integer, GuidanceLineInfo>> entrySet = mThreeAngleGrayGuidanceMap.entrySet();
            for (Map.Entry<Integer, GuidanceLineInfo> entry : entrySet) {
                List<Integer> angles = entry.getValue().getmAngles();
                if (angles.contains(Integer.valueOf(firstAngle)) && angles.contains(Integer.valueOf(secondAngle)) && angles.contains(Integer.valueOf(thirdAngle))) {
                    grayThreeAngleMapId = entry.getKey().intValue();
                }
            }
        } else {
            Log.e(TAG, "findGuidanceLineIdInThreeAngleGrayMap: mThreeAngleGrayGuidanceMap is null.");
        }
        return grayThreeAngleMapId;
    }

    private static int findGuidanceLineIdInThreeAngleHighlightMap(GuidanceLineInfo guidanceLineInfo) {
        int highlightThreeAngleMapId = -1;
        if (mThreeAngleHighlightGuidanceMap != null) {
            int highlightAngle = guidanceLineInfo.getmHighlightAngle();
            Integer angle1 = guidanceLineInfo.getmAngles().get(0);
            Integer angle2 = guidanceLineInfo.getmAngles().get(1);
            Integer angle3 = guidanceLineInfo.getmAngles().get(2);
            int firstAngle = findFirstAngle(angle1.intValue());
            int secondAngle = findFirstAngle(angle2.intValue());
            int thirdAngle = findFirstAngle(angle3.intValue());
            int mhighLightAngle = 0;
            if (highlightAngle == angle1.intValue()) {
                mhighLightAngle = firstAngle;
                if (secondAngle == firstAngle || secondAngle == thirdAngle) {
                    if (secondAngle == firstAngle && secondAngle == thirdAngle) {
                        secondAngle = firstAngle + 45;
                        if (secondAngle > 180) {
                            secondAngle -= 360;
                        }
                        thirdAngle = secondAngle + 45;
                        if (thirdAngle > 180) {
                            thirdAngle -= 360;
                        }
                    } else if (secondAngle == firstAngle) {
                        secondAngle = firstAngle + 45;
                        if (secondAngle > 180) {
                            secondAngle -= 360;
                        }
                        if (secondAngle == thirdAngle && (thirdAngle = secondAngle + 45) > 180) {
                            thirdAngle -= 360;
                        }
                    } else if (secondAngle != firstAngle && secondAngle == thirdAngle && (thirdAngle = secondAngle + 45) > 180) {
                        thirdAngle += ANGLE_N_180;
                    }
                }
            } else if (highlightAngle == angle2.intValue()) {
                mhighLightAngle = secondAngle;
                if (secondAngle == thirdAngle || secondAngle == firstAngle) {
                    if (secondAngle == thirdAngle && secondAngle == firstAngle) {
                        firstAngle = secondAngle - 45;
                        if (firstAngle < -180) {
                            firstAngle += 360;
                        }
                        thirdAngle = secondAngle + 45;
                        if (thirdAngle > 180) {
                            thirdAngle -= 360;
                        }
                    } else if (secondAngle == firstAngle) {
                        firstAngle = secondAngle - 45;
                        if (firstAngle < -180 && thirdAngle == (firstAngle = firstAngle + 360) && secondAngle - 45 < -180) {
                            thirdAngle += 360;
                        }
                    } else if (secondAngle == thirdAngle) {
                        thirdAngle = secondAngle + 45;
                        if (thirdAngle > 180 && thirdAngle - 360 == firstAngle && (firstAngle = thirdAngle + 45) > 180) {
                            firstAngle -= 360;
                        }
                    } else if (secondAngle != firstAngle && firstAngle == thirdAngle) {
                        if (firstAngle + 45 == secondAngle) {
                            thirdAngle -= 45;
                            if (thirdAngle < -180) {
                                thirdAngle += 360;
                            }
                        } else if (firstAngle + 45 != secondAngle) {
                            firstAngle += 45;
                            if (firstAngle > 180) {
                                firstAngle -= 360;
                            }
                        } else if (thirdAngle - 45 == secondAngle) {
                            firstAngle += 45;
                            if (firstAngle > 180) {
                                firstAngle -= 360;
                            }
                        } else if (thirdAngle - 45 != secondAngle && thirdAngle - 45 < -180) {
                            thirdAngle += 360;
                        }
                    }
                }
            } else if (highlightAngle == angle3.intValue()) {
                mhighLightAngle = thirdAngle;
                if (secondAngle == thirdAngle || secondAngle == firstAngle) {
                    if (secondAngle == thirdAngle && secondAngle == firstAngle) {
                        secondAngle = thirdAngle - 45;
                        if (secondAngle < -180) {
                            secondAngle += 360;
                        }
                        firstAngle = secondAngle - 45;
                        if (firstAngle < -180) {
                            firstAngle += 360;
                        }
                    } else if (secondAngle == thirdAngle) {
                        secondAngle = thirdAngle - 45;
                        if (secondAngle < -180) {
                            secondAngle += 360;
                        }
                        if (secondAngle == firstAngle && secondAngle - 45 < -180) {
                            firstAngle += 360;
                        }
                    } else if (secondAngle != thirdAngle && secondAngle == firstAngle && secondAngle - 45 < -180) {
                        firstAngle += 360;
                    }
                }
            }
            Set<Map.Entry<Integer, GuidanceLineInfo>> entrySet = mThreeAngleHighlightGuidanceMap.entrySet();
            for (Map.Entry<Integer, GuidanceLineInfo> entry : entrySet) {
                List<Integer> angles = entry.getValue().getmAngles();
                int highlightAngle1 = entry.getValue().getmHighlightAngle();
                if (angles.contains(Integer.valueOf(firstAngle)) && angles.contains(Integer.valueOf(secondAngle)) && angles.contains(Integer.valueOf(thirdAngle)) && highlightAngle1 == mhighLightAngle) {
                    highlightThreeAngleMapId = entry.getKey().intValue();
                }
            }
        } else {
            Log.e(TAG, "findGuidanceLineIdInThreeAngleHighlightMap: mThreeAngleHighlightGuidanceMap is null.");
        }
        return highlightThreeAngleMapId;
    }

    public static int convertManeuverIconId(int maneuverType, int roundaboutExitAngle) {
        switch (maneuverType) {
            case 32:
            case 33:
                if (roundaboutExitAngle < 1 || roundaboutExitAngle > 67) {
                    if (roundaboutExitAngle <= 67 || roundaboutExitAngle >= 112) {
                        if (roundaboutExitAngle <= 112 || roundaboutExitAngle > 157) {
                            if (roundaboutExitAngle <= 157 || roundaboutExitAngle > 202) {
                                if (roundaboutExitAngle <= 202 || roundaboutExitAngle > 247) {
                                    if (roundaboutExitAngle <= 247 || roundaboutExitAngle > 292) {
                                        if (roundaboutExitAngle <= 292 || roundaboutExitAngle > 337) {
                                            if (roundaboutExitAngle <= 337 || roundaboutExitAngle > 360) {
                                                if (roundaboutExitAngle == 0) {
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            case 34:
            case 35:
                if (roundaboutExitAngle < 1 || roundaboutExitAngle > 67) {
                    if (roundaboutExitAngle <= 67 || roundaboutExitAngle >= 112) {
                        if (roundaboutExitAngle <= 112 || roundaboutExitAngle > 157) {
                            if (roundaboutExitAngle <= 157 || roundaboutExitAngle > 202) {
                                if (roundaboutExitAngle <= 202 || roundaboutExitAngle > 247) {
                                    if (roundaboutExitAngle <= 247 || roundaboutExitAngle > 292) {
                                        if (roundaboutExitAngle <= 292 || roundaboutExitAngle > 337) {
                                            if (roundaboutExitAngle <= 337 || roundaboutExitAngle > 360) {
                                                if (roundaboutExitAngle == 0) {
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                break;
        }
        return 0;
    }
}
