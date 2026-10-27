package org.xwalk.core;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public class XWalkSettings {
    public static final int LOAD_CACHE_ELSE_NETWORK = 1;
    public static final int LOAD_CACHE_ONLY = 3;
    public static final int LOAD_DEFAULT = -1;
    public static final int LOAD_NO_CACHE = 2;
    private Object bridge;
    private ArrayList<Object> constructorParams;
    private ArrayList<Object> constructorTypes;
    private XWalkCoreWrapper coreWrapper;
    private ReflectMethod postWrapperMethod;
    private ReflectMethod enumLayoutAlgorithmClassValueOfMethod = new ReflectMethod();
    private ReflectMethod setCacheModeintMethod = new ReflectMethod((Class<?>) null, "setCacheMode", (Class<?>[]) new Class[0]);
    private ReflectMethod getCacheModeMethod = new ReflectMethod((Class<?>) null, "getCacheMode", (Class<?>[]) new Class[0]);
    private ReflectMethod setBlockNetworkLoadsbooleanMethod = new ReflectMethod((Class<?>) null, "setBlockNetworkLoads", (Class<?>[]) new Class[0]);
    private ReflectMethod getBlockNetworkLoadsMethod = new ReflectMethod((Class<?>) null, "getBlockNetworkLoads", (Class<?>[]) new Class[0]);
    private ReflectMethod setAllowFileAccessbooleanMethod = new ReflectMethod((Class<?>) null, "setAllowFileAccess", (Class<?>[]) new Class[0]);
    private ReflectMethod getAllowFileAccessMethod = new ReflectMethod((Class<?>) null, "getAllowFileAccess", (Class<?>[]) new Class[0]);
    private ReflectMethod setAllowContentAccessbooleanMethod = new ReflectMethod((Class<?>) null, "setAllowContentAccess", (Class<?>[]) new Class[0]);
    private ReflectMethod getAllowContentAccessMethod = new ReflectMethod((Class<?>) null, "getAllowContentAccess", (Class<?>[]) new Class[0]);
    private ReflectMethod setJavaScriptEnabledbooleanMethod = new ReflectMethod((Class<?>) null, "setJavaScriptEnabled", (Class<?>[]) new Class[0]);
    private ReflectMethod setAllowUniversalAccessFromFileURLsbooleanMethod = new ReflectMethod((Class<?>) null, "setAllowUniversalAccessFromFileURLs", (Class<?>[]) new Class[0]);
    private ReflectMethod setAllowFileAccessFromFileURLsbooleanMethod = new ReflectMethod((Class<?>) null, "setAllowFileAccessFromFileURLs", (Class<?>[]) new Class[0]);
    private ReflectMethod setLoadsImagesAutomaticallybooleanMethod = new ReflectMethod((Class<?>) null, "setLoadsImagesAutomatically", (Class<?>[]) new Class[0]);
    private ReflectMethod getLoadsImagesAutomaticallyMethod = new ReflectMethod((Class<?>) null, "getLoadsImagesAutomatically", (Class<?>[]) new Class[0]);
    private ReflectMethod setBlockNetworkImagebooleanMethod = new ReflectMethod((Class<?>) null, "setBlockNetworkImage", (Class<?>[]) new Class[0]);
    private ReflectMethod getBlockNetworkImageMethod = new ReflectMethod((Class<?>) null, "getBlockNetworkImage", (Class<?>[]) new Class[0]);
    private ReflectMethod getJavaScriptEnabledMethod = new ReflectMethod((Class<?>) null, "getJavaScriptEnabled", (Class<?>[]) new Class[0]);
    private ReflectMethod getAllowUniversalAccessFromFileURLsMethod = new ReflectMethod((Class<?>) null, "getAllowUniversalAccessFromFileURLs", (Class<?>[]) new Class[0]);
    private ReflectMethod getAllowFileAccessFromFileURLsMethod = new ReflectMethod((Class<?>) null, "getAllowFileAccessFromFileURLs", (Class<?>[]) new Class[0]);
    private ReflectMethod setJavaScriptCanOpenWindowsAutomaticallybooleanMethod = new ReflectMethod((Class<?>) null, "setJavaScriptCanOpenWindowsAutomatically", (Class<?>[]) new Class[0]);
    private ReflectMethod getJavaScriptCanOpenWindowsAutomaticallyMethod = new ReflectMethod((Class<?>) null, "getJavaScriptCanOpenWindowsAutomatically", (Class<?>[]) new Class[0]);
    private ReflectMethod setSupportMultipleWindowsbooleanMethod = new ReflectMethod((Class<?>) null, "setSupportMultipleWindows", (Class<?>[]) new Class[0]);
    private ReflectMethod supportMultipleWindowsMethod = new ReflectMethod((Class<?>) null, "supportMultipleWindows", (Class<?>[]) new Class[0]);
    private ReflectMethod setUseWideViewPortbooleanMethod = new ReflectMethod((Class<?>) null, "setUseWideViewPort", (Class<?>[]) new Class[0]);
    private ReflectMethod getUseWideViewPortMethod = new ReflectMethod((Class<?>) null, "getUseWideViewPort", (Class<?>[]) new Class[0]);
    private ReflectMethod setDomStorageEnabledbooleanMethod = new ReflectMethod((Class<?>) null, "setDomStorageEnabled", (Class<?>[]) new Class[0]);
    private ReflectMethod getDomStorageEnabledMethod = new ReflectMethod((Class<?>) null, "getDomStorageEnabled", (Class<?>[]) new Class[0]);
    private ReflectMethod setDatabaseEnabledbooleanMethod = new ReflectMethod((Class<?>) null, "setDatabaseEnabled", (Class<?>[]) new Class[0]);
    private ReflectMethod getDatabaseEnabledMethod = new ReflectMethod((Class<?>) null, "getDatabaseEnabled", (Class<?>[]) new Class[0]);
    private ReflectMethod setMediaPlaybackRequiresUserGesturebooleanMethod = new ReflectMethod((Class<?>) null, "setMediaPlaybackRequiresUserGesture", (Class<?>[]) new Class[0]);
    private ReflectMethod getMediaPlaybackRequiresUserGestureMethod = new ReflectMethod((Class<?>) null, "getMediaPlaybackRequiresUserGesture", (Class<?>[]) new Class[0]);
    private ReflectMethod setUserAgentStringStringMethod = new ReflectMethod((Class<?>) null, "setUserAgentString", (Class<?>[]) new Class[0]);
    private ReflectMethod getUserAgentStringMethod = new ReflectMethod((Class<?>) null, "getUserAgentString", (Class<?>[]) new Class[0]);
    private ReflectMethod setAcceptLanguagesStringMethod = new ReflectMethod((Class<?>) null, "setAcceptLanguages", (Class<?>[]) new Class[0]);
    private ReflectMethod getAcceptLanguagesMethod = new ReflectMethod((Class<?>) null, "getAcceptLanguages", (Class<?>[]) new Class[0]);
    private ReflectMethod setSaveFormDatabooleanMethod = new ReflectMethod((Class<?>) null, "setSaveFormData", (Class<?>[]) new Class[0]);
    private ReflectMethod getSaveFormDataMethod = new ReflectMethod((Class<?>) null, "getSaveFormData", (Class<?>[]) new Class[0]);
    private ReflectMethod setInitialPageScalefloatMethod = new ReflectMethod((Class<?>) null, "setInitialPageScale", (Class<?>[]) new Class[0]);
    private ReflectMethod setTextZoomintMethod = new ReflectMethod((Class<?>) null, "setTextZoom", (Class<?>[]) new Class[0]);
    private ReflectMethod getTextZoomMethod = new ReflectMethod((Class<?>) null, "getTextZoom", (Class<?>[]) new Class[0]);
    private ReflectMethod setDefaultFontSizeintMethod = new ReflectMethod((Class<?>) null, "setDefaultFontSize", (Class<?>[]) new Class[0]);
    private ReflectMethod getDefaultFontSizeMethod = new ReflectMethod((Class<?>) null, "getDefaultFontSize", (Class<?>[]) new Class[0]);
    private ReflectMethod setDefaultFixedFontSizeintMethod = new ReflectMethod((Class<?>) null, "setDefaultFixedFontSize", (Class<?>[]) new Class[0]);
    private ReflectMethod getDefaultFixedFontSizeMethod = new ReflectMethod((Class<?>) null, "getDefaultFixedFontSize", (Class<?>[]) new Class[0]);
    private ReflectMethod setSupportZoombooleanMethod = new ReflectMethod((Class<?>) null, "setSupportZoom", (Class<?>[]) new Class[0]);
    private ReflectMethod supportZoomMethod = new ReflectMethod((Class<?>) null, "supportZoom", (Class<?>[]) new Class[0]);
    private ReflectMethod setBuiltInZoomControlsbooleanMethod = new ReflectMethod((Class<?>) null, "setBuiltInZoomControls", (Class<?>[]) new Class[0]);
    private ReflectMethod getBuiltInZoomControlsMethod = new ReflectMethod((Class<?>) null, "getBuiltInZoomControls", (Class<?>[]) new Class[0]);
    private ReflectMethod supportsMultiTouchZoomForTestMethod = new ReflectMethod((Class<?>) null, "supportsMultiTouchZoomForTest", (Class<?>[]) new Class[0]);
    private ReflectMethod setSupportSpatialNavigationbooleanMethod = new ReflectMethod((Class<?>) null, "setSupportSpatialNavigation", (Class<?>[]) new Class[0]);
    private ReflectMethod getSupportSpatialNavigationMethod = new ReflectMethod((Class<?>) null, "getSupportSpatialNavigation", (Class<?>[]) new Class[0]);
    private ReflectMethod setSupportQuirksModebooleanMethod = new ReflectMethod((Class<?>) null, "setSupportQuirksMode", (Class<?>[]) new Class[0]);
    private ReflectMethod getSupportQuirksModeMethod = new ReflectMethod((Class<?>) null, "getSupportQuirksMode", (Class<?>[]) new Class[0]);
    private ReflectMethod setLayoutAlgorithmLayoutAlgorithmInternalMethod = new ReflectMethod((Class<?>) null, "setLayoutAlgorithm", (Class<?>[]) new Class[0]);
    private ReflectMethod getLayoutAlgorithmMethod = new ReflectMethod((Class<?>) null, "getLayoutAlgorithm", (Class<?>[]) new Class[0]);
    private ReflectMethod setLoadWithOverviewModebooleanMethod = new ReflectMethod((Class<?>) null, "setLoadWithOverviewMode", (Class<?>[]) new Class[0]);
    private ReflectMethod getLoadWithOverviewModeMethod = new ReflectMethod((Class<?>) null, "getLoadWithOverviewMode", (Class<?>[]) new Class[0]);

    public enum LayoutAlgorithm {
        NORMAL,
        SINGLE_COLUMN,
        NARROW_COLUMNS,
        TEXT_AUTOSIZING
    }

    private Object ConvertLayoutAlgorithm(LayoutAlgorithm layoutAlgorithm) {
        return this.enumLayoutAlgorithmClassValueOfMethod.invoke(layoutAlgorithm.toString());
    }

    protected Object getBridge() {
        return this.bridge;
    }

    public XWalkSettings(Object obj) {
        this.bridge = obj;
        reflectionInit();
    }

    public void setCacheMode(int i) {
        try {
            this.setCacheModeintMethod.invoke(Integer.valueOf(i));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public int getCacheMode() {
        try {
            return ((Integer) this.getCacheModeMethod.invoke(new Object[0])).intValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return 0;
        }
    }

    public void setBlockNetworkLoads(boolean z) {
        try {
            this.setBlockNetworkLoadsbooleanMethod.invoke(Boolean.valueOf(z));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public boolean getBlockNetworkLoads() {
        try {
            return ((Boolean) this.getBlockNetworkLoadsMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public void setAllowFileAccess(boolean z) {
        try {
            this.setAllowFileAccessbooleanMethod.invoke(Boolean.valueOf(z));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public boolean getAllowFileAccess() {
        try {
            return ((Boolean) this.getAllowFileAccessMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public void setAllowContentAccess(boolean z) {
        try {
            this.setAllowContentAccessbooleanMethod.invoke(Boolean.valueOf(z));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public boolean getAllowContentAccess() {
        try {
            return ((Boolean) this.getAllowContentAccessMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public void setJavaScriptEnabled(boolean z) {
        try {
            this.setJavaScriptEnabledbooleanMethod.invoke(Boolean.valueOf(z));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void setAllowUniversalAccessFromFileURLs(boolean z) {
        try {
            this.setAllowUniversalAccessFromFileURLsbooleanMethod.invoke(Boolean.valueOf(z));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void setAllowFileAccessFromFileURLs(boolean z) {
        try {
            this.setAllowFileAccessFromFileURLsbooleanMethod.invoke(Boolean.valueOf(z));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void setLoadsImagesAutomatically(boolean z) {
        try {
            this.setLoadsImagesAutomaticallybooleanMethod.invoke(Boolean.valueOf(z));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public boolean getLoadsImagesAutomatically() {
        try {
            return ((Boolean) this.getLoadsImagesAutomaticallyMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public void setBlockNetworkImage(boolean z) {
        try {
            this.setBlockNetworkImagebooleanMethod.invoke(Boolean.valueOf(z));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public boolean getBlockNetworkImage() {
        try {
            return ((Boolean) this.getBlockNetworkImageMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public boolean getJavaScriptEnabled() {
        try {
            return ((Boolean) this.getJavaScriptEnabledMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public boolean getAllowUniversalAccessFromFileURLs() {
        try {
            return ((Boolean) this.getAllowUniversalAccessFromFileURLsMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public boolean getAllowFileAccessFromFileURLs() {
        try {
            return ((Boolean) this.getAllowFileAccessFromFileURLsMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public void setJavaScriptCanOpenWindowsAutomatically(boolean z) {
        try {
            this.setJavaScriptCanOpenWindowsAutomaticallybooleanMethod.invoke(Boolean.valueOf(z));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public boolean getJavaScriptCanOpenWindowsAutomatically() {
        try {
            return ((Boolean) this.getJavaScriptCanOpenWindowsAutomaticallyMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public void setSupportMultipleWindows(boolean z) {
        try {
            this.setSupportMultipleWindowsbooleanMethod.invoke(Boolean.valueOf(z));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public boolean supportMultipleWindows() {
        try {
            return ((Boolean) this.supportMultipleWindowsMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public void setUseWideViewPort(boolean z) {
        try {
            this.setUseWideViewPortbooleanMethod.invoke(Boolean.valueOf(z));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public boolean getUseWideViewPort() {
        try {
            return ((Boolean) this.getUseWideViewPortMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public void setDomStorageEnabled(boolean z) {
        try {
            this.setDomStorageEnabledbooleanMethod.invoke(Boolean.valueOf(z));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public boolean getDomStorageEnabled() {
        try {
            return ((Boolean) this.getDomStorageEnabledMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public void setDatabaseEnabled(boolean z) {
        try {
            this.setDatabaseEnabledbooleanMethod.invoke(Boolean.valueOf(z));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public boolean getDatabaseEnabled() {
        try {
            return ((Boolean) this.getDatabaseEnabledMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public void setMediaPlaybackRequiresUserGesture(boolean z) {
        try {
            this.setMediaPlaybackRequiresUserGesturebooleanMethod.invoke(Boolean.valueOf(z));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public boolean getMediaPlaybackRequiresUserGesture() {
        try {
            return ((Boolean) this.getMediaPlaybackRequiresUserGestureMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public void setUserAgentString(String str) {
        try {
            this.setUserAgentStringStringMethod.invoke(str);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public String getUserAgentString() {
        try {
            return (String) this.getUserAgentStringMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
        }
    }

    public void setAcceptLanguages(String str) {
        try {
            this.setAcceptLanguagesStringMethod.invoke(str);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public String getAcceptLanguages() {
        try {
            return (String) this.getAcceptLanguagesMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
        }
    }

    public void setSaveFormData(boolean z) {
        try {
            this.setSaveFormDatabooleanMethod.invoke(Boolean.valueOf(z));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public boolean getSaveFormData() {
        try {
            return ((Boolean) this.getSaveFormDataMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public void setInitialPageScale(float f) {
        try {
            this.setInitialPageScalefloatMethod.invoke(Float.valueOf(f));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void setTextZoom(int i) {
        try {
            this.setTextZoomintMethod.invoke(Integer.valueOf(i));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public int getTextZoom() {
        try {
            return ((Integer) this.getTextZoomMethod.invoke(new Object[0])).intValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return 0;
        }
    }

    public void setDefaultFontSize(int i) {
        try {
            this.setDefaultFontSizeintMethod.invoke(Integer.valueOf(i));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public int getDefaultFontSize() {
        try {
            return ((Integer) this.getDefaultFontSizeMethod.invoke(new Object[0])).intValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return 0;
        }
    }

    public void setDefaultFixedFontSize(int i) {
        try {
            this.setDefaultFixedFontSizeintMethod.invoke(Integer.valueOf(i));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public int getDefaultFixedFontSize() {
        try {
            return ((Integer) this.getDefaultFixedFontSizeMethod.invoke(new Object[0])).intValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return 0;
        }
    }

    public void setSupportZoom(boolean z) {
        try {
            this.setSupportZoombooleanMethod.invoke(Boolean.valueOf(z));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public boolean supportZoom() {
        try {
            return ((Boolean) this.supportZoomMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public void setBuiltInZoomControls(boolean z) {
        try {
            this.setBuiltInZoomControlsbooleanMethod.invoke(Boolean.valueOf(z));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public boolean getBuiltInZoomControls() {
        try {
            return ((Boolean) this.getBuiltInZoomControlsMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public boolean supportsMultiTouchZoomForTest() {
        try {
            return ((Boolean) this.supportsMultiTouchZoomForTestMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public void setSupportSpatialNavigation(boolean z) {
        try {
            this.setSupportSpatialNavigationbooleanMethod.invoke(Boolean.valueOf(z));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public boolean getSupportSpatialNavigation() {
        try {
            return ((Boolean) this.getSupportSpatialNavigationMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public void setSupportQuirksMode(boolean z) {
        try {
            this.setSupportQuirksModebooleanMethod.invoke(Boolean.valueOf(z));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public boolean getSupportQuirksMode() {
        try {
            return ((Boolean) this.getSupportQuirksModeMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public void setLayoutAlgorithm(LayoutAlgorithm layoutAlgorithm) {
        try {
            this.setLayoutAlgorithmLayoutAlgorithmInternalMethod.invoke(ConvertLayoutAlgorithm(layoutAlgorithm));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public LayoutAlgorithm getLayoutAlgorithm() {
        try {
            return LayoutAlgorithm.valueOf(this.getLayoutAlgorithmMethod.invoke(new Object[0]).toString());
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
        }
    }

    public void setLoadWithOverviewMode(boolean z) {
        try {
            this.setLoadWithOverviewModebooleanMethod.invoke(Boolean.valueOf(z));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public boolean getLoadWithOverviewMode() {
        try {
            return ((Boolean) this.getLoadWithOverviewModeMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    void reflectionInit() {
        XWalkCoreWrapper.initEmbeddedMode();
        XWalkCoreWrapper xWalkCoreWrapper = XWalkCoreWrapper.getInstance();
        this.coreWrapper = xWalkCoreWrapper;
        if (xWalkCoreWrapper == null) {
            XWalkCoreWrapper.reserveReflectObject(this);
            return;
        }
        this.enumLayoutAlgorithmClassValueOfMethod.init(null, xWalkCoreWrapper.getBridgeClass("XWalkSettingsInternal$LayoutAlgorithmInternal"), "valueOf", String.class);
        this.setCacheModeintMethod.init(this.bridge, null, "setCacheModeSuper", Integer.TYPE);
        this.getCacheModeMethod.init(this.bridge, null, "getCacheModeSuper", new Class[0]);
        this.setBlockNetworkLoadsbooleanMethod.init(this.bridge, null, "setBlockNetworkLoadsSuper", Boolean.TYPE);
        this.getBlockNetworkLoadsMethod.init(this.bridge, null, "getBlockNetworkLoadsSuper", new Class[0]);
        this.setAllowFileAccessbooleanMethod.init(this.bridge, null, "setAllowFileAccessSuper", Boolean.TYPE);
        this.getAllowFileAccessMethod.init(this.bridge, null, "getAllowFileAccessSuper", new Class[0]);
        this.setAllowContentAccessbooleanMethod.init(this.bridge, null, "setAllowContentAccessSuper", Boolean.TYPE);
        this.getAllowContentAccessMethod.init(this.bridge, null, "getAllowContentAccessSuper", new Class[0]);
        this.setJavaScriptEnabledbooleanMethod.init(this.bridge, null, "setJavaScriptEnabledSuper", Boolean.TYPE);
        this.setAllowUniversalAccessFromFileURLsbooleanMethod.init(this.bridge, null, "setAllowUniversalAccessFromFileURLsSuper", Boolean.TYPE);
        this.setAllowFileAccessFromFileURLsbooleanMethod.init(this.bridge, null, "setAllowFileAccessFromFileURLsSuper", Boolean.TYPE);
        this.setLoadsImagesAutomaticallybooleanMethod.init(this.bridge, null, "setLoadsImagesAutomaticallySuper", Boolean.TYPE);
        this.getLoadsImagesAutomaticallyMethod.init(this.bridge, null, "getLoadsImagesAutomaticallySuper", new Class[0]);
        this.setBlockNetworkImagebooleanMethod.init(this.bridge, null, "setBlockNetworkImageSuper", Boolean.TYPE);
        this.getBlockNetworkImageMethod.init(this.bridge, null, "getBlockNetworkImageSuper", new Class[0]);
        this.getJavaScriptEnabledMethod.init(this.bridge, null, "getJavaScriptEnabledSuper", new Class[0]);
        this.getAllowUniversalAccessFromFileURLsMethod.init(this.bridge, null, "getAllowUniversalAccessFromFileURLsSuper", new Class[0]);
        this.getAllowFileAccessFromFileURLsMethod.init(this.bridge, null, "getAllowFileAccessFromFileURLsSuper", new Class[0]);
        this.setJavaScriptCanOpenWindowsAutomaticallybooleanMethod.init(this.bridge, null, "setJavaScriptCanOpenWindowsAutomaticallySuper", Boolean.TYPE);
        this.getJavaScriptCanOpenWindowsAutomaticallyMethod.init(this.bridge, null, "getJavaScriptCanOpenWindowsAutomaticallySuper", new Class[0]);
        this.setSupportMultipleWindowsbooleanMethod.init(this.bridge, null, "setSupportMultipleWindowsSuper", Boolean.TYPE);
        this.supportMultipleWindowsMethod.init(this.bridge, null, "supportMultipleWindowsSuper", new Class[0]);
        this.setUseWideViewPortbooleanMethod.init(this.bridge, null, "setUseWideViewPortSuper", Boolean.TYPE);
        this.getUseWideViewPortMethod.init(this.bridge, null, "getUseWideViewPortSuper", new Class[0]);
        this.setDomStorageEnabledbooleanMethod.init(this.bridge, null, "setDomStorageEnabledSuper", Boolean.TYPE);
        this.getDomStorageEnabledMethod.init(this.bridge, null, "getDomStorageEnabledSuper", new Class[0]);
        this.setDatabaseEnabledbooleanMethod.init(this.bridge, null, "setDatabaseEnabledSuper", Boolean.TYPE);
        this.getDatabaseEnabledMethod.init(this.bridge, null, "getDatabaseEnabledSuper", new Class[0]);
        this.setMediaPlaybackRequiresUserGesturebooleanMethod.init(this.bridge, null, "setMediaPlaybackRequiresUserGestureSuper", Boolean.TYPE);
        this.getMediaPlaybackRequiresUserGestureMethod.init(this.bridge, null, "getMediaPlaybackRequiresUserGestureSuper", new Class[0]);
        this.setUserAgentStringStringMethod.init(this.bridge, null, "setUserAgentStringSuper", String.class);
        this.getUserAgentStringMethod.init(this.bridge, null, "getUserAgentStringSuper", new Class[0]);
        this.setAcceptLanguagesStringMethod.init(this.bridge, null, "setAcceptLanguagesSuper", String.class);
        this.getAcceptLanguagesMethod.init(this.bridge, null, "getAcceptLanguagesSuper", new Class[0]);
        this.setSaveFormDatabooleanMethod.init(this.bridge, null, "setSaveFormDataSuper", Boolean.TYPE);
        this.getSaveFormDataMethod.init(this.bridge, null, "getSaveFormDataSuper", new Class[0]);
        this.setInitialPageScalefloatMethod.init(this.bridge, null, "setInitialPageScaleSuper", Float.TYPE);
        this.setTextZoomintMethod.init(this.bridge, null, "setTextZoomSuper", Integer.TYPE);
        this.getTextZoomMethod.init(this.bridge, null, "getTextZoomSuper", new Class[0]);
        this.setDefaultFontSizeintMethod.init(this.bridge, null, "setDefaultFontSizeSuper", Integer.TYPE);
        this.getDefaultFontSizeMethod.init(this.bridge, null, "getDefaultFontSizeSuper", new Class[0]);
        this.setDefaultFixedFontSizeintMethod.init(this.bridge, null, "setDefaultFixedFontSizeSuper", Integer.TYPE);
        this.getDefaultFixedFontSizeMethod.init(this.bridge, null, "getDefaultFixedFontSizeSuper", new Class[0]);
        this.setSupportZoombooleanMethod.init(this.bridge, null, "setSupportZoomSuper", Boolean.TYPE);
        this.supportZoomMethod.init(this.bridge, null, "supportZoomSuper", new Class[0]);
        this.setBuiltInZoomControlsbooleanMethod.init(this.bridge, null, "setBuiltInZoomControlsSuper", Boolean.TYPE);
        this.getBuiltInZoomControlsMethod.init(this.bridge, null, "getBuiltInZoomControlsSuper", new Class[0]);
        this.supportsMultiTouchZoomForTestMethod.init(this.bridge, null, "supportsMultiTouchZoomForTestSuper", new Class[0]);
        this.setSupportSpatialNavigationbooleanMethod.init(this.bridge, null, "setSupportSpatialNavigationSuper", Boolean.TYPE);
        this.getSupportSpatialNavigationMethod.init(this.bridge, null, "getSupportSpatialNavigationSuper", new Class[0]);
        this.setSupportQuirksModebooleanMethod.init(this.bridge, null, "setSupportQuirksModeSuper", Boolean.TYPE);
        this.getSupportQuirksModeMethod.init(this.bridge, null, "getSupportQuirksModeSuper", new Class[0]);
        this.setLayoutAlgorithmLayoutAlgorithmInternalMethod.init(this.bridge, null, "setLayoutAlgorithmSuper", this.coreWrapper.getBridgeClass("XWalkSettingsInternal$LayoutAlgorithmInternal"));
        this.getLayoutAlgorithmMethod.init(this.bridge, null, "getLayoutAlgorithmSuper", new Class[0]);
        this.setLoadWithOverviewModebooleanMethod.init(this.bridge, null, "setLoadWithOverviewModeSuper", Boolean.TYPE);
        this.getLoadWithOverviewModeMethod.init(this.bridge, null, "getLoadWithOverviewModeSuper", new Class[0]);
    }
}
