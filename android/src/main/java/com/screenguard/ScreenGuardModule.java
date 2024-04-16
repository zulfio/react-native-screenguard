package com.screenguard;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Looper;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.screenguard.model.ScreenGuardBlurData;
import com.screenguard.model.ScreenGuardImageData;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Objects;
import com.screenguard.helper.ScreenGuardHelper;

@ReactModule(name = ScreenGuardModule.NAME)
public class ScreenGuardModule extends ReactContextBaseJavaModule {

    public static final String NAME = "ScreenGuard";

    public static final String SCREENSHOT_EVT = "onScreenShotCaptured";

    public static final String SCREEN_RECORDING_EVT = "onScreenRecordingCaptured";

    private static Handler mHandlerBlockScreenShot = new Handler(Looper.getMainLooper());

    private ReactApplicationContext currentReactContext;
    private ScreenGuard mScreenGuard;

    public ScreenGuardModule(ReactApplicationContext reactContext) {
        super(reactContext);
        currentReactContext = reactContext;
    }

    @Override
    @NonNull
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void registerScreenShotEventListener(Boolean isCaptureScreenshotFile) {
        if (mScreenGuard == null) {
            mScreenGuard = new ScreenGuard(
                    currentReactContext,
                    isCaptureScreenshotFile,
                    (url) -> currentReactContext.getJSModule(
                          DeviceEventManagerModule.RCTDeviceEventEmitter.class
                    ).emit(ScreenGuardModule.SCREENSHOT_EVT, url)
            );
        }
        mScreenGuard.register();
    }

    @ReactMethod
    public void addListener(String eventName) {
//        if (mScreenGuard == null) {
//            mScreenGuard = new ScreenGuard(
//                    currentReactContext,
//                    (url) -> currentReactContext.getJSModule(
//                            DeviceEventManagerModule.RCTDeviceEventEmitter.class
//                    ).emit(eventName, url)
//            );
//        }
//        mScreenGuard.register();
        // Keep: Required for RN built in Event Emitter Calls.
    }

    @ReactMethod
    public void removeListeners(int count) {
        // Keep: Required for RN built in Event Emitter Calls.
        if (mScreenGuard != null) {
            mScreenGuard.unregister();
            mScreenGuard = null;
        }
    }

    @ReactMethod
    public void activateShieldWithBlurView(ReadableMap screenGuardBlurData) {
        try {
            if (mHandlerBlockScreenShot == null) {
                mHandlerBlockScreenShot = new Handler(Looper.getMainLooper());
            }
            if (currentReactContext == null) {
                currentReactContext = getReactApplicationContext();
            }
            Activity currentActivity = currentReactContext.getCurrentActivity();

            if (currentActivity == null) {
                return;
            }
            if (currentActivity.getClass() == ScreenGuardColorActivity.class) {
                deactivateShield();
            }
            final View currentView =
                    currentActivity.getWindow().getDecorView().getRootView();
            currentView.setDrawingCacheEnabled(true);
            Bitmap bitmap = captureReactView(currentView);
            String localPath = saveBitmapToFile(bitmap);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Intent intent = new Intent(
                        currentReactContext.getCurrentActivity(),
                        ScreenGuardColorActivity.class
                );
                intent.putExtra(ScreenGuardBlurData.class.getName(), new ScreenGuardBlurData(
                        screenGuardBlurData.getInt("radius"),
                        localPath,
                        screenGuardBlurData.getInt("timeAfterResume")
                ));
                currentActivity.startActivity(intent);
                
            }
            if (mHandlerBlockScreenShot != null) {
                    mHandlerBlockScreenShot.post(() ->
                            currentActivity.getWindow().setFlags(
                                    WindowManager.LayoutParams.FLAG_SECURE,
                                    WindowManager.LayoutParams.FLAG_SECURE
                            ));
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @ReactMethod
    public void activateShieldWithImage(ReadableMap data) {
        try {
            if (mHandlerBlockScreenShot == null) {
                mHandlerBlockScreenShot = new Handler(Looper.getMainLooper());
            }
            if (currentReactContext == null) {
                currentReactContext = getReactApplicationContext();
            }
            Activity currentActivity = currentReactContext.getCurrentActivity();

            if (currentActivity == null) {
                return;
            }
            if (currentActivity.getClass() == ScreenGuardColorActivity.class) {
                deactivateShield();
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Intent intent = new Intent(
                        currentReactContext.getCurrentActivity(),
                        ScreenGuardColorActivity.class
                );

                ReadableMap source = data.getMap("source");
                String uriImage = "";
                if (source != null) {
                    uriImage = source.getString("uri");
                }
                String backgroundColor = data.getString("backgroundColor");
                double width = data.getDouble("width");
                double height = data.getDouble("height");
                int alignment = data.getInt("alignment");
                int timeAfterResume = data.getInt("timeAfterResume");

                intent.putExtra(ScreenGuardImageData.class.getName(), new ScreenGuardImageData(
                        backgroundColor,
                        uriImage,
                        width,
                        height,
                        alignment,
                        timeAfterResume
                ));

                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                currentActivity.startActivity(intent);
            }

            mHandlerBlockScreenShot.post(() -> currentActivity.getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_SECURE,
                    WindowManager.LayoutParams.FLAG_SECURE
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String saveBitmapToFile(Bitmap bitmap) {
        try {
            File imagePath = new File(
                    getReactApplicationContext().getCacheDir(), "bitmap_images");
            if (!imagePath.exists()) {
                imagePath.mkdirs();
            }
            Calendar calendar = Calendar.getInstance();
            long time = calendar.getTimeInMillis();

            File imageFile = new File(imagePath, "bitmap_" + time + ".png");

            FileOutputStream fos = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();

            return imageFile.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @ReactMethod
    public void activateWithoutShield() {
        try {
            deactivateShield();
            if (mHandlerBlockScreenShot == null) {
                mHandlerBlockScreenShot = new Handler(Looper.getMainLooper());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ReactMethod
    public void activateShield(String hexColor) {
        try {
            if (mHandlerBlockScreenShot == null) {
                mHandlerBlockScreenShot = new Handler(Looper.getMainLooper());
            }
            if (currentReactContext == null) {
                currentReactContext = getReactApplicationContext();
            }
            Activity currentActivity = currentReactContext.getCurrentActivity();
            if (currentActivity != null) {
                mHandlerBlockScreenShot.post(() ->
                                currentActivity.getWindow().setFlags(
                        WindowManager.LayoutParams.FLAG_SECURE, 
                        WindowManager.LayoutParams.FLAG_SECURE
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ReactMethod
    public void deactivateShield() {
        try {
            if (mHandlerBlockScreenShot == null) {
                mHandlerBlockScreenShot = new Handler(Looper.getMainLooper());
            }
            if (currentReactContext == null) {
                currentReactContext = getReactApplicationContext();
            }
            currentReactContext.sendBroadcast(
                new Intent(ScreenGuardColorActivity.SCREENGUARD_COLOR_ACTIVITY_CLOSE)
            );
            Activity currentActivity = currentReactContext.getCurrentActivity();
            if (currentActivity != null) {
               mHandlerBlockScreenShot.post(() ->
                       currentActivity
                 .getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE));
                mHandlerBlockScreenShot = null;
            } else {
                Log.w("ACTIVITY_SCREENSHOT", "handler is null");
            }
            removeListeners(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
