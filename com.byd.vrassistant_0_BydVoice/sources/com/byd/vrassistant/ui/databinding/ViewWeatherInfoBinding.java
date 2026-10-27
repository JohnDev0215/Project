package com.byd.vrassistant.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrassistant.ui.R;

/* JADX INFO: loaded from: classes2.dex */
public final class ViewWeatherInfoBinding implements ViewBinding {
    public final ImageView UVImage;
    public final TextView UVIndex;
    public final ConstraintLayout clTemp;
    public final Group fourGroup;
    public final ImageView hoursHumi;
    public final TextView hoursHumiText;
    public final TextView hoursHumiValue;
    public final ImageView hoursUV;
    public final TextView hoursUVText;
    public final TextView hoursUVValue;
    public final ConstraintLayout hoursWeather;
    public final ImageView hoursWind;
    public final TextView hoursWindText;
    public final TextView hoursWindValue;
    public final ImageView locationImage;
    public final TextView locationName;
    public final Group oneGroup;
    private final ConstraintLayout rootView;
    public final Group threeGroup;
    public final TextView todayUVValue;
    public final ConstraintLayout todayWeather;
    public final TextView tvTemp;
    public final TextView tvTempUnit;
    public final Group twoGroup;
    public final View viewLine;
    public final View viewLineVertical;
    public final ConstraintLayout viewWeather;
    public final View weatherBgBig;
    public final View weatherBgSmall;
    public final TextView weatherDate;
    public final View weatherFourBg;
    public final ImageView weatherImage;
    public final TextView weatherName;
    public final View weatherOneBg;
    public final View weatherThreeBg;
    public final View weatherTwoBg;
    public final TextView weekFour;
    public final TextView weekFourDate;
    public final ImageView weekFourImage;
    public final TextView weekFourName;
    public final TextView weekFourWeather;
    public final TextView weekOne;
    public final TextView weekOneDate;
    public final ImageView weekOneImage;
    public final TextView weekOneName;
    public final TextView weekOneWeather;
    public final TextView weekThree;
    public final TextView weekThreeDate;
    public final ImageView weekThreeImage;
    public final TextView weekThreeName;
    public final TextView weekThreeWeather;
    public final TextView weekTwo;
    public final TextView weekTwoDate;
    public final ImageView weekTwoImage;
    public final TextView weekTwoName;
    public final TextView weekTwoWeather;

    private ViewWeatherInfoBinding(ConstraintLayout constraintLayout, ImageView imageView, TextView textView, ConstraintLayout constraintLayout2, Group group, ImageView imageView2, TextView textView2, TextView textView3, ImageView imageView3, TextView textView4, TextView textView5, ConstraintLayout constraintLayout3, ImageView imageView4, TextView textView6, TextView textView7, ImageView imageView5, TextView textView8, Group group2, Group group3, TextView textView9, ConstraintLayout constraintLayout4, TextView textView10, TextView textView11, Group group4, View view, View view2, ConstraintLayout constraintLayout5, View view3, View view4, TextView textView12, View view5, ImageView imageView6, TextView textView13, View view6, View view7, View view8, TextView textView14, TextView textView15, ImageView imageView7, TextView textView16, TextView textView17, TextView textView18, TextView textView19, ImageView imageView8, TextView textView20, TextView textView21, TextView textView22, TextView textView23, ImageView imageView9, TextView textView24, TextView textView25, TextView textView26, TextView textView27, ImageView imageView10, TextView textView28, TextView textView29) {
        this.rootView = constraintLayout;
        this.UVImage = imageView;
        this.UVIndex = textView;
        this.clTemp = constraintLayout2;
        this.fourGroup = group;
        this.hoursHumi = imageView2;
        this.hoursHumiText = textView2;
        this.hoursHumiValue = textView3;
        this.hoursUV = imageView3;
        this.hoursUVText = textView4;
        this.hoursUVValue = textView5;
        this.hoursWeather = constraintLayout3;
        this.hoursWind = imageView4;
        this.hoursWindText = textView6;
        this.hoursWindValue = textView7;
        this.locationImage = imageView5;
        this.locationName = textView8;
        this.oneGroup = group2;
        this.threeGroup = group3;
        this.todayUVValue = textView9;
        this.todayWeather = constraintLayout4;
        this.tvTemp = textView10;
        this.tvTempUnit = textView11;
        this.twoGroup = group4;
        this.viewLine = view;
        this.viewLineVertical = view2;
        this.viewWeather = constraintLayout5;
        this.weatherBgBig = view3;
        this.weatherBgSmall = view4;
        this.weatherDate = textView12;
        this.weatherFourBg = view5;
        this.weatherImage = imageView6;
        this.weatherName = textView13;
        this.weatherOneBg = view6;
        this.weatherThreeBg = view7;
        this.weatherTwoBg = view8;
        this.weekFour = textView14;
        this.weekFourDate = textView15;
        this.weekFourImage = imageView7;
        this.weekFourName = textView16;
        this.weekFourWeather = textView17;
        this.weekOne = textView18;
        this.weekOneDate = textView19;
        this.weekOneImage = imageView8;
        this.weekOneName = textView20;
        this.weekOneWeather = textView21;
        this.weekThree = textView22;
        this.weekThreeDate = textView23;
        this.weekThreeImage = imageView9;
        this.weekThreeName = textView24;
        this.weekThreeWeather = textView25;
        this.weekTwo = textView26;
        this.weekTwoDate = textView27;
        this.weekTwoImage = imageView10;
        this.weekTwoName = textView28;
        this.weekTwoWeather = textView29;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ViewWeatherInfoBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ViewWeatherInfoBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.view_weather_info, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ViewWeatherInfoBinding bind(View view) {
        View viewFindChildViewById;
        View viewFindChildViewById2;
        View viewFindChildViewById3;
        View viewFindChildViewById4;
        View viewFindChildViewById5;
        View viewFindChildViewById6;
        View viewFindChildViewById7;
        int i = R.id.UV_image;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.UV_index;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.cl_temp;
                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                if (constraintLayout != null) {
                    i = R.id.four_group;
                    Group group = (Group) ViewBindings.findChildViewById(view, i);
                    if (group != null) {
                        i = R.id.hours_Humi;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                        if (imageView2 != null) {
                            i = R.id.hours_Humi_text;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView2 != null) {
                                i = R.id.hours_Humi_value;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView3 != null) {
                                    i = R.id.hours_UV;
                                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i);
                                    if (imageView3 != null) {
                                        i = R.id.hours_UV_text;
                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                                        if (textView4 != null) {
                                            i = R.id.hours_UV_value;
                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, i);
                                            if (textView5 != null) {
                                                i = R.id.hours_weather;
                                                ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                                                if (constraintLayout2 != null) {
                                                    i = R.id.hours_Wind;
                                                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                    if (imageView4 != null) {
                                                        i = R.id.hours_wind_text;
                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, i);
                                                        if (textView6 != null) {
                                                            i = R.id.hours_wind_value;
                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(view, i);
                                                            if (textView7 != null) {
                                                                i = R.id.location_Image;
                                                                ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                                if (imageView5 != null) {
                                                                    i = R.id.location_name;
                                                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                    if (textView8 != null) {
                                                                        i = R.id.one_group;
                                                                        Group group2 = (Group) ViewBindings.findChildViewById(view, i);
                                                                        if (group2 != null) {
                                                                            i = R.id.three_group;
                                                                            Group group3 = (Group) ViewBindings.findChildViewById(view, i);
                                                                            if (group3 != null) {
                                                                                i = R.id.today_UV_value;
                                                                                TextView textView9 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                if (textView9 != null) {
                                                                                    i = R.id.today_weather;
                                                                                    ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                                                                                    if (constraintLayout3 != null) {
                                                                                        i = R.id.tv_temp;
                                                                                        TextView textView10 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                        if (textView10 != null) {
                                                                                            i = R.id.tv_temp_unit;
                                                                                            TextView textView11 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                            if (textView11 != null) {
                                                                                                i = R.id.two_group;
                                                                                                Group group4 = (Group) ViewBindings.findChildViewById(view, i);
                                                                                                if (group4 != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.view_line))) != null && (viewFindChildViewById2 = ViewBindings.findChildViewById(view, (i = R.id.view_line_vertical))) != null) {
                                                                                                    ConstraintLayout constraintLayout4 = (ConstraintLayout) view;
                                                                                                    i = R.id.weather_bg_big;
                                                                                                    View viewFindChildViewById8 = ViewBindings.findChildViewById(view, i);
                                                                                                    if (viewFindChildViewById8 != null && (viewFindChildViewById3 = ViewBindings.findChildViewById(view, (i = R.id.weather_bg_small))) != null) {
                                                                                                        i = R.id.weather_date;
                                                                                                        TextView textView12 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                        if (textView12 != null && (viewFindChildViewById4 = ViewBindings.findChildViewById(view, (i = R.id.weather_Four_bg))) != null) {
                                                                                                            i = R.id.weather_image;
                                                                                                            ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                                                                            if (imageView6 != null) {
                                                                                                                i = R.id.weather_name;
                                                                                                                TextView textView13 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                if (textView13 != null && (viewFindChildViewById5 = ViewBindings.findChildViewById(view, (i = R.id.weather_One_bg))) != null && (viewFindChildViewById6 = ViewBindings.findChildViewById(view, (i = R.id.weather_Three_bg))) != null && (viewFindChildViewById7 = ViewBindings.findChildViewById(view, (i = R.id.weather_Two_bg))) != null) {
                                                                                                                    i = R.id.weekFour;
                                                                                                                    TextView textView14 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                    if (textView14 != null) {
                                                                                                                        i = R.id.weekFourDate;
                                                                                                                        TextView textView15 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                        if (textView15 != null) {
                                                                                                                            i = R.id.weekFourImage;
                                                                                                                            ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                            if (imageView7 != null) {
                                                                                                                                i = R.id.weekFourName;
                                                                                                                                TextView textView16 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                if (textView16 != null) {
                                                                                                                                    i = R.id.weekFourWeather;
                                                                                                                                    TextView textView17 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                    if (textView17 != null) {
                                                                                                                                        i = R.id.weekOne;
                                                                                                                                        TextView textView18 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                        if (textView18 != null) {
                                                                                                                                            i = R.id.weekOneDate;
                                                                                                                                            TextView textView19 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                            if (textView19 != null) {
                                                                                                                                                i = R.id.weekOneImage;
                                                                                                                                                ImageView imageView8 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                if (imageView8 != null) {
                                                                                                                                                    i = R.id.weekOneName;
                                                                                                                                                    TextView textView20 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                    if (textView20 != null) {
                                                                                                                                                        i = R.id.weekOneWeather;
                                                                                                                                                        TextView textView21 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                        if (textView21 != null) {
                                                                                                                                                            i = R.id.weekThree;
                                                                                                                                                            TextView textView22 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                            if (textView22 != null) {
                                                                                                                                                                i = R.id.weekThreeDate;
                                                                                                                                                                TextView textView23 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                if (textView23 != null) {
                                                                                                                                                                    i = R.id.weekThreeImage;
                                                                                                                                                                    ImageView imageView9 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                    if (imageView9 != null) {
                                                                                                                                                                        i = R.id.weekThreeName;
                                                                                                                                                                        TextView textView24 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                        if (textView24 != null) {
                                                                                                                                                                            i = R.id.weekThreeWeather;
                                                                                                                                                                            TextView textView25 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                            if (textView25 != null) {
                                                                                                                                                                                i = R.id.weekTwo;
                                                                                                                                                                                TextView textView26 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                if (textView26 != null) {
                                                                                                                                                                                    i = R.id.weekTwoDate;
                                                                                                                                                                                    TextView textView27 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                    if (textView27 != null) {
                                                                                                                                                                                        i = R.id.weekTwoImage;
                                                                                                                                                                                        ImageView imageView10 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                        if (imageView10 != null) {
                                                                                                                                                                                            i = R.id.weekTwoName;
                                                                                                                                                                                            TextView textView28 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                            if (textView28 != null) {
                                                                                                                                                                                                i = R.id.weekTwoWeather;
                                                                                                                                                                                                TextView textView29 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                                                                                                                                                if (textView29 != null) {
                                                                                                                                                                                                    return new ViewWeatherInfoBinding(constraintLayout4, imageView, textView, constraintLayout, group, imageView2, textView2, textView3, imageView3, textView4, textView5, constraintLayout2, imageView4, textView6, textView7, imageView5, textView8, group2, group3, textView9, constraintLayout3, textView10, textView11, group4, viewFindChildViewById, viewFindChildViewById2, constraintLayout4, viewFindChildViewById8, viewFindChildViewById3, textView12, viewFindChildViewById4, imageView6, textView13, viewFindChildViewById5, viewFindChildViewById6, viewFindChildViewById7, textView14, textView15, imageView7, textView16, textView17, textView18, textView19, imageView8, textView20, textView21, textView22, textView23, imageView9, textView24, textView25, textView26, textView27, imageView10, textView28, textView29);
                                                                                                                                                                                                }
                                                                                                                                                                                            }
                                                                                                                                                                                        }
                                                                                                                                                                                    }
                                                                                                                                                                                }
                                                                                                                                                                            }
                                                                                                                                                                        }
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
