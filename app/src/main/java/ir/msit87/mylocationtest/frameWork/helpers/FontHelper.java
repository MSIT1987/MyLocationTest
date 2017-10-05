package ir.msit87.mylocationtest.frameWork.helpers;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.text.Format;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Hashtable;
import java.util.List;


public class FontHelper {


    public static String YEKAN_MOBILE_BOLD = "B TRAFFIC BOLD_P30DOWNLOAD.COM.TTF";
    public static String YEKAN_MOBILE_REGULAR = "B TRAFFIC_P30DOWNLOAD.COM.TTF";
    public static String IRAN_SANS_REGULAR = "iran_sans.ttf";

    private static Hashtable<String, Typeface> fontCache = new Hashtable();


    public static String[] IMAGE = {"Base-service-building.png", "Base-service-home.png", "Base-service-office.png", "Bathroom.png"};

    private static Hashtable<String, Drawable> imgCache = new Hashtable();

    private static String[] Text = {"اتاق", "پذیرایی", "پارکینگ", "آشپزخانه"};

    public static List<String> Text_List;
    public static List<Object> Object_List;

    public static String[] PREFVARS = {"firstItem", "secondItem", "thirdItem"};

    private static Hashtable<String, Integer> prefCache = new Hashtable();

    private Editor editor;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    public static Typeface get(String name, Context context) {
        Typeface tf = fontCache.get(name);
        if (tf == null) {
            try {
                tf = Typeface.createFromAsset(context.getAssets(), "fonts/" + name);
                fontCache.put(name, tf);
            } catch (Exception e) {
                return null;
            }
        }
        return tf;
    }

    public static Drawable getDrawable(String name, Context context) {
        Drawable drawable = imgCache.get(name);
        if (drawable == null) {
            try {
                drawable = Drawable.createFromStream(context.getAssets().open("static-images/" + name), null);
                imgCache.put(name, drawable);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return drawable;
    }

    static {
//        Object_List = new ArrayList();
//
//        Object_List.add(new OnOffItem("لامپ", "LGT1", "LGT0", R.mipmap.streetlightoff));
//        Object_List.add(new OnOffItem("درب پارکینگ", "DOR1", "DOR0", R.mipmap.garage));
//        Object_List.add(new OnOffItem("هواکش", "FAN1", "FAN0", R.mipmap.fanpropellers));
//        Object_List.add(new OnOffItem("رله", "RLY1", "RLY0", R.mipmap.btn_switch_off));
//
//        Object_List.add(new FanTempItem("دما", "TEMP", "TEMP", R.mipmap.celsius));
//        Object_List.add(new FanTempItem("رطوبت", "HUMI", "HUMI", R.mipmap.humidity));
//
//        Text_List = new ArrayList();
//        for (int i = 0; i < 4; i++) {
//            Text_List.add(i, Text[i]);
//        }
    }

    public static void setPrefCache(String key, Integer value) {
        prefCache.put(key, value);
    }
}
//        Text_List = new ArrayList();
//        for (int i = 0; i < 4; i++) {
//            Text_List.add(i, Text[i]);
//        }