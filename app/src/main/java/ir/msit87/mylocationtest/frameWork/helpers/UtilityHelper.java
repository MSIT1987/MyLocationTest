package ir.msit87.mylocationtest.frameWork.helpers;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
//import ir.msit87.smarthouse.SmartFrameWork.ui.custom.PickerDialogController;
//import ir.msit87.smarthouse.SmartFrameWork.ui.custom.PickerDialogController.PickerDialogResult;
import com.google.android.gms.common.GoogleApiAvailability;
import eu.amirs.JSON;
//import io.fabric.sdk.android.services.common.IdManager;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Locale;

public class UtilityHelper {

    public interface DelayCallback {
        void afterDelay();
    }

    public static boolean checkPlayServices(Context context) {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) != 0) {
            return false;
        }
        return true;
    }

    /**
     public static void checkPlayServicesAndAskToUpdate(final Context context) {
     if (!checkPlayServices(context)) {
     PickerDialogController.showYesNoDialog("جهت استفاده کامل از قابلیت‌های اسپارد لازم است خدمات گوگل پلی را به روز رسانی نمائید.", context, new PickerDialogResult() {
     public void onFinish(JSON data) {
     if (data.key("ok").booleanValue()) {
     context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.google.android.gms")));
     }
     }
     });
     }
     }
     */


    public static boolean mobileValidation(String mobile) {
        return mobile.length() == 11 && mobile.startsWith("09");
    }

    public static boolean ibanValidation(String code) {
        boolean z = true;
        String reformattedCode = code.substring(4) + code.substring(0, 4);
        long total = 0;
        for (int i = 0; i < reformattedCode.length(); i++) {
            int charValue = Character.getNumericValue(reformattedCode.charAt(i));
            if (charValue < 0 || charValue > 35) {
                return false;
            }
            total = (charValue > 9 ? 100 * total : 10 * total) + ((long) charValue);
            if (total > 999999999) {
                total %= 97;
            }
        }
        if (((int) (total % 97)) != 1) {
            z = false;
        }
        return z;
    }

    public static boolean shabaValidation(String shaba) {
        if (shaba.length() != 26) {
            return false;
        }
        if ((shaba.startsWith("IR") || shaba.startsWith("ir")) && ibanValidation(shaba)) {
            return true;
        }
        return false;
    }

    /**
     public static void openUrl(Context context, String url) {
     if (url != null) {
     try {
     if (url.length() < 3) {
     return;
     }
     if (url.startsWith("http://")) {
     context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
     } else if (url.startsWith("tel://")) {
     intent = new Intent("android.intent.action.DIAL");
     intent.setData(Uri.parse("tel:" + url.replace("tel://", "")));
     context.startActivity(intent);
     } else if (url.toLowerCase().startsWith("espardus://fetch:")) {
     DataManagement.getInstance().fetchSectionByName(url.toLowerCase().replace("espardus://fetch:", ""));
     } else {
     intent = new Intent("android.intent.action.VIEW");
     intent.setData(Uri.parse(url));
     context.startActivity(intent);
     }
     } catch (Exception e) {
     }
     }
     }
     */

    public static void delay(double secs, final DelayCallback delayCallback) {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                delayCallback.afterDelay();
            }
        }, (long) (1000.0d * secs));
    }

    public static String persianNumber(int number) {
        return persianNumber(number + "");
    }

    public static String persianNumber(String string) {
        return string.replace("0", "۰").replace("1", "۱").replace("2", "۲").replace("3", "۳").replace("4", "۴").replace("5", "۵").replace("6", "۶").replace("7", "۷").replace("8", "۸").replace("9", "۹");
    }

    public static String engNumber(String string) {
        return string.replace("۰", "0").replace("۱", "1").replace("۲", "2").replace("۳", "3").replace("۴", "4").replace("۵", "5").replace("۶", "6").replace("۷", "7").replace("۸", "8").replace("۹", "9");
    }

//    public static String inMoneyFormat(String S) {
//        try {
//            return inMoneyFormat(Long.valueOf(S).longValue(), true);
//        } catch (NumberFormatException e) {
//            return "";
//        }
//    }

//    public static String inMoneyFormat(long number) {
//        return inMoneyFormat(number, true);
//    }

    /**
     public static String inMoneyFormat(long number, boolean showCurrency) {
     boolean negative = false;
     if (number < 0) {
     number *= -1;
     negative = true;
     }
     String money = persianNumber(String.format(Locale.US, "%,d", new Object[]{Long.valueOf(number)}).replace(",", "،"));
     if (negative) {
     money = money + " - ";
     }
     if (showCurrency) {
     return money + " " + DataManagement.getInstance().getCurrency();
     }
     return money;
     }
     */

    public static int getResourceIdByString(String resourceName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resourceName);
            return idField.getInt(idField);
        } catch (Exception e) {
            throw new RuntimeException("No resource ID found for: " + resourceName + " / " + c, e);
        }
    }

    public static int pxToDp(int px) {
        return (int) (((float) px) / Resources.getSystem().getDisplayMetrics().density);
    }

    public static int dpToPx(int dp) {
        return (int) (((float) dp) * Resources.getSystem().getDisplayMetrics().density);
    }

    public static Drawable getBackgroundDrawableWithCustomRadius(int color, int leftTopRadiusInDp, int rightTopRadiusInDp, int leftBottomRadiusInDp, int rightBottomRadiusInDp) {
        GradientDrawable gradientDrawable = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{color, color});
        gradientDrawable.setCornerRadii(new float[]{(float) dpToPx(leftTopRadiusInDp), (float) dpToPx(leftTopRadiusInDp), (float) dpToPx(rightTopRadiusInDp), (float) dpToPx(rightTopRadiusInDp), (float) dpToPx(rightBottomRadiusInDp), (float) dpToPx(rightBottomRadiusInDp), (float) dpToPx(leftBottomRadiusInDp), (float) dpToPx(leftBottomRadiusInDp)});
        return gradientDrawable;
    }

    public static String md5(String s) {
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            digest.update(s.getBytes());
            byte[] bytes = digest.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(String.format("%02X", new Object[]{Byte.valueOf(bytes[i])}));
            }
            return sb.toString().toLowerCase();
        } catch (Exception e) {
            return "";
        }
    }

    public static long getTimeInSecond() {
        return Calendar.getInstance().getTimeInMillis() / 1000;
    }

    public static String getDeviceId(Context context) {
        if (context != null) {
            return System.getString(context.getContentResolver(), "android_id");
        }
        return "";
    }

//    public static String getAppVersionString(Context context) {
//        String versionCode = IdManager.DEFAULT_VERSION_NAME;
//        try {
//            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
//        } catch (NameNotFoundException e) {
//            e.printStackTrace();
//            return versionCode;
//        }
//    }

    public static String getAppVersionInt(Context context) {
        String versionCode = "0";
        try {
            versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode + "";
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    public static String sharedPreferencesGetValue(Context context, String key, String fileName) {
        return context.getSharedPreferences(fileName, 0).getString(key, null);
    }

    public static void sharedPreferencesSetValue(Context context, String key, String value, String fileName) {
        Editor editor = context.getSharedPreferences(fileName, 0).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;
        StringBuilder phrase = new StringBuilder();
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase.append(Character.toUpperCase(c));
                capitalizeNext = false;
            } else {
                if (Character.isWhitespace(c)) {
                    capitalizeNext = true;
                }
                phrase.append(c);
            }
        }
        return phrase.toString();
    }

    public static void enableDisableViewGroup(View rootView, boolean enabled) {
        if (ViewGroup.class.isInstance(rootView)) {
            ViewGroup viewGroup = (ViewGroup) rootView;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View view = viewGroup.getChildAt(i);
                view.setEnabled(enabled);
                if (view instanceof ViewGroup) {
                    enableDisableViewGroup((ViewGroup) view, enabled);
                }
            }
            return;
        }
        rootView.setEnabled(enabled);
    }

    public static String lastPath(String path) {
        String[] sections = path.split("/");
        return sections[sections.length - 1];
    }

    public static LayoutInflater getInflater(Context context) {
        if (Activity.class.isInstance(context)) {
            return ((Activity) context).getLayoutInflater();
        }
        return LayoutInflater.from(context);
    }

//    public static void hideSoftKeyboard(Activity activity) {
//        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService("input_method");
//        if (activity.getCurrentFocus() != null) {
//            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
//        }
//    }

    public static void setupUIForHideSoftwareKeyboard(View view, final Activity activity) {
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    /**
                     * UtilityHelper.hideSoftKeyboard(activity);
                     */
                    return false;
                }
            });
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                setupUIForHideSoftwareKeyboard(((ViewGroup) view).getChildAt(i), activity);
            }
        }
    }
}
