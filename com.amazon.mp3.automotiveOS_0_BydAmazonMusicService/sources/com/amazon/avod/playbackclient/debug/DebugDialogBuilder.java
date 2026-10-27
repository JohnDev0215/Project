package com.amazon.avod.playbackclient.debug;

import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import android.R;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.amazon.avod.config.ServiceDebugConfig;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.urlvending.FailoverMode;
import com.amazon.avod.content.urlvending.FailoverType;
import com.amazon.avod.media.diagnostics.DiagnosticsController;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.playback.IllegalPlayerStateException;
import com.amazon.avod.playback.PlaybackExperienceController;
import com.amazon.avod.playback.R$id;
import com.amazon.avod.playback.R$layout;
import com.amazon.avod.playbackclient.presenters.SurfaceSwitcher;
import com.amazon.avod.util.DLog;
import com.google.android.gms.cast.MediaError;
import com.google.common.base.Enums;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DebugDialogBuilder {
    private static final List<String> ENDPOINT_LOCATIONS = Arrays.asList("na", "eu", "fe");
    private Context mContext;
    private DiagnosticsController mDiagnosticsController;
    private FailoverMode mFailoverModeOverride;
    private PlaybackExperienceController mPlaybackExperienceController;
    private SurfaceSwitcher mSurfaceSwitcher;
    private List<QualityLevel> mVideoQualityLevels;
    private final List<Runnable> mOnShowRunnables = Lists.newArrayList();
    private final DebugOptions mDebugOptions = DebugOptions.getInstance();
    private final ServiceDebugConfig mServiceDebugConfig = ServiceDebugConfig.getInstance();
    private int mZigZagInterval = 10;
    private boolean mZigZagEnabled = false;
    private Map<String, String> mOverriddenEndpoints = new HashMap();
    private String mServiceEndpointOverrideLocation = ENDPOINT_LOCATIONS.get(0);

    public DebugDialogBuilder setContext(@Nonnull Context context) {
        this.mContext = (Context) Preconditions.checkNotNull(context, "context");
        return this;
    }

    public DebugDialogBuilder setDiagnosticsController(DiagnosticsController diagnosticsController) {
        this.mDiagnosticsController = diagnosticsController;
        return this;
    }

    public DebugDialogBuilder setPlaybackExperienceController(PlaybackExperienceController playbackExperienceController) {
        this.mPlaybackExperienceController = playbackExperienceController;
        return this;
    }

    private void initializeDiagnostics(View view) {
        if (this.mDiagnosticsController == null) {
            DLog.logf("Diagnostics controller not set - skipping setup.");
            return;
        }
        View viewFindViewById = view.findViewById(R$id.DiagnosticOptions);
        viewFindViewById.setVisibility(0);
        CheckBox checkBox = (CheckBox) viewFindViewById.findViewById(R$id.ShowGraphLinear);
        CheckBox checkBox2 = (CheckBox) viewFindViewById.findViewById(R$id.ShowGraphLogarithmic);
        CheckBox checkBox3 = (CheckBox) viewFindViewById.findViewById(R$id.ShowGraphEqualBandwidthSpaced);
        CheckBox checkBox4 = (CheckBox) viewFindViewById.findViewById(R$id.CompactView);
        checkBox4.setOnClickListener(createCompactViewListener(checkBox4));
        CheckBox checkBox5 = (CheckBox) viewFindViewById.findViewById(R$id.ShowQualityScore);
        checkBox5.setOnClickListener(createQualityScoreGraphListener(checkBox5));
        ArrayList arrayListNewArrayList = Lists.newArrayList(checkBox, checkBox2, checkBox3);
        checkBox.setOnClickListener(createDiagnosticsGraphSwitchListener(checkBox, DiagnosticsController.BandwidthScale.LINEAR, arrayListNewArrayList, checkBox4));
        checkBox2.setOnClickListener(createDiagnosticsGraphSwitchListener(checkBox2, DiagnosticsController.BandwidthScale.LOGARITHMIC, arrayListNewArrayList, checkBox4));
        checkBox3.setOnClickListener(createDiagnosticsGraphSwitchListener(checkBox3, DiagnosticsController.BandwidthScale.EQUISPACED, arrayListNewArrayList, checkBox4));
        CheckBox checkBox6 = (CheckBox) viewFindViewById.findViewById(R$id.ShowText);
        checkBox6.setOnClickListener(createDiagnosticsToggleTextListener(checkBox6));
        CheckBox checkBox7 = (CheckBox) viewFindViewById.findViewById(R$id.ShowToast);
        checkBox7.setOnClickListener(createDiagnosticsToastListener(checkBox7));
        CheckBox checkBox8 = (CheckBox) viewFindViewById.findViewById(R$id.ShowCDN);
        checkBox8.setOnClickListener(createDiagnosticsCdnGraphListener(checkBox8));
        CheckBox checkBox9 = (CheckBox) viewFindViewById.findViewById(R$id.ShowInternalState);
        checkBox9.setOnClickListener(createInternalStateListener(checkBox9));
        loadDebugOptions(checkBox, checkBox2, checkBox3, checkBox6, checkBox7, checkBox8, checkBox4, checkBox5, checkBox9);
    }

    private void loadDebugOptions(CheckBox checkBox, CheckBox checkBox2, CheckBox checkBox3, CheckBox checkBox4, CheckBox checkBox5, CheckBox checkBox6, CheckBox checkBox7, CheckBox checkBox8, CheckBox checkBox9) {
        if (this.mDebugOptions.isCompactViewEnabled()) {
            checkBox7.performClick();
        }
        if (this.mDebugOptions.isGraphLinearEnabled()) {
            checkBox.performClick();
        } else if (this.mDebugOptions.isGraphLogarithmicEnabled()) {
            checkBox2.performClick();
        } else if (this.mDebugOptions.isGraphEquiSpacedEnabled()) {
            checkBox3.performClick();
        }
        if (this.mDebugOptions.isInfoTextEnabled()) {
            checkBox4.performClick();
        }
        if (this.mDebugOptions.isDiagnosticToastEnabled()) {
            checkBox5.performClick();
        }
        if (this.mDebugOptions.isDiagnosticCdnGraphEnabled()) {
            checkBox6.performClick();
        }
        if (this.mDebugOptions.isQualityScoreGraphEnabled()) {
            checkBox8.performClick();
        }
        if (this.mDebugOptions.isInternalStateEnabled()) {
            checkBox9.performClick();
        }
        if (this.mDebugOptions.isInternalStateEnabledByOverride()) {
            this.mDiagnosticsController.showInternalState();
        }
    }

    private View.OnClickListener createDiagnosticsGraphSwitchListener(final CheckBox checkBox, final DiagnosticsController.BandwidthScale bandwidthScale, final List<CheckBox> list, final CheckBox checkBox2) {
        final DiagnosticsController diagnosticsController = this.mDiagnosticsController;
        return new View.OnClickListener() { // from class: com.amazon.avod.playbackclient.debug.DebugDialogBuilder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DebugDialogBuilder.this.mDebugOptions.setShowGraph(bandwidthScale, checkBox.isChecked(), checkBox2.isChecked());
                if (checkBox.isChecked()) {
                    diagnosticsController.showDiagnosticGraph(bandwidthScale, checkBox2.isChecked());
                    for (CheckBox checkBox3 : list) {
                        if (checkBox3 != checkBox) {
                            checkBox3.setChecked(false);
                        }
                    }
                    return;
                }
                diagnosticsController.hideDiagnosticGraph();
            }
        };
    }

    private View.OnClickListener createCompactViewListener(final CheckBox checkBox) {
        final DiagnosticsController diagnosticsController = this.mDiagnosticsController;
        return new View.OnClickListener() { // from class: com.amazon.avod.playbackclient.debug.DebugDialogBuilder.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DebugDialogBuilder.this.mDebugOptions.setCompactView(checkBox.isChecked());
                diagnosticsController.hideDiagnosticGraph();
                if (DebugDialogBuilder.this.mDebugOptions.isGraphLinearEnabled()) {
                    diagnosticsController.showDiagnosticGraph(DiagnosticsController.BandwidthScale.LINEAR, checkBox.isChecked());
                } else if (DebugDialogBuilder.this.mDebugOptions.isGraphLogarithmicEnabled()) {
                    diagnosticsController.showDiagnosticGraph(DiagnosticsController.BandwidthScale.LOGARITHMIC, checkBox.isChecked());
                } else if (DebugDialogBuilder.this.mDebugOptions.isGraphEquiSpacedEnabled()) {
                    diagnosticsController.showDiagnosticGraph(DiagnosticsController.BandwidthScale.EQUISPACED, checkBox.isChecked());
                }
            }
        };
    }

    private View.OnClickListener createDiagnosticsToggleTextListener(final CheckBox checkBox) {
        final DiagnosticsController diagnosticsController = this.mDiagnosticsController;
        return new View.OnClickListener() { // from class: com.amazon.avod.playbackclient.debug.DebugDialogBuilder.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DebugDialogBuilder.this.mDebugOptions.setShowText(checkBox.isChecked());
                if (checkBox.isChecked()) {
                    diagnosticsController.showDiagnosticInfoString();
                } else {
                    diagnosticsController.hideDiagnosticInfoString();
                }
            }
        };
    }

    private View.OnClickListener createQualityScoreGraphListener(final CheckBox checkBox) {
        final DiagnosticsController diagnosticsController = this.mDiagnosticsController;
        return new View.OnClickListener() { // from class: com.amazon.avod.playbackclient.debug.DebugDialogBuilder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$createQualityScoreGraphListener$0(checkBox, diagnosticsController, view);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$createQualityScoreGraphListener$0(CheckBox checkBox, DiagnosticsController diagnosticsController, View view) {
        this.mDebugOptions.setShowQualityScore(checkBox.isChecked());
        if (checkBox.isChecked()) {
            diagnosticsController.showQualityScoreGraph();
        } else {
            diagnosticsController.hideQualityScoreGraph();
        }
    }

    private View.OnClickListener createDiagnosticsCdnGraphListener(final CheckBox checkBox) {
        final DiagnosticsController diagnosticsController = this.mDiagnosticsController;
        return new View.OnClickListener() { // from class: com.amazon.avod.playbackclient.debug.DebugDialogBuilder.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DebugDialogBuilder.this.mDebugOptions.setShowCdn(checkBox.isChecked());
                if (checkBox.isChecked()) {
                    diagnosticsController.showDiagnosticCdnGraph();
                } else {
                    diagnosticsController.hideDiagnosticCdnGraph();
                }
            }
        };
    }

    private View.OnClickListener createInternalStateListener(final CheckBox checkBox) {
        final DiagnosticsController diagnosticsController = this.mDiagnosticsController;
        return new View.OnClickListener() { // from class: com.amazon.avod.playbackclient.debug.DebugDialogBuilder.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DebugDialogBuilder.this.mDebugOptions.setShowInternalState(checkBox.isChecked());
                if (checkBox.isChecked()) {
                    diagnosticsController.showInternalState();
                } else {
                    diagnosticsController.hideInternalState();
                }
            }
        };
    }

    private View.OnClickListener createDiagnosticsToastListener(final CheckBox checkBox) {
        final DiagnosticsController diagnosticsController = this.mDiagnosticsController;
        return new View.OnClickListener() { // from class: com.amazon.avod.playbackclient.debug.DebugDialogBuilder.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DebugDialogBuilder.this.mDebugOptions.setShowToast(checkBox.isChecked());
                if (checkBox.isChecked()) {
                    diagnosticsController.showDiagnosticToast();
                } else {
                    diagnosticsController.hideDiagnosticToast();
                }
            }
        };
    }

    @SuppressLint({"SetTextI18n"})
    private void initializeControlsDebugging(View view) {
        initializeServiceEndpointOverrides(view.findViewById(R$id.ServiceEndpointOverrides));
        view.findViewById(R$id.ControlsDebuggingCDNSwitch).setVisibility(0);
        View viewFindViewById = view.findViewById(R$id.CDNSwitchButton);
        View viewFindViewById2 = view.findViewById(R$id.ManifestSwitchButton);
        View viewFindViewById3 = view.findViewById(R$id.OriginSwitchButton);
        Spinner spinner = (Spinner) view.findViewById(R$id.OverrideFailoverMode);
        ToggleButton toggleButton = (ToggleButton) view.findViewById(R$id.ZigZagEnabled);
        final EditText editText = (EditText) view.findViewById(R$id.ZigZagFrequency);
        Spinner spinner2 = (Spinner) view.findViewById(R$id.OverrideVideoBitrateDropdown);
        SeekBar seekBar = (SeekBar) view.findViewById(R$id.PlaybackSpeedSeekBar);
        final TextView textView = (TextView) view.findViewById(R$id.PlaybackSpeedValue);
        if (this.mPlaybackExperienceController == null) {
            viewFindViewById.setEnabled(false);
            viewFindViewById2.setEnabled(false);
            viewFindViewById3.setEnabled(false);
            editText.setEnabled(false);
            spinner2.setEnabled(false);
            seekBar.setEnabled(false);
        } else {
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.avod.playbackclient.debug.DebugDialogBuilder.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    DebugDialogBuilder.this.mPlaybackExperienceController.manuallyTriggerFailover(FailoverType.CDN, DebugDialogBuilder.this.mFailoverModeOverride);
                }
            });
            viewFindViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.avod.playbackclient.debug.DebugDialogBuilder.8
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    DebugDialogBuilder.this.mPlaybackExperienceController.manuallyTriggerFailover(FailoverType.MANIFEST_SERVICE, DebugDialogBuilder.this.mFailoverModeOverride);
                }
            });
            viewFindViewById3.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.avod.playbackclient.debug.DebugDialogBuilder.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    DebugDialogBuilder.this.mPlaybackExperienceController.manuallyTriggerFailover(FailoverType.ORIGIN, DebugDialogBuilder.this.mFailoverModeOverride);
                }
            });
            initializeFailoverModeOverrideSpinner(spinner);
            toggleButton.setChecked(false);
            toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.amazon.avod.playbackclient.debug.DebugDialogBuilder.10
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    DebugDialogBuilder.this.mZigZagEnabled = z;
                    DebugDialogBuilder.this.updateZigZagInterval();
                }
            });
            editText.setText(String.valueOf(10));
            editText.addTextChangedListener(new TextWatcher() { // from class: com.amazon.avod.playbackclient.debug.DebugDialogBuilder.11
                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable editable) {
                    int i;
                    try {
                        i = Integer.parseInt(editable.toString());
                    } catch (NumberFormatException unused) {
                        i = 0;
                    }
                    DebugDialogBuilder.this.mZigZagInterval = Math.max(1, Math.min(MediaError.DetailedErrorCode.TEXT_UNKNOWN, i));
                    if (DebugDialogBuilder.this.mZigZagInterval != i && editable.length() > 0) {
                        editText.setText(String.valueOf(DebugDialogBuilder.this.mZigZagInterval));
                    }
                    DebugDialogBuilder.this.updateZigZagInterval();
                }
            });
            initializeOverrideVideoBitrateDropdown(spinner2);
            seekBar.setProgress(33);
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.amazon.avod.playbackclient.debug.DebugDialogBuilder.12
                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStartTrackingTouch(SeekBar seekBar2) {
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStopTrackingTouch(SeekBar seekBar2) {
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onProgressChanged(SeekBar seekBar2, int i, boolean z) {
                    float f = ((i / 100.0f) * 1.5f) + 0.5f;
                    DLog.logf("Setting playback speed to %s", Float.valueOf(f));
                    DebugDialogBuilder.this.mPlaybackExperienceController.setPlaybackSpeed(f);
                    textView.setText(String.valueOf(f));
                }
            });
        }
        View viewFindViewById4 = view.findViewById(R$id.ControlsDebuggingOptions);
        viewFindViewById4.setVisibility(0);
        final TextView textView2 = (TextView) viewFindViewById4.findViewById(R$id.ControlsSurface);
        TextView textView3 = (TextView) viewFindViewById4.findViewById(R$id.SwitchControlsSurface);
        final SurfaceSwitcher surfaceSwitcher = this.mSurfaceSwitcher;
        if (surfaceSwitcher == null) {
            textView2.setText("Surface switching not available.");
            textView3.setVisibility(8);
        } else {
            this.mOnShowRunnables.add(new Runnable() { // from class: com.amazon.avod.playbackclient.debug.DebugDialogBuilder.13
                @Override // java.lang.Runnable
                public void run() {
                    textView2.setText(DebugDialogBuilder.getSurfaceStatusString(surfaceSwitcher));
                }
            });
            textView3.setVisibility(0);
            textView3.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.avod.playbackclient.debug.DebugDialogBuilder.14
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    if (surfaceSwitcher.isEmbedded()) {
                        surfaceSwitcher.switchToWindowBased();
                    } else {
                        surfaceSwitcher.switchToEmbedded();
                    }
                    textView2.setText(DebugDialogBuilder.getSurfaceStatusString(surfaceSwitcher));
                }
            });
        }
    }

    private void initializeServiceEndpointOverrides(@Nonnull View view) {
        CheckBox checkBox = (CheckBox) view.findViewById(R$id.EnableEndpointOverrides);
        final View viewFindViewById = view.findViewById(R$id.EndpointOverridesContainer);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.amazon.avod.playbackclient.debug.DebugDialogBuilder$$ExternalSyntheticLambda1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                this.f$0.lambda$initializeServiceEndpointOverrides$1(viewFindViewById, compoundButton, z);
            }
        });
        checkBox.setChecked(this.mServiceDebugConfig.shouldUseServerApiSpecificOverride());
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R$id.EndpointOverridesList);
        initializeEndpointOverridesList(linearLayout);
        initializeEndpointLocationDropdown((Spinner) view.findViewById(R$id.EndpointLocationDropdown), linearLayout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initializeServiceEndpointOverrides$1(View view, CompoundButton compoundButton, boolean z) {
        this.mServiceDebugConfig.setUseServerApiSpecificOverride(z);
        view.setVisibility(z ? 0 : 8);
    }

    private void initializeEndpointLocationDropdown(@Nonnull Spinner spinner, @Nonnull final LinearLayout linearLayout) {
        ArrayAdapter arrayAdapter = new ArrayAdapter(this.mContext, R.layout.simple_spinner_item, new ArrayList(ENDPOINT_LOCATIONS));
        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter((SpinnerAdapter) arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.amazon.avod.playbackclient.debug.DebugDialogBuilder.15
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                DebugDialogBuilder.this.mServiceEndpointOverrideLocation = (String) DebugDialogBuilder.ENDPOINT_LOCATIONS.get(i);
                updateOverriddenEndpoints();
                DebugDialogBuilder.this.setEndpointOverrides();
                updateEndpointTextViews();
            }

            private void updateOverriddenEndpoints() {
                for (Map.Entry entry : DebugDialogBuilder.this.mOverriddenEndpoints.entrySet()) {
                    if (entry.getValue() != null) {
                        DebugDialogBuilder.this.mOverriddenEndpoints.put(entry.getKey(), Joiner.on("").join("https://", Joiner.on('.').join(DebugDialogBuilder.this.mServiceEndpointOverrideLocation, "api.av-gamma.com", new Object[0]), new Object[0]));
                    }
                }
            }

            private void updateEndpointTextViews() {
                for (String str : DebugDialogBuilder.this.mServiceDebugConfig.getSupportedDebugAPIs()) {
                    ((TextView) linearLayout.findViewWithTag(str)).setText(DebugDialogBuilder.this.mServiceDebugConfig.getServerApiSpecificOverride(str).or("<Default>"));
                }
            }
        });
    }

    private void initializeEndpointOverridesList(@Nonnull LinearLayout linearLayout) {
        for (final String str : this.mServiceDebugConfig.getSupportedDebugAPIs()) {
            LinearLayout linearLayout2 = new LinearLayout(this.mContext);
            linearLayout2.setOrientation(0);
            linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            final TextView textView = new TextView(this.mContext);
            textView.setTag(str);
            textView.setText(this.mServiceDebugConfig.getServerApiSpecificOverride(str).or("<Default>"));
            final CheckBox checkBox = new CheckBox(this.mContext);
            checkBox.setChecked(this.mServiceDebugConfig.getServerApiSpecificOverride(str).isPresent());
            checkBox.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.avod.playbackclient.debug.DebugDialogBuilder$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$initializeEndpointOverridesList$2(checkBox, str, textView, view);
                }
            });
            TextView textView2 = new TextView(this.mContext);
            textView2.setText(str);
            linearLayout2.addView(checkBox);
            linearLayout2.addView(textView);
            linearLayout2.addView(textView2);
            linearLayout.addView(linearLayout2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initializeEndpointOverridesList$2(CheckBox checkBox, String str, TextView textView, View view) {
        if (checkBox.isChecked()) {
            this.mOverriddenEndpoints.put(str, Joiner.on("").join("https://", Joiner.on('.').join(this.mServiceEndpointOverrideLocation, "api.av-gamma.com", new Object[0]), new Object[0]));
        } else {
            this.mOverriddenEndpoints.put(str, null);
        }
        setEndpointOverrides();
        textView.setText(this.mServiceDebugConfig.getServerApiSpecificOverride(str).or("<Default>"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEndpointOverrides() {
        for (Map.Entry<String, String> entry : this.mOverriddenEndpoints.entrySet()) {
            this.mServiceDebugConfig.setServerApiSpecificOverride(entry.getKey(), entry.getValue());
        }
    }

    private void initializeFailoverModeOverrideSpinner(@Nonnull Spinner spinner) {
        final ArrayList arrayListNewArrayList = Lists.newArrayList();
        arrayListNewArrayList.add("No FailoverMode Override");
        for (FailoverMode failoverMode : FailoverMode.values()) {
            arrayListNewArrayList.add(failoverMode.name());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this.mContext, R.layout.simple_spinner_item, arrayListNewArrayList);
        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter((SpinnerAdapter) arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.amazon.avod.playbackclient.debug.DebugDialogBuilder.16
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                DebugDialogBuilder.this.mFailoverModeOverride = (FailoverMode) Enums.getIfPresent(FailoverMode.class, (String) arrayListNewArrayList.get(i)).orNull();
            }
        });
    }

    private void initializeOverrideVideoBitrateDropdown(Spinner spinner) {
        setVideoQualityLevels();
        ArrayAdapter<String> dropdownArrayAdapter = getDropdownArrayAdapter();
        dropdownArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter((SpinnerAdapter) dropdownArrayAdapter);
        spinner.setOnItemSelectedListener(getOverrideVideoBitrateDropdownListener());
    }

    @Nonnull
    private ArrayAdapter<String> getDropdownArrayAdapter() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("No Bitrate Override");
        List<QualityLevel> list = this.mVideoQualityLevels;
        if (list != null) {
            Iterator<QualityLevel> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().toString());
            }
        }
        return new ArrayAdapter<>(this.mContext, R.layout.simple_spinner_item, arrayList);
    }

    private void setVideoQualityLevels() {
        try {
            QualityLevel[] videoQualityLevels = this.mPlaybackExperienceController.getVideoConfig().getVideoQualityLevels();
            this.mVideoQualityLevels = videoQualityLevels != null ? new ArrayList(Arrays.asList(videoQualityLevels)) : new ArrayList();
        } catch (IllegalPlayerStateException e) {
            DLog.exceptionf(e, "Something went wrong while getting the mVideoQualityLevels", new Object[0]);
        }
    }

    @Nonnull
    private AdapterView.OnItemSelectedListener getOverrideVideoBitrateDropdownListener() {
        return new AdapterView.OnItemSelectedListener() { // from class: com.amazon.avod.playbackclient.debug.DebugDialogBuilder.17
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                int i2 = i - 1;
                DebugDialogBuilder.this.mPlaybackExperienceController.overrideVideoQuality((i2 < 0 || DebugDialogBuilder.this.mVideoQualityLevels == null) ? null : (QualityLevel) DebugDialogBuilder.this.mVideoQualityLevels.get(i2));
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateZigZagInterval() {
        this.mPlaybackExperienceController.setFailoverZigZagSpeed(this.mZigZagEnabled ? this.mZigZagInterval : 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getSurfaceStatusString(SurfaceSwitcher surfaceSwitcher) {
        Locale locale = Locale.US;
        Object[] objArr = new Object[1];
        objArr[0] = surfaceSwitcher.isEmbedded() ? "embedded" : "window";
        return String.format(locale, "Current controls surface: %s.", objArr);
    }

    public Dialog create() {
        Preconditions.checkState(this.mContext != null, "The context must be set before calling the create() method.");
        View viewInflate = LayoutInflater.from(this.mContext).inflate(R$layout.player_diagnostics_dialog, (ViewGroup) null);
        initializeDiagnostics(viewInflate);
        initializeControlsDebugging(viewInflate);
        AlertDialog alertDialogCreate = new AlertDialog.Builder(this.mContext).setCancelable(true).setView(viewInflate).create();
        alertDialogCreate.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.amazon.avod.playbackclient.debug.DebugDialogBuilder.18
            @Override // android.content.DialogInterface.OnShowListener
            public void onShow(DialogInterface dialogInterface) {
                Iterator it = DebugDialogBuilder.this.mOnShowRunnables.iterator();
                while (it.hasNext()) {
                    ((Runnable) it.next()).run();
                }
            }
        });
        return alertDialogCreate;
    }

    private static class DebugOptions extends MediaConfigBase {
        private final ConfigurationValue<Boolean> mCompactView;
        private final ConfigurationValue<Boolean> mShowCdn;
        private final ConfigurationValue<Boolean> mShowGraphEquiSpaced;
        private final ConfigurationValue<Boolean> mShowGraphLinear;
        private final ConfigurationValue<Boolean> mShowGraphLogarithmic;
        private final ConfigurationValue<Boolean> mShowInternalState;
        private final ConfigurationValue<Boolean> mShowInternalStateByOverride;
        private final ConfigurationValue<Boolean> mShowQualityScore;
        private final ConfigurationValue<Boolean> mShowText;
        private final ConfigurationValue<Boolean> mShowToast;

        /* JADX INFO: Access modifiers changed from: private */
        public static DebugOptions getInstance() {
            return SingletonHolder.INSTANCE;
        }

        private static class SingletonHolder {
            private static final DebugOptions INSTANCE = new DebugOptions();

            private SingletonHolder() {
            }
        }

        DebugOptions() {
            ConfigType configType = ConfigType.INTERNAL;
            this.mShowGraphLinear = newBooleanConfigValue("PlaybackDiagnostics_showGraphLinear", false, configType);
            this.mShowGraphLogarithmic = newBooleanConfigValue("PlaybackDiagnostics_showLogarithmic", false, configType);
            this.mShowGraphEquiSpaced = newBooleanConfigValue("PlaybackDiagnostics_showEquiSpaced", false, configType);
            this.mShowText = newBooleanConfigValue("PlaybackDiagnostics_showInfoText", false, configType);
            this.mShowToast = newBooleanConfigValue("PlaybackDiagnostics_showDiagnosticToast", false, configType);
            this.mShowCdn = newBooleanConfigValue("PlaybackDiagnostic_showDiagnosticCdnGraph", false, configType);
            this.mCompactView = newBooleanConfigValue("PlaybackDiagnostic_compactView", false, configType);
            this.mShowQualityScore = newBooleanConfigValue("PlaybackDiagnostic_showQualityScore", false, configType);
            this.mShowInternalState = newBooleanConfigValue("PlaybackDiagnostic_showInternalState", false, configType);
            this.mShowInternalStateByOverride = newBooleanConfigValue("PlaybackDiagnostic_showInternalState_byOverride", false, ConfigType.SERVER);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setShowGraph(DiagnosticsController.BandwidthScale bandwidthScale, boolean z, boolean z2) {
            ConfigurationValue<Boolean> configurationValue = this.mShowGraphLogarithmic;
            Boolean bool = Boolean.FALSE;
            configurationValue.updateValue(bool);
            this.mShowGraphLinear.updateValue(bool);
            this.mShowGraphEquiSpaced.updateValue(bool);
            this.mCompactView.updateValue(Boolean.valueOf(z2));
            if (z) {
                int i = AnonymousClass19.$SwitchMap$com$amazon$avod$media$diagnostics$DiagnosticsController$BandwidthScale[bandwidthScale.ordinal()];
                if (i == 1) {
                    this.mShowGraphLinear.updateValue(Boolean.TRUE);
                } else if (i == 2) {
                    this.mShowGraphLogarithmic.updateValue(Boolean.TRUE);
                } else {
                    if (i != 3) {
                        return;
                    }
                    this.mShowGraphEquiSpaced.updateValue(Boolean.TRUE);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setShowText(boolean z) {
            this.mShowText.updateValue(Boolean.valueOf(z));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setShowToast(boolean z) {
            this.mShowToast.updateValue(Boolean.valueOf(z));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setShowCdn(boolean z) {
            this.mShowCdn.updateValue(Boolean.valueOf(z));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isGraphLinearEnabled() {
            return this.mShowGraphLinear.getValue().booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isGraphLogarithmicEnabled() {
            return this.mShowGraphLogarithmic.getValue().booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isGraphEquiSpacedEnabled() {
            return this.mShowGraphEquiSpaced.getValue().booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isInfoTextEnabled() {
            return this.mShowText.getValue().booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isDiagnosticToastEnabled() {
            return this.mShowToast.getValue().booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isDiagnosticCdnGraphEnabled() {
            return this.mShowCdn.getValue().booleanValue();
        }

        boolean isCompactViewEnabled() {
            return this.mCompactView.getValue().booleanValue();
        }

        void setCompactView(boolean z) {
            this.mCompactView.updateValue(Boolean.valueOf(z));
        }

        boolean isQualityScoreGraphEnabled() {
            return this.mShowQualityScore.getValue().booleanValue();
        }

        void setShowQualityScore(boolean z) {
            this.mShowQualityScore.updateValue(Boolean.valueOf(z));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setShowInternalState(boolean z) {
            this.mShowInternalState.updateValue(Boolean.valueOf(z));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isInternalStateEnabled() {
            return this.mShowInternalState.getValue().booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isInternalStateEnabledByOverride() {
            return this.mShowInternalStateByOverride.getValue().booleanValue();
        }
    }

    /* JADX INFO: renamed from: com.amazon.avod.playbackclient.debug.DebugDialogBuilder$19, reason: invalid class name */
    static /* synthetic */ class AnonymousClass19 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$media$diagnostics$DiagnosticsController$BandwidthScale;

        static {
            int[] iArr = new int[DiagnosticsController.BandwidthScale.values().length];
            $SwitchMap$com$amazon$avod$media$diagnostics$DiagnosticsController$BandwidthScale = iArr;
            try {
                iArr[DiagnosticsController.BandwidthScale.LINEAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$diagnostics$DiagnosticsController$BandwidthScale[DiagnosticsController.BandwidthScale.LOGARITHMIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$diagnostics$DiagnosticsController$BandwidthScale[DiagnosticsController.BandwidthScale.EQUISPACED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
