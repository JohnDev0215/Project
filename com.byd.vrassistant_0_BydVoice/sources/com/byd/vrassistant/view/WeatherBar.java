package com.byd.vrassistant.view;

import android.content.Context;
import android.graphics.Paint;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.Group;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import cerence.ark.assistant.controller.domain.hmi.param.WeatherInformationParam;
import cerence.ark.assistant.controller.enumeration.WeatherFocusTypeEnum;
import cerence.ark.assistant.controller.struct.CurrentWeatherInfo;
import cerence.ark.assistant.controller.struct.DailyWeatherInfo;
import cerence.ark.assistant.controller.struct.HourlyWeatherInfo;
import com.byd.vrassistant.base.ConstraintLayoutLifecycle;
import com.byd.vrassistant.conversation.DateHelper;
import com.byd.vrassistant.ui.R;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.TextViewUtils;
import com.byd.vrassistant.viewInf.weather.IWeatherBarView;
import com.byd.vrassistant.viewmodel.CommonViewModel;
import com.byd.vrassistant.viewmodel.ViewModelHelper;
import com.byd.vrassistant.viewmodel.WeatherViewModel;
import java.util.Locale;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class WeatherBar extends ConstraintLayoutLifecycle implements IWeatherBarView {
    private static final String TAG = "NEU_WeatherBar";
    CommonViewModel commonViewModel;
    DateHelper dateHelper;
    private Group fourGroup;
    private TextView hours_Humi_text;
    private TextView hours_Humi_value;
    private TextView hours_UV_text;
    private TextView hours_UV_value;
    private TextView hours_wind_text;
    private TextView hours_wind_value;
    private TextView location_name;
    private Context mContext;
    private TextView temp;
    private TextView tempUnit;
    private Group threeGroup;
    String tmpType;
    private Group twoGroup;
    private View view_line;
    private View view_line_vertical;
    WeatherRes weatherRes;
    WeatherViewModel weatherViewModel;
    private View weather_One_bg;
    private View weather_bg_small;
    private TextView weather_date;
    private ImageView weather_image;
    private TextView weather_name;
    private TextView weekFour;
    private TextView weekFourDate;
    private ImageView weekFourImage;
    private TextView weekFourName;
    private TextView weekFourWeather;
    private TextView weekOne;
    private TextView weekOneDate;
    private ImageView weekOneImage;
    private TextView weekOneName;
    private TextView weekOneWeather;
    private TextView weekThree;
    private TextView weekThreeDate;
    private ImageView weekThreeImage;
    private TextView weekThreeName;
    private TextView weekThreeWeather;
    private TextView weekTwo;
    private TextView weekTwoDate;
    private ImageView weekTwoImage;
    private TextView weekTwoName;
    private TextView weekTwoWeather;

    public WeatherBar(Context context) {
        super(context);
        this.weatherRes = new WeatherRes();
        this.dateHelper = new DateHelper();
        this.mContext = context;
    }

    @Override // com.byd.vrassistant.base.ConstraintLayoutLifecycle
    public void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_weather_info, (ViewGroup) this, true);
        this.weather_bg_small = findViewById(R.id.weather_bg_small);
        this.weather_image = (ImageView) findViewById(R.id.weather_image);
        this.weekOneImage = (ImageView) findViewById(R.id.weekOneImage);
        this.weekTwoImage = (ImageView) findViewById(R.id.weekTwoImage);
        this.weekThreeImage = (ImageView) findViewById(R.id.weekThreeImage);
        this.weekFourImage = (ImageView) findViewById(R.id.weekFourImage);
        this.temp = (TextView) findViewById(R.id.tv_temp);
        this.tempUnit = (TextView) findViewById(R.id.tv_temp_unit);
        this.weather_name = (TextView) findViewById(R.id.weather_name);
        this.weekOne = (TextView) findViewById(R.id.weekOne);
        this.weekOneName = (TextView) findViewById(R.id.weekOneName);
        this.weekOneDate = (TextView) findViewById(R.id.weekOneDate);
        this.weekOneWeather = (TextView) findViewById(R.id.weekOneWeather);
        this.twoGroup = (Group) findViewById(R.id.two_group);
        this.weekTwo = (TextView) findViewById(R.id.weekTwo);
        this.weekTwoName = (TextView) findViewById(R.id.weekTwoName);
        this.weekTwoDate = (TextView) findViewById(R.id.weekTwoDate);
        this.weekTwoWeather = (TextView) findViewById(R.id.weekTwoWeather);
        this.threeGroup = (Group) findViewById(R.id.three_group);
        this.weekThree = (TextView) findViewById(R.id.weekThree);
        this.weekThreeName = (TextView) findViewById(R.id.weekThreeName);
        this.weekThreeDate = (TextView) findViewById(R.id.weekThreeDate);
        this.weekThreeWeather = (TextView) findViewById(R.id.weekThreeWeather);
        this.fourGroup = (Group) findViewById(R.id.four_group);
        this.weekFour = (TextView) findViewById(R.id.weekFour);
        this.weekFourName = (TextView) findViewById(R.id.weekFourName);
        this.weekFourDate = (TextView) findViewById(R.id.weekFourDate);
        this.weekFourWeather = (TextView) findViewById(R.id.weekFourWeather);
        this.location_name = (TextView) findViewById(R.id.location_name);
        this.weather_date = (TextView) findViewById(R.id.weather_date);
        this.view_line = findViewById(R.id.view_line);
        this.view_line_vertical = findViewById(R.id.view_line_vertical);
        this.weather_One_bg = findViewById(R.id.weather_One_bg);
        this.hours_UV_value = (TextView) findViewById(R.id.hours_UV_value);
        this.hours_wind_value = (TextView) findViewById(R.id.hours_wind_value);
        this.hours_Humi_value = (TextView) findViewById(R.id.hours_Humi_value);
        this.hours_UV_text = (TextView) findViewById(R.id.hours_UV_text);
        this.hours_wind_text = (TextView) findViewById(R.id.hours_wind_text);
        this.hours_Humi_text = (TextView) findViewById(R.id.hours_Humi_text);
    }

    @Override // com.byd.vrassistant.base.ConstraintLayoutLifecycle
    public void initViewModel() {
        this.commonViewModel = (CommonViewModel) ViewModelHelper.getAppViewModel(CommonViewModel.class);
        this.weatherViewModel = (WeatherViewModel) ViewModelHelper.getAppViewModel(WeatherViewModel.class);
        this.commonViewModel.getContextLiveData().observe(this, new Observer() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.setContext((Context) obj);
            }
        });
        this.weatherViewModel.getWeatherInfo().observe(this, new Observer() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda11
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.setWeatherContent((WeatherInformationParam) obj);
            }
        });
        this.commonViewModel.getSkinType().observe(this, new Observer() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda22
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.setSkin(((Integer) obj).intValue());
            }
        });
        this.commonViewModel.getLocaleLiveData().observe(this, new Observer() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda30
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m209lambda$initViewModel$0$combydvrassistantviewWeatherBar((Locale) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initViewModel$0$com-byd-vrassistant-view-WeatherBar, reason: not valid java name */
    /* synthetic */ void m209lambda$initViewModel$0$combydvrassistantviewWeatherBar(Locale locale) {
        TextViewUtils.getInstance().updateLocale(this, locale);
    }

    public void setSkin(int i) {
        if (i == 1) {
            this.weather_One_bg.setBackground(ContextCompat.getDrawable(this.mContext, R.drawable.bg_weather_today_bg));
            this.weather_bg_small.setBackground(ContextCompat.getDrawable(this.mContext, R.drawable.bg_weather_info_small_day));
            int color = ContextCompat.getColor(this.mContext, R.color.list_text_light);
            int color2 = ContextCompat.getColor(this.mContext, R.color.list_text_55_light);
            int color3 = ContextCompat.getColor(this.mContext, R.color.list_text_70_light);
            this.temp.setTextColor(color);
            this.tempUnit.setTextColor(color);
            this.weather_name.setTextColor(color3);
            this.view_line.setBackgroundColor(ContextCompat.getColor(this.mContext, R.color.view_divider_list));
            this.view_line_vertical.setBackgroundColor(color3);
            this.weekOne.setTextColor(color2);
            this.weekOneName.setTextColor(color3);
            this.weekOneDate.setTextColor(color2);
            this.weekOneWeather.setTextColor(color2);
            this.weekTwo.setTextColor(color2);
            this.weekTwoName.setTextColor(color3);
            this.weekTwoDate.setTextColor(color2);
            this.weekTwoWeather.setTextColor(color2);
            this.weekThree.setTextColor(color2);
            this.weekThreeName.setTextColor(color3);
            this.weekThreeDate.setTextColor(color2);
            this.weekThreeWeather.setTextColor(color2);
            this.weekFour.setTextColor(color2);
            this.weekFourName.setTextColor(color3);
            this.weekFourDate.setTextColor(color2);
            this.weekFourWeather.setTextColor(color2);
            this.location_name.setTextColor(ContextCompat.getColor(this.mContext, R.color.list_text_85_light));
            this.weather_date.setTextColor(ContextCompat.getColor(this.mContext, R.color.list_text_85_light));
            this.hours_UV_text.setTextColor(color3);
            this.hours_UV_value.setTextColor(color);
            this.hours_wind_text.setTextColor(color3);
            this.hours_wind_value.setTextColor(color);
            this.hours_Humi_text.setTextColor(color3);
            this.hours_Humi_value.setTextColor(color);
            return;
        }
        this.weather_One_bg.setBackground(ContextCompat.getDrawable(this.mContext, R.drawable.bg_weather_today_bg_night));
        this.weather_bg_small.setBackground(ContextCompat.getDrawable(this.mContext, R.drawable.bg_weather_info_small_night));
        int color4 = ContextCompat.getColor(this.mContext, R.color.list_text_dark);
        int color5 = ContextCompat.getColor(this.mContext, R.color.list_text_55_dark);
        int color6 = ContextCompat.getColor(this.mContext, R.color.list_text_70_dark);
        this.temp.setTextColor(color4);
        this.tempUnit.setTextColor(color4);
        this.weather_name.setTextColor(color6);
        this.view_line.setBackgroundColor(ContextCompat.getColor(this.mContext, R.color.view_divider_list_dark));
        this.view_line_vertical.setBackgroundColor(color6);
        this.weekOne.setTextColor(color5);
        this.weekOneName.setTextColor(color5);
        this.weekOneDate.setTextColor(color5);
        this.weekOneWeather.setTextColor(color5);
        this.weekTwo.setTextColor(color5);
        this.weekTwoName.setTextColor(color5);
        this.weekTwoDate.setTextColor(color5);
        this.weekTwoWeather.setTextColor(color5);
        this.weekThree.setTextColor(color5);
        this.weekThreeName.setTextColor(color5);
        this.weekThreeDate.setTextColor(color5);
        this.weekThreeWeather.setTextColor(color5);
        this.weekFour.setTextColor(color5);
        this.weekFourName.setTextColor(color5);
        this.weekFourDate.setTextColor(color5);
        this.weekFourWeather.setTextColor(color5);
        this.location_name.setTextColor(ContextCompat.getColor(this.mContext, R.color.list_text_85_dark));
        this.weather_date.setTextColor(ContextCompat.getColor(this.mContext, R.color.list_text_85_dark));
        this.hours_UV_text.setTextColor(color6);
        this.hours_UV_value.setTextColor(color4);
        this.hours_wind_text.setTextColor(color6);
        this.hours_wind_value.setTextColor(color4);
        this.hours_Humi_text.setTextColor(color6);
        this.hours_Humi_value.setTextColor(color4);
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public void setWeatherContent(final WeatherInformationParam weatherInformationParam) {
        if (weatherInformationParam == null) {
            RLog.e(TAG, "setWeatherContent: null");
            return;
        }
        if ("OUT_OF_RANGE".equals(weatherInformationParam.getFocusType())) {
            RLog.e(TAG, "setWeatherContent: OUT_OF_RANGE");
            return;
        }
        RLog.d(TAG, "setWeatherContent getFocusType=" + weatherInformationParam.getFocusType());
        RLog.d(TAG, "setWeatherContent getStartIndex=" + weatherInformationParam.getStartIndex() + ",getEndIndex=" + weatherInformationParam.getEndIndex());
        trySetText(this.location_name, new GetString() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda31
            @Override // com.byd.vrassistant.view.GetString
            public final String get() {
                return weatherInformationParam.getLocation().getCity();
            }
        });
        try {
            if ("UOM_CELSIUS".equals(weatherInformationParam.getCurrentInfo().getTemperature().getType())) {
                this.tmpType = "°C";
            } else {
                this.tmpType = "°F";
            }
        } catch (Exception e) {
            RLog.e(TAG, "setWeatherContent: UOM_CELSIUS", e);
            this.tmpType = "°C";
        }
        showTopWeatherInfo(weatherInformationParam);
        showDaysWeather(weatherInformationParam);
    }

    private void showDaysWeather(WeatherInformationParam weatherInformationParam) {
        int i;
        if ("QUERY_WEEKEND".equals(weatherInformationParam.getSearchCriteria().getQueryExtension())) {
            i = weatherInformationParam.getEndIndex() > weatherInformationParam.getStartIndex() ? 2 : 1;
        } else {
            i = 4;
        }
        setDaysShow(i);
        int startIndex = WeatherFocusTypeEnum.DAILY_FORECASTS.equals(weatherInformationParam.getFocusType()) ? weatherInformationParam.getStartIndex() : 0;
        final DailyWeatherInfo dailyWeatherInfo = weatherInformationParam.getDayList().get(startIndex);
        final int weekNum = this.dateHelper.getWeekNum(dailyWeatherInfo.getDate());
        if (this.dateHelper.isToday(dailyWeatherInfo.getDate())) {
            this.weekOne.setText(this.mContext.getResources().getString(R.string.weather_today));
        } else {
            trySetText(this.weekOne, new GetString() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda3
                @Override // com.byd.vrassistant.view.GetString
                public final String get() {
                    return this.f$0.m219lambda$showDaysWeather$2$combydvrassistantviewWeatherBar(weekNum);
                }
            });
        }
        if (weatherInformationParam.getCurrentInfo().getDayTime()) {
            trySetImg(this.weekOneImage, new GerImgInt() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda14
                @Override // com.byd.vrassistant.view.GerImgInt
                public final int get() {
                    return this.f$0.m220lambda$showDaysWeather$3$combydvrassistantviewWeatherBar(dailyWeatherInfo);
                }
            });
        } else {
            trySetImg(this.weekOneImage, new GerImgInt() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda16
                @Override // com.byd.vrassistant.view.GerImgInt
                public final int get() {
                    return this.f$0.m221lambda$showDaysWeather$4$combydvrassistantviewWeatherBar(dailyWeatherInfo);
                }
            });
        }
        trySetText(this.weekOneName, new GetString() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda17
            @Override // com.byd.vrassistant.view.GetString
            public final String get() {
                return this.f$0.m222lambda$showDaysWeather$5$combydvrassistantviewWeatherBar(dailyWeatherInfo);
            }
        });
        TextView textView = this.weekOneDate;
        Objects.requireNonNull(dailyWeatherInfo);
        trySetText(textView, new GetString() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda7
            @Override // com.byd.vrassistant.view.GetString
            public final String get() {
                return dailyWeatherInfo.getDate();
            }
        });
        trySetText(this.weekOneWeather, new GetString() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda18
            @Override // com.byd.vrassistant.view.GetString
            public final String get() {
                return dailyWeatherInfo.getDay().getIcon().getPhrase();
            }
        });
        if (i >= 2) {
            final DailyWeatherInfo dailyWeatherInfo2 = weatherInformationParam.getDayList().get(startIndex + 1);
            final int weekNum2 = this.dateHelper.getWeekNum(dailyWeatherInfo2.getDate());
            trySetText(this.weekTwo, new GetString() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda19
                @Override // com.byd.vrassistant.view.GetString
                public final String get() {
                    return this.f$0.m223lambda$showDaysWeather$7$combydvrassistantviewWeatherBar(weekNum2);
                }
            });
            trySetImg(this.weekTwoImage, new GerImgInt() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda20
                @Override // com.byd.vrassistant.view.GerImgInt
                public final int get() {
                    return this.f$0.m224lambda$showDaysWeather$8$combydvrassistantviewWeatherBar(dailyWeatherInfo2);
                }
            });
            TextView textView2 = this.weekTwoDate;
            Objects.requireNonNull(dailyWeatherInfo2);
            trySetText(textView2, new GetString() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda7
                @Override // com.byd.vrassistant.view.GetString
                public final String get() {
                    return dailyWeatherInfo2.getDate();
                }
            });
            trySetText(this.weekTwoWeather, new GetString() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda21
                @Override // com.byd.vrassistant.view.GetString
                public final String get() {
                    return dailyWeatherInfo2.getDay().getIcon().getPhrase();
                }
            });
            trySetText(this.weekTwoName, new GetString() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda4
                @Override // com.byd.vrassistant.view.GetString
                public final String get() {
                    return this.f$0.m212lambda$showDaysWeather$10$combydvrassistantviewWeatherBar(dailyWeatherInfo2);
                }
            });
        }
        if (i >= 3) {
            final DailyWeatherInfo dailyWeatherInfo3 = weatherInformationParam.getDayList().get(startIndex + 2);
            final int weekNum3 = this.dateHelper.getWeekNum(dailyWeatherInfo3.getDate());
            trySetText(this.weekThree, new GetString() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda5
                @Override // com.byd.vrassistant.view.GetString
                public final String get() {
                    return this.f$0.m213lambda$showDaysWeather$11$combydvrassistantviewWeatherBar(weekNum3);
                }
            });
            trySetImg(this.weekThreeImage, new GerImgInt() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda6
                @Override // com.byd.vrassistant.view.GerImgInt
                public final int get() {
                    return this.f$0.m214lambda$showDaysWeather$12$combydvrassistantviewWeatherBar(dailyWeatherInfo3);
                }
            });
            TextView textView3 = this.weekThreeDate;
            Objects.requireNonNull(dailyWeatherInfo3);
            trySetText(textView3, new GetString() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda7
                @Override // com.byd.vrassistant.view.GetString
                public final String get() {
                    return dailyWeatherInfo3.getDate();
                }
            });
            trySetText(this.weekThreeWeather, new GetString() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda8
                @Override // com.byd.vrassistant.view.GetString
                public final String get() {
                    return dailyWeatherInfo3.getDay().getIcon().getPhrase();
                }
            });
            trySetText(this.weekThreeName, new GetString() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda9
                @Override // com.byd.vrassistant.view.GetString
                public final String get() {
                    return this.f$0.m215lambda$showDaysWeather$14$combydvrassistantviewWeatherBar(dailyWeatherInfo3);
                }
            });
        }
        if (i == 4) {
            final DailyWeatherInfo dailyWeatherInfo4 = weatherInformationParam.getDayList().get(startIndex + 3);
            final int weekNum4 = this.dateHelper.getWeekNum(dailyWeatherInfo4.getDate());
            RLog.i(TAG, "showDaysWeather: firstWeekDay=" + weekNum + ",finalWeek=" + weekNum4);
            trySetText(this.weekFour, new GetString() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda10
                @Override // com.byd.vrassistant.view.GetString
                public final String get() {
                    return this.f$0.m216lambda$showDaysWeather$15$combydvrassistantviewWeatherBar(weekNum4);
                }
            });
            trySetImg(this.weekFourImage, new GerImgInt() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda12
                @Override // com.byd.vrassistant.view.GerImgInt
                public final int get() {
                    return this.f$0.m217lambda$showDaysWeather$16$combydvrassistantviewWeatherBar(dailyWeatherInfo4);
                }
            });
            TextView textView4 = this.weekFourDate;
            Objects.requireNonNull(dailyWeatherInfo4);
            trySetText(textView4, new GetString() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda7
                @Override // com.byd.vrassistant.view.GetString
                public final String get() {
                    return dailyWeatherInfo4.getDate();
                }
            });
            trySetText(this.weekFourWeather, new GetString() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda13
                @Override // com.byd.vrassistant.view.GetString
                public final String get() {
                    return dailyWeatherInfo4.getDay().getIcon().getPhrase();
                }
            });
            trySetText(this.weekFourName, new GetString() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda15
                @Override // com.byd.vrassistant.view.GetString
                public final String get() {
                    return this.f$0.m218lambda$showDaysWeather$18$combydvrassistantviewWeatherBar(dailyWeatherInfo4);
                }
            });
        }
        setWeatherTextShow();
    }

    /* JADX INFO: renamed from: lambda$showDaysWeather$2$com-byd-vrassistant-view-WeatherBar, reason: not valid java name */
    /* synthetic */ String m219lambda$showDaysWeather$2$combydvrassistantviewWeatherBar(int i) {
        return this.weatherRes.getJXweek(this.mContext, i);
    }

    /* JADX INFO: renamed from: lambda$showDaysWeather$3$com-byd-vrassistant-view-WeatherBar, reason: not valid java name */
    /* synthetic */ int m220lambda$showDaysWeather$3$combydvrassistantviewWeatherBar(DailyWeatherInfo dailyWeatherInfo) {
        return this.weatherRes.get72ResId(dailyWeatherInfo.getDay().getIcon().getId());
    }

    /* JADX INFO: renamed from: lambda$showDaysWeather$4$com-byd-vrassistant-view-WeatherBar, reason: not valid java name */
    /* synthetic */ int m221lambda$showDaysWeather$4$combydvrassistantviewWeatherBar(DailyWeatherInfo dailyWeatherInfo) {
        return this.weatherRes.get72ResId(dailyWeatherInfo.getNight().getIcon().getId());
    }

    /* JADX INFO: renamed from: lambda$showDaysWeather$5$com-byd-vrassistant-view-WeatherBar, reason: not valid java name */
    /* synthetic */ String m222lambda$showDaysWeather$5$combydvrassistantviewWeatherBar(DailyWeatherInfo dailyWeatherInfo) {
        return dailyWeatherInfo.getTemperature().getLow().getValue() + "/" + dailyWeatherInfo.getTemperature().getHigh().getValue() + this.tmpType;
    }

    /* JADX INFO: renamed from: lambda$showDaysWeather$7$com-byd-vrassistant-view-WeatherBar, reason: not valid java name */
    /* synthetic */ String m223lambda$showDaysWeather$7$combydvrassistantviewWeatherBar(int i) {
        return this.weatherRes.getJXweek(this.mContext, i);
    }

    /* JADX INFO: renamed from: lambda$showDaysWeather$8$com-byd-vrassistant-view-WeatherBar, reason: not valid java name */
    /* synthetic */ int m224lambda$showDaysWeather$8$combydvrassistantviewWeatherBar(DailyWeatherInfo dailyWeatherInfo) {
        return this.weatherRes.get72ResId(dailyWeatherInfo.getDay().getIcon().getId());
    }

    /* JADX INFO: renamed from: lambda$showDaysWeather$10$com-byd-vrassistant-view-WeatherBar, reason: not valid java name */
    /* synthetic */ String m212lambda$showDaysWeather$10$combydvrassistantviewWeatherBar(DailyWeatherInfo dailyWeatherInfo) {
        return dailyWeatherInfo.getTemperature().getLow().getValue() + "/" + dailyWeatherInfo.getTemperature().getHigh().getValue() + this.tmpType;
    }

    /* JADX INFO: renamed from: lambda$showDaysWeather$11$com-byd-vrassistant-view-WeatherBar, reason: not valid java name */
    /* synthetic */ String m213lambda$showDaysWeather$11$combydvrassistantviewWeatherBar(int i) {
        return this.weatherRes.getJXweek(this.mContext, i);
    }

    /* JADX INFO: renamed from: lambda$showDaysWeather$12$com-byd-vrassistant-view-WeatherBar, reason: not valid java name */
    /* synthetic */ int m214lambda$showDaysWeather$12$combydvrassistantviewWeatherBar(DailyWeatherInfo dailyWeatherInfo) {
        return this.weatherRes.get72ResId(dailyWeatherInfo.getDay().getIcon().getId());
    }

    /* JADX INFO: renamed from: lambda$showDaysWeather$14$com-byd-vrassistant-view-WeatherBar, reason: not valid java name */
    /* synthetic */ String m215lambda$showDaysWeather$14$combydvrassistantviewWeatherBar(DailyWeatherInfo dailyWeatherInfo) {
        return dailyWeatherInfo.getTemperature().getLow().getValue() + "/" + dailyWeatherInfo.getTemperature().getHigh().getValue() + this.tmpType;
    }

    /* JADX INFO: renamed from: lambda$showDaysWeather$15$com-byd-vrassistant-view-WeatherBar, reason: not valid java name */
    /* synthetic */ String m216lambda$showDaysWeather$15$combydvrassistantviewWeatherBar(int i) {
        return this.weatherRes.getJXweek(this.mContext, i);
    }

    /* JADX INFO: renamed from: lambda$showDaysWeather$16$com-byd-vrassistant-view-WeatherBar, reason: not valid java name */
    /* synthetic */ int m217lambda$showDaysWeather$16$combydvrassistantviewWeatherBar(DailyWeatherInfo dailyWeatherInfo) {
        return this.weatherRes.get72ResId(dailyWeatherInfo.getDay().getIcon().getId());
    }

    /* JADX INFO: renamed from: lambda$showDaysWeather$18$com-byd-vrassistant-view-WeatherBar, reason: not valid java name */
    /* synthetic */ String m218lambda$showDaysWeather$18$combydvrassistantviewWeatherBar(DailyWeatherInfo dailyWeatherInfo) {
        return dailyWeatherInfo.getTemperature().getLow().getValue() + "/" + dailyWeatherInfo.getTemperature().getHigh().getValue() + this.tmpType;
    }

    private void setWeatherTextShow() {
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.px_102);
        Paint paint = new Paint();
        paint.setTextSize(18.0f);
        float fMeasureText = paint.measureText(this.weekOneWeather.getText().toString());
        float fMeasureText2 = paint.measureText(this.weekTwoWeather.getText().toString());
        float fMeasureText3 = paint.measureText(this.weekThreeWeather.getText().toString());
        float fMeasureText4 = paint.measureText(this.weekFourWeather.getText().toString());
        float f = dimensionPixelSize;
        if (fMeasureText > f || fMeasureText2 > f || fMeasureText3 > f || fMeasureText4 > f) {
            this.weekOneWeather.setVisibility(8);
            this.weekTwoWeather.setVisibility(8);
            this.weekThreeWeather.setVisibility(8);
            this.weekFourWeather.setVisibility(8);
        }
    }

    private void setDaysShow(int i) {
        this.twoGroup.setVisibility(i >= 2 ? 0 : 4);
        this.weekTwoWeather.setVisibility(i >= 2 ? 0 : 4);
        this.threeGroup.setVisibility(i >= 3 ? 0 : 4);
        this.weekThreeWeather.setVisibility(i >= 3 ? 0 : 4);
        this.fourGroup.setVisibility(i >= 4 ? 0 : 4);
        this.weekFourWeather.setVisibility(i < 4 ? 4 : 0);
    }

    private void showTopWeatherInfo(WeatherInformationParam weatherInformationParam) {
        this.tempUnit.setText(this.tmpType);
        if (weatherInformationParam.getFocusType().equals(WeatherFocusTypeEnum.HOURLY_FORECASTS)) {
            showHourWeatherInfo(weatherInformationParam.getHourList().get(weatherInformationParam.getStartIndex()));
        } else {
            showCurrentWeatherInfo(weatherInformationParam.getCurrentInfo());
        }
    }

    private void showHourWeatherInfo(final HourlyWeatherInfo hourlyWeatherInfo) {
        trySetImg(this.weather_image, new GerImgInt() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda23
            @Override // com.byd.vrassistant.view.GerImgInt
            public final int get() {
                return this.f$0.m225x8b5bb74(hourlyWeatherInfo);
            }
        });
        trySetText(this.weather_name, new GetString() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda24
            @Override // com.byd.vrassistant.view.GetString
            public final String get() {
                return hourlyWeatherInfo.getIcon().getPhrase();
            }
        });
        trySetText(this.temp, new GetString() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda25
            @Override // com.byd.vrassistant.view.GetString
            public final String get() {
                return hourlyWeatherInfo.getTemperature().getValue();
            }
        });
        trySetText(this.hours_UV_value, new GetString() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda26
            @Override // com.byd.vrassistant.view.GetString
            public final String get() {
                return String.valueOf(hourlyWeatherInfo.getUv().getIndex());
            }
        });
        trySetText(this.hours_wind_value, new GetString() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda27
            @Override // com.byd.vrassistant.view.GetString
            public final String get() {
                return WeatherBar.lambda$showHourWeatherInfo$23(hourlyWeatherInfo);
            }
        });
        trySetText(this.hours_Humi_value, new GetString() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda28
            @Override // com.byd.vrassistant.view.GetString
            public final String get() {
                return WeatherBar.lambda$showHourWeatherInfo$24(hourlyWeatherInfo);
            }
        });
        final int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.px_32);
        this.hours_Humi_value.post(new Runnable() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda29
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m226x1b690a0f(dimensionPixelSize);
            }
        });
        String dateTime = hourlyWeatherInfo.getDateTime();
        this.weather_date.setText(this.dateHelper.format(dateTime, "MM/dd") + " " + this.weatherRes.getJXweek(this.mContext, this.dateHelper.getWeekOneDayNumHour(dateTime)) + " " + this.dateHelper.getTime(dateTime, DateFormat.is24HourFormat(this.mContext)));
    }

    /* JADX INFO: renamed from: lambda$showHourWeatherInfo$19$com-byd-vrassistant-view-WeatherBar, reason: not valid java name */
    /* synthetic */ int m225x8b5bb74(HourlyWeatherInfo hourlyWeatherInfo) {
        return this.weatherRes.get120ResId(hourlyWeatherInfo.getIcon().getId());
    }

    static /* synthetic */ String lambda$showHourWeatherInfo$23(HourlyWeatherInfo hourlyWeatherInfo) {
        return hourlyWeatherInfo.getWind().getDirectionCode().substring(20) + " " + hourlyWeatherInfo.getWind().getSpeed().getValue() + hourlyWeatherInfo.getWind().getSpeed().getUnit();
    }

    static /* synthetic */ String lambda$showHourWeatherInfo$24(HourlyWeatherInfo hourlyWeatherInfo) {
        return hourlyWeatherInfo.getHumidity() + "%";
    }

    /* JADX INFO: renamed from: lambda$showHourWeatherInfo$25$com-byd-vrassistant-view-WeatherBar, reason: not valid java name */
    /* synthetic */ void m226x1b690a0f(int i) {
        if (this.hours_UV_value.getWidth() > i || this.hours_wind_value.getWidth() > i || this.hours_Humi_value.getWidth() > i) {
            this.hours_UV_text.setVisibility(8);
            this.hours_wind_text.setVisibility(8);
            this.hours_Humi_text.setVisibility(8);
        }
    }

    private void showCurrentWeatherInfo(final CurrentWeatherInfo currentWeatherInfo) {
        trySetImg(this.weather_image, new GerImgInt() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda32
            @Override // com.byd.vrassistant.view.GerImgInt
            public final int get() {
                return this.f$0.m210xcc6672f1(currentWeatherInfo);
            }
        });
        trySetText(this.weather_name, new GetString() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda33
            @Override // com.byd.vrassistant.view.GetString
            public final String get() {
                return currentWeatherInfo.getIcon().getPhrase();
            }
        });
        trySetText(this.temp, new GetString() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda34
            @Override // com.byd.vrassistant.view.GetString
            public final String get() {
                return currentWeatherInfo.getTemperature().getValue();
            }
        });
        trySetText(this.hours_UV_value, new GetString() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda35
            @Override // com.byd.vrassistant.view.GetString
            public final String get() {
                return String.valueOf(currentWeatherInfo.getUv().getIndex());
            }
        });
        trySetText(this.hours_wind_value, new GetString() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda36
            @Override // com.byd.vrassistant.view.GetString
            public final String get() {
                return WeatherBar.lambda$showCurrentWeatherInfo$30(currentWeatherInfo);
            }
        });
        trySetText(this.hours_Humi_value, new GetString() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda1
            @Override // com.byd.vrassistant.view.GetString
            public final String get() {
                return WeatherBar.lambda$showCurrentWeatherInfo$31(currentWeatherInfo);
            }
        });
        final int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.px_32);
        this.hours_Humi_value.post(new Runnable() { // from class: com.byd.vrassistant.view.WeatherBar$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m211xdf19c18c(dimensionPixelSize);
            }
        });
        String dateTime = currentWeatherInfo.getDateTime();
        this.weather_date.setText(this.dateHelper.format(dateTime, "MM/dd") + " " + this.weatherRes.getJXweek(this.mContext, this.dateHelper.getWeekOneDayNumHour(dateTime)) + " " + this.dateHelper.getTime(dateTime, DateFormat.is24HourFormat(this.mContext)));
    }

    /* JADX INFO: renamed from: lambda$showCurrentWeatherInfo$26$com-byd-vrassistant-view-WeatherBar, reason: not valid java name */
    /* synthetic */ int m210xcc6672f1(CurrentWeatherInfo currentWeatherInfo) {
        return this.weatherRes.get120ResId(currentWeatherInfo.getIcon().getId());
    }

    static /* synthetic */ String lambda$showCurrentWeatherInfo$30(CurrentWeatherInfo currentWeatherInfo) {
        return currentWeatherInfo.getWind().getDirectionCode().substring(20) + " " + currentWeatherInfo.getWind().getSpeed().getValue() + currentWeatherInfo.getWind().getSpeed().getUnit();
    }

    static /* synthetic */ String lambda$showCurrentWeatherInfo$31(CurrentWeatherInfo currentWeatherInfo) {
        return currentWeatherInfo.getHumidity() + "%";
    }

    /* JADX INFO: renamed from: lambda$showCurrentWeatherInfo$32$com-byd-vrassistant-view-WeatherBar, reason: not valid java name */
    /* synthetic */ void m211xdf19c18c(int i) {
        if (this.hours_UV_value.getWidth() > i || this.hours_wind_value.getWidth() > i || this.hours_Humi_value.getWidth() > i) {
            this.hours_UV_text.setVisibility(8);
            this.hours_wind_text.setVisibility(8);
            this.hours_Humi_text.setVisibility(8);
        }
    }

    private void trySetText(TextView textView, GetString getString) {
        try {
            textView.setText(getString.get());
        } catch (Exception e) {
            textView.setText("");
            RLog.e(TAG, "trySetText: ", e);
        }
    }

    private void trySetImg(ImageView imageView, GerImgInt gerImgInt) {
        try {
            imageView.setImageResource(gerImgInt.get());
        } catch (Exception e) {
            RLog.e(TAG, "trySetImg: ", e);
        }
    }
}
